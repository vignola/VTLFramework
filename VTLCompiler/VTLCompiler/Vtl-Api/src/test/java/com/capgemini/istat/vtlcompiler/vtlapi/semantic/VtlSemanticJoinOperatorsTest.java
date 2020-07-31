package com.capgemini.istat.vtlcompiler.vtlapi.semantic;

import com.capgemini.istat.vtlcompiler.vtlapi.utils.VtlTestUtils;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.dataset.VtlComponent;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.dataset.VtlComponentRole;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.dataset.VtlDataset;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.dataset.VtlType;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.semantic.ResultExpression;
import com.capgemini.istat.vtlcompiler.vtlcommon.repository.DatasetRepository;
import com.capgemini.istat.vtlcompiler.vtlsemantic.service.SemanticService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest

public class VtlSemanticJoinOperatorsTest {

    private VtlTestUtils vtlTestUtils;
	private DatasetRepository datasetRepository;
    public static final Logger logger = LogManager.getLogger(SemanticService.class);

	@Autowired
	public void setVtlTestUtils(VtlTestUtils vtlTestUtils) {
		this.vtlTestUtils = vtlTestUtils;
	}

	@Autowired
	public void setDatasetRepository(DatasetRepository datasetRepository) {
		this.datasetRepository = datasetRepository;
	}
    //se i nomi di dataset sono ripetuti e per questi non è stato utilizzato un alias ( es inner_join (ds1,ds1) restituisce un errore;
    //inner_join(ds1,ds1 as ds2) non restituisce errori)

    @Test
    @Transactional
    public void innerJoin() throws Exception {
        VtlDataset vtlDatasetFind = datasetRepository.findByNameIgnoreCase("DS_1");
        if (vtlDatasetFind == null) {
            VtlDataset vtlDataset = new VtlDataset();

            vtlDataset.setPersistent(true);
            vtlDataset.setName("DS_4");
            vtlDataset.setIsOnlyAScalar(false);
            vtlDataset.setTransitory(false);

            List<VtlComponent> vtlComponents = new ArrayList<VtlComponent>();
            VtlComponent vtlComponent;
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

            vtlDataset.addComponentsList(vtlComponents);
            datasetRepository.save(vtlDataset);
        }
        String commandStatements = "DS_r  := inner_join ( DS_4, DS_4);";

        try {
            List<ResultExpression> result = vtlTestUtils.getResult(commandStatements);
            assertTrue("contollo semantico fallito", false);

        } catch (Exception exception) {

            logger.info(exception);

        }

    }

    /***se i nomi di dataset sono ripetuti e per questi non è stato utilizzato un alias ( es inner_join (ds1,ds1) restituisce un errore;
     inner_join(ds1,ds1 as ds2) non restituisce errori)*/

    @Test
    @Transactional
    public void leftJoin() throws Exception {
        VtlDataset vtlDatasetFind = datasetRepository.findByNameIgnoreCase("DS_1");
        if (vtlDatasetFind == null) {
            VtlDataset vtlDataset = new VtlDataset();

            vtlDataset.setPersistent(true);
            vtlDataset.setName("DS_4");
            vtlDataset.setIsOnlyAScalar(false);
            vtlDataset.setTransitory(false);

            List<VtlComponent> vtlComponents = new ArrayList<VtlComponent>();
            VtlComponent vtlComponent;
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

            vtlDataset.addComponentsList(vtlComponents);
            datasetRepository.save(vtlDataset);
        }
        String commandStatements = "DS_r  := left_join ( DS_4, DS_4);";

        try {
            List<ResultExpression> result = vtlTestUtils.getResult(commandStatements);
            assertTrue("contollo semantico fallito", false);

        } catch (Exception exception) {

            logger.info(exception);

        }

    }

    /***	se i nomi di dataset sono ripetuti e per questi non è stato utilizzato un alias ( es inner_join (ds1,ds1) restituisce un errore;
     inner_join(ds1,ds1 as ds2) non restituisce errori) */

    @Test
    @Transactional
    public void fullJoin() throws Exception {
        VtlDataset vtlDatasetFind = datasetRepository.findByNameIgnoreCase("DS_1");
        if (vtlDatasetFind == null) {
            VtlDataset vtlDataset = new VtlDataset();

            vtlDataset.setPersistent(true);
            vtlDataset.setName("DS_4");
            vtlDataset.setIsOnlyAScalar(false);
            vtlDataset.setTransitory(false);

            List<VtlComponent> vtlComponents = new ArrayList<VtlComponent>();
            VtlComponent vtlComponent;
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

            vtlDataset.addComponentsList(vtlComponents);
            datasetRepository.save(vtlDataset);
        }
        String commandStatements = "DS_r  := full_join ( DS_4, DS_4);";

        try {
            List<ResultExpression> result = vtlTestUtils.getResult(commandStatements);
            assertTrue("contollo semantico fallito", false);

        } catch (Exception exception) {

            logger.info(exception);

        }

    }

    /***se i nomi di dataset sono ripetuti e per questi non è stato utilizzato un alias ( es inner_join (ds1,ds1) restituisce un errore;
     inner_join(ds1,ds1 as ds2) non restituisce errori)*/

    @Test
    @Transactional
    public void crossJoin() throws Exception {
        VtlDataset vtlDatasetFind = datasetRepository.findByNameIgnoreCase("DS_1");
        if (vtlDatasetFind == null) {
            VtlDataset vtlDataset = new VtlDataset();

            vtlDataset.setPersistent(true);
            vtlDataset.setName("DS_4");
            vtlDataset.setIsOnlyAScalar(false);
            vtlDataset.setTransitory(false);

            List<VtlComponent> vtlComponents = new ArrayList<VtlComponent>();
            VtlComponent vtlComponent;
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

            vtlDataset.addComponentsList(vtlComponents);
            datasetRepository.save(vtlDataset);
        }
        String commandStatements = "DS_r  := cross_join ( DS_4, DS_4);";

        try {
            List<ResultExpression> result = vtlTestUtils.getResult(commandStatements);
            assertTrue("contollo semantico fallito", false);

        } catch (Exception exception) {

            logger.info(exception);

        }

    }

    /***se tra i dataset del join c’è un’espressione e non ne è stato definito l’alias (es inner_join(ds1, ds1+ds2)
     restituisce un errore mentre, inner_join (ds1, ds1+ds2 as ds3) non restituisce errori)*/

