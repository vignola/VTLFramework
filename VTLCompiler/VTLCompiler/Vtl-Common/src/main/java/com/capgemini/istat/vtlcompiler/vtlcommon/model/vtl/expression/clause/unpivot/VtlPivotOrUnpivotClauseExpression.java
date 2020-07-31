package com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.clause.unpivot;

import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.dataset.VtlComponentId;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.VtlExpression;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.clause.VtlDatasetClause;

import java.io.Serializable;

public class VtlPivotOrUnpivotClauseExpression extends VtlDatasetClause implements Serializable {
    private VtlExpression vtlDataset;
    private VtlComponentId identifier;
    private VtlComponentId measure;

    @Override
    public void setDataset(VtlExpression vtlExpression) {
        this.vtlDataset = vtlExpression;
    }

    public VtlExpression getVtlDataset() {
        return vtlDataset;
    }

    public void setVtlDataset(VtlExpression vtlDataset) {
        this.vtlDataset = vtlDataset;
    }

    public VtlComponentId getIdentifier() {
        return identifier;
    }

    public void setIdentifier(VtlComponentId identifier) {
        this.identifier = identifier;
    }

    public VtlComponentId getMeasure() {
        return measure;
    }

    public void setMeasure(VtlComponentId measure) {
        this.measure = measure;
    }
}
