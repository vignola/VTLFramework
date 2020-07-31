package com.capgemini.istat.vtlcompiler.vtlsemantic.expression.clause.filter;

import com.capgemini.istat.vtlcompiler.vtlcommon.model.KeyVariables;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.dataset.VtlDataset;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.operator.OperatorValidation;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.semantic.ResultExpression;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.VtlExpression;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.clause.filter.VtlFilterClauseExpression;
import com.capgemini.istat.vtlcompiler.vtlcommon.service.DatasetUtilityService;
import com.capgemini.istat.vtlcompiler.vtlsemantic.SemanticFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.Map;

@Component
@Scope("prototype")
public class FilterClauseValidation implements OperatorValidation {
    private VtlFilterClauseExpression vtlFilterClauseExpression;
    private SemanticFactory semanticFactory;
    private DatasetUtilityService datasetUtilityService;

    @Override
    public void setVtlExpression(VtlExpression vtlExpression) {
        this.vtlFilterClauseExpression = (VtlFilterClauseExpression) vtlExpression;
    }

    @Autowired
    public void setSemanticFactory(SemanticFactory semanticFactory) {
        this.semanticFactory = semanticFactory;
    }

    @Autowired
    public void setDatasetUtilityService(DatasetUtilityService datasetUtilityService) {
        this.datasetUtilityService = datasetUtilityService;
    }

    @Override
    public LinkedList<ResultExpression> resolve(Map<KeyVariables, Object> variables) throws Exception {
        LinkedList<ResultExpression> resultExpressions = new LinkedList<>();
        VtlDataset vtlDataset = (VtlDataset) variables.get(KeyVariables.JOIN_RESULT);
        if (vtlDataset == null) {
            LinkedList<ResultExpression> datasetFilter = semanticFactory.checkSemantic(vtlFilterClauseExpression.getDatasetClause(), variables);
            vtlDataset = datasetFilter.getFirst().getResult();
            resultExpressions.addAll(datasetFilter);
        }
        variables.put(KeyVariables.DATASET_IN_CLAUSE, vtlDataset);
        LinkedList<ResultExpression> filterResult = semanticFactory.checkSemantic(vtlFilterClauseExpression.getFilterExpression(), variables);
        variables.remove(KeyVariables.DATASET_IN_CLAUSE);
        resultExpressions.addAll(0, filterResult);
        //restituisco un nuovo dataset sostanzialmente invariato per simulare la filter
        ResultExpression resultExpression = new ResultExpression();
        resultExpression.setResult(datasetUtilityService.createTemporaryDataset(vtlDataset.getVtlComponents(), false));
        resultExpressions.addFirst(resultExpression);
        resultExpressions.getFirst().setCommand(vtlFilterClauseExpression.getCommand());
        return resultExpressions;
    }
}
