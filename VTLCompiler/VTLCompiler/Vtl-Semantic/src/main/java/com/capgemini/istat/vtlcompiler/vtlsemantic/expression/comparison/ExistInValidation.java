package com.capgemini.istat.vtlcompiler.vtlsemantic.expression.comparison;

import com.capgemini.istat.vtlcompiler.vtlcommon.model.KeyVariables;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.operator.OperatorValidation;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.semantic.ResultExpression;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.VtlExpression;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.comparison.VtlExistIn;
import com.capgemini.istat.vtlcompiler.vtlsemantic.SemanticFactory;
import com.capgemini.istat.vtlcompiler.vtlsemantic.service.result.FunctionResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.Map;

@Component
@Scope("prototype")
public class ExistInValidation implements OperatorValidation {
    private VtlExistIn vtlExistIn;
    private SemanticFactory semanticFactory;
    private FunctionResultService functionResultService;

    @Override
    public void setVtlExpression(VtlExpression vtlExpression) {
        this.vtlExistIn = (VtlExistIn) vtlExpression;
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
        LinkedList<ResultExpression> vtlLeftResults =
                semanticFactory.checkSemantic(
                        vtlExistIn.getLeftExpression(),
                        variables);
        LinkedList<ResultExpression> vtlRightResults =
                semanticFactory.checkSemantic(
                        vtlExistIn.getRightExpression(),
                        variables);

        resultExpressions.addFirst(
                functionResultService.existInResult(
                        vtlLeftResults.getFirst(),
                        vtlRightResults.getFirst(),
                        vtlExistIn.getOperator()
                )
        );
        resultExpressions.getFirst().setCommand(vtlExistIn.getCommand());
        return resultExpressions;
    }
}
