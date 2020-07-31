package com.capgemini.istat.vtlcompiler.vtlsdmxbuilder.service.builder;

import com.capgemini.istat.vtlcompiler.vtlcommon.model.dataset.ValueDomainConstants;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.dataset.VtlComponent;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.dataset.VtlDataset;
import com.capgemini.istat.vtlcompiler.vtlcommon.service.ComponentUtilityService;
import com.capgemini.istat.vtlcompiler.vtlsdmxbuilder.service.utility.SdmxUtiltyService;
import org.sdmxsource.sdmx.api.constants.ATTRIBUTE_ATTACHMENT_LEVEL;
import org.sdmxsource.sdmx.api.constants.SDMX_STRUCTURE_TYPE;
import org.sdmxsource.sdmx.api.constants.TEXT_TYPE;
import org.sdmxsource.sdmx.api.model.beans.datastructure.DataStructureBean;
import org.sdmxsource.sdmx.api.model.beans.reference.StructureReferenceBean;
import org.sdmxsource.sdmx.api.model.mutable.base.RepresentationMutableBean;
import org.sdmxsource.sdmx.api.model.mutable.base.TextTypeWrapperMutableBean;
import org.sdmxsource.sdmx.api.model.mutable.datastructure.DataStructureMutableBean;
import org.sdmxsource.sdmx.api.model.mutable.datastructure.DimensionMutableBean;
import org.sdmxsource.sdmx.sdmxbeans.model.mutable.base.AnnotationMutableBeanImpl;
import org.sdmxsource.sdmx.sdmxbeans.model.mutable.base.RepresentationMutableBeanImpl;
import org.sdmxsource.sdmx.sdmxbeans.model.mutable.base.TextFormatMutableBeanImpl;
import org.sdmxsource.sdmx.sdmxbeans.model.mutable.base.TextTypeWrapperMutableBeanImpl;
import org.sdmxsource.sdmx.sdmxbeans.model.mutable.datastructure.AttributeMutableBeanImpl;
import org.sdmxsource.sdmx.sdmxbeans.model.mutable.datastructure.DataStructureMutableBeanImpl;
import org.sdmxsource.sdmx.sdmxbeans.model.mutable.datastructure.DimensionMutableBeanImpl;
import org.sdmxsource.sdmx.util.beans.reference.IdentifiableRefBeanImpl;
import org.sdmxsource.sdmx.util.beans.reference.StructureReferenceBeanImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * la classe si occupa di creare la dataStructure che rappresenta il dataset nel formato SDMX.
 * I metodi offerti da questa classe rappresentano l'ossatura della costruzione del SDMX di esportazione
 *
 * @see ComponentUtilityService
 * @see SdmxUtiltyService
 */
@Service
public class DataStructureBuilderService {
    public static final String SDMX_CONCEPT_SCHEME_IDENTIFIERS_ID = "sdmx.concept.scheme.identifiers.id";
    public static final String SDMX_VERSION = "sdmx.version";
    public static final String SDMX_AGENCY_ID = "sdmx.agency.id";
    private Environment environment;
    private ComponentUtilityService componentUtilityService;
    private SdmxUtiltyService sdmxUtiltyService;

    @Autowired
    public void setEnv(Environment env) {
        this.environment = env;
    }

    @Autowired
    public void setComponentUtilityService(ComponentUtilityService componentUtilityService) {
        this.componentUtilityService = componentUtilityService;
    }

    @Autowired
    public void setSdmxUtiltyService(SdmxUtiltyService sdmxUtiltyService) {
        this.sdmxUtiltyService = sdmxUtiltyService;
    }

