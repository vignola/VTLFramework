package com.capgemini.istat.vtlcompiler.vtlsqlbuilder.service.utility.sqlserver;

import com.capgemini.istat.vtlcompiler.vtlcommon.model.dataset.VtlComponentRole;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.dataset.VtlType;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.operator.Operator;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.translation.SqlComponent;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.translation.SqlDataset;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.constant.VtlConstantExpression;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.dataset.VtlComponentId;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.VtlExpression;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.clause.analytic.VtlAnalyticFunctionExpression;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.clause.analytic.VtlOrderByItem;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.clause.analytic.VtlWindowingType;
import com.capgemini.istat.vtlcompiler.vtlsqlbuilder.service.utility.CommonSqlObjectUtilityService;
import com.healthmarketscience.sqlbuilder.*;
import com.healthmarketscience.sqlbuilder.WindowDefinitionClause.FrameBound;
import com.healthmarketscience.sqlbuilder.custom.HookType;
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
 * La classe offre dei metodi specifici per SQL Server
 */
@Service
public class SqlServerObjectUtilityService extends CommonSqlObjectUtilityService {
    /**
     * il metodo prende in ingresso l'operatore di funzione generico e restituisce quello specifico del SQL
     * @param operatorValue
     * @return
     */
    @Override
    public String getSqLOperator(String operatorValue) {
        return substituteOperator.getOrDefault(operatorValue, operatorValue);
    }

    /**
     * Applica una funzione unaria a un SQLObject. In base all'operator viene applicata la funzione (o il costrutto) specifica SQL
     * @param sqlObject
     * @param parameter
     * @param optionalParameter
     * @param operator
     * @return
     */
    @Override
    public SqlObject applyUnaryFunctionToSqlObject(SqlObject sqlObject, VtlExpression parameter,
                                                   List<VtlExpression> optionalParameter, Operator operator) {
        if (operator.getValue().equals(Operator.IS_NULL.getValue())) {
            UnaryCondition uc = new UnaryCondition(UnaryCondition.Op.IS_NULL, sqlObject);
            uc.setDisableParens(true);
            CaseStatement cs = new CaseStatement();
            cs.addWhen(uc, applicationDefaultValueTrue);
            cs.addElse(applicationDefaultValueFalse);
            return cs;
        }

        if (operator.getValue().equals(Operator.MATCH_CHARACTERS.getValue())) {

            String sqlServerMacthCharactersOp = getSqLOperator(operator.getValue());
            String paramConvert = generateRegexAlphaDigit(parameter.getCommand());
            sqlServerMacthCharactersOp = sqlServerMacthCharactersOp + "("+sqlObject.toString()+","+ new CustomSql("'"+paramConvert+"'")+")";

            CustomSql customMacthCharacters = new CustomSql(sqlServerMacthCharactersOp +" <> 'NULL'");
            CaseStatement cs = new CaseStatement();
            cs.addWhen(customMacthCharacters, applicationDefaultValueTrue);
            cs.addElse(applicationDefaultValueFalse);
            return cs;
        }

        if (operator.getValue().equals(Operator.TIME_AGG.getValue()) || operator.getValue().equals(Operator.TIME_AGG_FIRST.getValue()) || operator.getValue().equals(Operator.TIME_AGG_LAST.getValue()) || operator.getValue().equals(Operator.TIME_AGG_PERIOD.getValue())) {
            FunctionCall fcCoalesce = FunctionCall.customFunction(getSqLOperator(Operator.NVL.getValue()))
                    .addCustomParams(sqlObject).addCustomParams("NULL");

            FunctionCall fcTimeAgg = FunctionCall.customFunction(getSqLOperator(operator.getValue()))
                    .addCustomParams(fcCoalesce);
            parameterQueryUtilityService.addParameter(fcTimeAgg, parameter);

            if(optionalParameter == null) {
                fcTimeAgg.addCustomParams(SqlObject.NULL_VALUE);
            } else {
                parameterQueryUtilityService.addOptionalParameters(fcTimeAgg, optionalParameter);
            }

            if (operator.getValue().equals(Operator.TIME_AGG_FIRST.getValue())) {
                fcTimeAgg.addCustomParams("first");
            } else if (operator.getValue().equals(Operator.TIME_AGG_LAST.getValue())) {
                fcTimeAgg.addCustomParams("last");
            } else {
                fcTimeAgg.addCustomParams(SqlObject.NULL_VALUE);
            }

            return fcTimeAgg;
        }

        FunctionCall fc = FunctionCall.customFunction(getSqLOperator(operator.getValue()));
        Boolean toSwitch = operator.getValue().equals(Operator.INSTR.getValue())
                || operator.getValue().equals(Operator.LOG.getValue());
        if (toSwitch) {
            parameterQueryUtilityService.addParameter(fc, parameter);
            fc.addCustomParams(sqlObject);
        } else {
            fc.addCustomParams(sqlObject);
            parameterQueryUtilityService.addParameter(fc, parameter);
        }

        parameterQueryUtilityService.addOptionalParameters(fc, optionalParameter);

        // AGGIUNTA TERZO PARAMETRO
        if (operator.getValue().equals(Operator.SUBSTR.getValue()) && optionalParameter.size() == 1) {
            FunctionCall fc2 = FunctionCall.customFunction(getSqLOperator(Operator.LENGTH.getValue()));
            fc2.addCustomParams(sqlObject);
            fc.addCustomParams(fc2);
        }

        return fc;
    }

