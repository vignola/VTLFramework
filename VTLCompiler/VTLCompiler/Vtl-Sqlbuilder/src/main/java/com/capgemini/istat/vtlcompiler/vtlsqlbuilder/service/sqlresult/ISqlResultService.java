package com.capgemini.istat.vtlcompiler.vtlsqlbuilder.service.sqlresult;

import com.capgemini.istat.vtlcompiler.vtlcommon.model.dataset.VtlDataset;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.operator.Operator;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.translation.SqlComponent;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.translation.SqlDataset;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.translation.SqlResult;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.constant.VtlConstantExpression;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.dataset.VtlComponentId;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.dataset.VtlVarId;
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
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.validation.VtlCheck;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.validation.VtlCheckDatapoint;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.validation.VtlCheckHierarchy;
import com.healthmarketscience.sqlbuilder.SqlObject;

import java.util.LinkedList;
import java.util.List;

/**
 * L'interfaccia offre tutti i metodi di traduzione offerte ai nodi di traduzione.
 * L'interfaccia viene poi implementata dai specifici servizi di SQL specifici per permettere la personalizzazione
 * della soluzione secondo le dinamiche tipiche del linguaggio
 * La maggior parte dei metodi effettua poca logica se non quella di aggregazione dei risultati dei servizi di utility
 *
 * @see SqlResult
 */
public interface ISqlResultService {
    /**
     * Crea un oggetto sqlResult che restituisce il risultato di una select semplice su una tabella
     *
     * @param isAComponent
     * @param sqlDataset
     * @param vtlVarId
     * @return
     */
    SqlResult getVarIdTableResult(boolean isAComponent, SqlDataset sqlDataset, VtlVarId vtlVarId);

    /**
     * Restituisce la rappresentazione di una selezione su un componente
     *
     * @param sqlDataset
     * @param vtlComponentId
     * @return
     */
    SqlResult getSqlComponentResult(SqlDataset sqlDataset, VtlComponentId vtlComponentId);

    /**
     * Applica una funzione di tipo time che possiede almeno un parametro e restituisce il risultato
     *
     * @param vtlTimeUnaryWithOneParameterExpression
     * @param sqlResult
     * @return
     */
    SqlResult applyTimeWithParameterFunction(VtlTimeUnaryWithOneParameterExpression vtlTimeUnaryWithOneParameterExpression, SqlResult sqlResult);

    /**
     * Applica una funzione unaria di tipo time e restituisce il risultato SQL
     *
     * @param vtlTimeUnaryExpression
     * @param sqlResult
     * @return
     */
    SqlResult applyUnaryTimeFunction(VtlTimeUnaryExpression vtlTimeUnaryExpression, SqlResult sqlResult);

    /**
     * applica la funzione fill time series e restituisce il risultato SQL
     *
     * @param vtlTimeUnaryExpression
     * @param sqlResult
     * @return
     */
    SqlResult applyUnaryTimeFunctionFillTimeSeries(VtlTimeUnaryExpression vtlTimeUnaryExpression, SqlResult sqlResult);

    /**
     * applica una funzione unaria con un parametro e retituisce il risultato SQL
     *
     * @param vtlUnaryWithOneParameter
     * @param sqlResult
     * @return
     */
    SqlResult applyUnaryWithParameterFunction(VtlUnaryWithOneParameter vtlUnaryWithOneParameter, SqlResult sqlResult);

    /**
     * Applica una funzione di aggregazione e restituisce il risultato SQL
     *
     * @param vtlAggrInvocationExpression
     * @param withExpressione
     * @param sqlResult
     * @return
     */
    SqlResult applyAggregateFunction(VtlExpression vtlAggrInvocationExpression, boolean withExpressione, SqlResult sqlResult);

    /**
     * Applica una funzione analitica e restituisce il risultato SQL
     *
     * @param vtlAnalyticFunctionExpression
     * @param sqlResult
     * @return
     */
    SqlResult applyAnalyticFunction(VtlAnalyticFunctionExpression vtlAnalyticFunctionExpression, SqlResult sqlResult);

