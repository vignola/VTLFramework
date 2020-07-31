package com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.clause.analytic;

import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.VtlExpression;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class VtlOrderBy extends VtlExpression implements Serializable {
    private List<VtlOrderByItem> vtlOrderByItems;

    public List<VtlOrderByItem> getVtlOrderByItems() {
        if (vtlOrderByItems == null)
            this.vtlOrderByItems = new ArrayList<>();
        return vtlOrderByItems;
    }

    public void setVtlOrderByItems(List<VtlOrderByItem> vtlComponentIds) {
        this.vtlOrderByItems = vtlComponentIds;
    }


}
