package com.apprecovery.view.backgroundjobs.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.apprecovery.AAViewerUI.R;
import com.apprecovery.backgroundjobs.BackgroundJobInfoLight;
import com.apprecovery.backgroundjobs.BackgroundJobInfoLightCollection;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 * Created by sckomoroh on 12.10.2014.
 */
public class BackgroundJobsAdapter extends BaseAdapter
{
    private Context context;
    private BackgroundJobInfoLightCollection jobsLight = new BackgroundJobInfoLightCollection();

    public BackgroundJobsAdapter(Context context)
    {
        this.context = context;
    }

    public void updateData(BackgroundJobInfoLightCollection jobsLight)
    {
        this.jobsLight = jobsLight;
        notifyDataSetChanged();
    }

    @Override
    public int getCount()
    {
        return jobsLight.size();
    }

    @Override
    public Object getItem(int index)
    {
        return jobsLight.get(index);
    }

    @Override
    public long getItemId(int index)
    {
        return index;
    }

    @Override
    public View getView(int index, View view, ViewGroup viewGroup)
    {
        LayoutInflater inflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = inflater.inflate(R.layout.background_job_item, null);

        BackgroundJobInfoLight job = jobsLight.get(index);

        ImageView statusImageView = (ImageView)view.findViewById(R.id.imageViewJobItemIcon);
        TextView summaryTextView = (TextView)view.findViewById(R.id.textViewJobItemSummary);
        TextView startTimeTextView = (TextView)view.findViewById(R.id.textViewJobItemStart);
        TextView endTimeTextView = (TextView)view.findViewById(R.id.textViewJobItemEnd);

        DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm");
        String creationTime = df.format(job.getCreationTime());
        String endTime = df.format(job.getEndTime());

        summaryTextView.setText(job.getSummary());
        startTimeTextView.setText(creationTime);
        endTimeTextView.setText(endTime);

        switch(job.getStatus())
        {
            case Succeeded:
                statusImageView.setImageResource(R.drawable.success_event);
                break;
            case Failed:
                statusImageView.setImageResource(R.drawable.error_event);
                break;
        }

        return view;
    }
}
