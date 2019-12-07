package com.senyk.volodymyr.officetimelogger.exceptions;

public class NoSuchUserException extends Exception {
    public NoSuchUserException() {
        super("user with such number no found");
    }
}
