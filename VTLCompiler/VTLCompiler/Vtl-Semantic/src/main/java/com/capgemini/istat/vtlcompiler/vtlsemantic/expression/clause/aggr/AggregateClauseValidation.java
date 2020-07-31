package com.capgemini.istat.vtlcompiler.vtlsemantic.expression.clause.aggr;

import com.capgemini.istat.vtlcompiler.vtlcommon.model.KeyVariables;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.dataset.VtlComponent;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.dataset.VtlDataset;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.operator.OperatorValidation;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.semantic.ResultExpression;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.VtlExpression;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.clause.aggr.VtlAggrFunctionClauseExpression;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.clause.aggr.VtlAggregateClauseExpression;
import com.capgemini.istat.vtlcompiler.vtlsemantic.SemanticFactory;
import com.capgemini.istat.vtlcompiler.vtlsemantic.service.result.FunctionResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Component
@Scope("prototype")
public class AggregateClauseValidation implements OperatorValidation {
    private VtlAggregateClauseExpression vtlAggregateClauseExpression;
    private SemanticFactory semanticFactory;
    private FunctionResultService functionResultService;

    @Override
    public void setVtlExpression(VtlExpression vtlExpression) {
        this.vtlAggregateClauseExpression = (VtlAggregateClauseExpression) vtlExpression;
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
        for (VtlAggrFunctionClauseExpression vtlAggrFunctionClauseExpression : vtlAggregateClauseExpression.getVtlAggrFunctionClauseExpressionList()) {
            resultExpressions.addAll(
                    0,
                    semanticFactory.checkSemantic(
                            vtlAggrFunctionClauseExpression,
                            variables
                    )
            );
        }
        VtlDataset vtlDatasetClause = (VtlDataset) variables.get(KeyVariables.DATASET_IN_CLAUSE);
        List<VtlComponent> vtlComponents = new ArrayList<>();
        for (ResultExpression resultExpression : resultExpressions) {
            vtlComponents.add(resultExpression.getResultComponent());
        }

        resultExpressions.addFirst(functionResultService.aggregateComponentToDataset(vtlDatasetClause, vtlComponents));
        resultExpressions.getFirst().setCommand(vtlAggregateClauseExpression.getCommand());

        return resultExpressions;
    }
}
