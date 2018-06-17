package com.pkproject.internetcourse.application.tuition;

import java.util.EnumMap;
import java.util.Map;

/**
 * Created by on 15.01.2017.
 */
public class Question {
    private String questionName;
    private Map<AnswerLetter, String> answers = new EnumMap<>(AnswerLetter.class);
    private AnswerLetter correctAnswer;

    public String getQuestionName() {
        return questionName;
    }
    public void setQuestionName(String questionName) {
        this.questionName = questionName;
    }

    public Map<AnswerLetter, String> getAllAnswers() {
        return new EnumMap<>(answers);
    }

    public void setAllAnswers(Map<AnswerLetter, String> answers) {
        this.answers = answers;
    }

    public void addAnswer(AnswerLetter answerLetter, String answer) {
        this.answers.put(answerLetter, answer);
    }

    public AnswerLetter getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(AnswerLetter answerLetter) {
        this.correctAnswer = answerLetter;
    }

    public String getAnswerText(AnswerLetter letter) {
        return this.answers.get(letter);
    }

    public enum AnswerLetter {
        A,
        B,
        C,
        D
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Question question = (Question) o;

        if (!questionName.equals(question.questionName)) {
            return false;
        }
        if (!answers.equals(question.answers)) {
            return false;
        }
        return correctAnswer == question.correctAnswer;
    }

    @Override
    public int hashCode() {
        int result = questionName.hashCode();
        result = 31 * result + answers.hashCode();
        result = 31 * result + correctAnswer.hashCode();
        return result;
    }

}
