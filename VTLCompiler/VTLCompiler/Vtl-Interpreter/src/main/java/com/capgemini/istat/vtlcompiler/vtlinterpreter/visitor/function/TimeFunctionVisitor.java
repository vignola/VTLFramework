package com.capgemini.istat.vtlcompiler.vtlinterpreter.visitor.function;

import com.capgemini.istat.vtlcompiler.vtlcommon.model.operator.Operator;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.constant.VtlConstantExpression;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.constant.VtlDuration;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.constant.VtlInteger;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.VtlExpression;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.time.VtlCurrentDate;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.time.VtlTimeUnaryExpression;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.time.VtlTimeUnaryWithOneParameterExpression;
import com.capgemini.istat.vtlcompiler.vtlinterpreter.visitor.InterpreterVisitor;
import com.capgemini.istat.vtlcompiler.vtlinterpreter.visitor.expression.ExpressionVisitor;
import com.capgemini.istat.vtlcompiler.vtlparser.VtlParser;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.tree.TerminalNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class TimeFunctionVisitor extends InterpreterVisitor<VtlExpression> {
    private ExpressionVisitor expressionVisitor;

    @Autowired
    public void setExpressionVisitor(ExpressionVisitor expressionVisitor) {
        this.expressionVisitor = expressionVisitor;
    }

    @Override
    public VtlTimeUnaryExpression visitPeriodAtom(VtlParser.PeriodAtomContext ctx) {
        expressionVisitor.setVariables(getVariables());
        logger.debug("visitPeriodExpr -> ");
        VtlTimeUnaryExpression vtlUnaryExpression = new VtlTimeUnaryExpression();
        vtlUnaryExpression.setOperator(Operator.PERIOD_INDICATOR);
        if (ctx.PERIOD_INDICATOR() != null)
            logger.debug("visitPeriodExpr -> " + ctx.PERIOD_INDICATOR().getText());
        if (ctx.expr() != null) {
            vtlUnaryExpression.setVtlExpression(expressionVisitor.visit(ctx.expr()));
        }
        vtlUnaryExpression.setCommand(ctx.getText());
        return vtlUnaryExpression;
    }

    @Override
    public VtlTimeUnaryExpression visitFillTimeAtom(VtlParser.FillTimeAtomContext ctx) {
        expressionVisitor.setVariables(getVariables());
        VtlTimeUnaryExpression vtlTimeUnaryExpression = new VtlTimeUnaryExpression();
        if (ctx.ALL() != null) {
            vtlTimeUnaryExpression.setOperator(Operator.FILL_TIME_SERIES_ALL);
        }
        if (ctx.expr() != null) {
            vtlTimeUnaryExpression.setVtlExpression(expressionVisitor.visit(ctx.expr()));
        }
        if (ctx.SINGLE() != null) {
            logger.debug("visitTimeSeriesExpr -> " + ctx.SINGLE().getText());
            vtlTimeUnaryExpression.setOperator(Operator.FILL_TIME_SERIES_SINGLE);
        }
        vtlTimeUnaryExpression.setCommand(ctx.getText());
        return vtlTimeUnaryExpression;
    }

    @Override
    public VtlTimeUnaryExpression visitFlowAtom(VtlParser.FlowAtomContext ctx) {
        expressionVisitor.setVariables(getVariables());
        VtlTimeUnaryExpression vtlTimeUnaryExpression = new VtlTimeUnaryExpression();
        vtlTimeUnaryExpression.setVtlExpression(expressionVisitor.visit(ctx.expr()));
        vtlTimeUnaryExpression.setOperator(Operator.valueOf(ctx.op.getText().toUpperCase()));
        vtlTimeUnaryExpression.setCommand(ctx.getText());
        return vtlTimeUnaryExpression;
    }

    @Override
    public VtlTimeUnaryWithOneParameterExpression visitTimeShiftAtom(VtlParser.TimeShiftAtomContext ctx) {
        expressionVisitor.setVariables(getVariables());
        VtlTimeUnaryWithOneParameterExpression vtlTimeUnaryWithOneParameterExpression = new VtlTimeUnaryWithOneParameterExpression();

        if (ctx.expr() != null) {
            vtlTimeUnaryWithOneParameterExpression.setVtlExpression(expressionVisitor.visit(ctx.expr()));
        }
        return getVtlTimeUnaryWithOneParameterExpression(vtlTimeUnaryWithOneParameterExpression, ctx.signedInteger(), ctx.getText());
    }

    @Override
    public VtlTimeUnaryWithOneParameterExpression visitTimeAggAtom(VtlParser.TimeAggAtomContext ctx) {
        VtlTimeUnaryWithOneParameterExpression vtlTimeUnaryWithOneParameterExpression = getTimeAgg(ctx.periodIndTo, ctx.periodIndFrom);

        if (ctx.optionalExpr() != null) {
            vtlTimeUnaryWithOneParameterExpression.setVtlExpression(expressionVisitor.visit(ctx.optionalExpr()));
        }
        return getVtlTimeUnaryWithOneParameterExpression(vtlTimeUnaryWithOneParameterExpression, ctx.FIRST(), ctx.LAST(), ctx.getText());
    }

    @Override
    public VtlCurrentDate visitCurrentDateAtom(VtlParser.CurrentDateAtomContext ctx) {
        VtlCurrentDate vtlCurrentDate = new VtlCurrentDate();
        vtlCurrentDate.setCommand(ctx.getText());
        vtlCurrentDate.setOperator(Operator.CURRENT_DATE);
        return vtlCurrentDate;
    }

    @Override
    public VtlTimeUnaryExpression visitPeriodAtomComponent(VtlParser.PeriodAtomComponentContext ctx) {
        expressionVisitor.setVariables(getVariables());
        logger.debug("visitPeriodExpr -> ");
        VtlTimeUnaryExpression vtlUnaryExpression = new VtlTimeUnaryExpression();
        vtlUnaryExpression.setOperator(Operator.PERIOD_INDICATOR);
        if (ctx.PERIOD_INDICATOR() != null)
            logger.debug("visitPeriodExpr -> " + ctx.PERIOD_INDICATOR().getText());
        if (ctx.exprComponent() != null) {
            vtlUnaryExpression.setVtlExpression(expressionVisitor.visit(ctx.exprComponent()));
        }
        vtlUnaryExpression.setCommand(ctx.getText());
        return vtlUnaryExpression;
    }

    @Override
    public VtlTimeUnaryExpression visitFillTimeAtomComponent(VtlParser.FillTimeAtomComponentContext ctx) {
        expressionVisitor.setVariables(getVariables());
        VtlTimeUnaryExpression vtlTimeUnaryExpression = new VtlTimeUnaryExpression();
        if (ctx.ALL() != null) {
            vtlTimeUnaryExpression.setOperator(Operator.FILL_TIME_SERIES_ALL);
        }
        if (ctx.exprComponent() != null) {
            vtlTimeUnaryExpression.setVtlExpression(expressionVisitor.visit(ctx.exprComponent()));
        }
        if (ctx.SINGLE() != null) {
            logger.debug("visitTimeSeriesExpr -> " + ctx.SINGLE().getText());
            vtlTimeUnaryExpression.setOperator(Operator.FILL_TIME_SERIES_SINGLE);
        }
        vtlTimeUnaryExpression.setCommand(ctx.getText());
        return vtlTimeUnaryExpression;
    }

    @Override
    public VtlTimeUnaryExpression visitFlowAtomComponent(VtlParser.FlowAtomComponentContext ctx) {
        expressionVisitor.setVariables(getVariables());
        VtlTimeUnaryExpression vtlTimeUnaryExpression = new VtlTimeUnaryExpression();
        vtlTimeUnaryExpression.setVtlExpression(expressionVisitor.visit(ctx.exprComponent()));
        vtlTimeUnaryExpression.setOperator(Operator.valueOf(ctx.op.getText().toUpperCase()));
        vtlTimeUnaryExpression.setCommand(ctx.getText());
        return vtlTimeUnaryExpression;
    }

    @Override
    public VtlTimeUnaryWithOneParameterExpression visitTimeShiftAtomComponent(VtlParser.TimeShiftAtomComponentContext ctx) {
        expressionVisitor.setVariables(getVariables());
        VtlTimeUnaryWithOneParameterExpression vtlTimeUnaryWithOneParameterExpression = new VtlTimeUnaryWithOneParameterExpression();

        if (ctx.exprComponent() != null) {
            vtlTimeUnaryWithOneParameterExpression.setVtlExpression(expressionVisitor.visit(ctx.exprComponent()));
        }
        return getVtlTimeUnaryWithOneParameterExpression(vtlTimeUnaryWithOneParameterExpression, ctx.signedInteger(), ctx.getText());
    }

    @Override
    public VtlTimeUnaryWithOneParameterExpression visitTimeAggAtomComponent(VtlParser.TimeAggAtomComponentContext ctx) {
        VtlTimeUnaryWithOneParameterExpression vtlTimeUnaryWithOneParameterExpression = getTimeAgg(ctx.periodIndTo, ctx.periodIndFrom);

        if (ctx.optionalExprComponent() != null) {
            vtlTimeUnaryWithOneParameterExpression.setVtlExpression(expressionVisitor.visit(ctx.optionalExprComponent()));
        }
        return getVtlTimeUnaryWithOneParameterExpression(vtlTimeUnaryWithOneParameterExpression, ctx.FIRST(), ctx.LAST(), ctx.getText());
    }

    @Override
    public VtlCurrentDate visitCurrentDateAtomComponent(VtlParser.CurrentDateAtomComponentContext ctx) {
        VtlCurrentDate vtlCurrentDate = new VtlCurrentDate();
        vtlCurrentDate.setCommand(ctx.getText());
        vtlCurrentDate.setOperator(Operator.CURRENT_DATE);
        return vtlCurrentDate;
    }

    private VtlTimeUnaryWithOneParameterExpression getVtlTimeUnaryWithOneParameterExpression(VtlTimeUnaryWithOneParameterExpression vtlTimeUnaryWithOneParameterExpression, VtlParser.SignedIntegerContext signedIntegerContext, String text) {
        if (signedIntegerContext != null) {
            VtlInteger vtlConstant = new VtlInteger();
            String sign = signedIntegerContext.getText();
            vtlConstant.setValue(Integer.valueOf(signedIntegerContext.INTEGER_CONSTANT().getText()));
            VtlConstantExpression vtlConstantExpression = new VtlConstantExpression();
            vtlConstantExpression.setVtlConstant(vtlConstant);
            vtlTimeUnaryWithOneParameterExpression.setParameterExpression(vtlConstantExpression);
            vtlTimeUnaryWithOneParameterExpression.setParameterOperator(Operator.TIMESHIFT_PARAM);
        }
        vtlTimeUnaryWithOneParameterExpression.setCommand(text);
        vtlTimeUnaryWithOneParameterExpression.setOperator(Operator.TIMESHIFT);
        return vtlTimeUnaryWithOneParameterExpression;
    }

    private VtlTimeUnaryWithOneParameterExpression getTimeAgg(Token periodIndTo, Token periodIndFrom) {
        expressionVisitor.setVariables(getVariables());
        VtlTimeUnaryWithOneParameterExpression vtlTimeUnaryWithOneParameterExpression = new VtlTimeUnaryWithOneParameterExpression();

        VtlDuration vtlDuration = new VtlDuration();
        Character character = periodIndTo.getText().charAt(1);
        vtlDuration.setValue(character);
        VtlConstantExpression vtlConstantExpression = new VtlConstantExpression(vtlDuration);
        vtlTimeUnaryWithOneParameterExpression.setParameterExpression(vtlConstantExpression);
        vtlTimeUnaryWithOneParameterExpression.setParameterOperator(Operator.DURATION_PARAMETER);
        if (periodIndFrom != null && !periodIndFrom.getText().equalsIgnoreCase("_")) {
            VtlDuration vtlDurationOptional = new VtlDuration();
            Character characterOptional = periodIndFrom.getText().charAt(1);
            vtlDuration.setValue(characterOptional);
            VtlConstantExpression vtlConstantExpressionOptional = new VtlConstantExpression(vtlDurationOptional);
            vtlTimeUnaryWithOneParameterExpression.setOptionalParameterExpression(new ArrayList<>());
            vtlTimeUnaryWithOneParameterExpression.getOptionalParameterExpression().add(vtlConstantExpressionOptional);
            vtlTimeUnaryWithOneParameterExpression.setOptionalParameterOperator(Operator.DURATION_OPTIONAL_PARAMETER);
        }
        return vtlTimeUnaryWithOneParameterExpression;
    }

    private VtlTimeUnaryWithOneParameterExpression getVtlTimeUnaryWithOneParameterExpression(VtlTimeUnaryWithOneParameterExpression vtlTimeUnaryWithOneParameterExpression, TerminalNode first, TerminalNode last, String text) {
        Operator operator;
        if (first != null) {
            operator = Operator.TIME_AGG_FIRST;
        } else if (last != null) {
            operator = Operator.TIME_AGG_LAST;
        } else {
            operator = Operator.TIME_AGG;
        }
        vtlTimeUnaryWithOneParameterExpression.setCommand(text);
        vtlTimeUnaryWithOneParameterExpression.setOperator(operator);
        return vtlTimeUnaryWithOneParameterExpression;
    }
}
