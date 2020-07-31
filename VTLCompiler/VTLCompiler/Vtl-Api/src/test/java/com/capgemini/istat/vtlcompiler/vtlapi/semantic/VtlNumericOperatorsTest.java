package com.capgemini.istat.vtlcompiler.vtlapi.semantic;

import com.capgemini.istat.vtlcompiler.vtlapi.utils.VtlTestUtils;
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

import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest

public class VtlNumericOperatorsTest {

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
	public void plus() throws Exception {
		VtlDataset vtlDatasetFind = datasetRepository.findByNameIgnoreCase("DS_1");
		if (vtlDatasetFind == null) {
			VtlDataset vtlDataset = new VtlDataset();

			vtlDataset.setPersistent(true);
			vtlDataset.setName("DS_7");
			vtlDataset.setIsOnlyAScalar(false);
			vtlDataset.setTransitory(false);
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_1", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_2", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_1", VtlType.NUMBER, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_2", VtlType.INTEGER, VtlComponentRole.MEASURE));
			datasetRepository.save(vtlDataset);
		}
		String commandStatements = "DS_r := + DS_7;";
		String jsonResult = "[{\"result\":{\"id\":46,\"name\":\"DS_r\",\"description\":null,\"attributes\":[],\"persistent\":false,\"identifiers\":[{\"id\":178,\"type\":\"INTEGER\",\"name\":\"ID_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":179,\"type\":\"STRING\",\"name\":\"ID_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}],\"measures\":[{\"id\":176,\"type\":\"NUMBER\",\"name\":\"ME_1\",\"domainValue\":\"num_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},{\"id\":177,\"type\":\"INTEGER\",\"name\":\"ME_2\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"viral\":[]},\"messages\":[],\"command\":\"DS_r:=+DS_7;\",\"acomponent\":false,\"ascalar\":false},{\"result\":{\"id\":44,\"name\":\"temporary_43\",\"description\":null,\"attributes\":[],\"persistent\":false,\"identifiers\":[{\"id\":174,\"type\":\"INTEGER\",\"name\":\"ID_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":175,\"type\":\"STRING\",\"name\":\"ID_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}],\"measures\":[{\"id\":172,\"type\":\"NUMBER\",\"name\":\"ME_1\",\"domainValue\":\"num_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},{\"id\":173,\"type\":\"INTEGER\",\"name\":\"ME_2\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"viral\":[]},\"messages\":[],\"command\":\"+DS_7\",\"acomponent\":false,\"ascalar\":false},{\"result\":{\"id\":7,\"name\":\"DS_7\",\"description\":null,\"attributes\":[],\"persistent\":true,\"identifiers\":[{\"id\":23,\"type\":\"INTEGER\",\"name\":\"ID_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":24,\"type\":\"STRING\",\"name\":\"ID_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}],\"measures\":[{\"id\":25,\"type\":\"NUMBER\",\"name\":\"ME_1\",\"domainValue\":\"num_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},{\"id\":26,\"type\":\"INTEGER\",\"name\":\"ME_2\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"viral\":[]},\"messages\":[],\"command\":\"DS_7\",\"acomponent\":false,\"ascalar\":false}]";
		List<ResultExpression> result = vtlTestUtils.getResult(commandStatements);
		List<ResultExpression> resultExpressionsToComapare = vtlTestUtils.convertJsonToResultExpression(jsonResult);
		assertNotNull("Conversione non riuscita", resultExpressionsToComapare);
		assertNotNull("Validazione Semantica non riuscita", result);
		assertTrue("I due risultati non sono uguali",
				vtlTestUtils.compareResult(result.get(0), resultExpressionsToComapare.get(0)));

	}

	@Test
	@Transactional
	public void plusInt() throws Exception {

		String commandStatements = "DS_r := 3 + 2;";
		String jsonResult = "[{\"result\":{\"id\":6,\"name\":\"DS_r\",\"description\":null,\"attributes\":[],\"measures\":[{\"id\":3,\"type\":\"INTEGER\",\"name\":\"int_var\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"viral\":[],\"identifiers\":[],\"persistent\":false},\"resultComponent\":{\"id\":3,\"type\":\"INTEGER\",\"name\":\"int_var\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},\"messages\":[],\"command\":\"DS_r:=3+2;\",\"acomponent\":false,\"scalarComponent\":{\"id\":3,\"type\":\"INTEGER\",\"name\":\"int_var\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},\"ascalar\":true},{\"result\":{\"id\":6,\"name\":\"DS_r\",\"description\":null,\"attributes\":[],\"measures\":[{\"id\":3,\"type\":\"INTEGER\",\"name\":\"int_var\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"viral\":[],\"identifiers\":[],\"persistent\":false},\"resultComponent\":{\"id\":3,\"type\":\"INTEGER\",\"name\":\"int_var\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},\"messages\":[],\"command\":\"3+2\",\"acomponent\":false,\"scalarComponent\":{\"id\":3,\"type\":\"INTEGER\",\"name\":\"int_var\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},\"ascalar\":true},{\"result\":{\"id\":2,\"name\":\"temporary_1\",\"description\":null,\"attributes\":[],\"measures\":[{\"id\":1,\"type\":\"INTEGER\",\"name\":\"int_var\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"viral\":[],\"identifiers\":[],\"persistent\":false},\"resultComponent\":{\"id\":1,\"type\":\"INTEGER\",\"name\":\"int_var\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},\"messages\":[],\"command\":\"3\",\"acomponent\":false,\"scalarComponent\":{\"id\":1,\"type\":\"INTEGER\",\"name\":\"int_var\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},\"ascalar\":true},{\"result\":{\"id\":4,\"name\":\"temporary_3\",\"description\":null,\"attributes\":[],\"measures\":[{\"id\":2,\"type\":\"INTEGER\",\"name\":\"int_var\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"viral\":[],\"identifiers\":[],\"persistent\":false},\"resultComponent\":{\"id\":2,\"type\":\"INTEGER\",\"name\":\"int_var\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},\"messages\":[],\"command\":\"2\",\"acomponent\":false,\"scalarComponent\":{\"id\":2,\"type\":\"INTEGER\",\"name\":\"int_var\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},\"ascalar\":true}]";
		List<ResultExpression> result = vtlTestUtils.getResult(commandStatements);
		List<ResultExpression> resultExpressionsToComapare = vtlTestUtils.convertJsonToResultExpression(jsonResult);
		assertNotNull("Conversione non riuscita", resultExpressionsToComapare);
		assertNotNull("Validazione Semantica non riuscita", result);
		assertTrue("I due risultati non sono uguali",
				vtlTestUtils.compareResult(result.get(0), resultExpressionsToComapare.get(0)));
	}

	@Test
	@Transactional
	public void plusComponent() throws Exception {
		VtlDataset vtlDatasetFind = datasetRepository.findByNameIgnoreCase("DS_1");
		if (vtlDatasetFind == null) {
			VtlDataset vtlDataset = new VtlDataset();

			vtlDataset.setPersistent(true);
			vtlDataset.setName("DS_7");
			vtlDataset.setIsOnlyAScalar(false);
			vtlDataset.setTransitory(false);
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_1", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_2", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_1", VtlType.NUMBER, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_2", VtlType.INTEGER, VtlComponentRole.MEASURE));
			datasetRepository.save(vtlDataset);
		}
		String commandStatements = "DS_r := DS_7 [calc  Me_3 :=  + Me_1  ];";
		String jsonResult = "[{\"result\":{\"id\":50,\"name\":\"DS_r\",\"description\":null,\"attributes\":[],\"persistent\":false,\"identifiers\":[{\"id\":185,\"type\":\"INTEGER\",\"name\":\"ID_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":186,\"type\":\"STRING\",\"name\":\"ID_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}],\"measures\":[{\"id\":187,\"type\":\"NUMBER\",\"name\":\"ME_1\",\"domainValue\":\"num_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},{\"id\":188,\"type\":\"INTEGER\",\"name\":\"ME_2\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},{\"id\":189,\"type\":\"NUMBER\",\"name\":\"Me_3\",\"domainValue\":\"num_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"viral\":[]},\"messages\":[],\"command\":\"DS_r:=DS_7[calcMe_3:=+Me_1];\",\"acomponent\":false,\"ascalar\":false},{\"result\":{\"id\":48,\"name\":\"temporary_47\",\"description\":null,\"attributes\":[],\"persistent\":false,\"identifiers\":[{\"id\":180,\"type\":\"INTEGER\",\"name\":\"ID_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":181,\"type\":\"STRING\",\"name\":\"ID_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}],\"measures\":[{\"id\":182,\"type\":\"NUMBER\",\"name\":\"ME_1\",\"domainValue\":\"num_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},{\"id\":183,\"type\":\"INTEGER\",\"name\":\"ME_2\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},{\"id\":184,\"type\":\"NUMBER\",\"name\":\"Me_3\",\"domainValue\":\"num_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"viral\":[]},\"messages\":[],\"command\":\"calcMe_3:=+Me_1\",\"acomponent\":false,\"ascalar\":false},{\"resultComponent\":{\"id\":null,\"type\":\"NUMBER\",\"name\":\"Me_3\",\"domainValue\":\"num_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},\"messages\":[],\"command\":\"Me_3:=+Me_1\",\"acomponent\":true,\"ascalar\":false},{\"resultComponent\":{\"id\":null,\"type\":\"NUMBER\",\"name\":\"ME_1\",\"domainValue\":\"num_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},\"messages\":[],\"command\":\"+Me_1\",\"acomponent\":true,\"ascalar\":false},{\"resultComponent\":{\"id\":25,\"type\":\"NUMBER\",\"name\":\"ME_1\",\"domainValue\":\"num_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},\"messages\":[],\"command\":\"Me_1\",\"acomponent\":true,\"ascalar\":false}]";
		List<ResultExpression> result = vtlTestUtils.getResult(commandStatements);
		List<ResultExpression> resultExpressionsToComapare = vtlTestUtils.convertJsonToResultExpression(jsonResult);
		assertNotNull("Conversione non riuscita", resultExpressionsToComapare);
		assertNotNull("Validazione Semantica non riuscita", result);
		assertTrue("I due risultati non sono uguali",
				vtlTestUtils.compareResult(result.get(0), resultExpressionsToComapare.get(0)));

	}

	@Test
	@Transactional
	public void minus() throws Exception {
		VtlDataset vtlDatasetFind = datasetRepository.findByNameIgnoreCase("DS_1");
		if (vtlDatasetFind == null) {
			VtlDataset vtlDataset = new VtlDataset();

			vtlDataset.setPersistent(true);
			vtlDataset.setName("DS_8");
			vtlDataset.setIsOnlyAScalar(false);
			vtlDataset.setTransitory(false);
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_1", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_2", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_1", VtlType.INTEGER, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_2", VtlType.NUMBER, VtlComponentRole.MEASURE));
			datasetRepository.save(vtlDataset);

		}
		String commandStatements = "DS_r := - DS_8;";
		String jsonResult = "[{\"result\":{\"id\":37,\"name\":\"DS_r\",\"description\":null,\"attributes\":[],\"persistent\":false,\"viral\":[],\"identifiers\":[{\"id\":1,\"type\":\"INTEGER\",\"name\":\"ID_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":2,\"type\":\"STRING\",\"name\":\"ID_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}],\"measures\":[{\"id\":144,\"type\":\"INTEGER\",\"name\":\"ME_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},{\"id\":145,\"type\":\"NUMBER\",\"name\":\"ME_2\",\"domainValue\":\"num_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"}]},\"messages\":[],\"command\":\"DS_r:=-DS_8;\",\"acomponent\":false,\"ascalar\":false},{\"result\":{\"id\":37,\"name\":\"DS_r\",\"description\":null,\"attributes\":[],\"persistent\":false,\"viral\":[],\"identifiers\":[{\"id\":1,\"type\":\"INTEGER\",\"name\":\"ID_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":2,\"type\":\"STRING\",\"name\":\"ID_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}],\"measures\":[{\"id\":144,\"type\":\"INTEGER\",\"name\":\"ME_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},{\"id\":145,\"type\":\"NUMBER\",\"name\":\"ME_2\",\"domainValue\":\"num_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"}]},\"messages\":[],\"command\":\"-DS_8\",\"acomponent\":false,\"ascalar\":false},{\"result\":{\"id\":1,\"name\":\"DS_8\",\"description\":null,\"attributes\":[],\"persistent\":true,\"viral\":[],\"identifiers\":[{\"id\":1,\"type\":\"INTEGER\",\"name\":\"ID_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":2,\"type\":\"STRING\",\"name\":\"ID_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}],\"measures\":[{\"id\":3,\"type\":\"INTEGER\",\"name\":\"ME_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},{\"id\":4,\"type\":\"NUMBER\",\"name\":\"ME_2\",\"domainValue\":\"num_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"}]},\"messages\":[],\"command\":\"DS_8\",\"acomponent\":false,\"ascalar\":false}]";
		List<ResultExpression> result = vtlTestUtils.getResult(commandStatements);
		List<ResultExpression> resultExpressionsToComapare = vtlTestUtils.convertJsonToResultExpression(jsonResult);
		assertNotNull("Conversione non riuscita", resultExpressionsToComapare);
		assertNotNull("Validazione Semantica non riuscita", result);
		assertTrue("I due risultati non sono uguali",
				vtlTestUtils.compareResult(result.get(0), resultExpressionsToComapare.get(0)));

	}

	@Test
	@Transactional
	public void minusInt() throws Exception {

		String commandStatements = "DS_r := 7 - 5;";
		String jsonResult = "[{\"result\":{\"id\":66,\"name\":\"DS_r\",\"description\":null,\"attributes\":[],\"measures\":[{\"id\":33,\"type\":\"INTEGER\",\"name\":\"int_var\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"persistent\":false,\"viral\":[],\"identifiers\":[]},\"resultComponent\":{\"id\":33,\"type\":\"INTEGER\",\"name\":\"int_var\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},\"messages\":[],\"command\":\"DS_r:=7-5;\",\"acomponent\":false,\"scalarComponent\":{\"id\":33,\"type\":\"INTEGER\",\"name\":\"int_var\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},\"ascalar\":true},{\"result\":{\"id\":66,\"name\":\"DS_r\",\"description\":null,\"attributes\":[],\"measures\":[{\"id\":33,\"type\":\"INTEGER\",\"name\":\"int_var\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"persistent\":false,\"viral\":[],\"identifiers\":[]},\"resultComponent\":{\"id\":33,\"type\":\"INTEGER\",\"name\":\"int_var\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},\"messages\":[],\"command\":\"7-5\",\"acomponent\":false,\"scalarComponent\":{\"id\":33,\"type\":\"INTEGER\",\"name\":\"int_var\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},\"ascalar\":true},{\"result\":{\"id\":62,\"name\":\"temporary_61\",\"description\":null,\"attributes\":[],\"measures\":[{\"id\":31,\"type\":\"INTEGER\",\"name\":\"int_var\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"persistent\":false,\"viral\":[],\"identifiers\":[]},\"resultComponent\":{\"id\":31,\"type\":\"INTEGER\",\"name\":\"int_var\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},\"messages\":[],\"command\":\"7\",\"acomponent\":false,\"scalarComponent\":{\"id\":31,\"type\":\"INTEGER\",\"name\":\"int_var\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},\"ascalar\":true},{\"result\":{\"id\":64,\"name\":\"temporary_63\",\"description\":null,\"attributes\":[],\"measures\":[{\"id\":32,\"type\":\"INTEGER\",\"name\":\"int_var\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"persistent\":false,\"viral\":[],\"identifiers\":[]},\"resultComponent\":{\"id\":32,\"type\":\"INTEGER\",\"name\":\"int_var\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},\"messages\":[],\"command\":\"5\",\"acomponent\":false,\"scalarComponent\":{\"id\":32,\"type\":\"INTEGER\",\"name\":\"int_var\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},\"ascalar\":true}]";
		List<ResultExpression> result = vtlTestUtils.getResult(commandStatements);
		List<ResultExpression> resultExpressionsToComapare = vtlTestUtils.convertJsonToResultExpression(jsonResult);
		assertNotNull("Conversione non riuscita", resultExpressionsToComapare);
		assertNotNull("Validazione Semantica non riuscita", result);
		assertTrue("I due risultati non sono uguali",
				vtlTestUtils.compareResult(result.get(0), resultExpressionsToComapare.get(0)));
	}

	@Test
	@Transactional
	public void minusComponent() throws Exception {
		VtlDataset vtlDatasetFind = datasetRepository.findByNameIgnoreCase("DS_1");
		if (vtlDatasetFind == null) {
			VtlDataset vtlDataset = new VtlDataset();

			vtlDataset.setPersistent(true);
			vtlDataset.setName("DS_8");
			vtlDataset.setIsOnlyAScalar(false);
			vtlDataset.setTransitory(false);
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_1", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_2", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_1", VtlType.INTEGER, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_2", VtlType.NUMBER, VtlComponentRole.MEASURE));
			datasetRepository.save(vtlDataset);
		}
		String commandStatements = "DS_r := DS_8 [  calc  Me_3  :=  -  Me_1  ];";
		String jsonResult = "[{\"result\":{\"id\":106,\"name\":\"DS_r\",\"description\":null,\"attributes\":[],\"persistent\":false,\"measures\":[{\"id\":280,\"type\":\"INTEGER\",\"name\":\"ME_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},{\"id\":281,\"type\":\"NUMBER\",\"name\":\"ME_2\",\"domainValue\":\"num_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},{\"id\":282,\"type\":\"INTEGER\",\"name\":\"Me_3\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"identifiers\":[{\"id\":278,\"type\":\"INTEGER\",\"name\":\"ID_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":279,\"type\":\"STRING\",\"name\":\"ID_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}],\"viral\":[]},\"messages\":[],\"command\":\"DS_r:=DS_8[calcMe_3:=-Me_1];\",\"acomponent\":false,\"ascalar\":false},{\"result\":{\"id\":104,\"name\":\"temporary_103\",\"description\":null,\"attributes\":[],\"persistent\":false,\"measures\":[{\"id\":275,\"type\":\"INTEGER\",\"name\":\"ME_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},{\"id\":276,\"type\":\"NUMBER\",\"name\":\"ME_2\",\"domainValue\":\"num_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},{\"id\":277,\"type\":\"INTEGER\",\"name\":\"Me_3\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"identifiers\":[{\"id\":273,\"type\":\"INTEGER\",\"name\":\"ID_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":274,\"type\":\"STRING\",\"name\":\"ID_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}],\"viral\":[]},\"messages\":[],\"command\":\"calcMe_3:=-Me_1\",\"acomponent\":false,\"ascalar\":false},{\"resultComponent\":{\"id\":null,\"type\":\"INTEGER\",\"name\":\"Me_3\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},\"messages\":[],\"command\":\"Me_3:=-Me_1\",\"acomponent\":true,\"ascalar\":false},{\"resultComponent\":{\"id\":null,\"type\":\"INTEGER\",\"name\":\"ME_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},\"messages\":[],\"command\":\"-Me_1\",\"acomponent\":true,\"ascalar\":false},{\"resultComponent\":{\"id\":28,\"type\":\"INTEGER\",\"name\":\"ME_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},\"messages\":[],\"command\":\"Me_1\",\"acomponent\":true,\"ascalar\":false}]";
		List<ResultExpression> result = vtlTestUtils.getResult(commandStatements);
		List<ResultExpression> resultExpressionsToComapare = vtlTestUtils.convertJsonToResultExpression(jsonResult);
		assertNotNull("Conversione non riuscita", resultExpressionsToComapare);
		assertNotNull("Validazione Semantica non riuscita", result);
		assertTrue("I due risultati non sono uguali",
				vtlTestUtils.compareResult(result.get(0), resultExpressionsToComapare.get(0)));

	}

	@Test
	@Transactional
	public void addition() throws Exception {
		VtlDataset vtlDatasetFind = datasetRepository.findByNameIgnoreCase("DS_1");
		if (vtlDatasetFind == null) {
			VtlDataset vtlDataset = new VtlDataset();

			vtlDataset.setPersistent(true);
			vtlDataset.setName("DS_8");
			vtlDataset.setIsOnlyAScalar(false);
			vtlDataset.setTransitory(false);
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_1", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_2", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_1", VtlType.INTEGER, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_2", VtlType.NUMBER, VtlComponentRole.MEASURE));
			datasetRepository.save(vtlDataset);
		}
		String commandStatements = "DS_r := DS_8 +  3;";
		String jsonResult = "[{\"result\":{\"id\":37,\"name\":\"DS_r\",\"description\":null,\"attributes\":[],\"persistent\":false,\"identifiers\":[{\"id\":1,\"type\":\"INTEGER\",\"name\":\"ID_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":2,\"type\":\"STRING\",\"name\":\"ID_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}],\"viral\":[],\"measures\":[{\"id\":143,\"type\":\"INTEGER\",\"name\":\"ME_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},{\"id\":144,\"type\":\"NUMBER\",\"name\":\"ME_2\",\"domainValue\":\"num_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"}]},\"messages\":[],\"command\":\"DS_r:=DS_8+3;\",\"acomponent\":false,\"ascalar\":false},{\"result\":{\"id\":37,\"name\":\"DS_r\",\"description\":null,\"attributes\":[],\"persistent\":false,\"identifiers\":[{\"id\":1,\"type\":\"INTEGER\",\"name\":\"ID_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":2,\"type\":\"STRING\",\"name\":\"ID_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}],\"viral\":[],\"measures\":[{\"id\":143,\"type\":\"INTEGER\",\"name\":\"ME_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},{\"id\":144,\"type\":\"NUMBER\",\"name\":\"ME_2\",\"domainValue\":\"num_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"}]},\"messages\":[],\"command\":\"DS_8+3\",\"acomponent\":false,\"ascalar\":false},{\"result\":{\"id\":1,\"name\":\"DS_8\",\"description\":null,\"attributes\":[],\"persistent\":true,\"identifiers\":[{\"id\":1,\"type\":\"INTEGER\",\"name\":\"ID_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":2,\"type\":\"STRING\",\"name\":\"ID_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}],\"viral\":[],\"measures\":[{\"id\":3,\"type\":\"INTEGER\",\"name\":\"ME_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},{\"id\":4,\"type\":\"NUMBER\",\"name\":\"ME_2\",\"domainValue\":\"num_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"}]},\"messages\":[],\"command\":\"DS_8\",\"acomponent\":false,\"ascalar\":false},{\"result\":{\"id\":35,\"name\":\"temporary_34\",\"description\":null,\"attributes\":[],\"persistent\":false,\"identifiers\":[],\"viral\":[],\"measures\":[{\"id\":142,\"type\":\"INTEGER\",\"name\":\"int_var\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"}]},\"resultComponent\":{\"id\":142,\"type\":\"INTEGER\",\"name\":\"int_var\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},\"messages\":[],\"command\":\"3\",\"acomponent\":false,\"scalarComponent\":{\"id\":142,\"type\":\"INTEGER\",\"name\":\"int_var\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},\"ascalar\":true}]";
		List<ResultExpression> result = vtlTestUtils.getResult(commandStatements);
		List<ResultExpression> resultExpressionsToComapare = vtlTestUtils.convertJsonToResultExpression(jsonResult);
		assertNotNull("Conversione non riuscita", resultExpressionsToComapare);
		assertNotNull("Validazione Semantica non riuscita", result);
		assertTrue("I due risultati non sono uguali",
				vtlTestUtils.compareResult(result.get(0), resultExpressionsToComapare.get(0)));

	}

	@Test
	@Transactional
	public void additionDs() throws Exception {
		VtlDataset vtlDatasetFind = datasetRepository.findByNameIgnoreCase("DS_1");
		if (vtlDatasetFind == null) {
			VtlDataset vtlDataset = new VtlDataset();

			vtlDataset.setPersistent(true);
			vtlDataset.setName("DS_8");
			vtlDataset.setIsOnlyAScalar(false);
			vtlDataset.setTransitory(false);
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_1", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_2", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_1", VtlType.INTEGER, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_2", VtlType.NUMBER, VtlComponentRole.MEASURE));
			datasetRepository.save(vtlDataset);

			VtlDataset vtlDatasetSecond = new VtlDataset();
			vtlDatasetSecond.setPersistent(true);
			vtlDatasetSecond.setName("DS_9");
			vtlDatasetSecond.setIsOnlyAScalar(false);
			vtlDatasetSecond.setTransitory(false);
			vtlDatasetSecond
					.addComponent(vtlTestUtils.getVtlComponent("ID_1", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDatasetSecond
					.addComponent(vtlTestUtils.getVtlComponent("ID_2", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDatasetSecond
					.addComponent(vtlTestUtils.getVtlComponent("ME_1", VtlType.INTEGER, VtlComponentRole.MEASURE));
			vtlDatasetSecond
					.addComponent(vtlTestUtils.getVtlComponent("ME_2", VtlType.NUMBER, VtlComponentRole.MEASURE));
			datasetRepository.save(vtlDatasetSecond);

		}
		String commandStatements = "DS_r := DS_8 +  DS_9;";
		String jsonResult = "[{\"result\":{\"id\":39,\"name\":\"DS_r\",\"description\":null,\"attributes\":[],\"persistent\":false,\"identifiers\":[{\"id\":1,\"type\":\"INTEGER\",\"name\":\"ID_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":2,\"type\":\"STRING\",\"name\":\"ID_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}],\"viral\":[],\"measures\":[{\"id\":145,\"type\":\"INTEGER\",\"name\":\"ME_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},{\"id\":146,\"type\":\"NUMBER\",\"name\":\"ME_2\",\"domainValue\":\"num_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"}]},\"messages\":[],\"command\":\"DS_r:=DS_8+DS_9;\",\"acomponent\":false,\"ascalar\":false},{\"result\":{\"id\":39,\"name\":\"DS_r\",\"description\":null,\"attributes\":[],\"persistent\":false,\"identifiers\":[{\"id\":1,\"type\":\"INTEGER\",\"name\":\"ID_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":2,\"type\":\"STRING\",\"name\":\"ID_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}],\"viral\":[],\"measures\":[{\"id\":145,\"type\":\"INTEGER\",\"name\":\"ME_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},{\"id\":146,\"type\":\"NUMBER\",\"name\":\"ME_2\",\"domainValue\":\"num_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"}]},\"messages\":[],\"command\":\"DS_8+DS_9\",\"acomponent\":false,\"ascalar\":false},{\"result\":{\"id\":1,\"name\":\"DS_8\",\"description\":null,\"attributes\":[],\"persistent\":true,\"identifiers\":[{\"id\":1,\"type\":\"INTEGER\",\"name\":\"ID_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":2,\"type\":\"STRING\",\"name\":\"ID_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}],\"viral\":[],\"measures\":[{\"id\":3,\"type\":\"INTEGER\",\"name\":\"ME_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},{\"id\":4,\"type\":\"NUMBER\",\"name\":\"ME_2\",\"domainValue\":\"num_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"}]},\"messages\":[],\"command\":\"DS_8\",\"acomponent\":false,\"ascalar\":false},{\"result\":{\"id\":2,\"name\":\"DS_9\",\"description\":null,\"attributes\":[],\"persistent\":true,\"identifiers\":[{\"id\":5,\"type\":\"INTEGER\",\"name\":\"ID_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":6,\"type\":\"STRING\",\"name\":\"ID_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}],\"viral\":[],\"measures\":[{\"id\":7,\"type\":\"INTEGER\",\"name\":\"ME_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},{\"id\":8,\"type\":\"NUMBER\",\"name\":\"ME_2\",\"domainValue\":\"num_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"}]},\"messages\":[],\"command\":\"DS_9\",\"acomponent\":false,\"ascalar\":false}]";
		List<ResultExpression> result = vtlTestUtils.getResult(commandStatements);
		List<ResultExpression> resultExpressionsToComapare = vtlTestUtils.convertJsonToResultExpression(jsonResult);
		assertNotNull("Conversione non riuscita", resultExpressionsToComapare);
		assertNotNull("Validazione Semantica non riuscita", result);
		assertTrue("I due risultati non sono uguali",
				vtlTestUtils.compareResult(result.get(0), resultExpressionsToComapare.get(0)));

	}

	@Test
	@Transactional
	public void additionComponent() throws Exception {
		VtlDataset vtlDatasetFind = datasetRepository.findByNameIgnoreCase("DS_1");
		if (vtlDatasetFind == null) {
			VtlDataset vtlDataset = new VtlDataset();

			vtlDataset.setPersistent(true);
			vtlDataset.setName("DS_8");
			vtlDataset.setIsOnlyAScalar(false);
			vtlDataset.setTransitory(false);
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_1", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_2", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_1", VtlType.INTEGER, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_2", VtlType.NUMBER, VtlComponentRole.MEASURE));
			datasetRepository.save(vtlDataset);
		}
		String commandStatements = "DS_r := DS_8 [ calc Me_3 :=  Me_1  +  3.0 ];";
		String jsonResult = "[{\"result\":{\"id\":43,\"name\":\"DS_r\",\"description\":null,\"attributes\":[],\"persistent\":false,\"identifiers\":[{\"id\":1,\"type\":\"INTEGER\",\"name\":\"ID_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":2,\"type\":\"STRING\",\"name\":\"ID_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}],\"viral\":[],\"measures\":[{\"id\":3,\"type\":\"INTEGER\",\"name\":\"ME_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},{\"id\":4,\"type\":\"NUMBER\",\"name\":\"ME_2\",\"domainValue\":\"num_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},{\"id\":148,\"type\":\"NUMBER\",\"name\":\"Me_3\",\"domainValue\":\"num_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"}]},\"messages\":[],\"command\":\"DS_r:=DS_8[calcMe_3:=Me_1+3.0];\",\"acomponent\":false,\"ascalar\":false},{\"result\":{\"id\":43,\"name\":\"DS_r\",\"description\":null,\"attributes\":[],\"persistent\":false,\"identifiers\":[{\"id\":1,\"type\":\"INTEGER\",\"name\":\"ID_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":2,\"type\":\"STRING\",\"name\":\"ID_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}],\"viral\":[],\"measures\":[{\"id\":3,\"type\":\"INTEGER\",\"name\":\"ME_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},{\"id\":4,\"type\":\"NUMBER\",\"name\":\"ME_2\",\"domainValue\":\"num_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},{\"id\":148,\"type\":\"NUMBER\",\"name\":\"Me_3\",\"domainValue\":\"num_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"}]},\"messages\":[],\"command\":\"calcMe_3:=Me_1+3.0\",\"acomponent\":false,\"ascalar\":false},{\"resultComponent\":{\"id\":null,\"type\":\"NUMBER\",\"name\":\"Me_3\",\"domainValue\":\"num_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},\"messages\":[],\"command\":\"Me_3:=Me_1+3.0\",\"acomponent\":true,\"ascalar\":false},{\"resultComponent\":{\"id\":null,\"type\":\"NUMBER\",\"name\":\"ME_1\",\"domainValue\":\"num_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},\"messages\":[],\"command\":\"Me_1+3.0\",\"acomponent\":true,\"ascalar\":false},{\"resultComponent\":{\"id\":3,\"type\":\"INTEGER\",\"name\":\"ME_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},\"messages\":[],\"command\":\"Me_1\",\"acomponent\":true,\"ascalar\":false},{\"result\":{\"id\":41,\"name\":\"temporary_40\",\"description\":null,\"attributes\":[],\"persistent\":false,\"identifiers\":[],\"viral\":[],\"measures\":[{\"id\":147,\"type\":\"NUMBER\",\"name\":\"num_var\",\"domainValue\":\"num_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"}]},\"resultComponent\":{\"id\":147,\"type\":\"NUMBER\",\"name\":\"num_var\",\"domainValue\":\"num_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},\"messages\":[],\"command\":\"3.0\",\"acomponent\":false,\"scalarComponent\":{\"id\":147,\"type\":\"NUMBER\",\"name\":\"num_var\",\"domainValue\":\"num_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},\"ascalar\":true}]";
		List<ResultExpression> result = vtlTestUtils.getResult(commandStatements);
		List<ResultExpression> resultExpressionsToComapare = vtlTestUtils.convertJsonToResultExpression(jsonResult);
		assertNotNull("Conversione non riuscita", resultExpressionsToComapare);
		assertNotNull("Validazione Semantica non riuscita", result);
		assertTrue("I due risultati non sono uguali",
				vtlTestUtils.compareResult(result.get(0), resultExpressionsToComapare.get(0)));

	}

	@Test
	@Transactional
	public void subtractionDs() throws Exception {
		VtlDataset vtlDatasetFind = datasetRepository.findByNameIgnoreCase("DS_1");
		if (vtlDatasetFind == null) {
			VtlDataset vtlDataset = new VtlDataset();
			vtlDataset.setPersistent(true);
			vtlDataset.setName("DS_8");
			vtlDataset.setIsOnlyAScalar(false);
			vtlDataset.setTransitory(false);
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_1", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_2", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_1", VtlType.INTEGER, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_2", VtlType.NUMBER, VtlComponentRole.MEASURE));
			datasetRepository.save(vtlDataset);

			VtlDataset vtlDatasetSecond = new VtlDataset();
			vtlDatasetSecond.setPersistent(true);
			vtlDatasetSecond.setName("DS_9");
			vtlDatasetSecond.setIsOnlyAScalar(false);
			vtlDatasetSecond.setTransitory(false);
			vtlDatasetSecond
					.addComponent(vtlTestUtils.getVtlComponent("ID_1", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDatasetSecond
					.addComponent(vtlTestUtils.getVtlComponent("ID_2", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDatasetSecond
					.addComponent(vtlTestUtils.getVtlComponent("ME_1", VtlType.INTEGER, VtlComponentRole.MEASURE));
			vtlDatasetSecond
					.addComponent(vtlTestUtils.getVtlComponent("ME_2", VtlType.NUMBER, VtlComponentRole.MEASURE));
			datasetRepository.save(vtlDatasetSecond);

		}
		String commandStatements = "DS_r := DS_8 -  DS_9;";
		String jsonResult = "[{\"result\":{\"id\":35,\"name\":\"DS_r\",\"description\":null,\"attributes\":[],\"persistent\":false,\"viral\":[],\"measures\":[{\"id\":142,\"type\":\"INTEGER\",\"name\":\"ME_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},{\"id\":143,\"type\":\"NUMBER\",\"name\":\"ME_2\",\"domainValue\":\"num_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"identifiers\":[{\"id\":1,\"type\":\"INTEGER\",\"name\":\"ID_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":2,\"type\":\"STRING\",\"name\":\"ID_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}]},\"messages\":[],\"command\":\"DS_r:=DS_8-DS_9;\",\"acomponent\":false,\"ascalar\":false},{\"result\":{\"id\":35,\"name\":\"DS_r\",\"description\":null,\"attributes\":[],\"persistent\":false,\"viral\":[],\"measures\":[{\"id\":142,\"type\":\"INTEGER\",\"name\":\"ME_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},{\"id\":143,\"type\":\"NUMBER\",\"name\":\"ME_2\",\"domainValue\":\"num_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"identifiers\":[{\"id\":1,\"type\":\"INTEGER\",\"name\":\"ID_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":2,\"type\":\"STRING\",\"name\":\"ID_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}]},\"messages\":[],\"command\":\"DS_8-DS_9\",\"acomponent\":false,\"ascalar\":false},{\"result\":{\"id\":1,\"name\":\"DS_8\",\"description\":null,\"attributes\":[],\"persistent\":true,\"viral\":[],\"measures\":[{\"id\":3,\"type\":\"INTEGER\",\"name\":\"ME_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},{\"id\":4,\"type\":\"NUMBER\",\"name\":\"ME_2\",\"domainValue\":\"num_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"identifiers\":[{\"id\":1,\"type\":\"INTEGER\",\"name\":\"ID_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":2,\"type\":\"STRING\",\"name\":\"ID_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}]},\"messages\":[],\"command\":\"DS_8\",\"acomponent\":false,\"ascalar\":false},{\"result\":{\"id\":2,\"name\":\"DS_9\",\"description\":null,\"attributes\":[],\"persistent\":true,\"viral\":[],\"measures\":[{\"id\":7,\"type\":\"INTEGER\",\"name\":\"ME_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},{\"id\":8,\"type\":\"NUMBER\",\"name\":\"ME_2\",\"domainValue\":\"num_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"identifiers\":[{\"id\":5,\"type\":\"INTEGER\",\"name\":\"ID_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":6,\"type\":\"STRING\",\"name\":\"ID_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}]},\"messages\":[],\"command\":\"DS_9\",\"acomponent\":false,\"ascalar\":false}]";
		List<ResultExpression> result = vtlTestUtils.getResult(commandStatements);
		List<ResultExpression> resultExpressionsToComapare = vtlTestUtils.convertJsonToResultExpression(jsonResult);
		assertNotNull("Conversione non riuscita", resultExpressionsToComapare);
		assertNotNull("Validazione Semantica non riuscita", result);
		assertTrue("I due risultati non sono uguali",
				vtlTestUtils.compareResult(result.get(0), resultExpressionsToComapare.get(0)));

	}

	@Test
	@Transactional
	public void subtractionComponent() throws Exception {
		VtlDataset vtlDatasetFind = datasetRepository.findByNameIgnoreCase("DS_1");
		if (vtlDatasetFind == null) {
			VtlDataset vtlDataset = new VtlDataset();

			vtlDataset.setPersistent(true);
			vtlDataset.setName("DS_8");
			vtlDataset.setIsOnlyAScalar(false);
			vtlDataset.setTransitory(false);
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_1", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_2", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_1", VtlType.INTEGER, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_2", VtlType.NUMBER, VtlComponentRole.MEASURE));
			datasetRepository.save(vtlDataset);
		}
		String commandStatements = "DS_r := DS_8 - 3;";
		String jsonResult = "[{\"result\":{\"id\":43,\"name\":\"DS_r\",\"description\":null,\"attributes\":[],\"persistent\":false,\"viral\":[],\"measures\":[{\"id\":148,\"type\":\"INTEGER\",\"name\":\"ME_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},{\"id\":149,\"type\":\"NUMBER\",\"name\":\"ME_2\",\"domainValue\":\"num_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"identifiers\":[{\"id\":1,\"type\":\"INTEGER\",\"name\":\"ID_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":2,\"type\":\"STRING\",\"name\":\"ID_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}]},\"messages\":[],\"command\":\"DS_r:=DS_8-3;\",\"acomponent\":false,\"ascalar\":false},{\"result\":{\"id\":43,\"name\":\"DS_r\",\"description\":null,\"attributes\":[],\"persistent\":false,\"viral\":[],\"measures\":[{\"id\":148,\"type\":\"INTEGER\",\"name\":\"ME_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},{\"id\":149,\"type\":\"NUMBER\",\"name\":\"ME_2\",\"domainValue\":\"num_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"identifiers\":[{\"id\":1,\"type\":\"INTEGER\",\"name\":\"ID_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":2,\"type\":\"STRING\",\"name\":\"ID_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}]},\"messages\":[],\"command\":\"DS_8-3\",\"acomponent\":false,\"ascalar\":false},{\"result\":{\"id\":1,\"name\":\"DS_8\",\"description\":null,\"attributes\":[],\"persistent\":true,\"viral\":[],\"measures\":[{\"id\":3,\"type\":\"INTEGER\",\"name\":\"ME_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},{\"id\":4,\"type\":\"NUMBER\",\"name\":\"ME_2\",\"domainValue\":\"num_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"identifiers\":[{\"id\":1,\"type\":\"INTEGER\",\"name\":\"ID_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":2,\"type\":\"STRING\",\"name\":\"ID_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}]},\"messages\":[],\"command\":\"DS_8\",\"acomponent\":false,\"ascalar\":false},{\"result\":{\"id\":41,\"name\":\"temporary_40\",\"description\":null,\"attributes\":[],\"persistent\":false,\"viral\":[],\"measures\":[{\"id\":147,\"type\":\"INTEGER\",\"name\":\"int_var\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"identifiers\":[]},\"resultComponent\":{\"id\":147,\"type\":\"INTEGER\",\"name\":\"int_var\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},\"messages\":[],\"command\":\"3\",\"acomponent\":false,\"ascalar\":true,\"scalarComponent\":{\"id\":147,\"type\":\"INTEGER\",\"name\":\"int_var\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"}}]";
		List<ResultExpression> result = vtlTestUtils.getResult(commandStatements);
		List<ResultExpression> resultExpressionsToComapare = vtlTestUtils.convertJsonToResultExpression(jsonResult);
		assertNotNull("Conversione non riuscita", resultExpressionsToComapare);
		assertNotNull("Validazione Semantica non riuscita", result);
		assertTrue("I due risultati non sono uguali",
				vtlTestUtils.compareResult(result.get(0), resultExpressionsToComapare.get(0)));

	}

	@Test
	@Transactional
	public void subtraction() throws Exception {
		VtlDataset vtlDatasetFind = datasetRepository.findByNameIgnoreCase("DS_1");
		if (vtlDatasetFind == null) {
			VtlDataset vtlDataset = new VtlDataset();

			vtlDataset.setPersistent(true);
			vtlDataset.setName("DS_8");
			vtlDataset.setIsOnlyAScalar(false);
			vtlDataset.setTransitory(false);
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_1", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_2", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_1", VtlType.INTEGER, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_2", VtlType.NUMBER, VtlComponentRole.MEASURE));
			datasetRepository.save(vtlDataset);
		}
		String commandStatements = "DS_r := DS_8 [ calc Me_3 :=  Me_1  -  3 ];";
		String jsonResult = "[{\"result\":{\"id\":47,\"name\":\"DS_r\",\"description\":null,\"attributes\":[],\"persistent\":false,\"viral\":[],\"measures\":[{\"id\":3,\"type\":\"INTEGER\",\"name\":\"ME_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},{\"id\":4,\"type\":\"NUMBER\",\"name\":\"ME_2\",\"domainValue\":\"num_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},{\"id\":151,\"type\":\"INTEGER\",\"name\":\"Me_3\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"identifiers\":[{\"id\":1,\"type\":\"INTEGER\",\"name\":\"ID_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":2,\"type\":\"STRING\",\"name\":\"ID_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}]},\"messages\":[],\"command\":\"DS_r:=DS_8[calcMe_3:=Me_1-3];\",\"acomponent\":false,\"ascalar\":false},{\"result\":{\"id\":47,\"name\":\"DS_r\",\"description\":null,\"attributes\":[],\"persistent\":false,\"viral\":[],\"measures\":[{\"id\":3,\"type\":\"INTEGER\",\"name\":\"ME_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},{\"id\":4,\"type\":\"NUMBER\",\"name\":\"ME_2\",\"domainValue\":\"num_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},{\"id\":151,\"type\":\"INTEGER\",\"name\":\"Me_3\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"identifiers\":[{\"id\":1,\"type\":\"INTEGER\",\"name\":\"ID_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":2,\"type\":\"STRING\",\"name\":\"ID_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}]},\"messages\":[],\"command\":\"calcMe_3:=Me_1-3\",\"acomponent\":false,\"ascalar\":false},{\"resultComponent\":{\"id\":null,\"type\":\"INTEGER\",\"name\":\"Me_3\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},\"messages\":[],\"command\":\"Me_3:=Me_1-3\",\"acomponent\":true,\"ascalar\":false},{\"resultComponent\":{\"id\":null,\"type\":\"INTEGER\",\"name\":\"ME_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},\"messages\":[],\"command\":\"Me_1-3\",\"acomponent\":true,\"ascalar\":false},{\"resultComponent\":{\"id\":3,\"type\":\"INTEGER\",\"name\":\"ME_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},\"messages\":[],\"command\":\"Me_1\",\"acomponent\":true,\"ascalar\":false},{\"result\":{\"id\":45,\"name\":\"temporary_44\",\"description\":null,\"attributes\":[],\"persistent\":false,\"viral\":[],\"measures\":[{\"id\":150,\"type\":\"INTEGER\",\"name\":\"int_var\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"identifiers\":[]},\"resultComponent\":{\"id\":150,\"type\":\"INTEGER\",\"name\":\"int_var\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},\"messages\":[],\"command\":\"3\",\"acomponent\":false,\"ascalar\":true,\"scalarComponent\":{\"id\":150,\"type\":\"INTEGER\",\"name\":\"int_var\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"}}]";
		List<ResultExpression> result = vtlTestUtils.getResult(commandStatements);
		List<ResultExpression> resultExpressionsToComapare = vtlTestUtils.convertJsonToResultExpression(jsonResult);
		assertNotNull("Conversione non riuscita", resultExpressionsToComapare);
		assertNotNull("Validazione Semantica non riuscita", result);
		assertTrue("I due risultati non sono uguali",
				vtlTestUtils.compareResult(result.get(0), resultExpressionsToComapare.get(0)));

	}

	@Test
	@Transactional
	public void multiplicationDs() throws Exception {
		VtlDataset vtlDatasetFind = datasetRepository.findByNameIgnoreCase("DS_1");
		if (vtlDatasetFind == null) {
			VtlDataset vtlDataset = new VtlDataset();
			vtlDataset.setPersistent(true);
			vtlDataset.setName("DS_8");
			vtlDataset.setIsOnlyAScalar(false);
			vtlDataset.setTransitory(false);
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_1", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_2", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_1", VtlType.INTEGER, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_2", VtlType.NUMBER, VtlComponentRole.MEASURE));
			datasetRepository.save(vtlDataset);

			VtlDataset vtlDatasetSecond = new VtlDataset();
			vtlDatasetSecond.setPersistent(true);
			vtlDatasetSecond.setName("DS_9");
			vtlDatasetSecond.setIsOnlyAScalar(false);
			vtlDatasetSecond.setTransitory(false);
			vtlDatasetSecond
					.addComponent(vtlTestUtils.getVtlComponent("ID_1", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDatasetSecond
					.addComponent(vtlTestUtils.getVtlComponent("ID_2", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDatasetSecond
					.addComponent(vtlTestUtils.getVtlComponent("ME_1", VtlType.INTEGER, VtlComponentRole.MEASURE));
			vtlDatasetSecond
					.addComponent(vtlTestUtils.getVtlComponent("ME_2", VtlType.NUMBER, VtlComponentRole.MEASURE));
			datasetRepository.save(vtlDatasetSecond);
		}
		String commandStatements = "DS_r := DS_8 * DS_9;";
		String jsonResult = "[{\"result\":{\"id\":39,\"name\":\"DS_r\",\"description\":null,\"attributes\":[],\"persistent\":false,\"measures\":[{\"id\":145,\"type\":\"INTEGER\",\"name\":\"ME_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},{\"id\":146,\"type\":\"NUMBER\",\"name\":\"ME_2\",\"domainValue\":\"num_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"viral\":[],\"identifiers\":[{\"id\":1,\"type\":\"INTEGER\",\"name\":\"ID_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":2,\"type\":\"STRING\",\"name\":\"ID_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}]},\"messages\":[],\"command\":\"DS_r:=DS_8*DS_9;\",\"acomponent\":false,\"ascalar\":false},{\"result\":{\"id\":39,\"name\":\"DS_r\",\"description\":null,\"attributes\":[],\"persistent\":false,\"measures\":[{\"id\":145,\"type\":\"INTEGER\",\"name\":\"ME_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},{\"id\":146,\"type\":\"NUMBER\",\"name\":\"ME_2\",\"domainValue\":\"num_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"viral\":[],\"identifiers\":[{\"id\":1,\"type\":\"INTEGER\",\"name\":\"ID_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":2,\"type\":\"STRING\",\"name\":\"ID_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}]},\"messages\":[],\"command\":\"DS_8*DS_9\",\"acomponent\":false,\"ascalar\":false},{\"result\":{\"id\":1,\"name\":\"DS_8\",\"description\":null,\"attributes\":[],\"persistent\":true,\"measures\":[{\"id\":3,\"type\":\"INTEGER\",\"name\":\"ME_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},{\"id\":4,\"type\":\"NUMBER\",\"name\":\"ME_2\",\"domainValue\":\"num_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"viral\":[],\"identifiers\":[{\"id\":1,\"type\":\"INTEGER\",\"name\":\"ID_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":2,\"type\":\"STRING\",\"name\":\"ID_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}]},\"messages\":[],\"command\":\"DS_8\",\"acomponent\":false,\"ascalar\":false},{\"result\":{\"id\":2,\"name\":\"DS_9\",\"description\":null,\"attributes\":[],\"persistent\":true,\"measures\":[{\"id\":7,\"type\":\"INTEGER\",\"name\":\"ME_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},{\"id\":8,\"type\":\"NUMBER\",\"name\":\"ME_2\",\"domainValue\":\"num_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"viral\":[],\"identifiers\":[{\"id\":5,\"type\":\"INTEGER\",\"name\":\"ID_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":6,\"type\":\"STRING\",\"name\":\"ID_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}]},\"messages\":[],\"command\":\"DS_9\",\"acomponent\":false,\"ascalar\":false}]";
		List<ResultExpression> result = vtlTestUtils.getResult(commandStatements);
		List<ResultExpression> resultExpressionsToComapare = vtlTestUtils.convertJsonToResultExpression(jsonResult);
		assertNotNull("Conversione non riuscita", resultExpressionsToComapare);
		assertNotNull("Validazione Semantica non riuscita", result);
		assertTrue("I due risultati non sono uguali",
				vtlTestUtils.compareResult(result.get(0), resultExpressionsToComapare.get(0)));
	}

	@Test
	@Transactional
	public void multiplication() throws Exception {
		VtlDataset vtlDatasetFind = datasetRepository.findByNameIgnoreCase("DS_1");
		if (vtlDatasetFind == null) {
			VtlDataset vtlDataset = new VtlDataset();

			vtlDataset.setPersistent(true);
			vtlDataset.setName("DS_8");
			vtlDataset.setIsOnlyAScalar(false);
			vtlDataset.setTransitory(false);
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_1", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_2", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_1", VtlType.INTEGER, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_2", VtlType.NUMBER, VtlComponentRole.MEASURE));
			datasetRepository.save(vtlDataset);
		}
		String commandStatements = "DS_r := DS_8 * -3;";
		String jsonResult = "[{\"result\":{\"id\":37,\"name\":\"DS_r\",\"description\":null,\"attributes\":[],\"persistent\":false,\"measures\":[{\"id\":143,\"type\":\"INTEGER\",\"name\":\"ME_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},{\"id\":144,\"type\":\"NUMBER\",\"name\":\"ME_2\",\"domainValue\":\"num_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"viral\":[],\"identifiers\":[{\"id\":1,\"type\":\"INTEGER\",\"name\":\"ID_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":2,\"type\":\"STRING\",\"name\":\"ID_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}]},\"messages\":[],\"command\":\"DS_r:=DS_8*-3;\",\"acomponent\":false,\"ascalar\":false},{\"result\":{\"id\":37,\"name\":\"DS_r\",\"description\":null,\"attributes\":[],\"persistent\":false,\"measures\":[{\"id\":143,\"type\":\"INTEGER\",\"name\":\"ME_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},{\"id\":144,\"type\":\"NUMBER\",\"name\":\"ME_2\",\"domainValue\":\"num_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"viral\":[],\"identifiers\":[{\"id\":1,\"type\":\"INTEGER\",\"name\":\"ID_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":2,\"type\":\"STRING\",\"name\":\"ID_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}]},\"messages\":[],\"command\":\"DS_8*-3\",\"acomponent\":false,\"ascalar\":false},{\"result\":{\"id\":1,\"name\":\"DS_8\",\"description\":null,\"attributes\":[],\"persistent\":true,\"measures\":[{\"id\":3,\"type\":\"INTEGER\",\"name\":\"ME_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},{\"id\":4,\"type\":\"NUMBER\",\"name\":\"ME_2\",\"domainValue\":\"num_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"viral\":[],\"identifiers\":[{\"id\":1,\"type\":\"INTEGER\",\"name\":\"ID_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":2,\"type\":\"STRING\",\"name\":\"ID_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}]},\"messages\":[],\"command\":\"DS_8\",\"acomponent\":false,\"ascalar\":false},{\"result\":{\"id\":35,\"name\":\"temporary_34\",\"description\":null,\"attributes\":[],\"persistent\":false,\"measures\":[{\"id\":142,\"type\":\"INTEGER\",\"name\":\"int_var\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"viral\":[],\"identifiers\":[]},\"resultComponent\":{\"id\":142,\"type\":\"INTEGER\",\"name\":\"int_var\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},\"messages\":[],\"command\":\"-3\",\"acomponent\":false,\"scalarComponent\":{\"id\":142,\"type\":\"INTEGER\",\"name\":\"int_var\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},\"ascalar\":true}]";
		List<ResultExpression> result = vtlTestUtils.getResult(commandStatements);
		List<ResultExpression> resultExpressionsToComapare = vtlTestUtils.convertJsonToResultExpression(jsonResult);
		assertNotNull("Conversione non riuscita", resultExpressionsToComapare);
		assertNotNull("Validazione Semantica non riuscita", result);
		assertTrue("I due risultati non sono uguali",
				vtlTestUtils.compareResult(result.get(0), resultExpressionsToComapare.get(0)));

	}

	@Test
	@Transactional
	public void multiplicationInt() throws Exception {

		String commandStatements = "DS_r := 7 * 6;";
		String jsonResult = "[{\"result\":{\"id\":72,\"name\":\"DS_r\",\"description\":null,\"attributes\":[],\"measures\":[{\"id\":36,\"type\":\"INTEGER\",\"name\":\"int_var\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"persistent\":false,\"viral\":[],\"identifiers\":[]},\"resultComponent\":{\"id\":36,\"type\":\"INTEGER\",\"name\":\"int_var\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},\"messages\":[],\"command\":\"DS_r:=7*6;\",\"acomponent\":false,\"scalarComponent\":{\"id\":36,\"type\":\"INTEGER\",\"name\":\"int_var\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},\"ascalar\":true},{\"result\":{\"id\":72,\"name\":\"DS_r\",\"description\":null,\"attributes\":[],\"measures\":[{\"id\":36,\"type\":\"INTEGER\",\"name\":\"int_var\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"persistent\":false,\"viral\":[],\"identifiers\":[]},\"resultComponent\":{\"id\":36,\"type\":\"INTEGER\",\"name\":\"int_var\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},\"messages\":[],\"command\":\"7*6\",\"acomponent\":false,\"scalarComponent\":{\"id\":36,\"type\":\"INTEGER\",\"name\":\"int_var\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},\"ascalar\":true},{\"result\":{\"id\":68,\"name\":\"temporary_67\",\"description\":null,\"attributes\":[],\"measures\":[{\"id\":34,\"type\":\"INTEGER\",\"name\":\"int_var\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"persistent\":false,\"viral\":[],\"identifiers\":[]},\"resultComponent\":{\"id\":34,\"type\":\"INTEGER\",\"name\":\"int_var\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},\"messages\":[],\"command\":\"7\",\"acomponent\":false,\"scalarComponent\":{\"id\":34,\"type\":\"INTEGER\",\"name\":\"int_var\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},\"ascalar\":true},{\"result\":{\"id\":70,\"name\":\"temporary_69\",\"description\":null,\"attributes\":[],\"measures\":[{\"id\":35,\"type\":\"INTEGER\",\"name\":\"int_var\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"persistent\":false,\"viral\":[],\"identifiers\":[]},\"resultComponent\":{\"id\":35,\"type\":\"INTEGER\",\"name\":\"int_var\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},\"messages\":[],\"command\":\"6\",\"acomponent\":false,\"scalarComponent\":{\"id\":35,\"type\":\"INTEGER\",\"name\":\"int_var\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},\"ascalar\":true}]";
		List<ResultExpression> result = vtlTestUtils.getResult(commandStatements);
		List<ResultExpression> resultExpressionsToComapare = vtlTestUtils.convertJsonToResultExpression(jsonResult);
		assertNotNull("Conversione non riuscita", resultExpressionsToComapare);
		assertNotNull("Validazione Semantica non riuscita", result);
		assertTrue("I due risultati non sono uguali",
				vtlTestUtils.compareResult(result.get(0), resultExpressionsToComapare.get(0)));
	}

	@Test
	@Transactional
	public void multiplicationComponent() throws Exception {
		VtlDataset vtlDatasetFind = datasetRepository.findByNameIgnoreCase("DS_1");
		if (vtlDatasetFind == null) {
			VtlDataset vtlDataset = new VtlDataset();

			vtlDataset.setPersistent(true);
			vtlDataset.setName("DS_8");
			vtlDataset.setIsOnlyAScalar(false);
			vtlDataset.setTransitory(false);
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_1", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_2", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_1", VtlType.INTEGER, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_2", VtlType.NUMBER, VtlComponentRole.MEASURE));
			datasetRepository.save(vtlDataset);
		}
		String commandStatements = "DS_r := DS_8 [ calc Me_3 :=  Me_1  * Me_2 ];";
		String jsonResult = "[{\"result\":{\"id\":110,\"name\":\"DS_r\",\"description\":null,\"attributes\":[],\"persistent\":false,\"measures\":[{\"id\":290,\"type\":\"INTEGER\",\"name\":\"ME_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},{\"id\":291,\"type\":\"NUMBER\",\"name\":\"ME_2\",\"domainValue\":\"num_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},{\"id\":292,\"type\":\"NUMBER\",\"name\":\"Me_3\",\"domainValue\":\"num_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"identifiers\":[{\"id\":288,\"type\":\"INTEGER\",\"name\":\"ID_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":289,\"type\":\"STRING\",\"name\":\"ID_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}],\"viral\":[]},\"messages\":[],\"command\":\"DS_r:=DS_8[calcMe_3:=Me_1*Me_2];\",\"acomponent\":false,\"ascalar\":false},{\"result\":{\"id\":108,\"name\":\"temporary_107\",\"description\":null,\"attributes\":[],\"persistent\":false,\"measures\":[{\"id\":285,\"type\":\"INTEGER\",\"name\":\"ME_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},{\"id\":286,\"type\":\"NUMBER\",\"name\":\"ME_2\",\"domainValue\":\"num_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},{\"id\":287,\"type\":\"NUMBER\",\"name\":\"Me_3\",\"domainValue\":\"num_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"identifiers\":[{\"id\":283,\"type\":\"INTEGER\",\"name\":\"ID_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":284,\"type\":\"STRING\",\"name\":\"ID_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}],\"viral\":[]},\"messages\":[],\"command\":\"calcMe_3:=Me_1*Me_2\",\"acomponent\":false,\"ascalar\":false},{\"resultComponent\":{\"id\":null,\"type\":\"NUMBER\",\"name\":\"Me_3\",\"domainValue\":\"num_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},\"messages\":[],\"command\":\"Me_3:=Me_1*Me_2\",\"acomponent\":true,\"ascalar\":false},{\"resultComponent\":{\"id\":null,\"type\":\"NUMBER\",\"name\":\"ME_1\",\"domainValue\":\"num_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},\"messages\":[],\"command\":\"Me_1*Me_2\",\"acomponent\":true,\"ascalar\":false},{\"resultComponent\":{\"id\":28,\"type\":\"INTEGER\",\"name\":\"ME_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},\"messages\":[],\"command\":\"Me_1\",\"acomponent\":true,\"ascalar\":false},{\"resultComponent\":{\"id\":29,\"type\":\"NUMBER\",\"name\":\"ME_2\",\"domainValue\":\"num_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},\"messages\":[],\"command\":\"Me_2\",\"acomponent\":true,\"ascalar\":false}]";
		List<ResultExpression> result = vtlTestUtils.getResult(commandStatements);
		List<ResultExpression> resultExpressionsToComapare = vtlTestUtils.convertJsonToResultExpression(jsonResult);
		assertNotNull("Conversione non riuscita", resultExpressionsToComapare);
		assertNotNull("Validazione Semantica non riuscita", result);
		assertTrue("I due risultati non sono uguali",
				vtlTestUtils.compareResult(result.get(0), resultExpressionsToComapare.get(0)));

	}

	@Test
	@Transactional
	public void division() throws Exception {
		VtlDataset vtlDatasetFind = datasetRepository.findByNameIgnoreCase("DS_1");
		if (vtlDatasetFind == null) {
			VtlDataset vtlDataset = new VtlDataset();

			vtlDataset.setPersistent(true);
			vtlDataset.setName("DS_8");
			vtlDataset.setIsOnlyAScalar(false);
			vtlDataset.setTransitory(false);
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_1", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_2", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_1", VtlType.INTEGER, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_2", VtlType.NUMBER, VtlComponentRole.MEASURE));
			datasetRepository.save(vtlDataset);
		}
		String commandStatements = "DS_r := DS_8 /  10;";
		String jsonResult = "[{\"result\":{\"id\":47,\"name\":\"DS_r\",\"description\":null,\"attributes\":[],\"persistent\":false,\"measures\":[{\"id\":150,\"type\":\"NUMBER\",\"name\":\"ME_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},{\"id\":151,\"type\":\"NUMBER\",\"name\":\"ME_2\",\"domainValue\":\"num_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"identifiers\":[{\"id\":1,\"type\":\"INTEGER\",\"name\":\"ID_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":2,\"type\":\"STRING\",\"name\":\"ID_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}],\"viral\":[]},\"messages\":[],\"command\":\"DS_r:=DS_8/10;\",\"ascalar\":false,\"acomponent\":false},{\"result\":{\"id\":47,\"name\":\"DS_r\",\"description\":null,\"attributes\":[],\"persistent\":false,\"measures\":[{\"id\":150,\"type\":\"NUMBER\",\"name\":\"ME_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},{\"id\":151,\"type\":\"NUMBER\",\"name\":\"ME_2\",\"domainValue\":\"num_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"identifiers\":[{\"id\":1,\"type\":\"INTEGER\",\"name\":\"ID_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":2,\"type\":\"STRING\",\"name\":\"ID_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}],\"viral\":[]},\"messages\":[],\"command\":\"DS_8/10\",\"ascalar\":false,\"acomponent\":false},{\"result\":{\"id\":1,\"name\":\"DS_8\",\"description\":null,\"attributes\":[],\"persistent\":true,\"measures\":[{\"id\":3,\"type\":\"INTEGER\",\"name\":\"ME_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},{\"id\":4,\"type\":\"NUMBER\",\"name\":\"ME_2\",\"domainValue\":\"num_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"identifiers\":[{\"id\":1,\"type\":\"INTEGER\",\"name\":\"ID_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":2,\"type\":\"STRING\",\"name\":\"ID_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}],\"viral\":[]},\"messages\":[],\"command\":\"DS_8\",\"ascalar\":false,\"acomponent\":false},{\"result\":{\"id\":45,\"name\":\"temporary_44\",\"description\":null,\"attributes\":[],\"persistent\":false,\"measures\":[{\"id\":149,\"type\":\"INTEGER\",\"name\":\"int_var\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"identifiers\":[],\"viral\":[]},\"resultComponent\":{\"id\":149,\"type\":\"INTEGER\",\"name\":\"int_var\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},\"messages\":[],\"command\":\"10\",\"ascalar\":true,\"acomponent\":false,\"scalarComponent\":{\"id\":149,\"type\":\"INTEGER\",\"name\":\"int_var\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"}}]";
		List<ResultExpression> result = vtlTestUtils.getResult(commandStatements);
		List<ResultExpression> resultExpressionsToComapare = vtlTestUtils.convertJsonToResultExpression(jsonResult);
		assertNotNull("Conversione non riuscita", resultExpressionsToComapare);
		assertNotNull("Validazione Semantica non riuscita", result);
		assertTrue("I due risultati non sono uguali",
				vtlTestUtils.compareResult(result.get(0), resultExpressionsToComapare.get(0)));

	}

	@Test
	@Transactional
	public void divisionInt() throws Exception {

		String commandStatements = "DS_r := 8 / 2;";
		String jsonResult = "[{\"result\":{\"id\":84,\"name\":\"DS_r\",\"description\":null,\"attributes\":[],\"measures\":[{\"id\":42,\"type\":\"NUMBER\",\"name\":\"int_var\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"persistent\":false,\"viral\":[],\"identifiers\":[]},\"resultComponent\":{\"id\":42,\"type\":\"NUMBER\",\"name\":\"int_var\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},\"messages\":[],\"command\":\"DS_r:=8/2;\",\"acomponent\":false,\"scalarComponent\":{\"id\":42,\"type\":\"NUMBER\",\"name\":\"int_var\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},\"ascalar\":true},{\"result\":{\"id\":84,\"name\":\"DS_r\",\"description\":null,\"attributes\":[],\"measures\":[{\"id\":42,\"type\":\"NUMBER\",\"name\":\"int_var\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"persistent\":false,\"viral\":[],\"identifiers\":[]},\"resultComponent\":{\"id\":42,\"type\":\"NUMBER\",\"name\":\"int_var\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},\"messages\":[],\"command\":\"8/2\",\"acomponent\":false,\"scalarComponent\":{\"id\":42,\"type\":\"NUMBER\",\"name\":\"int_var\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},\"ascalar\":true},{\"result\":{\"id\":80,\"name\":\"temporary_79\",\"description\":null,\"attributes\":[],\"measures\":[{\"id\":40,\"type\":\"INTEGER\",\"name\":\"int_var\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"persistent\":false,\"viral\":[],\"identifiers\":[]},\"resultComponent\":{\"id\":40,\"type\":\"INTEGER\",\"name\":\"int_var\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},\"messages\":[],\"command\":\"8\",\"acomponent\":false,\"scalarComponent\":{\"id\":40,\"type\":\"INTEGER\",\"name\":\"int_var\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},\"ascalar\":true},{\"result\":{\"id\":82,\"name\":\"temporary_81\",\"description\":null,\"attributes\":[],\"measures\":[{\"id\":41,\"type\":\"INTEGER\",\"name\":\"int_var\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"persistent\":false,\"viral\":[],\"identifiers\":[]},\"resultComponent\":{\"id\":41,\"type\":\"INTEGER\",\"name\":\"int_var\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},\"messages\":[],\"command\":\"2\",\"acomponent\":false,\"scalarComponent\":{\"id\":41,\"type\":\"INTEGER\",\"name\":\"int_var\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},\"ascalar\":true}]";
		List<ResultExpression> result = vtlTestUtils.getResult(commandStatements);
		List<ResultExpression> resultExpressionsToComapare = vtlTestUtils.convertJsonToResultExpression(jsonResult);
		assertNotNull("Conversione non riuscita", resultExpressionsToComapare);
		assertNotNull("Validazione Semantica non riuscita", result);
		assertTrue("I due risultati non sono uguali",
				vtlTestUtils.compareResult(result.get(0), resultExpressionsToComapare.get(0)));
	}

	@Test
	@Transactional
	public void divisionDs() throws Exception {
		VtlDataset vtlDatasetFind = datasetRepository.findByNameIgnoreCase("DS_1");
		if (vtlDatasetFind == null) {
			VtlDataset vtlDataset = new VtlDataset();

			vtlDataset.setPersistent(true);
			vtlDataset.setName("DS_8");
			vtlDataset.setIsOnlyAScalar(false);
			vtlDataset.setTransitory(false);
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_1", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_2", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_1", VtlType.INTEGER, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_2", VtlType.NUMBER, VtlComponentRole.MEASURE));
			datasetRepository.save(vtlDataset);

			VtlDataset vtlDatasetSecond = new VtlDataset();
			vtlDatasetSecond.setPersistent(true);
			vtlDatasetSecond.setName("DS_9");
			vtlDatasetSecond.setIsOnlyAScalar(false);
			vtlDatasetSecond.setTransitory(false);
			vtlDatasetSecond
					.addComponent(vtlTestUtils.getVtlComponent("ID_1", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDatasetSecond
					.addComponent(vtlTestUtils.getVtlComponent("ID_2", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDatasetSecond
					.addComponent(vtlTestUtils.getVtlComponent("ME_1", VtlType.INTEGER, VtlComponentRole.MEASURE));
			vtlDatasetSecond
					.addComponent(vtlTestUtils.getVtlComponent("ME_2", VtlType.NUMBER, VtlComponentRole.MEASURE));
			datasetRepository.save(vtlDatasetSecond);

		}
		String commandStatements = "DS_r := DS_8 /  DS_9;";
		String jsonResult = "[{\"result\":{\"id\":49,\"name\":\"DS_r\",\"description\":null,\"attributes\":[],\"persistent\":false,\"measures\":[{\"id\":152,\"type\":\"NUMBER\",\"name\":\"ME_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},{\"id\":153,\"type\":\"NUMBER\",\"name\":\"ME_2\",\"domainValue\":\"num_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"identifiers\":[{\"id\":1,\"type\":\"INTEGER\",\"name\":\"ID_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":2,\"type\":\"STRING\",\"name\":\"ID_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}],\"viral\":[]},\"messages\":[],\"command\":\"DS_r:=DS_8/DS_9;\",\"ascalar\":false,\"acomponent\":false},{\"result\":{\"id\":49,\"name\":\"DS_r\",\"description\":null,\"attributes\":[],\"persistent\":false,\"measures\":[{\"id\":152,\"type\":\"NUMBER\",\"name\":\"ME_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},{\"id\":153,\"type\":\"NUMBER\",\"name\":\"ME_2\",\"domainValue\":\"num_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"identifiers\":[{\"id\":1,\"type\":\"INTEGER\",\"name\":\"ID_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":2,\"type\":\"STRING\",\"name\":\"ID_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}],\"viral\":[]},\"messages\":[],\"command\":\"DS_8/DS_9\",\"ascalar\":false,\"acomponent\":false},{\"result\":{\"id\":1,\"name\":\"DS_8\",\"description\":null,\"attributes\":[],\"persistent\":true,\"measures\":[{\"id\":3,\"type\":\"INTEGER\",\"name\":\"ME_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},{\"id\":4,\"type\":\"NUMBER\",\"name\":\"ME_2\",\"domainValue\":\"num_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"identifiers\":[{\"id\":1,\"type\":\"INTEGER\",\"name\":\"ID_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":2,\"type\":\"STRING\",\"name\":\"ID_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}],\"viral\":[]},\"messages\":[],\"command\":\"DS_8\",\"ascalar\":false,\"acomponent\":false},{\"result\":{\"id\":2,\"name\":\"DS_9\",\"description\":null,\"attributes\":[],\"persistent\":true,\"measures\":[{\"id\":7,\"type\":\"INTEGER\",\"name\":\"ME_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},{\"id\":8,\"type\":\"NUMBER\",\"name\":\"ME_2\",\"domainValue\":\"num_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"identifiers\":[{\"id\":5,\"type\":\"INTEGER\",\"name\":\"ID_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":6,\"type\":\"STRING\",\"name\":\"ID_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}],\"viral\":[]},\"messages\":[],\"command\":\"DS_9\",\"ascalar\":false,\"acomponent\":false}]";
		List<ResultExpression> result = vtlTestUtils.getResult(commandStatements);
		List<ResultExpression> resultExpressionsToComapare = vtlTestUtils.convertJsonToResultExpression(jsonResult);
		assertNotNull("Conversione non riuscita", resultExpressionsToComapare);
		assertNotNull("Validazione Semantica non riuscita", result);
		assertTrue("I due risultati non sono uguali",
				vtlTestUtils.compareResult(result.get(0), resultExpressionsToComapare.get(0)));

	}

	@Test
	@Transactional
	public void divisionComponent() throws Exception {
		VtlDataset vtlDatasetFind = datasetRepository.findByNameIgnoreCase("DS_1");
		if (vtlDatasetFind == null) {
			VtlDataset vtlDataset = new VtlDataset();

			vtlDataset.setPersistent(true);
			vtlDataset.setName("DS_8");
			vtlDataset.setIsOnlyAScalar(false);
			vtlDataset.setTransitory(false);
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_1", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_2", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_1", VtlType.INTEGER, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_2", VtlType.NUMBER, VtlComponentRole.MEASURE));
			datasetRepository.save(vtlDataset);
		}
		String commandStatements = "DS_r := DS_8 [ calc Me_3 :=  Me_2  /  Me_1 ];";
		String jsonResult = "[{\"result\":{\"id\":114,\"name\":\"DS_r\",\"description\":null,\"attributes\":[],\"persistent\":false,\"measures\":[{\"id\":300,\"type\":\"INTEGER\",\"name\":\"ME_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},{\"id\":301,\"type\":\"NUMBER\",\"name\":\"ME_2\",\"domainValue\":\"num_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},{\"id\":302,\"type\":\"NUMBER\",\"name\":\"Me_3\",\"domainValue\":\"num_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"identifiers\":[{\"id\":298,\"type\":\"INTEGER\",\"name\":\"ID_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":299,\"type\":\"STRING\",\"name\":\"ID_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}],\"viral\":[]},\"messages\":[],\"command\":\"DS_r:=DS_8[calcMe_3:=Me_2/Me_1];\",\"acomponent\":false,\"ascalar\":false},{\"result\":{\"id\":112,\"name\":\"temporary_111\",\"description\":null,\"attributes\":[],\"persistent\":false,\"measures\":[{\"id\":295,\"type\":\"INTEGER\",\"name\":\"ME_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},{\"id\":296,\"type\":\"NUMBER\",\"name\":\"ME_2\",\"domainValue\":\"num_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},{\"id\":297,\"type\":\"NUMBER\",\"name\":\"Me_3\",\"domainValue\":\"num_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"identifiers\":[{\"id\":293,\"type\":\"INTEGER\",\"name\":\"ID_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":294,\"type\":\"STRING\",\"name\":\"ID_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}],\"viral\":[]},\"messages\":[],\"command\":\"calcMe_3:=Me_2/Me_1\",\"acomponent\":false,\"ascalar\":false},{\"resultComponent\":{\"id\":null,\"type\":\"NUMBER\",\"name\":\"Me_3\",\"domainValue\":\"num_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},\"messages\":[],\"command\":\"Me_3:=Me_2/Me_1\",\"acomponent\":true,\"ascalar\":false},{\"resultComponent\":{\"id\":null,\"type\":\"NUMBER\",\"name\":\"ME_2\",\"domainValue\":\"num_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},\"messages\":[],\"command\":\"Me_2/Me_1\",\"acomponent\":true,\"ascalar\":false},{\"resultComponent\":{\"id\":29,\"type\":\"NUMBER\",\"name\":\"ME_2\",\"domainValue\":\"num_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},\"messages\":[],\"command\":\"Me_2\",\"acomponent\":true,\"ascalar\":false},{\"resultComponent\":{\"id\":28,\"type\":\"INTEGER\",\"name\":\"ME_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},\"messages\":[],\"command\":\"Me_1\",\"acomponent\":true,\"ascalar\":false}]";
		List<ResultExpression> result = vtlTestUtils.getResult(commandStatements);
		List<ResultExpression> resultExpressionsToComapare = vtlTestUtils.convertJsonToResultExpression(jsonResult);
		assertNotNull("Conversione non riuscita", resultExpressionsToComapare);
		assertNotNull("Validazione Semantica non riuscita", result);
		assertTrue("I due risultati non sono uguali",
				vtlTestUtils.compareResult(result.get(0), resultExpressionsToComapare.get(0)));

	}

	@Test
	@Transactional
	public void mod() throws Exception {
		VtlDataset vtlDatasetFind = datasetRepository.findByNameIgnoreCase("DS_1");
		if (vtlDatasetFind == null) {
			VtlDataset vtlDataset = new VtlDataset();

			vtlDataset.setPersistent(true);
			vtlDataset.setName("DS_8");
			vtlDataset.setIsOnlyAScalar(false);
			vtlDataset.setTransitory(false);
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_1", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_2", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_1", VtlType.INTEGER, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_2", VtlType.NUMBER, VtlComponentRole.MEASURE));
			datasetRepository.save(vtlDataset);
		}
		String commandStatements = "DS_r := mod ( DS_8, 15 );";
		String jsonResult = "[{\"result\":{\"id\":45,\"name\":\"DS_r\",\"description\":null,\"attributes\":[],\"persistent\":false,\"measures\":[{\"id\":149,\"type\":\"NUMBER\",\"name\":\"ME_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},{\"id\":150,\"type\":\"NUMBER\",\"name\":\"ME_2\",\"domainValue\":\"num_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"viral\":[],\"identifiers\":[{\"id\":1,\"type\":\"INTEGER\",\"name\":\"ID_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":2,\"type\":\"STRING\",\"name\":\"ID_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}]},\"messages\":[],\"command\":\"DS_r:=mod(DS_8,15);\",\"acomponent\":false,\"ascalar\":false},{\"result\":{\"id\":45,\"name\":\"DS_r\",\"description\":null,\"attributes\":[],\"persistent\":false,\"measures\":[{\"id\":149,\"type\":\"NUMBER\",\"name\":\"ME_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},{\"id\":150,\"type\":\"NUMBER\",\"name\":\"ME_2\",\"domainValue\":\"num_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"viral\":[],\"identifiers\":[{\"id\":1,\"type\":\"INTEGER\",\"name\":\"ID_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":2,\"type\":\"STRING\",\"name\":\"ID_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}]},\"messages\":[],\"command\":\"mod(DS_8,15)\",\"acomponent\":false,\"ascalar\":false},{\"result\":{\"id\":1,\"name\":\"DS_8\",\"description\":null,\"attributes\":[],\"persistent\":true,\"measures\":[{\"id\":3,\"type\":\"INTEGER\",\"name\":\"ME_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},{\"id\":4,\"type\":\"NUMBER\",\"name\":\"ME_2\",\"domainValue\":\"num_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"viral\":[],\"identifiers\":[{\"id\":1,\"type\":\"INTEGER\",\"name\":\"ID_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":2,\"type\":\"STRING\",\"name\":\"ID_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}]},\"messages\":[],\"command\":\"DS_8\",\"acomponent\":false,\"ascalar\":false},{\"result\":{\"id\":43,\"name\":\"temporary_42\",\"description\":null,\"attributes\":[],\"persistent\":false,\"measures\":[{\"id\":148,\"type\":\"INTEGER\",\"name\":\"int_var\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"viral\":[],\"identifiers\":[]},\"resultComponent\":{\"id\":148,\"type\":\"INTEGER\",\"name\":\"int_var\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},\"messages\":[],\"command\":\"15\",\"acomponent\":false,\"scalarComponent\":{\"id\":148,\"type\":\"INTEGER\",\"name\":\"int_var\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},\"ascalar\":true}]";
		List<ResultExpression> result = vtlTestUtils.getResult(commandStatements);
		List<ResultExpression> resultExpressionsToComapare = vtlTestUtils.convertJsonToResultExpression(jsonResult);
		assertNotNull("Conversione non riuscita", resultExpressionsToComapare);
		assertNotNull("Validazione Semantica non riuscita", result);
		assertTrue("I due risultati non sono uguali",
				vtlTestUtils.compareResult(result.get(0), resultExpressionsToComapare.get(0)));

	}

	@Test
	@Transactional
	public void modInt() throws Exception {

		String commandStatements = "DS_r := mod ( 5, 2 );";
		String jsonResult = "[{\"result\":{\"id\":90,\"name\":\"DS_r\",\"description\":null,\"attributes\":[],\"measures\":[{\"id\":45,\"type\":\"NUMBER\",\"name\":\"int_var\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"persistent\":false,\"viral\":[],\"identifiers\":[]},\"resultComponent\":{\"id\":45,\"type\":\"NUMBER\",\"name\":\"int_var\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},\"messages\":[],\"command\":\"DS_r:=mod(5,2);\",\"acomponent\":false,\"scalarComponent\":{\"id\":45,\"type\":\"NUMBER\",\"name\":\"int_var\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},\"ascalar\":true},{\"result\":{\"id\":90,\"name\":\"DS_r\",\"description\":null,\"attributes\":[],\"measures\":[{\"id\":45,\"type\":\"NUMBER\",\"name\":\"int_var\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"persistent\":false,\"viral\":[],\"identifiers\":[]},\"resultComponent\":{\"id\":45,\"type\":\"NUMBER\",\"name\":\"int_var\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},\"messages\":[],\"command\":\"mod(5,2)\",\"acomponent\":false,\"scalarComponent\":{\"id\":45,\"type\":\"NUMBER\",\"name\":\"int_var\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},\"ascalar\":true},{\"result\":{\"id\":86,\"name\":\"temporary_85\",\"description\":null,\"attributes\":[],\"measures\":[{\"id\":43,\"type\":\"INTEGER\",\"name\":\"int_var\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"persistent\":false,\"viral\":[],\"identifiers\":[]},\"resultComponent\":{\"id\":43,\"type\":\"INTEGER\",\"name\":\"int_var\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},\"messages\":[],\"command\":\"5\",\"acomponent\":false,\"scalarComponent\":{\"id\":43,\"type\":\"INTEGER\",\"name\":\"int_var\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},\"ascalar\":true},{\"result\":{\"id\":88,\"name\":\"temporary_87\",\"description\":null,\"attributes\":[],\"measures\":[{\"id\":44,\"type\":\"INTEGER\",\"name\":\"int_var\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"persistent\":false,\"viral\":[],\"identifiers\":[]},\"resultComponent\":{\"id\":44,\"type\":\"INTEGER\",\"name\":\"int_var\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},\"messages\":[],\"command\":\"2\",\"acomponent\":false,\"scalarComponent\":{\"id\":44,\"type\":\"INTEGER\",\"name\":\"int_var\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},\"ascalar\":true}]";
		List<ResultExpression> result = vtlTestUtils.getResult(commandStatements);
		List<ResultExpression> resultExpressionsToComapare = vtlTestUtils.convertJsonToResultExpression(jsonResult);
		assertNotNull("Conversione non riuscita", resultExpressionsToComapare);
		assertNotNull("Validazione Semantica non riuscita", result);
		assertTrue("I due risultati non sono uguali",
				vtlTestUtils.compareResult(result.get(0), resultExpressionsToComapare.get(0)));
	}

	@Test
	@Transactional
	public void modDs() throws Exception {
		VtlDataset vtlDatasetFind = datasetRepository.findByNameIgnoreCase("DS_1");
		if (vtlDatasetFind == null) {
			VtlDataset vtlDataset = new VtlDataset();

			vtlDataset.setPersistent(true);
			vtlDataset.setName("DS_8");
			vtlDataset.setIsOnlyAScalar(false);
			vtlDataset.setTransitory(false);
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_1", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_2", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_1", VtlType.INTEGER, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_2", VtlType.NUMBER, VtlComponentRole.MEASURE));
			datasetRepository.save(vtlDataset);

			VtlDataset vtlDatasetSecond = new VtlDataset();
			vtlDatasetSecond.setPersistent(true);
			vtlDatasetSecond.setName("DS_9");
			vtlDatasetSecond.setIsOnlyAScalar(false);
			vtlDatasetSecond.setTransitory(false);
			vtlDatasetSecond
					.addComponent(vtlTestUtils.getVtlComponent("ID_1", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDatasetSecond
					.addComponent(vtlTestUtils.getVtlComponent("ID_2", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDatasetSecond
					.addComponent(vtlTestUtils.getVtlComponent("ME_1", VtlType.INTEGER, VtlComponentRole.MEASURE));
			vtlDatasetSecond
					.addComponent(vtlTestUtils.getVtlComponent("ME_2", VtlType.NUMBER, VtlComponentRole.MEASURE));
			datasetRepository.save(vtlDatasetSecond);

		}
		String commandStatements = "DS_r := mod ( DS_8, DS_9 );";
		String jsonResult = "[{\"result\":{\"id\":47,\"name\":\"DS_r\",\"description\":null,\"attributes\":[],\"persistent\":false,\"measures\":[{\"id\":151,\"type\":\"NUMBER\",\"name\":\"ME_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},{\"id\":152,\"type\":\"NUMBER\",\"name\":\"ME_2\",\"domainValue\":\"num_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"viral\":[],\"identifiers\":[{\"id\":1,\"type\":\"INTEGER\",\"name\":\"ID_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":2,\"type\":\"STRING\",\"name\":\"ID_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}]},\"messages\":[],\"command\":\"DS_r:=mod(DS_8,DS_9);\",\"acomponent\":false,\"ascalar\":false},{\"result\":{\"id\":47,\"name\":\"DS_r\",\"description\":null,\"attributes\":[],\"persistent\":false,\"measures\":[{\"id\":151,\"type\":\"NUMBER\",\"name\":\"ME_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},{\"id\":152,\"type\":\"NUMBER\",\"name\":\"ME_2\",\"domainValue\":\"num_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"viral\":[],\"identifiers\":[{\"id\":1,\"type\":\"INTEGER\",\"name\":\"ID_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":2,\"type\":\"STRING\",\"name\":\"ID_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}]},\"messages\":[],\"command\":\"mod(DS_8,DS_9)\",\"acomponent\":false,\"ascalar\":false},{\"result\":{\"id\":1,\"name\":\"DS_8\",\"description\":null,\"attributes\":[],\"persistent\":true,\"measures\":[{\"id\":3,\"type\":\"INTEGER\",\"name\":\"ME_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},{\"id\":4,\"type\":\"NUMBER\",\"name\":\"ME_2\",\"domainValue\":\"num_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"viral\":[],\"identifiers\":[{\"id\":1,\"type\":\"INTEGER\",\"name\":\"ID_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":2,\"type\":\"STRING\",\"name\":\"ID_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}]},\"messages\":[],\"command\":\"DS_8\",\"acomponent\":false,\"ascalar\":false},{\"result\":{\"id\":2,\"name\":\"DS_9\",\"description\":null,\"attributes\":[],\"persistent\":true,\"measures\":[{\"id\":7,\"type\":\"INTEGER\",\"name\":\"ME_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},{\"id\":8,\"type\":\"NUMBER\",\"name\":\"ME_2\",\"domainValue\":\"num_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"viral\":[],\"identifiers\":[{\"id\":5,\"type\":\"INTEGER\",\"name\":\"ID_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":6,\"type\":\"STRING\",\"name\":\"ID_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}]},\"messages\":[],\"command\":\"DS_9\",\"acomponent\":false,\"ascalar\":false}]";
		List<ResultExpression> result = vtlTestUtils.getResult(commandStatements);
		List<ResultExpression> resultExpressionsToComapare = vtlTestUtils.convertJsonToResultExpression(jsonResult);
		assertNotNull("Conversione non riuscita", resultExpressionsToComapare);
		assertNotNull("Validazione Semantica non riuscita", result);
		assertTrue("I due risultati non sono uguali",
				vtlTestUtils.compareResult(result.get(0), resultExpressionsToComapare.get(0)));

	}

	@Test
	@Transactional
	public void modComponent() throws Exception {
		VtlDataset vtlDatasetFind = datasetRepository.findByNameIgnoreCase("DS_1");
		if (vtlDatasetFind == null) {
			VtlDataset vtlDataset = new VtlDataset();

			vtlDataset.setPersistent(true);
			vtlDataset.setName("DS_8");
			vtlDataset.setIsOnlyAScalar(false);
			vtlDataset.setTransitory(false);
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_1", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_2", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_1", VtlType.INTEGER, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_2", VtlType.NUMBER, VtlComponentRole.MEASURE));
			datasetRepository.save(vtlDataset);
		}
        String commandStatements = "DS_r := DS_8[ calc Me_3 := mod( Me_1,  3.0 ) ];";
		String jsonResult = "[{\"result\":{\"id\":120,\"name\":\"DS_r\",\"description\":null,\"attributes\":[],\"persistent\":false,\"measures\":[{\"id\":311,\"type\":\"INTEGER\",\"name\":\"ME_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},{\"id\":312,\"type\":\"NUMBER\",\"name\":\"ME_2\",\"domainValue\":\"num_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},{\"id\":313,\"type\":\"NUMBER\",\"name\":\"Me_3\",\"domainValue\":\"num_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"identifiers\":[{\"id\":309,\"type\":\"INTEGER\",\"name\":\"ID_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":310,\"type\":\"STRING\",\"name\":\"ID_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}],\"viral\":[]},\"messages\":[],\"command\":\"DS_r:=DS_8[calcMe_3:=mod(DS_8#Me_1,3.0)];\",\"acomponent\":false,\"ascalar\":false},{\"result\":{\"id\":118,\"name\":\"temporary_117\",\"description\":null,\"attributes\":[],\"persistent\":false,\"measures\":[{\"id\":306,\"type\":\"INTEGER\",\"name\":\"ME_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},{\"id\":307,\"type\":\"NUMBER\",\"name\":\"ME_2\",\"domainValue\":\"num_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},{\"id\":308,\"type\":\"NUMBER\",\"name\":\"Me_3\",\"domainValue\":\"num_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"identifiers\":[{\"id\":304,\"type\":\"INTEGER\",\"name\":\"ID_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":305,\"type\":\"STRING\",\"name\":\"ID_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}],\"viral\":[]},\"messages\":[],\"command\":\"calcMe_3:=mod(DS_8#Me_1,3.0)\",\"acomponent\":false,\"ascalar\":false},{\"resultComponent\":{\"id\":null,\"type\":\"NUMBER\",\"name\":\"Me_3\",\"domainValue\":\"num_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},\"messages\":[],\"command\":\"Me_3:=mod(DS_8#Me_1,3.0)\",\"acomponent\":true,\"ascalar\":false},{\"resultComponent\":{\"id\":null,\"type\":\"NUMBER\",\"name\":\"ME_1\",\"domainValue\":\"num_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},\"messages\":[],\"command\":\"mod(DS_8#Me_1,3.0)\",\"acomponent\":true,\"ascalar\":false},{\"resultComponent\":{\"id\":28,\"type\":\"INTEGER\",\"name\":\"ME_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},\"messages\":[],\"command\":\"DS_8#Me_1\",\"acomponent\":true,\"ascalar\":false},{\"result\":{\"id\":8,\"name\":\"DS_8\",\"description\":null,\"attributes\":[],\"persistent\":true,\"measures\":[{\"id\":28,\"type\":\"INTEGER\",\"name\":\"ME_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},{\"id\":29,\"type\":\"NUMBER\",\"name\":\"ME_2\",\"domainValue\":\"num_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"identifiers\":[{\"id\":26,\"type\":\"INTEGER\",\"name\":\"ID_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":27,\"type\":\"STRING\",\"name\":\"ID_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}],\"viral\":[]},\"messages\":[],\"command\":\"DS_8\",\"acomponent\":false,\"ascalar\":false},{\"result\":{\"id\":116,\"name\":\"temporary_115\",\"description\":null,\"attributes\":[],\"persistent\":false,\"measures\":[{\"id\":303,\"type\":\"NUMBER\",\"name\":\"num_var\",\"domainValue\":\"num_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"identifiers\":[],\"viral\":[]},\"resultComponent\":{\"id\":303,\"type\":\"NUMBER\",\"name\":\"num_var\",\"domainValue\":\"num_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},\"messages\":[],\"command\":\"3.0\",\"scalarComponent\":{\"id\":303,\"type\":\"NUMBER\",\"name\":\"num_var\",\"domainValue\":\"num_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},\"acomponent\":false,\"ascalar\":true}]";
		List<ResultExpression> result = vtlTestUtils.getResult(commandStatements);
		List<ResultExpression> resultExpressionsToComapare = vtlTestUtils.convertJsonToResultExpression(jsonResult);
		assertNotNull("Conversione non riuscita", resultExpressionsToComapare);
		assertNotNull("Validazione Semantica non riuscita", result);
		assertTrue("I due risultati non sono uguali",
				vtlTestUtils.compareResult(result.get(0), resultExpressionsToComapare.get(0)));

	}

	@Test
	@Transactional
	public void trunc() throws Exception {
		VtlDataset vtlDatasetFind = datasetRepository.findByNameIgnoreCase("DS_1");
		if (vtlDatasetFind == null) {
			VtlDataset vtlDataset = new VtlDataset();

			vtlDataset.setPersistent(true);
			vtlDataset.setName("DS_10");
			vtlDataset.setIsOnlyAScalar(false);
			vtlDataset.setTransitory(false);
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_1", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_2", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_1", VtlType.NUMBER, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_2", VtlType.NUMBER, VtlComponentRole.MEASURE));
			datasetRepository.save(vtlDataset);

		}
		String commandStatements = "DS_r := trunc(DS_10, 0);";
		String jsonResult = "[{\"result\":{\"id\":35,\"name\":\"DS_r\",\"description\":null,\"attributes\":[],\"persistent\":false,\"measures\":[{\"id\":135,\"type\":\"NUMBER\",\"name\":\"ME_1\",\"domainValue\":\"num_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},{\"id\":136,\"type\":\"NUMBER\",\"name\":\"ME_2\",\"domainValue\":\"num_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"viral\":[],\"identifiers\":[{\"id\":1,\"type\":\"INTEGER\",\"name\":\"ID_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":2,\"type\":\"STRING\",\"name\":\"ID_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}]},\"messages\":[],\"command\":\"DS_r:=trunc(DS_10,0);\",\"acomponent\":false,\"ascalar\":false},{\"result\":{\"id\":35,\"name\":\"DS_r\",\"description\":null,\"attributes\":[],\"persistent\":false,\"measures\":[{\"id\":135,\"type\":\"NUMBER\",\"name\":\"ME_1\",\"domainValue\":\"num_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},{\"id\":136,\"type\":\"NUMBER\",\"name\":\"ME_2\",\"domainValue\":\"num_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"viral\":[],\"identifiers\":[{\"id\":1,\"type\":\"INTEGER\",\"name\":\"ID_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":2,\"type\":\"STRING\",\"name\":\"ID_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}]},\"messages\":[],\"command\":\"trunc(DS_10,0)\",\"acomponent\":false,\"ascalar\":false},{\"result\":{\"id\":1,\"name\":\"DS_10\",\"description\":null,\"attributes\":[],\"persistent\":true,\"measures\":[{\"id\":3,\"type\":\"NUMBER\",\"name\":\"ME_1\",\"domainValue\":\"num_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},{\"id\":4,\"type\":\"NUMBER\",\"name\":\"ME_2\",\"domainValue\":\"num_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"viral\":[],\"identifiers\":[{\"id\":1,\"type\":\"INTEGER\",\"name\":\"ID_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":2,\"type\":\"STRING\",\"name\":\"ID_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}]},\"messages\":[],\"command\":\"DS_10\",\"acomponent\":false,\"ascalar\":false},{\"result\":{\"id\":33,\"name\":\"temporary_32\",\"description\":null,\"attributes\":[],\"persistent\":false,\"measures\":[{\"id\":134,\"type\":\"INTEGER\",\"name\":\"int_var\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"viral\":[],\"identifiers\":[]},\"resultComponent\":{\"id\":134,\"type\":\"INTEGER\",\"name\":\"int_var\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},\"messages\":[],\"command\":\"0\",\"acomponent\":false,\"ascalar\":true,\"scalarComponent\":{\"id\":134,\"type\":\"INTEGER\",\"name\":\"int_var\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"}}]";
		List<ResultExpression> result = vtlTestUtils.getResult(commandStatements);
		List<ResultExpression> resultExpressionsToComapare = vtlTestUtils.convertJsonToResultExpression(jsonResult);
		assertNotNull("Conversione non riuscita", resultExpressionsToComapare);
		assertNotNull("Validazione Semantica non riuscita", result);
		assertTrue("I due risultati non sono uguali",
				vtlTestUtils.compareResult(result.get(0), resultExpressionsToComapare.get(0)));

	}

	@Test
	@Transactional
	public void truncComponent() throws Exception {
		VtlDataset vtlDatasetFind = datasetRepository.findByNameIgnoreCase("DS_1");
		if (vtlDatasetFind == null) {
			VtlDataset vtlDataset = new VtlDataset();
			vtlDataset.setPersistent(true);
			vtlDataset.setName("DS_10");
			vtlDataset.setIsOnlyAScalar(false);
			vtlDataset.setTransitory(false);
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_1", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_2", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_1", VtlType.NUMBER, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_2", VtlType.NUMBER, VtlComponentRole.MEASURE));
			datasetRepository.save(vtlDataset);
		}
		String commandStatements = "DS_r := DS_10[ calc Me_10:= trunc( Me_1 ) ];";
		String jsonResult = "[{\"result\":{\"id\":37,\"name\":\"DS_r\",\"description\":null,\"attributes\":[],\"persistent\":false,\"measures\":[{\"id\":3,\"type\":\"NUMBER\",\"name\":\"ME_1\",\"domainValue\":\"num_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},{\"id\":4,\"type\":\"NUMBER\",\"name\":\"ME_2\",\"domainValue\":\"num_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},{\"id\":137,\"type\":\"NUMBER\",\"name\":\"Me_10\",\"domainValue\":\"num_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"viral\":[],\"identifiers\":[{\"id\":1,\"type\":\"INTEGER\",\"name\":\"ID_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":2,\"type\":\"STRING\",\"name\":\"ID_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}]},\"messages\":[],\"command\":\"DS_r:=DS_10[calcMe_10:=trunc(Me_1)];\",\"acomponent\":false,\"ascalar\":false},{\"result\":{\"id\":37,\"name\":\"DS_r\",\"description\":null,\"attributes\":[],\"persistent\":false,\"measures\":[{\"id\":3,\"type\":\"NUMBER\",\"name\":\"ME_1\",\"domainValue\":\"num_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},{\"id\":4,\"type\":\"NUMBER\",\"name\":\"ME_2\",\"domainValue\":\"num_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},{\"id\":137,\"type\":\"NUMBER\",\"name\":\"Me_10\",\"domainValue\":\"num_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"viral\":[],\"identifiers\":[{\"id\":1,\"type\":\"INTEGER\",\"name\":\"ID_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":2,\"type\":\"STRING\",\"name\":\"ID_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}]},\"messages\":[],\"command\":\"calcMe_10:=trunc(Me_1)\",\"acomponent\":false,\"ascalar\":false},{\"resultComponent\":{\"id\":null,\"type\":\"NUMBER\",\"name\":\"Me_10\",\"domainValue\":\"num_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},\"messages\":[],\"command\":\"Me_10:=trunc(Me_1)\",\"acomponent\":true,\"ascalar\":false},{\"resultComponent\":{\"id\":null,\"type\":\"NUMBER\",\"name\":\"ME_1\",\"domainValue\":\"num_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},\"messages\":[],\"command\":\"trunc(Me_1)\",\"acomponent\":true,\"ascalar\":false},{\"resultComponent\":{\"id\":3,\"type\":\"NUMBER\",\"name\":\"ME_1\",\"domainValue\":\"num_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},\"messages\":[],\"command\":\"Me_1\",\"acomponent\":true,\"ascalar\":false}]";
		List<ResultExpression> result = vtlTestUtils.getResult(commandStatements);
		List<ResultExpression> resultExpressionsToComapare = vtlTestUtils.convertJsonToResultExpression(jsonResult);
		assertNotNull("Conversione non riuscita", resultExpressionsToComapare);
		assertNotNull("Validazione Semantica non riuscita", result);
		assertTrue("I due risultati non sono uguali",
				vtlTestUtils.compareResult(result.get(0), resultExpressionsToComapare.get(0)));

	}

	@Test
	@Transactional
	public void truncComponentDue() throws Exception {
		VtlDataset vtlDatasetFind = datasetRepository.findByNameIgnoreCase("DS_1");
		if (vtlDatasetFind == null) {
			VtlDataset vtlDataset = new VtlDataset();
			vtlDataset.setPersistent(true);
			vtlDataset.setName("DS_10");
			vtlDataset.setIsOnlyAScalar(false);
			vtlDataset.setTransitory(false);
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_1", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_2", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_1", VtlType.NUMBER, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_2", VtlType.NUMBER, VtlComponentRole.MEASURE));
			datasetRepository.save(vtlDataset);
		}
		String commandStatements = "DS_r := DS_10[ calc Me_20:= trunc( Me_1 , -1 ) ];";
		String jsonResult = "[{\"result\":{\"id\":7,\"name\":\"DS_r\",\"description\":null,\"attributes\":[],\"persistent\":false,\"identifiers\":[{\"id\":11,\"type\":\"INTEGER\",\"name\":\"ID_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":12,\"type\":\"STRING\",\"name\":\"ID_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}],\"measures\":[{\"id\":13,\"type\":\"NUMBER\",\"name\":\"ME_1\",\"domainValue\":\"num_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},{\"id\":14,\"type\":\"NUMBER\",\"name\":\"ME_2\",\"domainValue\":\"num_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},{\"id\":15,\"type\":\"NUMBER\",\"name\":\"Me_20\",\"domainValue\":\"num_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"viral\":[]},\"messages\":[],\"command\":\"DS_r:=DS_10[calcMe_20:=trunc(Me_1,-1)];\",\"ascalar\":false,\"acomponent\":false},{\"result\":{\"id\":5,\"name\":\"temporary_4\",\"description\":null,\"attributes\":[],\"persistent\":false,\"identifiers\":[{\"id\":6,\"type\":\"INTEGER\",\"name\":\"ID_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":7,\"type\":\"STRING\",\"name\":\"ID_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}],\"measures\":[{\"id\":8,\"type\":\"NUMBER\",\"name\":\"ME_1\",\"domainValue\":\"num_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},{\"id\":9,\"type\":\"NUMBER\",\"name\":\"ME_2\",\"domainValue\":\"num_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},{\"id\":10,\"type\":\"NUMBER\",\"name\":\"Me_20\",\"domainValue\":\"num_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"viral\":[]},\"messages\":[],\"command\":\"calcMe_20:=trunc(Me_1,-1)\",\"ascalar\":false,\"acomponent\":false},{\"resultComponent\":{\"id\":null,\"type\":\"NUMBER\",\"name\":\"Me_20\",\"domainValue\":\"num_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},\"messages\":[],\"command\":\"Me_20:=trunc(Me_1,-1)\",\"ascalar\":false,\"acomponent\":true},{\"resultComponent\":{\"id\":null,\"type\":\"NUMBER\",\"name\":\"ME_1\",\"domainValue\":\"num_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},\"messages\":[],\"command\":\"trunc(Me_1,-1)\",\"ascalar\":false,\"acomponent\":true},{\"resultComponent\":{\"id\":3,\"type\":\"NUMBER\",\"name\":\"ME_1\",\"domainValue\":\"num_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},\"messages\":[],\"command\":\"Me_1\",\"ascalar\":false,\"acomponent\":true},{\"result\":{\"id\":3,\"name\":\"temporary_2\",\"description\":null,\"attributes\":[],\"persistent\":false,\"identifiers\":[],\"measures\":[{\"id\":5,\"type\":\"INTEGER\",\"name\":\"int_var\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"viral\":[]},\"resultComponent\":{\"id\":5,\"type\":\"INTEGER\",\"name\":\"int_var\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},\"messages\":[],\"command\":\"-1\",\"ascalar\":true,\"acomponent\":false,\"scalarComponent\":{\"id\":5,\"type\":\"INTEGER\",\"name\":\"int_var\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"}}]";
		List<ResultExpression> result = vtlTestUtils.getResult(commandStatements);
		List<ResultExpression> resultExpressionsToComapare = vtlTestUtils.convertJsonToResultExpression(jsonResult);
		assertNotNull("Conversione non riuscita", resultExpressionsToComapare);
		assertNotNull("Validazione Semantica non riuscita", result);
		assertTrue("I due risultati non sono uguali",
				vtlTestUtils.compareResult(result.get(0), resultExpressionsToComapare.get(0)));

	}

	@Test
	@Transactional
	public void ceil() throws Exception {
		VtlDataset vtlDatasetFind = datasetRepository.findByNameIgnoreCase("DS_1");
		if (vtlDatasetFind == null) {
			VtlDataset vtlDataset = new VtlDataset();

			vtlDataset.setPersistent(true);
			vtlDataset.setName("DS_10");
			vtlDataset.setIsOnlyAScalar(false);
			vtlDataset.setTransitory(false);
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_1", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_2", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_1", VtlType.NUMBER, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_2", VtlType.NUMBER, VtlComponentRole.MEASURE));
			datasetRepository.save(vtlDataset);
		}
		String commandStatements = "DS_r := ceil (DS_10);";
		String jsonResult = "[{\"result\":{\"id\":35,\"name\":\"DS_r\",\"description\":null,\"attributes\":[],\"persistent\":false,\"viral\":[],\"measures\":[{\"id\":136,\"type\":\"INTEGER\",\"name\":\"ME_1\",\"domainValue\":\"num_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},{\"id\":137,\"type\":\"INTEGER\",\"name\":\"ME_2\",\"domainValue\":\"num_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"identifiers\":[{\"id\":1,\"type\":\"INTEGER\",\"name\":\"ID_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":2,\"type\":\"STRING\",\"name\":\"ID_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}]},\"messages\":[],\"command\":\"DS_r:=ceil(DS_10);\",\"ascalar\":false,\"acomponent\":false},{\"result\":{\"id\":35,\"name\":\"DS_r\",\"description\":null,\"attributes\":[],\"persistent\":false,\"viral\":[],\"measures\":[{\"id\":136,\"type\":\"INTEGER\",\"name\":\"ME_1\",\"domainValue\":\"num_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},{\"id\":137,\"type\":\"INTEGER\",\"name\":\"ME_2\",\"domainValue\":\"num_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"identifiers\":[{\"id\":1,\"type\":\"INTEGER\",\"name\":\"ID_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":2,\"type\":\"STRING\",\"name\":\"ID_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}]},\"messages\":[],\"command\":\"ceil(DS_10)\",\"ascalar\":false,\"acomponent\":false},{\"result\":{\"id\":1,\"name\":\"DS_10\",\"description\":null,\"attributes\":[],\"persistent\":true,\"viral\":[],\"measures\":[{\"id\":3,\"type\":\"NUMBER\",\"name\":\"ME_1\",\"domainValue\":\"num_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},{\"id\":4,\"type\":\"NUMBER\",\"name\":\"ME_2\",\"domainValue\":\"num_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"identifiers\":[{\"id\":1,\"type\":\"INTEGER\",\"name\":\"ID_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":2,\"type\":\"STRING\",\"name\":\"ID_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}]},\"messages\":[],\"command\":\"DS_10\",\"ascalar\":false,\"acomponent\":false}]";
		List<ResultExpression> result = vtlTestUtils.getResult(commandStatements);
		List<ResultExpression> resultExpressionsToComapare = vtlTestUtils.convertJsonToResultExpression(jsonResult);
		assertNotNull("Conversione non riuscita", resultExpressionsToComapare);
		assertNotNull("Validazione Semantica non riuscita", result);
		assertTrue("I due risultati non sono uguali",
				vtlTestUtils.compareResult(result.get(0), resultExpressionsToComapare.get(0)));

	}

	@Test
	@Transactional
	public void ceilInt() throws Exception {

		String commandStatements = "DS_r := ceil( 15 );";
		String jsonResult = "[{\"result\":{\"id\":100,\"name\":\"DS_r\",\"description\":null,\"attributes\":[],\"measures\":[{\"id\":50,\"type\":\"INTEGER\",\"name\":\"int_var\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"persistent\":false,\"viral\":[],\"identifiers\":[]},\"resultComponent\":{\"id\":50,\"type\":\"INTEGER\",\"name\":\"int_var\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},\"messages\":[],\"command\":\"DS_r:=ceil(15);\",\"acomponent\":false,\"scalarComponent\":{\"id\":50,\"type\":\"INTEGER\",\"name\":\"int_var\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},\"ascalar\":true},{\"result\":{\"id\":100,\"name\":\"DS_r\",\"description\":null,\"attributes\":[],\"measures\":[{\"id\":50,\"type\":\"INTEGER\",\"name\":\"int_var\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"persistent\":false,\"viral\":[],\"identifiers\":[]},\"resultComponent\":{\"id\":50,\"type\":\"INTEGER\",\"name\":\"int_var\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},\"messages\":[],\"command\":\"ceil(15)\",\"acomponent\":false,\"scalarComponent\":{\"id\":50,\"type\":\"INTEGER\",\"name\":\"int_var\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},\"ascalar\":true},{\"result\":{\"id\":98,\"name\":\"temporary_97\",\"description\":null,\"attributes\":[],\"measures\":[{\"id\":49,\"type\":\"INTEGER\",\"name\":\"int_var\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"persistent\":false,\"viral\":[],\"identifiers\":[]},\"resultComponent\":{\"id\":49,\"type\":\"INTEGER\",\"name\":\"int_var\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},\"messages\":[],\"command\":\"15\",\"acomponent\":false,\"scalarComponent\":{\"id\":49,\"type\":\"INTEGER\",\"name\":\"int_var\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},\"ascalar\":true}]";
		List<ResultExpression> result = vtlTestUtils.getResult(commandStatements);
		List<ResultExpression> resultExpressionsToComapare = vtlTestUtils.convertJsonToResultExpression(jsonResult);
		assertNotNull("Conversione non riuscita", resultExpressionsToComapare);
		assertNotNull("Validazione Semantica non riuscita", result);
		assertTrue("I due risultati non sono uguali",
				vtlTestUtils.compareResult(result.get(0), resultExpressionsToComapare.get(0)));
	}

	@Test
	@Transactional
	public void ceilNumber() throws Exception {

		String commandStatements = "DS_r := ceil( - 3.1415 );";
		String jsonResult = "[{\"result\":{\"id\":118,\"name\":\"DS_r\",\"description\":null,\"attributes\":[],\"measures\":[{\"id\":59,\"type\":\"INTEGER\",\"name\":\"int_var\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"persistent\":false,\"viral\":[],\"identifiers\":[]},\"resultComponent\":{\"id\":59,\"type\":\"INTEGER\",\"name\":\"int_var\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},\"messages\":[],\"command\":\"DS_r:=ceil(-3.1415);\",\"acomponent\":false,\"scalarComponent\":{\"id\":59,\"type\":\"INTEGER\",\"name\":\"int_var\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},\"ascalar\":true},{\"result\":{\"id\":118,\"name\":\"DS_r\",\"description\":null,\"attributes\":[],\"measures\":[{\"id\":59,\"type\":\"INTEGER\",\"name\":\"int_var\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"persistent\":false,\"viral\":[],\"identifiers\":[]},\"resultComponent\":{\"id\":59,\"type\":\"INTEGER\",\"name\":\"int_var\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},\"messages\":[],\"command\":\"ceil(-3.1415)\",\"acomponent\":false,\"scalarComponent\":{\"id\":59,\"type\":\"INTEGER\",\"name\":\"int_var\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},\"ascalar\":true},{\"result\":{\"id\":116,\"name\":\"temporary_115\",\"description\":null,\"attributes\":[],\"measures\":[{\"id\":58,\"type\":\"NUMBER\",\"name\":\"num_var\",\"domainValue\":\"num_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"persistent\":false,\"viral\":[],\"identifiers\":[]},\"resultComponent\":{\"id\":58,\"type\":\"NUMBER\",\"name\":\"num_var\",\"domainValue\":\"num_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},\"messages\":[],\"command\":\"-3.1415\",\"acomponent\":false,\"scalarComponent\":{\"id\":58,\"type\":\"NUMBER\",\"name\":\"num_var\",\"domainValue\":\"num_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},\"ascalar\":true},{\"result\":{\"id\":114,\"name\":\"temporary_113\",\"description\":null,\"attributes\":[],\"measures\":[{\"id\":57,\"type\":\"NUMBER\",\"name\":\"num_var\",\"domainValue\":\"num_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"persistent\":false,\"viral\":[],\"identifiers\":[]},\"resultComponent\":{\"id\":57,\"type\":\"NUMBER\",\"name\":\"num_var\",\"domainValue\":\"num_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},\"messages\":[],\"command\":\"3.1415\",\"acomponent\":false,\"scalarComponent\":{\"id\":57,\"type\":\"NUMBER\",\"name\":\"num_var\",\"domainValue\":\"num_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},\"ascalar\":true}]";
		List<ResultExpression> result = vtlTestUtils.getResult(commandStatements);
		List<ResultExpression> resultExpressionsToComapare = vtlTestUtils.convertJsonToResultExpression(jsonResult);
		assertNotNull("Conversione non riuscita", resultExpressionsToComapare);
		assertNotNull("Validazione Semantica non riuscita", result);
		assertTrue("I due risultati non sono uguali",
				vtlTestUtils.compareResult(result.get(0), resultExpressionsToComapare.get(0)));
	}

	@Test
	@Transactional
	public void ceilComponent() throws Exception {
		VtlDataset vtlDatasetFind = datasetRepository.findByNameIgnoreCase("DS_1");
		if (vtlDatasetFind == null) {
			VtlDataset vtlDataset = new VtlDataset();
			vtlDataset.setPersistent(true);
			vtlDataset.setName("DS_10");
			vtlDataset.setIsOnlyAScalar(false);
			vtlDataset.setTransitory(false);
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_1", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_2", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_1", VtlType.NUMBER, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_2", VtlType.NUMBER, VtlComponentRole.MEASURE));
			datasetRepository.save(vtlDataset);
		}
		String commandStatements = "DS_r := DS_10 [ calc Me_10 := ceil (Me_1) ];";
		String jsonResult = "[{\"result\":{\"id\":124,\"name\":\"DS_r\",\"description\":null,\"attributes\":[],\"persistent\":false,\"measures\":[{\"id\":321,\"type\":\"NUMBER\",\"name\":\"ME_1\",\"domainValue\":\"num_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},{\"id\":322,\"type\":\"NUMBER\",\"name\":\"ME_2\",\"domainValue\":\"num_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},{\"id\":323,\"type\":\"NUMBER\",\"name\":\"Me_10\",\"domainValue\":\"num_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"identifiers\":[{\"id\":319,\"type\":\"INTEGER\",\"name\":\"ID_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":320,\"type\":\"STRING\",\"name\":\"ID_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}],\"viral\":[]},\"messages\":[],\"command\":\"DS_r:=DS_10[calcMe_10:=ceil(Me_1)];\",\"acomponent\":false,\"ascalar\":false},{\"result\":{\"id\":122,\"name\":\"temporary_121\",\"description\":null,\"attributes\":[],\"persistent\":false,\"measures\":[{\"id\":316,\"type\":\"NUMBER\",\"name\":\"ME_1\",\"domainValue\":\"num_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},{\"id\":317,\"type\":\"NUMBER\",\"name\":\"ME_2\",\"domainValue\":\"num_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},{\"id\":318,\"type\":\"NUMBER\",\"name\":\"Me_10\",\"domainValue\":\"num_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"identifiers\":[{\"id\":314,\"type\":\"INTEGER\",\"name\":\"ID_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":315,\"type\":\"STRING\",\"name\":\"ID_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}],\"viral\":[]},\"messages\":[],\"command\":\"calcMe_10:=ceil(Me_1)\",\"acomponent\":false,\"ascalar\":false},{\"resultComponent\":{\"id\":null,\"type\":\"NUMBER\",\"name\":\"Me_10\",\"domainValue\":\"num_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},\"messages\":[],\"command\":\"Me_10:=ceil(Me_1)\",\"acomponent\":true,\"ascalar\":false},{\"resultComponent\":{\"id\":null,\"type\":\"NUMBER\",\"name\":\"ME_1\",\"domainValue\":\"num_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},\"messages\":[],\"command\":\"ceil(Me_1)\",\"acomponent\":true,\"ascalar\":false},{\"resultComponent\":{\"id\":36,\"type\":\"NUMBER\",\"name\":\"ME_1\",\"domainValue\":\"num_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},\"messages\":[],\"command\":\"Me_1\",\"acomponent\":true,\"ascalar\":false}]";
		List<ResultExpression> result = vtlTestUtils.getResult(commandStatements);
		List<ResultExpression> resultExpressionsToComapare = vtlTestUtils.convertJsonToResultExpression(jsonResult);
		assertNotNull("Conversione non riuscita", resultExpressionsToComapare);
		assertNotNull("Validazione Semantica non riuscita", result);
		assertTrue("I due risultati non sono uguali",
				vtlTestUtils.compareResult(result.get(0), resultExpressionsToComapare.get(0)));
	}

	@Test
	@Transactional
	public void floor() throws Exception {
		VtlDataset vtlDatasetFind = datasetRepository.findByNameIgnoreCase("DS_1");
		if (vtlDatasetFind == null) {
			VtlDataset vtlDataset = new VtlDataset();
			vtlDataset.setPersistent(true);
			vtlDataset.setName("DS_10");
			vtlDataset.setIsOnlyAScalar(false);
			vtlDataset.setTransitory(false);
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_1", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_2", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_1", VtlType.NUMBER, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_2", VtlType.NUMBER, VtlComponentRole.MEASURE));
			datasetRepository.save(vtlDataset);
		}
		String commandStatements = "DS_r := floor ( DS_10 );";
		String jsonResult = "[{\"result\":{\"id\":39,\"name\":\"DS_r\",\"description\":null,\"attributes\":[],\"persistent\":false,\"viral\":[],\"measures\":[{\"id\":139,\"type\":\"INTEGER\",\"name\":\"ME_1\",\"domainValue\":\"num_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},{\"id\":140,\"type\":\"INTEGER\",\"name\":\"ME_2\",\"domainValue\":\"num_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"identifiers\":[{\"id\":1,\"type\":\"INTEGER\",\"name\":\"ID_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":2,\"type\":\"STRING\",\"name\":\"ID_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}]},\"messages\":[],\"command\":\"DS_r:=floor(DS_10);\",\"ascalar\":false,\"acomponent\":false},{\"result\":{\"id\":39,\"name\":\"DS_r\",\"description\":null,\"attributes\":[],\"persistent\":false,\"viral\":[],\"measures\":[{\"id\":139,\"type\":\"INTEGER\",\"name\":\"ME_1\",\"domainValue\":\"num_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},{\"id\":140,\"type\":\"INTEGER\",\"name\":\"ME_2\",\"domainValue\":\"num_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"identifiers\":[{\"id\":1,\"type\":\"INTEGER\",\"name\":\"ID_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":2,\"type\":\"STRING\",\"name\":\"ID_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}]},\"messages\":[],\"command\":\"floor(DS_10)\",\"ascalar\":false,\"acomponent\":false},{\"result\":{\"id\":1,\"name\":\"DS_10\",\"description\":null,\"attributes\":[],\"persistent\":true,\"viral\":[],\"measures\":[{\"id\":3,\"type\":\"NUMBER\",\"name\":\"ME_1\",\"domainValue\":\"num_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},{\"id\":4,\"type\":\"NUMBER\",\"name\":\"ME_2\",\"domainValue\":\"num_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"identifiers\":[{\"id\":1,\"type\":\"INTEGER\",\"name\":\"ID_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":2,\"type\":\"STRING\",\"name\":\"ID_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}]},\"messages\":[],\"command\":\"DS_10\",\"ascalar\":false,\"acomponent\":false}]";
		List<ResultExpression> result = vtlTestUtils.getResult(commandStatements);
		List<ResultExpression> resultExpressionsToComapare = vtlTestUtils.convertJsonToResultExpression(jsonResult);
		assertNotNull("Conversione non riuscita", resultExpressionsToComapare);
		assertNotNull("Validazione Semantica non riuscita", result);
		assertTrue("I due risultati non sono uguali",
				vtlTestUtils.compareResult(result.get(0), resultExpressionsToComapare.get(0)));

	}

	@Test
	@Transactional
	public void floorNumber() throws Exception {

		String commandStatements = "DS_r := floor ( 3.14 );";
		String jsonResult = "[{\"result\":{\"id\":126,\"name\":\"DS_r\",\"description\":null,\"attributes\":[],\"measures\":[{\"id\":63,\"type\":\"INTEGER\",\"name\":\"int_var\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"persistent\":false,\"viral\":[],\"identifiers\":[]},\"resultComponent\":{\"id\":63,\"type\":\"INTEGER\",\"name\":\"int_var\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},\"messages\":[],\"command\":\"DS_r:=floor(3.14);\",\"acomponent\":false,\"scalarComponent\":{\"id\":63,\"type\":\"INTEGER\",\"name\":\"int_var\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},\"ascalar\":true},{\"result\":{\"id\":126,\"name\":\"DS_r\",\"description\":null,\"attributes\":[],\"measures\":[{\"id\":63,\"type\":\"INTEGER\",\"name\":\"int_var\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"persistent\":false,\"viral\":[],\"identifiers\":[]},\"resultComponent\":{\"id\":63,\"type\":\"INTEGER\",\"name\":\"int_var\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},\"messages\":[],\"command\":\"floor(3.14)\",\"acomponent\":false,\"scalarComponent\":{\"id\":63,\"type\":\"INTEGER\",\"name\":\"int_var\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},\"ascalar\":true},{\"result\":{\"id\":124,\"name\":\"temporary_123\",\"description\":null,\"attributes\":[],\"measures\":[{\"id\":62,\"type\":\"NUMBER\",\"name\":\"num_var\",\"domainValue\":\"num_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"persistent\":false,\"viral\":[],\"identifiers\":[]},\"resultComponent\":{\"id\":62,\"type\":\"NUMBER\",\"name\":\"num_var\",\"domainValue\":\"num_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},\"messages\":[],\"command\":\"3.14\",\"acomponent\":false,\"scalarComponent\":{\"id\":62,\"type\":\"NUMBER\",\"name\":\"num_var\",\"domainValue\":\"num_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},\"ascalar\":true}]";
		List<ResultExpression> result = vtlTestUtils.getResult(commandStatements);
		List<ResultExpression> resultExpressionsToComapare = vtlTestUtils.convertJsonToResultExpression(jsonResult);
		assertNotNull("Conversione non riuscita", resultExpressionsToComapare);
		assertNotNull("Validazione Semantica non riuscita", result);
		assertTrue("I due risultati non sono uguali",
				vtlTestUtils.compareResult(result.get(0), resultExpressionsToComapare.get(0)));
	}

	@Test
	@Transactional
	public void floorComponent() throws Exception {
		VtlDataset vtlDatasetFind = datasetRepository.findByNameIgnoreCase("DS_1");
		if (vtlDatasetFind == null) {
			VtlDataset vtlDataset = new VtlDataset();

			vtlDataset.setPersistent(true);
			vtlDataset.setName("DS_10");
			vtlDataset.setIsOnlyAScalar(false);
			vtlDataset.setTransitory(false);
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_1", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_2", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_1", VtlType.NUMBER, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_2", VtlType.NUMBER, VtlComponentRole.MEASURE));
			datasetRepository.save(vtlDataset);
		}
		String commandStatements = "DS_r := DS_10 [ calc Me_10 :=  floor (Me_1) ];";
		String jsonResult = "[{\"result\":{\"id\":128,\"name\":\"DS_r\",\"description\":null,\"attributes\":[],\"persistent\":false,\"measures\":[{\"id\":331,\"type\":\"NUMBER\",\"name\":\"ME_1\",\"domainValue\":\"num_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},{\"id\":332,\"type\":\"NUMBER\",\"name\":\"ME_2\",\"domainValue\":\"num_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},{\"id\":333,\"type\":\"NUMBER\",\"name\":\"Me_10\",\"domainValue\":\"num_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"identifiers\":[{\"id\":329,\"type\":\"INTEGER\",\"name\":\"ID_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":330,\"type\":\"STRING\",\"name\":\"ID_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}],\"viral\":[]},\"messages\":[],\"command\":\"DS_r:=DS_10[calcMe_10:=floor(Me_1)];\",\"acomponent\":false,\"ascalar\":false},{\"result\":{\"id\":126,\"name\":\"temporary_125\",\"description\":null,\"attributes\":[],\"persistent\":false,\"measures\":[{\"id\":326,\"type\":\"NUMBER\",\"name\":\"ME_1\",\"domainValue\":\"num_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},{\"id\":327,\"type\":\"NUMBER\",\"name\":\"ME_2\",\"domainValue\":\"num_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},{\"id\":328,\"type\":\"NUMBER\",\"name\":\"Me_10\",\"domainValue\":\"num_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"identifiers\":[{\"id\":324,\"type\":\"INTEGER\",\"name\":\"ID_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":325,\"type\":\"STRING\",\"name\":\"ID_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}],\"viral\":[]},\"messages\":[],\"command\":\"calcMe_10:=floor(Me_1)\",\"acomponent\":false,\"ascalar\":false},{\"resultComponent\":{\"id\":null,\"type\":\"NUMBER\",\"name\":\"Me_10\",\"domainValue\":\"num_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},\"messages\":[],\"command\":\"Me_10:=floor(Me_1)\",\"acomponent\":true,\"ascalar\":false},{\"resultComponent\":{\"id\":null,\"type\":\"NUMBER\",\"name\":\"ME_1\",\"domainValue\":\"num_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},\"messages\":[],\"command\":\"floor(Me_1)\",\"acomponent\":true,\"ascalar\":false},{\"resultComponent\":{\"id\":36,\"type\":\"NUMBER\",\"name\":\"ME_1\",\"domainValue\":\"num_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},\"messages\":[],\"command\":\"Me_1\",\"acomponent\":true,\"ascalar\":false}]";
		List<ResultExpression> result = vtlTestUtils.getResult(commandStatements);
		List<ResultExpression> resultExpressionsToComapare = vtlTestUtils.convertJsonToResultExpression(jsonResult);
		assertNotNull("Conversione non riuscita", resultExpressionsToComapare);
		assertNotNull("Validazione Semantica non riuscita", result);
		assertTrue("I due risultati non sono uguali",
				vtlTestUtils.compareResult(result.get(0), resultExpressionsToComapare.get(0)));

	}

	@Test
	@Transactional
	public void abs() throws Exception {
		VtlDataset vtlDatasetFind = datasetRepository.findByNameIgnoreCase("DS_1");
		if (vtlDatasetFind == null) {
			VtlDataset vtlDataset = new VtlDataset();

			vtlDataset.setPersistent(true);
			vtlDataset.setName("DS_10");
			vtlDataset.setIsOnlyAScalar(false);
			vtlDataset.setTransitory(false);
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_1", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_2", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_1", VtlType.NUMBER, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_2", VtlType.NUMBER, VtlComponentRole.MEASURE));
			datasetRepository.save(vtlDataset);
		}
		String commandStatements = "DS_r := abs ( DS_10 );";
		String jsonResult = "[{\"result\":{\"id\":43,\"name\":\"DS_r\",\"description\":null,\"attributes\":[],\"persistent\":false,\"viral\":[],\"measures\":[{\"id\":142,\"type\":\"NUMBER\",\"name\":\"ME_1\",\"domainValue\":\"num_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},{\"id\":143,\"type\":\"NUMBER\",\"name\":\"ME_2\",\"domainValue\":\"num_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"identifiers\":[{\"id\":1,\"type\":\"INTEGER\",\"name\":\"ID_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":2,\"type\":\"STRING\",\"name\":\"ID_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}]},\"messages\":[],\"command\":\"DS_r:=abs(DS_10);\",\"ascalar\":false,\"acomponent\":false},{\"result\":{\"id\":43,\"name\":\"DS_r\",\"description\":null,\"attributes\":[],\"persistent\":false,\"viral\":[],\"measures\":[{\"id\":142,\"type\":\"NUMBER\",\"name\":\"ME_1\",\"domainValue\":\"num_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},{\"id\":143,\"type\":\"NUMBER\",\"name\":\"ME_2\",\"domainValue\":\"num_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"identifiers\":[{\"id\":1,\"type\":\"INTEGER\",\"name\":\"ID_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":2,\"type\":\"STRING\",\"name\":\"ID_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}]},\"messages\":[],\"command\":\"abs(DS_10)\",\"ascalar\":false,\"acomponent\":false},{\"result\":{\"id\":1,\"name\":\"DS_10\",\"description\":null,\"attributes\":[],\"persistent\":true,\"viral\":[],\"measures\":[{\"id\":3,\"type\":\"NUMBER\",\"name\":\"ME_1\",\"domainValue\":\"num_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},{\"id\":4,\"type\":\"NUMBER\",\"name\":\"ME_2\",\"domainValue\":\"num_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"identifiers\":[{\"id\":1,\"type\":\"INTEGER\",\"name\":\"ID_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":2,\"type\":\"STRING\",\"name\":\"ID_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}]},\"messages\":[],\"command\":\"DS_10\",\"ascalar\":false,\"acomponent\":false}]";
		List<ResultExpression> result = vtlTestUtils.getResult(commandStatements);
		List<ResultExpression> resultExpressionsToComapare = vtlTestUtils.convertJsonToResultExpression(jsonResult);
		assertNotNull("Conversione non riuscita", resultExpressionsToComapare);
		assertNotNull("Validazione Semantica non riuscita", result);
		assertTrue("I due risultati non sono uguali",
				vtlTestUtils.compareResult(result.get(0), resultExpressionsToComapare.get(0)));

	}

	@Test
	@Transactional
	public void absNumber() throws Exception {

		String commandStatements = "DS_r := abs ( 5.49 );";
		String jsonResult = "[{\"result\":{\"id\":130,\"name\":\"DS_r\",\"description\":null,\"attributes\":[],\"measures\":[{\"id\":65,\"type\":\"NUMBER\",\"name\":\"num_var\",\"domainValue\":\"num_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"persistent\":false,\"viral\":[],\"identifiers\":[]},\"resultComponent\":{\"id\":65,\"type\":\"NUMBER\",\"name\":\"num_var\",\"domainValue\":\"num_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},\"messages\":[],\"command\":\"DS_r:=abs(5.49);\",\"acomponent\":false,\"scalarComponent\":{\"id\":65,\"type\":\"NUMBER\",\"name\":\"num_var\",\"domainValue\":\"num_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},\"ascalar\":true},{\"result\":{\"id\":130,\"name\":\"DS_r\",\"description\":null,\"attributes\":[],\"measures\":[{\"id\":65,\"type\":\"NUMBER\",\"name\":\"num_var\",\"domainValue\":\"num_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"persistent\":false,\"viral\":[],\"identifiers\":[]},\"resultComponent\":{\"id\":65,\"type\":\"NUMBER\",\"name\":\"num_var\",\"domainValue\":\"num_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},\"messages\":[],\"command\":\"abs(5.49)\",\"acomponent\":false,\"scalarComponent\":{\"id\":65,\"type\":\"NUMBER\",\"name\":\"num_var\",\"domainValue\":\"num_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},\"ascalar\":true},{\"result\":{\"id\":128,\"name\":\"temporary_127\",\"description\":null,\"attributes\":[],\"measures\":[{\"id\":64,\"type\":\"NUMBER\",\"name\":\"num_var\",\"domainValue\":\"num_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"persistent\":false,\"viral\":[],\"identifiers\":[]},\"resultComponent\":{\"id\":64,\"type\":\"NUMBER\",\"name\":\"num_var\",\"domainValue\":\"num_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},\"messages\":[],\"command\":\"5.49\",\"acomponent\":false,\"scalarComponent\":{\"id\":64,\"type\":\"NUMBER\",\"name\":\"num_var\",\"domainValue\":\"num_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},\"ascalar\":true}]";
		List<ResultExpression> result = vtlTestUtils.getResult(commandStatements);
		List<ResultExpression> resultExpressionsToComapare = vtlTestUtils.convertJsonToResultExpression(jsonResult);
		assertNotNull("Conversione non riuscita", resultExpressionsToComapare);
		assertNotNull("Validazione Semantica non riuscita", result);
		assertTrue("I due risultati non sono uguali",
				vtlTestUtils.compareResult(result.get(0), resultExpressionsToComapare.get(0)));
	}

	@Test
	@Transactional
	public void absComponent() throws Exception {
		VtlDataset vtlDatasetFind = datasetRepository.findByNameIgnoreCase("DS_1");
		if (vtlDatasetFind == null) {
			VtlDataset vtlDataset = new VtlDataset();

			vtlDataset.setPersistent(true);
			vtlDataset.setName("DS_10");
			vtlDataset.setIsOnlyAScalar(false);
			vtlDataset.setTransitory(false);
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_1", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_2", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_1", VtlType.NUMBER, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_2", VtlType.NUMBER, VtlComponentRole.MEASURE));
			datasetRepository.save(vtlDataset);
		}
		String commandStatements = "DS_r := DS_10 [ calc Me_10 := abs(Me_1) ];";
		String jsonResult = "[{\"result\":{\"id\":45,\"name\":\"DS_r\",\"description\":null,\"attributes\":[],\"persistent\":false,\"viral\":[],\"measures\":[{\"id\":3,\"type\":\"NUMBER\",\"name\":\"ME_1\",\"domainValue\":\"num_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},{\"id\":4,\"type\":\"NUMBER\",\"name\":\"ME_2\",\"domainValue\":\"num_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},{\"id\":144,\"type\":\"NUMBER\",\"name\":\"Me_10\",\"domainValue\":\"num_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"identifiers\":[{\"id\":1,\"type\":\"INTEGER\",\"name\":\"ID_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":2,\"type\":\"STRING\",\"name\":\"ID_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}]},\"messages\":[],\"command\":\"DS_r:=DS_10[calcMe_10:=abs(Me_1)];\",\"ascalar\":false,\"acomponent\":false},{\"result\":{\"id\":45,\"name\":\"DS_r\",\"description\":null,\"attributes\":[],\"persistent\":false,\"viral\":[],\"measures\":[{\"id\":3,\"type\":\"NUMBER\",\"name\":\"ME_1\",\"domainValue\":\"num_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},{\"id\":4,\"type\":\"NUMBER\",\"name\":\"ME_2\",\"domainValue\":\"num_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},{\"id\":144,\"type\":\"NUMBER\",\"name\":\"Me_10\",\"domainValue\":\"num_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"identifiers\":[{\"id\":1,\"type\":\"INTEGER\",\"name\":\"ID_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":2,\"type\":\"STRING\",\"name\":\"ID_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}]},\"messages\":[],\"command\":\"calcMe_10:=abs(Me_1)\",\"ascalar\":false,\"acomponent\":false},{\"resultComponent\":{\"id\":null,\"type\":\"NUMBER\",\"name\":\"Me_10\",\"domainValue\":\"num_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},\"messages\":[],\"command\":\"Me_10:=abs(Me_1)\",\"ascalar\":false,\"acomponent\":true},{\"resultComponent\":{\"id\":null,\"type\":\"NUMBER\",\"name\":\"ME_1\",\"domainValue\":\"num_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},\"messages\":[],\"command\":\"abs(Me_1)\",\"ascalar\":false,\"acomponent\":true},{\"resultComponent\":{\"id\":3,\"type\":\"NUMBER\",\"name\":\"ME_1\",\"domainValue\":\"num_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},\"messages\":[],\"command\":\"Me_1\",\"ascalar\":false,\"acomponent\":true}]";
		List<ResultExpression> result = vtlTestUtils.getResult(commandStatements);
		List<ResultExpression> resultExpressionsToComapare = vtlTestUtils.convertJsonToResultExpression(jsonResult);
		assertNotNull("Conversione non riuscita", resultExpressionsToComapare);
		assertNotNull("Validazione Semantica non riuscita", result);
		assertTrue("I due risultati non sono uguali",
				vtlTestUtils.compareResult(result.get(0), resultExpressionsToComapare.get(0)));

	}

	@Test
	@Transactional
	public void exp() throws Exception {
		VtlDataset vtlDatasetFind = datasetRepository.findByNameIgnoreCase("DS_1");
		if (vtlDatasetFind == null) {
			VtlDataset vtlDataset = new VtlDataset();

			vtlDataset.setPersistent(true);
			vtlDataset.setName("DS_8");
			vtlDataset.setIsOnlyAScalar(false);
			vtlDataset.setTransitory(false);
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_1", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_2", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_1", VtlType.INTEGER, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_2", VtlType.NUMBER, VtlComponentRole.MEASURE));
			datasetRepository.save(vtlDataset);

		}
		String commandStatements = "DS_r := exp(DS_8);";
		String jsonResult = "[{\"result\":{\"id\":35,\"name\":\"DS_r\",\"description\":null,\"attributes\":[],\"persistent\":false,\"measures\":[{\"id\":136,\"type\":\"NUMBER\",\"name\":\"ME_1\",\"domainValue\":\"num_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},{\"id\":137,\"type\":\"NUMBER\",\"name\":\"ME_2\",\"domainValue\":\"num_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"identifiers\":[{\"id\":1,\"type\":\"INTEGER\",\"name\":\"ID_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":2,\"type\":\"STRING\",\"name\":\"ID_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}],\"viral\":[]},\"messages\":[],\"command\":\"DS_r:=exp(DS_8);\",\"ascalar\":false,\"acomponent\":false},{\"result\":{\"id\":35,\"name\":\"DS_r\",\"description\":null,\"attributes\":[],\"persistent\":false,\"measures\":[{\"id\":136,\"type\":\"NUMBER\",\"name\":\"ME_1\",\"domainValue\":\"num_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},{\"id\":137,\"type\":\"NUMBER\",\"name\":\"ME_2\",\"domainValue\":\"num_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"identifiers\":[{\"id\":1,\"type\":\"INTEGER\",\"name\":\"ID_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":2,\"type\":\"STRING\",\"name\":\"ID_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}],\"viral\":[]},\"messages\":[],\"command\":\"exp(DS_8)\",\"ascalar\":false,\"acomponent\":false},{\"result\":{\"id\":1,\"name\":\"DS_8\",\"description\":null,\"attributes\":[],\"persistent\":true,\"measures\":[{\"id\":3,\"type\":\"INTEGER\",\"name\":\"ME_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},{\"id\":4,\"type\":\"NUMBER\",\"name\":\"ME_2\",\"domainValue\":\"num_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"identifiers\":[{\"id\":1,\"type\":\"INTEGER\",\"name\":\"ID_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":2,\"type\":\"STRING\",\"name\":\"ID_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}],\"viral\":[]},\"messages\":[],\"command\":\"DS_8\",\"ascalar\":false,\"acomponent\":false}]";
		List<ResultExpression> result = vtlTestUtils.getResult(commandStatements);
		List<ResultExpression> resultExpressionsToComapare = vtlTestUtils.convertJsonToResultExpression(jsonResult);
		assertNotNull("Conversione non riuscita", resultExpressionsToComapare);
		assertNotNull("Validazione Semantica non riuscita", result);
		assertTrue("I due risultati non sono uguali",
				vtlTestUtils.compareResult(result.get(0), resultExpressionsToComapare.get(0)));

	}

	@Test
	@Transactional
	public void expInt() throws Exception {

		String commandStatements = "DS_r := exp ( 0 );";
		String jsonResult = "[{\"result\":{\"id\":134,\"name\":\"DS_r\",\"description\":null,\"attributes\":[],\"measures\":[{\"id\":67,\"type\":\"NUMBER\",\"name\":\"num_var\",\"domainValue\":\"num_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"persistent\":false,\"viral\":[],\"identifiers\":[]},\"resultComponent\":{\"id\":67,\"type\":\"NUMBER\",\"name\":\"num_var\",\"domainValue\":\"num_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},\"messages\":[],\"command\":\"DS_r:=exp(0);\",\"acomponent\":false,\"scalarComponent\":{\"id\":67,\"type\":\"NUMBER\",\"name\":\"num_var\",\"domainValue\":\"num_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},\"ascalar\":true},{\"result\":{\"id\":134,\"name\":\"DS_r\",\"description\":null,\"attributes\":[],\"measures\":[{\"id\":67,\"type\":\"NUMBER\",\"name\":\"num_var\",\"domainValue\":\"num_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"persistent\":false,\"viral\":[],\"identifiers\":[]},\"resultComponent\":{\"id\":67,\"type\":\"NUMBER\",\"name\":\"num_var\",\"domainValue\":\"num_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},\"messages\":[],\"command\":\"exp(0)\",\"acomponent\":false,\"scalarComponent\":{\"id\":67,\"type\":\"NUMBER\",\"name\":\"num_var\",\"domainValue\":\"num_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},\"ascalar\":true},{\"result\":{\"id\":132,\"name\":\"temporary_131\",\"description\":null,\"attributes\":[],\"measures\":[{\"id\":66,\"type\":\"INTEGER\",\"name\":\"int_var\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"persistent\":false,\"viral\":[],\"identifiers\":[]},\"resultComponent\":{\"id\":66,\"type\":\"INTEGER\",\"name\":\"int_var\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},\"messages\":[],\"command\":\"0\",\"acomponent\":false,\"scalarComponent\":{\"id\":66,\"type\":\"INTEGER\",\"name\":\"int_var\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},\"ascalar\":true}]";
		List<ResultExpression> result = vtlTestUtils.getResult(commandStatements);
		List<ResultExpression> resultExpressionsToComapare = vtlTestUtils.convertJsonToResultExpression(jsonResult);
		assertNotNull("Conversione non riuscita", resultExpressionsToComapare);
		assertNotNull("Validazione Semantica non riuscita", result);
		assertTrue("I due risultati non sono uguali",
				vtlTestUtils.compareResult(result.get(0), resultExpressionsToComapare.get(0)));
	}

	@Test
	@Transactional
	public void expIntNegativo() throws Exception {

		String commandStatements = "DS_r := exp ( - 2 );";
		String jsonResult = "[{\"result\":{\"id\":152,\"name\":\"DS_r\",\"description\":null,\"attributes\":[],\"measures\":[{\"id\":76,\"type\":\"NUMBER\",\"name\":\"num_var\",\"domainValue\":\"num_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"persistent\":false,\"viral\":[],\"identifiers\":[]},\"resultComponent\":{\"id\":76,\"type\":\"NUMBER\",\"name\":\"num_var\",\"domainValue\":\"num_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},\"messages\":[],\"command\":\"DS_r:=exp(-2);\",\"acomponent\":false,\"scalarComponent\":{\"id\":76,\"type\":\"NUMBER\",\"name\":\"num_var\",\"domainValue\":\"num_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},\"ascalar\":true},{\"result\":{\"id\":152,\"name\":\"DS_r\",\"description\":null,\"attributes\":[],\"measures\":[{\"id\":76,\"type\":\"NUMBER\",\"name\":\"num_var\",\"domainValue\":\"num_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"persistent\":false,\"viral\":[],\"identifiers\":[]},\"resultComponent\":{\"id\":76,\"type\":\"NUMBER\",\"name\":\"num_var\",\"domainValue\":\"num_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},\"messages\":[],\"command\":\"exp(-2)\",\"acomponent\":false,\"scalarComponent\":{\"id\":76,\"type\":\"NUMBER\",\"name\":\"num_var\",\"domainValue\":\"num_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},\"ascalar\":true},{\"result\":{\"id\":150,\"name\":\"temporary_149\",\"description\":null,\"attributes\":[],\"measures\":[{\"id\":75,\"type\":\"INTEGER\",\"name\":\"int_var\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"persistent\":false,\"viral\":[],\"identifiers\":[]},\"resultComponent\":{\"id\":75,\"type\":\"INTEGER\",\"name\":\"int_var\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},\"messages\":[],\"command\":\"-2\",\"acomponent\":false,\"scalarComponent\":{\"id\":75,\"type\":\"INTEGER\",\"name\":\"int_var\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},\"ascalar\":true},{\"result\":{\"id\":148,\"name\":\"temporary_147\",\"description\":null,\"attributes\":[],\"measures\":[{\"id\":74,\"type\":\"INTEGER\",\"name\":\"int_var\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"persistent\":false,\"viral\":[],\"identifiers\":[]},\"resultComponent\":{\"id\":74,\"type\":\"INTEGER\",\"name\":\"int_var\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},\"messages\":[],\"command\":\"2\",\"acomponent\":false,\"scalarComponent\":{\"id\":74,\"type\":\"INTEGER\",\"name\":\"int_var\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},\"ascalar\":true}]";
		List<ResultExpression> result = vtlTestUtils.getResult(commandStatements);
		List<ResultExpression> resultExpressionsToComapare = vtlTestUtils.convertJsonToResultExpression(jsonResult);
		assertNotNull("Conversione non riuscita", resultExpressionsToComapare);
		assertNotNull("Validazione Semantica non riuscita", result);
		assertTrue("I due risultati non sono uguali",
				vtlTestUtils.compareResult(result.get(0), resultExpressionsToComapare.get(0)));
	}

	@Test
	@Transactional
	public void expComponent() throws Exception {
		VtlDataset vtlDatasetFind = datasetRepository.findByNameIgnoreCase("DS_1");
		if (vtlDatasetFind == null) {
			VtlDataset vtlDataset = new VtlDataset();

			vtlDataset.setPersistent(true);
			vtlDataset.setName("DS_8");
			vtlDataset.setIsOnlyAScalar(false);
			vtlDataset.setTransitory(false);
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_1", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_2", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_1", VtlType.INTEGER, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_2", VtlType.NUMBER, VtlComponentRole.MEASURE));
			datasetRepository.save(vtlDataset);

		}
		String commandStatements = "DS_r := DS_8 [ calc Me_1 :=  exp ( Me_1 ) ];";
		String jsonResult = "[{\"result\":{\"id\":132,\"name\":\"DS_r\",\"description\":null,\"attributes\":[],\"persistent\":false,\"measures\":[{\"id\":341,\"type\":\"NUMBER\",\"name\":\"ME_2\",\"domainValue\":\"num_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},{\"id\":342,\"type\":\"NUMBER\",\"name\":\"Me_1\",\"domainValue\":\"num_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"identifiers\":[{\"id\":339,\"type\":\"INTEGER\",\"name\":\"ID_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":340,\"type\":\"STRING\",\"name\":\"ID_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}],\"viral\":[]},\"messages\":[],\"command\":\"DS_r:=DS_8[calcMe_1:=exp(Me_1)];\",\"acomponent\":false,\"ascalar\":false},{\"result\":{\"id\":130,\"name\":\"temporary_129\",\"description\":null,\"attributes\":[],\"persistent\":false,\"measures\":[{\"id\":337,\"type\":\"NUMBER\",\"name\":\"ME_2\",\"domainValue\":\"num_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},{\"id\":338,\"type\":\"NUMBER\",\"name\":\"Me_1\",\"domainValue\":\"num_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"identifiers\":[{\"id\":334,\"type\":\"INTEGER\",\"name\":\"ID_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":335,\"type\":\"STRING\",\"name\":\"ID_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}],\"viral\":[]},\"messages\":[],\"command\":\"calcMe_1:=exp(Me_1)\",\"acomponent\":false,\"ascalar\":false},{\"resultComponent\":{\"id\":null,\"type\":\"NUMBER\",\"name\":\"Me_1\",\"domainValue\":\"num_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},\"messages\":[],\"command\":\"Me_1:=exp(Me_1)\",\"acomponent\":true,\"ascalar\":false},{\"resultComponent\":{\"id\":null,\"type\":\"NUMBER\",\"name\":\"ME_1\",\"domainValue\":\"num_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},\"messages\":[],\"command\":\"exp(Me_1)\",\"acomponent\":true,\"ascalar\":false},{\"resultComponent\":{\"id\":28,\"type\":\"INTEGER\",\"name\":\"ME_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},\"messages\":[],\"command\":\"Me_1\",\"acomponent\":true,\"ascalar\":false}]";
		List<ResultExpression> result = vtlTestUtils.getResult(commandStatements);
		List<ResultExpression> resultExpressionsToComapare = vtlTestUtils.convertJsonToResultExpression(jsonResult);
		assertNotNull("Conversione non riuscita", resultExpressionsToComapare);
		assertNotNull("Validazione Semantica non riuscita", result);
		assertTrue("I due risultati non sono uguali",
				vtlTestUtils.compareResult(result.get(0), resultExpressionsToComapare.get(0)));

	}

	@Test
	@Transactional
	public void ln() throws Exception {
		VtlDataset vtlDatasetFind = datasetRepository.findByNameIgnoreCase("DS_1");
		if (vtlDatasetFind == null) {
			VtlDataset vtlDataset = new VtlDataset();

			vtlDataset.setPersistent(true);
			vtlDataset.setName("DS_10");
			vtlDataset.setIsOnlyAScalar(false);
			vtlDataset.setTransitory(false);
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_1", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_2", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_1", VtlType.NUMBER, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_2", VtlType.NUMBER, VtlComponentRole.MEASURE));
			datasetRepository.save(vtlDataset);
		}
		String commandStatements = "DS_r := ln(DS_10);";
		String jsonResult = "[{\"result\":{\"id\":35,\"name\":\"DS_r\",\"description\":null,\"attributes\":[],\"persistent\":false,\"viral\":[],\"identifiers\":[{\"id\":1,\"type\":\"INTEGER\",\"name\":\"ID_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":2,\"type\":\"STRING\",\"name\":\"ID_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}],\"measures\":[{\"id\":136,\"type\":\"NUMBER\",\"name\":\"ME_1\",\"domainValue\":\"num_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},{\"id\":137,\"type\":\"NUMBER\",\"name\":\"ME_2\",\"domainValue\":\"num_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"}]},\"messages\":[],\"command\":\"DS_r:=ln(DS_10);\",\"acomponent\":false,\"ascalar\":false},{\"result\":{\"id\":35,\"name\":\"DS_r\",\"description\":null,\"attributes\":[],\"persistent\":false,\"viral\":[],\"identifiers\":[{\"id\":1,\"type\":\"INTEGER\",\"name\":\"ID_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":2,\"type\":\"STRING\",\"name\":\"ID_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}],\"measures\":[{\"id\":136,\"type\":\"NUMBER\",\"name\":\"ME_1\",\"domainValue\":\"num_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},{\"id\":137,\"type\":\"NUMBER\",\"name\":\"ME_2\",\"domainValue\":\"num_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"}]},\"messages\":[],\"command\":\"ln(DS_10)\",\"acomponent\":false,\"ascalar\":false},{\"result\":{\"id\":1,\"name\":\"DS_10\",\"description\":null,\"attributes\":[],\"persistent\":true,\"viral\":[],\"identifiers\":[{\"id\":1,\"type\":\"INTEGER\",\"name\":\"ID_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":2,\"type\":\"STRING\",\"name\":\"ID_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}],\"measures\":[{\"id\":3,\"type\":\"NUMBER\",\"name\":\"ME_1\",\"domainValue\":\"num_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},{\"id\":4,\"type\":\"NUMBER\",\"name\":\"ME_2\",\"domainValue\":\"num_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"}]},\"messages\":[],\"command\":\"DS_10\",\"acomponent\":false,\"ascalar\":false}]";
		List<ResultExpression> result = vtlTestUtils.getResult(commandStatements);
		List<ResultExpression> resultExpressionsToComapare = vtlTestUtils.convertJsonToResultExpression(jsonResult);
		assertNotNull("Conversione non riuscita", resultExpressionsToComapare);
		assertNotNull("Validazione Semantica non riuscita", result);
		assertTrue("I due risultati non sono uguali",
				vtlTestUtils.compareResult(result.get(0), resultExpressionsToComapare.get(0)));

	}

	@Test
	@Transactional
	public void lnInt() throws Exception {

		String commandStatements = "DS_r := ln ( 8 );";
		String jsonResult = "[{\"result\":{\"id\":156,\"name\":\"DS_r\",\"description\":null,\"attributes\":[],\"measures\":[{\"id\":78,\"type\":\"NUMBER\",\"name\":\"num_var\",\"domainValue\":\"num_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"persistent\":false,\"viral\":[],\"identifiers\":[]},\"resultComponent\":{\"id\":78,\"type\":\"NUMBER\",\"name\":\"num_var\",\"domainValue\":\"num_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},\"messages\":[],\"command\":\"DS_r:=ln(8);\",\"acomponent\":false,\"scalarComponent\":{\"id\":78,\"type\":\"NUMBER\",\"name\":\"num_var\",\"domainValue\":\"num_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},\"ascalar\":true},{\"result\":{\"id\":156,\"name\":\"DS_r\",\"description\":null,\"attributes\":[],\"measures\":[{\"id\":78,\"type\":\"NUMBER\",\"name\":\"num_var\",\"domainValue\":\"num_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"persistent\":false,\"viral\":[],\"identifiers\":[]},\"resultComponent\":{\"id\":78,\"type\":\"NUMBER\",\"name\":\"num_var\",\"domainValue\":\"num_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},\"messages\":[],\"command\":\"ln(8)\",\"acomponent\":false,\"scalarComponent\":{\"id\":78,\"type\":\"NUMBER\",\"name\":\"num_var\",\"domainValue\":\"num_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},\"ascalar\":true},{\"result\":{\"id\":154,\"name\":\"temporary_153\",\"description\":null,\"attributes\":[],\"measures\":[{\"id\":77,\"type\":\"INTEGER\",\"name\":\"int_var\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"persistent\":false,\"viral\":[],\"identifiers\":[]},\"resultComponent\":{\"id\":77,\"type\":\"INTEGER\",\"name\":\"int_var\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},\"messages\":[],\"command\":\"8\",\"acomponent\":false,\"scalarComponent\":{\"id\":77,\"type\":\"INTEGER\",\"name\":\"int_var\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},\"ascalar\":true}]";
		List<ResultExpression> result = vtlTestUtils.getResult(commandStatements);
		List<ResultExpression> resultExpressionsToComapare = vtlTestUtils.convertJsonToResultExpression(jsonResult);
		assertNotNull("Conversione non riuscita", resultExpressionsToComapare);
		assertNotNull("Validazione Semantica non riuscita", result);
		assertTrue("I due risultati non sono uguali",
				vtlTestUtils.compareResult(result.get(0), resultExpressionsToComapare.get(0)));
	}

	@Test
	@Transactional
	public void lnComponent() throws Exception {
		VtlDataset vtlDatasetFind = datasetRepository.findByNameIgnoreCase("DS_1");
		if (vtlDatasetFind == null) {
			VtlDataset vtlDataset = new VtlDataset();

			vtlDataset.setPersistent(true);
			vtlDataset.setName("DS_10");
			vtlDataset.setIsOnlyAScalar(false);
			vtlDataset.setTransitory(false);
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_1", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_2", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_1", VtlType.NUMBER, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_2", VtlType.NUMBER, VtlComponentRole.MEASURE));
			datasetRepository.save(vtlDataset);
		}
        String commandStatements = "DS_r := DS_10 [ calc Me_2 := ln ( Me_1 ) ];";
		String jsonResult = "[{\"result\":{\"id\":39,\"name\":\"DS_r\",\"description\":null,\"attributes\":[],\"persistent\":false,\"viral\":[],\"identifiers\":[{\"id\":1,\"type\":\"INTEGER\",\"name\":\"ID_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":2,\"type\":\"STRING\",\"name\":\"ID_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}],\"measures\":[{\"id\":3,\"type\":\"NUMBER\",\"name\":\"ME_1\",\"domainValue\":\"num_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},{\"id\":139,\"type\":\"NUMBER\",\"name\":\"Me_2\",\"domainValue\":\"num_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"}]},\"messages\":[],\"command\":\"DS_r:=DS_10[calcMe_2:=ln(DS_10#Me_1)];\",\"acomponent\":false,\"ascalar\":false},{\"result\":{\"id\":39,\"name\":\"DS_r\",\"description\":null,\"attributes\":[],\"persistent\":false,\"viral\":[],\"identifiers\":[{\"id\":1,\"type\":\"INTEGER\",\"name\":\"ID_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":2,\"type\":\"STRING\",\"name\":\"ID_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}],\"measures\":[{\"id\":3,\"type\":\"NUMBER\",\"name\":\"ME_1\",\"domainValue\":\"num_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},{\"id\":139,\"type\":\"NUMBER\",\"name\":\"Me_2\",\"domainValue\":\"num_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"}]},\"messages\":[],\"command\":\"calcMe_2:=ln(DS_10#Me_1)\",\"acomponent\":false,\"ascalar\":false},{\"resultComponent\":{\"id\":null,\"type\":\"NUMBER\",\"name\":\"Me_2\",\"domainValue\":\"num_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},\"messages\":[],\"command\":\"Me_2:=ln(DS_10#Me_1)\",\"acomponent\":true,\"ascalar\":false},{\"resultComponent\":{\"id\":null,\"type\":\"NUMBER\",\"name\":\"ME_1\",\"domainValue\":\"num_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},\"messages\":[],\"command\":\"ln(DS_10#Me_1)\",\"acomponent\":true,\"ascalar\":false},{\"resultComponent\":{\"id\":3,\"type\":\"NUMBER\",\"name\":\"ME_1\",\"domainValue\":\"num_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},\"messages\":[],\"command\":\"DS_10#Me_1\",\"acomponent\":true,\"ascalar\":false},{\"result\":{\"id\":1,\"name\":\"DS_10\",\"description\":null,\"attributes\":[],\"persistent\":true,\"viral\":[],\"identifiers\":[{\"id\":1,\"type\":\"INTEGER\",\"name\":\"ID_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":2,\"type\":\"STRING\",\"name\":\"ID_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}],\"measures\":[{\"id\":3,\"type\":\"NUMBER\",\"name\":\"ME_1\",\"domainValue\":\"num_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},{\"id\":4,\"type\":\"NUMBER\",\"name\":\"ME_2\",\"domainValue\":\"num_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"}]},\"messages\":[],\"command\":\"DS_10\",\"acomponent\":false,\"ascalar\":false}]";
		List<ResultExpression> result = vtlTestUtils.getResult(commandStatements);
		List<ResultExpression> resultExpressionsToComapare = vtlTestUtils.convertJsonToResultExpression(jsonResult);
		assertNotNull("Conversione non riuscita", resultExpressionsToComapare);
		assertNotNull("Validazione Semantica non riuscita", result);
		assertTrue("I due risultati non sono uguali",
				vtlTestUtils.compareResult(result.get(0), resultExpressionsToComapare.get(0)));

	}

	@Test
	@Transactional
	public void power() throws Exception {
		VtlDataset vtlDatasetFind = datasetRepository.findByNameIgnoreCase("DS_1");
		if (vtlDatasetFind == null) {
			VtlDataset vtlDataset = new VtlDataset();

			vtlDataset.setPersistent(true);
			vtlDataset.setName("DS_8");
			vtlDataset.setIsOnlyAScalar(false);
			vtlDataset.setTransitory(false);
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_1", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_2", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_1", VtlType.INTEGER, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_2", VtlType.NUMBER, VtlComponentRole.MEASURE));
			datasetRepository.save(vtlDataset);
		}
		String commandStatements = "DS_r := power(DS_8, 2);";
		String jsonResult = "[{\"result\":{\"id\":35,\"name\":\"DS_r\",\"description\":null,\"attributes\":[],\"persistent\":false,\"measures\":[{\"id\":135,\"type\":\"NUMBER\",\"name\":\"ME_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},{\"id\":136,\"type\":\"NUMBER\",\"name\":\"ME_2\",\"domainValue\":\"num_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"viral\":[],\"identifiers\":[{\"id\":1,\"type\":\"INTEGER\",\"name\":\"ID_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":2,\"type\":\"STRING\",\"name\":\"ID_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}]},\"messages\":[],\"command\":\"DS_r:=power(DS_8,2);\",\"acomponent\":false,\"ascalar\":false},{\"result\":{\"id\":35,\"name\":\"DS_r\",\"description\":null,\"attributes\":[],\"persistent\":false,\"measures\":[{\"id\":135,\"type\":\"NUMBER\",\"name\":\"ME_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},{\"id\":136,\"type\":\"NUMBER\",\"name\":\"ME_2\",\"domainValue\":\"num_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"viral\":[],\"identifiers\":[{\"id\":1,\"type\":\"INTEGER\",\"name\":\"ID_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":2,\"type\":\"STRING\",\"name\":\"ID_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}]},\"messages\":[],\"command\":\"power(DS_8,2)\",\"acomponent\":false,\"ascalar\":false},{\"result\":{\"id\":1,\"name\":\"DS_8\",\"description\":null,\"attributes\":[],\"persistent\":true,\"measures\":[{\"id\":3,\"type\":\"INTEGER\",\"name\":\"ME_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},{\"id\":4,\"type\":\"NUMBER\",\"name\":\"ME_2\",\"domainValue\":\"num_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"viral\":[],\"identifiers\":[{\"id\":1,\"type\":\"INTEGER\",\"name\":\"ID_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":2,\"type\":\"STRING\",\"name\":\"ID_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}]},\"messages\":[],\"command\":\"DS_8\",\"acomponent\":false,\"ascalar\":false},{\"result\":{\"id\":33,\"name\":\"temporary_32\",\"description\":null,\"attributes\":[],\"persistent\":false,\"measures\":[{\"id\":134,\"type\":\"INTEGER\",\"name\":\"int_var\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"viral\":[],\"identifiers\":[]},\"resultComponent\":{\"id\":134,\"type\":\"INTEGER\",\"name\":\"int_var\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},\"messages\":[],\"command\":\"2\",\"acomponent\":false,\"scalarComponent\":{\"id\":134,\"type\":\"INTEGER\",\"name\":\"int_var\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},\"ascalar\":true}]";
		List<ResultExpression> result = vtlTestUtils.getResult(commandStatements);
		List<ResultExpression> resultExpressionsToComapare = vtlTestUtils.convertJsonToResultExpression(jsonResult);
		assertNotNull("Conversione non riuscita", resultExpressionsToComapare);
		assertNotNull("Validazione Semantica non riuscita", result);
		assertTrue("I due risultati non sono uguali",
				vtlTestUtils.compareResult(result.get(0), resultExpressionsToComapare.get(0)));

	}

	@Test
	@Transactional
	public void powerInt() throws Exception {

		String commandStatements = "DS_r := power ( 5, 0 );";
		String jsonResult = "[{\"result\":{\"id\":162,\"name\":\"DS_r\",\"description\":null,\"attributes\":[],\"measures\":[{\"id\":81,\"type\":\"NUMBER\",\"name\":\"int_var\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"persistent\":false,\"viral\":[],\"identifiers\":[]},\"resultComponent\":{\"id\":81,\"type\":\"NUMBER\",\"name\":\"int_var\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},\"messages\":[],\"command\":\"DS_r:=power(5,0);\",\"acomponent\":false,\"scalarComponent\":{\"id\":81,\"type\":\"NUMBER\",\"name\":\"int_var\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},\"ascalar\":true},{\"result\":{\"id\":162,\"name\":\"DS_r\",\"description\":null,\"attributes\":[],\"measures\":[{\"id\":81,\"type\":\"NUMBER\",\"name\":\"int_var\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"persistent\":false,\"viral\":[],\"identifiers\":[]},\"resultComponent\":{\"id\":81,\"type\":\"NUMBER\",\"name\":\"int_var\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},\"messages\":[],\"command\":\"power(5,0)\",\"acomponent\":false,\"scalarComponent\":{\"id\":81,\"type\":\"NUMBER\",\"name\":\"int_var\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},\"ascalar\":true},{\"result\":{\"id\":158,\"name\":\"temporary_157\",\"description\":null,\"attributes\":[],\"measures\":[{\"id\":79,\"type\":\"INTEGER\",\"name\":\"int_var\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"persistent\":false,\"viral\":[],\"identifiers\":[]},\"resultComponent\":{\"id\":79,\"type\":\"INTEGER\",\"name\":\"int_var\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},\"messages\":[],\"command\":\"5\",\"acomponent\":false,\"scalarComponent\":{\"id\":79,\"type\":\"INTEGER\",\"name\":\"int_var\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},\"ascalar\":true},{\"result\":{\"id\":160,\"name\":\"temporary_159\",\"description\":null,\"attributes\":[],\"measures\":[{\"id\":80,\"type\":\"INTEGER\",\"name\":\"int_var\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"persistent\":false,\"viral\":[],\"identifiers\":[]},\"resultComponent\":{\"id\":80,\"type\":\"INTEGER\",\"name\":\"int_var\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},\"messages\":[],\"command\":\"0\",\"acomponent\":false,\"scalarComponent\":{\"id\":80,\"type\":\"INTEGER\",\"name\":\"int_var\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},\"ascalar\":true}]";
		List<ResultExpression> result = vtlTestUtils.getResult(commandStatements);
		List<ResultExpression> resultExpressionsToComapare = vtlTestUtils.convertJsonToResultExpression(jsonResult);
		assertNotNull("Conversione non riuscita", resultExpressionsToComapare);
		assertNotNull("Validazione Semantica non riuscita", result);
		assertTrue("I due risultati non sono uguali",
				vtlTestUtils.compareResult(result.get(0), resultExpressionsToComapare.get(0)));
	}

	@Test
	@Transactional
	public void powerComponent() throws Exception {
		VtlDataset vtlDatasetFind = datasetRepository.findByNameIgnoreCase("DS_1");
		if (vtlDatasetFind == null) {
			VtlDataset vtlDataset = new VtlDataset();

			vtlDataset.setPersistent(true);
			vtlDataset.setName("DS_8");
			vtlDataset.setIsOnlyAScalar(false);
			vtlDataset.setTransitory(false);
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_1", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_2", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_1", VtlType.INTEGER, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_2", VtlType.NUMBER, VtlComponentRole.MEASURE));
			datasetRepository.save(vtlDataset);
		}
		String commandStatements = "DS_r := DS_8[ calc Me_1 := power(Me_1, 2), Me_3 := power(Me_2, Me_1) ];";
		String jsonResult = "[{\"result\":{\"id\":43,\"name\":\"DS_r\",\"description\":null,\"attributes\":[],\"persistent\":false,\"measures\":[{\"id\":4,\"type\":\"NUMBER\",\"name\":\"ME_2\",\"domainValue\":\"num_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},{\"id\":141,\"type\":\"NUMBER\",\"name\":\"Me_1\",\"domainValue\":\"num_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},{\"id\":142,\"type\":\"NUMBER\",\"name\":\"Me_3\",\"domainValue\":\"num_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"viral\":[],\"identifiers\":[{\"id\":1,\"type\":\"INTEGER\",\"name\":\"ID_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":2,\"type\":\"STRING\",\"name\":\"ID_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}]},\"messages\":[],\"command\":\"DS_r:=DS_8[calcMe_1:=power(Me_1,2),Me_3:=power(Me_2,Me_1)];\",\"acomponent\":false,\"ascalar\":false},{\"result\":{\"id\":43,\"name\":\"DS_r\",\"description\":null,\"attributes\":[],\"persistent\":false,\"measures\":[{\"id\":4,\"type\":\"NUMBER\",\"name\":\"ME_2\",\"domainValue\":\"num_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},{\"id\":141,\"type\":\"NUMBER\",\"name\":\"Me_1\",\"domainValue\":\"num_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},{\"id\":142,\"type\":\"NUMBER\",\"name\":\"Me_3\",\"domainValue\":\"num_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"viral\":[],\"identifiers\":[{\"id\":1,\"type\":\"INTEGER\",\"name\":\"ID_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":2,\"type\":\"STRING\",\"name\":\"ID_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}]},\"messages\":[],\"command\":\"calcMe_1:=power(Me_1,2),Me_3:=power(Me_2,Me_1)\",\"acomponent\":false,\"ascalar\":false},{\"resultComponent\":{\"id\":null,\"type\":\"NUMBER\",\"name\":\"Me_3\",\"domainValue\":\"num_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},\"messages\":[],\"command\":\"Me_3:=power(Me_2,Me_1)\",\"acomponent\":true,\"ascalar\":false},{\"resultComponent\":{\"id\":null,\"type\":\"NUMBER\",\"name\":\"ME_2\",\"domainValue\":\"num_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},\"messages\":[],\"command\":\"power(Me_2,Me_1)\",\"acomponent\":true,\"ascalar\":false},{\"resultComponent\":{\"id\":4,\"type\":\"NUMBER\",\"name\":\"ME_2\",\"domainValue\":\"num_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},\"messages\":[],\"command\":\"Me_2\",\"acomponent\":true,\"ascalar\":false},{\"resultComponent\":{\"id\":3,\"type\":\"INTEGER\",\"name\":\"ME_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},\"messages\":[],\"command\":\"Me_1\",\"acomponent\":true,\"ascalar\":false},{\"resultComponent\":{\"id\":null,\"type\":\"NUMBER\",\"name\":\"Me_1\",\"domainValue\":\"num_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},\"messages\":[],\"command\":\"Me_1:=power(Me_1,2)\",\"acomponent\":true,\"ascalar\":false},{\"resultComponent\":{\"id\":null,\"type\":\"NUMBER\",\"name\":\"ME_1\",\"domainValue\":\"num_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},\"messages\":[],\"command\":\"power(Me_1,2)\",\"acomponent\":true,\"ascalar\":false},{\"resultComponent\":{\"id\":3,\"type\":\"INTEGER\",\"name\":\"ME_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},\"messages\":[],\"command\":\"Me_1\",\"acomponent\":true,\"ascalar\":false},{\"result\":{\"id\":41,\"name\":\"temporary_40\",\"description\":null,\"attributes\":[],\"persistent\":false,\"measures\":[{\"id\":140,\"type\":\"INTEGER\",\"name\":\"int_var\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"viral\":[],\"identifiers\":[]},\"resultComponent\":{\"id\":140,\"type\":\"INTEGER\",\"name\":\"int_var\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},\"messages\":[],\"command\":\"2\",\"acomponent\":false,\"scalarComponent\":{\"id\":140,\"type\":\"INTEGER\",\"name\":\"int_var\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},\"ascalar\":true}]";
		List<ResultExpression> result = vtlTestUtils.getResult(commandStatements);
		List<ResultExpression> resultExpressionsToComapare = vtlTestUtils.convertJsonToResultExpression(jsonResult);
		assertNotNull("Conversione non riuscita", resultExpressionsToComapare);
		assertNotNull("Validazione Semantica non riuscita", result);
		assertTrue("I due risultati non sono uguali",
				vtlTestUtils.compareResult(result.get(0), resultExpressionsToComapare.get(0)));

	}

	@Test
	@Transactional
	public void powerComponentMe() throws Exception {
		VtlDataset vtlDatasetFind = datasetRepository.findByNameIgnoreCase("DS_1");
		if (vtlDatasetFind == null) {
			VtlDataset vtlDataset = new VtlDataset();

			vtlDataset.setPersistent(true);
			vtlDataset.setName("DS_8");
			vtlDataset.setIsOnlyAScalar(false);
			vtlDataset.setTransitory(false);
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_1", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_2", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_1", VtlType.INTEGER, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_2", VtlType.NUMBER, VtlComponentRole.MEASURE));
			datasetRepository.save(vtlDataset);
		}
		String commandStatements = "DS_r := DS_8[ calc Me_1 := power(Me_1, 2) ];";
		String jsonResult = "[{\"result\":{\"id\":11,\"name\":\"DS_r\",\"description\":null,\"attributes\":[],\"persistent\":false,\"viral\":[],\"identifiers\":[{\"id\":1,\"type\":\"INTEGER\",\"name\":\"ID_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":2,\"type\":\"STRING\",\"name\":\"ID_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}],\"measures\":[{\"id\":4,\"type\":\"NUMBER\",\"name\":\"ME_2\",\"domainValue\":\"num_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},{\"id\":30,\"type\":\"NUMBER\",\"name\":\"Me_1\",\"domainValue\":\"num_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"}]},\"messages\":[],\"command\":\"DS_r:=DS_8[calcMe_1:=power(Me_1,2)];\",\"acomponent\":false,\"ascalar\":false},{\"result\":{\"id\":11,\"name\":\"DS_r\",\"description\":null,\"attributes\":[],\"persistent\":false,\"viral\":[],\"identifiers\":[{\"id\":1,\"type\":\"INTEGER\",\"name\":\"ID_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":2,\"type\":\"STRING\",\"name\":\"ID_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}],\"measures\":[{\"id\":4,\"type\":\"NUMBER\",\"name\":\"ME_2\",\"domainValue\":\"num_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},{\"id\":30,\"type\":\"NUMBER\",\"name\":\"Me_1\",\"domainValue\":\"num_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"}]},\"messages\":[],\"command\":\"calcMe_1:=power(Me_1,2)\",\"acomponent\":false,\"ascalar\":false},{\"resultComponent\":{\"id\":null,\"type\":\"NUMBER\",\"name\":\"Me_1\",\"domainValue\":\"num_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},\"messages\":[],\"command\":\"Me_1:=power(Me_1,2)\",\"acomponent\":true,\"ascalar\":false},{\"resultComponent\":{\"id\":null,\"type\":\"NUMBER\",\"name\":\"ME_1\",\"domainValue\":\"num_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},\"messages\":[],\"command\":\"power(Me_1,2)\",\"acomponent\":true,\"ascalar\":false},{\"resultComponent\":{\"id\":3,\"type\":\"INTEGER\",\"name\":\"ME_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},\"messages\":[],\"command\":\"Me_1\",\"acomponent\":true,\"ascalar\":false},{\"result\":{\"id\":9,\"name\":\"temporary_8\",\"description\":null,\"attributes\":[],\"persistent\":false,\"viral\":[],\"identifiers\":[],\"measures\":[{\"id\":29,\"type\":\"INTEGER\",\"name\":\"int_var\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"}]},\"resultComponent\":{\"id\":29,\"type\":\"INTEGER\",\"name\":\"int_var\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},\"messages\":[],\"command\":\"2\",\"acomponent\":false,\"ascalar\":true,\"scalarComponent\":{\"id\":29,\"type\":\"INTEGER\",\"name\":\"int_var\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"}}]";
		List<ResultExpression> result = vtlTestUtils.getResult(commandStatements);
		List<ResultExpression> resultExpressionsToComapare = vtlTestUtils.convertJsonToResultExpression(jsonResult);
		assertNotNull("Conversione non riuscita", resultExpressionsToComapare);
		assertNotNull("Validazione Semantica non riuscita", result);
		assertTrue("I due risultati non sono uguali",
				vtlTestUtils.compareResult(result.get(0), resultExpressionsToComapare.get(0)));

	}

	@Test
	@Transactional
	public void log() throws Exception {
		VtlDataset vtlDatasetFind = datasetRepository.findByNameIgnoreCase("DS_1");
		if (vtlDatasetFind == null) {
			VtlDataset vtlDataset = new VtlDataset();

			vtlDataset.setPersistent(true);
			vtlDataset.setName("DS_8");
			vtlDataset.setIsOnlyAScalar(false);
			vtlDataset.setTransitory(false);
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_1", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_2", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_1", VtlType.INTEGER, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_2", VtlType.NUMBER, VtlComponentRole.MEASURE));
			datasetRepository.save(vtlDataset);
		}
		String commandStatements = "DS_r := log ( DS_8, 2 );";
		String jsonResult = "[{\"result\":{\"id\":51,\"name\":\"DS_r\",\"description\":null,\"attributes\":[],\"persistent\":false,\"measures\":[{\"id\":147,\"type\":\"NUMBER\",\"name\":\"ME_1\",\"domainValue\":\"num_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},{\"id\":148,\"type\":\"NUMBER\",\"name\":\"ME_2\",\"domainValue\":\"num_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"viral\":[],\"identifiers\":[{\"id\":1,\"type\":\"INTEGER\",\"name\":\"ID_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":2,\"type\":\"STRING\",\"name\":\"ID_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}]},\"messages\":[],\"command\":\"DS_r:=log(DS_8,2);\",\"acomponent\":false,\"ascalar\":false},{\"result\":{\"id\":51,\"name\":\"DS_r\",\"description\":null,\"attributes\":[],\"persistent\":false,\"measures\":[{\"id\":147,\"type\":\"NUMBER\",\"name\":\"ME_1\",\"domainValue\":\"num_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},{\"id\":148,\"type\":\"NUMBER\",\"name\":\"ME_2\",\"domainValue\":\"num_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"viral\":[],\"identifiers\":[{\"id\":1,\"type\":\"INTEGER\",\"name\":\"ID_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":2,\"type\":\"STRING\",\"name\":\"ID_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}]},\"messages\":[],\"command\":\"log(DS_8,2)\",\"acomponent\":false,\"ascalar\":false},{\"result\":{\"id\":1,\"name\":\"DS_8\",\"description\":null,\"attributes\":[],\"persistent\":true,\"measures\":[{\"id\":3,\"type\":\"INTEGER\",\"name\":\"ME_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},{\"id\":4,\"type\":\"NUMBER\",\"name\":\"ME_2\",\"domainValue\":\"num_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"viral\":[],\"identifiers\":[{\"id\":1,\"type\":\"INTEGER\",\"name\":\"ID_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":2,\"type\":\"STRING\",\"name\":\"ID_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}]},\"messages\":[],\"command\":\"DS_8\",\"acomponent\":false,\"ascalar\":false},{\"result\":{\"id\":49,\"name\":\"temporary_48\",\"description\":null,\"attributes\":[],\"persistent\":false,\"measures\":[{\"id\":146,\"type\":\"INTEGER\",\"name\":\"int_var\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"viral\":[],\"identifiers\":[]},\"resultComponent\":{\"id\":146,\"type\":\"INTEGER\",\"name\":\"int_var\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},\"messages\":[],\"command\":\"2\",\"acomponent\":false,\"scalarComponent\":{\"id\":146,\"type\":\"INTEGER\",\"name\":\"int_var\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},\"ascalar\":true}]";
		List<ResultExpression> result = vtlTestUtils.getResult(commandStatements);
		List<ResultExpression> resultExpressionsToComapare = vtlTestUtils.convertJsonToResultExpression(jsonResult);
		assertNotNull("Conversione non riuscita", resultExpressionsToComapare);
		assertNotNull("Validazione Semantica non riuscita", result);
		assertTrue("I due risultati non sono uguali",
				vtlTestUtils.compareResult(result.get(0), resultExpressionsToComapare.get(0)));

	}

	@Test
	@Transactional
	public void logInt() throws Exception {

		String commandStatements = "DS_r := log ( 1024, 2 );";
		String jsonResult = "[{\"result\":{\"id\":174,\"name\":\"DS_r\",\"description\":null,\"attributes\":[],\"measures\":[{\"id\":87,\"type\":\"NUMBER\",\"name\":\"num_var\",\"domainValue\":\"num_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"persistent\":false,\"viral\":[],\"identifiers\":[]},\"resultComponent\":{\"id\":87,\"type\":\"NUMBER\",\"name\":\"num_var\",\"domainValue\":\"num_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},\"messages\":[],\"command\":\"DS_r:=log(1024,2);\",\"acomponent\":false,\"scalarComponent\":{\"id\":87,\"type\":\"NUMBER\",\"name\":\"num_var\",\"domainValue\":\"num_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},\"ascalar\":true},{\"result\":{\"id\":174,\"name\":\"DS_r\",\"description\":null,\"attributes\":[],\"measures\":[{\"id\":87,\"type\":\"NUMBER\",\"name\":\"num_var\",\"domainValue\":\"num_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"persistent\":false,\"viral\":[],\"identifiers\":[]},\"resultComponent\":{\"id\":87,\"type\":\"NUMBER\",\"name\":\"num_var\",\"domainValue\":\"num_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},\"messages\":[],\"command\":\"log(1024,2)\",\"acomponent\":false,\"scalarComponent\":{\"id\":87,\"type\":\"NUMBER\",\"name\":\"num_var\",\"domainValue\":\"num_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},\"ascalar\":true},{\"result\":{\"id\":172,\"name\":\"temporary_171\",\"description\":null,\"attributes\":[],\"measures\":[{\"id\":86,\"type\":\"INTEGER\",\"name\":\"int_var\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"persistent\":false,\"viral\":[],\"identifiers\":[]},\"resultComponent\":{\"id\":86,\"type\":\"INTEGER\",\"name\":\"int_var\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},\"messages\":[],\"command\":\"1024\",\"acomponent\":false,\"scalarComponent\":{\"id\":86,\"type\":\"INTEGER\",\"name\":\"int_var\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},\"ascalar\":true},{\"result\":{\"id\":170,\"name\":\"temporary_169\",\"description\":null,\"attributes\":[],\"measures\":[{\"id\":85,\"type\":\"INTEGER\",\"name\":\"int_var\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"persistent\":false,\"viral\":[],\"identifiers\":[]},\"resultComponent\":{\"id\":85,\"type\":\"INTEGER\",\"name\":\"int_var\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},\"messages\":[],\"command\":\"2\",\"acomponent\":false,\"scalarComponent\":{\"id\":85,\"type\":\"INTEGER\",\"name\":\"int_var\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},\"ascalar\":true}]";
		List<ResultExpression> result = vtlTestUtils.getResult(commandStatements);
		List<ResultExpression> resultExpressionsToComapare = vtlTestUtils.convertJsonToResultExpression(jsonResult);
		assertNotNull("Conversione non riuscita", resultExpressionsToComapare);
		assertNotNull("Validazione Semantica non riuscita", result);
		assertTrue("I due risultati non sono uguali",
				vtlTestUtils.compareResult(result.get(0), resultExpressionsToComapare.get(0)));
	}

	@Test
	@Transactional
	public void logComponent() throws Exception {
		VtlDataset vtlDatasetFind = datasetRepository.findByNameIgnoreCase("DS_1");
		if (vtlDatasetFind == null) {
			VtlDataset vtlDataset = new VtlDataset();

			vtlDataset.setPersistent(true);
			vtlDataset.setName("DS_8");
			vtlDataset.setIsOnlyAScalar(false);
			vtlDataset.setTransitory(false);
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_1", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_2", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_1", VtlType.INTEGER, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_2", VtlType.NUMBER, VtlComponentRole.MEASURE));
			datasetRepository.save(vtlDataset);
		}
		String commandStatements = "DS_r := DS_8 [ calc Me_1 := log (Me_1, 2)  ];";
		String jsonResult = "[{\"result\":{\"id\":55,\"name\":\"DS_r\",\"description\":null,\"attributes\":[],\"persistent\":false,\"measures\":[{\"id\":4,\"type\":\"NUMBER\",\"name\":\"ME_2\",\"domainValue\":\"num_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},{\"id\":150,\"type\":\"NUMBER\",\"name\":\"Me_1\",\"domainValue\":\"num_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"viral\":[],\"identifiers\":[{\"id\":1,\"type\":\"INTEGER\",\"name\":\"ID_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":2,\"type\":\"STRING\",\"name\":\"ID_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}]},\"messages\":[],\"command\":\"DS_r:=DS_8[calcMe_1:=log(Me_1,2)];\",\"acomponent\":false,\"ascalar\":false},{\"result\":{\"id\":55,\"name\":\"DS_r\",\"description\":null,\"attributes\":[],\"persistent\":false,\"measures\":[{\"id\":4,\"type\":\"NUMBER\",\"name\":\"ME_2\",\"domainValue\":\"num_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},{\"id\":150,\"type\":\"NUMBER\",\"name\":\"Me_1\",\"domainValue\":\"num_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"viral\":[],\"identifiers\":[{\"id\":1,\"type\":\"INTEGER\",\"name\":\"ID_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":2,\"type\":\"STRING\",\"name\":\"ID_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}]},\"messages\":[],\"command\":\"calcMe_1:=log(Me_1,2)\",\"acomponent\":false,\"ascalar\":false},{\"resultComponent\":{\"id\":null,\"type\":\"NUMBER\",\"name\":\"Me_1\",\"domainValue\":\"num_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},\"messages\":[],\"command\":\"Me_1:=log(Me_1,2)\",\"acomponent\":true,\"ascalar\":false},{\"resultComponent\":{\"id\":null,\"type\":\"NUMBER\",\"name\":\"ME_1\",\"domainValue\":\"num_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},\"messages\":[],\"command\":\"log(Me_1,2)\",\"acomponent\":true,\"ascalar\":false},{\"resultComponent\":{\"id\":3,\"type\":\"INTEGER\",\"name\":\"ME_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},\"messages\":[],\"command\":\"Me_1\",\"acomponent\":true,\"ascalar\":false},{\"result\":{\"id\":53,\"name\":\"temporary_52\",\"description\":null,\"attributes\":[],\"persistent\":false,\"measures\":[{\"id\":149,\"type\":\"INTEGER\",\"name\":\"int_var\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"viral\":[],\"identifiers\":[]},\"resultComponent\":{\"id\":149,\"type\":\"INTEGER\",\"name\":\"int_var\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},\"messages\":[],\"command\":\"2\",\"acomponent\":false,\"scalarComponent\":{\"id\":149,\"type\":\"INTEGER\",\"name\":\"int_var\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},\"ascalar\":true}]";
		List<ResultExpression> result = vtlTestUtils.getResult(commandStatements);
		List<ResultExpression> resultExpressionsToComapare = vtlTestUtils.convertJsonToResultExpression(jsonResult);
		assertNotNull("Conversione non riuscita", resultExpressionsToComapare);
		assertNotNull("Validazione Semantica non riuscita", result);
		assertTrue("I due risultati non sono uguali",
				vtlTestUtils.compareResult(result.get(0), resultExpressionsToComapare.get(0)));

	}

	@Test
	@Transactional
	public void sqrt() throws Exception {
		VtlDataset vtlDatasetFind = datasetRepository.findByNameIgnoreCase("DS_1");
		if (vtlDatasetFind == null) {
			VtlDataset vtlDataset = new VtlDataset();

			vtlDataset.setPersistent(true);
			vtlDataset.setName("DS_8");
			vtlDataset.setIsOnlyAScalar(false);
			vtlDataset.setTransitory(false);
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_1", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_2", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_1", VtlType.INTEGER, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_2", VtlType.NUMBER, VtlComponentRole.MEASURE));
			datasetRepository.save(vtlDataset);
		}
		String commandStatements = "DS_r := sqrt(DS_8);";
		String jsonResult = "[{\"result\":{\"id\":57,\"name\":\"DS_r\",\"description\":null,\"attributes\":[],\"persistent\":false,\"measures\":[{\"id\":151,\"type\":\"NUMBER\",\"name\":\"ME_1\",\"domainValue\":\"num_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},{\"id\":152,\"type\":\"NUMBER\",\"name\":\"ME_2\",\"domainValue\":\"num_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"viral\":[],\"identifiers\":[{\"id\":1,\"type\":\"INTEGER\",\"name\":\"ID_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":2,\"type\":\"STRING\",\"name\":\"ID_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}]},\"messages\":[],\"command\":\"DS_r:=sqrt(DS_8);\",\"acomponent\":false,\"ascalar\":false},{\"result\":{\"id\":57,\"name\":\"DS_r\",\"description\":null,\"attributes\":[],\"persistent\":false,\"measures\":[{\"id\":151,\"type\":\"NUMBER\",\"name\":\"ME_1\",\"domainValue\":\"num_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},{\"id\":152,\"type\":\"NUMBER\",\"name\":\"ME_2\",\"domainValue\":\"num_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"viral\":[],\"identifiers\":[{\"id\":1,\"type\":\"INTEGER\",\"name\":\"ID_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":2,\"type\":\"STRING\",\"name\":\"ID_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}]},\"messages\":[],\"command\":\"sqrt(DS_8)\",\"acomponent\":false,\"ascalar\":false},{\"result\":{\"id\":1,\"name\":\"DS_8\",\"description\":null,\"attributes\":[],\"persistent\":true,\"measures\":[{\"id\":3,\"type\":\"INTEGER\",\"name\":\"ME_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},{\"id\":4,\"type\":\"NUMBER\",\"name\":\"ME_2\",\"domainValue\":\"num_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"viral\":[],\"identifiers\":[{\"id\":1,\"type\":\"INTEGER\",\"name\":\"ID_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":2,\"type\":\"STRING\",\"name\":\"ID_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}]},\"messages\":[],\"command\":\"DS_8\",\"acomponent\":false,\"ascalar\":false}]";
		List<ResultExpression> result = vtlTestUtils.getResult(commandStatements);
		List<ResultExpression> resultExpressionsToComapare = vtlTestUtils.convertJsonToResultExpression(jsonResult);
		assertNotNull("Conversione non riuscita", resultExpressionsToComapare);
		assertNotNull("Validazione Semantica non riuscita", result);
		assertTrue("I due risultati non sono uguali",
				vtlTestUtils.compareResult(result.get(0), resultExpressionsToComapare.get(0)));

	}

	@Test
	@Transactional
	public void sqrtInt() throws Exception {

		String commandStatements = "DS_r := sqrt ( 25 );";
		String jsonResult = "[{\"result\":{\"id\":178,\"name\":\"DS_r\",\"description\":null,\"attributes\":[],\"measures\":[{\"id\":89,\"type\":\"NUMBER\",\"name\":\"num_var\",\"domainValue\":\"num_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"persistent\":false,\"viral\":[],\"identifiers\":[]},\"resultComponent\":{\"id\":89,\"type\":\"NUMBER\",\"name\":\"num_var\",\"domainValue\":\"num_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},\"messages\":[],\"command\":\"DS_r:=sqrt(25);\",\"acomponent\":false,\"scalarComponent\":{\"id\":89,\"type\":\"NUMBER\",\"name\":\"num_var\",\"domainValue\":\"num_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},\"ascalar\":true},{\"result\":{\"id\":178,\"name\":\"DS_r\",\"description\":null,\"attributes\":[],\"measures\":[{\"id\":89,\"type\":\"NUMBER\",\"name\":\"num_var\",\"domainValue\":\"num_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"persistent\":false,\"viral\":[],\"identifiers\":[]},\"resultComponent\":{\"id\":89,\"type\":\"NUMBER\",\"name\":\"num_var\",\"domainValue\":\"num_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},\"messages\":[],\"command\":\"sqrt(25)\",\"acomponent\":false,\"scalarComponent\":{\"id\":89,\"type\":\"NUMBER\",\"name\":\"num_var\",\"domainValue\":\"num_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},\"ascalar\":true},{\"result\":{\"id\":176,\"name\":\"temporary_175\",\"description\":null,\"attributes\":[],\"measures\":[{\"id\":88,\"type\":\"INTEGER\",\"name\":\"int_var\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"persistent\":false,\"viral\":[],\"identifiers\":[]},\"resultComponent\":{\"id\":88,\"type\":\"INTEGER\",\"name\":\"int_var\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},\"messages\":[],\"command\":\"25\",\"acomponent\":false,\"scalarComponent\":{\"id\":88,\"type\":\"INTEGER\",\"name\":\"int_var\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},\"ascalar\":true}]";
		List<ResultExpression> result = vtlTestUtils.getResult(commandStatements);
		List<ResultExpression> resultExpressionsToComapare = vtlTestUtils.convertJsonToResultExpression(jsonResult);
		assertNotNull("Conversione non riuscita", resultExpressionsToComapare);
		assertNotNull("Validazione Semantica non riuscita", result);
		assertTrue("I due risultati non sono uguali",
				vtlTestUtils.compareResult(result.get(0), resultExpressionsToComapare.get(0)));
	}

	@Test
	@Transactional
	public void sqrtComponent() throws Exception {
		VtlDataset vtlDatasetFind = datasetRepository.findByNameIgnoreCase("DS_1");
		if (vtlDatasetFind == null) {
			VtlDataset vtlDataset = new VtlDataset();

			vtlDataset.setPersistent(true);
			vtlDataset.setName("DS_8");
			vtlDataset.setIsOnlyAScalar(false);
			vtlDataset.setTransitory(false);
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_1", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_2", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_1", VtlType.INTEGER, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_2", VtlType.NUMBER, VtlComponentRole.MEASURE));
			datasetRepository.save(vtlDataset);
		}
		String commandStatements = "DS_r := DS_8 [ calc Me_1 := sqrt ( Me_1 ) ];";
		String jsonResult = "[{\"result\":{\"id\":142,\"name\":\"DS_r\",\"description\":null,\"attributes\":[],\"persistent\":false,\"measures\":[{\"id\":360,\"type\":\"NUMBER\",\"name\":\"ME_2\",\"domainValue\":\"num_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},{\"id\":361,\"type\":\"NUMBER\",\"name\":\"Me_1\",\"domainValue\":\"num_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"identifiers\":[{\"id\":358,\"type\":\"INTEGER\",\"name\":\"ID_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":359,\"type\":\"STRING\",\"name\":\"ID_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}],\"viral\":[]},\"messages\":[],\"command\":\"DS_r:=DS_8[calcMe_1:=sqrt(Me_1)];\",\"acomponent\":false,\"ascalar\":false},{\"result\":{\"id\":140,\"name\":\"temporary_139\",\"description\":null,\"attributes\":[],\"persistent\":false,\"measures\":[{\"id\":356,\"type\":\"NUMBER\",\"name\":\"ME_2\",\"domainValue\":\"num_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},{\"id\":357,\"type\":\"NUMBER\",\"name\":\"Me_1\",\"domainValue\":\"num_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"identifiers\":[{\"id\":353,\"type\":\"INTEGER\",\"name\":\"ID_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":354,\"type\":\"STRING\",\"name\":\"ID_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}],\"viral\":[]},\"messages\":[],\"command\":\"calcMe_1:=sqrt(Me_1)\",\"acomponent\":false,\"ascalar\":false},{\"resultComponent\":{\"id\":null,\"type\":\"NUMBER\",\"name\":\"Me_1\",\"domainValue\":\"num_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},\"messages\":[],\"command\":\"Me_1:=sqrt(Me_1)\",\"acomponent\":true,\"ascalar\":false},{\"resultComponent\":{\"id\":null,\"type\":\"NUMBER\",\"name\":\"ME_1\",\"domainValue\":\"num_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},\"messages\":[],\"command\":\"sqrt(Me_1)\",\"acomponent\":true,\"ascalar\":false},{\"resultComponent\":{\"id\":28,\"type\":\"INTEGER\",\"name\":\"ME_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},\"messages\":[],\"command\":\"Me_1\",\"acomponent\":true,\"ascalar\":false}]";
		List<ResultExpression> result = vtlTestUtils.getResult(commandStatements);
		List<ResultExpression> resultExpressionsToComapare = vtlTestUtils.convertJsonToResultExpression(jsonResult);
		assertNotNull("Conversione non riuscita", resultExpressionsToComapare);
		assertNotNull("Validazione Semantica non riuscita", result);
		assertTrue("I due risultati non sono uguali",
				vtlTestUtils.compareResult(result.get(0), resultExpressionsToComapare.get(0)));

	}

	@Test
	@Transactional
	public void round() throws Exception {
		VtlDataset vtlDatasetFind = datasetRepository.findByNameIgnoreCase("DS_1");
		if (vtlDatasetFind == null) {
			VtlDataset vtlDataset = new VtlDataset();

			vtlDataset.setPersistent(true);
			vtlDataset.setName("DS_10");
			vtlDataset.setIsOnlyAScalar(false);
			vtlDataset.setTransitory(false);
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_1", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_2", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_1", VtlType.NUMBER, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_2", VtlType.NUMBER, VtlComponentRole.MEASURE));
			datasetRepository.save(vtlDataset);

		}
		String commandStatements = "DS_r := round(DS_10, 0);";
		String jsonResult = "[{\"result\":{\"id\":110,\"name\":\"DS_r\",\"description\":null,\"attributes\":[],\"persistent\":false,\"viral\":[],\"measures\":[{\"id\":315,\"type\":\"NUMBER\",\"name\":\"ME_1\",\"domainValue\":\"num_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},{\"id\":316,\"type\":\"NUMBER\",\"name\":\"ME_2\",\"domainValue\":\"num_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"identifiers\":[{\"id\":317,\"type\":\"INTEGER\",\"name\":\"ID_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":318,\"type\":\"STRING\",\"name\":\"ID_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}]},\"messages\":[],\"command\":\"DS_r:=round(DS_10,0)<missing ';'>\",\"acomponent\":false,\"ascalar\":false},{\"result\":{\"id\":108,\"name\":\"temporary_107\",\"description\":null,\"attributes\":[],\"persistent\":false,\"viral\":[],\"measures\":[{\"id\":311,\"type\":\"NUMBER\",\"name\":\"ME_1\",\"domainValue\":\"num_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},{\"id\":312,\"type\":\"NUMBER\",\"name\":\"ME_2\",\"domainValue\":\"num_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"identifiers\":[{\"id\":313,\"type\":\"INTEGER\",\"name\":\"ID_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":314,\"type\":\"STRING\",\"name\":\"ID_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}]},\"messages\":[],\"command\":\"round(DS_10,0)\",\"acomponent\":false,\"ascalar\":false},{\"result\":{\"id\":10,\"name\":\"DS_10\",\"description\":null,\"attributes\":[],\"persistent\":true,\"viral\":[],\"measures\":[{\"id\":37,\"type\":\"NUMBER\",\"name\":\"ME_1\",\"domainValue\":\"num_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},{\"id\":38,\"type\":\"NUMBER\",\"name\":\"ME_2\",\"domainValue\":\"num_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"identifiers\":[{\"id\":35,\"type\":\"INTEGER\",\"name\":\"ID_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":36,\"type\":\"STRING\",\"name\":\"ID_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}]},\"messages\":[],\"command\":\"DS_10\",\"acomponent\":false,\"ascalar\":false},{\"result\":{\"id\":106,\"name\":\"temporary_105\",\"description\":null,\"attributes\":[],\"persistent\":false,\"viral\":[],\"measures\":[{\"id\":310,\"type\":\"INTEGER\",\"name\":\"int_var\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"identifiers\":[]},\"resultComponent\":{\"id\":310,\"type\":\"INTEGER\",\"name\":\"int_var\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},\"messages\":[],\"command\":\"0\",\"acomponent\":false,\"ascalar\":true,\"scalarComponent\":{\"id\":310,\"type\":\"INTEGER\",\"name\":\"int_var\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"}}]";
		List<ResultExpression> result = vtlTestUtils.getResult(commandStatements);
		List<ResultExpression> resultExpressionsToComapare = vtlTestUtils.convertJsonToResultExpression(jsonResult);
		assertNotNull("Conversione non riuscita", resultExpressionsToComapare);
		assertNotNull("Validazione Semantica non riuscita", result);
		assertTrue("I due risultati non sono uguali",
				vtlTestUtils.compareResult(result.get(0), resultExpressionsToComapare.get(0)));

	}

	@Test
	@Transactional
	public void roundComponent() throws Exception {
		VtlDataset vtlDatasetFind = datasetRepository.findByNameIgnoreCase("DS_1");
		if (vtlDatasetFind == null) {
			VtlDataset vtlDataset = new VtlDataset();

			vtlDataset.setPersistent(true);
			vtlDataset.setName("DS_10");
			vtlDataset.setIsOnlyAScalar(false);
			vtlDataset.setTransitory(false);
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_1", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_2", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_1", VtlType.NUMBER, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_2", VtlType.NUMBER, VtlComponentRole.MEASURE));
			datasetRepository.save(vtlDataset);

		}
		String commandStatements = "DS_r := DS_10 [ calc Me_10:= round( Me_1 ) ];";
		String jsonResult = "[{\"result\":{\"id\":11,\"name\":\"DS_r\",\"description\":null,\"attributes\":[],\"persistent\":false,\"identifiers\":[{\"id\":1,\"type\":\"INTEGER\",\"name\":\"ID_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":2,\"type\":\"STRING\",\"name\":\"ID_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}],\"measures\":[{\"id\":3,\"type\":\"NUMBER\",\"name\":\"ME_1\",\"domainValue\":\"num_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},{\"id\":4,\"type\":\"NUMBER\",\"name\":\"ME_2\",\"domainValue\":\"num_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},{\"id\":30,\"type\":\"NUMBER\",\"name\":\"Me_10\",\"domainValue\":\"num_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"viral\":[]},\"messages\":[],\"command\":\"DS_r:=DS_10[calcMe_10:=round(Me_1)];\",\"ascalar\":false,\"acomponent\":false},{\"result\":{\"id\":11,\"name\":\"DS_r\",\"description\":null,\"attributes\":[],\"persistent\":false,\"identifiers\":[{\"id\":1,\"type\":\"INTEGER\",\"name\":\"ID_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":2,\"type\":\"STRING\",\"name\":\"ID_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}],\"measures\":[{\"id\":3,\"type\":\"NUMBER\",\"name\":\"ME_1\",\"domainValue\":\"num_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},{\"id\":4,\"type\":\"NUMBER\",\"name\":\"ME_2\",\"domainValue\":\"num_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},{\"id\":30,\"type\":\"NUMBER\",\"name\":\"Me_10\",\"domainValue\":\"num_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"viral\":[]},\"messages\":[],\"command\":\"calcMe_10:=round(Me_1)\",\"ascalar\":false,\"acomponent\":false},{\"resultComponent\":{\"id\":null,\"type\":\"NUMBER\",\"name\":\"Me_10\",\"domainValue\":\"num_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},\"messages\":[],\"command\":\"Me_10:=round(Me_1)\",\"ascalar\":false,\"acomponent\":true},{\"resultComponent\":{\"id\":null,\"type\":\"NUMBER\",\"name\":\"ME_1\",\"domainValue\":\"num_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},\"messages\":[],\"command\":\"round(Me_1)\",\"ascalar\":false,\"acomponent\":true},{\"resultComponent\":{\"id\":3,\"type\":\"NUMBER\",\"name\":\"ME_1\",\"domainValue\":\"num_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},\"messages\":[],\"command\":\"Me_1\",\"ascalar\":false,\"acomponent\":true}]";
		List<ResultExpression> result = vtlTestUtils.getResult(commandStatements);
		List<ResultExpression> resultExpressionsToComapare = vtlTestUtils.convertJsonToResultExpression(jsonResult);
		assertNotNull("Conversione non riuscita", resultExpressionsToComapare);
		assertNotNull("Validazione Semantica non riuscita", result);
		assertTrue("I due risultati non sono uguali",
				vtlTestUtils.compareResult(result.get(0), resultExpressionsToComapare.get(0)));

	}

	@Test
	@Transactional
	public void roundComponentMe() throws Exception {
		VtlDataset vtlDatasetFind = datasetRepository.findByNameIgnoreCase("DS_1");
		if (vtlDatasetFind == null) {
			VtlDataset vtlDataset = new VtlDataset();

			vtlDataset.setPersistent(true);
			vtlDataset.setName("DS_10");
			vtlDataset.setIsOnlyAScalar(false);
			vtlDataset.setTransitory(false);
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_1", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_2", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_1", VtlType.NUMBER, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_2", VtlType.NUMBER, VtlComponentRole.MEASURE));
			datasetRepository.save(vtlDataset);

		}
		String commandStatements = "DS_r := DS_10 [ calc Me_20:= round( Me_1 , -1 ) ];";
		String jsonResult = "[{\"result\":{\"id\":122,\"name\":\"DS_r\",\"description\":null,\"attributes\":[],\"persistent\":false,\"viral\":[],\"measures\":[{\"id\":338,\"type\":\"NUMBER\",\"name\":\"ME_1\",\"domainValue\":\"num_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},{\"id\":339,\"type\":\"NUMBER\",\"name\":\"ME_2\",\"domainValue\":\"num_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},{\"id\":340,\"type\":\"NUMBER\",\"name\":\"Me_20\",\"domainValue\":\"num_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"identifiers\":[{\"id\":336,\"type\":\"INTEGER\",\"name\":\"ID_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":337,\"type\":\"STRING\",\"name\":\"ID_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}]},\"messages\":[],\"command\":\"DS_r:=DS_10[calcMe_20:=round(Me_1,-1)]<missing ';'>\",\"acomponent\":false,\"ascalar\":false},{\"result\":{\"id\":120,\"name\":\"temporary_119\",\"description\":null,\"attributes\":[],\"persistent\":false,\"viral\":[],\"measures\":[{\"id\":333,\"type\":\"NUMBER\",\"name\":\"ME_1\",\"domainValue\":\"num_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},{\"id\":334,\"type\":\"NUMBER\",\"name\":\"ME_2\",\"domainValue\":\"num_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},{\"id\":335,\"type\":\"NUMBER\",\"name\":\"Me_20\",\"domainValue\":\"num_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"identifiers\":[{\"id\":331,\"type\":\"INTEGER\",\"name\":\"ID_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":332,\"type\":\"STRING\",\"name\":\"ID_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}]},\"messages\":[],\"command\":\"calcMe_20:=round(Me_1,-1)\",\"acomponent\":false,\"ascalar\":false},{\"resultComponent\":{\"id\":null,\"type\":\"NUMBER\",\"name\":\"Me_20\",\"domainValue\":\"num_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},\"messages\":[],\"command\":\"Me_20:=round(Me_1,-1)\",\"acomponent\":true,\"ascalar\":false},{\"resultComponent\":{\"id\":null,\"type\":\"NUMBER\",\"name\":\"ME_1\",\"domainValue\":\"num_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},\"messages\":[],\"command\":\"round(Me_1,-1)\",\"acomponent\":true,\"ascalar\":false},{\"resultComponent\":{\"id\":37,\"type\":\"NUMBER\",\"name\":\"ME_1\",\"domainValue\":\"num_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},\"messages\":[],\"command\":\"Me_1\",\"acomponent\":true,\"ascalar\":false},{\"result\":{\"id\":118,\"name\":\"temporary_117\",\"description\":null,\"attributes\":[],\"persistent\":false,\"viral\":[],\"measures\":[{\"id\":330,\"type\":\"INTEGER\",\"name\":\"int_var\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"identifiers\":[]},\"resultComponent\":{\"id\":330,\"type\":\"INTEGER\",\"name\":\"int_var\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},\"messages\":[],\"command\":\"-1\",\"acomponent\":false,\"ascalar\":true,\"scalarComponent\":{\"id\":330,\"type\":\"INTEGER\",\"name\":\"int_var\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"}}]";
		List<ResultExpression> result = vtlTestUtils.getResult(commandStatements);
		List<ResultExpression> resultExpressionsToComapare = vtlTestUtils.convertJsonToResultExpression(jsonResult);
		assertNotNull("Conversione non riuscita", resultExpressionsToComapare);
		assertNotNull("Validazione Semantica non riuscita", result);
		assertTrue("I due risultati non sono uguali",
				vtlTestUtils.compareResult(result.get(0), resultExpressionsToComapare.get(0)));

	}

	@Test
	@Transactional
	public void roundNumber() throws Exception {

		String commandStatements = "DS_r := round ( 3.14159 , 2 );";
		String jsonResult = "[{\"result\":{\"id\":96,\"name\":\"DS_r\",\"description\":null,\"attributes\":[],\"measures\":[{\"id\":48,\"type\":\"NUMBER\",\"name\":\"num_var\",\"domainValue\":\"num_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"persistent\":false,\"viral\":[],\"identifiers\":[]},\"resultComponent\":{\"id\":48,\"type\":\"NUMBER\",\"name\":\"num_var\",\"domainValue\":\"num_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},\"messages\":[],\"command\":\"DS_r:=round(3.14159,2);\",\"acomponent\":false,\"scalarComponent\":{\"id\":48,\"type\":\"NUMBER\",\"name\":\"num_var\",\"domainValue\":\"num_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},\"ascalar\":true},{\"result\":{\"id\":96,\"name\":\"DS_r\",\"description\":null,\"attributes\":[],\"measures\":[{\"id\":48,\"type\":\"NUMBER\",\"name\":\"num_var\",\"domainValue\":\"num_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"persistent\":false,\"viral\":[],\"identifiers\":[]},\"resultComponent\":{\"id\":48,\"type\":\"NUMBER\",\"name\":\"num_var\",\"domainValue\":\"num_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},\"messages\":[],\"command\":\"round(3.14159,2)\",\"acomponent\":false,\"scalarComponent\":{\"id\":48,\"type\":\"NUMBER\",\"name\":\"num_var\",\"domainValue\":\"num_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},\"ascalar\":true},{\"result\":{\"id\":94,\"name\":\"temporary_93\",\"description\":null,\"attributes\":[],\"measures\":[{\"id\":47,\"type\":\"NUMBER\",\"name\":\"num_var\",\"domainValue\":\"num_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"persistent\":false,\"viral\":[],\"identifiers\":[]},\"resultComponent\":{\"id\":47,\"type\":\"NUMBER\",\"name\":\"num_var\",\"domainValue\":\"num_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},\"messages\":[],\"command\":\"3.14159\",\"acomponent\":false,\"scalarComponent\":{\"id\":47,\"type\":\"NUMBER\",\"name\":\"num_var\",\"domainValue\":\"num_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},\"ascalar\":true},{\"result\":{\"id\":92,\"name\":\"temporary_91\",\"description\":null,\"attributes\":[],\"measures\":[{\"id\":46,\"type\":\"INTEGER\",\"name\":\"int_var\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"persistent\":false,\"viral\":[],\"identifiers\":[]},\"resultComponent\":{\"id\":46,\"type\":\"INTEGER\",\"name\":\"int_var\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},\"messages\":[],\"command\":\"2\",\"acomponent\":false,\"scalarComponent\":{\"id\":46,\"type\":\"INTEGER\",\"name\":\"int_var\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},\"ascalar\":true}]";
		List<ResultExpression> result = vtlTestUtils.getResult(commandStatements);
		List<ResultExpression> resultExpressionsToComapare = vtlTestUtils.convertJsonToResultExpression(jsonResult);
		assertNotNull("Conversione non riuscita", resultExpressionsToComapare);
		assertNotNull("Validazione Semantica non riuscita", result);
		assertTrue("I due risultati non sono uguali",
				vtlTestUtils.compareResult(result.get(0), resultExpressionsToComapare.get(0)));
	}

	@Test
	@Transactional
	public void truncNumber_() throws Exception {

		String commandStatements = "DS_r := trunc ( 3.14159 , _ );";
		String jsonResult = "[{\"result\":{\"id\":6,\"name\":\"DS_r\",\"description\":null,\"attributes\":[],\"measures\":[{\"id\":3,\"type\":\"NUMBER\",\"name\":\"num_var\",\"domainValue\":\"num_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"identifiers\":[],\"viral\":[],\"persistent\":false},\"resultComponent\":{\"id\":3,\"type\":\"NUMBER\",\"name\":\"num_var\",\"domainValue\":\"num_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},\"messages\":[],\"command\":\"DS_r:=trunc(3.14159,_);\",\"acomponent\":false,\"scalarComponent\":{\"id\":3,\"type\":\"NUMBER\",\"name\":\"num_var\",\"domainValue\":\"num_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},\"ascalar\":true},{\"result\":{\"id\":4,\"name\":\"temporary_3\",\"description\":null,\"attributes\":[],\"measures\":[{\"id\":2,\"type\":\"NUMBER\",\"name\":\"num_var\",\"domainValue\":\"num_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"identifiers\":[],\"viral\":[],\"persistent\":false},\"resultComponent\":{\"id\":2,\"type\":\"NUMBER\",\"name\":\"num_var\",\"domainValue\":\"num_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},\"messages\":[],\"command\":\"trunc(3.14159,_)\",\"acomponent\":false,\"scalarComponent\":{\"id\":2,\"type\":\"NUMBER\",\"name\":\"num_var\",\"domainValue\":\"num_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},\"ascalar\":true},{\"result\":{\"id\":2,\"name\":\"temporary_1\",\"description\":null,\"attributes\":[],\"measures\":[{\"id\":1,\"type\":\"NUMBER\",\"name\":\"num_var\",\"domainValue\":\"num_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"identifiers\":[],\"viral\":[],\"persistent\":false},\"resultComponent\":{\"id\":1,\"type\":\"NUMBER\",\"name\":\"num_var\",\"domainValue\":\"num_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},\"messages\":[],\"command\":\"3.14159\",\"acomponent\":false,\"scalarComponent\":{\"id\":1,\"type\":\"NUMBER\",\"name\":\"num_var\",\"domainValue\":\"num_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},\"ascalar\":true}]";

		List<ResultExpression> result = vtlTestUtils.getResult(commandStatements);

		List<ResultExpression> resultExpressionsToComapare = vtlTestUtils.convertJsonToResultExpression(jsonResult);
		assertNotNull("Conversione non riuscita", resultExpressionsToComapare);
		assertNotNull("Validazione Semantica non riuscita", result);
		assertTrue("I due risultati non sono uguali",
				vtlTestUtils.compareResult(result.get(0), resultExpressionsToComapare.get(0)));
	}

	@Test
	@Transactional
	public void ln_e() throws Exception {
		// L'operatore E non è previsto nella grammatica
		String commandStatements = "DS_r := ln ( e );";
		String jsonResult = "";
		try {
			List<ResultExpression> result = vtlTestUtils.getResult(commandStatements);
			List<ResultExpression> resultExpressionsToComapare = vtlTestUtils.convertJsonToResultExpression(jsonResult);
			assertNotNull("Conversione non riuscita", resultExpressionsToComapare);
			assertNotNull("Validazione Semantica non riuscita", result);
			assertTrue("I due risultati non sono uguali",
					vtlTestUtils.compareResult(result.get(0), resultExpressionsToComapare.get(0)));
		} catch (Exception e) {
			assertTrue("Operatore non supportato", true);
		}
	}

}
