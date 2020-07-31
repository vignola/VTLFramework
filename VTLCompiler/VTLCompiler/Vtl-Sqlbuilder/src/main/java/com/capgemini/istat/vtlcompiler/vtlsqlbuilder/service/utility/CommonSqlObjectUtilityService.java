package com.capgemini.istat.vtlcompiler.vtlsqlbuilder.service.utility;

import com.capgemini.istat.vtlcompiler.vtlcommon.constant.ConstantUtility;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.dataset.VtlComponent;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.dataset.VtlComponentRole;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.dataset.VtlType;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.operator.Operator;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.translation.SqlComponent;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.translation.SqlDataset;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.translation.SqlTable;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.constant.*;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.dataset.VtlComponentId;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.define.ruleset.hierarchical.VtlCodeItem;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.define.ruleset.hierarchical.VtlCodeItemRelation;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.define.ruleset.hierarchical.VtlHRRule;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.VtlExpression;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.clause.analytic.VtlAnalyticFunctionExpression;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.clause.analytic.VtlOrderByItem;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.clause.analytic.VtlWindowingLimit;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.clause.analytic.VtlWindowingType;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.clause.pivot.VtlPivotClauseExpression;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.comparison.VtlBetweenExpression;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.comparison.VtlComparisonBinaryExpression;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.comparison.VtlInExpression;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.function.VtlBinaryExpression;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.hierarchy.VtlHierarchyExpression;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.validation.InputMode;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.validation.ValidationMode;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.validation.VtlCheckHierarchy;
import com.capgemini.istat.vtlcompiler.vtlcommon.service.ComponentUtilityService;
import com.healthmarketscience.sqlbuilder.*;
import com.healthmarketscience.sqlbuilder.WindowDefinitionClause.FrameBound;
import com.healthmarketscience.sqlbuilder.custom.HookType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * La classe contiene la logica di costruzione dei diversi operatori SQL necessari per implementare i comandi VTL verso SQL
 * I metodi restituiscono specifiche implementazioni di un SqlObject. Un SqlObject è l'oggetto utilizzato dalla libreria
 * SqlBuilder per rappresentare gli elementi SQL
 *
 * @see DbTableUtilityService
 * @see ParameterQueryUtilityService
 * @see ComponentUtilityService
 */
@Service
public class CommonSqlObjectUtilityService implements ISqlObjectUtilityService {

    public static final String CV = ", CV() ";
    protected DbTableUtilityService dbTableUtilityService;
    protected ParameterQueryUtilityService parameterQueryUtilityService;
    protected ComponentUtilityService componentUtilityService;
    protected Environment environment;


    @Value("${application.default.value.true}")
    protected String applicationDefaultValueTrue;

    @Value("${application.default.value.false}")
    protected String applicationDefaultValueFalse;

    @Value("${application.default.value.domain.code}")
    protected String applicationDefaultValueDomainCode;
    public static final String BOOL_VAR = "BOOL_VAR";
    public static final String ME_TO_USE = "ME_TO_USE";
    public static final String ME_DS = "ME_DS";
    public static final String ME_RULE = "ME_RULE";

    @Override
    public String getApplicationDefaultValueTrue() {
        return applicationDefaultValueTrue;
    }

    @Override
    public void setApplicationDefaultValueTrue(String applicationDefaultValueTrue) {
        this.applicationDefaultValueTrue = applicationDefaultValueTrue;
    }

    @Override
    public String getApplicationDefaultValueFalse() {
        return applicationDefaultValueFalse;
    }

    @Override
    public void setApplicationDefaultValueFalse(String applicationDefaultValueFalse) {
        this.applicationDefaultValueFalse = applicationDefaultValueFalse;
    }

    @Override
    public String getApplicationDefaultValueDomainCode() {
        return applicationDefaultValueDomainCode;
    }

    @Override
    public void setApplicationDefaultValueDomainCode(String applicationDefaultValueDomainCode) {
        this.applicationDefaultValueDomainCode = applicationDefaultValueDomainCode;
    }

    @Autowired
    public void setComponentUtilityService(ComponentUtilityService componentUtilityService) {
        this.componentUtilityService = componentUtilityService;
    }

    @Autowired
    public void setDbTableUtilityService(DbTableUtilityService dbTableUtilityService) {
        this.dbTableUtilityService = dbTableUtilityService;
    }

    @Autowired
    public void setParameterQueryUtilityService(ParameterQueryUtilityService parameterQueryUtilityService) {
        this.parameterQueryUtilityService = parameterQueryUtilityService;
    }

