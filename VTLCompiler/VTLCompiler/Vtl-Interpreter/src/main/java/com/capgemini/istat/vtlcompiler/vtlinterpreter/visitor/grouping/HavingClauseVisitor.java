package com.capgemini.istat.vtlcompiler.vtlinterpreter.visitor.grouping;

import com.capgemini.istat.vtlcompiler.vtlcommon.model.operator.Operator;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.grouping.VtlHavingClauseExpression;
import com.capgemini.istat.vtlcompiler.vtlinterpreter.visitor.InterpreterVisitor;
import com.capgemini.istat.vtlcompiler.vtlinterpreter.visitor.expression.ExpressionVisitor;
import com.capgemini.istat.vtlcompiler.vtlparser.VtlParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class HavingClauseVisitor extends InterpreterVisitor<VtlHavingClauseExpression> {
    private ExpressionVisitor expressionVisitor;

    @Autowired
    public void setExpressionVisitor(ExpressionVisitor expressionVisitor) {
        this.expressionVisitor = expressionVisitor;
    }

    @Override
    public VtlHavingClauseExpression visitHavingClause(VtlParser.HavingClauseContext ctx) {
        VtlHavingClauseExpression vtlHavingClauseExpression = new VtlHavingClauseExpression();
        if (ctx.HAVING() != null) {
            logger.debug("visitHavingClause -> " + ctx.HAVING());
            vtlHavingClauseExpression.setOperator(Operator.HAVING);
        }
        if (ctx.exprComponent() != null) {
            expressionVisitor.setVariables(getVariables());
            vtlHavingClauseExpression.setVtlExpression(expressionVisitor.visit(ctx.exprComponent()));
        }

        vtlHavingClauseExpression.setCommand(ctx.getText());
        return vtlHavingClauseExpression;
    }
}
