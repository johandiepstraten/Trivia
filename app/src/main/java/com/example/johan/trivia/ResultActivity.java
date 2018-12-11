package com.example.johan.trivia;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class ResultActivity extends AppCompatActivity {
    int score;
    long time;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        Bundle values = getIntent().getExtras();
        int score = values.getInt("score");
        long time = values.getLong("time");
        ((TextView) findViewById(R.id.TotalScore)).setText("Score: " + score);
        int seconds = (int) (time / 1000);
        int minutes = seconds / 60;
        seconds = seconds % 60;
        ((TextView) findViewById(R.id.TotalTime)).setText(String.format("%d:%02d", minutes, seconds));
    }

    public void Submit(View view) {
        String name = ((EditText)findViewById(R.id.InputName)).getText().toString();
        if(name.length() > 0)   {
//            Sent score, time and name to highscores
//            sent user to highscore interface
        }

    }
    public void onBackPressed() {
        startActivity(new Intent(ResultActivity.this, MainActivity.class));
    }
}
