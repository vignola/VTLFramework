package com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.clause.aggr;

import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.VtlExpression;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.grouping.VtlGroupClauseExpression;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.grouping.VtlHavingClauseExpression;

import java.io.Serializable;

public class VtlAggrInvocationExpression extends VtlExpression implements Serializable {
    private VtlExpression vtlExpression;
    private VtlGroupClauseExpression vtlGroupClauseExpression;
    private VtlHavingClauseExpression vtlHavingClauseExpression;

    public VtlGroupClauseExpression getVtlGroupClauseExpression() {
        return vtlGroupClauseExpression;
    }

    public void setVtlGroupClauseExpression(VtlGroupClauseExpression vtlGroupClauseExpression) {
        this.vtlGroupClauseExpression = vtlGroupClauseExpression;
    }

    public VtlExpression getVtlExpression() {
        return vtlExpression;
    }

    public void setVtlExpression(VtlExpression vtlExpression) {
        this.vtlExpression = vtlExpression;
    }

    public VtlHavingClauseExpression getVtlHavingClauseExpression() {
        return vtlHavingClauseExpression;
    }

    public void setVtlHavingClauseExpression(VtlHavingClauseExpression vtlHavingClauseExpression) {
        this.vtlHavingClauseExpression = vtlHavingClauseExpression;
    }


}
