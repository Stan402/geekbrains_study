package ru.geekbrains.java3.lesson6;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class TestFalseTask2 {

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {new int[]{1, 4, 1, 4, 1, 1, 5, 1}},
                {new int[]{1, 1, 1, 1, 1, 1, 1, 1}},
                {new int[]{4, 4, 4, 4, 4, 4}}
        });
    }
    private int[] array1;


    public TestFalseTask2(int[] array1){
        this.array1 = array1;
    }

    private Task2 task2;
    @Before
    public void init(){
        task2 = new Task2();
    }

    @Test
    public void testAfterFour(){
        Assert.assertFalse(task2.isFourAndOne(array1));
    }
}