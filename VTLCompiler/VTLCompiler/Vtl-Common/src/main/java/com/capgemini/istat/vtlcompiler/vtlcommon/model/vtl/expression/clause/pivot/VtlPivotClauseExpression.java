package com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.clause.pivot;

import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.constant.VtlConstantExpression;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.dataset.VtlComponentId;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.VtlExpression;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.clause.VtlDatasetClause;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class VtlPivotClauseExpression extends VtlDatasetClause implements Serializable {
    private VtlExpression vtlDataset;
    private VtlComponentId identifier;
    private VtlComponentId measure;
    private List<VtlConstantExpression> constantExpressions = new ArrayList<>();

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

    public List<VtlConstantExpression> getConstantExpressions() {
        return constantExpressions;
    }

    public void setConstantExpressions(List<VtlConstantExpression> constantExpressions) {
        this.constantExpressions = constantExpressions;
    }
}
