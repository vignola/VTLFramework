package com.capgemini.istat.vtlcompiler.vtlinterpreter.visitor.function;

import com.capgemini.istat.vtlcompiler.vtlcommon.model.operator.Operator;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.define.ruleset.VtlErrorRuleset;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.VtlExpression;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.validation.*;
import com.capgemini.istat.vtlcompiler.vtlinterpreter.visitor.InterpreterVisitor;
import com.capgemini.istat.vtlcompiler.vtlinterpreter.visitor.component.ComponentIdVisitor;
import com.capgemini.istat.vtlcompiler.vtlinterpreter.visitor.expression.ExpressionVisitor;
import com.capgemini.istat.vtlcompiler.vtlinterpreter.visitor.terminal.ConstantVisitor;
import com.capgemini.istat.vtlcompiler.vtlparser.VtlParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidationVisitor extends InterpreterVisitor<VtlExpression> {
    private ExpressionVisitor expressionVisitor;
    private ComponentIdVisitor componentIdVisitor;
    private ConstantVisitor constantVisitor;

    @Autowired
    public void setExpressionVisitor(ExpressionVisitor expressionVisitor) {
        this.expressionVisitor = expressionVisitor;
    }

    @Autowired
    public void setComponentIdVisitor(ComponentIdVisitor componentIdVisitor) {
        this.componentIdVisitor = componentIdVisitor;
    }

    @Autowired
    public void setConstantVisitor(ConstantVisitor constantVisitor) {
        this.constantVisitor = constantVisitor;
    }

    @Override
    public VtlCheckDatapoint visitValidateDPruleset(VtlParser.ValidateDPrulesetContext ctx) {
        VtlCheckDatapoint vtlCheckDatapoint = new VtlCheckDatapoint();
        vtlCheckDatapoint.setDatasetToCheck(
                expressionVisitor.visit(ctx.expr())
        );
        vtlCheckDatapoint.setRulesetName(
                ctx.dpName.getText()
        );
        if (ctx.componentID() != null) {
            for (VtlParser.ComponentIDContext componentIDContext : ctx.componentID()) {
                vtlCheckDatapoint.getComponentIds().addLast(
                        componentIdVisitor.visitComponentID(componentIDContext)
                );
            }
        }
        if (ctx.validationOutput() != null) {
            if (ctx.validationOutput().ALL() != null) {
                vtlCheckDatapoint.setOutputMode(OutputMode.ALL);
            } else if (ctx.validationOutput().ALL_MEASURES() != null) {
                vtlCheckDatapoint.setOutputMode(OutputMode.ALL_MEASURE);
            } else {
                vtlCheckDatapoint.setOutputMode(OutputMode.INVALID);
            }
        } else {
            vtlCheckDatapoint.setOutputMode(OutputMode.INVALID);
        }
        return vtlCheckDatapoint;
    }

    @Override
    public VtlCheckHierarchy visitValidateHRruleset(VtlParser.ValidateHRrulesetContext ctx) {
        VtlCheckHierarchy vtlCheckHierarchy = new VtlCheckHierarchy();
        vtlCheckHierarchy.setVtlVarId(
                expressionVisitor.visit(ctx.expr())
        );
        vtlCheckHierarchy.setHrRule(
                ctx.hrName.getText()
        );
        if (ctx.conditionClause() != null) {
            for (VtlParser.ComponentIDContext componentIDContext : ctx.conditionClause().componentID()) {
                vtlCheckHierarchy.getConditionComponents().addLast(
                        componentIdVisitor.visitComponentID(componentIDContext)
                );
            }
        }
        if (ctx.componentID() != null) {
            vtlCheckHierarchy.setRuleComponent(
                    componentIdVisitor.visitComponentID(ctx.componentID())
            );
        }
        if (ctx.inputMode() != null) {
            if (ctx.inputMode().DATASET_PRIORITY() != null) {
                vtlCheckHierarchy.setInputMode(InputMode.DATASET_PRIORITY);
            } else {
                vtlCheckHierarchy.setInputMode(InputMode.DATASET);
            }
        } else {
            vtlCheckHierarchy.setInputMode(InputMode.DATASET);
        }

        if (ctx.validationMode() != null) {
            if (ctx.validationMode().ALWAYS_NULL() != null) {
                vtlCheckHierarchy.setValidationMode(ValidationMode.ALWAYS_NULL);
            } else if (ctx.validationMode().ALWAYS_ZERO() != null) {
                vtlCheckHierarchy.setValidationMode(ValidationMode.ALWAYS_ZERO);
            } else if (ctx.validationMode().PARTIAL_NULL() != null) {
                vtlCheckHierarchy.setValidationMode(ValidationMode.PARTIAL_NULL);
            } else if (ctx.validationMode().PARTIAL_ZERO() != null) {
                vtlCheckHierarchy.setValidationMode(ValidationMode.PARTIAL_ZERO);
            } else if (ctx.validationMode().NON_ZERO() != null) {
                vtlCheckHierarchy.setValidationMode(ValidationMode.NON_ZERO);
            } else {
                vtlCheckHierarchy.setValidationMode(ValidationMode.NON_NULL);
            }
        } else {
            vtlCheckHierarchy.setValidationMode(ValidationMode.NON_NULL);
        }
        if (ctx.validationOutput() != null) {
            if (ctx.validationOutput().ALL() != null) {
                vtlCheckHierarchy.setOutputMode(OutputMode.ALL);
            } else if (ctx.validationOutput().ALL_MEASURES() != null) {
                vtlCheckHierarchy.setOutputMode(OutputMode.ALL_MEASURE);
            } else {
                vtlCheckHierarchy.setOutputMode(OutputMode.INVALID);
            }
        } else {
            vtlCheckHierarchy.setOutputMode(OutputMode.INVALID);
        }
        vtlCheckHierarchy.setOperator(Operator.CHECK_HIERARCHY);
        return vtlCheckHierarchy;
    }

    @Override
    public VtlCheck visitValidationSimple(VtlParser.ValidationSimpleContext ctx) {
        VtlCheck vtlCheck = new VtlCheck();
        vtlCheck.setBooleanDataset(
                expressionVisitor.visit(ctx.expr())
        );

        if (ctx.erCode() != null || ctx.erLevel() != null) {
            VtlErrorRuleset vtlErrorRuleset = new VtlErrorRuleset();
            if (ctx.erCode() != null)
                vtlErrorRuleset.setErrorCode(constantVisitor.visitConstant(ctx.erCode().constant()));
            if (ctx.erLevel() != null)
                vtlErrorRuleset.setErrorLevel(constantVisitor.visitConstant(ctx.erLevel().constant()));
            vtlCheck.setErrorRuleset(vtlErrorRuleset);
        }
        if (ctx.imbalanceExpr() != null) {
            vtlCheck.setImbalanceExpression(
                    expressionVisitor.visit(ctx.imbalanceExpr().expr())
            );
        }
        if (ctx.INVALID() != null) {
            vtlCheck.setOutputMode(OutputMode.INVALID);
        } else {
            vtlCheck.setOutputMode(OutputMode.ALL);
        }
        vtlCheck.setOperator(Operator.CHECK);
        return vtlCheck;
    }
}
