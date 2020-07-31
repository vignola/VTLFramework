package com.capgemini.istat.vtlcompiler.vtlsqlbuilder.expression.clause.aggr;

import com.capgemini.istat.vtlcompiler.vtlcommon.model.KeyVariables;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.translation.SqlComponent;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.translation.SqlDataset;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.translation.SqlResult;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.VtlExpression;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.clause.aggr.VtlAggrFunctionClauseExpression;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.clause.aggr.VtlAggregateClauseExpression;
import com.capgemini.istat.vtlcompiler.vtlsqlbuilder.TranslationFactory;
import com.capgemini.istat.vtlcompiler.vtlsqlbuilder.model.OperatorTranslation;
import com.capgemini.istat.vtlcompiler.vtlsqlbuilder.service.sqlresult.ISqlResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Component
@Scope("prototype")
public class AggregateClauseTranslation implements OperatorTranslation {
    private VtlAggregateClauseExpression vtlAggregateClauseExpression;
    private TranslationFactory translationFactory;
    private ISqlResultService sqlResultService;

    @Override
    public void setVtlExpression(VtlExpression vtlExpression) {
        this.vtlAggregateClauseExpression = (VtlAggregateClauseExpression) vtlExpression;
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
        SqlResult sqlResult = null;
        List<SqlComponent> aggrComponentList = new LinkedList<>();
        
        for (VtlAggrFunctionClauseExpression vtlAggrFunctionClauseExpression : vtlAggregateClauseExpression.getVtlAggrFunctionClauseExpressionList()) {
            sqlResult = translationFactory.translate(vtlAggrFunctionClauseExpression, variables);
            aggrComponentList.add(sqlResult.getSqlComponent());
        }

        sqlResult = sqlResultService.aggregateSqlComponent(vtlAggregateClauseExpression.getResultExpression().getResult(), aggrComponentList, (SqlDataset) variables.get(KeyVariables.DATASET_IN_CLAUSE));

        return sqlResult;
    }
}