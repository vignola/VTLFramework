package com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.clause.analytic;

import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.VtlExpression;

import java.io.Serializable;

public class VtlWindowing extends VtlExpression implements Serializable {
    private VtlWindowingType vtlWindowingType;
    private VtlWindowingLimit vtlWindowingLimitFrom;
    private VtlWindowingLimit vtlWindowingLimitTo;

    public VtlWindowingType getVtlWindowingType() {
        return vtlWindowingType;
    }

    public void setVtlWindowingType(VtlWindowingType vtlWindowingType) {
        this.vtlWindowingType = vtlWindowingType;
    }

    public VtlWindowingLimit getVtlWindowingLimitFrom() {
        return vtlWindowingLimitFrom;
    }

    public void setVtlWindowingLimitFrom(VtlWindowingLimit vtlWindowingLimitFrom) {
        this.vtlWindowingLimitFrom = vtlWindowingLimitFrom;
    }

    public VtlWindowingLimit getVtlWindowingLimitTo() {
        return vtlWindowingLimitTo;
    }

    public void setVtlWindowingLimitTo(VtlWindowingLimit vtlWindowingLimitTo) {
        this.vtlWindowingLimitTo = vtlWindowingLimitTo;
    }


}
