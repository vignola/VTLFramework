package com.capgemini.istat.vtlcompiler.vtlsemantic.expression.time;

import com.capgemini.istat.vtlcompiler.vtlcommon.model.KeyVariables;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.operator.OperatorValidation;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.semantic.ResultExpression;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.VtlExpression;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.time.VtlCurrentDate;
import com.capgemini.istat.vtlcompiler.vtlsemantic.service.result.FunctionResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.Map;
import java.util.UUID;

@Component
@Scope("prototype")
public class CurrentDateValidation implements OperatorValidation {
    private VtlCurrentDate vtlCurrentDate;
    private FunctionResultService functionResultService;

    @Autowired
    public void setFunctionResultService(FunctionResultService functionResultService) {
        this.functionResultService = functionResultService;
    }

    @Override
    public void setVtlExpression(VtlExpression vtlExpression) {
        vtlCurrentDate = (VtlCurrentDate) vtlExpression;
    }

    @Override
    public LinkedList<ResultExpression> resolve(Map<KeyVariables, Object> variables) throws Exception {
        LinkedList<ResultExpression> resultExpressions = new LinkedList<>();
        resultExpressions.add(functionResultService.currentDate((UUID) variables.get(KeyVariables.REQUEST_UUID)));
        resultExpressions.getFirst().setCommand(vtlCurrentDate.getCommand());
        return resultExpressions;
    }
}
