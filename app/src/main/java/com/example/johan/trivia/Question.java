package com.example.johan.trivia;

import java.io.Serializable;
import java.util.ArrayList;

public class Question implements Serializable {
    private String category, question, correct_answer;
    private ArrayList<String> answers;

//    Initiate class for questions in the quiz
    public Question(String category, String question, String correct_answer, ArrayList<String> answers) {
        this.category = category;
        this.question = question;
        this.correct_answer = correct_answer;
        this.answers = answers;
    }

//    Initiate getters
    public String getCategory() {
        return category;
    }

    public String getQuestion() {
        return question;
    }

    public String getCorrect_answer() {
        return correct_answer;
    }

    public ArrayList<String> getAnswers() {
        return answers;
    }

//    Initiate setters
    public void setCategory(String category) {
        this.category = category;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public void setCorrect_answer(String correct_answer) {
        this.correct_answer = correct_answer;
    }

    public void setAnswers(ArrayList<String> answers) {
        this.answers = answers;
    }
}