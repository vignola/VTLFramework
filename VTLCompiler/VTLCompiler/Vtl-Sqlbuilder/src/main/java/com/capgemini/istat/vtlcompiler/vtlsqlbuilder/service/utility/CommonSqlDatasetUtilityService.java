package com.capgemini.istat.vtlcompiler.vtlsqlbuilder.service.utility;

import com.capgemini.istat.vtlcompiler.vtlcommon.model.dataset.VtlComponent;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.dataset.VtlComponentRole;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.dataset.VtlDataset;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.dataset.VtlType;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.operator.Operator;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.translation.SqlComponent;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.translation.SqlDataset;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.translation.SqlResult;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.translation.SqlTable;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.dataset.VtlComponentId;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.define.ruleset.VtlErrorRuleset;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.define.ruleset.datapoint.VtlDPRule;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.VtlExpression;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.VtlMembership;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.clause.analytic.VtlAnalyticFunctionExpression;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.clause.drop.VtlKeepOrDropClauseExpression;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.clause.pivot.VtlPivotClauseExpression;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.clause.rename.VtlRenameClauseExpression;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.clause.rename.VtlRenameClauseItemExpression;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.clause.sub.VtlSubSpaceExpression;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.clause.sub.VtlSubSpaceItemExpression;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.comparison.VtlBetweenExpression;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.comparison.VtlInExpression;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.hierarchy.VtlHierarchyExpression;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.join.VtlJoinExpression;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.join.VtlJoinSelectClause;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.join.VtlJoinSelectClauseItem;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.join.VtlJoinUsing;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.validation.OutputMode;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.validation.ValidationMode;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.validation.VtlCheckHierarchy;
import com.capgemini.istat.vtlcompiler.vtlcommon.service.ComponentUtilityService;
import com.capgemini.istat.vtlcompiler.vtlcommon.service.DatasetUtilityService;
import com.capgemini.istat.vtlcompiler.vtlcommon.service.VtlTypeUtilityService;
import com.google.common.base.Strings;
import com.healthmarketscience.sqlbuilder.*;
import com.healthmarketscience.sqlbuilder.SelectQuery.JoinType;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * la classe offre tutte le funzionalità per manipolare gli SQLDataset. Un SqlDataset è una
 * classe che rappresenta la struttura della query da tradurre.
 * I metodi offerti da questa classe svolgono un ruolo importante nella generazione della traduzione SQL sopratutto
 * per quello che riguarda le trasformazioni sul dataset
 *
 * @see ISqlComponentUtilityService
 * @see VtlTypeUtilityService
 * @see ISqlObjectUtilityService
 * @see DatasetUtilityService
 * @see ComponentUtilityService
 * @see DbTableUtilityService
 */
@Service
public class CommonSqlDatasetUtilityService implements ISqlDatasetUtilityService {
    protected ISqlComponentUtilityService sqlComponentUtilityService;
    protected VtlTypeUtilityService vtlTypeUtilityService;
    protected ISqlObjectUtilityService sqlObjectUtilityService;
    protected DatasetUtilityService datasetUtilityService;
    protected ComponentUtilityService componentUtilityService;
    protected DbTableUtilityService dbTableUtilityService;

    @Autowired
    public void setVtlTypeUtilityService(VtlTypeUtilityService vtlTypeUtilityService) {
        this.vtlTypeUtilityService = vtlTypeUtilityService;
    }


    public void setSqlObjectUtilityService(ISqlObjectUtilityService sqlObjectUtilityService) {
        this.sqlObjectUtilityService = sqlObjectUtilityService;
    }


    public void setSqlComponentUtilityService(ISqlComponentUtilityService sqlComponentUtilityService) {
        this.sqlComponentUtilityService = sqlComponentUtilityService;
    }

    @Autowired
    public void setDatasetUtilityService(DatasetUtilityService datasetUtilityService) {
        this.datasetUtilityService = datasetUtilityService;
    }

    @Autowired
    public void setComponentUtilityService(ComponentUtilityService componentUtilityService) {
        this.componentUtilityService = componentUtilityService;
    }

    @Autowired
    public void setDbTableUtilityService(DbTableUtilityService dbTableUtilityService) {
        this.dbTableUtilityService = dbTableUtilityService;
    }

    /**
     * Dato un dataset genera un SqlDataset
     *
     * @param vtlDataset
     * @return
     */
    @Override
    public SqlDataset createSqlDataset(VtlDataset vtlDataset) {
        SqlDataset result = new SqlDataset();
        result.getSqlTables().add(createSqlTable(vtlDataset, null));
        for (VtlComponent vtlComponent : vtlDataset.getVtlComponents()) {
            result.getComponentList().add(sqlComponentUtilityService.createSqlComponent(vtlComponent));
        }
        return result;
    }

    /**
     * Apllica una funzione a un SqlDataset
     *
     * @param sqlDataset
     * @param parameter
     * @param optionalParameter
     * @param operator
     * @return
     */
    @Override
    public SqlDataset applyFunctionToSqlDataset(SqlDataset sqlDataset, VtlExpression parameter, List<VtlExpression> optionalParameter, Operator operator) {
        SqlDataset sqlDatasetResult = copySqlDatasetWithoutSqlComponent(sqlDataset);
        for (SqlComponent sqlComponent : sqlDataset.getComponentList()) {
            if (sqlComponent.getVtlComponent().getVtlComponentRole() == VtlComponentRole.IDENTIFIER) {
                sqlDatasetResult.getComponentList().add(sqlComponent);
            }
            if (sqlComponent.getVtlComponent().getVtlComponentRole() == VtlComponentRole.MEASURE) {
                sqlDatasetResult.getComponentList().add(
                        sqlComponentUtilityService.applyUnaryFunctionToSqlComponent(
                                sqlComponent,
                                parameter,
                                optionalParameter,
                                operator
                        )
                );
            }
            if (sqlComponent.getVtlComponent().getVtlComponentRole() == VtlComponentRole.VIRAL) {
                sqlDatasetResult.getComponentList().add(sqlComponent);
            }
        }
        return sqlDatasetResult;
    }

    /**
     * Applica una funzione di aggregazione a un SqlDataset
     *
     * @param sqlDataset
     * @param parameter
     * @param optionalParameter
     * @param operator
     * @return
     */
    @Override
    public SqlDataset applyAggregateFunctionToSqlDataset(SqlDataset sqlDataset, VtlExpression parameter, List<VtlExpression> optionalParameter, Operator operator) {
        SqlDataset sqlDatasetResult = copySqlDatasetWithoutSqlComponent(sqlDataset);
        //Nelle aggregazioni i componenti Virali non sono MAI riportati (concordato con ISTAT)
        for (SqlComponent sqlComponent : sqlDataset.getComponentList()) {
            if (sqlComponent.getVtlComponent().getVtlComponentRole() == VtlComponentRole.IDENTIFIER) {
                sqlDatasetResult.getComponentList().add(sqlComponent);
            }
            if (sqlComponent.getVtlComponent().getVtlComponentRole() == VtlComponentRole.MEASURE) {
                sqlDatasetResult.getComponentList().add(
                        sqlComponentUtilityService.applyUnaryFunctionToSqlComponent(
                                sqlComponent,
                                parameter,
                                optionalParameter,
                                operator
                        )
                );
            }
        }
        return sqlDatasetResult;
    }

    /**
     * Applica una funzione analitica a un sqlDataset
     *
     * @param sqlDataset
     * @param vtlAnalyticFunctionExpression
     * @param operator
     * @return
     */
    @Override
    public SqlDataset applyAnaliticFunctionToSqlDataset(SqlDataset sqlDataset, VtlAnalyticFunctionExpression vtlAnalyticFunctionExpression, Operator operator) {
        SqlDataset sqlDatasetResult = copySqlDatasetWithoutSqlComponent(sqlDataset);
        //Nelle analitic i componenti Virali non sono MAI riportati (concordato con ISTAT)
        for (SqlComponent sqlComponent : sqlDataset.getComponentList()) {
            if (sqlComponent.getVtlComponent().getVtlComponentRole() == VtlComponentRole.IDENTIFIER) {
                sqlDatasetResult.getComponentList().add(sqlComponent);
            }
            if (sqlComponent.getVtlComponent().getVtlComponentRole() == VtlComponentRole.MEASURE) {
                sqlDatasetResult.getComponentList().add(
                        sqlComponentUtilityService.applyAnalyticFunctionToSqlComponent(
                                sqlComponent,
                                sqlDataset,
                                vtlAnalyticFunctionExpression,
                                operator
                        )
                );
            }
        }
        return sqlDatasetResult;
    }

    /**
     * Applica la funzione In Not In a una dataset.
     *
     * @param sqlDataset
     * @param operator
     * @param vtlInExpression
     * @return
     */
    @Override
    public SqlDataset applyInNotInFunctionToSqlDataset(SqlDataset sqlDataset, Operator operator, VtlInExpression vtlInExpression) {
        SqlDataset sqlDatasetResult = copySqlDatasetWithoutSqlComponent(sqlDataset);
        for (SqlComponent sqlComponent : sqlDataset.getComponentList()) {
            if (sqlComponent.getVtlComponent().getVtlComponentRole() == VtlComponentRole.IDENTIFIER) {
                sqlDatasetResult.getComponentList().add(sqlComponent);
            }
            if (sqlComponent.getVtlComponent().getVtlComponentRole() == VtlComponentRole.MEASURE) {
                sqlDatasetResult.getComponentList().add(
                        sqlComponentUtilityService.applyInNotInFunctionToSqlComponent(
                                sqlComponent,
                                operator,
                                vtlInExpression
                        )
                );
            }
            if (sqlComponent.getVtlComponent().getVtlComponentRole() == VtlComponentRole.VIRAL) {
                sqlDatasetResult.getComponentList().add(sqlComponent);
            }
        }
        return sqlDatasetResult;
    }

