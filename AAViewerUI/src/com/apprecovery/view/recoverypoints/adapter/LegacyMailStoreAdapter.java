package com.apprecovery.view.recoverypoints.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.apprecovery.AAViewerUI.R;
import com.apprecovery.databasecheck.CheckFlags;
import com.apprecovery.exchange.LegacyMailStore;
import com.apprecovery.exchange.LegacyMailStoreCollection;
import com.apprecovery.volumeimage.VolumeImageSummary;

/**
 * Created with IntelliJ IDEA.
 * User: ezvigunov
 * Date: 5/16/13
 * Time: 2:13 PM
 * To change this template use File | Settings | File Templates.
 */
public class LegacyMailStoreAdapter extends BaseAdapter
{
    private LegacyMailStoreCollection legacyMailStores;
    private Context context;

    public LegacyMailStoreAdapter(LegacyMailStoreCollection legacyMailStores, Context context)
    {
        this.legacyMailStores = legacyMailStores;
        this.context = context;
    }

    @Override
    public int getCount()
    {
        return legacyMailStores.size();
    }

    @Override
    public Object getItem(int i)
    {
        return legacyMailStores.get(i);
    }

    @Override
    public long getItemId(int i)
    {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup)
    {
        LegacyMailStore legacyMailStore = legacyMailStores.get(i);

        LayoutInflater inflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = inflater.inflate(R.layout.legacy_mail_store_item, null);

        ImageView imageView = (ImageView) view.findViewById(R.id.legacyStoreStateImageView);
        TextView databaseNameTextView = (TextView) view.findViewById(R.id.legacyDatabaseNameTextView);
        TextView databasePathTextView = (TextView) view.findViewById(R.id.legacyDatabasePathTextView);

        databaseNameTextView.setText(legacyMailStore.getName());
        databasePathTextView.setText("Database path: " + legacyMailStore.getExchangeDatabasePath());

        if ((legacyMailStore.getCheckResults().getCheckResults() & CheckFlags.MountCheckFailed) != 0)
        {
            imageView.setImageResource(R.drawable.red);
        }
        else if ((legacyMailStore.getCheckResults().getCheckResults() & CheckFlags.MountCheckPassed) != 0 || (legacyMailStore.getCheckResults().getCheckResults() & CheckFlags.ChecksumCheckPassed) != 0)
        {
            imageView.setImageResource(R.drawable.green);
        }
        else
        {
            imageView.setImageResource(R.drawable.orange);
        }

        return view;
    }
}