    /**
     * Applica una funzione unaria e restituisce il risultato sql
     *
     * @param vtlUnaryExpression
     * @param sqlResult
     * @return
     */
    SqlResult applyUnaryFunction(VtlUnaryExpression vtlUnaryExpression, SqlResult sqlResult);

    /**
     * applica una funzione binaria  a un dataset o a un componente e restituisce il risultato SQL
     *
     * @param vtlBinaryExpression
     * @param sqlResultLeft
     * @param sqlResultRight
     * @param operator
     * @return
     */
    SqlResult applyBinaryFunction(VtlBinaryExpression vtlBinaryExpression, SqlResult sqlResultLeft, SqlResult sqlResultRight, Operator operator);

    /**
     * Applica una funzione binaria a due dataset
     *
     * @param vtlBinaryExpression
     * @param sqlResultLeft
     * @param sqlResultRight
     * @param operator
     * @return
     */
    SqlResult applyBinaryFuctionToSqlDataset(VtlBinaryExpression vtlBinaryExpression, SqlResult sqlResultLeft, SqlResult sqlResultRight, Operator operator);

    /**
     * Svolge in SQl la funzione di rinominazione del dataset
     *
     * @param datasetName
     * @param sqlObjects
     * @param assignName
     * @return
     */
    LinkedList<SqlObject> assignName(String datasetName, LinkedList<SqlObject> sqlObjects, String assignName);

    /**
     * Dato in ingresso un sqlDataset crea le istruzioni SQL di creazione corrispondenti
     *
     * @param sqlDataset
     * @param tableName
     * @param isTemporary
     * @return
     */
    SqlResult createTable(SqlDataset sqlDataset, String tableName, boolean isTemporary);

    /**
     * Crea delle espressioni di create table prima della join per consolidare i risultati delle operazioni precedenti.
     * l'espressione viene creata solo se vengono individuate operazioni precedenti.
     *
     * @param vtlExpression
     * @param aliasName
     * @return
     */
    //crea una temporary table se non è un var ID
    SqlResult renameTableAndCreateTemporaryTable(VtlExpression vtlExpression, String aliasName);

    /**
     * Effettua l'operazione di keep e restituisce il risultato sql
     *
     * @param vtlKeepClauseExpression
     * @param sqlResult
     * @return
     */
    SqlResult applyKeepClause(VtlKeepOrDropClauseExpression vtlKeepClauseExpression, SqlResult sqlResult);

    /**
     * Effettua l'operazione di Drop e restituisce il risultato sql
     *
     * @param vtlKeepOrDropClauseExpression
     * @param sqlResult
     * @return
     */
    SqlResult applyDropClause(VtlKeepOrDropClauseExpression vtlKeepOrDropClauseExpression, SqlResult sqlResult);

    /**
     * applica la funzione di rename e restituisce il risultato SQL
     *
     * @param vtlRenameClauseExpression
     * @param sqlResult
     * @return
     */
    SqlResult applyRenameClause(VtlRenameClauseExpression vtlRenameClauseExpression, SqlResult sqlResult);

    /**
     * applica la funzione di subspace e restituisce il risultato SQL
     *
     * @param vtlSubSpaceExpression
     * @param sqlResult
     * @return
     */
    SqlResult applySubspaceClause(VtlSubSpaceExpression vtlSubSpaceExpression, SqlResult sqlResult);

    /**
     * Effettua l'operazione sql di group by
     *
     * @param vtlDataset
     * @param sqlResult
     * @param sqlResultGroupAll
     * @return
     */
    SqlResult applyGroupClause(VtlDataset vtlDataset, SqlResult sqlResult, SqlResult sqlResultGroupAll);

    /**
     * applica un operatore unario in una where condition
     *
     * @param vtlUnaryExpression
     * @param sqlResult
     * @return
     */
    SqlResult applyUnaryCondition(VtlUnaryExpression vtlUnaryExpression, SqlResult sqlResult);

    /**
     * applica un operatore unario in una where condition
     *
     * @param vtlUnaryWithOneParameter
     * @param sqlResult
     * @return
     */
    SqlResult applyUnaryWithParameterCondition(VtlUnaryWithOneParameter vtlUnaryWithOneParameter, SqlResult sqlResult);

