package ru.otus.java.pro.homework02;

import ru.otus.java.pro.homework02.annotation.*;
import ru.otus.java.pro.homework02.exception.TestFailedException;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

public class Launcher {
    public static void launch(Class<?> testClass) throws Exception {

        var annotatedMethods = getAnnotatedMethods(testClass);
        var constructor = testClass.getConstructor();
        Map<String, Integer> testStatistics = new HashMap<>(Map.of("Tests count", 0,
                "Passed tests", 0,
                "Failed tests", 0));

        runSuitesMethods(annotatedMethods, BeforeSuite.class);

        annotatedMethods.get(Test.class).stream()
                .sorted(Collections.reverseOrder(Comparator.comparingInt(p1 -> p1.getAnnotation(Test.class).priority())))
                .forEach(testMethod -> {
                    try {
                        var object = constructor.newInstance();
                        testStatistics.compute("Tests count", (k, v) -> ++v);
                        runTest(testMethod, annotatedMethods, object);
                        testStatistics.compute("Passed tests", (k, v) -> ++v);
                    } catch (TestFailedException e) {
                        testStatistics.compute("Failed tests", (k, v) -> ++v);
                    } catch (InvocationTargetException | InstantiationException | IllegalAccessException e) {
                        System.out.println(e.getMessage());
                        e.printStackTrace();
                    }
                });

        runSuitesMethods(annotatedMethods, AfterSuite.class);

        testStatistics.forEach((k, v) -> System.out.printf("%s :\t%s\n", k, v));
    }

    private static Map<Class, List<Method>> getAnnotatedMethods(Class<?> clazz) {

        var methods = clazz.getDeclaredMethods();

        Map<Class, List<Method>> annotatedMethods = new HashMap<>();

        for (var method : methods) {
            for (var annotation : method.getDeclaredAnnotations()) {
                var annotationClass = annotation.annotationType();
                annotatedMethods.putIfAbsent(annotationClass, new ArrayList<>());
                annotatedMethods.get(annotationClass).add(method);
            }
        }
        return annotatedMethods;
    }

    private static void runTest(Method testMethod,
                                Map<Class, List<Method>> annotatedMethods,
                                Object object) throws TestFailedException {

        if (Arrays.stream(testMethod.getDeclaredAnnotations())
                .map(Annotation::annotationType)
                .anyMatch(a -> a.equals(Before.class) || a.equals(After.class))) {
            throw new RuntimeException("Метод не может содержать одновременно аннотации @Test и @Before/@After");
        }

        try {
            for (var beforeMethod : annotatedMethods.get(Before.class))
                beforeMethod.invoke(object);

            testMethod.invoke(object);

            for (var afterMethod : annotatedMethods.get(After.class))
                afterMethod.invoke(object);
        } catch (InvocationTargetException | IllegalAccessException e) {
            throw new TestFailedException();
        }
    }

    private static void runSuitesMethods(Map<Class, List<Method>> annotatedMethods, Class annotation) throws Exception {
        if (annotatedMethods.containsKey(annotation)) {
            if (annotatedMethods.get(annotation).size() > 1) {
                throw new RuntimeException(String.format("Аннотацией @%s допустимо отмечать только один метод тестового класса",
                        annotation.getSimpleName()));
            } else {
                annotatedMethods.get(annotation).get(0).invoke(null);
            }
        }
    }
}
