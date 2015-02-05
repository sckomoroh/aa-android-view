package com.apprecovery.view.backgroundjobs;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import com.apprecovery.AAViewerUI.R;
import com.apprecovery.backgroundjobs.*;
import com.apprecovery.view.backgroundjobs.adapter.BackgroundJobsAdapter;
import com.apprecovery.view.corehome.CoreHomeActivity;
import com.apprecovery.view.events.EventsActivity;

import java.util.Date;

/**
 * Created by sckomoroh on 12.10.2014.
 *
 */
public class BackgroundJobsActivity extends Activity
{
    private BackgroundJobsAdapter adapter;
    private TextView currentPageTextView;
    private TextView totalPagesTextView;
    private int mCurrentPage = 1;
    private int mTotalPages = 0;

    private String mAgentId = null;
    private String mHostName = null;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.background_jobs);

        mAgentId = getIntent().getStringExtra(EventsActivity.AGENT_ID_MESSAGE_ID);
        mHostName = getIntent().getStringExtra(CoreHomeActivity.HOST_NAME_ID_MESSAGE);

        currentPageTextView = (TextView)findViewById(R.id.textViewJobsCurPage);
        totalPagesTextView = (TextView)findViewById(R.id.textViewJobsLastPage);

        TextView hostNameTextView = (TextView)findViewById(R.id.textViewJobsHostName);
        TextView hostTypeTextView = (TextView)findViewById(R.id.textViewJobsType);

        if (mAgentId == null)
        {
            hostTypeTextView.setText("core");
        }
        else
        {
            hostTypeTextView.setText("agent");
        }
        hostNameTextView.setText(mHostName);

        adapter = new BackgroundJobsAdapter(this);

        ListView jobsListView = (ListView)findViewById(R.id.listViewJobsList);
        jobsListView.setAdapter(adapter);

        Button firstPageButton = (Button)findViewById(R.id.buttonJobsFirst);
        Button prevPageButton = (Button)findViewById(R.id.buttonJobsPrev);
        Button nextPageButton = (Button)findViewById(R.id.buttonJobsNext);
        Button lastPageButton = (Button) findViewById(R.id.buttonJobsLast);

        firstPageButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                if (mCurrentPage > 1)
                {
                    mCurrentPage = 1;
                    updateJobs();
                }
            }
        });

        prevPageButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                if (mCurrentPage > 1)
                {
                    mCurrentPage--;
                    updateJobs();
                }
            }
        });

        nextPageButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                if (mCurrentPage < mTotalPages)
                {
                    mCurrentPage++;
                    updateJobs();
                }
            }
        });

        lastPageButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                if (mCurrentPage != mTotalPages)
                {
                    mCurrentPage = mTotalPages;
                    updateJobs();
                }
            }
        });

        updateJobs();

        jobsListView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int index, long l)
            {
                BackgroundJobInfoLight jobInfoLight = (BackgroundJobInfoLight)BackgroundJobsActivity.this.adapter.getItem(index);
                String jobId = jobInfoLight.getId();

                Intent intent = new Intent(BackgroundJobsActivity.this, BackgroundJobDetailsActivity.class);
                intent.putExtra(BackgroundJobDetailsActivity.BACKGROUND_JOB_ID_MESSAGE_ID, jobId);

                startActivity(intent);
            }
        });
    }

    private void updateJobs()
    {
        BackgroundJobsRetrieveTask retrieveTask = new BackgroundJobsRetrieveTask();
        retrieveTask.execute();
    }

    private class BackgroundJobsRetrieveTask extends AsyncTask<Void, Void, BackgroundJobInfoLightCollection>
    {
        private Exception exception;
        private ProgressDialog progressDialog;

        @Override
        protected void onPreExecute()
        {
            progressDialog = new ProgressDialog(BackgroundJobsActivity.this);
            progressDialog.setIndeterminate(true);
            progressDialog.setMessage("Jobs retrieving");
            progressDialog.show();
        }

        @Override
        protected void onPostExecute(BackgroundJobInfoLightCollection result)
        {
            progressDialog.hide();

            if (result != null)
            {
                BackgroundJobsActivity.this.adapter.updateData(result);

                currentPageTextView.setText("" + mCurrentPage);
                totalPagesTextView.setText("" + mTotalPages);
            }

            if (exception != null)
            {
                AlertDialog.Builder builder = new AlertDialog.Builder(BackgroundJobsActivity.this);
                builder.setMessage(exception.getMessage()).setTitle("ERROR");
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        }

        @Override
        protected BackgroundJobInfoLightCollection doInBackground(Void... voids)
        {
            BackgroundJobInfoLightCollection result = null;

            try
            {
                BackgroundJobSearchParameters searchParams = new BackgroundJobSearchParameters();
                searchParams.setJobKindFlags(BackgroundJobKindFlags.UserJob);
                searchParams.setJobStatusFlags(JobStatusFlags.All);
                searchParams.setMinStartTime(new Date(0));
                searchParams.setMaxStartTime(new Date());
                searchParams.setSummarySearchMethod(BackgroundJobSearchMethod.SubstringOccurrence);
                searchParams.setAgentIds(new AgentIdsCollection());
                searchParams.setSummarySearchOptions("");
                searchParams.setSummarySearchPattern("");

                if (mAgentId == null)
                {
                    result = retrieveCoreJobs(searchParams);
                }
                else
                {
                    result = retrieveAgentJobs(searchParams);
                }
            }
            catch (Exception ex)
            {
                Log.e("[INVOKER]", ex.getMessage());
                ex.printStackTrace();
                exception = ex;
            }

            return result;
        }

        private BackgroundJobInfoLightCollection retrieveCoreJobs(BackgroundJobSearchParameters searchParams) throws Exception
        {
            BackgroundJobManagement jobsManagement = new BackgroundJobManagement();
            int jobsCount = jobsManagement.getJobsCount(searchParams);
            mTotalPages = jobsCount / 10 + 1;

            BackgroundJobInfoLightCollection result = jobsManagement.getJobsByPage(mCurrentPage, 10, searchParams);

            return result;
        }

        private BackgroundJobInfoLightCollection retrieveAgentJobs(BackgroundJobSearchParameters searchParams) throws Exception
        {
            searchParams.getAgentIds().add(mAgentId);

            BackgroundJobManagement jobsManagement = new BackgroundJobManagement();
            int jobsCount = jobsManagement.getJobsCount(searchParams);
            mTotalPages = jobsCount / 10 + 1;

            BackgroundJobInfoLightCollection result = jobsManagement.getJobsByPage(mCurrentPage, 10, searchParams);

            return result;
        }
    }
}
