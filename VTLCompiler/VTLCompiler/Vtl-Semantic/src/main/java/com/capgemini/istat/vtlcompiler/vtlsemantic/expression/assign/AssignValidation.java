package com.capgemini.istat.vtlcompiler.vtlsemantic.expression.assign;

import com.capgemini.istat.vtlcompiler.vtlcommon.model.KeyVariables;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.dataset.VtlDataset;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.operator.Operator;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.operator.OperatorValidation;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.semantic.ResultExpression;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.VtlExpression;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.statement.VtlAssignment;
import com.capgemini.istat.vtlcompiler.vtlsemantic.SemanticFactory;
import com.capgemini.istat.vtlcompiler.vtlsemantic.service.result.FunctionResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.Map;

@Component
@Scope("prototype")

public class AssignValidation implements OperatorValidation {
    private VtlAssignment vtlAssignment;
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

    public VtlAssignment getVtlExpression() {
        return vtlAssignment;
    }

    @Override
    public void setVtlExpression(VtlExpression vtlExpression) {
        this.vtlAssignment = (VtlAssignment) vtlExpression;
    }

    @Override
    public LinkedList<ResultExpression> resolve(Map<KeyVariables, Object> variables) throws Exception {
        String datasetName = vtlAssignment.getVarId().getName();
        LinkedList<ResultExpression> resultExpressions = semanticFactory.checkSemantic(getVtlExpression().getVtlExpression(), variables);
        VtlDataset lastResult = resultExpressions.getFirst().getResult();

        resultExpressions.addFirst(functionResultService.assignDatasetName(lastResult, datasetName, Operator.ASSIGN));
        resultExpressions.getFirst().setCommand(vtlAssignment.getCommand());
        return resultExpressions;
    }
}
