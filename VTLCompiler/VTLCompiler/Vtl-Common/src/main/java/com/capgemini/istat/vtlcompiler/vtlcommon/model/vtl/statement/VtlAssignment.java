package com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.statement;

import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.dataset.VtlVarId;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.VtlExpression;

import java.io.Serializable;

public class VtlAssignment extends VtlStatement implements Serializable {

    private VtlExpression vtlExpression;

    private VtlVarId varId;

    @Override
    public VtlExpression getVtlExpression() {
        return vtlExpression;
    }

    @Override
    public void setVtlExpression(VtlExpression vtlExpression) {
        this.vtlExpression = vtlExpression;
    }

    public VtlVarId getVarId() {
        return varId;
    }

    public void setVarId(VtlVarId varId) {
        this.varId = varId;
    }


    @Override
    public String getType() {
        return "VtlAssignment";
    }
}
