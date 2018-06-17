package com.pkproject.internetcourse.application.controller;

import java.io.IOException;
import java.sql.SQLException;

import com.pkproject.internetcourse.application.account.Account;
import com.pkproject.internetcourse.application.account.Admin;
import com.pkproject.internetcourse.application.account.Instructor;
import com.pkproject.internetcourse.application.account.Trainee;
import com.pkproject.internetcourse.application.controller.controllerinterfaces.Controller;
import com.pkproject.internetcourse.application.controller.primary.PrimaryController;
import com.pkproject.internetcourse.application.datebase.Operation;
import com.pkproject.internetcourse.application.dialogs.DialogsUtils;
import com.pkproject.internetcourse.application.exceptions.InvalidLoginException;
import com.pkproject.internetcourse.application.exceptions.InvalidPasswordException;
import com.pkproject.internetcourse.application.menu.AdminController;
import com.pkproject.internetcourse.application.menu.Context;
import com.pkproject.internetcourse.application.menu.InstructorController;
import com.pkproject.internetcourse.application.menu.TraineeController;
import com.pkproject.internetcourse.application.utils.AssertUtils;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;

public class LogingController implements Controller {
	private PrimaryController primaryController;
	private Account account;
	private Context context;
	private Stage primaryStage;

	@FXML
	private Button btnBack;

	@FXML
	private Button btnLogin;

	@FXML
	private TextField tfUserName;

	@FXML
	private TextField pfPassword;

	@FXML
	private ToggleGroup accountGroup;

	@FXML
	private RadioButton rbAdministrator;

	@FXML
	private RadioButton rbTrainee;

	@FXML
	private RadioButton rbInstructor;

	public LogingController() {
		context = null;
		primaryController = new PrimaryController();
	}

	@FXML
	public void goToMainScreen() throws IOException {
		String screenAddress = "/fxml/MainScreen.fxml";
		MainController controller = new MainController();
		primaryController.setController(controller);
		primaryController.setScreenAddress(screenAddress);
		changeController(primaryController);
	}

	@FXML
	public void goToMenuScreen() throws IOException, SQLException {
		assertDataValidForLogin();
		if (rbAdministrator.isSelected()) {
			account = new Admin();
			context = new Context(new AdminController());
		}

		if (rbInstructor.isSelected()) {
			account = new Instructor();
			context = new Context(new InstructorController());
		}

		if (rbTrainee.isSelected()) {
			account = new Trainee();
			context = new Context(new TraineeController());
		}
		account.setLogin(tfUserName.getText().trim());
		account.setPassword(pfPassword.getText().trim());

		Operation operation = new Operation();
		operation.logging(account);

		DialogsUtils.showLastDataLogin(account);
		Controller controller = context.menuController(account);
		String screenAddress = account.getAddressMainCountroller();
		primaryController.setController(controller);
		primaryController.setScreenAddress(screenAddress);
		changeController(primaryController);
	}

	private void assertDataValidForLogin() {
		if (AssertUtils.isEmpty(tfUserName.getText())) {
			throw new InvalidLoginException("Login is empty");
		}
		if (AssertUtils.isEmpty(pfPassword.getText())) {
			throw new InvalidPasswordException("Password is empty");
		}
	}

	@Override
	public void changeController(PrimaryController controller) throws IOException {
		primaryStage = (Stage) btnLogin.getScene().getWindow();
		primaryController.setPrimaryStage(primaryStage);
		primaryController.changeController();

	}
}
