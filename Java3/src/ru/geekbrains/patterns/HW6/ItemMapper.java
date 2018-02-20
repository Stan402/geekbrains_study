package ru.geekbrains.patterns.HW6;

import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ItemMapper {
    private final Connection connection;

    public ItemMapper(Connection connection) {
        this.connection = connection;
    }

    public Item findById(int id) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(
                "SELECT id, name, size, color LASTNAME FROM items WHERE id = ?");
        statement.setInt(1, id);
        System.out.format("getting from db item with id=%d \n", id);
        try (ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                Item item = new Item();
                item.setId(resultSet.getInt(1));
                item.setName(resultSet.getString(2));
                item.setSize(resultSet.getInt(3));
                item.setColor(getColorFromString(resultSet.getString(4)));
                return item;
            }
        }
        throw new RuntimeException("item not found");
    }

    public void insert(Item item) throws SQLException{
        PreparedStatement statement = connection.prepareStatement(
                "INSERT INTO items (id, name, size, color) VALUES (?,?,?,?)");
        statement.setInt(1, item.getId());
        statement.setString(2,item.getName());
        statement.setInt(3,item.getSize());
        statement.setString(4, getStringFromColor(item.getColor()));
        statement.executeUpdate();
    }

    public void delete(Item item) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(
                "DELETE FROM items WHERE id=?");
        statement.setInt(1, item.getId());
    }

    public void update(Item item) throws SQLException{
        PreparedStatement statement = connection.prepareStatement(
                "UPDATE items SET name=?, size=?, color=? WHERE id=?");
        statement.setString(1, item.getName());
        statement.setInt(2, item.getSize());
        statement.setString(3, getStringFromColor(item.getColor()));
        statement.setInt(4, item.getId());

        statement.executeUpdate();
    }


    private Color getColorFromString(String colorString){
        switch (colorString){
            case "red": return Color.RED;
            case "blue": return Color.BLUE;
            case "black": return Color.BLACK;
            default: return Color.WHITE;
        }
    }

    private String getStringFromColor(Color color){
        if (color.equals(Color.RED)){
            return "red";
        }
        else if (color.equals(Color.BLUE)){
            return "blue";
        }
        else if (color.equals(Color.BLACK)){
            return "black";
        }
        else {
            return "white";
        }
    }


}
