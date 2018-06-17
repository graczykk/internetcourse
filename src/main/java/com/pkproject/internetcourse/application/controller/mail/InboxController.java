package com.pkproject.internetcourse.application.controller.mail;

import com.pkproject.internetcourse.application.account.Account;
import com.pkproject.internetcourse.application.controller.controllerinterfaces.Controller;
import com.pkproject.internetcourse.application.controller.primary.PrimaryController;
import com.pkproject.internetcourse.application.datebase.MailOperation;
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
public class InboxController implements Controller {
    private Account account;
    private MailOperation mailOperation;
    ArrayList<Mail> mails;
    ObservableList<Mail> mailObservableList;


    public void setAccount(Account account) throws SQLException {
        this.account = account;
        initOutbox();
    }

    public void initOutbox() throws SQLException {
        mailOperation = new MailOperation();
        mails = mailOperation.inboxList(account);
        mailObservableList.addAll(mails);
    }

    @FXML
    public void initialize() {
        mailObservableList = FXCollections.observableArrayList();
        chbSubject.setItems(mailObservableList);
    }

    @FXML
    private ChoiceBox<Mail> chbSubject;

    @FXML
    private TextArea taTextMessage;

    @FXML
    private Label lEmail;

    @FXML
    private Button btnLogout;

    @FXML
    void showMessage() {
        if(!chbSubject.getSelectionModel().isEmpty()) {
            taTextMessage.setText(chbSubject.getValue().getTextMessage());
            lEmail.setText(chbSubject.getValue().getEmail());
        }
    }

    @Override
    public void changeController(PrimaryController controller) throws IOException {

    }
}
