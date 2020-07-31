package com.capgemini.istat.vtlcompiler.vtlinterpreter.visitor.clause.aggr;

import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.clause.aggr.VtlAggrClauseExpression;
import com.capgemini.istat.vtlcompiler.vtlinterpreter.visitor.InterpreterVisitor;
import com.capgemini.istat.vtlcompiler.vtlinterpreter.visitor.grouping.GroupingClauseVisitor;
import com.capgemini.istat.vtlcompiler.vtlinterpreter.visitor.grouping.HavingClauseVisitor;
import com.capgemini.istat.vtlcompiler.vtlparser.VtlParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AggrClauseVisitor extends InterpreterVisitor<VtlAggrClauseExpression> {
    private AggregateClauseVisitor aggregateClauseVisitor;
    private GroupingClauseVisitor groupingClauseVisitor;
    private HavingClauseVisitor havingClauseVisitor;

    @Autowired
    public void setAggregateClauseVisitor(AggregateClauseVisitor aggregateClauseVisitor) {
        this.aggregateClauseVisitor = aggregateClauseVisitor;
    }

    @Autowired
    public void setGroupingClauseVisitor(GroupingClauseVisitor groupingClauseVisitor) {
        this.groupingClauseVisitor = groupingClauseVisitor;
    }

    @Autowired
    public void setHavingClauseVisitor(HavingClauseVisitor havingClauseVisitor) {
        this.havingClauseVisitor = havingClauseVisitor;
    }

    @Override
    public VtlAggrClauseExpression visitAggrClause(VtlParser.AggrClauseContext ctx) {
        logger.debug("visitAggrClause -> ");
        VtlAggrClauseExpression vtlAggrClauseExpression = new VtlAggrClauseExpression();
        if (ctx.AGGREGATE() != null)
            logger.debug("visitAggrClause -> aggrClause -> " + ctx.AGGREGATE().getText());
        if (ctx.aggregateClause() != null) {
            aggregateClauseVisitor.setVariables(getVariables());
            vtlAggrClauseExpression.setVtlAggregateClauseExpression(
                    aggregateClauseVisitor.visitAggregateClause(ctx.aggregateClause())
            );
        }
        if (ctx.groupingClause() != null) {
            groupingClauseVisitor.setVariables(getVariables());
            vtlAggrClauseExpression.setVtlGroupClauseExpression(
                    groupingClauseVisitor.visit(ctx.groupingClause())
            );
        }
        if (ctx.havingClause() != null) {
            havingClauseVisitor.setVariables(getVariables());
            vtlAggrClauseExpression.setVtlHavingClauseExpression(
                    havingClauseVisitor.visitHavingClause(ctx.havingClause())
            );
        }
        vtlAggrClauseExpression.setCommand(ctx.getText());
        return vtlAggrClauseExpression;
    }

}
