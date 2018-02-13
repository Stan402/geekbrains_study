package ru.geekbrains.patterns.HW5.ObserverImpl;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

//очередь заказов
public class WaitingList implements StockListener{

    private List<Order> orders = new ArrayList<>();
    private Stock stock = Stock.getInstance();


    @Override
    public void onItemAdded(Item item) {

        Order order = orders.stream()
                .sorted(Comparator.comparing(Order::getDeliveryDate))
                .filter(o -> o.getItem().equals(item))
                .findFirst()
                .orElse(null);
        if (order != null){
            order.execute(item);
            stock.remove(item);
            orders.remove(order);
        }
    }

    @Override
    public void onItemRemoved(Item item) {
    }

    public void add(Order order){
        orders.add(order);
    }

    public void cancel(Order order){
        orders.remove(order);
    }
}
