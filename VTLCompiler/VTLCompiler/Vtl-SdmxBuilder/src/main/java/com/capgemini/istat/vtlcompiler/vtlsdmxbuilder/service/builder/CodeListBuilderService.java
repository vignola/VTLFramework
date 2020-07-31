package com.capgemini.istat.vtlcompiler.vtlsdmxbuilder.service.builder;

import com.capgemini.istat.vtlcompiler.vtlcommon.model.dataset.VtlComponent;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.valuedomain.ValueDomain;
import com.capgemini.istat.vtlcompiler.vtlcommon.service.DatasetIntegrationService;
import org.sdmxsource.sdmx.api.constants.TERTIARY_BOOL;
import org.sdmxsource.sdmx.api.model.beans.codelist.CodelistBean;
import org.sdmxsource.sdmx.api.model.mutable.codelist.CodelistMutableBean;
import org.sdmxsource.sdmx.sdmxbeans.model.mutable.codelist.CodelistMutableBeanImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * La classe si occupa di modellare, ai fini della costruzione dello schema SDMX, le code list.
 * Le codeList vengono popolate tramite i valori offerti del valueDomain.
 * I dati vengono offerti dal servizio DatasetIntegrationService
 *
 * @see DatasetIntegrationService
 */
@Service
public class CodeListBuilderService {
    private Environment environment;
    private DatasetIntegrationService datasetIntegrationService;

    @Autowired
    public void setEnv(Environment env) {
        this.environment = env;
    }

    @Autowired
    public void setDatasetIntegrationService(DatasetIntegrationService datasetIntegrationService) {
        this.datasetIntegrationService = datasetIntegrationService;
    }

    /**
     * Il metodo prende in ingresso un VTLComponent, estrae i codici del valuedomain corrispondente e popola una codelist
     * da aggregare alla struttura dati SDMX. Solitamente i componenti che diventano codeList sono gli identifier
     *
     * @param vtlComponent il componente che si vuole codificare in codeList
     * @return una codeListBean che rappresenta il componente
     */
    public CodelistBean buildCodeList(VtlComponent vtlComponent) {
        CodelistMutableBean codelistMutable = new CodelistMutableBeanImpl();
        codelistMutable.setAgencyId(environment.getProperty("sdmx.agency.id"));
        codelistMutable.setVersion(environment.getProperty("sdmx.version"));
        codelistMutable.setExternalReference(TERTIARY_BOOL.FALSE);
        List<ValueDomain> valueDomainList = datasetIntegrationService.getAllByValueDomainNameIgnoreCase(vtlComponent.getDomainValue());
        if (!valueDomainList.isEmpty()) {
            codelistMutable.setId(vtlComponent.getDomainValue());
            codelistMutable.addName(environment.getProperty("sdmx.locale"), environment.getProperty("sdmx.code.list.name") + " " + vtlComponent.getDomainValue());
            for (ValueDomain valueDomain : valueDomainList) {
                codelistMutable.createItem(valueDomain.getCode(), valueDomain.getDescription());
            }
            return codelistMutable.getImmutableInstance();
        }
        return null;
    }

    /**
     * metodo di esempio. Non ha utilizzo in pratica
     *
     * @return una codeListBean di esempio
     */
    public CodelistBean buildCountryCodelist() {
        CodelistMutableBean codelistMutable = new CodelistMutableBeanImpl();
        codelistMutable.setAgencyId("VTL");
        codelistMutable.setId("CL_COUNTRY");
        codelistMutable.setVersion("1.0");
        codelistMutable.addName("en", "Country");
        codelistMutable.addName("it", "Paese");
        codelistMutable.setExternalReference(TERTIARY_BOOL.FALSE);
        codelistMutable.createItem("UK", "United Kingdom");
        codelistMutable.createItem("FR", "France");
        codelistMutable.createItem("DE", "Germany");

        return codelistMutable.getImmutableInstance();
    }


    /**
     * metodo di esempio. Non ha utilizzo in pratica
     *
     * @return una codeListBean di esempio
     */
    public CodelistBean buildIndicatorCodelistVDID1() {
        CodelistMutableBean codelistMutable = new CodelistMutableBeanImpl();
        codelistMutable.setId("VD_ID1");
        codelistMutable.setAgencyId("VTL");
        codelistMutable.setVersion("1.0");
        codelistMutable.setExternalReference(TERTIARY_BOOL.FALSE);
        codelistMutable.addName("en", "Code list VD_ID1");
        codelistMutable.createItem("A1", "Code A1");
        codelistMutable.createItem("A2", "Code A2");
        codelistMutable.createItem("A3", "Code A3");
        return codelistMutable.getImmutableInstance();
    }

    /**
     * metodo di esempio. Non ha utilizzo in pratica
     *
     * @return una codeListBean di esempio
     */
    public CodelistBean buildIndicatorCodelistVDID2() {
        CodelistMutableBean codelistMutable = new CodelistMutableBeanImpl();
        codelistMutable.setId("VD_ID2");
        codelistMutable.setAgencyId("VTL");
        codelistMutable.setVersion("1.0");
        codelistMutable.setExternalReference(TERTIARY_BOOL.FALSE);
        codelistMutable.addName("en", "Code list VD_ID2");
        codelistMutable.createItem("B1", "Code B1");
        codelistMutable.createItem("B2", "Code B2");
        return codelistMutable.getImmutableInstance();
    }

    /**
     * metodo di esempio. Non ha utilizzo in pratica
     *
     * @return una codeListBean di esempio
     */
    public CodelistBean buildIndicatorCodelistVDAT2() {
        CodelistMutableBean codelistMutable = new CodelistMutableBeanImpl();
        codelistMutable.setId("VD_AT2");
        codelistMutable.setAgencyId("VTL");
        codelistMutable.setVersion("1.0");
        codelistMutable.setExternalReference(TERTIARY_BOOL.FALSE);
        codelistMutable.addName("en", "Code list VD_AT2");
        codelistMutable.createItem("C1", "Code C1");
        codelistMutable.createItem("C2", "Code C2");
        return codelistMutable.getImmutableInstance();
    }
}
