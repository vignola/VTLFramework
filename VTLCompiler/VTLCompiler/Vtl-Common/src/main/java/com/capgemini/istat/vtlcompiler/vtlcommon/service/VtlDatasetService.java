package com.capgemini.istat.vtlcompiler.vtlcommon.service;

import com.capgemini.istat.vtlcompiler.vtlcommon.model.dataset.VtlComponent;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.dataset.VtlComponentRole;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.dataset.VtlDataset;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.dataset.VtlType;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.valuedomain.ValueDomain;
import com.capgemini.istat.vtlcompiler.vtlcommon.repository.DatasetRepository;
import com.capgemini.istat.vtlcompiler.vtlcommon.repository.ValueDomainRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * la classe offre tutti i metodi necessari per la creazione dei dataset da API. Viene utilizzata come classe di
 * servizio predisposta all'utilizzo verso l'esterno e non possiede logica di manipolazione dataset.
 */
@Service
public class VtlDatasetService {
    public static final String IDENT = "IDENT";
    public static final String VD_AT_2 = "VD_AT2";
    public static final String VD_ID_2 = "VD_ID2";
    public static final String VD_ID_1 = "VD_ID1";
    public static final String DIM_CL_H_DAIRYPROD_BHREV = "DIM_CL_H_DAIRYPROD_BHREV";
    public static final String OBS_VALUE = "OBS_VALUE";
    private DatasetRepository datasetRepository;
    private ValueDomainRepository valueDomainRepository;


    @Autowired
    public void setDatasetRepository(DatasetRepository datasetRepository) {
        this.datasetRepository = datasetRepository;
    }

    @Autowired
    public void setValueDomainRepository(ValueDomainRepository valueDomainRepository) {
        this.valueDomainRepository = valueDomainRepository;
    }

