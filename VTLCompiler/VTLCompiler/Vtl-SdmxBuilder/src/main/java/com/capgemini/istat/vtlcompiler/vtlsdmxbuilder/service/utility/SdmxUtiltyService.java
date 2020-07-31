package com.capgemini.istat.vtlcompiler.vtlsdmxbuilder.service.utility;

import com.capgemini.istat.vtlcompiler.vtlcommon.model.dataset.VtlComponent;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.dataset.VtlType;
import org.sdmxsource.sdmx.api.constants.TEXT_TYPE;
import org.sdmxsource.sdmx.sdmxbeans.model.mutable.base.RepresentationMutableBeanImpl;
import org.sdmxsource.sdmx.sdmxbeans.model.mutable.base.TextFormatMutableBeanImpl;
import org.springframework.stereotype.Service;

/**
 * La classe offre una serie di metodi di utilità che servono agli altri servizi per popolare la struttura sdmx.
 */
@Service
public class SdmxUtiltyService {

    /**
     * Dato un tipo Vtl viene restituito il tipo SDMX
     * @param vtlType Il tipo vtl da convertire
     * @return il TextTipe corrispondente per la libreria di costruzione di struttura sdmx
     */
    public TEXT_TYPE getSdmxType(VtlType vtlType) {
        switch (vtlType) {
            case STRING:
                return TEXT_TYPE.STRING;
            case NUMBER:
                return TEXT_TYPE.FLOAT;
            case INTEGER:
                return TEXT_TYPE.INTEGER;
            case DATE:
                return TEXT_TYPE.DATE_TIME;
            case TIME:
                return TEXT_TYPE.STANDARD_TIME_PERIOD;
            case TIME_PERIOD:
                return TEXT_TYPE.REPORTING_TIME_PERIOD;
            case DURATION:
                return TEXT_TYPE.DURATION;
            case BOOLEAN:
                return TEXT_TYPE.BOOLEAN;
            default:
                return null;
        }
    }

    /**
     * Dato in ingresso un vtlComponent viene restituita una struttura sdmx in grado di rappresentarla
     *
     * @param vtlComponent il componente da mappare in SDMX
     * @return un oggetto RepresentationMutableBeanImpl in grado di rappresentare il componente secondo specifiche sdmx
     */
    public RepresentationMutableBeanImpl getRepresentationMeasure(VtlComponent vtlComponent) {
        RepresentationMutableBeanImpl representationMutableBean = new RepresentationMutableBeanImpl();
        TextFormatMutableBeanImpl textFormatMutableBean = new TextFormatMutableBeanImpl();
        textFormatMutableBean.setTextType(getSdmxType(vtlComponent.getType()));
        representationMutableBean.setTextFormat(textFormatMutableBean);
        return representationMutableBean;
    }

}