    /**
     * Il metodo si occupa di costruire la dataStructure per l'esportazione in SDMX.
     * Tutti i componenti del dataset vengono analizzati e, in base al ruolo, vengono invocati i metodi specifici che modellano
     * il componente in sdmx.
     * Campi come Obs_value e time period vengono valutati a parte. Se non sono presenti vengono create delle rappresentazioni fittizie
     * con informazioni che fanno risalire al fatto che il componente non era presente sul dataset mappato.
     *
     * @param vtlDataset
     * @return
     */
    public DataStructureBean buildDataStructure(VtlDataset vtlDataset) {
        DataStructureMutableBean dsd = new DataStructureMutableBeanImpl();
        dsd.setId("DSD_" + vtlDataset.getName());
        dsd.setAgencyId(environment.getProperty(SDMX_AGENCY_ID));
        dsd.setVersion(environment.getProperty(SDMX_VERSION));
        dsd.addName(environment.getProperty("sdmx.locale"), environment.getProperty("sdmx.dsd.name.prefix") + " " + vtlDataset.getName());
        for (VtlComponent vtlComponent : vtlDataset.getIdentifiers()) {
            if (!vtlComponent.getName().equalsIgnoreCase("time_period")) {
                if (ValueDomainConstants.isDefaultValueDomain(vtlComponent.getDomainValue())) {
                    dsd.addDimension(createConceptReference(vtlComponent.getName()), null).setRepresentation(sdmxUtiltyService.getRepresentationMeasure(vtlComponent));
                } else {
                    DimensionMutableBean newDimension = new DimensionMutableBeanImpl();
                    newDimension.setConceptRef(createConceptReference(vtlComponent.getName()));
                    RepresentationMutableBean representation = new RepresentationMutableBeanImpl();
                    representation.setRepresentation(createCodelistReference(vtlComponent.getDomainValue()));
                    newDimension.setRepresentation(representation);
                    TextFormatMutableBeanImpl textFormatMutableBean = new TextFormatMutableBeanImpl();
                    textFormatMutableBean.setTextType(sdmxUtiltyService.getSdmxType(vtlComponent.getType()));
                    newDimension.getRepresentation().setTextFormat(textFormatMutableBean);
                    dsd.addDimension(newDimension);
                }
            }
        }
        AnnotationMutableBeanImpl annotationMutableBean = new AnnotationMutableBeanImpl();
        annotationMutableBean.setId("IS_MEASURE");
        List<TextTypeWrapperMutableBean> textTypeWrapperMutableBeans = new ArrayList<>();
        TextTypeWrapperMutableBeanImpl textTypeWrapperMutableBean = new TextTypeWrapperMutableBeanImpl();
        VtlComponent vtlComponentObsValueFound = componentUtilityService.getComponentFromName(vtlDataset.getVtlComponents(), "obs_value", true);
        if (vtlComponentObsValueFound == null) {
            textTypeWrapperMutableBean.setValue("false");
        } else {
            textTypeWrapperMutableBean.setValue("true");
        }
        textTypeWrapperMutableBeans.add(textTypeWrapperMutableBean);
        annotationMutableBean.setText(textTypeWrapperMutableBeans);

        dsd.addPrimaryMeasure(createConceptReference("OBS_VALUE")).addAnnotation(annotationMutableBean);
        if (vtlComponentObsValueFound != null) {
            dsd.getMeasureList().getPrimaryMeasure().setRepresentation(sdmxUtiltyService.getRepresentationMeasure(vtlComponentObsValueFound));
        }

        dsd.addDimension(createDimensionMeasure());
        for (VtlComponent vtlComponent : vtlDataset.getAttributes()) {
            dsd.addAttribute(createAttribute(vtlComponent));
        }
        for (VtlComponent vtlComponent : vtlDataset.getViral()) {
            dsd.addAttribute(createViralAttribute(vtlComponent));
        }
        VtlComponent vtlCompoentTimePeriodFound = componentUtilityService.getComponentFromName(vtlDataset.getVtlComponents(), "time_period", true);
        dsd.addDimension(createDimensionMeasureTimeDimension(vtlCompoentTimePeriodFound));

        return dsd.getImmutableInstance();
    }

    /**
     * il metodo si occupa di rappresentare per SDMX la misura di time period.
     * Il metodo può essere utilizzato anche se il vtlComponent è null. In quel caso viene aggiunta un annotazione che
     * determina che il dato è vuoto (id: Empty)
     *
     * @param vtlComponent il componente di time period da rappresentare
     * @return una dimensione rappresentante la misura del time_period
     */
    public DimensionMutableBean createDimensionMeasureTimeDimension(VtlComponent vtlComponent) {
        StructureReferenceBeanImpl conceptRef = new StructureReferenceBeanImpl();
        conceptRef.setAgencyId(environment.getProperty(SDMX_AGENCY_ID));
        conceptRef.setMaintainableId(environment.getProperty(SDMX_CONCEPT_SCHEME_IDENTIFIERS_ID));
        conceptRef.setVersion(environment.getProperty(SDMX_VERSION));
        conceptRef.setTargetStructureType(SDMX_STRUCTURE_TYPE.CONCEPT);

        IdentifiableRefBeanImpl identifiableRefBean = new IdentifiableRefBeanImpl();
        identifiableRefBean.setId("TIME_PERIOD");
        conceptRef.setChildReference(identifiableRefBean);

        DimensionMutableBean dimensionMutableBean = new DimensionMutableBeanImpl();
        dimensionMutableBean.setTimeDimension(true);
        dimensionMutableBean.setConceptRef(conceptRef);

        RepresentationMutableBeanImpl representationMutableBean = new RepresentationMutableBeanImpl();
        TextFormatMutableBeanImpl textFormatMutableBean = new TextFormatMutableBeanImpl();
        textFormatMutableBean.setTextType(TEXT_TYPE.OBSERVATIONAL_TIME_PERIOD);
        representationMutableBean.setTextFormat(textFormatMutableBean);

        dimensionMutableBean.setRepresentation(representationMutableBean);
        if (vtlComponent == null) {
            AnnotationMutableBeanImpl annotationMutableBean = new AnnotationMutableBeanImpl();
            annotationMutableBean.setId("EMPTY");
            TextTypeWrapperMutableBeanImpl textTypeWrapperMutableBean = new TextTypeWrapperMutableBeanImpl();
            textTypeWrapperMutableBean.setValue("true");
            annotationMutableBean.addText(textTypeWrapperMutableBean);
            dimensionMutableBean.addAnnotation(annotationMutableBean);
        }
        return dimensionMutableBean;
    }

