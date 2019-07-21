package org.openjfx.database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Database API
 */

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

    /* Get all data from db
     */
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

    /* Get 10 latest entries from db
     */
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


    /* Delete all data from db (not applied in the App)
     */
    public void deleteAll() throws SQLException {

        String selectSQL = "DELETE FROM history;";
        statement.execute(selectSQL);

    }
}

