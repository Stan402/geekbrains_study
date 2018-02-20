package ru.geekbrains.patterns.HW6;

import java.awt.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class Main {

    private static Map<Integer, Item> itemMap = new HashMap<>();


    public static void main(String[] args) throws SQLException {
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/testDB", "root", "root");
        ItemMapper mapper = new ItemMapper(connection);

        Item item = getItem(1, mapper);
        System.out.println(item);
        System.out.println(getItem(1, mapper));
        System.out.println(getItem(2, mapper));
        System.out.println(getItem(2, mapper));
        item = new Item(3, "third", 25, Color.BLACK);
        mapper.insert(item);
        System.out.println(getItem(3, mapper));
    }

    private static Item getItem(int id, ItemMapper mapper) throws SQLException {

        Item item = itemMap.get(id);
        if (item == null){
            item = mapper.findById(id);
            itemMap.put(id, item);
        }
        return item;
    }

}
