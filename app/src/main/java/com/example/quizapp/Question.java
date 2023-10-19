package com.example.quizapp;

public class Question {
    private int questionID;
    private boolean trueAnswer;

    public Question (int questionId, boolean trueAnswer){
        this.questionID = questionId;
        this.trueAnswer = trueAnswer;
    }

    public boolean isTrue() {
        return trueAnswer;
    }

    public int getId(){
        return questionID;
    }
}
