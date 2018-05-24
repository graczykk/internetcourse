package com.pkproject.internetcourse.application.controller.instructor;

import com.pkproject.internetcourse.application.account.Account;
import com.pkproject.internetcourse.application.controller.MainController;
import com.pkproject.internetcourse.application.controller.controllerinterfaces.Controller;
import com.pkproject.internetcourse.application.controller.primary.PrimaryController;
import com.pkproject.internetcourse.application.datebase.TuitionOperation;
import com.pkproject.internetcourse.application.dialogs.DialogsUtils;
import com.pkproject.internetcourse.application.tuition.Course;
import com.pkproject.internetcourse.application.tuition.Test;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Optional;

/**
 * Created by Piotr Fudala on 14.11.2016.
 */
public class AddCourseController implements Controller{
    private Stage primaryStage;
    private PrimaryController primaryController;
    private Account account;
    private Test test;
    private Course course;

    public AddCourseController(Account account, Test test) {
        this.account = account;
        primaryController = new PrimaryController();
        this.test = test;
        course = new Course(test.getLevel(), test.getName());
    }

    @FXML
    private TextArea tfText;

    @FXML
    private TextField tfSubject;

    @FXML
    private Button btnAddCourse;

    @FXML
    void goToTest() throws IOException {
        if(isNoEmpty()) {
            course.setContent(tfText.getText().trim());
            course.setSubject(tfSubject.getText().trim());
            String screenAddress = "/fxml/instructor/AddTestScreen.fxml";
            AddTestController controller = new AddTestController(account, test, course);
            primaryController.setController(controller);
            primaryController.setScreenAddress(screenAddress);
            changeController(primaryController);
        }

    }

    public boolean isNoEmpty() {
        return !tfSubject.getText().isEmpty() && !tfText.getText().isEmpty();
    }

    @Override
    public void changeController(PrimaryController controller) throws IOException {
        primaryStage = (Stage) btnAddCourse.getScene().getWindow();
        primaryController.setPrimaryStage(primaryStage);
        primaryController.changeController();
    }
}
