package com.capgemini.istat.vtlcompiler.vtlapi;

import org.sdmxsource.sdmx.api.factory.ReadableDataLocationFactory;
import org.sdmxsource.util.factory.SdmxSourceReadableDataLocationFactory;
import org.sdmxsource.util.factory.SdmxSourceWriteableDataLocationFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration

public class BeanConfiguration {

    @Bean
    public ReadableDataLocationFactory getReadableDataLocationFactory() {
        return new SdmxSourceReadableDataLocationFactory();
    }

    @Bean
    public SdmxSourceWriteableDataLocationFactory getWriteableDataLocation() {
        return new SdmxSourceWriteableDataLocationFactory();
    }

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {

                registry.addMapping("/**").allowedMethods("*").allowedOrigins("*");

            }
        };
    }

}
