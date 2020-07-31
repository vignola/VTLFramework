package com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.constant;

import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.VtlExpression;

import java.io.Serializable;
import java.util.UUID;

public class VtlConstantExpression extends VtlExpression implements Serializable {
    public final static String VTL_OBJECT_TYPE = "VtlConstantExpression";
    private VtlConstant vtlConstant;

    public VtlConstantExpression() {

    }

    public VtlConstantExpression(VtlConstant vtlConstant) {
        this.vtlConstant = vtlConstant;
    }

    public VtlConstant getVtlConstant() {
        return vtlConstant;
    }

    public void setVtlConstant(VtlConstant vtlConstant) {
        this.vtlConstant = vtlConstant;
    }

    public void setRequestUuid(UUID requestUuid) {
        super.setRequestUuid(requestUuid);
        vtlConstant.setRequestUuid(requestUuid);
    }

    @Override
    public String getType() {
        return VTL_OBJECT_TYPE;
    }
}
