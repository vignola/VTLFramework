package com.capgemini.istat.vtlcompiler.vtlsemantic.expression.clause.calc;

import com.capgemini.istat.vtlcompiler.vtlcommon.model.KeyVariables;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.dataset.VtlComponent;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.dataset.VtlDataset;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.operator.OperatorValidation;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.semantic.ResultExpression;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.VtlExpression;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.clause.calc.VtlCalcClauseExpression;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.clause.calc.VtlCalcClauseItemExpression;
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
public class CalcClauseValidation implements OperatorValidation {
    private VtlCalcClauseExpression vtlCalcClauseExpression;
    private SemanticFactory semanticFactory;
    private FunctionResultService functionResultService;

    @Override
    public void setVtlExpression(VtlExpression vtlExpression) {
        this.vtlCalcClauseExpression = (VtlCalcClauseExpression) vtlExpression;
    }

    public VtlCalcClauseExpression getVtlCalcClauseExpression() {
        return vtlCalcClauseExpression;
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
        VtlDataset vtlDataset = (VtlDataset) variables.get(KeyVariables.JOIN_RESULT);
        if (vtlDataset == null) {
            LinkedList<ResultExpression> datasetClause = semanticFactory.checkSemantic(vtlCalcClauseExpression.getDataset(), variables);
            vtlDataset = datasetClause.getFirst().getResult();
        }

        List<VtlComponent> vtlComponentResultList = new ArrayList<>();
        variables.put(KeyVariables.DATASET_IN_CLAUSE, vtlDataset);
        for (VtlCalcClauseItemExpression vtlCalcClauseItemExpression : vtlCalcClauseExpression.getVtlCalcClauseItemExpressionList()) {
            result.addAll(0, semanticFactory.checkSemantic(vtlCalcClauseItemExpression, variables));
            vtlComponentResultList.add(result.getFirst().getResultComponent());
        }
        result.addFirst(
                functionResultService.addComponentsToDataset(
                        vtlDataset,
                        vtlComponentResultList
                )
        );

        variables.remove(KeyVariables.DATASET_IN_CLAUSE);
        result.getFirst().setCommand(vtlCalcClauseExpression.getCommand());
        return result;
    }
}
