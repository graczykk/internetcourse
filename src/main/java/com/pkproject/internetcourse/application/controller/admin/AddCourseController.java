package com.pkproject.internetcourse.application.controller.admin;

import com.pkproject.internetcourse.application.account.Account;
import com.pkproject.internetcourse.application.controller.controllerinterfaces.Controller;
import com.pkproject.internetcourse.application.controller.primary.PrimaryController;
import com.pkproject.internetcourse.application.exceptions.EmptyDescriptionException;
import com.pkproject.internetcourse.application.exceptions.EmptyTitleException;
import com.pkproject.internetcourse.application.tuition.Course;
import com.pkproject.internetcourse.application.tuition.Test;
import com.pkproject.internetcourse.application.utils.AssertUtils;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Created by Piotr Fudala on 14.11.2016.
 */
public class AddCourseController implements Controller {
	private Stage primaryStage;
	private PrimaryController primaryController;
	private Account account;
	private Test test;
	private Course course;

	public AddCourseController(Account account, Test test) {
		this.account = account;
		primaryController = new PrimaryController();
		this.test = test;
		course = new Course(test.getLevel(), test.getName());
	}

	@FXML
	private TextArea tfText;

	@FXML
	private TextField tfSubject;

	@FXML
	private Button btnAddCourse;

	@FXML
	void goToTest() throws IOException {
		assertCourseDataValid();
		course.setContent(tfText.getText().trim());
		course.setSubject(tfSubject.getText().trim());
		String screenAddress = "/fxml/instructor/AddTestScreen.fxml";
		AddTestController controller = new AddTestController(account, test, course);
		primaryController.setController(controller);
		primaryController.setScreenAddress(screenAddress);
		changeController(primaryController);
	}

	private void assertCourseDataValid() {
		if (AssertUtils.isEmpty(tfSubject.getText())) {
			throw new EmptyTitleException("Title is empty");
		}
		if (AssertUtils.isEmpty(tfText.getText())) {
			throw new EmptyDescriptionException("Description is empty");
		}
	}

	public boolean isNoEmpty() {
		return !tfSubject.getText().isEmpty() && !tfText.getText().isEmpty();
	}

	@Override
	public void changeController(PrimaryController controller) throws IOException {
		primaryStage = (Stage) btnAddCourse.getScene().getWindow();
		primaryController.setPrimaryStage(primaryStage);
		primaryController.changeController();
	}
}
