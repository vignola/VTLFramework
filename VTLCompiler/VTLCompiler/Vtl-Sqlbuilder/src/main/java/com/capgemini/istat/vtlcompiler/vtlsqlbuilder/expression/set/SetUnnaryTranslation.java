package com.capgemini.istat.vtlcompiler.vtlsqlbuilder.expression.set;

import com.capgemini.istat.vtlcompiler.vtlcommon.model.KeyVariables;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.translation.SqlResult;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.VtlExpression;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.set.VtlSetUnnaryExpression;
import com.capgemini.istat.vtlcompiler.vtlsqlbuilder.TranslationFactory;
import com.capgemini.istat.vtlcompiler.vtlsqlbuilder.model.OperatorTranslation;
import com.capgemini.istat.vtlcompiler.vtlsqlbuilder.service.sqlresult.ISqlResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.Map;

@Component
@Scope("prototype")
public class SetUnnaryTranslation implements OperatorTranslation {
    private VtlSetUnnaryExpression vtlSetUnnaryExpression;
    private TranslationFactory translationFactory;
    private ISqlResultService sqlResultService;

    @Override
    public void setVtlExpression(VtlExpression vtlExpression) {
        this.vtlSetUnnaryExpression = (VtlSetUnnaryExpression) vtlExpression;
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
        SqlResult sqlResult = new SqlResult();
        LinkedList<SqlResult> sqlResultList = new LinkedList<>();
        
        for (VtlExpression vtlExpression : vtlSetUnnaryExpression.getVtlExpressions()) {
            SqlResult expressionResult = translationFactory.translate(vtlExpression, variables);
            sqlResultList.add(expressionResult);
            sqlResult.getResultList().addAll(expressionResult.getResultList());

        }

        SqlResult sqlResultUnnary = sqlResultService.applySetUnnary(vtlSetUnnaryExpression, sqlResultList);
        sqlResult.getResultList().addAll(sqlResultUnnary.getResultList());
        sqlResult.setSqlDataset(sqlResultUnnary.getSqlDataset());
        
        return sqlResult;
    }  
}
