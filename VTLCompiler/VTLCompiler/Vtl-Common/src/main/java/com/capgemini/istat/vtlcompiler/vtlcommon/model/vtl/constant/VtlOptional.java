package com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.constant;

import com.capgemini.istat.vtlcompiler.vtlcommon.model.operator.Operator;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.VtlExpression;

import java.io.Serializable;

public class VtlOptional extends VtlExpression implements Serializable {
    public final static String VTL_OBJECT_TYPE = "VtlOptional";
    private String value;

    public VtlOptional() {
        this.value = "_";
        setCommand(value);
        setOperator(Operator.OPTIONAL);
    }


    @Override
    public String getType() {
        return VTL_OBJECT_TYPE;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
