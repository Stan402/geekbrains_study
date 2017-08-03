package ru.geekbrains.java3.lesson6;

import java.util.Arrays;

public class Task1 {

    public static void main(String[] args) {
        int[] array = {2,4,5,3,4,6,7,8};
        Task1 t = new Task1();
        System.out.println(Arrays.toString(t.afterFour(array)));
    }

    public int[] afterFour(int[] array){
        if (array == null) throw new RuntimeException();
        int last4 = -1;
        for (int i = 0; i < array.length; i++) {
            if (array[i] == 4) last4 = i;
        }
        if (last4 == -1) throw new RuntimeException();
        return Arrays.copyOfRange(array, last4 + 1, array.length);
    }
}
