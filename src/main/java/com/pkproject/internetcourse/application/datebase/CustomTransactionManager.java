package com.pkproject.internetcourse.application.datebase;

import com.atomikos.icatch.jta.UserTransactionManager;

import javax.transaction.SystemException;
import javax.transaction.TransactionManager;
import java.util.Properties;

public class CustomTransactionManager {

    private static UserTransactionManager utm;

    public static synchronized TransactionManager getTransactionManager() {
        if (utm == null) {
            utm = new UserTransactionManager();
            try {
                utm.init();
            } catch (SystemException e) {
                e.printStackTrace();
            }
        }
        return utm;
    }


}
