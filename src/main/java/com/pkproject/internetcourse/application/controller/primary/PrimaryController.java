package com.pkproject.internetcourse.application.controller.primary;

import com.pkproject.internetcourse.application.controller.controllerinterfaces.Controller;
import com.pkproject.internetcourse.application.controller.settings.AccountSettingsController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Created by Piotr Fudala on 17.01.2017.
 */
public class PrimaryController {
    private String screenAddress;
    private Controller controller;
    private Stage primaryStage;

    public void setScreenAddress(String screenAddress) {
        this.screenAddress = screenAddress;
    }

    public void setController(Controller controller) {
        this.controller = controller;
    }

    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    public void changeController() throws IOException {

        FXMLLoader loader = new FXMLLoader(this.getClass().getResource(screenAddress));
        loader.setController(controller);

        AnchorPane anchorPane = loader.load();
        Scene scene = new Scene(anchorPane);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();

        primaryStage.close();
    }
}
