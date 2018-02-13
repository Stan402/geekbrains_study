package ru.geekbrains.patterns.HW5.ObserverImpl;

import java.awt.*;

//Упрощенное описание товара
public class Item {

    private String name;
    private int size;
    private Color color;

    public Item(String name, int size, Color color) {
        this.name = name;
        this.size = size;
        this.color = color;
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
}
