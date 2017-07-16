package ru.geekbrains.java3.lesson1;

import java.util.Arrays;

public class HomeworkTask1 {

    public static void main(String[] args) {
        Integer[] arr1 = new Integer[]{1,2,3,4,5};
        String[] arr2 = new String[]{"one", "two", "three", "four", "five"};
        System.out.println(Arrays.toString(arr1));
        System.out.println(Arrays.toString(arr2));
        if (invert(arr1, 1, 3)) System.out.println(Arrays.toString(arr1));
        if (invert(arr2, 0, 2)) System.out.println(Arrays.toString(arr2));

    }

    private static <T> boolean invert(T[] array,int index1, int index2){

        if (array == null) return false;
        if (index1 < 0 || index1 > array.length - 1 || index2 < 0 || index2 > array.length - 1) return false;
        T buffer = array[index1];
        array[index1] = array[index2];
        array[index2] = buffer;
        return true;
    }
}
