package ru.geekbrains.patterns.HW5.ObserverImpl;

public interface StockListener {
    void onItemAdded (Item item);
    void onItemRemoved (Item item);
}