    /**
     * Applica la funzione di between a un SqlDataset. l'operatore viene applicato alle sole misure.
     *
     * @param sqlDataset
     * @param operator
     * @param vtlBetweenExpression
     * @return
     */
    @Override
    public SqlDataset applyBetweenFunctionToSqlDataset(SqlDataset sqlDataset, Operator operator, VtlBetweenExpression vtlBetweenExpression) {
        SqlDataset sqlDatasetResult = copySqlDatasetWithoutSqlComponent(sqlDataset);
        for (SqlComponent sqlComponent : sqlDataset.getComponentList()) {
            if (sqlComponent.getVtlComponent().getVtlComponentRole() == VtlComponentRole.IDENTIFIER) {
                sqlDatasetResult.getComponentList().add(sqlComponent);
            }
            if (sqlComponent.getVtlComponent().getVtlComponentRole() == VtlComponentRole.MEASURE) {
                sqlDatasetResult.getComponentList().add(
                        sqlComponentUtilityService.applyBetweenFunctionToSqlComponent(
                                sqlComponent,
                                operator,
                                vtlBetweenExpression
                        )
                );
            }
            if (sqlComponent.getVtlComponent().getVtlComponentRole() == VtlComponentRole.VIRAL) {
                sqlDatasetResult.getComponentList().add(sqlComponent);
            }
        }
        return sqlDatasetResult;
    }

    /**
     * Applica una funzione Time a un SqlDataset. Per gli operatori flow to stock/stock to flow e per la period indicator
     * il metodo si avvale della logica offerta da due metodi specifici. per tuttli gli altri operatori applica l'operazione
     * sull'identificativo del tipo time
     *
     * @param sqlDataset
     * @param parameter
     * @param optionalParameter
     * @param operator
     * @return
     */
    @Override
    public SqlDataset applyTimeFunctionToSqlDataset(SqlDataset sqlDataset, VtlExpression parameter, List<VtlExpression> optionalParameter, Operator operator) {
        if (operator.equals(Operator.PERIOD_INDICATOR)) {
            return applyPeriodIndicatorFunctionToSqlDataset(sqlDataset, operator);
        }

        if (operator.equals(Operator.FLOW_TO_STOCK) || operator.equals(Operator.STOCK_TO_FLOW)) {
            return applyFlowToStockStockToFlowFunctionToSqlDataset(sqlDataset, operator);
        }

        SqlDataset sqlDatasetResult = copySqlDatasetWithoutSqlComponent(sqlDataset);
        for (SqlComponent sqlComponent : sqlDataset.getComponentList()) {
            if (sqlComponent.getVtlComponent().getVtlComponentRole() == VtlComponentRole.IDENTIFIER) {
                if (vtlTypeUtilityService.isDateTimeComponent(sqlComponent.getVtlComponent().getType())) {
                    //applica la funzione sull'identificativo Time
                    sqlDatasetResult.getComponentList().add(
                            sqlComponentUtilityService.applyUnaryFunctionToSqlComponent(
                                    sqlComponent,
                                    parameter,
                                    optionalParameter,
                                    operator
                            )
                    );
                } else {
                    sqlDatasetResult.getComponentList().add(sqlComponent);
                }
            }
            if (sqlComponent.getVtlComponent().getVtlComponentRole() == VtlComponentRole.MEASURE) {
                sqlDatasetResult.getComponentList().add(sqlComponent);
            }
            if (sqlComponent.getVtlComponent().getVtlComponentRole() == VtlComponentRole.VIRAL) {
                sqlDatasetResult.getComponentList().add(sqlComponent);
            }
        }
        return sqlDatasetResult;
    }

    /**
     * Applica l'operatore period a un sqlDataset. Il metodo lascia i componenti identificativi invariati ed effettua
     * la trasformazione sulla misura di tipo time
     *
     * @param sqlDataset
     * @param operator
     * @return
     */
    @Override
    public SqlDataset applyPeriodIndicatorFunctionToSqlDataset(SqlDataset sqlDataset, Operator operator) {
        SqlDataset sqlDatasetResult = copySqlDatasetWithoutSqlComponent(sqlDataset);
        for (SqlComponent sqlComponent : sqlDataset.getComponentList()) {
            if (sqlComponent.getVtlComponent().getVtlComponentRole() == VtlComponentRole.IDENTIFIER) {
                sqlDatasetResult.getComponentList().add(sqlComponent);

                if (vtlTypeUtilityService.isDateTimeComponent(sqlComponent.getVtlComponent().getType())) {
                    SqlComponent timePeriodSqlComponent = sqlComponentUtilityService.createSqlComponent(
                            componentUtilityService.generateComponentWithRoleAndName(
                                    sqlComponent.getVtlComponent(),
                                    VtlComponentRole.MEASURE,
                                    sqlComponent.getVtlComponent().getName()
                            )
                    );

                    sqlDatasetResult.getComponentList().add(
                            sqlComponentUtilityService.applyUnaryFunctionToSqlComponent(
                                    timePeriodSqlComponent,
                                    null,
                                    null,
                                    operator
                            )
                    );
                }
            }
            if (sqlComponent.getVtlComponent().getVtlComponentRole() == VtlComponentRole.VIRAL) {
                sqlDatasetResult.getComponentList().add(sqlComponent);
            }
        }
        return sqlDatasetResult;
    }

    /**
     * Applica la funzione di flow to stock o stock to flow a un Sqldataset. Il metodo elimina l'identificativo
     * di tipo time e applica la funzione sulle misure del dataset
     *
     * @param sqlDataset
     * @param operator
     * @return
     */
    @Override
    public SqlDataset applyFlowToStockStockToFlowFunctionToSqlDataset(SqlDataset sqlDataset, Operator operator) {
        SqlDataset sqlDatasetResult = copySqlDatasetWithoutSqlComponent(sqlDataset);
        SqlComponent timeIdentifier = null;

        List<SqlComponent> identifierList = sqlComponentUtilityService.getSqlComponentsByRole(sqlDataset.getComponentList(), VtlComponentRole.IDENTIFIER);

        for (SqlComponent sqlComponent : identifierList) {
            if (vtlTypeUtilityService.isDateTimeComponent(sqlComponent.getVtlComponent().getType())) {
                timeIdentifier = sqlComponent;
                break;
            }
        }
        identifierList.remove(timeIdentifier);

        for (SqlComponent sqlComponent : sqlDataset.getComponentList()) {
            if (sqlComponent.getVtlComponent().getVtlComponentRole() == VtlComponentRole.IDENTIFIER) {
                sqlDatasetResult.getComponentList().add(sqlComponent);
            }
            if (sqlComponent.getVtlComponent().getVtlComponentRole() == VtlComponentRole.MEASURE) {
                sqlDatasetResult.getComponentList().add(
                        sqlComponentUtilityService.applyFlowToStockStockToFlowFunctionToSqlComponent(sqlComponent, identifierList, timeIdentifier, operator));
            }
            if (sqlComponent.getVtlComponent().getVtlComponentRole() == VtlComponentRole.VIRAL) {
                sqlDatasetResult.getComponentList().add(sqlComponent);
            }
        }
        return sqlDatasetResult;
    }

    /**
     * Applica una fill time series a un dataset. il metodo restituisce un nuovo sqlDataset che punta alla tabella di appoggio
     * che viene creata per calcolare la fill time
     *
     * @param vtlDataset
     * @return
     */
    @Override
    public SqlDataset applyFillTimeSeriesFunctionToSqlDataset(VtlDataset vtlDataset) {
        VtlDataset fillTimeSeriesVtlDataset = datasetUtilityService.cloneDatasetWithNewName(vtlDataset, "TEMPORARY_FTS", true, false, false);
        SqlDataset sqlDataset = createSqlDataset(fillTimeSeriesVtlDataset);

        sqlDataset.setUpAlias(dbTableUtilityService.getDbSpec().getNextAlias(), sqlDataset.getSqlTables().get(0).getVtlDataset());

        return sqlDataset;
    }

    /**
     * Applica una funzione binaria a due sqlDataset. Ripropone la logica di interazione fra due dataset. Viene
     * generato un nuovo sqlDataset che ha come identificativi il superset e applica le funzioni a tutte le misure.
     * Gli attributi virali vengono propagati tramite concatenazione. L'operazione risultante è una join con la oncondition
     * su tutti gli identificativi in comune fra i due dataset
     *
     * @param sqlDatasetLeft
     * @param sqlDatasetRight
     * @param operator
     * @return
     */
    @Override
    public SqlDataset applyBinaryFunction(SqlDataset sqlDatasetLeft, SqlDataset sqlDatasetRight, Operator operator) {
        SqlDataset sqlDatasetResult = new SqlDataset();

        sqlDatasetResult.getSqlTables().addAll(sqlDatasetLeft.getSqlTables());
        sqlDatasetResult.getSqlTables().addAll(sqlDatasetRight.getSqlTables());
        sqlDatasetResult.getComponentList().addAll(sqlComponentUtilityService.getSupersetIdentifier(sqlDatasetLeft, sqlDatasetRight));
        sqlDatasetResult.getComponentList().addAll(
                sqlComponentUtilityService.applyFunctionToCommonComponent(sqlDatasetLeft, sqlDatasetRight, VtlComponentRole.MEASURE, operator)
        );
        sqlDatasetResult.getComponentList().addAll(
                sqlComponentUtilityService.applyFunctionToCommonViralComponent(sqlDatasetLeft, sqlDatasetRight)
        );

        sqlDatasetResult = buildOnConditionStandard(sqlDatasetLeft, sqlDatasetRight, sqlDatasetResult);
        return sqlDatasetResult;
    }

