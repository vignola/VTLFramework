package com.capgemini.istat.vtlcompiler.vtlcommon.model.operator;

import com.capgemini.istat.vtlcompiler.vtlcommon.model.dataset.VtlType;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.semantic.ValidationCheck;

import java.io.Serializable;
import java.util.*;

import static com.capgemini.istat.vtlcompiler.vtlcommon.model.semantic.ValidationCheck.HAS_ONE_MEASURE_OF_SAME_TYPE;

/**
 * La classe rappresenta tutti gli operatori presenti nel linguaggio VTL e offre, per ciascun operatore, tutte
 * le validazioni che devono essere effettuate per eseguire la trasformazione.<p>
 * La mappa di validazione è formata da una chiave che rappresenta l'interazione (scalare con scalare, dataset con dataset, ecc).
 * e la lista di {@link ValidationCheck} che devono essere effettuati
 * <p>
 * Il flag substituteComponent si occupa di settare se a seguito dell'operazione viene generato un nuovo componente
 * con un il nome di default del tipo risultante che sostituisce il componente o i componenti precedenti.
 * </p>
 *
 * @see Interaction
 * @see ValidationCheck
 */
public enum Operator implements Serializable {

    //Generic Operators
    DEFINE("define", VtlType.NONE, null, null),
    COMPONENT("component", VtlType.NONE, null, getValidationComponent()),
    DATASET("dataset", VtlType.NONE, null, getDatasetValidation()),
    PERSIST("<-", VtlType.NONE, null, getPersistValidation()),
    ASSIGN(":=", VtlType.NONE, null, getEmptyHashMap()),
    MEMBERSHIP("#", VtlType.NONE, null, getMembershipValidation()),
    MEMBERSHIP_IN_CLAUSE("#", VtlType.NONE, null, getMembershipInClause()),
    OPTIONAL("_", VtlType.NONE, null, null),
    //STRING OPERATORS
    CONCATENATION("concat", VtlType.STRING, null, getBinaryStringValidation()),
    TRIM("trim", VtlType.STRING, null, getCommonStringUnaryOperatorValidation()),
    RTRIM("rtrim", VtlType.STRING, null, getCommonStringUnaryOperatorValidation()),
    LTRIM("ltrim", VtlType.STRING, null, getCommonStringUnaryOperatorValidation()),
    UPPER("upper", VtlType.STRING, null, getCommonStringUnaryOperatorValidation()),
    LOWER("lower", VtlType.STRING, null, getCommonStringUnaryOperatorValidation()),
    SUBSTR("substr", VtlType.STRING, null, getCommonStringUnaryOperatorValidation()),
    SUBSTR_OPTIONAL_PARAMETER("substr_param", VtlType.NONE, null, getStringParameterValidation()),
    REPLACE("replace", VtlType.STRING, null, getCommonStringUnaryOperatorValidation()),
    REPLACE_OPTIONAL_PARAMETER("replace", VtlType.STRING, null, getStringOperatorValidation()),
    INSTR("instr", VtlType.INTEGER, true, getStringOperatorValidation()),
    INSTR_PARAMETER("instr_parameter", VtlType.STRING, null, getStringOperatorValidation()),
    INSTR_OPTIONAL_PARAMETER("substr_optional_param", VtlType.NONE, null, getStringParameterValidation()),
    DURATION_PARAMETER("duration_parameter", VtlType.DURATION, null, getEmptyHashMap()),
    DURATION_OPTIONAL_PARAMETER("duration_optional_param", VtlType.DURATION, null, getEmptyHashMap()),
    LENGTH("length", VtlType.INTEGER, Boolean.TRUE, getStringOperatorValidation()),
    //NUMERIC OPERATOR
    ADDITION("+", VtlType.NONE, null, getCommonNumericBinaryOperatorValidation()),
    SUBSTRACTION("-", VtlType.NONE, null, getCommonNumericBinaryOperatorValidation()),
    MULTIPLICATION("*", VtlType.NONE, null, getCommonNumericBinaryOperatorValidation()),
    DIVISION("/", VtlType.NUMBER, null, getCommonNumericBinaryOperatorValidation()),
    UNARY_NEGATIVE("-", VtlType.NONE, false, getCommonNumericUnaryOperatorValidation()),
    UNARY_POSITIVE("+", VtlType.NONE, false, getCommonNumericUnaryOperatorValidation()),
    MOD("mod", VtlType.NUMBER, null, getCommonNumericBinaryOperatorValidation()),
    ROUND("round", VtlType.NUMBER, null, getMathValidation()),
    ROUND_OPTIONAL_PARAMETER("round_optional", VtlType.NONE, null, getMathParameterValidation()),
    TRUNC("trunc", VtlType.NUMBER, null, getMathValidation()),
    TRUNC_OPTIONAL_PARAMETER("trunc_optional", VtlType.NONE, null, getMathParameterValidation()),
    CEIL("ceil", VtlType.INTEGER, null, getCommonNumericUnaryOperatorValidation()),
    FLOOR("floor", VtlType.INTEGER, null, getCommonNumericUnaryOperatorValidation()),
    ABS("abs", VtlType.NONE, null, getCommonNumericUnaryOperatorValidation()),
    EXP("exp", VtlType.NUMBER, null, getCommonNumericUnaryOperatorValidation()),
    LN("ln", VtlType.NUMBER, null, getCommonNumericUnaryOperatorValidation()),
    POWER("power", VtlType.NUMBER, null, getCommonNumericBinaryOperatorValidation()),
    POWER_PARAM("power", VtlType.NONE, null, getMathParameterValidation()),
    LOG("log", VtlType.NUMBER, null, getCommonNumericUnaryOperatorValidation()),
    LOG_PARAM("log", VtlType.NONE, null, getMathParameterValidation()),
    SQRT("sqrt", VtlType.NUMBER, null, getCommonNumericUnaryOperatorValidation()),
    // COMPARISON OPERATORS
    EQUAL_TO("=", VtlType.BOOLEAN, Boolean.TRUE, getCommonComparisonExpressionValidation()),
    NOT_EQUAL_TO("<>", VtlType.BOOLEAN, Boolean.TRUE, getCommonComparisonExpressionValidation()),
    GREATER_THAN(">", VtlType.BOOLEAN, Boolean.TRUE, getCommonComparisonExpressionValidation()),
    GREATER_THAN_EQUALS(">=", VtlType.BOOLEAN, Boolean.TRUE, getCommonComparisonExpressionValidation()),
    LESS_THAN("<", VtlType.BOOLEAN, Boolean.TRUE, getCommonComparisonExpressionValidation()),
    LESS_THAN_EQUALS("<=", VtlType.BOOLEAN, Boolean.TRUE, getCommonComparisonExpressionValidation()),
    BETWEEN("between", VtlType.BOOLEAN, Boolean.TRUE, getBetweenValidation()),
    BETWEEN_PARAMETER("-", VtlType.NONE, Boolean.FALSE, getBetweenParameterValidation()),
    IN("in", VtlType.BOOLEAN, Boolean.TRUE, getCommonInOperatorValidation()),
    NOT_IN("not in", VtlType.BOOLEAN, Boolean.TRUE, getCommonInOperatorValidation()),
    MATCH_CHARACTERS("match_characters", VtlType.BOOLEAN, Boolean.TRUE, getStringOperatorValidation()),
    MATCH_CHARACTERS_PARAM("match_characters", VtlType.STRING, Boolean.FALSE, getStringOperatorValidation()),
    IS_NULL("is_null", VtlType.BOOLEAN, Boolean.TRUE, getIsNullValidation()),
    EXIST_IN("-", VtlType.BOOLEAN, Boolean.TRUE, getExistInValidation()),
    /// Boolean Operators
    AND("AND", VtlType.BOOLEAN, Boolean.FALSE, getCommonBooleanExpressionValidation()),
    OR("OR", VtlType.BOOLEAN, Boolean.FALSE, getCommonBooleanExpressionValidation()),
    XOR("XOR", VtlType.BOOLEAN, Boolean.FALSE, getCommonBooleanExpressionValidation()),
    NOT("NOT", VtlType.BOOLEAN, Boolean.FALSE, getNotValidation()),
    //SET OPERATOR
    UNION("union", VtlType.NONE, Boolean.FALSE, getUnionValidation()),
    INTERSECT("intersect", VtlType.NONE, Boolean.FALSE, getUnionValidation()),
    SETDIFF("set_diff", VtlType.NONE, Boolean.FALSE, getUnionValidation()),
    SYMDIFF("sym_diff", VtlType.NONE, Boolean.FALSE, getUnionValidation()),
    //AGGR FUNCTION
    SUM("sum", VtlType.NONE, Boolean.FALSE, getCommonAggrFunctionValidation()),
    COUNT("count", VtlType.INTEGER, Boolean.TRUE, getCountValidation()),
    MIN("min", VtlType.NONE, Boolean.FALSE, getCommonAggrFunctionValidation()),
    MAX("max", VtlType.NONE, Boolean.FALSE, getCommonAggrFunctionValidation()),
    MEDIAN("median", VtlType.NUMBER, Boolean.FALSE, getCommonAggrFunctionValidation()),
    AVG("avg", VtlType.NUMBER, Boolean.FALSE, getCommonAggrFunctionValidation()),
    STDDEV_POP("stddev_pop", VtlType.NUMBER, Boolean.FALSE, getCommonAggrFunctionValidation()),
    STDDEV_SAMP("stddev_samp", VtlType.NUMBER, Boolean.FALSE, getCommonAggrFunctionValidation()),
    VAR_POP("var_pop", VtlType.NUMBER, Boolean.FALSE, getCommonAggrFunctionValidation()),
    VAR_SAMP("var_samp", VtlType.NONE, Boolean.FALSE, getCommonAggrFunctionValidation()),
    FIRST_VALUE("first_value", VtlType.NONE, Boolean.FALSE, getEmptyHashMap()),
    LAST_VALUE("last_value", VtlType.NONE, Boolean.FALSE, getEmptyHashMap()),
    LAG("lag", VtlType.NONE, Boolean.FALSE, getEmptyHashMap()),
    LEAD("lead", VtlType.NONE, Boolean.FALSE, getEmptyHashMap()),
    RANK("rank", VtlType.INTEGER, Boolean.TRUE, getRankValidation()),
    RATIO_TO_REPORT("ratio_to_report", VtlType.NUMBER, Boolean.FALSE, getRatioValidation()),
    //GROUP Operator
    GROUP_BY("group by", VtlType.NONE, Boolean.FALSE, getGroupByValidation()),
    GROUP_EXCEPT("group except", VtlType.NONE, Boolean.FALSE, getGroupExceptValidation()),
    GROUP_ALL("group all", VtlType.NONE, Boolean.FALSE, getEmptyHashMap()),
    HAVING("rank", VtlType.NONE, Boolean.FALSE, getEmptyHashMap()),
    // CLAUSE OPERATORS
    FILTER("filter", VtlType.NONE, Boolean.FALSE, getEmptyHashMap()),
    CALC("calc", VtlType.NONE, Boolean.FALSE, getEmptyHashMap()),
    AGGR("aggr", VtlType.NONE, Boolean.FALSE, getEmptyHashMap()),
    KEEP("keep", VtlType.NONE, Boolean.FALSE, getKeepValidation()),
    DROP("drop", VtlType.NONE, Boolean.FALSE, getDropValidation()),
    RENAME("rename", VtlType.NONE, Boolean.FALSE, getRenameValidation()),
    PIVOT("pivot", VtlType.NONE, Boolean.FALSE, getEmptyHashMap()),
    UNPIVOT("unpivot", VtlType.NONE, Boolean.FALSE, getUnpivotValidation()),
    SUB("sub", VtlType.NONE, Boolean.FALSE, getSubValidation()),
    //TIME OPERATOR
    PERIOD_INDICATOR("period_indicator", VtlType.DURATION, Boolean.TRUE, getPeriodValidation()),
    FILL_TIME_SERIES_SINGLE("fill_time_series_single", VtlType.NONE, Boolean.FALSE, getFillTimeValidation()),
    FILL_TIME_SERIES_ALL("fill_time_series_all", VtlType.NONE, Boolean.FALSE, getFillTimeValidation()),
    FLOW_TO_STOCK("flow_to_stock", VtlType.NONE, Boolean.FALSE, getFillTimeValidation()),
    STOCK_TO_FLOW("stock_to_flow", VtlType.NONE, Boolean.FALSE, getFillTimeValidation()),
    TIMESHIFT("timeshift", VtlType.NONE, Boolean.FALSE, getFillTimeValidation()),
    TIMESHIFT_PARAM("timeshift_param", VtlType.NONE, Boolean.FALSE, getTimeshiftParamValidation()),
    TIME_AGG("time_agg", VtlType.NONE, Boolean.FALSE, getTimeAggValidation()),
    TIME_AGG_FIRST("time_agg_first", VtlType.NONE, Boolean.FALSE, getTimeAggValidation()),
    TIME_AGG_LAST("time_agg_last", VtlType.NONE, Boolean.FALSE, getTimeAggValidation()),
    TIME_AGG_PERIOD("time_agg_period", VtlType.NONE, Boolean.FALSE, getTimeAggPeriodValidation()),
    CURRENT_DATE("current_date", VtlType.DATE, Boolean.FALSE, getEmptyHashMap()),
    IF_THEN_ELSE("sub", VtlType.NONE, Boolean.FALSE, getIfValidation()),
    IF_THEN_ELSE_CONDITION("sub", VtlType.NONE, Boolean.FALSE, getIfCondtionValidation()),
    NVL("coalesce", VtlType.NONE, Boolean.FALSE, getNvlValidation()),
    //Join
    CROSS_JOIN("sub", VtlType.NONE, Boolean.FALSE, getEmptyHashMap()),
    FULL_JOIN("sub", VtlType.NONE, Boolean.FALSE, getEmptyHashMap()),
    INNER_JOIN("sub", VtlType.NONE, Boolean.FALSE, getEmptyHashMap()),
    LEFT_JOIN("sub", VtlType.NONE, Boolean.FALSE, getEmptyHashMap()),
    RIGHT_JOIN("sub", VtlType.NONE, Boolean.FALSE, getEmptyHashMap()),
    JOIN_CLAUSE("sub", VtlType.NONE, Boolean.FALSE, getEmptyHashMap()),
    JOIN_CLAUSE_ITEM("sub", VtlType.NONE, Boolean.FALSE, getEmptyHashMap()),
    JOIN_CLAUSE_USING("sub", VtlType.NONE, Boolean.FALSE, getEmptyHashMap()),
    JOIN_FILTER("sub", VtlType.NONE, Boolean.FALSE, getEmptyHashMap()),
    PARTITION_BY("sub", VtlType.NONE, Boolean.FALSE, getEmptyHashMap()),
    ORDER_BY("sub", VtlType.NONE, Boolean.FALSE, getEmptyHashMap()),
    WINDOWING("sub", VtlType.NONE, Boolean.FALSE, getEmptyHashMap()),
    CAST("cast", VtlType.NONE, Boolean.FALSE, getEmptyHashMap()),
    CAST_TO_INTEGER("cast_to_int", VtlType.NONE, Boolean.FALSE, getEmptyHashMap()),
    CAST_TO_NUMBER("cast_to_number", VtlType.NONE, Boolean.FALSE, getEmptyHashMap()),
    CAST_TO_BOOLEAN("cast_to_boolean", VtlType.NONE, Boolean.FALSE, getEmptyHashMap()),
    CAST_TO_TIME("cast_to_time", VtlType.NONE, Boolean.FALSE, getEmptyHashMap()),
    CAST_TO_DATE("cast_to_date", VtlType.NONE, Boolean.FALSE, getEmptyHashMap()),
    CAST_TO_TIME_PERIOD("cast_to_timeperiod", VtlType.NONE, Boolean.FALSE, getEmptyHashMap()),
    CAST_TO_STRING("cast_to_string", VtlType.NONE, Boolean.FALSE, getEmptyHashMap()),
    CAST_TO_DURATION("cast_to_duration", VtlType.NONE, Boolean.FALSE, getEmptyHashMap()),
    CHECK_HIERARCHY("CHECK_HIERARCHY", VtlType.NONE, Boolean.FALSE, getHierarchyValidation()),
    CHECK("CHECK", VtlType.NONE, Boolean.FALSE, getCheckValidation()),
    HIERARCHY("HIERARCHY", VtlType.NONE, Boolean.FALSE, getHierarchyValidation());


