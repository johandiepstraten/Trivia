package com.example.johan.trivia;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements QuestionsRequest.Callback {
    ArrayList<Question> questions;

//    Request questions and answers
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        QuestionsRequest question = new QuestionsRequest(this);
        question.getQuestions(this);
    }
//    tell user if questionlist is loaded
    @Override
    public void gotQuestions(ArrayList<Question> questionlist) {
        questions = questionlist;
        Toast.makeText(this, "Started", Toast.LENGTH_SHORT).show();
    }
//    tell user if something went wrong with the question request
    @Override
    public void gotQuestionsError(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }
//    close app if android back button is pressed in this screen
    public void onBackPressed() {
        Intent close = new Intent(Intent.ACTION_MAIN);
        close.addCategory(Intent.CATEGORY_HOME);
        close.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(close);
    }
//    send user to highscores
    public void Highscores(View view) {
        Intent intent = new Intent(MainActivity.this, HighscoreActivity.class);
        startActivity(intent);
    }
//    send user to the QuizActivity, add the list of questions
    public void Play(View view) {
        Intent intent = new Intent(MainActivity.this, QuizActivity.class);
        intent.putExtra("questions", questions);
        startActivity(intent);
    }
}
