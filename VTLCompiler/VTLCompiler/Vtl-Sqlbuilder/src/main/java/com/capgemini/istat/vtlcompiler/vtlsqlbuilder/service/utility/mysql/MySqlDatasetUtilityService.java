package com.capgemini.istat.vtlcompiler.vtlsqlbuilder.service.utility.mysql;

import com.capgemini.istat.vtlcompiler.vtlcommon.model.dataset.VtlComponentRole;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.dataset.VtlDataset;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.dataset.VtlType;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.operator.Operator;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.translation.SqlComponent;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.translation.SqlDataset;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.translation.SqlTable;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.VtlExpression;
import com.capgemini.istat.vtlcompiler.vtlsqlbuilder.service.utility.CommonSqlDatasetUtilityService;
import com.healthmarketscience.sqlbuilder.CustomSql;
import com.healthmarketscience.sqlbuilder.FunctionCall;
import com.healthmarketscience.sqlbuilder.OrderObject;
import com.healthmarketscience.sqlbuilder.WindowDefinitionClause;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * La classe offre manipolazioni di dataset e SqlDataset al fine di giungere alla traduzione verso MySQL
 *
 * @see MySqlObjectUtilityService
 * @see MySqlComponentUtilityService
 */
@Service
public class MySqlDatasetUtilityService extends CommonSqlDatasetUtilityService {

    @Autowired
    public void setSqlObjectUtilityService(MySqlObjectUtilityService mySqlObjectUtilityService) {
        super.setSqlObjectUtilityService(mySqlObjectUtilityService);
    }

    @Autowired
    public void setSqlComponentUtilityService(MySqlComponentUtilityService mySqlComponentUtilityService) {
        super.setSqlComponentUtilityService(mySqlComponentUtilityService);
    }

    /**
     * Applica una funzione di aggregazione a un SqlDataset.
     * Il metodo offre una gestione sperata dell'operatore MEDIAN
     *
     * @param sqlDataset
     * @param parameter
     * @param optionalParameter
     * @param operator
     * @return
     */
    @Override
    public SqlDataset applyAggregateFunctionToSqlDataset(SqlDataset sqlDataset, VtlExpression parameter, List<VtlExpression> optionalParameter, Operator operator) {
        SqlDataset sqlDatasetResult = copySqlDatasetWithoutSqlComponent(sqlDataset);
        // Nelle aggregazioni i componenti Virali non sono MAI riportati
        // (concordato con ISTAT)
        if (!operator.getValue().equals(Operator.MEDIAN.getValue())) {
            for (SqlComponent sqlComponent : sqlDataset.getComponentList()) {
                if (sqlComponent.getVtlComponent().getVtlComponentRole() == VtlComponentRole.IDENTIFIER) {
                    sqlDatasetResult.getComponentList().add(sqlComponent);
                }
                if (sqlComponent.getVtlComponent().getVtlComponentRole() == VtlComponentRole.MEASURE) {
                    sqlDatasetResult.getComponentList().add(sqlComponentUtilityService
                            .applyUnaryFunctionToSqlComponent(sqlComponent, parameter, optionalParameter, operator));
                }
            }
            return sqlDatasetResult;
        }

        if (operator.getValue().equals(Operator.MEDIAN.getValue())) {
            sqlDatasetResult = new SqlDataset();
            sqlDatasetResult.setSqlTables(copySqlTables(sqlDataset.getSqlTables()));
            List<SqlComponent> compsMeasures = sqlComponentUtilityService.getSqlComponentsByRole(
                    sqlDataset.getComponentList(),
                    VtlComponentRole.MEASURE
            );
            List<SqlTable> sqlTables = new ArrayList<>();
            List<SqlComponent> firstIdentifiers = new ArrayList<>();
            int i = 0;
            String firstAlias = "";
            for (SqlComponent compMeasure : compsMeasures) {
                SqlTable sqlTable = new SqlTable();
                SqlDataset sqlDatasetMedian = createSqlDatasetMedian(sqlDataset, compMeasure);
                sqlTable.setCustomFrom(sqlObjectUtilityService.createSelectFromSqlDataset(sqlDatasetMedian));
                sqlTable.setVtlDataset(sqlDatasetMedian.getSqlTables().get(0).getVtlDataset());
                String alias = dbTableUtilityService.getDbSpec().getNextAlias();
                sqlTable.setAliasName(alias);
                SqlComponent measure = sqlComponentUtilityService.copySqlComponent(compMeasure);
                measure.setUpAliasTable("tx", alias);
                if (i == 0) {
                    firstAlias = alias;
                    firstIdentifiers =
                            sqlComponentUtilityService.getSqlComponentsByRole(
                                    sqlDatasetMedian.getComponentList(),
                                    VtlComponentRole.IDENTIFIER
                            );
                    for (SqlComponent sqlComponent : firstIdentifiers) {
                        sqlComponent = sqlComponentUtilityService.copySqlComponent(sqlComponent);
                        sqlComponent.setUpAliasTable(sqlComponent.getAliasTable(), alias);
                        sqlDatasetResult.getComponentList().add(sqlComponent);
                        sqlTable.setVtlDataset(sqlDataset.getSqlTables().get(0).getVtlDataset());
                    }
                } else {
                    String onCondition = "";
                    for (SqlComponent sqlComponent : firstIdentifiers) {
                        SqlComponent identifier = sqlComponentUtilityService.copySqlComponent(sqlComponent);
                        identifier.setUpAliasTable(identifier.getAliasTable(), alias);
                        SqlComponent firstIdentifier = sqlComponentUtilityService.copySqlComponent(sqlComponent);
                        firstIdentifier.setUpAliasTable(firstIdentifier.getAliasTable(), firstAlias);
                        onCondition = onCondition + firstIdentifier.getResult().toString() + " AND " + identifier.getResult().toString();
                    }
                    sqlTable.setOnConditions(new CustomSql(onCondition));
                }
                sqlDatasetResult.getComponentList().add(measure);
                sqlTables.add(sqlTable);
                i++;
            }
            sqlDatasetResult.setSqlTables(sqlTables);
        }
        return sqlDatasetResult;
    }

