package com.capgemini.istat.vtlcompiler.vtlsqlbuilder.service.utility.mysql;

import com.capgemini.istat.vtlcompiler.vtlcommon.constant.ConstantUtility;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.dataset.VtlType;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.operator.Operator;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.translation.SqlComponent;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.translation.SqlDataset;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.constant.VtlConstantExpression;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.dataset.VtlComponentId;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.clause.analytic.VtlAnalyticFunctionExpression;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.clause.analytic.VtlOrderByItem;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.clause.analytic.VtlWindowingType;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.clause.pivot.VtlPivotClauseExpression;
import com.capgemini.istat.vtlcompiler.vtlsqlbuilder.service.utility.CommonSqlObjectUtilityService;
import com.healthmarketscience.sqlbuilder.*;
import com.healthmarketscience.sqlbuilder.WindowDefinitionClause.FrameBound;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * La classe contiene la logica di costruzione dei diversi operatori SQL necessari per implementare i comandi VTL verso SQL
 * I metodi restituiscono specifiche implementazioni di un SqlObject. Un SqlObject è loggetto utilizzato dalla libreria
 * SqlBuilder per rappresentare gli elementi SQL
 * La classe offre dei metodi specifici per MySQL
 */
@Service
public class MySqlObjectUtilityService extends CommonSqlObjectUtilityService {
    /**
     * il metodo prende in ingresso l'operatore di funzione generico e restituisce quello specifico del SQL
     *
     * @param operatorValue
     * @return
     */
    @Override
    public String getSqLOperator(String operatorValue) {
        return substituteOperator.getOrDefault(operatorValue, operatorValue);
    }

    /**
     * Crea un SqlObject che rappresenta una funzione analitica
     *
     * @param sqlObject
     * @param sqlDataset
     * @param vtlAnalyticFunctionExpression
     * @param operator
     * @return
     */
    @Override
    public SqlObject applyAnalyticFunctionToSqlObject(SqlObject sqlObject, SqlDataset sqlDataset, VtlAnalyticFunctionExpression vtlAnalyticFunctionExpression, Operator operator) {
        FunctionCall fc = FunctionCall.customFunction(getSqLOperator(operator.getValue()));

        if (!operator.getValue().equals(Operator.RANK.getValue())) {
            fc.addCustomParams(sqlObject);
        }

        if (operator.getValue().equals(Operator.LAG.getValue()) || operator.getValue().equals(Operator.LEAD.getValue())
                && vtlAnalyticFunctionExpression.getOffset() != null) {
            fc.addNumericValueParam(vtlAnalyticFunctionExpression.getOffset());

            if (vtlAnalyticFunctionExpression.getDefaultValue() != null) {
                parameterQueryUtilityService.addParameter(fc, vtlAnalyticFunctionExpression.getDefaultValue());
            }

        }

        WindowDefinitionClause wdc = new WindowDefinitionClause();

        if (vtlAnalyticFunctionExpression.getVtlPartitionBy() != null && !vtlAnalyticFunctionExpression.getVtlPartitionBy().getVtlComponentIds().isEmpty()) {
            for (VtlComponentId vtlComponentId : vtlAnalyticFunctionExpression.getVtlPartitionBy().getVtlComponentIds()) {
                wdc.addPartitionColumns(vtlComponentId.getSqlResult().getSqlComponent().getResult());
            }
        }

        if (vtlAnalyticFunctionExpression.getVltOrderBy() != null && !vtlAnalyticFunctionExpression.getVltOrderBy().getVtlOrderByItems().isEmpty()) {
            for (VtlOrderByItem vtlOrderByItem : vtlAnalyticFunctionExpression.getVltOrderBy().getVtlOrderByItems()) {
                SqlComponent sqlComponent = vtlOrderByItem.getVtlComponentId().getSqlResult().getSqlComponent();

                if (vtlOrderByItem.getOrderDirection() == null || vtlOrderByItem.getOrderDirection().equalsIgnoreCase(OrderObject.Dir.ASCENDING.toString().trim())) {
                    wdc.addOrdering(sqlComponent.getResult(), OrderObject.Dir.ASCENDING);
                } else {
                    wdc.addOrdering(sqlComponent.getResult(), OrderObject.Dir.DESCENDING);
                }
            }
        }

        //If the whole windowClause is omitted then the default is data points (rows) between unbounded preceding and current data point (row)
        //except for LAG, LEAD, RANK and RATIO_TO_REPORT
        if (vtlAnalyticFunctionExpression.getVtlWindowing() == null) {
            if (!operator.getValue().equals(Operator.LAG.getValue()) && !operator.getValue().equals(Operator.LEAD.getValue())
                    && !operator.getValue().equals(Operator.RANK.getValue()) && !operator.getValue().equals(Operator.RATIO_TO_REPORT.getValue())) {
                wdc.setFrameBetween(
                        WindowDefinitionClause.FrameUnits.ROWS,
                        FrameBound.UNBOUNDED_PRECEDING,
                        FrameBound.CURRENT_ROW
                );
            }
        } else {
            wdc.setFrameBetween(
                    vtlAnalyticFunctionExpression.getVtlWindowing().getVtlWindowingType().equals(VtlWindowingType.RANGE) ? WindowDefinitionClause.FrameUnits.RANGE : WindowDefinitionClause.FrameUnits.ROWS,
                    getFrameBound(vtlAnalyticFunctionExpression.getVtlWindowing().getVtlWindowingLimitFrom()),
                    getFrameBound(vtlAnalyticFunctionExpression.getVtlWindowing().getVtlWindowingLimitTo())
            );
        }


        fc.setWindow(wdc);

        if (operator.getValue().equals(Operator.RATIO_TO_REPORT.getValue())) {
            ComboExpression ce = ComboExpression.divide();
            ce.addExpression(sqlObject).addExpression(fc).setDisableParens(true);
            return ce;
        }

        return fc;

    }

