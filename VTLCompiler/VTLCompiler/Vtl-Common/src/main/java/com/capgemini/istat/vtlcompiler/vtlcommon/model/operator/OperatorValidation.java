package com.capgemini.istat.vtlcompiler.vtlcommon.model.operator;

import com.capgemini.istat.vtlcompiler.vtlcommon.model.KeyVariables;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.semantic.ResultExpression;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.VtlExpression;

import java.util.LinkedList;
import java.util.Map;

/**
 * L'interfaccia che rappresenta tutti i nodi del motore di validazione
 */
public interface OperatorValidation {
    /**
     * Il metodo setta nello specifico nodo di validazione l'espressione derivata dal nodo di interpretazione
     *
     * @param vtlExpression il risultato del nodo di interpretazione
     */
    void setVtlExpression(VtlExpression vtlExpression);

    /**
     * Il metodo fornisce i risultati del singolo nodo semantico. Prende in ingresso una serie di variabili di esecuzione
     * e restituisce la lista delle trasformazioni fatte o propaga un errore di validazione
     *
     * @param variables le variabili di esecuzione che vengono trasmesse ai singoli nodi di validazione
     * @return una lista ordinata di risultati semantici.
     * @throws Exception le possibili eccezioni semantiche
     * @see ResultExpression
     */
    LinkedList<ResultExpression> resolve(Map<KeyVariables, Object> variables) throws Exception;

}
