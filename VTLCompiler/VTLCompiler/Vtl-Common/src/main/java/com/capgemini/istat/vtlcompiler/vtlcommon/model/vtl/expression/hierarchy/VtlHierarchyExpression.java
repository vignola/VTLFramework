package com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.hierarchy;

import com.capgemini.istat.vtlcompiler.vtlcommon.model.operator.Operator;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.semantic.ResultExpression;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.constant.VtlConstant;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.constant.VtlConstantExpression;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.constant.VtlString;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.dataset.VtlComponentId;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.define.ruleset.hierarchical.VtlCodeItem;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.define.ruleset.hierarchical.VtlCodeItemRelation;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.define.ruleset.hierarchical.VtlHRRule;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.define.ruleset.hierarchical.VtlHierarchicalRuleset;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.VtlExpression;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.clause.pivot.VtlPivotClauseExpression;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.validation.InputMode;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.validation.OutputMode;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.validation.ValidationMode;

import java.io.Serializable;
import java.util.*;

public class VtlHierarchyExpression extends VtlExpression implements Serializable {

    private VtlExpression vtlOperandExpression;
    private String hrName;
    private LinkedList<VtlComponentId> componentIds = new LinkedList<>();
    private VtlComponentId ruleComponent;
    private ValidationMode validationMode;
    private InputMode inputMode;
    private OutputMode outputMode;
    private VtlHierarchicalRuleset vtlHierarchicalRuleset;
    private Map<String, VtlExpression> parameterMapping = new HashMap<>();
    private VtlPivotClauseExpression customPivot;


    public VtlExpression getVtlOperandExpression() {
        return vtlOperandExpression;
    }

    public void setVtlOperandExpression(VtlExpression vtlOperandExpression) {
        this.vtlOperandExpression = vtlOperandExpression;
    }

    public String getHrName() {
        return hrName;
    }

    public void setHrName(String hrName) {
        this.hrName = hrName;
    }

    public LinkedList<VtlComponentId> getComponentIds() {
        return componentIds;
    }

    public void setComponentIds(LinkedList<VtlComponentId> componentIds) {
        this.componentIds = componentIds;
    }

    public VtlComponentId getRuleComponent() {
        return ruleComponent;
    }

    public void setRuleComponent(VtlComponentId ruleComponent) {
        this.ruleComponent = ruleComponent;
    }

    public ValidationMode getValidationMode() {
        return validationMode;
    }

    public void setValidationMode(ValidationMode validationMode) {
        this.validationMode = validationMode;
    }

    public InputMode getInputMode() {
        return inputMode;
    }

    public void setInputMode(InputMode inputMode) {
        this.inputMode = inputMode;
    }

    public OutputMode getOutputMode() {
        return outputMode;
    }

    public void setOutputMode(OutputMode outputMode) {
        this.outputMode = outputMode;
    }

    public VtlHierarchicalRuleset getVtlHierarchicalRuleset() {
        return vtlHierarchicalRuleset;
    }

    public void setVtlHierarchicalRuleset(VtlHierarchicalRuleset vtlHierarchicalRuleset) {
        this.vtlHierarchicalRuleset = vtlHierarchicalRuleset;
    }

    public Map<String, VtlExpression> getParameterMapping() {
        return parameterMapping;
    }

    public void setParameterMapping(Map<String, VtlExpression> parameterMapping) {
        this.parameterMapping = parameterMapping;
    }

    public VtlPivotClauseExpression getCustomPivot() {
        return customPivot;
    }

    public void setCustomPivot(VtlPivotClauseExpression customPivot) {
        this.customPivot = customPivot;
    }

    public void addPivot() {
        VtlPivotClauseExpression vtlPivotClauseExpression = new VtlPivotClauseExpression();
        vtlPivotClauseExpression.setVtlDataset(vtlOperandExpression);
        vtlPivotClauseExpression.setIdentifier(getRuleComponent());
        vtlPivotClauseExpression.setOperator(Operator.PIVOT);
        VtlComponentId vtlComponentIdMeasure = new VtlComponentId();
        vtlComponentIdMeasure.setComponentName(vtlOperandExpression.getResultExpression().getResult().getMeasures().get(0).getName());
        ResultExpression resultExpression = new ResultExpression();
        resultExpression.setResultComponent(vtlOperandExpression.getResultExpression().getResult().getMeasures().get(0));
        vtlComponentIdMeasure.setResultExpression(resultExpression);
        vtlPivotClauseExpression.setMeasure(vtlComponentIdMeasure);
        List<String> vtlConstantPivot = new ArrayList<>();
        for (VtlHRRule vtlHRRule : vtlHierarchicalRuleset.getVtlHRRules()) {
            VtlCodeItemRelation vtlCodeItemRelation = vtlHRRule.getVtlCodeItemRelation();
            if (!vtlConstantPivot.contains(vtlCodeItemRelation.getLeftCodeItem())) {
                vtlConstantPivot.add(vtlCodeItemRelation.getLeftCodeItem());
            }
            for (VtlCodeItem vtlCodeItem : vtlCodeItemRelation.getVtlCodeItems()) {
                if (!vtlConstantPivot.contains(vtlCodeItem.getCodeItem())) {
                    vtlConstantPivot.add(vtlCodeItem.getCodeItem());
                }
            }
        }
        List<VtlConstantExpression> vtlConstantExpressions = new ArrayList<>();
        for (String constantPivot : vtlConstantPivot) {
            VtlConstantExpression vtlConstantExpression = new VtlConstantExpression();
            //TODO altri tipi oltre a stringa
            VtlConstant vtlConstant = new VtlString();
            vtlConstant.setValue(constantPivot);
            vtlConstantExpression.setVtlConstant(vtlConstant);
            vtlConstantExpressions.add(vtlConstantExpression);
        }
        vtlPivotClauseExpression.setConstantExpressions(vtlConstantExpressions);
        this.customPivot = vtlPivotClauseExpression;
    }

}
