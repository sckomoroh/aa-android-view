package com.apprecovery.view.repositories.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.apprecovery.AAViewerUI.R;
import com.apprecovery.repository.RepositoryFileInfo;
import com.apprecovery.repository.RepositorySummaryInfo;
import com.apprecovery.repository.RepositorySummaryInfoCollection;

/**
 * Created by sckomoroh on 14.10.2014.
 */
public class RepositoriesAdapter extends BaseExpandableListAdapter
{
    private Context context;
    private RepositorySummaryInfoCollection repositories = new RepositorySummaryInfoCollection();

    public RepositoriesAdapter(Context context)
    {
        this.context = context;
    }

    public void updateData(RepositorySummaryInfoCollection data)
    {
        repositories = data;
        notifyDataSetChanged();
    }

    @Override
    public int getGroupCount()
    {
        return repositories.size();
    }

    @Override
    public int getChildrenCount(int index)
    {
        RepositorySummaryInfo repository = repositories.get(index);
        return repository.getFiles().size();
    }

    @Override
    public Object getGroup(int index)
    {
        return repositories.get(index);
    }

    @Override
    public Object getChild(int groupIndex, int index)
    {
        return repositories.get(groupIndex).getFiles().get(index);
    }

    @Override
    public long getGroupId(int i)
    {
        return i;
    }

    @Override
    public long getChildId(int i, int i2)
    {
        return i * 256 + i2;
    }

    @Override
    public boolean hasStableIds()
    {
        return false;
    }

    @Override
    public View getGroupView(int groupIndex, boolean b, View view, ViewGroup viewGroup)
    {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = inflater.inflate(R.layout.repository_group_item, null);

        RepositorySummaryInfo repo = repositories.get(groupIndex);

        TextView nameView = (TextView)view.findViewById(R.id.textViewRepoGroupItemName);
        ProgressBar progressView = (ProgressBar)view.findViewById(R.id.progressBarRepoGroupItem);
        ImageView stateImage = (ImageView)view.findViewById(R.id.imageViewRepoGroupItem);

        nameView.setText(repo.getSpec().getName());

        int usage = 100 - (int)(((double)repo.getFree() / (double)repo.getSize()) * 100.0);
        progressView.setProgress(usage);
        //usingView.setText(String.format("Free %4.2f %%", usage));

        switch(repo.getStatus())
        {
            case Mounted:
                stateImage.setImageResource(R.drawable.green);
                break;
            case Mounting:
            case Unmounting:
                stateImage.setImageResource(R.drawable.orange);
                break;
            default:
                stateImage.setImageResource(R.drawable.red);
        }

        return view;
    }

    @Override
    public View getChildView(int groupIndex, int index, boolean b, View view, ViewGroup viewGroup)
    {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = inflater.inflate(R.layout.repository_item, null);

        RepositorySummaryInfo repo = repositories.get(groupIndex);
        RepositoryFileInfo repoFileInfo = repo.getFiles().get(index);

        TextView fileName = (TextView)view.findViewById(R.id.textViewRepoItemName);
        fileName.setText(repoFileInfo.getSpecification().getDataPath());

        return view;
    }

    @Override
    public boolean isChildSelectable(int i, int i2)
    {
        return false;
    }
}
