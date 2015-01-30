package com.apprecovery.view.recoverypoints.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.apprecovery.AAViewerUI.R;
import com.apprecovery.sql.SqlInstance;
import com.apprecovery.sql.SqlInstanceCollection;
import com.apprecovery.volumeimage.VolumeImageSummary;

/**
 * Created with IntelliJ IDEA.
 * User: ezvigunov
 * Date: 5/16/13
 * Time: 6:14 PM
 * To change this template use File | Settings | File Templates.
 */
public class SqlInstanceAdapter extends BaseAdapter
{
    private Context context;
    private SqlInstanceCollection sqlInstances;

    public SqlInstanceAdapter(SqlInstanceCollection sqlInstances, Context context)
    {
        this.context = context;
        this.sqlInstances = sqlInstances;
    }

    @Override
    public int getCount()
    {
        return sqlInstances.size();
    }

    @Override
    public Object getItem(int i)
    {
        return sqlInstances.get(i);
    }

    @Override
    public long getItemId(int i)
    {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup)
    {
        SqlInstance sqlInstance = sqlInstances.get(i);

        LayoutInflater inflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = inflater.inflate(R.layout.sql_instance_item, null);

        ImageView instanceState = (ImageView) view.findViewById(R.id.sqlInstanceStateImageView);
        TextView instanceName = (TextView) view.findViewById(R.id.sqlInstanceNameTextView);
        TextView instanceVersion = (TextView) view.findViewById(R.id.sqlInstanceVersionTextView);

        instanceName.setText(sqlInstance.getName());
        instanceVersion.setText(sqlInstance.getVersion());

        switch (sqlInstance.getDatabases().getState())
        {
            case VolumeImageSummary.GREEN :
                instanceState.setImageResource(R.drawable.green);
                break;
            case VolumeImageSummary.RED :
                instanceState.setImageResource(R.drawable.red);
                break;
            case VolumeImageSummary.YELLOW :
                instanceState.setImageResource(R.drawable.orange);
                break;
        }

        return view;
    }
}
