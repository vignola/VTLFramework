package com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.clause;

import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.VtlExpression;

import java.io.Serializable;

public abstract class VtlDatasetClause extends VtlExpression implements Serializable {

    public abstract void setDataset(VtlExpression vtlExpression);
}
