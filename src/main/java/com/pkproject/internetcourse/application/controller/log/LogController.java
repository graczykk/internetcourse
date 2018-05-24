package com.pkproject.internetcourse.application.controller.log;

import com.pkproject.internetcourse.application.account.Account;
import com.pkproject.internetcourse.application.controller.admin.AdminMenuController;
import com.pkproject.internetcourse.application.controller.controllerinterfaces.Controller;
import com.pkproject.internetcourse.application.controller.instructor.InstructorMenuController;
import com.pkproject.internetcourse.application.controller.primary.PrimaryController;
import com.pkproject.internetcourse.application.controller.trainee.ScoreController;
import com.pkproject.internetcourse.application.controller.trainee.TraineeMenuController;
import com.pkproject.internetcourse.application.datebase.LogOperation;
import com.pkproject.internetcourse.application.datebase.TuitionOperation;
import com.pkproject.internetcourse.application.tuition.Test;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by Piotr Fudala on 22.01.2017.
 */
public class LogController implements Controller{
    private Account account;
    private PrimaryController primaryController;
    private Stage primaryStage;
    ObservableList<Item> data;
    private ArrayList<String> tests;
    private LogOperation logOperation;

    public LogController(Account account) throws SQLException {
        this.account = account;
        primaryController = new PrimaryController();
        TuitionOperation tuitionOperation = new TuitionOperation();
        logOperation = new LogOperation();
        tests = logOperation.getAllLogs(account);
    }


    @FXML
    private Button btnBack;

    @FXML
    private TableView<Item> tvScore;

    @FXML
    private TableColumn<Item, String> tcData;

    @FXML
    private TableColumn<Item, Integer> tcNumber;

    @FXML
    public void initialize() {
        tcData.setCellValueFactory(new PropertyValueFactory<Item, String>("name"));
        tcNumber.setCellValueFactory(new PropertyValueFactory<Item, Integer>("result"));
        data = FXCollections.observableArrayList();
        tvScore.setItems(data);

        ArrayList<Item> items = new ArrayList<>();

        for(int i= 0; i < tests.size(); i++) {
            Item item = new Item();
            item.name.setValue(tests.get(i));
            item.result.setValue(i + 1);
            items.add(item);
        }

        data.addAll(items);
    }

    @FXML
    void goToMenu() throws IOException {


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
        primaryStage = (Stage) btnBack.getScene().getWindow();
        primaryController.setPrimaryStage(primaryStage);
        primaryController.changeController();
    }

    public class Item {
        public SimpleStringProperty name = new SimpleStringProperty();
        public SimpleIntegerProperty result = new SimpleIntegerProperty();

        public String getName() {
            return name.get();
        }

        public void setName(String name) {
            this.name.set(name);
        }

        public int getResult() {
            return result.get();
        }

        public void setResult(int result) {
            this.result.set(result);
        }
    }
}
