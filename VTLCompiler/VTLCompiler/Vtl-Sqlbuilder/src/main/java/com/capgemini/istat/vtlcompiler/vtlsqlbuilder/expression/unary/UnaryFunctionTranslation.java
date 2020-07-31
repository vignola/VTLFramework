package com.capgemini.istat.vtlcompiler.vtlsqlbuilder.expression.unary;

import com.capgemini.istat.vtlcompiler.vtlcommon.model.KeyVariables;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.translation.SqlResult;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.VtlExpression;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.function.VtlUnaryExpression;
import com.capgemini.istat.vtlcompiler.vtlsqlbuilder.TranslationFactory;
import com.capgemini.istat.vtlcompiler.vtlsqlbuilder.model.OperatorTranslation;
import com.capgemini.istat.vtlcompiler.vtlsqlbuilder.service.sqlresult.ISqlResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@Scope("prototype")
public class UnaryFunctionTranslation implements OperatorTranslation {

    private VtlUnaryExpression vtlUnaryExpression;
    private TranslationFactory translationFactory;
    private ISqlResultService sqlResultService;


    @Override
    public void setVtlExpression(VtlExpression vtlExpression) {
        this.vtlUnaryExpression = (VtlUnaryExpression) vtlExpression;
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
        SqlResult sqlResultTranslate = translationFactory.translate(vtlUnaryExpression.getVtlExpression(), variables);
        SqlResult sqlResultUnary = sqlResultService.applyUnaryFunction(vtlUnaryExpression, sqlResultTranslate);
        return sqlResultUnary;
    }
}
