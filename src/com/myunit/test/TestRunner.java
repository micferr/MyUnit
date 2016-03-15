package com.myunit.test;

import com.myunit.log.Logger;
import com.myunit.log.LoggerBuilder;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class TestRunner {
    private static Logger out;

    public TestRunner() {
        out = new LoggerBuilder().setLogger(System.out).build();
    }

    public TestRunner(Object o) {
        out = new LoggerBuilder().setLogger(o).build();
    }

    public static Logger getLogger() {
        return out;
    }

    public static void setLogger(Logger stream) {
        out = stream;
    }

    public void run(Class... testClasses) {
        for (Class testClass : testClasses) {
            try {
                Object test = buildTest(testClass);
                setUp(test);
                executeTests(test);
                tearDown(test);
            } catch (Throwable exception) {
                logSkipWholeTest(testClass, exception);
            }
        }
    }

    private static Object buildTest(Class<?> testClass) throws Throwable {
        out.log("Building " + testClass.getSimpleName() + "...");
        return testClass.getConstructor().newInstance();
    }

    private static void setUp(Object test) throws Throwable {
        Class testClass = test.getClass();
        out.log("Setting up " + testClass.getSimpleName() + "...");
        executeAnnotatedMethods(test, Before.class);
    }

    private static void executeTests(Object test) throws Throwable {
        Class testClass = test.getClass();
        out.log("Testing " + testClass.getSimpleName() + "...");
        for (Method method : testClass.getDeclaredMethods()) {
            if (method.isAnnotationPresent(Test.class)) {
                testMethod(test, method);
            }
        }
    }

    private static void testMethod(Object test, Method method) {
        out.log("Executing test method: " + method.getName());
        try {
            method.setAccessible(true);
            method.invoke(test);
        } catch (InvocationTargetException exception) {
            if (isExpectedException(exception, method)) {
                return;
            }
            logSkipTestCase(test.getClass(), exception.getCause());
        } catch (IllegalAccessException exception) {
            out.log("Should never happen.");
        }
    }

    private static boolean isExpectedException(Exception exception, Method method) {
        return exception.getCause().getClass().equals(method.getDeclaredAnnotation(Test.class).expected());
    }

    private static void tearDown(Object test) throws Throwable {
        Class testClass = test.getClass();
        out.log("Tearing down " + testClass.getSimpleName() + "...");
        executeAnnotatedMethods(test, After.class);
    }

    private static void executeAnnotatedMethods(Object test, Class<? extends Annotation> annotationClass) throws Throwable {
        for (Method method : test.getClass().getDeclaredMethods()) {
            if (method.isAnnotationPresent(annotationClass)) {
                out.log("Executing tear down method: " + method.getName());
                method.setAccessible(true);
                method.invoke(test);
            }
        }
    }

    private static void logSkipWholeTest(Class t, Throwable e) {
        out.log(skipWholeTestMessage(exceptionMessage(t, e)));
    }

    private static void logSkipTestCase(Class t, Throwable e) {
        out.log(skipTestCaseMessage(exceptionMessage(t, e)));
    }

    private static String skipWholeTestMessage(String errorMessage) {
        return errorMessage;
    }

    private static String skipTestCaseMessage(String errorMessage) {
        return errorMessage;
    }

    private static String exceptionMessage(Class t, Throwable e) {
        String message = t.getName() + " raised an exception:\n";
        message += "Exception type: " + e.getClass().getSimpleName() + "\n";
        message += "Exception message: " + e.getMessage();
        return message;
    }
}
