package ru.geekbrains.java3.lesson4;

public class Task1 {
    private final Object mon = new Object();
    private volatile char currentLetter = 'A';

    public static void main(String[] args) {
        Task1 task1 = new Task1();
        Thread t1 = new Thread(() -> {
           task1.printA();
        });
        Thread t2 = new Thread(() -> {
           task1.printB();
        });
        Thread t3 = new Thread(() -> {
           task1.printC();
        });

        t1.start();
        t2.start();
        t3.start();

    }

    private void printA(){
        synchronized (mon){
            try{
                for (int i = 0; i < 5; i++) {
                    while (currentLetter != 'A') mon.wait();
                    System.out.print('A');
                    currentLetter = 'B';
                    mon.notifyAll();
                }
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }
    private void printB(){
        synchronized (mon){
            try{
                for (int i = 0; i < 5; i++) {
                    while (currentLetter != 'B') mon.wait();
                    System.out.print('B');
                    currentLetter = 'C';
                    mon.notifyAll();
                }
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }
    private void printC(){
        synchronized (mon){
            try{
                for (int i = 0; i < 5; i++) {
                    while (currentLetter != 'C') mon.wait();
                    System.out.print('C');
                    currentLetter = 'A';
                    mon.notifyAll();
                }
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }



}
