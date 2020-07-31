package com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.define.ruleset.hierarchical;

import com.capgemini.istat.vtlcompiler.vtlcommon.model.operator.Operator;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.dataset.VtlComponentId;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.VtlExpression;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.Map;

public class VtlCodeItemRelation implements Serializable {

    private VtlExpression leftCondition;
    private String leftCodeItem;
    private Operator comparator;
    private LinkedList<VtlCodeItem> vtlCodeItems = new LinkedList<>();
    private Map<String, VtlComponentId> parameterUsed;


    public VtlExpression getLeftCondition() {
        return leftCondition;
    }

    public void setLeftCondition(VtlExpression leftCondition) {
        this.leftCondition = leftCondition;
    }

    public String getLeftCodeItem() {
        return leftCodeItem;
    }

    public void setLeftCodeItem(String leftCodeItem) {
        this.leftCodeItem = leftCodeItem;
    }

    public Operator getComparator() {
        return comparator;
    }

    public void setComparator(Operator comparator) {
        this.comparator = comparator;
    }

    public LinkedList<VtlCodeItem> getVtlCodeItems() {
        return vtlCodeItems;
    }

    public void setVtlCodeItems(LinkedList<VtlCodeItem> vtlCodeItems) {
        this.vtlCodeItems = vtlCodeItems;
    }

    public Map<String, VtlComponentId> getParameterUsed() {
        return parameterUsed;
    }

    public void setParameterUsed(Map<String, VtlComponentId> parameterUsed) {
        this.parameterUsed = parameterUsed;
    }
}
