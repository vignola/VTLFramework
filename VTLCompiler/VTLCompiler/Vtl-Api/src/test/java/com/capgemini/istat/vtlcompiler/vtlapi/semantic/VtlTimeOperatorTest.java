package com.capgemini.istat.vtlcompiler.vtlapi.semantic;

import com.capgemini.istat.vtlcompiler.vtlapi.utils.VtlTestUtils;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.dataset.VtlComponent;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.dataset.VtlComponentRole;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.dataset.VtlDataset;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.dataset.VtlType;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.semantic.ResultExpression;
import com.capgemini.istat.vtlcompiler.vtlcommon.repository.DatasetRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest

public class VtlTimeOperatorTest {

	private VtlTestUtils vtlTestUtils;
	private DatasetRepository datasetRepository;

	@Autowired
	public void setVtlTestUtils(VtlTestUtils vtlTestUtils) {
		this.vtlTestUtils = vtlTestUtils;
	}

	@Autowired
	public void setDatasetRepository(DatasetRepository datasetRepository) {
		this.datasetRepository = datasetRepository;
	}

	@Test
	@Transactional
	public void periodIndicator() throws Exception {
		VtlDataset vtlDatasetFind = datasetRepository.findByNameIgnoreCase("DS_1");
		if (vtlDatasetFind == null) {
			VtlDataset vtlDataset = new VtlDataset();

			vtlDataset.setPersistent(true);
			vtlDataset.setName("DS_39");
			vtlDataset.setIsOnlyAScalar(false);
			vtlDataset.setTransitory(false);
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_1", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_2", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(
					vtlTestUtils.getVtlComponent("ID_3", VtlType.TIME_PERIOD, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_1", VtlType.INTEGER, VtlComponentRole.MEASURE));
			datasetRepository.save(vtlDataset);

		}
		String commandStatements = "DS_r := period_indicator ( DS_39 );";
		String jsonResult = "[{\"result\":{\"id\":58,\"name\":\"DS_r\",\"description\":null,\"attributes\":[],\"persistent\":false,\"identifiers\":[{\"id\":207,\"type\":\"STRING\",\"name\":\"ID_1\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":208,\"type\":\"INTEGER\",\"name\":\"ID_2\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":209,\"type\":\"TIME_PERIOD\",\"name\":\"ID_3\",\"domainValue\":\"time_period_vd\",\"domainValueParent\":\"time_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}],\"viral\":[],\"measures\":[{\"id\":206,\"type\":\"DURATION\",\"name\":\"duration_var\",\"domainValue\":\"duration_vd\",\"domainValueParent\":\"duration_vd\",\"vtlComponentRole\":\"MEASURE\"}]},\"messages\":[],\"command\":\"DS_r:=period_indicator(DS_39);\",\"ascalar\":false,\"acomponent\":false},{\"result\":{\"id\":56,\"name\":\"temporary_55\",\"description\":null,\"attributes\":[],\"persistent\":false,\"identifiers\":[{\"id\":203,\"type\":\"STRING\",\"name\":\"ID_1\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":204,\"type\":\"INTEGER\",\"name\":\"ID_2\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":205,\"type\":\"TIME_PERIOD\",\"name\":\"ID_3\",\"domainValue\":\"time_period_vd\",\"domainValueParent\":\"time_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}],\"viral\":[],\"measures\":[{\"id\":202,\"type\":\"DURATION\",\"name\":\"duration_var\",\"domainValue\":\"duration_vd\",\"domainValueParent\":\"duration_vd\",\"vtlComponentRole\":\"MEASURE\"}]},\"messages\":[],\"command\":\"period_indicator(DS_39)\",\"ascalar\":false,\"acomponent\":false},{\"result\":{\"id\":39,\"name\":\"DS_39\",\"description\":null,\"attributes\":[],\"persistent\":true,\"identifiers\":[{\"id\":158,\"type\":\"STRING\",\"name\":\"ID_1\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":159,\"type\":\"INTEGER\",\"name\":\"ID_2\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":160,\"type\":\"TIME_PERIOD\",\"name\":\"ID_3\",\"domainValue\":\"time_period_vd\",\"domainValueParent\":\"time_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}],\"viral\":[],\"measures\":[{\"id\":161,\"type\":\"INTEGER\",\"name\":\"ME_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"}]},\"messages\":[],\"command\":\"DS_39\",\"ascalar\":false,\"acomponent\":false}]";
		List<ResultExpression> result = vtlTestUtils.getResult(commandStatements);
		List<ResultExpression> resultExpressionsToComapare = vtlTestUtils.convertJsonToResultExpression(jsonResult);
		assertNotNull("Conversione non riuscita", resultExpressionsToComapare);
		assertNotNull("Validazione Semantica non riuscita", result);
		assertTrue("I due risultati non sono uguali",
				vtlTestUtils.compareResult(result.get(0), resultExpressionsToComapare.get(0)));

	}

	@Test
	@Transactional
	public void periodIndicatorComponent() throws Exception {
		VtlDataset vtlDatasetFind = datasetRepository.findByNameIgnoreCase("DS_1");
		if (vtlDatasetFind == null) {
			VtlDataset vtlDataset = new VtlDataset();

			vtlDataset.setPersistent(true);
			vtlDataset.setName("DS_39");
			vtlDataset.setIsOnlyAScalar(false);
			vtlDataset.setTransitory(false);
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_1", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_2", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(
					vtlTestUtils.getVtlComponent("ID_3", VtlType.TIME_PERIOD, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_1", VtlType.INTEGER, VtlComponentRole.MEASURE));
			datasetRepository.save(vtlDataset);

		}
        String commandStatements = "DS_r := DS_39 [ filter period_indicator ( Id_3 ) = \"A\" ];";
		String jsonResult = "[{\"result\":{\"id\":25,\"name\":\"DS_r\",\"description\":null,\"attributes\":[],\"persistent\":false,\"identifiers\":[{\"id\":32,\"type\":\"STRING\",\"name\":\"ID_1\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":33,\"type\":\"INTEGER\",\"name\":\"ID_2\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":34,\"type\":\"TIME_PERIOD\",\"name\":\"ID_3\",\"domainValue\":\"time_period_vd\",\"domainValueParent\":\"time_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}],\"measures\":[{\"id\":35,\"type\":\"INTEGER\",\"name\":\"ME_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"viral\":[]},\"messages\":[],\"command\":\"DS_r:=DS_39[filterperiod_indicator(Id_3)=\\\"A\\\"]<missing ';'>\",\"acomponent\":false,\"ascalar\":false},{\"result\":{\"id\":25,\"name\":\"DS_r\",\"description\":null,\"attributes\":[],\"persistent\":false,\"identifiers\":[{\"id\":32,\"type\":\"STRING\",\"name\":\"ID_1\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":33,\"type\":\"INTEGER\",\"name\":\"ID_2\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":34,\"type\":\"TIME_PERIOD\",\"name\":\"ID_3\",\"domainValue\":\"time_period_vd\",\"domainValueParent\":\"time_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}],\"measures\":[{\"id\":35,\"type\":\"INTEGER\",\"name\":\"ME_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"viral\":[]},\"messages\":[],\"acomponent\":false,\"ascalar\":false},{\"resultComponent\":{\"id\":null,\"type\":\"BOOLEAN\",\"name\":\"bool_var\",\"domainValue\":\"bool_vd\",\"domainValueParent\":\"bool_vd\",\"vtlComponentRole\":\"MEASURE\"},\"messages\":[],\"command\":\"period_indicator(Id_3)=\\\"A\\\"\",\"acomponent\":true,\"ascalar\":false},{\"resultComponent\":{\"id\":null,\"type\":\"DURATION\",\"name\":\"duration_var\",\"domainValue\":\"duration_vd\",\"domainValueParent\":\"duration_vd\",\"vtlComponentRole\":\"MEASURE\"},\"messages\":[],\"command\":\"period_indicator(Id_3)\",\"acomponent\":true,\"ascalar\":false},{\"resultComponent\":{\"id\":34,\"type\":\"TIME_PERIOD\",\"name\":\"ID_3\",\"domainValue\":\"time_period_vd\",\"domainValueParent\":\"time_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},\"messages\":[],\"command\":\"Id_3\",\"acomponent\":true,\"ascalar\":false},{\"result\":{\"id\":23,\"name\":\"temporary_22\",\"description\":null,\"attributes\":[],\"persistent\":false,\"identifiers\":[],\"measures\":[{\"id\":47,\"type\":\"STRING\",\"name\":\"string_var\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"viral\":[]},\"resultComponent\":{\"id\":47,\"type\":\"STRING\",\"name\":\"string_var\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"MEASURE\"},\"messages\":[],\"command\":\"\\\"A\\\"\",\"acomponent\":false,\"ascalar\":true,\"scalarComponent\":{\"id\":47,\"type\":\"STRING\",\"name\":\"string_var\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"MEASURE\"}},{\"result\":{\"id\":8,\"name\":\"DS_39\",\"description\":null,\"attributes\":[],\"persistent\":true,\"identifiers\":[{\"id\":32,\"type\":\"STRING\",\"name\":\"ID_1\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":33,\"type\":\"INTEGER\",\"name\":\"ID_2\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":34,\"type\":\"TIME_PERIOD\",\"name\":\"ID_3\",\"domainValue\":\"time_period_vd\",\"domainValueParent\":\"time_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}],\"measures\":[{\"id\":35,\"type\":\"INTEGER\",\"name\":\"ME_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"viral\":[]},\"messages\":[],\"command\":\"DS_39\",\"acomponent\":false,\"ascalar\":false}]";
		List<ResultExpression> result = vtlTestUtils.getResult(commandStatements);
		List<ResultExpression> resultExpressionsToComapare = vtlTestUtils.convertJsonToResultExpression(jsonResult);
		assertNotNull("Conversione non riuscita", resultExpressionsToComapare);
		assertNotNull("Validazione Semantica non riuscita", result);
		assertTrue("I due risultati non sono uguali",
				vtlTestUtils.compareResult(result.get(0), resultExpressionsToComapare.get(0)));

	}

	@Test
	@Transactional
	public void fillTimeSeriesSingle() throws Exception {
		VtlDataset vtlDatasetFind = datasetRepository.findByNameIgnoreCase("DS_1");
		if (vtlDatasetFind == null) {
			VtlDataset vtlDataset = new VtlDataset();

			vtlDataset.setPersistent(true);
			vtlDataset.setName("DS_21");
			vtlDataset.setIsOnlyAScalar(false);
			vtlDataset.setTransitory(false);
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_1", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_2", VtlType.TIME, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_1", VtlType.STRING, VtlComponentRole.MEASURE));
			datasetRepository.save(vtlDataset);
		}
		String commandStatements = "DS_r := fill_time_series ( DS_21, single );";
		String jsonResult = "[{\"result\":{\"id\":62,\"name\":\"DS_r\",\"description\":null,\"attributes\":[],\"persistent\":false,\"identifiers\":[{\"id\":214,\"type\":\"STRING\",\"name\":\"ID_1\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":215,\"type\":\"TIME\",\"name\":\"ID_2\",\"domainValue\":\"time_vd\",\"domainValueParent\":\"time_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}],\"viral\":[],\"measures\":[{\"id\":213,\"type\":\"STRING\",\"name\":\"ME_1\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"MEASURE\"}]},\"messages\":[],\"command\":\"DS_r:=fill_time_series(DS_21,single);\",\"ascalar\":false,\"acomponent\":false},{\"result\":{\"id\":60,\"name\":\"temporary_59\",\"description\":null,\"attributes\":[],\"persistent\":false,\"identifiers\":[{\"id\":211,\"type\":\"STRING\",\"name\":\"ID_1\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":212,\"type\":\"TIME\",\"name\":\"ID_2\",\"domainValue\":\"time_vd\",\"domainValueParent\":\"time_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}],\"viral\":[],\"measures\":[{\"id\":210,\"type\":\"STRING\",\"name\":\"ME_1\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"MEASURE\"}]},\"messages\":[],\"command\":\"fill_time_series(DS_21,single)\",\"ascalar\":false,\"acomponent\":false},{\"result\":{\"id\":21,\"name\":\"DS_21\",\"description\":null,\"attributes\":[],\"persistent\":true,\"identifiers\":[{\"id\":84,\"type\":\"STRING\",\"name\":\"ID_1\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":85,\"type\":\"TIME\",\"name\":\"ID_2\",\"domainValue\":\"time_vd\",\"domainValueParent\":\"time_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}],\"viral\":[],\"measures\":[{\"id\":86,\"type\":\"STRING\",\"name\":\"ME_1\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"MEASURE\"}]},\"messages\":[],\"command\":\"DS_21\",\"ascalar\":false,\"acomponent\":false}]";
		List<ResultExpression> result = vtlTestUtils.getResult(commandStatements);
		List<ResultExpression> resultExpressionsToComapare = vtlTestUtils.convertJsonToResultExpression(jsonResult);
		assertNotNull("Conversione non riuscita", resultExpressionsToComapare);
		assertNotNull("Validazione Semantica non riuscita", result);
		assertTrue("I due risultati non sono uguali",
				vtlTestUtils.compareResult(result.get(0), resultExpressionsToComapare.get(0)));

	}

	@Test
	@Transactional
	public void fillTimeSeriesAll() throws Exception {
		VtlDataset vtlDatasetFind = datasetRepository.findByNameIgnoreCase("DS_1");
		if (vtlDatasetFind == null) {
			VtlDataset vtlDataset = new VtlDataset();

			vtlDataset.setPersistent(true);
			vtlDataset.setName("DS_21");
			vtlDataset.setIsOnlyAScalar(false);
			vtlDataset.setTransitory(false);

			List<VtlComponent> vtlComponents = new ArrayList<VtlComponent>();
			VtlComponent vtlComponent;
			vtlComponents = new ArrayList<>();

			vtlComponent = new VtlComponent();
			vtlComponent.setName("ID_1");
			vtlComponent.setType(VtlType.STRING);
			vtlComponent.setDomainValue(VtlType.STRING.getDomainValue());
			vtlComponent.setDomainValueParent(VtlType.STRING.getDomainValueParent());
			vtlComponent.setVtlComponentRole(VtlComponentRole.IDENTIFIER);
			vtlComponent.setVtlDataset(vtlDataset);
			vtlComponents.add(vtlComponent);

			vtlComponent = new VtlComponent();
			vtlComponent.setName("ID_2");
			vtlComponent.setType(VtlType.TIME);
			vtlComponent.setDomainValue(VtlType.TIME.getDomainValue());
			vtlComponent.setDomainValueParent(VtlType.TIME.getDomainValueParent());
			vtlComponent.setVtlComponentRole(VtlComponentRole.IDENTIFIER);
			vtlComponent.setVtlDataset(vtlDataset);
			vtlComponents.add(vtlComponent);

			vtlComponent = new VtlComponent();
			vtlComponent.setName("ME_1");
			vtlComponent.setType(VtlType.STRING);
			vtlComponent.setDomainValue(VtlType.STRING.getDomainValue());
			vtlComponent.setDomainValueParent(VtlType.STRING.getDomainValueParent());
			vtlComponent.setVtlComponentRole(VtlComponentRole.MEASURE);
			vtlComponent.setVtlDataset(vtlDataset);
			vtlComponents.add(vtlComponent);

			vtlDataset.addComponentsList(vtlComponents);
			datasetRepository.save(vtlDataset);
		}
		String commandStatements = "DS_r := fill_time_series ( DS_21, all );";
		String jsonResult = "[{\"result\":{\"id\":24,\"name\":\"DS_r\",\"description\":null,\"attributes\":[],\"persistent\":false,\"identifiers\":[{\"id\":1,\"type\":\"STRING\",\"name\":\"ID_1\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":2,\"type\":\"TIME\",\"name\":\"ID_2\",\"domainValue\":\"time_vd\",\"domainValueParent\":\"time_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}],\"measures\":[{\"id\":84,\"type\":\"STRING\",\"name\":\"ME_1\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"viral\":[]},\"messages\":[],\"command\":\"DS_r:=fill_time_series(DS_21,all);\",\"acomponent\":false,\"ascalar\":false},{\"result\":{\"id\":24,\"name\":\"DS_r\",\"description\":null,\"attributes\":[],\"persistent\":false,\"identifiers\":[{\"id\":1,\"type\":\"STRING\",\"name\":\"ID_1\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":2,\"type\":\"TIME\",\"name\":\"ID_2\",\"domainValue\":\"time_vd\",\"domainValueParent\":\"time_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}],\"measures\":[{\"id\":84,\"type\":\"STRING\",\"name\":\"ME_1\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"viral\":[]},\"messages\":[],\"command\":\"fill_time_series(DS_21,all)\",\"acomponent\":false,\"ascalar\":false},{\"result\":{\"id\":1,\"name\":\"DS_21\",\"description\":null,\"attributes\":[],\"persistent\":true,\"identifiers\":[{\"id\":1,\"type\":\"STRING\",\"name\":\"ID_1\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":2,\"type\":\"TIME\",\"name\":\"ID_2\",\"domainValue\":\"time_vd\",\"domainValueParent\":\"time_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}],\"measures\":[{\"id\":3,\"type\":\"STRING\",\"name\":\"ME_1\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"viral\":[]},\"messages\":[],\"command\":\"DS_21\",\"acomponent\":false,\"ascalar\":false}]";
		List<ResultExpression> result = vtlTestUtils.getResult(commandStatements);
		List<ResultExpression> resultExpressionsToComapare = vtlTestUtils.convertJsonToResultExpression(jsonResult);
		assertNotNull("Conversione non riuscita", resultExpressionsToComapare);
		assertNotNull("Validazione Semantica non riuscita", result);
		assertTrue("I due risultati non sono uguali",
				vtlTestUtils.compareResult(result.get(0), resultExpressionsToComapare.get(0)));

	}

	@Test
	@Transactional
	public void flowToStock() throws Exception {
		VtlDataset vtlDatasetFind = datasetRepository.findByNameIgnoreCase("DS_1");
		if (vtlDatasetFind == null) {
			VtlDataset vtlDataset = new VtlDataset();

			vtlDataset.setPersistent(true);
			vtlDataset.setName("DS_23");
			vtlDataset.setIsOnlyAScalar(false);
			vtlDataset.setTransitory(false);
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_1", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_2", VtlType.TIME, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_1", VtlType.INTEGER, VtlComponentRole.MEASURE));
			datasetRepository.save(vtlDataset);
		}
		String commandStatements = "DS_r := flow_to_stock ( DS_23 );";
		String jsonResult = "[{\"result\":{\"id\":22,\"name\":\"DS_r\",\"description\":null,\"attributes\":[],\"persistent\":false,\"identifiers\":[{\"id\":1,\"type\":\"STRING\",\"name\":\"ID_1\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":2,\"type\":\"TIME\",\"name\":\"ID_2\",\"domainValue\":\"time_vd\",\"domainValueParent\":\"time_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}],\"measures\":[{\"id\":78,\"type\":\"INTEGER\",\"name\":\"ME_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"viral\":[]},\"messages\":[],\"command\":\"DS_r:=flow_to_stock(DS_23);\",\"acomponent\":false,\"ascalar\":false},{\"result\":{\"id\":22,\"name\":\"DS_r\",\"description\":null,\"attributes\":[],\"persistent\":false,\"identifiers\":[{\"id\":1,\"type\":\"STRING\",\"name\":\"ID_1\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":2,\"type\":\"TIME\",\"name\":\"ID_2\",\"domainValue\":\"time_vd\",\"domainValueParent\":\"time_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}],\"measures\":[{\"id\":78,\"type\":\"INTEGER\",\"name\":\"ME_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"viral\":[]},\"messages\":[],\"command\":\"flow_to_stock(DS_23)\",\"acomponent\":false,\"ascalar\":false},{\"result\":{\"id\":1,\"name\":\"DS_23\",\"description\":null,\"attributes\":[],\"persistent\":true,\"identifiers\":[{\"id\":1,\"type\":\"STRING\",\"name\":\"ID_1\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":2,\"type\":\"TIME\",\"name\":\"ID_2\",\"domainValue\":\"time_vd\",\"domainValueParent\":\"time_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}],\"measures\":[{\"id\":3,\"type\":\"INTEGER\",\"name\":\"ME_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"viral\":[]},\"messages\":[],\"command\":\"DS_23\",\"acomponent\":false,\"ascalar\":false}]";
		List<ResultExpression> result = vtlTestUtils.getResult(commandStatements);
		List<ResultExpression> resultExpressionsToComapare = vtlTestUtils.convertJsonToResultExpression(jsonResult);
		assertNotNull("Conversione non riuscita", resultExpressionsToComapare);
		assertNotNull("Validazione Semantica non riuscita", result);
		assertTrue("I due risultati non sono uguali",
				vtlTestUtils.compareResult(result.get(0), resultExpressionsToComapare.get(0)));

	}

	@Test
	@Transactional
	public void flowToStock2() throws Exception {
		VtlDataset vtlDatasetFind = datasetRepository.findByNameIgnoreCase("DS_1");
		if (vtlDatasetFind == null) {
			VtlDataset vtlDataset = new VtlDataset();

			vtlDataset.setPersistent(true);
			vtlDataset.setName("DS_24");
			vtlDataset.setIsOnlyAScalar(false);
			vtlDataset.setTransitory(false);
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_1", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_2", VtlType.TIME, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_1", VtlType.INTEGER, VtlComponentRole.MEASURE));
			datasetRepository.save(vtlDataset);
		}
		String commandStatements = "DS_r := flow_to_stock ( DS_24 );";
		String jsonResult = "[{\"result\":{\"id\":66,\"name\":\"DS_r\",\"description\":null,\"attributes\":[],\"persistent\":false,\"identifiers\":[{\"id\":220,\"type\":\"STRING\",\"name\":\"ID_1\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":221,\"type\":\"TIME\",\"name\":\"ID_2\",\"domainValue\":\"time_vd\",\"domainValueParent\":\"time_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}],\"viral\":[],\"measures\":[{\"id\":219,\"type\":\"INTEGER\",\"name\":\"ME_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"}]},\"messages\":[],\"command\":\"DS_r:=flow_to_stock(DS_24);\",\"ascalar\":false,\"acomponent\":false},{\"result\":{\"id\":64,\"name\":\"temporary_63\",\"description\":null,\"attributes\":[],\"persistent\":false,\"identifiers\":[{\"id\":217,\"type\":\"STRING\",\"name\":\"ID_1\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":218,\"type\":\"TIME\",\"name\":\"ID_2\",\"domainValue\":\"time_vd\",\"domainValueParent\":\"time_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}],\"viral\":[],\"measures\":[{\"id\":216,\"type\":\"INTEGER\",\"name\":\"ME_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"}]},\"messages\":[],\"command\":\"flow_to_stock(DS_24)\",\"ascalar\":false,\"acomponent\":false},{\"result\":{\"id\":24,\"name\":\"DS_24\",\"description\":null,\"attributes\":[],\"persistent\":true,\"identifiers\":[{\"id\":93,\"type\":\"STRING\",\"name\":\"ID_1\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":94,\"type\":\"TIME\",\"name\":\"ID_2\",\"domainValue\":\"time_vd\",\"domainValueParent\":\"time_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}],\"viral\":[],\"measures\":[{\"id\":95,\"type\":\"INTEGER\",\"name\":\"ME_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"}]},\"messages\":[],\"command\":\"DS_24\",\"ascalar\":false,\"acomponent\":false}]";
		List<ResultExpression> result = vtlTestUtils.getResult(commandStatements);
		List<ResultExpression> resultExpressionsToComapare = vtlTestUtils.convertJsonToResultExpression(jsonResult);
		assertNotNull("Conversione non riuscita", resultExpressionsToComapare);
		assertNotNull("Validazione Semantica non riuscita", result);
		assertTrue("I due risultati non sono uguali",
				vtlTestUtils.compareResult(result.get(0), resultExpressionsToComapare.get(0)));

	}

	@Test
	@Transactional
	public void stockToFlow() throws Exception {
		VtlDataset vtlDatasetFind = datasetRepository.findByNameIgnoreCase("DS_1");
		if (vtlDatasetFind == null) {
			VtlDataset vtlDataset = new VtlDataset();

			vtlDataset.setPersistent(true);
			vtlDataset.setName("DS_23");
			vtlDataset.setIsOnlyAScalar(false);
			vtlDataset.setTransitory(false);
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_1", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_2", VtlType.TIME, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_1", VtlType.INTEGER, VtlComponentRole.MEASURE));
			datasetRepository.save(vtlDataset);
		}
		String commandStatements = "DS_r := stock_to_flow ( DS_23 );";
		String jsonResult = "[{\"result\":{\"id\":70,\"name\":\"DS_r\",\"description\":null,\"attributes\":[],\"persistent\":false,\"identifiers\":[{\"id\":226,\"type\":\"STRING\",\"name\":\"ID_1\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":227,\"type\":\"TIME\",\"name\":\"ID_2\",\"domainValue\":\"time_vd\",\"domainValueParent\":\"time_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}],\"viral\":[],\"measures\":[{\"id\":225,\"type\":\"INTEGER\",\"name\":\"ME_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"}]},\"messages\":[],\"command\":\"DS_r:=stock_to_flow(DS_23);\",\"ascalar\":false,\"acomponent\":false},{\"result\":{\"id\":68,\"name\":\"temporary_67\",\"description\":null,\"attributes\":[],\"persistent\":false,\"identifiers\":[{\"id\":223,\"type\":\"STRING\",\"name\":\"ID_1\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":224,\"type\":\"TIME\",\"name\":\"ID_2\",\"domainValue\":\"time_vd\",\"domainValueParent\":\"time_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}],\"viral\":[],\"measures\":[{\"id\":222,\"type\":\"INTEGER\",\"name\":\"ME_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"}]},\"messages\":[],\"command\":\"stock_to_flow(DS_23)\",\"ascalar\":false,\"acomponent\":false},{\"result\":{\"id\":23,\"name\":\"DS_23\",\"description\":null,\"attributes\":[],\"persistent\":true,\"identifiers\":[{\"id\":90,\"type\":\"STRING\",\"name\":\"ID_1\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":91,\"type\":\"TIME\",\"name\":\"ID_2\",\"domainValue\":\"time_vd\",\"domainValueParent\":\"time_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}],\"viral\":[],\"measures\":[{\"id\":92,\"type\":\"INTEGER\",\"name\":\"ME_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"}]},\"messages\":[],\"command\":\"DS_23\",\"ascalar\":false,\"acomponent\":false}]";
		List<ResultExpression> result = vtlTestUtils.getResult(commandStatements);
		List<ResultExpression> resultExpressionsToComapare = vtlTestUtils.convertJsonToResultExpression(jsonResult);
		assertNotNull("Conversione non riuscita", resultExpressionsToComapare);
		assertNotNull("Validazione Semantica non riuscita", result);
		assertTrue("I due risultati non sono uguali",
				vtlTestUtils.compareResult(result.get(0), resultExpressionsToComapare.get(0)));

	}

	@Test
	@Transactional
	public void timeshift() throws Exception {
		VtlDataset vtlDatasetFind = datasetRepository.findByNameIgnoreCase("DS_1");
		if (vtlDatasetFind == null) {
			VtlDataset vtlDataset = new VtlDataset();

			vtlDataset.setPersistent(true);
			vtlDataset.setName("DS_21");
			vtlDataset.setIsOnlyAScalar(false);
			vtlDataset.setTransitory(false);
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_1", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_2", VtlType.TIME, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_1", VtlType.STRING, VtlComponentRole.MEASURE));
			datasetRepository.save(vtlDataset);
		}
		String commandStatements = "DS_r := timeshift ( DS_21 , -1  );";
		String jsonResult = "[{\"result\":{\"id\":76,\"name\":\"DS_r\",\"description\":null,\"attributes\":[],\"persistent\":false,\"identifiers\":[{\"id\":233,\"type\":\"STRING\",\"name\":\"ID_1\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":234,\"type\":\"TIME\",\"name\":\"ID_2\",\"domainValue\":\"time_vd\",\"domainValueParent\":\"time_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}],\"viral\":[],\"measures\":[{\"id\":232,\"type\":\"STRING\",\"name\":\"ME_1\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"MEASURE\"}]},\"messages\":[],\"command\":\"DS_r:=timeshift(DS_21,-1);\",\"ascalar\":false,\"acomponent\":false},{\"result\":{\"id\":74,\"name\":\"temporary_73\",\"description\":null,\"attributes\":[],\"persistent\":false,\"identifiers\":[{\"id\":230,\"type\":\"STRING\",\"name\":\"ID_1\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":231,\"type\":\"TIME\",\"name\":\"ID_2\",\"domainValue\":\"time_vd\",\"domainValueParent\":\"time_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}],\"viral\":[],\"measures\":[{\"id\":229,\"type\":\"STRING\",\"name\":\"ME_1\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"MEASURE\"}]},\"messages\":[],\"command\":\"timeshift(DS_21,-1)\",\"ascalar\":false,\"acomponent\":false},{\"result\":{\"id\":21,\"name\":\"DS_21\",\"description\":null,\"attributes\":[],\"persistent\":true,\"identifiers\":[{\"id\":84,\"type\":\"STRING\",\"name\":\"ID_1\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":85,\"type\":\"TIME\",\"name\":\"ID_2\",\"domainValue\":\"time_vd\",\"domainValueParent\":\"time_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}],\"viral\":[],\"measures\":[{\"id\":86,\"type\":\"STRING\",\"name\":\"ME_1\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"MEASURE\"}]},\"messages\":[],\"command\":\"DS_21\",\"ascalar\":false,\"acomponent\":false},{\"result\":{\"id\":72,\"name\":\"temporary_71\",\"description\":null,\"attributes\":[],\"persistent\":false,\"identifiers\":[],\"viral\":[],\"measures\":[{\"id\":228,\"type\":\"INTEGER\",\"name\":\"int_var\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"}]},\"resultComponent\":{\"id\":228,\"type\":\"INTEGER\",\"name\":\"int_var\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},\"messages\":[],\"scalarComponent\":{\"id\":228,\"type\":\"INTEGER\",\"name\":\"int_var\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},\"ascalar\":true,\"acomponent\":false}]";
		List<ResultExpression> result = vtlTestUtils.getResult(commandStatements);
		List<ResultExpression> resultExpressionsToComapare = vtlTestUtils.convertJsonToResultExpression(jsonResult);
		assertNotNull("Conversione non riuscita", resultExpressionsToComapare);
		assertNotNull("Validazione Semantica non riuscita", result);
		assertTrue("I due risultati non sono uguali",
				vtlTestUtils.compareResult(result.get(0), resultExpressionsToComapare.get(0)));

	}

	@Test
	@Transactional
	public void timeshiftInt() throws Exception {
		VtlDataset vtlDatasetFind = datasetRepository.findByNameIgnoreCase("DS_1");
		if (vtlDatasetFind == null) {
			VtlDataset vtlDataset = new VtlDataset();

			vtlDataset.setPersistent(true);
			vtlDataset.setName("DS_22");
			vtlDataset.setIsOnlyAScalar(false);
			vtlDataset.setTransitory(false);
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_1", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_2", VtlType.TIME, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_1", VtlType.STRING, VtlComponentRole.MEASURE));
			datasetRepository.save(vtlDataset);

		}
		String commandStatements = "DS_r := timeshift ( DS_22 , 2  );";
		String jsonResult = "[{\"result\":{\"id\":82,\"name\":\"DS_r\",\"description\":null,\"attributes\":[],\"persistent\":false,\"identifiers\":[{\"id\":240,\"type\":\"STRING\",\"name\":\"ID_1\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":241,\"type\":\"TIME\",\"name\":\"ID_2\",\"domainValue\":\"time_vd\",\"domainValueParent\":\"time_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}],\"viral\":[],\"measures\":[{\"id\":239,\"type\":\"STRING\",\"name\":\"ME_1\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"MEASURE\"}]},\"messages\":[],\"command\":\"DS_r:=timeshift(DS_22,2);\",\"ascalar\":false,\"acomponent\":false},{\"result\":{\"id\":80,\"name\":\"temporary_79\",\"description\":null,\"attributes\":[],\"persistent\":false,\"identifiers\":[{\"id\":237,\"type\":\"STRING\",\"name\":\"ID_1\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":238,\"type\":\"TIME\",\"name\":\"ID_2\",\"domainValue\":\"time_vd\",\"domainValueParent\":\"time_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}],\"viral\":[],\"measures\":[{\"id\":236,\"type\":\"STRING\",\"name\":\"ME_1\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"MEASURE\"}]},\"messages\":[],\"command\":\"timeshift(DS_22,2)\",\"ascalar\":false,\"acomponent\":false},{\"result\":{\"id\":22,\"name\":\"DS_22\",\"description\":null,\"attributes\":[],\"persistent\":true,\"identifiers\":[{\"id\":87,\"type\":\"STRING\",\"name\":\"ID_1\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":88,\"type\":\"TIME\",\"name\":\"ID_2\",\"domainValue\":\"time_vd\",\"domainValueParent\":\"time_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}],\"viral\":[],\"measures\":[{\"id\":89,\"type\":\"STRING\",\"name\":\"ME_1\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"MEASURE\"}]},\"messages\":[],\"command\":\"DS_22\",\"ascalar\":false,\"acomponent\":false},{\"result\":{\"id\":78,\"name\":\"temporary_77\",\"description\":null,\"attributes\":[],\"persistent\":false,\"identifiers\":[],\"viral\":[],\"measures\":[{\"id\":235,\"type\":\"INTEGER\",\"name\":\"int_var\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"}]},\"resultComponent\":{\"id\":235,\"type\":\"INTEGER\",\"name\":\"int_var\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},\"messages\":[],\"scalarComponent\":{\"id\":235,\"type\":\"INTEGER\",\"name\":\"int_var\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},\"ascalar\":true,\"acomponent\":false}]";
		List<ResultExpression> result = vtlTestUtils.getResult(commandStatements);
		List<ResultExpression> resultExpressionsToComapare = vtlTestUtils.convertJsonToResultExpression(jsonResult);
		assertNotNull("Conversione non riuscita", resultExpressionsToComapare);
		assertNotNull("Validazione Semantica non riuscita", result);
		assertTrue("I due risultati non sono uguali",
				vtlTestUtils.compareResult(result.get(0), resultExpressionsToComapare.get(0)));

	}

	@Test
	@Transactional
        public void timeAgg() throws Exception { 
            VtlDataset vtlDatasetFind = datasetRepository.findByNameIgnoreCase("DS_1"); 
            if (vtlDatasetFind == null) { 
                VtlDataset vtlDataset = new VtlDataset();
	 
                vtlDataset.setPersistent(true); vtlDataset.setName("DS_35");
                vtlDataset.setIsOnlyAScalar(false); vtlDataset.setTransitory(false);
                vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_1", VtlType.TIME,
                VtlComponentRole.IDENTIFIER));
                vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_2", VtlType.STRING,
                VtlComponentRole.IDENTIFIER));
                vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_1", VtlType.INTEGER,
                VtlComponentRole.MEASURE)); datasetRepository.save(vtlDataset); 
            }

        String commandStatements = "DS_r := sum ( DS_35  group all time_agg ( \"A\" , _ , ID_1 ));";
            String jsonResult = "[ { \"result\": { \"id\": 55, \"name\": \"DS_r\", \"description\": null, \"attributes\": [], \"measures\": [ { \"id\": 190, \"type\": \"INTEGER\", \"name\": \"ME_1\", \"domainValue\": \"int_vd\", \"domainValueParent\": \"num_vd\", \"vtlComponentRole\": \"MEASURE\" } ], \"identifiers\": [ { \"id\": 188, \"type\": \"TIME\", \"name\": \"ID_1\", \"domainValue\": \"time_vd\", \"domainValueParent\": \"time_vd\", \"vtlComponentRole\": \"IDENTIFIER\" }, { \"id\": 189, \"type\": \"STRING\", \"name\": \"ID_2\", \"domainValue\": \"string_vd\", \"domainValueParent\": \"string_vd\", \"vtlComponentRole\": \"IDENTIFIER\" } ], \"viral\": [], \"persistent\": false }, \"messages\": [], \"command\": \"DS_r:=sum(DS_35groupalltime_agg(\\\"A\\\",_,ID_1))\", \"acomponent\": false, \"ascalar\": false, \"adataset\": true }, { \"result\": { \"id\": 51, \"name\": \"temporary_50\", \"description\": null, \"attributes\": [], \"measures\": [ { \"id\": 186, \"type\": \"INTEGER\", \"name\": \"ME_1\", \"domainValue\": \"int_vd\", \"domainValueParent\": \"num_vd\", \"vtlComponentRole\": \"MEASURE\" } ], \"identifiers\": [ { \"id\": 184, \"type\": \"TIME\", \"name\": \"ID_1\", \"domainValue\": \"time_vd\", \"domainValueParent\": \"time_vd\", \"vtlComponentRole\": \"IDENTIFIER\" }, { \"id\": 185, \"type\": \"STRING\", \"name\": \"ID_2\", \"domainValue\": \"string_vd\", \"domainValueParent\": \"string_vd\", \"vtlComponentRole\": \"IDENTIFIER\" } ], \"viral\": [], \"persistent\": false }, \"messages\": [], \"acomponent\": false, \"ascalar\": false, \"adataset\": true }, { \"result\": { \"id\": 35, \"name\": \"DS_35\", \"description\": null, \"attributes\": [], \"measures\": [ { \"id\": 146, \"type\": \"INTEGER\", \"name\": \"ME_1\", \"domainValue\": \"int_vd\", \"domainValueParent\": \"num_vd\", \"vtlComponentRole\": \"MEASURE\" } ], \"identifiers\": [ { \"id\": 144, \"type\": \"TIME\", \"name\": \"ID_1\", \"domainValue\": \"time_vd\", \"domainValueParent\": \"time_vd\", \"vtlComponentRole\": \"IDENTIFIER\" }, { \"id\": 145, \"type\": \"STRING\", \"name\": \"ID_2\", \"domainValue\": \"string_vd\", \"domainValueParent\": \"string_vd\", \"vtlComponentRole\": \"IDENTIFIER\" } ], \"viral\": [], \"persistent\": true }, \"messages\": [], \"command\": \"DS_35\", \"acomponent\": false, \"ascalar\": false, \"adataset\": true } ]";
	 
            List<ResultExpression> result = vtlTestUtils.getResult(commandStatements);

            List<ResultExpression> resultExpressionsToComapare =  vtlTestUtils.convertJsonToResultExpression(jsonResult);
            assertNotNull("Conversione non riuscita", resultExpressionsToComapare);
            assertNotNull("Validazione Semantica non riuscita", result);
            assertTrue("I due risultati non sono uguali", vtlTestUtils.compareResult(result.get(0), resultExpressionsToComapare.get(0)));
	 }

}
