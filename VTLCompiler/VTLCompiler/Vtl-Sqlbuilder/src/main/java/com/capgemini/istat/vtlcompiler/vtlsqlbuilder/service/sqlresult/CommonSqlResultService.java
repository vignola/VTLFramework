package com.capgemini.istat.vtlcompiler.vtlsqlbuilder.service.sqlresult;

import com.capgemini.istat.vtlcompiler.vtlcommon.model.dataset.VtlComponentRole;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.dataset.VtlDataset;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.dataset.VtlType;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.operator.Operator;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.translation.SqlComponent;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.translation.SqlDataset;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.translation.SqlResult;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.translation.SqlTable;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.constant.VtlConstantExpression;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.dataset.VtlComponentId;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.dataset.VtlVarId;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.define.ruleset.datapoint.VtlDPRule;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.define.ruleset.hierarchical.VtlCodeItem;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.define.ruleset.hierarchical.VtlHRRule;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.VtlExpression;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.VtlMembership;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.clause.analytic.VtlAnalyticFunctionExpression;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.clause.calc.VtlCalcClauseItemExpression;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.clause.drop.VtlKeepOrDropClauseExpression;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.clause.pivot.VtlPivotClauseExpression;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.clause.rename.VtlRenameClauseExpression;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.clause.sub.VtlSubSpaceExpression;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.clause.unpivot.VtlPivotOrUnpivotClauseExpression;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.comparison.VtlBetweenExpression;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.comparison.VtlExistIn;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.comparison.VtlInExpression;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.conditional.VtlIfExpression;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.function.VtlBinaryExpression;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.function.VtlUnaryExpression;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.function.VtlUnaryWithOneParameter;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.generic.VtlCastExpression;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.hierarchy.VtlHierarchyExpression;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.join.VtlJoinExpression;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.join.VtlJoinSelectClause;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.set.VtlSetBinaryExpression;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.set.VtlSetUnnaryExpression;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.time.VtlCurrentDate;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.time.VtlTimeUnaryExpression;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.time.VtlTimeUnaryWithOneParameterExpression;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.validation.ValidationMode;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.validation.VtlCheck;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.validation.VtlCheckDatapoint;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.validation.VtlCheckHierarchy;
import com.capgemini.istat.vtlcompiler.vtlcommon.service.VtlTypeUtilityService;
import com.capgemini.istat.vtlcompiler.vtlsqlbuilder.service.utility.DbTableUtilityService;
import com.capgemini.istat.vtlcompiler.vtlsqlbuilder.service.utility.ISqlComponentUtilityService;
import com.capgemini.istat.vtlcompiler.vtlsqlbuilder.service.utility.ISqlDatasetUtilityService;
import com.capgemini.istat.vtlcompiler.vtlsqlbuilder.service.utility.ISqlObjectUtilityService;
import com.healthmarketscience.sqlbuilder.*;
import com.healthmarketscience.sqlbuilder.SelectQuery.JoinType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Implementa tutti i metodi per l'orchestrazione del risultato della traduzione degli operatori
 * comune a tutti i sql
 *
 * @see ISqlComponentUtilityService
 * @see ISqlDatasetUtilityService
 * @see ISqlObjectUtilityService
 * @see DbTableUtilityService
 * @see VtlTypeUtilityService
 */
@Service
public class CommonSqlResultService implements ISqlResultService {
    public static final String TX_BOOL_VAR_FALSE = "tx.BOOL_VAR = 'FALSE'";
    protected ISqlComponentUtilityService sqlComponentUtilityService;
    protected ISqlDatasetUtilityService sqlDatasetUtilityService;
    protected ISqlObjectUtilityService sqlObjectUtilityService;
    protected DbTableUtilityService dbTableUtilityService;
    protected VtlTypeUtilityService vtlTypeUtilityService;


    public void setSqlComponentUtilityService(ISqlComponentUtilityService sqlComponentUtilityService) {
        this.sqlComponentUtilityService = sqlComponentUtilityService;
    }


    public void setSqlDatasetUtilityService(ISqlDatasetUtilityService sqlDatasetUtilityService) {
        this.sqlDatasetUtilityService = sqlDatasetUtilityService;
    }

    public void setSqlObjectUtilityService(ISqlObjectUtilityService sqlObjectUtilityService) {
        this.sqlObjectUtilityService = sqlObjectUtilityService;
    }

    @Autowired
    public void setDbTableUtilityService(DbTableUtilityService dbTableUtilityService) {
        this.dbTableUtilityService = dbTableUtilityService;
    }

    @Autowired
    public void setVtlTypeUtilityService(VtlTypeUtilityService vtlTypeUtilityService) {
        this.vtlTypeUtilityService = vtlTypeUtilityService;
    }

    /**
     * Crea un oggetto sqlResult che restituisce il risultato di una select semplice su una tabella
     *
     * @param isAComponent
     * @param sqlDataset
     * @param vtlVarId
     * @return
     */
    @Override
    public SqlResult getVarIdTableResult(boolean isAComponent, SqlDataset sqlDataset, VtlVarId vtlVarId) {
        SqlResult sqlResult = new SqlResult();
        if (isAComponent) {
            sqlResult.setSqlDataset(
                    sqlDataset
            );

            sqlResult.setSqlComponent(
                    sqlComponentUtilityService.getSqlComponentByName(sqlResult.getSqlDataset(), vtlVarId.getName())
            );
        } else {
            sqlResult.setSqlDataset(
                    sqlDatasetUtilityService
                            .createSqlDataset(
                                    vtlVarId.getResultExpression().getResult()
                            )
            );
        }
        return sqlResult;
    }

    /**
     * Restituisce la rappresentazione di una selezione su un componente
     *
     * @param sqlDataset
     * @param vtlComponentId
     * @return
     */
    @Override
    public SqlResult getSqlComponentResult(SqlDataset sqlDataset, VtlComponentId vtlComponentId) {
        SqlResult sqlResult = new SqlResult();
        sqlResult.setSqlDataset(
                sqlDataset
        );

        sqlResult.setSqlComponent(
                sqlComponentUtilityService.getSqlComponentByName(sqlResult.getSqlDataset(), vtlComponentId.getComponentName())
        );
        return sqlResult;
    }

    /**
     * Applica una funzione di tipo time che possiede almeno un parametro e restituisce il risultato
     *
     * @param vtlTimeUnaryWithOneParameterExpression
     * @param sqlResult
     * @return
     */
    @Override
    public SqlResult applyTimeWithParameterFunction(VtlTimeUnaryWithOneParameterExpression vtlTimeUnaryWithOneParameterExpression, SqlResult sqlResult) {
        if (vtlTimeUnaryWithOneParameterExpression.getResultExpression().isAComponent()) {
            //Forse si evita con il custom params. Da controllare
            sqlResult.setSqlComponent(
                    sqlComponentUtilityService.applyUnaryFunctionToSqlComponent(
                            sqlResult.getSqlComponent(),
                            vtlTimeUnaryWithOneParameterExpression.getParameterExpression(),
                            vtlTimeUnaryWithOneParameterExpression.getOptionalParameterExpression(),
                            vtlTimeUnaryWithOneParameterExpression.getOperator()
                    )
            );
        } else {
            sqlResult.setSqlDataset(
                    sqlDatasetUtilityService.applyTimeFunctionToSqlDataset(
                            sqlResult.getSqlDataset(),
                            vtlTimeUnaryWithOneParameterExpression.getParameterExpression(),
                            vtlTimeUnaryWithOneParameterExpression.getOptionalParameterExpression(),
                            vtlTimeUnaryWithOneParameterExpression.getOperator()
                    )
            );
        }
        return sqlResult;
    }

    /**
     * Applica una funzione unaria di tipo time e restituisce il risultato SQL
     *
     * @param vtlTimeUnaryExpression
     * @param sqlResult
     * @return
     */
    @Override
    public SqlResult applyUnaryTimeFunction(VtlTimeUnaryExpression vtlTimeUnaryExpression, SqlResult sqlResult) {
        if (vtlTimeUnaryExpression.getResultExpression().isAComponent()) {
            sqlResult.setSqlComponent(
                    sqlComponentUtilityService.applyUnaryFunctionToSqlComponent(
                            sqlResult.getSqlComponent(),
                            null,
                            null,
                            vtlTimeUnaryExpression.getOperator()
                    )
            );
        } else {
            sqlResult.setSqlDataset(
                    sqlDatasetUtilityService.applyTimeFunctionToSqlDataset(
                            sqlResult.getSqlDataset(),
                            null,
                            null,
                            vtlTimeUnaryExpression.getOperator()
                    )
            );
        }
        return sqlResult;
    }

