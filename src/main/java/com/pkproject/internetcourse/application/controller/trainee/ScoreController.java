package com.pkproject.internetcourse.application.controller.trainee;

import com.pkproject.internetcourse.application.account.Account;
import com.pkproject.internetcourse.application.controller.admin.ManageAccountController;
import com.pkproject.internetcourse.application.controller.controllerinterfaces.Controller;
import com.pkproject.internetcourse.application.controller.primary.PrimaryController;
import com.pkproject.internetcourse.application.datebase.TuitionOperation;
import com.pkproject.internetcourse.application.tuition.Test;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleLongProperty;
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
 * Created by Piotr Fudala on 19.01.2017.
 */
public class ScoreController implements Controller{
    private Account account;
    private PrimaryController primaryController;
    private Stage primaryStage;
    ObservableList<Item> data;
    private ArrayList<Test> tests;

    public ScoreController(Account account) throws SQLException {
        this.account = account;
        primaryController = new PrimaryController();
        TuitionOperation tuitionOperation = new TuitionOperation();
        tests = tuitionOperation.completeResultTest(account);
    }


    @FXML
    private Button btnBack;

    @FXML
    private TableView<Item> tvScore;

    @FXML
    private TableColumn<Item, String> tcName;

    @FXML
    private TableColumn<Item, Integer> tcResult;

    @FXML
    public void initialize() {
        tcName.setCellValueFactory(new PropertyValueFactory<Item, String>("name"));
        tcResult.setCellValueFactory(new PropertyValueFactory<Item, Integer>("result"));
        data = FXCollections.observableArrayList();
        tvScore.setItems(data);

        ArrayList<Item> items = new ArrayList<>();

        Item item = new Item();

        for(int i= 0; i < tests.size(); i++) {
            item.name.setValue(tests.get(i).getName());
            item.result.setValue(tests.get(i).getResult());
            items.add(item);
        }

        data.addAll(items);
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