    /**
     * Applica una funzione binaria a un sql e un sqlComponent. Il metodo gestisce la dinamica di interazione fra un
     * dataset e uno scalare. Il metodo applica alle sole misure del dataset la funzione senza cmaiare la propria struttura
     *
     * @param sqlDataset
     * @param scalarComponent
     * @param isScalarLeft
     * @param operator
     * @return
     */
    @Override
    public SqlDataset applyBinaryFunctionScalar(SqlDataset sqlDataset, SqlComponent scalarComponent, boolean isScalarLeft, Operator operator) {
        SqlDataset sqlDatasetResult = copySqlDatasetWithoutSqlComponent(sqlDataset);
        for (SqlComponent sqlComponent : sqlDataset.getComponentList()) {
            if (sqlComponent.getVtlComponent().getVtlComponentRole() == VtlComponentRole.IDENTIFIER ||
                    sqlComponent.getVtlComponent().getVtlComponentRole() == VtlComponentRole.ATTRIBUTE) {
                sqlDatasetResult.getComponentList().add(sqlComponent);
            }
            if (sqlComponent.getVtlComponent().getVtlComponentRole() == VtlComponentRole.MEASURE) {
                if (isScalarLeft)
                    sqlDatasetResult.getComponentList().add(sqlComponentUtilityService.applyBinaryFunctionToSqlComponent(scalarComponent, sqlComponent, operator));
                else
                    sqlDatasetResult.getComponentList().add(sqlComponentUtilityService.applyBinaryFunctionToSqlComponent(sqlComponent, scalarComponent, operator));
            }
        }
        return sqlDatasetResult;
    }

    /**
     * Applica la funzione di keep a un SqlDataset. Il metododo cicla tutti i componenti del dataset in ingresso e mantiene
     * gli identificativi e i soli componenti indicati dall'espressione
     *
     * @param sqlDataset
     * @param vtlKeepClauseExpression
     * @return
     */
    @Override
    public SqlDataset applyKeepClauseToSqlDataset(SqlDataset sqlDataset, VtlKeepOrDropClauseExpression vtlKeepClauseExpression) {
        SqlDataset sqlDatasetResult = copySqlDatasetWithoutSqlComponent(sqlDataset);
        for (SqlComponent sqlComponent : sqlDataset.getComponentList()) {
            if (sqlComponent.getVtlComponent().getVtlComponentRole() == VtlComponentRole.IDENTIFIER) {
                sqlDatasetResult.getComponentList().add(sqlComponent);
            } else {
                for (VtlExpression vtlExpression : vtlKeepClauseExpression.getVtlComponentIds()) {
                    if (vtlExpression.getResultExpression().getResultComponent().getName().equalsIgnoreCase(sqlComponent.getVtlComponent().getName())) {
                        sqlDatasetResult.getComponentList().add(sqlComponent);
                        break;
                    }
                }
            }
        }
        return sqlDatasetResult;
    }

    /**
     * Applica una funzione di drop a un SqlDataset. Il metodo scansiona tutti i componenti del dataset, e mantiene solo
     * gli identificativi e quelli non indicati nell'espressione
     *
     * @param sqlDataset
     * @param vtlKeepOrDropClauseExpression
     * @return
     */
    @Override
    public SqlDataset applyDropClauseToSqlDataset(SqlDataset sqlDataset, VtlKeepOrDropClauseExpression vtlKeepOrDropClauseExpression) {
        SqlDataset sqlDatasetResult = copySqlDatasetWithoutSqlComponent(sqlDataset);
        for (SqlComponent sqlComponent : sqlDataset.getComponentList()) {
            if (sqlComponent.getVtlComponent().getVtlComponentRole() == VtlComponentRole.IDENTIFIER) {
                sqlDatasetResult.getComponentList().add(sqlComponent);
            } else {
                boolean addComponent = true;
                for (VtlComponentId vtlComponentId : vtlKeepOrDropClauseExpression.getVtlComponentIds()) {
                    if (vtlComponentId.getComponentName().equalsIgnoreCase(sqlComponent.getVtlComponent().getName())) {
                        addComponent = false;
                        break;
                    }
                }
                if (addComponent) {
                    sqlDatasetResult.getComponentList().add(sqlComponent);
                }
            }
        }
        return sqlDatasetResult;
    }

    /**
     * Applica la funzionalità di rename a un dataset.il metoodo cicla tutti i componenti del dataset e applica la rinominazione
     * a tutti quell iindicati. la rinominazione viene fatta tramite l'alias offerta dal SQL
     *
     * @param sqlDataset
     * @param vtlRenameClauseExpression
     * @return
     */
    @Override
    public SqlDataset applyRenameClauseToSqlDataset(SqlDataset sqlDataset, VtlRenameClauseExpression vtlRenameClauseExpression) {
        SqlDataset sqlDatasetResult = copySqlDatasetWithoutSqlComponent(sqlDataset);
        for (SqlComponent sqlComponent : sqlDataset.getComponentList()) {
            String rename = "";
            for (VtlRenameClauseItemExpression vtlRenameClauseItemExpression : vtlRenameClauseExpression.getVtlRenameClauseItemExpressionList()) {
                if (vtlRenameClauseItemExpression.getVtlComponentNameFrom().getComponentName().equalsIgnoreCase(sqlComponent.getVtlComponent().getName())) {
                    rename = vtlRenameClauseItemExpression.getVtlComponentNameTo().getComponentName();
                    break;
                }
            }
            if (!Strings.isNullOrEmpty(rename)) {
                sqlComponent.setAliasName(rename);
            }
            sqlDatasetResult.getComponentList().add(sqlComponent);
        }
        return sqlDatasetResult;
    }

    /**
     * applica la funzione di sub a un SqlDataset. Il metodop cicla tutti i componenti, ed elimina tutti gli identificativi
     * indicati nella sub. Gli altri componenti vengono mantenuti
     *
     * @param sqlDataset
     * @param vtlSubSpaceExpression
     * @return
     */
    @Override
    public SqlDataset applySubspaceClauseToSqlDataset(SqlDataset sqlDataset, VtlSubSpaceExpression vtlSubSpaceExpression) {
        SqlDataset sqlDatasetResult = copySqlDatasetWithoutSqlComponent(sqlDataset);
        for (SqlComponent sqlComponent : sqlDataset.getComponentList()) {
            if (sqlComponent.getVtlComponent().getVtlComponentRole() == VtlComponentRole.IDENTIFIER) {
                boolean addComponent = true;
                for (VtlSubSpaceItemExpression vtlSubSpaceItemExpression : vtlSubSpaceExpression.getVtlSubSpaceItemExpressionList()) {
                    if (vtlSubSpaceItemExpression.getVtlComponentId().getComponentName().equalsIgnoreCase(sqlComponent.getVtlComponent().getName())) {
                        addComponent = false;
                        break;
                    }
                }
                if (addComponent) {
                    sqlDatasetResult.getComponentList().add(sqlComponent);
                }
            } else {
                sqlDatasetResult.getComponentList().add(sqlComponent);
            }
        }

        ComboCondition comboCondition = ComboCondition.and();
        for (VtlSubSpaceItemExpression vtlSubSpaceItemExpression : vtlSubSpaceExpression.getVtlSubSpaceItemExpressionList()) {
            SqlComponent sqlComponent = sqlComponentUtilityService.getSqlComponentByName(sqlDataset, vtlSubSpaceItemExpression.getVtlComponentId().getComponentName());
            comboCondition.addCondition(new BinaryCondition(BinaryCondition.Op.EQUAL_TO, sqlComponent.getResult(), vtlSubSpaceItemExpression.getVtlConstantExpression().getVtlConstant().getValue()));
        }
        sqlDatasetResult.setWhereCondition(comboCondition);

        return sqlDatasetResult;
    }

    /**
     * Applica una group by a un SqlDataset. Il metodo prende in ingresso un dataset che rappresenta un risultato
     * un sqlDataset che rappresenta il dataset di partenza e un componente che rappresenta il componente coinvolto nella group
     * by e costruisce un nuovo dataset che restituisce tutti gli identificativi e applica la funzione di raggruppamento
     * componente.
     *
     * @param vtlDataset
     * @param sqlDataset
     * @param sqlComponentGroupAll
     * @return
     */
    @Override
    public SqlDataset applyGroupClauseToSqlDataset(VtlDataset vtlDataset, SqlDataset sqlDataset, SqlComponent sqlComponentGroupAll) {
        SqlDataset sqlDatasetResult = copySqlDatasetWithoutSqlComponent(sqlDataset);

        for (VtlComponent vtlComponent : vtlDataset.getVtlComponents()) {
            if (vtlComponent.getVtlComponentRole() == VtlComponentRole.IDENTIFIER) {
                SqlComponent sqlComponent = sqlComponentUtilityService.getSqlComponentByName(sqlDataset, vtlComponent.getName());
                if (sqlComponent != null) {
                    if (sqlComponentGroupAll != null && sqlComponent.getVtlComponent().getName().equalsIgnoreCase(sqlComponentGroupAll.getVtlComponent().getName())) {
                        sqlDatasetResult.getComponentList().add(sqlComponentGroupAll);
                        sqlDatasetResult.getGroupByClauseColumns().add(sqlComponentGroupAll.getResult());
                    } else {
                        sqlDatasetResult.getComponentList().add(sqlComponent);
                        sqlDatasetResult.getGroupByClauseColumns().add(sqlComponent.getResult());
                    }
                }
            }
        }
        for (SqlComponent sqlComponent : sqlDataset.getComponentList()) {
            if (!sqlComponent.getVtlComponent().getVtlComponentRole().equals(VtlComponentRole.IDENTIFIER)) {
                sqlDatasetResult.getComponentList().add(sqlComponent);
            }

        }

        return sqlDatasetResult;
    }

    /**
     * Applica una funzione di aggregazione a un sqlDataset. Il metodo prende in ingresso un dataset che raffigura già
     * la soluzione finale e applica la funzione di aggregazione al risultato
     *
     * @param vtlDataset    il dataset con le misure aggregate
     * @param sqlComponents la lista degli sqlComponent del dataset
     * @param sqlDataset    l'sqlDataset di partenza
     * @return un nuovo dataset sul quale è stata applicata la funzione di aggregazione
     */
    @Override
    public SqlDataset aggregateSqlComponent(VtlDataset vtlDataset, List<SqlComponent> sqlComponents, SqlDataset sqlDataset) {
        SqlDataset sqlDatasetResult = copySqlDatasetWithoutSqlComponent(sqlDataset);
        for (VtlComponent vtlComponent : vtlDataset.getVtlComponents()) {
            String componentName = vtlComponent.getName();
            SqlComponent sqlComponent = sqlComponentUtilityService.getSqlComponentByName(sqlComponents, componentName);
            if (sqlComponent == null) {
                sqlComponent = sqlComponentUtilityService.getSqlComponentByName(sqlDataset, componentName);
            }
            sqlDatasetResult.getComponentList().add(sqlComponent);
        }
        return sqlDatasetResult;

    }

