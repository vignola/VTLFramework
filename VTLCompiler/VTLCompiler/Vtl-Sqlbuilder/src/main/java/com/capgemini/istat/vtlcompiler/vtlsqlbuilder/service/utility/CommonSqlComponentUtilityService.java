package com.capgemini.istat.vtlcompiler.vtlsqlbuilder.service.utility;

import com.capgemini.istat.vtlcompiler.vtlcommon.constant.ConstantUtility;
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
import com.capgemini.istat.vtlcompiler.vtlcommon.service.ComponentUtilityService;
import com.healthmarketscience.sqlbuilder.CustomSql;
import com.healthmarketscience.sqlbuilder.FunctionCall;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * la classe offre metodi per la manipolazione degli SQL component per la costruzione degli statement SQL.
 * Gli SqlComponent qui manipolati verranno poi riassegnati agli sqlDataset per costruire il risultato finale
 *
 * @see ComponentUtilityService
 * @see com.capgemini.istat.vtlcompiler.vtlsqlbuilder.service.utility.mysql.MySqlObjectUtilityService
 * @see com.capgemini.istat.vtlcompiler.vtlsqlbuilder.service.utility.postgresql.PostgreSqlObjectUtilityService
 * @see CommonSqlObjectUtilityService
 * @see com.capgemini.istat.vtlcompiler.vtlsqlbuilder.service.utility.sqlserver.SqlServerObjectUtilityService
 */
@Service
public class CommonSqlComponentUtilityService implements ISqlComponentUtilityService {
    protected ISqlObjectUtilityService sqlObjectUtilityService;
    protected ComponentUtilityService componentUtilityService;


    public void setSqlObjectUtilityService(ISqlObjectUtilityService sqlObjectUtilityService) {
        this.sqlObjectUtilityService = sqlObjectUtilityService;
    }

    @Autowired
    public void setComponentUtilityService(ComponentUtilityService componentUtilityService) {
        this.componentUtilityService = componentUtilityService;
    }

    /**
     * Applica una funzione unaria a un componente
     *
     * @param sqlComponent
     * @param parameter
     * @param optionalParameter
     * @param operator
     * @return
     */
    @Override
    public SqlComponent applyUnaryFunctionToSqlComponent(SqlComponent sqlComponent, VtlExpression parameter, List<VtlExpression> optionalParameter, Operator operator) {
        sqlComponent.setResult(
                sqlObjectUtilityService.applyUnaryFunctionToSqlObject(sqlComponent.getResult(), parameter, optionalParameter, operator)
        );
        if (operator.getSubstituteComponent()) {
            //se si tratta di una sostituzione va preso il campo del dataset precedente con il nome di quello risultante per simulare il cast
            sqlComponent.setAliasName(operator.getExpectedTypeReturn().getDefaultName());
        }
        return sqlComponent;
    }

    /**
     * Applica la funzione flow to stock a un componente
     *
     * @param sqlComponent
     * @param identifierList
     * @param timeIdentifier
     * @param operator
     * @return
     */
    @Override
    public SqlComponent applyFlowToStockStockToFlowFunctionToSqlComponent(SqlComponent sqlComponent, List<SqlComponent> identifierList, SqlComponent timeIdentifier, Operator operator) {
        if (operator.getValue().equals(Operator.FLOW_TO_STOCK.getValue())) {
            sqlComponent.setResult(sqlObjectUtilityService.applyFlowToStockFunctionToSqlObject(sqlComponent.getResult(), identifierList, timeIdentifier.getResult(), operator));
        } else {
            sqlComponent.setResult(sqlObjectUtilityService.applyStockToFlowFunctionToSqlObject(sqlComponent.getResult(), identifierList, timeIdentifier.getResult(), operator));
        }

        if (operator.getSubstituteComponent()) {
            //se si tratta di una sostituzione va preso il campo del dataset precedente con il nome di quello risultante per simulare il cast
            sqlComponent.setAliasName(operator.getExpectedTypeReturn().getDefaultName());
        }
        return sqlComponent;
    }

