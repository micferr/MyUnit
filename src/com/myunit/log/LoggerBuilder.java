package com.myunit.log;

import java.io.PrintStream;

public class LoggerBuilder {
    Object logger;
    LoggerType type;
    java.util.logging.Level logLevel;

    public LoggerBuilder setLogger(Object o) {
        logger = o;
        if (o instanceof PrintStream) {
            type = LoggerType.PRINT_STREAM;
        } else if (o instanceof java.util.logging.Logger) {
            type = LoggerType.JAVA_LOGGER;
        } else if (o instanceof Logger) {
            type = LoggerType.CUSTOM_LOGGER;
        } else {
            throw new IllegalArgumentException("Class " + o.getClass().getSimpleName() + "cannot be converted to Logger");
        }
        return this;
    }

    public LoggerBuilder setLogLevel(java.util.logging.Level level) {
        if (type == LoggerType.JAVA_LOGGER) {
            logLevel = level;
            return this;
        }
        throw new IllegalStateException("Cannot set log level on a Logger not built from a java.util.logging.Logger");
    }

    public Logger build() {
        switch (type) {
            case PRINT_STREAM:
                return new StreamLogger((PrintStream)logger);
            case JAVA_LOGGER:
                return new LogLogger((java.util.logging.Logger)logger);
            case CUSTOM_LOGGER:
                return (Logger)logger;
            default:
                throw new IllegalStateException("Invalid or unknown Logger");
        }
    }

    enum LoggerType {
        PRINT_STREAM,
        JAVA_LOGGER,
        CUSTOM_LOGGER
    }
}