    /**
     * applica la funzione fill time series e restituisce il risultato SQL
     *
     * @param vtlTimeUnaryExpression
     * @param sqlResult
     * @return
     */
    @Override
    public SqlResult applyUnaryTimeFunctionFillTimeSeries(VtlTimeUnaryExpression vtlTimeUnaryExpression, SqlResult sqlResult) {
        SqlResult sqlResultUnary = new SqlResult();

        sqlResultUnary.setSqlDataset(
                sqlDatasetUtilityService.applyFillTimeSeriesFunctionToSqlDataset(vtlTimeUnaryExpression.getResultExpression().getResult())
        );

        sqlResult.getSqlDataset().setUpAlias(dbTableUtilityService.getDbSpec().getNextAlias(), sqlResult.getSqlDataset().getSqlTables().get(0).getVtlDataset());

        sqlResultUnary.getSqlDataset().getSqlTables().addAll(sqlResult.getSqlDataset().getSqlTables());

        sqlResultUnary.getSqlDataset().getComponentList().addAll(sqlComponentUtilityService.getSqlComponentsByRole(sqlResult.getSqlDataset().getComponentList(), VtlComponentRole.MEASURE));
        sqlResultUnary.getSqlDataset().getComponentList().addAll(sqlComponentUtilityService.getSqlComponentsByRole(sqlResult.getSqlDataset().getComponentList(), VtlComponentRole.VIRAL));

        LinkedList<List<SqlComponent>> components = new LinkedList<>();
        components.addLast(sqlComponentUtilityService.getSqlComponentsByRole(sqlResultUnary.getSqlDataset().getComponentList(), VtlComponentRole.IDENTIFIER));
        components.addLast(sqlComponentUtilityService.getSqlComponentsByRole(sqlResult.getSqlDataset().getComponentList(), VtlComponentRole.IDENTIFIER));

        List<BinaryCondition> onConditions = sqlObjectUtilityService.applyCommonComponentsCondition(
                sqlComponentUtilityService.getCommonComponent(components)
        );
        sqlResultUnary.getSqlDataset().setupConditions(onConditions);

        String timeIdentifier = null;
        List<String> identifierList = new LinkedList<>();

        for (SqlComponent sqlComponent : sqlComponentUtilityService.getSqlComponentsByRole(sqlResultUnary.getSqlDataset().getComponentList(), VtlComponentRole.IDENTIFIER)) {
            if (vtlTypeUtilityService.isDateTimeComponent(sqlComponent.getVtlComponent().getType())) {
                timeIdentifier = sqlComponent.getAliasName();
            } else {
                identifierList.add(sqlComponent.getAliasName());
            }
        }

        sqlResultUnary.getResultList().add(sqlObjectUtilityService.createExecuteFillTimeSeries(
                sqlResult.getSqlDataset().getSqlTables().get(0).getVtlDataset().getName(),
                timeIdentifier,
                identifierList,
                vtlTimeUnaryExpression.getOperator())
        );
        sqlResultUnary.getSqlDataset().getSqlTables().get(1).setJoinType(JoinType.LEFT_OUTER);

        return sqlResultUnary;
    }

    /**
     * applica una funzione unaria con un parametro e retituisce il risultato SQL
     *
     * @param vtlUnaryWithOneParameter
     * @param sqlResult
     * @return
     */
    @Override
    public SqlResult applyUnaryWithParameterFunction(VtlUnaryWithOneParameter vtlUnaryWithOneParameter, SqlResult sqlResult) {
        if (vtlUnaryWithOneParameter.getResultExpression().isAComponent()) {
            sqlResult.setSqlComponent(
                    sqlComponentUtilityService.applyUnaryFunctionToSqlComponent(
                            sqlResult.getSqlComponent(),
                            vtlUnaryWithOneParameter.getParameterExpression(),
                            vtlUnaryWithOneParameter.getOptionalParameterExpression(),
                            vtlUnaryWithOneParameter.getOperator()
                    )
            );
        } else {
            sqlResult.setSqlDataset(
                    sqlDatasetUtilityService.applyFunctionToSqlDataset(
                            sqlResult.getSqlDataset(),
                            vtlUnaryWithOneParameter.getParameterExpression(),
                            vtlUnaryWithOneParameter.getOptionalParameterExpression(),
                            vtlUnaryWithOneParameter.getOperator()
                    )
            );
        }
        return sqlResult;
    }

    /**
     * Applica una funzione di aggregazione e restituisce il risultato SQL
     *
     * @param vtlAggrInvocationExpression
     * @param withExpressione
     * @param sqlResult
     * @return
     */
    @Override
    public SqlResult applyAggregateFunction(VtlExpression vtlAggrInvocationExpression, boolean withExpressione, SqlResult sqlResult) {
        if (withExpressione) {
            if (vtlAggrInvocationExpression.getResultExpression().isAComponent()) {
                sqlResult.setSqlComponent(
                        sqlComponentUtilityService.applyUnaryFunctionToSqlComponent(
                                sqlResult.getSqlComponent(),
                                null,
                                null,
                                vtlAggrInvocationExpression.getOperator()
                        )
                );
            } else {
                sqlResult.setSqlDataset(
                        sqlDatasetUtilityService.applyAggregateFunctionToSqlDataset(
                                sqlResult.getSqlDataset(),
                                null,
                                null,
                                vtlAggrInvocationExpression.getOperator()
                        )
                );
            }
        } else {
            //Note that the count operator is used in an aggrClause without parameters,
            //e.g.: DS_1 [ aggr Me_1 := count ( ) group by Id_1 ) ]
            SqlComponent sqlComponent = sqlResult.getSqlComponent();
            sqlComponent.setResult(FunctionCall.countAll());
            sqlResult.setSqlComponent(sqlComponent);
        }

        return sqlResult;
    }

    /**
     * Applica una funzione analitica e restituisce il risultato SQL
     *
     * @param vtlAnalyticFunctionExpression
     * @param sqlResult
     * @return
     */
    @Override
    public SqlResult applyAnalyticFunction(VtlAnalyticFunctionExpression vtlAnalyticFunctionExpression, SqlResult sqlResult) {
        if (vtlAnalyticFunctionExpression.getResultExpression().isAComponent()) {
            sqlResult.setSqlComponent(
                    sqlComponentUtilityService.applyAnalyticFunctionToSqlComponent(
                            sqlResult.getSqlComponent(),
                            sqlResult.getSqlDataset(),
                            vtlAnalyticFunctionExpression,
                            vtlAnalyticFunctionExpression.getOperator()
                    )
            );
        } else {
            sqlResult.setSqlDataset(
                    sqlDatasetUtilityService.applyAnaliticFunctionToSqlDataset(
                            sqlResult.getSqlDataset(),
                            vtlAnalyticFunctionExpression,
                            vtlAnalyticFunctionExpression.getOperator()
                    )
            );
        }

        return sqlResult;
    }

    /**
     * Applica una funzione unaria e restituisce il risultato sql
     *
     * @param vtlUnaryExpression
     * @param sqlResult
     * @return
     */
    @Override
    public SqlResult applyUnaryFunction(VtlUnaryExpression vtlUnaryExpression, SqlResult sqlResult) {
        if (vtlUnaryExpression.getResultExpression().getResultComponent() != null) {
            sqlResult.setSqlComponent(
                    sqlComponentUtilityService.applyUnaryFunctionToSqlComponent(
                            sqlResult.getSqlComponent(),
                            null,
                            vtlUnaryExpression.getOptionalExpression(),
                            vtlUnaryExpression.getOperator()
                    )
            );
        } else {
            sqlResult.setSqlDataset(
                    sqlDatasetUtilityService.applyFunctionToSqlDataset(
                            sqlResult.getSqlDataset(),
                            null,
                            vtlUnaryExpression.getOptionalExpression(),
                            vtlUnaryExpression.getOperator()
                    )
            );
        }
        return sqlResult;
    }

    /**
     * applica una funzione binaria  a un dataset o a un componente e restituisce il risultato SQL
     *
     * @param vtlBinaryExpression
     * @param sqlResultLeft
     * @param sqlResultRight
     * @param operator
     * @return
     */
    @Override
    public SqlResult applyBinaryFunction(VtlBinaryExpression vtlBinaryExpression, SqlResult sqlResultLeft, SqlResult sqlResultRight, Operator operator) {
        SqlResult result = new SqlResult();
        if (sqlResultLeft.getSqlComponent() != null && sqlResultRight.getSqlComponent() != null) {
            result.setSqlComponent(
                    sqlComponentUtilityService.applyBinaryFunctionToSqlComponent(sqlResultLeft.getSqlComponent(), sqlResultRight.getSqlComponent(), operator)
            );
            //il dataset è lo stesso
            if (sqlResultLeft.getSqlDataset() != null && sqlResultRight.getSqlDataset() != null)
                binaryResult(sqlResultLeft, sqlResultRight, result);
        } else {
            result = applyBinaryFuctionToSqlDataset(vtlBinaryExpression, sqlResultLeft, sqlResultRight, operator);
        }
        return result;
    }

    private void binaryResult(SqlResult sqlResultLeft, SqlResult sqlResultRight, SqlResult result) {
        if (sqlResultLeft.getSqlDataset().getSqlTables().get(0).getVtlDataset().isOnlyAScalar() &&
                sqlResultRight.getSqlDataset().getSqlTables().get(0).getVtlDataset().isOnlyAScalar()) {
            result.getSqlComponent().setAliasTable(null);
            SqlDataset sqlDataset = new SqlDataset();
            sqlDataset.getComponentList().add(result.getSqlComponent());
            sqlDataset.setSqlTables(sqlResultLeft.getSqlDataset().getSqlTables());
            result.setSqlDataset(sqlDataset);
        } else if (sqlResultLeft.getSqlDataset().getComponentList().get(0).getAliasTable() == null) {
            result.setSqlDataset(sqlResultRight.getSqlDataset());
        } else {
            result.setSqlDataset(sqlResultLeft.getSqlDataset());
        }
    }

