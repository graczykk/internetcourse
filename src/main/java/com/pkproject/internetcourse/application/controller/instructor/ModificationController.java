package com.pkproject.internetcourse.application.controller.instructor;

import com.pkproject.internetcourse.application.account.Account;
import com.pkproject.internetcourse.application.controller.controllerinterfaces.Controller;
import com.pkproject.internetcourse.application.controller.primary.PrimaryController;
import com.pkproject.internetcourse.application.controller.trainee.TraineeMenuController;
import com.pkproject.internetcourse.application.controller.trainee.TuitionController;
import com.pkproject.internetcourse.application.datebase.TuitionOperation;
import com.pkproject.internetcourse.application.tuition.Course;
import com.pkproject.internetcourse.application.tuition.Test;
import com.pkproject.internetcourse.application.tuition.Tuition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by Piotr Fudala on 14.11.2016.
 */
public class ModificationController implements Controller{
    private Stage primaryStage;
    private Account account;
    private Tuition tuition = new Tuition();
    private PrimaryController primaryController;
    private TuitionOperation tuitionOperation;
    private Course course;
    ObservableList<Test> testObservableList;

    private ArrayList<Test> tests;

    public ModificationController(Account account) {
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
            String screenAddress = "/fxml/instructor/CourseScreen.fxml";
            CourseController controller = new CourseController(account, course);
            primaryController.setController(controller);
            primaryController.setScreenAddress(screenAddress);
            changeController(primaryController);
        }

    }

    @FXML
    void searchData() throws SQLException {
        chbTuition.getItems().clear();

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
