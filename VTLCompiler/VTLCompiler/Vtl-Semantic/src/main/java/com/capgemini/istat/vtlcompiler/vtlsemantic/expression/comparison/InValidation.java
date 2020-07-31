package com.capgemini.istat.vtlcompiler.vtlsemantic.expression.comparison;

import com.capgemini.istat.vtlcompiler.vtlcommon.model.KeyVariables;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.operator.OperatorValidation;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.semantic.ResultExpression;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.VtlExpression;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.comparison.VtlInExpression;
import com.capgemini.istat.vtlcompiler.vtlsemantic.SemanticFactory;
import com.capgemini.istat.vtlcompiler.vtlsemantic.service.result.FunctionResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.Map;

@Component
@Scope("prototype")
public class InValidation implements OperatorValidation {
    private VtlInExpression vtlInExpression;
    private SemanticFactory semanticFactory;
    private FunctionResultService functionResultService;


    @Override
    public void setVtlExpression(VtlExpression vtlExpression) {
        vtlInExpression = (VtlInExpression) vtlExpression;
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
        LinkedList<ResultExpression> vtlElementResults = semanticFactory.checkSemantic(
                vtlInExpression.getVtlElement(),
                variables);
        resultExpressions.addAll(vtlElementResults);
        if (vtlInExpression.getValueDomain() == null) {
            ResultExpression listExpressionValidation = functionResultService.inConstantValidation(vtlInExpression.getVtlConstantList());
            if (listExpressionValidation == null) {
                resultExpressions.addAll(0, 
                        semanticFactory.checkSemantic(
                                vtlInExpression.getVtlConstantList().get(0),
                                variables));

            } else {
                resultExpressions.addFirst(listExpressionValidation);
            }
        } else {
            resultExpressions.addFirst(functionResultService.getScalarValueDomain(vtlInExpression));
        }
        if (resultExpressions.getFirst().getMessages().isEmpty()) {
            resultExpressions.addFirst(
                    functionResultService.getBinaryResult(
                            vtlElementResults.getFirst(),
                            resultExpressions.getFirst(),
                            vtlInExpression.getOperator()
                    )
            );
        }
        resultExpressions.getFirst().setCommand(vtlInExpression.getCommand());
        return resultExpressions;
    }
}
