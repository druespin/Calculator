package org.openjfx.database;

import java.sql.*;


/**
 * Database connection
 */
public class DbConnector {

    static final String DB_URL = "jdbc:postgresql://localhost:5432/calculator";
    static final String USERNAME = "user";
    static final String PASSWORD = "userpass";


    protected Connection connect() throws Exception {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
    }
}
