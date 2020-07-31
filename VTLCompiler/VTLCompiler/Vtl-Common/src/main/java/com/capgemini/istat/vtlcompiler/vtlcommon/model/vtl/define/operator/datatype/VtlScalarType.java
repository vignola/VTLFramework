package com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.define.operator.datatype;

import com.capgemini.istat.vtlcompiler.vtlcommon.model.dataset.VtlType;

import java.io.Serializable;

public class VtlScalarType implements VtlDataType, Serializable {

    private VtlType vtlType;
    private String valueDomain;
    private VtlConstraint vtlConstraint;

    public VtlType getVtlType() {
        return vtlType;
    }

    public void setVtlType(VtlType vtlType) {
        this.vtlType = vtlType;
    }

    public String getValueDomain() {
        return valueDomain;
    }

    public void setValueDomain(String valueDomain) {
        this.valueDomain = valueDomain;
    }

    public VtlConstraint getVtlConstraint() {
        return vtlConstraint;
    }

    public void setVtlConstraint(VtlConstraint vtlConstraint) {
        this.vtlConstraint = vtlConstraint;
    }
}
