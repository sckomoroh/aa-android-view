package com.apprecovery.view.agents.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.apprecovery.AAViewerUI.R;
import com.apprecovery.agents.AgentInfoSummaryCollection;

/**
 * Created with IntelliJ IDEA.
 * User: sckomoroh
 * Date: 5/12/13
 * Time: 9:29 AM
 */

public class AgentsAdapter extends BaseAdapter
{
    private AgentInfoSummaryCollection agents;
    private Context context;

    public AgentsAdapter(Context context)
    {
        this.context = context;
    }

    public void updateData(AgentInfoSummaryCollection agents)
    {
        this.agents = agents;
        notifyDataSetChanged();
    }

    @Override
    public int getCount()
    {
        if (agents == null)
        {
            return 0;
        }

        return agents.size();
    }

    @Override
    public Object getItem(int index)
    {
        return agents.get(index);
    }

    @Override
    public long getItemId(int i)
    {
        return i;
    }

    @Override
    public View getView(int index, View view, ViewGroup viewGroup)
    {
        LayoutInflater inflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = inflater.inflate(R.layout.agent_item, null);

        TextView textView = (TextView) view.findViewById(R.id.agentNameTextView);
        textView.setText(agents.get(index).getDisplayName());

        return view;
    }
}
