package com.pkproject.internetcourse.application.tuition;

/**
 * Created by on 15.01.2017.
 */
public class Question {
    private String question;
    private String [] allAnswers = new String[4];
    private String properlyResult;
    private int Number;

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public void setNumber(int number) {
        Number = number;
    }

    public String[] getAllAnswers() {
        return allAnswers;
    }

    public void setAllAnswers(String[] allAnswers) {
        this.allAnswers = allAnswers;
    }

    public String getProperlyResult() {
        return properlyResult;
    }

    public void setProperlyResult(String properlyResult) {
        this.properlyResult = properlyResult;
    }
}
