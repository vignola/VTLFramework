package com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.comparison;

import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.VtlExpression;

import java.io.Serializable;

public class VtlBetweenExpression extends VtlExpression implements Serializable {
    private VtlExpression vtlElement;
    private VtlExpression vtlfrom;
    private VtlExpression vtlTo;


    public VtlExpression getVtlElement() {
        return vtlElement;
    }

    public void setVtlElement(VtlExpression vtlElement) {
        this.vtlElement = vtlElement;
    }

    public VtlExpression getVtlfrom() {
        return vtlfrom;
    }

    public void setVtlfrom(VtlExpression vtlfrom) {
        this.vtlfrom = vtlfrom;
    }

    public VtlExpression getVtlTo() {
        return vtlTo;
    }

    public void setVtlTo(VtlExpression vtlTo) {
        this.vtlTo = vtlTo;
    }


}
