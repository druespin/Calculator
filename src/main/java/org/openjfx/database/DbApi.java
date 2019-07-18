package org.openjfx.database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class DbApi {

    static Connection dbConnection = null;
    static Statement statement = null;

    public static void createTable() throws SQLException {

        String createTableSQL = "CREATE TABLE CALCULATIONS("
                + "ID BIGINT PRIMARY KEY, "
                + "operation VARCHAR(20) NOT NULL, "
                + "created_date DATE NOT NULL)";

        try {
            dbConnection = new DbConnector().call();
            statement = dbConnection.createStatement();

            // выполнить SQL запрос
            statement.execute(createTableSQL);
            System.out.println("Table is created!");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            if (statement != null) {
                statement.close();
            }
            if (dbConnection != null) {
                dbConnection.close();
            }
        }
    }

    public static void insertToDB(String entry) throws SQLException {

        String insertSQL = "INSERT INTO CALCULATIONS "
                                + "(operation, created_date)"
                                + " VALUES (" + entry + ","
                                + new Date().toString() + ")";

        try {
            statement.execute(insertSQL);
        }
        catch (SQLException e)  {}
    }

    public static List<String> get10LatestEntries() throws SQLException {

        List<String> list = new ArrayList<>();
        String selectSQL = "SELECT operation FROM CALCULATIONS DESC LIMIT 10";
        ResultSet rs = statement.executeQuery(selectSQL);

        while (rs.next())
        {
            list.add(rs.getString("operation"));
        }
        return list;
    }
}

