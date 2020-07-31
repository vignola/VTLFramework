package com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.clause.drop;

import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.dataset.VtlComponentId;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.dataset.VtlVarId;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.VtlExpression;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.clause.VtlDatasetClause;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class VtlKeepOrDropClauseExpression extends VtlDatasetClause implements Serializable {
    private VtlExpression vtlExpression;
    private List<VtlComponentId> vtlComponentIds = new ArrayList<>();
    ;

    public VtlExpression getVtlExpression() {
        return vtlExpression;
    }

    public void setVtlVarId(VtlVarId vtlVarId) {
        this.vtlExpression = vtlVarId;
    }

    @Override
    public void setDataset(VtlExpression vtlExpression) {
        this.vtlExpression = vtlExpression;
    }

    public List<VtlComponentId> getVtlComponentIds() {
        return vtlComponentIds;
    }

    public void setVtlComponentIds(List<VtlComponentId> vtlComponentIds) {
        this.vtlComponentIds = vtlComponentIds;
    }


}