    /**
     * Applica una funzione binaria a due dataset
     *
     * @param vtlBinaryExpression
     * @param sqlResultLeft
     * @param sqlResultRight
     * @param operator
     * @return
     */
    @Override
    public SqlResult applyBinaryFuctionToSqlDataset(VtlBinaryExpression vtlBinaryExpression, SqlResult sqlResultLeft, SqlResult sqlResultRight, Operator operator) {
        SqlResult sqlResult = new SqlResult();
        if (sqlResultLeft.getSqlDataset().getSqlTables().get(0).getVtlDataset().isOnlyAScalar() ||
                sqlResultRight.getSqlDataset().getSqlTables().get(0).getVtlDataset().isOnlyAScalar()) {
            SqlDataset datasetToApply = null;
            SqlComponent scalarToApply = null;
            boolean isScalarLeft = false;
            if (sqlResultLeft.getSqlDataset().getSqlTables().get(0).getVtlDataset().isOnlyAScalar()) {
                datasetToApply = sqlResultRight.getSqlDataset();
                scalarToApply = sqlResultLeft.getSqlDataset().getComponentList().get(0);
                isScalarLeft = true;
            } else {
                datasetToApply = sqlResultLeft.getSqlDataset();
                scalarToApply = sqlResultRight.getSqlDataset().getComponentList().get(0);
            }
            for (SqlComponent sqlComponent : datasetToApply.getComponentList()) {
                if (sqlComponent.getVtlComponent().getVtlComponentRole() == VtlComponentRole.MEASURE) {
                    sqlResult.setSqlDataset(sqlDatasetUtilityService.applyBinaryFunctionScalar(datasetToApply, scalarToApply, isScalarLeft, operator));
                }
            }
        } else {
            //Se uno dei figli è VarId significa che è una tabella senza manipolazioni e non serve creare una tabella temporanea
            //Creo tabelle temporanee se servono
            SqlDataset sqlDatasetLeft = getTemporaryTable(sqlResult, vtlBinaryExpression.getLeftExpression(), sqlResultLeft);
            SqlDataset sqlDatasetRight = getTemporaryTable(sqlResult, vtlBinaryExpression.getRightExpression(), sqlResultRight);

            //Creo il dataset del prejoin
            SqlDataset sqlDatasetResult = sqlDatasetUtilityService.applyBinaryFunction(sqlDatasetLeft, sqlDatasetRight, operator);
            //creo la select join
            SqlResult sqlResultJoin = createTable(sqlDatasetResult, vtlBinaryExpression.getResultExpression().getResult().getName(), true);
            sqlResult.getResultList().addAll(sqlResultJoin.getResultList());
            sqlResult.setSqlDataset(sqlDatasetUtilityService.createSqlDataset(vtlBinaryExpression.getResultExpression().getResult()));
        }
        return sqlResult;
    }

    /**
     * Svolge in SQl la funzione di rinominazione del dataset
     *
     * @param datasetName
     * @param sqlObjects
     * @param assignName
     * @return
     */
    @Override
    public LinkedList<SqlObject> assignName(String datasetName, LinkedList<SqlObject> sqlObjects, String assignName) {
        LinkedList<SqlObject> resultSqlObjects = new LinkedList<>();
        for (SqlObject sqlObject : sqlObjects) {
            String sqlCommand = sqlObject.toString();
            sqlCommand = sqlCommand.replace(" " + datasetName + " ", " " + assignName + " ");
            if (datasetName.length() >= 15)
                sqlCommand = sqlCommand.replace(" " + datasetName + "_", " " + dbTableUtilityService.getDbSpec().getNextAlias() + "_");
            else
                sqlCommand = sqlCommand.replace(" " + datasetName + "_", " " + assignName + "_");
            resultSqlObjects.addLast(new CustomSql(sqlCommand));
        }
        return resultSqlObjects;
    }

    /**
     * Dato in ingresso un sqlDataset crea le istruzioni SQL di creazione corrispondenti
     *
     * @param sqlDataset
     * @param tableName
     * @param isTemporary
     * @return
     */
    @Override
    public SqlResult createTable(SqlDataset sqlDataset, String tableName, boolean isTemporary) {
        return createTable(sqlDataset, tableName, null, isTemporary);
    }

    protected SqlResult createTable(SqlDataset sqlDataset, String tableName, SqlObject selectQuery, boolean isTemporary) {
        SqlResult result = new SqlResult();
        if (selectQuery == null) {
            selectQuery = sqlObjectUtilityService.createSelectFromSqlDataset(sqlDataset);
        }
        //crea la tabella temporanea partendo dalla struttura della select

        result.getResultList().addLast(sqlObjectUtilityService.createTableQuery(tableName, selectQuery));
        //crea gli indici sugli identificativi
        result.getResultList().addAll(sqlObjectUtilityService.createIndex(sqlDataset.getComponentList(), tableName));
        //insert dei valori nella tabella temporanea
        if (isTemporary)
            result.getResultList().add(sqlObjectUtilityService.dropTableQuery(tableName));
        return result;
    }

    private SqlDataset getTemporaryTable(SqlResult result, VtlExpression vtlExpression, SqlResult sqlResultFrom) {
        SqlDataset sqlDataset = sqlResultFrom.getSqlDataset();
        if (!(vtlExpression instanceof VtlVarId) && (sqlResultFrom.getResultList().isEmpty() || !(sqlResultFrom.getResultList().getFirst() instanceof CreateTableQuery))) {
            SqlResult sqlLeftResult = createTable(sqlDataset, vtlExpression.getResultExpression().getResult().getName(), true);
            result.getResultList().addAll(sqlLeftResult.getResultList());
            sqlDataset = sqlDatasetUtilityService.createSqlDataset(vtlExpression.getResultExpression().getResult());
        }
        sqlDataset.setUpAlias(dbTableUtilityService.getDbSpec().getNextAlias(), sqlDataset.getSqlTables().get(0).getVtlDataset());
        return sqlDataset;
    }

    /**
     * Crea delle espressioni di create table prima della join per consolidare i risultati delle operazioni precedenti.
     * l'espressione viene creata solo se vengono individuate operazioni precedenti.
     *
     * @param vtlExpression
     * @param aliasName
     * @return
     */
    //crea una temporary table se non è un var ID
    @Override
    public SqlResult renameTableAndCreateTemporaryTable(VtlExpression vtlExpression, String aliasName) {
        SqlResult sqlResult = new SqlResult();
        SqlResult sqlResultFrom = vtlExpression.getSqlResult();
        SqlDataset sqlDataset = sqlResultFrom.getSqlDataset();
        if (!(vtlExpression instanceof VtlVarId) && (sqlResultFrom.getResultList().isEmpty() || !(sqlResultFrom.getResultList().getFirst() instanceof CreateTableQuery))) {
            sqlResult = createTable(sqlDataset, vtlExpression.getResultExpression().getResult().getName(), true);
            sqlDataset = sqlDatasetUtilityService.createSqlDataset(vtlExpression.getResultExpression().getResult());
            sqlResult.setSqlDataset(sqlDataset);
        }
        if (aliasName == null)
            aliasName = dbTableUtilityService.getDbSpec().getNextAlias();
        sqlDataset.setUpAlias(aliasName, sqlDataset.getSqlTables().get(0).getVtlDataset());
        return sqlResult;
    }

    /**
     * Effettua l'operazione di keep e restituisce il risultato sql
     *
     * @param vtlKeepClauseExpression
     * @param sqlResult
     * @return
     */
    @Override
    public SqlResult applyKeepClause(VtlKeepOrDropClauseExpression vtlKeepClauseExpression, SqlResult sqlResult) {
        sqlResult.setSqlDataset(sqlDatasetUtilityService.applyKeepClauseToSqlDataset(sqlResult.getSqlDataset(), vtlKeepClauseExpression));
        return sqlResult;
    }

    /**
     * Effettua l'operazione di Drop e restituisce il risultato sql
     *
     * @param vtlKeepOrDropClauseExpression
     * @param sqlResult
     * @return
     */
    @Override
    public SqlResult applyDropClause(VtlKeepOrDropClauseExpression vtlKeepOrDropClauseExpression, SqlResult sqlResult) {
        sqlResult.setSqlDataset(sqlDatasetUtilityService.applyDropClauseToSqlDataset(sqlResult.getSqlDataset(), vtlKeepOrDropClauseExpression));
        return sqlResult;
    }

    /**
     * applica la funzione di rename e restituisce il risultato SQL
     *
     * @param vtlRenameClauseExpression
     * @param sqlResult
     * @return
     */
    @Override
    public SqlResult applyRenameClause(VtlRenameClauseExpression vtlRenameClauseExpression, SqlResult sqlResult) {
        sqlResult.setSqlDataset(sqlDatasetUtilityService.applyRenameClauseToSqlDataset(sqlResult.getSqlDataset(), vtlRenameClauseExpression));
        return sqlResult;
    }

    /**
     * applica la funzione di subspace e restituisce il risultato SQL
     *
     * @param vtlSubSpaceExpression
     * @param sqlResult
     * @return
     */
    @Override
    public SqlResult applySubspaceClause(VtlSubSpaceExpression vtlSubSpaceExpression, SqlResult sqlResult) {
        sqlResult.setSqlDataset(sqlDatasetUtilityService.applySubspaceClauseToSqlDataset(sqlResult.getSqlDataset(), vtlSubSpaceExpression));
        return sqlResult;
    }

    /**
     * Effettua l'operazione sql di group by
     *
     * @param vtlDataset
     * @param sqlResult
     * @param sqlResultGroupAll
     * @return
     */
    @Override
    public SqlResult applyGroupClause(VtlDataset vtlDataset, SqlResult sqlResult, SqlResult sqlResultGroupAll) {
        sqlResult.setSqlDataset(sqlDatasetUtilityService.applyGroupClauseToSqlDataset(vtlDataset, sqlResult.getSqlDataset(), sqlResultGroupAll == null ? null : sqlResultGroupAll.getSqlComponent()));
        return sqlResult;
    }

    /**
     * applica un operatore unario in una where condition
     *
     * @param vtlUnaryExpression
     * @param sqlResult
     * @return
     */
    @Override
    public SqlResult applyUnaryCondition(VtlUnaryExpression vtlUnaryExpression, SqlResult sqlResult) {
        sqlResult.setSqlComponent(sqlComponentUtilityService.applyUnaryConditionToSqlComponent(sqlResult.getSqlComponent(), vtlUnaryExpression.getOperator(), vtlUnaryExpression.getVtlExpression()));
        return sqlResult;
    }

