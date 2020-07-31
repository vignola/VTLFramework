package com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.define.ruleset;


import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.constant.VtlConstant;

import java.io.Serializable;

public class VtlErrorRuleset implements Serializable {

    private VtlConstant errorCode;
    private VtlConstant errorLevel;

    public VtlConstant getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(VtlConstant errorCode) {
        this.errorCode = errorCode;
    }

    public VtlConstant getErrorLevel() {
        return errorLevel;
    }

    public void setErrorLevel(VtlConstant errorLevel) {
        this.errorLevel = errorLevel;
    }
}
