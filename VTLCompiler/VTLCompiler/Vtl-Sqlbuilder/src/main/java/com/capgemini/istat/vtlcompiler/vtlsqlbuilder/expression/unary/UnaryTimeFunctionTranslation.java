package com.capgemini.istat.vtlcompiler.vtlsqlbuilder.expression.unary;

import com.capgemini.istat.vtlcompiler.vtlcommon.model.KeyVariables;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.operator.Operator;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.translation.SqlResult;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.VtlExpression;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.time.VtlTimeUnaryExpression;
import com.capgemini.istat.vtlcompiler.vtlsqlbuilder.TranslationFactory;
import com.capgemini.istat.vtlcompiler.vtlsqlbuilder.model.OperatorTranslation;
import com.capgemini.istat.vtlcompiler.vtlsqlbuilder.service.sqlresult.ISqlResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@Scope("prototype")
public class UnaryTimeFunctionTranslation implements OperatorTranslation {
    
    private VtlTimeUnaryExpression vtlTimeUnaryExpression;
    private TranslationFactory translationFactory;
    private ISqlResultService sqlResultService;
    
    @Override
    public void setVtlExpression(VtlExpression vtlExpression) {
        this.vtlTimeUnaryExpression = (VtlTimeUnaryExpression) vtlExpression;
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
        SqlResult sqlResultTranslate = translationFactory.translate(vtlTimeUnaryExpression.getVtlExpression(), variables);
        SqlResult sqlResultUnary;
        if (vtlTimeUnaryExpression.getOperator().equals(Operator.FILL_TIME_SERIES_SINGLE) || vtlTimeUnaryExpression.getOperator().equals(Operator.FILL_TIME_SERIES_ALL)) {
            sqlResultUnary = sqlResultService.applyUnaryTimeFunctionFillTimeSeries(vtlTimeUnaryExpression, sqlResultTranslate);
            sqlResultUnary.getResultList().addAll(0, sqlResultTranslate.getResultList());
        } else {
            sqlResultUnary = sqlResultService.applyUnaryTimeFunction(vtlTimeUnaryExpression, sqlResultTranslate);
        }
        return sqlResultUnary;
    }
}
