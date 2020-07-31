package com.capgemini.istat.vtlcompiler.vtlsemantic.expression.functions.hierarchy;

import com.capgemini.istat.vtlcompiler.vtlcommon.model.KeyVariables;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.operator.OperatorValidation;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.semantic.ResultExpression;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.constant.VtlConstantExpression;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.dataset.VtlComponentId;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.define.ruleset.hierarchical.VtlCodeItem;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.define.ruleset.hierarchical.VtlHRRule;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.VtlExpression;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.hierarchy.VtlHierarchyExpression;
import com.capgemini.istat.vtlcompiler.vtlsemantic.SemanticFactory;
import com.capgemini.istat.vtlcompiler.vtlsemantic.service.result.FunctionResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.Map;

@Component
@Scope("prototype")
public class HierarchyValidation implements OperatorValidation {
    private VtlHierarchyExpression vtlHierarchyExpression;
    private SemanticFactory semanticFactory;
    private FunctionResultService functionResultService;

    @Override
    public void setVtlExpression(VtlExpression vtlExpression) {
        this.vtlHierarchyExpression = (VtlHierarchyExpression) vtlExpression;
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
        LinkedList<ResultExpression> resultDataset = semanticFactory.checkSemantic(vtlHierarchyExpression.getVtlOperandExpression(), variables);

        variables.put(KeyVariables.DATASET_IN_CLAUSE, resultDataset.getFirst().getResult());
        LinkedList<ResultExpression> resultComponents = new LinkedList<>();
        if (vtlHierarchyExpression.getComponentIds() != null) {
            for (VtlComponentId vtlComponentId : vtlHierarchyExpression.getComponentIds()) {
                resultComponents.addAll(
                        semanticFactory.checkSemantic(vtlComponentId, variables)
                );
            }
            resultExpressions.addAll(resultComponents);
        }
        if (vtlHierarchyExpression.getRuleComponent() != null) {
            semanticFactory.checkSemantic(vtlHierarchyExpression.getRuleComponent(), variables);

        }

        resultExpressions.addAll(0, resultDataset);
        functionResultService.validateHierarchy(vtlHierarchyExpression, variables);
        Map<String, VtlExpression> parameterMap = (Map<String, VtlExpression>) variables.get(KeyVariables.PARAMETER_MAP);
        parameterMap.put(vtlHierarchyExpression.getVtlHierarchicalRuleset().getRuleVar(), vtlHierarchyExpression.getRuleComponent());
        if (vtlHierarchyExpression.getVtlHierarchicalRuleset().getVtlHRRules() != null) {

            for (VtlHRRule vtlHRRule : vtlHierarchyExpression.getVtlHierarchicalRuleset().getVtlHRRules()) {
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

        resultExpressions.addFirst(functionResultService.hierarchy(vtlHierarchyExpression));
        resultExpressions.getFirst().setCommand(vtlHierarchyExpression.getCommand());
        // procedura per gli altri sql
        vtlHierarchyExpression.addPivot();
        for (VtlConstantExpression vtlConstantExpression : vtlHierarchyExpression.getCustomPivot().getConstantExpressions()) {
            semanticFactory.checkSemantic(vtlConstantExpression, variables);
        }
        variables.remove(KeyVariables.DATASET_IN_CLAUSE);
        semanticFactory.checkSemantic(vtlHierarchyExpression.getCustomPivot(), variables);
        variables.put(KeyVariables.DATASET_IN_CLAUSE, resultDataset.getFirst().getResult());
        return resultExpressions;
    }
}
