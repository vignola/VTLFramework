package com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.define.operator;

import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.constant.VtlConstantExpression;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.define.operator.datatype.VtlDataType;

import java.io.Serializable;

public class VtlFunctionParameter implements Serializable {

    private String parameterName;
    private VtlDataType vtlDataType;
    private VtlConstantExpression defaultValue;

    public String getParameterName() {
        return parameterName;
    }

    public void setParameterName(String parameterName) {
        this.parameterName = parameterName;
    }

    public VtlDataType getVtlDataType() {
        return vtlDataType;
    }

    public void setVtlDataType(VtlDataType vtlDataType) {
        this.vtlDataType = vtlDataType;
    }

    public VtlConstantExpression getDefaultValue() {
        return defaultValue;
    }

    public void setDefaultValue(VtlConstantExpression defaultValue) {
        this.defaultValue = defaultValue;
    }
}
