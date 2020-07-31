package com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.comparison;

import com.capgemini.istat.vtlcompiler.vtlcommon.model.operator.Operator;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.VtlExpression;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.function.VtlBinaryExpression;

import java.io.Serializable;


public class VtlComparisonBinaryExpression extends VtlBinaryExpression implements Serializable {

    public VtlComparisonBinaryExpression() {
        super();
    }

    public VtlComparisonBinaryExpression(VtlExpression leftExpression, VtlExpression rightExpression, Operator operator) {
        super(leftExpression, rightExpression, operator);
    }
}
