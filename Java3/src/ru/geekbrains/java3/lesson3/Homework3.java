package ru.geekbrains.java3.lesson3;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Homework3 {

    public static void main(String[] args) throws IOException{
        // Всё работает с одинарным слэшем на маке, кстати говоря.

        File file1 = new File("testfiles/lesson3/file1.txt");

        task1(file1);

        task2();

        File file3 = new File("testfiles/lesson3/Asimov, Isaac - The Complete Stories Volume 1 - 1990.txt");
        task3(file3);


    }

    private static void task1(File file)throws IOException{
        if (!file.isFile()) return;
        FileInputStream in = new FileInputStream(file);
        byte[] buffer = new byte[in.available()];
        int size  = in.read(buffer);
        System.out.println(Arrays.toString(buffer));
        in.close();
    }

    private static void task2() throws IOException{
        File a = new File("testfiles/lesson3/a.txt");
        File b = new File("testfiles/lesson3/b.txt");
        File c = new File("testfiles/lesson3/c.txt");
        File d = new File("testfiles/lesson3/d.txt");
        File e = new File("testfiles/lesson3/e.txt");
        ArrayList<FileInputStream> inputStreams = new ArrayList<>();
        inputStreams.add(new FileInputStream(a));
        inputStreams.add(new FileInputStream(b));
        inputStreams.add(new FileInputStream(c));
        inputStreams.add(new FileInputStream(d));
        inputStreams.add(new FileInputStream(e));

        SequenceInputStream sin = new SequenceInputStream(Collections.enumeration(inputStreams));
        FileOutputStream out = new FileOutputStream("testfiles/lesson3/result.txt");
        int x;
        while ((x = sin.read()) != -1){
            out.write(x);
        }
        sin.close();
        out.close();
    }

    private static void task3(File file) throws IOException{
        if (!file.isFile()) return;
        RandomAccessFile raf = new RandomAccessFile(file, "rw");
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        long l = raf.length();
        int totalPages = (int)(l/1800 + 1);
        String header = "  \n Вы находитесь в режиме постраничного чтения \n" +
                            "Вы читаете: " + file.getName() + "\n" +
                            "В файле " + totalPages + " страниц" + "\n" +
                            "Введите номер страницы, которую Вы хотите прочитать или exit для выхода из программы";
        int page;
        while (true) {
            System.out.println(header);
            String next = reader.readLine();
            if (next.equals("exit")) break;
            try {
                page = Integer.parseInt(next);
                if (page > 0 && page <= totalPages){
                raf.seek((page - 1) * 1800L);
                for (int i = 0; i < 1800; i++) {
                    int x;
                    if ((x = raf.read()) != -1) System.out.print((char) x);
                    else break;
                }
                }
            } catch (NumberFormatException e){
               // сознательно пустой блок
            }
        }
        reader.close();
        raf.close();
    }

}
