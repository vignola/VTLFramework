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
public class VtlTimeOperatorsTranslationTest {

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
	public void periodIndicator() throws Exception {
		dbTableUtilityService.resetSchema("t");
		VtlDataset vtlDatasetFind = datasetRepository.findByNameIgnoreCase("DS_1");
		if (vtlDatasetFind == null) {
			VtlDataset vtlDataset = new VtlDataset();

			vtlDataset.setPersistent(true);
			vtlDataset.setName("DS_39");
			vtlDataset.setIsOnlyAScalar(false);
			vtlDataset.setTransitory(false);
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ID_1", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ID_2", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(
					translationUtilTest.getVtlComponent("ID_3", VtlType.TIME_PERIOD, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ME_1", VtlType.INTEGER, VtlComponentRole.MEASURE));
			datasetRepository.save(vtlDataset);

		}
		
		System.out.println("Il profilo attivo è " + environment.getActiveProfiles()[0]);

		String comandSqlResult = "";
		String profileActive = environment.getActiveProfiles()[0];
		if (profileActive.equalsIgnoreCase("oracleSql")) {
			comandSqlResult = "CREATE TABLE DS_r AS (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,t0.ID_3 AS ID_3,vtl_pkg.period_indicator(t0.ID_3) AS duration_var FROM DS_39 t0);"+"CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);"+"CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);"+"CREATE INDEX DS_r_ID_3_IDX ON DS_r (ID_3);";
		} else if (profileActive.equalsIgnoreCase("sqlServer")) {
			comandSqlResult = "SELECT t1.* INTO DS_r FROM (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,t0.ID_3 AS ID_3,dbo.vtl_period_indicator(t0.ID_3) AS duration_var FROM DS_39 t0) t1;CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);CREATE INDEX DS_r_ID_3_IDX ON DS_r (ID_3);"; //TODO Importare Package
		} else if (profileActive.equalsIgnoreCase("mySql")) {
			comandSqlResult = "CREATE TABLE DS_r AS (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,t0.ID_3 AS ID_3,vtl_period_indicator(t0.ID_3) AS duration_var FROM DS_39 t0);"+"CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);"+"CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);"+"CREATE INDEX DS_r_ID_3_IDX ON DS_r (ID_3);";
		} else if (profileActive.equalsIgnoreCase("postgreSql")) {
			comandSqlResult = "CREATE TABLE DS_r AS (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,t0.ID_3 AS ID_3,vtl_period_indicator(t0.ID_3) AS duration_var FROM DS_39 t0);"+"CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);"+"CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);"+"CREATE INDEX DS_r_ID_3_IDX ON DS_r (ID_3);";
		}
		
		String commandStatements = "DS_r := period_indicator ( DS_39 );";
		String result = translationUtilTest.translate(commandStatements);
		System.out.println("Print queryPeriodIndicator: " +result);
		System.out.println("Print queryAttesaPeriodIndicator: " +comandSqlResult);
		assertEquals("Test operator PeriodIndicator", comandSqlResult, result);
	}

	@Test
	@Transactional
	public void periodIndicatorComponent() throws Exception {
		dbTableUtilityService.resetSchema("t");
		VtlDataset vtlDatasetFind = datasetRepository.findByNameIgnoreCase("DS_1");
		if (vtlDatasetFind == null) {
			VtlDataset vtlDataset = new VtlDataset();

			vtlDataset.setPersistent(true);
			vtlDataset.setName("DS_39");
			vtlDataset.setIsOnlyAScalar(false);
			vtlDataset.setTransitory(false);
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ID_1", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ID_2", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(
					translationUtilTest.getVtlComponent("ID_3", VtlType.TIME_PERIOD, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ME_1", VtlType.INTEGER, VtlComponentRole.MEASURE));
			datasetRepository.save(vtlDataset);

		}
		
		System.out.println("Il profilo attivo è " + environment.getActiveProfiles()[0]);

		String comandSqlResult = "";
		String profileActive = environment.getActiveProfiles()[0];
		if (profileActive.equalsIgnoreCase("oracleSql")) {
			comandSqlResult = "CREATE TABLE DS_r AS (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,t0.ID_3 AS ID_3,t0.ME_1 AS ME_1 FROM DS_39 t0 WHERE (vtl_pkg.period_indicator(t0.ID_3)='A'));"+"CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);"+"CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);"+"CREATE INDEX DS_r_ID_3_IDX ON DS_r (ID_3);"; 
		} else if (profileActive.equalsIgnoreCase("sqlServer")) {
			comandSqlResult = "SELECT t1.* INTO DS_r FROM (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,t0.ID_3 AS ID_3,t0.ME_1 AS ME_1 FROM DS_39 t0 WHERE (dbo.vtl_period_indicator(t0.ID_3)='A')) t1;CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);CREATE INDEX DS_r_ID_3_IDX ON DS_r (ID_3);";
		} else if (profileActive.equalsIgnoreCase("mySql")) {
			comandSqlResult = "CREATE TABLE DS_r AS (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,t0.ID_3 AS ID_3,t0.ME_1 AS ME_1 FROM DS_39 t0 WHERE (vtl_period_indicator(t0.ID_3)='A'));"+"CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);"+"CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);"+"CREATE INDEX DS_r_ID_3_IDX ON DS_r (ID_3);";
		} else if (profileActive.equalsIgnoreCase("postgreSql")) {
			comandSqlResult = "CREATE TABLE DS_r AS (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,t0.ID_3 AS ID_3,t0.ME_1 AS ME_1 FROM DS_39 t0 WHERE (vtl_period_indicator(t0.ID_3)='A'));"+"CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);"+"CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);"+"CREATE INDEX DS_r_ID_3_IDX ON DS_r (ID_3);";
		}
		
		String commandStatements = "DS_r := DS_39 [ filter period_indicator ( Id_3 ) = \"A\" ]";
		String result = translationUtilTest.translate(commandStatements);
		System.out.println("Print queryPeriodIndicatorComponent: " +result);
		System.out.println("Print queryAttesaPeriodIndicatorComponent: " +comandSqlResult);
		assertEquals("Test operator PeriodIndicatorComponent", comandSqlResult, result);

	}

	@Test
	@Transactional
	public void fillTimeSeriesSingle() throws Exception {
		dbTableUtilityService.resetSchema("t");
		VtlDataset vtlDatasetFind = datasetRepository.findByNameIgnoreCase("DS_1");
		if (vtlDatasetFind == null) {
			VtlDataset vtlDataset = new VtlDataset();

			vtlDataset.setPersistent(true);
			vtlDataset.setName("DS_21");
			vtlDataset.setIsOnlyAScalar(false);
			vtlDataset.setTransitory(false);
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ID_1", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ID_2", VtlType.TIME, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ME_1", VtlType.STRING, VtlComponentRole.MEASURE));
			datasetRepository.save(vtlDataset);
		}
		
		System.out.println("Il profilo attivo è " + environment.getActiveProfiles()[0]);

		String comandSqlResult = "";
		String profileActive = environment.getActiveProfiles()[0];
		if (profileActive.equalsIgnoreCase("oracleSql")) {
			comandSqlResult = "EXECUTE vtl_pkg.fill_time_series('DS_21','ID_1','ID_2','SINGLE');CREATE TABLE DS_r AS (SELECT t2.ID_1 AS ID_1,t2.ID_2 AS ID_2,t1.ME_1 AS ME_1 FROM TEMPORARY_FTS t2 LEFT OUTER JOIN DS_21 t1 ON (((t2.ID_1 = t1.ID_1) AND (t2.ID_2 = t1.ID_2))));CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);"; 
		} else if (profileActive.equalsIgnoreCase("sqlServer")) {
			comandSqlResult = "EXECUTE dbo.vtl_fill_time_series @p_input_dataset = 'DS_21', @p_id_list = 'ID_1', @p_id_time = 'ID_2', @p_limits_method = 'SINGLE';SELECT t3.* INTO DS_r FROM (SELECT t2.ID_1 AS ID_1,t2.ID_2 AS ID_2,t1.ME_1 AS ME_1 FROM TEMPORARY_FTS t2 LEFT OUTER JOIN DS_21 t1 ON (((t2.ID_1 = t1.ID_1) AND (t2.ID_2 = t1.ID_2)))) t3;CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);";
		} else if (profileActive.equalsIgnoreCase("mySql")) {
			comandSqlResult = "CALL vtl_fill_time_series('DS_21','ID_1','ID_2','SINGLE');CREATE TABLE DS_r AS (SELECT t2.ID_1 AS ID_1,t2.ID_2 AS ID_2,t1.ME_1 AS ME_1 FROM TEMPORARY_FTS t2 LEFT OUTER JOIN DS_21 t1 ON (((t2.ID_1 = t1.ID_1) AND (t2.ID_2 = t1.ID_2))));CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);";
		} else if (profileActive.equalsIgnoreCase("postgreSql")) {
			comandSqlResult = "CALL vtl_fill_time_series('DS_21','ID_1','ID_2','SINGLE');CREATE TABLE DS_r AS (SELECT t2.ID_1 AS ID_1,t2.ID_2 AS ID_2,t1.ME_1 AS ME_1 FROM TEMPORARY_FTS t2 LEFT OUTER JOIN DS_21 t1 ON (((t2.ID_1 = t1.ID_1) AND (t2.ID_2 = t1.ID_2))));CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);";
		}
		
		String commandStatements = "DS_r := fill_time_series ( DS_21, single );";
		String result = translationUtilTest.translate(commandStatements);
		System.out.println("Print queryFillTimeSeriesSingle: " +result);
		System.out.println("Print queryAttesaFillTimeSeriesSingle: " +comandSqlResult);
		assertEquals("Test operator FillTimeSeriesSingle", comandSqlResult, result);
	}

	@Test
	@Transactional
	public void fillTimeSeriesAll() throws Exception {
		dbTableUtilityService.resetSchema("t");
		VtlDataset vtlDatasetFind = datasetRepository.findByNameIgnoreCase("DS_1");
		if (vtlDatasetFind == null) {
			VtlDataset vtlDataset = new VtlDataset();

			vtlDataset.setPersistent(true);
			vtlDataset.setName("DS_60");
			vtlDataset.setIsOnlyAScalar(false);
			vtlDataset.setTransitory(false);
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ID_1", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ID_2", VtlType.TIME, VtlComponentRole.IDENTIFIER));
                        vtlDataset.addComponent(translationUtilTest.getVtlComponent("ID_3", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ME_1", VtlType.STRING, VtlComponentRole.MEASURE));
                        vtlDataset.addComponent(translationUtilTest.getVtlComponent("AT_1", VtlType.STRING, VtlComponentRole.VIRAL));
			datasetRepository.save(vtlDataset);
		}
		
		System.out.println("Il profilo attivo è " + environment.getActiveProfiles()[0]);

		String comandSqlResult = "";
		String profileActive = environment.getActiveProfiles()[0];
		if (profileActive.equalsIgnoreCase("oracleSql")) {
			comandSqlResult = "EXECUTE vtl_pkg.fill_time_series('DS_60','ID_1, ID_3','ID_2','ALL');CREATE TABLE DS_r AS (SELECT t2.ID_1 AS ID_1,t2.ID_2 AS ID_2,t2.ID_3 AS ID_3,t1.ME_1 AS ME_1,t1.AT_1 AS AT_1 FROM TEMPORARY_FTS t2 LEFT OUTER JOIN DS_60 t1 ON (((t2.ID_1 = t1.ID_1) AND (t2.ID_2 = t1.ID_2) AND (t2.ID_3 = t1.ID_3))));CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);CREATE INDEX DS_r_ID_3_IDX ON DS_r (ID_3);"; 
		} else if (profileActive.equalsIgnoreCase("sqlServer")) {
			comandSqlResult = "EXECUTE dbo.vtl_fill_time_series @p_input_dataset = 'DS_60', @p_id_list = 'ID_1, ID_3', @p_id_time = 'ID_2', @p_limits_method = 'ALL';SELECT t3.* INTO DS_r FROM (SELECT t2.ID_1 AS ID_1,t2.ID_2 AS ID_2,t2.ID_3 AS ID_3,t1.ME_1 AS ME_1,t1.AT_1 AS AT_1 FROM TEMPORARY_FTS t2 LEFT OUTER JOIN DS_60 t1 ON (((t2.ID_1 = t1.ID_1) AND (t2.ID_2 = t1.ID_2) AND (t2.ID_3 = t1.ID_3)))) t3;CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);CREATE INDEX DS_r_ID_3_IDX ON DS_r (ID_3);";
		} else if (profileActive.equalsIgnoreCase("mySql")) {
			comandSqlResult = "CALL vtl_fill_time_series('DS_60','ID_1, ID_3','ID_2','ALL');CREATE TABLE DS_r AS (SELECT t2.ID_1 AS ID_1,t2.ID_2 AS ID_2,t2.ID_3 AS ID_3,t1.ME_1 AS ME_1,t1.AT_1 AS AT_1 FROM TEMPORARY_FTS t2 LEFT OUTER JOIN DS_60 t1 ON (((t2.ID_1 = t1.ID_1) AND (t2.ID_2 = t1.ID_2) AND (t2.ID_3 = t1.ID_3))));CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);CREATE INDEX DS_r_ID_3_IDX ON DS_r (ID_3);";
		} else if (profileActive.equalsIgnoreCase("postgreSql")) {
			comandSqlResult = "CALL vtl_fill_time_series('DS_60','ID_1, ID_3','ID_2','ALL');CREATE TABLE DS_r AS (SELECT t2.ID_1 AS ID_1,t2.ID_2 AS ID_2,t2.ID_3 AS ID_3,t1.ME_1 AS ME_1,t1.AT_1 AS AT_1 FROM TEMPORARY_FTS t2 LEFT OUTER JOIN DS_60 t1 ON (((t2.ID_1 = t1.ID_1) AND (t2.ID_2 = t1.ID_2) AND (t2.ID_3 = t1.ID_3))));CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);CREATE INDEX DS_r_ID_3_IDX ON DS_r (ID_3);";
		}
		
		String commandStatements = "DS_r := fill_time_series ( DS_60, all );";
		String result = translationUtilTest.translate(commandStatements);
		System.out.println("Print queryFillTimeSeriesAll: " +result);
		System.out.println("Print queryAttesaFillTimeSeriesAll: " +comandSqlResult);
		assertEquals("Test operator FillTimeSeriesAll", comandSqlResult, result);
	}

	@Test
	@Transactional
	public void flowToStock() throws Exception {
		dbTableUtilityService.resetSchema("t");
		VtlDataset vtlDatasetFind = datasetRepository.findByNameIgnoreCase("DS_1");
		if (vtlDatasetFind == null) {
			VtlDataset vtlDataset = new VtlDataset();

			vtlDataset.setPersistent(true);
			vtlDataset.setName("DS_23");
			vtlDataset.setIsOnlyAScalar(false);
			vtlDataset.setTransitory(false);
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ID_1", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ID_2", VtlType.TIME, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ME_1", VtlType.INTEGER, VtlComponentRole.MEASURE));
			datasetRepository.save(vtlDataset);
		}
		
		System.out.println("Il profilo attivo è " + environment.getActiveProfiles()[0]);

		String comandSqlResult = "";
		String profileActive = environment.getActiveProfiles()[0];
		if (profileActive.equalsIgnoreCase("oracleSql")) {
			comandSqlResult = "CREATE TABLE DS_r AS (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,sum(t0.ME_1) OVER (PARTITION BY t0.ID_1,vtl_pkg.period_indicator(t0.ID_2) ORDER BY t0.ID_2 ASC) AS ME_1 FROM DS_23 t0);CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);";
		} else if (profileActive.equalsIgnoreCase("sqlServer")) {
			comandSqlResult = "SELECT t1.* INTO DS_r FROM (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,sum(t0.ME_1) OVER (PARTITION BY t0.ID_1,dbo.vtl_period_indicator(t0.ID_2) ORDER BY t0.ID_2 ASC) AS ME_1 FROM DS_23 t0) t1;CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);";
		} else if (profileActive.equalsIgnoreCase("mySql")) {
			comandSqlResult = "CREATE TABLE DS_r AS (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,sum(t0.ME_1) OVER (PARTITION BY t0.ID_1,vtl_period_indicator(t0.ID_2) ORDER BY t0.ID_2 ASC) AS ME_1 FROM DS_23 t0);CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);";
		} else if (profileActive.equalsIgnoreCase("postgreSql")) {
			comandSqlResult = "CREATE TABLE DS_r AS (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,sum(t0.ME_1) OVER (PARTITION BY t0.ID_1,vtl_period_indicator(t0.ID_2) ORDER BY t0.ID_2 ASC) AS ME_1 FROM DS_23 t0);CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);";
		}
		
		String commandStatements = "DS_r := flow_to_stock ( DS_23 );";
		String result = translationUtilTest.translate(commandStatements);
		System.out.println("Print queryFlowToStock: " +result);
		System.out.println("Print queryAttesaFlowToStock: " +comandSqlResult);
		assertEquals("Test operator FlowToStock", comandSqlResult, result);
	}

	@Test
	@Transactional
	public void stockToFlow() throws Exception {
		dbTableUtilityService.resetSchema("t");
		VtlDataset vtlDatasetFind = datasetRepository.findByNameIgnoreCase("DS_1");
		if (vtlDatasetFind == null) {
			VtlDataset vtlDataset = new VtlDataset();

			vtlDataset.setPersistent(true);
			vtlDataset.setName("DS_23");
			vtlDataset.setIsOnlyAScalar(false);
			vtlDataset.setTransitory(false);
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ID_1", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ID_2", VtlType.TIME, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ME_1", VtlType.INTEGER, VtlComponentRole.MEASURE));
			datasetRepository.save(vtlDataset);
		}
		
		System.out.println("Il profilo attivo è " + environment.getActiveProfiles()[0]);

		String comandSqlResult = "";
		String profileActive = environment.getActiveProfiles()[0];
		if (profileActive.equalsIgnoreCase("oracleSql")) {
			comandSqlResult = "CREATE TABLE DS_r AS (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,((t0.ME_1) - lag(t0.ME_1,1,0) OVER (PARTITION BY t0.ID_1,vtl_pkg.period_indicator(t0.ID_2) ORDER BY t0.ID_2 ASC)) AS ME_1 FROM DS_23 t0);CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);"; 
		} else if (profileActive.equalsIgnoreCase("sqlServer")) {
			comandSqlResult = "SELECT t1.* INTO DS_r FROM (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,((t0.ME_1) - lag(t0.ME_1,1,0) OVER (PARTITION BY t0.ID_1,dbo.vtl_period_indicator(t0.ID_2) ORDER BY t0.ID_2 ASC)) AS ME_1 FROM DS_23 t0) t1;CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);";
		} else if (profileActive.equalsIgnoreCase("mySql")) {
			comandSqlResult = "CREATE TABLE DS_r AS (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,((t0.ME_1) - lag(t0.ME_1,1,0) OVER (PARTITION BY t0.ID_1,vtl_period_indicator(t0.ID_2) ORDER BY t0.ID_2 ASC)) AS ME_1 FROM DS_23 t0);CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);";
		} else if (profileActive.equalsIgnoreCase("postgreSql")) {
			comandSqlResult = "CREATE TABLE DS_r AS (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,((t0.ME_1) - lag(t0.ME_1,1,0) OVER (PARTITION BY t0.ID_1,vtl_period_indicator(t0.ID_2) ORDER BY t0.ID_2 ASC)) AS ME_1 FROM DS_23 t0);CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);";
		}
		
		String commandStatements = "DS_r := stock_to_flow ( DS_23 );";
		String result = translationUtilTest.translate(commandStatements);
		System.out.println("Print queryStockToFlow: " +result);
		System.out.println("Print queryAttesaStockToFlow: " +comandSqlResult);
		assertEquals("Test operator StockToFlow", comandSqlResult, result);

	}

	@Test
	@Transactional
	public void timeshift() throws Exception {
		dbTableUtilityService.resetSchema("t");
		VtlDataset vtlDatasetFind = datasetRepository.findByNameIgnoreCase("DS_1");
		if (vtlDatasetFind == null) {
			VtlDataset vtlDataset = new VtlDataset();

			vtlDataset.setPersistent(true);
			vtlDataset.setName("DS_21");
			vtlDataset.setIsOnlyAScalar(false);
			vtlDataset.setTransitory(false);
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ID_1", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ID_2", VtlType.TIME, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ME_1", VtlType.STRING, VtlComponentRole.MEASURE));
			datasetRepository.save(vtlDataset);
		}
		
		System.out.println("Il profilo attivo è " + environment.getActiveProfiles()[0]);

		String comandSqlResult = "";
		String profileActive = environment.getActiveProfiles()[0];
		if (profileActive.equalsIgnoreCase("oracleSql")) {
			comandSqlResult = "CREATE TABLE DS_r AS (SELECT t0.ID_1 AS ID_1,vtl_pkg.timeshift(t0.ID_2,-1) AS ID_2,t0.ME_1 AS ME_1 FROM DS_21 t0);"+"CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);"+"CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);"; 
		} else if (profileActive.equalsIgnoreCase("sqlServer")) {
			comandSqlResult = "SELECT t1.* INTO DS_r FROM (SELECT t0.ID_1 AS ID_1,dbo.vtl_timeshift(t0.ID_2,-1) AS ID_2,t0.ME_1 AS ME_1 FROM DS_21 t0) t1;CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);";
		} else if (profileActive.equalsIgnoreCase("mySql")) {
			comandSqlResult = "CREATE TABLE DS_r AS (SELECT t0.ID_1 AS ID_1,vtl_timeshift(t0.ID_2,-1) AS ID_2,t0.ME_1 AS ME_1 FROM DS_21 t0);"+"CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);"+"CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);";
		} else if (profileActive.equalsIgnoreCase("postgreSql")) {
			comandSqlResult = "CREATE TABLE DS_r AS (SELECT t0.ID_1 AS ID_1,vtl_timeshift(t0.ID_2,-1) AS ID_2,t0.ME_1 AS ME_1 FROM DS_21 t0);"+"CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);"+"CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);";
		}
		
		String commandStatements = "DS_r := timeshift ( DS_21 , -1  );";
		String result = translationUtilTest.translate(commandStatements);
		System.out.println("Print queryTimeshift: " +result);
		System.out.println("Print queryAttesaTimeshift: " +comandSqlResult);
		assertEquals("Test operator Timeshift", comandSqlResult, result);

	}

	@Test
	@Transactional
	public void timeshiftInt() throws Exception {
		dbTableUtilityService.resetSchema("t");
		VtlDataset vtlDatasetFind = datasetRepository.findByNameIgnoreCase("DS_1");
		if (vtlDatasetFind == null) {
			VtlDataset vtlDataset = new VtlDataset();

			vtlDataset.setPersistent(true);
			vtlDataset.setName("DS_22");
			vtlDataset.setIsOnlyAScalar(false);
			vtlDataset.setTransitory(false);
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ID_1", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ID_2", VtlType.TIME, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(translationUtilTest.getVtlComponent("ME_1", VtlType.STRING, VtlComponentRole.MEASURE));
			datasetRepository.save(vtlDataset);

		}
		
		System.out.println("Il profilo attivo è " + environment.getActiveProfiles()[0]);

		String comandSqlResult = "";
		String profileActive = environment.getActiveProfiles()[0];
		if (profileActive.equalsIgnoreCase("oracleSql")) {
			comandSqlResult = "CREATE TABLE DS_r AS (SELECT t0.ID_1 AS ID_1,vtl_pkg.timeshift(t0.ID_2,2) AS ID_2,t0.ME_1 AS ME_1 FROM DS_22 t0);"+"CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);"+"CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);";
		} else if (profileActive.equalsIgnoreCase("sqlServer")) {
			comandSqlResult = "SELECT t1.* INTO DS_r FROM (SELECT t0.ID_1 AS ID_1,dbo.vtl_timeshift(t0.ID_2,2) AS ID_2,t0.ME_1 AS ME_1 FROM DS_22 t0) t1;CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);";
		} else if (profileActive.equalsIgnoreCase("mySql")) {
			comandSqlResult = "CREATE TABLE DS_r AS (SELECT t0.ID_1 AS ID_1,vtl_timeshift(t0.ID_2,2) AS ID_2,t0.ME_1 AS ME_1 FROM DS_22 t0);"+"CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);"+"CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);";
		} else if (profileActive.equalsIgnoreCase("postgreSql")) {
			comandSqlResult = "CREATE TABLE DS_r AS (SELECT t0.ID_1 AS ID_1,vtl_timeshift(t0.ID_2,2) AS ID_2,t0.ME_1 AS ME_1 FROM DS_22 t0);"+"CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);"+"CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);";
		}
		
		String commandStatements = "DS_r := timeshift ( DS_22 , 2  );"; 
		String result = translationUtilTest.translate(commandStatements);
		System.out.println("Print queryTimeshiftInt: " +result);
		System.out.println("Print queryAttesaTimeshiftInt: " +comandSqlResult);
		assertEquals("Test operator TimeshiftInt", comandSqlResult, result);
	}
        
	@Test
	@Transactional
	public void currentDate() throws Exception {
            dbTableUtilityService.resetSchema("t");
    		
    		System.out.println("Il profilo attivo è " + environment.getActiveProfiles()[0]);

    		String comandSqlResult = "";
    		String profileActive = environment.getActiveProfiles()[0];
    		if (profileActive.equalsIgnoreCase("oracleSql")) {
    			comandSqlResult = "CREATE TABLE DS_r AS (SELECT current_date AS date_var FROM DUAL);";
    		} else if (profileActive.equalsIgnoreCase("sqlServer")) {
    			comandSqlResult = "SELECT t0.* INTO DS_r FROM (SELECT current_date AS date_var) t0;";
    		} else if (profileActive.equalsIgnoreCase("mySql")) {
    			comandSqlResult = "CREATE TABLE DS_r AS (SELECT current_date AS date_var FROM DUAL);";
    		} else if (profileActive.equalsIgnoreCase("postgreSql")) {
    			comandSqlResult = "CREATE TABLE DS_r AS (SELECT current_date AS date_var);";
    		}
            String commandStatements = "DS_r := current_date;"; 
            String result = translationUtilTest.translate(commandStatements);
            System.out.println("Print queryCurrentDate: " +result);
            System.out.println("Print queryAttesaCurrentDate: " +comandSqlResult);
            assertEquals("Test operator CurrentDate", comandSqlResult, result);
	}
        
	@Test
	@Transactional
	public void currentDateFilter() throws Exception {
            dbTableUtilityService.resetSchema("t");
            VtlDataset vtlDatasetFind = datasetRepository.findByNameIgnoreCase("DS_1");
            if (vtlDatasetFind == null) {
                    VtlDataset vtlDataset = new VtlDataset();

                    vtlDataset.setPersistent(true);
                    vtlDataset.setName("DS_21");
                    vtlDataset.setIsOnlyAScalar(false);
                    vtlDataset.setTransitory(false);
                    vtlDataset.addComponent(translationUtilTest.getVtlComponent("ID_1", VtlType.STRING, VtlComponentRole.IDENTIFIER));
                    vtlDataset.addComponent(translationUtilTest.getVtlComponent("ID_2", VtlType.TIME, VtlComponentRole.IDENTIFIER));
                    vtlDataset.addComponent(translationUtilTest.getVtlComponent("ME_1", VtlType.STRING, VtlComponentRole.MEASURE));
                    datasetRepository.save(vtlDataset);
            }
            
            System.out.println("Il profilo attivo è " + environment.getActiveProfiles()[0]);

    		String comandSqlResult = "";
    		String profileActive = environment.getActiveProfiles()[0];
    		if (profileActive.equalsIgnoreCase("oracleSql")) {
    			comandSqlResult = "CREATE TABLE DS_r AS (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,t0.ME_1 AS ME_1 FROM DS_21 t0 WHERE (t0.ID_2=current_date));CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);"; 
    		} else if (profileActive.equalsIgnoreCase("sqlServer")) {
    			comandSqlResult = "SELECT t1.* INTO DS_r FROM (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,t0.ME_1 AS ME_1 FROM DS_21 t0 WHERE (t0.ID_2=current_date)) t1;CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);"; 
    		} else if (profileActive.equalsIgnoreCase("mySql")) {
    			comandSqlResult = "CREATE TABLE DS_r AS (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,t0.ME_1 AS ME_1 FROM DS_21 t0 WHERE (t0.ID_2=current_date));CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);"; 
    		} else if (profileActive.equalsIgnoreCase("postgreSql")) {
    			comandSqlResult = "CREATE TABLE DS_r AS (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,t0.ME_1 AS ME_1 FROM DS_21 t0 WHERE (t0.ID_2=current_date));CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);"; 
    		}
            
            String commandStatements = "DS_r := DS_21 [ filter ID_2 = current_date ];";
            String result = translationUtilTest.translate(commandStatements);
            System.out.println("Print queryCurrentDateFilter: " +result);
            System.out.println("Print queryAttesaCurrentDateFilter: " +comandSqlResult);
            assertEquals("Test operator CurrentDateFilter", comandSqlResult, result);
	}
        
	@Test
	@Transactional
	public void currentDateCalc() throws Exception {
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
    			comandSqlResult = "CREATE TABLE DS_r AS (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,t0.ID_3 AS ID_3,t0.ME_1 AS ME_1,t0.ME_2 AS ME_2,current_date AS ME_3 FROM DS_34 t0);CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);CREATE INDEX DS_r_ID_3_IDX ON DS_r (ID_3);"; 
    		} else if (profileActive.equalsIgnoreCase("sqlServer")) {
    			comandSqlResult = "SELECT t1.* INTO DS_r FROM (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,t0.ID_3 AS ID_3,t0.ME_1 AS ME_1,t0.ME_2 AS ME_2,current_date AS ME_3 FROM DS_34 t0) t1;CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);CREATE INDEX DS_r_ID_3_IDX ON DS_r (ID_3);"; 
    		} else if (profileActive.equalsIgnoreCase("mySql")) {
    			comandSqlResult = "CREATE TABLE DS_r AS (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,t0.ID_3 AS ID_3,t0.ME_1 AS ME_1,t0.ME_2 AS ME_2,current_date AS ME_3 FROM DS_34 t0);CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);CREATE INDEX DS_r_ID_3_IDX ON DS_r (ID_3);"; 
    		} else if (profileActive.equalsIgnoreCase("postgreSql")) {
    			comandSqlResult = "CREATE TABLE DS_r AS (SELECT t0.ID_1 AS ID_1,t0.ID_2 AS ID_2,t0.ID_3 AS ID_3,t0.ME_1 AS ME_1,t0.ME_2 AS ME_2,current_date AS ME_3 FROM DS_34 t0);CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);CREATE INDEX DS_r_ID_3_IDX ON DS_r (ID_3);"; 
    		}
            
            String commandStatements = "DS_r := DS_34 [ calc ME_3 :=  current_date ];";
            String result = translationUtilTest.translate(commandStatements);
            System.out.println("Print queryCurrentDateCalc: " +result);
            System.out.println("Print queryAttesaCurrentDateCalc: " +comandSqlResult);
            assertEquals("Test operator CurrentDateCalc", comandSqlResult, result);
	}
        
	@Test
	@Transactional
	public void timeAggDataset() throws Exception {
            dbTableUtilityService.resetSchema("t");
            VtlDataset vtlDatasetFind = datasetRepository.findByNameIgnoreCase("DS_1");
            if (vtlDatasetFind == null) {
                    VtlDataset vtlDataset = new VtlDataset();

                    vtlDataset.setPersistent(true);
                    vtlDataset.setName("DS_35");
                    vtlDataset.setIsOnlyAScalar(false);
                    vtlDataset.setTransitory(false);
                    vtlDataset.addComponent(translationUtilTest.getVtlComponent("ID_1", VtlType.TIME, VtlComponentRole.IDENTIFIER));
                    vtlDataset.addComponent(translationUtilTest.getVtlComponent("ID_2", VtlType.STRING, VtlComponentRole.IDENTIFIER));
                    vtlDataset.addComponent(translationUtilTest.getVtlComponent("ME_1", VtlType.INTEGER, VtlComponentRole.MEASURE));
                    datasetRepository.save(vtlDataset);
            }
            
            System.out.println("Il profilo attivo è " + environment.getActiveProfiles()[0]);

            String comandSqlResult = "";
            String profileActive = environment.getActiveProfiles()[0];
            if (profileActive.equalsIgnoreCase("oracleSql")) {
                    comandSqlResult = "CREATE TABLE DS_r AS (SELECT vtl_pkg.time_agg(coalesce(t0.ID_1,'NULL'),'A',NULL,NULL) AS ID_1,t0.ID_2 AS ID_2,t0.ME_1 AS ME_1 FROM DS_35 t0);CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);"; 
            } else if (profileActive.equalsIgnoreCase("sqlServer")) {
                    comandSqlResult = "SELECT t1.* INTO DS_r FROM (SELECT dbo.vtl_time_agg(coalesce(t0.ID_1,'NULL'),'A',NULL,NULL) AS ID_1,t0.ID_2 AS ID_2,t0.ME_1 AS ME_1 FROM DS_35 t0) t1;CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);"; 
            } else if (profileActive.equalsIgnoreCase("mySql")) {
                    comandSqlResult = "CREATE TABLE DS_r AS (SELECT vtl_time_agg(coalesce(t0.ID_1,'NULL'),'A',NULL,NULL) AS ID_1,t0.ID_2 AS ID_2,t0.ME_1 AS ME_1 FROM DS_35 t0);CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);"; 
            } else if (profileActive.equalsIgnoreCase("postgreSql")) {
                    comandSqlResult = "CREATE TABLE DS_r AS (SELECT vtl_time_agg(coalesce(t0.ID_1,'NULL'),'A',NULL,NULL) AS ID_1,t0.ID_2 AS ID_2,t0.ME_1 AS ME_1 FROM DS_35 t0);CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);"; 
            }
            
            String commandStatements = "DS_r := time_agg(\"A\", _, DS_35);";
            String result = translationUtilTest.translate(commandStatements);
            System.out.println("Print queryTimeAggDataset: " +result);
            System.out.println("Print queryAttesaTimeAggDataset: " +comandSqlResult);
            assertEquals("Test operator TimeAggDataset", comandSqlResult, result);
	}
        
	@Test
	@Transactional
	public void timeAggSumGroupAll() throws Exception {
            dbTableUtilityService.resetSchema("t");
            VtlDataset vtlDatasetFind = datasetRepository.findByNameIgnoreCase("DS_1");
            if (vtlDatasetFind == null) {
                    VtlDataset vtlDataset = new VtlDataset();

                    vtlDataset.setPersistent(true);
                    vtlDataset.setName("DS_35");
                    vtlDataset.setIsOnlyAScalar(false);
                    vtlDataset.setTransitory(false);
                    vtlDataset.addComponent(translationUtilTest.getVtlComponent("ID_1", VtlType.TIME, VtlComponentRole.IDENTIFIER));
                    vtlDataset.addComponent(translationUtilTest.getVtlComponent("ID_2", VtlType.STRING, VtlComponentRole.IDENTIFIER));
                    vtlDataset.addComponent(translationUtilTest.getVtlComponent("ME_1", VtlType.INTEGER, VtlComponentRole.MEASURE));
                    datasetRepository.save(vtlDataset);
            }
            
            System.out.println("Il profilo attivo è " + environment.getActiveProfiles()[0]);

            String comandSqlResult = "";
            String profileActive = environment.getActiveProfiles()[0];
            if (profileActive.equalsIgnoreCase("oracleSql")) {
                    comandSqlResult = "CREATE TABLE DS_r AS (SELECT vtl_pkg.time_agg(coalesce(t0.ID_1,'NULL'),'A',NULL,NULL) AS ID_1,t0.ID_2 AS ID_2,sum(t0.ME_1) AS ME_1 FROM DS_35 t0 GROUP BY vtl_pkg.time_agg(coalesce(t0.ID_1,'NULL'),'A',NULL,NULL),t0.ID_2);CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);"; 
            } else if (profileActive.equalsIgnoreCase("sqlServer")) {
                    comandSqlResult = "SELECT t1.* INTO DS_r FROM (SELECT dbo.vtl_time_agg(coalesce(t0.ID_1,'NULL'),'A',NULL,NULL) AS ID_1,t0.ID_2 AS ID_2,sum(t0.ME_1) AS ME_1 FROM DS_35 t0 GROUP BY dbo.vtl_time_agg(coalesce(t0.ID_1,'NULL'),'A',NULL,NULL),t0.ID_2) t1;CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);"; 
            } else if (profileActive.equalsIgnoreCase("mySql")) {
                    comandSqlResult = "CREATE TABLE DS_r AS (SELECT vtl_time_agg(coalesce(t0.ID_1,'NULL'),'A',NULL,NULL) AS ID_1,t0.ID_2 AS ID_2,sum(t0.ME_1) AS ME_1 FROM DS_35 t0 GROUP BY vtl_time_agg(coalesce(t0.ID_1,'NULL'),'A',NULL,NULL),t0.ID_2);CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);"; 
            } else if (profileActive.equalsIgnoreCase("postgreSql")) {
                    comandSqlResult = "CREATE TABLE DS_r AS (SELECT vtl_time_agg(coalesce(t0.ID_1,'NULL'),'A',NULL,NULL) AS ID_1,t0.ID_2 AS ID_2,sum(t0.ME_1) AS ME_1 FROM DS_35 t0 GROUP BY vtl_time_agg(coalesce(t0.ID_1,'NULL'),'A',NULL,NULL),t0.ID_2);CREATE INDEX DS_r_ID_1_IDX ON DS_r (ID_1);CREATE INDEX DS_r_ID_2_IDX ON DS_r (ID_2);"; 
            }
            
            String commandStatements = "DS_r := sum ( DS_35  group all time_agg ( \"A\" , _ , ID_1 ));";
            String result = translationUtilTest.translate(commandStatements);
            System.out.println("Print queryTimeAggSumGroupAll: " +result);
            System.out.println("Print queryAttesaTimeAggSumGroupAll: " +comandSqlResult);
            assertEquals("Test operator TimeAggSumGroupAll", comandSqlResult, result);
	}        
}
