package com.pkproject.internetcourse.application.controller;


import com.pkproject.internetcourse.application.controller.controllerinterfaces.Controller;
import com.pkproject.internetcourse.application.controller.primary.PrimaryController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Created by Piotr Fudala on 01.11.2016.
 */
public class InformationController implements Controller{
    private Stage primaryStage;
    private PrimaryController primaryController;

    public InformationController() {
        primaryController = new PrimaryController();
    }

    @FXML
    private Button btnBack;

    @FXML
    public void goToMainScreen() throws IOException {
        String screenAddress = "/fxml/MainScreen.fxml";
        MainController controller = new MainController();
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
