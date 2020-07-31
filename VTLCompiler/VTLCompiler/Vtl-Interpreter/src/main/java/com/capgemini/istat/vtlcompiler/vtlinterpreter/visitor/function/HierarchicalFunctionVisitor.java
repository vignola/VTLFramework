package com.capgemini.istat.vtlcompiler.vtlinterpreter.visitor.function;

import com.capgemini.istat.vtlcompiler.vtlcommon.model.operator.Operator;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.hierarchy.VtlHierarchyExpression;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.validation.InputMode;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.validation.OutputMode;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.validation.ValidationMode;
import com.capgemini.istat.vtlcompiler.vtlinterpreter.visitor.InterpreterVisitor;
import com.capgemini.istat.vtlcompiler.vtlinterpreter.visitor.component.ComponentIdVisitor;
import com.capgemini.istat.vtlcompiler.vtlinterpreter.visitor.expression.ExpressionVisitor;
import com.capgemini.istat.vtlcompiler.vtlparser.VtlParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class HierarchicalFunctionVisitor extends InterpreterVisitor {
    private ExpressionVisitor expressionVisitor;
    private ComponentIdVisitor componentIdVisitor;

    @Autowired
    public void setExpressionVisitor(ExpressionVisitor expressionVisitor) {
        this.expressionVisitor = expressionVisitor;
    }

    @Autowired
    public void setComponentIdVisitor(ComponentIdVisitor componentIdVisitor) {
        this.componentIdVisitor = componentIdVisitor;
    }

    @Override
    public VtlHierarchyExpression visitHierarchyOperators(VtlParser.HierarchyOperatorsContext ctx) {
        VtlHierarchyExpression vtlHierarchyExpression = new VtlHierarchyExpression();
        vtlHierarchyExpression.setVtlOperandExpression(
                expressionVisitor.visit(ctx.expr())
        );
        vtlHierarchyExpression.setHrName(ctx.hrName.getText());
        if (ctx.conditionClause() != null) {
            for (VtlParser.ComponentIDContext componentIDContext : ctx.conditionClause().componentID()) {
                vtlHierarchyExpression.getComponentIds().addLast(
                        componentIdVisitor.visitComponentID(componentIDContext)
                );
            }
        }
        if (ctx.componentID() != null) {
            vtlHierarchyExpression.setRuleComponent(
                    componentIdVisitor.visitComponentID(ctx.componentID())
            );
        }
        if (ctx.validationMode() != null) {
            if (ctx.validationMode().ALWAYS_NULL() != null) {
                vtlHierarchyExpression.setValidationMode(ValidationMode.ALWAYS_NULL);
            } else if (ctx.validationMode().ALWAYS_ZERO() != null) {
                vtlHierarchyExpression.setValidationMode(ValidationMode.ALWAYS_ZERO);
            } else if (ctx.validationMode().PARTIAL_NULL() != null) {
                vtlHierarchyExpression.setValidationMode(ValidationMode.PARTIAL_NULL);
            } else if (ctx.validationMode().PARTIAL_ZERO() != null) {
                vtlHierarchyExpression.setValidationMode(ValidationMode.PARTIAL_ZERO);
            } else if (ctx.validationMode().NON_ZERO() != null) {
                vtlHierarchyExpression.setValidationMode(ValidationMode.NON_ZERO);
            } else {
                vtlHierarchyExpression.setValidationMode(ValidationMode.NON_NULL);
            }
        } else {
            vtlHierarchyExpression.setValidationMode(ValidationMode.NON_NULL);
        }
        if (ctx.inputModeHierarchy() != null) {

            if (ctx.inputModeHierarchy().DATASET() != null) {
                vtlHierarchyExpression.setInputMode(InputMode.DATASET);
            } else if (ctx.inputModeHierarchy().RULE_PRIORITY() != null) {
                vtlHierarchyExpression.setInputMode(InputMode.RULE_PRIORITY);
            } else {
                vtlHierarchyExpression.setInputMode(InputMode.RULE);
            }
        } else {
            vtlHierarchyExpression.setInputMode(InputMode.RULE);
        }

        if (ctx.outputModeHierarchy() != null) {
            if (ctx.outputModeHierarchy().ALL() != null) {
                vtlHierarchyExpression.setOutputMode(OutputMode.ALL);
            } else {
                vtlHierarchyExpression.setOutputMode(OutputMode.COMPUTED);
            }
        } else {
            vtlHierarchyExpression.setOutputMode(OutputMode.COMPUTED);
        }
        vtlHierarchyExpression.setOperator(Operator.HIERARCHY);
        return vtlHierarchyExpression;
    }
}
