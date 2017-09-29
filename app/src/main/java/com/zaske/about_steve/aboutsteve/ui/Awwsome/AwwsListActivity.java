package com.zaske.about_steve.aboutsteve.ui.Awwsome;

import android.content.res.Configuration;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.zaske.about_steve.aboutsteve.R;
import com.zaske.about_steve.aboutsteve.adapters.AwwsRecyclerAdapter;
import com.zaske.about_steve.aboutsteve.util.AwwsRequester;
import com.zaske.about_steve.aboutsteve.util.AwwsRequester.AwwsRequesterResponse;
import com.zaske.about_steve.aboutsteve.models.Aww;

import java.io.IOException;
import java.util.ArrayList;

public class AwwsListActivity extends AppCompatActivity implements AwwsRequesterResponse {
    // My variables
    private RecyclerView mRecyclerView; // To connect to my view object
    private LinearLayoutManager mLinearLayoutManager; // This tracks what views are where in the Rview
    private ArrayList<Aww> mAwwsList; // What's being tracked
    private AwwsRequester mAwwRequester; //helper class
    private AwwsRecyclerAdapter mAdapter; //The 'data source' for the recyclerview
    private GridLayoutManager mGridLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_awws_list);

        //  Attaching the Rview and assigning a layoutManager
        mRecyclerView = (RecyclerView) findViewById(R.id.awwsRecyclerView);

        // Adjust grid according to orientation
        if(this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT){
            mGridLayoutManager = new GridLayoutManager(this, 3);
        }
        else{
            mGridLayoutManager = new GridLayoutManager(this, 4);
        }

        mRecyclerView.setLayoutManager(mGridLayoutManager);

        mAwwsList = new ArrayList<>(); // An empty arrayList for the items in your list
        mAdapter = new AwwsRecyclerAdapter(mAwwsList);
        mRecyclerView.setAdapter(mAdapter); //this attaches your empty list to the view
        setRecyclerViewScrollListener(); // This sets the scroll listener

        //This is the object that can fetch more content
        mAwwRequester = new AwwsRequester(this);
    }

    @Override
    protected void onStart() {
        super.onStart();

        // Better get some content
        if (mAwwsList.size() == 0) {
            requestMoreAwws();
        }
    }

    // This method asks the layoutManager what's the last visible position
    private int getLastVisibleItemPosition() {
        return mGridLayoutManager.findLastVisibleItemPosition();
    }

    // This creates a Scroll Listener to the RecyclerView
    private void setRecyclerViewScrollListener() {
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                int totalItemCount = mRecyclerView.getLayoutManager().getItemCount();

                Log.d("Scrolled", "TotalItemCount:" + totalItemCount + "LastVisItemPos:" + getLastVisibleItemPosition()  );
                if (!mAwwRequester.isLoadingData() && getLastVisibleItemPosition() > totalItemCount - 6) {
                    requestMoreAwws();
                }
            }
        });
    }

    private void requestMoreAwws() {

        try {
            mAwwRequester.getAwws();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void receivedNewAwws(final ArrayList<Aww> Awws) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {

                mAwwsList.addAll(Awws); // This adds a new item to the list
                mAdapter.notifyItemInserted(mAwwsList.size());  //This tells the adapter to reset and redraw
            }
        });
    }
}
