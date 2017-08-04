package ru.geekbrains.java3.lesson6;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TestTask3 {

    private Task3 task3;
    private static final String prepSelect = "SELECT * FROM Students WHERE LastName == ?";

    @Before
    public void init(){
        task3 = new Task3();
        task3.connect();
        try {
            task3.connection.setAutoCommit(false);
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
            PreparedStatement psSelect = task3.connection.prepareStatement(prepSelect);
            psSelect.setString(1, testName);
            ResultSet rs =psSelect.executeQuery();
            Assert.assertTrue(rs.next());
            if (rs.next()) Assert.assertEquals(testScore, rs.getInt("Score"));

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testGetScore(){
        String testName = "Student5";
        int res = task3.getScore(testName);
        try {
            PreparedStatement psSelect = task3.connection.prepareStatement(prepSelect);
            psSelect.setString(1, testName);
            ResultSet rs =psSelect.executeQuery();
            Assert.assertTrue(rs.next());
            if (rs.next()) {
                Assert.assertEquals(res, rs.getInt("Score"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @Test
    public void testDeleteStudent(){
        String testName = "Student5";
        task3.deleteStudent(testName);
        try {
            PreparedStatement psSelect = task3.connection.prepareStatement(prepSelect);
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
            PreparedStatement psSelect = task3.connection.prepareStatement(prepSelect);
            psSelect.setString(1, testName);
            ResultSet rs =psSelect.executeQuery();
            Assert.assertTrue(rs.next());
            if (rs.next()) {
                Assert.assertEquals(testScore, rs.getInt("Score"));
                Assert.assertEquals(testName,rs.getString("LastName"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @After
    public void end(){
        task3.disconnect();
    }
}
