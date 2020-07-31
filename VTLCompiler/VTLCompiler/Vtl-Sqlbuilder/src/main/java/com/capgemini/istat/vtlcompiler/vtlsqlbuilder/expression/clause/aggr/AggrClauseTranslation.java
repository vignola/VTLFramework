package com.capgemini.istat.vtlcompiler.vtlsqlbuilder.expression.clause.aggr;

import com.capgemini.istat.vtlcompiler.vtlcommon.model.KeyVariables;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.operator.Operator;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.translation.SqlResult;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.VtlExpression;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.clause.aggr.VtlAggrClauseExpression;
import com.capgemini.istat.vtlcompiler.vtlsqlbuilder.TranslationFactory;
import com.capgemini.istat.vtlcompiler.vtlsqlbuilder.model.OperatorTranslation;
import com.capgemini.istat.vtlcompiler.vtlsqlbuilder.service.sqlresult.ISqlResultService;
import com.healthmarketscience.sqlbuilder.SqlObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Component
@Scope("prototype")
public class AggrClauseTranslation implements OperatorTranslation {
    private VtlAggrClauseExpression vtlAggrClauseExpression;
    private TranslationFactory translationFactory;
    private ISqlResultService sqlResultService;

    @Override
    public void setVtlExpression(VtlExpression vtlExpression) {
        this.vtlAggrClauseExpression = (VtlAggrClauseExpression) vtlExpression;
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
        List<SqlObject> results = new LinkedList<>();
        if (vtlAggrClauseExpression.getVtlDatasetClause() != null) {
            SqlResult datasetClauseResult = translationFactory.translate(vtlAggrClauseExpression.getVtlDatasetClause(), variables);
            results = datasetClauseResult.getResultList();
            variables.put(KeyVariables.DATASET_IN_CLAUSE, datasetClauseResult.getSqlDataset());
        }
        SqlResult sqlResult = translationFactory.translate(vtlAggrClauseExpression.getVtlAggregateClauseExpression(), variables);

        if(vtlAggrClauseExpression.getVtlHavingClauseExpression() != null) {
            SqlResult sqlResultHaving = translationFactory.translate(vtlAggrClauseExpression.getVtlHavingClauseExpression(), variables);
            sqlResult.getSqlDataset().setHavingCondition(sqlResultHaving.getSqlDataset().getHavingCondition());
        }

        if(vtlAggrClauseExpression.getVtlGroupClauseExpression() != null) {
            SqlResult sqlResultGroupAll = null;
            if (vtlAggrClauseExpression.getVtlGroupClauseExpression().getGroupType().equals(Operator.GROUP_ALL)) {
                sqlResultGroupAll = translationFactory.translate(vtlAggrClauseExpression.getVtlGroupClauseExpression().getVtlExpression(), variables);
            }
            sqlResult = sqlResultService.applyGroupClause(vtlAggrClauseExpression.getResultExpression().getResult(), sqlResult, sqlResultGroupAll);
        }

        sqlResult.getResultList().addAll(0, results);
        return sqlResult;
    }
}