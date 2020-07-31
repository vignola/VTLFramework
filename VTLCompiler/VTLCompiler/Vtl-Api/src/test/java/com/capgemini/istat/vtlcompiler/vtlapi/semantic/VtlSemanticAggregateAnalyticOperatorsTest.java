package com.capgemini.istat.vtlcompiler.vtlapi.semantic;

import org.apache.logging.log4j.LogManager;

import org.apache.logging.log4j.Logger;
import org.junit.Test;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.capgemini.istat.vtlcompiler.vtlcommon.model.dataset.VtlComponentRole;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.dataset.VtlDataset;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.dataset.VtlType;

import com.capgemini.istat.vtlcompiler.vtlcommon.repository.DatasetRepository;
import com.capgemini.istat.vtlcompiler.vtlsemantic.service.SemanticService;
import com.capgemini.istat.vtlcompiler.vtlapi.utils.VtlTestUtils;

import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest

public class VtlSemanticAggregateAnalyticOperatorsTest {

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

//		I tipi scalari di base di firstOperand e additionalOperand 
//	(se presenti) non sono conformi ai tipi scalari di base specifici richiesti dall'operatore richiamato

	@Test
	@Transactional
	public void avgGroupByControlloDue() throws Exception {
		VtlDataset vtlDatasetFind = datasetRepository.findByNameIgnoreCase("DS_1");
		if (vtlDatasetFind == null) {
			VtlDataset vtlDataset = new VtlDataset();

			vtlDataset.setPersistent(true);
			vtlDataset.setName("DS_29");
			vtlDataset.setIsOnlyAScalar(false);
			vtlDataset.setTransitory(false);
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_1", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_2", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_3", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_1", VtlType.INTEGER, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("AT_1", VtlType.STRING, VtlComponentRole.VIRAL));
			datasetRepository.save(vtlDataset);
		}
		String commandStatements = "DS_r := avg ( DS_29  group by  ME_5 );";

		try {
			vtlTestUtils.getResult(commandStatements);
			assertTrue("contollo semantico fallito", false);

		}

		catch (Exception exception) {

			logger.info(exception);

		}

	}

//	I tipi scalari di base di firstOperand e additionalOperand 
//(se presenti) non sono conformi ai tipi scalari di base specifici richiesti dall'operatore richiamato

	@Test
	@Transactional
	public void avgGroupExceptControlloDue() throws Exception {
		VtlDataset vtlDatasetFind = datasetRepository.findByNameIgnoreCase("DS_1");
		if (vtlDatasetFind == null) {
			VtlDataset vtlDataset = new VtlDataset();

			vtlDataset.setPersistent(true);
			vtlDataset.setName("DS_29");
			vtlDataset.setIsOnlyAScalar(false);
			vtlDataset.setTransitory(false);
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_1", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_2", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_3", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_1", VtlType.INTEGER, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("AT_1", VtlType.STRING, VtlComponentRole.VIRAL));
			datasetRepository.save(vtlDataset);
		}
		String commandStatements = "DS_r := avg ( DS_29  group except   Id_6,  Id_8 );";

		try {
			vtlTestUtils.getResult(commandStatements);
			assertTrue("contollo semantico fallito", false);

		}

		catch (Exception exception) {

			logger.info(exception);

		}

	}

//	L’operatore di aggregazione è annidato ad altri operatori analitici o di aggregazione o analitiche
//
//	@Test
//	@Transactional
//	public void avgControlloDue() throws Exception {
//		VtlDataset vtlDatasetFind = datasetRepository.findByNameIgnoreCase("DS_1");
//		if (vtlDatasetFind == null) {
//			VtlDataset vtlDataset = new VtlDataset();
//
//			vtlDataset.setPersistent(true);
//			vtlDataset.setName("DS_29");
//			vtlDataset.setIsOnlyAScalar(false);
//			vtlDataset.setTransitory(false);
//			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_1", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
//			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_2", VtlType.STRING, VtlComponentRole.IDENTIFIER));
//			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_3", VtlType.STRING, VtlComponentRole.IDENTIFIER));
//			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_1", VtlType.INTEGER, VtlComponentRole.MEASURE));
//			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("AT_1", VtlType.STRING, VtlComponentRole.VIRAL));
//			datasetRepository.save(vtlDataset);
//		}
//
	// This operator cannot be applied to scalar values