    private SqlDataset createSqlDatasetMedian(SqlDataset sqlDataset, SqlComponent compMeasure) {
        SqlDataset sqlDatasetResult = new SqlDataset();
        VtlDataset vtlDataset = datasetUtilityService.createTemporaryDataset(sqlDataset.getSqlTables().get(0).getVtlDataset().getIdentifiers(), false);
        vtlDataset.addComponent(compMeasure.getVtlComponent());
        sqlDatasetResult.setSqlTables(copySqlTables(sqlDataset.getSqlTables()));
        sqlDatasetResult.getSqlTables().get(0).setVtlDataset(vtlDataset);
        List<SqlComponent> identifiers = sqlComponentUtilityService.getSqlComponentsByRole(sqlDataset.getComponentList(), VtlComponentRole.IDENTIFIER);
        sqlDatasetResult.getComponentList().addAll(identifiers);
        SqlComponent component = sqlComponentUtilityService.copySqlComponent(compMeasure);
        component.setResult(FunctionCall.avg().addCustomParams(component.getResult()));
        sqlDatasetResult.getComponentList().add(component);

        SqlDataset sqlDatasetCustomFrom = new SqlDataset();
        sqlDatasetCustomFrom.setSqlTables(copySqlTables(sqlDataset.getSqlTables()));
        sqlDatasetCustomFrom.getComponentList().addAll(sqlComponentUtilityService.getSqlComponentsByRole(sqlDataset.getComponentList(), VtlComponentRole.IDENTIFIER));
        sqlDatasetCustomFrom.getComponentList().add(sqlComponentUtilityService.copySqlComponent(compMeasure));

        FunctionCall fc_row = FunctionCall.rowNumber();
        WindowDefinitionClause wdc_row = new WindowDefinitionClause();

        for (SqlComponent sqlComponent_wdc : sqlDataset.getComponentList()) {
            if (sqlComponent_wdc.getVtlComponent().getVtlComponentRole() == VtlComponentRole.IDENTIFIER) {
                wdc_row.addPartitionColumns(sqlComponent_wdc.getResult());
            }
            if (sqlComponent_wdc.getAliasName().equalsIgnoreCase(compMeasure.getAliasName())) {
                wdc_row.addOrdering(sqlComponent_wdc.getResult(), OrderObject.Dir.ASCENDING);
            }
        }
        SqlComponent mrank = sqlComponentUtilityService.createSqlComponent(VtlType.NUMBER, "Mrank", VtlComponentRole.MEASURE);
        mrank.setResult(fc_row.setWindow(wdc_row));
        sqlDatasetCustomFrom.getComponentList().add(mrank);


        FunctionCall fc_count = FunctionCall.countAll();
        WindowDefinitionClause wdc_count = new WindowDefinitionClause();

        for (SqlComponent sqlComponent_count : sqlDataset.getComponentList()) {
            if (sqlComponent_count.getVtlComponent().getVtlComponentRole() == VtlComponentRole.IDENTIFIER) {
                wdc_count.addPartitionColumns(sqlComponent_count.getResult());
            }
        }
        SqlComponent mCount = sqlComponentUtilityService.createSqlComponent(VtlType.INTEGER, "mCount", VtlComponentRole.MEASURE);
        mCount.setResult(fc_count.setWindow(wdc_count));
        sqlDatasetCustomFrom.getComponentList().add(mCount);
        sqlDatasetResult.getSqlTables().get(0).setCustomFrom(sqlObjectUtilityService.createSelectFromSqlDataset(sqlDatasetCustomFrom));
        String whereCond = "tx.Mrank IN (tx.MCount / 2+1 , floor((tx.MCount + 1) / 2))";
        sqlDatasetResult.setWhereCondition(new CustomSql(whereCond));

        for (SqlComponent sqlComponent_val : identifiers) {
            sqlDatasetResult.getGroupByClauseColumns().add(sqlComponent_val.getResult());
        }
        return sqlDatasetResult;
    }
}
