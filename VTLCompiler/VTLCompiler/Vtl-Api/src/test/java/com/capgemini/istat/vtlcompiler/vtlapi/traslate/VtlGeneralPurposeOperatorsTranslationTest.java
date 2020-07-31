package com.capgemini.istat.vtlcompiler.vtlapi.traslate;

import com.capgemini.istat.vtlcompiler.vtlapi.utils.TranslationUtilsTestService;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.dataset.VtlComponentRole;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.dataset.VtlDataset;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.dataset.VtlType;
import com.capgemini.istat.vtlcompiler.vtlcommon.repository.DatasetRepository;
import com.capgemini.istat.vtlcompiler.vtlcommon.repository.UserFunctionRepository;
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
public class VtlGeneralPurposeOperatorsTranslationTest {

    private TranslationUtilsTestService translationUtilTest;
    
	private DatasetRepository datasetRepository;
	
	private DbTableUtilityService dbTableUtilityService;
           
    private UserFunctionRepository userFunctionRepository;
        
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
        public void setUserFunctionRepository(UserFunctionRepository userFunctionRepository) {
            this.userFunctionRepository = userFunctionRepository;
        }
        
        @Autowired
    	public void setEnvironment(Environment environment) {
    		this.environment = environment;
    	}
        
	@Test
	@Transactional
	public void Persistent() throws Exception {
		dbTableUtilityService.resetSchema("t");
		VtlDataset vtlDatasetFind = datasetRepository.findByNameIgnoreCase("DS_1");

		if (vtlDatasetFind == null) {

			VtlDataset vtlDataset = new VtlDataset();

			vtlDataset.setPersistent(true);
			vtlDataset.setName("DS_1");
			vtlDataset.setIsOnlyAScalar(false);
			vtlDataset.setTransitory(false);
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ID_1", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ID_2", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ME_1", VtlType.INTEGER, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ME_2", VtlType.INTEGER, VtlComponentRole.MEASURE));
			datasetRepository.save(vtlDataset);
		}
		
		System.out.println("Il profilo attivo è " + environment.getActiveProfiles()[0]);

		String comandSqlResult = "";
		String profileActive = environment.getActiveProfiles()[0];
		if (profileActive.equalsIgnoreCase("oracleSql")) {
			comandSqlResult = "CREATE TABLE DS_r AS (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,t0.ME_1 AS ME_1,t0.ME_2 AS ME_2 FROM DS_1 t0);"+"CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);"+"CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);";
		} else if (profileActive.equalsIgnoreCase("sqlServer")) {
			comandSqlResult = "SELECT t1.* INTO DS_r FROM (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,t0.ME_1 AS ME_1,t0.ME_2 AS ME_2 FROM DS_1 t0) t1;"+"CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);"+"CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);";
		} else if (profileActive.equalsIgnoreCase("mySql")) {
			comandSqlResult = "CREATE TABLE DS_r AS (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,t0.ME_1 AS ME_1,t0.ME_2 AS ME_2 FROM DS_1 t0);"+"CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);"+"CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);";
		} else if (profileActive.equalsIgnoreCase("postgreSql")) {
			comandSqlResult = "CREATE TABLE DS_r AS (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,t0.ME_1 AS ME_1,t0.ME_2 AS ME_2 FROM DS_1 t0);"+"CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);"+"CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);";
		}
		
		String commandStatements = "DS_r <- DS_1;"; 
		String result = translationUtilTest.translate(commandStatements);
		System.out.println("Print queryPersistent: " +result);
		System.out.println("Print queryAttesaPersistent: " +comandSqlResult);
		assertEquals("Test operator Persistent", comandSqlResult, result);

	}

	@Test
	@Transactional
	public void nonPersistentAssignmentComponent() throws Exception {
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
			comandSqlResult = "CREATE TABLE DS_r AS (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,t0.ID_3 AS ID_3,t0.ID_4 AS ID_4,t0.ID_5 AS ID_5,t0.ME_1 AS ME_1,(CASE WHEN (t0.ME_1>20) THEN 'TRUE' ELSE 'FALSE' END) AS Me_2 FROM DS_37 t0);"+"CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);"+"CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);"+"CREATE INDEX DS_r_ID_3_IDX ON DS_r (ID_3);"+"CREATE INDEX DS_r_ID_4_IDX ON DS_r (ID_4);"+"CREATE INDEX DS_r_ID_5_IDX ON DS_r (ID_5);";
		} else if (profileActive.equalsIgnoreCase("sqlServer")) {
			comandSqlResult = "SELECT t1.* INTO DS_r FROM (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,t0.ID_3 AS ID_3,t0.ID_4 AS ID_4,t0.ID_5 AS ID_5,t0.ME_1 AS ME_1,(CASE WHEN (t0.ME_1>20) THEN 'TRUE' ELSE 'FALSE' END) AS Me_2 FROM DS_37 t0) t1;"+"CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);"+"CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);"+"CREATE INDEX DS_r_ID_3_IDX ON DS_r (ID_3);"+"CREATE INDEX DS_r_ID_4_IDX ON DS_r (ID_4);"+"CREATE INDEX DS_r_ID_5_IDX ON DS_r (ID_5);";
		} else if (profileActive.equalsIgnoreCase("mySql")) {
			comandSqlResult = "CREATE TABLE DS_r AS (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,t0.ID_3 AS ID_3,t0.ID_4 AS ID_4,t0.ID_5 AS ID_5,t0.ME_1 AS ME_1,(CASE WHEN (t0.ME_1>20) THEN 'TRUE' ELSE 'FALSE' END) AS Me_2 FROM DS_37 t0);"+"CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);"+"CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);"+"CREATE INDEX DS_r_ID_3_IDX ON DS_r (ID_3);"+"CREATE INDEX DS_r_ID_4_IDX ON DS_r (ID_4);"+"CREATE INDEX DS_r_ID_5_IDX ON DS_r (ID_5);";
		} else if (profileActive.equalsIgnoreCase("postgreSql")) {
			comandSqlResult = "CREATE TABLE DS_r AS (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,t0.ID_3 AS ID_3,t0.ID_4 AS ID_4,t0.ID_5 AS ID_5,t0.ME_1 AS ME_1,(CASE WHEN (t0.ME_1>20) THEN 'TRUE' ELSE 'FALSE' END) AS Me_2 FROM DS_37 t0);"+"CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);"+"CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);"+"CREATE INDEX DS_r_ID_3_IDX ON DS_r (ID_3);"+"CREATE INDEX DS_r_ID_4_IDX ON DS_r (ID_4);"+"CREATE INDEX DS_r_ID_5_IDX ON DS_r (ID_5);";
		}
		
		String commandStatements = "DS_r := DS_37 [ calc Me_2 := Me_1 > 20 ];";
		String result = translationUtilTest.translate(commandStatements);
		System.out.println("Print queryNonPersistentAssignmentComponent: " +result);
		System.out.println("Print queryAttesaNonPersistentAssignmentComponent: " +comandSqlResult);
		assertEquals("Test operator NonPersistentAssignmentComponent", comandSqlResult, result);

	}

	@Test
	@Transactional
	public void nonPersistentAssignment() throws Exception {
		dbTableUtilityService.resetSchema("t");
		VtlDataset vtlDatasetFind = datasetRepository.findByNameIgnoreCase("DS_1");

		if (vtlDatasetFind == null) {

			VtlDataset vtlDataset = new VtlDataset();

			vtlDataset.setPersistent(true);
			vtlDataset.setName("DS_1");
			vtlDataset.setIsOnlyAScalar(false);
			vtlDataset.setTransitory(false);
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ID_1", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ID_2", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ME_1", VtlType.INTEGER, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ME_2", VtlType.INTEGER, VtlComponentRole.MEASURE));
			datasetRepository.save(vtlDataset);
		}
		
		System.out.println("Il profilo attivo è " + environment.getActiveProfiles()[0]);

		String comandSqlResult = "";
		String profileActive = environment.getActiveProfiles()[0];
		if (profileActive.equalsIgnoreCase("oracleSql")) {
			comandSqlResult = "CREATE TABLE DS_r AS (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,t0.ME_1 AS ME_1,t0.ME_2 AS ME_2 FROM DS_1 t0);"+"CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);"+"CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);";
		} else if (profileActive.equalsIgnoreCase("sqlServer")) {
			comandSqlResult = "SELECT t1.* INTO DS_r FROM (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,t0.ME_1 AS ME_1,t0.ME_2 AS ME_2 FROM DS_1 t0) t1;"+"CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);"+"CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);";
		} else if (profileActive.equalsIgnoreCase("mySql")) {
			comandSqlResult = "CREATE TABLE DS_r AS (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,t0.ME_1 AS ME_1,t0.ME_2 AS ME_2 FROM DS_1 t0);"+"CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);"+"CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);";
		} else if (profileActive.equalsIgnoreCase("postgreSql")) {
			comandSqlResult = "CREATE TABLE DS_r AS (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,t0.ME_1 AS ME_1,t0.ME_2 AS ME_2 FROM DS_1 t0);"+"CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);"+"CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);";
		}
		
		String commandStatements = "DS_r := DS_1;";
		String result = translationUtilTest.translate(commandStatements);
		System.out.println("Print queryNonPersistentAssignment: " +result);
		System.out.println("Print queryAttesaNonPersistentAssignment: " +comandSqlResult);
		assertEquals("Test operator NonPersistentAssignment", comandSqlResult, result);

	}

	@Test
	@Transactional
	public void membershipComponent() throws Exception {
		dbTableUtilityService.resetSchema("t");
		VtlDataset vtlDatasetFind = datasetRepository.findByNameIgnoreCase("DS_1");
		if (vtlDatasetFind == null) {
			VtlDataset vtlDataset = new VtlDataset();

			vtlDataset.setPersistent(true);
			vtlDataset.setName("DS_1");
			vtlDataset.setIsOnlyAScalar(false);
			vtlDataset.setTransitory(false);
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ID_1", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ID_2", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ME_1", VtlType.INTEGER, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ME_2", VtlType.INTEGER, VtlComponentRole.MEASURE));
			datasetRepository.save(vtlDataset);
		}
		
		System.out.println("Il profilo attivo è " + environment.getActiveProfiles()[0]);

		String comandSqlResult = "";
		String profileActive = environment.getActiveProfiles()[0];
		if (profileActive.equalsIgnoreCase("oracleSql")) {
			comandSqlResult = "CREATE TABLE DS_r AS (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,t0.ME_1 AS ME_1 FROM DS_1 t0);CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);";
		} else if (profileActive.equalsIgnoreCase("sqlServer")) {
			comandSqlResult = "SELECT t1.* INTO DS_r FROM (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,t0.ME_1 AS ME_1 FROM DS_1 t0) t1;"+"CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);"+"CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);";
		} else if (profileActive.equalsIgnoreCase("mySql")) {
			comandSqlResult = "CREATE TABLE DS_r AS (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,t0.ME_1 AS ME_1 FROM DS_1 t0);CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);";
		} else if (profileActive.equalsIgnoreCase("postgreSql")) {
			comandSqlResult = "CREATE TABLE DS_r AS (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,t0.ME_1 AS ME_1 FROM DS_1 t0);CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);";
		}
		
		String commandStatements = "DS_r := DS_1#Me_1;";
		String result = translationUtilTest.translate(commandStatements);
		System.out.println("Print queryMembershipComponent: " +result);
		System.out.println("Print queryAttesaMembershipComponent: " +comandSqlResult);
		assertEquals("Test operator MembershipComponent", comandSqlResult, result);
	}

	@Test
	@Transactional
	public void membershipComponentId() throws Exception {
		dbTableUtilityService.resetSchema("t");
		VtlDataset vtlDatasetFind = datasetRepository.findByNameIgnoreCase("DS_1");
		if (vtlDatasetFind == null) {
			VtlDataset vtlDataset = new VtlDataset();

			vtlDataset.setPersistent(true);
			vtlDataset.setName("DS_2");
			vtlDataset.setIsOnlyAScalar(false);
			vtlDataset.setTransitory(false);
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ID_1", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ID_2", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ME_1", VtlType.INTEGER, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ME_2", VtlType.INTEGER, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("AT_1", VtlType.STRING, VtlComponentRole.VIRAL));
			datasetRepository.save(vtlDataset);
		}
		
		System.out.println("Il profilo attivo è " + environment.getActiveProfiles()[0]);

		String comandSqlResult = "";
		String profileActive = environment.getActiveProfiles()[0];
		if (profileActive.equalsIgnoreCase("oracleSql")) {
			comandSqlResult = "CREATE TABLE DS_r AS (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,t0.ID_1 AS int_var,t0.AT_1 AS AT_1 FROM DS_2 t0);CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);";
		} else if (profileActive.equalsIgnoreCase("sqlServer")) {
			comandSqlResult = "SELECT t1.* INTO DS_r FROM (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,t0.ID_1 AS int_var,t0.AT_1 AS AT_1 FROM DS_2 t0) t1;"+"CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);"+"CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);";
		} else if (profileActive.equalsIgnoreCase("mySql")) {
			comandSqlResult = "CREATE TABLE DS_r AS (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,t0.ID_1 AS int_var,t0.AT_1 AS AT_1 FROM DS_2 t0);CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);";
		} else if (profileActive.equalsIgnoreCase("postgreSql")) {
			comandSqlResult = "CREATE TABLE DS_r AS (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,t0.ID_1 AS int_var,t0.AT_1 AS AT_1 FROM DS_2 t0);CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);";
		}
		
		String commandStatements = "DS_r := DS_2#Id_1;";
		String result = translationUtilTest.translate(commandStatements);
		System.out.println("Print queryMembershipComponentId: " +result);
		System.out.println("Print queryAttesaMembershipComponentId: " +comandSqlResult);
		assertEquals("Test operator MembershipComponentId", comandSqlResult, result);
	}

	@Test
	@Transactional
	public void membershipComponentAt() throws Exception {
		dbTableUtilityService.resetSchema("t");
		VtlDataset vtlDatasetFind = datasetRepository.findByNameIgnoreCase("DS_1");
		if (vtlDatasetFind == null) {
			VtlDataset vtlDataset = new VtlDataset();

			vtlDataset.setPersistent(true);
			vtlDataset.setName("DS_2");
			vtlDataset.setIsOnlyAScalar(false);
			vtlDataset.setTransitory(false);
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ID_1", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ID_2", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ME_1", VtlType.INTEGER, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ME_2", VtlType.INTEGER, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("AT_1", VtlType.STRING, VtlComponentRole.VIRAL));
			datasetRepository.save(vtlDataset);
		}
		
		System.out.println("Il profilo attivo è " + environment.getActiveProfiles()[0]);

		String comandSqlResult = "";
		String profileActive = environment.getActiveProfiles()[0];
		if (profileActive.equalsIgnoreCase("oracleSql")) {
			comandSqlResult = "CREATE TABLE DS_r AS (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,t0.AT_1 AS string_var,t0.AT_1 AS AT_1 FROM DS_2 t0);CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);";
		} else if (profileActive.equalsIgnoreCase("sqlServer")) {
			comandSqlResult = "SELECT t1.* INTO DS_r FROM (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,t0.AT_1 AS string_var,t0.AT_1 AS AT_1 FROM DS_2 t0) t1;"+"CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);"+"CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);";
		} else if (profileActive.equalsIgnoreCase("mySql")) {
			comandSqlResult = "CREATE TABLE DS_r AS (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,t0.AT_1 AS string_var,t0.AT_1 AS AT_1 FROM DS_2 t0);CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);";
		} else if (profileActive.equalsIgnoreCase("postgreSql")) {
			comandSqlResult = "CREATE TABLE DS_r AS (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,t0.AT_1 AS string_var,t0.AT_1 AS AT_1 FROM DS_2 t0);CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);";
		}
		
		String commandStatements = "DS_r := DS_2#At_1;";
		String result = translationUtilTest.translate(commandStatements);
		System.out.println("Print queryMembershipComponentAt: " +result);
		System.out.println("Print queryAttesaMembershipComponentAt: " +comandSqlResult);
		assertEquals("Test operator MembershipComponentAt", comandSqlResult, result);
	}
	
	@Test
	@Transactional
	public void castToStringFromInteger() throws Exception {
		dbTableUtilityService.resetSchema("t");
		VtlDataset vtlDatasetFind = datasetRepository.findByNameIgnoreCase("DS_1");
		if (vtlDatasetFind == null) {
			VtlDataset vtlDataset = new VtlDataset();

			vtlDataset.setPersistent(true);
			vtlDataset.setName("DS_1");
			vtlDataset.setIsOnlyAScalar(false);
			vtlDataset.setTransitory(false);
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ID_1", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ID_2", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ME_1", VtlType.INTEGER, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ME_2", VtlType.INTEGER, VtlComponentRole.MEASURE));
			datasetRepository.save(vtlDataset);
		}
		
		System.out.println("Il profilo attivo è " + environment.getActiveProfiles()[0]);

		String comandSqlResult = "";
		String profileActive = environment.getActiveProfiles()[0];
		if (profileActive.equalsIgnoreCase("oracleSql")) {
			comandSqlResult = "CREATE TABLE DS_r AS (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,t0.ME_1 AS ME_1,t0.ME_2 AS ME_2,vtl_pkg.cast_to_string(t0.ME_2,'INTEGER','') AS ME_3 FROM DS_1 t0);CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);";
		} else if (profileActive.equalsIgnoreCase("sqlServer")) {
			comandSqlResult = "SELECT t1.* INTO DS_r FROM (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,t0.ME_1 AS ME_1,t0.ME_2 AS ME_2,dbo.vtl_cast_to_string(t0.ME_2,'INTEGER','') AS ME_3 FROM DS_1 t0) t1;"+"CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);"+"CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);";
		} else if (profileActive.equalsIgnoreCase("mySql")) {
			comandSqlResult = "CREATE TABLE DS_r AS (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,t0.ME_1 AS ME_1,t0.ME_2 AS ME_2,vtl_cast_to_string(t0.ME_2,'INTEGER','') AS ME_3 FROM DS_1 t0);CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);";
		} else if (profileActive.equalsIgnoreCase("postgreSql")) {
			comandSqlResult = "CREATE TABLE DS_r AS (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,t0.ME_1 AS ME_1,t0.ME_2 AS ME_2,vtl_cast_to_string(t0.ME_2,'INTEGER','') AS ME_3 FROM DS_1 t0);CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);";
		}
		
		String commandStatements = "DS_r := DS_1[calc ME_3 := cast(ME_2, string)];";
		String result = translationUtilTest.translate(commandStatements);
		System.out.println("Print queryCastToStringFromInteger: " +result);
		System.out.println("Print queryAttesaCastToStringFromInteger: " +comandSqlResult);
		assertEquals("Test operator CastToStringFromInteger", comandSqlResult, result);
	}
	
	@Test
	@Transactional
	public void castToStringFromNumber() throws Exception {
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
			comandSqlResult = "CREATE TABLE DS_R AS (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,t0.ME_1 AS ME_1,t0.ME_2 AS ME_2,vtl_pkg.cast_to_string(t0.ME_2,'NUMBER','') AS ME_3 FROM DS_8 t0);CREATE INDEX DS_R_ID_1_IDX ON DS_R (ID_1);CREATE INDEX DS_R_ID_2_IDX ON DS_R (ID_2);";
		} else if (profileActive.equalsIgnoreCase("sqlServer")) {
			comandSqlResult = "SELECT t1.* INTO DS_R FROM (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,t0.ME_1 AS ME_1,t0.ME_2 AS ME_2,dbo.vtl_cast_to_string(t0.ME_2,'NUMBER','') AS ME_3 FROM DS_8 t0) t1;"+"CREATE INDEX DS_R_ID_1_IDX ON DS_R (ID_1);CREATE INDEX DS_R_ID_2_IDX ON DS_R (ID_2);";
		} else if (profileActive.equalsIgnoreCase("mySql")) {
			comandSqlResult = "CREATE TABLE DS_R AS (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,t0.ME_1 AS ME_1,t0.ME_2 AS ME_2,vtl_cast_to_string(t0.ME_2,'NUMBER','') AS ME_3 FROM DS_8 t0);CREATE INDEX DS_R_ID_1_IDX ON DS_R (ID_1);CREATE INDEX DS_R_ID_2_IDX ON DS_R (ID_2);";
		} else if (profileActive.equalsIgnoreCase("postgreSql")) {
			comandSqlResult = "CREATE TABLE DS_R AS (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,t0.ME_1 AS ME_1,t0.ME_2 AS ME_2,vtl_cast_to_string(t0.ME_2,'NUMBER','') AS ME_3 FROM DS_8 t0);CREATE INDEX DS_R_ID_1_IDX ON DS_R (ID_1);CREATE INDEX DS_R_ID_2_IDX ON DS_R (ID_2);";
		}
		
		String commandStatements = "DS_R := DS_8[calc ME_3 := cast(ME_2, string)];";
		String result = translationUtilTest.translate(commandStatements);
		System.out.println("Print queryCastToStringFromNumber: " +result);
		System.out.println("Print queryAttesaCastToStringFromNumber: " +comandSqlResult);
		assertEquals("Test operator CastToStringFromNumber", comandSqlResult, result);
	}

	@Test
	@Transactional
	public void castToStringFromBooleanImplicit() throws Exception {
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
			comandSqlResult = "CREATE TABLE DS_R AS (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,t0.ID_3 AS ID_3,t0.ID_4 AS ID_4,t0.ME_1 AS ME_1,concat(t0.ME_1,'abc') AS ME_3 FROM DS_19 t0);CREATE INDEX DS_R_ID_1_IDX ON DS_R (ID_1);CREATE INDEX DS_R_ID_2_IDX ON DS_R (ID_2);CREATE INDEX DS_R_ID_3_IDX ON DS_R (ID_3);CREATE INDEX DS_R_ID_4_IDX ON DS_R (ID_4);";
		} else if (profileActive.equalsIgnoreCase("sqlServer")) {
			comandSqlResult = "SELECT t1.* INTO DS_R FROM (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,t0.ID_3 AS ID_3,t0.ID_4 AS ID_4,t0.ME_1 AS ME_1,concat(t0.ME_1,'abc') AS ME_3 FROM DS_19 t0) t1;"+"CREATE INDEX DS_R_ID_1_IDX ON DS_R (ID_1);CREATE INDEX DS_R_ID_2_IDX ON DS_R (ID_2);CREATE INDEX DS_R_ID_3_IDX ON DS_R (ID_3);CREATE INDEX DS_R_ID_4_IDX ON DS_R (ID_4);";
		} else if (profileActive.equalsIgnoreCase("mySql")) {
			comandSqlResult = "CREATE TABLE DS_R AS (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,t0.ID_3 AS ID_3,t0.ID_4 AS ID_4,t0.ME_1 AS ME_1,concat(t0.ME_1,'abc') AS ME_3 FROM DS_19 t0);CREATE INDEX DS_R_ID_1_IDX ON DS_R (ID_1);CREATE INDEX DS_R_ID_2_IDX ON DS_R (ID_2);CREATE INDEX DS_R_ID_3_IDX ON DS_R (ID_3);CREATE INDEX DS_R_ID_4_IDX ON DS_R (ID_4);";
		} else if (profileActive.equalsIgnoreCase("postgreSql")) {
			comandSqlResult = "CREATE TABLE DS_R AS (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,t0.ID_3 AS ID_3,t0.ID_4 AS ID_4,t0.ME_1 AS ME_1,concat(t0.ME_1,'abc') AS ME_3 FROM DS_19 t0);CREATE INDEX DS_R_ID_1_IDX ON DS_R (ID_1);CREATE INDEX DS_R_ID_2_IDX ON DS_R (ID_2);CREATE INDEX DS_R_ID_3_IDX ON DS_R (ID_3);CREATE INDEX DS_R_ID_4_IDX ON DS_R (ID_4);";
		}
		
		String commandStatements = "DS_R := DS_19[calc ME_3 := ME_1 || \"abc\"];";
		String result = translationUtilTest.translate(commandStatements);
		System.out.println("Print queryCastToStringFromBooleanImplicit: " +result);
		System.out.println("Print queryAttesaCastToStringFromBooleanImplicit: " +comandSqlResult);
		assertEquals("Test operator CastToStringFromBooleanImplicit", comandSqlResult, result);
	}


	@Test
	@Transactional
	public void castToStringFromBooleanImplicit2() throws Exception {
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
			comandSqlResult = "CREATE TABLE DS_R AS (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,t0.ID_3 AS ID_3,t0.ID_4 AS ID_4,t0.ME_1 AS ME_1,vtl_pkg.cast_to_string(t0.ME_1,'BOOLEAN','') AS ME_3 FROM DS_19 t0);CREATE INDEX DS_R_ID_1_IDX ON DS_R (ID_1);CREATE INDEX DS_R_ID_2_IDX ON DS_R (ID_2);CREATE INDEX DS_R_ID_3_IDX ON DS_R (ID_3);CREATE INDEX DS_R_ID_4_IDX ON DS_R (ID_4);";
		} else if (profileActive.equalsIgnoreCase("sqlServer")) {
			comandSqlResult = "SELECT t1.* INTO DS_R FROM (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,t0.ID_3 AS ID_3,t0.ID_4 AS ID_4,t0.ME_1 AS ME_1,dbo.vtl_cast_to_string(t0.ME_1,'BOOLEAN','') AS ME_3 FROM DS_19 t0) t1;"+"CREATE INDEX DS_R_ID_1_IDX ON DS_R (ID_1);CREATE INDEX DS_R_ID_2_IDX ON DS_R (ID_2);CREATE INDEX DS_R_ID_3_IDX ON DS_R (ID_3);CREATE INDEX DS_R_ID_4_IDX ON DS_R (ID_4);";
		} else if (profileActive.equalsIgnoreCase("mySql")) {
			comandSqlResult = "CREATE TABLE DS_R AS (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,t0.ID_3 AS ID_3,t0.ID_4 AS ID_4,t0.ME_1 AS ME_1,vtl_cast_to_string(t0.ME_1,'BOOLEAN','') AS ME_3 FROM DS_19 t0);CREATE INDEX DS_R_ID_1_IDX ON DS_R (ID_1);CREATE INDEX DS_R_ID_2_IDX ON DS_R (ID_2);CREATE INDEX DS_R_ID_3_IDX ON DS_R (ID_3);CREATE INDEX DS_R_ID_4_IDX ON DS_R (ID_4);";
		} else if (profileActive.equalsIgnoreCase("postgreSql")) {
			comandSqlResult = "CREATE TABLE DS_R AS (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,t0.ID_3 AS ID_3,t0.ID_4 AS ID_4,t0.ME_1 AS ME_1,vtl_cast_to_string(t0.ME_1,'BOOLEAN','') AS ME_3 FROM DS_19 t0);CREATE INDEX DS_R_ID_1_IDX ON DS_R (ID_1);CREATE INDEX DS_R_ID_2_IDX ON DS_R (ID_2);CREATE INDEX DS_R_ID_3_IDX ON DS_R (ID_3);CREATE INDEX DS_R_ID_4_IDX ON DS_R (ID_4);";
		}
		
		String commandStatements = "DS_R := DS_19[calc ME_3 := cast(ME_1, string)];";
		String result = translationUtilTest.translate(commandStatements);
		System.out.println("Print queryCastToStringFromBooleanImplicit2: " +result);
		System.out.println("Print queryAttesaCastToStringFromBooleanImplicit2: " +comandSqlResult);
		assertEquals("Test operator CastToStringFromBooleanImplicit2", comandSqlResult, result);
	}
	@Test
	@Transactional
	public void castToStringFromDate() throws Exception {
		dbTableUtilityService.resetSchema("t");
		VtlDataset vtlDatasetFind = datasetRepository.findByNameIgnoreCase("DS_1");
		if (vtlDatasetFind == null) {
			VtlDataset vtlDataset = new VtlDataset();

			vtlDataset.setPersistent(true);
			vtlDataset.setName("DS_62");
			vtlDataset.setIsOnlyAScalar(false);
			vtlDataset.setTransitory(false);
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ID_1", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ID_2", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ME_1", VtlType.INTEGER, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ME_2", VtlType.NUMBER, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ME_3", VtlType.DATE, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ME_4", VtlType.BOOLEAN, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ME_5", VtlType.TIME, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ME_6", VtlType.TIME_PERIOD, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ME_7", VtlType.DURATION, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ME_8", VtlType.STRING, VtlComponentRole.MEASURE));
			datasetRepository.save(vtlDataset);
		}
		
		System.out.println("Il profilo attivo è " + environment.getActiveProfiles()[0]);

		String comandSqlResult = "";
		String profileActive = environment.getActiveProfiles()[0];
		if (profileActive.equalsIgnoreCase("oracleSql")) {
			comandSqlResult = "CREATE TABLE DS_r AS (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,t0.ME_1 AS ME_1,t0.ME_2 AS ME_2,t0.ME_3 AS ME_3,t0.ME_4 AS ME_4,t0.ME_5 AS ME_5,t0.ME_6 AS ME_6,t0.ME_7 AS ME_7,t0.ME_8 AS ME_8,vtl_pkg.cast_to_string(t0.ME_3,'DATE','YYYY-MM-DD') AS ME_9 FROM DS_62 t0);CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);";
		} else if (profileActive.equalsIgnoreCase("sqlServer")) {
			comandSqlResult = "SELECT t1.* INTO DS_r FROM (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,t0.ME_1 AS ME_1,t0.ME_2 AS ME_2,t0.ME_3 AS ME_3,t0.ME_4 AS ME_4,t0.ME_5 AS ME_5,t0.ME_6 AS ME_6,t0.ME_7 AS ME_7,t0.ME_8 AS ME_8,dbo.vtl_cast_to_string_date(t0.ME_3,'DATE','YYYY-MM-DD') AS ME_9 FROM DS_62 t0) t1;"+"CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);";
		} else if (profileActive.equalsIgnoreCase("mySql")) {
			comandSqlResult = "CREATE TABLE DS_r AS (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,t0.ME_1 AS ME_1,t0.ME_2 AS ME_2,t0.ME_3 AS ME_3,t0.ME_4 AS ME_4,t0.ME_5 AS ME_5,t0.ME_6 AS ME_6,t0.ME_7 AS ME_7,t0.ME_8 AS ME_8,vtl_cast_to_string_date(t0.ME_3,'DATE','YYYY-MM-DD') AS ME_9 FROM DS_62 t0);CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);";
		} else if (profileActive.equalsIgnoreCase("postgreSql")) {
			comandSqlResult = "CREATE TABLE DS_r AS (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,t0.ME_1 AS ME_1,t0.ME_2 AS ME_2,t0.ME_3 AS ME_3,t0.ME_4 AS ME_4,t0.ME_5 AS ME_5,t0.ME_6 AS ME_6,t0.ME_7 AS ME_7,t0.ME_8 AS ME_8,vtl_cast_to_string(t0.ME_3,'DATE','YYYY-MM-DD') AS ME_9 FROM DS_62 t0);CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);";
		}
		
		String commandStatements = "DS_r := DS_62[calc ME_9 := cast(ME_3, string, \"YYYY-MM-DD\")];";
		String result = translationUtilTest.translate(commandStatements);
		System.out.println("Print queryCastToStringFromDate: " +result);
		System.out.println("Print queryAttesaCastToStringFromDate: " +comandSqlResult);
		assertEquals("Test operator CastToStringFromDate", comandSqlResult, result);
	}
	
	@Test
	@Transactional
	public void castToStringFromTime() throws Exception {
		dbTableUtilityService.resetSchema("t");
		VtlDataset vtlDatasetFind = datasetRepository.findByNameIgnoreCase("DS_1");
		if (vtlDatasetFind == null) {
			VtlDataset vtlDataset = new VtlDataset();

			vtlDataset.setPersistent(true);
			vtlDataset.setName("DS_62");
			vtlDataset.setIsOnlyAScalar(false);
			vtlDataset.setTransitory(false);
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ID_1", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ID_2", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ME_1", VtlType.INTEGER, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ME_2", VtlType.NUMBER, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ME_3", VtlType.DATE, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ME_4", VtlType.BOOLEAN, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ME_5", VtlType.TIME, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ME_6", VtlType.TIME_PERIOD, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ME_7", VtlType.DURATION, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ME_8", VtlType.STRING, VtlComponentRole.MEASURE));
			datasetRepository.save(vtlDataset);
		}
		
		System.out.println("Il profilo attivo è " + environment.getActiveProfiles()[0]);

		String comandSqlResult = "";
		String profileActive = environment.getActiveProfiles()[0];
		if (profileActive.equalsIgnoreCase("oracleSql")) {
			comandSqlResult = "CREATE TABLE DS_r AS (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,t0.ME_1 AS ME_1,t0.ME_2 AS ME_2,t0.ME_3 AS ME_3,t0.ME_4 AS ME_4,t0.ME_5 AS ME_5,t0.ME_6 AS ME_6,t0.ME_7 AS ME_7,t0.ME_8 AS ME_8,vtl_pkg.cast_to_string(t0.ME_5,'TIME','YYYY-MM-DD/YYYY-MM-DD') AS ME_9 FROM DS_62 t0);CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);";
		} else if (profileActive.equalsIgnoreCase("sqlServer")) {
			comandSqlResult = "SELECT t1.* INTO DS_r FROM (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,t0.ME_1 AS ME_1,t0.ME_2 AS ME_2,t0.ME_3 AS ME_3,t0.ME_4 AS ME_4,t0.ME_5 AS ME_5,t0.ME_6 AS ME_6,t0.ME_7 AS ME_7,t0.ME_8 AS ME_8,dbo.vtl_cast_to_string(t0.ME_5,'TIME','YYYY-MM-DD/YYYY-MM-DD') AS ME_9 FROM DS_62 t0) t1;"+"CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);";
		} else if (profileActive.equalsIgnoreCase("mySql")) {
			comandSqlResult = "CREATE TABLE DS_r AS (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,t0.ME_1 AS ME_1,t0.ME_2 AS ME_2,t0.ME_3 AS ME_3,t0.ME_4 AS ME_4,t0.ME_5 AS ME_5,t0.ME_6 AS ME_6,t0.ME_7 AS ME_7,t0.ME_8 AS ME_8,vtl_cast_to_string(t0.ME_5,'TIME','YYYY-MM-DD/YYYY-MM-DD') AS ME_9 FROM DS_62 t0);CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);";
		} else if (profileActive.equalsIgnoreCase("postgreSql")) {
			comandSqlResult = "CREATE TABLE DS_r AS (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,t0.ME_1 AS ME_1,t0.ME_2 AS ME_2,t0.ME_3 AS ME_3,t0.ME_4 AS ME_4,t0.ME_5 AS ME_5,t0.ME_6 AS ME_6,t0.ME_7 AS ME_7,t0.ME_8 AS ME_8,vtl_cast_to_string(t0.ME_5,'TIME','YYYY-MM-DD/YYYY-MM-DD') AS ME_9 FROM DS_62 t0);CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);";
		}
		//TODO
		String commandStatements = "DS_r := DS_62[calc ME_9 := cast(ME_5, string, \"YYYY-MM-DD/YYYY-MM-DD\")];";
		String result = translationUtilTest.translate(commandStatements);
		System.out.println("Print queryCastToStringFromTime: " +result);
		System.out.println("Print queryAttesaCastToStringFromTime: " +comandSqlResult);
		assertEquals("Test operator CastToStringFromTime", comandSqlResult, result);
	}
	
	@Test
	@Transactional
	public void castToIntFromString() throws Exception {
		dbTableUtilityService.resetSchema("t");
		VtlDataset vtlDatasetFind = datasetRepository.findByNameIgnoreCase("DS_1");
		if (vtlDatasetFind == null) {
			VtlDataset vtlDataset = new VtlDataset();

			vtlDataset.setPersistent(true);
			vtlDataset.setName("DS_62");
			vtlDataset.setIsOnlyAScalar(false);
			vtlDataset.setTransitory(false);
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ID_1", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ID_2", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ME_1", VtlType.INTEGER, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ME_2", VtlType.NUMBER, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ME_3", VtlType.DATE, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ME_4", VtlType.BOOLEAN, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ME_5", VtlType.TIME, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ME_6", VtlType.TIME_PERIOD, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ME_7", VtlType.DURATION, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ME_8", VtlType.STRING, VtlComponentRole.MEASURE));
			datasetRepository.save(vtlDataset);
		}
		
		System.out.println("Il profilo attivo è " + environment.getActiveProfiles()[0]);

		String comandSqlResult = "";
		String profileActive = environment.getActiveProfiles()[0];
		if (profileActive.equalsIgnoreCase("oracleSql")) {
			comandSqlResult = "CREATE TABLE DS_r AS (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,t0.ME_1 AS ME_1,t0.ME_2 AS ME_2,t0.ME_3 AS ME_3,t0.ME_4 AS ME_4,t0.ME_5 AS ME_5,t0.ME_6 AS ME_6,t0.ME_7 AS ME_7,t0.ME_8 AS ME_8,vtl_pkg.cast_to_int(t0.ME_8,'STRING','') AS ME_9 FROM DS_62 t0);CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);";
		} else if (profileActive.equalsIgnoreCase("sqlServer")) {
			comandSqlResult = "SELECT t1.* INTO DS_r FROM (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,t0.ME_1 AS ME_1,t0.ME_2 AS ME_2,t0.ME_3 AS ME_3,t0.ME_4 AS ME_4,t0.ME_5 AS ME_5,t0.ME_6 AS ME_6,t0.ME_7 AS ME_7,t0.ME_8 AS ME_8,dbo.vtl_cast_to_int(t0.ME_8,'STRING','') AS ME_9 FROM DS_62 t0) t1;"+"CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);";
		} else if (profileActive.equalsIgnoreCase("mySql")) {
			comandSqlResult = "CREATE TABLE DS_r AS (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,t0.ME_1 AS ME_1,t0.ME_2 AS ME_2,t0.ME_3 AS ME_3,t0.ME_4 AS ME_4,t0.ME_5 AS ME_5,t0.ME_6 AS ME_6,t0.ME_7 AS ME_7,t0.ME_8 AS ME_8,vtl_cast_to_int(t0.ME_8,'STRING','') AS ME_9 FROM DS_62 t0);CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);";
		} else if (profileActive.equalsIgnoreCase("postgreSql")) {
			comandSqlResult = "CREATE TABLE DS_r AS (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,t0.ME_1 AS ME_1,t0.ME_2 AS ME_2,t0.ME_3 AS ME_3,t0.ME_4 AS ME_4,t0.ME_5 AS ME_5,t0.ME_6 AS ME_6,t0.ME_7 AS ME_7,t0.ME_8 AS ME_8,vtl_cast_to_int(t0.ME_8,'STRING','') AS ME_9 FROM DS_62 t0);CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);";
		}
		
		String commandStatements = "DS_r := DS_62[calc ME_9 := cast(ME_8, integer)];";
		String result = translationUtilTest.translate(commandStatements);
		System.out.println("Print queryCastToIntFromString: " +result);
		System.out.println("Print queryAttesaCastToIntFromString: " +comandSqlResult);
		assertEquals("Test operator CastToIntFromString", comandSqlResult, result);
	}
	
	@Test
	@Transactional
	public void castToIntFromNumber() throws Exception {
		dbTableUtilityService.resetSchema("t");
		VtlDataset vtlDatasetFind = datasetRepository.findByNameIgnoreCase("DS_1");
		if (vtlDatasetFind == null) {
			VtlDataset vtlDataset = new VtlDataset();

			vtlDataset.setPersistent(true);
			vtlDataset.setName("DS_62");
			vtlDataset.setIsOnlyAScalar(false);
			vtlDataset.setTransitory(false);
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ID_1", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ID_2", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ME_1", VtlType.INTEGER, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ME_2", VtlType.NUMBER, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ME_3", VtlType.DATE, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ME_4", VtlType.BOOLEAN, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ME_5", VtlType.TIME, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ME_6", VtlType.TIME_PERIOD, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ME_7", VtlType.DURATION, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ME_8", VtlType.STRING, VtlComponentRole.MEASURE));
			datasetRepository.save(vtlDataset);
		}
		
		System.out.println("Il profilo attivo è " + environment.getActiveProfiles()[0]);

		String comandSqlResult = "";
		String profileActive = environment.getActiveProfiles()[0];
		if (profileActive.equalsIgnoreCase("oracleSql")) {
			comandSqlResult = "CREATE TABLE DS_r AS (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,t0.ME_1 AS ME_1,t0.ME_2 AS ME_2,t0.ME_3 AS ME_3,t0.ME_4 AS ME_4,t0.ME_5 AS ME_5,t0.ME_6 AS ME_6,t0.ME_7 AS ME_7,t0.ME_8 AS ME_8,vtl_pkg.cast_to_int(t0.ME_2,'NUMBER','') AS ME_9 FROM DS_62 t0);CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);";
		} else if (profileActive.equalsIgnoreCase("sqlServer")) {
			comandSqlResult = "SELECT t1.* INTO DS_r FROM (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,t0.ME_1 AS ME_1,t0.ME_2 AS ME_2,t0.ME_3 AS ME_3,t0.ME_4 AS ME_4,t0.ME_5 AS ME_5,t0.ME_6 AS ME_6,t0.ME_7 AS ME_7,t0.ME_8 AS ME_8,dbo.vtl_cast_to_int(t0.ME_2,'NUMBER','') AS ME_9 FROM DS_62 t0) t1;"+"CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);";
		} else if (profileActive.equalsIgnoreCase("mySql")) {
			comandSqlResult = "CREATE TABLE DS_r AS (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,t0.ME_1 AS ME_1,t0.ME_2 AS ME_2,t0.ME_3 AS ME_3,t0.ME_4 AS ME_4,t0.ME_5 AS ME_5,t0.ME_6 AS ME_6,t0.ME_7 AS ME_7,t0.ME_8 AS ME_8,vtl_cast_to_int(t0.ME_2,'NUMBER','') AS ME_9 FROM DS_62 t0);CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);";
		} else if (profileActive.equalsIgnoreCase("postgreSql")) {
			comandSqlResult = "CREATE TABLE DS_r AS (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,t0.ME_1 AS ME_1,t0.ME_2 AS ME_2,t0.ME_3 AS ME_3,t0.ME_4 AS ME_4,t0.ME_5 AS ME_5,t0.ME_6 AS ME_6,t0.ME_7 AS ME_7,t0.ME_8 AS ME_8,vtl_cast_to_int(t0.ME_2,'NUMBER','') AS ME_9 FROM DS_62 t0);CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);";
		}
		
		String commandStatements = "DS_r := DS_62[calc ME_9 := cast(ME_2, integer)];";
		String result = translationUtilTest.translate(commandStatements);
		System.out.println("Print queryCastToIntFromNumber: " +result);
		System.out.println("Print queryAttesaCastToIntFromNumber: " +comandSqlResult);
		assertEquals("Test operator CastToIntFromNumber", comandSqlResult, result);
	}
	
	@Test
	@Transactional
	public void castToIntFromBoolean() throws Exception {
		dbTableUtilityService.resetSchema("t");
		VtlDataset vtlDatasetFind = datasetRepository.findByNameIgnoreCase("DS_1");
		if (vtlDatasetFind == null) {
			VtlDataset vtlDataset = new VtlDataset();

			vtlDataset.setPersistent(true);
			vtlDataset.setName("DS_62");
			vtlDataset.setIsOnlyAScalar(false);
			vtlDataset.setTransitory(false);
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ID_1", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ID_2", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ME_1", VtlType.INTEGER, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ME_2", VtlType.NUMBER, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ME_3", VtlType.DATE, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ME_4", VtlType.BOOLEAN, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ME_5", VtlType.TIME, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ME_6", VtlType.TIME_PERIOD, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ME_7", VtlType.DURATION, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ME_8", VtlType.STRING, VtlComponentRole.MEASURE));
			datasetRepository.save(vtlDataset);
		}
		
		System.out.println("Il profilo attivo è " + environment.getActiveProfiles()[0]);

		String comandSqlResult = "";
		String profileActive = environment.getActiveProfiles()[0];
		if (profileActive.equalsIgnoreCase("oracleSql")) {
			comandSqlResult = "CREATE TABLE DS_r AS (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,t0.ME_1 AS ME_1,t0.ME_2 AS ME_2,t0.ME_3 AS ME_3,t0.ME_4 AS ME_4,t0.ME_5 AS ME_5,t0.ME_6 AS ME_6,t0.ME_7 AS ME_7,t0.ME_8 AS ME_8,vtl_pkg.cast_to_int(t0.ME_4,'BOOLEAN','') AS ME_9 FROM DS_62 t0);CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);";
		} else if (profileActive.equalsIgnoreCase("sqlServer")) {
			comandSqlResult = "SELECT t1.* INTO DS_r FROM (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,t0.ME_1 AS ME_1,t0.ME_2 AS ME_2,t0.ME_3 AS ME_3,t0.ME_4 AS ME_4,t0.ME_5 AS ME_5,t0.ME_6 AS ME_6,t0.ME_7 AS ME_7,t0.ME_8 AS ME_8,dbo.vtl_cast_to_int(t0.ME_4,'BOOLEAN','') AS ME_9 FROM DS_62 t0) t1;"+"CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);";
		} else if (profileActive.equalsIgnoreCase("mySql")) {
			comandSqlResult = "CREATE TABLE DS_r AS (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,t0.ME_1 AS ME_1,t0.ME_2 AS ME_2,t0.ME_3 AS ME_3,t0.ME_4 AS ME_4,t0.ME_5 AS ME_5,t0.ME_6 AS ME_6,t0.ME_7 AS ME_7,t0.ME_8 AS ME_8,vtl_cast_to_int(t0.ME_4,'BOOLEAN','') AS ME_9 FROM DS_62 t0);CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);";
		} else if (profileActive.equalsIgnoreCase("postgreSql")) {
			comandSqlResult = "CREATE TABLE DS_r AS (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,t0.ME_1 AS ME_1,t0.ME_2 AS ME_2,t0.ME_3 AS ME_3,t0.ME_4 AS ME_4,t0.ME_5 AS ME_5,t0.ME_6 AS ME_6,t0.ME_7 AS ME_7,t0.ME_8 AS ME_8,vtl_cast_to_int(t0.ME_4,'BOOLEAN','') AS ME_9 FROM DS_62 t0);CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);";
		}
		
		String commandStatements = "DS_r := DS_62[calc ME_9 := cast(ME_4, integer)];";
		String result = translationUtilTest.translate(commandStatements);
		System.out.println("Print queryCastToIntFromNumber: " +result);
		System.out.println("Print queryAttesaCastToIntFromNumber: " +comandSqlResult);
		assertEquals("Test operator CastToIntFromNumber", comandSqlResult, result);
	}
	
	@Test
	@Transactional
	public void castToNumberFromString() throws Exception {
		dbTableUtilityService.resetSchema("t");
		VtlDataset vtlDatasetFind = datasetRepository.findByNameIgnoreCase("DS_1");
		if (vtlDatasetFind == null) {
			VtlDataset vtlDataset = new VtlDataset();

			vtlDataset.setPersistent(true);
			vtlDataset.setName("DS_62");
			vtlDataset.setIsOnlyAScalar(false);
			vtlDataset.setTransitory(false);
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ID_1", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ID_2", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ME_1", VtlType.INTEGER, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ME_2", VtlType.NUMBER, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ME_3", VtlType.DATE, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ME_4", VtlType.BOOLEAN, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ME_5", VtlType.TIME, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ME_6", VtlType.TIME_PERIOD, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ME_7", VtlType.DURATION, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ME_8", VtlType.STRING, VtlComponentRole.MEASURE));
			datasetRepository.save(vtlDataset);
		}
		
		System.out.println("Il profilo attivo è " + environment.getActiveProfiles()[0]);

		String comandSqlResult = "";
		String profileActive = environment.getActiveProfiles()[0];
		if (profileActive.equalsIgnoreCase("oracleSql")) {
			comandSqlResult = "CREATE TABLE DS_r AS (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,t0.ME_1 AS ME_1,t0.ME_2 AS ME_2,t0.ME_3 AS ME_3,t0.ME_4 AS ME_4,t0.ME_5 AS ME_5,t0.ME_6 AS ME_6,t0.ME_7 AS ME_7,t0.ME_8 AS ME_8,vtl_pkg.cast_to_number(t0.ME_8,'STRING','DD,DD') AS ME_9 FROM DS_62 t0);CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);";
		} else if (profileActive.equalsIgnoreCase("sqlServer")) {
			comandSqlResult = "SELECT t1.* INTO DS_r FROM (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,t0.ME_1 AS ME_1,t0.ME_2 AS ME_2,t0.ME_3 AS ME_3,t0.ME_4 AS ME_4,t0.ME_5 AS ME_5,t0.ME_6 AS ME_6,t0.ME_7 AS ME_7,t0.ME_8 AS ME_8,dbo.vtl_cast_to_number(t0.ME_8,'STRING','DD,DD') AS ME_9 FROM DS_62 t0) t1;"+"CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);";
		} else if (profileActive.equalsIgnoreCase("mySql")) {
			comandSqlResult = "CREATE TABLE DS_r AS (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,t0.ME_1 AS ME_1,t0.ME_2 AS ME_2,t0.ME_3 AS ME_3,t0.ME_4 AS ME_4,t0.ME_5 AS ME_5,t0.ME_6 AS ME_6,t0.ME_7 AS ME_7,t0.ME_8 AS ME_8,vtl_cast_to_number(t0.ME_8,'STRING','DD,DD') AS ME_9 FROM DS_62 t0);CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);";
		} else if (profileActive.equalsIgnoreCase("postgreSql")) {
			comandSqlResult = "CREATE TABLE DS_r AS (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,t0.ME_1 AS ME_1,t0.ME_2 AS ME_2,t0.ME_3 AS ME_3,t0.ME_4 AS ME_4,t0.ME_5 AS ME_5,t0.ME_6 AS ME_6,t0.ME_7 AS ME_7,t0.ME_8 AS ME_8,vtl_cast_to_number(t0.ME_8,'STRING','DD,DD') AS ME_9 FROM DS_62 t0);CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);";
		}
		String commandStatements = "DS_r := DS_62[calc ME_9 := cast(ME_8, number, \"DD,DD\")];";
		String result = translationUtilTest.translate(commandStatements);
		System.out.println("Print queryCastToNumberFromString: " +result);
		System.out.println("Print queryAttesaCastToNumberFromString: " +comandSqlResult);
		assertEquals("Test operator CastToNumberFromString", comandSqlResult, result);
	}

	@Test
	@Transactional
	public void castToNumberFromInteger() throws Exception {
		dbTableUtilityService.resetSchema("t");
		VtlDataset vtlDatasetFind = datasetRepository.findByNameIgnoreCase("DS_1");
		if (vtlDatasetFind == null) {
			VtlDataset vtlDataset = new VtlDataset();

			vtlDataset.setPersistent(true);
			vtlDataset.setName("DS_62");
			vtlDataset.setIsOnlyAScalar(false);
			vtlDataset.setTransitory(false);
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ID_1", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ID_2", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ME_1", VtlType.INTEGER, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ME_2", VtlType.NUMBER, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ME_3", VtlType.DATE, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ME_4", VtlType.BOOLEAN, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ME_5", VtlType.TIME, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ME_6", VtlType.TIME_PERIOD, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ME_7", VtlType.DURATION, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ME_8", VtlType.STRING, VtlComponentRole.MEASURE));
			datasetRepository.save(vtlDataset);
		}
		
		System.out.println("Il profilo attivo è " + environment.getActiveProfiles()[0]);

		String comandSqlResult = "";
		String profileActive = environment.getActiveProfiles()[0];
		if (profileActive.equalsIgnoreCase("oracleSql")) {
			comandSqlResult = "CREATE TABLE DS_r AS (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,t0.ME_1 AS ME_1,t0.ME_2 AS ME_2,t0.ME_3 AS ME_3,t0.ME_4 AS ME_4,t0.ME_5 AS ME_5,t0.ME_6 AS ME_6,t0.ME_7 AS ME_7,t0.ME_8 AS ME_8,vtl_pkg.cast_to_number(t0.ME_1,'INTEGER','') AS ME_9 FROM DS_62 t0);CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);";
		} else if (profileActive.equalsIgnoreCase("sqlServer")) {
			comandSqlResult = "SELECT t1.* INTO DS_r FROM (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,t0.ME_1 AS ME_1,t0.ME_2 AS ME_2,t0.ME_3 AS ME_3,t0.ME_4 AS ME_4,t0.ME_5 AS ME_5,t0.ME_6 AS ME_6,t0.ME_7 AS ME_7,t0.ME_8 AS ME_8,dbo.vtl_cast_to_number(t0.ME_1,'INTEGER','') AS ME_9 FROM DS_62 t0) t1;"+"CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);";
		} else if (profileActive.equalsIgnoreCase("mySql")) {
			comandSqlResult = "CREATE TABLE DS_r AS (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,t0.ME_1 AS ME_1,t0.ME_2 AS ME_2,t0.ME_3 AS ME_3,t0.ME_4 AS ME_4,t0.ME_5 AS ME_5,t0.ME_6 AS ME_6,t0.ME_7 AS ME_7,t0.ME_8 AS ME_8,vtl_cast_to_number(t0.ME_1,'INTEGER','') AS ME_9 FROM DS_62 t0);CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);";
		} else if (profileActive.equalsIgnoreCase("postgreSql")) {
			comandSqlResult = "CREATE TABLE DS_r AS (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,t0.ME_1 AS ME_1,t0.ME_2 AS ME_2,t0.ME_3 AS ME_3,t0.ME_4 AS ME_4,t0.ME_5 AS ME_5,t0.ME_6 AS ME_6,t0.ME_7 AS ME_7,t0.ME_8 AS ME_8,vtl_cast_to_number(t0.ME_1,'INTEGER','') AS ME_9 FROM DS_62 t0);CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);";
		}
		
		String commandStatements = "DS_r := DS_62[calc ME_9 := cast(ME_1, number)];";
		String result = translationUtilTest.translate(commandStatements);
		System.out.println("Print queryCastToNumberFromInteger: " +result);
		System.out.println("Print queryAttesaCastToNumberFromInteger: " +comandSqlResult);
		assertEquals("Test operator CastToNumberFromInteger", comandSqlResult, result);
	}
	
	@Test
	@Transactional
	public void castToNumberFromBoolean() throws Exception {
		dbTableUtilityService.resetSchema("t");
		VtlDataset vtlDatasetFind = datasetRepository.findByNameIgnoreCase("DS_1");
		if (vtlDatasetFind == null) {
			VtlDataset vtlDataset = new VtlDataset();

			vtlDataset.setPersistent(true);
			vtlDataset.setName("DS_62");
			vtlDataset.setIsOnlyAScalar(false);
			vtlDataset.setTransitory(false);
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ID_1", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ID_2", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ME_1", VtlType.INTEGER, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ME_2", VtlType.NUMBER, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ME_3", VtlType.DATE, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ME_4", VtlType.BOOLEAN, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ME_5", VtlType.TIME, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ME_6", VtlType.TIME_PERIOD, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ME_7", VtlType.DURATION, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ME_8", VtlType.STRING, VtlComponentRole.MEASURE));
			datasetRepository.save(vtlDataset);
		}
		
		System.out.println("Il profilo attivo è " + environment.getActiveProfiles()[0]);

		String comandSqlResult = "";
		String profileActive = environment.getActiveProfiles()[0];
		if (profileActive.equalsIgnoreCase("oracleSql")) {
			comandSqlResult = "CREATE TABLE DS_r AS (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,t0.ME_1 AS ME_1,t0.ME_2 AS ME_2,t0.ME_3 AS ME_3,t0.ME_4 AS ME_4,t0.ME_5 AS ME_5,t0.ME_6 AS ME_6,t0.ME_7 AS ME_7,t0.ME_8 AS ME_8,vtl_pkg.cast_to_number(t0.ME_4,'BOOLEAN','') AS ME_9 FROM DS_62 t0);CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);";
		} else if (profileActive.equalsIgnoreCase("sqlServer")) {
			comandSqlResult = "SELECT t1.* INTO DS_r FROM (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,t0.ME_1 AS ME_1,t0.ME_2 AS ME_2,t0.ME_3 AS ME_3,t0.ME_4 AS ME_4,t0.ME_5 AS ME_5,t0.ME_6 AS ME_6,t0.ME_7 AS ME_7,t0.ME_8 AS ME_8,dbo.vtl_cast_to_number(t0.ME_4,'BOOLEAN','') AS ME_9 FROM DS_62 t0) t1;"+"CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);";
		} else if (profileActive.equalsIgnoreCase("mySql")) {
			comandSqlResult = "CREATE TABLE DS_r AS (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,t0.ME_1 AS ME_1,t0.ME_2 AS ME_2,t0.ME_3 AS ME_3,t0.ME_4 AS ME_4,t0.ME_5 AS ME_5,t0.ME_6 AS ME_6,t0.ME_7 AS ME_7,t0.ME_8 AS ME_8,vtl_cast_to_number(t0.ME_4,'BOOLEAN','') AS ME_9 FROM DS_62 t0);CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);";
		} else if (profileActive.equalsIgnoreCase("postgreSql")) {
			comandSqlResult = "CREATE TABLE DS_r AS (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,t0.ME_1 AS ME_1,t0.ME_2 AS ME_2,t0.ME_3 AS ME_3,t0.ME_4 AS ME_4,t0.ME_5 AS ME_5,t0.ME_6 AS ME_6,t0.ME_7 AS ME_7,t0.ME_8 AS ME_8,vtl_cast_to_number(t0.ME_4,'BOOLEAN','') AS ME_9 FROM DS_62 t0);CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);";
		}
		
		String commandStatements = "DS_r := DS_62[calc ME_9 := cast(ME_4, number)];";
		String result = translationUtilTest.translate(commandStatements);
		System.out.println("Print queryCastToNumberFromBoolean: " +result);
		System.out.println("Print queryAttesaCastToNumberFromBoolean: " +comandSqlResult);
		assertEquals("Test operator CastToNumberFromBoolean", comandSqlResult, result);
	}
	
	@Test
	@Transactional
	public void castToBooleanFromInteger() throws Exception {
		dbTableUtilityService.resetSchema("t");
		VtlDataset vtlDatasetFind = datasetRepository.findByNameIgnoreCase("DS_1");
		if (vtlDatasetFind == null) {
			VtlDataset vtlDataset = new VtlDataset();

			vtlDataset.setPersistent(true);
			vtlDataset.setName("DS_62");
			vtlDataset.setIsOnlyAScalar(false);
			vtlDataset.setTransitory(false);
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ID_1", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ID_2", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ME_1", VtlType.INTEGER, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ME_2", VtlType.NUMBER, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ME_3", VtlType.DATE, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ME_4", VtlType.BOOLEAN, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ME_5", VtlType.TIME, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ME_6", VtlType.TIME_PERIOD, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ME_7", VtlType.DURATION, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ME_8", VtlType.STRING, VtlComponentRole.MEASURE));
			datasetRepository.save(vtlDataset);
		}
		
		System.out.println("Il profilo attivo è " + environment.getActiveProfiles()[0]);

		String comandSqlResult = "";
		String profileActive = environment.getActiveProfiles()[0];
		if (profileActive.equalsIgnoreCase("oracleSql")) {
			comandSqlResult = "CREATE TABLE DS_r AS (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,t0.ME_1 AS ME_1,t0.ME_2 AS ME_2,t0.ME_3 AS ME_3,t0.ME_4 AS ME_4,t0.ME_5 AS ME_5,t0.ME_6 AS ME_6,t0.ME_7 AS ME_7,t0.ME_8 AS ME_8,vtl_pkg.cast_to_boolean(t0.ME_1,'INTEGER','') AS ME_9 FROM DS_62 t0);CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);";
		} else if (profileActive.equalsIgnoreCase("sqlServer")) {
			comandSqlResult = "SELECT t1.* INTO DS_r FROM (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,t0.ME_1 AS ME_1,t0.ME_2 AS ME_2,t0.ME_3 AS ME_3,t0.ME_4 AS ME_4,t0.ME_5 AS ME_5,t0.ME_6 AS ME_6,t0.ME_7 AS ME_7,t0.ME_8 AS ME_8,dbo.vtl_cast_to_boolean(t0.ME_1,'INTEGER','') AS ME_9 FROM DS_62 t0) t1;"+"CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);";
		} else if (profileActive.equalsIgnoreCase("mySql")) {
			comandSqlResult = "CREATE TABLE DS_r AS (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,t0.ME_1 AS ME_1,t0.ME_2 AS ME_2,t0.ME_3 AS ME_3,t0.ME_4 AS ME_4,t0.ME_5 AS ME_5,t0.ME_6 AS ME_6,t0.ME_7 AS ME_7,t0.ME_8 AS ME_8,vtl_cast_to_boolean(t0.ME_1,'INTEGER','') AS ME_9 FROM DS_62 t0);CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);";
		} else if (profileActive.equalsIgnoreCase("postgreSql")) {
			comandSqlResult = "CREATE TABLE DS_r AS (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,t0.ME_1 AS ME_1,t0.ME_2 AS ME_2,t0.ME_3 AS ME_3,t0.ME_4 AS ME_4,t0.ME_5 AS ME_5,t0.ME_6 AS ME_6,t0.ME_7 AS ME_7,t0.ME_8 AS ME_8,vtl_cast_to_boolean(t0.ME_1,'INTEGER','') AS ME_9 FROM DS_62 t0);CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);";
		}
		
		String commandStatements = "DS_r := DS_62[calc ME_9 := cast(ME_1, boolean)];";
		String result = translationUtilTest.translate(commandStatements);
		System.out.println("Print queryCastToBooleanFromInteger: " +result);
		System.out.println("Print queryAttesaCastToBooleanFromInteger: " +comandSqlResult);
		assertEquals("Test operator CastToBooleanFromInteger", comandSqlResult, result);
	}
	
	@Test
	@Transactional
	public void castToBooleanFromNumber() throws Exception {
		dbTableUtilityService.resetSchema("t");
		VtlDataset vtlDatasetFind = datasetRepository.findByNameIgnoreCase("DS_1");
		if (vtlDatasetFind == null) {
			VtlDataset vtlDataset = new VtlDataset();

			vtlDataset.setPersistent(true);
			vtlDataset.setName("DS_62");
			vtlDataset.setIsOnlyAScalar(false);
			vtlDataset.setTransitory(false);
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ID_1", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ID_2", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ME_1", VtlType.INTEGER, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ME_2", VtlType.NUMBER, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ME_3", VtlType.DATE, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ME_4", VtlType.BOOLEAN, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ME_5", VtlType.TIME, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ME_6", VtlType.TIME_PERIOD, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ME_7", VtlType.DURATION, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ME_8", VtlType.STRING, VtlComponentRole.MEASURE));
			datasetRepository.save(vtlDataset);
		}
		
		System.out.println("Il profilo attivo è " + environment.getActiveProfiles()[0]);

		String comandSqlResult = "";
		String profileActive = environment.getActiveProfiles()[0];
		if (profileActive.equalsIgnoreCase("oracleSql")) {
			comandSqlResult = "CREATE TABLE DS_r AS (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,t0.ME_1 AS ME_1,t0.ME_2 AS ME_2,t0.ME_3 AS ME_3,t0.ME_4 AS ME_4,t0.ME_5 AS ME_5,t0.ME_6 AS ME_6,t0.ME_7 AS ME_7,t0.ME_8 AS ME_8,vtl_pkg.cast_to_boolean(t0.ME_2,'NUMBER','') AS ME_9 FROM DS_62 t0);CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);";
		} else if (profileActive.equalsIgnoreCase("sqlServer")) {
			comandSqlResult = "SELECT t1.* INTO DS_r FROM (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,t0.ME_1 AS ME_1,t0.ME_2 AS ME_2,t0.ME_3 AS ME_3,t0.ME_4 AS ME_4,t0.ME_5 AS ME_5,t0.ME_6 AS ME_6,t0.ME_7 AS ME_7,t0.ME_8 AS ME_8,dbo.vtl_cast_to_boolean(t0.ME_2,'NUMBER','') AS ME_9 FROM DS_62 t0) t1;"+"CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);";
		} else if (profileActive.equalsIgnoreCase("mySql")) {
			comandSqlResult = "CREATE TABLE DS_r AS (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,t0.ME_1 AS ME_1,t0.ME_2 AS ME_2,t0.ME_3 AS ME_3,t0.ME_4 AS ME_4,t0.ME_5 AS ME_5,t0.ME_6 AS ME_6,t0.ME_7 AS ME_7,t0.ME_8 AS ME_8,vtl_cast_to_boolean(t0.ME_2,'NUMBER','') AS ME_9 FROM DS_62 t0);CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);";
		} else if (profileActive.equalsIgnoreCase("postgreSql")) {
			comandSqlResult = "CREATE TABLE DS_r AS (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,t0.ME_1 AS ME_1,t0.ME_2 AS ME_2,t0.ME_3 AS ME_3,t0.ME_4 AS ME_4,t0.ME_5 AS ME_5,t0.ME_6 AS ME_6,t0.ME_7 AS ME_7,t0.ME_8 AS ME_8,vtl_cast_to_boolean(t0.ME_2,'NUMBER','') AS ME_9 FROM DS_62 t0);CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);";
		}
		
		String commandStatements = "DS_r := DS_62[calc ME_9 := cast(ME_2, boolean)];";
		String result = translationUtilTest.translate(commandStatements);
		System.out.println("Print queryCastToBooleanFromNumber: " +result);
		System.out.println("Print queryAttesaCastToBooleanFromNumber: " +comandSqlResult);
		assertEquals("Test operator CastToBooleanFromNumber", comandSqlResult, result);
	}
	
	@Test
	@Transactional
	public void castToTimePeriodFromDate() throws Exception {
		dbTableUtilityService.resetSchema("t");
		VtlDataset vtlDatasetFind = datasetRepository.findByNameIgnoreCase("DS_1");
		if (vtlDatasetFind == null) {
			VtlDataset vtlDataset = new VtlDataset();

			vtlDataset.setPersistent(true);
			vtlDataset.setName("DS_62");
			vtlDataset.setIsOnlyAScalar(false);
			vtlDataset.setTransitory(false);
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ID_1", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ID_2", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ME_1", VtlType.INTEGER, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ME_2", VtlType.NUMBER, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ME_3", VtlType.DATE, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ME_4", VtlType.BOOLEAN, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ME_5", VtlType.TIME, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ME_6", VtlType.TIME_PERIOD, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ME_7", VtlType.DURATION, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ME_8", VtlType.STRING, VtlComponentRole.MEASURE));
			datasetRepository.save(vtlDataset);
		}
		
		System.out.println("Il profilo attivo è " + environment.getActiveProfiles()[0]);

		String comandSqlResult = "";
		String profileActive = environment.getActiveProfiles()[0];
		if (profileActive.equalsIgnoreCase("oracleSql")) {
			comandSqlResult = "CREATE TABLE DS_r AS (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,t0.ME_1 AS ME_1,t0.ME_2 AS ME_2,t0.ME_3 AS ME_3,t0.ME_4 AS ME_4,t0.ME_5 AS ME_5,t0.ME_6 AS ME_6,t0.ME_7 AS ME_7,t0.ME_8 AS ME_8,vtl_pkg.cast_to_timeperiod(t0.ME_3,'DATE','YYYY-MM-DD') AS ME_9 FROM DS_62 t0);CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);";
		} else if (profileActive.equalsIgnoreCase("sqlServer")) {
			comandSqlResult = "SELECT t1.* INTO DS_r FROM (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,t0.ME_1 AS ME_1,t0.ME_2 AS ME_2,t0.ME_3 AS ME_3,t0.ME_4 AS ME_4,t0.ME_5 AS ME_5,t0.ME_6 AS ME_6,t0.ME_7 AS ME_7,t0.ME_8 AS ME_8,dbo.vtl_cast_to_timeperiod(t0.ME_3,'DATE','YYYY-MM-DD') AS ME_9 FROM DS_62 t0) t1;"+"CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);";
		} else if (profileActive.equalsIgnoreCase("mySql")) {
			comandSqlResult = "CREATE TABLE DS_r AS (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,t0.ME_1 AS ME_1,t0.ME_2 AS ME_2,t0.ME_3 AS ME_3,t0.ME_4 AS ME_4,t0.ME_5 AS ME_5,t0.ME_6 AS ME_6,t0.ME_7 AS ME_7,t0.ME_8 AS ME_8,vtl_cast_to_timeperiod(t0.ME_3,'DATE','YYYY-MM-DD') AS ME_9 FROM DS_62 t0);CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);";
		} else if (profileActive.equalsIgnoreCase("postgreSql")) {
			comandSqlResult = "CREATE TABLE DS_r AS (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,t0.ME_1 AS ME_1,t0.ME_2 AS ME_2,t0.ME_3 AS ME_3,t0.ME_4 AS ME_4,t0.ME_5 AS ME_5,t0.ME_6 AS ME_6,t0.ME_7 AS ME_7,t0.ME_8 AS ME_8,vtl_cast_to_timeperiod(t0.ME_3,'DATE','YYYY-MM-DD') AS ME_9 FROM DS_62 t0);CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);";
		}
		
		String commandStatements = "DS_r := DS_62[calc ME_9 := cast(ME_3, time_period, \"YYYY-MM-DD\")];";
		String result = translationUtilTest.translate(commandStatements);
		System.out.println("Print queryCastToTimePeriodFromDate: " +result);
		System.out.println("Print queryAttesaCastToTimePeriodFromDate: " +comandSqlResult);
		assertEquals("Test operator CastToTimePeriodFromDate", comandSqlResult, result);
	}
	
	@Test
	@Transactional
	public void castToTimePeriodFromString() throws Exception {
		dbTableUtilityService.resetSchema("t");
		VtlDataset vtlDatasetFind = datasetRepository.findByNameIgnoreCase("DS_1");
		if (vtlDatasetFind == null) {
			VtlDataset vtlDataset = new VtlDataset();

			vtlDataset.setPersistent(true);
			vtlDataset.setName("DS_62");
			vtlDataset.setIsOnlyAScalar(false);
			vtlDataset.setTransitory(false);
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ID_1", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ID_2", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ME_1", VtlType.INTEGER, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ME_2", VtlType.NUMBER, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ME_3", VtlType.DATE, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ME_4", VtlType.BOOLEAN, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ME_5", VtlType.TIME, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ME_6", VtlType.TIME_PERIOD, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ME_7", VtlType.DURATION, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ME_8", VtlType.STRING, VtlComponentRole.MEASURE));
			datasetRepository.save(vtlDataset);
		}
		
		System.out.println("Il profilo attivo è " + environment.getActiveProfiles()[0]);

		String comandSqlResult = "";
		String profileActive = environment.getActiveProfiles()[0];
		if (profileActive.equalsIgnoreCase("oracleSql")) {
			comandSqlResult = "CREATE TABLE DS_r AS (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,t0.ME_1 AS ME_1,t0.ME_2 AS ME_2,t0.ME_3 AS ME_3,t0.ME_4 AS ME_4,t0.ME_5 AS ME_5,t0.ME_6 AS ME_6,t0.ME_7 AS ME_7,t0.ME_8 AS ME_8,vtl_pkg.cast_to_timeperiod(t0.ME_8,'STRING','YYYY\\QQ') AS ME_9 FROM DS_62 t0);CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);";
		} else if (profileActive.equalsIgnoreCase("sqlServer")) {
			comandSqlResult = "SELECT t1.* INTO DS_r FROM (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,t0.ME_1 AS ME_1,t0.ME_2 AS ME_2,t0.ME_3 AS ME_3,t0.ME_4 AS ME_4,t0.ME_5 AS ME_5,t0.ME_6 AS ME_6,t0.ME_7 AS ME_7,t0.ME_8 AS ME_8,dbo.vtl_cast_to_timeperiod(t0.ME_8,'STRING','YYYY\\QQ') AS ME_9 FROM DS_62 t0) t1;"+"CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);";
		} else if (profileActive.equalsIgnoreCase("mySql")) {
			comandSqlResult = "CREATE TABLE DS_r AS (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,t0.ME_1 AS ME_1,t0.ME_2 AS ME_2,t0.ME_3 AS ME_3,t0.ME_4 AS ME_4,t0.ME_5 AS ME_5,t0.ME_6 AS ME_6,t0.ME_7 AS ME_7,t0.ME_8 AS ME_8,vtl_cast_to_timeperiod(t0.ME_8,'STRING','YYYY\\QQ') AS ME_9 FROM DS_62 t0);CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);";
		} else if (profileActive.equalsIgnoreCase("postgreSql")) {
			comandSqlResult = "CREATE TABLE DS_r AS (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,t0.ME_1 AS ME_1,t0.ME_2 AS ME_2,t0.ME_3 AS ME_3,t0.ME_4 AS ME_4,t0.ME_5 AS ME_5,t0.ME_6 AS ME_6,t0.ME_7 AS ME_7,t0.ME_8 AS ME_8,vtl_cast_to_timeperiod(t0.ME_8,'STRING','YYYY\\QQ') AS ME_9 FROM DS_62 t0);CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);";
		}
		//TODO
		String commandStatements = "DS_r := DS_62[calc ME_9 := cast(ME_8, time_period, \"YYYY\\QQ\" )];";
		String result = translationUtilTest.translate(commandStatements);
		System.out.println("Print queryCastToTimePeriodFromString: " +result);
		System.out.println("Print queryAttesaCastToTimePeriodFromString: " +comandSqlResult);
		assertEquals("Test operator CastToTimePeriodFromString", comandSqlResult, result);
	}
	
	@Test
	@Transactional
	public void castToDateFromString() throws Exception {
		dbTableUtilityService.resetSchema("t");
		VtlDataset vtlDatasetFind = datasetRepository.findByNameIgnoreCase("DS_1");
		if (vtlDatasetFind == null) {
			VtlDataset vtlDataset = new VtlDataset();

			vtlDataset.setPersistent(true);
			vtlDataset.setName("DS_62");
			vtlDataset.setIsOnlyAScalar(false);
			vtlDataset.setTransitory(false);
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ID_1", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ID_2", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ME_1", VtlType.INTEGER, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ME_2", VtlType.NUMBER, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ME_3", VtlType.DATE, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ME_4", VtlType.BOOLEAN, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ME_5", VtlType.TIME, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ME_6", VtlType.TIME_PERIOD, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ME_7", VtlType.DURATION, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ME_8", VtlType.STRING, VtlComponentRole.MEASURE));
			datasetRepository.save(vtlDataset);
		}
		
		System.out.println("Il profilo attivo è " + environment.getActiveProfiles()[0]);

		String comandSqlResult = "";
		String profileActive = environment.getActiveProfiles()[0];
		if (profileActive.equalsIgnoreCase("oracleSql")) {
			comandSqlResult = "CREATE TABLE DS_r AS (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,t0.ME_1 AS ME_1,t0.ME_2 AS ME_2,t0.ME_3 AS ME_3,t0.ME_4 AS ME_4,t0.ME_5 AS ME_5,t0.ME_6 AS ME_6,t0.ME_7 AS ME_7,t0.ME_8 AS ME_8,vtl_pkg.cast_to_date(t0.ME_8,'STRING','YYYY-MM-DD') AS ME_9 FROM DS_62 t0);CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);";
		} else if (profileActive.equalsIgnoreCase("sqlServer")) {
			comandSqlResult = "SELECT t1.* INTO DS_r FROM (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,t0.ME_1 AS ME_1,t0.ME_2 AS ME_2,t0.ME_3 AS ME_3,t0.ME_4 AS ME_4,t0.ME_5 AS ME_5,t0.ME_6 AS ME_6,t0.ME_7 AS ME_7,t0.ME_8 AS ME_8,dbo.vtl_cast_to_date(t0.ME_8,'STRING','YYYY-MM-DD') AS ME_9 FROM DS_62 t0) t1;"+"CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);";
		} else if (profileActive.equalsIgnoreCase("mySql")) {
			comandSqlResult = "CREATE TABLE DS_r AS (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,t0.ME_1 AS ME_1,t0.ME_2 AS ME_2,t0.ME_3 AS ME_3,t0.ME_4 AS ME_4,t0.ME_5 AS ME_5,t0.ME_6 AS ME_6,t0.ME_7 AS ME_7,t0.ME_8 AS ME_8,vtl_cast_to_date(t0.ME_8,'STRING','YYYY-MM-DD') AS ME_9 FROM DS_62 t0);CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);";
		} else if (profileActive.equalsIgnoreCase("postgreSql")) {
			comandSqlResult = "CREATE TABLE DS_r AS (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,t0.ME_1 AS ME_1,t0.ME_2 AS ME_2,t0.ME_3 AS ME_3,t0.ME_4 AS ME_4,t0.ME_5 AS ME_5,t0.ME_6 AS ME_6,t0.ME_7 AS ME_7,t0.ME_8 AS ME_8,vtl_cast_to_date(t0.ME_8,'STRING','YYYY-MM-DD') AS ME_9 FROM DS_62 t0);CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);";
		}
		
		String commandStatements = "DS_r := DS_62[calc ME_9 := cast(ME_8, date, \"YYYY-MM-DD\")];";
		String result = translationUtilTest.translate(commandStatements);
		System.out.println("Print queryCastToDateFromString: " +result);
		System.out.println("Print queryAttesaCastToDateFromString: " +comandSqlResult);
		assertEquals("Test operator CastToDateFromString", comandSqlResult, result);
	}
	
	@Test
	@Transactional
	public void castToDateFromTimePeriod() throws Exception {
		dbTableUtilityService.resetSchema("t");
		VtlDataset vtlDatasetFind = datasetRepository.findByNameIgnoreCase("DS_1");
		if (vtlDatasetFind == null) {
			VtlDataset vtlDataset = new VtlDataset();

			vtlDataset.setPersistent(true);
			vtlDataset.setName("DS_62");
			vtlDataset.setIsOnlyAScalar(false);
			vtlDataset.setTransitory(false);
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ID_1", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ID_2", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ME_1", VtlType.INTEGER, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ME_2", VtlType.NUMBER, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ME_3", VtlType.DATE, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ME_4", VtlType.BOOLEAN, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ME_5", VtlType.TIME, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ME_6", VtlType.TIME_PERIOD, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ME_7", VtlType.DURATION, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ME_8", VtlType.STRING, VtlComponentRole.MEASURE));
			datasetRepository.save(vtlDataset);
		}
		
		System.out.println("Il profilo attivo è " + environment.getActiveProfiles()[0]);

		String comandSqlResult = "";
		String profileActive = environment.getActiveProfiles()[0];
		if (profileActive.equalsIgnoreCase("oracleSql")) {
			comandSqlResult = "CREATE TABLE DS_r AS (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,t0.ME_1 AS ME_1,t0.ME_2 AS ME_2,t0.ME_3 AS ME_3,t0.ME_4 AS ME_4,t0.ME_5 AS ME_5,t0.ME_6 AS ME_6,t0.ME_7 AS ME_7,t0.ME_8 AS ME_8,vtl_pkg.cast_to_date(t0.ME_6,'TIME_PERIOD','YYYY-MM-DD') AS ME_9 FROM DS_62 t0);CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);";
		} else if (profileActive.equalsIgnoreCase("sqlServer")) {
			comandSqlResult = "SELECT t1.* INTO DS_r FROM (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,t0.ME_1 AS ME_1,t0.ME_2 AS ME_2,t0.ME_3 AS ME_3,t0.ME_4 AS ME_4,t0.ME_5 AS ME_5,t0.ME_6 AS ME_6,t0.ME_7 AS ME_7,t0.ME_8 AS ME_8,dbo.vtl_cast_to_date(t0.ME_6,'TIME_PERIOD','YYYY-MM-DD') AS ME_9 FROM DS_62 t0) t1;"+"CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);";
		} else if (profileActive.equalsIgnoreCase("mySql")) {
			comandSqlResult = "CREATE TABLE DS_r AS (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,t0.ME_1 AS ME_1,t0.ME_2 AS ME_2,t0.ME_3 AS ME_3,t0.ME_4 AS ME_4,t0.ME_5 AS ME_5,t0.ME_6 AS ME_6,t0.ME_7 AS ME_7,t0.ME_8 AS ME_8,vtl_cast_to_date(t0.ME_6,'TIME_PERIOD','YYYY-MM-DD') AS ME_9 FROM DS_62 t0);CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);";
		} else if (profileActive.equalsIgnoreCase("postgreSql")) {
			comandSqlResult = "CREATE TABLE DS_r AS (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,t0.ME_1 AS ME_1,t0.ME_2 AS ME_2,t0.ME_3 AS ME_3,t0.ME_4 AS ME_4,t0.ME_5 AS ME_5,t0.ME_6 AS ME_6,t0.ME_7 AS ME_7,t0.ME_8 AS ME_8,vtl_cast_to_date(t0.ME_6,'TIME_PERIOD','YYYY-MM-DD') AS ME_9 FROM DS_62 t0);CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);";
		}
		//TODO
		String commandStatements = "DS_r := DS_62[calc ME_9 := cast(ME_6, date, \"YYYY-MM-DD\")];";
