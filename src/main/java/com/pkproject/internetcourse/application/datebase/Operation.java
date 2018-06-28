package com.pkproject.internetcourse.application.datebase;

import com.pkproject.internetcourse.application.account.Account;
import com.pkproject.internetcourse.application.account.Trainee;
import com.pkproject.internetcourse.application.exceptions.AuthenticationFailedException;
import com.pkproject.internetcourse.application.exceptions.LoginAlreadyUsedException;

import java.sql.*;
import java.util.ArrayList;

/**
 * Created by Piotr Fudala on 21.12.2016.
 */
public class Operation {
    private DBConnectMysql dbConnectMysql;
    private PreparedStatement preparedStatement;
    private Connection connection;
    private String query;

    public Operation() {
        dbConnectMysql = new DBConnectMysql();
        try {
            connection = dbConnectMysql.getConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void lockAccount(int id) throws SQLException {
        query = " update `internetcourse`.`konto` SET BlokadaKonta=true where idKonto=" + id;
        preparedStatement = connection.prepareStatement(query);
        preparedStatement.executeUpdate();
    }

    public void unlockAccount(int id) throws SQLException {
        query = " update `internetcourse`.`konto` SET BlokadaKonta=false where idKonto=" + id;
        preparedStatement = connection.prepareStatement(query);
        preparedStatement.executeUpdate();
    }

    public Account findByLogin(String login) throws SQLException {
    	Account account = null;
    	query=  "SELECT * FROM `internetcourse`.`konto` as k\n" +
                 "join `internetcourse`.`daneosobiste` as do on  do.idKonto = k.idKonto\n" +
                 "where k.Login=?";
         preparedStatement = connection.prepareStatement(query);
         preparedStatement.setString(1, login);

         ResultSet resultSet = preparedStatement.executeQuery();
         while (resultSet.next()) {
        	 account = new Account();
             account.setIdAccount(resultSet.getInt("idKonto"));
             account.setFullname(resultSet.getString("NazwaUzytkownika"));
             account.setEmail((resultSet.getString("Email")));
             account.setCheckLogin(true);
         }
         return account;
    }

    public void registration(Account account) throws SQLException {
    	assertLoginNotUsed(account.getLogin());
        query = "INSERT INTO `internetcourse`.`konto` (`Login`, `Haslo`, `RodzajKonta`, `BlokadaKonta`) VALUES (?, ?, ?, ?);";

        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, account.getLogin());
            preparedStatement.setString(2, account.getPassword());
            preparedStatement.setString(3, account.getAccountType());
            preparedStatement.setBoolean(4, account.getBlockAccount());
            preparedStatement.execute();

            query = "select konto.idKonto from `internetcourse`.`konto` where Login='" + account.getLogin() + "'";
            preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();

            int id = 0;
            while (resultSet.next()) {
                id = resultSet.getInt(1);
            }

            query = "INSERT INTO `internetcourse`.`daneOsobiste` (`idKonto`, `NazwaUzytkownika`, `Email`) VALUES (?, ?, ?)";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            preparedStatement.setString(2, account.getFullname());
            preparedStatement.setString(3, account.getEmail());
            preparedStatement.execute();


            query = "INSERT IGNORE INTO `internetcourse`.`log` (`idKonto`) VALUES (?)";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (preparedStatement != null) {
                preparedStatement.close();
            }

            if (connection != null) {
                connection.close();
            }
        }
    }

