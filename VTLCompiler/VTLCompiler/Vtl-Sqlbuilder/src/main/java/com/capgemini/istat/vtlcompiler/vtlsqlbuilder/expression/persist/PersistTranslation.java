package com.capgemini.istat.vtlcompiler.vtlsqlbuilder.expression.persist;

import com.capgemini.istat.vtlcompiler.vtlcommon.model.KeyVariables;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.translation.SqlResult;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.VtlExpression;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.statement.VtlPersist;
import com.capgemini.istat.vtlcompiler.vtlsqlbuilder.TranslationFactory;
import com.capgemini.istat.vtlcompiler.vtlsqlbuilder.model.OperatorTranslation;
import com.capgemini.istat.vtlcompiler.vtlsqlbuilder.service.sqlresult.ISqlResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@Scope("prototype")
public class PersistTranslation implements OperatorTranslation {
    private VtlPersist vtlPersist;
    private TranslationFactory translationFactory;
    private ISqlResultService sqlResultService;

    @Override
    public void setVtlExpression(VtlExpression vtlExpression) {
        this.vtlPersist = (VtlPersist) vtlExpression;
    }

    @Autowired
    public void setTranslationFactory(TranslationFactory translationFactory) {
        this.translationFactory = translationFactory;
    }


    public void setSqlResultService(ISqlResultService sqlResultService) {
        this.sqlResultService = sqlResultService;
    }

    @Override
    public SqlResult translate(Map<KeyVariables, Object> variables) throws Exception {
        SqlResult result = translationFactory.translate(vtlPersist.getVtlExpression(), variables);

        if (result.getSqlDataset().getSqlTables().get(0).getVtlDataset().isOnlyAScalar()) {
            result.getSqlDataset().getComponentList().get(0).setAliasName(vtlPersist.getResultExpression().getResultComponent().getName());
        }

        if (result.getResultList().size() != 0 && result.getResultList().getLast().toString().toLowerCase().contains(vtlPersist.getVtlExpression().getResultExpression().getResult().getName().toLowerCase())) {
            result.setResultList(
                    sqlResultService.assignName(
                            vtlPersist.getVtlExpression().getResultExpression().getResult().getName(),
                            result.getResultList(),
                            vtlPersist.getVtlVarId().getName()
                    )
            );
            result.getResultList().removeLast();
        } else {
            SqlResult resultAssign = sqlResultService.createTable(result.getSqlDataset(), vtlPersist.getVtlVarId().getName(), false);
            result.getResultList().addAll(resultAssign.getResultList());
        }
        return result;
    }
}