//		String commandStatements = "DS_r := DS_62[calc ME_9 := cast(ME_6, date)];";
		String result = translationUtilTest.translate(commandStatements);
		System.out.println("Print queryCastToDateFromTimePeriod: " +result);
		System.out.println("Print queryAttesaCastToDateFromTimePeriod: " +comandSqlResult);
		assertEquals("Test operator CastToDateFromTimePeriod", comandSqlResult, result);
	}
	
	@Test
	@Transactional
	public void castToTimeFromString() throws Exception {
		dbTableUtilityService.resetSchema("t");
		VtlDataset vtlDatasetFind = datasetRepository.findByNameIgnoreCase("DS_1");
		if (vtlDatasetFind == null) {
			VtlDataset vtlDataset = new VtlDataset();

			vtlDataset.setPersistent(true);
			vtlDataset.setName("DS_62");
			vtlDataset.setIsOnlyAScalar(false);
			vtlDataset.setTransitory(false);
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ID_1", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ID_2", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ME_1", VtlType.INTEGER, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ME_2", VtlType.NUMBER, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ME_3", VtlType.DATE, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ME_4", VtlType.BOOLEAN, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ME_5", VtlType.TIME, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ME_6", VtlType.TIME_PERIOD, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ME_7", VtlType.DURATION, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ME_8", VtlType.STRING, VtlComponentRole.MEASURE));
			datasetRepository.save(vtlDataset);
		}
		
		System.out.println("Il profilo attivo è " + environment.getActiveProfiles()[0]);

		String comandSqlResult = "";
		String profileActive = environment.getActiveProfiles()[0];
		if (profileActive.equalsIgnoreCase("oracleSql")) {
			comandSqlResult = "CREATE TABLE DS_r AS (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,t0.ME_1 AS ME_1,t0.ME_2 AS ME_2,t0.ME_3 AS ME_3,t0.ME_4 AS ME_4,t0.ME_5 AS ME_5,t0.ME_6 AS ME_6,t0.ME_7 AS ME_7,t0.ME_8 AS ME_8,vtl_pkg.cast_to_time(t0.ME_8,'STRING','YYYY-MM-DD/YYYY-MM-DD') AS ME_9 FROM DS_62 t0);CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);";
		} else if (profileActive.equalsIgnoreCase("sqlServer")) {
			comandSqlResult = "SELECT t1.* INTO DS_r FROM (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,t0.ME_1 AS ME_1,t0.ME_2 AS ME_2,t0.ME_3 AS ME_3,t0.ME_4 AS ME_4,t0.ME_5 AS ME_5,t0.ME_6 AS ME_6,t0.ME_7 AS ME_7,t0.ME_8 AS ME_8,dbo.vtl_cast_to_time(t0.ME_8,'STRING','YYYY-MM-DD/YYYY-MM-DD') AS ME_9 FROM DS_62 t0) t1;"+"CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);";
		} else if (profileActive.equalsIgnoreCase("mySql")) {
			comandSqlResult = "CREATE TABLE DS_r AS (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,t0.ME_1 AS ME_1,t0.ME_2 AS ME_2,t0.ME_3 AS ME_3,t0.ME_4 AS ME_4,t0.ME_5 AS ME_5,t0.ME_6 AS ME_6,t0.ME_7 AS ME_7,t0.ME_8 AS ME_8,vtl_cast_to_time(t0.ME_8,'STRING','YYYY-MM-DD/YYYY-MM-DD') AS ME_9 FROM DS_62 t0);CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);";
		} else if (profileActive.equalsIgnoreCase("postgreSql")) {
			comandSqlResult = "CREATE TABLE DS_r AS (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,t0.ME_1 AS ME_1,t0.ME_2 AS ME_2,t0.ME_3 AS ME_3,t0.ME_4 AS ME_4,t0.ME_5 AS ME_5,t0.ME_6 AS ME_6,t0.ME_7 AS ME_7,t0.ME_8 AS ME_8,vtl_cast_to_time(t0.ME_8,'STRING','YYYY-MM-DD/YYYY-MM-DD') AS ME_9 FROM DS_62 t0);CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);";
		}
		
		String commandStatements = "DS_r := DS_62[calc ME_9 := cast(ME_8, time, \"YYYY-MM-DD/YYYY-MM-DD\")];";
		String result = translationUtilTest.translate(commandStatements);
		System.out.println("Print queryCastToTimeFromString: " +result);
		System.out.println("Print queryAttesaCastToTimeFromString: " +comandSqlResult);
		assertEquals("Test operator CastToToTimeFromString", comandSqlResult, result);
	}

	@Test
	@Transactional
	public void castToTimeFromDateImplict() throws Exception {
		dbTableUtilityService.resetSchema("t");
		VtlDataset vtlDatasetFind = datasetRepository.findByNameIgnoreCase("DS_1");
		if (vtlDatasetFind == null) {
			VtlDataset vtlDataset = new VtlDataset();

			vtlDataset.setPersistent(true);
			vtlDataset.setName("DS_62");
			vtlDataset.setIsOnlyAScalar(false);
			vtlDataset.setTransitory(false);
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ID_1", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ID_2", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ME_1", VtlType.INTEGER, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ME_2", VtlType.NUMBER, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ME_3", VtlType.DATE, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ME_4", VtlType.BOOLEAN, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ME_5", VtlType.TIME, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ME_6", VtlType.TIME_PERIOD, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ME_7", VtlType.DURATION, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ME_8", VtlType.STRING, VtlComponentRole.MEASURE));
			datasetRepository.save(vtlDataset);
		}
		
		System.out.println("Il profilo attivo è " + environment.getActiveProfiles()[0]);

		String comandSqlResult = "";
		String profileActive = environment.getActiveProfiles()[0];
		if (profileActive.equalsIgnoreCase("oracleSql")) {
			comandSqlResult = "CREATE TABLE DS_r AS (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,t0.ME_1 AS ME_1,t0.ME_2 AS ME_2,t0.ME_3 AS ME_3,t0.ME_4 AS ME_4,t0.ME_5 AS ME_5,t0.ME_6 AS ME_6,t0.ME_7 AS ME_7,t0.ME_8 AS ME_8,vtl_pkg.cast_to_time(t0.ME_3,'DATE','YYYY-MM-DD') AS ME_9 FROM DS_62 t0);CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);";
		} else if (profileActive.equalsIgnoreCase("sqlServer")) {
			comandSqlResult = "SELECT t1.* INTO DS_r FROM (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,t0.ME_1 AS ME_1,t0.ME_2 AS ME_2,t0.ME_3 AS ME_3,t0.ME_4 AS ME_4,t0.ME_5 AS ME_5,t0.ME_6 AS ME_6,t0.ME_7 AS ME_7,t0.ME_8 AS ME_8,dbo.vtl_cast_to_time_date(t0.ME_3,'DATE','YYYY-MM-DD') AS ME_9 FROM DS_62 t0) t1;"+"CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);";
		} else if (profileActive.equalsIgnoreCase("mySql")) {
			comandSqlResult = "CREATE TABLE DS_r AS (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,t0.ME_1 AS ME_1,t0.ME_2 AS ME_2,t0.ME_3 AS ME_3,t0.ME_4 AS ME_4,t0.ME_5 AS ME_5,t0.ME_6 AS ME_6,t0.ME_7 AS ME_7,t0.ME_8 AS ME_8,vtl_cast_to_time_date(t0.ME_3,'DATE','YYYY-MM-DD') AS ME_9 FROM DS_62 t0);CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);";
		} else if (profileActive.equalsIgnoreCase("postgreSql")) {
			comandSqlResult = "CREATE TABLE DS_r AS (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,t0.ME_1 AS ME_1,t0.ME_2 AS ME_2,t0.ME_3 AS ME_3,t0.ME_4 AS ME_4,t0.ME_5 AS ME_5,t0.ME_6 AS ME_6,t0.ME_7 AS ME_7,t0.ME_8 AS ME_8,vtl_cast_to_time(t0.ME_3,'DATE','YYYY-MM-DD') AS ME_9 FROM DS_62 t0);CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);";
		}
		
		String commandStatements = "DS_r := DS_62[calc ME_9 := cast(ME_3, time, \"YYYY-MM-DD\")];";
		String result = translationUtilTest.translate(commandStatements);
		System.out.println("Print queryCastToTimeFromDateImplicit: " +result);
		System.out.println("Print queryAttesaCastToTimeFromDateImplicit: " +comandSqlResult);
		assertEquals("Test operator CastToToTimeFromDateImplicit", comandSqlResult, result);
	}

	@Test
	@Transactional
	public void castToTimeFromTimePeriod() throws Exception {
		dbTableUtilityService.resetSchema("t");
		VtlDataset vtlDatasetFind = datasetRepository.findByNameIgnoreCase("DS_1");
		if (vtlDatasetFind == null) {
			VtlDataset vtlDataset = new VtlDataset();

			vtlDataset.setPersistent(true);
			vtlDataset.setName("DS_62");
			vtlDataset.setIsOnlyAScalar(false);
			vtlDataset.setTransitory(false);
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ID_1", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ID_2", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ME_1", VtlType.INTEGER, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ME_2", VtlType.NUMBER, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ME_3", VtlType.DATE, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ME_4", VtlType.BOOLEAN, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ME_5", VtlType.TIME, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ME_6", VtlType.TIME_PERIOD, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ME_7", VtlType.DURATION, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ME_8", VtlType.STRING, VtlComponentRole.MEASURE));
			datasetRepository.save(vtlDataset);
		}
		
		System.out.println("Il profilo attivo è " + environment.getActiveProfiles()[0]);

		String comandSqlResult = "";
		String profileActive = environment.getActiveProfiles()[0];
		if (profileActive.equalsIgnoreCase("oracleSql")) {
			comandSqlResult = "CREATE TABLE DS_r AS (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,t0.ME_1 AS ME_1,t0.ME_2 AS ME_2,t0.ME_3 AS ME_3,t0.ME_4 AS ME_4,t0.ME_5 AS ME_5,t0.ME_6 AS ME_6,t0.ME_7 AS ME_7,t0.ME_8 AS ME_8,vtl_pkg.cast_to_time(t0.ME_6,'TIME_PERIOD','YYYY-MM-DD') AS ME_9 FROM DS_62 t0);CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);";
		} else if (profileActive.equalsIgnoreCase("sqlServer")) {
			comandSqlResult = "SELECT t1.* INTO DS_r FROM (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,t0.ME_1 AS ME_1,t0.ME_2 AS ME_2,t0.ME_3 AS ME_3,t0.ME_4 AS ME_4,t0.ME_5 AS ME_5,t0.ME_6 AS ME_6,t0.ME_7 AS ME_7,t0.ME_8 AS ME_8,dbo.vtl_cast_to_time(t0.ME_6,'TIME_PERIOD','YYYY-MM-DD') AS ME_9 FROM DS_62 t0) t1;"+"CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);";
		} else if (profileActive.equalsIgnoreCase("mySql")) {
			comandSqlResult = "CREATE TABLE DS_r AS (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,t0.ME_1 AS ME_1,t0.ME_2 AS ME_2,t0.ME_3 AS ME_3,t0.ME_4 AS ME_4,t0.ME_5 AS ME_5,t0.ME_6 AS ME_6,t0.ME_7 AS ME_7,t0.ME_8 AS ME_8,vtl_cast_to_time(t0.ME_6,'TIME_PERIOD','YYYY-MM-DD') AS ME_9 FROM DS_62 t0);CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);";
		} else if (profileActive.equalsIgnoreCase("postgreSql")) {
			comandSqlResult = "CREATE TABLE DS_r AS (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,t0.ME_1 AS ME_1,t0.ME_2 AS ME_2,t0.ME_3 AS ME_3,t0.ME_4 AS ME_4,t0.ME_5 AS ME_5,t0.ME_6 AS ME_6,t0.ME_7 AS ME_7,t0.ME_8 AS ME_8,vtl_cast_to_time(t0.ME_6,'TIME_PERIOD','YYYY-MM-DD') AS ME_9 FROM DS_62 t0);CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);";
		}
		
		String commandStatements = "DS_r := DS_62[calc ME_9 := cast(ME_6, time, \"YYYY-MM-DD\")];";
		String result = translationUtilTest.translate(commandStatements);
		System.out.println("Print queryCastToTimeFromTimePeriod: " +result);
		System.out.println("Print queryAttesaCastToTimeFromTimePeriod: " +comandSqlResult);
		assertEquals("Test operator CastToToTimeFromTimePeriod", comandSqlResult, result);
	}

	@Test
	@Transactional
	public void castToStringFromDuration() throws Exception {
		dbTableUtilityService.resetSchema("t");
		VtlDataset vtlDatasetFind = datasetRepository.findByNameIgnoreCase("DS_1");
		if (vtlDatasetFind == null) {
			VtlDataset vtlDataset = new VtlDataset();

			vtlDataset.setPersistent(true);
			vtlDataset.setName("DS_62");
			vtlDataset.setIsOnlyAScalar(false);
			vtlDataset.setTransitory(false);
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ID_1", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ID_2", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ME_1", VtlType.INTEGER, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ME_2", VtlType.NUMBER, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ME_3", VtlType.DATE, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ME_4", VtlType.BOOLEAN, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ME_5", VtlType.TIME, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ME_6", VtlType.TIME_PERIOD, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ME_7", VtlType.DURATION, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ME_8", VtlType.STRING, VtlComponentRole.MEASURE));
			datasetRepository.save(vtlDataset);
		}
		
		System.out.println("Il profilo attivo è " + environment.getActiveProfiles()[0]);

		String comandSqlResult = "";
		String profileActive = environment.getActiveProfiles()[0];
		if (profileActive.equalsIgnoreCase("oracleSql")) {
			comandSqlResult = "CREATE TABLE DS_r AS (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,t0.ME_1 AS ME_1,t0.ME_2 AS ME_2,t0.ME_3 AS ME_3,t0.ME_4 AS ME_4,t0.ME_5 AS ME_5,t0.ME_6 AS ME_6,t0.ME_7 AS ME_7,t0.ME_8 AS ME_8,vtl_pkg.cast_to_string(t0.ME_7,'DURATION','YYYY-MM-DD') AS ME_9 FROM DS_62 t0);CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);";
		} else if (profileActive.equalsIgnoreCase("sqlServer")) {
			comandSqlResult = "SELECT t1.* INTO DS_r FROM (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,t0.ME_1 AS ME_1,t0.ME_2 AS ME_2,t0.ME_3 AS ME_3,t0.ME_4 AS ME_4,t0.ME_5 AS ME_5,t0.ME_6 AS ME_6,t0.ME_7 AS ME_7,t0.ME_8 AS ME_8,dbo.vtl_cast_to_string(t0.ME_7,'DURATION','YYYY-MM-DD') AS ME_9 FROM DS_62 t0) t1;"+"CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);";
		} else if (profileActive.equalsIgnoreCase("mySql")) {
			comandSqlResult = "CREATE TABLE DS_r AS (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,t0.ME_1 AS ME_1,t0.ME_2 AS ME_2,t0.ME_3 AS ME_3,t0.ME_4 AS ME_4,t0.ME_5 AS ME_5,t0.ME_6 AS ME_6,t0.ME_7 AS ME_7,t0.ME_8 AS ME_8,vtl_cast_to_string(t0.ME_7,'DURATION','YYYY-MM-DD') AS ME_9 FROM DS_62 t0);CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);";
		} else if (profileActive.equalsIgnoreCase("postgreSql")) {
			comandSqlResult = "CREATE TABLE DS_r AS (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,t0.ME_1 AS ME_1,t0.ME_2 AS ME_2,t0.ME_3 AS ME_3,t0.ME_4 AS ME_4,t0.ME_5 AS ME_5,t0.ME_6 AS ME_6,t0.ME_7 AS ME_7,t0.ME_8 AS ME_8,vtl_cast_to_string(t0.ME_7,'DURATION','YYYY-MM-DD') AS ME_9 FROM DS_62 t0);CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);";
		}
		//TODO 
		String commandStatements = "DS_r := DS_62[calc ME_9 := cast(ME_7, string, \"YYYY-MM-DD\")];";
		String result = translationUtilTest.translate(commandStatements);
		System.out.println("Print queryCastToStringFromDuration: " +result);
		System.out.println("Print queryAttesaCastToStringFromDuration: " +comandSqlResult);
		assertEquals("Test operator CastToStringFromDuration", comandSqlResult, result);
	}
	
	@Test
	@Transactional
	public void castToStringFromTimePeriod() throws Exception {
		dbTableUtilityService.resetSchema("t");
		VtlDataset vtlDatasetFind = datasetRepository.findByNameIgnoreCase("DS_1");
		if (vtlDatasetFind == null) {
			VtlDataset vtlDataset = new VtlDataset();

			vtlDataset.setPersistent(true);
			vtlDataset.setName("DS_62");
			vtlDataset.setIsOnlyAScalar(false);
			vtlDataset.setTransitory(false);
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ID_1", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ID_2", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ME_1", VtlType.INTEGER, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ME_2", VtlType.NUMBER, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ME_3", VtlType.DATE, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ME_4", VtlType.BOOLEAN, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ME_5", VtlType.TIME, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ME_6", VtlType.TIME_PERIOD, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ME_7", VtlType.DURATION, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ME_8", VtlType.STRING, VtlComponentRole.MEASURE));
			datasetRepository.save(vtlDataset);
		}
		
		System.out.println("Il profilo attivo è " + environment.getActiveProfiles()[0]);

		String comandSqlResult = "";
		String profileActive = environment.getActiveProfiles()[0];
		if (profileActive.equalsIgnoreCase("oracleSql")) {
			comandSqlResult = "CREATE TABLE DS_r AS (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,t0.ME_1 AS ME_1,t0.ME_2 AS ME_2,t0.ME_3 AS ME_3,t0.ME_4 AS ME_4,t0.ME_5 AS ME_5,t0.ME_6 AS ME_6,t0.ME_7 AS ME_7,t0.ME_8 AS ME_8,vtl_pkg.cast_to_string(t0.ME_6,'TIME_PERIOD','YYYY-MM-DD') AS ME_9 FROM DS_62 t0);CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);";
		} else if (profileActive.equalsIgnoreCase("sqlServer")) {
			comandSqlResult = "SELECT t1.* INTO DS_r FROM (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,t0.ME_1 AS ME_1,t0.ME_2 AS ME_2,t0.ME_3 AS ME_3,t0.ME_4 AS ME_4,t0.ME_5 AS ME_5,t0.ME_6 AS ME_6,t0.ME_7 AS ME_7,t0.ME_8 AS ME_8,dbo.vtl_cast_to_string(t0.ME_6,'TIME_PERIOD','YYYY-MM-DD') AS ME_9 FROM DS_62 t0) t1;"+"CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);";
		} else if (profileActive.equalsIgnoreCase("mySql")) {
			comandSqlResult = "CREATE TABLE DS_r AS (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,t0.ME_1 AS ME_1,t0.ME_2 AS ME_2,t0.ME_3 AS ME_3,t0.ME_4 AS ME_4,t0.ME_5 AS ME_5,t0.ME_6 AS ME_6,t0.ME_7 AS ME_7,t0.ME_8 AS ME_8,vtl_cast_to_string(t0.ME_6,'TIME_PERIOD','YYYY-MM-DD') AS ME_9 FROM DS_62 t0);CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);";
		} else if (profileActive.equalsIgnoreCase("postgreSql")) {
			comandSqlResult = "CREATE TABLE DS_r AS (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,t0.ME_1 AS ME_1,t0.ME_2 AS ME_2,t0.ME_3 AS ME_3,t0.ME_4 AS ME_4,t0.ME_5 AS ME_5,t0.ME_6 AS ME_6,t0.ME_7 AS ME_7,t0.ME_8 AS ME_8,vtl_cast_to_string(t0.ME_6,'TIME_PERIOD','YYYY-MM-DD') AS ME_9 FROM DS_62 t0);CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);";
		}
		
		String commandStatements = "DS_r := DS_62[calc ME_9 := cast(ME_6, string, \"YYYY-MM-DD\")];";
		String result = translationUtilTest.translate(commandStatements);
		System.out.println("Print queryCastToStringFromTimePeriod: " +result);
		System.out.println("Print queryAttesaCastToStringFromTimePeriod: " +comandSqlResult);
		assertEquals("Test operator CastToStringFromTimePeriod", comandSqlResult, result);
	}
	
	@Test
	@Transactional
	public void castToDurationFromString() throws Exception {
		dbTableUtilityService.resetSchema("t");
		VtlDataset vtlDatasetFind = datasetRepository.findByNameIgnoreCase("DS_1");
		if (vtlDatasetFind == null) {
			VtlDataset vtlDataset = new VtlDataset();

			vtlDataset.setPersistent(true);
			vtlDataset.setName("DS_62");
			vtlDataset.setIsOnlyAScalar(false);
			vtlDataset.setTransitory(false);
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ID_1", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ID_2", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ME_1", VtlType.INTEGER, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ME_2", VtlType.NUMBER, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ME_3", VtlType.DATE, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ME_4", VtlType.BOOLEAN, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ME_5", VtlType.TIME, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ME_6", VtlType.TIME_PERIOD, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ME_7", VtlType.DURATION, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ME_8", VtlType.STRING, VtlComponentRole.MEASURE));
			datasetRepository.save(vtlDataset);
		}
		
		System.out.println("Il profilo attivo è " + environment.getActiveProfiles()[0]);

		String comandSqlResult = "";
		String profileActive = environment.getActiveProfiles()[0];
		if (profileActive.equalsIgnoreCase("oracleSql")) {
			comandSqlResult = "CREATE TABLE DS_r AS (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,t0.ME_1 AS ME_1,t0.ME_2 AS ME_2,t0.ME_3 AS ME_3,t0.ME_4 AS ME_4,t0.ME_5 AS ME_5,t0.ME_6 AS ME_6,t0.ME_7 AS ME_7,t0.ME_8 AS ME_8,vtl_pkg.cast_to_duration(t0.ME_8,'STRING','YYYY-MM-DD') AS ME_9 FROM DS_62 t0);CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);";
		} else if (profileActive.equalsIgnoreCase("sqlServer")) {
			comandSqlResult = "SELECT t1.* INTO DS_r FROM (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,t0.ME_1 AS ME_1,t0.ME_2 AS ME_2,t0.ME_3 AS ME_3,t0.ME_4 AS ME_4,t0.ME_5 AS ME_5,t0.ME_6 AS ME_6,t0.ME_7 AS ME_7,t0.ME_8 AS ME_8,dbo.vtl_cast_to_duration(t0.ME_8,'STRING','YYYY-MM-DD') AS ME_9 FROM DS_62 t0) t1;"+"CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);";
		} else if (profileActive.equalsIgnoreCase("mySql")) {
			comandSqlResult = "CREATE TABLE DS_r AS (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,t0.ME_1 AS ME_1,t0.ME_2 AS ME_2,t0.ME_3 AS ME_3,t0.ME_4 AS ME_4,t0.ME_5 AS ME_5,t0.ME_6 AS ME_6,t0.ME_7 AS ME_7,t0.ME_8 AS ME_8,vtl_cast_to_duration(t0.ME_8,'STRING','YYYY-MM-DD') AS ME_9 FROM DS_62 t0);CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);";
		} else if (profileActive.equalsIgnoreCase("postgreSql")) {
			comandSqlResult = "CREATE TABLE DS_r AS (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,t0.ME_1 AS ME_1,t0.ME_2 AS ME_2,t0.ME_3 AS ME_3,t0.ME_4 AS ME_4,t0.ME_5 AS ME_5,t0.ME_6 AS ME_6,t0.ME_7 AS ME_7,t0.ME_8 AS ME_8,vtl_cast_to_duration(t0.ME_8,'STRING','YYYY-MM-DD') AS ME_9 FROM DS_62 t0);CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);";
		}
		//TODO
		String commandStatements = "DS_r := DS_62[calc ME_9 := cast(ME_8, duration, \"YYYY-MM-DD\")];";
		String result = translationUtilTest.translate(commandStatements);
		System.out.println("Print queryCastToDurationFromString: " +result);
		System.out.println("Print queryAttesaCastToDurationFromString: " +comandSqlResult);
		assertEquals("Test operator CastToToDurationFromString", comandSqlResult, result);
	}

}