    /**
     * applica un operatore unario in una where condition
     *
     * @param vtlUnaryWithOneParameter
     * @param sqlResult
     * @return
     */
    @Override
    public SqlResult applyUnaryWithParameterCondition(VtlUnaryWithOneParameter vtlUnaryWithOneParameter, SqlResult sqlResult) {
        sqlResult.setSqlComponent(sqlComponentUtilityService.applyUnaryConditionWithParameterToSqlComponent(sqlResult.getSqlComponent(), vtlUnaryWithOneParameter.getOperator(), vtlUnaryWithOneParameter.getVtlExpression(), vtlUnaryWithOneParameter.getParameterExpression(), vtlUnaryWithOneParameter.getOptionalParameterExpression()));
        return sqlResult;
    }

    /**
     * applica un operatore condiziona binario in una where condition
     *
     * @param vtlBinaryExpression
     * @param sqlResultLeft
     * @param sqlResultRight
     * @param operator
     * @return
     */
    @Override
    public SqlResult applyBooleanBinaryCondition(VtlBinaryExpression vtlBinaryExpression, SqlResult sqlResultLeft, SqlResult sqlResultRight, Operator operator) {
        SqlResult result = new SqlResult();
        if (sqlResultLeft.getSqlComponent() != null && sqlResultRight.getSqlComponent() != null) {
            result.setSqlComponent(
                    sqlComponentUtilityService.applyBooleanBinaryConditionToSqlComponent(sqlResultLeft.getSqlComponent(), sqlResultRight.getSqlComponent(), operator, vtlBinaryExpression)
            );
            //il dataset è lo stesso
            if (sqlResultLeft.getSqlDataset() != null && sqlResultRight.getSqlDataset() != null)
                binaryResult(sqlResultLeft, sqlResultRight, result);
        } else {
            throw new UnsupportedOperationException("BooleanBinaryConditionToSqlDataset");
        }
        return result;
    }

    /**
     * applica un operazione di comparazione binaria in una where condition
     *
     * @param vtlBinaryExpression
     * @param sqlResultLeft
     * @param sqlResultRight
     * @param operator
     * @return
     */
    @Override
    public SqlResult applyComparisonBinaryCondition(VtlBinaryExpression vtlBinaryExpression, SqlResult sqlResultLeft, SqlResult sqlResultRight, Operator operator) {
        SqlResult result = new SqlResult();
        if (sqlResultLeft.getSqlComponent() != null && sqlResultRight.getSqlComponent() != null) {
            result.setSqlComponent(
                    sqlComponentUtilityService.applyComparisonBinaryConditionToSqlComponent(sqlResultLeft.getSqlComponent(), sqlResultRight.getSqlComponent(), operator, vtlBinaryExpression)
            );
            //il dataset è lo stesso
            if (sqlResultLeft.getSqlDataset() != null && sqlResultRight.getSqlDataset() != null)
                binaryResult(sqlResultLeft, sqlResultRight, result);
        } else {
            throw new UnsupportedOperationException("ComparisonBinaryConditionToSqlDataset");
        }
        return result;
    }

    /**
     * Applica un operatore condizionale in una where condition
     *
     * @param vtlBinaryExpression
     * @param sqlResultLeft
     * @param sqlResultRight
     * @param operator
     * @return
     */
    @Override
    public SqlResult applyConditionalBinaryCondition(VtlBinaryExpression vtlBinaryExpression, SqlResult sqlResultLeft, SqlResult sqlResultRight, Operator operator) {
        SqlResult result = new SqlResult();
        if (sqlResultLeft.getSqlComponent() != null && sqlResultRight.getSqlComponent() != null) {
            result.setSqlComponent(
                    sqlComponentUtilityService.applyConditionalBinaryConditionToSqlComponent(sqlResultLeft.getSqlComponent(), sqlResultRight.getSqlComponent(), operator, vtlBinaryExpression)
            );
            //il dataset è lo stesso
            if (sqlResultLeft.getSqlDataset() != null && sqlResultRight.getSqlDataset() != null)
                binaryResult(sqlResultLeft, sqlResultRight, result);
        } else {
            throw new UnsupportedOperationException("ConditionalBinaryConditionToSqlDataset");
        }
        return result;
    }

    /**
     * Applica un operatore numerico binario in una where condition
     *
     * @param vtlBinaryExpression
     * @param sqlResultLeft
     * @param sqlResultRight
     * @param operator
     * @return
     */
    @Override
    public SqlResult applyNumericBinaryCondition(VtlBinaryExpression vtlBinaryExpression, SqlResult sqlResultLeft, SqlResult sqlResultRight, Operator operator) {
        SqlResult result = new SqlResult();
        if (sqlResultLeft.getSqlComponent() != null && sqlResultRight.getSqlComponent() != null) {
            result.setSqlComponent(
                    sqlComponentUtilityService.applyNumericOrStringBinaryConditionToSqlComponent(sqlResultLeft.getSqlComponent(), sqlResultRight.getSqlComponent(), operator, vtlBinaryExpression)
            );
            //il dataset è lo stesso
            if (sqlResultLeft.getSqlDataset() != null && sqlResultRight.getSqlDataset() != null)
                binaryResult(sqlResultLeft, sqlResultRight, result);
        } else {
            throw new UnsupportedOperationException("NumericBinaryConditionToSqlDataset");
        }
        return result;
    }

    /**
     * Applica un operatore di stringa binario in una where condition
     *
     * @param vtlBinaryExpression
     * @param sqlResultLeft
     * @param sqlResultRight
     * @param operator
     * @return
     */
    @Override
    public SqlResult applyStringBinaryCondition(VtlBinaryExpression vtlBinaryExpression, SqlResult sqlResultLeft, SqlResult sqlResultRight, Operator operator) {
        SqlResult result = new SqlResult();
        if (sqlResultLeft.getSqlComponent() != null && sqlResultRight.getSqlComponent() != null) {
            result.setSqlComponent(
                    sqlComponentUtilityService.applyNumericOrStringBinaryConditionToSqlComponent(sqlResultLeft.getSqlComponent(), sqlResultRight.getSqlComponent(), operator, vtlBinaryExpression)
            );
            //il dataset è lo stesso
            if (sqlResultLeft.getSqlDataset() != null && sqlResultRight.getSqlDataset() != null)
                binaryResult(sqlResultLeft, sqlResultRight, result);
        } else {
            throw new UnsupportedOperationException("StringBinaryConditionToSqlDataset");
        }
        return result;
    }

    /**
     * aggiunge le parentesi a un espressione SQL
     *
     * @param sqlResult
     * @return
     */
    @Override
    public SqlResult applyParentheses(SqlResult sqlResult) {
        if (sqlResult.getSqlComponent() != null) {
            sqlResult.getSqlComponent().setResult(sqlObjectUtilityService.applParenthesesToSqlObject(sqlResult.getSqlComponent().getResult()));
        }

        return sqlResult;
    }

    /**
     * Applica la funzione in not in e genera un espressione SQL
     *
     * @param vtlInExpression
     * @param sqlResult
     * @return
     */
    @Override
    public SqlResult applyInNotInFunction(VtlInExpression vtlInExpression, SqlResult sqlResult) {
        if (vtlInExpression.getResultExpression().getResultComponent() != null) {
            sqlResult.setSqlComponent(sqlComponentUtilityService.applyInNotInFunctionToSqlComponent(sqlResult.getSqlComponent(), vtlInExpression.getOperator(), vtlInExpression));
        } else {
            sqlResult.setSqlDataset(
                    sqlDatasetUtilityService.applyInNotInFunctionToSqlDataset(
                            sqlResult.getSqlDataset(),
                            vtlInExpression.getOperator(),
                            vtlInExpression
                    )
            );
        }
        return sqlResult;
    }

    /**
     * Applica una funzione in not in in una where condition
     *
     * @param vtlInExpression
     * @param sqlResult
     * @return
     */
    @Override
    public SqlResult applyInNotInCondition(VtlInExpression vtlInExpression, SqlResult sqlResult) {
        sqlResult.setSqlComponent(sqlComponentUtilityService.applyInNotInConditionToSqlComponent(sqlResult.getSqlComponent(), vtlInExpression.getOperator(), vtlInExpression));
        return sqlResult;
    }

    /**
     * applica la funzione between e genera un espressione sql
     *
     * @param vtlBetweenExpression
     * @param sqlResult
     * @return
     */
    @Override
    public SqlResult applyBetweenFunction(VtlBetweenExpression vtlBetweenExpression, SqlResult sqlResult) {
        if (vtlBetweenExpression.getResultExpression().getResultComponent() != null) {
            sqlResult.setSqlComponent(sqlComponentUtilityService.applyBetweenFunctionToSqlComponent(sqlResult.getSqlComponent(), vtlBetweenExpression.getOperator(), vtlBetweenExpression));
        } else {
            sqlResult.setSqlDataset(
                    sqlDatasetUtilityService.applyBetweenFunctionToSqlDataset(
                            sqlResult.getSqlDataset(),
                            vtlBetweenExpression.getOperator(),
                            vtlBetweenExpression
                    )
            );
        }
        return sqlResult;
    }

    /**
     * applica la funzione between in una where condition
     *
     * @param vtlBetweenExpression
     * @param sqlResult
     * @return
     */
    @Override
    public SqlResult applyBetweenCondition(VtlBetweenExpression vtlBetweenExpression, SqlResult sqlResult) {
        sqlResult.setSqlComponent(sqlComponentUtilityService.applyBetweenConditionToSqlComponent(sqlResult.getSqlComponent(), vtlBetweenExpression.getOperator(), vtlBetweenExpression));
        return sqlResult;
    }

    /**
     * Esegue l'operazione di aggregazione su un dataset eliminando i componenti non indicati e restituisce la traduzione Sql
     *
     * @param vtlDataset
     * @param sqlComponents
     * @param sqlDataset
     * @return
     */
    @Override
    public SqlResult aggregateSqlComponent(VtlDataset vtlDataset, List<SqlComponent> sqlComponents, SqlDataset sqlDataset) {
        SqlResult sqlresult = new SqlResult();
        sqlresult.setSqlDataset(sqlDatasetUtilityService.aggregateSqlComponent(vtlDataset, sqlComponents, sqlDataset));
        sqlresult.setSqlComponent(null);
        return sqlresult;
    }

