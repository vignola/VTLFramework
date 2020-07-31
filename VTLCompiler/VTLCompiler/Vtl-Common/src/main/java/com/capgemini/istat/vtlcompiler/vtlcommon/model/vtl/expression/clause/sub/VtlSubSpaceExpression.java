package com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.clause.sub;

import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.VtlExpression;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.clause.VtlDatasetClause;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class VtlSubSpaceExpression extends VtlDatasetClause implements Serializable {

    private VtlExpression vtlDatasetClause;
    private List<VtlSubSpaceItemExpression> vtlSubSpaceItemExpressionList;

    @Override
    public void setDataset(VtlExpression vtlExpression) {
        this.vtlDatasetClause = vtlExpression;
    }


    public List<VtlSubSpaceItemExpression> getVtlSubSpaceItemExpressionList() {
        if (vtlSubSpaceItemExpressionList == null)
            this.vtlSubSpaceItemExpressionList = new ArrayList<>();
        return vtlSubSpaceItemExpressionList;
    }

    public void setVtlSubSpaceItemExpressionList(List<VtlSubSpaceItemExpression> vtlSubSpaceItemExpressionList) {
        this.vtlSubSpaceItemExpressionList = vtlSubSpaceItemExpressionList;
    }

    public VtlExpression getVtlDatasetClause() {
        return vtlDatasetClause;
    }
}
