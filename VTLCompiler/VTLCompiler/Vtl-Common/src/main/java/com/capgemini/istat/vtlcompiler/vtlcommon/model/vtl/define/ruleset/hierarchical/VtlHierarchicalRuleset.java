package com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.define.ruleset.hierarchical;

import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.define.VtlDefineFunction;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.define.ruleset.VarParameter;

import java.io.Serializable;
import java.util.LinkedList;

public class VtlHierarchicalRuleset extends VtlDefineFunction implements Serializable {

    private boolean isValueDomainParameter = false;
    private boolean isVariableParameter = false;
    private LinkedList<VarParameter> signatures = new LinkedList<>();
    private String ruleVar;
    private LinkedList<VtlHRRule> vtlHRRules = new LinkedList<>();


    public boolean isValueDomainParameter() {
        return isValueDomainParameter;
    }

    public void setValueDomainParameter(boolean valueDomainParameter) {
        isValueDomainParameter = valueDomainParameter;
    }

    public boolean isVariableParameter() {
        return isVariableParameter;
    }

    public void setVariableParameter(boolean variableParameter) {
        isVariableParameter = variableParameter;
    }

    public LinkedList<VarParameter> getSignatures() {
        return signatures;
    }

    public void setSignatures(LinkedList<VarParameter> signatures) {
        this.signatures = signatures;
    }

    public String getRuleVar() {
        return ruleVar;
    }

    public void setRuleVar(String ruleVar) {
        this.ruleVar = ruleVar;
    }

    public LinkedList<VtlHRRule> getVtlHRRules() {
        return vtlHRRules;
    }

    public void setVtlHRRules(LinkedList<VtlHRRule> vtlHRRules) {
        this.vtlHRRules = vtlHRRules;
    }
}
