package com.capgemini.istat.vtlcompiler.vtlsqlbuilder.service.utility.oracle;

import com.capgemini.istat.vtlcompiler.vtlcommon.constant.ConstantUtility;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.dataset.VtlComponentRole;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.translation.SqlComponent;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.translation.SqlDataset;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.constant.VtlConstantExpression;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.dataset.VtlComponentId;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.clause.pivot.VtlPivotClauseExpression;
import com.capgemini.istat.vtlcompiler.vtlsqlbuilder.service.utility.CommonSqlObjectUtilityService;
import com.healthmarketscience.sqlbuilder.CustomSql;
import com.healthmarketscience.sqlbuilder.SelectQuery;
import com.healthmarketscience.sqlbuilder.SqlObject;
import com.healthmarketscience.sqlbuilder.custom.HookType;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
/**
 * La classe contiene la logica di costruzione dei diversi operatori SQL necessari per implementare i comandi VTL verso SQL
 * I metodi restituiscono specifiche implementazioni di un SqlObject. Un SqlObject è loggetto utilizzato dalla libreria
 * SqlBuilder per rappresentare gli elementi SQL
 * La classe offre dei metodi specifici per OracleSQL
 */
@Service
public class OracleSqlObjectUtilityService extends CommonSqlObjectUtilityService {
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
     * Costruisce una from che contiene una nuova select invece che un nome tabella
     *
     * @param selectQuery
     */
    @Override
    public void addCustomFrom(SelectQuery selectQuery) {
        selectQuery.addCustomFromTable(ConstantUtility.DUMMY_TABLE);
    }

    /**
     * Genera una query di unpivot per OracleSQL
     *
     * @param identifier
     * @param measure
     * @param sqlDatasetFrom
     * @param sqlDatasetUnpivot
     * @return
     */
    @Override
    public SqlObject createSelectForUnpivot(VtlComponentId identifier, VtlComponentId measure, SqlDataset sqlDatasetFrom, SqlDataset sqlDatasetUnpivot) {
        SelectQuery selectQuery = new SelectQuery();

        sqlDatasetUnpivot.setUpAlias(dbTableUtilityService.getDbSpec().getNextAlias(), sqlDatasetUnpivot.getSqlTables().get(0).getVtlDataset());

        for (SqlComponent sqlComponent : sqlDatasetUnpivot.getComponentList()) {
            selectQuery.addCustomColumns(new CustomSql(sqlComponent.getAliasName()));
        }

        //The Unpivot Query has been created without alias table
        selectQuery.addCustomFromTable(getFormattedTableName(sqlDatasetFrom.getSqlTables().get(0).getVtlDataset().getName()));

        String comma = "";
        String unpivotCommand = " UNPIVOT (" + measure.getComponentName() + " for " + identifier.getComponentName() + " in (";
        for (SqlComponent sqlComponent : sqlDatasetFrom.getComponentList()) {
            if (sqlComponent.getVtlComponent().getVtlComponentRole() == VtlComponentRole.MEASURE) {
                unpivotCommand += comma + sqlComponent.getAliasName() + " as '" + sqlComponent.getAliasName() + "'";
                if (comma.isEmpty()) comma = ",";
            }
        }
        unpivotCommand += ") )";
        selectQuery.addCustomization(SelectQuery.Hook.WHERE, HookType.BEFORE, unpivotCommand);

        return selectQuery;
    }

    /**
     * Genera una query di pivot per OracleSQL
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
            try {
                Integer.parseInt(vtlConstantExpression.getVtlConstant().getValue().toString());
            	pivot = pivot + constantString + " AS " +
                        vtlPivotClauseExpression.getIdentifier().getComponentName() + "_" + vtlConstantExpression.getVtlConstant().getValue();
			} catch (Exception e) {
				pivot = pivot + constantString + " AS " +
	                     vtlConstantExpression.getVtlConstant().getValue();
			}
                        
        }

        pivot = pivot + ")";
        pivot = pivot + ")";
        return new CustomSql(pivot);
    }

    //getSqLOperator valid for Oracle
    private static final Map<String, String> substituteOperator;
    static {
        Map<String, String> map = new HashMap<>();
        map.put("AND", "vtl_pkg.and_bool");
        map.put("OR", "vtl_pkg.or_bool");
        map.put("XOR", "vtl_pkg.xor_bool");
        map.put("NOT", "vtl_pkg.not_bool");
        map.put("match_characters", "REGEXP_LIKE");
        map.put("timeshift", "vtl_pkg.timeshift");
        map.put("period_indicator", "vtl_pkg.period_indicator");
        map.put("fill_time_series_single", "vtl_pkg.fill_time_series");
        map.put("fill_time_series_all", "vtl_pkg.fill_time_series");
        String value = "vtl_pkg.time_agg";
        map.put("time_agg", value);
        map.put("time_agg_first", value);
        map.put("time_agg_last", value);
        map.put("cast_to_int", "vtl_pkg.cast_to_int");
        map.put("cast_to_number", "vtl_pkg.cast_to_number");
        map.put("cast_to_boolean", "vtl_pkg.cast_to_boolean");
        map.put("cast_to_time", "vtl_pkg.cast_to_time");
        map.put("cast_to_date", "vtl_pkg.cast_to_date");
        map.put("cast_to_timeperiod", "vtl_pkg.cast_to_timeperiod");
        map.put("cast_to_string", "vtl_pkg.cast_to_string");
        map.put("cast_to_duration", "vtl_pkg.cast_to_duration");
        substituteOperator = Collections.unmodifiableMap(map);
    }
}
