package com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.clause;

import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.VtlExpression;

import java.io.Serializable;

public class VtlClauseOperator extends VtlExpression implements Serializable {
    private VtlDatasetClause vtlDatasetClause;


    public VtlDatasetClause getVtlDatasetClause() {
        return vtlDatasetClause;
    }

    public void setVtlDatasetClause(VtlDatasetClause vtlDatasetClause) {
        this.vtlDatasetClause = vtlDatasetClause;
    }
}
