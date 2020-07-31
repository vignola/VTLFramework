package com.capgemini.istat.vtlcompiler.vtlinterpreter.visitor.function.clause.aggr;

import com.capgemini.istat.vtlcompiler.vtlcommon.model.operator.Operator;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.VtlExpression;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.clause.aggr.VtlAggrInvocationExpression;
import com.capgemini.istat.vtlcompiler.vtlinterpreter.visitor.InterpreterVisitor;
import com.capgemini.istat.vtlcompiler.vtlinterpreter.visitor.expression.ExpressionVisitor;
import com.capgemini.istat.vtlcompiler.vtlinterpreter.visitor.grouping.GroupingClauseVisitor;
import com.capgemini.istat.vtlcompiler.vtlinterpreter.visitor.grouping.HavingClauseVisitor;
import com.capgemini.istat.vtlcompiler.vtlparser.VtlParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AggrGroupFunctionVisitor extends InterpreterVisitor<VtlExpression> {

    private ExpressionVisitor expressionVisitor;
    private GroupingClauseVisitor groupingClauseVisitor;
    private HavingClauseVisitor havingClauseVisitor;

    @Autowired
    public void setGroupingClauseVisitor(GroupingClauseVisitor groupingClauseVisitor) {
        this.groupingClauseVisitor = groupingClauseVisitor;
    }

    @Autowired
    public void setHavingClauseVisitor(HavingClauseVisitor havingClauseVisitor) {
        this.havingClauseVisitor = havingClauseVisitor;
    }

    @Autowired
    public void setExpressionVisitor(ExpressionVisitor expressionVisitor) {
        this.expressionVisitor = expressionVisitor;
    }

    @Override
    public VtlAggrInvocationExpression visitAggrDataset(VtlParser.AggrDatasetContext ctx) {
        VtlAggrInvocationExpression vtlAggrInvocationExpression = getVtlAggrInvocation(
                ctx.expr(),
                ctx.groupingClause(),
                ctx.havingClause()
        );
        vtlAggrInvocationExpression.setOperator(Operator.valueOf(ctx.op.getText().toUpperCase()));
        vtlAggrInvocationExpression.setCommand(ctx.getText());
        return vtlAggrInvocationExpression;
    }

    @Override
    public VtlAggrInvocationExpression visitAggrComp(VtlParser.AggrCompContext ctx) {
        VtlAggrInvocationExpression vtlAggrInvocationExpression = getVtlAggrInvocation(
                ctx.exprComponent()
        );
        vtlAggrInvocationExpression.setOperator(Operator.valueOf(ctx.op.getText().toUpperCase()));
        vtlAggrInvocationExpression.setCommand(ctx.getText());
        return vtlAggrInvocationExpression;
    }

    @Override
    public VtlAggrInvocationExpression visitCountAggrComp(VtlParser.CountAggrCompContext ctx) {
        VtlAggrInvocationExpression vtlAggrInvocationExpression = new VtlAggrInvocationExpression();
        vtlAggrInvocationExpression.setCommand(ctx.getText());
        vtlAggrInvocationExpression.setOperator(Operator.COUNT);
        return vtlAggrInvocationExpression;
    }

    private void setUpVisitors() {
        this.expressionVisitor.setVariables(getVariables());
        this.groupingClauseVisitor.setVariables(getVariables());
        this.havingClauseVisitor.setVariables(getVariables());
    }

    private VtlAggrInvocationExpression getVtlAggrInvocation(VtlParser.ExprContext exprContext,
                                                             VtlParser.GroupingClauseContext groupingClauseContext,
                                                             VtlParser.HavingClauseContext havingClauseContext) {
        setUpVisitors();
        VtlAggrInvocationExpression vtlAggrInvocationExpression = new VtlAggrInvocationExpression();
        if (exprContext != null) {
            vtlAggrInvocationExpression.setVtlExpression(
                    expressionVisitor.visit(exprContext)
            );
        }
        if (groupingClauseContext != null) {
            vtlAggrInvocationExpression.setVtlGroupClauseExpression(
                    groupingClauseVisitor.visit(groupingClauseContext)
            );
        }
        if (havingClauseContext != null) {
            vtlAggrInvocationExpression.setVtlHavingClauseExpression(
                    havingClauseVisitor.visitHavingClause(havingClauseContext)
            );
        }
        return vtlAggrInvocationExpression;
    }

    private VtlAggrInvocationExpression getVtlAggrInvocation(VtlParser.ExprComponentContext exprContext) {
        setUpVisitors();
        VtlAggrInvocationExpression vtlAggrInvocationExpression = new VtlAggrInvocationExpression();
        if (exprContext != null) {
            vtlAggrInvocationExpression.setVtlExpression(
                    expressionVisitor.visit(exprContext)
            );
        }
        return vtlAggrInvocationExpression;
    }
}
