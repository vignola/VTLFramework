package com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.join;

import com.capgemini.istat.vtlcompiler.vtlcommon.model.dataset.VtlComponent;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.VtlExpression;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class VtlJoinSelectClause extends VtlExpression implements Serializable {
    private List<VtlJoinSelectClauseItem> vtlJoinSelectClauseItems;
    private VtlJoinUsing vtlJoinUsing;
    private List<VtlComponent> supersetIdentifiers;
    private List<VtlComponent> commonIdentifiers;

    public List<VtlJoinSelectClauseItem> getVtlJoinSelectClauseItems() {
        if (vtlJoinSelectClauseItems == null)
            this.vtlJoinSelectClauseItems = new ArrayList<>();
        return vtlJoinSelectClauseItems;
    }

    public void setVtlJoinSelectClauseItems(List<VtlJoinSelectClauseItem> vtlJoinSelectClauseItems) {
        this.vtlJoinSelectClauseItems = vtlJoinSelectClauseItems;
    }

    public VtlJoinUsing getVtlJoinUsing() {
        return vtlJoinUsing;
    }

    public void setVtlJoinUsing(VtlJoinUsing vtlJoinUsing) {
        this.vtlJoinUsing = vtlJoinUsing;
    }

    public List<VtlComponent> getSupersetIdentifiers() {
        return supersetIdentifiers;
    }

    public void setSupersetIdentifiers(List<VtlComponent> supersetIdentifiers) {
        this.supersetIdentifiers = supersetIdentifiers;
    }

    public List<VtlComponent> getCommonIdentifiers() {
        return commonIdentifiers;
    }

    public void setCommonIdentifiers(List<VtlComponent> commonIdentifiers) {
        this.commonIdentifiers = commonIdentifiers;
    }
}
