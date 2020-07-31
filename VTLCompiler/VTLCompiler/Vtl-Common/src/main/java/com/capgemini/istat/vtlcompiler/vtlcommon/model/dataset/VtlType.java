package com.capgemini.istat.vtlcompiler.vtlcommon.model.dataset;


import java.io.Serializable;

/**
 * La clase contiene tutti i tipi del motore VTL. Oltre a fornire i tipi, vengono forniti tutti i nomi di componente
 * di default legati al tipo e il nome dei valueDomain di default legati al tipo
 *
 * @see ValueDomainConstants
 */
public enum VtlType implements Serializable {
    STRING("string", "string_var", ValueDomainConstants.STRING_VD, ValueDomainConstants.STRING_VD),
    NUMBER("number", "num_var", ValueDomainConstants.NUM_VD, ValueDomainConstants.NUM_VD),
    INTEGER("integer", "int_var", ValueDomainConstants.INT_VD, ValueDomainConstants.NUM_VD),
    BOOLEAN("bolean", "bool_var", ValueDomainConstants.BOOL_VD, ValueDomainConstants.BOOL_VD),
    NULL_VALUE("null", "", "", ""),
    TIME("times", "time_var", ValueDomainConstants.TIME_VD, ValueDomainConstants.TIME_VD),
    TIME_PERIOD("time_period", "time_period_var", ValueDomainConstants.TIME_PERIOD_VD, ValueDomainConstants.TIME_VD),
    DATE("date", "date_var", ValueDomainConstants.DATE_VD, ValueDomainConstants.TIME_VD),
    DURATION("duration", "duration_var", ValueDomainConstants.DURATION_VD, ValueDomainConstants.DURATION_VD),
    SCALAR("scalar", "", "", ""),
    NONE("var", "var", "vd", "vd");

    private String valueType;
    private String defaultName;
    private String domainValue;
    private String domainValueParent;

    VtlType(String valueType, String defaultName, String domainValue, String domainValueParent) {
        this.valueType = valueType;
        this.defaultName = defaultName;
        this.domainValue = domainValue;
        this.domainValueParent = domainValueParent;
    }

    public String getValueType() {
        return valueType;
    }

    public String getDefaultName() {
        return defaultName;
    }

    public String getDomainValue() {
        return domainValue;
    }

    public String getDomainValueParent() {
        return domainValueParent;
    }

}
