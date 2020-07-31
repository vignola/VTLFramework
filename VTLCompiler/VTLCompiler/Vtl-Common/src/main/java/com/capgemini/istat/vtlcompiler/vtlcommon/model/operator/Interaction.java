package com.capgemini.istat.vtlcompiler.vtlcommon.model.operator;

/**
 * tutte le interazioni possibili fra dataset, component e scalari
 */
public enum Interaction {

    SCALAR_TO_SCALAR,
    SCALAR_TO_COMPONENT,
    SCALAR_TO_DATASET,
    COMPONENT_TO_COMPONENT,
    DATASET_TO_DATASET,
    UNARY_OPERATOR,
    SCALAR,
    DATASET,
    PARAMETERS,
    PARAMETER_1,
    PARAMETER_2,
    COMPONENT,


}