//
//		String commandStatements = "DS_r :=  avg ( DS_29  group by  5 );";
//
//		try {
//			vtlTestUtils.getResult(commandStatements);
//			assertTrue("contollo semantico fallito", false);
//
//		}
//
//		catch (Exception exception) {
//
//			logger.info(exception);
//
//		}
//
//	}

//		Il tipo scalare di base dell’identificativo usato nella conversionExpr
//	deve essere compatibile con il tipo scalare di base dell'operatore di conversione

	@Test
	@Transactional
	public void avgControlloTre() throws Exception {
		VtlDataset vtlDatasetFind = datasetRepository.findByNameIgnoreCase("DS_1");
		if (vtlDatasetFind == null) {
			VtlDataset vtlDataset = new VtlDataset();

			vtlDataset.setPersistent(true);
			vtlDataset.setName("DS_29");
			vtlDataset.setIsOnlyAScalar(false);
			vtlDataset.setTransitory(false);
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_1", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_2", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_3", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_1", VtlType.INTEGER, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("AT_1", VtlType.STRING, VtlComponentRole.VIRAL));
			datasetRepository.save(vtlDataset);
		}
		String commandStatements = "DS_r := avg ( DS_29#Me_1  group by  Id_5 );";

		try {
			vtlTestUtils.getResult(commandStatements);
			assertTrue("contollo semantico fallito", false);

		}

		catch (Exception exception) {

			logger.info(exception);

		}

	}

//	I tipi scalari di base di firstOperand e additionalOperand 
//(se presenti) non sono conformi ai tipi scalari di base specifici richiesti dall'operatore richiamato

	@Test
	@Transactional
	public void aggrGroupByControlloDue() throws Exception {
		VtlDataset vtlDatasetFind = datasetRepository.findByNameIgnoreCase("DS_1");
		if (vtlDatasetFind == null) {
			VtlDataset vtlDataset = new VtlDataset();

			vtlDataset.setPersistent(true);
			vtlDataset.setName("DS_29");
			vtlDataset.setIsOnlyAScalar(false);
			vtlDataset.setTransitory(false);
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_1", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_2", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_3", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_1", VtlType.INTEGER, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("AT_1", VtlType.STRING, VtlComponentRole.VIRAL));
			datasetRepository.save(vtlDataset);

		}
		String commandStatements = "DS_r := DS_29 [ aggr Me_2 := max ( Me_1 ) , Me_3 := min ( Me_1 ) group by Id_5 ];";
		try {
			vtlTestUtils.getResult(commandStatements);
			assertTrue("contollo semantico fallito", false);

		}

		catch (Exception exception) {

			logger.info(exception);

		}

	}

//	L’operatore di aggregazione è annidato ad altri operatori analitici o di aggregazione o analitiche

