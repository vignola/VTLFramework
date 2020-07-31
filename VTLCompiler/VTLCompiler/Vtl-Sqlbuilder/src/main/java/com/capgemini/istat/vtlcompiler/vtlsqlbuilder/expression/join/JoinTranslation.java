package com.capgemini.istat.vtlcompiler.vtlsqlbuilder.expression.join;

import com.capgemini.istat.vtlcompiler.vtlcommon.model.KeyVariables;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.translation.SqlResult;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.VtlExpression;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.join.VtlJoinExpression;
import com.capgemini.istat.vtlcompiler.vtlsqlbuilder.TranslationFactory;
import com.capgemini.istat.vtlcompiler.vtlsqlbuilder.model.OperatorTranslation;
import com.capgemini.istat.vtlcompiler.vtlsqlbuilder.service.sqlresult.ISqlResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@Scope("prototype")
public class JoinTranslation implements OperatorTranslation {
    private VtlJoinExpression vtlJoinExpression;
    private TranslationFactory translationFactory;
    private ISqlResultService sqlResultService;


    @Override
    public void setVtlExpression(VtlExpression vtlExpression) {
        this.vtlJoinExpression = (VtlJoinExpression) vtlExpression;
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
        SqlResult sqlResult = new SqlResult();
        variables.put(KeyVariables.JOIN_OPERATOR, vtlJoinExpression.getOperator());
        SqlResult sqlResultSelectClause = translationFactory.translate(vtlJoinExpression.getVtlJoinSelectClause(), variables);
        sqlResult.getResultList().addAll(sqlResultSelectClause.getResultList());
        if (vtlJoinExpression.getVtlJoinBody() != null) {
            variables.put(KeyVariables.DATASET_IN_CLAUSE, sqlResultSelectClause.getSqlDataset());
            SqlResult sqlResultBody = translationFactory.translate(vtlJoinExpression.getVtlJoinBody(), variables);
            sqlResult.setSqlDataset(sqlResultBody.getSqlDataset());
            sqlResult.getResultList().addAll(sqlResultBody.getResultList());
            variables.remove(KeyVariables.DATASET_IN_CLAUSE);
        }
        SqlResult sqlResultClean = sqlResultService.cleanJoinNames(vtlJoinExpression);

        sqlResult.setSqlDataset(sqlResultClean.getSqlDataset());
        sqlResult.getResultList().addAll(sqlResultClean.getResultList());

        return sqlResult;
    }
}
