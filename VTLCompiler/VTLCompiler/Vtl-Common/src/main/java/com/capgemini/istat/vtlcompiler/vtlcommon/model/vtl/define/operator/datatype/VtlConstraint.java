package com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.define.operator.datatype;

import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.constant.VtlConstant;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.VtlExpression;

import java.io.Serializable;
import java.util.LinkedList;

public class VtlConstraint implements VtlDataType, Serializable {

    private VtlExpression vtlCondition;
    private LinkedList<VtlConstant> vtlConstantList = new LinkedList<>();

    public VtlExpression getVtlCondition() {
        return vtlCondition;
    }

    public void setVtlCondition(VtlExpression vtlCondition) {
        this.vtlCondition = vtlCondition;
    }

    public LinkedList<VtlConstant> getVtlConstantList() {
        return vtlConstantList;
    }

    public void setVtlConstantList(LinkedList<VtlConstant> vtlConstantList) {
        this.vtlConstantList = vtlConstantList;
    }
}
