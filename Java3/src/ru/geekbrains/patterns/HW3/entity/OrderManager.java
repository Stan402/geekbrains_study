package ru.geekbrains.patterns.HW3.entity;

import ru.geekbrains.patterns.HW3.ManSetBuilder;
import ru.geekbrains.patterns.HW3.SetDirector;
import ru.geekbrains.patterns.HW3.WomanSetBuilder;

public class OrderManager {

    private SetDirector director = new SetDirector();

    public Set formOrder(String sex){
        switch (sex){
            case "male":
                return director.createSet(new ManSetBuilder());
            case "female":
                return director.createSet(new WomanSetBuilder());
            default:
                throw new RuntimeException("unexpected order specifications");
        }
    }


}
