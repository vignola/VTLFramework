package com.capgemini.istat.vtlcompiler.vtlinterpreter.visitor.define;

import com.capgemini.istat.vtlcompiler.vtlcommon.model.operator.Operator;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.define.ruleset.VtlErrorRuleset;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.define.ruleset.datapoint.VtlDPRule;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.define.ruleset.hierarchical.VtlCodeItem;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.define.ruleset.hierarchical.VtlCodeItemRelation;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.define.ruleset.hierarchical.VtlHRRule;
import com.capgemini.istat.vtlcompiler.vtlinterpreter.visitor.InterpreterVisitor;
import com.capgemini.istat.vtlcompiler.vtlinterpreter.visitor.expression.ExpressionVisitor;
import com.capgemini.istat.vtlcompiler.vtlinterpreter.visitor.terminal.ConstantVisitor;
import com.capgemini.istat.vtlcompiler.vtlparser.VtlParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RuleVisitor extends InterpreterVisitor {
    private ExpressionVisitor expressionVisitor;
    private ConstantVisitor constantVisitor;

    @Autowired
    public void setExpressionVisitor(ExpressionVisitor expressionVisitor) {
        this.expressionVisitor = expressionVisitor;
    }

    @Autowired
    public void setConstantVisitor(ConstantVisitor constantVisitor) {
        this.constantVisitor = constantVisitor;
    }

    @Override
    public VtlDPRule visitRuleItemDatapoint(VtlParser.RuleItemDatapointContext ctx) {
        VtlDPRule vtlDPRule = new VtlDPRule();
        if (ctx.ruleName != null) {
            vtlDPRule.setRuleName(ctx.ruleName.getText());
        }
        if (ctx.antecedentContiditon != null) {
            vtlDPRule.setAntecedentCondition(
                    expressionVisitor.visit(ctx.antecedentContiditon)
            );
        }
        if (ctx.consequentCondition != null) {
            vtlDPRule.setConsequentCondition(
                    expressionVisitor.visit(ctx.consequentCondition)
            );
        }
        if (ctx.erCode() != null || ctx.erLevel() != null) {
            VtlErrorRuleset vtlErrorRuleset = new VtlErrorRuleset();
            if (ctx.erCode() != null)
                vtlErrorRuleset.setErrorCode(constantVisitor.visitConstant(ctx.erCode().constant()));
            if (ctx.erLevel() != null)
                vtlErrorRuleset.setErrorLevel(constantVisitor.visitConstant(ctx.erLevel().constant()));
            vtlDPRule.setVtlErrorRuleset(vtlErrorRuleset);
        }
        return vtlDPRule;
    }

    @Override
    public VtlHRRule visitRuleItemHierarchical(VtlParser.RuleItemHierarchicalContext ctx) {
        VtlHRRule vtlhrRule = new VtlHRRule();
        if (ctx.ruleName != null) {
            vtlhrRule.setRuleName(ctx.ruleName.getText());
        }
        if (ctx.codeItemRelation() != null) {
            vtlhrRule.setVtlCodeItemRelation(
                    visitCodeItemRelation(ctx.codeItemRelation())
            );
        }
        if (ctx.erCode() != null || ctx.erLevel() != null) {
            VtlErrorRuleset vtlErrorRuleset = new VtlErrorRuleset();
            if (ctx.erCode() != null)
                vtlErrorRuleset.setErrorCode(constantVisitor.visitConstant(ctx.erCode().constant()));
            if (ctx.erLevel() != null)
                vtlErrorRuleset.setErrorLevel(constantVisitor.visitConstant(ctx.erLevel().constant()));
            vtlhrRule.setErrorRuleset(vtlErrorRuleset);
        }
        return vtlhrRule;
    }

    @Override
    public VtlCodeItemRelation visitCodeItemRelation(VtlParser.CodeItemRelationContext ctx) {
        VtlCodeItemRelation vtlCodeItemRelation = new VtlCodeItemRelation();
        if (ctx.exprComponent() != null) {
            vtlCodeItemRelation.setLeftCondition(expressionVisitor.visit(ctx.exprComponent()));
        }
        vtlCodeItemRelation.setLeftCodeItem(visitValueDomainValue(ctx.codetemRef));
        for (VtlParser.CodeItemRelationClauseContext codeItemRelationClauseContext : ctx.codeItemRelationClause()) {
            vtlCodeItemRelation.getVtlCodeItems().addLast(
                    visitCodeItemRelationClause(codeItemRelationClauseContext)
            );
        }
        if (ctx.comparisonOperand().EQ() != null)
            vtlCodeItemRelation.setComparator(Operator.EQUAL_TO);
        if (ctx.comparisonOperand().NEQ() != null)
            vtlCodeItemRelation.setComparator(Operator.NOT_EQUAL_TO);
        if (ctx.comparisonOperand().LE() != null)
            vtlCodeItemRelation.setComparator(Operator.LESS_THAN_EQUALS);
        if (ctx.comparisonOperand().LT() != null)
            vtlCodeItemRelation.setComparator(Operator.LESS_THAN);
        if (ctx.comparisonOperand().ME() != null)
            vtlCodeItemRelation.setComparator(Operator.GREATER_THAN_EQUALS);
        if (ctx.comparisonOperand().MT() != null)
            vtlCodeItemRelation.setComparator(Operator.GREATER_THAN);
        return vtlCodeItemRelation;
    }

    @Override
    public VtlCodeItem visitCodeItemRelationClause(VtlParser.CodeItemRelationClauseContext ctx) {
        VtlCodeItem vtlCodeItem = new VtlCodeItem();
        if (ctx.opAdd != null) {
            if (ctx.opAdd.getText().equalsIgnoreCase("+"))
                vtlCodeItem.setOperator(Operator.ADDITION);
            else
                vtlCodeItem.setOperator(Operator.SUBSTRACTION);
        }
        vtlCodeItem.setCodeItem(ctx.rightCodeItem.getText());
        if (ctx.exprComponent() != null) {
            vtlCodeItem.setCondition(
                    expressionVisitor.visit(ctx.exprComponent())
            );
        }
        return vtlCodeItem;
    }

    @Override
    public String visitValueDomainValue(VtlParser.ValueDomainValueContext ctx) {
        return ctx.getText();
    }

}
