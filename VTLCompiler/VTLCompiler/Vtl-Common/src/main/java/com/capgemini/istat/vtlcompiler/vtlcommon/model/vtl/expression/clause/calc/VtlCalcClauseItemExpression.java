package com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.clause.calc;

import com.capgemini.istat.vtlcompiler.vtlcommon.model.dataset.VtlComponentRole;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.dataset.VtlComponentId;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.VtlExpression;

import java.io.Serializable;

public class VtlCalcClauseItemExpression extends VtlExpression implements Serializable {

    private VtlComponentId vtlComponentId;
    private VtlExpression vtlExpression;
    private VtlComponentRole vtlComponentRole;


    public VtlComponentRole getVtlComponentRole() {
        return vtlComponentRole;
    }

    public void setVtlComponentRole(VtlComponentRole vtlComponentRole) {
        this.vtlComponentRole = vtlComponentRole;
    }

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
}
