package com.capgemini.istat.vtlcompiler.vtlsemantic.expression.clause.calc;

import com.capgemini.istat.vtlcompiler.vtlcommon.model.KeyVariables;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.dataset.VtlComponent;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.operator.OperatorValidation;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.semantic.ResultExpression;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.VtlExpression;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.clause.calc.VtlCalcClauseItemExpression;
import com.capgemini.istat.vtlcompiler.vtlsemantic.SemanticFactory;
import com.capgemini.istat.vtlcompiler.vtlsemantic.service.result.FunctionResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.Map;

@Component
@Scope("prototype")
public class CalcClauseItemValidation implements OperatorValidation {
    private SemanticFactory semanticFactory;
    private VtlCalcClauseItemExpression vtlCalcClauseItemExpression;
    private FunctionResultService functionResultService;


    @Override
    public void setVtlExpression(VtlExpression vtlExpression) {
        this.vtlCalcClauseItemExpression = (VtlCalcClauseItemExpression) vtlExpression;
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
        LinkedList<ResultExpression> result;
        String componentName = vtlCalcClauseItemExpression.getVtlComponentId().getComponentName();
        result = semanticFactory.checkSemantic(vtlCalcClauseItemExpression.getVtlExpression(), variables);
        ResultExpression resultExpression = result.getFirst();
        VtlComponent partialResult = resultExpression.getResultComponent();

        result.addFirst(
                functionResultService.assignComponentNameAndRole(
                        partialResult,
                        vtlCalcClauseItemExpression.getVtlComponentRole(),
                        componentName
                )
        );
        result.getFirst().setCommand(vtlCalcClauseItemExpression.getCommand());
        return result;
    }
}