    private static Map<Interaction, List<ValidationCheck>> getCheckValidation() {
        EnumMap<Interaction, List<ValidationCheck>> validationMap = new EnumMap<>(Interaction.class);
        setValidationMap(validationMap, Interaction.SCALAR, ValidationCheck.NOT_ALLOWED);
        setValidationMap(validationMap, Interaction.COMPONENT, ValidationCheck.NOT_ALLOWED_COMPONENT);
        setValidationMap(validationMap, Interaction.DATASET, ValidationCheck.HAS_ONLY_ONE_MEASURE, ValidationCheck.HAS_ONLY_MEASURE_BOOLEAN);
        return validationMap;
    }

    private static Map<Interaction, List<ValidationCheck>> getNvlValidation() {
        EnumMap<Interaction, List<ValidationCheck>> validationMap = new EnumMap<>(Interaction.class);
        setValidationMap(validationMap, Interaction.SCALAR_TO_SCALAR, HAS_ONE_MEASURE_OF_SAME_TYPE);
        setValidationMap(validationMap, Interaction.SCALAR_TO_DATASET, HAS_ONE_MEASURE_OF_SAME_TYPE);
        setValidationMap(validationMap, Interaction.DATASET_TO_DATASET, ValidationCheck.HAS_SAME_STRUCTURE, ValidationCheck.HAS_ONE_MEASURE_OF_SAME_TYPE, ValidationCheck.HAS_ONLY_ONE_MEASURE);
        setValidationMap(validationMap, Interaction.SCALAR_TO_COMPONENT, ValidationCheck.IS_IDENTICAL_TYPE);
        setValidationMap(validationMap, Interaction.COMPONENT_TO_COMPONENT, ValidationCheck.IS_IDENTICAL_TYPE);
        return validationMap;
    }

