package com.pkproject.internetcourse.application.controller.instructor;

import com.pkproject.internetcourse.application.account.Account;
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
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;

/**
 * Created by Piotr Fudala on 14.11.2016.
 */
public class AddTestController implements Controller {
    private Stage primaryStage;
    private PrimaryController primaryController;
    private Account account;
    private Test test;
    private ArrayList<Question> questions;
    private int numberOfQuestion = 0;
    private int lengthQuestion;
    private Course course;

    public AddTestController(Account account, Test test, Course course) {
        primaryController = new PrimaryController();
        this.account = account;
        this.test = test;
        questions = new ArrayList<>();
        lengthQuestion = test.getNumberOfQuestion();
        this.course = course;
    }

    @FXML
    private Button btnTest;

    @FXML
    private TextField tfQuestion;

    @FXML
    private TextField tfAnswerA;

    @FXML
    private TextField tfAnswerB;

    @FXML
    private TextField tfAnswerC;

    @FXML
    private TextField tfAnswerD;

    @FXML
    private TextField tfCorrectAnswer;


    @FXML
    public void addTest() throws IOException, SQLException {
        if (numberOfQuestion < lengthQuestion) {

            if (isNoEmpty()) {
                addQuestion();
                clearForm();

                numberOfQuestion++;

                if(numberOfQuestion++ == lengthQuestion ) {
                    tfQuestion.setDisable(true);
                    tfAnswerA.setDisable(true);
                    tfAnswerB.setDisable(true);
                    tfAnswerC.setDisable(true);
                    tfAnswerD.setDisable(true);
                    tfCorrectAnswer.setDisable(true);
                }
            }
        } else {
            TuitionOperation tuitionOperation = new TuitionOperation();
            tuitionOperation.insertTuition(account, test, course);
            String screenAddress = "/fxml/instructor/InstructorMenuScreen.fxml";
            InstructorMenuController controller = new InstructorMenuController(account);
            primaryController.setController(controller);
            primaryController.setScreenAddress(screenAddress);
            changeController(primaryController);
        }

    }

    private void clearForm() {
        tfQuestion.clear();
        tfAnswerA.clear();
        tfAnswerB.clear();
        tfAnswerC.clear();
        tfAnswerD.clear();
        tfCorrectAnswer.clear();
    }

    private void addQuestion() {
        Question question = new Question();
        question.setQuestionName(tfQuestion.getText().trim());

        question.addAnswer(Question.AnswerLetter.A, tfAnswerA.getText().trim());
        question.addAnswer(Question.AnswerLetter.B, tfAnswerB.getText().trim());
        question.addAnswer(Question.AnswerLetter.C, tfAnswerC.getText().trim());
        question.addAnswer(Question.AnswerLetter.D, tfAnswerD.getText().trim());
        question.setCorrectAnswer(Question.AnswerLetter.valueOf(tfCorrectAnswer.getText().trim()));
        test.addQuestion(question);
    }

    public boolean isNoEmpty() {
        return (!tfQuestion.getText().isEmpty() && !tfAnswerA.getText().isEmpty() && !tfAnswerB.getText().isEmpty() &&
                !tfAnswerC.getText().isEmpty() && !tfAnswerD.getText().isEmpty() && !tfCorrectAnswer.getText().isEmpty());
    }

    @Override
    public void changeController(PrimaryController controller) throws IOException {
        primaryStage = (Stage) btnTest.getScene().getWindow();
        primaryController.setPrimaryStage(primaryStage);
        primaryController.changeController();
    }
}
