package com.capgemini.istat.vtlcompiler.vtlcommon.model.semantic;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;

/**
 * la classe contiene tutte le informazioni necessarie a rappresentare un messaggio di errore semantico.
 * Contiene il codice, il messaggio e le informazioni riguardanti la gravità dell'errore.
 * Solitamente viene popolato con i dati della classe ValidationCheck
 *
 * @see ValidationCheck
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SemanticMessage implements Serializable {

    private String code;
    private String message;
    private String[] parameters;
    private ErrorLevel errorLevel;
    private Boolean isBlockingFault;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String[] getParameters() {
        return parameters;
    }

    public void setParameters(String[] parameters) {
        this.parameters = parameters;
    }

    public ErrorLevel getErrorLevel() {
        return errorLevel;
    }

    public void setErrorLevel(ErrorLevel errorLevel) {
        this.errorLevel = errorLevel;
    }

    public Boolean getBlockingFault() {
        return isBlockingFault;
    }

    public void setBlockingFault(Boolean blockingFault) {
        isBlockingFault = blockingFault;
    }

    public enum ErrorLevel implements Serializable {
        GRAVE,
        WARNING,
        INFO
    }

    @Override
    public String toString() {
        return "SemanticMessage{" +
                "message='" + message + '\'' +
                ", errorLevel=" + errorLevel +
                ", isBlockingFault=" + isBlockingFault +
                '}';
    }
}
