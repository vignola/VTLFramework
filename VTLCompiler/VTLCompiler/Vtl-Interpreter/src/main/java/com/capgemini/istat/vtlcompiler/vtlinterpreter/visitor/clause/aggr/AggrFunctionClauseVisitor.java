package com.capgemini.istat.vtlcompiler.vtlinterpreter.visitor.clause.aggr;

import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.clause.aggr.VtlAggrFunctionClauseExpression;
import com.capgemini.istat.vtlcompiler.vtlinterpreter.visitor.InterpreterVisitor;
import com.capgemini.istat.vtlcompiler.vtlinterpreter.visitor.component.ComponentIdVisitor;
import com.capgemini.istat.vtlcompiler.vtlinterpreter.visitor.component.ComponentRoleVisitor;
import com.capgemini.istat.vtlcompiler.vtlinterpreter.visitor.function.clause.aggr.AggrFunctionVisitor;
import com.capgemini.istat.vtlcompiler.vtlparser.VtlParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AggrFunctionClauseVisitor extends InterpreterVisitor<VtlAggrFunctionClauseExpression> {

    private ComponentIdVisitor componentIdVisitor;
    private ComponentRoleVisitor componentRoleVisitor;
    private AggrFunctionVisitor aggrFunctionVisitor;

    @Autowired
    public void setComponentIdVisitor(ComponentIdVisitor componentIdVisitor) {
        this.componentIdVisitor = componentIdVisitor;
    }

    @Autowired
    public void setAggrFunctionVisitor(AggrFunctionVisitor aggrFunctionVisitor) {
        this.aggrFunctionVisitor = aggrFunctionVisitor;
    }

    @Autowired
    public void setComponentRoleVisitor(ComponentRoleVisitor componentRoleVisitor) {
        this.componentRoleVisitor = componentRoleVisitor;
    }

    @Override
    public VtlAggrFunctionClauseExpression visitAggrFunctionClause(VtlParser.AggrFunctionClauseContext ctx) {
        logger.debug("visitAggrFunctionClause -> ");
        VtlAggrFunctionClauseExpression vtlAggrFunctionClauseExpression = new VtlAggrFunctionClauseExpression();

        if (ctx.componentRole() != null) {
            vtlAggrFunctionClauseExpression.setVtlcomponentRole(
                    componentRoleVisitor.visitComponentRole(ctx.componentRole())
            );
        }
        if (ctx.componentID() != null) {
            vtlAggrFunctionClauseExpression.setVtlComponentId(
                    componentIdVisitor.visitComponentID(ctx.componentID())

            );
        }
        if (ctx.aggrOperators() != null) {
            aggrFunctionVisitor.setVariables(getVariables());
            vtlAggrFunctionClauseExpression.setVtlAggrComp(
                    aggrFunctionVisitor.visit(ctx.aggrOperators())

            );
        }
        vtlAggrFunctionClauseExpression.setCommand(ctx.getText());
        return vtlAggrFunctionClauseExpression;
    }
}
