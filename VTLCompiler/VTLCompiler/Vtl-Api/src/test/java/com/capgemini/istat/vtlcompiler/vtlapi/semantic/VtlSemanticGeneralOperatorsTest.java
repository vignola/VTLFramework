package com.capgemini.istat.vtlcompiler.vtlapi.semantic;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

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

@RunWith(SpringRunner.class)
@SpringBootTest

public class VtlSemanticGeneralOperatorsTest {

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

	@Test
	@Transactional
	public void persistent() throws Exception {

//			I controlli semantici che restituiscono un errore quando viene applicato l’operatore <-, 
//			si hanno se si tenta di rendere persistente un dataset senza identificativi

		VtlDataset vtlDatasetFind = datasetRepository.findByNameIgnoreCase("DS_1");

		if (vtlDatasetFind == null) {
			VtlDataset vtlDataset = new VtlDataset();

			vtlDataset.setPersistent(true);
			vtlDataset.setName("DS_1");
			vtlDataset.setIsOnlyAScalar(false);
			vtlDataset.setTransitory(false);
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_1", VtlType.INTEGER, VtlComponentRole.MEASURE));
			datasetRepository.save(vtlDataset);

		}
		String commandStatements = "DS_r   <-   DS_1;";
		try {
			vtlTestUtils.getResult(commandStatements);
			assertTrue("contollo semantico fallito", false);

		}

		catch (Exception exception) {

			logger.info(exception);

		}

	}

//			Il componente specificato (comp) deve appartenere al dataset (op)
//			Sintassi: ds#comp

