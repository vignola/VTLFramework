package com.capgemini.istat.vtlcompiler.vtlapi;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hsqldb.Server;
import org.hsqldb.persist.HsqlProperties;
import org.hsqldb.server.ServerAcl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import java.io.IOException;
import java.sql.SQLException;

@Configuration
public class DBHsqlDBServerConfiguration {
    public static final Logger logger = LogManager.getLogger(DBHsqlDBServerConfiguration.class);


    private Environment env;

    @Autowired
    public void setEnv(Environment env) {
        this.env = env;
    }

    @Bean(destroyMethod = "stop")
    public Server databaseServer() throws SQLException, IOException, ServerAcl.AclFormatException {
            HsqlProperties configProps = new HsqlProperties();                       
            configProps.setProperty("server.port", env.getProperty("hsql.server.port"));
            configProps.setProperty("server.database.0", env.getProperty("hsql.database.path"));
            configProps.setProperty("server.dbname.0", env.getProperty("hsql.server.dbname"));
            
            Server server = new org.hsqldb.Server();
            server.setLogWriter(null);
            server.setErrWriter(null);
            server.setRestartOnShutdown(false);
            server.setNoSystemExit(true);
            server.setProperties(configProps);
            server.start();
            
            logger.info("HSQLDB Server start using port: " + env.getProperty("hsql.server.port"));
            
            return server;
    }
}
