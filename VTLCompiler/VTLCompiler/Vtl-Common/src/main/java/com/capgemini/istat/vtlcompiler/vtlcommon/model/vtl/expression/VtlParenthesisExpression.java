package com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression;

import java.io.Serializable;

public class VtlParenthesisExpression extends VtlExpression implements Serializable {

    private VtlExpression expression;

    public VtlParenthesisExpression() {
        this.expression = null;
    }

    @Override
    public String getType() {
        return VtlParenthesisExpression.class.toString();
    }

    public VtlExpression getExpression() {
        return expression;
    }

    public void setExpression(VtlExpression expression) {
        this.expression = expression;
    }


}