    /**
     * Il metodo crea una serie di dataset utili ai fini dei test. Non dovrebbe essere utilizzato se non per fare alcuni test
     *
     * @return Torna true se la creazione è avvenuta con successo
     */
    public boolean setUpMockVtlDatasets() {
        datasetRepository.deleteAll();
        valueDomainRepository.deleteAll();

        VtlDataset vtlDataset = new VtlDataset();
        vtlDataset.setPersistent(true);
        vtlDataset.setName("DS_1");
        vtlDataset.setIsOnlyAScalar(false);
        vtlDataset.setTransitory(false);
        List<VtlComponent> vtlComponents = new ArrayList<>();

        VtlComponent vtlComponent;

        vtlComponent = new VtlComponent();
        vtlComponent.setName("ID_1");
        vtlComponent.setType(VtlType.INTEGER);
        vtlComponent.setDomainValue(VtlType.INTEGER.getDomainValue());
        vtlComponent.setDomainValueParent(VtlType.INTEGER.getDomainValueParent());
        vtlComponent.setVtlComponentRole(VtlComponentRole.IDENTIFIER);
        vtlComponent.setVtlDataset(vtlDataset);
        vtlComponents.add(vtlComponent);

        vtlComponent = new VtlComponent();
        vtlComponent.setName("ID_2");
        vtlComponent.setType(VtlType.STRING);
        vtlComponent.setDomainValue(VtlType.STRING.getDomainValue());
        vtlComponent.setDomainValueParent(VtlType.STRING.getDomainValueParent());
        vtlComponent.setVtlComponentRole(VtlComponentRole.IDENTIFIER);
        vtlComponent.setVtlDataset(vtlDataset);
        vtlComponents.add(vtlComponent);

        vtlComponent = new VtlComponent();
        vtlComponent.setName("ME_1");
        vtlComponent.setType(VtlType.INTEGER);
        vtlComponent.setDomainValue(VtlType.INTEGER.getDomainValue());
        vtlComponent.setDomainValueParent(VtlType.INTEGER.getDomainValueParent());
        vtlComponent.setVtlComponentRole(VtlComponentRole.MEASURE);
        vtlComponent.setVtlDataset(vtlDataset);
        vtlComponents.add(vtlComponent);

        vtlComponent = new VtlComponent();
        vtlComponent.setName("ME_2");
        vtlComponent.setType(VtlType.INTEGER);
        vtlComponent.setDomainValue(VtlType.INTEGER.getDomainValue());
        vtlComponent.setDomainValueParent(VtlType.INTEGER.getDomainValueParent());
        vtlComponent.setVtlComponentRole(VtlComponentRole.MEASURE);
        vtlComponent.setVtlDataset(vtlDataset);
        vtlComponents.add(vtlComponent);


        vtlDataset.addComponentsList(vtlComponents);
        datasetRepository.save(vtlDataset);


        VtlDataset vtlDataset2 = new VtlDataset();
        vtlDataset2.setPersistent(true);
        vtlDataset2.setName("DS_2");
        vtlDataset2.setIsOnlyAScalar(false);
        vtlDataset2.setTransitory(false);
        vtlComponents = new ArrayList<>();

        vtlComponent = new VtlComponent();
        vtlComponent.setName("ID_1");
        vtlComponent.setType(VtlType.INTEGER);
        vtlComponent.setDomainValue(VtlType.INTEGER.getDomainValue());
        vtlComponent.setDomainValueParent(VtlType.INTEGER.getDomainValueParent());
        vtlComponent.setVtlComponentRole(VtlComponentRole.IDENTIFIER);
        vtlComponent.setVtlDataset(vtlDataset);
        vtlComponents.add(vtlComponent);

        vtlComponent = new VtlComponent();
        vtlComponent.setName("ID_2");
        vtlComponent.setType(VtlType.STRING);
        vtlComponent.setDomainValue(VtlType.STRING.getDomainValue());
        vtlComponent.setDomainValueParent(VtlType.STRING.getDomainValueParent());
        vtlComponent.setVtlComponentRole(VtlComponentRole.IDENTIFIER);
        vtlComponent.setVtlDataset(vtlDataset);
        vtlComponents.add(vtlComponent);

        vtlComponent = new VtlComponent();
        vtlComponent.setName("ME_1");
        vtlComponent.setType(VtlType.INTEGER);
        vtlComponent.setDomainValue(VtlType.INTEGER.getDomainValue());
        vtlComponent.setDomainValueParent(VtlType.INTEGER.getDomainValueParent());
        vtlComponent.setVtlComponentRole(VtlComponentRole.MEASURE);
        vtlComponent.setVtlDataset(vtlDataset);
        vtlComponents.add(vtlComponent);

        vtlComponent = new VtlComponent();
        vtlComponent.setName("ME_2");
        vtlComponent.setType(VtlType.INTEGER);
        vtlComponent.setDomainValue(VtlType.INTEGER.getDomainValue());
        vtlComponent.setDomainValueParent(VtlType.INTEGER.getDomainValueParent());
        vtlComponent.setVtlComponentRole(VtlComponentRole.MEASURE);
        vtlComponent.setVtlDataset(vtlDataset);
        vtlComponents.add(vtlComponent);

        vtlDataset2.addComponentsList(vtlComponents);
        datasetRepository.save(vtlDataset2);


        VtlDataset vtlDataset3 = new VtlDataset();
        vtlDataset3.setPersistent(true);
        vtlDataset3.setName("DS_3");
        vtlDataset3.setIsOnlyAScalar(false);
        vtlDataset3.setTransitory(false);
        vtlComponents = new ArrayList<>();

        vtlComponent = new VtlComponent();
        vtlComponent.setName("ID_1");
        vtlComponent.setType(VtlType.INTEGER);
        vtlComponent.setDomainValue(VtlType.INTEGER.getDomainValue());
        vtlComponent.setDomainValueParent(VtlType.INTEGER.getDomainValueParent());
        vtlComponent.setVtlComponentRole(VtlComponentRole.IDENTIFIER);
        vtlComponent.setVtlDataset(vtlDataset);
        vtlComponents.add(vtlComponent);

        vtlComponent = new VtlComponent();
        vtlComponent.setName("ID_2");
        vtlComponent.setType(VtlType.STRING);
        vtlComponent.setDomainValue(VtlType.STRING.getDomainValue());
        vtlComponent.setDomainValueParent(VtlType.STRING.getDomainValueParent());
        vtlComponent.setVtlComponentRole(VtlComponentRole.IDENTIFIER);
        vtlComponent.setVtlDataset(vtlDataset);
        vtlComponents.add(vtlComponent);

        vtlComponent = new VtlComponent();
        vtlComponent.setName("ME_1");
        vtlComponent.setType(VtlType.STRING);
        vtlComponent.setDomainValue(VtlType.STRING.getDomainValue());
        vtlComponent.setDomainValueParent(VtlType.STRING.getDomainValueParent());
        vtlComponent.setVtlComponentRole(VtlComponentRole.MEASURE);
        vtlComponent.setVtlDataset(vtlDataset);
        vtlComponents.add(vtlComponent);

        vtlComponent = new VtlComponent();
        vtlComponent.setName("ME_3");
        vtlComponent.setType(VtlType.STRING);
        vtlComponent.setDomainValue(VtlType.STRING.getDomainValue());
        vtlComponent.setDomainValueParent(VtlType.STRING.getDomainValueParent());
        vtlComponent.setVtlComponentRole(VtlComponentRole.MEASURE);
        vtlComponent.setVtlDataset(vtlDataset);
        vtlComponents.add(vtlComponent);

        vtlDataset3.addComponentsList(vtlComponents);
        datasetRepository.save(vtlDataset3);


        VtlDataset vtlDataset4 = new VtlDataset();
        vtlDataset4.setPersistent(true);
        vtlDataset4.setName("DS_4");
        vtlDataset4.setIsOnlyAScalar(false);
        vtlDataset4.setTransitory(false);
        vtlComponents = new ArrayList<>();

        vtlComponent = new VtlComponent();
        vtlComponent.setName("ID_1");
        vtlComponent.setType(VtlType.INTEGER);
        vtlComponent.setDomainValue(VtlType.INTEGER.getDomainValue());
        vtlComponent.setDomainValueParent(VtlType.INTEGER.getDomainValueParent());
        vtlComponent.setVtlComponentRole(VtlComponentRole.IDENTIFIER);
        vtlComponent.setVtlDataset(vtlDataset);
        vtlComponents.add(vtlComponent);

        vtlComponent = new VtlComponent();
        vtlComponent.setName("ID_2");
        vtlComponent.setType(VtlType.STRING);
        vtlComponent.setDomainValue(VtlType.STRING.getDomainValue());
        vtlComponent.setDomainValueParent(VtlType.STRING.getDomainValueParent());
        vtlComponent.setVtlComponentRole(VtlComponentRole.IDENTIFIER);
        vtlComponent.setVtlDataset(vtlDataset);
        vtlComponents.add(vtlComponent);

        vtlComponent = new VtlComponent();
        vtlComponent.setName("ME_1");
        vtlComponent.setType(VtlType.STRING);
        vtlComponent.setDomainValue(VtlType.STRING.getDomainValue());
        vtlComponent.setDomainValueParent(VtlType.STRING.getDomainValueParent());
        vtlComponent.setVtlComponentRole(VtlComponentRole.MEASURE);
        vtlComponent.setVtlDataset(vtlDataset);
        vtlComponents.add(vtlComponent);

        vtlComponent = new VtlComponent();
        vtlComponent.setName("ME_2");
        vtlComponent.setType(VtlType.STRING);
        vtlComponent.setDomainValue(VtlType.STRING.getDomainValue());
        vtlComponent.setDomainValueParent(VtlType.STRING.getDomainValueParent());
        vtlComponent.setVtlComponentRole(VtlComponentRole.MEASURE);
        vtlComponent.setVtlDataset(vtlDataset);
        vtlComponents.add(vtlComponent);

        vtlDataset4.addComponentsList(vtlComponents);
        datasetRepository.save(vtlDataset4);


        VtlDataset vtlDataset5 = new VtlDataset();
        vtlDataset5.setPersistent(true);
        vtlDataset5.setName("DS_5");
        vtlDataset5.setIsOnlyAScalar(false);
        vtlDataset5.setTransitory(false);
        vtlComponents = new ArrayList<>();

        vtlComponent = new VtlComponent();
        vtlComponent.setName("ID_2");
        vtlComponent.setType(VtlType.STRING);
        vtlComponent.setDomainValue(VtlType.STRING.getDomainValue());
        vtlComponent.setDomainValueParent(VtlType.STRING.getDomainValueParent());
        vtlComponent.setVtlComponentRole(VtlComponentRole.IDENTIFIER);
        vtlComponent.setVtlDataset(vtlDataset);
        vtlComponents.add(vtlComponent);

        vtlComponent = new VtlComponent();
        vtlComponent.setName("ME_1");
        vtlComponent.setType(VtlType.STRING);
        vtlComponent.setDomainValue(VtlType.STRING.getDomainValue());
        vtlComponent.setDomainValueParent(VtlType.STRING.getDomainValueParent());
        vtlComponent.setVtlComponentRole(VtlComponentRole.MEASURE);
        vtlComponent.setVtlDataset(vtlDataset);
        vtlComponents.add(vtlComponent);

        vtlDataset5.addComponentsList(vtlComponents);
        datasetRepository.save(vtlDataset5);


        VtlDataset vtlDataset6 = new VtlDataset();
        vtlDataset6.setPersistent(true);
        vtlDataset6.setName("DS_6");
        vtlDataset6.setIsOnlyAScalar(false);
        vtlDataset6.setTransitory(false);
        vtlComponents = new ArrayList<>();

        vtlComponent = new VtlComponent();
        vtlComponent.setName("ID_1");
        vtlComponent.setType(VtlType.INTEGER);
        vtlComponent.setDomainValue(VtlType.INTEGER.getDomainValue());
        vtlComponent.setDomainValueParent(VtlType.INTEGER.getDomainValueParent());
        vtlComponent.setVtlComponentRole(VtlComponentRole.IDENTIFIER);
        vtlComponent.setVtlDataset(vtlDataset);
        vtlComponents.add(vtlComponent);

        vtlComponent = new VtlComponent();
        vtlComponent.setName("ID_2");
        vtlComponent.setType(VtlType.STRING);
        vtlComponent.setDomainValue(VtlType.STRING.getDomainValue());
        vtlComponent.setDomainValueParent(VtlType.STRING.getDomainValueParent());
        vtlComponent.setVtlComponentRole(VtlComponentRole.IDENTIFIER);
        vtlComponent.setVtlDataset(vtlDataset);
        vtlComponents.add(vtlComponent);

        vtlComponent = new VtlComponent();
        vtlComponent.setName("ME_1");
        vtlComponent.setType(VtlType.STRING);
        vtlComponent.setDomainValue(VtlType.STRING.getDomainValue());
        vtlComponent.setDomainValueParent(VtlType.STRING.getDomainValueParent());
        vtlComponent.setVtlComponentRole(VtlComponentRole.MEASURE);
        vtlComponent.setVtlDataset(vtlDataset);
        vtlComponents.add(vtlComponent);

        vtlComponent = new VtlComponent();
        vtlComponent.setName("AT_1");
        vtlComponent.setType(VtlType.STRING);
        vtlComponent.setDomainValue(VtlType.STRING.getDomainValue());
        vtlComponent.setDomainValueParent(VtlType.STRING.getDomainValueParent());
        vtlComponent.setVtlComponentRole(VtlComponentRole.VIRAL);
        vtlComponent.setVtlDataset(vtlDataset);
        vtlComponents.add(vtlComponent);

        vtlDataset6.addComponentsList(vtlComponents);
        datasetRepository.save(vtlDataset6);


        VtlDataset vtlDataset7 = new VtlDataset();
        vtlDataset7.setPersistent(true);
        vtlDataset7.setName("DS_7");
        vtlDataset7.setIsOnlyAScalar(false);
        vtlDataset7.setTransitory(false);
        vtlComponents = new ArrayList<>();

        vtlComponent = new VtlComponent();
        vtlComponent.setName("ID_1");
        vtlComponent.setType(VtlType.INTEGER);
        vtlComponent.setDomainValue(VtlType.INTEGER.getDomainValue());
        vtlComponent.setDomainValueParent(VtlType.INTEGER.getDomainValueParent());
        vtlComponent.setVtlComponentRole(VtlComponentRole.IDENTIFIER);
        vtlComponent.setVtlDataset(vtlDataset);
        vtlComponents.add(vtlComponent);

        vtlComponent = new VtlComponent();
        vtlComponent.setName("ID_2");
        vtlComponent.setType(VtlType.STRING);
        vtlComponent.setDomainValue(VtlType.STRING.getDomainValue());
        vtlComponent.setDomainValueParent(VtlType.STRING.getDomainValueParent());
        vtlComponent.setVtlComponentRole(VtlComponentRole.IDENTIFIER);
        vtlComponent.setVtlDataset(vtlDataset);
        vtlComponents.add(vtlComponent);

        vtlComponent = new VtlComponent();
        vtlComponent.setName("ME_1");
        vtlComponent.setType(VtlType.NUMBER);
        vtlComponent.setDomainValue(VtlType.NUMBER.getDomainValue());
        vtlComponent.setDomainValueParent(VtlType.NUMBER.getDomainValueParent());
        vtlComponent.setVtlComponentRole(VtlComponentRole.MEASURE);
        vtlComponent.setVtlDataset(vtlDataset);
        vtlComponents.add(vtlComponent);

        vtlComponent = new VtlComponent();
        vtlComponent.setName("ME_2");
        vtlComponent.setType(VtlType.INTEGER);
        vtlComponent.setDomainValue(VtlType.INTEGER.getDomainValue());
        vtlComponent.setDomainValueParent(VtlType.INTEGER.getDomainValueParent());
        vtlComponent.setVtlComponentRole(VtlComponentRole.MEASURE);
        vtlComponent.setVtlDataset(vtlDataset);
        vtlComponents.add(vtlComponent);

        vtlDataset7.addComponentsList(vtlComponents);
        datasetRepository.save(vtlDataset7);


        VtlDataset vtlDataset8 = new VtlDataset();
        vtlDataset8.setPersistent(true);
        vtlDataset8.setName("DS_8");
        vtlDataset8.setIsOnlyAScalar(false);
        vtlDataset8.setTransitory(false);
        vtlComponents = new ArrayList<>();

        vtlComponent = new VtlComponent();
        vtlComponent.setName("ID_1");
        vtlComponent.setType(VtlType.INTEGER);
        vtlComponent.setDomainValue(VtlType.INTEGER.getDomainValue());
        vtlComponent.setDomainValueParent(VtlType.INTEGER.getDomainValueParent());
        vtlComponent.setVtlComponentRole(VtlComponentRole.IDENTIFIER);
        vtlComponent.setVtlDataset(vtlDataset);
        vtlComponents.add(vtlComponent);

        vtlComponent = new VtlComponent();
        vtlComponent.setName("ID_2");
        vtlComponent.setType(VtlType.STRING);
        vtlComponent.setDomainValue(VtlType.STRING.getDomainValue());
        vtlComponent.setDomainValueParent(VtlType.STRING.getDomainValueParent());
        vtlComponent.setVtlComponentRole(VtlComponentRole.IDENTIFIER);
        vtlComponent.setVtlDataset(vtlDataset);
        vtlComponents.add(vtlComponent);


        vtlComponent = new VtlComponent();
        vtlComponent.setName("ME_1");
        vtlComponent.setType(VtlType.INTEGER);
        vtlComponent.setDomainValue(VtlType.INTEGER.getDomainValue());
        vtlComponent.setDomainValueParent(VtlType.INTEGER.getDomainValueParent());
        vtlComponent.setVtlComponentRole(VtlComponentRole.MEASURE);
        vtlComponent.setVtlDataset(vtlDataset);
        vtlComponents.add(vtlComponent);

        vtlComponent = new VtlComponent();
        vtlComponent.setName("ME_2");
        vtlComponent.setType(VtlType.NUMBER);
        vtlComponent.setDomainValue(VtlType.NUMBER.getDomainValue());
        vtlComponent.setDomainValueParent(VtlType.NUMBER.getDomainValueParent());
        vtlComponent.setVtlComponentRole(VtlComponentRole.MEASURE);
        vtlComponent.setVtlDataset(vtlDataset);
        vtlComponents.add(vtlComponent);

        vtlDataset8.addComponentsList(vtlComponents);
        datasetRepository.save(vtlDataset8);


        VtlDataset vtlDataset9 = new VtlDataset();
        vtlDataset9.setPersistent(true);
        vtlDataset9.setName("DS_9");
        vtlDataset9.setIsOnlyAScalar(false);
        vtlDataset9.setTransitory(false);
        vtlComponents = new ArrayList<>();

        vtlComponent = new VtlComponent();
        vtlComponent.setName("ID_1");
        vtlComponent.setType(VtlType.INTEGER);
        vtlComponent.setDomainValue(VtlType.INTEGER.getDomainValue());
        vtlComponent.setDomainValueParent(VtlType.INTEGER.getDomainValueParent());
        vtlComponent.setVtlComponentRole(VtlComponentRole.IDENTIFIER);
        vtlComponent.setVtlDataset(vtlDataset);
        vtlComponents.add(vtlComponent);

        vtlComponent = new VtlComponent();
        vtlComponent.setName("ID_2");
        vtlComponent.setType(VtlType.STRING);
        vtlComponent.setDomainValue(VtlType.STRING.getDomainValue());
        vtlComponent.setDomainValueParent(VtlType.STRING.getDomainValueParent());
        vtlComponent.setVtlComponentRole(VtlComponentRole.IDENTIFIER);
        vtlComponent.setVtlDataset(vtlDataset);
        vtlComponents.add(vtlComponent);


        vtlComponent = new VtlComponent();
        vtlComponent.setName("ME_1");
        vtlComponent.setType(VtlType.INTEGER);
        vtlComponent.setDomainValue(VtlType.INTEGER.getDomainValue());
        vtlComponent.setDomainValueParent(VtlType.INTEGER.getDomainValueParent());
        vtlComponent.setVtlComponentRole(VtlComponentRole.MEASURE);
        vtlComponent.setVtlDataset(vtlDataset);
        vtlComponents.add(vtlComponent);

        vtlComponent = new VtlComponent();
        vtlComponent.setName("ME_2");
        vtlComponent.setType(VtlType.NUMBER);
        vtlComponent.setDomainValue(VtlType.NUMBER.getDomainValue());
        vtlComponent.setDomainValueParent(VtlType.NUMBER.getDomainValueParent());
        vtlComponent.setVtlComponentRole(VtlComponentRole.MEASURE);
        vtlComponent.setVtlDataset(vtlDataset);
        vtlComponents.add(vtlComponent);

        vtlDataset9.addComponentsList(vtlComponents);
        datasetRepository.save(vtlDataset9);


        VtlDataset vtlDataset10 = new VtlDataset();
        vtlDataset10.setPersistent(true);
        vtlDataset10.setName("DS_10");
        vtlDataset10.setIsOnlyAScalar(false);
        vtlDataset10.setTransitory(false);
        vtlComponents = new ArrayList<>();

        vtlComponent = new VtlComponent();
        vtlComponent.setName("ID_1");
        vtlComponent.setType(VtlType.INTEGER);
        vtlComponent.setDomainValue(VtlType.INTEGER.getDomainValue());
        vtlComponent.setDomainValueParent(VtlType.INTEGER.getDomainValueParent());
        vtlComponent.setVtlComponentRole(VtlComponentRole.IDENTIFIER);
        vtlComponent.setVtlDataset(vtlDataset);
        vtlComponents.add(vtlComponent);

        vtlComponent = new VtlComponent();
        vtlComponent.setName("ID_2");
        vtlComponent.setType(VtlType.STRING);
        vtlComponent.setDomainValue(VtlType.STRING.getDomainValue());
        vtlComponent.setDomainValueParent(VtlType.STRING.getDomainValueParent());
        vtlComponent.setVtlComponentRole(VtlComponentRole.IDENTIFIER);
        vtlComponent.setVtlDataset(vtlDataset);
        vtlComponents.add(vtlComponent);

        vtlComponent = new VtlComponent();
        vtlComponent.setName("ME_1");
        vtlComponent.setType(VtlType.NUMBER);
        vtlComponent.setDomainValue(VtlType.NUMBER.getDomainValue());
        vtlComponent.setDomainValueParent(VtlType.NUMBER.getDomainValueParent());
        vtlComponent.setVtlComponentRole(VtlComponentRole.MEASURE);
        vtlComponent.setVtlDataset(vtlDataset);
        vtlComponents.add(vtlComponent);

        vtlComponent = new VtlComponent();
        vtlComponent.setName("ME_2");
        vtlComponent.setType(VtlType.NUMBER);
        vtlComponent.setDomainValue(VtlType.NUMBER.getDomainValue());
        vtlComponent.setDomainValueParent(VtlType.NUMBER.getDomainValueParent());
        vtlComponent.setVtlComponentRole(VtlComponentRole.MEASURE);
        vtlComponent.setVtlDataset(vtlDataset);
        vtlComponents.add(vtlComponent);

        vtlDataset10.addComponentsList(vtlComponents);
        datasetRepository.save(vtlDataset10);


        VtlDataset vtlDataset11 = new VtlDataset();
        vtlDataset11.setPersistent(true);
        vtlDataset11.setName("DS_11");
        vtlDataset11.setIsOnlyAScalar(false);
        vtlDataset11.setTransitory(false);
        vtlComponents = new ArrayList<>();

        vtlComponent = new VtlComponent();
        vtlComponent.setName("ID_1");
        vtlComponent.setType(VtlType.INTEGER);
        vtlComponent.setDomainValue(VtlType.INTEGER.getDomainValue());
        vtlComponent.setDomainValueParent(VtlType.INTEGER.getDomainValueParent());
        vtlComponent.setVtlComponentRole(VtlComponentRole.IDENTIFIER);
        vtlComponent.setVtlDataset(vtlDataset);
        vtlComponents.add(vtlComponent);

        vtlComponent = new VtlComponent();
        vtlComponent.setName("ID_2");
        vtlComponent.setType(VtlType.STRING);
        vtlComponent.setDomainValue(VtlType.STRING.getDomainValue());
        vtlComponent.setDomainValueParent(VtlType.STRING.getDomainValueParent());
        vtlComponent.setVtlComponentRole(VtlComponentRole.IDENTIFIER);
        vtlComponent.setVtlDataset(vtlDataset);
        vtlComponents.add(vtlComponent);

        vtlComponent = new VtlComponent();
        vtlComponent.setName("ID_3");
        vtlComponent.setType(VtlType.STRING);
        vtlComponent.setDomainValue(VtlType.STRING.getDomainValue());
        vtlComponent.setDomainValueParent(VtlType.STRING.getDomainValueParent());
        vtlComponent.setVtlComponentRole(VtlComponentRole.IDENTIFIER);
        vtlComponent.setVtlDataset(vtlDataset);
        vtlComponents.add(vtlComponent);

        vtlComponent = new VtlComponent();
        vtlComponent.setName("ID_4");
        vtlComponent.setType(VtlType.STRING);
        vtlComponent.setDomainValue(VtlType.STRING.getDomainValue());
        vtlComponent.setDomainValueParent(VtlType.STRING.getDomainValueParent());
        vtlComponent.setVtlComponentRole(VtlComponentRole.IDENTIFIER);
        vtlComponent.setVtlDataset(vtlDataset);
        vtlComponents.add(vtlComponent);

        vtlComponent = new VtlComponent();
        vtlComponent.setName("ME_1");
        vtlComponent.setType(VtlType.NUMBER);
        vtlComponent.setDomainValue(VtlType.NUMBER.getDomainValue());
        vtlComponent.setDomainValueParent(VtlType.NUMBER.getDomainValueParent());
        vtlComponent.setVtlComponentRole(VtlComponentRole.MEASURE);
        vtlComponent.setVtlDataset(vtlDataset);
        vtlComponents.add(vtlComponent);

        vtlDataset11.addComponentsList(vtlComponents);
        datasetRepository.save(vtlDataset11);


        VtlDataset vtlDataset12 = new VtlDataset();
        vtlDataset12.setPersistent(true);
        vtlDataset12.setName("DS_12");
        vtlDataset12.setIsOnlyAScalar(false);
        vtlDataset12.setTransitory(false);
        vtlComponents = new ArrayList<>();

        vtlComponent = new VtlComponent();
        vtlComponent.setName("ID_1");
        vtlComponent.setType(VtlType.INTEGER);
        vtlComponent.setDomainValue(VtlType.INTEGER.getDomainValue());
        vtlComponent.setDomainValueParent(VtlType.INTEGER.getDomainValueParent());
        vtlComponent.setVtlComponentRole(VtlComponentRole.IDENTIFIER);
        vtlComponent.setVtlDataset(vtlDataset);
        vtlComponents.add(vtlComponent);

        vtlComponent = new VtlComponent();
        vtlComponent.setName("ID_2");
        vtlComponent.setType(VtlType.STRING);
        vtlComponent.setDomainValue(VtlType.STRING.getDomainValue());
        vtlComponent.setDomainValueParent(VtlType.STRING.getDomainValueParent());
        vtlComponent.setVtlComponentRole(VtlComponentRole.IDENTIFIER);
        vtlComponent.setVtlDataset(vtlDataset);
        vtlComponents.add(vtlComponent);

        vtlComponent = new VtlComponent();
        vtlComponent.setName("ID_4");
        vtlComponent.setType(VtlType.STRING);
        vtlComponent.setDomainValue(VtlType.STRING.getDomainValue());
        vtlComponent.setDomainValueParent(VtlType.STRING.getDomainValueParent());
        vtlComponent.setVtlComponentRole(VtlComponentRole.IDENTIFIER);
        vtlComponent.setVtlDataset(vtlDataset);
        vtlComponents.add(vtlComponent);

        vtlComponent = new VtlComponent();
        vtlComponent.setName("ME_1");
        vtlComponent.setType(VtlType.NUMBER);
        vtlComponent.setDomainValue(VtlType.NUMBER.getDomainValue());
        vtlComponent.setDomainValueParent(VtlType.NUMBER.getDomainValueParent());
        vtlComponent.setVtlComponentRole(VtlComponentRole.MEASURE);
        vtlComponent.setVtlDataset(vtlDataset);
        vtlComponents.add(vtlComponent);

        vtlDataset12.addComponentsList(vtlComponents);
        datasetRepository.save(vtlDataset12);


        VtlDataset vtlDataset13 = new VtlDataset();
        vtlDataset13.setPersistent(true);
        vtlDataset13.setName("DS_13");
        vtlDataset13.setIsOnlyAScalar(false);
        vtlDataset13.setTransitory(false);
        vtlComponents = new ArrayList<>();

        vtlComponent = new VtlComponent();
        vtlComponent.setName("ID_1");
        vtlComponent.setType(VtlType.STRING);
        vtlComponent.setDomainValue(VtlType.STRING.getDomainValue());
        vtlComponent.setDomainValueParent(VtlType.STRING.getDomainValueParent());
        vtlComponent.setVtlComponentRole(VtlComponentRole.IDENTIFIER);
        vtlComponent.setVtlDataset(vtlDataset);
        vtlComponents.add(vtlComponent);

        vtlComponent = new VtlComponent();
        vtlComponent.setName("ID_2");
        vtlComponent.setType(VtlType.STRING);
        vtlComponent.setDomainValue(VtlType.STRING.getDomainValue());
        vtlComponent.setDomainValueParent(VtlType.STRING.getDomainValueParent());
        vtlComponent.setVtlComponentRole(VtlComponentRole.IDENTIFIER);
        vtlComponent.setVtlDataset(vtlDataset);
        vtlComponents.add(vtlComponent);

        vtlComponent = new VtlComponent();
        vtlComponent.setName("ID_3");
        vtlComponent.setType(VtlType.STRING);
        vtlComponent.setDomainValue(VtlType.STRING.getDomainValue());
        vtlComponent.setDomainValueParent(VtlType.STRING.getDomainValueParent());
        vtlComponent.setVtlComponentRole(VtlComponentRole.IDENTIFIER);
        vtlComponent.setVtlDataset(vtlDataset);
        vtlComponents.add(vtlComponent);

        vtlComponent = new VtlComponent();
        vtlComponent.setName("ID_4");
        vtlComponent.setType(VtlType.STRING);
        vtlComponent.setDomainValue(VtlType.STRING.getDomainValue());
        vtlComponent.setDomainValueParent(VtlType.STRING.getDomainValueParent());
        vtlComponent.setVtlComponentRole(VtlComponentRole.IDENTIFIER);
        vtlComponent.setVtlDataset(vtlDataset);
        vtlComponents.add(vtlComponent);

        vtlComponent = new VtlComponent();
        vtlComponent.setName("ME_1");
        vtlComponent.setType(VtlType.NUMBER);
        vtlComponent.setDomainValue(VtlType.NUMBER.getDomainValue());
        vtlComponent.setDomainValueParent(VtlType.NUMBER.getDomainValueParent());
        vtlComponent.setVtlComponentRole(VtlComponentRole.MEASURE);
        vtlComponent.setVtlDataset(vtlDataset);
        vtlComponents.add(vtlComponent);

        vtlDataset13.addComponentsList(vtlComponents);
        datasetRepository.save(vtlDataset13);


        VtlDataset vtlDataset14 = new VtlDataset();
        vtlDataset14.setPersistent(true);
        vtlDataset14.setName("DS_14");
        vtlDataset14.setIsOnlyAScalar(false);
        vtlDataset14.setTransitory(false);
        vtlComponents = new ArrayList<>();

        vtlComponent = new VtlComponent();
        vtlComponent.setName("ID_1");
        vtlComponent.setType(VtlType.STRING);
        vtlComponent.setDomainValue(VtlType.STRING.getDomainValue());
        vtlComponent.setDomainValueParent(VtlType.STRING.getDomainValueParent());
        vtlComponent.setVtlComponentRole(VtlComponentRole.IDENTIFIER);
        vtlComponent.setVtlDataset(vtlDataset);
        vtlComponents.add(vtlComponent);

        vtlComponent = new VtlComponent();
        vtlComponent.setName("ID_2");
        vtlComponent.setType(VtlType.STRING);
        vtlComponent.setDomainValue(VtlType.STRING.getDomainValue());
        vtlComponent.setDomainValueParent(VtlType.STRING.getDomainValueParent());
        vtlComponent.setVtlComponentRole(VtlComponentRole.IDENTIFIER);
        vtlComponent.setVtlDataset(vtlDataset);
        vtlComponents.add(vtlComponent);

        vtlComponent = new VtlComponent();
        vtlComponent.setName("ID_3");
        vtlComponent.setType(VtlType.STRING);
        vtlComponent.setDomainValue(VtlType.STRING.getDomainValue());
        vtlComponent.setDomainValueParent(VtlType.STRING.getDomainValueParent());
        vtlComponent.setVtlComponentRole(VtlComponentRole.IDENTIFIER);
        vtlComponent.setVtlDataset(vtlDataset);
        vtlComponents.add(vtlComponent);

        vtlComponent = new VtlComponent();
        vtlComponent.setName("ID_4");
        vtlComponent.setType(VtlType.STRING);
        vtlComponent.setDomainValue(VtlType.STRING.getDomainValue());
        vtlComponent.setDomainValueParent(VtlType.STRING.getDomainValueParent());
        vtlComponent.setVtlComponentRole(VtlComponentRole.IDENTIFIER);
        vtlComponent.setVtlDataset(vtlDataset);
        vtlComponents.add(vtlComponent);

        vtlComponent = new VtlComponent();
        vtlComponent.setName("ME_1");
        vtlComponent.setType(VtlType.NUMBER);
        vtlComponent.setDomainValue(VtlType.NUMBER.getDomainValue());
        vtlComponent.setDomainValueParent(VtlType.NUMBER.getDomainValueParent());
        vtlComponent.setVtlComponentRole(VtlComponentRole.MEASURE);
        vtlComponent.setVtlDataset(vtlDataset);
        vtlComponents.add(vtlComponent);

        vtlDataset14.addComponentsList(vtlComponents);
        datasetRepository.save(vtlDataset14);


        VtlDataset vtlDataset15 = new VtlDataset();
        vtlDataset15.setPersistent(true);
        vtlDataset15.setName("DS_15");
        vtlDataset15.setIsOnlyAScalar(false);
        vtlDataset15.setTransitory(false);
        vtlComponents = new ArrayList<>();

        vtlComponent = new VtlComponent();
        vtlComponent.setName("ID_1");
        vtlComponent.setType(VtlType.STRING);
        vtlComponent.setDomainValue(VtlType.STRING.getDomainValue());
        vtlComponent.setDomainValueParent(VtlType.STRING.getDomainValueParent());
        vtlComponent.setVtlComponentRole(VtlComponentRole.IDENTIFIER);
        vtlComponent.setVtlDataset(vtlDataset);
        vtlComponents.add(vtlComponent);

        vtlComponent = new VtlComponent();
        vtlComponent.setName("ID_2");
        vtlComponent.setType(VtlType.STRING);
        vtlComponent.setDomainValue(VtlType.STRING.getDomainValue());
        vtlComponent.setDomainValueParent(VtlType.STRING.getDomainValueParent());
        vtlComponent.setVtlComponentRole(VtlComponentRole.IDENTIFIER);
        vtlComponent.setVtlDataset(vtlDataset);
        vtlComponents.add(vtlComponent);

        vtlComponent = new VtlComponent();
        vtlComponent.setName("ID_3");
        vtlComponent.setType(VtlType.STRING);
        vtlComponent.setDomainValue(VtlType.STRING.getDomainValue());
        vtlComponent.setDomainValueParent(VtlType.STRING.getDomainValueParent());
        vtlComponent.setVtlComponentRole(VtlComponentRole.IDENTIFIER);
        vtlComponent.setVtlDataset(vtlDataset);
        vtlComponents.add(vtlComponent);

        vtlComponent = new VtlComponent();
        vtlComponent.setName("ID_4");
        vtlComponent.setType(VtlType.STRING);
        vtlComponent.setDomainValue(VtlType.STRING.getDomainValue());
        vtlComponent.setDomainValueParent(VtlType.STRING.getDomainValueParent());
        vtlComponent.setVtlComponentRole(VtlComponentRole.IDENTIFIER);
        vtlComponent.setVtlDataset(vtlDataset);
        vtlComponents.add(vtlComponent);

        vtlComponent = new VtlComponent();
        vtlComponent.setName("ME_1");
        vtlComponent.setType(VtlType.INTEGER);
        vtlComponent.setDomainValue(VtlType.INTEGER.getDomainValue());
        vtlComponent.setDomainValueParent(VtlType.INTEGER.getDomainValueParent());
        vtlComponent.setVtlComponentRole(VtlComponentRole.MEASURE);
        vtlComponent.setVtlDataset(vtlDataset);
        vtlComponents.add(vtlComponent);

        vtlDataset15.addComponentsList(vtlComponents);
        datasetRepository.save(vtlDataset15);


        VtlDataset vtlDataset16 = new VtlDataset();
        vtlDataset16.setPersistent(true);
        vtlDataset16.setName("DS_16");
        vtlDataset16.setIsOnlyAScalar(false);
        vtlDataset16.setTransitory(false);
        vtlComponents = new ArrayList<>();

        vtlComponent = new VtlComponent();
        vtlComponent.setName("ID_1");
        vtlComponent.setType(VtlType.INTEGER);
        vtlComponent.setDomainValue(VtlType.INTEGER.getDomainValue());
        vtlComponent.setDomainValueParent(VtlType.INTEGER.getDomainValueParent());
        vtlComponent.setVtlComponentRole(VtlComponentRole.IDENTIFIER);
        vtlComponent.setVtlDataset(vtlDataset);
        vtlComponents.add(vtlComponent);

        vtlComponent = new VtlComponent();
        vtlComponent.setName("ID_2");
        vtlComponent.setType(VtlType.STRING);
        vtlComponent.setDomainValue(VtlType.STRING.getDomainValue());
        vtlComponent.setDomainValueParent(VtlType.STRING.getDomainValueParent());
        vtlComponent.setVtlComponentRole(VtlComponentRole.IDENTIFIER);
        vtlComponent.setVtlDataset(vtlDataset);
        vtlComponents.add(vtlComponent);

        vtlComponent = new VtlComponent();
        vtlComponent.setName("ME_1");
        vtlComponent.setType(VtlType.INTEGER);
        vtlComponent.setDomainValue(VtlType.INTEGER.getDomainValue());
        vtlComponent.setDomainValueParent(VtlType.INTEGER.getDomainValueParent());
        vtlComponent.setVtlComponentRole(VtlComponentRole.MEASURE);
        vtlComponent.setVtlDataset(vtlDataset);
        vtlComponents.add(vtlComponent);

        vtlDataset16.addComponentsList(vtlComponents);
        datasetRepository.save(vtlDataset16);


        VtlDataset vtlDataset17 = new VtlDataset();
        vtlDataset17.setPersistent(true);
        vtlDataset17.setName("DS_17");
        vtlDataset17.setIsOnlyAScalar(false);
        vtlDataset17.setTransitory(false);
        vtlComponents = new ArrayList<>();

        vtlComponent = new VtlComponent();
        vtlComponent.setName("ID_1");
        vtlComponent.setType(VtlType.STRING);
        vtlComponent.setDomainValue(VtlType.STRING.getDomainValue());
        vtlComponent.setDomainValueParent(VtlType.STRING.getDomainValueParent());
        vtlComponent.setVtlComponentRole(VtlComponentRole.IDENTIFIER);
        vtlComponent.setVtlDataset(vtlDataset);
        vtlComponents.add(vtlComponent);

        vtlComponent = new VtlComponent();
        vtlComponent.setName("ID_2");
        vtlComponent.setType(VtlType.STRING);
        vtlComponent.setDomainValue(VtlType.STRING.getDomainValue());
        vtlComponent.setDomainValueParent(VtlType.STRING.getDomainValueParent());
        vtlComponent.setVtlComponentRole(VtlComponentRole.IDENTIFIER);
        vtlComponent.setVtlDataset(vtlDataset);
        vtlComponents.add(vtlComponent);

        vtlComponent = new VtlComponent();
        vtlComponent.setName("ID_3");
        vtlComponent.setType(VtlType.STRING);
        vtlComponent.setDomainValue(VtlType.STRING.getDomainValue());
        vtlComponent.setDomainValueParent(VtlType.STRING.getDomainValueParent());
        vtlComponent.setVtlComponentRole(VtlComponentRole.IDENTIFIER);
        vtlComponent.setVtlDataset(vtlDataset);
        vtlComponents.add(vtlComponent);

        vtlComponent = new VtlComponent();
        vtlComponent.setName("ID_4");
        vtlComponent.setType(VtlType.STRING);
        vtlComponent.setDomainValue(VtlType.STRING.getDomainValue());
        vtlComponent.setDomainValueParent(VtlType.STRING.getDomainValueParent());
        vtlComponent.setVtlComponentRole(VtlComponentRole.IDENTIFIER);
        vtlComponent.setVtlDataset(vtlDataset);
        vtlComponents.add(vtlComponent);

        vtlComponent = new VtlComponent();
        vtlComponent.setName("ME_1");
        vtlComponent.setType(VtlType.STRING);
        vtlComponent.setDomainValue(VtlType.STRING.getDomainValue());
        vtlComponent.setDomainValueParent(VtlType.STRING.getDomainValueParent());
        vtlComponent.setVtlComponentRole(VtlComponentRole.MEASURE);
        vtlComponent.setVtlDataset(vtlDataset);
        vtlComponents.add(vtlComponent);

        vtlDataset17.addComponentsList(vtlComponents);
        datasetRepository.save(vtlDataset17);


        VtlDataset vtlDataset18 = new VtlDataset();
        vtlDataset18.setPersistent(true);
        vtlDataset18.setName("DS_18");
        vtlDataset18.setIsOnlyAScalar(false);
        vtlDataset18.setTransitory(false);
        vtlComponents = new ArrayList<>();

        vtlComponent = new VtlComponent();
        vtlComponent.setName("ID_1");
        vtlComponent.setType(VtlType.INTEGER);
        vtlComponent.setDomainValue(VtlType.INTEGER.getDomainValue());
        vtlComponent.setDomainValueParent(VtlType.INTEGER.getDomainValueParent());
        vtlComponent.setVtlComponentRole(VtlComponentRole.IDENTIFIER);
        vtlComponent.setVtlDataset(vtlDataset);
        vtlComponents.add(vtlComponent);

        vtlComponent = new VtlComponent();
        vtlComponent.setName("ID_2");
        vtlComponent.setType(VtlType.STRING);
        vtlComponent.setDomainValue(VtlType.STRING.getDomainValue());
        vtlComponent.setDomainValueParent(VtlType.STRING.getDomainValueParent());
        vtlComponent.setVtlComponentRole(VtlComponentRole.IDENTIFIER);
        vtlComponent.setVtlDataset(vtlDataset);
        vtlComponents.add(vtlComponent);

        vtlComponent = new VtlComponent();
        vtlComponent.setName("ID_3");
        vtlComponent.setType(VtlType.STRING);
        vtlComponent.setDomainValue(VtlType.STRING.getDomainValue());
        vtlComponent.setDomainValueParent(VtlType.STRING.getDomainValueParent());
        vtlComponent.setVtlComponentRole(VtlComponentRole.IDENTIFIER);
        vtlComponent.setVtlDataset(vtlDataset);
        vtlComponents.add(vtlComponent);

        vtlComponent = new VtlComponent();
        vtlComponent.setName("ID_4");
        vtlComponent.setType(VtlType.STRING);
        vtlComponent.setDomainValue(VtlType.STRING.getDomainValue());
        vtlComponent.setDomainValueParent(VtlType.STRING.getDomainValueParent());
        vtlComponent.setVtlComponentRole(VtlComponentRole.IDENTIFIER);
        vtlComponent.setVtlDataset(vtlDataset);
        vtlComponents.add(vtlComponent);

        vtlComponent = new VtlComponent();
        vtlComponent.setName("ME_1");
        vtlComponent.setType(VtlType.INTEGER);
        vtlComponent.setDomainValue(VtlType.INTEGER.getDomainValue());
        vtlComponent.setDomainValueParent(VtlType.INTEGER.getDomainValueParent());
        vtlComponent.setVtlComponentRole(VtlComponentRole.MEASURE);
        vtlComponent.setVtlDataset(vtlDataset);
        vtlComponents.add(vtlComponent);

        vtlDataset18.addComponentsList(vtlComponents);
        datasetRepository.save(vtlDataset18);


        VtlDataset vtlDataset19 = new VtlDataset();
        vtlDataset19.setPersistent(true);
        vtlDataset19.setName("DS_19");
        vtlDataset19.setIsOnlyAScalar(false);
        vtlDataset19.setTransitory(false);
        vtlComponents = new ArrayList<>();

        vtlComponent = new VtlComponent();
        vtlComponent.setName("ID_1");
        vtlComponent.setType(VtlType.STRING);
        vtlComponent.setDomainValue(VtlType.STRING.getDomainValue());
        vtlComponent.setDomainValueParent(VtlType.STRING.getDomainValueParent());
        vtlComponent.setVtlComponentRole(VtlComponentRole.IDENTIFIER);
        vtlComponent.setVtlDataset(vtlDataset);
        vtlComponents.add(vtlComponent);

        vtlComponent = new VtlComponent();
        vtlComponent.setName("ID_2");
        vtlComponent.setType(VtlType.INTEGER);
        vtlComponent.setDomainValue(VtlType.INTEGER.getDomainValue());
        vtlComponent.setDomainValueParent(VtlType.INTEGER.getDomainValueParent());
        vtlComponent.setVtlComponentRole(VtlComponentRole.IDENTIFIER);
        vtlComponent.setVtlDataset(vtlDataset);
        vtlComponents.add(vtlComponent);

        vtlComponent = new VtlComponent();
        vtlComponent.setName("ID_3");
        vtlComponent.setType(VtlType.STRING);
        vtlComponent.setDomainValue(VtlType.STRING.getDomainValue());
        vtlComponent.setDomainValueParent(VtlType.STRING.getDomainValueParent());
        vtlComponent.setVtlComponentRole(VtlComponentRole.IDENTIFIER);
        vtlComponent.setVtlDataset(vtlDataset);
        vtlComponents.add(vtlComponent);

        vtlComponent = new VtlComponent();
        vtlComponent.setName("ID_4");
        vtlComponent.setType(VtlType.INTEGER);
        vtlComponent.setDomainValue(VtlType.INTEGER.getDomainValue());
        vtlComponent.setDomainValueParent(VtlType.INTEGER.getDomainValueParent());
        vtlComponent.setVtlComponentRole(VtlComponentRole.IDENTIFIER);
        vtlComponent.setVtlDataset(vtlDataset);
        vtlComponents.add(vtlComponent);

        vtlComponent = new VtlComponent();
        vtlComponent.setName("ME_1");
        vtlComponent.setType(VtlType.BOOLEAN);
        vtlComponent.setDomainValue(VtlType.BOOLEAN.getDomainValue());
        vtlComponent.setDomainValueParent(VtlType.BOOLEAN.getDomainValueParent());
        vtlComponent.setVtlComponentRole(VtlComponentRole.MEASURE);
        vtlComponent.setVtlDataset(vtlDataset);
        vtlComponents.add(vtlComponent);

        vtlDataset19.addComponentsList(vtlComponents);
        datasetRepository.save(vtlDataset19);


        VtlDataset vtlDataset20 = new VtlDataset();
        vtlDataset20.setPersistent(true);
        vtlDataset20.setName("DS_20");
        vtlDataset20.setIsOnlyAScalar(false);
        vtlDataset20.setTransitory(false);
        vtlComponents = new ArrayList<>();

        vtlComponent = new VtlComponent();
        vtlComponent.setName("ID_1");
        vtlComponent.setType(VtlType.STRING);
        vtlComponent.setDomainValue(VtlType.STRING.getDomainValue());
        vtlComponent.setDomainValueParent(VtlType.STRING.getDomainValueParent());
        vtlComponent.setVtlComponentRole(VtlComponentRole.IDENTIFIER);
        vtlComponent.setVtlDataset(vtlDataset);
        vtlComponents.add(vtlComponent);

        vtlComponent = new VtlComponent();
        vtlComponent.setName("ID_2");
        vtlComponent.setType(VtlType.INTEGER);
        vtlComponent.setDomainValue(VtlType.INTEGER.getDomainValue());
        vtlComponent.setDomainValueParent(VtlType.INTEGER.getDomainValueParent());
        vtlComponent.setVtlComponentRole(VtlComponentRole.IDENTIFIER);
        vtlComponent.setVtlDataset(vtlDataset);
        vtlComponents.add(vtlComponent);

        vtlComponent = new VtlComponent();
        vtlComponent.setName("ID_3");
        vtlComponent.setType(VtlType.STRING);
        vtlComponent.setDomainValue(VtlType.STRING.getDomainValue());
        vtlComponent.setDomainValueParent(VtlType.STRING.getDomainValueParent());
        vtlComponent.setVtlComponentRole(VtlComponentRole.IDENTIFIER);
        vtlComponent.setVtlDataset(vtlDataset);
        vtlComponents.add(vtlComponent);

        vtlComponent = new VtlComponent();
        vtlComponent.setName("ID_4");
        vtlComponent.setType(VtlType.INTEGER);
        vtlComponent.setDomainValue(VtlType.INTEGER.getDomainValue());
        vtlComponent.setDomainValueParent(VtlType.INTEGER.getDomainValueParent());
        vtlComponent.setVtlComponentRole(VtlComponentRole.IDENTIFIER);
        vtlComponent.setVtlDataset(vtlDataset);
        vtlComponents.add(vtlComponent);

        vtlComponent = new VtlComponent();
        vtlComponent.setName("ME_1");
        vtlComponent.setType(VtlType.BOOLEAN);
        vtlComponent.setDomainValue(VtlType.BOOLEAN.getDomainValue());
        vtlComponent.setDomainValueParent(VtlType.BOOLEAN.getDomainValueParent());
        vtlComponent.setVtlComponentRole(VtlComponentRole.MEASURE);
        vtlComponent.setVtlDataset(vtlDataset);
        vtlComponents.add(vtlComponent);

        vtlDataset20.addComponentsList(vtlComponents);
        datasetRepository.save(vtlDataset20);


        VtlDataset vtlDataset21 = new VtlDataset();
        vtlDataset21.setPersistent(true);
        vtlDataset21.setName("DS_21");
        vtlDataset21.setIsOnlyAScalar(false);
        vtlDataset21.setTransitory(false);
        vtlComponents = new ArrayList<>();

        vtlComponent = new VtlComponent();
        vtlComponent.setName("ID_1");
        vtlComponent.setType(VtlType.STRING);
        vtlComponent.setDomainValue(VtlType.STRING.getDomainValue());
        vtlComponent.setDomainValueParent(VtlType.STRING.getDomainValueParent());
        vtlComponent.setVtlComponentRole(VtlComponentRole.IDENTIFIER);
        vtlComponent.setVtlDataset(vtlDataset);
        vtlComponents.add(vtlComponent);

        vtlComponent = new VtlComponent();
        vtlComponent.setName("ID_2");
        vtlComponent.setType(VtlType.TIME);
        vtlComponent.setDomainValue(VtlType.TIME.getDomainValue());
        vtlComponent.setDomainValueParent(VtlType.TIME.getDomainValueParent());
        vtlComponent.setVtlComponentRole(VtlComponentRole.IDENTIFIER);
        vtlComponent.setVtlDataset(vtlDataset);
        vtlComponents.add(vtlComponent);

        vtlComponent = new VtlComponent();
        vtlComponent.setName("ME_1");
        vtlComponent.setType(VtlType.STRING);
        vtlComponent.setDomainValue(VtlType.STRING.getDomainValue());
        vtlComponent.setDomainValueParent(VtlType.STRING.getDomainValueParent());
        vtlComponent.setVtlComponentRole(VtlComponentRole.MEASURE);
        vtlComponent.setVtlDataset(vtlDataset);
        vtlComponents.add(vtlComponent);

        vtlDataset21.addComponentsList(vtlComponents);
        datasetRepository.save(vtlDataset21);


        VtlDataset vtlDataset22 = new VtlDataset();
        vtlDataset22.setPersistent(true);
        vtlDataset22.setName("DS_22");
        vtlDataset22.setIsOnlyAScalar(false);
        vtlDataset22.setTransitory(false);
        vtlComponents = new ArrayList<>();

        vtlComponent = new VtlComponent();
        vtlComponent.setName("ID_1");
        vtlComponent.setType(VtlType.STRING);
        vtlComponent.setDomainValue(VtlType.STRING.getDomainValue());
        vtlComponent.setDomainValueParent(VtlType.STRING.getDomainValueParent());
        vtlComponent.setVtlComponentRole(VtlComponentRole.IDENTIFIER);
        vtlComponent.setVtlDataset(vtlDataset);
        vtlComponents.add(vtlComponent);

        vtlComponent = new VtlComponent();
        vtlComponent.setName("ID_2");
        vtlComponent.setType(VtlType.TIME);
        vtlComponent.setDomainValue(VtlType.TIME.getDomainValue());
        vtlComponent.setDomainValueParent(VtlType.TIME.getDomainValueParent());
        vtlComponent.setVtlComponentRole(VtlComponentRole.IDENTIFIER);
        vtlComponent.setVtlDataset(vtlDataset);
        vtlComponents.add(vtlComponent);

        vtlComponent = new VtlComponent();
        vtlComponent.setName("ME_1");
        vtlComponent.setType(VtlType.STRING);
        vtlComponent.setDomainValue(VtlType.STRING.getDomainValue());
        vtlComponent.setDomainValueParent(VtlType.STRING.getDomainValueParent());
        vtlComponent.setVtlComponentRole(VtlComponentRole.MEASURE);
        vtlComponent.setVtlDataset(vtlDataset);
        vtlComponents.add(vtlComponent);

        vtlDataset22.addComponentsList(vtlComponents);
        datasetRepository.save(vtlDataset22);


        VtlDataset vtlDataset23 = new VtlDataset();
        vtlDataset23.setPersistent(true);
        vtlDataset23.setName("DS_23");
        vtlDataset23.setIsOnlyAScalar(false);
        vtlDataset23.setTransitory(false);
        vtlComponents = new ArrayList<>();

        vtlComponent = new VtlComponent();
        vtlComponent.setName("ID_1");
        vtlComponent.setType(VtlType.STRING);
        vtlComponent.setDomainValue(VtlType.STRING.getDomainValue());
        vtlComponent.setDomainValueParent(VtlType.STRING.getDomainValueParent());
        vtlComponent.setVtlComponentRole(VtlComponentRole.IDENTIFIER);
        vtlComponent.setVtlDataset(vtlDataset);
        vtlComponents.add(vtlComponent);

        vtlComponent = new VtlComponent();
        vtlComponent.setName("ID_2");
        vtlComponent.setType(VtlType.TIME);
        vtlComponent.setDomainValue(VtlType.TIME.getDomainValue());
        vtlComponent.setDomainValueParent(VtlType.TIME.getDomainValueParent());
        vtlComponent.setVtlComponentRole(VtlComponentRole.IDENTIFIER);
        vtlComponent.setVtlDataset(vtlDataset);
        vtlComponents.add(vtlComponent);

        vtlComponent = new VtlComponent();
        vtlComponent.setName("ME_1");
        vtlComponent.setType(VtlType.INTEGER);
        vtlComponent.setDomainValue(VtlType.INTEGER.getDomainValue());
        vtlComponent.setDomainValueParent(VtlType.INTEGER.getDomainValueParent());
        vtlComponent.setVtlComponentRole(VtlComponentRole.MEASURE);
        vtlComponent.setVtlDataset(vtlDataset);
        vtlComponents.add(vtlComponent);

        vtlDataset23.addComponentsList(vtlComponents);
        datasetRepository.save(vtlDataset23);


        VtlDataset vtlDataset24 = new VtlDataset();
        vtlDataset24.setPersistent(true);
        vtlDataset24.setName("DS_24");
        vtlDataset24.setIsOnlyAScalar(false);
        vtlDataset24.setTransitory(false);
        vtlComponents = new ArrayList<>();

        vtlComponent = new VtlComponent();
        vtlComponent.setName("ID_1");
        vtlComponent.setType(VtlType.STRING);
        vtlComponent.setDomainValue(VtlType.STRING.getDomainValue());
        vtlComponent.setDomainValueParent(VtlType.STRING.getDomainValueParent());
        vtlComponent.setVtlComponentRole(VtlComponentRole.IDENTIFIER);
        vtlComponent.setVtlDataset(vtlDataset);
        vtlComponents.add(vtlComponent);

        vtlComponent = new VtlComponent();
        vtlComponent.setName("ID_2");
        vtlComponent.setType(VtlType.TIME);
        vtlComponent.setDomainValue(VtlType.TIME.getDomainValue());
        vtlComponent.setDomainValueParent(VtlType.TIME.getDomainValueParent());
        vtlComponent.setVtlComponentRole(VtlComponentRole.IDENTIFIER);
        vtlComponent.setVtlDataset(vtlDataset);
        vtlComponents.add(vtlComponent);

        vtlComponent = new VtlComponent();
        vtlComponent.setName("ME_1");
        vtlComponent.setType(VtlType.INTEGER);
        vtlComponent.setDomainValue(VtlType.INTEGER.getDomainValue());
        vtlComponent.setDomainValueParent(VtlType.INTEGER.getDomainValueParent());
        vtlComponent.setVtlComponentRole(VtlComponentRole.MEASURE);
        vtlComponent.setVtlDataset(vtlDataset);
        vtlComponents.add(vtlComponent);

        vtlDataset24.addComponentsList(vtlComponents);
        datasetRepository.save(vtlDataset24);


        VtlDataset vtlDataset25 = new VtlDataset();
        vtlDataset25.setPersistent(true);
        vtlDataset25.setName("DS_25");
        vtlDataset25.setIsOnlyAScalar(false);
        vtlDataset25.setTransitory(false);
        vtlComponents = new ArrayList<>();

        vtlComponent = new VtlComponent();
        vtlComponent.setName("ID_1");
        vtlComponent.setType(VtlType.INTEGER);
        vtlComponent.setDomainValue(VtlType.INTEGER.getDomainValue());
        vtlComponent.setDomainValueParent(VtlType.INTEGER.getDomainValueParent());
        vtlComponent.setVtlComponentRole(VtlComponentRole.IDENTIFIER);
        vtlComponent.setVtlDataset(vtlDataset);
        vtlComponents.add(vtlComponent);

        vtlComponent = new VtlComponent();
        vtlComponent.setName("ID_2");
        vtlComponent.setType(VtlType.STRING);
        vtlComponent.setDomainValue(VtlType.STRING.getDomainValue());
        vtlComponent.setDomainValueParent(VtlType.STRING.getDomainValueParent());
        vtlComponent.setVtlComponentRole(VtlComponentRole.IDENTIFIER);
        vtlComponent.setVtlDataset(vtlDataset);
        vtlComponents.add(vtlComponent);

        vtlComponent = new VtlComponent();
        vtlComponent.setName("ID_3");
        vtlComponent.setType(VtlType.STRING);
        vtlComponent.setDomainValue(VtlType.STRING.getDomainValue());
        vtlComponent.setDomainValueParent(VtlType.STRING.getDomainValueParent());
        vtlComponent.setVtlComponentRole(VtlComponentRole.IDENTIFIER);
        vtlComponent.setVtlDataset(vtlDataset);
        vtlComponents.add(vtlComponent);

        vtlComponent = new VtlComponent();
        vtlComponent.setName("ID_4");
        vtlComponent.setType(VtlType.STRING);
        vtlComponent.setDomainValue(VtlType.STRING.getDomainValue());
        vtlComponent.setDomainValueParent(VtlType.STRING.getDomainValueParent());
        vtlComponent.setVtlComponentRole(VtlComponentRole.IDENTIFIER);
        vtlComponent.setVtlDataset(vtlDataset);
        vtlComponents.add(vtlComponent);

        vtlComponent = new VtlComponent();
        vtlComponent.setName("ME_1");
        vtlComponent.setType(VtlType.INTEGER);
        vtlComponent.setDomainValue(VtlType.INTEGER.getDomainValue());
        vtlComponent.setDomainValueParent(VtlType.INTEGER.getDomainValueParent());
        vtlComponent.setVtlComponentRole(VtlComponentRole.MEASURE);
        vtlComponent.setVtlDataset(vtlDataset);
        vtlComponents.add(vtlComponent);

        vtlDataset25.addComponentsList(vtlComponents);
        datasetRepository.save(vtlDataset25);


        VtlDataset vtlDataset26 = new VtlDataset();
        vtlDataset26.setPersistent(true);
        vtlDataset26.setName("DS_26");
        vtlDataset26.setIsOnlyAScalar(false);
        vtlDataset26.setTransitory(false);
        vtlComponents = new ArrayList<>();

        vtlComponent = new VtlComponent();
        vtlComponent.setName("ID_1");
        vtlComponent.setType(VtlType.STRING);
        vtlComponent.setDomainValue(VtlType.STRING.getDomainValue());
        vtlComponent.setDomainValueParent(VtlType.STRING.getDomainValueParent());
        vtlComponent.setVtlComponentRole(VtlComponentRole.IDENTIFIER);
        vtlComponent.setVtlDataset(vtlDataset);
        vtlComponents.add(vtlComponent);

        vtlComponent = new VtlComponent();
        vtlComponent.setName("ID_2");
        vtlComponent.setType(VtlType.STRING);
        vtlComponent.setDomainValue(VtlType.STRING.getDomainValue());
        vtlComponent.setDomainValueParent(VtlType.STRING.getDomainValueParent());
        vtlComponent.setVtlComponentRole(VtlComponentRole.IDENTIFIER);
        vtlComponent.setVtlDataset(vtlDataset);
        vtlComponents.add(vtlComponent);

        vtlComponent = new VtlComponent();
        vtlComponent.setName("ID_3");
        vtlComponent.setType(VtlType.INTEGER);
        vtlComponent.setDomainValue(VtlType.INTEGER.getDomainValue());
        vtlComponent.setDomainValueParent(VtlType.INTEGER.getDomainValueParent());
        vtlComponent.setVtlComponentRole(VtlComponentRole.IDENTIFIER);
        vtlComponent.setVtlDataset(vtlDataset);
        vtlComponents.add(vtlComponent);

        vtlComponent = new VtlComponent();
        vtlComponent.setName("ME_1");
        vtlComponent.setType(VtlType.INTEGER);
        vtlComponent.setDomainValue(VtlType.INTEGER.getDomainValue());
        vtlComponent.setDomainValueParent(VtlType.INTEGER.getDomainValueParent());
        vtlComponent.setVtlComponentRole(VtlComponentRole.MEASURE);
        vtlComponent.setVtlDataset(vtlDataset);
        vtlComponents.add(vtlComponent);

        vtlDataset26.addComponentsList(vtlComponents);
        datasetRepository.save(vtlDataset26);


        VtlDataset vtlDataset27 = new VtlDataset();
        vtlDataset27.setPersistent(true);
        vtlDataset27.setName("DS_27");
        vtlDataset27.setIsOnlyAScalar(false);
        vtlDataset27.setTransitory(false);
        vtlComponents = new ArrayList<>();

        vtlComponent = new VtlComponent();
        vtlComponent.setName("ID_1");
        vtlComponent.setType(VtlType.STRING);
        vtlComponent.setDomainValue(VtlType.STRING.getDomainValue());
        vtlComponent.setDomainValueParent(VtlType.STRING.getDomainValueParent());
        vtlComponent.setVtlComponentRole(VtlComponentRole.IDENTIFIER);
        vtlComponent.setVtlDataset(vtlDataset);
        vtlComponents.add(vtlComponent);

        vtlComponent = new VtlComponent();
        vtlComponent.setName("ID_2");
        vtlComponent.setType(VtlType.STRING);
        vtlComponent.setDomainValue(VtlType.STRING.getDomainValue());
        vtlComponent.setDomainValueParent(VtlType.STRING.getDomainValueParent());
        vtlComponent.setVtlComponentRole(VtlComponentRole.IDENTIFIER);
        vtlComponent.setVtlDataset(vtlDataset);
        vtlComponents.add(vtlComponent);

        vtlComponent = new VtlComponent();
        vtlComponent.setName("ID_3");
        vtlComponent.setType(VtlType.INTEGER);
        vtlComponent.setDomainValue(VtlType.INTEGER.getDomainValue());
        vtlComponent.setDomainValueParent(VtlType.INTEGER.getDomainValueParent());
        vtlComponent.setVtlComponentRole(VtlComponentRole.IDENTIFIER);
        vtlComponent.setVtlDataset(vtlDataset);
        vtlComponents.add(vtlComponent);

        vtlComponent = new VtlComponent();
        vtlComponent.setName("ME_1");
        vtlComponent.setType(VtlType.INTEGER);
        vtlComponent.setDomainValue(VtlType.INTEGER.getDomainValue());
        vtlComponent.setDomainValueParent(VtlType.INTEGER.getDomainValueParent());
        vtlComponent.setVtlComponentRole(VtlComponentRole.MEASURE);
        vtlComponent.setVtlDataset(vtlDataset);
        vtlComponents.add(vtlComponent);

        vtlDataset27.addComponentsList(vtlComponents);
        datasetRepository.save(vtlDataset27);


        VtlDataset vtlDataset28 = new VtlDataset();
        vtlDataset28.setPersistent(true);
        vtlDataset28.setName("DS_28");
        vtlDataset28.setIsOnlyAScalar(false);
        vtlDataset28.setTransitory(false);
        vtlComponents = new ArrayList<>();

        vtlComponent = new VtlComponent();
        vtlComponent.setName("ID_1");
        vtlComponent.setType(VtlType.INTEGER);
        vtlComponent.setDomainValue(VtlType.INTEGER.getDomainValue());
        vtlComponent.setDomainValueParent(VtlType.INTEGER.getDomainValueParent());
        vtlComponent.setVtlComponentRole(VtlComponentRole.IDENTIFIER);
        vtlComponent.setVtlDataset(vtlDataset);
        vtlComponents.add(vtlComponent);

        vtlComponent = new VtlComponent();
        vtlComponent.setName("ID_2");
        vtlComponent.setType(VtlType.STRING);
        vtlComponent.setDomainValue(VtlType.STRING.getDomainValue());
        vtlComponent.setDomainValueParent(VtlType.STRING.getDomainValueParent());
        vtlComponent.setVtlComponentRole(VtlComponentRole.IDENTIFIER);
        vtlComponent.setVtlDataset(vtlDataset);
        vtlComponents.add(vtlComponent);

        vtlComponent = new VtlComponent();
        vtlComponent.setName("ID_3");
        vtlComponent.setType(VtlType.STRING);
        vtlComponent.setDomainValue(VtlType.STRING.getDomainValue());
        vtlComponent.setDomainValueParent(VtlType.STRING.getDomainValueParent());
        vtlComponent.setVtlComponentRole(VtlComponentRole.IDENTIFIER);
        vtlComponent.setVtlDataset(vtlDataset);
        vtlComponents.add(vtlComponent);

        vtlComponent = new VtlComponent();
        vtlComponent.setName("ID_4");
        vtlComponent.setType(VtlType.STRING);
        vtlComponent.setDomainValue(VtlType.STRING.getDomainValue());
        vtlComponent.setDomainValueParent(VtlType.STRING.getDomainValueParent());
        vtlComponent.setVtlComponentRole(VtlComponentRole.IDENTIFIER);
        vtlComponent.setVtlDataset(vtlDataset);
        vtlComponents.add(vtlComponent);

        vtlComponent = new VtlComponent();
        vtlComponent.setName("ME_1");
        vtlComponent.setType(VtlType.NUMBER);
        vtlComponent.setDomainValue(VtlType.NUMBER.getDomainValue());
        vtlComponent.setDomainValueParent(VtlType.NUMBER.getDomainValueParent());
        vtlComponent.setVtlComponentRole(VtlComponentRole.MEASURE);
        vtlComponent.setVtlDataset(vtlDataset);
        vtlComponents.add(vtlComponent);

        vtlDataset28.addComponentsList(vtlComponents);
        datasetRepository.save(vtlDataset28);


        VtlDataset vtlDataset29 = new VtlDataset();
        vtlDataset29.setPersistent(true);
        vtlDataset29.setName("DS_29");
        vtlDataset29.setIsOnlyAScalar(false);
        vtlDataset29.setTransitory(false);
        vtlComponents = new ArrayList<>();

        vtlComponent = new VtlComponent();
        vtlComponent.setName("ID_1");
        vtlComponent.setType(VtlType.INTEGER);
        vtlComponent.setDomainValue(VtlType.INTEGER.getDomainValue());
        vtlComponent.setDomainValueParent(VtlType.INTEGER.getDomainValueParent());
        vtlComponent.setVtlComponentRole(VtlComponentRole.IDENTIFIER);
        vtlComponent.setVtlDataset(vtlDataset);
        vtlComponents.add(vtlComponent);

        vtlComponent = new VtlComponent();
        vtlComponent.setName("ID_2");
        vtlComponent.setType(VtlType.STRING);
        vtlComponent.setDomainValue(VtlType.STRING.getDomainValue());
        vtlComponent.setDomainValueParent(VtlType.STRING.getDomainValueParent());
        vtlComponent.setVtlComponentRole(VtlComponentRole.IDENTIFIER);
        vtlComponent.setVtlDataset(vtlDataset);
        vtlComponents.add(vtlComponent);

        vtlComponent = new VtlComponent();
        vtlComponent.setName("ID_3");
        vtlComponent.setType(VtlType.STRING);
        vtlComponent.setDomainValue(VtlType.STRING.getDomainValue());
        vtlComponent.setDomainValueParent(VtlType.STRING.getDomainValueParent());
        vtlComponent.setVtlComponentRole(VtlComponentRole.IDENTIFIER);
        vtlComponent.setVtlDataset(vtlDataset);
        vtlComponents.add(vtlComponent);

        vtlComponent = new VtlComponent();
        vtlComponent.setName("ME_1");
        vtlComponent.setType(VtlType.INTEGER);
        vtlComponent.setDomainValue(VtlType.INTEGER.getDomainValue());
        vtlComponent.setDomainValueParent(VtlType.INTEGER.getDomainValueParent());
        vtlComponent.setVtlComponentRole(VtlComponentRole.MEASURE);
        vtlComponent.setVtlDataset(vtlDataset);
        vtlComponents.add(vtlComponent);

        vtlComponent = new VtlComponent();
        vtlComponent.setName("AT_1");
        vtlComponent.setType(VtlType.STRING);
        vtlComponent.setDomainValue(VtlType.STRING.getDomainValue());
        vtlComponent.setDomainValueParent(VtlType.STRING.getDomainValueParent());
        vtlComponent.setVtlComponentRole(VtlComponentRole.VIRAL);
        vtlComponent.setVtlDataset(vtlDataset);
        vtlComponents.add(vtlComponent);

        vtlDataset29.addComponentsList(vtlComponents);
        datasetRepository.save(vtlDataset29);


        VtlDataset vtlDataset30 = new VtlDataset();
        vtlDataset30.setPersistent(true);
        vtlDataset30.setName("DS_30");
        vtlDataset30.setIsOnlyAScalar(false);
        vtlDataset30.setTransitory(false);
        vtlComponents = new ArrayList<>();

        vtlComponent = new VtlComponent();
        vtlComponent.setName("ID_1");
        vtlComponent.setType(VtlType.INTEGER);
        vtlComponent.setDomainValue(VtlType.INTEGER.getDomainValue());
        vtlComponent.setDomainValueParent(VtlType.INTEGER.getDomainValueParent());
        vtlComponent.setVtlComponentRole(VtlComponentRole.IDENTIFIER);
        vtlComponent.setVtlDataset(vtlDataset);
        vtlComponents.add(vtlComponent);

        vtlComponent = new VtlComponent();
        vtlComponent.setName("ID_2");
        vtlComponent.setType(VtlType.STRING);
        vtlComponent.setDomainValue(VtlType.STRING.getDomainValue());
        vtlComponent.setDomainValueParent(VtlType.STRING.getDomainValueParent());
        vtlComponent.setVtlComponentRole(VtlComponentRole.IDENTIFIER);
        vtlComponent.setVtlDataset(vtlDataset);
        vtlComponents.add(vtlComponent);

        vtlComponent = new VtlComponent();
        vtlComponent.setName("ID_3");
        vtlComponent.setType(VtlType.STRING);
        vtlComponent.setDomainValue(VtlType.STRING.getDomainValue());
        vtlComponent.setDomainValueParent(VtlType.STRING.getDomainValueParent());
        vtlComponent.setVtlComponentRole(VtlComponentRole.IDENTIFIER);
        vtlComponent.setVtlDataset(vtlDataset);
        vtlComponents.add(vtlComponent);

        vtlComponent = new VtlComponent();
        vtlComponent.setName("ME_1");
        vtlComponent.setType(VtlType.INTEGER);
        vtlComponent.setDomainValue(VtlType.INTEGER.getDomainValue());
        vtlComponent.setDomainValueParent(VtlType.INTEGER.getDomainValueParent());
        vtlComponent.setVtlComponentRole(VtlComponentRole.MEASURE);
        vtlComponent.setVtlDataset(vtlDataset);
        vtlComponents.add(vtlComponent);

        vtlDataset30.addComponentsList(vtlComponents);
        datasetRepository.save(vtlDataset30);


        VtlDataset vtlDataset31 = new VtlDataset();
        vtlDataset31.setPersistent(true);
        vtlDataset31.setName("DS_31");
        vtlDataset31.setIsOnlyAScalar(false);
        vtlDataset31.setTransitory(false);
        vtlComponents = new ArrayList<>();

        vtlComponent = new VtlComponent();
        vtlComponent.setName("ID_1");
        vtlComponent.setType(VtlType.INTEGER);
        vtlComponent.setDomainValue(VtlType.INTEGER.getDomainValue());
        vtlComponent.setDomainValueParent(VtlType.INTEGER.getDomainValueParent());
        vtlComponent.setVtlComponentRole(VtlComponentRole.IDENTIFIER);
        vtlComponent.setVtlDataset(vtlDataset);
        vtlComponents.add(vtlComponent);

        vtlComponent = new VtlComponent();
        vtlComponent.setName("ID_2");
        vtlComponent.setType(VtlType.STRING);
        vtlComponent.setDomainValue(VtlType.STRING.getDomainValue());
        vtlComponent.setDomainValueParent(VtlType.STRING.getDomainValueParent());
        vtlComponent.setVtlComponentRole(VtlComponentRole.IDENTIFIER);
        vtlComponent.setVtlDataset(vtlDataset);
        vtlComponents.add(vtlComponent);

        vtlComponent = new VtlComponent();
        vtlComponent.setName("ID_3");
        vtlComponent.setType(VtlType.STRING);
        vtlComponent.setDomainValue(VtlType.STRING.getDomainValue());
        vtlComponent.setDomainValueParent(VtlType.STRING.getDomainValueParent());
        vtlComponent.setVtlComponentRole(VtlComponentRole.IDENTIFIER);
        vtlComponent.setVtlDataset(vtlDataset);
        vtlComponents.add(vtlComponent);

        vtlComponent = new VtlComponent();
        vtlComponent.setName("ME_1");
        vtlComponent.setType(VtlType.INTEGER);
        vtlComponent.setDomainValue(VtlType.INTEGER.getDomainValue());
        vtlComponent.setDomainValueParent(VtlType.INTEGER.getDomainValueParent());
        vtlComponent.setVtlComponentRole(VtlComponentRole.MEASURE);
        vtlComponent.setVtlDataset(vtlDataset);
        vtlComponents.add(vtlComponent);

        vtlComponent = new VtlComponent();
        vtlComponent.setName("ME_2");
        vtlComponent.setType(VtlType.INTEGER);
        vtlComponent.setDomainValue(VtlType.INTEGER.getDomainValue());
        vtlComponent.setDomainValueParent(VtlType.INTEGER.getDomainValueParent());
        vtlComponent.setVtlComponentRole(VtlComponentRole.MEASURE);
        vtlComponent.setVtlDataset(vtlDataset);
        vtlComponents.add(vtlComponent);

        vtlComponent = new VtlComponent();
        vtlComponent.setName("AT_1");
        vtlComponent.setType(VtlType.STRING);
        vtlComponent.setDomainValue(VtlType.STRING.getDomainValue());
        vtlComponent.setDomainValueParent(VtlType.STRING.getDomainValueParent());
        vtlComponent.setVtlComponentRole(VtlComponentRole.VIRAL);
        vtlComponent.setVtlDataset(vtlDataset);
        vtlComponents.add(vtlComponent);

        vtlDataset31.addComponentsList(vtlComponents);
        datasetRepository.save(vtlDataset31);


        VtlDataset vtlDataset32 = new VtlDataset();
        vtlDataset32.setPersistent(true);
        vtlDataset32.setName("DS_32");
        vtlDataset32.setIsOnlyAScalar(false);
        vtlDataset32.setTransitory(false);
        vtlComponents = new ArrayList<>();

        vtlComponent = new VtlComponent();
        vtlComponent.setName("ID_1");
        vtlComponent.setType(VtlType.INTEGER);
        vtlComponent.setDomainValue(VtlType.INTEGER.getDomainValue());
        vtlComponent.setDomainValueParent(VtlType.INTEGER.getDomainValueParent());
        vtlComponent.setVtlComponentRole(VtlComponentRole.IDENTIFIER);
        vtlComponent.setVtlDataset(vtlDataset);
        vtlComponents.add(vtlComponent);

        vtlComponent = new VtlComponent();
        vtlComponent.setName("ID_2");
        vtlComponent.setType(VtlType.STRING);
        vtlComponent.setDomainValue(VtlType.STRING.getDomainValue());
        vtlComponent.setDomainValueParent(VtlType.STRING.getDomainValueParent());
        vtlComponent.setVtlComponentRole(VtlComponentRole.IDENTIFIER);
        vtlComponent.setVtlDataset(vtlDataset);
        vtlComponents.add(vtlComponent);

        vtlComponent = new VtlComponent();
        vtlComponent.setName("ME_1");
        vtlComponent.setType(VtlType.INTEGER);
        vtlComponent.setDomainValue(VtlType.INTEGER.getDomainValue());
        vtlComponent.setDomainValueParent(VtlType.INTEGER.getDomainValueParent());
        vtlComponent.setVtlComponentRole(VtlComponentRole.MEASURE);
        vtlComponent.setVtlDataset(vtlDataset);
        vtlComponents.add(vtlComponent);

        vtlComponent = new VtlComponent();
        vtlComponent.setName("AT_1");
        vtlComponent.setType(VtlType.STRING);
        vtlComponent.setDomainValue(VtlType.STRING.getDomainValue());
        vtlComponent.setDomainValueParent(VtlType.STRING.getDomainValueParent());
        vtlComponent.setVtlComponentRole(VtlComponentRole.VIRAL);
        vtlComponent.setVtlDataset(vtlDataset);
        vtlComponents.add(vtlComponent);

        vtlDataset32.addComponentsList(vtlComponents);
        datasetRepository.save(vtlDataset32);


        VtlDataset vtlDataset33 = new VtlDataset();
        vtlDataset33.setPersistent(true);
        vtlDataset33.setName("DS_33");
        vtlDataset33.setIsOnlyAScalar(false);
        vtlDataset33.setTransitory(false);
        vtlComponents = new ArrayList<>();

        vtlComponent = new VtlComponent();
        vtlComponent.setName("ID_1");
        vtlComponent.setType(VtlType.INTEGER);
        vtlComponent.setDomainValue(VtlType.INTEGER.getDomainValue());
        vtlComponent.setDomainValueParent(VtlType.INTEGER.getDomainValueParent());
        vtlComponent.setVtlComponentRole(VtlComponentRole.IDENTIFIER);
        vtlComponent.setVtlDataset(vtlDataset);
        vtlComponents.add(vtlComponent);

        vtlComponent = new VtlComponent();
        vtlComponent.setName("ID_2");
        vtlComponent.setType(VtlType.STRING);
        vtlComponent.setDomainValue(VtlType.STRING.getDomainValue());
        vtlComponent.setDomainValueParent(VtlType.STRING.getDomainValueParent());
        vtlComponent.setVtlComponentRole(VtlComponentRole.IDENTIFIER);
        vtlComponent.setVtlDataset(vtlDataset);
        vtlComponents.add(vtlComponent);

        vtlComponent = new VtlComponent();
        vtlComponent.setName("ID_3");
        vtlComponent.setType(VtlType.STRING);
        vtlComponent.setDomainValue(VtlType.STRING.getDomainValue());
        vtlComponent.setDomainValueParent(VtlType.STRING.getDomainValueParent());
        vtlComponent.setVtlComponentRole(VtlComponentRole.IDENTIFIER);
        vtlComponent.setVtlDataset(vtlDataset);
        vtlComponents.add(vtlComponent);

        vtlComponent = new VtlComponent();
        vtlComponent.setName("ME_1");
        vtlComponent.setType(VtlType.STRING);
        vtlComponent.setDomainValue(VtlType.STRING.getDomainValue());
        vtlComponent.setDomainValueParent(VtlType.STRING.getDomainValueParent());
        vtlComponent.setVtlComponentRole(VtlComponentRole.MEASURE);
        vtlComponent.setVtlDataset(vtlDataset);
        vtlComponents.add(vtlComponent);

        vtlDataset33.addComponentsList(vtlComponents);
        datasetRepository.save(vtlDataset33);


        VtlDataset vtlDataset34 = new VtlDataset();
        vtlDataset34.setPersistent(true);
        vtlDataset34.setName("DS_34");
        vtlDataset34.setIsOnlyAScalar(false);
        vtlDataset34.setTransitory(false);
        vtlComponents = new ArrayList<>();

        vtlComponent = new VtlComponent();
        vtlComponent.setName("ID_1");
        vtlComponent.setType(VtlType.STRING);
        vtlComponent.setDomainValue(VtlType.STRING.getDomainValue());
        vtlComponent.setDomainValueParent(VtlType.STRING.getDomainValueParent());
        vtlComponent.setVtlComponentRole(VtlComponentRole.IDENTIFIER);
        vtlComponent.setVtlDataset(vtlDataset);
        vtlComponents.add(vtlComponent);

        vtlComponent = new VtlComponent();
        vtlComponent.setName("ID_2");
        vtlComponent.setType(VtlType.STRING);
        vtlComponent.setDomainValue(VtlType.STRING.getDomainValue());
        vtlComponent.setDomainValueParent(VtlType.STRING.getDomainValueParent());
        vtlComponent.setVtlComponentRole(VtlComponentRole.IDENTIFIER);
        vtlComponent.setVtlDataset(vtlDataset);
        vtlComponents.add(vtlComponent);

        vtlComponent = new VtlComponent();
        vtlComponent.setName("ID_3");
        vtlComponent.setType(VtlType.INTEGER);
        vtlComponent.setDomainValue(VtlType.INTEGER.getDomainValue());
        vtlComponent.setDomainValueParent(VtlType.INTEGER.getDomainValueParent());
        vtlComponent.setVtlComponentRole(VtlComponentRole.IDENTIFIER);
        vtlComponent.setVtlDataset(vtlDataset);
        vtlComponents.add(vtlComponent);

        vtlComponent = new VtlComponent();
        vtlComponent.setName("ME_1");
        vtlComponent.setType(VtlType.INTEGER);
        vtlComponent.setDomainValue(VtlType.INTEGER.getDomainValue());
        vtlComponent.setDomainValueParent(VtlType.INTEGER.getDomainValueParent());
        vtlComponent.setVtlComponentRole(VtlComponentRole.MEASURE);
        vtlComponent.setVtlDataset(vtlDataset);
        vtlComponents.add(vtlComponent);

        vtlComponent = new VtlComponent();
        vtlComponent.setName("ME_2");
        vtlComponent.setType(VtlType.INTEGER);
        vtlComponent.setDomainValue(VtlType.INTEGER.getDomainValue());
        vtlComponent.setDomainValueParent(VtlType.INTEGER.getDomainValueParent());
        vtlComponent.setVtlComponentRole(VtlComponentRole.MEASURE);
        vtlComponent.setVtlDataset(vtlDataset);
        vtlComponents.add(vtlComponent);

        vtlDataset34.addComponentsList(vtlComponents);
        datasetRepository.save(vtlDataset34);


        VtlDataset vtlDataset35 = new VtlDataset();
        vtlDataset35.setPersistent(true);
        vtlDataset35.setName("DS_35");
        vtlDataset35.setIsOnlyAScalar(false);
        vtlDataset35.setTransitory(false);
        vtlComponents = new ArrayList<>();

        vtlComponent = new VtlComponent();
        vtlComponent.setName("ID_1");
        vtlComponent.setType(VtlType.TIME);
        vtlComponent.setDomainValue(VtlType.TIME.getDomainValue());
        vtlComponent.setDomainValueParent(VtlType.TIME.getDomainValueParent());
        vtlComponent.setVtlComponentRole(VtlComponentRole.IDENTIFIER);
        vtlComponent.setVtlDataset(vtlDataset);
        vtlComponents.add(vtlComponent);

        vtlComponent = new VtlComponent();
        vtlComponent.setName("ID_2");
        vtlComponent.setType(VtlType.STRING);
        vtlComponent.setDomainValue(VtlType.STRING.getDomainValue());
        vtlComponent.setDomainValueParent(VtlType.STRING.getDomainValueParent());
        vtlComponent.setVtlComponentRole(VtlComponentRole.IDENTIFIER);
        vtlComponent.setVtlDataset(vtlDataset);
        vtlComponents.add(vtlComponent);

        vtlComponent = new VtlComponent();
        vtlComponent.setName("ME_1");
        vtlComponent.setType(VtlType.INTEGER);
        vtlComponent.setDomainValue(VtlType.INTEGER.getDomainValue());
        vtlComponent.setDomainValueParent(VtlType.INTEGER.getDomainValueParent());
        vtlComponent.setVtlComponentRole(VtlComponentRole.MEASURE);
        vtlComponent.setVtlDataset(vtlDataset);
        vtlComponents.add(vtlComponent);

        vtlDataset35.addComponentsList(vtlComponents);
        datasetRepository.save(vtlDataset35);


        VtlDataset vtlDataset36 = new VtlDataset();
        vtlDataset36.setPersistent(true);
        vtlDataset36.setName("DS_36");
        vtlDataset36.setIsOnlyAScalar(false);
        vtlDataset36.setTransitory(false);
        vtlComponents = new ArrayList<>();

        vtlComponent = new VtlComponent();
        vtlComponent.setName("ID_1");
        vtlComponent.setType(VtlType.INTEGER);
        vtlComponent.setDomainValue(VtlType.INTEGER.getDomainValue());
        vtlComponent.setDomainValueParent(VtlType.INTEGER.getDomainValueParent());
        vtlComponent.setVtlComponentRole(VtlComponentRole.IDENTIFIER);
        vtlComponent.setVtlDataset(vtlDataset);
        vtlComponents.add(vtlComponent);

        vtlComponent = new VtlComponent();
        vtlComponent.setName("ID_2");
        vtlComponent.setType(VtlType.STRING);
        vtlComponent.setDomainValue(VtlType.STRING.getDomainValue());
        vtlComponent.setDomainValueParent(VtlType.STRING.getDomainValueParent());
        vtlComponent.setVtlComponentRole(VtlComponentRole.IDENTIFIER);
        vtlComponent.setVtlDataset(vtlDataset);
        vtlComponents.add(vtlComponent);

        vtlComponent = new VtlComponent();
        vtlComponent.setName("ME_1A");
        vtlComponent.setType(VtlType.STRING);
        vtlComponent.setDomainValue(VtlType.STRING.getDomainValue());
        vtlComponent.setDomainValueParent(VtlType.STRING.getDomainValueParent());
        vtlComponent.setVtlComponentRole(VtlComponentRole.MEASURE);
        vtlComponent.setVtlDataset(vtlDataset);
        vtlComponents.add(vtlComponent);

        vtlComponent = new VtlComponent();
        vtlComponent.setName("ME_2");
        vtlComponent.setType(VtlType.STRING);
        vtlComponent.setDomainValue(VtlType.STRING.getDomainValue());
        vtlComponent.setDomainValueParent(VtlType.STRING.getDomainValueParent());
        vtlComponent.setVtlComponentRole(VtlComponentRole.MEASURE);
        vtlComponent.setVtlDataset(vtlDataset);
        vtlComponents.add(vtlComponent);

        vtlDataset36.addComponentsList(vtlComponents);
        datasetRepository.save(vtlDataset36);


        VtlDataset vtlDataset37 = new VtlDataset();
        vtlDataset37.setPersistent(true);
        vtlDataset37.setName("DS_37");
        vtlDataset37.setIsOnlyAScalar(false);
        vtlDataset37.setTransitory(false);
        vtlComponents = new ArrayList<>();

        vtlComponent = new VtlComponent();
        vtlComponent.setName("ID_1");
        vtlComponent.setType(VtlType.INTEGER);
        vtlComponent.setDomainValue(VtlType.INTEGER.getDomainValue());
        vtlComponent.setDomainValueParent(VtlType.INTEGER.getDomainValueParent());
        vtlComponent.setVtlComponentRole(VtlComponentRole.IDENTIFIER);
        vtlComponent.setVtlDataset(vtlDataset);
        vtlComponents.add(vtlComponent);

        vtlComponent = new VtlComponent();
        vtlComponent.setName("ID_2");
        vtlComponent.setType(VtlType.STRING);
        vtlComponent.setDomainValue(VtlType.STRING.getDomainValue());
        vtlComponent.setDomainValueParent(VtlType.STRING.getDomainValueParent());
        vtlComponent.setVtlComponentRole(VtlComponentRole.IDENTIFIER);
        vtlComponent.setVtlDataset(vtlDataset);
        vtlComponents.add(vtlComponent);

        vtlComponent = new VtlComponent();
        vtlComponent.setName("ID_3");
        vtlComponent.setType(VtlType.INTEGER);
        vtlComponent.setDomainValue(VtlType.INTEGER.getDomainValue());
        vtlComponent.setDomainValueParent(VtlType.INTEGER.getDomainValueParent());
        vtlComponent.setVtlComponentRole(VtlComponentRole.IDENTIFIER);
        vtlComponent.setVtlDataset(vtlDataset);
        vtlComponents.add(vtlComponent);

        vtlComponent = new VtlComponent();
        vtlComponent.setName("ID_4");
        vtlComponent.setType(VtlType.STRING);
        vtlComponent.setDomainValue(VtlType.STRING.getDomainValue());
        vtlComponent.setDomainValueParent(VtlType.STRING.getDomainValueParent());
        vtlComponent.setVtlComponentRole(VtlComponentRole.IDENTIFIER);
        vtlComponent.setVtlDataset(vtlDataset);
        vtlComponents.add(vtlComponent);

        vtlComponent = new VtlComponent();
        vtlComponent.setName("ID_5");
        vtlComponent.setType(VtlType.STRING);
        vtlComponent.setDomainValue(VtlType.STRING.getDomainValue());
        vtlComponent.setDomainValueParent(VtlType.STRING.getDomainValueParent());
        vtlComponent.setVtlComponentRole(VtlComponentRole.IDENTIFIER);
        vtlComponent.setVtlDataset(vtlDataset);
        vtlComponents.add(vtlComponent);

        vtlComponent = new VtlComponent();
        vtlComponent.setName("ME_1");
        vtlComponent.setType(VtlType.NUMBER);
        vtlComponent.setDomainValue(VtlType.NUMBER.getDomainValue());
        vtlComponent.setDomainValueParent(VtlType.NUMBER.getDomainValueParent());
        vtlComponent.setVtlComponentRole(VtlComponentRole.MEASURE);
        vtlComponent.setVtlDataset(vtlDataset);
        vtlComponents.add(vtlComponent);

        vtlDataset37.addComponentsList(vtlComponents);
        datasetRepository.save(vtlDataset37);


        VtlDataset vtlDataset38 = new VtlDataset();
        vtlDataset38.setPersistent(true);
        vtlDataset38.setName("DS_38");
        vtlDataset38.setIsOnlyAScalar(false);
        vtlDataset38.setTransitory(false);
        vtlComponents = new ArrayList<>();

        vtlComponent = new VtlComponent();
        vtlComponent.setName("ID_1");
        vtlComponent.setType(VtlType.INTEGER);
        vtlComponent.setDomainValue(VtlType.INTEGER.getDomainValue());
        vtlComponent.setDomainValueParent(VtlType.INTEGER.getDomainValueParent());
        vtlComponent.setVtlComponentRole(VtlComponentRole.IDENTIFIER);
        vtlComponent.setVtlDataset(vtlDataset);
        vtlComponents.add(vtlComponent);

        vtlComponent = new VtlComponent();
        vtlComponent.setName("ID_2");
        vtlComponent.setType(VtlType.STRING);
        vtlComponent.setDomainValue(VtlType.STRING.getDomainValue());
        vtlComponent.setDomainValueParent(VtlType.STRING.getDomainValueParent());
        vtlComponent.setVtlComponentRole(VtlComponentRole.IDENTIFIER);
        vtlComponent.setVtlDataset(vtlDataset);
        vtlComponents.add(vtlComponent);

        vtlComponent = new VtlComponent();
        vtlComponent.setName("ME_1");
        vtlComponent.setType(VtlType.INTEGER);
        vtlComponent.setDomainValue(VtlType.INTEGER.getDomainValue());
        vtlComponent.setDomainValueParent(VtlType.INTEGER.getDomainValueParent());
        vtlComponent.setVtlComponentRole(VtlComponentRole.MEASURE);
        vtlComponent.setVtlDataset(vtlDataset);
        vtlComponents.add(vtlComponent);

        vtlDataset38.addComponentsList(vtlComponents);
        datasetRepository.save(vtlDataset38);


        VtlDataset vtlDataset39 = new VtlDataset();
        vtlDataset39.setPersistent(true);
        vtlDataset39.setName("DS_39");
        vtlDataset39.setIsOnlyAScalar(false);
        vtlDataset39.setTransitory(false);
        vtlComponents = new ArrayList<>();

        vtlComponent = new VtlComponent();
        vtlComponent.setName("ID_1");
        vtlComponent.setType(VtlType.STRING);
        vtlComponent.setDomainValue(VtlType.STRING.getDomainValue());
        vtlComponent.setDomainValueParent(VtlType.STRING.getDomainValueParent());
        vtlComponent.setVtlComponentRole(VtlComponentRole.IDENTIFIER);
        vtlComponent.setVtlDataset(vtlDataset);
        vtlComponents.add(vtlComponent);

        vtlComponent = new VtlComponent();
        vtlComponent.setName("ID_2");
        vtlComponent.setType(VtlType.INTEGER);
        vtlComponent.setDomainValue(VtlType.INTEGER.getDomainValue());
        vtlComponent.setDomainValueParent(VtlType.INTEGER.getDomainValueParent());
        vtlComponent.setVtlComponentRole(VtlComponentRole.IDENTIFIER);
        vtlComponent.setVtlDataset(vtlDataset);
        vtlComponents.add(vtlComponent);

        vtlComponent = new VtlComponent();
        vtlComponent.setName("ID_3");
        vtlComponent.setType(VtlType.TIME_PERIOD);
        vtlComponent.setDomainValue(VtlType.TIME_PERIOD.getDomainValue());
        vtlComponent.setDomainValueParent(VtlType.TIME_PERIOD.getDomainValueParent());
        vtlComponent.setVtlComponentRole(VtlComponentRole.IDENTIFIER);
        vtlComponent.setVtlDataset(vtlDataset);
        vtlComponents.add(vtlComponent);

        vtlComponent = new VtlComponent();
        vtlComponent.setName("ME_1");
        vtlComponent.setType(VtlType.INTEGER);
        vtlComponent.setDomainValue(VtlType.INTEGER.getDomainValue());
        vtlComponent.setDomainValueParent(VtlType.INTEGER.getDomainValueParent());
        vtlComponent.setVtlComponentRole(VtlComponentRole.MEASURE);
        vtlComponent.setVtlDataset(vtlDataset);
        vtlComponents.add(vtlComponent);

        vtlDataset39.addComponentsList(vtlComponents);
        datasetRepository.save(vtlDataset39);


        VtlDataset vtlDataset40 = new VtlDataset();
        vtlDataset40.setPersistent(true);
        vtlDataset40.setName("DS_40");
        vtlDataset40.setIsOnlyAScalar(false);
        vtlDataset40.setTransitory(false);
        vtlComponents = new ArrayList<>();

        vtlComponent = new VtlComponent();
        vtlComponent.setName("ID_1");
        vtlComponent.setType(VtlType.INTEGER);
        vtlComponent.setDomainValue(VtlType.INTEGER.getDomainValue());
        vtlComponent.setDomainValueParent(VtlType.INTEGER.getDomainValueParent());
        vtlComponent.setVtlComponentRole(VtlComponentRole.IDENTIFIER);
        vtlComponent.setVtlDataset(vtlDataset);
        vtlComponents.add(vtlComponent);

        vtlComponent = new VtlComponent();
        vtlComponent.setName("A");
        vtlComponent.setType(VtlType.INTEGER);
        vtlComponent.setDomainValue(VtlType.INTEGER.getDomainValue());
        vtlComponent.setDomainValueParent(VtlType.INTEGER.getDomainValueParent());
        vtlComponent.setVtlComponentRole(VtlComponentRole.MEASURE);
        vtlComponent.setVtlDataset(vtlDataset);
        vtlComponents.add(vtlComponent);

        vtlComponent = new VtlComponent();
        vtlComponent.setName("B");
        vtlComponent.setType(VtlType.INTEGER);
        vtlComponent.setDomainValue(VtlType.INTEGER.getDomainValue());
        vtlComponent.setDomainValueParent(VtlType.INTEGER.getDomainValueParent());
        vtlComponent.setVtlComponentRole(VtlComponentRole.MEASURE);
        vtlComponent.setVtlDataset(vtlDataset);
        vtlComponents.add(vtlComponent);

        vtlComponent = new VtlComponent();
        vtlComponent.setName("C");
        vtlComponent.setType(VtlType.INTEGER);
        vtlComponent.setDomainValue(VtlType.INTEGER.getDomainValue());
        vtlComponent.setDomainValueParent(VtlType.INTEGER.getDomainValueParent());
        vtlComponent.setVtlComponentRole(VtlComponentRole.MEASURE);
        vtlComponent.setVtlDataset(vtlDataset);
        vtlComponents.add(vtlComponent);

        vtlDataset40.addComponentsList(vtlComponents);
        datasetRepository.save(vtlDataset40);


        VtlDataset vtlDataset41 = new VtlDataset();
        vtlDataset41.setPersistent(true);
        vtlDataset41.setName("DS_41");
        vtlDataset41.setIsOnlyAScalar(false);
        vtlDataset41.setTransitory(false);
        vtlComponents = new ArrayList<>();

        vtlComponent = new VtlComponent();
        vtlComponent.setName("ID_1");
        vtlComponent.setType(VtlType.INTEGER);
        vtlComponent.setDomainValue(VtlType.INTEGER.getDomainValue());
        vtlComponent.setDomainValueParent(VtlType.INTEGER.getDomainValueParent());
        vtlComponent.setVtlComponentRole(VtlComponentRole.IDENTIFIER);
        vtlComponent.setVtlDataset(vtlDataset41);
        vtlComponents.add(vtlComponent);

        vtlComponent = new VtlComponent();
        vtlComponent.setName("ID_2");
        vtlComponent.setType(VtlType.STRING);
        vtlComponent.setDomainValue(VtlType.STRING.getDomainValue());
        vtlComponent.setDomainValueParent(VtlType.STRING.getDomainValueParent());
        vtlComponent.setVtlComponentRole(VtlComponentRole.IDENTIFIER);
        vtlComponent.setVtlDataset(vtlDataset);
        vtlComponents.add(vtlComponent);

        vtlComponent = new VtlComponent();
        vtlComponent.setName("ME_1");
        vtlComponent.setType(VtlType.STRING);
        vtlComponent.setDomainValue(VtlType.STRING.getDomainValue());
        vtlComponent.setDomainValueParent(VtlType.STRING.getDomainValueParent());
        vtlComponent.setVtlComponentRole(VtlComponentRole.MEASURE);
        vtlComponent.setVtlDataset(vtlDataset);
        vtlComponents.add(vtlComponent);

        vtlDataset41.addComponentsList(vtlComponents);
        datasetRepository.save(vtlDataset41);

        VtlDataset vtlDataset42 = new VtlDataset();
        vtlDataset42.setPersistent(true);
        vtlDataset42.setName("DS_42");
        vtlDataset42.setIsOnlyAScalar(false);
        vtlDataset42.setTransitory(false);
        vtlComponents = new ArrayList<>();

        vtlComponent = new VtlComponent();
        vtlComponent.setName("ID_1");
        vtlComponent.setType(VtlType.STRING);
        String clArea = "cl_area";
        vtlComponent.setDomainValue(clArea);
        vtlComponent.setDomainValueParent(clArea);
        vtlComponent.setVtlComponentRole(VtlComponentRole.IDENTIFIER);
        vtlComponent.setVtlDataset(vtlDataset42);
        vtlComponents.add(vtlComponent);

        vtlComponent = new VtlComponent();
        vtlComponent.setName("ID_2");
        vtlComponent.setType(VtlType.STRING);
        String clSex = "cl_sex";
        vtlComponent.setDomainValue(clSex);
        vtlComponent.setDomainValueParent(clSex);
        vtlComponent.setVtlComponentRole(VtlComponentRole.IDENTIFIER);
        vtlComponent.setVtlDataset(vtlDataset42);
        vtlComponents.add(vtlComponent);

        vtlComponent = new VtlComponent();
        vtlComponent.setName("ME_1");
        vtlComponent.setType(VtlType.NUMBER);
        vtlComponent.setDomainValue(VtlType.NUMBER.getDomainValue());
        vtlComponent.setDomainValueParent(VtlType.NUMBER.getDomainValueParent());
        vtlComponent.setVtlComponentRole(VtlComponentRole.MEASURE);
        vtlComponent.setVtlDataset(vtlDataset42);
        vtlComponents.add(vtlComponent);

        vtlComponent = new VtlComponent();
        vtlComponent.setName("ME_2");
        vtlComponent.setType(VtlType.NUMBER);
        vtlComponent.setDomainValue(VtlType.NUMBER.getDomainValue());
        vtlComponent.setDomainValueParent(VtlType.NUMBER.getDomainValueParent());
        vtlComponent.setVtlComponentRole(VtlComponentRole.MEASURE);
        vtlComponent.setVtlDataset(vtlDataset42);
        vtlComponents.add(vtlComponent);

        vtlComponent = new VtlComponent();
        vtlComponent.setName("AT_1");
        vtlComponent.setType(VtlType.STRING);
        vtlComponent.setDomainValue(VtlType.STRING.getDomainValue());
        vtlComponent.setDomainValueParent(VtlType.STRING.getDomainValueParent());
        vtlComponent.setVtlComponentRole(VtlComponentRole.ATTRIBUTE);
        vtlComponent.setVtlDataset(vtlDataset42);
        vtlComponents.add(vtlComponent);

        vtlComponent = new VtlComponent();
        vtlComponent.setName("AT_2");
        vtlComponent.setType(VtlType.STRING);
        vtlComponent.setDomainValue(VtlType.STRING.getDomainValue());
        vtlComponent.setDomainValueParent(VtlType.STRING.getDomainValueParent());
        vtlComponent.setVtlComponentRole(VtlComponentRole.VIRAL);
        vtlComponent.setVtlDataset(vtlDataset42);
        vtlComponents.add(vtlComponent);

        vtlDataset42.addComponentsList(vtlComponents);
        datasetRepository.save(vtlDataset42);

        vtlDataset = new VtlDataset();
        vtlDataset.setPersistent(true);
        vtlDataset.setName("DS_56");
        vtlDataset.setIsOnlyAScalar(false);
        vtlDataset.setTransitory(false);
        vtlComponents = new ArrayList<>();

        vtlComponent = new VtlComponent();
        vtlComponent.setName("ID_1");
        vtlComponent.setType(VtlType.INTEGER);
        vtlComponent.setDomainValue(VtlType.INTEGER.getDomainValue());
        vtlComponent.setDomainValueParent(VtlType.INTEGER.getDomainValueParent());
        vtlComponent.setVtlComponentRole(VtlComponentRole.IDENTIFIER);
        vtlComponent.setVtlDataset(vtlDataset41);
        vtlComponents.add(vtlComponent);

        vtlComponent = new VtlComponent();
        vtlComponent.setName("ID_2");
        vtlComponent.setType(VtlType.STRING);
        vtlComponent.setDomainValue(VtlType.STRING.getDomainValue());
        vtlComponent.setDomainValueParent(VtlType.STRING.getDomainValueParent());
        vtlComponent.setVtlComponentRole(VtlComponentRole.IDENTIFIER);
        vtlComponent.setVtlDataset(vtlDataset);
        vtlComponents.add(vtlComponent);

        vtlComponent = new VtlComponent();
        vtlComponent.setName("ME_1");
        vtlComponent.setType(VtlType.INTEGER);
        vtlComponent.setDomainValue(VtlType.INTEGER.getDomainValue());
        vtlComponent.setDomainValueParent(VtlType.INTEGER.getDomainValueParent());
        vtlComponent.setVtlComponentRole(VtlComponentRole.MEASURE);
        vtlComponent.setVtlDataset(vtlDataset);
        vtlComponents.add(vtlComponent);

        vtlComponent = new VtlComponent();
        vtlComponent.setName("ME_2");
        vtlComponent.setType(VtlType.INTEGER);
        vtlComponent.setDomainValue(VtlType.INTEGER.getDomainValue());
        vtlComponent.setDomainValueParent(VtlType.INTEGER.getDomainValueParent());
        vtlComponent.setVtlComponentRole(VtlComponentRole.MEASURE);
        vtlComponent.setVtlDataset(vtlDataset);
        vtlComponents.add(vtlComponent);

        vtlComponent = new VtlComponent();
        vtlComponent.setName("ME_3");
        vtlComponent.setType(VtlType.INTEGER);
        vtlComponent.setDomainValue(VtlType.INTEGER.getDomainValue());
        vtlComponent.setDomainValueParent(VtlType.INTEGER.getDomainValueParent());
        vtlComponent.setVtlComponentRole(VtlComponentRole.MEASURE);
        vtlComponent.setVtlDataset(vtlDataset);
        vtlComponents.add(vtlComponent);

        vtlDataset.addComponentsList(vtlComponents);
        datasetRepository.save(vtlDataset);

        vtlDataset = new VtlDataset();
        vtlDataset.setPersistent(true);
        vtlDataset.setName("DS_61");
        vtlDataset.setIsOnlyAScalar(false);
        vtlDataset.setTransitory(false);
        vtlComponents = new ArrayList<>();

        vtlComponent = new VtlComponent();
        vtlComponent.setName("ID_1");
        vtlComponent.setType(VtlType.INTEGER);
        vtlComponent.setDomainValue(VtlType.INTEGER.getDomainValue());
        vtlComponent.setDomainValueParent(VtlType.INTEGER.getDomainValueParent());
        vtlComponent.setVtlComponentRole(VtlComponentRole.IDENTIFIER);
        vtlComponent.setVtlDataset(vtlDataset41);
        vtlComponents.add(vtlComponent);

        vtlComponent = new VtlComponent();
        vtlComponent.setName("ID_2");
        vtlComponent.setType(VtlType.STRING);
        vtlComponent.setDomainValue(VtlType.STRING.getDomainValue());
        vtlComponent.setDomainValueParent(VtlType.STRING.getDomainValueParent());
        vtlComponent.setVtlComponentRole(VtlComponentRole.IDENTIFIER);
        vtlComponent.setVtlDataset(vtlDataset);
        vtlComponents.add(vtlComponent);

        vtlComponent = new VtlComponent();
        vtlComponent.setName("ME_1");
        vtlComponent.setType(VtlType.INTEGER);
        vtlComponent.setDomainValue(VtlType.INTEGER.getDomainValue());
        vtlComponent.setDomainValueParent(VtlType.INTEGER.getDomainValueParent());
        vtlComponent.setVtlComponentRole(VtlComponentRole.MEASURE);
        vtlComponent.setVtlDataset(vtlDataset);
        vtlComponents.add(vtlComponent);

        vtlComponent = new VtlComponent();
        vtlComponent.setName("AT1");
        vtlComponent.setType(VtlType.INTEGER);
        vtlComponent.setDomainValue(VtlType.INTEGER.getDomainValue());
        vtlComponent.setDomainValueParent(VtlType.INTEGER.getDomainValueParent());
        vtlComponent.setVtlComponentRole(VtlComponentRole.ATTRIBUTE);
        vtlComponent.setVtlDataset(vtlDataset);
        vtlComponents.add(vtlComponent);

        vtlDataset.addComponentsList(vtlComponents);
        datasetRepository.save(vtlDataset);

        vtlDataset = new VtlDataset();
        vtlDataset.setPersistent(true);
        vtlDataset.setName("DS_49");
        vtlDataset.setIsOnlyAScalar(false);
        vtlDataset.setTransitory(false);
        vtlComponents = new ArrayList<>();

        vtlComponent = new VtlComponent();
        vtlComponent.setName("ID_1");
        vtlComponent.setType(VtlType.STRING);
        vtlComponent.setDomainValue(VtlType.STRING.getDomainValue());
        vtlComponent.setDomainValueParent(VtlType.STRING.getDomainValueParent());
        vtlComponent.setVtlComponentRole(VtlComponentRole.IDENTIFIER);
        vtlComponent.setVtlDataset(vtlDataset);
        vtlComponents.add(vtlComponent);

        vtlComponent = new VtlComponent();
        vtlComponent.setName("ID_2");
        vtlComponent.setType(VtlType.STRING);
        vtlComponent.setDomainValue(VtlType.STRING.getDomainValue());
        vtlComponent.setDomainValueParent(VtlType.STRING.getDomainValueParent());
        vtlComponent.setVtlComponentRole(VtlComponentRole.IDENTIFIER);
        vtlComponent.setVtlDataset(vtlDataset);
        vtlComponents.add(vtlComponent);

        vtlComponent = new VtlComponent();
        vtlComponent.setName("ME_1");
        vtlComponent.setType(VtlType.INTEGER);
        vtlComponent.setDomainValue(VtlType.INTEGER.getDomainValue());
        vtlComponent.setDomainValueParent(VtlType.INTEGER.getDomainValueParent());
        vtlComponent.setVtlComponentRole(VtlComponentRole.MEASURE);
        vtlComponent.setVtlDataset(vtlDataset);
        vtlComponents.add(vtlComponent);

        vtlComponent = new VtlComponent();
        vtlComponent.setName("AT1");
        vtlComponent.setType(VtlType.STRING);
        vtlComponent.setDomainValue(VtlType.STRING.getDomainValue());
        vtlComponent.setDomainValueParent(VtlType.STRING.getDomainValueParent());
        vtlComponent.setVtlComponentRole(VtlComponentRole.VIRAL);
        vtlComponent.setVtlDataset(vtlDataset);
        vtlComponents.add(vtlComponent);

        vtlDataset.addComponentsList(vtlComponents);
        datasetRepository.save(vtlDataset);

        vtlDataset = new VtlDataset();
        vtlDataset.setPersistent(true);
        vtlDataset.setName("DS_62");
        vtlDataset.setIsOnlyAScalar(false);
        vtlDataset.setTransitory(false);
        vtlComponents = new ArrayList<>();

        vtlComponent = new VtlComponent();
        vtlComponent.setName("ID_1");
        vtlComponent.setType(VtlType.INTEGER);
        vtlComponent.setDomainValue(VtlType.INTEGER.getDomainValue());
        vtlComponent.setDomainValueParent(VtlType.INTEGER.getDomainValueParent());
        vtlComponent.setVtlComponentRole(VtlComponentRole.IDENTIFIER);
        vtlComponent.setVtlDataset(vtlDataset41);
        vtlComponents.add(vtlComponent);

        vtlComponent = new VtlComponent();
        vtlComponent.setName("ID_2");
        vtlComponent.setType(VtlType.STRING);
        vtlComponent.setDomainValue(VtlType.STRING.getDomainValue());
        vtlComponent.setDomainValueParent(VtlType.STRING.getDomainValueParent());
        vtlComponent.setVtlComponentRole(VtlComponentRole.IDENTIFIER);
        vtlComponent.setVtlDataset(vtlDataset);
        vtlComponents.add(vtlComponent);

        vtlComponent = new VtlComponent();
        vtlComponent.setName("ME_1");
        vtlComponent.setType(VtlType.INTEGER);
        vtlComponent.setDomainValue(VtlType.INTEGER.getDomainValue());
        vtlComponent.setDomainValueParent(VtlType.INTEGER.getDomainValueParent());
        vtlComponent.setVtlComponentRole(VtlComponentRole.MEASURE);
        vtlComponent.setVtlDataset(vtlDataset);
        vtlComponents.add(vtlComponent);

        vtlComponent = new VtlComponent();
        vtlComponent.setName("ME_2");
        vtlComponent.setType(VtlType.NUMBER);
        vtlComponent.setDomainValue(VtlType.NUMBER.getDomainValue());
        vtlComponent.setDomainValueParent(VtlType.NUMBER.getDomainValueParent());
        vtlComponent.setVtlComponentRole(VtlComponentRole.MEASURE);
        vtlComponent.setVtlDataset(vtlDataset);
        vtlComponents.add(vtlComponent);

        vtlComponent = new VtlComponent();
        vtlComponent.setName("ME_3");
        vtlComponent.setType(VtlType.DATE);
        vtlComponent.setDomainValue(VtlType.DATE.getDomainValue());
        vtlComponent.setDomainValueParent(VtlType.DATE.getDomainValueParent());
        vtlComponent.setVtlComponentRole(VtlComponentRole.MEASURE);
        vtlComponent.setVtlDataset(vtlDataset);
        vtlComponents.add(vtlComponent);

        vtlComponent = new VtlComponent();
        vtlComponent.setName("ME_4");
        vtlComponent.setType(VtlType.BOOLEAN);
        vtlComponent.setDomainValue(VtlType.BOOLEAN.getDomainValue());
        vtlComponent.setDomainValueParent(VtlType.BOOLEAN.getDomainValueParent());
        vtlComponent.setVtlComponentRole(VtlComponentRole.MEASURE);
        vtlComponent.setVtlDataset(vtlDataset);
        vtlComponents.add(vtlComponent);

        vtlComponent = new VtlComponent();
        vtlComponent.setName("ME_5");
        vtlComponent.setType(VtlType.TIME);
        vtlComponent.setDomainValue(VtlType.TIME.getDomainValue());
        vtlComponent.setDomainValueParent(VtlType.TIME.getDomainValueParent());
        vtlComponent.setVtlComponentRole(VtlComponentRole.MEASURE);
        vtlComponent.setVtlDataset(vtlDataset);
        vtlComponents.add(vtlComponent);

        vtlComponent = new VtlComponent();
        vtlComponent.setName("ME_6");
        vtlComponent.setType(VtlType.TIME_PERIOD);
        vtlComponent.setDomainValue(VtlType.TIME_PERIOD.getDomainValue());
        vtlComponent.setDomainValueParent(VtlType.TIME_PERIOD.getDomainValueParent());
        vtlComponent.setVtlComponentRole(VtlComponentRole.MEASURE);
        vtlComponent.setVtlDataset(vtlDataset);
        vtlComponents.add(vtlComponent);

        vtlComponent = new VtlComponent();
        vtlComponent.setName("ME_7");
        vtlComponent.setType(VtlType.DURATION);
        vtlComponent.setDomainValue(VtlType.DURATION.getDomainValue());
        vtlComponent.setDomainValueParent(VtlType.DURATION.getDomainValueParent());
        vtlComponent.setVtlComponentRole(VtlComponentRole.MEASURE);
        vtlComponent.setVtlDataset(vtlDataset);
        vtlComponents.add(vtlComponent);

        vtlComponent = new VtlComponent();
        vtlComponent.setName("ME_8");
        vtlComponent.setType(VtlType.STRING);
        vtlComponent.setDomainValue(VtlType.STRING.getDomainValue());
        vtlComponent.setDomainValueParent(VtlType.STRING.getDomainValueParent());
        vtlComponent.setVtlComponentRole(VtlComponentRole.MEASURE);
        vtlComponent.setVtlDataset(vtlDataset);
        vtlComponents.add(vtlComponent);

        vtlDataset.addComponentsList(vtlComponents);
        datasetRepository.save(vtlDataset);

        VtlDataset vtlDatasetN = new VtlDataset();
        vtlDatasetN.setPersistent(true);
        vtlDatasetN.setName("DS_N");
        vtlDatasetN.setIsOnlyAScalar(false);
        vtlDatasetN.setTransitory(false);
        vtlComponents = new ArrayList<>();


        vtlDatasetN.addComponentsList(vtlComponents);
        datasetRepository.save(vtlDatasetN);

        ValueDomain valueDomain = new ValueDomain();
        valueDomain.setCode("");
        valueDomain.setValueDomainName(VtlType.NUMBER.getDomainValue());
        valueDomain.setDescription("");
        valueDomainRepository.save(valueDomain);

        valueDomain = new ValueDomain();
        valueDomain.setCode("T");
        valueDomain.setValueDomainName(clSex);
        valueDomain.setDescription("");
        valueDomainRepository.save(valueDomain);

        valueDomain = new ValueDomain();
        valueDomain.setCode("F");
        valueDomain.setValueDomainName(clSex);
        valueDomain.setDescription("");
        valueDomainRepository.save(valueDomain);

        valueDomain = new ValueDomain();
        valueDomain.setCode("M");
        valueDomain.setValueDomainName(clSex);
        valueDomain.setDescription("");
        valueDomainRepository.save(valueDomain);

        valueDomain = new ValueDomain();
        valueDomain.setCode("");
        valueDomain.setValueDomainName(clArea);
        valueDomain.setDescription("");
        valueDomainRepository.save(valueDomain);

        valueDomain = new ValueDomain();
        valueDomain.setCode("");
        valueDomain.setValueDomainName("vd_me1");
        valueDomain.setDescription("");
        valueDomainRepository.save(valueDomain);

        valueDomain = new ValueDomain();
        valueDomain.setCode("");
        valueDomain.setValueDomainName("vd_me2");
        valueDomain.setDescription("");
        valueDomainRepository.save(valueDomain);

        //dataset per UAT

        vtlDataset = new VtlDataset();
        vtlDataset.setPersistent(true);
        vtlDataset.setName("DS_micro_pos");
        vtlDataset.setIsOnlyAScalar(false);
        vtlDataset.setTransitory(false);
        vtlComponents = new ArrayList<>();

        vtlComponent = new VtlComponent();
        vtlComponent.setName("YEAR_POS");
        vtlComponent.setType(VtlType.STRING);
        vtlComponent.setDomainValue(VtlType.STRING.getDomainValue());
        vtlComponent.setDomainValueParent(VtlType.STRING.getDomainValueParent());
        vtlComponent.setVtlComponentRole(VtlComponentRole.IDENTIFIER);
        vtlComponent.setVtlDataset(vtlDataset);
        vtlComponents.add(vtlComponent);

        vtlComponent = new VtlComponent();
        vtlComponent.setName("MONTH_POS");
        vtlComponent.setType(VtlType.STRING);
        vtlComponent.setDomainValue(VtlType.STRING.getDomainValue());
        vtlComponent.setDomainValueParent(VtlType.STRING.getDomainValueParent());
        vtlComponent.setVtlComponentRole(VtlComponentRole.IDENTIFIER);
        vtlComponent.setVtlDataset(vtlDataset);
        vtlComponents.add(vtlComponent);

        vtlComponent = new VtlComponent();
        vtlComponent.setName(IDENT);
        vtlComponent.setType(VtlType.STRING);
        vtlComponent.setDomainValue(VtlType.STRING.getDomainValue());
        vtlComponent.setDomainValueParent(VtlType.STRING.getDomainValueParent());
        vtlComponent.setVtlComponentRole(VtlComponentRole.IDENTIFIER);
        vtlComponent.setVtlDataset(vtlDataset);
        vtlComponents.add(vtlComponent);

        vtlComponent = new VtlComponent();
        vtlComponent.setName("WEIGHT");
        vtlComponent.setType(VtlType.NUMBER);
        vtlComponent.setDomainValue(VtlType.NUMBER.getDomainValue());
        vtlComponent.setDomainValueParent(VtlType.NUMBER.getDomainValueParent());
        vtlComponent.setVtlComponentRole(VtlComponentRole.MEASURE);
        vtlComponent.setVtlDataset(vtlDataset);
        vtlComponents.add(vtlComponent);

        vtlComponent = new VtlComponent();
        vtlComponent.setName("M011");
        vtlComponent.setType(VtlType.NUMBER);
        vtlComponent.setDomainValue(VtlType.NUMBER.getDomainValue());
        vtlComponent.setDomainValueParent(VtlType.NUMBER.getDomainValueParent());
        vtlComponent.setVtlComponentRole(VtlComponentRole.MEASURE);
        vtlComponent.setVtlDataset(vtlDataset);
        vtlComponents.add(vtlComponent);

        vtlComponent = new VtlComponent();
        vtlComponent.setName("M012");
        vtlComponent.setType(VtlType.NUMBER);
        vtlComponent.setDomainValue(VtlType.NUMBER.getDomainValue());
        vtlComponent.setDomainValueParent(VtlType.NUMBER.getDomainValueParent());
        vtlComponent.setVtlComponentRole(VtlComponentRole.MEASURE);
        vtlComponent.setVtlDataset(vtlDataset);
        vtlComponents.add(vtlComponent);

        vtlComponent = new VtlComponent();
        vtlComponent.setName("M013");
        vtlComponent.setType(VtlType.NUMBER);
        vtlComponent.setDomainValue(VtlType.NUMBER.getDomainValue());
        vtlComponent.setDomainValueParent(VtlType.NUMBER.getDomainValueParent());
        vtlComponent.setVtlComponentRole(VtlComponentRole.MEASURE);
        vtlComponent.setVtlDataset(vtlDataset);
        vtlComponents.add(vtlComponent);

        vtlComponent = new VtlComponent();
        vtlComponent.setName("M021");
        vtlComponent.setType(VtlType.NUMBER);
        vtlComponent.setDomainValue(VtlType.NUMBER.getDomainValue());
        vtlComponent.setDomainValueParent(VtlType.NUMBER.getDomainValueParent());
        vtlComponent.setVtlComponentRole(VtlComponentRole.MEASURE);
        vtlComponent.setVtlDataset(vtlDataset);
        vtlComponents.add(vtlComponent);

        vtlComponent = new VtlComponent();
        vtlComponent.setName("M022");
        vtlComponent.setType(VtlType.NUMBER);
        vtlComponent.setDomainValue(VtlType.NUMBER.getDomainValue());
        vtlComponent.setDomainValueParent(VtlType.NUMBER.getDomainValueParent());
        vtlComponent.setVtlComponentRole(VtlComponentRole.MEASURE);
        vtlComponent.setVtlDataset(vtlDataset);
        vtlComponents.add(vtlComponent);

        vtlComponent = new VtlComponent();
        vtlComponent.setName("M022");
        vtlComponent.setType(VtlType.NUMBER);
        vtlComponent.setDomainValue(VtlType.NUMBER.getDomainValue());
        vtlComponent.setDomainValueParent(VtlType.NUMBER.getDomainValueParent());
        vtlComponent.setVtlComponentRole(VtlComponentRole.MEASURE);
        vtlComponent.setVtlDataset(vtlDataset);
        vtlComponents.add(vtlComponent);

        vtlComponent = new VtlComponent();
        vtlComponent.setName("M031");
        vtlComponent.setType(VtlType.NUMBER);
        vtlComponent.setDomainValue(VtlType.NUMBER.getDomainValue());
        vtlComponent.setDomainValueParent(VtlType.NUMBER.getDomainValueParent());
        vtlComponent.setVtlComponentRole(VtlComponentRole.MEASURE);
        vtlComponent.setVtlDataset(vtlDataset);
        vtlComponents.add(vtlComponent);

        vtlComponent = new VtlComponent();
        vtlComponent.setName("M032");
        vtlComponent.setType(VtlType.NUMBER);
        vtlComponent.setDomainValue(VtlType.NUMBER.getDomainValue());
        vtlComponent.setDomainValueParent(VtlType.NUMBER.getDomainValueParent());
        vtlComponent.setVtlComponentRole(VtlComponentRole.MEASURE);
        vtlComponent.setVtlDataset(vtlDataset);
        vtlComponents.add(vtlComponent);

        vtlComponent = new VtlComponent();
        vtlComponent.setName("M033");
        vtlComponent.setType(VtlType.NUMBER);
        vtlComponent.setDomainValue(VtlType.NUMBER.getDomainValue());
        vtlComponent.setDomainValueParent(VtlType.NUMBER.getDomainValueParent());
        vtlComponent.setVtlComponentRole(VtlComponentRole.MEASURE);
        vtlComponent.setVtlDataset(vtlDataset);
        vtlComponents.add(vtlComponent);

        vtlComponent = new VtlComponent();
        vtlComponent.setName("M041");
        vtlComponent.setType(VtlType.NUMBER);
        vtlComponent.setDomainValue(VtlType.NUMBER.getDomainValue());
        vtlComponent.setDomainValueParent(VtlType.NUMBER.getDomainValueParent());
        vtlComponent.setVtlComponentRole(VtlComponentRole.MEASURE);
        vtlComponent.setVtlDataset(vtlDataset);
        vtlComponents.add(vtlComponent);

        vtlComponent = new VtlComponent();
        vtlComponent.setName("M042");
        vtlComponent.setType(VtlType.NUMBER);
        vtlComponent.setDomainValue(VtlType.NUMBER.getDomainValue());
        vtlComponent.setDomainValueParent(VtlType.NUMBER.getDomainValueParent());
        vtlComponent.setVtlComponentRole(VtlComponentRole.MEASURE);
        vtlComponent.setVtlDataset(vtlDataset);
        vtlComponents.add(vtlComponent);

        vtlComponent = new VtlComponent();
        vtlComponent.setName("M043");
        vtlComponent.setType(VtlType.NUMBER);
        vtlComponent.setDomainValue(VtlType.NUMBER.getDomainValue());
        vtlComponent.setDomainValueParent(VtlType.NUMBER.getDomainValueParent());
        vtlComponent.setVtlComponentRole(VtlComponentRole.MEASURE);
        vtlComponent.setVtlDataset(vtlDataset);
        vtlComponents.add(vtlComponent);

        vtlComponent = new VtlComponent();
        vtlComponent.setName("M051");
        vtlComponent.setType(VtlType.NUMBER);
        vtlComponent.setDomainValue(VtlType.NUMBER.getDomainValue());
        vtlComponent.setDomainValueParent(VtlType.NUMBER.getDomainValueParent());
        vtlComponent.setVtlComponentRole(VtlComponentRole.MEASURE);
        vtlComponent.setVtlDataset(vtlDataset);
        vtlComponents.add(vtlComponent);

        vtlComponent = new VtlComponent();
        vtlComponent.setName("M052");
        vtlComponent.setType(VtlType.NUMBER);
        vtlComponent.setDomainValue(VtlType.NUMBER.getDomainValue());
        vtlComponent.setDomainValueParent(VtlType.NUMBER.getDomainValueParent());
        vtlComponent.setVtlComponentRole(VtlComponentRole.MEASURE);
        vtlComponent.setVtlDataset(vtlDataset);
        vtlComponents.add(vtlComponent);

        vtlComponent = new VtlComponent();
        vtlComponent.setName("M053");
        vtlComponent.setType(VtlType.NUMBER);
        vtlComponent.setDomainValue(VtlType.NUMBER.getDomainValue());
        vtlComponent.setDomainValueParent(VtlType.NUMBER.getDomainValueParent());
        vtlComponent.setVtlComponentRole(VtlComponentRole.MEASURE);
        vtlComponent.setVtlDataset(vtlDataset);
        vtlComponents.add(vtlComponent);

        vtlComponent = new VtlComponent();
        vtlComponent.setName("M061");
        vtlComponent.setType(VtlType.NUMBER);
        vtlComponent.setDomainValue(VtlType.NUMBER.getDomainValue());
        vtlComponent.setDomainValueParent(VtlType.NUMBER.getDomainValueParent());
        vtlComponent.setVtlComponentRole(VtlComponentRole.MEASURE);
        vtlComponent.setVtlDataset(vtlDataset);
        vtlComponents.add(vtlComponent);

        vtlComponent = new VtlComponent();
        vtlComponent.setName("M062");
        vtlComponent.setType(VtlType.NUMBER);
        vtlComponent.setDomainValue(VtlType.NUMBER.getDomainValue());
        vtlComponent.setDomainValueParent(VtlType.NUMBER.getDomainValueParent());
        vtlComponent.setVtlComponentRole(VtlComponentRole.MEASURE);
        vtlComponent.setVtlDataset(vtlDataset);
        vtlComponents.add(vtlComponent);

        vtlComponent = new VtlComponent();
        vtlComponent.setName("M063");
        vtlComponent.setType(VtlType.NUMBER);
        vtlComponent.setDomainValue(VtlType.NUMBER.getDomainValue());
        vtlComponent.setDomainValueParent(VtlType.NUMBER.getDomainValueParent());
        vtlComponent.setVtlComponentRole(VtlComponentRole.MEASURE);
        vtlComponent.setVtlDataset(vtlDataset);
        vtlComponents.add(vtlComponent);

        vtlComponent = new VtlComponent();
        vtlComponent.setName("M07");
        vtlComponent.setType(VtlType.NUMBER);
        vtlComponent.setDomainValue(VtlType.NUMBER.getDomainValue());
        vtlComponent.setDomainValueParent(VtlType.NUMBER.getDomainValueParent());
        vtlComponent.setVtlComponentRole(VtlComponentRole.MEASURE);
        vtlComponent.setVtlDataset(vtlDataset);
        vtlComponents.add(vtlComponent);

        vtlComponent = new VtlComponent();
        vtlComponent.setName("M08");
        vtlComponent.setType(VtlType.NUMBER);
        vtlComponent.setDomainValue(VtlType.NUMBER.getDomainValue());
        vtlComponent.setDomainValueParent(VtlType.NUMBER.getDomainValueParent());
        vtlComponent.setVtlComponentRole(VtlComponentRole.MEASURE);
        vtlComponent.setVtlDataset(vtlDataset);
        vtlComponents.add(vtlComponent);

        vtlComponent = new VtlComponent();
        vtlComponent.setName("M09");
        vtlComponent.setType(VtlType.NUMBER);
        vtlComponent.setDomainValue(VtlType.NUMBER.getDomainValue());
        vtlComponent.setDomainValueParent(VtlType.NUMBER.getDomainValueParent());
        vtlComponent.setVtlComponentRole(VtlComponentRole.MEASURE);
        vtlComponent.setVtlDataset(vtlDataset);
        vtlComponents.add(vtlComponent);

        vtlComponent = new VtlComponent();
        vtlComponent.setName("M10");
        vtlComponent.setType(VtlType.NUMBER);
        vtlComponent.setDomainValue(VtlType.NUMBER.getDomainValue());
        vtlComponent.setDomainValueParent(VtlType.NUMBER.getDomainValueParent());
        vtlComponent.setVtlComponentRole(VtlComponentRole.MEASURE);
        vtlComponent.setVtlDataset(vtlDataset);
        vtlComponents.add(vtlComponent);

        vtlComponent = new VtlComponent();
        vtlComponent.setName("M11");
        vtlComponent.setType(VtlType.NUMBER);
        vtlComponent.setDomainValue(VtlType.NUMBER.getDomainValue());
        vtlComponent.setDomainValueParent(VtlType.NUMBER.getDomainValueParent());
        vtlComponent.setVtlComponentRole(VtlComponentRole.MEASURE);
        vtlComponent.setVtlDataset(vtlDataset);
        vtlComponents.add(vtlComponent);

        vtlComponent = new VtlComponent();
        vtlComponent.setName("M12");
        vtlComponent.setType(VtlType.NUMBER);
        vtlComponent.setDomainValue(VtlType.NUMBER.getDomainValue());
        vtlComponent.setDomainValueParent(VtlType.NUMBER.getDomainValueParent());
        vtlComponent.setVtlComponentRole(VtlComponentRole.MEASURE);
        vtlComponent.setVtlDataset(vtlDataset);
        vtlComponents.add(vtlComponent);

        vtlComponent = new VtlComponent();
        vtlComponent.setName("M13");
        vtlComponent.setType(VtlType.NUMBER);
        vtlComponent.setDomainValue(VtlType.NUMBER.getDomainValue());
        vtlComponent.setDomainValueParent(VtlType.NUMBER.getDomainValueParent());
        vtlComponent.setVtlComponentRole(VtlComponentRole.MEASURE);
        vtlComponent.setVtlDataset(vtlDataset);
        vtlComponents.add(vtlComponent);

        vtlComponent = new VtlComponent();
        vtlComponent.setName("M14");
        vtlComponent.setType(VtlType.NUMBER);
        vtlComponent.setDomainValue(VtlType.NUMBER.getDomainValue());
        vtlComponent.setDomainValueParent(VtlType.NUMBER.getDomainValueParent());
        vtlComponent.setVtlComponentRole(VtlComponentRole.MEASURE);
        vtlComponent.setVtlDataset(vtlDataset);
        vtlComponents.add(vtlComponent);

        vtlComponent = new VtlComponent();
        vtlComponent.setName("M15");
        vtlComponent.setType(VtlType.NUMBER);
        vtlComponent.setDomainValue(VtlType.NUMBER.getDomainValue());
        vtlComponent.setDomainValueParent(VtlType.NUMBER.getDomainValueParent());
        vtlComponent.setVtlComponentRole(VtlComponentRole.MEASURE);
        vtlComponent.setVtlDataset(vtlDataset);
        vtlComponents.add(vtlComponent);

        vtlComponent = new VtlComponent();
        vtlComponent.setName("M16");
        vtlComponent.setType(VtlType.NUMBER);
        vtlComponent.setDomainValue(VtlType.NUMBER.getDomainValue());
        vtlComponent.setDomainValueParent(VtlType.NUMBER.getDomainValueParent());
        vtlComponent.setVtlComponentRole(VtlComponentRole.MEASURE);
        vtlComponent.setVtlDataset(vtlDataset);
        vtlComponents.add(vtlComponent);

        vtlComponent = new VtlComponent();
        vtlComponent.setName("M17");
        vtlComponent.setType(VtlType.NUMBER);
        vtlComponent.setDomainValue(VtlType.NUMBER.getDomainValue());
        vtlComponent.setDomainValueParent(VtlType.NUMBER.getDomainValueParent());
        vtlComponent.setVtlComponentRole(VtlComponentRole.MEASURE);
        vtlComponent.setVtlDataset(vtlDataset);
        vtlComponents.add(vtlComponent);

        vtlComponent = new VtlComponent();
        vtlComponent.setName("M18");
        vtlComponent.setType(VtlType.NUMBER);
        vtlComponent.setDomainValue(VtlType.NUMBER.getDomainValue());
        vtlComponent.setDomainValueParent(VtlType.NUMBER.getDomainValueParent());
        vtlComponent.setVtlComponentRole(VtlComponentRole.MEASURE);
        vtlComponent.setVtlDataset(vtlDataset);
        vtlComponents.add(vtlComponent);

        vtlComponent = new VtlComponent();
        vtlComponent.setName("M19");
        vtlComponent.setType(VtlType.NUMBER);
        vtlComponent.setDomainValue(VtlType.NUMBER.getDomainValue());
        vtlComponent.setDomainValueParent(VtlType.NUMBER.getDomainValueParent());
        vtlComponent.setVtlComponentRole(VtlComponentRole.MEASURE);
        vtlComponent.setVtlDataset(vtlDataset);
        vtlComponents.add(vtlComponent);

        vtlComponent = new VtlComponent();
        vtlComponent.setName("M20");
        vtlComponent.setType(VtlType.NUMBER);
        vtlComponent.setDomainValue(VtlType.NUMBER.getDomainValue());
        vtlComponent.setDomainValueParent(VtlType.NUMBER.getDomainValueParent());
        vtlComponent.setVtlComponentRole(VtlComponentRole.MEASURE);
        vtlComponent.setVtlDataset(vtlDataset);
        vtlComponents.add(vtlComponent);

        vtlComponent = new VtlComponent();
        vtlComponent.setName("M21");
        vtlComponent.setType(VtlType.NUMBER);
        vtlComponent.setDomainValue(VtlType.NUMBER.getDomainValue());
        vtlComponent.setDomainValueParent(VtlType.NUMBER.getDomainValueParent());
        vtlComponent.setVtlComponentRole(VtlComponentRole.MEASURE);
        vtlComponent.setVtlDataset(vtlDataset);
        vtlComponents.add(vtlComponent);

        vtlComponent = new VtlComponent();
        vtlComponent.setName("M22");
        vtlComponent.setType(VtlType.NUMBER);
        vtlComponent.setDomainValue(VtlType.NUMBER.getDomainValue());
        vtlComponent.setDomainValueParent(VtlType.NUMBER.getDomainValueParent());
        vtlComponent.setVtlComponentRole(VtlComponentRole.MEASURE);
        vtlComponent.setVtlDataset(vtlDataset);
        vtlComponents.add(vtlComponent);

        vtlComponent = new VtlComponent();
        vtlComponent.setName("M23");
        vtlComponent.setType(VtlType.NUMBER);
        vtlComponent.setDomainValue(VtlType.NUMBER.getDomainValue());
        vtlComponent.setDomainValueParent(VtlType.NUMBER.getDomainValueParent());
        vtlComponent.setVtlComponentRole(VtlComponentRole.MEASURE);
        vtlComponent.setVtlDataset(vtlDataset);
        vtlComponents.add(vtlComponent);

        vtlDataset.addComponentsList(vtlComponents);
        datasetRepository.save(vtlDataset);

        vtlDataset = new VtlDataset();
        vtlDataset.setPersistent(true);
        vtlDataset.setName("DS_micro");
        vtlDataset.setIsOnlyAScalar(false);
        vtlDataset.setTransitory(false);
        vtlComponents = new ArrayList<>();

        vtlComponent = new VtlComponent();
        vtlComponent.setName("YEAR");
        vtlComponent.setType(VtlType.STRING);
        vtlComponent.setDomainValue(VtlType.STRING.getDomainValue());
        vtlComponent.setDomainValueParent(VtlType.STRING.getDomainValueParent());
        vtlComponent.setVtlComponentRole(VtlComponentRole.IDENTIFIER);
        vtlComponent.setVtlDataset(vtlDataset);
        vtlComponents.add(vtlComponent);

        vtlComponent = new VtlComponent();
        vtlComponent.setName("MONTH");
        vtlComponent.setType(VtlType.STRING);
        vtlComponent.setDomainValue(VtlType.STRING.getDomainValue());
        vtlComponent.setDomainValueParent(VtlType.STRING.getDomainValueParent());
        vtlComponent.setVtlComponentRole(VtlComponentRole.IDENTIFIER);
        vtlComponent.setVtlDataset(vtlDataset);
        vtlComponents.add(vtlComponent);

        vtlComponent = new VtlComponent();
        vtlComponent.setName(IDENT);
        vtlComponent.setType(VtlType.STRING);
        vtlComponent.setDomainValue(VtlType.STRING.getDomainValue());
        vtlComponent.setDomainValueParent(VtlType.STRING.getDomainValueParent());
        vtlComponent.setVtlComponentRole(VtlComponentRole.IDENTIFIER);
        vtlComponent.setVtlDataset(vtlDataset);
        vtlComponents.add(vtlComponent);

        vtlComponent = new VtlComponent();
        vtlComponent.setName("WEIGHT");
        vtlComponent.setType(VtlType.NUMBER);
        vtlComponent.setDomainValue(VtlType.NUMBER.getDomainValue());
        vtlComponent.setDomainValueParent(VtlType.NUMBER.getDomainValueParent());
        vtlComponent.setVtlComponentRole(VtlComponentRole.MEASURE);
        vtlComponent.setVtlDataset(vtlDataset);
        vtlComponents.add(vtlComponent);

        vtlComponent = new VtlComponent();
        vtlComponent.setName("M011");
        vtlComponent.setType(VtlType.NUMBER);
        vtlComponent.setDomainValue(VtlType.NUMBER.getDomainValue());
        vtlComponent.setDomainValueParent(VtlType.NUMBER.getDomainValueParent());
        vtlComponent.setVtlComponentRole(VtlComponentRole.MEASURE);
        vtlComponent.setVtlDataset(vtlDataset);
        vtlComponents.add(vtlComponent);

        vtlComponent = new VtlComponent();
        vtlComponent.setName("M012");
        vtlComponent.setType(VtlType.NUMBER);
        vtlComponent.setDomainValue(VtlType.NUMBER.getDomainValue());
        vtlComponent.setDomainValueParent(VtlType.NUMBER.getDomainValueParent());
        vtlComponent.setVtlComponentRole(VtlComponentRole.MEASURE);
        vtlComponent.setVtlDataset(vtlDataset);
        vtlComponents.add(vtlComponent);

        vtlComponent = new VtlComponent();
        vtlComponent.setName("M013");
        vtlComponent.setType(VtlType.NUMBER);
        vtlComponent.setDomainValue(VtlType.NUMBER.getDomainValue());
        vtlComponent.setDomainValueParent(VtlType.NUMBER.getDomainValueParent());
        vtlComponent.setVtlComponentRole(VtlComponentRole.MEASURE);
        vtlComponent.setVtlDataset(vtlDataset);
        vtlComponents.add(vtlComponent);

        vtlComponent = new VtlComponent();
        vtlComponent.setName("M021");
        vtlComponent.setType(VtlType.NUMBER);
        vtlComponent.setDomainValue(VtlType.NUMBER.getDomainValue());
        vtlComponent.setDomainValueParent(VtlType.NUMBER.getDomainValueParent());
        vtlComponent.setVtlComponentRole(VtlComponentRole.MEASURE);
        vtlComponent.setVtlDataset(vtlDataset);
        vtlComponents.add(vtlComponent);

        vtlComponent = new VtlComponent();
        vtlComponent.setName("M022");
        vtlComponent.setType(VtlType.NUMBER);
        vtlComponent.setDomainValue(VtlType.NUMBER.getDomainValue());
        vtlComponent.setDomainValueParent(VtlType.NUMBER.getDomainValueParent());
        vtlComponent.setVtlComponentRole(VtlComponentRole.MEASURE);
        vtlComponent.setVtlDataset(vtlDataset);
        vtlComponents.add(vtlComponent);

        vtlComponent = new VtlComponent();
        vtlComponent.setName("M023");
        vtlComponent.setType(VtlType.NUMBER);
        vtlComponent.setDomainValue(VtlType.NUMBER.getDomainValue());
        vtlComponent.setDomainValueParent(VtlType.NUMBER.getDomainValueParent());
        vtlComponent.setVtlComponentRole(VtlComponentRole.MEASURE);
        vtlComponent.setVtlDataset(vtlDataset);
        vtlComponents.add(vtlComponent);

        vtlComponent = new VtlComponent();
        vtlComponent.setName("M031");
        vtlComponent.setType(VtlType.NUMBER);
        vtlComponent.setDomainValue(VtlType.NUMBER.getDomainValue());
        vtlComponent.setDomainValueParent(VtlType.NUMBER.getDomainValueParent());
        vtlComponent.setVtlComponentRole(VtlComponentRole.MEASURE);
        vtlComponent.setVtlDataset(vtlDataset);
        vtlComponents.add(vtlComponent);

        vtlComponent = new VtlComponent();
        vtlComponent.setName("M032");
        vtlComponent.setType(VtlType.NUMBER);
        vtlComponent.setDomainValue(VtlType.NUMBER.getDomainValue());
        vtlComponent.setDomainValueParent(VtlType.NUMBER.getDomainValueParent());
        vtlComponent.setVtlComponentRole(VtlComponentRole.MEASURE);
        vtlComponent.setVtlDataset(vtlDataset);
        vtlComponents.add(vtlComponent);

        vtlComponent = new VtlComponent();
        vtlComponent.setName("M033");
        vtlComponent.setType(VtlType.NUMBER);
        vtlComponent.setDomainValue(VtlType.NUMBER.getDomainValue());
        vtlComponent.setDomainValueParent(VtlType.NUMBER.getDomainValueParent());
        vtlComponent.setVtlComponentRole(VtlComponentRole.MEASURE);
        vtlComponent.setVtlDataset(vtlDataset);
        vtlComponents.add(vtlComponent);

        vtlComponent = new VtlComponent();
        vtlComponent.setName("M041");
        vtlComponent.setType(VtlType.NUMBER);
        vtlComponent.setDomainValue(VtlType.NUMBER.getDomainValue());
        vtlComponent.setDomainValueParent(VtlType.NUMBER.getDomainValueParent());
        vtlComponent.setVtlComponentRole(VtlComponentRole.MEASURE);
        vtlComponent.setVtlDataset(vtlDataset);
        vtlComponents.add(vtlComponent);

        vtlComponent = new VtlComponent();
        vtlComponent.setName("M042");
        vtlComponent.setType(VtlType.NUMBER);
        vtlComponent.setDomainValue(VtlType.NUMBER.getDomainValue());
        vtlComponent.setDomainValueParent(VtlType.NUMBER.getDomainValueParent());
        vtlComponent.setVtlComponentRole(VtlComponentRole.MEASURE);
        vtlComponent.setVtlDataset(vtlDataset);
        vtlComponents.add(vtlComponent);

        vtlComponent = new VtlComponent();
        vtlComponent.setName("M043");
        vtlComponent.setType(VtlType.NUMBER);
        vtlComponent.setDomainValue(VtlType.NUMBER.getDomainValue());
        vtlComponent.setDomainValueParent(VtlType.NUMBER.getDomainValueParent());
        vtlComponent.setVtlComponentRole(VtlComponentRole.MEASURE);
        vtlComponent.setVtlDataset(vtlDataset);
        vtlComponents.add(vtlComponent);

        vtlComponent = new VtlComponent();
        vtlComponent.setName("M051");
        vtlComponent.setType(VtlType.NUMBER);
        vtlComponent.setDomainValue(VtlType.NUMBER.getDomainValue());
        vtlComponent.setDomainValueParent(VtlType.NUMBER.getDomainValueParent());
        vtlComponent.setVtlComponentRole(VtlComponentRole.MEASURE);
        vtlComponent.setVtlDataset(vtlDataset);
        vtlComponents.add(vtlComponent);

        vtlComponent = new VtlComponent();
        vtlComponent.setName("M052");
        vtlComponent.setType(VtlType.NUMBER);
        vtlComponent.setDomainValue(VtlType.NUMBER.getDomainValue());
        vtlComponent.setDomainValueParent(VtlType.NUMBER.getDomainValueParent());
        vtlComponent.setVtlComponentRole(VtlComponentRole.MEASURE);
        vtlComponent.setVtlDataset(vtlDataset);
        vtlComponents.add(vtlComponent);

        vtlComponent = new VtlComponent();
        vtlComponent.setName("M053");
        vtlComponent.setType(VtlType.NUMBER);
        vtlComponent.setDomainValue(VtlType.NUMBER.getDomainValue());
        vtlComponent.setDomainValueParent(VtlType.NUMBER.getDomainValueParent());
        vtlComponent.setVtlComponentRole(VtlComponentRole.MEASURE);
        vtlComponent.setVtlDataset(vtlDataset);
        vtlComponents.add(vtlComponent);

        vtlComponent = new VtlComponent();
        vtlComponent.setName("M061");
        vtlComponent.setType(VtlType.NUMBER);
        vtlComponent.setDomainValue(VtlType.NUMBER.getDomainValue());
        vtlComponent.setDomainValueParent(VtlType.NUMBER.getDomainValueParent());
        vtlComponent.setVtlComponentRole(VtlComponentRole.MEASURE);
        vtlComponent.setVtlDataset(vtlDataset);
        vtlComponents.add(vtlComponent);

        vtlComponent = new VtlComponent();
        vtlComponent.setName("M062");
        vtlComponent.setType(VtlType.NUMBER);
        vtlComponent.setDomainValue(VtlType.NUMBER.getDomainValue());
        vtlComponent.setDomainValueParent(VtlType.NUMBER.getDomainValueParent());
        vtlComponent.setVtlComponentRole(VtlComponentRole.MEASURE);
        vtlComponent.setVtlDataset(vtlDataset);
        vtlComponents.add(vtlComponent);

        vtlComponent = new VtlComponent();
        vtlComponent.setName("M063");
        vtlComponent.setType(VtlType.NUMBER);
        vtlComponent.setDomainValue(VtlType.NUMBER.getDomainValue());
        vtlComponent.setDomainValueParent(VtlType.NUMBER.getDomainValueParent());
        vtlComponent.setVtlComponentRole(VtlComponentRole.MEASURE);
        vtlComponent.setVtlDataset(vtlDataset);
        vtlComponents.add(vtlComponent);

        vtlComponent = new VtlComponent();
        vtlComponent.setName("M07");
        vtlComponent.setType(VtlType.NUMBER);
        vtlComponent.setDomainValue(VtlType.NUMBER.getDomainValue());
        vtlComponent.setDomainValueParent(VtlType.NUMBER.getDomainValueParent());
        vtlComponent.setVtlComponentRole(VtlComponentRole.MEASURE);
        vtlComponent.setVtlDataset(vtlDataset);
        vtlComponents.add(vtlComponent);

        vtlComponent = new VtlComponent();
        vtlComponent.setName("M08");
        vtlComponent.setType(VtlType.NUMBER);
        vtlComponent.setDomainValue(VtlType.NUMBER.getDomainValue());
        vtlComponent.setDomainValueParent(VtlType.NUMBER.getDomainValueParent());
        vtlComponent.setVtlComponentRole(VtlComponentRole.MEASURE);
        vtlComponent.setVtlDataset(vtlDataset);
        vtlComponents.add(vtlComponent);

        vtlComponent = new VtlComponent();
        vtlComponent.setName("M09");
        vtlComponent.setType(VtlType.NUMBER);
        vtlComponent.setDomainValue(VtlType.NUMBER.getDomainValue());
        vtlComponent.setDomainValueParent(VtlType.NUMBER.getDomainValueParent());
        vtlComponent.setVtlComponentRole(VtlComponentRole.MEASURE);
        vtlComponent.setVtlDataset(vtlDataset);
        vtlComponents.add(vtlComponent);

        vtlComponent = new VtlComponent();
        vtlComponent.setName("M10");
        vtlComponent.setType(VtlType.NUMBER);
        vtlComponent.setDomainValue(VtlType.NUMBER.getDomainValue());
        vtlComponent.setDomainValueParent(VtlType.NUMBER.getDomainValueParent());
        vtlComponent.setVtlComponentRole(VtlComponentRole.MEASURE);
        vtlComponent.setVtlDataset(vtlDataset);
        vtlComponents.add(vtlComponent);

        vtlComponent = new VtlComponent();
        vtlComponent.setName("M11");
        vtlComponent.setType(VtlType.NUMBER);
        vtlComponent.setDomainValue(VtlType.NUMBER.getDomainValue());
        vtlComponent.setDomainValueParent(VtlType.NUMBER.getDomainValueParent());
        vtlComponent.setVtlComponentRole(VtlComponentRole.MEASURE);
        vtlComponent.setVtlDataset(vtlDataset);
        vtlComponents.add(vtlComponent);

        vtlComponent = new VtlComponent();
        vtlComponent.setName("M12");
        vtlComponent.setType(VtlType.NUMBER);
        vtlComponent.setDomainValue(VtlType.NUMBER.getDomainValue());
        vtlComponent.setDomainValueParent(VtlType.NUMBER.getDomainValueParent());
        vtlComponent.setVtlComponentRole(VtlComponentRole.MEASURE);
        vtlComponent.setVtlDataset(vtlDataset);
        vtlComponents.add(vtlComponent);

        vtlComponent = new VtlComponent();
        vtlComponent.setName("M13");
        vtlComponent.setType(VtlType.NUMBER);
        vtlComponent.setDomainValue(VtlType.NUMBER.getDomainValue());
        vtlComponent.setDomainValueParent(VtlType.NUMBER.getDomainValueParent());
        vtlComponent.setVtlComponentRole(VtlComponentRole.MEASURE);
        vtlComponent.setVtlDataset(vtlDataset);
        vtlComponents.add(vtlComponent);

        vtlComponent = new VtlComponent();
        vtlComponent.setName("M14");
        vtlComponent.setType(VtlType.NUMBER);
        vtlComponent.setDomainValue(VtlType.NUMBER.getDomainValue());
        vtlComponent.setDomainValueParent(VtlType.NUMBER.getDomainValueParent());
        vtlComponent.setVtlComponentRole(VtlComponentRole.MEASURE);
        vtlComponent.setVtlDataset(vtlDataset);
        vtlComponents.add(vtlComponent);

        vtlComponent = new VtlComponent();
        vtlComponent.setName("M15");
        vtlComponent.setType(VtlType.NUMBER);
        vtlComponent.setDomainValue(VtlType.NUMBER.getDomainValue());
        vtlComponent.setDomainValueParent(VtlType.NUMBER.getDomainValueParent());
        vtlComponent.setVtlComponentRole(VtlComponentRole.MEASURE);
        vtlComponent.setVtlDataset(vtlDataset);
        vtlComponents.add(vtlComponent);

        vtlComponent = new VtlComponent();
        vtlComponent.setName("M16");
        vtlComponent.setType(VtlType.NUMBER);
        vtlComponent.setDomainValue(VtlType.NUMBER.getDomainValue());
        vtlComponent.setDomainValueParent(VtlType.NUMBER.getDomainValueParent());
        vtlComponent.setVtlComponentRole(VtlComponentRole.MEASURE);
        vtlComponent.setVtlDataset(vtlDataset);
        vtlComponents.add(vtlComponent);

        vtlComponent = new VtlComponent();
        vtlComponent.setName("M17");
        vtlComponent.setType(VtlType.NUMBER);
        vtlComponent.setDomainValue(VtlType.NUMBER.getDomainValue());
        vtlComponent.setDomainValueParent(VtlType.NUMBER.getDomainValueParent());
        vtlComponent.setVtlComponentRole(VtlComponentRole.MEASURE);
        vtlComponent.setVtlDataset(vtlDataset);
        vtlComponents.add(vtlComponent);

        vtlComponent = new VtlComponent();
        vtlComponent.setName("M18");
        vtlComponent.setType(VtlType.NUMBER);
        vtlComponent.setDomainValue(VtlType.NUMBER.getDomainValue());
        vtlComponent.setDomainValueParent(VtlType.NUMBER.getDomainValueParent());
        vtlComponent.setVtlComponentRole(VtlComponentRole.MEASURE);
        vtlComponent.setVtlDataset(vtlDataset);
        vtlComponents.add(vtlComponent);

        vtlComponent = new VtlComponent();
        vtlComponent.setName("M19");
        vtlComponent.setType(VtlType.NUMBER);
        vtlComponent.setDomainValue(VtlType.NUMBER.getDomainValue());
        vtlComponent.setDomainValueParent(VtlType.NUMBER.getDomainValueParent());
        vtlComponent.setVtlComponentRole(VtlComponentRole.MEASURE);
        vtlComponent.setVtlDataset(vtlDataset);
        vtlComponents.add(vtlComponent);

        vtlComponent = new VtlComponent();
        vtlComponent.setName("M20");
        vtlComponent.setType(VtlType.NUMBER);
        vtlComponent.setDomainValue(VtlType.NUMBER.getDomainValue());
        vtlComponent.setDomainValueParent(VtlType.NUMBER.getDomainValueParent());
        vtlComponent.setVtlComponentRole(VtlComponentRole.MEASURE);
        vtlComponent.setVtlDataset(vtlDataset);
        vtlComponents.add(vtlComponent);

        vtlComponent = new VtlComponent();
        vtlComponent.setName("M21");
        vtlComponent.setType(VtlType.NUMBER);
        vtlComponent.setDomainValue(VtlType.NUMBER.getDomainValue());
        vtlComponent.setDomainValueParent(VtlType.NUMBER.getDomainValueParent());
        vtlComponent.setVtlComponentRole(VtlComponentRole.MEASURE);
        vtlComponent.setVtlDataset(vtlDataset);
        vtlComponents.add(vtlComponent);

        vtlComponent = new VtlComponent();
        vtlComponent.setName("M22");
        vtlComponent.setType(VtlType.NUMBER);
        vtlComponent.setDomainValue(VtlType.NUMBER.getDomainValue());
        vtlComponent.setDomainValueParent(VtlType.NUMBER.getDomainValueParent());
        vtlComponent.setVtlComponentRole(VtlComponentRole.MEASURE);
        vtlComponent.setVtlDataset(vtlDataset);
        vtlComponents.add(vtlComponent);

        vtlComponent = new VtlComponent();
        vtlComponent.setName("M23");
        vtlComponent.setType(VtlType.NUMBER);
        vtlComponent.setDomainValue(VtlType.NUMBER.getDomainValue());
        vtlComponent.setDomainValueParent(VtlType.NUMBER.getDomainValueParent());
        vtlComponent.setVtlComponentRole(VtlComponentRole.MEASURE);
        vtlComponent.setVtlDataset(vtlDataset);
        vtlComponents.add(vtlComponent);

        vtlDataset.addComponentsList(vtlComponents);
        datasetRepository.save(vtlDataset);


        vtlDataset = new VtlDataset();
        vtlDataset.setPersistent(true);
        vtlDataset.setName("DS_MAP");
        vtlDataset.setIsOnlyAScalar(false);
        vtlDataset.setTransitory(false);

        vtlComponent = new VtlComponent();
        vtlComponent.setName("ISTAT_DAIRYPROD_CODES");
        vtlComponent.setType(VtlType.STRING);
        vtlComponent.setDomainValue(VtlType.STRING.getDomainValue());
        vtlComponent.setDomainValueParent(VtlType.STRING.getDomainValueParent());
        vtlComponent.setVtlComponentRole(VtlComponentRole.IDENTIFIER);
        vtlDataset.addComponent(vtlComponent);

        vtlComponent = new VtlComponent();
        vtlComponent.setName("DESCRIPTION");
        vtlComponent.setType(VtlType.STRING);
        vtlComponent.setDomainValue(VtlType.STRING.getDomainValue());
        vtlComponent.setDomainValueParent(VtlType.STRING.getDomainValueParent());
        vtlComponent.setVtlComponentRole(VtlComponentRole.MEASURE);
        vtlDataset.addComponent(vtlComponent);

        vtlComponent = new VtlComponent();
        vtlComponent.setName("DIM_CL_H_DAIRYPROD_A");
        vtlComponent.setType(VtlType.STRING);
        vtlComponent.setDomainValue(VtlType.STRING.getDomainValue());
        vtlComponent.setDomainValueParent(VtlType.STRING.getDomainValueParent());
        vtlComponent.setVtlComponentRole(VtlComponentRole.MEASURE);
        vtlDataset.addComponent(vtlComponent);

        datasetRepository.save(vtlDataset);

        vtlDataset = new VtlDataset();
        vtlDataset.setPersistent(true);
        vtlDataset.setName("DS_MANDATORY");
        vtlDataset.setIsOnlyAScalar(false);
        vtlDataset.setTransitory(false);

        vtlComponent = new VtlComponent();
        vtlComponent.setName("DIM_CL_H_DAIRYPROD_A");
        vtlComponent.setType(VtlType.STRING);
        vtlComponent.setDomainValue(VtlType.STRING.getDomainValue());
        vtlComponent.setDomainValueParent(VtlType.STRING.getDomainValueParent());
        vtlComponent.setVtlComponentRole(VtlComponentRole.IDENTIFIER);
        vtlDataset.addComponent(vtlComponent);

        datasetRepository.save(vtlDataset);

        //Annual Milk

        vtlDataset = new VtlDataset();
        vtlDataset.setPersistent(true);
        vtlDataset.setName("DS_MANDATORY_m");
        vtlDataset.setIsOnlyAScalar(false);
        vtlDataset.setTransitory(false);

        vtlComponent = new VtlComponent();
        vtlComponent.setName(DIM_CL_H_DAIRYPROD_BHREV);
        vtlComponent.setType(VtlType.STRING);
        vtlComponent.setDomainValue(VtlType.STRING.getDomainValue());
        vtlComponent.setDomainValueParent(VtlType.STRING.getDomainValueParent());
        vtlComponent.setVtlComponentRole(VtlComponentRole.IDENTIFIER);
        vtlDataset.addComponent(vtlComponent);

        vtlComponent = new VtlComponent();
        vtlComponent.setName("DIM_CL_H_MILKITEM_BH");
        vtlComponent.setType(VtlType.STRING);
        vtlComponent.setDomainValue(VtlType.STRING.getDomainValue());
        vtlComponent.setDomainValueParent(VtlType.STRING.getDomainValueParent());
        vtlComponent.setVtlComponentRole(VtlComponentRole.IDENTIFIER);
        vtlDataset.addComponent(vtlComponent);

        datasetRepository.save(vtlDataset);

        vtlDataset = new VtlDataset();
        vtlDataset.setPersistent(true);
        vtlDataset.setName("DS_micro_m");
        vtlDataset.setIsOnlyAScalar(false);
        vtlDataset.setTransitory(false);
        vtlComponents = new ArrayList<>();

        vtlComponent = new VtlComponent();
        vtlComponent.setName("YEAR");
        vtlComponent.setType(VtlType.STRING);
        vtlComponent.setDomainValue(VtlType.STRING.getDomainValue());
        vtlComponent.setDomainValueParent(VtlType.STRING.getDomainValueParent());
        vtlComponent.setVtlComponentRole(VtlComponentRole.IDENTIFIER);
        vtlComponent.setVtlDataset(vtlDataset);
        vtlComponents.add(vtlComponent);

        vtlComponent = new VtlComponent();
        vtlComponent.setName(IDENT);
        vtlComponent.setType(VtlType.STRING);
        vtlComponent.setDomainValue(VtlType.STRING.getDomainValue());
        vtlComponent.setDomainValueParent(VtlType.STRING.getDomainValueParent());
        vtlComponent.setVtlComponentRole(VtlComponentRole.IDENTIFIER);
        vtlComponent.setVtlDataset(vtlDataset);
        vtlComponents.add(vtlComponent);

        vtlComponent = new VtlComponent();
        vtlComponent.setName("IT_REG");
        vtlComponent.setType(VtlType.STRING);
        vtlComponent.setDomainValue(VtlType.STRING.getDomainValue());
        vtlComponent.setDomainValueParent(VtlType.STRING.getDomainValueParent());
        vtlComponent.setVtlComponentRole(VtlComponentRole.IDENTIFIER);
        vtlComponent.setVtlDataset(vtlDataset);
        vtlComponents.add(vtlComponent);

        vtlComponent = new VtlComponent();
        vtlComponent.setName("TYPE_ENT");
        vtlComponent.setType(VtlType.STRING);
        vtlComponent.setDomainValue(VtlType.STRING.getDomainValue());
        vtlComponent.setDomainValueParent(VtlType.STRING.getDomainValueParent());
        vtlComponent.setVtlComponentRole(VtlComponentRole.IDENTIFIER);
        vtlComponent.setVtlDataset(vtlDataset);
        vtlComponents.add(vtlComponent);

        vtlComponent = new VtlComponent();
        vtlComponent.setName("MAT_PROD");
        vtlComponent.setType(VtlType.STRING);
        vtlComponent.setDomainValue(VtlType.STRING.getDomainValue());
        vtlComponent.setDomainValueParent(VtlType.STRING.getDomainValueParent());
        vtlComponent.setVtlComponentRole(VtlComponentRole.IDENTIFIER);
        vtlComponent.setVtlDataset(vtlDataset);
        vtlComponents.add(vtlComponent);

        vtlComponent = new VtlComponent();
        vtlComponent.setName("QUANTITY");
        vtlComponent.setType(VtlType.NUMBER);
        vtlComponent.setDomainValue(VtlType.NUMBER.getDomainValue());
        vtlComponent.setDomainValueParent(VtlType.NUMBER.getDomainValueParent());
        vtlComponent.setVtlComponentRole(VtlComponentRole.MEASURE);
        vtlComponent.setVtlDataset(vtlDataset);
        vtlComponents.add(vtlComponent);

        vtlComponent = new VtlComponent();
        vtlComponent.setName("FAT_DRY");
        vtlComponent.setType(VtlType.NUMBER);
        vtlComponent.setDomainValue(VtlType.NUMBER.getDomainValue());
        vtlComponent.setDomainValueParent(VtlType.NUMBER.getDomainValueParent());
        vtlComponent.setVtlComponentRole(VtlComponentRole.MEASURE);
        vtlComponent.setVtlDataset(vtlDataset);
        vtlComponents.add(vtlComponent);

        vtlComponent = new VtlComponent();
        vtlComponent.setName("FAT_ITIS");
        vtlComponent.setType(VtlType.NUMBER);
        vtlComponent.setDomainValue(VtlType.NUMBER.getDomainValue());
        vtlComponent.setDomainValueParent(VtlType.NUMBER.getDomainValueParent());
        vtlComponent.setVtlComponentRole(VtlComponentRole.MEASURE);
        vtlComponent.setVtlDataset(vtlDataset);
        vtlComponents.add(vtlComponent);

        vtlComponent = new VtlComponent();
        vtlComponent.setName("PROTEIN");
        vtlComponent.setType(VtlType.NUMBER);
        vtlComponent.setDomainValue(VtlType.NUMBER.getDomainValue());
        vtlComponent.setDomainValueParent(VtlType.NUMBER.getDomainValueParent());
        vtlComponent.setVtlComponentRole(VtlComponentRole.MEASURE);
        vtlComponent.setVtlDataset(vtlDataset);
        vtlComponents.add(vtlComponent);

        vtlDataset.addComponentsList(vtlComponents);
        datasetRepository.save(vtlDataset);

        vtlDataset = new VtlDataset();
        vtlDataset.setPersistent(true);
        vtlDataset.setName("MILK_TABLEA_M");
        vtlDataset.setIsOnlyAScalar(false);
        vtlDataset.setTransitory(false);
        vtlComponents = new ArrayList<>();

        vtlComponent = new VtlComponent();
        vtlComponent.setName("FREQ");
        vtlComponent.setType(VtlType.STRING);
        vtlComponent.setDomainValue(VtlType.STRING.getDomainValue());
        vtlComponent.setDomainValueParent(VtlType.STRING.getDomainValueParent());
        vtlComponent.setVtlComponentRole(VtlComponentRole.IDENTIFIER);
        vtlComponent.setVtlDataset(vtlDataset);
        vtlComponents.add(vtlComponent);

        vtlComponent = new VtlComponent();
        vtlComponent.setName("REF_AREA");
        vtlComponent.setType(VtlType.STRING);
        vtlComponent.setDomainValue(VtlType.STRING.getDomainValue());
        vtlComponent.setDomainValueParent(VtlType.STRING.getDomainValueParent());
        vtlComponent.setVtlComponentRole(VtlComponentRole.IDENTIFIER);
        vtlComponent.setVtlDataset(vtlDataset);
        vtlComponents.add(vtlComponent);

        vtlComponent = new VtlComponent();
        vtlComponent.setName("DIM_CL_H_MILKITEM_BH");
        vtlComponent.setType(VtlType.STRING);
        vtlComponent.setDomainValue(VtlType.STRING.getDomainValue());
        vtlComponent.setDomainValueParent(VtlType.STRING.getDomainValueParent());
        vtlComponent.setVtlComponentRole(VtlComponentRole.IDENTIFIER);
        vtlComponent.setVtlDataset(vtlDataset);
        vtlComponents.add(vtlComponent);

        vtlComponent = new VtlComponent();
        vtlComponent.setName(DIM_CL_H_DAIRYPROD_BHREV);
        vtlComponent.setType(VtlType.STRING);
        vtlComponent.setDomainValue(VtlType.STRING.getDomainValue());
        vtlComponent.setDomainValueParent(VtlType.STRING.getDomainValueParent());
        vtlComponent.setVtlComponentRole(VtlComponentRole.IDENTIFIER);
        vtlComponent.setVtlDataset(vtlDataset);
        vtlComponents.add(vtlComponent);

        vtlComponent = new VtlComponent();
        vtlComponent.setName("TIME_PERIOD");
        vtlComponent.setType(VtlType.STRING);
        vtlComponent.setDomainValue(VtlType.STRING.getDomainValue());
        vtlComponent.setDomainValueParent(VtlType.STRING.getDomainValueParent());
        vtlComponent.setVtlComponentRole(VtlComponentRole.IDENTIFIER);
        vtlComponent.setVtlDataset(vtlDataset);
        vtlComponents.add(vtlComponent);

        vtlComponent = new VtlComponent();
        vtlComponent.setName(OBS_VALUE);
        vtlComponent.setType(VtlType.NUMBER);
        vtlComponent.setDomainValue(VtlType.NUMBER.getDomainValue());
        vtlComponent.setDomainValueParent(VtlType.NUMBER.getDomainValueParent());
        vtlComponent.setVtlComponentRole(VtlComponentRole.MEASURE);
        vtlComponent.setVtlDataset(vtlDataset);
        vtlComponents.add(vtlComponent);

        vtlDataset.addComponentsList(vtlComponents);
        datasetRepository.save(vtlDataset);

        vtlDataset = new VtlDataset();
        vtlDataset.setPersistent(true);
        vtlDataset.setName("DS_MAP_m");
        vtlDataset.setIsOnlyAScalar(false);
        vtlDataset.setTransitory(false);

        vtlComponent = new VtlComponent();
        vtlComponent.setName("MAT_PROD");
        vtlComponent.setType(VtlType.STRING);
        vtlComponent.setDomainValue(VtlType.STRING.getDomainValue());
        vtlComponent.setDomainValueParent(VtlType.STRING.getDomainValueParent());
        vtlComponent.setVtlComponentRole(VtlComponentRole.IDENTIFIER);
        vtlDataset.addComponent(vtlComponent);

        vtlComponent = new VtlComponent();
        vtlComponent.setName("DESCRIPTION");
        vtlComponent.setType(VtlType.STRING);
        vtlComponent.setDomainValue(VtlType.STRING.getDomainValue());
        vtlComponent.setDomainValueParent(VtlType.STRING.getDomainValueParent());
        vtlComponent.setVtlComponentRole(VtlComponentRole.MEASURE);
        vtlDataset.addComponent(vtlComponent);

        vtlComponent = new VtlComponent();
        vtlComponent.setName(DIM_CL_H_DAIRYPROD_BHREV);
        vtlComponent.setType(VtlType.STRING);
        vtlComponent.setDomainValue(VtlType.STRING.getDomainValue());
        vtlComponent.setDomainValueParent(VtlType.STRING.getDomainValueParent());
        vtlComponent.setVtlComponentRole(VtlComponentRole.MEASURE);
        vtlDataset.addComponent(vtlComponent);

        datasetRepository.save(vtlDataset);


        valueDomain = new ValueDomain();
        valueDomain.setCode("A1");
        valueDomain.setValueDomainName(VD_ID_1);
        valueDomain.setDescription("Code A1");
        valueDomainRepository.save(valueDomain);
        valueDomain = new ValueDomain();
        valueDomain.setCode("A2");
        valueDomain.setValueDomainName(VD_ID_1);
        valueDomain.setDescription("Code A2");
        valueDomainRepository.save(valueDomain);
        valueDomain = new ValueDomain();
        valueDomain.setCode("A3");
        valueDomain.setValueDomainName(VD_ID_1);
        valueDomain.setDescription("Code A3");
        valueDomainRepository.save(valueDomain);

        valueDomain = new ValueDomain();
        valueDomain.setCode("B1");
        valueDomain.setValueDomainName(VD_ID_2);
        valueDomain.setDescription("Code B1");
        valueDomainRepository.save(valueDomain);
        valueDomain = new ValueDomain();
        valueDomain.setCode("B2");
        valueDomain.setValueDomainName(VD_ID_2);
        valueDomain.setDescription("Code B2");
        valueDomainRepository.save(valueDomain);

        valueDomain = new ValueDomain();
        valueDomain.setCode("C1");
        valueDomain.setValueDomainName(VD_AT_2);
        valueDomain.setDescription("Code C1");
        valueDomainRepository.save(valueDomain);
        valueDomain = new ValueDomain();
        valueDomain.setCode("C2");
        valueDomain.setValueDomainName(VD_AT_2);
        valueDomain.setDescription("Code C2");
        valueDomainRepository.save(valueDomain);


        vtlDataset = new VtlDataset();
        vtlDataset.setPersistent(true);
        vtlDataset.setName("DS_r_no_obs_no_time");
        vtlDataset.setIsOnlyAScalar(false);
        vtlDataset.setTransitory(false);

        vtlComponent = new VtlComponent();
        vtlComponent.setName("ID_1");
        vtlComponent.setType(VtlType.STRING);
        vtlComponent.setDomainValue(VD_ID_1);
        vtlComponent.setDomainValueParent(VD_ID_1);
        vtlComponent.setVtlComponentRole(VtlComponentRole.IDENTIFIER);
        vtlDataset.addComponent(vtlComponent);

        vtlComponent = new VtlComponent();
        vtlComponent.setName("ID_2");
        vtlComponent.setType(VtlType.STRING);
        vtlComponent.setDomainValue(VD_ID_2);
        vtlComponent.setDomainValueParent(VD_ID_2);
        vtlComponent.setVtlComponentRole(VtlComponentRole.IDENTIFIER);
        vtlDataset.addComponent(vtlComponent);

        vtlComponent = new VtlComponent();
        vtlComponent.setName("ID_3");
        vtlComponent.setType(VtlType.STRING);
        vtlComponent.setDomainValue(VtlType.STRING.getDomainValue());
        vtlComponent.setDomainValueParent(VtlType.STRING.getDomainValue());
        vtlComponent.setVtlComponentRole(VtlComponentRole.IDENTIFIER);
        vtlDataset.addComponent(vtlComponent);

        vtlComponent = new VtlComponent();
        vtlComponent.setName("ME_1");
        vtlComponent.setType(VtlType.STRING);
        vtlComponent.setDomainValue(VtlType.STRING.getDomainValue());
        vtlComponent.setDomainValueParent(VtlType.STRING.getDomainValue());
        vtlComponent.setVtlComponentRole(VtlComponentRole.MEASURE);
        vtlDataset.addComponent(vtlComponent);

        vtlComponent = new VtlComponent();
        vtlComponent.setName("ME_2");
        vtlComponent.setType(VtlType.NUMBER);
        vtlComponent.setDomainValue(VtlType.NUMBER.getDomainValue());
        vtlComponent.setDomainValueParent(VtlType.NUMBER.getDomainValue());
        vtlComponent.setVtlComponentRole(VtlComponentRole.MEASURE);
        vtlDataset.addComponent(vtlComponent);

        vtlComponent = new VtlComponent();
        vtlComponent.setName("AT_1");
        vtlComponent.setType(VtlType.STRING);
        vtlComponent.setDomainValue(VtlType.STRING.getDomainValue());
        vtlComponent.setDomainValueParent(VtlType.STRING.getDomainValue());
        vtlComponent.setVtlComponentRole(VtlComponentRole.VIRAL);
        vtlDataset.addComponent(vtlComponent);

        vtlComponent = new VtlComponent();
        vtlComponent.setName("AT_2");
        vtlComponent.setType(VtlType.STRING);
        vtlComponent.setDomainValue(VD_AT_2);
        vtlComponent.setDomainValueParent(VD_AT_2);
        vtlComponent.setVtlComponentRole(VtlComponentRole.ATTRIBUTE);
        vtlDataset.addComponent(vtlComponent);

        datasetRepository.save(vtlDataset);


        vtlDataset = new VtlDataset();
        vtlDataset.setPersistent(true);
        vtlDataset.setName("DS_r_si_obs_no_time");
        vtlDataset.setIsOnlyAScalar(false);
        vtlDataset.setTransitory(false);

        vtlComponent = new VtlComponent();
        vtlComponent.setName("ID_1");
        vtlComponent.setType(VtlType.STRING);
        vtlComponent.setDomainValue(VD_ID_1);
        vtlComponent.setDomainValueParent(VD_ID_1);
        vtlComponent.setVtlComponentRole(VtlComponentRole.IDENTIFIER);
        vtlDataset.addComponent(vtlComponent);

        vtlComponent = new VtlComponent();
        vtlComponent.setName("ID_2");
        vtlComponent.setType(VtlType.STRING);
        vtlComponent.setDomainValue(VD_ID_2);
        vtlComponent.setDomainValueParent(VD_ID_2);
        vtlComponent.setVtlComponentRole(VtlComponentRole.IDENTIFIER);
        vtlDataset.addComponent(vtlComponent);

        vtlComponent = new VtlComponent();
        vtlComponent.setName("ID_3");
        vtlComponent.setType(VtlType.STRING);
        vtlComponent.setDomainValue(VtlType.STRING.getDomainValue());
        vtlComponent.setDomainValueParent(VtlType.STRING.getDomainValue());
        vtlComponent.setVtlComponentRole(VtlComponentRole.IDENTIFIER);
        vtlDataset.addComponent(vtlComponent);

        vtlComponent = new VtlComponent();
        vtlComponent.setName("ME_1");
        vtlComponent.setType(VtlType.STRING);
        vtlComponent.setDomainValue(VtlType.STRING.getDomainValue());
        vtlComponent.setDomainValueParent(VtlType.STRING.getDomainValue());
        vtlComponent.setVtlComponentRole(VtlComponentRole.MEASURE);
        vtlDataset.addComponent(vtlComponent);

        vtlComponent = new VtlComponent();
        vtlComponent.setName("ME_2");
        vtlComponent.setType(VtlType.NUMBER);
        vtlComponent.setDomainValue(VtlType.NUMBER.getDomainValue());
        vtlComponent.setDomainValueParent(VtlType.NUMBER.getDomainValue());
        vtlComponent.setVtlComponentRole(VtlComponentRole.MEASURE);
        vtlDataset.addComponent(vtlComponent);

        vtlComponent = new VtlComponent();
        vtlComponent.setName(OBS_VALUE);
        vtlComponent.setType(VtlType.NUMBER);
        vtlComponent.setDomainValue(VtlType.NUMBER.getDomainValue());
        vtlComponent.setDomainValueParent(VtlType.NUMBER.getDomainValue());
        vtlComponent.setVtlComponentRole(VtlComponentRole.MEASURE);
        vtlDataset.addComponent(vtlComponent);

        vtlComponent = new VtlComponent();
        vtlComponent.setName("AT_1");
        vtlComponent.setType(VtlType.STRING);
        vtlComponent.setDomainValue(VtlType.STRING.getDomainValue());
        vtlComponent.setDomainValueParent(VtlType.STRING.getDomainValue());
        vtlComponent.setVtlComponentRole(VtlComponentRole.VIRAL);
        vtlDataset.addComponent(vtlComponent);

        vtlComponent = new VtlComponent();
        vtlComponent.setName("AT_2");
        vtlComponent.setType(VtlType.STRING);
        vtlComponent.setDomainValue(VD_AT_2);
        vtlComponent.setDomainValueParent(VD_AT_2);
        vtlComponent.setVtlComponentRole(VtlComponentRole.ATTRIBUTE);
        vtlDataset.addComponent(vtlComponent);

        datasetRepository.save(vtlDataset);

        vtlDataset = new VtlDataset();
        vtlDataset.setPersistent(true);
        vtlDataset.setName("DS_r_si_obs_si_time");
        vtlDataset.setIsOnlyAScalar(false);
        vtlDataset.setTransitory(false);

        vtlComponent = new VtlComponent();
        vtlComponent.setName("ID_1");
        vtlComponent.setType(VtlType.STRING);
        vtlComponent.setDomainValue(VD_ID_1);
        vtlComponent.setDomainValueParent(VD_ID_1);
        vtlComponent.setVtlComponentRole(VtlComponentRole.IDENTIFIER);
        vtlDataset.addComponent(vtlComponent);

        vtlComponent = new VtlComponent();
        vtlComponent.setName("ID_2");
        vtlComponent.setType(VtlType.STRING);
        vtlComponent.setDomainValue(VD_ID_2);
        vtlComponent.setDomainValueParent(VD_ID_2);
        vtlComponent.setVtlComponentRole(VtlComponentRole.IDENTIFIER);
        vtlDataset.addComponent(vtlComponent);

        vtlComponent = new VtlComponent();
        vtlComponent.setName("ID_3");
        vtlComponent.setType(VtlType.STRING);
        vtlComponent.setDomainValue(VtlType.STRING.getDomainValue());
        vtlComponent.setDomainValueParent(VtlType.STRING.getDomainValue());
        vtlComponent.setVtlComponentRole(VtlComponentRole.IDENTIFIER);
        vtlDataset.addComponent(vtlComponent);

        vtlComponent = new VtlComponent();
        vtlComponent.setName("TIME_PERIOD");
        vtlComponent.setType(VtlType.STRING);
        vtlComponent.setDomainValue(VtlType.STRING.getDomainValue());
        vtlComponent.setDomainValueParent(VtlType.STRING.getDomainValue());
        vtlComponent.setVtlComponentRole(VtlComponentRole.IDENTIFIER);
        vtlDataset.addComponent(vtlComponent);


        vtlComponent = new VtlComponent();
        vtlComponent.setName("ME_1");
        vtlComponent.setType(VtlType.STRING);
        vtlComponent.setDomainValue(VtlType.STRING.getDomainValue());
        vtlComponent.setDomainValueParent(VtlType.STRING.getDomainValue());
        vtlComponent.setVtlComponentRole(VtlComponentRole.MEASURE);
        vtlDataset.addComponent(vtlComponent);

        vtlComponent = new VtlComponent();
        vtlComponent.setName("ME_2");
        vtlComponent.setType(VtlType.NUMBER);
        vtlComponent.setDomainValue(VtlType.NUMBER.getDomainValue());
        vtlComponent.setDomainValueParent(VtlType.NUMBER.getDomainValue());
        vtlComponent.setVtlComponentRole(VtlComponentRole.MEASURE);
        vtlDataset.addComponent(vtlComponent);

        vtlComponent = new VtlComponent();
        vtlComponent.setName(OBS_VALUE);
        vtlComponent.setType(VtlType.NUMBER);
        vtlComponent.setDomainValue(VtlType.NUMBER.getDomainValue());
        vtlComponent.setDomainValueParent(VtlType.NUMBER.getDomainValue());
        vtlComponent.setVtlComponentRole(VtlComponentRole.MEASURE);
        vtlDataset.addComponent(vtlComponent);

        vtlComponent = new VtlComponent();
        vtlComponent.setName("AT_1");
        vtlComponent.setType(VtlType.STRING);
        vtlComponent.setDomainValue(VtlType.STRING.getDomainValue());
        vtlComponent.setDomainValueParent(VtlType.STRING.getDomainValue());
        vtlComponent.setVtlComponentRole(VtlComponentRole.VIRAL);
        vtlDataset.addComponent(vtlComponent);

        vtlComponent = new VtlComponent();
        vtlComponent.setName("AT_2");
        vtlComponent.setType(VtlType.STRING);
        vtlComponent.setDomainValue(VD_AT_2);
        vtlComponent.setDomainValueParent(VD_AT_2);
        vtlComponent.setVtlComponentRole(VtlComponentRole.ATTRIBUTE);
        vtlDataset.addComponent(vtlComponent);

        datasetRepository.save(vtlDataset);

        vtlDataset = new VtlDataset();
        vtlDataset.setPersistent(true);
        vtlDataset.setName("ds_no_meas_1");
        vtlDataset.setIsOnlyAScalar(false);
        vtlDataset.setTransitory(false);

        vtlComponent = new VtlComponent();
        vtlComponent.setName("ID_1");
        vtlComponent.setType(VtlType.STRING);
        vtlComponent.setDomainValue(VD_ID_1);
        vtlComponent.setDomainValueParent(VD_ID_1);
        vtlComponent.setVtlComponentRole(VtlComponentRole.IDENTIFIER);
        vtlDataset.addComponent(vtlComponent);

        vtlComponent = new VtlComponent();
        vtlComponent.setName("ID_2");
        vtlComponent.setType(VtlType.STRING);
        vtlComponent.setDomainValue(VD_ID_2);
        vtlComponent.setDomainValueParent(VD_ID_2);
        vtlComponent.setVtlComponentRole(VtlComponentRole.IDENTIFIER);
        vtlDataset.addComponent(vtlComponent);

        vtlComponent = new VtlComponent();
        vtlComponent.setName("ID_3");
        vtlComponent.setType(VtlType.STRING);
        vtlComponent.setDomainValue(VtlType.STRING.getDomainValue());
        vtlComponent.setDomainValueParent(VtlType.STRING.getDomainValue());
        vtlComponent.setVtlComponentRole(VtlComponentRole.IDENTIFIER);
        vtlDataset.addComponent(vtlComponent);

        datasetRepository.save(vtlDataset);

        vtlDataset = new VtlDataset();
        vtlDataset.setPersistent(true);
        vtlDataset.setName("ds_no_meas_2");
        vtlDataset.setIsOnlyAScalar(false);
        vtlDataset.setTransitory(false);

        vtlComponent = new VtlComponent();
        vtlComponent.setName("ID_1");
        vtlComponent.setType(VtlType.STRING);
        vtlComponent.setDomainValue(VD_ID_1);
        vtlComponent.setDomainValueParent(VD_ID_1);
        vtlComponent.setVtlComponentRole(VtlComponentRole.IDENTIFIER);
        vtlDataset.addComponent(vtlComponent);

        vtlComponent = new VtlComponent();
        vtlComponent.setName("ID_2");
        vtlComponent.setType(VtlType.STRING);
        vtlComponent.setDomainValue(VD_ID_2);
        vtlComponent.setDomainValueParent(VD_ID_2);
        vtlComponent.setVtlComponentRole(VtlComponentRole.IDENTIFIER);
        vtlDataset.addComponent(vtlComponent);

        vtlComponent = new VtlComponent();
        vtlComponent.setName("ID_3");
        vtlComponent.setType(VtlType.STRING);
        vtlComponent.setDomainValue(VtlType.STRING.getDomainValue());
        vtlComponent.setDomainValueParent(VtlType.STRING.getDomainValue());
        vtlComponent.setVtlComponentRole(VtlComponentRole.IDENTIFIER);
        vtlDataset.addComponent(vtlComponent);
        datasetRepository.save(vtlDataset);

        vtlDataset = new VtlDataset();
        vtlDataset.setPersistent(true);
        vtlDataset.setName("77_47__IT1__1.1");
        vtlDataset.setIsOnlyAScalar(false);
        vtlDataset.setTransitory(false);

        vtlComponent = new VtlComponent();
        vtlComponent.setName("ID_1");
        vtlComponent.setType(VtlType.STRING);
        vtlComponent.setDomainValue(VD_ID_1);
        vtlComponent.setDomainValueParent(VD_ID_1);
        vtlComponent.setVtlComponentRole(VtlComponentRole.IDENTIFIER);
        vtlDataset.addComponent(vtlComponent);
        datasetRepository.save(vtlDataset);


        return true;
    }

