package com.capgemini.istat.vtlcompiler.vtlsemantic.expression.clause.analytic;

import com.capgemini.istat.vtlcompiler.vtlcommon.model.KeyVariables;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.operator.OperatorValidation;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.semantic.ResultExpression;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.dataset.VtlComponentId;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.VtlExpression;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.clause.analytic.VtlAnalyticFunctionExpression;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.clause.analytic.VtlOrderByItem;
import com.capgemini.istat.vtlcompiler.vtlsemantic.SemanticFactory;
import com.capgemini.istat.vtlcompiler.vtlsemantic.service.result.FunctionResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.Map;

@Component
@Scope("prototype")
public class AnalyticFunctionValidation implements OperatorValidation {
    private VtlAnalyticFunctionExpression analyticFunctionExpression;
    private SemanticFactory semanticFactory;
    private FunctionResultService functionResultService;

    @Autowired
    public void setSemanticFactory(SemanticFactory semanticFactory) {
        this.semanticFactory = semanticFactory;
    }

    @Autowired
    public void setFunctionResultService(FunctionResultService functionResultService) {
        this.functionResultService = functionResultService;
    }

    @Override
    public void setVtlExpression(VtlExpression vtlExpression) {
        this.analyticFunctionExpression = (VtlAnalyticFunctionExpression) vtlExpression;
    }

    @Override
    public LinkedList<ResultExpression> resolve(Map<KeyVariables, Object> variables) throws Exception {
        LinkedList<ResultExpression> resultExpressions = new LinkedList<>();
        boolean isNotInClause = false;
        LinkedList<ResultExpression> resultDataset =
                semanticFactory.checkSemantic(
                        analyticFunctionExpression.getVtlExpression(),
                        variables
                );
        ResultExpression resultExpression = resultDataset.getFirst();
        if (variables.get(KeyVariables.DATASET_IN_CLAUSE) == null) {
            isNotInClause = true;
            variables.put(KeyVariables.DATASET_IN_CLAUSE, resultExpression.getResult());
        }
        if (analyticFunctionExpression.getVtlPartitionBy() != null) {
            for (VtlComponentId vtlComponentId : analyticFunctionExpression.getVtlPartitionBy().getVtlComponentIds()) {
                resultExpressions.addAll(0,
                        semanticFactory.checkSemantic(
                                vtlComponentId,
                                variables
                        )
                );
            }
        }
        if (analyticFunctionExpression.getVltOrderBy() != null) {
            for (VtlOrderByItem vtlOrderByItem : analyticFunctionExpression.getVltOrderBy().getVtlOrderByItems()) {
                resultExpressions.addAll(0,
                        semanticFactory.checkSemantic(
                                vtlOrderByItem.getVtlComponentId(),
                                variables
                        )
                );
            }
        }
        resultExpressions.addFirst(
                functionResultService.getUnaryResult(
                        resultExpression,
                        analyticFunctionExpression.getOperator()
                )
        );
        if (isNotInClause) {
            variables.remove(KeyVariables.DATASET_IN_CLAUSE);
        }
        return resultExpressions;
    }
}
