package com.capgemini.istat.vtlcompiler.vtlcommon.model.translation;

import com.capgemini.istat.vtlcompiler.vtlcommon.constant.ConstantUtility;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.dataset.VtlDataset;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.operator.Operator;
import com.healthmarketscience.sqlbuilder.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * La classe è un estensione sql del dataset. All'interno della classe sono presenti tutti i dati che servono per
 * contenere le informazioni delle QuerySql da costruire.
 * La classe conntiene informazioni sulle select, sulle condizioni di where, having group by e order
 * Vengono custodite anche le informazioni relative ai join fra dataset
 * Vengono inoltre offerti alcuni metodi di setting dell'alias delle tabelle e dei componenti.
 * Se nell'oggetto è presente più un di un oggetto sqlTables significa che l'SQLDataset rappresenta una join
 *
 * @see SqlComponent
 * @see SqlTable
 * @see SqlObject
 */
public class SqlDataset implements Serializable {

    private List<SqlComponent> componentList;
    private List<SqlTable> sqlTables = new ArrayList<>();
    private SqlObject whereCondition;
    private SqlObject havingCondition;
    private List<SqlObject> groupByClauseColumns;
    private List<OrderObject> orderByClauseColumns;

    public List<SqlTable> getSqlTables() {
        return sqlTables;
    }

    public void setSqlTables(List<SqlTable> sqlTables) {
        this.sqlTables = sqlTables;
    }


    public List<SqlComponent> getComponentList() {
        if (componentList == null)
            this.componentList = new ArrayList<>();
        return componentList;
    }

    public void setComponentList(List<SqlComponent> componentList) {
        this.componentList = componentList;
    }

    public SqlObject getWhereCondition() {
        return whereCondition;
    }

    public void setWhereCondition(SqlObject whereCondition) {
        this.whereCondition = whereCondition;
    }

    public SqlObject getHavingCondition() {
        return havingCondition;
    }

    public void setHavingCondition(SqlObject havingCondition) {
        this.havingCondition = havingCondition;
    }

    public List<SqlObject> getGroupByClauseColumns() {
        if (groupByClauseColumns == null)
            this.groupByClauseColumns = new LinkedList<>();
        return groupByClauseColumns;
    }

    public void setGroupByClauseColumns(List<SqlObject> groupByClauseColumns) {
        this.groupByClauseColumns = groupByClauseColumns;
    }

    public List<OrderObject> getOrderByClauseColumns() {
        if (orderByClauseColumns == null)
            this.orderByClauseColumns = new LinkedList<>();
        return orderByClauseColumns;
    }

    public void setOrderByClauseColumns(List<OrderObject> orderByClauseColumns) {
        this.orderByClauseColumns = orderByClauseColumns;
    }

    /**
     * setta l'alias della tabella sql alla tabella e a tutti i componenti collegati.
     *
     * @param newAlias   il nuovo alias da sostituire
     * @param vtlDataset il dataset a cui si applica all'alias
     */
    public void setUpAlias(String newAlias, VtlDataset vtlDataset) {
        if (vtlDataset == null) {
            vtlDataset = getSqlTables().get(0).getVtlDataset();
        }
        SqlTable sqlTableFound = null;
        for (SqlTable sqlTable : sqlTables) {
            if (sqlTable.getVtlDataset().equals(vtlDataset)) {
                sqlTableFound = sqlTable;
            }
        }
        if (sqlTableFound != null) {
            String oldAlias = sqlTableFound.getAliasName();
            if (componentList != null) {
                for (SqlComponent sqlComponent : componentList) {
                    sqlComponent.setUpAliasTable(oldAlias, newAlias);
                }
            }

            setUpAliasOnCondition(oldAlias, newAlias);
            setUpAliasWhere(oldAlias, newAlias);
            setUpAliasHaving(oldAlias, newAlias);
            setUpAliasGroupBy(oldAlias, newAlias);
            setUpAliasOrderBy(oldAlias, newAlias);
            sqlTableFound.setAliasName(newAlias);
        }
    }


    public List<SqlComponent> getCommonComponent(List<SqlComponent> sqlComponents) {
        List<SqlComponent> results = new ArrayList<>();
        for (SqlComponent sqlComponent : sqlComponents) {
            for (SqlComponent sqlComponent1 : componentList) {
                if (sqlComponent.getAliasName().equals(sqlComponent1.getAliasName()))
                    results.add(sqlComponent1);
            }
        }
        return results;
    }