    @Test
    @Transactional
    public void innerJoinControlloDue() throws Exception {
        VtlDataset vtlDatasetFind = datasetRepository.findByNameIgnoreCase("DS_1");
        if (vtlDatasetFind == null) {
            VtlDataset vtlDataset = new VtlDataset();

            vtlDataset.setPersistent(true);
            vtlDataset.setName("DS_4");
            vtlDataset.setIsOnlyAScalar(false);
            vtlDataset.setTransitory(false);

            List<VtlComponent> vtlComponents = new ArrayList<VtlComponent>();
            VtlComponent vtlComponent;
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

            vtlDataset.addComponentsList(vtlComponents);
            datasetRepository.save(vtlDataset);

            VtlDataset vtlDatasetS = new VtlDataset();
            vtlDatasetS.setPersistent(true);
            vtlDatasetS.setName("DS_36");
            vtlDatasetS.setIsOnlyAScalar(false);
            vtlDatasetS.setTransitory(false);
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

            vtlDatasetS.addComponentsList(vtlComponents);
            datasetRepository.save(vtlDatasetS);

        }
        String commandStatements = "DS_r  := cross_join ( DS_4, DS_4 + DS_36);";

        try {
            List<ResultExpression> result = vtlTestUtils.getResult(commandStatements);
            assertTrue("contollo semantico fallito", false);

        } catch (Exception exception) {

            logger.info(exception);

        }


    }
    