    /**
     * Applica una funzione su due componenti di una query
     *
     * @param sqlComponentLeft
     * @param sqlComponentRight
     * @param operator
     * @return
     */
    @Override
    public SqlObject applyOperatorFunctionToSqlObjects(SqlComponent sqlComponentLeft, SqlComponent sqlComponentRight,
                                                       Operator operator) {
        SqlObject sqlObjectLeft = sqlComponentLeft.getResult();
        SqlObject sqlObjectRight = sqlComponentRight.getResult();

        if (operator.isComboExpression()) {
            ComboExpression ce = new ComboExpression(operator.getValue());
            ce.addExpression(sqlObjectLeft).addExpression(sqlObjectRight).setDisableParens(true);
            return ce;
        }

        if (operator.isComparisonBinary()) {
            BinaryCondition bc = new BinaryCondition(operator.getValue(), sqlObjectLeft, sqlObjectRight);
            CaseStatement cs = new CaseStatement();
            cs.addWhen(bc, applicationDefaultValueTrue);
            cs.addElse(applicationDefaultValueFalse);
            return cs;
        }

        if (operator.getValue().equals(Operator.MOD.getValue())) {
            ComboExpression ce = new ComboExpression(getSqLOperator(operator.getValue()));
            ce.addExpression(sqlObjectLeft).addExpression(sqlObjectRight).setDisableParens(false);
            return ce;
        }

        FunctionCall fc = FunctionCall.customFunction(getSqLOperator(operator.getValue()))
                .addCustomParams(sqlObjectLeft).addCustomParams(sqlObjectRight);
        return fc;
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
            if (operator.getValue().equals(Operator.RATIO_TO_REPORT.getValue())) {
                FunctionCall fc2 = FunctionCall.customFunction(Operator.CAST.getValue());
                FunctionCall fc3 = FunctionCall.customFunction("");
                fc3.setDisableParens(true);
                fc3.addCustomParams(sqlObject);
                fc3.addAlias("FLOAT");
                fc2.addCustomParams(fc3);
                fc.addCustomParams(fc2);
            } else {
                fc.addCustomParams(sqlObject);
            }
        }