    /**
     * applica una funzione binaria a due componenti
     *
     * @param sqlComponentLeft
     * @param sqlComponentRight
     * @param operator
     * @return
     */
    @Override
    public SqlComponent applyBinaryFunctionToSqlComponent(SqlComponent sqlComponentLeft, SqlComponent sqlComponentRight, Operator operator) {
        SqlComponent sqlComponent = new SqlComponent();
        SqlComponent sqlComponentToCopy = null;
        if (sqlComponentLeft.getAliasTable() != null) {
            sqlComponentToCopy = sqlComponentLeft;
        } else {
            sqlComponentToCopy = sqlComponentRight;
        }
        sqlComponent.setVtlComponent(sqlComponentToCopy.getVtlComponent());
        if (operator.getSubstituteComponent()) {
            //se si tratta di una sostituzione va preso il campo del dataset precedente con il nome di quello risultante per simulare il cast
            sqlComponent.setAliasName(operator.getExpectedTypeReturn().getDefaultName());
        } else {
            sqlComponent.setAliasName(sqlComponentToCopy.getVtlComponent().getName());
        }
        sqlComponent.setAliasTable(ConstantUtility.DEFAULT_ALIAS_TABLE);
        sqlComponent.setResult(
                sqlObjectUtilityService.applyOperatorFunctionToSqlObjects(sqlComponentLeft, sqlComponentRight, operator)
        );
        return sqlComponent;
    }

    /**
     * Applica un operatore unaria a un componente
     *
     * @param sqlComponent
     * @param operator
     * @param vtlExpression
     * @return
     */
    @Override
    public SqlComponent applyUnaryConditionToSqlComponent(SqlComponent sqlComponent, Operator operator, VtlExpression vtlExpression) {
        sqlComponent.setResult(sqlObjectUtilityService.applyUnaryConditionToSqlObject(sqlComponent.getResult(), operator, vtlExpression));
        if (operator.getSubstituteComponent()) {
            //se si tratta di una sostituzione va preso il campo del dataset precedente con il nome di quello risultante per simulare il cast
            sqlComponent.setAliasName(operator.getExpectedTypeReturn().getDefaultName());
        }
        return sqlComponent;
    }

    /**
     * Applica un operatore unaria con dei parametri a un componente.
     *
     * @param sqlComponent
     * @param operator
     * @param vtlExpression
     * @param parameter
     * @param optionalParameter
     * @return
     */
    @Override
    public SqlComponent applyUnaryConditionWithParameterToSqlComponent(SqlComponent sqlComponent, Operator operator, VtlExpression vtlExpression, VtlExpression parameter, List<VtlExpression> optionalParameter) {
        sqlComponent.setResult(sqlObjectUtilityService.applyUnaryConditionWithParameterToSqlObject(sqlComponent.getResult(), operator, vtlExpression, parameter, optionalParameter));
        if (operator.getSubstituteComponent()) {
            //se si tratta di una sostituzione va preso il campo del dataset precedente con il nome di quello risultante per simulare il cast
            sqlComponent.setAliasName(operator.getExpectedTypeReturn().getDefaultName());
        }
        return sqlComponent;
    }

    /**
     * Applica l'operazione di in o not in su un componente
     *
     * @param sqlComponent
     * @param operator
     * @param vtlInExpression
     * @return
     */
    @Override
    public SqlComponent applyInNotInFunctionToSqlComponent(SqlComponent sqlComponent, Operator operator, VtlInExpression vtlInExpression) {
        sqlComponent.setResult(sqlObjectUtilityService.applyInNotInFunctionToSqlObject(sqlComponent.getResult(), operator, vtlInExpression));
        if (operator.getSubstituteComponent()) {
            //se si tratta di una sostituzione va preso il campo del dataset precedente con il nome di quello risultante per simulare il cast
            sqlComponent.setAliasName(operator.getExpectedTypeReturn().getDefaultName());
        }
        return sqlComponent;
    }

