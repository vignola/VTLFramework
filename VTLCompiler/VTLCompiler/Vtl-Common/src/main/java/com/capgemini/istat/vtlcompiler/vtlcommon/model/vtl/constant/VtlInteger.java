package com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.constant;

import java.io.Serializable;

public class VtlInteger extends VtlConstant<Integer> implements Serializable {

    public final static String VTL_OBJECT_TYPE = "VtlInteger";
    private Integer value;

    @Override
    public Integer getValue() {
        return value == null ? 0 : value;
    }
   
    @Override
    public Integer getFormattedValue() {
        return value;
    }
    
    @Override
    public void setValue(Integer value) {
        this.value = value;
    }

    @Override
    public String getType() {
        return VTL_OBJECT_TYPE;
    }
}
