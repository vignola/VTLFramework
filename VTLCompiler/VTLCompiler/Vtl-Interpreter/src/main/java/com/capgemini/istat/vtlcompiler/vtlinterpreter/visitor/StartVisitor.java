package com.capgemini.istat.vtlcompiler.vtlinterpreter.visitor;

import com.capgemini.istat.vtlcompiler.vtlcommon.model.KeyVariables;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.statement.VtlStatement;
import com.capgemini.istat.vtlcompiler.vtlinterpreter.visitor.statement.StatementVisitor;
import com.capgemini.istat.vtlcompiler.vtlparser.VtlParser;
import org.antlr.v4.runtime.misc.Interval;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class StartVisitor extends InterpreterVisitor<List<VtlStatement>> {

    private StatementVisitor statementVisitor;

    @Autowired
    public void setStatementVisitor(StatementVisitor statementVisitor) {
        this.statementVisitor = statementVisitor;
    }

    @Override
    public List<VtlStatement> visitStart(VtlParser.StartContext ctx) {
        List<VtlStatement> vtlStatements = new ArrayList<>();
/*        int a = ctx.start.getStartIndex();
        int b = ctx.stop.getStopIndex();
        Interval interval = new Interval(a,b);*/


        if (ctx.statement() != null) {
            for (VtlParser.StatementContext statementContext : ctx.statement()) {
                logger.info("statement in interpretazione -> " + statementContext.getText());
                Map<KeyVariables, Object> variables = new HashMap<>();
                statementVisitor.setVariables(variables);
                VtlStatement vtlStatement = statementVisitor.visit(statementContext);
                int a = statementContext.getStart().getStartIndex();
                int b = statementContext.getStop().getStopIndex();
                Interval interval = new Interval(a, b);
                vtlStatement.setCommand(statementContext.getStart().getInputStream().getText(interval));
                vtlStatements.add(vtlStatement);
                logger.info("interprertato con successo -> " + statementContext.getText());
            }
        }
        return vtlStatements;
    }

}
