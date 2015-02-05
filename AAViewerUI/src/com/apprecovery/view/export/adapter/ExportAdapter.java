package com.apprecovery.view.export.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.apprecovery.AAViewerUI.R;
import com.apprecovery.export.AgentExportConfiguration;
import com.apprecovery.export.AgentExportConfigurationCollection;

/**
 * Created by administrator on 2/5/2015.
 */
public class ExportAdapter extends BaseAdapter
{
    private AgentExportConfigurationCollection mExports = new AgentExportConfigurationCollection();
    private Context mContext;

    public ExportAdapter(Context context)
    {
        mContext = context;
    }

    public void updateData(AgentExportConfigurationCollection data)
    {
        mExports.clear();

        for(AgentExportConfiguration agentExport : data)
        {
            if (agentExport.isIsAllowed())
            {
                mExports.add(agentExport);
            }
        }

        notifyDataSetChanged();
    }

    @Override
    public int getCount()
    {
        return mExports.size();
    }

    @Override
    public Object getItem(int position)
    {
        return mExports.get(position);
    }

    @Override
    public long getItemId(int position)
    {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent)
    {
        LayoutInflater inflater = (LayoutInflater) this.mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = inflater.inflate(R.layout.export_item, null);

        AgentExportConfiguration export = mExports.get(position);

        TextView textView = (TextView)view.findViewById(R.id.exportNameTextView);
        textView.setText(export.getAgentDisplayName());

        ImageView stateImageView = (ImageView)view.findViewById(R.id.exportStateImageView);

        if (export.isIsEnabled())
        {
            stateImageView.setImageResource(R.drawable.green);
        }
        else
        {
            stateImageView.setImageResource(R.drawable.orange);
        }

        return view;
    }
}
