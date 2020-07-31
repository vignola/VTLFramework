package com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.booleans;

import com.capgemini.istat.vtlcompiler.vtlcommon.model.operator.Operator;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.VtlExpression;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.function.VtlBinaryExpression;

import java.io.Serializable;

public class VtlBooleanBinaryExpression extends VtlBinaryExpression implements Serializable {

    public VtlBooleanBinaryExpression() {
        super();
    }

    public VtlBooleanBinaryExpression(VtlExpression leftExpression, VtlExpression rightExpression, Operator operator) {
        super(leftExpression, rightExpression, operator);
    }
}
