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
public class VtlDataValidationOperatorsTranslationTest {

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
    public void CheckErrorImbalanceInvalid() throws Exception {
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
            
            vtlDataset = new VtlDataset();

            vtlDataset.setPersistent(true);
            vtlDataset.setName("DS_28");
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
                comandSqlResult = "CREATE TABLE temporary_2847 AS (SELECT t2.ID_1 AS ID_1,t2.ID_2 AS ID_2,t2.ID_3 AS ID_3,t2.ID_4 AS ID_4,(CASE WHEN (t2.ME_1>=t1.ME_1) THEN 'TRUE' ELSE 'FALSE' END) AS bool_var FROM DS_11 t2 INNER JOIN DS_28 t1 ON (((t2.ID_1 = t1.ID_1) AND (t2.ID_2 = t1.ID_2) AND (t2.ID_3 = t1.ID_3) AND (t2.ID_4 = t1.ID_4))));CREATE INDEX temporary_2847_ID_1_IDX ON temporary_2847 (ID_1);CREATE INDEX temporary_2847_ID_2_IDX ON temporary_2847 (ID_2);CREATE INDEX temporary_2847_ID_3_IDX ON temporary_2847 (ID_3);CREATE INDEX temporary_2847_ID_4_IDX ON temporary_2847 (ID_4);CREATE TABLE temporary_2849 AS (SELECT t5.ID_1 AS ID_1,t5.ID_2 AS ID_2,t5.ID_3 AS ID_3,t5.ID_4 AS ID_4,(t5.ME_1)-(t4.ME_1) AS ME_1 FROM DS_11 t5 INNER JOIN DS_28 t4 ON (((t5.ID_1 = t4.ID_1) AND (t5.ID_2 = t4.ID_2) AND (t5.ID_3 = t4.ID_3) AND (t5.ID_4 = t4.ID_4))));CREATE INDEX temporary_2849_ID_1_IDX ON temporary_2849 (ID_1);CREATE INDEX temporary_2849_ID_2_IDX ON temporary_2849 (ID_2);CREATE INDEX temporary_2849_ID_3_IDX ON temporary_2849 (ID_3);CREATE INDEX temporary_2849_ID_4_IDX ON temporary_2849 (ID_4);CREATE TABLE DS_r AS (SELECT t8.ID_1 AS ID_1,t8.ID_2 AS ID_2,t8.ID_3 AS ID_3,t8.ID_4 AS ID_4,t8.bool_var AS bool_var,t7.ME_1 AS imbalance,'ERR101' AS errorcode,101 AS errorlevel FROM temporary_2847 t8 INNER JOIN temporary_2849 t7 ON (((t8.ID_1 = t7.ID_1) AND (t8.ID_2 = t7.ID_2) AND (t8.ID_3 = t7.ID_3) AND (t8.ID_4 = t7.ID_4))) WHERE (t8.bool_var = 'FALSE'));CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);CREATE INDEX DS_r_ID_3_IDX ON DS_r (ID_3);CREATE INDEX DS_r_ID_4_IDX ON DS_r (ID_4);";
        } else if (profileActive.equalsIgnoreCase("sqlServer")) {
                comandSqlResult = "SELECT t3.* INTO temporary_5 FROM (SELECT t2.ID_1 AS ID_1,t2.ID_2 AS ID_2,t2.ID_3 AS ID_3,t2.ID_4 AS ID_4,(CASE WHEN (t2.ME_1>=t1.ME_1) THEN 'TRUE' ELSE 'FALSE' END) AS bool_var FROM DS_11 t2 INNER JOIN DS_28 t1 ON (((t2.ID_1 = t1.ID_1) AND (t2.ID_2 = t1.ID_2) AND (t2.ID_3 = t1.ID_3) AND (t2.ID_4 = t1.ID_4)))) t3;CREATE INDEX temporary_5_ID_1_IDX ON temporary_5 (ID_1);CREATE INDEX temporary_5_ID_2_IDX ON temporary_5 (ID_2);CREATE INDEX temporary_5_ID_3_IDX ON temporary_5 (ID_3);CREATE INDEX temporary_5_ID_4_IDX ON temporary_5 (ID_4);SELECT t7.* INTO temporary_7 FROM (SELECT t6.ID_1 AS ID_1,t6.ID_2 AS ID_2,t6.ID_3 AS ID_3,t6.ID_4 AS ID_4,(t6.ME_1)-(t5.ME_1) AS ME_1 FROM DS_11 t6 INNER JOIN DS_28 t5 ON (((t6.ID_1 = t5.ID_1) AND (t6.ID_2 = t5.ID_2) AND (t6.ID_3 = t5.ID_3) AND (t6.ID_4 = t5.ID_4)))) t7;CREATE INDEX temporary_7_ID_1_IDX ON temporary_7 (ID_1);CREATE INDEX temporary_7_ID_2_IDX ON temporary_7 (ID_2);CREATE INDEX temporary_7_ID_3_IDX ON temporary_7 (ID_3);CREATE INDEX temporary_7_ID_4_IDX ON temporary_7 (ID_4);SELECT t11.* INTO DS_r FROM (SELECT t10.ID_1 AS ID_1,t10.ID_2 AS ID_2,t10.ID_3 AS ID_3,t10.ID_4 AS ID_4,t10.bool_var AS bool_var,t9.ME_1 AS imbalance,'ERR101' AS errorcode,101 AS errorlevel FROM temporary_5 t10 INNER JOIN temporary_7 t9 ON (((t10.ID_1 = t9.ID_1) AND (t10.ID_2 = t9.ID_2) AND (t10.ID_3 = t9.ID_3) AND (t10.ID_4 = t9.ID_4))) WHERE (t10.bool_var = 'FALSE')) t11;CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);CREATE INDEX DS_r_ID_3_IDX ON DS_r (ID_3);CREATE INDEX DS_r_ID_4_IDX ON DS_r (ID_4);";
        } else if (profileActive.equalsIgnoreCase("mySql")) {
                comandSqlResult = "CREATE TABLE temporary_2561 AS (SELECT t2.ID_1 AS ID_1,t2.ID_2 AS ID_2,t2.ID_3 AS ID_3,t2.ID_4 AS ID_4,(CASE WHEN (t2.ME_1>=t1.ME_1) THEN 'TRUE' ELSE 'FALSE' END) AS bool_var FROM DS_11 t2 INNER JOIN DS_28 t1 ON (((t2.ID_1 = t1.ID_1) AND (t2.ID_2 = t1.ID_2) AND (t2.ID_3 = t1.ID_3) AND (t2.ID_4 = t1.ID_4))));CREATE INDEX temporary_2561_ID_1_IDX ON temporary_2561 (ID_1);CREATE INDEX temporary_2561_ID_2_IDX ON temporary_2561 (ID_2);CREATE INDEX temporary_2561_ID_3_IDX ON temporary_2561 (ID_3);CREATE INDEX temporary_2561_ID_4_IDX ON temporary_2561 (ID_4);CREATE TABLE temporary_2563 AS (SELECT t5.ID_1 AS ID_1,t5.ID_2 AS ID_2,t5.ID_3 AS ID_3,t5.ID_4 AS ID_4,(t5.ME_1)-(t4.ME_1) AS ME_1 FROM DS_11 t5 INNER JOIN DS_28 t4 ON (((t5.ID_1 = t4.ID_1) AND (t5.ID_2 = t4.ID_2) AND (t5.ID_3 = t4.ID_3) AND (t5.ID_4 = t4.ID_4))));CREATE INDEX temporary_2563_ID_1_IDX ON temporary_2563 (ID_1);CREATE INDEX temporary_2563_ID_2_IDX ON temporary_2563 (ID_2);CREATE INDEX temporary_2563_ID_3_IDX ON temporary_2563 (ID_3);CREATE INDEX temporary_2563_ID_4_IDX ON temporary_2563 (ID_4);CREATE TABLE DS_r AS (SELECT t8.ID_1 AS ID_1,t8.ID_2 AS ID_2,t8.ID_3 AS ID_3,t8.ID_4 AS ID_4,t8.bool_var AS bool_var,t7.ME_1 AS imbalance,'ERR101' AS errorcode,101 AS errorlevel FROM temporary_2561 t8 INNER JOIN temporary_2563 t7 ON (((t8.ID_1 = t7.ID_1) AND (t8.ID_2 = t7.ID_2) AND (t8.ID_3 = t7.ID_3) AND (t8.ID_4 = t7.ID_4))) WHERE (t8.bool_var = 'FALSE'));CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);CREATE INDEX DS_r_ID_3_IDX ON DS_r (ID_3);CREATE INDEX DS_r_ID_4_IDX ON DS_r (ID_4);";
        } else if (profileActive.equalsIgnoreCase("postgreSql")) {
                comandSqlResult = "CREATE TABLE temporary_2561 AS (SELECT t2.ID_1 AS ID_1,t2.ID_2 AS ID_2,t2.ID_3 AS ID_3,t2.ID_4 AS ID_4,(CASE WHEN (t2.ME_1>=t1.ME_1) THEN 'TRUE' ELSE 'FALSE' END) AS bool_var FROM DS_11 t2 INNER JOIN DS_28 t1 ON (((t2.ID_1 = t1.ID_1) AND (t2.ID_2 = t1.ID_2) AND (t2.ID_3 = t1.ID_3) AND (t2.ID_4 = t1.ID_4))));CREATE INDEX temporary_2561_ID_1_IDX ON temporary_2561 (ID_1);CREATE INDEX temporary_2561_ID_2_IDX ON temporary_2561 (ID_2);CREATE INDEX temporary_2561_ID_3_IDX ON temporary_2561 (ID_3);CREATE INDEX temporary_2561_ID_4_IDX ON temporary_2561 (ID_4);CREATE TABLE temporary_2563 AS (SELECT t5.ID_1 AS ID_1,t5.ID_2 AS ID_2,t5.ID_3 AS ID_3,t5.ID_4 AS ID_4,(t5.ME_1)-(t4.ME_1) AS ME_1 FROM DS_11 t5 INNER JOIN DS_28 t4 ON (((t5.ID_1 = t4.ID_1) AND (t5.ID_2 = t4.ID_2) AND (t5.ID_3 = t4.ID_3) AND (t5.ID_4 = t4.ID_4))));CREATE INDEX temporary_2563_ID_1_IDX ON temporary_2563 (ID_1);CREATE INDEX temporary_2563_ID_2_IDX ON temporary_2563 (ID_2);CREATE INDEX temporary_2563_ID_3_IDX ON temporary_2563 (ID_3);CREATE INDEX temporary_2563_ID_4_IDX ON temporary_2563 (ID_4);CREATE TABLE DS_r AS (SELECT t8.ID_1 AS ID_1,t8.ID_2 AS ID_2,t8.ID_3 AS ID_3,t8.ID_4 AS ID_4,t8.bool_var AS bool_var,t7.ME_1 AS imbalance,'ERR101' AS errorcode,101 AS errorlevel FROM temporary_2561 t8 INNER JOIN temporary_2563 t7 ON (((t8.ID_1 = t7.ID_1) AND (t8.ID_2 = t7.ID_2) AND (t8.ID_3 = t7.ID_3) AND (t8.ID_4 = t7.ID_4))) WHERE (t8.bool_var = 'FALSE'));CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);CREATE INDEX DS_r_ID_3_IDX ON DS_r (ID_3);CREATE INDEX DS_r_ID_4_IDX ON DS_r (ID_4);";
        }

