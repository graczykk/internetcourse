package com.pkproject.internetcourse.application.datebase;

import com.pkproject.internetcourse.application.account.Account;
import com.pkproject.internetcourse.application.mail.Mail;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by Piotr Fudala on 21.12.2016.
 */
public class LogOperation {
    private DBConnect dbConnect;
    private PreparedStatement preparedStatement;
    private Connection connection;
    private String query;

    public LogOperation() {
        dbConnect = new DBConnect();
        connection = dbConnect.getConnection();
    }

    public ArrayList<String> getAllLogs(Account account) throws SQLException {
        ArrayList<String> allLogs = new ArrayList<>();
        query = "SELECT * FROM `internetcourse`.`konto` as k\n" +
                "join `internetcourse`.`log` as l on  l.idKonto = k.idKonto\n" +
                "where k.idKonto= ? " +
                "GROUP BY (DataLogowania) ASC";
        preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1,account.getIdAccount());
        ResultSet resultSet = preparedStatement.executeQuery();

        while(resultSet.next()) {
            allLogs.add(resultSet.getString("DataLogowania"));
        }

        return allLogs;
    }
}
