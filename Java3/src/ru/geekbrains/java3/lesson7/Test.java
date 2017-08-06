package ru.geekbrains.java3.lesson7;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by stan on 06/08/2017.
 * set priority in ascending order, where 1 is the highest priority and 10 is the lowest one.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Test {
    int priority() default 5;
}
