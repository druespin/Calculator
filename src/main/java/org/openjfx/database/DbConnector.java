package org.openjfx.database;

import javafx.concurrent.Service;
import javafx.concurrent.Task;

import java.sql.*;

public class DbConnector extends Task {

    static final String DB_URL = "jdbc:postgresql://localhost:5432/calculator";
    static final String USERNAME = "drue";
    static final String PASSWORD = "pass";


    @Override
    protected Connection call() throws Exception {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
    }
}
