package com.capgemini.istat.vtlcompiler.vtlsqlbuilder.expression.functions.validation;

import com.capgemini.istat.vtlcompiler.vtlcommon.model.KeyVariables;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.translation.SqlResult;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.VtlExpression;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.validation.VtlCheck;
import com.capgemini.istat.vtlcompiler.vtlsqlbuilder.TranslationFactory;
import com.capgemini.istat.vtlcompiler.vtlsqlbuilder.model.OperatorTranslation;
import com.capgemini.istat.vtlcompiler.vtlsqlbuilder.service.sqlresult.ISqlResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@Scope("prototype")
public class CheckValidationTranslation implements OperatorTranslation {
    private VtlCheck vtlCheck;
    private TranslationFactory translationFactory;
    private ISqlResultService sqlResultService;

    @Override
    public void setVtlExpression(VtlExpression vtlExpression) {
        this.vtlCheck = (VtlCheck) vtlExpression;
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
        SqlResult sqlResultBoolean = translationFactory.translate(vtlCheck.getBooleanDataset(), variables);
        SqlResult sqlResultImbalance = null;
        if(vtlCheck.getImbalanceExpression() != null) {
            sqlResultImbalance = translationFactory.translate(vtlCheck.getImbalanceExpression(), variables);        
        }
        SqlResult sqlResult = sqlResultService.applyCheck(vtlCheck, sqlResultBoolean, sqlResultImbalance);
        return sqlResult;
    }
}
