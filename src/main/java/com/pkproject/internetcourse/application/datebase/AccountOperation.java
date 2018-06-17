package com.pkproject.internetcourse.application.datebase;

import com.pkproject.internetcourse.application.account.Account;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Piotr Fudala on 21.01.2017.
 */
public class AccountOperation {
    private DBConnect dbConnect;
    private PreparedStatement preparedStatement;
    private Connection connection;
    private String query;

    public AccountOperation() {
        dbConnect = new DBConnect();
        connection = dbConnect.getConnection();
    }

    public void deleteAccount(int id) throws SQLException {
        query = "delete from `internetcourse`.rozwiazanytest where idKonto=?";
        preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, id);
        preparedStatement.execute();

        query = "delete from `internetcourse`.daneosobiste where idKonto = ?";
        preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, id);
        preparedStatement.execute();

        query = "delete from `internetcourse`.log where idKonto = ?";
        preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, id);
        preparedStatement.execute();

        query = "delete from `internetcourse`.konto where idKonto = ?";
        preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, id);
        preparedStatement.execute();
    }

}