package com.myunit.log;

import java.lang.reflect.Method;

public interface Logger {
    void log(String message);
    void logExceptionRaised(Class testClass, Method method, Throwable errorCause);
    void logExecutingMethod(Method method);
    void logTestCaseSuccess();
    void logTestCaseFail();
    void logSkipWholeTest(Class testClass, Throwable throwable);
    void logTestEnd();
    void logSuiteResults(int passedTests, int failedTests);
}
