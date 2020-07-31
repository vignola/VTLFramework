package com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.constant;


import java.io.Serializable;

public class VtlDuration extends VtlConstant<Character> implements Serializable {
    public final static String VTL_OBJECT_TYPE = "VtlDuration";
    private Character value;

    @Override
    public Character getValue() {
        return value;
    }
    
    @Override
    public Character getFormattedValue() {
        return getValue();
    }
    
    @Override
    public void setValue(Character value) {

        this.value = value;
    }

    @Override
    public String getType() {
        return VTL_OBJECT_TYPE;
    }


}
