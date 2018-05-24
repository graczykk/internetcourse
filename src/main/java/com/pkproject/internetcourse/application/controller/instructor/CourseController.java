package com.pkproject.internetcourse.application.controller.instructor;

import com.pkproject.internetcourse.application.account.Account;
import com.pkproject.internetcourse.application.controller.controllerinterfaces.Controller;
import com.pkproject.internetcourse.application.controller.primary.PrimaryController;
import com.pkproject.internetcourse.application.datebase.TuitionOperation;
import com.pkproject.internetcourse.application.tuition.Course;
import com.pkproject.internetcourse.application.tuition.Test;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

/**
 * Created by Piotr Fudala on 22.01.2017.
 */
public class CourseController implements Controller {
    private Stage primaryStage;
    private PrimaryController primaryController;
    private Account account;
    private Test test;
    private Course course;
    private TuitionOperation tuitionOperation;

    public CourseController(Account account, Course course) {
        this.account = account;
        this.course = course;
        primaryController = new PrimaryController();
    }

    @FXML
    public void initialize() {
        tfSubject.setText(course.getSubject());
        taText.setText(course.getContent());
        tuitionOperation = new TuitionOperation();
    }

    @FXML
    private Button btnLogout;

    @FXML
    private TextArea taText;

    @FXML
    private TextField tfSubject;

    @FXML
    public void modification() throws SQLException, IOException {
        String newSubject = tfSubject.getText();
        String newText = taText.getText();

        tuitionOperation.changeData(course.getIdCourse(),newSubject, newText);

        String screenAddress = "/fxml/instructor/InstructorMenuScreen.fxml";
        InstructorMenuController controller = new InstructorMenuController(account);
        primaryController.setController(controller);
        primaryController.setScreenAddress(screenAddress);
        changeController(primaryController);

    }

    @Override
    public void changeController(PrimaryController controller) throws IOException {
        primaryStage = (Stage) btnLogout.getScene().getWindow();
        primaryController.setPrimaryStage(primaryStage);
        primaryController.changeController();
    }
}
