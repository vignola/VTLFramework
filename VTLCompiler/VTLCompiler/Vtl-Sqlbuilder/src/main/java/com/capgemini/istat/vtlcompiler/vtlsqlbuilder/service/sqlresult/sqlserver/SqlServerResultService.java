package com.capgemini.istat.vtlcompiler.vtlsqlbuilder.service.sqlresult.sqlserver;

import com.capgemini.istat.vtlcompiler.vtlcommon.model.translation.SqlDataset;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.translation.SqlResult;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.clause.pivot.VtlPivotClauseExpression;
import com.capgemini.istat.vtlcompiler.vtlsqlbuilder.service.sqlresult.CommonSqlResultService;
import com.capgemini.istat.vtlcompiler.vtlsqlbuilder.service.utility.sqlserver.SqlServerComponentUtilityService;
import com.capgemini.istat.vtlcompiler.vtlsqlbuilder.service.utility.sqlserver.SqlServerDatasetUtilityService;
import com.capgemini.istat.vtlcompiler.vtlsqlbuilder.service.utility.sqlserver.SqlServerObjectUtilityService;
import com.healthmarketscience.sqlbuilder.SqlObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SqlServerResultService extends CommonSqlResultService {

    @Autowired
    public void setSqlComponentUtilityService(SqlServerComponentUtilityService sqlServerComponentUtilityService) {
        super.setSqlComponentUtilityService(sqlServerComponentUtilityService);
    }

    @Autowired
    public void setSqlDatasetUtilityService(SqlServerDatasetUtilityService sqlServerDatasetUtilityService) {
        super.setSqlDatasetUtilityService(sqlServerDatasetUtilityService);
    }

    @Autowired
    public void setSqlObjectUtilityService(SqlServerObjectUtilityService sqlServerObjectUtilityService) {
        super.setSqlObjectUtilityService(sqlServerObjectUtilityService);
    }

    @Override
    public SqlResult applyPivot(VtlPivotClauseExpression vtlPivotClauseExpression) {
        SqlResult sqlResult = new SqlResult();

        SqlDataset sqlDatasetFrom = vtlPivotClauseExpression.getVtlDataset().getSqlResult().getSqlDataset();

        SqlDataset sqlDatasetPivot = sqlDatasetUtilityService.createSqlDataset(vtlPivotClauseExpression.getResultExpression().getResult());
        
        SqlObject selectQueryUnpivot = sqlObjectUtilityService.createSelectForPivot(vtlPivotClauseExpression.getIdentifier(), vtlPivotClauseExpression.getMeasure(), vtlPivotClauseExpression.getConstantExpressions(), sqlDatasetFrom, sqlDatasetPivot);
       
        SqlResult sqlResultUnpivot = createTable(sqlDatasetPivot, vtlPivotClauseExpression.getResultExpression().getResult().getName(), selectQueryUnpivot, true);

        sqlResult.getResultList().addAll(sqlResultUnpivot.getResultList());
        sqlResult.setSqlDataset(sqlDatasetPivot);

        return sqlResult;
    }

}
