package com.capgemini.istat.vtlcompiler.vtlinterpreter.visitor.expression;

import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.constant.VtlConstantExpression;
import com.capgemini.istat.vtlcompiler.vtlinterpreter.visitor.InterpreterVisitor;
import com.capgemini.istat.vtlcompiler.vtlinterpreter.visitor.terminal.ConstantVisitor;
import com.capgemini.istat.vtlcompiler.vtlparser.VtlParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ListsVisitor extends InterpreterVisitor {
    private ConstantVisitor constantVisitor;

    @Autowired
    public ListsVisitor(ConstantVisitor constantVisitor) {
        this.constantVisitor = constantVisitor;
    }

    @Override
    public List<VtlConstantExpression> visitLists(VtlParser.ListsContext ctx) {
        logger.debug("visitLists -> ");
        List<VtlConstantExpression> constantList = new ArrayList<>();
        if (ctx.constant() != null) {
            for (VtlParser.ConstantContext constantContext : ctx.constant()) {
                constantList.add(new VtlConstantExpression(constantVisitor.visitConstant(constantContext)));
            }
        }
        return constantList;
    }
}