//	@Test
//	@Transactional
//	public void sumControlloUno() throws Exception {
//		VtlDataset vtlDatasetFind = datasetRepository.findByNameIgnoreCase("DS_1");
//		if (vtlDatasetFind == null) {
//			VtlDataset vtlDataset = new VtlDataset();
//
//			vtlDataset.setPersistent(true);
//			vtlDataset.setName("DS_29");
//			vtlDataset.setIsOnlyAScalar(false);
//			vtlDataset.setTransitory(false);
//
//			List<VtlComponent> vtlComponents = new ArrayList<VtlComponent>();
//			VtlComponent vtlComponent;
//			vtlComponents = new ArrayList<>();
//
//			vtlComponent = new VtlComponent();
//			vtlComponent.setName("ID_1");
//			vtlComponent.setType(VtlType.INTEGER);
//			vtlComponent.setDomainValue(VtlType.INTEGER.getDomainValue());
//			vtlComponent.setDomainValueParent(VtlType.INTEGER.getDomainValueParent());
//			vtlComponent.setVtlComponentRole(VtlComponentRole.IDENTIFIER);
//			vtlComponent.setVtlDataset(vtlDataset);
//			vtlComponents.add(vtlComponent);
//
//			vtlComponent = new VtlComponent();
//			vtlComponent.setName("ID_2");
//			vtlComponent.setType(VtlType.STRING);
//			vtlComponent.setDomainValue(VtlType.STRING.getDomainValue());
//			vtlComponent.setDomainValueParent(VtlType.STRING.getDomainValueParent());
//			vtlComponent.setVtlComponentRole(VtlComponentRole.IDENTIFIER);
//			vtlComponent.setVtlDataset(vtlDataset);
//			vtlComponents.add(vtlComponent);
//
//			vtlComponent = new VtlComponent();
//			vtlComponent.setName("ID_3");
//			vtlComponent.setType(VtlType.STRING);
//			vtlComponent.setDomainValue(VtlType.STRING.getDomainValue());
//			vtlComponent.setDomainValueParent(VtlType.STRING.getDomainValueParent());
//			vtlComponent.setVtlComponentRole(VtlComponentRole.IDENTIFIER);
//			vtlComponent.setVtlDataset(vtlDataset);
//			vtlComponents.add(vtlComponent);
//
//			vtlComponent = new VtlComponent();
//			vtlComponent.setName("ME_1");
//			vtlComponent.setType(VtlType.INTEGER);
//			vtlComponent.setDomainValue(VtlType.INTEGER.getDomainValue());
//			vtlComponent.setDomainValueParent(VtlType.INTEGER.getDomainValueParent());
//			vtlComponent.setVtlComponentRole(VtlComponentRole.MEASURE);
//			vtlComponent.setVtlDataset(vtlDataset);
//			vtlComponents.add(vtlComponent);
//
//			vtlComponent = new VtlComponent();
//			vtlComponent.setName("AT_1");
//			vtlComponent.setType(VtlType.STRING);
//			vtlComponent.setDomainValue(VtlType.STRING.getDomainValue());
//			vtlComponent.setDomainValueParent(VtlType.STRING.getDomainValueParent());
//			vtlComponent.setVtlComponentRole(VtlComponentRole.VIRAL);
//			vtlComponent.setVtlDataset(vtlDataset);
//			vtlComponents.add(vtlComponent);
//
//			vtlDataset.addComponentsList(vtlComponents);
//			datasetRepository.save(vtlDataset);
//		}
//		String commandStatements = "DS_r := avg (sum ( DS_29  group by  Id_1, Id_3 ));";
//
//		try {
//			List<ResultExpression> result = vtlTestUtils.getResult(commandStatements);
//			assertTrue("contollo semantico fallito", false);
//
//		}
//
//		catch (Exception exception) {
//
//			logger.info(exception);
//
//		}
//
//	}
//

//	I tipi scalari di base di firstOperand e additionalOperand (se presenti)
//	non sono conformi ai tipi scalari di base specifici richiesti dall'operatore richiamato

	@Test
	@Transactional
	public void sumControlloDue() throws Exception {
		VtlDataset vtlDatasetFind = datasetRepository.findByNameIgnoreCase("DS_1");
		if (vtlDatasetFind == null) {
			VtlDataset vtlDataset = new VtlDataset();

			vtlDataset.setPersistent(true);
			vtlDataset.setName("DS_30");
			vtlDataset.setIsOnlyAScalar(false);
			vtlDataset.setTransitory(false);
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_1", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_2", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_3", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_1", VtlType.INTEGER, VtlComponentRole.MEASURE));
			datasetRepository.save(vtlDataset);
		}
		String commandStatements = "DS_r := sum ( DS_30 group by  ME_4 );";

		try {
			vtlTestUtils.getResult(commandStatements);
			assertTrue("contollo semantico fallito", false);

		}

		catch (Exception exception) {

			logger.info(exception);

		}

	}

