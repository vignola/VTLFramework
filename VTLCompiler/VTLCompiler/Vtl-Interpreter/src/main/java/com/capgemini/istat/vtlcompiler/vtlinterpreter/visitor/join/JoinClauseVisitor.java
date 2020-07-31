package com.capgemini.istat.vtlcompiler.vtlinterpreter.visitor.join;

import com.capgemini.istat.vtlcompiler.vtlcommon.model.operator.Operator;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.VtlExpression;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.join.VtlJoinSelectClause;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.join.VtlJoinSelectClauseItem;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.join.VtlJoinUsing;
import com.capgemini.istat.vtlcompiler.vtlinterpreter.visitor.InterpreterVisitor;
import com.capgemini.istat.vtlcompiler.vtlinterpreter.visitor.component.ComponentIdVisitor;
import com.capgemini.istat.vtlcompiler.vtlinterpreter.visitor.expression.ExpressionVisitor;
import com.capgemini.istat.vtlcompiler.vtlparser.VtlParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class JoinClauseVisitor extends InterpreterVisitor<VtlExpression> {
    private ExpressionVisitor expressionVisitor;
    private ComponentIdVisitor componentIdVisitor;

    @Autowired
    public void setExpressionVisitor(ExpressionVisitor expressionVisitor) {
        this.expressionVisitor = expressionVisitor;
    }

    @Autowired
    public void setComponentIdVisitor(ComponentIdVisitor componentIdVisitor) {
        this.componentIdVisitor = componentIdVisitor;
    }

    @Override
    public VtlJoinSelectClause visitJoinClauseWithoutUsing(VtlParser.JoinClauseWithoutUsingContext ctx){
        logger.debug("visitJoinClauseWithoutUsing -> ");
        VtlJoinSelectClause vtlJoinSelectClause = new VtlJoinSelectClause();
        if (ctx.joinClauseItem() != null) {
            for (VtlParser.JoinClauseItemContext joinClauseItemContext : ctx.joinClauseItem()) {
                vtlJoinSelectClause.getVtlJoinSelectClauseItems().add(visitJoinClauseItem(joinClauseItemContext));
            }
        }
        vtlJoinSelectClause.setOperator(Operator.JOIN_CLAUSE);
        vtlJoinSelectClause.setCommand(ctx.getText());
        return vtlJoinSelectClause;
    }

    @Override
    public VtlJoinSelectClause visitJoinClause(VtlParser.JoinClauseContext ctx) {
        logger.debug("visitJoinClause -> ");
        VtlJoinSelectClause vtlJoinSelectClause = new VtlJoinSelectClause();
        if (ctx.joinClauseItem() != null) {
            for (VtlParser.JoinClauseItemContext joinClauseItemContext : ctx.joinClauseItem()) {
                vtlJoinSelectClause.getVtlJoinSelectClauseItems().add(visitJoinClauseItem(joinClauseItemContext));
            }
        }
        if (ctx.USING() != null) {
            VtlJoinUsing vtlJoinUsing = new VtlJoinUsing();
            if (ctx.componentID() != null) {
                for (VtlParser.ComponentIDContext componentIDContext : ctx.componentID()) {
                    vtlJoinUsing.getComponentIds().add(componentIdVisitor.visitComponentID(componentIDContext));
                }
            }
            vtlJoinUsing.setOperator(Operator.JOIN_CLAUSE_USING);
            vtlJoinUsing.setCommand(ctx.USING().getText());
            vtlJoinSelectClause.setVtlJoinUsing(vtlJoinUsing);
        }
        vtlJoinSelectClause.setOperator(Operator.JOIN_CLAUSE);
        vtlJoinSelectClause.setCommand(ctx.getText());
        return vtlJoinSelectClause;
    }

    @Override
    public VtlJoinSelectClauseItem visitJoinClauseItem(VtlParser.JoinClauseItemContext ctx) {
        VtlJoinSelectClauseItem vtlJoinSelectClauseItem = new VtlJoinSelectClauseItem();
        if (ctx.AS() != null && ctx.alias() != null) {
                vtlJoinSelectClauseItem.setAsName(ctx.alias().getText());
        }
        if (ctx.expr() != null) {
            expressionVisitor.setVariables(getVariables());
            vtlJoinSelectClauseItem.setVtlExpression(expressionVisitor.visit(ctx.expr()));
        }
        vtlJoinSelectClauseItem.setOperator(Operator.JOIN_CLAUSE_ITEM);
        vtlJoinSelectClauseItem.setCommand(ctx.getText());
        return vtlJoinSelectClauseItem;
    }

}
