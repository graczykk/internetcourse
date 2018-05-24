package com.pkproject.internetcourse.application.controller;

import com.pkproject.internetcourse.application.account.Account;
import com.pkproject.internetcourse.application.account.Instructor;
import com.pkproject.internetcourse.application.account.Trainee;
import com.pkproject.internetcourse.application.controller.controllerinterfaces.Controller;
import com.pkproject.internetcourse.application.controller.primary.PrimaryController;
import com.pkproject.internetcourse.application.dialogs.DialogsUtils;
import com.pkproject.internetcourse.application.exceptions.InvalidEmailException;
import com.pkproject.internetcourse.application.exceptions.InvalidLoginException;
import com.pkproject.internetcourse.application.exceptions.InvalidNameException;
import com.pkproject.internetcourse.application.exceptions.InvalidPasswordException;
import com.pkproject.internetcourse.application.exceptions.PasswordNotMatchException;
import com.pkproject.internetcourse.application.utils.AssertUtils;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Optional;

import com.pkproject.internetcourse.application.datebase.Operation;

public class RegistrationController implements Controller {
	private PrimaryController primaryController;

	public RegistrationController() {
		primaryController = new PrimaryController();
	}

	private Account account;
	private Stage primaryStage;

	@FXML
	private TextField tfLogin;

	@FXML
	private TextField tfFullName;

	@FXML
	private TextField tfEmail;

	@FXML
	private TextField tfPassword;

	@FXML
	private TextField tfReTypePassword;

	@FXML
	private RadioButton rbTrainee;

	@FXML
	private RadioButton rbInstructor;

	@FXML
	private Button btnBack;

	@FXML
	public void goToMainScreen() throws IOException, SQLException {
		String screenAddress = "/fxml/MainScreen.fxml";
		MainController controller = new MainController();
		primaryController.setController(controller);
		primaryController.setScreenAddress(screenAddress);
		changeController(primaryController);
	}

	@FXML
	public void signUp() throws SQLException, IOException {
		assertDataValid();
		Operation operation = new Operation();

		if (rbTrainee.isSelected()) {
			account = new Trainee();
		} else {
			account = new Instructor();
		}

		account.setLogin(tfLogin.getText());
		account.setPassword(tfPassword.getText());
		account.setEmail(tfEmail.getText());
		account.setFullname(tfFullName.getText());
		account.setBlockAccount(false);
		operation.registration(account);

		String screenAddress = "/fxml/MainScreen.fxml";
		MainController controller = new MainController();
		primaryController.setController(controller);
		primaryController.setScreenAddress(screenAddress);
		changeController(primaryController);
	}

	private void assertDataValid() {
		if (AssertUtils.isEmpty(tfLogin.getText())) {
			throw new InvalidLoginException("Login is empty");
		}
		if (AssertUtils.isEmpty(tfFullName.getText())) {
			throw new InvalidNameException("Name is empty");
		}
		if (AssertUtils.isEmpty(tfEmail.getText())) {
			throw new InvalidEmailException("Email address is empty");
		}
		if (AssertUtils.isEmailInvalid(tfEmail.getText())) {
			throw new InvalidEmailException("Email not matches regexp ^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}");
		}
		if (AssertUtils.isEmpty(tfPassword.getText())) {
			throw new InvalidPasswordException("Password is empty");
		}
		if (AssertUtils.isEmpty(tfReTypePassword.getText())) {
			throw new InvalidPasswordException("Retyped password is empty");
		}
		if (!tfReTypePassword.getText().equals(tfPassword.getText())) {
			throw new PasswordNotMatchException("Password and retyped password not match");
		}
	}

	@Override
	public void changeController(PrimaryController controller) throws IOException {
		primaryStage = (Stage) btnBack.getScene().getWindow();
		primaryController.setPrimaryStage(primaryStage);
		primaryController.changeController();

	}
}
