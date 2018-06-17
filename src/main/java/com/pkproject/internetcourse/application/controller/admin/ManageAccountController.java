package com.pkproject.internetcourse.application.controller.admin;

import com.pkproject.internetcourse.application.account.Account;
import com.pkproject.internetcourse.application.account.Instructor;
import com.pkproject.internetcourse.application.account.Trainee;
import com.pkproject.internetcourse.application.controller.controllerinterfaces.Controller;
import com.pkproject.internetcourse.application.controller.primary.PrimaryController;
import com.pkproject.internetcourse.application.controller.settings.AccountSettingsController;
import com.pkproject.internetcourse.application.datebase.AccountOperation;
import com.pkproject.internetcourse.application.datebase.Operation;
import com.pkproject.internetcourse.application.dialogs.DialogsUtils;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;

/**
 * Created by Piotr Fudala on 14.11.2016.
 */
public class ManageAccountController implements Controller {
    private PrimaryController primaryController;

    public ManageAccountController(Account account) {
        this.account = account;
        accounts = new ArrayList<Account>();
        primaryController = new PrimaryController();
    }

    private Stage primaryStage;
    private Account account;
    private ArrayList<Account> accounts;
    ObservableList<Item> data;
    ObservableList<KeyValuePair> keyValue;

    @FXML
    private RadioButton rbTrainee;

    @FXML
    private RadioButton rbInstructor;

    @FXML
    private Button btnBack;

    @FXML
    private RadioButton rbChangeTypeAccount;

    @FXML
    private TableView<Item> tbAccount;

    @FXML
    private TableColumn<Item, Integer> idAccount;

    @FXML
    private TableColumn<Item, String> userName;

    @FXML
    private TableColumn<Item, String> blockAccount;


    @FXML
    private ChoiceBox<KeyValuePair> chbIdAccount;

    @FXML
    private RadioButton rbLockAccount;

    @FXML
    private RadioButton rbUnlockAccount;

    @FXML
    private RadioButton rbDeleteAccount;

    @FXML
    public void initialize() {
        idAccount.setCellValueFactory(new PropertyValueFactory<Item, Integer>("id"));
        blockAccount.setCellValueFactory(new PropertyValueFactory<Item, String>("blockAccount"));
        userName.setCellValueFactory(new PropertyValueFactory<Item, String>("userName"));
        data = FXCollections.observableArrayList();
        keyValue = FXCollections.observableArrayList();
        tbAccount.setItems(data);
        chbIdAccount.setItems(keyValue);
    }

    @FXML
    public void searchData() throws SQLException {
        data.clear();
        keyValue.clear();
        Operation operation = new Operation();

        if(rbInstructor.isSelected()) {
            accounts = operation.instructorAccountList();
            String type = "Trainee";
            ArrayList<Item> items = new ArrayList<>();
            ArrayList<KeyValuePair> keyValuePairs = new ArrayList<>();

            for(int i = 0; i < accounts.size(); i++) {
                KeyValuePair keyValuePair = new KeyValuePair(accounts.get(i).getIdAccount(), accounts.get(i).getFullname(),type);
                keyValuePairs.add(keyValuePair);

                Item item = new Item();
                item.id.setValue(accounts.get(i).getIdAccount());
                item.blockAccount.setValue(accounts.get(i).getBlockAccount());
                item.userName.setValue(accounts.get(i).getFullname());
                items.add(item);
            }

            keyValue.addAll(keyValuePairs);
            data.addAll(items);
        }

        if(rbTrainee.isSelected()) {
            accounts = operation.traineeAccountList();
            String type = "Instructor";
            ArrayList<Item> items = new ArrayList<>();
            ArrayList<KeyValuePair> keyValuePairs = new ArrayList<>();

            for(int i = 0; i < accounts.size(); i++) {
                KeyValuePair keyValuePair = new KeyValuePair(accounts.get(i).getIdAccount(), accounts.get(i).getFullname(),type);
                keyValuePairs.add(keyValuePair);

                Item item = new Item();
                item.id.setValue(accounts.get(i).getIdAccount());
                item.blockAccount.setValue(accounts.get(i).getBlockAccount());
                item.userName.setValue(accounts.get(i).getFullname());
                items.add(item);
            }
            keyValue.addAll(keyValuePairs);
            data.addAll(items);
        }
    }

    @FXML
    void confirmOperation() throws SQLException {
        Operation operation = new Operation();

        if(!chbIdAccount.getSelectionModel().isEmpty()) {
            int id = chbIdAccount.getValue().getKey();
            String typeAccount = chbIdAccount.getValue().getTypeAccount();
            System.out.println(typeAccount);
            if(rbLockAccount.isSelected()) {
                operation.lockAccount(id);
            }

            if(rbUnlockAccount.isSelected()) {
                operation.unlockAccount(id);
            }

            if(rbDeleteAccount.isSelected()) {
                AccountOperation accountOperation = new AccountOperation();
                accountOperation.deleteAccount(id);
            }

            if(rbChangeTypeAccount.isSelected()) {
                operation.changeAccount(id, typeAccount);
            }
        }


    }

    @FXML
    public void goToBack() throws IOException {
        String screenAddress = "/fxml/admin/AdminMenuScreen.fxml";
        AdminMenuController controller = new AdminMenuController(account);
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

    public class KeyValuePair {
        private final int key;
        private final String value;
        private  String typeAccount;

        public KeyValuePair(int key, String value, String typeAccount) {
            this.key = key;
            this.value = value;
            this.typeAccount = typeAccount;
        }
        public String getTypeAccount() {
            return typeAccount;
        }

        public int getKey()   {    return key;    }

        public String toString() {    return value;  }
    }

    public class Item {
        public SimpleLongProperty id = new SimpleLongProperty();
        public SimpleStringProperty userName = new SimpleStringProperty();
        public SimpleBooleanProperty blockAccount = new SimpleBooleanProperty();

        public String getUserName() {
            return userName.get();
        }

        public SimpleStringProperty userNameProperty() {
            return userName;
        }

        public long getId() {
            return id.get();
        }

        public boolean getBlockAccount() {
            return blockAccount.get();
        }
    }

}
