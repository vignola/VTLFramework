package com.capgemini.istat.vtlcompiler.vtlsqlbuilder.service.utility;

import com.capgemini.istat.vtlcompiler.vtlcommon.model.dataset.VtlComponent;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.dataset.VtlComponentRole;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.dataset.VtlType;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.operator.Operator;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.translation.SqlComponent;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.translation.SqlDataset;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.constant.VtlConstantExpression;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.VtlExpression;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.clause.analytic.VtlAnalyticFunctionExpression;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.comparison.VtlBetweenExpression;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.comparison.VtlInExpression;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.function.VtlBinaryExpression;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.time.VtlCurrentDate;

import java.util.LinkedList;
import java.util.List;

public interface ISqlComponentUtilityService {
    SqlComponent applyUnaryFunctionToSqlComponent(SqlComponent sqlComponent, VtlExpression parameter, List<VtlExpression> optionalParameter, Operator operator);

    SqlComponent applyFlowToStockStockToFlowFunctionToSqlComponent(SqlComponent sqlComponent, List<SqlComponent> identifierList, SqlComponent timeIdentifier, Operator operator);

    SqlComponent applyBinaryFunctionToSqlComponent(SqlComponent sqlComponentLeft, SqlComponent sqlComponentRight, Operator operator);

    SqlComponent applyUnaryConditionToSqlComponent(SqlComponent sqlComponent, Operator operator, VtlExpression vtlExpression);

    SqlComponent applyUnaryConditionWithParameterToSqlComponent(SqlComponent sqlComponent, Operator operator, VtlExpression vtlExpression, VtlExpression parameter, List<VtlExpression> optionalParameter);

    SqlComponent applyInNotInFunctionToSqlComponent(SqlComponent sqlComponent, Operator operator, VtlInExpression vtlInExpression);

    SqlComponent applyBetweenConditionToSqlComponent(SqlComponent sqlComponent, Operator operator, VtlBetweenExpression vtlBetweenExpression);

    SqlComponent applyBetweenFunctionToSqlComponent(SqlComponent sqlComponent, Operator operator, VtlBetweenExpression vtlBetweenExpression);

    SqlComponent applyInNotInConditionToSqlComponent(SqlComponent sqlComponent, Operator operator, VtlInExpression vtlInExpression);

    SqlComponent applyBooleanBinaryConditionToSqlComponent(SqlComponent sqlComponentLeft, SqlComponent sqlComponentRight, Operator operator, VtlBinaryExpression vtlExpression);

    SqlComponent applyComparisonBinaryConditionToSqlComponent(SqlComponent sqlComponentLeft, SqlComponent sqlComponentRight, Operator operator, VtlBinaryExpression vtlExpression);

    SqlComponent applyConditionalBinaryConditionToSqlComponent(SqlComponent sqlComponentLeft, SqlComponent sqlComponentRight, Operator operator, VtlBinaryExpression vtlExpression);

    SqlComponent applyNumericOrStringBinaryConditionToSqlComponent(SqlComponent sqlComponentLeft, SqlComponent sqlComponentRight, Operator operator, VtlBinaryExpression vtlExpression);

    SqlComponent applyAnalyticFunctionToSqlComponent(SqlComponent sqlComponent, SqlDataset sqlDataset, VtlAnalyticFunctionExpression vtlAnalyticFunctionExpression, Operator operator);

    SqlComponent createPriorityComponent(Integer priority);

    SqlComponent getSqlComponentByName(List<SqlComponent> sqlComponents, String name);

    SqlComponent getSqlComponentByName(SqlDataset sqlDataset, String name);

    SqlComponent copySqlComponent(SqlComponent sqlComponent);

    List<SqlComponent> copySqlComponents(List<SqlComponent> sqlComponents);

    SqlComponent createSqlComponent(VtlType vtlType, String componentName, VtlComponentRole vtlComponentRole);

    SqlComponent createSqlComponent(VtlComponent vtlComponent);

    List<SqlComponent> getSupersetIdentifier(SqlDataset sqlDatasetLeft, SqlDataset sqlDatasetRight);

    List<SqlComponent> applyFunctionToCommonComponent(SqlDataset sqlDatasetLeft, SqlDataset sqlDatasetRight, VtlComponentRole vtlComponentRole, Operator operator);

    List<SqlComponent> getSqlComponentsByRole(List<SqlComponent> sqlComponents, VtlComponentRole vtlComponentRole);

    List<SqlComponent> applyFunctionToCommonViralComponent(SqlDataset sqlDatasetLeft, SqlDataset sqlDatasetRight);

    SqlComponent applyConcatToViralsComponent(SqlComponent sqlComponentLeft, SqlComponent sqlComponentRight);

    LinkedList<SqlComponent> getSpecificCommonComponent(List<List<SqlComponent>> sqlComponents, int tableIndex);

    LinkedList<LinkedList<SqlComponent>> getCommonComponent(List<List<SqlComponent>> sqlComponents);

    SqlComponent getScalarComponent(VtlType vtlType, String value, VtlComponentRole vtlComponentRole);

    SqlComponent applyCastOperator(SqlComponent sqlComponent, String mask, Operator operator, VtlType vtlType);

    SqlComponent applyCurrentDateToSqlComponent(VtlCurrentDate vtlCurrentDate);

    SqlComponent getScalarComponent(VtlConstantExpression vtlConstantExpression);
    
    SqlComponent applyIfToSqlComponent(SqlComponent sqlComponentCondition, SqlComponent sqlComponentThen, SqlComponent sqlComponentElse, Operator operator, Boolean isWhereCondition);
}