    /**
     * Copia l'SqlDataset in ingresso e ne genera uno nuovo da poter utilizzare senza impattare il precendente
     *
     * @param sqlDataset
     * @return
     */
    @Override
    public SqlDataset copySqlDatasetWithoutSqlComponent(SqlDataset sqlDataset) {
        SqlDataset sqlDatasetResult = new SqlDataset();
        sqlDatasetResult.setSqlTables(copySqlTables(sqlDataset.getSqlTables()));
        sqlDatasetResult.setWhereCondition(sqlDataset.getWhereCondition());
        sqlDatasetResult.setHavingCondition(sqlDataset.getHavingCondition());
        sqlDatasetResult.setGroupByClauseColumns(sqlDataset.getGroupByClauseColumns());
        sqlDatasetResult.setOrderByClauseColumns(sqlDataset.getOrderByClauseColumns());
        return sqlDatasetResult;
    }

    /**
     * Genera una nuova lista di sqlTable identica a quella di partenza
     *
     * @param sqlTables
     * @return
     */
    public List<SqlTable> copySqlTables(List<SqlTable> sqlTables) {
        List<SqlTable> result = new ArrayList<>();
        for (SqlTable sqlTable : sqlTables) {
            SqlTable sqlTableResult = new SqlTable();
            BeanUtils.copyProperties(sqlTable, sqlTableResult);
            result.add(sqlTableResult);
        }
        return result;
    }

    /**
     * Il metodo prende in ingresso un sqlDataset e una lista di sqlComponent e genera un nuovo SqlDataset copiando le impotsazioni
     * del SQlDataset di partenza e aggiunge la lista di componenti
     *
     * @param sqlDataset
     * @param sqlComponents
     * @return
     */
    @Override
    public SqlDataset copySqlDatasetWithSqlComponent(SqlDataset sqlDataset, List<SqlComponent> sqlComponents) {
        SqlDataset sqlDatasetResult = copySqlDatasetWithoutSqlComponent(sqlDataset);
        sqlDatasetResult.getComponentList().addAll(sqlComponentUtilityService.copySqlComponents(sqlComponents));
        return sqlDatasetResult;
    }

    /**
     * Applica la funzione di membership a un dataset. Il metood uitilizza il risultato già ottenuto dal motore semantico
     * per costruire la soluzione
     *
     * @param vtlMembership
     * @param sqlDataset
     * @return
     */
    @Override
    public SqlDataset applyMembership(VtlMembership vtlMembership, SqlDataset sqlDataset) {
        SqlDataset sqlDatasetResult = copySqlDatasetWithoutSqlComponent(sqlDataset);
        sqlDatasetResult.getComponentList().addAll(getSqlComponentsByRole(sqlDataset, VtlComponentRole.IDENTIFIER));
        SqlComponent sqlComponent = sqlComponentUtilityService.getSqlComponentByName(sqlDataset, vtlMembership.getVtlComponentId().getComponentName());
        sqlComponent.setAliasName(vtlMembership.getResultExpression().getResult().getMeasures().get(0).getName());
        sqlComponent.setVtlComponent(vtlMembership.getResultExpression().getResult().getMeasures().get(0));
        sqlDatasetResult.getComponentList().add(sqlComponent);
        sqlDatasetResult.getComponentList().addAll(getSqlComponentsByRole(sqlDataset, VtlComponentRole.VIRAL));
        return sqlDatasetResult;
    }

    /**
     * Dato in ingresso un SqlDataset e un ruolo  restituisce tutti gli sqlcomponent che appartengono al dataset
     * che ricoprono quel ruolo
     *
     * @param sqlDataset
     * @param vtlComponentRole
     * @return
     */
    @Override
    public List<SqlComponent> getSqlComponentsByRole(SqlDataset sqlDataset, VtlComponentRole vtlComponentRole) {
        List<SqlComponent> sqlComponents = new ArrayList<>();
        for (SqlComponent sqlComponent : sqlDataset.getComponentList()) {
            if (sqlComponent.getVtlComponent().getVtlComponentRole().equals(vtlComponentRole)) {
                sqlComponents.add(sqlComponent);
            }
        }
        return sqlComponents;
    }

    /**
     * Costruisce un SqlDataset per calocare la tabella di prejoin necessaria ad alcune modalità di join.
     * L'sqlDataset di prejoin rappresenta un join puro di tutte le tabelle coinvolte contenente il superset degli
     * identificativi e tutte le misure dei dataset. Se i componenti dei dataset hanno lo stesse misure, queste vengono
     * rinominate in nomeTabella#nomeComponente
     *
     * @param vtlJoinSelectClause
     * @param operator
     * @return
     */
    @Override
    public SqlDataset buildPreJoinTable(VtlJoinSelectClause vtlJoinSelectClause, Operator operator) {
        VtlDataset vtlDatasetPreJoined = vtlJoinSelectClause.getResultExpression().getResult();
        SqlDataset sqlDatasetResult = new SqlDataset();
        List<SqlComponent> sqlIdentifiers = new ArrayList<>();
        List<List<SqlComponent>> allComponents = new ArrayList<>();
        for (VtlJoinSelectClauseItem vtlJoinSelectClauseItem : vtlJoinSelectClause.getVtlJoinSelectClauseItems()) {
            String alias = vtlJoinSelectClauseItem.getAsName();
            if (alias == null)
                alias = vtlJoinSelectClauseItem.getResultExpression().getResult().getName();
            allComponents.add(vtlJoinSelectClauseItem.getVtlExpression().getSqlResult().getSqlDataset().getComponentList());
            SqlDataset sqlDataset = copySqlDatasetWithSqlComponent(vtlJoinSelectClauseItem.getVtlExpression().getSqlResult().getSqlDataset(), vtlJoinSelectClauseItem.getVtlExpression().getSqlResult().getSqlDataset().getComponentList());
            for (SqlComponent sqlComponent : sqlDataset.getComponentList()) {

                VtlComponent vtlComponentFound = componentUtilityService.getComponentFromName(
                        vtlDatasetPreJoined.getVtlComponents(),
                        sqlComponent.getVtlComponent().getName(),
                        true
                );

                if (vtlComponentFound == null) {
                    if (sqlComponent.getVtlComponent().getVtlComponentRole() != VtlComponentRole.IDENTIFIER) {
                        //Non l'ho trovato quindi è rinominato in alias#nomeCampo
                        sqlComponent.setAliasName(alias + "#" + sqlComponent.getAliasName());
                        sqlDatasetResult.getComponentList().add(sqlComponent);
                    }

                } else {
                    if (sqlComponent.getVtlComponent().getVtlComponentRole() != VtlComponentRole.IDENTIFIER) {
                        sqlDatasetResult.getComponentList().add(sqlComponent);
                    }
                }
                SqlComponent supersetIdentifier = getSuperSetIdentifier(alias, sqlComponent, vtlJoinSelectClause.getSupersetIdentifiers());

                if (supersetIdentifier != null)
                    sqlIdentifiers.add(sqlComponent);
                if (operator == Operator.CROSS_JOIN && sqlComponent.getVtlComponent().getVtlComponentRole() == VtlComponentRole.IDENTIFIER) {
                    sqlComponent.setAliasName(alias + "#" + sqlComponent.getAliasName());
                    sqlIdentifiers.add(sqlComponent);
                }
            }
            sqlDatasetResult.getSqlTables().addAll(vtlJoinSelectClauseItem.getVtlExpression().getSqlResult().getSqlDataset().getSqlTables());
        }
        sqlDatasetResult.getComponentList().addAll(0, sqlIdentifiers);
        if (operator != Operator.CROSS_JOIN) {
            LinkedList<LinkedList<SqlComponent>> onIdentifiers = getUsingComponent(sqlComponentUtilityService.getCommonComponent(allComponents), vtlJoinSelectClause.getVtlJoinUsing());
            List<BinaryCondition> binaryConditions = sqlObjectUtilityService.applyCommonComponentsCondition(onIdentifiers);
            sqlDatasetResult.setupConditions(binaryConditions);
        }
        sqlDatasetResult.setUpAliasJoinType(operator);
        return sqlDatasetResult;
    }

    /**
     * Non implementata. Non necessaria
     *
     * @param vtlJoinSelectClause
     * @param operator
     * @return
     */
    @Override
    public SqlDataset buildPreFullJoinTable(VtlJoinSelectClause vtlJoinSelectClause, Operator operator) {
        return null;
    }

    /**
     * Fornisce un SqlDataset ripulito dai componenti duplicati. Il metodo cicla i componenti ed elimina lo sharp
     *
     * @param vtlJoinExpression
     * @return
     */
    @Override
    public SqlDataset cleanJoinTable(VtlJoinExpression vtlJoinExpression) {
        SqlDataset sqlDataset = vtlJoinExpression.getVtlJoinBody().getSqlResult().getSqlDataset();
        for (SqlComponent sqlComponent : sqlDataset.getComponentList()) {
            if (sqlComponent.getAliasName().contains("#")) {
                String[] names = sqlComponent.getAliasName().split("#");
                sqlComponent.setAliasName(names[1]);
            }
        }
        return sqlDataset;
    }

    private LinkedList<LinkedList<SqlComponent>> getUsingComponent(LinkedList<LinkedList<SqlComponent>> commonComponents, VtlJoinUsing vtlJoinUsing) {
        LinkedList<LinkedList<SqlComponent>> result = new LinkedList<>();
        if (vtlJoinUsing == null) {
            for (LinkedList<SqlComponent> sqlComponents : commonComponents) {
                SqlComponent firstComponent = sqlComponents.getFirst();
                if (firstComponent.getVtlComponent().getVtlComponentRole() == VtlComponentRole.IDENTIFIER) {
                    result.addLast(sqlComponents);
                }
            }
        } else {
            for (VtlComponentId vtlComponentId : vtlJoinUsing.getComponentIds()) {
                for (LinkedList<SqlComponent> sqlComponents : commonComponents) {
                    SqlComponent firstComponent = sqlComponents.getFirst();
                    if (firstComponent.getVtlComponent().getName().equalsIgnoreCase(vtlComponentId.getComponentName()))
                        result.addLast(sqlComponents);
                }
            }
        }
        return result;
    }

