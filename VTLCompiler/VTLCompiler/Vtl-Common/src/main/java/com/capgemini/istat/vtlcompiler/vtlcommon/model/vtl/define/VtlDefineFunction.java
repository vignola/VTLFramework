package com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.define;


import com.capgemini.istat.vtlcompiler.vtlcommon.model.define.VtlUserFunctionType;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.define.operator.VtlUserFunction;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.define.ruleset.datapoint.VtlDatapointRuleset;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.define.ruleset.hierarchical.VtlHierarchicalRuleset;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.VtlExpression;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME)
@JsonSubTypes({
        @JsonSubTypes.Type(value = VtlHierarchicalRuleset.class),
        @JsonSubTypes.Type(value = VtlUserFunction.class),
        @JsonSubTypes.Type(value = VtlDatapointRuleset.class)

})
public abstract class VtlDefineFunction extends VtlExpression {

    public VtlDefineFunction() {
    }

    private String functionName;
    private VtlUserFunctionType functionType;

    public String getFunctionName() {
        return functionName;
    }

    public void setFunctionName(String functionName) {
        this.functionName = functionName;
    }

    public VtlUserFunctionType getFunctionType() {
        return functionType;
    }

    public void setFunctionType(VtlUserFunctionType functionType) {
        this.functionType = functionType;
    }
}
