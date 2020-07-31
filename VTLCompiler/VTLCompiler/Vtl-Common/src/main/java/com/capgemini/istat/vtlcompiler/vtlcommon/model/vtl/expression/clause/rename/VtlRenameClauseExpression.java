package com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.clause.rename;

import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.VtlExpression;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.clause.VtlDatasetClause;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class VtlRenameClauseExpression extends VtlDatasetClause implements Serializable {

    private VtlExpression vtlDatasetClause;
    private List<VtlRenameClauseItemExpression> vtlRenameClauseItemExpressionList;

    public List<VtlRenameClauseItemExpression> getVtlRenameClauseItemExpressionList() {
        if (vtlRenameClauseItemExpressionList == null)
            this.vtlRenameClauseItemExpressionList = new ArrayList<>();
        return vtlRenameClauseItemExpressionList;
    }

    public void setVtlRenameClauseItemExpressionList(List<VtlRenameClauseItemExpression> vtlRenameClauseItemExpressionList) {
        this.vtlRenameClauseItemExpressionList = vtlRenameClauseItemExpressionList;
    }


    @Override
    public void setDataset(VtlExpression vtlExpression) {
        this.vtlDatasetClause = vtlExpression;
    }

    public VtlExpression getVtlDatasetClause() {
        return vtlDatasetClause;
    }
}
