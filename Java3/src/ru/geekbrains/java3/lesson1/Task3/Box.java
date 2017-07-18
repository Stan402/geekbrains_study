package ru.geekbrains.java3.lesson1.Task3;


import java.util.ArrayList;

@SuppressWarnings("WeakerAccess")
public class Box<T extends Fruit> {

    private ArrayList<T> fruits = new ArrayList<>();

    public float getWeight(){
        if (fruits.size() == 0) return 0f;
        return fruits.get(0).getWeight() * fruits.size();
    }

    public boolean compare(Box<?> anotherBox){
        return Math.abs(this.getWeight() - anotherBox.getWeight()) < 0.0001f;
    }

    public void addFruit(T fruit){
        fruits.add(fruit);
    }

    public void addAllFruits(Box<T> anotherBox){
        fruits.addAll(anotherBox.fruits);
        anotherBox.fruits.clear();
    }
}