    /**
     * Setta nella query il tipo di join effettuato
     *
     * @param operator un operatore di join
     */
    public void setUpAliasJoinType(Operator operator) {
        SelectQuery.JoinType joinType = null;
        if (operator == Operator.LEFT_JOIN) {
            joinType = SelectQuery.JoinType.LEFT_OUTER;
        } else if (operator == Operator.INNER_JOIN) {
            joinType = SelectQuery.JoinType.INNER;
        } else if (operator == Operator.CROSS_JOIN) {
            joinType = SelectQuery.JoinType.CROSS_JOIN;
        } else if (operator == Operator.FULL_JOIN) {
            joinType = SelectQuery.JoinType.FULL_OUTER;
        } else if (operator == Operator.RIGHT_JOIN) {
            joinType = SelectQuery.JoinType.RIGHT_OUTER;
        } else {
            joinType = SelectQuery.JoinType.INNER;
        }
        if (sqlTables.size() > 1) {
            for (int i = 1; i < sqlTables.size(); i++) {
                sqlTables.get(i).setJoinType(joinType);
            }
        }
    }

    /**
     * modifica l'alias sulle on condition
     *
     * @param oldAlias il vecchio alias dei componenti
     * @param alias    il nuovo alias da imporre
     */
    public void setUpAliasOnCondition(String oldAlias, String alias) {
        for (SqlTable sqlTable : sqlTables) {
            if (sqlTable.getOnConditions() != null) {
                String condition = sqlTable.getOnConditions().toString();
                if (oldAlias == null)
                    condition = condition.replace(ConstantUtility.DEFAULT_ALIAS_TABLE + ".", alias + ".");
                else
                    condition = condition.replace(oldAlias + ".", alias + ".");
                sqlTable.setOnConditions(new CustomCondition(condition));
            }
        }
    }

    /**
     * modifica l'alias sulle on condition
     *
     * @param oldAlias il vecchio alias dei componenti
     * @param alias    il nuovo alias da imporre
     */
    public void setUpAliasWhere(String oldAlias, String alias) {
        if (whereCondition != null) {
            String condition = whereCondition.toString();
            if (oldAlias == null)
                condition = condition.replace(ConstantUtility.DEFAULT_ALIAS_TABLE + ".", alias + ".");
            else
                condition = condition.replace(oldAlias + ".", alias + ".");
            this.whereCondition = new CustomCondition(condition);
        }
    }

    /**
     * modifica l'alias sulle where condition
     *
     * @param oldAlias il vecchio alias dei componenti
     * @param alias    il nuovo alias da imporre
     */
    public void setUpAliasHaving(String oldAlias, String alias) {
        if (havingCondition != null) {
            String condition = havingCondition.toString();
            if (oldAlias == null)
                condition = condition.replace(ConstantUtility.DEFAULT_ALIAS_TABLE + ".", alias + ".");
            else
                condition = condition.replace(oldAlias + ".", alias + ".");
            this.havingCondition = new CustomCondition(condition);
        }
    }

    /**
     * modifica l'alias sulle group by condition
     *
     * @param oldAlias il vecchio alias dei componenti
     * @param alias    il nuovo alias da imporre
     */
    public void setUpAliasGroupBy(String oldAlias, String alias) {
        if (groupByClauseColumns != null) {
            List groupByClauseColumnsAlias = new LinkedList<>();
            for (SqlObject sqlObject : groupByClauseColumns) {
                String strinColumn = sqlObject.toString();
                if (oldAlias == null)
                    strinColumn = strinColumn.replace(ConstantUtility.DEFAULT_ALIAS_TABLE + ".", alias + ".");
                else
                    strinColumn = strinColumn.replace(oldAlias + ".", alias + ".");
                groupByClauseColumnsAlias.add(new CustomSql(strinColumn));
            }
            groupByClauseColumns = groupByClauseColumnsAlias;
        }
    }

    /**
     * modifica l'alias sulle orderBy condition
     *
     * @param oldAlias il vecchio alias dei componenti
     * @param alias    il nuovo alias da imporre
     */
    public void setUpAliasOrderBy(String oldAlias, String alias) {
        if (orderByClauseColumns != null) {
            for (int i = 0; i < orderByClauseColumns.size(); ++i) {
                OrderObject orderObject = ((OrderObject) orderByClauseColumns.get(i));
                String strinColumn = orderObject.getObject().toString();
                if (oldAlias == null)
                    strinColumn = strinColumn.replace(ConstantUtility.DEFAULT_ALIAS_TABLE + ".", alias + ".");
                else
                    strinColumn = strinColumn.replace(oldAlias + ".", alias + ".");
                orderObject.setObject(new CustomSql(strinColumn));
                orderByClauseColumns.set(i, orderObject);
            }
        }
    }

    /**
     * Aggiunge le condition alla sqlDataset
     *
     * @param conditionList la lista di condizioni da aggiungere
     */
    public void setupConditions(List<BinaryCondition> conditionList) {
        if (sqlTables.size() > 1) {
            SqlTable firstTable = sqlTables.get(0);
            for (int i = 1; i < sqlTables.size(); i++) {
                SqlTable sqlTable = sqlTables.get(i);
                sqlTable.setupConditions(conditionList, firstTable.getAliasName(), sqlTable.getAliasName());
            }
        }
    }


}