        String commandStatements = "DS_r := check ( DS_11 >= DS_28 errorcode \"ERR101\" errorlevel 101 imbalance DS_11 - DS_28 invalid);"; 
        String result = translationUtilTest.translate(commandStatements);
        System.out.println("Print queryCheckErrorImbalanceInvalid: " +result);
        System.out.println("Print queryAttesaCheckErrorImbalanceInvalid: " +comandSqlResult);
        assertEquals("Test operator CheckErrorImbalanceInvalid", translationUtilTest.temporaryValueObfuscation(comandSqlResult), translationUtilTest.temporaryValueObfuscation(result));
    }
    
    @Test
    @Transactional
    public void CheckImbalanceAll() throws Exception {
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
            
            vtlDataset = new VtlDataset();

            vtlDataset.setPersistent(true);
            vtlDataset.setName("DS_28");
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
                comandSqlResult = "CREATE TABLE temporary_2859 AS (SELECT t2.ID_1 AS ID_1,t2.ID_2 AS ID_2,t2.ID_3 AS ID_3,t2.ID_4 AS ID_4,(CASE WHEN (t2.ME_1>=t1.ME_1) THEN 'TRUE' ELSE 'FALSE' END) AS bool_var FROM DS_11 t2 INNER JOIN DS_28 t1 ON (((t2.ID_1 = t1.ID_1) AND (t2.ID_2 = t1.ID_2) AND (t2.ID_3 = t1.ID_3) AND (t2.ID_4 = t1.ID_4))));CREATE INDEX temporary_2859_ID_1_IDX ON temporary_2859 (ID_1);CREATE INDEX temporary_2859_ID_2_IDX ON temporary_2859 (ID_2);CREATE INDEX temporary_2859_ID_3_IDX ON temporary_2859 (ID_3);CREATE INDEX temporary_2859_ID_4_IDX ON temporary_2859 (ID_4);CREATE TABLE temporary_2861 AS (SELECT t5.ID_1 AS ID_1,t5.ID_2 AS ID_2,t5.ID_3 AS ID_3,t5.ID_4 AS ID_4,(t5.ME_1)-(t4.ME_1) AS ME_1 FROM DS_11 t5 INNER JOIN DS_28 t4 ON (((t5.ID_1 = t4.ID_1) AND (t5.ID_2 = t4.ID_2) AND (t5.ID_3 = t4.ID_3) AND (t5.ID_4 = t4.ID_4))));CREATE INDEX temporary_2861_ID_1_IDX ON temporary_2861 (ID_1);CREATE INDEX temporary_2861_ID_2_IDX ON temporary_2861 (ID_2);CREATE INDEX temporary_2861_ID_3_IDX ON temporary_2861 (ID_3);CREATE INDEX temporary_2861_ID_4_IDX ON temporary_2861 (ID_4);CREATE TABLE DS_r AS (SELECT t8.ID_1 AS ID_1,t8.ID_2 AS ID_2,t8.ID_3 AS ID_3,t8.ID_4 AS ID_4,t8.bool_var AS bool_var,t7.ME_1 AS imbalance,CAST(NULL AS VARCHAR2(100)) AS errorcode,CAST(NULL AS VARCHAR2(100)) AS errorlevel FROM temporary_2859 t8 INNER JOIN temporary_2861 t7 ON (((t8.ID_1 = t7.ID_1) AND (t8.ID_2 = t7.ID_2) AND (t8.ID_3 = t7.ID_3) AND (t8.ID_4 = t7.ID_4))));CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);CREATE INDEX DS_r_ID_3_IDX ON DS_r (ID_3);CREATE INDEX DS_r_ID_4_IDX ON DS_r (ID_4);";
        } else if (profileActive.equalsIgnoreCase("sqlServer")) {
                comandSqlResult = "SELECT t3.* INTO temporary_5 FROM (SELECT t2.ID_1 AS ID_1,t2.ID_2 AS ID_2,t2.ID_3 AS ID_3,t2.ID_4 AS ID_4,(CASE WHEN (t2.ME_1>=t1.ME_1) THEN 'TRUE' ELSE 'FALSE' END) AS bool_var FROM DS_11 t2 INNER JOIN DS_28 t1 ON (((t2.ID_1 = t1.ID_1) AND (t2.ID_2 = t1.ID_2) AND (t2.ID_3 = t1.ID_3) AND (t2.ID_4 = t1.ID_4)))) t3;CREATE INDEX temporary_5_ID_1_IDX ON temporary_5 (ID_1);CREATE INDEX temporary_5_ID_2_IDX ON temporary_5 (ID_2);CREATE INDEX temporary_5_ID_3_IDX ON temporary_5 (ID_3);CREATE INDEX temporary_5_ID_4_IDX ON temporary_5 (ID_4);SELECT t7.* INTO temporary_7 FROM (SELECT t6.ID_1 AS ID_1,t6.ID_2 AS ID_2,t6.ID_3 AS ID_3,t6.ID_4 AS ID_4,(t6.ME_1)-(t5.ME_1) AS ME_1 FROM DS_11 t6 INNER JOIN DS_28 t5 ON (((t6.ID_1 = t5.ID_1) AND (t6.ID_2 = t5.ID_2) AND (t6.ID_3 = t5.ID_3) AND (t6.ID_4 = t5.ID_4)))) t7;CREATE INDEX temporary_7_ID_1_IDX ON temporary_7 (ID_1);CREATE INDEX temporary_7_ID_2_IDX ON temporary_7 (ID_2);CREATE INDEX temporary_7_ID_3_IDX ON temporary_7 (ID_3);CREATE INDEX temporary_7_ID_4_IDX ON temporary_7 (ID_4);SELECT t11.* INTO DS_r FROM (SELECT t10.ID_1 AS ID_1,t10.ID_2 AS ID_2,t10.ID_3 AS ID_3,t10.ID_4 AS ID_4,t10.bool_var AS bool_var,t9.ME_1 AS imbalance,CONVERT(varchar, NULL ) AS errorcode,CONVERT(varchar, NULL ) AS errorlevel FROM temporary_5 t10 INNER JOIN temporary_7 t9 ON (((t10.ID_1 = t9.ID_1) AND (t10.ID_2 = t9.ID_2) AND (t10.ID_3 = t9.ID_3) AND (t10.ID_4 = t9.ID_4)))) t11;CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);CREATE INDEX DS_r_ID_3_IDX ON DS_r (ID_3);CREATE INDEX DS_r_ID_4_IDX ON DS_r (ID_4);";
        } else if (profileActive.equalsIgnoreCase("mySql")) {
                comandSqlResult = "CREATE TABLE temporary_27 AS (SELECT t2.ID_1 AS ID_1,t2.ID_2 AS ID_2,t2.ID_3 AS ID_3,t2.ID_4 AS ID_4,(CASE WHEN (t2.ME_1>=t1.ME_1) THEN 'TRUE' ELSE 'FALSE' END) AS bool_var FROM DS_11 t2 INNER JOIN DS_28 t1 ON (((t2.ID_1 = t1.ID_1) AND (t2.ID_2 = t1.ID_2) AND (t2.ID_3 = t1.ID_3) AND (t2.ID_4 = t1.ID_4))));CREATE INDEX temporary_27_ID_1_IDX ON temporary_27 (ID_1);CREATE INDEX temporary_27_ID_2_IDX ON temporary_27 (ID_2);CREATE INDEX temporary_27_ID_3_IDX ON temporary_27 (ID_3);CREATE INDEX temporary_27_ID_4_IDX ON temporary_27 (ID_4);CREATE TABLE temporary_29 AS (SELECT t5.ID_1 AS ID_1,t5.ID_2 AS ID_2,t5.ID_3 AS ID_3,t5.ID_4 AS ID_4,(t5.ME_1)-(t4.ME_1) AS ME_1 FROM DS_11 t5 INNER JOIN DS_28 t4 ON (((t5.ID_1 = t4.ID_1) AND (t5.ID_2 = t4.ID_2) AND (t5.ID_3 = t4.ID_3) AND (t5.ID_4 = t4.ID_4))));CREATE INDEX temporary_29_ID_1_IDX ON temporary_29 (ID_1);CREATE INDEX temporary_29_ID_2_IDX ON temporary_29 (ID_2);CREATE INDEX temporary_29_ID_3_IDX ON temporary_29 (ID_3);CREATE INDEX temporary_29_ID_4_IDX ON temporary_29 (ID_4);CREATE TABLE DS_r AS (SELECT t8.ID_1 AS ID_1,t8.ID_2 AS ID_2,t8.ID_3 AS ID_3,t8.ID_4 AS ID_4,t8.bool_var AS bool_var,t7.ME_1 AS imbalance,CAST(NULL AS CHAR(100)) AS errorcode,CAST(NULL AS CHAR(100)) AS errorlevel FROM temporary_27 t8 INNER JOIN temporary_29 t7 ON (((t8.ID_1 = t7.ID_1) AND (t8.ID_2 = t7.ID_2) AND (t8.ID_3 = t7.ID_3) AND (t8.ID_4 = t7.ID_4))));CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);CREATE INDEX DS_r_ID_3_IDX ON DS_r (ID_3);CREATE INDEX DS_r_ID_4_IDX ON DS_r (ID_4);";
        } else if (profileActive.equalsIgnoreCase("postgreSql")) {
                comandSqlResult = "CREATE TABLE temporary_27 AS (SELECT t2.ID_1 AS ID_1,t2.ID_2 AS ID_2,t2.ID_3 AS ID_3,t2.ID_4 AS ID_4,(CASE WHEN (t2.ME_1>=t1.ME_1) THEN 'TRUE' ELSE 'FALSE' END) AS bool_var FROM DS_11 t2 INNER JOIN DS_28 t1 ON (((t2.ID_1 = t1.ID_1) AND (t2.ID_2 = t1.ID_2) AND (t2.ID_3 = t1.ID_3) AND (t2.ID_4 = t1.ID_4))));CREATE INDEX temporary_27_ID_1_IDX ON temporary_27 (ID_1);CREATE INDEX temporary_27_ID_2_IDX ON temporary_27 (ID_2);CREATE INDEX temporary_27_ID_3_IDX ON temporary_27 (ID_3);CREATE INDEX temporary_27_ID_4_IDX ON temporary_27 (ID_4);CREATE TABLE temporary_29 AS (SELECT t5.ID_1 AS ID_1,t5.ID_2 AS ID_2,t5.ID_3 AS ID_3,t5.ID_4 AS ID_4,(t5.ME_1)-(t4.ME_1) AS ME_1 FROM DS_11 t5 INNER JOIN DS_28 t4 ON (((t5.ID_1 = t4.ID_1) AND (t5.ID_2 = t4.ID_2) AND (t5.ID_3 = t4.ID_3) AND (t5.ID_4 = t4.ID_4))));CREATE INDEX temporary_29_ID_1_IDX ON temporary_29 (ID_1);CREATE INDEX temporary_29_ID_2_IDX ON temporary_29 (ID_2);CREATE INDEX temporary_29_ID_3_IDX ON temporary_29 (ID_3);CREATE INDEX temporary_29_ID_4_IDX ON temporary_29 (ID_4);CREATE TABLE DS_r AS (SELECT t8.ID_1 AS ID_1,t8.ID_2 AS ID_2,t8.ID_3 AS ID_3,t8.ID_4 AS ID_4,t8.bool_var AS bool_var,t7.ME_1 AS imbalance,CAST(NULL AS varchar(100)) AS errorcode,CAST(NULL AS varchar(100)) AS errorlevel FROM temporary_27 t8 INNER JOIN temporary_29 t7 ON (((t8.ID_1 = t7.ID_1) AND (t8.ID_2 = t7.ID_2) AND (t8.ID_3 = t7.ID_3) AND (t8.ID_4 = t7.ID_4))));CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);CREATE INDEX DS_r_ID_3_IDX ON DS_r (ID_3);CREATE INDEX DS_r_ID_4_IDX ON DS_r (ID_4);";
        }

        String commandStatements = "DS_r := check ( DS_11 >= DS_28 imbalance DS_11 - DS_28 all);"; 
        String result = translationUtilTest.translate(commandStatements);
        System.out.println("Print queryCheckImbalanceAll: " +result);
        System.out.println("Print queryAttesaCheckImbalanceAll: " +comandSqlResult);
        assertEquals("Test operator CheckImbalanceAll", translationUtilTest.temporaryValueObfuscation(comandSqlResult), translationUtilTest.temporaryValueObfuscation(result));
    }

    @Test
    @Transactional
    public void Check() throws Exception {
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
            
            vtlDataset = new VtlDataset();

            vtlDataset.setPersistent(true);
            vtlDataset.setName("DS_28");
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
                comandSqlResult = "CREATE TABLE temporary_2837 AS (SELECT t2.ID_1 AS ID_1,t2.ID_2 AS ID_2,t2.ID_3 AS ID_3,t2.ID_4 AS ID_4,(CASE WHEN (t2.ME_1>=t1.ME_1) THEN 'TRUE' ELSE 'FALSE' END) AS bool_var FROM DS_11 t2 INNER JOIN DS_28 t1 ON (((t2.ID_1 = t1.ID_1) AND (t2.ID_2 = t1.ID_2) AND (t2.ID_3 = t1.ID_3) AND (t2.ID_4 = t1.ID_4))));CREATE INDEX temporary_2837_ID_1_IDX ON temporary_2837 (ID_1);CREATE INDEX temporary_2837_ID_2_IDX ON temporary_2837 (ID_2);CREATE INDEX temporary_2837_ID_3_IDX ON temporary_2837 (ID_3);CREATE INDEX temporary_2837_ID_4_IDX ON temporary_2837 (ID_4);CREATE TABLE DS_r AS (SELECT t4.ID_1 AS ID_1,t4.ID_2 AS ID_2,t4.ID_3 AS ID_3,t4.ID_4 AS ID_4,t4.bool_var AS bool_var,CAST(NULL AS NUMBER) AS imbalance,CAST(NULL AS VARCHAR2(100)) AS errorcode,CAST(NULL AS VARCHAR2(100)) AS errorlevel FROM temporary_2837 t4);CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);CREATE INDEX DS_r_ID_3_IDX ON DS_r (ID_3);CREATE INDEX DS_r_ID_4_IDX ON DS_r (ID_4);";
        } else if (profileActive.equalsIgnoreCase("sqlServer")) {
                comandSqlResult = "SELECT t3.* INTO temporary_5 FROM (SELECT t2.ID_1 AS ID_1,t2.ID_2 AS ID_2,t2.ID_3 AS ID_3,t2.ID_4 AS ID_4,(CASE WHEN (t2.ME_1>=t1.ME_1) THEN 'TRUE' ELSE 'FALSE' END) AS bool_var FROM DS_11 t2 INNER JOIN DS_28 t1 ON (((t2.ID_1 = t1.ID_1) AND (t2.ID_2 = t1.ID_2) AND (t2.ID_3 = t1.ID_3) AND (t2.ID_4 = t1.ID_4)))) t3;CREATE INDEX temporary_5_ID_1_IDX ON temporary_5 (ID_1);CREATE INDEX temporary_5_ID_2_IDX ON temporary_5 (ID_2);CREATE INDEX temporary_5_ID_3_IDX ON temporary_5 (ID_3);CREATE INDEX temporary_5_ID_4_IDX ON temporary_5 (ID_4);SELECT t6.* INTO DS_r FROM (SELECT t5.ID_1 AS ID_1,t5.ID_2 AS ID_2,t5.ID_3 AS ID_3,t5.ID_4 AS ID_4,t5.bool_var AS bool_var,CONVERT(NUMERIC, NULL ) AS imbalance,CONVERT(varchar, NULL ) AS errorcode,CONVERT(varchar, NULL ) AS errorlevel FROM temporary_5 t5) t6;CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);CREATE INDEX DS_r_ID_3_IDX ON DS_r (ID_3);CREATE INDEX DS_r_ID_4_IDX ON DS_r (ID_4);";
        } else if (profileActive.equalsIgnoreCase("mySql")) {
                comandSqlResult = "CREATE TABLE temporary_5 AS (SELECT t2.ID_1 AS ID_1,t2.ID_2 AS ID_2,t2.ID_3 AS ID_3,t2.ID_4 AS ID_4,(CASE WHEN (t2.ME_1>=t1.ME_1) THEN 'TRUE' ELSE 'FALSE' END) AS bool_var FROM DS_11 t2 INNER JOIN DS_28 t1 ON (((t2.ID_1 = t1.ID_1) AND (t2.ID_2 = t1.ID_2) AND (t2.ID_3 = t1.ID_3) AND (t2.ID_4 = t1.ID_4))));CREATE INDEX temporary_5_ID_1_IDX ON temporary_5 (ID_1);CREATE INDEX temporary_5_ID_2_IDX ON temporary_5 (ID_2);CREATE INDEX temporary_5_ID_3_IDX ON temporary_5 (ID_3);CREATE INDEX temporary_5_ID_4_IDX ON temporary_5 (ID_4);CREATE TABLE DS_r AS (SELECT t4.ID_1 AS ID_1,t4.ID_2 AS ID_2,t4.ID_3 AS ID_3,t4.ID_4 AS ID_4,t4.bool_var AS bool_var,CAST(NULL AS SIGNED) AS imbalance,CAST(NULL AS CHAR(100)) AS errorcode,CAST(NULL AS CHAR(100)) AS errorlevel FROM temporary_5 t4);CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);CREATE INDEX DS_r_ID_3_IDX ON DS_r (ID_3);CREATE INDEX DS_r_ID_4_IDX ON DS_r (ID_4);";
        } else if (profileActive.equalsIgnoreCase("postgreSql")) {
                comandSqlResult = "CREATE TABLE temporary_5 AS (SELECT t2.ID_1 AS ID_1,t2.ID_2 AS ID_2,t2.ID_3 AS ID_3,t2.ID_4 AS ID_4,(CASE WHEN (t2.ME_1>=t1.ME_1) THEN 'TRUE' ELSE 'FALSE' END) AS bool_var FROM DS_11 t2 INNER JOIN DS_28 t1 ON (((t2.ID_1 = t1.ID_1) AND (t2.ID_2 = t1.ID_2) AND (t2.ID_3 = t1.ID_3) AND (t2.ID_4 = t1.ID_4))));CREATE INDEX temporary_5_ID_1_IDX ON temporary_5 (ID_1);CREATE INDEX temporary_5_ID_2_IDX ON temporary_5 (ID_2);CREATE INDEX temporary_5_ID_3_IDX ON temporary_5 (ID_3);CREATE INDEX temporary_5_ID_4_IDX ON temporary_5 (ID_4);CREATE TABLE DS_r AS (SELECT t4.ID_1 AS ID_1,t4.ID_2 AS ID_2,t4.ID_3 AS ID_3,t4.ID_4 AS ID_4,t4.bool_var AS bool_var,CAST(NULL AS numeric) AS imbalance,CAST(NULL AS varchar(100)) AS errorcode,CAST(NULL AS varchar(100)) AS errorlevel FROM temporary_5 t4);CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);CREATE INDEX DS_r_ID_3_IDX ON DS_r (ID_3);CREATE INDEX DS_r_ID_4_IDX ON DS_r (ID_4);";
        }

        String commandStatements = "DS_r := check ( DS_11 >= DS_28 );"; 
        String result = translationUtilTest.translate(commandStatements);
        System.out.println("Print queryCheck: " +result);
        System.out.println("Print queryAttesaCheck: " +comandSqlResult);
        assertEquals("Test operator Check", translationUtilTest.temporaryValueObfuscation(comandSqlResult), translationUtilTest.temporaryValueObfuscation(result));
    }    
}
