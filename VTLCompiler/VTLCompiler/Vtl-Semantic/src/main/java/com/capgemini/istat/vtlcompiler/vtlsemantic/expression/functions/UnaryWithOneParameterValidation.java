package com.capgemini.istat.vtlcompiler.vtlsemantic.expression.functions;

import com.capgemini.istat.vtlcompiler.vtlcommon.model.KeyVariables;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.operator.OperatorValidation;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.semantic.ResultExpression;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.VtlExpression;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.function.VtlUnaryWithOneParameter;
import com.capgemini.istat.vtlcompiler.vtlsemantic.SemanticFactory;
import com.capgemini.istat.vtlcompiler.vtlsemantic.service.result.FunctionResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Component
@Scope("prototype")
public class UnaryWithOneParameterValidation implements OperatorValidation {
    private VtlUnaryWithOneParameter vtlUnaryWithOneParameter;
    private SemanticFactory semanticFactory;
    private FunctionResultService functionResultService;

    @Override
    public void setVtlExpression(VtlExpression vtlExpression) {
        this.vtlUnaryWithOneParameter = (VtlUnaryWithOneParameter) vtlExpression;
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
        LinkedList<ResultExpression> resultRight = semanticFactory.checkSemantic(vtlUnaryWithOneParameter.getParameterExpression(), variables);
        functionResultService.checkParameter(resultRight.getFirst(), vtlUnaryWithOneParameter.getParameterOperator());

        List<VtlExpression> optionalExpression = vtlUnaryWithOneParameter.getOptionalParameterExpression();
        if (optionalExpression != null && optionalExpression.size() != 0) {
            for (VtlExpression vtlExpression : optionalExpression) {
                    resultOptional.addAll(
                            0,
                            semanticFactory.checkSemantic(
                                    vtlExpression,
                                    variables
                            )
                    );
                    functionResultService.checkParameter(resultOptional.getFirst(), vtlUnaryWithOneParameter.getOptionalParameterOperator());

                result.addAll(0, resultOptional);
            }
        }

        result.addAll(0, resultRight);
        resultExpressions = semanticFactory.checkSemantic(vtlUnaryWithOneParameter.getVtlExpression(), variables);
        result.addAll(0, resultExpressions);
        ResultExpression resultExpression = result.getFirst();
        result.addFirst(
                functionResultService.getUnaryResult(
                        resultExpression,
                        vtlUnaryWithOneParameter.getOperator()
                )
        );

        result.getFirst().setCommand(vtlUnaryWithOneParameter.getCommand());

        return result;
    }


}
