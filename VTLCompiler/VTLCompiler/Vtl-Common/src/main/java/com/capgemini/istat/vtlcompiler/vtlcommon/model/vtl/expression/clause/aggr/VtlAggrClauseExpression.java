package com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.clause.aggr;

import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.VtlExpression;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.clause.VtlDatasetClause;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.grouping.VtlGroupClauseExpression;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.grouping.VtlHavingClauseExpression;

import java.io.Serializable;

public class VtlAggrClauseExpression extends VtlDatasetClause implements Serializable {
    private VtlGroupClauseExpression vtlGroupClauseExpression;
    private VtlHavingClauseExpression vtlHavingClauseExpression;
    private VtlAggregateClauseExpression vtlAggregateClauseExpression;
    private VtlExpression vtlDatasetClause;

    public VtlGroupClauseExpression getVtlGroupClauseExpression() {
        return vtlGroupClauseExpression;
    }

    public void setVtlGroupClauseExpression(VtlGroupClauseExpression vtlGroupClauseExpression) {
        this.vtlGroupClauseExpression = vtlGroupClauseExpression;
    }

    public VtlHavingClauseExpression getVtlHavingClauseExpression() {
        return vtlHavingClauseExpression;
    }

    public void setVtlHavingClauseExpression(VtlHavingClauseExpression vtlHavingClauseExpression) {
        this.vtlHavingClauseExpression = vtlHavingClauseExpression;
    }

    public VtlAggregateClauseExpression getVtlAggregateClauseExpression() {
        return vtlAggregateClauseExpression;
    }

    public void setVtlAggregateClauseExpression(VtlAggregateClauseExpression vtlAggregateClauseExpression) {
        this.vtlAggregateClauseExpression = vtlAggregateClauseExpression;
    }

    @Override
    public void setDataset(VtlExpression vtlExpression) {
        this.vtlDatasetClause = vtlExpression;
    }

    public VtlExpression getVtlDatasetClause() {
        return vtlDatasetClause;
    }


}
