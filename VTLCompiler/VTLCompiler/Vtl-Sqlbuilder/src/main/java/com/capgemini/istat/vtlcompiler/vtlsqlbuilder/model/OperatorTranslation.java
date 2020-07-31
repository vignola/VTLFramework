package com.capgemini.istat.vtlcompiler.vtlsqlbuilder.model;

import com.capgemini.istat.vtlcompiler.vtlcommon.model.KeyVariables;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.translation.SqlResult;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.VtlExpression;
import com.capgemini.istat.vtlcompiler.vtlsqlbuilder.service.sqlresult.ISqlResultService;

import java.util.Map;

/**
 * L'interfaccia rappresenta tutte le classi facenti parte i nodi di traduzione SQL
 */
public interface OperatorTranslation {
    /**
     * Setta il servizio specifico (oracle, MySQL, ecc) necessario alla traduzione
     *
     * @param sqlResultService il servizi di traduzione
     */
    void setSqlResultService(ISqlResultService sqlResultService);

    /**
     * Setta l'oggetto di interpretazione
     *
     * @param vtlExpression l'oggetto che rappresenta l'espressione VTL
     */
    void setVtlExpression(VtlExpression vtlExpression);

    /**
     * .
     * il metodo si occupa di attivare la traduzione del nodo. Prede in ingresso delle variabili di esecuzione e restituisce
     * un ogggetto contenente il risultato della traduzione
     *
     * @param variables le variabili di esecuzione dei singoli nodi
     * @return un oggetto rappresentante la traduzione SQL
     * @throws Exception possibili errori di traduzione
     * @see SqlResult
     */
    SqlResult translate(Map<KeyVariables, Object> variables) throws Exception;


}
