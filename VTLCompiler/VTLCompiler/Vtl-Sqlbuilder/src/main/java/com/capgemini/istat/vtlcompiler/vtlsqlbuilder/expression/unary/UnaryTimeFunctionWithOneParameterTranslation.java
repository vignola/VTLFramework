package com.capgemini.istat.vtlcompiler.vtlsqlbuilder.expression.unary;

import com.capgemini.istat.vtlcompiler.vtlcommon.model.KeyVariables;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.translation.SqlResult;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.VtlExpression;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.time.VtlTimeUnaryWithOneParameterExpression;
import com.capgemini.istat.vtlcompiler.vtlsqlbuilder.TranslationFactory;
import com.capgemini.istat.vtlcompiler.vtlsqlbuilder.model.OperatorTranslation;
import com.capgemini.istat.vtlcompiler.vtlsqlbuilder.service.sqlresult.ISqlResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@Scope("prototype")
public class UnaryTimeFunctionWithOneParameterTranslation implements OperatorTranslation {
    
    private VtlTimeUnaryWithOneParameterExpression vtlTimeUnaryWithOneParameterExpression;
    private TranslationFactory translationFactory;
    private ISqlResultService sqlResultService;
    
    @Override
    public void setVtlExpression(VtlExpression vtlExpression) {
        this.vtlTimeUnaryWithOneParameterExpression = (VtlTimeUnaryWithOneParameterExpression) vtlExpression;
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
        SqlResult sqlResultTranslate = translationFactory.translate(vtlTimeUnaryWithOneParameterExpression.getVtlExpression(), variables);      
        SqlResult sqlResultUnary = sqlResultService.applyTimeWithParameterFunction(vtlTimeUnaryWithOneParameterExpression, sqlResultTranslate);
        return sqlResultUnary;
    }
}
