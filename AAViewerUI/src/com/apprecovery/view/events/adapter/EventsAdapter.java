package com.apprecovery.view.events.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.apprecovery.AAViewerUI.R;
import com.apprecovery.events.EventLevel;
import com.apprecovery.events.EventSummariesCollection;
import com.apprecovery.events.EventSummary;

/**
 * Created with IntelliJ IDEA.
 * User: ezvigunov
 * Date: 9/25/13
 * Time: 2:33 AM
 */

public class EventsAdapter extends BaseAdapter
{
    private EventSummariesCollection mEvents = new EventSummariesCollection();
    private Context mContext;

    public EventsAdapter(Context context)
    {
        mContext = context;
    }

    public void updateData(EventSummariesCollection data)
    {
        mEvents = data;
        notifyDataSetChanged();
    }

    @Override
    public int getCount()
    {
        return mEvents.size();
    }

    @Override
    public Object getItem(int index)
    {
        return mEvents.get(index);
    }

    @Override
    public long getItemId(int i)
    {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup)
    {
        LayoutInflater inflater = (LayoutInflater) this.mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = inflater.inflate(R.layout.event_item, null);

        EventSummary eventSummary = mEvents.get(i);

        TextView textView = (TextView)view.findViewById(R.id.eventContentTextView);
        textView.setText(eventSummary.getMessage());

        TextView timeTextView = (TextView)view.findViewById(R.id.eventTimeTextView);
        timeTextView.setText(eventSummary.getTimeStamp());

        ImageView levelImageView = (ImageView)view.findViewById(R.id.eventLevelImageView);

        EventLevel eventLevel = eventSummary.getLevel();
        switch(eventLevel)
        {
            case Error:
            case Fatal:
                levelImageView.setImageResource(R.drawable.error_event);
                break;
            case Info:
            case Debug:
                levelImageView.setImageResource(R.drawable.info_event);
                break;
           case Warning:
               levelImageView.setImageResource(R.drawable.warning_event);
        }

        return view;
    }
}
