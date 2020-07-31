package com.capgemini.istat.vtlcompiler.vtlinterpreter.visitor.component;

import com.capgemini.istat.vtlcompiler.vtlcommon.model.dataset.VtlComponentRole;
import com.capgemini.istat.vtlcompiler.vtlinterpreter.visitor.InterpreterVisitor;
import com.capgemini.istat.vtlcompiler.vtlparser.VtlParser;
import org.springframework.stereotype.Component;

@Component
public class ComponentRoleVisitor extends InterpreterVisitor<VtlComponentRole> {

    @Override
    public VtlComponentRole visitComponentRole(VtlParser.ComponentRoleContext ctx) {
        if (ctx.ATTRIBUTE() != null && !ctx.ATTRIBUTE().getText().equals("")) {
            return VtlComponentRole.ATTRIBUTE;
        }
        if (ctx.MEASURE() != null && !ctx.MEASURE().getText().equals("")) {
            return VtlComponentRole.MEASURE;
        }
        if (ctx.viralAttribute() != null && ctx.viralAttribute().VIRAL() != null && !ctx.viralAttribute().VIRAL().getText().equals("")) {
            return VtlComponentRole.VIRAL;
        }
        if (ctx.DIMENSION() != null && !ctx.DIMENSION().getText().equals("")) {
            return VtlComponentRole.IDENTIFIER;
        }

        return null;
    }
}
