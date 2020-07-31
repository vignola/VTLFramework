package com.capgemini.istat.vtlcompiler.vtlinterpreter.visitor.terminal;

import com.capgemini.istat.vtlcompiler.vtlinterpreter.visitor.InterpreterVisitor;
import com.capgemini.istat.vtlcompiler.vtlparser.VtlParser;
import org.springframework.stereotype.Component;

@Component
public class ValueDomainNameVisitor extends InterpreterVisitor {

    @Override
    public Object visitValueDomainName(VtlParser.ValueDomainNameContext ctx) {
        logger.debug("visitValueDomainName -> ");
        if (ctx.IDENTIFIER() != null) {
            logger.debug("visitValueDomainName -> Identifier -> " + ctx.IDENTIFIER().getText());
        }
        return null;
    }
}
