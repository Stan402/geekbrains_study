package ru.geekbrains.patterns.HW5.ObserverImpl;

import java.time.LocalDate;

public class Order {

    private Item item;
    private LocalDate deliveryDate;
    private Client client;

    public Order(Item item, LocalDate deliveryDate, Client client) {
        this.item = item;
        this.deliveryDate = deliveryDate;
        this.client = client;
    }

    public void execute(Item item){
        System.out.println(this.toString() + " executed");
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public LocalDate getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(LocalDate deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    @Override
    public String toString() {
        return "Order{" +
                "item=" + item +
                ", deliveryDate=" + deliveryDate +
                ", client=" + client +
                '}';
    }
}
