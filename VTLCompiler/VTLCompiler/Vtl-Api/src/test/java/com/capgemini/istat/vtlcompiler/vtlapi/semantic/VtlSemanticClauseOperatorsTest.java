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

public class VtlSemanticClauseOperatorsTest {

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

//	viene applicato l’operatore filter si ha se il tipo di dato di filterCondition non è boolean

	@Test
	@Transactional
	public void filterControlloUno() throws Exception {
		VtlDataset vtlDatasetFind = datasetRepository.findByNameIgnoreCase("DS_1");
		if (vtlDatasetFind == null) {
			VtlDataset vtlDataset = new VtlDataset();

			vtlDataset.setPersistent(true);
			vtlDataset.setName("DS_29");
			vtlDataset.setIsOnlyAScalar(false);
			vtlDataset.setTransitory(false);
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_1", VtlType.TIME, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_2", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_3", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_1", VtlType.INTEGER, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("AT_1", VtlType.STRING, VtlComponentRole.VIRAL));
			datasetRepository.save(vtlDataset);

		}
		String commandStatements = "DS_r := DS_29  [ filter Id_1 = 1 and Me_1 < 10 ];";

		try {
			vtlTestUtils.getResult(commandStatements);
			assertTrue("contollo semantico fallito", false);

		}

		catch (Exception exception) {

			logger.info(exception);

		}

	}

//	Vengono definite condizioni su componenti che non sono del dataset di input

	@Test
	@Transactional
	public void filterControlloDue() throws Exception {
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
		String commandStatements = "DS_r := DS_29  [ filter Id_4 = 1 and Me_3 < 10 ];";

		try {
			vtlTestUtils.getResult(commandStatements);
			assertTrue("contollo semantico fallito", false);

		}

		catch (Exception exception) {

			logger.info(exception);

		}

	}

//	Il controllo semantico che restituisce un errore quando viene applicato l’operatore calc 
//	si ha se tra i calcComp compare il nome di uno o più identificativi del dataset.

	@Test
	@Transactional
	public void calcControlloUno() throws Exception {
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
		String commandStatements = "DS_r := DS_30 [ calc ID_1:= ID_6 * 2 ];";

		try {
			vtlTestUtils.getResult(commandStatements);
			assertTrue("contollo semantico fallito", false);

		}

		catch (Exception exception) {

			logger.info(exception);

		}

	}

//	Il controllo semantico restituisce un errore quando viene applicato l’operatore keep se
//		i componenti della keep non sono componenti del dataset di input

	@Test
	@Transactional
	public void keepControlloUno() throws Exception {
		VtlDataset vtlDatasetFind = datasetRepository.findByNameIgnoreCase("DS_1");
		if (vtlDatasetFind == null) {
			VtlDataset vtlDataset = new VtlDataset();

			vtlDataset.setPersistent(true);
			vtlDataset.setName("DS_31");
			vtlDataset.setIsOnlyAScalar(false);
			vtlDataset.setTransitory(false);
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_1", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_2", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_3", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_1", VtlType.INTEGER, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_2", VtlType.INTEGER, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("AT_1", VtlType.STRING, VtlComponentRole.VIRAL));
			datasetRepository.save(vtlDataset);

		}
		String commandStatements = "DS_r := DS_31 [ keep  Me_8 ];";

		try {
			vtlTestUtils.getResult(commandStatements);
			assertTrue("contollo semantico fallito", false);

		}

		catch (Exception exception) {

			logger.info(exception);

		}
	}

//	Il controllo semantico restituisce un errore quando viene applicato l’operatore keep se
//		i componenti della keep sono identificativi per il dataset di input

	@Test
	@Transactional
	public void keepControlloDue() throws Exception {
		VtlDataset vtlDatasetFind = datasetRepository.findByNameIgnoreCase("DS_1");
		if (vtlDatasetFind == null) {
			VtlDataset vtlDataset = new VtlDataset();

			vtlDataset.setPersistent(true);
			vtlDataset.setName("DS_31");
			vtlDataset.setIsOnlyAScalar(false);
			vtlDataset.setTransitory(false);
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_1", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_2", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_3", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_1", VtlType.INTEGER, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_2", VtlType.INTEGER, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("AT_1", VtlType.STRING, VtlComponentRole.VIRAL));
			datasetRepository.save(vtlDataset);

		}
		String commandStatements = "DS_r := DS_31 [ keep  ID_1 ];";

		try {
			vtlTestUtils.getResult(commandStatements);
			assertTrue("contollo semantico fallito", false);

		}

		catch (Exception exception) {

			logger.info(exception);

		}

	}
	
