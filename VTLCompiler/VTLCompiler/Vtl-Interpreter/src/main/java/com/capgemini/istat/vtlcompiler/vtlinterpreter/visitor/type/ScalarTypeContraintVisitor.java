package com.capgemini.istat.vtlcompiler.vtlinterpreter.visitor.type;

import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.define.operator.datatype.VtlConstraint;
import com.capgemini.istat.vtlcompiler.vtlinterpreter.visitor.InterpreterVisitor;
import com.capgemini.istat.vtlcompiler.vtlinterpreter.visitor.expression.ExpressionVisitor;
import com.capgemini.istat.vtlcompiler.vtlinterpreter.visitor.terminal.ConstantVisitor;
import com.capgemini.istat.vtlcompiler.vtlparser.VtlParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ScalarTypeContraintVisitor extends InterpreterVisitor<VtlConstraint> {
    private ExpressionVisitor expressionVisitor;
    private ConstantVisitor constantVisitor;

    @Autowired
    public void setExpressionVisitor(ExpressionVisitor expressionVisitor) {
        this.expressionVisitor = expressionVisitor;
    }

    @Autowired
    public void setConstantVisitor(ConstantVisitor constantVisitor) {
        this.constantVisitor = constantVisitor;
    }

    @Override
    public VtlConstraint visitConditionConstraint(VtlParser.ConditionConstraintContext ctx) {
        VtlConstraint vtlConstraint = new VtlConstraint();
        vtlConstraint.setVtlCondition(
                expressionVisitor.visit(ctx.exprComponent())
        );
        return vtlConstraint;
    }

    @Override
    public VtlConstraint visitRangeConstraint(VtlParser.RangeConstraintContext ctx) {
        VtlConstraint vtlConstraint = new VtlConstraint();
        for (VtlParser.ConstantContext constantContext : ctx.constant()) {
            vtlConstraint.getVtlConstantList().addLast(
                    constantVisitor.visitConstant(constantContext)
            );
        }
        return vtlConstraint;
    }
}
