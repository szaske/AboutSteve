package com.zaske.about_steve.aboutsteve.services;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import com.zaske.about_steve.aboutsteve.models.Aww;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.DataInput;
import java.io.IOException;
import java.util.ArrayList;

import static com.zaske.about_steve.aboutsteve.Constants.REDDIT_SERVICE_BASE_URL;

public class RedditService extends Service {

    public RedditService() {
    }

    public static void getPage(Callback callback){

        OkHttpClient client = new OkHttpClient();

        //Build the request
        Request request= new Request.Builder()
                .url(REDDIT_SERVICE_BASE_URL)
                .build();

        //make the call asynchronously
        Call call = client.newCall(request);
        call.enqueue(callback);
    }

    public ArrayList<Aww> processAwwResults(Response response){
        ArrayList<Aww> Awws = new ArrayList<>();
        // ObjectMapper mapper = new ObjectMapper();

        try {
            String jsonData = response.body().string();
            Log.d("Zaske: ","processAwwResults: " + jsonData);
            if(response.isSuccessful()){

                JSONObject tmdbJSON = new JSONObject(jsonData);
                JSONArray awwsJSON = tmdbJSON.getJSONObject("data").getJSONArray("children");
                for(int i = 0; i < awwsJSON.length(); i++){
                    JSONObject thisAwwParent = awwsJSON.getJSONObject(i);

                    //collect information about this specific thing
                    String kind = thisAwwParent.getString("kind");

                    JSONObject awwData = thisAwwParent.getJSONObject("data");
                    String title = awwData.getString("title");
                    String id = awwData.getString("id");
                    String thumb = awwData.getString("thumbnail");
                    String url = awwData.getString("url");

                    Log.d("JSON print ", "processAwwResults: " + kind + "-" + title + url);
                    //add the thing to the Awws list
                    // Aww(String mKind, String mId, String mTitle, String mThumbnail, String mUrl)
                    Aww tempAww = new Aww(kind, id, title, thumb, url);
                    Awws.add(tempAww);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return Awws;
    } //end of processHatedStuffResults

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
