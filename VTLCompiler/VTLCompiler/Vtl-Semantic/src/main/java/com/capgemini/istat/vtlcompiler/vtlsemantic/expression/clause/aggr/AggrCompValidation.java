package com.capgemini.istat.vtlcompiler.vtlsemantic.expression.clause.aggr;

import com.capgemini.istat.vtlcompiler.vtlcommon.model.KeyVariables;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.operator.OperatorValidation;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.semantic.ResultExpression;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.VtlExpression;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.clause.aggr.VtlAggrComp;
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
public class AggrCompValidation implements OperatorValidation {
    private VtlAggrComp vtlAggrComp;
    private SemanticFactory semanticFactory;
    private FunctionResultService functionResultService;

    @Override
    public void setVtlExpression(VtlExpression vtlExpression) {
        this.vtlAggrComp = (VtlAggrComp) vtlExpression;
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
        if (vtlAggrComp.getVtlExpression() != null) {
            resultExpressions.addAll(semanticFactory.checkSemantic(vtlAggrComp.getVtlExpression(), variables));
            ResultExpression resultExpression =
                    functionResultService.getUnaryResult(
                            resultExpressions.getFirst(),
                            vtlAggrComp.getOperator()
                    );
            resultExpressions.addFirst(resultExpression);
        } else {
            //è la count
            resultExpressions.addFirst(functionResultService.countExpr((UUID) variables.get(KeyVariables.REQUEST_UUID)));
        }
        resultExpressions.getFirst().setCommand(vtlAggrComp.getCommand());
        return resultExpressions;
    }
}
