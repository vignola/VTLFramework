package com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.constant;

import java.io.Serializable;

public class VtlFloat extends VtlConstant<Float> implements Serializable {

    Float value;

    @Override
    public Float getValue() {
        return value == null ? 0 : value;
    }
    
    @Override
    public Float getFormattedValue() {
        return value;
    }
    
    @Override
    public void setValue(Float value) {
        this.value = value;
    }

    @Override
    public String getType() {
        return null;
    }
}
