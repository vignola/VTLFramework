package com.capgemini.istat.vtlcompiler.vtlinterpreter.visitor.define;

import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.constant.VtlConstantExpression;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.define.operator.VtlFunctionParameter;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.define.operator.datatype.VtlDataType;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.define.ruleset.VarParameter;
import com.capgemini.istat.vtlcompiler.vtlinterpreter.visitor.InterpreterVisitor;
import com.capgemini.istat.vtlcompiler.vtlinterpreter.visitor.terminal.ConstantVisitor;
import com.capgemini.istat.vtlcompiler.vtlparser.VtlParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FunctionParameterVisitor extends InterpreterVisitor {
    private ParameterTypeVisitor parameterTypeVisitor;
    private ConstantVisitor constantVisitor;

    @Autowired
    public void setParameterTypeVisitor(ParameterTypeVisitor parameterTypeVisitor) {
        this.parameterTypeVisitor = parameterTypeVisitor;
    }

    @Autowired
    public void setConstantVisitor(ConstantVisitor constantVisitor) {
        this.constantVisitor = constantVisitor;
    }

    @Override
    public VtlFunctionParameter visitParameterItem(VtlParser.ParameterItemContext ctx) {
        VtlFunctionParameter vtlFunctionParameter = new VtlFunctionParameter();
        vtlFunctionParameter.setParameterName(ctx.varID().getText());
        vtlFunctionParameter.setVtlDataType(
                visitInputParameterType(ctx.inputParameterType())
        );
        if (ctx.constant() != null) {
            vtlFunctionParameter.setDefaultValue(
                    new VtlConstantExpression(constantVisitor.visitConstant(ctx.constant()))
            );
        }
        return vtlFunctionParameter;
    }

    @Override
    public VtlDataType visitInputParameterType(VtlParser.InputParameterTypeContext ctx) {
        if (ctx.scalarType() != null)
            return parameterTypeVisitor.visitScalarType(ctx.scalarType());
        if (ctx.scalarSetType() != null)
            return parameterTypeVisitor.visitScalarSetType(ctx.scalarSetType());
        if (ctx.componentType() != null)
            return parameterTypeVisitor.visitComponentType(ctx.componentType());
        if (ctx.datasetType() != null)
            return parameterTypeVisitor.visitDatasetType(ctx.datasetType());
        return null;
    }

    @Override
    public VtlDataType visitOutputParameterType(VtlParser.OutputParameterTypeContext ctx) {
        if (ctx.scalarType() != null)
            return parameterTypeVisitor.visitScalarType(ctx.scalarType());
        if (ctx.componentType() != null)
            return parameterTypeVisitor.visitComponentType(ctx.componentType());
        if (ctx.datasetType() != null)
            return parameterTypeVisitor.visitDatasetType(ctx.datasetType());
        return null;
    }

    @Override
    public VarParameter visitSignature(VtlParser.SignatureContext ctx) {
        VarParameter varParameter = new VarParameter();
        varParameter.setName(
                ctx.varID().IDENTIFIER().getText()
        );
        if (ctx.alias() != null) {
            varParameter.setAlias(ctx.alias().IDENTIFIER().getText());
        }
        return varParameter;
    }

}
