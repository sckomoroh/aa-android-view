package com.apprecovery.view.recoverypoints;

import android.app.Activity;
import android.app.ListActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;
import com.apprecovery.AAViewerUI.R;
import com.apprecovery.exchange.ExchangeServerVersion;
import com.apprecovery.exchange.MailStore;
import com.apprecovery.view.recoverypoints.adapter.MailStoresAdapter;

import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: ezvigunov
 * Date: 5/15/13
 * Time: 12:03 PM
 */

public class MailStoresActivity extends Activity
{
    public static final String MAIL_STORES_MESSAGE_ID = "MAIL_STORES_MESSAGE_ID";
    public static final String RECOVERY_POINT_NAME_MESSAGE_ID = "HOST_NAME_ID_MESSAGE";
    public static final String EXCHANGE_VERSION_MESSAGE_ID = "EXCHANGE_VERSION_MESSAGE_ID";

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mail_store);

        ArrayList<MailStore> mailStoreCollection = (ArrayList<MailStore>) getIntent().getSerializableExtra(MAIL_STORES_MESSAGE_ID);
        ExchangeServerVersion exchangeVersion = (ExchangeServerVersion)getIntent().getSerializableExtra(EXCHANGE_VERSION_MESSAGE_ID);

        MailStoresAdapter adapter = new MailStoresAdapter(mailStoreCollection, this);

        ListView listView = (ListView)findViewById(R.id.listViewMailStore);
        listView.setAdapter(adapter);

        TextView rpNameTextView = (TextView)findViewById(R.id.textViewMailStoreVolName);
        rpNameTextView.setText(getIntent().getStringExtra(RECOVERY_POINT_NAME_MESSAGE_ID));

        TextView exchangeVersionTextView = (TextView)findViewById(R.id.mailStoreExchangeVersionTextView);
        exchangeVersionTextView.setText(exchangeVersion.toString());
    }
}
