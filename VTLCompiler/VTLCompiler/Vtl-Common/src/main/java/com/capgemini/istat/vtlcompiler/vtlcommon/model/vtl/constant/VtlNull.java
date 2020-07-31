package com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.constant;

import java.io.Serializable;

public class VtlNull extends VtlConstant implements Serializable {
    @Override
    public Object getValue() {
        return null;
    }

    @Override
    public Object getFormattedValue() {
        return null;
    }
    
    @Override
    public void setValue(Object value) {
        //non necessario
    }

    @Override
    public String getType() {
        return null;
    }
}
