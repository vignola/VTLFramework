package com.capgemini.istat.vtlcompiler.vtlsemantic.expression;

import com.capgemini.istat.vtlcompiler.vtlcommon.model.KeyVariables;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.operator.OperatorValidation;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.semantic.ResultExpression;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.VtlExpression;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.statement.VtlStatement;
import com.capgemini.istat.vtlcompiler.vtlsemantic.SemanticFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.Map;

@Component
@Scope("prototype")
public class StatementValidation implements OperatorValidation {
    private VtlStatement vtlStatement;
    private SemanticFactory semanticFactory;

    public VtlStatement getVtlExpression() {
        return vtlStatement;
    }

    @Override
    public void setVtlExpression(VtlExpression vtlExpression) {
        this.vtlStatement = (VtlStatement) vtlExpression;
    }

    @Autowired
    public void setSemanticFactory(SemanticFactory semanticFactory) {
        this.semanticFactory = semanticFactory;
    }

    @Override
    public LinkedList<ResultExpression> resolve(Map<KeyVariables, Object> variables) throws Exception {
        LinkedList<ResultExpression> datasets = null;
        if (vtlStatement.getVtlDefineFunction() != null) {
            datasets = semanticFactory.checkSemantic(vtlStatement.getVtlDefineFunction(), variables);
        } else {
            datasets = semanticFactory.checkSemantic(vtlStatement.getVtlExpression(), variables);
        }
        return datasets;
    }
}
