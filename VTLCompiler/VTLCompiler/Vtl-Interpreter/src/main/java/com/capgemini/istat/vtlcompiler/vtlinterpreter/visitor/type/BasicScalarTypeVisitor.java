package com.capgemini.istat.vtlcompiler.vtlinterpreter.visitor.type;

import com.capgemini.istat.vtlcompiler.vtlcommon.model.dataset.VtlType;
import com.capgemini.istat.vtlcompiler.vtlinterpreter.visitor.InterpreterVisitor;
import com.capgemini.istat.vtlcompiler.vtlparser.VtlParser;
import org.springframework.stereotype.Component;

@Component
public class BasicScalarTypeVisitor extends InterpreterVisitor<VtlType> {

    @Override
    public VtlType visitBasicScalarType(VtlParser.BasicScalarTypeContext ctx) {
        if (ctx.BOOLEAN() != null)
            return VtlType.BOOLEAN;
        if (ctx.NUMBER() != null)
            return VtlType.NUMBER;
        if (ctx.DATE() != null)
            return VtlType.DATE;
        if (ctx.DURATION() != null)
            return VtlType.DURATION;
        if (ctx.TIME() != null)
            return VtlType.TIME;
        if (ctx.TIME_PERIOD() != null)
            return VtlType.TIME_PERIOD;
        if (ctx.INTEGER() != null)
            return VtlType.INTEGER;
        if (ctx.STRING() != null)
            return VtlType.STRING;
        if (ctx.SCALAR() != null)
            return null;
        return null;
    }

}
