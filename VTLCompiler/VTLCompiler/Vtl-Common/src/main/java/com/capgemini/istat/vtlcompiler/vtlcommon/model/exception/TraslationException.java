package com.capgemini.istat.vtlcompiler.vtlcommon.model.exception;

public class TraslationException extends Exception {
    private String code;
    private String[] parameters;
    private String command;
    
    public TraslationException(String message) {
        super(message);
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String[] getParameters() {
        return parameters;
    }


    public void setParameters(String... parameters) {
        this.parameters = parameters;
    }

    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }
}