        if (operator.getValue().equals(Operator.LAG.getValue()) || operator.getValue().equals(Operator.LEAD.getValue()) &&
                vtlAnalyticFunctionExpression.getOffset() != null) {
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
     * Crea un oggetto che rappresenta una tabella all'interno della query
     *
     * @param tableName
     * @param sqlObject
     * @return
     */
    @Override
    public SqlObject createTableQuery(String tableName, SqlObject sqlObject) {
        String alias = dbTableUtilityService.getDbSpec().getNextAlias();
        return new CreateTableQueryInto(tableName, alias).setAsCustomSelect(sqlObject);
    }

    @Override
    public void addCustomFrom(SelectQuery selectQuery) {
        //non ce n'è bisogno
    }

    /**
     * Genera una query di unpivot per sqlServer
     *
     * @param identifier
     * @param measure
     * @param sqlDatasetFrom
     * @param sqlDatasetUnpivot
     * @return
     */
    @Override
    public SelectQuery createSelectForUnpivot(VtlComponentId identifier, VtlComponentId measure, SqlDataset sqlDatasetFrom, SqlDataset sqlDatasetUnpivot) {
        SelectQuery selectQuery = new SelectQuery();

        sqlDatasetUnpivot.setUpAlias(dbTableUtilityService.getDbSpec().getNextAlias(), sqlDatasetUnpivot.getSqlTables().get(0).getVtlDataset());

        for (SqlComponent sqlComponent : sqlDatasetUnpivot.getComponentList()) {
            selectQuery.addCustomColumns(new CustomSql(sqlComponent.getAliasName()));
        }

        //The Unpivot Query has been created without alias table
        selectQuery.addCustomFromTable(sqlDatasetFrom.getSqlTables().get(0).getVtlDataset().getName());

        String comma = "";
        String unpivotCommand = " UNPIVOT (" + measure.getComponentName() + " for " + identifier.getComponentName() + " in (";
        for (SqlComponent sqlComponent : sqlDatasetFrom.getComponentList()) {
            if (sqlComponent.getVtlComponent().getVtlComponentRole() == VtlComponentRole.MEASURE) {
                unpivotCommand += comma + sqlComponent.getAliasName();
                if (comma.isEmpty()) comma = ",";
            }
        }
        String aliasUnpivotTable = dbTableUtilityService.getDbSpec().getNextAlias();
        unpivotCommand += ") )" + " " + aliasUnpivotTable;
        selectQuery.addCustomization(SelectQuery.Hook.WHERE, HookType.BEFORE, unpivotCommand);

        return selectQuery;
    }

    /**
     * Genera una query di pivot per sqlServer
     *
     * @param identifier
     * @param measure
     * @param constantExpressions
     * @param sqlDatasetFrom
     * @param sqlDatasetPivot
     * @return
     */
    @Override
    public SelectQuery createSelectForPivot(VtlComponentId identifier, VtlComponentId measure, List<VtlConstantExpression> constantExpressions, SqlDataset sqlDatasetFrom, SqlDataset sqlDatasetPivot) {
        SelectQuery sqPivot = new SelectQuery();
        SelectQuery sqFrom = new SelectQuery();

        String aliasFromTable = dbTableUtilityService.getDbSpec().getNextAlias();
        String aliasPivotTable = dbTableUtilityService.getDbSpec().getNextAlias();

        sqlDatasetFrom.setUpAlias(aliasFromTable, sqlDatasetFrom.getSqlTables().get(0).getVtlDataset());

        for (SqlComponent sqlComponent : sqlDatasetFrom.getComponentList()) {
            if (sqlComponent.getVtlComponent().getVtlComponentRole().name().equalsIgnoreCase(VtlComponentRole.IDENTIFIER.name()) ||
                    sqlComponent.getVtlComponent().getName().equalsIgnoreCase(measure.getComponentName())) {
                sqFrom.addAliasedColumn(sqlComponent.getResult(), sqlComponent.getAliasName());
            }
        }

        sqFrom.addCustomFromTable(sqlDatasetFrom.getSqlTables().get(0).getVtlDataset().getName() + " " + sqlDatasetFrom.getSqlTables().get(0).getAliasName());

        String customFrom = "(" + sqFrom.toString() + ") AS " + aliasFromTable;


        sqlDatasetPivot.setUpAlias(aliasPivotTable, sqlDatasetPivot.getSqlTables().get(0).getVtlDataset());

        int index = -1;
        for (SqlComponent sqlComponent : sqlDatasetPivot.getComponentList()) {
            if (sqlComponent.getVtlComponent().getVtlComponentRole() == VtlComponentRole.MEASURE) {
                index++;
                sqPivot.addCustomColumns(new CustomSql("[" + constantExpressions.get(index).getVtlConstant().getValue()+ "] AS " + sqlComponent.getAliasName()));
            } else {
                sqPivot.addCustomColumns(new CustomSql(sqlComponent.getAliasName()));
            }
        }

        sqPivot.addCustomFromTable(customFrom);

        String comma = "";

        String pivotCommand = " PIVOT ( ";
        pivotCommand += "SUM( " + measure.getComponentName() + " ) ";
        pivotCommand += "FOR " + identifier.getComponentName() + " IN (";
        for (VtlConstantExpression vtlConstantExpression : constantExpressions) {
            pivotCommand += comma + "[" + vtlConstantExpression.getVtlConstant().getValue() + "]";
            if (comma.isEmpty()) comma = ",";
        }
        pivotCommand += ") ) AS " + aliasPivotTable;

        sqPivot.addCustomization(SelectQuery.Hook.WHERE, HookType.BEFORE, pivotCommand);

        return sqPivot;
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

        CustomSql executeProcedure = new CustomSql("EXECUTE " + getSqLOperator(operator.getValue()) + " @p_input_dataset = '" + inTableName + "'," + " @p_id_list = '" + identifierList.stream().collect(Collectors.joining(", ")) + "'," + " @p_id_time = '" + timeIdentifier + "'," + " @p_limits_method = '" + limitsMethod + "'");

        return executeProcedure;
    }

    // getSqLOperator valid for SqlServer
    private static final Map<String, String> substituteOperator;

    static {
        Map<String, String> map = new HashMap<>();
        map.put("AND", "dbo.vtl_and_bool");
        map.put("OR", "dbo.vtl_or_bool");
        map.put("XOR", "dbo.vtl_xor_bool");
        map.put("NOT", "dbo.vtl_not_bool");
        map.put("timeshift", "dbo.vtl_timeshift");
        map.put("period_indicator", "dbo.vtl_period_indicator");
        map.put("fill_time_series_single", "dbo.vtl_fill_time_series");
        map.put("fill_time_series_all", "dbo.vtl_fill_time_series");
        String value = "dbo.vtl_time_agg";
        map.put("time_agg", value);
        map.put("time_agg_first", value);
        map.put("time_agg_last", value);
        map.put("cast_to_int", "dbo.vtl_cast_to_int");
        map.put("cast_to_number", "dbo.vtl_cast_to_number");
        map.put("cast_to_boolean", "dbo.vtl_cast_to_boolean");
        map.put("cast_to_time", "dbo.vtl_cast_to_time");
        map.put("cast_to_date", "dbo.vtl_cast_to_date");
        map.put("cast_to_timeperiod", "dbo.vtl_cast_to_timeperiod");
        map.put("cast_to_string", "dbo.vtl_cast_to_string");
        map.put("cast_to_duration", "dbo.vtl_cast_to_duration");

        map.put("mod", "%");
        map.put("ln", "log");
        map.put("ceil", "ceiling");
        map.put("stddev_pop", "stdevp");
        map.put("stddev_samp", "stdev");
        map.put("var_pop", "varp");
        map.put("var_samp", "var");
        map.put("trunc", "round");
        map.put("substr", "substring");
        map.put("instr", "charindex");
        map.put("length", "len");
        map.put("match_characters", "dbo.RegexMatch");
        map.put("ratio_to_report", "sum");

        substituteOperator = Collections.unmodifiableMap(map);
    }

    /**
     * Crea un cast generico negli scenari in cui non si conosce il tipo e non è possibile costruire una insert select
     *
     * @param domainValue
     * @return
     */
    @Override
    public SqlObject createCastNull(String domainValue) {
        SqlObject castNull;

        if (domainValue.equals(VtlType.STRING.getDomainValue())) {
            castNull = new CustomSql("CONVERT(varchar, NULL )");
        } else if (domainValue.equals(VtlType.NUMBER.getDomainValue())) {
            castNull = new CustomSql("CONVERT(NUMERIC, NULL )");
        } else if (domainValue.equals(VtlType.INTEGER.getDomainValue())) {
            castNull = new CustomSql("CONVERT(INT, NULL )");
        } else {
            castNull = new CustomSql("CONVERT(varchar, NULL )");
        }

        return castNull;
    }

    private String generateRegexAlphaDigit(String command){
        String result = "";
        String alpha = "[:alpha:]";
        String digit = "[:digit:]";
        if (command.contains(alpha) || command.contains(digit)) {
            if (command.contains(alpha)) {
                String alphaResult = "";
                String alphaValueDefault = "[A-Za-z]";
                int posixAlpha = command.indexOf(alpha, 0);
                String afterAlpha = command.substring(posixAlpha+9, command.length());

                if (afterAlpha.startsWith("{")) {
                    int posixStartAlphaParam = afterAlpha.indexOf('{');
                    int posixEndAlphaParam = afterAlpha.indexOf('}');
                    //valutare se mettere controllo posixStartAlphaParam < posixEndAlphaParam
                    String alphaParam = afterAlpha.substring(posixStartAlphaParam+1, posixEndAlphaParam);

                    try {
                        int alphaParamValue = Integer.parseInt(alphaParam);
                        for (int i = 0; i < alphaParamValue; i++) {
                            alphaResult += alphaValueDefault;
                        }
                        result += alphaResult;
                    } catch (Exception e) {
                        result += alphaResult;
                    }

                }else{
                    result += alphaValueDefault;
                }
            }
            if (command.contains(digit)) {
                String digitResult = "";
                String digitValueDefault = "[0-9]";
                int posixDigit = command.indexOf(digit, 0);
                String afterDigit = command.substring(posixDigit+9, command.length());

                if (afterDigit.startsWith("{")) {
                    int posixStartDigitParam = afterDigit.indexOf('{');
                    int posixEndDigitParam = afterDigit.indexOf('}');
                    String digitParam = afterDigit.substring(posixStartDigitParam+1, posixEndDigitParam);

                    try {
                        int digitParamValue = Integer.parseInt(digitParam);
                        for (int i = 0; i < digitParamValue; i++) {
                            digitResult += digitValueDefault;
                        }
                        result += digitResult;
                    } catch (Exception e) {
                        result += digitResult;
                    }

                }else{
                    result += digitValueDefault;
                }
            }
        }else{
            result=command;
        }

        return result;
    }
}
