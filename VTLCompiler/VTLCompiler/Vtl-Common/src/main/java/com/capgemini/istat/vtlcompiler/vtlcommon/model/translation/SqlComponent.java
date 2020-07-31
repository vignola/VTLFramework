package com.capgemini.istat.vtlcompiler.vtlcommon.model.translation;

import com.capgemini.istat.vtlcompiler.vtlcommon.constant.ConstantUtility;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.dataset.VtlComponent;
import com.healthmarketscience.sqlbuilder.CustomSql;
import com.healthmarketscience.sqlbuilder.SqlObject;

import java.io.Serializable;

/**
 * la classe è un estensione sql dei VTLComponent.
 * contiente tutte le informazioni necessarie a rappresentare le operazioni in colonna sulle tabelle
 * Offre alcuni metodi di setting alias
 */
public class SqlComponent implements Serializable {

    private VtlComponent vtlComponent;
    private SqlObject result;
    private String aliasName;
    private String aliasTable;


    public VtlComponent getVtlComponent() {
        return vtlComponent;
    }

    public void setVtlComponent(VtlComponent vtlComponent) {
        this.vtlComponent = vtlComponent;
    }

    public SqlObject getResult() {
        return result;
    }

    public void setResult(SqlObject result) {
        this.result = result;
    }

    public String getAliasName() {
        return aliasName;
    }

    public void setAliasName(String aliasName) {
        this.aliasName = aliasName;
    }

    public String getAliasTable() {
        return aliasTable;
    }

    /**
     * setta un alias sulla tabella
     *
     * @param aliasTable l'alias della tabella
     */
    public void setAliasTable(String aliasTable) {
        this.aliasTable = aliasTable;
    }

    /**
     * setta il nuovo alias della tabella al componente
     *
     * @param oldAlias il vecchio alias da sostituire
     * @param newAlias il nuovo alias da settare
     */
    public void setUpAliasTable(String oldAlias, String newAlias) {
        String sqlObjectString = result.toString();
        if (oldAlias == null)
            sqlObjectString = sqlObjectString.replace(ConstantUtility.DEFAULT_ALIAS_TABLE + ".", newAlias + ".");
        else
            sqlObjectString = sqlObjectString.replace(oldAlias + ".", newAlias + ".");
        this.result = new CustomSql(sqlObjectString);
        this.aliasTable = newAlias;
    }
}
