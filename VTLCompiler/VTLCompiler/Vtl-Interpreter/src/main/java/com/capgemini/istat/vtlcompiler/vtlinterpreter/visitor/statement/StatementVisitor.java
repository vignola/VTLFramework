package com.capgemini.istat.vtlcompiler.vtlinterpreter.visitor.statement;

import com.capgemini.istat.vtlcompiler.vtlcommon.model.operator.Operator;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.dataset.VtlVarId;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.statement.VtlAssignment;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.statement.VtlPersist;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.statement.VtlStatement;
import com.capgemini.istat.vtlcompiler.vtlinterpreter.visitor.InterpreterVisitor;
import com.capgemini.istat.vtlcompiler.vtlinterpreter.visitor.define.DefineVisitor;
import com.capgemini.istat.vtlcompiler.vtlinterpreter.visitor.expression.ExpressionVisitor;
import com.capgemini.istat.vtlcompiler.vtlinterpreter.visitor.terminal.VarIdVisitor;
import com.capgemini.istat.vtlcompiler.vtlparser.VtlParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class StatementVisitor extends InterpreterVisitor<VtlStatement> {
    private ExpressionVisitor expressionVisitor;
    private VarIdVisitor varIdVisitor;
    private DefineVisitor defineVisitor;

    @Autowired
    public void setVarIdVisitor(VarIdVisitor varIdVisitor) {
        this.varIdVisitor = varIdVisitor;
    }

    @Autowired
    public void setExpressionVisitor(ExpressionVisitor expressionVisitor) {
        this.expressionVisitor = expressionVisitor;
    }

    @Autowired
    public void setDefineVisitor(DefineVisitor defineVisitor) {
        this.defineVisitor = defineVisitor;
    }

    @Override
    public VtlStatement visitTemporaryAssignment(VtlParser.TemporaryAssignmentContext ctx) {
        varIdVisitor.setVariables(getVariables());
        VtlStatement vtlStatement = new VtlStatement();
        logger.debug("visitAssign -> ");
        VtlAssignment vtlAssignment = new VtlAssignment();
        vtlAssignment.setVarId(
                (VtlVarId) varIdVisitor.visitVarID(ctx.varID())
        );
        logger.debug("visitAssign -> " + ctx.ASSIGN().getText());
        vtlAssignment.setVtlExpression(expressionVisitor.visit(ctx.expr()));
        vtlAssignment.setCommand(ctx.getText());
        vtlStatement.setVtlExpression(vtlAssignment);
        vtlStatement.setCommand(ctx.getText());
        return vtlStatement;
    }

    @Override
    public VtlStatement visitPersistAssignment(VtlParser.PersistAssignmentContext ctx) {
        varIdVisitor.setVariables(getVariables());

        VtlStatement vtlStatement = new VtlStatement();
        logger.debug("visitPersistAssignment -> ");
        VtlPersist vtlPersist = new VtlPersist();
        if (ctx.PUT_SYMBOL() != null) {
            vtlPersist.setVtlVarId((VtlVarId) varIdVisitor.visit(ctx.varID()));
            vtlPersist.setVtlExpression(expressionVisitor.visit(ctx.expr()));
            logger.debug("visitPersistAssignment -> " + ctx.PUT_SYMBOL().getText());
        }
        vtlPersist.setOperator(Operator.PERSIST);
        vtlPersist.setCommand(ctx.getText());
        vtlStatement.setVtlExpression(vtlPersist);
        vtlStatement.setCommand(ctx.getText());
        return vtlStatement;
    }

    @Override
    public VtlStatement visitDefineExpression(VtlParser.DefineExpressionContext ctx) {
        VtlStatement vtlStatement = new VtlStatement();
        vtlStatement.setVtlDefineFunction(
                defineVisitor.visit(ctx.defOperators())
        );
        vtlStatement.setOperator(Operator.DEFINE);
        return vtlStatement;
    }

}