    /**
     * Il metodo prende in ingresso un vtlDataset e lo salva nel database interno dell'applicazione per renderlo utilizzabile
     * da tutti i motori dell'applicazione
     *
     * @param vtlDataset il dataset che deve essere salvato sul database interno
     * @return il dataset salvato
     */
    public VtlDataset createVtlDataset(VtlDataset vtlDataset) {
        if (vtlDataset.getName() == null || vtlDataset.getName().isEmpty())
            return null;
        for (VtlComponent vtlComponent : vtlDataset.getVtlComponents()) {
            if (vtlComponent.getType() == null || vtlComponent.getName() == null || vtlComponent.getVtlComponentRole() == null)
                return null;
            if (vtlComponent.getDomainValue() == null)
                vtlComponent.setDomainValue(vtlComponent.getType().getDomainValue());
            if (vtlComponent.getDomainValueParent() == null)
                vtlComponent.setDomainValueParent(vtlComponent.getType().getDomainValueParent());

        }
        datasetRepository.save(vtlDataset);
        return vtlDataset;
    }

    /**
     * Il metodo prende in ingresso un dataset e una descrizione dei componenti sotto forma di stringa e
     * li salva sul database interno dell'applicazione. Questo metodo viene utilizzato per interpretare la
     * modalità alternativa di immissione dataset
     *
     * @param vtlDataset            il dataset che devee essere salvato sul database interno
     * @param componentDescriptions la stringa che descrive i components. le diverse righe della stringa
     *                              sono separate dal punto e virgola e i diversi elementi della riga sono
     *                              divisi dalla virgola
     * @return il dataset salvato
     */
    public VtlDataset createVtlDatasetByString(VtlDataset vtlDataset, String componentDescriptions) {
        if (vtlDataset.getName() == null || vtlDataset.getName().isEmpty())
            return null;
        String[] componentDescriptionsSplitted = componentDescriptions.split(";");
        for (String componentDescription : componentDescriptionsSplitted) {
            String[] componentFields = componentDescription.split(",");
            vtlDataset.addComponent(
                    getVtlComponent(componentFields[0], componentFields[1], componentFields[2])
            );
        }
        datasetRepository.save(vtlDataset);
        return vtlDataset;
    }

