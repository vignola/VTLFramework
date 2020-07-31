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
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
public class VtlNumericOperatorsTranslationTest {

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
	public void plus() throws Exception {
		dbTableUtilityService.resetSchema("t");
		VtlDataset vtlDatasetFind = datasetRepository.findByNameIgnoreCase("DS_1");
		if (vtlDatasetFind == null) {
			VtlDataset vtlDataset = new VtlDataset();

			vtlDataset.setPersistent(true);
			vtlDataset.setName("DS_7");
			vtlDataset.setIsOnlyAScalar(false);
			vtlDataset.setTransitory(false);
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ID_1", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ID_2", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ME_1", VtlType.NUMBER, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ME_2", VtlType.INTEGER, VtlComponentRole.MEASURE));
			datasetRepository.save(vtlDataset);
		}
		
		System.out.println("Il profilo attivo è " + environment.getActiveProfiles()[0]);

		String comandSqlResult = "";
		String profileActive = environment.getActiveProfiles()[0];
		if (profileActive.equalsIgnoreCase("oracleSql")) {
			comandSqlResult = "CREATE TABLE DS_r AS (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,+(t0.ME_1) AS ME_1,+(t0.ME_2) AS ME_2 FROM DS_7 t0);"+"CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);"+"CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);";
		} else if (profileActive.equalsIgnoreCase("sqlServer")) {
			comandSqlResult = "SELECT t1.* INTO DS_r FROM (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,+(t0.ME_1) AS ME_1,+(t0.ME_2) AS ME_2 FROM DS_7 t0) t1;"+"CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);"+"CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);";
		} else if (profileActive.equalsIgnoreCase("mySql")) {
			comandSqlResult = "CREATE TABLE DS_r AS (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,+(t0.ME_1) AS ME_1,+(t0.ME_2) AS ME_2 FROM DS_7 t0);"+"CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);"+"CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);";
		} else if (profileActive.equalsIgnoreCase("postgreSql")) {
			comandSqlResult = "CREATE TABLE DS_r AS (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,+(t0.ME_1) AS ME_1,+(t0.ME_2) AS ME_2 FROM DS_7 t0);"+"CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);"+"CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);";
		}
		
		String commandStatements = "DS_r := + DS_7;";
		String result = translationUtilTest.translate(commandStatements);
		System.out.println("Print queryPlus: " +result);
		System.out.println("Print queryAttesaPlus: " +comandSqlResult);
		assertEquals("Test operator Plus", comandSqlResult, result);

	}

	@Test
	@Transactional
	public void plusInt() throws Exception {
		dbTableUtilityService.resetSchema("t");
		
		System.out.println("Il profilo attivo è " + environment.getActiveProfiles()[0]);

		String comandSqlResult = "";
		String profileActive = environment.getActiveProfiles()[0];
		if (profileActive.equalsIgnoreCase("oracleSql")) {
			comandSqlResult = "CREATE TABLE DS_r AS (SELECT (3)+(2) AS int_var FROM DUAL);";
		} else if (profileActive.equalsIgnoreCase("sqlServer")) {
			comandSqlResult = "SELECT t0.* INTO DS_r FROM (SELECT (3)+(2) AS int_var) t0;";
		} else if (profileActive.equalsIgnoreCase("mySql")) {
			comandSqlResult = "CREATE TABLE DS_r AS (SELECT (3)+(2) AS int_var FROM DUAL);";
		} else if (profileActive.equalsIgnoreCase("postgreSql")) {
			comandSqlResult = "CREATE TABLE DS_r AS (SELECT (3)+(2) AS int_var);";
		}
		
		String commandStatements = "DS_r := 3 + 2;";
		String result = translationUtilTest.translate(commandStatements);
		System.out.println("Print queryPlusInt: " +result);
		System.out.println("Print queryAttesaPlusInt: " +comandSqlResult);
		assertEquals("Test operator PlusInt", comandSqlResult, result);
	}

	@Test
	@Transactional
	public void plusComponent() throws Exception {
		dbTableUtilityService.resetSchema("t");
		VtlDataset vtlDatasetFind = datasetRepository.findByNameIgnoreCase("DS_1");
		if (vtlDatasetFind == null) {
			VtlDataset vtlDataset = new VtlDataset();

			vtlDataset.setPersistent(true);
			vtlDataset.setName("DS_7");
			vtlDataset.setIsOnlyAScalar(false);
			vtlDataset.setTransitory(false);
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ID_1", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ID_2", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ME_1", VtlType.NUMBER, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ME_2", VtlType.INTEGER, VtlComponentRole.MEASURE));
			datasetRepository.save(vtlDataset);
		}
		
		System.out.println("Il profilo attivo è " + environment.getActiveProfiles()[0]);

		String comandSqlResult = "";
		String profileActive = environment.getActiveProfiles()[0];
		if (profileActive.equalsIgnoreCase("oracleSql")) {
			comandSqlResult = "CREATE TABLE DS_r AS (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,t0.ME_1 AS ME_1,t0.ME_2 AS ME_2,+(t0.ME_1) AS Me_3 FROM DS_7 t0);"+"CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);"+"CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);";
		} else if (profileActive.equalsIgnoreCase("sqlServer")) {
			comandSqlResult = "SELECT t1.* INTO DS_r FROM (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,t0.ME_1 AS ME_1,t0.ME_2 AS ME_2,+(t0.ME_1) AS Me_3 FROM DS_7 t0) t1;"+"CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);"+"CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);";
		} else if (profileActive.equalsIgnoreCase("mySql")) {
			comandSqlResult = "CREATE TABLE DS_r AS (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,t0.ME_1 AS ME_1,t0.ME_2 AS ME_2,+(t0.ME_1) AS Me_3 FROM DS_7 t0);"+"CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);"+"CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);";
		} else if (profileActive.equalsIgnoreCase("postgreSql")) {
			comandSqlResult = "CREATE TABLE DS_r AS (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,t0.ME_1 AS ME_1,t0.ME_2 AS ME_2,+(t0.ME_1) AS Me_3 FROM DS_7 t0);"+"CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);"+"CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);";
		}
		
		String commandStatements = "DS_r := DS_7 [calc  Me_3 :=  + Me_1  ];";
		String result = translationUtilTest.translate(commandStatements);
		System.out.println("Print queryPlusComponent: " +result);
		System.out.println("Print queryAttesaPlusComponent: " +comandSqlResult);
		assertEquals("Test operator PlusComponent", comandSqlResult, result);

	}

	@Test
	@Transactional
	public void minus() throws Exception {
		dbTableUtilityService.resetSchema("t");
		VtlDataset vtlDatasetFind = datasetRepository.findByNameIgnoreCase("DS_1");
		if (vtlDatasetFind == null) {
			VtlDataset vtlDataset = new VtlDataset();

			vtlDataset.setPersistent(true);
			vtlDataset.setName("DS_8");
			vtlDataset.setIsOnlyAScalar(false);
			vtlDataset.setTransitory(false);
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ID_1", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ID_2", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ME_1", VtlType.INTEGER, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ME_2", VtlType.NUMBER, VtlComponentRole.MEASURE));
			datasetRepository.save(vtlDataset);

		}
		
		System.out.println("Il profilo attivo è " + environment.getActiveProfiles()[0]);

		String comandSqlResult = "";
		String profileActive = environment.getActiveProfiles()[0];
		if (profileActive.equalsIgnoreCase("oracleSql")) {
			comandSqlResult = "CREATE TABLE DS_r AS (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,-(t0.ME_1) AS ME_1,-(t0.ME_2) AS ME_2 FROM DS_8 t0);"+"CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);"+"CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);";
		} else if (profileActive.equalsIgnoreCase("sqlServer")) {
			comandSqlResult = "SELECT t1.* INTO DS_r FROM (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,-(t0.ME_1) AS ME_1,-(t0.ME_2) AS ME_2 FROM DS_8 t0) t1;"+"CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);"+"CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);";
		} else if (profileActive.equalsIgnoreCase("mySql")) {
			comandSqlResult = "CREATE TABLE DS_r AS (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,-(t0.ME_1) AS ME_1,-(t0.ME_2) AS ME_2 FROM DS_8 t0);"+"CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);"+"CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);";
		} else if (profileActive.equalsIgnoreCase("postgreSql")) {
			comandSqlResult = "CREATE TABLE DS_r AS (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,-(t0.ME_1) AS ME_1,-(t0.ME_2) AS ME_2 FROM DS_8 t0);"+"CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);"+"CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);";
		}
		
		String commandStatements = "DS_r := - DS_8;";
		String result = translationUtilTest.translate(commandStatements);
		System.out.println("Print queryMinus: " +result);
		System.out.println("Print queryAttesaMinus: " +comandSqlResult);
		assertEquals("Test operator Minus", comandSqlResult, result);

	}

	@Test
	@Transactional
	public void minusInt() throws Exception {
		dbTableUtilityService.resetSchema("t");
		
		System.out.println("Il profilo attivo è " + environment.getActiveProfiles()[0]);

		String comandSqlResult = "";
		String profileActive = environment.getActiveProfiles()[0];
		if (profileActive.equalsIgnoreCase("oracleSql")) {
			comandSqlResult = "CREATE TABLE DS_r AS (SELECT (7)-(5) AS int_var FROM DUAL);";
		} else if (profileActive.equalsIgnoreCase("sqlServer")) {
			comandSqlResult = "SELECT t0.* INTO DS_r FROM (SELECT (7)-(5) AS int_var) t0;";
		} else if (profileActive.equalsIgnoreCase("mySql")) {
			comandSqlResult = "CREATE TABLE DS_r AS (SELECT (7)-(5) AS int_var FROM DUAL);";
		} else if (profileActive.equalsIgnoreCase("postgreSql")) {
			comandSqlResult = "CREATE TABLE DS_r AS (SELECT (7)-(5) AS int_var);";
		}
		
		String commandStatements = "DS_r := 7 - 5;";
		String result = translationUtilTest.translate(commandStatements);
		System.out.println("Print queryMinusInt: " +result);
		System.out.println("Print queryAttesaMinusInt: " +comandSqlResult);
		assertEquals("Test operator MinusInt", comandSqlResult, result);
	}

	@Test
	@Transactional
	public void minusComponent() throws Exception {
		dbTableUtilityService.resetSchema("t");
		VtlDataset vtlDatasetFind = datasetRepository.findByNameIgnoreCase("DS_1");
		if (vtlDatasetFind == null) {
			VtlDataset vtlDataset = new VtlDataset();

			vtlDataset.setPersistent(true);
			vtlDataset.setName("DS_8");
			vtlDataset.setIsOnlyAScalar(false);
			vtlDataset.setTransitory(false);
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ID_1", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ID_2", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ME_1", VtlType.INTEGER, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ME_2", VtlType.NUMBER, VtlComponentRole.MEASURE));
			datasetRepository.save(vtlDataset);
		}
		
		System.out.println("Il profilo attivo è " + environment.getActiveProfiles()[0]);

		String comandSqlResult = "";
		String profileActive = environment.getActiveProfiles()[0];
		if (profileActive.equalsIgnoreCase("oracleSql")) {
			comandSqlResult = "CREATE TABLE DS_r AS (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,t0.ME_1 AS ME_1,t0.ME_2 AS ME_2,-(t0.ME_1) AS Me_3 FROM DS_8 t0);"+"CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);"+"CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);";
		} else if (profileActive.equalsIgnoreCase("sqlServer")) {
			comandSqlResult = "SELECT t1.* INTO DS_r FROM (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,t0.ME_1 AS ME_1,t0.ME_2 AS ME_2,-(t0.ME_1) AS Me_3 FROM DS_8 t0) t1;"+"CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);"+"CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);";
		} else if (profileActive.equalsIgnoreCase("mySql")) {
			comandSqlResult = "CREATE TABLE DS_r AS (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,t0.ME_1 AS ME_1,t0.ME_2 AS ME_2,-(t0.ME_1) AS Me_3 FROM DS_8 t0);"+"CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);"+"CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);";
		} else if (profileActive.equalsIgnoreCase("postgreSql")) {
			comandSqlResult = "CREATE TABLE DS_r AS (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,t0.ME_1 AS ME_1,t0.ME_2 AS ME_2,-(t0.ME_1) AS Me_3 FROM DS_8 t0);"+"CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);"+"CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);";
		}
		
		String commandStatements = "DS_r := DS_8 [  calc  Me_3  :=  -  Me_1  ];";
		String result = translationUtilTest.translate(commandStatements);
		System.out.println("Print queryMinusComponent: " +result);
		System.out.println("Print queryAttesaMinusComponent: " +comandSqlResult);
		assertEquals("Test operator MinusComponent", comandSqlResult, result);

	}
	
	@Test
	@Transactional
	public void addition() throws Exception {
		dbTableUtilityService.resetSchema("t");
		VtlDataset vtlDatasetFind = datasetRepository.findByNameIgnoreCase("DS_1");
		if (vtlDatasetFind == null) {
			VtlDataset vtlDataset = new VtlDataset();

			vtlDataset.setPersistent(true);
			vtlDataset.setName("DS_8");
			vtlDataset.setIsOnlyAScalar(false);
			vtlDataset.setTransitory(false);
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ID_1", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ID_2", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ME_1", VtlType.INTEGER, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ME_2", VtlType.NUMBER, VtlComponentRole.MEASURE));
			datasetRepository.save(vtlDataset);
		}
		
		System.out.println("Il profilo attivo è " + environment.getActiveProfiles()[0]);

		String comandSqlResult = "";
		String profileActive = environment.getActiveProfiles()[0];
		if (profileActive.equalsIgnoreCase("oracleSql")) {
			comandSqlResult = "CREATE TABLE DS_r AS (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,(t0.ME_1)+(3) AS ME_1,(t0.ME_2)+(3) AS ME_2 FROM DS_8 t0);"+"CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);"+"CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);";
		} else if (profileActive.equalsIgnoreCase("sqlServer")) {
			comandSqlResult = "SELECT t1.* INTO DS_r FROM (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,(t0.ME_1)+(3) AS ME_1,(t0.ME_2)+(3) AS ME_2 FROM DS_8 t0) t1;"+"CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);"+"CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);";
		} else if (profileActive.equalsIgnoreCase("mySql")) {
			comandSqlResult = "CREATE TABLE DS_r AS (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,(t0.ME_1)+(3) AS ME_1,(t0.ME_2)+(3) AS ME_2 FROM DS_8 t0);"+"CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);"+"CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);";
		} else if (profileActive.equalsIgnoreCase("postgreSql")) {
			comandSqlResult = "CREATE TABLE DS_r AS (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,(t0.ME_1)+(3) AS ME_1,(t0.ME_2)+(3) AS ME_2 FROM DS_8 t0);"+"CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);"+"CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);";
		}
		
		String commandStatements = "DS_r := DS_8 +  3;";
		String result = translationUtilTest.translate(commandStatements);
		System.out.println("Print queryAddition: " +result);
		System.out.println("Print queryAttesaAddition: " +comandSqlResult);
		assertEquals("Test operator Addition", comandSqlResult, result);

	}

	@Test
	@Transactional
	public void additionDs() throws Exception {
		dbTableUtilityService.resetSchema("t");
		VtlDataset vtlDatasetFind = datasetRepository.findByNameIgnoreCase("DS_1");
		if (vtlDatasetFind == null) {
			VtlDataset vtlDataset = new VtlDataset();

			vtlDataset.setPersistent(true);
			vtlDataset.setName("DS_8");
			vtlDataset.setIsOnlyAScalar(false);
			vtlDataset.setTransitory(false);
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ID_1", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ID_2", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ME_1", VtlType.INTEGER, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ME_2", VtlType.NUMBER, VtlComponentRole.MEASURE));
			datasetRepository.save(vtlDataset);

			VtlDataset vtlDatasetSecond = new VtlDataset();
			vtlDatasetSecond.setPersistent(true);
			vtlDatasetSecond.setName("DS_9");
			vtlDatasetSecond.setIsOnlyAScalar(false);
			vtlDatasetSecond.setTransitory(false);
			vtlDatasetSecond
					.addComponent(translationUtilTest.getVtlComponent("ID_1", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDatasetSecond
					.addComponent(translationUtilTest.getVtlComponent("ID_2", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDatasetSecond
					.addComponent(translationUtilTest.getVtlComponent("ME_1", VtlType.INTEGER, VtlComponentRole.MEASURE));
			vtlDatasetSecond
					.addComponent(translationUtilTest.getVtlComponent("ME_2", VtlType.NUMBER, VtlComponentRole.MEASURE));
			datasetRepository.save(vtlDatasetSecond);

		}
		
		System.out.println("Il profilo attivo è " + environment.getActiveProfiles()[0]);

		String comandSqlResult = "";
		String profileActive = environment.getActiveProfiles()[0];
		if (profileActive.equalsIgnoreCase("oracleSql")) {
			comandSqlResult = "CREATE TABLE DS_r AS (SELECT t2.ID_1 AS ID_1,t2.ID_2 AS ID_2,(t2.ME_1)+(t1.ME_1) AS ME_1,(t2.ME_2)+(t1.ME_2) AS ME_2 FROM DS_8 t2 INNER JOIN DS_9 t1 ON (((t2.ID_1 = t1.ID_1) AND (t2.ID_2 = t1.ID_2))));"+"CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);"+"CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);";
		} else if (profileActive.equalsIgnoreCase("sqlServer")) {
			comandSqlResult = "SELECT t3.* INTO DS_r FROM (SELECT t2.ID_1 AS ID_1,t2.ID_2 AS ID_2,(t2.ME_1)+(t1.ME_1) AS ME_1,(t2.ME_2)+(t1.ME_2) AS ME_2 FROM DS_8 t2 INNER JOIN DS_9 t1 ON (((t2.ID_1 = t1.ID_1) AND (t2.ID_2 = t1.ID_2)))) t3;"+"CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);"+"CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);";
		} else if (profileActive.equalsIgnoreCase("mySql")) {
			comandSqlResult = "CREATE TABLE DS_r AS (SELECT t2.ID_1 AS ID_1,t2.ID_2 AS ID_2,(t2.ME_1)+(t1.ME_1) AS ME_1,(t2.ME_2)+(t1.ME_2) AS ME_2 FROM DS_8 t2 INNER JOIN DS_9 t1 ON (((t2.ID_1 = t1.ID_1) AND (t2.ID_2 = t1.ID_2))));"+"CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);"+"CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);";
		} else if (profileActive.equalsIgnoreCase("postgreSql")) {
			comandSqlResult = "CREATE TABLE DS_r AS (SELECT t2.ID_1 AS ID_1,t2.ID_2 AS ID_2,(t2.ME_1)+(t1.ME_1) AS ME_1,(t2.ME_2)+(t1.ME_2) AS ME_2 FROM DS_8 t2 INNER JOIN DS_9 t1 ON (((t2.ID_1 = t1.ID_1) AND (t2.ID_2 = t1.ID_2))));"+"CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);"+"CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);";
		}
		
		String commandStatements = "DS_r := DS_8 +  DS_9;";
		String result = translationUtilTest.translate(commandStatements);
		System.out.println("Print queryAdditionDs: " +result);
		System.out.println("Print queryAttesaAdditionDs: " +comandSqlResult);
		assertEquals("Test operator AdditionDs", comandSqlResult, result);

	}

	@Test
	@Transactional
	public void additionComponent() throws Exception {
		dbTableUtilityService.resetSchema("t");
		VtlDataset vtlDatasetFind = datasetRepository.findByNameIgnoreCase("DS_1");
		if (vtlDatasetFind == null) {
			VtlDataset vtlDataset = new VtlDataset();

			vtlDataset.setPersistent(true);
			vtlDataset.setName("DS_8");
			vtlDataset.setIsOnlyAScalar(false);
			vtlDataset.setTransitory(false);
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ID_1", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ID_2", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ME_1", VtlType.INTEGER, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ME_2", VtlType.NUMBER, VtlComponentRole.MEASURE));
			datasetRepository.save(vtlDataset);
		}
		
		System.out.println("Il profilo attivo è " + environment.getActiveProfiles()[0]);

		String comandSqlResult = "";
		String profileActive = environment.getActiveProfiles()[0];
		if (profileActive.equalsIgnoreCase("oracleSql")) {
			comandSqlResult = "CREATE TABLE DS_r AS (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,t0.ME_1 AS ME_1,t0.ME_2 AS ME_2,(t0.ME_1)+(3.0) AS Me_3 FROM DS_8 t0);"+"CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);"+"CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);";
		} else if (profileActive.equalsIgnoreCase("sqlServer")) {
			comandSqlResult = "SELECT t1.* INTO DS_r FROM (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,t0.ME_1 AS ME_1,t0.ME_2 AS ME_2,(t0.ME_1)+(3.0) AS Me_3 FROM DS_8 t0) t1;"+"CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);"+"CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);";
		} else if (profileActive.equalsIgnoreCase("mySql")) {
			comandSqlResult = "CREATE TABLE DS_r AS (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,t0.ME_1 AS ME_1,t0.ME_2 AS ME_2,(t0.ME_1)+(3.0) AS Me_3 FROM DS_8 t0);"+"CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);"+"CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);";
		} else if (profileActive.equalsIgnoreCase("postgreSql")) {
			comandSqlResult = "CREATE TABLE DS_r AS (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,t0.ME_1 AS ME_1,t0.ME_2 AS ME_2,(t0.ME_1)+(3.0) AS Me_3 FROM DS_8 t0);"+"CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);"+"CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);";
		}
		
		String commandStatements = "DS_r := DS_8 [ calc Me_3 :=  Me_1  +  3.0 ];";
		String result = translationUtilTest.translate(commandStatements);
		System.out.println("Print queryAdditionComponent: " +result);
		System.out.println("Print queryAttesaAdditionComponent: " +comandSqlResult);
		assertEquals("Test operator AdditionComponent", comandSqlResult, result);

	}

	@Test
	@Transactional
	public void subtractionDs() throws Exception {
		dbTableUtilityService.resetSchema("t");
		VtlDataset vtlDatasetFind = datasetRepository.findByNameIgnoreCase("DS_1");
		if (vtlDatasetFind == null) {
			VtlDataset vtlDataset = new VtlDataset();
			vtlDataset.setPersistent(true);
			vtlDataset.setName("DS_8");
			vtlDataset.setIsOnlyAScalar(false);
			vtlDataset.setTransitory(false);
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ID_1", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ID_2", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ME_1", VtlType.INTEGER, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ME_2", VtlType.NUMBER, VtlComponentRole.MEASURE));
			datasetRepository.save(vtlDataset);

			VtlDataset vtlDatasetSecond = new VtlDataset();
			vtlDatasetSecond.setPersistent(true);
			vtlDatasetSecond.setName("DS_9");
			vtlDatasetSecond.setIsOnlyAScalar(false);
			vtlDatasetSecond.setTransitory(false);
			vtlDatasetSecond
					.addComponent(translationUtilTest.getVtlComponent("ID_1", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDatasetSecond
					.addComponent(translationUtilTest.getVtlComponent("ID_2", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDatasetSecond
					.addComponent(translationUtilTest.getVtlComponent("ME_1", VtlType.INTEGER, VtlComponentRole.MEASURE));
			vtlDatasetSecond
					.addComponent(translationUtilTest.getVtlComponent("ME_2", VtlType.NUMBER, VtlComponentRole.MEASURE));
			datasetRepository.save(vtlDatasetSecond);

		}
		
		System.out.println("Il profilo attivo è " + environment.getActiveProfiles()[0]);

		String comandSqlResult = "";
		String profileActive = environment.getActiveProfiles()[0];
		if (profileActive.equalsIgnoreCase("oracleSql")) {
			comandSqlResult = "CREATE TABLE DS_r AS (SELECT t2.ID_1 AS ID_1,t2.ID_2 AS ID_2,(t2.ME_1)-(t1.ME_1) AS ME_1,(t2.ME_2)-(t1.ME_2) AS ME_2 FROM DS_8 t2 INNER JOIN DS_9 t1 ON (((t2.ID_1 = t1.ID_1) AND (t2.ID_2 = t1.ID_2))));"+"CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);"+"CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);";
		} else if (profileActive.equalsIgnoreCase("sqlServer")) {
			comandSqlResult = "SELECT t3.* INTO DS_r FROM (SELECT t2.ID_1 AS ID_1,t2.ID_2 AS ID_2,(t2.ME_1)-(t1.ME_1) AS ME_1,(t2.ME_2)-(t1.ME_2) AS ME_2 FROM DS_8 t2 INNER JOIN DS_9 t1 ON (((t2.ID_1 = t1.ID_1) AND (t2.ID_2 = t1.ID_2)))) t3;"+"CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);"+"CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);";
		} else if (profileActive.equalsIgnoreCase("mySql")) {
			comandSqlResult = "CREATE TABLE DS_r AS (SELECT t2.ID_1 AS ID_1,t2.ID_2 AS ID_2,(t2.ME_1)-(t1.ME_1) AS ME_1,(t2.ME_2)-(t1.ME_2) AS ME_2 FROM DS_8 t2 INNER JOIN DS_9 t1 ON (((t2.ID_1 = t1.ID_1) AND (t2.ID_2 = t1.ID_2))));"+"CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);"+"CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);";
		} else if (profileActive.equalsIgnoreCase("postgreSql")) {
			comandSqlResult = "CREATE TABLE DS_r AS (SELECT t2.ID_1 AS ID_1,t2.ID_2 AS ID_2,(t2.ME_1)-(t1.ME_1) AS ME_1,(t2.ME_2)-(t1.ME_2) AS ME_2 FROM DS_8 t2 INNER JOIN DS_9 t1 ON (((t2.ID_1 = t1.ID_1) AND (t2.ID_2 = t1.ID_2))));"+"CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);"+"CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);";
		}
		
		String commandStatements = "DS_r := DS_8 -  DS_9;";
		String result = translationUtilTest.translate(commandStatements);
		System.out.println("Print querySubtractionDs: " +result);
		System.out.println("Print queryAttesaSubtractionDs: " +comandSqlResult);
		assertEquals("Test operator SubtractionDs", comandSqlResult, result);

	}

	@Test
	@Transactional
	public void subtractionComponent() throws Exception {
		dbTableUtilityService.resetSchema("t");
		VtlDataset vtlDatasetFind = datasetRepository.findByNameIgnoreCase("DS_1");
		if (vtlDatasetFind == null) {
			VtlDataset vtlDataset = new VtlDataset();

			vtlDataset.setPersistent(true);
			vtlDataset.setName("DS_8");
			vtlDataset.setIsOnlyAScalar(false);
			vtlDataset.setTransitory(false);
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ID_1", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ID_2", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ME_1", VtlType.INTEGER, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ME_2", VtlType.NUMBER, VtlComponentRole.MEASURE));
			datasetRepository.save(vtlDataset);
		}
		
		System.out.println("Il profilo attivo è " + environment.getActiveProfiles()[0]);

		String comandSqlResult = "";
		String profileActive = environment.getActiveProfiles()[0];
		if (profileActive.equalsIgnoreCase("oracleSql")) {
			comandSqlResult = "CREATE TABLE DS_r AS (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,(t0.ME_1)-(3) AS ME_1,(t0.ME_2)-(3) AS ME_2 FROM DS_8 t0);"+"CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);"+"CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);";
		} else if (profileActive.equalsIgnoreCase("sqlServer")) {
			comandSqlResult = "SELECT t1.* INTO DS_r FROM (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,(t0.ME_1)-(3) AS ME_1,(t0.ME_2)-(3) AS ME_2 FROM DS_8 t0) t1;"+"CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);"+"CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);";
		} else if (profileActive.equalsIgnoreCase("mySql")) {
			comandSqlResult = "CREATE TABLE DS_r AS (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,(t0.ME_1)-(3) AS ME_1,(t0.ME_2)-(3) AS ME_2 FROM DS_8 t0);"+"CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);"+"CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);";
		} else if (profileActive.equalsIgnoreCase("postgreSql")) {
			comandSqlResult = "CREATE TABLE DS_r AS (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,(t0.ME_1)-(3) AS ME_1,(t0.ME_2)-(3) AS ME_2 FROM DS_8 t0);"+"CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);"+"CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);";
		}
		
		String commandStatements = "DS_r := DS_8 - 3;";
		String result = translationUtilTest.translate(commandStatements);
		System.out.println("Print querySubtractionComponent: " +result);
		System.out.println("Print queryAttesaSubtractionComponent: " +comandSqlResult);
		assertEquals("Test operator SubtractionComponent", comandSqlResult, result);

	}

	@Test
	@Transactional
	public void subtraction() throws Exception {
		dbTableUtilityService.resetSchema("t");
		VtlDataset vtlDatasetFind = datasetRepository.findByNameIgnoreCase("DS_1");
		if (vtlDatasetFind == null) {
			VtlDataset vtlDataset = new VtlDataset();

			vtlDataset.setPersistent(true);
			vtlDataset.setName("DS_8");
			vtlDataset.setIsOnlyAScalar(false);
			vtlDataset.setTransitory(false);
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ID_1", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ID_2", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ME_1", VtlType.INTEGER, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ME_2", VtlType.NUMBER, VtlComponentRole.MEASURE));
			datasetRepository.save(vtlDataset);
		}
		
		System.out.println("Il profilo attivo è " + environment.getActiveProfiles()[0]);

		String comandSqlResult = "";
		String profileActive = environment.getActiveProfiles()[0];
		if (profileActive.equalsIgnoreCase("oracleSql")) {
			comandSqlResult = "CREATE TABLE DS_r AS (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,t0.ME_1 AS ME_1,t0.ME_2 AS ME_2,(t0.ME_1)-(3) AS Me_3 FROM DS_8 t0);"+"CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);"+"CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);";
		} else if (profileActive.equalsIgnoreCase("sqlServer")) {
			comandSqlResult = "SELECT t1.* INTO DS_r FROM (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,t0.ME_1 AS ME_1,t0.ME_2 AS ME_2,(t0.ME_1)-(3) AS Me_3 FROM DS_8 t0) t1;"+"CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);"+"CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);";
		} else if (profileActive.equalsIgnoreCase("mySql")) {
			comandSqlResult = "CREATE TABLE DS_r AS (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,t0.ME_1 AS ME_1,t0.ME_2 AS ME_2,(t0.ME_1)-(3) AS Me_3 FROM DS_8 t0);"+"CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);"+"CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);";
		} else if (profileActive.equalsIgnoreCase("postgreSql")) {
			comandSqlResult = "CREATE TABLE DS_r AS (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,t0.ME_1 AS ME_1,t0.ME_2 AS ME_2,(t0.ME_1)-(3) AS Me_3 FROM DS_8 t0);"+"CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);"+"CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);";
		}
		
		String commandStatements = "DS_r := DS_8 [ calc Me_3 :=  Me_1  -  3 ];";
