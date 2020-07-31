package com.capgemini.istat.vtlcompiler.vtlapi.traslate;

import com.capgemini.istat.vtlcompiler.vtlapi.utils.TranslationUtilsTestService;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.dataset.VtlComponentRole;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.dataset.VtlDataset;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.dataset.VtlType;
import com.capgemini.istat.vtlcompiler.vtlcommon.repository.DatasetRepository;
import com.capgemini.istat.vtlcompiler.vtlsqlbuilder.service.utility.DbTableUtilityService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class VtlStringOperatorsTranslationTest {

    private TranslationUtilsTestService translationUtilTest;
    
	private DatasetRepository datasetRepository;
	
	private DbTableUtilityService dbTableUtilityService;
	
	private Environment environment; 
	
	@Autowired
    public void setTranslationUtilTest(TranslationUtilsTestService translationUtilTest) {
		this.translationUtilTest = translationUtilTest;
	}
	
	@Autowired
	public void setDatasetRepository(DatasetRepository datasetRepository) {
		this.datasetRepository = datasetRepository;
	}
	
	@Autowired
	public void setDbTableUtilityService(DbTableUtilityService dbTableUtilityService) {
		this.dbTableUtilityService = dbTableUtilityService;
	}
	
	@Autowired
	public void setEnvironment(Environment environment) {
		this.environment = environment;
	}
	
	@Test
	@Transactional
	public void concatenationString() throws Exception {
		dbTableUtilityService.resetSchema("t");
		VtlDataset vtlDatasetFind = datasetRepository.findByNameIgnoreCase("DS_1");
		if (vtlDatasetFind == null) {
			VtlDataset vtlDataset = new VtlDataset();

			vtlDataset.setPersistent(true);
			vtlDataset.setName("DS_4");
			vtlDataset.setIsOnlyAScalar(false);
			vtlDataset.setTransitory(false);
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ID_1", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ID_2", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ME_1", VtlType.STRING, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ME_2", VtlType.STRING, VtlComponentRole.MEASURE));
			datasetRepository.save(vtlDataset);

		}
		
		System.out.println("Il profilo attivo è " + environment.getActiveProfiles()[0]);

		String comandSqlResult = "";
		String profileActive = environment.getActiveProfiles()[0];
		if (profileActive.equalsIgnoreCase("oracleSql")) {
			comandSqlResult = "CREATE TABLE DS_r AS (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,concat(t0.ME_1,' world!') AS ME_1,concat(t0.ME_2,' world!') AS ME_2 FROM DS_4 t0);CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);"; 
		} else if (profileActive.equalsIgnoreCase("sqlServer")) {
			comandSqlResult = "SELECT t1.* INTO DS_r FROM (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,concat(t0.ME_1,' world!') AS ME_1,concat(t0.ME_2,' world!') AS ME_2 FROM DS_4 t0) t1;CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);"; 
		} else if (profileActive.equalsIgnoreCase("mySql")) {
			comandSqlResult = "CREATE TABLE DS_r AS (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,concat(t0.ME_1,' world!') AS ME_1,concat(t0.ME_2,' world!') AS ME_2 FROM DS_4 t0);CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);"; 
		} else if (profileActive.equalsIgnoreCase("postgreSql")) {
			comandSqlResult = "CREATE TABLE DS_r AS (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,concat(t0.ME_1,' world!') AS ME_1,concat(t0.ME_2,' world!') AS ME_2 FROM DS_4 t0);CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);"; 
		}
		
		String commandStatements = "DS_r := DS_4|| \" world!\";";
		String result = translationUtilTest.translate(commandStatements);
		System.out.println("Print queryConcatenationString: " +result);
		System.out.println("Print queryAttesaConcatenationString: " +comandSqlResult);
		assertEquals("Test operator ConcatenationString", comandSqlResult, result);

	}
	
	@Test
	@Transactional
	public void concatenationDs() throws Exception {
		dbTableUtilityService.resetSchema("t");
		VtlDataset vtlDatasetFind = datasetRepository.findByNameIgnoreCase("DS_1");
		if (vtlDatasetFind == null) {
			VtlDataset vtlDataset = new VtlDataset();

			vtlDataset.setPersistent(true);
			vtlDataset.setName("DS_4");
			vtlDataset.setIsOnlyAScalar(false);
			vtlDataset.setTransitory(false);
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ID_1", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ID_2", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ME_1", VtlType.STRING, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ME_2", VtlType.STRING, VtlComponentRole.MEASURE));
			datasetRepository.save(vtlDataset);

			VtlDataset vtlDatasetSecond = new VtlDataset();

			vtlDatasetSecond.setPersistent(true);
			vtlDatasetSecond.setName("DS_5");
			vtlDatasetSecond.setIsOnlyAScalar(false);
			vtlDatasetSecond.setTransitory(false);
			vtlDatasetSecond
					.addComponent(translationUtilTest.getVtlComponent("ID_2", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDatasetSecond
					.addComponent(translationUtilTest.getVtlComponent("ME_1", VtlType.STRING, VtlComponentRole.MEASURE));
			datasetRepository.save(vtlDatasetSecond);
		}
		
		System.out.println("Il profilo attivo è " + environment.getActiveProfiles()[0]);

		String comandSqlResult = "";
		String profileActive = environment.getActiveProfiles()[0];
		if (profileActive.equalsIgnoreCase("oracleSql")) {
			comandSqlResult = "CREATE TABLE DS_r AS (SELECT t2.ID_1 AS ID_1,t2.ID_2 AS ID_2,concat(t2.ME_1,t1.ME_1) AS ME_1 FROM DS_4 t2 INNER JOIN DS_5 t1 ON ((t2.ID_2 = t1.ID_2)));CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);"; 
		} else if (profileActive.equalsIgnoreCase("sqlServer")) {
			comandSqlResult = "SELECT t3.* INTO DS_r FROM (SELECT t2.ID_1 AS ID_1,t2.ID_2 AS ID_2,concat(t2.ME_1,t1.ME_1) AS ME_1 FROM DS_4 t2 INNER JOIN DS_5 t1 ON ((t2.ID_2 = t1.ID_2))) t3;CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);"; 
		} else if (profileActive.equalsIgnoreCase("mySql")) {
			comandSqlResult = "CREATE TABLE DS_r AS (SELECT t2.ID_1 AS ID_1,t2.ID_2 AS ID_2,concat(t2.ME_1,t1.ME_1) AS ME_1 FROM DS_4 t2 INNER JOIN DS_5 t1 ON ((t2.ID_2 = t1.ID_2)));CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);"; 
		} else if (profileActive.equalsIgnoreCase("postgreSql")) {
			comandSqlResult = "CREATE TABLE DS_r AS (SELECT t2.ID_1 AS ID_1,t2.ID_2 AS ID_2,concat(t2.ME_1,t1.ME_1) AS ME_1 FROM DS_4 t2 INNER JOIN DS_5 t1 ON ((t2.ID_2 = t1.ID_2)));CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);"; 
		}
		
		String commandStatements = "DS_r := DS_4||DS_5;";
		String result = translationUtilTest.translate(commandStatements);
		System.out.println("Print queryConcatenationDs: " +result);
		System.out.println("Print queryAttesaConcatenationDs: " +comandSqlResult);
		assertEquals("Test operator ConcatenationDs", comandSqlResult, result);

	}
	
	@Test
	@Transactional
	public void concatenationComponent() throws Exception {
		dbTableUtilityService.resetSchema("t");
		VtlDataset vtlDatasetFind = datasetRepository.findByNameIgnoreCase("DS_1");
		if (vtlDatasetFind == null) {
			VtlDataset vtlDataset = new VtlDataset();

			vtlDataset.setPersistent(true);
			vtlDataset.setName("DS_4");
			vtlDataset.setIsOnlyAScalar(false);
			vtlDataset.setTransitory(false);
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ID_1", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ID_2", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ME_1", VtlType.STRING, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ME_2", VtlType.STRING, VtlComponentRole.MEASURE));
			datasetRepository.save(vtlDataset);
		}
		
		System.out.println("Il profilo attivo è " + environment.getActiveProfiles()[0]);

		String comandSqlResult = "";
		String profileActive = environment.getActiveProfiles()[0];
		if (profileActive.equalsIgnoreCase("oracleSql")) {
			comandSqlResult = "CREATE TABLE DS_r AS (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,t0.ME_1 AS ME_1,t0.ME_2 AS ME_2,concat(t0.ME_1,'world') AS Me1,concat(t0.ME_1,t0.ME_2) AS Me3 FROM DS_4 t0);CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);"; 
		} else if (profileActive.equalsIgnoreCase("sqlServer")) {
			comandSqlResult = "SELECT t1.* INTO DS_r FROM (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,t0.ME_1 AS ME_1,t0.ME_2 AS ME_2,concat(t0.ME_1,'world') AS Me1,concat(t0.ME_1,t0.ME_2) AS Me3 FROM DS_4 t0) t1;CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);"; 
		} else if (profileActive.equalsIgnoreCase("mySql")) {
			comandSqlResult = "CREATE TABLE DS_r AS (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,t0.ME_1 AS ME_1,t0.ME_2 AS ME_2,concat(t0.ME_1,'world') AS Me1,concat(t0.ME_1,t0.ME_2) AS Me3 FROM DS_4 t0);CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);"; 
		} else if (profileActive.equalsIgnoreCase("postgreSql")) {
			comandSqlResult = "CREATE TABLE DS_r AS (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,t0.ME_1 AS ME_1,t0.ME_2 AS ME_2,concat(t0.ME_1,'world') AS Me1,concat(t0.ME_1,t0.ME_2) AS Me3 FROM DS_4 t0);CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);"; 
		}
		
		String commandStatements = "DS_r := DS_4 [calc Me1:=Me_1|| \"world\", Me3:= Me_1|| Me_2];";
		String result = translationUtilTest.translate(commandStatements);
		System.out.println("Print queryConcatenationComponent: " +result);
		System.out.println("Print queryAttesaConcatenationComponent: " +comandSqlResult);
		assertEquals("Test operator ConcatenationComponent", comandSqlResult, result);

	}

	@Test
	@Transactional
	public void whitespaceRemoval() throws Exception {
		dbTableUtilityService.resetSchema("t");
		VtlDataset vtlDatasetFind = datasetRepository.findByNameIgnoreCase("DS_1");
		if (vtlDatasetFind == null) {
			VtlDataset vtlDataset = new VtlDataset();

			vtlDataset.setPersistent(true);
			vtlDataset.setName("DS_6");
			vtlDataset.setIsOnlyAScalar(false);
			vtlDataset.setTransitory(false);
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ID_1", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ID_2", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ME_1", VtlType.STRING, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("AT_1", VtlType.STRING, VtlComponentRole.VIRAL));
			datasetRepository.save(vtlDataset);

		}
		
		System.out.println("Il profilo attivo è " + environment.getActiveProfiles()[0]);

		String comandSqlResult = "";
		String profileActive = environment.getActiveProfiles()[0];
		if (profileActive.equalsIgnoreCase("oracleSql")) {
			comandSqlResult = "CREATE TABLE DS_r AS (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,rtrim(t0.ME_1) AS ME_1,t0.AT_1 AS AT_1 FROM DS_6 t0);"+"CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);"+"CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);"; 
		} else if (profileActive.equalsIgnoreCase("sqlServer")) {
			comandSqlResult = "SELECT t1.* INTO DS_r FROM (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,rtrim(t0.ME_1) AS ME_1,t0.AT_1 AS AT_1 FROM DS_6 t0) t1;CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);"; 
		} else if (profileActive.equalsIgnoreCase("mySql")) {
			comandSqlResult = "CREATE TABLE DS_r AS (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,rtrim(t0.ME_1) AS ME_1,t0.AT_1 AS AT_1 FROM DS_6 t0);"+"CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);"+"CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);";  
		} else if (profileActive.equalsIgnoreCase("postgreSql")) {
			comandSqlResult = "CREATE TABLE DS_r AS (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,rtrim(t0.ME_1) AS ME_1,t0.AT_1 AS AT_1 FROM DS_6 t0);"+"CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);"+"CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);"; 
		}
		
		String commandStatements = "DS_r := rtrim(DS_6);";
		String result = translationUtilTest.translate(commandStatements);
		System.out.println("Print queryWhitespaceRemoval: " +result);
		System.out.println("Print queryAttesaWhitespaceRemoval: " +comandSqlResult);
		assertEquals("Test operator WhitespaceRemoval", comandSqlResult, result);

	}

	@Test
	@Transactional
	public void whitespaceRemovalComponent() throws Exception {
		dbTableUtilityService.resetSchema("t");
		VtlDataset vtlDatasetFind = datasetRepository.findByNameIgnoreCase("DS_1");
		if (vtlDatasetFind == null) {
			VtlDataset vtlDataset = new VtlDataset();

			vtlDataset.setPersistent(true);
			vtlDataset.setName("DS_6");
			vtlDataset.setIsOnlyAScalar(false);
			vtlDataset.setTransitory(false);
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ID_1", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ID_2", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ME_1", VtlType.STRING, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("AT_1", VtlType.STRING, VtlComponentRole.VIRAL));
			datasetRepository.save(vtlDataset);

		}
		
		System.out.println("Il profilo attivo è " + environment.getActiveProfiles()[0]);

		String comandSqlResult = "";
		String profileActive = environment.getActiveProfiles()[0];
		if (profileActive.equalsIgnoreCase("oracleSql")) {
			comandSqlResult = "CREATE TABLE DS_r AS (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,t0.ME_1 AS ME_1,t0.AT_1 AS AT_1,rtrim(t0.ME_1) AS Me_2 FROM DS_6 t0);"+"CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);"+"CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);"; 
		} else if (profileActive.equalsIgnoreCase("sqlServer")) {
			comandSqlResult = "SELECT t1.* INTO DS_r FROM (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,t0.ME_1 AS ME_1,t0.AT_1 AS AT_1,rtrim(t0.ME_1) AS Me_2 FROM DS_6 t0) t1;CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);"; 
		} else if (profileActive.equalsIgnoreCase("mySql")) {
			comandSqlResult = "CREATE TABLE DS_r AS (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,t0.ME_1 AS ME_1,t0.AT_1 AS AT_1,rtrim(t0.ME_1) AS Me_2 FROM DS_6 t0);"+"CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);"+"CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);";
		} else if (profileActive.equalsIgnoreCase("postgreSql")) {
			comandSqlResult = "CREATE TABLE DS_r AS (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,t0.ME_1 AS ME_1,t0.AT_1 AS AT_1,rtrim(t0.ME_1) AS Me_2 FROM DS_6 t0);"+"CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);"+"CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);";
		}
		
		String commandStatements = "DS_r := DS_6[ calc Me_2:=  rtrim(Me_1)];";
		String result = translationUtilTest.translate(commandStatements);
		System.out.println("Print queryWhitespaceRemovalComponent: " +result);
		System.out.println("Print queryAttesaWhitespaceRemovalComponent: " +comandSqlResult);
		assertEquals("Test operator WhitespaceRemovalComponent", comandSqlResult, result);

	}
	
	@Test
	@Transactional
	public void upper() throws Exception {
		dbTableUtilityService.resetSchema("t");
		VtlDataset vtlDatasetFind = datasetRepository.findByNameIgnoreCase("DS_1");
		if (vtlDatasetFind == null) {
			VtlDataset vtlDataset = new VtlDataset();

			vtlDataset.setPersistent(true);
			vtlDataset.setName("DS_41");
			vtlDataset.setIsOnlyAScalar(false);
			vtlDataset.setTransitory(false);
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ID_1", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ID_2", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ME_1", VtlType.STRING, VtlComponentRole.MEASURE));
			datasetRepository.save(vtlDataset);

		}
		
		System.out.println("Il profilo attivo è " + environment.getActiveProfiles()[0]);

		String comandSqlResult = "";
		String profileActive = environment.getActiveProfiles()[0];
		if (profileActive.equalsIgnoreCase("oracleSql")) {
			comandSqlResult = "CREATE TABLE DS_r AS (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,upper(t0.ME_1) AS ME_1 FROM DS_41 t0);"+"CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);"+"CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);"; 
		} else if (profileActive.equalsIgnoreCase("sqlServer")) {
			comandSqlResult = "SELECT t1.* INTO DS_r FROM (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,upper(t0.ME_1) AS ME_1 FROM DS_41 t0) t1;CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);"; 
		} else if (profileActive.equalsIgnoreCase("mySql")) {
			comandSqlResult = "CREATE TABLE DS_r AS (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,upper(t0.ME_1) AS ME_1 FROM DS_41 t0);"+"CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);"+"CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);"; 
		} else if (profileActive.equalsIgnoreCase("postgreSql")) {
			comandSqlResult = "CREATE TABLE DS_r AS (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,upper(t0.ME_1) AS ME_1 FROM DS_41 t0);"+"CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);"+"CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);"; 
		}
		
		String commandStatements = "DS_r := upper(DS_41);";
		String result = translationUtilTest.translate(commandStatements);
		System.out.println("Print queryUpper: " +result);
		System.out.println("Print queryAttesaUpper: " +comandSqlResult);
		assertEquals("Test operator Upper", comandSqlResult, result);

	}
	
	@Test
	@Transactional
	public void upperString() throws Exception {
		
        dbTableUtilityService.resetSchema("t");
		
		System.out.println("Il profilo attivo è " + environment.getActiveProfiles()[0]);

		String comandSqlResult = "";
		String profileActive = environment.getActiveProfiles()[0];
		if (profileActive.equalsIgnoreCase("oracleSql")) {
			comandSqlResult = "CREATE TABLE DS_r AS (SELECT upper('Hello') AS string_var FROM DUAL);";
		} else if (profileActive.equalsIgnoreCase("sqlServer")) {
			comandSqlResult = "SELECT t0.* INTO DS_r FROM (SELECT upper('Hello') AS string_var) t0;";
		} else if (profileActive.equalsIgnoreCase("mySql")) {
			comandSqlResult = "CREATE TABLE DS_r AS (SELECT upper('Hello') AS string_var FROM DUAL);";
		} else if (profileActive.equalsIgnoreCase("postgreSql")) {
			comandSqlResult = "CREATE TABLE DS_r AS (SELECT upper('Hello') AS string_var);";
		}
		
		String commandStatements = "DS_r := upper(\"Hello\");"; 
		String result = translationUtilTest.translate(commandStatements);
		System.out.println("Print queryUpperString: " +result);
		System.out.println("Print queryAttesaUpperString: " +comandSqlResult);
		assertEquals("Test operator UpperString", comandSqlResult, result);
	}

	@Test
	@Transactional
	public void upperComponent() throws Exception {
		dbTableUtilityService.resetSchema("t");
		VtlDataset vtlDatasetFind = datasetRepository.findByNameIgnoreCase("DS_1");
		if (vtlDatasetFind == null) {
			VtlDataset vtlDataset = new VtlDataset();

			vtlDataset.setPersistent(true);
			vtlDataset.setName("DS_41");
			vtlDataset.setIsOnlyAScalar(false);
			vtlDataset.setTransitory(false);
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ID_1", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ID_2", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ME_1", VtlType.STRING, VtlComponentRole.MEASURE));
			datasetRepository.save(vtlDataset);
		}
		
		System.out.println("Il profilo attivo è " + environment.getActiveProfiles()[0]);

		String comandSqlResult = "";
		String profileActive = environment.getActiveProfiles()[0];
		if (profileActive.equalsIgnoreCase("oracleSql")) {
			comandSqlResult = "CREATE TABLE DS_r AS (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,t0.ME_1 AS ME_1,upper(t0.ME_1) AS Me_2 FROM DS_41 t0);"+"CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);"+"CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);"; 
		} else if (profileActive.equalsIgnoreCase("sqlServer")) {
			comandSqlResult = "SELECT t1.* INTO DS_r FROM (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,t0.ME_1 AS ME_1,upper(t0.ME_1) AS Me_2 FROM DS_41 t0) t1;CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);"; 
		} else if (profileActive.equalsIgnoreCase("mySql")) {
			comandSqlResult = "CREATE TABLE DS_r AS (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,t0.ME_1 AS ME_1,upper(t0.ME_1) AS Me_2 FROM DS_41 t0);"+"CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);"+"CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);"; 
		} else if (profileActive.equalsIgnoreCase("postgreSql")) {
			comandSqlResult = "CREATE TABLE DS_r AS (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,t0.ME_1 AS ME_1,upper(t0.ME_1) AS Me_2 FROM DS_41 t0);"+"CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);"+"CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);"; 
		}
		
		String commandStatements = "DS_r := DS_41[ calc Me_2:=  upper(Me_1)];";
		String result = translationUtilTest.translate(commandStatements);
		System.out.println("Print queryUpperComponent: " +result);
		System.out.println("Print queryAttesaUpperComponent: " +comandSqlResult);
		assertEquals("Test operator UpperComponent", comandSqlResult, result);

	}

	@Test
	@Transactional
	public void substr() throws Exception {
		dbTableUtilityService.resetSchema("t");
		VtlDataset vtlDatasetFind = datasetRepository.findByNameIgnoreCase("DS_1");
		if (vtlDatasetFind == null) {
			VtlDataset vtlDataset = new VtlDataset();

			vtlDataset.setPersistent(true);
			vtlDataset.setName("DS_4");
			vtlDataset.setIsOnlyAScalar(false);
			vtlDataset.setTransitory(false);
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ID_1", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ID_2", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ME_1", VtlType.STRING, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ME_2", VtlType.STRING, VtlComponentRole.MEASURE));
			datasetRepository.save(vtlDataset);
		}
		
		System.out.println("Il profilo attivo è " + environment.getActiveProfiles()[0]);

		String comandSqlResult = "";
		String profileActive = environment.getActiveProfiles()[0];
		if (profileActive.equalsIgnoreCase("oracleSql")) {
			comandSqlResult = "CREATE TABLE DS_r AS (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,substr(t0.ME_1,7) AS ME_1,substr(t0.ME_2,7) AS ME_2 FROM DS_4 t0);"+"CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);"+"CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);"; 
		} else if (profileActive.equalsIgnoreCase("sqlServer")) {
			comandSqlResult = "SELECT t1.* INTO DS_r FROM (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,substring(t0.ME_1,7,len(t0.ME_1)) AS ME_1,substring(t0.ME_2,7,len(t0.ME_2)) AS ME_2 FROM DS_4 t0) t1;CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);"; 
		} else if (profileActive.equalsIgnoreCase("mySql")) {
			comandSqlResult = "CREATE TABLE DS_r AS (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,substr(t0.ME_1,7) AS ME_1,substr(t0.ME_2,7) AS ME_2 FROM DS_4 t0);"+"CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);"+"CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);";  
		} else if (profileActive.equalsIgnoreCase("postgreSql")) {
			comandSqlResult = "CREATE TABLE DS_r AS (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,substr(t0.ME_1,7) AS ME_1,substr(t0.ME_2,7) AS ME_2 FROM DS_4 t0);"+"CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);"+"CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);"; 
		}
		
		String commandStatements = "DS_r := substr ( DS_4  , 7 );";
		String result = translationUtilTest.translate(commandStatements);
		System.out.println("Print querySubstr: " +result);
		System.out.println("Print queryAttesaSubstr: " +comandSqlResult);
		assertEquals("Test operator Substr", comandSqlResult, result);

	}
	
	@Test
	@Transactional
	public void substrInt() throws Exception {
        dbTableUtilityService.resetSchema("t");
		
		System.out.println("Il profilo attivo è " + environment.getActiveProfiles()[0]);

		String comandSqlResult = "";
		String profileActive = environment.getActiveProfiles()[0];
		if (profileActive.equalsIgnoreCase("oracleSql")) {
			comandSqlResult = "CREATE TABLE DS_r AS (SELECT substr(1,2,3) AS string_var FROM DUAL);"; 
		} else if (profileActive.equalsIgnoreCase("sqlServer")) {
			comandSqlResult = "SELECT t0.* INTO DS_r FROM (SELECT substring(1,2,3) AS string_var) t0;";
		} else if (profileActive.equalsIgnoreCase("mySql")) {
			comandSqlResult = "CREATE TABLE DS_r AS (SELECT substr(1,2,3) AS string_var FROM DUAL);"; 
		} else if (profileActive.equalsIgnoreCase("postgreSql")) {
			comandSqlResult = "CREATE TABLE DS_r AS (SELECT substr(1,2,3) AS string_var);"; 
		}
		String commandStatements = "DS_r := substr ( 1, 2 , 3 );";
		String result = translationUtilTest.translate(commandStatements);
		System.out.println("Print querySubstrInt: " +result);
		System.out.println("Print queryAttesaSubstrInt: " +comandSqlResult);
		assertEquals("Test operator SubstrInt", comandSqlResult, result);
	}

	@Test
	@Transactional
	public void substrComponent() throws Exception {
		dbTableUtilityService.resetSchema("t");
		VtlDataset vtlDatasetFind = datasetRepository.findByNameIgnoreCase("DS_1");
		if (vtlDatasetFind == null) {
			VtlDataset vtlDataset = new VtlDataset();

			vtlDataset.setPersistent(true);
			vtlDataset.setName("DS_4");
			vtlDataset.setIsOnlyAScalar(false);
			vtlDataset.setTransitory(false);
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ID_1", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ID_2", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ME_1", VtlType.STRING, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ME_2", VtlType.STRING, VtlComponentRole.MEASURE));
			datasetRepository.save(vtlDataset);
		}
		
		System.out.println("Il profilo attivo è " + environment.getActiveProfiles()[0]);

		String comandSqlResult = "";
		String profileActive = environment.getActiveProfiles()[0];
		if (profileActive.equalsIgnoreCase("oracleSql")) {
			comandSqlResult = "CREATE TABLE DS_r AS (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,t0.ME_1 AS ME_1,substr(t0.ME_2,1,5) AS Me_2 FROM DS_4 t0);"+"CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);"+"CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);";  
		} else if (profileActive.equalsIgnoreCase("sqlServer")) {
			comandSqlResult = "SELECT t1.* INTO DS_r FROM (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,t0.ME_1 AS ME_1,substring(t0.ME_2,1,5) AS Me_2 FROM DS_4 t0) t1;CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);"; 
		} else if (profileActive.equalsIgnoreCase("mySql")) {
			comandSqlResult = "CREATE TABLE DS_r AS (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,t0.ME_1 AS ME_1,substr(t0.ME_2,1,5) AS Me_2 FROM DS_4 t0);"+"CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);"+"CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);";  
		} else if (profileActive.equalsIgnoreCase("postgreSql")) {
			comandSqlResult = "CREATE TABLE DS_r AS (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,t0.ME_1 AS ME_1,substr(t0.ME_2,1,5) AS Me_2 FROM DS_4 t0);"+"CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);"+"CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);";  
		}
		
		String commandStatements = "DS_r := DS_4  [ calc Me_2:=  substr ( Me_2 , 1 , 5 ) ];";
		String result = translationUtilTest.translate(commandStatements);
		System.out.println("Print querySubstrComponent: " +result);
		System.out.println("Print queryAttesaSubstrComponent: " +comandSqlResult);
		assertEquals("Test operator SubstrComponent", comandSqlResult, result);

	}

	@Test
	@Transactional
	public void replace() throws Exception {
		dbTableUtilityService.resetSchema("t");
		VtlDataset vtlDatasetFind = datasetRepository.findByNameIgnoreCase("DS_1");
		if (vtlDatasetFind == null) {
			VtlDataset vtlDataset = new VtlDataset();

			vtlDataset.setPersistent(true);
			vtlDataset.setName("DS_4");
			vtlDataset.setIsOnlyAScalar(false);
			vtlDataset.setTransitory(false);
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ID_1", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ID_2", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ME_1", VtlType.STRING, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ME_2", VtlType.STRING, VtlComponentRole.MEASURE));
			datasetRepository.save(vtlDataset);

		}
		
		System.out.println("Il profilo attivo è " + environment.getActiveProfiles()[0]);

		String comandSqlResult = "";
		String profileActive = environment.getActiveProfiles()[0];
		if (profileActive.equalsIgnoreCase("oracleSql")) {
			comandSqlResult = "CREATE TABLE DS_r AS (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,replace(t0.ME_1,'ello','i') AS ME_1,replace(t0.ME_2,'ello','i') AS ME_2 FROM DS_4 t0);"+"CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);"+"CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);";  
		} else if (profileActive.equalsIgnoreCase("sqlServer")) {
			comandSqlResult = "SELECT t1.* INTO DS_r FROM (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,replace(t0.ME_1,'ello','i') AS ME_1,replace(t0.ME_2,'ello','i') AS ME_2 FROM DS_4 t0) t1;CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);"; 
		} else if (profileActive.equalsIgnoreCase("mySql")) {
			comandSqlResult = "CREATE TABLE DS_r AS (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,replace(t0.ME_1,'ello','i') AS ME_1,replace(t0.ME_2,'ello','i') AS ME_2 FROM DS_4 t0);"+"CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);"+"CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);"; 
		} else if (profileActive.equalsIgnoreCase("postgreSql")) {
			comandSqlResult = "CREATE TABLE DS_r AS (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,replace(t0.ME_1,'ello','i') AS ME_1,replace(t0.ME_2,'ello','i') AS ME_2 FROM DS_4 t0);"+"CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);"+"CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);"; 
		}
		
		String commandStatements = "DS_r := replace (DS_4,\"ello\",\"i\");";
		String result = translationUtilTest.translate(commandStatements);
		System.out.println("Print queryReplace: " +result);
		System.out.println("Print queryAttesaReplace: " +comandSqlResult);
		assertEquals("Test operator Replace", comandSqlResult, result);

	}

	@Test
	@Transactional
	public void replaceString() throws Exception {
       dbTableUtilityService.resetSchema("t");
		
		System.out.println("Il profilo attivo è " + environment.getActiveProfiles()[0]);

		String comandSqlResult = "";
		String profileActive = environment.getActiveProfiles()[0];
		if (profileActive.equalsIgnoreCase("oracleSql")) {
			comandSqlResult = "CREATE TABLE DS_r AS (SELECT replace('Hello world','Hello','Hi') AS string_var FROM DUAL);";
		} else if (profileActive.equalsIgnoreCase("sqlServer")) {
			comandSqlResult = "SELECT t0.* INTO DS_r FROM (SELECT replace('Hello world','Hello','Hi') AS string_var) t0;";
		} else if (profileActive.equalsIgnoreCase("mySql")) {
			comandSqlResult = "CREATE TABLE DS_r AS (SELECT replace('Hello world','Hello','Hi') AS string_var FROM DUAL);";
		} else if (profileActive.equalsIgnoreCase("postgreSql")) {
			comandSqlResult = "CREATE TABLE DS_r AS (SELECT replace('Hello world','Hello','Hi') AS string_var);";
		}
		String commandStatements = "DS_r := replace(\"Hello world\", \"Hello\", \"Hi\");";
		String result = translationUtilTest.translate(commandStatements);
		System.out.println("Print queryReplaceString: " +result);
		System.out.println("Print queryAttesaReplaceString: " +comandSqlResult);
		assertEquals("Test operator ReplaceString", comandSqlResult, result);
	}

	@Test
	@Transactional
	public void replaceComponent() throws Exception {
		dbTableUtilityService.resetSchema("t");
		VtlDataset vtlDatasetFind = datasetRepository.findByNameIgnoreCase("DS_1");
		if (vtlDatasetFind == null) {
			VtlDataset vtlDataset = new VtlDataset();

			vtlDataset.setPersistent(true);
			vtlDataset.setName("DS_4");
			vtlDataset.setIsOnlyAScalar(false);
			vtlDataset.setTransitory(false);
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ID_1", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ID_2", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ME_1", VtlType.STRING, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ME_2", VtlType.STRING, VtlComponentRole.MEASURE));
			datasetRepository.save(vtlDataset);

		}
		
		System.out.println("Il profilo attivo è " + environment.getActiveProfiles()[0]);

		String comandSqlResult = "";
		String profileActive = environment.getActiveProfiles()[0];
		if (profileActive.equalsIgnoreCase("oracleSql")) {
			comandSqlResult = "CREATE TABLE DS_r AS (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,t0.ME_1 AS ME_1,t0.ME_2 AS ME_2,replace(t0.ME_1,'ello','i') AS ME_3 FROM DS_4 t0);"+"CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);"+"CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);"; 
		} else if (profileActive.equalsIgnoreCase("sqlServer")) {
			comandSqlResult = "SELECT t1.* INTO DS_r FROM (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,t0.ME_1 AS ME_1,t0.ME_2 AS ME_2,replace(t0.ME_1,'ello','i') AS ME_3 FROM DS_4 t0) t1;CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);"; 
		} else if (profileActive.equalsIgnoreCase("mySql")) {
			comandSqlResult = "CREATE TABLE DS_r AS (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,t0.ME_1 AS ME_1,t0.ME_2 AS ME_2,replace(t0.ME_1,'ello','i') AS ME_3 FROM DS_4 t0);"+"CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);"+"CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);"; 
		} else if (profileActive.equalsIgnoreCase("postgreSql")) {
			comandSqlResult = "CREATE TABLE DS_r AS (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,t0.ME_1 AS ME_1,t0.ME_2 AS ME_2,replace(t0.ME_1,'ello','i') AS ME_3 FROM DS_4 t0);"+"CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);"+"CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);"; 
		}
		
		String commandStatements = "DS_r := DS_4[ calc ME_3:=  replace (Me_1,\"ello\",\"i\")];";
		String result = translationUtilTest.translate(commandStatements);
		System.out.println("Print queryReplaceComponent: " +result);
		System.out.println("Print queryAttesaReplaceComponent: " +comandSqlResult);
		assertEquals("Test operator ReplaceComponent", comandSqlResult, result);
	}

	@Test
	@Transactional
	public void instr() throws Exception {
		dbTableUtilityService.resetSchema("t");
		VtlDataset vtlDatasetFind = datasetRepository.findByNameIgnoreCase("DS_1");
		if (vtlDatasetFind == null) {
			VtlDataset vtlDataset = new VtlDataset();

			vtlDataset.setPersistent(true);
			vtlDataset.setName("DS_5");
			vtlDataset.setIsOnlyAScalar(false);
			vtlDataset.setTransitory(false);
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ID_2", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ME_1", VtlType.STRING, VtlComponentRole.MEASURE));
			datasetRepository.save(vtlDataset);

		}
		
		System.out.println("Il profilo attivo è " + environment.getActiveProfiles()[0]);

		String comandSqlResult = "";
		String profileActive = environment.getActiveProfiles()[0];
		if (profileActive.equalsIgnoreCase("oracleSql")) {
			comandSqlResult = "CREATE TABLE DS_r AS (SELECT t0.ID_2 AS ID_2,instr(t0.ME_1,'hello') AS int_var FROM DS_5 t0);"+"CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);"; 
		} else if (profileActive.equalsIgnoreCase("sqlServer")) {
			comandSqlResult = "SELECT t1.* INTO DS_r FROM (SELECT t0.ID_2 AS ID_2,charindex('hello',t0.ME_1) AS int_var FROM DS_5 t0) t1;CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);"; 
		} else if (profileActive.equalsIgnoreCase("mySql")) {
			comandSqlResult = "CREATE TABLE DS_r AS (SELECT t0.ID_2 AS ID_2,instr(t0.ME_1,'hello') AS int_var FROM DS_5 t0);"+"CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);"; 
		} else if (profileActive.equalsIgnoreCase("postgreSql")) {
			comandSqlResult = "CREATE TABLE DS_r AS (SELECT t0.ID_2 AS ID_2,strpos(t0.ME_1,'hello') AS int_var FROM DS_5 t0);"+"CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);"; 
		}
		
		String commandStatements = "DS_r := instr(DS_5,\"hello\");";
		String result = translationUtilTest.translate(commandStatements);
		System.out.println("Print queryInstr: " +result);
		System.out.println("Print queryAttesaInstr: " +comandSqlResult);
		assertEquals("Test operator Instr", comandSqlResult, result);

	}

	@Test
	@Transactional
	public void instrString() throws Exception {
		dbTableUtilityService.resetSchema("t");
		System.out.println("Il profilo attivo è " + environment.getActiveProfiles()[0]);

		String comandSqlResult = "";
		String profileActive = environment.getActiveProfiles()[0];
		if (profileActive.equalsIgnoreCase("oracleSql")) {
			comandSqlResult =  "CREATE TABLE DS_r AS (SELECT instr('abcde','c') AS int_var FROM DUAL);"; 
		} else if (profileActive.equalsIgnoreCase("sqlServer")) {
			comandSqlResult = "SELECT t0.* INTO DS_r FROM (SELECT charindex('c','abcde') AS int_var) t0;";
		} else if (profileActive.equalsIgnoreCase("mySql")) {
			comandSqlResult =  "CREATE TABLE DS_r AS (SELECT instr('abcde','c') AS int_var FROM DUAL);"; 
		} else if (profileActive.equalsIgnoreCase("postgreSql")) {
			comandSqlResult =  "CREATE TABLE DS_r AS (SELECT strpos('abcde','c') AS int_var);"; 
		}
		
		String commandStatements = "DS_r := instr (\"abcde\", \"c\" );";
		String result = translationUtilTest.translate(commandStatements);
		System.out.println("Print queryInstrString: " +result);
		System.out.println("Print queryAttesaInstrString: " +comandSqlResult);
		assertEquals("Test operator InstrString", comandSqlResult, result);
	}

	@Test
	@Transactional
	public void instrComponent() throws Exception {
		dbTableUtilityService.resetSchema("t");
		VtlDataset vtlDatasetFind = datasetRepository.findByNameIgnoreCase("DS_1");
		if (vtlDatasetFind == null) {
			VtlDataset vtlDataset = new VtlDataset();

			vtlDataset.setPersistent(true);
			vtlDataset.setName("DS_4");
			vtlDataset.setIsOnlyAScalar(false);
			vtlDataset.setTransitory(false);
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ID_1", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ID_2", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ME_1", VtlType.STRING, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ME_2", VtlType.STRING, VtlComponentRole.MEASURE));
			datasetRepository.save(vtlDataset);

		}
		
		System.out.println("Il profilo attivo è " + environment.getActiveProfiles()[0]);

		String comandSqlResult = "";
		String profileActive = environment.getActiveProfiles()[0];
		if (profileActive.equalsIgnoreCase("oracleSql")) {
			comandSqlResult = "CREATE TABLE DS_r AS (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,t0.ME_1 AS ME_1,t0.ME_2 AS ME_2,instr(t0.ME_1,'hello') AS ME_3 FROM DS_4 t0);"+"CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);"+"CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);"; 
		} else if (profileActive.equalsIgnoreCase("sqlServer")) {
			comandSqlResult = "SELECT t1.* INTO DS_r FROM (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,t0.ME_1 AS ME_1,t0.ME_2 AS ME_2,charindex('hello',t0.ME_1) AS ME_3 FROM DS_4 t0) t1;CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);"; 
		} else if (profileActive.equalsIgnoreCase("mySql")) {
			comandSqlResult = "CREATE TABLE DS_r AS (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,t0.ME_1 AS ME_1,t0.ME_2 AS ME_2,instr(t0.ME_1,'hello') AS ME_3 FROM DS_4 t0);"+"CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);"+"CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);"; 
		} else if (profileActive.equalsIgnoreCase("postgreSql")) {
			comandSqlResult = "CREATE TABLE DS_r AS (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,t0.ME_1 AS ME_1,t0.ME_2 AS ME_2,strpos(t0.ME_1,'hello') AS ME_3 FROM DS_4 t0);"+"CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);"+"CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);"; 
		}
		
		String commandStatements = "DS_r := DS_4[calc ME_3:=instr(Me_1,\"hello\")];";
		String result = translationUtilTest.translate(commandStatements);
		System.out.println("Print queryInstrComponent: " +result);
		System.out.println("Print queryAttesaInstrComponent: " +comandSqlResult);
		assertEquals("Test operator ReplaceInstrComponent", comandSqlResult, result);

	}

	@Test
	@Transactional
	public void instrComponentMe() throws Exception {
		dbTableUtilityService.resetSchema("t");
		VtlDataset vtlDatasetFind = datasetRepository.findByNameIgnoreCase("DS_1");
		if (vtlDatasetFind == null) {
			VtlDataset vtlDataset = new VtlDataset();

			vtlDataset.setPersistent(true);
			vtlDataset.setName("DS_4");
			vtlDataset.setIsOnlyAScalar(false);
			vtlDataset.setTransitory(false);
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ID_1", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ID_2", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ME_1", VtlType.STRING, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ME_2", VtlType.STRING, VtlComponentRole.MEASURE));
			datasetRepository.save(vtlDataset);
		}
		
		System.out.println("Il profilo attivo è " + environment.getActiveProfiles()[0]);

		String comandSqlResult = "";
		String profileActive = environment.getActiveProfiles()[0];
		if (profileActive.equalsIgnoreCase("oracleSql")) {
			comandSqlResult = "CREATE TABLE DS_r AS (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,t0.ME_1 AS ME_1,t0.ME_2 AS ME_2,instr(t0.ME_1,'o') AS Me_10,instr(t0.ME_2,'o') AS Me_20 FROM DS_4 t0);"+"CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);"+"CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);";
		} else if (profileActive.equalsIgnoreCase("sqlServer")) {
			comandSqlResult = "SELECT t1.* INTO DS_r FROM (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,t0.ME_1 AS ME_1,t0.ME_2 AS ME_2,charindex('o',t0.ME_1) AS Me_10,charindex('o',t0.ME_2) AS Me_20 FROM DS_4 t0) t1;CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);"; 
		} else if (profileActive.equalsIgnoreCase("mySql")) {
			comandSqlResult = "CREATE TABLE DS_r AS (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,t0.ME_1 AS ME_1,t0.ME_2 AS ME_2,instr(t0.ME_1,'o') AS Me_10,instr(t0.ME_2,'o') AS Me_20 FROM DS_4 t0);"+"CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);"+"CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);"; 
		} else if (profileActive.equalsIgnoreCase("postgreSql")) {
			comandSqlResult = "CREATE TABLE DS_r AS (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,t0.ME_1 AS ME_1,t0.ME_2 AS ME_2,strpos(t0.ME_1,'o') AS Me_10,strpos(t0.ME_2,'o') AS Me_20 FROM DS_4 t0);"+"CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);"+"CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);";
		}
		
		String commandStatements = "DS_r := DS_4 [calc Me_10:= instr(Me_1, \"o\" ), Me_20:=instr(Me_2, \"o\")];";
		String result = translationUtilTest.translate(commandStatements);
		System.out.println("Print queryInstrComponentMe: " +result);
		System.out.println("Print queryAttesaInstrComponentMe: " +comandSqlResult);
		assertEquals("Test operator ReplaceInstrComponentMe", comandSqlResult, result);

	}

	@Test
	@Transactional
	public void length() throws Exception {
		dbTableUtilityService.resetSchema("t");
		VtlDataset vtlDatasetFind = datasetRepository.findByNameIgnoreCase("DS_1");
		if (vtlDatasetFind == null) {
			VtlDataset vtlDataset = new VtlDataset();

			vtlDataset.setPersistent(true);
			vtlDataset.setName("DS_5");
			vtlDataset.setIsOnlyAScalar(false);
			vtlDataset.setTransitory(false);
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ID_2", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ME_1", VtlType.STRING, VtlComponentRole.MEASURE));datasetRepository.save(vtlDataset);
		}
		
		System.out.println("Il profilo attivo è " + environment.getActiveProfiles()[0]);

		String comandSqlResult = "";
		String profileActive = environment.getActiveProfiles()[0];
		if (profileActive.equalsIgnoreCase("oracleSql")) {
			comandSqlResult = "CREATE TABLE DS_r AS (SELECT t0.ID_2 AS ID_2,length(t0.ME_1) AS int_var FROM DS_5 t0);"+"CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);";
		} else if (profileActive.equalsIgnoreCase("sqlServer")) {
			comandSqlResult = "SELECT t1.* INTO DS_r FROM (SELECT t0.ID_2 AS ID_2,len(t0.ME_1) AS int_var FROM DS_5 t0) t1;CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);"; 
		} else if (profileActive.equalsIgnoreCase("mySql")) {
			comandSqlResult = "CREATE TABLE DS_r AS (SELECT t0.ID_2 AS ID_2,length(t0.ME_1) AS int_var FROM DS_5 t0);"+"CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);";
		} else if (profileActive.equalsIgnoreCase("postgreSql")) {
			comandSqlResult = "CREATE TABLE DS_r AS (SELECT t0.ID_2 AS ID_2,length(t0.ME_1) AS int_var FROM DS_5 t0);"+"CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);";
		}
		
		String commandStatements = "DS_r := length(DS_5);";
		String result = translationUtilTest.translate(commandStatements);
		System.out.println("Print queryLength: " +result);
		System.out.println("Print queryAttesaLength: " +comandSqlResult);
		assertEquals("Test operator Length", comandSqlResult, result);

	}

	@Test
	@Transactional
	public void lengthString() throws Exception {
        
        dbTableUtilityService.resetSchema("t");
		System.out.println("Il profilo attivo è " + environment.getActiveProfiles()[0]);

		String comandSqlResult = "";
		String profileActive = environment.getActiveProfiles()[0];
		if (profileActive.equalsIgnoreCase("oracleSql")) {
			comandSqlResult =  "CREATE TABLE DS_r AS (SELECT length('Hello, World!') AS int_var FROM DUAL);";
		} else if (profileActive.equalsIgnoreCase("sqlServer")) {
			comandSqlResult = "SELECT t0.* INTO DS_r FROM (SELECT len('Hello, World!') AS int_var) t0;";
		} else if (profileActive.equalsIgnoreCase("mySql")) {
			comandSqlResult =  "CREATE TABLE DS_r AS (SELECT length('Hello, World!') AS int_var FROM DUAL);"; 
		} else if (profileActive.equalsIgnoreCase("postgreSql")) {
			comandSqlResult =  "CREATE TABLE DS_r AS (SELECT length('Hello, World!') AS int_var);";
		}
		String commandStatements = "DS_r := length(\"Hello, World!\")";
		String result = translationUtilTest.translate(commandStatements);
		System.out.println("Print queryLengthString: " +result);
		System.out.println("Print queryAttesaLengthString: " +comandSqlResult);
		assertEquals("Test operator LengthString", comandSqlResult, result);
	}

	@Test
	@Transactional
	public void lengthComponent() throws Exception {
		dbTableUtilityService.resetSchema("t");
		VtlDataset vtlDatasetFind = datasetRepository.findByNameIgnoreCase("DS_1");
		if (vtlDatasetFind == null) {
			VtlDataset vtlDataset = new VtlDataset();

			vtlDataset.setPersistent(true);
			vtlDataset.setName("DS_4");
			vtlDataset.setIsOnlyAScalar(false);
			vtlDataset.setTransitory(false);
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ID_1", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ID_2", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ME_1", VtlType.STRING, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ME_2", VtlType.STRING, VtlComponentRole.MEASURE));
			datasetRepository.save(vtlDataset);
		}
		
		System.out.println("Il profilo attivo è " + environment.getActiveProfiles()[0]);

		String comandSqlResult = "";
		String profileActive = environment.getActiveProfiles()[0];
		if (profileActive.equalsIgnoreCase("oracleSql")) {
			comandSqlResult = "CREATE TABLE DS_r AS (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,t0.ME_1 AS ME_1,t0.ME_2 AS ME_2,length(t0.ME_1) AS ME_3 FROM DS_4 t0);"+"CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);"+"CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);";
		} else if (profileActive.equalsIgnoreCase("sqlServer")) {
			comandSqlResult = "SELECT t1.* INTO DS_r FROM (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,t0.ME_1 AS ME_1,t0.ME_2 AS ME_2,len(t0.ME_1) AS ME_3 FROM DS_4 t0) t1;CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);"; 
		} else if (profileActive.equalsIgnoreCase("mySql")) {
			comandSqlResult = "CREATE TABLE DS_r AS (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,t0.ME_1 AS ME_1,t0.ME_2 AS ME_2,length(t0.ME_1) AS ME_3 FROM DS_4 t0);"+"CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);"+"CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);";
		} else if (profileActive.equalsIgnoreCase("postgreSql")) {
			comandSqlResult = "CREATE TABLE DS_r AS (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,t0.ME_1 AS ME_1,t0.ME_2 AS ME_2,length(t0.ME_1) AS ME_3 FROM DS_4 t0);"+"CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);"+"CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);";
		}
		
		String commandStatements = "DS_r := DS_4[calc ME_3:=length(Me_1)];";
		String result = translationUtilTest.translate(commandStatements);
		System.out.println("Print queryLengthComponent: " +result);
		System.out.println("Print queryAttesaLengthComponent: " +comandSqlResult);
		assertEquals("Test operator LengthStringComponent", comandSqlResult, result);

	}

	@Test
	@Transactional
	public void lengthComponentDoppio() throws Exception {
		dbTableUtilityService.resetSchema("t");
		VtlDataset vtlDatasetFind = datasetRepository.findByNameIgnoreCase("DS_1");
		if (vtlDatasetFind == null) {
			VtlDataset vtlDataset = new VtlDataset();

			vtlDataset.setPersistent(true);
			vtlDataset.setName("DS_4");
			vtlDataset.setIsOnlyAScalar(false);
			vtlDataset.setTransitory(false);
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ID_1", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ID_2", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ME_1", VtlType.STRING, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ME_2", VtlType.STRING, VtlComponentRole.MEASURE));
			datasetRepository.save(vtlDataset);
		}
		
		System.out.println("Il profilo attivo è " + environment.getActiveProfiles()[0]);

		String comandSqlResult = "";
		String profileActive = environment.getActiveProfiles()[0];
		if (profileActive.equalsIgnoreCase("oracleSql")) {
			comandSqlResult = "CREATE TABLE DS_r AS (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,t0.ME_1 AS ME_1,t0.ME_2 AS ME_2,length(t0.ME_1) AS Me_10,length(t0.ME_2) AS Me_20 FROM DS_4 t0);"+"CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);"+"CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);";
		} else if (profileActive.equalsIgnoreCase("sqlServer")) {
			comandSqlResult = "SELECT t1.* INTO DS_r FROM (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,t0.ME_1 AS ME_1,t0.ME_2 AS ME_2,len(t0.ME_1) AS Me_10,len(t0.ME_2) AS Me_20 FROM DS_4 t0) t1;CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);"; 
		} else if (profileActive.equalsIgnoreCase("mySql")) {
			comandSqlResult = "CREATE TABLE DS_r AS (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,t0.ME_1 AS ME_1,t0.ME_2 AS ME_2,length(t0.ME_1) AS Me_10,length(t0.ME_2) AS Me_20 FROM DS_4 t0);"+"CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);"+"CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);";
		} else if (profileActive.equalsIgnoreCase("postgreSql")) {
			comandSqlResult = "CREATE TABLE DS_r AS (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,t0.ME_1 AS ME_1,t0.ME_2 AS ME_2,length(t0.ME_1) AS Me_10,length(t0.ME_2) AS Me_20 FROM DS_4 t0);"+"CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);"+"CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);";
		}
		
		String commandStatements = "DS_r := DS_4 [calc Me_10:= length(Me_1), Me_20:=length(Me_2)];";
		String result = translationUtilTest.translate(commandStatements);
		System.out.println("Print queryLengthComponentDoppio: " +result);
		System.out.println("Print queryAttesaLengthComponentDoppio: " +comandSqlResult);
		assertEquals("Test operator LengthStringComponentDoppio", comandSqlResult, result);

	}

	
}
