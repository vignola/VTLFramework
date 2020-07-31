package com.capgemini.istat.vtlcompiler.vtlsemantic.expression.join;

import com.capgemini.istat.vtlcompiler.vtlcommon.model.KeyVariables;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.operator.OperatorValidation;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.semantic.ResultExpression;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.VtlExpression;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.join.VtlJoinBody;
import com.capgemini.istat.vtlcompiler.vtlsemantic.SemanticFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.Map;

@Component
@Scope("prototype")
public class JoinBodyValidation implements OperatorValidation {
    private VtlJoinBody vtlJoinBody;
    private SemanticFactory semanticFactory;

    @Override
    public void setVtlExpression(VtlExpression vtlExpression) {
        this.vtlJoinBody = (VtlJoinBody) vtlExpression;
    }

    @Autowired
    public void setSemanticFactory(SemanticFactory semanticFactory) {
        this.semanticFactory = semanticFactory;
    }

    @Override
    public LinkedList<ResultExpression> resolve(Map<KeyVariables, Object> variables) throws Exception {
        LinkedList<ResultExpression> resultExpressions = new LinkedList<>();
        if (vtlJoinBody.getVtlFilterClauseExpression() != null) {
            resultExpressions.addAll(
                    0,
                    semanticFactory.checkSemantic(vtlJoinBody.getVtlFilterClauseExpression(), variables)
            );
        }
        //TODO applyClause
        if (vtlJoinBody.getVtlJoinBodyClause() != null) {
            resultExpressions.addAll(
                    0,
                    semanticFactory.checkSemantic(vtlJoinBody.getVtlJoinBodyClause(), variables)
            );
            variables.put(KeyVariables.JOIN_RESULT, resultExpressions.getFirst().getResult());
        }

        if (vtlJoinBody.getVtlRenameClauseExpression() != null) {
            resultExpressions.addAll(
                    0,
                    semanticFactory.checkSemantic(vtlJoinBody.getVtlRenameClauseExpression(), variables)
            );
            variables.put(KeyVariables.JOIN_RESULT, resultExpressions.getFirst().getResult());
        }

        if (vtlJoinBody.getKeepDropClause() != null) {
            resultExpressions.addAll(
                    0,
                    semanticFactory.checkSemantic(vtlJoinBody.getKeepDropClause(), variables)
            );
            variables.put(KeyVariables.JOIN_RESULT, resultExpressions.getFirst().getResult());
        }
        if (resultExpressions.size() != 0) {
            resultExpressions.getFirst().setCommand(vtlJoinBody.getCommand());
        }
        return resultExpressions;
    }
}
