package com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.numeric;

import com.capgemini.istat.vtlcompiler.vtlcommon.model.operator.Operator;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.VtlExpression;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.function.VtlUnaryExpression;

import java.io.Serializable;

public class VtlNumericUnaryExpression extends VtlUnaryExpression implements Serializable {

    public VtlNumericUnaryExpression() {
    }

    public VtlNumericUnaryExpression(VtlExpression vtlExpression, Operator operator) {
        super(vtlExpression, operator);
    }
}
