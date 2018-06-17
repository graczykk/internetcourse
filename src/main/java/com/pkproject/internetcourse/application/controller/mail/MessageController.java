package com.pkproject.internetcourse.application.controller.mail;

import com.pkproject.internetcourse.application.account.Account;
import com.pkproject.internetcourse.application.controller.admin.ManageAccountController;
import com.pkproject.internetcourse.application.controller.controllerinterfaces.Controller;
import com.pkproject.internetcourse.application.controller.primary.PrimaryController;
import com.pkproject.internetcourse.application.datebase.MailOperation;
import com.pkproject.internetcourse.application.datebase.Operation;
import com.pkproject.internetcourse.application.dialogs.DialogsUtils;
import com.pkproject.internetcourse.application.mail.Mail;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;

/**
 * Created by Piotr Fudala on 14.11.2016.
 */
public class MessageController implements Controller{
    private Account account;
    private Mail mail = new Mail();
    private MailOperation mailOperation;
    ArrayList<String> userNames;

    public void setAccount(Account account) throws SQLException {
        this.account = account;
        mailOperation  = new MailOperation();
        initMail();
    }

    public void initMail() throws SQLException {
        userNames = mailOperation.listMail();
        keyValue.addAll(userNames);
    }

    @FXML
    private TextArea tfText;

    @FXML
    private TextField tfSubject;

    @FXML
    private ChoiceBox<String> chbEmail;

    ObservableList<String> keyValue;

    @FXML
    public void initialize() throws SQLException {
        keyValue = FXCollections.observableArrayList();
        chbEmail.setItems(keyValue);
    }

    @FXML
    void cancelOperation() {
        tfText.clear();
        tfSubject.clear();
    }

    @FXML
    void sendMessage() throws SQLException {
        if(isNull()) {
            mail.setTextMessage(chbEmail.getValue());
            mail.setSubject(tfSubject.getText().trim());
            mail.setTextMessage(tfText.getText().trim());

            if(mailOperation.insertMessage(account, mail)) {
                tfText.clear();
                tfSubject.clear();
            }
        }
    }

    public boolean isNull() {
        return !chbEmail.getSelectionModel().isEmpty() && !(tfSubject.getText().trim().isEmpty() || tfText.getText().trim().isEmpty());
    }

    @Override
    public void changeController(PrimaryController controller) throws IOException {

    }
}
