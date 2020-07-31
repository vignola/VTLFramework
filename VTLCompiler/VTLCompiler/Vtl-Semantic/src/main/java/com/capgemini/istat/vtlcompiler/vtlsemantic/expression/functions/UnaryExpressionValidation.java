package com.capgemini.istat.vtlcompiler.vtlsemantic.expression.functions;

import com.capgemini.istat.vtlcompiler.vtlcommon.model.KeyVariables;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.operator.OperatorValidation;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.semantic.ResultExpression;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.VtlExpression;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.function.VtlUnaryExpression;
import com.capgemini.istat.vtlcompiler.vtlsemantic.SemanticFactory;
import com.capgemini.istat.vtlcompiler.vtlsemantic.service.result.FunctionResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.Map;

@Component
@Scope("prototype")
public class UnaryExpressionValidation implements OperatorValidation {
    private VtlUnaryExpression vtlUnaryExpression;
    private SemanticFactory semanticFactory;
    private FunctionResultService functionResultService;

    @Override
    public void setVtlExpression(VtlExpression vtlExpression) {
        this.vtlUnaryExpression = (VtlUnaryExpression) vtlExpression;
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
        LinkedList<ResultExpression> result = new LinkedList<>();
        LinkedList<ResultExpression> resultExpressions;
        LinkedList<ResultExpression> resultOptional = new LinkedList<>();
        if (vtlUnaryExpression.getOptionalExpression() != null && vtlUnaryExpression.getOptionalExpression().size() != 0) {
            for (VtlExpression vtlExpression : vtlUnaryExpression.getOptionalExpression()) {
                if (!vtlExpression.getCommand().equalsIgnoreCase("_")) {
                    resultOptional.addAll(0, semanticFactory.checkSemantic(vtlExpression, variables));
                    functionResultService.checkParameter(resultOptional.getFirst(), vtlUnaryExpression.getOptionalOperator());

                }
            }

            result.addAll(0, resultOptional);
        }
        resultExpressions = semanticFactory.checkSemantic(vtlUnaryExpression.getVtlExpression(), variables);
        result.addAll(0, resultExpressions);

        ResultExpression resultExpression = resultExpressions.getFirst();
        result.addFirst(functionResultService.getUnaryResult(resultExpression, vtlUnaryExpression.getOperator()));
        result.getFirst().setCommand(vtlUnaryExpression.getCommand());
        return result;
    }
}
