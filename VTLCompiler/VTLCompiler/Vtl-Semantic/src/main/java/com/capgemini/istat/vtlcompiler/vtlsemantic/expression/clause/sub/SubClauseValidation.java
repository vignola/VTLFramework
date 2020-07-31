package com.capgemini.istat.vtlcompiler.vtlsemantic.expression.clause.sub;

import com.capgemini.istat.vtlcompiler.vtlcommon.model.KeyVariables;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.dataset.VtlDataset;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.operator.Operator;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.operator.OperatorValidation;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.semantic.ResultExpression;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.dataset.VtlComponentId;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.VtlExpression;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.clause.sub.VtlSubSpaceExpression;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.clause.sub.VtlSubSpaceItemExpression;
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
public class SubClauseValidation implements OperatorValidation {
    private VtlSubSpaceExpression vtlSubSpaceExpression;
    private SemanticFactory semanticFactory;
    private FunctionResultService functionResultService;

    @Override
    public void setVtlExpression(VtlExpression vtlExpression) {
        this.vtlSubSpaceExpression = (VtlSubSpaceExpression) vtlExpression;
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

        List<String> componentNames = new ArrayList<>();
        List<VtlComponentId> components = new ArrayList<>();
        for (VtlSubSpaceItemExpression vtlSubSpaceItemExpression : vtlSubSpaceExpression.getVtlSubSpaceItemExpressionList()) {
            components.add(vtlSubSpaceItemExpression.getVtlComponentId());
            componentNames.add(vtlSubSpaceItemExpression.getVtlComponentId().getComponentName());
        }


        LinkedList<ResultExpression> resultDataset =
                semanticFactory.checkSemantic(
                        vtlSubSpaceExpression.getVtlDatasetClause(),
                        variables
                );
        VtlDataset datasetClause = resultDataset.getFirst().getResult();

        variables.put(KeyVariables.DATASET_IN_CLAUSE, datasetClause);

        for (VtlComponentId vtlComponentId : components) {
            semanticFactory.checkSemantic(vtlComponentId, variables);
        }

        resultExpressions.addAll(0, resultDataset);
        resultExpressions
                .addFirst(
                        functionResultService.subSpaceComponent(
                                datasetClause,
                                componentNames,
                                Operator.SUB
                        )
                );

        variables.remove(KeyVariables.DATASET_IN_CLAUSE);
        return resultExpressions;
    }
}
