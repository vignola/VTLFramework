package com.capgemini.istat.vtlcompiler.vtlsqlbuilder.expression.clause.calc;

import com.capgemini.istat.vtlcompiler.vtlcommon.model.KeyVariables;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.translation.SqlComponent;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.translation.SqlDataset;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.translation.SqlResult;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.VtlExpression;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.clause.calc.VtlCalcClauseExpression;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.clause.calc.VtlCalcClauseItemExpression;
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
public class CalcClauseTranslation implements OperatorTranslation {
    private VtlCalcClauseExpression vtlCalcClauseExpression;
    private TranslationFactory translationFactory;

    @Override
    public void setSqlResultService(ISqlResultService sqlResultService) {
        //non ce n'è bisogno
    }

    @Override
    public void setVtlExpression(VtlExpression vtlExpression) {
        this.vtlCalcClauseExpression = (VtlCalcClauseExpression) vtlExpression;
    }

    @Autowired
    public void setTranslationFactory(TranslationFactory translationFactory) {
        this.translationFactory = translationFactory;
    }


    @Override
    public SqlResult translate(Map<KeyVariables, Object> variables) throws Exception {
        SqlResult sqlResult = new SqlResult();
        SqlDataset sqlDatasetClause;
        if (vtlCalcClauseExpression.getDataset() != null) {
            SqlResult sqlDatasetResult = translationFactory.translate(vtlCalcClauseExpression.getDataset(), variables);
            sqlDatasetClause = sqlDatasetResult.getSqlDataset();
            sqlResult.getResultList().addAll(sqlDatasetResult.getResultList());
            variables.put(KeyVariables.DATASET_IN_CLAUSE, sqlDatasetClause);
        } else {
            sqlDatasetClause = (SqlDataset) variables.get(KeyVariables.DATASET_IN_CLAUSE);
        }

        List<SqlComponent> calcSqlComponentList = new LinkedList<SqlComponent>();
        for (VtlCalcClauseItemExpression vtlCalcClauseItemExpression : vtlCalcClauseExpression.getVtlCalcClauseItemExpressionList()) {
            translationFactory.translate(vtlCalcClauseItemExpression, variables);
            calcSqlComponentList.add(vtlCalcClauseItemExpression.getSqlResult().getSqlComponent());
        }
        
        //se esiste un componente rinominato nella calc lo sostituisce
        for (SqlComponent sqlComponent : sqlDatasetClause.getComponentList()) {
            for (VtlCalcClauseItemExpression vtlCalcClauseItemExpression : vtlCalcClauseExpression.getVtlCalcClauseItemExpressionList()) {
                if (sqlComponent.getVtlComponent().getName().equalsIgnoreCase(vtlCalcClauseItemExpression.getVtlComponentId().getComponentName())) {
                    int index = sqlDatasetClause.getComponentList().indexOf(sqlComponent);
                    
                    for (SqlComponent calcSqlComponent : calcSqlComponentList) {
                        if (calcSqlComponent.getVtlComponent().getName().equalsIgnoreCase(vtlCalcClauseItemExpression.getVtlComponentId().getComponentName())) {
                            sqlDatasetClause.getComponentList().set(index, calcSqlComponent);
                            calcSqlComponentList.remove(calcSqlComponent);
                            break;
                        }
                    }
                    
                    break;
                }
            }
        }
        
        //aggiunge i componenti rimanenti della calc
        for (SqlComponent sqlComponent : calcSqlComponentList) {
            sqlDatasetClause.getComponentList().add(sqlComponent);
        }
        sqlResult.setSqlDataset(sqlDatasetClause);
        return sqlResult;
    }
}