    private static Map<Interaction, List<ValidationCheck>> getIfCondtionValidation() {
        EnumMap<Interaction, List<ValidationCheck>> validationMap = new EnumMap<>(Interaction.class);
        setValidationMap(validationMap, Interaction.SCALAR, ValidationCheck.HAS_ONLY_MEASURE_BOOLEAN);
        setValidationMap(validationMap, Interaction.COMPONENT, ValidationCheck.IS_BOOLEAN);
        setValidationMap(validationMap, Interaction.DATASET, ValidationCheck.HAS_ONLY_ONE_MEASURE, ValidationCheck.HAS_ONLY_MEASURE_BOOLEAN);
        return validationMap;
    }

    private static Map<Interaction, List<ValidationCheck>> getIfValidation() {
        EnumMap<Interaction, List<ValidationCheck>> validationMap = new EnumMap<>(Interaction.class);
        setValidationMap(validationMap, Interaction.SCALAR_TO_SCALAR, HAS_ONE_MEASURE_OF_SAME_TYPE, ValidationCheck.HAS_SAME_STRUCTURE);
        setValidationMap(validationMap, Interaction.SCALAR_TO_DATASET, ValidationCheck.NOT_ALLOWED_SCALAR_TO_DATASET);
        setValidationMap(validationMap, Interaction.DATASET_TO_DATASET, ValidationCheck.HAS_ONE_MEASURE_OF_SAME_TYPE, ValidationCheck.HAS_ONLY_ONE_MEASURE, ValidationCheck.HAS_SAME_STRUCTURE);
        setValidationMap(validationMap, Interaction.SCALAR_TO_COMPONENT, ValidationCheck.IS_IDENTICAL_TYPE);
        setValidationMap(validationMap, Interaction.COMPONENT_TO_COMPONENT, ValidationCheck.IS_IDENTICAL_TYPE);
        return validationMap;
    }

