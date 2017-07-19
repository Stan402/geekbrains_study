package ru.geekbrains.java3.lesson2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.*;

@SuppressWarnings("WeakerAccess")
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
        initDB();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        connect();
        try {
            statement = connection.createStatement();
            while (!isExit) {
                System.out.println();
                System.out.println("*******Консольное приложение по работе с тестовой базой данных*******");
                System.out.println("Вам доступны команды: ");
                System.out.print(PRICE_QUERY + " 'наименование товара'                      ");
                System.out.println(PRICE_SET_QUERY + " 'наименование товара' 'новая цена'");
                System.out.print(GOODS_LIST_QUERY + " 'минимум цены' 'максимум цены'    ");
                System.out.println(EXIT_QUERY);
                System.out.println();
                System.out.println("Пожалуйста введите команду");

                    String text = reader.readLine();
                    String[] tokens = text.split(" ");
                    switch (tokens[0]) {
                        case PRICE_QUERY:
                            getPrice(tokens);
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
            }
        } catch (SQLException | IOException e){
            e.printStackTrace();
        }
        disconnect();
    }

    static void getPrice(String[] tokens) throws SQLException {
        if (tokens.length < 2) {
            System.out.println("Получен некорректный запрос цены: уточните наименование товара...");
            return;
        }
        ResultSet rs = statement.executeQuery("SELECT * FROM Goods WHERE title = '" + tokens[1] + "'");
        if(rs.next()) System.out.println(rs.getInt("cost"));
        else System.out.println("Такого товара нет: " + tokens[1]);
    }

    static void setPrice(String[] tokens) throws SQLException {
        if (tokens.length != 3) {
            System.out.println("Получен некорректный запрос на смену цены. Неправильное число параметров");
            return;
        }
        try{
            int price = Integer.parseInt(tokens[2]);
            int check = statement.executeUpdate("UPDATE Goods SET cost = " + price + " WHERE title = '" + tokens[1] + "'");
            System.out.println("Установлена новая цена для " + check + " товаров.");
        }catch (NumberFormatException e){
            System.out.println("Получен некорректный запрос на смену цены. Цена должна быть целым числом!");
        }
    }

    static void getListOfGoods(String[] tokens) throws SQLException {
        if (tokens.length != 3) {
            System.out.println("Получен некорректный запрос на вывод. Неправильное число параметров");
            return;
        }
        try{
            int minPrice = Integer.parseInt(tokens[1]);
            int maxPrice = Integer.parseInt(tokens[2]);
            ResultSet rs = statement.executeQuery("SELECT * FROM Goods WHERE cost >=" + minPrice + " AND cost <= " + maxPrice);
            while (rs.next()){
                System.out.println(rs.getString("prodid") + " " + rs.getString("title") + " " + rs.getInt("cost"));
            }
        }catch (NumberFormatException e){
            System.out.println("Получен некорректный запрос на смену цены. Цена должна быть целым числом!");
        }
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

