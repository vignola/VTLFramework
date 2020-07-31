package com.capgemini.istat.vtlcompiler.vtlinterpreter.visitor.clause.rename;

import com.capgemini.istat.vtlcompiler.vtlcommon.model.operator.Operator;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.VtlExpression;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.clause.rename.VtlRenameClauseExpression;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.clause.rename.VtlRenameClauseItemExpression;
import com.capgemini.istat.vtlcompiler.vtlinterpreter.visitor.InterpreterVisitor;
import com.capgemini.istat.vtlcompiler.vtlinterpreter.visitor.component.ComponentIdVisitor;
import com.capgemini.istat.vtlcompiler.vtlparser.VtlParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RenameClauseVisitor extends InterpreterVisitor<VtlExpression> {
    private ComponentIdVisitor componentIdVisitor;

    @Autowired
    public void setComponentClauseVisitor(ComponentIdVisitor componentIdVisitor) {
        this.componentIdVisitor = componentIdVisitor;
    }

    @Override
    public VtlRenameClauseExpression visitRenameClause(VtlParser.RenameClauseContext ctx) {
        logger.debug("visitRenameClause -> ");
        VtlRenameClauseExpression vtlRenameClauseExpression = new VtlRenameClauseExpression();
        if (ctx.renameClauseItem() != null) {
            for (VtlParser.RenameClauseItemContext renameClauseItemContext : ctx.renameClauseItem()) {
                vtlRenameClauseExpression.getVtlRenameClauseItemExpressionList().add(visitRenameClauseItem(renameClauseItemContext));
            }
        }
        vtlRenameClauseExpression.setCommand(ctx.getText());
        vtlRenameClauseExpression.setOperator(Operator.RENAME);
        return vtlRenameClauseExpression;
    }

    @Override
    public VtlRenameClauseItemExpression visitRenameClauseItem(VtlParser.RenameClauseItemContext ctx) {
        VtlRenameClauseItemExpression vtlRenameClauseItemExpression = new VtlRenameClauseItemExpression();
        if (ctx.fromName != null) {
            vtlRenameClauseItemExpression.setVtlComponentNameFrom(componentIdVisitor.visitComponentID(ctx.fromName));
        }
        if (ctx.toName != null) {
            vtlRenameClauseItemExpression.setVtlComponentNameTo(componentIdVisitor.visitComponentID(ctx.toName));
        }

        vtlRenameClauseItemExpression.setCommand(ctx.getText());
        return vtlRenameClauseItemExpression;
    }

}
