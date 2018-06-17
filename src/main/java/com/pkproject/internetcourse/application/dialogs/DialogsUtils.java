package com.pkproject.internetcourse.application.dialogs;

import com.pkproject.internetcourse.application.account.Account;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.util.*;


/**
 * Created by  on 13.12.2016.
 */
public class DialogsUtils {
    public static void DialogAboutApplication() {
        Alert informationAlert = new Alert(Alert.AlertType.INFORMATION);
        informationAlert.setTitle("ok");
        informationAlert.setHeaderText("git");
        informationAlert.setContentText("seriously");
        informationAlert.showAndWait();
    }

    public static Optional<ButtonType> exitWithProgramDialog() {
        Alert confirmationDialog = new Alert(Alert.AlertType.CONFIRMATION);
        confirmationDialog.setTitle("exit with program");
        confirmationDialog.setHeaderText("do you really want to quit");
        Optional<ButtonType> result = confirmationDialog.showAndWait();
        return result;
    }

    public static Optional<ButtonType> logoutWithProgramDialog() {
        Alert confirmationDialog = new Alert(Alert.AlertType.CONFIRMATION);
        confirmationDialog.setTitle("logout");
        confirmationDialog.setHeaderText("do you really want to logout");
        Optional<ButtonType> result = confirmationDialog.showAndWait();
        return result;
    }

    public static Optional<ButtonType> loginError() {
        Alert confirmationDialog = new Alert(Alert.AlertType.ERROR);
        confirmationDialog.setTitle("error");
        confirmationDialog.setHeaderText("incorrect Login or Password! Try Again");
        Optional<ButtonType> result = confirmationDialog.showAndWait();
        return result;
    }

    public static Optional<ButtonType> showLastDataLogin(Account account) {
        Alert confirmationDialog = new Alert(Alert.AlertType.INFORMATION);
        confirmationDialog.setTitle("data login");
        confirmationDialog.setHeaderText("Your last login it: " + account.getDataLogin());
        Optional<ButtonType> result = confirmationDialog.showAndWait();
        return result;
    }

    public static Optional<ButtonType> registerError() {
        Alert confirmationDialog = new Alert(Alert.AlertType.ERROR);
        confirmationDialog.setTitle("error");
        confirmationDialog.setHeaderText("incorrect data! Try Again");
        Optional<ButtonType> result = confirmationDialog.showAndWait();
        return result;
    }

    public static Optional<ButtonType> saveTest() {
        Alert confirmationDialog = new Alert(Alert.AlertType.CONFIRMATION);
        confirmationDialog.setTitle("save test");
        confirmationDialog.setHeaderText("do you really want save test result");
        Optional<ButtonType> result = confirmationDialog.showAndWait();
        return result;
    }


    public static Optional<ButtonType> confirmDeleteAccount() {
        Alert confirmationDialog = new Alert(Alert.AlertType.CONFIRMATION);
        confirmationDialog.setTitle("save test");
        confirmationDialog.setHeaderText("do you really delete account");
        Optional<ButtonType> result = confirmationDialog.showAndWait();
        return result;
    }
}
