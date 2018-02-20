package ru.geekbrains.patterns.HW6;

import java.awt.*;

//Упрощенное описание товара
//value object btw
public class Item {

    private int id;
    private String name;
    private int size;
    private Color color;

    public Item() {
    }

    public Item(int id, String name, int size, Color color) {
        this.id = id;
        this.name = name;
        this.size = size;
        this.color = color;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Item item = (Item) o;

        if (size != item.size) return false;
        return color.equals(item.color);
    }

    @Override
    public int hashCode() {
        int result = size;
        result = 31 * result + color.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Item{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", size=" + size +
                ", color=" + color +
                '}';
    }
}