//		String comandSqlResult = "CREATE TABLE DS_r AS (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,t0.ME_1 AS ME_1,t0.ME_2 AS ME_2,(t0.ME_1)-(3) AS Me_3 FROM DS_8 t0);"+"CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);"+"CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);"; 
		String result = translationUtilTest.translate(commandStatements);
		System.out.println("Print querySubtraction: " +result);
		System.out.println("Print queryAttesaSubtraction: " +comandSqlResult);
		assertEquals("Test operator Subtraction", comandSqlResult, result);

	}
	
	@Test
	@Transactional
	public void multiplicationDs() throws Exception {
		dbTableUtilityService.resetSchema("t");
		VtlDataset vtlDatasetFind = datasetRepository.findByNameIgnoreCase("DS_1");
		if (vtlDatasetFind == null) {
			VtlDataset vtlDataset = new VtlDataset();
			vtlDataset.setPersistent(true);
			vtlDataset.setName("DS_8");
			vtlDataset.setIsOnlyAScalar(false);
			vtlDataset.setTransitory(false);
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ID_1", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ID_2", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ME_1", VtlType.INTEGER, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ME_2", VtlType.NUMBER, VtlComponentRole.MEASURE));
			datasetRepository.save(vtlDataset);

			VtlDataset vtlDatasetSecond = new VtlDataset();
			vtlDatasetSecond.setPersistent(true);
			vtlDatasetSecond.setName("DS_9");
			vtlDatasetSecond.setIsOnlyAScalar(false);
			vtlDatasetSecond.setTransitory(false);
			vtlDatasetSecond
					.addComponent(translationUtilTest.getVtlComponent("ID_1", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDatasetSecond
					.addComponent(translationUtilTest.getVtlComponent("ID_2", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDatasetSecond
					.addComponent(translationUtilTest.getVtlComponent("ME_1", VtlType.INTEGER, VtlComponentRole.MEASURE));
			vtlDatasetSecond
					.addComponent(translationUtilTest.getVtlComponent("ME_2", VtlType.NUMBER, VtlComponentRole.MEASURE));
			datasetRepository.save(vtlDatasetSecond);
		}
		
		System.out.println("Il profilo attivo è " + environment.getActiveProfiles()[0]);

		String comandSqlResult = "";
		String profileActive = environment.getActiveProfiles()[0];
		if (profileActive.equalsIgnoreCase("oracleSql")) {
			comandSqlResult = "CREATE TABLE DS_r AS (SELECT t2.ID_1 AS ID_1,t2.ID_2 AS ID_2,(t2.ME_1)*(t1.ME_1) AS ME_1,(t2.ME_2)*(t1.ME_2) AS ME_2 FROM DS_8 t2 INNER JOIN DS_9 t1 ON (((t2.ID_1 = t1.ID_1) AND (t2.ID_2 = t1.ID_2))));"+"CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);"+"CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);";
		} else if (profileActive.equalsIgnoreCase("sqlServer")) {
			comandSqlResult = "SELECT t3.* INTO DS_r FROM (SELECT t2.ID_1 AS ID_1,t2.ID_2 AS ID_2,(t2.ME_1)*(t1.ME_1) AS ME_1,(t2.ME_2)*(t1.ME_2) AS ME_2 FROM DS_8 t2 INNER JOIN DS_9 t1 ON (((t2.ID_1 = t1.ID_1) AND (t2.ID_2 = t1.ID_2)))) t3;"+"CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);"+"CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);";
		} else if (profileActive.equalsIgnoreCase("mySql")) {
			comandSqlResult = "CREATE TABLE DS_r AS (SELECT t2.ID_1 AS ID_1,t2.ID_2 AS ID_2,(t2.ME_1)*(t1.ME_1) AS ME_1,(t2.ME_2)*(t1.ME_2) AS ME_2 FROM DS_8 t2 INNER JOIN DS_9 t1 ON (((t2.ID_1 = t1.ID_1) AND (t2.ID_2 = t1.ID_2))));"+"CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);"+"CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);";
		} else if (profileActive.equalsIgnoreCase("postgreSql")) {
			comandSqlResult = "CREATE TABLE DS_r AS (SELECT t2.ID_1 AS ID_1,t2.ID_2 AS ID_2,(t2.ME_1)*(t1.ME_1) AS ME_1,(t2.ME_2)*(t1.ME_2) AS ME_2 FROM DS_8 t2 INNER JOIN DS_9 t1 ON (((t2.ID_1 = t1.ID_1) AND (t2.ID_2 = t1.ID_2))));"+"CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);"+"CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);";
		}
		
		String commandStatements = "DS_r := DS_8 * DS_9;";
		String result = translationUtilTest.translate(commandStatements);
		System.out.println("Print queryMultiplicationDs: " +result);
		System.out.println("Print queryAttesaMultiplicationDs: " +comandSqlResult);
		assertEquals("Test operator MultiplicationDs", comandSqlResult, result);
	}

	@Test
	@Transactional
	public void multiplication() throws Exception {
		dbTableUtilityService.resetSchema("t");
		VtlDataset vtlDatasetFind = datasetRepository.findByNameIgnoreCase("DS_1");
		if (vtlDatasetFind == null) {
			VtlDataset vtlDataset = new VtlDataset();

			vtlDataset.setPersistent(true);
			vtlDataset.setName("DS_8");
			vtlDataset.setIsOnlyAScalar(false);
			vtlDataset.setTransitory(false);
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ID_1", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ID_2", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ME_1", VtlType.INTEGER, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ME_2", VtlType.NUMBER, VtlComponentRole.MEASURE));
			datasetRepository.save(vtlDataset);
		}
		
		System.out.println("Il profilo attivo è " + environment.getActiveProfiles()[0]);

		String comandSqlResult = "";
		String profileActive = environment.getActiveProfiles()[0];
		if (profileActive.equalsIgnoreCase("oracleSql")) {
			comandSqlResult = "CREATE TABLE DS_r AS (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,(t0.ME_1)*(-3) AS ME_1,(t0.ME_2)*(-3) AS ME_2 FROM DS_8 t0);"+"CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);"+"CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);"; 
		} else if (profileActive.equalsIgnoreCase("sqlServer")) {
			comandSqlResult = "SELECT t1.* INTO DS_r FROM (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,(t0.ME_1)*(-3) AS ME_1,(t0.ME_2)*(-3) AS ME_2 FROM DS_8 t0) t1;"+"CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);"+"CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);";
		} else if (profileActive.equalsIgnoreCase("mySql")) {
			comandSqlResult = "CREATE TABLE DS_r AS (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,(t0.ME_1)*(-3) AS ME_1,(t0.ME_2)*(-3) AS ME_2 FROM DS_8 t0);"+"CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);"+"CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);"; 
		} else if (profileActive.equalsIgnoreCase("postgreSql")) {
			comandSqlResult = "CREATE TABLE DS_r AS (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,(t0.ME_1)*(-3) AS ME_1,(t0.ME_2)*(-3) AS ME_2 FROM DS_8 t0);"+"CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);"+"CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);"; 
		}
		
		String commandStatements = "DS_r := DS_8 * -3;";
		String result = translationUtilTest.translate(commandStatements);
		System.out.println("Print queryMultiplication: " +result);
		System.out.println("Print queryAttesaMultiplication: " +comandSqlResult);
		assertEquals("Test operator Multiplication", comandSqlResult, result);
	}

	@Test
	@Transactional
	public void multiplicationInt() throws Exception {
		dbTableUtilityService.resetSchema("t");
		System.out.println("Il profilo attivo è " + environment.getActiveProfiles()[0]);
		
		String comandSqlResult = "";
		String profileActive = environment.getActiveProfiles()[0];
		if (profileActive.equalsIgnoreCase("oracleSql")) {
			comandSqlResult = "CREATE TABLE DS_r AS (SELECT (7)*(6) AS int_var FROM DUAL);"; 
		} else if (profileActive.equalsIgnoreCase("sqlServer")) {
			comandSqlResult = "SELECT t0.* INTO DS_r FROM (SELECT (7)*(6) AS int_var) t0;";
		} else if (profileActive.equalsIgnoreCase("mySql")) {
			comandSqlResult = "CREATE TABLE DS_r AS (SELECT (7)*(6) AS int_var FROM DUAL);"; 
		} else if (profileActive.equalsIgnoreCase("postgreSql")) {
			comandSqlResult = "CREATE TABLE DS_r AS (SELECT (7)*(6) AS int_var);";
		}
		
		String commandStatements = "DS_r := 7 * 6;";
		String result = translationUtilTest.translate(commandStatements);
		System.out.println("Print queryMultiplicationInt: " +result);
		System.out.println("Print queryAttesaMultiplicationInt: " +comandSqlResult);
		assertEquals("Test operator MultiplicationInt", comandSqlResult, result);
	}

	@Test
	@Transactional
	public void multiplicationComponent() throws Exception {
		dbTableUtilityService.resetSchema("t");
		VtlDataset vtlDatasetFind = datasetRepository.findByNameIgnoreCase("DS_1");
		if (vtlDatasetFind == null) {
			VtlDataset vtlDataset = new VtlDataset();

			vtlDataset.setPersistent(true);
			vtlDataset.setName("DS_8");
			vtlDataset.setIsOnlyAScalar(false);
			vtlDataset.setTransitory(false);
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ID_1", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ID_2", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ME_1", VtlType.INTEGER, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ME_2", VtlType.NUMBER, VtlComponentRole.MEASURE));
			datasetRepository.save(vtlDataset);
		}
		System.out.println("Il profilo attivo è " + environment.getActiveProfiles()[0]);
		String comandSqlResult = "";
		String profileActive = environment.getActiveProfiles()[0];
		if (profileActive.equalsIgnoreCase("oracleSql")) {
			comandSqlResult = "CREATE TABLE DS_r AS (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,t0.ME_1 AS ME_1,t0.ME_2 AS ME_2,(t0.ME_1)*(t0.ME_2) AS Me_3 FROM DS_8 t0);"+"CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);"+"CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);";  
		} else if (profileActive.equalsIgnoreCase("sqlServer")) {
			comandSqlResult = "SELECT t1.* INTO DS_r FROM (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,t0.ME_1 AS ME_1,t0.ME_2 AS ME_2,(t0.ME_1)*(t0.ME_2) AS Me_3 FROM DS_8 t0) t1;"+"CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);"+"CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);"; 
		} else if (profileActive.equalsIgnoreCase("mySql")) {
			comandSqlResult = "CREATE TABLE DS_r AS (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,t0.ME_1 AS ME_1,t0.ME_2 AS ME_2,(t0.ME_1)*(t0.ME_2) AS Me_3 FROM DS_8 t0);"+"CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);"+"CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);"; 
		} else if (profileActive.equalsIgnoreCase("postgreSql")) {
			comandSqlResult = "CREATE TABLE DS_r AS (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,t0.ME_1 AS ME_1,t0.ME_2 AS ME_2,(t0.ME_1)*(t0.ME_2) AS Me_3 FROM DS_8 t0);"+"CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);"+"CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);"; 
		}
		
		String commandStatements = "DS_r := DS_8 [ calc Me_3 :=  Me_1  * Me_2 ];";
		String result = translationUtilTest.translate(commandStatements);
		System.out.println("Print queryMultiplicationComponent: " +result);
		System.out.println("Print queryAttesaMultiplicationComponent: " +comandSqlResult);
		assertEquals("Test operator MultiplicationComponent", comandSqlResult, result);

	}

	@Test
	@Transactional
	public void division() throws Exception {
		dbTableUtilityService.resetSchema("t");
		VtlDataset vtlDatasetFind = datasetRepository.findByNameIgnoreCase("DS_1");
		if (vtlDatasetFind == null) {
			VtlDataset vtlDataset = new VtlDataset();

			vtlDataset.setPersistent(true);
			vtlDataset.setName("DS_8");
			vtlDataset.setIsOnlyAScalar(false);
			vtlDataset.setTransitory(false);
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ID_1", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ID_2", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ME_1", VtlType.INTEGER, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ME_2", VtlType.NUMBER, VtlComponentRole.MEASURE));
			datasetRepository.save(vtlDataset);
		}
		System.out.println("Il profilo attivo è " + environment.getActiveProfiles()[0]);
		String comandSqlResult = "";
		String profileActive = environment.getActiveProfiles()[0];
		if (profileActive.equalsIgnoreCase("oracleSql")) {
			comandSqlResult = "CREATE TABLE DS_r AS (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,(t0.ME_1)/(10) AS ME_1,(t0.ME_2)/(10) AS ME_2 FROM DS_8 t0);"+"CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);"+"CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);";   
		} else if (profileActive.equalsIgnoreCase("sqlServer")) {
			comandSqlResult = "SELECT t1.* INTO DS_r FROM (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,(t0.ME_1)/(10) AS ME_1,(t0.ME_2)/(10) AS ME_2 FROM DS_8 t0) t1;"+"CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);"+"CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);"; 
		} else if (profileActive.equalsIgnoreCase("mySql")) {
			comandSqlResult = "CREATE TABLE DS_r AS (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,(t0.ME_1)/(10) AS ME_1,(t0.ME_2)/(10) AS ME_2 FROM DS_8 t0);"+"CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);"+"CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);";  
		} else if (profileActive.equalsIgnoreCase("postgreSql")) {
			comandSqlResult = "CREATE TABLE DS_r AS (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,(t0.ME_1)/(10) AS ME_1,(t0.ME_2)/(10) AS ME_2 FROM DS_8 t0);"+"CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);"+"CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);"; 
		}
		
		String commandStatements = "DS_r := DS_8 /  10;";
		String result = translationUtilTest.translate(commandStatements);
		System.out.println("Print queryDivision: " +result);
		System.out.println("Print queryAttesaDivision: " +comandSqlResult);
		assertEquals("Test operator Division", comandSqlResult, result);

	}

	@Test
	@Transactional
	public void divisionInt() throws Exception {
		dbTableUtilityService.resetSchema("t");
		System.out.println("Il profilo attivo è " + environment.getActiveProfiles()[0]);
		String comandSqlResult = "";
		String profileActive = environment.getActiveProfiles()[0];
		if (profileActive.equalsIgnoreCase("oracleSql")) {
			comandSqlResult = "CREATE TABLE DS_r AS (SELECT (8)/(2) AS int_var FROM DUAL);";  
		} else if (profileActive.equalsIgnoreCase("sqlServer")) {
			comandSqlResult = "SELECT t0.* INTO DS_r FROM (SELECT (8)/(2) AS int_var) t0;"; 
		} else if (profileActive.equalsIgnoreCase("mySql")) {
			comandSqlResult = "CREATE TABLE DS_r AS (SELECT (8)/(2) AS int_var FROM DUAL);";  
		} else if (profileActive.equalsIgnoreCase("postgreSql")) {
			comandSqlResult = "CREATE TABLE DS_r AS (SELECT (8)/(2) AS int_var);"; 
		}
		
		String commandStatements = "DS_r := 8 / 2;";
		String result = translationUtilTest.translate(commandStatements);
		System.out.println("Print queryDivisionInt: " +result);
		System.out.println("Print queryAttesaDivisionInt: " +comandSqlResult);
		assertEquals("Test operator DivisionInt", comandSqlResult, result);
	}

	@Test
	@Transactional
	public void divisionDs() throws Exception {
		dbTableUtilityService.resetSchema("t");
		VtlDataset vtlDatasetFind = datasetRepository.findByNameIgnoreCase("DS_1");
		if (vtlDatasetFind == null) {
			VtlDataset vtlDataset = new VtlDataset();

			vtlDataset.setPersistent(true);
			vtlDataset.setName("DS_8");
			vtlDataset.setIsOnlyAScalar(false);
			vtlDataset.setTransitory(false);
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ID_1", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ID_2", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ME_1", VtlType.INTEGER, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ME_2", VtlType.NUMBER, VtlComponentRole.MEASURE));
			datasetRepository.save(vtlDataset);

			VtlDataset vtlDatasetSecond = new VtlDataset();
			vtlDatasetSecond.setPersistent(true);
			vtlDatasetSecond.setName("DS_9");
			vtlDatasetSecond.setIsOnlyAScalar(false);
			vtlDatasetSecond.setTransitory(false);
			vtlDatasetSecond
					.addComponent(translationUtilTest.getVtlComponent("ID_1", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDatasetSecond
					.addComponent(translationUtilTest.getVtlComponent("ID_2", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDatasetSecond
					.addComponent(translationUtilTest.getVtlComponent("ME_1", VtlType.INTEGER, VtlComponentRole.MEASURE));
			vtlDatasetSecond
					.addComponent(translationUtilTest.getVtlComponent("ME_2", VtlType.NUMBER, VtlComponentRole.MEASURE));
			datasetRepository.save(vtlDatasetSecond);

		}
		
		System.out.println("Il profilo attivo è " + environment.getActiveProfiles()[0]);
		String comandSqlResult = "";
		String profileActive = environment.getActiveProfiles()[0];
		if (profileActive.equalsIgnoreCase("oracleSql")) {
			comandSqlResult = "CREATE TABLE DS_r AS (SELECT t2.ID_1 AS ID_1,t2.ID_2 AS ID_2,(t2.ME_1)/(t1.ME_1) AS ME_1,(t2.ME_2)/(t1.ME_2) AS ME_2 FROM DS_8 t2 INNER JOIN DS_9 t1 ON (((t2.ID_1 = t1.ID_1) AND (t2.ID_2 = t1.ID_2))));"+"CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);"+"CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);"; 
		} else if (profileActive.equalsIgnoreCase("sqlServer")) {
			comandSqlResult = "SELECT t3.* INTO DS_r FROM (SELECT t2.ID_1 AS ID_1,t2.ID_2 AS ID_2,(t2.ME_1)/(t1.ME_1) AS ME_1,(t2.ME_2)/(t1.ME_2) AS ME_2 FROM DS_8 t2 INNER JOIN DS_9 t1 ON (((t2.ID_1 = t1.ID_1) AND (t2.ID_2 = t1.ID_2)))) t3;"+"CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);"+"CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);"; 
		} else if (profileActive.equalsIgnoreCase("mySql")) {
			comandSqlResult = "CREATE TABLE DS_r AS (SELECT t2.ID_1 AS ID_1,t2.ID_2 AS ID_2,(t2.ME_1)/(t1.ME_1) AS ME_1,(t2.ME_2)/(t1.ME_2) AS ME_2 FROM DS_8 t2 INNER JOIN DS_9 t1 ON (((t2.ID_1 = t1.ID_1) AND (t2.ID_2 = t1.ID_2))));"+"CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);"+"CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);"; 
		} else if (profileActive.equalsIgnoreCase("postgreSql")) {
			comandSqlResult = "CREATE TABLE DS_r AS (SELECT t2.ID_1 AS ID_1,t2.ID_2 AS ID_2,(t2.ME_1)/(t1.ME_1) AS ME_1,(t2.ME_2)/(t1.ME_2) AS ME_2 FROM DS_8 t2 INNER JOIN DS_9 t1 ON (((t2.ID_1 = t1.ID_1) AND (t2.ID_2 = t1.ID_2))));"+"CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);"+"CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);";
		}
		
		String commandStatements = "DS_r := DS_8 /  DS_9;";
		String result = translationUtilTest.translate(commandStatements);
		System.out.println("Print queryDivisionDs: " +result);
		System.out.println("Print queryAttesaDivisionDs: " +comandSqlResult);
		assertEquals("Test operator DivisionDs", comandSqlResult, result);

	}

	@Test
	@Transactional
	public void divisionComponent() throws Exception {
		dbTableUtilityService.resetSchema("t");
		VtlDataset vtlDatasetFind = datasetRepository.findByNameIgnoreCase("DS_1");
		if (vtlDatasetFind == null) {
			VtlDataset vtlDataset = new VtlDataset();

			vtlDataset.setPersistent(true);
			vtlDataset.setName("DS_8");
			vtlDataset.setIsOnlyAScalar(false);
			vtlDataset.setTransitory(false);
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ID_1", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ID_2", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ME_1", VtlType.INTEGER, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ME_2", VtlType.NUMBER, VtlComponentRole.MEASURE));
			datasetRepository.save(vtlDataset);
		}
		
		System.out.println("Il profilo attivo è " + environment.getActiveProfiles()[0]);
		String comandSqlResult = "";
		String profileActive = environment.getActiveProfiles()[0];
		if (profileActive.equalsIgnoreCase("oracleSql")) {
			comandSqlResult = "CREATE TABLE DS_r AS (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,t0.ME_1 AS ME_1,t0.ME_2 AS ME_2,(t0.ME_2)/(t0.ME_1) AS Me_3 FROM DS_8 t0);"+"CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);"+"CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);";  
		} else if (profileActive.equalsIgnoreCase("sqlServer")) {
			comandSqlResult = "SELECT t1.* INTO DS_r FROM (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,t0.ME_1 AS ME_1,t0.ME_2 AS ME_2,(t0.ME_2)/(t0.ME_1) AS Me_3 FROM DS_8 t0) t1;"+"CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);"+"CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);"; 
		} else if (profileActive.equalsIgnoreCase("mySql")) {
			comandSqlResult = "CREATE TABLE DS_r AS (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,t0.ME_1 AS ME_1,t0.ME_2 AS ME_2,(t0.ME_2)/(t0.ME_1) AS Me_3 FROM DS_8 t0);"+"CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);"+"CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);"; 
		} else if (profileActive.equalsIgnoreCase("postgreSql")) {
			comandSqlResult = "CREATE TABLE DS_r AS (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,t0.ME_1 AS ME_1,t0.ME_2 AS ME_2,(t0.ME_2)/(t0.ME_1) AS Me_3 FROM DS_8 t0);"+"CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);"+"CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);"; 
		}
		
		String commandStatements = "DS_r := DS_8 [ calc Me_3 :=  Me_2  /  Me_1 ];";
		String result = translationUtilTest.translate(commandStatements);
		System.out.println("Print queryDivisionComponent: " +result);
		System.out.println("Print queryAttesaDivisionComponent: " +comandSqlResult);
		assertEquals("Test operator DivisionComponent", comandSqlResult, result);

	}

	@Test
	@Transactional
	public void mod() throws Exception {
		dbTableUtilityService.resetSchema("t");
		VtlDataset vtlDatasetFind = datasetRepository.findByNameIgnoreCase("DS_1");
		if (vtlDatasetFind == null) {
			VtlDataset vtlDataset = new VtlDataset();

			vtlDataset.setPersistent(true);
			vtlDataset.setName("DS_8");
			vtlDataset.setIsOnlyAScalar(false);
			vtlDataset.setTransitory(false);
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ID_1", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ID_2", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ME_1", VtlType.INTEGER, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ME_2", VtlType.NUMBER, VtlComponentRole.MEASURE));
			datasetRepository.save(vtlDataset);
		}
		
		System.out.println("Il profilo attivo è " + environment.getActiveProfiles()[0]);
		String comandSqlResult = "";
		String profileActive = environment.getActiveProfiles()[0];
		if (profileActive.equalsIgnoreCase("oracleSql")) {
			comandSqlResult =  "CREATE TABLE DS_r AS (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,mod(t0.ME_1,15) AS ME_1,mod(t0.ME_2,15) AS ME_2 FROM DS_8 t0);"+"CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);"+"CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);"; 
		} else if (profileActive.equalsIgnoreCase("sqlServer")) {
			comandSqlResult = "SELECT t1.* INTO DS_r FROM (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,((t0.ME_1)%(15)) AS ME_1,((t0.ME_2)%(15)) AS ME_2 FROM DS_8 t0) t1;"+"CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);"+"CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);"; 
		} else if (profileActive.equalsIgnoreCase("mySql")) {
			comandSqlResult =  "CREATE TABLE DS_r AS (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,mod(t0.ME_1,15) AS ME_1,mod(t0.ME_2,15) AS ME_2 FROM DS_8 t0);"+"CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);"+"CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);"; 
		} else if (profileActive.equalsIgnoreCase("postgreSql")) {
			comandSqlResult =  "CREATE TABLE DS_r AS (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,mod(t0.ME_1,15) AS ME_1,mod(t0.ME_2,15) AS ME_2 FROM DS_8 t0);"+"CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);"+"CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);"; 
		}
		
		String commandStatements = "DS_r := mod ( DS_8, 15 );";
		String result = translationUtilTest.translate(commandStatements);
		System.out.println("Print queryMod: " +result);
		System.out.println("Print queryAttesaMod: " +comandSqlResult);
		assertEquals("Test operator Mod", comandSqlResult, result);

	}

	@Test
	@Transactional
	public void modInt() throws Exception {
		
		dbTableUtilityService.resetSchema("t");
		
		System.out.println("Il profilo attivo è " + environment.getActiveProfiles()[0]);
		String comandSqlResult = "";
		String profileActive = environment.getActiveProfiles()[0];
		if (profileActive.equalsIgnoreCase("oracleSql")) {
			comandSqlResult =  "CREATE TABLE DS_r AS (SELECT mod(5,2) AS int_var FROM DUAL);";
		} else if (profileActive.equalsIgnoreCase("sqlServer")) {
			comandSqlResult = "SELECT t0.* INTO DS_r FROM (SELECT ((5)%(2)) AS int_var) t0;"; 
		} else if (profileActive.equalsIgnoreCase("mySql")) {
			comandSqlResult =  "CREATE TABLE DS_r AS (SELECT mod(5,2) AS int_var FROM DUAL);"; 
		} else if (profileActive.equalsIgnoreCase("postgreSql")) {
			comandSqlResult =  "CREATE TABLE DS_r AS (SELECT mod(5,2) AS int_var);";
		}

		String commandStatements = "DS_r := mod ( 5, 2 );";
		String result = translationUtilTest.translate(commandStatements);
		System.out.println("Print queryModInt: " +result);
		System.out.println("Print queryAttesaModInt: " +comandSqlResult);
		assertEquals("Test operator ModInt", comandSqlResult, result);

	}

	@Test
	@Transactional
	public void modDs() throws Exception {
		dbTableUtilityService.resetSchema("t");
		VtlDataset vtlDatasetFind = datasetRepository.findByNameIgnoreCase("DS_1");
		if (vtlDatasetFind == null) {
			VtlDataset vtlDataset = new VtlDataset();

			vtlDataset.setPersistent(true);
			vtlDataset.setName("DS_8");
			vtlDataset.setIsOnlyAScalar(false);
			vtlDataset.setTransitory(false);
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ID_1", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ID_2", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ME_1", VtlType.INTEGER, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ME_2", VtlType.NUMBER, VtlComponentRole.MEASURE));
			datasetRepository.save(vtlDataset);

			VtlDataset vtlDatasetSecond = new VtlDataset();
			vtlDatasetSecond.setPersistent(true);
			vtlDatasetSecond.setName("DS_9");
			vtlDatasetSecond.setIsOnlyAScalar(false);
			vtlDatasetSecond.setTransitory(false);
			vtlDatasetSecond
					.addComponent(translationUtilTest.getVtlComponent("ID_1", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDatasetSecond
					.addComponent(translationUtilTest.getVtlComponent("ID_2", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDatasetSecond
					.addComponent(translationUtilTest.getVtlComponent("ME_1", VtlType.INTEGER, VtlComponentRole.MEASURE));
			vtlDatasetSecond
					.addComponent(translationUtilTest.getVtlComponent("ME_2", VtlType.NUMBER, VtlComponentRole.MEASURE));
			datasetRepository.save(vtlDatasetSecond);

		}
		
		System.out.println("Il profilo attivo è " + environment.getActiveProfiles()[0]);
		String comandSqlResult = "";
		String profileActive = environment.getActiveProfiles()[0];
		if (profileActive.equalsIgnoreCase("oracleSql")) {
			comandSqlResult =  "CREATE TABLE DS_r AS (SELECT t2.ID_1 AS ID_1,t2.ID_2 AS ID_2,mod(t2.ME_1,t1.ME_1) AS ME_1,mod(t2.ME_2,t1.ME_2) AS ME_2 FROM DS_8 t2 INNER JOIN DS_9 t1 ON (((t2.ID_1 = t1.ID_1) AND (t2.ID_2 = t1.ID_2))));"+"CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);"+"CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);";
		} else if (profileActive.equalsIgnoreCase("sqlServer")) {
			comandSqlResult = "SELECT t3.* INTO DS_r FROM (SELECT t2.ID_1 AS ID_1,t2.ID_2 AS ID_2,((t2.ME_1)%(t1.ME_1)) AS ME_1,((t2.ME_2)%(t1.ME_2)) AS ME_2 FROM DS_8 t2 INNER JOIN DS_9 t1 ON (((t2.ID_1 = t1.ID_1) AND (t2.ID_2 = t1.ID_2)))) t3;"+"CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);"+"CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);"; 
		} else if (profileActive.equalsIgnoreCase("mySql")) {
			comandSqlResult =  "CREATE TABLE DS_r AS (SELECT t2.ID_1 AS ID_1,t2.ID_2 AS ID_2,mod(t2.ME_1,t1.ME_1) AS ME_1,mod(t2.ME_2,t1.ME_2) AS ME_2 FROM DS_8 t2 INNER JOIN DS_9 t1 ON (((t2.ID_1 = t1.ID_1) AND (t2.ID_2 = t1.ID_2))));"+"CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);"+"CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);"; 
		} else if (profileActive.equalsIgnoreCase("postgreSql")) {
			comandSqlResult =  "CREATE TABLE DS_r AS (SELECT t2.ID_1 AS ID_1,t2.ID_2 AS ID_2,mod(t2.ME_1,t1.ME_1) AS ME_1,mod(t2.ME_2,t1.ME_2) AS ME_2 FROM DS_8 t2 INNER JOIN DS_9 t1 ON (((t2.ID_1 = t1.ID_1) AND (t2.ID_2 = t1.ID_2))));"+"CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);"+"CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);"; 
		}
		
		String commandStatements = "DS_r := mod ( DS_8, DS_9 );";
		String result = translationUtilTest.translate(commandStatements);
		System.out.println("Print queryModDs: " +result);
		System.out.println("Print queryAttesaModDs: " +comandSqlResult);
		assertEquals("Test operator ModDs", comandSqlResult, result);
	}

	@Test
	@Transactional
	public void modComponent() throws Exception {
		dbTableUtilityService.resetSchema("t");
		VtlDataset vtlDatasetFind = datasetRepository.findByNameIgnoreCase("DS_1");
		if (vtlDatasetFind == null) {
			VtlDataset vtlDataset = new VtlDataset();

			vtlDataset.setPersistent(true);
			vtlDataset.setName("DS_8");
			vtlDataset.setIsOnlyAScalar(false);
			vtlDataset.setTransitory(false);
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ID_1", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ID_2", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ME_1", VtlType.INTEGER, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ME_2", VtlType.NUMBER, VtlComponentRole.MEASURE));
			datasetRepository.save(vtlDataset);
		}
		
		System.out.println("Il profilo attivo è " + environment.getActiveProfiles()[0]);
		String comandSqlResult = "";
		String profileActive = environment.getActiveProfiles()[0];
		if (profileActive.equalsIgnoreCase("oracleSql")) {
			comandSqlResult =  "CREATE TABLE DS_r AS (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,t0.ME_1 AS ME_1,t0.ME_2 AS ME_2,mod(t0.ME_1,3.0) AS Me_3 FROM DS_8 t0);"+"CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);"+"CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);";
		} else if (profileActive.equalsIgnoreCase("sqlServer")) {
			comandSqlResult = "SELECT t1.* INTO DS_r FROM (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,t0.ME_1 AS ME_1,t0.ME_2 AS ME_2,((t0.ME_1)%(3.0)) AS Me_3 FROM DS_8 t0) t1;"+"CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);"+"CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);"; 
		} else if (profileActive.equalsIgnoreCase("mySql")) {
			comandSqlResult =  "CREATE TABLE DS_r AS (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,t0.ME_1 AS ME_1,t0.ME_2 AS ME_2,mod(t0.ME_1,3.0) AS Me_3 FROM DS_8 t0);"+"CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);"+"CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);"; 
		} else if (profileActive.equalsIgnoreCase("postgreSql")) {
			comandSqlResult =  "CREATE TABLE DS_r AS (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,t0.ME_1 AS ME_1,t0.ME_2 AS ME_2,mod(t0.ME_1,3.0) AS Me_3 FROM DS_8 t0);"+"CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);"+"CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);";
		}

		String commandStatements = "DS_r := DS_8[ calc Me_3 := mod( Me_1,  3.0 ) ];";
		String result = translationUtilTest.translate(commandStatements);
		System.out.println("Print queryModComponent: " +result);
		System.out.println("Print queryAttesaModComponent: " +comandSqlResult);
		assertEquals("Test operator ModComponent", comandSqlResult, result);

	}

	@Test
	@Transactional
	public void trunc() throws Exception {
		dbTableUtilityService.resetSchema("t");
		VtlDataset vtlDatasetFind = datasetRepository.findByNameIgnoreCase("DS_1");
		if (vtlDatasetFind == null) {
			VtlDataset vtlDataset = new VtlDataset();

			vtlDataset.setPersistent(true);
			vtlDataset.setName("DS_10");
			vtlDataset.setIsOnlyAScalar(false);
			vtlDataset.setTransitory(false);
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ID_1", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ID_2", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ME_1", VtlType.NUMBER, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ME_2", VtlType.NUMBER, VtlComponentRole.MEASURE));
			datasetRepository.save(vtlDataset);

		}
		
		System.out.println("Il profilo attivo è " + environment.getActiveProfiles()[0]);
		String comandSqlResult = "";
		String profileActive = environment.getActiveProfiles()[0];
		if (profileActive.equalsIgnoreCase("oracleSql")) {
			comandSqlResult =  "CREATE TABLE DS_r AS (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,trunc(t0.ME_1,0) AS ME_1,trunc(t0.ME_2,0) AS ME_2 FROM DS_10 t0);"+"CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);"+"CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);";
		} else if (profileActive.equalsIgnoreCase("sqlServer")) {
			comandSqlResult = "SELECT t1.* INTO DS_r FROM (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,round(t0.ME_1,0) AS ME_1,round(t0.ME_2,0) AS ME_2 FROM DS_10 t0) t1;"+"CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);"+"CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);";
		} else if (profileActive.equalsIgnoreCase("mySql")) {
			comandSqlResult =  "CREATE TABLE DS_r AS (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,truncate(t0.ME_1,0) AS ME_1,truncate(t0.ME_2,0) AS ME_2 FROM DS_10 t0);"+"CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);"+"CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);";
		} else if (profileActive.equalsIgnoreCase("postgreSql")) {
			comandSqlResult =  "CREATE TABLE DS_r AS (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,trunc(t0.ME_1,0) AS ME_1,trunc(t0.ME_2,0) AS ME_2 FROM DS_10 t0);"+"CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);"+"CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);";
		}
		
		String commandStatements = "DS_r := trunc(DS_10, 0);";
		String result = translationUtilTest.translate(commandStatements);
		System.out.println("Print queryTrunc: " +result);
		System.out.println("Print queryAttesaTrunc: " +comandSqlResult);
		assertEquals("Test operator Trunc", comandSqlResult, result);

	}

	@Test
	@Transactional
	public void truncComponent() throws Exception {
		dbTableUtilityService.resetSchema("t");
		VtlDataset vtlDatasetFind = datasetRepository.findByNameIgnoreCase("DS_1");
		if (vtlDatasetFind == null) {
			VtlDataset vtlDataset = new VtlDataset();
			vtlDataset.setPersistent(true);
			vtlDataset.setName("DS_10");
			vtlDataset.setIsOnlyAScalar(false);
			vtlDataset.setTransitory(false);
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ID_1", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ID_2", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ME_1", VtlType.NUMBER, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ME_2", VtlType.NUMBER, VtlComponentRole.MEASURE));
			datasetRepository.save(vtlDataset);
		}
		
		System.out.println("Il profilo attivo è " + environment.getActiveProfiles()[0]);
		String comandSqlResult = "";
		String profileActive = environment.getActiveProfiles()[0];
		if (profileActive.equalsIgnoreCase("oracleSql")) {
			comandSqlResult =   "CREATE TABLE DS_r AS (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,t0.ME_1 AS ME_1,t0.ME_2 AS ME_2,trunc(t0.ME_1) AS Me_10 FROM DS_10 t0);"+"CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);"+"CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);";
		} else if (profileActive.equalsIgnoreCase("sqlServer")) {
			comandSqlResult = "SELECT t1.* INTO DS_r FROM (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,t0.ME_1 AS ME_1,t0.ME_2 AS ME_2,round(t0.ME_1) AS Me_10 FROM DS_10 t0) t1;"+"CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);"+"CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);";
		} else if (profileActive.equalsIgnoreCase("mySql")) {
			comandSqlResult =   "CREATE TABLE DS_r AS (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,t0.ME_1 AS ME_1,t0.ME_2 AS ME_2,truncate(t0.ME_1) AS Me_10 FROM DS_10 t0);"+"CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);"+"CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);";
		} else if (profileActive.equalsIgnoreCase("postgreSql")) {
			comandSqlResult =   "CREATE TABLE DS_r AS (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,t0.ME_1 AS ME_1,t0.ME_2 AS ME_2,trunc(t0.ME_1) AS Me_10 FROM DS_10 t0);"+"CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);"+"CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);";
		}
		
		String commandStatements = "DS_r := DS_10[ calc Me_10:= trunc( Me_1 ) ];";
		String result = translationUtilTest.translate(commandStatements);
		System.out.println("Print queryTruncComponent: " +result);
		System.out.println("Print queryAttesaTruncComponent: " +comandSqlResult);
		assertEquals("Test operator TruncComponent", comandSqlResult, result);

	}

	@Test
	@Transactional
	public void truncComponentDue() throws Exception {
		dbTableUtilityService.resetSchema("t");
		VtlDataset vtlDatasetFind = datasetRepository.findByNameIgnoreCase("DS_1");
		if (vtlDatasetFind == null) {
			VtlDataset vtlDataset = new VtlDataset();
			vtlDataset.setPersistent(true);
			vtlDataset.setName("DS_10");
			vtlDataset.setIsOnlyAScalar(false);
			vtlDataset.setTransitory(false);
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ID_1", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ID_2", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ME_1", VtlType.NUMBER, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ME_2", VtlType.NUMBER, VtlComponentRole.MEASURE));
			datasetRepository.save(vtlDataset);
		}
		
		System.out.println("Il profilo attivo è " + environment.getActiveProfiles()[0]);
		String comandSqlResult = "";
		String profileActive = environment.getActiveProfiles()[0];
		if (profileActive.equalsIgnoreCase("oracleSql")) {
			comandSqlResult =   "CREATE TABLE DS_r AS (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,t0.ME_1 AS ME_1,t0.ME_2 AS ME_2,trunc(t0.ME_1,-1) AS Me_20 FROM DS_10 t0);"+"CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);"+"CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);";
		} else if (profileActive.equalsIgnoreCase("sqlServer")) {
			comandSqlResult = "SELECT t1.* INTO DS_r FROM (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,t0.ME_1 AS ME_1,t0.ME_2 AS ME_2,round(t0.ME_1,-1) AS Me_20 FROM DS_10 t0) t1;"+"CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);"+"CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);";
		} else if (profileActive.equalsIgnoreCase("mySql")) {
			comandSqlResult =   "CREATE TABLE DS_r AS (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,t0.ME_1 AS ME_1,t0.ME_2 AS ME_2,truncate(t0.ME_1,-1) AS Me_20 FROM DS_10 t0);"+"CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);"+"CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);";
		} else if (profileActive.equalsIgnoreCase("postgreSql")) {
			comandSqlResult =   "CREATE TABLE DS_r AS (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,t0.ME_1 AS ME_1,t0.ME_2 AS ME_2,trunc(t0.ME_1,-1) AS Me_20 FROM DS_10 t0);"+"CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);"+"CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);";
		}
		
		String commandStatements = "DS_r := DS_10[ calc Me_20:= trunc( Me_1 , -1 ) ];";
		String result = translationUtilTest.translate(commandStatements);
		System.out.println("Print queryTruncComponentDue: " +result);
		System.out.println("Print queryAttesaTruncComponentDue: " +comandSqlResult);
		assertEquals("Test operator TruncComponentDue", comandSqlResult, result);

	}

	@Test
	@Transactional
	public void ceil() throws Exception {
		dbTableUtilityService.resetSchema("t");
		VtlDataset vtlDatasetFind = datasetRepository.findByNameIgnoreCase("DS_1");
		if (vtlDatasetFind == null) {
			VtlDataset vtlDataset = new VtlDataset();

			vtlDataset.setPersistent(true);
			vtlDataset.setName("DS_10");
			vtlDataset.setIsOnlyAScalar(false);
			vtlDataset.setTransitory(false);
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ID_1", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ID_2", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ME_1", VtlType.NUMBER, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ME_2", VtlType.NUMBER, VtlComponentRole.MEASURE));
			datasetRepository.save(vtlDataset);
		}
		
		System.out.println("Il profilo attivo è " + environment.getActiveProfiles()[0]);
		String comandSqlResult = "";
		String profileActive = environment.getActiveProfiles()[0];
		if (profileActive.equalsIgnoreCase("oracleSql")) {
			comandSqlResult =   "CREATE TABLE DS_r AS (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,ceil(t0.ME_1) AS ME_1,ceil(t0.ME_2) AS ME_2 FROM DS_10 t0);"+"CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);"+"CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);";
		} else if (profileActive.equalsIgnoreCase("sqlServer")) {
			comandSqlResult = "SELECT t1.* INTO DS_r FROM (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,ceiling(t0.ME_1) AS ME_1,ceiling(t0.ME_2) AS ME_2 FROM DS_10 t0) t1;"+"CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);"+"CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);";
		} else if (profileActive.equalsIgnoreCase("mySql")) {
			comandSqlResult =   "CREATE TABLE DS_r AS (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,ceil(t0.ME_1) AS ME_1,ceil(t0.ME_2) AS ME_2 FROM DS_10 t0);"+"CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);"+"CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);";
		} else if (profileActive.equalsIgnoreCase("postgreSql")) {
			comandSqlResult =   "CREATE TABLE DS_r AS (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,ceil(t0.ME_1) AS ME_1,ceil(t0.ME_2) AS ME_2 FROM DS_10 t0);"+"CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);"+"CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);";
		}
		
		String commandStatements = "DS_r := ceil (DS_10);";
		String result = translationUtilTest.translate(commandStatements);
		System.out.println("Print queryCeil: " +result);
		System.out.println("Print queryAttesaCeil: " +comandSqlResult);
		assertEquals("Test operator Ceil", comandSqlResult, result);

	}

	@Test
	@Transactional
	public void ceilInt() throws Exception {
		dbTableUtilityService.resetSchema("t");
		System.out.println("Il profilo attivo è " + environment.getActiveProfiles()[0]);
		String comandSqlResult = "";
		String profileActive = environment.getActiveProfiles()[0];
		if (profileActive.equalsIgnoreCase("oracleSql")) {
			comandSqlResult =   "CREATE TABLE DS_r AS (SELECT ceil(15) AS int_var FROM DUAL);";
		} else if (profileActive.equalsIgnoreCase("sqlServer")) {
			comandSqlResult = "SELECT t0.* INTO DS_r FROM (SELECT ceiling(15) AS int_var) t0;";
		} else if (profileActive.equalsIgnoreCase("mySql")) {
			comandSqlResult =   "CREATE TABLE DS_r AS (SELECT ceil(15) AS int_var FROM DUAL);";
		} else if (profileActive.equalsIgnoreCase("postgreSql")) {
			comandSqlResult =   "CREATE TABLE DS_r AS (SELECT ceil(15) AS int_var);";
		}
		
		String commandStatements = "DS_r := ceil( 15 );";
		String result = translationUtilTest.translate(commandStatements);
		System.out.println("Print queryCeilInt: " +result);
		System.out.println("Print queryAttesaCeilInt: " +comandSqlResult);
		assertEquals("Test operator CeilInt", comandSqlResult, result);
	}

	@Test
	@Transactional
	public void ceilNumber() throws Exception {
		dbTableUtilityService.resetSchema("t");
		System.out.println("Il profilo attivo è " + environment.getActiveProfiles()[0]);
		String comandSqlResult = "";
		String profileActive = environment.getActiveProfiles()[0];
		if (profileActive.equalsIgnoreCase("oracleSql")) {
			comandSqlResult =   "CREATE TABLE DS_r AS (SELECT ceil(-(3.1415)) AS int_var FROM DUAL);";
		} else if (profileActive.equalsIgnoreCase("sqlServer")) {
			comandSqlResult = "SELECT t0.* INTO DS_r FROM (SELECT ceiling(-(3.1415)) AS int_var) t0;";
		} else if (profileActive.equalsIgnoreCase("mySql")) {
			comandSqlResult =   "CREATE TABLE DS_r AS (SELECT ceil(-(3.1415)) AS int_var FROM DUAL);";
		} else if (profileActive.equalsIgnoreCase("postgreSql")) {
			comandSqlResult =   "CREATE TABLE DS_r AS (SELECT ceil(-(3.1415)) AS int_var);";
		}
		
        String commandStatements = "DS_r := ceil( - 3.1415 );";
		String result = translationUtilTest.translate(commandStatements);
		System.out.println("Print queryCeilNumber: " +result);
		System.out.println("Print queryAttesaCeilNumber: " +comandSqlResult);
		assertEquals("Test operator CeilNumber", comandSqlResult, result);
	}

	@Test
	@Transactional
	public void ceilComponent() throws Exception {
		dbTableUtilityService.resetSchema("t");
		VtlDataset vtlDatasetFind = datasetRepository.findByNameIgnoreCase("DS_1");
		if (vtlDatasetFind == null) {
			VtlDataset vtlDataset = new VtlDataset();
			vtlDataset.setPersistent(true);
			vtlDataset.setName("DS_10");
			vtlDataset.setIsOnlyAScalar(false);
			vtlDataset.setTransitory(false);
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ID_1", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ID_2", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ME_1", VtlType.NUMBER, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ME_2", VtlType.NUMBER, VtlComponentRole.MEASURE));
			datasetRepository.save(vtlDataset);
		}
		
		System.out.println("Il profilo attivo è " + environment.getActiveProfiles()[0]);
		String comandSqlResult = "";
		String profileActive = environment.getActiveProfiles()[0];
		if (profileActive.equalsIgnoreCase("oracleSql")) {
			comandSqlResult =   "CREATE TABLE DS_r AS (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,t0.ME_1 AS ME_1,t0.ME_2 AS ME_2,ceil(t0.ME_1) AS Me_10 FROM DS_10 t0);"+"CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);"+"CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);";
		} else if (profileActive.equalsIgnoreCase("sqlServer")) {
			comandSqlResult = "SELECT t1.* INTO DS_r FROM (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,t0.ME_1 AS ME_1,t0.ME_2 AS ME_2,ceiling(t0.ME_1) AS Me_10 FROM DS_10 t0) t1;"+"CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);"+"CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);";
		} else if (profileActive.equalsIgnoreCase("mySql")) {
			comandSqlResult =   "CREATE TABLE DS_r AS (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,t0.ME_1 AS ME_1,t0.ME_2 AS ME_2,ceil(t0.ME_1) AS Me_10 FROM DS_10 t0);"+"CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);"+"CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);";
		} else if (profileActive.equalsIgnoreCase("postgreSql")) {
			comandSqlResult =   "CREATE TABLE DS_r AS (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,t0.ME_1 AS ME_1,t0.ME_2 AS ME_2,ceil(t0.ME_1) AS Me_10 FROM DS_10 t0);"+"CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);"+"CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);";
		}
		
		String commandStatements = "DS_r := DS_10 [ calc Me_10 := ceil (Me_1) ];";
		String result = translationUtilTest.translate(commandStatements);
		System.out.println("Print queryCeilComponent: " +result);
		System.out.println("Print queryAttesaCeilComponent: " +comandSqlResult);
		assertEquals("Test operator CeilComponent", comandSqlResult, result);
	}

	@Test
	@Transactional
	public void floor() throws Exception {
		dbTableUtilityService.resetSchema("t");
		VtlDataset vtlDatasetFind = datasetRepository.findByNameIgnoreCase("DS_1");
		if (vtlDatasetFind == null) {
			VtlDataset vtlDataset = new VtlDataset();
			vtlDataset.setPersistent(true);
			vtlDataset.setName("DS_10");
			vtlDataset.setIsOnlyAScalar(false);
			vtlDataset.setTransitory(false);
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ID_1", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ID_2", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ME_1", VtlType.NUMBER, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ME_2", VtlType.NUMBER, VtlComponentRole.MEASURE));
			datasetRepository.save(vtlDataset);
		}
		
		System.out.println("Il profilo attivo è " + environment.getActiveProfiles()[0]);
		String comandSqlResult = "";
		String profileActive = environment.getActiveProfiles()[0];
		if (profileActive.equalsIgnoreCase("oracleSql")) {
			comandSqlResult =   "CREATE TABLE DS_r AS (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,floor(t0.ME_1) AS ME_1,floor(t0.ME_2) AS ME_2 FROM DS_10 t0);"+"CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);"+"CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);";
		} else if (profileActive.equalsIgnoreCase("sqlServer")) {
			comandSqlResult = "SELECT t1.* INTO DS_r FROM (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,floor(t0.ME_1) AS ME_1,floor(t0.ME_2) AS ME_2 FROM DS_10 t0) t1;"+"CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);"+"CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);";
		} else if (profileActive.equalsIgnoreCase("mySql")) {
			comandSqlResult =   "CREATE TABLE DS_r AS (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,floor(t0.ME_1) AS ME_1,floor(t0.ME_2) AS ME_2 FROM DS_10 t0);"+"CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);"+"CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);";
		} else if (profileActive.equalsIgnoreCase("postgreSql")) {
			comandSqlResult =   "CREATE TABLE DS_r AS (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,floor(t0.ME_1) AS ME_1,floor(t0.ME_2) AS ME_2 FROM DS_10 t0);"+"CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);"+"CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);";
		}
		
		String commandStatements = "DS_r := floor ( DS_10 );";
		String result = translationUtilTest.translate(commandStatements);
		System.out.println("Print queryFloor: " +result);
		System.out.println("Print queryAttesaFloor: " +comandSqlResult);
		assertEquals("Test operator Floor", comandSqlResult, result);

	}

	@Test
	@Transactional
	public void floorNumber() throws Exception {
		dbTableUtilityService.resetSchema("t");
		System.out.println("Il profilo attivo è " + environment.getActiveProfiles()[0]);
		String comandSqlResult = "";
		String profileActive = environment.getActiveProfiles()[0];
		if (profileActive.equalsIgnoreCase("oracleSql")) {
			comandSqlResult =   "CREATE TABLE DS_r AS (SELECT floor(3.14) AS int_var FROM DUAL);";
		} else if (profileActive.equalsIgnoreCase("sqlServer")) {
			comandSqlResult = "SELECT t0.* INTO DS_r FROM (SELECT floor(3.14) AS int_var) t0;";
		} else if (profileActive.equalsIgnoreCase("mySql")) {
			comandSqlResult =   "CREATE TABLE DS_r AS (SELECT floor(3.14) AS int_var FROM DUAL);";
		} else if (profileActive.equalsIgnoreCase("postgreSql")) {
			comandSqlResult =   "CREATE TABLE DS_r AS (SELECT floor(3.14) AS int_var);";
		}
		
		String commandStatements = "DS_r := floor ( 3.14 );";
		String result = translationUtilTest.translate(commandStatements);
		System.out.println("Print queryFloorNumber: " +result);
		System.out.println("Print queryAttesaFloorNumber: " +comandSqlResult);
		assertEquals("Test operator FloorNumber", comandSqlResult, result);
	}
	
	@Test
	@Transactional
	public void floorComponent() throws Exception {
		dbTableUtilityService.resetSchema("t");
		VtlDataset vtlDatasetFind = datasetRepository.findByNameIgnoreCase("DS_1");
		if (vtlDatasetFind == null) {
			VtlDataset vtlDataset = new VtlDataset();

			vtlDataset.setPersistent(true);
			vtlDataset.setName("DS_10");
			vtlDataset.setIsOnlyAScalar(false);
			vtlDataset.setTransitory(false);
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ID_1", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ID_2", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ME_1", VtlType.NUMBER, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ME_2", VtlType.NUMBER, VtlComponentRole.MEASURE));
			datasetRepository.save(vtlDataset);
		}
		
		System.out.println("Il profilo attivo è " + environment.getActiveProfiles()[0]);
		String comandSqlResult = "";
		String profileActive = environment.getActiveProfiles()[0];
		if (profileActive.equalsIgnoreCase("oracleSql")) {
			comandSqlResult =   "CREATE TABLE DS_r AS (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,t0.ME_1 AS ME_1,t0.ME_2 AS ME_2,floor(t0.ME_1) AS Me_10 FROM DS_10 t0);"+"CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);"+"CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);";
		} else if (profileActive.equalsIgnoreCase("sqlServer")) {
			comandSqlResult = "SELECT t1.* INTO DS_r FROM (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,t0.ME_1 AS ME_1,t0.ME_2 AS ME_2,floor(t0.ME_1) AS Me_10 FROM DS_10 t0) t1;"+"CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);"+"CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);";
		} else if (profileActive.equalsIgnoreCase("mySql")) {
			comandSqlResult =   "CREATE TABLE DS_r AS (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,t0.ME_1 AS ME_1,t0.ME_2 AS ME_2,floor(t0.ME_1) AS Me_10 FROM DS_10 t0);"+"CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);"+"CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);";
		} else if (profileActive.equalsIgnoreCase("postgreSql")) {
			comandSqlResult =   "CREATE TABLE DS_r AS (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,t0.ME_1 AS ME_1,t0.ME_2 AS ME_2,floor(t0.ME_1) AS Me_10 FROM DS_10 t0);"+"CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);"+"CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);";
		}
		
		String commandStatements = "DS_r := DS_10 [ calc Me_10 :=  floor (Me_1) ];";
		String result = translationUtilTest.translate(commandStatements);
		System.out.println("Print queryFloorComponent: " +result);
		System.out.println("Print queryAttesaFloorComponent: " +comandSqlResult);
		assertEquals("Test operator FloorComponent", comandSqlResult, result);

	}

	@Test
	@Transactional
	public void abs() throws Exception {
		dbTableUtilityService.resetSchema("t");
		VtlDataset vtlDatasetFind = datasetRepository.findByNameIgnoreCase("DS_1");
		if (vtlDatasetFind == null) {
			VtlDataset vtlDataset = new VtlDataset();

			vtlDataset.setPersistent(true);
			vtlDataset.setName("DS_10");
			vtlDataset.setIsOnlyAScalar(false);
			vtlDataset.setTransitory(false);
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ID_1", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ID_2", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ME_1", VtlType.NUMBER, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ME_2", VtlType.NUMBER, VtlComponentRole.MEASURE));
			datasetRepository.save(vtlDataset);
		}
		
		System.out.println("Il profilo attivo è " + environment.getActiveProfiles()[0]);
		String comandSqlResult = "";
		String profileActive = environment.getActiveProfiles()[0];
		if (profileActive.equalsIgnoreCase("oracleSql")) {
			comandSqlResult =   "CREATE TABLE DS_r AS (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,abs(t0.ME_1) AS ME_1,abs(t0.ME_2) AS ME_2 FROM DS_10 t0);"+"CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);"+"CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);";
		} else if (profileActive.equalsIgnoreCase("sqlServer")) {
			comandSqlResult = "SELECT t1.* INTO DS_r FROM (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,abs(t0.ME_1) AS ME_1,abs(t0.ME_2) AS ME_2 FROM DS_10 t0) t1;"+"CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);"+"CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);";
		} else if (profileActive.equalsIgnoreCase("mySql")) {
			comandSqlResult =   "CREATE TABLE DS_r AS (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,abs(t0.ME_1) AS ME_1,abs(t0.ME_2) AS ME_2 FROM DS_10 t0);"+"CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);"+"CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);";
		} else if (profileActive.equalsIgnoreCase("postgreSql")) {
			comandSqlResult =   "CREATE TABLE DS_r AS (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,abs(t0.ME_1) AS ME_1,abs(t0.ME_2) AS ME_2 FROM DS_10 t0);"+"CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);"+"CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);";
		}
		
		String commandStatements = "DS_r := abs ( DS_10 );";
		String result = translationUtilTest.translate(commandStatements);
		System.out.println("Print queryAbs: " +result);
		System.out.println("Print queryAttesaAbs: " +comandSqlResult);
		assertEquals("Test operator Abs", comandSqlResult, result);

	}

	@Test
	@Transactional
	public void absNumber() throws Exception {
		dbTableUtilityService.resetSchema("t");
		System.out.println("Il profilo attivo è " + environment.getActiveProfiles()[0]);
		String comandSqlResult = "";
		String profileActive = environment.getActiveProfiles()[0];
		if (profileActive.equalsIgnoreCase("oracleSql")) {
			comandSqlResult =   "CREATE TABLE DS_r AS (SELECT abs(5.49) AS num_var FROM DUAL);";
		} else if (profileActive.equalsIgnoreCase("sqlServer")) {
			comandSqlResult = "SELECT t0.* INTO DS_r FROM (SELECT abs(5.49) AS num_var) t0;";
		} else if (profileActive.equalsIgnoreCase("mySql")) {
			comandSqlResult =   "CREATE TABLE DS_r AS (SELECT abs(5.49) AS num_var FROM DUAL);";
		} else if (profileActive.equalsIgnoreCase("postgreSql")) {
			comandSqlResult =   "CREATE TABLE DS_r AS (SELECT abs(5.49) AS num_var);";
		}
		String commandStatements = "DS_r := abs ( 5.49 );";
		String result = translationUtilTest.translate(commandStatements);
		System.out.println("Print queryAbsNumber: " +result);
		System.out.println("Print queryAttesaAbsNumber: " +comandSqlResult);
		assertEquals("Test operator AbsNumber", comandSqlResult, result);
	}

	@Test
	@Transactional
	public void absComponent() throws Exception {
		dbTableUtilityService.resetSchema("t");
		VtlDataset vtlDatasetFind = datasetRepository.findByNameIgnoreCase("DS_1");
		if (vtlDatasetFind == null) {
			VtlDataset vtlDataset = new VtlDataset();

			vtlDataset.setPersistent(true);
			vtlDataset.setName("DS_10");
			vtlDataset.setIsOnlyAScalar(false);
			vtlDataset.setTransitory(false);
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ID_1", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ID_2", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ME_1", VtlType.NUMBER, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ME_2", VtlType.NUMBER, VtlComponentRole.MEASURE));
			datasetRepository.save(vtlDataset);
		}
		
		System.out.println("Il profilo attivo è " + environment.getActiveProfiles()[0]);
		String comandSqlResult = "";
		String profileActive = environment.getActiveProfiles()[0];
		if (profileActive.equalsIgnoreCase("oracleSql")) {
			comandSqlResult =   "CREATE TABLE DS_r AS (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,t0.ME_1 AS ME_1,t0.ME_2 AS ME_2,abs(t0.ME_1) AS Me_10 FROM DS_10 t0);"+"CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);"+"CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);";
		} else if (profileActive.equalsIgnoreCase("sqlServer")) {
			comandSqlResult = "SELECT t1.* INTO DS_r FROM (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,t0.ME_1 AS ME_1,t0.ME_2 AS ME_2,abs(t0.ME_1) AS Me_10 FROM DS_10 t0) t1;"+"CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);"+"CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);";
		} else if (profileActive.equalsIgnoreCase("mySql")) {
			comandSqlResult =   "CREATE TABLE DS_r AS (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,t0.ME_1 AS ME_1,t0.ME_2 AS ME_2,abs(t0.ME_1) AS Me_10 FROM DS_10 t0);"+"CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);"+"CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);";
		} else if (profileActive.equalsIgnoreCase("postgreSql")) {
			comandSqlResult =   "CREATE TABLE DS_r AS (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,t0.ME_1 AS ME_1,t0.ME_2 AS ME_2,abs(t0.ME_1) AS Me_10 FROM DS_10 t0);"+"CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);"+"CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);";
		}
		
		String commandStatements = "DS_r := DS_10 [ calc Me_10 := abs(Me_1) ];";
		String result = translationUtilTest.translate(commandStatements);
		System.out.println("Print queryAbsComponent: " +result);
		System.out.println("Print queryAttesaAbsComponent: " +comandSqlResult);
		assertEquals("Test operator AbsComponent", comandSqlResult, result);

	}

	@Test
	@Transactional
	public void exp() throws Exception {
		dbTableUtilityService.resetSchema("t");
		VtlDataset vtlDatasetFind = datasetRepository.findByNameIgnoreCase("DS_1");
		if (vtlDatasetFind == null) {
			VtlDataset vtlDataset = new VtlDataset();

			vtlDataset.setPersistent(true);
			vtlDataset.setName("DS_8");
			vtlDataset.setIsOnlyAScalar(false);
			vtlDataset.setTransitory(false);
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ID_1", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ID_2", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ME_1", VtlType.INTEGER, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ME_2", VtlType.NUMBER, VtlComponentRole.MEASURE));
			datasetRepository.save(vtlDataset);

		}
		
		System.out.println("Il profilo attivo è " + environment.getActiveProfiles()[0]);
		String comandSqlResult = "";
		String profileActive = environment.getActiveProfiles()[0];
		if (profileActive.equalsIgnoreCase("oracleSql")) {
			comandSqlResult = "CREATE TABLE DS_r AS (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,exp(t0.ME_1) AS ME_1,exp(t0.ME_2) AS ME_2 FROM DS_8 t0);"+"CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);"+"CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);";
		} else if (profileActive.equalsIgnoreCase("sqlServer")) {
			comandSqlResult = "SELECT t1.* INTO DS_r FROM (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,exp(t0.ME_1) AS ME_1,exp(t0.ME_2) AS ME_2 FROM DS_8 t0) t1;"+"CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);"+"CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);";
		} else if (profileActive.equalsIgnoreCase("mySql")) {
			comandSqlResult = "CREATE TABLE DS_r AS (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,exp(t0.ME_1) AS ME_1,exp(t0.ME_2) AS ME_2 FROM DS_8 t0);"+"CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);"+"CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);";
		} else if (profileActive.equalsIgnoreCase("postgreSql")) {
			comandSqlResult = "CREATE TABLE DS_r AS (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,exp(t0.ME_1) AS ME_1,exp(t0.ME_2) AS ME_2 FROM DS_8 t0);"+"CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);"+"CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);";
		}
		
		String commandStatements = "DS_r := exp(DS_8);";
		String result = translationUtilTest.translate(commandStatements);
		System.out.println("Print queryExp: " +result);
		System.out.println("Print queryAttesaExp: " +comandSqlResult);
		assertEquals("Test operator Exp", comandSqlResult, result);

	}

	@Test
	@Transactional
	public void expInt() throws Exception {
		dbTableUtilityService.resetSchema("t");
		System.out.println("Il profilo attivo è " + environment.getActiveProfiles()[0]);
		String comandSqlResult = "";
		String profileActive = environment.getActiveProfiles()[0];
		if (profileActive.equalsIgnoreCase("oracleSql")) {
			comandSqlResult =   "CREATE TABLE DS_r AS (SELECT exp(0) AS num_var FROM DUAL);";
		} else if (profileActive.equalsIgnoreCase("sqlServer")) {
			comandSqlResult = "SELECT t0.* INTO DS_r FROM (SELECT exp(0) AS num_var) t0;";
		} else if (profileActive.equalsIgnoreCase("mySql")) {
			comandSqlResult =   "CREATE TABLE DS_r AS (SELECT exp(0) AS num_var FROM DUAL);";
		} else if (profileActive.equalsIgnoreCase("postgreSql")) {
			comandSqlResult =  "CREATE TABLE DS_r AS (SELECT exp(0) AS num_var);";
		}
		String commandStatements = "DS_r := exp ( 0 );";
		String result = translationUtilTest.translate(commandStatements);
		System.out.println("Print queryExpInt: " +result);
		System.out.println("Print queryAttesaExpInt: " +comandSqlResult);
		assertEquals("Test operator ExpInt", comandSqlResult, result);
	}

	@Test
	@Transactional
	public void expIntNegativo() throws Exception {
		dbTableUtilityService.resetSchema("t");
		System.out.println("Il profilo attivo è " + environment.getActiveProfiles()[0]);
		String comandSqlResult = "";
		String profileActive = environment.getActiveProfiles()[0];
		if (profileActive.equalsIgnoreCase("oracleSql")) {
			comandSqlResult = "CREATE TABLE DS_r AS (SELECT exp(-(2)) AS num_var FROM DUAL);";
		} else if (profileActive.equalsIgnoreCase("sqlServer")) {
			comandSqlResult = "SELECT t0.* INTO DS_r FROM (SELECT exp(-(2)) AS num_var) t0;";
		} else if (profileActive.equalsIgnoreCase("mySql")) {
			comandSqlResult = "CREATE TABLE DS_r AS (SELECT exp(-(2)) AS num_var FROM DUAL);";
		} else if (profileActive.equalsIgnoreCase("postgreSql")) {
			comandSqlResult = "CREATE TABLE DS_r AS (SELECT exp(-(2)) AS num_var);";
		}
		String commandStatements = "DS_r := exp ( - 2 );";
		String result = translationUtilTest.translate(commandStatements);
		System.out.println("Print queryExpIntNegativo: " +result);
		System.out.println("Print queryAttesaExpIntNegativo: " +comandSqlResult);
		assertEquals("Test operator ExpIntNegativo", comandSqlResult, result);
	}

	@Test
	@Transactional
	public void expComponent() throws Exception {
		dbTableUtilityService.resetSchema("t");
		VtlDataset vtlDatasetFind = datasetRepository.findByNameIgnoreCase("DS_1");
		if (vtlDatasetFind == null) {
			VtlDataset vtlDataset = new VtlDataset();

			vtlDataset.setPersistent(true);
			vtlDataset.setName("DS_8");
			vtlDataset.setIsOnlyAScalar(false);
			vtlDataset.setTransitory(false);
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ID_1", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ID_2", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ME_1", VtlType.INTEGER, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ME_2", VtlType.NUMBER, VtlComponentRole.MEASURE));
			datasetRepository.save(vtlDataset);

		}
		
		System.out.println("Il profilo attivo è " + environment.getActiveProfiles()[0]);
		String comandSqlResult = "";
		String profileActive = environment.getActiveProfiles()[0];
		if (profileActive.equalsIgnoreCase("oracleSql")) {
			comandSqlResult = "CREATE TABLE DS_r AS (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,exp(t0.ME_1) AS Me_1,t0.ME_2 AS ME_2 FROM DS_8 t0);"+"CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);"+"CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);";
		} else if (profileActive.equalsIgnoreCase("sqlServer")) {
			comandSqlResult = "SELECT t1.* INTO DS_r FROM (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,exp(t0.ME_1) AS Me_1,t0.ME_2 AS ME_2 FROM DS_8 t0) t1;"+"CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);"+"CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);";
		} else if (profileActive.equalsIgnoreCase("mySql")) {
			comandSqlResult = "CREATE TABLE DS_r AS (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,exp(t0.ME_1) AS Me_1,t0.ME_2 AS ME_2 FROM DS_8 t0);"+"CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);"+"CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);";
		} else if (profileActive.equalsIgnoreCase("postgreSql")) {
			comandSqlResult = "CREATE TABLE DS_r AS (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,exp(t0.ME_1) AS Me_1,t0.ME_2 AS ME_2 FROM DS_8 t0);"+"CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);"+"CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);";
		}
		
		String commandStatements = "DS_r := DS_8 [ calc Me_1 :=  exp ( Me_1 ) ];";
		String result = translationUtilTest.translate(commandStatements);
		System.out.println("Print queryExpComponent: " +result);
		System.out.println("Print queryAttesaExpComponent: " +comandSqlResult);
		assertEquals("Test operator ExpIntComponent", comandSqlResult, result);

	}

	@Test
	@Transactional
	public void ln() throws Exception {
		dbTableUtilityService.resetSchema("t");
		VtlDataset vtlDatasetFind = datasetRepository.findByNameIgnoreCase("DS_1");
		if (vtlDatasetFind == null) {
			VtlDataset vtlDataset = new VtlDataset();

			vtlDataset.setPersistent(true);
			vtlDataset.setName("DS_10");
			vtlDataset.setIsOnlyAScalar(false);
			vtlDataset.setTransitory(false);
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ID_1", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ID_2", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ME_1", VtlType.NUMBER, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ME_2", VtlType.NUMBER, VtlComponentRole.MEASURE));
			datasetRepository.save(vtlDataset);
		}
		
		System.out.println("Il profilo attivo è " + environment.getActiveProfiles()[0]);
		String comandSqlResult = "";
		String profileActive = environment.getActiveProfiles()[0];
		if (profileActive.equalsIgnoreCase("oracleSql")) {
			comandSqlResult = "CREATE TABLE DS_r AS (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,ln(t0.ME_1) AS ME_1,ln(t0.ME_2) AS ME_2 FROM DS_10 t0);"+"CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);"+"CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);";
		} else if (profileActive.equalsIgnoreCase("sqlServer")) {
			comandSqlResult = "SELECT t1.* INTO DS_r FROM (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,log(t0.ME_1) AS ME_1,log(t0.ME_2) AS ME_2 FROM DS_10 t0) t1;"+"CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);"+"CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);";
		} else if (profileActive.equalsIgnoreCase("mySql")) {
			comandSqlResult = "CREATE TABLE DS_r AS (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,ln(t0.ME_1) AS ME_1,ln(t0.ME_2) AS ME_2 FROM DS_10 t0);"+"CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);"+"CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);";
		} else if (profileActive.equalsIgnoreCase("postgreSql")) {
			comandSqlResult = "CREATE TABLE DS_r AS (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,ln(t0.ME_1) AS ME_1,ln(t0.ME_2) AS ME_2 FROM DS_10 t0);"+"CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);"+"CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);";
		}
		
		String commandStatements = "DS_r := ln(DS_10);";
		String result = translationUtilTest.translate(commandStatements);
		System.out.println("Print queryLn: " +result);
		System.out.println("Print queryAttesaLn: " +comandSqlResult);
		assertEquals("Test operator Ln", comandSqlResult, result);

	}

	@Test
	@Transactional
	public void lnInt() throws Exception {
		dbTableUtilityService.resetSchema("t");
		System.out.println("Il profilo attivo è " + environment.getActiveProfiles()[0]);
		String comandSqlResult = "";
		String profileActive = environment.getActiveProfiles()[0];
		if (profileActive.equalsIgnoreCase("oracleSql")) {
			comandSqlResult = "CREATE TABLE DS_r AS (SELECT ln(8) AS num_var FROM DUAL);";
		} else if (profileActive.equalsIgnoreCase("sqlServer")) {
			comandSqlResult = "SELECT t0.* INTO DS_r FROM (SELECT log(8) AS num_var) t0;";
		} else if (profileActive.equalsIgnoreCase("mySql")) {
			comandSqlResult = "CREATE TABLE DS_r AS (SELECT ln(8) AS num_var FROM DUAL);";
		} else if (profileActive.equalsIgnoreCase("postgreSql")) {
			comandSqlResult = "CREATE TABLE DS_r AS (SELECT ln(8) AS num_var);";
		}
		String commandStatements = "DS_r := ln ( 8 );";
		String result = translationUtilTest.translate(commandStatements);
		System.out.println("Print queryLnInt: " +result);
		System.out.println("Print queryAttesaLnInt: " +comandSqlResult);
		assertEquals("Test operator LnInt", comandSqlResult, result);
	}

	@Test
	@Transactional
	public void lnComponent() throws Exception {
		dbTableUtilityService.resetSchema("t");
		VtlDataset vtlDatasetFind = datasetRepository.findByNameIgnoreCase("DS_1");
		if (vtlDatasetFind == null) {
			VtlDataset vtlDataset = new VtlDataset();

			vtlDataset.setPersistent(true);
			vtlDataset.setName("DS_10");
			vtlDataset.setIsOnlyAScalar(false);
			vtlDataset.setTransitory(false);
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ID_1", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ID_2", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ME_1", VtlType.NUMBER, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ME_2", VtlType.NUMBER, VtlComponentRole.MEASURE));
			datasetRepository.save(vtlDataset);
		}
		
		System.out.println("Il profilo attivo è " + environment.getActiveProfiles()[0]);
		String comandSqlResult = "";
		String profileActive = environment.getActiveProfiles()[0];
		if (profileActive.equalsIgnoreCase("oracleSql")) {
			comandSqlResult = "CREATE TABLE DS_r AS (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,t0.ME_1 AS ME_1,ln(t0.ME_1) AS Me_2 FROM DS_10 t0);CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);";
		} else if (profileActive.equalsIgnoreCase("sqlServer")) {
			comandSqlResult = "SELECT t1.* INTO DS_r FROM (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,t0.ME_1 AS ME_1,log(t0.ME_1) AS Me_2 FROM DS_10 t0) t1;"+"CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);"+"CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);";
		} else if (profileActive.equalsIgnoreCase("mySql")) {
			comandSqlResult = "CREATE TABLE DS_r AS (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,t0.ME_1 AS ME_1,ln(t0.ME_1) AS Me_2 FROM DS_10 t0);CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);";
		} else if (profileActive.equalsIgnoreCase("postgreSql")) {
			comandSqlResult = "CREATE TABLE DS_r AS (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,t0.ME_1 AS ME_1,ln(t0.ME_1) AS Me_2 FROM DS_10 t0);CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);";
		}

		String commandStatements = "DS_r := DS_10 [ calc Me_2 := ln ( Me_1 ) ];";
		String result = translationUtilTest.translate(commandStatements);
		System.out.println("Print queryLnComponent: " +result);
		System.out.println("Print queryAttesaLnComponent: " +comandSqlResult);
		assertEquals("Test operator LnComponent", comandSqlResult, result);
	}

	@Test
	@Transactional
	public void power() throws Exception {
		dbTableUtilityService.resetSchema("t");
		VtlDataset vtlDatasetFind = datasetRepository.findByNameIgnoreCase("DS_1");
		if (vtlDatasetFind == null) {
			VtlDataset vtlDataset = new VtlDataset();

			vtlDataset.setPersistent(true);
			vtlDataset.setName("DS_8");
			vtlDataset.setIsOnlyAScalar(false);
			vtlDataset.setTransitory(false);
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ID_1", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ID_2", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ME_1", VtlType.INTEGER, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ME_2", VtlType.NUMBER, VtlComponentRole.MEASURE));
			datasetRepository.save(vtlDataset);
		}
		
		System.out.println("Il profilo attivo è " + environment.getActiveProfiles()[0]);
		String comandSqlResult = "";
		String profileActive = environment.getActiveProfiles()[0];
		if (profileActive.equalsIgnoreCase("oracleSql")) {
			comandSqlResult = "CREATE TABLE DS_r AS (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,power(t0.ME_1,2) AS ME_1,power(t0.ME_2,2) AS ME_2 FROM DS_8 t0);"+"CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);"+"CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);";
		} else if (profileActive.equalsIgnoreCase("sqlServer")) {
			comandSqlResult = "SELECT t1.* INTO DS_r FROM (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,power(t0.ME_1,2) AS ME_1,power(t0.ME_2,2) AS ME_2 FROM DS_8 t0) t1;"+"CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);"+"CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);";
		} else if (profileActive.equalsIgnoreCase("mySql")) {
			comandSqlResult = "CREATE TABLE DS_r AS (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,power(t0.ME_1,2) AS ME_1,power(t0.ME_2,2) AS ME_2 FROM DS_8 t0);"+"CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);"+"CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);";
		} else if (profileActive.equalsIgnoreCase("postgreSql")) {
			comandSqlResult = "CREATE TABLE DS_r AS (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,power(t0.ME_1,2) AS ME_1,power(t0.ME_2,2) AS ME_2 FROM DS_8 t0);"+"CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);"+"CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);";
		}
		
		String commandStatements = "DS_r := power(DS_8, 2);";
		String result = translationUtilTest.translate(commandStatements);
		System.out.println("Print queryPower: " +result);
		System.out.println("Print queryAttesaPower: " +comandSqlResult);
		assertEquals("Test operator Power", comandSqlResult, result);

	}

	@Test
	@Transactional
	public void powerInt() throws Exception {
		dbTableUtilityService.resetSchema("t");
		System.out.println("Il profilo attivo è " + environment.getActiveProfiles()[0]);
		String comandSqlResult = "";
		String profileActive = environment.getActiveProfiles()[0];
		if (profileActive.equalsIgnoreCase("oracleSql")) {
			comandSqlResult = "CREATE TABLE DS_r AS (SELECT power(5,0) AS int_var FROM DUAL);";
		} else if (profileActive.equalsIgnoreCase("sqlServer")) {
			comandSqlResult = "SELECT t0.* INTO DS_r FROM (SELECT power(5,0) AS int_var) t0;";
		} else if (profileActive.equalsIgnoreCase("mySql")) {
			comandSqlResult = "CREATE TABLE DS_r AS (SELECT power(5,0) AS int_var FROM DUAL);";
		} else if (profileActive.equalsIgnoreCase("postgreSql")) {
			comandSqlResult = "CREATE TABLE DS_r AS (SELECT power(5,0) AS int_var);";
		}
		String commandStatements = "DS_r := power ( 5, 0 );";
		String result = translationUtilTest.translate(commandStatements);
		System.out.println("Print queryPowerInt: " +result);
		System.out.println("Print queryAttesaPowerInt: " +comandSqlResult);
		assertEquals("Test operator PowerInt", comandSqlResult, result);
	}

	@Test
	@Transactional
	public void powerComponent() throws Exception {
		dbTableUtilityService.resetSchema("t");
		VtlDataset vtlDatasetFind = datasetRepository.findByNameIgnoreCase("DS_1");
		if (vtlDatasetFind == null) {
			VtlDataset vtlDataset = new VtlDataset();

			vtlDataset.setPersistent(true);
			vtlDataset.setName("DS_8");
			vtlDataset.setIsOnlyAScalar(false);
			vtlDataset.setTransitory(false);
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ID_1", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ID_2", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ME_1", VtlType.INTEGER, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ME_2", VtlType.NUMBER, VtlComponentRole.MEASURE));
			datasetRepository.save(vtlDataset);
		}
		
		System.out.println("Il profilo attivo è " + environment.getActiveProfiles()[0]);
		String comandSqlResult = "";
		String profileActive = environment.getActiveProfiles()[0];
		if (profileActive.equalsIgnoreCase("oracleSql")) {
			comandSqlResult = "CREATE TABLE DS_r AS (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,power(t0.ME_1,2) AS Me_1,t0.ME_2 AS ME_2,power(t0.ME_2,t0.ME_1) AS Me_3 FROM DS_8 t0);"+"CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);"+"CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);";
		} else if (profileActive.equalsIgnoreCase("sqlServer")) {
			comandSqlResult = "SELECT t1.* INTO DS_r FROM (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,power(t0.ME_1,2) AS Me_1,t0.ME_2 AS ME_2,power(t0.ME_2,t0.ME_1) AS Me_3 FROM DS_8 t0) t1;"+"CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);"+"CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);";
		} else if (profileActive.equalsIgnoreCase("mySql")) {
			comandSqlResult = "CREATE TABLE DS_r AS (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,power(t0.ME_1,2) AS Me_1,t0.ME_2 AS ME_2,power(t0.ME_2,t0.ME_1) AS Me_3 FROM DS_8 t0);"+"CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);"+"CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);";
		} else if (profileActive.equalsIgnoreCase("postgreSql")) {
			comandSqlResult = "CREATE TABLE DS_r AS (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,power(t0.ME_1,2) AS Me_1,t0.ME_2 AS ME_2,power(t0.ME_2,t0.ME_1) AS Me_3 FROM DS_8 t0);"+"CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);"+"CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);";
		}
		
		String commandStatements = "DS_r := DS_8[ calc Me_1 := power(Me_1, 2), Me_3 := power(Me_2, Me_1) ];";
		String result = translationUtilTest.translate(commandStatements);
		System.out.println("Print queryPowerComponent: " +result);
		System.out.println("Print queryAttesaPowerComponent: " +comandSqlResult);
		assertEquals("Test operator PowerComponent", comandSqlResult, result);

	}

	@Test
	@Transactional
	public void powerComponentMe() throws Exception {
		dbTableUtilityService.resetSchema("t");
		VtlDataset vtlDatasetFind = datasetRepository.findByNameIgnoreCase("DS_1");
		if (vtlDatasetFind == null) {
			VtlDataset vtlDataset = new VtlDataset();

			vtlDataset.setPersistent(true);
			vtlDataset.setName("DS_8");
			vtlDataset.setIsOnlyAScalar(false);
			vtlDataset.setTransitory(false);
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ID_1", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ID_2", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ME_1", VtlType.INTEGER, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ME_2", VtlType.NUMBER, VtlComponentRole.MEASURE));
			datasetRepository.save(vtlDataset);
		}
		
		System.out.println("Il profilo attivo è " + environment.getActiveProfiles()[0]);
		String comandSqlResult = "";
		String profileActive = environment.getActiveProfiles()[0];
		if (profileActive.equalsIgnoreCase("oracleSql")) {
			comandSqlResult = "CREATE TABLE DS_r AS (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,power(t0.ME_1,2) AS Me_1,t0.ME_2 AS ME_2 FROM DS_8 t0);"+"CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);"+"CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);";
		} else if (profileActive.equalsIgnoreCase("sqlServer")) {
			comandSqlResult = "SELECT t1.* INTO DS_r FROM (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,power(t0.ME_1,2) AS Me_1,t0.ME_2 AS ME_2 FROM DS_8 t0) t1;"+"CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);"+"CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);";
		} else if (profileActive.equalsIgnoreCase("mySql")) {
			comandSqlResult = "CREATE TABLE DS_r AS (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,power(t0.ME_1,2) AS Me_1,t0.ME_2 AS ME_2 FROM DS_8 t0);"+"CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);"+"CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);";
		} else if (profileActive.equalsIgnoreCase("postgreSql")) {
			comandSqlResult = "CREATE TABLE DS_r AS (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,power(t0.ME_1,2) AS Me_1,t0.ME_2 AS ME_2 FROM DS_8 t0);"+"CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);"+"CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);";
		}
		
		String commandStatements = "DS_r := DS_8[ calc Me_1 := power(Me_1, 2) ];";
		String result = translationUtilTest.translate(commandStatements);
		System.out.println("Print queryPowerComponentMe: " +result);
		System.out.println("Print queryAttesaPowerComponentMe: " +comandSqlResult);
		assertEquals("Test operator PowerComponentMe", comandSqlResult, result);


	}

	@Test
	@Transactional
	public void log() throws Exception {
		dbTableUtilityService.resetSchema("t");
		VtlDataset vtlDatasetFind = datasetRepository.findByNameIgnoreCase("DS_1");
		if (vtlDatasetFind == null) {
			VtlDataset vtlDataset = new VtlDataset();

			vtlDataset.setPersistent(true);
			vtlDataset.setName("DS_8");
			vtlDataset.setIsOnlyAScalar(false);
			vtlDataset.setTransitory(false);
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ID_1", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ID_2", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ME_1", VtlType.INTEGER, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ME_2", VtlType.NUMBER, VtlComponentRole.MEASURE));
			datasetRepository.save(vtlDataset);
		}
		
		System.out.println("Il profilo attivo è " + environment.getActiveProfiles()[0]);
		String comandSqlResult = "";
		String profileActive = environment.getActiveProfiles()[0];
		if (profileActive.equalsIgnoreCase("oracleSql")) {
			comandSqlResult = "CREATE TABLE DS_r AS (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,log(t0.ME_1,2) AS ME_1,log(t0.ME_2,2) AS ME_2 FROM DS_8 t0);"+"CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);"+"CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);";
		} else if (profileActive.equalsIgnoreCase("sqlServer")) {
			comandSqlResult = "SELECT t1.* INTO DS_r FROM (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,log(2,t0.ME_1) AS ME_1,log(2,t0.ME_2) AS ME_2 FROM DS_8 t0) t1;"+"CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);"+"CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);";
		} else if (profileActive.equalsIgnoreCase("mySql")) {
			comandSqlResult = "CREATE TABLE DS_r AS (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,log(t0.ME_1,2) AS ME_1,log(t0.ME_2,2) AS ME_2 FROM DS_8 t0);"+"CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);"+"CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);";
		} else if (profileActive.equalsIgnoreCase("postgreSql")) {
			comandSqlResult = "CREATE TABLE DS_r AS (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,log(t0.ME_1,2) AS ME_1,log(t0.ME_2,2) AS ME_2 FROM DS_8 t0);"+"CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);"+"CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);";
		}
		
		String commandStatements = "DS_r := log ( DS_8, 2 );";
		String result = translationUtilTest.translate(commandStatements);
		System.out.println("Print queryLog: " +result);
		System.out.println("Print queryAttesaLog: " +comandSqlResult);
		assertEquals("Test operator Log", comandSqlResult, result);

	}

	@Test
	@Transactional
	public void logInt() throws Exception {
		dbTableUtilityService.resetSchema("t");
		System.out.println("Il profilo attivo è " + environment.getActiveProfiles()[0]);
		String comandSqlResult = "";
		String profileActive = environment.getActiveProfiles()[0];
		if (profileActive.equalsIgnoreCase("oracleSql")) {
			comandSqlResult = "CREATE TABLE DS_r AS (SELECT log(1024,2) AS num_var FROM DUAL);";
		} else if (profileActive.equalsIgnoreCase("sqlServer")) {
			comandSqlResult = "SELECT t0.* INTO DS_r FROM (SELECT log(2,1024) AS num_var) t0;";
		} else if (profileActive.equalsIgnoreCase("mySql")) {
			comandSqlResult = "CREATE TABLE DS_r AS (SELECT log(1024,2) AS num_var FROM DUAL);";
		} else if (profileActive.equalsIgnoreCase("postgreSql")) {
			comandSqlResult = "CREATE TABLE DS_r AS (SELECT log(1024,2) AS num_var);";
		}
		String commandStatements = "DS_r := log ( 1024, 2 );";
		String result = translationUtilTest.translate(commandStatements);
		System.out.println("Print queryLogInt: " +result);
		System.out.println("Print queryAttesaLogInt: " +comandSqlResult);
		assertEquals("Test operator LogInt", comandSqlResult, result);
	}

	@Test
	@Transactional
	public void logComponent() throws Exception {
		dbTableUtilityService.resetSchema("t");
		VtlDataset vtlDatasetFind = datasetRepository.findByNameIgnoreCase("DS_1");
		if (vtlDatasetFind == null) {
			VtlDataset vtlDataset = new VtlDataset();

			vtlDataset.setPersistent(true);
			vtlDataset.setName("DS_8");
			vtlDataset.setIsOnlyAScalar(false);
			vtlDataset.setTransitory(false);
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ID_1", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ID_2", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ME_1", VtlType.INTEGER, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ME_2", VtlType.NUMBER, VtlComponentRole.MEASURE));
			datasetRepository.save(vtlDataset);
		}
		
		System.out.println("Il profilo attivo è " + environment.getActiveProfiles()[0]);
		String comandSqlResult = "";
		String profileActive = environment.getActiveProfiles()[0];
		if (profileActive.equalsIgnoreCase("oracleSql")) {
			comandSqlResult = "CREATE TABLE DS_r AS (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,log(t0.ME_1,2) AS Me_1,t0.ME_2 AS ME_2 FROM DS_8 t0);"+"CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);"+"CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);";
		} else if (profileActive.equalsIgnoreCase("sqlServer")) {
			comandSqlResult = "SELECT t1.* INTO DS_r FROM (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,log(2,t0.ME_1) AS Me_1,t0.ME_2 AS ME_2 FROM DS_8 t0) t1;"+"CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);"+"CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);";
		} else if (profileActive.equalsIgnoreCase("mySql")) {
			comandSqlResult = "CREATE TABLE DS_r AS (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,log(t0.ME_1,2) AS Me_1,t0.ME_2 AS ME_2 FROM DS_8 t0);"+"CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);"+"CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);";
		} else if (profileActive.equalsIgnoreCase("postgreSql")) {
			comandSqlResult = "CREATE TABLE DS_r AS (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,log(t0.ME_1,2) AS Me_1,t0.ME_2 AS ME_2 FROM DS_8 t0);"+"CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);"+"CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);";
		}
		
		String commandStatements = "DS_r := DS_8 [ calc Me_1 := log (Me_1, 2)  ];";
		String result = translationUtilTest.translate(commandStatements);
		System.out.println("Print queryLogComponent: " +result);
		System.out.println("Print queryAttesaLogComponent: " +comandSqlResult);
		assertEquals("Test operator LogComponent", comandSqlResult, result);

	}
	
	@Test
	@Transactional
	public void sqrt() throws Exception {
		dbTableUtilityService.resetSchema("t");
		VtlDataset vtlDatasetFind = datasetRepository.findByNameIgnoreCase("DS_1");
		if (vtlDatasetFind == null) {
			VtlDataset vtlDataset = new VtlDataset();

			vtlDataset.setPersistent(true);
			vtlDataset.setName("DS_8");
			vtlDataset.setIsOnlyAScalar(false);
			vtlDataset.setTransitory(false);
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ID_1", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ID_2", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ME_1", VtlType.INTEGER, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ME_2", VtlType.NUMBER, VtlComponentRole.MEASURE));
			datasetRepository.save(vtlDataset);
		}
		
		System.out.println("Il profilo attivo è " + environment.getActiveProfiles()[0]);
		String comandSqlResult = "";
		String profileActive = environment.getActiveProfiles()[0];
		if (profileActive.equalsIgnoreCase("oracleSql")) {
			comandSqlResult = "CREATE TABLE DS_r AS (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,sqrt(t0.ME_1) AS ME_1,sqrt(t0.ME_2) AS ME_2 FROM DS_8 t0);"+"CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);"+"CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);";
		} else if (profileActive.equalsIgnoreCase("sqlServer")) {
			comandSqlResult = "SELECT t1.* INTO DS_r FROM (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,sqrt(t0.ME_1) AS ME_1,sqrt(t0.ME_2) AS ME_2 FROM DS_8 t0) t1;"+"CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);"+"CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);";
		} else if (profileActive.equalsIgnoreCase("mySql")) {
			comandSqlResult = "CREATE TABLE DS_r AS (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,sqrt(t0.ME_1) AS ME_1,sqrt(t0.ME_2) AS ME_2 FROM DS_8 t0);"+"CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);"+"CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);";
		} else if (profileActive.equalsIgnoreCase("postgreSql")) {
			comandSqlResult = "CREATE TABLE DS_r AS (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,sqrt(t0.ME_1) AS ME_1,sqrt(t0.ME_2) AS ME_2 FROM DS_8 t0);"+"CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);"+"CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);";
		}
		
		String commandStatements = "DS_r := sqrt(DS_8);";
		String result = translationUtilTest.translate(commandStatements);
		System.out.println("Print querySqrt: " +result);
		System.out.println("Print queryAttesaSqrt: " +comandSqlResult);
		assertEquals("Test operator Sqrt", comandSqlResult, result);

	}

	@Test
	@Transactional
	public void sqrtInt() throws Exception {
		dbTableUtilityService.resetSchema("t");
		System.out.println("Il profilo attivo è " + environment.getActiveProfiles()[0]);
		String comandSqlResult = "";
		String profileActive = environment.getActiveProfiles()[0];
		if (profileActive.equalsIgnoreCase("oracleSql")) {
			comandSqlResult = "CREATE TABLE DS_r AS (SELECT sqrt(25) AS num_var FROM DUAL);";
		} else if (profileActive.equalsIgnoreCase("sqlServer")) {
			comandSqlResult = "SELECT t0.* INTO DS_r FROM (SELECT sqrt(25) AS num_var) t0;";
		} else if (profileActive.equalsIgnoreCase("mySql")) {
			comandSqlResult = "CREATE TABLE DS_r AS (SELECT sqrt(25) AS num_var FROM DUAL);";;
		} else if (profileActive.equalsIgnoreCase("postgreSql")) {
			comandSqlResult = "CREATE TABLE DS_r AS (SELECT sqrt(25) AS num_var);";
		}
		String commandStatements = "DS_r := sqrt ( 25 );";
		String result = translationUtilTest.translate(commandStatements);
		System.out.println("Print querySqrtInt: " +result);
		System.out.println("Print queryAttesaSqrtInt: " +comandSqlResult);
		assertEquals("Test operator SqrtInt", comandSqlResult, result);
	}

	@Test
	@Transactional
	public void sqrtComponent() throws Exception {
		dbTableUtilityService.resetSchema("t");
		VtlDataset vtlDatasetFind = datasetRepository.findByNameIgnoreCase("DS_1");
		if (vtlDatasetFind == null) {
			VtlDataset vtlDataset = new VtlDataset();

			vtlDataset.setPersistent(true);
			vtlDataset.setName("DS_8");
			vtlDataset.setIsOnlyAScalar(false);
			vtlDataset.setTransitory(false);
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ID_1", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ID_2", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ME_1", VtlType.INTEGER, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ME_2", VtlType.NUMBER, VtlComponentRole.MEASURE));
			datasetRepository.save(vtlDataset);
		}
		
		System.out.println("Il profilo attivo è " + environment.getActiveProfiles()[0]);
		String comandSqlResult = "";
		String profileActive = environment.getActiveProfiles()[0];
		if (profileActive.equalsIgnoreCase("oracleSql")) {
			comandSqlResult = "CREATE TABLE DS_r AS (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,sqrt(t0.ME_1) AS Me_1,t0.ME_2 AS ME_2 FROM DS_8 t0);"+"CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);"+"CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);";
		} else if (profileActive.equalsIgnoreCase("sqlServer")) {
			comandSqlResult = "SELECT t1.* INTO DS_r FROM (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,sqrt(t0.ME_1) AS Me_1,t0.ME_2 AS ME_2 FROM DS_8 t0) t1;"+"CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);"+"CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);";
		} else if (profileActive.equalsIgnoreCase("mySql")) {
			comandSqlResult = "CREATE TABLE DS_r AS (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,sqrt(t0.ME_1) AS Me_1,t0.ME_2 AS ME_2 FROM DS_8 t0);"+"CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);"+"CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);";
		} else if (profileActive.equalsIgnoreCase("postgreSql")) {
			comandSqlResult = "CREATE TABLE DS_r AS (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,sqrt(t0.ME_1) AS Me_1,t0.ME_2 AS ME_2 FROM DS_8 t0);"+"CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);"+"CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);";
		}
		
		String commandStatements = "DS_r := DS_8 [ calc Me_1 := sqrt ( Me_1 ) ];";
		String result = translationUtilTest.translate(commandStatements);
		System.out.println("Print querySqrtComponent: " +result);
		System.out.println("Print queryAttesaSqrtComponent: " +comandSqlResult);
		assertEquals("Test operator SqrtComponent", comandSqlResult, result);

	}

	@Test
	@Transactional
	public void round() throws Exception {
		dbTableUtilityService.resetSchema("t");
		VtlDataset vtlDatasetFind = datasetRepository.findByNameIgnoreCase("DS_1");
		if (vtlDatasetFind == null) {
			VtlDataset vtlDataset = new VtlDataset();

			vtlDataset.setPersistent(true);
			vtlDataset.setName("DS_10");
			vtlDataset.setIsOnlyAScalar(false);
			vtlDataset.setTransitory(false);
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ID_1", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ID_2", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ME_1", VtlType.NUMBER, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ME_2", VtlType.NUMBER, VtlComponentRole.MEASURE));
			datasetRepository.save(vtlDataset);

		}
		
		System.out.println("Il profilo attivo è " + environment.getActiveProfiles()[0]);
		String comandSqlResult = "";
		String profileActive = environment.getActiveProfiles()[0];
		if (profileActive.equalsIgnoreCase("oracleSql")) {
			comandSqlResult = "CREATE TABLE DS_r AS (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,round(t0.ME_1,0) AS ME_1,round(t0.ME_2,0) AS ME_2 FROM DS_10 t0);"+"CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);"+"CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);";
		} else if (profileActive.equalsIgnoreCase("sqlServer")) {
			comandSqlResult = "SELECT t1.* INTO DS_r FROM (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,round(t0.ME_1,0) AS ME_1,round(t0.ME_2,0) AS ME_2 FROM DS_10 t0) t1;"+"CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);"+"CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);";
		} else if (profileActive.equalsIgnoreCase("mySql")) {
			comandSqlResult = "CREATE TABLE DS_r AS (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,round(t0.ME_1,0) AS ME_1,round(t0.ME_2,0) AS ME_2 FROM DS_10 t0);"+"CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);"+"CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);";
		} else if (profileActive.equalsIgnoreCase("postgreSql")) {
			comandSqlResult = "CREATE TABLE DS_r AS (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,round(t0.ME_1,0) AS ME_1,round(t0.ME_2,0) AS ME_2 FROM DS_10 t0);"+"CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);"+"CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);";
		}
		
		String commandStatements = "DS_r := round(DS_10, 0);";
		String result = translationUtilTest.translate(commandStatements);
		System.out.println("Print queryRound: " +result);
		System.out.println("Print queryAttesaRound: " +comandSqlResult);
		assertEquals("Test operator Round", comandSqlResult, result);


	}

	@Test
	@Transactional
	public void roundComponent() throws Exception {
		dbTableUtilityService.resetSchema("t");
		VtlDataset vtlDatasetFind = datasetRepository.findByNameIgnoreCase("DS_1");
		if (vtlDatasetFind == null) {
			VtlDataset vtlDataset = new VtlDataset();

			vtlDataset.setPersistent(true);
			vtlDataset.setName("DS_10");
			vtlDataset.setIsOnlyAScalar(false);
			vtlDataset.setTransitory(false);
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ID_1", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ID_2", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ME_1", VtlType.NUMBER, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ME_2", VtlType.NUMBER, VtlComponentRole.MEASURE));
			datasetRepository.save(vtlDataset);

		}
		
		System.out.println("Il profilo attivo è " + environment.getActiveProfiles()[0]);
		String comandSqlResult = "";
		String profileActive = environment.getActiveProfiles()[0];
		if (profileActive.equalsIgnoreCase("oracleSql")) {
			comandSqlResult = "CREATE TABLE DS_r AS (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,t0.ME_1 AS ME_1,t0.ME_2 AS ME_2,round(t0.ME_1) AS Me_10 FROM DS_10 t0);"+"CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);"+"CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);";
		} else if (profileActive.equalsIgnoreCase("sqlServer")) {
			comandSqlResult = "SELECT t1.* INTO DS_r FROM (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,t0.ME_1 AS ME_1,t0.ME_2 AS ME_2,round(t0.ME_1) AS Me_10 FROM DS_10 t0) t1;"+"CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);"+"CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);";
		} else if (profileActive.equalsIgnoreCase("mySql")) {
			comandSqlResult = "CREATE TABLE DS_r AS (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,t0.ME_1 AS ME_1,t0.ME_2 AS ME_2,round(t0.ME_1) AS Me_10 FROM DS_10 t0);"+"CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);"+"CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);";
		} else if (profileActive.equalsIgnoreCase("postgreSql")) {
			comandSqlResult = "CREATE TABLE DS_r AS (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,t0.ME_1 AS ME_1,t0.ME_2 AS ME_2,round(t0.ME_1) AS Me_10 FROM DS_10 t0);"+"CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);"+"CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);";
		}
		
		String commandStatements = "DS_r := DS_10 [ calc Me_10:= round( Me_1 ) ];";
		String result = translationUtilTest.translate(commandStatements);
		System.out.println("Print queryRoundComponent: " +result);
		System.out.println("Print queryAttesaRoundComponent: " +comandSqlResult);
		assertEquals("Test operator RoundComponent", comandSqlResult, result);

	}

	@Test
	@Transactional
	public void roundComponentMe() throws Exception {
		dbTableUtilityService.resetSchema("t");
		VtlDataset vtlDatasetFind = datasetRepository.findByNameIgnoreCase("DS_1");
		if (vtlDatasetFind == null) {
			VtlDataset vtlDataset = new VtlDataset();

			vtlDataset.setPersistent(true);
			vtlDataset.setName("DS_10");
			vtlDataset.setIsOnlyAScalar(false);
			vtlDataset.setTransitory(false);
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ID_1", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ID_2", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ME_1", VtlType.NUMBER, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ME_2", VtlType.NUMBER, VtlComponentRole.MEASURE));
			datasetRepository.save(vtlDataset);

		}
		System.out.println("Il profilo attivo è " + environment.getActiveProfiles()[0]);
		String comandSqlResult = "";
		String profileActive = environment.getActiveProfiles()[0];
		if (profileActive.equalsIgnoreCase("oracleSql")) {
			comandSqlResult = "CREATE TABLE DS_r AS (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,t0.ME_1 AS ME_1,t0.ME_2 AS ME_2,round(t0.ME_1,-1) AS Me_20 FROM DS_10 t0);"+"CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);"+"CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);";
		} else if (profileActive.equalsIgnoreCase("sqlServer")) {
			comandSqlResult = "SELECT t1.* INTO DS_r FROM (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,t0.ME_1 AS ME_1,t0.ME_2 AS ME_2,round(t0.ME_1,-1) AS Me_20 FROM DS_10 t0) t1;"+"CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);"+"CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);";
		} else if (profileActive.equalsIgnoreCase("mySql")) {
			comandSqlResult = "CREATE TABLE DS_r AS (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,t0.ME_1 AS ME_1,t0.ME_2 AS ME_2,round(t0.ME_1,-1) AS Me_20 FROM DS_10 t0);"+"CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);"+"CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);";
		} else if (profileActive.equalsIgnoreCase("postgreSql")) {
			comandSqlResult = "CREATE TABLE DS_r AS (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,t0.ME_1 AS ME_1,t0.ME_2 AS ME_2,round(t0.ME_1,-1) AS Me_20 FROM DS_10 t0);"+"CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);"+"CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);";
		}
		String commandStatements = "DS_r := DS_10 [ calc Me_20:= round( Me_1 , -1 ) ];";
		String result = translationUtilTest.translate(commandStatements);
		System.out.println("Print queryRoundComponentMe: " +result);
		System.out.println("Print queryAttesaRoundComponentMe: " +comandSqlResult);
		assertEquals("Test operator RoundComponentMe", comandSqlResult, result);

	}

	@Test
	@Transactional
	public void roundNumber() throws Exception {
		dbTableUtilityService.resetSchema("t");
		System.out.println("Il profilo attivo è " + environment.getActiveProfiles()[0]);
		String comandSqlResult = "";
		String profileActive = environment.getActiveProfiles()[0];
		if (profileActive.equalsIgnoreCase("oracleSql")) {
			comandSqlResult = "CREATE TABLE DS_r AS (SELECT round(3.14159,2) AS num_var FROM DUAL);";
		} else if (profileActive.equalsIgnoreCase("sqlServer")) {
			comandSqlResult = "SELECT t0.* INTO DS_r FROM (SELECT round(3.14159,2) AS num_var) t0;";
		} else if (profileActive.equalsIgnoreCase("mySql")) {
			comandSqlResult = "CREATE TABLE DS_r AS (SELECT round(3.14159,2) AS num_var FROM DUAL);";
		} else if (profileActive.equalsIgnoreCase("postgreSql")) {
			comandSqlResult = "CREATE TABLE DS_r AS (SELECT round(3.14159,2) AS num_var);";
		}
		String commandStatements = "DS_r := round ( 3.14159 , 2 );";
		String result = translationUtilTest.translate(commandStatements);
		System.out.println("Print queryRoundComponentMe: " +result);
		System.out.println("Print queryAttesaRoundComponentMe: " +comandSqlResult);
		assertEquals("Test operator RoundComponentMe", comandSqlResult, result);
	}

	@Test
	@Transactional
	public void truncNumber_() throws Exception {
		dbTableUtilityService.resetSchema("t");
		System.out.println("Il profilo attivo è " + environment.getActiveProfiles()[0]);
		String comandSqlResult = "";
		String profileActive = environment.getActiveProfiles()[0];
		if (profileActive.equalsIgnoreCase("oracleSql")) {
			comandSqlResult = "CREATE TABLE DS_r AS (SELECT trunc(3.14159,99) AS num_var FROM DUAL);";
		} else if (profileActive.equalsIgnoreCase("sqlServer")) {
			comandSqlResult = "SELECT t0.* INTO DS_r FROM (SELECT round(3.14159,99) AS num_var) t0;";
		} else if (profileActive.equalsIgnoreCase("mySql")) {
			comandSqlResult = "CREATE TABLE DS_r AS (SELECT truncate(3.14159,99) AS num_var FROM DUAL);";
		} else if (profileActive.equalsIgnoreCase("postgreSql")) {
			comandSqlResult = "CREATE TABLE DS_r AS (SELECT trunc(3.14159,99) AS num_var);";
		}
		String commandStatements = "DS_r := trunc ( 3.14159 , _ );";
		String result = translationUtilTest.translate(commandStatements);
		System.out.println("Print queryRoundComponentMe: " +result);
		System.out.println("Print queryAttesaRoundComponentMe: " +comandSqlResult);
		assertEquals("Test operator RoundComponentMe", comandSqlResult, result);
	}

	@Test
	@Transactional
	public void ln_e() throws Exception {
		// L'operatore E non è previsto nella grammatica //TODO
		String commandStatements = "DS_r := ln ( e );";
		String comandSqlResult = "";
		try {
			String result = translationUtilTest.translate(commandStatements);
			System.out.println("Print queryLn_e: " +result);
			System.out.println("Print queryAttesaLn_e: " +comandSqlResult);
			assertEquals("Test operator Ln_e", comandSqlResult, result);
		} catch (Exception e) {
			assertTrue("Operatore non supportato", true);
		}
	}

}
