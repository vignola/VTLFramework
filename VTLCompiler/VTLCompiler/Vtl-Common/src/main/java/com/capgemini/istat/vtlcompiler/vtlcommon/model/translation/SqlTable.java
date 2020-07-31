package com.capgemini.istat.vtlcompiler.vtlcommon.model.translation;

import com.capgemini.istat.vtlcompiler.vtlcommon.model.dataset.VtlDataset;
import com.healthmarketscience.sqlbuilder.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * la classe rappresenta la tabella o le tabelle che intercorrono nelle query
 */
public class SqlTable implements Serializable {

    private VtlDataset vtlDataset;
    private String aliasName;
    private SqlObject onConditions;
    private SelectQuery.JoinType joinType = SelectQuery.JoinType.INNER;
    private SqlObject customFrom;

    public SqlTable() {
    }

    public SqlTable(VtlDataset vtlDataset) {
        this.vtlDataset = vtlDataset;
    }

    public VtlDataset getVtlDataset() {
        return vtlDataset;
    }

    public void setVtlDataset(VtlDataset vtlDataset) {
        this.vtlDataset = vtlDataset;
    }

    public String getAliasName() {
        return aliasName;
    }

    public void setAliasName(String aliasName) {
        this.aliasName = aliasName;
    }

    public SqlObject getOnConditions() {
        return onConditions;
    }

    public void setOnConditions(SqlObject onConditions) {
        this.onConditions = onConditions;
    }

    public SelectQuery.JoinType getJoinType() {
        return joinType;
    }

    public void setJoinType(SelectQuery.JoinType joinType) {
        this.joinType = joinType;
    }

    public SqlObject getCustomFrom() {
        return customFrom;
    }

    public void setCustomFrom(SqlObject customFrom) {
        this.customFrom = customFrom;
    }

    /**
     * Setta le condizioni che intercorrono nella on clause della join
     *
     * @param conditionList   la lista di condizioni da applicare
     * @param firstTableAlias l'alias della prima tabella
     * @param otherTableAlias l'alias della seconda tabella
     */
    public void setupConditions(List<BinaryCondition> conditionList, String firstTableAlias, String otherTableAlias) {
        List<Condition> binaryConditions = new ArrayList<>();
        for (BinaryCondition condition : conditionList) {
            String conditionString = condition.toString();
            if (conditionString.contains(firstTableAlias + ".") && conditionString.contains(otherTableAlias + ".")) {
                binaryConditions.add(condition);
            }
        }
        ComboCondition comboCondition = ComboCondition.and();
        comboCondition.addConditionsList(binaryConditions);
        this.onConditions = comboCondition;
    }
}
