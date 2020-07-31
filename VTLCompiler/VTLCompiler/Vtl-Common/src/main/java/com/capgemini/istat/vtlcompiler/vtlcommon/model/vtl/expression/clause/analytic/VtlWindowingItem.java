package com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.clause.analytic;

import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.constant.VtlConstantExpression;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.VtlExpression;

import java.io.Serializable;

public class VtlWindowingItem extends VtlExpression implements Serializable {
    private VtlConstantExpression vtlConstantExpression;
    private VtlWindowingType vtlWindowingType;

    public VtlConstantExpression getVtlConstantExpression() {
        return vtlConstantExpression;
    }

    public void setVtlConstantExpression(VtlConstantExpression vtlConstantExpression) {
        this.vtlConstantExpression = vtlConstantExpression;
    }

    public VtlWindowingType getVtlWindowingType() {
        return vtlWindowingType;
    }

    public void setVtlWindowingType(VtlWindowingType vtlWindowingType) {
        this.vtlWindowingType = vtlWindowingType;
    }


}
