package com.capgemini.istat.vtlcompiler.vtlsqlbuilder.expression.binary;

import com.capgemini.istat.vtlcompiler.vtlcommon.model.KeyVariables;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.translation.SqlResult;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.VtlExpression;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.booleans.VtlBooleanBinaryExpression;
import com.capgemini.istat.vtlcompiler.vtlsqlbuilder.TranslationFactory;
import com.capgemini.istat.vtlcompiler.vtlsqlbuilder.model.OperatorTranslation;
import com.capgemini.istat.vtlcompiler.vtlsqlbuilder.service.sqlresult.ISqlResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@Scope("prototype")
public class BooleanBinaryTranslation implements OperatorTranslation {

    private VtlBooleanBinaryExpression vtlBooleanBinaryExpression;
    private TranslationFactory translationFactory;
    private ISqlResultService sqlResultService;

    @Override
    public void setVtlExpression(VtlExpression vtlExpression) {
        this.vtlBooleanBinaryExpression = (VtlBooleanBinaryExpression) vtlExpression;
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
        SqlResult sqlResultLeft = translationFactory.translate(vtlBooleanBinaryExpression.getLeftExpression(), variables);
        SqlResult sqlResultRight = translationFactory.translate(vtlBooleanBinaryExpression.getRightExpression(), variables);
        SqlResult sqlResult;
        Boolean isWhereCondition = (Boolean) variables.getOrDefault(KeyVariables.FILTER_CLAUSE, false);
        if (isWhereCondition) {
            sqlResult = sqlResultService.applyBooleanBinaryCondition(vtlBooleanBinaryExpression, sqlResultLeft, sqlResultRight, vtlBooleanBinaryExpression.getOperator());           
        } else {
            sqlResult = sqlResultService.applyBinaryFunction(vtlBooleanBinaryExpression, sqlResultLeft, sqlResultRight, vtlBooleanBinaryExpression.getOperator());           
        }
        sqlResult.getResultList().addAll(0, sqlResultRight.getResultList());
        sqlResult.getResultList().addAll(0, sqlResultLeft.getResultList());
        return sqlResult;
    }
}