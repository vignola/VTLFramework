package com.capgemini.istat.vtlcompiler.vtlsemantic.expression.functions.generic;

import com.capgemini.istat.vtlcompiler.vtlcommon.model.KeyVariables;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.operator.OperatorValidation;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.semantic.ResultExpression;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.VtlExpression;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.generic.VtlCastExpression;
import com.capgemini.istat.vtlcompiler.vtlsemantic.SemanticFactory;
import com.capgemini.istat.vtlcompiler.vtlsemantic.service.result.FunctionResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.Map;

@Component
@Scope("prototype")
public class CastValidation implements OperatorValidation {
    private VtlCastExpression vtlCastExpression;
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
        this.vtlCastExpression = (VtlCastExpression) vtlExpression;
    }

    @Override
    public LinkedList<ResultExpression> resolve(Map<KeyVariables, Object> variables) throws Exception {
        LinkedList<ResultExpression> resultExpressions = new LinkedList<>();
        resultExpressions.addAll(0,
                semanticFactory.checkSemantic(vtlCastExpression.getVtlExpression(), variables)
        );
        resultExpressions.addFirst(functionResultService.doCast(vtlCastExpression));

        return resultExpressions;
    }

}
