package com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.clause.filter;

import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.VtlExpression;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.clause.VtlDatasetClause;

import java.io.Serializable;

public class VtlFilterClauseExpression extends VtlDatasetClause implements Serializable {
    private VtlExpression datasetClause;
    private VtlExpression filterExpression;

    @Override
    public void setDataset(VtlExpression vtlExpression) {
        this.datasetClause = vtlExpression;
    }

    public VtlExpression getDatasetClause() {
        return datasetClause;
    }

    public VtlExpression getFilterExpression() {
        return filterExpression;
    }

    public void setFilterExpression(VtlExpression filterExpression) {
        this.filterExpression = filterExpression;
    }


}
