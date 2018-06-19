package com.pkproject.internetcourse.application.tuition;

import java.util.*;

/**
 * Created by on 05.12.2016.
 */
public class Test {
    private  int idTest;
    private String name;
    private String author;
    private String level;
    private int numberOfQuestion;
    private int result;
    private Queue<Question> questions = new LinkedList<>();
    private Map<Question, Question.AnswerLetter> answers = new HashMap<>();
    private Course course;

    public int getResult() {
        return result;
    }
    public void setResult(int result) {
        this.result = result;
    }
    public int getNumberOfQuestion() {
        return numberOfQuestion;
    }
    public void setNumberOfQuestion(int numberOfQuestion) {
        this.numberOfQuestion = numberOfQuestion;
    }
    public Course getCourse() {
        return course;
    }
    public void setCourse(Course course) {
        this.course = course;
    }
    public int getIdTest() {
        return idTest;
    }
    public void setIdTest(int idTest) {
        this.idTest = idTest;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getAuthor() {
        return author;
    }
    public void setAuthor(String author) {
        this.author = author;
    }
    public String getLevel() {
        return level;
    }
    public void setLevel(String level) {
        this.level = level;
    }

    public Map<Question, Question.AnswerLetter> getAnswers() {
        return new HashMap<>(answers);
    }

    public Queue<Question> getQuestions() {
        return new LinkedList<>(questions);
    }

    public void addQuestion(Question question) {
        this.questions.add(question);
    }

    public void setAnswerForQuestion(Question question, Question.AnswerLetter answer) {
        this.answers.put(question, answer);
    }

    @Override
    public String toString() {
        return name;
    }
}