    private SqlComponent getSuperSetIdentifier(String alias, SqlComponent sqlComponent, List<VtlComponent> supersetIdentifiers) {
        for (VtlComponent vtlComponent : supersetIdentifiers) {
            if (vtlComponent.getVtlDataset() != null && vtlComponent.getVtlDataset().getName().equalsIgnoreCase(alias)) {
                if (sqlComponent.getAliasName().equalsIgnoreCase(vtlComponent.getName())) {
                    return sqlComponent;
                }
            } else {
                return null;
            }
        }
        return null;
    }

    /**
     * Applica la funzione di exist in a due dataset.
     * Viene costruito un oggetto SQlDataset che rappresenta due tabelle in left outer join dove viene
     * verificata l'esistenza del componente tramite l'ausilio di una case when
     *
     * @param sqlResultLeft
     * @param sqlResultRight
     * @param vtlDatasetResult
     * @param retain
     * @return
     */
    @Override
    public SqlDataset applyExistIn(SqlResult sqlResultLeft, SqlResult sqlResultRight, VtlDataset vtlDatasetResult, String retain) {
        SqlDataset sqlDatasetResult;

        List<SqlComponent> identifiersLeft = sqlComponentUtilityService.getSqlComponentsByRole(
                sqlResultLeft.getSqlDataset().getComponentList(),
                VtlComponentRole.IDENTIFIER
        );
        List<SqlComponent> identifiersRight = sqlComponentUtilityService.getSqlComponentsByRole(
                sqlResultRight.getSqlDataset().getComponentList(),
                VtlComponentRole.IDENTIFIER
        );


        SqlDataset dsRight = copySqlDatasetWithoutSqlComponent(sqlResultRight.getSqlDataset());
        dsRight.getComponentList().addAll(sqlComponentUtilityService.getSqlComponentsByRole(identifiersRight, VtlComponentRole.IDENTIFIER));

        SqlDataset dsLeft = copySqlDatasetWithoutSqlComponent(sqlResultLeft.getSqlDataset());
        dsLeft.getComponentList().addAll(sqlComponentUtilityService.getSqlComponentsByRole(identifiersLeft, VtlComponentRole.IDENTIFIER));


        SqlDataset resultS = copySqlDatasetWithSqlComponent(dsLeft, identifiersLeft);
        SqlDataset resultR = copySqlDatasetWithSqlComponent(dsRight, identifiersRight);

        LinkedList<List<SqlComponent>> components = new LinkedList<>();
        components.addLast(identifiersLeft);
        components.addLast(identifiersRight);

        List<BinaryCondition> onConditions = sqlObjectUtilityService.applyCommonComponentsCondition(
                sqlComponentUtilityService.getCommonComponent(components)
        );

        resultR.getSqlTables().get(0).setJoinType(JoinType.LEFT_OUTER);
        resultS.getSqlTables().addAll(resultR.getSqlTables());
        resultS.setupConditions(onConditions);

        UnaryCondition ucNull = new UnaryCondition(UnaryCondition.Op.IS_NULL, resultR.getComponentList().get(0).getResult());
        ucNull.setDisableParens(true);
        SqlObject caseS = sqlObjectUtilityService.createCaseStatement(ucNull, sqlObjectUtilityService.getApplicationDefaultValueFalse(), sqlObjectUtilityService.getApplicationDefaultValueTrue());

        for (VtlComponent vtlComponent : vtlDatasetResult.getVtlComponents()) {
            if (vtlComponent.getVtlComponentRole() == VtlComponentRole.MEASURE) {
                SqlComponent sqlComponent = sqlComponentUtilityService.createSqlComponent(vtlComponent);

                sqlComponent.setResult(caseS);
                resultS.getComponentList().add(sqlComponent);

                break;
            }
        }
        UnaryCondition ucNotNull = new UnaryCondition(UnaryCondition.Op.IS_NOT_NULL, resultR.getComponentList().get(0).getResult());
        ucNotNull.setDisableParens(true);
        if (retain == null || retain.isEmpty() || retain.equalsIgnoreCase("all")) {
            sqlDatasetResult = resultS;
        } else if (retain.equalsIgnoreCase("true")) {
            resultS.setWhereCondition(ucNotNull);
            sqlDatasetResult = resultS;
        } else {
            resultS.setWhereCondition(ucNull);
            sqlDatasetResult = resultS;
        }

        return sqlDatasetResult;
    }

    /**
     * Applica la funzione di cast a un SqlDataset.
     * Il metodo cicla tutti i compondenti del dataset e applica la funzione di cast a tutte le misure
     *
     * @param sqlDataset
     * @param mask
     * @param operator
     * @param vtlType
     * @return
     */
    @Override
    public SqlDataset applyCastOperator(SqlDataset sqlDataset, String mask, Operator operator, VtlType vtlType) {
        SqlDataset sqlDatasetResult = copySqlDatasetWithoutSqlComponent(sqlDataset);
        for (SqlComponent sqlComponent : sqlDataset.getComponentList()) {
            if (sqlComponent.getVtlComponent().getVtlComponentRole() == VtlComponentRole.IDENTIFIER) {
                sqlDatasetResult.getComponentList().add(sqlComponent);
            }
            if (sqlComponent.getVtlComponent().getVtlComponentRole() == VtlComponentRole.MEASURE) {
                sqlDatasetResult.getComponentList().add(
                        sqlComponentUtilityService.applyCastOperator(
                                sqlComponent,
                                mask,
                                operator,
                                vtlType
                        )
                );
            }
            if (sqlComponent.getVtlComponent().getVtlComponentRole() == VtlComponentRole.VIRAL) {
                sqlDatasetResult.getComponentList().add(sqlComponent);
            }
        }
        return sqlDatasetResult;
    }

    /**
     * Applica  la funzione di if then else su un SqlDataset. Il metodo gestisce sia la casisistica in cui l'opreatore if
     * è applicato a due dataset sia il caso in cui si utilizzi a livello di una where.
     *
     * @param sqlDatasetLeft
     * @param sqlDatasetRight
     * @param whereCondition
     * @return
     */
    @Override
    public SqlDataset applyIfToSqlDataset(SqlDataset sqlDatasetLeft, SqlDataset sqlDatasetRight, Boolean whereCondition) {
        SqlDataset sqlDatasetResult = copySqlDatasetWithoutSqlComponent(sqlDatasetLeft);
        sqlDatasetResult.getSqlTables().addAll(sqlDatasetRight.getSqlTables());
        sqlDatasetResult.getComponentList().addAll(sqlComponentUtilityService.getSqlComponentsByRole(sqlDatasetLeft.getComponentList(), VtlComponentRole.IDENTIFIER));
        sqlDatasetResult.getComponentList().addAll(sqlComponentUtilityService.getSqlComponentsByRole(sqlDatasetRight.getComponentList(), VtlComponentRole.MEASURE));
        sqlDatasetResult.getComponentList().addAll(sqlComponentUtilityService.getSqlComponentsByRole(sqlDatasetRight.getComponentList(), VtlComponentRole.VIRAL));

        sqlDatasetResult = buildOnConditionStandard(sqlDatasetResult, sqlDatasetRight, sqlDatasetResult);

        List<SqlComponent> measureList = sqlComponentUtilityService.getSqlComponentsByRole(sqlDatasetLeft.getComponentList(), VtlComponentRole.MEASURE);

        if (whereCondition) {
            sqlDatasetResult.setWhereCondition(new BinaryCondition(BinaryCondition.Op.EQUAL_TO, measureList.get(0).getResult(), sqlObjectUtilityService.getApplicationDefaultValueTrue()));
        } else {
            sqlDatasetResult.setWhereCondition(new BinaryCondition(BinaryCondition.Op.EQUAL_TO, measureList.get(0).getResult(), sqlObjectUtilityService.getApplicationDefaultValueFalse()));
        }

        return sqlDatasetResult;
    }

