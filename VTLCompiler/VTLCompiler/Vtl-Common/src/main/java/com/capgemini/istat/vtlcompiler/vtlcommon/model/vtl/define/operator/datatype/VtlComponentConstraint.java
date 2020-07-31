package com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.define.operator.datatype;

import java.io.Serializable;

public class VtlComponentConstraint implements VtlDataType, Serializable {
    private VtlComponentType vtlComponentType;
    private String componentName;
    private VtlComponentMultiplicity vtlComponentMultiplicity;

    public VtlComponentType getVtlComponentType() {
        return vtlComponentType;
    }

    public void setVtlComponentType(VtlComponentType vtlComponentType) {
        this.vtlComponentType = vtlComponentType;
    }

    public String getComponentName() {
        return componentName;
    }

    public void setComponentName(String componentName) {
        this.componentName = componentName;
    }

    public VtlComponentMultiplicity getVtlComponentMultiplicity() {
        return vtlComponentMultiplicity;
    }

    public void setVtlComponentMultiplicity(VtlComponentMultiplicity vtlComponentMultiplicity) {
        this.vtlComponentMultiplicity = vtlComponentMultiplicity;
    }
}
