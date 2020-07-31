package com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.numeric;

import com.capgemini.istat.vtlcompiler.vtlcommon.model.operator.Operator;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.VtlExpression;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.function.VtlBinaryExpression;

import java.io.Serializable;

public class VtlNumericBinaryExpression extends VtlBinaryExpression implements Serializable {

    public VtlNumericBinaryExpression() {

    }

    public VtlNumericBinaryExpression(VtlExpression leftExpression, VtlExpression rightExpression, Operator operator) {
        super(leftExpression, rightExpression, operator);
    }
}
