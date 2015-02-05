package com.apprecovery.view.repositories;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ExpandableListView;
import android.widget.TextView;
import com.apprecovery.AAViewerUI.R;
import com.apprecovery.repository.RepositoryManagement;
import com.apprecovery.repository.RepositorySummaryInfoCollection;
import com.apprecovery.view.repositories.adapter.RepositoriesAdapter;

/**
 * Created by sckomoroh on 13.10.2014.
 */
public class RepositoriesActivity extends Activity
{
    public static final String CORE_HOST_NAME_MESSAGE_ID = "CORE_HOST_NAME_MESSAGE_ID";

    private RepositoriesAdapter adapter;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.repositories);

        TextView coreHostNameTextView = (TextView)findViewById(R.id.textViewRepoCoreName);
        coreHostNameTextView.setText(getIntent().getStringExtra(CORE_HOST_NAME_MESSAGE_ID));

        RecoveryPointsRetrieveTask retrieveTask = new RecoveryPointsRetrieveTask();
        retrieveTask.execute();

        adapter = new RepositoriesAdapter(this);

        ExpandableListView repoListView = (ExpandableListView)findViewById(R.id.expandableListViewRepo);
        repoListView.setAdapter(adapter);
    }

    private class RecoveryPointsRetrieveTask extends AsyncTask<Void, Void, RepositorySummaryInfoCollection>
    {
        private Exception exception;
        private ProgressDialog progressDialog;

        @Override
        protected RepositorySummaryInfoCollection doInBackground(Void... p)
        {
            RepositorySummaryInfoCollection result = null;

            try
            {
                RepositoryManagement repositoryManagement = new RepositoryManagement();
                result = repositoryManagement.GetRepositorySummaries();
            } catch (Exception ex)
            {
                ex.printStackTrace();
                exception = ex;
            }

            return result;
        }

        @Override
        protected void onPreExecute()
        {
            progressDialog = new ProgressDialog(RepositoriesActivity.this);
            progressDialog.setIndeterminate(true);
            progressDialog.setMessage("Recovery points retrieving");
            progressDialog.show();
        }

        @Override
        protected void onPostExecute(RepositorySummaryInfoCollection result)
        {
            progressDialog.hide();

            if (result != null)
            {
                adapter.updateData(result);
            }

            if (exception != null)
            {
                AlertDialog.Builder builder = new AlertDialog.Builder(RepositoriesActivity.this);
                builder.setMessage(exception.getMessage()).setTitle("ERROR");
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        }
    }
}