    /**
     * Applica a un componente una funzione di between in una condition
     *
     * @param sqlComponent
     * @param operator
     * @param vtlBetweenExpression
     * @return
     */
    @Override
    public SqlComponent applyBetweenConditionToSqlComponent(SqlComponent sqlComponent, Operator operator, VtlBetweenExpression vtlBetweenExpression) {
        sqlComponent.setResult(sqlObjectUtilityService.applyBetweenConditionToSqlObject(sqlComponent.getResult(), vtlBetweenExpression));
        if (operator.getSubstituteComponent()) {
            //se si tratta di una sostituzione va preso il campo del dataset precedente con il nome di quello risultante per simulare il cast
            sqlComponent.setAliasName(operator.getExpectedTypeReturn().getDefaultName());
        }
        return sqlComponent;
    }

    /**
     * Applica a un componente una between condition. Il metodo viene utilizzato nelle where
     *
     * @param sqlComponent
     * @param operator
     * @param vtlBetweenExpression
     * @return
     */
    @Override
    public SqlComponent applyBetweenFunctionToSqlComponent(SqlComponent sqlComponent, Operator operator, VtlBetweenExpression vtlBetweenExpression) {
        sqlComponent.setResult(sqlObjectUtilityService.applyBetweenFunctionToSqlObject(sqlComponent.getResult(), vtlBetweenExpression));
        if (operator.getSubstituteComponent()) {
            //se si tratta di una sostituzione va preso il campo del dataset precedente con il nome di quello risultante per simulare il cast
            sqlComponent.setAliasName(operator.getExpectedTypeReturn().getDefaultName());
        }
        return sqlComponent;
    }

    /**
     * Applica una in not in condition a un componente
     *
     * @param sqlComponent
     * @param operator
     * @param vtlInExpression
     * @return
     */
    @Override
    public SqlComponent applyInNotInConditionToSqlComponent(SqlComponent sqlComponent, Operator operator, VtlInExpression vtlInExpression) {
        sqlComponent.setResult(sqlObjectUtilityService.applyInNotInConditionToSqlObject(sqlComponent.getResult(), operator, vtlInExpression));
        if (operator.getSubstituteComponent()) {
            //se si tratta di una sostituzione va preso il campo del dataset precedente con il nome di quello risultante per simulare il cast
            sqlComponent.setAliasName(operator.getExpectedTypeReturn().getDefaultName());
        }
        return sqlComponent;
    }

    /**
     * Applica una condiziione booleana a due componenti
     *
     * @param sqlComponentLeft
     * @param sqlComponentRight
     * @param operator
     * @param vtlExpression
     * @return
     */
    @Override
    public SqlComponent applyBooleanBinaryConditionToSqlComponent(SqlComponent sqlComponentLeft, SqlComponent sqlComponentRight, Operator operator, VtlBinaryExpression vtlExpression) {
        SqlComponent sqlComponent = new SqlComponent();
        SqlComponent sqlComponentToCopy = null;
        if (sqlComponentLeft.getAliasTable() != null) {
            sqlComponentToCopy = sqlComponentLeft;
        } else {
            sqlComponentToCopy = sqlComponentRight;
        }
        sqlComponent.setVtlComponent(sqlComponentToCopy.getVtlComponent());
        sqlComponent.setAliasName(sqlComponentToCopy.getVtlComponent().getName());
        sqlComponent.setAliasTable(ConstantUtility.DEFAULT_ALIAS_TABLE);
        sqlComponent.setResult(
                sqlObjectUtilityService.applyBooleanBinaryConditionToSqlObjects(sqlComponentLeft, sqlComponentRight, operator, vtlExpression)
        );
        return sqlComponent;
    }

