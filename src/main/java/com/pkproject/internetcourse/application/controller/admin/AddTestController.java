package com.pkproject.internetcourse.application.controller.admin;

import com.pkproject.internetcourse.application.account.Account;
import com.pkproject.internetcourse.application.controller.controllerinterfaces.Controller;
import com.pkproject.internetcourse.application.controller.instructor.InstructorMenuController;
import com.pkproject.internetcourse.application.controller.primary.PrimaryController;
import com.pkproject.internetcourse.application.datebase.TuitionOperation;
import com.pkproject.internetcourse.application.exceptions.QuestionInvalidException;
import com.pkproject.internetcourse.application.tuition.Course;
import com.pkproject.internetcourse.application.tuition.Question;
import com.pkproject.internetcourse.application.tuition.Test;
import com.pkproject.internetcourse.application.utils.AssertUtils;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by Piotr Fudala on 14.11.2016.
 */
public class AddTestController implements Controller {
    private Stage primaryStage;
    private PrimaryController primaryController;
    private Account account;
    private Test test;
    private Course course;
    int currentQuestion = 1;

    public AddTestController(Account account, Test test, Course course) {
        primaryController = new PrimaryController();
        this.account = account;
        this.test = test;
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
    private Label currentQuestionLabel;

    @FXML
    public void addTest() throws IOException, SQLException {
        if (currentQuestion < test.getNumberOfQuestion()) {
            test.addQuestion(readQuestion());
            currentQuestion++;
            clearForm();
            currentQuestionLabel.setText("Current question: " + currentQuestion);
        } else {
            disableForm();
            TuitionOperation tuitionOperation = new TuitionOperation();
            tuitionOperation.insertTuition(account, test, course);
            String screenAddress = "/fxml/admin/AdminMenuScreen.fxml";
            AdminMenuController controller = new AdminMenuController(account);
            primaryController.setController(controller);
            primaryController.setScreenAddress(screenAddress);
            changeController(primaryController);
        }
    }

    private void disableForm() {
        tfQuestion.setDisable(true);
        tfAnswerA.setDisable(true);
        tfAnswerB.setDisable(true);
        tfAnswerC.setDisable(true);
        tfAnswerD.setDisable(true);
        tfCorrectAnswer.setDisable(true);
    }

    private void clearForm() {
        tfQuestion.clear();
        tfAnswerA.clear();
        tfAnswerB.clear();
        tfAnswerC.clear();
        tfAnswerD.clear();
        tfCorrectAnswer.clear();
    }

    private Question readQuestion() {
        assertQuestionValid();
        Question question = new Question();
        question.setQuestionName(tfQuestion.getText().trim());

        question.addAnswer(Question.AnswerLetter.A, tfAnswerA.getText().trim());
        question.addAnswer(Question.AnswerLetter.B, tfAnswerB.getText().trim());
        question.addAnswer(Question.AnswerLetter.C, tfAnswerC.getText().trim());
        question.addAnswer(Question.AnswerLetter.D, tfAnswerD.getText().trim());

        question.setCorrectAnswer(Question.AnswerLetter.valueOf(tfCorrectAnswer.getText().trim()));

        return question;
    }

    private void assertQuestionValid() {
        if (AssertUtils.isEmpty(tfQuestion.getText())) {
            throw new QuestionInvalidException("Question is empty");
        }
        if (AssertUtils.isEmpty(tfAnswerA.getText())) {
            throw new QuestionInvalidException("Answer A is empty");
        }
        if (AssertUtils.isEmpty(tfAnswerB.getText())) {
            throw new QuestionInvalidException("Answer B is empty");
        }
        if (AssertUtils.isEmpty(tfAnswerC.getText())) {
            throw new QuestionInvalidException("Answer C is empty");
        }
        if (AssertUtils.isEmpty(tfAnswerD.getText())) {
            throw new QuestionInvalidException("Answer D is empty");
        }
        if (AssertUtils.isEmpty(tfCorrectAnswer.getText())) {
            throw new QuestionInvalidException("Correct answer is empty");
        }
        if (!tfCorrectAnswer.getText().matches("[a|A]|[b|B]|[c|C]|[d|D]")) {
            throw new QuestionInvalidException("Correct answer should be A,B,C or D");
        }
    }

    @Override
    public void changeController(PrimaryController controller) throws IOException {
        primaryStage = (Stage) btnTest.getScene().getWindow();
        primaryController.setPrimaryStage(primaryStage);
        primaryController.changeController();
    }
}
