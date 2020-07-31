package com.capgemini.istat.vtlcompiler.vtlsemantic.expression.general;

import com.capgemini.istat.vtlcompiler.vtlcommon.model.KeyVariables;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.operator.OperatorValidation;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.semantic.ResultExpression;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.VtlExpression;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.VtlParenthesisExpression;
import com.capgemini.istat.vtlcompiler.vtlsemantic.SemanticFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.Map;

@Component
@Scope("prototype")
public class ParenthesisValidation implements OperatorValidation {
    private VtlParenthesisExpression vtlParenthesisExpression;
    private SemanticFactory semanticFactory;

    @Autowired
    public void setSemanticFactory(SemanticFactory semanticFactory) {
        this.semanticFactory = semanticFactory;
    }

    @Override
    public void setVtlExpression(VtlExpression vtlExpression) {
        this.vtlParenthesisExpression = (VtlParenthesisExpression) vtlExpression;
    }

    @Override
    public LinkedList<ResultExpression> resolve(Map<KeyVariables, Object> variables) throws Exception {
        return semanticFactory.checkSemantic(vtlParenthesisExpression.getExpression(), variables);
    }
}
