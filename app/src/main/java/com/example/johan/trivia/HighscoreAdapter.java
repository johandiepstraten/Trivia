package com.example.johan.trivia;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class HighscoreAdapter extends ArrayAdapter<Score> {

    private ArrayList<Score> scores;
    private Context context;

    //  Initiate adapter
    public HighscoreAdapter(Context context, int resource, ArrayList<Score> scores) {
        super(context, resource, scores);
        this.scores = scores;
        this.context = context;
    }
    //    Set adapter to view ranking, name, score and time of each player
    public View getView(int position, View listView, ViewGroup parent) {
        if (listView == null) {
            listView = LayoutInflater.from(getContext()).inflate(R.layout.score_row, parent, false);
        }
        Score currentscore = scores.get(position);
        String name = currentscore.getName();
        int score = currentscore.getScore();
        long time = currentscore.getTime();
        ((TextView) listView.findViewById(R.id.scorename)).setText(name);
        ((TextView) listView.findViewById(R.id.scorescore)).setText(String.valueOf(score));
        int millis = (int) (time % 1000);
        int seconds = (int) (time / 1000);
        int minutes = seconds / 60;
        seconds = seconds % 60;
        ((TextView) listView.findViewById(R.id.scoretime)).setText(String.format("%d:%02d:%03d", minutes, seconds, millis));
        int ranking = currentscore.getRanking();
        ((TextView) listView.findViewById(R.id.scorenumber)).setText(String.valueOf(ranking));

        return listView;
    }
}
