package com.capgemini.istat.vtlcompiler.vtlsemantic.expression.comparison;

import com.capgemini.istat.vtlcompiler.vtlcommon.model.KeyVariables;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.dataset.VtlComponent;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.operator.Operator;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.operator.OperatorValidation;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.semantic.ResultExpression;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.VtlExpression;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.comparison.VtlBetweenExpression;
import com.capgemini.istat.vtlcompiler.vtlsemantic.SemanticFactory;
import com.capgemini.istat.vtlcompiler.vtlsemantic.service.result.FunctionResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.Map;

@Component
@Scope("prototype")
public class BetweenValidation implements OperatorValidation {
    private VtlBetweenExpression vtlBetweenExpression;
    private FunctionResultService functionResultService;
    private SemanticFactory semanticFactory;

    @Override
    public void setVtlExpression(VtlExpression vtlExpression) {
        this.vtlBetweenExpression = (VtlBetweenExpression) vtlExpression;
    }

    @Autowired
    public void setFunctionResultService(FunctionResultService functionResultService) {
        this.functionResultService = functionResultService;
    }

    @Autowired
    public void setSemanticFactory(SemanticFactory semanticFactory) {
        this.semanticFactory = semanticFactory;
    }


    @Override
    public LinkedList<ResultExpression> resolve(Map<KeyVariables, Object> variables) throws Exception {
        LinkedList<ResultExpression> resultExpressions = new LinkedList<>();
        LinkedList<ResultExpression> fromResults = semanticFactory.checkSemantic(vtlBetweenExpression.getVtlfrom(), variables);
        LinkedList<ResultExpression> toResults = semanticFactory.checkSemantic(vtlBetweenExpression.getVtlTo(), variables);
        ResultExpression fromResult = fromResults.getFirst();
        ResultExpression toResult = toResults.getFirst();
        VtlComponent fromComponent = fromResult.getResultComponent();
        VtlComponent toComponent = toResult.getResultComponent();
        functionResultService.createTemporaryComponentFromBinaryExpression(
                        fromComponent, toComponent, Operator.BETWEEN_PARAMETER
                );
        LinkedList<ResultExpression> vtlElementResults = semanticFactory.checkSemantic(vtlBetweenExpression.getVtlElement(), variables);
        ResultExpression vtlElementResult = vtlElementResults.getFirst();
        resultExpressions.addFirst(
                functionResultService.getBinaryResult(vtlElementResult, fromResult, vtlBetweenExpression.getOperator())
        );
        resultExpressions.getFirst().setCommand(vtlBetweenExpression.getCommand());
        return resultExpressions;
    }
}
