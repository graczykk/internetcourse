package com.pkproject.internetcourse.application.controller.instructor;

import com.pkproject.internetcourse.application.account.Account;

import com.pkproject.internetcourse.application.controller.controllerinterfaces.Controller;
import com.pkproject.internetcourse.application.controller.primary.PrimaryController;
import com.pkproject.internetcourse.application.tuition.Test;
import com.pkproject.internetcourse.application.tuition.Tuition;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;


/**
 * Created by Piotr Fudala on 17.01.2017.
 */
public class CreateTuitionController implements Controller{
    private Account account;
    private Stage primaryStage;
    private PrimaryController primaryController;
    private Tuition tuition;
    private Test test;

    public CreateTuitionController(Account account) {
        this.account = account;
        primaryController = new PrimaryController();
        tuition = new Tuition();
        test = new Test();
    }

    @FXML
    private TextField tfTuition;

    @FXML
    private RadioButton basicLevel;

    @FXML
    private RadioButton advancedLevel;

    @FXML
    private TextField tfNumberOfQuestion;

    @FXML
    private Button btnAdd;

    @FXML
    void addTuition() throws IOException {
        if(isNoEmpty()) {
            test.setName(tfTuition.getText().trim());
            if(basicLevel.isSelected()) {
                test.setLevel("podstawowy");
            }
            else {
                test.setLevel("zaawansowany");
            }

            int number = Integer.parseInt(tfNumberOfQuestion.getText().trim());
            test.setNumberOfQuestion(number);

            String screenAddress = "/fxml/instructor/AddCourseScreen.fxml";
            AddCourseController controller = new AddCourseController(account, test);
            primaryController.setController(controller);
            primaryController.setScreenAddress(screenAddress);
            changeController(primaryController);
        }
    }


    public boolean isNoEmpty() {
        return  !tfTuition.getText().isEmpty() && !tfNumberOfQuestion.getText().isEmpty();
    }

    @FXML
    public void goToMenu() throws IOException {
        String screenAddress = "/fxml/instructor/InstructorMenuScreen.fxml";
        InstructorMenuController controller = new InstructorMenuController(account);
        primaryController.setController(controller);
        primaryController.setScreenAddress(screenAddress);
        changeController(primaryController);

    }

    @Override
    public void changeController(PrimaryController controller) throws IOException {
        primaryStage = (Stage) btnAdd.getScene().getWindow();
        primaryController.setPrimaryStage(primaryStage);
        primaryController.changeController();
}
}
