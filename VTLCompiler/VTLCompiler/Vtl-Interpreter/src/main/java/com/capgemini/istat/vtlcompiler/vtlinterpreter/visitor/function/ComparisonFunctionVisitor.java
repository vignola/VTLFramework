package com.capgemini.istat.vtlcompiler.vtlinterpreter.visitor.function;

import com.capgemini.istat.vtlcompiler.vtlcommon.model.operator.Operator;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.VtlExpression;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.comparison.VtlBetweenExpression;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.comparison.VtlComparisonUnaryWithOneParameterExpression;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.comparison.VtlExistIn;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.numeric.VtlNumericUnaryExpression;
import com.capgemini.istat.vtlcompiler.vtlinterpreter.visitor.InterpreterVisitor;
import com.capgemini.istat.vtlcompiler.vtlinterpreter.visitor.expression.ExpressionVisitor;
import com.capgemini.istat.vtlcompiler.vtlparser.VtlParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ComparisonFunctionVisitor extends InterpreterVisitor<VtlExpression> {
    private ExpressionVisitor expressionVisitor;

    @Autowired
    public void setExpressionVisitor(ExpressionVisitor expressionVisitor) {
        this.expressionVisitor = expressionVisitor;
    }

    @Override
    public VtlBetweenExpression visitBetweenAtom(VtlParser.BetweenAtomContext ctx) {
        expressionVisitor.setVariables(getVariables());
        VtlBetweenExpression vtlBetweenExpression = new VtlBetweenExpression();
        if (ctx.expr() != null && ctx.expr().size() > 2) {
            vtlBetweenExpression.setVtlElement(expressionVisitor.visit(ctx.op));
            vtlBetweenExpression.setVtlfrom(expressionVisitor.visit(ctx.from_));
            vtlBetweenExpression.setVtlTo(expressionVisitor.visit(ctx.to_));
        }
        vtlBetweenExpression.setOperator(Operator.BETWEEN);
        vtlBetweenExpression.setCommand(ctx.getText());
        return vtlBetweenExpression;
    }

    @Override
    public VtlComparisonUnaryWithOneParameterExpression visitCharsetMatchAtom(VtlParser.CharsetMatchAtomContext ctx) {
        expressionVisitor.setVariables(getVariables());
        VtlComparisonUnaryWithOneParameterExpression vtlUnaryWithOneParameter = new VtlComparisonUnaryWithOneParameterExpression();

        if (ctx.expr() != null && ctx.op != null && ctx.pattern != null) {
            vtlUnaryWithOneParameter.setVtlExpression(expressionVisitor.visit(ctx.op));
            vtlUnaryWithOneParameter.setParameterExpression(expressionVisitor.visit(ctx.pattern));
            vtlUnaryWithOneParameter.setParameterOperator(Operator.MATCH_CHARACTERS_PARAM);
        }
        vtlUnaryWithOneParameter.setOperator(Operator.MATCH_CHARACTERS);
        vtlUnaryWithOneParameter.setCommand(ctx.getText());
        return vtlUnaryWithOneParameter;
    }

    @Override
    public VtlNumericUnaryExpression visitIsNullAtom(VtlParser.IsNullAtomContext ctx) {
        expressionVisitor.setVariables(getVariables());
        VtlNumericUnaryExpression vtlUnaryExpression = new VtlNumericUnaryExpression();
        logger.debug("visitIsNullAtom -> ");
        if (ctx.ISNULL() != null) {
            vtlUnaryExpression.setOperator(Operator.IS_NULL);
        }
        if (ctx.expr() != null)
            vtlUnaryExpression.setVtlExpression(expressionVisitor.visit(ctx.expr()));
        return vtlUnaryExpression;
    }

    @Override
    public VtlExistIn visitExistInAtom(VtlParser.ExistInAtomContext ctx) {
        expressionVisitor.setVariables(getVariables());
        VtlExistIn vtlExistIn = new VtlExistIn();
        vtlExistIn.setLeftExpression(expressionVisitor.visit(ctx.left));
        vtlExistIn.setRightExpression(expressionVisitor.visit(ctx.right));
        vtlExistIn.setOperator(Operator.EXIST_IN);
        if (ctx.retainType() != null) {
            if (ctx.retainType().ALL() != null) {
                vtlExistIn.setRetain(ctx.retainType().ALL().getText());
            }
            if (ctx.retainType().BOOLEAN_CONSTANT() != null) {
                vtlExistIn.setRetain(ctx.retainType().BOOLEAN_CONSTANT().getText());
            }
        }
        vtlExistIn.setCommand(ctx.getText());
        return vtlExistIn;
    }

    @Override
    public VtlBetweenExpression visitBetweenAtomComponent(VtlParser.BetweenAtomComponentContext ctx) {
        expressionVisitor.setVariables(getVariables());
        VtlBetweenExpression vtlBetweenExpression = new VtlBetweenExpression();
        if (ctx.exprComponent() != null && ctx.exprComponent().size() > 2) {
            vtlBetweenExpression.setVtlElement(expressionVisitor.visit(ctx.op));
            vtlBetweenExpression.setVtlfrom(expressionVisitor.visit(ctx.from_));
            vtlBetweenExpression.setVtlTo(expressionVisitor.visit(ctx.to_));
        }
        vtlBetweenExpression.setOperator(Operator.BETWEEN);
        vtlBetweenExpression.setCommand(ctx.getText());
        return vtlBetweenExpression;
    }

    @Override
    public VtlComparisonUnaryWithOneParameterExpression visitCharsetMatchAtomComponent(VtlParser.CharsetMatchAtomComponentContext ctx) {
        expressionVisitor.setVariables(getVariables());
        VtlComparisonUnaryWithOneParameterExpression vtlUnaryWithOneParameter = new VtlComparisonUnaryWithOneParameterExpression();

        if (ctx.exprComponent() != null && ctx.op != null && ctx.pattern != null) {
            vtlUnaryWithOneParameter.setVtlExpression(expressionVisitor.visit(ctx.op));
            vtlUnaryWithOneParameter.setParameterExpression(expressionVisitor.visit(ctx.pattern));
            vtlUnaryWithOneParameter.setParameterOperator(Operator.MATCH_CHARACTERS_PARAM);
        }
        vtlUnaryWithOneParameter.setOperator(Operator.MATCH_CHARACTERS);
        vtlUnaryWithOneParameter.setCommand(ctx.getText());
        return vtlUnaryWithOneParameter;
    }

    @Override
    public VtlNumericUnaryExpression visitIsNullAtomComponent(VtlParser.IsNullAtomComponentContext ctx) {
        expressionVisitor.setVariables(getVariables());
        VtlNumericUnaryExpression vtlUnaryExpression = new VtlNumericUnaryExpression();
        logger.debug("visitIsNullAtom -> ");
        if (ctx.ISNULL() != null) {
            vtlUnaryExpression.setOperator(Operator.IS_NULL);
        }
        if (ctx.exprComponent() != null)
            vtlUnaryExpression.setVtlExpression(expressionVisitor.visit(ctx.exprComponent()));
        return vtlUnaryExpression;
    }


}
