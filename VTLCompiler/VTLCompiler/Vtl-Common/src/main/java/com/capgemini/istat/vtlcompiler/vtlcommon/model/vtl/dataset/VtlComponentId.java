package com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.dataset;

import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.VtlExpression;

import java.io.Serializable;
import java.util.UUID;

public class VtlComponentId extends VtlExpression implements Serializable {
    public final static String VTL_OBJECT_TYPE = "VtlComponentId";
    private String componentName;
    private boolean ignoreCase = true;
    private UUID requestUuid;

    public VtlComponentId() {

    }

    public VtlComponentId(String componentName) {
        this.componentName = componentName;
    }

    @Override
    public String getType() {
        return VTL_OBJECT_TYPE;
    }

    public String getComponentName() {
        return componentName;
    }

    public void setComponentName(String componentName) {
        this.componentName = componentName;
    }

    public boolean isIgnoreCase() {
        return ignoreCase;
    }

    public void setIgnoreCase(boolean ignoreCase) {
        this.ignoreCase = ignoreCase;
    }

    public UUID getRequestUuid() {
        return requestUuid;
    }

    public void setRequestUuid(UUID requestUuid) {
        this.requestUuid = requestUuid;
    }
}
