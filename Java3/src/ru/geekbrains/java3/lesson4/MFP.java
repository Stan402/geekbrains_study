package ru.geekbrains.java3.lesson4;


public class MFP {
    private final Object mon1 = new Object();
    private final Object mon2 = new Object();

    public static void main(String[] args) {
        MFP mfp = new MFP();
        Thread t1 = new Thread(() -> {
            mfp.print(10);
        });
        Thread t2 = new Thread(() -> {
            mfp.print(15);
        });
        Thread t3 = new Thread(() -> {
            mfp.scan(10);
        });
        Thread t4 = new Thread(() -> {
            mfp.scan(15);
        });

        t1.start();
        t2.start();
        t3.start();
        t4.start();

    }

    public void print(int pages){
        String printFormat = "отпечатано %d страницы";
        synchronized (mon1){
            if (pages < 0 || pages > 100) return;
            for (int i = 0; i < pages; i++) {
                System.out.println(String.format(printFormat, i));
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void scan(int pages){
        String scanFormat = "отсканировано %d страницы";
        synchronized (mon2){
            if (pages < 0 || pages > 100) return;
            for (int i = 0; i < pages; i++) {
                System.out.println(String.format(scanFormat, i));
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