    /**
     * Applica un operatore di comparazione a due componenti
     *
     * @param sqlComponentLeft
     * @param sqlComponentRight
     * @param operator
     * @param vtlExpression
     * @return
     */
    @Override
    public SqlComponent applyComparisonBinaryConditionToSqlComponent(SqlComponent sqlComponentLeft, SqlComponent sqlComponentRight, Operator operator, VtlBinaryExpression vtlExpression) {
        SqlComponent sqlComponent = new SqlComponent();
        SqlComponent sqlComponentToCopy = null;
        if (sqlComponentLeft.getAliasTable() != null) {
            sqlComponentToCopy = sqlComponentLeft;
        } else {
            sqlComponentToCopy = sqlComponentRight;
        }
        sqlComponent.setVtlComponent(sqlComponentToCopy.getVtlComponent());
        sqlComponent.setAliasName(sqlComponentToCopy.getVtlComponent().getName());
        sqlComponent.setAliasTable(ConstantUtility.DEFAULT_ALIAS_TABLE);
        sqlComponent.setResult(
                sqlObjectUtilityService.applyComparisonBinaryConditionToSqlObjects(sqlComponentLeft, sqlComponentRight, operator, vtlExpression)
        );
        return sqlComponent;
    }

    /**
     * Applica un operatore di condizione binaria a due componenti
     *
     * @param sqlComponentLeft
     * @param sqlComponentRight
     * @param operator
     * @param vtlExpression
     * @return
     */
    @Override
    public SqlComponent applyConditionalBinaryConditionToSqlComponent(SqlComponent sqlComponentLeft, SqlComponent sqlComponentRight, Operator operator, VtlBinaryExpression vtlExpression) {
        SqlComponent sqlComponent = new SqlComponent();
        SqlComponent sqlComponentToCopy = null;
        if (sqlComponentLeft.getAliasTable() != null) {
            sqlComponentToCopy = sqlComponentLeft;
        } else {
            sqlComponentToCopy = sqlComponentRight;
        }
        sqlComponent.setVtlComponent(sqlComponentToCopy.getVtlComponent());
        sqlComponent.setAliasName(sqlComponentToCopy.getVtlComponent().getName());
        sqlComponent.setAliasTable(ConstantUtility.DEFAULT_ALIAS_TABLE);
        sqlComponent.setResult(
                sqlObjectUtilityService.applyConditionalBinaryConditionToSqlObjects(sqlComponentLeft, sqlComponentRight, operator, vtlExpression)
        );
        return sqlComponent;
    }

    /**
     * applica una condizione binaria a due componenti
     *
     * @param sqlComponentLeft
     * @param sqlComponentRight
     * @param operator
     * @param vtlExpression
     * @return
     */
    @Override
    public SqlComponent applyNumericOrStringBinaryConditionToSqlComponent(SqlComponent sqlComponentLeft, SqlComponent sqlComponentRight, Operator operator, VtlBinaryExpression vtlExpression) {
        SqlComponent sqlComponent = new SqlComponent();
        SqlComponent sqlComponentToCopy = null;
        if (sqlComponentLeft.getAliasTable() != null) {
            sqlComponentToCopy = sqlComponentLeft;
        } else {
            sqlComponentToCopy = sqlComponentRight;
        }
        sqlComponent.setVtlComponent(sqlComponentToCopy.getVtlComponent());
        sqlComponent.setAliasName(sqlComponentToCopy.getVtlComponent().getName());
        sqlComponent.setAliasTable(ConstantUtility.DEFAULT_ALIAS_TABLE);
        sqlComponent.setResult(
                sqlObjectUtilityService.applyNumericOrStringBinaryConditionToSqlObjects(sqlComponentLeft, sqlComponentRight, operator, vtlExpression)
        );
        return sqlComponent;
    }