    private static Map<Interaction, List<ValidationCheck>> getTimeAggPeriodValidation() {
        EnumMap<Interaction, List<ValidationCheck>> validationMap = new EnumMap<>(Interaction.class);
        setValidationMap(validationMap, Interaction.SCALAR, ValidationCheck.IS_DURATION);
        setValidationMap(validationMap, Interaction.COMPONENT, ValidationCheck.IS_DURATION);
        setValidationMap(validationMap, Interaction.DATASET, ValidationCheck.NOT_ALLOWED);
        return validationMap;
    }

    private static Map<Interaction, List<ValidationCheck>> getTimeAggValidation() {
        EnumMap<Interaction, List<ValidationCheck>> validationMap = new EnumMap<>(Interaction.class);
        setValidationMap(validationMap, Interaction.SCALAR, ValidationCheck.IS_TIME);
        setValidationMap(validationMap, Interaction.COMPONENT, ValidationCheck.IS_TIME);
        setValidationMap(validationMap, Interaction.DATASET, ValidationCheck.HAS_AT_LEAST_ONE_IDENTIFIER, ValidationCheck.HAS_AT_LEAST_ONE_IDENTIFIER_TIME, ValidationCheck.HAS_ONLY_ONE_IDENTIFIER_TIME);
        return validationMap;
    }

    private static Map<Interaction, List<ValidationCheck>> getTimeshiftParamValidation() {
        EnumMap<Interaction, List<ValidationCheck>> validationMap = new EnumMap<>(Interaction.class);
        setValidationMap(validationMap, Interaction.SCALAR, ValidationCheck.HAS_ONLY_MEASURE_INTEGER);
        setValidationMap(validationMap, Interaction.COMPONENT, ValidationCheck.IS_INTEGER);
        return validationMap;
    }

    private static Map<Interaction, List<ValidationCheck>> getFillTimeValidation() {
        EnumMap<Interaction, List<ValidationCheck>> validationMap = new EnumMap<>(Interaction.class);
        setValidationMap(validationMap, Interaction.SCALAR, ValidationCheck.NOT_ALLOWED_SCALAR_TO_SCALAR);
        setValidationMap(validationMap, Interaction.COMPONENT, ValidationCheck.NOT_ALLOWED_COMPONENT);
        setValidationMap(validationMap, Interaction.DATASET, ValidationCheck.HAS_AT_LEAST_ONE_IDENTIFIER, ValidationCheck.HAS_AT_LEAST_ONE_IDENTIFIER_TIME, ValidationCheck.HAS_ONLY_ONE_IDENTIFIER_TIME);
        return validationMap;
    }

    private static Map<Interaction, List<ValidationCheck>> getPeriodValidation() {
        EnumMap<Interaction, List<ValidationCheck>> validationMap = new EnumMap<>(Interaction.class);
        setValidationMap(validationMap, Interaction.SCALAR, ValidationCheck.IS_TIME_PERIOD);
        setValidationMap(validationMap, Interaction.COMPONENT, ValidationCheck.IS_TIME_PERIOD);
        setValidationMap(validationMap, Interaction.DATASET, ValidationCheck.HAS_AT_LEAST_ONE_IDENTIFIER, ValidationCheck.HAS_AT_LEAST_ONE_IDENTIFIER_TIME_PERIOD, ValidationCheck.HAS_ONLY_ONE_IDENTIFIER_TIME_PERIOD);
        return validationMap;
    }

