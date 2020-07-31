package com.capgemini.istat.vtlcompiler.vtlinterpreter.visitor.type;

import com.capgemini.istat.vtlcompiler.vtlinterpreter.visitor.InterpreterVisitor;
import com.capgemini.istat.vtlcompiler.vtlparser.VtlParser;
import org.springframework.stereotype.Component;

@Component
public class ValueDomainVisitor extends InterpreterVisitor<String> {

    @Override
    public String visitValueDomainName(VtlParser.ValueDomainNameContext ctx) {
        if (ctx.IDENTIFIER() != null) {
            return ctx.IDENTIFIER().getText();
        }
        return null;
    }
}
