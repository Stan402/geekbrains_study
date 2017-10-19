package ru.geekbrains.java3.Algorithmes.lesson5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Algorithmes5task5 {

    private static final String  DELIMETER = "_";

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Enter standart math expression:");
        String expression = reader.readLine();
        String result = convertToPostfix(expression);
    }

    private static String convertToPostfix(String expression) {
        StringBuilder exp = new StringBuilder(expression);
        StringBuilder first = new StringBuilder();
        StringBuilder second = new StringBuilder();
        char oper;
        int i;

        if (Character.isDigit(exp.charAt(0))){
            first.append(exp.charAt(0));
            i = 1;
            while (Character.isDigit(exp.charAt(i))){
                first.append(exp.charAt(i));
                i++;
            }
            if (isOperand(exp.charAt(i))){
                oper = exp.charAt(i);
                i++;
                if (Character.isDigit(exp.charAt(i))){
                    second.append(exp.charAt(i));
                    i++;
                    while (Character.isDigit(exp.charAt(i)) && i < exp.length()){
                        second.append(exp.charAt(i));
                        i++;
                    }
                    if (i == exp.length()){
                        return first.append(exp.charAt(i)).toString();
                    }
                }
            }
        }
        return " ";
    }

    private static boolean isOperand(char a){
        return a =='+' || a == '-' || a == '*' || a == '/';
    }
    private static boolean isLowOperand(char a){
        return a =='+' || a == '-';
    }
    private static boolean isUpOperand(char a){
        return a == '*' || a == '/';
    }

}
