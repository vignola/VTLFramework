package com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.clause.aggr;

import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.VtlExpression;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class VtlAggregateClauseExpression extends VtlExpression implements Serializable {
    private List<VtlAggrFunctionClauseExpression> vtlAggrFunctionClauseExpressionList = new ArrayList<>();

    public List<VtlAggrFunctionClauseExpression> getVtlAggrFunctionClauseExpressionList() {
        return vtlAggrFunctionClauseExpressionList;
    }

    public void setVtlAggrFunctionClauseExpressionList(List<VtlAggrFunctionClauseExpression> vtlAggrFunctionClauseExpressionList) {
        this.vtlAggrFunctionClauseExpressionList = vtlAggrFunctionClauseExpressionList;
    }


}
