package com.marsrobot.app.exception;

public class CliExceptionHandler implements Thread.UncaughtExceptionHandler {
    public void uncaughtException(Thread t, Throwable e) {
        System.err.println("ERROR: "+e.getMessage());
    }
}