//
//	L’operatore di aggregazione è annidato ad altri operatori analitici o di aggregazione o analitiche
//
//	@Test
//	@Transactional
//	public void countControlloUno() throws Exception {
//		VtlDataset vtlDatasetFind = datasetRepository.findByNameIgnoreCase("DS_1");
//		if (vtlDatasetFind == null) {
//			VtlDataset vtlDataset = new VtlDataset();
//
//			vtlDataset.setPersistent(true);
//			vtlDataset.setName("DS_33");
//			vtlDataset.setIsOnlyAScalar(false);
//			vtlDataset.setTransitory(false);
//
//			List<VtlComponent> vtlComponents = new ArrayList<VtlComponent>();
//			VtlComponent vtlComponent;
//			vtlComponents = new ArrayList<>();
//
//			vtlComponent = new VtlComponent();
//			vtlComponent.setName("ID_1");
//			vtlComponent.setType(VtlType.INTEGER);
//			vtlComponent.setDomainValue(VtlType.INTEGER.getDomainValue());
//			vtlComponent.setDomainValueParent(VtlType.INTEGER.getDomainValueParent());
//			vtlComponent.setVtlComponentRole(VtlComponentRole.IDENTIFIER);
//			vtlComponent.setVtlDataset(vtlDataset);
//			vtlComponents.add(vtlComponent);
//
//			vtlComponent = new VtlComponent();
//			vtlComponent.setName("ID_2");
//			vtlComponent.setType(VtlType.STRING);
//			vtlComponent.setDomainValue(VtlType.STRING.getDomainValue());
//			vtlComponent.setDomainValueParent(VtlType.STRING.getDomainValueParent());
//			vtlComponent.setVtlComponentRole(VtlComponentRole.IDENTIFIER);
//			vtlComponent.setVtlDataset(vtlDataset);
//			vtlComponents.add(vtlComponent);
//
//			vtlComponent = new VtlComponent();
//			vtlComponent.setName("ID_3");
//			vtlComponent.setType(VtlType.STRING);
//			vtlComponent.setDomainValue(VtlType.STRING.getDomainValue());
//			vtlComponent.setDomainValueParent(VtlType.STRING.getDomainValueParent());
//			vtlComponent.setVtlComponentRole(VtlComponentRole.IDENTIFIER);
//			vtlComponent.setVtlDataset(vtlDataset);
//			vtlComponents.add(vtlComponent);
//
//			vtlComponent = new VtlComponent();
//			vtlComponent.setName("ME_1");
//			vtlComponent.setType(VtlType.STRING);
//			vtlComponent.setDomainValue(VtlType.STRING.getDomainValue());
//			vtlComponent.setDomainValueParent(VtlType.STRING.getDomainValueParent());
//			vtlComponent.setVtlComponentRole(VtlComponentRole.MEASURE);
//			vtlComponent.setVtlDataset(vtlDataset);
//			vtlComponents.add(vtlComponent);
//
//			vtlDataset.addComponentsList(vtlComponents);
//			datasetRepository.save(vtlDataset);
//		}
//		String commandStatements = "DS_r := sum(count ( DS_33 group by  Id_1 ));";
//
//		try {
//			List<ResultExpression> result = vtlTestUtils.getResult(commandStatements);
//			assertTrue("contollo semantico fallito", false);
//
//		}
//
//		catch (Exception exception) {
//
//			logger.info(exception);
//
//		}
//
//	}
//

//	I tipi scalari di base di firstOperand e additionalOperand (se presenti)
//	non sono conformi ai tipi scalari di base specifici richiesti dall'operatore richiamato

	@Test
	@Transactional
	public void countControlloDue() throws Exception {
		VtlDataset vtlDatasetFind = datasetRepository.findByNameIgnoreCase("DS_1");
		if (vtlDatasetFind == null) {
			VtlDataset vtlDataset = new VtlDataset();

			vtlDataset.setPersistent(true);
			vtlDataset.setName("DS_33");
			vtlDataset.setIsOnlyAScalar(false);
			vtlDataset.setTransitory(false);
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_1", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_2", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_3", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_1", VtlType.STRING, VtlComponentRole.MEASURE));
			datasetRepository.save(vtlDataset);

		}
		String commandStatements = "DS_r := count ( DS_33 group by  ME_7 );";

		try {
			vtlTestUtils.getResult(commandStatements);
			assertTrue("contollo semantico fallito", false);

		}

		catch (Exception exception) {

			logger.info(exception);

		}

	}
