package ru.geekbrains.patterns.HW5.ObserverImpl;

//счётчик наличия
public class StockCounter implements StockListener {
    private int count;

    public StockCounter() {
        count = Stock.getInstance().getItems().size();
    }

    @Override
    public void onItemAdded(Item item) {
        System.out.println("Item added to stock");
        count++;
        System.out.println("Current stock: " + count);
    }

    @Override
    public void onItemRemoved(Item item) {
        System.out.println("Item removed from stock");
        count--;
        System.out.println("Current stock: " + count);
    }
}
