package com.capgemini.istat.vtlcompiler.vtlsqlbuilder.service.sqlresult.oracle;

import com.capgemini.istat.vtlcompiler.vtlcommon.model.dataset.VtlComponentRole;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.operator.Operator;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.translation.SqlDataset;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.translation.SqlResult;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.hierarchy.VtlHierarchyExpression;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.validation.OutputMode;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.validation.VtlCheckHierarchy;
import com.capgemini.istat.vtlcompiler.vtlsqlbuilder.service.sqlresult.CommonSqlResultService;
import com.capgemini.istat.vtlcompiler.vtlsqlbuilder.service.utility.oracle.OracleSqlComponentUtilityService;
import com.capgemini.istat.vtlcompiler.vtlsqlbuilder.service.utility.oracle.OracleSqlDatasetUtilityService;
import com.capgemini.istat.vtlcompiler.vtlsqlbuilder.service.utility.oracle.OracleSqlObjectUtilityService;
import com.healthmarketscience.sqlbuilder.CustomSql;
import com.healthmarketscience.sqlbuilder.SelectQuery;
import com.healthmarketscience.sqlbuilder.SqlObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OracleSqlResultService extends CommonSqlResultService {

    @Autowired
    public void setOracleSqlComponentUtilityService(OracleSqlComponentUtilityService oracleSqlComponentUtilityService) {
        super.setSqlComponentUtilityService(oracleSqlComponentUtilityService);
    }

    @Autowired
    public void setOracleSqlDatasetUtilityService(OracleSqlDatasetUtilityService oracleSqlDatasetUtilityService) {
        super.setSqlDatasetUtilityService(oracleSqlDatasetUtilityService);
    }

    @Autowired
    public void setOracleSqlObjectUtilityService(OracleSqlObjectUtilityService oracleSqlObjectUtilityService) {
        super.setSqlObjectUtilityService(oracleSqlObjectUtilityService);
    }

    @Override
    public SqlResult hierarchy(VtlHierarchyExpression vtlHierarchyExpression) {
        SqlResult sqlResult = new SqlResult();
        SqlObject buildModel = sqlObjectUtilityService.buildModel(vtlHierarchyExpression);
        SqlObject buildFrom = new CustomSql("SELECT * FROM " + vtlHierarchyExpression.getVtlOperandExpression().getResultExpression().getResult().getName() + "  " + buildModel);

        SqlDataset sqlDatasetHierarchyComputed = sqlDatasetUtilityService.buildHierarchyComputedDataset(vtlHierarchyExpression, buildFrom);

        String computedName = vtlHierarchyExpression.getResultExpression().getResult().getName();
        if (vtlHierarchyExpression.getOutputMode() == OutputMode.ALL) {
            computedName = vtlHierarchyExpression.getResultExpression().getResult().getName() + "_computed";
        }

        SqlDataset sqlDatasetResult = sqlDatasetUtilityService.createSqlDataset(vtlHierarchyExpression.getResultExpression().getResult());
        SqlObject createTable = sqlObjectUtilityService.createTableQuery(computedName,
                sqlObjectUtilityService.createSelectFromSqlDataset(sqlDatasetHierarchyComputed)
        );
        List<SqlObject> createIndex = sqlObjectUtilityService.createIndex(
                sqlComponentUtilityService.getSqlComponentsByRole(sqlDatasetHierarchyComputed.getComponentList(), VtlComponentRole.IDENTIFIER),
                computedName
        );
        sqlResult.setSqlDataset(sqlDatasetResult);
        sqlResult.getResultList().addLast(createTable);
        sqlResult.getResultList().addAll(createIndex);

        if (vtlHierarchyExpression.getOutputMode() == OutputMode.ALL) {
            SqlDataset resultCopied = sqlDatasetUtilityService.buildDatasetWithoutViral(vtlHierarchyExpression.getVtlOperandExpression().getResultExpression().getResult());
            resultCopied.getSqlTables().get(0).getVtlDataset().setName(computedName);
            resultCopied.setUpAlias(dbTableUtilityService.getDbSpec().getNextAlias(), resultCopied.getSqlTables().get(0).getVtlDataset());
            SqlDataset sqlDatasetOperandWithoutViral = sqlDatasetUtilityService.buildDatasetWithoutViral(vtlHierarchyExpression.getVtlOperandExpression().getResultExpression().getResult());
            sqlObjectUtilityService.createSelectFromSqlDataset(
                    sqlDatasetOperandWithoutViral
            );

            SqlDataset sqlDsBinaryJoin = sqlDatasetUtilityService.createQueryForSetBinaryJoin(sqlDatasetOperandWithoutViral, resultCopied);
            sqlObjectUtilityService.createSelectFromSqlDataset(sqlDsBinaryJoin);
            sqlObjectUtilityService.createSelectFromSqlDataset(
                    resultCopied
            );
            List<SqlDataset> sqlDatasets = new ArrayList<>();
            sqlDatasets.add(sqlDsBinaryJoin);
            sqlDatasets.add(resultCopied);
            List<SqlDataset> sqlDatasetListWithRank = sqlDatasetUtilityService.buildSqlDatasetWithPriorityComponents(sqlDatasets);
            SqlObject unionQuery = sqlObjectUtilityService.createUnionQueryForSetUnnary(sqlDatasetListWithRank, Operator.UNION);
            SqlObject createTableQuery = sqlObjectUtilityService.createTableQuery(vtlHierarchyExpression.getResultExpression().getResult().getName(),
                    unionQuery);
            sqlResult.getResultList().addLast(createTableQuery);
            sqlResult.getResultList().addAll(sqlObjectUtilityService.createIndex(
                    sqlComponentUtilityService.getSqlComponentsByRole(sqlDatasetHierarchyComputed.getComponentList(), VtlComponentRole.IDENTIFIER),
                    vtlHierarchyExpression.getResultExpression().getResult().getName())
            );
        }
        return sqlResult;
    }

    @Override
    public SqlResult checkHierarchy(VtlCheckHierarchy vtlCheckHierarchy) {
        SqlResult sqlResult = new SqlResult();
        SqlObject errorRulesest = sqlObjectUtilityService.buildRuleTable(vtlCheckHierarchy.getVtlHierarchicalRuleset().getVtlHRRules());
        SqlObject model = sqlObjectUtilityService.buildCheckModel(vtlCheckHierarchy);
        SqlDataset datasetCleaned = sqlDatasetUtilityService.cleanDatasetForModal(vtlCheckHierarchy.getVtlVarId().getResultExpression().getResult());
        SelectQuery selectQueryCleaned = sqlObjectUtilityService.createSelectFromSqlDataset(datasetCleaned);
        String query = "SELECT *" +
                "  FROM" +
                " (SELECT * FROM  (" + selectQueryCleaned.toString() + ") ) A" +
                " CROSS JOIN" +
                "( " + errorRulesest.toString() + ") B ";
        SqlObject selectModal = new CustomSql(query + model.toString());

        SqlDataset sqlDatasetWithModal = sqlDatasetUtilityService.buildCheckHierarchyModal(vtlCheckHierarchy, selectModal);
        SqlDataset sqlDatasetOperand = sqlDatasetUtilityService.createSqlDataset(vtlCheckHierarchy.getVtlVarId().getResultExpression().getResult());


        SqlDataset sqlModalWithViral = sqlDatasetUtilityService.addViralAttribute(sqlDatasetOperand, sqlDatasetWithModal);
        SqlDataset sqlDatasetResult = sqlDatasetUtilityService.createDatasetCheckHierarchical(vtlCheckHierarchy, sqlModalWithViral);
        SelectQuery selectQuery = sqlObjectUtilityService.createSelectFromSqlDataset(sqlDatasetResult);
        sqlResult.getResultList().add(sqlObjectUtilityService.createTableQuery(vtlCheckHierarchy.getResultExpression().getResult().getName(), selectQuery));
        sqlResult.setSqlDataset(sqlDatasetUtilityService.createSqlDataset(vtlCheckHierarchy.getResultExpression().getResult()));
        return sqlResult;
    }
}
