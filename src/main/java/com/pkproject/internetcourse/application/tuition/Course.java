package com.pkproject.internetcourse.application.tuition;

/**
 * Created by on 05.12.2016.
 */
public class Course {
    private int idCourse;
    private String subject;
    private String content;
    private String level;
    private String name;

    public Course(String level, String name) {
        this.level = level;
        this.name = name;
    }
    public Course() {

    }

    public int getIdCourse() {
        return idCourse;
    }

    public void setIdCourse(int idCourse) {
        this.idCourse = idCourse;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getLevel() {
        return level;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
