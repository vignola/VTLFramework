package com.capgemini.istat.vtlcompiler.vtlsdmxbuilder.service.builder;

import org.sdmxsource.sdmx.api.constants.TERTIARY_BOOL;
import org.sdmxsource.sdmx.api.model.beans.base.AgencySchemeBean;
import org.sdmxsource.sdmx.api.model.mutable.base.AgencySchemeMutableBean;
import org.sdmxsource.sdmx.sdmxbeans.model.beans.base.AgencySchemeBeanImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

/**
 * Questa è una classe utilizzata per costruire una strutturata sdmx di test non ha un utilità se non ai fini di guidare
 * future espansioni
 */
@Service
public class AgencySchemeBuilderService {
    private Environment environment;

    @Autowired
    public void setEnv(Environment env) {
        this.environment = env;
    }

    /**
     * Il metodo costruisce un agencySchema di test
     *
     * @return il metodo restituisce un agencySchema di test
     */
    public AgencySchemeBean buildAgencyScheme() {
        AgencySchemeMutableBean mutableBean = AgencySchemeBeanImpl.createDefaultScheme().getMutableInstance();
        mutableBean.setAgencyId(environment.getProperty("sdmx.agency.id"));
        mutableBean.setVersion(environment.getProperty("sdmx.version"));
        mutableBean.setExternalReference(TERTIARY_BOOL.FALSE);
        mutableBean.createItem(environment.getProperty("sdmx.agency.id"), environment.getProperty("sdmx.agency.name"));
        return mutableBean.getImmutableInstance();
    }

}
