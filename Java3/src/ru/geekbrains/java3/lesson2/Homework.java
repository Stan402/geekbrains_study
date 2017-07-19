package ru.geekbrains.java3.lesson2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.*;

public class Homework {

    private static Connection connection;
    private static Statement statement;
    private static PreparedStatement prStmt;
    private static boolean isExit = false;

    private static final int DBSIZE = 10_000;
    private static final String PRICE_QUERY = "/цена";
    private static final String PRICE_SET_QUERY = "/сменитьцену";
    private static final String GOODS_LIST_QUERY = "/товарыпоцене";
    private static final String EXIT_QUERY = "/выход";


    public static void main(String[] args) {
        //initDB();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        while (!isExit){
            System.out.println("*******Консольное приложение по работе с тестовой базой данных*******");
            System.out.println("Вам доступны команды: ");
            System.out.println(PRICE_QUERY + " 'наименование товара'");
            System.out.println(PRICE_SET_QUERY + " 'наименование товара' 'новая цена'");
            System.out.println(GOODS_LIST_QUERY + " 'минимум цены' 'максимум цены'");
            System.out.println(EXIT_QUERY);
            System.out.println();
            System.out.println("Пожалуйста введите команду");
            try {
                String text = reader.readLine();
                String[] tokens = text.split(" ");
                switch (tokens[0]){
                    case PRICE_QUERY:
                        getPrice(tokens[1]);
                        break;
                    case PRICE_SET_QUERY:
                        setPrice(tokens);
                        break;
                    case GOODS_LIST_QUERY:
                        getListOfGoods(tokens);
                        break;
                    case EXIT_QUERY:
                        isExit = true;
                        break;
                    default:
                        System.out.println("Введена некорректная команда. Попробуйте еще раз!");
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    static void getPrice(String title){
        System.out.println("Получен запрос на определение цены " + title);
    }

    static void setPrice(String[] tokens){
        System.out.println("Получен запрос на смену цены " + tokens[1] + " " + tokens[2]);
    }

    static void getListOfGoods(String[] tokens){
        System.out.println("Получен запрос на список " + tokens[1] + " " + tokens[2] + " " + tokens[3]);
    }

    private static void initDB(){
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
            int countTo = DBSIZE + 1;
            for (int i = 1; i < countTo; i++) {
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

