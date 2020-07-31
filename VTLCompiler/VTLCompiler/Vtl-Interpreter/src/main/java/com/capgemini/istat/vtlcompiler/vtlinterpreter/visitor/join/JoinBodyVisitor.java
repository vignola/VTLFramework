package com.capgemini.istat.vtlcompiler.vtlinterpreter.visitor.join;

import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.join.VtlJoinBody;
import com.capgemini.istat.vtlcompiler.vtlinterpreter.visitor.InterpreterVisitor;
import com.capgemini.istat.vtlcompiler.vtlinterpreter.visitor.clause.aggr.AggrClauseVisitor;
import com.capgemini.istat.vtlcompiler.vtlinterpreter.visitor.clause.calc.CalcClauseVisitor;
import com.capgemini.istat.vtlcompiler.vtlinterpreter.visitor.clause.filter.FilterClauseVisitor;
import com.capgemini.istat.vtlcompiler.vtlinterpreter.visitor.clause.keepdrop.KeepOrDropClauseVisitor;
import com.capgemini.istat.vtlcompiler.vtlinterpreter.visitor.clause.rename.RenameClauseVisitor;
import com.capgemini.istat.vtlcompiler.vtlparser.VtlParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class JoinBodyVisitor extends InterpreterVisitor {
    private JoinApplyClauseVisitor joinApplyClauseVisitor;
    private FilterClauseVisitor filterClauseVisitor;
    private CalcClauseVisitor calcClauseVisitor;
    private AggrClauseVisitor aggrClauseVisitor;
    private KeepOrDropClauseVisitor keepOrDropClauseVisitor;
    private RenameClauseVisitor renameClauseVisitor;

    @Autowired
    public void setCalcClauseVisitor(CalcClauseVisitor calcClauseVisitor) {
        this.calcClauseVisitor = calcClauseVisitor;
    }

    @Autowired
    public void setAggrClauseVisitor(AggrClauseVisitor aggrClauseVisitor) {
        this.aggrClauseVisitor = aggrClauseVisitor;
    }

    @Autowired
    public void setKeepOrDropClauseVisitor(KeepOrDropClauseVisitor keepOrDropClauseVisitor) {
        this.keepOrDropClauseVisitor = keepOrDropClauseVisitor;
    }


    @Autowired
    public void setRenameClauseVisitor(RenameClauseVisitor renameClauseVisitor) {
        this.renameClauseVisitor = renameClauseVisitor;
    }

    @Autowired
    public void setJoinApplyClauseVisitor(JoinApplyClauseVisitor joinApplyClauseVisitor) {
        this.joinApplyClauseVisitor = joinApplyClauseVisitor;
    }

    @Autowired
    public void setFilterClauseVisitor(FilterClauseVisitor filterClauseVisitor) {
        this.filterClauseVisitor = filterClauseVisitor;
    }

    @Override
    public VtlJoinBody visitJoinBody(VtlParser.JoinBodyContext ctx) {
        logger.debug("visitJoinBody -> ");
        VtlJoinBody vtlJoinBody = new VtlJoinBody();
        if (ctx.filterClause() != null) {
            filterClauseVisitor.setVariables(getVariables());

            vtlJoinBody.setVtlFilterClauseExpression(
                    filterClauseVisitor.visitFilterClause(ctx.filterClause())
            );
        }
        if (ctx.aggrClause() != null) {
            aggrClauseVisitor.setVariables(getVariables());

            vtlJoinBody.setVtlJoinBodyClause(
                    aggrClauseVisitor.visitAggrClause(ctx.aggrClause())
            );
        }
        if (ctx.joinApplyClause() != null) {
            joinApplyClauseVisitor.setVariables(getVariables());

            vtlJoinBody.setVtlJoinBodyClause(
                    joinApplyClauseVisitor.visitJoinApplyClause(ctx.joinApplyClause())
            );
        }
        if (ctx.calcClause() != null) {
            calcClauseVisitor.setVariables(getVariables());

            vtlJoinBody.setVtlJoinBodyClause(
                    calcClauseVisitor.visitCalcClause(ctx.calcClause())
            );
        }
        if (ctx.keepOrDropClause() != null) {
            keepOrDropClauseVisitor.setVariables(getVariables());

            vtlJoinBody.setKeepDropClause(
                    keepOrDropClauseVisitor.visitKeepOrDropClause(ctx.keepOrDropClause())
            );
        }

        if (ctx.renameClause() != null) {
            renameClauseVisitor.visitRenameClause(ctx.renameClause());

            vtlJoinBody.setVtlRenameClauseExpression(
                    renameClauseVisitor.visitRenameClause(ctx.renameClause())
            );
        }
        vtlJoinBody.setCommand(ctx.getText());
        return vtlJoinBody;
    }
}
