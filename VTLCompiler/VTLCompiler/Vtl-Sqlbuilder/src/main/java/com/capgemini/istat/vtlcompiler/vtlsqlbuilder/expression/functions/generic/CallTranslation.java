package com.capgemini.istat.vtlcompiler.vtlsqlbuilder.expression.functions.generic;

import com.capgemini.istat.vtlcompiler.vtlcommon.model.KeyVariables;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.translation.SqlResult;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.constant.VtlOptional;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.define.operator.VtlFunctionParameter;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.VtlExpression;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.generic.VtlCallExpression;
import com.capgemini.istat.vtlcompiler.vtlsqlbuilder.TranslationFactory;
import com.capgemini.istat.vtlcompiler.vtlsqlbuilder.model.OperatorTranslation;
import com.capgemini.istat.vtlcompiler.vtlsqlbuilder.service.sqlresult.ISqlResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@Scope("prototype")
public class CallTranslation implements OperatorTranslation {
    private VtlCallExpression vtlCallExpression;
    private TranslationFactory translationFactory;

    @Override
    public void setVtlExpression(VtlExpression vtlExpression) {
        this.vtlCallExpression = (VtlCallExpression) vtlExpression;
    }

    @Autowired
    public void setTranslationFactory(TranslationFactory translationFactory) {
        this.translationFactory = translationFactory;
    }


    public void setSqlResultService(ISqlResultService sqlResultService) {
        //non ce n'è bisogno
    }
    
    @Override
    public SqlResult translate(Map<KeyVariables, Object> variables) throws Exception {
        SqlResult sqlResult;
        for (VtlExpression vtlExpression : vtlCallExpression.getParameters()) {
            if (!(vtlExpression instanceof VtlOptional))
                translationFactory.translate(vtlExpression, variables);
        }
        for (VtlFunctionParameter vtlFunctionParameter : vtlCallExpression.getVtlUserFunction().getVtlFunctionParameters()) {
            if (vtlFunctionParameter.getDefaultValue() != null)
                translationFactory.translate(vtlFunctionParameter.getDefaultValue(), variables);
        }

        variables.put(KeyVariables.PARAMETER_MAP, vtlCallExpression.getParameterMap());
        
        sqlResult = translationFactory.translate(vtlCallExpression.getVtlUserFunction().getVtlExpression(), variables);
        
        variables.remove(KeyVariables.PARAMETER_MAP);
        
        return sqlResult;
    }  
}
