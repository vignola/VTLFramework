package com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.clause.keep;

import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.dataset.VtlVarId;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.VtlExpression;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.clause.VtlDatasetClause;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class VtlKeepClauseExpression extends VtlDatasetClause implements Serializable {

    private List<VtlExpression> componentIds;
    private VtlExpression vtlExpression;

    public List<VtlExpression> getComponentIds() {
        if (componentIds == null)
            this.componentIds = new ArrayList<>();
        return componentIds;
    }

    public void setComponentIds(List<VtlExpression> componentIds) {
        this.componentIds = componentIds;
    }

    public VtlExpression getVtlExpression() {
        return vtlExpression;
    }

    public void setVtlExpression(VtlVarId vtlExpression) {
        this.vtlExpression = vtlExpression;
    }


    @Override
    public void setDataset(VtlExpression vtlExpression) {
        this.vtlExpression = vtlExpression; //TODO Cast
    }
}
