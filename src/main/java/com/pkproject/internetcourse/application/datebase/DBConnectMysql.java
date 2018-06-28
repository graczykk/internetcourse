package com.pkproject.internetcourse.application.datebase;


import com.atomikos.jdbc.AtomikosDataSourceBean;
import com.mysql.cj.jdbc.MysqlXADataSource;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by Piotr Fudala
 */
public class DBConnectMysql {

    private static AtomikosDataSourceBean ds;
    private static MysqlXADataSource mysqlXADataSource;

    static final String DB_URL = "jdbc:mysql://localhost/";
    static final String USER = "root";
    static final String PASS = "root";

    public static Connection getConnection() {
        if (mysqlXADataSource == null) {
            mysqlXADataSource = new MysqlXADataSource();
            mysqlXADataSource.setUrl(DB_URL);
            mysqlXADataSource.setUser(USER);
            mysqlXADataSource.setPassword(PASS);
            try {
                mysqlXADataSource.setPinGlobalTxToPhysicalConnection(true);
                mysqlXADataSource.setServerTimezone("UTC");
            } catch (SQLException e) {
                e.printStackTrace();
            }

            ds = new AtomikosDataSourceBean();
            ds.setUniqueResourceName("mysql");
            ds.setXaDataSource(mysqlXADataSource);
            ds.setMinPoolSize(5);
            ds.setMaxPoolSize(50);
        }

        try {
            return ds.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

}
