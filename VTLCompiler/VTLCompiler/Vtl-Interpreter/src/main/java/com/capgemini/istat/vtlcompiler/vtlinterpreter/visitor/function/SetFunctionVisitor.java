package com.capgemini.istat.vtlcompiler.vtlinterpreter.visitor.function;

import com.capgemini.istat.vtlcompiler.vtlcommon.model.operator.Operator;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.VtlExpression;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.set.VtlSetBinaryExpression;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.set.VtlSetUnnaryExpression;
import com.capgemini.istat.vtlcompiler.vtlinterpreter.visitor.InterpreterVisitor;
import com.capgemini.istat.vtlcompiler.vtlinterpreter.visitor.expression.ExpressionVisitor;
import com.capgemini.istat.vtlcompiler.vtlparser.VtlParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SetFunctionVisitor extends InterpreterVisitor<VtlExpression> {
    private ExpressionVisitor expressionVisitor;

    @Autowired
    public void setExpressionVisitor(ExpressionVisitor expressionVisitor) {
        this.expressionVisitor = expressionVisitor;
    }

    @Override
    public VtlSetUnnaryExpression visitUnionAtom(VtlParser.UnionAtomContext ctx) {
        VtlSetUnnaryExpression vtlBinaryExpression = new VtlSetUnnaryExpression();
        for (VtlParser.ExprContext exprContext : ctx.expr()) {
            expressionVisitor.setVariables(getVariables());
            vtlBinaryExpression.getVtlExpressions().addLast(
                    expressionVisitor.visit(exprContext)
            );
        }
        vtlBinaryExpression.setCommand(ctx.getText());
        vtlBinaryExpression.setOperator(Operator.UNION);
        return vtlBinaryExpression;
    }

    @Override
    public VtlSetUnnaryExpression visitIntersectAtom(VtlParser.IntersectAtomContext ctx) {
        VtlSetUnnaryExpression vtlBinaryExpression = new VtlSetUnnaryExpression();
        for (VtlParser.ExprContext exprContext : ctx.expr()) {
            expressionVisitor.setVariables(getVariables());
            vtlBinaryExpression.getVtlExpressions().addLast(
                    expressionVisitor.visit(exprContext)
            );
        }
        vtlBinaryExpression.setCommand(ctx.getText());
        vtlBinaryExpression.setOperator(Operator.INTERSECT);
        return vtlBinaryExpression;
    }

    @Override
    public VtlSetBinaryExpression visitSetOrSYmDiffAtom(VtlParser.SetOrSYmDiffAtomContext ctx) {
        expressionVisitor.setVariables(getVariables());
        VtlSetBinaryExpression vtlBinaryExpression = new VtlSetBinaryExpression();
        vtlBinaryExpression.setLeftExpression(expressionVisitor.visit(ctx.left));
        vtlBinaryExpression.setRightExpression(expressionVisitor.visit(ctx.right));
        vtlBinaryExpression.setCommand(ctx.getText());
        vtlBinaryExpression.setOperator(Operator.valueOf(ctx.op.getText().toUpperCase()));
        return vtlBinaryExpression;
    }

}