	@Test
	@Transactional
	public void membership() throws Exception {

		VtlDataset vtlDatasetFind = datasetRepository.findByNameIgnoreCase("DS_1");
		if (vtlDatasetFind == null) {
			VtlDataset vtlDataset = new VtlDataset();

			vtlDataset.setPersistent(true);
			vtlDataset.setName("DS_2");
			vtlDataset.setIsOnlyAScalar(false);
			vtlDataset.setTransitory(false);
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_1", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_2", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_1", VtlType.INTEGER, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_2", VtlType.INTEGER, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("AT_1", VtlType.STRING, VtlComponentRole.VIRAL));
			datasetRepository.save(vtlDataset);

		}
		String commandStatements = "DS_r := DS_2#Me_3;";

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
	public void castToBooleanFromString() throws Exception {
		VtlDataset vtlDatasetFind = datasetRepository.findByNameIgnoreCase("DS_1");
		if (vtlDatasetFind == null) {
			VtlDataset vtlDataset = new VtlDataset();

			vtlDataset.setPersistent(true);
			vtlDataset.setName("DS_62");
			vtlDataset.setIsOnlyAScalar(false);
			vtlDataset.setTransitory(false);
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_1", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_2", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_1", VtlType.INTEGER, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_2", VtlType.NUMBER, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_3", VtlType.DATE, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_4", VtlType.BOOLEAN, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_5", VtlType.TIME, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_6", VtlType.TIME_PERIOD, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_7", VtlType.DURATION, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_8", VtlType.STRING, VtlComponentRole.MEASURE));
			datasetRepository.save(vtlDataset);
		}
		
		String commandStatements = "DS_r := DS_62[calc ME_9 := cast(ME_8, boolean)];";
		try {
			vtlTestUtils.getResult(commandStatements);
			assertTrue("controllo semantico fallito: not.allowed.cast", false);
		}
		catch (Exception exception) {
			logger.info(exception);
		}
	}
	
	@Test
	@Transactional
	public void castToBooleanFromTime() throws Exception {
		VtlDataset vtlDatasetFind = datasetRepository.findByNameIgnoreCase("DS_1");
		if (vtlDatasetFind == null) {
			VtlDataset vtlDataset = new VtlDataset();

			vtlDataset.setPersistent(true);
			vtlDataset.setName("DS_62");
			vtlDataset.setIsOnlyAScalar(false);
			vtlDataset.setTransitory(false);
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_1", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_2", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_1", VtlType.INTEGER, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_2", VtlType.NUMBER, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_3", VtlType.DATE, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_4", VtlType.BOOLEAN, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_5", VtlType.TIME, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_6", VtlType.TIME_PERIOD, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_7", VtlType.DURATION, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_8", VtlType.STRING, VtlComponentRole.MEASURE));
			datasetRepository.save(vtlDataset);
		}
		
		String commandStatements = "DS_r := DS_62[calc ME_9 := cast(ME_5, boolean)];";
		try {
			vtlTestUtils.getResult(commandStatements);
			assertTrue("controllo semantico fallito: not.allowed.cast", false);
		}
		catch (Exception exception) {
			logger.info(exception);
		}
	}
	
	@Test
	@Transactional
	public void castToBooleanFromDate() throws Exception {
		VtlDataset vtlDatasetFind = datasetRepository.findByNameIgnoreCase("DS_1");
		if (vtlDatasetFind == null) {
			VtlDataset vtlDataset = new VtlDataset();

			vtlDataset.setPersistent(true);
			vtlDataset.setName("DS_62");
			vtlDataset.setIsOnlyAScalar(false);
			vtlDataset.setTransitory(false);
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_1", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_2", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_1", VtlType.INTEGER, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_2", VtlType.NUMBER, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_3", VtlType.DATE, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_4", VtlType.BOOLEAN, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_5", VtlType.TIME, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_6", VtlType.TIME_PERIOD, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_7", VtlType.DURATION, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_8", VtlType.STRING, VtlComponentRole.MEASURE));
			datasetRepository.save(vtlDataset);
		}
		
		String commandStatements = "DS_r := DS_62[calc ME_9 := cast(ME_3, boolean)];";
		try {
			vtlTestUtils.getResult(commandStatements);
			assertTrue("controllo semantico fallito: not.allowed.cast", false);
		}
		catch (Exception exception) {
			logger.info(exception);
		}
	}
	
	@Test
	@Transactional
	public void castToBooleanFromTimePeriod() throws Exception {
		VtlDataset vtlDatasetFind = datasetRepository.findByNameIgnoreCase("DS_1");
		if (vtlDatasetFind == null) {
			VtlDataset vtlDataset = new VtlDataset();

			vtlDataset.setPersistent(true);
			vtlDataset.setName("DS_62");
			vtlDataset.setIsOnlyAScalar(false);
			vtlDataset.setTransitory(false);
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_1", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_2", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_1", VtlType.INTEGER, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_2", VtlType.NUMBER, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_3", VtlType.DATE, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_4", VtlType.BOOLEAN, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_5", VtlType.TIME, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_6", VtlType.TIME_PERIOD, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_7", VtlType.DURATION, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_8", VtlType.STRING, VtlComponentRole.MEASURE));
			datasetRepository.save(vtlDataset);
		}
		
		String commandStatements = "DS_r := DS_62[calc ME_9 := cast(ME_6, boolean)];";
		try {
			vtlTestUtils.getResult(commandStatements);
			assertTrue("controllo semantico fallito: not.allowed.cast", false);
		}
		catch (Exception exception) {
			logger.info(exception);
		}
	}
	
	@Test
	@Transactional
	public void castToBooleanFromDuration() throws Exception {
		VtlDataset vtlDatasetFind = datasetRepository.findByNameIgnoreCase("DS_1");
		if (vtlDatasetFind == null) {
			VtlDataset vtlDataset = new VtlDataset();

			vtlDataset.setPersistent(true);
			vtlDataset.setName("DS_62");
			vtlDataset.setIsOnlyAScalar(false);
			vtlDataset.setTransitory(false);
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_1", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_2", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_1", VtlType.INTEGER, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_2", VtlType.NUMBER, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_3", VtlType.DATE, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_4", VtlType.BOOLEAN, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_5", VtlType.TIME, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_6", VtlType.TIME_PERIOD, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_7", VtlType.DURATION, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_8", VtlType.STRING, VtlComponentRole.MEASURE));
			datasetRepository.save(vtlDataset);
		}
		
		String commandStatements = "DS_r := DS_62[calc ME_9 := cast(ME_7, boolean)];";
		try {
			vtlTestUtils.getResult(commandStatements);
			assertTrue("controllo semantico fallito: not.allowed.cast", false);
		}
		catch (Exception exception) {
			logger.info(exception);
		}
	}
	
	@Test
	@Transactional
	public void castToIntegerFromTime() throws Exception {
		VtlDataset vtlDatasetFind = datasetRepository.findByNameIgnoreCase("DS_1");
		if (vtlDatasetFind == null) {
			VtlDataset vtlDataset = new VtlDataset();

			vtlDataset.setPersistent(true);
			vtlDataset.setName("DS_62");
			vtlDataset.setIsOnlyAScalar(false);
			vtlDataset.setTransitory(false);
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_1", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_2", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_1", VtlType.INTEGER, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_2", VtlType.NUMBER, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_3", VtlType.DATE, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_4", VtlType.BOOLEAN, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_5", VtlType.TIME, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_6", VtlType.TIME_PERIOD, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_7", VtlType.DURATION, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_8", VtlType.STRING, VtlComponentRole.MEASURE));
			datasetRepository.save(vtlDataset);
		}
		
		String commandStatements = "DS_r := DS_62[calc ME_9 := cast(ME_5, integer)];";
		try {
			vtlTestUtils.getResult(commandStatements);
			assertTrue("controllo semantico fallito: not.allowed.cast", false);
		}
		catch (Exception exception) {
			logger.info(exception);
		}
	}
	
	@Test
	@Transactional
	public void castToIntegerFromDate() throws Exception {
		VtlDataset vtlDatasetFind = datasetRepository.findByNameIgnoreCase("DS_1");
		if (vtlDatasetFind == null) {
			VtlDataset vtlDataset = new VtlDataset();

			vtlDataset.setPersistent(true);
			vtlDataset.setName("DS_62");
			vtlDataset.setIsOnlyAScalar(false);
			vtlDataset.setTransitory(false);
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_1", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_2", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_1", VtlType.INTEGER, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_2", VtlType.NUMBER, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_3", VtlType.DATE, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_4", VtlType.BOOLEAN, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_5", VtlType.TIME, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_6", VtlType.TIME_PERIOD, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_7", VtlType.DURATION, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_8", VtlType.STRING, VtlComponentRole.MEASURE));
			datasetRepository.save(vtlDataset);
		}
		
		String commandStatements = "DS_r := DS_62[calc ME_9 := cast(ME_3, integer)];";
		try {
			vtlTestUtils.getResult(commandStatements);
			assertTrue("controllo semantico fallito: not.allowed.cast", false);
		}
		catch (Exception exception) {
			logger.info(exception);
		}
	}
	
	@Test
	@Transactional
	public void castToIntegerFromTimePeriod() throws Exception {
		VtlDataset vtlDatasetFind = datasetRepository.findByNameIgnoreCase("DS_1");
		if (vtlDatasetFind == null) {
			VtlDataset vtlDataset = new VtlDataset();

			vtlDataset.setPersistent(true);
			vtlDataset.setName("DS_62");
			vtlDataset.setIsOnlyAScalar(false);
			vtlDataset.setTransitory(false);
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_1", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_2", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_1", VtlType.INTEGER, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_2", VtlType.NUMBER, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_3", VtlType.DATE, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_4", VtlType.BOOLEAN, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_5", VtlType.TIME, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_6", VtlType.TIME_PERIOD, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_7", VtlType.DURATION, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_8", VtlType.STRING, VtlComponentRole.MEASURE));
			datasetRepository.save(vtlDataset);
		}
		
		String commandStatements = "DS_r := DS_62[calc ME_9 := cast(ME_6, integer)];";
		try {
			vtlTestUtils.getResult(commandStatements);
			assertTrue("controllo semantico fallito: not.allowed.cast", false);
		}
		catch (Exception exception) {
			logger.info(exception);
		}
	}
	
	@Test
	@Transactional
	public void castToIntegerFromDuration() throws Exception {
		VtlDataset vtlDatasetFind = datasetRepository.findByNameIgnoreCase("DS_1");
		if (vtlDatasetFind == null) {
			VtlDataset vtlDataset = new VtlDataset();

			vtlDataset.setPersistent(true);
			vtlDataset.setName("DS_62");
			vtlDataset.setIsOnlyAScalar(false);
			vtlDataset.setTransitory(false);
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_1", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_2", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_1", VtlType.INTEGER, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_2", VtlType.NUMBER, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_3", VtlType.DATE, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_4", VtlType.BOOLEAN, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_5", VtlType.TIME, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_6", VtlType.TIME_PERIOD, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_7", VtlType.DURATION, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_8", VtlType.STRING, VtlComponentRole.MEASURE));
			datasetRepository.save(vtlDataset);
		}
				
		String commandStatements = "DS_r := DS_62[calc ME_9 := cast(ME_7, integer)];";
		try {
			vtlTestUtils.getResult(commandStatements);
			assertTrue("controllo semantico fallito: not.allowed.cast", false);
		}
		catch (Exception exception) {
			logger.info(exception);
		}
	}
	
	@Test
	@Transactional
	public void castToNumberFromTime() throws Exception {
		VtlDataset vtlDatasetFind = datasetRepository.findByNameIgnoreCase("DS_1");
		if (vtlDatasetFind == null) {
			VtlDataset vtlDataset = new VtlDataset();

			vtlDataset.setPersistent(true);
			vtlDataset.setName("DS_62");
			vtlDataset.setIsOnlyAScalar(false);
			vtlDataset.setTransitory(false);
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_1", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_2", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_1", VtlType.INTEGER, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_2", VtlType.NUMBER, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_3", VtlType.DATE, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_4", VtlType.BOOLEAN, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_5", VtlType.TIME, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_6", VtlType.TIME_PERIOD, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_7", VtlType.DURATION, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_8", VtlType.STRING, VtlComponentRole.MEASURE));
			datasetRepository.save(vtlDataset);
		}
				
		String commandStatements = "DS_r := DS_62[calc ME_9 := cast(ME_5, number)];";
		try {
			vtlTestUtils.getResult(commandStatements);
			assertTrue("controllo semantico fallito: not.allowed.cast", false);
		}
		catch (Exception exception) {
			logger.info(exception);
		}
	}
	
	@Test
	@Transactional
	public void castToNumberFromDate() throws Exception {
		VtlDataset vtlDatasetFind = datasetRepository.findByNameIgnoreCase("DS_1");
		if (vtlDatasetFind == null) {
			VtlDataset vtlDataset = new VtlDataset();

			vtlDataset.setPersistent(true);
			vtlDataset.setName("DS_62");
			vtlDataset.setIsOnlyAScalar(false);
			vtlDataset.setTransitory(false);
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_1", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_2", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_1", VtlType.INTEGER, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_2", VtlType.NUMBER, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_3", VtlType.DATE, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_4", VtlType.BOOLEAN, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_5", VtlType.TIME, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_6", VtlType.TIME_PERIOD, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_7", VtlType.DURATION, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_8", VtlType.STRING, VtlComponentRole.MEASURE));
			datasetRepository.save(vtlDataset);
		}
				
		String commandStatements = "DS_r := DS_62[calc ME_9 := cast(ME_3, number)];";
		try {
			vtlTestUtils.getResult(commandStatements);
			assertTrue("controllo semantico fallito: not.allowed.cast", false);
		}
		catch (Exception exception) {
			logger.info(exception);
		}
	}
	
	@Test
	@Transactional
	public void castToNumberFromTimePeriod() throws Exception {
		VtlDataset vtlDatasetFind = datasetRepository.findByNameIgnoreCase("DS_1");
		if (vtlDatasetFind == null) {
			VtlDataset vtlDataset = new VtlDataset();

			vtlDataset.setPersistent(true);
			vtlDataset.setName("DS_62");
			vtlDataset.setIsOnlyAScalar(false);
			vtlDataset.setTransitory(false);
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_1", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_2", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_1", VtlType.INTEGER, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_2", VtlType.NUMBER, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_3", VtlType.DATE, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_4", VtlType.BOOLEAN, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_5", VtlType.TIME, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_6", VtlType.TIME_PERIOD, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_7", VtlType.DURATION, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_8", VtlType.STRING, VtlComponentRole.MEASURE));
			datasetRepository.save(vtlDataset);
		}
				
		String commandStatements = "DS_r := DS_62[calc ME_9 := cast(ME_6, number)];";
		try {
			vtlTestUtils.getResult(commandStatements);
			assertTrue("controllo semantico fallito: not.allowed.cast", false);
		}
		catch (Exception exception) {
			logger.info(exception);
		}
	}
	
	@Test
	@Transactional
	public void castToNumberFromDuration() throws Exception {
		VtlDataset vtlDatasetFind = datasetRepository.findByNameIgnoreCase("DS_1");
		if (vtlDatasetFind == null) {
			VtlDataset vtlDataset = new VtlDataset();

			vtlDataset.setPersistent(true);
			vtlDataset.setName("DS_62");
			vtlDataset.setIsOnlyAScalar(false);
			vtlDataset.setTransitory(false);
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_1", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_2", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_1", VtlType.INTEGER, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_2", VtlType.NUMBER, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_3", VtlType.DATE, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_4", VtlType.BOOLEAN, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_5", VtlType.TIME, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_6", VtlType.TIME_PERIOD, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_7", VtlType.DURATION, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_8", VtlType.STRING, VtlComponentRole.MEASURE));
			datasetRepository.save(vtlDataset);
		}
				
		String commandStatements = "DS_r := DS_62[calc ME_9 := cast(ME_7, number)];";
		try {
			vtlTestUtils.getResult(commandStatements);
			assertTrue("controllo semantico fallito: not.allowed.cast", false);
		}
		catch (Exception exception) {
			logger.info(exception);
		}
	}
	
	@Test
	@Transactional
	public void castToTimeFromInteger() throws Exception {
		VtlDataset vtlDatasetFind = datasetRepository.findByNameIgnoreCase("DS_1");
		if (vtlDatasetFind == null) {
			VtlDataset vtlDataset = new VtlDataset();

			vtlDataset.setPersistent(true);
			vtlDataset.setName("DS_62");
			vtlDataset.setIsOnlyAScalar(false);
			vtlDataset.setTransitory(false);
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_1", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_2", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_1", VtlType.INTEGER, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_2", VtlType.NUMBER, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_3", VtlType.DATE, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_4", VtlType.BOOLEAN, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_5", VtlType.TIME, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_6", VtlType.TIME_PERIOD, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_7", VtlType.DURATION, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_8", VtlType.STRING, VtlComponentRole.MEASURE));
			datasetRepository.save(vtlDataset);
		}
				
		String commandStatements = "DS_r := DS_62[calc ME_9 := cast(ME_1, time)];";
		try {
			vtlTestUtils.getResult(commandStatements);
			assertTrue("controllo semantico fallito: not.allowed.cast", false);
		}
		catch (Exception exception) {
			logger.info(exception);
		}
	}
	
	@Test
	@Transactional
	public void castToTimeFromNumber() throws Exception {
		VtlDataset vtlDatasetFind = datasetRepository.findByNameIgnoreCase("DS_1");
		if (vtlDatasetFind == null) {
			VtlDataset vtlDataset = new VtlDataset();

			vtlDataset.setPersistent(true);
			vtlDataset.setName("DS_62");
			vtlDataset.setIsOnlyAScalar(false);
			vtlDataset.setTransitory(false);
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_1", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_2", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_1", VtlType.INTEGER, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_2", VtlType.NUMBER, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_3", VtlType.DATE, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_4", VtlType.BOOLEAN, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_5", VtlType.TIME, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_6", VtlType.TIME_PERIOD, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_7", VtlType.DURATION, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_8", VtlType.STRING, VtlComponentRole.MEASURE));
			datasetRepository.save(vtlDataset);
		}
				
		String commandStatements = "DS_r := DS_62[calc ME_9 := cast(ME_1, time)];";
		try {
			vtlTestUtils.getResult(commandStatements);
			assertTrue("controllo semantico fallito: not.allowed.cast", false);
		}
		catch (Exception exception) {
			logger.info(exception.getMessage());
		}
	}
	
	@Test
	@Transactional
	public void castToTimeFromBoolean() throws Exception {
		VtlDataset vtlDatasetFind = datasetRepository.findByNameIgnoreCase("DS_1");
		if (vtlDatasetFind == null) {
			VtlDataset vtlDataset = new VtlDataset();

			vtlDataset.setPersistent(true);
			vtlDataset.setName("DS_62");
			vtlDataset.setIsOnlyAScalar(false);
			vtlDataset.setTransitory(false);
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_1", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_2", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_1", VtlType.INTEGER, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_2", VtlType.NUMBER, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_3", VtlType.DATE, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_4", VtlType.BOOLEAN, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_5", VtlType.TIME, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_6", VtlType.TIME_PERIOD, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_7", VtlType.DURATION, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_8", VtlType.STRING, VtlComponentRole.MEASURE));
			datasetRepository.save(vtlDataset);
		}
				
		String commandStatements = "DS_r := DS_62[calc ME_9 := cast(ME_4, time)];";
		try {
			vtlTestUtils.getResult(commandStatements);
			assertTrue("controllo semantico fallito: not.allowed.cast", false);
		}
		catch (Exception exception) {
			logger.info(exception);
		}
	}
	
	@Test
	@Transactional
	public void castToTimeFromDuration() throws Exception {
		VtlDataset vtlDatasetFind = datasetRepository.findByNameIgnoreCase("DS_1");
		if (vtlDatasetFind == null) {
			VtlDataset vtlDataset = new VtlDataset();

			vtlDataset.setPersistent(true);
			vtlDataset.setName("DS_62");
			vtlDataset.setIsOnlyAScalar(false);
			vtlDataset.setTransitory(false);
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_1", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_2", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_1", VtlType.INTEGER, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_2", VtlType.NUMBER, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_3", VtlType.DATE, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_4", VtlType.BOOLEAN, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_5", VtlType.TIME, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_6", VtlType.TIME_PERIOD, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_7", VtlType.DURATION, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_8", VtlType.STRING, VtlComponentRole.MEASURE));
			datasetRepository.save(vtlDataset);
		}
				
		String commandStatements = "DS_r := DS_62[calc ME_9 := cast(ME_7, time)];";
		try {
			vtlTestUtils.getResult(commandStatements);
			assertTrue("controllo semantico fallito: not.allowed.cast", false);
		}
		catch (Exception exception) {
			logger.info(exception);
		}
	}
	
	@Test
	@Transactional
	public void castToDateFromInteger() throws Exception {
		VtlDataset vtlDatasetFind = datasetRepository.findByNameIgnoreCase("DS_1");
		if (vtlDatasetFind == null) {
			VtlDataset vtlDataset = new VtlDataset();

			vtlDataset.setPersistent(true);
			vtlDataset.setName("DS_62");
			vtlDataset.setIsOnlyAScalar(false);
			vtlDataset.setTransitory(false);
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_1", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_2", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_1", VtlType.INTEGER, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_2", VtlType.NUMBER, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_3", VtlType.DATE, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_4", VtlType.BOOLEAN, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_5", VtlType.TIME, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_6", VtlType.TIME_PERIOD, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_7", VtlType.DURATION, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_8", VtlType.STRING, VtlComponentRole.MEASURE));
			datasetRepository.save(vtlDataset);
		}
				
		String commandStatements = "DS_r := DS_62[calc ME_9 := cast(ME_1, date)];";
		try {
			vtlTestUtils.getResult(commandStatements);
			assertTrue("controllo semantico fallito: not.allowed.cast", false);
		}
		catch (Exception exception) {
			logger.info(exception);
		}
	}
	
	@Test
	@Transactional
	public void castToDateFromNumber() throws Exception {
		VtlDataset vtlDatasetFind = datasetRepository.findByNameIgnoreCase("DS_1");
		if (vtlDatasetFind == null) {
			VtlDataset vtlDataset = new VtlDataset();

			vtlDataset.setPersistent(true);
			vtlDataset.setName("DS_62");
			vtlDataset.setIsOnlyAScalar(false);
			vtlDataset.setTransitory(false);
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_1", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_2", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_1", VtlType.INTEGER, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_2", VtlType.NUMBER, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_3", VtlType.DATE, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_4", VtlType.BOOLEAN, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_5", VtlType.TIME, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_6", VtlType.TIME_PERIOD, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_7", VtlType.DURATION, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_8", VtlType.STRING, VtlComponentRole.MEASURE));
			datasetRepository.save(vtlDataset);
		}
				
		String commandStatements = "DS_r := DS_62[calc ME_9 := cast(ME_2, date)];";
		try {
			vtlTestUtils.getResult(commandStatements);
			assertTrue("controllo semantico fallito: not.allowed.cast", false);
		}
		catch (Exception exception) {
			logger.info(exception);
		}
	}
	
	@Test
	@Transactional
	public void castToDateFromBoolean() throws Exception {
		VtlDataset vtlDatasetFind = datasetRepository.findByNameIgnoreCase("DS_1");
		if (vtlDatasetFind == null) {
			VtlDataset vtlDataset = new VtlDataset();

			vtlDataset.setPersistent(true);
			vtlDataset.setName("DS_62");
			vtlDataset.setIsOnlyAScalar(false);
			vtlDataset.setTransitory(false);
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_1", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_2", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_1", VtlType.INTEGER, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_2", VtlType.NUMBER, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_3", VtlType.DATE, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_4", VtlType.BOOLEAN, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_5", VtlType.TIME, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_6", VtlType.TIME_PERIOD, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_7", VtlType.DURATION, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_8", VtlType.STRING, VtlComponentRole.MEASURE));
			datasetRepository.save(vtlDataset);
		}
				
		String commandStatements = "DS_r := DS_62[calc ME_9 := cast(ME_4, date)];";
		try {
			vtlTestUtils.getResult(commandStatements);
			assertTrue("controllo semantico fallito: not.allowed.cast", false);
		}
		catch (Exception exception) {
			logger.info(exception);
		}
	}
	
	@Test
	@Transactional
	public void castToDateFromTime() throws Exception {
		VtlDataset vtlDatasetFind = datasetRepository.findByNameIgnoreCase("DS_1");
		if (vtlDatasetFind == null) {
			VtlDataset vtlDataset = new VtlDataset();

			vtlDataset.setPersistent(true);
			vtlDataset.setName("DS_62");
			vtlDataset.setIsOnlyAScalar(false);
			vtlDataset.setTransitory(false);
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_1", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_2", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_1", VtlType.INTEGER, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_2", VtlType.NUMBER, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_3", VtlType.DATE, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_4", VtlType.BOOLEAN, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_5", VtlType.TIME, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_6", VtlType.TIME_PERIOD, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_7", VtlType.DURATION, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_8", VtlType.STRING, VtlComponentRole.MEASURE));
			datasetRepository.save(vtlDataset);
		}
				
		String commandStatements = "DS_r := DS_62[calc ME_9 := cast(ME_5, date)];";
		try {
			vtlTestUtils.getResult(commandStatements);
			assertTrue("controllo semantico fallito: not.allowed.cast", false);
		}
		catch (Exception exception) {
			logger.info(exception);
		}
	}
	
	@Test
	@Transactional
	public void castToDateFromDuration() throws Exception {
		VtlDataset vtlDatasetFind = datasetRepository.findByNameIgnoreCase("DS_1");
		if (vtlDatasetFind == null) {
			VtlDataset vtlDataset = new VtlDataset();

			vtlDataset.setPersistent(true);
			vtlDataset.setName("DS_62");
			vtlDataset.setIsOnlyAScalar(false);
			vtlDataset.setTransitory(false);
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_1", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_2", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_1", VtlType.INTEGER, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_2", VtlType.NUMBER, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_3", VtlType.DATE, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_4", VtlType.BOOLEAN, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_5", VtlType.TIME, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_6", VtlType.TIME_PERIOD, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_7", VtlType.DURATION, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_8", VtlType.STRING, VtlComponentRole.MEASURE));
			datasetRepository.save(vtlDataset);
		}
				
		String commandStatements = "DS_r := DS_62[calc ME_9 := cast(ME_7, date)];";
		try {
			vtlTestUtils.getResult(commandStatements);
			assertTrue("controllo semantico fallito: not.allowed.cast", false);
		}
		catch (Exception exception) {
			logger.info(exception);
		}
	}
	
	@Test
	@Transactional
	public void castToTimePeriodFromInteger() throws Exception {
		VtlDataset vtlDatasetFind = datasetRepository.findByNameIgnoreCase("DS_1");
		if (vtlDatasetFind == null) {
			VtlDataset vtlDataset = new VtlDataset();

			vtlDataset.setPersistent(true);
			vtlDataset.setName("DS_62");
			vtlDataset.setIsOnlyAScalar(false);
			vtlDataset.setTransitory(false);
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_1", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_2", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_1", VtlType.INTEGER, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_2", VtlType.NUMBER, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_3", VtlType.DATE, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_4", VtlType.BOOLEAN, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_5", VtlType.TIME, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_6", VtlType.TIME_PERIOD, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_7", VtlType.DURATION, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_8", VtlType.STRING, VtlComponentRole.MEASURE));
			datasetRepository.save(vtlDataset);
		}
				
		String commandStatements = "DS_r := DS_62[calc ME_9 := cast(ME_1, time_period)];";
		try {
			vtlTestUtils.getResult(commandStatements);
			assertTrue("controllo semantico fallito: not.allowed.cast", false);
		}
		catch (Exception exception) {
			logger.info(exception);
		}
	}
	
	@Test
	@Transactional
	public void castToTimePeriodFromNumber() throws Exception {
		VtlDataset vtlDatasetFind = datasetRepository.findByNameIgnoreCase("DS_1");
		if (vtlDatasetFind == null) {
			VtlDataset vtlDataset = new VtlDataset();

			vtlDataset.setPersistent(true);
			vtlDataset.setName("DS_62");
			vtlDataset.setIsOnlyAScalar(false);
			vtlDataset.setTransitory(false);
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_1", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_2", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_1", VtlType.INTEGER, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_2", VtlType.NUMBER, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_3", VtlType.DATE, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_4", VtlType.BOOLEAN, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_5", VtlType.TIME, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_6", VtlType.TIME_PERIOD, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_7", VtlType.DURATION, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_8", VtlType.STRING, VtlComponentRole.MEASURE));
			datasetRepository.save(vtlDataset);
		}
				
		String commandStatements = "DS_r := DS_62[calc ME_9 := cast(ME_2, time_period)];";
		try {
			vtlTestUtils.getResult(commandStatements);
			assertTrue("controllo semantico fallito: not.allowed.cast", false);
		}
		catch (Exception exception) {
			logger.info(exception);
		}
	}
	
	@Test
	@Transactional
	public void castToTimePeriodFromBoolean() throws Exception {
		VtlDataset vtlDatasetFind = datasetRepository.findByNameIgnoreCase("DS_1");
		if (vtlDatasetFind == null) {
			VtlDataset vtlDataset = new VtlDataset();

			vtlDataset.setPersistent(true);
			vtlDataset.setName("DS_62");
			vtlDataset.setIsOnlyAScalar(false);
			vtlDataset.setTransitory(false);
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_1", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_2", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_1", VtlType.INTEGER, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_2", VtlType.NUMBER, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_3", VtlType.DATE, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_4", VtlType.BOOLEAN, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_5", VtlType.TIME, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_6", VtlType.TIME_PERIOD, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_7", VtlType.DURATION, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_8", VtlType.STRING, VtlComponentRole.MEASURE));
			datasetRepository.save(vtlDataset);
		}
				
		String commandStatements = "DS_r := DS_62[calc ME_9 := cast(ME_4, time_period)];";
		try {
			vtlTestUtils.getResult(commandStatements);
			assertTrue("controllo semantico fallito: not.allowed.cast", false);
		}
		catch (Exception exception) {
			logger.info(exception);
		}
	}
	
	@Test
	@Transactional
	public void castToTimePeriodFromTime() throws Exception {
		VtlDataset vtlDatasetFind = datasetRepository.findByNameIgnoreCase("DS_1");
		if (vtlDatasetFind == null) {
			VtlDataset vtlDataset = new VtlDataset();

			vtlDataset.setPersistent(true);
			vtlDataset.setName("DS_62");
			vtlDataset.setIsOnlyAScalar(false);
			vtlDataset.setTransitory(false);
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_1", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_2", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_1", VtlType.INTEGER, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_2", VtlType.NUMBER, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_3", VtlType.DATE, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_4", VtlType.BOOLEAN, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_5", VtlType.TIME, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_6", VtlType.TIME_PERIOD, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_7", VtlType.DURATION, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_8", VtlType.STRING, VtlComponentRole.MEASURE));
			datasetRepository.save(vtlDataset);
		}
				
		String commandStatements = "DS_r := DS_62[calc ME_9 := cast(ME_5, time_period)];";
		try {
			vtlTestUtils.getResult(commandStatements);
			assertTrue("controllo semantico fallito: not.allowed.cast", false);
		}
		catch (Exception exception) {
			logger.info(exception);
		}
	}
	
	@Test
	@Transactional
	public void castToTimePeriodFromDuration() throws Exception {
		VtlDataset vtlDatasetFind = datasetRepository.findByNameIgnoreCase("DS_1");
		if (vtlDatasetFind == null) {
			VtlDataset vtlDataset = new VtlDataset();

			vtlDataset.setPersistent(true);
			vtlDataset.setName("DS_62");
			vtlDataset.setIsOnlyAScalar(false);
			vtlDataset.setTransitory(false);
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_1", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_2", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_1", VtlType.INTEGER, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_2", VtlType.NUMBER, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_3", VtlType.DATE, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_4", VtlType.BOOLEAN, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_5", VtlType.TIME, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_6", VtlType.TIME_PERIOD, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_7", VtlType.DURATION, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_8", VtlType.STRING, VtlComponentRole.MEASURE));
			datasetRepository.save(vtlDataset);
		}
				
		String commandStatements = "DS_r := DS_62[calc ME_9 := cast(ME_7, time_period)];";
		try {
			vtlTestUtils.getResult(commandStatements);
			assertTrue("controllo semantico fallito: not.allowed.cast", false);
		}
		catch (Exception exception) {
			logger.info(exception);
		}
	}
	
	@Test
	@Transactional
	public void castToDurationFromInteger() throws Exception {
		VtlDataset vtlDatasetFind = datasetRepository.findByNameIgnoreCase("DS_1");
		if (vtlDatasetFind == null) {
			VtlDataset vtlDataset = new VtlDataset();

			vtlDataset.setPersistent(true);
			vtlDataset.setName("DS_62");
			vtlDataset.setIsOnlyAScalar(false);
			vtlDataset.setTransitory(false);
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_1", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_2", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_1", VtlType.INTEGER, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_2", VtlType.NUMBER, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_3", VtlType.DATE, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_4", VtlType.BOOLEAN, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_5", VtlType.TIME, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_6", VtlType.TIME_PERIOD, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_7", VtlType.DURATION, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_8", VtlType.STRING, VtlComponentRole.MEASURE));
			datasetRepository.save(vtlDataset);
		}
				
		String commandStatements = "DS_r := DS_62[calc ME_9 := cast(ME_1, duration)];";
		try {
			vtlTestUtils.getResult(commandStatements);
			assertTrue("controllo semantico fallito: not.allowed.cast", false);
		}
		catch (Exception exception) {
			logger.info(exception);
		}
	}
	
	@Test
	@Transactional
	public void castToDurationFromNumber() throws Exception {
		VtlDataset vtlDatasetFind = datasetRepository.findByNameIgnoreCase("DS_1");
		if (vtlDatasetFind == null) {
			VtlDataset vtlDataset = new VtlDataset();

			vtlDataset.setPersistent(true);
			vtlDataset.setName("DS_62");
			vtlDataset.setIsOnlyAScalar(false);
			vtlDataset.setTransitory(false);
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_1", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_2", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_1", VtlType.INTEGER, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_2", VtlType.NUMBER, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_3", VtlType.DATE, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_4", VtlType.BOOLEAN, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_5", VtlType.TIME, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_6", VtlType.TIME_PERIOD, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_7", VtlType.DURATION, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_8", VtlType.STRING, VtlComponentRole.MEASURE));
			datasetRepository.save(vtlDataset);
		}
				
		String commandStatements = "DS_r := DS_62[calc ME_9 := cast(ME_2, duration)];";
		try {
			vtlTestUtils.getResult(commandStatements);
			assertTrue("controllo semantico fallito: not.allowed.cast", false);
		}
		catch (Exception exception) {
			logger.info(exception);
		}
	}
	
	@Test
	@Transactional
	public void castToDurationFromBoolean() throws Exception {
		VtlDataset vtlDatasetFind = datasetRepository.findByNameIgnoreCase("DS_1");
		if (vtlDatasetFind == null) {
			VtlDataset vtlDataset = new VtlDataset();

			vtlDataset.setPersistent(true);
			vtlDataset.setName("DS_62");
			vtlDataset.setIsOnlyAScalar(false);
			vtlDataset.setTransitory(false);
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_1", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_2", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_1", VtlType.INTEGER, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_2", VtlType.NUMBER, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_3", VtlType.DATE, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_4", VtlType.BOOLEAN, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_5", VtlType.TIME, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_6", VtlType.TIME_PERIOD, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_7", VtlType.DURATION, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_8", VtlType.STRING, VtlComponentRole.MEASURE));
			datasetRepository.save(vtlDataset);
		}
				
		String commandStatements = "DS_r := DS_62[calc ME_9 := cast(ME_4, duration)];";
		try {
			vtlTestUtils.getResult(commandStatements);
			assertTrue("controllo semantico fallito: not.allowed.cast", false);
		}
		catch (Exception exception) {
			logger.info(exception);
		}
	}
	
	@Test
	@Transactional
	public void castToDurationFromTime() throws Exception {
		VtlDataset vtlDatasetFind = datasetRepository.findByNameIgnoreCase("DS_1");
		if (vtlDatasetFind == null) {
			VtlDataset vtlDataset = new VtlDataset();

			vtlDataset.setPersistent(true);
			vtlDataset.setName("DS_62");
			vtlDataset.setIsOnlyAScalar(false);
			vtlDataset.setTransitory(false);
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_1", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_2", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_1", VtlType.INTEGER, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_2", VtlType.NUMBER, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_3", VtlType.DATE, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_4", VtlType.BOOLEAN, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_5", VtlType.TIME, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_6", VtlType.TIME_PERIOD, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_7", VtlType.DURATION, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_8", VtlType.STRING, VtlComponentRole.MEASURE));
			datasetRepository.save(vtlDataset);
		}
				
		String commandStatements = "DS_r := DS_62[calc ME_9 := cast(ME_5, duration)];";
		try {
			vtlTestUtils.getResult(commandStatements);
			assertTrue("controllo semantico fallito: not.allowed.cast", false);
		}
		catch (Exception exception) {
			logger.info(exception);
		}
	}
	
	@Test
	@Transactional
	public void castToDurationFromDate() throws Exception {
		VtlDataset vtlDatasetFind = datasetRepository.findByNameIgnoreCase("DS_1");
		if (vtlDatasetFind == null) {
			VtlDataset vtlDataset = new VtlDataset();

			vtlDataset.setPersistent(true);
			vtlDataset.setName("DS_62");
			vtlDataset.setIsOnlyAScalar(false);
			vtlDataset.setTransitory(false);
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_1", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_2", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_1", VtlType.INTEGER, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_2", VtlType.NUMBER, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_3", VtlType.DATE, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_4", VtlType.BOOLEAN, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_5", VtlType.TIME, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_6", VtlType.TIME_PERIOD, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_7", VtlType.DURATION, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_8", VtlType.STRING, VtlComponentRole.MEASURE));
			datasetRepository.save(vtlDataset);
		}
				
		String commandStatements = "DS_r := DS_62[calc ME_9 := cast(ME_3, duration)];";
		try {
			vtlTestUtils.getResult(commandStatements);
			assertTrue("controllo semantico fallito: not.allowed.cast", false);
		}
		catch (Exception exception) {
			logger.info(exception);
		}
	}
	
	@Test
	@Transactional
	public void castToDurationFromTimePeriod() throws Exception {
		VtlDataset vtlDatasetFind = datasetRepository.findByNameIgnoreCase("DS_1");
		if (vtlDatasetFind == null) {
			VtlDataset vtlDataset = new VtlDataset();

			vtlDataset.setPersistent(true);
			vtlDataset.setName("DS_62");
			vtlDataset.setIsOnlyAScalar(false);
			vtlDataset.setTransitory(false);
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_1", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_2", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_1", VtlType.INTEGER, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_2", VtlType.NUMBER, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_3", VtlType.DATE, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_4", VtlType.BOOLEAN, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_5", VtlType.TIME, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_6", VtlType.TIME_PERIOD, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_7", VtlType.DURATION, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_8", VtlType.STRING, VtlComponentRole.MEASURE));
			datasetRepository.save(vtlDataset);
		}
				
		String commandStatements = "DS_r := DS_62[calc ME_9 := cast(ME_6, duration)];";
		try {
			vtlTestUtils.getResult(commandStatements);
			assertTrue("controllo semantico fallito: not.allowed.cast", false);
		}
		catch (Exception exception) {
			logger.info(exception);
		}
	}
	
	@Test
	@Transactional
	public void castToDateFromTimePeriodWithMask() throws Exception {
		VtlDataset vtlDatasetFind = datasetRepository.findByNameIgnoreCase("DS_1");
		if (vtlDatasetFind == null) {
			VtlDataset vtlDataset = new VtlDataset();

			vtlDataset.setPersistent(true);
			vtlDataset.setName("DS_62");
			vtlDataset.setIsOnlyAScalar(false);
			vtlDataset.setTransitory(false);
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_1", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_2", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_1", VtlType.INTEGER, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_2", VtlType.NUMBER, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_3", VtlType.DATE, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_4", VtlType.BOOLEAN, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_5", VtlType.TIME, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_6", VtlType.TIME_PERIOD, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_7", VtlType.DURATION, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_8", VtlType.STRING, VtlComponentRole.MEASURE));
			datasetRepository.save(vtlDataset);
		}		
		String commandStatements = "DS_r := DS_62[calc ME_9 := cast(ME_6, date)];";
		try {
			vtlTestUtils.getResult(commandStatements);
			assertTrue("controllo semantico fallito: need.cast.mask", false);
		}
		catch (Exception exception) {
			logger.info(exception);
		}
	}
	
	@Test
	@Transactional
	public void castToNummberFromStringWithMask() throws Exception {
		VtlDataset vtlDatasetFind = datasetRepository.findByNameIgnoreCase("DS_1");
		if (vtlDatasetFind == null) {
			VtlDataset vtlDataset = new VtlDataset();

			vtlDataset.setPersistent(true);
			vtlDataset.setName("DS_62");
			vtlDataset.setIsOnlyAScalar(false);
			vtlDataset.setTransitory(false);
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_1", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_2", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_1", VtlType.INTEGER, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_2", VtlType.NUMBER, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_3", VtlType.DATE, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_4", VtlType.BOOLEAN, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_5", VtlType.TIME, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_6", VtlType.TIME_PERIOD, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_7", VtlType.DURATION, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_8", VtlType.STRING, VtlComponentRole.MEASURE));
			datasetRepository.save(vtlDataset);
		}
		String commandStatements = "DS_r := DS_62[calc ME_9 := cast(ME_8, number)];";
		try {
			vtlTestUtils.getResult(commandStatements);
			assertTrue("controllo semantico fallito: need.cast.mask", false);
		}
		catch (Exception exception) {
			logger.info(exception);
		}
	}
	
	@Test
	@Transactional
	public void castToTimeFromStringWithMask() throws Exception {
		VtlDataset vtlDatasetFind = datasetRepository.findByNameIgnoreCase("DS_1");
		if (vtlDatasetFind == null) {
			VtlDataset vtlDataset = new VtlDataset();

			vtlDataset.setPersistent(true);
			vtlDataset.setName("DS_62");
			vtlDataset.setIsOnlyAScalar(false);
			vtlDataset.setTransitory(false);
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_1", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_2", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_1", VtlType.INTEGER, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_2", VtlType.NUMBER, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_3", VtlType.DATE, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_4", VtlType.BOOLEAN, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_5", VtlType.TIME, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_6", VtlType.TIME_PERIOD, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_7", VtlType.DURATION, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_8", VtlType.STRING, VtlComponentRole.MEASURE));
			datasetRepository.save(vtlDataset);
		}
		String commandStatements = "DS_r := DS_62[calc ME_9 := cast(ME_8, time)];";
		try {
			vtlTestUtils.getResult(commandStatements);
			assertTrue("controllo semantico fallito: need.cast.mask", false);
		}
		catch (Exception exception) {
			logger.info(exception);
		}
	}
	
	@Test
	@Transactional
	public void castToDateFromStringWithMask() throws Exception {
		VtlDataset vtlDatasetFind = datasetRepository.findByNameIgnoreCase("DS_1");
		if (vtlDatasetFind == null) {
			VtlDataset vtlDataset = new VtlDataset();

			vtlDataset.setPersistent(true);
			vtlDataset.setName("DS_62");
			vtlDataset.setIsOnlyAScalar(false);
			vtlDataset.setTransitory(false);
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_1", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_2", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_1", VtlType.INTEGER, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_2", VtlType.NUMBER, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_3", VtlType.DATE, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_4", VtlType.BOOLEAN, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_5", VtlType.TIME, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_6", VtlType.TIME_PERIOD, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_7", VtlType.DURATION, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_8", VtlType.STRING, VtlComponentRole.MEASURE));
			datasetRepository.save(vtlDataset);
		}
		String commandStatements = "DS_r := DS_62[calc ME_9 := cast(ME_8, date)];";
		try {
			vtlTestUtils.getResult(commandStatements);
			assertTrue("controllo semantico fallito: need.cast.mask", false);
		}
		catch (Exception exception) {
			logger.info(exception);
		}
	}
	
	@Test
	@Transactional
	public void castToTimePeriodFromStringWithMask() throws Exception {
		VtlDataset vtlDatasetFind = datasetRepository.findByNameIgnoreCase("DS_1");
		if (vtlDatasetFind == null) {
			VtlDataset vtlDataset = new VtlDataset();

			vtlDataset.setPersistent(true);
			vtlDataset.setName("DS_62");
			vtlDataset.setIsOnlyAScalar(false);
			vtlDataset.setTransitory(false);
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_1", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_2", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_1", VtlType.INTEGER, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_2", VtlType.NUMBER, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_3", VtlType.DATE, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_4", VtlType.BOOLEAN, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_5", VtlType.TIME, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_6", VtlType.TIME_PERIOD, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_7", VtlType.DURATION, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_8", VtlType.STRING, VtlComponentRole.MEASURE));
			datasetRepository.save(vtlDataset);
		}
		String commandStatements = "DS_r := DS_62[calc ME_9 := cast(ME_8, time_period)];";
		try {
			vtlTestUtils.getResult(commandStatements);
			assertTrue("controllo semantico fallito: need.cast.mask", false);
		}
		catch (Exception exception) {
			logger.info(exception);
		}
	}
	
	@Test
	@Transactional
	public void castToStringFromDateWithMask() throws Exception {
		VtlDataset vtlDatasetFind = datasetRepository.findByNameIgnoreCase("DS_1");
		if (vtlDatasetFind == null) {
			VtlDataset vtlDataset = new VtlDataset();

			vtlDataset.setPersistent(true);
			vtlDataset.setName("DS_62");
			vtlDataset.setIsOnlyAScalar(false);
			vtlDataset.setTransitory(false);
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_1", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_2", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_1", VtlType.INTEGER, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_2", VtlType.NUMBER, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_3", VtlType.DATE, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_4", VtlType.BOOLEAN, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_5", VtlType.TIME, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_6", VtlType.TIME_PERIOD, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_7", VtlType.DURATION, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_8", VtlType.STRING, VtlComponentRole.MEASURE));
			datasetRepository.save(vtlDataset);
		}
		String commandStatements = "DS_r := DS_62[calc ME_9 := cast(ME_3, string)];";
		try {
			vtlTestUtils.getResult(commandStatements);
			assertTrue("controllo semantico fallito: need.cast.mask", false);
		}
		catch (Exception exception) {
			logger.info(exception);
		}
	}
	
	@Test
	@Transactional
	public void castToStringFromTimeWithMask() throws Exception {
		VtlDataset vtlDatasetFind = datasetRepository.findByNameIgnoreCase("DS_1");
		if (vtlDatasetFind == null) {
			VtlDataset vtlDataset = new VtlDataset();

			vtlDataset.setPersistent(true);
			vtlDataset.setName("DS_62");
			vtlDataset.setIsOnlyAScalar(false);
			vtlDataset.setTransitory(false);
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_1", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_2", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_1", VtlType.INTEGER, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_2", VtlType.NUMBER, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_3", VtlType.DATE, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_4", VtlType.BOOLEAN, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_5", VtlType.TIME, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_6", VtlType.TIME_PERIOD, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_7", VtlType.DURATION, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_8", VtlType.STRING, VtlComponentRole.MEASURE));
			datasetRepository.save(vtlDataset);
		}
		String commandStatements = "DS_r := DS_62[calc ME_9 := cast(ME_5, string)];";
		try {
			vtlTestUtils.getResult(commandStatements);
			assertTrue("controllo semantico fallito: need.cast.mask", false);
		}
		catch (Exception exception) {
			logger.info(exception);
		}
	}
	
	@Test
	@Transactional
	public void castToDurationFromStringWithMask() throws Exception {
		VtlDataset vtlDatasetFind = datasetRepository.findByNameIgnoreCase("DS_1");
		if (vtlDatasetFind == null) {
			VtlDataset vtlDataset = new VtlDataset();

			vtlDataset.setPersistent(true);
			vtlDataset.setName("DS_62");
			vtlDataset.setIsOnlyAScalar(false);
			vtlDataset.setTransitory(false);
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_1", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_2", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_1", VtlType.INTEGER, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_2", VtlType.NUMBER, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_3", VtlType.DATE, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_4", VtlType.BOOLEAN, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_5", VtlType.TIME, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_6", VtlType.TIME_PERIOD, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_7", VtlType.DURATION, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_8", VtlType.STRING, VtlComponentRole.MEASURE));
			datasetRepository.save(vtlDataset);
		}
		String commandStatements = "DS_r := DS_62[calc ME_9 := cast(ME_8, duration)];";
		try {
			vtlTestUtils.getResult(commandStatements);
			assertTrue("controllo semantico fallito: need.cast.mask", false);
		}
		catch (Exception exception) {
			logger.info(exception);
		}
	}
	
	@Test
	@Transactional
	public void castToStringFromDurationWithMask() throws Exception {
		VtlDataset vtlDatasetFind = datasetRepository.findByNameIgnoreCase("DS_1");
		if (vtlDatasetFind == null) {
			VtlDataset vtlDataset = new VtlDataset();

			vtlDataset.setPersistent(true);
			vtlDataset.setName("DS_62");
			vtlDataset.setIsOnlyAScalar(false);
			vtlDataset.setTransitory(false);
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_1", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_2", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_1", VtlType.INTEGER, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_2", VtlType.NUMBER, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_3", VtlType.DATE, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_4", VtlType.BOOLEAN, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_5", VtlType.TIME, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_6", VtlType.TIME_PERIOD, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_7", VtlType.DURATION, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_8", VtlType.STRING, VtlComponentRole.MEASURE));
			datasetRepository.save(vtlDataset);
		}
		String commandStatements = "DS_r := DS_62[calc ME_9 := cast(ME_7, string)];";
		try {
			vtlTestUtils.getResult(commandStatements);
			assertTrue("controllo semantico fallito: need.cast.mask", false);
		}
		catch (Exception exception) {
			logger.info(exception);
		}
	}
}
