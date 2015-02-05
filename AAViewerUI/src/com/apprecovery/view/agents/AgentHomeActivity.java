package com.apprecovery.view.agents;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.apprecovery.AAViewerUI.R;
import com.apprecovery.view.corehome.CoreHomeActivity;
import com.apprecovery.view.events.EventsActivity;
import com.apprecovery.view.recoverypoints.RecoveryPointsActivity;

/**
 * Created with IntelliJ IDEA.
 * User: ezvigunov
 * Date: 9/24/13
 * Time: 7:44 PM
 */
public class AgentHomeActivity extends Activity
{
    private String mAgentID;
    private String mAgentDisplayName;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.agent_home);

        mAgentID = getIntent().getStringExtra(AgentsActivity.AGENT_ID_MESSAGE_ID);
        mAgentDisplayName = getIntent().getStringExtra(AgentsActivity.AGENT_DISPLAY_NAME_MESSAGE_ID);

        TextView agentHostNameTextView = (TextView)findViewById(R.id.textViewAgentHomeHost);
        agentHostNameTextView.setText(mAgentDisplayName);

        Button recoveryPointsButton = (Button)findViewById(R.id.buttonAgentHomeRP);
        Button agentEventsButton = (Button) findViewById(R.id.buttonAgentHomeEvents);

        recoveryPointsButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(AgentHomeActivity.this, RecoveryPointsActivity.class);
                intent.putExtra(AgentsActivity.AGENT_ID_MESSAGE_ID, mAgentID);
                intent.putExtra(AgentsActivity.AGENT_DISPLAY_NAME_MESSAGE_ID, mAgentDisplayName);
                startActivity(intent);
            }
        });

        agentEventsButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(AgentHomeActivity.this, EventsActivity.class);
                intent.putExtra(EventsActivity.HOST_NAME_MESSAGE_ID, mAgentDisplayName);
                intent.putExtra(EventsActivity.AGENT_ID_MESSAGE_ID, mAgentID);
                startActivity(intent);
            }
        });
    }
}

