package com.pkproject.internetcourse.application.controller.trainee;

import com.pkproject.internetcourse.application.account.Account;
import com.pkproject.internetcourse.application.account.Trainee;
import com.pkproject.internetcourse.application.controller.admin.AdminMenuController;
import com.pkproject.internetcourse.application.controller.admin.ManageAccountController;
import com.pkproject.internetcourse.application.controller.controllerinterfaces.Controller;
import com.pkproject.internetcourse.application.controller.mail.MailController;
import com.pkproject.internetcourse.application.controller.primary.PrimaryController;
import com.pkproject.internetcourse.application.controller.settings.AccountSettingsController;
import com.pkproject.internetcourse.application.datebase.TuitionOperation;
import com.pkproject.internetcourse.application.dialogs.DialogsUtils;
import com.pkproject.internetcourse.application.menu.TraineeController;
import com.pkproject.internetcourse.application.tuition.Course;
import com.pkproject.internetcourse.application.tuition.Test;
import com.pkproject.internetcourse.application.tuition.Tuition;
import javafx.beans.property.SimpleLongProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;

/**
 * Created by Piotr Fudala on 14.11.2016.
 */
public class ChoiceCourseController implements Controller{
    private Stage primaryStage;
    private Account account;
    private Tuition tuition = new Tuition();
    private PrimaryController primaryController;
    private TuitionOperation tuitionOperation;
    private Course course;
    ObservableList<Test> testObservableList;

    private ArrayList<Test> tests;

    public ChoiceCourseController(Account account) {
        this.account = account;
        primaryController = new PrimaryController();
        tuitionOperation = new TuitionOperation();
        tests = new ArrayList<>();
    }

    @FXML
    private RadioButton rbBasicLevel;

    @FXML
    private ToggleGroup tgCourseLevel;

    @FXML
    private RadioButton rbAdvancedLevel;

    @FXML
    private ChoiceBox<Test> chbTuition;

    @FXML
    private Button btnBack;

    @FXML
    public void initialize() {
        testObservableList = FXCollections.observableArrayList();
        chbTuition.setItems(testObservableList);
    }

    @FXML
    void goToTuition() throws IOException, SQLException {

        if(!chbTuition.getSelectionModel().isEmpty()) {
            int idTest = chbTuition.getValue().getIdTest();

            Test test = tests.stream()
                             .filter(t -> t.getIdTest() == idTest)
                             .findFirst()
                             .orElse(new Test());

            course = new Course(test.getLevel(),test.getName());
            course = tuitionOperation.getCourse(course);

            tuitionOperation.addQuestion(test.getIdTest())
                            .forEach(test::addQuestion);

            String screenAddress = "/fxml/trainee/TuitionScreen.fxml";
            TuitionController controller = new TuitionController(account, test, course);
            primaryController.setController(controller);
            primaryController.setScreenAddress(screenAddress);
            changeController(primaryController);
        }

    }

    @FXML
    void searchData() throws SQLException {
        if(rbBasicLevel.isSelected()) {
            tests = tuitionOperation.tuitionList("podstawowy");
            testObservableList.addAll(tests);
        }

        if(rbAdvancedLevel.isSelected()) {
            tests = tuitionOperation.tuitionList("zaawansowany");
            testObservableList.addAll(tests);

        }

        for(int i = 0; i < tests.size(); i++) {
            System.out.println(tests.get(i).getName());
        }
    }


    @FXML
    public void goToMenu() throws IOException {
        String screenAddress = "/fxml/trainee/TraineeMenuScreen.fxml";
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
