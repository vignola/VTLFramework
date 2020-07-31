package com.capgemini.istat.vtlcompiler.vtlsemantic.expression.clause.aggr;

import com.capgemini.istat.vtlcompiler.vtlcommon.model.KeyVariables;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.operator.OperatorValidation;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.semantic.ResultExpression;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.VtlExpression;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.clause.aggr.VtlAggrInvocationExpression;
import com.capgemini.istat.vtlcompiler.vtlsemantic.SemanticFactory;
import com.capgemini.istat.vtlcompiler.vtlsemantic.service.result.FunctionResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.Map;
import java.util.UUID;

@Component
@Scope("prototype")
public class AggrInvocationValidation implements OperatorValidation {
    private VtlAggrInvocationExpression vtlAggrInvocationExpression;
    private SemanticFactory semanticFactory;
    private FunctionResultService functionResultService;

    @Override
    public void setVtlExpression(VtlExpression vtlExpression) {
        this.vtlAggrInvocationExpression = (VtlAggrInvocationExpression) vtlExpression;
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
        LinkedList<ResultExpression> resultDataset = null;
        LinkedList<ResultExpression> resoultGrouping = new LinkedList<>();
        if (vtlAggrInvocationExpression.getVtlExpression() != null) {
            resultDataset = semanticFactory.checkSemantic(vtlAggrInvocationExpression.getVtlExpression(), variables);
            resultExpressions.addAll(0, resultDataset);
        } else {
            //è la count
            resultExpressions.addFirst(functionResultService.countExpr((UUID) variables.get(KeyVariables.REQUEST_UUID)));
            return resultExpressions;
        }
        ResultExpression resultExpression =
                functionResultService.getAggrResult(
                        resultDataset.getFirst(),
                        vtlAggrInvocationExpression.getOperator()
                );
        resultExpressions.addFirst(resultExpression);
        if (resultExpression.getResult()!=null && !resultExpression.isAComponent() && !resultExpression.isAScalar()) {
            if (!resultExpression.isAComponent())
                variables.put(KeyVariables.DATASET_IN_CLAUSE, resultExpression.getResult());
            if (vtlAggrInvocationExpression.getVtlHavingClauseExpression() != null) {
                LinkedList<ResultExpression> resultHaving =
                        semanticFactory.checkSemantic(
                                vtlAggrInvocationExpression.getVtlHavingClauseExpression(),
                                variables
                        );
                resultExpressions.addAll(0, resultHaving);
            }
            if (vtlAggrInvocationExpression.getVtlGroupClauseExpression() != null) {
                resoultGrouping = semanticFactory.checkSemantic(vtlAggrInvocationExpression.getVtlGroupClauseExpression(), variables);
            } else {
                resoultGrouping.addFirst(
                        functionResultService.removeIdentifiers(resultExpression)
                );
            }
            resultExpressions.addAll(0, resoultGrouping);

        }
        if (!resultExpression.isAComponent())
            variables.remove(KeyVariables.DATASET_IN_CLAUSE);
        return resultExpressions;
    }
}
