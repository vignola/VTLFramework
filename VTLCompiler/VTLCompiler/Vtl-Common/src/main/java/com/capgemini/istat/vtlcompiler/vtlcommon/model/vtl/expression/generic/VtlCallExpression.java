package com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.generic;

import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.define.operator.VtlUserFunction;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.VtlExpression;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.Map;

public class VtlCallExpression extends VtlExpression implements Serializable {

    private String functionName;
    private LinkedList<VtlExpression> parameters = new LinkedList<>();
    private VtlUserFunction vtlUserFunction;
    private Map<String, VtlExpression> parameterMap;

    public String getFunctionName() {
        return functionName;
    }

    public void setFunctionName(String functionName) {
        this.functionName = functionName;
    }

    public LinkedList<VtlExpression> getParameters() {
        return parameters;
    }

    public void setParameters(LinkedList<VtlExpression> parameters) {
        this.parameters = parameters;
    }

    public VtlUserFunction getVtlUserFunction() {
        return vtlUserFunction;
    }

    public void setVtlUserFunction(VtlUserFunction vtlUserFunction) {
        this.vtlUserFunction = vtlUserFunction;
    }

    public Map<String, VtlExpression> getParameterMap() {
        return parameterMap;
    }

    public void setParameterMap(Map<String, VtlExpression> parameterMap) {
        this.parameterMap = parameterMap;
    }
}
