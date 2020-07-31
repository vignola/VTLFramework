package com.capgemini.istat.vtlcompiler.vtlapi.traslate;

import com.capgemini.istat.vtlcompiler.vtlapi.utils.TranslationUtilsTestService;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.dataset.VtlComponentRole;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.dataset.VtlDataset;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.dataset.VtlType;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.valuedomain.ValueDomain;
import com.capgemini.istat.vtlcompiler.vtlcommon.repository.DatasetRepository;
import com.capgemini.istat.vtlcompiler.vtlcommon.repository.ValueDomainRepository;
import com.capgemini.istat.vtlcompiler.vtlsqlbuilder.service.utility.DbTableUtilityService;
import org.junit.Assert;
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
public class VtlComparisonOperatorsTranslationTest {

    private TranslationUtilsTestService translationUtilTest;
    
	private DatasetRepository datasetRepository;
        
	private ValueDomainRepository valueDomainRepository;
        
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
        public void setValueDomainRepository(ValueDomainRepository valueDomainRepository) {
            this.valueDomainRepository = valueDomainRepository;
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
	public void equal() throws Exception {
		dbTableUtilityService.resetSchema("t");
		VtlDataset vtlDatasetFind = datasetRepository.findByNameIgnoreCase("DS_1");
		if (vtlDatasetFind == null) {
			VtlDataset vtlDataset = new VtlDataset();

			vtlDataset.setPersistent(true);
			vtlDataset.setName("DS_11");
			vtlDataset.setIsOnlyAScalar(false);
			vtlDataset.setTransitory(false);
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ID_1", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ID_2", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ID_3", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ID_4", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ME_1", VtlType.NUMBER, VtlComponentRole.MEASURE));
			datasetRepository.save(vtlDataset);
		}
		
		System.out.println("Il profilo attivo è " + environment.getActiveProfiles()[0]);
		String comandSqlResult = "";
		String profileActive = environment.getActiveProfiles()[0];
		if (profileActive.equalsIgnoreCase("oracleSql")) {
                    comandSqlResult = "CREATE TABLE DS_r AS (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,t0.ID_3 AS ID_3,t0.ID_4 AS ID_4,(CASE WHEN (t0.ME_1=8) THEN 'TRUE' ELSE 'FALSE' END) AS bool_var FROM DS_11 t0);"+"CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);"+"CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);"+"CREATE INDEX DS_r_ID_3_IDX ON DS_r (ID_3);"+"CREATE INDEX DS_r_ID_4_IDX ON DS_r (ID_4);"; 
		} else if (profileActive.equalsIgnoreCase("sqlServer")) {
                    comandSqlResult = "SELECT t1.* INTO DS_r FROM (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,t0.ID_3 AS ID_3,t0.ID_4 AS ID_4,(CASE WHEN (t0.ME_1=8) THEN 'TRUE' ELSE 'FALSE' END) AS bool_var FROM DS_11 t0) t1;CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);"+"CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);"+"CREATE INDEX DS_r_ID_3_IDX ON DS_r (ID_3);"+"CREATE INDEX DS_r_ID_4_IDX ON DS_r (ID_4);"; 
		} else if (profileActive.equalsIgnoreCase("mySql")) {
                    comandSqlResult = "CREATE TABLE DS_r AS (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,t0.ID_3 AS ID_3,t0.ID_4 AS ID_4,(CASE WHEN (t0.ME_1=8) THEN 'TRUE' ELSE 'FALSE' END) AS bool_var FROM DS_11 t0);"+"CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);"+"CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);"+"CREATE INDEX DS_r_ID_3_IDX ON DS_r (ID_3);"+"CREATE INDEX DS_r_ID_4_IDX ON DS_r (ID_4);"; 
		} else if (profileActive.equalsIgnoreCase("postgreSql")) {
                    comandSqlResult = "CREATE TABLE DS_r AS (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,t0.ID_3 AS ID_3,t0.ID_4 AS ID_4,(CASE WHEN (t0.ME_1=8) THEN 'TRUE' ELSE 'FALSE' END) AS bool_var FROM DS_11 t0);"+"CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);"+"CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);"+"CREATE INDEX DS_r_ID_3_IDX ON DS_r (ID_3);"+"CREATE INDEX DS_r_ID_4_IDX ON DS_r (ID_4);"; 
		}
		
		String commandStatements = "DS_r := DS_11 = 8;";
		String result = translationUtilTest.translate(commandStatements);
		System.out.println("Print queryEqual: " +result);
		System.out.println("Print queryAttesaEqual: " +comandSqlResult);
		assertEquals("Test operator Equal", comandSqlResult, result); 

		
	}

	@Test
	@Transactional
	public void equalDs() throws Exception {
		dbTableUtilityService.resetSchema("t");
		VtlDataset vtlDatasetFind = datasetRepository.findByNameIgnoreCase("DS_1");
		if (vtlDatasetFind == null) {
			VtlDataset vtlDataset = new VtlDataset();

			vtlDataset.setPersistent(true);
			vtlDataset.setName("DS_11");
			vtlDataset.setIsOnlyAScalar(false);
			vtlDataset.setTransitory(false);
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ID_1", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ID_2", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ID_3", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ID_4", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ME_1", VtlType.NUMBER, VtlComponentRole.MEASURE));
			datasetRepository.save(vtlDataset);

			VtlDataset vtlDatasetSecond = new VtlDataset();
			vtlDatasetSecond.setPersistent(true);
			vtlDatasetSecond.setName("DS_12");
			vtlDatasetSecond.setIsOnlyAScalar(false);
			vtlDatasetSecond.setTransitory(false);
			vtlDatasetSecond
					.addComponent(translationUtilTest.getVtlComponent("ID_1", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDatasetSecond
					.addComponent(translationUtilTest.getVtlComponent("ID_2", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDatasetSecond
					.addComponent(translationUtilTest.getVtlComponent("ID_4", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDatasetSecond
					.addComponent(translationUtilTest.getVtlComponent("ME_1", VtlType.NUMBER, VtlComponentRole.MEASURE));
			datasetRepository.save(vtlDatasetSecond);
		}
		
		System.out.println("Il profilo attivo è " + environment.getActiveProfiles()[0]);
		String comandSqlResult = "";
		String profileActive = environment.getActiveProfiles()[0];
		if (profileActive.equalsIgnoreCase("oracleSql")) {
                    comandSqlResult = "CREATE TABLE DS_r AS (SELECT t2.ID_1 AS ID_1,t2.ID_2 AS ID_2,t2.ID_3 AS ID_3,t2.ID_4 AS ID_4,(CASE WHEN (t2.ME_1=t1.ME_1) THEN 'TRUE' ELSE 'FALSE' END) AS bool_var FROM DS_11 t2 INNER JOIN DS_12 t1 ON (((t2.ID_1 = t1.ID_1) AND (t2.ID_2 = t1.ID_2) AND (t2.ID_4 = t1.ID_4))));"+"CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);"+"CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);"+"CREATE INDEX DS_r_ID_3_IDX ON DS_r (ID_3);"+"CREATE INDEX DS_r_ID_4_IDX ON DS_r (ID_4);";  
		} else if (profileActive.equalsIgnoreCase("sqlServer")) {
                    comandSqlResult = "SELECT t3.* INTO DS_r FROM (SELECT t2.ID_1 AS ID_1,t2.ID_2 AS ID_2,t2.ID_3 AS ID_3,t2.ID_4 AS ID_4,(CASE WHEN (t2.ME_1=t1.ME_1) THEN 'TRUE' ELSE 'FALSE' END) AS bool_var FROM DS_11 t2 INNER JOIN DS_12 t1 ON (((t2.ID_1 = t1.ID_1) AND (t2.ID_2 = t1.ID_2) AND (t2.ID_4 = t1.ID_4)))) t3;CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);"+"CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);"+"CREATE INDEX DS_r_ID_3_IDX ON DS_r (ID_3);"+"CREATE INDEX DS_r_ID_4_IDX ON DS_r (ID_4);"; 
		} else if (profileActive.equalsIgnoreCase("mySql")) {
                    comandSqlResult = "CREATE TABLE DS_r AS (SELECT t2.ID_1 AS ID_1,t2.ID_2 AS ID_2,t2.ID_3 AS ID_3,t2.ID_4 AS ID_4,(CASE WHEN (t2.ME_1=t1.ME_1) THEN 'TRUE' ELSE 'FALSE' END) AS bool_var FROM DS_11 t2 INNER JOIN DS_12 t1 ON (((t2.ID_1 = t1.ID_1) AND (t2.ID_2 = t1.ID_2) AND (t2.ID_4 = t1.ID_4))));"+"CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);"+"CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);"+"CREATE INDEX DS_r_ID_3_IDX ON DS_r (ID_3);"+"CREATE INDEX DS_r_ID_4_IDX ON DS_r (ID_4);";  
		} else if (profileActive.equalsIgnoreCase("postgreSql")) {
                    comandSqlResult = "CREATE TABLE DS_r AS (SELECT t2.ID_1 AS ID_1,t2.ID_2 AS ID_2,t2.ID_3 AS ID_3,t2.ID_4 AS ID_4,(CASE WHEN (t2.ME_1=t1.ME_1) THEN 'TRUE' ELSE 'FALSE' END) AS bool_var FROM DS_11 t2 INNER JOIN DS_12 t1 ON (((t2.ID_1 = t1.ID_1) AND (t2.ID_2 = t1.ID_2) AND (t2.ID_4 = t1.ID_4))));"+"CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);"+"CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);"+"CREATE INDEX DS_r_ID_3_IDX ON DS_r (ID_3);"+"CREATE INDEX DS_r_ID_4_IDX ON DS_r (ID_4);";  
		}
		
		String commandStatements = "DS_r := DS_11 = DS_12;";
		String result = translationUtilTest.translate(commandStatements);
		System.out.println("Print queryEqualDs: " +result);
		System.out.println("Print queryAttesaEqualDs: " +comandSqlResult);
		assertEquals("Test operator EqualDs", comandSqlResult, result);

	}

	@Test
	@Transactional
	public void equalComponent() throws Exception {
		dbTableUtilityService.resetSchema("t");
		VtlDataset vtlDatasetFind = datasetRepository.findByNameIgnoreCase("DS_1");
		if (vtlDatasetFind == null) {
			VtlDataset vtlDataset = new VtlDataset();

			vtlDataset.setPersistent(true);
			vtlDataset.setName("DS_11");
			vtlDataset.setIsOnlyAScalar(false);
			vtlDataset.setTransitory(false);
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ID_1", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ID_2", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ID_3", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ID_4", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ME_1", VtlType.NUMBER, VtlComponentRole.MEASURE));
			datasetRepository.save(vtlDataset);
		}
		
		System.out.println("Il profilo attivo è " + environment.getActiveProfiles()[0]);
		String comandSqlResult = "";
		String profileActive = environment.getActiveProfiles()[0];
		if (profileActive.equalsIgnoreCase("oracleSql")) {
                    comandSqlResult = "CREATE TABLE DS_r AS (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,t0.ID_3 AS ID_3,t0.ID_4 AS ID_4,t0.ME_1 AS ME_1,(CASE WHEN (t0.ME_1=0.08) THEN 'TRUE' ELSE 'FALSE' END) AS Me_2 FROM DS_11 t0);"+"CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);"+"CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);"+"CREATE INDEX DS_r_ID_3_IDX ON DS_r (ID_3);"+"CREATE INDEX DS_r_ID_4_IDX ON DS_r (ID_4);";  
		} else if (profileActive.equalsIgnoreCase("sqlServer")) {
                    comandSqlResult = "SELECT t1.* INTO DS_r FROM (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,t0.ID_3 AS ID_3,t0.ID_4 AS ID_4,t0.ME_1 AS ME_1,(CASE WHEN (t0.ME_1=0.08) THEN 'TRUE' ELSE 'FALSE' END) AS Me_2 FROM DS_11 t0) t1;CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);"+"CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);"+"CREATE INDEX DS_r_ID_3_IDX ON DS_r (ID_3);"+"CREATE INDEX DS_r_ID_4_IDX ON DS_r (ID_4);"; 
		} else if (profileActive.equalsIgnoreCase("mySql")) {
                    comandSqlResult = "CREATE TABLE DS_r AS (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,t0.ID_3 AS ID_3,t0.ID_4 AS ID_4,t0.ME_1 AS ME_1,(CASE WHEN (t0.ME_1=0.08) THEN 'TRUE' ELSE 'FALSE' END) AS Me_2 FROM DS_11 t0);"+"CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);"+"CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);"+"CREATE INDEX DS_r_ID_3_IDX ON DS_r (ID_3);"+"CREATE INDEX DS_r_ID_4_IDX ON DS_r (ID_4);"; 
		} else if (profileActive.equalsIgnoreCase("postgreSql")) {
                    comandSqlResult = "CREATE TABLE DS_r AS (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,t0.ID_3 AS ID_3,t0.ID_4 AS ID_4,t0.ME_1 AS ME_1,(CASE WHEN (t0.ME_1=0.08) THEN 'TRUE' ELSE 'FALSE' END) AS Me_2 FROM DS_11 t0);"+"CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);"+"CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);"+"CREATE INDEX DS_r_ID_3_IDX ON DS_r (ID_3);"+"CREATE INDEX DS_r_ID_4_IDX ON DS_r (ID_4);";  
		}
		
		String commandStatements = "DS_r := DS_11 [ calc Me_2 := Me_1 = 0.08 ];";
//		String comandSqlResult = "CREATE TABLE DS_r AS (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,t0.ID_3 AS ID_3,t0.ID_4 AS ID_4,t0.ME_1 AS ME_1,(CASE WHEN (t0.ME_1=0.08) THEN 'TRUE' ELSE 'FALSE' END) AS Me_2 FROM DS_11 t0);"+"CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);"+"CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);"+"CREATE INDEX DS_r_ID_3_IDX ON DS_r (ID_3);"+"CREATE INDEX DS_r_ID_4_IDX ON DS_r (ID_4);"; 
		String result = translationUtilTest.translate(commandStatements);
		System.out.println("Print queryEqualComponent: " +result);
		System.out.println("Print queryAttesaEqualComponent: " +comandSqlResult);
		assertEquals("Test operator EqualComponent", comandSqlResult, result);

	}

	@Test
	@Transactional
	public void notEqualTo() throws Exception {
		dbTableUtilityService.resetSchema("t");
		VtlDataset vtlDatasetFind = datasetRepository.findByNameIgnoreCase("DS_1");
		if (vtlDatasetFind == null) {
			VtlDataset vtlDataset = new VtlDataset();

			vtlDataset.setPersistent(true);
			vtlDataset.setName("DS_13");
			vtlDataset.setIsOnlyAScalar(false);
			vtlDataset.setTransitory(false);
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ID_1", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ID_2", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ID_3", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ID_4", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ME_1", VtlType.NUMBER, VtlComponentRole.MEASURE));
			datasetRepository.save(vtlDataset);
		}
		
		System.out.println("Il profilo attivo è " + environment.getActiveProfiles()[0]);
		String comandSqlResult = "";
		String profileActive = environment.getActiveProfiles()[0];
		if (profileActive.equalsIgnoreCase("oracleSql")) {
                    comandSqlResult = "CREATE TABLE DS_r AS (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,t0.ID_3 AS ID_3,t0.ID_4 AS ID_4,(CASE WHEN (t0.ME_1<>3) THEN 'TRUE' ELSE 'FALSE' END) AS bool_var FROM DS_13 t0);"+"CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);"+"CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);"+"CREATE INDEX DS_r_ID_3_IDX ON DS_r (ID_3);"+"CREATE INDEX DS_r_ID_4_IDX ON DS_r (ID_4);";   
		} else if (profileActive.equalsIgnoreCase("sqlServer")) {
                    comandSqlResult = "SELECT t1.* INTO DS_r FROM (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,t0.ID_3 AS ID_3,t0.ID_4 AS ID_4,(CASE WHEN (t0.ME_1<>3) THEN 'TRUE' ELSE 'FALSE' END) AS bool_var FROM DS_13 t0) t1;CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);"+"CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);"+"CREATE INDEX DS_r_ID_3_IDX ON DS_r (ID_3);"+"CREATE INDEX DS_r_ID_4_IDX ON DS_r (ID_4);"; 
		} else if (profileActive.equalsIgnoreCase("mySql")) {
                    comandSqlResult = "CREATE TABLE DS_r AS (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,t0.ID_3 AS ID_3,t0.ID_4 AS ID_4,(CASE WHEN (t0.ME_1<>3) THEN 'TRUE' ELSE 'FALSE' END) AS bool_var FROM DS_13 t0);"+"CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);"+"CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);"+"CREATE INDEX DS_r_ID_3_IDX ON DS_r (ID_3);"+"CREATE INDEX DS_r_ID_4_IDX ON DS_r (ID_4);";  
		} else if (profileActive.equalsIgnoreCase("postgreSql")) {
                    comandSqlResult = "CREATE TABLE DS_r AS (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,t0.ID_3 AS ID_3,t0.ID_4 AS ID_4,(CASE WHEN (t0.ME_1<>3) THEN 'TRUE' ELSE 'FALSE' END) AS bool_var FROM DS_13 t0);"+"CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);"+"CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);"+"CREATE INDEX DS_r_ID_3_IDX ON DS_r (ID_3);"+"CREATE INDEX DS_r_ID_4_IDX ON DS_r (ID_4);";   
		}
		
		String commandStatements = "DS_r := DS_13 <>  3;";
		String result = translationUtilTest.translate(commandStatements);
		System.out.println("Print queryNotEqualTo: " +result);
		System.out.println("Print queryAttesaNotEqualTo: " +comandSqlResult);
		assertEquals("Test operator NotEqualTo", comandSqlResult, result);

	}

	@Test
	@Transactional
	public void notEqualToString() throws Exception {
		dbTableUtilityService.resetSchema("t");
		System.out.println("Il profilo attivo è " + environment.getActiveProfiles()[0]);
		String comandSqlResult = "";
		String profileActive = environment.getActiveProfiles()[0];
		if (profileActive.equalsIgnoreCase("oracleSql")) {
                    comandSqlResult = "CREATE TABLE DS_r AS (SELECT (CASE WHEN ('hello'<>'hi') THEN 'TRUE' ELSE 'FALSE' END) AS bool_var FROM DUAL);";   
		} else if (profileActive.equalsIgnoreCase("sqlServer")) {
                    comandSqlResult = "SELECT t0.* INTO DS_r FROM (SELECT (CASE WHEN ('hello'<>'hi') THEN 'TRUE' ELSE 'FALSE' END) AS bool_var) t0;"; 
		} else if (profileActive.equalsIgnoreCase("mySql")) {
                    comandSqlResult = "CREATE TABLE DS_r AS (SELECT (CASE WHEN ('hello'<>'hi') THEN 'TRUE' ELSE 'FALSE' END) AS bool_var FROM DUAL);";  
		} else if (profileActive.equalsIgnoreCase("postgreSql")) {
                    comandSqlResult = "CREATE TABLE DS_r AS (SELECT (CASE WHEN ('hello'<>'hi') THEN 'TRUE' ELSE 'FALSE' END) AS bool_var);";   
		}
		
		String commandStatements = "DS_r := \"hello\" <> \"hi\";";
		String result = translationUtilTest.translate(commandStatements);
		System.out.println("Print queryNotEqualToString: " +result);
		System.out.println("Print queryAttesaNotEqualToString: " +comandSqlResult);
		assertEquals("Test operator NotEqualToString", comandSqlResult, result);
	}

	@Test
	@Transactional
	public void notEqualToDs() throws Exception {
		dbTableUtilityService.resetSchema("t");
		VtlDataset vtlDatasetFind = datasetRepository.findByNameIgnoreCase("DS_1");
		if (vtlDatasetFind == null) {
			VtlDataset vtlDataset = new VtlDataset();

			vtlDataset.setPersistent(true);
			vtlDataset.setName("DS_13");
			vtlDataset.setIsOnlyAScalar(false);
			vtlDataset.setTransitory(false);
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ID_1", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ID_2", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ID_3", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ID_4", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ME_1", VtlType.NUMBER, VtlComponentRole.MEASURE));
			datasetRepository.save(vtlDataset);

			VtlDataset vtlDatasetSecond = new VtlDataset();
			vtlDatasetSecond.setPersistent(true);
			vtlDatasetSecond.setName("DS_14");
			vtlDatasetSecond.setIsOnlyAScalar(false);
			vtlDatasetSecond.setTransitory(false);
			vtlDatasetSecond
					.addComponent(translationUtilTest.getVtlComponent("ID_1", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDatasetSecond
					.addComponent(translationUtilTest.getVtlComponent("ID_2", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDatasetSecond
					.addComponent(translationUtilTest.getVtlComponent("ID_3", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDatasetSecond
					.addComponent(translationUtilTest.getVtlComponent("ID_4", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDatasetSecond
					.addComponent(translationUtilTest.getVtlComponent("ME_1", VtlType.NUMBER, VtlComponentRole.MEASURE));
			datasetRepository.save(vtlDatasetSecond);

		}
		
		System.out.println("Il profilo attivo è " + environment.getActiveProfiles()[0]);
		String comandSqlResult = "";
		String profileActive = environment.getActiveProfiles()[0];
		if (profileActive.equalsIgnoreCase("oracleSql")) {
                    comandSqlResult = "CREATE TABLE DS_r AS (SELECT t2.ID_1 AS ID_1,t2.ID_2 AS ID_2,t2.ID_3 AS ID_3,t2.ID_4 AS ID_4,(CASE WHEN (t2.ME_1<>t1.ME_1) THEN 'TRUE' ELSE 'FALSE' END) AS bool_var FROM DS_13 t2 INNER JOIN DS_14 t1 ON (((t2.ID_1 = t1.ID_1) AND (t2.ID_2 = t1.ID_2) AND (t2.ID_3 = t1.ID_3) AND (t2.ID_4 = t1.ID_4))));"+"CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);"+"CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);"+"CREATE INDEX DS_r_ID_3_IDX ON DS_r (ID_3);"+"CREATE INDEX DS_r_ID_4_IDX ON DS_r (ID_4);";   
		} else if (profileActive.equalsIgnoreCase("sqlServer")) {
                    comandSqlResult = "SELECT t3.* INTO DS_r FROM (SELECT t2.ID_1 AS ID_1,t2.ID_2 AS ID_2,t2.ID_3 AS ID_3,t2.ID_4 AS ID_4,(CASE WHEN (t2.ME_1<>t1.ME_1) THEN 'TRUE' ELSE 'FALSE' END) AS bool_var FROM DS_13 t2 INNER JOIN DS_14 t1 ON (((t2.ID_1 = t1.ID_1) AND (t2.ID_2 = t1.ID_2) AND (t2.ID_3 = t1.ID_3) AND (t2.ID_4 = t1.ID_4)))) t3;"+"CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);"+"CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);"+"CREATE INDEX DS_r_ID_3_IDX ON DS_r (ID_3);"+"CREATE INDEX DS_r_ID_4_IDX ON DS_r (ID_4);";
		} else if (profileActive.equalsIgnoreCase("mySql")) {
                    comandSqlResult = "CREATE TABLE DS_r AS (SELECT t2.ID_1 AS ID_1,t2.ID_2 AS ID_2,t2.ID_3 AS ID_3,t2.ID_4 AS ID_4,(CASE WHEN (t2.ME_1<>t1.ME_1) THEN 'TRUE' ELSE 'FALSE' END) AS bool_var FROM DS_13 t2 INNER JOIN DS_14 t1 ON (((t2.ID_1 = t1.ID_1) AND (t2.ID_2 = t1.ID_2) AND (t2.ID_3 = t1.ID_3) AND (t2.ID_4 = t1.ID_4))));"+"CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);"+"CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);"+"CREATE INDEX DS_r_ID_3_IDX ON DS_r (ID_3);"+"CREATE INDEX DS_r_ID_4_IDX ON DS_r (ID_4);";  
		} else if (profileActive.equalsIgnoreCase("postgreSql")) {
                    comandSqlResult = "CREATE TABLE DS_r AS (SELECT t2.ID_1 AS ID_1,t2.ID_2 AS ID_2,t2.ID_3 AS ID_3,t2.ID_4 AS ID_4,(CASE WHEN (t2.ME_1<>t1.ME_1) THEN 'TRUE' ELSE 'FALSE' END) AS bool_var FROM DS_13 t2 INNER JOIN DS_14 t1 ON (((t2.ID_1 = t1.ID_1) AND (t2.ID_2 = t1.ID_2) AND (t2.ID_3 = t1.ID_3) AND (t2.ID_4 = t1.ID_4))));"+"CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);"+"CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);"+"CREATE INDEX DS_r_ID_3_IDX ON DS_r (ID_3);"+"CREATE INDEX DS_r_ID_4_IDX ON DS_r (ID_4);";   
		}
		
		String commandStatements = "DS_r := DS_13 <>  DS_14;";
		String result = translationUtilTest.translate(commandStatements);
		System.out.println("Print queryNotEqualToDs: " +result);
		System.out.println("Print queryAttesaNotEqualToDs: " +comandSqlResult);
		assertEquals("Test operator NotEqualToDs", comandSqlResult, result);

	}
	
	@Test
	@Transactional
	public void notEqualToComponent() throws Exception {
		dbTableUtilityService.resetSchema("t");
		VtlDataset vtlDatasetFind = datasetRepository.findByNameIgnoreCase("DS_1");
		if (vtlDatasetFind == null) {
			VtlDataset vtlDataset = new VtlDataset();

			vtlDataset.setPersistent(true);
			vtlDataset.setName("DS_13");
			vtlDataset.setIsOnlyAScalar(false);
			vtlDataset.setTransitory(false);
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ID_1", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ID_2", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ID_3", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ID_4", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ME_1", VtlType.NUMBER, VtlComponentRole.MEASURE));
			datasetRepository.save(vtlDataset);
		}
		
		System.out.println("Il profilo attivo è " + environment.getActiveProfiles()[0]);
		String comandSqlResult = "";
		String profileActive = environment.getActiveProfiles()[0];
		if (profileActive.equalsIgnoreCase("oracleSql")) {
                    comandSqlResult = "CREATE TABLE DS_r AS (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,t0.ID_3 AS ID_3,t0.ID_4 AS ID_4,t0.ME_1 AS ME_1,(CASE WHEN (t0.ME_1<>7.5) THEN 'TRUE' ELSE 'FALSE' END) AS Me_2 FROM DS_13 t0);"+"CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);"+"CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);"+"CREATE INDEX DS_r_ID_3_IDX ON DS_r (ID_3);"+"CREATE INDEX DS_r_ID_4_IDX ON DS_r (ID_4);";   
		} else if (profileActive.equalsIgnoreCase("sqlServer")) {
                    comandSqlResult = "SELECT t1.* INTO DS_r FROM (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,t0.ID_3 AS ID_3,t0.ID_4 AS ID_4,t0.ME_1 AS ME_1,(CASE WHEN (t0.ME_1<>7.5) THEN 'TRUE' ELSE 'FALSE' END) AS Me_2 FROM DS_13 t0) t1;"+"CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);"+"CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);"+"CREATE INDEX DS_r_ID_3_IDX ON DS_r (ID_3);"+"CREATE INDEX DS_r_ID_4_IDX ON DS_r (ID_4);";
		} else if (profileActive.equalsIgnoreCase("mySql")) {
                    comandSqlResult = "CREATE TABLE DS_r AS (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,t0.ID_3 AS ID_3,t0.ID_4 AS ID_4,t0.ME_1 AS ME_1,(CASE WHEN (t0.ME_1<>7.5) THEN 'TRUE' ELSE 'FALSE' END) AS Me_2 FROM DS_13 t0);"+"CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);"+"CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);"+"CREATE INDEX DS_r_ID_3_IDX ON DS_r (ID_3);"+"CREATE INDEX DS_r_ID_4_IDX ON DS_r (ID_4);";  
		} else if (profileActive.equalsIgnoreCase("postgreSql")) {
                    comandSqlResult = "CREATE TABLE DS_r AS (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,t0.ID_3 AS ID_3,t0.ID_4 AS ID_4,t0.ME_1 AS ME_1,(CASE WHEN (t0.ME_1<>7.5) THEN 'TRUE' ELSE 'FALSE' END) AS Me_2 FROM DS_13 t0);"+"CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);"+"CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);"+"CREATE INDEX DS_r_ID_3_IDX ON DS_r (ID_3);"+"CREATE INDEX DS_r_ID_4_IDX ON DS_r (ID_4);";   
		}
		
		String commandStatements = "DS_r := DS_13 [ calc Me_2 :=  Me_1<>7.5 ];";
		String result = translationUtilTest.translate(commandStatements);
		System.out.println("Print queryNotEqualToComponent: " +result);
		System.out.println("Print queryAttesaNotEqualToComponent: " +comandSqlResult);
		assertEquals("Test operator NotEqualToComponent", comandSqlResult, result);

	}

	@Test
	@Transactional
	public void between() throws Exception {
	    dbTableUtilityService.resetSchema("t");
		VtlDataset vtlDatasetFind = datasetRepository.findByNameIgnoreCase("DS_1");
		if (vtlDatasetFind == null) {
			VtlDataset vtlDataset = new VtlDataset();
			vtlDataset.setPersistent(true);
			vtlDataset.setName("DS_15");
			vtlDataset.setIsOnlyAScalar(false);
			vtlDataset.setTransitory(false);
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ID_1", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ID_2", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ID_3", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ID_4", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ME_1", VtlType.INTEGER, VtlComponentRole.MEASURE));
			datasetRepository.save(vtlDataset);
		}
		
		System.out.println("Il profilo attivo è " + environment.getActiveProfiles()[0]);
		String comandSqlResult = "";
		String profileActive = environment.getActiveProfiles()[0];
		if (profileActive.equalsIgnoreCase("oracleSql")) {
                    comandSqlResult = "CREATE TABLE DS_r AS (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,t0.ID_3 AS ID_3,t0.ID_4 AS ID_4,(CASE WHEN (t0.ME_1 BETWEEN 5 AND 10) THEN 'TRUE' ELSE 'FALSE' END) AS bool_var FROM DS_15 t0);CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);CREATE INDEX DS_r_ID_3_IDX ON DS_r (ID_3);CREATE INDEX DS_r_ID_4_IDX ON DS_r (ID_4);";   
		} else if (profileActive.equalsIgnoreCase("sqlServer")) {
                    comandSqlResult = "SELECT t1.* INTO DS_r FROM (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,t0.ID_3 AS ID_3,t0.ID_4 AS ID_4,(CASE WHEN (t0.ME_1 BETWEEN 5 AND 10) THEN 'TRUE' ELSE 'FALSE' END) AS bool_var FROM DS_15 t0) t1;"+"CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);"+"CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);"+"CREATE INDEX DS_r_ID_3_IDX ON DS_r (ID_3);"+"CREATE INDEX DS_r_ID_4_IDX ON DS_r (ID_4);";
		} else if (profileActive.equalsIgnoreCase("mySql")) {
                    comandSqlResult = "CREATE TABLE DS_r AS (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,t0.ID_3 AS ID_3,t0.ID_4 AS ID_4,(CASE WHEN (t0.ME_1 BETWEEN 5 AND 10) THEN 'TRUE' ELSE 'FALSE' END) AS bool_var FROM DS_15 t0);CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);CREATE INDEX DS_r_ID_3_IDX ON DS_r (ID_3);CREATE INDEX DS_r_ID_4_IDX ON DS_r (ID_4);";  
		} else if (profileActive.equalsIgnoreCase("postgreSql")) {
                    comandSqlResult = "CREATE TABLE DS_r AS (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,t0.ID_3 AS ID_3,t0.ID_4 AS ID_4,(CASE WHEN (t0.ME_1 BETWEEN 5 AND 10) THEN 'TRUE' ELSE 'FALSE' END) AS bool_var FROM DS_15 t0);CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);CREATE INDEX DS_r_ID_3_IDX ON DS_r (ID_3);CREATE INDEX DS_r_ID_4_IDX ON DS_r (ID_4);";   
		}
		
		String commandStatements = "DS_r := between(DS_15, 5,10);";
		String result = translationUtilTest.translate(commandStatements);
		System.out.println("Print queryBetween: " +result);
		System.out.println("Print queryAttesaBetween: " +comandSqlResult);
		assertEquals("Test operator Between", comandSqlResult, result);
	}

	@Test
	@Transactional
	public void betweenCalc() throws Exception {
	    dbTableUtilityService.resetSchema("t");
		VtlDataset vtlDatasetFind = datasetRepository.findByNameIgnoreCase("DS_1");
		if (vtlDatasetFind == null) {
			VtlDataset vtlDataset = new VtlDataset();
			vtlDataset.setPersistent(true);
			vtlDataset.setName("DS_17");
			vtlDataset.setIsOnlyAScalar(false);
			vtlDataset.setTransitory(false);
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ID_1", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ID_2", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ID_3", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ID_4", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ME_1", VtlType.STRING, VtlComponentRole.MEASURE));
			datasetRepository.save(vtlDataset);
		}
		
		System.out.println("Il profilo attivo è " + environment.getActiveProfiles()[0]);
		String comandSqlResult = "";
		String profileActive = environment.getActiveProfiles()[0];
		if (profileActive.equalsIgnoreCase("oracleSql")) {
                    comandSqlResult = "CREATE TABLE DS_r AS (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,t0.ID_3 AS ID_3,t0.ID_4 AS ID_4,t0.ME_1 AS ME_1,(CASE WHEN (t0.ME_1 BETWEEN 'A' AND 'Z') THEN 'TRUE' ELSE 'FALSE' END) AS ME_X FROM DS_17 t0);CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);CREATE INDEX DS_r_ID_3_IDX ON DS_r (ID_3);CREATE INDEX DS_r_ID_4_IDX ON DS_r (ID_4);";   
		} else if (profileActive.equalsIgnoreCase("sqlServer")) {
                    comandSqlResult = "SELECT t1.* INTO DS_r FROM (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,t0.ID_3 AS ID_3,t0.ID_4 AS ID_4,t0.ME_1 AS ME_1,(CASE WHEN (t0.ME_1 BETWEEN 'A' AND 'Z') THEN 'TRUE' ELSE 'FALSE' END) AS ME_X FROM DS_17 t0) t1;"+"CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);"+"CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);"+"CREATE INDEX DS_r_ID_3_IDX ON DS_r (ID_3);"+"CREATE INDEX DS_r_ID_4_IDX ON DS_r (ID_4);";
		} else if (profileActive.equalsIgnoreCase("mySql")) {
                    comandSqlResult = "CREATE TABLE DS_r AS (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,t0.ID_3 AS ID_3,t0.ID_4 AS ID_4,t0.ME_1 AS ME_1,(CASE WHEN (t0.ME_1 BETWEEN 'A' AND 'Z') THEN 'TRUE' ELSE 'FALSE' END) AS ME_X FROM DS_17 t0);CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);CREATE INDEX DS_r_ID_3_IDX ON DS_r (ID_3);CREATE INDEX DS_r_ID_4_IDX ON DS_r (ID_4);";  
		} else if (profileActive.equalsIgnoreCase("postgreSql")) {
                    comandSqlResult = "CREATE TABLE DS_r AS (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,t0.ID_3 AS ID_3,t0.ID_4 AS ID_4,t0.ME_1 AS ME_1,(CASE WHEN (t0.ME_1 BETWEEN 'A' AND 'Z') THEN 'TRUE' ELSE 'FALSE' END) AS ME_X FROM DS_17 t0);CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);CREATE INDEX DS_r_ID_3_IDX ON DS_r (ID_3);CREATE INDEX DS_r_ID_4_IDX ON DS_r (ID_4);";   
		}
		
		String commandStatements = "DS_r := DS_17 [ calc ME_X := between(ME_1, \"A\", \"Z\") ];";
		String result = translationUtilTest.translate(commandStatements);
		System.out.println("Print queryBetweenCalc: " +result);
		System.out.println("Print queryAttesaBetweenCalc: " +comandSqlResult);
		assertEquals("Test operator Between", comandSqlResult, result);
	}
        
	@Test
	@Transactional
	public void in() throws Exception {
   	dbTableUtilityService.resetSchema("t");
		VtlDataset vtlDatasetFind = datasetRepository.findByNameIgnoreCase("DS_1");
		if (vtlDatasetFind == null) {

			VtlDataset vtlDataset = new VtlDataset();

			vtlDataset.setPersistent(true);
			vtlDataset.setName("DS_17");
			vtlDataset.setIsOnlyAScalar(false);
			vtlDataset.setTransitory(false);
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ID_1", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ID_2", VtlType.STRING, VtlComponentRole.IDENTIFIER));
      	    vtlDataset.addComponent(translationUtilTest.getVtlComponent("ID_3", VtlType.STRING, VtlComponentRole.IDENTIFIER));
	        vtlDataset.addComponent(translationUtilTest.getVtlComponent("ID_4", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ME_1", VtlType.STRING, VtlComponentRole.MEASURE));
			datasetRepository.save(vtlDataset);
		}
		
		System.out.println("Il profilo attivo è " + environment.getActiveProfiles()[0]);
		String comandSqlResult = "";
		String profileActive = environment.getActiveProfiles()[0];
		if (profileActive.equalsIgnoreCase("oracleSql")) {
                    comandSqlResult = "CREATE TABLE DS_r AS (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,t0.ID_3 AS ID_3,t0.ID_4 AS ID_4,(CASE WHEN t0.ME_1 IN ('BS','MO','HH','PP') THEN 'TRUE' ELSE 'FALSE' END) AS bool_var FROM DS_17 t0);"+"CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);"+"CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);"+"CREATE INDEX DS_r_ID_3_IDX ON DS_r (ID_3);"+"CREATE INDEX DS_r_ID_4_IDX ON DS_r (ID_4);";    
		} else if (profileActive.equalsIgnoreCase("sqlServer")) {
                    comandSqlResult = "SELECT t1.* INTO DS_r FROM (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,t0.ID_3 AS ID_3,t0.ID_4 AS ID_4,(CASE WHEN t0.ME_1 IN ('BS','MO','HH','PP') THEN 'TRUE' ELSE 'FALSE' END) AS bool_var FROM DS_17 t0) t1;"+"CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);"+"CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);"+"CREATE INDEX DS_r_ID_3_IDX ON DS_r (ID_3);"+"CREATE INDEX DS_r_ID_4_IDX ON DS_r (ID_4);";
		} else if (profileActive.equalsIgnoreCase("mySql")) {
                    comandSqlResult = "CREATE TABLE DS_r AS (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,t0.ID_3 AS ID_3,t0.ID_4 AS ID_4,(CASE WHEN t0.ME_1 IN ('BS','MO','HH','PP') THEN 'TRUE' ELSE 'FALSE' END) AS bool_var FROM DS_17 t0);"+"CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);"+"CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);"+"CREATE INDEX DS_r_ID_3_IDX ON DS_r (ID_3);"+"CREATE INDEX DS_r_ID_4_IDX ON DS_r (ID_4);";  
		} else if (profileActive.equalsIgnoreCase("postgreSql")) {
                    comandSqlResult = "CREATE TABLE DS_r AS (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,t0.ID_3 AS ID_3,t0.ID_4 AS ID_4,(CASE WHEN t0.ME_1 IN ('BS','MO','HH','PP') THEN 'TRUE' ELSE 'FALSE' END) AS bool_var FROM DS_17 t0);"+"CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);"+"CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);"+"CREATE INDEX DS_r_ID_3_IDX ON DS_r (ID_3);"+"CREATE INDEX DS_r_ID_4_IDX ON DS_r (ID_4);";   
		}
		
		String commandStatements = "DS_r := DS_17 in  { \"BS\", \"MO\", \"HH\", \"PP\" };";
		String result = translationUtilTest.translate(commandStatements);
		System.out.println("Print queryIn: " +result);
		System.out.println("Print queryAttesaIn: " +comandSqlResult);
		assertEquals("Test operator In", comandSqlResult, result);

	}


	@Test
	@Transactional
	public void inInt() throws Exception {
		dbTableUtilityService.resetSchema("t");
		System.out.println("Il profilo attivo è " + environment.getActiveProfiles()[0]);
		String comandSqlResult = "";
		String profileActive = environment.getActiveProfiles()[0];
		if (profileActive.equalsIgnoreCase("oracleSql")) {
                    comandSqlResult = "CREATE TABLE DS_r AS (SELECT (CASE WHEN 1 IN (1,2,3) THEN 'TRUE' ELSE 'FALSE' END) AS bool_var FROM DUAL);";    
		} else if (profileActive.equalsIgnoreCase("sqlServer")) {
                    comandSqlResult = "SELECT t0.* INTO DS_r FROM (SELECT (CASE WHEN 1 IN (1,2,3) THEN 'TRUE' ELSE 'FALSE' END) AS bool_var) t0;"; 
		} else if (profileActive.equalsIgnoreCase("mySql")) {
                    comandSqlResult = "CREATE TABLE DS_r AS (SELECT (CASE WHEN 1 IN (1,2,3) THEN 'TRUE' ELSE 'FALSE' END) AS bool_var FROM DUAL);";  
		} else if (profileActive.equalsIgnoreCase("postgreSql")) {
                    comandSqlResult = "CREATE TABLE DS_r AS (SELECT (CASE WHEN 1 IN (1,2,3) THEN 'TRUE' ELSE 'FALSE' END) AS bool_var);";   
		}
		
		String commandStatements = "DS_r := 1 in { 1, 2, 3 };";
		String result = translationUtilTest.translate(commandStatements);
		System.out.println("Print queryInInt: " +result);
		System.out.println("Print queryAttesaInInt: " +comandSqlResult);
		assertEquals("Test operator InInt", comandSqlResult, result);
	}


	@Test
	@Transactional
	public void inComponent() throws Exception {
	    dbTableUtilityService.resetSchema("t");
		VtlDataset vtlDatasetFind = datasetRepository.findByNameIgnoreCase("DS_1");
		if (vtlDatasetFind == null) {
			VtlDataset vtlDataset = new VtlDataset();

			vtlDataset.setPersistent(true);
			vtlDataset.setName("DS_16");
			vtlDataset.setIsOnlyAScalar(false);
			vtlDataset.setTransitory(false);
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ID_1", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ID_2", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ME_1", VtlType.INTEGER, VtlComponentRole.MEASURE));
			datasetRepository.save(vtlDataset);
		}
		
		System.out.println("Il profilo attivo è " + environment.getActiveProfiles()[0]);
		String comandSqlResult = "";
		String profileActive = environment.getActiveProfiles()[0];
		if (profileActive.equalsIgnoreCase("oracleSql")) {
                    comandSqlResult = "CREATE TABLE DS_r AS (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,(CASE WHEN t0.ME_1 IN (0,3,6,12) THEN 'TRUE' ELSE 'FALSE' END) AS bool_var FROM DS_16 t0);"+"CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);"+"CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);";    
		} else if (profileActive.equalsIgnoreCase("sqlServer")) {
                    comandSqlResult = "SELECT t1.* INTO DS_r FROM (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,(CASE WHEN t0.ME_1 IN (0,3,6,12) THEN 'TRUE' ELSE 'FALSE' END) AS bool_var FROM DS_16 t0) t1;"+"CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);"+"CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);";
		} else if (profileActive.equalsIgnoreCase("mySql")) {
                    comandSqlResult = "CREATE TABLE DS_r AS (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,(CASE WHEN t0.ME_1 IN (0,3,6,12) THEN 'TRUE' ELSE 'FALSE' END) AS bool_var FROM DS_16 t0);"+"CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);"+"CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);";  
		} else if (profileActive.equalsIgnoreCase("postgreSql")) {
                    comandSqlResult = "CREATE TABLE DS_r AS (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,(CASE WHEN t0.ME_1 IN (0,3,6,12) THEN 'TRUE' ELSE 'FALSE' END) AS bool_var FROM DS_16 t0);"+"CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);"+"CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);";   
		}
		
		String commandStatements = "DS_r := DS_16 in { 0, 3, 6, 12 };";
		String result = translationUtilTest.translate(commandStatements);
		System.out.println("Print queryInComponent: " +result);
		System.out.println("Print queryAttesaInComponent: " +comandSqlResult);
		assertEquals("Test operator InComponent", comandSqlResult, result);

	}


	@Test
	@Transactional
	public void inComponentMe() throws Exception {
	    dbTableUtilityService.resetSchema("t");	
		VtlDataset vtlDatasetFind = datasetRepository.findByNameIgnoreCase("DS_1");
		if (vtlDatasetFind == null) {
			VtlDataset vtlDataset = new VtlDataset();

			vtlDataset.setPersistent(true);
			vtlDataset.setName("DS_16");
			vtlDataset.setIsOnlyAScalar(false);
			vtlDataset.setTransitory(false);
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ID_1", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ID_2", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ME_1", VtlType.INTEGER, VtlComponentRole.MEASURE));
			datasetRepository.save(vtlDataset);
		}
		
		System.out.println("Il profilo attivo è " + environment.getActiveProfiles()[0]);
		String comandSqlResult = "";
		String profileActive = environment.getActiveProfiles()[0];
		if (profileActive.equalsIgnoreCase("oracleSql")) {
                    comandSqlResult = "CREATE TABLE DS_r AS (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,t0.ME_1 AS ME_1,(CASE WHEN t0.ME_1 IN (0,3,6,12) THEN 'TRUE' ELSE 'FALSE' END) AS Me_2 FROM DS_16 t0);"+"CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);"+"CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);";    
		} else if (profileActive.equalsIgnoreCase("sqlServer")) {
                    comandSqlResult = "SELECT t1.* INTO DS_r FROM (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,t0.ME_1 AS ME_1,(CASE WHEN t0.ME_1 IN (0,3,6,12) THEN 'TRUE' ELSE 'FALSE' END) AS Me_2 FROM DS_16 t0) t1;"+"CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);"+"CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);";
		} else if (profileActive.equalsIgnoreCase("mySql")) {
                    comandSqlResult = "CREATE TABLE DS_r AS (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,t0.ME_1 AS ME_1,(CASE WHEN t0.ME_1 IN (0,3,6,12) THEN 'TRUE' ELSE 'FALSE' END) AS Me_2 FROM DS_16 t0);"+"CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);"+"CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);";  
		} else if (profileActive.equalsIgnoreCase("postgreSql")) {
                    comandSqlResult = "CREATE TABLE DS_r AS (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,t0.ME_1 AS ME_1,(CASE WHEN t0.ME_1 IN (0,3,6,12) THEN 'TRUE' ELSE 'FALSE' END) AS Me_2 FROM DS_16 t0);"+"CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);"+"CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);";   
		}
		
		String commandStatements = "DS_r := DS_16 [ calc Me_2:= Me_1 in { 0, 3, 6, 12 } ];";
    	String result = translationUtilTest.translate(commandStatements);
		System.out.println("Print queryInComponentMe: " +result);
		System.out.println("Print queryAttesaInComponentMe: " +comandSqlResult);
		assertEquals("Test operator InComponentMe", comandSqlResult, result);

	}


	@Test
	@Transactional
	public void notIn() throws Exception {
    	dbTableUtilityService.resetSchema("t");
		VtlDataset vtlDatasetFind = datasetRepository.findByNameIgnoreCase("DS_1");
		if (vtlDatasetFind == null) {
			VtlDataset vtlDataset = new VtlDataset();

			vtlDataset.setPersistent(true);
			vtlDataset.setName("DS_17");
			vtlDataset.setIsOnlyAScalar(false);
			vtlDataset.setTransitory(false);
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ID_1", VtlType.STRING, VtlComponentRole.IDENTIFIER));
	        vtlDataset.addComponent(translationUtilTest.getVtlComponent("ID_2", VtlType.STRING, VtlComponentRole.IDENTIFIER));
	        vtlDataset.addComponent(translationUtilTest.getVtlComponent("ID_3", VtlType.STRING, VtlComponentRole.IDENTIFIER));
            vtlDataset.addComponent(translationUtilTest.getVtlComponent("ID_4", VtlType.STRING, VtlComponentRole.IDENTIFIER));
	        vtlDataset.addComponent(translationUtilTest.getVtlComponent("ME_1", VtlType.STRING, VtlComponentRole.MEASURE));
	        datasetRepository.save(vtlDataset);
		}
		
		System.out.println("Il profilo attivo è " + environment.getActiveProfiles()[0]);
		String comandSqlResult = "";
		String profileActive = environment.getActiveProfiles()[0];
		if (profileActive.equalsIgnoreCase("oracleSql")) {
                    comandSqlResult = "CREATE TABLE DS_r AS (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,t0.ID_3 AS ID_3,t0.ID_4 AS ID_4,(CASE WHEN t0.ME_1 NOT IN ('BS','MO','HH','PP') THEN 'TRUE' ELSE 'FALSE' END) AS bool_var FROM DS_17 t0);"+"CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);"+"CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);"+"CREATE INDEX DS_r_ID_3_IDX ON DS_r (ID_3);"+"CREATE INDEX DS_r_ID_4_IDX ON DS_r (ID_4);";    
		} else if (profileActive.equalsIgnoreCase("sqlServer")) {
                    comandSqlResult = "SELECT t1.* INTO DS_r FROM (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,t0.ID_3 AS ID_3,t0.ID_4 AS ID_4,(CASE WHEN t0.ME_1 NOT IN ('BS','MO','HH','PP') THEN 'TRUE' ELSE 'FALSE' END) AS bool_var FROM DS_17 t0) t1;"+"CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);"+"CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);"+"CREATE INDEX DS_r_ID_3_IDX ON DS_r (ID_3);"+"CREATE INDEX DS_r_ID_4_IDX ON DS_r (ID_4);";
		} else if (profileActive.equalsIgnoreCase("mySql")) {
                    comandSqlResult = "CREATE TABLE DS_r AS (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,t0.ID_3 AS ID_3,t0.ID_4 AS ID_4,(CASE WHEN t0.ME_1 NOT IN ('BS','MO','HH','PP') THEN 'TRUE' ELSE 'FALSE' END) AS bool_var FROM DS_17 t0);"+"CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);"+"CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);"+"CREATE INDEX DS_r_ID_3_IDX ON DS_r (ID_3);"+"CREATE INDEX DS_r_ID_4_IDX ON DS_r (ID_4);";  
		} else if (profileActive.equalsIgnoreCase("postgreSql")) {
                    comandSqlResult = "CREATE TABLE DS_r AS (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,t0.ID_3 AS ID_3,t0.ID_4 AS ID_4,(CASE WHEN t0.ME_1 NOT IN ('BS','MO','HH','PP') THEN 'TRUE' ELSE 'FALSE' END) AS bool_var FROM DS_17 t0);"+"CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);"+"CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);"+"CREATE INDEX DS_r_ID_3_IDX ON DS_r (ID_3);"+"CREATE INDEX DS_r_ID_4_IDX ON DS_r (ID_4);";   
		}
		
		String commandStatements = "DS_r := DS_17 not_in  { \"BS\", \"MO\", \"HH\", \"PP\" };";
		String result = translationUtilTest.translate(commandStatements);
		System.out.println("Print queryNotIn: " +result);
		System.out.println("Print queryAttesaNotIn: " +comandSqlResult);
		assertEquals("Test operator NotIn", comandSqlResult, result);

	}

	@Test
	@Transactional
	public void matchCharacters() throws Exception {
		dbTableUtilityService.resetSchema("t");
		VtlDataset vtlDatasetFind = datasetRepository.findByNameIgnoreCase("DS_1");
		if (vtlDatasetFind == null) {
			VtlDataset vtlDataset = new VtlDataset();

			vtlDataset.setPersistent(true);
			vtlDataset.setName("DS_17");
			vtlDataset.setIsOnlyAScalar(false);
			vtlDataset.setTransitory(false);
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ID_1", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ID_2", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ID_3", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ID_4", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ME_1", VtlType.STRING, VtlComponentRole.MEASURE));
			datasetRepository.save(vtlDataset);
		}
		
		System.out.println("Il profilo attivo è " + environment.getActiveProfiles()[0]);
		String comandSqlResult = "";
		String profileActive = environment.getActiveProfiles()[0];
		if (profileActive.equalsIgnoreCase("oracleSql")) {
                    comandSqlResult = "CREATE TABLE DS_r AS (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,t0.ID_3 AS ID_3,t0.ID_4 AS ID_4,(CASE WHEN REGEXP_LIKE (t0.ME_1,'[:alpha:]{2}[:digit:]{3}') THEN 'TRUE' ELSE 'FALSE' END) AS bool_var FROM DS_17 t0);CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);CREATE INDEX DS_r_ID_3_IDX ON DS_r (ID_3);CREATE INDEX DS_r_ID_4_IDX ON DS_r (ID_4);";    
		} else if (profileActive.equalsIgnoreCase("mySql")) {
                    comandSqlResult = "CREATE TABLE DS_r AS (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,t0.ID_3 AS ID_3,t0.ID_4 AS ID_4,(CASE WHEN REGEXP_LIKE (t0.ME_1,'[:alpha:]{2}[:digit:]{3}') THEN 'TRUE' ELSE 'FALSE' END) AS bool_var FROM DS_17 t0);CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);CREATE INDEX DS_r_ID_3_IDX ON DS_r (ID_3);CREATE INDEX DS_r_ID_4_IDX ON DS_r (ID_4);";  
		} else if (profileActive.equalsIgnoreCase("postgreSql")) {
                    comandSqlResult = "CREATE TABLE DS_r AS (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,t0.ID_3 AS ID_3,t0.ID_4 AS ID_4,((t0.ME_1) similar to '[:alpha:]{2}[:digit:]{3}') AS bool_var FROM DS_17 t0);CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);CREATE INDEX DS_r_ID_3_IDX ON DS_r (ID_3);CREATE INDEX DS_r_ID_4_IDX ON DS_r (ID_4);";   
		}else if (profileActive.equalsIgnoreCase("sqlServer")) {
            comandSqlResult = "SELECT t1.* INTO DS_r FROM (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,t0.ID_3 AS ID_3,t0.ID_4 AS ID_4,(CASE WHEN dbo.RegexMatch(t0.ME_1,'[A-Za-z][A-Za-z][0-9][0-9][0-9]') <> 'NULL' THEN 'TRUE' ELSE 'FALSE' END) AS bool_var FROM DS_17 t0) t1;CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);CREATE INDEX DS_r_ID_3_IDX ON DS_r (ID_3);CREATE INDEX DS_r_ID_4_IDX ON DS_r (ID_4);";   
		}

		String commandStatements = "DS_r := match_characters(DS_17, \"[:alpha:]{2}[:digit:]{3}\");";
		String result = translationUtilTest.translate(commandStatements);
		System.out.println("Print queryMatchCharacters: " +result);
		System.out.println("Print queryAttesaMatchCharacters: " +comandSqlResult);
		assertEquals("Test operator MatchCharacters", comandSqlResult, result);

	}

	@Test
	@Transactional
	public void existsIn() throws Exception {
		dbTableUtilityService.resetSchema("t");
		VtlDataset vtlDatasetFind = datasetRepository.findByNameIgnoreCase("DS_1");
		if (vtlDatasetFind == null) {
			VtlDataset vtlDataset = new VtlDataset();

			vtlDataset.setPersistent(true);
			vtlDataset.setName("DS_18");
			vtlDataset.setIsOnlyAScalar(false);
			vtlDataset.setTransitory(false);
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ID_1", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ID_2", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ID_3", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ID_4", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ME_1", VtlType.INTEGER, VtlComponentRole.MEASURE));
			datasetRepository.save(vtlDataset);

			VtlDataset vtlDatasetSecond = new VtlDataset();
			vtlDatasetSecond.setPersistent(true);
			vtlDatasetSecond.setName("DS_12");
			vtlDatasetSecond.setIsOnlyAScalar(false);
			vtlDatasetSecond.setTransitory(false);
			vtlDatasetSecond
					.addComponent(translationUtilTest.getVtlComponent("ID_1", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDatasetSecond
					.addComponent(translationUtilTest.getVtlComponent("ID_2", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDatasetSecond
					.addComponent(translationUtilTest.getVtlComponent("ID_4", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDatasetSecond
					.addComponent(translationUtilTest.getVtlComponent("ME_1", VtlType.NUMBER, VtlComponentRole.MEASURE));
			datasetRepository.save(vtlDatasetSecond);

		}
		
		System.out.println("Il profilo attivo è " + environment.getActiveProfiles()[0]);
		String comandSqlResult = "";
		String profileActive = environment.getActiveProfiles()[0];
		if (profileActive.equalsIgnoreCase("oracleSql")) {
                    comandSqlResult = "CREATE TABLE DS_r AS (SELECT t2.ID_1 AS ID_1,t2.ID_2 AS ID_2,t2.ID_3 AS ID_3,t2.ID_4 AS ID_4,(CASE WHEN t1.ID_1 IS NULL THEN 'FALSE' ELSE 'TRUE' END) AS bool_var FROM DS_18 t2 LEFT OUTER JOIN DS_12 t1 ON (((t2.ID_1 = t1.ID_1) AND (t2.ID_2 = t1.ID_2) AND (t2.ID_4 = t1.ID_4))));CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);CREATE INDEX DS_r_ID_3_IDX ON DS_r (ID_3);CREATE INDEX DS_r_ID_4_IDX ON DS_r (ID_4);";
		} else if (profileActive.equalsIgnoreCase("sqlServer")) {
                    comandSqlResult = "SELECT t3.* INTO DS_r FROM (SELECT t2.ID_1 AS ID_1,t2.ID_2 AS ID_2,t2.ID_3 AS ID_3,t2.ID_4 AS ID_4,(CASE WHEN t1.ID_1 IS NULL THEN 'FALSE' ELSE 'TRUE' END) AS bool_var FROM DS_18 t2 LEFT OUTER JOIN DS_12 t1 ON (((t2.ID_1 = t1.ID_1) AND (t2.ID_2 = t1.ID_2) AND (t2.ID_4 = t1.ID_4)))) t3;CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);CREATE INDEX DS_r_ID_3_IDX ON DS_r (ID_3);CREATE INDEX DS_r_ID_4_IDX ON DS_r (ID_4);"; 
		} else if (profileActive.equalsIgnoreCase("mySql")) {
                    comandSqlResult = "CREATE TABLE DS_r AS (SELECT t2.ID_1 AS ID_1,t2.ID_2 AS ID_2,t2.ID_3 AS ID_3,t2.ID_4 AS ID_4,(CASE WHEN t1.ID_1 IS NULL THEN 'FALSE' ELSE 'TRUE' END) AS bool_var FROM DS_18 t2 LEFT OUTER JOIN DS_12 t1 ON (((t2.ID_1 = t1.ID_1) AND (t2.ID_2 = t1.ID_2) AND (t2.ID_4 = t1.ID_4))));CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);CREATE INDEX DS_r_ID_3_IDX ON DS_r (ID_3);CREATE INDEX DS_r_ID_4_IDX ON DS_r (ID_4);";  
		} else if (profileActive.equalsIgnoreCase("postgreSql")) {
                    comandSqlResult = "CREATE TABLE DS_r AS (SELECT t2.ID_1 AS ID_1,t2.ID_2 AS ID_2,t2.ID_3 AS ID_3,t2.ID_4 AS ID_4,(CASE WHEN t1.ID_1 IS NULL THEN 'FALSE' ELSE 'TRUE' END) AS bool_var FROM DS_18 t2 LEFT OUTER JOIN DS_12 t1 ON (((t2.ID_1 = t1.ID_1) AND (t2.ID_2 = t1.ID_2) AND (t2.ID_4 = t1.ID_4))));CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);CREATE INDEX DS_r_ID_3_IDX ON DS_r (ID_3);CREATE INDEX DS_r_ID_4_IDX ON DS_r (ID_4);";   
		}
		
		String commandStatements = "DS_r := exists_in (DS_18, DS_12, all);";
		String result = translationUtilTest.translate(commandStatements);
		System.out.println("Print queryExistsIn: " +result);
		System.out.println("Print queryAttesaExistsIn: " +comandSqlResult);
		assertEquals("Test operator ExistsIn", comandSqlResult, result);

	}

	@Test
	@Transactional
	public void existsInTrue() throws Exception {
		dbTableUtilityService.resetSchema("t");
		VtlDataset vtlDatasetFind = datasetRepository.findByNameIgnoreCase("DS_1");
		if (vtlDatasetFind == null) {
			VtlDataset vtlDataset = new VtlDataset();

			vtlDataset.setPersistent(true);
			vtlDataset.setName("DS_18");
			vtlDataset.setIsOnlyAScalar(false);
			vtlDataset.setTransitory(false);
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ID_1", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ID_2", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ID_3", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ID_4", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ME_1", VtlType.INTEGER, VtlComponentRole.MEASURE));
			datasetRepository.save(vtlDataset);

			VtlDataset vtlDatasetSecond = new VtlDataset();
			vtlDatasetSecond.setPersistent(true);
			vtlDatasetSecond.setName("DS_12");
			vtlDatasetSecond.setIsOnlyAScalar(false);
			vtlDatasetSecond.setTransitory(false);
			vtlDatasetSecond
					.addComponent(translationUtilTest.getVtlComponent("ID_1", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDatasetSecond
					.addComponent(translationUtilTest.getVtlComponent("ID_2", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDatasetSecond
					.addComponent(translationUtilTest.getVtlComponent("ID_4", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDatasetSecond
					.addComponent(translationUtilTest.getVtlComponent("ME_1", VtlType.NUMBER, VtlComponentRole.MEASURE));
			datasetRepository.save(vtlDatasetSecond);
		}
		
		System.out.println("Il profilo attivo è " + environment.getActiveProfiles()[0]);
		String comandSqlResult = "";
		String profileActive = environment.getActiveProfiles()[0];
		if (profileActive.equalsIgnoreCase("oracleSql")) {
                    comandSqlResult = "CREATE TABLE DS_r AS (SELECT t2.ID_1 AS ID_1,t2.ID_2 AS ID_2,t2.ID_3 AS ID_3,t2.ID_4 AS ID_4,(CASE WHEN t1.ID_1 IS NULL THEN 'FALSE' ELSE 'TRUE' END) AS bool_var FROM DS_18 t2 LEFT OUTER JOIN DS_12 t1 ON (((t2.ID_1 = t1.ID_1) AND (t2.ID_2 = t1.ID_2) AND (t2.ID_4 = t1.ID_4))) WHERE (t1.ID_1 IS NOT NULL));CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);CREATE INDEX DS_r_ID_3_IDX ON DS_r (ID_3);CREATE INDEX DS_r_ID_4_IDX ON DS_r (ID_4);";
		} else if (profileActive.equalsIgnoreCase("sqlServer")) {
                    comandSqlResult = "SELECT t3.* INTO DS_r FROM (SELECT t2.ID_1 AS ID_1,t2.ID_2 AS ID_2,t2.ID_3 AS ID_3,t2.ID_4 AS ID_4,(CASE WHEN t1.ID_1 IS NULL THEN 'FALSE' ELSE 'TRUE' END) AS bool_var FROM DS_18 t2 LEFT OUTER JOIN DS_12 t1 ON (((t2.ID_1 = t1.ID_1) AND (t2.ID_2 = t1.ID_2) AND (t2.ID_4 = t1.ID_4))) WHERE (t1.ID_1 IS NOT NULL)) t3;CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);CREATE INDEX DS_r_ID_3_IDX ON DS_r (ID_3);CREATE INDEX DS_r_ID_4_IDX ON DS_r (ID_4);"; 
		} else if (profileActive.equalsIgnoreCase("mySql")) {
                    comandSqlResult = "CREATE TABLE DS_r AS (SELECT t2.ID_1 AS ID_1,t2.ID_2 AS ID_2,t2.ID_3 AS ID_3,t2.ID_4 AS ID_4,(CASE WHEN t1.ID_1 IS NULL THEN 'FALSE' ELSE 'TRUE' END) AS bool_var FROM DS_18 t2 LEFT OUTER JOIN DS_12 t1 ON (((t2.ID_1 = t1.ID_1) AND (t2.ID_2 = t1.ID_2) AND (t2.ID_4 = t1.ID_4))) WHERE (t1.ID_1 IS NOT NULL));CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);CREATE INDEX DS_r_ID_3_IDX ON DS_r (ID_3);CREATE INDEX DS_r_ID_4_IDX ON DS_r (ID_4);";  
		} else if (profileActive.equalsIgnoreCase("postgreSql")) {
                    comandSqlResult = "CREATE TABLE DS_r AS (SELECT t2.ID_1 AS ID_1,t2.ID_2 AS ID_2,t2.ID_3 AS ID_3,t2.ID_4 AS ID_4,(CASE WHEN t1.ID_1 IS NULL THEN 'FALSE' ELSE 'TRUE' END) AS bool_var FROM DS_18 t2 LEFT OUTER JOIN DS_12 t1 ON (((t2.ID_1 = t1.ID_1) AND (t2.ID_2 = t1.ID_2) AND (t2.ID_4 = t1.ID_4))) WHERE (t1.ID_1 IS NOT NULL));CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);CREATE INDEX DS_r_ID_3_IDX ON DS_r (ID_3);CREATE INDEX DS_r_ID_4_IDX ON DS_r (ID_4);";   
		}
		
		String commandStatements = "DS_r := exists_in (DS_18, DS_12, true);";
		String result = translationUtilTest.translate(commandStatements);
		System.out.println("Print queryExistsInTrue: " +result);
		System.out.println("Print queryAttesaExistsInTrue: " +comandSqlResult);
		assertEquals("Test operator ExistsInTrue", comandSqlResult, result);
	}

	@Test
	@Transactional
	public void existsInFalse() throws Exception {
		dbTableUtilityService.resetSchema("t");
		VtlDataset vtlDatasetFind = datasetRepository.findByNameIgnoreCase("DS_1");
		if (vtlDatasetFind == null) {
			VtlDataset vtlDataset = new VtlDataset();

			vtlDataset.setPersistent(true);
			vtlDataset.setName("DS_18");
			vtlDataset.setIsOnlyAScalar(false);
			vtlDataset.setTransitory(false);
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ID_1", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ID_2", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ID_3", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ID_4", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ME_1", VtlType.INTEGER, VtlComponentRole.MEASURE));
			datasetRepository.save(vtlDataset);

			VtlDataset vtlDatasetSecond = new VtlDataset();
			vtlDatasetSecond.setPersistent(true);
			vtlDatasetSecond.setName("DS_12");
			vtlDatasetSecond.setIsOnlyAScalar(false);
			vtlDatasetSecond.setTransitory(false);
			vtlDatasetSecond
					.addComponent(translationUtilTest.getVtlComponent("ID_1", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDatasetSecond
					.addComponent(translationUtilTest.getVtlComponent("ID_2", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDatasetSecond
					.addComponent(translationUtilTest.getVtlComponent("ID_4", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDatasetSecond
					.addComponent(translationUtilTest.getVtlComponent("ME_1", VtlType.NUMBER, VtlComponentRole.MEASURE));
			datasetRepository.save(vtlDatasetSecond);
		}
		
		System.out.println("Il profilo attivo è " + environment.getActiveProfiles()[0]);
		String comandSqlResult = "";
		String profileActive = environment.getActiveProfiles()[0];
		if (profileActive.equalsIgnoreCase("oracleSql")) {
                    comandSqlResult = "CREATE TABLE DS_r AS (SELECT t2.ID_1 AS ID_1,t2.ID_2 AS ID_2,t2.ID_3 AS ID_3,t2.ID_4 AS ID_4,(CASE WHEN t1.ID_1 IS NULL THEN 'FALSE' ELSE 'TRUE' END) AS bool_var FROM DS_18 t2 LEFT OUTER JOIN DS_12 t1 ON (((t2.ID_1 = t1.ID_1) AND (t2.ID_2 = t1.ID_2) AND (t2.ID_4 = t1.ID_4))) WHERE (t1.ID_1 IS NULL));CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);CREATE INDEX DS_r_ID_3_IDX ON DS_r (ID_3);CREATE INDEX DS_r_ID_4_IDX ON DS_r (ID_4);";    
		} else if (profileActive.equalsIgnoreCase("sqlServer")) {
                    comandSqlResult = "SELECT t3.* INTO DS_r FROM (SELECT t2.ID_1 AS ID_1,t2.ID_2 AS ID_2,t2.ID_3 AS ID_3,t2.ID_4 AS ID_4,(CASE WHEN t1.ID_1 IS NULL THEN 'FALSE' ELSE 'TRUE' END) AS bool_var FROM DS_18 t2 LEFT OUTER JOIN DS_12 t1 ON (((t2.ID_1 = t1.ID_1) AND (t2.ID_2 = t1.ID_2) AND (t2.ID_4 = t1.ID_4))) WHERE (t1.ID_1 IS NULL)) t3;CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);CREATE INDEX DS_r_ID_3_IDX ON DS_r (ID_3);CREATE INDEX DS_r_ID_4_IDX ON DS_r (ID_4);";
		} else if (profileActive.equalsIgnoreCase("mySql")) {
                    comandSqlResult = "CREATE TABLE DS_r AS (SELECT t2.ID_1 AS ID_1,t2.ID_2 AS ID_2,t2.ID_3 AS ID_3,t2.ID_4 AS ID_4,(CASE WHEN t1.ID_1 IS NULL THEN 'FALSE' ELSE 'TRUE' END) AS bool_var FROM DS_18 t2 LEFT OUTER JOIN DS_12 t1 ON (((t2.ID_1 = t1.ID_1) AND (t2.ID_2 = t1.ID_2) AND (t2.ID_4 = t1.ID_4))) WHERE (t1.ID_1 IS NULL));CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);CREATE INDEX DS_r_ID_3_IDX ON DS_r (ID_3);CREATE INDEX DS_r_ID_4_IDX ON DS_r (ID_4);";  
		} else if (profileActive.equalsIgnoreCase("postgreSql")) {
                    comandSqlResult = "CREATE TABLE DS_r AS (SELECT t2.ID_1 AS ID_1,t2.ID_2 AS ID_2,t2.ID_3 AS ID_3,t2.ID_4 AS ID_4,(CASE WHEN t1.ID_1 IS NULL THEN 'FALSE' ELSE 'TRUE' END) AS bool_var FROM DS_18 t2 LEFT OUTER JOIN DS_12 t1 ON (((t2.ID_1 = t1.ID_1) AND (t2.ID_2 = t1.ID_2) AND (t2.ID_4 = t1.ID_4))) WHERE (t1.ID_1 IS NULL));CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);CREATE INDEX DS_r_ID_3_IDX ON DS_r (ID_3);CREATE INDEX DS_r_ID_4_IDX ON DS_r (ID_4);";   
		}
		
		String commandStatements = "DS_r := exists_in (DS_18, DS_12, false);";
		String result = translationUtilTest.translate(commandStatements);
		System.out.println("Print queryExistsInFalse: " +result);
		System.out.println("Print queryAttesaExistsInFalse: " +comandSqlResult);
		assertEquals("Test operator ExistsInFalse", comandSqlResult, result);
	}

	@Test
	@Transactional
	public void isNull() throws Exception {
		dbTableUtilityService.resetSchema("t");
		VtlDataset vtlDatasetFind = datasetRepository.findByNameIgnoreCase("DS_1");
		if (vtlDatasetFind == null) {
			VtlDataset vtlDataset = new VtlDataset();

			vtlDataset.setPersistent(true);
			vtlDataset.setName("DS_18");
			vtlDataset.setIsOnlyAScalar(false);
			vtlDataset.setTransitory(false);
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ID_1", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ID_2", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ID_3", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ID_4", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ME_1", VtlType.INTEGER, VtlComponentRole.MEASURE));
			datasetRepository.save(vtlDataset);

		}
		
		System.out.println("Il profilo attivo è " + environment.getActiveProfiles()[0]);
		String comandSqlResult = "";
		String profileActive = environment.getActiveProfiles()[0];
		if (profileActive.equalsIgnoreCase("oracleSql")) {
                    comandSqlResult = "CREATE TABLE DS_r AS (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,t0.ID_3 AS ID_3,t0.ID_4 AS ID_4,(CASE WHEN t0.ME_1 IS NULL THEN 'TRUE' ELSE 'FALSE' END) AS bool_var FROM DS_18 t0);"+"CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);"+"CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);"+"CREATE INDEX DS_r_ID_3_IDX ON DS_r (ID_3);"+"CREATE INDEX DS_r_ID_4_IDX ON DS_r (ID_4);";    
		} else if (profileActive.equalsIgnoreCase("sqlServer")) {
                    comandSqlResult = "SELECT t1.* INTO DS_r FROM (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,t0.ID_3 AS ID_3,t0.ID_4 AS ID_4,(CASE WHEN t0.ME_1 IS NULL THEN 'TRUE' ELSE 'FALSE' END) AS bool_var FROM DS_18 t0) t1;"+"CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);"+"CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);"+"CREATE INDEX DS_r_ID_3_IDX ON DS_r (ID_3);"+"CREATE INDEX DS_r_ID_4_IDX ON DS_r (ID_4);";
		} else if (profileActive.equalsIgnoreCase("mySql")) {
                    comandSqlResult = "CREATE TABLE DS_r AS (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,t0.ID_3 AS ID_3,t0.ID_4 AS ID_4,(CASE WHEN t0.ME_1 IS NULL THEN 'TRUE' ELSE 'FALSE' END) AS bool_var FROM DS_18 t0);"+"CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);"+"CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);"+"CREATE INDEX DS_r_ID_3_IDX ON DS_r (ID_3);"+"CREATE INDEX DS_r_ID_4_IDX ON DS_r (ID_4);";  
		} else if (profileActive.equalsIgnoreCase("postgreSql")) {
                    comandSqlResult = "CREATE TABLE DS_r AS (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,t0.ID_3 AS ID_3,t0.ID_4 AS ID_4,(CASE WHEN t0.ME_1 IS NULL THEN 'TRUE' ELSE 'FALSE' END) AS bool_var FROM DS_18 t0);"+"CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);"+"CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);"+"CREATE INDEX DS_r_ID_3_IDX ON DS_r (ID_3);"+"CREATE INDEX DS_r_ID_4_IDX ON DS_r (ID_4);";  
		}
		
		String commandStatements = "DS_r := isnull(DS_18);";
		String result = translationUtilTest.translate(commandStatements);
		System.out.println("Print queryIsNull: " +result);
		System.out.println("Print queryAttesaIsNull: " +comandSqlResult);
		assertEquals("Test operator IsNull", comandSqlResult, result);

	}

	@Test
	@Transactional
	public void isNullString() throws Exception {
		dbTableUtilityService.resetSchema("t");
		System.out.println("Il profilo attivo è " + environment.getActiveProfiles()[0]);
		String comandSqlResult = "";
		String profileActive = environment.getActiveProfiles()[0];
		if (profileActive.equalsIgnoreCase("oracleSql")) {
                    comandSqlResult = "CREATE TABLE DS_r AS (SELECT (CASE WHEN 'Hello' IS NULL THEN 'TRUE' ELSE 'FALSE' END) AS bool_var FROM DUAL);";    
		} else if (profileActive.equalsIgnoreCase("sqlServer")) {
                    comandSqlResult = "SELECT t0.* INTO DS_r FROM (SELECT (CASE WHEN 'Hello' IS NULL THEN 'TRUE' ELSE 'FALSE' END) AS bool_var) t0;";
		} else if (profileActive.equalsIgnoreCase("mySql")) {
                    comandSqlResult = "CREATE TABLE DS_r AS (SELECT (CASE WHEN 'Hello' IS NULL THEN 'TRUE' ELSE 'FALSE' END) AS bool_var FROM DUAL);";  
		} else if (profileActive.equalsIgnoreCase("postgreSql")) {
                    comandSqlResult = "CREATE TABLE DS_r AS (SELECT (CASE WHEN 'Hello' IS NULL THEN 'TRUE' ELSE 'FALSE' END) AS bool_var);";  
		}
		
		String commandStatements = "DS_r := isnull(\"Hello\");";
		String result = translationUtilTest.translate(commandStatements);
		System.out.println("Print queryIsNullString: " +result);
		System.out.println("Print queryAttesaIsNullString: " +comandSqlResult);
		assertEquals("Test operator IsNullString", comandSqlResult, result);
	}

	@Test
	@Transactional
	public void isNullComponent() throws Exception {
		dbTableUtilityService.resetSchema("t");
		VtlDataset vtlDatasetFind = datasetRepository.findByNameIgnoreCase("DS_1");
		if (vtlDatasetFind == null) {
			VtlDataset vtlDataset = new VtlDataset();

			vtlDataset.setPersistent(true);
			vtlDataset.setName("DS_18");
			vtlDataset.setIsOnlyAScalar(false);
			vtlDataset.setTransitory(false);
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ID_1", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ID_2", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ID_3", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ID_4", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ME_1", VtlType.INTEGER, VtlComponentRole.MEASURE));
			datasetRepository.save(vtlDataset);

		}
		
		System.out.println("Il profilo attivo è " + environment.getActiveProfiles()[0]);
		String comandSqlResult = "";
		String profileActive = environment.getActiveProfiles()[0];
		if (profileActive.equalsIgnoreCase("oracleSql")) {
                    comandSqlResult = "CREATE TABLE DS_r AS (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,t0.ID_3 AS ID_3,t0.ID_4 AS ID_4,t0.ME_1 AS ME_1,(CASE WHEN t0.ME_1 IS NULL THEN 'TRUE' ELSE 'FALSE' END) AS Me_2 FROM DS_18 t0);"+"CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);"+"CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);"+"CREATE INDEX DS_r_ID_3_IDX ON DS_r (ID_3);"+"CREATE INDEX DS_r_ID_4_IDX ON DS_r (ID_4);";    
		} else if (profileActive.equalsIgnoreCase("sqlServer")) {
                    comandSqlResult = "SELECT t1.* INTO DS_r FROM (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,t0.ID_3 AS ID_3,t0.ID_4 AS ID_4,t0.ME_1 AS ME_1,(CASE WHEN t0.ME_1 IS NULL THEN 'TRUE' ELSE 'FALSE' END) AS Me_2 FROM DS_18 t0) t1;"+"CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);"+"CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);"+"CREATE INDEX DS_r_ID_3_IDX ON DS_r (ID_3);"+"CREATE INDEX DS_r_ID_4_IDX ON DS_r (ID_4);";
		} else if (profileActive.equalsIgnoreCase("mySql")) {
                    comandSqlResult = "CREATE TABLE DS_r AS (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,t0.ID_3 AS ID_3,t0.ID_4 AS ID_4,t0.ME_1 AS ME_1,(CASE WHEN t0.ME_1 IS NULL THEN 'TRUE' ELSE 'FALSE' END) AS Me_2 FROM DS_18 t0);"+"CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);"+"CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);"+"CREATE INDEX DS_r_ID_3_IDX ON DS_r (ID_3);"+"CREATE INDEX DS_r_ID_4_IDX ON DS_r (ID_4);";  
		} else if (profileActive.equalsIgnoreCase("postgreSql")) {
                    comandSqlResult = "CREATE TABLE DS_r AS (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,t0.ID_3 AS ID_3,t0.ID_4 AS ID_4,t0.ME_1 AS ME_1,(CASE WHEN t0.ME_1 IS NULL THEN 'TRUE' ELSE 'FALSE' END) AS Me_2 FROM DS_18 t0);"+"CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);"+"CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);"+"CREATE INDEX DS_r_ID_3_IDX ON DS_r (ID_3);"+"CREATE INDEX DS_r_ID_4_IDX ON DS_r (ID_4);";  
		}
		
		String commandStatements = "DS_r := DS_18[ calc Me_2 := isnull(Me_1) ];";
		String result = translationUtilTest.translate(commandStatements);
		System.out.println("Print queryIsNullComponent: " +result);
		System.out.println("Print queryAttesaIsNullComponent: " +comandSqlResult);
		assertEquals("Test operator IsNullComponent", comandSqlResult, result);
	}

	@Test
	@Transactional
	public void greater() throws Exception {
		dbTableUtilityService.resetSchema("t");
		VtlDataset vtlDatasetFind = datasetRepository.findByNameIgnoreCase("DS_1");
		if (vtlDatasetFind == null) {
			VtlDataset vtlDataset = new VtlDataset();

			vtlDataset.setPersistent(true);
			vtlDataset.setName("DS_37");
			vtlDataset.setIsOnlyAScalar(false);
			vtlDataset.setTransitory(false);
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ID_1", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ID_2", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ID_3", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ID_4", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ID_5", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ME_1", VtlType.NUMBER, VtlComponentRole.MEASURE));
			datasetRepository.save(vtlDataset);
		}
		
		System.out.println("Il profilo attivo è " + environment.getActiveProfiles()[0]);
		String comandSqlResult = "";
		String profileActive = environment.getActiveProfiles()[0];
		if (profileActive.equalsIgnoreCase("oracleSql")) {
                    comandSqlResult = "CREATE TABLE DS_r AS (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,t0.ID_3 AS ID_3,t0.ID_4 AS ID_4,t0.ID_5 AS ID_5,(CASE WHEN (t0.ME_1>20) THEN 'TRUE' ELSE 'FALSE' END) AS bool_var FROM DS_37 t0);"+"CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);"+"CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);"+"CREATE INDEX DS_r_ID_3_IDX ON DS_r (ID_3);"+"CREATE INDEX DS_r_ID_4_IDX ON DS_r (ID_4);"+"CREATE INDEX DS_r_ID_5_IDX ON DS_r (ID_5);";    
		} else if (profileActive.equalsIgnoreCase("sqlServer")) {
                    comandSqlResult = "SELECT t1.* INTO DS_r FROM (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,t0.ID_3 AS ID_3,t0.ID_4 AS ID_4,t0.ID_5 AS ID_5,(CASE WHEN (t0.ME_1>20) THEN 'TRUE' ELSE 'FALSE' END) AS bool_var FROM DS_37 t0) t1;"+"CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);"+"CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);"+"CREATE INDEX DS_r_ID_3_IDX ON DS_r (ID_3);"+"CREATE INDEX DS_r_ID_4_IDX ON DS_r (ID_4);"+"CREATE INDEX DS_r_ID_5_IDX ON DS_r (ID_5);";
		} else if (profileActive.equalsIgnoreCase("mySql")) {
                    comandSqlResult = "CREATE TABLE DS_r AS (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,t0.ID_3 AS ID_3,t0.ID_4 AS ID_4,t0.ID_5 AS ID_5,(CASE WHEN (t0.ME_1>20) THEN 'TRUE' ELSE 'FALSE' END) AS bool_var FROM DS_37 t0);"+"CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);"+"CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);"+"CREATE INDEX DS_r_ID_3_IDX ON DS_r (ID_3);"+"CREATE INDEX DS_r_ID_4_IDX ON DS_r (ID_4);"+"CREATE INDEX DS_r_ID_5_IDX ON DS_r (ID_5);";  
		} else if (profileActive.equalsIgnoreCase("postgreSql")) {
                    comandSqlResult = "CREATE TABLE DS_r AS (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,t0.ID_3 AS ID_3,t0.ID_4 AS ID_4,t0.ID_5 AS ID_5,(CASE WHEN (t0.ME_1>20) THEN 'TRUE' ELSE 'FALSE' END) AS bool_var FROM DS_37 t0);"+"CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);"+"CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);"+"CREATE INDEX DS_r_ID_3_IDX ON DS_r (ID_3);"+"CREATE INDEX DS_r_ID_4_IDX ON DS_r (ID_4);"+"CREATE INDEX DS_r_ID_5_IDX ON DS_r (ID_5);";  
		}
		
		String commandStatements = "DS_r := DS_37 > 20;";
    	String result = translationUtilTest.translate(commandStatements);
		System.out.println("Print queryGreater: " +result);
		System.out.println("Print queryAttesaGreater: " +comandSqlResult);
		assertEquals("Test operator Greater", comandSqlResult, result);

	}

	@Test
	@Transactional
	public void greaterEquals() throws Exception {
		dbTableUtilityService.resetSchema("t");
		System.out.println("Il profilo attivo è " + environment.getActiveProfiles()[0]);
		String comandSqlResult = "";
		String profileActive = environment.getActiveProfiles()[0];
		if (profileActive.equalsIgnoreCase("oracleSql")) {
                    comandSqlResult = "CREATE TABLE DS_r AS (SELECT (CASE WHEN (5>=5) THEN 'TRUE' ELSE 'FALSE' END) AS bool_var FROM DUAL);";    
		} else if (profileActive.equalsIgnoreCase("sqlServer")) {
                    comandSqlResult = "SELECT t0.* INTO DS_r FROM (SELECT (CASE WHEN (5>=5) THEN 'TRUE' ELSE 'FALSE' END) AS bool_var) t0;";
		} else if (profileActive.equalsIgnoreCase("mySql")) {
                    comandSqlResult = "CREATE TABLE DS_r AS (SELECT (CASE WHEN (5>=5) THEN 'TRUE' ELSE 'FALSE' END) AS bool_var FROM DUAL);";  
		} else if (profileActive.equalsIgnoreCase("postgreSql")) {
                    comandSqlResult = "CREATE TABLE DS_r AS (SELECT (CASE WHEN (5>=5) THEN 'TRUE' ELSE 'FALSE' END) AS bool_var);";  
		}		
		
		String commandStatements = "DS_r := 5 >= 5;";
		String result = translationUtilTest.translate(commandStatements);
		System.out.println("Print queryGreaterEquals: " +result);
		System.out.println("Print queryAttesaGreaterEquals: " +comandSqlResult);
		assertEquals("Test operator GreaterEquals", comandSqlResult, result);

	}

	@Test
	@Transactional
	public void greaterDs() throws Exception {
		dbTableUtilityService.resetSchema("t");
		VtlDataset vtlDatasetFind = datasetRepository.findByNameIgnoreCase("DS_1");
		if (vtlDatasetFind == null) {
			VtlDataset vtlDataset = new VtlDataset();

			vtlDataset.setPersistent(true);
			vtlDataset.setName("DS_13");
			vtlDataset.setIsOnlyAScalar(false);
			vtlDataset.setTransitory(false);
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ID_1", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ID_2", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ID_3", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ID_4", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ME_1", VtlType.NUMBER, VtlComponentRole.MEASURE));
			datasetRepository.save(vtlDataset);

			VtlDataset vtlDatasetSecond = new VtlDataset();
			vtlDatasetSecond.setPersistent(true);
			vtlDatasetSecond.setName("DS_14");
			vtlDatasetSecond.setIsOnlyAScalar(false);
			vtlDatasetSecond.setTransitory(false);
			vtlDatasetSecond
					.addComponent(translationUtilTest.getVtlComponent("ID_1", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDatasetSecond
					.addComponent(translationUtilTest.getVtlComponent("ID_2", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDatasetSecond
					.addComponent(translationUtilTest.getVtlComponent("ID_3", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDatasetSecond
					.addComponent(translationUtilTest.getVtlComponent("ID_4", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDatasetSecond
					.addComponent(translationUtilTest.getVtlComponent("ME_1", VtlType.NUMBER, VtlComponentRole.MEASURE));
			datasetRepository.save(vtlDatasetSecond);
		}
		
		System.out.println("Il profilo attivo è " + environment.getActiveProfiles()[0]);
		String comandSqlResult = "";
		String profileActive = environment.getActiveProfiles()[0];
		if (profileActive.equalsIgnoreCase("oracleSql")) {
                    comandSqlResult = "CREATE TABLE DS_r AS (SELECT t2.ID_1 AS ID_1,t2.ID_2 AS ID_2,t2.ID_3 AS ID_3,t2.ID_4 AS ID_4,(CASE WHEN (t2.ME_1>t1.ME_1) THEN 'TRUE' ELSE 'FALSE' END) AS bool_var FROM DS_13 t2 INNER JOIN DS_14 t1 ON (((t2.ID_1 = t1.ID_1) AND (t2.ID_2 = t1.ID_2) AND (t2.ID_3 = t1.ID_3) AND (t2.ID_4 = t1.ID_4))));"+"CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);"+"CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);"+"CREATE INDEX DS_r_ID_3_IDX ON DS_r (ID_3);"+"CREATE INDEX DS_r_ID_4_IDX ON DS_r (ID_4);";    
		} else if (profileActive.equalsIgnoreCase("sqlServer")) {
                    comandSqlResult = "SELECT t3.* INTO DS_r FROM (SELECT t2.ID_1 AS ID_1,t2.ID_2 AS ID_2,t2.ID_3 AS ID_3,t2.ID_4 AS ID_4,(CASE WHEN (t2.ME_1>t1.ME_1) THEN 'TRUE' ELSE 'FALSE' END) AS bool_var FROM DS_13 t2 INNER JOIN DS_14 t1 ON (((t2.ID_1 = t1.ID_1) AND (t2.ID_2 = t1.ID_2) AND (t2.ID_3 = t1.ID_3) AND (t2.ID_4 = t1.ID_4)))) t3;"+"CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);"+"CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);"+"CREATE INDEX DS_r_ID_3_IDX ON DS_r (ID_3);"+"CREATE INDEX DS_r_ID_4_IDX ON DS_r (ID_4);";
		} else if (profileActive.equalsIgnoreCase("mySql")) {
                    comandSqlResult = "CREATE TABLE DS_r AS (SELECT t2.ID_1 AS ID_1,t2.ID_2 AS ID_2,t2.ID_3 AS ID_3,t2.ID_4 AS ID_4,(CASE WHEN (t2.ME_1>t1.ME_1) THEN 'TRUE' ELSE 'FALSE' END) AS bool_var FROM DS_13 t2 INNER JOIN DS_14 t1 ON (((t2.ID_1 = t1.ID_1) AND (t2.ID_2 = t1.ID_2) AND (t2.ID_3 = t1.ID_3) AND (t2.ID_4 = t1.ID_4))));"+"CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);"+"CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);"+"CREATE INDEX DS_r_ID_3_IDX ON DS_r (ID_3);"+"CREATE INDEX DS_r_ID_4_IDX ON DS_r (ID_4);";  
		} else if (profileActive.equalsIgnoreCase("postgreSql")) {
                    comandSqlResult = "CREATE TABLE DS_r AS (SELECT t2.ID_1 AS ID_1,t2.ID_2 AS ID_2,t2.ID_3 AS ID_3,t2.ID_4 AS ID_4,(CASE WHEN (t2.ME_1>t1.ME_1) THEN 'TRUE' ELSE 'FALSE' END) AS bool_var FROM DS_13 t2 INNER JOIN DS_14 t1 ON (((t2.ID_1 = t1.ID_1) AND (t2.ID_2 = t1.ID_2) AND (t2.ID_3 = t1.ID_3) AND (t2.ID_4 = t1.ID_4))));"+"CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);"+"CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);"+"CREATE INDEX DS_r_ID_3_IDX ON DS_r (ID_3);"+"CREATE INDEX DS_r_ID_4_IDX ON DS_r (ID_4);";  
		}
		
		String commandStatements = "DS_r := DS_13 > DS_14;";
		String result = translationUtilTest.translate(commandStatements);
		System.out.println("Print queryGreaterDs: " +result);
		System.out.println("Print queryAttesaGreaterDs: " +comandSqlResult);
		assertEquals("Test operator GreaterDs", comandSqlResult, result);

	}

	@Test
	@Transactional
	public void greaterComponent() throws Exception {
		dbTableUtilityService.resetSchema("t");
		VtlDataset vtlDatasetFind = datasetRepository.findByNameIgnoreCase("DS_1");
		if (vtlDatasetFind == null) {
			VtlDataset vtlDataset = new VtlDataset();

			vtlDataset.setPersistent(true);
			vtlDataset.setName("DS_13");
			vtlDataset.setIsOnlyAScalar(false);
			vtlDataset.setTransitory(false);
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ID_1", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ID_2", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ID_3", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ID_4", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ME_1", VtlType.NUMBER, VtlComponentRole.MEASURE));
			datasetRepository.save(vtlDataset);

		}
		
		System.out.println("Il profilo attivo è " + environment.getActiveProfiles()[0]);
		String comandSqlResult = "";
		String profileActive = environment.getActiveProfiles()[0];
		if (profileActive.equalsIgnoreCase("oracleSql")) {
                    comandSqlResult = "CREATE TABLE DS_r AS (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,t0.ID_3 AS ID_3,t0.ID_4 AS ID_4,t0.ME_1 AS ME_1,(CASE WHEN (t0.ME_1>20) THEN 'TRUE' ELSE 'FALSE' END) AS Me_2 FROM DS_13 t0);"+"CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);"+"CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);"+"CREATE INDEX DS_r_ID_3_IDX ON DS_r (ID_3);"+"CREATE INDEX DS_r_ID_4_IDX ON DS_r (ID_4);";    
		} else if (profileActive.equalsIgnoreCase("sqlServer")) {
                    comandSqlResult = "SELECT t1.* INTO DS_r FROM (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,t0.ID_3 AS ID_3,t0.ID_4 AS ID_4,t0.ME_1 AS ME_1,(CASE WHEN (t0.ME_1>20) THEN 'TRUE' ELSE 'FALSE' END) AS Me_2 FROM DS_13 t0) t1;"+"CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);"+"CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);"+"CREATE INDEX DS_r_ID_3_IDX ON DS_r (ID_3);"+"CREATE INDEX DS_r_ID_4_IDX ON DS_r (ID_4);";
		} else if (profileActive.equalsIgnoreCase("mySql")) {
                    comandSqlResult = "CREATE TABLE DS_r AS (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,t0.ID_3 AS ID_3,t0.ID_4 AS ID_4,t0.ME_1 AS ME_1,(CASE WHEN (t0.ME_1>20) THEN 'TRUE' ELSE 'FALSE' END) AS Me_2 FROM DS_13 t0);"+"CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);"+"CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);"+"CREATE INDEX DS_r_ID_3_IDX ON DS_r (ID_3);"+"CREATE INDEX DS_r_ID_4_IDX ON DS_r (ID_4);";  
		} else if (profileActive.equalsIgnoreCase("postgreSql")) {
                    comandSqlResult = "CREATE TABLE DS_r AS (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,t0.ID_3 AS ID_3,t0.ID_4 AS ID_4,t0.ME_1 AS ME_1,(CASE WHEN (t0.ME_1>20) THEN 'TRUE' ELSE 'FALSE' END) AS Me_2 FROM DS_13 t0);"+"CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);"+"CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);"+"CREATE INDEX DS_r_ID_3_IDX ON DS_r (ID_3);"+"CREATE INDEX DS_r_ID_4_IDX ON DS_r (ID_4);";  
		}
		
		String commandStatements = "DS_r := DS_13 [ calc Me_2 := Me_1 > 20 ];";
		String result = translationUtilTest.translate(commandStatements);
		System.out.println("Print queryGreaterComponent: " +result);
		System.out.println("Print queryAttesaGreaterComponent: " +comandSqlResult);
		assertEquals("Test operator GreaterComponent", comandSqlResult, result);

	}

	@Test
	@Transactional
	public void less() throws Exception {
		dbTableUtilityService.resetSchema("t");
		VtlDataset vtlDatasetFind = datasetRepository.findByNameIgnoreCase("DS_1");
		if (vtlDatasetFind == null) {

			VtlDataset vtlDataset = new VtlDataset();

			vtlDataset.setPersistent(true);
			vtlDataset.setName("DS_25");
			vtlDataset.setIsOnlyAScalar(false);
			vtlDataset.setTransitory(false);
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ID_1", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ID_2", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ID_3", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ID_4", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ME_1", VtlType.INTEGER, VtlComponentRole.MEASURE));
			datasetRepository.save(vtlDataset);
		}
		
		System.out.println("Il profilo attivo è " + environment.getActiveProfiles()[0]);
		String comandSqlResult = "";
		String profileActive = environment.getActiveProfiles()[0];
		if (profileActive.equalsIgnoreCase("oracleSql")) {
                    comandSqlResult = "CREATE TABLE DS_r AS (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,t0.ID_3 AS ID_3,t0.ID_4 AS ID_4,(CASE WHEN (t0.ME_1<15000000) THEN 'TRUE' ELSE 'FALSE' END) AS bool_var FROM DS_25 t0);"+"CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);"+"CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);"+"CREATE INDEX DS_r_ID_3_IDX ON DS_r (ID_3);"+"CREATE INDEX DS_r_ID_4_IDX ON DS_r (ID_4);";    
		} else if (profileActive.equalsIgnoreCase("sqlServer")) {
                    comandSqlResult = "SELECT t1.* INTO DS_r FROM (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,t0.ID_3 AS ID_3,t0.ID_4 AS ID_4,(CASE WHEN (t0.ME_1<15000000) THEN 'TRUE' ELSE 'FALSE' END) AS bool_var FROM DS_25 t0) t1;"+"CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);"+"CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);"+"CREATE INDEX DS_r_ID_3_IDX ON DS_r (ID_3);"+"CREATE INDEX DS_r_ID_4_IDX ON DS_r (ID_4);";
		} else if (profileActive.equalsIgnoreCase("mySql")) {
                    comandSqlResult = "CREATE TABLE DS_r AS (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,t0.ID_3 AS ID_3,t0.ID_4 AS ID_4,(CASE WHEN (t0.ME_1<15000000) THEN 'TRUE' ELSE 'FALSE' END) AS bool_var FROM DS_25 t0);"+"CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);"+"CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);"+"CREATE INDEX DS_r_ID_3_IDX ON DS_r (ID_3);"+"CREATE INDEX DS_r_ID_4_IDX ON DS_r (ID_4);";  
		} else if (profileActive.equalsIgnoreCase("postgreSql")) {
                    comandSqlResult = "CREATE TABLE DS_r AS (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,t0.ID_3 AS ID_3,t0.ID_4 AS ID_4,(CASE WHEN (t0.ME_1<15000000) THEN 'TRUE' ELSE 'FALSE' END) AS bool_var FROM DS_25 t0);"+"CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);"+"CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);"+"CREATE INDEX DS_r_ID_3_IDX ON DS_r (ID_3);"+"CREATE INDEX DS_r_ID_4_IDX ON DS_r (ID_4);";  
		}
		
		String commandStatements = "DS_r := DS_25 < 15000000;";
		String result = translationUtilTest.translate(commandStatements);
		System.out.println("Print queryLess: " +result);
		System.out.println("Print queryAttesaLess: " +comandSqlResult);
		assertEquals("Test operator Less", comandSqlResult, result);

	}

	@Test
	@Transactional
	public void lessInt() throws Exception {
		dbTableUtilityService.resetSchema("t");
		System.out.println("Il profilo attivo è " + environment.getActiveProfiles()[0]);
		String comandSqlResult = "";
		String profileActive = environment.getActiveProfiles()[0];
		if (profileActive.equalsIgnoreCase("oracleSql")) {
                    comandSqlResult = "CREATE TABLE DS_r AS (SELECT (CASE WHEN (5<4) THEN 'TRUE' ELSE 'FALSE' END) AS bool_var FROM DUAL);";    
		} else if (profileActive.equalsIgnoreCase("sqlServer")) {
                    comandSqlResult = "SELECT t0.* INTO DS_r FROM (SELECT (CASE WHEN (5<4) THEN 'TRUE' ELSE 'FALSE' END) AS bool_var) t0;";
		} else if (profileActive.equalsIgnoreCase("mySql")) {
                    comandSqlResult = "CREATE TABLE DS_r AS (SELECT (CASE WHEN (5<4) THEN 'TRUE' ELSE 'FALSE' END) AS bool_var FROM DUAL);";  
		} else if (profileActive.equalsIgnoreCase("postgreSql")) {
                    comandSqlResult = "CREATE TABLE DS_r AS (SELECT (CASE WHEN (5<4) THEN 'TRUE' ELSE 'FALSE' END) AS bool_var);";  
		}
		
		String commandStatements = "DS_r := 5 < 4;";
		String result = translationUtilTest.translate(commandStatements);
		System.out.println("Print queryLessInt: " +result);
		System.out.println("Print queryAttesaLessInt: " +comandSqlResult);
		assertEquals("Test operator LessInt", comandSqlResult, result);

	}

	@Test
	@Transactional
	public void inMe() throws Exception {
		dbTableUtilityService.resetSchema("t");
		VtlDataset vtlDatasetFind = datasetRepository.findByNameIgnoreCase("DS_1");
		if (vtlDatasetFind == null) {
			VtlDataset vtlDataset = new VtlDataset();

			vtlDataset.setPersistent(true);
			vtlDataset.setName("DS_17");
			vtlDataset.setIsOnlyAScalar(false);
			vtlDataset.setTransitory(false);
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ID_1", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ID_2", VtlType.STRING, VtlComponentRole.IDENTIFIER));
      	    vtlDataset.addComponent(translationUtilTest.getVtlComponent("ID_3", VtlType.STRING, VtlComponentRole.IDENTIFIER));
	        vtlDataset.addComponent(translationUtilTest.getVtlComponent("ID_4", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ME_1", VtlType.STRING, VtlComponentRole.MEASURE));
			datasetRepository.save(vtlDataset);
		}
		
		System.out.println("Il profilo attivo è " + environment.getActiveProfiles()[0]);
		String comandSqlResult = "";
		String profileActive = environment.getActiveProfiles()[0];
		if (profileActive.equalsIgnoreCase("oracleSql")) {
                    comandSqlResult = "CREATE TABLE DS_r AS (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,t0.ID_3 AS ID_3,t0.ID_4 AS ID_4,t0.ME_1 AS ME_1,(CASE WHEN t0.ME_1 IN ('BS','MO','HH','PP') THEN 'TRUE' ELSE 'FALSE' END) AS Me_2 FROM DS_17 t0);"+"CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);"+"CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);"+"CREATE INDEX DS_r_ID_3_IDX ON DS_r (ID_3);"+"CREATE INDEX DS_r_ID_4_IDX ON DS_r (ID_4);";    
		} else if (profileActive.equalsIgnoreCase("sqlServer")) {
                    comandSqlResult = "SELECT t1.* INTO DS_r FROM (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,t0.ID_3 AS ID_3,t0.ID_4 AS ID_4,t0.ME_1 AS ME_1,(CASE WHEN t0.ME_1 IN ('BS','MO','HH','PP') THEN 'TRUE' ELSE 'FALSE' END) AS Me_2 FROM DS_17 t0) t1;"+"CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);"+"CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);"+"CREATE INDEX DS_r_ID_3_IDX ON DS_r (ID_3);"+"CREATE INDEX DS_r_ID_4_IDX ON DS_r (ID_4);";    
		} else if (profileActive.equalsIgnoreCase("mySql")) {
                    comandSqlResult = "CREATE TABLE DS_r AS (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,t0.ID_3 AS ID_3,t0.ID_4 AS ID_4,t0.ME_1 AS ME_1,(CASE WHEN t0.ME_1 IN ('BS','MO','HH','PP') THEN 'TRUE' ELSE 'FALSE' END) AS Me_2 FROM DS_17 t0);"+"CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);"+"CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);"+"CREATE INDEX DS_r_ID_3_IDX ON DS_r (ID_3);"+"CREATE INDEX DS_r_ID_4_IDX ON DS_r (ID_4);";  
		} else if (profileActive.equalsIgnoreCase("postgreSql")) {
                    comandSqlResult = "CREATE TABLE DS_r AS (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,t0.ID_3 AS ID_3,t0.ID_4 AS ID_4,t0.ME_1 AS ME_1,(CASE WHEN t0.ME_1 IN ('BS','MO','HH','PP') THEN 'TRUE' ELSE 'FALSE' END) AS Me_2 FROM DS_17 t0);"+"CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);"+"CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);"+"CREATE INDEX DS_r_ID_3_IDX ON DS_r (ID_3);"+"CREATE INDEX DS_r_ID_4_IDX ON DS_r (ID_4);";  
		}
		
		String commandStatements = "DS_r := DS_17 [ calc Me_2:= Me_1 in { \"BS\", \"MO\", \"HH\", \"PP\" }];";
		String result = translationUtilTest.translate(commandStatements);
		System.out.println("Print queryInMe: " +result);
		System.out.println("Print queryAttesaInMe: " +comandSqlResult);
		assertEquals("Test operator InMe", comandSqlResult, result);

	}

	@Test
	@Transactional
	public void notInMe() throws Exception {
		dbTableUtilityService.resetSchema("t");
		VtlDataset vtlDatasetFind = datasetRepository.findByNameIgnoreCase("DS_1");
		if (vtlDatasetFind == null) {
			VtlDataset vtlDataset = new VtlDataset();

			vtlDataset.setPersistent(true);
			vtlDataset.setName("DS_17");
			vtlDataset.setIsOnlyAScalar(false);
			vtlDataset.setTransitory(false);
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ID_1", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ID_2", VtlType.STRING, VtlComponentRole.IDENTIFIER));
      	    vtlDataset.addComponent(translationUtilTest.getVtlComponent("ID_3", VtlType.STRING, VtlComponentRole.IDENTIFIER));
	        vtlDataset.addComponent(translationUtilTest.getVtlComponent("ID_4", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ME_1", VtlType.STRING, VtlComponentRole.MEASURE));
			datasetRepository.save(vtlDataset);
		}
		
		System.out.println("Il profilo attivo è " + environment.getActiveProfiles()[0]);
		String comandSqlResult = "";
		String profileActive = environment.getActiveProfiles()[0];
		if (profileActive.equalsIgnoreCase("oracleSql")) {
                    comandSqlResult = "CREATE TABLE DS_r AS (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,t0.ID_3 AS ID_3,t0.ID_4 AS ID_4,t0.ME_1 AS ME_1,(CASE WHEN t0.ME_1 NOT IN ('BS','MO','HH','PP') THEN 'TRUE' ELSE 'FALSE' END) AS Me_2 FROM DS_17 t0);"+"CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);"+"CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);"+"CREATE INDEX DS_r_ID_3_IDX ON DS_r (ID_3);"+"CREATE INDEX DS_r_ID_4_IDX ON DS_r (ID_4);";    
		} else if (profileActive.equalsIgnoreCase("sqlServer")) {
                    comandSqlResult = "SELECT t1.* INTO DS_r FROM (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,t0.ID_3 AS ID_3,t0.ID_4 AS ID_4,t0.ME_1 AS ME_1,(CASE WHEN t0.ME_1 NOT IN ('BS','MO','HH','PP') THEN 'TRUE' ELSE 'FALSE' END) AS Me_2 FROM DS_17 t0) t1;"+"CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);"+"CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);"+"CREATE INDEX DS_r_ID_3_IDX ON DS_r (ID_3);"+"CREATE INDEX DS_r_ID_4_IDX ON DS_r (ID_4);";    
		} else if (profileActive.equalsIgnoreCase("mySql")) {
                    comandSqlResult = "CREATE TABLE DS_r AS (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,t0.ID_3 AS ID_3,t0.ID_4 AS ID_4,t0.ME_1 AS ME_1,(CASE WHEN t0.ME_1 NOT IN ('BS','MO','HH','PP') THEN 'TRUE' ELSE 'FALSE' END) AS Me_2 FROM DS_17 t0);"+"CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);"+"CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);"+"CREATE INDEX DS_r_ID_3_IDX ON DS_r (ID_3);"+"CREATE INDEX DS_r_ID_4_IDX ON DS_r (ID_4);";  
		} else if (profileActive.equalsIgnoreCase("postgreSql")) {
                    comandSqlResult = "CREATE TABLE DS_r AS (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,t0.ID_3 AS ID_3,t0.ID_4 AS ID_4,t0.ME_1 AS ME_1,(CASE WHEN t0.ME_1 NOT IN ('BS','MO','HH','PP') THEN 'TRUE' ELSE 'FALSE' END) AS Me_2 FROM DS_17 t0);"+"CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);"+"CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);"+"CREATE INDEX DS_r_ID_3_IDX ON DS_r (ID_3);"+"CREATE INDEX DS_r_ID_4_IDX ON DS_r (ID_4);";  
		}
		
		String commandStatements = "DS_r := DS_17 [ calc Me_2:= Me_1 not_in { \"BS\", \"MO\", \"HH\", \"PP\" }];";
		String result = translationUtilTest.translate(commandStatements);
		System.out.println("Print queryNotInMe: " +result);
		System.out.println("Print queryAttesaNotInMe: " +comandSqlResult);
		assertEquals("Test operator NotInMe", comandSqlResult, result);
	}
	
        @Test
	@Transactional
	public void inValueDomain() throws Exception {
		dbTableUtilityService.resetSchema("t");
		VtlDataset vtlDatasetFind = datasetRepository.findByNameIgnoreCase("DS_1");
		if (vtlDatasetFind == null) {
			VtlDataset vtlDataset = new VtlDataset();
			vtlDataset.setPersistent(true);
			vtlDataset.setName("DS_17");
			vtlDataset.setIsOnlyAScalar(false);
			vtlDataset.setTransitory(false);
                        
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ID_1", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ID_2", VtlType.STRING, VtlComponentRole.IDENTIFIER));
                        vtlDataset.addComponent(translationUtilTest.getVtlComponent("ID_3", VtlType.STRING, VtlComponentRole.IDENTIFIER));
                        vtlDataset.addComponent(translationUtilTest.getVtlComponent("ID_4", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ME_1", VtlType.STRING, VtlComponentRole.MEASURE));
			
                        ValueDomain valueDomain = new ValueDomain();
                        valueDomain.setCode("");
                        valueDomain.setValueDomainName("cl_area");
                        valueDomain.setDescription("");
                        valueDomainRepository.save(valueDomain);

                        datasetRepository.save(vtlDataset);
		}
		
		System.out.println("Il profilo attivo è " + environment.getActiveProfiles()[0]);
		String comandSqlResult = "";
		String profileActive = environment.getActiveProfiles()[0];
		if (profileActive.equalsIgnoreCase("oracleSql")) {
                    comandSqlResult = "CREATE TABLE DS_R AS (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,t0.ID_3 AS ID_3,t0.ID_4 AS ID_4,(CASE WHEN t0.ME_1 IN (SELECT ITEM_ID FROM cl_area) THEN 'TRUE' ELSE 'FALSE' END) AS bool_var FROM DS_17 t0);CREATE INDEX DS_R_ID_1_IDX ON DS_R (ID_1);CREATE INDEX DS_R_ID_2_IDX ON DS_R (ID_2);CREATE INDEX DS_R_ID_3_IDX ON DS_R (ID_3);CREATE INDEX DS_R_ID_4_IDX ON DS_R (ID_4);";    
		} else if (profileActive.equalsIgnoreCase("sqlServer")) {
                    comandSqlResult = "SELECT t1.* INTO DS_R FROM (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,t0.ID_3 AS ID_3,t0.ID_4 AS ID_4,(CASE WHEN t0.ME_1 IN (SELECT ITEM_ID FROM cl_area) THEN 'TRUE' ELSE 'FALSE' END) AS bool_var FROM DS_17 t0) t1;CREATE INDEX DS_R_ID_1_IDX ON DS_R (ID_1);CREATE INDEX DS_R_ID_2_IDX ON DS_R (ID_2);CREATE INDEX DS_R_ID_3_IDX ON DS_R (ID_3);CREATE INDEX DS_R_ID_4_IDX ON DS_R (ID_4);";    
		} else if (profileActive.equalsIgnoreCase("mySql")) {
                    comandSqlResult = "CREATE TABLE DS_R AS (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,t0.ID_3 AS ID_3,t0.ID_4 AS ID_4,(CASE WHEN t0.ME_1 IN (SELECT ITEM_ID FROM cl_area) THEN 'TRUE' ELSE 'FALSE' END) AS bool_var FROM DS_17 t0);CREATE INDEX DS_R_ID_1_IDX ON DS_R (ID_1);CREATE INDEX DS_R_ID_2_IDX ON DS_R (ID_2);CREATE INDEX DS_R_ID_3_IDX ON DS_R (ID_3);CREATE INDEX DS_R_ID_4_IDX ON DS_R (ID_4);";  
		} else if (profileActive.equalsIgnoreCase("postgreSql")) {
                    comandSqlResult = "CREATE TABLE DS_R AS (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,t0.ID_3 AS ID_3,t0.ID_4 AS ID_4,(CASE WHEN t0.ME_1 IN (SELECT ITEM_ID FROM cl_area) THEN 'TRUE' ELSE 'FALSE' END) AS bool_var FROM DS_17 t0);CREATE INDEX DS_R_ID_1_IDX ON DS_R (ID_1);CREATE INDEX DS_R_ID_2_IDX ON DS_R (ID_2);CREATE INDEX DS_R_ID_3_IDX ON DS_R (ID_3);CREATE INDEX DS_R_ID_4_IDX ON DS_R (ID_4);";  
		}
		
		String commandStatements = "DS_R := DS_17 in cl_area;";
		String result = translationUtilTest.translate(commandStatements);
		System.out.println("Print queryInValueDomain: " +result);
		System.out.println("Print queryAttesaInValueDomain: " +comandSqlResult);
		assertEquals("Test operator inValueDomain", comandSqlResult, result);
	}
}
