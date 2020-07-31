package com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.validation;

import java.io.Serializable;

public enum ValidationMode implements Serializable {

    NON_NULL,
    NON_ZERO,
    PARTIAL_NULL,
    PARTIAL_ZERO,
    ALWAYS_NULL,
    ALWAYS_ZERO
}