    /**
     * Il metodo costruisce il contenitore che contiene le informazioni dei componenti misura. Restituisce una dimension SDMX
     *
     * @return un oggetto che rappresenta la dimensione delle misure
     */
    public DimensionMutableBean createDimensionMeasure() {
        StructureReferenceBeanImpl conceptRef = new StructureReferenceBeanImpl();
        conceptRef.setAgencyId(environment.getProperty(SDMX_AGENCY_ID));
        conceptRef.setMaintainableId(environment.getProperty(SDMX_CONCEPT_SCHEME_IDENTIFIERS_ID));
        conceptRef.setVersion(environment.getProperty(SDMX_VERSION));
        conceptRef.setTargetStructureType(SDMX_STRUCTURE_TYPE.CONCEPT);

        IdentifiableRefBeanImpl identifiableRefBean = new IdentifiableRefBeanImpl();
        identifiableRefBean.setId("MS");
        conceptRef.setChildReference(identifiableRefBean);

        DimensionMutableBean dimensionMutableBean = new DimensionMutableBeanImpl();
        dimensionMutableBean.setMeasureDimension(true);
        dimensionMutableBean.setConceptRef(conceptRef);
        RepresentationMutableBeanImpl representationMutableBean = new RepresentationMutableBeanImpl();

        StructureReferenceBeanImpl structureCodeListRef = new StructureReferenceBeanImpl();
        structureCodeListRef.setAgencyId(environment.getProperty(SDMX_AGENCY_ID));
        structureCodeListRef.setMaintainableId(environment.getProperty("sdmx.concept.scheme.measures.id"));
        structureCodeListRef.setVersion(environment.getProperty(SDMX_VERSION));
        structureCodeListRef.setTargetStructureType(SDMX_STRUCTURE_TYPE.CONCEPT_SCHEME);
        representationMutableBean.setRepresentation(structureCodeListRef);
        dimensionMutableBean.setRepresentation(representationMutableBean);
        return dimensionMutableBean;
    }

    /**
     * il metodo dato in ingresso un componente di ruolo attributo  si occupa di trasfromarlo in formato
     * Attribute per la struttura SDMX.
     * Se l'attributo ha un valueDomain formato da codici viene aggregata anche l'informazione della codelist.
     *
     * @param vtlComponent il componente di ruolo attributo che si vuole rappresentare
     * @return una struttura che rappresenta un attributo SDMX
     */
    public AttributeMutableBeanImpl createAttribute(VtlComponent vtlComponent) {
        AttributeMutableBeanImpl attributeMutableBean = new AttributeMutableBeanImpl();
        attributeMutableBean.setAssignmentStatus("Conditional");
        attributeMutableBean.setId(vtlComponent.getName());
        StructureReferenceBeanImpl attributeRef = new StructureReferenceBeanImpl();
        attributeRef.setAgencyId(environment.getProperty(SDMX_AGENCY_ID));
        attributeRef.setMaintainableId(environment.getProperty(SDMX_CONCEPT_SCHEME_IDENTIFIERS_ID));
        attributeRef.setVersion(environment.getProperty(SDMX_VERSION));
        attributeRef.setTargetStructureType(SDMX_STRUCTURE_TYPE.CONCEPT);

        IdentifiableRefBeanImpl attributeRefBean = new IdentifiableRefBeanImpl();
        attributeRefBean.setId(vtlComponent.getName());
        attributeRef.setChildReference(attributeRefBean);
        attributeMutableBean.setConceptRef(attributeRef);
        attributeMutableBean.setAttachmentLevel(ATTRIBUTE_ATTACHMENT_LEVEL.OBSERVATION);

        if (!ValueDomainConstants.isDefaultValueDomain(vtlComponent.getDomainValue())) {
            RepresentationMutableBeanImpl representationMutableBean = new RepresentationMutableBeanImpl();
            representationMutableBean.setRepresentation(createCodelistReference(vtlComponent.getDomainValue()));
            attributeMutableBean.setRepresentation(representationMutableBean);
        } else {
            RepresentationMutableBeanImpl representationMutableBean = new RepresentationMutableBeanImpl();
            attributeMutableBean.setRepresentation(representationMutableBean);
        }
        TextFormatMutableBeanImpl textFormatMutableBean = new TextFormatMutableBeanImpl();
        textFormatMutableBean.setTextType(sdmxUtiltyService.getSdmxType(vtlComponent.getType()));
        attributeMutableBean.getRepresentation().setTextFormat(textFormatMutableBean);
        return attributeMutableBean;
    }

