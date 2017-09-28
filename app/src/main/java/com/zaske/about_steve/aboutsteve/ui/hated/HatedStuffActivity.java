package com.zaske.about_steve.aboutsteve.ui.hated;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ViewGroup;

import com.zaske.about_steve.aboutsteve.R;
import com.zaske.about_steve.aboutsteve.adapters.HatedListAdapter;
import com.zaske.about_steve.aboutsteve.models.Stuff;
import com.zaske.about_steve.aboutsteve.services.STZDataService;

import java.io.IOException;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class HatedStuffActivity extends AppCompatActivity {
    public ArrayList<Stuff> mHated = new ArrayList<>();
    private HatedListAdapter hateAdapter;
    @BindView(R.id.hateViewPager) ViewPager mHatedViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hated_stuff);
        ButterKnife.bind(this);
        this.setTitle(getString(R.string.hate_Activity_Title));
        getHatedStuff();
    }

    private void getHatedStuff(){
        final STZDataService stzService = new STZDataService();
        stzService.getHatedStuff(new Callback() {

            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) {
                mHated = stzService.processHatedStuffResults(response);

                HatedStuffActivity.this.runOnUiThread(new Runnable() {

                    @Override
                    public void run() {
                        hateAdapter = new HatedListAdapter(getSupportFragmentManager(),mHated);
                        mHatedViewPager.setAdapter(hateAdapter);
                        mHatedViewPager.setPersistentDrawingCache(ViewGroup.PERSISTENT_SCROLLING_CACHE);
                        mHatedViewPager.setDrawingCacheEnabled(true);
                        mHatedViewPager.setDrawingCacheQuality(mHatedViewPager.DRAWING_CACHE_QUALITY_HIGH);
                        mHatedViewPager.setCurrentItem(0);

                    }
                });  //end of runnable

            } // end of onResponse
        });
    } //end of getHatedStuff method
}
