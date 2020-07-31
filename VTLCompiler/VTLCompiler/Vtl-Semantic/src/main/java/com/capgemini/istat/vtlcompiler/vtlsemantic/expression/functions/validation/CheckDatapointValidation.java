package com.capgemini.istat.vtlcompiler.vtlsemantic.expression.functions.validation;

import com.capgemini.istat.vtlcompiler.vtlcommon.model.KeyVariables;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.dataset.VtlDataset;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.operator.OperatorValidation;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.semantic.ResultExpression;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.dataset.VtlComponentId;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.define.ruleset.datapoint.VtlDPRule;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.VtlExpression;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.validation.VtlCheckDatapoint;
import com.capgemini.istat.vtlcompiler.vtlsemantic.SemanticFactory;
import com.capgemini.istat.vtlcompiler.vtlsemantic.service.result.FunctionResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.Map;

@Component
@Scope("prototype")
public class CheckDatapointValidation implements OperatorValidation {
    private VtlCheckDatapoint vtlCheckDatapoint;
    private SemanticFactory semanticFactory;
    private FunctionResultService functionResultService;

    @Override
    public void setVtlExpression(VtlExpression vtlExpression) {
        this.vtlCheckDatapoint = (VtlCheckDatapoint) vtlExpression;
    }

    @Autowired
    public void setSemanticFactory(SemanticFactory semanticFactory) {
        this.semanticFactory = semanticFactory;
    }

    @Autowired
    public void setFunctionResultService(FunctionResultService functionResultService) {
        this.functionResultService = functionResultService;
    }

    @Override
    public LinkedList<ResultExpression> resolve(Map<KeyVariables, Object> variables) throws Exception {
        LinkedList<ResultExpression> resultExpressions = new LinkedList<>();
        LinkedList<ResultExpression> resultDatasetToChecks = new LinkedList<>();
        resultDatasetToChecks.addAll(
                semanticFactory.checkSemantic(
                        vtlCheckDatapoint.getDatasetToCheck(),
                        variables
                )
        );
        resultExpressions.addAll(resultDatasetToChecks);
        VtlDataset datasetToCheck = resultDatasetToChecks.getFirst().getResult();
        variables.put(KeyVariables.DATASET_IN_CLAUSE, datasetToCheck);
        LinkedList<ResultExpression> resultComponents = new LinkedList<>();
        if (vtlCheckDatapoint.getComponentIds() != null) {
            for (VtlComponentId vtlComponentId : vtlCheckDatapoint.getComponentIds()) {
                resultComponents.addAll(semanticFactory.checkSemantic(vtlComponentId, variables));
            }
        }
        resultExpressions.addAll(resultComponents);
        functionResultService.validateCheckDatapoint(vtlCheckDatapoint, variables);
        for (VtlDPRule vtlDPRule : vtlCheckDatapoint.getVtlDatapointRuleset().getDpRules()) {
            //check antecedent condition
            if (vtlDPRule.getAntecedentCondition() != null)
                semanticFactory.checkSemantic(vtlDPRule.getAntecedentCondition(), variables);
            //check consequent condition
            semanticFactory.checkSemantic(vtlDPRule.getConsequentCondition(), variables);
            if (vtlDPRule.getRuleName() == null || vtlDPRule.getRuleName().isEmpty()) {
                vtlDPRule.setRuleName(vtlCheckDatapoint.getRulesetName() + "_" + vtlCheckDatapoint.getVtlDatapointRuleset().getDpRules().indexOf(vtlDPRule));
            }
        }
        variables.remove(KeyVariables.PARAMETER_MAP);
        variables.remove(KeyVariables.DATASET_IN_CLAUSE);
        resultExpressions.addFirst(functionResultService.checkDatapoint(vtlCheckDatapoint));
        resultExpressions.getFirst().setCommand(vtlCheckDatapoint.getCommand());
        return resultExpressions;
    }
}
