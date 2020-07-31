package com.capgemini.istat.vtlcompiler.vtlsqlbuilder.expression.binary;

import com.capgemini.istat.vtlcompiler.vtlcommon.model.KeyVariables;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.translation.SqlResult;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.VtlExpression;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.string.VtlStringBinaryExpression;
import com.capgemini.istat.vtlcompiler.vtlsqlbuilder.TranslationFactory;
import com.capgemini.istat.vtlcompiler.vtlsqlbuilder.model.OperatorTranslation;
import com.capgemini.istat.vtlcompiler.vtlsqlbuilder.service.sqlresult.ISqlResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@Scope("prototype")
public class StringBinaryTranslation implements OperatorTranslation {

    private VtlStringBinaryExpression vtlStringBinaryExpression;
    private TranslationFactory translationFactory;
    private ISqlResultService sqlResultService;

    @Override
    public void setVtlExpression(VtlExpression vtlExpression) {
        this.vtlStringBinaryExpression = (VtlStringBinaryExpression) vtlExpression;
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
        SqlResult sqlResultLeft = translationFactory.translate(vtlStringBinaryExpression.getLeftExpression(), variables);
        SqlResult sqlResultRight = translationFactory.translate(vtlStringBinaryExpression.getRightExpression(), variables);
        Boolean isWhereCondition = (Boolean) variables.getOrDefault(KeyVariables.FILTER_CLAUSE, false);
        SqlResult sqlResult;
        if (isWhereCondition) {
            sqlResult = sqlResultService.applyStringBinaryCondition(vtlStringBinaryExpression, sqlResultLeft, sqlResultRight, vtlStringBinaryExpression.getOperator());           
        } else {
            sqlResult = sqlResultService.applyBinaryFunction(vtlStringBinaryExpression, sqlResultLeft, sqlResultRight, vtlStringBinaryExpression.getOperator());
        }
        sqlResult.getResultList().addAll(0, sqlResultRight.getResultList());
        sqlResult.getResultList().addAll(0, sqlResultLeft.getResultList());
        return sqlResult;
    }
}    