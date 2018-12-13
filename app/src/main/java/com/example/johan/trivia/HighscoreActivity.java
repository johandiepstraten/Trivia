package com.example.johan.trivia;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class HighscoreActivity extends AppCompatActivity implements HighscoreRequest.Callback {
    ArrayList<Score> scores;
    @Override

//    Request online Json file with scores
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_highscore);
        HighscoreRequest highscores = new HighscoreRequest(this);
        highscores.getHighscores(this);
    }

    @Override
    public void gotHighscore(ArrayList<Score> scorelist) {
        scores = scorelist;

//        sort list from highest to lowest score
        ArrayList<Score> sortedscores = new ArrayList<Score>();
        Collections.sort(scores, new Comparator<Score>() {
            public int compare(Score c1, Score c2) {
                if (c1.getScore() > c2.getScore()) return -1;
                if (c1.getScore() < c2.getScore()) return 1;
                return 0;
            }});
//        Sort list per socore from fastest to slowest
        for (int i = 10; i>0; i--)    {
            ArrayList<Score> tempscores = new ArrayList<Score>();
            for (int j = 0; j<scores.size(); j++)    {
                if(scores.get(j).getScore() == i) {
                    tempscores.add(scores.get(j));
                }
            }
            Collections.sort(tempscores, new Comparator<Score>() {
                public int compare(Score c1, Score c2) {
                    if (c1.getTime() < c2.getTime()) return -1;
                    if (c1.getTime() > c2.getTime()) return 1;
                    return 0;
                }});
            for (int k = 0; k<tempscores.size(); k++)    {
                sortedscores.add(tempscores.get(k));
            }
        }
//        Set ranking for each score in the list
        for (int l = 0; l<sortedscores.size(); l++) {
            Score rankscore = sortedscores.get(l);
            rankscore.setRanking(l+1);
        }
//        Set adapter to view highscores.
        HighscoreAdapter adapter = new HighscoreAdapter(this, R.layout.score_row, sortedscores);
        ListView listView = findViewById(R.id.HighScores);
        listView.setAdapter(adapter);
    }
//      Show possible errors
    @Override
    public void gotHighscoreError(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }
//    Send user back to main screen if back button is pressed
    public void onBackPressed() {
        startActivity(new Intent(HighscoreActivity.this, MainActivity.class));
    }
}