    private static Map<Interaction, List<ValidationCheck>> getSubValidation() {
        EnumMap<Interaction, List<ValidationCheck>> validationMap = new EnumMap<>(Interaction.class);
        setValidationMap(validationMap, Interaction.SCALAR, ValidationCheck.NOT_ALLOWED);
        setValidationMap(validationMap, Interaction.COMPONENT, ValidationCheck.NOT_ALLOWED);
        setValidationMap(validationMap, Interaction.DATASET, ValidationCheck.HAS_ONLY_IDENTIFIER);
        return validationMap;
    }

    private static Map<Interaction, List<ValidationCheck>> getUnpivotValidation() {
        EnumMap<Interaction, List<ValidationCheck>> validationMap = new EnumMap<>(Interaction.class);
        setValidationMap(validationMap, Interaction.SCALAR, ValidationCheck.NOT_ALLOWED);
        setValidationMap(validationMap, Interaction.COMPONENT, ValidationCheck.NOT_ALLOWED);
        setValidationMap(validationMap, Interaction.DATASET, ValidationCheck.HAS_VALUE_DOMAIN_AND_ROLE);
        return validationMap;
    }

    private static Map<Interaction, List<ValidationCheck>> getRenameValidation() {
        EnumMap<Interaction, List<ValidationCheck>> validationMap = new EnumMap<>(Interaction.class);
        setValidationMap(validationMap, Interaction.SCALAR, ValidationCheck.NOT_ALLOWED);
        setValidationMap(validationMap, Interaction.COMPONENT, ValidationCheck.NOT_ALLOWED);
        setValidationMap(validationMap, Interaction.DATASET, ValidationCheck.COMPONENT_NOT_EXISTS);
        return validationMap;
    }

    private static Map<Interaction, List<ValidationCheck>> getDropValidation() {
        EnumMap<Interaction, List<ValidationCheck>> validationMap = new EnumMap<>(Interaction.class);
        setValidationMap(validationMap, Interaction.DATASET, ValidationCheck.ARE_NOT_IDENTIFIERS);
        return validationMap;
    }

    private static Map<Interaction, List<ValidationCheck>> getKeepValidation() {
        EnumMap<Interaction, List<ValidationCheck>> validationMap = new EnumMap<>(Interaction.class);
        setValidationMap(validationMap, Interaction.DATASET, ValidationCheck.ARE_NOT_IDENTIFIERS);
        return validationMap;
    }

    private static Map<Interaction, List<ValidationCheck>> getGroupExceptValidation() {
        EnumMap<Interaction, List<ValidationCheck>> validationMap = new EnumMap<>(Interaction.class);
        setValidationMap(validationMap, Interaction.DATASET, ValidationCheck.ARE_ALL_IDENTIFIERS);
        return validationMap;
    }

    private static Map<Interaction, List<ValidationCheck>> getGroupByValidation() {
        EnumMap<Interaction, List<ValidationCheck>> validationMap = new EnumMap<>(Interaction.class);
        setValidationMap(validationMap, Interaction.DATASET, ValidationCheck.ARE_ALL_IDENTIFIERS);
        return validationMap;
    }

    private static Map<Interaction, List<ValidationCheck>> getRatioValidation() {
        EnumMap<Interaction, List<ValidationCheck>> validationMap = new EnumMap<>(Interaction.class);
        setValidationMap(validationMap, Interaction.COMPONENT, ValidationCheck.IS_NUMBER_OR_INTEGER_TYPE);
        setValidationMap(validationMap, Interaction.DATASET, ValidationCheck.HAS_ONLY_MEASURE_INTEGER_OR_NUMBER);
        return validationMap;
    }

    private static Map<Interaction, List<ValidationCheck>> getRankValidation() {
        EnumMap<Interaction, List<ValidationCheck>> validationMap = new EnumMap<>(Interaction.class);
        setValidationMap(validationMap, Interaction.COMPONENT, ValidationCheck.IS_NUMBER_OR_INTEGER_TYPE);
        setValidationMap(validationMap, Interaction.DATASET, ValidationCheck.HAS_ONLY_ONE_MEASURE, ValidationCheck.HAS_ONLY_MEASURE_INTEGER_OR_NUMBER);
        return validationMap;
    }

    private static Map<Interaction, List<ValidationCheck>> getCountValidation() {
        EnumMap<Interaction, List<ValidationCheck>> validationMap = new EnumMap<>(Interaction.class);
        setValidationMap(validationMap, Interaction.DATASET, ValidationCheck.HAS_ONLY_ONE_MEASURE);
        return validationMap;
    }

    private static Map<Interaction, List<ValidationCheck>> getUnionValidation() {
        EnumMap<Interaction, List<ValidationCheck>> validationMap = new EnumMap<>(Interaction.class);
        setValidationMap(validationMap, Interaction.SCALAR_TO_SCALAR, ValidationCheck.NOT_ALLOWED_SCALAR_TO_SCALAR);
        setValidationMap(validationMap, Interaction.SCALAR_TO_DATASET, ValidationCheck.NOT_ALLOWED_SCALAR_TO_SCALAR);
        setValidationMap(validationMap, Interaction.DATASET_TO_DATASET, ValidationCheck.HAS_SAME_STRUCTURE);
        return validationMap;
    }

    private static Map<Interaction, List<ValidationCheck>> getNotValidation() {
        EnumMap<Interaction, List<ValidationCheck>> validationMap = new EnumMap<>(Interaction.class);
        setValidationMap(validationMap, Interaction.SCALAR, ValidationCheck.HAS_ONLY_ONE_MEASURE, ValidationCheck.HAS_ONLY_MEASURE_BOOLEAN);
        setValidationMap(validationMap, Interaction.COMPONENT, ValidationCheck.IS_BOOLEAN);
        setValidationMap(validationMap, Interaction.DATASET, ValidationCheck.HAS_ONLY_ONE_MEASURE, ValidationCheck.HAS_ONLY_MEASURE_BOOLEAN);
        return validationMap;

    }