    /**
     * applica un operatore condiziona binario in una where condition
     *
     * @param vtlBinaryExpression
     * @param sqlResultLeft
     * @param sqlResultRight
     * @param operator
     * @return
     */
    SqlResult applyBooleanBinaryCondition(VtlBinaryExpression vtlBinaryExpression, SqlResult sqlResultLeft, SqlResult sqlResultRight, Operator operator);

    /**
     * applica un operazione di comparazione binaria in una where condition
     *
     * @param vtlBinaryExpression
     * @param sqlResultLeft
     * @param sqlResultRight
     * @param operator
     * @return
     */
    SqlResult applyComparisonBinaryCondition(VtlBinaryExpression vtlBinaryExpression, SqlResult sqlResultLeft, SqlResult sqlResultRight, Operator operator);

    /**
     * Applica un operatore condizionale in una where condition
     *
     * @param vtlBinaryExpression
     * @param sqlResultLeft
     * @param sqlResultRight
     * @param operator
     * @return
     */
    SqlResult applyConditionalBinaryCondition(VtlBinaryExpression vtlBinaryExpression, SqlResult sqlResultLeft, SqlResult sqlResultRight, Operator operator);

    /**
     * Applica un operatore numerico binario in una where condition
     *
     * @param vtlBinaryExpression
     * @param sqlResultLeft
     * @param sqlResultRight
     * @param operator
     * @return
     */
    SqlResult applyNumericBinaryCondition(VtlBinaryExpression vtlBinaryExpression, SqlResult sqlResultLeft, SqlResult sqlResultRight, Operator operator);

    /**
     * Applica un operatore di stringa binario in una where condition
     *
     * @param vtlBinaryExpression
     * @param sqlResultLeft
     * @param sqlResultRight
     * @param operator
     * @return
     */
    SqlResult applyStringBinaryCondition(VtlBinaryExpression vtlBinaryExpression, SqlResult sqlResultLeft, SqlResult sqlResultRight, Operator operator);

    /**
     * aggiunge le parentesi a un espressione SQL
     *
     * @param sqlResult
     * @return
     */
    SqlResult applyParentheses(SqlResult sqlResult);

    /**
     * Applica la funzione in not in e genera un espressione SQL
     *
     * @param vtlInExpression
     * @param sqlResult
     * @return
     */
    SqlResult applyInNotInFunction(VtlInExpression vtlInExpression, SqlResult sqlResult);

    /**
     * Applica una funzione in not in in una where condition
     *
     * @param vtlInExpression
     * @param sqlResult
     * @return
     */
    SqlResult applyInNotInCondition(VtlInExpression vtlInExpression, SqlResult sqlResult);

    /**
     * applica la funzione between e genera un espressione sql
     *
     * @param vtlBetweenExpression
     * @param sqlResult
     * @return
     */
    SqlResult applyBetweenFunction(VtlBetweenExpression vtlBetweenExpression, SqlResult sqlResult);

    /**
     * applica la funzione between in una where condition
     *
     * @param vtlBetweenExpression
     * @param sqlResult
     * @return
     */
    SqlResult applyBetweenCondition(VtlBetweenExpression vtlBetweenExpression, SqlResult sqlResult);

    /**
     * Esegue l'operazione di aggregazione su un dataset eliminando i componenti non indicati e restituisce la traduzione Sql
     *
     * @param vtlDataset
     * @param sqlComponents
     * @param sqlDataset
     * @return
     */
    SqlResult aggregateSqlComponent(VtlDataset vtlDataset, List<SqlComponent> sqlComponents, SqlDataset sqlDataset);

    /**
     * Esegue l'opearzione di mebership e restituisce la traduzione sql
     *
     * @param vtlMembership
     * @param sqlResult
     * @return
     */
    SqlResult applyMembership(VtlMembership vtlMembership, SqlResult sqlResult);

    /**
     * Applica l'operatre di Union,intercept e simili su una lista di dataset
     *
     * @param vtlSetUnnaryExpression
     * @param sqlResultList
     * @return
     */
    SqlResult applySetUnnary(VtlSetUnnaryExpression vtlSetUnnaryExpression, List<SqlResult> sqlResultList);

