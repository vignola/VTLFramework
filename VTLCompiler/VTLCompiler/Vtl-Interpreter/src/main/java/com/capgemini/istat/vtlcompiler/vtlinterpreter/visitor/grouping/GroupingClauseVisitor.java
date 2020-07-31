package com.capgemini.istat.vtlcompiler.vtlinterpreter.visitor.grouping;

import com.capgemini.istat.vtlcompiler.vtlcommon.model.operator.Operator;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.grouping.VtlGroupClauseExpression;
import com.capgemini.istat.vtlcompiler.vtlinterpreter.visitor.InterpreterVisitor;
import com.capgemini.istat.vtlcompiler.vtlinterpreter.visitor.component.ComponentIdVisitor;
import com.capgemini.istat.vtlcompiler.vtlinterpreter.visitor.expression.ExpressionVisitor;
import com.capgemini.istat.vtlcompiler.vtlparser.VtlParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GroupingClauseVisitor extends InterpreterVisitor<VtlGroupClauseExpression> {
    private ExpressionVisitor expressionVisitor;
    private ComponentIdVisitor componentIdVisitor;

    @Autowired
    public void setExpressionVisitor(ExpressionVisitor expressionVisitor) {
        this.expressionVisitor = expressionVisitor;
    }

    @Autowired
    public void setComponentIdVisitor(ComponentIdVisitor componentIdVisitor) {
        this.componentIdVisitor = componentIdVisitor;
    }

    @Override
    public VtlGroupClauseExpression visitGroupByOrExcept(VtlParser.GroupByOrExceptContext ctx) {
        VtlGroupClauseExpression vtlGroupClauseExpression = new VtlGroupClauseExpression();
        if (ctx.op.getText().equalsIgnoreCase("BY"))
            vtlGroupClauseExpression.setGroupType(Operator.GROUP_BY);
        else
            vtlGroupClauseExpression.setGroupType(Operator.GROUP_EXCEPT);

        if (ctx.componentID() != null && !ctx.componentID().isEmpty()) {
            for (VtlParser.ComponentIDContext componentIDContext : ctx.componentID()) {
                vtlGroupClauseExpression.getVtlComponentIds().add(
                        componentIdVisitor.visitComponentID(componentIDContext)
                );
            }
        }
        vtlGroupClauseExpression.setCommand(ctx.getText());
        return vtlGroupClauseExpression;
    }

    @Override
    public VtlGroupClauseExpression visitGroupAll(VtlParser.GroupAllContext ctx) {
        VtlGroupClauseExpression vtlGroupClauseExpression = new VtlGroupClauseExpression();
        vtlGroupClauseExpression.setGroupType(
                Operator.GROUP_ALL
        );
        expressionVisitor.setVariables(getVariables());

        vtlGroupClauseExpression.setVtlExpression(
                expressionVisitor.visit(ctx.exprComponent())
        );
        vtlGroupClauseExpression.setCommand(ctx.getText());
        return vtlGroupClauseExpression;
    }

}
