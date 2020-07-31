package com.capgemini.istat.vtlcompiler.vtlapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.aspectj.EnableSpringConfigured;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan({"com.capgemini.istat.vtlcompiler","org.sdmxsource"})
@EntityScan("com.capgemini.istat.vtlcompiler.vtlcommon")
@EnableJpaRepositories(basePackages = "com.capgemini.istat.vtlcompiler.vtlcommon.repository")
@EnableSpringConfigured
public class VtlApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(VtlApiApplication.class, args);
    }

}
