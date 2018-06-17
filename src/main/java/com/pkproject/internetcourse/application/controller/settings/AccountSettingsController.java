package com.pkproject.internetcourse.application.controller.settings;

import com.pkproject.internetcourse.application.account.Account;
import com.pkproject.internetcourse.application.controller.MainController;
import com.pkproject.internetcourse.application.controller.admin.AdminMenuController;
import com.pkproject.internetcourse.application.controller.controllerinterfaces.Controller;
import com.pkproject.internetcourse.application.controller.instructor.InstructorMenuController;
import com.pkproject.internetcourse.application.controller.primary.PrimaryController;
import com.pkproject.internetcourse.application.controller.trainee.TraineeMenuController;
import com.pkproject.internetcourse.application.datebase.AccountOperation;
import com.pkproject.internetcourse.application.datebase.Operation;
import com.pkproject.internetcourse.application.dialogs.DialogsUtils;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Optional;

/**
 * Created by Piotr Fudala on 06.01.2017.
 */
public class AccountSettingsController implements Controller{
    private Stage  primaryStage;
    private Account account;
    private Operation operation;
    private PrimaryController primaryController;

    public AccountSettingsController(Account account) {
        this.account = account;
        operation = new Operation();
        primaryController = new PrimaryController();
    }

    @FXML
    private TextField tfFullName;

    @FXML
    private TextField tfLogin;

    @FXML
    private TextField tfEmail;

    @FXML
    private TextField tfPassword;

    @FXML
    private Button btnBack;

    @FXML
    private Label lFullname;

    @FXML
    private Label lLogin;

    @FXML
    private Label lEmail;

    @FXML
    private Label lPassword;

    @FXML
    private TextField tfReTypePassword;

    @FXML
    public void initialize() {
        lFullname.setText(account.getFullname());
        lLogin.setText(account.getLogin());
        lEmail.setText(account.getEmail());
    }

    @FXML
    void changeEmail() throws SQLException {
        String email = tfEmail.getText().trim();
        if(email != "") {
            lEmail.setText(email);
            account = operation.updateEmail(account,email);
        }
    }

    @FXML
    public void deleteAccount() throws SQLException, IOException {
        AccountOperation accountOperation = new AccountOperation();
        Optional<ButtonType> result = DialogsUtils.confirmDeleteAccount();
        if(result.get() == ButtonType.OK) {
            int id = account.getIdAccount();
            accountOperation.deleteAccount(id);

            String screenAddress = "/fxml/MainScreen.fxml";
            MainController controller = new MainController();
            primaryController.setController(controller);
            primaryController.setScreenAddress(screenAddress);
            changeController(primaryController);
        }
    }

    @FXML
    void changeFullName() throws SQLException {
        String fullName = tfFullName.getText().trim();

        if(fullName != "") {
            lFullname.setText(fullName);
            account = operation.updateFullName(account, fullName);
        }
    }

    @FXML
    void changeLogin() throws SQLException {
        String login = tfLogin.getText().trim();

        if(login != "") {
            lLogin.setText(tfLogin.getText());
            account = operation.updateLogin(account, login);
        }
    }

    @FXML
    void changePassword() throws SQLException {
        String password = tfPassword.getText().trim();

        if(isNoEmpty()) {
            lPassword.setText(tfPassword.getText());
            account = operation.updatePassword(account, password);
        }
    }

    public boolean isNoEmpty() {
        return !(tfPassword.getText().trim().isEmpty() || tfReTypePassword.getText().trim().isEmpty());
    }

    @FXML
    void goToMenuScreen() throws IOException {


        if (account.getAccountType() == "Admin") {
            goToAdminMenuController("/fxml/admin/AdminMenuScreen.fxml");
        }

        if (account.getAccountType() == "Instructor") {
            goToInstructorMenuController("/fxml/instructor/InstructorMenuScreen.fxml");
        }

        if (account.getAccountType() == "Trainee") {
            goToTraineeMenuController("/fxml/trainee/TraineeMenuScreen.fxml");
        }
    }

    public void goToAdminMenuController(String screenAddress) throws IOException {
        AdminMenuController controller = new AdminMenuController(account);
        primaryController.setController(controller);
        primaryController.setScreenAddress(screenAddress);
        changeController(primaryController);
    }




    public void goToInstructorMenuController(String screenAddress) throws IOException {
        InstructorMenuController controller = new InstructorMenuController(account);
        primaryController.setController(controller);
        primaryController.setScreenAddress(screenAddress);
        changeController(primaryController);
    }

    public void goToTraineeMenuController(String screenAddress) throws IOException {
        TraineeMenuController controller = new TraineeMenuController(account);
        primaryController.setController(controller);
        primaryController.setScreenAddress(screenAddress);
        changeController(primaryController);
    }

    @Override
    public void changeController(PrimaryController controller) throws IOException {
        primaryStage = (Stage) btnBack.getScene().getWindow();
        primaryController.setPrimaryStage(primaryStage);
        primaryController.changeController();
    }
}
