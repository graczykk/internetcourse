package com.pkproject.internetcourse.application.main;

import com.pkproject.internetcourse.application.controller.MainController;
import com.pkproject.internetcourse.application.datebase.DBCreateMysql;
import com.pkproject.internetcourse.application.datebase.DBCreatePostgres;
import com.pkproject.internetcourse.application.utils.DialogsUtils;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.lang.Thread.UncaughtExceptionHandler;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;

/**
 * Created by  on 31.10.2016.
 */
public class InternetCourseMain extends Application{

	public InternetCourseMain() throws SQLException {
		new DBCreateMysql().createDB();
		new DBCreatePostgres().createDB();
	}

    public static void main(String [] args) {
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
