package com.capgemini.istat.vtlcompiler.vtlinterpreter.visitor.terminal;

import com.capgemini.istat.vtlcompiler.vtlinterpreter.visitor.InterpreterVisitor;
import com.capgemini.istat.vtlcompiler.vtlparser.VtlParser;
import org.springframework.stereotype.Component;

@Component
public class OperatorIdVisitor extends InterpreterVisitor {

    @Override
    public Object visitOperatorID(VtlParser.OperatorIDContext ctx) {
        logger.debug("visitOperatorID -> ");

        if (ctx.IDENTIFIER() != null)
            logger.debug("operatorId -> identifier -> " + ctx.IDENTIFIER().getText());
        return null;
    }
}
