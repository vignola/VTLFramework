package com.capgemini.istat.vtlcompiler.vtlsemantic.expression.functions.validation;

import com.capgemini.istat.vtlcompiler.vtlcommon.model.KeyVariables;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.dataset.VtlDataset;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.dataset.VtlType;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.operator.OperatorValidation;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.semantic.ResultExpression;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.VtlExpression;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.validation.VtlCheck;
import com.capgemini.istat.vtlcompiler.vtlsemantic.SemanticFactory;
import com.capgemini.istat.vtlcompiler.vtlsemantic.service.result.FunctionResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.Map;

@Component
@Scope("prototype")
public class CheckValidation implements OperatorValidation {
    private VtlCheck vtlCheck;
    private SemanticFactory semanticFactory;
    private FunctionResultService functionResultService;

    @Override
    public void setVtlExpression(VtlExpression vtlExpression) {
        this.vtlCheck = (VtlCheck) vtlExpression;
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
        resultExpressions.addAll(
                semanticFactory.checkSemantic(vtlCheck.getBooleanDataset(), variables)
        );
        VtlType vtlType = null;
        if (vtlCheck.getImbalanceExpression() != null) {
            LinkedList<ResultExpression> imbalanceResult = semanticFactory.checkSemantic(vtlCheck.getImbalanceExpression(), variables);
            VtlDataset vtlDataset = imbalanceResult.getFirst().getResult();
            vtlType = vtlDataset.getMeasures().get(0).getType();
        }
        resultExpressions.addFirst(
                functionResultService.checkValidation(vtlCheck, vtlType)
        );
        return resultExpressions;
    }
}
