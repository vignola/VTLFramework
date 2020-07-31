package com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.clause.rename;

import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.dataset.VtlComponentId;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.VtlExpression;

import java.io.Serializable;

public class VtlRenameClauseItemExpression extends VtlExpression implements Serializable {

    private VtlComponentId vtlComponentNameFrom;
    private VtlComponentId vtlComponentNameTo;

    public VtlComponentId getVtlComponentNameFrom() {
        return vtlComponentNameFrom;
    }

    public void setVtlComponentNameFrom(VtlComponentId vtlComponentNameFrom) {
        this.vtlComponentNameFrom = vtlComponentNameFrom;
    }

    public VtlComponentId getVtlComponentNameTo() {
        return vtlComponentNameTo;
    }

    public void setVtlComponentNameTo(VtlComponentId vtlComponentNameTo) {
        this.vtlComponentNameTo = vtlComponentNameTo;
    }


}