    private void assertLoginNotUsed(String login) {
    	try {
			Account account = findByLogin(login);
			if (account != null) {
				throw new LoginAlreadyUsedException("Login: " + login + " is already in use");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("Database connection problem!");
		}
    }

    public void logging(Account account) throws SQLException {
        query=  "SELECT * FROM `internetcourse`.`konto` as k\n" +
                "join `internetcourse`.`daneosobiste` as do on  do.idKonto = k.idKonto\n" +
                "join `internetcourse`.`log` as l on  l.idKonto = k.idKonto\n" +
                "where k.Login=? AND k.Haslo=? AND k.BlokadaKonta=? AND k.RodzajKonta=?" +
                "ORDER BY(l.DataLogowania) DESC limit 1";
        preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, account.getLogin());
        preparedStatement.setString(2, account.getPassword());
        preparedStatement.setBoolean(3, false);
        preparedStatement.setString(4, account.getAccountType());

        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            account.setIdAccount(resultSet.getInt("idKonto"));
            account.setFullname(resultSet.getString("NazwaUzytkownika"));
            account.setEmail((resultSet.getString("Email")));
            account.setDataLogin(resultSet.getString("DataLogowania"));
            account.setCheckLogin(true);

             query = "INSERT INTO `internetcourse`.`log` (`idKonto`) VALUES (?)";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, account.getIdAccount());
            preparedStatement.executeUpdate();
        }

        assertAuthenticationSucces(account);

    }

    private void assertAuthenticationSucces(Account account) {
		if (!account.isCheckLogin()) {
			throw new AuthenticationFailedException("Failed to authenticate user!");
		}
	}

    public Account updateLogin(Account account, String login) throws SQLException {
        query = "update `internetcourse`.`konto` SET Login='" + login + "' where idKonto=" + account.getIdAccount();
        preparedStatement = connection.prepareStatement(query);
        preparedStatement.execute(query);
        account.setLogin(login);
        return account;
    }

    public Account updateFullName(Account account, String userName) throws SQLException {
        query = "update `internetcourse`.`daneosobiste`\n" +
                "join `internetcourse`.`konto` on daneosobiste.idKonto = konto.idKonto\n" +
                "set daneosobiste.NazwaUzytkownika = '"+ userName +"'\n" +
                "where konto.idKonto=" + account.getIdAccount();
        preparedStatement = connection.prepareStatement(query);
        preparedStatement.execute(query);
        account.setFullname(userName);
        return account;
    }

    public Account updateEmail(Account account, String email) throws SQLException {
        query = "update `internetcourse`.`daneosobiste`\n" +
                "join `internetcourse`.`konto` on daneosobiste.idKonto = konto.idKonto\n" +
                "set \n daneosobiste.Email = '"+ email +"'\n" +
                "where konto.idKonto=" + account.getIdAccount();
        preparedStatement = connection.prepareStatement(query);
        preparedStatement.execute(query);
        account.setEmail(email);
        return  account;
    }

    public Account updatePassword(Account account, String password) throws SQLException {
        query = "update `internetcourse`.`konto` SET Haslo='"+ password +"' where idKonto=" + account.getIdAccount();
        preparedStatement = connection.prepareStatement(query);
        preparedStatement.execute(query);
        account.setPassword(password);
        return account;
    }

    public ArrayList<Account> instructorAccountList() throws SQLException {
        ArrayList<Account> accounts = new ArrayList<Account>();

        query = "SELECT k.idKonto,do.NazwaUzytkownika, k.BlokadaKonta FROM `internetcourse`.`konto` as k \n" +
                "join `internetcourse`.`daneosobiste` as do on  do.idKonto = k.idKonto\n" +
                "where k.RodzajKonta='Instructor'";

        preparedStatement = connection.prepareStatement(query);
        ResultSet resultSet = preparedStatement.executeQuery();

        while(resultSet.next()) {
            Account account = new Trainee();
            account.setIdAccount(resultSet.getInt("idKonto"));
            account.setBlockAccount(resultSet.getBoolean("BlokadaKonta"));
            account.setFullname(resultSet.getString("NazwaUzytkownika"));
            accounts.add(account);
        }

        return accounts;
    }

    public ArrayList<Account> traineeAccountList() throws SQLException {
        ArrayList<Account> accounts = new ArrayList<>();

        query =  "SELECT k.idKonto,do.NazwaUzytkownika, k.BlokadaKonta FROM `internetcourse`.`konto` as k \n" +
                "join `internetcourse`.`daneosobiste` as do on  do.idKonto = k.idKonto\n" +
                "where k.RodzajKonta='Trainee'";
        preparedStatement = connection.prepareStatement(query);
        ResultSet resultSet = preparedStatement.executeQuery();

        while(resultSet.next()) {
            Account account = new Trainee();
            account.setIdAccount(resultSet.getInt("idKonto"));
            account.setBlockAccount(resultSet.getBoolean("BlokadaKonta"));
            account.setFullname(resultSet.getString("NazwaUzytkownika"));
            accounts.add(account);
        }

        return accounts;
    }

    public void deleteAccount(Account account) throws SQLException {
        query = "DELETE FROM `internetcourse`.`rozwiazanytest` WHERE idWynik=?";
        preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, account.getIdAccount());
        preparedStatement.executeUpdate();

    }


    public void changeAccount(int id, String typeAccount) throws SQLException {
        query = " update `internetcourse`.`konto` SET RodzajKonta=? where idKonto="+ id;
        preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1,typeAccount);
        preparedStatement.executeUpdate();
    }


}
