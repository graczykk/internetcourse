package com.pkproject.internetcourse.application.account;

/**
 * Created by Piotr Fudala on 05.12.2016.
 */
public class Instructor extends Account {

    public Instructor() {
        accountType = "Instructor";
        addressMainCountroller = "/fxml/instructor/InstructorMenuScreen.fxml";
    }
}
