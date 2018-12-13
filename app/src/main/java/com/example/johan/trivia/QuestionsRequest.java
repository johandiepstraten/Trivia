package com.example.johan.trivia;

import android.content.Context;
import android.util.Log;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

//    get questions from online json file
public class QuestionsRequest implements Response.Listener<JSONObject>, Response.ErrorListener {
    private Context context;
    Callback activity;

//    forward possible error message
    public QuestionsRequest (Context context)  {
        this.context = context;
    }
    @Override
    public void onErrorResponse(VolleyError error) {
        activity.gotQuestionsError(error.getMessage());
    }
    @Override
    public void onResponse(JSONObject response) {
        ArrayList<Question> questionlist = new ArrayList<Question>();

        try {
            JSONArray questions = response.getJSONArray("results");
            for (int i = 0; i < questions.length(); i++) {
                JSONObject current_question = questions.getJSONObject(i);

//                extract all needed values from JsonObject and make Question objects for each question
                String category = current_question.getString("category");
                String question = current_question.getString("question");
                String correct_answer = current_question.getString("correct_answer");
                ArrayList<String> wrong_answers = new ArrayList<String>();

//                extract all wrong answers
                JSONArray answers = current_question.getJSONArray("incorrect_answers");
                for (int j = 0; j < answers.length(); j++) {
                    String wrong_answer = answers.getString(j);
                    wrong_answers.add(wrong_answer);
                }
                questionlist.add(new Question(category, question, correct_answer, wrong_answers));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
//        send list to callback
        activity.gotQuestions(questionlist);
    }
//    Send result of request back to MainActivity
    public interface Callback {
        void gotQuestions(ArrayList<Question> questionlist);
        void gotQuestionsError(String message);
    }
//    get jsonObject from right url
    void getQuestions(Callback activity)   {
        RequestQueue queue = Volley.newRequestQueue(context);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest("https://opentdb.com/api.php?amount=10&category=17&difficulty=hard&type=multiple", null, this, this);
        queue.add(jsonObjectRequest);
        this.activity = activity;
    }
}
