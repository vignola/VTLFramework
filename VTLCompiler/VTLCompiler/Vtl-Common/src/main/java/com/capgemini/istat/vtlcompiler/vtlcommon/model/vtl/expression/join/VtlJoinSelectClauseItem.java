package com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.join;

import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.VtlExpression;

import java.io.Serializable;

public class VtlJoinSelectClauseItem extends VtlExpression implements Serializable {

    private VtlExpression vtlExpression;
    private String asName;

    public VtlExpression getVtlExpression() {
        return vtlExpression;
    }

    public void setVtlExpression(VtlExpression vtlExpression) {
        this.vtlExpression = vtlExpression;
    }

    public String getAsName() {
        return asName;
    }

    public void setAsName(String asName) {
        this.asName = asName;
    }


}
