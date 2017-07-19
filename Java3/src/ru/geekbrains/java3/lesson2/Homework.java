package ru.geekbrains.java3.lesson2;

import java.sql.*;

public class Homework {

    private static Connection connection;
    private static Statement statement;
    private static PreparedStatement prStmt;


    public static void main(String[] args) {

        initDB();

    }

    public static void initDB(){
        connect();
        try {
            statement = connection.createStatement();
            statement.execute("CREATE TABLE IF NOT EXISTS Goods (\n" +
                    "    id     INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                    "    prodid STRING,\n" +
                    "    title  TEXT,\n" +
                    "    cost   INTEGER\n" +
                    ");\n");
            statement.execute("DELETE FROM Goods");
            prStmt = connection.prepareStatement("INSERT INTO Goods (prodid, title, cost) VALUES(?, ?, ?)");
            connection.setAutoCommit(false);
            for (int i = 1; i < 10001; i++) {
                prStmt.setString(1,"id_товара " + i);
                prStmt.setString(2, "товар" + i);
                prStmt.setInt(3, i * 10);
                prStmt.addBatch();
            }
            prStmt.executeBatch();
            connection.setAutoCommit(true);
        } catch (SQLException e) {
            e.printStackTrace();
        }


        disconnect();
    }

    public static void connect(){
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:Lesson2hw.db");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
    public static void disconnect(){
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

