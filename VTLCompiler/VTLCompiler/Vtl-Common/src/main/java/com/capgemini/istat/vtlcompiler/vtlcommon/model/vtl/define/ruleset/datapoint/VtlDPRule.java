package com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.define.ruleset.datapoint;


import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.define.ruleset.VtlErrorRuleset;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.VtlExpression;

import java.io.Serializable;

public class VtlDPRule implements Serializable {

    private String ruleName;
    private VtlExpression antecedentCondition;
    private VtlExpression consequentCondition;
    private VtlErrorRuleset vtlErrorRuleset;

    public String getRuleName() {
        return ruleName;
    }

    public void setRuleName(String ruleName) {
        this.ruleName = ruleName;
    }

    public VtlExpression getAntecedentCondition() {
        return antecedentCondition;
    }

    public void setAntecedentCondition(VtlExpression antecedentCondition) {
        this.antecedentCondition = antecedentCondition;
    }

    public VtlExpression getConsequentCondition() {
        return consequentCondition;
    }

    public void setConsequentCondition(VtlExpression consequentCondition) {
        this.consequentCondition = consequentCondition;
    }

    public VtlErrorRuleset getVtlErrorRuleset() {
        return vtlErrorRuleset;
    }

    public void setVtlErrorRuleset(VtlErrorRuleset vtlErrorRuleset) {
        this.vtlErrorRuleset = vtlErrorRuleset;
    }
}
