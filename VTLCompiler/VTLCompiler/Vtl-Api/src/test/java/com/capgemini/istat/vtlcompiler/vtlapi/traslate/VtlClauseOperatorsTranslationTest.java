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
public class VtlClauseOperatorsTranslationTest {

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
	public void filter() throws Exception {
		dbTableUtilityService.resetSchema("t");
		VtlDataset vtlDatasetFind = datasetRepository.findByNameIgnoreCase("DS_1");
		if (vtlDatasetFind == null) {
			VtlDataset vtlDataset = new VtlDataset();

			vtlDataset.setPersistent(true);
			vtlDataset.setName("DS_29");
			vtlDataset.setIsOnlyAScalar(false);
			vtlDataset.setTransitory(false);
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ID_1", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ID_2", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ID_3", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ME_1", VtlType.INTEGER, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("AT_1", VtlType.STRING, VtlComponentRole.VIRAL));
			datasetRepository.save(vtlDataset);
		}
		
		System.out.println("Il profilo attivo è " + environment.getActiveProfiles()[0]);
		String comandSqlResult = "";
		String profileActive = environment.getActiveProfiles()[0];
		if (profileActive.equalsIgnoreCase("oracleSql")) {
                    comandSqlResult = "CREATE TABLE DS_r AS (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,t0.ID_3 AS ID_3,t0.ME_1 AS ME_1,t0.AT_1 AS AT_1 FROM DS_29 t0 WHERE (t0.ID_1=1 AND t0.ME_1<10));"+"CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);"+"CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);"+"CREATE INDEX DS_r_ID_3_IDX ON DS_r (ID_3);";
		} else if (profileActive.equalsIgnoreCase("sqlServer")) {
                    comandSqlResult = "SELECT t1.* INTO DS_r FROM (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,t0.ID_3 AS ID_3,t0.ME_1 AS ME_1,t0.AT_1 AS AT_1 FROM DS_29 t0 WHERE (t0.ID_1=1 AND t0.ME_1<10)) t1;CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);"+"CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);"+"CREATE INDEX DS_r_ID_3_IDX ON DS_r (ID_3);";
		} else if (profileActive.equalsIgnoreCase("mySql")) {
                    comandSqlResult = "CREATE TABLE DS_r AS (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,t0.ID_3 AS ID_3,t0.ME_1 AS ME_1,t0.AT_1 AS AT_1 FROM DS_29 t0 WHERE (t0.ID_1=1 AND t0.ME_1<10));"+"CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);"+"CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);"+"CREATE INDEX DS_r_ID_3_IDX ON DS_r (ID_3);";
		} else if (profileActive.equalsIgnoreCase("postgreSql")) {
                    comandSqlResult = "CREATE TABLE DS_r AS (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,t0.ID_3 AS ID_3,t0.ME_1 AS ME_1,t0.AT_1 AS AT_1 FROM DS_29 t0 WHERE (t0.ID_1=1 AND t0.ME_1<10));"+"CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);"+"CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);"+"CREATE INDEX DS_r_ID_3_IDX ON DS_r (ID_3);";
		}
		
