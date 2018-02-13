package ru.geekbrains.patterns.HW5.ObserverImpl;

import java.awt.*;
import java.time.LocalDate;

public class Main {

    public static void main(String[] args) {
        Client client1 = new Client("Ivan", "Ivanov", "from Nowhere");
        Client client2 = new Client("Petr", "Petrov", "from Everywhere");
        WaitingList waitingList = new WaitingList();
        Stock stock = Stock.getInstance();
        waitingList.add(new Order(new Item("good1", 10, Color.BLACK), LocalDate.now().plusDays(3), client1));
        waitingList.add(new Order(new Item("good2", 15, Color.RED), LocalDate.now().plusDays(2), client2));
        stock.addListener(new StockCounter());
        stock.addListener(waitingList);
        stock.add(new Item("1", 8, Color.BLUE));
        stock.add(new Item("2", 10, Color.CYAN));
        stock.add(new Item("3", 15, Color.RED));
        stock.add(new Item("4", 10, Color.BLACK));
    }
}
