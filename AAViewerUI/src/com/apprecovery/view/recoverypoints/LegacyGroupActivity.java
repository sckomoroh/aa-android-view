package com.apprecovery.view.recoverypoints;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import com.apprecovery.exchange.LegacyStorageGroup;
import com.apprecovery.exchange.LegacyStorageGroupCollection;
import com.apprecovery.view.recoverypoints.adapter.LegacyGroupAdapter;

import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: ezvigunov
 * Date: 5/16/13
 * Time: 2:38 PM
 */

public class LegacyGroupActivity extends ListActivity
{
    public static final String STORAGE_GROUP_COLLECTION_MESSAGE_ID = "STORAGE_GROUP_COLLECTION_MESSAGE_ID";
    private LegacyGroupAdapter adapter;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        ArrayList<LegacyStorageGroup> legacyStorageGroupArray = (ArrayList<LegacyStorageGroup>) getIntent().getSerializableExtra(STORAGE_GROUP_COLLECTION_MESSAGE_ID);
        LegacyStorageGroupCollection legacyStorageGroupCollection = new LegacyStorageGroupCollection();
        legacyStorageGroupCollection.addAll(legacyStorageGroupArray);

        adapter = new LegacyGroupAdapter(legacyStorageGroupCollection, this);
        setListAdapter(adapter);

        getListView().setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l)
            {
                LegacyStorageGroup legacyStorageGroup = (LegacyStorageGroup)adapter.getItem(i);

                if (legacyStorageGroup.getMailStores() != null)
                {
                    Intent intent = new Intent(LegacyGroupActivity.this, LegacyMailStoreActivity.class);
                    intent.putExtra(LegacyMailStoreActivity.MAIL_STORE_COLLECTION_MESSAGE_ID, legacyStorageGroup.getMailStores());
                    startActivity(intent);
                }
            }
        });
    }
}
