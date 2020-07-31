package com.capgemini.istat.vtlcompiler.vtlinterpreter.visitor.function;

import com.capgemini.istat.vtlcompiler.vtlcommon.model.dataset.VtlType;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.operator.Operator;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.constant.VtlConstantExpression;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.constant.VtlOptional;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.VtlExpression;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.generic.VtlCallExpression;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.generic.VtlCastExpression;
import com.capgemini.istat.vtlcompiler.vtlinterpreter.visitor.InterpreterVisitor;
import com.capgemini.istat.vtlcompiler.vtlinterpreter.visitor.component.ComponentIdVisitor;
import com.capgemini.istat.vtlcompiler.vtlinterpreter.visitor.expression.ExpressionVisitor;
import com.capgemini.istat.vtlcompiler.vtlinterpreter.visitor.terminal.ConstantVisitor;
import com.capgemini.istat.vtlcompiler.vtlinterpreter.visitor.terminal.VarIdVisitor;
import com.capgemini.istat.vtlcompiler.vtlinterpreter.visitor.type.BasicScalarTypeVisitor;
import com.capgemini.istat.vtlcompiler.vtlinterpreter.visitor.type.ValueDomainVisitor;
import com.capgemini.istat.vtlcompiler.vtlparser.VtlParser;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.TerminalNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GenericFunctionVisitor extends InterpreterVisitor<VtlExpression> {
    private ExpressionVisitor expressionVisitor;
    private ValueDomainVisitor valueDomainVisitor;
    private BasicScalarTypeVisitor basicScalarTypeVisitor;
    private VarIdVisitor varIdVisitor;
    private ComponentIdVisitor componentIdVisitor;
    private ConstantVisitor constantVisitor;

    @Autowired
    public void setExpressionVisitor(ExpressionVisitor expressionVisitor) {
        this.expressionVisitor = expressionVisitor;
    }

    @Autowired
    public void setValueDomainVisitor(ValueDomainVisitor valueDomainVisitor) {
        this.valueDomainVisitor = valueDomainVisitor;
    }

    @Autowired
    public void setBasicScalarTypeVisitor(BasicScalarTypeVisitor basicScalarTypeVisitor) {
        this.basicScalarTypeVisitor = basicScalarTypeVisitor;
    }

    @Autowired
    public void setVarIdVisitor(VarIdVisitor varIdVisitor) {
        this.varIdVisitor = varIdVisitor;
    }

    @Autowired
    public void setConstantVisitor(ConstantVisitor constantVisitor) {
        this.constantVisitor = constantVisitor;
    }

    @Autowired
    public void setComponentIdVisitor(ComponentIdVisitor componentIdVisitor) {
        this.componentIdVisitor = componentIdVisitor;
    }

    @Override
    public VtlExpression visitCallDataset(VtlParser.CallDatasetContext ctx) {
        VtlCallExpression vtlCallExpression = new VtlCallExpression();
        vtlCallExpression.setFunctionName(
                ctx.operatorID().IDENTIFIER().getText()
        );
        if (ctx.parameter() != null) {
            for (VtlParser.ParameterContext parameterContext : ctx.parameter()) {
                vtlCallExpression.getParameters().addLast(
                        visitParameter(parameterContext)
                );
            }
        }
        vtlCallExpression.setCommand(ctx.getText());
        return vtlCallExpression;
    }

    @Override
    public VtlExpression visitCallComponent(VtlParser.CallComponentContext ctx) {
        VtlCallExpression vtlCallExpression = new VtlCallExpression();
        vtlCallExpression.setFunctionName(
                ctx.operatorID().IDENTIFIER().getText()
        );
        if (ctx.parameterComponent() != null) {
            for (VtlParser.ParameterComponentContext parameterComponentContext : ctx.parameterComponent()) {
                vtlCallExpression.getParameters().addLast(
                        visitParameterComponent(parameterComponentContext)
                );
            }
        }
        vtlCallExpression.setCommand(ctx.getText());
        return vtlCallExpression;
    }

    @Override
    public VtlExpression visitParameter(VtlParser.ParameterContext ctx) {
        if (ctx.varID() != null) {
            return varIdVisitor.visitVarID(ctx.varID());
        }
        return getParameter(ctx.constant(), ctx.getText(), ctx.OPTIONAL());
    }

    @Override
    public VtlExpression visitParameterComponent(VtlParser.ParameterComponentContext ctx) {
        if (ctx.componentID() != null) {
            return componentIdVisitor.visit(ctx.componentID());
        }
        return getParameter(ctx.constant(), ctx.getText(), ctx.OPTIONAL());
    }


    private VtlExpression getParameter(VtlParser.ConstantContext constant, String text, TerminalNode optional) {
        if (constant != null) {
            VtlConstantExpression vtlConstantExpression = new VtlConstantExpression(constantVisitor.visitConstant(constant));
            vtlConstantExpression.setCommand(text);
            return vtlConstantExpression;
        }
        if (optional != null) {
            return new VtlOptional();
        }
        return null;
    }


    @Override
    public VtlExpression visitEvalAtom(VtlParser.EvalAtomContext ctx) {
        return null;
    }

    @Override
    public VtlCastExpression visitCastExprDataset(VtlParser.CastExprDatasetContext ctx) {
        return getCastExpression(ctx.expr(), ctx.basicScalarType(), ctx.valueDomainName(), ctx.STRING_CONSTANT() != null ? ctx.STRING_CONSTANT().getText() : null, ctx.getText());
    }

    @Override
    public VtlCastExpression visitCastExprComponent(VtlParser.CastExprComponentContext ctx) {
        return getCastExpression(ctx.exprComponent(), ctx.basicScalarType(), ctx.valueDomainName(), ctx.STRING_CONSTANT() != null ? ctx.STRING_CONSTANT().getText() : null, ctx.getText());
    }

    private VtlCastExpression getCastExpression(ParseTree tree, VtlParser.BasicScalarTypeContext scalarTypeContext,
                                                VtlParser.ValueDomainNameContext valueDomainNameContext, String constant, String text) {
        VtlCastExpression vtlCastExpression = new VtlCastExpression();
        if (tree != null) {
            vtlCastExpression.setVtlExpression(
                    expressionVisitor.visit(tree)
            );
        }
        if (scalarTypeContext != null) {
            vtlCastExpression.setVtlType(
                    basicScalarTypeVisitor.visitBasicScalarType(scalarTypeContext)
            );
        }
        if (valueDomainNameContext != null) {
            String valueDomain = valueDomainVisitor.visitValueDomainName(valueDomainNameContext);
            if (valueDomain.equals("time")) {
                vtlCastExpression.setVtlType(VtlType.TIME);
            } else {
                vtlCastExpression.setValueDomainName(
                        valueDomain
                );
            }
        }
        if (constant != null) {
            vtlCastExpression.setMask(
                    constant.replace("'", "").replace("\"", "")
            );
        }
        vtlCastExpression.setOperator(getCastType(vtlCastExpression.getVtlType()));
        vtlCastExpression.setCommand(text);
        return vtlCastExpression;
    }

    private Operator getCastType(VtlType vtlType){
        if(vtlType == VtlType.INTEGER)
            return Operator.CAST_TO_INTEGER;
        if(vtlType == VtlType.NUMBER)
            return Operator.CAST_TO_NUMBER;
        if(vtlType == VtlType.BOOLEAN)
            return Operator.CAST_TO_BOOLEAN;
        if(vtlType == VtlType.TIME)
            return Operator.CAST_TO_TIME;
        if(vtlType == VtlType.TIME_PERIOD)
            return Operator.CAST_TO_TIME_PERIOD;
        if(vtlType == VtlType.DATE)
            return Operator.CAST_TO_DATE;
        if(vtlType == VtlType.STRING)
            return Operator.CAST_TO_STRING;
        if(vtlType == VtlType.DURATION)
            return Operator.CAST_TO_DURATION;
        return null;
    }


}
