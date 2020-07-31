package com.capgemini.istat.vtlcompiler.vtlsqlbuilder.service.utility;

import com.capgemini.istat.vtlcompiler.vtlcommon.model.operator.Operator;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.translation.SqlComponent;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.translation.SqlDataset;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.constant.VtlConstant;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.constant.VtlConstantExpression;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.dataset.VtlComponentId;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.define.ruleset.hierarchical.VtlHRRule;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.VtlExpression;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.clause.analytic.VtlAnalyticFunctionExpression;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.clause.pivot.VtlPivotClauseExpression;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.comparison.VtlBetweenExpression;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.comparison.VtlInExpression;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.function.VtlBinaryExpression;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.hierarchy.VtlHierarchyExpression;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.validation.VtlCheckHierarchy;
import com.healthmarketscience.sqlbuilder.*;

import java.util.LinkedList;
import java.util.List;

public interface ISqlObjectUtilityService {
    String getApplicationDefaultValueTrue();

    void setApplicationDefaultValueTrue(String applicationDefaultValueTrue);

    String getApplicationDefaultValueFalse();

    void setApplicationDefaultValueFalse(String applicationDefaultValueFalse);

    String getApplicationDefaultValueDomainCode();

    void setApplicationDefaultValueDomainCode(String applicationDefaultValueDomainCode);

    String getSqLOperator(String operatorValue);

    String getFormattedTableName(String tableName);
    
    SelectQuery createSelectFromSqlDataset(SqlDataset sqlDataset);

    SqlObject createTableQuery(String tableName, SqlObject sqlObject);

    List<SqlObject> createIndex(List<SqlComponent> sqlComponents, String tableName);

    InsertSelectQuery insertSelectQuery(String tableName, SelectQuery selectQuery);

    SqlObject dropTableQuery(String tableName);

    SqlObject applyUnaryFunctionToSqlObject(SqlObject sqlObject, VtlExpression parameter, List<VtlExpression> optionalParameter, Operator operator);

    SqlObject applyOperatorFunctionToSqlObjects(SqlComponent sqlComponentLeft, SqlComponent sqlComponentRight, Operator operator);

    SqlObject applyUnaryConditionToSqlObject(SqlObject sqlObject, Operator operator, VtlExpression vtlExpression);

    SqlObject applyUnaryConditionWithParameterToSqlObject(SqlObject sqlObject, Operator operator, VtlExpression vtlExpression, VtlExpression parameter, List<VtlExpression> optionalParameter);

    SqlObject applyInNotInFunctionToSqlObject(SqlObject sqlObject, Operator operator, VtlInExpression vtlInExpression);

    SqlObject applyInNotInConditionToSqlObject(SqlObject sqlObject, Operator operator, VtlInExpression vtlInExpression);

    SqlObject applyBetweenFunctionToSqlObject(SqlObject sqlObject, VtlBetweenExpression vtlBetweenExpression);

    SqlObject applyBetweenConditionToSqlObject(SqlObject sqlObject, VtlBetweenExpression vtlBetweenExpression);

    SqlObject applyBooleanBinaryConditionToSqlObjects(SqlComponent sqlComponentLeft, SqlComponent sqlComponentRight, Operator operator, VtlBinaryExpression vtlExpression);

    SqlObject applyComparisonBinaryConditionToSqlObjects(SqlComponent sqlComponentLeft, SqlComponent sqlComponentRight, Operator operator, VtlBinaryExpression vtlExpression);

    SqlObject applyConditionalBinaryConditionToSqlObjects(SqlComponent sqlComponentLeft, SqlComponent sqlComponentRight, Operator operator, VtlBinaryExpression vtlExpression);

    SqlObject applyNumericOrStringBinaryConditionToSqlObjects(SqlComponent sqlComponentLeft, SqlComponent sqlComponentRight, Operator operator, VtlBinaryExpression vtlExpression);

    SqlObject applyDefaultBooleanUnaryConditionToSqlObject(SqlObject sqlObject, VtlExpression vtlExpression);

    SqlObject applyAnalyticFunctionToSqlObject(SqlObject sqlObject, SqlDataset sqlDataset, VtlAnalyticFunctionExpression vtlAnalyticFunctionExpression, Operator operator);

    SqlObject createPrioritySqlObjects(Integer priority);

    SqlObject createUnionQueryForSetUnnary(List<SqlDataset> sqlDatasetList, Operator operator);

    SqlObject createExecuteFillTimeSeries(String inTableName, String timeIdentifier, List<String> identifierList, Operator operator);
    
    InCondition getExistInCondition(List<SqlComponent> identifierCommonList, SelectQuery subQuery, boolean neagate);

    SqlObject applyFlowToStockFunctionToSqlObject(SqlObject sqlObject, List<SqlComponent> identifierList, SqlObject timeIdentifier, Operator operator);

    SqlObject applyStockToFlowFunctionToSqlObject(SqlObject sqlObject, List<SqlComponent> identifierList, SqlObject timeIdentifier, Operator operator);

    List<BinaryCondition> applyCommonComponentsCondition(LinkedList<LinkedList<SqlComponent>> commonComponents);

    SqlObject applParenthesesToSqlObject(SqlObject sqlObject);

    void addCustomFrom(SelectQuery selectQuery);
    
    SqlObject applyCurrentDateToSqlObjects(Operator operator);

    SqlObject getConstantSql(VtlConstant vtlConstant);
    
    SqlObject applyIfToSqlObject(SqlObject sqlObjectCondition, SqlObject sqlObjectThen, SqlObject sqlObjectElse, Boolean isWhereCondition);
    
    SqlObject createSelectForUnpivot(VtlComponentId identifier, VtlComponentId measure, SqlDataset sqlDatasetFrom, SqlDataset sqlDatasetUnpivot);
    
    SelectQuery createSelectForPivot(VtlComponentId identifier, VtlComponentId measure, List<VtlConstantExpression> constantExpressions, SqlDataset sqlDatasetFrom, SqlDataset sqlDatasetPivot);

    SqlObject createCaseStatement(SqlObject condition, Object resultThen, Object resultElse);

    SqlObject applyWindowClause(List<SqlComponent> sqlComponents, SqlObject partitionColums, SqlObject timeIdentifier);

    SqlObject buildModel(VtlHierarchyExpression vtlHierarchyExpression);

    SqlObject buildCheckModel(VtlCheckHierarchy vtlCheckHierarchy);

    SqlObject buildRuleTable(List<VtlHRRule> vtlHRRules);

    SqlObject buildPivot(VtlPivotClauseExpression vtlPivotClauseExpression);
    
    SqlObject createCastNull(String domainValue);
        
    SqlObject buildComponentPivot(SqlComponent sqlComponent, VtlPivotClauseExpression vtlPivotClauseExpression);

}
