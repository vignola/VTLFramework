package com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.clause.sub;

import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.constant.VtlConstantExpression;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.dataset.VtlComponentId;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.VtlExpression;

import java.io.Serializable;

public class VtlSubSpaceItemExpression extends VtlExpression implements Serializable {

    private VtlComponentId vtlComponentId;
    private VtlConstantExpression vtlConstantExpression;

    public VtlComponentId getVtlComponentId() {
        return vtlComponentId;
    }

    public void setVtlComponentId(VtlComponentId vtlComponentId) {
        this.vtlComponentId = vtlComponentId;
    }

    public VtlConstantExpression getVtlConstantExpression() {
        return vtlConstantExpression;
    }

    public void setVtlConstantExpression(VtlConstantExpression vtlConstantExpression) {
        this.vtlConstantExpression = vtlConstantExpression;
    }


}
