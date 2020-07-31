package com.capgemini.istat.vtlcompiler.vtlinterpreter.visitor.function;

import com.capgemini.istat.vtlcompiler.vtlcommon.model.operator.Operator;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.VtlExpression;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.string.VtlStringUnaryExpression;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.string.VtlStringUnaryWithOneParameterExpression;
import com.capgemini.istat.vtlcompiler.vtlinterpreter.visitor.InterpreterVisitor;
import com.capgemini.istat.vtlcompiler.vtlinterpreter.visitor.expression.ExpressionVisitor;
import com.capgemini.istat.vtlcompiler.vtlparser.VtlParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class StringFunctionVisitor extends InterpreterVisitor<VtlExpression> {
    private ExpressionVisitor expressionVisitor;

    @Autowired
    public void setExpressionVisitor(ExpressionVisitor expressionVisitor) {
        this.expressionVisitor = expressionVisitor;
    }

    @Override
    public VtlStringUnaryExpression visitUnaryStringFunction(VtlParser.UnaryStringFunctionContext ctx) {
        expressionVisitor.setVariables(getVariables());
        return getUnaryExpression(expressionVisitor.visit(ctx.expr()), ctx.op.getText().toUpperCase(), ctx.getText());
    }

    @Override
    public VtlStringUnaryExpression visitSubstrAtom(VtlParser.SubstrAtomContext ctx) {
        expressionVisitor.setVariables(getVariables());
        VtlStringUnaryExpression vtlUnaryExpression = new VtlStringUnaryExpression();
        vtlUnaryExpression.setOperator(Operator.SUBSTR);
        logger.debug("visitSubstrAtom -> ");
        vtlUnaryExpression.setVtlExpression(expressionVisitor.visit(ctx.expr()));
        if (ctx.optionalExpr() != null) {
            for (VtlParser.OptionalExprContext optionalExprContext : ctx.optionalExpr()) {
                vtlUnaryExpression.getOptionalExpression().add(expressionVisitor.visit(optionalExprContext));
            }
        }
        vtlUnaryExpression.setCommand(ctx.getText());
        vtlUnaryExpression.setOptionalOperator(Operator.SUBSTR_OPTIONAL_PARAMETER);
        return vtlUnaryExpression;
    }

    @Override
    public VtlStringUnaryExpression visitReplaceAtom(VtlParser.ReplaceAtomContext ctx) {
        expressionVisitor.setVariables(getVariables());
        VtlStringUnaryExpression vtlUnaryExpression = new VtlStringUnaryExpression();
        vtlUnaryExpression.setOperator(Operator.REPLACE);
        logger.debug("visitReplaceAtom -> ");
        vtlUnaryExpression.setVtlExpression(expressionVisitor.visit(ctx.expr().get(0)));
        vtlUnaryExpression.getOptionalExpression().add(expressionVisitor.visit(ctx.param));

        if (ctx.optionalExpr() != null) {
            vtlUnaryExpression.getOptionalExpression().add(expressionVisitor.visit(ctx.optionalExpr()));
        }
        vtlUnaryExpression.setCommand(ctx.getText());
        vtlUnaryExpression.setOptionalOperator(Operator.REPLACE_OPTIONAL_PARAMETER);
        return vtlUnaryExpression;
    }

    @Override
    public VtlStringUnaryWithOneParameterExpression visitInstrAtom(VtlParser.InstrAtomContext ctx) {
        expressionVisitor.setVariables(getVariables());
        VtlStringUnaryWithOneParameterExpression vtlUnaryWithOneParameter = new VtlStringUnaryWithOneParameterExpression();
        vtlUnaryWithOneParameter.setOperator(Operator.INSTR);
        if (ctx.INSTR() != null) {
            logger.debug("visitInstrAtom -> " + ctx.INSTR().getText());
        }
        if (ctx.expr() != null) {
            vtlUnaryWithOneParameter.setVtlExpression(expressionVisitor.visit(ctx.expr().get(0)));
            vtlUnaryWithOneParameter.setParameterExpression(expressionVisitor.visit(ctx.expr().get(1)));
            vtlUnaryWithOneParameter.setParameterOperator(Operator.INSTR_PARAMETER);
        }
        if (ctx.optionalExpr() != null) {
            vtlUnaryWithOneParameter.setOptionalParameterExpression(new ArrayList<>());
            for (VtlParser.OptionalExprContext optionalExprContext : ctx.optionalExpr()) {
                vtlUnaryWithOneParameter.getOptionalParameterExpression().add(expressionVisitor.visit(optionalExprContext));
            }
        }
        vtlUnaryWithOneParameter.setCommand(ctx.getText());
        vtlUnaryWithOneParameter.setOptionalParameterOperator(Operator.INSTR_OPTIONAL_PARAMETER);
        return vtlUnaryWithOneParameter;
    }

    /*
     * COMPONENTS
     */

    @Override
    public VtlStringUnaryExpression visitUnaryStringFunctionComponent(VtlParser.UnaryStringFunctionComponentContext ctx) {
        expressionVisitor.setVariables(getVariables());
        return getUnaryExpression(expressionVisitor.visit(ctx.exprComponent()), ctx.op.getText().toUpperCase(), ctx.getText());
    }

    @Override
    public VtlStringUnaryExpression visitSubstrAtomComponent(VtlParser.SubstrAtomComponentContext ctx) {
        expressionVisitor.setVariables(getVariables());
        VtlStringUnaryExpression vtlUnaryExpression = new VtlStringUnaryExpression();
        vtlUnaryExpression.setOperator(Operator.SUBSTR);
        logger.debug("visitSubstrAtom -> ");
        vtlUnaryExpression.setVtlExpression(expressionVisitor.visit(ctx.exprComponent()));
        if (ctx.optionalExprComponent() != null) {
            for (VtlParser.OptionalExprComponentContext optionalExprContext : ctx.optionalExprComponent()) {
                vtlUnaryExpression.getOptionalExpression().add(expressionVisitor.visit(optionalExprContext));
            }
        }
        vtlUnaryExpression.setCommand(ctx.getText());
        vtlUnaryExpression.setOptionalOperator(Operator.SUBSTR_OPTIONAL_PARAMETER);
        return vtlUnaryExpression;
    }

    @Override
    public VtlStringUnaryExpression visitReplaceAtomComponent(VtlParser.ReplaceAtomComponentContext ctx) {
        expressionVisitor.setVariables(getVariables());
        VtlStringUnaryExpression vtlUnaryExpression = new VtlStringUnaryExpression();
        vtlUnaryExpression.setOperator(Operator.REPLACE);
        logger.debug("visitReplaceAtomComponent -> ");
        vtlUnaryExpression.setVtlExpression(expressionVisitor.visit(ctx.exprComponent().get(0)));
        vtlUnaryExpression.getOptionalExpression().add(expressionVisitor.visit(ctx.param));

        if (ctx.optionalExprComponent() != null) {
            vtlUnaryExpression.getOptionalExpression().add(expressionVisitor.visit(ctx.optionalExprComponent()));
        }
        vtlUnaryExpression.setCommand(ctx.getText());
        vtlUnaryExpression.setOptionalOperator(Operator.REPLACE_OPTIONAL_PARAMETER);
        return vtlUnaryExpression;
    }

    @Override
    public VtlStringUnaryWithOneParameterExpression visitInstrAtomComponent(VtlParser.InstrAtomComponentContext ctx) {
        expressionVisitor.setVariables(getVariables());
        VtlStringUnaryWithOneParameterExpression vtlUnaryWithOneParameter = new VtlStringUnaryWithOneParameterExpression();
        vtlUnaryWithOneParameter.setOperator(Operator.INSTR);
        if (ctx.INSTR() != null) {
            logger.debug("visitInstrAtom -> " + ctx.INSTR().getText());
        }
        if (ctx.exprComponent() != null) {
            vtlUnaryWithOneParameter.setVtlExpression(expressionVisitor.visit(ctx.exprComponent().get(0)));
            vtlUnaryWithOneParameter.setParameterExpression(expressionVisitor.visit(ctx.exprComponent().get(1)));
            vtlUnaryWithOneParameter.setParameterOperator(Operator.INSTR_PARAMETER);
        }
        if (ctx.optionalExprComponent() != null) {
            vtlUnaryWithOneParameter.setOptionalParameterExpression(new ArrayList<>());
            for (VtlParser.OptionalExprComponentContext optionalExprComponentContext : ctx.optionalExprComponent()) {
                vtlUnaryWithOneParameter.getOptionalParameterExpression().add(expressionVisitor.visit(optionalExprComponentContext));
            }
        }
        vtlUnaryWithOneParameter.setCommand(ctx.getText());
        vtlUnaryWithOneParameter.setOptionalParameterOperator(Operator.INSTR_OPTIONAL_PARAMETER);
        return vtlUnaryWithOneParameter;
    }


    private VtlStringUnaryExpression getUnaryExpression(VtlExpression vtlExpression, String operator, String text) {
        logger.debug("visitUnaryStringFunction");
        VtlStringUnaryExpression vtlUnaryExpression = new VtlStringUnaryExpression();
        vtlUnaryExpression.setOperator(Operator.valueOf(operator));
        vtlUnaryExpression.setVtlExpression(vtlExpression);
        vtlUnaryExpression.setCommand(text);
        return vtlUnaryExpression;
    }

}