//
//	L’operatore di aggregazione è annidato ad altri operatori analitici o di aggregazione o analitiche
//
//	@Test
//	@Transactional
//	public void minControlloUno() throws Exception {
//		VtlDataset vtlDatasetFind = datasetRepository.findByNameIgnoreCase("DS_1");
//		if (vtlDatasetFind == null) {
//			VtlDataset vtlDataset = new VtlDataset();
//
//			vtlDataset.setPersistent(true);
//			vtlDataset.setName("DS_30");
//			vtlDataset.setIsOnlyAScalar(false);
//			vtlDataset.setTransitory(false);
//
//			List<VtlComponent> vtlComponents = new ArrayList<VtlComponent>();
//			VtlComponent vtlComponent;
//			vtlComponents = new ArrayList<>();
//
//			vtlComponent = new VtlComponent();
//			vtlComponent.setName("ID_1");
//			vtlComponent.setType(VtlType.INTEGER);
//			vtlComponent.setDomainValue(VtlType.INTEGER.getDomainValue());
//			vtlComponent.setDomainValueParent(VtlType.INTEGER.getDomainValueParent());
//			vtlComponent.setVtlComponentRole(VtlComponentRole.IDENTIFIER);
//			vtlComponent.setVtlDataset(vtlDataset);
//			vtlComponents.add(vtlComponent);
//
//			vtlComponent = new VtlComponent();
//			vtlComponent.setName("ID_2");
//			vtlComponent.setType(VtlType.STRING);
//			vtlComponent.setDomainValue(VtlType.STRING.getDomainValue());
//			vtlComponent.setDomainValueParent(VtlType.STRING.getDomainValueParent());
//			vtlComponent.setVtlComponentRole(VtlComponentRole.IDENTIFIER);
//			vtlComponent.setVtlDataset(vtlDataset);
//			vtlComponents.add(vtlComponent);
//
//			vtlComponent = new VtlComponent();
//			vtlComponent.setName("ID_3");
//			vtlComponent.setType(VtlType.STRING);
//			vtlComponent.setDomainValue(VtlType.STRING.getDomainValue());
//			vtlComponent.setDomainValueParent(VtlType.STRING.getDomainValueParent());
//			vtlComponent.setVtlComponentRole(VtlComponentRole.IDENTIFIER);
//			vtlComponent.setVtlDataset(vtlDataset);
//			vtlComponents.add(vtlComponent);
//
//			vtlComponent = new VtlComponent();
//			vtlComponent.setName("ME_1");
//			vtlComponent.setType(VtlType.INTEGER);
//			vtlComponent.setDomainValue(VtlType.INTEGER.getDomainValue());
//			vtlComponent.setDomainValueParent(VtlType.INTEGER.getDomainValueParent());
//			vtlComponent.setVtlComponentRole(VtlComponentRole.MEASURE);
//			vtlComponent.setVtlDataset(vtlDataset);
//			vtlComponents.add(vtlComponent);
//
//			vtlDataset.addComponentsList(vtlComponents);
//			datasetRepository.save(vtlDataset);
//		}
//
//		String commandStatements = "DS_r := avg( min ( DS_30 group by  Id_1 ));";
//
//		try {
//			List<ResultExpression> result = vtlTestUtils.getResult(commandStatements);
//			assertTrue("contollo semantico fallito", false);
//
//		}
//
//		catch (Exception exception) {
//
//			logger.info(exception);
//
//		}
//
//	}
//

//	I tipi scalari di base di firstOperand e additionalOperand (se presenti)
//	non sono conformi ai tipi scalari di base specifici richiesti dall'operatore richiamato

	@Test
	@Transactional
	public void minControlloDue() throws Exception {
		VtlDataset vtlDatasetFind = datasetRepository.findByNameIgnoreCase("DS_1");
		if (vtlDatasetFind == null) {
			VtlDataset vtlDataset = new VtlDataset();

			vtlDataset.setPersistent(true);
			vtlDataset.setName("DS_30");
			vtlDataset.setIsOnlyAScalar(false);
			vtlDataset.setTransitory(false);
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_1", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_2", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_3", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_1", VtlType.INTEGER, VtlComponentRole.MEASURE));
			datasetRepository.save(vtlDataset);
		}
		String commandStatements = "DS_r := min ( DS_30 group by  ME_11 );";

		try {
			vtlTestUtils.getResult(commandStatements);
			assertTrue("contollo semantico fallito", false);

		}

		catch (Exception exception) {

			logger.info(exception);

		}

	}

