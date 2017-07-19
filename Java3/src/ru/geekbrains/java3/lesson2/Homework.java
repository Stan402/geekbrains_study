package ru.geekbrains.java3.lesson2;

import java.sql.*;

public class Homework {

    private static Connection connection;
    private static Statement statement;
    private static PreparedStatement prStmt;


    public static void main(String[] args) {

        connect();

        disconnect();

    }

    public static void initDB(){
        connect();
        try {
            statement = connection.createStatement();
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
//    CREATE TABLE Goods (
//        id     INTEGER PRIMARY KEY AUTOINCREMENT,
//        prodid STRING,
//        title  TEXT,
//        cost   INTEGER
//);
