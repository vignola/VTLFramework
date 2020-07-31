package com.capgemini.istat.vtlcompiler.vtlsqlbuilder.expression.analytic;

import com.capgemini.istat.vtlcompiler.vtlcommon.model.KeyVariables;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.translation.SqlResult;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.dataset.VtlComponentId;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.VtlExpression;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.clause.analytic.VtlAnalyticFunctionExpression;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.clause.analytic.VtlOrderByItem;
import com.capgemini.istat.vtlcompiler.vtlsqlbuilder.TranslationFactory;
import com.capgemini.istat.vtlcompiler.vtlsqlbuilder.model.OperatorTranslation;
import com.capgemini.istat.vtlcompiler.vtlsqlbuilder.service.sqlresult.ISqlResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@Scope("prototype")
public class AnalyticFunctionTranslation implements OperatorTranslation {
    private VtlAnalyticFunctionExpression vtlAnalyticFunctionExpression;
    private TranslationFactory translationFactory;
    private ISqlResultService sqlResultService;

    @Override
    public void setVtlExpression(VtlExpression vtlExpression) {
        this.vtlAnalyticFunctionExpression = (VtlAnalyticFunctionExpression) vtlExpression;
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
        SqlResult sqlResult = translationFactory.translate(vtlAnalyticFunctionExpression.getVtlExpression(), variables);
        variables.put(KeyVariables.DATASET_IN_CLAUSE, sqlResult.getSqlDataset());
        if (vtlAnalyticFunctionExpression.getVtlPartitionBy() != null
                && vtlAnalyticFunctionExpression.getVtlPartitionBy().getVtlComponentIds() != null) {
            for (VtlComponentId vtlComponentId : vtlAnalyticFunctionExpression.getVtlPartitionBy().getVtlComponentIds()) {
                translationFactory.translate(vtlComponentId, variables);
            }
        }
        if (vtlAnalyticFunctionExpression.getVltOrderBy() != null
                && vtlAnalyticFunctionExpression.getVltOrderBy().getVtlOrderByItems() != null) {
            for (VtlOrderByItem vtlOrderByItem : vtlAnalyticFunctionExpression.getVltOrderBy().getVtlOrderByItems()) {
                translationFactory.translate(vtlOrderByItem.getVtlComponentId(), variables);
            }
        }
        sqlResult = sqlResultService.applyAnalyticFunction(vtlAnalyticFunctionExpression, sqlResult);
        
        return sqlResult;
    }    
}
