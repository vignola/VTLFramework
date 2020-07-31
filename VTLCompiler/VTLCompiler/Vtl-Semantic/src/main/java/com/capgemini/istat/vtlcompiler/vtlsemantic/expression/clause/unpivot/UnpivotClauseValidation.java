package com.capgemini.istat.vtlcompiler.vtlsemantic.expression.clause.unpivot;

import com.capgemini.istat.vtlcompiler.vtlcommon.model.KeyVariables;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.operator.OperatorValidation;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.semantic.ResultExpression;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.VtlExpression;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.clause.unpivot.VtlPivotOrUnpivotClauseExpression;
import com.capgemini.istat.vtlcompiler.vtlsemantic.SemanticFactory;
import com.capgemini.istat.vtlcompiler.vtlsemantic.service.result.FunctionResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.Map;

@Component
@Scope("prototype")
public class UnpivotClauseValidation implements OperatorValidation {
    private VtlPivotOrUnpivotClauseExpression vtlPivotOrUnpivotClauseExpression;
    private SemanticFactory semanticFactory;
    private FunctionResultService functionResultService;

    @Override
    public void setVtlExpression(VtlExpression vtlExpression) {
        this.vtlPivotOrUnpivotClauseExpression = (VtlPivotOrUnpivotClauseExpression) vtlExpression;
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
        LinkedList<ResultExpression> datasetClause = semanticFactory.checkSemantic(vtlPivotOrUnpivotClauseExpression.getVtlDataset(), variables);
        resultExpressions.addAll(datasetClause);
        resultExpressions.addFirst(functionResultService.unpivot(vtlPivotOrUnpivotClauseExpression));
        resultExpressions.getFirst().setCommand(vtlPivotOrUnpivotClauseExpression.getCommand());
        return resultExpressions;
    }
}
