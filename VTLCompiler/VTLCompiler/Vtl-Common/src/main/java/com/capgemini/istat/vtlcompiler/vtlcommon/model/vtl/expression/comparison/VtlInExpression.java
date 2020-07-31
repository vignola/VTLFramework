package com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.comparison;

import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.constant.VtlConstantExpression;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.VtlExpression;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class VtlInExpression extends VtlExpression implements Serializable {
    private VtlExpression vtlElement;
    private List<VtlConstantExpression> vtlConstantList;
    private String valueDomain;

    public VtlExpression getVtlElement() {
        return vtlElement;
    }

    public void setVtlElement(VtlExpression vtlElement) {
        this.vtlElement = vtlElement;
    }

    public List<VtlConstantExpression> getVtlConstantList() {
        if (vtlConstantList == null)
            this.vtlConstantList = new ArrayList<>();
        return vtlConstantList;
    }

    public void setVtlConstantList(List<VtlConstantExpression> vtlConstantList) {
        this.vtlConstantList = vtlConstantList;
    }

    public String getValueDomain() {
        return valueDomain;
    }

    public void setValueDomain(String valueDomain) {
        this.valueDomain = valueDomain;
    }
}
