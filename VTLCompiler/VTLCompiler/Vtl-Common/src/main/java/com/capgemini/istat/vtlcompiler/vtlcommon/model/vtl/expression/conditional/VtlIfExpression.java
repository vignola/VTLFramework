package com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.conditional;

import com.capgemini.istat.vtlcompiler.vtlcommon.model.operator.Operator;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.VtlExpression;

import java.io.Serializable;

public class VtlIfExpression extends VtlExpression implements Serializable {
    private VtlExpression condition;
    private VtlExpression thenExpr;
    private VtlExpression elseExpr;
    private Operator conditionalOperator;

    public VtlExpression getCondition() {
        return condition;
    }

    public void setCondition(VtlExpression condition) {
        this.condition = condition;
    }

    public VtlExpression getThenExpr() {
        return thenExpr;
    }

    public void setThenExpr(VtlExpression thenExpr) {
        this.thenExpr = thenExpr;
    }

    public VtlExpression getElseExpr() {
        return elseExpr;
    }

    public void setElseExpr(VtlExpression elseExpr) {
        this.elseExpr = elseExpr;
    }

    public Operator getConditionalOperator() {
        return conditionalOperator;
    }

    public void setConditionalOperator(Operator conditionalOperator) {
        this.conditionalOperator = conditionalOperator;
    }


}
