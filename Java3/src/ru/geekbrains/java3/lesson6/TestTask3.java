package ru.geekbrains.java3.lesson6;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.sql.*;

public class TestTask3 {

    private Task3 task3;
    private static final String prepSelect = "SELECT * FROM Students WHERE LastName == ?";
    private static Connection connection;
    private static final String PATH = "Lesson6_task3.db";

    @Before
    public void init(){
        task3 = new Task3();
        connect();
        try {
            connection.setAutoCommit(false);
            Task3.connection = connection;
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testAddStudent(){
        String testName = "Student15";
        int testScore = 15;
        task3.addStudent(testName, testScore);
        try {
            PreparedStatement psSelect = connection.prepareStatement(prepSelect);
            psSelect.setString(1, testName);
            ResultSet rs =psSelect.executeQuery();
            Assert.assertEquals(testScore, rs.getInt("Score"));

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testGetScore(){
        String testName = "Student5";
        int res = task3.getScore(testName);
        try {
            PreparedStatement psSelect = connection.prepareStatement(prepSelect);
            psSelect.setString(1, testName);
            ResultSet rs =psSelect.executeQuery();
            Assert.assertEquals(res, rs.getInt("Score"));


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @Test
    public void testDeleteStudent(){
        String testName = "Student5";
        task3.deleteStudent(testName);
        try {
            PreparedStatement psSelect = connection.prepareStatement(prepSelect);
            psSelect.setString(1, testName);
            ResultSet rs =psSelect.executeQuery();
            Assert.assertFalse(rs.next());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @Test
    public void testUpdateScore(){
        String testName = "Student5";
        int testScore = 5;
        task3.updateScore(testName, testScore);
        try {
            PreparedStatement psSelect = connection.prepareStatement(prepSelect);
            psSelect.setString(1, testName);
            ResultSet rs =psSelect.executeQuery();
            Assert.assertEquals(testScore, rs.getInt("Score"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @After
    public void end(){
        disconnect();
    }
    private static void connect() {
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:" + PATH);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    private static void disconnect() {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
