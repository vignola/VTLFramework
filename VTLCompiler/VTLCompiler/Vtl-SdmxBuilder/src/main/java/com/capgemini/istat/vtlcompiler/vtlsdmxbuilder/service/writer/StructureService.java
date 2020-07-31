package com.capgemini.istat.vtlcompiler.vtlsdmxbuilder.service.writer;

import com.capgemini.istat.vtlcompiler.vtlcommon.model.dataset.ValueDomainConstants;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.dataset.VtlComponent;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.dataset.VtlDataset;
import com.capgemini.istat.vtlcompiler.vtlsdmxbuilder.service.builder.CodeListBuilderService;
import com.capgemini.istat.vtlcompiler.vtlsdmxbuilder.service.builder.ConceptSchemeBuilderService;
import com.capgemini.istat.vtlcompiler.vtlsdmxbuilder.service.builder.DataStructureBuilderService;
import org.sdmxsource.sdmx.api.constants.STRUCTURE_OUTPUT_FORMAT;
import org.sdmxsource.sdmx.api.model.beans.SdmxBeans;
import org.sdmxsource.sdmx.api.model.beans.datastructure.DataStructureBean;
import org.sdmxsource.sdmx.sdmxbeans.model.SdmxStructureFormat;
import org.sdmxsource.sdmx.sdmxbeans.model.header.HeaderBeanImpl;
import org.sdmxsource.sdmx.structureparser.manager.impl.StructureWriterManagerImpl;
import org.sdmxsource.sdmx.util.beans.container.SdmxBeansImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * La classe StructureService è la classe di servizio che si occupa di attivare i servizi necessari alla traduzione
 * verso SDMX XML.
 *
 * @see StructureWriterManagerImpl
 * @see CodeListBuilderService
 * @see DataStructureBuilderService
 * @see ConceptSchemeBuilderService
 */
@Service
public class StructureService {

    private StructureWriterManagerImpl structureWriterManager;
    private CodeListBuilderService codeListBuilderService;
    private DataStructureBuilderService dataStructureBuilderService;
    private ConceptSchemeBuilderService conceptSchemeBuilderService;
    private Environment environment;

    @Autowired
    public void setEnv(Environment env) {
        this.environment = env;
    }

    @Autowired
    public void setStructureWriterManager(StructureWriterManagerImpl structureWriterManager) {
        this.structureWriterManager = structureWriterManager;
    }

    @Autowired
    public void setCodeListBuilderService(CodeListBuilderService codeListBuilderService) {
        this.codeListBuilderService = codeListBuilderService;
    }


    @Autowired
    public void setDataStructureBuilderService(DataStructureBuilderService dataStructureBuilderService) {
        this.dataStructureBuilderService = dataStructureBuilderService;
    }

    @Autowired
    public void setConceptSchemeBuilderService(ConceptSchemeBuilderService conceptSchemeBuilderService) {
        this.conceptSchemeBuilderService = conceptSchemeBuilderService;
    }

    /**
     * il metodo si occupa di prendere in ingresso un dataset(il dataset risultante) e costruisce la struttura
     * SDMX di riferimento per quella struttura. Il metoodo offre in uscita il file di struttura come Stream
     * per non creare eventuali problemi di performance
     *
     * @param vtlDataset il dataset di cui si vuole la struttura SDMX
     * @return il ByteArrayOutputStream contenente la struttura SDMX-XML
     */
    public ByteArrayOutputStream writeStructureToStream(VtlDataset vtlDataset) {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        SdmxStructureFormat sdmxStructureFormat = new SdmxStructureFormat(STRUCTURE_OUTPUT_FORMAT.SDMX_V21_STRUCTURE_DOCUMENT);
        SdmxBeans sdmxBeans = new SdmxBeansImpl();
        HeaderBeanImpl headerBean = new HeaderBeanImpl(environment.getProperty("sdmx.id"), environment.getProperty("sdmx.sender"));
        sdmxBeans.setHeader(headerBean);
        List<String> valueDomains = new ArrayList<>();
        for (VtlComponent vtlComponent : vtlDataset.getVtlComponents()) {
            if (!ValueDomainConstants.isDefaultValueDomain(vtlComponent.getDomainValue()) && !valueDomains.contains(vtlComponent.getDomainValue().toUpperCase())) {
                valueDomains.add(vtlComponent.getDomainValue().toUpperCase());
                sdmxBeans.addCodelist(codeListBuilderService.buildCodeList(vtlComponent));
            }
        }


        sdmxBeans.addIdentifiable(conceptSchemeBuilderService.conceptSchemeIdentifiers(vtlDataset));
        sdmxBeans.addIdentifiable(conceptSchemeBuilderService.conceptSchemeMeasures(vtlDataset));

        DataStructureBean dataStructureBean = dataStructureBuilderService.buildDataStructure(vtlDataset);
        sdmxBeans.addIdentifiable(dataStructureBean);

        structureWriterManager.writeStructures(sdmxBeans, sdmxStructureFormat, out);
        return out;
    }

    /**
     * Trasforma lo stream in una stringa. Utilizzo sconsigliato, utilizzato solo per test
     *
     * @param byteArrayOutputStream uno stream di dati. Solitamente la struttura SDMX
     * @return una stringa che descrive la struttura SDMX
     */
    public String getStringStructure(ByteArrayOutputStream byteArrayOutputStream) {
        return new String(byteArrayOutputStream.toByteArray());
    }
}