    /**
     * Esegue l'opearzione di mebership e restituisce la traduzione sql
     *
     * @param vtlMembership
     * @param sqlResult
     * @return
     */
    @Override
    public SqlResult applyMembership(VtlMembership vtlMembership, SqlResult sqlResult) {
        if (vtlMembership.getResultExpression().getResultComponent() != null) {
            sqlResult.setSqlComponent(sqlComponentUtilityService.getSqlComponentByName(sqlResult.getSqlDataset(), vtlMembership.getVtlComponentId().getComponentName()));
        } else {
            sqlResult.setSqlDataset(sqlDatasetUtilityService.applyMembership(vtlMembership, sqlResult.getSqlDataset()));
        }
        return sqlResult;
    }

    /**
     * Applica l'operatre di Union,intercept e simili su una lista di dataset
     *
     * @param vtlSetUnnaryExpression
     * @param sqlResultList
     * @return
     */
    @Override
    public SqlResult applySetUnnary(VtlSetUnnaryExpression vtlSetUnnaryExpression, List<SqlResult> sqlResultList) {
        List<SqlDataset> sqlDatasets = buildSqlDatasetWithPriorityComponents(sqlResultList);

        SqlObject sqlObject = sqlObjectUtilityService.createUnionQueryForSetUnnary(sqlDatasets, vtlSetUnnaryExpression.getOperator());

        SqlDataset sqlDataset = sqlDatasetUtilityService.createSqlDataset(vtlSetUnnaryExpression.getResultExpression().getResult());

        //Creo la tabella temporanea
        SqlResult sqlResult = createTable(sqlDataset, vtlSetUnnaryExpression.getResultExpression().getResult().getName(), (SelectQuery) sqlObject, true);

        sqlResult.setSqlDataset(sqlDataset);
        return sqlResult;
    }

    private List<SqlDataset> buildSqlDatasetWithPriorityComponents(List<SqlResult> sqlResultList) {
        List<SqlDataset> datasets = new ArrayList<>();
        for (int i = 0; i < sqlResultList.size(); i++) {
            SqlDataset sqlDatasetCopied = sqlDatasetUtilityService.copySqlDatasetWithSqlComponent(sqlResultList.get(i).getSqlDataset(), sqlResultList.get(i).getSqlDataset().getComponentList());
            sqlDatasetCopied.getComponentList().add(sqlComponentUtilityService.createPriorityComponent(i + 1));
            datasets.add(sqlDatasetUtilityService.getDatasetOrdered(sqlDatasetCopied));
        }
        return datasets;
    }

    /**
     * applica l'operatore di setDif/symDif e restituisce la traduzione SQL
     *
     * @param vtlSetBinaryExpression
     * @param sqlResultLeft
     * @param sqlResultRight
     * @return
     */
    @Override
    public SqlResult applySetBinary(VtlSetBinaryExpression vtlSetBinaryExpression, SqlResult sqlResultLeft, SqlResult sqlResultRight) {

        sqlResultLeft.getSqlDataset().setUpAlias(dbTableUtilityService.getDbSpec().getNextAlias(),
                sqlResultLeft.getSqlDataset().getSqlTables().get(0).getVtlDataset());

        sqlResultRight.getSqlDataset().setUpAlias(dbTableUtilityService.getDbSpec().getNextAlias(),
                sqlResultRight.getSqlDataset().getSqlTables().get(0).getVtlDataset());

        SqlDataset sqlDsBinaryJoin = sqlDatasetUtilityService.createQueryForSetBinaryJoin(sqlResultLeft.getSqlDataset(), sqlResultRight.getSqlDataset());
        SelectQuery selectQuery =  sqlObjectUtilityService.createSelectFromSqlDataset(sqlDsBinaryJoin);
        SqlObject sqlObjResult = selectQuery;

        if (vtlSetBinaryExpression.getOperator().getValue().equals(Operator.SYMDIFF.getValue())) {
            SqlDataset sqlDsBinaryJoinAfterUnion = sqlDatasetUtilityService.createQueryForSetBinaryJoin(sqlResultRight.getSqlDataset(), sqlResultLeft.getSqlDataset());
            SelectQuery selectQueryAfterUnion =  sqlObjectUtilityService.createSelectFromSqlDataset(sqlDsBinaryJoinAfterUnion);
            UnionQuery unionQuery = UnionQuery.unionAll();
            unionQuery.addQueries(selectQuery);
            unionQuery.addQueries(selectQueryAfterUnion);
            sqlObjResult = unionQuery;

        }

        SqlDataset sqlDataset = sqlDatasetUtilityService.createSqlDataset(vtlSetBinaryExpression.getResultExpression().getResult());

        //Creo la tabella temporanea
        SqlResult sqlResult = createTable(sqlDataset, vtlSetBinaryExpression.getResultExpression().getResult().getName(), sqlObjResult, true);


        sqlResult.setSqlDataset(sqlDataset);
        return sqlResult;
    }

    /**
     * applica la funzione di exist in e retituisce la traduzione SQL
     *
     * @param vtlExistIn
     * @return
     */
    @Override
    public SqlResult applyExistIn(VtlExistIn vtlExistIn) {
        SqlResult sqlResult = new SqlResult();

        VtlExistIn vei = vtlExistIn;
        vei.getLeftExpression().getSqlResult().getSqlDataset().setUpAlias(
                dbTableUtilityService.getDbSpec().getNextAlias(),
                vei.getLeftExpression().getSqlResult().getSqlDataset().getSqlTables().get(0).getVtlDataset());

        vei.getRightExpression().getSqlResult().getSqlDataset().setUpAlias(
                dbTableUtilityService.getDbSpec().getNextAlias(),
                vei.getRightExpression().getSqlResult().getSqlDataset().getSqlTables().get(0).getVtlDataset());

        sqlResult.setSqlDataset(sqlDatasetUtilityService.applyExistIn(
                vei.getLeftExpression().getSqlResult(),
                vei.getRightExpression().getSqlResult(),
                vtlExistIn.getResultExpression().getResult(),
                vtlExistIn.getRetain()));
        return sqlResult;
    }

    /**
     * Crea il sql per generare il dataset di prejoin per l'operazione di join
     *
     * @param vtlJoinSelectClause
     * @param operator
     * @return
     */
    @Override
    public SqlResult preJoinRenames(VtlJoinSelectClause vtlJoinSelectClause, Operator operator) {
        SqlResult sqlResult;
        if (operator != Operator.FULL_JOIN) {
            SqlDataset sqlDatasetPreJoin = sqlDatasetUtilityService.buildPreJoinTable(vtlJoinSelectClause, operator);
            sqlResult = createTable(
                    sqlDatasetPreJoin,
                    vtlJoinSelectClause.getResultExpression().getResult().getName(),
                    true
            );
        } else {
            SqlDataset sqlDatasetLeftJoin = sqlDatasetUtilityService.buildPreJoinTable(vtlJoinSelectClause, Operator.LEFT_JOIN);
            SqlDataset sqlDatasetRightJoin = sqlDatasetUtilityService.copySqlDatasetWithSqlComponent(sqlDatasetLeftJoin, sqlDatasetLeftJoin.getComponentList());
            sqlDatasetRightJoin.setUpAliasJoinType(Operator.RIGHT_JOIN);

            SelectQuery selectQueryLeft = sqlObjectUtilityService.createSelectFromSqlDataset(sqlDatasetLeftJoin);
            SelectQuery selectQueryRight = sqlObjectUtilityService.createSelectFromSqlDataset(sqlDatasetRightJoin);
            UnionQuery unionQuery = UnionQuery.union();
            unionQuery.addQueries(selectQueryLeft, selectQueryRight);
            sqlResult = createTable(
                    sqlDatasetUtilityService.createSqlDataset(vtlJoinSelectClause.getResultExpression().getResult()),
                    vtlJoinSelectClause.getResultExpression().getResult().getName(),
                    unionQuery,
                    true
            );
        }
        sqlResult.setSqlDataset(sqlDatasetUtilityService.createSqlDataset(vtlJoinSelectClause.getResultExpression().getResult()));
        return sqlResult;
    }

    /**
     * effettua l'operazioni SQl per ripulire il dataset al termine dell'operazione di join
     *
     * @param vtlJoinExpression
     * @return
     */
    @Override
    public SqlResult cleanJoinNames(VtlJoinExpression vtlJoinExpression) {
        SqlResult sqlResult;
        SqlDataset cleanDataset = sqlDatasetUtilityService.cleanJoinTable(vtlJoinExpression);
        sqlResult = createTable(
                cleanDataset,
                vtlJoinExpression.getResultExpression().getResult().getName(),
                true
        );
        sqlResult.setSqlDataset(
                sqlDatasetUtilityService.createSqlDataset(vtlJoinExpression.getResultExpression().getResult())
        );
        return sqlResult;
    }

    /**
     * effettua l'operazione SQl espressa da una cal su un componente del dataset
     *
     * @param vtlCalcClauseItemExpression
     * @return
     */
    @Override
    public SqlResult calcItemExpression(VtlCalcClauseItemExpression vtlCalcClauseItemExpression) {
        SqlResult sqlResult = new SqlResult();
        SqlComponent calcSqlComponent = sqlComponentUtilityService.copySqlComponent(vtlCalcClauseItemExpression.getVtlExpression().getSqlResult().getSqlComponent());
        calcSqlComponent.setAliasName(vtlCalcClauseItemExpression.getVtlComponentId().getComponentName());
        //Cambio ruolo su VTLCOMPONENT
        calcSqlComponent.setVtlComponent(vtlCalcClauseItemExpression.getResultExpression().getResultComponent());

        sqlResult.setSqlComponent(calcSqlComponent);
        return sqlResult;
    }