    /**
     * Costruisce una from che contiene una nuova select invece che un nome tabella
     *
     * @param selectQuery
     */
    @Override
    public void addCustomFrom(SelectQuery selectQuery) {
        selectQuery.addCustomFromTable(ConstantUtility.DUMMY_TABLE);
    }

    /**
     * Crea un sql object che contiene una fillTime.
     *
     * @param inTableName
     * @param timeIdentifier
     * @param identifierList
     * @param operator
     * @return
     */
    @Override
    public SqlObject createExecuteFillTimeSeries(String inTableName, String timeIdentifier, List<String> identifierList, Operator operator) {
        String limitsMethod = "ALL";
        if (operator.getValue().equals(Operator.FILL_TIME_SERIES_SINGLE.getValue())) {
            limitsMethod = "SINGLE";
        }

        CustomSql executeProcedure = new CustomSql("CALL " + getSqLOperator(operator.getValue()) + "('" + inTableName + "','" + identifierList.stream().collect(Collectors.joining(", ")) + "','" + timeIdentifier + "','" + limitsMethod + "')");

        return executeProcedure;
    }

    /**
     * Genera una select di pivot per MySQL
     *
     * @param sqlComponent
     * @param vtlPivotClauseExpression
     * @return
     */
    @Override
    public SqlObject buildComponentPivot(SqlComponent sqlComponent, VtlPivotClauseExpression vtlPivotClauseExpression) {
        SqlObject result = new CustomSql("");
        if (sqlComponent.getVtlComponent().getVtlComponentRole().toString().equals("IDENTIFIER")) {
            result = sqlComponent.getResult();
        } else if (sqlComponent.getVtlComponent().getVtlComponentRole().toString().equals("MEASURE")) {
            for (VtlConstantExpression vtlConstantExpression : vtlPivotClauseExpression.getConstantExpressions()) {
//aggiunto start
                String aliasComp;
                try {
                    Integer.parseInt(vtlConstantExpression.getVtlConstant().getValue().toString());
                    aliasComp = vtlPivotClauseExpression.getIdentifier().getComponentName() + "_" + vtlConstantExpression.getVtlConstant().getValue();
                } catch (Exception e) {
                    aliasComp = vtlConstantExpression.getVtlConstant().getValue().toString();
                }

//aggiunto fine
                if(sqlComponent.getAliasName().equals(aliasComp)){
                    FunctionCall cf  = FunctionCall.sum();
                    cf.setDisableParens(true);
                    BinaryCondition bc = new BinaryCondition(Operator.EQUAL_TO.getValue(),
                            new CustomSql(vtlPivotClauseExpression.getIdentifier().getComponentName()) ,
                            new CustomSql(getConstantSql(vtlConstantExpression.getVtlConstant())));
                    SqlObject caseCostant = createCaseStatement(bc, new CustomSql(vtlPivotClauseExpression.getMeasure().getComponentName()) , new CustomSql("NULL"));
                    cf.addCustomParams(caseCostant);
                    result = new CustomSql(cf);
                }
            }

        }
        return result;
    }

    //getSqLOperator valid for MySql
    private static final Map<String, String> substituteOperator;

    static {
        Map<String, String> map = new HashMap<>();
        map.put("AND", "vtl_and_bool");
        map.put("OR", "vtl_or_bool");
        map.put("XOR", "vtl_xor_bool");
        map.put("NOT", "vtl_not_bool");
        map.put("timeshift", "vtl_timeshift");
        map.put("period_indicator", "vtl_period_indicator");
        map.put("fill_time_series_single", "vtl_fill_time_series");
        map.put("fill_time_series_all", "vtl_fill_time_series");
        String vtl_time_agg = "vtl_time_agg";
        map.put("time_agg", vtl_time_agg);
        map.put("time_agg_first", vtl_time_agg);
        map.put("time_agg_last", vtl_time_agg);
        map.put("cast_to_int", "vtl_cast_to_int");
        map.put("cast_to_number", "vtl_cast_to_number");
        map.put("cast_to_boolean", "vtl_cast_to_boolean");
        map.put("cast_to_time", "vtl_cast_to_time");
        map.put("cast_to_date", "vtl_cast_to_date");
        map.put("cast_to_timeperiod", "vtl_cast_to_timeperiod");
        map.put("cast_to_string", "vtl_cast_to_string");
        map.put("cast_to_duration", "vtl_cast_to_duration");

        map.put("match_characters", "REGEXP_LIKE");
        map.put("trunc", "truncate");
        map.put("ratio_to_report", "sum");
        substituteOperator = Collections.unmodifiableMap(map);
    }

    /**
     * @param domainValue
     * @return
     */
    @Override
    public SqlObject createCastNull(String domainValue) {
        SqlObject castNull;

        if (domainValue.equals(VtlType.STRING.getDomainValue())) {
            castNull = new CustomSql("CAST(NULL AS CHAR(100))");
        } else if (domainValue.equals(VtlType.NUMBER.getDomainValue())) {
            castNull = new CustomSql("CAST(NULL AS SIGNED)");
        } else if (domainValue.equals(VtlType.INTEGER.getDomainValue())) {
            castNull = new CustomSql("CAST(NULL AS SIGNED)");
        } else {
            castNull = new CustomSql("CAST(NULL AS CHAR(100))");
        }

        return castNull;
    }
}
