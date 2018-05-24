package com.pkproject.internetcourse.application.controller.trainee;

import com.pkproject.internetcourse.application.account.Account;
import com.pkproject.internetcourse.application.controller.controllerinterfaces.Controller;
import com.pkproject.internetcourse.application.controller.primary.PrimaryController;
import com.pkproject.internetcourse.application.tuition.Course;
import com.pkproject.internetcourse.application.tuition.Test;
import com.pkproject.internetcourse.application.tuition.Tuition;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.text.Text;


import java.io.IOException;

/**
 * Created by Piotr Fudala on 18.01.2017.
 */
public class CourseController implements Controller {
    private Account account;
    private Course course;

    @FXML
    private TextArea taText;

    @FXML
    private Text tSubject;

    public void setCourse(Course course) {
        this.course = course;
        tSubject.setText(course.getSubject());
        taText.setText(course.getContent());
    }

    public void setAccount(Account account) {
        this.account = account;
    }




    @Override
    public void changeController(PrimaryController controller) throws IOException {

    }
}