    /**
     * applica una funzione analitica a un componente
     *
     * @param sqlComponent
     * @param sqlDataset
     * @param vtlAnalyticFunctionExpression
     * @param operator
     * @return
     */
    @Override
    public SqlComponent applyAnalyticFunctionToSqlComponent(SqlComponent sqlComponent, SqlDataset sqlDataset, VtlAnalyticFunctionExpression vtlAnalyticFunctionExpression, Operator operator) {
        sqlComponent.setResult(
                sqlObjectUtilityService.applyAnalyticFunctionToSqlObject(sqlComponent.getResult(), sqlDataset, vtlAnalyticFunctionExpression, operator)
        );
        if (operator.getSubstituteComponent()) {
            //se si tratta di una sostituzione va preso il campo del dataset precedente con il nome di quello risultante per simulare il cast
            sqlComponent.setAliasName(operator.getExpectedTypeReturn().getDefaultName());
        }
        return sqlComponent;
    }

    /**
     * Crea un componente contenente un numero. Viene utilizzato per dare un ordine di creazione della query in alcune condizioni
     *
     * @param priority
     * @return
     */
    @Override
    public SqlComponent createPriorityComponent(Integer priority) {
        SqlComponent sqlComponent = new SqlComponent();
        sqlComponent.setAliasName(ConstantUtility.COLUMN_PRIORITY);
        sqlComponent.setAliasTable(ConstantUtility.DEFAULT_ALIAS_TABLE);
        sqlComponent.setResult(sqlObjectUtilityService.createPrioritySqlObjects(priority));
        return sqlComponent;
    }

    /**
     * Data una lista di componenti restituisce quello che corrisponde al nome cercato
     *
     * @param sqlComponents
     * @param name
     * @return
     */
    @Override
    public SqlComponent getSqlComponentByName(List<SqlComponent> sqlComponents, String name) {
        for (SqlComponent sqlComponent : sqlComponents) {
            if (sqlComponent.getVtlComponent().getName().equalsIgnoreCase(name))
                return copySqlComponent(sqlComponent);
        }
        return null;
    }

    /**
     * Dato un dataset e il nome di un componente viene restituito il componente che corrisponde al nome ricercato
     *
     * @param sqlDataset
     * @param name
     * @return
     */
    @Override
    public SqlComponent getSqlComponentByName(SqlDataset sqlDataset, String name) {
        for (SqlComponent sqlComponent : sqlDataset.getComponentList()) {
            if (sqlComponent.getVtlComponent().getName().equalsIgnoreCase(name))
                return copySqlComponent(sqlComponent);
        }
        for (SqlComponent sqlComponent : sqlDataset.getComponentList()) {
            if (sqlComponent.getAliasName().equalsIgnoreCase(name))
                return copySqlComponent(sqlComponent);
        }
        return null;
    }

    /**
     * Effettua una copia di un componente
     *
     * @param sqlComponent
     * @return
     */
    @Override
    public SqlComponent copySqlComponent(SqlComponent sqlComponent) {
        SqlComponent result = new SqlComponent();

        result.setVtlComponent(componentUtilityService.copyComponent(sqlComponent.getVtlComponent()));

        result.setAliasName(sqlComponent.getAliasName());
        result.setAliasTable(sqlComponent.getAliasTable());
        result.setResult(new CustomSql(sqlComponent.getResult()));
        return result;
    }

    /**
     * Copia una lista di componenti in una nuova lista
     *
     * @param sqlComponents
     * @return
     */
    @Override
    public List<SqlComponent> copySqlComponents(List<SqlComponent> sqlComponents) {
        List<SqlComponent> result = new ArrayList<>();
        for (SqlComponent sqlComponent : sqlComponents) {
            result.add(copySqlComponent(sqlComponent));
        }
        return result;
    }

    /**
     * Genera un SQLComponent a partire da un VTLComponent
     *
     * @param vtlComponent
     * @return
     */
    @Override
    public SqlComponent createSqlComponent(VtlComponent vtlComponent) {
        SqlComponent sqlComponent = new SqlComponent();
        sqlComponent.setAliasName(vtlComponent.getName());
        sqlComponent.setAliasTable(ConstantUtility.DEFAULT_ALIAS_TABLE);
        sqlComponent.setVtlComponent(vtlComponent);
        sqlComponent.setResult(new CustomSql(sqlComponent.getAliasTable() + "." + vtlComponent.getName()));
        return sqlComponent;
    }

