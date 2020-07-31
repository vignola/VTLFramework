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
import com.capgemini.istat.vtlcompiler.vtlcommon.model.define.UserFunction;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.define.VtlUserFunctionType;
import com.capgemini.istat.vtlcompiler.vtlcommon.repository.UserFunctionRepository;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class VtlGeneralPurposeOperatorsTest {

	private VtlTestUtils vtlTestUtils;
	private DatasetRepository datasetRepository;
        private UserFunctionRepository userFunctionRepository;

	@Autowired
	public void setVtlTestUtils(VtlTestUtils vtlTestUtils) {
		this.vtlTestUtils = vtlTestUtils;
	}

	@Autowired
	public void setDatasetRepository(DatasetRepository datasetRepository) {
		this.datasetRepository = datasetRepository;
	}
        
        @Autowired
        public void setUserFunctionRepository(UserFunctionRepository userFunctionRepository) {
            this.userFunctionRepository = userFunctionRepository;
        }
        
	@Test
	@Transactional
	public void Persistent() throws Exception {
		VtlDataset vtlDatasetFind = datasetRepository.findByNameIgnoreCase("DS_1");

		if (vtlDatasetFind == null) {

			VtlDataset vtlDataset = new VtlDataset();

			vtlDataset.setPersistent(true);
			vtlDataset.setName("DS_1");
			vtlDataset.setIsOnlyAScalar(false);
			vtlDataset.setTransitory(false);
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_1", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_2", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_1", VtlType.INTEGER, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_2", VtlType.INTEGER, VtlComponentRole.MEASURE));
			datasetRepository.save(vtlDataset);
		}

		String commandStatements = "DS_r <- DS_1;";
		String jsonResult = "[{\"result\":{\"id\":44,\"name\":\"DS_r\",\"description\":null,\"attributes\":[],\"persistent\":false,\"identifiers\":[{\"id\":171,\"type\":\"INTEGER\",\"name\":\"ID_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":172,\"type\":\"STRING\",\"name\":\"ID_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}],\"measures\":[{\"id\":173,\"type\":\"INTEGER\",\"name\":\"ME_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},{\"id\":174,\"type\":\"INTEGER\",\"name\":\"ME_2\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"viral\":[]},\"messages\":[],\"command\":\"DS_r:=DS_1;\",\"acomponent\":false,\"ascalar\":false},{\"result\":{\"id\":1,\"name\":\"DS_1\",\"description\":null,\"attributes\":[],\"persistent\":true,\"identifiers\":[{\"id\":1,\"type\":\"INTEGER\",\"name\":\"ID_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":2,\"type\":\"STRING\",\"name\":\"ID_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}],\"measures\":[{\"id\":3,\"type\":\"INTEGER\",\"name\":\"ME_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},{\"id\":4,\"type\":\"INTEGER\",\"name\":\"ME_2\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"viral\":[]},\"messages\":[],\"command\":\"DS_1\",\"acomponent\":false,\"ascalar\":false}]";
		List<ResultExpression> result = vtlTestUtils.getResult(commandStatements);

		List<ResultExpression> resultExpressionsToComapare = vtlTestUtils.convertJsonToResultExpression(jsonResult);
		assertNotNull("Conversione non riuscita", resultExpressionsToComapare);
		assertNotNull("Validazione Semantica non riuscita", result);
		assertTrue("I due risultati non sono uguali",
				vtlTestUtils.compareResult(result.get(0), resultExpressionsToComapare.get(0)));

	}

	@Test
	@Transactional
	public void nonPersistentAssignmentComponent() throws Exception {
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
		String commandStatements = "DS_r := DS_37 [ calc Me_2 := Me_1 > 20 ];";
		String jsonResult = "[{\"result\":{\"id\":22,\"name\":\"DS_r\",\"description\":null,\"attributes\":[],\"persistent\":false,\"measures\":[{\"id\":13,\"type\":\"NUMBER\",\"name\":\"ME_1\",\"domainValue\":\"num_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},{\"id\":32,\"type\":\"BOOLEAN\",\"name\":\"Me_2\",\"domainValue\":\"bool_vd\",\"domainValueParent\":\"bool_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"viral\":[],\"identifiers\":[{\"id\":8,\"type\":\"INTEGER\",\"name\":\"ID_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":9,\"type\":\"STRING\",\"name\":\"ID_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":10,\"type\":\"INTEGER\",\"name\":\"ID_3\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":11,\"type\":\"STRING\",\"name\":\"ID_4\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":12,\"type\":\"STRING\",\"name\":\"ID_5\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}]},\"messages\":[],\"command\":\"DS_r:=DS_37[calcMe_2:=Me_1>20];\",\"acomponent\":false,\"ascalar\":false},{\"result\":{\"id\":22,\"name\":\"DS_r\",\"description\":null,\"attributes\":[],\"persistent\":false,\"measures\":[{\"id\":13,\"type\":\"NUMBER\",\"name\":\"ME_1\",\"domainValue\":\"num_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},{\"id\":32,\"type\":\"BOOLEAN\",\"name\":\"Me_2\",\"domainValue\":\"bool_vd\",\"domainValueParent\":\"bool_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"viral\":[],\"identifiers\":[{\"id\":8,\"type\":\"INTEGER\",\"name\":\"ID_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":9,\"type\":\"STRING\",\"name\":\"ID_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":10,\"type\":\"INTEGER\",\"name\":\"ID_3\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":11,\"type\":\"STRING\",\"name\":\"ID_4\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":12,\"type\":\"STRING\",\"name\":\"ID_5\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}]},\"messages\":[],\"command\":\"calcMe_2:=Me_1>20\",\"acomponent\":false,\"ascalar\":false},{\"resultComponent\":{\"id\":null,\"type\":\"BOOLEAN\",\"name\":\"Me_2\",\"domainValue\":\"bool_vd\",\"domainValueParent\":\"bool_vd\",\"vtlComponentRole\":\"MEASURE\"},\"messages\":[],\"command\":\"Me_2:=Me_1>20\",\"acomponent\":true,\"ascalar\":false},{\"resultComponent\":{\"id\":null,\"type\":\"BOOLEAN\",\"name\":\"bool_var\",\"domainValue\":\"bool_vd\",\"domainValueParent\":\"bool_vd\",\"vtlComponentRole\":\"MEASURE\"},\"messages\":[],\"command\":\"Me_1>20\",\"acomponent\":true,\"ascalar\":false},{\"resultComponent\":{\"id\":13,\"type\":\"NUMBER\",\"name\":\"ME_1\",\"domainValue\":\"num_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},\"messages\":[],\"command\":\"Me_1\",\"acomponent\":true,\"ascalar\":false},{\"result\":{\"id\":20,\"name\":\"temporary_19\",\"description\":null,\"attributes\":[],\"persistent\":false,\"measures\":[{\"id\":31,\"type\":\"INTEGER\",\"name\":\"int_var\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"viral\":[],\"identifiers\":[]},\"resultComponent\":{\"id\":31,\"type\":\"INTEGER\",\"name\":\"int_var\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},\"messages\":[],\"command\":\"20\",\"acomponent\":false,\"ascalar\":true,\"scalarComponent\":{\"id\":31,\"type\":\"INTEGER\",\"name\":\"int_var\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"}}]";
		List<ResultExpression> result = vtlTestUtils.getResult(commandStatements);
		List<ResultExpression> resultExpressionsToComapare = vtlTestUtils.convertJsonToResultExpression(jsonResult);
		assertNotNull("Conversione non riuscita", resultExpressionsToComapare);
		assertNotNull("Validazione Semantica non riuscita", result);
		assertTrue("I due risultati non sono uguali",
				vtlTestUtils.compareResult(result.get(0), resultExpressionsToComapare.get(0)));

	}

	@Test
	@Transactional
	public void nonPersistentAssignment() throws Exception {
		VtlDataset vtlDatasetFind = datasetRepository.findByNameIgnoreCase("DS_1");

		if (vtlDatasetFind == null) {

			VtlDataset vtlDataset = new VtlDataset();

			vtlDataset.setPersistent(true);
			vtlDataset.setName("DS_1");
			vtlDataset.setIsOnlyAScalar(false);
			vtlDataset.setTransitory(false);
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_1", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_2", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_1", VtlType.INTEGER, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_2", VtlType.INTEGER, VtlComponentRole.MEASURE));
			datasetRepository.save(vtlDataset);
		}
		String commandStatements = "DS_r := DS_1;";
		String jsonResult = "[{\"result\":{\"id\":679,\"name\":\"DS_r\",\"description\":null,\"attributes\":[],\"persistent\":false,\"identifiers\":[{\"id\":1,\"type\":\"INTEGER\",\"name\":\"ID_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":2,\"type\":\"STRING\",\"name\":\"ID_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}],\"measures\":[{\"id\":3,\"type\":\"INTEGER\",\"name\":\"ME_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},{\"id\":4,\"type\":\"INTEGER\",\"name\":\"ME_2\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"viral\":[]},\"messages\":[],\"command\":\"DS_r:=DS_1;\",\"acomponent\":false,\"ascalar\":false},{\"result\":{\"id\":1,\"name\":\"DS_1\",\"description\":null,\"attributes\":[],\"persistent\":true,\"identifiers\":[{\"id\":1,\"type\":\"INTEGER\",\"name\":\"ID_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":2,\"type\":\"STRING\",\"name\":\"ID_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}],\"measures\":[{\"id\":3,\"type\":\"INTEGER\",\"name\":\"ME_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},{\"id\":4,\"type\":\"INTEGER\",\"name\":\"ME_2\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"viral\":[]},\"messages\":[],\"command\":\"DS_1\",\"acomponent\":false,\"ascalar\":false}]";
		List<ResultExpression> result = vtlTestUtils.getResult(commandStatements);
		List<ResultExpression> resultExpressionsToComapare = vtlTestUtils.convertJsonToResultExpression(jsonResult);
		assertNotNull("Conversione non riuscita", resultExpressionsToComapare);
		assertNotNull("Validazione Semantica non riuscita", result);
		assertTrue("I due risultati non sono uguali",
				vtlTestUtils.compareResult(result.get(0), resultExpressionsToComapare.get(0)));

	}

	@Test
	@Transactional
	public void membershipComponent() throws Exception {

		VtlDataset vtlDatasetFind = datasetRepository.findByNameIgnoreCase("DS_1");
		if (vtlDatasetFind == null) {
			VtlDataset vtlDataset = new VtlDataset();

			vtlDataset.setPersistent(true);
			vtlDataset.setName("DS_1");
			vtlDataset.setIsOnlyAScalar(false);
			vtlDataset.setTransitory(false);
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_1", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_2", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_1", VtlType.INTEGER, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_2", VtlType.INTEGER, VtlComponentRole.MEASURE));
			datasetRepository.save(vtlDataset);
		}
		String commandStatements = "DS_r := DS_1#Me_1;";
		String jsonResult = "[{\"result\":{\"id\":46,\"name\":\"DS_r\",\"description\":null,\"attributes\":[],\"persistent\":false,\"measures\":[{\"id\":177,\"type\":\"INTEGER\",\"name\":\"ME_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"identifiers\":[{\"id\":175,\"type\":\"INTEGER\",\"name\":\"ID_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":176,\"type\":\"STRING\",\"name\":\"ID_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}],\"viral\":[]},\"messages\":[],\"command\":\"DS_r:=DS_1#Me_1;\",\"ascalar\":false,\"acomponent\":false},{\"result\":{\"id\":44,\"name\":\"temporary_43\",\"description\":\"DS_1\",\"attributes\":[],\"persistent\":false,\"measures\":[{\"id\":174,\"type\":\"INTEGER\",\"name\":\"ME_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"identifiers\":[{\"id\":172,\"type\":\"INTEGER\",\"name\":\"ID_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":173,\"type\":\"STRING\",\"name\":\"ID_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}],\"viral\":[]},\"messages\":[],\"command\":\"DS_1#Me_1\",\"ascalar\":false,\"acomponent\":false},{\"result\":{\"id\":2,\"name\":\"DS_1\",\"description\":null,\"attributes\":[],\"persistent\":true,\"measures\":[{\"id\":7,\"type\":\"INTEGER\",\"name\":\"ME_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},{\"id\":8,\"type\":\"INTEGER\",\"name\":\"ME_2\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"identifiers\":[{\"id\":5,\"type\":\"INTEGER\",\"name\":\"ID_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":6,\"type\":\"STRING\",\"name\":\"ID_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}],\"viral\":[]},\"messages\":[],\"command\":\"DS_1\",\"ascalar\":false,\"acomponent\":false}]";
		List<ResultExpression> result = vtlTestUtils.getResult(commandStatements);
		List<ResultExpression> resultExpressionsToComapare = vtlTestUtils.convertJsonToResultExpression(jsonResult);
		assertNotNull("Conversione non riuscita", resultExpressionsToComapare);
		assertNotNull("Validazione Semantica non riuscita", result);
		assertTrue("I due risultati non sono uguali",
				vtlTestUtils.compareResult(result.get(0), resultExpressionsToComapare.get(0)));

	}

	@Test
	@Transactional
	public void membershipComponentId() throws Exception {

		VtlDataset vtlDatasetFind = datasetRepository.findByNameIgnoreCase("DS_1");
		if (vtlDatasetFind == null) {
			VtlDataset vtlDataset = new VtlDataset();

			vtlDataset.setPersistent(true);
			vtlDataset.setName("DS_2");
			vtlDataset.setIsOnlyAScalar(false);
			vtlDataset.setTransitory(false);
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_1", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_2", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_1", VtlType.INTEGER, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_2", VtlType.INTEGER, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("AT_1", VtlType.STRING, VtlComponentRole.VIRAL));
			datasetRepository.save(vtlDataset);
		}
		String commandStatements = "DS_r := DS_2#Id_1;";
		String jsonResult = "[{\"result\":{\"id\":46,\"name\":\"DS_r\",\"description\":null,\"attributes\":[],\"persistent\":false,\"identifiers\":[{\"id\":177,\"type\":\"INTEGER\",\"name\":\"ID_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":178,\"type\":\"STRING\",\"name\":\"ID_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}],\"measures\":[{\"id\":179,\"type\":\"INTEGER\",\"name\":\"int_var\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"viral\":[{\"id\":180,\"type\":\"STRING\",\"name\":\"AT_1\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"VIRAL\"}]},\"messages\":[],\"command\":\"DS_r:=DS_2#Id_1;\",\"acomponent\":false,\"ascalar\":false},{\"result\":{\"id\":44,\"name\":\"temporary_43\",\"description\":\"DS_2\",\"attributes\":[],\"persistent\":false,\"identifiers\":[{\"id\":173,\"type\":\"INTEGER\",\"name\":\"ID_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":174,\"type\":\"STRING\",\"name\":\"ID_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}],\"measures\":[{\"id\":175,\"type\":\"INTEGER\",\"name\":\"int_var\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"viral\":[{\"id\":176,\"type\":\"STRING\",\"name\":\"AT_1\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"VIRAL\"}]},\"messages\":[],\"command\":\"DS_2#Id_1\",\"acomponent\":false,\"ascalar\":false},{\"result\":{\"id\":2,\"name\":\"DS_2\",\"description\":null,\"attributes\":[],\"persistent\":true,\"identifiers\":[{\"id\":5,\"type\":\"INTEGER\",\"name\":\"ID_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":6,\"type\":\"STRING\",\"name\":\"ID_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}],\"measures\":[{\"id\":7,\"type\":\"INTEGER\",\"name\":\"ME_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},{\"id\":8,\"type\":\"INTEGER\",\"name\":\"ME_2\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"viral\":[{\"id\":9,\"type\":\"STRING\",\"name\":\"AT_1\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"VIRAL\"}]},\"messages\":[],\"command\":\"DS_2\",\"acomponent\":false,\"ascalar\":false}]";
		List<ResultExpression> result = vtlTestUtils.getResult(commandStatements);
		List<ResultExpression> resultExpressionsToComapare = vtlTestUtils.convertJsonToResultExpression(jsonResult);
		assertNotNull("Conversione non riuscita", resultExpressionsToComapare);
		assertNotNull("Validazione Semantica non riuscita", result);
		assertTrue("I due risultati non sono uguali",
				vtlTestUtils.compareResult(result.get(0), resultExpressionsToComapare.get(0)));

	}

	@Test
	@Transactional
	public void membershipComponentAt() throws Exception {
		VtlDataset vtlDatasetFind = datasetRepository.findByNameIgnoreCase("DS_1");
		if (vtlDatasetFind == null) {
			VtlDataset vtlDataset = new VtlDataset();

			vtlDataset.setPersistent(true);
			vtlDataset.setName("DS_2");
			vtlDataset.setIsOnlyAScalar(false);
			vtlDataset.setTransitory(false);
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_1", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_2", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_1", VtlType.INTEGER, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_2", VtlType.INTEGER, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("AT_1", VtlType.STRING, VtlComponentRole.VIRAL));
			datasetRepository.save(vtlDataset);
		}
		String commandStatements = "DS_r := DS_2#At_1;";
		String jsonResult = "[{\"result\":{\"id\":42,\"name\":\"DS_r\",\"description\":\"DS_2\",\"attributes\":[],\"persistent\":false,\"identifiers\":[{\"id\":1,\"type\":\"INTEGER\",\"name\":\"ID_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":2,\"type\":\"STRING\",\"name\":\"ID_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}],\"measures\":[{\"id\":168,\"type\":\"STRING\",\"name\":\"string_var\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"viral\":[{\"id\":5,\"type\":\"STRING\",\"name\":\"AT_1\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"VIRAL\"}]},\"messages\":[],\"command\":\"DS_r:=DS_2#At_1;\",\"acomponent\":false,\"ascalar\":false},{\"result\":{\"id\":42,\"name\":\"DS_r\",\"description\":\"DS_2\",\"attributes\":[],\"persistent\":false,\"identifiers\":[{\"id\":1,\"type\":\"INTEGER\",\"name\":\"ID_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":2,\"type\":\"STRING\",\"name\":\"ID_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}],\"measures\":[{\"id\":168,\"type\":\"STRING\",\"name\":\"string_var\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"viral\":[{\"id\":5,\"type\":\"STRING\",\"name\":\"AT_1\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"VIRAL\"}]},\"messages\":[],\"command\":\"DS_2#At_1\",\"acomponent\":false,\"ascalar\":false},{\"result\":{\"id\":1,\"name\":\"DS_2\",\"description\":null,\"attributes\":[],\"persistent\":true,\"identifiers\":[{\"id\":1,\"type\":\"INTEGER\",\"name\":\"ID_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":2,\"type\":\"STRING\",\"name\":\"ID_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}],\"measures\":[{\"id\":3,\"type\":\"INTEGER\",\"name\":\"ME_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},{\"id\":4,\"type\":\"INTEGER\",\"name\":\"ME_2\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"viral\":[{\"id\":5,\"type\":\"STRING\",\"name\":\"AT_1\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"VIRAL\"}]},\"messages\":[],\"command\":\"DS_2\",\"acomponent\":false,\"ascalar\":false}]";
		List<ResultExpression> result = vtlTestUtils.getResult(commandStatements);
		List<ResultExpression> resultExpressionsToComapare = vtlTestUtils.convertJsonToResultExpression(jsonResult);
		assertNotNull("Conversione non riuscita", resultExpressionsToComapare);
		assertNotNull("Validazione Semantica non riuscita", result);
		assertTrue("I due risultati non sono uguali",
				vtlTestUtils.compareResult(result.get(0), resultExpressionsToComapare.get(0)));
	}

	@Test
	@Transactional
	public void callSumInt() throws Exception {
            UserFunction userFunction = new UserFunction();
            userFunction.setFunctionName("sum_int");
            userFunction.setFunctionType(VtlUserFunctionType.OPERATOR_FUNCTION);
            userFunction.setFunctionContent("define operator sum_int (x integer, y integer) " + "returns integer " + "is x + y " + "end operator;");
            userFunctionRepository.save(userFunction);

            String commandStatements = "DS_r := sum_int(2, 3);";
            String jsonResult = "[ { \"result\": { \"id\": 16, \"name\": \"DS_r\", \"description\": null, \"attributes\": [], \"identifiers\": [], \"measures\": [ { \"id\": 8, \"type\": \"INTEGER\", \"name\": \"int_var\", \"domainValue\": \"int_vd\", \"domainValueParent\": \"num_vd\", \"vtlComponentRole\": \"MEASURE\" } ], \"persistent\": false, \"viral\": [] }, \"resultComponent\": { \"id\": 8, \"type\": \"INTEGER\", \"name\": \"int_var\", \"domainValue\": \"int_vd\", \"domainValueParent\": \"num_vd\", \"vtlComponentRole\": \"MEASURE\" }, \"messages\": [], \"command\": \"DS_r:=sum_int(2,3)\", \"scalarComponent\": { \"id\": 8, \"type\": \"INTEGER\", \"name\": \"int_var\", \"domainValue\": \"int_vd\", \"domainValueParent\": \"num_vd\", \"vtlComponentRole\": \"MEASURE\" }, \"ascalar\": true, \"acomponent\": false, \"adataset\": false }, { \"result\": { \"id\": 14, \"name\": \"temporary_13\", \"description\": \"temporary_9\", \"attributes\": [], \"identifiers\": [], \"measures\": [ { \"id\": 7, \"type\": \"INTEGER\", \"name\": \"int_var\", \"domainValue\": \"int_vd\", \"domainValueParent\": \"num_vd\", \"vtlComponentRole\": \"MEASURE\" } ], \"persistent\": false, \"viral\": [] }, \"resultComponent\": { \"id\": 7, \"type\": \"INTEGER\", \"name\": \"int_var\", \"domainValue\": \"int_vd\", \"domainValueParent\": \"num_vd\", \"vtlComponentRole\": \"MEASURE\" }, \"messages\": [], \"command\": \"sum_int(2,3)\", \"scalarComponent\": { \"id\": 7, \"type\": \"INTEGER\", \"name\": \"int_var\", \"domainValue\": \"int_vd\", \"domainValueParent\": \"num_vd\", \"vtlComponentRole\": \"MEASURE\" }, \"ascalar\": true, \"acomponent\": false, \"adataset\": false }, { \"result\": { \"id\": 10, \"name\": \"temporary_9\", \"description\": null, \"attributes\": [], \"identifiers\": [], \"measures\": [ { \"id\": 5, \"type\": \"INTEGER\", \"name\": \"int_var\", \"domainValue\": \"int_vd\", \"domainValueParent\": \"num_vd\", \"vtlComponentRole\": \"MEASURE\" } ], \"persistent\": false, \"viral\": [] }, \"resultComponent\": { \"id\": 5, \"type\": \"INTEGER\", \"name\": \"int_var\", \"domainValue\": \"int_vd\", \"domainValueParent\": \"num_vd\", \"vtlComponentRole\": \"MEASURE\" }, \"messages\": [], \"command\": \"x+y\", \"scalarComponent\": { \"id\": 5, \"type\": \"INTEGER\", \"name\": \"int_var\", \"domainValue\": \"int_vd\", \"domainValueParent\": \"num_vd\", \"vtlComponentRole\": \"MEASURE\" }, \"ascalar\": true, \"acomponent\": false, \"adataset\": false }, { \"result\": { \"id\": 6, \"name\": \"temporary_5\", \"description\": null, \"attributes\": [], \"identifiers\": [], \"measures\": [ { \"id\": 3, \"type\": \"INTEGER\", \"name\": \"int_var\", \"domainValue\": \"int_vd\", \"domainValueParent\": \"num_vd\", \"vtlComponentRole\": \"MEASURE\" } ], \"persistent\": false, \"viral\": [] }, \"resultComponent\": { \"id\": 3, \"type\": \"INTEGER\", \"name\": \"int_var\", \"domainValue\": \"int_vd\", \"domainValueParent\": \"num_vd\", \"vtlComponentRole\": \"MEASURE\" }, \"messages\": [], \"command\": \"x\", \"scalarComponent\": { \"id\": 3, \"type\": \"INTEGER\", \"name\": \"int_var\", \"domainValue\": \"int_vd\", \"domainValueParent\": \"num_vd\", \"vtlComponentRole\": \"MEASURE\" }, \"ascalar\": true, \"acomponent\": false, \"adataset\": false }, { \"result\": { \"id\": 8, \"name\": \"temporary_7\", \"description\": null, \"attributes\": [], \"identifiers\": [], \"measures\": [ { \"id\": 4, \"type\": \"INTEGER\", \"name\": \"int_var\", \"domainValue\": \"int_vd\", \"domainValueParent\": \"num_vd\", \"vtlComponentRole\": \"MEASURE\" } ], \"persistent\": false, \"viral\": [] }, \"resultComponent\": { \"id\": 4, \"type\": \"INTEGER\", \"name\": \"int_var\", \"domainValue\": \"int_vd\", \"domainValueParent\": \"num_vd\", \"vtlComponentRole\": \"MEASURE\" }, \"messages\": [], \"command\": \"y\", \"scalarComponent\": { \"id\": 4, \"type\": \"INTEGER\", \"name\": \"int_var\", \"domainValue\": \"int_vd\", \"domainValueParent\": \"num_vd\", \"vtlComponentRole\": \"MEASURE\" }, \"ascalar\": true, \"acomponent\": false, \"adataset\": false } ]";
            List<ResultExpression> result = vtlTestUtils.getResult(commandStatements);
            List<ResultExpression> resultExpressionsToComapare = vtlTestUtils.convertJsonToResultExpression(jsonResult);
            assertNotNull("Conversione non riuscita", resultExpressionsToComapare);
            assertNotNull("Validazione Semantica non riuscita", result);
            assertTrue("I due risultati non sono uguali", vtlTestUtils.compareResult(result.get(0), resultExpressionsToComapare.get(0)));
	}
        
	@Test
	@Transactional
	public void callSumIntDefaultFirst() throws Exception {
            UserFunction userFunction = new UserFunction();
            userFunction.setFunctionName("sum_int");
            userFunction.setFunctionType(VtlUserFunctionType.OPERATOR_FUNCTION);
            userFunction.setFunctionContent("define operator sum_int (x integer default 0, y integer default 0) " + "returns integer " + "is x + y " + "end operator;");
            userFunctionRepository.save(userFunction);

            String commandStatements = "DS_r := sum_int(_, 3);";
            String jsonResult = "[ { \"result\": { \"id\": 16, \"name\": \"DS_r\", \"description\": null, \"attributes\": [], \"identifiers\": [], \"measures\": [ { \"id\": 8, \"type\": \"INTEGER\", \"name\": \"int_var\", \"domainValue\": \"int_vd\", \"domainValueParent\": \"num_vd\", \"vtlComponentRole\": \"MEASURE\" } ], \"persistent\": false, \"viral\": [] }, \"resultComponent\": { \"id\": 8, \"type\": \"INTEGER\", \"name\": \"int_var\", \"domainValue\": \"int_vd\", \"domainValueParent\": \"num_vd\", \"vtlComponentRole\": \"MEASURE\" }, \"messages\": [], \"command\": \"DS_r:=sum_int(2,3)\", \"scalarComponent\": { \"id\": 8, \"type\": \"INTEGER\", \"name\": \"int_var\", \"domainValue\": \"int_vd\", \"domainValueParent\": \"num_vd\", \"vtlComponentRole\": \"MEASURE\" }, \"ascalar\": true, \"acomponent\": false, \"adataset\": false }, { \"result\": { \"id\": 14, \"name\": \"temporary_13\", \"description\": \"temporary_9\", \"attributes\": [], \"identifiers\": [], \"measures\": [ { \"id\": 7, \"type\": \"INTEGER\", \"name\": \"int_var\", \"domainValue\": \"int_vd\", \"domainValueParent\": \"num_vd\", \"vtlComponentRole\": \"MEASURE\" } ], \"persistent\": false, \"viral\": [] }, \"resultComponent\": { \"id\": 7, \"type\": \"INTEGER\", \"name\": \"int_var\", \"domainValue\": \"int_vd\", \"domainValueParent\": \"num_vd\", \"vtlComponentRole\": \"MEASURE\" }, \"messages\": [], \"command\": \"sum_int(2,3)\", \"scalarComponent\": { \"id\": 7, \"type\": \"INTEGER\", \"name\": \"int_var\", \"domainValue\": \"int_vd\", \"domainValueParent\": \"num_vd\", \"vtlComponentRole\": \"MEASURE\" }, \"ascalar\": true, \"acomponent\": false, \"adataset\": false }, { \"result\": { \"id\": 10, \"name\": \"temporary_9\", \"description\": null, \"attributes\": [], \"identifiers\": [], \"measures\": [ { \"id\": 5, \"type\": \"INTEGER\", \"name\": \"int_var\", \"domainValue\": \"int_vd\", \"domainValueParent\": \"num_vd\", \"vtlComponentRole\": \"MEASURE\" } ], \"persistent\": false, \"viral\": [] }, \"resultComponent\": { \"id\": 5, \"type\": \"INTEGER\", \"name\": \"int_var\", \"domainValue\": \"int_vd\", \"domainValueParent\": \"num_vd\", \"vtlComponentRole\": \"MEASURE\" }, \"messages\": [], \"command\": \"x+y\", \"scalarComponent\": { \"id\": 5, \"type\": \"INTEGER\", \"name\": \"int_var\", \"domainValue\": \"int_vd\", \"domainValueParent\": \"num_vd\", \"vtlComponentRole\": \"MEASURE\" }, \"ascalar\": true, \"acomponent\": false, \"adataset\": false }, { \"result\": { \"id\": 6, \"name\": \"temporary_5\", \"description\": null, \"attributes\": [], \"identifiers\": [], \"measures\": [ { \"id\": 3, \"type\": \"INTEGER\", \"name\": \"int_var\", \"domainValue\": \"int_vd\", \"domainValueParent\": \"num_vd\", \"vtlComponentRole\": \"MEASURE\" } ], \"persistent\": false, \"viral\": [] }, \"resultComponent\": { \"id\": 3, \"type\": \"INTEGER\", \"name\": \"int_var\", \"domainValue\": \"int_vd\", \"domainValueParent\": \"num_vd\", \"vtlComponentRole\": \"MEASURE\" }, \"messages\": [], \"command\": \"x\", \"scalarComponent\": { \"id\": 3, \"type\": \"INTEGER\", \"name\": \"int_var\", \"domainValue\": \"int_vd\", \"domainValueParent\": \"num_vd\", \"vtlComponentRole\": \"MEASURE\" }, \"ascalar\": true, \"acomponent\": false, \"adataset\": false }, { \"result\": { \"id\": 8, \"name\": \"temporary_7\", \"description\": null, \"attributes\": [], \"identifiers\": [], \"measures\": [ { \"id\": 4, \"type\": \"INTEGER\", \"name\": \"int_var\", \"domainValue\": \"int_vd\", \"domainValueParent\": \"num_vd\", \"vtlComponentRole\": \"MEASURE\" } ], \"persistent\": false, \"viral\": [] }, \"resultComponent\": { \"id\": 4, \"type\": \"INTEGER\", \"name\": \"int_var\", \"domainValue\": \"int_vd\", \"domainValueParent\": \"num_vd\", \"vtlComponentRole\": \"MEASURE\" }, \"messages\": [], \"command\": \"y\", \"scalarComponent\": { \"id\": 4, \"type\": \"INTEGER\", \"name\": \"int_var\", \"domainValue\": \"int_vd\", \"domainValueParent\": \"num_vd\", \"vtlComponentRole\": \"MEASURE\" }, \"ascalar\": true, \"acomponent\": false, \"adataset\": false } ]";
            List<ResultExpression> result = vtlTestUtils.getResult(commandStatements);
            List<ResultExpression> resultExpressionsToComapare = vtlTestUtils.convertJsonToResultExpression(jsonResult);
            assertNotNull("Conversione non riuscita", resultExpressionsToComapare);
            assertNotNull("Validazione Semantica non riuscita", result);
            assertTrue("I due risultati non sono uguali", vtlTestUtils.compareResult(result.get(0), resultExpressionsToComapare.get(0)));
	}
        
	@Test
	@Transactional
	public void callSumIntDefaultSecond() throws Exception {
            UserFunction userFunction = new UserFunction();
            userFunction.setFunctionName("sum_int");
            userFunction.setFunctionType(VtlUserFunctionType.OPERATOR_FUNCTION);
            userFunction.setFunctionContent("define operator sum_int (x integer default 0, y integer default 0) " + "returns integer " + "is x + y " + "end operator;");
            userFunctionRepository.save(userFunction);

            String commandStatements = "DS_r := sum_int(1);";
            String jsonResult = "[ { \"result\": { \"id\": 16, \"name\": \"DS_r\", \"description\": null, \"attributes\": [], \"identifiers\": [], \"measures\": [ { \"id\": 8, \"type\": \"INTEGER\", \"name\": \"int_var\", \"domainValue\": \"int_vd\", \"domainValueParent\": \"num_vd\", \"vtlComponentRole\": \"MEASURE\" } ], \"persistent\": false, \"viral\": [] }, \"resultComponent\": { \"id\": 8, \"type\": \"INTEGER\", \"name\": \"int_var\", \"domainValue\": \"int_vd\", \"domainValueParent\": \"num_vd\", \"vtlComponentRole\": \"MEASURE\" }, \"messages\": [], \"command\": \"DS_r:=sum_int(2,3)\", \"scalarComponent\": { \"id\": 8, \"type\": \"INTEGER\", \"name\": \"int_var\", \"domainValue\": \"int_vd\", \"domainValueParent\": \"num_vd\", \"vtlComponentRole\": \"MEASURE\" }, \"ascalar\": true, \"acomponent\": false, \"adataset\": false }, { \"result\": { \"id\": 14, \"name\": \"temporary_13\", \"description\": \"temporary_9\", \"attributes\": [], \"identifiers\": [], \"measures\": [ { \"id\": 7, \"type\": \"INTEGER\", \"name\": \"int_var\", \"domainValue\": \"int_vd\", \"domainValueParent\": \"num_vd\", \"vtlComponentRole\": \"MEASURE\" } ], \"persistent\": false, \"viral\": [] }, \"resultComponent\": { \"id\": 7, \"type\": \"INTEGER\", \"name\": \"int_var\", \"domainValue\": \"int_vd\", \"domainValueParent\": \"num_vd\", \"vtlComponentRole\": \"MEASURE\" }, \"messages\": [], \"command\": \"sum_int(2,3)\", \"scalarComponent\": { \"id\": 7, \"type\": \"INTEGER\", \"name\": \"int_var\", \"domainValue\": \"int_vd\", \"domainValueParent\": \"num_vd\", \"vtlComponentRole\": \"MEASURE\" }, \"ascalar\": true, \"acomponent\": false, \"adataset\": false }, { \"result\": { \"id\": 10, \"name\": \"temporary_9\", \"description\": null, \"attributes\": [], \"identifiers\": [], \"measures\": [ { \"id\": 5, \"type\": \"INTEGER\", \"name\": \"int_var\", \"domainValue\": \"int_vd\", \"domainValueParent\": \"num_vd\", \"vtlComponentRole\": \"MEASURE\" } ], \"persistent\": false, \"viral\": [] }, \"resultComponent\": { \"id\": 5, \"type\": \"INTEGER\", \"name\": \"int_var\", \"domainValue\": \"int_vd\", \"domainValueParent\": \"num_vd\", \"vtlComponentRole\": \"MEASURE\" }, \"messages\": [], \"command\": \"x+y\", \"scalarComponent\": { \"id\": 5, \"type\": \"INTEGER\", \"name\": \"int_var\", \"domainValue\": \"int_vd\", \"domainValueParent\": \"num_vd\", \"vtlComponentRole\": \"MEASURE\" }, \"ascalar\": true, \"acomponent\": false, \"adataset\": false }, { \"result\": { \"id\": 6, \"name\": \"temporary_5\", \"description\": null, \"attributes\": [], \"identifiers\": [], \"measures\": [ { \"id\": 3, \"type\": \"INTEGER\", \"name\": \"int_var\", \"domainValue\": \"int_vd\", \"domainValueParent\": \"num_vd\", \"vtlComponentRole\": \"MEASURE\" } ], \"persistent\": false, \"viral\": [] }, \"resultComponent\": { \"id\": 3, \"type\": \"INTEGER\", \"name\": \"int_var\", \"domainValue\": \"int_vd\", \"domainValueParent\": \"num_vd\", \"vtlComponentRole\": \"MEASURE\" }, \"messages\": [], \"command\": \"x\", \"scalarComponent\": { \"id\": 3, \"type\": \"INTEGER\", \"name\": \"int_var\", \"domainValue\": \"int_vd\", \"domainValueParent\": \"num_vd\", \"vtlComponentRole\": \"MEASURE\" }, \"ascalar\": true, \"acomponent\": false, \"adataset\": false }, { \"result\": { \"id\": 8, \"name\": \"temporary_7\", \"description\": null, \"attributes\": [], \"identifiers\": [], \"measures\": [ { \"id\": 4, \"type\": \"INTEGER\", \"name\": \"int_var\", \"domainValue\": \"int_vd\", \"domainValueParent\": \"num_vd\", \"vtlComponentRole\": \"MEASURE\" } ], \"persistent\": false, \"viral\": [] }, \"resultComponent\": { \"id\": 4, \"type\": \"INTEGER\", \"name\": \"int_var\", \"domainValue\": \"int_vd\", \"domainValueParent\": \"num_vd\", \"vtlComponentRole\": \"MEASURE\" }, \"messages\": [], \"command\": \"y\", \"scalarComponent\": { \"id\": 4, \"type\": \"INTEGER\", \"name\": \"int_var\", \"domainValue\": \"int_vd\", \"domainValueParent\": \"num_vd\", \"vtlComponentRole\": \"MEASURE\" }, \"ascalar\": true, \"acomponent\": false, \"adataset\": false } ]";
            List<ResultExpression> result = vtlTestUtils.getResult(commandStatements);
            List<ResultExpression> resultExpressionsToComapare = vtlTestUtils.convertJsonToResultExpression(jsonResult);
            assertNotNull("Conversione non riuscita", resultExpressionsToComapare);
            assertNotNull("Validazione Semantica non riuscita", result);
            assertTrue("I due risultati non sono uguali", vtlTestUtils.compareResult(result.get(0), resultExpressionsToComapare.get(0)));
	}
        
	@Test
	@Transactional
	public void callSumDatasetInt() throws Exception {
            VtlDataset vtlDatasetFind = datasetRepository.findByNameIgnoreCase("DS_1");
            if (vtlDatasetFind == null) {
                    VtlDataset vtlDataset = new VtlDataset();

                    vtlDataset.setPersistent(true);
                    vtlDataset.setName("DS_1");
                    vtlDataset.setIsOnlyAScalar(false);
                    vtlDataset.setTransitory(false);
                    vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_1", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
                    vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_2", VtlType.STRING, VtlComponentRole.IDENTIFIER));
                    vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_1", VtlType.INTEGER, VtlComponentRole.MEASURE));
                    vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_2", VtlType.INTEGER, VtlComponentRole.MEASURE));
                    datasetRepository.save(vtlDataset);
            }
            
            UserFunction userFunction = new UserFunction();
            userFunction.setFunctionName("sum_dataset_int");
            userFunction.setFunctionType(VtlUserFunctionType.OPERATOR_FUNCTION);
            userFunction.setFunctionContent("define operator sum_dataset_int (x dataset, y integer default 0) " + "returns dataset " + "is x + y " + "end operator;");
            userFunctionRepository.save(userFunction);

            String commandStatements = "DS_r := sum_dataset_int(DS_1, 3);";
            String jsonResult = "[ { \"result\": { \"id\": 53, \"name\": \"DS_r\", \"description\": null, \"attributes\": [], \"identifiers\": [ { \"id\": 191, \"type\": \"INTEGER\", \"name\": \"ID_1\", \"domainValue\": \"int_vd\", \"domainValueParent\": \"num_vd\", \"vtlComponentRole\": \"IDENTIFIER\" }, { \"id\": 192, \"type\": \"STRING\", \"name\": \"ID_2\", \"domainValue\": \"string_vd\", \"domainValueParent\": \"string_vd\", \"vtlComponentRole\": \"IDENTIFIER\" } ], \"viral\": [], \"measures\": [ { \"id\": 189, \"type\": \"INTEGER\", \"name\": \"ME_1\", \"domainValue\": \"int_vd\", \"domainValueParent\": \"num_vd\", \"vtlComponentRole\": \"MEASURE\" }, { \"id\": 190, \"type\": \"INTEGER\", \"name\": \"ME_2\", \"domainValue\": \"int_vd\", \"domainValueParent\": \"num_vd\", \"vtlComponentRole\": \"MEASURE\" } ], \"persistent\": false }, \"messages\": [], \"command\": \"DS_r:=sum_dataset_int(DS_1,3)\", \"adataset\": true, \"acomponent\": false, \"ascalar\": false }, { \"result\": { \"id\": 51, \"name\": \"temporary_50\", \"description\": null, \"attributes\": [], \"identifiers\": [ { \"id\": 187, \"type\": \"INTEGER\", \"name\": \"ID_1\", \"domainValue\": \"int_vd\", \"domainValueParent\": \"num_vd\", \"vtlComponentRole\": \"IDENTIFIER\" }, { \"id\": 188, \"type\": \"STRING\", \"name\": \"ID_2\", \"domainValue\": \"string_vd\", \"domainValueParent\": \"string_vd\", \"vtlComponentRole\": \"IDENTIFIER\" } ], \"viral\": [], \"measures\": [ { \"id\": 185, \"type\": \"INTEGER\", \"name\": \"ME_1\", \"domainValue\": \"int_vd\", \"domainValueParent\": \"num_vd\", \"vtlComponentRole\": \"MEASURE\" }, { \"id\": 186, \"type\": \"INTEGER\", \"name\": \"ME_2\", \"domainValue\": \"int_vd\", \"domainValueParent\": \"num_vd\", \"vtlComponentRole\": \"MEASURE\" } ], \"persistent\": false }, \"messages\": [], \"command\": \"sum_dataset_int(DS_1,3)\", \"adataset\": true, \"acomponent\": false, \"ascalar\": false }, { \"result\": { \"id\": 1, \"name\": \"DS_1\", \"description\": null, \"attributes\": [], \"identifiers\": [ { \"id\": 1, \"type\": \"INTEGER\", \"name\": \"ID_1\", \"domainValue\": \"int_vd\", \"domainValueParent\": \"num_vd\", \"vtlComponentRole\": \"IDENTIFIER\" }, { \"id\": 2, \"type\": \"STRING\", \"name\": \"ID_2\", \"domainValue\": \"string_vd\", \"domainValueParent\": \"string_vd\", \"vtlComponentRole\": \"IDENTIFIER\" } ], \"viral\": [], \"measures\": [ { \"id\": 3, \"type\": \"INTEGER\", \"name\": \"ME_1\", \"domainValue\": \"int_vd\", \"domainValueParent\": \"num_vd\", \"vtlComponentRole\": \"MEASURE\" }, { \"id\": 4, \"type\": \"INTEGER\", \"name\": \"ME_2\", \"domainValue\": \"int_vd\", \"domainValueParent\": \"num_vd\", \"vtlComponentRole\": \"MEASURE\" } ], \"persistent\": true }, \"messages\": [], \"command\": \"x\", \"adataset\": true, \"acomponent\": false, \"ascalar\": false }, { \"result\": { \"id\": 49, \"name\": \"temporary_48\", \"description\": null, \"attributes\": [], \"identifiers\": [], \"viral\": [], \"measures\": [ { \"id\": 184, \"type\": \"INTEGER\", \"name\": \"int_var\", \"domainValue\": \"int_vd\", \"domainValueParent\": \"num_vd\", \"vtlComponentRole\": \"MEASURE\" } ], \"persistent\": false }, \"resultComponent\": { \"id\": 184, \"type\": \"INTEGER\", \"name\": \"int_var\", \"domainValue\": \"int_vd\", \"domainValueParent\": \"num_vd\", \"vtlComponentRole\": \"MEASURE\" }, \"messages\": [], \"command\": \"y\", \"scalarComponent\": { \"id\": 184, \"type\": \"INTEGER\", \"name\": \"int_var\", \"domainValue\": \"int_vd\", \"domainValueParent\": \"num_vd\", \"vtlComponentRole\": \"MEASURE\" }, \"adataset\": false, \"acomponent\": false, \"ascalar\": true } ]";
            List<ResultExpression> result = vtlTestUtils.getResult(commandStatements);
            List<ResultExpression> resultExpressionsToComapare = vtlTestUtils.convertJsonToResultExpression(jsonResult);
            assertNotNull("Conversione non riuscita", resultExpressionsToComapare);
            assertNotNull("Validazione Semantica non riuscita", result);
            assertTrue("I due risultati non sono uguali", vtlTestUtils.compareResult(result.get(0), resultExpressionsToComapare.get(0)));
	}
        
	@Test
	@Transactional
	public void callSumDataset() throws Exception {
            VtlDataset vtlDatasetFind = datasetRepository.findByNameIgnoreCase("DS_1");
            if (vtlDatasetFind == null) {
                    VtlDataset vtlDataset = new VtlDataset();

                    vtlDataset.setPersistent(true);
                    vtlDataset.setName("DS_1");
                    vtlDataset.setIsOnlyAScalar(false);
                    vtlDataset.setTransitory(false);
                    vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_1", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
                    vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_2", VtlType.STRING, VtlComponentRole.IDENTIFIER));
                    vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_1", VtlType.INTEGER, VtlComponentRole.MEASURE));
                    vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_2", VtlType.INTEGER, VtlComponentRole.MEASURE));
                    datasetRepository.save(vtlDataset);
                    
                    VtlDataset vtlDataset2 = new VtlDataset();

                    vtlDataset2.setPersistent(true);
                    vtlDataset2.setName("DS_2");
                    vtlDataset2.setIsOnlyAScalar(false);
                    vtlDataset2.setTransitory(false);
                    vtlDataset2.addComponent(vtlTestUtils.getVtlComponent("ID_1", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
                    vtlDataset2.addComponent(vtlTestUtils.getVtlComponent("ID_2", VtlType.STRING, VtlComponentRole.IDENTIFIER));
                    vtlDataset2.addComponent(vtlTestUtils.getVtlComponent("ME_1", VtlType.INTEGER, VtlComponentRole.MEASURE));
                    vtlDataset2.addComponent(vtlTestUtils.getVtlComponent("ME_2", VtlType.INTEGER, VtlComponentRole.MEASURE));
                    vtlDataset2.addComponent(vtlTestUtils.getVtlComponent("AT_1", VtlType.STRING, VtlComponentRole.VIRAL));
                    datasetRepository.save(vtlDataset2);                    
            }
            
            UserFunction userFunction = new UserFunction();
            userFunction.setFunctionName("sum_dataset");
            userFunction.setFunctionType(VtlUserFunctionType.OPERATOR_FUNCTION);
            userFunction.setFunctionContent("define operator sum_dataset (x dataset, y dataset) " + "returns dataset " + "is x + y " + "end operator;");
            userFunctionRepository.save(userFunction);

            String commandStatements = "DS_r := sum_dataset(DS_1, DS_2);";
            String jsonResult = "[ { \"result\": { \"id\": 47, \"name\": \"DS_r\", \"description\": null, \"attributes\": [], \"identifiers\": [ { \"id\": 180, \"type\": \"INTEGER\", \"name\": \"ID_1\", \"domainValue\": \"int_vd\", \"domainValueParent\": \"num_vd\", \"vtlComponentRole\": \"IDENTIFIER\" }, { \"id\": 181, \"type\": \"STRING\", \"name\": \"ID_2\", \"domainValue\": \"string_vd\", \"domainValueParent\": \"string_vd\", \"vtlComponentRole\": \"IDENTIFIER\" } ], \"viral\": [], \"measures\": [ { \"id\": 182, \"type\": \"INTEGER\", \"name\": \"ME_1\", \"domainValue\": \"int_vd\", \"domainValueParent\": \"num_vd\", \"vtlComponentRole\": \"MEASURE\" }, { \"id\": 183, \"type\": \"INTEGER\", \"name\": \"ME_2\", \"domainValue\": \"int_vd\", \"domainValueParent\": \"num_vd\", \"vtlComponentRole\": \"MEASURE\" } ], \"persistent\": false }, \"messages\": [], \"command\": \"DS_r:=sum_dataset(DS_1,DS_2)\", \"adataset\": true, \"acomponent\": false, \"ascalar\": false }, { \"result\": { \"id\": 45, \"name\": \"temporary_44\", \"description\": null, \"attributes\": [], \"identifiers\": [ { \"id\": 176, \"type\": \"INTEGER\", \"name\": \"ID_1\", \"domainValue\": \"int_vd\", \"domainValueParent\": \"num_vd\", \"vtlComponentRole\": \"IDENTIFIER\" }, { \"id\": 177, \"type\": \"STRING\", \"name\": \"ID_2\", \"domainValue\": \"string_vd\", \"domainValueParent\": \"string_vd\", \"vtlComponentRole\": \"IDENTIFIER\" } ], \"viral\": [], \"measures\": [ { \"id\": 178, \"type\": \"INTEGER\", \"name\": \"ME_1\", \"domainValue\": \"int_vd\", \"domainValueParent\": \"num_vd\", \"vtlComponentRole\": \"MEASURE\" }, { \"id\": 179, \"type\": \"INTEGER\", \"name\": \"ME_2\", \"domainValue\": \"int_vd\", \"domainValueParent\": \"num_vd\", \"vtlComponentRole\": \"MEASURE\" } ], \"persistent\": false }, \"messages\": [], \"command\": \"sum_dataset(DS_1,DS_2)\", \"adataset\": true, \"acomponent\": false, \"ascalar\": false }, { \"result\": { \"id\": 1, \"name\": \"DS_1\", \"description\": null, \"attributes\": [], \"identifiers\": [ { \"id\": 1, \"type\": \"INTEGER\", \"name\": \"ID_1\", \"domainValue\": \"int_vd\", \"domainValueParent\": \"num_vd\", \"vtlComponentRole\": \"IDENTIFIER\" }, { \"id\": 2, \"type\": \"STRING\", \"name\": \"ID_2\", \"domainValue\": \"string_vd\", \"domainValueParent\": \"string_vd\", \"vtlComponentRole\": \"IDENTIFIER\" } ], \"viral\": [], \"measures\": [ { \"id\": 3, \"type\": \"INTEGER\", \"name\": \"ME_1\", \"domainValue\": \"int_vd\", \"domainValueParent\": \"num_vd\", \"vtlComponentRole\": \"MEASURE\" }, { \"id\": 4, \"type\": \"INTEGER\", \"name\": \"ME_2\", \"domainValue\": \"int_vd\", \"domainValueParent\": \"num_vd\", \"vtlComponentRole\": \"MEASURE\" } ], \"persistent\": true }, \"messages\": [], \"command\": \"x\", \"adataset\": true, \"acomponent\": false, \"ascalar\": false }, { \"result\": { \"id\": 2, \"name\": \"DS_2\", \"description\": null, \"attributes\": [], \"identifiers\": [ { \"id\": 5, \"type\": \"INTEGER\", \"name\": \"ID_1\", \"domainValue\": \"int_vd\", \"domainValueParent\": \"num_vd\", \"vtlComponentRole\": \"IDENTIFIER\" }, { \"id\": 6, \"type\": \"STRING\", \"name\": \"ID_2\", \"domainValue\": \"string_vd\", \"domainValueParent\": \"string_vd\", \"vtlComponentRole\": \"IDENTIFIER\" } ], \"viral\": [], \"measures\": [ { \"id\": 7, \"type\": \"INTEGER\", \"name\": \"ME_1\", \"domainValue\": \"int_vd\", \"domainValueParent\": \"num_vd\", \"vtlComponentRole\": \"MEASURE\" }, { \"id\": 8, \"type\": \"INTEGER\", \"name\": \"ME_2\", \"domainValue\": \"int_vd\", \"domainValueParent\": \"num_vd\", \"vtlComponentRole\": \"MEASURE\" } ], \"persistent\": true }, \"messages\": [], \"command\": \"y\", \"adataset\": true, \"acomponent\": false, \"ascalar\": false } ]";
            List<ResultExpression> result = vtlTestUtils.getResult(commandStatements);
            List<ResultExpression> resultExpressionsToComapare = vtlTestUtils.convertJsonToResultExpression(jsonResult);
            assertNotNull("Conversione non riuscita", resultExpressionsToComapare);
            assertNotNull("Validazione Semantica non riuscita", result);
            assertTrue("I due risultati non sono uguali", vtlTestUtils.compareResult(result.get(0), resultExpressionsToComapare.get(0)));
	}
        
	@Test
	@Transactional
	public void callMaxInt() throws Exception {
            UserFunction userFunction = new UserFunction();
            userFunction.setFunctionName("max_int");
            userFunction.setFunctionType(VtlUserFunctionType.OPERATOR_FUNCTION);
            userFunction.setFunctionContent("define operator max_int (x integer, y integer) " + "returns integer " + "is if x > y then x else y " + "end operator;");
            userFunctionRepository.save(userFunction);

            String commandStatements = "DS_r := max_int(2, 3);";
            String jsonResult = "[ { \"result\": { \"id\": 16, \"name\": \"DS_r\", \"description\": null, \"attributes\": [], \"identifiers\": [], \"measures\": [ { \"id\": 8, \"type\": \"INTEGER\", \"name\": \"int_var\", \"domainValue\": \"int_vd\", \"domainValueParent\": \"num_vd\", \"vtlComponentRole\": \"MEASURE\" } ], \"persistent\": false, \"viral\": [] }, \"resultComponent\": { \"id\": 8, \"type\": \"INTEGER\", \"name\": \"int_var\", \"domainValue\": \"int_vd\", \"domainValueParent\": \"num_vd\", \"vtlComponentRole\": \"MEASURE\" }, \"messages\": [], \"command\": \"DS_r:=max_int(2,3)\", \"scalarComponent\": { \"id\": 8, \"type\": \"INTEGER\", \"name\": \"int_var\", \"domainValue\": \"int_vd\", \"domainValueParent\": \"num_vd\", \"vtlComponentRole\": \"MEASURE\" }, \"ascalar\": true, \"acomponent\": false, \"adataset\": false }, { \"result\": { \"id\": 14, \"name\": \"temporary_13\", \"description\": \"temporary_9\", \"attributes\": [], \"identifiers\": [], \"measures\": [ { \"id\": 7, \"type\": \"INTEGER\", \"name\": \"int_var\", \"domainValue\": \"int_vd\", \"domainValueParent\": \"num_vd\", \"vtlComponentRole\": \"MEASURE\" } ], \"persistent\": false, \"viral\": [] }, \"resultComponent\": { \"id\": 7, \"type\": \"INTEGER\", \"name\": \"int_var\", \"domainValue\": \"int_vd\", \"domainValueParent\": \"num_vd\", \"vtlComponentRole\": \"MEASURE\" }, \"messages\": [], \"command\": \"max_int(2,3)\", \"scalarComponent\": { \"id\": 7, \"type\": \"INTEGER\", \"name\": \"int_var\", \"domainValue\": \"int_vd\", \"domainValueParent\": \"num_vd\", \"vtlComponentRole\": \"MEASURE\" }, \"ascalar\": true, \"acomponent\": false, \"adataset\": false }, { \"result\": { \"id\": 10, \"name\": \"temporary_9\", \"description\": null, \"attributes\": [], \"identifiers\": [], \"measures\": [ { \"id\": 5, \"type\": \"INTEGER\", \"name\": \"int_var\", \"domainValue\": \"int_vd\", \"domainValueParent\": \"num_vd\", \"vtlComponentRole\": \"MEASURE\" } ], \"persistent\": false, \"viral\": [] }, \"resultComponent\": { \"id\": 5, \"type\": \"INTEGER\", \"name\": \"int_var\", \"domainValue\": \"int_vd\", \"domainValueParent\": \"num_vd\", \"vtlComponentRole\": \"MEASURE\" }, \"messages\": [], \"command\": \"x+y\", \"scalarComponent\": { \"id\": 5, \"type\": \"INTEGER\", \"name\": \"int_var\", \"domainValue\": \"int_vd\", \"domainValueParent\": \"num_vd\", \"vtlComponentRole\": \"MEASURE\" }, \"ascalar\": true, \"acomponent\": false, \"adataset\": false }, { \"result\": { \"id\": 6, \"name\": \"temporary_5\", \"description\": null, \"attributes\": [], \"identifiers\": [], \"measures\": [ { \"id\": 3, \"type\": \"INTEGER\", \"name\": \"int_var\", \"domainValue\": \"int_vd\", \"domainValueParent\": \"num_vd\", \"vtlComponentRole\": \"MEASURE\" } ], \"persistent\": false, \"viral\": [] }, \"resultComponent\": { \"id\": 3, \"type\": \"INTEGER\", \"name\": \"int_var\", \"domainValue\": \"int_vd\", \"domainValueParent\": \"num_vd\", \"vtlComponentRole\": \"MEASURE\" }, \"messages\": [], \"command\": \"x\", \"scalarComponent\": { \"id\": 3, \"type\": \"INTEGER\", \"name\": \"int_var\", \"domainValue\": \"int_vd\", \"domainValueParent\": \"num_vd\", \"vtlComponentRole\": \"MEASURE\" }, \"ascalar\": true, \"acomponent\": false, \"adataset\": false }, { \"result\": { \"id\": 8, \"name\": \"temporary_7\", \"description\": null, \"attributes\": [], \"identifiers\": [], \"measures\": [ { \"id\": 4, \"type\": \"INTEGER\", \"name\": \"int_var\", \"domainValue\": \"int_vd\", \"domainValueParent\": \"num_vd\", \"vtlComponentRole\": \"MEASURE\" } ], \"persistent\": false, \"viral\": [] }, \"resultComponent\": { \"id\": 4, \"type\": \"INTEGER\", \"name\": \"int_var\", \"domainValue\": \"int_vd\", \"domainValueParent\": \"num_vd\", \"vtlComponentRole\": \"MEASURE\" }, \"messages\": [], \"command\": \"y\", \"scalarComponent\": { \"id\": 4, \"type\": \"INTEGER\", \"name\": \"int_var\", \"domainValue\": \"int_vd\", \"domainValueParent\": \"num_vd\", \"vtlComponentRole\": \"MEASURE\" }, \"ascalar\": true, \"acomponent\": false, \"adataset\": false } ]";
            List<ResultExpression> result = vtlTestUtils.getResult(commandStatements);
            List<ResultExpression> resultExpressionsToComapare = vtlTestUtils.convertJsonToResultExpression(jsonResult);
            assertNotNull("Conversione non riuscita", resultExpressionsToComapare);
            assertNotNull("Validazione Semantica non riuscita", result);
            assertTrue("I due risultati non sono uguali", vtlTestUtils.compareResult(result.get(0), resultExpressionsToComapare.get(0)));
	}        
}
