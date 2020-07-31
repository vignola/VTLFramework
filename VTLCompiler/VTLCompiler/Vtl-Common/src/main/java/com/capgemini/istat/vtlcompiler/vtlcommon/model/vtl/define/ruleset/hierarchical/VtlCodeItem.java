package com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.define.ruleset.hierarchical;

import com.capgemini.istat.vtlcompiler.vtlcommon.model.operator.Operator;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.VtlExpression;

import java.io.Serializable;

public class VtlCodeItem extends VtlExpression implements Serializable {

    private Operator operator;
    private String codeItem;
    private VtlExpression condition;

    @Override
    public Operator getOperator() {
        return operator;
    }

    @Override
    public void setOperator(Operator operator) {
        this.operator = operator;
    }

    public String getCodeItem() {
        return codeItem;
    }

    public void setCodeItem(String codeItem) {
        this.codeItem = codeItem;
    }

    public VtlExpression getCondition() {
        return condition;
    }

    public void setCondition(VtlExpression condition) {
        this.condition = condition;
    }
}