    /**
     * applica l'operatore di setDif/symDif e restituisce la traduzione SQL
     *
     * @param vtlSetBinaryExpression
     * @param sqlResultLeft
     * @param sqlResultRight
     * @return
     */
    SqlResult applySetBinary(VtlSetBinaryExpression vtlSetBinaryExpression, SqlResult sqlResultLeft, SqlResult sqlResultRight);

    /**
     * applica la funzione di exist in e retituisce la traduzione SQL
     *
     * @param vtlExistIn
     * @return
     */
    SqlResult applyExistIn(VtlExistIn vtlExistIn);

    /**
     * Crea il sql per generare il dataset di prejoin per l'operazione di join
     *
     * @param vtlJoinSelectClause
     * @param operator
     * @return
     */
    SqlResult preJoinRenames(VtlJoinSelectClause vtlJoinSelectClause, Operator operator);

    /**
     * effettua l'operazioni SQl per ripulire il dataset al termine dell'operazione di join
     *
     * @param vtlJoinExpression
     * @return
     */
    SqlResult cleanJoinNames(VtlJoinExpression vtlJoinExpression);

    /**
     * effettua l'operazione SQl espressa da una cal su un componente del dataset
     *
     * @param vtlCalcClauseItemExpression
     * @return
     */
    SqlResult calcItemExpression(VtlCalcClauseItemExpression vtlCalcClauseItemExpression);

    /**
     * Applica l'operazione di filter/having by e restituisce l'istruzione SQL
     *
     * @param sqlResult
     * @param vtlExpression
     * @return
     */
    SqlResult applyDefaultBooleanUnaryConditionToSqlObject(SqlResult sqlResult, VtlExpression vtlExpression);

    /**
     * Effettua l'opearzione di cast e restituisce la traduzione
     *
     * @param vtlCastExpression
     * @return
     */
    SqlResult castTranslation(VtlCastExpression vtlCastExpression);

    /**
     * applica la funzione di currentDate e restituisce la traduzione sql
     *
     * @param vtlCurrentDate
     * @return
     */
    SqlResult applyCurrentDate(VtlCurrentDate vtlCurrentDate);

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
    SqlResult applyIf(VtlIfExpression vtlIfExpression, SqlResult sqlResultCondition, SqlResult sqlResultThen, SqlResult sqlResultElse, Boolean isWhereCondition);

    /**
     * applica l'operazione di unpivot e restituisce la traduzione sql
     *
     * @param vtlUnpivotClauseExpression
     * @param sqlResultFrom
     * @return
     */
    SqlResult applyUnpivot(VtlPivotOrUnpivotClauseExpression vtlUnpivotClauseExpression, SqlResult sqlResultFrom);

    /**
     * applica l'operazione di pivot e restituisce la traduzione sql
     *
     * @param vtlPivotClauseExpression
     * @return
     */
    SqlResult applyPivot(VtlPivotClauseExpression vtlPivotClauseExpression);

    /**
     * applica l'opearzione di check e restituisce la traduzione sql
     *
     * @param vtlCheck
     * @param sqlResultBoolean
     * @param sqlResultImbalance
     * @return
     */
    SqlResult applyCheck(VtlCheck vtlCheck, SqlResult sqlResultBoolean, SqlResult sqlResultImbalance);

    /**
     * Crea l'espressione sql per rappresentare una costante
     *
     * @param vtlConstantExpression
     * @return
     */
    SqlResult createConstantSql(VtlConstantExpression vtlConstantExpression);

    /**
     * Effettua l'operazione di checkDatapoint e restituisce la traduzione SQL
     *
     * @param vtlCheckDatapoint
     * @return
     */
    SqlResult checkDatapoint(VtlCheckDatapoint vtlCheckDatapoint);

    /**
     * effettua l'operazione di hierarchy e restituisce la traduzione sql
     *
     * @param vtlHierarchyExpression
     * @return
     */
    SqlResult hierarchy(VtlHierarchyExpression vtlHierarchyExpression);

    /**
     * Effettua l'operazione di check hierarchy e restituisce la traduzione SQL
     *
     * @param vtlCheckHierarchy
     * @return
     */
    SqlResult checkHierarchy(VtlCheckHierarchy vtlCheckHierarchy);
}
