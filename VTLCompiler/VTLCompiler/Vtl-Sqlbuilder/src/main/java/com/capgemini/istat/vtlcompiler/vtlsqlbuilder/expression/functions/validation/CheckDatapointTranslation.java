package com.capgemini.istat.vtlcompiler.vtlsqlbuilder.expression.functions.validation;

import com.capgemini.istat.vtlcompiler.vtlcommon.model.KeyVariables;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.translation.SqlDataset;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.translation.SqlResult;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.dataset.VtlComponentId;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.define.ruleset.datapoint.VtlDPRule;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.VtlExpression;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.validation.VtlCheckDatapoint;
import com.capgemini.istat.vtlcompiler.vtlsqlbuilder.TranslationFactory;
import com.capgemini.istat.vtlcompiler.vtlsqlbuilder.model.OperatorTranslation;
import com.capgemini.istat.vtlcompiler.vtlsqlbuilder.service.sqlresult.ISqlResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.Map;

@Component
@Scope("prototype")
public class CheckDatapointTranslation implements OperatorTranslation {
    private VtlCheckDatapoint vtlCheckDatapoint;
    private TranslationFactory translationFactory;
    private ISqlResultService sqlResultService;

    @Override
    public void setVtlExpression(VtlExpression vtlExpression) {
        this.vtlCheckDatapoint = (VtlCheckDatapoint) vtlExpression;
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
        SqlResult result = new SqlResult();
        SqlResult sqlResultDataset = translationFactory.translate(vtlCheckDatapoint.getDatasetToCheck(), variables);
        result.getResultList().addAll(sqlResultDataset.getResultList());
        SqlDataset sqlDataset = sqlResultDataset.getSqlDataset();
        variables.put(KeyVariables.DATASET_IN_CLAUSE, sqlDataset);
        LinkedList<SqlResult> sqlResultsComponent = new LinkedList<>();
        for (VtlComponentId vtlComponentId : vtlCheckDatapoint.getComponentIds()) {
            sqlResultsComponent.addLast(
                    translationFactory.translate(vtlComponentId, variables)
            );
        }
        variables.put(KeyVariables.PARAMETER_MAP, vtlCheckDatapoint.getParameterMapping());
        LinkedList<VtlDPRule> vtlDPRules = vtlCheckDatapoint.getVtlDatapointRuleset().getDpRules();
        variables.put(KeyVariables.FILTER_CLAUSE, true);
        for (VtlDPRule vtlDPRule : vtlDPRules) {
            if (vtlDPRule.getAntecedentCondition() != null)
                translationFactory.translate(vtlDPRule.getAntecedentCondition(), variables);
            translationFactory.translate(vtlDPRule.getConsequentCondition(), variables);
        }
        variables.remove(KeyVariables.PARAMETER_MAP);
        variables.remove(KeyVariables.FILTER_CLAUSE);
        SqlResult sqlResult = sqlResultService.checkDatapoint(vtlCheckDatapoint);
        result.getResultList().addAll(sqlResult.getResultList());
        result.setSqlDataset(sqlResult.getSqlDataset());
        return result;
    }
}
