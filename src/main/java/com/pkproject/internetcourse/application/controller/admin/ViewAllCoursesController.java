package com.pkproject.internetcourse.application.controller.admin;

import com.pkproject.internetcourse.application.account.Account;
import com.pkproject.internetcourse.application.controller.controllerinterfaces.Controller;
import com.pkproject.internetcourse.application.controller.instructor.InstructorMenuController;
import com.pkproject.internetcourse.application.controller.primary.PrimaryController;
import com.pkproject.internetcourse.application.controller.trainee.TraineeMenuController;
import com.pkproject.internetcourse.application.datebase.LogOperation;
import com.pkproject.internetcourse.application.datebase.TuitionOperation;
import com.pkproject.internetcourse.application.tuition.Course;
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
public class ViewAllCoursesController implements Controller{
    private Account account;
    private PrimaryController primaryController;
    private Stage primaryStage;
    ObservableList<Item> data;
    private LogOperation logOperation;
    private ArrayList<Test> tuition;
    private ArrayList<Course> courses;
    public ViewAllCoursesController(Account account) throws SQLException {
        this.account = account;
        primaryController = new PrimaryController();
        TuitionOperation tuitionOperation = new TuitionOperation();
        logOperation = new LogOperation();
        tuition = tuitionOperation.testAllList();


        for(int i = 0; i < tuition.size(); i++) {
            System.out.println(tuition.get(i).getName());
        }
    }


    @FXML
    private Button btnBack;

    @FXML
    private TableView<Item> tvScore;

    @FXML
    private TableColumn<Item, Integer> tcNumber;

    @FXML
    private TableColumn<Item, String> tcName;

    @FXML
    private TableColumn<Item, String> tcLevel;

    @FXML
    private TableColumn<Item, String> tcAuthor;

    @FXML
    public void initialize() {
        tcNumber.setCellValueFactory(new PropertyValueFactory<Item, Integer>("result"));
        tcName.setCellValueFactory(new PropertyValueFactory<Item, String>("name"));
        tcLevel.setCellValueFactory(new PropertyValueFactory<Item, String>("level"));
        tcAuthor.setCellValueFactory(new PropertyValueFactory<Item, String>("autor"));

        data = FXCollections.observableArrayList();
        tvScore.setItems(data);

        ArrayList<Item> items = new ArrayList<>();
        int j = 0;
        for(int i= 0; i < tuition.size(); i++) {
            Item item = new Item();
            item.result.setValue(i + 1);


            if(j < tuition.size()) {
                item.name.setValue(tuition.get(i).getName());
                item.level.setValue(tuition.get(i).getLevel());
                item.autor.setValue(tuition.get(i).getAuthor());
                j++;
            }
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
        public SimpleIntegerProperty result = new SimpleIntegerProperty();
        public SimpleStringProperty  name = new SimpleStringProperty();
        public SimpleStringProperty  level = new SimpleStringProperty();
        public SimpleStringProperty  autor = new SimpleStringProperty();

        public String getAutor() {
            return autor.get();
        }

        public SimpleStringProperty autorProperty() {
            return autor;
        }

        public String getLevel() {
            return level.get();
        }

        public SimpleStringProperty levelProperty() {
            return level;
        }

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
