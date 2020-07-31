package com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.statement;

import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.define.VtlDefineFunction;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.VtlExpression;

import java.io.Serializable;


public class VtlStatement extends VtlExpression implements Serializable {

    private VtlExpression vtlExpression;

    private VtlDefineFunction vtlDefineFunction;

    public VtlExpression getVtlExpression() {
        return vtlExpression;
    }

    public void setVtlExpression(VtlExpression vtlExpression) {
        this.vtlExpression = vtlExpression;
    }

    public VtlDefineFunction getVtlDefineFunction() {
        return vtlDefineFunction;
    }

    public void setVtlDefineFunction(VtlDefineFunction vtlDefineFunction) {
        this.vtlDefineFunction = vtlDefineFunction;
    }


}
