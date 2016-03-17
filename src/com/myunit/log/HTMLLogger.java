package com.myunit.log;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.lang.reflect.Method;

public class HTMLLogger implements Logger {
    private PrintStream stream;
    private String logFile;
    private String extraLogs;
    private boolean autoOpenLog;
    private boolean writeEventLog;

    public HTMLLogger(String file) {
        try {
            stream = new PrintStream(file);
        } catch (FileNotFoundException e) {
            System.out.println("Error: Cannot open log file");
        }
        logFile = file;
        extraLogs = "";
        autoOpenLog = true;
        writeEventLog = false;
        stream.print("<html><head></head><body>");
    }

    public HTMLLogger openLogAfterTests(boolean autoOpenLog) {
        this.autoOpenLog = autoOpenLog;
        return this;
    }

    public HTMLLogger writeEventLog(boolean writeEventLog) {
        this.writeEventLog = writeEventLog;
        return this;
    }

    @Override
    public void log(String message) {
        extraLogs += message + "<br />";
    }

    @Override
    public void logExceptionRaised(Class testClass, Method method, Throwable errorCause) {
        extraLogs += testClass.getName() + "#" + method.getName() +
                "raised an exception of type " + errorCause.getClass().getName() +
                (errorCause.getMessage() != null ? "with message: " + errorCause.getMessage() : "") +
                "<br />";
    }

    @Override
    public void logExecutingMethod(Method method) {
        stream.print("<tr><td>"+method.getName()+"</td>");
    }

    @Override
    public void logTestCaseSuccess() {
        stream.print("<td><font color=\"green\">Success</font></td><td></td></tr>\n");
    }

    @Override
    public void logTestCaseFail(Throwable throwable) {
        stream.print("<td><font color=\"red\">Fail</font></td><td>"+throwable.getClass().getName());
        String message = throwable.getMessage();
        if (message != null) {
            stream.print(" - " + message);
        }
        stream.print("</td></tr>\n");
    }

    @Override
    public void logSkipWholeTest(Class testClass, Throwable throwable) {

    }

    @Override
    public void logTestBegin(Class testClass) {
        stream.print("<table>\n<tr><td colspan=\"3\">"+testClass.getName()+"</td></tr>\n");
    }

    @Override
    public void logTestEnd() {
        stream.print("</table><br />\n");
    }

    @Override
    public void logSuiteResults(int passedTests, int failedTests) {
        stream.print("</body></html>\n\n");
        if (writeEventLog) {
            stream.print("Logs:<br /><br />\n" + extraLogs + "<br />\n");
        }
        stream.print("Executed tests: " + (passedTests+failedTests) + "<br />\n"
                + "Passed: " + passedTests + "<br />\n"
                + "Failed: " + failedTests + "<br />\n");
        stream.close();
        if (autoOpenLog) {
            try {
                Runtime.getRuntime().exec("cmd.exe /c " + logFile);
            } catch (IOException exception) {
                System.out.println("Error: Cannot open log file.");
            }
        }
    }
}
