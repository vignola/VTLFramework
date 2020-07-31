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
public class VtlSemanticConditionalOperatorsTest {

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

//		condition è un dataset con più misure o con una sola misura non booleana

	@Test
	@Transactional
	public void ifThenElse() throws Exception {
		VtlDataset vtlDatasetFind = datasetRepository.findByNameIgnoreCase("DS_1");
		if (vtlDatasetFind == null) {
			VtlDataset vtlDataset = new VtlDataset();

			vtlDataset.setPersistent(true);
			vtlDataset.setName("DS_18");
			vtlDataset.setIsOnlyAScalar(false);
			vtlDataset.setTransitory(false);
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_1", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_2", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_3", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_4", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			datasetRepository.save(vtlDataset);

			VtlDataset vtlDatasetSecond = new VtlDataset();
			vtlDatasetSecond.setPersistent(true);
			vtlDatasetSecond.setName("DS_11");
			vtlDatasetSecond.setIsOnlyAScalar(false);
			vtlDatasetSecond.setTransitory(false);
			vtlDatasetSecond
					.addComponent(vtlTestUtils.getVtlComponent("ID_1", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDatasetSecond
					.addComponent(vtlTestUtils.getVtlComponent("ID_2", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDatasetSecond
					.addComponent(vtlTestUtils.getVtlComponent("ID_3", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDatasetSecond
					.addComponent(vtlTestUtils.getVtlComponent("ID_4", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			datasetRepository.save(vtlDatasetSecond);

			VtlDataset vtlDatasetThird = new VtlDataset();
			vtlDatasetThird.setPersistent(true);
			vtlDatasetThird.setName("DS_28");
			vtlDatasetThird.setIsOnlyAScalar(false);
			vtlDatasetThird.setTransitory(false);
			vtlDatasetThird
					.addComponent(vtlTestUtils.getVtlComponent("ID_1", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDatasetThird
					.addComponent(vtlTestUtils.getVtlComponent("ID_2", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDatasetThird
					.addComponent(vtlTestUtils.getVtlComponent("ID_3", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDatasetThird
					.addComponent(vtlTestUtils.getVtlComponent("ID_4", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			datasetRepository.save(vtlDatasetThird);

		}
		String commandStatements = "DS_r := if ( DS_18#Id_4 = \"F\" ) then DS_11 else DS_28;";

		try {
			vtlTestUtils.getResult(commandStatements);
			assertTrue("contollo semantico fallito", false);

		}

		catch (Exception exception) {

			logger.info(exception);

		}

	}

//	thenOperand, elseOperand  =dataset avente la stessa struttura

	@Test
	@Transactional
	public void ifThenElseControlloDue() throws Exception {
		VtlDataset vtlDatasetFind = datasetRepository.findByNameIgnoreCase("DS_1");
		if (vtlDatasetFind == null) {
			VtlDataset vtlDataset = new VtlDataset();

			vtlDataset.setPersistent(true);
			vtlDataset.setName("DS_18");
			vtlDataset.setIsOnlyAScalar(false);
			vtlDataset.setTransitory(false);
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_1", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_2", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_3", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_4", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_1", VtlType.INTEGER, VtlComponentRole.MEASURE));
			datasetRepository.save(vtlDataset);

			VtlDataset vtlDatasetSecond = new VtlDataset();
			vtlDatasetSecond.setPersistent(true);
			vtlDatasetSecond.setName("DS_11");
			vtlDatasetSecond.setIsOnlyAScalar(false);
			vtlDatasetSecond.setTransitory(false);
			vtlDatasetSecond
					.addComponent(vtlTestUtils.getVtlComponent("ID_12", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDatasetSecond
					.addComponent(vtlTestUtils.getVtlComponent("ID_22", VtlType.STRING, VtlComponentRole.IDENTIFIER));

			vtlDatasetSecond
					.addComponent(vtlTestUtils.getVtlComponent("ID_42", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDatasetSecond
					.addComponent(vtlTestUtils.getVtlComponent("ME_10", VtlType.NUMBER, VtlComponentRole.MEASURE));
			datasetRepository.save(vtlDatasetSecond);

			VtlDataset vtlDatasetThird = new VtlDataset();
			vtlDatasetThird.setPersistent(true);
			vtlDatasetThird.setName("DS_28");
			vtlDatasetThird.setIsOnlyAScalar(false);
			vtlDatasetThird.setTransitory(false);
			vtlDatasetThird
					.addComponent(vtlTestUtils.getVtlComponent("ID_11", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDatasetThird
					.addComponent(vtlTestUtils.getVtlComponent("ID_17", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDatasetThird
					.addComponent(vtlTestUtils.getVtlComponent("ID_25", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDatasetThird
					.addComponent(vtlTestUtils.getVtlComponent("ID_33", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDatasetThird
					.addComponent(vtlTestUtils.getVtlComponent("ID_49", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDatasetThird
					.addComponent(vtlTestUtils.getVtlComponent("ME_5", VtlType.INTEGER, VtlComponentRole.MEASURE));
			datasetRepository.save(vtlDatasetThird);
		}
		String commandStatements = "DS_r := if ( DS_18#Id_4 = \"F\" ) then DS_11 else DS_28;";

		try {
			vtlTestUtils.getResult(commandStatements);
			assertTrue("contollo semantico fallito", false);

		}

		catch (Exception exception) {

			logger.info(exception);

		}

	}

//		condition e thenoperand non hanno stessi identificativi

	@Test
	@Transactional
	public void ifThenElseControlloTre() throws Exception {
		VtlDataset vtlDatasetFind = datasetRepository.findByNameIgnoreCase("DS_1");
		if (vtlDatasetFind == null) {
			VtlDataset vtlDataset = new VtlDataset();

			vtlDataset.setPersistent(true);
			vtlDataset.setName("DS_18");
			vtlDataset.setIsOnlyAScalar(false);
			vtlDataset.setTransitory(false);
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_1", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_2", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_3", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_45", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_1", VtlType.INTEGER, VtlComponentRole.MEASURE));
			datasetRepository.save(vtlDataset);

			VtlDataset vtlDatasetSecond = new VtlDataset();
			vtlDatasetSecond.setPersistent(true);
			vtlDatasetSecond.setName("DS_11");
			vtlDatasetSecond.setIsOnlyAScalar(false);
			vtlDatasetSecond.setTransitory(false);
			vtlDatasetSecond
					.addComponent(vtlTestUtils.getVtlComponent("ID_5", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDatasetSecond
					.addComponent(vtlTestUtils.getVtlComponent("ID_6", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDatasetSecond
					.addComponent(vtlTestUtils.getVtlComponent("ID_7", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDatasetSecond
					.addComponent(vtlTestUtils.getVtlComponent("ID_8", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDatasetSecond
					.addComponent(vtlTestUtils.getVtlComponent("ME_1", VtlType.NUMBER, VtlComponentRole.MEASURE));
			datasetRepository.save(vtlDatasetSecond);

			VtlDataset vtlDatasetThird = new VtlDataset();
			vtlDatasetThird.setPersistent(true);
			vtlDatasetThird.setName("DS_28");
			vtlDatasetThird.setIsOnlyAScalar(false);
			vtlDatasetThird.setTransitory(false);
			vtlDatasetThird
					.addComponent(vtlTestUtils.getVtlComponent("ID_11", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDatasetThird
					.addComponent(vtlTestUtils.getVtlComponent("ID_25", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDatasetThird
					.addComponent(vtlTestUtils.getVtlComponent("ID_33", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDatasetThird
					.addComponent(vtlTestUtils.getVtlComponent("ID_49", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDatasetThird
					.addComponent(vtlTestUtils.getVtlComponent("ME_1", VtlType.INTEGER, VtlComponentRole.MEASURE));
			datasetRepository.save(vtlDatasetThird);
		}
		String commandStatements = "DS_r := if ( DS_18#Id_4 = \"F\" ) then DS_11 else DS_28;";

		try {
			vtlTestUtils.getResult(commandStatements);
			assertTrue("contollo semantico fallito", false);

		}

		catch (Exception exception) {

			logger.info(exception);

		}

	}

//	le misure di thenOperand e lo scalare elseOperand non hanno stesso tipo di dato

	@Test
	@Transactional
	public void ifThenElseControlloQuattro() throws Exception {
		VtlDataset vtlDatasetFind = datasetRepository.findByNameIgnoreCase("DS_1");
		if (vtlDatasetFind == null) {
			VtlDataset vtlDataset = new VtlDataset();
			vtlDataset.setPersistent(true);
			vtlDataset.setName("DS_18");
			vtlDataset.setIsOnlyAScalar(false);
			vtlDataset.setTransitory(false);
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_1", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_2", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_3", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_4", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_1", VtlType.INTEGER, VtlComponentRole.MEASURE));
			datasetRepository.save(vtlDataset);

			VtlDataset vtlDatasetSecond = new VtlDataset();
			vtlDatasetSecond.setPersistent(true);
			vtlDatasetSecond.setName("DS_11");
			vtlDatasetSecond.setIsOnlyAScalar(false);
			vtlDatasetSecond.setTransitory(false);
			vtlDatasetSecond
					.addComponent(vtlTestUtils.getVtlComponent("ID_1", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDatasetSecond
					.addComponent(vtlTestUtils.getVtlComponent("ID_2", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDatasetSecond
					.addComponent(vtlTestUtils.getVtlComponent("ID_2", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDatasetSecond
					.addComponent(vtlTestUtils.getVtlComponent("ID_4", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDatasetSecond
					.addComponent(vtlTestUtils.getVtlComponent("ME_1", VtlType.NUMBER, VtlComponentRole.MEASURE));
			datasetRepository.save(vtlDatasetSecond);

			VtlDataset vtlDatasetThird = new VtlDataset();
			vtlDatasetThird.setPersistent(true);
			vtlDatasetThird.setName("DS_28");
			vtlDatasetThird.setIsOnlyAScalar(false);
			vtlDatasetThird.setTransitory(false);
			vtlDatasetThird
					.addComponent(vtlTestUtils.getVtlComponent("ID_1", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDatasetThird
					.addComponent(vtlTestUtils.getVtlComponent("ID_2", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDatasetThird
					.addComponent(vtlTestUtils.getVtlComponent("ID_3", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDatasetThird
					.addComponent(vtlTestUtils.getVtlComponent("ID_4", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDatasetThird.addComponent(vtlTestUtils.getVtlComponent("ME_1", VtlType.TIME, VtlComponentRole.MEASURE));
			datasetRepository.save(vtlDatasetThird);
		}
		String commandStatements = "DS_r := if ( DS_18#Id_4 = \"F\" ) then DS_11 else DS_28;";

		try {
			vtlTestUtils.getResult(commandStatements);
			assertTrue("contollo semantico fallito", false);

		}

		catch (Exception exception) {

			logger.info(exception);

		}
	}

//  	I due operandi op1 e op2 di input non sono dello stesso dipo di dato

	@Test
	@Transactional
	public void nvl() throws Exception {
		VtlDataset vtlDatasetFind = datasetRepository.findByNameIgnoreCase("DS_1");
		if (vtlDatasetFind == null) {
			VtlDataset vtlDataset = new VtlDataset();
			vtlDataset.setPersistent(true);
			vtlDataset.setName("DS_18");
			vtlDataset.setIsOnlyAScalar(false);
			vtlDataset.setTransitory(false);
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_1", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_2", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_3", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_4", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_1", VtlType.INTEGER, VtlComponentRole.MEASURE));
			datasetRepository.save(vtlDataset);
		}
		String commandStatements = "DS_r := nvl ( DS_18, ciao );";

		try {
			vtlTestUtils.getResult(commandStatements);
			assertTrue("contollo semantico fallito", false);

		}

		catch (Exception exception) {

			logger.info(exception);

		}

	}
//	Il component e lo scalare o i due component non hanno stesso tipo di dato 

	@Test
	@Transactional
	public void nvlControlloDue() throws Exception {
		VtlDataset vtlDatasetFind = datasetRepository.findByNameIgnoreCase("DS_1");
		if (vtlDatasetFind == null) {
			VtlDataset vtlDataset = new VtlDataset();

			vtlDataset.setPersistent(true);
			vtlDataset.setName("DS_18");
			vtlDataset.setIsOnlyAScalar(false);
			vtlDataset.setTransitory(false);
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_1", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_2", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_3", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_4", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_1", VtlType.INTEGER, VtlComponentRole.MEASURE));
			datasetRepository.save(vtlDataset);
		}
		String commandStatements = "DS_r := nvl ( DS_18, ciao );";

		try {
			vtlTestUtils.getResult(commandStatements);
			assertTrue("contollo semantico fallito", false);

		}

		catch (Exception exception) {

			logger.info(exception);

		}

	}
}
