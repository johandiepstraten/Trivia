package com.example.johan.trivia;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class ResultActivity extends AppCompatActivity implements PostHelper.CallbackPost {
    int score;
    long time;
    Context context;

//    if submit was succesfull send user to HighscoreActivity and enable submit button
    @Override
    public void gotHelper(String message) {
        startActivity(new Intent(ResultActivity.this, HighscoreActivity.class));
        Button disable_button = findViewById(R.id.Submit);
        disable_button.setEnabled(true);
    }
//    show user possible error message
    @Override
    public void gotHelperError(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();

    }
//    get score and time and show to user
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        Bundle values = getIntent().getExtras();
        score = values.getInt("score");
        time = values.getLong("time");
        ((TextView) findViewById(R.id.TotalScore)).setText("Score: " + score);
        int millis = (int) (time % 1000);
        int seconds = (int) (time / 1000);
        int minutes = seconds / 60;
        seconds = seconds % 60;
        ((TextView) findViewById(R.id.TotalTime)).setText(String.format("Time: %d:%02d:%03d", minutes, seconds, millis));
    }
//    get name of user if submit is pressed. send it to the json file and disable submit button
//    so that user cannot request a submit multiple times while waiting for the request to finish
    public void Submit(View view) {
        String name = ((EditText)findViewById(R.id.InputName)).getText().toString();
        if(name.length() > 0)   {
            PostHelper helper = new PostHelper(name, score, time, getApplicationContext(), ResultActivity.this);//hier gaat iets fout
            Button disable_button = findViewById(R.id.Submit);
            disable_button.setEnabled(false);
        }
    }
//     send user to MainActivity if back button is pressed
    public void onBackPressed() {
        startActivity(new Intent(ResultActivity.this, MainActivity.class));
    }
}
