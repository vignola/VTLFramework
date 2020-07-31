package com.capgemini.istat.vtlcompiler.vtlsqlbuilder.expression.conditional;

import com.capgemini.istat.vtlcompiler.vtlcommon.model.KeyVariables;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.translation.SqlResult;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.VtlExpression;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.conditional.VtlIfExpression;
import com.capgemini.istat.vtlcompiler.vtlsqlbuilder.TranslationFactory;
import com.capgemini.istat.vtlcompiler.vtlsqlbuilder.model.OperatorTranslation;
import com.capgemini.istat.vtlcompiler.vtlsqlbuilder.service.sqlresult.ISqlResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@Scope("prototype")
public class IfTranslation implements OperatorTranslation {
    private VtlIfExpression vtlIfExpression;
    private TranslationFactory translationFactory;
    private ISqlResultService sqlResultService;

    @Override
    public void setVtlExpression(VtlExpression vtlExpression) {
        this.vtlIfExpression = (VtlIfExpression) vtlExpression;
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
        SqlResult sqlResultThen = translationFactory.translate(vtlIfExpression.getThenExpr(), variables);
        SqlResult sqlResultElse = translationFactory.translate(vtlIfExpression.getElseExpr(), variables);
        SqlResult sqlResultCondition = translationFactory.translate(vtlIfExpression.getCondition(), variables);
        Boolean isWhereCondition = (Boolean) variables.getOrDefault(KeyVariables.FILTER_CLAUSE, false);        
        SqlResult sqlResult = sqlResultService.applyIf(vtlIfExpression, sqlResultCondition, sqlResultThen, sqlResultElse, isWhereCondition);
        return sqlResult;
    }
}
