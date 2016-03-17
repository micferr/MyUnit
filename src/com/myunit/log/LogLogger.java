package com.myunit.log;

import java.lang.reflect.Method;

public class LogLogger implements Logger {
    private java.util.logging.Logger logger;

    public LogLogger(java.util.logging.Logger logger) {
        this.logger = logger;
    }

    @Override
    public void log(String s) {
        logger.log(logger.getLevel(), s);
    }

    @Override
    public void logExceptionRaised(Class testClass, Method method, Throwable errorCause) {}

    @Override
    public void logTestEnd() { logger.log(logger.getLevel(), "\n"); }

    @Override
    public void logSuiteResults(int passedTests, int failedTests) {

    }
}
