package com.capgemini.istat.vtlcompiler.vtlsemantic.expression.functions.generic;

import com.capgemini.istat.vtlcompiler.vtlcommon.model.KeyVariables;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.operator.OperatorValidation;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.semantic.ResultExpression;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.constant.VtlOptional;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.define.operator.VtlFunctionParameter;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.VtlExpression;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.generic.VtlCallExpression;
import com.capgemini.istat.vtlcompiler.vtlsemantic.SemanticFactory;
import com.capgemini.istat.vtlcompiler.vtlsemantic.service.result.FunctionResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.Map;

@Component
@Scope("prototype")
public class CallValidation implements OperatorValidation {
    private VtlCallExpression vtlCallExpression;
    private SemanticFactory semanticFactory;
    private FunctionResultService functionResultService;

    @Override
    public void setVtlExpression(VtlExpression vtlExpression) {
        this.vtlCallExpression = (VtlCallExpression) vtlExpression;
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
        LinkedList<ResultExpression> vtlParameters = new LinkedList<>();
        if (vtlCallExpression.getParameters() != null) {
            for (VtlExpression vtlParameter : vtlCallExpression.getParameters()) {
                if (!(vtlParameter instanceof VtlOptional)) {
                    vtlParameters.addAll(
                            semanticFactory.checkSemantic(vtlParameter, variables)
                    );                    
                }
            }
        }


        functionResultService.validateCallExpression(vtlCallExpression);
        for (VtlFunctionParameter vtlFunctionParameter : vtlCallExpression.getVtlUserFunction().getVtlFunctionParameters()) {
            if (vtlFunctionParameter.getDefaultValue() != null)
                semanticFactory.checkSemantic(vtlFunctionParameter.getDefaultValue(), variables);
        }

        functionResultService.validateCallExpressionParameter(vtlCallExpression, variables);

        resultExpressions.addAll(
                semanticFactory.checkSemantic(
                        vtlCallExpression.getVtlUserFunction().getVtlExpression(),
                        variables
                )
        );
        variables.remove(KeyVariables.PARAMETER_MAP);
        vtlCallExpression.setResultExpression(resultExpressions.getFirst());
        if (vtlCallExpression.getVtlUserFunction().getOutputType() != null) {
            functionResultService.checkParameterOutput(resultExpressions.getFirst(), vtlCallExpression, vtlCallExpression.getVtlUserFunction().getOutputType());
            if (resultExpressions.getFirst().getMessages().size() == 0) {
                ResultExpression resultExpression = functionResultService.castUserOperatorOutput(resultExpressions.getFirst(), vtlCallExpression);
                if (resultExpression != null) {
                    resultExpressions.addFirst(
                            resultExpression
                    );
                }
            }
        }

        resultExpressions.getFirst().setCommand(vtlCallExpression.getCommand());
        return resultExpressions;
    }
}
