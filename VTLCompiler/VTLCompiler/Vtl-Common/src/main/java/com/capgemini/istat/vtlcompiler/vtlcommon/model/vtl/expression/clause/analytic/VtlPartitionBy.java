package com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.clause.analytic;

import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.dataset.VtlComponentId;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.VtlExpression;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class VtlPartitionBy extends VtlExpression implements Serializable {
    private List<VtlComponentId> vtlComponentIds;

    public List<VtlComponentId> getVtlComponentIds() {
        if (vtlComponentIds == null)
            this.vtlComponentIds = new ArrayList<>();
        return vtlComponentIds;
    }

    public void setVtlComponentIds(List<VtlComponentId> vtlComponentIds) {
        this.vtlComponentIds = vtlComponentIds;
    }


}
