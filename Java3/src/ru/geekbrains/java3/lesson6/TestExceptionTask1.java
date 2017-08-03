package ru.geekbrains.java3.lesson6;


import org.junit.Before;
import org.junit.Test;

public class TestExceptionTask1 {
    private Task1 task1;

    @Before
    public void init(){
        task1 = new Task1();
    }
    @Test (expected = RuntimeException.class)
    public void TestWrongDataAfter4(){

        task1.afterFour(new int[]{1, 2, 3, 5, 6, 7});

    }
}
