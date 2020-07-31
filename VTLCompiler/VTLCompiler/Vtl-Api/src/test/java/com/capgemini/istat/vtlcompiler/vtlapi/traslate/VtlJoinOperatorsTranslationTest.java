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
public class VtlJoinOperatorsTranslationTest {

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
	
//TODO Da aggiornare dopo implementazione operatore 'inner_join' al momento il test sarà sempre verificato
	@Test
	@Transactional
	public void innerJoin() throws Exception {
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
			vtlDatasetSecond.setName("DS_36");
			vtlDatasetSecond.setIsOnlyAScalar(false);
			vtlDatasetSecond.setTransitory(false);
			vtlDatasetSecond
					.addComponent(translationUtilTest.getVtlComponent("ID_1", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDatasetSecond
					.addComponent(translationUtilTest.getVtlComponent("ID_2", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDatasetSecond
					.addComponent(translationUtilTest.getVtlComponent("ME_1A", VtlType.STRING, VtlComponentRole.MEASURE));
			vtlDatasetSecond
					.addComponent(translationUtilTest.getVtlComponent("ME_2", VtlType.STRING, VtlComponentRole.MEASURE));
			datasetRepository.save(vtlDatasetSecond);
		}
		
		System.out.println("Il profilo attivo è " + environment.getActiveProfiles()[0]);

		String comandSqlResult = "";
		String profileActive = environment.getActiveProfiles()[0];
		if (profileActive.equalsIgnoreCase("oracleSql")) {
			comandSqlResult = "CREATE TABLE temporary_7 AS (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,t0.ME_1 AS ME_1,t0.ME_2 AS d1#ME_2,d2.ME_1A AS ME_1A,d2.ME_2 AS d2#ME_2 FROM DS_4 t0 INNER JOIN DS_36 d2 ON (((t0.ID_1 = d2.ID_1) AND (t0.ID_2 = d2.ID_2))));CREATE INDEX temporary_7_ID_1_IDX ON temporary_7 (ID_1);CREATE INDEX temporary_7_ID_2_IDX ON temporary_7 (ID_2);CREATE TABLE DS_r AS (SELECT t1.ID_1 AS ID_1,t1.ID_2 AS ID_2,t1.ME_1 AS ME_1,t1.ME_1A AS ME_1A,t1.d2#ME_2 AS ME_2 FROM temporary_7 t1);CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);"; 
		} else if (profileActive.equalsIgnoreCase("sqlServer")) {
			comandSqlResult = "SELECT t1.* INTO temporary_7 FROM (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,t0.ME_1 AS ME_1,t0.ME_2 AS d1#ME_2,d2.ME_1A AS ME_1A,d2.ME_2 AS d2#ME_2 FROM DS_4 t0 INNER JOIN DS_36 d2 ON (((t0.ID_1 = d2.ID_1) AND (t0.ID_2 = d2.ID_2)))) t1;CREATE INDEX temporary_7_ID_1_IDX ON temporary_7 (ID_1);CREATE INDEX temporary_7_ID_2_IDX ON temporary_7 (ID_2);SELECT t3.* INTO DS_r FROM (SELECT t2.ID_1 AS ID_1,t2.ID_2 AS ID_2,t2.ME_1 AS ME_1,t2.ME_1A AS ME_1A,t2.d2#ME_2 AS ME_2 FROM temporary_7 t2) t3;CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);"; 
		} else if (profileActive.equalsIgnoreCase("mySql")) {
			comandSqlResult = "CREATE TABLE temporary_7 AS (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,t0.ME_1 AS ME_1,t0.ME_2 AS d1#ME_2,d2.ME_1A AS ME_1A,d2.ME_2 AS d2#ME_2 FROM DS_4 t0 INNER JOIN DS_36 d2 ON (((t0.ID_1 = d2.ID_1) AND (t0.ID_2 = d2.ID_2))));CREATE INDEX temporary_7_ID_1_IDX ON temporary_7 (ID_1);CREATE INDEX temporary_7_ID_2_IDX ON temporary_7 (ID_2);CREATE TABLE DS_r AS (SELECT t1.ID_1 AS ID_1,t1.ID_2 AS ID_2,t1.ME_1 AS ME_1,t1.ME_1A AS ME_1A,t1.d2#ME_2 AS ME_2 FROM temporary_7 t1);CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);"; 
		} else if (profileActive.equalsIgnoreCase("postgreSql")) {
			comandSqlResult = "CREATE TABLE temporary_7 AS (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,t0.ME_1 AS ME_1,t0.ME_2 AS d1#ME_2,d2.ME_1A AS ME_1A,d2.ME_2 AS d2#ME_2 FROM DS_4 t0 INNER JOIN DS_36 d2 ON (((t0.ID_1 = d2.ID_1) AND (t0.ID_2 = d2.ID_2))));CREATE INDEX temporary_7_ID_1_IDX ON temporary_7 (ID_1);CREATE INDEX temporary_7_ID_2_IDX ON temporary_7 (ID_2);CREATE TABLE DS_r AS (SELECT t1.ID_1 AS ID_1,t1.ID_2 AS ID_2,t1.ME_1 AS ME_1,t1.ME_1A AS ME_1A,t1.d2#ME_2 AS ME_2 FROM temporary_7 t1);CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);"; 
		}
		
		String commandStatements = "DS_r  := inner_join ( DS_4 as d1, DS_36 as d2 keep Me_1, d2#Me_2, Me_1A);";
		String result = translationUtilTest.translate(commandStatements);
		System.out.println("Print queryInnerJoin: " +result);
		System.out.println("Print queryAttesaInnerJoin: " +comandSqlResult);
		assertEquals("Test operator InnerJoin", translationUtilTest.temporaryValueObfuscation(comandSqlResult), translationUtilTest.temporaryValueObfuscation(result));

	}

	@Test
	@Transactional
	public void innerJoinFilter() throws Exception {
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
			vtlDatasetSecond.setName("DS_36");
			vtlDatasetSecond.setIsOnlyAScalar(false);
			vtlDatasetSecond.setTransitory(false);
			vtlDatasetSecond
					.addComponent(translationUtilTest.getVtlComponent("ID_1", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDatasetSecond
					.addComponent(translationUtilTest.getVtlComponent("ID_2", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDatasetSecond
					.addComponent(translationUtilTest.getVtlComponent("ME_1A", VtlType.STRING, VtlComponentRole.MEASURE));
			vtlDatasetSecond
					.addComponent(translationUtilTest.getVtlComponent("ME_2", VtlType.STRING, VtlComponentRole.MEASURE));
			datasetRepository.save(vtlDatasetSecond);
		}
		
		System.out.println("Il profilo attivo è " + environment.getActiveProfiles()[0]);

		String comandSqlResult = "";
		String profileActive = environment.getActiveProfiles()[0];
		if (profileActive.equalsIgnoreCase("oracleSql")) {
			comandSqlResult = "CREATE TABLE temporary_2786 AS (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,t0.ME_1 AS ME_1,t0.ME_2 AS d1#ME_2,d2.ME_1A AS ME_1A,d2.ME_2 AS d2#ME_2 FROM DS_4 t0 INNER JOIN DS_36 d2 ON (((t0.ID_1 = d2.ID_1) AND (t0.ID_2 = d2.ID_2))));CREATE INDEX temporary_2786_ID_1_IDX ON temporary_2786 (ID_1);CREATE INDEX temporary_2786_ID_2_IDX ON temporary_2786 (ID_2);CREATE TABLE DS_r AS (SELECT t1.ID_1 AS ID_1,t1.ID_2 AS ID_2,t1.ME_1 AS ME_1,t1.ME_1A AS ME_1A,t1.d2#ME_2 AS ME_2,concat(t1.ME_1,t1.ME_1A) AS Me_4 FROM temporary_2786 t1 WHERE (t1.ME_1='A'));CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);";
		} else if (profileActive.equalsIgnoreCase("sqlServer")) {
			comandSqlResult = "SELECT t1.* INTO temporary_75 FROM (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,t0.ME_1 AS ME_1,t0.ME_2 AS d1#ME_2,d2.ME_1A AS ME_1A,d2.ME_2 AS d2#ME_2 FROM DS_4 t0 INNER JOIN DS_36 d2 ON (((t0.ID_1 = d2.ID_1) AND (t0.ID_2 = d2.ID_2)))) t1;CREATE INDEX temporary_75_ID_1_IDX ON temporary_75 (ID_1);CREATE INDEX temporary_75_ID_2_IDX ON temporary_75 (ID_2);SELECT t3.* INTO DS_r FROM (SELECT t2.ID_1 AS ID_1,t2.ID_2 AS ID_2,t2.ME_1 AS ME_1,t2.ME_1A AS ME_1A,t2.d2#ME_2 AS ME_2,concat(t2.ME_1,t2.ME_1A) AS Me_4 FROM temporary_75 t2 WHERE (t2.ME_1='A')) t3;CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);"; 
		} else if (profileActive.equalsIgnoreCase("mySql")) {
			comandSqlResult = "CREATE TABLE temporary_75 AS (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,t0.ME_1 AS ME_1,t0.ME_2 AS d1#ME_2,d2.ME_1A AS ME_1A,d2.ME_2 AS d2#ME_2 FROM DS_4 t0 INNER JOIN DS_36 d2 ON (((t0.ID_1 = d2.ID_1) AND (t0.ID_2 = d2.ID_2))));CREATE INDEX temporary_75_ID_1_IDX ON temporary_75 (ID_1);CREATE INDEX temporary_75_ID_2_IDX ON temporary_75 (ID_2);CREATE TABLE DS_r AS (SELECT t1.ID_1 AS ID_1,t1.ID_2 AS ID_2,t1.ME_1 AS ME_1,t1.ME_1A AS ME_1A,t1.d2#ME_2 AS ME_2,concat(t1.ME_1,t1.ME_1A) AS Me_4 FROM temporary_75 t1 WHERE (t1.ME_1='A'));CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);"; 
		} else if (profileActive.equalsIgnoreCase("postgreSql")) {
			comandSqlResult = "CREATE TABLE temporary_75 AS (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,t0.ME_1 AS ME_1,t0.ME_2 AS d1#ME_2,d2.ME_1A AS ME_1A,d2.ME_2 AS d2#ME_2 FROM DS_4 t0 INNER JOIN DS_36 d2 ON (((t0.ID_1 = d2.ID_1) AND (t0.ID_2 = d2.ID_2))));CREATE INDEX temporary_75_ID_1_IDX ON temporary_75 (ID_1);CREATE INDEX temporary_75_ID_2_IDX ON temporary_75 (ID_2);CREATE TABLE DS_r AS (SELECT t1.ID_1 AS ID_1,t1.ID_2 AS ID_2,t1.ME_1 AS ME_1,t1.ME_1A AS ME_1A,t1.d2#ME_2 AS ME_2,concat(t1.ME_1,t1.ME_1A) AS Me_4 FROM temporary_75 t1 WHERE (t1.ME_1='A'));CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);"; 
		}
		
		
		String commandStatements = "DS_r := inner_join (DS_4 as d1, DS_36 as d2 filter Me_1 = \"A\" calc Me_4 := Me_1 || Me_1A drop d1#Me_2);";
		String result = translationUtilTest.translate(commandStatements);
		System.out.println("Print queryInnerJoinFilter: " +result);
		System.out.println("Print queryAttesaInnerJoinFilter: " +comandSqlResult);
		assertEquals("Test operator InnerJoinFilter", translationUtilTest.temporaryValueObfuscation(comandSqlResult), translationUtilTest.temporaryValueObfuscation(result));


	}

	@Test
	@Transactional
	public void innerJoinKeep() throws Exception {
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
			vtlDatasetSecond.setName("DS_36");
			vtlDatasetSecond.setIsOnlyAScalar(false);
			vtlDatasetSecond.setTransitory(false);
			vtlDatasetSecond
					.addComponent(translationUtilTest.getVtlComponent("ID_1", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDatasetSecond
					.addComponent(translationUtilTest.getVtlComponent("ID_2", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDatasetSecond
					.addComponent(translationUtilTest.getVtlComponent("ME_1A", VtlType.STRING, VtlComponentRole.MEASURE));
			vtlDatasetSecond
					.addComponent(translationUtilTest.getVtlComponent("ME_2", VtlType.STRING, VtlComponentRole.MEASURE));
			datasetRepository.save(vtlDatasetSecond);
		}
		
		System.out.println("Il profilo attivo è " + environment.getActiveProfiles()[0]);

		String comandSqlResult = "";
		String profileActive = environment.getActiveProfiles()[0];
		if (profileActive.equalsIgnoreCase("oracleSql")) {
			comandSqlResult = "CREATE TABLE temporary_17 AS (SELECT t1.ID_1 AS ID_1,t1.ID_2 AS ID_2,t1.ME_1 AS ME_1,t1.ME_2 AS ME_2 FROM DS_4 t1);CREATE INDEX temporary_17_ID_1_IDX ON temporary_17 (ID_1);CREATE INDEX temporary_17_ID_2_IDX ON temporary_17 (ID_2);CREATE TABLE DS_r AS (SELECT t2.ID_1 AS ID_1,t2.ID_2 AS ID_2,t2.ME_1 AS ME_1,concat(t2.ME_2,'NEW') AS Me_2 FROM temporary_17 t2 WHERE (t2.ID_2='B'));CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);"; 
		} else if (profileActive.equalsIgnoreCase("sqlServer")) {
			comandSqlResult = "SELECT t2.* INTO temporary_17 FROM (SELECT t1.ID_1 AS ID_1,t1.ID_2 AS ID_2,t1.ME_1 AS ME_1,t1.ME_2 AS ME_2 FROM DS_4 t1) t2;CREATE INDEX temporary_17_ID_1_IDX ON temporary_17 (ID_1);CREATE INDEX temporary_17_ID_2_IDX ON temporary_17 (ID_2);SELECT t4.* INTO DS_r FROM (SELECT t3.ID_1 AS ID_1,t3.ID_2 AS ID_2,t3.ME_1 AS ME_1,concat(t3.ME_2,'NEW') AS Me_2 FROM temporary_17 t3 WHERE (t3.ID_2='B')) t4;CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);"; 
		} else if (profileActive.equalsIgnoreCase("mySql")) {
			comandSqlResult = "CREATE TABLE temporary_17 AS (SELECT t1.ID_1 AS ID_1,t1.ID_2 AS ID_2,t1.ME_1 AS ME_1,t1.ME_2 AS ME_2 FROM DS_4 t1);CREATE INDEX temporary_17_ID_1_IDX ON temporary_17 (ID_1);CREATE INDEX temporary_17_ID_2_IDX ON temporary_17 (ID_2);CREATE TABLE DS_r AS (SELECT t2.ID_1 AS ID_1,t2.ID_2 AS ID_2,t2.ME_1 AS ME_1,concat(t2.ME_2,'NEW') AS Me_2 FROM temporary_17 t2 WHERE (t2.ID_2='B'));CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);"; 
		} else if (profileActive.equalsIgnoreCase("postgreSql")) {
			comandSqlResult = "CREATE TABLE temporary_17 AS (SELECT t1.ID_1 AS ID_1,t1.ID_2 AS ID_2,t1.ME_1 AS ME_1,t1.ME_2 AS ME_2 FROM DS_4 t1);CREATE INDEX temporary_17_ID_1_IDX ON temporary_17 (ID_1);CREATE INDEX temporary_17_ID_2_IDX ON temporary_17 (ID_2);CREATE TABLE DS_r AS (SELECT t2.ID_1 AS ID_1,t2.ID_2 AS ID_2,t2.ME_1 AS ME_1,concat(t2.ME_2,'NEW') AS Me_2 FROM temporary_17 t2 WHERE (t2.ID_2='B'));CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);"; 
		}
		
		String commandStatements = "DS_r := inner_join ( DS_4  filter Id_2 = \"B\" calc Me_2 := Me_2 || \"NEW\" keep Me_1, Me_2);";
		String result = translationUtilTest.translate(commandStatements);
		System.out.println("Print queryInnerJoinKeep: " +result);
		System.out.println("Print queryAttesaInnerJoinKeep: " +comandSqlResult);
		assertEquals("Test operator InnerJoinKeep", translationUtilTest.temporaryValueObfuscation(comandSqlResult), translationUtilTest.temporaryValueObfuscation(result));

	}

	@Test
	@Transactional
	public void leftJoin() throws Exception {
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
			vtlDatasetSecond.setName("DS_36");
			vtlDatasetSecond.setIsOnlyAScalar(false);
			vtlDatasetSecond.setTransitory(false);
			vtlDatasetSecond
					.addComponent(translationUtilTest.getVtlComponent("ID_1", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDatasetSecond
					.addComponent(translationUtilTest.getVtlComponent("ID_2", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDatasetSecond
					.addComponent(translationUtilTest.getVtlComponent("ME_1A", VtlType.STRING, VtlComponentRole.MEASURE));
			vtlDatasetSecond
					.addComponent(translationUtilTest.getVtlComponent("ME_2", VtlType.STRING, VtlComponentRole.MEASURE));
			datasetRepository.save(vtlDatasetSecond);
		}
		
		System.out.println("Il profilo attivo è " + environment.getActiveProfiles()[0]);

		String comandSqlResult = "";
		String profileActive = environment.getActiveProfiles()[0];
		if (profileActive.equalsIgnoreCase("oracleSql")) {
            comandSqlResult = "CREATE TABLE temporary_7 AS (SELECT d2.ID_1 AS ID_1,d2.ID_2 AS ID_2,t0.ME_1 AS ME_1,t0.ME_2 AS d1#ME_2,d2.ME_1A AS ME_1A,d2.ME_2 AS d2#ME_2 FROM DS_4 t0 LEFT OUTER JOIN DS_36 d2 ON (((t0.ID_1 = d2.ID_1) AND (t0.ID_2 = d2.ID_2))));CREATE INDEX temporary_7_ID_1_IDX ON temporary_7 (ID_1);CREATE INDEX temporary_7_ID_2_IDX ON temporary_7 (ID_2);CREATE TABLE DS_r AS (SELECT t1.ID_1 AS ID_1,t1.ID_2 AS ID_2,t1.ME_1 AS ME_1,t1.ME_1A AS ME_1A,t1.d2#ME_2 AS ME_2 FROM temporary_7 t1);CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);";
		} else if (profileActive.equalsIgnoreCase("sqlServer")) {
            comandSqlResult = "SELECT t1.* INTO temporary_61 FROM (SELECT d2.ID_1 AS ID_1,d2.ID_2 AS ID_2,t0.ME_1 AS ME_1,t0.ME_2 AS d1#ME_2,d2.ME_1A AS ME_1A,d2.ME_2 AS d2#ME_2 FROM DS_4 t0 LEFT OUTER JOIN DS_36 d2 ON (((t0.ID_1 = d2.ID_1) AND (t0.ID_2 = d2.ID_2)))) t1;CREATE INDEX temporary_61_ID_1_IDX ON temporary_61 (ID_1);CREATE INDEX temporary_61_ID_2_IDX ON temporary_61 (ID_2);SELECT t3.* INTO DS_r FROM (SELECT t2.ID_1 AS ID_1,t2.ID_2 AS ID_2,t2.ME_1 AS ME_1,t2.ME_1A AS ME_1A,t2.d2#ME_2 AS ME_2 FROM temporary_61 t2) t3;CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);";
		} else if (profileActive.equalsIgnoreCase("mySql")) {
            comandSqlResult = "CREATE TABLE temporary_61 AS (SELECT d2.ID_1 AS ID_1,d2.ID_2 AS ID_2,t0.ME_1 AS ME_1,t0.ME_2 AS d1#ME_2,d2.ME_1A AS ME_1A,d2.ME_2 AS d2#ME_2 FROM DS_4 t0 LEFT OUTER JOIN DS_36 d2 ON (((t0.ID_1 = d2.ID_1) AND (t0.ID_2 = d2.ID_2))));CREATE INDEX temporary_61_ID_1_IDX ON temporary_61 (ID_1);CREATE INDEX temporary_61_ID_2_IDX ON temporary_61 (ID_2);CREATE TABLE DS_r AS (SELECT t1.ID_1 AS ID_1,t1.ID_2 AS ID_2,t1.ME_1 AS ME_1,t1.ME_1A AS ME_1A,t1.d2#ME_2 AS ME_2 FROM temporary_61 t1);CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);";
		} else if (profileActive.equalsIgnoreCase("postgreSql")) {
            comandSqlResult = "CREATE TABLE temporary_61 AS (SELECT d2.ID_1 AS ID_1,d2.ID_2 AS ID_2,t0.ME_1 AS ME_1,t0.ME_2 AS d1#ME_2,d2.ME_1A AS ME_1A,d2.ME_2 AS d2#ME_2 FROM DS_4 t0 LEFT OUTER JOIN DS_36 d2 ON (((t0.ID_1 = d2.ID_1) AND (t0.ID_2 = d2.ID_2))));CREATE INDEX temporary_61_ID_1_IDX ON temporary_61 (ID_1);CREATE INDEX temporary_61_ID_2_IDX ON temporary_61 (ID_2);CREATE TABLE DS_r AS (SELECT t1.ID_1 AS ID_1,t1.ID_2 AS ID_2,t1.ME_1 AS ME_1,t1.ME_1A AS ME_1A,t1.d2#ME_2 AS ME_2 FROM temporary_61 t1);CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);";
		}//TODO Vedere query generata contiene # ?!
		
		String commandStatements = "DS_r  := left_join ( DS_4 as d1, DS_36 as d2 keep Me_1, d2#Me_2, Me_1A );";
		String result = translationUtilTest.translate(commandStatements);
		System.out.println("Print queryLeftJoin: " +result);
		System.out.println("Print queryAttesaLeftJoin: " +comandSqlResult);
		assertEquals("Test operator LeftJoin", translationUtilTest.temporaryValueObfuscation(comandSqlResult), translationUtilTest.temporaryValueObfuscation(result));

	}

	@Test
	@Transactional
	public void fullJoin() throws Exception {
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
			vtlDatasetSecond.setName("DS_36");
			vtlDatasetSecond.setIsOnlyAScalar(false);
			vtlDatasetSecond.setTransitory(false);
			vtlDatasetSecond
					.addComponent(translationUtilTest.getVtlComponent("ID_1", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDatasetSecond
					.addComponent(translationUtilTest.getVtlComponent("ID_2", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDatasetSecond
					.addComponent(translationUtilTest.getVtlComponent("ME_1A", VtlType.STRING, VtlComponentRole.MEASURE));
			vtlDatasetSecond
					.addComponent(translationUtilTest.getVtlComponent("ME_2", VtlType.STRING, VtlComponentRole.MEASURE));
			datasetRepository.save(vtlDatasetSecond);
		}
		
		System.out.println("Il profilo attivo è " + environment.getActiveProfiles()[0]);

		String comandSqlResult = "";
		String profileActive = environment.getActiveProfiles()[0];
		if (profileActive.equalsIgnoreCase("oracleSql")) {
            comandSqlResult = "CREATE TABLE temporary_7 AS (SELECT d2.ID_1 AS ID_1,d2.ID_2 AS ID_2,t0.ME_1 AS ME_1,t0.ME_2 AS d1#ME_2,d2.ME_1A AS ME_1A,d2.ME_2 AS d2#ME_2 FROM DS_4 t0 LEFT OUTER JOIN DS_36 d2 ON (((t0.ID_1 = d2.ID_1) AND (t0.ID_2 = d2.ID_2))) UNION SELECT d2.ID_1 AS ID_1,d2.ID_2 AS ID_2,t1.ME_1 AS ME_1,t1.ME_2 AS d1#ME_2,d2.ME_1A AS ME_1A,d2.ME_2 AS d2#ME_2 FROM DS_4 t1 RIGHT OUTER JOIN DS_36 d2 ON (((t1.ID_1 = d2.ID_1) AND (t1.ID_2 = d2.ID_2))));CREATE INDEX temporary_7_ID_1_IDX ON temporary_7 (ID_1);CREATE INDEX temporary_7_ID_2_IDX ON temporary_7 (ID_2);CREATE TABLE DS_r AS (SELECT t2.ID_1 AS ID_1,t2.ID_2 AS ID_2,t2.ME_1 AS ME_1,t2.ME_1A AS ME_1A,t2.d2#ME_2 AS ME_2 FROM temporary_7 t2);CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);";
		} else if (profileActive.equalsIgnoreCase("sqlServer")) {
            comandSqlResult = "SELECT t2.* INTO temporary_39 FROM (SELECT d2.ID_1 AS ID_1,d2.ID_2 AS ID_2,t0.ME_1 AS ME_1,t0.ME_2 AS d1#ME_2,d2.ME_1A AS ME_1A,d2.ME_2 AS d2#ME_2 FROM DS_4 t0 LEFT OUTER JOIN DS_36 d2 ON (((t0.ID_1 = d2.ID_1) AND (t0.ID_2 = d2.ID_2))) UNION SELECT t1.ID_1 AS ID_1,t1.ID_2 AS ID_2,t1.ME_1 AS ME_1,t1.ME_2 AS d1#ME_2,d2.ME_1A AS ME_1A,d2.ME_2 AS d2#ME_2 FROM DS_4 t1 RIGHT OUTER JOIN DS_36 d2 ON (((t1.ID_1 = d2.ID_1) AND (t1.ID_2 = d2.ID_2)))) t2;CREATE INDEX temporary_39_ID_1_IDX ON temporary_39 (ID_1);CREATE INDEX temporary_39_ID_2_IDX ON temporary_39 (ID_2);SELECT t4.* INTO DS_r FROM (SELECT t3.ID_1 AS ID_1,t3.ID_2 AS ID_2,t3.ME_1 AS ME_1,t3.ME_1A AS ME_1A,t3.d2#ME_2 AS ME_2 FROM temporary_39 t3) t4;CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);";
		} else if (profileActive.equalsIgnoreCase("mySql")) {
            comandSqlResult = "CREATE TABLE temporary_39 AS (SELECT d2.ID_1 AS ID_1,d2.ID_2 AS ID_2,t0.ME_1 AS ME_1,t0.ME_2 AS d1#ME_2,d2.ME_1A AS ME_1A,d2.ME_2 AS d2#ME_2 FROM DS_4 t0 LEFT OUTER JOIN DS_36 d2 ON (((t0.ID_1 = d2.ID_1) AND (t0.ID_2 = d2.ID_2))) UNION SELECT t1.ID_1 AS ID_1,t1.ID_2 AS ID_2,t1.ME_1 AS ME_1,t1.ME_2 AS d1#ME_2,d2.ME_1A AS ME_1A,d2.ME_2 AS d2#ME_2 FROM DS_4 t1 RIGHT OUTER JOIN DS_36 d2 ON (((t1.ID_1 = d2.ID_1) AND (t1.ID_2 = d2.ID_2))));CREATE INDEX temporary_39_ID_1_IDX ON temporary_39 (ID_1);CREATE INDEX temporary_39_ID_2_IDX ON temporary_39 (ID_2);CREATE TABLE DS_r AS (SELECT t2.ID_1 AS ID_1,t2.ID_2 AS ID_2,t2.ME_1 AS ME_1,t2.ME_1A AS ME_1A,t2.d2#ME_2 AS ME_2 FROM temporary_39 t2);CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);";
		} else if (profileActive.equalsIgnoreCase("postgreSql")) {
            comandSqlResult = "CREATE TABLE temporary_39 AS (SELECT d2.ID_1 AS ID_1,d2.ID_2 AS ID_2,t0.ME_1 AS ME_1,t0.ME_2 AS d1#ME_2,d2.ME_1A AS ME_1A,d2.ME_2 AS d2#ME_2 FROM DS_4 t0 LEFT OUTER JOIN DS_36 d2 ON (((t0.ID_1 = d2.ID_1) AND (t0.ID_2 = d2.ID_2))) UNION SELECT t1.ID_1 AS ID_1,t1.ID_2 AS ID_2,t1.ME_1 AS ME_1,t1.ME_2 AS d1#ME_2,d2.ME_1A AS ME_1A,d2.ME_2 AS d2#ME_2 FROM DS_4 t1 RIGHT OUTER JOIN DS_36 d2 ON (((t1.ID_1 = d2.ID_1) AND (t1.ID_2 = d2.ID_2))));CREATE INDEX temporary_39_ID_1_IDX ON temporary_39 (ID_1);CREATE INDEX temporary_39_ID_2_IDX ON temporary_39 (ID_2);CREATE TABLE DS_r AS (SELECT t2.ID_1 AS ID_1,t2.ID_2 AS ID_2,t2.ME_1 AS ME_1,t2.ME_1A AS ME_1A,t2.d2#ME_2 AS ME_2 FROM temporary_39 t2);CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);";
		}
		
		String commandStatements = "DS_r := full_join ( DS_4 as d1, DS_36 as d2 keep Me_1, d2#Me_2, Me_1A );";
		String result = translationUtilTest.translate(commandStatements);
		System.out.println("Print queryFullJoin: " +result);
		System.out.println("Print queryAttesaFullJoin: " +comandSqlResult);
		assertEquals("Test operator FullJoin", translationUtilTest.temporaryValueObfuscation(comandSqlResult), translationUtilTest.temporaryValueObfuscation(result));

	}

	@Test
	@Transactional
	public void crossJoin() throws Exception {
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
			vtlDatasetSecond.setName("DS_36");
			vtlDatasetSecond.setIsOnlyAScalar(false);
			vtlDatasetSecond.setTransitory(false);
			vtlDatasetSecond
					.addComponent(translationUtilTest.getVtlComponent("ID_1", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDatasetSecond
					.addComponent(translationUtilTest.getVtlComponent("ID_2", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDatasetSecond
					.addComponent(translationUtilTest.getVtlComponent("ME_1A", VtlType.STRING, VtlComponentRole.MEASURE));
			vtlDatasetSecond
					.addComponent(translationUtilTest.getVtlComponent("ME_2", VtlType.STRING, VtlComponentRole.MEASURE));
			datasetRepository.save(vtlDatasetSecond);
		}
		
		System.out.println("Il profilo attivo è " + environment.getActiveProfiles()[0]);

		String comandSqlResult = "";
		String profileActive = environment.getActiveProfiles()[0];
		if (profileActive.equalsIgnoreCase("oracleSql")) {
			comandSqlResult = "CREATE TABLE temporary_2750 AS (SELECT t0.ID_1 AS d1#ID_1,t0.ID_2 AS d1#ID_2,d2.ID_1 AS d2#ID_1,d2.ID_2 AS d2#ID_2,t0.ME_1 AS ME_1,t0.ME_2 AS d1#ME_2,d2.ME_1A AS ME_1A,d2.ME_2 AS d2#ME_2 FROM DS_4 t0 CROSS JOIN DS_36 d2);CREATE INDEX temporary_2750_d1#ID_1_IDX ON temporary_2750 (d1#ID_1);CREATE INDEX temporary_2750_d1#ID_2_IDX ON temporary_2750 (d1#ID_2);CREATE INDEX temporary_2750_d2#ID_1_IDX ON temporary_2750 (d2#ID_1);CREATE INDEX temporary_2750_d2#ID_2_IDX ON temporary_2750 (d2#ID_2);CREATE TABLE DS_r AS (SELECT t1.d1#ID_1 AS Id11,t1.d1#ID_2 AS Id12,t1.d2#ID_1 AS Id21,t1.d2#ID_2 AS Id22,t1.ME_1 AS ME_1,t1.d1#ME_2 AS Me12,t1.ME_1A AS ME_1A,t1.d2#ME_2 AS ME_2 FROM temporary_2750 t1);CREATE INDEX DS_r_Id11_IDX ON DS_r (Id11);CREATE INDEX DS_r_Id12_IDX ON DS_r (Id12);CREATE INDEX DS_r_Id21_IDX ON DS_r (Id21);CREATE INDEX DS_r_Id22_IDX ON DS_r (Id22);"; 
		} else if (profileActive.equalsIgnoreCase("sqlServer")) {
			comandSqlResult = "SELECT t1.* INTO temporary_7 FROM (SELECT t0.ID_1 AS d1#ID_1,t0.ID_2 AS d1#ID_2,d2.ID_1 AS d2#ID_1,d2.ID_2 AS d2#ID_2,t0.ME_1 AS ME_1,t0.ME_2 AS d1#ME_2,d2.ME_1A AS ME_1A,d2.ME_2 AS d2#ME_2 FROM DS_4 t0 CROSS JOIN DS_36 d2) t1;CREATE INDEX temporary_7_d1#ID_1_IDX ON temporary_7 (d1#ID_1);CREATE INDEX temporary_7_d1#ID_2_IDX ON temporary_7 (d1#ID_2);CREATE INDEX temporary_7_d2#ID_1_IDX ON temporary_7 (d2#ID_1);CREATE INDEX temporary_7_d2#ID_2_IDX ON temporary_7 (d2#ID_2);SELECT t3.* INTO DS_r FROM (SELECT t2.d1#ID_1 AS Id11,t2.d1#ID_2 AS Id12,t2.d2#ID_1 AS Id21,t2.d2#ID_2 AS Id22,t2.ME_1 AS ME_1,t2.d1#ME_2 AS Me12,t2.ME_1A AS ME_1A,t2.d2#ME_2 AS ME_2 FROM temporary_7 t2) t3;CREATE INDEX DS_r_Id11_IDX ON DS_r (Id11);CREATE INDEX DS_r_Id12_IDX ON DS_r (Id12);CREATE INDEX DS_r_Id21_IDX ON DS_r (Id21);CREATE INDEX DS_r_Id22_IDX ON DS_r (Id22);"; 
		} else if (profileActive.equalsIgnoreCase("mySql")) {
			comandSqlResult = "CREATE TABLE temporary_2750 AS (SELECT t0.ID_1 AS d1#ID_1,t0.ID_2 AS d1#ID_2,d2.ID_1 AS d2#ID_1,d2.ID_2 AS d2#ID_2,t0.ME_1 AS ME_1,t0.ME_2 AS d1#ME_2,d2.ME_1A AS ME_1A,d2.ME_2 AS d2#ME_2 FROM DS_4 t0 CROSS JOIN DS_36 d2);CREATE INDEX temporary_2750_d1#ID_1_IDX ON temporary_2750 (d1#ID_1);CREATE INDEX temporary_2750_d1#ID_2_IDX ON temporary_2750 (d1#ID_2);CREATE INDEX temporary_2750_d2#ID_1_IDX ON temporary_2750 (d2#ID_1);CREATE INDEX temporary_2750_d2#ID_2_IDX ON temporary_2750 (d2#ID_2);CREATE TABLE DS_r AS (SELECT t1.d1#ID_1 AS Id11,t1.d1#ID_2 AS Id12,t1.d2#ID_1 AS Id21,t1.d2#ID_2 AS Id22,t1.ME_1 AS ME_1,t1.d1#ME_2 AS Me12,t1.ME_1A AS ME_1A,t1.d2#ME_2 AS ME_2 FROM temporary_2750 t1);CREATE INDEX DS_r_Id11_IDX ON DS_r (Id11);CREATE INDEX DS_r_Id12_IDX ON DS_r (Id12);CREATE INDEX DS_r_Id21_IDX ON DS_r (Id21);CREATE INDEX DS_r_Id22_IDX ON DS_r (Id22);";
		} else if (profileActive.equalsIgnoreCase("postgreSql")) {
			comandSqlResult = "CREATE TABLE temporary_2750 AS (SELECT t0.ID_1 AS d1#ID_1,t0.ID_2 AS d1#ID_2,d2.ID_1 AS d2#ID_1,d2.ID_2 AS d2#ID_2,t0.ME_1 AS ME_1,t0.ME_2 AS d1#ME_2,d2.ME_1A AS ME_1A,d2.ME_2 AS d2#ME_2 FROM DS_4 t0 CROSS JOIN DS_36 d2);CREATE INDEX temporary_2750_d1#ID_1_IDX ON temporary_2750 (d1#ID_1);CREATE INDEX temporary_2750_d1#ID_2_IDX ON temporary_2750 (d1#ID_2);CREATE INDEX temporary_2750_d2#ID_1_IDX ON temporary_2750 (d2#ID_1);CREATE INDEX temporary_2750_d2#ID_2_IDX ON temporary_2750 (d2#ID_2);CREATE TABLE DS_r AS (SELECT t1.d1#ID_1 AS Id11,t1.d1#ID_2 AS Id12,t1.d2#ID_1 AS Id21,t1.d2#ID_2 AS Id22,t1.ME_1 AS ME_1,t1.d1#ME_2 AS Me12,t1.ME_1A AS ME_1A,t1.d2#ME_2 AS ME_2 FROM temporary_2750 t1);CREATE INDEX DS_r_Id11_IDX ON DS_r (Id11);CREATE INDEX DS_r_Id12_IDX ON DS_r (Id12);CREATE INDEX DS_r_Id21_IDX ON DS_r (Id21);CREATE INDEX DS_r_Id22_IDX ON DS_r (Id22);";
		}
		
		String commandStatements = "DS_r := cross_join (DS_4 as d1, DS_36 as d2 rename d1#Id_1 to Id11, d1#Id_2 to Id12, d2#Id_1 to Id21, d2#Id_2 to Id22, d1#Me_2 to Me12 );";
		String result = translationUtilTest.translate(commandStatements);
		System.out.println("Print queryCrossJoin: " +result);
		System.out.println("Print queryAttesaCrossJoin: " +comandSqlResult);
		assertEquals("Test operator CrossJoin", translationUtilTest.temporaryValueObfuscation(comandSqlResult), translationUtilTest.temporaryValueObfuscation(result));
	}
}
