package com.zaske.about_steve.aboutsteve.util;

import android.app.Activity;
import android.content.Context;
import android.util.Log;

import com.zaske.about_steve.aboutsteve.models.Aww;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Guest on 9/21/17.
 */

public class AwwsRequester {

    public interface AwwsRequesterResponse {
        void receivedNewAwws(ArrayList<Aww> Awws);
    }

    private AwwsRequesterResponse mResponseListener;
    private Context mContext;
    private OkHttpClient mClient;
    private static final String BASE_URL = "http://www.reddit.com/r/aww/hot.json?";
    private static final String COUNT_PARAMETER = "count=";
    private static final String COUNT_VALUE = "25";
    private static final String AFTER_PARAMETER = "&after=";
    private boolean mLoadingData;

    //Set to blank and Reddit works fine
    private String mLastAwwId = "";

    public boolean isLoadingData() {
        return mLoadingData;
    }

    public AwwsRequester(Activity listeningActivity) {
        mResponseListener = (AwwsRequesterResponse) listeningActivity;
        mContext = listeningActivity.getApplicationContext();
        mClient = new OkHttpClient();
        mLoadingData = false;
    }

    public void getAwws() throws IOException {


        String urlRequest = BASE_URL + COUNT_PARAMETER + COUNT_VALUE + AFTER_PARAMETER + getLastAwwId();
        Request request = new Request.Builder().url(urlRequest).build();
        mLoadingData = true;

        mClient.newCall(request).enqueue(new Callback() {

            @Override
            public void onFailure(Call call, IOException e) {
                mLoadingData = false;
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                // An Array of Awws
                ArrayList<Aww> Awws = new ArrayList<>();
                try {
                    //JSONObject photoJSON = new JSONObject(response.body().string());


                    JSONObject tmdbJSON = new JSONObject(response.body().string());
                    Log.d("JSON Object is", "onResponse: ");

                    JSONArray awwsJSON = tmdbJSON.getJSONObject("data").getJSONArray("children");
                    for(int i = 0; i < awwsJSON.length(); i++){
                        JSONObject thisAwwParent = awwsJSON.getJSONObject(i);

                        //collect information about this specific thing
                        String kind = thisAwwParent.getString("kind");

                        JSONObject awwData = thisAwwParent.getJSONObject("data");
                        String title = awwData.getString("title");
                        String id = awwData.getString("id");
                        String thumb = awwData.getString("thumbnail");
                        String url = correctGIFVs(awwData.getString("url"));

                        //TO DO Speed this up
                        //
                        // Set the last seen Aww in the list
                        setLastAwwId(kind+"_"+id);

                        if(isContentNotAd(awwData.getString("domain")) && isNotJunkURL(url)){
                            Awws.add(new Aww(kind, id, title, thumb, url));
                        }
                    }

                    // Has the last known Aww by kept in LastAwwId?
                    Log.d("mLastAwwId = ", getLastAwwId());

                    //This is where the new Awws are passed back to the Activity
                    mResponseListener.receivedNewAwws(Awws);
                    mLoadingData = false;

                } catch (JSONException e) {
                    mLoadingData = false;
                    e.printStackTrace();
                }
            }
        });
    }

    public void setLastAwwId(String id){ this.mLastAwwId = id; }

    public String getLastAwwId(){ return mLastAwwId; }

    public boolean isContentNotAd(String domain){
        if(domain.equals("reddit.com")){
            return false;
        }
        return true;
    }

    public boolean isNotJunkURL(String url) {
        return url.endsWith(".jpg") || url.endsWith(".png") || url.endsWith(".gif") || url.endsWith(".mp4");
    }

    public String correctGIFVs(String url){
        if (url.endsWith(".gifv")){
            return url.replace(".gifv",".mp4");
        } else {
            return url;
        }
    }
}
