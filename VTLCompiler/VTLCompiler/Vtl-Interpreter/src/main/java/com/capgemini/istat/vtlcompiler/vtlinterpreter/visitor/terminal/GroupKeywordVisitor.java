package com.capgemini.istat.vtlcompiler.vtlinterpreter.visitor.terminal;

import com.capgemini.istat.vtlcompiler.vtlinterpreter.visitor.InterpreterVisitor;
import org.springframework.stereotype.Component;

@Component
public class GroupKeywordVisitor extends InterpreterVisitor {

  /*  @Override
    public Operator visitGroupKeyword(VtlParser.GroupKeywordContext ctx) {
        logger.debug("visitGroupKeyword -> ");
        if (ctx.GROUP() != null && ctx.BY() != null) {
            logger.debug("groupKeyword -> " + ctx.GROUP().getText() + " " + ctx.BY().getText());
            return Operator.GROUP_BY;
        }
        if (ctx.GROUP() != null && ctx.EXCEPT() != null) {
            logger.debug("groupKeyword -> " + ctx.GROUP().getText() + " " + ctx.EXCEPT().getText());
            return Operator.GROUP_EXCEPT;
        }

        return null;
    }*/
}
