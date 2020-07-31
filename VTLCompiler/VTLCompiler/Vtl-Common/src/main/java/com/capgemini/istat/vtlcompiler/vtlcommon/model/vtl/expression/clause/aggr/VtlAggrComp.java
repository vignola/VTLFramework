package com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.clause.aggr;

import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.VtlExpression;

import java.io.Serializable;

public class VtlAggrComp extends VtlExpression implements Serializable {

    private VtlExpression vtlExpression;

    public VtlExpression getVtlExpression() {
        return vtlExpression;
    }

    public void setVtlExpression(VtlExpression vtlExpression) {
        this.vtlExpression = vtlExpression;
    }
}
