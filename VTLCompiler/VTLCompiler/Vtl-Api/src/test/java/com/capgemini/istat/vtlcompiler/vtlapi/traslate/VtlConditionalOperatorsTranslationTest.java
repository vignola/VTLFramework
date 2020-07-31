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
public class VtlConditionalOperatorsTranslationTest {
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
    public void nvl() throws Exception {
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
                    comandSqlResult = "CREATE TABLE DS_r AS (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,t0.ID_3 AS ID_3,t0.ID_4 AS ID_4,coalesce(t0.ME_1,0) AS ME_1 FROM DS_18 t0);CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);CREATE INDEX DS_r_ID_3_IDX ON DS_r (ID_3);CREATE INDEX DS_r_ID_4_IDX ON DS_r (ID_4);";
		} else if (profileActive.equalsIgnoreCase("sqlServer")) {
                    comandSqlResult = "SELECT t1.* INTO DS_r FROM (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,t0.ID_3 AS ID_3,t0.ID_4 AS ID_4,coalesce(t0.ME_1,0) AS ME_1 FROM DS_18 t0) t1;CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);"+"CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);"+"CREATE INDEX DS_r_ID_3_IDX ON DS_r (ID_3);"+"CREATE INDEX DS_r_ID_4_IDX ON DS_r (ID_4);"; 
		} else if (profileActive.equalsIgnoreCase("mySql")) {
                    comandSqlResult = "CREATE TABLE DS_r AS (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,t0.ID_3 AS ID_3,t0.ID_4 AS ID_4,coalesce(t0.ME_1,0) AS ME_1 FROM DS_18 t0);CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);CREATE INDEX DS_r_ID_3_IDX ON DS_r (ID_3);CREATE INDEX DS_r_ID_4_IDX ON DS_r (ID_4);"; 
		} else if (profileActive.equalsIgnoreCase("postgreSql")) {
                    comandSqlResult = "CREATE TABLE DS_r AS (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,t0.ID_3 AS ID_3,t0.ID_4 AS ID_4,coalesce(t0.ME_1,0) AS ME_1 FROM DS_18 t0);CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);CREATE INDEX DS_r_ID_3_IDX ON DS_r (ID_3);CREATE INDEX DS_r_ID_4_IDX ON DS_r (ID_4);"; 
		}
        
