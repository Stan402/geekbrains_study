package ru.geekbrains.java3.lesson1.Task3;

public class Test {

    public static void main(String[] args) {
        Box<Apple> box1 = new Box<>();
        Box<Apple> box2 = new Box<>();
        Box<Orange> box3 = new Box<>();
        Box<Orange> box4 = new Box<>();
        fillBoxApple(box1, 6);
        fillBoxApple(box2, 10);
        fillBoxOrange(box3, 4);
        fillBoxOrange(box4, 8);
        System.out.println(box1.compare(box3));
        System.out.println(box4.compare(box2));
        System.out.println("вес box2: " + box2.getWeight());
        System.out.println("вес box1: " + box1.getWeight());
        box1.addAllFruits(box2);
        System.out.println("вес высыпанной box2: " + box2.getWeight());
        System.out.println("вес досыпанной box1: " + box1.getWeight());

    }

    @SuppressWarnings("WeakerAccess")
    static void fillBoxApple(Box<Apple> box, int numberOfFruits){
        for (int i = 0; i < numberOfFruits; i++) {
            box.addFruit(new Apple());
        }
    }
    @SuppressWarnings("WeakerAccess")
    static void fillBoxOrange(Box<Orange> box, int numberOfFruits){
        for (int i = 0; i < numberOfFruits; i++) {
            box.addFruit(new Orange());
        }
    }
}
