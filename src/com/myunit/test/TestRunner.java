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
        executePhase(test, TestPhase.SETUP);
    }

    private static void executeTests(Object test) throws Throwable {
        executePhase(test, TestPhase.EXECUTION);
    }

    private static void tearDown(Object test) throws Throwable {
        executePhase(test, TestPhase.TEARDOWN);
    }

    private static void executePhase(Object test, TestPhase testPhase) throws Throwable {
        Class<? extends Annotation> annotationClass;
        String actioningDescription;
        String actionDescription;
        switch (testPhase) {
            case SETUP:
                annotationClass = Before.class;
                actionDescription = "setup";
                actioningDescription = "Setting up";
                break;
            case EXECUTION:
                annotationClass = Test.class;
                actionDescription = "test";
                actioningDescription = "Testing";
                break;
            case TEARDOWN:
                annotationClass = After.class;
                actionDescription = "tear down";
                actioningDescription = "Tearing down";
                break;
            default: throw new IllegalArgumentException("Invalid test phase");
        }
        Class testClass = test.getClass();
        out.log(actioningDescription + " " + testClass.getSimpleName() + "...");
        for (Method method : testClass.getDeclaredMethods()) {
            if (method.isAnnotationPresent(annotationClass)) {
                out.log("Executing " + actionDescription +" method: " + method.getName());
                try {
                    method.setAccessible(true);
                    method.invoke(test);
                } catch (InvocationTargetException exception) {
                    if (testPhase == TestPhase.EXECUTION) {
                        logSkipTestCase(testClass, exception.getTargetException());
                    } else {
                        throw exception.getCause();
                    }
                }
            }
        }
    }

    private enum TestPhase {
        SETUP,
        EXECUTION,
        TEARDOWN
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
