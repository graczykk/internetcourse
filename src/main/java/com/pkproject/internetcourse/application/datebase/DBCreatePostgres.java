package com.pkproject.internetcourse.application.datebase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DBCreatePostgres {

    private DBConnectPostgres dbConnectPostgres;
    private PreparedStatement preparedStatement;
    private Connection connection;
    private String query;

    public DBCreatePostgres() {
        dbConnectPostgres = new DBConnectPostgres();
    }

    public void createDB() throws SQLException {
        connection = dbConnectPostgres.getConnection();

        query = "CREATE SCHEMA IF NOT EXISTS questions AUTHORIZATION postgres;";
        preparedStatement = connection.prepareStatement(query);
        preparedStatement.execute();

        query = "CREATE SEQUENCE IF NOT EXISTS questions.pytanie_sq\n" +
                "    INCREMENT 1\n" +
                "    START 1\n" +
                "    MINVALUE 1\n" +
                "    MAXVALUE 9999999999\n" +
                "    CACHE 1;\n" +
                "\n" +
                "ALTER SEQUENCE questions.pytanie_sq\n" +
                "    OWNER TO postgres;";

        preparedStatement = connection.prepareStatement(query);
        preparedStatement.execute();

        query = "CREATE TABLE IF NOT EXISTS questions.\"Pytanie\"\n" +
                "(\n" +
                "    \"idPytanie\" integer NOT NULL,\n" +
                "    \"idTest\" integer NOT NULL,\n" +
                "    \"tresc\" text COLLATE pg_catalog.\"default\",\n" +
                "    \"odpowiedzA\" text COLLATE pg_catalog.\"default\",\n" +
                "    \"odpowiedzB\" text COLLATE pg_catalog.\"default\",\n" +
                "    \"odpowiedzC\" text COLLATE pg_catalog.\"default\",\n" +
                "    \"odpowiedzD\" text COLLATE pg_catalog.\"default\",\n" +
                "    \"prawidlowaOdpowiedz\" character varying(45) COLLATE pg_catalog.\"default\",\n" +
                "    \"liczbaPunktow\" character varying(45) COLLATE pg_catalog.\"default\",\n" +
                "    CONSTRAINT \"pytaniePK\" PRIMARY KEY (\"idPytanie\")\n" +
                ")\n" +
                "WITH (\n" +
                "    OIDS = FALSE\n" +
                ")\n" +
                "TABLESPACE pg_default;\n" +
                "\n" +
                "ALTER TABLE questions.\"Pytanie\"\n" +
                "    OWNER to postgres;";
        preparedStatement = connection.prepareStatement(query);
        preparedStatement.execute();

    }
}
