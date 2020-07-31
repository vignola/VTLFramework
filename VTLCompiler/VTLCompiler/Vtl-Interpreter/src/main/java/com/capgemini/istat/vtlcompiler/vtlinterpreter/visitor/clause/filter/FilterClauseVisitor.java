package com.capgemini.istat.vtlcompiler.vtlinterpreter.visitor.clause.filter;

import com.capgemini.istat.vtlcompiler.vtlcommon.model.operator.Operator;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.clause.filter.VtlFilterClauseExpression;
import com.capgemini.istat.vtlcompiler.vtlinterpreter.visitor.InterpreterVisitor;
import com.capgemini.istat.vtlcompiler.vtlinterpreter.visitor.expression.ExpressionVisitor;
import com.capgemini.istat.vtlcompiler.vtlparser.VtlParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FilterClauseVisitor extends InterpreterVisitor<VtlFilterClauseExpression> {

    private ExpressionVisitor expressionVisitor;

    @Autowired
    public void setExpressionVisitor(ExpressionVisitor expressionVisitor) {
        this.expressionVisitor = expressionVisitor;
    }

    @Override
    public VtlFilterClauseExpression visitFilterClause(VtlParser.FilterClauseContext ctx) {
        logger.debug("visitFilterClause -> ");
        VtlFilterClauseExpression vtlFilterClauseExpression = new VtlFilterClauseExpression();

        if (ctx.FILTER() != null) {
            logger.debug("visitFilterClause -> filterClause -> " + ctx.FILTER().getText());
            vtlFilterClauseExpression.setOperator(Operator.FILTER);
        }
        if (ctx.exprComponent() != null) {
            expressionVisitor.setVariables(getVariables());
            vtlFilterClauseExpression.setFilterExpression(expressionVisitor.visit(ctx.exprComponent()));
        }
        return vtlFilterClauseExpression;
    }
}
