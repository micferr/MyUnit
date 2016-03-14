package com.myunit.log;

import java.io.PrintStream;

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
}
