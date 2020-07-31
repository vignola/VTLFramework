package com.capgemini.istat.vtlcompiler.vtlapi.semantic;

import org.junit.Test;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.capgemini.istat.vtlcompiler.vtlcommon.model.dataset.VtlComponentRole;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.dataset.VtlDataset;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.dataset.VtlType;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.semantic.ResultExpression;
import com.capgemini.istat.vtlcompiler.vtlcommon.repository.DatasetRepository;
import com.capgemini.istat.vtlcompiler.vtlapi.utils.VtlTestUtils;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class VtlBooleanOperatorsTest {

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
	public void and() throws Exception {
		VtlDataset vtlDatasetFind = datasetRepository.findByNameIgnoreCase("DS_1");
		if (vtlDatasetFind == null) {
			VtlDataset vtlDataset = new VtlDataset();

			vtlDataset.setPersistent(true);   
			vtlDataset.setName("DS_19");
			vtlDataset.setIsOnlyAScalar(false);
			vtlDataset.setTransitory(false);
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_1", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_2", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_3", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_4", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_1", VtlType.BOOLEAN, VtlComponentRole.MEASURE));
			datasetRepository.save(vtlDataset);

			VtlDataset vtlDatasetSecond = new VtlDataset();
			vtlDatasetSecond.setPersistent(true);
			vtlDatasetSecond.setName("DS_20");
			vtlDatasetSecond.setIsOnlyAScalar(false);
			vtlDatasetSecond.setTransitory(false);
			vtlDatasetSecond
					.addComponent(vtlTestUtils.getVtlComponent("ID_1", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDatasetSecond
					.addComponent(vtlTestUtils.getVtlComponent("ID_2", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDatasetSecond
					.addComponent(vtlTestUtils.getVtlComponent("ID_3", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDatasetSecond
					.addComponent(vtlTestUtils.getVtlComponent("ID_4", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDatasetSecond
					.addComponent(vtlTestUtils.getVtlComponent("ME_1", VtlType.BOOLEAN, VtlComponentRole.MEASURE));
			datasetRepository.save(vtlDatasetSecond);
		}

		String commandStatements = "DS_r := DS_19 and DS_20;";
		String jsonResult = "[{\"result\":{\"id\":46,\"name\":\"DS_r\",\"description\":null,\"attributes\":[],\"persistent\":false,\"identifiers\":[{\"id\":175,\"type\":\"STRING\",\"name\":\"ID_1\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":176,\"type\":\"INTEGER\",\"name\":\"ID_2\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":177,\"type\":\"STRING\",\"name\":\"ID_3\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":178,\"type\":\"INTEGER\",\"name\":\"ID_4\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}],\"viral\":[],\"measures\":[{\"id\":179,\"type\":\"BOOLEAN\",\"name\":\"ME_1\",\"domainValue\":\"bool_vd\",\"domainValueParent\":\"bool_vd\",\"vtlComponentRole\":\"MEASURE\"}]},\"messages\":[],\"command\":\"DS_r:=DS_19andDS_20;\",\"ascalar\":false,\"acomponent\":false},{\"result\":{\"id\":44,\"name\":\"temporary_43\",\"description\":null,\"attributes\":[],\"persistent\":false,\"identifiers\":[{\"id\":170,\"type\":\"STRING\",\"name\":\"ID_1\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":171,\"type\":\"INTEGER\",\"name\":\"ID_2\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":172,\"type\":\"STRING\",\"name\":\"ID_3\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":173,\"type\":\"INTEGER\",\"name\":\"ID_4\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}],\"viral\":[],\"measures\":[{\"id\":174,\"type\":\"BOOLEAN\",\"name\":\"ME_1\",\"domainValue\":\"bool_vd\",\"domainValueParent\":\"bool_vd\",\"vtlComponentRole\":\"MEASURE\"}]},\"messages\":[],\"command\":\"DS_19andDS_20\",\"ascalar\":false,\"acomponent\":false},{\"result\":{\"id\":19,\"name\":\"DS_19\",\"description\":null,\"attributes\":[],\"persistent\":true,\"identifiers\":[{\"id\":74,\"type\":\"STRING\",\"name\":\"ID_1\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":75,\"type\":\"INTEGER\",\"name\":\"ID_2\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":76,\"type\":\"STRING\",\"name\":\"ID_3\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":77,\"type\":\"INTEGER\",\"name\":\"ID_4\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}],\"viral\":[],\"measures\":[{\"id\":78,\"type\":\"BOOLEAN\",\"name\":\"ME_1\",\"domainValue\":\"bool_vd\",\"domainValueParent\":\"bool_vd\",\"vtlComponentRole\":\"MEASURE\"}]},\"messages\":[],\"command\":\"DS_19\",\"ascalar\":false,\"acomponent\":false},{\"result\":{\"id\":20,\"name\":\"DS_20\",\"description\":null,\"attributes\":[],\"persistent\":true,\"identifiers\":[{\"id\":79,\"type\":\"STRING\",\"name\":\"ID_1\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":80,\"type\":\"INTEGER\",\"name\":\"ID_2\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":81,\"type\":\"STRING\",\"name\":\"ID_3\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":82,\"type\":\"INTEGER\",\"name\":\"ID_4\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}],\"viral\":[],\"measures\":[{\"id\":83,\"type\":\"BOOLEAN\",\"name\":\"ME_1\",\"domainValue\":\"bool_vd\",\"domainValueParent\":\"bool_vd\",\"vtlComponentRole\":\"MEASURE\"}]},\"messages\":[],\"command\":\"DS_20\",\"ascalar\":false,\"acomponent\":false}]";
		List<ResultExpression> result = vtlTestUtils.getResult(commandStatements);
		List<ResultExpression> resultExpressionsToComapare = vtlTestUtils.convertJsonToResultExpression(jsonResult);
		assertNotNull("Conversione non riuscita", resultExpressionsToComapare);
		assertNotNull("Validazione Semantica non riuscita", result);
		assertTrue("I due risultati non sono uguali",
				vtlTestUtils.compareResult(result.get(0), resultExpressionsToComapare.get(0)));
	}

	@Test
	@Transactional
	public void andComponent() throws Exception {
		VtlDataset vtlDatasetFind = datasetRepository.findByNameIgnoreCase("DS_1");
		if (vtlDatasetFind == null) {
			VtlDataset vtlDataset = new VtlDataset();

			vtlDataset.setPersistent(true);
			vtlDataset.setName("DS_19");
			vtlDataset.setIsOnlyAScalar(false);
			vtlDataset.setTransitory(false);
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_1", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_2", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_3", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_4", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_1", VtlType.BOOLEAN, VtlComponentRole.MEASURE));
			datasetRepository.save(vtlDataset);

			VtlDataset vtlDatasetSecond = new VtlDataset();
			vtlDatasetSecond.setPersistent(true);
			vtlDatasetSecond.setName("DS_20");
			vtlDatasetSecond.setIsOnlyAScalar(false);
			vtlDatasetSecond.setTransitory(false);
			vtlDatasetSecond
					.addComponent(vtlTestUtils.getVtlComponent("ID_1", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDatasetSecond
					.addComponent(vtlTestUtils.getVtlComponent("ID_2", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDatasetSecond
					.addComponent(vtlTestUtils.getVtlComponent("ID_3", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDatasetSecond
					.addComponent(vtlTestUtils.getVtlComponent("ID_4", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDatasetSecond
					.addComponent(vtlTestUtils.getVtlComponent("ME_1", VtlType.BOOLEAN, VtlComponentRole.MEASURE));
			datasetRepository.save(vtlDatasetSecond);
		}
		String commandStatements = "DS_r := DS_19 [ calc Me_20:= Me_1 and true ];";
		String jsonResult = "[{\"result\":{\"id\":32,\"name\":\"DS_r\",\"description\":null,\"attributes\":[],\"persistent\":false,\"measures\":[{\"id\":5,\"type\":\"BOOLEAN\",\"name\":\"ME_1\",\"domainValue\":\"bool_vd\",\"domainValueParent\":\"bool_vd\",\"vtlComponentRole\":\"MEASURE\"},{\"id\":97,\"type\":\"BOOLEAN\",\"name\":\"Me_20\",\"domainValue\":\"bool_vd\",\"domainValueParent\":\"bool_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"viral\":[],\"identifiers\":[{\"id\":1,\"type\":\"STRING\",\"name\":\"ID_1\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":2,\"type\":\"INTEGER\",\"name\":\"ID_2\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":3,\"type\":\"STRING\",\"name\":\"ID_3\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":4,\"type\":\"INTEGER\",\"name\":\"ID_4\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}]},\"messages\":[],\"command\":\"DS_r:=DS_19[calcMe_20:=Me_1andtrue];\",\"ascalar\":false,\"acomponent\":false},{\"result\":{\"id\":32,\"name\":\"DS_r\",\"description\":null,\"attributes\":[],\"persistent\":false,\"measures\":[{\"id\":5,\"type\":\"BOOLEAN\",\"name\":\"ME_1\",\"domainValue\":\"bool_vd\",\"domainValueParent\":\"bool_vd\",\"vtlComponentRole\":\"MEASURE\"},{\"id\":97,\"type\":\"BOOLEAN\",\"name\":\"Me_20\",\"domainValue\":\"bool_vd\",\"domainValueParent\":\"bool_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"viral\":[],\"identifiers\":[{\"id\":1,\"type\":\"STRING\",\"name\":\"ID_1\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":2,\"type\":\"INTEGER\",\"name\":\"ID_2\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":3,\"type\":\"STRING\",\"name\":\"ID_3\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":4,\"type\":\"INTEGER\",\"name\":\"ID_4\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}]},\"messages\":[],\"command\":\"calcMe_20:=Me_1andtrue\",\"ascalar\":false,\"acomponent\":false},{\"resultComponent\":{\"id\":null,\"type\":\"BOOLEAN\",\"name\":\"Me_20\",\"domainValue\":\"bool_vd\",\"domainValueParent\":\"bool_vd\",\"vtlComponentRole\":\"MEASURE\"},\"messages\":[],\"command\":\"Me_20:=Me_1andtrue\",\"ascalar\":false,\"acomponent\":true},{\"resultComponent\":{\"id\":null,\"type\":\"BOOLEAN\",\"name\":\"ME_1\",\"domainValue\":\"bool_vd\",\"domainValueParent\":\"bool_vd\",\"vtlComponentRole\":\"MEASURE\"},\"messages\":[],\"command\":\"Me_1andtrue\",\"ascalar\":false,\"acomponent\":true},{\"resultComponent\":{\"id\":5,\"type\":\"BOOLEAN\",\"name\":\"ME_1\",\"domainValue\":\"bool_vd\",\"domainValueParent\":\"bool_vd\",\"vtlComponentRole\":\"MEASURE\"},\"messages\":[],\"command\":\"Me_1\",\"ascalar\":false,\"acomponent\":true},{\"result\":{\"id\":30,\"name\":\"temporary_29\",\"description\":null,\"attributes\":[],\"persistent\":false,\"measures\":[{\"id\":96,\"type\":\"BOOLEAN\",\"name\":\"bool_var\",\"domainValue\":\"bool_vd\",\"domainValueParent\":\"bool_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"viral\":[],\"identifiers\":[]},\"resultComponent\":{\"id\":96,\"type\":\"BOOLEAN\",\"name\":\"bool_var\",\"domainValue\":\"bool_vd\",\"domainValueParent\":\"bool_vd\",\"vtlComponentRole\":\"MEASURE\"},\"messages\":[],\"command\":\"true\",\"ascalar\":true,\"acomponent\":false,\"scalarComponent\":{\"id\":96,\"type\":\"BOOLEAN\",\"name\":\"bool_var\",\"domainValue\":\"bool_vd\",\"domainValueParent\":\"bool_vd\",\"vtlComponentRole\":\"MEASURE\"}}]";
		List<ResultExpression> result = vtlTestUtils.getResult(commandStatements);
		List<ResultExpression> resultExpressionsToComapare = vtlTestUtils.convertJsonToResultExpression(jsonResult);
		assertNotNull("Conversione non riuscita", resultExpressionsToComapare);
		assertNotNull("Validazione Semantica non riuscita", result);
		assertTrue("I due risultati non sono uguali",
				vtlTestUtils.compareResult(result.get(0), resultExpressionsToComapare.get(0)));

	}

	@Test
	@Transactional
	public void or() throws Exception {
		VtlDataset vtlDatasetFind = datasetRepository.findByNameIgnoreCase("DS_1");
		if (vtlDatasetFind == null) {
			VtlDataset vtlDataset = new VtlDataset();

			vtlDataset.setPersistent(true);
			vtlDataset.setName("DS_19");
			vtlDataset.setIsOnlyAScalar(false);
			vtlDataset.setTransitory(false);
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_1", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_2", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_3", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_4", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_1", VtlType.BOOLEAN, VtlComponentRole.MEASURE));
			datasetRepository.save(vtlDataset);

			VtlDataset vtlDatasetSecond = new VtlDataset();
			vtlDatasetSecond.setPersistent(true);
			vtlDatasetSecond.setName("DS_20");
			vtlDatasetSecond.setIsOnlyAScalar(false);
			vtlDatasetSecond.setTransitory(false);
			vtlDatasetSecond
					.addComponent(vtlTestUtils.getVtlComponent("ID_1", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDatasetSecond
					.addComponent(vtlTestUtils.getVtlComponent("ID_2", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDatasetSecond
					.addComponent(vtlTestUtils.getVtlComponent("ID_3", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDatasetSecond
					.addComponent(vtlTestUtils.getVtlComponent("ID_4", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDatasetSecond
					.addComponent(vtlTestUtils.getVtlComponent("ME_1", VtlType.BOOLEAN, VtlComponentRole.MEASURE));
			datasetRepository.save(vtlDatasetSecond);
		}
		String commandStatements = "DS_r := DS_19 or DS_20;";
		String jsonResult = "[{\"result\":{\"id\":46,\"name\":\"DS_r\",\"description\":null,\"attributes\":[],\"persistent\":false,\"identifiers\":[{\"id\":175,\"type\":\"STRING\",\"name\":\"ID_1\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":176,\"type\":\"INTEGER\",\"name\":\"ID_2\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":177,\"type\":\"STRING\",\"name\":\"ID_3\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":178,\"type\":\"INTEGER\",\"name\":\"ID_4\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}],\"viral\":[],\"measures\":[{\"id\":179,\"type\":\"BOOLEAN\",\"name\":\"ME_1\",\"domainValue\":\"bool_vd\",\"domainValueParent\":\"bool_vd\",\"vtlComponentRole\":\"MEASURE\"}]},\"messages\":[],\"command\":\"DS_r:=DS_19orDS_20;\",\"ascalar\":false,\"acomponent\":false},{\"result\":{\"id\":44,\"name\":\"temporary_43\",\"description\":null,\"attributes\":[],\"persistent\":false,\"identifiers\":[{\"id\":170,\"type\":\"STRING\",\"name\":\"ID_1\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":171,\"type\":\"INTEGER\",\"name\":\"ID_2\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":172,\"type\":\"STRING\",\"name\":\"ID_3\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":173,\"type\":\"INTEGER\",\"name\":\"ID_4\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}],\"viral\":[],\"measures\":[{\"id\":174,\"type\":\"BOOLEAN\",\"name\":\"ME_1\",\"domainValue\":\"bool_vd\",\"domainValueParent\":\"bool_vd\",\"vtlComponentRole\":\"MEASURE\"}]},\"messages\":[],\"command\":\"DS_19orDS_20\",\"ascalar\":false,\"acomponent\":false},{\"result\":{\"id\":19,\"name\":\"DS_19\",\"description\":null,\"attributes\":[],\"persistent\":true,\"identifiers\":[{\"id\":74,\"type\":\"STRING\",\"name\":\"ID_1\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":75,\"type\":\"INTEGER\",\"name\":\"ID_2\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":76,\"type\":\"STRING\",\"name\":\"ID_3\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":77,\"type\":\"INTEGER\",\"name\":\"ID_4\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}],\"viral\":[],\"measures\":[{\"id\":78,\"type\":\"BOOLEAN\",\"name\":\"ME_1\",\"domainValue\":\"bool_vd\",\"domainValueParent\":\"bool_vd\",\"vtlComponentRole\":\"MEASURE\"}]},\"messages\":[],\"command\":\"DS_19\",\"ascalar\":false,\"acomponent\":false},{\"result\":{\"id\":20,\"name\":\"DS_20\",\"description\":null,\"attributes\":[],\"persistent\":true,\"identifiers\":[{\"id\":79,\"type\":\"STRING\",\"name\":\"ID_1\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":80,\"type\":\"INTEGER\",\"name\":\"ID_2\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":81,\"type\":\"STRING\",\"name\":\"ID_3\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":82,\"type\":\"INTEGER\",\"name\":\"ID_4\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}],\"viral\":[],\"measures\":[{\"id\":83,\"type\":\"BOOLEAN\",\"name\":\"ME_1\",\"domainValue\":\"bool_vd\",\"domainValueParent\":\"bool_vd\",\"vtlComponentRole\":\"MEASURE\"}]},\"messages\":[],\"command\":\"DS_20\",\"ascalar\":false,\"acomponent\":false}]";
		List<ResultExpression> result = vtlTestUtils.getResult(commandStatements);
		List<ResultExpression> resultExpressionsToComapare = vtlTestUtils.convertJsonToResultExpression(jsonResult);
		assertNotNull("Conversione non riuscita", resultExpressionsToComapare);
		assertNotNull("Validazione Semantica non riuscita", result);
		assertTrue("I due risultati non sono uguali",
				vtlTestUtils.compareResult(result.get(0), resultExpressionsToComapare.get(0)));
	}

	@Test
	@Transactional
	public void orComponent() throws Exception {
		VtlDataset vtlDatasetFind = datasetRepository.findByNameIgnoreCase("DS_1");
		if (vtlDatasetFind == null) {
			VtlDataset vtlDataset = new VtlDataset();

			vtlDataset.setPersistent(true);
			vtlDataset.setName("DS_19");
			vtlDataset.setIsOnlyAScalar(false);
			vtlDataset.setTransitory(false);
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_1", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_2", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_3", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_4", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_1", VtlType.BOOLEAN, VtlComponentRole.MEASURE));
			datasetRepository.save(vtlDataset);

		}
		String commandStatements = "DS_r := DS_19 [ calc Me_2:= Me_1 or true ];";
		String jsonResult = "[{\"result\":{\"id\":42,\"name\":\"DS_r\",\"description\":null,\"attributes\":[],\"persistent\":false,\"measures\":[{\"id\":5,\"type\":\"BOOLEAN\",\"name\":\"ME_1\",\"domainValue\":\"bool_vd\",\"domainValueParent\":\"bool_vd\",\"vtlComponentRole\":\"MEASURE\"},{\"id\":102,\"type\":\"BOOLEAN\",\"name\":\"Me_2\",\"domainValue\":\"bool_vd\",\"domainValueParent\":\"bool_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"viral\":[],\"identifiers\":[{\"id\":1,\"type\":\"STRING\",\"name\":\"ID_1\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":2,\"type\":\"INTEGER\",\"name\":\"ID_2\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":3,\"type\":\"STRING\",\"name\":\"ID_3\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":4,\"type\":\"INTEGER\",\"name\":\"ID_4\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}]},\"messages\":[],\"command\":\"DS_r:=DS_19[calcMe_2:=Me_1ortrue];\",\"ascalar\":false,\"acomponent\":false},{\"result\":{\"id\":42,\"name\":\"DS_r\",\"description\":null,\"attributes\":[],\"persistent\":false,\"measures\":[{\"id\":5,\"type\":\"BOOLEAN\",\"name\":\"ME_1\",\"domainValue\":\"bool_vd\",\"domainValueParent\":\"bool_vd\",\"vtlComponentRole\":\"MEASURE\"},{\"id\":102,\"type\":\"BOOLEAN\",\"name\":\"Me_2\",\"domainValue\":\"bool_vd\",\"domainValueParent\":\"bool_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"viral\":[],\"identifiers\":[{\"id\":1,\"type\":\"STRING\",\"name\":\"ID_1\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":2,\"type\":\"INTEGER\",\"name\":\"ID_2\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":3,\"type\":\"STRING\",\"name\":\"ID_3\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":4,\"type\":\"INTEGER\",\"name\":\"ID_4\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}]},\"messages\":[],\"command\":\"calcMe_2:=Me_1ortrue\",\"ascalar\":false,\"acomponent\":false},{\"resultComponent\":{\"id\":null,\"type\":\"BOOLEAN\",\"name\":\"Me_2\",\"domainValue\":\"bool_vd\",\"domainValueParent\":\"bool_vd\",\"vtlComponentRole\":\"MEASURE\"},\"messages\":[],\"command\":\"Me_2:=Me_1ortrue\",\"ascalar\":false,\"acomponent\":true},{\"resultComponent\":{\"id\":null,\"type\":\"BOOLEAN\",\"name\":\"ME_1\",\"domainValue\":\"bool_vd\",\"domainValueParent\":\"bool_vd\",\"vtlComponentRole\":\"MEASURE\"},\"messages\":[],\"command\":\"Me_1ortrue\",\"ascalar\":false,\"acomponent\":true},{\"resultComponent\":{\"id\":5,\"type\":\"BOOLEAN\",\"name\":\"ME_1\",\"domainValue\":\"bool_vd\",\"domainValueParent\":\"bool_vd\",\"vtlComponentRole\":\"MEASURE\"},\"messages\":[],\"command\":\"Me_1\",\"ascalar\":false,\"acomponent\":true},{\"result\":{\"id\":40,\"name\":\"temporary_39\",\"description\":null,\"attributes\":[],\"persistent\":false,\"measures\":[{\"id\":101,\"type\":\"BOOLEAN\",\"name\":\"bool_var\",\"domainValue\":\"bool_vd\",\"domainValueParent\":\"bool_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"viral\":[],\"identifiers\":[]},\"resultComponent\":{\"id\":101,\"type\":\"BOOLEAN\",\"name\":\"bool_var\",\"domainValue\":\"bool_vd\",\"domainValueParent\":\"bool_vd\",\"vtlComponentRole\":\"MEASURE\"},\"messages\":[],\"command\":\"true\",\"ascalar\":true,\"acomponent\":false,\"scalarComponent\":{\"id\":101,\"type\":\"BOOLEAN\",\"name\":\"bool_var\",\"domainValue\":\"bool_vd\",\"domainValueParent\":\"bool_vd\",\"vtlComponentRole\":\"MEASURE\"}}]";
		List<ResultExpression> result = vtlTestUtils.getResult(commandStatements);
		List<ResultExpression> resultExpressionsToComapare = vtlTestUtils.convertJsonToResultExpression(jsonResult);
		assertNotNull("Conversione non riuscita", resultExpressionsToComapare);
		assertNotNull("Validazione Semantica non riuscita", result);
		assertTrue("I due risultati non sono uguali",
				vtlTestUtils.compareResult(result.get(0), resultExpressionsToComapare.get(0)));
	}

	@Test
	@Transactional
	public void orComponent2() throws Exception {
		VtlDataset vtlDatasetFind = datasetRepository.findByNameIgnoreCase("DS_1");
		if (vtlDatasetFind == null) {
			VtlDataset vtlDataset = new VtlDataset();

			vtlDataset.setPersistent(true);
			vtlDataset.setName("DS_19");
			vtlDataset.setIsOnlyAScalar(false);
			vtlDataset.setTransitory(false);
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_1", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_2", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_3", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_4", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_1", VtlType.BOOLEAN, VtlComponentRole.MEASURE));
			datasetRepository.save(vtlDataset);
		}
		String commandStatements = "DS_r := DS_19 or true;";
		String jsonResult = "[{\"result\":{\"id\":6,\"name\":\"DS_r\",\"description\":null,\"attributes\":[],\"persistent\":false,\"viral\":[],\"identifiers\":[{\"id\":1,\"type\":\"STRING\",\"name\":\"ID_1\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":2,\"type\":\"INTEGER\",\"name\":\"ID_2\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":3,\"type\":\"STRING\",\"name\":\"ID_3\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":4,\"type\":\"INTEGER\",\"name\":\"ID_4\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}],\"measures\":[{\"id\":11,\"type\":\"BOOLEAN\",\"name\":\"ME_1\",\"domainValue\":\"bool_vd\",\"domainValueParent\":\"bool_vd\",\"vtlComponentRole\":\"MEASURE\"}]},\"messages\":[],\"command\":\"DS_r:=DS_19ortrue;\",\"acomponent\":false,\"ascalar\":false},{\"result\":{\"id\":6,\"name\":\"DS_r\",\"description\":null,\"attributes\":[],\"persistent\":false,\"viral\":[],\"identifiers\":[{\"id\":1,\"type\":\"STRING\",\"name\":\"ID_1\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":2,\"type\":\"INTEGER\",\"name\":\"ID_2\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":3,\"type\":\"STRING\",\"name\":\"ID_3\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":4,\"type\":\"INTEGER\",\"name\":\"ID_4\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}],\"measures\":[{\"id\":11,\"type\":\"BOOLEAN\",\"name\":\"ME_1\",\"domainValue\":\"bool_vd\",\"domainValueParent\":\"bool_vd\",\"vtlComponentRole\":\"MEASURE\"}]},\"messages\":[],\"command\":\"DS_19ortrue\",\"acomponent\":false,\"ascalar\":false},{\"result\":{\"id\":1,\"name\":\"DS_19\",\"description\":null,\"attributes\":[],\"persistent\":true,\"viral\":[],\"identifiers\":[{\"id\":1,\"type\":\"STRING\",\"name\":\"ID_1\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":2,\"type\":\"INTEGER\",\"name\":\"ID_2\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":3,\"type\":\"STRING\",\"name\":\"ID_3\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":4,\"type\":\"INTEGER\",\"name\":\"ID_4\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}],\"measures\":[{\"id\":5,\"type\":\"BOOLEAN\",\"name\":\"ME_1\",\"domainValue\":\"bool_vd\",\"domainValueParent\":\"bool_vd\",\"vtlComponentRole\":\"MEASURE\"}]},\"messages\":[],\"command\":\"DS_19\",\"acomponent\":false,\"ascalar\":false},{\"result\":{\"id\":4,\"name\":\"temporary_3\",\"description\":null,\"attributes\":[],\"persistent\":false,\"viral\":[],\"identifiers\":[],\"measures\":[{\"id\":10,\"type\":\"BOOLEAN\",\"name\":\"bool_var\",\"domainValue\":\"bool_vd\",\"domainValueParent\":\"bool_vd\",\"vtlComponentRole\":\"MEASURE\"}]},\"resultComponent\":{\"id\":10,\"type\":\"BOOLEAN\",\"name\":\"bool_var\",\"domainValue\":\"bool_vd\",\"domainValueParent\":\"bool_vd\",\"vtlComponentRole\":\"MEASURE\"},\"messages\":[],\"command\":\"true\",\"acomponent\":false,\"ascalar\":true,\"scalarComponent\":{\"id\":10,\"type\":\"BOOLEAN\",\"name\":\"bool_var\",\"domainValue\":\"bool_vd\",\"domainValueParent\":\"bool_vd\",\"vtlComponentRole\":\"MEASURE\"}}]";
		List<ResultExpression> result = vtlTestUtils.getResult(commandStatements);
		List<ResultExpression> resultExpressionsToComapare = vtlTestUtils.convertJsonToResultExpression(jsonResult);
		assertNotNull("Conversione non riuscita", resultExpressionsToComapare);
		assertNotNull("Validazione Semantica non riuscita", result);
		assertTrue("I due risultati non sono uguali",
				vtlTestUtils.compareResult(result.get(0), resultExpressionsToComapare.get(0)));
	}

	@Test
	@Transactional
	public void orComponent3() throws Exception {
		VtlDataset vtlDatasetFind = datasetRepository.findByNameIgnoreCase("DS_1");
		if (vtlDatasetFind == null) {
			VtlDataset vtlDataset = new VtlDataset();

			vtlDataset.setPersistent(true);
			vtlDataset.setName("DS_19");
			vtlDataset.setIsOnlyAScalar(false);
			vtlDataset.setTransitory(false);
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_1", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_2", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_3", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_4", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_1", VtlType.BOOLEAN, VtlComponentRole.MEASURE));
			datasetRepository.save(vtlDataset);
		}
		String commandStatements = "DS_r := DS_19 or false;";
		String jsonResult = "[{\"result\":{\"id\":10,\"name\":\"DS_r\",\"description\":null,\"attributes\":[],\"persistent\":false,\"viral\":[],\"identifiers\":[{\"id\":1,\"type\":\"STRING\",\"name\":\"ID_1\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":2,\"type\":\"INTEGER\",\"name\":\"ID_2\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":3,\"type\":\"STRING\",\"name\":\"ID_3\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":4,\"type\":\"INTEGER\",\"name\":\"ID_4\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}],\"measures\":[{\"id\":13,\"type\":\"BOOLEAN\",\"name\":\"ME_1\",\"domainValue\":\"bool_vd\",\"domainValueParent\":\"bool_vd\",\"vtlComponentRole\":\"MEASURE\"}]},\"messages\":[],\"command\":\"DS_r:=DS_19orfalse;\",\"acomponent\":false,\"ascalar\":false},{\"result\":{\"id\":10,\"name\":\"DS_r\",\"description\":null,\"attributes\":[],\"persistent\":false,\"viral\":[],\"identifiers\":[{\"id\":1,\"type\":\"STRING\",\"name\":\"ID_1\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":2,\"type\":\"INTEGER\",\"name\":\"ID_2\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":3,\"type\":\"STRING\",\"name\":\"ID_3\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":4,\"type\":\"INTEGER\",\"name\":\"ID_4\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}],\"measures\":[{\"id\":13,\"type\":\"BOOLEAN\",\"name\":\"ME_1\",\"domainValue\":\"bool_vd\",\"domainValueParent\":\"bool_vd\",\"vtlComponentRole\":\"MEASURE\"}]},\"messages\":[],\"command\":\"DS_19orfalse\",\"acomponent\":false,\"ascalar\":false},{\"result\":{\"id\":1,\"name\":\"DS_19\",\"description\":null,\"attributes\":[],\"persistent\":true,\"viral\":[],\"identifiers\":[{\"id\":1,\"type\":\"STRING\",\"name\":\"ID_1\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":2,\"type\":\"INTEGER\",\"name\":\"ID_2\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":3,\"type\":\"STRING\",\"name\":\"ID_3\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":4,\"type\":\"INTEGER\",\"name\":\"ID_4\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}],\"measures\":[{\"id\":5,\"type\":\"BOOLEAN\",\"name\":\"ME_1\",\"domainValue\":\"bool_vd\",\"domainValueParent\":\"bool_vd\",\"vtlComponentRole\":\"MEASURE\"}]},\"messages\":[],\"command\":\"DS_19\",\"acomponent\":false,\"ascalar\":false},{\"result\":{\"id\":8,\"name\":\"temporary_7\",\"description\":null,\"attributes\":[],\"persistent\":false,\"viral\":[],\"identifiers\":[],\"measures\":[{\"id\":12,\"type\":\"BOOLEAN\",\"name\":\"bool_var\",\"domainValue\":\"bool_vd\",\"domainValueParent\":\"bool_vd\",\"vtlComponentRole\":\"MEASURE\"}]},\"resultComponent\":{\"id\":12,\"type\":\"BOOLEAN\",\"name\":\"bool_var\",\"domainValue\":\"bool_vd\",\"domainValueParent\":\"bool_vd\",\"vtlComponentRole\":\"MEASURE\"},\"messages\":[],\"command\":\"false\",\"acomponent\":false,\"ascalar\":true,\"scalarComponent\":{\"id\":12,\"type\":\"BOOLEAN\",\"name\":\"bool_var\",\"domainValue\":\"bool_vd\",\"domainValueParent\":\"bool_vd\",\"vtlComponentRole\":\"MEASURE\"}}]";
		List<ResultExpression> result = vtlTestUtils.getResult(commandStatements);
		List<ResultExpression> resultExpressionsToComapare = vtlTestUtils.convertJsonToResultExpression(jsonResult);
		assertNotNull("Conversione non riuscita", resultExpressionsToComapare);
		assertNotNull("Validazione Semantica non riuscita", result);
		assertTrue("I due risultati non sono uguali",
				vtlTestUtils.compareResult(result.get(0), resultExpressionsToComapare.get(0)));
	}

	@Test
	@Transactional
	public void xor() throws Exception {
		VtlDataset vtlDatasetFind = datasetRepository.findByNameIgnoreCase("DS_1");
		if (vtlDatasetFind == null) {
			VtlDataset vtlDataset = new VtlDataset();

			vtlDataset.setPersistent(true);
			vtlDataset.setName("DS_19");
			vtlDataset.setIsOnlyAScalar(false);
			vtlDataset.setTransitory(false);
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_1", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_2", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_3", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_4", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_1", VtlType.BOOLEAN, VtlComponentRole.MEASURE));
			datasetRepository.save(vtlDataset);
		}
		String commandStatements = "DS_r := DS_19 xor true;";
		String jsonResult = "[{\"result\":{\"id\":50,\"name\":\"DS_r\",\"description\":null,\"attributes\":[],\"persistent\":false,\"measures\":[{\"id\":106,\"type\":\"BOOLEAN\",\"name\":\"ME_1\",\"domainValue\":\"bool_vd\",\"domainValueParent\":\"bool_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"viral\":[],\"identifiers\":[{\"id\":1,\"type\":\"STRING\",\"name\":\"ID_1\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":2,\"type\":\"INTEGER\",\"name\":\"ID_2\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":3,\"type\":\"STRING\",\"name\":\"ID_3\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":4,\"type\":\"INTEGER\",\"name\":\"ID_4\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}]},\"messages\":[],\"command\":\"DS_r:=DS_19xortrue;\",\"ascalar\":false,\"acomponent\":false},{\"result\":{\"id\":50,\"name\":\"DS_r\",\"description\":null,\"attributes\":[],\"persistent\":false,\"measures\":[{\"id\":106,\"type\":\"BOOLEAN\",\"name\":\"ME_1\",\"domainValue\":\"bool_vd\",\"domainValueParent\":\"bool_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"viral\":[],\"identifiers\":[{\"id\":1,\"type\":\"STRING\",\"name\":\"ID_1\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":2,\"type\":\"INTEGER\",\"name\":\"ID_2\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":3,\"type\":\"STRING\",\"name\":\"ID_3\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":4,\"type\":\"INTEGER\",\"name\":\"ID_4\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}]},\"messages\":[],\"command\":\"DS_19xortrue\",\"ascalar\":false,\"acomponent\":false},{\"result\":{\"id\":1,\"name\":\"DS_19\",\"description\":null,\"attributes\":[],\"persistent\":true,\"measures\":[{\"id\":5,\"type\":\"BOOLEAN\",\"name\":\"ME_1\",\"domainValue\":\"bool_vd\",\"domainValueParent\":\"bool_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"viral\":[],\"identifiers\":[{\"id\":1,\"type\":\"STRING\",\"name\":\"ID_1\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":2,\"type\":\"INTEGER\",\"name\":\"ID_2\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":3,\"type\":\"STRING\",\"name\":\"ID_3\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":4,\"type\":\"INTEGER\",\"name\":\"ID_4\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}]},\"messages\":[],\"command\":\"DS_19\",\"ascalar\":false,\"acomponent\":false},{\"result\":{\"id\":48,\"name\":\"temporary_47\",\"description\":null,\"attributes\":[],\"persistent\":false,\"measures\":[{\"id\":105,\"type\":\"BOOLEAN\",\"name\":\"bool_var\",\"domainValue\":\"bool_vd\",\"domainValueParent\":\"bool_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"viral\":[],\"identifiers\":[]},\"resultComponent\":{\"id\":105,\"type\":\"BOOLEAN\",\"name\":\"bool_var\",\"domainValue\":\"bool_vd\",\"domainValueParent\":\"bool_vd\",\"vtlComponentRole\":\"MEASURE\"},\"messages\":[],\"command\":\"true\",\"ascalar\":true,\"acomponent\":false,\"scalarComponent\":{\"id\":105,\"type\":\"BOOLEAN\",\"name\":\"bool_var\",\"domainValue\":\"bool_vd\",\"domainValueParent\":\"bool_vd\",\"vtlComponentRole\":\"MEASURE\"}}]";
		List<ResultExpression> result = vtlTestUtils.getResult(commandStatements);
		List<ResultExpression> resultExpressionsToComapare = vtlTestUtils.convertJsonToResultExpression(jsonResult);
		assertNotNull("Conversione non riuscita", resultExpressionsToComapare);
		assertNotNull("Validazione Semantica non riuscita", result);
		assertTrue("I due risultati non sono uguali",
				vtlTestUtils.compareResult(result.get(0), resultExpressionsToComapare.get(0)));
	}

	@Test
	@Transactional
	public void xorDs() throws Exception {
		VtlDataset vtlDatasetFind = datasetRepository.findByNameIgnoreCase("DS_1");
		if (vtlDatasetFind == null) {
			VtlDataset vtlDataset = new VtlDataset();

			vtlDataset.setPersistent(true);
			vtlDataset.setName("DS_19");
			vtlDataset.setIsOnlyAScalar(false);
			vtlDataset.setTransitory(false);
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_1", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_2", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_3", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_4", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_1", VtlType.BOOLEAN, VtlComponentRole.MEASURE));
			datasetRepository.save(vtlDataset);

			VtlDataset vtlDatasetSecond = new VtlDataset();
			vtlDatasetSecond.setPersistent(true);
			vtlDatasetSecond.setName("DS_20");
			vtlDatasetSecond.setIsOnlyAScalar(false);
			vtlDatasetSecond.setTransitory(false);
			vtlDatasetSecond
					.addComponent(vtlTestUtils.getVtlComponent("ID_1", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDatasetSecond
					.addComponent(vtlTestUtils.getVtlComponent("ID_2", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDatasetSecond
					.addComponent(vtlTestUtils.getVtlComponent("ID_3", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDatasetSecond
					.addComponent(vtlTestUtils.getVtlComponent("ID_4", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDatasetSecond
					.addComponent(vtlTestUtils.getVtlComponent("ME_1", VtlType.BOOLEAN, VtlComponentRole.MEASURE));
			datasetRepository.save(vtlDatasetSecond);
		}
		String commandStatements = "DS_r := DS_19 xor DS_20;";
		String jsonResult = "[{\"result\":{\"id\":50,\"name\":\"DS_r\",\"description\":null,\"attributes\":[],\"persistent\":false,\"identifiers\":[{\"id\":185,\"type\":\"STRING\",\"name\":\"ID_1\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":186,\"type\":\"INTEGER\",\"name\":\"ID_2\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":187,\"type\":\"STRING\",\"name\":\"ID_3\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":188,\"type\":\"INTEGER\",\"name\":\"ID_4\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}],\"viral\":[],\"measures\":[{\"id\":189,\"type\":\"BOOLEAN\",\"name\":\"ME_1\",\"domainValue\":\"bool_vd\",\"domainValueParent\":\"bool_vd\",\"vtlComponentRole\":\"MEASURE\"}]},\"messages\":[],\"command\":\"DS_r:=DS_19xorDS_20;\",\"ascalar\":false,\"acomponent\":false},{\"result\":{\"id\":48,\"name\":\"temporary_47\",\"description\":null,\"attributes\":[],\"persistent\":false,\"identifiers\":[{\"id\":180,\"type\":\"STRING\",\"name\":\"ID_1\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":181,\"type\":\"INTEGER\",\"name\":\"ID_2\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":182,\"type\":\"STRING\",\"name\":\"ID_3\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":183,\"type\":\"INTEGER\",\"name\":\"ID_4\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}],\"viral\":[],\"measures\":[{\"id\":184,\"type\":\"BOOLEAN\",\"name\":\"ME_1\",\"domainValue\":\"bool_vd\",\"domainValueParent\":\"bool_vd\",\"vtlComponentRole\":\"MEASURE\"}]},\"messages\":[],\"command\":\"DS_19xorDS_20\",\"ascalar\":false,\"acomponent\":false},{\"result\":{\"id\":19,\"name\":\"DS_19\",\"description\":null,\"attributes\":[],\"persistent\":true,\"identifiers\":[{\"id\":74,\"type\":\"STRING\",\"name\":\"ID_1\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":75,\"type\":\"INTEGER\",\"name\":\"ID_2\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":76,\"type\":\"STRING\",\"name\":\"ID_3\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":77,\"type\":\"INTEGER\",\"name\":\"ID_4\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}],\"viral\":[],\"measures\":[{\"id\":78,\"type\":\"BOOLEAN\",\"name\":\"ME_1\",\"domainValue\":\"bool_vd\",\"domainValueParent\":\"bool_vd\",\"vtlComponentRole\":\"MEASURE\"}]},\"messages\":[],\"command\":\"DS_19\",\"ascalar\":false,\"acomponent\":false},{\"result\":{\"id\":20,\"name\":\"DS_20\",\"description\":null,\"attributes\":[],\"persistent\":true,\"identifiers\":[{\"id\":79,\"type\":\"STRING\",\"name\":\"ID_1\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":80,\"type\":\"INTEGER\",\"name\":\"ID_2\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":81,\"type\":\"STRING\",\"name\":\"ID_3\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":82,\"type\":\"INTEGER\",\"name\":\"ID_4\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}],\"viral\":[],\"measures\":[{\"id\":83,\"type\":\"BOOLEAN\",\"name\":\"ME_1\",\"domainValue\":\"bool_vd\",\"domainValueParent\":\"bool_vd\",\"vtlComponentRole\":\"MEASURE\"}]},\"messages\":[],\"command\":\"DS_20\",\"ascalar\":false,\"acomponent\":false}]";
		List<ResultExpression> result = vtlTestUtils.getResult(commandStatements);
		List<ResultExpression> resultExpressionsToComapare = vtlTestUtils.convertJsonToResultExpression(jsonResult);
		assertNotNull("Conversione non riuscita", resultExpressionsToComapare);
		assertNotNull("Validazione Semantica non riuscita", result);
		assertTrue("I due risultati non sono uguali",
				vtlTestUtils.compareResult(result.get(0), resultExpressionsToComapare.get(0)));
	}

	@Test
	@Transactional
	public void xorComponent() throws Exception {
		VtlDataset vtlDatasetFind = datasetRepository.findByNameIgnoreCase("DS_1");
		if (vtlDatasetFind == null) {
			VtlDataset vtlDataset = new VtlDataset();

			vtlDataset.setPersistent(true);
			vtlDataset.setName("DS_19");
			vtlDataset.setIsOnlyAScalar(false);
			vtlDataset.setTransitory(false);
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_1", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_2", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_3", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_4", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_1", VtlType.BOOLEAN, VtlComponentRole.MEASURE));
			datasetRepository.save(vtlDataset);
		}
		String commandStatements = "DS_r := DS_19 [ calc Me_2:= Me_1  xor  true ];";
		String jsonResult = "[{\"result\":{\"id\":58,\"name\":\"DS_r\",\"description\":null,\"attributes\":[],\"persistent\":false,\"measures\":[{\"id\":5,\"type\":\"BOOLEAN\",\"name\":\"ME_1\",\"domainValue\":\"bool_vd\",\"domainValueParent\":\"bool_vd\",\"vtlComponentRole\":\"MEASURE\"},{\"id\":110,\"type\":\"BOOLEAN\",\"name\":\"Me_2\",\"domainValue\":\"bool_vd\",\"domainValueParent\":\"bool_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"viral\":[],\"identifiers\":[{\"id\":1,\"type\":\"STRING\",\"name\":\"ID_1\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":2,\"type\":\"INTEGER\",\"name\":\"ID_2\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":3,\"type\":\"STRING\",\"name\":\"ID_3\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":4,\"type\":\"INTEGER\",\"name\":\"ID_4\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}]},\"messages\":[],\"command\":\"DS_r:=DS_19[calcMe_2:=Me_1xortrue];\",\"ascalar\":false,\"acomponent\":false},{\"result\":{\"id\":58,\"name\":\"DS_r\",\"description\":null,\"attributes\":[],\"persistent\":false,\"measures\":[{\"id\":5,\"type\":\"BOOLEAN\",\"name\":\"ME_1\",\"domainValue\":\"bool_vd\",\"domainValueParent\":\"bool_vd\",\"vtlComponentRole\":\"MEASURE\"},{\"id\":110,\"type\":\"BOOLEAN\",\"name\":\"Me_2\",\"domainValue\":\"bool_vd\",\"domainValueParent\":\"bool_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"viral\":[],\"identifiers\":[{\"id\":1,\"type\":\"STRING\",\"name\":\"ID_1\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":2,\"type\":\"INTEGER\",\"name\":\"ID_2\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":3,\"type\":\"STRING\",\"name\":\"ID_3\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":4,\"type\":\"INTEGER\",\"name\":\"ID_4\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}]},\"messages\":[],\"command\":\"calcMe_2:=Me_1xortrue\",\"ascalar\":false,\"acomponent\":false},{\"resultComponent\":{\"id\":null,\"type\":\"BOOLEAN\",\"name\":\"Me_2\",\"domainValue\":\"bool_vd\",\"domainValueParent\":\"bool_vd\",\"vtlComponentRole\":\"MEASURE\"},\"messages\":[],\"command\":\"Me_2:=Me_1xortrue\",\"ascalar\":false,\"acomponent\":true},{\"resultComponent\":{\"id\":null,\"type\":\"BOOLEAN\",\"name\":\"ME_1\",\"domainValue\":\"bool_vd\",\"domainValueParent\":\"bool_vd\",\"vtlComponentRole\":\"MEASURE\"},\"messages\":[],\"command\":\"Me_1xortrue\",\"ascalar\":false,\"acomponent\":true},{\"resultComponent\":{\"id\":5,\"type\":\"BOOLEAN\",\"name\":\"ME_1\",\"domainValue\":\"bool_vd\",\"domainValueParent\":\"bool_vd\",\"vtlComponentRole\":\"MEASURE\"},\"messages\":[],\"command\":\"Me_1\",\"ascalar\":false,\"acomponent\":true},{\"result\":{\"id\":56,\"name\":\"temporary_55\",\"description\":null,\"attributes\":[],\"persistent\":false,\"measures\":[{\"id\":109,\"type\":\"BOOLEAN\",\"name\":\"bool_var\",\"domainValue\":\"bool_vd\",\"domainValueParent\":\"bool_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"viral\":[],\"identifiers\":[]},\"resultComponent\":{\"id\":109,\"type\":\"BOOLEAN\",\"name\":\"bool_var\",\"domainValue\":\"bool_vd\",\"domainValueParent\":\"bool_vd\",\"vtlComponentRole\":\"MEASURE\"},\"messages\":[],\"command\":\"true\",\"ascalar\":true,\"acomponent\":false,\"scalarComponent\":{\"id\":109,\"type\":\"BOOLEAN\",\"name\":\"bool_var\",\"domainValue\":\"bool_vd\",\"domainValueParent\":\"bool_vd\",\"vtlComponentRole\":\"MEASURE\"}}]";
		List<ResultExpression> result = vtlTestUtils.getResult(commandStatements);
		List<ResultExpression> resultExpressionsToComapare = vtlTestUtils.convertJsonToResultExpression(jsonResult);
		assertNotNull("Conversione non riuscita", resultExpressionsToComapare);
		assertNotNull("Validazione Semantica non riuscita", result);
		assertTrue("I due risultati non sono uguali",
				vtlTestUtils.compareResult(result.get(0), resultExpressionsToComapare.get(0)));
	}

	@Test
	@Transactional
	public void not() throws Exception {
		VtlDataset vtlDatasetFind = datasetRepository.findByNameIgnoreCase("DS_1");
		if (vtlDatasetFind == null) {
			VtlDataset vtlDataset = new VtlDataset();

			vtlDataset.setPersistent(true);
			vtlDataset.setName("DS_19");
			vtlDataset.setIsOnlyAScalar(false);
			vtlDataset.setTransitory(false);
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_1", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_2", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_3", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_4", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_1", VtlType.BOOLEAN, VtlComponentRole.MEASURE));
			datasetRepository.save(vtlDataset);
		}
		String commandStatements = "DS_r := not DS_19;";
		String jsonResult = "[{\"result\":{\"id\":60,\"name\":\"DS_r\",\"description\":null,\"attributes\":[],\"persistent\":false,\"measures\":[{\"id\":111,\"type\":\"BOOLEAN\",\"name\":\"ME_1\",\"domainValue\":\"bool_vd\",\"domainValueParent\":\"bool_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"viral\":[],\"identifiers\":[{\"id\":1,\"type\":\"STRING\",\"name\":\"ID_1\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":2,\"type\":\"INTEGER\",\"name\":\"ID_2\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":3,\"type\":\"STRING\",\"name\":\"ID_3\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":4,\"type\":\"INTEGER\",\"name\":\"ID_4\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}]},\"messages\":[],\"command\":\"DS_r:=notDS_19;\",\"ascalar\":false,\"acomponent\":false},{\"result\":{\"id\":60,\"name\":\"DS_r\",\"description\":null,\"attributes\":[],\"persistent\":false,\"measures\":[{\"id\":111,\"type\":\"BOOLEAN\",\"name\":\"ME_1\",\"domainValue\":\"bool_vd\",\"domainValueParent\":\"bool_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"viral\":[],\"identifiers\":[{\"id\":1,\"type\":\"STRING\",\"name\":\"ID_1\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":2,\"type\":\"INTEGER\",\"name\":\"ID_2\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":3,\"type\":\"STRING\",\"name\":\"ID_3\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":4,\"type\":\"INTEGER\",\"name\":\"ID_4\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}]},\"messages\":[],\"command\":\"notDS_19\",\"ascalar\":false,\"acomponent\":false},{\"result\":{\"id\":1,\"name\":\"DS_19\",\"description\":null,\"attributes\":[],\"persistent\":true,\"measures\":[{\"id\":5,\"type\":\"BOOLEAN\",\"name\":\"ME_1\",\"domainValue\":\"bool_vd\",\"domainValueParent\":\"bool_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"viral\":[],\"identifiers\":[{\"id\":1,\"type\":\"STRING\",\"name\":\"ID_1\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":2,\"type\":\"INTEGER\",\"name\":\"ID_2\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":3,\"type\":\"STRING\",\"name\":\"ID_3\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":4,\"type\":\"INTEGER\",\"name\":\"ID_4\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}]},\"messages\":[],\"command\":\"DS_19\",\"ascalar\":false,\"acomponent\":false}]";
		List<ResultExpression> result = vtlTestUtils.getResult(commandStatements);
		List<ResultExpression> resultExpressionsToComapare = vtlTestUtils.convertJsonToResultExpression(jsonResult);
		assertNotNull("Conversione non riuscita", resultExpressionsToComapare);
		assertNotNull("Validazione Semantica non riuscita", result);
		assertTrue("I due risultati non sono uguali",
				vtlTestUtils.compareResult(result.get(0), resultExpressionsToComapare.get(0)));

	}

	@Test
	@Transactional
	public void notComponent() throws Exception {
		VtlDataset vtlDatasetFind = datasetRepository.findByNameIgnoreCase("DS_1");
		if (vtlDatasetFind == null) {
			VtlDataset vtlDataset = new VtlDataset();

			vtlDataset.setPersistent(true);
			vtlDataset.setName("DS_19");
			vtlDataset.setIsOnlyAScalar(false);
			vtlDataset.setTransitory(false);
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_1", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_2", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_3", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_4", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_1", VtlType.BOOLEAN, VtlComponentRole.MEASURE));
			datasetRepository.save(vtlDataset);
		}
		String commandStatements = "DS_r := DS_19 [ calc Me_2 := not Me_1 ];";
		String jsonResult = "[{\"result\":{\"id\":54,\"name\":\"DS_r\",\"description\":null,\"attributes\":[],\"persistent\":false,\"identifiers\":[{\"id\":197,\"type\":\"STRING\",\"name\":\"ID_1\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":198,\"type\":\"INTEGER\",\"name\":\"ID_2\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":199,\"type\":\"STRING\",\"name\":\"ID_3\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":200,\"type\":\"INTEGER\",\"name\":\"ID_4\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}],\"viral\":[],\"measures\":[{\"id\":196,\"type\":\"BOOLEAN\",\"name\":\"ME_1\",\"domainValue\":\"bool_vd\",\"domainValueParent\":\"bool_vd\",\"vtlComponentRole\":\"MEASURE\"},{\"id\":201,\"type\":\"BOOLEAN\",\"name\":\"Me_2\",\"domainValue\":\"bool_vd\",\"domainValueParent\":\"bool_vd\",\"vtlComponentRole\":\"MEASURE\"}]},\"messages\":[],\"command\":\"DS_r:=DS_19[calcMe_2:=notMe_1];\",\"ascalar\":false,\"acomponent\":false},{\"result\":{\"id\":52,\"name\":\"temporary_51\",\"description\":null,\"attributes\":[],\"persistent\":false,\"identifiers\":[{\"id\":191,\"type\":\"STRING\",\"name\":\"ID_1\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":192,\"type\":\"INTEGER\",\"name\":\"ID_2\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":193,\"type\":\"STRING\",\"name\":\"ID_3\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":194,\"type\":\"INTEGER\",\"name\":\"ID_4\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}],\"viral\":[],\"measures\":[{\"id\":190,\"type\":\"BOOLEAN\",\"name\":\"ME_1\",\"domainValue\":\"bool_vd\",\"domainValueParent\":\"bool_vd\",\"vtlComponentRole\":\"MEASURE\"},{\"id\":195,\"type\":\"BOOLEAN\",\"name\":\"Me_2\",\"domainValue\":\"bool_vd\",\"domainValueParent\":\"bool_vd\",\"vtlComponentRole\":\"MEASURE\"}]},\"messages\":[],\"command\":\"calcMe_2:=notMe_1\",\"ascalar\":false,\"acomponent\":false},{\"resultComponent\":{\"id\":null,\"type\":\"BOOLEAN\",\"name\":\"Me_2\",\"domainValue\":\"bool_vd\",\"domainValueParent\":\"bool_vd\",\"vtlComponentRole\":\"MEASURE\"},\"messages\":[],\"command\":\"Me_2:=notMe_1\",\"ascalar\":false,\"acomponent\":true},{\"resultComponent\":{\"id\":null,\"type\":\"BOOLEAN\",\"name\":\"ME_1\",\"domainValue\":\"bool_vd\",\"domainValueParent\":\"bool_vd\",\"vtlComponentRole\":\"MEASURE\"},\"messages\":[],\"command\":\"notMe_1\",\"ascalar\":false,\"acomponent\":true},{\"resultComponent\":{\"id\":78,\"type\":\"BOOLEAN\",\"name\":\"ME_1\",\"domainValue\":\"bool_vd\",\"domainValueParent\":\"bool_vd\",\"vtlComponentRole\":\"MEASURE\"},\"messages\":[],\"command\":\"Me_1\",\"ascalar\":false,\"acomponent\":true}]";
		List<ResultExpression> result = vtlTestUtils.getResult(commandStatements);
		List<ResultExpression> resultExpressionsToComapare = vtlTestUtils.convertJsonToResultExpression(jsonResult);
		assertNotNull("Conversione non riuscita", resultExpressionsToComapare);
		assertNotNull("Validazione Semantica non riuscita", result);
		assertTrue("I due risultati non sono uguali",
				vtlTestUtils.compareResult(result.get(0), resultExpressionsToComapare.get(0)));
	}

}
