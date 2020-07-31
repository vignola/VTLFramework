package com.capgemini.istat.vtlcompiler.vtlinterpreter.visitor.terminal;

import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.constant.*;
import com.capgemini.istat.vtlcompiler.vtlinterpreter.visitor.InterpreterVisitor;
import com.capgemini.istat.vtlcompiler.vtlparser.VtlParser;
import org.springframework.stereotype.Component;

@Component
public class ConstantVisitor extends InterpreterVisitor<VtlConstant> {


    @Override
    public VtlConstant<?> visitConstant(VtlParser.ConstantContext ctx) {
        logger.debug("visitConstant -> ");
        VtlConstant vtlConstant = null;

        if (ctx.BOOLEAN_CONSTANT() != null) {
            logger.debug("constant tipo BOOLEAN -> " + ctx.BOOLEAN_CONSTANT().getText());
            vtlConstant = new VtlBoolean();
            vtlConstant.setValue(Boolean.valueOf(ctx.BOOLEAN_CONSTANT().getText()));
        }
        if (ctx.NUMBER_CONSTANT() != null) {
            logger.debug("constant tipo FLOAT -> " + ctx.NUMBER_CONSTANT().getText());
            vtlConstant = new VtlFloat();
            vtlConstant.setValue(Float.valueOf(ctx.NUMBER_CONSTANT().getText()));
        }
        if (ctx.INTEGER_CONSTANT() != null) {
            logger.debug("constant tipo INTEGER -> " + ctx.INTEGER_CONSTANT().getText());
            vtlConstant = new VtlInteger();
            vtlConstant.setValue(Integer.valueOf(ctx.INTEGER_CONSTANT().getText()));
        }
        if (ctx.STRING_CONSTANT() != null) {
            logger.debug("constant tipo STRING -> " + ctx.STRING_CONSTANT().getText());
            vtlConstant = new VtlString();
            vtlConstant.setValue(ctx.STRING_CONSTANT().getText().replaceAll("\"", ""));
        }

        return vtlConstant;
    }
}
