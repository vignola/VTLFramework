package com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.define.operator;

import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.define.VtlDefineFunction;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.define.operator.datatype.VtlDataType;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.VtlExpression;

import java.io.Serializable;
import java.util.LinkedList;

public class VtlUserFunction extends VtlDefineFunction implements Serializable {


    private LinkedList<VtlFunctionParameter> vtlFunctionParameters = new LinkedList<>();
    private transient VtlDataType outputType;
    private VtlExpression vtlExpression;


    public LinkedList<VtlFunctionParameter> getVtlFunctionParameters() {
        return vtlFunctionParameters;
    }

    public void setVtlFunctionParameters(LinkedList<VtlFunctionParameter> vtlFunctionParameters) {
        this.vtlFunctionParameters = vtlFunctionParameters;
    }

    public VtlDataType getOutputType() {
        return outputType;
    }

    public void setOutputType(VtlDataType outputType) {
        this.outputType = outputType;
    }

    public VtlExpression getVtlExpression() {
        return vtlExpression;
    }

    public void setVtlExpression(VtlExpression vtlExpression) {
        this.vtlExpression = vtlExpression;
    }
}
