package com.capgemini.istat.vtlcompiler.vtlapi.traslate;

import com.capgemini.istat.vtlcompiler.vtlapi.utils.TranslationUtilsTestService;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.dataset.VtlComponentRole;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.dataset.VtlDataset;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.dataset.VtlType;
import com.capgemini.istat.vtlcompiler.vtlcommon.repository.DatasetRepository;
import com.capgemini.istat.vtlcompiler.vtlsqlbuilder.service.utility.DbTableUtilityService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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
public class VtlBooleanOperatorsTranslationTest {
	
	private static final Logger logger = LogManager.getLogger(VtlBooleanOperatorsTranslationTest.class);

    private TranslationUtilsTestService translationUtilTest;
    
	private DatasetRepository datasetRepository;
	
	private DbTableUtilityService dbTableUtilityService;
	
	private Environment environment;
	
	@Autowired
	public void setEnvironment(Environment environment) {
		this.environment = environment;
	}
        
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

	@Test
	@Transactional
	public void and() throws Exception {
		dbTableUtilityService.resetSchema("t");
		VtlDataset vtlDatasetFind = datasetRepository.findByNameIgnoreCase("DS_1");
		if (vtlDatasetFind == null) {
			VtlDataset vtlDataset = new VtlDataset();

			vtlDataset.setPersistent(true);   
			vtlDataset.setName("DS_19");
			vtlDataset.setIsOnlyAScalar(false);
			vtlDataset.setTransitory(false);
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ID_1", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ID_2", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ID_3", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ID_4", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ME_1", VtlType.BOOLEAN, VtlComponentRole.MEASURE));
			datasetRepository.save(vtlDataset);

			VtlDataset vtlDatasetSecond = new VtlDataset();
			vtlDatasetSecond.setPersistent(true);
			vtlDatasetSecond.setName("DS_20");
			vtlDatasetSecond.setIsOnlyAScalar(false);
			vtlDatasetSecond.setTransitory(false);
			vtlDatasetSecond
					.addComponent(translationUtilTest.getVtlComponent("ID_1", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDatasetSecond
					.addComponent(translationUtilTest.getVtlComponent("ID_2", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDatasetSecond
					.addComponent(translationUtilTest.getVtlComponent("ID_3", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDatasetSecond
					.addComponent(translationUtilTest.getVtlComponent("ID_4", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDatasetSecond
					.addComponent(translationUtilTest.getVtlComponent("ME_1", VtlType.BOOLEAN, VtlComponentRole.MEASURE));
			datasetRepository.save(vtlDatasetSecond);
		}
		
        System.out.println("Il profilo attivo è " + environment.getActiveProfiles()[0]);
        String comandSqlResult = "";
        String profileActive = environment.getActiveProfiles()[0];
        if (profileActive.equalsIgnoreCase("oracleSql")) {
                    comandSqlResult = "CREATE TABLE DS_r AS (SELECT t2.ID_1 AS ID_1,t2.ID_2 AS ID_2,t2.ID_3 AS ID_3,t2.ID_4 AS ID_4,vtl_pkg.and_bool(t2.ME_1,t1.ME_1) AS ME_1 FROM DS_19 t2 INNER JOIN DS_20 t1 ON (((t2.ID_1 = t1.ID_1) AND (t2.ID_2 = t1.ID_2) AND (t2.ID_3 = t1.ID_3) AND (t2.ID_4 = t1.ID_4))))"+";"+"CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1)"+";"+"CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2)"+";"+"CREATE INDEX DS_r_ID_3_IDX ON DS_r (ID_3)"+";"+"CREATE INDEX DS_r_ID_4_IDX ON DS_r (ID_4)"+";";  
        } else if (profileActive.equalsIgnoreCase("sqlServer")) {
                    comandSqlResult = "SELECT t3.* INTO DS_r FROM (SELECT t2.ID_1 AS ID_1,t2.ID_2 AS ID_2,t2.ID_3 AS ID_3,t2.ID_4 AS ID_4,dbo.vtl_and_bool(t2.ME_1,t1.ME_1) AS ME_1 FROM DS_19 t2 INNER JOIN DS_20 t1 ON (((t2.ID_1 = t1.ID_1) AND (t2.ID_2 = t1.ID_2) AND (t2.ID_3 = t1.ID_3) AND (t2.ID_4 = t1.ID_4)))) t3;CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);CREATE INDEX DS_r_ID_3_IDX ON DS_r (ID_3);CREATE INDEX DS_r_ID_4_IDX ON DS_r (ID_4);";
        } else if (profileActive.equalsIgnoreCase("mySql")) {
                    comandSqlResult = "CREATE TABLE DS_r AS (SELECT t2.ID_1 AS ID_1,t2.ID_2 AS ID_2,t2.ID_3 AS ID_3,t2.ID_4 AS ID_4,vtl_and_bool(t2.ME_1,t1.ME_1) AS ME_1 FROM DS_19 t2 INNER JOIN DS_20 t1 ON (((t2.ID_1 = t1.ID_1) AND (t2.ID_2 = t1.ID_2) AND (t2.ID_3 = t1.ID_3) AND (t2.ID_4 = t1.ID_4))))"+";"+"CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1)"+";"+"CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2)"+";"+"CREATE INDEX DS_r_ID_3_IDX ON DS_r (ID_3)"+";"+"CREATE INDEX DS_r_ID_4_IDX ON DS_r (ID_4)"+";";
        } else if (profileActive.equalsIgnoreCase("postgreSql")) {
                    comandSqlResult = "CREATE TABLE DS_r AS (SELECT t2.ID_1 AS ID_1,t2.ID_2 AS ID_2,t2.ID_3 AS ID_3,t2.ID_4 AS ID_4,vtl_and_bool(t2.ME_1,t1.ME_1) AS ME_1 FROM DS_19 t2 INNER JOIN DS_20 t1 ON (((t2.ID_1 = t1.ID_1) AND (t2.ID_2 = t1.ID_2) AND (t2.ID_3 = t1.ID_3) AND (t2.ID_4 = t1.ID_4))))"+";"+"CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1)"+";"+"CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2)"+";"+"CREATE INDEX DS_r_ID_3_IDX ON DS_r (ID_3)"+";"+"CREATE INDEX DS_r_ID_4_IDX ON DS_r (ID_4)"+";";
        }

		String commandStatements = "DS_r := DS_19 and DS_20;"; 
		String result = translationUtilTest.translate(commandStatements);
		System.out.println("Print queryAND: " +result);
		System.out.println("Print queryAttesaAND: " +comandSqlResult);
		assertEquals("Test operator AND", comandSqlResult, result);
		}

	@Test
	@Transactional
	public void andComponent() throws Exception {
		dbTableUtilityService.resetSchema("t");
		VtlDataset vtlDatasetFind = datasetRepository.findByNameIgnoreCase("DS_1");
		if (vtlDatasetFind == null) {
			VtlDataset vtlDataset = new VtlDataset();

			vtlDataset.setPersistent(true);
			vtlDataset.setName("DS_19");
			vtlDataset.setIsOnlyAScalar(false);
			vtlDataset.setTransitory(false);
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ID_1", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ID_2", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ID_3", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ID_4", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ME_1", VtlType.BOOLEAN, VtlComponentRole.MEASURE));
			datasetRepository.save(vtlDataset);

			VtlDataset vtlDatasetSecond = new VtlDataset();
			vtlDatasetSecond.setPersistent(true);
			vtlDatasetSecond.setName("DS_20");
			vtlDatasetSecond.setIsOnlyAScalar(false);
			vtlDatasetSecond.setTransitory(false);
			vtlDatasetSecond
					.addComponent(translationUtilTest.getVtlComponent("ID_1", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDatasetSecond
					.addComponent(translationUtilTest.getVtlComponent("ID_2", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDatasetSecond
					.addComponent(translationUtilTest.getVtlComponent("ID_3", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDatasetSecond
					.addComponent(translationUtilTest.getVtlComponent("ID_4", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDatasetSecond
					.addComponent(translationUtilTest.getVtlComponent("ME_1", VtlType.BOOLEAN, VtlComponentRole.MEASURE));
			datasetRepository.save(vtlDatasetSecond);
		}
		
        System.out.println("Il profilo attivo è " + environment.getActiveProfiles()[0]);
        String comandSqlResult = "";
        String profileActive = environment.getActiveProfiles()[0];
        if (profileActive.equalsIgnoreCase("oracleSql")) {
                    comandSqlResult = "CREATE TABLE DS_r AS (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,t0.ID_3 AS ID_3,t0.ID_4 AS ID_4,t0.ME_1 AS ME_1,vtl_pkg.and_bool(t0.ME_1,'true') AS Me_20 FROM DS_19 t0);" + "CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);" + "CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);" + "CREATE INDEX DS_r_ID_3_IDX ON DS_r (ID_3);" + "CREATE INDEX DS_r_ID_4_IDX ON DS_r (ID_4);";  
        } else if (profileActive.equalsIgnoreCase("sqlServer")) {
                    comandSqlResult = "SELECT t1.* INTO DS_r FROM (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,t0.ID_3 AS ID_3,t0.ID_4 AS ID_4,t0.ME_1 AS ME_1,dbo.vtl_and_bool(t0.ME_1,'true') AS Me_20 FROM DS_19 t0) t1;CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);CREATE INDEX DS_r_ID_3_IDX ON DS_r (ID_3);CREATE INDEX DS_r_ID_4_IDX ON DS_r (ID_4);";
        } else if (profileActive.equalsIgnoreCase("mySql")) {
                    comandSqlResult = "CREATE TABLE DS_r AS (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,t0.ID_3 AS ID_3,t0.ID_4 AS ID_4,t0.ME_1 AS ME_1,vtl_and_bool(t0.ME_1,'true') AS Me_20 FROM DS_19 t0);" + "CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);" + "CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);" + "CREATE INDEX DS_r_ID_3_IDX ON DS_r (ID_3);" + "CREATE INDEX DS_r_ID_4_IDX ON DS_r (ID_4);";
        } else if (profileActive.equalsIgnoreCase("postgreSql")) {
                    comandSqlResult = "CREATE TABLE DS_r AS (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,t0.ID_3 AS ID_3,t0.ID_4 AS ID_4,t0.ME_1 AS ME_1,vtl_and_bool(t0.ME_1,'true') AS Me_20 FROM DS_19 t0);" + "CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);" + "CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);" + "CREATE INDEX DS_r_ID_3_IDX ON DS_r (ID_3);" + "CREATE INDEX DS_r_ID_4_IDX ON DS_r (ID_4);";
        }		
		
		String commandStatements = "DS_r := DS_19 [ calc Me_20:= Me_1 and true ];";
		String result = translationUtilTest.translate(commandStatements);
		
		logger.info("Print Traslate: " + result);
		System.out.println("Print queryANDComponent: " +result);
		System.out.println("Print queryAttesaANDComponent: " +comandSqlResult);
		
		assertEquals("Test operator ANDComponent", comandSqlResult, result);

	}
	
	@Test
	@Transactional
	public void or() throws Exception {
		dbTableUtilityService.resetSchema("t");
		VtlDataset vtlDatasetFind = datasetRepository.findByNameIgnoreCase("DS_1");
		if (vtlDatasetFind == null) {
			VtlDataset vtlDataset = new VtlDataset();

			vtlDataset.setPersistent(true);
			vtlDataset.setName("DS_19");
			vtlDataset.setIsOnlyAScalar(false);
			vtlDataset.setTransitory(false);
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ID_1", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ID_2", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ID_3", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ID_4", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ME_1", VtlType.BOOLEAN, VtlComponentRole.MEASURE));
			datasetRepository.save(vtlDataset);

			VtlDataset vtlDatasetSecond = new VtlDataset();
			vtlDatasetSecond.setPersistent(true);
			vtlDatasetSecond.setName("DS_20");
			vtlDatasetSecond.setIsOnlyAScalar(false);
			vtlDatasetSecond.setTransitory(false);
			vtlDatasetSecond
					.addComponent(translationUtilTest.getVtlComponent("ID_1", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDatasetSecond
					.addComponent(translationUtilTest.getVtlComponent("ID_2", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDatasetSecond
					.addComponent(translationUtilTest.getVtlComponent("ID_3", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDatasetSecond
					.addComponent(translationUtilTest.getVtlComponent("ID_4", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDatasetSecond
					.addComponent(translationUtilTest.getVtlComponent("ME_1", VtlType.BOOLEAN, VtlComponentRole.MEASURE));
			datasetRepository.save(vtlDatasetSecond);
		}
		
        System.out.println("Il profilo attivo è " + environment.getActiveProfiles()[0]);
        String comandSqlResult = "";
        String profileActive = environment.getActiveProfiles()[0];
        if (profileActive.equalsIgnoreCase("oracleSql")) {
                    comandSqlResult = "CREATE TABLE DS_r AS (SELECT t2.ID_1 AS ID_1,t2.ID_2 AS ID_2,t2.ID_3 AS ID_3,t2.ID_4 AS ID_4,vtl_pkg.or_bool(t2.ME_1,t1.ME_1) AS ME_1 FROM DS_19 t2 INNER JOIN DS_20 t1 ON (((t2.ID_1 = t1.ID_1) AND (t2.ID_2 = t1.ID_2) AND (t2.ID_3 = t1.ID_3) AND (t2.ID_4 = t1.ID_4))));"+"CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);"+"CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);"+"CREATE INDEX DS_r_ID_3_IDX ON DS_r (ID_3);"+"CREATE INDEX DS_r_ID_4_IDX ON DS_r (ID_4);";  
        } else if (profileActive.equalsIgnoreCase("sqlServer")) {
                    comandSqlResult = "SELECT t3.* INTO DS_r FROM (SELECT t2.ID_1 AS ID_1,t2.ID_2 AS ID_2,t2.ID_3 AS ID_3,t2.ID_4 AS ID_4,dbo.vtl_or_bool(t2.ME_1,t1.ME_1) AS ME_1 FROM DS_19 t2 INNER JOIN DS_20 t1 ON (((t2.ID_1 = t1.ID_1) AND (t2.ID_2 = t1.ID_2) AND (t2.ID_3 = t1.ID_3) AND (t2.ID_4 = t1.ID_4)))) t3;CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);CREATE INDEX DS_r_ID_3_IDX ON DS_r (ID_3);CREATE INDEX DS_r_ID_4_IDX ON DS_r (ID_4);";
        } else if (profileActive.equalsIgnoreCase("mySql")) {
                    comandSqlResult = "CREATE TABLE DS_r AS (SELECT t2.ID_1 AS ID_1,t2.ID_2 AS ID_2,t2.ID_3 AS ID_3,t2.ID_4 AS ID_4,vtl_or_bool(t2.ME_1,t1.ME_1) AS ME_1 FROM DS_19 t2 INNER JOIN DS_20 t1 ON (((t2.ID_1 = t1.ID_1) AND (t2.ID_2 = t1.ID_2) AND (t2.ID_3 = t1.ID_3) AND (t2.ID_4 = t1.ID_4))));"+"CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);"+"CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);"+"CREATE INDEX DS_r_ID_3_IDX ON DS_r (ID_3);"+"CREATE INDEX DS_r_ID_4_IDX ON DS_r (ID_4);";
        } else if (profileActive.equalsIgnoreCase("postgreSql")) {
                    comandSqlResult = "CREATE TABLE DS_r AS (SELECT t2.ID_1 AS ID_1,t2.ID_2 AS ID_2,t2.ID_3 AS ID_3,t2.ID_4 AS ID_4,vtl_or_bool(t2.ME_1,t1.ME_1) AS ME_1 FROM DS_19 t2 INNER JOIN DS_20 t1 ON (((t2.ID_1 = t1.ID_1) AND (t2.ID_2 = t1.ID_2) AND (t2.ID_3 = t1.ID_3) AND (t2.ID_4 = t1.ID_4))));"+"CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);"+"CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);"+"CREATE INDEX DS_r_ID_3_IDX ON DS_r (ID_3);"+"CREATE INDEX DS_r_ID_4_IDX ON DS_r (ID_4);"; 
        }		
		
		String commandStatements = "DS_r := DS_19 or DS_20;";
		String result = translationUtilTest.translate(commandStatements);
		
		System.out.println("Print queryOR: " +result);
		System.out.println("Print queryAttesaOR: " +comandSqlResult);
		
		assertEquals("Test operator OR", comandSqlResult, result);
		
	}
	
	@Test
	@Transactional
	public void orComponent() throws Exception {
		dbTableUtilityService.resetSchema("t");
		VtlDataset vtlDatasetFind = datasetRepository.findByNameIgnoreCase("DS_1");
		if (vtlDatasetFind == null) {
			VtlDataset vtlDataset = new VtlDataset();

			vtlDataset.setPersistent(true);
			vtlDataset.setName("DS_19");
			vtlDataset.setIsOnlyAScalar(false);
			vtlDataset.setTransitory(false);
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ID_1", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ID_2", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ID_3", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ID_4", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ME_1", VtlType.BOOLEAN, VtlComponentRole.MEASURE));
			datasetRepository.save(vtlDataset);

		}
		
        System.out.println("Il profilo attivo è " + environment.getActiveProfiles()[0]);
        String comandSqlResult = "";
        String profileActive = environment.getActiveProfiles()[0];
        if (profileActive.equalsIgnoreCase("oracleSql")) {
                    comandSqlResult = "CREATE TABLE DS_r AS (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,t0.ID_3 AS ID_3,t0.ID_4 AS ID_4,t0.ME_1 AS ME_1,vtl_pkg.or_bool(t0.ME_1,'true') AS Me_2 FROM DS_19 t0);" + "CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);" + "CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);" + "CREATE INDEX DS_r_ID_3_IDX ON DS_r (ID_3);" + "CREATE INDEX DS_r_ID_4_IDX ON DS_r (ID_4);"; 
        } else if (profileActive.equalsIgnoreCase("sqlServer")) {
                    comandSqlResult = "SELECT t1.* INTO DS_r FROM (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,t0.ID_3 AS ID_3,t0.ID_4 AS ID_4,t0.ME_1 AS ME_1,dbo.vtl_or_bool(t0.ME_1,'true') AS Me_2 FROM DS_19 t0) t1;CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);CREATE INDEX DS_r_ID_3_IDX ON DS_r (ID_3);CREATE INDEX DS_r_ID_4_IDX ON DS_r (ID_4);";
        } else if (profileActive.equalsIgnoreCase("mySql")) {
                    comandSqlResult = "CREATE TABLE DS_r AS (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,t0.ID_3 AS ID_3,t0.ID_4 AS ID_4,t0.ME_1 AS ME_1,vtl_or_bool(t0.ME_1,'true') AS Me_2 FROM DS_19 t0);" + "CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);" + "CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);" + "CREATE INDEX DS_r_ID_3_IDX ON DS_r (ID_3);" + "CREATE INDEX DS_r_ID_4_IDX ON DS_r (ID_4);";
        } else if (profileActive.equalsIgnoreCase("postgreSql")) {
                    comandSqlResult = "CREATE TABLE DS_r AS (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,t0.ID_3 AS ID_3,t0.ID_4 AS ID_4,t0.ME_1 AS ME_1,vtl_or_bool(t0.ME_1,'true') AS Me_2 FROM DS_19 t0);" + "CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);" + "CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);" + "CREATE INDEX DS_r_ID_3_IDX ON DS_r (ID_3);" + "CREATE INDEX DS_r_ID_4_IDX ON DS_r (ID_4);";  
        }		
		
		String commandStatements = "DS_r := DS_19 [ calc Me_2:= Me_1 or true ];";
		String result = translationUtilTest.translate(commandStatements);
		System.out.println("Print queryOrComponent: " +result);
		System.out.println("Print queryAttesaOrComponent: " +comandSqlResult);
		assertEquals("Test operator orComponent", comandSqlResult, result);
		
	}

	@Test
	@Transactional
	public void orComponent2() throws Exception {
		dbTableUtilityService.resetSchema("t");
		VtlDataset vtlDatasetFind = datasetRepository.findByNameIgnoreCase("DS_1");
		if (vtlDatasetFind == null) {
			VtlDataset vtlDataset = new VtlDataset();

			vtlDataset.setPersistent(true);
			vtlDataset.setName("DS_19");
			vtlDataset.setIsOnlyAScalar(false);
			vtlDataset.setTransitory(false);
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ID_1", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ID_2", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ID_3", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ID_4", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ME_1", VtlType.BOOLEAN, VtlComponentRole.MEASURE));
			datasetRepository.save(vtlDataset);
		}
		
        System.out.println("Il profilo attivo è " + environment.getActiveProfiles()[0]);
        String comandSqlResult = "";
        String profileActive = environment.getActiveProfiles()[0];
        if (profileActive.equalsIgnoreCase("oracleSql")) {
                    comandSqlResult = "CREATE TABLE DS_r AS (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,t0.ID_3 AS ID_3,t0.ID_4 AS ID_4,vtl_pkg.or_bool(t0.ME_1,'true') AS ME_1 FROM DS_19 t0);" + "CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);" + "CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);" + "CREATE INDEX DS_r_ID_3_IDX ON DS_r (ID_3);" + "CREATE INDEX DS_r_ID_4_IDX ON DS_r (ID_4);"; 
        } else if (profileActive.equalsIgnoreCase("sqlServer")) {
                    comandSqlResult = "SELECT t1.* INTO DS_r FROM (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,t0.ID_3 AS ID_3,t0.ID_4 AS ID_4,dbo.vtl_or_bool(t0.ME_1,'true') AS ME_1 FROM DS_19 t0) t1;CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);CREATE INDEX DS_r_ID_3_IDX ON DS_r (ID_3);CREATE INDEX DS_r_ID_4_IDX ON DS_r (ID_4);";
        } else if (profileActive.equalsIgnoreCase("mySql")) {
                    comandSqlResult = "CREATE TABLE DS_r AS (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,t0.ID_3 AS ID_3,t0.ID_4 AS ID_4,vtl_or_bool(t0.ME_1,'true') AS ME_1 FROM DS_19 t0);" + "CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);" + "CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);" + "CREATE INDEX DS_r_ID_3_IDX ON DS_r (ID_3);" + "CREATE INDEX DS_r_ID_4_IDX ON DS_r (ID_4);"; 
        } else if (profileActive.equalsIgnoreCase("postgreSql")) {
                    comandSqlResult = "CREATE TABLE DS_r AS (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,t0.ID_3 AS ID_3,t0.ID_4 AS ID_4,vtl_or_bool(t0.ME_1,'true') AS ME_1 FROM DS_19 t0);" + "CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);" + "CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);" + "CREATE INDEX DS_r_ID_3_IDX ON DS_r (ID_3);" + "CREATE INDEX DS_r_ID_4_IDX ON DS_r (ID_4);"; 
        }		
		
		String commandStatements = "DS_r := DS_19 or true;";
 		String result = translationUtilTest.translate(commandStatements);
		System.out.println("Print queryOrComponent2: " +result);
		System.out.println("Print queryAttesaOrComponent2: " +comandSqlResult);
		assertEquals("Test operator orComponent2", comandSqlResult, result);
	}
	
	@Test
	@Transactional
	public void orComponent3() throws Exception {
		dbTableUtilityService.resetSchema("t");
		VtlDataset vtlDatasetFind = datasetRepository.findByNameIgnoreCase("DS_1");
		if (vtlDatasetFind == null) {
			VtlDataset vtlDataset = new VtlDataset();

			vtlDataset.setPersistent(true);
			vtlDataset.setName("DS_19");
			vtlDataset.setIsOnlyAScalar(false);
			vtlDataset.setTransitory(false);
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ID_1", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ID_2", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ID_3", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ID_4", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ME_1", VtlType.BOOLEAN, VtlComponentRole.MEASURE));
			datasetRepository.save(vtlDataset);
		}
		
        System.out.println("Il profilo attivo è " + environment.getActiveProfiles()[0]);
        String comandSqlResult = "";
        String profileActive = environment.getActiveProfiles()[0];
        if (profileActive.equalsIgnoreCase("oracleSql")) {
                    comandSqlResult = "CREATE TABLE DS_r AS (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,t0.ID_3 AS ID_3,t0.ID_4 AS ID_4,vtl_pkg.or_bool(t0.ME_1,'false') AS ME_1 FROM DS_19 t0);" + "CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);" + "CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);" + "CREATE INDEX DS_r_ID_3_IDX ON DS_r (ID_3);" + "CREATE INDEX DS_r_ID_4_IDX ON DS_r (ID_4);"; 
        } else if (profileActive.equalsIgnoreCase("sqlServer")) {
                    comandSqlResult = "SELECT t1.* INTO DS_r FROM (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,t0.ID_3 AS ID_3,t0.ID_4 AS ID_4,dbo.vtl_or_bool(t0.ME_1,'false') AS ME_1 FROM DS_19 t0) t1;CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);CREATE INDEX DS_r_ID_3_IDX ON DS_r (ID_3);CREATE INDEX DS_r_ID_4_IDX ON DS_r (ID_4);";
        } else if (profileActive.equalsIgnoreCase("mySql")) {
                    comandSqlResult = "CREATE TABLE DS_r AS (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,t0.ID_3 AS ID_3,t0.ID_4 AS ID_4,vtl_or_bool(t0.ME_1,'false') AS ME_1 FROM DS_19 t0);" + "CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);" + "CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);" + "CREATE INDEX DS_r_ID_3_IDX ON DS_r (ID_3);" + "CREATE INDEX DS_r_ID_4_IDX ON DS_r (ID_4);";
        } else if (profileActive.equalsIgnoreCase("postgreSql")) {
                    comandSqlResult = "CREATE TABLE DS_r AS (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,t0.ID_3 AS ID_3,t0.ID_4 AS ID_4,vtl_or_bool(t0.ME_1,'false') AS ME_1 FROM DS_19 t0);" + "CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);" + "CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);" + "CREATE INDEX DS_r_ID_3_IDX ON DS_r (ID_3);" + "CREATE INDEX DS_r_ID_4_IDX ON DS_r (ID_4);";
        }		
		
		String commandStatements = "DS_r := DS_19 or false;";
    	String result = translationUtilTest.translate(commandStatements);
		System.out.println("Print queryOrComponent3: " +result);
		System.out.println("Print queryAttesaOrComponent3: " +comandSqlResult);
		assertEquals("Test operator orComponent3", comandSqlResult, result);
	}

	@Test
	@Transactional
	public void xor() throws Exception {
		dbTableUtilityService.resetSchema("t");
		VtlDataset vtlDatasetFind = datasetRepository.findByNameIgnoreCase("DS_1");
		if (vtlDatasetFind == null) {
			VtlDataset vtlDataset = new VtlDataset();

			vtlDataset.setPersistent(true);
			vtlDataset.setName("DS_19");
			vtlDataset.setIsOnlyAScalar(false);
			vtlDataset.setTransitory(false);
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ID_1", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ID_2", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ID_3", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ID_4", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ME_1", VtlType.BOOLEAN, VtlComponentRole.MEASURE));
			datasetRepository.save(vtlDataset);
		}
		
		System.out.println("Il profilo attivo è " + environment.getActiveProfiles()[0]);
        String comandSqlResult = "";
        String profileActive = environment.getActiveProfiles()[0];
        if (profileActive.equalsIgnoreCase("oracleSql")) {
                    comandSqlResult = "CREATE TABLE DS_r AS (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,t0.ID_3 AS ID_3,t0.ID_4 AS ID_4,vtl_pkg.xor_bool(t0.ME_1,'true') AS ME_1 FROM DS_19 t0);" + "CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);" + "CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);" + "CREATE INDEX DS_r_ID_3_IDX ON DS_r (ID_3);" + "CREATE INDEX DS_r_ID_4_IDX ON DS_r (ID_4);";
        } else if (profileActive.equalsIgnoreCase("sqlServer")) {
                    comandSqlResult = "SELECT t1.* INTO DS_r FROM (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,t0.ID_3 AS ID_3,t0.ID_4 AS ID_4,dbo.vtl_xor_bool(t0.ME_1,'true') AS ME_1 FROM DS_19 t0) t1;CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);CREATE INDEX DS_r_ID_3_IDX ON DS_r (ID_3);CREATE INDEX DS_r_ID_4_IDX ON DS_r (ID_4);";
        } else if (profileActive.equalsIgnoreCase("mySql")) {
                    comandSqlResult = "CREATE TABLE DS_r AS (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,t0.ID_3 AS ID_3,t0.ID_4 AS ID_4,vtl_xor_bool(t0.ME_1,'true') AS ME_1 FROM DS_19 t0);" + "CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);" + "CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);" + "CREATE INDEX DS_r_ID_3_IDX ON DS_r (ID_3);" + "CREATE INDEX DS_r_ID_4_IDX ON DS_r (ID_4);";
        } else if (profileActive.equalsIgnoreCase("postgreSql")) {
                    comandSqlResult = "CREATE TABLE DS_r AS (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,t0.ID_3 AS ID_3,t0.ID_4 AS ID_4,vtl_xor_bool(t0.ME_1,'true') AS ME_1 FROM DS_19 t0);" + "CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);" + "CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);" + "CREATE INDEX DS_r_ID_3_IDX ON DS_r (ID_3);" + "CREATE INDEX DS_r_ID_4_IDX ON DS_r (ID_4);";
        }
		
		String commandStatements = "DS_r := DS_19 xor true;";
		String result = translationUtilTest.translate(commandStatements);
		System.out.println("Print queryXor: " +result);
		System.out.println("Print queryAttesaXor: " +comandSqlResult);
		assertEquals("Test operator orComponentXor", comandSqlResult, result);
	}

	@Test
	@Transactional
	public void xorDs() throws Exception {
		dbTableUtilityService.resetSchema("t");
		VtlDataset vtlDatasetFind = datasetRepository.findByNameIgnoreCase("DS_1");
		if (vtlDatasetFind == null) {
			VtlDataset vtlDataset = new VtlDataset();

			vtlDataset.setPersistent(true);
			vtlDataset.setName("DS_19");
			vtlDataset.setIsOnlyAScalar(false);
			vtlDataset.setTransitory(false);
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ID_1", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ID_2", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ID_3", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ID_4", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ME_1", VtlType.BOOLEAN, VtlComponentRole.MEASURE));
			datasetRepository.save(vtlDataset);

			VtlDataset vtlDatasetSecond = new VtlDataset();
			vtlDatasetSecond.setPersistent(true);
			vtlDatasetSecond.setName("DS_20");
			vtlDatasetSecond.setIsOnlyAScalar(false);
			vtlDatasetSecond.setTransitory(false);
			vtlDatasetSecond
					.addComponent(translationUtilTest.getVtlComponent("ID_1", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDatasetSecond
					.addComponent(translationUtilTest.getVtlComponent("ID_2", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDatasetSecond
					.addComponent(translationUtilTest.getVtlComponent("ID_3", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDatasetSecond
					.addComponent(translationUtilTest.getVtlComponent("ID_4", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDatasetSecond
					.addComponent(translationUtilTest.getVtlComponent("ME_1", VtlType.BOOLEAN, VtlComponentRole.MEASURE));
			datasetRepository.save(vtlDatasetSecond);
		}
		
		System.out.println("Il profilo attivo è " + environment.getActiveProfiles()[0]);
        String comandSqlResult = "";
        String profileActive = environment.getActiveProfiles()[0];
        if (profileActive.equalsIgnoreCase("oracleSql")) {
                    comandSqlResult = "CREATE TABLE DS_r AS (SELECT t2.ID_1 AS ID_1,t2.ID_2 AS ID_2,t2.ID_3 AS ID_3,t2.ID_4 AS ID_4,vtl_pkg.xor_bool(t2.ME_1,t1.ME_1) AS ME_1 FROM DS_19 t2 INNER JOIN DS_20 t1 ON (((t2.ID_1 = t1.ID_1) AND (t2.ID_2 = t1.ID_2) AND (t2.ID_3 = t1.ID_3) AND (t2.ID_4 = t1.ID_4))));"+"CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);"+"CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);"+"CREATE INDEX DS_r_ID_3_IDX ON DS_r (ID_3);"+"CREATE INDEX DS_r_ID_4_IDX ON DS_r (ID_4);";
        } else if (profileActive.equalsIgnoreCase("sqlServer")) {
                    comandSqlResult = "SELECT t3.* INTO DS_r FROM (SELECT t2.ID_1 AS ID_1,t2.ID_2 AS ID_2,t2.ID_3 AS ID_3,t2.ID_4 AS ID_4,dbo.vtl_xor_bool(t2.ME_1,t1.ME_1) AS ME_1 FROM DS_19 t2 INNER JOIN DS_20 t1 ON (((t2.ID_1 = t1.ID_1) AND (t2.ID_2 = t1.ID_2) AND (t2.ID_3 = t1.ID_3) AND (t2.ID_4 = t1.ID_4)))) t3;CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);CREATE INDEX DS_r_ID_3_IDX ON DS_r (ID_3);CREATE INDEX DS_r_ID_4_IDX ON DS_r (ID_4);";
        } else if (profileActive.equalsIgnoreCase("mySql")) {
                    comandSqlResult = "CREATE TABLE DS_r AS (SELECT t2.ID_1 AS ID_1,t2.ID_2 AS ID_2,t2.ID_3 AS ID_3,t2.ID_4 AS ID_4,vtl_xor_bool(t2.ME_1,t1.ME_1) AS ME_1 FROM DS_19 t2 INNER JOIN DS_20 t1 ON (((t2.ID_1 = t1.ID_1) AND (t2.ID_2 = t1.ID_2) AND (t2.ID_3 = t1.ID_3) AND (t2.ID_4 = t1.ID_4))));"+"CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);"+"CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);"+"CREATE INDEX DS_r_ID_3_IDX ON DS_r (ID_3);"+"CREATE INDEX DS_r_ID_4_IDX ON DS_r (ID_4);";
        } else if (profileActive.equalsIgnoreCase("postgreSql")) {
                    comandSqlResult = "CREATE TABLE DS_r AS (SELECT t2.ID_1 AS ID_1,t2.ID_2 AS ID_2,t2.ID_3 AS ID_3,t2.ID_4 AS ID_4,vtl_xor_bool(t2.ME_1,t1.ME_1) AS ME_1 FROM DS_19 t2 INNER JOIN DS_20 t1 ON (((t2.ID_1 = t1.ID_1) AND (t2.ID_2 = t1.ID_2) AND (t2.ID_3 = t1.ID_3) AND (t2.ID_4 = t1.ID_4))));"+"CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);"+"CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);"+"CREATE INDEX DS_r_ID_3_IDX ON DS_r (ID_3);"+"CREATE INDEX DS_r_ID_4_IDX ON DS_r (ID_4);"; 
        }
		
		String commandStatements = "DS_r := DS_19 xor DS_20;";
		String result = translationUtilTest.translate(commandStatements);
		System.out.println("Print queryXorDs: " +result);
		System.out.println("Print queryAttesaXorDs: " +comandSqlResult);
		assertEquals("Test operator orComponentXorDs", comandSqlResult, result);
	}

	@Test
	@Transactional
	public void xorComponent() throws Exception {
		dbTableUtilityService.resetSchema("t");
		VtlDataset vtlDatasetFind = datasetRepository.findByNameIgnoreCase("DS_1");
		if (vtlDatasetFind == null) {
			VtlDataset vtlDataset = new VtlDataset();

			vtlDataset.setPersistent(true);
			vtlDataset.setName("DS_19");
			vtlDataset.setIsOnlyAScalar(false);
			vtlDataset.setTransitory(false);
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ID_1", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ID_2", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ID_3", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ID_4", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ME_1", VtlType.BOOLEAN, VtlComponentRole.MEASURE));
			datasetRepository.save(vtlDataset);
		}
		
		System.out.println("Il profilo attivo è " + environment.getActiveProfiles()[0]);
        String comandSqlResult = "";
        String profileActive = environment.getActiveProfiles()[0];
        if (profileActive.equalsIgnoreCase("oracleSql")) {
                    comandSqlResult = "CREATE TABLE DS_r AS (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,t0.ID_3 AS ID_3,t0.ID_4 AS ID_4,t0.ME_1 AS ME_1,vtl_pkg.xor_bool(t0.ME_1,'true') AS Me_2 FROM DS_19 t0);" + "CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);" + "CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);" + "CREATE INDEX DS_r_ID_3_IDX ON DS_r (ID_3);" + "CREATE INDEX DS_r_ID_4_IDX ON DS_r (ID_4);";
        } else if (profileActive.equalsIgnoreCase("sqlServer")) {
                    comandSqlResult = "SELECT t1.* INTO DS_r FROM (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,t0.ID_3 AS ID_3,t0.ID_4 AS ID_4,t0.ME_1 AS ME_1,dbo.vtl_xor_bool(t0.ME_1,'true') AS Me_2 FROM DS_19 t0) t1;CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);CREATE INDEX DS_r_ID_3_IDX ON DS_r (ID_3);CREATE INDEX DS_r_ID_4_IDX ON DS_r (ID_4);";
        } else if (profileActive.equalsIgnoreCase("mySql")) {
                    comandSqlResult = "CREATE TABLE DS_r AS (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,t0.ID_3 AS ID_3,t0.ID_4 AS ID_4,t0.ME_1 AS ME_1,vtl_xor_bool(t0.ME_1,'true') AS Me_2 FROM DS_19 t0);" + "CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);" + "CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);" + "CREATE INDEX DS_r_ID_3_IDX ON DS_r (ID_3);" + "CREATE INDEX DS_r_ID_4_IDX ON DS_r (ID_4);";
        } else if (profileActive.equalsIgnoreCase("postgreSql")) {
                    comandSqlResult = "CREATE TABLE DS_r AS (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,t0.ID_3 AS ID_3,t0.ID_4 AS ID_4,t0.ME_1 AS ME_1,vtl_xor_bool(t0.ME_1,'true') AS Me_2 FROM DS_19 t0);" + "CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);" + "CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);" + "CREATE INDEX DS_r_ID_3_IDX ON DS_r (ID_3);" + "CREATE INDEX DS_r_ID_4_IDX ON DS_r (ID_4);";
        }		
		
		String commandStatements = "DS_r := DS_19 [ calc Me_2:= Me_1  xor  true ];";
		String result = translationUtilTest.translate(commandStatements);
		System.out.println("Print queryXorComponent: " +result);
		System.out.println("Print queryAttesaXorComponent: " +comandSqlResult);
		assertEquals("Test operator orComponentXorComponent", comandSqlResult, result);
	}

	@Test
	@Transactional
	public void not() throws Exception {
		dbTableUtilityService.resetSchema("t");
		VtlDataset vtlDatasetFind = datasetRepository.findByNameIgnoreCase("DS_1");
		if (vtlDatasetFind == null) {
			VtlDataset vtlDataset = new VtlDataset();

			vtlDataset.setPersistent(true);
			vtlDataset.setName("DS_19");
			vtlDataset.setIsOnlyAScalar(false);
			vtlDataset.setTransitory(false);
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ID_1", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ID_2", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ID_3", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ID_4", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ME_1", VtlType.BOOLEAN, VtlComponentRole.MEASURE));
			datasetRepository.save(vtlDataset);
		}
		
		System.out.println("Il profilo attivo è " + environment.getActiveProfiles()[0]);
        String comandSqlResult = "";
        String profileActive = environment.getActiveProfiles()[0];
        if (profileActive.equalsIgnoreCase("oracleSql")) {
                    comandSqlResult = "CREATE TABLE DS_r AS (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,t0.ID_3 AS ID_3,t0.ID_4 AS ID_4,vtl_pkg.not_bool(t0.ME_1) AS ME_1 FROM DS_19 t0);"+"CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);"+"CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);"+"CREATE INDEX DS_r_ID_3_IDX ON DS_r (ID_3);"+"CREATE INDEX DS_r_ID_4_IDX ON DS_r (ID_4);";
        } else if (profileActive.equalsIgnoreCase("sqlServer")) {
                    comandSqlResult = "SELECT t1.* INTO DS_r FROM (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,t0.ID_3 AS ID_3,t0.ID_4 AS ID_4,dbo.vtl_not_bool(t0.ME_1) AS ME_1 FROM DS_19 t0) t1;CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);CREATE INDEX DS_r_ID_3_IDX ON DS_r (ID_3);CREATE INDEX DS_r_ID_4_IDX ON DS_r (ID_4);";
        } else if (profileActive.equalsIgnoreCase("mySql")) {
                    comandSqlResult = "CREATE TABLE DS_r AS (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,t0.ID_3 AS ID_3,t0.ID_4 AS ID_4,vtl_not_bool(t0.ME_1) AS ME_1 FROM DS_19 t0);"+"CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);"+"CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);"+"CREATE INDEX DS_r_ID_3_IDX ON DS_r (ID_3);"+"CREATE INDEX DS_r_ID_4_IDX ON DS_r (ID_4);";
        } else if (profileActive.equalsIgnoreCase("postgreSql")) {
                    comandSqlResult = "CREATE TABLE DS_r AS (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,t0.ID_3 AS ID_3,t0.ID_4 AS ID_4,vtl_not_bool(t0.ME_1) AS ME_1 FROM DS_19 t0);"+"CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);"+"CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);"+"CREATE INDEX DS_r_ID_3_IDX ON DS_r (ID_3);"+"CREATE INDEX DS_r_ID_4_IDX ON DS_r (ID_4);";
        }		
		
		String commandStatements = "DS_r := not DS_19;";
		String result = translationUtilTest.translate(commandStatements);
		System.out.println("Print queryNot: " +result);
		System.out.println("Print queryAttesaNot: " +comandSqlResult);
		assertEquals("Test operator orComponentNot", comandSqlResult, result);

	}

	@Test
	@Transactional
	public void notComponent() throws Exception {
		dbTableUtilityService.resetSchema("t");
		VtlDataset vtlDatasetFind = datasetRepository.findByNameIgnoreCase("DS_1");
		if (vtlDatasetFind == null) {
			VtlDataset vtlDataset = new VtlDataset();

			vtlDataset.setPersistent(true);
			vtlDataset.setName("DS_19");
			vtlDataset.setIsOnlyAScalar(false);
			vtlDataset.setTransitory(false);
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ID_1", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ID_2", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ID_3", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ID_4", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ME_1", VtlType.BOOLEAN, VtlComponentRole.MEASURE));
			datasetRepository.save(vtlDataset);
		}
		
		System.out.println("Il profilo attivo è " + environment.getActiveProfiles()[0]);
        String comandSqlResult = "";
        String profileActive = environment.getActiveProfiles()[0];
        if (profileActive.equalsIgnoreCase("oracleSql")) {
                    comandSqlResult = "CREATE TABLE DS_r AS (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,t0.ID_3 AS ID_3,t0.ID_4 AS ID_4,t0.ME_1 AS ME_1,vtl_pkg.not_bool(t0.ME_1) AS Me_2 FROM DS_19 t0);"+"CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);"+"CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);"+"CREATE INDEX DS_r_ID_3_IDX ON DS_r (ID_3);"+"CREATE INDEX DS_r_ID_4_IDX ON DS_r (ID_4);";
        } else if (profileActive.equalsIgnoreCase("sqlServer")) {
                    comandSqlResult = "SELECT t1.* INTO DS_r FROM (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,t0.ID_3 AS ID_3,t0.ID_4 AS ID_4,t0.ME_1 AS ME_1,dbo.vtl_not_bool(t0.ME_1) AS Me_2 FROM DS_19 t0) t1;CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);CREATE INDEX DS_r_ID_3_IDX ON DS_r (ID_3);CREATE INDEX DS_r_ID_4_IDX ON DS_r (ID_4);";
        } else if (profileActive.equalsIgnoreCase("mySql")) {
                    comandSqlResult = "CREATE TABLE DS_r AS (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,t0.ID_3 AS ID_3,t0.ID_4 AS ID_4,t0.ME_1 AS ME_1,vtl_not_bool(t0.ME_1) AS Me_2 FROM DS_19 t0);"+"CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);"+"CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);"+"CREATE INDEX DS_r_ID_3_IDX ON DS_r (ID_3);"+"CREATE INDEX DS_r_ID_4_IDX ON DS_r (ID_4);";
        } else if (profileActive.equalsIgnoreCase("postgreSql")) {
                    comandSqlResult = "CREATE TABLE DS_r AS (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,t0.ID_3 AS ID_3,t0.ID_4 AS ID_4,t0.ME_1 AS ME_1,vtl_not_bool(t0.ME_1) AS Me_2 FROM DS_19 t0);"+"CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);"+"CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);"+"CREATE INDEX DS_r_ID_3_IDX ON DS_r (ID_3);"+"CREATE INDEX DS_r_ID_4_IDX ON DS_r (ID_4);";
        }		
		
		String commandStatements = "DS_r := DS_19 [ calc Me_2 := not Me_1 ];";
		String result = translationUtilTest.translate(commandStatements);
		System.out.println("Print queryNotComponent: " +result);
		System.out.println("Print queryAttesaNotComponent: " +comandSqlResult);
		assertEquals("Test operator orComponentNotComponent", comandSqlResult, result);
	}
}
