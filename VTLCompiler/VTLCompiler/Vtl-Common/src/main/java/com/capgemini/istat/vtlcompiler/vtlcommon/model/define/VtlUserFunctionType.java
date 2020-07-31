package com.capgemini.istat.vtlcompiler.vtlcommon.model.define;

import java.io.Serializable;

/**
 * I tipi possibili di userDefineFunction
 */
public enum VtlUserFunctionType implements Serializable {

    OPERATOR_FUNCTION,
    DATAPOINT_FUNCTION,
    HIERARCHICAL_FUNCTION

}
