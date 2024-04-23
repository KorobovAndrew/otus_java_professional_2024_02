package ru.otus.java.pro.homework02.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static ru.otus.java.pro.homework02.annotation.TestPriority.FIVE;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Test {
    TestPriority priority() default FIVE;
}
