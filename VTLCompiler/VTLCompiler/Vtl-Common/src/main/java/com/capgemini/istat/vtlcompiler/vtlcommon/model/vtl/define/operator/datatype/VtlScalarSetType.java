package com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.define.operator.datatype;

import java.io.Serializable;

public class VtlScalarSetType implements VtlDataType, Serializable {
    private VtlScalarType scalarType;

    public VtlScalarType getScalarType() {
        return scalarType;
    }

    public void setScalarType(VtlScalarType scalarType) {
        this.scalarType = scalarType;
    }
}
