package ru.geekbrains.java3.lesson6;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class TestTask1 {
    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {new int[]{1, 2, 3, 4, 5, 6, 7, 8}, new int[]{5, 6, 7, 8}},
                {new int[]{1, 2, 3, 4, 5, 6, 4, 8}, new int[]{8}},
                {new int[]{1, 2, 3, 4, 5, 6, 7, 4}, new int[]{}}
        });
    }
    private int[] array1;
    private int[] array2;

    public TestTask1(int[] array1, int[] array2){
        this.array1 = array1;
        this.array2 = array2;
    }

    private Task1 task1;
    @Before
    public void init(){
        task1 = new Task1();
    }

    @Test
    public void testAfterFour(){
        Assert.assertArrayEquals(array2, task1.afterFour(array1));
    }
}


