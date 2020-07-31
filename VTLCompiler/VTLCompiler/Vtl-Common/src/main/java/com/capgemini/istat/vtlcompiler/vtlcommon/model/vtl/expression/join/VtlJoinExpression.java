package com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.join;

import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.VtlExpression;

import java.io.Serializable;

public class VtlJoinExpression extends VtlExpression implements Serializable {
    private VtlJoinSelectClause vtlJoinSelectClause;
    private VtlJoinBody vtlJoinBody;

    public VtlJoinSelectClause getVtlJoinSelectClause() {
        return vtlJoinSelectClause;
    }

    public void setVtlJoinSelectClause(VtlJoinSelectClause vtlJoinSelectClause) {
        this.vtlJoinSelectClause = vtlJoinSelectClause;
    }

    public VtlJoinBody getVtlJoinBody() {
        return vtlJoinBody;
    }

    public void setVtlJoinBody(VtlJoinBody vtlJoinBody) {
        this.vtlJoinBody = vtlJoinBody;
    }


}