    /**
     * Applica la funzione di Check a un SqlDataset
     * il metodo costruisce una tabella che verifica l'esistenza della condizione, uno che effettua l'operazione di imbalance
     * e infine mette i risultati in join per costruire la struttura risultante attesa
     *
     * @param sqlDatasetCheck
     * @param sqlDatasetBoolean
     * @param sqlDatasetImbalance
     * @param errorRuleset
     * @param outputMode
     * @return
     */
    @Override
    public SqlDataset applyCheckToSqlDataset(SqlDataset sqlDatasetCheck, SqlDataset sqlDatasetBoolean, SqlDataset sqlDatasetImbalance, VtlErrorRuleset errorRuleset, OutputMode outputMode) {
        SqlDataset sqlDatasetResult = copySqlDatasetWithoutSqlComponent(sqlDatasetBoolean);

        sqlDatasetResult.getComponentList().addAll(sqlComponentUtilityService.getSqlComponentsByRole(sqlDatasetBoolean.getComponentList(), VtlComponentRole.IDENTIFIER));
        sqlDatasetResult.getComponentList().addAll(sqlComponentUtilityService.getSqlComponentsByRole(sqlDatasetBoolean.getComponentList(), VtlComponentRole.MEASURE));

        if (sqlDatasetImbalance != null) {
            sqlDatasetResult.getSqlTables().addAll(sqlDatasetImbalance.getSqlTables());

            SqlComponent imbalance = sqlComponentUtilityService.getSqlComponentsByRole(sqlDatasetImbalance.getComponentList(), VtlComponentRole.MEASURE).get(0);
            imbalance.setAliasName("imbalance");
            sqlDatasetResult.getComponentList().add(imbalance);
            sqlDatasetResult = buildOnConditionStandard(sqlDatasetBoolean, sqlDatasetImbalance, sqlDatasetResult);

        } else {
            SqlComponent imbalance = sqlComponentUtilityService.getSqlComponentByName(sqlDatasetCheck, "imbalance");
            imbalance.setResult(sqlObjectUtilityService.createCastNull(imbalance.getVtlComponent().getDomainValue()));
            sqlDatasetResult.getComponentList().add(imbalance);
        }

        SqlComponent boolvar = sqlComponentUtilityService.getSqlComponentsByRole(sqlDatasetBoolean.getComponentList(), VtlComponentRole.MEASURE).get(0);
        BinaryCondition boolvarCondition = new BinaryCondition(BinaryCondition.Op.EQUAL_TO, boolvar.getResult(), sqlObjectUtilityService.getApplicationDefaultValueFalse());
        boolvarCondition.setDisableParens(true);

        if (errorRuleset != null && errorRuleset.getErrorCode() != null) {
            SqlComponent errorcode = sqlComponentUtilityService.getSqlComponentByName(sqlDatasetCheck, "errorcode");

            if (outputMode.equals(OutputMode.INVALID)) {
                errorcode.setResult(new CustomSql(errorRuleset.getErrorCode().getFormattedValue()));
            } else {
                SqlObject caseStatement = sqlObjectUtilityService.createCaseStatement(
                        boolvarCondition,
                        new CustomSql(errorRuleset.getErrorCode().getFormattedValue()),
                        sqlObjectUtilityService.createCastNull(errorcode.getVtlComponent().getDomainValue())
                );
                errorcode.setResult(caseStatement);
            }

            sqlDatasetResult.getComponentList().add(errorcode);
        } else {
            SqlComponent errorcode = sqlComponentUtilityService.getSqlComponentByName(sqlDatasetCheck, "errorcode");
            errorcode.setResult(sqlObjectUtilityService.createCastNull(errorcode.getVtlComponent().getDomainValue()));
            sqlDatasetResult.getComponentList().add(errorcode);
        }

        if (errorRuleset != null && errorRuleset.getErrorLevel() != null) {
            SqlComponent errorlevel = sqlComponentUtilityService.getSqlComponentByName(sqlDatasetCheck, "errorlevel");

            if (outputMode.equals(OutputMode.INVALID)) {
                errorlevel.setResult(new CustomSql(errorRuleset.getErrorLevel().getFormattedValue()));
            } else {
                SqlObject caseStatement = sqlObjectUtilityService.createCaseStatement(
                        boolvarCondition,
                        new CustomSql(errorRuleset.getErrorLevel().getFormattedValue()),
                        sqlObjectUtilityService.createCastNull(errorlevel.getVtlComponent().getDomainValue())
                );
                errorlevel.setResult(caseStatement);
            }

            sqlDatasetResult.getComponentList().add(errorlevel);
        } else {
            SqlComponent errorlevel = sqlComponentUtilityService.getSqlComponentByName(sqlDatasetCheck, "errorlevel");
            errorlevel.setResult(sqlObjectUtilityService.createCastNull(errorlevel.getVtlComponent().getDomainValue()));
            sqlDatasetResult.getComponentList().add(errorlevel);
        }

        if (outputMode.equals(OutputMode.INVALID)) {
            sqlDatasetResult.setWhereCondition(boolvarCondition);
        }

        return sqlDatasetResult;
    }

    /**
     * il metodo prende in ingresso due sqlDataset, raccoglie tutti gli identificativi in comune e restituisce nell'oggetto
     * sqlDatasetResult la onCondition risultante.
     *
     * @param sqlDatasetLeft
     * @param sqlDatasetRight
     * @param sqlDatasetResult
     * @return
     */
    public SqlDataset buildOnConditionStandard(SqlDataset sqlDatasetLeft, SqlDataset sqlDatasetRight, SqlDataset sqlDatasetResult) {
        LinkedList<List<SqlComponent>> components = new LinkedList<>();
        components.addLast(sqlComponentUtilityService.getSqlComponentsByRole(sqlDatasetLeft.getComponentList(), VtlComponentRole.IDENTIFIER));
        components.addLast(sqlComponentUtilityService.getSqlComponentsByRole(sqlDatasetRight.getComponentList(), VtlComponentRole.IDENTIFIER));

        List<BinaryCondition> onConditions = sqlObjectUtilityService.applyCommonComponentsCondition(
                sqlComponentUtilityService.getCommonComponent(components)
        );
        sqlDatasetResult.setupConditions(onConditions);
        return sqlDatasetResult;
    }

    /**
     * Il metodo prende in ingresso un dataset e una rule e genera una select che attraverso i case when verifica
     * la sussistenza della rules
     *
     * @param sqlDatasetToCheck
     * @param vtlDPRule
     * @param outputMode
     * @return
     */
    @Override
    public SqlDataset checkDataPoint(SqlDataset sqlDatasetToCheck, VtlDPRule vtlDPRule, OutputMode outputMode) {
        SqlDataset sqlDatasetResult = copySqlDatasetWithoutSqlComponent(sqlDatasetToCheck);
        sqlDatasetResult.getComponentList().addAll(
                sqlComponentUtilityService.getSqlComponentsByRole(
                        sqlDatasetToCheck.getComponentList(),
                        VtlComponentRole.IDENTIFIER
                )
        );
        SqlComponent sqlComponentRuleId = sqlComponentUtilityService.getScalarComponent(
                VtlType.STRING, vtlDPRule.getRuleName(),
                VtlComponentRole.IDENTIFIER
        );
        sqlComponentRuleId.setAliasName("ruleid");
        sqlDatasetResult.getComponentList().add(
                sqlComponentRuleId
        );
        if (outputMode != OutputMode.ALL) {
            sqlDatasetResult.getComponentList().addAll(
                    sqlComponentUtilityService.getSqlComponentsByRole(
                            sqlDatasetToCheck.getComponentList(),
                            VtlComponentRole.MEASURE
                    )
            );
        }
        if (outputMode != OutputMode.INVALID) {
            SqlObject caseStatement =
                    sqlObjectUtilityService.createCaseStatement(
                            vtlDPRule.getConsequentCondition().getSqlResult().getSqlComponent().getResult(),
                            sqlObjectUtilityService.getApplicationDefaultValueTrue(),
                            sqlObjectUtilityService.getApplicationDefaultValueFalse()
                    );
            if (vtlDPRule.getAntecedentCondition() != null) {
                caseStatement =
                        sqlObjectUtilityService.createCaseStatement(
                                vtlDPRule.getAntecedentCondition().getSqlResult().getSqlComponent().getResult(),
                                caseStatement,
                                sqlObjectUtilityService.getApplicationDefaultValueFalse()
                        );
            }
            SqlComponent sqlBooleanComponent = sqlComponentUtilityService.getScalarComponent(VtlType.BOOLEAN, "", null);
            sqlBooleanComponent.setAliasTable(vtlDPRule.getConsequentCondition().getSqlResult().getSqlComponent().getAliasTable());
            sqlBooleanComponent.setResult(caseStatement);
            sqlDatasetResult.getComponentList().add(sqlBooleanComponent);
        }

        sqlDatasetResult.getComponentList().addAll(
                sqlComponentUtilityService.getSqlComponentsByRole(
                        sqlDatasetToCheck.getComponentList(),
                        VtlComponentRole.VIRAL
                )
        );
        //ERROR CODE

        String errorCode = vtlDPRule.getVtlErrorRuleset().getErrorCode() != null && vtlDPRule.getVtlErrorRuleset().getErrorCode().getValue() != null ?
                vtlDPRule.getVtlErrorRuleset().getErrorCode().getValue().toString() : "";

        SqlComponent errorComponent = buildErrorMessageComponent(
                vtlDPRule, errorCode,
                "ERROR_CODE",
                vtlDPRule.getConsequentCondition().getSqlResult().getSqlComponent().getAliasTable()
        );
        sqlDatasetResult.getComponentList().add(errorComponent);

        //ERROR LEVEL
        String errorLevel = vtlDPRule.getVtlErrorRuleset().getErrorLevel() != null && vtlDPRule.getVtlErrorRuleset().getErrorLevel().getValue() != null ?
                vtlDPRule.getVtlErrorRuleset().getErrorLevel().getValue().toString() : "";
        SqlComponent errorComponentLevel = buildErrorMessageComponent(
                vtlDPRule, errorLevel,
                "ERROR_LEVEL",
                vtlDPRule.getConsequentCondition().getSqlResult().getSqlComponent().getAliasTable()
        );
        sqlDatasetResult.getComponentList().add(errorComponentLevel);
        if (outputMode == OutputMode.INVALID) {
            SqlComponent whereComponent = sqlComponentUtilityService.copySqlComponent(errorComponent);
            sqlDatasetResult.setWhereCondition(new CustomSql(whereComponent.getResult() + " IS NOT NULL"));
        }
        return sqlDatasetResult;
    }

    private SqlComponent buildErrorMessageComponent(VtlDPRule vtlDPRule, String error, String aliasName, String aliasTable) {
        SqlObject caseStatement = buildDpRulesCase(vtlDPRule, error);

        SqlComponent errorComponent = sqlComponentUtilityService.getScalarComponent(VtlType.STRING, "", null);
        errorComponent.setAliasTable(aliasTable);
        errorComponent.setResult(caseStatement);
        errorComponent.setAliasName(aliasName);
        return errorComponent;
    }

    private SqlObject buildDpRulesCase(VtlDPRule vtlDPRule, String value) {
        SqlObject caseStatement =
                sqlObjectUtilityService.createCaseStatement(
                        vtlDPRule.getConsequentCondition().getSqlResult().getSqlComponent().getResult(),
                        new CustomSql("NULL"),
                        value
                );

        if (vtlDPRule.getAntecedentCondition() != null) {
            caseStatement =
                    sqlObjectUtilityService.createCaseStatement(
                            vtlDPRule.getAntecedentCondition().getSqlResult().getSqlComponent().getResult(),
                            caseStatement,
                            new CustomSql("NULL")
                    );
        }
        return caseStatement;
    }

    private SqlTable createSqlTable(VtlDataset vtlDataset, String aliasName) {
        SqlTable sqlTable = new SqlTable();
        sqlTable.setVtlDataset(vtlDataset);
        sqlTable.setAliasName(aliasName);
        return sqlTable;
    }

