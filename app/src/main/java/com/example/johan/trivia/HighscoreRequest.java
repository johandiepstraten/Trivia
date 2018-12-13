package com.example.johan.trivia;

import android.content.Context;
import android.util.Log;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

//    get scores of all players from online json file
public class HighscoreRequest implements Response.Listener<JSONArray>, Response.ErrorListener {

    private Context context;
    Callback activity;

    public HighscoreRequest (Context context)  {
        this.context = context;
    }
    @Override

//    forward possible error message
    public void onErrorResponse(VolleyError error) {
        activity.gotHighscoreError(error.getMessage());
    }
    @Override
    public void onResponse(JSONArray response) {
        ArrayList<Score> scorelist = new ArrayList<Score>();

//        extract all needed values from JsonObject and make Score objects for each player
        try {
            for (int i = 0; i < response.length(); i++) {
                JSONObject current_score = response.getJSONObject(i);
                String name = current_score.getString("Name");
                String scorestring = current_score.getString("Score");
                int score = Integer.parseInt(scorestring);
                long time = current_score.getLong("Time");
                scorelist.add(new Score(name, score, time, 0));
                Log.d("response loop", time + name + score);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
//        send list to callback
        activity.gotHighscore(scorelist);
    }
//    Send result of request back to HighscoreActivity
    public interface Callback {
        void gotHighscore(ArrayList<Score> scorelist);
        void gotHighscoreError(String message);
    }
//    get jsonarray from right url
    void getHighscores(Callback activity)   {
        RequestQueue queue = Volley.newRequestQueue(context);
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest("https://ide50-johadiep.cs50.io:8080/list", this, this);
        queue.add(jsonArrayRequest);
        this.activity = activity;
    }
}
