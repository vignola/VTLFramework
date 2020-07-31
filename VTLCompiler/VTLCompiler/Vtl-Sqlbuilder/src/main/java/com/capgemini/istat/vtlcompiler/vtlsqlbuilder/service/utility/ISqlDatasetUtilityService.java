package com.capgemini.istat.vtlcompiler.vtlsqlbuilder.service.utility;

import com.capgemini.istat.vtlcompiler.vtlcommon.model.dataset.VtlComponentRole;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.dataset.VtlDataset;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.dataset.VtlType;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.operator.Operator;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.translation.SqlComponent;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.translation.SqlDataset;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.translation.SqlResult;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.translation.SqlTable;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.define.ruleset.VtlErrorRuleset;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.define.ruleset.datapoint.VtlDPRule;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.VtlExpression;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.VtlMembership;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.clause.analytic.VtlAnalyticFunctionExpression;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.clause.drop.VtlKeepOrDropClauseExpression;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.clause.pivot.VtlPivotClauseExpression;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.clause.rename.VtlRenameClauseExpression;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.clause.sub.VtlSubSpaceExpression;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.comparison.VtlBetweenExpression;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.comparison.VtlInExpression;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.hierarchy.VtlHierarchyExpression;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.join.VtlJoinExpression;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.join.VtlJoinSelectClause;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.validation.OutputMode;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.validation.VtlCheckHierarchy;
import com.healthmarketscience.sqlbuilder.SqlObject;

import java.util.List;

public interface ISqlDatasetUtilityService {
    SqlDataset createSqlDataset(VtlDataset vtlDataset);

    SqlDataset applyFunctionToSqlDataset(SqlDataset sqlDataset, VtlExpression parameter, List<VtlExpression> optionalParameter, Operator operator);

    SqlDataset applyAggregateFunctionToSqlDataset(SqlDataset sqlDataset, VtlExpression parameter, List<VtlExpression> optionalParameter, Operator operator);

    SqlDataset applyAnaliticFunctionToSqlDataset(SqlDataset sqlDataset, VtlAnalyticFunctionExpression vtlAnalyticFunctionExpression, Operator operator);

    SqlDataset applyInNotInFunctionToSqlDataset(SqlDataset sqlDataset, Operator operator, VtlInExpression vtlInExpression);

    SqlDataset applyBetweenFunctionToSqlDataset(SqlDataset sqlDataset, Operator operator, VtlBetweenExpression vtlBetweenExpression);

    SqlDataset applyTimeFunctionToSqlDataset(SqlDataset sqlDataset, VtlExpression parameter, List<VtlExpression> optionalParameter, Operator operator);

    SqlDataset applyPeriodIndicatorFunctionToSqlDataset(SqlDataset sqlDataset, Operator operator);

    SqlDataset applyFlowToStockStockToFlowFunctionToSqlDataset(SqlDataset sqlDataset, Operator operator);

    SqlDataset applyFillTimeSeriesFunctionToSqlDataset(VtlDataset vtlDataset);
            
    SqlDataset applyBinaryFunction(SqlDataset sqlDatasetLeft, SqlDataset sqlDatasetRight, Operator operator);

    SqlDataset applyBinaryFunctionScalar(SqlDataset sqlDataset, SqlComponent scalarComponent, boolean isScalarLeft, Operator operator);

    SqlDataset applyKeepClauseToSqlDataset(SqlDataset sqlDataset, VtlKeepOrDropClauseExpression vtlKeepClauseExpression);

    SqlDataset applyDropClauseToSqlDataset(SqlDataset sqlDataset, VtlKeepOrDropClauseExpression vtlKeepOrDropClauseExpression);

    SqlDataset applyRenameClauseToSqlDataset(SqlDataset sqlDataset, VtlRenameClauseExpression vtlRenameClauseExpression);

    SqlDataset applySubspaceClauseToSqlDataset(SqlDataset sqlDataset, VtlSubSpaceExpression vtlSubSpaceExpression);

    SqlDataset applyGroupClauseToSqlDataset(VtlDataset vtlDataset, SqlDataset sqlDataset, SqlComponent sqlComponentGroupAll);

    SqlDataset aggregateSqlComponent(VtlDataset vtlDataset, List<SqlComponent> sqlComponents, SqlDataset sqlDataset);

    SqlDataset copySqlDatasetWithoutSqlComponent(SqlDataset sqlDataset);

    SqlDataset copySqlDatasetWithSqlComponent(SqlDataset sqlDataset, List<SqlComponent> sqlComponents);

    List<SqlTable> copySqlTables(List<SqlTable> sqlTables);

    SqlDataset applyMembership(VtlMembership vtlMembership, SqlDataset sqlDataset);

    List<SqlComponent> getSqlComponentsByRole(SqlDataset sqlDataset, VtlComponentRole vtlComponentRole);

    SqlDataset buildPreJoinTable(VtlJoinSelectClause vtlJoinSelectClause, Operator operator);

    SqlDataset buildPreFullJoinTable(VtlJoinSelectClause vtlJoinSelectClause, Operator operator);

    SqlDataset cleanJoinTable(VtlJoinExpression vtlJoinExpression);

    SqlDataset applyExistIn(SqlResult sqlResultLeft, SqlResult sqlResultRight, VtlDataset vtlDatasetResult, String negate);

    SqlDataset applyCastOperator(SqlDataset sqlComponent, String mask, Operator operator, VtlType vtlType);

    SqlDataset applyIfToSqlDataset(SqlDataset sqlDatasetLeft, SqlDataset sqlDatasetRight, Boolean whereCondition);
    
    SqlDataset applyCheckToSqlDataset(SqlDataset sqlDatasetCheck, SqlDataset sqlDatasetBoolean, SqlDataset sqlDatasetImbalance, VtlErrorRuleset errorRuleset, OutputMode outputMode);

    SqlDataset buildOnConditionStandard(SqlDataset sqlDatasetLeft, SqlDataset sqlDatasetRight, SqlDataset sqlDatasetResult);
    
    SqlDataset checkDataPoint(SqlDataset sqlDatasetToCheck, VtlDPRule vtlDPRule, OutputMode outputMode);

    SqlDataset createQueryForSetBinaryJoin(SqlDataset sqlDatasetLeft, SqlDataset sqlDatasetRight);

    SqlDataset buildHierarchyComputedDataset(VtlHierarchyExpression vtlHierarchyExpression, SqlObject buildFrom);

    SqlDataset buildDatasetWithoutViral(VtlDataset vtlDataset);

    List<SqlDataset> buildSqlDatasetWithPriorityComponents(List<SqlDataset> sqlDatasetList);

    SqlDataset buildCheckHierarchyModal(VtlCheckHierarchy vtlCheckHierarchy, SqlObject queryModal);

    SqlDataset cleanDatasetForModal(VtlDataset vtlDataset);

    SqlDataset addViralAttribute(SqlDataset datasetWithViral, SqlDataset datasetWithoutViral);

    SqlDataset createDatasetCheckHierarchical(VtlCheckHierarchy vtlCheckHierarchy, SqlDataset selectModal);

    SqlDataset buildPrePivot(VtlPivotClauseExpression vtlPivotClauseExpression);
    
    SqlDataset getDatasetOrdered(SqlDataset sqlDataset);

    SqlDataset substituteSqlComponent(SqlDataset sqlDataset, SqlComponent sqlComponent);

}