//	Questo operatore non può essere applicato a valori scalari
	
@Test
@Transactional
public void keepControlloTre() throws Exception {
	VtlDataset vtlDatasetFind = datasetRepository.findByNameIgnoreCase("DS_1");
	if (vtlDatasetFind == null) {
		VtlDataset vtlDataset = new VtlDataset();

		vtlDataset.setPersistent(true);
		vtlDataset.setName("DS_31");
		vtlDataset.setIsOnlyAScalar(false);
		vtlDataset.setTransitory(false);
		vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_1", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
		vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_2", VtlType.STRING, VtlComponentRole.IDENTIFIER));
		vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_3", VtlType.STRING, VtlComponentRole.IDENTIFIER));
		vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_1", VtlType.INTEGER, VtlComponentRole.MEASURE));
		vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_2", VtlType.INTEGER, VtlComponentRole.MEASURE));
		vtlDataset.addComponent(vtlTestUtils.getVtlComponent("AT_1", VtlType.STRING, VtlComponentRole.VIRAL));
		datasetRepository.save(vtlDataset);
	}
	String commandStatements = "DS_r := DS_31 [ keep  15 ];";

	try {
		vtlTestUtils.getResult(commandStatements);
		assertTrue("contollo semantico fallito", false);

	}

	catch (Exception exception) {

		logger.info(exception);

	}
}

//	Il controllo semantico restituisce un errore quando viene applicato l’operatore drop se 
//		i componenti della drop non sono componenti del dataset di input

	@Test
	@Transactional
	public void dropControlloUno() throws Exception {
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
		String commandStatements = "DS_r := DS_29 [ drop  At_3 ];";

		try {
			vtlTestUtils.getResult(commandStatements);
			assertTrue("contollo semantico fallito", false);

		}

		catch (Exception exception) {

			logger.info(exception);

		}

	}

//	Il controllo semantico restituisce un errore quando viene applicato l’operatore drop se 
//  	i componenti della drop sono identificativi per il dataset di input

	@Test
	@Transactional
	public void dropControlloDue() throws Exception {
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
		String commandStatements = "DS_r := DS_29 [ drop  ID_2 ];";

		try {
			vtlTestUtils.getResult(commandStatements);
			assertTrue("contollo semantico fallito", false);

		}

		catch (Exception exception) {

			logger.info(exception);

		}

	}

//	Il controllo semantico restituisce un errore quando viene applicato l’operatore rename 
//	se i componenti della rename per le parti comp_from non sono componenti del dataset di input

	@Test
	@Transactional
	public void renameControlloUno() throws Exception {
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
		String commandStatements = "DS_r := DS_29 [ rename  Me_3 to Me_2, At_5 to At_2];";

		try {
			vtlTestUtils.getResult(commandStatements);
			assertTrue("contollo semantico fallito", false);

		}

		catch (Exception exception) {

			logger.info(exception);

		}

	}

//	Il controllo semantico restituisce un errore quando viene applicato l’operatore pivot se 
//		i componenti della sub non sono componenti del dataset di input

	@Test
	@Transactional
	public void subControlloUno() throws Exception {
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
		String commandStatements = "DS_r := DS_29 [ sub  Id_4 = 1,  Id_2 = \"A\" ];";

		try {
			vtlTestUtils.getResult(commandStatements);
			assertTrue("contollo semantico fallito", false);

		}

		catch (Exception exception) {

			logger.info(exception);

		}

	}

//	Il controllo semantico restituisce un errore quando viene applicato l’operatore pivot se 
//	i componenti della sub non sono identificativi

	@Test
	@Transactional
	public void subControlloDue() throws Exception {
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
		String commandStatements = "DS_r := DS_29 [ sub  ME_1 = 1,  AT_1 = \"A\" ];";

		try {
			vtlTestUtils.getResult(commandStatements);
			assertTrue("contollo semantico fallito", false);

		}

		catch (Exception exception) {

			logger.info(exception);

		}

	}

}
