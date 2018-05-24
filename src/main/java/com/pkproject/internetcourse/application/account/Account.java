package com.pkproject.internetcourse.application.account;

import com.pkproject.internetcourse.application.authorization.Logging;

/**
 * Created by Piotr Fudala on 05.12.2016.
 */
public class Account {
    private int idAccount;
    private String login;
    private String fullname;
    private String email;
    private String password;
    private Logging logging;
    private boolean blockAccount;
    protected String accountType;
    protected String addressMainCountroller;
    private String dataLogin;
    private boolean checkLogin;

    public boolean isCheckLogin() {
        return checkLogin;
    }

    public void setCheckLogin(boolean checkLogin) {
        this.checkLogin = checkLogin;
    }

    public String getDataLogin() {
        return dataLogin;
    }

    public void setDataLogin(String dataLogin) {
        this.dataLogin = dataLogin;
    }

    public String getAddressMainCountroller() {
        return addressMainCountroller;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public Logging getLogging() {
        return logging;
    }

    public boolean getBlockAccount() {
        return blockAccount;
    }

    public void setBlockAccount(boolean blockAccount) {
        this.blockAccount = blockAccount;
    }

    public void setLogging(Logging logging) {
        this.logging = logging;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAccountType() {
        return accountType;
    }

    public int getIdAccount() {
        return idAccount;
    }

    public void setIdAccount(int idAccount) {
        this.idAccount = idAccount;
    }
}
