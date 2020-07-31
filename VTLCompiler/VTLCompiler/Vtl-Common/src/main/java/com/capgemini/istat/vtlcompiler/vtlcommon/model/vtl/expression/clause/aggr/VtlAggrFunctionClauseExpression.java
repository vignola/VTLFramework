package com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.clause.aggr;

import com.capgemini.istat.vtlcompiler.vtlcommon.model.dataset.VtlComponentRole;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.dataset.VtlComponentId;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.VtlExpression;

import java.io.Serializable;

public class VtlAggrFunctionClauseExpression extends VtlExpression implements Serializable {
    private VtlComponentRole vtlcomponentRole;
    private VtlComponentId vtlComponentId;
    private VtlAggrComp vtlAggrComp;

    public VtlComponentRole getVtlcomponentRole() {
        return vtlcomponentRole;
    }

    public void setVtlcomponentRole(VtlComponentRole vtlcomponentRole) {
        this.vtlcomponentRole = vtlcomponentRole;
    }

    public VtlComponentId getVtlComponentId() {
        return vtlComponentId;
    }

    public void setVtlComponentId(VtlComponentId vtlComponentId) {
        this.vtlComponentId = vtlComponentId;
    }

    public VtlAggrComp getVtlAggrComp() {
        return vtlAggrComp;
    }

    public void setVtlAggrComp(VtlAggrComp vtlAggrComp) {
        this.vtlAggrComp = vtlAggrComp;
    }
}
