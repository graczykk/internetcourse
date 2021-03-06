package com.pkproject.internetcourse.application.main;

import com.atomikos.icatch.jta.UserTransactionFactory;
import com.atomikos.icatch.jta.UserTransactionManager;
import com.mysql.cj.jdbc.MysqlXid;
import com.pkproject.internetcourse.application.controller.MainController;
import com.pkproject.internetcourse.application.datebase.*;
import com.pkproject.internetcourse.application.utils.DialogsUtils;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import javax.transaction.*;
import javax.transaction.xa.XAResource;
import javax.transaction.xa.Xid;
import java.lang.Thread.UncaughtExceptionHandler;
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by  on 31.10.2016.
 */
public class InternetCourseMain extends Application {


    public InternetCourseMain() throws Exception {

        Connection connectionMysql = DBConnectMysql.getConnection();
        DBCreateMysql mysqlCreate = new DBCreateMysql(connectionMysql);
        mysqlCreate.createDB();
        connectionMysql.close();

        Connection connectionPostgres = DBConnectPostgres.getConnection();
        new DBCreatePostgres(connectionPostgres).createDB();
        connectionPostgres.close();

    }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Thread.setDefaultUncaughtExceptionHandler(new ExceptionHandler());
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/fxml/MainScreen.fxml"));
        MainController controller = new MainController();
        loader.setController(controller);

        AnchorPane anchorPane = loader.load();
        Scene scene = new Scene(anchorPane);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }

    class ExceptionHandler implements UncaughtExceptionHandler {

        @Override
        public void uncaughtException(Thread t, Throwable e) {
            if (e.getCause() instanceof InvocationTargetException) {
                InvocationTargetException ex = (InvocationTargetException) e.getCause();
                DialogsUtils.errorMessageDialog(ex.getTargetException().getLocalizedMessage());
            } else {
                DialogsUtils.errorMessageDialog(e.getCause().getLocalizedMessage());
            }
        }

    }
}
