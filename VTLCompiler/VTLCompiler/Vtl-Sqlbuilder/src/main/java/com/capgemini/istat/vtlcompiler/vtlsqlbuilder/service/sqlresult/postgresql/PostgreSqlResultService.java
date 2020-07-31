package com.capgemini.istat.vtlcompiler.vtlsqlbuilder.service.sqlresult.postgresql;

import com.capgemini.istat.vtlcompiler.vtlcommon.model.dataset.VtlComponentRole;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.translation.SqlComponent;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.translation.SqlDataset;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.translation.SqlResult;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.clause.pivot.VtlPivotClauseExpression;
import com.capgemini.istat.vtlcompiler.vtlsqlbuilder.service.sqlresult.CommonSqlResultService;
import com.capgemini.istat.vtlcompiler.vtlsqlbuilder.service.utility.postgresql.PostgreSqlComponentUtilityService;
import com.capgemini.istat.vtlcompiler.vtlsqlbuilder.service.utility.postgresql.PostgreSqlDatasetUtilityService;
import com.capgemini.istat.vtlcompiler.vtlsqlbuilder.service.utility.postgresql.PostgreSqlObjectUtilityService;
import com.healthmarketscience.sqlbuilder.SqlObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PostgreSqlResultService extends CommonSqlResultService {

    @Autowired
    public void setOracleSqlComponentUtilityService(PostgreSqlComponentUtilityService postgreSqlComponentUtilityService) {
        super.setSqlComponentUtilityService(postgreSqlComponentUtilityService);
    }

    @Autowired
    public void setOracleSqlDatasetUtilityService(PostgreSqlDatasetUtilityService postgreSqlDatasetUtilityService) {
        super.setSqlDatasetUtilityService(postgreSqlDatasetUtilityService);
    }

    @Autowired
    public void setOracleSqlObjectUtilityService(PostgreSqlObjectUtilityService postgreSqlObjectUtilityService) {
        super.setSqlObjectUtilityService(postgreSqlObjectUtilityService);
    }

    @Override
    public SqlResult applyPivot(VtlPivotClauseExpression vtlPivotClauseExpression) {
        SqlResult sqlResult = new SqlResult();
        SqlDataset sqlDatasetResult = sqlDatasetUtilityService.createSqlDataset(vtlPivotClauseExpression.getResultExpression().getResult());
        for (SqlComponent sqlComponent : sqlDatasetResult.getComponentList()) {
            sqlComponent.setResult(sqlObjectUtilityService.buildComponentPivot(sqlComponent, vtlPivotClauseExpression));
        }
        sqlDatasetResult.getSqlTables().clear();
        sqlDatasetResult.getSqlTables().add(vtlPivotClauseExpression.getVtlDataset().getSqlResult().getSqlDataset().getSqlTables().get(0));

        List<SqlObject> compsToGroupBy = new ArrayList<>();
        List<SqlComponent> idsCompList = sqlComponentUtilityService.getSqlComponentsByRole(sqlDatasetResult.getComponentList(), VtlComponentRole.IDENTIFIER);
        for (SqlComponent sqlComponent : idsCompList) {
            compsToGroupBy.add(sqlComponent.getResult());
        }
        sqlDatasetResult.getGroupByClauseColumns().addAll(compsToGroupBy);
        sqlResult.setSqlDataset(sqlDatasetResult);
        return sqlResult;
    }

}
