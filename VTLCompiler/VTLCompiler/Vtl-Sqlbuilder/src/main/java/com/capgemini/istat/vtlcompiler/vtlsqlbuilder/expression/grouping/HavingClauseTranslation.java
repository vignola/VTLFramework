package com.capgemini.istat.vtlcompiler.vtlsqlbuilder.expression.grouping;

import com.capgemini.istat.vtlcompiler.vtlcommon.model.KeyVariables;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.translation.SqlResult;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.constant.VtlConstantExpression;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.dataset.VtlComponentId;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.VtlExpression;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.grouping.VtlHavingClauseExpression;
import com.capgemini.istat.vtlcompiler.vtlsqlbuilder.TranslationFactory;
import com.capgemini.istat.vtlcompiler.vtlsqlbuilder.model.OperatorTranslation;
import com.capgemini.istat.vtlcompiler.vtlsqlbuilder.service.sqlresult.ISqlResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@Scope("prototype")
public class HavingClauseTranslation implements OperatorTranslation {
    private VtlHavingClauseExpression vtlHavingClauseExpression;
    private TranslationFactory translationFactory;
    private ISqlResultService sqlResultService;

    @Override
    public void setVtlExpression(VtlExpression vtlExpression) {
        this.vtlHavingClauseExpression = (VtlHavingClauseExpression) vtlExpression;
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
        variables.put(KeyVariables.FILTER_CLAUSE, true);
        SqlResult sqlResult = translationFactory.translate(vtlHavingClauseExpression.getVtlExpression(), variables);

        sqlResult.getSqlDataset().setHavingCondition(sqlResult.getSqlComponent().getResult());
        
        if(vtlHavingClauseExpression.getVtlExpression().getType() != null &&
        (vtlHavingClauseExpression.getVtlExpression().getType().equalsIgnoreCase(VtlComponentId.VTL_OBJECT_TYPE)
        || vtlHavingClauseExpression.getVtlExpression().getType().equalsIgnoreCase(VtlConstantExpression.VTL_OBJECT_TYPE))) {
            sqlResult = sqlResultService.applyDefaultBooleanUnaryConditionToSqlObject(sqlResult, vtlHavingClauseExpression.getVtlExpression());
        }
        
        return sqlResult;
    }
}