    /**
     * Dati in ingresso due SqlDataset genera un nuovo SqlDataset contenente la query necessaria a effettuare l'operazione
     * di SetDiff
     * Dati due dataset viene generata una leftouter join propedeudica per la costruzione della setDiff
     *
     * @param sqlDatasetLeft
     * @param sqlDatasetRight
     * @return
     */
    @Override
    public SqlDataset createQueryForSetBinaryJoin(SqlDataset sqlDatasetLeft, SqlDataset sqlDatasetRight) {
        SqlDataset sqlDatasetResult;

        List<SqlComponent> identifiersLeft = sqlComponentUtilityService.getSqlComponentsByRole(
                sqlDatasetLeft.getComponentList(),
                VtlComponentRole.IDENTIFIER
        );
        List<SqlComponent> identifiersRight = sqlComponentUtilityService.getSqlComponentsByRole(
                sqlDatasetRight.getComponentList(),
                VtlComponentRole.IDENTIFIER
        );

        SqlDataset dsRight = copySqlDatasetWithoutSqlComponent(sqlDatasetRight);
        dsRight.getComponentList().addAll(sqlComponentUtilityService.getSqlComponentsByRole(identifiersRight, VtlComponentRole.IDENTIFIER));

        SqlDataset dsLeft = copySqlDatasetWithoutSqlComponent(sqlDatasetLeft);
        dsLeft.getComponentList().addAll(sqlComponentUtilityService.getSqlComponentsByRole(identifiersLeft, VtlComponentRole.IDENTIFIER));

        SqlDataset resultS = copySqlDatasetWithSqlComponent(dsLeft, identifiersLeft);
        SqlDataset resultR = copySqlDatasetWithSqlComponent(dsRight, identifiersRight);

        //aggiungo misure e virali
        List<SqlComponent> measuresLeft = sqlComponentUtilityService.getSqlComponentsByRole(
                sqlDatasetLeft.getComponentList(),
                VtlComponentRole.MEASURE
        );
        resultS.getComponentList().addAll(sqlComponentUtilityService.getSqlComponentsByRole(measuresLeft, VtlComponentRole.MEASURE));

        List<SqlComponent> attributesLeft = sqlComponentUtilityService.getSqlComponentsByRole(
                sqlDatasetLeft.getComponentList(),
                VtlComponentRole.VIRAL
        );
        resultS.getComponentList().addAll(sqlComponentUtilityService.getSqlComponentsByRole(attributesLeft, VtlComponentRole.MEASURE));


        LinkedList<List<SqlComponent>> components = new LinkedList<>();
        components.addLast(identifiersLeft);
        components.addLast(identifiersRight);

        List<BinaryCondition> onConditions = sqlObjectUtilityService.applyCommonComponentsCondition(
                sqlComponentUtilityService.getCommonComponent(components)
        );

        resultR.getSqlTables().get(0).setJoinType(JoinType.LEFT_OUTER);
        resultS.getSqlTables().addAll(resultR.getSqlTables());
        resultS.setupConditions(onConditions);

        UnaryCondition ucNull = new UnaryCondition(UnaryCondition.Op.IS_NULL, resultR.getComponentList().get(0).getResult());
        ucNull.setDisableParens(true);

        resultS.setWhereCondition(ucNull);
        sqlDatasetResult = resultS;

        return sqlDatasetResult;
    }

    /**
     * Data una vtlHierarchical expression e una from specifica, genera un nuovo dataset che rappresenta la query contenenti
     * le rule della model
     *
     * @param vtlHierarchyExpression
     * @param buildFrom
     * @return
     */
    @Override
    public SqlDataset buildHierarchyComputedDataset(VtlHierarchyExpression vtlHierarchyExpression, SqlObject buildFrom) {
        VtlDataset vtlDatasetComputed = datasetUtilityService.createTemporaryDataset(vtlHierarchyExpression.getResultExpression().getResult().getVtlComponents(), false);
        vtlDatasetComputed.setName(vtlHierarchyExpression.getResultExpression().getResult().getName());
        SqlDataset sqlDatasetViral = copySqlDatasetWithSqlComponent(vtlHierarchyExpression.getVtlOperandExpression().getSqlResult().getSqlDataset(),
                vtlHierarchyExpression.getVtlOperandExpression().getSqlResult().getSqlDataset().getComponentList());
        sqlDatasetViral.setUpAlias(dbTableUtilityService.getDbSpec().getNextAlias(), sqlDatasetViral.getSqlTables().get(0).getVtlDataset());
        String oldName = vtlDatasetComputed.getMeasures().get(0).getName();
        vtlDatasetComputed.getMeasures().get(0).setName("ME_RULE");
        SqlDataset sqlDatasetPreHierarchy = createSqlDataset(vtlDatasetComputed);
        //aggiungi from personalizzata
        sqlDatasetPreHierarchy.getSqlTables().get(0).setCustomFrom(buildFrom);
        sqlDatasetPreHierarchy.setUpAlias(dbTableUtilityService.getDbSpec().getNextAlias(), sqlDatasetPreHierarchy.getSqlTables().get(0).getVtlDataset());

        List<SqlComponent> measures = getSqlComponentsByRole(sqlDatasetPreHierarchy, VtlComponentRole.MEASURE);
        measures.get(0).setAliasName(oldName);

        sqlDatasetPreHierarchy.getSqlTables().addAll(sqlDatasetViral.getSqlTables());
        sqlDatasetPreHierarchy.getSqlTables().get(1).setJoinType(JoinType.LEFT_OUTER);
        sqlDatasetPreHierarchy = buildOnConditionStandard(sqlDatasetPreHierarchy, sqlDatasetViral, sqlDatasetPreHierarchy);

        //filtrare sqlDataset
        SqlDataset sqlDatasetHierarchyComputed = copySqlDatasetWithoutSqlComponent(sqlDatasetPreHierarchy);
        sqlDatasetHierarchyComputed.getComponentList().addAll(getSqlComponentsByRole(sqlDatasetPreHierarchy, VtlComponentRole.IDENTIFIER));
        sqlDatasetHierarchyComputed.getComponentList().addAll(getSqlComponentsByRole(sqlDatasetPreHierarchy, VtlComponentRole.MEASURE));
        if (vtlHierarchyExpression.getValidationMode() != ValidationMode.ALWAYS_ZERO && vtlHierarchyExpression.getValidationMode() != ValidationMode.ALWAYS_NULL) {
            sqlDatasetHierarchyComputed.setWhereCondition(
                    new CustomSql(sqlDatasetHierarchyComputed.getSqlTables().get(0).getAliasName() + ".FLAG = 'Y'")
            );
        }
        sqlDatasetHierarchyComputed.getComponentList().addAll(getSqlComponentsByRole(sqlDatasetViral, VtlComponentRole.VIRAL));
        return sqlDatasetHierarchyComputed;
    }

    /**
     * A partire da un dataset genera un sqlDataset non contenente gli attributi. Rimangono gli attribut virali
     *
     * @param vtlDataset
     * @return
     */
    @Override
    public SqlDataset buildDatasetWithoutViral(VtlDataset vtlDataset) {
        List<VtlComponent> vtlComponents = new ArrayList<>();
        vtlComponents.addAll(vtlDataset.getIdentifiers());
        vtlComponents.addAll(vtlDataset.getMeasures());
        vtlComponents.addAll(vtlDataset.getViral());
        VtlDataset vtlDatasetResult = datasetUtilityService.createTemporaryDataset(vtlComponents, false);
        vtlDatasetResult.setName(vtlDataset.getName());
        return createSqlDataset(vtlDatasetResult);
    }

    /**
     * Data una lista di SqlDataset aggiunge ai dataset un componente che indica la priorità di elaborazione della query.
     * La priorità viene utilizzata per far valutare alcune query prima delle altre
     *
     * @param sqlDatasetList
     * @return
     */
    @Override
    public List<SqlDataset> buildSqlDatasetWithPriorityComponents(List<SqlDataset> sqlDatasetList) {
        List<SqlDataset> datasets = new ArrayList<>();
        for (int i = 0; i < sqlDatasetList.size(); i++) {
            SqlDataset sqlDatasetCopied = copySqlDatasetWithSqlComponent(sqlDatasetList.get(i), sqlDatasetList.get(i).getComponentList());
            sqlDatasetCopied.getComponentList().add(sqlComponentUtilityService.createPriorityComponent(i + 1));
            datasets.add(sqlDatasetCopied);
        }
        return datasets;
    }

