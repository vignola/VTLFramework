package com.capgemini.istat.vtlcompiler.vtlsemantic.expression.clause.aggr;

import com.capgemini.istat.vtlcompiler.vtlcommon.model.KeyVariables;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.dataset.VtlDataset;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.operator.OperatorValidation;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.semantic.ResultExpression;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.VtlExpression;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.clause.aggr.VtlAggrClauseExpression;
import com.capgemini.istat.vtlcompiler.vtlsemantic.SemanticFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.Map;

@Component
@Scope("prototype")
public class AggrClauseValidation implements OperatorValidation {
    private VtlAggrClauseExpression vtlAggrClauseExpression;
    private SemanticFactory semanticFactory;

    @Override
    public void setVtlExpression(VtlExpression vtlExpression) {
        this.vtlAggrClauseExpression = (VtlAggrClauseExpression) vtlExpression;
    }

    @Autowired
    public void setSemanticFactory(SemanticFactory semanticFactory) {
        this.semanticFactory = semanticFactory;
    }

    @Override
    public LinkedList<ResultExpression> resolve(Map<KeyVariables, Object> variables) throws Exception {
        LinkedList<ResultExpression> results = new LinkedList<>();
        VtlDataset vtlDataset = (VtlDataset) variables.get(KeyVariables.JOIN_RESULT);
        if (vtlDataset == null) {
            LinkedList<ResultExpression> resultDataset =
                    semanticFactory.checkSemantic(
                            vtlAggrClauseExpression.getVtlDatasetClause(),
                            variables
                    );
            vtlDataset = resultDataset.getFirst().getResult();
            results.addAll(0, resultDataset);
        }
        variables.put(KeyVariables.DATASET_IN_CLAUSE, vtlDataset);
        LinkedList<ResultExpression> resultAggregateClause =
                semanticFactory.checkSemantic(
                        vtlAggrClauseExpression.getVtlAggregateClauseExpression(),
                        variables
                );
        results.addAll(0, resultAggregateClause);
        //Nel caso sia cambiato
        variables.put(KeyVariables.DATASET_IN_CLAUSE, resultAggregateClause.getFirst().getResult());
        if (vtlAggrClauseExpression.getVtlHavingClauseExpression() != null) {
            LinkedList<ResultExpression> resultHaving =
                    semanticFactory.checkSemantic(
                            vtlAggrClauseExpression.getVtlHavingClauseExpression(),
                            variables
                    );
            results.addAll(0, resultHaving);
        }
        if (vtlAggrClauseExpression.getVtlGroupClauseExpression() != null) {
            if (vtlAggrClauseExpression.getVtlGroupClauseExpression().getVtlExpression() == null) {
                LinkedList<ResultExpression> resultGrouping =
                        semanticFactory.checkSemantic(
                                vtlAggrClauseExpression.getVtlGroupClauseExpression(),
                                variables
                        );

                results.addAll(0, resultGrouping);
            } else {
                semanticFactory.checkSemantic(vtlAggrClauseExpression.getVtlGroupClauseExpression().getVtlExpression(), variables);
            }
        }
        variables.remove(KeyVariables.DATASET_IN_CLAUSE);
        results.getFirst().setCommand(vtlAggrClauseExpression.getCommand());
        return results;
    }
}
