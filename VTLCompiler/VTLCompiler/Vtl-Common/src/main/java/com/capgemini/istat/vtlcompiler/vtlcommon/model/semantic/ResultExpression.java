package com.capgemini.istat.vtlcompiler.vtlcommon.model.semantic;

import com.capgemini.istat.vtlcompiler.vtlcommon.model.dataset.VtlComponent;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.dataset.VtlDataset;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * La classe contiene tutte le informazioni necessarie per rappresentare la soluzione del motore semantico.
 * All'interno della classe vengono raccolti i risultati di ogni singolo nodo.
 * La classe contiene:
 * <p>- un campo VtlDataset che rappresenta la struttura della tabella</p>
 * <p>- un campo VtlComponent che rappresenta il campo di una tabella</p>
 * <p>- una lista di messaggi semantici che contengono gli errori semantici riscontrati durante l'elaborazione</p>
 * <p>- una stringa che contiene la parte di linguaggio VTL che è in validazione</p>
 *
 * @see VtlDataset
 * @see VtlComponent
 * @see SemanticMessage
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResultExpression implements Serializable {

    private VtlDataset result;
    private VtlComponent resultComponent;
    private List<SemanticMessage> messages;
    private String command;

    public ResultExpression() {
        messages = new ArrayList<>();
    }

    public VtlDataset getResult() {
        return result;
    }

    public void setResult(VtlDataset result) {
        this.result = result;
    }

    public List<SemanticMessage> getMessages() {
        if (this.messages == null)
            this.messages = new ArrayList<>();
        return messages;
    }

    public void setMessages(List<SemanticMessage> messages) {
        this.messages = messages;
    }

    public VtlComponent getResultComponent() {
        if (isAScalar())
            return getScalarComponent();
        return resultComponent;
    }

    public void setResultComponent(VtlComponent resultComponent) {
        this.resultComponent = resultComponent;
    }

    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    public Boolean isAComponent() {
        return resultComponent != null;
    }

    public Boolean isAScalar() {
        return (result != null && result.isOnlyAScalar());
    }

    public Boolean isADataset() {
        return (result != null && !result.isOnlyAScalar());
    }

    public VtlComponent getScalarComponent() {
        if (result != null && result.getMeasures() != null && result.getMeasures().size() > 0 && result.isOnlyAScalar())
            return result.getMeasures().get(0);
        else
            return null;
    }


}
