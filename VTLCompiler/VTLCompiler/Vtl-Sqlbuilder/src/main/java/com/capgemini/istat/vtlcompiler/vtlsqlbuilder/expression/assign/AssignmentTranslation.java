package com.capgemini.istat.vtlcompiler.vtlsqlbuilder.expression.assign;

import com.capgemini.istat.vtlcompiler.vtlcommon.model.KeyVariables;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.translation.SqlResult;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.VtlExpression;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.statement.VtlAssignment;
import com.capgemini.istat.vtlcompiler.vtlsqlbuilder.TranslationFactory;
import com.capgemini.istat.vtlcompiler.vtlsqlbuilder.model.OperatorTranslation;
import com.capgemini.istat.vtlcompiler.vtlsqlbuilder.service.sqlresult.ISqlResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@Scope("prototype")
public class AssignmentTranslation implements OperatorTranslation {
    private VtlAssignment vtlAssignment;
    private TranslationFactory translationFactory;
    private ISqlResultService sqlResultService;

    @Autowired
    public void setTranslationFactory(TranslationFactory translationFactory) {
        this.translationFactory = translationFactory;
    }


    public void setSqlResultService(ISqlResultService sqlResultService) {
        this.sqlResultService = sqlResultService;
    }


    @Override
    public void setVtlExpression(VtlExpression vtlExpression) {
        this.vtlAssignment = (VtlAssignment) vtlExpression;
    }

    @Override
    public SqlResult translate(Map<KeyVariables, Object> variables) throws Exception {
        SqlResult result = translationFactory.translate(vtlAssignment.getVtlExpression(), variables);

        if (result.getSqlDataset().getSqlTables().get(0).getVtlDataset().isOnlyAScalar()) {
            result.getSqlDataset().getComponentList().get(0).setAliasName(vtlAssignment.getResultExpression().getResultComponent().getName());
        }

        if (result.getResultList().size() != 0
        && result.getResultList().getLast().toString().toLowerCase().contains(vtlAssignment.getVtlExpression().getResultExpression().getResult().getName().toLowerCase())) {
            result.setResultList(
                sqlResultService.assignName(
                    vtlAssignment.getVtlExpression().getResultExpression().getResult().getName(),
                    result.getResultList(),
                    vtlAssignment.getVarId().getName()
                )
            );
        } else {
            SqlResult resultAssign = sqlResultService.createTable(result.getSqlDataset(), vtlAssignment.getVarId().getName(), true);
            result.getResultList().addAll(resultAssign.getResultList());
        }
        return result;
    }
}
