package com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.define.operator.datatype;

import java.io.Serializable;
import java.util.LinkedList;

public class VtlDatasetType implements VtlDataType, Serializable {
    private LinkedList<VtlComponentConstraint> componentConstraints = new LinkedList<>();

    public LinkedList<VtlComponentConstraint> getComponentConstraints() {
        return componentConstraints;
    }

    public void setComponentConstraints(LinkedList<VtlComponentConstraint> componentConstraints) {
        this.componentConstraints = componentConstraints;
    }
}
