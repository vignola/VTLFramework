package com.capgemini.istat.vtlcompiler.vtlinterpreter.visitor.terminal;

import com.capgemini.istat.vtlcompiler.vtlcommon.model.KeyVariables;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.operator.Operator;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.dataset.VtlComponentId;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.dataset.VtlVarId;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.VtlExpression;
import com.capgemini.istat.vtlcompiler.vtlinterpreter.visitor.InterpreterVisitor;
import com.capgemini.istat.vtlcompiler.vtlparser.VtlParser;
import org.springframework.stereotype.Component;


@Component
public class VarIdVisitor extends InterpreterVisitor<VtlExpression> {



    @Override
    public VtlExpression visitVarID(VtlParser.VarIDContext ctx) {
        Boolean isInCluase = false;
        Boolean isInClauseMembership = false;
        VtlExpression vtlExpression = null;
        if (this.getVariables().get(KeyVariables.DATASET_IN_CLAUSE) != null) {
            isInCluase = true;
        }
        if (this.getVariables().get(KeyVariables.MEMBERSHIP_IN_CLAUSE) != null) {
            isInClauseMembership = true;
        }
        logger.debug("visitVarID -> ");

        if (ctx.getText() != null) {
            if (isInCluase && !isInClauseMembership) {
                //Se è  inClause è un errore di grammatica e quindi devo tornare un VtlComponentId
                logger.debug("Errore di grammatica restituisco contextID -> " + ctx.getText());
                VtlComponentId vtlComponentId = new VtlComponentId(ctx.getText().replaceAll("'", ""));
                vtlComponentId.setIgnoreCase(!ctx.getText().contains("'"));
                vtlExpression = vtlComponentId;
                vtlExpression.setOperator(Operator.COMPONENT);

            } else {
                logger.debug("varId -> " + ctx.getText().replaceAll("'", ""));
                VtlVarId vtlVarId = new VtlVarId(ctx.getText().replaceAll("'", ""));
                vtlVarId.setIgnoreCase(!ctx.getText().contains("'"));
                vtlExpression = vtlVarId;
                vtlExpression.setOperator(Operator.DATASET);
            }
            vtlExpression.setCommand(ctx.getText());
        }

        return vtlExpression;
    }
}
