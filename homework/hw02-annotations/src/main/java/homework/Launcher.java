package homework;

import homework.annotations.Test;

import java.lang.reflect.Method;
import java.util.*;

public class Launcher {
    public static void launch(Class<?> testClass) throws Exception {

        var annotatedMethods = getAnnotatedMethods(testClass);
        var constructor = testClass.getConstructor();
        Map<String, Integer> testStatistics = new HashMap<>(Map.of("Tests count", 0,
                "Passed tests", 0,
                "Failed tests", 0));
        if (annotatedMethods.containsKey("BeforeSuite") && annotatedMethods.get("BeforeSuite").size() <= 1) {
            annotatedMethods.get("BeforeSuite").get(0).invoke(null);
        }
        annotatedMethods.get("Test").stream()
                .sorted(Collections.reverseOrder(Comparator.comparingInt(p1 -> p1.getAnnotation(Test.class).priority())))
                .forEach(testMethod -> {
                    try {
                        var object = constructor.newInstance();
                        testStatistics.compute("Tests count", (k, v) -> ++v);
                        runTest(testMethod, annotatedMethods, object);
                        testStatistics.compute("Passed tests", (k, v) -> ++v);
                    } catch (Exception e) {
                        testStatistics.compute("Failed tests", (k, v) -> ++v);
                    }
                });
        if (annotatedMethods.containsKey("AfterSuite") && annotatedMethods.get("AfterSuite").size() <= 1) {
            annotatedMethods.get("AfterSuite").get(0).invoke(null);
        }
        testStatistics.forEach((k, v) -> System.out.printf("%s :\t%s\n", k, v));
    }

    private static Map<String, List<Method>> getAnnotatedMethods(Class<?> clazz) {

        var methods = clazz.getDeclaredMethods();

        Map<String, List<Method>> annotatedMethods = new HashMap<>();

        for (var method : methods) {
            for (var annotation : method.getDeclaredAnnotations()) {
                var annotationName = annotation.annotationType().getSimpleName();
                annotatedMethods.putIfAbsent(annotationName, new ArrayList<>());
                annotatedMethods.get(annotationName).add(method);
            }
        }
        return annotatedMethods;
    }

    private static void runTest(Method testMethod,
                                Map<String, List<Method>> annotatedMethods,
                                Object object) throws Exception {

        for (var beforeMethod : annotatedMethods.get("Before"))
            beforeMethod.invoke(object);

        testMethod.invoke(object);

        for (var afterMethod : annotatedMethods.get("After"))
            afterMethod.invoke(object);
    }
}
