package com.capgemini.istat.vtlcompiler.vtlinterpreter.visitor.clause.calc;

import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.clause.calc.VtlCalcClauseExpression;
import com.capgemini.istat.vtlcompiler.vtlinterpreter.visitor.InterpreterVisitor;
import com.capgemini.istat.vtlcompiler.vtlparser.VtlParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CalcClauseVisitor extends InterpreterVisitor<VtlCalcClauseExpression> {
    private CalcClauseItemVisitor calcClauseItemVisitor;

    @Autowired
    public void setCalcClauseItemVisitor(CalcClauseItemVisitor calcClauseItemVisitor) {
        this.calcClauseItemVisitor = calcClauseItemVisitor;
    }

    @Override
    public VtlCalcClauseExpression visitCalcClause(VtlParser.CalcClauseContext ctx) {
        logger.debug("visitCalcClause -> ");

        VtlCalcClauseExpression vtlCalcClauseExpression = new VtlCalcClauseExpression();
        if (ctx.CALC() != null) {
            logger.debug("visitCalcClause -> clause -> " + ctx.CALC().getText());
        }

        if (ctx.calcClauseItem() != null) {
            calcClauseItemVisitor.setVariables(getVariables());
            for (VtlParser.CalcClauseItemContext calcClauseItemContext : ctx.calcClauseItem()) {
                vtlCalcClauseExpression.getVtlCalcClauseItemExpressionList().add(calcClauseItemVisitor.visitCalcClauseItem(calcClauseItemContext));
            }
        }
        vtlCalcClauseExpression.setCommand(ctx.getText());

        return vtlCalcClauseExpression;
    }
}