    private static Map<Interaction, List<ValidationCheck>> getExistInValidation() {
        EnumMap<Interaction, List<ValidationCheck>> validationMap = new EnumMap<>(Interaction.class);
        setValidationMap(validationMap, Interaction.SCALAR_TO_SCALAR, ValidationCheck.NOT_ALLOWED_SCALAR_TO_SCALAR);
        setValidationMap(validationMap, Interaction.SCALAR_TO_DATASET, ValidationCheck.NOT_ALLOWED_SCALAR_TO_SCALAR);
        setValidationMap(validationMap, Interaction.DATASET_TO_DATASET, ValidationCheck.IS_SUPERSET);
        return validationMap;
    }

    private static Map<Interaction, List<ValidationCheck>> getIsNullValidation() {
        EnumMap<Interaction, List<ValidationCheck>> validationMap = new EnumMap<>(Interaction.class);
        setValidationMap(validationMap, Interaction.SCALAR, ValidationCheck.HAS_ONLY_ONE_MEASURE);
        setValidationMap(validationMap, Interaction.DATASET, ValidationCheck.HAS_ONLY_ONE_MEASURE);
        return validationMap;
    }


    private static Map<Interaction, List<ValidationCheck>> getBetweenParameterValidation() {
        EnumMap<Interaction, List<ValidationCheck>> validationMap = new EnumMap<>(Interaction.class);
        setValidationMap(validationMap, Interaction.COMPONENT_TO_COMPONENT, ValidationCheck.IS_IDENTICAL_TYPE);
        setValidationMap(validationMap, Interaction.SCALAR_TO_COMPONENT, ValidationCheck.IS_IDENTICAL_TYPE);
        return validationMap;
    }

    private static Map<Interaction, List<ValidationCheck>> getBetweenValidation() {
        EnumMap<Interaction, List<ValidationCheck>> validationMap = new EnumMap<>(Interaction.class);
        setValidationMap(validationMap, Interaction.SCALAR_TO_DATASET, HAS_ONE_MEASURE_OF_SAME_TYPE, ValidationCheck.HAS_ONLY_ONE_MEASURE);
        setValidationMap(validationMap, Interaction.COMPONENT_TO_COMPONENT, ValidationCheck.IS_IDENTICAL_TYPE);
        setValidationMap(validationMap, Interaction.SCALAR_TO_COMPONENT, ValidationCheck.IS_IDENTICAL_TYPE);
        return validationMap;
    }

    private static Map<Interaction, List<ValidationCheck>> getMathParameterValidation() {
        EnumMap<Interaction, List<ValidationCheck>> validationMap = new EnumMap<>(Interaction.class);
        setValidationMap(validationMap, Interaction.SCALAR, ValidationCheck.HAS_ONLY_MEASURE_INTEGER);
        setValidationMap(validationMap, Interaction.COMPONENT, ValidationCheck.NOT_ALLOWED);
        setValidationMap(validationMap, Interaction.DATASET, ValidationCheck.HAS_ONLY_ONE_MEASURE, ValidationCheck.HAS_ONLY_MEASURE_INTEGER);
        return validationMap;
    }

    private static Map<Interaction, List<ValidationCheck>> getMathValidation() {
        EnumMap<Interaction, List<ValidationCheck>> validationMap = new EnumMap<>(Interaction.class);
        setValidationMap(validationMap, Interaction.SCALAR, ValidationCheck.HAS_ONLY_MEASURE_NUMBER);
        setValidationMap(validationMap, Interaction.COMPONENT, ValidationCheck.IS_NUMBER);
        setValidationMap(validationMap, Interaction.DATASET, ValidationCheck.HAS_ONLY_MEASURE_NUMBER);
        return validationMap;

    }

    private static Map<Interaction, List<ValidationCheck>> getStringOperatorValidation() {
        EnumMap<Interaction, List<ValidationCheck>> validationMap = new EnumMap<>(Interaction.class);
        setValidationMap(validationMap, Interaction.SCALAR, ValidationCheck.HAS_MEASURES_IMPLICITLY_CASTABLE_TO_STRING);
        setValidationMap(validationMap, Interaction.COMPONENT, ValidationCheck.IS_IMPLICIT_CASTABLE_TO_STRING);
        setValidationMap(validationMap, Interaction.DATASET, ValidationCheck.HAS_ONLY_ONE_MEASURE, ValidationCheck.HAS_MEASURES_IMPLICITLY_CASTABLE_TO_STRING);
        return validationMap;
    }

    private static Map<Interaction, List<ValidationCheck>> getStringParameterValidation() {
        EnumMap<Interaction, List<ValidationCheck>> validationMap = new EnumMap<>(Interaction.class);
        setValidationMap(validationMap, Interaction.SCALAR, ValidationCheck.HAS_ONLY_MEASURE_INTEGER);
        setValidationMap(validationMap, Interaction.DATASET, ValidationCheck.HAS_ONLY_ONE_MEASURE, ValidationCheck.HAS_ONLY_MEASURE_INTEGER);
        return validationMap;
    }

    private static Map<Interaction, List<ValidationCheck>> getBinaryStringValidation() {
        EnumMap<Interaction, List<ValidationCheck>> validationMap = new EnumMap<>(Interaction.class);
        setValidationMap(validationMap, Interaction.SCALAR_TO_SCALAR, ValidationCheck.HAS_MEASURES_IMPLICITLY_CASTABLE_TO_STRING);
        setValidationMap(validationMap, Interaction.SCALAR_TO_DATASET, ValidationCheck.HAS_MEASURES_IMPLICITLY_CASTABLE_TO_STRING);
        setValidationMap(validationMap, Interaction.DATASET_TO_DATASET, ValidationCheck.HAS_MEASURES_IMPLICITLY_CASTABLE_TO_STRING, ValidationCheck.IS_SUPERSET, ValidationCheck.HAS_COMMON_MEASURE);
        return validationMap;
    }

