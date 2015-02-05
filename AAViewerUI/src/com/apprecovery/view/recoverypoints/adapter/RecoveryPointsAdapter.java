package com.apprecovery.view.recoverypoints.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import com.apprecovery.AAViewerUI.R;
import com.apprecovery.recoverypoints.RecoveryPointSummaryInfo;
import com.apprecovery.recoverypoints.RecoveryPointSummaryInfoCollection;
import com.apprecovery.volumeimage.VolumeImageSummary;
import com.apprecovery.volumeimage.VolumeImageSummaryCollection;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 * User: sckomoroh
 * Date: 5/12/13
 * Time: 9:29 AM
 */

public class RecoveryPointsAdapter
        extends BaseExpandableListAdapter
{
    private RecoveryPointSummaryInfoCollection recoveryPoints;
    private Context context;

    public RecoveryPointsAdapter(Context context)
    {
        this.context = context;
    }

    public void updateData(RecoveryPointSummaryInfoCollection recoveryPoints)
    {
        this.recoveryPoints = recoveryPoints;
        notifyDataSetChanged();
    }

    private String getRecoveryPointContent(RecoveryPointSummaryInfo recoveryPointSummaryInfo)
    {
        String result = "";
        VolumeImageSummaryCollection volumeImageSummaries = recoveryPointSummaryInfo.getVolumeImages();

        for (VolumeImageSummary volumeImageSummary : volumeImageSummaries)
        {
            result += volumeImageSummary.getVolumeDisplayName() + ", ";
        }

        if (!result.isEmpty())
        {
            result = result.substring(0, result.length() - 2);
        }

        return result;
    }

    @Override
    public int getGroupCount()
    {
        if (recoveryPoints == null)
        {
            return 0;
        }

        return recoveryPoints.size();
    }

    @Override
    public int getChildrenCount(int i)
    {
        return recoveryPoints.get(i).getVolumeImages().size();
    }

    @Override
    public Object getGroup(int i)
    {
        return recoveryPoints.get(i);
    }

    @Override
    public Object getChild(int i, int i2)
    {
        return recoveryPoints.get(i).getVolumeImages().get(i2);
    }

    @Override
    public long getGroupId(int i)
    {
        return i;
    }

    @Override
    public long getChildId(int i, int i2)
    {
        return i * 1024 + i2;
    }

    @Override
    public boolean hasStableIds()
    {
        return true;
    }

    @Override
    public View getGroupView(int i, boolean b, View view, ViewGroup viewGroup)
    {
        RecoveryPointSummaryInfo recoveryPointSummaryInfo = recoveryPoints.get(i);

        LayoutInflater inflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = inflater.inflate(R.layout.recovery_point_item, null);

        TextView nameTextView = (TextView) view.findViewById(R.id.recoveryPointNameTextView);
        nameTextView.setText(getRecoveryPointContent(recoveryPointSummaryInfo));

        DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm");
        String date = df.format(recoveryPointSummaryInfo.getTimeStamp());

        TextView timeTextView = (TextView) view.findViewById(R.id.recoveryPointTimeTextView);
        timeTextView.setText(date);

        TextView typeTextView = (TextView) view.findViewById(R.id.recoveryPointTypeTextView);
        setRecoveryPointType(typeTextView, recoveryPointSummaryInfo.getStatus());

        ImageView imageView = (ImageView) view.findViewById(R.id.recoveryPointStateImageView);

        setRecoveryPointState(recoveryPointSummaryInfo.getVolumeImages(), imageView);

        return view;
    }

    @Override
    public View getChildView(int i, int i2, boolean b, View view, ViewGroup viewGroup)
    {
        VolumeImageSummary volumeImageSummary = recoveryPoints.get(i).getVolumeImages().get(i2);

        LayoutInflater inflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = inflater.inflate(R.layout.volume_item, null);

        TextView nameTextView = (TextView) view.findViewById(R.id.volumeNameTextView);
        nameTextView.setText(volumeImageSummary.getVolumeDisplayName());

        TextView sizeTextView = (TextView) view.findViewById(R.id.volumeSizeTypeTextView);
        sizeTextView.setText(getVolumeSize(volumeImageSummary.getSize()));

        ImageView imageView = (ImageView) view.findViewById(R.id.volumeStateImageView);
        setStateVolume(volumeImageSummary, imageView);

        return view;
    }

    @Override
    public boolean isChildSelectable(int i, int i2)
    {
        VolumeImageSummary volumeImageSummary = recoveryPoints.get(i).getVolumeImages().get(i2);

        if (volumeImageSummary.isHasExchangeMetadata()
                && volumeImageSummary.getVolumeImageComponents().getExchangeServer().getMailStores() != null)
        {
            return true;
        }

        return true;
    }

    private void setRecoveryPointType(TextView typeTextView, String state)
    {
        typeTextView.setText(state);
    }

    private void setRecoveryPointState(VolumeImageSummaryCollection volumeImageSummaries, ImageView imageView)
    {
        boolean isGray = true;

        for (VolumeImageSummary volumeImageSummary : volumeImageSummaries)
        {
            int volumeImageState = volumeImageSummary.getVolumeImageState();
            if (volumeImageState == VolumeImageSummary.RED)
            {
                imageView.setImageResource(R.drawable.red);
                return;
            }

            if (volumeImageState == VolumeImageSummary.GREEN)
            {
                isGray = false;
            }
            else if (volumeImageState != VolumeImageSummary.GRAY)
            {
                imageView.setImageResource(R.drawable.orange);
                return;
            }
        }

        if (!isGray)
        {
            imageView.setImageResource(R.drawable.green);
        }
    }

    private void setStateVolume(VolumeImageSummary volumeImageSummary, ImageView imageView)
    {
        switch (volumeImageSummary.getVolumeImageState())
        {
        case VolumeImageSummary.GREEN:
            imageView.setImageResource(R.drawable.green);
            break;
        case VolumeImageSummary.RED:
            imageView.setImageResource(R.drawable.red);
            break;
        case VolumeImageSummary.YELLOW:
            imageView.setImageResource(R.drawable.orange);
            break;
        }
    }

    private String getVolumeSize(long size)
    {
        int pow = 0;
        long value = size;

        while (value > 1024)
        {
            value /= 1024;
            pow++;
        }

        String result = "" + value;

        switch (pow)
        {
            case 1:
                result += "KB";
                break;
            case 2:
                result += "MB";
                break;
            case 3:
                result += "GB";
                break;
            case 4:
                result += "TB";
                break;
        }

        return result;
    }
}
