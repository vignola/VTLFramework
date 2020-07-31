package com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.generic;

import com.capgemini.istat.vtlcompiler.vtlcommon.model.dataset.VtlType;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.VtlExpression;

import java.io.Serializable;

public class VtlCastExpression extends VtlExpression implements Serializable {
    private VtlExpression vtlExpression;
    private VtlType vtlType;
    private String valueDomainName;
    private String mask;

    public VtlExpression getVtlExpression() {
        return vtlExpression;
    }

    public void setVtlExpression(VtlExpression vtlExpression) {
        this.vtlExpression = vtlExpression;
    }

    public VtlType getVtlType() {
        return vtlType;
    }

    public void setVtlType(VtlType vtlType) {
        this.vtlType = vtlType;
    }

    public String getValueDomainName() {
        return valueDomainName;
    }

    public void setValueDomainName(String valueDomainName) {
        this.valueDomainName = valueDomainName;
    }

    public String getMask() {
        return mask;
    }

    public void setMask(String mask) {
        this.mask = mask;
    }


}
