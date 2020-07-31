package com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.statement;

import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.VtlExpression;

import java.io.Serializable;

public class VtlStatementExpression extends VtlStatement implements Serializable {
    private VtlExpression vtlExpression;

    @Override
    public VtlExpression getVtlExpression() {
        return vtlExpression;
    }

    @Override
    public void setVtlExpression(VtlExpression vtlExpression) {
        this.vtlExpression = vtlExpression;
    }


}