    /**
     * Data una vtlHierarchical expression e una from specifica, genera un nuovo dataset che rappresenta il dataset
     * sul quale verrà applicata la modale.
     *
     * @param vtlCheckHierarchy
     * @param queryModal
     * @return
     */
    @Override
    public SqlDataset buildCheckHierarchyModal(VtlCheckHierarchy vtlCheckHierarchy, SqlObject queryModal) {
        SqlDataset sqlDatasetResult = new SqlDataset();
        SqlDataset sqlDatasetOperand = vtlCheckHierarchy.getVtlVarId().getSqlResult().getSqlDataset();
        sqlDatasetResult.getComponentList().addAll(sqlComponentUtilityService.getSqlComponentsByRole(sqlDatasetOperand.getComponentList(), VtlComponentRole.IDENTIFIER));
        //Crea ruleID
        sqlDatasetResult.getComponentList().add(sqlComponentUtilityService.createSqlComponent(VtlType.STRING, "RULEID", VtlComponentRole.IDENTIFIER));
        //ME_DS
        SqlComponent meDsComponent = sqlComponentUtilityService.createSqlComponent(
                vtlCheckHierarchy.getVtlVarId().getResultExpression().getResult().getMeasures().get(0).getType(),
                "ME_DS",
                VtlComponentRole.MEASURE);
        meDsComponent.setAliasName(vtlCheckHierarchy.getVtlVarId().getResultExpression().getResult().getMeasures().get(0).getName());
        sqlDatasetResult.getComponentList().add(
                meDsComponent
        );
        //ME_RULE
        sqlDatasetResult.getComponentList().add(sqlComponentUtilityService.createSqlComponent(
                vtlCheckHierarchy.getVtlVarId().getResultExpression().getResult().getMeasures().get(0).getType(),
                "ME_RULE",
                VtlComponentRole.MEASURE)
        );
        //BOOL_VAR
        SqlComponent boolVar = sqlComponentUtilityService.createSqlComponent(
                VtlType.STRING,
                "BOOL_VAR",
                VtlComponentRole.MEASURE);
        if (vtlCheckHierarchy.getValidationMode() != ValidationMode.NON_NULL && vtlCheckHierarchy.getValidationMode() != ValidationMode.NON_ZERO
        ) {
            SqlObject caseBool = sqlObjectUtilityService.createCaseStatement(new CustomSql("FLG_ALL = 'N'"), new CustomSql("NULL"), new CustomSql("BOOL_VAR"));
            boolVar.setResult(caseBool);
        }
        sqlDatasetResult.getComponentList().add(boolVar);
        //IMBALANCE
        sqlDatasetResult.getComponentList().add(sqlComponentUtilityService.createSqlComponent(
                vtlCheckHierarchy.getVtlVarId().getResultExpression().getResult().getMeasures().get(0).getType(),
                "IMBALANCE",
                VtlComponentRole.MEASURE)
        );
        //ERRORCODE
        sqlDatasetResult.getComponentList().add(sqlComponentUtilityService.createSqlComponent(
                VtlType.STRING,
                "ERRORCODE",
                VtlComponentRole.MEASURE)
        );
        //ERRORLEVEL
        sqlDatasetResult.getComponentList().add(sqlComponentUtilityService.createSqlComponent(
                VtlType.STRING,
                "ERRORLEVEL",
                VtlComponentRole.MEASURE)
        );
        //FLAG_ONE
        if (vtlCheckHierarchy.getValidationMode() != ValidationMode.NON_NULL) {
            sqlDatasetResult.getComponentList().add(sqlComponentUtilityService.createSqlComponent(
                    VtlType.STRING,
                    "FLG_ONE",
                    VtlComponentRole.MEASURE)
            );
        }
        //FLAG_ALL
        if (vtlCheckHierarchy.getValidationMode() != ValidationMode.NON_ZERO) {
            sqlDatasetResult.getComponentList().add(sqlComponentUtilityService.createSqlComponent(
                    VtlType.STRING,
                    "FLG_ALL",
                    VtlComponentRole.MEASURE)
            );
        }
        SqlTable sqlTable = new SqlTable();
        sqlTable.setCustomFrom(queryModal);
        sqlTable.setVtlDataset(vtlCheckHierarchy.getVtlVarId().getResultExpression().getResult());
        sqlDatasetResult.getSqlTables().add(sqlTable);


        return sqlDatasetResult;
    }

    /**
     * prende in ingresso un dataset e costruisce un sqlDataset contenenete solo gli identificativi e le misure
     *
     * @param vtlDataset
     * @return
     */
    @Override
    public SqlDataset cleanDatasetForModal(VtlDataset vtlDataset) {
        VtlDataset datasetResult = new VtlDataset();
        datasetResult.setName(vtlDataset.getName());
        datasetResult.getVtlComponents().addAll(componentUtilityService.copyComponents(vtlDataset.getIdentifiers()));
        datasetResult.getVtlComponents().addAll(componentUtilityService.copyComponents(vtlDataset.getMeasures()));
        return createSqlDataset(datasetResult);
    }

    /**
     * Dati in ingresso due dataset, uno con componenti virali e uno senza ed effettua una join. La join risultante manterrà
     * gli attributi virali
     *
     * @param datasetWithViral
     * @param datasetWithoutViral
     * @return
     */
    @Override
    public SqlDataset addViralAttribute(SqlDataset datasetWithViral, SqlDataset datasetWithoutViral) {
        SqlDataset sqlDatasetResult = copySqlDatasetWithSqlComponent(datasetWithoutViral, datasetWithoutViral.getComponentList());
        sqlDatasetResult.setUpAlias(dbTableUtilityService.getDbSpec().getNextAlias(), null);
        datasetWithViral.setUpAlias(dbTableUtilityService.getDbSpec().getNextAlias(), null);
        sqlDatasetResult.getComponentList().addAll(sqlComponentUtilityService.getSqlComponentsByRole(datasetWithViral.getComponentList(), VtlComponentRole.VIRAL));
        sqlDatasetResult.getSqlTables().addAll(datasetWithViral.getSqlTables());
        buildOnConditionStandard(sqlDatasetResult, datasetWithViral, sqlDatasetResult);
        return sqlDatasetResult;
    }

    /**
     * il metodo raccoglie i diversi passaggi necessari per la traduzione di una ruleset e crea un dataset che rappresenta
     * la query che deve essere effettuata sulla query che ha incorporato la model.
     *
     * @param vtlCheckHierarchy
     * @param selectModal
     * @return
     */
    @Override
    public SqlDataset createDatasetCheckHierarchical(VtlCheckHierarchy vtlCheckHierarchy, SqlDataset selectModal) {
        SqlDataset sqlDatasetResult = createSqlDataset(vtlCheckHierarchy.getResultExpression().getResult());

        String boolVarField = "tx.BOOL_VAR = '";
        for (SqlComponent sqlComponent : sqlDatasetResult.getComponentList()) {
            if (sqlComponent.getAliasName().equalsIgnoreCase("ERRORCODE")) {
                SqlObject caseStatement = sqlObjectUtilityService.createCaseStatement(
                        new CustomSql(boolVarField + sqlObjectUtilityService.getApplicationDefaultValueFalse() + "'"),
                        sqlComponent.getResult(),
                        new CustomSql("NULL")
                );
                sqlComponent.setResult(caseStatement);
            }

            if (sqlComponent.getAliasName().equalsIgnoreCase("ERRORLEVEL")) {
                SqlObject caseStatement = sqlObjectUtilityService.createCaseStatement(
                        new CustomSql(boolVarField + sqlObjectUtilityService.getApplicationDefaultValueFalse() + "'"),
                        sqlComponent.getResult(),
                        new CustomSql("NULL")
                );
                sqlComponent.setResult(caseStatement);
            }
        }
        sqlDatasetResult.getSqlTables().get(0).setCustomFrom(sqlObjectUtilityService.createSelectFromSqlDataset(selectModal));
        if (vtlCheckHierarchy.getValidationMode() == ValidationMode.NON_NULL) {
            sqlDatasetResult.setWhereCondition(
                    new CustomSql("tx.FLG_ALL = 'Y'")
            );
        }
        if (vtlCheckHierarchy.getValidationMode() == ValidationMode.NON_ZERO || vtlCheckHierarchy.getValidationMode() == ValidationMode.PARTIAL_NULL
                || vtlCheckHierarchy.getValidationMode() == ValidationMode.PARTIAL_ZERO) {
            sqlDatasetResult.setWhereCondition(
                    new CustomSql("tx.FLG_ONE = 'Y'")
            );
        }
        if (vtlCheckHierarchy.getOutputMode() == OutputMode.INVALID) {
            if (sqlDatasetResult.getWhereCondition() != null) {
                sqlDatasetResult.setWhereCondition(new CustomSql(sqlDatasetResult.getWhereCondition().toString() + " AND " + boolVarField + sqlObjectUtilityService.getApplicationDefaultValueFalse() + "'"));
            } else {
                sqlDatasetResult.setWhereCondition(
                        new CustomSql(boolVarField + sqlObjectUtilityService.getApplicationDefaultValueFalse() + "'")
                );
            }


        }
        return sqlDatasetResult;
    }

    /**
     * Genera un SqlDataset contenente il risultato di un operazione di pivot. Questo dataset viene utilizzato per
     * mostrare il risultato dell'operazione di pivot
     *
     * @param vtlPivotClauseExpression
     * @return
     */
    public SqlDataset buildPrePivot(VtlPivotClauseExpression vtlPivotClauseExpression) {
        SqlDataset sqlDatasetOperand = vtlPivotClauseExpression.getVtlDataset().getSqlResult().getSqlDataset();
        SqlDataset sqlDatasetResult = copySqlDatasetWithoutSqlComponent(sqlDatasetOperand);
        sqlDatasetResult.getComponentList().addAll(
                sqlComponentUtilityService.getSqlComponentsByRole(
                        sqlDatasetOperand.getComponentList(),
                        VtlComponentRole.IDENTIFIER
                )
        );
        sqlDatasetResult.getComponentList().add(
                sqlComponentUtilityService.getSqlComponentByName(
                        sqlDatasetOperand,
                        vtlPivotClauseExpression.getMeasure().getComponentName()
                )
        );

        return sqlDatasetResult;
    }

    /**
     * Dato un SqlDataset viene restituito un sqldataset con i componenti ordinati in ordine alfabetico
     *
     * @param sqlDataset
     * @return
     */
    @Override
    public SqlDataset getDatasetOrdered(SqlDataset sqlDataset) {
        Map<String, SqlComponent> map = new TreeMap<>();
        SqlDataset result = copySqlDatasetWithoutSqlComponent(sqlDataset);
        List<SqlComponent> components = sqlDataset.getComponentList();

        for (SqlComponent sqlComponent : components) {
            map.put(sqlComponent.getAliasName(), sqlComponent);
        }

        for (Map.Entry<String, SqlComponent> entry : map.entrySet()) {
            result.getComponentList().add(entry.getValue());
        }

        return result;
    }

    /**
     * Il metodo prende in ingresso un sqlDataset e un componente e se trova un sqlComponent con lo stesso nome di quello immesso
     * lo sostituisce. Se non lo trova lo aggiunte all'sqlDataset
     *
     * @param sqlDataset
     * @param sqlComponent
     * @return
     */
    @Override
    public SqlDataset substituteSqlComponent(SqlDataset sqlDataset, SqlComponent sqlComponent) {
        SqlDataset sqlDatasetResult = copySqlDatasetWithoutSqlComponent(sqlDataset);
        for (SqlComponent sqlComponentDataset : sqlDataset.getComponentList()) {
            if (sqlComponentDataset.getAliasName().equalsIgnoreCase(sqlComponent.getAliasName())) {
                sqlDatasetResult.getComponentList().add(sqlComponent);
            } else {
                sqlDatasetResult.getComponentList().add(sqlComponentDataset);
            }
        }
        return sqlDatasetResult;
    }

}


