package com.capgemini.istat.vtlcompiler.vtlsdmxbuilder.service.builder;

import com.capgemini.istat.vtlcompiler.vtlcommon.model.dataset.VtlComponent;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.dataset.VtlComponentRole;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.dataset.VtlDataset;
import com.capgemini.istat.vtlcompiler.vtlsdmxbuilder.service.utility.SdmxUtiltyService;
import org.sdmxsource.sdmx.api.model.beans.conceptscheme.ConceptSchemeBean;
import org.sdmxsource.sdmx.api.model.mutable.conceptscheme.ConceptSchemeMutableBean;
import org.sdmxsource.sdmx.sdmxbeans.model.mutable.conceptscheme.ConceptSchemeMutableBeanImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

/**
 * La classe si occupa di popolare la sezione del SDMX che riguarda il concepScheme. La maggior parte dei valori
 * sono prelevati dall'application properties.
 * Il servizio popola il concept sheme delle misure e degli identificativi
 *
 * @see SdmxUtiltyService
 */
@Service
public class ConceptSchemeBuilderService {
    private Environment environment;
    private SdmxUtiltyService sdmxUtiltyService;

    @Autowired
    public void setEnv(Environment env) {
        this.environment = env;
    }

    @Autowired
    public void setSdmxUtiltyService(SdmxUtiltyService sdmxUtiltyService) {
        this.sdmxUtiltyService = sdmxUtiltyService;
    }

    /**
     * Il metodo si occupa di generare un conceptScheme per tutte le misure del dataset. Se non è presente un componente
     * del dataset chiamato obsValue viene creato un item rappresentativo del componente. Questa logica è stata messa secondo
     * specifiche per consentire l'utilizzo di misure multiple
     *
     * @param vtlDataset il dataset che si vuole condificare in sdmx
     * @return un concept scheme rappresentate le misure del dataset
     */
    public ConceptSchemeBean conceptSchemeMeasures(VtlDataset vtlDataset) {
        ConceptSchemeMutableBean conceptSchemeMutable = new ConceptSchemeMutableBeanImpl();
        conceptSchemeMutable.setId(environment.getProperty("sdmx.concept.scheme.measures.id"));
        conceptSchemeMutable.setAgencyId(environment.getProperty("sdmx.agency.id"));
        conceptSchemeMutable.setVersion(environment.getProperty("sdmx.version"));
        conceptSchemeMutable.addName(environment.getProperty("sdmx.locale"), environment.getProperty("sdmx.concept.scheme.measures.name"));
        for (VtlComponent vtlComponent : vtlDataset.getMeasures()) {
            if (!vtlComponent.getName().equalsIgnoreCase("obs_value")) {
                conceptSchemeMutable.createItem(
                        vtlComponent.getName(),
                        environment.getProperty("sdmx.concept.scheme.measure.item.name") + " " + vtlComponent.getName())
                        .setCoreRepresentation(sdmxUtiltyService.getRepresentationMeasure(vtlComponent));
            }
        }
        return conceptSchemeMutable.getImmutableInstance();
    }

    /**
     * Il metodo si occupa di generare un conceptScheme per tutti gli identificativi del dataset.
     * Il metodo prende tutti gli identificativi e costruisce un item a eccezione del campo obs_value e time_period.
     * Questi campi sono speciali e vengono trattati in maniera diversa dal servizio che compone l'sdmx
     *
     * @param vtlDataset il dataset che si vuole condificare in sdmx
     * @return un concept scheme rappresentate le misure del dataset
     */
    public ConceptSchemeBean conceptSchemeIdentifiers(VtlDataset vtlDataset) {
        ConceptSchemeMutableBean conceptSchemeMutable = new ConceptSchemeMutableBeanImpl();
        conceptSchemeMutable.setId(environment.getProperty("sdmx.concept.scheme.identifiers.id"));
        conceptSchemeMutable.setAgencyId(environment.getProperty("sdmx.concept.scheme.identifiers.agency.id"));
        conceptSchemeMutable.setVersion(environment.getProperty("sdmx.concept.scheme.identifiers.version"));
        conceptSchemeMutable.addName(environment.getProperty("sdmx.locale"), environment.getProperty("sdmx.concept.scheme.identifiers.name"));
        for (VtlComponent vtlComponent : vtlDataset.getVtlComponents()) {
            if (vtlComponent.getVtlComponentRole() != VtlComponentRole.MEASURE && (!vtlComponent.getName().equalsIgnoreCase("obs_value") &&
                    !vtlComponent.getName().equalsIgnoreCase("time_period"))) {
                conceptSchemeMutable.createItem(vtlComponent.getName(), vtlComponent.getVtlComponentRole() + " " + vtlComponent.getName());

            }
        }
        //TODO logica da chiarire
        conceptSchemeMutable.createItem("MS", "Measure Dimension");
        conceptSchemeMutable.createItem("TIME_PERIOD", "TIME_PERIOD");
        conceptSchemeMutable.createItem("OBS_VALUE", "Observation Value");
        return conceptSchemeMutable.getImmutableInstance();
    }

}
