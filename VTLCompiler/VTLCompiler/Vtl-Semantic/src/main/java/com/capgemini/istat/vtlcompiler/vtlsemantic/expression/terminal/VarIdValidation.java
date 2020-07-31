package com.capgemini.istat.vtlcompiler.vtlsemantic.expression.terminal;

import com.capgemini.istat.vtlcompiler.vtlcommon.model.KeyVariables;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.dataset.VtlDataset;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.operator.Operator;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.operator.OperatorValidation;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.semantic.ResultExpression;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.dataset.VtlVarId;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.VtlExpression;
import com.capgemini.istat.vtlcompiler.vtlsemantic.service.result.FunctionResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.Map;
import java.util.UUID;

@Component
@Scope("prototype")
public class VarIdValidation implements OperatorValidation {
    private VtlVarId vtlVarId;
    private FunctionResultService functionResultService;

    @Override
    public void setVtlExpression(VtlExpression vtlExpression) {
        this.vtlVarId = (VtlVarId) vtlExpression;
    }

    @Autowired
    public void setFunctionResultService(FunctionResultService functionResultService) {
        this.functionResultService = functionResultService;
    }


    @Override
    public LinkedList<ResultExpression> resolve(Map<KeyVariables, Object> variables) throws Exception {
        LinkedList<ResultExpression> resultExpressions = new LinkedList<>();
        ResultExpression resultExpression = null;
        Map<String, VtlExpression> parameterVar = (Map<String, VtlExpression>) variables.get(KeyVariables.PARAMETER_MAP);
        if (parameterVar != null) {
            VtlExpression variable = parameterVar.get(vtlVarId.getName());
            if (variable != null) {
                resultExpression = variable.getResultExpression();
            }
        }
        if (resultExpression == null) {
            if (variables.get(KeyVariables.DATASET_IN_CLAUSE) != null) {
                VtlDataset vtlDatasetInClause = (VtlDataset) variables.get(KeyVariables.DATASET_IN_CLAUSE);
                String componentName = vtlVarId.getName();
                resultExpression = functionResultService.getComponent(
                        vtlDatasetInClause,
                        componentName,
                        Operator.COMPONENT,
                        vtlVarId.isIgnoreCase()
                );
            } else { //  va controllata l'esistenza del dataset e va restituito
                UUID requestUuid = (UUID) variables.get(KeyVariables.REQUEST_UUID);
                vtlVarId.setRequestUuid(requestUuid);
                resultExpression = functionResultService.getDatasetByName(
                        vtlVarId
                );
            }
        }
        if (resultExpression != null) {
            resultExpression.setCommand(vtlVarId.getCommand());
            resultExpressions.addFirst(resultExpression);
        }
        return resultExpressions;
    }
}
