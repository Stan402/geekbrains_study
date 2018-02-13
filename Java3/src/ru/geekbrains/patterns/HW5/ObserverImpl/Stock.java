package ru.geekbrains.patterns.HW5.ObserverImpl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

//Склад (наличие)
public class Stock {
    private static Stock ourInstance = new Stock();

    public static Stock getInstance() {
        return ourInstance;
    }

    private Stock() {
    }

    //имитация - здесь отсылка в бд должна быть
    private Set<Item> items = new HashSet<>();
    private List<StockListener> listeners = new ArrayList<>();

    public void remove(Item item) {
        if (items.remove(item)){
            listeners.forEach(stockListener -> stockListener.onItemRemoved(item));
        }

    }

    public void add(Item item){
        items.add(item);
        listeners.forEach(stockListener -> stockListener.onItemAdded(item));

    }

    public void addListener(StockListener stockListener){
        listeners.add(stockListener);
    }

    public void removeListener(StockListener stockListener){
        listeners.remove(stockListener);
    }

    public Set<Item> getItems() {
        return items;
    }

    public void setItems(Set<Item> items) {
        this.items = items;
    }

    public List<StockListener> getListeners() {
        return listeners;
    }
}
