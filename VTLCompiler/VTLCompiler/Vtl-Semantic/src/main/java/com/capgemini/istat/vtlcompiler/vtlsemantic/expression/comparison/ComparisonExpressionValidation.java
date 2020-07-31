package com.capgemini.istat.vtlcompiler.vtlsemantic.expression.comparison;

import com.capgemini.istat.vtlcompiler.vtlcommon.model.KeyVariables;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.operator.OperatorValidation;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.semantic.ResultExpression;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.VtlExpression;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.comparison.VtlComparisonBinaryExpression;
import com.capgemini.istat.vtlcompiler.vtlsemantic.SemanticFactory;
import com.capgemini.istat.vtlcompiler.vtlsemantic.service.result.FunctionResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.Map;

@Component
@Scope("prototype")
public class ComparisonExpressionValidation implements OperatorValidation {
    private SemanticFactory semanticFactory;
    private FunctionResultService functionResultService;
    private VtlComparisonBinaryExpression comparisonExpression;

    @Override
    public void setVtlExpression(VtlExpression vtlExpression) {
        this.comparisonExpression = (VtlComparisonBinaryExpression) vtlExpression;
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
        //Identico al binario ma probabilmente si distinguerà per le filter e similari
        LinkedList<ResultExpression> result = new LinkedList<>();
        LinkedList<ResultExpression> resultsLeft = semanticFactory.checkSemantic(comparisonExpression.getLeftExpression(), variables);
        LinkedList<ResultExpression> resultsRight = semanticFactory.checkSemantic(comparisonExpression.getRightExpression(), variables);
        result.addAll(resultsLeft);
        result.addAll(resultsRight);
        ResultExpression resultLeft = resultsLeft.getFirst();
        ResultExpression resultRight = resultsRight.getFirst();
        result.addFirst(functionResultService.getBinaryResult(resultLeft, resultRight, comparisonExpression.getOperator()));
        result.getFirst().setCommand(comparisonExpression.getCommand());
        return result;
    }
}
