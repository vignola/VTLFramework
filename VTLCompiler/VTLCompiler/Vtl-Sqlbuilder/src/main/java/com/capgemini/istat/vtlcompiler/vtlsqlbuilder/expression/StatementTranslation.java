package com.capgemini.istat.vtlcompiler.vtlsqlbuilder.expression;

import com.capgemini.istat.vtlcompiler.vtlcommon.model.KeyVariables;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.translation.SqlResult;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.VtlExpression;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.statement.VtlStatement;
import com.capgemini.istat.vtlcompiler.vtlsqlbuilder.TranslationFactory;
import com.capgemini.istat.vtlcompiler.vtlsqlbuilder.model.OperatorTranslation;
import com.capgemini.istat.vtlcompiler.vtlsqlbuilder.service.sqlresult.ISqlResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@Scope("prototype")
public class StatementTranslation implements OperatorTranslation {
    private TranslationFactory translationFactory;
    private VtlStatement vtlStatement;

    @Override
    public void setSqlResultService(ISqlResultService sqlResultService) {
        //non ce n'è bisogno
    }

    @Override
    public void setVtlExpression(VtlExpression vtlExpression) {
        this.vtlStatement = (VtlStatement) vtlExpression;
    }

    @Autowired
    public void setTranslationFactory(TranslationFactory translationFactory) {
        this.translationFactory = translationFactory;
    }

    @Override
    public SqlResult translate(Map<KeyVariables, Object> variables) throws Exception {
        SqlResult sqlResult = (SqlResult) translationFactory.translate(vtlStatement.getVtlExpression(), variables);
        return sqlResult;
    }
}
