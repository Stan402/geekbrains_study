package ru.geekbrains.java3.lesson7;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class MainClass {

    public static void main(String[] args) {
        Class testClass = ClassTest.class;
        //start(testClass);
        start("ru.geekbrains.java3.lesson7.ClassTest");
    }

    private static void start(String className) {
        try {
            Class cl = Class.forName(className);
            start(cl);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * works only for static tests
     * in any other cases object should be given as parameter - not realized
     *
     * @param cl Class with tests to run
     */
    private static void start(Class cl) {
        Method[] methods = cl.getDeclaredMethods();

        Method before = null;
        Method after = null;
        List<Method> tests = new ArrayList<>();

        for (int i = 0; i < methods.length; i++) {
            if (!Modifier.isStatic(methods[i].getModifiers())) continue;
            if (methods[i].getAnnotation(BeforeSuite.class) != null) {
                if (before == null) before = methods[i];
                else throw new RuntimeException("More then one 'BeforeSuite' methods");
            } else if (methods[i].getAnnotation(AfterSuite.class) != null) {
                if (after == null) after = methods[i];
                else throw new RuntimeException("More then one 'AfterSuite' methods");
            } else if (methods[i].getAnnotation(Test.class) != null) {
                int pr = methods[i].getAnnotation(Test.class).priority();
                if (pr > 0 && pr < 11) tests.add(methods[i]);
            }
        }

        tests.sort(new Comparator<Method>() {
            @Override
            public int compare(Method o1, Method o2) {
                return o1.getAnnotation(Test.class).priority() - o2.getAnnotation(Test.class).priority();
            }
        });
        try {
            if (before != null) before.invoke(cl);
            for (int i = 0; i < tests.size(); i++) {
                tests.get(i).invoke(cl);
            }
            if (after != null) after.invoke(cl);
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
