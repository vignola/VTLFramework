package com.capgemini.istat.vtlcompiler.vtlsqlbuilder.expression.terminal;

import com.capgemini.istat.vtlcompiler.vtlcommon.model.KeyVariables;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.translation.SqlDataset;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.translation.SqlResult;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.dataset.VtlComponentId;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.dataset.VtlVarId;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.VtlExpression;
import com.capgemini.istat.vtlcompiler.vtlsqlbuilder.model.OperatorTranslation;
import com.capgemini.istat.vtlcompiler.vtlsqlbuilder.service.sqlresult.ISqlResultService;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
@Scope("prototype")
public class VarIdTranslation implements OperatorTranslation {
    private VtlVarId vtlVarId;
    private ISqlResultService sqlResultService;


    public void setSqlResultService(ISqlResultService sqlResultService) {
        this.sqlResultService = sqlResultService;
    }

    @Override
    public void setVtlExpression(VtlExpression vtlExpression) {
        this.vtlVarId = (VtlVarId) vtlExpression;
    }

    @Override
    public SqlResult translate(Map<KeyVariables, Object> variables) throws Exception {
        SqlResult sqlResult;

        Map<String, VtlExpression> parameterMap = (Map<String, VtlExpression>) variables.get(KeyVariables.PARAMETER_MAP);
        Map<String, VtlComponentId> parameterUsed = (Map<String, VtlComponentId>) variables.get(KeyVariables.PARAMETER_USED);
        if (parameterUsed == null) {
            parameterUsed = new HashMap<>();
        }

        if (parameterMap != null) {
            //Call operator scenario
            sqlResult = parameterMap.get(vtlVarId.getName()).getSqlResult();
            if (parameterMap.get(vtlVarId.getName()) instanceof VtlComponentId) {
                VtlComponentId vtlComponentId = (VtlComponentId) parameterMap.get(vtlVarId.getName());

                parameterUsed.put(vtlComponentId.getComponentName(), vtlComponentId);
                variables.put(KeyVariables.PARAMETER_USED, parameterUsed);
            }
        } else {
            sqlResult = sqlResultService.getVarIdTableResult(
                    vtlVarId.getResultExpression().isAComponent(),
                    vtlVarId.getResultExpression().isAComponent() ? (SqlDataset) variables.get(KeyVariables.DATASET_IN_CLAUSE) : null,
                    vtlVarId
            );            
        }


        return sqlResult;
    }
}
