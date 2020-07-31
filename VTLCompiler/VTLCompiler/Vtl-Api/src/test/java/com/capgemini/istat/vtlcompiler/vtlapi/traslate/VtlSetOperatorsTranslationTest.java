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
public class VtlSetOperatorsTranslationTest {

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
	public void union() throws Exception {
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
			vtlDatasetSecond.setName("DS_25");
			vtlDatasetSecond.setIsOnlyAScalar(false);
			vtlDatasetSecond.setTransitory(false);
			vtlDatasetSecond
					.addComponent(translationUtilTest.getVtlComponent("ID_1", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDatasetSecond
					.addComponent(translationUtilTest.getVtlComponent("ID_2", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDatasetSecond
					.addComponent(translationUtilTest.getVtlComponent("ID_3", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDatasetSecond
					.addComponent(translationUtilTest.getVtlComponent("ID_4", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDatasetSecond
					.addComponent(translationUtilTest.getVtlComponent("ME_1", VtlType.INTEGER, VtlComponentRole.MEASURE));
			datasetRepository.save(vtlDatasetSecond);

		}
		
		System.out.println("Il profilo attivo è " + environment.getActiveProfiles()[0]);

		String comandSqlResult = "";
		String profileActive = environment.getActiveProfiles()[0];
		if (profileActive.equalsIgnoreCase("oracleSql")) {
			comandSqlResult = "CREATE TABLE DS_r AS (SELECT ID_1,ID_2,ID_3,ID_4,ME_1 FROM (SELECT ID_1,ID_2,ID_3,ID_4,ME_1,DENSE_RANK() OVER (PARTITION BY ID_1,ID_2,ID_3,ID_4 ORDER BY PRIORITY) AS DENSE FROM (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,t0.ID_3 AS ID_3,t0.ID_4 AS ID_4,t0.ME_1 AS ME_1,1 AS PRIORITY FROM DS_18 t0 UNION ALL SELECT t1.ID_1 AS ID_1,t1.ID_2 AS ID_2,t1.ID_3 AS ID_3,t1.ID_4 AS ID_4,t1.ME_1 AS ME_1,2 AS PRIORITY FROM DS_25 t1) DT) FT WHERE (DENSE = 1));"+"CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);"+"CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);"+"CREATE INDEX DS_r_ID_3_IDX ON DS_r (ID_3);"+"CREATE INDEX DS_r_ID_4_IDX ON DS_r (ID_4);"; 
		} else if (profileActive.equalsIgnoreCase("sqlServer")) {
			comandSqlResult = "SELECT t2.* INTO DS_r FROM (SELECT ID_1,ID_2,ID_3,ID_4,ME_1 FROM (SELECT ID_1,ID_2,ID_3,ID_4,ME_1,DENSE_RANK() OVER (PARTITION BY ID_1,ID_2,ID_3,ID_4 ORDER BY PRIORITY) AS DENSE FROM (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,t0.ID_3 AS ID_3,t0.ID_4 AS ID_4,t0.ME_1 AS ME_1,1 AS PRIORITY FROM DS_18 t0 UNION ALL SELECT t1.ID_1 AS ID_1,t1.ID_2 AS ID_2,t1.ID_3 AS ID_3,t1.ID_4 AS ID_4,t1.ME_1 AS ME_1,2 AS PRIORITY FROM DS_25 t1) DT) FT WHERE (DENSE = 1)) t2;"+"CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);"+"CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);"+"CREATE INDEX DS_r_ID_3_IDX ON DS_r (ID_3);"+"CREATE INDEX DS_r_ID_4_IDX ON DS_r (ID_4);";
		} else if (profileActive.equalsIgnoreCase("mySql")) {
			comandSqlResult = "CREATE TABLE DS_r AS (SELECT ID_1,ID_2,ID_3,ID_4,ME_1 FROM (SELECT ID_1,ID_2,ID_3,ID_4,ME_1,DENSE_RANK() OVER (PARTITION BY ID_1,ID_2,ID_3,ID_4 ORDER BY PRIORITY) AS DENSE FROM (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,t0.ID_3 AS ID_3,t0.ID_4 AS ID_4,t0.ME_1 AS ME_1,1 AS PRIORITY FROM DS_18 t0 UNION ALL SELECT t1.ID_1 AS ID_1,t1.ID_2 AS ID_2,t1.ID_3 AS ID_3,t1.ID_4 AS ID_4,t1.ME_1 AS ME_1,2 AS PRIORITY FROM DS_25 t1) DT) FT WHERE (DENSE = 1));"+"CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);"+"CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);"+"CREATE INDEX DS_r_ID_3_IDX ON DS_r (ID_3);"+"CREATE INDEX DS_r_ID_4_IDX ON DS_r (ID_4);";  
		} else if (profileActive.equalsIgnoreCase("postgreSql")) {
			comandSqlResult = "CREATE TABLE DS_r AS (SELECT ID_1,ID_2,ID_3,ID_4,ME_1 FROM (SELECT ID_1,ID_2,ID_3,ID_4,ME_1,DENSE_RANK() OVER (PARTITION BY ID_1,ID_2,ID_3,ID_4 ORDER BY PRIORITY) AS DENSE FROM (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,t0.ID_3 AS ID_3,t0.ID_4 AS ID_4,t0.ME_1 AS ME_1,1 AS PRIORITY FROM DS_18 t0 UNION ALL SELECT t1.ID_1 AS ID_1,t1.ID_2 AS ID_2,t1.ID_3 AS ID_3,t1.ID_4 AS ID_4,t1.ME_1 AS ME_1,2 AS PRIORITY FROM DS_25 t1) DT) FT WHERE (DENSE = 1));"+"CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);"+"CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);"+"CREATE INDEX DS_r_ID_3_IDX ON DS_r (ID_3);"+"CREATE INDEX DS_r_ID_4_IDX ON DS_r (ID_4);"; 
		}
		
		String commandStatements = "DS_r := union(DS_18,DS_25);";
		String result = translationUtilTest.translate(commandStatements);
		System.out.println("Print queryUnion: " +result);
		System.out.println("Print queryAttesaUnion: " +comandSqlResult);
		assertEquals("Test operator Union", comandSqlResult, result);
	}

	@Test
	@Transactional
	public void unionThree() throws Exception {
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
			vtlDatasetSecond.setName("DS_25");
			vtlDatasetSecond.setIsOnlyAScalar(false);
			vtlDatasetSecond.setTransitory(false);
			vtlDatasetSecond
					.addComponent(translationUtilTest.getVtlComponent("ID_1", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDatasetSecond
					.addComponent(translationUtilTest.getVtlComponent("ID_2", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDatasetSecond
					.addComponent(translationUtilTest.getVtlComponent("ID_3", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDatasetSecond
					.addComponent(translationUtilTest.getVtlComponent("ID_4", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDatasetSecond
					.addComponent(translationUtilTest.getVtlComponent("ME_1", VtlType.INTEGER, VtlComponentRole.MEASURE));
			datasetRepository.save(vtlDatasetSecond);
			
                        VtlDataset vtlDataset3 = new VtlDataset();

			vtlDataset3.setPersistent(true);
			vtlDataset3.setName("DS_26");
			vtlDataset3.setIsOnlyAScalar(false);
			vtlDataset3.setTransitory(false);
			vtlDataset3.addComponent(translationUtilTest.getVtlComponent("ID_1", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDataset3.addComponent(translationUtilTest.getVtlComponent("ID_2", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDataset3.addComponent(translationUtilTest.getVtlComponent("ID_3", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDataset3.addComponent(translationUtilTest.getVtlComponent("ID_4", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDataset3.addComponent(translationUtilTest.getVtlComponent("ME_1", VtlType.INTEGER, VtlComponentRole.MEASURE));
			datasetRepository.save(vtlDataset3);
		}
		
		System.out.println("Il profilo attivo è " + environment.getActiveProfiles()[0]);

		String comandSqlResult = "";
		String profileActive = environment.getActiveProfiles()[0];
		if (profileActive.equalsIgnoreCase("oracleSql")) {
			comandSqlResult = "CREATE TABLE DS_r AS (SELECT ID_1,ID_2,ID_3,ID_4,ME_1 FROM (SELECT ID_1,ID_2,ID_3,ID_4,ME_1,DENSE_RANK() OVER (PARTITION BY ID_1,ID_2,ID_3,ID_4 ORDER BY PRIORITY) AS DENSE FROM (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,t0.ID_3 AS ID_3,t0.ID_4 AS ID_4,t0.ME_1 AS ME_1,1 AS PRIORITY FROM DS_18 t0 UNION ALL SELECT t1.ID_1 AS ID_1,t1.ID_2 AS ID_2,t1.ID_3 AS ID_3,t1.ID_4 AS ID_4,t1.ME_1 AS ME_1,2 AS PRIORITY FROM DS_25 t1 UNION ALL SELECT t2.ID_1 AS ID_1,t2.ID_2 AS ID_2,t2.ID_3 AS ID_3,t2.ID_4 AS ID_4,t2.ME_1 AS ME_1,3 AS PRIORITY FROM DS_26 t2) DT) FT WHERE (DENSE = 1));CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);CREATE INDEX DS_r_ID_3_IDX ON DS_r (ID_3);CREATE INDEX DS_r_ID_4_IDX ON DS_r (ID_4);"; 
		} else if (profileActive.equalsIgnoreCase("sqlServer")) {
			comandSqlResult = "SELECT t3.* INTO DS_r FROM (SELECT ID_1,ID_2,ID_3,ID_4,ME_1 FROM (SELECT ID_1,ID_2,ID_3,ID_4,ME_1,DENSE_RANK() OVER (PARTITION BY ID_1,ID_2,ID_3,ID_4 ORDER BY PRIORITY) AS DENSE FROM (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,t0.ID_3 AS ID_3,t0.ID_4 AS ID_4,t0.ME_1 AS ME_1,1 AS PRIORITY FROM DS_18 t0 UNION ALL SELECT t1.ID_1 AS ID_1,t1.ID_2 AS ID_2,t1.ID_3 AS ID_3,t1.ID_4 AS ID_4,t1.ME_1 AS ME_1,2 AS PRIORITY FROM DS_25 t1 UNION ALL SELECT t2.ID_1 AS ID_1,t2.ID_2 AS ID_2,t2.ID_3 AS ID_3,t2.ID_4 AS ID_4,t2.ME_1 AS ME_1,3 AS PRIORITY FROM DS_26 t2) DT) FT WHERE (DENSE = 1)) t3;"+"CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);"+"CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);"+"CREATE INDEX DS_r_ID_3_IDX ON DS_r (ID_3);"+"CREATE INDEX DS_r_ID_4_IDX ON DS_r (ID_4);";
		} else if (profileActive.equalsIgnoreCase("mySql")) {
			comandSqlResult = "CREATE TABLE DS_r AS (SELECT ID_1,ID_2,ID_3,ID_4,ME_1 FROM (SELECT ID_1,ID_2,ID_3,ID_4,ME_1,DENSE_RANK() OVER (PARTITION BY ID_1,ID_2,ID_3,ID_4 ORDER BY PRIORITY) AS DENSE FROM (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,t0.ID_3 AS ID_3,t0.ID_4 AS ID_4,t0.ME_1 AS ME_1,1 AS PRIORITY FROM DS_18 t0 UNION ALL SELECT t1.ID_1 AS ID_1,t1.ID_2 AS ID_2,t1.ID_3 AS ID_3,t1.ID_4 AS ID_4,t1.ME_1 AS ME_1,2 AS PRIORITY FROM DS_25 t1 UNION ALL SELECT t2.ID_1 AS ID_1,t2.ID_2 AS ID_2,t2.ID_3 AS ID_3,t2.ID_4 AS ID_4,t2.ME_1 AS ME_1,3 AS PRIORITY FROM DS_26 t2) DT) FT WHERE (DENSE = 1));CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);CREATE INDEX DS_r_ID_3_IDX ON DS_r (ID_3);CREATE INDEX DS_r_ID_4_IDX ON DS_r (ID_4);"; 
		} else if (profileActive.equalsIgnoreCase("postgreSql")) {
			comandSqlResult = "CREATE TABLE DS_r AS (SELECT ID_1,ID_2,ID_3,ID_4,ME_1 FROM (SELECT ID_1,ID_2,ID_3,ID_4,ME_1,DENSE_RANK() OVER (PARTITION BY ID_1,ID_2,ID_3,ID_4 ORDER BY PRIORITY) AS DENSE FROM (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,t0.ID_3 AS ID_3,t0.ID_4 AS ID_4,t0.ME_1 AS ME_1,1 AS PRIORITY FROM DS_18 t0 UNION ALL SELECT t1.ID_1 AS ID_1,t1.ID_2 AS ID_2,t1.ID_3 AS ID_3,t1.ID_4 AS ID_4,t1.ME_1 AS ME_1,2 AS PRIORITY FROM DS_25 t1 UNION ALL SELECT t2.ID_1 AS ID_1,t2.ID_2 AS ID_2,t2.ID_3 AS ID_3,t2.ID_4 AS ID_4,t2.ME_1 AS ME_1,3 AS PRIORITY FROM DS_26 t2) DT) FT WHERE (DENSE = 1));CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);CREATE INDEX DS_r_ID_3_IDX ON DS_r (ID_3);CREATE INDEX DS_r_ID_4_IDX ON DS_r (ID_4);"; 
		}
		
		String commandStatements = "DS_r := union(DS_18,DS_25,DS_26);";
		String result = translationUtilTest.translate(commandStatements);
		System.out.println("Print queryUnionThree: " +result);
		System.out.println("Print queryAttesaUnionThree: " +comandSqlResult);
		assertEquals("Test operator unionThree", comandSqlResult, result);
	}
        
	@Test
	@Transactional
	public void intersect() throws Exception {
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
			vtlDatasetSecond.setName("DS_25");
			vtlDatasetSecond.setIsOnlyAScalar(false);
			vtlDatasetSecond.setTransitory(false);
			vtlDatasetSecond
					.addComponent(translationUtilTest.getVtlComponent("ID_1", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDatasetSecond
					.addComponent(translationUtilTest.getVtlComponent("ID_2", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDatasetSecond
					.addComponent(translationUtilTest.getVtlComponent("ID_3", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDatasetSecond
					.addComponent(translationUtilTest.getVtlComponent("ID_4", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDatasetSecond
					.addComponent(translationUtilTest.getVtlComponent("ME_1", VtlType.INTEGER, VtlComponentRole.MEASURE));
			datasetRepository.save(vtlDatasetSecond);
		}
		
		System.out.println("Il profilo attivo è " + environment.getActiveProfiles()[0]);

		String comandSqlResult = "";
		String profileActive = environment.getActiveProfiles()[0];
		if (profileActive.equalsIgnoreCase("oracleSql")) {
			comandSqlResult = "CREATE TABLE DS_r AS (SELECT ID_1,ID_2,ID_3,ID_4,ME_1 FROM (SELECT ID_1,ID_2,ID_3,ID_4,ME_1,DENSE_RANK() OVER (PARTITION BY ID_1,ID_2,ID_3,ID_4 ORDER BY PRIORITY) AS DENSE,COUNT(*) OVER (PARTITION BY ID_1,ID_2,ID_3,ID_4) AS CNT FROM (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,t0.ID_3 AS ID_3,t0.ID_4 AS ID_4,t0.ME_1 AS ME_1,1 AS PRIORITY FROM DS_18 t0 UNION ALL SELECT t1.ID_1 AS ID_1,t1.ID_2 AS ID_2,t1.ID_3 AS ID_3,t1.ID_4 AS ID_4,t1.ME_1 AS ME_1,2 AS PRIORITY FROM DS_25 t1) DT) FT WHERE ((DENSE = 1) AND (CNT = 2)));"+"CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);"+"CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);"+"CREATE INDEX DS_r_ID_3_IDX ON DS_r (ID_3);"+"CREATE INDEX DS_r_ID_4_IDX ON DS_r (ID_4);"; 
		} else if (profileActive.equalsIgnoreCase("sqlServer")) {
			comandSqlResult = "SELECT t2.* INTO DS_r FROM (SELECT ID_1,ID_2,ID_3,ID_4,ME_1 FROM (SELECT ID_1,ID_2,ID_3,ID_4,ME_1,DENSE_RANK() OVER (PARTITION BY ID_1,ID_2,ID_3,ID_4 ORDER BY PRIORITY) AS DENSE,COUNT(*) OVER (PARTITION BY ID_1,ID_2,ID_3,ID_4) AS CNT FROM (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,t0.ID_3 AS ID_3,t0.ID_4 AS ID_4,t0.ME_1 AS ME_1,1 AS PRIORITY FROM DS_18 t0 UNION ALL SELECT t1.ID_1 AS ID_1,t1.ID_2 AS ID_2,t1.ID_3 AS ID_3,t1.ID_4 AS ID_4,t1.ME_1 AS ME_1,2 AS PRIORITY FROM DS_25 t1) DT) FT WHERE ((DENSE = 1) AND (CNT = 2))) t2;"+"CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);"+"CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);"+"CREATE INDEX DS_r_ID_3_IDX ON DS_r (ID_3);"+"CREATE INDEX DS_r_ID_4_IDX ON DS_r (ID_4);";
		} else if (profileActive.equalsIgnoreCase("mySql")) {
			comandSqlResult = "CREATE TABLE DS_r AS (SELECT ID_1,ID_2,ID_3,ID_4,ME_1 FROM (SELECT ID_1,ID_2,ID_3,ID_4,ME_1,DENSE_RANK() OVER (PARTITION BY ID_1,ID_2,ID_3,ID_4 ORDER BY PRIORITY) AS DENSE,COUNT(*) OVER (PARTITION BY ID_1,ID_2,ID_3,ID_4) AS CNT FROM (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,t0.ID_3 AS ID_3,t0.ID_4 AS ID_4,t0.ME_1 AS ME_1,1 AS PRIORITY FROM DS_18 t0 UNION ALL SELECT t1.ID_1 AS ID_1,t1.ID_2 AS ID_2,t1.ID_3 AS ID_3,t1.ID_4 AS ID_4,t1.ME_1 AS ME_1,2 AS PRIORITY FROM DS_25 t1) DT) FT WHERE ((DENSE = 1) AND (CNT = 2)));"+"CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);"+"CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);"+"CREATE INDEX DS_r_ID_3_IDX ON DS_r (ID_3);"+"CREATE INDEX DS_r_ID_4_IDX ON DS_r (ID_4);";
		} else if (profileActive.equalsIgnoreCase("postgreSql")) {
			comandSqlResult = "CREATE TABLE DS_r AS (SELECT ID_1,ID_2,ID_3,ID_4,ME_1 FROM (SELECT ID_1,ID_2,ID_3,ID_4,ME_1,DENSE_RANK() OVER (PARTITION BY ID_1,ID_2,ID_3,ID_4 ORDER BY PRIORITY) AS DENSE,COUNT(*) OVER (PARTITION BY ID_1,ID_2,ID_3,ID_4) AS CNT FROM (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,t0.ID_3 AS ID_3,t0.ID_4 AS ID_4,t0.ME_1 AS ME_1,1 AS PRIORITY FROM DS_18 t0 UNION ALL SELECT t1.ID_1 AS ID_1,t1.ID_2 AS ID_2,t1.ID_3 AS ID_3,t1.ID_4 AS ID_4,t1.ME_1 AS ME_1,2 AS PRIORITY FROM DS_25 t1) DT) FT WHERE ((DENSE = 1) AND (CNT = 2)));"+"CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);"+"CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);"+"CREATE INDEX DS_r_ID_3_IDX ON DS_r (ID_3);"+"CREATE INDEX DS_r_ID_4_IDX ON DS_r (ID_4);"; 
		}
		
		String commandStatements = "DS_r := intersect(DS_18,DS_25);";
		String result = translationUtilTest.translate(commandStatements);
		System.out.println("Print queryIntersect: " +result);
		System.out.println("Print queryAttesaIntersect: " +comandSqlResult);
		assertEquals("Test operator Intersect", comandSqlResult, result);
	}
        
	@Test
	@Transactional
	public void intersectThree() throws Exception {
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
			vtlDatasetSecond.setName("DS_25");
			vtlDatasetSecond.setIsOnlyAScalar(false);
			vtlDatasetSecond.setTransitory(false);
			vtlDatasetSecond
					.addComponent(translationUtilTest.getVtlComponent("ID_1", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDatasetSecond
					.addComponent(translationUtilTest.getVtlComponent("ID_2", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDatasetSecond
					.addComponent(translationUtilTest.getVtlComponent("ID_3", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDatasetSecond
					.addComponent(translationUtilTest.getVtlComponent("ID_4", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDatasetSecond
					.addComponent(translationUtilTest.getVtlComponent("ME_1", VtlType.INTEGER, VtlComponentRole.MEASURE));
			datasetRepository.save(vtlDatasetSecond);
			
                        VtlDataset vtlDataset3 = new VtlDataset();

			vtlDataset3.setPersistent(true);
			vtlDataset3.setName("DS_26");
			vtlDataset3.setIsOnlyAScalar(false);
			vtlDataset3.setTransitory(false);
			vtlDataset3.addComponent(translationUtilTest.getVtlComponent("ID_1", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDataset3.addComponent(translationUtilTest.getVtlComponent("ID_2", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDataset3.addComponent(translationUtilTest.getVtlComponent("ID_3", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDataset3.addComponent(translationUtilTest.getVtlComponent("ID_4", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDataset3.addComponent(translationUtilTest.getVtlComponent("ME_1", VtlType.INTEGER, VtlComponentRole.MEASURE));
			datasetRepository.save(vtlDataset3);
		}
		
		System.out.println("Il profilo attivo è " + environment.getActiveProfiles()[0]);

		String comandSqlResult = "";
		String profileActive = environment.getActiveProfiles()[0];
		if (profileActive.equalsIgnoreCase("oracleSql")) {
			comandSqlResult = "CREATE TABLE DS_r AS (SELECT ID_1,ID_2,ID_3,ID_4,ME_1 FROM (SELECT ID_1,ID_2,ID_3,ID_4,ME_1,DENSE_RANK() OVER (PARTITION BY ID_1,ID_2,ID_3,ID_4 ORDER BY PRIORITY) AS DENSE,COUNT(*) OVER (PARTITION BY ID_1,ID_2,ID_3,ID_4) AS CNT FROM (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,t0.ID_3 AS ID_3,t0.ID_4 AS ID_4,t0.ME_1 AS ME_1,1 AS PRIORITY FROM DS_18 t0 UNION ALL SELECT t1.ID_1 AS ID_1,t1.ID_2 AS ID_2,t1.ID_3 AS ID_3,t1.ID_4 AS ID_4,t1.ME_1 AS ME_1,2 AS PRIORITY FROM DS_25 t1 UNION ALL SELECT t2.ID_1 AS ID_1,t2.ID_2 AS ID_2,t2.ID_3 AS ID_3,t2.ID_4 AS ID_4,t2.ME_1 AS ME_1,3 AS PRIORITY FROM DS_26 t2) DT) FT WHERE ((DENSE = 1) AND (CNT = 3)));CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);CREATE INDEX DS_r_ID_3_IDX ON DS_r (ID_3);CREATE INDEX DS_r_ID_4_IDX ON DS_r (ID_4);"; 
		} else if (profileActive.equalsIgnoreCase("sqlServer")) {
			comandSqlResult = "SELECT t3.* INTO DS_r FROM (SELECT ID_1,ID_2,ID_3,ID_4,ME_1 FROM (SELECT ID_1,ID_2,ID_3,ID_4,ME_1,DENSE_RANK() OVER (PARTITION BY ID_1,ID_2,ID_3,ID_4 ORDER BY PRIORITY) AS DENSE,COUNT(*) OVER (PARTITION BY ID_1,ID_2,ID_3,ID_4) AS CNT FROM (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,t0.ID_3 AS ID_3,t0.ID_4 AS ID_4,t0.ME_1 AS ME_1,1 AS PRIORITY FROM DS_18 t0 UNION ALL SELECT t1.ID_1 AS ID_1,t1.ID_2 AS ID_2,t1.ID_3 AS ID_3,t1.ID_4 AS ID_4,t1.ME_1 AS ME_1,2 AS PRIORITY FROM DS_25 t1 UNION ALL SELECT t2.ID_1 AS ID_1,t2.ID_2 AS ID_2,t2.ID_3 AS ID_3,t2.ID_4 AS ID_4,t2.ME_1 AS ME_1,3 AS PRIORITY FROM DS_26 t2) DT) FT WHERE ((DENSE = 1) AND (CNT = 3))) t3;"+"CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);"+"CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);"+"CREATE INDEX DS_r_ID_3_IDX ON DS_r (ID_3);"+"CREATE INDEX DS_r_ID_4_IDX ON DS_r (ID_4);";
		} else if (profileActive.equalsIgnoreCase("mySql")) {
			comandSqlResult = "CREATE TABLE DS_r AS (SELECT ID_1,ID_2,ID_3,ID_4,ME_1 FROM (SELECT ID_1,ID_2,ID_3,ID_4,ME_1,DENSE_RANK() OVER (PARTITION BY ID_1,ID_2,ID_3,ID_4 ORDER BY PRIORITY) AS DENSE,COUNT(*) OVER (PARTITION BY ID_1,ID_2,ID_3,ID_4) AS CNT FROM (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,t0.ID_3 AS ID_3,t0.ID_4 AS ID_4,t0.ME_1 AS ME_1,1 AS PRIORITY FROM DS_18 t0 UNION ALL SELECT t1.ID_1 AS ID_1,t1.ID_2 AS ID_2,t1.ID_3 AS ID_3,t1.ID_4 AS ID_4,t1.ME_1 AS ME_1,2 AS PRIORITY FROM DS_25 t1 UNION ALL SELECT t2.ID_1 AS ID_1,t2.ID_2 AS ID_2,t2.ID_3 AS ID_3,t2.ID_4 AS ID_4,t2.ME_1 AS ME_1,3 AS PRIORITY FROM DS_26 t2) DT) FT WHERE ((DENSE = 1) AND (CNT = 3)));CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);CREATE INDEX DS_r_ID_3_IDX ON DS_r (ID_3);CREATE INDEX DS_r_ID_4_IDX ON DS_r (ID_4);"; 
		} else if (profileActive.equalsIgnoreCase("postgreSql")) {
			comandSqlResult = "CREATE TABLE DS_r AS (SELECT ID_1,ID_2,ID_3,ID_4,ME_1 FROM (SELECT ID_1,ID_2,ID_3,ID_4,ME_1,DENSE_RANK() OVER (PARTITION BY ID_1,ID_2,ID_3,ID_4 ORDER BY PRIORITY) AS DENSE,COUNT(*) OVER (PARTITION BY ID_1,ID_2,ID_3,ID_4) AS CNT FROM (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,t0.ID_3 AS ID_3,t0.ID_4 AS ID_4,t0.ME_1 AS ME_1,1 AS PRIORITY FROM DS_18 t0 UNION ALL SELECT t1.ID_1 AS ID_1,t1.ID_2 AS ID_2,t1.ID_3 AS ID_3,t1.ID_4 AS ID_4,t1.ME_1 AS ME_1,2 AS PRIORITY FROM DS_25 t1 UNION ALL SELECT t2.ID_1 AS ID_1,t2.ID_2 AS ID_2,t2.ID_3 AS ID_3,t2.ID_4 AS ID_4,t2.ME_1 AS ME_1,3 AS PRIORITY FROM DS_26 t2) DT) FT WHERE ((DENSE = 1) AND (CNT = 3)));CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);CREATE INDEX DS_r_ID_3_IDX ON DS_r (ID_3);CREATE INDEX DS_r_ID_4_IDX ON DS_r (ID_4);"; 
		}
		
		String commandStatements = "DS_r := intersect(DS_18,DS_25,DS_26);";
		String result = translationUtilTest.translate(commandStatements);
		System.out.println("Print queryIntersectThree: " +result);
		System.out.println("Print queryAttesaIntersectThree: " +comandSqlResult);
		assertEquals("Test operator intersectThree", comandSqlResult, result);
	}
        
	@Test
	@Transactional
	public void setdiff() throws Exception {
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
			vtlDatasetSecond.setName("DS_25");
			vtlDatasetSecond.setIsOnlyAScalar(false);
			vtlDatasetSecond.setTransitory(false);
			vtlDatasetSecond
					.addComponent(translationUtilTest.getVtlComponent("ID_1", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDatasetSecond
					.addComponent(translationUtilTest.getVtlComponent("ID_2", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDatasetSecond
					.addComponent(translationUtilTest.getVtlComponent("ID_3", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDatasetSecond
					.addComponent(translationUtilTest.getVtlComponent("ID_4", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDatasetSecond
					.addComponent(translationUtilTest.getVtlComponent("ME_1", VtlType.INTEGER, VtlComponentRole.MEASURE));
			datasetRepository.save(vtlDatasetSecond);
		}
		System.out.println("Il profilo attivo è " + environment.getActiveProfiles()[0]);

		String comandSqlResult = "";
		String profileActive = environment.getActiveProfiles()[0];
		if (profileActive.equalsIgnoreCase("oracleSql")) {
//			comandSqlResult = "CREATE TABLE DS_r AS (SELECT ID_1,ID_2,ID_3,ID_4,ME_1 FROM DS_18 WHERE ((ID_1, ID_2, ID_3, ID_4) NOT IN (SELECT ID_1,ID_2,ID_3,ID_4 FROM DS_25) ));CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);CREATE INDEX DS_r_ID_3_IDX ON DS_r (ID_3);CREATE INDEX DS_r_ID_4_IDX ON DS_r (ID_4);";
			comandSqlResult = "CREATE TABLE DS_r AS (SELECT t2.ID_1 AS ID_1,t2.ID_2 AS ID_2,t2.ID_3 AS ID_3,t2.ID_4 AS ID_4,t2.ME_1 AS ME_1 FROM DS_18 t2 LEFT OUTER JOIN DS_25 t1 ON (((t2.ID_1 = t1.ID_1) AND (t2.ID_2 = t1.ID_2) AND (t2.ID_3 = t1.ID_3) AND (t2.ID_4 = t1.ID_4))) WHERE (t1.ID_1 IS NULL));CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);CREATE INDEX DS_r_ID_3_IDX ON DS_r (ID_3);CREATE INDEX DS_r_ID_4_IDX ON DS_r (ID_4);"; 
		} else if (profileActive.equalsIgnoreCase("sqlServer")) {
			comandSqlResult = "SELECT t3.* INTO DS_r FROM (SELECT t2.ID_1 AS ID_1,t2.ID_2 AS ID_2,t2.ID_3 AS ID_3,t2.ID_4 AS ID_4,t2.ME_1 AS ME_1 FROM DS_18 t2 LEFT OUTER JOIN DS_25 t1 ON (((t2.ID_1 = t1.ID_1) AND (t2.ID_2 = t1.ID_2) AND (t2.ID_3 = t1.ID_3) AND (t2.ID_4 = t1.ID_4))) WHERE (t1.ID_1 IS NULL)) t3;CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);CREATE INDEX DS_r_ID_3_IDX ON DS_r (ID_3);CREATE INDEX DS_r_ID_4_IDX ON DS_r (ID_4);";
		} else if (profileActive.equalsIgnoreCase("mySql")) {
			comandSqlResult = "CREATE TABLE DS_r AS (SELECT t2.ID_1 AS ID_1,t2.ID_2 AS ID_2,t2.ID_3 AS ID_3,t2.ID_4 AS ID_4,t2.ME_1 AS ME_1 FROM DS_18 t2 LEFT OUTER JOIN DS_25 t1 ON (((t2.ID_1 = t1.ID_1) AND (t2.ID_2 = t1.ID_2) AND (t2.ID_3 = t1.ID_3) AND (t2.ID_4 = t1.ID_4))) WHERE (t1.ID_1 IS NULL));CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);CREATE INDEX DS_r_ID_3_IDX ON DS_r (ID_3);CREATE INDEX DS_r_ID_4_IDX ON DS_r (ID_4);"; 
		} else if (profileActive.equalsIgnoreCase("postgreSql")) {
			comandSqlResult = "CREATE TABLE DS_r AS (SELECT t2.ID_1 AS ID_1,t2.ID_2 AS ID_2,t2.ID_3 AS ID_3,t2.ID_4 AS ID_4,t2.ME_1 AS ME_1 FROM DS_18 t2 LEFT OUTER JOIN DS_25 t1 ON (((t2.ID_1 = t1.ID_1) AND (t2.ID_2 = t1.ID_2) AND (t2.ID_3 = t1.ID_3) AND (t2.ID_4 = t1.ID_4))) WHERE (t1.ID_1 IS NULL));CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);CREATE INDEX DS_r_ID_3_IDX ON DS_r (ID_3);CREATE INDEX DS_r_ID_4_IDX ON DS_r (ID_4);";  
		}
		String commandStatements = "DS_r := setdiff ( DS_18, DS_25 );";
		String result = translationUtilTest.translate(commandStatements);
		System.out.println("Print querySetdiff: " +result);
		System.out.println("Print queryAttesaSetdiff: " +comandSqlResult);
		assertEquals("Test operator Setdiff", comandSqlResult, result);
	}

	@Test
	@Transactional
	public void symdiff() throws Exception {
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
			vtlDatasetSecond.setName("DS_25");
			vtlDatasetSecond.setIsOnlyAScalar(false);
			vtlDatasetSecond.setTransitory(false);
			vtlDatasetSecond
					.addComponent(translationUtilTest.getVtlComponent("ID_1", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDatasetSecond
					.addComponent(translationUtilTest.getVtlComponent("ID_2", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDatasetSecond
					.addComponent(translationUtilTest.getVtlComponent("ID_3", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDatasetSecond
					.addComponent(translationUtilTest.getVtlComponent("ID_4", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDatasetSecond
					.addComponent(translationUtilTest.getVtlComponent("ME_1", VtlType.INTEGER, VtlComponentRole.MEASURE));
			datasetRepository.save(vtlDatasetSecond);
		}
		
		System.out.println("Il profilo attivo è " + environment.getActiveProfiles()[0]);

		String comandSqlResult = "";
		String profileActive = environment.getActiveProfiles()[0];
		if (profileActive.equalsIgnoreCase("oracleSql")) {
			comandSqlResult = "CREATE TABLE DS_r AS (SELECT t2.ID_1 AS ID_1,t2.ID_2 AS ID_2,t2.ID_3 AS ID_3,t2.ID_4 AS ID_4,t2.ME_1 AS ME_1 FROM DS_18 t2 LEFT OUTER JOIN DS_25 t1 ON (((t2.ID_1 = t1.ID_1) AND (t2.ID_2 = t1.ID_2) AND (t2.ID_3 = t1.ID_3) AND (t2.ID_4 = t1.ID_4))) WHERE (t1.ID_1 IS NULL) UNION ALL SELECT t3.ID_1 AS ID_1,t3.ID_2 AS ID_2,t3.ID_3 AS ID_3,t3.ID_4 AS ID_4,t3.ME_1 AS ME_1 FROM DS_25 t3 LEFT OUTER JOIN DS_18 t0 ON (((t3.ID_1 = t0.ID_1) AND (t3.ID_2 = t0.ID_2) AND (t3.ID_3 = t0.ID_3) AND (t3.ID_4 = t0.ID_4))) WHERE (t0.ID_1 IS NULL));"+"CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);"+"CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);"+"CREATE INDEX DS_r_ID_3_IDX ON DS_r (ID_3);"+"CREATE INDEX DS_r_ID_4_IDX ON DS_r (ID_4);";  
		} else if (profileActive.equalsIgnoreCase("sqlServer")) {
			comandSqlResult = "SELECT t4.* INTO DS_r FROM (SELECT t2.ID_1 AS ID_1,t2.ID_2 AS ID_2,t2.ID_3 AS ID_3,t2.ID_4 AS ID_4,t2.ME_1 AS ME_1 FROM DS_18 t2 LEFT OUTER JOIN DS_25 t1 ON (((t2.ID_1 = t1.ID_1) AND (t2.ID_2 = t1.ID_2) AND (t2.ID_3 = t1.ID_3) AND (t2.ID_4 = t1.ID_4))) WHERE (t1.ID_1 IS NULL) UNION ALL SELECT t3.ID_1 AS ID_1,t3.ID_2 AS ID_2,t3.ID_3 AS ID_3,t3.ID_4 AS ID_4,t3.ME_1 AS ME_1 FROM DS_25 t3 LEFT OUTER JOIN DS_18 t0 ON (((t3.ID_1 = t0.ID_1) AND (t3.ID_2 = t0.ID_2) AND (t3.ID_3 = t0.ID_3) AND (t3.ID_4 = t0.ID_4))) WHERE (t0.ID_1 IS NULL)) t4;CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);CREATE INDEX DS_r_ID_3_IDX ON DS_r (ID_3);CREATE INDEX DS_r_ID_4_IDX ON DS_r (ID_4);";
		} else if (profileActive.equalsIgnoreCase("mySql")) {
			comandSqlResult = "CREATE TABLE DS_r AS (SELECT t2.ID_1 AS ID_1,t2.ID_2 AS ID_2,t2.ID_3 AS ID_3,t2.ID_4 AS ID_4,t2.ME_1 AS ME_1 FROM DS_18 t2 LEFT OUTER JOIN DS_25 t1 ON (((t2.ID_1 = t1.ID_1) AND (t2.ID_2 = t1.ID_2) AND (t2.ID_3 = t1.ID_3) AND (t2.ID_4 = t1.ID_4))) WHERE (t1.ID_1 IS NULL) UNION ALL SELECT t3.ID_1 AS ID_1,t3.ID_2 AS ID_2,t3.ID_3 AS ID_3,t3.ID_4 AS ID_4,t3.ME_1 AS ME_1 FROM DS_25 t3 LEFT OUTER JOIN DS_18 t0 ON (((t3.ID_1 = t0.ID_1) AND (t3.ID_2 = t0.ID_2) AND (t3.ID_3 = t0.ID_3) AND (t3.ID_4 = t0.ID_4))) WHERE (t0.ID_1 IS NULL));"+"CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);"+"CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);"+"CREATE INDEX DS_r_ID_3_IDX ON DS_r (ID_3);"+"CREATE INDEX DS_r_ID_4_IDX ON DS_r (ID_4);";    
		} else if (profileActive.equalsIgnoreCase("postgreSql")) {
			comandSqlResult = "CREATE TABLE DS_r AS (SELECT t2.ID_1 AS ID_1,t2.ID_2 AS ID_2,t2.ID_3 AS ID_3,t2.ID_4 AS ID_4,t2.ME_1 AS ME_1 FROM DS_18 t2 LEFT OUTER JOIN DS_25 t1 ON (((t2.ID_1 = t1.ID_1) AND (t2.ID_2 = t1.ID_2) AND (t2.ID_3 = t1.ID_3) AND (t2.ID_4 = t1.ID_4))) WHERE (t1.ID_1 IS NULL) UNION ALL SELECT t3.ID_1 AS ID_1,t3.ID_2 AS ID_2,t3.ID_3 AS ID_3,t3.ID_4 AS ID_4,t3.ME_1 AS ME_1 FROM DS_25 t3 LEFT OUTER JOIN DS_18 t0 ON (((t3.ID_1 = t0.ID_1) AND (t3.ID_2 = t0.ID_2) AND (t3.ID_3 = t0.ID_3) AND (t3.ID_4 = t0.ID_4))) WHERE (t0.ID_1 IS NULL));"+"CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);"+"CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);"+"CREATE INDEX DS_r_ID_3_IDX ON DS_r (ID_3);"+"CREATE INDEX DS_r_ID_4_IDX ON DS_r (ID_4);";    
		}
		
		String commandStatements = "DS_r := symdiff ( DS_18, DS_25 );";
		String result = translationUtilTest.translate(commandStatements);
		System.out.println("Print querySymdiff: " +result);
		System.out.println("Print queryAttesaSymdiff: " +comandSqlResult);
		assertEquals("Test operator Symdiff", comandSqlResult, result);
	}
}
