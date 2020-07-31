package com.capgemini.istat.vtlcompiler.vtlinterpreter.visitor.function.clause.analytic;

import com.capgemini.istat.vtlcompiler.vtlcommon.model.operator.Operator;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.clause.analytic.*;
import com.capgemini.istat.vtlcompiler.vtlinterpreter.visitor.InterpreterVisitor;
import com.capgemini.istat.vtlcompiler.vtlinterpreter.visitor.component.ComponentIdVisitor;
import com.capgemini.istat.vtlcompiler.vtlparser.VtlParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AnalyticClauseVisitor extends InterpreterVisitor {
    private ComponentIdVisitor componentIdVisitor;

    @Autowired
    public void setComponentIdVisitor(ComponentIdVisitor componentIdVisitor) {
        this.componentIdVisitor = componentIdVisitor;
    }

    @Override
    public VtlPartitionBy visitPartitionByClause(VtlParser.PartitionByClauseContext ctx) {
        VtlPartitionBy vtlPartitionBy = new VtlPartitionBy();
        if (ctx.componentID() != null && !ctx.componentID().isEmpty()) {
            for (VtlParser.ComponentIDContext componentIDContext : ctx.componentID())
                vtlPartitionBy.getVtlComponentIds().add(
                        componentIdVisitor.visitComponentID(componentIDContext)
                );
        }
        vtlPartitionBy.setCommand(ctx.getText());
        vtlPartitionBy.setOperator(Operator.PARTITION_BY);
        return vtlPartitionBy;
    }

    @Override
    public VtlOrderBy visitOrderByClause(VtlParser.OrderByClauseContext ctx) {
        VtlOrderBy vtlOrderBy = new VtlOrderBy();
        if (ctx.orderByItem() != null) {
            for (VtlParser.OrderByItemContext orderByItemContext : ctx.orderByItem()) {
                vtlOrderBy.getVtlOrderByItems().add(
                        visitOrderByItem(orderByItemContext)
                );
            }
        }
        vtlOrderBy.setCommand(ctx.getText());
        vtlOrderBy.setOperator(Operator.ORDER_BY);
        return vtlOrderBy;
    }

    @Override
    public VtlOrderByItem visitOrderByItem(VtlParser.OrderByItemContext ctx) {
        VtlOrderByItem vtlOrderByItem = new VtlOrderByItem();
        if (ctx.ASC() != null)
            vtlOrderByItem.setOrderDirection(ctx.ASC().getText());
        else if (ctx.DESC() != null)
            vtlOrderByItem.setOrderDirection(ctx.DESC().getText());
        if (ctx.componentID() != null) {
            vtlOrderByItem.setVtlComponentId(
                    componentIdVisitor.visitComponentID(ctx.componentID())
            );
        }
        vtlOrderByItem.setCommand(ctx.getText());
        return vtlOrderByItem;

    }

    @Override
    public VtlWindowing visitWindowingClause(VtlParser.WindowingClauseContext ctx) {
        VtlWindowing vtlWindowing = new VtlWindowing();
        if (ctx.DATA() != null) {
            vtlWindowing.setVtlWindowingType(VtlWindowingType.DATA_POINTS);
        } else {
            vtlWindowing.setVtlWindowingType(VtlWindowingType.RANGE);
        }
        if (ctx.from_ != null) {
            vtlWindowing.setVtlWindowingLimitFrom(
                    visitLimitClauseItem(ctx.from_)
            );
        }
        if (ctx.to_ != null) {
            vtlWindowing.setVtlWindowingLimitTo(
                    visitLimitClauseItem(ctx.to_)
            );
        }
        vtlWindowing.setOperator(Operator.WINDOWING);
        vtlWindowing.setCommand(ctx.getText());
        return vtlWindowing;
    }

    @Override
    public VtlWindowingLimit visitLimitClauseItem(VtlParser.LimitClauseItemContext ctx) {
        VtlWindowingLimit vtlWindowingLimit = new VtlWindowingLimit();
        if (ctx.PRECEDING() != null) {
            vtlWindowingLimit.setValue(
                    Integer.valueOf(ctx.INTEGER_CONSTANT().getText())
            );
            vtlWindowingLimit.setVtlLimitType(
                    VtlLimitType.PRECENDING
            );
        } else if (ctx.FOLLOWING() != null) {
            vtlWindowingLimit.setValue(
                    Integer.valueOf(ctx.INTEGER_CONSTANT().getText())
            );
            vtlWindowingLimit.setVtlLimitType(
                    VtlLimitType.FOLLOWING
            );
        }
        if (ctx.CURRENT() != null) {
            vtlWindowingLimit.setVtlLimitType(
                    VtlLimitType.CURRENT_DATA_POINT
            );
        }
        if (ctx.PRECEDING() != null) {
            vtlWindowingLimit.setVtlLimitType(
                    VtlLimitType.PRECENDING
            );
        }
        if (ctx.FOLLOWING() != null) {
            vtlWindowingLimit.setVtlLimitType(
                    VtlLimitType.FOLLOWING
            );
        }
        vtlWindowingLimit.setCommand(ctx.getText());
        return vtlWindowingLimit;
    }

}
