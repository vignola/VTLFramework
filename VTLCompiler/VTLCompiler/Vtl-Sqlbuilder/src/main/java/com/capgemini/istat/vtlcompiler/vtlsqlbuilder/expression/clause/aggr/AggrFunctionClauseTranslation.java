package com.capgemini.istat.vtlcompiler.vtlsqlbuilder.expression.clause.aggr;

import com.capgemini.istat.vtlcompiler.vtlcommon.model.KeyVariables;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.translation.SqlComponent;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.translation.SqlResult;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.VtlExpression;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.clause.aggr.VtlAggrFunctionClauseExpression;
import com.capgemini.istat.vtlcompiler.vtlsqlbuilder.TranslationFactory;
import com.capgemini.istat.vtlcompiler.vtlsqlbuilder.model.OperatorTranslation;
import com.capgemini.istat.vtlcompiler.vtlsqlbuilder.service.sqlresult.ISqlResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@Scope("prototype")
public class AggrFunctionClauseTranslation implements OperatorTranslation {
    private VtlAggrFunctionClauseExpression vtlAggrFunctionClauseExpression;
    private TranslationFactory translationFactory;

    @Override
    public void setVtlExpression(VtlExpression vtlExpression) {
        this.vtlAggrFunctionClauseExpression = (VtlAggrFunctionClauseExpression) vtlExpression;
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
        SqlResult sqlResult = translationFactory.translate(vtlAggrFunctionClauseExpression.getVtlAggrComp(), variables);
        
        SqlComponent aggrSqlComponent = sqlResult.getSqlComponent();
        aggrSqlComponent.setAliasName(vtlAggrFunctionClauseExpression.getVtlComponentId().getComponentName());
        aggrSqlComponent.setVtlComponent(vtlAggrFunctionClauseExpression.getResultExpression().getResultComponent());
        return sqlResult;
    }
}