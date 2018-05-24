package com.pkproject.internetcourse.application.authorization;

import com.pkproject.internetcourse.application.account.Account;
import com.pkproject.internetcourse.application.applicationinterfaces.AuthorizationState;
import com.pkproject.internetcourse.application.datebase.Operation;

import java.sql.SQLException;


/**
 * Created by Piotr Fudala on 14.01.2017.
 */
public class AuthorizationContext {
    private Operation operation;
    private int counterAuthorization = 0;
    private AuthorizationState state;

    public AuthorizationContext() {
        this.operation = new Operation();
    }

    public void checkState(Account account) throws SQLException {
            operation.logging(account);
    }


    public AuthorizationState getState() {
        return state;
    }

    public void setState(AuthorizationState state) {
        this.state = state;
    }



}
