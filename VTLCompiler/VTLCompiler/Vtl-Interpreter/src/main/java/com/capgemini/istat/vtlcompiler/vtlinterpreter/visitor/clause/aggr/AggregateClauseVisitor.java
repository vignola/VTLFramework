package com.capgemini.istat.vtlcompiler.vtlinterpreter.visitor.clause.aggr;

import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.clause.aggr.VtlAggregateClauseExpression;
import com.capgemini.istat.vtlcompiler.vtlinterpreter.visitor.InterpreterVisitor;
import com.capgemini.istat.vtlcompiler.vtlparser.VtlParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AggregateClauseVisitor extends InterpreterVisitor<VtlAggregateClauseExpression> {

    private AggrFunctionClauseVisitor aggrFunctionClauseVisitor;

    @Autowired
    public void setAggrFunctionClauseVisitor(AggrFunctionClauseVisitor aggrFunctionClauseVisitor) {
        this.aggrFunctionClauseVisitor = aggrFunctionClauseVisitor;
    }

    @Override
    public VtlAggregateClauseExpression visitAggregateClause(VtlParser.AggregateClauseContext ctx) {
        logger.debug("visitAggregateClause -> ");
        VtlAggregateClauseExpression vtlAggregateClauseExpression = new VtlAggregateClauseExpression();
        if (ctx.aggrFunctionClause() != null) {
            aggrFunctionClauseVisitor.setVariables(getVariables());
            for (VtlParser.AggrFunctionClauseContext aggrFunctionClauseContext : ctx.aggrFunctionClause()) {
                vtlAggregateClauseExpression.getVtlAggrFunctionClauseExpressionList().add(aggrFunctionClauseVisitor.visitAggrFunctionClause(aggrFunctionClauseContext));
            }
        }
        vtlAggregateClauseExpression.setCommand(ctx.getText());
        return vtlAggregateClauseExpression;
    }
}
