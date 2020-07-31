package com.capgemini.istat.vtlcompiler.vtlsqlbuilder.expression.clause.aggr;

import com.capgemini.istat.vtlcompiler.vtlcommon.model.KeyVariables;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.operator.Operator;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.translation.SqlComponent;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.translation.SqlDataset;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.translation.SqlResult;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.VtlExpression;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.clause.aggr.VtlAggrInvocationExpression;
import com.capgemini.istat.vtlcompiler.vtlsqlbuilder.TranslationFactory;
import com.capgemini.istat.vtlcompiler.vtlsqlbuilder.model.OperatorTranslation;
import com.capgemini.istat.vtlcompiler.vtlsqlbuilder.service.sqlresult.ISqlResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@Scope("prototype")
public class AggrInvocationTranslation implements OperatorTranslation {
    private VtlAggrInvocationExpression vtlAggrInvocationExpression;
    private TranslationFactory translationFactory;
    private ISqlResultService sqlResultService;

    @Override
    public void setVtlExpression(VtlExpression vtlExpression) {
        this.vtlAggrInvocationExpression = (VtlAggrInvocationExpression) vtlExpression;
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
        SqlResult sqlResult;
                
        if(vtlAggrInvocationExpression.getVtlExpression() != null) {
           sqlResult = translationFactory.translate(vtlAggrInvocationExpression.getVtlExpression(), variables);         
        } else {
            //Note that the count operator is used in an aggrClause without parameters,
            sqlResult = new SqlResult();
            SqlComponent sqlComponent = new SqlComponent();
            sqlResult.setSqlComponent(sqlComponent);
            sqlResult.setSqlDataset((SqlDataset) variables.get(KeyVariables.DATASET_IN_CLAUSE));
        }

        if(variables.get(KeyVariables.DATASET_IN_CLAUSE) == null) {
            variables.put(KeyVariables.DATASET_IN_CLAUSE, sqlResult.getSqlDataset());
        }

        if (vtlAggrInvocationExpression.getResultExpression().getResultComponent() == null) {
            if (vtlAggrInvocationExpression.getVtlHavingClauseExpression() != null) {
                SqlResult sqlResultHaving = translationFactory.translate(vtlAggrInvocationExpression.getVtlHavingClauseExpression(), variables);
                sqlResult.getSqlDataset().setHavingCondition(sqlResultHaving.getSqlDataset().getHavingCondition());
            }

            SqlResult sqlResultGroupAll = null;
            if (vtlAggrInvocationExpression.getVtlGroupClauseExpression() != null && vtlAggrInvocationExpression.getVtlGroupClauseExpression().getGroupType().equals(Operator.GROUP_ALL)) {
                sqlResultGroupAll = translationFactory.translate(vtlAggrInvocationExpression.getVtlGroupClauseExpression().getVtlExpression(), variables);
            }
            
            sqlResult = sqlResultService.applyGroupClause(vtlAggrInvocationExpression.getResultExpression().getResult(), sqlResult, sqlResultGroupAll);

        }
        sqlResult = sqlResultService.applyAggregateFunction(vtlAggrInvocationExpression, vtlAggrInvocationExpression.getVtlExpression() != null, sqlResult);

        return sqlResult;
    }
}