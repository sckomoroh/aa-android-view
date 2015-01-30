package com.apprecovery.view.recoverypoints.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.TextView;
import com.apprecovery.AAViewerUI.R;
import com.apprecovery.exchange.LegacyStorageGroup;
import com.apprecovery.exchange.LegacyStorageGroupCollection;
import com.apprecovery.volumeimage.VolumeImageSummary;

/**
 * Created with IntelliJ IDEA.
 * User: ezvigunov
 * Date: 5/16/13
 * Time: 2:41 PM
 * To change this template use File | Settings | File Templates.
 */
public class LegacyGroupAdapter extends BaseAdapter
{
    private Context context;
    private LegacyStorageGroupCollection groups;

    public LegacyGroupAdapter(LegacyStorageGroupCollection groups, Context context)
    {
        this.groups = groups;
        this.context = context;
    }

    @Override
    public int getCount()
    {
        return groups.size();
    }

    @Override
    public Object getItem(int i)
    {
        return groups.get(i);
    }

    @Override
    public long getItemId(int i)
    {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup)
    {
        LegacyStorageGroup legacyStorageGroup = groups.get(i);
        LayoutInflater inflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = inflater.inflate(R.layout.legacy_group_item, null);

        TextView logPath = (TextView)view.findViewById(R.id.legacyGroupLogPathTextView);
        TextView systemPath = (TextView) view.findViewById(R.id.legacyGroupSystemPathTextView);
        TextView groupName = (TextView) view.findViewById(R.id.legacyGroupNameTextView);
        ImageView state = (ImageView) view.findViewById(R.id.legacyGroupStateImageView);

        logPath.setText("Log path: " + legacyStorageGroup.getLogFilePath());
        systemPath.setText("System path: " + legacyStorageGroup.getSystemPath());
        groupName.setText(legacyStorageGroup.getName());

        if (legacyStorageGroup.getState() == VolumeImageSummary.RED)
        {
            state.setImageResource(R.drawable.red);
        }
        else if (legacyStorageGroup.getState() == VolumeImageSummary.GREEN)
        {
            state.setImageResource(R.drawable.green);
        }
        else
        {
            state.setImageResource(R.drawable.orange);
        }

        return view;
    }
}
