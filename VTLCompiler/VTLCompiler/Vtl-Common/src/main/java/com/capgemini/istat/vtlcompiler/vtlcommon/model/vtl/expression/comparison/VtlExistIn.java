package com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.comparison;

import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.VtlExpression;

import java.io.Serializable;

public class VtlExistIn extends VtlExpression implements Serializable {
    private VtlExpression leftExpression;
    private VtlExpression rightExpression;
    private String retain;

    public VtlExpression getLeftExpression() {
        return leftExpression;
    }

    public void setLeftExpression(VtlExpression leftExpression) {
        this.leftExpression = leftExpression;
    }

    public VtlExpression getRightExpression() {
        return rightExpression;
    }

    public void setRightExpression(VtlExpression rightExpression) {
        this.rightExpression = rightExpression;
    }

    public String getRetain() {
        return retain;
    }

    public void setRetain(String retain) {
        this.retain = retain;
    }


}
