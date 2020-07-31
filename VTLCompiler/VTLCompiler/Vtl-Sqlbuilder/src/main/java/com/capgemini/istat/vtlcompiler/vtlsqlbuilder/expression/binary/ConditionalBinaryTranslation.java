package com.capgemini.istat.vtlcompiler.vtlsqlbuilder.expression.binary;

import com.capgemini.istat.vtlcompiler.vtlcommon.model.KeyVariables;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.translation.SqlResult;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.VtlExpression;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.conditional.VtlConditionalBinaryExpression;
import com.capgemini.istat.vtlcompiler.vtlsqlbuilder.TranslationFactory;
import com.capgemini.istat.vtlcompiler.vtlsqlbuilder.model.OperatorTranslation;
import com.capgemini.istat.vtlcompiler.vtlsqlbuilder.service.sqlresult.ISqlResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@Scope("prototype")
public class ConditionalBinaryTranslation implements OperatorTranslation {
    private VtlConditionalBinaryExpression vtlConditionalBinaryExpression;
    private TranslationFactory translationFactory;
    private ISqlResultService sqlResultService;

    @Override
    public void setVtlExpression(VtlExpression vtlExpression) {
        this.vtlConditionalBinaryExpression = (VtlConditionalBinaryExpression) vtlExpression;
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
        SqlResult sqlResultLeft = translationFactory.translate(vtlConditionalBinaryExpression.getLeftExpression(), variables);
        SqlResult sqlResultRight = translationFactory.translate(vtlConditionalBinaryExpression.getRightExpression(), variables);
        Boolean isWhereCondition = (Boolean) variables.getOrDefault(KeyVariables.FILTER_CLAUSE, false);
        SqlResult sqlResult;
        if (isWhereCondition) {
            sqlResult = sqlResultService.applyConditionalBinaryCondition(vtlConditionalBinaryExpression, sqlResultLeft, sqlResultRight, vtlConditionalBinaryExpression.getOperator());
        } else {
            sqlResult = sqlResultService.applyBinaryFunction(vtlConditionalBinaryExpression, sqlResultLeft, sqlResultRight, vtlConditionalBinaryExpression.getOperator());
        }
        sqlResult.getResultList().addAll(0, sqlResultRight.getResultList());
        sqlResult.getResultList().addAll(0, sqlResultLeft.getResultList());
        return sqlResult;
    }
}