    /**
     * a partire da un tipo, un ruolo e un nome viene generato un SQLComponent
     *
     * @param vtlType
     * @param componentName
     * @param vtlComponentRole
     * @return
     */
    @Override
    public SqlComponent createSqlComponent(VtlType vtlType, String componentName, VtlComponentRole vtlComponentRole) {
        VtlComponent vtlComponentRule = componentUtilityService.getDefaultComponent(vtlType, null);
        vtlComponentRule.setName(componentName);
        vtlComponentRule.setVtlComponentRole(vtlComponentRole);
        return createSqlComponent(vtlComponentRule);
    }

    /**
     * Dati due dataset viene restituita la lista di superset component
     *
     * @param sqlDatasetLeft
     * @param sqlDatasetRight
     * @return
     */
    @Override
    public List<SqlComponent> getSupersetIdentifier(SqlDataset sqlDatasetLeft, SqlDataset sqlDatasetRight) {
        List<SqlComponent> sqlComponentsResult = new ArrayList<>();
        SqlDataset sqlDatasetSuperset = null;
        if (sqlDatasetLeft.getSqlTables().get(0).getVtlDataset().getIdentifiers().size() >= sqlDatasetRight.getSqlTables().get(0).getVtlDataset().getIdentifiers().size()) {
            sqlDatasetSuperset = sqlDatasetLeft;
        } else {
            sqlDatasetSuperset = sqlDatasetRight;
        }
        sqlComponentsResult.addAll(getSqlComponentsByRole(sqlDatasetSuperset.getComponentList(), VtlComponentRole.IDENTIFIER));
        return sqlComponentsResult;

    }

    /**
     * Dati due daset applica una funzione a tutti i component in comune
     *
     * @param sqlDatasetLeft
     * @param sqlDatasetRight
     * @param vtlComponentRole
     * @param operator
     * @return
     */
    @Override
    public List<SqlComponent> applyFunctionToCommonComponent(SqlDataset sqlDatasetLeft, SqlDataset sqlDatasetRight, VtlComponentRole vtlComponentRole, Operator operator) {
        List<SqlComponent> sqlComponentsResult = new ArrayList<>();
        List<SqlComponent> componentsLeft = getSqlComponentsByRole(sqlDatasetLeft.getComponentList(), vtlComponentRole);
        for (SqlComponent sqlComponent : componentsLeft) {

            SqlComponent otherSqlComponent = getSqlComponentByName(sqlDatasetRight, sqlComponent.getVtlComponent().getName());
            if (otherSqlComponent != null) {
                SqlComponent sqlComponentResult = new SqlComponent();
                sqlComponentResult.setResult(sqlObjectUtilityService.applyOperatorFunctionToSqlObjects(sqlComponent, otherSqlComponent, operator));
                if (operator.getSubstituteComponent()) {
                    //se si tratta di una sostituzione va preso il campo del dataset precedente con il nome di quello risultante per simulare il cast
                    sqlComponentResult.setAliasName(operator.getExpectedTypeReturn().getDefaultName());
                } else {
                    sqlComponentResult.setAliasName(sqlComponent.getAliasName());
                }

                sqlComponentsResult.add(sqlComponentResult);
            }
        }

        return sqlComponentsResult;
    }

    /**
     * Effettua un filtro per ruolo componente su una lista di componenti
     *
     * @param sqlComponents
     * @param vtlComponentRole
     * @return
     */
    @Override
    public List<SqlComponent> getSqlComponentsByRole(List<SqlComponent> sqlComponents, VtlComponentRole vtlComponentRole) {
        List<SqlComponent> sqlComponentResults = new ArrayList<>();
        for (SqlComponent sqlComponent : sqlComponents) {
            if (sqlComponent.getVtlComponent().getVtlComponentRole() == vtlComponentRole) {
                sqlComponentResults.add(copySqlComponent(sqlComponent));
            }
        }
        return sqlComponentResults;
    }

