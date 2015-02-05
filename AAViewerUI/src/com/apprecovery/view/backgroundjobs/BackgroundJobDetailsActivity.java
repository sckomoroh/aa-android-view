package com.apprecovery.view.backgroundjobs;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import com.apprecovery.AAViewerUI.R;
import com.apprecovery.backgroundjobs.BackgroundJobInfo;
import com.apprecovery.backgroundjobs.BackgroundJobManagement;
import com.apprecovery.view.backgroundjobs.adapter.BackgroundChildJobsAdapter;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 * Created by sckomoroh on 13.10.2014.
 *
 */
public class BackgroundJobDetailsActivity extends Activity
{
    public static final String BACKGROUND_JOB_ID_MESSAGE_ID = "BACKGROUND_JOB_ID_MESSAGE_ID";
    public static final String BACKGROUND_JOB_MESSAGE_ID = "BACKGROUND_JOB_MESSAGE_ID";

    private TextView summaryTextView;
    private TextView statusTextView;
    private TextView startTimeTextView;
    private TextView endTimeTextView;
    private TextView errorDetailsTextView;
    private TextView errorMessageTextView;
    private BackgroundChildJobsAdapter adapter;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.backgroun_job_details);

        summaryTextView = (TextView)findViewById(R.id.textViewJobDetailsSummary);
        statusTextView = (TextView)findViewById(R.id.textViewJobDetailsStatus);
        startTimeTextView = (TextView)findViewById(R.id.textViewJobDetailsStartTime);
        endTimeTextView = (TextView)findViewById(R.id.textViewJobDetailsEndTime);
        errorDetailsTextView = (TextView)findViewById(R.id.textViewJobDetailsErrorDetails);
        errorMessageTextView = (TextView)findViewById(R.id.textViewJobDetailErrorMessage);
        ListView childrenListView = (ListView)findViewById(R.id.listViewJobDetailsChildren);

        adapter = new BackgroundChildJobsAdapter(this);
        childrenListView.setAdapter(adapter);

        String jobId = getIntent().getStringExtra(BACKGROUND_JOB_ID_MESSAGE_ID);
        if (jobId != null)
        {
            BackgroundJobsRetrieveTask task = new BackgroundJobsRetrieveTask(jobId);
            task.execute();
        }
        else
        {
            BackgroundJobInfo jobInfo = (BackgroundJobInfo)getIntent().getSerializableExtra(BACKGROUND_JOB_MESSAGE_ID);

            DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm");

            summaryTextView.setText(jobInfo.getSummary());
            statusTextView.setText(jobInfo.getJobStatus().toString());
            startTimeTextView.setText(df.format(jobInfo.getStartTime()));
            endTimeTextView.setText(df.format(jobInfo.getEndTime()));

            if (jobInfo.getFailureReason() == null)
            {
                errorDetailsTextView.setVisibility(View.GONE);
                errorMessageTextView.setVisibility(View.GONE);
            }
            else
            {
                errorMessageTextView.setText(jobInfo.getFailureReason().getMessage());
                errorDetailsTextView.setText(jobInfo.getFailureReason().getDetails());
            }
        }

        childrenListView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int index, long l)
            {
                BackgroundJobInfo job = (BackgroundJobInfo) adapter.getItem(index);

                Intent intent = new Intent(BackgroundJobDetailsActivity.this, BackgroundJobDetailsActivity.class);
                intent.putExtra(BackgroundJobDetailsActivity.BACKGROUND_JOB_MESSAGE_ID, job);

                startActivity(intent);

            }
        });
    }

    private class BackgroundJobsRetrieveTask extends AsyncTask<Void, Void, BackgroundJobInfo>
    {
        private String jobId;
        private Exception exception;
        private ProgressDialog progressDialog;

        public BackgroundJobsRetrieveTask(String jobId)
        {
            this.jobId = jobId;
        }

        @Override
        protected void onPreExecute()
        {
            progressDialog = new ProgressDialog(BackgroundJobDetailsActivity.this);
            progressDialog.setIndeterminate(true);
            progressDialog.setMessage("Job details retrieving");
            progressDialog.show();
        }

        @Override
        protected void onPostExecute(BackgroundJobInfo result)
        {
            progressDialog.hide();

            if (result != null)
            {
                DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm");

                summaryTextView.setText(result.getSummary());
                statusTextView.setText(result.getJobStatus().toString());
                startTimeTextView.setText(df.format(result.getStartTime()));
                endTimeTextView.setText(df.format(result.getEndTime()));

                if (result.getFailureReason() == null)
                {
                    errorDetailsTextView.setVisibility(View.GONE);
                    errorMessageTextView.setVisibility(View.GONE);
                }
                else
                {
                    errorMessageTextView.setText(result.getFailureReason().getMessage());
                    errorDetailsTextView.setText(result.getFailureReason().getDetails());
                }

                if (result.getChildJobsInfo() != null && !result.getChildJobsInfo().isEmpty())
                {
                    adapter.updateData(result.getChildJobsInfo());
                }
            }

            if (exception != null)
            {
                AlertDialog.Builder builder = new AlertDialog.Builder(BackgroundJobDetailsActivity.this);
                builder.setMessage(exception.getMessage()).setTitle("ERROR");
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        }

        @Override
        protected BackgroundJobInfo doInBackground(Void... voids)
        {
            BackgroundJobInfo result = null;

            try
            {
                BackgroundJobManagement jobsManagement = new BackgroundJobManagement();
                result = jobsManagement.getJob(jobId);

            }
            catch (Exception ex)
            {
                exception = ex;
            }

            return result;
        }
    }
}
