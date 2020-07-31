package com.capgemini.istat.vtlcompiler.vtlapi.response;

public class ErrorResponse {
    private String code;
    private String message;
    private String command;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String messages) {
        this.message = messages;
    }

    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }
}
