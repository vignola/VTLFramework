package com.capgemini.istat.vtlcompiler.vtlinterpreter.visitor.function;

import com.capgemini.istat.vtlcompiler.vtlcommon.model.operator.Operator;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.VtlExpression;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.conditional.VtlConditionalBinaryExpression;
import com.capgemini.istat.vtlcompiler.vtlinterpreter.visitor.InterpreterVisitor;
import com.capgemini.istat.vtlcompiler.vtlinterpreter.visitor.expression.ExpressionVisitor;
import com.capgemini.istat.vtlcompiler.vtlparser.VtlParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ConditionalFunctionVisitor extends InterpreterVisitor<VtlExpression> {

    private ExpressionVisitor expressionVisitor;

    @Autowired
    public void setExpressionVisitor(ExpressionVisitor expressionVisitor) {
        this.expressionVisitor = expressionVisitor;
    }

    @Override
    public VtlConditionalBinaryExpression visitNvlAtom(VtlParser.NvlAtomContext ctx) {
        expressionVisitor.setVariables(getVariables());
        VtlConditionalBinaryExpression vtlConditionalBinaryExpression = new VtlConditionalBinaryExpression();
        vtlConditionalBinaryExpression.setLeftExpression(expressionVisitor.visit(ctx.left));
        vtlConditionalBinaryExpression.setRightExpression(expressionVisitor.visit(ctx.right));
        vtlConditionalBinaryExpression.setOperator(Operator.NVL);
        vtlConditionalBinaryExpression.setCommand(ctx.getText());
        return vtlConditionalBinaryExpression;
    }
}
