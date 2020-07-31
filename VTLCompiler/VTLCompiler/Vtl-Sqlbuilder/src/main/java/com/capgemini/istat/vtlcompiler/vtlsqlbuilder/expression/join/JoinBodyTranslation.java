package com.capgemini.istat.vtlcompiler.vtlsqlbuilder.expression.join;

import com.capgemini.istat.vtlcompiler.vtlcommon.model.KeyVariables;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.translation.SqlDataset;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.translation.SqlResult;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.VtlExpression;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.join.VtlJoinBody;
import com.capgemini.istat.vtlcompiler.vtlsqlbuilder.TranslationFactory;
import com.capgemini.istat.vtlcompiler.vtlsqlbuilder.model.OperatorTranslation;
import com.capgemini.istat.vtlcompiler.vtlsqlbuilder.service.sqlresult.ISqlResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@Scope("prototype")
public class JoinBodyTranslation implements OperatorTranslation {
    private VtlJoinBody vtlJoinBody;
    private TranslationFactory translationFactory;

    @Override
    public void setVtlExpression(VtlExpression vtlExpression) {
        this.vtlJoinBody = (VtlJoinBody) vtlExpression;
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
        SqlResult sqlResult = new SqlResult();

        if (vtlJoinBody.getVtlFilterClauseExpression() != null) {
            sqlResult = translationFactory.translate(vtlJoinBody.getVtlFilterClauseExpression(), variables);
            variables.put(KeyVariables.DATASET_IN_CLAUSE, sqlResult.getSqlDataset());
        }

        if (vtlJoinBody.getVtlJoinBodyClause() != null) {
            sqlResult = translationFactory.translate(vtlJoinBody.getVtlJoinBodyClause(), variables);
            variables.put(KeyVariables.DATASET_IN_CLAUSE, sqlResult.getSqlDataset());
        }

        if (vtlJoinBody.getKeepDropClause() != null) {
            sqlResult = translationFactory.translate(vtlJoinBody.getKeepDropClause(), variables);
            variables.put(KeyVariables.DATASET_IN_CLAUSE, sqlResult.getSqlDataset());
        }

        if (vtlJoinBody.getVtlRenameClauseExpression() != null) {
            sqlResult = translationFactory.translate(vtlJoinBody.getVtlRenameClauseExpression(), variables);
        }

        if (sqlResult.getSqlDataset() == null) {
            sqlResult.setSqlDataset((SqlDataset) variables.get(KeyVariables.DATASET_IN_CLAUSE));
        }
        
        return sqlResult;
    }
}