    private static Map<Interaction, List<ValidationCheck>> getMembershipInClause() {
        EnumMap<Interaction, List<ValidationCheck>> validationMap = new EnumMap<>(Interaction.class);
        setValidationMap(validationMap, Interaction.DATASET, ValidationCheck.HAS_DATASET_THIS_NAME, ValidationCheck.COMPONENT_EXISTS);
        return validationMap;
    }

    private static Map<Interaction, List<ValidationCheck>> getMembershipValidation() {
        EnumMap<Interaction, List<ValidationCheck>> validationMap = new EnumMap<>(Interaction.class);
        setValidationMap(validationMap, Interaction.DATASET, ValidationCheck.COMPONENT_EXISTS);
        return validationMap;
    }

    private static Map<Interaction, List<ValidationCheck>> getPersistValidation() {
        EnumMap<Interaction, List<ValidationCheck>> validationMap = new EnumMap<>(Interaction.class);
        setValidationMap(validationMap, Interaction.DATASET, ValidationCheck.HAS_AT_LEAST_ONE_IDENTIFIER);
        return validationMap;
    }

    private static Map<Interaction, List<ValidationCheck>> getDatasetValidation() {
        EnumMap<Interaction, List<ValidationCheck>> validationMap = new EnumMap<>(Interaction.class);
        setValidationMap(validationMap, Interaction.SCALAR, ValidationCheck.DATASET_EXIST);
        setValidationMap(validationMap, Interaction.DATASET, ValidationCheck.DATASET_EXIST);
        return validationMap;
    }

    private static Map<Interaction, List<ValidationCheck>> getValidationComponent() {
        EnumMap<Interaction, List<ValidationCheck>> validationMap = new EnumMap<>(Interaction.class);
        setValidationMap(validationMap, Interaction.COMPONENT, ValidationCheck.COMPONENT_EXISTS);
        return validationMap;
    }

    private static Map<Interaction, List<ValidationCheck>> getEmptyHashMap() {
        return new EnumMap<>(Interaction.class);
    }

    private static Map<Interaction, List<ValidationCheck>> getHierarchyValidation() {
        EnumMap<Interaction, List<ValidationCheck>> validationMap = new EnumMap<>(Interaction.class);
        setValidationMap(validationMap, Interaction.SCALAR, ValidationCheck.NOT_ALLOWED);
        setValidationMap(validationMap, Interaction.COMPONENT, ValidationCheck.NOT_ALLOWED_COMPONENT);
        setValidationMap(validationMap, Interaction.DATASET, ValidationCheck.HAS_ONLY_ONE_MEASURE, ValidationCheck.HAS_ONLY_MEASURE_INTEGER_OR_NUMBER);
        return validationMap;
    }

    private String value;
    private VtlType expectedTypeReturn;
    private Map<Interaction, List<ValidationCheck>> validationMap;
    private Boolean substituteComponent;


    Operator(String value, VtlType expectedTypeReturn, Boolean substituteComponent, Map<Interaction, List<ValidationCheck>> validationMap) {
        this.value = value;
        this.expectedTypeReturn = expectedTypeReturn;
        this.validationMap = validationMap;
        this.substituteComponent = substituteComponent;
    }

    public String getValue() {
        return value;
    }

    public Boolean isComboExpression() {
        return comboExpression.getOrDefault(value, false);
    }
        
    public Boolean isBooleanOperator() {
        return booleanOperator.getOrDefault(value, false);
    }
        
    public Boolean isComparisonBinary() {
        return comparisonBinary.getOrDefault(value, false);
    }
    
    public VtlType getExpectedTypeReturn() {
        return expectedTypeReturn;
    }

    public Map<Interaction, List<ValidationCheck>> getValidationMap() {
        return validationMap;
    }

    public Boolean getSubstituteComponent() {
        if (substituteComponent == null)
            return false;
        return substituteComponent;
    }

    private static final Map<String, Boolean> comboExpression;
    static {
        Map<String, Boolean> map = new HashMap<>();
        map.put("+", true);
        map.put("-", true);
        map.put("*", true);
        map.put("/", true);
        comboExpression = Collections.unmodifiableMap(map);
    }
    
    private static final Map<String, Boolean> booleanOperator;
    static {
        Map<String, Boolean> map = new HashMap<>();
        map.put("AND", true);
        map.put("OR", true);
        map.put("XOR", true);
        map.put("NOT", true);
        booleanOperator = Collections.unmodifiableMap(map);
    }
     
    private static final Map<String, Boolean> comparisonBinary;
    static {
        Map<String, Boolean> map = new HashMap<>();
        map.put("=", true);
        map.put("<", true);
        map.put("<=", true);
        map.put(">", true);
        map.put(">=", true);
        map.put("<>", true);
        comparisonBinary = Collections.unmodifiableMap(map);
    }   
    
    public static Map<Interaction, List<ValidationCheck>> getCommonNumericBinaryOperatorValidation() {
        EnumMap<Interaction, List<ValidationCheck>> validationMap = new EnumMap<>(Interaction.class);
        setValidationMap(validationMap, Interaction.SCALAR_TO_SCALAR, ValidationCheck.HAS_ONLY_MEASURE_INTEGER_OR_NUMBER);
        setValidationMap(validationMap, Interaction.SCALAR_TO_DATASET, ValidationCheck.HAS_ONLY_MEASURE_INTEGER_OR_NUMBER, ValidationCheck.HAS_AT_LEAST_ONE_BY_ROLE);
        setValidationMap(validationMap, Interaction.DATASET_TO_DATASET, ValidationCheck.HAS_ONLY_MEASURE_INTEGER_OR_NUMBER, ValidationCheck.IS_SUPERSET, ValidationCheck.HAS_COMMON_MEASURE);
        setValidationMap(validationMap, Interaction.SCALAR_TO_COMPONENT, ValidationCheck.IS_NUMBER_OR_INTEGER_TYPE);
        setValidationMap(validationMap, Interaction.COMPONENT_TO_COMPONENT, ValidationCheck.IS_NUMBER_OR_INTEGER_TYPE);
        return validationMap;
    }

