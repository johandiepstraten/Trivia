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
    //    Set adapter to view certain values of menus of chosen category in listview
    public View getView(int position, View listView, ViewGroup parent) {
        if (listView == null) {
            listView = LayoutInflater.from(getContext()).inflate(R.layout.score_row, parent, false);
        }

        Log.d("gebeurt dit?", scores + "");

        Score currentscore = scores.get(position);
        String name = currentscore.getName();
        Log.d("gebeurt dit2", "" + name);
        int score = currentscore.getScore();
        Log.d("gebeurt dit4", "" + score);
        long time = currentscore.getTime();
        ((TextView) listView.findViewById(R.id.scorename)).setText(name);
        Log.d("gebeurt dit5", "" + score);
        ((TextView) listView.findViewById(R.id.scorescore)).setText(String.valueOf(score));
        Log.d("gebeurt dit3", "" + time);
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
