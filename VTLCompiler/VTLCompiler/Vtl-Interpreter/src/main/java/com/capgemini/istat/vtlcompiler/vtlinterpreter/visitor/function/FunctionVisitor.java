package com.capgemini.istat.vtlcompiler.vtlinterpreter.visitor.function;

import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.VtlExpression;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.join.VtlJoinExpression;
import com.capgemini.istat.vtlcompiler.vtlinterpreter.visitor.InterpreterVisitor;
import com.capgemini.istat.vtlcompiler.vtlinterpreter.visitor.function.clause.aggr.AggrGroupFunctionVisitor;
import com.capgemini.istat.vtlcompiler.vtlinterpreter.visitor.function.clause.analytic.AnFunctionVisitor;
import com.capgemini.istat.vtlcompiler.vtlinterpreter.visitor.join.JoinExprVisitor;
import com.capgemini.istat.vtlcompiler.vtlparser.VtlParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FunctionVisitor extends InterpreterVisitor<VtlExpression> {

    private GenericFunctionVisitor genericFunctionVisitor;
    private StringFunctionVisitor stringFunctionVisitor;
    private NumericFunctionVisitor numericFunctionVisitor;
    private ComparisonFunctionVisitor comparisonFunctionVisitor;
    private TimeFunctionVisitor timeFunctionVisitor;
    private SetFunctionVisitor setFunctionVisitor;
    private ConditionalFunctionVisitor conditionalFunctionVisitor;
    private AggrGroupFunctionVisitor aggrGroupFunctionVisitor;
    private AnFunctionVisitor anFunctionVisitor;
    private JoinExprVisitor joinExprVisitor;
    private ValidationVisitor validationVisitor;
    private HierarchicalFunctionVisitor hierarchicalFunctionVisitor;

    @Autowired
    public void setGenericFunctionVisitor(GenericFunctionVisitor genericFunctionVisitor) {
        this.genericFunctionVisitor = genericFunctionVisitor;
    }

    @Autowired
    public void setStringFunctionVisitor(StringFunctionVisitor stringFunctionVisitor) {
        this.stringFunctionVisitor = stringFunctionVisitor;
    }

    @Autowired
    public void setNumericFunctionVisitor(NumericFunctionVisitor numericFunctionVisitor) {
        this.numericFunctionVisitor = numericFunctionVisitor;
    }

    @Autowired
    public void setComparisonFunctionVisitor(ComparisonFunctionVisitor comparisonFunctionVisitor) {
        this.comparisonFunctionVisitor = comparisonFunctionVisitor;
    }

    @Autowired
    public void setTimeFunctionVisitor(TimeFunctionVisitor timeFunctionVisitor) {
        this.timeFunctionVisitor = timeFunctionVisitor;
    }

    @Autowired
    public void setSetFunctionVisitor(SetFunctionVisitor setFunctionVisitor) {
        this.setFunctionVisitor = setFunctionVisitor;
    }

    @Autowired
    public void setConditionalFunctionVisitor(ConditionalFunctionVisitor conditionalFunctionVisitor) {
        this.conditionalFunctionVisitor = conditionalFunctionVisitor;
    }

    @Autowired
    public void setAggrGroupFunctionVisitor(AggrGroupFunctionVisitor aggrGroupFunctionVisitor) {
        this.aggrGroupFunctionVisitor = aggrGroupFunctionVisitor;
    }

    @Autowired
    public void setJoinExprVisitor(JoinExprVisitor joinExprVisitor) {
        this.joinExprVisitor = joinExprVisitor;
    }

    @Autowired
    public void setAnFunctionVisitor(AnFunctionVisitor anFunctionVisitor) {
        this.anFunctionVisitor = anFunctionVisitor;
    }

    @Autowired
    public void setValidationVisitor(ValidationVisitor validationVisitor) {
        this.validationVisitor = validationVisitor;
    }

    @Autowired
    public void setHierarchicalFunctionVisitor(HierarchicalFunctionVisitor hierarchicalFunctionVisitor) {
        this.hierarchicalFunctionVisitor = hierarchicalFunctionVisitor;
    }

    @Override
    public VtlJoinExpression visitJoinFunctions(VtlParser.JoinFunctionsContext ctx) {
        joinExprVisitor.setVariables(getVariables());
        return joinExprVisitor.visit(ctx.joinOperators());
    }

    @Override
    public VtlExpression visitGenericFunctions(VtlParser.GenericFunctionsContext ctx) {
        genericFunctionVisitor.setVariables(getVariables());
        return genericFunctionVisitor.visit(ctx.genericOperators());
    }

    @Override
    public VtlExpression visitStringFunctions(VtlParser.StringFunctionsContext ctx) {
        stringFunctionVisitor.setVariables(getVariables());
        return stringFunctionVisitor.visit(ctx.stringOperators());
    }

    @Override
    public VtlExpression visitNumericFunctions(VtlParser.NumericFunctionsContext ctx) {
        numericFunctionVisitor.setVariables(getVariables());
        return numericFunctionVisitor.visit(ctx.numericOperators());
    }

    @Override
    public VtlExpression visitComparisonFunctions(VtlParser.ComparisonFunctionsContext ctx) {
        comparisonFunctionVisitor.setVariables(getVariables());
        return comparisonFunctionVisitor.visit(ctx.comparisonOperators());
    }

    @Override
    public VtlExpression visitTimeFunctions(VtlParser.TimeFunctionsContext ctx) {
        timeFunctionVisitor.setVariables(getVariables());
        return timeFunctionVisitor.visit(ctx.timeOperators());
    }

    @Override
    public VtlExpression visitSetFunctions(VtlParser.SetFunctionsContext ctx) {
        setFunctionVisitor.setVariables(getVariables());
        return setFunctionVisitor.visit(ctx.setOperators());
    }

    @Override
    public VtlExpression visitHierarchyFunctions(VtlParser.HierarchyFunctionsContext ctx) {
        return hierarchicalFunctionVisitor.visitHierarchyOperators(ctx.hierarchyOperators());
    }

    @Override
    public VtlExpression visitValidationFunctions(VtlParser.ValidationFunctionsContext ctx) {
        return validationVisitor.visit(ctx.validationOperators());
    }

    @Override
    public VtlExpression visitConditionalFunctions(VtlParser.ConditionalFunctionsContext ctx) {
        conditionalFunctionVisitor.setVariables(getVariables());
        return conditionalFunctionVisitor.visit(ctx.conditionalOperators());
    }

    @Override
    public VtlExpression visitAggregateFunctions(VtlParser.AggregateFunctionsContext ctx) {
        if (ctx.aggrOperatorsGrouping() != null) {
            aggrGroupFunctionVisitor.setVariables(getVariables());
            return aggrGroupFunctionVisitor.visit(
                    ctx.aggrOperatorsGrouping()
            );
        }

        return null;
    }

    @Override
    public VtlExpression visitAnalyticFunctions(VtlParser.AnalyticFunctionsContext ctx) {
        anFunctionVisitor.setVariables(getVariables());
        return anFunctionVisitor.visit(ctx.anFunction());
    }

    /**
     * COMPONENTS FUNCTION
     */

    @Override
    public VtlExpression visitGenericFunctionsComponents(VtlParser.GenericFunctionsComponentsContext ctx) {
        genericFunctionVisitor.setVariables(getVariables());
        return genericFunctionVisitor.visit(ctx.genericOperatorsComponent());
    }

    @Override
    public VtlExpression visitStringFunctionsComponents(VtlParser.StringFunctionsComponentsContext ctx) {
        stringFunctionVisitor.setVariables(getVariables());
        return stringFunctionVisitor.visit(ctx.stringOperatorsComponent());
    }

    @Override
    public VtlExpression visitNumericFunctionsComponents(VtlParser.NumericFunctionsComponentsContext ctx) {
        numericFunctionVisitor.setVariables(getVariables());
        return numericFunctionVisitor.visit(ctx.numericOperatorsComponent());
    }

    @Override
    public VtlExpression visitComparisonFunctionsComponents(VtlParser.ComparisonFunctionsComponentsContext ctx) {
        comparisonFunctionVisitor.setVariables(getVariables());
        return comparisonFunctionVisitor.visit(ctx.comparisonOperatorsComponent());
    }

    @Override
    public VtlExpression visitTimeFunctionsComponents(VtlParser.TimeFunctionsComponentsContext ctx) {
        timeFunctionVisitor.setVariables(getVariables());
        return timeFunctionVisitor.visit(ctx.timeOperatorsComponent());
    }

    @Override
    public VtlExpression visitConditionalFunctionsComponents(VtlParser.ConditionalFunctionsComponentsContext ctx) {
        conditionalFunctionVisitor.setVariables(getVariables());
        return conditionalFunctionVisitor.visit(ctx.conditionalOperatorsComponent());
    }

    @Override
    public VtlExpression visitAggregateFunctionsComponents(VtlParser.AggregateFunctionsComponentsContext ctx) {
        if (ctx.aggrOperators() != null) {
            aggrGroupFunctionVisitor.setVariables(getVariables());
            return aggrGroupFunctionVisitor.visit(
                    ctx.aggrOperators()
            );
        }

        return null;
    }

    @Override
    public VtlExpression visitAnalyticFunctionsComponents(VtlParser.AnalyticFunctionsComponentsContext ctx) {
        anFunctionVisitor.setVariables(getVariables());
        return anFunctionVisitor.visit(ctx.anFunctionComponent());
    }



}
