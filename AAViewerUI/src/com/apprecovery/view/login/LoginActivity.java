package com.apprecovery.view.login;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.apprecovery.AAViewerUI.R;
import com.apprecovery.config.AACoreConfig;
import com.apprecovery.export.AgentExportConfigurationCollection;
import com.apprecovery.export.ExportManagement;
import com.apprecovery.metadata.CoreSummaryMetadata;
import com.apprecovery.metadata.MetadataManagement;
import com.apprecovery.view.corehome.CoreHomeActivity;
import com.serialization.XmlDeserializer;

public class LoginActivity
        extends Activity
{
    public final static String HOST_NAME_ID_MESSAGE = "HOST_NAME_ID_MESSAGE";
    public final static String OS_VERSION_ID_MESSAGE = "OS_VERSION_ID_MESSAGE";
    public final static String OS_ARCH_ID_MESSAGE = "OS_ARCH_ID_MESSAGE";

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        initContractClasses();

        final TextView hostTextView = (TextView)findViewById(R.id.hostEditText);
        final TextView userTextView = (TextView)findViewById(R.id.userNameEditText);
        final TextView passwordTextView = (TextView)findViewById(R.id.passwordEditText);

        final Button loginButton = (Button) findViewById(R.id.loginButton);
        loginButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                AACoreConfig.getInstance().setHost(hostTextView.getText().toString());
                AACoreConfig.getInstance().setPassword(passwordTextView.getText().toString());
                AACoreConfig.getInstance().setUserName(userTextView.getText().toString());

                SummaryMetadataTask task = new SummaryMetadataTask((TextView)findViewById(R.id.loginErrorTextView));
                task.execute();
            }
        });
    }

    private void initContractClasses()
    {
        try
        {
            // background jobs
            XmlDeserializer.addClass("com.apprecovery.backgroundjobs.ServerError");
            XmlDeserializer.addClass("com.apprecovery.backgroundjobs.BackgroundJobInfoCollection");
            XmlDeserializer.addClass("com.apprecovery.backgroundjobs.BackgroundJobInfo");
            XmlDeserializer.addClass("com.apprecovery.backgroundjobs.BackgroundJobInfoLightCollection");
            XmlDeserializer.addClass("com.apprecovery.backgroundjobs.BackgroundJobInfoLight");

            // events
            XmlDeserializer.addClass("com.apprecovery.events.EventSummariesCollection");
            XmlDeserializer.addClass("com.apprecovery.events.EventSummary");

            // repository
            XmlDeserializer.addClass("com.apprecovery.repository.RepositoryFileInfo");
            XmlDeserializer.addClass("com.apprecovery.repository.RepositoryFileInfoCollection");
            XmlDeserializer.addClass("com.apprecovery.repository.RepositoryFileSpecification");
            XmlDeserializer.addClass("com.apprecovery.repository.RepositorySpecification");
            XmlDeserializer.addClass("com.apprecovery.repository.RepositorySummaryInfo");
            XmlDeserializer.addClass("com.apprecovery.repository.RepositorySummaryInfoCollection");

            // metadata
            XmlDeserializer.addClass("com.apprecovery.metadata.CoreSummaryMetadata");

            // agents
            XmlDeserializer.addClass("com.apprecovery.agents.AgentInfoSummary");
            XmlDeserializer.addClass("com.apprecovery.agents.AgentInfoSummaryCollection");

            // recovery points
            XmlDeserializer.addClass("com.apprecovery.recoverypoints.RecoveryPointSummaryInfo");
            XmlDeserializer.addClass("com.apprecovery.recoverypoints.RecoveryPointSummaryInfoCollection");
            XmlDeserializer.addClass("com.apprecovery.recoverypoints.RecoveryPointsResponse");
            XmlDeserializer.addClass("com.apprecovery.recoverypoints.RecoveryPointSummaryInfoCollection2");

            // volume images
            XmlDeserializer.addClass("com.apprecovery.volumeimage.VolumeImageSummary");
            XmlDeserializer.addClass("com.apprecovery.volumeimage.VolumeImageSummaryCollection");
            XmlDeserializer.addClass("com.apprecovery.volumeimage.VolumeImageComponents");

            // sql
            XmlDeserializer.addClass("com.apprecovery.sql.SqlServer");
            XmlDeserializer.addClass("com.apprecovery.sql.SqlInstance");
            XmlDeserializer.addClass("com.apprecovery.sql.SqlInstanceCollection");
            XmlDeserializer.addClass("com.apprecovery.sql.Database");
            XmlDeserializer.addClass("com.apprecovery.sql.DatabaseCollection");
            XmlDeserializer.addClass("com.apprecovery.sql.DatabaseDataFile");
            XmlDeserializer.addClass("com.apprecovery.sql.DatabaseDataFileCollection");
            XmlDeserializer.addClass("com.apprecovery.sql.DatabaseFileGroup");
            XmlDeserializer.addClass("com.apprecovery.sql.DatabaseFileGroupCollection");
            XmlDeserializer.addClass("com.apprecovery.sql.DatabaseLogFile");
            XmlDeserializer.addClass("com.apprecovery.sql.DatabaseLogFileCollection");

            // exchange
            XmlDeserializer.addClass("com.apprecovery.exchange.ExchangeServer");
            XmlDeserializer.addClass("com.apprecovery.exchange.MailStore");
            XmlDeserializer.addClass("com.apprecovery.exchange.MailStoreCollection");
            XmlDeserializer.addClass("com.apprecovery.exchange.LegacyMailStore");
            XmlDeserializer.addClass("com.apprecovery.exchange.LegacyMailStoreCollection");
            XmlDeserializer.addClass("com.apprecovery.exchange.LegacyStorageGroup");
            XmlDeserializer.addClass("com.apprecovery.exchange.LegacyStorageGroupCollection");

            // database check
            XmlDeserializer.addClass("com.apprecovery.databasecheck.CheckResult");

            // export
            XmlDeserializer.addClass("com.apprecovery.export.AgentExportConfiguration");
            XmlDeserializer.addClass("com.apprecovery.export.AgentExportConfigurationCollection");
            XmlDeserializer.addClass("com.apprecovery.export.VirtualMachineLocation");
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }

    private class SummaryMetadataTask extends AsyncTask<Void, Void, CoreSummaryMetadata>
    {
        private Exception exception = null;
        private TextView errorTextView;
        private ProgressDialog progressDialog;

        public SummaryMetadataTask(TextView errorTextView)
        {
            this.errorTextView = errorTextView;

            progressDialog = new ProgressDialog(LoginActivity.this);
            progressDialog.setMessage("Signing in...");
            progressDialog.setIndeterminate(true);
        }

        @Override
        protected void onPreExecute()
        {
            progressDialog.show();
        }

        @Override
        protected CoreSummaryMetadata doInBackground(Void... voids)
        {
            CoreSummaryMetadata coreSummaryMetadata = null;

            try
            {
                MetadataManagement metadataManagement = new MetadataManagement();
                coreSummaryMetadata = metadataManagement.getSummaryMetadata();
            }
            catch (Exception ex)
            {
                exception = ex;
            }

            return coreSummaryMetadata;
        }

        @Override
        protected void onPostExecute(CoreSummaryMetadata result)
        {
            progressDialog.hide();
            if (exception != null)
            {
                errorTextView.setText(exception.getMessage());
            }
            else
            {
                errorTextView.setText("");
                Intent intent = new Intent(LoginActivity.this, CoreHomeActivity.class);

                intent.putExtra(HOST_NAME_ID_MESSAGE, result.getHostName());
                intent.putExtra(OS_VERSION_ID_MESSAGE, result.getOsVersion().toString());
                intent.putExtra(OS_ARCH_ID_MESSAGE, result.getOsArchitecture().toString());
                startActivity(intent);
            }
        }
    }
}
