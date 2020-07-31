package com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.grouping;

import com.capgemini.istat.vtlcompiler.vtlcommon.model.operator.Operator;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.dataset.VtlComponentId;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.VtlExpression;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class VtlGroupClauseExpression extends VtlExpression implements Serializable {
    private List<VtlComponentId> vtlComponentIds;
    private Operator groupType;
    private VtlExpression vtlExpression;

    public List<VtlComponentId> getVtlComponentIds() {
        if (vtlComponentIds == null)
            this.vtlComponentIds = new ArrayList<>();
        return vtlComponentIds;
    }

    public void setVtlComponentIds(List<VtlComponentId> vtlComponentIds) {
        this.vtlComponentIds = vtlComponentIds;
    }

    public Operator getGroupType() {
        return groupType;
    }

    public void setGroupType(Operator groupType) {
        this.groupType = groupType;
    }

    public VtlExpression getVtlExpression() {
        return vtlExpression;
    }

    public void setVtlExpression(VtlExpression vtlExpression) {
        this.vtlExpression = vtlExpression;
    }

    public List<String> extractVtlComponentName() {
        ArrayList<String> result = new ArrayList<>();
        for (VtlComponentId vtlComponentId : vtlComponentIds) {
            result.add(vtlComponentId.getComponentName());
        }
        return result;
    }
}
