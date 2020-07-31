package com.capgemini.istat.vtlcompiler.vtlinterpreter.visitor.clause.keepdrop;

import com.capgemini.istat.vtlcompiler.vtlcommon.model.operator.Operator;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.clause.drop.VtlKeepOrDropClauseExpression;
import com.capgemini.istat.vtlcompiler.vtlinterpreter.visitor.InterpreterVisitor;
import com.capgemini.istat.vtlcompiler.vtlinterpreter.visitor.component.ComponentIdVisitor;
import com.capgemini.istat.vtlcompiler.vtlparser.VtlParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class KeepOrDropClauseVisitor extends InterpreterVisitor<VtlKeepOrDropClauseExpression> {
    private ComponentIdVisitor componentIdVisitor;

    @Autowired
    public void setComponentClauseVisitor(ComponentIdVisitor componentIdVisitor) {
        this.componentIdVisitor = componentIdVisitor;
    }
    @Override
    public VtlKeepOrDropClauseExpression visitKeepOrDropClause(VtlParser.KeepOrDropClauseContext ctx) {
        logger.debug("visitKeepOrDropClause  -> ");
        VtlKeepOrDropClauseExpression vtlKeepOrDropClauseExpression = new VtlKeepOrDropClauseExpression();


        if (ctx.componentID() != null) {
            for (VtlParser.ComponentIDContext componentClauseContext : ctx.componentID()) {
                vtlKeepOrDropClauseExpression.getVtlComponentIds().add(componentIdVisitor.visitComponentID(componentClauseContext));
            }
        }
        vtlKeepOrDropClauseExpression.setCommand(ctx.getText());
        vtlKeepOrDropClauseExpression.setOperator(Operator.valueOf(ctx.op.getText().toUpperCase()));
        return vtlKeepOrDropClauseExpression;
    }
}
