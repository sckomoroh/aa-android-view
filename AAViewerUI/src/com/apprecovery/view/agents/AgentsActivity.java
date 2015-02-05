package com.apprecovery.view.agents;

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
import com.apprecovery.agents.AgentInfoSummary;
import com.apprecovery.agents.AgentInfoSummaryCollection;
import com.apprecovery.agents.AgentManagement;
import com.apprecovery.view.agents.adapter.AgentsAdapter;
import com.apprecovery.view.corehome.CoreHomeActivity;
import com.apprecovery.view.login.LoginActivity;
import com.apprecovery.view.recoverypoints.RecoveryPointsActivity;

/**
 * Created with IntelliJ IDEA.
 * User: sckomoroh
 * Date: 5/12/13
 * Time: 9:28 AM
 */

public class AgentsActivity
        extends Activity
{
    public final static String AGENT_ID_MESSAGE_ID = "AGENT_ID_MESSAGE";
    public final static String AGENT_DISPLAY_NAME_MESSAGE_ID = "AGENT_DISPLAY_NAME_MESSAGE_ID";

    private AgentsAdapter adapter;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.agents);

        adapter = new AgentsAdapter(this);

        ListView listView = (ListView)findViewById(R.id.agentListView);
        listView.setAdapter(adapter);

        TextView hostNameTextView = (TextView) findViewById(R.id.textViewAgentsCoreName);
        hostNameTextView.setText(getIntent().getStringExtra(CoreHomeActivity.HOST_NAME_ID_MESSAGE));

        AgentRetrieveTask retrieveTask = new AgentRetrieveTask();
        retrieveTask.execute();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l)
            {
                AgentInfoSummary agentInfoSummary = (AgentInfoSummary)adapter.getItem(i);

                //Intent intent = new Intent(AgentsActivity.this, RecoveryPointsActivity.class);
                Intent intent = new Intent(AgentsActivity.this, AgentHomeActivity.class);
                intent.putExtra(AGENT_ID_MESSAGE_ID, agentInfoSummary.getId());
                intent.putExtra(AGENT_DISPLAY_NAME_MESSAGE_ID, agentInfoSummary.getDisplayName());
                startActivity(intent);
            }
        });
    }

    private class AgentRetrieveTask extends AsyncTask<Void, Void, AgentInfoSummaryCollection>
    {
        private Exception exception;
        private ProgressDialog progressDialog;

        @Override
        protected AgentInfoSummaryCollection doInBackground(Void... voids)
        {
            AgentInfoSummaryCollection result = null;

            try
            {
                AgentManagement agentManagement = new AgentManagement();
                result = agentManagement.getAgentInfoSummaries();
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
            progressDialog = new ProgressDialog(AgentsActivity.this);
            progressDialog.setIndeterminate(true);
            progressDialog.setMessage("Agent retrieving");
            progressDialog.show();
        }

        @Override
        protected void onPostExecute(AgentInfoSummaryCollection result)
        {
            progressDialog.hide();

            if (result != null)
            {
                AgentsActivity.this.adapter.updateData(result);
            }

            if (exception != null)
            {
                AlertDialog.Builder builder = new AlertDialog.Builder(AgentsActivity.this);
                builder.setMessage(exception.getMessage()).setTitle("ERROR");
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        }
    }
}
