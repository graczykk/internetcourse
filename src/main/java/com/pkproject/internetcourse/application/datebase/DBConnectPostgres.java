package com.pkproject.internetcourse.application.datebase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnectPostgres {
    String JDBC_DRIVER = "org.postgresql.Driver";
    String DB_URL = "jdbc:postgresql:postgres";
    String USER = "postgres";
    String PASS = "postgres";
    private Connection connection;

    public Connection getConnection() {
        try {
            Class.forName(JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL, USER, PASS);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return connection;
    }

}
