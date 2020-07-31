package com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.validation;

import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.dataset.VtlComponentId;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.define.ruleset.datapoint.VtlDatapointRuleset;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.VtlExpression;

import java.io.Serializable;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class VtlCheckDatapoint extends VtlExpression implements Serializable {

    private VtlExpression datasetToCheck;
    private String rulesetName;
    private LinkedList<VtlComponentId> componentIds = new LinkedList<>();
    private OutputMode outputMode;
    private Map<String, VtlExpression> parameterMapping = new HashMap<>();
    private VtlDatapointRuleset vtlDatapointRuleset;

    public VtlExpression getDatasetToCheck() {
        return datasetToCheck;
    }

    public void setDatasetToCheck(VtlExpression datasetToCheck) {
        this.datasetToCheck = datasetToCheck;
    }

    public String getRulesetName() {
        return rulesetName;
    }

    public void setRulesetName(String rulesetName) {
        this.rulesetName = rulesetName;
    }

    public LinkedList<VtlComponentId> getComponentIds() {
        return componentIds;
    }

    public void setComponentIds(LinkedList<VtlComponentId> componentIds) {
        this.componentIds = componentIds;
    }

    public OutputMode getOutputMode() {
        return outputMode;
    }

    public void setOutputMode(OutputMode outputMode) {
        this.outputMode = outputMode;
    }

    public VtlDatapointRuleset getVtlDatapointRuleset() {
        return vtlDatapointRuleset;
    }

    public void setVtlDatapointRuleset(VtlDatapointRuleset vtlDatapointRuleset) {
        this.vtlDatapointRuleset = vtlDatapointRuleset;
    }

    public Map<String, VtlExpression> getParameterMapping() {
        return parameterMapping;
    }

    public void setParameterMapping(Map<String, VtlExpression> parameterMapping) {
        this.parameterMapping = parameterMapping;
    }
}
