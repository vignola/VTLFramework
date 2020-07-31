package com.capgemini.istat.vtlcompiler.vtlsqlbuilder.expression.time;

import com.capgemini.istat.vtlcompiler.vtlcommon.model.KeyVariables;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.translation.SqlDataset;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.translation.SqlResult;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.translation.SqlTable;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.VtlExpression;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.time.VtlCurrentDate;
import com.capgemini.istat.vtlcompiler.vtlsqlbuilder.model.OperatorTranslation;
import com.capgemini.istat.vtlcompiler.vtlsqlbuilder.service.sqlresult.ISqlResultService;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@Scope("prototype")
public class CurrentDateTranslation implements OperatorTranslation {
    
    private VtlCurrentDate vtlCurrentDate;
    private ISqlResultService sqlResultService;
    
    @Override
    public void setVtlExpression(VtlExpression vtlExpression) {
        this.vtlCurrentDate = (VtlCurrentDate) vtlExpression;
    }


    public void setSqlResultService(ISqlResultService sqlResultService) {
        this.sqlResultService = sqlResultService;
    }

    @Override
    public SqlResult translate(Map<KeyVariables, Object> variables) throws Exception {
        SqlDataset sqlDataset = new SqlDataset();
        SqlResult sqlResult = sqlResultService.applyCurrentDate(vtlCurrentDate);
        if (variables.get(KeyVariables.DATASET_IN_CLAUSE) == null) {
            SqlTable sqlTable = new SqlTable();
            sqlTable.setVtlDataset(vtlCurrentDate.getResultExpression().getResult());
            sqlDataset.getSqlTables().add(sqlTable);
            sqlDataset.getComponentList().add(sqlResult.getSqlComponent());
            sqlResult.setSqlDataset(sqlDataset);
        } else {
            sqlResult.setSqlDataset(
                    (SqlDataset) variables.get(KeyVariables.DATASET_IN_CLAUSE)
            );
        }

        return sqlResult;
    }
}