    /**
     * il metodo prende in ingresso i dati del componente e genera un VtlComponent che può essere associato a
     * un dataset
     *
     * @param componentName il nome del componente
     * @param componentType il tipo del componente(stringa,number, integer, eccetera)
     * @param componentRole il ruolo del componente(identifier, measure, ecc)
     * @return il componente generato dalle informazioni inserite
     */
    public VtlComponent getVtlComponent(String componentName, String componentType, String componentRole) {
        VtlType vtlType = VtlType.valueOf(componentType.trim().toUpperCase());
        VtlComponentRole vtlComponentRole = VtlComponentRole.valueOf(componentRole.trim().toUpperCase());
        VtlComponent vtlComponent = new VtlComponent();
        vtlComponent.setName(componentName.trim().toUpperCase());
        vtlComponent.setType(vtlType);
        vtlComponent.setDomainValue(vtlType.getDomainValue());
        vtlComponent.setDomainValueParent(vtlType.getDomainValueParent());
        vtlComponent.setVtlComponentRole(vtlComponentRole);
        return vtlComponent;
    }

    /**
     * il metodo cancella tutti i dataset legati a un determinato requestUuid
     *
     * @param requestUuid la requestUuid che si vuole cancellare
     * @return il numero dei dataset cancellati
     */
    public Integer deleteAllByRequestUuid(UUID requestUuid) {
        return datasetRepository.deleteAllByRequestUuid(requestUuid);
    }

}
