package com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.constant;


import java.io.Serializable;

public class VtlBoolean extends VtlConstant<Boolean> implements Serializable {

    public final static String VTL_OBJECT_TYPE = "VtlBoolean";
    private Boolean value;

    @Override
    public Boolean getValue() {
        return value;
    }

    @Override
    public Boolean getFormattedValue() {
        return getValue();
    }

    @Override
    public void setValue(Boolean value) {
        this.value = value;
    }

    @Override
    public String getType() {
        return VTL_OBJECT_TYPE;
    }

}
