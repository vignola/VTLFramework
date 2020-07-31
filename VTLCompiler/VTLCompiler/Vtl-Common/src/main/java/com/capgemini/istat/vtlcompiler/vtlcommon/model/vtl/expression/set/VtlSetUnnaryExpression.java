package com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.set;

import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.VtlExpression;

import java.io.Serializable;
import java.util.LinkedList;

public class VtlSetUnnaryExpression extends VtlExpression implements Serializable {

    LinkedList<VtlExpression> vtlExpressions = new LinkedList<>();

    public LinkedList<VtlExpression> getVtlExpressions() {
        return vtlExpressions;
    }

    public void setVtlExpressions(LinkedList<VtlExpression> vtlExpressions) {
        this.vtlExpressions = vtlExpressions;
    }
}
