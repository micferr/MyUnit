package com.myunit.log;

import java.io.PrintStream;
import java.lang.reflect.Method;

public class StreamLogger implements Logger {
    private PrintStream stream;

    public StreamLogger(PrintStream stream) {
        if (stream == null) {
            throw new NullPointerException("Provided PrintStream must not be null.");
        }
        this.stream = stream;
    }

    @Override
    public void log(String s) {
        stream.println(s);
    }

    @Override
    public void logExceptionRaised(Class testClass, Method method, Throwable errorCause) {
        String message = testClass.getName() + "#" + method.getName() + " raised an exception:\n" +
                "Exception type: " + errorCause.getClass().getSimpleName() + "\n" +
                "Exception message: " + errorCause.getMessage() + "\n";
        stream.print(message);
    }

    @Override
    public void logSkipWholeTest(Class testClass, Throwable throwable) {
        String message = testClass.getName() + " raised a fatal exception of type " +
                throwable.getClass().getName() + ". Testing of this class stopped.";
        stream.print(message);
    }

    @Override
    public void logTestEnd() { stream.println(""); }


    @Override
    public void logSuiteResults(int passedTests, int failedTests) {
        stream.print("Passed tests: " + passedTests + "\n" +
                "Failed tests: " + failedTests + "\n");
    }
}
