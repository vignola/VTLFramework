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
public class VtlAggregateAnalyticOperatorsTranslationTest {

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
	public void avgGroupBy() throws Exception {
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
		
        //Gestione profilo DB- inizio
        System.out.println("Il profilo attivo è " + environment.getActiveProfiles()[0]);
		String comandSqlResult = "";
		String profileActive = environment.getActiveProfiles()[0];
		if (profileActive.equalsIgnoreCase("oracleSql")) {
                    comandSqlResult = "CREATE TABLE DS_r AS (SELECT t0.ID_1 AS ID_1,avg(t0.ME_1) AS ME_1 FROM DS_29 t0 GROUP BY t0.ID_1);" + "CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);";
		} else if (profileActive.equalsIgnoreCase("sqlServer")) {
                    comandSqlResult = "SELECT t1.* INTO DS_r FROM (SELECT t0.ID_1 AS ID_1,avg(t0.ME_1) AS ME_1 FROM DS_29 t0 GROUP BY t0.ID_1) t1;CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);";
		} else if (profileActive.equalsIgnoreCase("mySql")) {
                    comandSqlResult = "CREATE TABLE DS_r AS (SELECT t0.ID_1 AS ID_1,avg(t0.ME_1) AS ME_1 FROM DS_29 t0 GROUP BY t0.ID_1);"+ "CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);";
		} else if (profileActive.equalsIgnoreCase("postgreSql")) {
                    comandSqlResult = "CREATE TABLE DS_r AS (SELECT t0.ID_1 AS ID_1,avg(t0.ME_1) AS ME_1 FROM DS_29 t0 GROUP BY t0.ID_1);"+ "CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);";
		}
        //Gestione profilo DB - fine

                String commandStatements = "DS_r := avg ( DS_29  group by  Id_1 );";		
		String result = translationUtilTest.translate(commandStatements);
		System.out.println("Print queryAvgGroupBy: " +result);
		System.out.println("Print queryAttesaAvgGroupBy: " +comandSqlResult);
		assertEquals("Test operator AvgGroupBy", comandSqlResult, result);
	}

	@Test
	@Transactional
	public void avgGroupExcept() throws Exception {
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
                    comandSqlResult = "CREATE TABLE DS_r AS (SELECT t0.ID_1 AS ID_1,avg(t0.ME_1) AS ME_1 FROM DS_29 t0 GROUP BY t0.ID_1);"+"CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);";
        } else if (profileActive.equalsIgnoreCase("sqlServer")) {
                    comandSqlResult = "SELECT t1.* INTO DS_r FROM (SELECT t0.ID_1 AS ID_1,avg(t0.ME_1) AS ME_1 FROM DS_29 t0 GROUP BY t0.ID_1) t1;"+"CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);";
        } else if (profileActive.equalsIgnoreCase("mySql")) {
                    comandSqlResult = "CREATE TABLE DS_r AS (SELECT t0.ID_1 AS ID_1,avg(t0.ME_1) AS ME_1 FROM DS_29 t0 GROUP BY t0.ID_1);"+"CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);";
        } else if (profileActive.equalsIgnoreCase("postgreSql")) {
                    comandSqlResult = "CREATE TABLE DS_r AS (SELECT t0.ID_1 AS ID_1,avg(t0.ME_1) AS ME_1 FROM DS_29 t0 GROUP BY t0.ID_1);"+"CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);";
        }
		
