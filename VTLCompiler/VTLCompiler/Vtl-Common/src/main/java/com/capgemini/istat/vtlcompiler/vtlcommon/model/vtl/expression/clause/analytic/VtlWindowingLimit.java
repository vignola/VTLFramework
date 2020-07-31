package com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.clause.analytic;

import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.VtlExpression;

import java.io.Serializable;

public class VtlWindowingLimit extends VtlExpression implements Serializable {
    private Integer value;
    private VtlLimitType vtlLimitType;

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public VtlLimitType getVtlLimitType() {
        return vtlLimitType;
    }

    public void setVtlLimitType(VtlLimitType vtlLimitType) {
        this.vtlLimitType = vtlLimitType;
    }


}
