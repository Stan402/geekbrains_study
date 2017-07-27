package ru.geekbrains.java3.lesson4;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Task2 {
    private static BufferedWriter writer;

    public static void main(String[] args) {
        try {
            writer = new BufferedWriter(new FileWriter("task2.txt"));
            Thread t1 = new Thread(() -> {
                for (int i = 0; i < 10; i++) {
                    try {
                        writer.write("Thread1 wrote" + i +"\r\n");
                        Thread.sleep(20);
                    } catch (InterruptedException | IOException e) {
                        e.printStackTrace();
                    }
                }
            });
            Thread t2 = new Thread(() -> {
                for (int i = 0; i < 10; i++) {
                    try {
                        writer.write("Thread2 wrote" + i + "\r\n");
                        Thread.sleep(20);
                    } catch (InterruptedException | IOException e) {
                        e.printStackTrace();
                    }
                }
            });
            Thread t3 = new Thread(() -> {
                for (int i = 0; i < 10; i++) {
                    try {
                        writer.write("Thread3 wrote" + i + "\r\n");
                        Thread.sleep(20);
                    } catch (InterruptedException | IOException e) {
                        e.printStackTrace();
                    }
                }
            });
            t1.start();
            t2.start();
            t3.start();

            t1.join();
            t2.join();
            t3.join();

            writer.close();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
