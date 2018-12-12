package com.example.johan.trivia;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;

public class QuizActivity extends AppCompatActivity {
    ArrayList<Question> questions;
    int counter;
    int score;
    String answer;
    long StartTime;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        Intent intent = getIntent();
        questions = (ArrayList<Question>)getIntent().getSerializableExtra("questions");
        Collections.shuffle(questions);
        counter = 0;
        score = 0;
        Question question = questions.get(counter);
        String current_question = question.getQuestion();
        ((TextView) findViewById(R.id.question)).setText(current_question);
        ArrayList<String> answers = question.getAnswers();
        answer = question.getCorrect_answer();
        answers.add(answer);
        Collections.shuffle(answers);
        Button answer1 = (Button) findViewById(R.id.Answer1);
        answer1.setText(answers.get(0));
        Button answer2 = (Button) findViewById(R.id.Answer2);
        answer2.setText(answers.get(1));
        Button answer3 = (Button) findViewById(R.id.Answer3);
        answer3.setText(answers.get(2));
        Button answer4 = (Button) findViewById(R.id.Answer4);
        answer4.setText(answers.get(3));
        StartTime = System.currentTimeMillis();
    }

    public void CheckAnswer(View view) {
        Button button = (Button) view;
        String chosen_answer = button.getText().toString();
        counter += 1;
        if(chosen_answer.equals(answer)) {
            score += 1;
            Toast.makeText(this, "Correct!", Toast.LENGTH_SHORT).show();
        }   else    {
            Toast.makeText(this, "False!", Toast.LENGTH_SHORT).show();
        }
        if(counter<10)  {
            Question question = questions.get(counter);
            String current_question = question.getQuestion();
            ((TextView) findViewById(R.id.question)).setText(current_question);
            ArrayList<String> answers = question.getAnswers();
            answer = question.getCorrect_answer();
            answers.add(answer);
            Collections.shuffle(answers);
            Button answer1 = (Button) findViewById(R.id.Answer1);
            answer1.setText(answers.get(0));
            Button answer2 = (Button) findViewById(R.id.Answer2);
            answer2.setText(answers.get(1));
            Button answer3 = (Button) findViewById(R.id.Answer3);
            answer3.setText(answers.get(2));
            Button answer4 = (Button) findViewById(R.id.Answer4);
            answer4.setText(answers.get(3));
            ((TextView) findViewById(R.id.Score)).setText("Score: " + score);
            ((TextView) findViewById(R.id.Counter)).setText("Question " + (counter + 1) + "/10");
        }   else    {
            long Time = System.currentTimeMillis() - StartTime;
            Intent intent = new Intent(QuizActivity.this, ResultActivity.class);
            Bundle values = new Bundle();
            values.putInt("score", score);
            values.putLong("time", Time);
            intent.putExtras(values);
            startActivity(intent);
        }
    }
    public void onBackPressed() {
        startActivity(new Intent(QuizActivity.this, MainActivity.class));
    }
}
