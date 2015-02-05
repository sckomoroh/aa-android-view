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
import com.apprecovery.exchange.MailStore;

import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: ezvigunov
 * Date: 5/15/13
 * Time: 12:26 PM
 */

public class MailStoresAdapter extends BaseAdapter
{
    private Context context;
    private ArrayList<MailStore> mailStoreCollection;

    public MailStoresAdapter(ArrayList<MailStore> mailStoreCollection, Context context)
    {
        this.mailStoreCollection = mailStoreCollection;
        this.context = context;
    }

    @Override
    public int getCount()
    {
        return mailStoreCollection.size();
    }

    @Override
    public Object getItem(int i)
    {
        return mailStoreCollection.get(i);
    }

    @Override
    public long getItemId(int i)
    {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup)
    {
        MailStore mailStore = mailStoreCollection.get(i);

        LayoutInflater inflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = inflater.inflate(R.layout.mail_store_item, null);

        TextView databaseName = (TextView)view.findViewById(R.id.mailStoreNameTextView);
        TextView databasePath = (TextView)view.findViewById(R.id.mailStoreDatabasePathTextView);
        TextView databaseSystemPath = (TextView)view.findViewById(R.id.mailStoreSystemPathTextView);
        TextView databaseLogPath = (TextView)view.findViewById(R.id.mailStoreLogPathTextView);

        databaseName.setText(mailStore.getName());
        databasePath.setText("Database path: " + mailStore.getExchangeDatabasePath());
        databaseSystemPath.setText("System path: " + mailStore.getSystemPath());
        databaseLogPath.setText("Log path: " + mailStore.getLogFilePath());

        ImageView stateImageView = (ImageView)view.findViewById(R.id.mailStoreStateImageView);

        if ((mailStore.getCheckResults().getCheckResults() & CheckFlags.MountCheckPassed) != 0 || (mailStore.getCheckResults().getCheckResults() & CheckFlags.ChecksumCheckPassed) != 0)
        {
            stateImageView.setImageResource(R.drawable.green);
        }
        else if ((mailStore.getCheckResults().getCheckResults() & CheckFlags.MountCheckFailed) != 0)
        {
            stateImageView.setImageResource(R.drawable.red);
        }
        else
        {
            stateImageView.setImageResource(R.drawable.orange);
        }

        return view;
    }
}