//	L’operatore di aggregazione è annidato ad altri operatori analitici o di aggregazione o analitiche
//
//	@Test
//	@Transactional
//	public void maxControlloUno() throws Exception {
//		VtlDataset vtlDatasetFind = datasetRepository.findByNameIgnoreCase("DS_1");
//		if (vtlDatasetFind == null) {
//			VtlDataset vtlDataset = new VtlDataset();
//
//			vtlDataset.setPersistent(true);
//			vtlDataset.setName("DS_30");
//			vtlDataset.setIsOnlyAScalar(false);
//			vtlDataset.setTransitory(false);
//
//			List<VtlComponent> vtlComponents = new ArrayList<VtlComponent>();
//			VtlComponent vtlComponent;
//			vtlComponents = new ArrayList<>();
//
//			vtlComponent = new VtlComponent();
//			vtlComponent.setName("ID_1");
//			vtlComponent.setType(VtlType.INTEGER);
//			vtlComponent.setDomainValue(VtlType.INTEGER.getDomainValue());
//			vtlComponent.setDomainValueParent(VtlType.INTEGER.getDomainValueParent());
//			vtlComponent.setVtlComponentRole(VtlComponentRole.IDENTIFIER);
//			vtlComponent.setVtlDataset(vtlDataset);
//			vtlComponents.add(vtlComponent);
//
//			vtlComponent = new VtlComponent();
//			vtlComponent.setName("ID_2");
//			vtlComponent.setType(VtlType.STRING);
//			vtlComponent.setDomainValue(VtlType.STRING.getDomainValue());
//			vtlComponent.setDomainValueParent(VtlType.STRING.getDomainValueParent());
//			vtlComponent.setVtlComponentRole(VtlComponentRole.IDENTIFIER);
//			vtlComponent.setVtlDataset(vtlDataset);
//			vtlComponents.add(vtlComponent);
//
//			vtlComponent = new VtlComponent();
//			vtlComponent.setName("ID_3");
//			vtlComponent.setType(VtlType.STRING);
//			vtlComponent.setDomainValue(VtlType.STRING.getDomainValue());
//			vtlComponent.setDomainValueParent(VtlType.STRING.getDomainValueParent());
//			vtlComponent.setVtlComponentRole(VtlComponentRole.IDENTIFIER);
//			vtlComponent.setVtlDataset(vtlDataset);
//			vtlComponents.add(vtlComponent);
//
//			vtlComponent = new VtlComponent();
//			vtlComponent.setName("ME_1");
//			vtlComponent.setType(VtlType.INTEGER);
//			vtlComponent.setDomainValue(VtlType.INTEGER.getDomainValue());
//			vtlComponent.setDomainValueParent(VtlType.INTEGER.getDomainValueParent());
//			vtlComponent.setVtlComponentRole(VtlComponentRole.MEASURE);
//			vtlComponent.setVtlDataset(vtlDataset);
//			vtlComponents.add(vtlComponent);
//
//			vtlDataset.addComponentsList(vtlComponents);
//			datasetRepository.save(vtlDataset);
//		}
//		String commandStatements = "DS_r := avg( max ( DS_30 group by  Id_1 ));";
//
//		try {
//			List<ResultExpression> result = vtlTestUtils.getResult(commandStatements);
//			assertTrue("contollo semantico fallito", false);
//
//		}
//
//		catch (Exception exception) {
//
//			logger.info(exception);
//
//		}
//
//	}
//

