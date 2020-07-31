package com.capgemini.istat.vtlcompiler.vtlsemantic.expression.clause;

import com.capgemini.istat.vtlcompiler.vtlcommon.model.KeyVariables;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.dataset.VtlComponent;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.dataset.VtlDataset;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.operator.OperatorValidation;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.semantic.ResultExpression;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.dataset.VtlComponentId;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.VtlExpression;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.grouping.VtlGroupClauseExpression;
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
public class GroupClauseValidation implements OperatorValidation {
    private VtlGroupClauseExpression vtlGroupClauseExpression;
    private SemanticFactory semanticFactory;
    private FunctionResultService functionResultService;


    @Override
    public void setVtlExpression(VtlExpression vtlExpression) {
        this.vtlGroupClauseExpression = (VtlGroupClauseExpression) vtlExpression;
    }

    @Autowired
    public void setFunctionResultService(FunctionResultService functionResultService) {
        this.functionResultService = functionResultService;
    }

    @Autowired
    public void setSemanticFactory(SemanticFactory semanticFactory) {
        this.semanticFactory = semanticFactory;
    }

    @Override
    public LinkedList<ResultExpression> resolve(Map<KeyVariables, Object> variables) throws Exception {
        LinkedList<ResultExpression> resultExpressions = new LinkedList<>();
        List<VtlComponent> compoentsGrouping = new ArrayList<>();
        VtlDataset datasetClause = (VtlDataset) variables.get(KeyVariables.DATASET_IN_CLAUSE);
        if (vtlGroupClauseExpression.getVtlExpression() == null) {
            for (VtlComponentId vtlComponentId : vtlGroupClauseExpression.getVtlComponentIds()) {
                resultExpressions.addAll(0,
                        semanticFactory.checkSemantic(vtlComponentId, variables));
                compoentsGrouping.add(resultExpressions.getFirst().getResultComponent());
            }
            ResultExpression resultExpression =
                    functionResultService.groupIdentifier(
                            datasetClause,
                            compoentsGrouping,
                            vtlGroupClauseExpression.getGroupType()
                    );
            resultExpression.setCommand(vtlGroupClauseExpression.getCommand());
            variables.put(KeyVariables.DATASET_IN_CLAUSE, resultExpression.getResult());
            resultExpressions.addFirst(resultExpression);
        } else {
            semanticFactory.checkSemantic(vtlGroupClauseExpression.getVtlExpression(), variables);
        }
        return resultExpressions;
    }


}
