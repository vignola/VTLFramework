package com.capgemini.istat.vtlcompiler.vtlsemantic.expression.clause.drop;

import com.capgemini.istat.vtlcompiler.vtlcommon.model.KeyVariables;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.dataset.VtlComponent;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.dataset.VtlDataset;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.operator.Operator;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.operator.OperatorValidation;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.semantic.ResultExpression;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.dataset.VtlComponentId;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.VtlExpression;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.clause.drop.VtlKeepOrDropClauseExpression;
import com.capgemini.istat.vtlcompiler.vtlsemantic.SemanticFactory;
import com.capgemini.istat.vtlcompiler.vtlsemantic.service.result.FunctionResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Component
@Scope("prototype")
public class KeepOrDropClauseValidation implements OperatorValidation {
    private SemanticFactory semanticFactory;
    private VtlKeepOrDropClauseExpression vtlKeepOrDropClauseExpression;
    private FunctionResultService functionResultService;

    @Override
    public void setVtlExpression(VtlExpression vtlExpression) {
        this.vtlKeepOrDropClauseExpression = (VtlKeepOrDropClauseExpression) vtlExpression;
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
        VtlDataset vtlDataset = (VtlDataset) variables.get(KeyVariables.JOIN_RESULT);
        if (vtlDataset == null) {
            LinkedList<ResultExpression> resultDataset = new LinkedList<>();
            resultDataset.addAll(0, semanticFactory.checkSemantic(vtlKeepOrDropClauseExpression.getVtlExpression(), variables));
            vtlDataset = resultDataset.getFirst().getResult();
            resultExpressions.addAll(resultDataset);
        }
        variables.put(KeyVariables.DATASET_IN_CLAUSE, vtlDataset);
        List<VtlComponent> vtlComponents = new ArrayList<>();
        if (!vtlKeepOrDropClauseExpression.getVtlComponentIds().isEmpty()) {
            for (VtlComponentId vtlComponentId : vtlKeepOrDropClauseExpression.getVtlComponentIds()) {
                LinkedList<ResultExpression> components = semanticFactory.checkSemantic(vtlComponentId, variables);
                vtlComponents.add(components.getFirst().getResultComponent());
            }
        }

        resultExpressions.addFirst(
                vtlKeepOrDropClauseExpression.getOperator().equals(Operator.DROP) ?
                        functionResultService.dropComponents(vtlDataset, vtlComponents, Operator.DROP)
                        :
                        functionResultService.keepComponents(vtlDataset, vtlComponents, Operator.KEEP)
        );
        resultExpressions.getFirst().setCommand(vtlKeepOrDropClauseExpression.getCommand());
        variables.remove(KeyVariables.DATASET_IN_CLAUSE);
        return resultExpressions;
    }
}