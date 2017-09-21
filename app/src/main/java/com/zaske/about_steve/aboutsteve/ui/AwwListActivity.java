package com.zaske.about_steve.aboutsteve.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.zaske.about_steve.aboutsteve.R;
import com.zaske.about_steve.aboutsteve.adapters.AwwRecyclerViewAdapter;
import com.zaske.about_steve.aboutsteve.adapters.HatedListAdapter;
import com.zaske.about_steve.aboutsteve.models.Aww;
import com.zaske.about_steve.aboutsteve.services.RedditService;
import com.zaske.about_steve.aboutsteve.services.STZDataService;

import java.io.IOException;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class AwwListActivity extends AppCompatActivity {
    public static final String TAG = AwwListActivity.class.getSimpleName();
    public ArrayList<Aww> mAwws = new ArrayList<>();

    private AwwRecyclerViewAdapter mAdapter;

    @BindView(R.id.awwRecyclerView)
    RecyclerView mAwwRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aww_list);
        ButterKnife.bind(this);
        getAwwd();
    }

    private void getAwwd(){
        final RedditService awwService = new RedditService();
        awwService.getPage(new Callback() {

            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) {
                mAwws = awwService.processAwwResults(response);
                Log.d(TAG, "onResponse: " + mAwws);

                AwwListActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mAdapter = new AwwRecyclerViewAdapter(getApplicationContext(),mAwws);
                        mAwwRecyclerView.setAdapter(mAdapter);
                        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(AwwListActivity.this);
                        mAwwRecyclerView.setLayoutManager(layoutManager);
                        mAwwRecyclerView.setHasFixedSize(true);
                    }
                });


            } // end of onResponse
        });
    } //end of getHatedStuff method
}
