package com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.constant;

import java.io.Serializable;

public class VtlString extends VtlConstant<String> implements Serializable {

    public final static String VTL_OBJECT_TYPE = "VtlString";
    private String value;

    @Override
    public String getValue() {
        return value == null ? "" : value;
    }
    
    @Override
    public String getFormattedValue() {
        return value == null ? "" : "'"+value+"'";
    }
    
    @Override
    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String getType() {
        return VTL_OBJECT_TYPE;
    }
}