    /**
     * Applica l'operazione di filter/having by e restituisce l'istruzione SQL
     *
     * @param sqlResult
     * @param vtlExpression
     * @return
     */
    @Override
    public SqlResult applyDefaultBooleanUnaryConditionToSqlObject(SqlResult sqlResult, VtlExpression vtlExpression) {
        sqlResult.getSqlDataset().setWhereCondition(sqlObjectUtilityService.applyDefaultBooleanUnaryConditionToSqlObject(sqlResult.getSqlComponent().getResult(), vtlExpression));
        return sqlResult;
    }

    /**
     * applica la funzione di currentDate e restituisce la traduzione sql
     *
     * @param vtlCurrentDate
     * @return
     */
    @Override
    public SqlResult applyCurrentDate(VtlCurrentDate vtlCurrentDate) {
        SqlResult sqlResult = new SqlResult();
        sqlResult.setSqlComponent(sqlComponentUtilityService.applyCurrentDateToSqlComponent(vtlCurrentDate));
        return sqlResult;
    }

    /**
     * applica l'operatore if e restituisce la traduzione sql
     *
     * @param vtlIfExpression
     * @param sqlResultCondition
     * @param sqlResultThen
     * @param sqlResultElse
     * @param isWhereCondition
     * @return
     */
    @Override
    public SqlResult applyIf(VtlIfExpression vtlIfExpression, SqlResult sqlResultCondition, SqlResult sqlResultThen, SqlResult sqlResultElse, Boolean isWhereCondition) {
        SqlResult sqlResult = new SqlResult();

        if (vtlIfExpression.getResultExpression().isAComponent() || vtlIfExpression.getResultExpression().isAScalar()) {
            sqlResult.setSqlComponent(sqlComponentUtilityService.applyIfToSqlComponent(sqlResultCondition.getSqlComponent(), sqlResultThen.getSqlComponent(), sqlResultElse.getSqlComponent(), vtlIfExpression.getOperator(), isWhereCondition));
            sqlResult.getSqlComponent().setVtlComponent(vtlIfExpression.getResultExpression().getResultComponent());
            if (vtlIfExpression.getResultExpression().isAScalar()) {
                sqlResult.setSqlDataset(sqlDatasetUtilityService.createSqlDataset(vtlIfExpression.getResultExpression().getResult()));
                sqlResult.getSqlDataset().getComponentList().get(0).setResult(sqlResult.getSqlComponent().getResult());
            }
        } else {
            //Creo tabelle temporanee se servono
            SqlDataset sqlDatasetCondition = getTemporaryTable(sqlResult, vtlIfExpression.getCondition(), sqlResultCondition);
            SqlDataset sqlDatasetThen = getTemporaryTable(sqlResult, vtlIfExpression.getThenExpr(), sqlResultThen);
            SqlDataset sqlDatasetElse = getTemporaryTable(sqlResult, vtlIfExpression.getElseExpr(), sqlResultElse);

            SqlDataset sqlDatasetResultThen = sqlDatasetUtilityService.applyIfToSqlDataset(sqlDatasetCondition, sqlDatasetThen, true);
            SqlDataset sqlDatasetResultElse = sqlDatasetUtilityService.applyIfToSqlDataset(sqlDatasetCondition, sqlDatasetElse, false);

            //creo le select join
            SelectQuery selectQueryThen = sqlObjectUtilityService.createSelectFromSqlDataset(sqlDatasetResultThen);
            SelectQuery selectQueryElse = sqlObjectUtilityService.createSelectFromSqlDataset(sqlDatasetResultElse);

            UnionQuery unionQuery = UnionQuery.union();
            unionQuery.addQueries(selectQueryThen);
            unionQuery.addQueries(selectQueryElse);

            SqlResult sqlResultUnion = createTable(sqlDatasetResultThen, vtlIfExpression.getResultExpression().getResult().getName(), unionQuery, true);

            sqlResult.getResultList().addAll(sqlResultThen.getResultList());
            sqlResult.getResultList().addAll(sqlResultElse.getResultList());
            sqlResult.getResultList().addAll(sqlResultUnion.getResultList());
            sqlResult.setSqlDataset(sqlDatasetUtilityService.createSqlDataset(vtlIfExpression.getResultExpression().getResult()));
        }

        return sqlResult;
    }

    /**
     * applica l'operazione di unpivot e restituisce la traduzione sql
     *
     * @param vtlUnpivotClauseExpression
     * @param sqlResultFrom
     * @return
     */
    @Override
    public SqlResult applyUnpivot(VtlPivotOrUnpivotClauseExpression vtlUnpivotClauseExpression, SqlResult sqlResultFrom) {
        SqlResult sqlResult = new SqlResult();

        //Creo tabelle temporanee se servono
        SqlDataset sqlDatasetFrom = getTemporaryTable(sqlResult, vtlUnpivotClauseExpression.getVtlDataset(), sqlResultFrom);

        SqlDataset sqlDatasetUnpivot = sqlDatasetUtilityService.createSqlDataset(vtlUnpivotClauseExpression.getResultExpression().getResult());

        SqlObject selectQueryUnpivot = sqlObjectUtilityService.createSelectForUnpivot(vtlUnpivotClauseExpression.getIdentifier(), vtlUnpivotClauseExpression.getMeasure(), sqlDatasetFrom, sqlDatasetUnpivot);


        SqlResult sqlResultUnpivot = createTable(sqlDatasetUnpivot, vtlUnpivotClauseExpression.getResultExpression().getResult().getName(), selectQueryUnpivot, true);

        sqlResult.getResultList().addAll(sqlResultFrom.getResultList());
        sqlResult.getResultList().addAll(sqlResultUnpivot.getResultList());
        sqlResult.setSqlDataset(sqlDatasetUnpivot);

        return sqlResult;
    }

    /**
     * applica l'operazione di pivot e restituisce la traduzione sql
     *
     * @param vtlPivotClauseExpression
     * @return
     */
    @Override
    public SqlResult applyPivot(VtlPivotClauseExpression vtlPivotClauseExpression) {
        SqlResult sqlResult = new SqlResult();
        SqlDataset sqlDatasetResult = sqlDatasetUtilityService.createSqlDataset(vtlPivotClauseExpression.getResultExpression().getResult());
        SqlDataset datasetPrePivot = sqlDatasetUtilityService.buildPrePivot(vtlPivotClauseExpression);
        SqlObject selectPrePivot = sqlObjectUtilityService.createSelectFromSqlDataset(datasetPrePivot);
        SqlObject buildPivot = sqlObjectUtilityService.buildPivot(vtlPivotClauseExpression);
        String customFrom = "(" + selectPrePivot.toString() + ")" + buildPivot.toString();
        sqlDatasetResult.getSqlTables().get(0).setCustomFrom(new CustomSql(customFrom));
        sqlResult.setSqlDataset(sqlDatasetResult);
        return sqlResult;
    }

    /**
     * applica l'opearzione di check e restituisce la traduzione sql
     *
     * @param vtlCheck
     * @param sqlResultBoolean
     * @param sqlResultImbalance
     * @return
     */
    @Override
    public SqlResult applyCheck(VtlCheck vtlCheck, SqlResult sqlResultBoolean, SqlResult sqlResultImbalance) {
        SqlResult sqlResult = new SqlResult();

        //Creo tabelle temporanee se servono
        SqlDataset sqlDatasetBoolean = getTemporaryTable(sqlResult, vtlCheck.getBooleanDataset(), sqlResultBoolean);

        SqlDataset sqlDatasetImbalance = null;
        if (vtlCheck.getImbalanceExpression() != null) {
            sqlDatasetImbalance = getTemporaryTable(sqlResult, vtlCheck.getImbalanceExpression(), sqlResultImbalance);
        }

        SqlDataset sqlDatasetCheck = sqlDatasetUtilityService.createSqlDataset(vtlCheck.getResultExpression().getResult());

        sqlDatasetCheck = sqlDatasetUtilityService.applyCheckToSqlDataset(sqlDatasetCheck, sqlDatasetBoolean, sqlDatasetImbalance, vtlCheck.getErrorRuleset(), vtlCheck.getOutputMode());

        SqlResult sqlResultCheck = createTable(sqlDatasetCheck, vtlCheck.getResultExpression().getResult().getName(), true);

        sqlResult.getResultList().addAll(sqlResultBoolean.getResultList());
        if (vtlCheck.getImbalanceExpression() != null) {
            sqlResult.getResultList().addAll(sqlResultImbalance.getResultList());
        }
        sqlResult.getResultList().addAll(sqlResultCheck.getResultList());
        sqlResult.setSqlDataset(sqlDatasetUtilityService.createSqlDataset(vtlCheck.getResultExpression().getResult()));

        return sqlResult;
    }

    /**
     * Crea l'espressione sql per rappresentare una costante
     *
     * @param vtlConstantExpression
     * @return
     */
    @Override
    public SqlResult createConstantSql(VtlConstantExpression vtlConstantExpression) {
        SqlResult sqlResult = new SqlResult();
        SqlComponent sqlComponent = sqlComponentUtilityService.getScalarComponent(vtlConstantExpression);

        sqlResult.setSqlComponent(sqlComponent);
        SqlDataset sqlDataset = new SqlDataset();
        sqlDataset.getSqlTables().add(new SqlTable(vtlConstantExpression.getResultExpression().getResult()));
        sqlDataset.getComponentList().add(sqlComponent);
        sqlResult.setSqlDataset(
                sqlDataset
        );
        return sqlResult;
    }

