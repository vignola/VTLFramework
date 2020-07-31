package com.capgemini.istat.vtlcompiler.vtlsemantic.expression.clause.keep;

import com.capgemini.istat.vtlcompiler.vtlcommon.model.KeyVariables;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.dataset.VtlComponent;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.dataset.VtlDataset;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.operator.Operator;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.operator.OperatorValidation;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.semantic.ResultExpression;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.VtlExpression;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.clause.keep.VtlKeepClauseExpression;
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
public class KeepClauseValidation implements OperatorValidation {
    private SemanticFactory semanticFactory;
    private VtlKeepClauseExpression vtlKeepClauseExpression;
    private FunctionResultService functionResultService;

    @Override
    public void setVtlExpression(VtlExpression vtlExpression) {
        this.vtlKeepClauseExpression = (VtlKeepClauseExpression) vtlExpression;
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
            LinkedList<ResultExpression> resultDataset =
                    semanticFactory.checkSemantic(
                            vtlKeepClauseExpression.getVtlExpression(),
                            variables
                    );
            vtlDataset = resultDataset.getFirst().getResult();
            resultExpressions.addAll(0, resultDataset);
        }
        variables.put(KeyVariables.DATASET_IN_CLAUSE, vtlDataset);
        List<VtlComponent> vtlComponents = new ArrayList<>();
        if (!vtlKeepClauseExpression.getComponentIds().isEmpty()) {
            for (VtlExpression vtlExpression : vtlKeepClauseExpression.getComponentIds()) {
                LinkedList<ResultExpression> components = semanticFactory.checkSemantic(vtlExpression, variables);
                vtlComponents.add(components.getFirst().getResultComponent());
            }
        }

        resultExpressions.addFirst(
                functionResultService.keepComponents(vtlDataset, vtlComponents, Operator.KEEP)
        );
        resultExpressions.getFirst().setCommand(vtlKeepClauseExpression.getCommand());
        return resultExpressions;
    }
}
