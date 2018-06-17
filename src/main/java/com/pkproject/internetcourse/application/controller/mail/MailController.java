package com.pkproject.internetcourse.application.controller.mail;

import com.pkproject.internetcourse.application.account.Account;
import com.pkproject.internetcourse.application.controller.controllerinterfaces.Controller;
import com.pkproject.internetcourse.application.controller.primary.PrimaryController;
import com.pkproject.internetcourse.application.dialogs.DialogsUtils;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Optional;

/**
 * Created by Piotr Fudala on 14.11.2016.
 */
public class MailController implements Controller{
    private Account account;

    @FXML
    private ExitController exitController;

    @FXML
    private InboxController inboxController;

    @FXML
    private MessageController messageController;

    @FXML
    private OutboxController outboxController;

    public MailController(Account account) {
        this.account = account;
    }

    @FXML
    public void initialize() throws SQLException {
        exitController.setAccount(account);
        inboxController.setAccount(account);
        messageController.setAccount(account);
        outboxController.setAccount(account);
    }

    @Override
    public void changeController(PrimaryController controller) throws IOException {

    }
}
