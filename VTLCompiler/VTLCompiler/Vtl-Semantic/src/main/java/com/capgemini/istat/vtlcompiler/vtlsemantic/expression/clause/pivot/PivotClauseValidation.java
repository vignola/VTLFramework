package com.capgemini.istat.vtlcompiler.vtlsemantic.expression.clause.pivot;

import com.capgemini.istat.vtlcompiler.vtlcommon.model.KeyVariables;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.operator.OperatorValidation;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.semantic.ResultExpression;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.VtlExpression;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.clause.pivot.VtlPivotClauseExpression;
import com.capgemini.istat.vtlcompiler.vtlsemantic.SemanticFactory;
import com.capgemini.istat.vtlcompiler.vtlsemantic.service.result.FunctionResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.Map;

@Component
@Scope("prototype")
public class PivotClauseValidation implements OperatorValidation {
    private VtlPivotClauseExpression vtlPivotClauseExpression;
    private SemanticFactory semanticFactory;
    private FunctionResultService functionResultService;

    @Override
    public void setVtlExpression(VtlExpression vtlExpression) {
        this.vtlPivotClauseExpression = (VtlPivotClauseExpression) vtlExpression;
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
        LinkedList<ResultExpression> datasetClause = semanticFactory.checkSemantic(vtlPivotClauseExpression.getVtlDataset(), variables);
        resultExpressions.addAll(datasetClause);
        variables.put(KeyVariables.DATASET_IN_CLAUSE, datasetClause.getFirst().getResult());
        resultExpressions.addAll(
                semanticFactory.checkSemantic(vtlPivotClauseExpression.getIdentifier(), variables)
        );
        resultExpressions.addAll(
                semanticFactory.checkSemantic(vtlPivotClauseExpression.getMeasure(), variables)
        );
        resultExpressions.addFirst(functionResultService.pivot(vtlPivotClauseExpression));
        variables.remove(KeyVariables.DATASET_IN_CLAUSE);
        resultExpressions.getFirst().setCommand(vtlPivotClauseExpression.getCommand());
        return resultExpressions;
    }
}
