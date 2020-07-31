package com.capgemini.istat.vtlcompiler.vtlcommon.model.dataset;

import java.io.Serializable;

/**
 * la classe determina i possibili ruoli dei componenti
 */
public enum VtlComponentRole implements Serializable {
    IDENTIFIER,
    MEASURE,
    ATTRIBUTE,
    VIRAL
}