        String commandStatements = "DS_r := nvl ( DS_18, 0 );";
        String result = translationUtilTest.translate(commandStatements);
        System.out.println("Print queryNvl: " +result);
        System.out.println("Print queryAttesaNvl: " +comandSqlResult);
        assertEquals("Test operator Nvl", comandSqlResult, result);
    }

    @Test
    @Transactional
    public void ifThenElseScalar() throws Exception {
        dbTableUtilityService.resetSchema("t");
        
        System.out.println("Il profilo attivo è " + environment.getActiveProfiles()[0]);
		String comandSqlResult = "";
		String profileActive = environment.getActiveProfiles()[0];
		if (profileActive.equalsIgnoreCase("oracleSql")) {
                    comandSqlResult = "CREATE TABLE DS_r AS (SELECT (CASE WHEN (((CASE WHEN (3>5) THEN 'TRUE' ELSE 'FALSE' END)) = 'TRUE') THEN 2 ELSE 6 END) AS int_var FROM DUAL);"; 
		} else if (profileActive.equalsIgnoreCase("sqlServer")) {
                    comandSqlResult = "SELECT t0.* INTO DS_r FROM (SELECT (CASE WHEN (((CASE WHEN (3>5) THEN 'TRUE' ELSE 'FALSE' END)) = 'TRUE') THEN 2 ELSE 6 END) AS int_var) t0;";
		} else if (profileActive.equalsIgnoreCase("mySql")) {
                    comandSqlResult = "CREATE TABLE DS_r AS (SELECT (CASE WHEN (((CASE WHEN (3>5) THEN 'TRUE' ELSE 'FALSE' END)) = 'TRUE') THEN 2 ELSE 6 END) AS int_var FROM DUAL);"; 
		} else if (profileActive.equalsIgnoreCase("postgreSql")) {
                    comandSqlResult = "CREATE TABLE DS_r AS (SELECT (CASE WHEN (((CASE WHEN (3>5) THEN 'TRUE' ELSE 'FALSE' END)) = 'TRUE') THEN 2 ELSE 6 END) AS int_var);"; 
		}
        
        String commandStatements = "DS_r := if (3 > 5) then 2 else 6;";
        String result = translationUtilTest.translate(commandStatements);
        System.out.println("Print queryIfThenElseScalar: " +result);
        System.out.println("Print queryAttesaIfThenElseScalar: " +comandSqlResult);
        
        assertEquals("Test operator IfThenElseScalar", comandSqlResult, result);
    }

    @Test
    @Transactional
    public void ifThenElseComponent() throws Exception {
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
            vtlDatasetSecond.setName("DS_11");
            vtlDatasetSecond.setIsOnlyAScalar(false);
            vtlDatasetSecond.setTransitory(false);
            vtlDatasetSecond.addComponent(translationUtilTest.getVtlComponent("ID_1", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
            vtlDatasetSecond.addComponent(translationUtilTest.getVtlComponent("ID_2", VtlType.STRING, VtlComponentRole.IDENTIFIER));
            vtlDatasetSecond.addComponent(translationUtilTest.getVtlComponent("ID_3", VtlType.STRING, VtlComponentRole.IDENTIFIER));
            vtlDatasetSecond.addComponent(translationUtilTest.getVtlComponent("ID_4", VtlType.STRING, VtlComponentRole.IDENTIFIER));
            vtlDatasetSecond.addComponent(translationUtilTest.getVtlComponent("ME_1", VtlType.NUMBER, VtlComponentRole.MEASURE));
            datasetRepository.save(vtlDatasetSecond);
        }
        
        System.out.println("Il profilo attivo è " + environment.getActiveProfiles()[0]);
		String comandSqlResult = "";
		String profileActive = environment.getActiveProfiles()[0];
		if (profileActive.equalsIgnoreCase("oracleSql")) {
                    comandSqlResult = "CREATE TABLE DS_r AS (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,t0.ID_3 AS ID_3,t0.ID_4 AS ID_4,t0.ME_1 AS ME_1,(CASE WHEN (((CASE WHEN (t0.ID_4='F') THEN 'TRUE' ELSE 'FALSE' END)) = 'TRUE') THEN trim(t0.ID_4) ELSE 'Test' END) AS ME_X FROM DS_18 t0);CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);CREATE INDEX DS_r_ID_3_IDX ON DS_r (ID_3);CREATE INDEX DS_r_ID_4_IDX ON DS_r (ID_4);CREATE INDEX DS_r_ME_X_IDX ON DS_r (ME_X);"; 
		} else if (profileActive.equalsIgnoreCase("sqlServer")) {
                    comandSqlResult = "SELECT t1.* INTO DS_r FROM (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,t0.ID_3 AS ID_3,t0.ID_4 AS ID_4,t0.ME_1 AS ME_1,(CASE WHEN (((CASE WHEN (t0.ID_4='F') THEN 'TRUE' ELSE 'FALSE' END)) = 'TRUE') THEN trim(t0.ID_4) ELSE 'Test' END) AS ME_X FROM DS_18 t0) t1;CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);CREATE INDEX DS_r_ID_3_IDX ON DS_r (ID_3);CREATE INDEX DS_r_ID_4_IDX ON DS_r (ID_4);CREATE INDEX DS_r_ME_X_IDX ON DS_r (ME_X);";
		} else if (profileActive.equalsIgnoreCase("mySql")) {
                    comandSqlResult = "CREATE TABLE DS_r AS (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,t0.ID_3 AS ID_3,t0.ID_4 AS ID_4,t0.ME_1 AS ME_1,(CASE WHEN (((CASE WHEN (t0.ID_4='F') THEN 'TRUE' ELSE 'FALSE' END)) = 'TRUE') THEN trim(t0.ID_4) ELSE 'Test' END) AS ME_X FROM DS_18 t0);CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);CREATE INDEX DS_r_ID_3_IDX ON DS_r (ID_3);CREATE INDEX DS_r_ID_4_IDX ON DS_r (ID_4);CREATE INDEX DS_r_ME_X_IDX ON DS_r (ME_X);";  
		} else if (profileActive.equalsIgnoreCase("postgreSql")) {
                    comandSqlResult = "CREATE TABLE DS_r AS (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,t0.ID_3 AS ID_3,t0.ID_4 AS ID_4,t0.ME_1 AS ME_1,(CASE WHEN (((CASE WHEN (t0.ID_4='F') THEN 'TRUE' ELSE 'FALSE' END)) = 'TRUE') THEN trim(t0.ID_4) ELSE 'Test' END) AS ME_X FROM DS_18 t0);CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);CREATE INDEX DS_r_ID_3_IDX ON DS_r (ID_3);CREATE INDEX DS_r_ID_4_IDX ON DS_r (ID_4);CREATE INDEX DS_r_ME_X_IDX ON DS_r (ME_X);"; 
		}
        
        String commandStatements = "DS_r := DS_18 [ calc ME_X := if (ID_4 = \"F\") then trim(ID_4) else \"Test\"];";
        String result = translationUtilTest.translate(commandStatements);
        System.out.println("Print queryIfThenElseComponent: " +result);
        System.out.println("Print queryAttesaIfThenElseComponent: " +comandSqlResult);
        
        assertEquals("Test operator IfThenElseComponent", comandSqlResult, result);
    }    

    @Test
    @Transactional
    public void ifThenElseDataset() throws Exception {
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
            vtlDatasetSecond.setName("DS_11");
            vtlDatasetSecond.setIsOnlyAScalar(false);
            vtlDatasetSecond.setTransitory(false);
            vtlDatasetSecond.addComponent(translationUtilTest.getVtlComponent("ID_1", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
            vtlDatasetSecond.addComponent(translationUtilTest.getVtlComponent("ID_2", VtlType.STRING, VtlComponentRole.IDENTIFIER));
            vtlDatasetSecond.addComponent(translationUtilTest.getVtlComponent("ID_3", VtlType.STRING, VtlComponentRole.IDENTIFIER));
            vtlDatasetSecond.addComponent(translationUtilTest.getVtlComponent("ID_4", VtlType.STRING, VtlComponentRole.IDENTIFIER));
            vtlDatasetSecond.addComponent(translationUtilTest.getVtlComponent("ME_1", VtlType.NUMBER, VtlComponentRole.MEASURE));
            datasetRepository.save(vtlDatasetSecond);

            VtlDataset vtlDatasetThird = new VtlDataset();
            vtlDatasetThird.setPersistent(true);
            vtlDatasetThird.setName("DS_28");
            vtlDatasetThird.setIsOnlyAScalar(false);
            vtlDatasetThird.setTransitory(false);
            vtlDatasetThird.addComponent(translationUtilTest.getVtlComponent("ID_1", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
            vtlDatasetThird.addComponent(translationUtilTest.getVtlComponent("ID_2", VtlType.STRING, VtlComponentRole.IDENTIFIER));
            vtlDatasetThird.addComponent(translationUtilTest.getVtlComponent("ID_3", VtlType.STRING, VtlComponentRole.IDENTIFIER));
            vtlDatasetThird.addComponent(translationUtilTest.getVtlComponent("ID_4", VtlType.STRING, VtlComponentRole.IDENTIFIER));
            vtlDatasetThird.addComponent(translationUtilTest.getVtlComponent("ME_1", VtlType.NUMBER, VtlComponentRole.MEASURE));
            datasetRepository.save(vtlDatasetThird);
        }
        
        System.out.println("Il profilo attivo è " + environment.getActiveProfiles()[0]);
		String comandSqlResult = "";
		String profileActive = environment.getActiveProfiles()[0];
		if (profileActive.equalsIgnoreCase("oracleSql")) {
                    comandSqlResult = "CREATE TABLE temporary_10 AS (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,t0.ID_3 AS ID_3,t0.ID_4 AS ID_4,(CASE WHEN (t0.ID_4='F') THEN 'TRUE' ELSE 'FALSE' END) AS bool_var FROM DS_18 t0);CREATE INDEX temporary_10_ID_1_IDX ON temporary_10 (ID_1);CREATE INDEX temporary_10_ID_2_IDX ON temporary_10 (ID_2);CREATE INDEX temporary_10_ID_3_IDX ON temporary_10 (ID_3);CREATE INDEX temporary_10_ID_4_IDX ON temporary_10 (ID_4);CREATE TABLE DS_r AS (SELECT t4.ID_1 AS ID_1,t4.ID_2 AS ID_2,t4.ID_3 AS ID_3,t4.ID_4 AS ID_4,t2.ME_1 AS ME_1 FROM temporary_10 t4 INNER JOIN DS_11 t2 ON (((t4.ID_1 = t2.ID_1) AND (t4.ID_2 = t2.ID_2) AND (t4.ID_3 = t2.ID_3) AND (t4.ID_4 = t2.ID_4))) WHERE ((t4.bool_var = 'TRUE')) UNION SELECT t5.ID_1 AS ID_1,t5.ID_2 AS ID_2,t5.ID_3 AS ID_3,t5.ID_4 AS ID_4,t3.ME_1 AS ME_1 FROM temporary_10 t5 INNER JOIN DS_28 t3 ON (((t5.ID_1 = t3.ID_1) AND (t5.ID_2 = t3.ID_2) AND (t5.ID_3 = t3.ID_3) AND (t5.ID_4 = t3.ID_4))) WHERE ((t5.bool_var = 'FALSE')));CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);CREATE INDEX DS_r_ID_3_IDX ON DS_r (ID_3);CREATE INDEX DS_r_ID_4_IDX ON DS_r (ID_4);";
		} else if (profileActive.equalsIgnoreCase("sqlServer")) {
                    comandSqlResult = "SELECT t1.* INTO temporary_10 FROM (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,t0.ID_3 AS ID_3,t0.ID_4 AS ID_4,(CASE WHEN (t0.ID_4='F') THEN 'TRUE' ELSE 'FALSE' END) AS bool_var FROM DS_18 t0) t1;CREATE INDEX temporary_10_ID_1_IDX ON temporary_10 (ID_1);CREATE INDEX temporary_10_ID_2_IDX ON temporary_10 (ID_2);CREATE INDEX temporary_10_ID_3_IDX ON temporary_10 (ID_3);CREATE INDEX temporary_10_ID_4_IDX ON temporary_10 (ID_4);SELECT t7.* INTO DS_r FROM (SELECT t5.ID_1 AS ID_1,t5.ID_2 AS ID_2,t5.ID_3 AS ID_3,t5.ID_4 AS ID_4,t3.ME_1 AS ME_1 FROM temporary_10 t5 INNER JOIN DS_11 t3 ON (((t5.ID_1 = t3.ID_1) AND (t5.ID_2 = t3.ID_2) AND (t5.ID_3 = t3.ID_3) AND (t5.ID_4 = t3.ID_4))) WHERE ((t5.bool_var = 'TRUE')) UNION SELECT t6.ID_1 AS ID_1,t6.ID_2 AS ID_2,t6.ID_3 AS ID_3,t6.ID_4 AS ID_4,t4.ME_1 AS ME_1 FROM temporary_10 t6 INNER JOIN DS_28 t4 ON (((t6.ID_1 = t4.ID_1) AND (t6.ID_2 = t4.ID_2) AND (t6.ID_3 = t4.ID_3) AND (t6.ID_4 = t4.ID_4))) WHERE ((t6.bool_var = 'FALSE'))) t7;CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);CREATE INDEX DS_r_ID_3_IDX ON DS_r (ID_3);CREATE INDEX DS_r_ID_4_IDX ON DS_r (ID_4);"; 
		} else if (profileActive.equalsIgnoreCase("mySql")) {
                    comandSqlResult = "CREATE TABLE temporary_10 AS (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,t0.ID_3 AS ID_3,t0.ID_4 AS ID_4,(CASE WHEN (t0.ID_4='F') THEN 'TRUE' ELSE 'FALSE' END) AS bool_var FROM DS_18 t0);CREATE INDEX temporary_10_ID_1_IDX ON temporary_10 (ID_1);CREATE INDEX temporary_10_ID_2_IDX ON temporary_10 (ID_2);CREATE INDEX temporary_10_ID_3_IDX ON temporary_10 (ID_3);CREATE INDEX temporary_10_ID_4_IDX ON temporary_10 (ID_4);CREATE TABLE DS_r AS (SELECT t4.ID_1 AS ID_1,t4.ID_2 AS ID_2,t4.ID_3 AS ID_3,t4.ID_4 AS ID_4,t2.ME_1 AS ME_1 FROM temporary_10 t4 INNER JOIN DS_11 t2 ON (((t4.ID_1 = t2.ID_1) AND (t4.ID_2 = t2.ID_2) AND (t4.ID_3 = t2.ID_3) AND (t4.ID_4 = t2.ID_4))) WHERE ((t4.bool_var = 'TRUE')) UNION SELECT t5.ID_1 AS ID_1,t5.ID_2 AS ID_2,t5.ID_3 AS ID_3,t5.ID_4 AS ID_4,t3.ME_1 AS ME_1 FROM temporary_10 t5 INNER JOIN DS_28 t3 ON (((t5.ID_1 = t3.ID_1) AND (t5.ID_2 = t3.ID_2) AND (t5.ID_3 = t3.ID_3) AND (t5.ID_4 = t3.ID_4))) WHERE ((t5.bool_var = 'FALSE')));CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);CREATE INDEX DS_r_ID_3_IDX ON DS_r (ID_3);CREATE INDEX DS_r_ID_4_IDX ON DS_r (ID_4);"; 
		} else if (profileActive.equalsIgnoreCase("postgreSql")) {
                    comandSqlResult = "CREATE TABLE temporary_10 AS (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,t0.ID_3 AS ID_3,t0.ID_4 AS ID_4,(CASE WHEN (t0.ID_4='F') THEN 'TRUE' ELSE 'FALSE' END) AS bool_var FROM DS_18 t0);CREATE INDEX temporary_10_ID_1_IDX ON temporary_10 (ID_1);CREATE INDEX temporary_10_ID_2_IDX ON temporary_10 (ID_2);CREATE INDEX temporary_10_ID_3_IDX ON temporary_10 (ID_3);CREATE INDEX temporary_10_ID_4_IDX ON temporary_10 (ID_4);CREATE TABLE DS_r AS (SELECT t4.ID_1 AS ID_1,t4.ID_2 AS ID_2,t4.ID_3 AS ID_3,t4.ID_4 AS ID_4,t2.ME_1 AS ME_1 FROM temporary_10 t4 INNER JOIN DS_11 t2 ON (((t4.ID_1 = t2.ID_1) AND (t4.ID_2 = t2.ID_2) AND (t4.ID_3 = t2.ID_3) AND (t4.ID_4 = t2.ID_4))) WHERE ((t4.bool_var = 'TRUE')) UNION SELECT t5.ID_1 AS ID_1,t5.ID_2 AS ID_2,t5.ID_3 AS ID_3,t5.ID_4 AS ID_4,t3.ME_1 AS ME_1 FROM temporary_10 t5 INNER JOIN DS_28 t3 ON (((t5.ID_1 = t3.ID_1) AND (t5.ID_2 = t3.ID_2) AND (t5.ID_3 = t3.ID_3) AND (t5.ID_4 = t3.ID_4))) WHERE ((t5.bool_var = 'FALSE')));CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);CREATE INDEX DS_r_ID_3_IDX ON DS_r (ID_3);CREATE INDEX DS_r_ID_4_IDX ON DS_r (ID_4);"; 
		}
        
        String commandStatements = "DS_r := if ( DS_18#Id_4 = \"F\" ) then DS_11 else DS_28;";
        String result = translationUtilTest.translate(commandStatements);
        System.out.println("Print queryIfThenElseDataset: " +result);
        System.out.println("Print queryAttesaIfThenElseDataset: " +comandSqlResult);
        
        assertEquals("Test operator IfThenElseDataset", translationUtilTest.temporaryValueObfuscation(comandSqlResult), translationUtilTest.temporaryValueObfuscation(result));
    }

    @Test
    @Transactional
    public void ifThenElseDatasetJoin() throws Exception {
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
            vtlDatasetSecond.setName("DS_11");
            vtlDatasetSecond.setIsOnlyAScalar(false);
            vtlDatasetSecond.setTransitory(false);
            vtlDatasetSecond.addComponent(translationUtilTest.getVtlComponent("ID_1", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
            vtlDatasetSecond.addComponent(translationUtilTest.getVtlComponent("ID_2", VtlType.STRING, VtlComponentRole.IDENTIFIER));
            vtlDatasetSecond.addComponent(translationUtilTest.getVtlComponent("ID_3", VtlType.STRING, VtlComponentRole.IDENTIFIER));
            vtlDatasetSecond.addComponent(translationUtilTest.getVtlComponent("ID_4", VtlType.STRING, VtlComponentRole.IDENTIFIER));
            vtlDatasetSecond.addComponent(translationUtilTest.getVtlComponent("ME_1", VtlType.NUMBER, VtlComponentRole.MEASURE));
            datasetRepository.save(vtlDatasetSecond);

            VtlDataset vtlDatasetThird = new VtlDataset();
            vtlDatasetThird.setPersistent(true);
            vtlDatasetThird.setName("DS_28");
            vtlDatasetThird.setIsOnlyAScalar(false);
            vtlDatasetThird.setTransitory(false);
            vtlDatasetThird.addComponent(translationUtilTest.getVtlComponent("ID_1", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
            vtlDatasetThird.addComponent(translationUtilTest.getVtlComponent("ID_2", VtlType.STRING, VtlComponentRole.IDENTIFIER));
            vtlDatasetThird.addComponent(translationUtilTest.getVtlComponent("ID_3", VtlType.STRING, VtlComponentRole.IDENTIFIER));
            vtlDatasetThird.addComponent(translationUtilTest.getVtlComponent("ID_4", VtlType.STRING, VtlComponentRole.IDENTIFIER));
            vtlDatasetThird.addComponent(translationUtilTest.getVtlComponent("ME_1", VtlType.NUMBER, VtlComponentRole.MEASURE));
            datasetRepository.save(vtlDatasetThird);
        }
        
        System.out.println("Il profilo attivo è " + environment.getActiveProfiles()[0]);
		String comandSqlResult = "";
		String profileActive = environment.getActiveProfiles()[0];
		if (profileActive.equalsIgnoreCase("oracleSql")) {
                    comandSqlResult = "CREATE TABLE temporary_10 AS (SELECT t3.ID_1 AS ID_1,t3.ID_2 AS ID_2,t3.ID_3 AS ID_3,t3.ID_4 AS ID_4,(CASE WHEN (t3.ID_4='F') THEN 'TRUE' ELSE 'FALSE' END) AS bool_var FROM DS_18 t3);CREATE INDEX temporary_10_ID_1_IDX ON temporary_10 (ID_1);CREATE INDEX temporary_10_ID_2_IDX ON temporary_10 (ID_2);CREATE INDEX temporary_10_ID_3_IDX ON temporary_10 (ID_3);CREATE INDEX temporary_10_ID_4_IDX ON temporary_10 (ID_4);CREATE TABLE temporary_14 AS (SELECT t2.ID_1 AS ID_1,t2.ID_2 AS ID_2,t2.ID_3 AS ID_3,t2.ID_4 AS ID_4,(t2.ME_1)+(t1.ME_1) AS ME_1 FROM DS_11 t2 INNER JOIN DS_28 t1 ON (((t2.ID_1 = t1.ID_1) AND (t2.ID_2 = t1.ID_2) AND (t2.ID_3 = t1.ID_3) AND (t2.ID_4 = t1.ID_4))));CREATE INDEX temporary_14_ID_1_IDX ON temporary_14 (ID_1);CREATE INDEX temporary_14_ID_2_IDX ON temporary_14 (ID_2);CREATE INDEX temporary_14_ID_3_IDX ON temporary_14 (ID_3);CREATE INDEX temporary_14_ID_4_IDX ON temporary_14 (ID_4);CREATE TABLE DS_r AS (SELECT t7.ID_1 AS ID_1,t7.ID_2 AS ID_2,t7.ID_3 AS ID_3,t7.ID_4 AS ID_4,t5.ME_1 AS ME_1 FROM temporary_10 t7 INNER JOIN temporary_14 t5 ON (((t7.ID_1 = t5.ID_1) AND (t7.ID_2 = t5.ID_2) AND (t7.ID_3 = t5.ID_3) AND (t7.ID_4 = t5.ID_4))) WHERE ((t7.bool_var = 'TRUE')) UNION SELECT t8.ID_1 AS ID_1,t8.ID_2 AS ID_2,t8.ID_3 AS ID_3,t8.ID_4 AS ID_4,t6.ME_1 AS ME_1 FROM temporary_10 t8 INNER JOIN DS_28 t6 ON (((t8.ID_1 = t6.ID_1) AND (t8.ID_2 = t6.ID_2) AND (t8.ID_3 = t6.ID_3) AND (t8.ID_4 = t6.ID_4))) WHERE ((t8.bool_var = 'FALSE')));CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);CREATE INDEX DS_r_ID_3_IDX ON DS_r (ID_3);CREATE INDEX DS_r_ID_4_IDX ON DS_r (ID_4);"; 
		} else if (profileActive.equalsIgnoreCase("sqlServer")) {
                    comandSqlResult = "SELECT t5.* INTO temporary_10 FROM (SELECT t4.ID_1 AS ID_1,t4.ID_2 AS ID_2,t4.ID_3 AS ID_3,t4.ID_4 AS ID_4,(CASE WHEN (t4.ID_4='F') THEN 'TRUE' ELSE 'FALSE' END) AS bool_var FROM DS_18 t4) t5;CREATE INDEX temporary_10_ID_1_IDX ON temporary_10 (ID_1);CREATE INDEX temporary_10_ID_2_IDX ON temporary_10 (ID_2);CREATE INDEX temporary_10_ID_3_IDX ON temporary_10 (ID_3);CREATE INDEX temporary_10_ID_4_IDX ON temporary_10 (ID_4);SELECT t3.* INTO temporary_14 FROM (SELECT t2.ID_1 AS ID_1,t2.ID_2 AS ID_2,t2.ID_3 AS ID_3,t2.ID_4 AS ID_4,(t2.ME_1)+(t1.ME_1) AS ME_1 FROM DS_11 t2 INNER JOIN DS_28 t1 ON (((t2.ID_1 = t1.ID_1) AND (t2.ID_2 = t1.ID_2) AND (t2.ID_3 = t1.ID_3) AND (t2.ID_4 = t1.ID_4)))) t3;CREATE INDEX temporary_14_ID_1_IDX ON temporary_14 (ID_1);CREATE INDEX temporary_14_ID_2_IDX ON temporary_14 (ID_2);CREATE INDEX temporary_14_ID_3_IDX ON temporary_14 (ID_3);CREATE INDEX temporary_14_ID_4_IDX ON temporary_14 (ID_4);SELECT t11.* INTO DS_r FROM (SELECT t9.ID_1 AS ID_1,t9.ID_2 AS ID_2,t9.ID_3 AS ID_3,t9.ID_4 AS ID_4,t7.ME_1 AS ME_1 FROM temporary_10 t9 INNER JOIN temporary_14 t7 ON (((t9.ID_1 = t7.ID_1) AND (t9.ID_2 = t7.ID_2) AND (t9.ID_3 = t7.ID_3) AND (t9.ID_4 = t7.ID_4))) WHERE ((t9.bool_var = 'TRUE')) UNION SELECT t10.ID_1 AS ID_1,t10.ID_2 AS ID_2,t10.ID_3 AS ID_3,t10.ID_4 AS ID_4,t8.ME_1 AS ME_1 FROM temporary_10 t10 INNER JOIN DS_28 t8 ON (((t10.ID_1 = t8.ID_1) AND (t10.ID_2 = t8.ID_2) AND (t10.ID_3 = t8.ID_3) AND (t10.ID_4 = t8.ID_4))) WHERE ((t10.bool_var = 'FALSE'))) t11;CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);CREATE INDEX DS_r_ID_3_IDX ON DS_r (ID_3);CREATE INDEX DS_r_ID_4_IDX ON DS_r (ID_4);"; 
		} else if (profileActive.equalsIgnoreCase("mySql")) {
                    comandSqlResult = "CREATE TABLE temporary_10 AS (SELECT t3.ID_1 AS ID_1,t3.ID_2 AS ID_2,t3.ID_3 AS ID_3,t3.ID_4 AS ID_4,(CASE WHEN (t3.ID_4='F') THEN 'TRUE' ELSE 'FALSE' END) AS bool_var FROM DS_18 t3);CREATE INDEX temporary_10_ID_1_IDX ON temporary_10 (ID_1);CREATE INDEX temporary_10_ID_2_IDX ON temporary_10 (ID_2);CREATE INDEX temporary_10_ID_3_IDX ON temporary_10 (ID_3);CREATE INDEX temporary_10_ID_4_IDX ON temporary_10 (ID_4);CREATE TABLE temporary_14 AS (SELECT t2.ID_1 AS ID_1,t2.ID_2 AS ID_2,t2.ID_3 AS ID_3,t2.ID_4 AS ID_4,(t2.ME_1)+(t1.ME_1) AS ME_1 FROM DS_11 t2 INNER JOIN DS_28 t1 ON (((t2.ID_1 = t1.ID_1) AND (t2.ID_2 = t1.ID_2) AND (t2.ID_3 = t1.ID_3) AND (t2.ID_4 = t1.ID_4))));CREATE INDEX temporary_14_ID_1_IDX ON temporary_14 (ID_1);CREATE INDEX temporary_14_ID_2_IDX ON temporary_14 (ID_2);CREATE INDEX temporary_14_ID_3_IDX ON temporary_14 (ID_3);CREATE INDEX temporary_14_ID_4_IDX ON temporary_14 (ID_4);CREATE TABLE DS_r AS (SELECT t7.ID_1 AS ID_1,t7.ID_2 AS ID_2,t7.ID_3 AS ID_3,t7.ID_4 AS ID_4,t5.ME_1 AS ME_1 FROM temporary_10 t7 INNER JOIN temporary_14 t5 ON (((t7.ID_1 = t5.ID_1) AND (t7.ID_2 = t5.ID_2) AND (t7.ID_3 = t5.ID_3) AND (t7.ID_4 = t5.ID_4))) WHERE ((t7.bool_var = 'TRUE')) UNION SELECT t8.ID_1 AS ID_1,t8.ID_2 AS ID_2,t8.ID_3 AS ID_3,t8.ID_4 AS ID_4,t6.ME_1 AS ME_1 FROM temporary_10 t8 INNER JOIN DS_28 t6 ON (((t8.ID_1 = t6.ID_1) AND (t8.ID_2 = t6.ID_2) AND (t8.ID_3 = t6.ID_3) AND (t8.ID_4 = t6.ID_4))) WHERE ((t8.bool_var = 'FALSE')));CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);CREATE INDEX DS_r_ID_3_IDX ON DS_r (ID_3);CREATE INDEX DS_r_ID_4_IDX ON DS_r (ID_4);";
		} else if (profileActive.equalsIgnoreCase("postgreSql")) {
                    comandSqlResult = "CREATE TABLE temporary_10 AS (SELECT t3.ID_1 AS ID_1,t3.ID_2 AS ID_2,t3.ID_3 AS ID_3,t3.ID_4 AS ID_4,(CASE WHEN (t3.ID_4='F') THEN 'TRUE' ELSE 'FALSE' END) AS bool_var FROM DS_18 t3);CREATE INDEX temporary_10_ID_1_IDX ON temporary_10 (ID_1);CREATE INDEX temporary_10_ID_2_IDX ON temporary_10 (ID_2);CREATE INDEX temporary_10_ID_3_IDX ON temporary_10 (ID_3);CREATE INDEX temporary_10_ID_4_IDX ON temporary_10 (ID_4);CREATE TABLE temporary_14 AS (SELECT t2.ID_1 AS ID_1,t2.ID_2 AS ID_2,t2.ID_3 AS ID_3,t2.ID_4 AS ID_4,(t2.ME_1)+(t1.ME_1) AS ME_1 FROM DS_11 t2 INNER JOIN DS_28 t1 ON (((t2.ID_1 = t1.ID_1) AND (t2.ID_2 = t1.ID_2) AND (t2.ID_3 = t1.ID_3) AND (t2.ID_4 = t1.ID_4))));CREATE INDEX temporary_14_ID_1_IDX ON temporary_14 (ID_1);CREATE INDEX temporary_14_ID_2_IDX ON temporary_14 (ID_2);CREATE INDEX temporary_14_ID_3_IDX ON temporary_14 (ID_3);CREATE INDEX temporary_14_ID_4_IDX ON temporary_14 (ID_4);CREATE TABLE DS_r AS (SELECT t7.ID_1 AS ID_1,t7.ID_2 AS ID_2,t7.ID_3 AS ID_3,t7.ID_4 AS ID_4,t5.ME_1 AS ME_1 FROM temporary_10 t7 INNER JOIN temporary_14 t5 ON (((t7.ID_1 = t5.ID_1) AND (t7.ID_2 = t5.ID_2) AND (t7.ID_3 = t5.ID_3) AND (t7.ID_4 = t5.ID_4))) WHERE ((t7.bool_var = 'TRUE')) UNION SELECT t8.ID_1 AS ID_1,t8.ID_2 AS ID_2,t8.ID_3 AS ID_3,t8.ID_4 AS ID_4,t6.ME_1 AS ME_1 FROM temporary_10 t8 INNER JOIN DS_28 t6 ON (((t8.ID_1 = t6.ID_1) AND (t8.ID_2 = t6.ID_2) AND (t8.ID_3 = t6.ID_3) AND (t8.ID_4 = t6.ID_4))) WHERE ((t8.bool_var = 'FALSE')));CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);CREATE INDEX DS_r_ID_3_IDX ON DS_r (ID_3);CREATE INDEX DS_r_ID_4_IDX ON DS_r (ID_4);"; 
		}
        
        String commandStatements = "DS_r := if ( DS_18#Id_4 = \"F\" ) then DS_11 + DS_28 else DS_28;";
        String result = translationUtilTest.translate(commandStatements);
        System.out.println("Print queryIfThenElseDatasetJoin: " +result);
        System.out.println("Print queryAttesaIfThenElseDatasetJoin: " +comandSqlResult);
        
        assertEquals("Test operator IfThenElseDatasetJoin", translationUtilTest.temporaryValueObfuscation(comandSqlResult), translationUtilTest.temporaryValueObfuscation(result));
    }
    
    @Test
    @Transactional
    public void ifThenElseFilter() throws Exception {
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
                    comandSqlResult = "CREATE TABLE DS_r AS (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,t0.ME_1 AS ME_1,t0.ME_2 AS ME_2 FROM DS_1 t0 WHERE (t0.ID_1=(CASE WHEN (t0.ME_1>100) THEN t0.ME_1 ELSE t0.ME_2 END)));CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);"; 
		} else if (profileActive.equalsIgnoreCase("sqlServer")) {
                    comandSqlResult = "SELECT t1.* INTO DS_r FROM (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,t0.ME_1 AS ME_1,t0.ME_2 AS ME_2 FROM DS_1 t0 WHERE (t0.ID_1=(CASE WHEN (t0.ME_1>100) THEN t0.ME_1 ELSE t0.ME_2 END))) t1;CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);";
		} else if (profileActive.equalsIgnoreCase("mySql")) {
                    comandSqlResult = "CREATE TABLE DS_r AS (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,t0.ME_1 AS ME_1,t0.ME_2 AS ME_2 FROM DS_1 t0 WHERE (t0.ID_1=(CASE WHEN (t0.ME_1>100) THEN t0.ME_1 ELSE t0.ME_2 END)));CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);"; 
		} else if (profileActive.equalsIgnoreCase("postgreSql")) {
                    comandSqlResult = "CREATE TABLE DS_r AS (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,t0.ME_1 AS ME_1,t0.ME_2 AS ME_2 FROM DS_1 t0 WHERE (t0.ID_1=(CASE WHEN (t0.ME_1>100) THEN t0.ME_1 ELSE t0.ME_2 END)));CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);";  
		}
        
        String commandStatements = "DS_r := DS_1 [ filter ID_1 = if (ME_1 > 100) then ME_1 else ME_2 ];";
        String result = translationUtilTest.translate(commandStatements);
        System.out.println("Print queryIfThenElseFilter: " +result);
        System.out.println("Print queryAttesaIfThenElseFilter: " +comandSqlResult);
        
        assertEquals("Test operator IfThenElseFilter", comandSqlResult, result);
    }
}
