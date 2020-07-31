package com.capgemini.istat.vtlcompiler.vtlsdmxbuilder.service.builder;

import org.sdmxsource.sdmx.api.model.beans.datastructure.DataStructureBean;
import org.sdmxsource.sdmx.api.model.beans.datastructure.DataflowBean;
import org.sdmxsource.sdmx.api.model.mutable.datastructure.DataflowMutableBean;
import org.sdmxsource.sdmx.sdmxbeans.model.mutable.metadatastructure.DataflowMutableBeanImpl;
import org.springframework.stereotype.Service;

/**
 * Questa è una classe di esempio di popolamento struttura. Non ha un utilizzo pratico
 */
@Service
public class DataFlowBuilderService {

    /**
     * il metodo serve a creare un dataflow. Questo metodo è di test e non viene utilizzato ai fini di produzione
     * di un sdmx valido
     *
     * @param id   l'id del dataFlow
     * @param name il n ome del dataflow
     * @param dsd  la data structure da aggregare al dataflow
     * @return un dataflow valido ai fini della costruzione di un SDMX
     */
    public DataflowBean buildDataflow(String id, String name, DataStructureBean dsd) {
        DataflowMutableBean dataflow = new DataflowMutableBeanImpl();
        dataflow.setAgencyId("VTL");
        dataflow.setId(id);
        dataflow.addName("en", name);

        dataflow.setDataStructureRef(dsd.asReference());

        return dataflow.getImmutableInstance();
    }
}