//	I tipi scalari di base di firstOperand e additionalOperand (se presenti)
//	non sono conformi ai tipi scalari di base specifici richiesti dall'operatore richiamato

	@Test
	@Transactional
	public void maxControlloDue() throws Exception {
		VtlDataset vtlDatasetFind = datasetRepository.findByNameIgnoreCase("DS_1");
		if (vtlDatasetFind == null) {
			VtlDataset vtlDataset = new VtlDataset();

			vtlDataset.setPersistent(true);
			vtlDataset.setName("DS_30");
			vtlDataset.setIsOnlyAScalar(false);
			vtlDataset.setTransitory(false);
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_1", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_2", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_3", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_1", VtlType.INTEGER, VtlComponentRole.MEASURE));
			datasetRepository.save(vtlDataset);
		}
		String commandStatements = "DS_r := max ( DS_30 group by  ME_2 );";

		try {
			vtlTestUtils.getResult(commandStatements);
			assertTrue("contollo semantico fallito", false);

		}

		catch (Exception exception) {

			logger.info(exception);

		}

	}

//	L’operatore di aggregazione è annidato ad altri operatori analitici o di aggregazione o analitiche
//
//	@Test
//	@Transactional
//	public void medianControlloUno() throws Exception {
//		VtlDataset vtlDatasetFind = datasetRepository.findByNameIgnoreCase("DS_1");
//		if (vtlDatasetFind == null) {
//			VtlDataset vtlDataset = new VtlDataset();
//
//			vtlDataset.setPersistent(true);
//			vtlDataset.setName("DS_30");
//			vtlDataset.setIsOnlyAScalar(false);
//			vtlDataset.setTransitory(false);
//
//			List<VtlComponent> vtlComponents = new ArrayList<VtlComponent>();
//			VtlComponent vtlComponent;
//			vtlComponents = new ArrayList<>();
//
//			vtlComponent = new VtlComponent();
//			vtlComponent.setName("ID_1");
//			vtlComponent.setType(VtlType.INTEGER);
//			vtlComponent.setDomainValue(VtlType.INTEGER.getDomainValue());
//			vtlComponent.setDomainValueParent(VtlType.INTEGER.getDomainValueParent());
//			vtlComponent.setVtlComponentRole(VtlComponentRole.IDENTIFIER);
//			vtlComponent.setVtlDataset(vtlDataset);
//			vtlComponents.add(vtlComponent);
//
//			vtlComponent = new VtlComponent();
//			vtlComponent.setName("ID_2");
//			vtlComponent.setType(VtlType.STRING);
//			vtlComponent.setDomainValue(VtlType.STRING.getDomainValue());
//			vtlComponent.setDomainValueParent(VtlType.STRING.getDomainValueParent());
//			vtlComponent.setVtlComponentRole(VtlComponentRole.IDENTIFIER);
//			vtlComponent.setVtlDataset(vtlDataset);
//			vtlComponents.add(vtlComponent);
//
//			vtlComponent = new VtlComponent();
//			vtlComponent.setName("ID_3");
//			vtlComponent.setType(VtlType.STRING);
//			vtlComponent.setDomainValue(VtlType.STRING.getDomainValue());
//			vtlComponent.setDomainValueParent(VtlType.STRING.getDomainValueParent());
//			vtlComponent.setVtlComponentRole(VtlComponentRole.IDENTIFIER);
//			vtlComponent.setVtlDataset(vtlDataset);
//			vtlComponents.add(vtlComponent);
//
//			vtlComponent = new VtlComponent();
//			vtlComponent.setName("ME_1");
//			vtlComponent.setType(VtlType.INTEGER);
//			vtlComponent.setDomainValue(VtlType.INTEGER.getDomainValue());
//			vtlComponent.setDomainValueParent(VtlType.INTEGER.getDomainValueParent());
//			vtlComponent.setVtlComponentRole(VtlComponentRole.MEASURE);
//			vtlComponent.setVtlDataset(vtlDataset);
//			vtlComponents.add(vtlComponent);
//
//			vtlDataset.addComponentsList(vtlComponents);
//			datasetRepository.save(vtlDataset);
//		}
//		String commandStatements = "DS_r := avg( median ( DS_30 group by  Id_1 ));";
//
//		try {
//			List<ResultExpression> result = vtlTestUtils.getResult(commandStatements);
//			assertTrue("contollo semantico fallito", false);
//
//		}
//
//		catch (Exception exception) {
//
//			logger.info(exception);
//
//		}
//
//	}
//

