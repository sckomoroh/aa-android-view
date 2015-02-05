package com.apprecovery.view.recoverypoints;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.TextView;
import com.apprecovery.AAViewerUI.R;
import com.apprecovery.exchange.ExchangeServerVersion;
import com.apprecovery.exchange.LegacyStorageGroupCollection;
import com.apprecovery.exchange.MailStoreCollection;
import com.apprecovery.recoverypoints.RecoveryPointsManagement;
import com.apprecovery.recoverypoints.RecoveryPointsResponse;
import com.apprecovery.sql.SqlInstanceCollection;
import com.apprecovery.sql.SqlServer;
import com.apprecovery.view.agents.AgentsActivity;
import com.apprecovery.view.recoverypoints.adapter.RecoveryPointsAdapter;
import com.apprecovery.volumeimage.VolumeImageSummary;

/**
 * Created with IntelliJ IDEA.
 * User: sckomoroh
 * Date: 5/12/13
 * Time: 9:29 AM
 */

public class RecoveryPointsActivity extends Activity
{
    private RecoveryPointsAdapter adapter;
    private TextView totalRecoveryPointTextView;
    private TextView currentRecoveryPointTextView;
    private int currentPage = 1;
    private int totalPages;
    private String agentId;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recovery_points_layout);

        totalRecoveryPointTextView = (TextView) findViewById(R.id.recoveryPointsCountTextView);
        currentRecoveryPointTextView = (TextView) findViewById(R.id.currentRecoveryPointPageTextView);

        ExpandableListView listView = (ExpandableListView)findViewById(R.id.recoveryPointListView);
        adapter = new RecoveryPointsAdapter(this);
        listView.setAdapter(adapter);

        TextView agentName = (TextView) findViewById(R.id.recoveryPointAgentNameTextView);

        agentId = getIntent().getStringExtra(AgentsActivity.AGENT_ID_MESSAGE_ID);
        String agentDisplayName = getIntent().getStringExtra(AgentsActivity.AGENT_DISPLAY_NAME_MESSAGE_ID);
        agentName.setText(agentDisplayName);

        updateRecoveryPointList();

        listView.setOnChildClickListener(new ExpandableListView.OnChildClickListener()
        {
            @Override
            public boolean onChildClick(ExpandableListView expandableListView, View view, int i, int i2, long l)
            {
                VolumeImageSummary volumeImageSummary = (VolumeImageSummary) adapter.getChild(i, i2);
                if (volumeImageSummary.isHasExchangeMetadata())
                {
                    ExchangeServerVersion exchangeServerVersion = volumeImageSummary.getVolumeImageComponents().getExchangeServer().getVersion();
                    if (exchangeServerVersion == ExchangeServerVersion.Exchange2010 || exchangeServerVersion == ExchangeServerVersion.Exchange2013)
                    {
                        MailStoreCollection mailStoreCollection = volumeImageSummary.getVolumeImageComponents().getExchangeServer().getMailStores();
                        if (mailStoreCollection != null)
                        {
                            Intent intent = new Intent(RecoveryPointsActivity.this, MailStoresActivity.class);
                            intent.putExtra(MailStoresActivity.MAIL_STORES_MESSAGE_ID, mailStoreCollection);
                            intent.putExtra(MailStoresActivity.RECOVERY_POINT_NAME_MESSAGE_ID, volumeImageSummary.getVolumeDisplayName());
                            intent.putExtra(MailStoresActivity.EXCHANGE_VERSION_MESSAGE_ID, exchangeServerVersion);

                            startActivity(intent);
                        }
                    }
                    else if (exchangeServerVersion == ExchangeServerVersion.Exchange2007)
                    {
                        LegacyStorageGroupCollection legacyStorageGroupCollection = volumeImageSummary.getVolumeImageComponents().getExchangeServer().getLegacyStorageGroups();
                        if (legacyStorageGroupCollection != null && volumeImageSummary.getVolumeImageState() != VolumeImageSummary.GRAY)
                        {
                            Intent intent = new Intent(RecoveryPointsActivity.this, LegacyGroupActivity.class);
                            intent.putExtra(LegacyGroupActivity.STORAGE_GROUP_COLLECTION_MESSAGE_ID, legacyStorageGroupCollection);
                            intent.putExtra(MailStoresActivity.RECOVERY_POINT_NAME_MESSAGE_ID, volumeImageSummary.getVolumeDisplayName());
                            intent.putExtra(MailStoresActivity.EXCHANGE_VERSION_MESSAGE_ID, exchangeServerVersion);

                            startActivity(intent);
                        }
                    }
                }
                else if (volumeImageSummary.isHasSqlServerMetadata())
                {
                    SqlServer sqlServer = volumeImageSummary.getVolumeImageComponents().getSqlServer();
                    if (sqlServer != null)
                    {
                        SqlInstanceCollection sqlInstances = sqlServer.getInstances();
                        if (sqlInstances != null)
                        {
                            Intent intent = new Intent(RecoveryPointsActivity.this, SqlInstancesActivity.class);
                            intent.putExtra(SqlInstancesActivity.SQL_INSTANCE_MESSAGE_ID, sqlInstances);
                            intent.putExtra(MailStoresActivity.RECOVERY_POINT_NAME_MESSAGE_ID, volumeImageSummary.getVolumeDisplayName());

                            startActivity(intent);
                        }
                    }
                }

                return false;
            }
        });

        Button firstPage = (Button) findViewById(R.id.firstRecoveryPointPageButton);
        Button nextPage = (Button) findViewById(R.id.nextRecoveryPointPageButton);
        Button prevPage = (Button) findViewById(R.id.prevRecoveryPointPageButton);
        Button lastPage = (Button) findViewById(R.id.lastRecoveryPointPageButton);

        firstPage.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                if (currentPage != 1)
                {
                    currentPage = 1;
                    updateRecoveryPointList();
                }
            }
        });

        nextPage.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                if (currentPage < totalPages)
                {
                    currentPage++;
                    updateRecoveryPointList();
                }
            }
        });

        prevPage.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                if (currentPage > 1)
                {
                    currentPage--;
                    updateRecoveryPointList();
                }
            }
        });

        lastPage.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                if (currentPage != totalPages)
                {
                    currentPage = totalPages;
                    updateRecoveryPointList();
                }
            }
        });
    }

    private void updateRecoveryPointList()
    {
        RecoveryPointsRetrieveTask retrieveTask = new RecoveryPointsRetrieveTask();
        retrieveTask.execute();
    }

    private class RecoveryPointsRetrieveTask extends AsyncTask<Void, Void, RecoveryPointsResponse>
    {
        private Exception exception;
        private ProgressDialog progressDialog;

        @Override
        protected RecoveryPointsResponse doInBackground(Void... p)
        {
            RecoveryPointsResponse result = null;

            try
            {
                RecoveryPointsManagement recoveryPointsManagement = new RecoveryPointsManagement();
                result = recoveryPointsManagement.getRecoveryPointsByPage(agentId, 10, currentPage);
            }
            catch (Exception ex)
            {
                ex.printStackTrace();
                exception = ex;
            }

            return result;
        }

        @Override
        protected void onPreExecute()
        {
            currentRecoveryPointTextView.setText("" + currentPage);
            progressDialog = new ProgressDialog(RecoveryPointsActivity.this);
            progressDialog.setIndeterminate(true);
            progressDialog.setMessage("Recovery points retrieving");
            progressDialog.show();
        }

        @Override
        protected void onPostExecute(RecoveryPointsResponse result)
        {
            progressDialog.hide();

            if (result != null)
            {
                RecoveryPointsActivity.this.adapter.updateData(result.getRecoveryPoints());
                totalPages = result.getTotal() / 10 + 1;
                totalRecoveryPointTextView.setText("" + totalPages);
            }

            if (exception != null)
            {
                AlertDialog.Builder builder = new AlertDialog.Builder(RecoveryPointsActivity.this);
                builder.setMessage(exception.getMessage()).setTitle("ERROR");
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        }
    }
}
