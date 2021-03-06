package com.pkproject.internetcourse.application.controller.trainee;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Optional;

import com.pkproject.internetcourse.application.account.Account;
import com.pkproject.internetcourse.application.controller.MainController;
import com.pkproject.internetcourse.application.controller.controllerinterfaces.Controller;
import com.pkproject.internetcourse.application.controller.log.LogController;
import com.pkproject.internetcourse.application.controller.mail.MailController;
import com.pkproject.internetcourse.application.controller.primary.PrimaryController;
import com.pkproject.internetcourse.application.controller.settings.AccountSettingsController;
import com.pkproject.internetcourse.application.dialogs.DialogsUtils;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * Created by Piotr Fudala on 14.11.2016.
 */
public class TraineeMenuController implements Controller{
    private Stage primaryStage;
    private Account account;
    private PrimaryController primaryController;

    public TraineeMenuController(Account account) {
        this.account = account;
        primaryController = new PrimaryController();
    }

    @FXML
    private Button btnLogout;

    @FXML
    public void goToLog() throws IOException, SQLException {
        String screenAddress = "/fxml/log/LogScreen.fxml";
        LogController controller = new LogController(account);
        primaryController.setController(controller);
        primaryController.setScreenAddress(screenAddress);
        changeController(primaryController);
    }

    @FXML
    public void goToMainScreen() throws IOException {
        Optional<ButtonType> result = DialogsUtils.logoutWithProgramDialog();

        if(result.get() == ButtonType.APPLY.OK) {
            String screenAddress = "/fxml/MainScreen.fxml";
            MainController controller = new MainController();
            primaryController.setController(controller);
            primaryController.setScreenAddress(screenAddress);
            changeController(primaryController);
        }
    }

    @FXML
    public void goToOpenCourses() throws IOException {
        String screenAddress = "/fxml/trainee/ChoiceCourseScreen.fxml";
        ChoiceCourseController controller = new ChoiceCourseController(account);
        primaryController.setController(controller);
        primaryController.setScreenAddress(screenAddress);
        changeController(primaryController);
    }

    @FXML
    void goToMail() throws IOException {
        String screenAddress = "/fxml/mail/MailScreen.fxml";
        MailController controller = new MailController(account);
        primaryController.setController(controller);
        primaryController.setScreenAddress(screenAddress);
        changeController(primaryController);
    }

    @FXML
    public void goToAccountSettings() throws IOException {
        String screenAddress = "/fxml/settings/AccountSettingsScreen.fxml";
        AccountSettingsController controller = new AccountSettingsController(account);
        primaryController.setController(controller);
        primaryController.setScreenAddress(screenAddress);
        changeController(primaryController);
    }

    @FXML
    public void goToScores() throws IOException, SQLException {
        String screenAddress = "/fxml/trainee/ScoreScreen.fxml";
        ScoreController controller = new ScoreController(account);
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
