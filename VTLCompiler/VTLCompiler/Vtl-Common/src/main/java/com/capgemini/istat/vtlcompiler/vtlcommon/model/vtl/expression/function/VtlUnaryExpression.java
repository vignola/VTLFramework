package com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.function;

import com.capgemini.istat.vtlcompiler.vtlcommon.model.operator.Operator;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.VtlExpression;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class VtlUnaryExpression extends VtlExpression implements Serializable {
    private VtlExpression vtlExpression;
    private List<VtlExpression> optionalExpression;
    private Operator optionalOperator;

    public VtlUnaryExpression() {

    }

    public VtlUnaryExpression(VtlExpression vtlExpression, Operator optionalOperator) {
        this.vtlExpression = vtlExpression;
        this.optionalOperator = optionalOperator;
    }


    public VtlExpression getVtlExpression() {
        return vtlExpression;
    }

    public void setVtlExpression(VtlExpression vtlExpression) {
        this.vtlExpression = vtlExpression;
    }


    public List<VtlExpression> getOptionalExpression() {
        if (optionalExpression == null)
            this.optionalExpression = new ArrayList<>();
        return optionalExpression;
    }

    public void setOptionalExpression(List<VtlExpression> optionalExpression) {
        this.optionalExpression = optionalExpression;
    }

    public Operator getOptionalOperator() {
        return optionalOperator;
    }

    public void setOptionalOperator(Operator optionalOperator) {
        this.optionalOperator = optionalOperator;
    }
}
