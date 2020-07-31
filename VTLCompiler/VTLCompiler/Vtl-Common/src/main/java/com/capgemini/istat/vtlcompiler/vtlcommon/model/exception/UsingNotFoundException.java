package com.capgemini.istat.vtlcompiler.vtlcommon.model.exception;

public class UsingNotFoundException extends Exception {
    private String message;

    public UsingNotFoundException(String message) {
        super(message);
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
