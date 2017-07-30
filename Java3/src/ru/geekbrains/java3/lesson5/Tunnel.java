package ru.geekbrains.java3.lesson5;

import java.util.concurrent.Semaphore;

public class Tunnel extends Stage {
    private int capacity;
    Semaphore smp;

    public Tunnel(int length, int capacity) {
        this.length = length;
        this.capacity = capacity;
        smp = new Semaphore(capacity);
        this.description = "Тоннель " + length + " метров";
    }
    @Override
    public void go(Car c) {
        try {
            try {
                System.out.println(c.getName() + " готовится к этапу(ждет): " +
                        description);
                smp.acquire();
                System.out.println(c.getName() + " начал этап: " + description);
                Thread.sleep(length / c.getSpeed() * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                System.out.println(c.getName() + " закончил этап: " +
                        description);
                smp.release();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
