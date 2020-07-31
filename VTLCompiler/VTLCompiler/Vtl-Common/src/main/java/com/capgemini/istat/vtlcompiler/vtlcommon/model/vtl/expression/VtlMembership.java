package com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression;


import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.dataset.VtlComponentId;

import java.io.Serializable;

public class VtlMembership extends VtlExpression implements Serializable {
    private VtlComponentId vtlComponentId;
    private VtlExpression vtlExpression;

    public VtlComponentId getVtlComponentId() {
        return vtlComponentId;
    }

    public void setVtlComponentId(VtlComponentId vtlComponentId) {
        this.vtlComponentId = vtlComponentId;
    }

    public VtlExpression getVtlExpression() {
        return vtlExpression;
    }

    public void setVtlExpression(VtlExpression vtlExpression) {
        this.vtlExpression = vtlExpression;
    }

    @Override
    public String getType() {
        return "VtlMembership";
    }


}
