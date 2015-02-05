package com.apprecovery.view.export;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import com.apprecovery.AAViewerUI.R;
import com.apprecovery.export.AgentExportConfigurationCollection;
import com.apprecovery.export.ExportManagement;
import com.apprecovery.view.corehome.CoreHomeActivity;
import com.apprecovery.view.export.adapter.ExportAdapter;

/**
 * Created by administrator
 * 2/5/2015.
 */
public class ExportActivity extends Activity
{
    private ExportAdapter adapter;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.exports);

        adapter = new ExportAdapter(this);

        ListView listView = (ListView)findViewById(R.id.listViewExports);
        listView.setAdapter(adapter);

        TextView hostNameTextView = (TextView) findViewById(R.id.textViewExportsHostName);
        hostNameTextView.setText(getIntent().getStringExtra(CoreHomeActivity.HOST_NAME_ID_MESSAGE));

        ExportsRetrieveTask retrieveTask = new ExportsRetrieveTask();
        retrieveTask.execute();
    }

    private class ExportsRetrieveTask extends AsyncTask<Void, Void, AgentExportConfigurationCollection>
    {
        private Exception exception;
        private ProgressDialog progressDialog;

        @Override
        protected AgentExportConfigurationCollection doInBackground(Void... voids)
        {
            AgentExportConfigurationCollection result = null;

            try
            {
                ExportManagement agentManagement = new ExportManagement();
                result = agentManagement.getAllAgentsExportConfiguration();
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
            progressDialog = new ProgressDialog(ExportActivity.this);
            progressDialog.setIndeterminate(true);
            progressDialog.setMessage("Exports retrieving");
            progressDialog.show();
        }

        @Override
        protected void onPostExecute(AgentExportConfigurationCollection result)
        {
            progressDialog.hide();

            if (result != null)
            {
                ExportActivity.this.adapter.updateData(result);
            }

            if (exception != null)
            {
                AlertDialog.Builder builder = new AlertDialog.Builder(ExportActivity.this);
                builder.setMessage(exception.getMessage()).setTitle("ERROR");
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        }
    }
}
