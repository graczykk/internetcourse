package com.pkproject.internetcourse.application.controller.trainee;

import com.pkproject.internetcourse.application.account.Account;
import com.pkproject.internetcourse.application.account.Trainee;
import com.pkproject.internetcourse.application.controller.MainController;
import com.pkproject.internetcourse.application.controller.controllerinterfaces.Controller;
import com.pkproject.internetcourse.application.controller.primary.PrimaryController;
import com.pkproject.internetcourse.application.datebase.TuitionOperation;
import com.pkproject.internetcourse.application.dialogs.DialogsUtils;
import com.pkproject.internetcourse.application.tuition.Course;
import com.pkproject.internetcourse.application.tuition.Question;
import com.pkproject.internetcourse.application.tuition.Test;
import com.pkproject.internetcourse.application.tuition.Tuition;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.stage.Stage;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;

/**
 * Created by Piotr Fudala on 16.01.2017.
 */
public class TestController implements Controller{
    private Test test;
    private static int counter = 0;
    private int result = 0;
    private ArrayList<Question> questions;
    private Tuition tuition;
    private Account account;
    private Stage primaryStage;
    private PrimaryController primaryController;
    private Course course;

    public void setAccount(Account account) {
        this.account = account;
    }


    public void setTest(Test test) {
        this.test = test;
        questions = test.getQuestion();
        primaryController = new PrimaryController();
    }

    @FXML
    private Label lQuestion;

    @FXML
    private Label lAnswerA;

    @FXML
    private Label lAnswerB;

    @FXML
    private Label lAnswerC;

    @FXML
    private Label lAnswerD;

    @FXML
    private RadioButton rbA;

    @FXML
    private RadioButton rbB;

    @FXML
    private RadioButton rbC;

    @FXML
    private RadioButton rbD;
    @FXML
    private Button btnStart;
    private boolean entry = true;

    @FXML
    public void runTest() throws IOException, SQLException {
        if (entry) {
            nextQuestion();
        } else if (entry == false) {
            checkResult();
        }

    }

    public void nextQuestion() throws IOException {
        btnStart.setText("next");
        if (counter < questions.size()) {
            String[] answers = questions.get(counter).getAllAnswers();
            lAnswerA.setText(answers[0]);
            lAnswerB.setText(answers[1]);
            lAnswerC.setText(answers[2]);
            lAnswerD.setText(answers[3]);

            lQuestion.setText(questions.get(counter).getQuestion());
            entry = false;
        } else {
            goToMenu();
        }

    }

    private void goToMenu() throws IOException {
        String screenAddress = "/fxml/trainee/TraineeMenuScreen.fxml";
        TraineeMenuController controller = new TraineeMenuController(account);
        primaryController.setController(controller);
        primaryController.setScreenAddress(screenAddress);
        changeController(primaryController);
    }

    public void checkResult() throws IOException, SQLException {
        boolean checkAnswer = false;

        if (rbA.isSelected()) {
            checkAnswer = lAnswerA.getText().equals(questions.get(counter).getProperlyResult());
        }

        if (rbB.isSelected()) {
            checkAnswer = lAnswerB.getText().equals(questions.get(counter).getProperlyResult());
        }

        if (rbC.isSelected()) {
            checkAnswer = lAnswerC.getText().equals(questions.get(counter).getProperlyResult());
        }

        if (rbD.isSelected()) {
            checkAnswer = lAnswerD.getText().equals(questions.get(counter).getProperlyResult());
        }

        result = (checkAnswer == true ? ++result : result);
        System.out.println(result);
        entry = true;
        if(counter == questions.size()-1) {
            test.setResult(result);
            saveTest();
            String screenAddress = "/fxml/trainee/TraineeMenuScreen.fxml";
            TraineeMenuController controller = new TraineeMenuController(account);
            primaryController.setController(controller);
            primaryController.setScreenAddress(screenAddress);
            changeController(primaryController);
        }
        counter++;
    }

    public void saveTest() throws SQLException {
        TuitionOperation operation = new TuitionOperation();
        Optional<ButtonType> result = DialogsUtils.saveTest();
        if(result.get() == ButtonType.APPLY.OK) {
            operation.insertResult(account, test);
        }
    }

    @Override
    public void changeController(PrimaryController controller) throws IOException {
        primaryStage = (Stage) btnStart.getScene().getWindow();
        primaryController.setPrimaryStage(primaryStage);
        primaryController.changeController();
    }
}
