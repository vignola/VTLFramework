package com.capgemini.istat.vtlcompiler.vtlsemantic.expression.join;

import com.capgemini.istat.vtlcompiler.vtlcommon.model.KeyVariables;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.dataset.VtlDataset;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.operator.Operator;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.operator.OperatorValidation;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.semantic.ResultExpression;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.VtlExpression;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.join.VtlJoinSelectClause;
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
public class JoinSelectClauseValidation implements OperatorValidation {
    private VtlJoinSelectClause vtlJoinSelectClause;
    private SemanticFactory semanticFactory;
    private FunctionResultService functionResultService;

    @Override
    public void setVtlExpression(VtlExpression vtlExpression) {
        this.vtlJoinSelectClause = (VtlJoinSelectClause) vtlExpression;
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
        LinkedList<VtlDataset> vtlDatasets = new LinkedList<>();

        for (VtlJoinSelectClauseItem vtlJoinSelectClauseItem : vtlJoinSelectClause.getVtlJoinSelectClauseItems()) {
            LinkedList<ResultExpression> resultDataset =
                    semanticFactory.checkSemantic(vtlJoinSelectClauseItem, variables);
            resultExpressions.addFirst(resultDataset.getFirst());
            vtlDatasets.addLast(resultDataset.getFirst().getResult());
        }

        resultExpressions.addFirst(
                functionResultService.joinExpression(vtlDatasets, vtlJoinSelectClause, (Operator) variables.get(KeyVariables.JOIN_OPERATOR))
        );

        functionResultService.checkRedundantNames(vtlJoinSelectClause, resultExpressions.getFirst());
        return resultExpressions;
    }
}
