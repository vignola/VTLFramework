package com.capgemini.istat.vtlcompiler.vtlinterpreter.visitor.terminal;

import com.capgemini.istat.vtlcompiler.vtlinterpreter.visitor.InterpreterVisitor;
import com.capgemini.istat.vtlcompiler.vtlparser.VtlParser;
import org.springframework.stereotype.Component;

@Component
public class RoutineNameVisitor extends InterpreterVisitor {

    @Override
    public Object visitRoutineName(VtlParser.RoutineNameContext ctx) {
        logger.debug("visitRoutineName -> ");

        logger.debug("routineName -> " + ctx.IDENTIFIER().getText());
        return null;
    }
}
