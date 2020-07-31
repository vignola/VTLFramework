package com.capgemini.istat.vtlcompiler.vtlcommon.model.translation;

import com.healthmarketscience.sqlbuilder.SqlObject;

import java.io.Serializable;
import java.util.LinkedList;

/**
 * La classe contiene tutti i dati necessari per propagare il risultato della traduzione da un nodo semantico all'altro
 * contiene il risultato di traduzione del singolo nodo, la lista dei risultati precedenti, un oggetto che rappresenta la
 * traduzione del dataset e uno che rappresenta la traduzione del componente.
 * Da questa classe viene estratto il risultato finale della traduzione
 *
 * @see SqlObject
 * @see SqlDataset
 * @see SqlComponent
 */
public class SqlResult implements Serializable {
    private SqlObject result;
    private LinkedList<SqlObject> resultList = new LinkedList<>();
    private SqlDataset sqlDataset;
    private SqlComponent sqlComponent;


    public SqlDataset getSqlDataset() {
        return sqlDataset;
    }

    public void setSqlDataset(SqlDataset sqlDataset) {
        this.sqlDataset = sqlDataset;
    }

    public SqlComponent getSqlComponent() {
        return sqlComponent;
    }

    public void setSqlComponent(SqlComponent sqlComponent) {
        this.sqlComponent = sqlComponent;
    }


    public SqlObject getResult() {
        return result;
    }

    public void setResult(SqlObject result) {
        this.result = result;
    }

    public LinkedList<SqlObject> getResultList() {
        return resultList;
    }

    public void setResultList(LinkedList<SqlObject> resultList) {
        this.resultList = resultList;
    }


}
