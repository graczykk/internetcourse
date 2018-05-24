package com.pkproject.internetcourse.application.authorization;

import com.pkproject.internetcourse.application.applicationinterfaces.AuthorizationState;

/**
 * Created by Piotr Fudala on 22.12.2016.
 */
public class AuthorizationIncorrect implements AuthorizationState{
    @Override
    public void checkAthorization(String login, String password) {

    }
}
