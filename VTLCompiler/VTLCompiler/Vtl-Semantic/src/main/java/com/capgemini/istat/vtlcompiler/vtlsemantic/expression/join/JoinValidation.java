package com.capgemini.istat.vtlcompiler.vtlsemantic.expression.join;

import com.capgemini.istat.vtlcompiler.vtlcommon.model.KeyVariables;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.operator.OperatorValidation;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.semantic.ResultExpression;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.VtlExpression;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.join.VtlJoinExpression;
import com.capgemini.istat.vtlcompiler.vtlsemantic.SemanticFactory;
import com.capgemini.istat.vtlcompiler.vtlsemantic.service.result.FunctionResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.Map;

@Component
@Scope("prototype")
public class JoinValidation implements OperatorValidation {
    private VtlJoinExpression vtlJoinExpression;
    private SemanticFactory semanticFactory;
    private FunctionResultService functionResultService;

    @Override
    public void setVtlExpression(VtlExpression vtlExpression) {
        this.vtlJoinExpression = (VtlJoinExpression) vtlExpression;
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
        variables.put(KeyVariables.JOIN_OPERATOR, vtlJoinExpression.getOperator());
        resultExpressions.addAll(0, semanticFactory.checkSemantic(vtlJoinExpression.getVtlJoinSelectClause(), variables));
        ResultExpression resultJoin = resultExpressions.getFirst();
        variables.put(KeyVariables.JOIN_RESULT, resultJoin.getResult());
        if (vtlJoinExpression.getVtlJoinBody() != null) {
            resultExpressions.addAll(0,
                    semanticFactory.checkSemantic(vtlJoinExpression.getVtlJoinBody(), variables)
            );
        }
        variables.remove(KeyVariables.JOIN_RESULT);
        variables.remove(KeyVariables.JOIN_OPERATOR);
        resultExpressions.addFirst(functionResultService.removeRedundantNames(resultExpressions.getFirst().getResult()));
        resultExpressions.getFirst().setCommand(vtlJoinExpression.getCommand());
        return resultExpressions;
    }
}
