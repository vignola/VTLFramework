package com.capgemini.istat.vtlcompiler.vtlinterpreter.visitor.clause.sub;

import com.capgemini.istat.vtlcompiler.vtlcommon.model.operator.Operator;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.constant.VtlConstantExpression;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.VtlExpression;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.clause.sub.VtlSubSpaceExpression;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.clause.sub.VtlSubSpaceItemExpression;
import com.capgemini.istat.vtlcompiler.vtlinterpreter.visitor.InterpreterVisitor;
import com.capgemini.istat.vtlcompiler.vtlinterpreter.visitor.component.ComponentIdVisitor;
import com.capgemini.istat.vtlcompiler.vtlinterpreter.visitor.terminal.ConstantVisitor;
import com.capgemini.istat.vtlcompiler.vtlparser.VtlParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SubspaceClauseVisitor extends InterpreterVisitor<VtlExpression> {
    private ComponentIdVisitor componentIdVisitor;
    private ConstantVisitor constantVisitor;

    @Autowired
    public void setComponentIdVisitor(ComponentIdVisitor componentIdVisitor) {
        this.componentIdVisitor = componentIdVisitor;
    }

    @Autowired
    public void setConstantVisitor(ConstantVisitor constantVisitor) {
        this.constantVisitor = constantVisitor;
    }

    @Override
    public VtlSubSpaceExpression visitSubspaceClause(VtlParser.SubspaceClauseContext ctx) {
        VtlSubSpaceExpression vtlSubSpaceExpression = new VtlSubSpaceExpression();
        if (ctx.subspaceClauseItem() != null) {
            for (VtlParser.SubspaceClauseItemContext subspaceExprItemContext : ctx.subspaceClauseItem()) {
                vtlSubSpaceExpression.getVtlSubSpaceItemExpressionList().add(
                        visitSubspaceClauseItem(subspaceExprItemContext)
                );
            }
        }
        vtlSubSpaceExpression.setCommand(ctx.getText());
        vtlSubSpaceExpression.setOperator(Operator.SUB);
        return vtlSubSpaceExpression;
    }

    @Override
    public VtlSubSpaceItemExpression visitSubspaceClauseItem(VtlParser.SubspaceClauseItemContext ctx) {
        VtlSubSpaceItemExpression vtlSubSpaceItemExpression = new VtlSubSpaceItemExpression();
        if (ctx.componentID() != null) {
            vtlSubSpaceItemExpression.setVtlComponentId(componentIdVisitor.visitComponentID(ctx.componentID()));
        }
        if (ctx.constant() != null) {
            vtlSubSpaceItemExpression
                    .setVtlConstantExpression(
                            new VtlConstantExpression(
                                    constantVisitor.visitConstant(ctx.constant())
                            )
                    );
        }
        vtlSubSpaceItemExpression.setCommand(ctx.getText());
        return vtlSubSpaceItemExpression;
    }
}
