package ru.geekbrains.java3.big_test;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;

public class DataBaseManager {

    private String path;
    private Connection connection;
    private Statement statement;
    private PreparedStatement psInit;

    DataBaseManager(String path){
        this.path = path;
    }

    void initDB() {
        connect();
        try {
            statement = connection.createStatement();
            statement.execute("CREATE TABLE IF NOT EXISTS Organogram (\n" +
                    "    id     INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                    "    DepCode TEXT,\n" +
                    "    DepJob  TEXT,\n" +
                    "    Description   TEXT\n" +
                    ");\n");
            statement.execute("DELETE FROM Organogram");
            psInit = connection.prepareStatement("INSERT INTO Organogram (DepCode, DepJob, Description) VALUES(?, ?, ?)");
            connection.setAutoCommit(false);
            int countTo = 100;
            for (int i = 1; i < countTo; i++) {
                psInit.setString(1, "department" + i/10);
                psInit.setString(2, "worker#" + i);
                psInit.setString(3, "doing some stupid work" + i);
                psInit.addBatch();
            }
            psInit.executeBatch();
            connection.setAutoCommit(true);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        disconnect();
    }

    Map<NaturalKey, String> loadDB(){
        Map<NaturalKey, String> result = new HashMap<>();
        connect();
        try {
            PreparedStatement psLoadDB = connection.prepareStatement("SELECT * FROM Organogram");
            statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM Organogram");
            while (rs.next()){
                result.put(new NaturalKey(rs.getString("DepCode"), rs.getString("DepJob")), rs.getString("Description"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        disconnect();
        return result;
    }


    public void connect() {
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:" + path);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public void disconnect() {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