    /**
     * Dati due dataset applica l'aggregazione dei dati virali
     *
     * @param sqlDatasetLeft
     * @param sqlDatasetRight
     * @return
     */
    @Override
    public List<SqlComponent> applyFunctionToCommonViralComponent(SqlDataset sqlDatasetLeft, SqlDataset sqlDatasetRight) {
        List<SqlComponent> sqlComponentsResult = new ArrayList<>();
        List<SqlComponent> componentsLeft = getSqlComponentsByRole(sqlDatasetLeft.getComponentList(), VtlComponentRole.VIRAL);
        for (SqlComponent sqlComponent : componentsLeft) {

            SqlComponent otherSqlComponent = getSqlComponentByName(sqlDatasetRight, sqlComponent.getVtlComponent().getName());
            if (otherSqlComponent != null) {
                SqlComponent sqlComponentResult = applyConcatToViralsComponent(sqlComponent, otherSqlComponent);
                sqlComponentsResult.add(sqlComponentResult);
            }
        }

        return sqlComponentsResult;
    }

    /**
     * Applica la concatenazione sue due . Viene utilizzato per mantere gli attributi virali fra le trasformazioni
     *
     * @param sqlComponentLeft
     * @param sqlComponentRight
     * @return
     */
    @Override
    public SqlComponent applyConcatToViralsComponent(SqlComponent sqlComponentLeft, SqlComponent sqlComponentRight) {
        SqlComponent sqlComponentsResult = new SqlComponent();
        sqlComponentsResult.setResult(
                new CustomSql(sqlComponentLeft.getResult() + " || ':' || " + sqlComponentRight.getResult())
        );
        sqlComponentsResult.setAliasName(sqlComponentLeft.getAliasName());
        return sqlComponentsResult;
    }

    /**
     * Dati una lista di lista di componenti viene restituita vengono individuati un insieme di componenti comuni
     * e restituisce una lista specifica ordinata.
     *
     * @param sqlComponents
     * @param tableIndex
     * @return
     */
    @Override
    public LinkedList<SqlComponent> getSpecificCommonComponent(List<List<SqlComponent>> sqlComponents, int tableIndex) {
        LinkedList<SqlComponent> result = new LinkedList<>();
        LinkedList<LinkedList<SqlComponent>> allCommonComponents = getCommonComponent(sqlComponents);
        for (LinkedList<SqlComponent> commmonComponentsTable : allCommonComponents) {
            result.addLast(
                    commmonComponentsTable.get(tableIndex)
            );
        }
        return result;
    }

    /**
     * Estrae da una lista di liste di componenti tutti gli elementi in comune e li restituisce
     *
     * @param sqlComponents
     * @return
     */
    @Override
    public LinkedList<LinkedList<SqlComponent>> getCommonComponent(List<List<SqlComponent>> sqlComponents) {
        LinkedList<LinkedList<SqlComponent>> commonComponentsResult = new LinkedList<>();
        List<SqlComponent> firstComponents = sqlComponents.get(0);
        LinkedList<SqlComponent> commonComponents;
        for (SqlComponent sqlComponent : firstComponents) {
            commonComponents = new LinkedList<>();
            commonComponents.addLast(sqlComponent);
            boolean foundAll = true;
            for (int i = 1; i < sqlComponents.size(); i++) {
                List<SqlComponent> otherComponents = sqlComponents.get(i);
                SqlComponent otherCompoent = getSqlComponentByName(otherComponents, sqlComponent.getVtlComponent().getName());
                if (otherCompoent == null) {
                    foundAll = false;
                } else {
                    commonComponents.addLast(otherCompoent);
                }
            }
            if (foundAll)
                commonComponentsResult.addLast(commonComponents);
        }
        return commonComponentsResult;
    }

