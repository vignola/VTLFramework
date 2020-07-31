package com.capgemini.istat.vtlcompiler.vtlsemantic.expression.clause.rename;

import com.capgemini.istat.vtlcompiler.vtlcommon.model.KeyVariables;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.dataset.VtlDataset;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.operator.Operator;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.operator.OperatorValidation;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.semantic.ResultExpression;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.dataset.VtlComponentId;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.VtlExpression;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.clause.rename.VtlRenameClauseExpression;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.clause.rename.VtlRenameClauseItemExpression;
import com.capgemini.istat.vtlcompiler.vtlsemantic.SemanticFactory;
import com.capgemini.istat.vtlcompiler.vtlsemantic.service.result.FunctionResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
@Scope("prototype")
public class RenameClauseValidation implements OperatorValidation {
    private VtlRenameClauseExpression renameClauseExpression;
    private SemanticFactory semanticFactory;
    private FunctionResultService functionResultService;

    @Autowired
    public void setFunctionResultService(FunctionResultService functionResultService) {
        this.functionResultService = functionResultService;
    }

    @Autowired
    public void setSemanticFactory(SemanticFactory semanticFactory) {
        this.semanticFactory = semanticFactory;
    }

    @Override
    public void setVtlExpression(VtlExpression vtlExpression) {
        this.renameClauseExpression = (VtlRenameClauseExpression) vtlExpression;
    }

    @Override
    public LinkedList<ResultExpression> resolve(Map<KeyVariables, Object> variables) throws Exception {
        LinkedList<ResultExpression> resultExpressions = new LinkedList<>();
        Map<String, String> renameComponents = new HashMap<>();
        List<VtlComponentId> componentNames = new ArrayList<>();
        for (VtlRenameClauseItemExpression renameClauseItemExpression : renameClauseExpression.getVtlRenameClauseItemExpressionList()) {
            componentNames.add(renameClauseItemExpression.getVtlComponentNameFrom());
            renameComponents.put(
                    renameClauseItemExpression.getVtlComponentNameFrom().getComponentName(),
                    renameClauseItemExpression.getVtlComponentNameTo().getComponentName()
            );
        }
        VtlDataset vtlDataset = (VtlDataset) variables.get(KeyVariables.JOIN_RESULT);
        if (vtlDataset == null) {
            LinkedList<ResultExpression> resultDataset =
                    semanticFactory.checkSemantic(
                            renameClauseExpression.getVtlDatasetClause(),
                            variables
                    );
            vtlDataset = resultDataset.getFirst().getResult();
            resultExpressions.addAll(resultDataset);
        }


        variables.put(KeyVariables.DATASET_IN_CLAUSE, vtlDataset);
        for (VtlComponentId vtlComponentId : componentNames) {
            //Non mi servono i risultati ma solo il controllo di esistenza
            semanticFactory.checkSemantic(vtlComponentId, variables);
        }

        resultExpressions.addFirst(
                functionResultService.renameComponents(
                        vtlDataset,
                        renameComponents,
                        Operator.RENAME
                )
        );
        variables.remove(KeyVariables.DATASET_IN_CLAUSE);
        resultExpressions.getFirst().setCommand(renameClauseExpression.getCommand());
        return resultExpressions;
    }
}
