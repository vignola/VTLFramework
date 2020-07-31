package com.capgemini.istat.vtlcompiler.vtlinterpreter.visitor.clause.calc;

import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.clause.calc.VtlCalcClauseItemExpression;
import com.capgemini.istat.vtlcompiler.vtlinterpreter.visitor.InterpreterVisitor;
import com.capgemini.istat.vtlcompiler.vtlinterpreter.visitor.component.ComponentIdVisitor;
import com.capgemini.istat.vtlcompiler.vtlinterpreter.visitor.component.ComponentRoleVisitor;
import com.capgemini.istat.vtlcompiler.vtlinterpreter.visitor.expression.ExpressionVisitor;
import com.capgemini.istat.vtlcompiler.vtlparser.VtlParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CalcClauseItemVisitor extends InterpreterVisitor<VtlCalcClauseItemExpression> {
    private ComponentIdVisitor componentIdVisitor;
    private ComponentRoleVisitor componentRoleVisitor;
    private ExpressionVisitor expressionVisitor;

    @Autowired
    public void setComponentIdVisitor(ComponentIdVisitor componentIdVisitor) {
        this.componentIdVisitor = componentIdVisitor;
    }

    @Autowired
    public void setComponentRoleVisitor(ComponentRoleVisitor componentRoleVisitor) {
        this.componentRoleVisitor = componentRoleVisitor;
    }

    @Autowired
    public void setExpressionVisitor(ExpressionVisitor expressionVisitor) {
        this.expressionVisitor = expressionVisitor;
    }

    @Override
    public VtlCalcClauseItemExpression visitCalcClauseItem(VtlParser.CalcClauseItemContext ctx) {
        logger.debug("visitCalcClauseItem -> ");
        VtlCalcClauseItemExpression vtlCalcClauseItemExpression = new VtlCalcClauseItemExpression();
        if (ctx.componentRole() != null) {
            vtlCalcClauseItemExpression.setVtlComponentRole(componentRoleVisitor.visitComponentRole(ctx.componentRole()));
        }
        if (ctx.componentID() != null) {
            vtlCalcClauseItemExpression.setVtlComponentId(componentIdVisitor.visitComponentID(ctx.componentID()));
        }
        if (ctx.exprComponent() != null) {
            expressionVisitor.setVariables(getVariables());
            vtlCalcClauseItemExpression.setVtlExpression(expressionVisitor.visit(ctx.exprComponent()));
        }
        vtlCalcClauseItemExpression.setCommand(ctx.getText());
        return vtlCalcClauseItemExpression;
    }

}
