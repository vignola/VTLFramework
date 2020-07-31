package com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.define.operator.datatype;

import com.capgemini.istat.vtlcompiler.vtlcommon.model.dataset.VtlComponentRole;

import java.io.Serializable;

public class VtlComponentType implements VtlDataType, Serializable {

    private VtlComponentRole componentRole;
    private VtlScalarType scalarType;

    public VtlComponentRole getComponentRole() {
        return componentRole;
    }

    public void setComponentRole(VtlComponentRole componentRole) {
        this.componentRole = componentRole;
    }

    public VtlScalarType getScalarType() {
        return scalarType;
    }

    public void setScalarType(VtlScalarType scalarType) {
        this.scalarType = scalarType;
    }
}
