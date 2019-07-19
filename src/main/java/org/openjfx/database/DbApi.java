package org.openjfx.database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class DbApi {

    private Connection dbConnection = null;
    private Statement statement = null;

    public void createTable() throws SQLException {

        String createTableSQL = "CREATE TABLE IF NOT EXISTS history ("
                + "id bigint PRIMARY KEY GENERATED ALWAYS AS IDENTITY, "
                + "operation varchar(20) NOT NULL);";

        try {
            dbConnection = new DbConnector().connect();
            statement = dbConnection.createStatement();

            // выполнить SQL запрос
            statement.execute(createTableSQL);
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void insertToDB(String entry) throws SQLException {

        String insertSQL = "INSERT INTO history "
                                + "(operation) "
                                + "VALUES (\'" + entry + "\');";


            statement.execute(insertSQL);
    }

    public List<String> getAllEntries() throws SQLException {

        List<String> list = new ArrayList<>();
        String selectSQL = "SELECT operation FROM history;";
        ResultSet rs = statement.executeQuery(selectSQL);

        while (rs.next())
        {
            list.add(rs.getString("operation"));
        }
        return list;
    }

    public List<String> get10LatestEntries() throws SQLException {

        List<String> list = new ArrayList<>();

        String selectSQL = "SELECT operation FROM history ORDER BY id DESC LIMIT 10;";
        ResultSet rs = statement.executeQuery(selectSQL);

        while (rs.next())
        {
            list.add(rs.getString("operation"));
        }
        return list;
    }
}

