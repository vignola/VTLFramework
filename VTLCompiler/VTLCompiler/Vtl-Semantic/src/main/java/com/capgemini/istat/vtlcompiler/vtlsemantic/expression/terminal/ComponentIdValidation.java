package com.capgemini.istat.vtlcompiler.vtlsemantic.expression.terminal;

import com.capgemini.istat.vtlcompiler.vtlcommon.model.KeyVariables;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.dataset.VtlDataset;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.operator.Operator;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.operator.OperatorValidation;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.semantic.ResultExpression;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.dataset.VtlComponentId;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.VtlExpression;
import com.capgemini.istat.vtlcompiler.vtlsemantic.service.result.FunctionResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.Map;

@Component
@Scope("prototype")
public class ComponentIdValidation implements OperatorValidation {
    private VtlComponentId vtlComponentId;
    private FunctionResultService functionResultService;

    @Override
    public void setVtlExpression(VtlExpression vtlExpression) {
        this.vtlComponentId = (VtlComponentId) vtlExpression;
    }


    @Autowired
    public void setFunctionResultService(FunctionResultService functionResultService) {
        this.functionResultService = functionResultService;
    }

    @Override
    public LinkedList<ResultExpression> resolve(Map<KeyVariables, Object> variables) throws Exception {
        LinkedList<ResultExpression> result = new LinkedList<>();
        Map<String, VtlExpression> parameterVar = (Map<String, VtlExpression>) variables.get(KeyVariables.PARAMETER_MAP);
        if (parameterVar != null) {
            VtlExpression variable = parameterVar.get(vtlComponentId.getComponentName());
            if (variable != null) {
                result.addFirst(variable.getResultExpression());
            }
        }
        if (result.size() == 0) {
            VtlDataset vtlDatasetClause = (VtlDataset) variables.get(KeyVariables.DATASET_IN_CLAUSE);
            VtlDataset vtlDatasetMembership = (VtlDataset) variables.get(KeyVariables.DATASET_MEMBERSHIP);
            VtlDataset vtlDataset;
            if (vtlDatasetMembership != null)
                vtlDataset = vtlDatasetMembership;
            else
                vtlDataset = vtlDatasetClause;

            result.addFirst(
                    functionResultService.getComponent(
                            vtlDataset,
                            vtlComponentId.getComponentName(),
                            Operator.COMPONENT,
                            vtlComponentId.isIgnoreCase()
                    )
            );
        }
        result.getFirst().setCommand(vtlComponentId.getCommand());
        return result;
    }
}
