package com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.clause.aggr;

import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.dataset.VtlComponentId;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.dataset.VtlVarId;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.VtlExpression;

import java.io.Serializable;

public class VtlAggrFunctionExpression extends VtlExpression implements Serializable {

    private VtlComponentId vtlComponentId;

    public VtlComponentId getVtlComponentId() {
        return vtlComponentId;
    }

    public void setVtlComponentId(VtlComponentId vtlComponentId) {
        this.vtlComponentId = vtlComponentId;
    }

    public void setVtlComponentIdFromVtlExpression(VtlExpression vtlExpression) {
        VtlVarId vtlVarId = (VtlVarId) vtlExpression; //TODO try cactch
        this.vtlComponentId = new VtlComponentId(vtlVarId.getName());
    }


}
