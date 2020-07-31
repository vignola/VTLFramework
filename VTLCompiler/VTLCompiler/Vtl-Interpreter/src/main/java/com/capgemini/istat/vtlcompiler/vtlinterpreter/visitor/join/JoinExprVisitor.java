package com.capgemini.istat.vtlcompiler.vtlinterpreter.visitor.join;

import com.capgemini.istat.vtlcompiler.vtlcommon.model.KeyVariables;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.operator.Operator;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.join.VtlJoinExpression;
import com.capgemini.istat.vtlcompiler.vtlinterpreter.visitor.InterpreterVisitor;
import com.capgemini.istat.vtlcompiler.vtlparser.VtlParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class JoinExprVisitor extends InterpreterVisitor<VtlJoinExpression> {
    private JoinClauseVisitor joinClauseVisitor;
    private JoinBodyVisitor joinBodyVisitor;

    @Autowired
    public void setJoinClauseVisitor(JoinClauseVisitor joinClauseVisitor) {
        this.joinClauseVisitor = joinClauseVisitor;
    }

    @Autowired
    public void setJoinBodyVisitor(JoinBodyVisitor joinBodyVisitor) {
        this.joinBodyVisitor = joinBodyVisitor;
    }


    @Override
    public VtlJoinExpression visitJoinExpr(VtlParser.JoinExprContext ctx) {
        VtlJoinExpression vtlJoinExpression = new VtlJoinExpression();
        vtlJoinExpression.setOperator(getJoinTYpe(ctx));
        getVariables().put(KeyVariables.JOIN_OPERATOR, vtlJoinExpression.getOperator());
        if (ctx.joinClause() != null) {
            joinClauseVisitor.setVariables(getVariables());
            vtlJoinExpression.setVtlJoinSelectClause(
                    joinClauseVisitor.visitJoinClause(ctx.joinClause())
            );

        }

        if (ctx.joinClauseWithoutUsing() != null) {
            joinClauseVisitor.setVariables(getVariables());
            vtlJoinExpression.setVtlJoinSelectClause(
                    joinClauseVisitor.visitJoinClauseWithoutUsing(ctx.joinClauseWithoutUsing())
            );

        }

        if (ctx.joinBody() != null) {
            joinBodyVisitor.setVariables(getVariables());
            vtlJoinExpression.setVtlJoinBody(
                    joinBodyVisitor.visitJoinBody(ctx.joinBody())
            );
        }
        getVariables().remove(KeyVariables.JOIN_OPERATOR, vtlJoinExpression.getOperator());
        vtlJoinExpression.setCommand(ctx.getText());
        return vtlJoinExpression;
    }

    private Operator getJoinTYpe(VtlParser.JoinExprContext ctx) {
        logger.debug("visitJoinKeyword -> ");
        if (ctx.CROSS_JOIN() != null)
            return Operator.CROSS_JOIN;
        if (ctx.FULL_JOIN() != null)
            return Operator.FULL_JOIN;
        if (ctx.INNER_JOIN() != null)
            return Operator.INNER_JOIN;
        if (ctx.LEFT_JOIN() != null)
            return Operator.LEFT_JOIN;
        return null;
    }
}
