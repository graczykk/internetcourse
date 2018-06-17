package com.pkproject.internetcourse.application.controller;


import com.pkproject.internetcourse.application.controller.controllerinterfaces.Controller;
import com.pkproject.internetcourse.application.controller.primary.PrimaryController;
import com.pkproject.internetcourse.application.dialogs.DialogsUtils;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Optional;

public class MainController implements Controller{
    private Stage primaryStage;
    private PrimaryController primaryController;

    public MainController() {
        primaryController = new PrimaryController();
    }

    @FXML
    private Button btnLogging;

    @FXML
    private Button btnRegistration;

    @FXML
    private Button btnInformation;

	@FXML
	public void goToLogging() throws IOException {
        String screenAddress = "/fxml/LoggingScreen.fxml";
        LogingController controller = new LogingController();
        primaryController.setController(controller);
        primaryController.setScreenAddress(screenAddress);
        changeController(primaryController);

	}
	
	@FXML
	public void goToRegistration() throws IOException{
        String screenAddress = "/fxml/RegistrationScreen.fxml";
        RegistrationController controller = new RegistrationController();
        primaryController.setController(controller);
        primaryController.setScreenAddress(screenAddress);
        changeController(primaryController);
	}
	
	@FXML
	public void goToInformation() throws IOException {
        String screenAddress = "/fxml/InformationScreen.fxml";
        InformationController controller = new InformationController();
        primaryController.setController(controller);
        primaryController.setScreenAddress(screenAddress);
        changeController(primaryController);
	}
	
	@FXML
	public void exitWithProgram() {
        Optional<ButtonType> result = DialogsUtils.exitWithProgramDialog();

        if(result.get() == ButtonType.APPLY.OK) {
            Platform.exit();
        }

	}

    @Override
    public void changeController(PrimaryController controller) throws IOException {
        primaryStage = (Stage) btnInformation.getScene().getWindow();
        primaryController.setPrimaryStage(primaryStage);
        primaryController.changeController();
    }
}
