package com.capgemini.istat.vtlcompiler.vtlinterpreter.visitor.define;

import com.capgemini.istat.vtlcompiler.vtlcommon.model.define.VtlUserFunctionType;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.define.VtlDefineFunction;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.define.operator.VtlUserFunction;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.define.ruleset.datapoint.VtlDatapointRuleset;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.define.ruleset.hierarchical.VtlHierarchicalRuleset;
import com.capgemini.istat.vtlcompiler.vtlinterpreter.visitor.InterpreterVisitor;
import com.capgemini.istat.vtlcompiler.vtlinterpreter.visitor.expression.ExpressionVisitor;
import com.capgemini.istat.vtlcompiler.vtlparser.VtlParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DefineVisitor extends InterpreterVisitor<VtlDefineFunction> {
    private ExpressionVisitor expressionVisitor;
    private FunctionParameterVisitor functionParameterVisitor;
    private RuleVisitor ruleVisitor;

    @Autowired
    public void setExpressionVisitor(ExpressionVisitor expressionVisitor) {
        this.expressionVisitor = expressionVisitor;
    }

    @Autowired
    public void setFunctionParameterVisitor(FunctionParameterVisitor functionParameterVisitor) {
        this.functionParameterVisitor = functionParameterVisitor;
    }

    @Autowired
    public void setRuleVisitor(RuleVisitor ruleVisitor) {
        this.ruleVisitor = ruleVisitor;
    }

    @Override
    public VtlUserFunction visitDefOperator(VtlParser.DefOperatorContext ctx) {
        VtlUserFunction vtlUserFunction = new VtlUserFunction();
        vtlUserFunction.setFunctionType(VtlUserFunctionType.OPERATOR_FUNCTION);

        vtlUserFunction.setFunctionName(ctx.operatorID().IDENTIFIER().getText());


        if (ctx.parameterItem() != null && ctx.parameterItem().size() != 0) {
            for (VtlParser.ParameterItemContext parameterItemContext : ctx.parameterItem()) {
                vtlUserFunction.getVtlFunctionParameters().addLast(
                        functionParameterVisitor.visitParameterItem(parameterItemContext)
                );
            }
        }
        if (ctx.outputParameterType() != null) {
            vtlUserFunction.setOutputType(
                    functionParameterVisitor.visitOutputParameterType(ctx.outputParameterType())
            );
        }
        vtlUserFunction.setVtlExpression(
                expressionVisitor.visit(ctx.expr())
        );
        return vtlUserFunction;
    }

    @Override
    public VtlDatapointRuleset visitDefDatapointRuleset(VtlParser.DefDatapointRulesetContext ctx) {
        VtlDatapointRuleset vtlDatapointRuleset = new VtlDatapointRuleset();
        vtlDatapointRuleset.setFunctionType(VtlUserFunctionType.DATAPOINT_FUNCTION);
        vtlDatapointRuleset.setFunctionName(
                ctx.rulesetID().getText()
        );
        if (ctx.rulesetSignature() != null) {
            vtlDatapointRuleset.setValueDomainParameter(ctx.rulesetSignature().VALUE_DOMAIN() != null);
            vtlDatapointRuleset.setVariableParameter(ctx.rulesetSignature().VARIABLE() != null);
            for (VtlParser.SignatureContext signature : ctx.rulesetSignature().signature()) {
                vtlDatapointRuleset.getSignatures().addLast(functionParameterVisitor.visitSignature(signature));
            }
        }
        if (ctx.ruleClauseDatapoint() != null && ctx.ruleClauseDatapoint().ruleItemDatapoint() != null) {
            for (VtlParser.RuleItemDatapointContext ruleItemDatapointContext : ctx.ruleClauseDatapoint().ruleItemDatapoint()) {
                vtlDatapointRuleset.getDpRules().addLast(
                        ruleVisitor.visitRuleItemDatapoint(ruleItemDatapointContext)
                );
            }
        }
        return vtlDatapointRuleset;
    }

    @Override
    public VtlHierarchicalRuleset visitDefHierarchical(VtlParser.DefHierarchicalContext ctx) {
        VtlHierarchicalRuleset vtlHierarchicalRuleset = new VtlHierarchicalRuleset();
        vtlHierarchicalRuleset.setFunctionType(VtlUserFunctionType.HIERARCHICAL_FUNCTION);
        vtlHierarchicalRuleset.setFunctionName(
                ctx.rulesetID().IDENTIFIER().getText()
        );
        if (ctx.hierRuleSignature() != null) {
            vtlHierarchicalRuleset.setValueDomainParameter(ctx.hierRuleSignature().VALUE_DOMAIN() != null);
            vtlHierarchicalRuleset.setVariableParameter(ctx.hierRuleSignature().VARIABLE() != null);
            if (ctx.hierRuleSignature().valueDomainSignature() != null) {
                for (VtlParser.SignatureContext signature : ctx.hierRuleSignature().valueDomainSignature().signature()) {
                    vtlHierarchicalRuleset.getSignatures().addLast(functionParameterVisitor.visitSignature(signature));
                }
            }
            vtlHierarchicalRuleset.setRuleVar(ctx.hierRuleSignature().IDENTIFIER().getText());
        }
        if (ctx.ruleClauseHierarchical() != null && ctx.ruleClauseHierarchical().ruleItemHierarchical() != null) {
            for (VtlParser.RuleItemHierarchicalContext ruleItem : ctx.ruleClauseHierarchical().ruleItemHierarchical()) {
                vtlHierarchicalRuleset.getVtlHRRules().addLast(
                        ruleVisitor.visitRuleItemHierarchical(ruleItem)
                );
            }
        }
        return vtlHierarchicalRuleset;
    }


}
