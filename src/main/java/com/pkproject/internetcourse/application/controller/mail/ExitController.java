package com.pkproject.internetcourse.application.controller.mail;

import com.pkproject.internetcourse.application.account.Account;
import com.pkproject.internetcourse.application.controller.admin.AdminMenuController;
import com.pkproject.internetcourse.application.controller.controllerinterfaces.Controller;
import com.pkproject.internetcourse.application.controller.instructor.InstructorMenuController;
import com.pkproject.internetcourse.application.controller.primary.PrimaryController;
import com.pkproject.internetcourse.application.controller.trainee.TraineeMenuController;
import com.pkproject.internetcourse.application.dialogs.DialogsUtils;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Optional;

/**
 * Created by Piotr Fudala on 14.11.2016.
 */
public class ExitController implements Controller{
    private Stage primaryStage;
    private Account account;
    private  PrimaryController primaryController;

    public void setAccount(Account account) {
        this.account = account;
        primaryController = new PrimaryController();
    }

    @FXML
    public Button btnExit;

    @FXML
    void exitWithMail() throws IOException {


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
        primaryStage = (Stage) btnExit.getScene().getWindow();
        primaryController.setPrimaryStage(primaryStage);
        primaryController.changeController();
    }
}
