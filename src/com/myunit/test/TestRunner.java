package com.myunit.test;

import com.myunit.assertion.TestFailedError;
import com.myunit.log.Logger;
import com.myunit.log.LoggerBuilder;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class TestRunner {
    private Logger out;

    public TestRunner() {
        out = new LoggerBuilder().setLogger(System.out).build();
    }

    public TestRunner(Object o) {
        out = new LoggerBuilder().setLogger(o).build();
    }

    public Logger getLogger() {
        return out;
    }

    public void setLogger(Logger stream) {
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
            out.log("");
        }
    }

    private Object buildTest(Class<?> testClass) throws Throwable {
        out.log("Building " + testClass.getSimpleName() + "...");
        return testClass.getConstructor().newInstance();
    }

    private void setUp(Object test) throws Throwable {
        Class testClass = test.getClass();
        out.log("Setting up " + testClass.getSimpleName() + "...");
        executeAnnotatedMethods(test, Before.class);
    }

    private void executeTests(Object test) throws Throwable {
        Class testClass = test.getClass();
        out.log("Testing " + testClass.getSimpleName() + "...");
        getSortedTests(testClass).forEach((method) -> {
            if (method.isAnnotationPresent(Test.class)) {
                testMethod(test, method);
            }
        });
    }

    private List<Method> getSortedTests(Class testClass) {
        Method[] methods = testClass.getDeclaredMethods();
        List<Method> tests = new ArrayList<>();
        for (Method method : methods) {
            if (method.isAnnotationPresent(Test.class)) {
                tests.add(method);
            }
        }
        tests.sort((o1, o2) -> {
            boolean o1isSorted = o1.isAnnotationPresent(Sorted.class);
            boolean o2isSorted = o2.isAnnotationPresent(Sorted.class);
            if (!o1isSorted && !o2isSorted) {
                return 0;
            } else if (o1isSorted && !o2isSorted){
                return -1;
            } else if (!o1isSorted) {
                return 1;
            } else {
                return o1.getDeclaredAnnotation(Sorted.class).value() -
                        o2.getDeclaredAnnotation(Sorted.class).value();
            }
        });
        return tests;
    }

    private void testMethod(Object test, Method method) {
        out.log("Executing test method: " + method.getName());
        try {
            method.setAccessible(true);
            method.invoke(test);
            if (expectsExceptions(method)) {
                throw new TestFailedError(method.getName() +
                        " returned without throwing an exception, but expected one of type " +
                        method.getDeclaredAnnotation(Test.class).expected().getName()
                );
            }
        } catch (InvocationTargetException exception) {
            if (isExpectedException(exception, method)) {
                return;
            }
            logSkipTestCase(test.getClass(), exception.getCause());
        } catch (IllegalAccessException exception) {
            out.log("Should never happen.");
        }
    }

    private boolean expectsExceptions(Method method) {
        return method.getDeclaredAnnotation(Test.class).expected() != Test.None.class;
    }

    private boolean isExpectedException(Exception exception, Method method) {
        return exception.getCause().getClass().equals(method.getDeclaredAnnotation(Test.class).expected());
    }

    private void tearDown(Object test) throws Throwable {
        Class testClass = test.getClass();
        out.log("Tearing down " + testClass.getSimpleName() + "...");
        executeAnnotatedMethods(test, After.class);
    }

    private void executeAnnotatedMethods(Object test, Class<? extends Annotation> annotationClass) throws Throwable {
        for (Method method : test.getClass().getDeclaredMethods()) {
            if (method.isAnnotationPresent(annotationClass)) {
                out.log("Executing tear down method: " + method.getName());
                method.setAccessible(true);
                method.invoke(test);
            }
        }
    }

    private void logSkipWholeTest(Class t, Throwable e) {
        out.log(skipWholeTestMessage(exceptionMessage(t, e)));
    }

    private void logSkipTestCase(Class t, Throwable e) {
        out.log(skipTestCaseMessage(exceptionMessage(t, e)));
    }

    private String skipWholeTestMessage(String errorMessage) {
        return errorMessage;
    }

    private String skipTestCaseMessage(String errorMessage) {
        return errorMessage;
    }

    private String exceptionMessage(Class t, Throwable e) {
        String message = t.getName() + " raised an exception:\n";
        message += "Exception type: " + e.getClass().getSimpleName() + "\n";
        message += "Exception message: " + e.getMessage();
        return message;
    }
}
