package com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.function;

import com.capgemini.istat.vtlcompiler.vtlcommon.model.operator.Operator;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.VtlExpression;

import java.io.Serializable;
import java.util.List;

public class VtlUnaryWithOneParameter extends VtlExpression implements Serializable {
    private VtlExpression vtlExpression;
    private VtlExpression parameterExpression;
    private Operator parameterOperator;
    private List<VtlExpression> optionalParameterExpression;
    private Operator optionalParameterOperator;


    public VtlExpression getVtlExpression() {
        return vtlExpression;
    }

    public void setVtlExpression(VtlExpression vtlExpression) {
        this.vtlExpression = vtlExpression;
    }

    public VtlExpression getParameterExpression() {
        return parameterExpression;
    }

    public void setParameterExpression(VtlExpression parameterExpression) {
        this.parameterExpression = parameterExpression;
    }

    public List<VtlExpression> getOptionalParameterExpression() {
        return optionalParameterExpression;
    }

    public void setOptionalParameterExpression(List<VtlExpression> optionalParameterExpression) {
        this.optionalParameterExpression = optionalParameterExpression;
    }

    public Operator getOptionalParameterOperator() {
        return optionalParameterOperator;
    }

    public void setOptionalParameterOperator(Operator optionalParameterOperator) {
        this.optionalParameterOperator = optionalParameterOperator;
    }

    public Operator getParameterOperator() {
        return parameterOperator;
    }

    public void setParameterOperator(Operator parameterOperator) {
        this.parameterOperator = parameterOperator;
    }
}
