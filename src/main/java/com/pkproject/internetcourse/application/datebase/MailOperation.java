package com.pkproject.internetcourse.application.datebase;

import com.pkproject.internetcourse.application.account.Account;
import com.pkproject.internetcourse.application.account.Trainee;
import com.pkproject.internetcourse.application.mail.Mail;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by Piotr Fudala on 21.12.2016.
 */
public class MailOperation {
    private DBConnect dbConnect;
    private PreparedStatement preparedStatement;
    private Connection connection;
    private String query;

    public MailOperation() {
        dbConnect = new DBConnect();
        connection = dbConnect.getConnection();
    }
    public ArrayList<Mail> inboxList(Account account) throws SQLException {
        ArrayList<Mail> mails = new ArrayList<>();

        query = "SELECT w.nadawca, w.tematWiadomosci, w.trescWiadomosci FROM `internetcourse`.`wiadomosc` as w" +
                " join `internetcourse`.konto as k on k.idKonto = w.idKonto";

        preparedStatement = connection.prepareStatement(query);
        ResultSet resultSet = preparedStatement.executeQuery();

        while(resultSet.next()) {
            if(account.getEmail().equals(resultSet.getString("nadawca"))) {
                Mail mail = new Mail();
                mail.setSubject(resultSet.getString("tematWiadomosci"));
                mail.setTextMessage(resultSet.getString("trescWiadomosci"));
                mail.setEmail(resultSet.getString("nadawca"));
                mails.add(mail);
            }
        }

        return mails;
    }

    public ArrayList<Mail> outboxList(Account account) throws SQLException {
        ArrayList<Mail> mails = new ArrayList<>();

        query = "SELECT w.odbiorca, w.tematWiadomosci, w.trescWiadomosci FROM `internetcourse`.`wiadomosc` as w" +
                " join `internetcourse`.konto as k on k.idKonto = w.idKonto";

        preparedStatement = connection.prepareStatement(query);
        ResultSet resultSet = preparedStatement.executeQuery();
        while(resultSet.next()) {
            if(account.getEmail().equals(resultSet.getString("odbiorca"))) {
                Mail mail = new Mail();
                mail.setSubject(resultSet.getString("tematWiadomosci"));
                mail.setTextMessage(resultSet.getString("trescWiadomosci"));
                mail.setEmail(resultSet.getString("odbiorca"));
                mails.add(mail);
            }
        }

        return mails;
    }

    public ArrayList<String> listMail() throws SQLException {
        ArrayList<String> mails = new ArrayList<>();

        query = "select daneosobiste.Email, daneosobiste.NazwaUzytkownika from `internetcourse`.`konto` " +
                "join `internetcourse`.`daneosobiste` " +
                "on  daneosobiste.idKonto = konto.idKonto";

        preparedStatement = connection.prepareStatement(query);
        ResultSet resultSet = preparedStatement.executeQuery();

        while(resultSet.next()) {
            mails.add(resultSet.getString("Email"));
            mails.add(resultSet.getString("NazwaUzytkownika"));
        }

        return mails;
    }

    public boolean insertMessage(Account account, Mail mail) throws SQLException {
        query = "INSERT INTO `internetcourse`.`wiadomosc`(`idKonto`, `nadawca`, `odbiorca`, `tematWiadomosci`, `trescWiadomosci`) " +
                "VALUES (?, ?, ?, ?, ?)";

        preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, account.getIdAccount());
        preparedStatement.setString(2, account.getEmail());
        preparedStatement.setString(3, mail.getEmail());
        preparedStatement.setString(4, mail.getSubject());
        preparedStatement.setString(5, mail.getTextMessage());
        preparedStatement.execute();

        return true;
    }
}
