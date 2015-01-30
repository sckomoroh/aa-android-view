package com.apprecovery.view.recoverypoints;

import android.app.ListActivity;
import android.os.Bundle;
import com.apprecovery.exchange.LegacyMailStore;
import com.apprecovery.exchange.LegacyMailStoreCollection;
import com.apprecovery.view.recoverypoints.adapter.LegacyMailStoreAdapter;

import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: ezvigunov
 * Date: 5/16/13
 * Time: 2:38 PM
 */

public class LegacyMailStoreActivity extends ListActivity
{
    public static final String MAIL_STORE_COLLECTION_MESSAGE_ID = "MAIL_STORE_COLLECTION_MESSAGE_ID";
    private LegacyMailStoreAdapter adapter;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        ArrayList<LegacyMailStore> legacyMailStores = (ArrayList<LegacyMailStore>)getIntent().getSerializableExtra(MAIL_STORE_COLLECTION_MESSAGE_ID);
        LegacyMailStoreCollection legacyMailStoreCollection = new LegacyMailStoreCollection();
        legacyMailStoreCollection.addAll(legacyMailStores);

        adapter = new LegacyMailStoreAdapter(legacyMailStoreCollection, this);
        setListAdapter(adapter);
    }
}

