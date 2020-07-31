package com.capgemini.istat.vtlcompiler.vtlsqlbuilder.expression.clause.filter;

import com.capgemini.istat.vtlcompiler.vtlcommon.model.KeyVariables;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.translation.SqlDataset;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.translation.SqlResult;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.constant.VtlConstantExpression;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.dataset.VtlComponentId;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.VtlExpression;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.clause.filter.VtlFilterClauseExpression;
import com.capgemini.istat.vtlcompiler.vtlsqlbuilder.TranslationFactory;
import com.capgemini.istat.vtlcompiler.vtlsqlbuilder.model.OperatorTranslation;
import com.capgemini.istat.vtlcompiler.vtlsqlbuilder.service.sqlresult.ISqlResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@Scope("prototype")
public class FilterClauseTranslation implements OperatorTranslation {
    private VtlFilterClauseExpression vtlFilterClauseExpression;
    private TranslationFactory translationFactory;
    private ISqlResultService sqlResultService;



    @Override
    public void setVtlExpression(VtlExpression vtlExpression) {
        this.vtlFilterClauseExpression = (VtlFilterClauseExpression) vtlExpression;
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
        if (vtlFilterClauseExpression.getDatasetClause() != null) {
            SqlResult datasetClauseResult = translationFactory.translate(vtlFilterClauseExpression.getDatasetClause(), variables);
            variables.put(KeyVariables.DATASET_IN_CLAUSE, datasetClauseResult.getSqlDataset());
        } else {
            sqlResult.setSqlDataset((SqlDataset) variables.get(KeyVariables.DATASET_IN_CLAUSE));
        }

        variables.put(KeyVariables.FILTER_CLAUSE, true);
        SqlResult result = translationFactory.translate(vtlFilterClauseExpression.getFilterExpression(), variables);
        if (result.getSqlDataset() == null)
            result.setSqlDataset((SqlDataset) (variables.get(KeyVariables.DATASET_IN_CLAUSE)));
        result.getSqlDataset().setWhereCondition(result.getSqlComponent().getResult());
        
        if(vtlFilterClauseExpression.getFilterExpression().getType() != null &&
        (vtlFilterClauseExpression.getFilterExpression().getType().equalsIgnoreCase(VtlComponentId.VTL_OBJECT_TYPE)
        || vtlFilterClauseExpression.getFilterExpression().getType().equalsIgnoreCase(VtlConstantExpression.VTL_OBJECT_TYPE))) {
            result = sqlResultService.applyDefaultBooleanUnaryConditionToSqlObject(result, vtlFilterClauseExpression.getFilterExpression());
        }
        variables.remove(KeyVariables.FILTER_CLAUSE);
        return result;
    }    
}
