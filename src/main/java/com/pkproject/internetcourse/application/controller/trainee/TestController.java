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
import java.util.List;
import java.util.Optional;
import java.util.Queue;

/**
 * Created by Piotr Fudala on 16.01.2017.
 */
public class TestController implements Controller {
    private Test test;
    private Account account;
    private PrimaryController primaryController;
    private Queue<Question> questions;
    private Question currentQuestion;

    public void setAccount(Account account) {
        this.account = account;
    }

    public void setTest(Test test) {
        this.test = test;
        primaryController = new PrimaryController();
        questions = test.getQuestions();
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

    @FXML
    public void runTest() throws IOException, SQLException {

        currentQuestion = questions.poll();
        if (currentQuestion != null) {
            if (questions.isEmpty()) {
                btnStart.setText("finish");
            } else {
                btnStart.setText("next");
            }
            displayCurrentQuestion();
            storeAnswer();
        } else {
            test.setResult(calculateScore());
            saveTest();
            goToMenu();
        }
    }

    private void storeAnswer() {
        if (rbA.isSelected()) {
            test.setAnswerForQuestion(currentQuestion, Question.AnswerLetter.A);
        }
        if (rbB.isSelected()) {
            test.setAnswerForQuestion(currentQuestion, Question.AnswerLetter.B);
        }
        if (rbC.isSelected()) {
            test.setAnswerForQuestion(currentQuestion, Question.AnswerLetter.C);
        }
        if (rbD.isSelected()) {
            test.setAnswerForQuestion(currentQuestion, Question.AnswerLetter.D);
        }
    }

    private void displayCurrentQuestion() {
        lQuestion.setText(currentQuestion.getQuestionName());
        lAnswerA.setText(currentQuestion.getAnswerText(Question.AnswerLetter.A));
        lAnswerB.setText(currentQuestion.getAnswerText(Question.AnswerLetter.B));
        lAnswerC.setText(currentQuestion.getAnswerText(Question.AnswerLetter.C));
        lAnswerD.setText(currentQuestion.getAnswerText(Question.AnswerLetter.D));
    }

    private int calculateScore() {
        return test.getAnswers().entrySet()
            .stream()
            .mapToInt(entry -> entry.getKey().getCorrectAnswer() == entry.getValue() ? 1 : 0)
            .sum();
    }

    private void goToMenu() throws IOException {
        String screenAddress = "/fxml/trainee/TraineeMenuScreen.fxml";
        TraineeMenuController controller = new TraineeMenuController(account);
        primaryController.setController(controller);
        primaryController.setScreenAddress(screenAddress);
        changeController(primaryController);
    }

    public void saveTest() throws SQLException {
        TuitionOperation operation = new TuitionOperation();
        Optional<ButtonType> result = DialogsUtils.saveTest();
        if (result.get() == ButtonType.APPLY.OK) {
            operation.insertResult(account, test);
        }
    }

    @Override
    public void changeController(PrimaryController controller) throws IOException {
        Stage primaryStage = (Stage) btnStart.getScene().getWindow();
        primaryController.setPrimaryStage(primaryStage);
        primaryController.changeController();
    }
}
