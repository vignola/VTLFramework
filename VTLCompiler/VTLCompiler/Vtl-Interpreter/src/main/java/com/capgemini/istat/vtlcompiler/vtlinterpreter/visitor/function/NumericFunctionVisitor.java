package com.capgemini.istat.vtlcompiler.vtlinterpreter.visitor.function;

import com.capgemini.istat.vtlcompiler.vtlcommon.model.operator.Operator;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.VtlExpression;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.numeric.VtlNumericBinaryExpression;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.numeric.VtlNumericUnaryExpression;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.numeric.VtlNumericUnaryWithOneParameterExpression;
import com.capgemini.istat.vtlcompiler.vtlinterpreter.visitor.InterpreterVisitor;
import com.capgemini.istat.vtlcompiler.vtlinterpreter.visitor.expression.ExpressionVisitor;
import com.capgemini.istat.vtlcompiler.vtlparser.VtlParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class NumericFunctionVisitor extends InterpreterVisitor<VtlExpression> {
    private ExpressionVisitor expressionVisitor;

    @Autowired
    public void setExpressionVisitor(ExpressionVisitor expressionVisitor) {
        this.expressionVisitor = expressionVisitor;
    }

    @Override
    public VtlNumericUnaryExpression visitUnaryNumeric(VtlParser.UnaryNumericContext ctx) {
        expressionVisitor.setVariables(getVariables());
        VtlNumericUnaryExpression vtlUnaryExpression = getUnaryNumeric(expressionVisitor.visit(ctx.expr()), ctx.op.getText().toUpperCase(), ctx.getText());
        return vtlUnaryExpression;
    }

    @Override
    public VtlNumericUnaryExpression visitUnaryWithOptionalNumericComponent(VtlParser.UnaryWithOptionalNumericComponentContext ctx) {
        expressionVisitor.setVariables(getVariables());
        VtlNumericUnaryExpression vtlUnaryExpression = getUnaryNumericOptional(
                expressionVisitor.visit(ctx.exprComponent()),
                ctx.op.getText().toUpperCase(),
                ctx.getText(),
                ctx.optionalExprComponent() != null,
                ctx.optionalExprComponent() != null ? expressionVisitor.visit(ctx.optionalExprComponent()) : null
        );
        return vtlUnaryExpression;
    }

    @Override
    public VtlExpression visitBinaryNumeric(VtlParser.BinaryNumericContext ctx) {
        if (ctx.op.getText().equalsIgnoreCase("log")) {
            VtlNumericUnaryWithOneParameterExpression vtlUnaryWithOneParameter = new VtlNumericUnaryWithOneParameterExpression();
            logger.debug("visitLogAtom -> ");

            if (ctx.LOG() != null)
                logger.debug("visitLogAtom -> " + ctx.LOG().getText());
            if (ctx.expr() != null && ctx.expr().get(0) != null && ctx.expr().get(1) != null) {
                vtlUnaryWithOneParameter.setVtlExpression(expressionVisitor.visit(ctx.expr().get(0)));
                vtlUnaryWithOneParameter.setParameterExpression(expressionVisitor.visit(ctx.expr().get(1)));
                vtlUnaryWithOneParameter.setParameterOperator(Operator.LOG_PARAM);
            }

            vtlUnaryWithOneParameter.setOperator(Operator.LOG);
            vtlUnaryWithOneParameter.setCommand(ctx.getText());
            return vtlUnaryWithOneParameter;
        }
        return getNumericBinaryExpression(expressionVisitor.visit(ctx.left), expressionVisitor.visit(ctx.right), ctx.op.getText().toUpperCase(), ctx.getText());
    }


    /*
     COMPONENTS
     */

    @Override
    public VtlNumericUnaryExpression visitUnaryNumericComponent(VtlParser.UnaryNumericComponentContext ctx) {
        expressionVisitor.setVariables(getVariables());
        VtlNumericUnaryExpression vtlUnaryExpression = getUnaryNumeric(
                expressionVisitor.visit(ctx.exprComponent()),
                ctx.op.getText().toUpperCase(),
                ctx.getText());
        return vtlUnaryExpression;
    }

    @Override
    public VtlNumericUnaryExpression visitUnaryWithOptionalNumeric(VtlParser.UnaryWithOptionalNumericContext ctx) {
        expressionVisitor.setVariables(getVariables());
        VtlNumericUnaryExpression vtlUnaryExpression = getUnaryNumericOptional(
                expressionVisitor.visit(ctx.expr()),
                ctx.op.getText().toUpperCase(),
                ctx.getText(),
                ctx.optionalExpr() != null,
                expressionVisitor.visit(ctx.optionalExpr())
        );
        return vtlUnaryExpression;
    }

    @Override
    public VtlExpression visitBinaryNumericComponent(VtlParser.BinaryNumericComponentContext ctx) {
        if (ctx.op.getText().equalsIgnoreCase("log")) {
            VtlNumericUnaryWithOneParameterExpression vtlUnaryWithOneParameter = new VtlNumericUnaryWithOneParameterExpression();
            logger.debug("visitLogAtom -> ");

            if (ctx.LOG() != null)
                logger.debug("visitLogAtom -> " + ctx.LOG().getText());
            if (ctx.exprComponent() != null && ctx.exprComponent().get(0) != null && ctx.exprComponent().get(1) != null) {
                vtlUnaryWithOneParameter.setVtlExpression(expressionVisitor.visit(ctx.exprComponent().get(0)));
                vtlUnaryWithOneParameter.setParameterExpression(expressionVisitor.visit(ctx.exprComponent().get(1)));
                vtlUnaryWithOneParameter.setParameterOperator(Operator.LOG_PARAM);
            }

            vtlUnaryWithOneParameter.setOperator(Operator.LOG);
            vtlUnaryWithOneParameter.setCommand(ctx.getText());
            return vtlUnaryWithOneParameter;
        }
        return getNumericBinaryExpression(expressionVisitor.visit(ctx.left), expressionVisitor.visit(ctx.right), ctx.op.getText().toUpperCase(), ctx.getText());
    }



    private VtlNumericUnaryExpression getUnaryNumericOptional(VtlExpression vtlExpression, String operator,
                                                              String text, boolean optionalCondition,
                                                              VtlExpression optionalExpression) {
        VtlNumericUnaryExpression vtlUnaryExpression = getUnaryNumeric(vtlExpression, operator, text);
        if (optionalCondition) {
            vtlUnaryExpression.getOptionalExpression().add(optionalExpression);
            vtlUnaryExpression.setOptionalOperator(Operator.ROUND_OPTIONAL_PARAMETER);
        }
        return vtlUnaryExpression;
    }

    private VtlNumericBinaryExpression getNumericBinaryExpression(VtlExpression visit, VtlExpression visit2, String op, String text) {
        expressionVisitor.setVariables(getVariables());
        VtlNumericBinaryExpression vtlExpression = new VtlNumericBinaryExpression();
        logger.debug("visitBinaryNumeric -> ");
        vtlExpression.setLeftExpression(visit);
        vtlExpression.setRightExpression(visit2);
        vtlExpression.setCommand(text);
        vtlExpression.setOperator(Operator.valueOf(op));
        return vtlExpression;
    }

    private VtlNumericUnaryExpression getUnaryNumeric(VtlExpression vtlExpression, String operator, String text) {
        VtlNumericUnaryExpression vtlUnaryExpression = new VtlNumericUnaryExpression();
        logger.debug("visitUnaryNumeric -> ");
        vtlUnaryExpression.setOperator(Operator.valueOf(operator));
        vtlUnaryExpression.setVtlExpression(vtlExpression);
        vtlUnaryExpression.setCommand(text);
        return vtlUnaryExpression;
    }

}
