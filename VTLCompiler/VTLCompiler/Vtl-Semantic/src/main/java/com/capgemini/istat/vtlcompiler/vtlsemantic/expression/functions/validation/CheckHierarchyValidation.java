package com.capgemini.istat.vtlcompiler.vtlsemantic.expression.functions.validation;

import com.capgemini.istat.vtlcompiler.vtlcommon.model.KeyVariables;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.operator.OperatorValidation;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.semantic.ResultExpression;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.constant.VtlConstantExpression;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.dataset.VtlComponentId;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.define.ruleset.hierarchical.VtlCodeItem;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.define.ruleset.hierarchical.VtlHRRule;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.VtlExpression;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.validation.VtlCheckHierarchy;
import com.capgemini.istat.vtlcompiler.vtlsemantic.SemanticFactory;
import com.capgemini.istat.vtlcompiler.vtlsemantic.service.result.FunctionResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.Map;

@Component
@Scope("prototype")
public class CheckHierarchyValidation implements OperatorValidation {
    private VtlCheckHierarchy vtlCheckHierarchy;
    private SemanticFactory semanticFactory;
    private FunctionResultService functionResultService;

    @Override
    public void setVtlExpression(VtlExpression vtlExpression) {
        this.vtlCheckHierarchy = (VtlCheckHierarchy) vtlExpression;
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
        LinkedList<ResultExpression> resultDataset = semanticFactory.checkSemantic(vtlCheckHierarchy.getVtlVarId(), variables);

        variables.put(KeyVariables.DATASET_IN_CLAUSE, resultDataset.getFirst().getResult());
        LinkedList<ResultExpression> resultComponents = new LinkedList<>();
        if (vtlCheckHierarchy.getConditionComponents() != null) {
            for (VtlComponentId vtlComponentId : vtlCheckHierarchy.getConditionComponents()) {
                resultComponents.addAll(
                        semanticFactory.checkSemantic(vtlComponentId, variables)
                );
            }
            resultExpressions.addAll(resultComponents);
        }
        if (vtlCheckHierarchy.getRuleComponent() != null) {
            semanticFactory.checkSemantic(vtlCheckHierarchy.getRuleComponent(), variables);
        }
        resultExpressions.addAll(0, resultDataset);
        functionResultService.validateCheckHierarchicalRuleset(vtlCheckHierarchy, variables);
        if (vtlCheckHierarchy.getVtlHierarchicalRuleset().getVtlHRRules() != null) {

            for (VtlHRRule vtlHRRule : vtlCheckHierarchy.getVtlHierarchicalRuleset().getVtlHRRules()) {
                if (vtlHRRule.getVtlCodeItemRelation().getLeftCondition() != null) {
                    semanticFactory.checkSemantic(vtlHRRule.getVtlCodeItemRelation().getLeftCondition(), variables);
                }
                for (VtlCodeItem vtlCodeItem : vtlHRRule.getVtlCodeItemRelation().getVtlCodeItems()) {
                    if (vtlCodeItem.getCondition() != null) {
                        semanticFactory.checkSemantic(vtlCodeItem.getCondition(), variables);
                    }
                }
            }
        }
        resultExpressions.addFirst(functionResultService.checkHierarchicalRuleset(vtlCheckHierarchy));
        resultExpressions.getFirst().setCommand(vtlCheckHierarchy.getCommand());
        // procedura per gli altri sql
        vtlCheckHierarchy.addPivot();
        for (VtlConstantExpression vtlConstantExpression : vtlCheckHierarchy.getCustomPivot().getConstantExpressions()) {
            semanticFactory.checkSemantic(vtlConstantExpression, variables);
        }
        variables.remove(KeyVariables.DATASET_IN_CLAUSE);
        semanticFactory.checkSemantic(vtlCheckHierarchy.getCustomPivot(), variables);
        variables.put(KeyVariables.DATASET_IN_CLAUSE, resultDataset.getFirst().getResult());
        return resultExpressions;
    }
}
