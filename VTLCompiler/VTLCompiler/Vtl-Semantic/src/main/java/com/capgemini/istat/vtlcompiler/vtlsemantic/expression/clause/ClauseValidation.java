package com.capgemini.istat.vtlcompiler.vtlsemantic.expression.clause;

import com.capgemini.istat.vtlcompiler.vtlcommon.model.KeyVariables;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.operator.OperatorValidation;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.semantic.ResultExpression;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.VtlExpression;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.clause.VtlClauseOperator;
import com.capgemini.istat.vtlcompiler.vtlsemantic.SemanticFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.Map;

@Component
@Scope("prototype")
public class ClauseValidation implements OperatorValidation {
    private VtlClauseOperator vtlClauseOperator;

    private SemanticFactory semanticFactory;

    @Autowired
    public void setSemanticFactory(SemanticFactory semanticFactory) {
        this.semanticFactory = semanticFactory;
    }


    @Override
    public void setVtlExpression(VtlExpression vtlExpression) {
        this.vtlClauseOperator = (VtlClauseOperator) vtlExpression;
    }

    public VtlClauseOperator getVtlClauseOperator() {
        return vtlClauseOperator;
    }

    @Override
    public LinkedList<ResultExpression> resolve(Map<KeyVariables, Object> variables) throws Exception {
        LinkedList<ResultExpression> result = semanticFactory.checkSemantic(vtlClauseOperator.getVtlDatasetClause(), variables);
        return result;
    }
}
