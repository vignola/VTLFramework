package com.capgemini.istat.vtlcompiler.vtlsemantic.expression.join;

import com.capgemini.istat.vtlcompiler.vtlcommon.model.KeyVariables;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.operator.Operator;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.operator.OperatorValidation;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.semantic.ResultExpression;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.VtlExpression;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.join.VtlJoinSelectClauseItem;
import com.capgemini.istat.vtlcompiler.vtlsemantic.SemanticFactory;
import com.capgemini.istat.vtlcompiler.vtlsemantic.service.result.FunctionResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.Map;

@Component
@Scope("prototype")
public class JoinSelectClauseValidationItem implements OperatorValidation {
    private VtlJoinSelectClauseItem vtlJoinSelectClauseItem;
    private SemanticFactory semanticFactory;
    private FunctionResultService functionResultService;

    @Override
    public void setVtlExpression(VtlExpression vtlExpression) {
        this.vtlJoinSelectClauseItem = (VtlJoinSelectClauseItem) vtlExpression;
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
        resultExpressions.addAll(
                0,
                semanticFactory.checkSemantic(vtlJoinSelectClauseItem.getVtlExpression(), variables)
        );
        if (vtlJoinSelectClauseItem.getAsName() != null && !vtlJoinSelectClauseItem.getAsName().isEmpty()) {
            ResultExpression resultExpression = resultExpressions.getFirst();
            resultExpressions.addFirst(
                    functionResultService.assignDatasetName(resultExpression.getResult(), vtlJoinSelectClauseItem.getAsName(), Operator.ASSIGN)
            );
        }
        return resultExpressions;
    }
}
