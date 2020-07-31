package com.capgemini.istat.vtlcompiler.vtlsemantic.expression.functions;

import com.capgemini.istat.vtlcompiler.vtlcommon.model.KeyVariables;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.operator.OperatorValidation;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.semantic.ResultExpression;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.VtlExpression;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.set.VtlSetUnnaryExpression;
import com.capgemini.istat.vtlcompiler.vtlsemantic.SemanticFactory;
import com.capgemini.istat.vtlcompiler.vtlsemantic.service.result.FunctionResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.Map;

@Component
@Scope("prototype")
public class SetUnnaryValidation implements OperatorValidation {
    private VtlSetUnnaryExpression vtlSetUnnaryExpression;
    private SemanticFactory semanticFactory;
    private FunctionResultService functionResultService;

    @Override
    public void setVtlExpression(VtlExpression vtlExpression) {
        this.vtlSetUnnaryExpression = (VtlSetUnnaryExpression) vtlExpression;
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
        LinkedList<ResultExpression> resultDataset = new LinkedList<>();
        for (VtlExpression vtlExpression : vtlSetUnnaryExpression.getVtlExpressions()) {
            LinkedList<ResultExpression> result = semanticFactory.checkSemantic(vtlExpression, variables);
            resultDataset.addLast(result.getFirst());
            resultExpressions.addAll(result);
        }

        ResultExpression firstResult = resultDataset.removeFirst();
        for (ResultExpression resultExpression : resultDataset) {
            ResultExpression resultUnion =
                    functionResultService.getBinaryResult(
                            firstResult,
                            resultExpression,
                            vtlSetUnnaryExpression.getOperator()
                    );
            resultExpressions.addFirst(resultUnion);
            if (resultUnion.getMessages().size() != 0) {
                break;
            }
          
        }
        resultExpressions.getFirst().setCommand(vtlSetUnnaryExpression.getCommand());
        return resultExpressions;
    }
}
