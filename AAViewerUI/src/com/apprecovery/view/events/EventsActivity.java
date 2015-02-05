package com.apprecovery.view.events;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import com.apprecovery.AAViewerUI.R;
import com.apprecovery.agents.AgentInfoSummaryCollection;
import com.apprecovery.agents.AgentManagement;
import com.apprecovery.events.EventSummariesCollection;
import com.apprecovery.events.EventsManagement;
import com.apprecovery.view.events.adapter.EventsAdapter;

/**
 * Created with IntelliJ IDEA.
 * User: ezvigunov
 * Date: 9/25/13
 * Time: 1:16 AM
 */

public class EventsActivity extends Activity
{
    public final static String HOST_NAME_MESSAGE_ID = "HOST_NAME_ID_MESSAGE";
    public final static String AGENT_ID_MESSAGE_ID = "AGENT_ID_MESSAGE_ID";

    private TextView currentPage;
    private TextView lastPage;

    private int mCurrentPage = 1;
    private int mTotalPages = 0;

    private EventsAdapter adapter;
    private String mAgentId;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.events);

        currentPage = (TextView) findViewById(R.id.textViewEventsCurPage);
        lastPage = (TextView) findViewById(R.id.textViewEventsLastPage);
        TextView coreHostName = (TextView) findViewById(R.id.textViewEventsHostName);

        coreHostName.setText(getIntent().getStringExtra(HOST_NAME_MESSAGE_ID));

        mAgentId = getIntent().getStringExtra(AGENT_ID_MESSAGE_ID);

        if (mAgentId != null && !mAgentId.isEmpty())
        {
            TextView eventsTypeTextView = (TextView)findViewById(R.id.textViewEventsType);
            eventsTypeTextView.setText("agent");

        }

        Button firstPage = (Button) findViewById(R.id.buttonEventsFirst);
        Button prevPage = (Button) findViewById(R.id.buttonEventsPrev);
        Button nextPage = (Button) findViewById(R.id.buttonEventsNext);
        Button lastPage = (Button) findViewById(R.id.buttonEventLast);

        firstPage.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                if (mCurrentPage != 1)
                {
                    mCurrentPage = 1;
                    updateEventsList();
                }
            }
        });

        prevPage.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                if (mCurrentPage > 1)
                {
                    mCurrentPage--;
                    updateEventsList();
                }
            }
        });

        nextPage.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                if (mCurrentPage < mTotalPages)
                {
                    mCurrentPage++;
                    updateEventsList();
                }
            }
        });

        lastPage.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                if (mCurrentPage != mTotalPages)
                {
                    mCurrentPage = mTotalPages;
                    updateEventsList();
                }
            }
        });

        adapter = new EventsAdapter(this);

        ListView listView = (ListView) findViewById(R.id.listViewEventsList);
        listView.setAdapter(adapter);

        updateEventsList();
    }

    private void updateEventsList()
    {
        EventsRetrieveTask task = new EventsRetrieveTask();
        task.execute();
    }

    private class EventsRetrieveTask extends AsyncTask<Void, Void, EventSummariesCollection>
    {
        private Exception exception;
        private ProgressDialog progressDialog;

        @Override
        protected EventSummariesCollection doInBackground(Void... voids)
        {
            EventSummariesCollection result = null;

            try
            {
                EventsManagement eventsManagement = new EventsManagement();

                if (mAgentId == null)
                {
                    EventsActivity.this.mTotalPages = (int) (eventsManagement.getCoreEventCount() / 10 + 1);
                    result = eventsManagement.getCoreEventsByPage(10, mCurrentPage);
                }
                else
                {
                    EventsActivity.this.mTotalPages = (int) (eventsManagement.getAgentEventCount(mAgentId) / 10 + 1);
                    result = eventsManagement.getAgentEventsByPage(mAgentId, 10, mCurrentPage);
                }
            }
            catch (Exception ex)
            {
                ex.printStackTrace();
                exception = ex;
            }

            return result;
        }

        @Override
        protected void onPreExecute()
        {
            progressDialog = new ProgressDialog(EventsActivity.this);
            progressDialog.setIndeterminate(true);
            progressDialog.setMessage("Events retrieving");
            progressDialog.show();
        }

        @Override
        protected void onPostExecute(EventSummariesCollection result)
        {
            progressDialog.hide();

            if (result != null)
            {
                EventsActivity.this.adapter.updateData(result);

                currentPage.setText("" + mCurrentPage);
                lastPage.setText("" + mTotalPages);
            }

            if (exception != null)
            {
                AlertDialog.Builder builder = new AlertDialog.Builder(EventsActivity.this);
                builder.setMessage(exception.getMessage()).setTitle("ERROR");
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        }
    }
}
