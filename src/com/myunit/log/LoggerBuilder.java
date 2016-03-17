package com.myunit.log;

import java.io.PrintStream;

public class LoggerBuilder {
    private Object logger;
    private LoggerType type;

    public LoggerBuilder setLogger(Object o) {
        logger = o;
        if (o instanceof PrintStream) {
            type = LoggerType.PRINT_STREAM;
        } else if (o instanceof Logger) {
            type = LoggerType.CUSTOM_LOGGER;
        } else {
            throw new IllegalArgumentException("Class " + o.getClass().getSimpleName() + "cannot be converted to Logger");
        }
        return this;
    }

    public Logger build() {
        switch (type) {
            case PRINT_STREAM:
                return new StreamLogger((PrintStream)logger);
            case CUSTOM_LOGGER:
                return (Logger)logger;
            default:
                throw new IllegalStateException("Invalid or unknown Logger");
        }
    }

    enum LoggerType {
        PRINT_STREAM,
        CUSTOM_LOGGER
    }
}
