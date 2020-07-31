package com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.clause.apply;

import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.VtlExpression;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.clause.VtlDatasetClause;

import java.io.Serializable;

public class VtlApplyClause extends VtlDatasetClause implements Serializable {
    private VtlExpression vtlVarId;
    private VtlExpression vtlExpression;

    @Override
    public void setDataset(VtlExpression vtlExpression) {
        this.vtlVarId = vtlExpression;
    }

    public VtlExpression getVtlVarId() {
        return vtlVarId;
    }

    public void setVtlVarId(VtlExpression vtlVarId) {
        this.vtlVarId = vtlVarId;
    }

    public VtlExpression getVtlExpression() {
        return vtlExpression;
    }

    public void setVtlExpression(VtlExpression vtlExpression) {
        this.vtlExpression = vtlExpression;
    }


}
