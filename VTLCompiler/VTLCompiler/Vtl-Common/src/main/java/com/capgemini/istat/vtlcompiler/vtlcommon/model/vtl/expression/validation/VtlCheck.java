package com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.validation;

import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.define.ruleset.VtlErrorRuleset;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.VtlExpression;

import java.io.Serializable;

public class VtlCheck extends VtlExpression implements Serializable {
    private VtlExpression booleanDataset;
    private VtlErrorRuleset errorRuleset;
    private VtlExpression imbalanceExpression;
    private OutputMode outputMode;

    public VtlExpression getBooleanDataset() {
        return booleanDataset;
    }

    public void setBooleanDataset(VtlExpression booleanDataset) {
        this.booleanDataset = booleanDataset;
    }

    public VtlErrorRuleset getErrorRuleset() {
        return errorRuleset;
    }

    public void setErrorRuleset(VtlErrorRuleset errorRuleset) {
        this.errorRuleset = errorRuleset;
    }

    public VtlExpression getImbalanceExpression() {
        return imbalanceExpression;
    }

    public void setImbalanceExpression(VtlExpression imbalanceExpression) {
        this.imbalanceExpression = imbalanceExpression;
    }

    public OutputMode getOutputMode() {
        return outputMode;
    }

    public void setOutputMode(OutputMode outputMode) {
        this.outputMode = outputMode;
    }
}
