package com.capgemini.istat.vtlcompiler.vtlsemantic.expression.general;

import com.capgemini.istat.vtlcompiler.vtlcommon.model.KeyVariables;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.dataset.VtlComponent;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.dataset.VtlDataset;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.operator.Operator;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.operator.OperatorValidation;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.semantic.ResultExpression;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.dataset.VtlVarId;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.VtlExpression;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.VtlMembership;
import com.capgemini.istat.vtlcompiler.vtlsemantic.SemanticFactory;
import com.capgemini.istat.vtlcompiler.vtlsemantic.service.result.FunctionResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.Map;

@Component
@Scope("prototype")
public class MembershipValidation implements OperatorValidation {
    private VtlMembership vtlMembership;
    private SemanticFactory semanticFactory;
    private FunctionResultService functionResultService;

    @Override
    public void setVtlExpression(VtlExpression vtlExpression) {
        this.vtlMembership = (VtlMembership) vtlExpression;
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

        LinkedList<ResultExpression> result = new LinkedList<>();
        if (variables.get(KeyVariables.JOIN_OPERATOR) != null
                && vtlMembership.getVtlExpression() instanceof VtlVarId) {
                VtlVarId vtlVarId = (VtlVarId) vtlMembership.getVtlExpression();
                result.add(
                        functionResultService.getComponent(
                                (VtlDataset) variables.get(KeyVariables.DATASET_IN_CLAUSE),
                                vtlVarId.getName() + "#" + vtlMembership.getVtlComponentId().getComponentName().replaceAll("'", ""),
                                Operator.COMPONENT,
                                vtlVarId.isIgnoreCase()
                        )
                );
                return result;
        }
        variables.put(KeyVariables.MEMBERSHIP_IN_CLAUSE, true);
        LinkedList<ResultExpression> resultDataset = semanticFactory.checkSemantic(vtlMembership.getVtlExpression(), variables);
        variables.remove(KeyVariables.MEMBERSHIP_IN_CLAUSE);
        VtlDataset vtlDatasetResult = resultDataset.getFirst().getResult();
        VtlDataset vtlDatasetInClause = (VtlDataset) variables.get(KeyVariables.DATASET_IN_CLAUSE);
        variables.put(KeyVariables.DATASET_MEMBERSHIP, vtlDatasetResult);
        result.addAll(0, resultDataset);
        LinkedList<ResultExpression> resultComponents = semanticFactory.checkSemantic(
                vtlMembership.getVtlComponentId(),
                variables
        );
        VtlComponent vtlResultComponent = resultComponents.getFirst().getResultComponent();

        if (vtlDatasetInClause != null) {
            result.addFirst(
                    functionResultService.getComponentMembershipInClause(
                            vtlDatasetResult,
                            vtlResultComponent.getName(),
                            vtlDatasetInClause.getName(),
                            Operator.COMPONENT
                    )
            );

        } else {

            //prendo il component
            result.addFirst(functionResultService.createTemporaryDatasetFromComponentName(
                    vtlDatasetResult,
                    vtlMembership.getVtlComponentId().getComponentName(),
                    Operator.COMPONENT
                    )
            );
        }

        result.getFirst().setCommand(vtlMembership.getCommand());
        variables.remove(KeyVariables.DATASET_MEMBERSHIP);
        return result;
    }
}
