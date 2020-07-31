package com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.define.ruleset.hierarchical;

import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.define.ruleset.VtlErrorRuleset;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.VtlExpression;

import java.io.Serializable;

public class VtlHRRule extends VtlExpression implements Serializable {

    private String ruleName;
    private VtlCodeItemRelation vtlCodeItemRelation;
    private VtlErrorRuleset errorRuleset;

    public String getRuleName() {
        return ruleName;
    }

    public void setRuleName(String ruleName) {
        this.ruleName = ruleName;
    }

    public VtlCodeItemRelation getVtlCodeItemRelation() {
        return vtlCodeItemRelation;
    }

    public void setVtlCodeItemRelation(VtlCodeItemRelation vtlCodeItemRelation) {
        this.vtlCodeItemRelation = vtlCodeItemRelation;
    }

    public VtlErrorRuleset getErrorRuleset() {
        return errorRuleset;
    }

    public void setErrorRuleset(VtlErrorRuleset errorRuleset) {
        this.errorRuleset = errorRuleset;
    }


}
