package ru.geekbrains.java3.lesson3;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;

public class Homework3 {

    public static void main(String[] args) throws IOException{
        // Всё работает с одинарным слэшем на маке, кстати говоря.
//        File myFiles = new File("testfiles/lesson3");
//        myFiles.mkdir();
        File file1 = new File("testfiles/lesson3/file1.txt");
//        file1.createNewFile();
        task1(file1);



    }

    private static void task1(File file)throws IOException{
        if (!file.isFile()) return;
        FileInputStream in = new FileInputStream(file);
        byte[] buffer = new byte[in.available()];
        int size  = in.read(buffer);
        System.out.println(Arrays.toString(buffer));
    }

}
