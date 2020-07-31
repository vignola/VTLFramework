package com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.join;

import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.VtlExpression;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.clause.VtlDatasetClause;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.clause.filter.VtlFilterClauseExpression;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.clause.rename.VtlRenameClauseExpression;

import java.io.Serializable;

public class VtlJoinBody extends VtlExpression implements Serializable {
    private VtlFilterClauseExpression vtlFilterClauseExpression;
    private VtlDatasetClause vtlJoinBodyClause;
    private VtlDatasetClause keepDropClause;
    private VtlRenameClauseExpression vtlRenameClauseExpression;

    public VtlRenameClauseExpression getVtlRenameClauseExpression() {
        return vtlRenameClauseExpression;
    }

    public void setVtlRenameClauseExpression(VtlRenameClauseExpression vtlRenameClauseExpression) {
        this.vtlRenameClauseExpression = vtlRenameClauseExpression;
    }

    public VtlFilterClauseExpression getVtlFilterClauseExpression() {
        return vtlFilterClauseExpression;
    }

    public void setVtlFilterClauseExpression(VtlFilterClauseExpression vtlFilterClauseExpression) {
        this.vtlFilterClauseExpression = vtlFilterClauseExpression;
    }

    public VtlDatasetClause getVtlJoinBodyClause() {
        return vtlJoinBodyClause;
    }

    public void setVtlJoinBodyClause(VtlDatasetClause vtlJoinBodyClause) {
        this.vtlJoinBodyClause = vtlJoinBodyClause;
    }

    public VtlDatasetClause getKeepDropClause() {
        return keepDropClause;
    }

    public void setKeepDropClause(VtlDatasetClause keepDropClause) {
        this.keepDropClause = keepDropClause;
    }


}