    /**
     * il metodo dato in ingresso un componente di ruolo attributo virale e si occupa di trasfromarlo in formato
     * Attribute per la struttura SDMX.
     * Se l'attributo virale ha un valueDomain formato da codici viene aggregata anche l'informazione della codelist.
     *
     * @param vtlComponent il componente di ruolo attributo che si vuole rappresentare
     * @return una struttura che rappresenta un attributo SDMX
     */
    public AttributeMutableBeanImpl createViralAttribute(VtlComponent vtlComponent) {
        AttributeMutableBeanImpl viralAttribute = new AttributeMutableBeanImpl();
        viralAttribute.setAssignmentStatus("Conditional");
        viralAttribute.setId(vtlComponent.getName());
        StructureReferenceBeanImpl viralRef = new StructureReferenceBeanImpl();
        viralRef.setAgencyId(environment.getProperty(SDMX_AGENCY_ID));
        viralRef.setMaintainableId(environment.getProperty(SDMX_CONCEPT_SCHEME_IDENTIFIERS_ID));
        viralRef.setVersion(environment.getProperty(SDMX_VERSION));
        viralRef.setTargetStructureType(SDMX_STRUCTURE_TYPE.CONCEPT);
        AnnotationMutableBeanImpl annotationMutableBean = new AnnotationMutableBeanImpl();
        annotationMutableBean.setId("IS_VIRAL");
        List<TextTypeWrapperMutableBean> textTypeWrapperMutableBeans = new ArrayList<>();
        TextTypeWrapperMutableBeanImpl textTypeWrapperMutableBean = new TextTypeWrapperMutableBeanImpl();
        textTypeWrapperMutableBean.setValue("true");
        textTypeWrapperMutableBeans.add(textTypeWrapperMutableBean);
        annotationMutableBean.setText(textTypeWrapperMutableBeans);
        viralAttribute.addAnnotation(annotationMutableBean);
        IdentifiableRefBeanImpl viralRefBean = new IdentifiableRefBeanImpl();
        viralRefBean.setId(vtlComponent.getName());
        viralRef.setChildReference(viralRefBean);
        viralAttribute.setConceptRef(viralRef);
        viralAttribute.setAttachmentLevel(ATTRIBUTE_ATTACHMENT_LEVEL.OBSERVATION);
        if (!ValueDomainConstants.isDefaultValueDomain(vtlComponent.getDomainValue())) {
            RepresentationMutableBeanImpl representationMutableBean = new RepresentationMutableBeanImpl();
            representationMutableBean.setRepresentation(createCodelistReference(vtlComponent.getDomainValue()));
            viralAttribute.setRepresentation(representationMutableBean);
        } else {
            RepresentationMutableBeanImpl representationMutableBean = new RepresentationMutableBeanImpl();
            viralAttribute.setRepresentation(representationMutableBean);
        }
        TextFormatMutableBeanImpl textFormatMutableBean = new TextFormatMutableBeanImpl();
        textFormatMutableBean.setTextType(sdmxUtiltyService.getSdmxType(vtlComponent.getType()));
        viralAttribute.getRepresentation().setTextFormat(textFormatMutableBean);
        return viralAttribute;
    }

    private StructureReferenceBean createCodelistReference(String id) {
        return new StructureReferenceBeanImpl(environment.getProperty(SDMX_AGENCY_ID), id, environment.getProperty(SDMX_VERSION), SDMX_STRUCTURE_TYPE.CODE_LIST);
    }

    private StructureReferenceBean createConceptReference(String id) {
        return new StructureReferenceBeanImpl(environment.getProperty(SDMX_AGENCY_ID), environment.getProperty(SDMX_CONCEPT_SCHEME_IDENTIFIERS_ID), environment.getProperty(SDMX_VERSION), SDMX_STRUCTURE_TYPE.CONCEPT, id);
    }
}
