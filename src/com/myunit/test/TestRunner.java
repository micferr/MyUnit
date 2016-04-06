package com.myunit.test;

import com.myunit.assertion.TestFailedError;
import com.myunit.log.Logger;
import com.myunit.log.StreamLogger;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class TestRunner {
    private Logger out;
    private int failedTests;
    private int executedTests;
    private boolean runOptionalTests;

    public TestRunner() {
        this(new StreamLogger(System.out));
    }

    public TestRunner(Logger logger) {
        out = logger;
        failedTests = 0;
        executedTests = 0;
        runOptionalTests = true;
    }

    public Logger getLogger() {
        return out;
    }

    public void setLogger(Logger stream) {
        out = stream;
    }

    public TestRunner runOptionalTests(boolean runOptTests) {
        this.runOptionalTests = runOptTests;
        return this;
    }

    public void run(Class... testClasses) {
        failedTests = 0;
        executedTests = 0;
        for (Class testClass : testClasses) {
            out.logTestBegin(testClass);
            try {
                Object test = buildTest(testClass);
                setUp(test);
                executeTests(test);
                tearDown(test);
            } catch (Throwable exception) {
                out.logSkipWholeTest(testClass, exception);
            }
            out.logTestEnd();
        }
        out.logSuiteResults(executedTests-failedTests, failedTests);
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

    private void executeTests(Object test) {
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
            boolean methodHasTestAnnotation = method.isAnnotationPresent(Test.class),
                    isOptionalTest =
                            methodHasTestAnnotation && method.getDeclaredAnnotation(Test.class).optional();
            if (methodHasTestAnnotation && (!isOptionalTest || runOptionalTests)) {
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
        out.logExecutingMethod(method);
        executedTests++;
        try {
            method.setAccessible(true);
            method.invoke(test);
            if (expectsExceptions(method)) {
                TestFailedError error = new TestFailedError(method.getName() +
                        " returned without throwing an exception, but expected one of type " +
                        method.getDeclaredAnnotation(Test.class).expected().getName());
                onFailedTest(error);
            } else {
                out.logTestCaseSuccess();
            }
        } catch (InvocationTargetException exception) {
            if (isExpectedException(exception, method)) {
                out.logTestCaseSuccess();
                return;
            }
            onFailedTest(exception.getCause());
        } catch (IllegalAccessException exception) {
            out.log("Should never happen.");
            onFailedTest(exception.getCause());
        }
    }

    private boolean expectsExceptions(Method method) {
        return method.getDeclaredAnnotation(Test.class).expected() != Test.None.class;
    }

    private boolean isExpectedException(Exception exception, Method method) {
        return exception.getCause().getClass().equals(method.getDeclaredAnnotation(Test.class).expected());
    }

    private void onFailedTest(Throwable throwable) {
        failedTests++;
        out.logTestCaseFail(throwable);
    }

    private void tearDown(Object test) throws Throwable {
        Class testClass = test.getClass();
        out.log("Tearing down " + testClass.getSimpleName() + "...");
        executeAnnotatedMethods(test, After.class);
    }

    private void executeAnnotatedMethods(Object test, Class<? extends Annotation> annotationClass) throws Throwable {
        for (Method method : test.getClass().getDeclaredMethods()) {
            if (method.isAnnotationPresent(annotationClass)) {
                out.log("Executing method: " + method.getName());
                method.setAccessible(true);
                method.invoke(test);
            }
        }
    }
}
