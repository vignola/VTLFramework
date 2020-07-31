package com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.clause.calc;

import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.VtlExpression;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.clause.VtlDatasetClause;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class VtlCalcClauseExpression extends VtlDatasetClause implements Serializable {
    private List<VtlCalcClauseItemExpression> vtlCalcClauseItemExpressionList;
    private VtlExpression vtlVarId;


    @Override
    public String getType() {
        return VtlCalcClauseExpression.class.toString();
    }

    public List<VtlCalcClauseItemExpression> getVtlCalcClauseItemExpressionList() {
        if (vtlCalcClauseItemExpressionList == null)
            this.vtlCalcClauseItemExpressionList = new ArrayList<>();
        return vtlCalcClauseItemExpressionList;
    }

    public void setVtlCalcClauseItemExpressionList(List<VtlCalcClauseItemExpression> vtlCalcClauseItemExpressionList) {
        this.vtlCalcClauseItemExpressionList = vtlCalcClauseItemExpressionList;
    }

    @Override
    public void setDataset(VtlExpression vtlExpression) {
        this.vtlVarId = vtlExpression;
    }

    public VtlExpression getDataset() {
        return vtlVarId;
    }
}
