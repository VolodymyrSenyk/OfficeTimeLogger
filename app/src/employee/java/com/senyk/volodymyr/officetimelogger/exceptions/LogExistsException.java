package com.senyk.volodymyr.officetimelogger.exceptions;

public class LogExistsException extends Exception {
    public LogExistsException() {
        super("log for this day exists");
    }
}