    public static Map<Interaction, List<ValidationCheck>> getCommonNumericUnaryOperatorValidation() {
        EnumMap<Interaction, List<ValidationCheck>> validationMap = new EnumMap<>(Interaction.class);
        setValidationMap(validationMap, Interaction.SCALAR, ValidationCheck.HAS_ONLY_MEASURE_INTEGER_OR_NUMBER);
        setValidationMap(validationMap, Interaction.COMPONENT, ValidationCheck.IS_NUMBER_OR_INTEGER_TYPE);
        setValidationMap(validationMap, Interaction.DATASET, ValidationCheck.HAS_ONLY_MEASURE_INTEGER_OR_NUMBER);
        return validationMap;
    }

    public static Map<Interaction, List<ValidationCheck>> getCommonComparisonExpressionValidation() {
        EnumMap<Interaction, List<ValidationCheck>> validationMap = new EnumMap<>(Interaction.class);
        setValidationMap(validationMap, Interaction.SCALAR_TO_SCALAR, HAS_ONE_MEASURE_OF_SAME_TYPE);
        setValidationMap(validationMap, Interaction.SCALAR_TO_DATASET, ValidationCheck.HAS_ONLY_ONE_MEASURE, HAS_ONE_MEASURE_OF_SAME_TYPE);
        setValidationMap(validationMap, Interaction.DATASET_TO_DATASET, ValidationCheck.IS_SUPERSET, ValidationCheck.HAS_ONLY_ONE_MEASURE, ValidationCheck.HAS_COMMON_MEASURE, HAS_ONE_MEASURE_OF_SAME_TYPE);
        return validationMap;
    }

    public static Map<Interaction, List<ValidationCheck>> getCommonBooleanExpressionValidation() {
        EnumMap<Interaction, List<ValidationCheck>> validationMap = new EnumMap<>(Interaction.class);
        setValidationMap(validationMap, Interaction.SCALAR_TO_SCALAR, ValidationCheck.HAS_ONLY_MEASURE_BOOLEAN);
        setValidationMap(validationMap, Interaction.SCALAR_TO_DATASET, ValidationCheck.HAS_ONLY_ONE_MEASURE, ValidationCheck.HAS_ONLY_MEASURE_BOOLEAN);
        setValidationMap(validationMap, Interaction.DATASET_TO_DATASET, ValidationCheck.IS_SUPERSET, ValidationCheck.HAS_ONLY_ONE_MEASURE, ValidationCheck.HAS_COMMON_MEASURE, ValidationCheck.HAS_ONLY_MEASURE_BOOLEAN);
        setValidationMap(validationMap, Interaction.SCALAR_TO_COMPONENT, ValidationCheck.IS_BOOLEAN);
        setValidationMap(validationMap, Interaction.COMPONENT_TO_COMPONENT, ValidationCheck.IS_BOOLEAN);
        return validationMap;
    }

    public static Map<Interaction, List<ValidationCheck>> getCommonStringUnaryOperatorValidation() {
        EnumMap<Interaction, List<ValidationCheck>> validationMap = new EnumMap<>(Interaction.class);
        setValidationMap(validationMap, Interaction.SCALAR, ValidationCheck.HAS_MEASURES_IMPLICITLY_CASTABLE_TO_STRING);
        setValidationMap(validationMap, Interaction.COMPONENT, ValidationCheck.IS_IMPLICIT_CASTABLE_TO_STRING);
        setValidationMap(validationMap, Interaction.DATASET, ValidationCheck.HAS_MEASURES_IMPLICITLY_CASTABLE_TO_STRING);
        return validationMap;
    }

    public static Map<Interaction, List<ValidationCheck>> getCommonInOperatorValidation() {
        EnumMap<Interaction, List<ValidationCheck>> validationMap = new EnumMap<>(Interaction.class);
        setValidationMap(validationMap, Interaction.SCALAR_TO_SCALAR, HAS_ONE_MEASURE_OF_SAME_TYPE);
        setValidationMap(validationMap, Interaction.SCALAR_TO_DATASET, HAS_ONE_MEASURE_OF_SAME_TYPE,
                ValidationCheck.HAS_ONLY_ONE_MEASURE);
        setValidationMap(validationMap, Interaction.DATASET_TO_DATASET, HAS_ONE_MEASURE_OF_SAME_TYPE,
                ValidationCheck.HAS_ONLY_ONE_MEASURE);
        setValidationMap(validationMap, Interaction.SCALAR_TO_COMPONENT, ValidationCheck.IS_IDENTICAL_TYPE);
        setValidationMap(validationMap, Interaction.COMPONENT_TO_COMPONENT, ValidationCheck.IS_IDENTICAL_TYPE);
        return validationMap;
    }

    public static Map<Interaction, List<ValidationCheck>> getCommonAggrFunctionValidation() {
        EnumMap<Interaction, List<ValidationCheck>> validationMap = new EnumMap<>(Interaction.class);
        setValidationMap(validationMap, Interaction.COMPONENT, ValidationCheck.IS_NUMBER_OR_INTEGER_TYPE);
        setValidationMap(validationMap, Interaction.DATASET, ValidationCheck.HAS_ONLY_MEASURE_INTEGER_OR_NUMBER);
        return validationMap;
    }

    private static void setValidationMap(EnumMap<Interaction, List<ValidationCheck>> validationMap, Interaction interaction, ValidationCheck... validationChecks) {
        validationMap.put(interaction, Arrays.asList(validationChecks));
    }
}
