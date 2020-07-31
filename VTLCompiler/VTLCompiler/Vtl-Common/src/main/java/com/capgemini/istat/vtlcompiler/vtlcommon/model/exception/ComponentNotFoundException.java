package com.capgemini.istat.vtlcompiler.vtlcommon.model.exception;

public class ComponentNotFoundException extends Exception {
    private String message;

    public ComponentNotFoundException(String message) {
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
