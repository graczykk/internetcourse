package com.pkproject.internetcourse.application.controller.admin;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Optional;

import com.pkproject.internetcourse.application.account.Account;
import com.pkproject.internetcourse.application.controller.MainController;
import com.pkproject.internetcourse.application.controller.controllerinterfaces.Controller;
import com.pkproject.internetcourse.application.controller.instructor.*;
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
public class AdminMenuController implements Controller{
    private Stage primaryStage;
    private Account account;
    private PrimaryController primaryController;

    @FXML
    private Button btnLogout;

    public AdminMenuController(Account account) {
        this.account = account;
        primaryController = new PrimaryController();
    }

    @FXML
    public void goToCreateTuition() throws IOException {
        String screenAddress = "/fxml/admin/CreateTuitionScreen.fxml";
        CreateTuitionController controller = new CreateTuitionController(account);
        primaryController.setController(controller);
        primaryController.setScreenAddress(screenAddress);
        changeController(primaryController);
    }

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
    public void seeCourses() throws SQLException, IOException {
        String screenAddress = "/fxml/admin/ViewAllCourseScreen.fxml";
        ViewAllCoursesController controller = new ViewAllCoursesController(account);
        primaryController.setController(controller);
        primaryController.setScreenAddress(screenAddress);
        changeController(primaryController);
    }

    @FXML
    void goToMail() throws IOException {
        String screenAddress = "/fxml/mail/MailScreen.fxml";
        MailController controller = new MailController(account);
        primaryController.setScreenAddress(screenAddress);
        primaryController.setController(controller);
        changeController(primaryController);

    }


    @FXML
    void goToAccountSettings() throws IOException {
        String screenAddress = "/fxml/settings/AccountSettingsScreen.fxml";
        AccountSettingsController controller = new AccountSettingsController(account);
        primaryController.setScreenAddress(screenAddress);
        primaryController.setController(controller);
        changeController(primaryController);
    }

    @FXML
    public void goToManageAccount() throws IOException {
        String screenAddress = "/fxml/admin/ManageAccountScreen.fxml";
        ManageAccountController controller = new ManageAccountController(account);
        primaryController.setScreenAddress(screenAddress);
        primaryController.setController(controller);
        changeController(primaryController);
    }


    @Override
    public void changeController(PrimaryController controller) throws IOException {
        primaryStage = (Stage) btnLogout.getScene().getWindow();
        primaryController.setPrimaryStage(primaryStage);
        primaryController.changeController();
    }
}
