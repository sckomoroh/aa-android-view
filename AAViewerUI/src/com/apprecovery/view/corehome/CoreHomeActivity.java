package com.apprecovery.view.corehome;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.apprecovery.AAViewerUI.R;
import com.apprecovery.view.agents.AgentsActivity;
import com.apprecovery.view.backgroundjobs.BackgroundJobsActivity;
import com.apprecovery.view.events.EventsActivity;
import com.apprecovery.view.login.LoginActivity;
import com.apprecovery.view.repositories.RepositoriesActivity;

/**
 * Created with IntelliJ IDEA.
 * User: sckomoroh
 * Date: 5/12/13
 * Time: 9:28 AM
 */

public class CoreHomeActivity extends Activity
{
    public final static String HOST_NAME_ID_MESSAGE = "HOST_NAME_ID_MESSAGE";

    private String mCoreHostName;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.core_home);

        TextView hostNameTextView = (TextView) findViewById(R.id.textViewHomeHostName);
        TextView osVersionTextView = (TextView) findViewById(R.id.textViewHomeOsVer);
        TextView osArchTextView = (TextView) findViewById(R.id.textViewHomeArch);

        mCoreHostName = getIntent().getStringExtra(LoginActivity.HOST_NAME_ID_MESSAGE);
        hostNameTextView.setText(mCoreHostName);
        osVersionTextView.setText(getIntent().getStringExtra(LoginActivity.OS_VERSION_ID_MESSAGE));
        osArchTextView.setText(getIntent().getStringExtra(LoginActivity.OS_ARCH_ID_MESSAGE));

        Button buttonMachines = (Button)findViewById(R.id.buttonHomeMachines);
        Button buttonRepository = (Button)findViewById(R.id.buttonHomeRepository);
        Button buttonEvents = (Button)findViewById(R.id.buttonHomeEvents);
        Button buttonReplications = (Button)findViewById(R.id.buttonHomeReplication);
        Button buttonVirtualStandby = (Button)findViewById(R.id.buttonHomeVirtualStanby);
        Button buttonBackgroundJobs = (Button)findViewById(R.id.buttonHomeJobs);

        buttonRepository.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(CoreHomeActivity.this, RepositoriesActivity.class);
                intent.putExtra(RepositoriesActivity.CORE_HOST_NAME_MESSAGE_ID, mCoreHostName);

                startActivity(intent);
            }
        });

        buttonMachines.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(CoreHomeActivity.this, AgentsActivity.class);
                intent.putExtra(HOST_NAME_ID_MESSAGE, mCoreHostName);
                startActivity(intent);
            }
        });

        buttonEvents.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(CoreHomeActivity.this, EventsActivity.class);
                intent.putExtra(EventsActivity.HOST_NAME_MESSAGE_ID, mCoreHostName);
                startActivity(intent);
            }
        });

        buttonReplications.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
            }
        });

        buttonVirtualStandby.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
            }
        });

        buttonBackgroundJobs.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(CoreHomeActivity.this, BackgroundJobsActivity.class);
                intent.putExtra(HOST_NAME_ID_MESSAGE, mCoreHostName);
                startActivity(intent);
            }
        });
    }
}
