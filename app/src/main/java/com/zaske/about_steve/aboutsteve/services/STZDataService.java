package com.zaske.about_steve.aboutsteve.services;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import com.zaske.about_steve.aboutsteve.models.Stuff;

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

public class STZDataService extends Service {
    public STZDataService() {
    }

    public static void getHatedStuff(Callback callback){
        String STZDATASERVICE_BASE_URL = "https://api-test-c0f4f.firebaseio.com/.json";

        OkHttpClient client = new OkHttpClient();

        //Build the request
        Request request= new Request.Builder()
                .url(STZDATASERVICE_BASE_URL)
                .build();

        //make the call asynchronously
        Call call = client.newCall(request);
        call.enqueue(callback);
    }

    public ArrayList<Stuff> processHatedStuffResults(Response response){
        ArrayList<Stuff> hated = new ArrayList<>();

        try {
            String jsonData = response.body().string();
            if(response.isSuccessful()){
                JSONObject tmdbJSON = new JSONObject(jsonData);
                JSONArray hatedsJSON = tmdbJSON.getJSONArray("hated_stuff");
                for(int i = 0; i < hatedsJSON.length(); i++){
                    JSONObject thisHated = hatedsJSON.getJSONObject(i);

                    //collect information about this specific thing
                    String name = thisHated.getString("name");
                    String imgUrl = thisHated.getString("img_url");
                    String cause = thisHated.getString("cause");
                    int id = thisHated.getInt("id");
                    int hateLevel = thisHated.getInt("level");
                    String link = thisHated.getString("link");
                    boolean cure = thisHated.getBoolean("cure");

                    //add the thing to the hated list
                    Stuff tempHated = new Stuff(id, name, cause, imgUrl, hateLevel, link, cure);
                    hated.add(tempHated);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return hated;
    } //end of processGenreResults


    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
