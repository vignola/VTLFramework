package com.capgemini.istat.vtlcompiler.vtlapi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@Configuration
public class DBStructureConfiguration {


    private Environment env;

    @Autowired
    public void setEnv(Environment env) {
        this.env = env;
    }

    @Bean(name = "dbDatasource")
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        setClassName(dataSource, env.getProperty("db.type"));
        dataSource.setUrl(env.getProperty("db.datasource.url"));
        dataSource.setUsername(env.getProperty("db.datasource.username"));
        dataSource.setPassword(env.getProperty("db.datasource.password"));
        return dataSource;

    }

    private void setClassName(DriverManagerDataSource dataSource, String type) {
        if (type == null || type.equalsIgnoreCase("oracleSql"))
            dataSource.setDriverClassName("oracle.jdbc.driver.OracleDriver");
        else if (type.equalsIgnoreCase("mySql"))
            dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        else if (type.equalsIgnoreCase("postgreSql"))
            dataSource.setDriverClassName("org.postgresql.Driver");
        else if (type.equalsIgnoreCase("sqlServer"))
            dataSource.setDriverClassName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
    }


}
