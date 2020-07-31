package com.capgemini.istat.vtlcompiler.vtlinterpreter.visitor.clause.unpivot;

import com.capgemini.istat.vtlcompiler.vtlcommon.model.operator.Operator;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.constant.VtlConstantExpression;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.VtlExpression;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.clause.pivot.VtlPivotClauseExpression;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.clause.unpivot.VtlPivotOrUnpivotClauseExpression;
import com.capgemini.istat.vtlcompiler.vtlinterpreter.visitor.InterpreterVisitor;
import com.capgemini.istat.vtlcompiler.vtlinterpreter.visitor.component.ComponentIdVisitor;
import com.capgemini.istat.vtlcompiler.vtlinterpreter.visitor.terminal.ConstantVisitor;
import com.capgemini.istat.vtlcompiler.vtlparser.VtlParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PivotOrUnpivotClauseVisitor extends InterpreterVisitor<VtlExpression> {
    private ComponentIdVisitor componentIdVisitor;
    private ConstantVisitor constantVisitor;


    @Autowired
    public void setConstantVisitor(ConstantVisitor constantVisitor) {
        this.constantVisitor = constantVisitor;
    }

    @Autowired
    public void setComponentIdVisitor(ComponentIdVisitor componentIdVisitor) {
        this.componentIdVisitor = componentIdVisitor;
    }

    @Override
    public VtlPivotOrUnpivotClauseExpression visitPivotOrUnpivotClause(VtlParser.PivotOrUnpivotClauseContext ctx) {
        VtlPivotOrUnpivotClauseExpression vtlUnpivotClauseExpression = new VtlPivotOrUnpivotClauseExpression();
        vtlUnpivotClauseExpression.setIdentifier(componentIdVisitor.visitComponentID(ctx.id_));
        vtlUnpivotClauseExpression.setMeasure(componentIdVisitor.visitComponentID(ctx.mea));
        vtlUnpivotClauseExpression.setCommand(ctx.getText());
        vtlUnpivotClauseExpression.setOperator(Operator.valueOf(ctx.op.getText().toUpperCase()));
        return vtlUnpivotClauseExpression;
    }

    @Override
    public VtlPivotClauseExpression visitCustomPivotClause(VtlParser.CustomPivotClauseContext ctx) {
        VtlPivotClauseExpression vtlPivotClauseExpression = new VtlPivotClauseExpression();
        componentIdVisitor.setVariables(getVariables());
        vtlPivotClauseExpression.setIdentifier(componentIdVisitor.visitComponentID(ctx.id_));
        vtlPivotClauseExpression.setMeasure(componentIdVisitor.visitComponentID(ctx.mea));
        vtlPivotClauseExpression.setOperator(Operator.PIVOT);
        vtlPivotClauseExpression.setCommand(ctx.getText());
        for (VtlParser.ConstantContext constantContext : ctx.constant()) {
            vtlPivotClauseExpression.getConstantExpressions().add(
                    new VtlConstantExpression(
                            constantVisitor.visitConstant(constantContext)
                    )
            );

        }
        return vtlPivotClauseExpression;
    }
}