    /**
     * Dato un tipo un valore e un ruolo cmponente viene generato un componente scalare
     *
     * @param vtlType
     * @param value
     * @param vtlComponentRole
     * @return
     */
    @Override
    public SqlComponent getScalarComponent(VtlType vtlType, String value, VtlComponentRole vtlComponentRole) {
        VtlComponent vtlDefaultComponent = componentUtilityService.getDefaultComponent(vtlType, null);
        if (vtlComponentRole != null) {
            vtlDefaultComponent.setVtlComponentRole(vtlComponentRole);
        }
        SqlComponent sqlComponent = createSqlComponent(vtlDefaultComponent);
        sqlComponent.setAliasTable("");
        sqlComponent.setResult(new CustomSql("'" + value + "'"));
        return sqlComponent;
    }

    /**
     * Data una costantExpression viene generaot un componente scalare
     *
     * @param vtlConstantExpression
     * @return
     */
    @Override
    public SqlComponent getScalarComponent(VtlConstantExpression vtlConstantExpression) {
        SqlComponent sqlComponent = createSqlComponent(vtlConstantExpression.getResultExpression().getResultComponent());
        sqlComponent.setAliasTable("");
        sqlComponent.setResult(sqlObjectUtilityService.getConstantSql(vtlConstantExpression.getVtlConstant()));
        return sqlComponent;
    }

    /**
     * Costruisce la funzione di cast su un componente
     *
     * @param sqlComponent
     * @param mask
     * @param operator
     * @param vtlType
     * @return
     */
    @Override
    public SqlComponent applyCastOperator(SqlComponent sqlComponent, String mask, Operator operator, VtlType vtlType) {
        FunctionCall fc = FunctionCall.customFunction(sqlObjectUtilityService.getSqLOperator(operator.getValue()))
                .addCustomParams(sqlComponent.getResult(), sqlComponent.getVtlComponent().getType().toString(), mask != null ? mask : "");
        sqlComponent.setResult(
                fc
        );
        sqlComponent.setAliasName(vtlType.getDefaultName());
        return sqlComponent;
    }

    /**
     * Genera un componente che rappresenta la funzione currentDate
     *
     * @param vtlCurrentDate
     * @return
     */
    @Override
    public SqlComponent applyCurrentDateToSqlComponent(VtlCurrentDate vtlCurrentDate) {
        SqlComponent sqlComponent = new SqlComponent();
        sqlComponent.setVtlComponent(vtlCurrentDate.getResultExpression().getResultComponent());
        sqlComponent.setResult(sqlObjectUtilityService.applyCurrentDateToSqlObjects(vtlCurrentDate.getOperator()));
        sqlComponent.setAliasName(vtlCurrentDate.getOperator().getExpectedTypeReturn().getDefaultName());
        return sqlComponent;
    }

    /**
     * Restituisce un componente che rappresenta la funzione di if then else
     *
     * @param sqlComponentCondition
     * @param sqlComponentThen
     * @param sqlComponentElse
     * @param operator
     * @param isWhereCondition
     * @return
     */
    @Override
    public SqlComponent applyIfToSqlComponent(SqlComponent sqlComponentCondition, SqlComponent sqlComponentThen, SqlComponent sqlComponentElse, Operator operator, Boolean isWhereCondition) {
        SqlComponent sqlComponent = new SqlComponent();
        sqlComponent.setResult(sqlObjectUtilityService.applyIfToSqlObject(sqlComponentCondition.getResult(), sqlComponentThen.getResult(), sqlComponentElse.getResult(), isWhereCondition));
        if (operator.getSubstituteComponent()) {
            //se si tratta di una sostituzione va preso il campo del dataset precedente con il nome di quello risultante per simulare il cast
            sqlComponent.setAliasName(operator.getExpectedTypeReturn().getDefaultName());
        }
        return sqlComponent;
    }
}
