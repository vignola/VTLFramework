package com.capgemini.istat.vtlcompiler.vtlinterpreter.visitor.component;

import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.dataset.VtlComponentId;
import com.capgemini.istat.vtlcompiler.vtlinterpreter.visitor.InterpreterVisitor;
import com.capgemini.istat.vtlcompiler.vtlparser.VtlParser;
import org.springframework.stereotype.Component;

@Component
public class ComponentIdVisitor extends InterpreterVisitor<VtlComponentId> {

    @Override
    public VtlComponentId visitComponentID(VtlParser.ComponentIDContext ctx) {
        logger.debug("visitComponentID -> ");
        logger.debug("componentID -> " + ctx.IDENTIFIER().toString());
        VtlComponentId vtlComponentId = getVtlComponentId(ctx.getText());
        return vtlComponentId;
    }

    private VtlComponentId getVtlComponentId(String text) {
        VtlComponentId vtlComponentId = new VtlComponentId();
        vtlComponentId.setComponentName(text.replace("'", ""));
        vtlComponentId.setCommand(text);
        return vtlComponentId;
    }

    @Override
    public VtlComponentId visitSimpleComponentId(VtlParser.SimpleComponentIdContext ctx) {
        logger.debug("visitSimpleComponentId -> ");
        logger.debug("componentID -> " + ctx.IDENTIFIER().toString());
        VtlComponentId vtlComponentId = getVtlComponentId(ctx.getText());
        return vtlComponentId;
    }
}
