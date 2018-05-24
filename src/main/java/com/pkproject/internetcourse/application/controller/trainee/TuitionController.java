package com.pkproject.internetcourse.application.controller.trainee;

import com.pkproject.internetcourse.application.account.Account;
import com.pkproject.internetcourse.application.controller.controllerinterfaces.Controller;
import com.pkproject.internetcourse.application.controller.primary.PrimaryController;
import com.pkproject.internetcourse.application.tuition.Course;
import com.pkproject.internetcourse.application.tuition.Test;
import com.pkproject.internetcourse.application.tuition.Tuition;
import javafx.fxml.FXML;

import java.io.IOException;

/**
 * Created by Piotr Fudala on 16.01.2017.
 */
public class TuitionController implements Controller{
    private Account account;
    private Test test;
    private Course course;

    public TuitionController(Account account, Test test, Course course) {
        this.account = account;
        this.test = test;
        this.course = course;
    }

    @FXML
    public TestController testController;

    @FXML
    public CourseController courseController;



    @FXML
    public void initialize() {
        testController.setTest(test);
        testController.setAccount(account);
        courseController.setCourse(course);
    }

    @Override
    public void changeController(PrimaryController controller) throws IOException {

    }
}
