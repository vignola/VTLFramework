package com.capgemini.istat.vtlcompiler.vtlsemantic.expression.terminal;

import com.capgemini.istat.vtlcompiler.vtlcommon.model.KeyVariables;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.operator.OperatorValidation;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.semantic.ResultExpression;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.constant.VtlConstantExpression;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.VtlExpression;
import com.capgemini.istat.vtlcompiler.vtlsemantic.service.result.FunctionResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.Map;
import java.util.UUID;

@Component
@Scope("prototype")
public class ConstantValidation implements OperatorValidation {
    private VtlConstantExpression vtlConstantExpression;
    private FunctionResultService functionResultService;

    @Override
    public void setVtlExpression(VtlExpression vtlExpression) {
        this.vtlConstantExpression = (VtlConstantExpression) vtlExpression;
    }

    @Autowired
    public void setFunctionResultService(FunctionResultService functionResultService) {
        this.functionResultService = functionResultService;
    }

    @Override
    public LinkedList<ResultExpression> resolve(Map<KeyVariables, Object> variables) throws Exception {
        LinkedList<ResultExpression> resultExpressions = new LinkedList<>();
        UUID requestUuid = (UUID) variables.get(KeyVariables.REQUEST_UUID);
        vtlConstantExpression.setRequestUuid(requestUuid);
        ResultExpression resultExpression = functionResultService.createTemporaryScalarDataset(vtlConstantExpression.getVtlConstant());
        resultExpression.setCommand(vtlConstantExpression.getCommand());
        resultExpressions.addFirst(resultExpression);
        return resultExpressions;
    }
}