		String commandStatements = "DS_r := DS_29  [ filter Id_1 = 1 and Me_1 < 10 ];";
		String result = translationUtilTest.translate(commandStatements);
		System.out.println("Print queryFilter: " +result);
		System.out.println("Print queryAttesaFilter: " +comandSqlResult);
		assertEquals("Test operator Filter", comandSqlResult, result);
	}
        
	@Test
	@Transactional
	public void filterBetween() throws Exception {
		dbTableUtilityService.resetSchema("t");
		VtlDataset vtlDatasetFind = datasetRepository.findByNameIgnoreCase("DS_1");
		if (vtlDatasetFind == null) {
			VtlDataset vtlDataset = new VtlDataset();

			vtlDataset.setPersistent(true);
			vtlDataset.setName("DS_31");
			vtlDataset.setIsOnlyAScalar(false);
			vtlDataset.setTransitory(false);
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ID_1", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ID_2", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ID_3", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ME_1", VtlType.INTEGER, VtlComponentRole.MEASURE));
                        vtlDataset.addComponent(translationUtilTest.getVtlComponent("ME_2", VtlType.INTEGER, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("AT_1", VtlType.STRING, VtlComponentRole.VIRAL));
			datasetRepository.save(vtlDataset);
		}
		
		System.out.println("Il profilo attivo è " + environment.getActiveProfiles()[0]);
		String comandSqlResult = "";
		String profileActive = environment.getActiveProfiles()[0];
		if (profileActive.equalsIgnoreCase("oracleSql")) {
                    comandSqlResult = "CREATE TABLE DS_r AS (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,t0.ID_3 AS ID_3,t0.ME_1 AS ME_1,t0.ME_2 AS ME_2,t0.AT_1 AS AT_1 FROM DS_31 t0 WHERE (t0.ME_2=1 AND (t0.ME_1 BETWEEN 0 AND 3)));"+"CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);"+"CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);"+"CREATE INDEX DS_r_ID_3_IDX ON DS_r (ID_3);";
		} else if (profileActive.equalsIgnoreCase("sqlServer")) {
                    comandSqlResult = "SELECT t1.* INTO DS_r FROM (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,t0.ID_3 AS ID_3,t0.ME_1 AS ME_1,t0.ME_2 AS ME_2,t0.AT_1 AS AT_1 FROM DS_31 t0 WHERE (t0.ME_2=1 AND (t0.ME_1 BETWEEN 0 AND 3))) t1;CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);"+"CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);"+"CREATE INDEX DS_r_ID_3_IDX ON DS_r (ID_3);";
		} else if (profileActive.equalsIgnoreCase("mySql")) {
                    comandSqlResult = "CREATE TABLE DS_r AS (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,t0.ID_3 AS ID_3,t0.ME_1 AS ME_1,t0.ME_2 AS ME_2,t0.AT_1 AS AT_1 FROM DS_31 t0 WHERE (t0.ME_2=1 AND (t0.ME_1 BETWEEN 0 AND 3)));"+"CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);"+"CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);"+"CREATE INDEX DS_r_ID_3_IDX ON DS_r (ID_3);";
		} else if (profileActive.equalsIgnoreCase("postgreSql")) {
                    comandSqlResult = "CREATE TABLE DS_r AS (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,t0.ID_3 AS ID_3,t0.ME_1 AS ME_1,t0.ME_2 AS ME_2,t0.AT_1 AS AT_1 FROM DS_31 t0 WHERE (t0.ME_2=1 AND (t0.ME_1 BETWEEN 0 AND 3)));"+"CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);"+"CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);"+"CREATE INDEX DS_r_ID_3_IDX ON DS_r (ID_3);";
		}
		
		String commandStatements = "DS_r:= DS_31 [ filter ME_2 = 1 and between(ME_1,  0, 3) ];";
		String result = translationUtilTest.translate(commandStatements);
		System.out.println("Print queryFilterBetweeb: " +result);
		System.out.println("Print queryAttesaFilterBetween: " +comandSqlResult);
		assertEquals("Test operator Filter", comandSqlResult, result);
	}
        
	@Test
	@Transactional
	public void calc() throws Exception {
		dbTableUtilityService.resetSchema("t");
		VtlDataset vtlDatasetFind = datasetRepository.findByNameIgnoreCase("DS_1");
		if (vtlDatasetFind == null) {
			VtlDataset vtlDataset = new VtlDataset();

			vtlDataset.setPersistent(true);
			vtlDataset.setName("DS_30");
			vtlDataset.setIsOnlyAScalar(false);
			vtlDataset.setTransitory(false);
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ID_1", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ID_2", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ID_3", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ME_1", VtlType.INTEGER, VtlComponentRole.MEASURE));
			datasetRepository.save(vtlDataset);
		}
		
		System.out.println("Il profilo attivo è " + environment.getActiveProfiles()[0]);
		String comandSqlResult = "";
		String profileActive = environment.getActiveProfiles()[0];
		if (profileActive.equalsIgnoreCase("oracleSql")) {
                    comandSqlResult = "CREATE TABLE DS_r AS (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,t0.ID_3 AS ID_3,(t0.ME_1)*(2) AS Me_1 FROM DS_30 t0);"+"CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);"+"CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);"+"CREATE INDEX DS_r_ID_3_IDX ON DS_r (ID_3);";
		} else if (profileActive.equalsIgnoreCase("sqlServer")) {
                    comandSqlResult = "SELECT t1.* INTO DS_r FROM (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,t0.ID_3 AS ID_3,(t0.ME_1)*(2) AS Me_1 FROM DS_30 t0) t1;CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);"+"CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);"+"CREATE INDEX DS_r_ID_3_IDX ON DS_r (ID_3);";
		} else if (profileActive.equalsIgnoreCase("mySql")) {
                    comandSqlResult = "CREATE TABLE DS_r AS (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,t0.ID_3 AS ID_3,(t0.ME_1)*(2) AS Me_1 FROM DS_30 t0);"+"CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);"+"CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);"+"CREATE INDEX DS_r_ID_3_IDX ON DS_r (ID_3);";
		} else if (profileActive.equalsIgnoreCase("postgreSql")) {
                    comandSqlResult = "CREATE TABLE DS_r AS (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,t0.ID_3 AS ID_3,(t0.ME_1)*(2) AS Me_1 FROM DS_30 t0);"+"CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);"+"CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);"+"CREATE INDEX DS_r_ID_3_IDX ON DS_r (ID_3);";
		}
		
		String commandStatements = "DS_r := DS_30 [ calc Me_1:= Me_1 * 2 ];";
		String result = translationUtilTest.translate(commandStatements);
		System.out.println("Print queryCalc: " +result);
		System.out.println("Print queryAttesaCalc: " +comandSqlResult);
		assertEquals("Test operator Calc", comandSqlResult, result);

	}

	@Test
	@Transactional
	public void calcAt() throws Exception {		
		dbTableUtilityService.resetSchema("t");
		VtlDataset vtlDatasetFind = datasetRepository.findByNameIgnoreCase("DS_1");
		if (vtlDatasetFind == null) {
			VtlDataset vtlDataset = new VtlDataset();

			vtlDataset.setPersistent(true);
			vtlDataset.setName("DS_30");
			vtlDataset.setIsOnlyAScalar(false);
			vtlDataset.setTransitory(false);
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ID_1", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ID_2", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ID_3", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ME_1", VtlType.INTEGER, VtlComponentRole.MEASURE));
			datasetRepository.save(vtlDataset);
		}
		
		System.out.println("Il profilo attivo è " + environment.getActiveProfiles()[0]);
		String comandSqlResult = "";
		String profileActive = environment.getActiveProfiles()[0];
		if (profileActive.equalsIgnoreCase("oracleSql")) {
                    comandSqlResult = "CREATE TABLE DS_r AS (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,t0.ID_3 AS ID_3,t0.ME_1 AS ME_1,'EP' AS At_1 FROM DS_30 t0);"+"CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);"+"CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);"+"CREATE INDEX DS_r_ID_3_IDX ON DS_r (ID_3);";
		} else if (profileActive.equalsIgnoreCase("sqlServer")) {
                    comandSqlResult = "SELECT t1.* INTO DS_r FROM (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,t0.ID_3 AS ID_3,t0.ME_1 AS ME_1,'EP' AS At_1 FROM DS_30 t0) t1;CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);"+"CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);"+"CREATE INDEX DS_r_ID_3_IDX ON DS_r (ID_3);";
		} else if (profileActive.equalsIgnoreCase("mySql")) {
                    comandSqlResult = "CREATE TABLE DS_r AS (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,t0.ID_3 AS ID_3,t0.ME_1 AS ME_1,'EP' AS At_1 FROM DS_30 t0);"+"CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);"+"CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);"+"CREATE INDEX DS_r_ID_3_IDX ON DS_r (ID_3);";
		} else if (profileActive.equalsIgnoreCase("postgreSql")) {
                    comandSqlResult = "CREATE TABLE DS_r AS (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,t0.ID_3 AS ID_3,t0.ME_1 AS ME_1,'EP' AS At_1 FROM DS_30 t0);"+"CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);"+"CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);"+"CREATE INDEX DS_r_ID_3_IDX ON DS_r (ID_3);";
		}
		
		String commandStatements = "DS_r := DS_30 [ calc attribute At_1:= \"EP\" ];";
		String result = translationUtilTest.translate(commandStatements);
		System.out.println("Print queryCalcAt: " +result);
		System.out.println("Print queryAttesaCalcAt: " +comandSqlResult);
		assertEquals("Test operator CalcAt", comandSqlResult, result);

	}

	@Test
	@Transactional
	public void calcMe() throws Exception {
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
                    comandSqlResult = "CREATE TABLE DS_r AS (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,t0.ME_1 AS ME_1,concat(t0.ME_1,'world') AS Me_2 FROM DS_16 t0);"+"CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);"+"CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);"; 
		} else if (profileActive.equalsIgnoreCase("sqlServer")) {
                    comandSqlResult = "SELECT t1.* INTO DS_r FROM (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,t0.ME_1 AS ME_1,concat(t0.ME_1,'world') AS Me_2 FROM DS_16 t0) t1;CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);"+"CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);"; 
		} else if (profileActive.equalsIgnoreCase("mySql")) {
                    comandSqlResult = "CREATE TABLE DS_r AS (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,t0.ME_1 AS ME_1,concat(t0.ME_1,'world') AS Me_2 FROM DS_16 t0);"+"CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);"+"CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);"; 
		} else if (profileActive.equalsIgnoreCase("postgreSql")) {
                    comandSqlResult = "CREATE TABLE DS_r AS (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,t0.ME_1 AS ME_1,concat(t0.ME_1,'world') AS Me_2 FROM DS_16 t0);"+"CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);"+"CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);"; 
		}
		
		String commandStatements = "DS_r := DS_16[calc Me_2:= Me_1 || \"world\"];";
		String result = translationUtilTest.translate(commandStatements);
		System.out.println("Print queryCalcMe: " +result);
		System.out.println("Print queryAttesaCalcMe: " +comandSqlResult);
		assertEquals("Test operator CalcMe", comandSqlResult, result);

	}

	@Test
	@Transactional
	public void aggrGroupBy() throws Exception {
		dbTableUtilityService.resetSchema("t");
		VtlDataset vtlDatasetFind = datasetRepository.findByNameIgnoreCase("DS_1");
		if (vtlDatasetFind == null) {
			VtlDataset vtlDataset = new VtlDataset();

			vtlDataset.setPersistent(true);
			vtlDataset.setName("DS_30");
			vtlDataset.setIsOnlyAScalar(false);
			vtlDataset.setTransitory(false);
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ID_1", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ID_2", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ID_3", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ME_1", VtlType.INTEGER, VtlComponentRole.MEASURE));
			datasetRepository.save(vtlDataset);
		}
		
		System.out.println("Il profilo attivo è " + environment.getActiveProfiles()[0]);
		String comandSqlResult = "";
		String profileActive = environment.getActiveProfiles()[0];
		if (profileActive.equalsIgnoreCase("oracleSql")) {
                    comandSqlResult = "CREATE TABLE DS_r AS (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,sum(t0.ME_1) AS Me_1 FROM DS_30 t0 GROUP BY t0.ID_1,t0.ID_2);"+"CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);"+"CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);"; 
		} else if (profileActive.equalsIgnoreCase("sqlServer")) {
                    comandSqlResult = "SELECT t1.* INTO DS_r FROM (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,sum(t0.ME_1) AS Me_1 FROM DS_30 t0 GROUP BY t0.ID_1,t0.ID_2) t1;CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);"+"CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);";
		} else if (profileActive.equalsIgnoreCase("mySql")) {
                    comandSqlResult = "CREATE TABLE DS_r AS (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,sum(t0.ME_1) AS Me_1 FROM DS_30 t0 GROUP BY t0.ID_1,t0.ID_2);"+"CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);"+"CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);"; 
		} else if (profileActive.equalsIgnoreCase("postgreSql")) {
                    comandSqlResult = "CREATE TABLE DS_r AS (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,sum(t0.ME_1) AS Me_1 FROM DS_30 t0 GROUP BY t0.ID_1,t0.ID_2);"+"CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);"+"CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);"; 
		}
		
		String commandStatements = "DS_r := DS_30 [ aggr Me_1:= sum( Me_1 ) group by Id_1 , Id_2 ];";
		String result = translationUtilTest.translate(commandStatements);
		System.out.println("Print queryAggrGroupBy: " +result);
		System.out.println("Print queryAttesaAggrGroupBy: " +comandSqlResult);
		assertEquals("Test operator AggrGroupBy", comandSqlResult, result);

	}

	@Test
	@Transactional
	public void aggrGroupExcept() throws Exception {
		dbTableUtilityService.resetSchema("t");
		VtlDataset vtlDatasetFind = datasetRepository.findByNameIgnoreCase("DS_1");
		if (vtlDatasetFind == null) {
			VtlDataset vtlDataset = new VtlDataset();

			vtlDataset.setPersistent(true);
			vtlDataset.setName("DS_30");
			vtlDataset.setIsOnlyAScalar(false);
			vtlDataset.setTransitory(false);
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ID_1", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ID_2", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ID_3", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ME_1", VtlType.INTEGER, VtlComponentRole.MEASURE));
			datasetRepository.save(vtlDataset);
		}
		
		System.out.println("Il profilo attivo è " + environment.getActiveProfiles()[0]);
		String comandSqlResult = "";
		String profileActive = environment.getActiveProfiles()[0];
		if (profileActive.equalsIgnoreCase("oracleSql")) {
                    comandSqlResult = "CREATE TABLE DS_r AS (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,min(t0.ME_1) AS Me_3 FROM DS_30 t0 GROUP BY t0.ID_1,t0.ID_2);"+"CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);"+"CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);"; 
		} else if (profileActive.equalsIgnoreCase("sqlServer")) {
                    comandSqlResult = "SELECT t1.* INTO DS_r FROM (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,min(t0.ME_1) AS Me_3 FROM DS_30 t0 GROUP BY t0.ID_1,t0.ID_2) t1;CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);"+"CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);";
		} else if (profileActive.equalsIgnoreCase("mySql")) {
                    comandSqlResult = "CREATE TABLE DS_r AS (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,min(t0.ME_1) AS Me_3 FROM DS_30 t0 GROUP BY t0.ID_1,t0.ID_2);"+"CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);"+"CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);"; 
		} else if (profileActive.equalsIgnoreCase("postgreSql")) {
                    comandSqlResult = "CREATE TABLE DS_r AS (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,min(t0.ME_1) AS Me_3 FROM DS_30 t0 GROUP BY t0.ID_1,t0.ID_2);"+"CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);"+"CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);"; 
		}
		
		String commandStatements = "DS_r := DS_30 [ aggr Me_3:= min( Me_1 ) group except Id_3 ];";
		String result = translationUtilTest.translate(commandStatements);
		System.out.println("Print queryAggrGroupBy: " +result);
		System.out.println("Print queryAttesaAggrGroupBy: " +comandSqlResult);
		assertEquals("Test operator AggrGroupBy", comandSqlResult, result);

	}

	@Test
	@Transactional
	public void aggrGroupHaving() throws Exception {
		dbTableUtilityService.resetSchema("t");
		VtlDataset vtlDatasetFind = datasetRepository.findByNameIgnoreCase("DS_1");
		if (vtlDatasetFind == null) {
			VtlDataset vtlDataset = new VtlDataset();

			vtlDataset.setPersistent(true);
			vtlDataset.setName("DS_30");
			vtlDataset.setIsOnlyAScalar(false);
			vtlDataset.setTransitory(false);
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ID_1", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ID_2", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ID_3", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ME_1", VtlType.INTEGER, VtlComponentRole.MEASURE));
			datasetRepository.save(vtlDataset);
		}
		
		System.out.println("Il profilo attivo è " + environment.getActiveProfiles()[0]);
		String comandSqlResult = "";
		String profileActive = environment.getActiveProfiles()[0];
		if (profileActive.equalsIgnoreCase("oracleSql")) {
                    comandSqlResult = "CREATE TABLE DS_r AS (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,max(t0.ME_1) AS Me_2,sum(t0.ME_1) AS Me_1 FROM DS_30 t0 GROUP BY t0.ID_1,t0.ID_2 HAVING (avg(t0.ME_1)>2));"+"CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);"+"CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);";
		} else if (profileActive.equalsIgnoreCase("sqlServer")) {
                    comandSqlResult = "SELECT t1.* INTO DS_r FROM (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,max(t0.ME_1) AS Me_2,sum(t0.ME_1) AS Me_1 FROM DS_30 t0 GROUP BY t0.ID_1,t0.ID_2 HAVING (avg(t0.ME_1)>2)) t1;CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);"+"CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);";
		} else if (profileActive.equalsIgnoreCase("mySql")) {
                    comandSqlResult = "CREATE TABLE DS_r AS (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,max(t0.ME_1) AS Me_2,sum(t0.ME_1) AS Me_1 FROM DS_30 t0 GROUP BY t0.ID_1,t0.ID_2 HAVING (avg(t0.ME_1)>2));"+"CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);"+"CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);"; 
		} else if (profileActive.equalsIgnoreCase("postgreSql")) {
                    comandSqlResult = "CREATE TABLE DS_r AS (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,max(t0.ME_1) AS Me_2,sum(t0.ME_1) AS Me_1 FROM DS_30 t0 GROUP BY t0.ID_1,t0.ID_2 HAVING (avg(t0.ME_1)>2));"+"CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);"+"CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);"; 
		}
		
		String commandStatements = "DS_r := DS_30 [ aggr Me_1:= sum( Me_1 ), Me_2 := max( Me_1) group by Id_1 , Id_2 having avg (Me_1 ) > 2 ];";
		String result = translationUtilTest.translate(commandStatements);
		System.out.println("Print queryAggrGroupHaving: " +result);
		System.out.println("Print queryAttesaAggrGroupHaving: " +comandSqlResult);
		assertEquals("Test operator AggrGroupHaving", comandSqlResult, result);

	}
	
	@Test
	@Transactional
	public void keep() throws Exception {
		dbTableUtilityService.resetSchema("t");
		VtlDataset vtlDatasetFind = datasetRepository.findByNameIgnoreCase("DS_1");
		if (vtlDatasetFind == null) {
			VtlDataset vtlDataset = new VtlDataset();

			vtlDataset.setPersistent(true);
			vtlDataset.setName("DS_31");
			vtlDataset.setIsOnlyAScalar(false);
			vtlDataset.setTransitory(false);
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ID_1", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ID_2", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ID_3", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ME_1", VtlType.INTEGER, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ME_2", VtlType.INTEGER, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("AT_1", VtlType.STRING, VtlComponentRole.VIRAL));
			datasetRepository.save(vtlDataset);
		}
		
		System.out.println("Il profilo attivo è " + environment.getActiveProfiles()[0]);
		String comandSqlResult = "";
		String profileActive = environment.getActiveProfiles()[0];
		if (profileActive.equalsIgnoreCase("oracleSql")) {
                    comandSqlResult = "CREATE TABLE DS_r AS (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,t0.ID_3 AS ID_3,t0.ME_1 AS ME_1 FROM DS_31 t0);"+"CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);"+"CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);"+"CREATE INDEX DS_r_ID_3_IDX ON DS_r (ID_3);";
		} else if (profileActive.equalsIgnoreCase("sqlServer")) {
                    comandSqlResult = "SELECT t1.* INTO DS_r FROM (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,t0.ID_3 AS ID_3,t0.ME_1 AS ME_1 FROM DS_31 t0) t1;CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);"+"CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);"+"CREATE INDEX DS_r_ID_3_IDX ON DS_r (ID_3);";
		} else if (profileActive.equalsIgnoreCase("mySql")) {
                    comandSqlResult = "CREATE TABLE DS_r AS (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,t0.ID_3 AS ID_3,t0.ME_1 AS ME_1 FROM DS_31 t0);"+"CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);"+"CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);"+"CREATE INDEX DS_r_ID_3_IDX ON DS_r (ID_3);"; 
		} else if (profileActive.equalsIgnoreCase("postgreSql")) {
                    comandSqlResult = "CREATE TABLE DS_r AS (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,t0.ID_3 AS ID_3,t0.ME_1 AS ME_1 FROM DS_31 t0);"+"CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);"+"CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);"+"CREATE INDEX DS_r_ID_3_IDX ON DS_r (ID_3);"; 
		}
		
		String commandStatements = "DS_r := DS_31 [ keep  Me_1 ];";
		String result = translationUtilTest.translate(commandStatements);
		System.out.println("Print queryKeep: " +result);
		System.out.println("Print queryAttesaKeep: " +comandSqlResult);
		assertEquals("Test operator Keep", comandSqlResult, result);

	}

	@Test
	@Transactional
	public void drop() throws Exception {
		dbTableUtilityService.resetSchema("t");
		VtlDataset vtlDatasetFind = datasetRepository.findByNameIgnoreCase("DS_1");
		if (vtlDatasetFind == null) {
			VtlDataset vtlDataset = new VtlDataset();

			vtlDataset.setPersistent(true);
			vtlDataset.setName("DS_29");
			vtlDataset.setIsOnlyAScalar(false);
			vtlDataset.setTransitory(false);
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ID_1", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ID_2", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ID_3", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ME_1", VtlType.INTEGER, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("AT_1", VtlType.STRING, VtlComponentRole.VIRAL));
			datasetRepository.save(vtlDataset);
		}
		
		System.out.println("Il profilo attivo è " + environment.getActiveProfiles()[0]);
		String comandSqlResult = "";
		String profileActive = environment.getActiveProfiles()[0];
		if (profileActive.equalsIgnoreCase("oracleSql")) {
                    comandSqlResult = "CREATE TABLE DS_r AS (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,t0.ID_3 AS ID_3,t0.ME_1 AS ME_1 FROM DS_29 t0);"+"CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);"+"CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);"+"CREATE INDEX DS_r_ID_3_IDX ON DS_r (ID_3);";
		} else if (profileActive.equalsIgnoreCase("sqlServer")) {
                    comandSqlResult = "SELECT t1.* INTO DS_r FROM (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,t0.ID_3 AS ID_3,t0.ME_1 AS ME_1 FROM DS_29 t0) t1;CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);"+"CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);"+"CREATE INDEX DS_r_ID_3_IDX ON DS_r (ID_3);";
		} else if (profileActive.equalsIgnoreCase("mySql")) {
                    comandSqlResult = "CREATE TABLE DS_r AS (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,t0.ID_3 AS ID_3,t0.ME_1 AS ME_1 FROM DS_29 t0);"+"CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);"+"CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);"+"CREATE INDEX DS_r_ID_3_IDX ON DS_r (ID_3);"; 
		} else if (profileActive.equalsIgnoreCase("postgreSql")) {
                    comandSqlResult = "CREATE TABLE DS_r AS (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,t0.ID_3 AS ID_3,t0.ME_1 AS ME_1 FROM DS_29 t0);"+"CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);"+"CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);"+"CREATE INDEX DS_r_ID_3_IDX ON DS_r (ID_3);"; 
		}
		
		String commandStatements = "DS_r := DS_29 [ drop  At_1 ];";
		String result = translationUtilTest.translate(commandStatements);
		System.out.println("Print queryDrop: " +result);
		System.out.println("Print queryAttesaDrop: " +comandSqlResult);
		assertEquals("Test operator Drop", comandSqlResult, result);

	}

	@Test
	@Transactional
	public void rename() throws Exception {
		dbTableUtilityService.resetSchema("t");
		VtlDataset vtlDatasetFind = datasetRepository.findByNameIgnoreCase("DS_1");
		if (vtlDatasetFind == null) {
			VtlDataset vtlDataset = new VtlDataset();

			vtlDataset.setPersistent(true);
			vtlDataset.setName("DS_29");
			vtlDataset.setIsOnlyAScalar(false);
			vtlDataset.setTransitory(false);
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ID_1", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ID_2", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ID_3", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ME_1", VtlType.INTEGER, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("AT_1", VtlType.STRING, VtlComponentRole.VIRAL));
			datasetRepository.save(vtlDataset);
		}
		
		System.out.println("Il profilo attivo è " + environment.getActiveProfiles()[0]);
		String comandSqlResult = "";
		String profileActive = environment.getActiveProfiles()[0];
		if (profileActive.equalsIgnoreCase("oracleSql")) {
                    comandSqlResult = "CREATE TABLE DS_r AS (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,t0.ID_3 AS ID_3,t0.ME_1 AS Me_2,t0.AT_1 AS At_2 FROM DS_29 t0);"+"CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);"+"CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);"+"CREATE INDEX DS_r_ID_3_IDX ON DS_r (ID_3);";
		} else if (profileActive.equalsIgnoreCase("sqlServer")) {
                    comandSqlResult = "SELECT t1.* INTO DS_r FROM (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,t0.ID_3 AS ID_3,t0.ME_1 AS Me_2,t0.AT_1 AS At_2 FROM DS_29 t0) t1;CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);"+"CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);"+"CREATE INDEX DS_r_ID_3_IDX ON DS_r (ID_3);";
		} else if (profileActive.equalsIgnoreCase("mySql")) {
                    comandSqlResult = "CREATE TABLE DS_r AS (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,t0.ID_3 AS ID_3,t0.ME_1 AS Me_2,t0.AT_1 AS At_2 FROM DS_29 t0);"+"CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);"+"CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);"+"CREATE INDEX DS_r_ID_3_IDX ON DS_r (ID_3);"; 
		} else if (profileActive.equalsIgnoreCase("postgreSql")) {
                    comandSqlResult = "CREATE TABLE DS_r AS (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,t0.ID_3 AS ID_3,t0.ME_1 AS Me_2,t0.AT_1 AS At_2 FROM DS_29 t0);"+"CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);"+"CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);"+"CREATE INDEX DS_r_ID_3_IDX ON DS_r (ID_3);"; 
		}
		
		String commandStatements = "DS_r := DS_29 [ rename  Me_1 to Me_2, At_1 to At_2];";
		String result = translationUtilTest.translate(commandStatements);
		System.out.println("Print queryRename: " +result);
		System.out.println("Print queryAttesaRename: " +comandSqlResult);
		assertEquals("Test operator Rename", comandSqlResult, result);


	}

	@Test
	@Transactional
	public void sub() throws Exception {
		dbTableUtilityService.resetSchema("t");
		VtlDataset vtlDatasetFind = datasetRepository.findByNameIgnoreCase("DS_1");
		if (vtlDatasetFind == null) {
			VtlDataset vtlDataset = new VtlDataset();

			vtlDataset.setPersistent(true);
			vtlDataset.setName("DS_29");
			vtlDataset.setIsOnlyAScalar(false);
			vtlDataset.setTransitory(false);
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ID_1", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ID_2", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ID_3", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ME_1", VtlType.INTEGER, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("AT_1", VtlType.STRING, VtlComponentRole.VIRAL));
			datasetRepository.save(vtlDataset);
		}
		
		System.out.println("Il profilo attivo è " + environment.getActiveProfiles()[0]);
		String comandSqlResult = "";
		String profileActive = environment.getActiveProfiles()[0];
		if (profileActive.equalsIgnoreCase("oracleSql")) {
                    comandSqlResult = "CREATE TABLE DS_r AS (SELECT t0.ID_3 AS ID_3,t0.ME_1 AS ME_1,t0.AT_1 AS AT_1 FROM DS_29 t0 WHERE (((t0.ID_1 = 1) AND (t0.ID_2 = 'A'))));"+"CREATE INDEX DS_r_ID_3_IDX ON DS_r (ID_3);";
		} else if (profileActive.equalsIgnoreCase("sqlServer")) {
                    comandSqlResult = "SELECT t1.* INTO DS_r FROM (SELECT t0.ID_3 AS ID_3,t0.ME_1 AS ME_1,t0.AT_1 AS AT_1 FROM DS_29 t0 WHERE (((t0.ID_1 = 1) AND (t0.ID_2 = 'A')))) t1;"+"CREATE INDEX DS_r_ID_3_IDX ON DS_r (ID_3);";
		} else if (profileActive.equalsIgnoreCase("mySql")) {
                    comandSqlResult = "CREATE TABLE DS_r AS (SELECT t0.ID_3 AS ID_3,t0.ME_1 AS ME_1,t0.AT_1 AS AT_1 FROM DS_29 t0 WHERE (((t0.ID_1 = 1) AND (t0.ID_2 = 'A'))));"+"CREATE INDEX DS_r_ID_3_IDX ON DS_r (ID_3);"; 
		} else if (profileActive.equalsIgnoreCase("postgreSql")) {
                    comandSqlResult = "CREATE TABLE DS_r AS (SELECT t0.ID_3 AS ID_3,t0.ME_1 AS ME_1,t0.AT_1 AS AT_1 FROM DS_29 t0 WHERE (((t0.ID_1 = 1) AND (t0.ID_2 = 'A'))));"+"CREATE INDEX DS_r_ID_3_IDX ON DS_r (ID_3);"; 
		}
		
		String commandStatements = "DS_r := DS_29 [ sub  Id_1 = 1,  Id_2 = \"A\" ];";
		String result = translationUtilTest.translate(commandStatements);
		System.out.println("Print querySub: " +result);
		System.out.println("Print queryAttesaSub: " +comandSqlResult);
		assertEquals("Test operator Sub", comandSqlResult, result);

	}

	@Test
	@Transactional
	public void subComponent() throws Exception {
		dbTableUtilityService.resetSchema("t");
		VtlDataset vtlDatasetFind = datasetRepository.findByNameIgnoreCase("DS_1");
		if (vtlDatasetFind == null) {
			VtlDataset vtlDataset = new VtlDataset();

			vtlDataset.setPersistent(true);
			vtlDataset.setName("DS_29");
			vtlDataset.setIsOnlyAScalar(false);
			vtlDataset.setTransitory(false);
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ID_1", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ID_2", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ID_3", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ME_1", VtlType.INTEGER, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("AT_1", VtlType.STRING, VtlComponentRole.VIRAL));
			datasetRepository.save(vtlDataset);
		}
		
		System.out.println("Il profilo attivo è " + environment.getActiveProfiles()[0]);
		String comandSqlResult = "";
		String profileActive = environment.getActiveProfiles()[0];
		if (profileActive.equalsIgnoreCase("oracleSql")) {
                    comandSqlResult = "CREATE TABLE DS_r AS (SELECT t0.ME_1 AS ME_1,t0.AT_1 AS AT_1 FROM DS_29 t0 WHERE (((t0.ID_1 = 1) AND (t0.ID_2 = 'B') AND (t0.ID_3 = 'YY'))));";
		} else if (profileActive.equalsIgnoreCase("sqlServer")) {
                    comandSqlResult = "SELECT t1.* INTO DS_r FROM (SELECT t0.ME_1 AS ME_1,t0.AT_1 AS AT_1 FROM DS_29 t0 WHERE (((t0.ID_1 = 1) AND (t0.ID_2 = 'B') AND (t0.ID_3 = 'YY')))) t1;";
		} else if (profileActive.equalsIgnoreCase("mySql")) {
                    comandSqlResult = "CREATE TABLE DS_r AS (SELECT t0.ME_1 AS ME_1,t0.AT_1 AS AT_1 FROM DS_29 t0 WHERE (((t0.ID_1 = 1) AND (t0.ID_2 = 'B') AND (t0.ID_3 = 'YY'))));"; 
		} else if (profileActive.equalsIgnoreCase("postgreSql")) {
                    comandSqlResult = "CREATE TABLE DS_r AS (SELECT t0.ME_1 AS ME_1,t0.AT_1 AS AT_1 FROM DS_29 t0 WHERE (((t0.ID_1 = 1) AND (t0.ID_2 = 'B') AND (t0.ID_3 = 'YY'))));";
		}
		
		String commandStatements = "DS_r := DS_29 [ sub Id_1 =  1, Id_2 = \"B\", Id_3 = \"YY\" ];";
		String result = translationUtilTest.translate(commandStatements);
		System.out.println("Print querySubComponent: " +result);
		System.out.println("Print queryAttesaSubComponent: " +comandSqlResult);
		assertEquals("Test operator SubComponent", comandSqlResult, result);

	}
	
	@Test
	@Transactional
	public void unpivot() throws Exception {
		dbTableUtilityService.resetSchema("t");
		VtlDataset vtlDatasetFind = datasetRepository.findByNameIgnoreCase("DS_1");
		if (vtlDatasetFind == null) {
			VtlDataset vtlDataset = new VtlDataset();

			vtlDataset.setPersistent(true);
			vtlDataset.setName("DS_56");
			vtlDataset.setIsOnlyAScalar(false);
			vtlDataset.setTransitory(false);
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ID_1", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ID_2", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ME_1", VtlType.INTEGER, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ME_2", VtlType.INTEGER, VtlComponentRole.MEASURE));
            vtlDataset.addComponent(translationUtilTest.getVtlComponent("ME_3", VtlType.INTEGER, VtlComponentRole.MEASURE));
			datasetRepository.save(vtlDataset);
		}
		
		System.out.println("Il profilo attivo è " + environment.getActiveProfiles()[0]);
		String comandSqlResult = "";
		String profileActive = environment.getActiveProfiles()[0];
		if (profileActive.equalsIgnoreCase("oracleSql")) {
                    comandSqlResult = "CREATE TABLE DS_r AS (SELECT ID_1,ID_2,ID_X,ME_X FROM DS_56 UNPIVOT (ME_X for ID_X in (ME_1 as 'ME_1',ME_2 as 'ME_2',ME_3 as 'ME_3') ));CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);CREATE INDEX DS_r_ID_X_IDX ON DS_r (ID_X);";
		} else if (profileActive.equalsIgnoreCase("sqlServer")) {
                    comandSqlResult = "SELECT t3.* INTO DS_r FROM (SELECT ID_1,ID_2,ID_X,ME_X FROM DS_56 UNPIVOT (ME_X for ID_X in (ME_1,ME_2,ME_3) ) t2) t3;CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);CREATE INDEX DS_r_ID_X_IDX ON DS_r (ID_X);";
		} else if (profileActive.equalsIgnoreCase("mySql")) {
                    comandSqlResult = "CREATE TABLE DS_r AS (SELECT ID_1,ID_2,'ME_1' as ID_X,ME_1 as ME_X FROM DS_56 UNION SELECT ID_1,ID_2,'ME_2' as ID_X,ME_2 as ME_X FROM DS_56 UNION SELECT ID_1,ID_2,'ME_3' as ID_X,ME_3 as ME_X FROM DS_56);CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);CREATE INDEX DS_r_ID_X_IDX ON DS_r (ID_X);";
		} else if (profileActive.equalsIgnoreCase("postgreSql")) {
                    comandSqlResult = "CREATE TABLE DS_r AS (SELECT ID_1,ID_2,'ME_1' as ID_X,ME_1 as ME_X FROM DS_56 UNION SELECT ID_1,ID_2,'ME_2' as ID_X,ME_2 as ME_X FROM DS_56 UNION SELECT ID_1,ID_2,'ME_3' as ID_X,ME_3 as ME_X FROM DS_56);CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);CREATE INDEX DS_r_ID_X_IDX ON DS_r (ID_X);"; 
		}
		
		String commandStatements = "DS_r := DS_56 [ unpivot ID_X, ME_X];";
		String result = translationUtilTest.translate(commandStatements);
		System.out.println("Print queryUnpivot: " +result);
		System.out.println("Print queryAttesaUnpivot: " +comandSqlResult);
		assertEquals("Test operator Unpivot", comandSqlResult, result);

	}

	@Test
	@Transactional
	public void customPivot() throws Exception {
		dbTableUtilityService.resetSchema("t");
		VtlDataset vtlDatasetFind = datasetRepository.findByNameIgnoreCase("DS_1");
		if (vtlDatasetFind == null) {
			VtlDataset vtlDataset = new VtlDataset();

			vtlDataset.setPersistent(true);
			vtlDataset.setName("DS_61");
			vtlDataset.setIsOnlyAScalar(false);
			vtlDataset.setTransitory(false);
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ID_1", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ID_2", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ME_1", VtlType.INTEGER, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("AT_1", VtlType.INTEGER, VtlComponentRole.ATTRIBUTE));
			datasetRepository.save(vtlDataset);
		}

		System.out.println("Il profilo attivo è " + environment.getActiveProfiles()[0]);
		String comandSqlResult = "";
		String profileActive = environment.getActiveProfiles()[0];
		if (profileActive.equalsIgnoreCase("oracleSql")) {
                    comandSqlResult = "CREATE TABLE DS_r AS (SELECT t1.ID_1 AS ID_1,t1.A AS A,t1.B AS B,t1.ID_2_10 AS ID_2_10 FROM ( (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,t0.ME_1 AS ME_1 FROM DS_61 t0) PIVOT ( SUM( ME_1 ) FOR ID_2 IN ('A' AS A, 'B' AS B, '10' AS ID_2_10)) )  t1);CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);";
		} else if (profileActive.equalsIgnoreCase("sqlServer")) {
                    comandSqlResult = "SELECT t2.* INTO DS_r FROM (SELECT ID_1,[A] AS A,[B] AS B,[10] AS ID_2_10 FROM (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,t0.ME_1 AS ME_1 FROM DS_61 t0) AS t0 PIVOT ( SUM( ME_1 ) FOR ID_2 IN ([A],[B],[10]) ) AS t1) t2;CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);";
		} else if (profileActive.equalsIgnoreCase("mySql")) {
            		comandSqlResult = "CREATE TABLE DS_r AS (SELECT t0.ID_1 AS ID_1,SUM((CASE WHEN (ID_2='A') THEN ME_1 ELSE NULL END)) AS A,SUM((CASE WHEN (ID_2='B') THEN ME_1 ELSE NULL END)) AS B,SUM((CASE WHEN (ID_2='10') THEN ME_1 ELSE NULL END)) AS ID_2_10 FROM DS_61 t0 GROUP BY t0.ID_1);CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);"; 
		}else if (profileActive.equalsIgnoreCase("postgreSql")) {
            		comandSqlResult = "CREATE TABLE DS_r AS (SELECT t0.ID_1 AS ID_1,SUM((CASE WHEN (ID_2='A') THEN ME_1 ELSE NULL END)) AS A,SUM((CASE WHEN (ID_2='B') THEN ME_1 ELSE NULL END)) AS B,SUM((CASE WHEN (ID_2='10') THEN ME_1 ELSE NULL END)) AS ID_2_10 FROM DS_61 t0 GROUP BY t0.ID_1);CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);"; 
		}
		
		String commandStatements = "DS_r := DS_61 [ customPivot ID_2, ME_1 in \"A\",\"B\",\"10\"];";
//		String commandStatements = "DS_r := DS_61 [ customPivot ID_2, ME_1 in \"A\",\"B\",\"C\"];";
		String result = translationUtilTest.translate(commandStatements);
		System.out.println("Print queryCustomPivot: " + result);
		System.out.println("Print queryAttesaCustomPivot: " + comandSqlResult);
		assertEquals("Test operator CustomPivot", comandSqlResult, result);

	}
	
	@Test
	@Transactional
	public void customPivot2() throws Exception {
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
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ID_3", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ME_1", VtlType.INTEGER, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ME_2", VtlType.STRING, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("AT_1", VtlType.INTEGER, VtlComponentRole.ATTRIBUTE));
			datasetRepository.save(vtlDataset);
		}

		System.out.println("Il profilo attivo è " + environment.getActiveProfiles()[0]);
		String comandSqlResult = "";
		String profileActive = environment.getActiveProfiles()[0];
		if (profileActive.equalsIgnoreCase("oracleSql")) {
                    comandSqlResult = "CREATE TABLE DS_r AS (SELECT t1.ID_1 AS ID_1,t1.ID_3 AS ID_3,t1.A AS A,t1.B AS B,t1.ID_2_10 AS ID_2_10 FROM ( (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,t0.ID_3 AS ID_3,t0.ME_1 AS ME_1 FROM DS_62 t0) PIVOT ( SUM( ME_1 ) FOR ID_2 IN ('A' AS A, 'B' AS B, '10' AS ID_2_10)) )  t1);CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);CREATE INDEX DS_r_ID_3_IDX ON DS_r (ID_3);";
		} else if (profileActive.equalsIgnoreCase("sqlServer")) {
                    comandSqlResult = "SELECT t2.* INTO DS_r FROM (SELECT ID_1,ID_3,[A] AS A,[B] AS B,[10] AS ID_2_10 FROM (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,t0.ID_3 AS ID_3,t0.ME_1 AS ME_1 FROM DS_62 t0) AS t0 PIVOT ( SUM( ME_1 ) FOR ID_2 IN ([A],[B],[10]) ) AS t1) t2;CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);CREATE INDEX DS_r_ID_3_IDX ON DS_r (ID_3);";
		} else if (profileActive.equalsIgnoreCase("mySql")) {
    				comandSqlResult = "CREATE TABLE DS_r AS (SELECT t0.ID_1 AS ID_1,t0.ID_3 AS ID_3,SUM((CASE WHEN (ID_2='A') THEN ME_1 ELSE NULL END)) AS A,SUM((CASE WHEN (ID_2='B') THEN ME_1 ELSE NULL END)) AS B,SUM((CASE WHEN (ID_2='10') THEN ME_1 ELSE NULL END)) AS ID_2_10 FROM DS_62 t0 GROUP BY t0.ID_1,t0.ID_3);CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);CREATE INDEX DS_r_ID_3_IDX ON DS_r (ID_3);"; 
		}else if (profileActive.equalsIgnoreCase("postgreSql")) {
    				comandSqlResult = "CREATE TABLE DS_r AS (SELECT t0.ID_1 AS ID_1,t0.ID_3 AS ID_3,SUM((CASE WHEN (ID_2='A') THEN ME_1 ELSE NULL END)) AS A,SUM((CASE WHEN (ID_2='B') THEN ME_1 ELSE NULL END)) AS B,SUM((CASE WHEN (ID_2='10') THEN ME_1 ELSE NULL END)) AS ID_2_10 FROM DS_62 t0 GROUP BY t0.ID_1,t0.ID_3);CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);CREATE INDEX DS_r_ID_3_IDX ON DS_r (ID_3);"; 
		} 

		String commandStatements = "DS_r := DS_62 [ customPivot ID_2, ME_1 in \"A\",\"B\",\"10\"];";
		String result = translationUtilTest.translate(commandStatements);
		System.out.println("Print queryCustomPivot2: " + result);
		System.out.println("Print queryAttesaCustomPivot2: " + comandSqlResult);
		assertEquals("Test operator CustomPivot2", comandSqlResult, result);

	}

	@Test
	@Transactional
	public void subDoubleComponent() throws Exception {
		dbTableUtilityService.resetSchema("t");
		VtlDataset vtlDatasetFind = datasetRepository.findByNameIgnoreCase("DS_1");
		if (vtlDatasetFind == null) {
			VtlDataset vtlDataset = new VtlDataset();

			vtlDataset.setPersistent(true);
			vtlDataset.setName("DS_29");
			vtlDataset.setIsOnlyAScalar(false);
			vtlDataset.setTransitory(false);
                        vtlDataset.addComponent(translationUtilTest.getVtlComponent("ID_1", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
                        vtlDataset.addComponent(translationUtilTest.getVtlComponent("ID_2", VtlType.STRING, VtlComponentRole.IDENTIFIER));
                        vtlDataset.addComponent(translationUtilTest.getVtlComponent("ID_3", VtlType.STRING, VtlComponentRole.IDENTIFIER));
                        vtlDataset.addComponent(translationUtilTest.getVtlComponent("ME_1", VtlType.INTEGER, VtlComponentRole.MEASURE));
                        vtlDataset.addComponent(translationUtilTest.getVtlComponent("AT_1", VtlType.STRING, VtlComponentRole.VIRAL));
			datasetRepository.save(vtlDataset);
		}
		String commandStatements = "DS_r := DS_29 [ sub Id_2 = \"A\" ] + DS_29 [ sub Id_2 = \"B\" ];";
                String comandSqlResult = ""; 
                String profileActive = environment.getActiveProfiles()[0];
		if (profileActive.equalsIgnoreCase("oracleSql")) {
                    comandSqlResult = "CREATE TABLE temporary_1709 AS (SELECT t0.ID_1 AS ID_1,t0.ID_3 AS ID_3,t0.ME_1 AS ME_1,t0.AT_1 AS AT_1 FROM DS_29 t0 WHERE ((t0.ID_2 = 'A')));"+"CREATE INDEX temporary_1709_ID_1_IDX ON temporary_1709 (ID_1);"+"CREATE INDEX temporary_1709_ID_3_IDX ON temporary_1709 (ID_3);"+"CREATE TABLE temporary_1711 AS (SELECT t2.ID_1 AS ID_1,t2.ID_3 AS ID_3,t2.ME_1 AS ME_1,t2.AT_1 AS AT_1 FROM DS_29 t2 WHERE ((t2.ID_2 = 'B')));"+"CREATE INDEX temporary_1711_ID_1_IDX ON temporary_1711 (ID_1);"+"CREATE INDEX temporary_1711_ID_3_IDX ON temporary_1711 (ID_3);"+"CREATE TABLE DS_r AS (SELECT t4.ID_1 AS ID_1,t4.ID_3 AS ID_3,(t4.ME_1)+(t3.ME_1) AS ME_1,t4.AT_1 || ':' || t3.AT_1 AS AT_1 FROM temporary_1709 t4 INNER JOIN temporary_1711 t3 ON (((t4.ID_1 = t3.ID_1) AND (t4.ID_3 = t3.ID_3))));"+"CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);"+"CREATE INDEX DS_r_ID_3_IDX ON DS_r (ID_3);";
		} else if (profileActive.equalsIgnoreCase("sqlServer")) {
                    comandSqlResult = "SELECT t1.* INTO temporary_2 FROM (SELECT t0.ID_1 AS ID_1,t0.ID_3 AS ID_3,t0.ME_1 AS ME_1,t0.AT_1 AS AT_1 FROM DS_29 t0 WHERE ((t0.ID_2 = 'A'))) t1;CREATE INDEX temporary_2_ID_1_IDX ON temporary_2 (ID_1);CREATE INDEX temporary_2_ID_3_IDX ON temporary_2 (ID_3);SELECT t4.* INTO temporary_4 FROM (SELECT t3.ID_1 AS ID_1,t3.ID_3 AS ID_3,t3.ME_1 AS ME_1,t3.AT_1 AS AT_1 FROM DS_29 t3 WHERE ((t3.ID_2 = 'B'))) t4;CREATE INDEX temporary_4_ID_1_IDX ON temporary_4 (ID_1);CREATE INDEX temporary_4_ID_3_IDX ON temporary_4 (ID_3);SELECT t7.* INTO DS_r FROM (SELECT t6.ID_1 AS ID_1,t6.ID_3 AS ID_3,(t6.ME_1)+(t5.ME_1) AS ME_1,concat(concat(t6.AT_1,':'),t5.AT_1) AS AT_1 FROM temporary_2 t6 INNER JOIN temporary_4 t5 ON (((t6.ID_1 = t5.ID_1) AND (t6.ID_3 = t5.ID_3)))) t7;CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);CREATE INDEX DS_r_ID_3_IDX ON DS_r (ID_3);";
		} else if (profileActive.equalsIgnoreCase("mySql")) {
                    comandSqlResult = "CREATE TABLE temporary_2 AS (SELECT t0.ID_1 AS ID_1,t0.ID_3 AS ID_3,t0.ME_1 AS ME_1,t0.AT_1 AS AT_1 FROM DS_29 t0 WHERE ((t0.ID_2 = 'A')));CREATE INDEX temporary_2_ID_1_IDX ON temporary_2 (ID_1);CREATE INDEX temporary_2_ID_3_IDX ON temporary_2 (ID_3);CREATE TABLE temporary_4 AS (SELECT t2.ID_1 AS ID_1,t2.ID_3 AS ID_3,t2.ME_1 AS ME_1,t2.AT_1 AS AT_1 FROM DS_29 t2 WHERE ((t2.ID_2 = 'B')));CREATE INDEX temporary_4_ID_1_IDX ON temporary_4 (ID_1);CREATE INDEX temporary_4_ID_3_IDX ON temporary_4 (ID_3);CREATE TABLE DS_r AS (SELECT t4.ID_1 AS ID_1,t4.ID_3 AS ID_3,(t4.ME_1)+(t3.ME_1) AS ME_1,concat(concat(t4.AT_1,':'),t3.AT_1) AS AT_1 FROM temporary_2 t4 INNER JOIN temporary_4 t3 ON (((t4.ID_1 = t3.ID_1) AND (t4.ID_3 = t3.ID_3))));CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);CREATE INDEX DS_r_ID_3_IDX ON DS_r (ID_3);";
		} else if (profileActive.equalsIgnoreCase("postgreSql")) {
                    comandSqlResult = "CREATE TABLE temporary_1709 AS (SELECT t0.ID_1 AS ID_1,t0.ID_3 AS ID_3,t0.ME_1 AS ME_1,t0.AT_1 AS AT_1 FROM DS_29 t0 WHERE ((t0.ID_2 = 'A')));"+"CREATE INDEX temporary_1709_ID_1_IDX ON temporary_1709 (ID_1);"+"CREATE INDEX temporary_1709_ID_3_IDX ON temporary_1709 (ID_3);"+"CREATE TABLE temporary_1711 AS (SELECT t2.ID_1 AS ID_1,t2.ID_3 AS ID_3,t2.ME_1 AS ME_1,t2.AT_1 AS AT_1 FROM DS_29 t2 WHERE ((t2.ID_2 = 'B')));"+"CREATE INDEX temporary_1711_ID_1_IDX ON temporary_1711 (ID_1);"+"CREATE INDEX temporary_1711_ID_3_IDX ON temporary_1711 (ID_3);"+"CREATE TABLE DS_r AS (SELECT t4.ID_1 AS ID_1,t4.ID_3 AS ID_3,(t4.ME_1)+(t3.ME_1) AS ME_1,t4.AT_1 || ':' || t3.AT_1 AS AT_1 FROM temporary_1709 t4 INNER JOIN temporary_1711 t3 ON (((t4.ID_1 = t3.ID_1) AND (t4.ID_3 = t3.ID_3))));"+"CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);"+"CREATE INDEX DS_r_ID_3_IDX ON DS_r (ID_3);"; 
		}
                String result = translationUtilTest.translate(commandStatements);
		System.out.println("Print querySubDoubleComponent: " +result);
		System.out.println("Print queryAttesaSubDoubleComponent: " +comandSqlResult);
		assertEquals("Test operator SubDoubleComponent", translationUtilTest.temporaryValueObfuscation(comandSqlResult), translationUtilTest.temporaryValueObfuscation(result));

	}	
}
