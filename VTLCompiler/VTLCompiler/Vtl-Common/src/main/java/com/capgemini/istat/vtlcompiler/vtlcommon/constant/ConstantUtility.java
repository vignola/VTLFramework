package com.capgemini.istat.vtlcompiler.vtlcommon.constant;

public class ConstantUtility {
    private ConstantUtility() { }  // Prevents instantiation

    public static final String DB_SCHEMA  = "vtl_db";
    public static final String DEFAULT_ALIAS_TABLE  = "tx";
    public static final String DUMMY_TABLE  = "DUAL";
    public static final String ORACLE_PACKAGE  = "VTL_PKG_ORACLE.sql";
    public static final String SQLSERVER_PACKAGE  = "VTL_PKG_SQLSERVER.sql";
    public static final String MYSQL_PACKAGE  = "VTL_PKG_MYSQL.sql";
    public static final String POSTGRESQL_PACKAGE  = "VTL_PKG_POSTGRESQL.sql";
    public static final String SEMICOLON = ";";
    public static final String UNDERSCORE = "_";
    public static final String INDEX_SUFFIX = "_IDX";
    public static final String COLUMN_PRIORITY = "PRIORITY";
}