    /**
     * Effettua l'operazione di checkDatapoint e restituisce la traduzione SQL
     *
     * @param vtlCheckDatapoint
     * @return
     */
    @Override
    public SqlResult checkDatapoint(VtlCheckDatapoint vtlCheckDatapoint) {
        SqlResult sqlResult = new SqlResult();
        List<SqlObject> queries = new ArrayList<>();
        for (VtlDPRule vtlDPRule : vtlCheckDatapoint.getVtlDatapointRuleset().getDpRules()) {
            SqlDataset sqlDataset = sqlDatasetUtilityService.checkDataPoint(vtlCheckDatapoint.getDatasetToCheck().getSqlResult().getSqlDataset(), vtlDPRule, vtlCheckDatapoint.getOutputMode());
            SelectQuery selectQuery = sqlObjectUtilityService.createSelectFromSqlDataset(sqlDataset);
            queries.add(selectQuery);
        }
        UnionQuery unionQuery = new UnionQuery(SetOperationQuery.Type.UNION_ALL, queries);
        SqlObject createQuery = sqlObjectUtilityService.createTableQuery(vtlCheckDatapoint.getResultExpression().getResult().getName(), unionQuery);
        sqlResult.setSqlDataset(sqlDatasetUtilityService.createSqlDataset(vtlCheckDatapoint.getResultExpression().getResult()));
        sqlResult.getResultList().add(createQuery);
        return sqlResult;
    }

    /**
     * effettua l'operazione di hierarchy e restituisce la traduzione sql
     *
     * @param vtlHierarchyExpression
     * @return
     */
    @Override
    public SqlResult hierarchy(VtlHierarchyExpression vtlHierarchyExpression) {
        SqlResult sqlResult = new SqlResult();
        SqlObject selectPivot = sqlObjectUtilityService.createSelectFromSqlDataset(vtlHierarchyExpression.getCustomPivot().getSqlResult().getSqlDataset());
        UnionQuery unionQuery = new UnionQuery(SetOperationQuery.Type.UNION_ALL);
        for (VtlHRRule vtlHRRule : vtlHierarchyExpression.getVtlHierarchicalRuleset().getVtlHRRules()) {
            SqlDataset sqlDatasetRule = sqlDatasetUtilityService.createSqlDataset(vtlHierarchyExpression.getVtlOperandExpression().getResultExpression().getResult());
            sqlDatasetRule.getSqlTables().get(0).setCustomFrom(selectPivot);
            SqlComponent sqlComponentRule = sqlComponentUtilityService.getSqlComponentByName(sqlDatasetRule, vtlHierarchyExpression.getRuleComponent().getComponentName());
            sqlComponentRule.setResult(new CustomSql("'" + vtlHRRule.getVtlCodeItemRelation().getLeftCodeItem() + "'"));
            SqlComponent sqlComponentMeasure = sqlComponentUtilityService.getSqlComponentByName(sqlDatasetRule, vtlHierarchyExpression.getVtlOperandExpression().getResultExpression().getResult().getMeasures().get(0).getName());
            sqlDatasetRule = sqlDatasetUtilityService.substituteSqlComponent(sqlDatasetRule, sqlComponentRule);
            SqlComponent sqlComponentFlag = sqlComponentUtilityService.createSqlComponent(VtlType.STRING, "FLAG", VtlComponentRole.MEASURE);
            if (vtlHRRule.getVtlCodeItemRelation().getLeftCondition() != null) {
                SqlObject caseCondition = sqlObjectUtilityService.createCaseStatement(
                        vtlHRRule.getVtlCodeItemRelation().getLeftCondition().getSqlResult().getSqlComponent().getResult(),
                        buildRuleNonModel(vtlHRRule, vtlHierarchyExpression.getValidationMode()),
                        buildElseCondition(vtlHierarchyExpression.getValidationMode())
                );
                sqlComponentMeasure.setResult(caseCondition);
                sqlDatasetRule = sqlDatasetUtilityService.substituteSqlComponent(sqlDatasetRule, sqlComponentMeasure);
                SqlObject condition = vtlHRRule.getVtlCodeItemRelation().getLeftCondition().getSqlResult().getSqlComponent().getResult();
                if (vtlHierarchyExpression.getValidationMode().equals(ValidationMode.NON_ZERO) || vtlHierarchyExpression.getValidationMode().equals(ValidationMode.NON_NULL)) {
                    condition = new CustomSql(condition.toString() + "AND ( " + buildRuleConstraint(vtlHRRule, vtlHierarchyExpression.getValidationMode()).toString() + " )");
                }
                SqlObject caseConditionFlag = sqlObjectUtilityService.createCaseStatement(
                        condition,
                        "Y",
                        "N"
                );
                sqlComponentFlag.setResult(caseConditionFlag);
            } else {
                SqlObject ruleModel = buildRuleNonModel(vtlHRRule, vtlHierarchyExpression.getValidationMode());
                sqlComponentMeasure.setResult(ruleModel);
                sqlDatasetRule = sqlDatasetUtilityService.substituteSqlComponent(sqlDatasetRule, sqlComponentMeasure);
                if (vtlHierarchyExpression.getValidationMode().equals(ValidationMode.NON_ZERO) || vtlHierarchyExpression.getValidationMode().equals(ValidationMode.NON_NULL)) {
                    SqlObject condition = buildRuleConstraint(vtlHRRule, vtlHierarchyExpression.getValidationMode());
                    SqlObject caseConditionFlag = sqlObjectUtilityService.createCaseStatement(
                            condition,
                            "Y",
                            "N"
                    );
                    sqlComponentFlag.setResult(caseConditionFlag);
                } else {
                    sqlComponentFlag.setResult(new CustomSql("'Y'"));
                }
            }
            sqlDatasetRule.getComponentList().add(sqlComponentFlag);
            unionQuery.addQueries(sqlObjectUtilityService.createSelectFromSqlDataset(sqlDatasetRule));
        }
        SqlDataset sqlDatasetCleaned = sqlDatasetUtilityService.cleanDatasetForModal(vtlHierarchyExpression.getResultExpression().getResult());
        sqlDatasetCleaned.getSqlTables().get(0).setCustomFrom(unionQuery);
        sqlDatasetCleaned.setWhereCondition(new CustomSql("tx.FLAG = 'Y'"));
        sqlResult.setSqlDataset(sqlDatasetCleaned);
        return sqlResult;
    }

    public SqlObject buildElseCondition(ValidationMode validationMode) {
        if (validationMode.equals(ValidationMode.ALWAYS_ZERO))
            return new CustomSql("0");
        else
            return new CustomSql("NULL");
    }

    private SqlObject buildRuleConstraint(VtlHRRule vtlHRRule, ValidationMode validationMode) {
        SqlObject sqlObjectResult = null;
        for (VtlCodeItem vtlCodeItem : vtlHRRule.getVtlCodeItemRelation().getVtlCodeItems()) {
            SqlObject vltCodeConstraint;
            if (validationMode.equals(ValidationMode.NON_ZERO)) {
                vltCodeConstraint = new CustomSql(vtlCodeItem.getCodeItem() + " <> 0");
            } else {
                vltCodeConstraint = new CustomSql(vtlCodeItem.getCodeItem() + " IS NOT NULL");
            }
            if (sqlObjectResult == null) {
                sqlObjectResult = vltCodeConstraint;
            } else {
                sqlObjectResult = new CustomSql(sqlObjectResult.toString() + " OR " + vltCodeConstraint.toString());
            }
        }
        return sqlObjectResult;
    }

    private SqlObject buildRuleNonModel(VtlHRRule vtlHRRule, ValidationMode validationMode) {
        SqlObject sqlObjectResult;
        List<SqlObject> sqlObjectsCondition = new ArrayList<>();
        SqlObject internalRule = buildInternalRule(vtlHRRule, sqlObjectsCondition);
        sqlObjectResult = internalRule;
        if (!sqlObjectsCondition.isEmpty()) {
            ComboCondition comboCondition = new ComboCondition(ComboCondition.Op.AND);
            comboCondition.setDisableParens(false);
            for (SqlObject sqlObjectCondition : sqlObjectsCondition) {
                comboCondition.addCustomCondition(sqlObjectCondition);
            }
            sqlObjectResult = sqlObjectUtilityService.createCaseStatement(comboCondition, internalRule, buildElseCondition(validationMode));
        }
        return sqlObjectResult;
    }

    private SqlObject buildCheckRule(VtlHRRule vtlHRRule, ValidationMode validationMode) {
        List<SqlObject> sqlObjectsCondition = new ArrayList<>();
        String checkRule = vtlHRRule.getVtlCodeItemRelation().getLeftCodeItem() + " "
                + vtlHRRule.getVtlCodeItemRelation().getComparator().getValue() + " "
                + buildInternalRule(vtlHRRule, sqlObjectsCondition).toString();
        checkRule = sqlObjectUtilityService.createCaseStatement(new CustomSql(checkRule), "TRUE", "FALSE").toString();
        if (!sqlObjectsCondition.isEmpty()) {
            ComboCondition comboCondition = new ComboCondition(ComboCondition.Op.AND);
            comboCondition.setDisableParens(false);
            for (SqlObject sqlObjectCondition : sqlObjectsCondition) {
                comboCondition.addCustomCondition(sqlObjectCondition);
            }
            checkRule = sqlObjectUtilityService.createCaseStatement(comboCondition, new CustomSql(checkRule), "TRUE").toString();
        }

        if (vtlHRRule.getVtlCodeItemRelation().getLeftCondition() != null) {
            checkRule = sqlObjectUtilityService.createCaseStatement(
                    buildCondition(
                            vtlHRRule.getVtlCodeItemRelation().getLeftCondition().getSqlResult().getSqlComponent().getResult(),
                            validationMode,
                            vtlHRRule
                    ),
                    new CustomSql(checkRule),
                    "TRUE"
            ).toString();
        }
        return new CustomSql(checkRule);
    }

