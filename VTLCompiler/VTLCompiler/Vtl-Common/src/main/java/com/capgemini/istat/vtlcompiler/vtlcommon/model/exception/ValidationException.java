package com.capgemini.istat.vtlcompiler.vtlcommon.model.exception;

import com.capgemini.istat.vtlcompiler.vtlcommon.model.semantic.SemanticMessage;

public class ValidationException extends Exception {
    private String command;
    private String liteCommand;
    private SemanticMessage semanticMessage;

    public ValidationException(String message) {
        super(message);
    }

    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    public SemanticMessage getSemanticMessage() {
        return semanticMessage;
    }

    public void setSemanticMessage(SemanticMessage semanticMessage) {
        this.semanticMessage = semanticMessage;
    }

    public String getLiteCommand() {
        return liteCommand;
    }

    public void setLiteCommand(String liteCommand) {
        this.liteCommand = liteCommand;
    }
}
