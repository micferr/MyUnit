package com.myunit.log;

public class LogLogger implements Logger {
    private java.util.logging.Logger logger;

    public LogLogger(java.util.logging.Logger logger) {
        this.logger = logger;
    }

    @Override
    public void log(String s) {
        logger.log(logger.getLevel(), s);
    }
}
