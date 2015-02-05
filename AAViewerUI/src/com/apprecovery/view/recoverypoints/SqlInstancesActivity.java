package com.apprecovery.view.recoverypoints;

import android.app.ListActivity;
import android.os.Bundle;
import com.apprecovery.sql.SqlInstance;
import com.apprecovery.sql.SqlInstanceCollection;
import com.apprecovery.view.recoverypoints.adapter.SqlInstanceAdapter;

import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: ezvigunov
 * Date: 5/16/13
 * Time: 6:10 PM
 */
public class SqlInstancesActivity extends ListActivity
{
    public static final String SQL_INSTANCE_MESSAGE_ID = "SQL_INSTANCE_MESSAGE_ID";

    private SqlInstanceAdapter adapter;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        ArrayList<SqlInstance> sqlInstances = (ArrayList<SqlInstance>) getIntent().getSerializableExtra(SQL_INSTANCE_MESSAGE_ID);
        SqlInstanceCollection sqlInstanceCollection = new SqlInstanceCollection();
        sqlInstanceCollection.addAll(sqlInstances);

        adapter = new SqlInstanceAdapter(sqlInstanceCollection, this);

        setListAdapter(adapter);
    }
}
