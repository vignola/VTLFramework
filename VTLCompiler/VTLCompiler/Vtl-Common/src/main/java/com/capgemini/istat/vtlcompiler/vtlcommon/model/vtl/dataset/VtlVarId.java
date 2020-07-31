package com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.dataset;

import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.VtlExpression;

import java.io.Serializable;
import java.util.UUID;

public class VtlVarId extends VtlExpression implements Serializable {

    private String name;
    private boolean ignoreCase = true;
    private UUID requestUuid;

    public VtlVarId() {
        this.name = null;
    }

    public VtlVarId(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getType() {
        return "VtlVarId";
    }

    public boolean isIgnoreCase() {
        return ignoreCase;
    }

    public void setIgnoreCase(boolean ignoreCase) {
        this.ignoreCase = ignoreCase;
    }

    @Override
    public UUID getRequestUuid() {
        return requestUuid;
    }

    @Override
    public void setRequestUuid(UUID requestUuid) {
        this.requestUuid = requestUuid;
    }
}
