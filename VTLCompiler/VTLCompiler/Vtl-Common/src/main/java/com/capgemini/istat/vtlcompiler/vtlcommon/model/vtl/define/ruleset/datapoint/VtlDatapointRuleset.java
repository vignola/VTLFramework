package com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.define.ruleset.datapoint;

import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.define.VtlDefineFunction;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.define.ruleset.VarParameter;

import java.io.Serializable;
import java.util.LinkedList;

public class VtlDatapointRuleset extends VtlDefineFunction implements Serializable {

    private boolean isValueDomainParameter = false;
    private boolean isVariableParameter = false;
    private LinkedList<VarParameter> signatures = new LinkedList<>();
    private LinkedList<VtlDPRule> dpRules = new LinkedList<>();


    public LinkedList<VarParameter> getSignatures() {
        return signatures;
    }

    public void setSignatures(LinkedList<VarParameter> signatures) {
        this.signatures = signatures;
    }

    public LinkedList<VtlDPRule> getDpRules() {
        return dpRules;
    }

    public void setDpRules(LinkedList<VtlDPRule> dpRules) {
        this.dpRules = dpRules;
    }

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
}
