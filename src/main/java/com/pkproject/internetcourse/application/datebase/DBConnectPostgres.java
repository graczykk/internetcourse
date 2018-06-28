package com.pkproject.internetcourse.application.datebase;

import com.atomikos.jdbc.AtomikosDataSourceBean;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public class DBConnectPostgres {

    private static AtomikosDataSourceBean ds;
    static final String USER = "postgres";
    static final String PASS = "postgres";

    public static Connection getConnection() {
        if (ds == null) {
            ds = new AtomikosDataSourceBean();
            ds.setUniqueResourceName("postgres");
            ds.setXaDataSourceClassName("org.postgresql.xa.PGXADataSource");
            Properties p = new Properties();
            p.setProperty ( "user" , USER );
            p.setProperty ( "password" , PASS );
            p.setProperty ( "serverName" , "localhost" );
            p.setProperty ( "portNumber" , "5432" );
            p.setProperty ( "databaseName" , "postgres" );
            ds.setXaProperties ( p );
            ds.setMaxPoolSize(50);
            ds.setMinPoolSize(5);
        }
        try {
            ds.getConnection();
            return ds.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

}
