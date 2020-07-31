package com.capgemini.istat.vtlcompiler.vtlinterpreter.visitor.function.clause.aggr;

import com.capgemini.istat.vtlcompiler.vtlcommon.model.operator.Operator;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.clause.aggr.VtlAggrComp;
import com.capgemini.istat.vtlcompiler.vtlinterpreter.visitor.InterpreterVisitor;
import com.capgemini.istat.vtlcompiler.vtlinterpreter.visitor.expression.ExpressionVisitor;
import com.capgemini.istat.vtlcompiler.vtlparser.VtlParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AggrFunctionVisitor extends InterpreterVisitor<VtlAggrComp> {
    private ExpressionVisitor expressionVisitor;

    @Autowired
    public void setExpressionVisitor(ExpressionVisitor expressionVisitor) {
        this.expressionVisitor = expressionVisitor;
    }

    @Override
    public VtlAggrComp visitAggrComp(VtlParser.AggrCompContext ctx) {
        VtlAggrComp vtlAggrComp = new VtlAggrComp();
        vtlAggrComp.setVtlExpression(
                expressionVisitor.visit(ctx.exprComponent())
        );
        vtlAggrComp.setOperator(Operator.valueOf(ctx.op.getText().toUpperCase()));
        vtlAggrComp.setCommand(ctx.getText());
        return vtlAggrComp;
    }

    @Override
    public VtlAggrComp visitCountAggrComp(VtlParser.CountAggrCompContext ctx) {
        VtlAggrComp vtlAggrComp = new VtlAggrComp();
        vtlAggrComp.setOperator(Operator.COUNT);
        vtlAggrComp.setCommand(ctx.getText());
        return vtlAggrComp;
    }


}