		String commandStatements = "DS_r := avg ( DS_29  group except   Id_2,  Id_3 );";
		String result = translationUtilTest.translate(commandStatements);
		System.out.println("Print queryAvgGroupExcept: " +result);
		System.out.println("Print queryAttesaAvgGroupExcept: " +comandSqlResult);
		assertEquals("Test operator AvgGroupExcept", comandSqlResult, result);
	}

	@Test
	@Transactional
	public void avgComponent() throws Exception {
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
                    comandSqlResult = "CREATE TABLE DS_r AS (SELECT t0.ID_1 AS ID_1,avg(t0.ME_1) AS ME_1 FROM DS_29 t0 GROUP BY t0.ID_1);CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);";
        } else if (profileActive.equalsIgnoreCase("sqlServer")) {
                    comandSqlResult = "SELECT t1.* INTO DS_r FROM (SELECT t0.ID_1 AS ID_1,avg(t0.ME_1) AS ME_1 FROM DS_29 t0 GROUP BY t0.ID_1) t1;CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);";
        } else if (profileActive.equalsIgnoreCase("mySql")) {
                    comandSqlResult = "CREATE TABLE DS_r AS (SELECT t0.ID_1 AS ID_1,avg(t0.ME_1) AS ME_1 FROM DS_29 t0 GROUP BY t0.ID_1);CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);";
        } else if (profileActive.equalsIgnoreCase("postgreSql")) {
                    comandSqlResult = "CREATE TABLE DS_r AS (SELECT t0.ID_1 AS ID_1,avg(t0.ME_1) AS ME_1 FROM DS_29 t0 GROUP BY t0.ID_1);CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);";
        }		
		
		String commandStatements = "DS_r := avg ( DS_29#Me_1  group by  Id_1 );"; 
		String result = translationUtilTest.translate(commandStatements);
		System.out.println("Print queryAvgComponent: " +result);
		System.out.println("Print queryAttesaAvgComponent: " +comandSqlResult);
		assertEquals("Test operator AvgComponent", comandSqlResult, result);
	}

	@Test
	@Transactional
	public void avgDs() throws Exception {
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
                    comandSqlResult = "CREATE TABLE DS_r AS (SELECT avg(t0.ME_1) AS ME_1 FROM DS_29 t0);"; 
        } else if (profileActive.equalsIgnoreCase("sqlServer")) {
                    comandSqlResult = "SELECT t1.* INTO DS_r FROM (SELECT avg(t0.ME_1) AS ME_1 FROM DS_29 t0) t1;"; 
        } else if (profileActive.equalsIgnoreCase("mySql")) {
                    comandSqlResult = "CREATE TABLE DS_r AS (SELECT avg(t0.ME_1) AS ME_1 FROM DS_29 t0);"; 
        } else if (profileActive.equalsIgnoreCase("postgreSql")) {
                    comandSqlResult = "CREATE TABLE DS_r AS (SELECT avg(t0.ME_1) AS ME_1 FROM DS_29 t0);"; 
        }
		
		String commandStatements = "DS_r := avg ( DS_29 );";
		String result = translationUtilTest.translate(commandStatements);
		System.out.println("Print queryAvgDs: " +result);
		System.out.println("Print queryAttesaAvgDs: " +comandSqlResult);
		assertEquals("Test operator AvgDs", comandSqlResult, result);
	}


	@Test
	@Transactional
	public void aggrGroupBy() throws Exception {
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
			comandSqlResult = "CREATE TABLE DS_r AS (SELECT t0.ID_1 AS ID_1,min(t0.ME_1) AS Me_3,max(t0.ME_1) AS Me_2 FROM DS_29 t0 GROUP BY t0.ID_1);" + "CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);";
        } else if (profileActive.equalsIgnoreCase("sqlServer")) {
			comandSqlResult = "SELECT t1.* INTO DS_r FROM (SELECT t0.ID_1 AS ID_1,min(t0.ME_1) AS Me_3,max(t0.ME_1) AS Me_2 FROM DS_29 t0 GROUP BY t0.ID_1) t1;" + "CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);";
        } else if (profileActive.equalsIgnoreCase("mySql")) {
			comandSqlResult = "CREATE TABLE DS_r AS (SELECT t0.ID_1 AS ID_1,min(t0.ME_1) AS Me_3,max(t0.ME_1) AS Me_2 FROM DS_29 t0 GROUP BY t0.ID_1);" + "CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);";
        } else if (profileActive.equalsIgnoreCase("postgreSql")) {
			comandSqlResult = "CREATE TABLE DS_r AS (SELECT t0.ID_1 AS ID_1,min(t0.ME_1) AS Me_3,max(t0.ME_1) AS Me_2 FROM DS_29 t0 GROUP BY t0.ID_1);" + "CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);";
        }
		
		String commandStatements = "DS_r := DS_29 [ aggr Me_2 := max ( Me_1 ) , Me_3 := min ( Me_1 ) group by Id_1 ];"; 
		String result = translationUtilTest.translate(commandStatements);
		System.out.println("Print queryAggrGroupBy: " +result);
		System.out.println("Print queryAttesaAggrGroupBy: " +comandSqlResult);
		assertEquals("Test operator AggrGroupBy", comandSqlResult, result);
	}

	@Test
	@Transactional
	public void sumGroupBy() throws Exception {
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
                    comandSqlResult = "CREATE TABLE DS_r AS (SELECT t0.ID_1 AS ID_1,t0.ID_3 AS ID_3,sum(t0.ME_1) AS ME_1 FROM DS_29 t0 GROUP BY t0.ID_1,t0.ID_3);"+"CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);"+"CREATE INDEX DS_r_ID_3_IDX ON DS_r (ID_3);"; 
        } else if (profileActive.equalsIgnoreCase("sqlServer")) {
                    comandSqlResult = "SELECT t1.* INTO DS_r FROM (SELECT t0.ID_1 AS ID_1,t0.ID_3 AS ID_3,sum(t0.ME_1) AS ME_1 FROM DS_29 t0 GROUP BY t0.ID_1,t0.ID_3) t1;"+"CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);"+"CREATE INDEX DS_r_ID_3_IDX ON DS_r (ID_3);";
        } else if (profileActive.equalsIgnoreCase("mySql")) {
                    comandSqlResult = "CREATE TABLE DS_r AS (SELECT t0.ID_1 AS ID_1,t0.ID_3 AS ID_3,sum(t0.ME_1) AS ME_1 FROM DS_29 t0 GROUP BY t0.ID_1,t0.ID_3);"+"CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);"+"CREATE INDEX DS_r_ID_3_IDX ON DS_r (ID_3);";
        } else if (profileActive.equalsIgnoreCase("postgreSql")) {
                    comandSqlResult = "CREATE TABLE DS_r AS (SELECT t0.ID_1 AS ID_1,t0.ID_3 AS ID_3,sum(t0.ME_1) AS ME_1 FROM DS_29 t0 GROUP BY t0.ID_1,t0.ID_3);"+"CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);"+"CREATE INDEX DS_r_ID_3_IDX ON DS_r (ID_3);"; 
        }
		
		String commandStatements = "DS_r := sum ( DS_29  group by  Id_1, Id_3 );";
		String result = translationUtilTest.translate(commandStatements);
		System.out.println("Print querySumGroupBy: " +result);
		System.out.println("Print queryAttesaSumGroupBy: " +comandSqlResult);
		assertEquals("Test operator SumGroupBy", comandSqlResult, result);

	}

	@Test
	@Transactional
	public void sumOrderBy() throws Exception {
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
                    comandSqlResult = "CREATE TABLE DS_r AS (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,t0.ID_3 AS ID_3,sum(t0.ME_1) OVER (ORDER BY t0.ID_1 ASC,t0.ID_2 ASC,t0.ID_3 ASC ROWS BETWEEN 1 PRECEDING AND 1 FOLLOWING) AS ME_1 FROM DS_30 t0);"+"CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);"+"CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);"+"CREATE INDEX DS_r_ID_3_IDX ON DS_r (ID_3);"; 
        } else if (profileActive.equalsIgnoreCase("sqlServer")) {
                    comandSqlResult = "SELECT t1.* INTO DS_r FROM (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,t0.ID_3 AS ID_3,sum(t0.ME_1) OVER (ORDER BY t0.ID_1 ASC,t0.ID_2 ASC,t0.ID_3 ASC ROWS BETWEEN 1 PRECEDING AND 1 FOLLOWING) AS ME_1 FROM DS_30 t0) t1;"+"CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);"+"CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);"+"CREATE INDEX DS_r_ID_3_IDX ON DS_r (ID_3);";
        } else if (profileActive.equalsIgnoreCase("mySql")) {
                    comandSqlResult = "CREATE TABLE DS_r AS (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,t0.ID_3 AS ID_3,sum(t0.ME_1) OVER (ORDER BY t0.ID_1 ASC,t0.ID_2 ASC,t0.ID_3 ASC ROWS BETWEEN 1 PRECEDING AND 1 FOLLOWING) AS ME_1 FROM DS_30 t0);"+"CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);"+"CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);"+"CREATE INDEX DS_r_ID_3_IDX ON DS_r (ID_3);";
        } else if (profileActive.equalsIgnoreCase("postgreSql")) {
                    comandSqlResult = "CREATE TABLE DS_r AS (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,t0.ID_3 AS ID_3,sum(t0.ME_1) OVER (ORDER BY t0.ID_1 ASC,t0.ID_2 ASC,t0.ID_3 ASC ROWS BETWEEN 1 PRECEDING AND 1 FOLLOWING) AS ME_1 FROM DS_30 t0);"+"CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);"+"CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);"+"CREATE INDEX DS_r_ID_3_IDX ON DS_r (ID_3);"; 
        }		
		
		String commandStatements = "DS_r := sum ( DS_30 over ( order by  Id_1, Id_2, Id_3  data points between 1 preceding and 1 following ) );"; 
		String result = translationUtilTest.translate(commandStatements);
		System.out.println("Print querySumOrderBy: " +result);
		System.out.println("Print queryAttesaSumOrderBy: " +comandSqlResult);
		assertEquals("Test operator SumOrderBy", comandSqlResult, result);

	}

	@Test
	@Transactional
	public void sumHavingCount() throws Exception {
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
                    comandSqlResult = "CREATE TABLE DS_r AS (SELECT t0.ID_1 AS ID_1,sum(t0.ME_1) AS ME_1 FROM DS_30 t0 GROUP BY t0.ID_1 HAVING (COUNT(*)>2));"+"CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);"; 
        } else if (profileActive.equalsIgnoreCase("sqlServer")) {
                    comandSqlResult = "SELECT t1.* INTO DS_r FROM (SELECT t0.ID_1 AS ID_1,sum(t0.ME_1) AS ME_1 FROM DS_30 t0 GROUP BY t0.ID_1 HAVING (COUNT(*)>2)) t1;"+"CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);";
        } else if (profileActive.equalsIgnoreCase("mySql")) {
                    comandSqlResult = "CREATE TABLE DS_r AS (SELECT t0.ID_1 AS ID_1,sum(t0.ME_1) AS ME_1 FROM DS_30 t0 GROUP BY t0.ID_1 HAVING (COUNT(*)>2));"+"CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);";
        } else if (profileActive.equalsIgnoreCase("postgreSql")) {
                    comandSqlResult = "CREATE TABLE DS_r AS (SELECT t0.ID_1 AS ID_1,sum(t0.ME_1) AS ME_1 FROM DS_30 t0 GROUP BY t0.ID_1 HAVING (COUNT(*)>2));"+"CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);"; 
        }
		
		String commandStatements = "DS_r := sum ( DS_30 group by  Id_1  having count() > 2 );";
		String result = translationUtilTest.translate(commandStatements);
		System.out.println("Print querySumHavingCount: " +result);
		System.out.println("Print queryAttesaSumHavingCount: " +comandSqlResult);
		assertEquals("Test operator SumHavingCount", comandSqlResult, result);

	}

	@Test
	@Transactional
	public void count() throws Exception {
		dbTableUtilityService.resetSchema("t");
		VtlDataset vtlDatasetFind = datasetRepository.findByNameIgnoreCase("DS_1");
		if (vtlDatasetFind == null) {
			VtlDataset vtlDataset = new VtlDataset();

			vtlDataset.setPersistent(true);
			vtlDataset.setName("DS_33");
			vtlDataset.setIsOnlyAScalar(false);
			vtlDataset.setTransitory(false);
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ID_1", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ID_2", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ID_3", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ME_1", VtlType.STRING, VtlComponentRole.MEASURE));
			datasetRepository.save(vtlDataset);
		}
		
        System.out.println("Il profilo attivo è " + environment.getActiveProfiles()[0]);
        String comandSqlResult = "";
        String profileActive = environment.getActiveProfiles()[0];
        if (profileActive.equalsIgnoreCase("oracleSql")) {
                    comandSqlResult = "CREATE TABLE DS_r AS (SELECT t0.ID_1 AS ID_1,count(t0.ME_1) AS int_var FROM DS_33 t0 GROUP BY t0.ID_1);"+"CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);"; 
        } else if (profileActive.equalsIgnoreCase("sqlServer")) {
                    comandSqlResult = "SELECT t1.* INTO DS_r FROM (SELECT t0.ID_1 AS ID_1,count(t0.ME_1) AS int_var FROM DS_33 t0 GROUP BY t0.ID_1) t1;"+"CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);";
        } else if (profileActive.equalsIgnoreCase("mySql")) {
                    comandSqlResult = "CREATE TABLE DS_r AS (SELECT t0.ID_1 AS ID_1,count(t0.ME_1) AS int_var FROM DS_33 t0 GROUP BY t0.ID_1);"+"CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);";
        } else if (profileActive.equalsIgnoreCase("postgreSql")) {
                    comandSqlResult = "CREATE TABLE DS_r AS (SELECT t0.ID_1 AS ID_1,count(t0.ME_1) AS int_var FROM DS_33 t0 GROUP BY t0.ID_1);"+"CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);"; 
        }		
		
		String commandStatements = "DS_r := count ( DS_33 group by  Id_1 );";
		String result = translationUtilTest.translate(commandStatements);
		System.out.println("Print queryCount: " +result);
		System.out.println("Print queryAttesaCount: " +comandSqlResult);
		assertEquals("Test operator Count", comandSqlResult, result);
	}

	@Test
	@Transactional
	public void min() throws Exception {
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
                    comandSqlResult = "CREATE TABLE DS_r AS (SELECT t0.ID_1 AS ID_1,min(t0.ME_1) AS ME_1 FROM DS_30 t0 GROUP BY t0.ID_1);"+"CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);"; 
        } else if (profileActive.equalsIgnoreCase("sqlServer")) {
                    comandSqlResult = "SELECT t1.* INTO DS_r FROM (SELECT t0.ID_1 AS ID_1,min(t0.ME_1) AS ME_1 FROM DS_30 t0 GROUP BY t0.ID_1) t1;"+"CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);";
        } else if (profileActive.equalsIgnoreCase("mySql")) {
                    comandSqlResult = "CREATE TABLE DS_r AS (SELECT t0.ID_1 AS ID_1,min(t0.ME_1) AS ME_1 FROM DS_30 t0 GROUP BY t0.ID_1);"+"CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);";
        } else if (profileActive.equalsIgnoreCase("postgreSql")) {
                    comandSqlResult = "CREATE TABLE DS_r AS (SELECT t0.ID_1 AS ID_1,min(t0.ME_1) AS ME_1 FROM DS_30 t0 GROUP BY t0.ID_1);"+"CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);"; 
        }		
		
		String commandStatements = "DS_r := min ( DS_30 group by  Id_1 );";
		String result = translationUtilTest.translate(commandStatements);
		System.out.println("Print queryMin: " +result);
		System.out.println("Print queryAttesaMin: " +comandSqlResult);
		assertEquals("Test operator Min", comandSqlResult, result);
	}

	@Test
	@Transactional
	public void max() throws Exception {
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
                    comandSqlResult = "CREATE TABLE DS_r AS (SELECT t0.ID_1 AS ID_1,max(t0.ME_1) AS ME_1 FROM DS_30 t0 GROUP BY t0.ID_1);"+"CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);"; 
        } else if (profileActive.equalsIgnoreCase("sqlServer")) {
                    comandSqlResult = "SELECT t1.* INTO DS_r FROM (SELECT t0.ID_1 AS ID_1,max(t0.ME_1) AS ME_1 FROM DS_30 t0 GROUP BY t0.ID_1) t1;"+"CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);";
        } else if (profileActive.equalsIgnoreCase("mySql")) {
                    comandSqlResult = "CREATE TABLE DS_r AS (SELECT t0.ID_1 AS ID_1,max(t0.ME_1) AS ME_1 FROM DS_30 t0 GROUP BY t0.ID_1);"+"CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);";
        } else if (profileActive.equalsIgnoreCase("postgreSql")) {
                    comandSqlResult = "CREATE TABLE DS_r AS (SELECT t0.ID_1 AS ID_1,max(t0.ME_1) AS ME_1 FROM DS_30 t0 GROUP BY t0.ID_1);"+"CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);"; 
        }
		
		String commandStatements = "DS_r := max ( DS_30 group by  Id_1 );";
		String result = translationUtilTest.translate(commandStatements);
		System.out.println("Print queryMax: " +result);
		System.out.println("Print queryAttesaMax: " +comandSqlResult);
		assertEquals("Test operator Max", comandSqlResult, result);
	}

	@Test
	@Transactional
	public void median() throws Exception {
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
                    comandSqlResult = "CREATE TABLE DS_r AS (SELECT t0.ID_1 AS ID_1,median(t0.ME_1) AS ME_1 FROM DS_30 t0 GROUP BY t0.ID_1);"+"CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);"; 
        } else if (profileActive.equalsIgnoreCase("sqlServer")) {
            comandSqlResult = "SELECT t4.* INTO DS_r FROM (SELECT t3.ID_1 AS ID_1,t3.ME_1 AS ME_1 FROM ( SELECT t1.ID_1 AS ID_1,AVG(t1.ME_1) AS ME_1 FROM ( SELECT t0.ID_1 AS ID_1,t0.ME_1 AS ME_1,ROW_NUMBER() OVER (PARTITION BY t0.ID_1 ORDER BY t0.ME_1 ASC) AS Mrank,COUNT(*) OVER (PARTITION BY t0.ID_1) AS mCount FROM DS_30 t0 )  t1 WHERE (t1.Mrank IN (t1.MCount / 2+1 , floor((t1.MCount + 1) / 2))) GROUP BY t1.ID_1 )  t3) t4;CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);";
        } else if (profileActive.equalsIgnoreCase("mySql")) {
            comandSqlResult = "CREATE TABLE DS_r AS (SELECT t3.ID_1 AS ID_1,t3.ME_1 AS ME_1 FROM ( SELECT t1.ID_1 AS ID_1,AVG(t1.ME_1) AS ME_1 FROM ( SELECT t0.ID_1 AS ID_1,t0.ME_1 AS ME_1,ROW_NUMBER() OVER (PARTITION BY t0.ID_1 ORDER BY t0.ME_1 ASC) AS Mrank,COUNT(*) OVER (PARTITION BY t0.ID_1) AS mCount FROM DS_30 t0 )  t1 WHERE (t1.Mrank IN (t1.MCount / 2+1 , floor((t1.MCount + 1) / 2))) GROUP BY t1.ID_1 )  t3);CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);";
        } else if (profileActive.equalsIgnoreCase("postgreSql")) {
                    comandSqlResult = "CREATE TABLE DS_r AS (SELECT t0.ID_1 AS ID_1,median(t0.ME_1) AS ME_1 FROM DS_30 t0 GROUP BY t0.ID_1);"+"CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);"; 
        }
		
		String commandStatements = "DS_r := median ( DS_30  group by  Id_1 );";
		String result = translationUtilTest.translate(commandStatements);
		System.out.println("Print queryMedian: " +result);
		System.out.println("Print queryAttesaMedian: " +comandSqlResult);
		assertEquals("Test operator Median", comandSqlResult, result);
	}

	@Test
	@Transactional
	public void stddevPop() throws Exception {
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
                    comandSqlResult = "CREATE TABLE DS_r AS (SELECT t0.ID_1 AS ID_1,stddev_pop(t0.ME_1) AS ME_1 FROM DS_30 t0 GROUP BY t0.ID_1);"+"CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);"; 
        } else if (profileActive.equalsIgnoreCase("sqlServer")) {
                    comandSqlResult = "SELECT t1.* INTO DS_r FROM (SELECT t0.ID_1 AS ID_1,stdevp(t0.ME_1) AS ME_1 FROM DS_30 t0 GROUP BY t0.ID_1) t1;"+"CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);";
        } else if (profileActive.equalsIgnoreCase("mySql")) {
                    comandSqlResult = "CREATE TABLE DS_r AS (SELECT t0.ID_1 AS ID_1,stddev_pop(t0.ME_1) AS ME_1 FROM DS_30 t0 GROUP BY t0.ID_1);"+"CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);";
        } else if (profileActive.equalsIgnoreCase("postgreSql")) {
                    comandSqlResult = "CREATE TABLE DS_r AS (SELECT t0.ID_1 AS ID_1,stddev_pop(t0.ME_1) AS ME_1 FROM DS_30 t0 GROUP BY t0.ID_1);"+"CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);"; 
        }
		
		String commandStatements = "DS_r := stddev_pop ( DS_30 group by  Id_1 );";
		String result = translationUtilTest.translate(commandStatements);
		System.out.println("Print queryStddevPop: " +result);
		System.out.println("Print queryAttesaStddevPop: " +comandSqlResult);
		assertEquals("Test operator StddevPop", comandSqlResult, result);
	}

	@Test
	@Transactional
	public void stddevSamp() throws Exception {
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
                    comandSqlResult = "CREATE TABLE DS_r AS (SELECT t0.ID_1 AS ID_1,stddev_samp(t0.ME_1) AS ME_1 FROM DS_30 t0 GROUP BY t0.ID_1);"+"CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);"; 
        } else if (profileActive.equalsIgnoreCase("sqlServer")) {
                    comandSqlResult = "SELECT t1.* INTO DS_r FROM (SELECT t0.ID_1 AS ID_1,stdev(t0.ME_1) AS ME_1 FROM DS_30 t0 GROUP BY t0.ID_1) t1;"+"CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);";
        } else if (profileActive.equalsIgnoreCase("mySql")) {
                    comandSqlResult = "CREATE TABLE DS_r AS (SELECT t0.ID_1 AS ID_1,stddev_samp(t0.ME_1) AS ME_1 FROM DS_30 t0 GROUP BY t0.ID_1);"+"CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);";
        } else if (profileActive.equalsIgnoreCase("postgreSql")) {
                    comandSqlResult = "CREATE TABLE DS_r AS (SELECT t0.ID_1 AS ID_1,stddev_samp(t0.ME_1) AS ME_1 FROM DS_30 t0 GROUP BY t0.ID_1);"+"CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);";
        }
		
		String commandStatements = "DS_r := stddev_samp ( DS_30 group by  Id_1 );";
		String result = translationUtilTest.translate(commandStatements);
		System.out.println("Print queryStddevSamp: " +result);
		System.out.println("Print queryAttesaStddevSamp: " +comandSqlResult);
		assertEquals("Test operator StddevSamp", comandSqlResult, result);

	}

	@Test
	@Transactional
	public void varPop() throws Exception {
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
                    comandSqlResult = "CREATE TABLE DS_r AS (SELECT t0.ID_1 AS ID_1,var_pop(t0.ME_1) AS ME_1 FROM DS_30 t0 GROUP BY t0.ID_1);"+"CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);";
        } else if (profileActive.equalsIgnoreCase("sqlServer")) {
                    comandSqlResult = "SELECT t1.* INTO DS_r FROM (SELECT t0.ID_1 AS ID_1,varp(t0.ME_1) AS ME_1 FROM DS_30 t0 GROUP BY t0.ID_1) t1;"+"CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);";
        } else if (profileActive.equalsIgnoreCase("mySql")) {
                    comandSqlResult = "CREATE TABLE DS_r AS (SELECT t0.ID_1 AS ID_1,var_pop(t0.ME_1) AS ME_1 FROM DS_30 t0 GROUP BY t0.ID_1);"+"CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);";
        } else if (profileActive.equalsIgnoreCase("postgreSql")) {
                    comandSqlResult = "CREATE TABLE DS_r AS (SELECT t0.ID_1 AS ID_1,var_pop(t0.ME_1) AS ME_1 FROM DS_30 t0 GROUP BY t0.ID_1);"+"CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);";
        }		
		
		String commandStatements = "DS_r := var_pop ( DS_30 group by Id_1 );";
		String result = translationUtilTest.translate(commandStatements);
		System.out.println("Print queryVarPop: " +result);
		System.out.println("Print queryAttesaVarPop: " +comandSqlResult);
		assertEquals("Test operator VarPop", comandSqlResult, result);

	}

	@Test
	@Transactional
	public void varSamp() throws Exception {
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
                    comandSqlResult = "CREATE TABLE DS_r AS (SELECT t0.ID_1 AS ID_1,var_samp(t0.ME_1) AS ME_1 FROM DS_30 t0 GROUP BY t0.ID_1);"+"CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);";
        } else if (profileActive.equalsIgnoreCase("sqlServer")) {
                    comandSqlResult = "SELECT t1.* INTO DS_r FROM (SELECT t0.ID_1 AS ID_1,var(t0.ME_1) AS ME_1 FROM DS_30 t0 GROUP BY t0.ID_1) t1;"+"CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);";
        } else if (profileActive.equalsIgnoreCase("mySql")) {
                    comandSqlResult = "CREATE TABLE DS_r AS (SELECT t0.ID_1 AS ID_1,var_samp(t0.ME_1) AS ME_1 FROM DS_30 t0 GROUP BY t0.ID_1);"+"CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);";
        } else if (profileActive.equalsIgnoreCase("postgreSql")) {
                    comandSqlResult = "CREATE TABLE DS_r AS (SELECT t0.ID_1 AS ID_1,var_samp(t0.ME_1) AS ME_1 FROM DS_30 t0 GROUP BY t0.ID_1);"+"CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);";
        }		
		
		String commandStatements = "DS_r := var_samp ( DS_30 group by Id_1 );";
		String result = translationUtilTest.translate(commandStatements);
		System.out.println("Print queryVarSamp: " +result);
		System.out.println("Print queryAttesaVarSamp: " +comandSqlResult);
		assertEquals("Test operator VarSamp", comandSqlResult, result);

	}

	@Test
	@Transactional
	public void firstValue() throws Exception {
		dbTableUtilityService.resetSchema("t");
		VtlDataset vtlDatasetFind = datasetRepository.findByNameIgnoreCase("DS_1");
		if (vtlDatasetFind == null) {
			VtlDataset vtlDataset = new VtlDataset();

			vtlDataset.setPersistent(true);
			vtlDataset.setName("DS_34");
			vtlDataset.setIsOnlyAScalar(false);
			vtlDataset.setTransitory(false);
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ID_1", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ID_2", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ID_3", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ME_1", VtlType.INTEGER, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ME_2", VtlType.INTEGER, VtlComponentRole.MEASURE));
			datasetRepository.save(vtlDataset);
		}
		
        System.out.println("Il profilo attivo è " + environment.getActiveProfiles()[0]);
        String comandSqlResult = "";
        String profileActive = environment.getActiveProfiles()[0];
        if (profileActive.equalsIgnoreCase("oracleSql")) {
                    comandSqlResult = "CREATE TABLE DS_r AS (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,t0.ID_3 AS ID_3,first_value(t0.ME_1) OVER (PARTITION BY t0.ID_1,t0.ID_2 ORDER BY t0.ID_3 ASC ROWS BETWEEN 1 PRECEDING AND 1 FOLLOWING) AS ME_1,first_value(t0.ME_2) OVER (PARTITION BY t0.ID_1,t0.ID_2 ORDER BY t0.ID_3 ASC ROWS BETWEEN 1 PRECEDING AND 1 FOLLOWING) AS ME_2 FROM DS_34 t0);"+"CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);"+"CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);"+"CREATE INDEX DS_r_ID_3_IDX ON DS_r (ID_3);";
        } else if (profileActive.equalsIgnoreCase("sqlServer")) {
                    comandSqlResult = "SELECT t1.* INTO DS_r FROM (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,t0.ID_3 AS ID_3,first_value(t0.ME_1) OVER (PARTITION BY t0.ID_1,t0.ID_2 ORDER BY t0.ID_3 ASC ROWS BETWEEN 1 PRECEDING AND 1 FOLLOWING) AS ME_1,first_value(t0.ME_2) OVER (PARTITION BY t0.ID_1,t0.ID_2 ORDER BY t0.ID_3 ASC ROWS BETWEEN 1 PRECEDING AND 1 FOLLOWING) AS ME_2 FROM DS_34 t0) t1;"+"CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);"+"CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);"+"CREATE INDEX DS_r_ID_3_IDX ON DS_r (ID_3);";
        } else if (profileActive.equalsIgnoreCase("mySql")) {
                    comandSqlResult = "CREATE TABLE DS_r AS (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,t0.ID_3 AS ID_3,first_value(t0.ME_1) OVER (PARTITION BY t0.ID_1,t0.ID_2 ORDER BY t0.ID_3 ASC ROWS BETWEEN 1 PRECEDING AND 1 FOLLOWING) AS ME_1,first_value(t0.ME_2) OVER (PARTITION BY t0.ID_1,t0.ID_2 ORDER BY t0.ID_3 ASC ROWS BETWEEN 1 PRECEDING AND 1 FOLLOWING) AS ME_2 FROM DS_34 t0);"+"CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);"+"CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);"+"CREATE INDEX DS_r_ID_3_IDX ON DS_r (ID_3);";
        } else if (profileActive.equalsIgnoreCase("postgreSql")) {
                    comandSqlResult = "CREATE TABLE DS_r AS (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,t0.ID_3 AS ID_3,first_value(t0.ME_1) OVER (PARTITION BY t0.ID_1,t0.ID_2 ORDER BY t0.ID_3 ASC ROWS BETWEEN 1 PRECEDING AND 1 FOLLOWING) AS ME_1,first_value(t0.ME_2) OVER (PARTITION BY t0.ID_1,t0.ID_2 ORDER BY t0.ID_3 ASC ROWS BETWEEN 1 PRECEDING AND 1 FOLLOWING) AS ME_2 FROM DS_34 t0);"+"CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);"+"CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);"+"CREATE INDEX DS_r_ID_3_IDX ON DS_r (ID_3);";
        }		
		
		String commandStatements = "DS_r := first_value ( DS_34 over ( partition by  Id_1, Id_2 order by  Id_3  data points between 1 preceding and 1 following) );";
		String result = translationUtilTest.translate(commandStatements);
		System.out.println("Print queryFirstValue: " +result);
		System.out.println("Print queryAttesaFirstValue: " +comandSqlResult);
		assertEquals("Test operator FirstValue", comandSqlResult, result);

	}

	@Test
	@Transactional
	public void lastValue() throws Exception {
		dbTableUtilityService.resetSchema("t");
		VtlDataset vtlDatasetFind = datasetRepository.findByNameIgnoreCase("DS_1");
		if (vtlDatasetFind == null) {
			VtlDataset vtlDataset = new VtlDataset();

			vtlDataset.setPersistent(true);
			vtlDataset.setName("DS_34");
			vtlDataset.setIsOnlyAScalar(false);
			vtlDataset.setTransitory(false);
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ID_1", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ID_2", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ID_3", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ME_1", VtlType.INTEGER, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ME_2", VtlType.INTEGER, VtlComponentRole.MEASURE));
			datasetRepository.save(vtlDataset);
		}
		
        System.out.println("Il profilo attivo è " + environment.getActiveProfiles()[0]);
        String comandSqlResult = "";
        String profileActive = environment.getActiveProfiles()[0];
        if (profileActive.equalsIgnoreCase("oracleSql")) {
                    comandSqlResult = "CREATE TABLE DS_r AS (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,t0.ID_3 AS ID_3,last_value(t0.ME_1) OVER (PARTITION BY t0.ID_1,t0.ID_2 ORDER BY t0.ID_3 ASC ROWS BETWEEN 1 PRECEDING AND 1 FOLLOWING) AS ME_1,last_value(t0.ME_2) OVER (PARTITION BY t0.ID_1,t0.ID_2 ORDER BY t0.ID_3 ASC ROWS BETWEEN 1 PRECEDING AND 1 FOLLOWING) AS ME_2 FROM DS_34 t0);"+"CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);"+"CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);"+"CREATE INDEX DS_r_ID_3_IDX ON DS_r (ID_3);";
        } else if (profileActive.equalsIgnoreCase("sqlServer")) {
                    comandSqlResult = "SELECT t1.* INTO DS_r FROM (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,t0.ID_3 AS ID_3,last_value(t0.ME_1) OVER (PARTITION BY t0.ID_1,t0.ID_2 ORDER BY t0.ID_3 ASC ROWS BETWEEN 1 PRECEDING AND 1 FOLLOWING) AS ME_1,last_value(t0.ME_2) OVER (PARTITION BY t0.ID_1,t0.ID_2 ORDER BY t0.ID_3 ASC ROWS BETWEEN 1 PRECEDING AND 1 FOLLOWING) AS ME_2 FROM DS_34 t0) t1;"+"CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);"+"CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);"+"CREATE INDEX DS_r_ID_3_IDX ON DS_r (ID_3);";
        } else if (profileActive.equalsIgnoreCase("mySql")) {
                    comandSqlResult = "CREATE TABLE DS_r AS (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,t0.ID_3 AS ID_3,last_value(t0.ME_1) OVER (PARTITION BY t0.ID_1,t0.ID_2 ORDER BY t0.ID_3 ASC ROWS BETWEEN 1 PRECEDING AND 1 FOLLOWING) AS ME_1,last_value(t0.ME_2) OVER (PARTITION BY t0.ID_1,t0.ID_2 ORDER BY t0.ID_3 ASC ROWS BETWEEN 1 PRECEDING AND 1 FOLLOWING) AS ME_2 FROM DS_34 t0);"+"CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);"+"CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);"+"CREATE INDEX DS_r_ID_3_IDX ON DS_r (ID_3);";
        } else if (profileActive.equalsIgnoreCase("postgreSql")) {
                    comandSqlResult = "CREATE TABLE DS_r AS (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,t0.ID_3 AS ID_3,last_value(t0.ME_1) OVER (PARTITION BY t0.ID_1,t0.ID_2 ORDER BY t0.ID_3 ASC ROWS BETWEEN 1 PRECEDING AND 1 FOLLOWING) AS ME_1,last_value(t0.ME_2) OVER (PARTITION BY t0.ID_1,t0.ID_2 ORDER BY t0.ID_3 ASC ROWS BETWEEN 1 PRECEDING AND 1 FOLLOWING) AS ME_2 FROM DS_34 t0);"+"CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);"+"CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);"+"CREATE INDEX DS_r_ID_3_IDX ON DS_r (ID_3);";
        }		
		
		String commandStatements = "DS_r := last_value ( DS_34 over ( partition by Id_1, Id_2 order by Id_3 data points between 1 preceding and 1 following ) );";
		String result = translationUtilTest.translate(commandStatements);
		System.out.println("Print queryLastValue: " +result);
		System.out.println("Print queryAttesaLastValue: " +comandSqlResult);
		assertEquals("Test operator LastValue", comandSqlResult, result);
	}

	@Test
	@Transactional
	public void lag() throws Exception {
		dbTableUtilityService.resetSchema("t");
		VtlDataset vtlDatasetFind = datasetRepository.findByNameIgnoreCase("DS_1");
		if (vtlDatasetFind == null) {
			VtlDataset vtlDataset = new VtlDataset();

			vtlDataset.setPersistent(true);
			vtlDataset.setName("DS_34");
			vtlDataset.setIsOnlyAScalar(false);
			vtlDataset.setTransitory(false);
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ID_1", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ID_2", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ID_3", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ME_1", VtlType.INTEGER, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ME_2", VtlType.INTEGER, VtlComponentRole.MEASURE));
			datasetRepository.save(vtlDataset);
		}
		
        System.out.println("Il profilo attivo è " + environment.getActiveProfiles()[0]);
        String comandSqlResult = "";
        String profileActive = environment.getActiveProfiles()[0];
        if (profileActive.equalsIgnoreCase("oracleSql")) {
                    comandSqlResult = "CREATE TABLE DS_r AS (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,t0.ID_3 AS ID_3,lag(t0.ME_1,1) OVER (PARTITION BY t0.ID_1,t0.ID_2 ORDER BY t0.ID_3 ASC) AS ME_1,lag(t0.ME_2,1) OVER (PARTITION BY t0.ID_1,t0.ID_2 ORDER BY t0.ID_3 ASC) AS ME_2 FROM DS_34 t0);"+"CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);"+"CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);"+"CREATE INDEX DS_r_ID_3_IDX ON DS_r (ID_3);";
        } else if (profileActive.equalsIgnoreCase("sqlServer")) {
                    comandSqlResult = "SELECT t1.* INTO DS_r FROM (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,t0.ID_3 AS ID_3,lag(t0.ME_1,1) OVER (PARTITION BY t0.ID_1,t0.ID_2 ORDER BY t0.ID_3 ASC) AS ME_1,lag(t0.ME_2,1) OVER (PARTITION BY t0.ID_1,t0.ID_2 ORDER BY t0.ID_3 ASC) AS ME_2 FROM DS_34 t0) t1;"+"CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);"+"CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);"+"CREATE INDEX DS_r_ID_3_IDX ON DS_r (ID_3);";
        } else if (profileActive.equalsIgnoreCase("mySql")) {
                    comandSqlResult = "CREATE TABLE DS_r AS (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,t0.ID_3 AS ID_3,lag(t0.ME_1,1) OVER (PARTITION BY t0.ID_1,t0.ID_2 ORDER BY t0.ID_3 ASC) AS ME_1,lag(t0.ME_2,1) OVER (PARTITION BY t0.ID_1,t0.ID_2 ORDER BY t0.ID_3 ASC) AS ME_2 FROM DS_34 t0);"+"CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);"+"CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);"+"CREATE INDEX DS_r_ID_3_IDX ON DS_r (ID_3);";
        } else if (profileActive.equalsIgnoreCase("postgreSql")) {
                    comandSqlResult = "CREATE TABLE DS_r AS (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,t0.ID_3 AS ID_3,lag(t0.ME_1,1) OVER (PARTITION BY t0.ID_1,t0.ID_2 ORDER BY t0.ID_3 ASC) AS ME_1,lag(t0.ME_2,1) OVER (PARTITION BY t0.ID_1,t0.ID_2 ORDER BY t0.ID_3 ASC) AS ME_2 FROM DS_34 t0);"+"CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);"+"CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);"+"CREATE INDEX DS_r_ID_3_IDX ON DS_r (ID_3);";
        }		
		
		String commandStatements = "DS_r := lag ( DS_34 , 1 over ( partition by  Id_1 , Id_2  order by  Id_3  ) );";
		String result = translationUtilTest.translate(commandStatements);
		System.out.println("Print queryLag: " +result);
		System.out.println("Print queryAttesaLag: " +comandSqlResult);
		assertEquals("Test operator Lag", comandSqlResult, result);
	}

	@Test
	@Transactional
	public void lead() throws Exception {
		dbTableUtilityService.resetSchema("t");
		VtlDataset vtlDatasetFind = datasetRepository.findByNameIgnoreCase("DS_1");
		if (vtlDatasetFind == null) {
			VtlDataset vtlDataset = new VtlDataset();

			vtlDataset.setPersistent(true);
			vtlDataset.setName("DS_34");
			vtlDataset.setIsOnlyAScalar(false);
			vtlDataset.setTransitory(false);
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ID_1", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ID_2", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ID_3", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ME_1", VtlType.INTEGER, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ME_2", VtlType.INTEGER, VtlComponentRole.MEASURE));
			datasetRepository.save(vtlDataset);
		}
		
        System.out.println("Il profilo attivo è " + environment.getActiveProfiles()[0]);
        String comandSqlResult = "";
        String profileActive = environment.getActiveProfiles()[0];
        if (profileActive.equalsIgnoreCase("oracleSql")) {
                    comandSqlResult = "CREATE TABLE DS_r AS (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,t0.ID_3 AS ID_3,lead(t0.ME_1,1) OVER (PARTITION BY t0.ID_1,t0.ID_2 ORDER BY t0.ID_3 ASC) AS ME_1,lead(t0.ME_2,1) OVER (PARTITION BY t0.ID_1,t0.ID_2 ORDER BY t0.ID_3 ASC) AS ME_2 FROM DS_34 t0);"+"CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);"+"CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);"+"CREATE INDEX DS_r_ID_3_IDX ON DS_r (ID_3);";
        } else if (profileActive.equalsIgnoreCase("sqlServer")) {
                    comandSqlResult = "SELECT t1.* INTO DS_r FROM (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,t0.ID_3 AS ID_3,lead(t0.ME_1,1) OVER (PARTITION BY t0.ID_1,t0.ID_2 ORDER BY t0.ID_3 ASC) AS ME_1,lead(t0.ME_2,1) OVER (PARTITION BY t0.ID_1,t0.ID_2 ORDER BY t0.ID_3 ASC) AS ME_2 FROM DS_34 t0) t1;"+"CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);"+"CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);"+"CREATE INDEX DS_r_ID_3_IDX ON DS_r (ID_3);";
        } else if (profileActive.equalsIgnoreCase("mySql")) {
                    comandSqlResult = "CREATE TABLE DS_r AS (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,t0.ID_3 AS ID_3,lead(t0.ME_1,1) OVER (PARTITION BY t0.ID_1,t0.ID_2 ORDER BY t0.ID_3 ASC) AS ME_1,lead(t0.ME_2,1) OVER (PARTITION BY t0.ID_1,t0.ID_2 ORDER BY t0.ID_3 ASC) AS ME_2 FROM DS_34 t0);"+"CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);"+"CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);"+"CREATE INDEX DS_r_ID_3_IDX ON DS_r (ID_3);";
        } else if (profileActive.equalsIgnoreCase("postgreSql")) {
                    comandSqlResult = "CREATE TABLE DS_r AS (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,t0.ID_3 AS ID_3,lead(t0.ME_1,1) OVER (PARTITION BY t0.ID_1,t0.ID_2 ORDER BY t0.ID_3 ASC) AS ME_1,lead(t0.ME_2,1) OVER (PARTITION BY t0.ID_1,t0.ID_2 ORDER BY t0.ID_3 ASC) AS ME_2 FROM DS_34 t0);"+"CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);"+"CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);"+"CREATE INDEX DS_r_ID_3_IDX ON DS_r (ID_3);";
        }		
		
		String commandStatements = "DS_r := lead ( DS_34 , 1 over ( partition by Id_1 , Id_2 order by Id_3 ) );";
		String result = translationUtilTest.translate(commandStatements);
		System.out.println("Print queryLead: " +result);
		System.out.println("Print queryAttesaLead: " +comandSqlResult);
		assertEquals("Test operator Lead", comandSqlResult, result);
	}

	@Test
	@Transactional
	public void rank() throws Exception {
		dbTableUtilityService.resetSchema("t");
		VtlDataset vtlDatasetFind = datasetRepository.findByNameIgnoreCase("DS_1");
		if (vtlDatasetFind == null) {
			VtlDataset vtlDataset = new VtlDataset();

			vtlDataset.setPersistent(true);
			vtlDataset.setName("DS_34");
			vtlDataset.setIsOnlyAScalar(false);
			vtlDataset.setTransitory(false);
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ID_1", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ID_2", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ID_3", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ME_1", VtlType.INTEGER, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ME_2", VtlType.INTEGER, VtlComponentRole.MEASURE));
			datasetRepository.save(vtlDataset);
		}
		
        System.out.println("Il profilo attivo è " + environment.getActiveProfiles()[0]);
        String comandSqlResult = "";
        String profileActive = environment.getActiveProfiles()[0];
        if (profileActive.equalsIgnoreCase("oracleSql")) {
                    comandSqlResult = "CREATE TABLE DS_r AS (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,t0.ID_3 AS ID_3,t0.ME_1 AS ME_1,rank() OVER (PARTITION BY t0.ID_1,t0.ID_2 ORDER BY t0.ME_1 ASC) AS Me_2 FROM DS_34 t0);"+"CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);"+"CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);"+"CREATE INDEX DS_r_ID_3_IDX ON DS_r (ID_3);";
        } else if (profileActive.equalsIgnoreCase("sqlServer")) {
                    comandSqlResult = "SELECT t1.* INTO DS_r FROM (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,t0.ID_3 AS ID_3,t0.ME_1 AS ME_1,rank() OVER (PARTITION BY t0.ID_1,t0.ID_2 ORDER BY t0.ME_1 ASC) AS Me_2 FROM DS_34 t0) t1;"+"CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);"+"CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);"+"CREATE INDEX DS_r_ID_3_IDX ON DS_r (ID_3);";
        } else if (profileActive.equalsIgnoreCase("mySql")) {
                    comandSqlResult = "CREATE TABLE DS_r AS (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,t0.ID_3 AS ID_3,t0.ME_1 AS ME_1,rank() OVER (PARTITION BY t0.ID_1,t0.ID_2 ORDER BY t0.ME_1 ASC) AS Me_2 FROM DS_34 t0);"+"CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);"+"CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);"+"CREATE INDEX DS_r_ID_3_IDX ON DS_r (ID_3);";
        } else if (profileActive.equalsIgnoreCase("postgreSql")) {
                    comandSqlResult = "CREATE TABLE DS_r AS (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,t0.ID_3 AS ID_3,t0.ME_1 AS ME_1,rank() OVER (PARTITION BY t0.ID_1,t0.ID_2 ORDER BY t0.ME_1 ASC) AS Me_2 FROM DS_34 t0);"+"CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);"+"CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);"+"CREATE INDEX DS_r_ID_3_IDX ON DS_r (ID_3);";
        }		
		
		String commandStatements = "DS_r := DS_34 [ calc Me_2 := rank (ME_1 over ( partition by Id_1 , Id_2 order by Me_1 ) ) ];";
		String result = translationUtilTest.translate(commandStatements);
		System.out.println("Print queryRank: " +result);
		System.out.println("Print queryAttesaRank: " +comandSqlResult);
		assertEquals("Test operator Rank", comandSqlResult, result);

	}

	@Test
	@Transactional
	public void ratioToReport() throws Exception {
		dbTableUtilityService.resetSchema("t");
		VtlDataset vtlDatasetFind = datasetRepository.findByNameIgnoreCase("DS_1");
		if (vtlDatasetFind == null) {
			VtlDataset vtlDataset = new VtlDataset();

			vtlDataset.setPersistent(true);
			vtlDataset.setName("DS_34");
			vtlDataset.setIsOnlyAScalar(false);
			vtlDataset.setTransitory(false);
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ID_1", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ID_2", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ID_3", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ME_1", VtlType.INTEGER, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ME_2", VtlType.INTEGER, VtlComponentRole.MEASURE));
			datasetRepository.save(vtlDataset);
		}
		
        System.out.println("Il profilo attivo è " + environment.getActiveProfiles()[0]);
        String comandSqlResult = "";
        String profileActive = environment.getActiveProfiles()[0];
        if (profileActive.equalsIgnoreCase("oracleSql")) {
                    comandSqlResult = "CREATE TABLE DS_r AS (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,t0.ID_3 AS ID_3,ratio_to_report(t0.ME_1) OVER (PARTITION BY t0.ID_1,t0.ID_2) AS ME_1,ratio_to_report(t0.ME_2) OVER (PARTITION BY t0.ID_1,t0.ID_2) AS ME_2 FROM DS_34 t0);"+"CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);"+"CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);"+"CREATE INDEX DS_r_ID_3_IDX ON DS_r (ID_3);";
        } else if (profileActive.equalsIgnoreCase("sqlServer")) {
                    comandSqlResult = "SELECT t1.* INTO DS_r FROM (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,t0.ID_3 AS ID_3,(t0.ME_1) / sum(cast((t0.ME_1) AS FLOAT)) OVER (PARTITION BY t0.ID_1,t0.ID_2) AS ME_1,(t0.ME_2) / sum(cast((t0.ME_2) AS FLOAT)) OVER (PARTITION BY t0.ID_1,t0.ID_2) AS ME_2 FROM DS_34 t0) t1;CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);CREATE INDEX DS_r_ID_3_IDX ON DS_r (ID_3);";
        } else if (profileActive.equalsIgnoreCase("mySql")) {
                    comandSqlResult = "CREATE TABLE DS_r AS (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,t0.ID_3 AS ID_3,(t0.ME_1) / sum(t0.ME_1) OVER (PARTITION BY t0.ID_1,t0.ID_2) AS ME_1,(t0.ME_2) / sum(t0.ME_2) OVER (PARTITION BY t0.ID_1,t0.ID_2) AS ME_2 FROM DS_34 t0);CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);CREATE INDEX DS_r_ID_3_IDX ON DS_r (ID_3);";
        } else if (profileActive.equalsIgnoreCase("postgreSql")) {
                    comandSqlResult = "CREATE TABLE DS_r AS (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,t0.ID_3 AS ID_3,(t0.ME_1) / sum(cast((t0.ME_1) AS FLOAT)) OVER (PARTITION BY t0.ID_1,t0.ID_2) AS ME_1,(t0.ME_2) / sum(cast((t0.ME_2) AS FLOAT)) OVER (PARTITION BY t0.ID_1,t0.ID_2) AS ME_2 FROM DS_34 t0);CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);CREATE INDEX DS_r_ID_3_IDX ON DS_r (ID_3);";
        }
		
		String commandStatements = "DS_r := ratio_to_report ( DS_34  over ( partition by  Id_1, Id_2  ) );";
		String result = translationUtilTest.translate(commandStatements);
		System.out.println("Print queryRatioToReport: " +result);
		System.out.println("Print queryAttesaRatioToReport: " +comandSqlResult);
		assertEquals("Test operator RatioToReport", comandSqlResult, result);
		

	}
}
