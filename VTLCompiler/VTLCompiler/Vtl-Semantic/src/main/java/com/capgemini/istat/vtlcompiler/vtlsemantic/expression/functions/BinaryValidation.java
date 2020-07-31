package com.capgemini.istat.vtlcompiler.vtlsemantic.expression.functions;

import com.capgemini.istat.vtlcompiler.vtlcommon.model.KeyVariables;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.operator.OperatorValidation;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.semantic.ResultExpression;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.VtlExpression;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.function.VtlBinaryExpression;
import com.capgemini.istat.vtlcompiler.vtlsemantic.SemanticFactory;
import com.capgemini.istat.vtlcompiler.vtlsemantic.service.result.FunctionResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.Map;

@Component
@Scope("prototype")
public class BinaryValidation implements OperatorValidation {
    private VtlBinaryExpression vtlBinaryExpression;
    private SemanticFactory semanticFactory;
    private FunctionResultService functionResultService;


    public VtlBinaryExpression getVtlExpression() {
        return vtlBinaryExpression;
    }

    @Override
    public void setVtlExpression(VtlExpression vtlExpression) {
        this.vtlBinaryExpression = (VtlBinaryExpression) vtlExpression;
    }

    @Autowired
    public void setSemanticFactory(SemanticFactory semanticFactory) {
        this.semanticFactory = semanticFactory;
    }

    @Autowired
    public void setFunctionResultService(FunctionResultService functionResultService) {
        this.functionResultService = functionResultService;
    }

    public LinkedList<ResultExpression> resolve(Map<KeyVariables, Object> variables) throws Exception {
        LinkedList<ResultExpression> result = new LinkedList<>();
        LinkedList<ResultExpression> resultsLeft = semanticFactory.checkSemantic(getVtlExpression().getLeftExpression(), variables);
        LinkedList<ResultExpression> resultsRight = semanticFactory.checkSemantic(getVtlExpression().getRightExpression(), variables);
        result.addAll(resultsLeft);
        result.addAll(resultsRight);
        ResultExpression resultLeft = resultsLeft.getFirst();
        ResultExpression resultRight = resultsRight.getFirst();
        result.addFirst(
                functionResultService.getBinaryResult(resultLeft, resultRight, vtlBinaryExpression.getOperator())
        );
        result.getFirst().setCommand(vtlBinaryExpression.getCommand());
        return result;
    }


}
