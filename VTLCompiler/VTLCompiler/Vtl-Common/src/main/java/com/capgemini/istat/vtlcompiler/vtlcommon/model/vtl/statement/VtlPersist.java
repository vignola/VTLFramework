package com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.statement;

import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.dataset.VtlVarId;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.VtlExpression;

import java.io.Serializable;

public class VtlPersist extends VtlStatement implements Serializable {

    private VtlVarId vtlVarId;
    private VtlExpression vtlExpression;

    public VtlVarId getVtlVarId() {
        return vtlVarId;
    }

    public void setVtlVarId(VtlVarId vtlVarId) {
        this.vtlVarId = vtlVarId;
    }

    @Override
    public VtlExpression getVtlExpression() {
        return vtlExpression;
    }

    @Override
    public void setVtlExpression(VtlExpression vtlExpression) {
        this.vtlExpression = vtlExpression;
    }


    @Override
    public String getType() {
        return "VtlPersist";
    }
}
