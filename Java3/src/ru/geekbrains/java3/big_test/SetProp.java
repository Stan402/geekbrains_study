package ru.geekbrains.java3.big_test;

import java.io.*;
import java.util.Properties;

public class SetProp {

    public static void main(String[] args) {
        Properties prop = new Properties();
        OutputStream output = null;
        File file = new File("testConfig.properties");
        try {
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        File file1 = new File("myLog.txt");
        try {
            file1.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            output = new FileOutputStream("testConfig.properties");

            prop.setProperty("DBpath", "BigTestSQL.db");
            prop.setProperty("logFile","myLog.txt" );

            prop.store(output, null);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
