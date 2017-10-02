package ru.geekbrains.java3.lesson7;

public class ClassTest {

    @BeforeSuite
    static void startMethod() {
        System.out.println("'Before suite' method called");
    }

    @Test(priority = 1)
    static void test1() {
        System.out.println("test1 called");
    }

    @Test(priority = 2)
    static void test2() {
        System.out.println("test2 called");
    }

    @Test(priority = 1)
    static void test3() {
        System.out.println("test3 called");
    }

    @Test(priority = 10)
    static void test4() {
        System.out.println("test4 called");
    }

    @Test
    static void test5() {
        System.out.println("test5 called");
    }

    @Test(priority = 15)
    static void test6() {
        System.out.println("test6 called");
    }

    @AfterSuite
    static void lastMethod() {
        System.out.println("'After suite' method called");
    }

    //    @AfterSuite
//    static void lastMethod2() {
//        System.out.println("'After suite' method2 called");
//    }
    static void withoutAnnotation() {
        System.out.println("not annotated method");
    }
}
