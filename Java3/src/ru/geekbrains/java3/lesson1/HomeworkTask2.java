package ru.geekbrains.java3.lesson1;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class HomeworkTask2 {

    public static void main(String[] args) {

        Integer[] arr1 = new Integer[]{1,2,3,4,5};
        String[] arr2 = new String[]{"one", "two", "three", "four", "five"};
        ArrayList<Integer> list1 = arrayToList(arr1);
        ArrayList<String> list2 = arrayToList(arr2);
        System.out.println(list1.toString());
        System.out.println(list2.toString());

    }

    private static <E> ArrayList<E> arrayToList(E[] array){

        ArrayList<E> list = new ArrayList<>();
        Collections.addAll(list, array);
        return list;
    }
}
