package com.capgemini.istat.vtlcompiler.vtlinterpreter.visitor.function.clause.analytic;

import com.capgemini.istat.vtlcompiler.vtlcommon.model.operator.Operator;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.constant.VtlConstantExpression;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.clause.analytic.VtlAnalyticFunctionExpression;
import com.capgemini.istat.vtlcompiler.vtlinterpreter.visitor.InterpreterVisitor;
import com.capgemini.istat.vtlcompiler.vtlinterpreter.visitor.expression.ExpressionVisitor;
import com.capgemini.istat.vtlcompiler.vtlinterpreter.visitor.terminal.ConstantVisitor;
import com.capgemini.istat.vtlcompiler.vtlparser.VtlParser;
import org.antlr.v4.runtime.Token;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AnFunctionVisitor extends InterpreterVisitor<VtlAnalyticFunctionExpression> {
    private ExpressionVisitor expressionVisitor;
    private AnalyticClauseVisitor analyticClauseVisitor;
    private ConstantVisitor constantVisitor;

    @Autowired
    public void setExpressionVisitor(ExpressionVisitor expressionVisitor) {
        this.expressionVisitor = expressionVisitor;
    }

    @Autowired
    public void setAnalyticClauseVisitor(AnalyticClauseVisitor analyticClauseVisitor) {
        this.analyticClauseVisitor = analyticClauseVisitor;
    }

    @Autowired
    public void setConstantVisitor(ConstantVisitor constantVisitor) {
        this.constantVisitor = constantVisitor;
    }

    @Override
    public VtlAnalyticFunctionExpression visitAnSimpleFunction(VtlParser.AnSimpleFunctionContext ctx) {
        VtlAnalyticFunctionExpression vtlAnalyticFunctionExpression = getAnalyticFunction(
                ctx.expr(), null,
                ctx.op, ctx.getText()
        );
        settingUpPartitioning(vtlAnalyticFunctionExpression, ctx.partition, ctx.orderBy, ctx.windowing, null, null);
        return vtlAnalyticFunctionExpression;
    }

    @Override
    public VtlAnalyticFunctionExpression visitLagOrLeadAn(VtlParser.LagOrLeadAnContext ctx) {
        VtlAnalyticFunctionExpression vtlAnalyticFunctionExpression = getAnalyticFunction(
                ctx.expr(), null,
                ctx.op, ctx.getText()
        );
        settingUpPartitioning(vtlAnalyticFunctionExpression, ctx.partition, ctx.orderBy, null, ctx.offet, ctx.defaultValue);
        return vtlAnalyticFunctionExpression;
    }

    @Override
    public VtlAnalyticFunctionExpression visitRatioToReportAn(VtlParser.RatioToReportAnContext ctx) {
        VtlAnalyticFunctionExpression vtlAnalyticFunctionExpression = getAnalyticFunction(
                ctx.expr(), null,
                ctx.op, ctx.getText()
        );
        settingUpPartitioning(vtlAnalyticFunctionExpression, ctx.partition, null, null, null, null);
        return vtlAnalyticFunctionExpression;
    }

    /**
     * Components
     */

    @Override
    public VtlAnalyticFunctionExpression visitAnSimpleFunctionComponent(VtlParser.AnSimpleFunctionComponentContext ctx) {
        VtlAnalyticFunctionExpression vtlAnalyticFunctionExpression = getAnalyticFunction(
                null, ctx.exprComponent(),
                ctx.op, ctx.getText()
        );
        settingUpPartitioning(vtlAnalyticFunctionExpression, ctx.partition, ctx.orderBy, ctx.windowing, null, null);
        return vtlAnalyticFunctionExpression;
    }

    @Override
    public VtlAnalyticFunctionExpression visitLagOrLeadAnComponent(VtlParser.LagOrLeadAnComponentContext ctx) {
        VtlAnalyticFunctionExpression vtlAnalyticFunctionExpression = getAnalyticFunction(
                null, ctx.exprComponent(),
                ctx.op, ctx.getText()
        );
        settingUpPartitioning(vtlAnalyticFunctionExpression, ctx.partition, ctx.orderBy, null, ctx.offet, ctx.defaultValue );
        return vtlAnalyticFunctionExpression;
    }

    @Override
    public VtlAnalyticFunctionExpression visitRankAnComponent(VtlParser.RankAnComponentContext ctx) {
        VtlAnalyticFunctionExpression vtlAnalyticFunctionExpression = getAnalyticFunction(null, null, ctx.op, ctx.getText());
        settingUpPartitioning(vtlAnalyticFunctionExpression, ctx.partition, ctx.orderBy, null, null, null);
        vtlAnalyticFunctionExpression.setVtlExpression(vtlAnalyticFunctionExpression.getVltOrderBy().getVtlOrderByItems().get(0).getVtlComponentId());
        return vtlAnalyticFunctionExpression;
    }

    @Override
    public VtlAnalyticFunctionExpression visitRatioToReportAnComponent(VtlParser.RatioToReportAnComponentContext ctx) {
        VtlAnalyticFunctionExpression vtlAnalyticFunctionExpression = getAnalyticFunction(
                null, ctx.exprComponent(),
                ctx.op, ctx.getText()
        );
        settingUpPartitioning(vtlAnalyticFunctionExpression, ctx.partition, null, null, null, null);
        return vtlAnalyticFunctionExpression;
    }


    private VtlAnalyticFunctionExpression settingUpPartitioning(VtlAnalyticFunctionExpression vtlAnalyticFunctionExpression,
                                                                VtlParser.PartitionByClauseContext partitionByClauseContext,
                                                                VtlParser.OrderByClauseContext orderByClauseContext,
                                                                VtlParser.WindowingClauseContext windowingClauseContext,
                                                                VtlParser.SignedIntegerContext signedIntegerContext,
                                                                VtlParser.ConstantContext constantContext) {

        if (partitionByClauseContext != null) {
            vtlAnalyticFunctionExpression.setVtlPartitionBy(
                    analyticClauseVisitor.visitPartitionByClause(
                            partitionByClauseContext
                    )
            );
        }
        if (orderByClauseContext != null) {
            vtlAnalyticFunctionExpression.setVltOrderBy(
                    analyticClauseVisitor.visitOrderByClause(
                            orderByClauseContext
                    )
            );
        }
        if (windowingClauseContext != null) {
            vtlAnalyticFunctionExpression.setVtlWindowing(
                    analyticClauseVisitor.visitWindowingClause(
                            windowingClauseContext
                    )
            );
        }
        if (signedIntegerContext != null) {
            vtlAnalyticFunctionExpression.setOffset(
                    Integer.valueOf(signedIntegerContext.getText())
            );
        }
        if (constantContext != null) {
            vtlAnalyticFunctionExpression.setDefaultValue(
                    new VtlConstantExpression(
                            constantVisitor.visitConstant(constantContext)
                    )
            );
        }
        return vtlAnalyticFunctionExpression;
    }


    private VtlAnalyticFunctionExpression getAnalyticFunction(VtlParser.ExprContext exprContext,
                                                              VtlParser.ExprComponentContext exprComponentContext,
                                                              Token operator,
                                                              String text){
        VtlAnalyticFunctionExpression vtlAnalyticFunctionExpression = new VtlAnalyticFunctionExpression();
        if (exprContext != null) {
            expressionVisitor.setVariables(getVariables());
            vtlAnalyticFunctionExpression.setVtlExpression(
                    expressionVisitor.visit(exprContext)
            );
        } else if (exprComponentContext !=null){
            expressionVisitor.setVariables(getVariables());
            vtlAnalyticFunctionExpression.setVtlExpression(
                    expressionVisitor.visit(exprComponentContext)
            );
        }
        vtlAnalyticFunctionExpression.setOperator(Operator.valueOf(operator.getText().toUpperCase()));
        vtlAnalyticFunctionExpression.setCommand(text);
        return vtlAnalyticFunctionExpression;
    }
}
