package com.capgemini.istat.vtlcompiler.vtlcommon.model.dataset;

/**
 * La classe fornisce tutti i value Domain di default legati al tipo del componente
 */
public class ValueDomainConstants {

    private ValueDomainConstants() {
        //non serve
    }

    public static final String NUM_VD = "num_vd";
    public static final String TIME_VD = "time_vd";
    public static final String STRING_VD = "string_vd";
    public static final String INT_VD = "int_vd";
    public static final String BOOL_VD = "bool_vd";
    public static final String TIME_PERIOD_VD = "time_period_vd";
    public static final String DATE_VD = "date_vd";
    public static final String DURATION_VD = "duration_vd";


    public static boolean isDefaultValueDomain(String valueDomain) {
        return valueDomain.equalsIgnoreCase(ValueDomainConstants.NUM_VD)
                || valueDomain.equalsIgnoreCase(ValueDomainConstants.TIME_VD)
                || valueDomain.equalsIgnoreCase(ValueDomainConstants.STRING_VD)
                || valueDomain.equalsIgnoreCase(ValueDomainConstants.INT_VD)
                || valueDomain.equalsIgnoreCase(ValueDomainConstants.BOOL_VD)
                || valueDomain.equalsIgnoreCase(ValueDomainConstants.TIME_PERIOD_VD)
                || valueDomain.equalsIgnoreCase(ValueDomainConstants.DATE_VD)
                || valueDomain.equalsIgnoreCase(ValueDomainConstants.DURATION_VD);
    }
}
