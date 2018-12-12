package com.example.johan.trivia;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class PostHelper implements Response.ErrorListener, Response.Listener {

    String name;
    int score;
    long time;
    CallbackPost activity;

    public PostHelper(String name, int score, long time, Context context, CallbackPost cb)    {
        this.name = name;
        this.time = time;
        this.score = score;

        this.activity = cb;
        RequestQueue queue = Volley.newRequestQueue(context); // hier gaat iets fout
        PostRequest request = new PostRequest(Request.Method.POST, "https://ide50-johadiep.cs50.io:8080/list", this, this);
        queue.add(request);
        Log.d("isditiets", "" + name + time + score);


    }

    @Override
    public void onErrorResponse(VolleyError error) {
        error.printStackTrace();
        activity.gotHelperError(error.getMessage());
    }

    @Override
    public void onResponse(Object response) {
        activity.gotHelper("Succes!");
    }
    public interface CallbackPost {
        void gotHelper(String message);
        void gotHelperError(String message);
    }
    public class PostRequest extends StringRequest {

        // Constructor
        public PostRequest(int method, String url, Response.Listener<String> listener, Response.ErrorListener errorListener) {
            super(method, url, listener, errorListener);
        }


        // Method to supply parameters to the request
        @Override
        protected Map<String, String> getParams() {

            Map<String, String> params = new HashMap<>();
            params.put("Name", name);
            params.put("Score", Integer.toString(score));
            params.put("Time", Long.toString(time));
            return params;
//            Log.d("gebeurt dit10", "help");
        }
    }
}
