package com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.clause.analytic;

import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.constant.VtlConstantExpression;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.VtlExpression;

import java.io.Serializable;

public class VtlAnalyticFunctionExpression extends VtlExpression implements Serializable {
    private VtlExpression vtlExpression;
    private VtlOrderBy vltOrderBy;
    private VtlPartitionBy vtlPartitionBy;
    private VtlWindowing vtlWindowing;
    private Integer offset;
    private VtlConstantExpression defaultValue;

    public Integer getOffset() {
        return offset;
    }

    public void setOffset(Integer offset) {
        this.offset = offset;
    }

    public VtlConstantExpression getDefaultValue() {
        return defaultValue;
    }

    public void setDefaultValue(VtlConstantExpression defaultValue) {
        this.defaultValue = defaultValue;
    }

    public VtlExpression getVtlExpression() {
        return vtlExpression;
    }

    public void setVtlExpression(VtlExpression vtlExpression) {
        this.vtlExpression = vtlExpression;
    }

    public VtlOrderBy getVltOrderBy() {
        return vltOrderBy;
    }

    public void setVltOrderBy(VtlOrderBy vltOrderBy) {
        this.vltOrderBy = vltOrderBy;
    }

    public VtlPartitionBy getVtlPartitionBy() {
        return vtlPartitionBy;
    }

    public void setVtlPartitionBy(VtlPartitionBy vtlPartitionBy) {
        this.vtlPartitionBy = vtlPartitionBy;
    }

    public VtlWindowing getVtlWindowing() {
        return vtlWindowing;
    }

    public void setVtlWindowing(VtlWindowing vtlWindowing) {
        this.vtlWindowing = vtlWindowing;
    }


}
