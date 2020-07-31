package com.capgemini.istat.vtlcompiler.vtlinterpreter.visitor.join;

import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.clause.apply.VtlApplyClause;
import com.capgemini.istat.vtlcompiler.vtlinterpreter.visitor.InterpreterVisitor;
import com.capgemini.istat.vtlcompiler.vtlinterpreter.visitor.expression.ExpressionVisitor;
import com.capgemini.istat.vtlcompiler.vtlparser.VtlParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class JoinApplyClauseVisitor extends InterpreterVisitor {
    private ExpressionVisitor expressionVisitor;

    @Autowired
    public void setExpressionVisitor(ExpressionVisitor expressionVisitor) {
        this.expressionVisitor = expressionVisitor;
    }

    @Override
    public VtlApplyClause visitJoinApplyClause(VtlParser.JoinApplyClauseContext ctx) {
        logger.debug("visitJoinApplyClause -> ");
        VtlApplyClause vtlJoinApplyClause = new VtlApplyClause();
        if (ctx.APPLY() != null)
            logger.debug("joinApplyClause -> " + ctx.APPLY().getText());
        if (ctx.expr() != null) {
            vtlJoinApplyClause.setVtlExpression(expressionVisitor.visit(ctx.expr())
            );
        }
        vtlJoinApplyClause.setCommand(ctx.getText());
        return vtlJoinApplyClause;
    }
}