    private SqlObject buildCondition(SqlObject condition, ValidationMode validationMode, VtlHRRule vtlHRRule) {
        String result = condition.toString();
        List<String> vtlItems = new ArrayList<>();
        vtlItems.add(vtlHRRule.getVtlCodeItemRelation().getLeftCodeItem());
        for (VtlCodeItem vtlCodeItem : vtlHRRule.getVtlCodeItemRelation().getVtlCodeItems()) {
            vtlItems.add(vtlCodeItem.getCodeItem());
        }
        if (validationMode != ValidationMode.ALWAYS_NULL && validationMode != ValidationMode.ALWAYS_ZERO) {
            String conditionNull = "( ";
            boolean first = true;
            for (String vtlItem : vtlItems) {
                if (first)
                    first = false;
                else if (validationMode == ValidationMode.NON_NULL)
                    conditionNull = conditionNull + " AND ";
                else
                    conditionNull = conditionNull + " OR ";
                if (validationMode == ValidationMode.NON_ZERO)
                    conditionNull = conditionNull + vtlItem + " <> 0 ";
                else
                    conditionNull = conditionNull + vtlItem + " is NOT NULL";
            }
            result = result + " AND " + conditionNull + " ) ";
        }
        return new CustomSql(result);
    }


    private SqlObject buildImbalance(VtlHRRule vtlHRRule) {
        List<SqlObject> sqlObjectsCondition = new ArrayList<>();
        String checkRule = vtlHRRule.getVtlCodeItemRelation().getLeftCodeItem() + " "
                + "-" + " ("
                + buildInternalRule(vtlHRRule, sqlObjectsCondition).toString() + ")";
        return new CustomSql(checkRule);
    }

    private SqlObject buildInternalRule(VtlHRRule vtlHRRule, List<SqlObject> sqlObjectsCondition) {
        SqlObject internalRule = new CustomSql("");
        for (VtlCodeItem vtlCodeItem : vtlHRRule.getVtlCodeItemRelation().getVtlCodeItems()) {
            if (vtlCodeItem.getCondition() != null) {
                sqlObjectsCondition.add(
                        new CustomSql(
                                vtlCodeItem.getCondition().getSqlResult().getSqlComponent().getResult().toString().replace("tx." + vtlCodeItem.getCondition().getSqlResult().getSqlComponent().getAliasName(), vtlCodeItem.getCodeItem()))
                );
            }
            SqlObject operator = new CustomSql("");
            if (vtlCodeItem.getOperator() != null) {
                if (vtlCodeItem.getOperator().equals(Operator.ADDITION)) {
                    operator = new CustomSql("+");
                } else {
                    operator = new CustomSql("-");
                }
            }
            internalRule = new CustomSql(internalRule.toString() + operator.toString() + new CustomSql(vtlCodeItem.getCodeItem()));

        }
        return internalRule;
    }

    /**
     * Effettua l'operazione di check hierarchy e restituisce la traduzione SQL
     *
     * @param vtlCheckHierarchy
     * @return
     */
    @Override
    public SqlResult checkHierarchy(VtlCheckHierarchy vtlCheckHierarchy) {
        SqlResult sqlResult = new SqlResult();
        SqlObject selectPivot = sqlObjectUtilityService.createSelectFromSqlDataset(vtlCheckHierarchy.getCustomPivot().getSqlResult().getSqlDataset());
        UnionQuery unionQuery = new UnionQuery(SetOperationQuery.Type.UNION_ALL);
        for (VtlHRRule vtlHRRule : vtlCheckHierarchy.getVtlHierarchicalRuleset().getVtlHRRules()) {
            SqlDataset sqlDatasetRule = sqlDatasetUtilityService.createSqlDataset(vtlCheckHierarchy.getVtlVarId().getResultExpression().getResult());
            List<SqlComponent> identifiers = sqlDatasetUtilityService.getSqlComponentsByRole(sqlDatasetRule, VtlComponentRole.IDENTIFIER);
            sqlDatasetRule = sqlDatasetUtilityService.copySqlDatasetWithoutSqlComponent(sqlDatasetRule);
            sqlDatasetRule.getComponentList().addAll(identifiers);
            sqlDatasetRule.getSqlTables().get(0).setCustomFrom(selectPivot);
            SqlComponent sqlComponentRule = sqlComponentUtilityService.getSqlComponentByName(sqlDatasetRule, vtlCheckHierarchy.getRuleComponent().getComponentName());
            sqlComponentRule.setResult(new CustomSql("'" + vtlHRRule.getVtlCodeItemRelation().getLeftCodeItem() + "'"));
            sqlDatasetRule = sqlDatasetUtilityService.substituteSqlComponent(sqlDatasetRule, sqlComponentRule);
            SqlComponent ruleid = sqlComponentUtilityService.createSqlComponent(VtlType.STRING, "RULEID", VtlComponentRole.MEASURE);
            ruleid.setResult(new CustomSql("'" + vtlHRRule.getRuleName() + "'"));
            sqlDatasetRule.getComponentList().add(ruleid);
            SqlComponent bool_var = sqlComponentUtilityService.createSqlComponent(VtlType.BOOLEAN, "BOOL_VAR", VtlComponentRole.MEASURE);
            bool_var.setResult(buildCheckRule(vtlHRRule, vtlCheckHierarchy.getValidationMode()));
            sqlDatasetRule.getComponentList().add(bool_var);

            SqlComponent errorCode = sqlComponentUtilityService.createSqlComponent(VtlType.STRING, "ERRORCODE", VtlComponentRole.MEASURE);
            String value = "NULL";
            if (vtlHRRule.getErrorRuleset() != null && vtlHRRule.getErrorRuleset().getErrorCode() != null) {
                value = "'" + vtlHRRule.getErrorRuleset().getErrorCode().getValue().toString() + "'";
            }
            errorCode.setResult(new CustomSql(value));
            sqlDatasetRule.getComponentList().add(errorCode);

            value = "NULL";
            SqlComponent errorLevel = sqlComponentUtilityService.createSqlComponent(VtlType.STRING, "ERRORLEVEL", VtlComponentRole.MEASURE);
            if (vtlHRRule.getErrorRuleset() != null && vtlHRRule.getErrorRuleset().getErrorLevel() != null) {
                value = "'" + vtlHRRule.getErrorRuleset().getErrorLevel().getValue().toString() + "'";
            }
            errorLevel.setResult(new CustomSql(value));
            sqlDatasetRule.getComponentList().add(errorLevel);

            SqlComponent imbalance = sqlComponentUtilityService.createSqlComponent(VtlType.NUMBER, "IMBALANCE", VtlComponentRole.MEASURE);
            imbalance.setResult(buildImbalance(vtlHRRule));
            sqlDatasetRule.getComponentList().add(imbalance);
            unionQuery.addQueries(sqlObjectUtilityService.createSelectFromSqlDataset(sqlDatasetRule));

        }
        SqlDataset sqlDataset = sqlDatasetUtilityService.cleanDatasetForModal(vtlCheckHierarchy.getResultExpression().getResult());
        SqlComponent sqlComponent = sqlComponentUtilityService.getSqlComponentByName(sqlDataset, "ERRORCODE");
        sqlComponent.setResult(
                sqlObjectUtilityService.createCaseStatement(
                        new CustomSql(TX_BOOL_VAR_FALSE),
                        new CustomSql("tx.ERRORCODE"),
                        new CustomSql("NULL")
                )
        );
        sqlDataset = sqlDatasetUtilityService.substituteSqlComponent(sqlDataset, sqlComponent);

        sqlComponent = sqlComponentUtilityService.getSqlComponentByName(sqlDataset, "ERRORLEVEL");
        sqlComponent.setResult(
                sqlObjectUtilityService.createCaseStatement(
                        new CustomSql(TX_BOOL_VAR_FALSE),
                        new CustomSql("tx.ERRORLEVEL"),
                        new CustomSql("NULL")
                )
        );
        sqlDataset = sqlDatasetUtilityService.substituteSqlComponent(sqlDataset, sqlComponent);

        sqlComponent = sqlComponentUtilityService.getSqlComponentByName(sqlDataset, "IMBALANCE");
        sqlComponent.setResult(
                sqlObjectUtilityService.createCaseStatement(
                        new CustomSql(TX_BOOL_VAR_FALSE),
                        new CustomSql("tx.IMBALANCE"),
                        new CustomSql("NULL")
                )
        );
        sqlDataset = sqlDatasetUtilityService.substituteSqlComponent(sqlDataset, sqlComponent);
        sqlDataset.getSqlTables().get(0).setCustomFrom(unionQuery);
        sqlResult.setSqlDataset(sqlDataset);

        return sqlResult;
    }

    @Override
    public SqlResult castTranslation(VtlCastExpression vtlCastExpression) {
        SqlResult sqlResult = new SqlResult();
        sqlResult.getResultList().addAll(vtlCastExpression.getVtlExpression().getSqlResult().getResultList());
        if (vtlCastExpression.getVtlExpression().getResultExpression().isAComponent() || vtlCastExpression.getVtlExpression().getResultExpression().isAScalar()) {
            sqlResult.setSqlComponent(
                    sqlComponentUtilityService.applyCastOperator(
                            vtlCastExpression.getVtlExpression().getSqlResult().getSqlComponent(),
                            vtlCastExpression.getMask(),
                            vtlCastExpression.getOperator(),
                            vtlCastExpression.getVtlType()
                    )
            );
        } else {
            sqlResult.setSqlDataset(
                    sqlDatasetUtilityService.applyCastOperator(
                            vtlCastExpression.getVtlExpression().getSqlResult().getSqlDataset(),
                            vtlCastExpression.getMask(),
                            vtlCastExpression.getOperator(),
                            vtlCastExpression.getVtlType()
                    )
            );
        }
        return sqlResult;
    }

}
