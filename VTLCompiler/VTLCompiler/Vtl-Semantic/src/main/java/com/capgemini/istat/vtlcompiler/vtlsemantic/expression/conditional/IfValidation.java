package com.capgemini.istat.vtlcompiler.vtlsemantic.expression.conditional;

import com.capgemini.istat.vtlcompiler.vtlcommon.model.KeyVariables;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.operator.OperatorValidation;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.semantic.ResultExpression;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.VtlExpression;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.conditional.VtlIfExpression;
import com.capgemini.istat.vtlcompiler.vtlsemantic.SemanticFactory;
import com.capgemini.istat.vtlcompiler.vtlsemantic.service.result.FunctionResultService;
import com.capgemini.istat.vtlcompiler.vtlsemantic.service.validation.SemanticMessageUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.Map;

@Component
@Scope("prototype")
public class IfValidation implements OperatorValidation {
    private VtlIfExpression vtlIfExpression;
    private SemanticFactory semanticFactory;
    private FunctionResultService functionResultService;


    @Autowired
    public void setSemanticFactory(SemanticFactory semanticFactory) {
        this.semanticFactory = semanticFactory;
    }

    @Autowired
    public void setFunctionResultService(FunctionResultService functionResultService) {
        this.functionResultService = functionResultService;
    }



    @Override
    public void setVtlExpression(VtlExpression vtlExpression) {
        this.vtlIfExpression = (VtlIfExpression) vtlExpression;
    }

    @Override
    public LinkedList<ResultExpression> resolve(Map<KeyVariables, Object> variables) throws Exception {
        LinkedList<ResultExpression> resultExpressions = new LinkedList<>();
        LinkedList<ResultExpression> conditionalExpressions =
                semanticFactory.checkSemantic(vtlIfExpression.getCondition(), variables);
        resultExpressions.addAll(0, conditionalExpressions);
        ResultExpression conditionResult = conditionalExpressions.getFirst();
        resultExpressions.addFirst(
                functionResultService.getUnaryResult(
                        conditionResult,
                        vtlIfExpression.getConditionalOperator()
                )
        );
        SemanticMessageUtility.processSemanticMessage(resultExpressions.getFirst());

        LinkedList<ResultExpression> thenExprResults =
                semanticFactory.checkSemantic(vtlIfExpression.getThenExpr(), variables);
        resultExpressions.addAll(0, thenExprResults);
        LinkedList<ResultExpression> elseExprResults =
                semanticFactory.checkSemantic(vtlIfExpression.getElseExpr(), variables);
        resultExpressions.addAll(0, elseExprResults);

        ResultExpression checkTipeResult = functionResultService.getBinaryResult(
                thenExprResults.getFirst(),
                elseExprResults.getFirst(),
                vtlIfExpression.getOperator()
        );
        SemanticMessageUtility.processSemanticMessage(checkTipeResult);

        checkTipeResult = functionResultService.getBinaryResult(
                    thenExprResults.getFirst(),
                    elseExprResults.getFirst(),
                    vtlIfExpression.getOperator()
            );
            resultExpressions.addFirst(checkTipeResult);
        return resultExpressions;
    }
}
