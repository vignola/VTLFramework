package com.capgemini.istat.vtlcompiler.vtlsqlbuilder.service.utility;

import com.capgemini.istat.vtlcompiler.vtlcommon.constant.ConstantUtility;
import com.healthmarketscience.sqlbuilder.dbspec.basic.DbSchema;
import com.healthmarketscience.sqlbuilder.dbspec.basic.DbSpec;
import org.springframework.stereotype.Service;

/**
 * Questa è una classe di utilità necessaria al motore di traduzione per mantenere una dinamica corretta di alias delle
 * tabelle
 */
@Service
public class DbTableUtilityService {
    private DbSpec dbSpec = new DbSpec();
    private DbSchema dbSchema = new DbSchema(dbSpec, ConstantUtility.DB_SCHEMA);

    /**
     * Fornisce uno schema db (non utilizzato)
     *
     * @return
     */
    public DbSchema getDbSchema() {
        return dbSchema;
    }

    /**
     * Resetta uno schema e fa ripartire il conteggio degli alias
     *
     * @param aliasPrefix
     */
    public void resetSchema(String aliasPrefix) {
        this.dbSpec = new DbSpec(aliasPrefix);
        this.dbSchema = new DbSchema(dbSpec, ConstantUtility.DB_SCHEMA);
    }

    /**
     * Fornisce il database necessario al calcolo degli alias
     *
     * @return
     */
    public DbSpec getDbSpec() {
        return dbSpec;
    }
}
