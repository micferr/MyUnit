package com.myunit.log;

import java.lang.reflect.Method;

public interface Logger {
    void log(String message);
    void logExceptionRaised(Class testClass, Method method, Throwable errorCause);
    void logTestEnd();
    void logSuiteResults(int passedTests, int failedTests);
}
