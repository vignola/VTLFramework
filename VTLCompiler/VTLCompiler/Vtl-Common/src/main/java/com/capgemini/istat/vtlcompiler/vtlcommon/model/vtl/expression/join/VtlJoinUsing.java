package com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.join;

import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.dataset.VtlComponentId;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.VtlExpression;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class VtlJoinUsing extends VtlExpression implements Serializable {
    private List<VtlComponentId> componentIds;

    public List<VtlComponentId> getComponentIds() {
        if (componentIds == null)
            this.componentIds = new ArrayList<>();
        return componentIds;
    }

    public void setComponentIds(List<VtlComponentId> componentIds) {
        this.componentIds = componentIds;
    }


}
