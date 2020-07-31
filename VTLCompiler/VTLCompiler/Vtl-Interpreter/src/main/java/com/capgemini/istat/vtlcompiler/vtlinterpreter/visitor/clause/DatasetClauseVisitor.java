package com.capgemini.istat.vtlcompiler.vtlinterpreter.visitor.clause;

import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.VtlExpression;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.clause.VtlDatasetClause;
import com.capgemini.istat.vtlcompiler.vtlinterpreter.visitor.InterpreterVisitor;
import com.capgemini.istat.vtlcompiler.vtlinterpreter.visitor.clause.aggr.AggrClauseVisitor;
import com.capgemini.istat.vtlcompiler.vtlinterpreter.visitor.clause.calc.CalcClauseVisitor;
import com.capgemini.istat.vtlcompiler.vtlinterpreter.visitor.clause.filter.FilterClauseVisitor;
import com.capgemini.istat.vtlcompiler.vtlinterpreter.visitor.clause.keepdrop.KeepOrDropClauseVisitor;
import com.capgemini.istat.vtlcompiler.vtlinterpreter.visitor.clause.rename.RenameClauseVisitor;
import com.capgemini.istat.vtlcompiler.vtlinterpreter.visitor.clause.sub.SubspaceClauseVisitor;
import com.capgemini.istat.vtlcompiler.vtlinterpreter.visitor.clause.unpivot.PivotOrUnpivotClauseVisitor;
import com.capgemini.istat.vtlcompiler.vtlparser.VtlParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DatasetClauseVisitor extends InterpreterVisitor<VtlExpression> {
    private CalcClauseVisitor calcClauseVisitor;
    private FilterClauseVisitor filterClauseVisitor;
    private AggrClauseVisitor aggrClauseVisitor;
    private KeepOrDropClauseVisitor keepOrDropClauseVisitor;
    private RenameClauseVisitor renameClauseVisitor;
    private SubspaceClauseVisitor subspaceClauseVisitor;
    private PivotOrUnpivotClauseVisitor unpivotClauseVisitor;

    @Autowired
    public void setCalcClauseVisitor(CalcClauseVisitor calcClauseVisitor) {
        this.calcClauseVisitor = calcClauseVisitor;
    }

    @Autowired
    public void setFilterClauseVisitor(FilterClauseVisitor filterClauseVisitor) {
        this.filterClauseVisitor = filterClauseVisitor;
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
    public void setSubspaceClauseVisitor(SubspaceClauseVisitor subspaceClauseVisitor) {
        this.subspaceClauseVisitor = subspaceClauseVisitor;
    }

    @Autowired
    public void setUnpivotClauseVisitor(PivotOrUnpivotClauseVisitor unpivotClauseVisitor) {
        this.unpivotClauseVisitor = unpivotClauseVisitor;
    }

    @Override
    public VtlDatasetClause visitDatasetClause(VtlParser.DatasetClauseContext ctx) {
        if (ctx.calcClause() != null) {
            calcClauseVisitor.setVariables(getVariables());
            return calcClauseVisitor.visitCalcClause(ctx.calcClause());
        }
        if (ctx.filterClause() != null) {
            filterClauseVisitor.setVariables(getVariables());
            return filterClauseVisitor.visitFilterClause(ctx.filterClause());
        }
        if (ctx.aggrClause() != null) {
            aggrClauseVisitor.setVariables(getVariables());
            return aggrClauseVisitor.visitAggrClause(ctx.aggrClause());
        }
        if (ctx.keepOrDropClause() != null) {
            keepOrDropClauseVisitor.setVariables(getVariables());
            return keepOrDropClauseVisitor.visitKeepOrDropClause(ctx.keepOrDropClause());
        }

        if (ctx.renameClause() != null) {
            return renameClauseVisitor.visitRenameClause(ctx.renameClause());
        }
        if (ctx.subspaceClause() != null) {
            subspaceClauseVisitor.setVariables(getVariables());
            return subspaceClauseVisitor.visitSubspaceClause(ctx.subspaceClause());
        }
        if (ctx.pivotOrUnpivotClause() != null) {
            unpivotClauseVisitor.setVariables(getVariables());
            return unpivotClauseVisitor.visitPivotOrUnpivotClause(ctx.pivotOrUnpivotClause());
        }
        if (ctx.customPivotClause() != null) {
            unpivotClauseVisitor.setVariables(getVariables());
            return unpivotClauseVisitor.visitCustomPivotClause(ctx.customPivotClause());
        }
        return null;
    }


}