    @Autowired
    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }

    @Override
    public String getSqLOperator(String operatorValue) {
        return null;
    }

    @Override
    public void addCustomFrom(SelectQuery selectQuery) {
        //non ce n'è bisogno

    }

    /**
     * Costruisce una select da un sqlDataset. Il metodo interpreta l'oggetto sqlDataset e costruisce la query che è stata
     * descritta nei diversi passaggi dell'elaborazione.
     * La maggior parte degli operatori utilizza questo metodo per costruire il risultato sql finale
     * Il metodo prende in ingresso un sqlDataset e costruisce una select dove
     * - ai componenti viene applicata la funzione e imposto l'alias del nome di ocmponente previsto
     * - Viene creata una join se è presente più di un sqlTables
     * - Viene aggiunta la where se è popolata la whereCondition
     * - Viene aggiunta una group by se presente
     * - Viene aggiunta una order by se presente
     * - Viene aggiunta una having se presente
     *
     * @param sqlDataset
     * @return
     */
    @Override
    public SelectQuery createSelectFromSqlDataset(SqlDataset sqlDataset) {
        if (!sqlDataset.getSqlTables().get(0).getVtlDataset().isOnlyAScalar())
            sqlDataset.setUpAlias(dbTableUtilityService.getDbSpec().getNextAlias(), sqlDataset.getSqlTables().get(0).getVtlDataset());
        SelectQuery selectQuery = new SelectQuery();
        for (SqlComponent sqlComponent : sqlDataset.getComponentList()) {
            selectQuery.addAliasedColumn(sqlComponent.getResult(), sqlComponent.getAliasName());
        }
        if (sqlDataset.getSqlTables().size() == 1) {
            if (sqlDataset.getSqlTables().get(0).getCustomFrom() != null) {
                selectQuery.addCustomFromTable("( " + sqlDataset.getSqlTables().get(0).getCustomFrom() + " ) " + " " + sqlDataset.getSqlTables().get(0).getAliasName());
            } else if (sqlDataset.getSqlTables().get(0).getVtlDataset().isOnlyAScalar()) {
                addCustomFrom(selectQuery);
            } else {
                selectQuery.addCustomFromTable(getFormattedTableName(sqlDataset.getSqlTables().get(0).getVtlDataset().getName()) + " " + sqlDataset.getSqlTables().get(0).getAliasName());
            }
        } else {
            List<SqlTable> sqlTables = sqlDataset.getSqlTables();
            SqlTable firstTable = sqlTables.get(0);
            String tableName = firstTable.getCustomFrom() == null ? getFormattedTableName(firstTable.getVtlDataset().getName()) : "( " + firstTable.getCustomFrom() + " ) ";
            for (int i = 1; i < sqlTables.size(); i++) {
                String joinTableName = sqlTables.get(i).getCustomFrom() == null ? getFormattedTableName(sqlTables.get(i).getVtlDataset().getName()) : "( " + sqlTables.get(i).getCustomFrom() + " ) ";
                selectQuery.addCustomJoin(
                        sqlTables.get(i).getJoinType(),
                        tableName + " " + firstTable.getAliasName(),
                        joinTableName + " " + sqlTables.get(i).getAliasName(),
                        (Condition) sqlTables.get(i).getOnConditions()
                );
            }
        }
        if (sqlDataset.getWhereCondition() != null) {
            selectQuery.addCondition((Condition) sqlDataset.getWhereCondition());
        }

        if (sqlDataset.getGroupByClauseColumns() != null) {
            selectQuery.addCustomGroupings(sqlDataset.getGroupByClauseColumns());
        }

        if (sqlDataset.getOrderByClauseColumns() != null) {
            for (OrderObject orderObject : sqlDataset.getOrderByClauseColumns()) {
                selectQuery.addCustomOrderings(orderObject);
            }
        }

        if (sqlDataset.getHavingCondition() != null) {
            selectQuery.addHaving((Condition) sqlDataset.getHavingCondition());
        }
        return selectQuery;
    }

    /**
     * prende in ingresso un nome tabella e aggiunge i doppi apici se riscontra l'utilizzo di un carattere speciale
     *
     * @param tableName
     * @return
     */
    public String getFormattedTableName(String tableName) {
        String specialCharacters = environment.getProperty("db.specialCharacter");
        if (specialCharacters == null || specialCharacters.isEmpty()) {
            specialCharacters = "C.C,C&";
        }
        String[] splittedCharacter = specialCharacters.split("C");
        for (String specialCharacter : splittedCharacter) {
            if (specialCharacter != null && !specialCharacter.isEmpty() && tableName.contains(specialCharacter)) {
                return "\"" + tableName + "\"";
            }

        }
        return tableName;
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
        return new CreateTableQuery(getFormattedTableName(tableName)).setAsCustomSelect(sqlObject);
    }

    /**
     * Dato in ingresso una lista di componenti e un nome tabella costruisce una lista di statement sql che crea gli indici
     * per la tabella.
     * Vengono creati tanti indici quanti identificativi vengono trovati nella lista
     *
     * @param sqlComponents
     * @param tableName
     * @return
     */
    @Override
    public List<SqlObject> createIndex(List<SqlComponent> sqlComponents, String tableName) {
        List<SqlObject> results = new ArrayList<>();
        for (SqlComponent component : sqlComponents) {
            if (component.getVtlComponent() != null && component.getVtlComponent().getVtlComponentRole().equals(VtlComponentRole.IDENTIFIER)) {
                String indexName = tableName + ConstantUtility.UNDERSCORE + component.getAliasName() + ConstantUtility.INDEX_SUFFIX;
                if (indexName.length() >= 30) {
                    indexName = dbTableUtilityService.getDbSpec().getNextAlias() + ConstantUtility.INDEX_SUFFIX;
                }
                CreateIndexQuery createIndexQuery;
                if (!tableName.equalsIgnoreCase(getFormattedTableName(tableName))) {
                    createIndexQuery = new CreateIndexQuery(getFormattedTableName(tableName), "\"" + indexName + "\"").addCustomColumns(component.getAliasName());

                } else {
                    createIndexQuery = new CreateIndexQuery(tableName, indexName).addCustomColumns(component.getAliasName());
                }
                results.add(createIndexQuery);
            }
        }
        return results;
    }

    /**
     * Crea lo statement di insert select
     *
     * @param tableName
     * @param selectQuery
     * @return
     */
    @Override
    public InsertSelectQuery insertSelectQuery(String tableName, SelectQuery selectQuery) {
        return new InsertSelectQuery(tableName).setSelectQuery(selectQuery);
    }

    /**
     * Crea uno statement di drop table
     *
     * @param tableName
     * @return
     */
    @Override
    public SqlObject dropTableQuery(String tableName) {
        DropQuery dropQuery = new DropQuery(DropQuery.Type.TABLE, getFormattedTableName(tableName));
        return new CustomSql("-- reverse " + dropQuery + " ");
    }

    /**
     * Applica una funzione unaria a un SQLObject. In base all'operator viene applicata la funzione (o il costrutto) specifica SQL
     * il metodo offre costruzioni ad hoc per l'operatore is null, match caracter e time agg
     *
     * @param sqlObject
     * @param parameter
     * @param optionalParameter
     * @param operator
     * @return
     */
    @Override
    public SqlObject applyUnaryFunctionToSqlObject(SqlObject sqlObject, VtlExpression parameter, List<VtlExpression> optionalParameter, Operator operator) {
        if (operator.getValue().equals(Operator.IS_NULL.getValue())) {
            UnaryCondition uc = new UnaryCondition(UnaryCondition.Op.IS_NULL, sqlObject);
            uc.setDisableParens(true);
            return createCaseStatement(uc, applicationDefaultValueTrue, applicationDefaultValueFalse);
        }

        if (operator.getValue().equals(Operator.MATCH_CHARACTERS.getValue())) {
            UnaryConditionWithParameter uc = new UnaryConditionWithParameter(UnaryConditionWithParameter.Op.REGEXP_LIKE, sqlObject, new ValueObject(((VtlConstantExpression) parameter).getVtlConstant().getValue()));
            return createCaseStatement(uc, applicationDefaultValueTrue, applicationDefaultValueFalse);

        }

        if (operator.getValue().equals(Operator.TIME_AGG.getValue()) || operator.getValue().equals(Operator.TIME_AGG_FIRST.getValue()) || operator.getValue().equals(Operator.TIME_AGG_LAST.getValue()) || operator.getValue().equals(Operator.TIME_AGG_PERIOD.getValue())) {
            FunctionCall fcCoalesce = FunctionCall.customFunction(getSqLOperator(Operator.NVL.getValue()))
                    .addCustomParams(sqlObject).addCustomParams("NULL");

            FunctionCall fcTimeAgg = FunctionCall.customFunction(getSqLOperator(operator.getValue()))
                    .addCustomParams(fcCoalesce);
            parameterQueryUtilityService.addParameter(fcTimeAgg, parameter);

            if (optionalParameter == null) {
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

        FunctionCall fc = FunctionCall.customFunction(getSqLOperator(operator.getValue()))
                .addCustomParams(sqlObject);
        parameterQueryUtilityService.addParameter(fc, parameter);
        parameterQueryUtilityService.addOptionalParameters(fc, optionalParameter);
        return fc;
    }

    /**
     * Applica una funzione su due componenti di una query. il metodo in base alle informazioni dell'operatore
     * può acclicare una combo expression o una comparison expression(simulandola con l'ausilio di una case when)
     *
     * @param sqlComponentLeft
     * @param sqlComponentRight
     * @param operator
     * @return
     */
    @Override
    public SqlObject applyOperatorFunctionToSqlObjects(SqlComponent sqlComponentLeft, SqlComponent sqlComponentRight, Operator operator) {
        SqlObject sqlObjectLeft = sqlComponentLeft.getResult();
        SqlObject sqlObjectRight = sqlComponentRight.getResult();

        if (operator.isComboExpression()) {
            ComboExpression ce = new ComboExpression(operator.getValue());
            ce.addExpression(sqlObjectLeft).addExpression(sqlObjectRight).setDisableParens(true);
            return ce;
        }

        if (operator.isComparisonBinary()) {
            BinaryCondition bc = new BinaryCondition(operator.getValue(), sqlObjectLeft, sqlObjectRight);
            return createCaseStatement(bc, applicationDefaultValueTrue, applicationDefaultValueFalse);
        }

        FunctionCall fc = FunctionCall.customFunction(getSqLOperator(operator.getValue()))
                .addCustomParams(sqlObjectLeft).addCustomParams(sqlObjectRight);
        return fc;
    }

    /**
     * applica una condizione o una funzione unaria su un componente. Il metodo applica la logica di isNUll e Not a un
     * oggetto SQL
     *
     * @param sqlObject
     * @param operator
     * @param vtlExpression
     * @return
     */
    @Override
    public SqlObject applyUnaryConditionToSqlObject(SqlObject sqlObject, Operator operator, VtlExpression vtlExpression) {
        if (operator.getValue().equals(Operator.IS_NULL.getValue())) {
            UnaryCondition uc = new UnaryCondition(UnaryCondition.Op.IS_NULL, sqlObject);
            uc.setDisableParens(true);
            return uc;
        }

        if (operator.getValue().equals(Operator.NOT.getValue())) {
            sqlObject = applyDefaultBooleanUnaryConditionToSqlObject(sqlObject, vtlExpression);

            NotCondition nc = new NotCondition(sqlObject);
            nc.setDisableParens(true);
            return nc;
        }

        throw new UnsupportedOperationException(operator.getValue());
    }

    /**
     * Applica una condizione o una funzione unaria su un componente. La funzione applicata possiede altri parametri oltre al primo
     *
     * @param sqlObject
     * @param operator
     * @param vtlExpression
     * @param parameter
     * @param optionalParameter
     * @return
     */
    @Override
    public SqlObject applyUnaryConditionWithParameterToSqlObject(SqlObject sqlObject, Operator operator, VtlExpression vtlExpression, VtlExpression parameter, List<VtlExpression> optionalParameter) {
        if (!operator.getValue().equals(Operator.MATCH_CHARACTERS.getValue())) {
            throw new UnsupportedOperationException(operator.getValue());
        }

        UnaryConditionWithParameter uc = new UnaryConditionWithParameter(UnaryConditionWithParameter.Op.REGEXP_LIKE, sqlObject, new ValueObject(((VtlConstantExpression) parameter).getVtlConstant().getValue()));
        return uc;
    }

    /**
     * Crea un case statement per simulare l'operatore sql in- not in
     *
     * @param sqlObject
     * @param operator
     * @param vtlInExpression
     * @return
     */
    @Override
    public SqlObject applyInNotInFunctionToSqlObject(SqlObject sqlObject, Operator operator, VtlInExpression vtlInExpression) {
        return createCaseStatement(createInNotInToSqlObject(sqlObject, operator, vtlInExpression), applicationDefaultValueTrue, applicationDefaultValueFalse);
    }

    /**
     * Crea il costrutto SQL in not In
     *
     * @param sqlObject
     * @param operator
     * @param vtlInExpression
     * @return
     */
    @Override
    public SqlObject applyInNotInConditionToSqlObject(SqlObject sqlObject, Operator operator, VtlInExpression vtlInExpression) {
        return createInNotInToSqlObject(sqlObject, operator, vtlInExpression);
    }

    /**
     * crea una In condition. La in condition viene costruita o su una lista di elementi o su una tabella di riferimento
     *
     * @param sqlObject
     * @param operator
     * @param vtlInExpression
     * @return
     */
    private InCondition createInNotInToSqlObject(SqlObject sqlObject, Operator operator, VtlInExpression vtlInExpression) {
        InCondition ic;

        if (vtlInExpression.getValueDomain() != null) {
            SelectQuery sq = new SelectQuery();
            sq.addCustomFromTable(vtlInExpression.getValueDomain());
            sq.addCustomColumns(new CustomSql(applicationDefaultValueDomainCode));

            ic = new InCondition(sqlObject, new Subquery(sq));
        } else {
            ic = new InCondition(sqlObject);
            for (VtlConstantExpression vtlConstantExpression : vtlInExpression.getVtlConstantList()) {
                ic.addObject(vtlConstantExpression.getVtlConstant().getValue());
            }
        }

        ic.setDisableParens(true);
        if (operator.getValue().equals(Operator.IN.getValue()))
            ic.setNegate(false);
        else
            ic.setNegate(true);
        return ic;
    }

    /**
     * Crea una case statement per rappresentare un between operator
     *
     * @param sqlObject
     * @param vtlBetweenExpression
     * @return
     */
    @Override
    public SqlObject applyBetweenFunctionToSqlObject(SqlObject sqlObject, VtlBetweenExpression vtlBetweenExpression) {
        return createCaseStatement(createBetweenConditionToSqlObject(sqlObject, vtlBetweenExpression), applicationDefaultValueTrue, applicationDefaultValueFalse);
    }

    /**
     * crea uno statement between da una betweenExpression
     *
     * @param sqlObject
     * @param vtlBetweenExpression
     * @return
     */
    @Override
    public SqlObject applyBetweenConditionToSqlObject(SqlObject sqlObject, VtlBetweenExpression vtlBetweenExpression) {
        return createBetweenConditionToSqlObject(sqlObject, vtlBetweenExpression);
    }

    private BetweenCondition createBetweenConditionToSqlObject(SqlObject sqlObject, VtlBetweenExpression vtlBetweenExpression) {
        BetweenCondition bc = new BetweenCondition(
                sqlObject,
                ((VtlConstantExpression) vtlBetweenExpression.getVtlfrom()).getVtlConstant().getValue(),
                ((VtlConstantExpression) vtlBetweenExpression.getVtlTo()).getVtlConstant().getValue()
        );

        return bc;
    }

    /**
     * Costruisce un sqlObject che rappresenta un operatore booleano binario. Applica l'ooperatore and or e xor.
     * Lo xor viene scomposto con combinaizoni di and e or
     *
     * @param sqlComponentLeft
     * @param sqlComponentRight
     * @param operator
     * @param vtlExpression
     * @return
     */
    @Override
    public SqlObject applyBooleanBinaryConditionToSqlObjects(SqlComponent sqlComponentLeft, SqlComponent sqlComponentRight, Operator operator, VtlBinaryExpression vtlExpression) {
        SqlObject sqlObjectLeft = applyDefaultBooleanBinaryConditionToSqlObject(sqlComponentLeft.getResult(), vtlExpression.getLeftExpression(), operator);
        SqlObject sqlObjectRight = applyDefaultBooleanBinaryConditionToSqlObject(sqlComponentRight.getResult(), vtlExpression.getRightExpression(), operator);

        if (operator.getValue().equals(Operator.XOR.getValue())) {
            NotCondition ncLeft = new NotCondition(sqlObjectLeft);
            NotCondition ncRight = new NotCondition(sqlObjectRight);
            ComboCondition ccAndLeft = new ComboCondition(ComboCondition.Op.AND);
            ccAndLeft.addCustomConditions(sqlObjectLeft, ncRight);
            ComboCondition ccAndRight = new ComboCondition(ComboCondition.Op.AND);
            ccAndRight.addCustomConditions(ncLeft, sqlObjectRight);
            ComboCondition ccOr = new ComboCondition(ComboCondition.Op.OR);
            ccOr.addCustomConditions(ccAndLeft, ccAndRight);
            return ccOr;
        } else {
            ComboCondition ce;

            if (operator.getValue().equals(Operator.AND.getValue())) {
                ce = new ComboCondition(ComboCondition.Op.AND);
            } else {
                ce = new ComboCondition(ComboCondition.Op.OR);
            }

            ce.addCustomCondition(sqlObjectLeft).addCustomCondition(sqlObjectRight).setDisableParens(true);

            return ce;
        }
    }

    /**
     * Crea un sqlObject che rappresenta una condizione booleana
     *
     * @param sqlComponentLeft
     * @param sqlComponentRight
     * @param operator
     * @param vtlExpression
     * @return
     */
    @Override
    public SqlObject applyComparisonBinaryConditionToSqlObjects(SqlComponent sqlComponentLeft, SqlComponent sqlComponentRight, Operator operator, VtlBinaryExpression vtlExpression) {
        SqlObject sqlObjectLeft = applyDefaultBooleanBinaryConditionToSqlObject(sqlComponentLeft.getResult(), vtlExpression.getLeftExpression(), operator);
        SqlObject sqlObjectRight = applyDefaultBooleanBinaryConditionToSqlObject(sqlComponentRight.getResult(), vtlExpression.getRightExpression(), operator);

        BinaryCondition bc = new BinaryCondition(operator.getValue(), sqlObjectLeft, sqlObjectRight);
        bc.setDisableParens(true);
        return bc;
    }

    /**
     * Applica una combo condition a due componenti
     *
     * @param sqlComponentLeft
     * @param sqlComponentRight
     * @param operator
     * @param vtlExpression
     * @return
     */
    @Override
    public SqlObject applyConditionalBinaryConditionToSqlObjects(SqlComponent sqlComponentLeft, SqlComponent sqlComponentRight, Operator operator, VtlBinaryExpression vtlExpression) {
        SqlObject sqlObjectLeft = applyDefaultBooleanBinaryConditionToSqlObject(sqlComponentLeft.getResult(), vtlExpression.getLeftExpression(), operator);
        SqlObject sqlObjectRight = applyDefaultBooleanBinaryConditionToSqlObject(sqlComponentRight.getResult(), vtlExpression.getRightExpression(), operator);

        ComboCondition cc = new ComboCondition(operator.getValue());
        cc.addCustomCondition(sqlObjectLeft).addCustomCondition(sqlObjectRight).setDisableParens(true);
        return cc;
    }

    /**
     * Applica una combo condition a due componenti senza utilizzare parentesi
     *
     * @param sqlComponentLeft
     * @param sqlComponentRight
     * @param operator
     * @param vtlExpression
     * @return
     */
    @Override
    public SqlObject applyNumericOrStringBinaryConditionToSqlObjects(SqlComponent sqlComponentLeft, SqlComponent sqlComponentRight, Operator operator, VtlBinaryExpression vtlExpression) {
        ComboExpression ce = new ComboExpression(operator.getValue(), sqlComponentLeft, sqlComponentRight);
        ce.setDisableParens(true);
        return ce;
    }

    /**
     * Applica una funzione booleana unaria su un espressione.
     *
     * @param sqlObject
     * @param vtlExpression
     * @return
     */
    @Override
    public SqlObject applyDefaultBooleanUnaryConditionToSqlObject(SqlObject sqlObject, VtlExpression vtlExpression) {
        //Trasforma dal boolean del DB in una condizione boolean
        if (vtlExpression.getType() != null && vtlExpression.getType().equalsIgnoreCase(VtlComponentId.VTL_OBJECT_TYPE)) {
            sqlObject = new BinaryCondition(BinaryCondition.Op.EQUAL_TO, sqlObject, applicationDefaultValueTrue);
        }

        //Trasforma dal boolean in una condizione boolean
        if (vtlExpression.getType() != null && vtlExpression.getType().equalsIgnoreCase(VtlConstantExpression.VTL_OBJECT_TYPE)) {
            VtlConstantExpression vtlConstantExpression = (VtlConstantExpression) vtlExpression;
            if (vtlConstantExpression.getVtlConstant().getType().equalsIgnoreCase(VtlBoolean.VTL_OBJECT_TYPE)) {
                if ((Boolean) vtlConstantExpression.getVtlConstant().getValue()) {
                    sqlObject = new BinaryCondition(BinaryCondition.Op.EQUAL_TO, applicationDefaultValueTrue, applicationDefaultValueTrue);
                } else {
                    sqlObject = new BinaryCondition(BinaryCondition.Op.EQUAL_TO, applicationDefaultValueFalse, applicationDefaultValueTrue);
                }
            }
        }

        return sqlObject;
    }

    private SqlObject applyDefaultBooleanBinaryConditionToSqlObject(SqlObject sqlObject, VtlExpression vtlExpression, Operator operator) {
        //Trasforma dal boolean del DB in una condizione boolean (solo per operatori boolean)
        if (vtlExpression.getType() != null && vtlExpression.getType().equalsIgnoreCase(VtlComponentId.VTL_OBJECT_TYPE) && operator.isBooleanOperator()) {
            sqlObject = new BinaryCondition(BinaryCondition.Op.EQUAL_TO, sqlObject, applicationDefaultValueTrue);
        }

        //Trasforma il boolean nella stringa default dell'installazione DB
        if (vtlExpression.getType() != null && vtlExpression.getType().equalsIgnoreCase(VtlConstantExpression.VTL_OBJECT_TYPE)) {
            VtlConstantExpression vtlConstantExpression = (VtlConstantExpression) vtlExpression;
            if (vtlConstantExpression.getVtlConstant().getType().equalsIgnoreCase(VtlBoolean.VTL_OBJECT_TYPE)) {
                if (operator.isBooleanOperator()) {
                    if ((Boolean) vtlConstantExpression.getVtlConstant().getValue()) {
                        sqlObject = new BinaryCondition(BinaryCondition.Op.EQUAL_TO, applicationDefaultValueTrue, applicationDefaultValueTrue);
                    } else {
                        sqlObject = new BinaryCondition(BinaryCondition.Op.EQUAL_TO, applicationDefaultValueFalse, applicationDefaultValueTrue);
                    }
                } else {
                    if ((Boolean) vtlConstantExpression.getVtlConstant().getValue()) {
                        sqlObject = new ValueObject(applicationDefaultValueTrue);
                    } else {
                        sqlObject = new ValueObject(applicationDefaultValueFalse);
                    }
                }
            }
        }

        return sqlObject;
    }

    /**
     * Crea un SqlObject che rappresenta una funzione analitica. Il metodo aggiunge, se previsti partioning, windowing e ordering
     * Gli operatori implementati sono Rank, lag e Lead
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

        if (operator.getValue().equals(Operator.LAG.getValue()) || operator.getValue().equals(Operator.LEAD.getValue()) && vtlAnalyticFunctionExpression.getOffset() != null) {
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

        return fc;
    }

    protected FrameBound getFrameBound(VtlWindowingLimit vtlWindowingLimit) {
        switch (vtlWindowingLimit.getVtlLimitType()) {
            case UNBOUNDED_PRECEDING:
                return FrameBound.UNBOUNDED_PRECEDING;
            case UNBOUNDED_FOLLOWING:
                return FrameBound.UNBOUNDED_FOLLOWING;
            case PRECENDING:
                return FrameBound.boundedPreceding(vtlWindowingLimit.getValue());
            case FOLLOWING:
                return FrameBound.boundedFollowing(vtlWindowingLimit.getValue());
            default:
                return FrameBound.CURRENT_ROW;
        }
    }

    /**
     * Crea un oggetto SQL contenente un numero. Viene utilizzato per dare un ordine di creazione della query in alcune condizioni
     *
     * @param priority
     * @return
     */
    @Override
    public SqlObject createPrioritySqlObjects(Integer priority) {
        CustomSql customSql = new CustomSql(priority);
        return customSql;
    }

    /**
     * Crea una query che contiene una union. Il metodo costruisce una query che prevede la precedenza di valutazione delle colonne
     * secondo le regole previste da VTL.
     *
     * @param sqlDatasetList
     * @param operator
     * @return
     */
    @Override
    public SqlObject createUnionQueryForSetUnnary(List<SqlDataset> sqlDatasetList, Operator operator) {
        UnionQuery unionQuery = UnionQuery.unionAll();
        for (int i = 0; i < sqlDatasetList.size(); i++) {
            unionQuery.addQueries(createSelectFromSqlDataset(sqlDatasetList.get(i)));
        }

        SelectQuery finalQuery = new SelectQuery();
        SelectQuery denseQuery = new SelectQuery();

        for (SqlComponent sqlComponent : sqlDatasetList.get(0).getComponentList()) {
            if (!sqlComponent.getAliasName().equalsIgnoreCase(ConstantUtility.COLUMN_PRIORITY)) {
                denseQuery.addCustomColumns(new CustomSql(sqlComponent.getAliasName()));
                finalQuery.addCustomColumns(new CustomSql(sqlComponent.getAliasName()));
            }
        }

        FunctionCall dense = FunctionCall.denseRank();
        FunctionCall cnt = FunctionCall.countAll();
        WindowDefinitionClause wdcDense = new WindowDefinitionClause();
        WindowDefinitionClause wdcCnt = new WindowDefinitionClause();
        for (SqlComponent sqlComponent : sqlDatasetList.get(0).getComponentList()) {
            if (sqlComponent.getVtlComponent() != null && sqlComponent.getVtlComponent().getVtlComponentRole() == VtlComponentRole.IDENTIFIER) {
                wdcDense.addPartitionColumns(new CustomSql(sqlComponent.getAliasName()));
                wdcCnt.addPartitionColumns(new CustomSql(sqlComponent.getAliasName()));
            }
        }
        wdcDense.addOrderings(ConstantUtility.COLUMN_PRIORITY);
        dense.setWindow(wdcDense);
        cnt.setWindow(wdcCnt);

        denseQuery.addAliasedColumn(dense, "DENSE");
        if (operator.getValue().equals(Operator.INTERSECT.getValue())) {
            denseQuery.addAliasedColumn(cnt, "CNT");
        }
        denseQuery.addCustomFromTable(applParenthesesToSqlObject(unionQuery) + " DT");

        finalQuery.addCustomFromTable(applParenthesesToSqlObject(denseQuery) + " FT");
        finalQuery.addCondition(BinaryCondition.equalTo(new CustomSql("DENSE"), 1));
        if (operator.getValue().equals(Operator.INTERSECT.getValue())) {
            finalQuery.addCondition(BinaryCondition.equalTo(new CustomSql("CNT"), sqlDatasetList.size()));
        }

        return finalQuery;
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

        CustomSql executeProcedure = new CustomSql("EXECUTE " + getSqLOperator(operator.getValue()) + "('" + inTableName + "','" + identifierList.stream().collect(Collectors.joining(", ")) + "','" + timeIdentifier + "','" + limitsMethod + "')");

        return executeProcedure;
    }

    /**
     * Genera il sql della ExistIn expression
     *
     * @param identifierCommonList
     * @param subQuery
     * @param neagate
     * @return
     */
    @Override
    public InCondition getExistInCondition(List<SqlComponent> identifierCommonList, SelectQuery subQuery, boolean neagate) {
        return new InCondition(applParenthesesToSqlObject(
                new CustomSql(
                        identifierCommonList.stream()
                                .map(identifier -> identifier.getResult().toString())
                                .collect(Collectors.joining(", "))
                )
        ), subQuery).setNegate(neagate);
    }

    /**
     * Genera un sql Object che rappresenta la funzione di flow to stock
     *
     * @param sqlObject
     * @param identifierList
     * @param timeIdentifier
     * @param operator
     * @return
     */
    @Override
    public SqlObject applyFlowToStockFunctionToSqlObject(SqlObject sqlObject, List<SqlComponent> identifierList, SqlObject timeIdentifier, Operator operator) {
        FunctionCall fc = (FunctionCall) applyUnaryFunctionToSqlObject(sqlObject, null, null, Operator.SUM);

        FunctionCall periodIndicator = (FunctionCall) applyUnaryFunctionToSqlObject(timeIdentifier, null, null, Operator.PERIOD_INDICATOR);

        fc.setWindow(applyWindowClause(identifierList, periodIndicator, timeIdentifier));

        return fc;
    }

    /**
     * Genera un sql Object che rappresenta la funzione di stock to flow
     *
     * @param sqlObject
     * @param identifierList
     * @param timeIdentifier
     * @param operator
     * @return
     */
    @Override
    public SqlObject applyStockToFlowFunctionToSqlObject(SqlObject sqlObject, List<SqlComponent> identifierList, SqlObject timeIdentifier, Operator operator) {
        FunctionCall fc = (FunctionCall) applyUnaryFunctionToSqlObject(sqlObject, null, null, Operator.LAG);
        fc.addNumericValueParam(1);
        fc.addNumericValueParam(0);

        FunctionCall periodIndicator = (FunctionCall) applyUnaryFunctionToSqlObject(timeIdentifier, null, null, Operator.PERIOD_INDICATOR);

        fc.setWindow(applyWindowClause(identifierList, periodIndicator, timeIdentifier));
        ComboExpression ce = new ComboExpression(ComboExpression.Op.SUBTRACT);
        ce.addExpression(sqlObject).addExpression(fc);

        return ce;
    }

    /**
     * Applica una condizione di uguaglianza sui componenti comuni. Viene usato principalmente per costrure la where condition dell join
     *
     * @param commonComponents
     * @return
     */
    @Override
    public List<BinaryCondition> applyCommonComponentsCondition(LinkedList<LinkedList<SqlComponent>> commonComponents) {
        List<BinaryCondition> conditions = new ArrayList<>();
        for (LinkedList<SqlComponent> components : commonComponents) {
            SqlComponent firstComponent = components.removeFirst();
            for (SqlComponent sqlComponent : components) {
                conditions.add(
                        new BinaryCondition(BinaryCondition.Op.EQUAL_TO, firstComponent.getResult(), sqlComponent.getResult())
                );
            }
        }
        return conditions;
    }

    /**
     * aggiunge delle parentesi al SQl object
     *
     * @param sqlObject
     * @return
     */
    @Override
    public SqlObject applParenthesesToSqlObject(SqlObject sqlObject) {
        CustomSql customSql = new CustomSql("(" + sqlObject + ")");

        return customSql;
    }

    /**
     * Genera una SQL object che invoca il currentDate
     *
     * @param operator
     * @return
     */
    @Override
    public SqlObject applyCurrentDateToSqlObjects(Operator operator) {
        return new CustomSql(getSqLOperator(operator.getValue()));
    }

    /**
     * Genera un SQL object che rappresenta uno scalare
     *
     * @param vtlConstant
     * @return
     */
    @Override
    public SqlObject getConstantSql(VtlConstant vtlConstant) {
        if (vtlConstant instanceof VtlBoolean) {
            String defaultValue;
            if (((VtlBoolean) vtlConstant).getValue()) {
                defaultValue = applicationDefaultValueTrue;
            } else {
                defaultValue = applicationDefaultValueFalse;
            }

            try {
                Double.parseDouble(defaultValue);
                return new CustomSql(defaultValue);
            } catch (Exception e) {
                return new CustomSql("'" + vtlConstant.getValue() + "'");
            }

        }

        if (vtlConstant instanceof VtlInteger || vtlConstant instanceof VtlFloat) {
            return new CustomSql(vtlConstant.getValue());
        }

        return new CustomSql("'" + vtlConstant.getValue() + "'");
    }

    /**
     * genera una if condition da utilizzare in una query
     *
     * @param sqlObjectCondition
     * @param sqlObjectThen
     * @param sqlObjectElse
     * @param isWhereCondition
     * @return
     */
    @Override
    public SqlObject applyIfToSqlObject(SqlObject sqlObjectCondition, SqlObject sqlObjectThen, SqlObject sqlObjectElse, Boolean isWhereCondition) {

        if (isWhereCondition) {
            return createCaseStatement(sqlObjectCondition, sqlObjectThen, sqlObjectElse);

        } else {
            BinaryCondition bc = BinaryCondition.equalTo(sqlObjectCondition, applicationDefaultValueTrue);
            return createCaseStatement(bc, sqlObjectThen, sqlObjectElse);

        }

    }

    /**
     * genera un case statement
     *
     * @param condition
     * @param resultThen
     * @param resultElse
     * @return
     */
    @Override
    public SqlObject createCaseStatement(SqlObject condition, Object resultThen, Object resultElse) {
        CaseStatement caseStatement = new CaseStatement();
        caseStatement.addWhen(condition, resultThen);
        if (resultElse != null)
            caseStatement.addElse(resultElse);
        return caseStatement;
    }

    /**
     * Genera una select di pivot
     *
     * @param identifier
     * @param measure
     * @param sqlDatasetFrom
     * @param sqlDatasetUnpivot
     * @return
     */
    @Override
    public SqlObject createSelectForUnpivot(VtlComponentId identifier, VtlComponentId measure, SqlDataset sqlDatasetFrom, SqlDataset sqlDatasetUnpivot) {

        UnionQuery unionQuery = UnionQuery.union();

        sqlDatasetUnpivot.setUpAlias(dbTableUtilityService.getDbSpec().getNextAlias(), sqlDatasetUnpivot.getSqlTables().get(0).getVtlDataset());

        for (SqlComponent sqlComponent : sqlDatasetFrom.getComponentList()){
            SelectQuery selectQuery = new SelectQuery();
            if (sqlComponent.getVtlComponent().getVtlComponentRole() == VtlComponentRole.MEASURE){
                for(SqlComponent sqlComponentUnpivot : sqlDatasetUnpivot.getComponentList()){
                    if (sqlComponentUnpivot.getVtlComponent().getVtlComponentRole() == VtlComponentRole.IDENTIFIER && !sqlComponentUnpivot.getAliasName().equalsIgnoreCase(identifier.getComponentName())) {
                        selectQuery.addCustomColumns(new CustomSql(sqlComponentUnpivot.getAliasName()));
                    }
                }
                selectQuery.addCustomFromTable(sqlDatasetFrom.getSqlTables().get(0).getVtlDataset().getName());
                String selectCustom = ",";
                selectCustom += "'" + sqlComponent.getAliasName() + "'" + " as " + identifier.getComponentName() + "," + sqlComponent.getAliasName() + " as " + measure.getComponentName();
                selectQuery.addCustomization(SelectQuery.Hook.FROM, HookType.BEFORE, selectCustom);
                unionQuery.addQueries(selectQuery);
            }
        }
        return unionQuery;
    }

    @Override
    public SelectQuery createSelectForPivot(VtlComponentId identifier, VtlComponentId measure, List<VtlConstantExpression> constantExpressions, SqlDataset sqlDatasetFrom, SqlDataset sqlDatasetPivot) {
        return null;
    }

    /**
     * Genera un Window clause da usare nel partizionamento
     *
     * @param sqlComponents
     * @param partitionColums
     * @param timeIdentifier
     * @return
     */
    @Override
    public SqlObject applyWindowClause(List<SqlComponent> sqlComponents, SqlObject partitionColums, SqlObject timeIdentifier) {
        WindowDefinitionClause wdc = new WindowDefinitionClause();
        for (SqlComponent sqlComponent : sqlComponents) {
            wdc.addPartitionColumns(sqlComponent.getResult());
        }
        wdc.addPartitionColumns(partitionColums);
        wdc.addOrdering(timeIdentifier, OrderObject.Dir.ASCENDING);
        return wdc;
    }

    /**
     * Costruisce la model per la VtlHiearrchyExpression
     *
     * @param vtlHierarchyExpression
     * @return
     */
    @Override
    public SqlObject buildModel(VtlHierarchyExpression vtlHierarchyExpression) {
        String model = "MODEL ";
        model = model + "RETURN UPDATED ROWS ";
        FunctionCall partitionBy = FunctionCall.customFunction("PARTITION BY ");
        List<VtlComponent> partitionByIdentifiers = componentUtilityService.copyComponents(vtlHierarchyExpression.getVtlOperandExpression().getResultExpression().getResult().getIdentifiers());

        FunctionCall dimensionBy = FunctionCall.customFunction("DIMENSION BY ");

        partitionByIdentifiers = buildPartitionAndDimension(dimensionBy, partitionBy, partitionByIdentifiers, vtlHierarchyExpression.getRuleComponent(), vtlHierarchyExpression.getComponentIds());

        String measureName = vtlHierarchyExpression.getVtlOperandExpression().getResultExpression().getResult().getMeasures().get(0).getName();
        FunctionCall measures = FunctionCall.customFunction("MEASURES ");
        measures.addCustomParams(new CustomSql(measureName + " AS ME_DS "), new CustomSql(measureName + " AS ME_RULE "));
        if (vtlHierarchyExpression.getValidationMode() != ValidationMode.ALWAYS_ZERO && vtlHierarchyExpression.getValidationMode() != ValidationMode.ALWAYS_NULL) {
            measures.addCustomParams(new CustomSql(" 'N' AS FLAG "));
        }
        if (!partitionByIdentifiers.isEmpty())
            model = model + partitionBy.toString() + " ";
        model = model + dimensionBy.toString() + " ";
        model = model + measures.toString() + " ";
        String ruleOrder = "";
        if (vtlHierarchyExpression.getInputMode() == InputMode.RULE || vtlHierarchyExpression.getInputMode() == InputMode.RULE_PRIORITY)
            ruleOrder = "AUTOMATIC ORDER ";
        FunctionCall rules = FunctionCall.customFunction("RULES UPSERT ALL " + ruleOrder);

        List<String> items = new ArrayList<>();
        if ((vtlHierarchyExpression.getValidationMode().equals(ValidationMode.ALWAYS_ZERO)
                || vtlHierarchyExpression.getValidationMode().equals(ValidationMode.PARTIAL_ZERO)
                || vtlHierarchyExpression.getValidationMode().equals(ValidationMode.NON_ZERO))
                &&
                (vtlHierarchyExpression.getInputMode().equals(InputMode.RULE)
                        || vtlHierarchyExpression.getInputMode().equals(InputMode.RULE_PRIORITY))) {
            for (VtlHRRule vtlHRRule : vtlHierarchyExpression.getVtlHierarchicalRuleset().getVtlHRRules()) {
                buildPreZeroRule(rules, vtlHRRule.getVtlCodeItemRelation(), items);
            }
        }

        for (VtlHRRule vtlHRRule : vtlHierarchyExpression.getVtlHierarchicalRuleset().getVtlHRRules()) {
            if (vtlHRRule.getVtlCodeItemRelation().getComparator() == Operator.EQUAL_TO) {
                SqlObject sqlObject = buildHierarchyRule(
                        vtlHRRule.getVtlCodeItemRelation(),
                        vtlHierarchyExpression.getInputMode(),
                        vtlHierarchyExpression.getRuleComponent(),
                        vtlHierarchyExpression.getComponentIds(),
                        vtlHierarchyExpression.getValidationMode()
                );
                rules.addCustomParams(sqlObject);
            }
        }
        for (VtlHRRule vtlHRRule : vtlHierarchyExpression.getVtlHierarchicalRuleset().getVtlHRRules()) {
            if (vtlHRRule.getVtlCodeItemRelation().getComparator() == Operator.EQUAL_TO && (vtlHierarchyExpression.getValidationMode() == ValidationMode.NON_NULL || vtlHierarchyExpression.getValidationMode() == ValidationMode.NON_ZERO || vtlHierarchyExpression.getValidationMode() == ValidationMode.PARTIAL_ZERO || vtlHierarchyExpression.getValidationMode() == ValidationMode.PARTIAL_NULL)) {
                SqlObject sqlObject = buildFlagRule(vtlHRRule.getVtlCodeItemRelation(), vtlHierarchyExpression.getInputMode(), vtlHierarchyExpression.getValidationMode(), vtlHierarchyExpression.getRuleComponent(), vtlHierarchyExpression.getComponentIds());
                rules.addCustomParams(sqlObject);
            }
        }

        model = model + rules.toString() + " ";
        return new CustomSql(model);
    }


    private void buildPreZeroRule(FunctionCall rule, VtlCodeItemRelation vtlCodeItemRelation, List<String> items) {
        if (!items.contains(vtlCodeItemRelation.getLeftCodeItem().toUpperCase())) {
            items.add(vtlCodeItemRelation.getLeftCodeItem().toUpperCase());
        }
        for (VtlCodeItem vtlCodeItem : vtlCodeItemRelation.getVtlCodeItems()) {
            if (!items.contains(vtlCodeItem.getCodeItem().toUpperCase())) {
                items.add(vtlCodeItem.getCodeItem().toUpperCase());
                String preZeroRule = " ME_RULE['" + vtlCodeItem.getCodeItem() + "', ANY] = PRESENTV(ME_DS['" + vtlCodeItem.getCodeItem() + "', CV()], ME_DS['" + vtlCodeItem.getCodeItem() + "', CV()],0)";
                rule.addCustomParams(new CustomSql(preZeroRule));
            }
        }
    }

    private void buildPartitionAndDimensionCheck(FunctionCall dimensionBy, FunctionCall partitionBy, List<VtlComponent> partitionByIdentifiers, VtlComponentId vtlRuleComponent, List<VtlComponentId> vtlComponentIds) {
        dimensionBy.addCustomParams(new CustomSql(vtlRuleComponent.getComponentName()));
        for (VtlComponentId vtlComponentId : vtlComponentIds) {
            dimensionBy.addCustomParams(new CustomSql(vtlComponentId.getComponentName()));
            partitionByIdentifiers = componentUtilityService.removeComponentWithName(partitionByIdentifiers, vtlComponentId.getComponentName());
        }

        partitionByIdentifiers = componentUtilityService.removeComponentWithName(partitionByIdentifiers, vtlRuleComponent.getComponentName());
        for (VtlComponent vtlComponent : partitionByIdentifiers) {
            partitionBy.addCustomParams(new CustomSql(vtlComponent.getName()));
        }
    }


    private List<VtlComponent> buildPartitionAndDimension(FunctionCall dimensionBy, FunctionCall partitionBy, List<VtlComponent> partitionByIdentifiers, VtlComponentId vtlRuleComponent, List<VtlComponentId> vtlComponentIds) {
        dimensionBy.addCustomParams(new CustomSql(vtlRuleComponent.getComponentName()));
        for (VtlComponentId vtlComponentId : vtlComponentIds) {
            dimensionBy.addCustomParams(new CustomSql(vtlComponentId.getComponentName()));
            partitionByIdentifiers = componentUtilityService.removeComponentWithName(partitionByIdentifiers, vtlComponentId.getComponentName());
        }

        partitionByIdentifiers = componentUtilityService.removeComponentWithName(partitionByIdentifiers, vtlRuleComponent.getComponentName());
        for (VtlComponent vtlComponent : partitionByIdentifiers) {
            partitionBy.addCustomParams(new CustomSql(vtlComponent.getName()));
        }
        return partitionByIdentifiers;
    }

    private String buildIgnoreNav(ValidationMode validationMode) {
        if (validationMode == ValidationMode.ALWAYS_ZERO ||
                validationMode == ValidationMode.PARTIAL_ZERO ||
                validationMode == ValidationMode.NON_ZERO) {
            return "IGNORE NAV ";
        }
        return "";
    }

    /**
     * Costruisce la model per la check hierarhcy
     *
     * @param vtlCheckHierarchy
     * @return
     */
    @Override
    public SqlObject buildCheckModel(VtlCheckHierarchy vtlCheckHierarchy) {
        String model = "MODEL ";
        model = model + buildIgnoreNav(vtlCheckHierarchy.getValidationMode());
        model = model + "RETURN UPDATED ROWS ";
        FunctionCall partitionBy = FunctionCall.customFunction("PARTITION BY ");
        FunctionCall dimensionBy = FunctionCall.customFunction("DIMENSION BY ");
        dimensionBy.addCustomParams(new CustomSql("RULEID"));
        FunctionCall measures = FunctionCall.customFunction("MEASURES ");
        List<VtlComponent> partitionByIdentifiers = componentUtilityService.copyComponents(vtlCheckHierarchy.getVtlVarId().getResultExpression().getResult().getIdentifiers());
        buildPartitionAndDimensionCheck(dimensionBy, partitionBy, partitionByIdentifiers, vtlCheckHierarchy.getRuleComponent(), vtlCheckHierarchy.getConditionComponents());
        partitionBy.addCustomParams(new CustomSql("ERRORCODE"));
        partitionBy.addCustomParams(new CustomSql("ERRORLEVEL"));

        model = model + partitionBy.toString() + " ";
        model = model + dimensionBy.toString() + " ";
        String measureName = vtlCheckHierarchy.getVtlVarId().getResultExpression().getResult().getMeasures().get(0).getName();
        measures.addCustomParams(
                new CustomSql(measureName + " AS ME_DS "),
                new CustomSql(measureName + " AS ME_RULE "),
                new CustomSql("CAST('TRUE' AS VARCHAR2(10)) AS BOOL_VAR "),
                new CustomSql("0 AS IMBALANCE ")

        );
        if (vtlCheckHierarchy.getValidationMode() == ValidationMode.ALWAYS_NULL ||
                vtlCheckHierarchy.getValidationMode() == ValidationMode.ALWAYS_ZERO ||
                vtlCheckHierarchy.getValidationMode() == ValidationMode.NON_ZERO ||
                vtlCheckHierarchy.getValidationMode() == ValidationMode.PARTIAL_ZERO ||
                vtlCheckHierarchy.getValidationMode() == ValidationMode.PARTIAL_NULL) {

            measures.addCustomParams(new CustomSql("'N' AS FLG_ONE"));
        }
        if (vtlCheckHierarchy.getValidationMode() == ValidationMode.ALWAYS_NULL ||
                vtlCheckHierarchy.getValidationMode() == ValidationMode.ALWAYS_ZERO ||
                vtlCheckHierarchy.getValidationMode() == ValidationMode.PARTIAL_NULL ||
                vtlCheckHierarchy.getValidationMode() == ValidationMode.NON_NULL ||
                vtlCheckHierarchy.getValidationMode() == ValidationMode.PARTIAL_ZERO) {

            measures.addCustomParams(new CustomSql("'N' AS FLG_ALL"));
        }
        if (vtlCheckHierarchy.getInputMode() == InputMode.DATASET_PRIORITY) {
            measures.addCustomParams(new CustomSql(measureName + " AS ME_TO_USE "));
        }
        model = model + measures.toString() + " ";
        FunctionCall rules = FunctionCall.customFunction("RULES ");
        for (VtlHRRule vtlHRRule : vtlCheckHierarchy.getVtlHierarchicalRuleset().getVtlHRRules()) {
            if (vtlHRRule.getVtlCodeItemRelation().getComparator() == Operator.EQUAL_TO) {
                rules.addCustomParams(buildRulePriority(vtlHRRule, vtlCheckHierarchy.getConditionComponents(), vtlCheckHierarchy.getRuleComponent(), vtlCheckHierarchy.getValidationMode()));
                if (vtlCheckHierarchy.getInputMode() == InputMode.DATASET_PRIORITY) {
                    rules.addCustomParams(buildMeToUse(vtlHRRule, vtlCheckHierarchy.getConditionComponents()));
                }
            }
            rules.addCustomParams(buildDefaultFlag(BOOL_VAR, getApplicationDefaultValueTrue(), vtlHRRule, vtlCheckHierarchy.getConditionComponents()));
            rules.addCustomParams(buildBoolVar(vtlHRRule, vtlCheckHierarchy.getConditionComponents(), vtlCheckHierarchy.getRuleComponent(), vtlCheckHierarchy.getInputMode(), vtlCheckHierarchy.getValidationMode()));
            rules.addCustomParams(buildDefaultFlag("IMBALANCE", null, vtlHRRule, vtlCheckHierarchy.getConditionComponents()));
            rules.addCustomParams(buildImbalance(vtlHRRule, vtlCheckHierarchy.getConditionComponents(), vtlCheckHierarchy.getRuleComponent(), vtlCheckHierarchy.getInputMode(), vtlCheckHierarchy.getValidationMode()));
            if (vtlCheckHierarchy.getValidationMode() == ValidationMode.ALWAYS_NULL ||
                    vtlCheckHierarchy.getValidationMode() == ValidationMode.ALWAYS_ZERO ||
                    vtlCheckHierarchy.getValidationMode() == ValidationMode.NON_ZERO ||
                    vtlCheckHierarchy.getValidationMode() == ValidationMode.PARTIAL_ZERO ||
                    vtlCheckHierarchy.getValidationMode() == ValidationMode.PARTIAL_NULL) {
                rules.addCustomParams(buildDefaultFlag("FLG_ONE", "Y", vtlHRRule, vtlCheckHierarchy.getConditionComponents()));
                rules.addCustomParams(buildFlagNonZero("FLG_ONE", " OR ", vtlHRRule, vtlCheckHierarchy.getConditionComponents(), vtlCheckHierarchy.getInputMode(), vtlCheckHierarchy.getValidationMode()));
            }
            if (vtlCheckHierarchy.getValidationMode() == ValidationMode.ALWAYS_NULL ||
                    vtlCheckHierarchy.getValidationMode() == ValidationMode.ALWAYS_ZERO ||
                    vtlCheckHierarchy.getValidationMode() == ValidationMode.PARTIAL_NULL ||
                    vtlCheckHierarchy.getValidationMode() == ValidationMode.NON_NULL ||
                    vtlCheckHierarchy.getValidationMode() == ValidationMode.PARTIAL_ZERO) {
                rules.addCustomParams(buildDefaultFlag("FLG_ALL", "Y", vtlHRRule, vtlCheckHierarchy.getConditionComponents()));
                rules.addCustomParams(buildFlagNonZero("FLG_ALL", " AND ", vtlHRRule, vtlCheckHierarchy.getConditionComponents(), vtlCheckHierarchy.getInputMode(), vtlCheckHierarchy.getValidationMode()));
            }

        }
        model = model + rules.toString() + " ";
        return new CustomSql(model);
    }

    private SqlObject buildMeToUse(VtlHRRule vtlHRRule, List<VtlComponentId> antecedentComponents) {
        FunctionCall dimensionBy = FunctionCall.customFunction("PRESENTNNV");
        String leftCondition = buildLeftItem(ME_TO_USE, vtlHRRule, antecedentComponents);
        String condition = buildLeftItem(ME_DS, vtlHRRule, antecedentComponents);

        String elseCondition = buildLeftItem(ME_RULE, vtlHRRule, antecedentComponents);
        dimensionBy.addCustomParams(new CustomSql(condition), new CustomSql(condition), new CustomSql(elseCondition));
        return new CustomSql(leftCondition + " = " + dimensionBy.toString());
    }

    private SqlObject buildFlagNonZero(String ruleName, String conditional, VtlHRRule vtlHRRule, List<VtlComponentId> antecedentComponents, InputMode inputMode, ValidationMode validationMode) {
        String buildFlag = "";
        buildFlag = buildFlag + buildLeftItem(ruleName, vtlHRRule, antecedentComponents);
        buildFlag = buildFlag + " = ";
        String condition = "";
        condition = buildInsideCondition(antecedentComponents, vtlHRRule.getVtlCodeItemRelation().getLeftCodeItem(), inputMode, validationMode);
        for (VtlCodeItem vtlCodeItem : vtlHRRule.getVtlCodeItemRelation().getVtlCodeItems()) {
            condition = condition + conditional + buildInsideCondition(antecedentComponents, vtlCodeItem.getCodeItem(), inputMode, validationMode);
        }
        SqlObject sqlObject = createCaseStatement(new CustomSql(condition), new CustomSql("'Y'"), new CustomSql("'N'"));
        buildFlag = buildFlag + sqlObject.toString();
        return new CustomSql(buildFlag);
    }

    private String buildInsideCondition(List<VtlComponentId> antecedentComponents, String value, InputMode inputMode, ValidationMode validationMode) {
        String ruleName = ME_DS;
        if (inputMode == InputMode.DATASET_PRIORITY) {
            ruleName = ME_TO_USE;
        }
        String insideCondition = ruleName + "[ CV(), '" + value + "'";
        for (VtlComponentId vtlComponentId : antecedentComponents) {
            insideCondition = insideCondition + ", " + "CV()";
        }
        String confronto = " IS NOT NULL ";
        if (validationMode == ValidationMode.NON_ZERO) {
            confronto = " <> 0 ";
        }
        insideCondition = insideCondition + "]" + confronto;
        return insideCondition;
    }

    private SqlObject buildImbalance(VtlHRRule vtlHRRule, List<VtlComponentId> antecedentComponents, VtlComponentId vtlComponentRule, InputMode inputMode, ValidationMode validationMode) {
        String ruleImbalance = "";
        ruleImbalance = ruleImbalance + buildLeftItem("IMBALANCE", vtlHRRule, antecedentComponents);
        String condition = buildHeadRightItem(BOOL_VAR, vtlHRRule, antecedentComponents) + " = '" + getApplicationDefaultValueFalse() + "'";
        String ruleName = ME_DS;
        if (inputMode == InputMode.DATASET_PRIORITY) {
            ruleName = ME_TO_USE;
        }
        String thenCondition = buildRightItem(true, "-", ruleName, vtlHRRule, antecedentComponents, vtlComponentRule, validationMode);
        SqlObject selectCase = createCaseStatement(
                new CustomSql(condition),
                new CustomSql(thenCondition),
                new CustomSql("NULL")
        );
        ruleImbalance = ruleImbalance + " = " + selectCase.toString();
        return new CustomSql(ruleImbalance);
    }

    private SqlObject buildBoolVar(VtlHRRule vtlHRRule, List<VtlComponentId> antecedentComponents, VtlComponentId vtlComponentRule, InputMode inputMode, ValidationMode validationMode) {
        String rulePriority;
        String leftItem = buildLeftItem(BOOL_VAR, vtlHRRule, antecedentComponents);
        String comparator = "=";
        String ruleName = ME_DS;
        if (inputMode == InputMode.DATASET_PRIORITY) {
            ruleName = ME_TO_USE;
        }
        String rightItem = buildRightItem(true, null, ruleName, vtlHRRule, antecedentComponents, vtlComponentRule, validationMode);

        SqlObject caseStatement = createCaseStatement(new CustomSql(rightItem), getApplicationDefaultValueTrue(), getApplicationDefaultValueFalse());
        rulePriority = leftItem + " " + comparator + " " + caseStatement.toString();
        return new CustomSql(rulePriority);
    }


    private SqlObject buildRulePriority(VtlHRRule vtlHRRule, List<VtlComponentId> antecedentComponents, VtlComponentId vtlComponentRule, ValidationMode validationMode) {
        String rulePriority;
        String leftItem = buildLeftItem(ME_RULE, vtlHRRule, antecedentComponents);
        String comparator = "=";
        String rightItem = buildRightItem(false, null, ME_RULE, vtlHRRule, antecedentComponents, vtlComponentRule, validationMode);
        rulePriority = leftItem + " " + comparator + " " + rightItem;
        return new CustomSql(rulePriority);
    }

    private String buildHeadRightItem(String ruleName, VtlHRRule vtlHRRule, List<VtlComponentId> antecedentComponents) {
        String rightItem = "";
        rightItem = rightItem + " " + ruleName + "[ ";
        rightItem = rightItem + "CV() ";
        rightItem = rightItem + ", '" + vtlHRRule.getVtlCodeItemRelation().getLeftCodeItem() + "' ";
        for (VtlComponentId vtlComponentId : antecedentComponents) {

            rightItem = rightItem + CV;
        }
        rightItem = rightItem + " ]";
        return rightItem;
    }

    private String buildRightItem(boolean buildLeft, String comparator, String ruleName, VtlHRRule vtlHRRule, List<VtlComponentId> antecedentComponents, VtlComponentId vtlComponentRule, ValidationMode validationMode) {
        String rightItem = "";
        if (buildLeft) {
            rightItem = buildHeadRightItem(ruleName, vtlHRRule, antecedentComponents);
            if (comparator == null)
                comparator = vtlHRRule.getVtlCodeItemRelation().getComparator().getValue();
            rightItem = rightItem + " " + comparator + " ";
        }
        rightItem = rightItem + "(";
        String singleRightItem = "";
        for (VtlCodeItem vtlCodeItem : vtlHRRule.getVtlCodeItemRelation().getVtlCodeItems()) {
            if (vtlCodeItem.getOperator() != null) {
                singleRightItem = singleRightItem + vtlCodeItem.getOperator().getValue();
            }
            singleRightItem = singleRightItem + " " + ruleName + "[ ";
            singleRightItem = singleRightItem + "CV() ";
            singleRightItem = singleRightItem + ",  '" + vtlCodeItem.getCodeItem() + "' ";
            boolean buildCase = true;
            for (VtlComponentId vtlComponentId : antecedentComponents) {
                if (vtlCodeItem.getCondition() != null) {
                    VtlComparisonBinaryExpression vtlComparisonBinaryExpression = (VtlComparisonBinaryExpression) vtlCodeItem.getCondition();
                    if (vtlComparisonBinaryExpression.getOperator().equals(Operator.EQUAL_TO) &&
                            vtlComparisonBinaryExpression.getLeftExpression().getResultExpression().getResultComponent().getName().equalsIgnoreCase(vtlComponentId.getComponentName())) {
                        singleRightItem = singleRightItem + ", " + getValueComparison(vtlCodeItem);
                        buildCase = false;
                    } else {
                        singleRightItem = singleRightItem + CV;
                    }
                } else {
                    singleRightItem = singleRightItem + CV;
                }
            }

            singleRightItem = singleRightItem + " ]";
            if (vtlCodeItem.getCondition() != null && buildCase) {
                singleRightItem = buildCaseConstraint(vtlCodeItem, vtlComponentRule, singleRightItem, validationMode);

            }
        }
        rightItem = rightItem + singleRightItem + ")";
        return rightItem;
    }


    private String buildLeftItem(String ruleName, VtlHRRule vtlHRRule, List<VtlComponentId> antecedentComponents) {
        String leftItem = " " + ruleName + "[";
        leftItem = leftItem + " '" + vtlHRRule.getRuleName() + "', ";
        leftItem = leftItem + "'" + vtlHRRule.getVtlCodeItemRelation().getLeftCodeItem() + "'";
        String antecedentCondition = buildAntecedentCondition(vtlHRRule.getVtlCodeItemRelation(), antecedentComponents);
        leftItem = leftItem + antecedentCondition + " ]";
        return leftItem;
    }


    private SqlObject buildDefaultFlag(String ruleName, String ruleValue, VtlHRRule vtlHRRule, List<VtlComponentId> antecedentComponents) {
        String flag = ruleName + "[ ";
        flag = flag + " '" + vtlHRRule.getRuleName() + "', ";
        flag = flag + " '" + vtlHRRule.getVtlCodeItemRelation().getLeftCodeItem() + "' ";
        for (VtlComponentId vtlComponentId : antecedentComponents) {
            flag = flag + ", ANY ";
        }
        if (ruleValue == null)
            flag = flag + "] = NULL ";
        else
            flag = flag + "] = '" + ruleValue + "' ";
        return new CustomSql(flag);
    }

    private String buildAntecedentCondition(VtlCodeItemRelation vtlCodeItemRelation, List<VtlComponentId> antecedentsComponent) {
        String antecedentCondition = "";
        for (VtlComponentId vtlComponentId : antecedentsComponent) {
            if (vtlCodeItemRelation.getLeftCondition() != null) {
                VtlComponentId vtlComponentIdExist = vtlCodeItemRelation.getParameterUsed().get(vtlComponentId.getComponentName());
                if (vtlComponentIdExist != null) {
                    antecedentCondition = "," + getValueComparison(vtlCodeItemRelation);

                } else {
                    antecedentCondition = antecedentCondition + "," + " ANY ";
                }
            } else {

                antecedentCondition = antecedentCondition + "," + " ANY ";
            }
        }
        return antecedentCondition;
    }


    private String getValueComparison(VtlCodeItemRelation vtlCodeItemRelation) {
        try {
            VtlComparisonBinaryExpression vtlComparisonBinaryExpression = (VtlComparisonBinaryExpression) vtlCodeItemRelation.getLeftCondition();
            VtlConstantExpression vtlConstantExpression = (VtlConstantExpression) vtlComparisonBinaryExpression.getRightExpression();
            return vtlConstantExpression.getSqlResult().getSqlComponent().getResult().toString();
        } catch (Exception e) {
            //nessun problema
            return null;
        }
    }

    private String getValueComparison(VtlCodeItem vtlCodeItem) {
        try {
            VtlComparisonBinaryExpression vtlComparisonBinaryExpression = (VtlComparisonBinaryExpression) vtlCodeItem.getCondition();
            VtlConstantExpression vtlConstantExpression = (VtlConstantExpression) vtlComparisonBinaryExpression.getRightExpression();
            return vtlConstantExpression.getSqlResult().getSqlComponent().getResult().toString();
        } catch (Exception e) {
            //nessun problema
            return null;
        }
    }

    private boolean checkCaseConstraint(List<VtlComponentId> antecedentsComponent,
                                        VtlCodeItem vtlCodeItem, Map<String, VtlComponentId> parameterUsed) {
        for (VtlComponentId vtlComponentId : antecedentsComponent) {
            if (vtlCodeItem.getCondition() != null && parameterUsed != null) {
                VtlComponentId vtlComponentIdExist = parameterUsed.get(vtlComponentId.getComponentName());
                VtlComparisonBinaryExpression vtlComparisonBinaryExpression = (VtlComparisonBinaryExpression) vtlCodeItem.getCondition();
                if (vtlComponentIdExist != null && vtlComparisonBinaryExpression.getLeftExpression().getResultExpression().getResultComponent().getName().equalsIgnoreCase(vtlComponentId.getComponentName())) {
                    return false;
                }
            }
        }
        return true;
    }

    private String buildRightAntecedentCondition(List<VtlComponentId> antecedentsComponent,
                                                 VtlCodeItem vtlCodeItem, Map<String, VtlComponentId> parameterUsed) {
        String rightAntecedentCondition = "";
        for (VtlComponentId vtlComponentId : antecedentsComponent) {
            if (vtlCodeItem.getCondition() != null && parameterUsed != null) {
                VtlComponentId vtlComponentIdExist = parameterUsed.get(vtlComponentId.getComponentName());
                VtlComparisonBinaryExpression vtlComparisonBinaryExpression = (VtlComparisonBinaryExpression) vtlCodeItem.getCondition();
                if (vtlComponentIdExist != null && vtlComparisonBinaryExpression.getLeftExpression().getResultExpression().getResultComponent().getName().equalsIgnoreCase(vtlComponentId.getComponentName())) {
                    rightAntecedentCondition = rightAntecedentCondition + "," + getValueComparison(vtlCodeItem);
                } else {
                    rightAntecedentCondition = rightAntecedentCondition + CV;
                }
            } else {
                rightAntecedentCondition = rightAntecedentCondition + CV;

            }
        }
        return rightAntecedentCondition;
    }

    private SqlObject buildHierarchyRule(VtlCodeItemRelation vtlCodeItemRelation, InputMode inputMode, VtlComponentId ruleComponent, List<VtlComponentId> antecedentsComponent, ValidationMode validationMode) {
        String rule = "";
        String antecedentCondition = buildAntecedentCondition(vtlCodeItemRelation, antecedentsComponent);
        String leftCodeItem = ME_RULE + "[ '" + vtlCodeItemRelation.getLeftCodeItem() + "' " + antecedentCondition + " ] ";
        String operator = vtlCodeItemRelation.getComparator().getValue() + " ";
        String rigthCodeItem = "";
        for (VtlCodeItem vtlCodeItem : vtlCodeItemRelation.getVtlCodeItems()) {
            String condition = "";
            String sign = vtlCodeItem.getOperator() != null ? vtlCodeItem.getOperator().getValue() : "";
            Boolean caseConstraint = true;
            String rightAntecedentCondition = buildRightAntecedentCondition(antecedentsComponent, vtlCodeItem, vtlCodeItemRelation.getParameterUsed());
            caseConstraint = checkCaseConstraint(antecedentsComponent, vtlCodeItem, vtlCodeItemRelation.getParameterUsed());
            if (inputMode == InputMode.DATASET) {
                if (validationMode.equals(ValidationMode.NON_ZERO) || validationMode.equals(ValidationMode.PARTIAL_ZERO) || validationMode.equals(ValidationMode.ALWAYS_ZERO)) {
                    String preCondition = ME_DS + "[ '" + vtlCodeItem.getCodeItem() + "' " + rightAntecedentCondition + " ] ";
                    condition = condition + "PRESENTV(" + preCondition + ", " + preCondition + ", 0)";
                } else {
                    condition = condition + ME_DS + "[ '" + vtlCodeItem.getCodeItem() + "' " + rightAntecedentCondition + " ] ";
                }
            } else if (inputMode == InputMode.RULE) {
                condition = condition + ME_RULE + "[ '" + vtlCodeItem.getCodeItem() + "' " + rightAntecedentCondition + " ] ";
            } else {
                SqlObject caseStatement = createCaseStatement(
                        new CustomSql(ME_RULE + "[ '" + vtlCodeItem.getCodeItem() + "' " + rightAntecedentCondition + " ] IS NULL"),
                        new CustomSql(ME_DS + "[ '" + vtlCodeItem.getCodeItem() + "' " + rightAntecedentCondition + " ] "),
                        new CustomSql(ME_RULE + "[ '" + vtlCodeItem.getCodeItem() + "' " + rightAntecedentCondition + " ] ")
                );
                condition = condition + caseStatement.toString() + " ";
            }
            if (vtlCodeItem.getCondition() != null && caseConstraint) {
                condition = buildCaseConstraint(vtlCodeItem, ruleComponent, condition, validationMode);
            }
            rigthCodeItem = rigthCodeItem + sign + condition + " ";
        }
        rule = leftCodeItem + operator + rigthCodeItem;
        return new CustomSql(rule);
    }

    private String buildCaseConstraint(VtlCodeItem vtlCodeItem, VtlComponentId ruleComponent, String condition, ValidationMode validationMode) {
        String constraintCodeItem = vtlCodeItem.getCondition().getSqlResult().getSqlComponent().getResult().toString();
        constraintCodeItem = constraintCodeItem.replace(ruleComponent.getSqlResult().getSqlComponent().getResult().toString(), condition + " ");
        CustomSql defaultValue = new CustomSql("NULL");
        if (validationMode.equals(ValidationMode.NON_ZERO) || validationMode.equals(ValidationMode.PARTIAL_ZERO) || validationMode.equals(ValidationMode.ALWAYS_ZERO)) {
            defaultValue = new CustomSql("0");
        }
        SqlObject createConstraintStatement = createCaseStatement(
                new CustomSql("(" + constraintCodeItem + ")"),
                new CustomSql(condition),
                defaultValue
        );
        condition = createConstraintStatement.toString();
        return condition;
    }

    private SqlObject buildFlagRule(VtlCodeItemRelation vtlCodeItemRelation, InputMode inputMode, ValidationMode validationMode, VtlComponentId ruleComponent, List<VtlComponentId> antecedentsComponent) {
        String rule = "";
        String operator = vtlCodeItemRelation.getComparator().getValue() + " ";
        String antecedentCondition = buildAntecedentCondition(vtlCodeItemRelation, antecedentsComponent);
        String leftCodeItem = "FLAG[ '" + vtlCodeItemRelation.getLeftCodeItem() + "' " + antecedentCondition + " ] ";
        String totalCondition = "";
        for (VtlCodeItem vtlCodeItem : vtlCodeItemRelation.getVtlCodeItems()) {

            String condition = "";
            String rightAntecedentCondition = buildRightAntecedentCondition(antecedentsComponent, vtlCodeItem, vtlCodeItemRelation.getParameterUsed());

            String nomecampo = ME_RULE;
            if (inputMode == InputMode.DATASET) {
                nomecampo = ME_DS;
            }

            if (inputMode == InputMode.DATASET && (validationMode.equals(ValidationMode.NON_ZERO) || validationMode.equals(ValidationMode.PARTIAL_ZERO) || validationMode.equals(ValidationMode.ALWAYS_ZERO))) {
                String preCondition = nomecampo + "[ '" + vtlCodeItem.getCodeItem() + "' " + rightAntecedentCondition + " ] ";
                condition = condition + "PRESENTV(" + preCondition + ", " + preCondition + ", 0)";
            } else {
                condition = condition + nomecampo + "[ '" + vtlCodeItem.getCodeItem() + "' " + rightAntecedentCondition + " ] ";
            }
            if (vtlCodeItem.getCondition() != null) {
                String elseCondition = "NULL";
                if (validationMode == ValidationMode.NON_ZERO) {
                    elseCondition = " 0";
                }
                String constraintCodeItem = vtlCodeItem.getCondition().getSqlResult().getSqlComponent().getResult().toString();
                constraintCodeItem = constraintCodeItem.replace(ruleComponent.getSqlResult().getSqlComponent().getResult().toString(), condition + " ");
                SqlObject createConstraintStatement = createCaseStatement(
                        new CustomSql("(" + constraintCodeItem + ")"),
                        new CustomSql(condition),
                        new CustomSql(elseCondition)
                );
                condition = createConstraintStatement.toString();
            }
            String booleanCondition = " AND ";
            String switchCondition = " IS NOT NULL";
            if (validationMode == ValidationMode.NON_ZERO || validationMode == ValidationMode.PARTIAL_NULL || validationMode == ValidationMode.PARTIAL_ZERO) {
                booleanCondition = " OR ";
            }

            if (validationMode == ValidationMode.NON_ZERO) {
                switchCondition = " <> 0";
            }
            condition = "(" + condition + ")" + switchCondition;
            if (totalCondition.isEmpty()) {
                totalCondition = condition;
            } else {
                totalCondition = totalCondition + booleanCondition + condition;
            }

        }
        SqlObject createConstraintStatement = createCaseStatement(
                new CustomSql("(" + totalCondition + ") "),
                "Y",
                "N"
        );

        rule = leftCodeItem + operator + createConstraintStatement.toString();
        return new CustomSql(rule);
    }

    /**
     * Costruisce l'sql per la tabella delle regole per le ruleset
     *
     * @param vtlHRRules
     * @return
     */
    @Override
    public SqlObject buildRuleTable(List<VtlHRRule> vtlHRRules) {
        //SELECT 'RULE01' AS RULEID, 'wrong 01' AS ERRORCODE, '1' AS ERRORLEVEL FROM DUAL
        UnionQuery unionQuery = new UnionQuery(SetOperationQuery.Type.UNION_ALL);
        for (VtlHRRule vtlHRRule : vtlHRRules) {
            String selectStatement = "SELECT ";
            selectStatement = selectStatement + "'" + vtlHRRule.getRuleName() + "' AS RULEID, ";
            if (vtlHRRule.getErrorRuleset() == null)
                selectStatement = selectStatement + "' ' AS ERRORCODE, ";
            else
                selectStatement = selectStatement + "'" + vtlHRRule.getErrorRuleset().getErrorCode().getValue() + "' AS ERRORCODE, ";
            String errorLevel;
            if (vtlHRRule.getErrorRuleset() != null && vtlHRRule.getErrorRuleset().getErrorLevel() != null) {
                errorLevel = " '" + vtlHRRule.getErrorRuleset().getErrorLevel().getValue() + "'";
            } else {
                errorLevel = " ' ' ";
            }
            selectStatement = selectStatement + errorLevel + " AS ERRORLEVEL FROM DUAL ";
            unionQuery.addQueries(new CustomSql(selectStatement));
        }

        return unionQuery;
    }

    /**
     * Costruisce gli oggetti di pivot per le query
     *
     * @param vtlPivotClauseExpression
     * @return
     */
    @Override
    public SqlObject buildPivot(VtlPivotClauseExpression vtlPivotClauseExpression) {
        String pivot = " PIVOT ( ";
        pivot = pivot + "SUM( " + vtlPivotClauseExpression.getMeasure().getComponentName() + " ) ";
        pivot = pivot + "FOR " + vtlPivotClauseExpression.getIdentifier().getComponentName() + " IN (";
        boolean first = true;
        for (VtlConstantExpression vtlConstantExpression : vtlPivotClauseExpression.getConstantExpressions()) {
            if (!first) {
                pivot = pivot + ", ";
            }
            first = false;
            String constantString = getConstantSql(vtlConstantExpression.getVtlConstant()).toString();
            pivot = pivot + constantString + " AS " +
                    vtlPivotClauseExpression.getIdentifier().getComponentName() + "_" + vtlConstantExpression.getVtlConstant().getValue();
        }

        pivot = pivot + ")";
        pivot = pivot + ")";
        return new CustomSql(pivot);
    }

    @Override
    public SqlObject buildComponentPivot(SqlComponent sqlComponent, VtlPivotClauseExpression vtlPivotClauseExpression) {
        return null;
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
            castNull = new CustomSql("CAST(NULL AS VARCHAR2(100))");
        } else if (domainValue.equals(VtlType.NUMBER.getDomainValue())) {
            castNull = new CustomSql("CAST(NULL AS NUMBER)");
        } else if (domainValue.equals(VtlType.INTEGER.getDomainValue())) {
            castNull = new CustomSql("CAST(NULL AS INTEGER)");
        } else {
            castNull = new CustomSql("CAST(NULL AS VARCHAR2(100))");
        }

        return castNull;
    }
}