//	I tipi scalari di base di firstOperand e additionalOperand (se presenti)
//	non sono conformi ai tipi scalari di base specifici richiesti dall'operatore richiamato

	@Test
	@Transactional
	public void medianControlloDue() throws Exception {
		VtlDataset vtlDatasetFind = datasetRepository.findByNameIgnoreCase("DS_1");
		if (vtlDatasetFind == null) {
			VtlDataset vtlDataset = new VtlDataset();

			vtlDataset.setPersistent(true);
			vtlDataset.setName("DS_30");
			vtlDataset.setIsOnlyAScalar(false);
			vtlDataset.setTransitory(false);
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_1", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_2", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_3", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_1", VtlType.INTEGER, VtlComponentRole.MEASURE));
			datasetRepository.save(vtlDataset);
		}
		String commandStatements = "DS_r := median ( DS_30 group by  Me_3 );";

		try {
			vtlTestUtils.getResult(commandStatements);
			assertTrue("contollo semantico fallito", false);

		}

		catch (Exception exception) {

			logger.info(exception);

		}
	}

//	I tipi scalari di base di firstOperand e additionalOperand 
//(se presenti) non sono conformi ai tipi scalari di base specifici richiesti dall'operatore richiamato

	@Test
	@Transactional
	public void stddevPopControlloDue() throws Exception {
		VtlDataset vtlDatasetFind = datasetRepository.findByNameIgnoreCase("DS_1");
		if (vtlDatasetFind == null) {
			VtlDataset vtlDataset = new VtlDataset();

			vtlDataset.setPersistent(true);
			vtlDataset.setName("DS_30");
			vtlDataset.setIsOnlyAScalar(false);
			vtlDataset.setTransitory(false);
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_1", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_2", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_3", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_1", VtlType.INTEGER, VtlComponentRole.MEASURE));
			datasetRepository.save(vtlDataset);
		}
		String commandStatements = "DS_r := stddev_pop ( DS_30 group by  Id_8 );";

		try {
			vtlTestUtils.getResult(commandStatements);
			assertTrue("contollo semantico fallito", false);

		}

		catch (Exception exception) {

			logger.info(exception);

		}
	}

	@Test
	@Transactional
	public void stddevSampControlloDue() throws Exception {
		VtlDataset vtlDatasetFind = datasetRepository.findByNameIgnoreCase("DS_1");
		if (vtlDatasetFind == null) {
			VtlDataset vtlDataset = new VtlDataset();

			vtlDataset.setPersistent(true);
			vtlDataset.setName("DS_30");
			vtlDataset.setIsOnlyAScalar(false);
			vtlDataset.setTransitory(false);
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_1", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_2", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_3", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_1", VtlType.INTEGER, VtlComponentRole.MEASURE));
			datasetRepository.save(vtlDataset);
		}
		String commandStatements = "DS_r := stddev_samp ( DS_30 group by  Id_12 );";

		try {
			vtlTestUtils.getResult(commandStatements);
			assertTrue("contollo semantico fallito", false);

		}

		catch (Exception exception) {

			logger.info(exception);

		}
	}

	@Test
	@Transactional
	public void varSampControlloDue() throws Exception {
		VtlDataset vtlDatasetFind = datasetRepository.findByNameIgnoreCase("DS_1");
		if (vtlDatasetFind == null) {
			VtlDataset vtlDataset = new VtlDataset();

			vtlDataset.setPersistent(true);
			vtlDataset.setName("DS_30");
			vtlDataset.setIsOnlyAScalar(false);
			vtlDataset.setTransitory(false);
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_1", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_2", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_3", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_1", VtlType.INTEGER, VtlComponentRole.MEASURE));
			datasetRepository.save(vtlDataset);
		}

		String commandStatements = "DS_r := var_samp ( DS_30 group by Me_7 );";
		try {
			vtlTestUtils.getResult(commandStatements);
			assertTrue("contollo semantico fallito", false);

		}

		catch (Exception exception) {

			logger.info(exception);

		}
	}
}
