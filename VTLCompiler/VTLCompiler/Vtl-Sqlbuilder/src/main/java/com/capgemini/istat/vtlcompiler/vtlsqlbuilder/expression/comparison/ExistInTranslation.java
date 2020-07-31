package com.capgemini.istat.vtlcompiler.vtlsqlbuilder.expression.comparison;

import com.capgemini.istat.vtlcompiler.vtlcommon.model.KeyVariables;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.translation.SqlResult;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.VtlExpression;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.comparison.VtlExistIn;
import com.capgemini.istat.vtlcompiler.vtlsqlbuilder.TranslationFactory;
import com.capgemini.istat.vtlcompiler.vtlsqlbuilder.model.OperatorTranslation;
import com.capgemini.istat.vtlcompiler.vtlsqlbuilder.service.sqlresult.ISqlResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@Scope("prototype")
public class ExistInTranslation implements OperatorTranslation {
    private VtlExistIn vtlExistIn;
    private TranslationFactory translationFactory;
    private ISqlResultService sqlResultService;

    @Override
    public void setVtlExpression(VtlExpression vtlExpression) {
        this.vtlExistIn = (VtlExistIn) vtlExpression;
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
        SqlResult sqlResultLeft = translationFactory.translate(vtlExistIn.getLeftExpression(), variables);
        SqlResult sqlResultRight = translationFactory.translate(vtlExistIn.getRightExpression(), variables);

        SqlResult sqlResult = sqlResultService.applyExistIn(vtlExistIn);
        sqlResult.getResultList().addAll(0, sqlResultRight.getResultList());
        sqlResult.getResultList().addAll(0, sqlResultLeft.getResultList());
        return sqlResult;
    }  
}
