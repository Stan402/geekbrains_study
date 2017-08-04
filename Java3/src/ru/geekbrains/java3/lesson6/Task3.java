package ru.geekbrains.java3.lesson6;

import java.sql.*;
import java.util.Set;

public class Task3 {
    private static final String PATH = "Lesson6_task3.db";
    Connection connection;

    private static final String prepDelete = "DELETE FROM Students WHERE LastName = ? ";
    private static final String prepSelect = "SELECT Score FROM Students WHERE LastName == ?";
    private static final String prepInsert = "INSERT INTO Students (LastName, Score) VALUES(?, ?)";
    private static final String prepUpdate = "UPDATE Students SET Score = ? WHERE LastName = ?";

    public static void main(String[] args) {
        Task3 task3 = new Task3();
        task3.connect();
        //task3.initDB();

        task3.disconnect();
    }

    void initDB() {

        try {
            Statement statement = connection.createStatement();
            statement.execute("CREATE TABLE IF NOT EXISTS Students (\n" +
                    "    id     INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                    "    LastName TEXT,\n" +
                    "    Score  Integer\n" +
                    ");\n");
            statement.execute("DELETE FROM Students");
            PreparedStatement psInit = connection.prepareStatement(prepInsert);
            connection.setAutoCommit(false);
            int countTo = 10;
            for (int i = 1; i < countTo; i++) {
                psInit.setString(1, "Student" + i);
                psInit.setInt(2, i);
                psInit.addBatch();
            }
            psInit.executeBatch();
            connection.setAutoCommit(true);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    void addStudent(String name, int score){
        try {
            PreparedStatement psInsert = connection.prepareStatement(prepInsert);
            psInsert.setString(1, name);
            psInsert.setInt(2, score);
            psInsert.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    int getScore(String name){
        int res = -1;
        try {
            PreparedStatement psGetScore = connection.prepareStatement(prepSelect);
            psGetScore.setString(1, name);
            ResultSet rs = psGetScore.executeQuery();
            if (rs.next()) res = rs.getInt("Score");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return res;
    }
    void deleteStudent(String name){
        try {
            PreparedStatement psDelete = connection.prepareStatement(prepDelete);
            psDelete.setString(1, name);
            psDelete.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    void updateScore(String name, int score){
        try {
            PreparedStatement psUpdate = connection.prepareStatement(prepUpdate);
            psUpdate.setString(1, name);
            psUpdate.setInt(2, score);
            psUpdate.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    void connect() {
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:" + PATH);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    void disconnect() {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
