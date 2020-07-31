package com.capgemini.istat.vtlcompiler.vtlapi.semantic;

import com.capgemini.istat.vtlcompiler.vtlcommon.model.dataset.VtlComponentRole;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.dataset.VtlDataset;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.dataset.VtlType;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.semantic.ResultExpression;
import com.capgemini.istat.vtlcompiler.vtlcommon.repository.DatasetRepository;
import com.capgemini.istat.vtlcompiler.vtlapi.utils.VtlTestUtils;
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

public class VtlComparisonOpetatorsTest {

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
	public void equal() throws Exception {
		VtlDataset vtlDatasetFind = datasetRepository.findByNameIgnoreCase("DS_1");
		if (vtlDatasetFind == null) {
			VtlDataset vtlDataset = new VtlDataset();

			vtlDataset.setPersistent(true);
			vtlDataset.setName("DS_11");
			vtlDataset.setIsOnlyAScalar(false);
			vtlDataset.setTransitory(false);
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_1", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_2", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_3", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_4", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_1", VtlType.NUMBER, VtlComponentRole.MEASURE));
			datasetRepository.save(vtlDataset);
		}
		String commandStatements = "DS_r := DS_11 = 8;";
		String jsonResult = "[{\"result\":{\"id\":36,\"name\":\"DS_r\",\"description\":null,\"attributes\":[],\"persistent\":false,\"identifiers\":[{\"id\":1,\"type\":\"INTEGER\",\"name\":\"ID_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":2,\"type\":\"STRING\",\"name\":\"ID_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":3,\"type\":\"STRING\",\"name\":\"ID_3\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":4,\"type\":\"STRING\",\"name\":\"ID_4\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}],\"viral\":[],\"measures\":[{\"id\":132,\"type\":\"BOOLEAN\",\"name\":\"bool_var\",\"domainValue\":\"bool_vd\",\"domainValueParent\":\"bool_vd\",\"vtlComponentRole\":\"MEASURE\"}]},\"messages\":[],\"command\":\"DS_r:=DS_11=8;\",\"ascalar\":false,\"acomponent\":false},{\"result\":{\"id\":36,\"name\":\"DS_r\",\"description\":null,\"attributes\":[],\"persistent\":false,\"identifiers\":[{\"id\":1,\"type\":\"INTEGER\",\"name\":\"ID_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":2,\"type\":\"STRING\",\"name\":\"ID_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":3,\"type\":\"STRING\",\"name\":\"ID_3\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":4,\"type\":\"STRING\",\"name\":\"ID_4\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}],\"viral\":[],\"measures\":[{\"id\":132,\"type\":\"BOOLEAN\",\"name\":\"bool_var\",\"domainValue\":\"bool_vd\",\"domainValueParent\":\"bool_vd\",\"vtlComponentRole\":\"MEASURE\"}]},\"messages\":[],\"command\":\"DS_11=8\",\"ascalar\":false,\"acomponent\":false},{\"result\":{\"id\":1,\"name\":\"DS_11\",\"description\":null,\"attributes\":[],\"persistent\":true,\"identifiers\":[{\"id\":1,\"type\":\"INTEGER\",\"name\":\"ID_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":2,\"type\":\"STRING\",\"name\":\"ID_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":3,\"type\":\"STRING\",\"name\":\"ID_3\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":4,\"type\":\"STRING\",\"name\":\"ID_4\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}],\"viral\":[],\"measures\":[{\"id\":5,\"type\":\"NUMBER\",\"name\":\"ME_1\",\"domainValue\":\"num_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"}]},\"messages\":[],\"command\":\"DS_11\",\"ascalar\":false,\"acomponent\":false},{\"result\":{\"id\":32,\"name\":\"temporary_31\",\"description\":null,\"attributes\":[],\"persistent\":false,\"identifiers\":[],\"viral\":[],\"measures\":[{\"id\":130,\"type\":\"INTEGER\",\"name\":\"int_var\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"}]},\"resultComponent\":{\"id\":130,\"type\":\"INTEGER\",\"name\":\"int_var\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},\"messages\":[],\"command\":\"8\",\"ascalar\":true,\"acomponent\":false,\"scalarComponent\":{\"id\":130,\"type\":\"INTEGER\",\"name\":\"int_var\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"}}]";
		List<ResultExpression> result = vtlTestUtils.getResult(commandStatements);
		List<ResultExpression> resultExpressionsToComapare = vtlTestUtils.convertJsonToResultExpression(jsonResult);
		assertNotNull("Conversione non riuscita", resultExpressionsToComapare);
		assertNotNull("Validazione Semantica non riuscita", result);
		assertTrue("I due risultati non sono uguali",
				vtlTestUtils.compareResult(result.get(0), resultExpressionsToComapare.get(0)));

	}

	@Test
	@Transactional
	public void equalDs() throws Exception {
		VtlDataset vtlDatasetFind = datasetRepository.findByNameIgnoreCase("DS_1");
		if (vtlDatasetFind == null) {
			VtlDataset vtlDataset = new VtlDataset();

			vtlDataset.setPersistent(true);
			vtlDataset.setName("DS_11");
			vtlDataset.setIsOnlyAScalar(false);
			vtlDataset.setTransitory(false);
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_1", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_2", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_3", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_4", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_1", VtlType.NUMBER, VtlComponentRole.MEASURE));
			datasetRepository.save(vtlDataset);

			VtlDataset vtlDatasetSecond = new VtlDataset();
			vtlDatasetSecond.setPersistent(true);
			vtlDatasetSecond.setName("DS_12");
			vtlDatasetSecond.setIsOnlyAScalar(false);
			vtlDatasetSecond.setTransitory(false);
			vtlDatasetSecond
					.addComponent(vtlTestUtils.getVtlComponent("ID_1", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDatasetSecond
					.addComponent(vtlTestUtils.getVtlComponent("ID_2", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDatasetSecond
					.addComponent(vtlTestUtils.getVtlComponent("ID_4", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDatasetSecond
					.addComponent(vtlTestUtils.getVtlComponent("ME_1", VtlType.NUMBER, VtlComponentRole.MEASURE));
			datasetRepository.save(vtlDatasetSecond);
		}
		String commandStatements = "DS_r := DS_11 = DS_12;";
		String jsonResult = "[{\"result\":{\"id\":40,\"name\":\"DS_r\",\"description\":null,\"attributes\":[],\"persistent\":false,\"identifiers\":[{\"id\":1,\"type\":\"INTEGER\",\"name\":\"ID_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":2,\"type\":\"STRING\",\"name\":\"ID_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":3,\"type\":\"STRING\",\"name\":\"ID_3\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":4,\"type\":\"STRING\",\"name\":\"ID_4\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}],\"viral\":[],\"measures\":[{\"id\":134,\"type\":\"BOOLEAN\",\"name\":\"bool_var\",\"domainValue\":\"bool_vd\",\"domainValueParent\":\"bool_vd\",\"vtlComponentRole\":\"MEASURE\"}]},\"messages\":[],\"command\":\"DS_r:=DS_11=DS_12;\",\"ascalar\":false,\"acomponent\":false},{\"result\":{\"id\":40,\"name\":\"DS_r\",\"description\":null,\"attributes\":[],\"persistent\":false,\"identifiers\":[{\"id\":1,\"type\":\"INTEGER\",\"name\":\"ID_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":2,\"type\":\"STRING\",\"name\":\"ID_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":3,\"type\":\"STRING\",\"name\":\"ID_3\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":4,\"type\":\"STRING\",\"name\":\"ID_4\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}],\"viral\":[],\"measures\":[{\"id\":134,\"type\":\"BOOLEAN\",\"name\":\"bool_var\",\"domainValue\":\"bool_vd\",\"domainValueParent\":\"bool_vd\",\"vtlComponentRole\":\"MEASURE\"}]},\"messages\":[],\"command\":\"DS_11=DS_12\",\"ascalar\":false,\"acomponent\":false},{\"result\":{\"id\":1,\"name\":\"DS_11\",\"description\":null,\"attributes\":[],\"persistent\":true,\"identifiers\":[{\"id\":1,\"type\":\"INTEGER\",\"name\":\"ID_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":2,\"type\":\"STRING\",\"name\":\"ID_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":3,\"type\":\"STRING\",\"name\":\"ID_3\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":4,\"type\":\"STRING\",\"name\":\"ID_4\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}],\"viral\":[],\"measures\":[{\"id\":5,\"type\":\"NUMBER\",\"name\":\"ME_1\",\"domainValue\":\"num_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"}]},\"messages\":[],\"command\":\"DS_11\",\"ascalar\":false,\"acomponent\":false},{\"result\":{\"id\":2,\"name\":\"DS_12\",\"description\":null,\"attributes\":[],\"persistent\":true,\"identifiers\":[{\"id\":6,\"type\":\"INTEGER\",\"name\":\"ID_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":7,\"type\":\"STRING\",\"name\":\"ID_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":8,\"type\":\"STRING\",\"name\":\"ID_4\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}],\"viral\":[],\"measures\":[{\"id\":9,\"type\":\"NUMBER\",\"name\":\"ME_1\",\"domainValue\":\"num_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"}]},\"messages\":[],\"command\":\"DS_12\",\"ascalar\":false,\"acomponent\":false}]";
		List<ResultExpression> result = vtlTestUtils.getResult(commandStatements);
		List<ResultExpression> resultExpressionsToComapare = vtlTestUtils.convertJsonToResultExpression(jsonResult);
		assertNotNull("Conversione non riuscita", resultExpressionsToComapare);
		assertNotNull("Validazione Semantica non riuscita", result);
		assertTrue("I due risultati non sono uguali",
				vtlTestUtils.compareResult(result.get(0), resultExpressionsToComapare.get(0)));

	}

	@Test
	@Transactional
	public void equalComponent() throws Exception {
		VtlDataset vtlDatasetFind = datasetRepository.findByNameIgnoreCase("DS_1");
		if (vtlDatasetFind == null) {
			VtlDataset vtlDataset = new VtlDataset();

			vtlDataset.setPersistent(true);
			vtlDataset.setName("DS_11");
			vtlDataset.setIsOnlyAScalar(false);
			vtlDataset.setTransitory(false);
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_1", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_2", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_3", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_4", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_1", VtlType.NUMBER, VtlComponentRole.MEASURE));
			datasetRepository.save(vtlDataset);
		}
		String commandStatements = "DS_r := DS_11 [ calc Me_2 := Me_1 = 0.08 ];";
		String jsonResult = "[{\"result\":{\"id\":148,\"name\":\"DS_r\",\"description\":null,\"attributes\":[],\"persistent\":false,\"measures\":[{\"id\":373,\"type\":\"NUMBER\",\"name\":\"ME_1\",\"domainValue\":\"num_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},{\"id\":374,\"type\":\"BOOLEAN\",\"name\":\"Me_2\",\"domainValue\":\"bool_vd\",\"domainValueParent\":\"bool_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"identifiers\":[{\"id\":369,\"type\":\"INTEGER\",\"name\":\"ID_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":370,\"type\":\"STRING\",\"name\":\"ID_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":371,\"type\":\"STRING\",\"name\":\"ID_3\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":372,\"type\":\"STRING\",\"name\":\"ID_4\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}],\"viral\":[]},\"messages\":[],\"command\":\"DS_r:=DS_11[calcMe_2:=Me_1=0.08];\",\"acomponent\":false,\"ascalar\":false},{\"result\":{\"id\":146,\"name\":\"temporary_145\",\"description\":null,\"attributes\":[],\"persistent\":false,\"measures\":[{\"id\":367,\"type\":\"NUMBER\",\"name\":\"ME_1\",\"domainValue\":\"num_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},{\"id\":368,\"type\":\"BOOLEAN\",\"name\":\"Me_2\",\"domainValue\":\"bool_vd\",\"domainValueParent\":\"bool_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"identifiers\":[{\"id\":363,\"type\":\"INTEGER\",\"name\":\"ID_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":364,\"type\":\"STRING\",\"name\":\"ID_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":365,\"type\":\"STRING\",\"name\":\"ID_3\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":366,\"type\":\"STRING\",\"name\":\"ID_4\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}],\"viral\":[]},\"messages\":[],\"command\":\"calcMe_2:=Me_1=0.08\",\"acomponent\":false,\"ascalar\":false},{\"resultComponent\":{\"id\":null,\"type\":\"BOOLEAN\",\"name\":\"Me_2\",\"domainValue\":\"bool_vd\",\"domainValueParent\":\"bool_vd\",\"vtlComponentRole\":\"MEASURE\"},\"messages\":[],\"command\":\"Me_2:=Me_1=0.08\",\"acomponent\":true,\"ascalar\":false},{\"resultComponent\":{\"id\":null,\"type\":\"BOOLEAN\",\"name\":\"bool_var\",\"domainValue\":\"bool_vd\",\"domainValueParent\":\"bool_vd\",\"vtlComponentRole\":\"MEASURE\"},\"messages\":[],\"command\":\"Me_1=0.08\",\"acomponent\":true,\"ascalar\":false},{\"resultComponent\":{\"id\":42,\"type\":\"NUMBER\",\"name\":\"ME_1\",\"domainValue\":\"num_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},\"messages\":[],\"command\":\"Me_1\",\"acomponent\":true,\"ascalar\":false},{\"result\":{\"id\":144,\"name\":\"temporary_143\",\"description\":null,\"attributes\":[],\"persistent\":false,\"measures\":[{\"id\":362,\"type\":\"NUMBER\",\"name\":\"num_var\",\"domainValue\":\"num_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"identifiers\":[],\"viral\":[]},\"resultComponent\":{\"id\":362,\"type\":\"NUMBER\",\"name\":\"num_var\",\"domainValue\":\"num_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},\"messages\":[],\"command\":\"0.08\",\"scalarComponent\":{\"id\":362,\"type\":\"NUMBER\",\"name\":\"num_var\",\"domainValue\":\"num_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},\"acomponent\":false,\"ascalar\":true}]";
		List<ResultExpression> result = vtlTestUtils.getResult(commandStatements);
		List<ResultExpression> resultExpressionsToComapare = vtlTestUtils.convertJsonToResultExpression(jsonResult);
		assertNotNull("Conversione non riuscita", resultExpressionsToComapare);
		assertNotNull("Validazione Semantica non riuscita", result);
		assertTrue("I due risultati non sono uguali",
				vtlTestUtils.compareResult(result.get(0), resultExpressionsToComapare.get(0)));

	}

	@Test
	@Transactional
	public void notEqualTo() throws Exception {
		VtlDataset vtlDatasetFind = datasetRepository.findByNameIgnoreCase("DS_1");
		if (vtlDatasetFind == null) {
			VtlDataset vtlDataset = new VtlDataset();

			vtlDataset.setPersistent(true);
			vtlDataset.setName("DS_13");
			vtlDataset.setIsOnlyAScalar(false);
			vtlDataset.setTransitory(false);
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_1", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_2", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_3", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_4", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_1", VtlType.NUMBER, VtlComponentRole.MEASURE));
			datasetRepository.save(vtlDataset);
		}
		String commandStatements = "DS_r := DS_13 <>  3;";
		String jsonResult = "[{\"result\":{\"id\":34,\"name\":\"DS_r\",\"description\":null,\"attributes\":[],\"persistent\":false,\"identifiers\":[{\"id\":1,\"type\":\"STRING\",\"name\":\"ID_1\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":2,\"type\":\"STRING\",\"name\":\"ID_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":3,\"type\":\"STRING\",\"name\":\"ID_3\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":4,\"type\":\"STRING\",\"name\":\"ID_4\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}],\"viral\":[],\"measures\":[{\"id\":123,\"type\":\"BOOLEAN\",\"name\":\"bool_var\",\"domainValue\":\"bool_vd\",\"domainValueParent\":\"bool_vd\",\"vtlComponentRole\":\"MEASURE\"}]},\"messages\":[],\"command\":\"DS_r:=DS_13<>3;\",\"acomponent\":false,\"ascalar\":false},{\"result\":{\"id\":34,\"name\":\"DS_r\",\"description\":null,\"attributes\":[],\"persistent\":false,\"identifiers\":[{\"id\":1,\"type\":\"STRING\",\"name\":\"ID_1\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":2,\"type\":\"STRING\",\"name\":\"ID_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":3,\"type\":\"STRING\",\"name\":\"ID_3\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":4,\"type\":\"STRING\",\"name\":\"ID_4\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}],\"viral\":[],\"measures\":[{\"id\":123,\"type\":\"BOOLEAN\",\"name\":\"bool_var\",\"domainValue\":\"bool_vd\",\"domainValueParent\":\"bool_vd\",\"vtlComponentRole\":\"MEASURE\"}]},\"messages\":[],\"command\":\"DS_13<>3\",\"acomponent\":false,\"ascalar\":false},{\"result\":{\"id\":1,\"name\":\"DS_13\",\"description\":null,\"attributes\":[],\"persistent\":true,\"identifiers\":[{\"id\":1,\"type\":\"STRING\",\"name\":\"ID_1\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":2,\"type\":\"STRING\",\"name\":\"ID_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":3,\"type\":\"STRING\",\"name\":\"ID_3\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":4,\"type\":\"STRING\",\"name\":\"ID_4\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}],\"viral\":[],\"measures\":[{\"id\":5,\"type\":\"NUMBER\",\"name\":\"ME_1\",\"domainValue\":\"num_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"}]},\"messages\":[],\"command\":\"DS_13\",\"acomponent\":false,\"ascalar\":false},{\"result\":{\"id\":30,\"name\":\"temporary_29\",\"description\":null,\"attributes\":[],\"persistent\":false,\"identifiers\":[],\"viral\":[],\"measures\":[{\"id\":121,\"type\":\"INTEGER\",\"name\":\"int_var\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"}]},\"resultComponent\":{\"id\":121,\"type\":\"INTEGER\",\"name\":\"int_var\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},\"messages\":[],\"command\":\"3\",\"acomponent\":false,\"scalarComponent\":{\"id\":121,\"type\":\"INTEGER\",\"name\":\"int_var\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},\"ascalar\":true}]";
		List<ResultExpression> result = vtlTestUtils.getResult(commandStatements);
		List<ResultExpression> resultExpressionsToComapare = vtlTestUtils.convertJsonToResultExpression(jsonResult);
		assertNotNull("Conversione non riuscita", resultExpressionsToComapare);
		assertNotNull("Validazione Semantica non riuscita", result);
		assertTrue("I due risultati non sono uguali",
				vtlTestUtils.compareResult(result.get(0), resultExpressionsToComapare.get(0)));

	}

	@Test
	@Transactional
	public void notEqualToString() throws Exception {

		String commandStatements = "DS_r := \"hello\" <> \"hi\";";
		String jsonResult = "[{\"result\":{\"id\":16,\"name\":\"DS_r\",\"description\":null,\"attributes\":[],\"persistent\":false,\"measures\":[{\"id\":8,\"type\":\"BOOLEAN\",\"name\":\"bool_var\",\"domainValue\":\"bool_vd\",\"domainValueParent\":\"bool_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"viral\":[],\"identifiers\":[]},\"resultComponent\":{\"id\":8,\"type\":\"BOOLEAN\",\"name\":\"bool_var\",\"domainValue\":\"bool_vd\",\"domainValueParent\":\"bool_vd\",\"vtlComponentRole\":\"MEASURE\"},\"messages\":[],\"command\":\"DS_r:=\\\"hello\\\"<>\\\"hi\\\";\",\"scalarComponent\":{\"id\":8,\"type\":\"BOOLEAN\",\"name\":\"bool_var\",\"domainValue\":\"bool_vd\",\"domainValueParent\":\"bool_vd\",\"vtlComponentRole\":\"MEASURE\"},\"acomponent\":false,\"ascalar\":true},{\"result\":{\"id\":16,\"name\":\"DS_r\",\"description\":null,\"attributes\":[],\"persistent\":false,\"measures\":[{\"id\":8,\"type\":\"BOOLEAN\",\"name\":\"bool_var\",\"domainValue\":\"bool_vd\",\"domainValueParent\":\"bool_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"viral\":[],\"identifiers\":[]},\"resultComponent\":{\"id\":8,\"type\":\"BOOLEAN\",\"name\":\"bool_var\",\"domainValue\":\"bool_vd\",\"domainValueParent\":\"bool_vd\",\"vtlComponentRole\":\"MEASURE\"},\"messages\":[],\"command\":\"\\\"hello\\\"<>\\\"hi\\\"\",\"scalarComponent\":{\"id\":8,\"type\":\"BOOLEAN\",\"name\":\"bool_var\",\"domainValue\":\"bool_vd\",\"domainValueParent\":\"bool_vd\",\"vtlComponentRole\":\"MEASURE\"},\"acomponent\":false,\"ascalar\":true},{\"result\":{\"id\":10,\"name\":\"temporary_9\",\"description\":null,\"attributes\":[],\"persistent\":false,\"measures\":[{\"id\":5,\"type\":\"STRING\",\"name\":\"string_var\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"viral\":[],\"identifiers\":[]},\"resultComponent\":{\"id\":5,\"type\":\"STRING\",\"name\":\"string_var\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"MEASURE\"},\"messages\":[],\"command\":\"\\\"hello\\\"\",\"scalarComponent\":{\"id\":5,\"type\":\"STRING\",\"name\":\"string_var\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"MEASURE\"},\"acomponent\":false,\"ascalar\":true},{\"result\":{\"id\":12,\"name\":\"temporary_11\",\"description\":null,\"attributes\":[],\"persistent\":false,\"measures\":[{\"id\":6,\"type\":\"STRING\",\"name\":\"string_var\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"viral\":[],\"identifiers\":[]},\"resultComponent\":{\"id\":6,\"type\":\"STRING\",\"name\":\"string_var\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"MEASURE\"},\"messages\":[],\"command\":\"\\\"hi\\\"\",\"scalarComponent\":{\"id\":6,\"type\":\"STRING\",\"name\":\"string_var\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"MEASURE\"},\"acomponent\":false,\"ascalar\":true}]";
		List<ResultExpression> result = vtlTestUtils.getResult(commandStatements);
		List<ResultExpression> resultExpressionsToComapare = vtlTestUtils.convertJsonToResultExpression(jsonResult);
		assertNotNull("Conversione non riuscita", resultExpressionsToComapare);
		assertNotNull("Validazione Semantica non riuscita", result);
		assertTrue("I due risultati non sono uguali",
				vtlTestUtils.compareResult(result.get(0), resultExpressionsToComapare.get(0)));
	}

	@Test
	@Transactional
	public void notEqualToDs() throws Exception {
		VtlDataset vtlDatasetFind = datasetRepository.findByNameIgnoreCase("DS_1");
		if (vtlDatasetFind == null) {
			VtlDataset vtlDataset = new VtlDataset();

			vtlDataset.setPersistent(true);
			vtlDataset.setName("DS_13");
			vtlDataset.setIsOnlyAScalar(false);
			vtlDataset.setTransitory(false);
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_1", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_2", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_3", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_4", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_1", VtlType.NUMBER, VtlComponentRole.MEASURE));
			datasetRepository.save(vtlDataset);

			VtlDataset vtlDatasetSecond = new VtlDataset();
			vtlDatasetSecond.setPersistent(true);
			vtlDatasetSecond.setName("DS_14");
			vtlDatasetSecond.setIsOnlyAScalar(false);
			vtlDatasetSecond.setTransitory(false);
			vtlDatasetSecond
					.addComponent(vtlTestUtils.getVtlComponent("ID_1", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDatasetSecond
					.addComponent(vtlTestUtils.getVtlComponent("ID_2", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDatasetSecond
					.addComponent(vtlTestUtils.getVtlComponent("ID_3", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDatasetSecond
					.addComponent(vtlTestUtils.getVtlComponent("ID_4", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDatasetSecond
					.addComponent(vtlTestUtils.getVtlComponent("ME_1", VtlType.NUMBER, VtlComponentRole.MEASURE));
			datasetRepository.save(vtlDatasetSecond);

		}
		String commandStatements = "DS_r := DS_13 <>  DS_14;";
		String jsonResult = "[{\"result\":{\"id\":38,\"name\":\"DS_r\",\"description\":null,\"attributes\":[],\"persistent\":false,\"identifiers\":[{\"id\":1,\"type\":\"STRING\",\"name\":\"ID_1\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":2,\"type\":\"STRING\",\"name\":\"ID_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":3,\"type\":\"STRING\",\"name\":\"ID_3\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":4,\"type\":\"STRING\",\"name\":\"ID_4\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}],\"viral\":[],\"measures\":[{\"id\":125,\"type\":\"BOOLEAN\",\"name\":\"bool_var\",\"domainValue\":\"bool_vd\",\"domainValueParent\":\"bool_vd\",\"vtlComponentRole\":\"MEASURE\"}]},\"messages\":[],\"command\":\"DS_r:=DS_13<>DS_14;\",\"acomponent\":false,\"ascalar\":false},{\"result\":{\"id\":38,\"name\":\"DS_r\",\"description\":null,\"attributes\":[],\"persistent\":false,\"identifiers\":[{\"id\":1,\"type\":\"STRING\",\"name\":\"ID_1\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":2,\"type\":\"STRING\",\"name\":\"ID_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":3,\"type\":\"STRING\",\"name\":\"ID_3\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":4,\"type\":\"STRING\",\"name\":\"ID_4\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}],\"viral\":[],\"measures\":[{\"id\":125,\"type\":\"BOOLEAN\",\"name\":\"bool_var\",\"domainValue\":\"bool_vd\",\"domainValueParent\":\"bool_vd\",\"vtlComponentRole\":\"MEASURE\"}]},\"messages\":[],\"command\":\"DS_13<>DS_14\",\"acomponent\":false,\"ascalar\":false},{\"result\":{\"id\":1,\"name\":\"DS_13\",\"description\":null,\"attributes\":[],\"persistent\":true,\"identifiers\":[{\"id\":1,\"type\":\"STRING\",\"name\":\"ID_1\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":2,\"type\":\"STRING\",\"name\":\"ID_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":3,\"type\":\"STRING\",\"name\":\"ID_3\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":4,\"type\":\"STRING\",\"name\":\"ID_4\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}],\"viral\":[],\"measures\":[{\"id\":5,\"type\":\"NUMBER\",\"name\":\"ME_1\",\"domainValue\":\"num_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"}]},\"messages\":[],\"command\":\"DS_13\",\"acomponent\":false,\"ascalar\":false},{\"result\":{\"id\":2,\"name\":\"DS_14\",\"description\":null,\"attributes\":[],\"persistent\":true,\"identifiers\":[{\"id\":6,\"type\":\"STRING\",\"name\":\"ID_1\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":7,\"type\":\"STRING\",\"name\":\"ID_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":8,\"type\":\"STRING\",\"name\":\"ID_3\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":9,\"type\":\"STRING\",\"name\":\"ID_4\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}],\"viral\":[],\"measures\":[{\"id\":10,\"type\":\"NUMBER\",\"name\":\"ME_1\",\"domainValue\":\"num_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"}]},\"messages\":[],\"command\":\"DS_14\",\"acomponent\":false,\"ascalar\":false}]";
		List<ResultExpression> result = vtlTestUtils.getResult(commandStatements);
		List<ResultExpression> resultExpressionsToComapare = vtlTestUtils.convertJsonToResultExpression(jsonResult);
		assertNotNull("Conversione non riuscita", resultExpressionsToComapare);
		assertNotNull("Validazione Semantica non riuscita", result);
		assertTrue("I due risultati non sono uguali",
				vtlTestUtils.compareResult(result.get(0), resultExpressionsToComapare.get(0)));

	}

	@Test
	@Transactional
	public void notEqualToComponent() throws Exception {
		VtlDataset vtlDatasetFind = datasetRepository.findByNameIgnoreCase("DS_1");
		if (vtlDatasetFind == null) {
			VtlDataset vtlDataset = new VtlDataset();

			vtlDataset.setPersistent(true);
			vtlDataset.setName("DS_13");
			vtlDataset.setIsOnlyAScalar(false);
			vtlDataset.setTransitory(false);
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_1", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_2", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_3", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_4", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_1", VtlType.NUMBER, VtlComponentRole.MEASURE));
			datasetRepository.save(vtlDataset);
		}
		String commandStatements = "DS_r := DS_13 [ calc Me_2 :=  Me_1<>7.5 ];";
		String jsonResult = "[{\"result\":{\"id\":48,\"name\":\"DS_r\",\"description\":null,\"attributes\":[],\"persistent\":false,\"identifiers\":[{\"id\":177,\"type\":\"STRING\",\"name\":\"ID_1\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":178,\"type\":\"STRING\",\"name\":\"ID_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":179,\"type\":\"STRING\",\"name\":\"ID_3\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":180,\"type\":\"STRING\",\"name\":\"ID_4\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}],\"measures\":[{\"id\":181,\"type\":\"NUMBER\",\"name\":\"ME_1\",\"domainValue\":\"num_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},{\"id\":182,\"type\":\"BOOLEAN\",\"name\":\"Me_2\",\"domainValue\":\"bool_vd\",\"domainValueParent\":\"bool_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"viral\":[]},\"messages\":[],\"command\":\"DS_r:=DS_13[calcMe_2:=Me_1<>7.5];\",\"acomponent\":false,\"ascalar\":false},{\"result\":{\"id\":46,\"name\":\"temporary_45\",\"description\":null,\"attributes\":[],\"persistent\":false,\"identifiers\":[{\"id\":171,\"type\":\"STRING\",\"name\":\"ID_1\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":172,\"type\":\"STRING\",\"name\":\"ID_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":173,\"type\":\"STRING\",\"name\":\"ID_3\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":174,\"type\":\"STRING\",\"name\":\"ID_4\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}],\"measures\":[{\"id\":175,\"type\":\"NUMBER\",\"name\":\"ME_1\",\"domainValue\":\"num_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},{\"id\":176,\"type\":\"BOOLEAN\",\"name\":\"Me_2\",\"domainValue\":\"bool_vd\",\"domainValueParent\":\"bool_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"viral\":[]},\"messages\":[],\"command\":\"calcMe_2:=Me_1<>7.5\",\"acomponent\":false,\"ascalar\":false},{\"resultComponent\":{\"id\":null,\"type\":\"BOOLEAN\",\"name\":\"Me_2\",\"domainValue\":\"bool_vd\",\"domainValueParent\":\"bool_vd\",\"vtlComponentRole\":\"MEASURE\"},\"messages\":[],\"command\":\"Me_2:=Me_1<>7.5\",\"acomponent\":true,\"ascalar\":false},{\"resultComponent\":{\"id\":null,\"type\":\"BOOLEAN\",\"name\":\"bool_var\",\"domainValue\":\"bool_vd\",\"domainValueParent\":\"bool_vd\",\"vtlComponentRole\":\"MEASURE\"},\"messages\":[],\"command\":\"Me_1<>7.5\",\"acomponent\":true,\"ascalar\":false},{\"resultComponent\":{\"id\":50,\"type\":\"NUMBER\",\"name\":\"ME_1\",\"domainValue\":\"num_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},\"messages\":[],\"command\":\"Me_1\",\"acomponent\":true,\"ascalar\":false},{\"result\":{\"id\":44,\"name\":\"temporary_43\",\"description\":null,\"attributes\":[],\"persistent\":false,\"identifiers\":[],\"measures\":[{\"id\":170,\"type\":\"NUMBER\",\"name\":\"num_var\",\"domainValue\":\"num_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"viral\":[]},\"resultComponent\":{\"id\":170,\"type\":\"NUMBER\",\"name\":\"num_var\",\"domainValue\":\"num_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},\"messages\":[],\"command\":\"7.5\",\"scalarComponent\":{\"id\":170,\"type\":\"NUMBER\",\"name\":\"num_var\",\"domainValue\":\"num_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},\"acomponent\":false,\"ascalar\":true}]";
		List<ResultExpression> result = vtlTestUtils.getResult(commandStatements);
		List<ResultExpression> resultExpressionsToComapare = vtlTestUtils.convertJsonToResultExpression(jsonResult);
		assertNotNull("Conversione non riuscita", resultExpressionsToComapare);
		assertNotNull("Validazione Semantica non riuscita", result);
		assertTrue("I due risultati non sono uguali",
				vtlTestUtils.compareResult(result.get(0), resultExpressionsToComapare.get(0)));

	}

	@Test
	@Transactional
	public void between() throws Exception {
		VtlDataset vtlDatasetFind = datasetRepository.findByNameIgnoreCase("DS_1");
		if (vtlDatasetFind == null) {

			VtlDataset vtlDataset = new VtlDataset();

			vtlDataset.setPersistent(true);
			vtlDataset.setName("DS_15");
			vtlDataset.setIsOnlyAScalar(false);
			vtlDataset.setTransitory(false);
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_1", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_2", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_3", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_4", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_1", VtlType.INTEGER, VtlComponentRole.MEASURE));
			datasetRepository.save(vtlDataset);
		}
		String commandStatements = "DS_r := between(DS_15, 5,10);";
		String jsonResult = "[{\"result\":{\"id\":34,\"name\":\"DS_r\",\"description\":null,\"attributes\":[],\"persistent\":false,\"measures\":[{\"id\":114,\"type\":\"BOOLEAN\",\"name\":\"bool_var\",\"domainValue\":\"bool_vd\",\"domainValueParent\":\"bool_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"viral\":[],\"identifiers\":[{\"id\":1,\"type\":\"STRING\",\"name\":\"ID_1\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":2,\"type\":\"STRING\",\"name\":\"ID_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":3,\"type\":\"STRING\",\"name\":\"ID_3\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":4,\"type\":\"STRING\",\"name\":\"ID_4\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}]},\"messages\":[],\"command\":\"DS_r:=between(DS_15,5,10);\",\"ascalar\":false,\"acomponent\":false},{\"result\":{\"id\":34,\"name\":\"DS_r\",\"description\":null,\"attributes\":[],\"persistent\":false,\"measures\":[{\"id\":114,\"type\":\"BOOLEAN\",\"name\":\"bool_var\",\"domainValue\":\"bool_vd\",\"domainValueParent\":\"bool_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"viral\":[],\"identifiers\":[{\"id\":1,\"type\":\"STRING\",\"name\":\"ID_1\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":2,\"type\":\"STRING\",\"name\":\"ID_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":3,\"type\":\"STRING\",\"name\":\"ID_3\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":4,\"type\":\"STRING\",\"name\":\"ID_4\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}]},\"messages\":[],\"command\":\"between(DS_15,5,10)\",\"ascalar\":false,\"acomponent\":false}]";
		List<ResultExpression> result = vtlTestUtils.getResult(commandStatements);
		List<ResultExpression> resultExpressionsToComapare = vtlTestUtils.convertJsonToResultExpression(jsonResult);
		assertNotNull("Conversione non riuscita", resultExpressionsToComapare);
		assertNotNull("Validazione Semantica non riuscita", result);
		assertTrue("I due risultati non sono uguali",
				vtlTestUtils.compareResult(result.get(0), resultExpressionsToComapare.get(0)));

	}

	@Test
	@Transactional
	public void in() throws Exception {
		VtlDataset vtlDatasetFind = datasetRepository.findByNameIgnoreCase("DS_1");
		if (vtlDatasetFind == null) {

			VtlDataset vtlDataset = new VtlDataset();

			vtlDataset.setPersistent(true);
			vtlDataset.setName("DS_16");
			vtlDataset.setIsOnlyAScalar(false);
			vtlDataset.setTransitory(false);
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_1", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_2", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_1", VtlType.INTEGER, VtlComponentRole.MEASURE));
			datasetRepository.save(vtlDataset);
		}
		String commandStatements = "DS_r := DS_16 in  { \"BS\", \"MO\", \"HH\", \"PP\" };";
		String jsonResult = "[{\"result\":{\"id\":37,\"name\":\"DS_r\",\"description\":null,\"attributes\":[],\"persistent\":false,\"measures\":[{\"id\":111,\"type\":\"BOOLEAN\",\"name\":\"bool_var\",\"domainValue\":\"bool_vd\",\"domainValueParent\":\"bool_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"viral\":[],\"identifiers\":[{\"id\":1,\"type\":\"INTEGER\",\"name\":\"ID_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":2,\"type\":\"STRING\",\"name\":\"ID_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}]},\"messages\":[],\"command\":\"DS_r:=DS_16in{\\\"BS\\\",\\\"MO\\\",\\\"HH\\\",\\\"PP\\\"};\",\"ascalar\":false,\"acomponent\":false},{\"result\":{\"id\":37,\"name\":\"DS_r\",\"description\":null,\"attributes\":[],\"persistent\":false,\"measures\":[{\"id\":111,\"type\":\"BOOLEAN\",\"name\":\"bool_var\",\"domainValue\":\"bool_vd\",\"domainValueParent\":\"bool_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"viral\":[],\"identifiers\":[{\"id\":1,\"type\":\"INTEGER\",\"name\":\"ID_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":2,\"type\":\"STRING\",\"name\":\"ID_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}]},\"messages\":[],\"command\":\"DS_16in{\\\"BS\\\",\\\"MO\\\",\\\"HH\\\",\\\"PP\\\"}\",\"ascalar\":false,\"acomponent\":false}]";
		List<ResultExpression> result = vtlTestUtils.getResult(commandStatements);
		List<ResultExpression> resultExpressionsToComapare = vtlTestUtils.convertJsonToResultExpression(jsonResult);
		assertNotNull("Conversione non riuscita", resultExpressionsToComapare);
		assertNotNull("Validazione Semantica non riuscita", result);
		assertTrue("I due risultati non sono uguali",
				vtlTestUtils.compareResult(result.get(0), resultExpressionsToComapare.get(0)));

	}

	@Test
	@Transactional
	public void inInt() throws Exception {

		String commandStatements = "DS_r := 1 in { 1, 2, 3 };";
		String jsonResult = "[{\"result\":{\"id\":48,\"name\":\"DS_r\",\"description\":null,\"attributes\":[],\"persistent\":false,\"measures\":[{\"id\":24,\"type\":\"BOOLEAN\",\"name\":\"bool_var\",\"domainValue\":\"bool_vd\",\"domainValueParent\":\"bool_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"viral\":[],\"identifiers\":[]},\"resultComponent\":{\"id\":24,\"type\":\"BOOLEAN\",\"name\":\"bool_var\",\"domainValue\":\"bool_vd\",\"domainValueParent\":\"bool_vd\",\"vtlComponentRole\":\"MEASURE\"},\"messages\":[],\"command\":\"DS_r:=1in{1,2,3};\",\"scalarComponent\":{\"id\":24,\"type\":\"BOOLEAN\",\"name\":\"bool_var\",\"domainValue\":\"bool_vd\",\"domainValueParent\":\"bool_vd\",\"vtlComponentRole\":\"MEASURE\"},\"acomponent\":false,\"ascalar\":true},{\"result\":{\"id\":48,\"name\":\"DS_r\",\"description\":null,\"attributes\":[],\"persistent\":false,\"measures\":[{\"id\":24,\"type\":\"BOOLEAN\",\"name\":\"bool_var\",\"domainValue\":\"bool_vd\",\"domainValueParent\":\"bool_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"viral\":[],\"identifiers\":[]},\"resultComponent\":{\"id\":24,\"type\":\"BOOLEAN\",\"name\":\"bool_var\",\"domainValue\":\"bool_vd\",\"domainValueParent\":\"bool_vd\",\"vtlComponentRole\":\"MEASURE\"},\"messages\":[],\"command\":\"1in{1,2,3}\",\"scalarComponent\":{\"id\":24,\"type\":\"BOOLEAN\",\"name\":\"bool_var\",\"domainValue\":\"bool_vd\",\"domainValueParent\":\"bool_vd\",\"vtlComponentRole\":\"MEASURE\"},\"acomponent\":false,\"ascalar\":true}]";
		List<ResultExpression> result = vtlTestUtils.getResult(commandStatements);
		List<ResultExpression> resultExpressionsToComapare = vtlTestUtils.convertJsonToResultExpression(jsonResult);
		assertNotNull("Conversione non riuscita", resultExpressionsToComapare);
		assertNotNull("Validazione Semantica non riuscita", result);
		assertTrue("I due risultati non sono uguali",
				vtlTestUtils.compareResult(result.get(0), resultExpressionsToComapare.get(0)));
	}

	@Test
	@Transactional
	public void inComponent() throws Exception {
		VtlDataset vtlDatasetFind = datasetRepository.findByNameIgnoreCase("DS_1");
		if (vtlDatasetFind == null) {
			VtlDataset vtlDataset = new VtlDataset();

			vtlDataset.setPersistent(true);
			vtlDataset.setName("DS_16");
			vtlDataset.setIsOnlyAScalar(false);
			vtlDataset.setTransitory(false);
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_1", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_2", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_1", VtlType.INTEGER, VtlComponentRole.MEASURE));
			datasetRepository.save(vtlDataset);
		}
		String commandStatements = "DS_r := DS_16 in { 0, 3, 6, 12 };";
		String jsonResult = "[{\"result\":{\"id\":10,\"name\":\"DS_r\",\"description\":null,\"attributes\":[],\"persistent\":false,\"measures\":[{\"id\":17,\"type\":\"BOOLEAN\",\"name\":\"bool_var\",\"domainValue\":\"bool_vd\",\"domainValueParent\":\"bool_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"identifiers\":[{\"id\":1,\"type\":\"INTEGER\",\"name\":\"ID_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":2,\"type\":\"STRING\",\"name\":\"ID_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}],\"viral\":[]},\"messages\":[],\"command\":\"DS_r:=DS_16in{0,3,6,12};\",\"acomponent\":false,\"ascalar\":false},{\"result\":{\"id\":10,\"name\":\"DS_r\",\"description\":null,\"attributes\":[],\"persistent\":false,\"measures\":[{\"id\":17,\"type\":\"BOOLEAN\",\"name\":\"bool_var\",\"domainValue\":\"bool_vd\",\"domainValueParent\":\"bool_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"identifiers\":[{\"id\":1,\"type\":\"INTEGER\",\"name\":\"ID_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":2,\"type\":\"STRING\",\"name\":\"ID_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}],\"viral\":[]},\"messages\":[],\"command\":\"DS_16in{0,3,6,12}\",\"acomponent\":false,\"ascalar\":false}]";
		List<ResultExpression> result = vtlTestUtils.getResult(commandStatements);
		List<ResultExpression> resultExpressionsToComapare = vtlTestUtils.convertJsonToResultExpression(jsonResult);
		assertNotNull("Conversione non riuscita", resultExpressionsToComapare);
		assertNotNull("Validazione Semantica non riuscita", result);
		assertTrue("I due risultati non sono uguali",
				vtlTestUtils.compareResult(result.get(0), resultExpressionsToComapare.get(0)));

	}

	@Test
	@Transactional
	public void inComponentMe() throws Exception {
		VtlDataset vtlDatasetFind = datasetRepository.findByNameIgnoreCase("DS_1");
		if (vtlDatasetFind == null) {
			VtlDataset vtlDataset = new VtlDataset();

			vtlDataset.setPersistent(true);
			vtlDataset.setName("DS_16");
			vtlDataset.setIsOnlyAScalar(false);
			vtlDataset.setTransitory(false);
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_1", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_2", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_1", VtlType.INTEGER, VtlComponentRole.MEASURE));
			datasetRepository.save(vtlDataset);
		}
		String commandStatements = "DS_r := DS_16 [ calc Me_2:= Me_1 in { 0, 3, 6, 12 } ];";
		String jsonResult = "[{\"result\":{\"id\":54,\"name\":\"DS_r\",\"description\":null,\"attributes\":[],\"persistent\":false,\"identifiers\":[{\"id\":188,\"type\":\"INTEGER\",\"name\":\"ID_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":189,\"type\":\"STRING\",\"name\":\"ID_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}],\"measures\":[{\"id\":190,\"type\":\"INTEGER\",\"name\":\"ME_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},{\"id\":191,\"type\":\"BOOLEAN\",\"name\":\"Me_2\",\"domainValue\":\"bool_vd\",\"domainValueParent\":\"bool_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"viral\":[]},\"messages\":[],\"command\":\"DS_r:=DS_16[calcMe_2:=Me_1in{0,3,6,12}];\",\"acomponent\":false,\"ascalar\":false},{\"result\":{\"id\":52,\"name\":\"temporary_51\",\"description\":null,\"attributes\":[],\"persistent\":false,\"identifiers\":[{\"id\":184,\"type\":\"INTEGER\",\"name\":\"ID_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":185,\"type\":\"STRING\",\"name\":\"ID_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}],\"measures\":[{\"id\":186,\"type\":\"INTEGER\",\"name\":\"ME_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},{\"id\":187,\"type\":\"BOOLEAN\",\"name\":\"Me_2\",\"domainValue\":\"bool_vd\",\"domainValueParent\":\"bool_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"viral\":[]},\"messages\":[],\"command\":\"calcMe_2:=Me_1in{0,3,6,12}\",\"acomponent\":false,\"ascalar\":false},{\"resultComponent\":{\"id\":null,\"type\":\"BOOLEAN\",\"name\":\"Me_2\",\"domainValue\":\"bool_vd\",\"domainValueParent\":\"bool_vd\",\"vtlComponentRole\":\"MEASURE\"},\"messages\":[],\"command\":\"Me_2:=Me_1in{0,3,6,12}\",\"acomponent\":true,\"ascalar\":false},{\"resultComponent\":{\"id\":null,\"type\":\"BOOLEAN\",\"name\":\"bool_var\",\"domainValue\":\"bool_vd\",\"domainValueParent\":\"bool_vd\",\"vtlComponentRole\":\"MEASURE\"},\"messages\":[],\"command\":\"Me_1in{0,3,6,12}\",\"acomponent\":true,\"ascalar\":false}]";
		List<ResultExpression> result = vtlTestUtils.getResult(commandStatements);
		List<ResultExpression> resultExpressionsToComapare = vtlTestUtils.convertJsonToResultExpression(jsonResult);
		assertNotNull("Conversione non riuscita", resultExpressionsToComapare);
		assertNotNull("Validazione Semantica non riuscita", result);
		assertTrue("I due risultati non sono uguali",
				vtlTestUtils.compareResult(result.get(0), resultExpressionsToComapare.get(0)));

	}

	@Test
	@Transactional
	public void notIn() throws Exception {
		VtlDataset vtlDatasetFind = datasetRepository.findByNameIgnoreCase("DS_1");
		if (vtlDatasetFind == null) {
			VtlDataset vtlDataset = new VtlDataset();

			vtlDataset.setPersistent(true);
			vtlDataset.setName("DS_16");
			vtlDataset.setIsOnlyAScalar(false);
			vtlDataset.setTransitory(false);
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_1", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_2", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_1", VtlType.INTEGER, VtlComponentRole.MEASURE));
			datasetRepository.save(vtlDataset);
		}
		String commandStatements = "DS_r := DS_16 not_in  { \"BS\", \"MO\", \"HH\", \"PP\" };";
		String jsonResult = "[{\"result\":{\"id\":42,\"name\":\"DS_r\",\"description\":null,\"attributes\":[],\"persistent\":false,\"identifiers\":[{\"id\":20,\"type\":\"INTEGER\",\"name\":\"ID_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":21,\"type\":\"STRING\",\"name\":\"ID_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}],\"measures\":[{\"id\":83,\"type\":\"BOOLEAN\",\"name\":\"bool_var\",\"domainValue\":\"bool_vd\",\"domainValueParent\":\"bool_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"viral\":[]},\"messages\":[],\"command\":\"DS_r:=DS_16not_in{\\\"BS\\\",\\\"MO\\\",\\\"HH\\\",\\\"PP\\\"};\",\"acomponent\":false,\"ascalar\":false},{\"result\":{\"id\":42,\"name\":\"DS_r\",\"description\":null,\"attributes\":[],\"persistent\":false,\"identifiers\":[{\"id\":20,\"type\":\"INTEGER\",\"name\":\"ID_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":21,\"type\":\"STRING\",\"name\":\"ID_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}],\"measures\":[{\"id\":83,\"type\":\"BOOLEAN\",\"name\":\"bool_var\",\"domainValue\":\"bool_vd\",\"domainValueParent\":\"bool_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"viral\":[]},\"messages\":[],\"command\":\"DS_16not_in{\\\"BS\\\",\\\"MO\\\",\\\"HH\\\",\\\"PP\\\"}\",\"acomponent\":false,\"ascalar\":false}]";
		List<ResultExpression> result = vtlTestUtils.getResult(commandStatements);
		List<ResultExpression> resultExpressionsToComapare = vtlTestUtils.convertJsonToResultExpression(jsonResult);
		assertNotNull("Conversione non riuscita", resultExpressionsToComapare);
		assertNotNull("Validazione Semantica non riuscita", result);
		assertTrue("I due risultati non sono uguali",
				vtlTestUtils.compareResult(result.get(0), resultExpressionsToComapare.get(0)));

	}

	@Test
	@Transactional
	public void matchCharacters() throws Exception {
		VtlDataset vtlDatasetFind = datasetRepository.findByNameIgnoreCase("DS_1");
		if (vtlDatasetFind == null) {
			VtlDataset vtlDataset = new VtlDataset();

			vtlDataset.setPersistent(true);
			vtlDataset.setName("DS_17");
			vtlDataset.setIsOnlyAScalar(false);
			vtlDataset.setTransitory(false);
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_1", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_2", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_3", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_4", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_1", VtlType.STRING, VtlComponentRole.MEASURE));
			datasetRepository.save(vtlDataset);
		}
		String commandStatements = "DS_r := match_characters(DS_17, \"[:alpha:]{2}[:digit:]{3}\");";
		String jsonResult = "[{\"result\":{\"id\":41,\"name\":\"DS_r\",\"description\":null,\"attributes\":[],\"persistent\":false,\"measures\":[{\"id\":113,\"type\":\"BOOLEAN\",\"name\":\"bool_var\",\"domainValue\":\"bool_vd\",\"domainValueParent\":\"bool_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"viral\":[],\"identifiers\":[{\"id\":4,\"type\":\"STRING\",\"name\":\"ID_1\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":5,\"type\":\"STRING\",\"name\":\"ID_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":6,\"type\":\"STRING\",\"name\":\"ID_3\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":7,\"type\":\"STRING\",\"name\":\"ID_4\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}]},\"messages\":[],\"command\":\"DS_r:=match_characters(DS_17,\\\"[:alpha:]{2}[:digit:]{3}\\\");\",\"ascalar\":false,\"acomponent\":false},{\"result\":{\"id\":41,\"name\":\"DS_r\",\"description\":null,\"attributes\":[],\"persistent\":false,\"measures\":[{\"id\":113,\"type\":\"BOOLEAN\",\"name\":\"bool_var\",\"domainValue\":\"bool_vd\",\"domainValueParent\":\"bool_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"viral\":[],\"identifiers\":[{\"id\":4,\"type\":\"STRING\",\"name\":\"ID_1\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":5,\"type\":\"STRING\",\"name\":\"ID_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":6,\"type\":\"STRING\",\"name\":\"ID_3\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":7,\"type\":\"STRING\",\"name\":\"ID_4\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}]},\"messages\":[],\"command\":\"match_characters(DS_17,\\\"[:alpha:]{2}[:digit:]{3}\\\")\",\"ascalar\":false,\"acomponent\":false},{\"result\":{\"id\":2,\"name\":\"DS_17\",\"description\":null,\"attributes\":[],\"persistent\":true,\"measures\":[{\"id\":8,\"type\":\"STRING\",\"name\":\"ME_1\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"viral\":[],\"identifiers\":[{\"id\":4,\"type\":\"STRING\",\"name\":\"ID_1\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":5,\"type\":\"STRING\",\"name\":\"ID_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":6,\"type\":\"STRING\",\"name\":\"ID_3\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":7,\"type\":\"STRING\",\"name\":\"ID_4\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}]},\"messages\":[],\"command\":\"DS_17\",\"ascalar\":false,\"acomponent\":false},{\"result\":{\"id\":39,\"name\":\"temporary_38\",\"description\":null,\"attributes\":[],\"persistent\":false,\"measures\":[{\"id\":112,\"type\":\"STRING\",\"name\":\"string_var\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"viral\":[],\"identifiers\":[]},\"resultComponent\":{\"id\":112,\"type\":\"STRING\",\"name\":\"string_var\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"MEASURE\"},\"messages\":[],\"command\":\"\\\"[:alpha:]{2}[:digit:]{3}\\\"\",\"ascalar\":true,\"scalarComponent\":{\"id\":112,\"type\":\"STRING\",\"name\":\"string_var\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"MEASURE\"},\"acomponent\":false}]";
		List<ResultExpression> result = vtlTestUtils.getResult(commandStatements);
		List<ResultExpression> resultExpressionsToComapare = vtlTestUtils.convertJsonToResultExpression(jsonResult);
		assertNotNull("Conversione non riuscita", resultExpressionsToComapare);
		assertNotNull("Validazione Semantica non riuscita", result);
		assertTrue("I due risultati non sono uguali",
				vtlTestUtils.compareResult(result.get(0), resultExpressionsToComapare.get(0)));

	}

	@Test
	@Transactional
	public void existsIn() throws Exception {
		VtlDataset vtlDatasetFind = datasetRepository.findByNameIgnoreCase("DS_1");
		if (vtlDatasetFind == null) {
			VtlDataset vtlDataset = new VtlDataset();

			vtlDataset.setPersistent(true);
			vtlDataset.setName("DS_18");
			vtlDataset.setIsOnlyAScalar(false);
			vtlDataset.setTransitory(false);
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_1", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_2", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_3", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_4", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_1", VtlType.INTEGER, VtlComponentRole.MEASURE));
			datasetRepository.save(vtlDataset);

			VtlDataset vtlDatasetSecond = new VtlDataset();
			vtlDatasetSecond.setPersistent(true);
			vtlDatasetSecond.setName("DS_12");
			vtlDatasetSecond.setIsOnlyAScalar(false);
			vtlDatasetSecond.setTransitory(false);
			vtlDatasetSecond
					.addComponent(vtlTestUtils.getVtlComponent("ID_1", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDatasetSecond
					.addComponent(vtlTestUtils.getVtlComponent("ID_2", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDatasetSecond
					.addComponent(vtlTestUtils.getVtlComponent("ID_4", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDatasetSecond
					.addComponent(vtlTestUtils.getVtlComponent("ME_1", VtlType.NUMBER, VtlComponentRole.MEASURE));
			datasetRepository.save(vtlDatasetSecond);

		}
		String commandStatements = "DS_r := exists_in (DS_18, DS_12, all);";
		String jsonResult = "[{\"result\":{\"id\":30,\"name\":\"DS_r\",\"description\":null,\"attributes\":[],\"persistent\":false,\"measures\":[{\"id\":111,\"type\":\"BOOLEAN\",\"name\":\"bool_var\",\"domainValue\":\"bool_vd\",\"domainValueParent\":\"bool_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"viral\":[],\"identifiers\":[{\"id\":13,\"type\":\"INTEGER\",\"name\":\"ID_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":14,\"type\":\"STRING\",\"name\":\"ID_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":15,\"type\":\"STRING\",\"name\":\"ID_3\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":16,\"type\":\"STRING\",\"name\":\"ID_4\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}]},\"messages\":[],\"command\":\"DS_r:=exists_in(DS_18,DS_12,all);\",\"ascalar\":false,\"acomponent\":false},{\"result\":{\"id\":30,\"name\":\"DS_r\",\"description\":null,\"attributes\":[],\"persistent\":false,\"measures\":[{\"id\":111,\"type\":\"BOOLEAN\",\"name\":\"bool_var\",\"domainValue\":\"bool_vd\",\"domainValueParent\":\"bool_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"viral\":[],\"identifiers\":[{\"id\":13,\"type\":\"INTEGER\",\"name\":\"ID_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":14,\"type\":\"STRING\",\"name\":\"ID_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":15,\"type\":\"STRING\",\"name\":\"ID_3\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":16,\"type\":\"STRING\",\"name\":\"ID_4\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}]},\"messages\":[],\"command\":\"exists_in(DS_18,DS_12,all)\",\"ascalar\":false,\"acomponent\":false}]";
		List<ResultExpression> result = vtlTestUtils.getResult(commandStatements);
		List<ResultExpression> resultExpressionsToComapare = vtlTestUtils.convertJsonToResultExpression(jsonResult);
		assertNotNull("Conversione non riuscita", resultExpressionsToComapare);
		assertNotNull("Validazione Semantica non riuscita", result);
		assertTrue("I due risultati non sono uguali",
				vtlTestUtils.compareResult(result.get(0), resultExpressionsToComapare.get(0)));

	}

	@Test
	@Transactional
	public void existsInTrue() throws Exception {
		VtlDataset vtlDatasetFind = datasetRepository.findByNameIgnoreCase("DS_1");
		if (vtlDatasetFind == null) {
			VtlDataset vtlDataset = new VtlDataset();

			vtlDataset.setPersistent(true);
			vtlDataset.setName("DS_18");
			vtlDataset.setIsOnlyAScalar(false);
			vtlDataset.setTransitory(false);
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_1", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_2", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_3", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_4", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_1", VtlType.INTEGER, VtlComponentRole.MEASURE));
			datasetRepository.save(vtlDataset);

			VtlDataset vtlDatasetSecond = new VtlDataset();
			vtlDatasetSecond.setPersistent(true);
			vtlDatasetSecond.setName("DS_12");
			vtlDatasetSecond.setIsOnlyAScalar(false);
			vtlDatasetSecond.setTransitory(false);
			vtlDatasetSecond
					.addComponent(vtlTestUtils.getVtlComponent("ID_1", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDatasetSecond
					.addComponent(vtlTestUtils.getVtlComponent("ID_2", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDatasetSecond
					.addComponent(vtlTestUtils.getVtlComponent("ID_4", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDatasetSecond
					.addComponent(vtlTestUtils.getVtlComponent("ME_1", VtlType.NUMBER, VtlComponentRole.MEASURE));
			datasetRepository.save(vtlDatasetSecond);
		}
		String commandStatements = "DS_r := exists_in (DS_18, DS_12, true);";
		String jsonResult = "[{\"result\":{\"id\":24,\"name\":\"DS_r\",\"description\":null,\"attributes\":[],\"persistent\":false,\"identifiers\":[{\"id\":28,\"type\":\"INTEGER\",\"name\":\"ID_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":29,\"type\":\"STRING\",\"name\":\"ID_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":30,\"type\":\"STRING\",\"name\":\"ID_3\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":31,\"type\":\"STRING\",\"name\":\"ID_4\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}],\"measures\":[{\"id\":74,\"type\":\"BOOLEAN\",\"name\":\"bool_var\",\"domainValue\":\"bool_vd\",\"domainValueParent\":\"bool_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"viral\":[]},\"messages\":[],\"command\":\"DS_r:=exists_in(DS_18,DS_12,true);\",\"acomponent\":false,\"ascalar\":false},{\"result\":{\"id\":24,\"name\":\"DS_r\",\"description\":null,\"attributes\":[],\"persistent\":false,\"identifiers\":[{\"id\":28,\"type\":\"INTEGER\",\"name\":\"ID_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":29,\"type\":\"STRING\",\"name\":\"ID_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":30,\"type\":\"STRING\",\"name\":\"ID_3\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":31,\"type\":\"STRING\",\"name\":\"ID_4\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}],\"measures\":[{\"id\":74,\"type\":\"BOOLEAN\",\"name\":\"bool_var\",\"domainValue\":\"bool_vd\",\"domainValueParent\":\"bool_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"viral\":[]},\"messages\":[],\"command\":\"exists_in(DS_18,DS_12,true)\",\"acomponent\":false,\"ascalar\":false}]";
		List<ResultExpression> result = vtlTestUtils.getResult(commandStatements);
		List<ResultExpression> resultExpressionsToComapare = vtlTestUtils.convertJsonToResultExpression(jsonResult);
		assertNotNull("Conversione non riuscita", resultExpressionsToComapare);
		assertNotNull("Validazione Semantica non riuscita", result);
		assertTrue("I due risultati non sono uguali",
				vtlTestUtils.compareResult(result.get(0), resultExpressionsToComapare.get(0)));
	}

	@Test
	@Transactional
	public void existsInFalse() throws Exception {
		VtlDataset vtlDatasetFind = datasetRepository.findByNameIgnoreCase("DS_1");
		if (vtlDatasetFind == null) {
			VtlDataset vtlDataset = new VtlDataset();

			vtlDataset.setPersistent(true);
			vtlDataset.setName("DS_18");
			vtlDataset.setIsOnlyAScalar(false);
			vtlDataset.setTransitory(false);
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_1", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_2", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_3", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_4", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_1", VtlType.INTEGER, VtlComponentRole.MEASURE));
			datasetRepository.save(vtlDataset);

			VtlDataset vtlDatasetSecond = new VtlDataset();
			vtlDatasetSecond.setPersistent(true);
			vtlDatasetSecond.setName("DS_12");
			vtlDatasetSecond.setIsOnlyAScalar(false);
			vtlDatasetSecond.setTransitory(false);
			vtlDatasetSecond
					.addComponent(vtlTestUtils.getVtlComponent("ID_1", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDatasetSecond
					.addComponent(vtlTestUtils.getVtlComponent("ID_2", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDatasetSecond
					.addComponent(vtlTestUtils.getVtlComponent("ID_4", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDatasetSecond
					.addComponent(vtlTestUtils.getVtlComponent("ME_1", VtlType.NUMBER, VtlComponentRole.MEASURE));
			datasetRepository.save(vtlDatasetSecond);
		}
		String commandStatements = "DS_r := exists_in (DS_18, DS_12, false);";
		String jsonResult = "[{\"result\":{\"id\":48,\"name\":\"DS_r\",\"description\":null,\"attributes\":[],\"persistent\":false,\"identifiers\":[{\"id\":181,\"type\":\"INTEGER\",\"name\":\"ID_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":182,\"type\":\"STRING\",\"name\":\"ID_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":183,\"type\":\"STRING\",\"name\":\"ID_3\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":184,\"type\":\"STRING\",\"name\":\"ID_4\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}],\"measures\":[{\"id\":180,\"type\":\"BOOLEAN\",\"name\":\"bool_var\",\"domainValue\":\"bool_vd\",\"domainValueParent\":\"bool_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"viral\":[]},\"messages\":[],\"command\":\"DS_r:=exists_in(DS_18,DS_12,false);\",\"acomponent\":false,\"ascalar\":false},{\"result\":{\"id\":46,\"name\":\"temporary_45\",\"description\":null,\"attributes\":[],\"persistent\":false,\"identifiers\":[{\"id\":176,\"type\":\"INTEGER\",\"name\":\"ID_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":177,\"type\":\"STRING\",\"name\":\"ID_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":178,\"type\":\"STRING\",\"name\":\"ID_3\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":179,\"type\":\"STRING\",\"name\":\"ID_4\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}],\"measures\":[{\"id\":175,\"type\":\"BOOLEAN\",\"name\":\"bool_var\",\"domainValue\":\"bool_vd\",\"domainValueParent\":\"bool_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"viral\":[]},\"messages\":[],\"command\":\"exists_in(DS_18,DS_12,false)\",\"acomponent\":false,\"ascalar\":false}]";
		List<ResultExpression> result = vtlTestUtils.getResult(commandStatements);
		List<ResultExpression> resultExpressionsToComapare = vtlTestUtils.convertJsonToResultExpression(jsonResult);
		assertNotNull("Conversione non riuscita", resultExpressionsToComapare);
		assertNotNull("Validazione Semantica non riuscita", result);
		assertTrue("I due risultati non sono uguali",
				vtlTestUtils.compareResult(result.get(0), resultExpressionsToComapare.get(0)));
	}

	@Test
	@Transactional
	public void isNull() throws Exception {
		VtlDataset vtlDatasetFind = datasetRepository.findByNameIgnoreCase("DS_1");
		if (vtlDatasetFind == null) {
			VtlDataset vtlDataset = new VtlDataset();

			vtlDataset.setPersistent(true);
			vtlDataset.setName("DS_18");
			vtlDataset.setIsOnlyAScalar(false);
			vtlDataset.setTransitory(false);
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_1", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_2", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_3", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_4", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_1", VtlType.INTEGER, VtlComponentRole.MEASURE));
			datasetRepository.save(vtlDataset);

		}
		String commandStatements = "DS_r := isnull(DS_18);";
		String jsonResult = "[{\"result\":{\"id\":27,\"name\":\"DS_r\",\"description\":null,\"attributes\":[],\"persistent\":false,\"measures\":[{\"id\":106,\"type\":\"BOOLEAN\",\"name\":\"bool_var\",\"domainValue\":\"bool_vd\",\"domainValueParent\":\"bool_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"viral\":[],\"identifiers\":[{\"id\":9,\"type\":\"INTEGER\",\"name\":\"ID_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":10,\"type\":\"STRING\",\"name\":\"ID_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":11,\"type\":\"STRING\",\"name\":\"ID_3\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":12,\"type\":\"STRING\",\"name\":\"ID_4\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}]},\"messages\":[],\"command\":\"DS_r:=isnull(DS_18);\",\"ascalar\":false,\"acomponent\":false},{\"result\":{\"id\":27,\"name\":\"DS_r\",\"description\":null,\"attributes\":[],\"persistent\":false,\"measures\":[{\"id\":106,\"type\":\"BOOLEAN\",\"name\":\"bool_var\",\"domainValue\":\"bool_vd\",\"domainValueParent\":\"bool_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"viral\":[],\"identifiers\":[{\"id\":9,\"type\":\"INTEGER\",\"name\":\"ID_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":10,\"type\":\"STRING\",\"name\":\"ID_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":11,\"type\":\"STRING\",\"name\":\"ID_3\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":12,\"type\":\"STRING\",\"name\":\"ID_4\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}]},\"messages\":[],\"ascalar\":false,\"acomponent\":false},{\"result\":{\"id\":3,\"name\":\"DS_18\",\"description\":null,\"attributes\":[],\"persistent\":true,\"measures\":[{\"id\":13,\"type\":\"INTEGER\",\"name\":\"ME_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"viral\":[],\"identifiers\":[{\"id\":9,\"type\":\"INTEGER\",\"name\":\"ID_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":10,\"type\":\"STRING\",\"name\":\"ID_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":11,\"type\":\"STRING\",\"name\":\"ID_3\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":12,\"type\":\"STRING\",\"name\":\"ID_4\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}]},\"messages\":[],\"command\":\"DS_18\",\"ascalar\":false,\"acomponent\":false}]";
		List<ResultExpression> result = vtlTestUtils.getResult(commandStatements);
		List<ResultExpression> resultExpressionsToComapare = vtlTestUtils.convertJsonToResultExpression(jsonResult);
		assertNotNull("Conversione non riuscita", resultExpressionsToComapare);
		assertNotNull("Validazione Semantica non riuscita", result);
		assertTrue("I due risultati non sono uguali",
				vtlTestUtils.compareResult(result.get(0), resultExpressionsToComapare.get(0)));

	}

	@Test
	@Transactional
	public void isNullString() throws Exception {

		String commandStatements = "DS_r := isnull(\"Hello\");";
		String jsonResult = "[{\"result\":{\"id\":52,\"name\":\"DS_r\",\"description\":null,\"attributes\":[],\"persistent\":false,\"measures\":[{\"id\":26,\"type\":\"BOOLEAN\",\"name\":\"bool_var\",\"domainValue\":\"bool_vd\",\"domainValueParent\":\"bool_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"viral\":[],\"identifiers\":[]},\"resultComponent\":{\"id\":26,\"type\":\"BOOLEAN\",\"name\":\"bool_var\",\"domainValue\":\"bool_vd\",\"domainValueParent\":\"bool_vd\",\"vtlComponentRole\":\"MEASURE\"},\"messages\":[],\"command\":\"DS_r:=isnull(\\\"Hello\\\");\",\"scalarComponent\":{\"id\":26,\"type\":\"BOOLEAN\",\"name\":\"bool_var\",\"domainValue\":\"bool_vd\",\"domainValueParent\":\"bool_vd\",\"vtlComponentRole\":\"MEASURE\"},\"acomponent\":false,\"ascalar\":true},{\"result\":{\"id\":52,\"name\":\"DS_r\",\"description\":null,\"attributes\":[],\"persistent\":false,\"measures\":[{\"id\":26,\"type\":\"BOOLEAN\",\"name\":\"bool_var\",\"domainValue\":\"bool_vd\",\"domainValueParent\":\"bool_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"viral\":[],\"identifiers\":[]},\"resultComponent\":{\"id\":26,\"type\":\"BOOLEAN\",\"name\":\"bool_var\",\"domainValue\":\"bool_vd\",\"domainValueParent\":\"bool_vd\",\"vtlComponentRole\":\"MEASURE\"},\"messages\":[],\"scalarComponent\":{\"id\":26,\"type\":\"BOOLEAN\",\"name\":\"bool_var\",\"domainValue\":\"bool_vd\",\"domainValueParent\":\"bool_vd\",\"vtlComponentRole\":\"MEASURE\"},\"acomponent\":false,\"ascalar\":true},{\"result\":{\"id\":50,\"name\":\"temporary_49\",\"description\":null,\"attributes\":[],\"persistent\":false,\"measures\":[{\"id\":25,\"type\":\"STRING\",\"name\":\"string_var\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"viral\":[],\"identifiers\":[]},\"resultComponent\":{\"id\":25,\"type\":\"STRING\",\"name\":\"string_var\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"MEASURE\"},\"messages\":[],\"command\":\"\\\"Hello\\\"\",\"scalarComponent\":{\"id\":25,\"type\":\"STRING\",\"name\":\"string_var\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"MEASURE\"},\"acomponent\":false,\"ascalar\":true}]";
		List<ResultExpression> result = vtlTestUtils.getResult(commandStatements);
		List<ResultExpression> resultExpressionsToComapare = vtlTestUtils.convertJsonToResultExpression(jsonResult);
		assertNotNull("Conversione non riuscita", resultExpressionsToComapare);
		assertNotNull("Validazione Semantica non riuscita", result);
		assertTrue("I due risultati non sono uguali",
				vtlTestUtils.compareResult(result.get(0), resultExpressionsToComapare.get(0)));
	}

	@Test
	@Transactional
	public void isNullComponent() throws Exception {
		VtlDataset vtlDatasetFind = datasetRepository.findByNameIgnoreCase("DS_1");
		if (vtlDatasetFind == null) {
			VtlDataset vtlDataset = new VtlDataset();

			vtlDataset.setPersistent(true);
			vtlDataset.setName("DS_18");
			vtlDataset.setIsOnlyAScalar(false);
			vtlDataset.setTransitory(false);
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_1", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_2", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_3", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_4", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_1", VtlType.INTEGER, VtlComponentRole.MEASURE));
			datasetRepository.save(vtlDataset);

		}
		String commandStatements = "DS_r := DS_18[ calc Me_2 := isnull(Me_1) ];";
		String jsonResult = "[{\"result\":{\"id\":29,\"name\":\"DS_r\",\"description\":null,\"attributes\":[],\"persistent\":false,\"measures\":[{\"id\":13,\"type\":\"INTEGER\",\"name\":\"ME_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},{\"id\":107,\"type\":\"BOOLEAN\",\"name\":\"Me_2\",\"domainValue\":\"bool_vd\",\"domainValueParent\":\"bool_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"viral\":[],\"identifiers\":[{\"id\":9,\"type\":\"INTEGER\",\"name\":\"ID_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":10,\"type\":\"STRING\",\"name\":\"ID_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":11,\"type\":\"STRING\",\"name\":\"ID_3\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":12,\"type\":\"STRING\",\"name\":\"ID_4\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}]},\"messages\":[],\"command\":\"DS_r:=DS_18[calcMe_2:=isnull(Me_1)];\",\"ascalar\":false,\"acomponent\":false},{\"result\":{\"id\":29,\"name\":\"DS_r\",\"description\":null,\"attributes\":[],\"persistent\":false,\"measures\":[{\"id\":13,\"type\":\"INTEGER\",\"name\":\"ME_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},{\"id\":107,\"type\":\"BOOLEAN\",\"name\":\"Me_2\",\"domainValue\":\"bool_vd\",\"domainValueParent\":\"bool_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"viral\":[],\"identifiers\":[{\"id\":9,\"type\":\"INTEGER\",\"name\":\"ID_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":10,\"type\":\"STRING\",\"name\":\"ID_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":11,\"type\":\"STRING\",\"name\":\"ID_3\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":12,\"type\":\"STRING\",\"name\":\"ID_4\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}]},\"messages\":[],\"command\":\"calcMe_2:=isnull(Me_1)\",\"ascalar\":false,\"acomponent\":false},{\"resultComponent\":{\"id\":null,\"type\":\"BOOLEAN\",\"name\":\"Me_2\",\"domainValue\":\"bool_vd\",\"domainValueParent\":\"bool_vd\",\"vtlComponentRole\":\"MEASURE\"},\"messages\":[],\"command\":\"Me_2:=isnull(Me_1)\",\"ascalar\":false,\"acomponent\":true},{\"resultComponent\":{\"id\":null,\"type\":\"BOOLEAN\",\"name\":\"bool_var\",\"domainValue\":\"bool_vd\",\"domainValueParent\":\"bool_vd\",\"vtlComponentRole\":\"MEASURE\"},\"messages\":[],\"ascalar\":false,\"acomponent\":true},{\"resultComponent\":{\"id\":13,\"type\":\"INTEGER\",\"name\":\"ME_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},\"messages\":[],\"command\":\"Me_1\",\"ascalar\":false,\"acomponent\":true}]";
		List<ResultExpression> result = vtlTestUtils.getResult(commandStatements);
		List<ResultExpression> resultExpressionsToComapare = vtlTestUtils.convertJsonToResultExpression(jsonResult);
		assertNotNull("Conversione non riuscita", resultExpressionsToComapare);
		assertNotNull("Validazione Semantica non riuscita", result);
		assertTrue("I due risultati non sono uguali",
				vtlTestUtils.compareResult(result.get(0), resultExpressionsToComapare.get(0)));

	}

	@Test
	@Transactional
	public void greater() throws Exception {
		VtlDataset vtlDatasetFind = datasetRepository.findByNameIgnoreCase("DS_1");
		if (vtlDatasetFind == null) {
			VtlDataset vtlDataset = new VtlDataset();

			vtlDataset.setPersistent(true);
			vtlDataset.setName("DS_37");
			vtlDataset.setIsOnlyAScalar(false);
			vtlDataset.setTransitory(false);
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_1", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_2", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_3", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_4", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_5", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_1", VtlType.NUMBER, VtlComponentRole.MEASURE));
			datasetRepository.save(vtlDataset);
		}
		String commandStatements = "DS_r := DS_37 > 20;";
		String jsonResult = "[{\"result\":{\"id\":12,\"name\":\"DS_r\",\"description\":null,\"attributes\":[],\"persistent\":false,\"measures\":[{\"id\":27,\"type\":\"BOOLEAN\",\"name\":\"bool_var\",\"domainValue\":\"bool_vd\",\"domainValueParent\":\"bool_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"viral\":[],\"identifiers\":[{\"id\":8,\"type\":\"INTEGER\",\"name\":\"ID_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":9,\"type\":\"STRING\",\"name\":\"ID_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":10,\"type\":\"INTEGER\",\"name\":\"ID_3\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":11,\"type\":\"STRING\",\"name\":\"ID_4\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":12,\"type\":\"STRING\",\"name\":\"ID_5\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}]},\"messages\":[],\"command\":\"DS_r:=DS_37>20;\",\"acomponent\":false,\"ascalar\":false},{\"result\":{\"id\":12,\"name\":\"DS_r\",\"description\":null,\"attributes\":[],\"persistent\":false,\"measures\":[{\"id\":27,\"type\":\"BOOLEAN\",\"name\":\"bool_var\",\"domainValue\":\"bool_vd\",\"domainValueParent\":\"bool_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"viral\":[],\"identifiers\":[{\"id\":8,\"type\":\"INTEGER\",\"name\":\"ID_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":9,\"type\":\"STRING\",\"name\":\"ID_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":10,\"type\":\"INTEGER\",\"name\":\"ID_3\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":11,\"type\":\"STRING\",\"name\":\"ID_4\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":12,\"type\":\"STRING\",\"name\":\"ID_5\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}]},\"messages\":[],\"command\":\"DS_37>20\",\"acomponent\":false,\"ascalar\":false},{\"result\":{\"id\":3,\"name\":\"DS_37\",\"description\":null,\"attributes\":[],\"persistent\":true,\"measures\":[{\"id\":13,\"type\":\"NUMBER\",\"name\":\"ME_1\",\"domainValue\":\"num_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"viral\":[],\"identifiers\":[{\"id\":8,\"type\":\"INTEGER\",\"name\":\"ID_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":9,\"type\":\"STRING\",\"name\":\"ID_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":10,\"type\":\"INTEGER\",\"name\":\"ID_3\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":11,\"type\":\"STRING\",\"name\":\"ID_4\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":12,\"type\":\"STRING\",\"name\":\"ID_5\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}]},\"messages\":[],\"command\":\"DS_37\",\"acomponent\":false,\"ascalar\":false},{\"result\":{\"id\":8,\"name\":\"temporary_7\",\"description\":null,\"attributes\":[],\"persistent\":false,\"measures\":[{\"id\":25,\"type\":\"INTEGER\",\"name\":\"int_var\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"viral\":[],\"identifiers\":[]},\"resultComponent\":{\"id\":25,\"type\":\"INTEGER\",\"name\":\"int_var\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},\"messages\":[],\"command\":\"20\",\"acomponent\":false,\"ascalar\":true,\"scalarComponent\":{\"id\":25,\"type\":\"INTEGER\",\"name\":\"int_var\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"}}]";
		List<ResultExpression> result = vtlTestUtils.getResult(commandStatements);
		List<ResultExpression> resultExpressionsToComapare = vtlTestUtils.convertJsonToResultExpression(jsonResult);
		assertNotNull("Conversione non riuscita", resultExpressionsToComapare);
		assertNotNull("Validazione Semantica non riuscita", result);
		assertTrue("I due risultati non sono uguali",
				vtlTestUtils.compareResult(result.get(0), resultExpressionsToComapare.get(0)));

	}

	@Test
	@Transactional
	public void greaterEquals() throws Exception {

		String commandStatements = "DS_r := 5 >= 5;";
		String jsonResult = "[{\"result\":{\"id\":32,\"name\":\"DS_r\",\"description\":null,\"attributes\":[],\"persistent\":false,\"measures\":[{\"id\":16,\"type\":\"BOOLEAN\",\"name\":\"bool_var\",\"domainValue\":\"bool_vd\",\"domainValueParent\":\"bool_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"viral\":[],\"identifiers\":[]},\"resultComponent\":{\"id\":16,\"type\":\"BOOLEAN\",\"name\":\"bool_var\",\"domainValue\":\"bool_vd\",\"domainValueParent\":\"bool_vd\",\"vtlComponentRole\":\"MEASURE\"},\"messages\":[],\"command\":\"DS_r:=5>=5;\",\"scalarComponent\":{\"id\":16,\"type\":\"BOOLEAN\",\"name\":\"bool_var\",\"domainValue\":\"bool_vd\",\"domainValueParent\":\"bool_vd\",\"vtlComponentRole\":\"MEASURE\"},\"acomponent\":false,\"ascalar\":true},{\"result\":{\"id\":32,\"name\":\"DS_r\",\"description\":null,\"attributes\":[],\"persistent\":false,\"measures\":[{\"id\":16,\"type\":\"BOOLEAN\",\"name\":\"bool_var\",\"domainValue\":\"bool_vd\",\"domainValueParent\":\"bool_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"viral\":[],\"identifiers\":[]},\"resultComponent\":{\"id\":16,\"type\":\"BOOLEAN\",\"name\":\"bool_var\",\"domainValue\":\"bool_vd\",\"domainValueParent\":\"bool_vd\",\"vtlComponentRole\":\"MEASURE\"},\"messages\":[],\"command\":\"5>=5\",\"scalarComponent\":{\"id\":16,\"type\":\"BOOLEAN\",\"name\":\"bool_var\",\"domainValue\":\"bool_vd\",\"domainValueParent\":\"bool_vd\",\"vtlComponentRole\":\"MEASURE\"},\"acomponent\":false,\"ascalar\":true},{\"result\":{\"id\":26,\"name\":\"temporary_25\",\"description\":null,\"attributes\":[],\"persistent\":false,\"measures\":[{\"id\":13,\"type\":\"INTEGER\",\"name\":\"int_var\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"viral\":[],\"identifiers\":[]},\"resultComponent\":{\"id\":13,\"type\":\"INTEGER\",\"name\":\"int_var\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},\"messages\":[],\"command\":\"5\",\"scalarComponent\":{\"id\":13,\"type\":\"INTEGER\",\"name\":\"int_var\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},\"acomponent\":false,\"ascalar\":true},{\"result\":{\"id\":28,\"name\":\"temporary_27\",\"description\":null,\"attributes\":[],\"persistent\":false,\"measures\":[{\"id\":14,\"type\":\"INTEGER\",\"name\":\"int_var\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"viral\":[],\"identifiers\":[]},\"resultComponent\":{\"id\":14,\"type\":\"INTEGER\",\"name\":\"int_var\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},\"messages\":[],\"command\":\"5\",\"scalarComponent\":{\"id\":14,\"type\":\"INTEGER\",\"name\":\"int_var\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},\"acomponent\":false,\"ascalar\":true}]";
		List<ResultExpression> result = vtlTestUtils.getResult(commandStatements);
		List<ResultExpression> resultExpressionsToComapare = vtlTestUtils.convertJsonToResultExpression(jsonResult);
		assertNotNull("Conversione non riuscita", resultExpressionsToComapare);
		assertNotNull("Validazione Semantica non riuscita", result);
		assertTrue("I due risultati non sono uguali",
				vtlTestUtils.compareResult(result.get(0), resultExpressionsToComapare.get(0)));
	}

	@Test
	@Transactional
	public void greaterDs() throws Exception {
		VtlDataset vtlDatasetFind = datasetRepository.findByNameIgnoreCase("DS_1");
		if (vtlDatasetFind == null) {
			VtlDataset vtlDataset = new VtlDataset();

			vtlDataset.setPersistent(true);
			vtlDataset.setName("DS_13");
			vtlDataset.setIsOnlyAScalar(false);
			vtlDataset.setTransitory(false);
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_1", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_2", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_3", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_4", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_1", VtlType.NUMBER, VtlComponentRole.MEASURE));
			datasetRepository.save(vtlDataset);

			VtlDataset vtlDatasetSecond = new VtlDataset();
			vtlDatasetSecond.setPersistent(true);
			vtlDatasetSecond.setName("DS_14");
			vtlDatasetSecond.setIsOnlyAScalar(false);
			vtlDatasetSecond.setTransitory(false);
			vtlDatasetSecond
					.addComponent(vtlTestUtils.getVtlComponent("ID_1", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDatasetSecond
					.addComponent(vtlTestUtils.getVtlComponent("ID_2", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDatasetSecond
					.addComponent(vtlTestUtils.getVtlComponent("ID_3", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDatasetSecond
					.addComponent(vtlTestUtils.getVtlComponent("ID_4", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDatasetSecond
					.addComponent(vtlTestUtils.getVtlComponent("ME_1", VtlType.NUMBER, VtlComponentRole.MEASURE));
			datasetRepository.save(vtlDatasetSecond);
		}
		String commandStatements = "DS_r := DS_13 > DS_14;";
		String jsonResult = "[{\"result\":{\"id\":12,\"name\":\"DS_r\",\"description\":null,\"attributes\":[],\"persistent\":false,\"measures\":[{\"id\":36,\"type\":\"BOOLEAN\",\"name\":\"bool_var\",\"domainValue\":\"bool_vd\",\"domainValueParent\":\"bool_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"viral\":[],\"identifiers\":[{\"id\":1,\"type\":\"STRING\",\"name\":\"ID_1\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":2,\"type\":\"STRING\",\"name\":\"ID_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":3,\"type\":\"STRING\",\"name\":\"ID_3\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":4,\"type\":\"STRING\",\"name\":\"ID_4\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}]},\"messages\":[],\"command\":\"DS_r:=DS_13>DS_14;\",\"acomponent\":false,\"ascalar\":false},{\"result\":{\"id\":12,\"name\":\"DS_r\",\"description\":null,\"attributes\":[],\"persistent\":false,\"measures\":[{\"id\":36,\"type\":\"BOOLEAN\",\"name\":\"bool_var\",\"domainValue\":\"bool_vd\",\"domainValueParent\":\"bool_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"viral\":[],\"identifiers\":[{\"id\":1,\"type\":\"STRING\",\"name\":\"ID_1\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":2,\"type\":\"STRING\",\"name\":\"ID_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":3,\"type\":\"STRING\",\"name\":\"ID_3\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":4,\"type\":\"STRING\",\"name\":\"ID_4\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}]},\"messages\":[],\"command\":\"DS_13>DS_14\",\"acomponent\":false,\"ascalar\":false},{\"result\":{\"id\":1,\"name\":\"DS_13\",\"description\":null,\"attributes\":[],\"persistent\":true,\"measures\":[{\"id\":5,\"type\":\"NUMBER\",\"name\":\"ME_1\",\"domainValue\":\"num_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"viral\":[],\"identifiers\":[{\"id\":1,\"type\":\"STRING\",\"name\":\"ID_1\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":2,\"type\":\"STRING\",\"name\":\"ID_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":3,\"type\":\"STRING\",\"name\":\"ID_3\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":4,\"type\":\"STRING\",\"name\":\"ID_4\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}]},\"messages\":[],\"command\":\"DS_13\",\"acomponent\":false,\"ascalar\":false},{\"result\":{\"id\":2,\"name\":\"DS_14\",\"description\":null,\"attributes\":[],\"persistent\":true,\"measures\":[{\"id\":10,\"type\":\"NUMBER\",\"name\":\"ME_1\",\"domainValue\":\"num_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"viral\":[],\"identifiers\":[{\"id\":6,\"type\":\"STRING\",\"name\":\"ID_1\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":7,\"type\":\"STRING\",\"name\":\"ID_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":8,\"type\":\"STRING\",\"name\":\"ID_3\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":9,\"type\":\"STRING\",\"name\":\"ID_4\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}]},\"messages\":[],\"command\":\"DS_14\",\"acomponent\":false,\"ascalar\":false}]";
		List<ResultExpression> result = vtlTestUtils.getResult(commandStatements);
		List<ResultExpression> resultExpressionsToComapare = vtlTestUtils.convertJsonToResultExpression(jsonResult);
		assertNotNull("Conversione non riuscita", resultExpressionsToComapare);
		assertNotNull("Validazione Semantica non riuscita", result);
		assertTrue("I due risultati non sono uguali",
				vtlTestUtils.compareResult(result.get(0), resultExpressionsToComapare.get(0)));

	}

	@Test
	@Transactional
	public void greaterComponent() throws Exception {
		VtlDataset vtlDatasetFind = datasetRepository.findByNameIgnoreCase("DS_1");
		if (vtlDatasetFind == null) {
			VtlDataset vtlDataset = new VtlDataset();

			vtlDataset.setPersistent(true);
			vtlDataset.setName("DS_13");
			vtlDataset.setIsOnlyAScalar(false);
			vtlDataset.setTransitory(false);
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_1", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_2", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_3", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_4", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_1", VtlType.NUMBER, VtlComponentRole.MEASURE));
			datasetRepository.save(vtlDataset);

		}
		String commandStatements = "DS_r := DS_13 [ calc Me_2 := Me_1 > 20 ];";
		String jsonResult = "[{\"result\":{\"id\":128,\"name\":\"DS_r\",\"description\":null,\"attributes\":[],\"persistent\":false,\"viral\":[],\"measures\":[{\"id\":352,\"type\":\"NUMBER\",\"name\":\"ME_1\",\"domainValue\":\"num_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},{\"id\":353,\"type\":\"BOOLEAN\",\"name\":\"Me_2\",\"domainValue\":\"bool_vd\",\"domainValueParent\":\"bool_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"identifiers\":[{\"id\":348,\"type\":\"STRING\",\"name\":\"ID_1\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":349,\"type\":\"STRING\",\"name\":\"ID_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":350,\"type\":\"STRING\",\"name\":\"ID_3\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":351,\"type\":\"STRING\",\"name\":\"ID_4\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}]},\"messages\":[],\"command\":\"DS_r:=DS_13[calcMe_2:=Me_1>20];\",\"acomponent\":false,\"ascalar\":false},{\"result\":{\"id\":126,\"name\":\"temporary_125\",\"description\":null,\"attributes\":[],\"persistent\":false,\"viral\":[],\"measures\":[{\"id\":346,\"type\":\"NUMBER\",\"name\":\"ME_1\",\"domainValue\":\"num_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},{\"id\":347,\"type\":\"BOOLEAN\",\"name\":\"Me_2\",\"domainValue\":\"bool_vd\",\"domainValueParent\":\"bool_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"identifiers\":[{\"id\":342,\"type\":\"STRING\",\"name\":\"ID_1\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":343,\"type\":\"STRING\",\"name\":\"ID_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":344,\"type\":\"STRING\",\"name\":\"ID_3\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":345,\"type\":\"STRING\",\"name\":\"ID_4\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}]},\"messages\":[],\"command\":\"calcMe_2:=Me_1>20\",\"acomponent\":false,\"ascalar\":false},{\"resultComponent\":{\"id\":null,\"type\":\"BOOLEAN\",\"name\":\"Me_2\",\"domainValue\":\"bool_vd\",\"domainValueParent\":\"bool_vd\",\"vtlComponentRole\":\"MEASURE\"},\"messages\":[],\"command\":\"Me_2:=Me_1>20\",\"acomponent\":true,\"ascalar\":false},{\"resultComponent\":{\"id\":null,\"type\":\"BOOLEAN\",\"name\":\"bool_var\",\"domainValue\":\"bool_vd\",\"domainValueParent\":\"bool_vd\",\"vtlComponentRole\":\"MEASURE\"},\"messages\":[],\"command\":\"Me_1>20\",\"acomponent\":true,\"ascalar\":false},{\"resultComponent\":{\"id\":52,\"type\":\"NUMBER\",\"name\":\"ME_1\",\"domainValue\":\"num_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},\"messages\":[],\"command\":\"Me_1\",\"acomponent\":true,\"ascalar\":false},{\"result\":{\"id\":124,\"name\":\"temporary_123\",\"description\":null,\"attributes\":[],\"persistent\":false,\"viral\":[],\"measures\":[{\"id\":341,\"type\":\"INTEGER\",\"name\":\"int_var\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"identifiers\":[]},\"resultComponent\":{\"id\":341,\"type\":\"INTEGER\",\"name\":\"int_var\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},\"messages\":[],\"command\":\"20\",\"acomponent\":false,\"ascalar\":true,\"scalarComponent\":{\"id\":341,\"type\":\"INTEGER\",\"name\":\"int_var\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"}}]";
		List<ResultExpression> result = vtlTestUtils.getResult(commandStatements);
		List<ResultExpression> resultExpressionsToComapare = vtlTestUtils.convertJsonToResultExpression(jsonResult);
		assertNotNull("Conversione non riuscita", resultExpressionsToComapare);
		assertNotNull("Validazione Semantica non riuscita", result);
		assertTrue("I due risultati non sono uguali",
				vtlTestUtils.compareResult(result.get(0), resultExpressionsToComapare.get(0)));

	}

	@Test
	@Transactional
	public void less() throws Exception {
		VtlDataset vtlDatasetFind = datasetRepository.findByNameIgnoreCase("DS_1");
		if (vtlDatasetFind == null) {

			VtlDataset vtlDataset = new VtlDataset();

			vtlDataset.setPersistent(true);
			vtlDataset.setName("DS_25");
			vtlDataset.setIsOnlyAScalar(false);
			vtlDataset.setTransitory(false);
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_1", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_2", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_3", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_4", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_1", VtlType.INTEGER, VtlComponentRole.MEASURE));
			datasetRepository.save(vtlDataset);
		}
		String commandStatements = "DS_r := DS_25 < 15000000;";
		String jsonResult = "[{\"result\":{\"id\":10,\"name\":\"DS_r\",\"description\":null,\"attributes\":[],\"persistent\":false,\"viral\":[],\"identifiers\":[{\"id\":1,\"type\":\"INTEGER\",\"name\":\"ID_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":2,\"type\":\"STRING\",\"name\":\"ID_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":3,\"type\":\"STRING\",\"name\":\"ID_3\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":4,\"type\":\"STRING\",\"name\":\"ID_4\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}],\"measures\":[{\"id\":19,\"type\":\"BOOLEAN\",\"name\":\"bool_var\",\"domainValue\":\"bool_vd\",\"domainValueParent\":\"bool_vd\",\"vtlComponentRole\":\"MEASURE\"}]},\"messages\":[],\"command\":\"DS_r:=DS_25<15000000;\",\"ascalar\":false,\"acomponent\":false},{\"result\":{\"id\":10,\"name\":\"DS_r\",\"description\":null,\"attributes\":[],\"persistent\":false,\"viral\":[],\"identifiers\":[{\"id\":1,\"type\":\"INTEGER\",\"name\":\"ID_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":2,\"type\":\"STRING\",\"name\":\"ID_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":3,\"type\":\"STRING\",\"name\":\"ID_3\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":4,\"type\":\"STRING\",\"name\":\"ID_4\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}],\"measures\":[{\"id\":19,\"type\":\"BOOLEAN\",\"name\":\"bool_var\",\"domainValue\":\"bool_vd\",\"domainValueParent\":\"bool_vd\",\"vtlComponentRole\":\"MEASURE\"}]},\"messages\":[],\"command\":\"DS_25<15000000\",\"ascalar\":false,\"acomponent\":false},{\"result\":{\"id\":1,\"name\":\"DS_25\",\"description\":null,\"attributes\":[],\"persistent\":true,\"viral\":[],\"identifiers\":[{\"id\":1,\"type\":\"INTEGER\",\"name\":\"ID_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":2,\"type\":\"STRING\",\"name\":\"ID_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":3,\"type\":\"STRING\",\"name\":\"ID_3\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":4,\"type\":\"STRING\",\"name\":\"ID_4\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}],\"measures\":[{\"id\":5,\"type\":\"INTEGER\",\"name\":\"ME_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"}]},\"messages\":[],\"command\":\"DS_25\",\"ascalar\":false,\"acomponent\":false},{\"result\":{\"id\":6,\"name\":\"temporary_5\",\"description\":null,\"attributes\":[],\"persistent\":false,\"viral\":[],\"identifiers\":[],\"measures\":[{\"id\":17,\"type\":\"INTEGER\",\"name\":\"int_var\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"}]},\"resultComponent\":{\"id\":17,\"type\":\"INTEGER\",\"name\":\"int_var\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},\"messages\":[],\"command\":\"15000000\",\"ascalar\":true,\"scalarComponent\":{\"id\":17,\"type\":\"INTEGER\",\"name\":\"int_var\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},\"acomponent\":false}]";
		List<ResultExpression> result = vtlTestUtils.getResult(commandStatements);
		List<ResultExpression> resultExpressionsToComapare = vtlTestUtils.convertJsonToResultExpression(jsonResult);
		assertNotNull("Conversione non riuscita", resultExpressionsToComapare);
		assertNotNull("Validazione Semantica non riuscita", result);
		assertTrue("I due risultati non sono uguali",
				vtlTestUtils.compareResult(result.get(0), resultExpressionsToComapare.get(0)));

	}

	@Test
	@Transactional
	public void lessInt() throws Exception {

		String commandStatements = "DS_r := 5 < 4;";
		String jsonResult = "[{\"result\":{\"id\":40,\"name\":\"DS_r\",\"description\":null,\"attributes\":[],\"persistent\":false,\"measures\":[{\"id\":20,\"type\":\"BOOLEAN\",\"name\":\"bool_var\",\"domainValue\":\"bool_vd\",\"domainValueParent\":\"bool_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"viral\":[],\"identifiers\":[]},\"resultComponent\":{\"id\":20,\"type\":\"BOOLEAN\",\"name\":\"bool_var\",\"domainValue\":\"bool_vd\",\"domainValueParent\":\"bool_vd\",\"vtlComponentRole\":\"MEASURE\"},\"messages\":[],\"command\":\"DS_r:=5<4;\",\"scalarComponent\":{\"id\":20,\"type\":\"BOOLEAN\",\"name\":\"bool_var\",\"domainValue\":\"bool_vd\",\"domainValueParent\":\"bool_vd\",\"vtlComponentRole\":\"MEASURE\"},\"acomponent\":false,\"ascalar\":true},{\"result\":{\"id\":40,\"name\":\"DS_r\",\"description\":null,\"attributes\":[],\"persistent\":false,\"measures\":[{\"id\":20,\"type\":\"BOOLEAN\",\"name\":\"bool_var\",\"domainValue\":\"bool_vd\",\"domainValueParent\":\"bool_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"viral\":[],\"identifiers\":[]},\"resultComponent\":{\"id\":20,\"type\":\"BOOLEAN\",\"name\":\"bool_var\",\"domainValue\":\"bool_vd\",\"domainValueParent\":\"bool_vd\",\"vtlComponentRole\":\"MEASURE\"},\"messages\":[],\"command\":\"5<4\",\"scalarComponent\":{\"id\":20,\"type\":\"BOOLEAN\",\"name\":\"bool_var\",\"domainValue\":\"bool_vd\",\"domainValueParent\":\"bool_vd\",\"vtlComponentRole\":\"MEASURE\"},\"acomponent\":false,\"ascalar\":true},{\"result\":{\"id\":34,\"name\":\"temporary_33\",\"description\":null,\"attributes\":[],\"persistent\":false,\"measures\":[{\"id\":17,\"type\":\"INTEGER\",\"name\":\"int_var\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"viral\":[],\"identifiers\":[]},\"resultComponent\":{\"id\":17,\"type\":\"INTEGER\",\"name\":\"int_var\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},\"messages\":[],\"command\":\"5\",\"scalarComponent\":{\"id\":17,\"type\":\"INTEGER\",\"name\":\"int_var\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},\"acomponent\":false,\"ascalar\":true},{\"result\":{\"id\":36,\"name\":\"temporary_35\",\"description\":null,\"attributes\":[],\"persistent\":false,\"measures\":[{\"id\":18,\"type\":\"INTEGER\",\"name\":\"int_var\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"viral\":[],\"identifiers\":[]},\"resultComponent\":{\"id\":18,\"type\":\"INTEGER\",\"name\":\"int_var\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},\"messages\":[],\"command\":\"4\",\"scalarComponent\":{\"id\":18,\"type\":\"INTEGER\",\"name\":\"int_var\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},\"acomponent\":false,\"ascalar\":true}]";
		List<ResultExpression> result = vtlTestUtils.getResult(commandStatements);
		List<ResultExpression> resultExpressionsToComapare = vtlTestUtils.convertJsonToResultExpression(jsonResult);
		assertNotNull("Conversione non riuscita", resultExpressionsToComapare);
		assertNotNull("Validazione Semantica non riuscita", result);
		assertTrue("I due risultati non sono uguali",
				vtlTestUtils.compareResult(result.get(0), resultExpressionsToComapare.get(0)));
	}

	@Test
	@Transactional
	public void inMe() throws Exception {
		VtlDataset vtlDatasetFind = datasetRepository.findByNameIgnoreCase("DS_1");
		if (vtlDatasetFind == null) {
			VtlDataset vtlDataset = new VtlDataset();

			vtlDataset.setPersistent(true);
			vtlDataset.setName("DS_3");
			vtlDataset.setIsOnlyAScalar(false);
			vtlDataset.setTransitory(false);
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_1", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_2", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_1", VtlType.STRING, VtlComponentRole.MEASURE));
			datasetRepository.save(vtlDataset);
		}
		String commandStatements = "DS_r := DS_3 [ calc Me_2:= Me_1 in { \"BS\", \"MO\", \"HH\", \"PP\" }];";
		String jsonResult = "[{\"result\":{\"id\":7,\"name\":\"DS_r\",\"description\":null,\"attributes\":[],\"persistent\":false,\"measures\":[{\"id\":11,\"type\":\"STRING\",\"name\":\"ME_1\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"MEASURE\"},{\"id\":12,\"type\":\"BOOLEAN\",\"name\":\"Me_2\",\"domainValue\":\"bool_vd\",\"domainValueParent\":\"bool_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"viral\":[],\"identifiers\":[{\"id\":9,\"type\":\"INTEGER\",\"name\":\"ID_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":10,\"type\":\"STRING\",\"name\":\"ID_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}]},\"messages\":[],\"command\":\"DS_r:=DS_3[calcMe_2:=Me_1in{\\\"BS\\\",\\\"MO\\\",\\\"HH\\\",\\\"PP\\\"}];\",\"ascalar\":false,\"acomponent\":false},{\"result\":{\"id\":5,\"name\":\"temporary_4\",\"description\":null,\"attributes\":[],\"persistent\":false,\"measures\":[{\"id\":7,\"type\":\"STRING\",\"name\":\"ME_1\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"MEASURE\"},{\"id\":8,\"type\":\"BOOLEAN\",\"name\":\"Me_2\",\"domainValue\":\"bool_vd\",\"domainValueParent\":\"bool_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"viral\":[],\"identifiers\":[{\"id\":5,\"type\":\"INTEGER\",\"name\":\"ID_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":6,\"type\":\"STRING\",\"name\":\"ID_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}]},\"messages\":[],\"command\":\"calcMe_2:=Me_1in{\\\"BS\\\",\\\"MO\\\",\\\"HH\\\",\\\"PP\\\"}\",\"ascalar\":false,\"acomponent\":false},{\"resultComponent\":{\"id\":null,\"type\":\"BOOLEAN\",\"name\":\"Me_2\",\"domainValue\":\"bool_vd\",\"domainValueParent\":\"bool_vd\",\"vtlComponentRole\":\"MEASURE\"},\"messages\":[],\"command\":\"Me_2:=Me_1in{\\\"BS\\\",\\\"MO\\\",\\\"HH\\\",\\\"PP\\\"}\",\"ascalar\":false,\"acomponent\":true},{\"resultComponent\":{\"id\":null,\"type\":\"BOOLEAN\",\"name\":\"bool_var\",\"domainValue\":\"bool_vd\",\"domainValueParent\":\"bool_vd\",\"vtlComponentRole\":\"MEASURE\"},\"messages\":[],\"command\":\"Me_1in{\\\"BS\\\",\\\"MO\\\",\\\"HH\\\",\\\"PP\\\"}\",\"ascalar\":false,\"acomponent\":true}]";

		List<ResultExpression> result = vtlTestUtils.getResult(commandStatements);

		List<ResultExpression> resultExpressionsToComapare = vtlTestUtils.convertJsonToResultExpression(jsonResult);
		assertNotNull("Conversione non riuscita", resultExpressionsToComapare);
		assertNotNull("Validazione Semantica non riuscita", result);
		assertTrue("I due risultati non sono uguali",
				vtlTestUtils.compareResult(result.get(0), resultExpressionsToComapare.get(0)));

	}

	@Test
	@Transactional
	public void notInMe() throws Exception {
		VtlDataset vtlDatasetFind = datasetRepository.findByNameIgnoreCase("DS_1");
		if (vtlDatasetFind == null) {
			VtlDataset vtlDataset = new VtlDataset();

			vtlDataset.setPersistent(true);
			vtlDataset.setName("DS_3");
			vtlDataset.setIsOnlyAScalar(false);
			vtlDataset.setTransitory(false);
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_1", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_2", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_1", VtlType.STRING, VtlComponentRole.MEASURE));
			datasetRepository.save(vtlDataset);
		}
		String commandStatements = "DS_r := DS_3 [ calc Me_2:= Me_1 not_in { \"BS\", \"MO\", \"HH\", \"PP\" }];";
		String jsonResult = "[{\"result\":{\"id\":7,\"name\":\"DS_r\",\"description\":null,\"attributes\":[],\"persistent\":false,\"measures\":[{\"id\":11,\"type\":\"STRING\",\"name\":\"ME_1\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"MEASURE\"},{\"id\":12,\"type\":\"BOOLEAN\",\"name\":\"Me_2\",\"domainValue\":\"bool_vd\",\"domainValueParent\":\"bool_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"viral\":[],\"identifiers\":[{\"id\":9,\"type\":\"INTEGER\",\"name\":\"ID_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":10,\"type\":\"STRING\",\"name\":\"ID_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}]},\"messages\":[],\"command\":\"DS_r:=DS_3[calcMe_2:=Me_1in{\\\"BS\\\",\\\"MO\\\",\\\"HH\\\",\\\"PP\\\"}];\",\"ascalar\":false,\"acomponent\":false},{\"result\":{\"id\":5,\"name\":\"temporary_4\",\"description\":null,\"attributes\":[],\"persistent\":false,\"measures\":[{\"id\":7,\"type\":\"STRING\",\"name\":\"ME_1\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"MEASURE\"},{\"id\":8,\"type\":\"BOOLEAN\",\"name\":\"Me_2\",\"domainValue\":\"bool_vd\",\"domainValueParent\":\"bool_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"viral\":[],\"identifiers\":[{\"id\":5,\"type\":\"INTEGER\",\"name\":\"ID_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":6,\"type\":\"STRING\",\"name\":\"ID_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}]},\"messages\":[],\"command\":\"calcMe_2:=Me_1in{\\\"BS\\\",\\\"MO\\\",\\\"HH\\\",\\\"PP\\\"}\",\"ascalar\":false,\"acomponent\":false},{\"resultComponent\":{\"id\":null,\"type\":\"BOOLEAN\",\"name\":\"Me_2\",\"domainValue\":\"bool_vd\",\"domainValueParent\":\"bool_vd\",\"vtlComponentRole\":\"MEASURE\"},\"messages\":[],\"command\":\"Me_2:=Me_1in{\\\"BS\\\",\\\"MO\\\",\\\"HH\\\",\\\"PP\\\"}\",\"ascalar\":false,\"acomponent\":true},{\"resultComponent\":{\"id\":null,\"type\":\"BOOLEAN\",\"name\":\"bool_var\",\"domainValue\":\"bool_vd\",\"domainValueParent\":\"bool_vd\",\"vtlComponentRole\":\"MEASURE\"},\"messages\":[],\"command\":\"Me_1in{\\\"BS\\\",\\\"MO\\\",\\\"HH\\\",\\\"PP\\\"}\",\"ascalar\":false,\"acomponent\":true}]";

		List<ResultExpression> result = vtlTestUtils.getResult(commandStatements);

		List<ResultExpression> resultExpressionsToComapare = vtlTestUtils.convertJsonToResultExpression(jsonResult);
		assertNotNull("Conversione non riuscita", resultExpressionsToComapare);
		assertNotNull("Validazione Semantica non riuscita", result);
		assertTrue("I due risultati non sono uguali",
				vtlTestUtils.compareResult(result.get(0), resultExpressionsToComapare.get(0)));

	}
}