    @Test
	@Transactional
	public void innerJoinNoUsing2Fail() throws Exception {
		//dovrebbe fallire in quanto i dataset DS1 e DS2 non hanno stessi Identifier né uno è superset dell’altro
		VtlDataset vtlDatasetFind = datasetRepository.findByNameIgnoreCase("DS_1");
		if (vtlDatasetFind == null) {
			VtlDataset vtlDataset = new VtlDataset();
			vtlDataset.setPersistent(true);
			vtlDataset.setName("DS_63");
			vtlDataset.setIsOnlyAScalar(false);
			vtlDataset.setTransitory(false);
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_1", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_2", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_1", VtlType.STRING, VtlComponentRole.MEASURE));
			datasetRepository.save(vtlDataset);

			VtlDataset vtlDatasetSecond = new VtlDataset();
			vtlDatasetSecond.setPersistent(true);
			vtlDatasetSecond.setName("DS_64");
			vtlDatasetSecond.setIsOnlyAScalar(false);
			vtlDatasetSecond.setTransitory(false);
			vtlDatasetSecond
					.addComponent(vtlTestUtils.getVtlComponent("ID_1", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDatasetSecond
					.addComponent(vtlTestUtils.getVtlComponent("ID_3", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDatasetSecond
					.addComponent(vtlTestUtils.getVtlComponent("ME_2", VtlType.STRING, VtlComponentRole.MEASURE));
			datasetRepository.save(vtlDatasetSecond);
			
			VtlDataset vtlDatasetThird = new VtlDataset();
			vtlDatasetThird.setPersistent(true);
			vtlDatasetThird.setName("DS_65");
			vtlDatasetThird.setIsOnlyAScalar(false);
			vtlDatasetThird.setTransitory(false);
			vtlDatasetThird
					.addComponent(vtlTestUtils.getVtlComponent("ID_1", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDatasetThird
					.addComponent(vtlTestUtils.getVtlComponent("ID_2", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDatasetThird
					.addComponent(vtlTestUtils.getVtlComponent("ID_3", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDatasetThird
					.addComponent(vtlTestUtils.getVtlComponent("ME_1", VtlType.STRING, VtlComponentRole.MEASURE));
			datasetRepository.save(vtlDatasetThird);
		}
		String commandStatements = "DS_r := inner_join (DS_63 as ds63, DS_64 as ds64, DS_65 as ds65);";  
		
		try {
            List<ResultExpression> result = vtlTestUtils.getResult(commandStatements);
            assertTrue("contollo semantico fallito", false);

        } catch (Exception exception) {

            logger.info(exception);

        }
    }
    
    @Test
	@Transactional
	public void innerJoinUsing3Fail() throws Exception {
		//dovrebbe fallire ID_3 non è comune a tutti i dataset
		VtlDataset vtlDatasetFind = datasetRepository.findByNameIgnoreCase("DS_1");
		if (vtlDatasetFind == null) {
			VtlDataset vtlDataset = new VtlDataset();
			vtlDataset.setPersistent(true);
			vtlDataset.setName("DS_63");
			vtlDataset.setIsOnlyAScalar(false);
			vtlDataset.setTransitory(false);
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_1", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_2", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_1", VtlType.STRING, VtlComponentRole.MEASURE));
			datasetRepository.save(vtlDataset);

			VtlDataset vtlDatasetSecond = new VtlDataset();
			vtlDatasetSecond.setPersistent(true);
			vtlDatasetSecond.setName("DS_64");
			vtlDatasetSecond.setIsOnlyAScalar(false);
			vtlDatasetSecond.setTransitory(false);
			vtlDatasetSecond
					.addComponent(vtlTestUtils.getVtlComponent("ID_1", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDatasetSecond
					.addComponent(vtlTestUtils.getVtlComponent("ID_2", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDatasetSecond
					.addComponent(vtlTestUtils.getVtlComponent("ME_2", VtlType.STRING, VtlComponentRole.MEASURE));
			datasetRepository.save(vtlDatasetSecond);
			
			VtlDataset vtlDatasetThird = new VtlDataset();
			vtlDatasetThird.setPersistent(true);
			vtlDatasetThird.setName("DS_65");
			vtlDatasetThird.setIsOnlyAScalar(false);
			vtlDatasetThird.setTransitory(false);
			vtlDatasetThird
					.addComponent(vtlTestUtils.getVtlComponent("ID_1", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDatasetThird
					.addComponent(vtlTestUtils.getVtlComponent("ID_2", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDatasetThird
					.addComponent(vtlTestUtils.getVtlComponent("ID_3", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDatasetThird
					.addComponent(vtlTestUtils.getVtlComponent("ME_3", VtlType.STRING, VtlComponentRole.MEASURE));
			datasetRepository.save(vtlDatasetThird);
		}
		String commandStatements = "DS_r := inner_join (DS_63 as ds63, DS_64 as ds64, DS_65 as ds65 using ID_1, ID_2, ID_3);";
    
		try {
            List<ResultExpression> result = vtlTestUtils.getResult(commandStatements);
            assertTrue("contollo semantico fallito", false);

        } catch (Exception exception) {

            logger.info(exception);

        }
    }
    
    @Test
	@Transactional
	public void innerJoinUsing5Fail() throws Exception {
		//dovrebbe fallire ID_2 non è identifier degli altri due dataset e non è del dataset di riferimento
		VtlDataset vtlDatasetFind = datasetRepository.findByNameIgnoreCase("DS_1");
		if (vtlDatasetFind == null) {
			VtlDataset vtlDataset = new VtlDataset();
			vtlDataset.setPersistent(true);
			vtlDataset.setName("DS_63");
			vtlDataset.setIsOnlyAScalar(false);
			vtlDataset.setTransitory(false);
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_1", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("CMP_1", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_1", VtlType.STRING, VtlComponentRole.MEASURE));
			datasetRepository.save(vtlDataset);

			VtlDataset vtlDatasetSecond = new VtlDataset();
			vtlDatasetSecond.setPersistent(true);
			vtlDatasetSecond.setName("DS_64");
			vtlDatasetSecond.setIsOnlyAScalar(false);
			vtlDatasetSecond.setTransitory(false);
			vtlDatasetSecond
					.addComponent(vtlTestUtils.getVtlComponent("ID_1", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDatasetSecond
					.addComponent(vtlTestUtils.getVtlComponent("CMP_1", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDatasetSecond
					.addComponent(vtlTestUtils.getVtlComponent("ME_2", VtlType.STRING, VtlComponentRole.MEASURE));
			datasetRepository.save(vtlDatasetSecond);
			
			VtlDataset vtlDatasetThird = new VtlDataset();
			vtlDatasetThird.setPersistent(true);
			vtlDatasetThird.setName("DS_65");
			vtlDatasetThird.setIsOnlyAScalar(false);
			vtlDatasetThird.setTransitory(false);
			vtlDatasetThird
					.addComponent(vtlTestUtils.getVtlComponent("ID_1", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDatasetThird
					.addComponent(vtlTestUtils.getVtlComponent("ID_2", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDatasetThird
					.addComponent(vtlTestUtils.getVtlComponent("CMP_1", VtlType.STRING, VtlComponentRole.MEASURE));
			vtlDatasetThird
					.addComponent(vtlTestUtils.getVtlComponent("ME_3", VtlType.STRING, VtlComponentRole.MEASURE));
			datasetRepository.save(vtlDatasetThird);
		}
		String commandStatements = "DS_r := inner_join (DS_63 as ds63, DS_64 as ds64, DS_65 as ds65 using ID_1, ID_2);";
		
		try {
            List<ResultExpression> result = vtlTestUtils.getResult(commandStatements);
            assertTrue("contollo semantico fallito", false);

        } catch (Exception exception) {

            logger.info(exception);

        }
    }
    
    @Test
	@Transactional
	public void innerJoinUsing6Fail() throws Exception {
		//dovrebbe fallire manca CMP_1 nello using
		VtlDataset vtlDatasetFind = datasetRepository.findByNameIgnoreCase("DS_1");
		if (vtlDatasetFind == null) {
			VtlDataset vtlDataset = new VtlDataset();
			vtlDataset.setPersistent(true);
			vtlDataset.setName("DS_63");
			vtlDataset.setIsOnlyAScalar(false);
			vtlDataset.setTransitory(false);
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_1", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("CMP_1", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_1", VtlType.STRING, VtlComponentRole.MEASURE));
			datasetRepository.save(vtlDataset);

			VtlDataset vtlDatasetSecond = new VtlDataset();
			vtlDatasetSecond.setPersistent(true);
			vtlDatasetSecond.setName("DS_64");
			vtlDatasetSecond.setIsOnlyAScalar(false);
			vtlDatasetSecond.setTransitory(false);
			vtlDatasetSecond
					.addComponent(vtlTestUtils.getVtlComponent("ID_1", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDatasetSecond
					.addComponent(vtlTestUtils.getVtlComponent("CMP_1", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDatasetSecond
					.addComponent(vtlTestUtils.getVtlComponent("ME_2", VtlType.STRING, VtlComponentRole.MEASURE));
			datasetRepository.save(vtlDatasetSecond);
			
			VtlDataset vtlDatasetThird = new VtlDataset();
			vtlDatasetThird.setPersistent(true);
			vtlDatasetThird.setName("DS_65");
			vtlDatasetThird.setIsOnlyAScalar(false);
			vtlDatasetThird.setTransitory(false);
			vtlDatasetThird
					.addComponent(vtlTestUtils.getVtlComponent("ID_1", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDatasetThird
					.addComponent(vtlTestUtils.getVtlComponent("ID_2", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDatasetThird
					.addComponent(vtlTestUtils.getVtlComponent("CMP_1", VtlType.STRING, VtlComponentRole.MEASURE));
			vtlDatasetThird
					.addComponent(vtlTestUtils.getVtlComponent("ME_3", VtlType.STRING, VtlComponentRole.MEASURE));
			datasetRepository.save(vtlDatasetThird);
		}
		String commandStatements = "DS_r := inner_join (DS_63 as ds63, DS_64 as ds64, DS_65 as ds65 using ID_1);";
		
		try {
            List<ResultExpression> result = vtlTestUtils.getResult(commandStatements);
            assertTrue("contollo semantico fallito", false);

        } catch (Exception exception) {

            logger.info(exception);

        }
    }
    
    @Test
	@Transactional
	public void innerJoinUsing7Fail() throws Exception {
		//dovrebbe fallire manca CMP_1 tra i componenti di DS_65
		VtlDataset vtlDatasetFind = datasetRepository.findByNameIgnoreCase("DS_1");
		if (vtlDatasetFind == null) {
			VtlDataset vtlDataset = new VtlDataset();
			vtlDataset.setPersistent(true);
			vtlDataset.setName("DS_63");
			vtlDataset.setIsOnlyAScalar(false);
			vtlDataset.setTransitory(false);
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_1", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("CMP_1", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_2", VtlType.STRING, VtlComponentRole.MEASURE));
			datasetRepository.save(vtlDataset);

			VtlDataset vtlDatasetSecond = new VtlDataset();
			vtlDatasetSecond.setPersistent(true);
			vtlDatasetSecond.setName("DS_64");
			vtlDatasetSecond.setIsOnlyAScalar(false);
			vtlDatasetSecond.setTransitory(false);
			vtlDatasetSecond
					.addComponent(vtlTestUtils.getVtlComponent("ID_1", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDatasetSecond
					.addComponent(vtlTestUtils.getVtlComponent("CMP_1", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDatasetSecond
					.addComponent(vtlTestUtils.getVtlComponent("ME_3", VtlType.STRING, VtlComponentRole.MEASURE));
			datasetRepository.save(vtlDatasetSecond);
			
			VtlDataset vtlDatasetThird = new VtlDataset();
			vtlDatasetThird.setPersistent(true);
			vtlDatasetThird.setName("DS_65");
			vtlDatasetThird.setIsOnlyAScalar(false);
			vtlDatasetThird.setTransitory(false);
			vtlDatasetThird
					.addComponent(vtlTestUtils.getVtlComponent("ID_1", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDatasetThird
					.addComponent(vtlTestUtils.getVtlComponent("ID_2", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDatasetThird
					.addComponent(vtlTestUtils.getVtlComponent("ME_1", VtlType.STRING, VtlComponentRole.MEASURE));
			datasetRepository.save(vtlDatasetThird);
		}
		String commandStatements = "DS_r := inner_join (DS_63 as ds63, DS_64 as ds64, DS_65 as ds65 using ID_1, CMP_1);";
		
		try {
            List<ResultExpression> result = vtlTestUtils.getResult(commandStatements);
            assertTrue("contollo semantico fallito", false);

        } catch (Exception exception) {

            logger.info(exception);

        }
    }
    
    @Test
	@Transactional
	public void leftJoinNoUsing2Fail() throws Exception {
		//dovrebbe fallire non hanno gli stessi Identifier 
		VtlDataset vtlDatasetFind = datasetRepository.findByNameIgnoreCase("DS_1");
		if (vtlDatasetFind == null) {
			VtlDataset vtlDataset = new VtlDataset();
			vtlDataset.setPersistent(true);
			vtlDataset.setName("DS_63");
			vtlDataset.setIsOnlyAScalar(false);
			vtlDataset.setTransitory(false);
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_1", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_2", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_2", VtlType.STRING, VtlComponentRole.MEASURE));
			datasetRepository.save(vtlDataset);

			VtlDataset vtlDatasetSecond = new VtlDataset();
			vtlDatasetSecond.setPersistent(true);
			vtlDatasetSecond.setName("DS_64");
			vtlDatasetSecond.setIsOnlyAScalar(false);
			vtlDatasetSecond.setTransitory(false);
			vtlDatasetSecond
					.addComponent(vtlTestUtils.getVtlComponent("ID_1", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDatasetSecond
					.addComponent(vtlTestUtils.getVtlComponent("ID_3", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDatasetSecond
					.addComponent(vtlTestUtils.getVtlComponent("ME_1", VtlType.STRING, VtlComponentRole.MEASURE));
			datasetRepository.save(vtlDatasetSecond);
			
			VtlDataset vtlDatasetThird = new VtlDataset();
			vtlDatasetThird.setPersistent(true);
			vtlDatasetThird.setName("DS_65");
			vtlDatasetThird.setIsOnlyAScalar(false);
			vtlDatasetThird.setTransitory(false);
			vtlDatasetThird
					.addComponent(vtlTestUtils.getVtlComponent("ID_1", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDatasetThird
					.addComponent(vtlTestUtils.getVtlComponent("ME_3", VtlType.STRING, VtlComponentRole.MEASURE));
			datasetRepository.save(vtlDatasetThird);
		}
		String commandStatements = "DS_r := left_join (DS_63 as ds63, DS_64 as ds64, DS_65 as ds65 );";
		
		try {
            List<ResultExpression> result = vtlTestUtils.getResult(commandStatements);
            assertTrue("contollo semantico fallito", false);

        } catch (Exception exception) {

            logger.info(exception);

        }
    }
    
    @Test
	@Transactional
	public void leftJoinUsing4Fail() throws Exception {
		//dovrebbe fallire non hanno gli stessi Identifier 
		VtlDataset vtlDatasetFind = datasetRepository.findByNameIgnoreCase("DS_1");
		if (vtlDatasetFind == null) {
			VtlDataset vtlDataset = new VtlDataset();
			vtlDataset.setPersistent(true);
			vtlDataset.setName("DS_63");
			vtlDataset.setIsOnlyAScalar(false);
			vtlDataset.setTransitory(false);
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_1", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_2", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_2", VtlType.STRING, VtlComponentRole.MEASURE));
			datasetRepository.save(vtlDataset);

			VtlDataset vtlDatasetSecond = new VtlDataset();
			vtlDatasetSecond.setPersistent(true);
			vtlDatasetSecond.setName("DS_64");
			vtlDatasetSecond.setIsOnlyAScalar(false);
			vtlDatasetSecond.setTransitory(false);
			vtlDatasetSecond
					.addComponent(vtlTestUtils.getVtlComponent("ID_1", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDatasetSecond
					.addComponent(vtlTestUtils.getVtlComponent("ID_3", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDatasetSecond
					.addComponent(vtlTestUtils.getVtlComponent("ME_1", VtlType.STRING, VtlComponentRole.MEASURE));
			datasetRepository.save(vtlDatasetSecond);
			
			VtlDataset vtlDatasetThird = new VtlDataset();
			vtlDatasetThird.setPersistent(true);
			vtlDatasetThird.setName("DS_65");
			vtlDatasetThird.setIsOnlyAScalar(false);
			vtlDatasetThird.setTransitory(false);
			vtlDatasetThird
					.addComponent(vtlTestUtils.getVtlComponent("ID_1", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDatasetThird
					.addComponent(vtlTestUtils.getVtlComponent("ME_3", VtlType.STRING, VtlComponentRole.MEASURE));
			datasetRepository.save(vtlDatasetThird);
		}
		String commandStatements = "DS_r := left_join (DS_63 as ds63, DS_64 as ds64, DS_65 as ds65 using ID_1);";
		
		try {
            List<ResultExpression> result = vtlTestUtils.getResult(commandStatements);
            assertTrue("contollo semantico fallito", false);

        } catch (Exception exception) {

            logger.info(exception);

        }
    }
    
    @Test
	@Transactional
	public void leftJoinUsing6Fail() throws Exception {
		//dovrebbe fallire DS_65 non è il dataset più a sinistra 
		VtlDataset vtlDatasetFind = datasetRepository.findByNameIgnoreCase("DS_1");
		if (vtlDatasetFind == null) {
			VtlDataset vtlDataset = new VtlDataset();
			vtlDataset.setPersistent(true);
			vtlDataset.setName("DS_63");
			vtlDataset.setIsOnlyAScalar(false);
			vtlDataset.setTransitory(false);
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_1", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("CMP_1", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_1", VtlType.STRING, VtlComponentRole.MEASURE));
			datasetRepository.save(vtlDataset);

			VtlDataset vtlDatasetSecond = new VtlDataset();
			vtlDatasetSecond.setPersistent(true);
			vtlDatasetSecond.setName("DS_64");
			vtlDatasetSecond.setIsOnlyAScalar(false);
			vtlDatasetSecond.setTransitory(false);
			vtlDatasetSecond
					.addComponent(vtlTestUtils.getVtlComponent("ID_1", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDatasetSecond
					.addComponent(vtlTestUtils.getVtlComponent("CMP_1", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDatasetSecond
					.addComponent(vtlTestUtils.getVtlComponent("ME_2", VtlType.STRING, VtlComponentRole.MEASURE));
			datasetRepository.save(vtlDatasetSecond);
			
			VtlDataset vtlDatasetThird = new VtlDataset();
			vtlDatasetThird.setPersistent(true);
			vtlDatasetThird.setName("DS_65");
			vtlDatasetThird.setIsOnlyAScalar(false);
			vtlDatasetThird.setTransitory(false);
			vtlDatasetThird
					.addComponent(vtlTestUtils.getVtlComponent("ID_1", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDatasetThird
					.addComponent(vtlTestUtils.getVtlComponent("ID_2", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDatasetThird
					.addComponent(vtlTestUtils.getVtlComponent("CMP_1", VtlType.STRING, VtlComponentRole.MEASURE));
			vtlDatasetThird
					.addComponent(vtlTestUtils.getVtlComponent("ME_3", VtlType.STRING, VtlComponentRole.MEASURE));
	
			datasetRepository.save(vtlDatasetThird);
		}
		String commandStatements = "DS_r := left_join (DS_63 as ds63, DS_64 as ds64, DS_65 as ds65 using ID_1, CMP_1);";
		
		try {
            List<ResultExpression> result = vtlTestUtils.getResult(commandStatements);
            assertTrue("contollo semantico fallito", false);

        } catch (Exception exception) {

            logger.info(exception);

        }
    }
    
    @Test
	@Transactional
	public void leftJoinUsing7Fail() throws Exception {
		//dovrebbe fallire manca cmp1 nello using e DS_65 non è il più a sinistra  
		VtlDataset vtlDatasetFind = datasetRepository.findByNameIgnoreCase("DS_1");
		if (vtlDatasetFind == null) {
			VtlDataset vtlDataset = new VtlDataset();
			vtlDataset.setPersistent(true);
			vtlDataset.setName("DS_63");
			vtlDataset.setIsOnlyAScalar(false);
			vtlDataset.setTransitory(false);
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_1", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("CMP_1", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_1", VtlType.STRING, VtlComponentRole.MEASURE));
			datasetRepository.save(vtlDataset);

			VtlDataset vtlDatasetSecond = new VtlDataset();
			vtlDatasetSecond.setPersistent(true);
			vtlDatasetSecond.setName("DS_64");
			vtlDatasetSecond.setIsOnlyAScalar(false);
			vtlDatasetSecond.setTransitory(false);
			vtlDatasetSecond
					.addComponent(vtlTestUtils.getVtlComponent("ID_1", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDatasetSecond
					.addComponent(vtlTestUtils.getVtlComponent("CMP_1", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDatasetSecond
					.addComponent(vtlTestUtils.getVtlComponent("ME_2", VtlType.STRING, VtlComponentRole.MEASURE));
			datasetRepository.save(vtlDatasetSecond);
			
			VtlDataset vtlDatasetThird = new VtlDataset();
			vtlDatasetThird.setPersistent(true);
			vtlDatasetThird.setName("DS_65");
			vtlDatasetThird.setIsOnlyAScalar(false);
			vtlDatasetThird.setTransitory(false);
			vtlDatasetThird
					.addComponent(vtlTestUtils.getVtlComponent("ID_1", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDatasetThird
					.addComponent(vtlTestUtils.getVtlComponent("ID_2", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDatasetThird
					.addComponent(vtlTestUtils.getVtlComponent("CMP_1", VtlType.STRING, VtlComponentRole.MEASURE));
			vtlDatasetThird
					.addComponent(vtlTestUtils.getVtlComponent("ME_3", VtlType.STRING, VtlComponentRole.MEASURE));
	
			datasetRepository.save(vtlDatasetThird);
		}
		String commandStatements = "DS_r := left_join (DS_63 as ds63, DS_64 as ds64, DS_65 as ds65 using ID_1);";
		
		try {
            List<ResultExpression> result = vtlTestUtils.getResult(commandStatements);
            assertTrue("contollo semantico fallito", false);

        } catch (Exception exception) {

            logger.info(exception);

        }
    }
    
    @Test
	@Transactional
	public void leftJoinUsing8Fail() throws Exception {
		//dovrebbe fallire in DS_65 manca cmp1  
		VtlDataset vtlDatasetFind = datasetRepository.findByNameIgnoreCase("DS_1");
		if (vtlDatasetFind == null) {
			VtlDataset vtlDataset = new VtlDataset();
			vtlDataset.setPersistent(true);
			vtlDataset.setName("DS_63");
			vtlDataset.setIsOnlyAScalar(false);
			vtlDataset.setTransitory(false);
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_1", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("CMP_1", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_2", VtlType.STRING, VtlComponentRole.MEASURE));
			datasetRepository.save(vtlDataset);

			VtlDataset vtlDatasetSecond = new VtlDataset();
			vtlDatasetSecond.setPersistent(true);
			vtlDatasetSecond.setName("DS_64");
			vtlDatasetSecond.setIsOnlyAScalar(false);
			vtlDatasetSecond.setTransitory(false);
			vtlDatasetSecond
					.addComponent(vtlTestUtils.getVtlComponent("ID_1", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDatasetSecond
					.addComponent(vtlTestUtils.getVtlComponent("CMP_1", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDatasetSecond
					.addComponent(vtlTestUtils.getVtlComponent("ME_3", VtlType.STRING, VtlComponentRole.MEASURE));
			datasetRepository.save(vtlDatasetSecond);
			
			VtlDataset vtlDatasetThird = new VtlDataset();
			vtlDatasetThird.setPersistent(true);
			vtlDatasetThird.setName("DS_65");
			vtlDatasetThird.setIsOnlyAScalar(false);
			vtlDatasetThird.setTransitory(false);
			vtlDatasetThird
					.addComponent(vtlTestUtils.getVtlComponent("ID_1", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDatasetThird
					.addComponent(vtlTestUtils.getVtlComponent("ID_2", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDatasetThird
					.addComponent(vtlTestUtils.getVtlComponent("ME_1", VtlType.STRING, VtlComponentRole.MEASURE));
	
			datasetRepository.save(vtlDatasetThird);
		}
		String commandStatements = "DS_r := left_join (DS_65 as ds65, DS_63 as ds63, DS_64 as ds64 using ID_1);";
		
		try {
            List<ResultExpression> result = vtlTestUtils.getResult(commandStatements);
            assertTrue("contollo semantico fallito", false);

        } catch (Exception exception) {

            logger.info(exception);

        }
    }
    
    @Test
	@Transactional
	public void fullJoinNoUsing2Fail() throws Exception {
		//dovrebbe fallire : i tre dataset non hanno gli stessi identificativi
		VtlDataset vtlDatasetFind = datasetRepository.findByNameIgnoreCase("DS_1");
		if (vtlDatasetFind == null) {
			VtlDataset vtlDataset = new VtlDataset();
			vtlDataset.setPersistent(true);
			vtlDataset.setName("DS_63");
			vtlDataset.setIsOnlyAScalar(false);
			vtlDataset.setTransitory(false);
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_1", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_2", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_1", VtlType.STRING, VtlComponentRole.MEASURE));
			datasetRepository.save(vtlDataset);

			VtlDataset vtlDatasetSecond = new VtlDataset();
			vtlDatasetSecond.setPersistent(true);
			vtlDatasetSecond.setName("DS_64");
			vtlDatasetSecond.setIsOnlyAScalar(false);
			vtlDatasetSecond.setTransitory(false);
			vtlDatasetSecond
					.addComponent(vtlTestUtils.getVtlComponent("ID_1", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDatasetSecond
					.addComponent(vtlTestUtils.getVtlComponent("ID_2", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDatasetSecond
					.addComponent(vtlTestUtils.getVtlComponent("ME_2", VtlType.STRING, VtlComponentRole.MEASURE));
			datasetRepository.save(vtlDatasetSecond);
			
			VtlDataset vtlDatasetThird = new VtlDataset();
			vtlDatasetThird.setPersistent(true);
			vtlDatasetThird.setName("DS_65");
			vtlDatasetThird.setIsOnlyAScalar(false);
			vtlDatasetThird.setTransitory(false);
			vtlDatasetThird
					.addComponent(vtlTestUtils.getVtlComponent("ID_1", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDatasetThird
					.addComponent(vtlTestUtils.getVtlComponent("ID_2", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDatasetThird
					.addComponent(vtlTestUtils.getVtlComponent("ID_3", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDatasetThird
					.addComponent(vtlTestUtils.getVtlComponent("ME_3", VtlType.STRING, VtlComponentRole.MEASURE));
	
			datasetRepository.save(vtlDatasetThird);
		}
		String commandStatements = "DS_r := full_join (DS_63 as ds63, DS_64 as ds64, DS_65 as ds65);";
		
		try {
            List<ResultExpression> result = vtlTestUtils.getResult(commandStatements);
            assertTrue("contollo semantico fallito", false);

        } catch (Exception exception) {

            logger.info(exception);

        }
    }
    
    @Test
	@Transactional
	public void fullJoinUsingFail() throws Exception {
		//dovrebbe fallire : non si può utilizzare lo using
		VtlDataset vtlDatasetFind = datasetRepository.findByNameIgnoreCase("DS_1");
		if (vtlDatasetFind == null) {
			VtlDataset vtlDataset = new VtlDataset();
			vtlDataset.setPersistent(true);
			vtlDataset.setName("DS_63");
			vtlDataset.setIsOnlyAScalar(false);
			vtlDataset.setTransitory(false);
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_1", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_2", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_1", VtlType.STRING, VtlComponentRole.MEASURE));
			datasetRepository.save(vtlDataset);

			VtlDataset vtlDatasetSecond = new VtlDataset();
			vtlDatasetSecond.setPersistent(true);
			vtlDatasetSecond.setName("DS_64");
			vtlDatasetSecond.setIsOnlyAScalar(false);
			vtlDatasetSecond.setTransitory(false);
			vtlDatasetSecond
					.addComponent(vtlTestUtils.getVtlComponent("ID_1", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDatasetSecond
					.addComponent(vtlTestUtils.getVtlComponent("ID_2", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDatasetSecond
					.addComponent(vtlTestUtils.getVtlComponent("ME_2", VtlType.STRING, VtlComponentRole.MEASURE));
			datasetRepository.save(vtlDatasetSecond);
			
		}
		String commandStatements = "DS_r := full_join (DS_63 as ds63, DS_64 as ds64 using ID_1);";
		
		try {
            List<ResultExpression> result = vtlTestUtils.getResult(commandStatements);
            assertTrue("contollo semantico fallito", false);

        } catch (Exception exception) {

            logger.info(exception);

        }
    }
    
    @Test
	@Transactional
	public void crossJoinUsingFail() throws Exception {
		//dovrebbe fallire : non si può utilizzare lo using
		VtlDataset vtlDatasetFind = datasetRepository.findByNameIgnoreCase("DS_1");
		if (vtlDatasetFind == null) {
			VtlDataset vtlDataset = new VtlDataset();
			vtlDataset.setPersistent(true);
			vtlDataset.setName("DS_63");
			vtlDataset.setIsOnlyAScalar(false);
			vtlDataset.setTransitory(false);
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_1", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_2", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_1", VtlType.STRING, VtlComponentRole.MEASURE));
			datasetRepository.save(vtlDataset);

			VtlDataset vtlDatasetSecond = new VtlDataset();
			vtlDatasetSecond.setPersistent(true);
			vtlDatasetSecond.setName("DS_64");
			vtlDatasetSecond.setIsOnlyAScalar(false);
			vtlDatasetSecond.setTransitory(false);
			vtlDatasetSecond
					.addComponent(vtlTestUtils.getVtlComponent("ID_1", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDatasetSecond
					.addComponent(vtlTestUtils.getVtlComponent("ID_2", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDatasetSecond
					.addComponent(vtlTestUtils.getVtlComponent("ME_2", VtlType.STRING, VtlComponentRole.MEASURE));
			datasetRepository.save(vtlDatasetSecond);
			
		}
		String commandStatements = "DS_r := cross_join (DS_63 as ds63, DS_64 as ds64 using ID_1);";
		
		try {
            List<ResultExpression> result = vtlTestUtils.getResult(commandStatements);
            assertTrue("contollo semantico fallito", false);

        } catch (Exception exception) {

            logger.info(exception);

        }
    }
    
    @Test
	@Transactional
	public void innerJoinFilter5Fail() throws Exception {
		//fallisce:  la misura me1 è ambigua perché presente in due dataset
		VtlDataset vtlDatasetFind = datasetRepository.findByNameIgnoreCase("DS_1");
		if (vtlDatasetFind == null) {
			VtlDataset vtlDataset = new VtlDataset();
			vtlDataset.setPersistent(true);
			vtlDataset.setName("DS_63");
			vtlDataset.setIsOnlyAScalar(false);
			vtlDataset.setTransitory(false);
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_1", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("CMP_1", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_1", VtlType.STRING, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("AT_1", VtlType.STRING, VtlComponentRole.ATTRIBUTE));
			datasetRepository.save(vtlDataset);

			VtlDataset vtlDatasetSecond = new VtlDataset();
			vtlDatasetSecond.setPersistent(true);
			vtlDatasetSecond.setName("DS_64");
			vtlDatasetSecond.setIsOnlyAScalar(false);
			vtlDatasetSecond.setTransitory(false);
			vtlDatasetSecond
					.addComponent(vtlTestUtils.getVtlComponent("ID_1", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDatasetSecond
					.addComponent(vtlTestUtils.getVtlComponent("CMP_1", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDatasetSecond
					.addComponent(vtlTestUtils.getVtlComponent("ME_1", VtlType.STRING, VtlComponentRole.MEASURE));
			vtlDatasetSecond
					.addComponent(vtlTestUtils.getVtlComponent("AT_1", VtlType.STRING, VtlComponentRole.ATTRIBUTE));
			datasetRepository.save(vtlDatasetSecond);
			
			VtlDataset vtlDatasetThird = new VtlDataset();
			vtlDatasetThird.setPersistent(true);
			vtlDatasetThird.setName("DS_65");
			vtlDatasetThird.setIsOnlyAScalar(false);
			vtlDatasetThird.setTransitory(false);
			vtlDatasetThird
					.addComponent(vtlTestUtils.getVtlComponent("ID_1", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDatasetThird
					.addComponent(vtlTestUtils.getVtlComponent("ID_2", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDatasetThird
					.addComponent(vtlTestUtils.getVtlComponent("CMP_1", VtlType.INTEGER, VtlComponentRole.MEASURE));
			vtlDatasetThird
					.addComponent(vtlTestUtils.getVtlComponent("ME_1", VtlType.STRING, VtlComponentRole.MEASURE));
			vtlDatasetThird
					.addComponent(vtlTestUtils.getVtlComponent("AT_2", VtlType.STRING, VtlComponentRole.VIRAL));			
			datasetRepository.save(vtlDatasetThird);
						
		}
		String commandStatements = "DS_r := inner_join (DS_63 as ds63, DS_64 as ds64, DS_65 as ds65 filter ME_1>0 and ME_3=ME_2);";
		
		try {
            List<ResultExpression> result = vtlTestUtils.getResult(commandStatements);
            assertTrue("contollo semantico fallito", false);

        } catch (Exception exception) {

            logger.info(exception);

        }
    }
    
	@Test
	@Transactional
	public void innerJoinApplyFail() throws Exception {
		
		VtlDataset vtlDatasetFind = datasetRepository.findByNameIgnoreCase("DS_1");
		if (vtlDatasetFind == null) {
			VtlDataset vtlDataset = new VtlDataset();
			vtlDataset.setPersistent(true);
			vtlDataset.setName("DS_63");
			vtlDataset.setIsOnlyAScalar(false);
			vtlDataset.setTransitory(false);
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_1", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_1", VtlType.STRING, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("AT_1", VtlType.STRING, VtlComponentRole.ATTRIBUTE));
			datasetRepository.save(vtlDataset);

			VtlDataset vtlDatasetSecond = new VtlDataset();
			vtlDatasetSecond.setPersistent(true);
			vtlDatasetSecond.setName("DS_64");
			vtlDatasetSecond.setIsOnlyAScalar(false);
			vtlDatasetSecond.setTransitory(false);
			vtlDatasetSecond
					.addComponent(vtlTestUtils.getVtlComponent("ID_1", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDatasetSecond
					.addComponent(vtlTestUtils.getVtlComponent("ID_2", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDatasetSecond
					.addComponent(vtlTestUtils.getVtlComponent("ME_1", VtlType.STRING, VtlComponentRole.MEASURE));
			vtlDatasetSecond
					.addComponent(vtlTestUtils.getVtlComponent("AT_2", VtlType.STRING, VtlComponentRole.VIRAL));
			datasetRepository.save(vtlDatasetSecond);
			
			VtlDataset vtlDatasetThird = new VtlDataset();
			vtlDatasetThird.setPersistent(true);
			vtlDatasetThird.setName("DS_65");
			vtlDatasetThird.setIsOnlyAScalar(false);
			vtlDatasetThird.setTransitory(false);
			vtlDatasetThird
					.addComponent(vtlTestUtils.getVtlComponent("ID_1", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDatasetThird
					.addComponent(vtlTestUtils.getVtlComponent("ID_2", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDatasetThird
					.addComponent(vtlTestUtils.getVtlComponent("ID_3", VtlType.INTEGER, VtlComponentRole.MEASURE));
			vtlDatasetThird
					.addComponent(vtlTestUtils.getVtlComponent("ME_3", VtlType.STRING, VtlComponentRole.MEASURE));
			datasetRepository.save(vtlDatasetThird);
						
		}
		String commandStatements = "DS_r := inner_join (DS_63 as ds63, DS_64 as ds64, DS_65 as ds65 apply ds63 + ds64);";
		
		try {
            List<ResultExpression> result = vtlTestUtils.getResult(commandStatements);
            assertTrue("contollo semantico fallito", false);

        } catch (Exception exception) {

            logger.info(exception);

        }
	}
	
	@Test
	@Transactional
	public void innerJoinApply2Fail() throws Exception {
		//l’operatore <- non può essere utilizzato nella apply 
		VtlDataset vtlDatasetFind = datasetRepository.findByNameIgnoreCase("DS_1");
		if (vtlDatasetFind == null) {
			VtlDataset vtlDataset = new VtlDataset();
			vtlDataset.setPersistent(true);
			vtlDataset.setName("DS_63");
			vtlDataset.setIsOnlyAScalar(false);
			vtlDataset.setTransitory(false);
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_1", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_1", VtlType.STRING, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("AT_1", VtlType.STRING, VtlComponentRole.ATTRIBUTE));
			datasetRepository.save(vtlDataset);

			VtlDataset vtlDatasetSecond = new VtlDataset();
			vtlDatasetSecond.setPersistent(true);
			vtlDatasetSecond.setName("DS_64");
			vtlDatasetSecond.setIsOnlyAScalar(false);
			vtlDatasetSecond.setTransitory(false);
			vtlDatasetSecond
					.addComponent(vtlTestUtils.getVtlComponent("ID_1", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDatasetSecond
					.addComponent(vtlTestUtils.getVtlComponent("ID_2", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDatasetSecond
					.addComponent(vtlTestUtils.getVtlComponent("ME_1", VtlType.STRING, VtlComponentRole.MEASURE));
			vtlDatasetSecond
					.addComponent(vtlTestUtils.getVtlComponent("AT_2", VtlType.STRING, VtlComponentRole.VIRAL));
			datasetRepository.save(vtlDatasetSecond);
			
			VtlDataset vtlDatasetThird = new VtlDataset();
			vtlDatasetThird.setPersistent(true);
			vtlDatasetThird.setName("DS_65");
			vtlDatasetThird.setIsOnlyAScalar(false);
			vtlDatasetThird.setTransitory(false);
			vtlDatasetThird
					.addComponent(vtlTestUtils.getVtlComponent("ID_1", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDatasetThird
					.addComponent(vtlTestUtils.getVtlComponent("ID_2", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDatasetThird
					.addComponent(vtlTestUtils.getVtlComponent("ID_3", VtlType.INTEGER, VtlComponentRole.MEASURE));
			vtlDatasetThird
					.addComponent(vtlTestUtils.getVtlComponent("ME_3", VtlType.STRING, VtlComponentRole.MEASURE));
			datasetRepository.save(vtlDatasetThird);
						
		}
		String commandStatements = "DS_r := inner_join (DS_63 as ds63, DS_64 as ds64, DS_65 as ds65 apply ds63 <- ds64);";
		try {
            List<ResultExpression> result = vtlTestUtils.getResult(commandStatements);
            assertTrue("contollo semantico fallito", false);

        } catch (Exception exception) {

            logger.info(exception);

        }
	}
	
	@Test
	@Transactional
	public void innerJoinApply3Fail() throws Exception {
		//, il dataset DS4 non è tra quelli messi in join 
		VtlDataset vtlDatasetFind = datasetRepository.findByNameIgnoreCase("DS_1");
		if (vtlDatasetFind == null) {
			VtlDataset vtlDataset = new VtlDataset();
			vtlDataset.setPersistent(true);
			vtlDataset.setName("DS_63");
			vtlDataset.setIsOnlyAScalar(false);
			vtlDataset.setTransitory(false);
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_1", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_1", VtlType.STRING, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("AT_1", VtlType.STRING, VtlComponentRole.ATTRIBUTE));
			datasetRepository.save(vtlDataset);

			VtlDataset vtlDatasetSecond = new VtlDataset();
			vtlDatasetSecond.setPersistent(true);
			vtlDatasetSecond.setName("DS_64");
			vtlDatasetSecond.setIsOnlyAScalar(false);
			vtlDatasetSecond.setTransitory(false);
			vtlDatasetSecond
					.addComponent(vtlTestUtils.getVtlComponent("ID_1", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDatasetSecond
					.addComponent(vtlTestUtils.getVtlComponent("ID_2", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDatasetSecond
					.addComponent(vtlTestUtils.getVtlComponent("ME_1", VtlType.STRING, VtlComponentRole.MEASURE));
			vtlDatasetSecond
					.addComponent(vtlTestUtils.getVtlComponent("AT_2", VtlType.STRING, VtlComponentRole.VIRAL));
			datasetRepository.save(vtlDatasetSecond);
			
			VtlDataset vtlDatasetThird = new VtlDataset();
			vtlDatasetThird.setPersistent(true);
			vtlDatasetThird.setName("DS_65");
			vtlDatasetThird.setIsOnlyAScalar(false);
			vtlDatasetThird.setTransitory(false);
			vtlDatasetThird
					.addComponent(vtlTestUtils.getVtlComponent("ID_1", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDatasetThird
					.addComponent(vtlTestUtils.getVtlComponent("ID_2", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDatasetThird
					.addComponent(vtlTestUtils.getVtlComponent("ID_3", VtlType.INTEGER, VtlComponentRole.MEASURE));
			vtlDatasetThird
					.addComponent(vtlTestUtils.getVtlComponent("ME_3", VtlType.STRING, VtlComponentRole.MEASURE));
			datasetRepository.save(vtlDatasetThird);
						
		}
		String commandStatements = "DS_r := inner_join (DS_63 as ds63, DS_64 as ds64, DS_65 as ds65 apply ds63 + DS4);";
		try {
            List<ResultExpression> result = vtlTestUtils.getResult(commandStatements);
            assertTrue("contollo semantico fallito", false);

        } catch (Exception exception) {

            logger.info(exception);

        }
	}
	
	@Test
	@Transactional
	public void innerJoinAggr2Fail() throws Exception {
		
		VtlDataset vtlDatasetFind = datasetRepository.findByNameIgnoreCase("DS_1");
		if (vtlDatasetFind == null) {
			VtlDataset vtlDataset = new VtlDataset();
			vtlDataset.setPersistent(true);
			vtlDataset.setName("DS_63");
			vtlDataset.setIsOnlyAScalar(false);
			vtlDataset.setTransitory(false);
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_1", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_1", VtlType.INTEGER, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("AT_1", VtlType.INTEGER, VtlComponentRole.ATTRIBUTE));
			datasetRepository.save(vtlDataset);

			VtlDataset vtlDatasetSecond = new VtlDataset();
			vtlDatasetSecond.setPersistent(true);
			vtlDatasetSecond.setName("DS_64");
			vtlDatasetSecond.setIsOnlyAScalar(false);
			vtlDatasetSecond.setTransitory(false);
			vtlDatasetSecond
					.addComponent(vtlTestUtils.getVtlComponent("ID_1", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDatasetSecond
					.addComponent(vtlTestUtils.getVtlComponent("ID_2", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDatasetSecond
					.addComponent(vtlTestUtils.getVtlComponent("ME_2", VtlType.INTEGER, VtlComponentRole.MEASURE));
			vtlDatasetSecond
					.addComponent(vtlTestUtils.getVtlComponent("AT_2", VtlType.STRING, VtlComponentRole.VIRAL));
			datasetRepository.save(vtlDatasetSecond);
			
			VtlDataset vtlDatasetThird = new VtlDataset();
			vtlDatasetThird.setPersistent(true);
			vtlDatasetThird.setName("DS_65");
			vtlDatasetThird.setIsOnlyAScalar(false);
			vtlDatasetThird.setTransitory(false);
			vtlDatasetThird
					.addComponent(vtlTestUtils.getVtlComponent("ID_1", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDatasetThird
					.addComponent(vtlTestUtils.getVtlComponent("ID_2", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDatasetThird
					.addComponent(vtlTestUtils.getVtlComponent("ID_3", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDatasetThird
					.addComponent(vtlTestUtils.getVtlComponent("ME_3", VtlType.INTEGER, VtlComponentRole.MEASURE));
			datasetRepository.save(vtlDatasetThird);
						
		}
		String commandStatements = "DS_r := inner_join (DS_63 as ds63, DS_64 as ds64, DS_65 as ds65 aggr ME_1:=sum(ME_2) group by ID_1, ID_2 having max(ME_2)>10);";
		try {
            List<ResultExpression> result = vtlTestUtils.getResult(commandStatements);
            assertTrue("contollo semantico fallito", false);

        } catch (Exception exception) {

            logger.info(exception);

        }
	}
	
	@Test
	@Transactional
	public void innerJoinAggr3Fail() throws Exception {
		
		VtlDataset vtlDatasetFind = datasetRepository.findByNameIgnoreCase("DS_1");
		if (vtlDatasetFind == null) {
			VtlDataset vtlDataset = new VtlDataset();
			vtlDataset.setPersistent(true);
			vtlDataset.setName("DS_63");
			vtlDataset.setIsOnlyAScalar(false);
			vtlDataset.setTransitory(false);
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_1", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_1", VtlType.INTEGER, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("AT_1", VtlType.INTEGER, VtlComponentRole.ATTRIBUTE));
			datasetRepository.save(vtlDataset);

			VtlDataset vtlDatasetSecond = new VtlDataset();
			vtlDatasetSecond.setPersistent(true);
			vtlDatasetSecond.setName("DS_64");
			vtlDatasetSecond.setIsOnlyAScalar(false);
			vtlDatasetSecond.setTransitory(false);
			vtlDatasetSecond
					.addComponent(vtlTestUtils.getVtlComponent("ID_1", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDatasetSecond
					.addComponent(vtlTestUtils.getVtlComponent("ID_2", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDatasetSecond
					.addComponent(vtlTestUtils.getVtlComponent("ME_2", VtlType.INTEGER, VtlComponentRole.MEASURE));
			vtlDatasetSecond
					.addComponent(vtlTestUtils.getVtlComponent("AT_2", VtlType.STRING, VtlComponentRole.VIRAL));
			datasetRepository.save(vtlDatasetSecond);
			
			VtlDataset vtlDatasetThird = new VtlDataset();
			vtlDatasetThird.setPersistent(true);
			vtlDatasetThird.setName("DS_65");
			vtlDatasetThird.setIsOnlyAScalar(false);
			vtlDatasetThird.setTransitory(false);
			vtlDatasetThird
					.addComponent(vtlTestUtils.getVtlComponent("ID_1", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDatasetThird
					.addComponent(vtlTestUtils.getVtlComponent("ID_2", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDatasetThird
					.addComponent(vtlTestUtils.getVtlComponent("ID_3", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDatasetThird
					.addComponent(vtlTestUtils.getVtlComponent("ME_3", VtlType.INTEGER, VtlComponentRole.MEASURE));
			datasetRepository.save(vtlDatasetThird);
						
		}
		String commandStatements = "DS_r := inner_join (DS_63 as ds63, DS_64 as ds64, DS_65 as ds65 aggr ME_1:=sum(ME_2) group by ID_1, ID_2 having ME_2>10);";
		try {
            List<ResultExpression> result = vtlTestUtils.getResult(commandStatements);
            assertTrue("contollo semantico fallito", false);

        } catch (Exception exception) {

            logger.info(exception);

        }
	}
}
