package ru.geekbrains.java3.lesson6;


public class Task2 {

    public boolean isFourAndOne(int[] array){
        if (array == null) return false;
        int count1 = 0;
        int count4 = 0;
        for (int i = 0; i < array.length; i++) {
            if (array[i] == 1) count1++;
            else if (array[i] == 4) count4++;
            else return false;
        }
        return count1 > 0 && count4 > 0;
    }

}
