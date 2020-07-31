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
public class VtlAggregateAnalyticOperatorsTest {

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
	public void avgGroupBy() throws Exception {
		VtlDataset vtlDatasetFind = datasetRepository.findByNameIgnoreCase("DS_1");
		if (vtlDatasetFind == null) {
			VtlDataset vtlDataset = new VtlDataset();

			vtlDataset.setPersistent(true);
			vtlDataset.setName("DS_29");
			vtlDataset.setIsOnlyAScalar(false);
			vtlDataset.setTransitory(false);
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_1", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_2", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_3", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_1", VtlType.INTEGER, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("AT_1", VtlType.STRING, VtlComponentRole.VIRAL));
			datasetRepository.save(vtlDataset);
		}
		String commandStatements = "DS_r := avg ( DS_29  group by  Id_1 );";
		String jsonResult = "[{\"result\":{\"id\":9,\"name\":\"DS_r\",\"description\":null,\"attributes\":[],\"persistent\":false,\"identifiers\":[{\"id\":16,\"type\":\"INTEGER\",\"name\":\"ID_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}],\"measures\":[{\"id\":17,\"type\":\"NUMBER\",\"name\":\"ME_1\",\"domainValue\":\"num_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"viral\":[]},\"messages\":[],\"command\":\"DS_r:=avg(DS_29groupbyId_1)\",\"acomponent\":false,\"ascalar\":false,\"adataset\":true},{\"result\":{\"id\":7,\"name\":\"temporary_6\",\"description\":null,\"attributes\":[],\"persistent\":false,\"identifiers\":[{\"id\":14,\"type\":\"INTEGER\",\"name\":\"ID_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}],\"measures\":[{\"id\":15,\"type\":\"NUMBER\",\"name\":\"ME_1\",\"domainValue\":\"num_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"viral\":[]},\"messages\":[],\"command\":\"groupbyId_1\",\"acomponent\":false,\"ascalar\":false,\"adataset\":true},{\"resultComponent\":{\"id\":10,\"type\":\"INTEGER\",\"name\":\"ID_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},\"messages\":[],\"command\":\"Id_1\",\"acomponent\":true,\"ascalar\":false,\"adataset\":false},{\"result\":{\"id\":5,\"name\":\"temporary_4\",\"description\":\"temporary_2\",\"attributes\":[],\"persistent\":false,\"identifiers\":[{\"id\":10,\"type\":\"INTEGER\",\"name\":\"ID_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":11,\"type\":\"STRING\",\"name\":\"ID_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":12,\"type\":\"STRING\",\"name\":\"ID_3\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}],\"measures\":[{\"id\":13,\"type\":\"NUMBER\",\"name\":\"ME_1\",\"domainValue\":\"num_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"viral\":[]},\"messages\":[],\"acomponent\":false,\"ascalar\":false,\"adataset\":true},{\"result\":{\"id\":1,\"name\":\"DS_29\",\"description\":null,\"attributes\":[],\"persistent\":true,\"identifiers\":[{\"id\":1,\"type\":\"INTEGER\",\"name\":\"ID_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":2,\"type\":\"STRING\",\"name\":\"ID_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":3,\"type\":\"STRING\",\"name\":\"ID_3\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}],\"measures\":[{\"id\":4,\"type\":\"INTEGER\",\"name\":\"ME_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"viral\":[{\"id\":5,\"type\":\"STRING\",\"name\":\"AT_1\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"VIRAL\"}]},\"messages\":[],\"command\":\"DS_29\",\"acomponent\":false,\"ascalar\":false,\"adataset\":true}]";
		List<ResultExpression> result = vtlTestUtils.getResult(commandStatements);
		System.out.println(vtlTestUtils.convertResultToJson(result));

		List<ResultExpression> resultExpressionsToComapare = vtlTestUtils.convertJsonToResultExpression(jsonResult);
		assertNotNull("Conversione non riuscita", resultExpressionsToComapare);
		assertNotNull("Validazione Semantica non riuscita", result);
		assertTrue("I due risultati non sono uguali",
				vtlTestUtils.compareResult(result.get(0), resultExpressionsToComapare.get(0)));
	}

	@Test
	@Transactional
	public void avgGroupExcept() throws Exception {
		VtlDataset vtlDatasetFind = datasetRepository.findByNameIgnoreCase("DS_1");
		if (vtlDatasetFind == null) {
			VtlDataset vtlDataset = new VtlDataset();

			vtlDataset.setPersistent(true);
			vtlDataset.setName("DS_29");
			vtlDataset.setIsOnlyAScalar(false);
			vtlDataset.setTransitory(false);
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_1", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_2", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_3", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_1", VtlType.INTEGER, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("AT_1", VtlType.STRING, VtlComponentRole.VIRAL));
			datasetRepository.save(vtlDataset);
		}
		String commandStatements = "DS_r := avg ( DS_29  group except   Id_2,  Id_3 );";
		String jsonResult = "[{\"result\":{\"id\":180,\"name\":\"DS_r\",\"description\":null,\"attributes\":[],\"persistent\":false,\"viral\":[],\"measures\":[{\"id\":519,\"type\":\"NUMBER\",\"name\":\"ME_1\",\"domainValue\":\"num_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"identifiers\":[{\"id\":518,\"type\":\"INTEGER\",\"name\":\"ID_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}]},\"messages\":[],\"command\":\"DS_r:=avg(DS_29groupexceptId_2,Id_3)\",\"adataset\":true,\"acomponent\":false,\"ascalar\":false},{\"result\":{\"id\":178,\"name\":\"temporary_177\",\"description\":null,\"attributes\":[],\"persistent\":false,\"viral\":[],\"measures\":[{\"id\":517,\"type\":\"NUMBER\",\"name\":\"ME_1\",\"domainValue\":\"num_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"identifiers\":[{\"id\":516,\"type\":\"INTEGER\",\"name\":\"ID_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}]},\"messages\":[],\"command\":\"groupexceptId_2,Id_3\",\"adataset\":true,\"acomponent\":false,\"ascalar\":false},{\"resultComponent\":{\"id\":514,\"type\":\"STRING\",\"name\":\"ID_3\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},\"messages\":[],\"command\":\"Id_3\",\"adataset\":false,\"acomponent\":true,\"ascalar\":false},{\"resultComponent\":{\"id\":513,\"type\":\"STRING\",\"name\":\"ID_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},\"messages\":[],\"command\":\"Id_2\",\"adataset\":false,\"acomponent\":true,\"ascalar\":false},{\"result\":{\"id\":176,\"name\":\"temporary_175\",\"description\":\"temporary_173\",\"attributes\":[],\"persistent\":false,\"viral\":[],\"measures\":[{\"id\":515,\"type\":\"NUMBER\",\"name\":\"ME_1\",\"domainValue\":\"num_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"identifiers\":[{\"id\":512,\"type\":\"INTEGER\",\"name\":\"ID_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":513,\"type\":\"STRING\",\"name\":\"ID_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":514,\"type\":\"STRING\",\"name\":\"ID_3\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}]},\"messages\":[],\"adataset\":true,\"acomponent\":false,\"ascalar\":false},{\"result\":{\"id\":172,\"name\":\"DS_29\",\"description\":null,\"attributes\":[],\"persistent\":true,\"viral\":[{\"id\":507,\"type\":\"STRING\",\"name\":\"AT_1\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"VIRAL\"}],\"measures\":[{\"id\":506,\"type\":\"INTEGER\",\"name\":\"ME_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"identifiers\":[{\"id\":503,\"type\":\"INTEGER\",\"name\":\"ID_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":504,\"type\":\"STRING\",\"name\":\"ID_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":505,\"type\":\"STRING\",\"name\":\"ID_3\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}]},\"messages\":[],\"command\":\"DS_29\",\"adataset\":true,\"acomponent\":false,\"ascalar\":false}]";
		List<ResultExpression> result = vtlTestUtils.getResult(commandStatements);
		System.out.println(vtlTestUtils.convertResultToJson(result));

		List<ResultExpression> resultExpressionsToComapare = vtlTestUtils.convertJsonToResultExpression(jsonResult);
		assertNotNull("Conversione non riuscita", resultExpressionsToComapare);
		assertNotNull("Validazione Semantica non riuscita", result);
		assertTrue("I due risultati non sono uguali",
				vtlTestUtils.compareResult(result.get(0), resultExpressionsToComapare.get(0)));
	}

	@Test
	@Transactional
	public void avgComponent() throws Exception {
		VtlDataset vtlDatasetFind = datasetRepository.findByNameIgnoreCase("DS_1");
		if (vtlDatasetFind == null) {
			VtlDataset vtlDataset = new VtlDataset();

			vtlDataset.setPersistent(true);
			vtlDataset.setName("DS_29");
			vtlDataset.setIsOnlyAScalar(false);
			vtlDataset.setTransitory(false);
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_1", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_2", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_3", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_1", VtlType.INTEGER, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("AT_1", VtlType.STRING, VtlComponentRole.VIRAL));
			datasetRepository.save(vtlDataset);
		}
		String commandStatements = "DS_r := avg ( DS_29#Me_1  group by  Id_1 );";
		String jsonResult = "[{\"result\":{\"id\":11,\"name\":\"DS_r\",\"description\":null,\"attributes\":[],\"persistent\":false,\"measures\":[{\"id\":22,\"type\":\"NUMBER\",\"name\":\"ME_1\",\"domainValue\":\"num_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"viral\":[],\"identifiers\":[{\"id\":21,\"type\":\"INTEGER\",\"name\":\"ID_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}]},\"messages\":[],\"command\":\"DS_r:=avg(DS_29#Me_1groupbyId_1)\",\"acomponent\":false,\"ascalar\":false,\"adataset\":true},{\"result\":{\"id\":9,\"name\":\"temporary_8\",\"description\":null,\"attributes\":[],\"persistent\":false,\"measures\":[{\"id\":20,\"type\":\"NUMBER\",\"name\":\"ME_1\",\"domainValue\":\"num_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"viral\":[],\"identifiers\":[{\"id\":19,\"type\":\"INTEGER\",\"name\":\"ID_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}]},\"messages\":[],\"command\":\"groupbyId_1\",\"acomponent\":false,\"ascalar\":false,\"adataset\":true},{\"resultComponent\":{\"id\":15,\"type\":\"INTEGER\",\"name\":\"ID_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},\"messages\":[],\"command\":\"Id_1\",\"acomponent\":true,\"ascalar\":false,\"adataset\":false},{\"result\":{\"id\":7,\"name\":\"temporary_6\",\"description\":\"temporary_4\",\"attributes\":[],\"persistent\":false,\"measures\":[{\"id\":18,\"type\":\"NUMBER\",\"name\":\"ME_1\",\"domainValue\":\"num_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"viral\":[],\"identifiers\":[{\"id\":15,\"type\":\"INTEGER\",\"name\":\"ID_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":16,\"type\":\"STRING\",\"name\":\"ID_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":17,\"type\":\"STRING\",\"name\":\"ID_3\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}]},\"messages\":[],\"acomponent\":false,\"ascalar\":false,\"adataset\":true},{\"result\":{\"id\":3,\"name\":\"temporary_2\",\"description\":\"DS_29\",\"attributes\":[],\"persistent\":false,\"measures\":[{\"id\":9,\"type\":\"INTEGER\",\"name\":\"ME_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"viral\":[{\"id\":10,\"type\":\"STRING\",\"name\":\"AT_1\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"VIRAL\"}],\"identifiers\":[{\"id\":6,\"type\":\"INTEGER\",\"name\":\"ID_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":7,\"type\":\"STRING\",\"name\":\"ID_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":8,\"type\":\"STRING\",\"name\":\"ID_3\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}]},\"messages\":[],\"command\":\"DS_29#Me_1\",\"acomponent\":false,\"ascalar\":false,\"adataset\":true},{\"result\":{\"id\":1,\"name\":\"DS_29\",\"description\":null,\"attributes\":[],\"persistent\":true,\"measures\":[{\"id\":4,\"type\":\"INTEGER\",\"name\":\"ME_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"viral\":[{\"id\":5,\"type\":\"STRING\",\"name\":\"AT_1\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"VIRAL\"}],\"identifiers\":[{\"id\":1,\"type\":\"INTEGER\",\"name\":\"ID_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":2,\"type\":\"STRING\",\"name\":\"ID_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":3,\"type\":\"STRING\",\"name\":\"ID_3\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}]},\"messages\":[],\"command\":\"DS_29\",\"acomponent\":false,\"ascalar\":false,\"adataset\":true}]";
		List<ResultExpression> result = vtlTestUtils.getResult(commandStatements);
		System.out.println(vtlTestUtils.convertResultToJson(result));
		List<ResultExpression> resultExpressionsToComapare = vtlTestUtils.convertJsonToResultExpression(jsonResult);
		assertNotNull("Conversione non riuscita", resultExpressionsToComapare);
		assertNotNull("Validazione Semantica non riuscita", result);
		assertTrue("I due risultati non sono uguali",
				vtlTestUtils.compareResult(result.get(0), resultExpressionsToComapare.get(0)));
	}

	@Test
	@Transactional
	public void avgDs() throws Exception {
		VtlDataset vtlDatasetFind = datasetRepository.findByNameIgnoreCase("DS_1");
		if (vtlDatasetFind == null) {
			VtlDataset vtlDataset = new VtlDataset();

			vtlDataset.setPersistent(true);
			vtlDataset.setName("DS_29");
			vtlDataset.setIsOnlyAScalar(false);
			vtlDataset.setTransitory(false);
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_1", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_2", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_3", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_1", VtlType.INTEGER, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("AT_1", VtlType.STRING, VtlComponentRole.VIRAL));
			datasetRepository.save(vtlDataset);
		}
		String commandStatements = "DS_r := avg ( DS_29 );";
		String jsonResult = "[{\"result\":{\"id\":9,\"name\":\"DS_r\",\"description\":null,\"attributes\":[],\"persistent\":false,\"viral\":[],\"measures\":[{\"id\":15,\"type\":\"NUMBER\",\"name\":\"ME_1\",\"domainValue\":\"num_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"identifiers\":[]},\"messages\":[],\"command\":\"DS_r:=avg(DS_29)\",\"adataset\":true,\"acomponent\":false,\"ascalar\":false},{\"result\":{\"id\":7,\"name\":\"temporary_6\",\"description\":null,\"attributes\":[],\"persistent\":false,\"viral\":[],\"measures\":[{\"id\":14,\"type\":\"NUMBER\",\"name\":\"ME_1\",\"domainValue\":\"num_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"identifiers\":[]},\"messages\":[],\"adataset\":true,\"acomponent\":false,\"ascalar\":false},{\"result\":{\"id\":5,\"name\":\"temporary_4\",\"description\":\"temporary_2\",\"attributes\":[],\"persistent\":false,\"viral\":[],\"measures\":[{\"id\":13,\"type\":\"NUMBER\",\"name\":\"ME_1\",\"domainValue\":\"num_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"identifiers\":[{\"id\":10,\"type\":\"INTEGER\",\"name\":\"ID_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":11,\"type\":\"STRING\",\"name\":\"ID_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":12,\"type\":\"STRING\",\"name\":\"ID_3\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}]},\"messages\":[],\"adataset\":true,\"acomponent\":false,\"ascalar\":false},{\"result\":{\"id\":1,\"name\":\"DS_29\",\"description\":null,\"attributes\":[],\"persistent\":true,\"viral\":[{\"id\":5,\"type\":\"STRING\",\"name\":\"AT_1\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"VIRAL\"}],\"measures\":[{\"id\":4,\"type\":\"INTEGER\",\"name\":\"ME_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"identifiers\":[{\"id\":1,\"type\":\"INTEGER\",\"name\":\"ID_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":2,\"type\":\"STRING\",\"name\":\"ID_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":3,\"type\":\"STRING\",\"name\":\"ID_3\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}]},\"messages\":[],\"command\":\"DS_29\",\"adataset\":true,\"acomponent\":false,\"ascalar\":false}]";
		List<ResultExpression> result = vtlTestUtils.getResult(commandStatements);
		System.out.println(vtlTestUtils.convertResultToJson(result));
		List<ResultExpression> resultExpressionsToComapare = vtlTestUtils.convertJsonToResultExpression(jsonResult);
		assertNotNull("Conversione non riuscita", resultExpressionsToComapare);
		assertNotNull("Validazione Semantica non riuscita", result);
		assertTrue("I due risultati non sono uguali",
				vtlTestUtils.compareResult(result.get(0), resultExpressionsToComapare.get(0)));
	}

	@Test
	@Transactional
	public void aggrGroupBy() throws Exception {
		VtlDataset vtlDatasetFind = datasetRepository.findByNameIgnoreCase("DS_1");
		if (vtlDatasetFind == null) {
			VtlDataset vtlDataset = new VtlDataset();

			vtlDataset.setPersistent(true);
			vtlDataset.setName("DS_29");
			vtlDataset.setIsOnlyAScalar(false);
			vtlDataset.setTransitory(false);
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_1", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_2", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_3", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_1", VtlType.INTEGER, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("AT_1", VtlType.STRING, VtlComponentRole.VIRAL));
			datasetRepository.save(vtlDataset);

		}
		String commandStatements = "DS_r := DS_29 [ aggr Me_2 := max ( Me_1 ) , Me_3 := min ( Me_1 ) group by Id_1 ];";
		String jsonResult = "[{\"result\":{\"id\":171,\"name\":\"DS_r\",\"description\":null,\"attributes\":[],\"persistent\":false,\"viral\":[],\"measures\":[{\"id\":501,\"type\":\"INTEGER\",\"name\":\"Me_3\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},{\"id\":502,\"type\":\"INTEGER\",\"name\":\"Me_2\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"identifiers\":[{\"id\":500,\"type\":\"INTEGER\",\"name\":\"ID_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}]},\"messages\":[],\"command\":\"DS_r:=DS_29[aggrMe_2:=max(Me_1),Me_3:=min(Me_1)groupbyId_1]\",\"adataset\":true,\"acomponent\":false,\"ascalar\":false},{\"result\":{\"id\":169,\"name\":\"temporary_168\",\"description\":null,\"attributes\":[],\"persistent\":false,\"viral\":[],\"measures\":[{\"id\":498,\"type\":\"INTEGER\",\"name\":\"Me_3\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},{\"id\":499,\"type\":\"INTEGER\",\"name\":\"Me_2\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"identifiers\":[{\"id\":497,\"type\":\"INTEGER\",\"name\":\"ID_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}]},\"messages\":[],\"command\":\"aggrMe_2:=max(Me_1),Me_3:=min(Me_1)groupbyId_1\",\"adataset\":true,\"acomponent\":false,\"ascalar\":false},{\"resultComponent\":{\"id\":492,\"type\":\"INTEGER\",\"name\":\"ID_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},\"messages\":[],\"command\":\"Id_1\",\"adataset\":false,\"acomponent\":true,\"ascalar\":false},{\"result\":{\"id\":167,\"name\":\"temporary_166\",\"description\":\"DS_29\",\"attributes\":[],\"persistent\":false,\"viral\":[],\"measures\":[{\"id\":495,\"type\":\"INTEGER\",\"name\":\"Me_3\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},{\"id\":496,\"type\":\"INTEGER\",\"name\":\"Me_2\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"identifiers\":[{\"id\":492,\"type\":\"INTEGER\",\"name\":\"ID_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":493,\"type\":\"STRING\",\"name\":\"ID_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":494,\"type\":\"STRING\",\"name\":\"ID_3\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}]},\"messages\":[],\"command\":\"Me_2:=max(Me_1),Me_3:=min(Me_1)\",\"adataset\":true,\"acomponent\":false,\"ascalar\":false},{\"resultComponent\":{\"id\":null,\"type\":\"INTEGER\",\"name\":\"Me_3\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},\"messages\":[],\"command\":\"Me_3:=min(Me_1)\",\"adataset\":false,\"acomponent\":true,\"ascalar\":false},{\"resultComponent\":{\"id\":null,\"type\":\"INTEGER\",\"name\":\"Me_2\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},\"messages\":[],\"command\":\"Me_2:=max(Me_1)\",\"adataset\":false,\"acomponent\":true,\"ascalar\":false},{\"result\":{\"id\":165,\"name\":\"DS_29\",\"description\":null,\"attributes\":[],\"persistent\":true,\"viral\":[{\"id\":491,\"type\":\"STRING\",\"name\":\"AT_1\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"VIRAL\"}],\"measures\":[{\"id\":490,\"type\":\"INTEGER\",\"name\":\"ME_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"identifiers\":[{\"id\":487,\"type\":\"INTEGER\",\"name\":\"ID_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":488,\"type\":\"STRING\",\"name\":\"ID_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":489,\"type\":\"STRING\",\"name\":\"ID_3\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}]},\"messages\":[],\"command\":\"DS_29\",\"adataset\":true,\"acomponent\":false,\"ascalar\":false}]";
		List<ResultExpression> result = vtlTestUtils.getResult(commandStatements);
		System.out.println(vtlTestUtils.convertResultToJson(result));

		List<ResultExpression> resultExpressionsToComapare = vtlTestUtils.convertJsonToResultExpression(jsonResult);
		assertNotNull("Conversione non riuscita", resultExpressionsToComapare);
		assertNotNull("Validazione Semantica non riuscita", result);
		assertTrue("I due risultati non sono uguali",
				vtlTestUtils.compareResult(result.get(0), resultExpressionsToComapare.get(0)));
	}

	@Test
	@Transactional
	public void sumGroupBy() throws Exception {
		VtlDataset vtlDatasetFind = datasetRepository.findByNameIgnoreCase("DS_1");
		if (vtlDatasetFind == null) {
			VtlDataset vtlDataset = new VtlDataset();

			vtlDataset.setPersistent(true);
			vtlDataset.setName("DS_29");
			vtlDataset.setIsOnlyAScalar(false);
			vtlDataset.setTransitory(false);
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_1", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_2", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_3", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_1", VtlType.INTEGER, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("AT_1", VtlType.STRING, VtlComponentRole.VIRAL));
			datasetRepository.save(vtlDataset);
		}
		String commandStatements = "DS_r := sum ( DS_29  group by  Id_1, Id_3 );";
		String jsonResult = "[{\"result\":{\"id\":196,\"name\":\"DS_r\",\"description\":null,\"attributes\":[],\"persistent\":false,\"viral\":[],\"measures\":[{\"id\":551,\"type\":\"INTEGER\",\"name\":\"ME_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"identifiers\":[{\"id\":549,\"type\":\"INTEGER\",\"name\":\"ID_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":550,\"type\":\"STRING\",\"name\":\"ID_3\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}]},\"messages\":[],\"command\":\"DS_r:=sum(DS_29groupbyId_1,Id_3)\",\"acomponent\":false,\"ascalar\":false,\"adataset\":true},{\"result\":{\"id\":194,\"name\":\"temporary_193\",\"description\":null,\"attributes\":[],\"persistent\":false,\"viral\":[],\"measures\":[{\"id\":548,\"type\":\"INTEGER\",\"name\":\"ME_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"identifiers\":[{\"id\":546,\"type\":\"INTEGER\",\"name\":\"ID_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":547,\"type\":\"STRING\",\"name\":\"ID_3\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}]},\"messages\":[],\"command\":\"groupbyId_1,Id_3\",\"acomponent\":false,\"ascalar\":false,\"adataset\":true},{\"resultComponent\":{\"id\":544,\"type\":\"STRING\",\"name\":\"ID_3\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},\"messages\":[],\"command\":\"Id_3\",\"acomponent\":true,\"ascalar\":false,\"adataset\":false},{\"resultComponent\":{\"id\":542,\"type\":\"INTEGER\",\"name\":\"ID_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},\"messages\":[],\"command\":\"Id_1\",\"acomponent\":true,\"ascalar\":false,\"adataset\":false},{\"result\":{\"id\":192,\"name\":\"temporary_191\",\"description\":null,\"attributes\":[],\"persistent\":false,\"viral\":[],\"measures\":[{\"id\":545,\"type\":\"INTEGER\",\"name\":\"ME_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"identifiers\":[{\"id\":542,\"type\":\"INTEGER\",\"name\":\"ID_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":543,\"type\":\"STRING\",\"name\":\"ID_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":544,\"type\":\"STRING\",\"name\":\"ID_3\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}]},\"messages\":[],\"acomponent\":false,\"ascalar\":false,\"adataset\":true},{\"result\":{\"id\":190,\"name\":\"DS_29\",\"description\":null,\"attributes\":[],\"persistent\":true,\"viral\":[{\"id\":541,\"type\":\"STRING\",\"name\":\"AT_1\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"VIRAL\"}],\"measures\":[{\"id\":540,\"type\":\"INTEGER\",\"name\":\"ME_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"identifiers\":[{\"id\":537,\"type\":\"INTEGER\",\"name\":\"ID_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":538,\"type\":\"STRING\",\"name\":\"ID_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":539,\"type\":\"STRING\",\"name\":\"ID_3\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}]},\"messages\":[],\"command\":\"DS_29\",\"acomponent\":false,\"ascalar\":false,\"adataset\":true}]";
		List<ResultExpression> result = vtlTestUtils.getResult(commandStatements);
		System.out.println(vtlTestUtils.convertResultToJson(result));

		List<ResultExpression> resultExpressionsToComapare = vtlTestUtils.convertJsonToResultExpression(jsonResult);
		assertNotNull("Conversione non riuscita", resultExpressionsToComapare);
		assertNotNull("Validazione Semantica non riuscita", result);
		assertTrue("I due risultati non sono uguali",
				vtlTestUtils.compareResult(result.get(0), resultExpressionsToComapare.get(0)));

	}

	@Test
	@Transactional
	public void sumOrderBy() throws Exception {
		VtlDataset vtlDatasetFind = datasetRepository.findByNameIgnoreCase("DS_1");
		if (vtlDatasetFind == null) {
			VtlDataset vtlDataset = new VtlDataset();

			vtlDataset.setPersistent(true);
			vtlDataset.setName("DS_30");
			vtlDataset.setIsOnlyAScalar(false);
			vtlDataset.setTransitory(false);
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_1", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_2", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_3", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_1", VtlType.INTEGER, VtlComponentRole.MEASURE));
			datasetRepository.save(vtlDataset);
		}
		String commandStatements = "DS_r := sum ( DS_30 over ( order by  Id_1, Id_2, Id_3  data points between 1 preceding and 1 following ) );";
		String jsonResult = "[{\"result\":{\"id\":16,\"name\":\"DS_r\",\"description\":null,\"attributes\":[],\"persistent\":false,\"viral\":[],\"identifiers\":[{\"id\":6,\"type\":\"INTEGER\",\"name\":\"ID_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":7,\"type\":\"STRING\",\"name\":\"ID_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":8,\"type\":\"STRING\",\"name\":\"ID_3\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}],\"measures\":[{\"id\":54,\"type\":\"INTEGER\",\"name\":\"ME_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"}]},\"messages\":[],\"command\":\"DS_r:=sum(DS_30over(orderbyId_1,Id_2,Id_3datapointsbetween1precedingand1following));\",\"ascalar\":false,\"acomponent\":false},{\"result\":{\"id\":16,\"name\":\"DS_r\",\"description\":null,\"attributes\":[],\"persistent\":false,\"viral\":[],\"identifiers\":[{\"id\":6,\"type\":\"INTEGER\",\"name\":\"ID_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":7,\"type\":\"STRING\",\"name\":\"ID_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":8,\"type\":\"STRING\",\"name\":\"ID_3\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}],\"measures\":[{\"id\":54,\"type\":\"INTEGER\",\"name\":\"ME_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"}]},\"messages\":[],\"ascalar\":false,\"acomponent\":false},{\"resultComponent\":{\"id\":8,\"type\":\"STRING\",\"name\":\"ID_3\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},\"messages\":[],\"command\":\"Id_3\",\"ascalar\":false,\"acomponent\":true},{\"resultComponent\":{\"id\":7,\"type\":\"STRING\",\"name\":\"ID_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},\"messages\":[],\"command\":\"Id_2\",\"ascalar\":false,\"acomponent\":true},{\"resultComponent\":{\"id\":6,\"type\":\"INTEGER\",\"name\":\"ID_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},\"messages\":[],\"command\":\"Id_1\",\"ascalar\":false,\"acomponent\":true}]";
		List<ResultExpression> result = vtlTestUtils.getResult(commandStatements);
		List<ResultExpression> resultExpressionsToComapare = vtlTestUtils.convertJsonToResultExpression(jsonResult);
		assertNotNull("Conversione non riuscita", resultExpressionsToComapare);
		assertNotNull("Validazione Semantica non riuscita", result);
		assertTrue("I due risultati non sono uguali",
				vtlTestUtils.compareResult(result.get(0), resultExpressionsToComapare.get(0)));

	}

	@Test
	@Transactional
	public void sumHavingCount() throws Exception {
		VtlDataset vtlDatasetFind = datasetRepository.findByNameIgnoreCase("DS_1");
		if (vtlDatasetFind == null) {
			VtlDataset vtlDataset = new VtlDataset();

			vtlDataset.setPersistent(true);
			vtlDataset.setName("DS_30");
			vtlDataset.setIsOnlyAScalar(false);
			vtlDataset.setTransitory(false);
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_1", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_2", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_3", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_1", VtlType.INTEGER, VtlComponentRole.MEASURE));
			datasetRepository.save(vtlDataset);

		}
		String commandStatements = "DS_r := sum ( DS_30 group by  Id_1  having count() > 2 );";
		//String jsonResult = "[{\"result\":{\"id\":134,\"name\":\"DS_r\",\"description\":null,\"attributes\":[],\"persistent\":false,\"identifiers\":[{\"id\":355,\"type\":\"INTEGER\",\"name\":\"ID_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}],\"viral\":[],\"measures\":[{\"id\":356,\"type\":\"STRING\",\"name\":\"ME_1\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"MEASURE\"}]},\"messages\":[],\"command\":\"DS_r:=sum(DS_33groupbyId_1havingcount()>2);\",\"ascalar\":false,\"acomponent\":false},{\"result\":{\"id\":132,\"name\":\"temporary_131\",\"description\":null,\"attributes\":[],\"persistent\":false,\"identifiers\":[{\"id\":353,\"type\":\"INTEGER\",\"name\":\"ID_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}],\"viral\":[],\"measures\":[{\"id\":354,\"type\":\"STRING\",\"name\":\"ME_1\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"MEASURE\"}]},\"messages\":[],\"command\":\"groupbyId_1\",\"ascalar\":false,\"acomponent\":false},{\"resultComponent\":{\"id\":350,\"type\":\"INTEGER\",\"name\":\"ID_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},\"messages\":[],\"command\":\"Id_1\",\"ascalar\":false,\"acomponent\":true},{\"result\":{\"id\":130,\"name\":\"temporary_129\",\"description\":null,\"attributes\":[],\"persistent\":false,\"identifiers\":[{\"id\":350,\"type\":\"INTEGER\",\"name\":\"ID_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":351,\"type\":\"STRING\",\"name\":\"ID_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":352,\"type\":\"STRING\",\"name\":\"ID_3\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}],\"viral\":[],\"measures\":[{\"id\":349,\"type\":\"STRING\",\"name\":\"ME_1\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"MEASURE\"}]},\"messages\":[],\"ascalar\":false,\"acomponent\":false},{\"result\":{\"id\":33,\"name\":\"DS_33\",\"description\":null,\"attributes\":[],\"persistent\":true,\"identifiers\":[{\"id\":133,\"type\":\"INTEGER\",\"name\":\"ID_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":134,\"type\":\"STRING\",\"name\":\"ID_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":135,\"type\":\"STRING\",\"name\":\"ID_3\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}],\"viral\":[],\"measures\":[{\"id\":136,\"type\":\"STRING\",\"name\":\"ME_1\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"MEASURE\"}]},\"messages\":[],\"command\":\"DS_33\",\"ascalar\":false,\"acomponent\":false}]";
                String jsonResult = "[ { \"result\": { \"id\": 56, \"name\": \"DS_r\", \"description\": null, \"attributes\": [], \"identifiers\": [ { \"id\": 181, \"type\": \"INTEGER\", \"name\": \"ID_1\", \"domainValue\": \"int_vd\", \"domainValueParent\": \"num_vd\", \"vtlComponentRole\": \"IDENTIFIER\" } ], \"measures\": [ { \"id\": 182, \"type\": \"INTEGER\", \"name\": \"ME_1\", \"domainValue\": \"int_vd\", \"domainValueParent\": \"num_vd\", \"vtlComponentRole\": \"MEASURE\" } ], \"viral\": [], \"persistent\": false }, \"messages\": [], \"command\": \"DS_r:=sum(DS_30groupbyId_1havingcount()>2)\", \"acomponent\": false, \"ascalar\": false, \"adataset\": true }, { \"result\": { \"id\": 54, \"name\": \"temporary_53\", \"description\": null, \"attributes\": [], \"identifiers\": [ { \"id\": 179, \"type\": \"INTEGER\", \"name\": \"ID_1\", \"domainValue\": \"int_vd\", \"domainValueParent\": \"num_vd\", \"vtlComponentRole\": \"IDENTIFIER\" } ], \"measures\": [ { \"id\": 180, \"type\": \"INTEGER\", \"name\": \"ME_1\", \"domainValue\": \"int_vd\", \"domainValueParent\": \"num_vd\", \"vtlComponentRole\": \"MEASURE\" } ], \"viral\": [], \"persistent\": false }, \"messages\": [], \"command\": \"groupbyId_1\", \"acomponent\": false, \"ascalar\": false, \"adataset\": true }, { \"resultComponent\": { \"id\": 171, \"type\": \"INTEGER\", \"name\": \"ID_1\", \"domainValue\": \"int_vd\", \"domainValueParent\": \"num_vd\", \"vtlComponentRole\": \"IDENTIFIER\" }, \"messages\": [], \"command\": \"Id_1\", \"acomponent\": true, \"ascalar\": false, \"adataset\": false }, { \"result\": { \"id\": 52, \"name\": \"temporary_51\", \"description\": null, \"attributes\": [], \"identifiers\": [], \"measures\": [ { \"id\": 178, \"type\": \"BOOLEAN\", \"name\": \"bool_var\", \"domainValue\": \"bool_vd\", \"domainValueParent\": \"bool_vd\", \"vtlComponentRole\": \"MEASURE\" } ], \"viral\": [], \"persistent\": false }, \"resultComponent\": { \"id\": 178, \"type\": \"BOOLEAN\", \"name\": \"bool_var\", \"domainValue\": \"bool_vd\", \"domainValueParent\": \"bool_vd\", \"vtlComponentRole\": \"MEASURE\" }, \"messages\": [], \"command\": \"count()>2\", \"acomponent\": false, \"ascalar\": true, \"scalarComponent\": { \"id\": 178, \"type\": \"BOOLEAN\", \"name\": \"bool_var\", \"domainValue\": \"bool_vd\", \"domainValueParent\": \"bool_vd\", \"vtlComponentRole\": \"MEASURE\" }, \"adataset\": false }, { \"result\": { \"id\": 46, \"name\": \"temporary_45\", \"description\": null, \"attributes\": [], \"identifiers\": [], \"measures\": [ { \"id\": 175, \"type\": \"INTEGER\", \"name\": \"int_var\", \"domainValue\": \"int_vd\", \"domainValueParent\": \"num_vd\", \"vtlComponentRole\": \"MEASURE\" } ], \"viral\": [], \"persistent\": false }, \"resultComponent\": { \"id\": 175, \"type\": \"INTEGER\", \"name\": \"int_var\", \"domainValue\": \"int_vd\", \"domainValueParent\": \"num_vd\", \"vtlComponentRole\": \"MEASURE\" }, \"messages\": [], \"acomponent\": false, \"ascalar\": true, \"scalarComponent\": { \"id\": 175, \"type\": \"INTEGER\", \"name\": \"int_var\", \"domainValue\": \"int_vd\", \"domainValueParent\": \"num_vd\", \"vtlComponentRole\": \"MEASURE\" }, \"adataset\": false }, { \"result\": { \"id\": 48, \"name\": \"temporary_47\", \"description\": null, \"attributes\": [], \"identifiers\": [], \"measures\": [ { \"id\": 176, \"type\": \"INTEGER\", \"name\": \"int_var\", \"domainValue\": \"int_vd\", \"domainValueParent\": \"num_vd\", \"vtlComponentRole\": \"MEASURE\" } ], \"viral\": [], \"persistent\": false }, \"resultComponent\": { \"id\": 176, \"type\": \"INTEGER\", \"name\": \"int_var\", \"domainValue\": \"int_vd\", \"domainValueParent\": \"num_vd\", \"vtlComponentRole\": \"MEASURE\" }, \"messages\": [], \"command\": \"2\", \"acomponent\": false, \"ascalar\": true, \"scalarComponent\": { \"id\": 176, \"type\": \"INTEGER\", \"name\": \"int_var\", \"domainValue\": \"int_vd\", \"domainValueParent\": \"num_vd\", \"vtlComponentRole\": \"MEASURE\" }, \"adataset\": false }, { \"result\": { \"id\": 44, \"name\": \"temporary_43\", \"description\": null, \"attributes\": [], \"identifiers\": [ { \"id\": 171, \"type\": \"INTEGER\", \"name\": \"ID_1\", \"domainValue\": \"int_vd\", \"domainValueParent\": \"num_vd\", \"vtlComponentRole\": \"IDENTIFIER\" }, { \"id\": 172, \"type\": \"STRING\", \"name\": \"ID_2\", \"domainValue\": \"string_vd\", \"domainValueParent\": \"string_vd\", \"vtlComponentRole\": \"IDENTIFIER\" }, { \"id\": 173, \"type\": \"STRING\", \"name\": \"ID_3\", \"domainValue\": \"string_vd\", \"domainValueParent\": \"string_vd\", \"vtlComponentRole\": \"IDENTIFIER\" } ], \"measures\": [ { \"id\": 174, \"type\": \"INTEGER\", \"name\": \"ME_1\", \"domainValue\": \"int_vd\", \"domainValueParent\": \"num_vd\", \"vtlComponentRole\": \"MEASURE\" } ], \"viral\": [], \"persistent\": false }, \"messages\": [], \"acomponent\": false, \"ascalar\": false, \"adataset\": true }, { \"result\": { \"id\": 30, \"name\": \"DS_30\", \"description\": null, \"attributes\": [], \"identifiers\": [ { \"id\": 121, \"type\": \"INTEGER\", \"name\": \"ID_1\", \"domainValue\": \"int_vd\", \"domainValueParent\": \"num_vd\", \"vtlComponentRole\": \"IDENTIFIER\" }, { \"id\": 122, \"type\": \"STRING\", \"name\": \"ID_2\", \"domainValue\": \"string_vd\", \"domainValueParent\": \"string_vd\", \"vtlComponentRole\": \"IDENTIFIER\" }, { \"id\": 123, \"type\": \"STRING\", \"name\": \"ID_3\", \"domainValue\": \"string_vd\", \"domainValueParent\": \"string_vd\", \"vtlComponentRole\": \"IDENTIFIER\" } ], \"measures\": [ { \"id\": 124, \"type\": \"INTEGER\", \"name\": \"ME_1\", \"domainValue\": \"int_vd\", \"domainValueParent\": \"num_vd\", \"vtlComponentRole\": \"MEASURE\" } ], \"viral\": [], \"persistent\": true }, \"messages\": [], \"command\": \"DS_30\", \"acomponent\": false, \"ascalar\": false, \"adataset\": true } ]";
		List<ResultExpression> result = vtlTestUtils.getResult(commandStatements);
		List<ResultExpression> resultExpressionsToComapare = vtlTestUtils.convertJsonToResultExpression(jsonResult);
		assertNotNull("Conversione non riuscita", resultExpressionsToComapare);
		assertNotNull("Validazione Semantica non riuscita", result);
		assertTrue("I due risultati non sono uguali",
				vtlTestUtils.compareResult(result.get(0), resultExpressionsToComapare.get(0)));

	}

	@Test
	@Transactional
	public void count() throws Exception {
		VtlDataset vtlDatasetFind = datasetRepository.findByNameIgnoreCase("DS_1");
		if (vtlDatasetFind == null) {
			VtlDataset vtlDataset = new VtlDataset();

			vtlDataset.setPersistent(true);
			vtlDataset.setName("DS_33");
			vtlDataset.setIsOnlyAScalar(false);
			vtlDataset.setTransitory(false);
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_1", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_2", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_3", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_1", VtlType.STRING, VtlComponentRole.MEASURE));
			datasetRepository.save(vtlDataset);
		}
		String commandStatements = "DS_r := count ( DS_33 group by  Id_1 );";
		String jsonResult = "[{\"result\":{\"id\":22,\"name\":\"DS_r\",\"description\":null,\"attributes\":[],\"persistent\":false,\"viral\":[],\"identifiers\":[{\"id\":20,\"type\":\"INTEGER\",\"name\":\"ID_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}],\"measures\":[{\"id\":56,\"type\":\"INTEGER\",\"name\":\"int_var\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"}]},\"messages\":[],\"command\":\"DS_r:=count(DS_33groupbyId_1);\",\"ascalar\":false,\"acomponent\":false},{\"result\":{\"id\":22,\"name\":\"DS_r\",\"description\":null,\"attributes\":[],\"persistent\":false,\"viral\":[],\"identifiers\":[{\"id\":20,\"type\":\"INTEGER\",\"name\":\"ID_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}],\"measures\":[{\"id\":56,\"type\":\"INTEGER\",\"name\":\"int_var\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"}]},\"messages\":[],\"command\":\"groupbyId_1\",\"ascalar\":false,\"acomponent\":false},{\"result\":{\"id\":20,\"name\":\"temporary_19\",\"description\":null,\"attributes\":[],\"persistent\":false,\"viral\":[],\"identifiers\":[{\"id\":20,\"type\":\"INTEGER\",\"name\":\"ID_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":21,\"type\":\"STRING\",\"name\":\"ID_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":22,\"type\":\"STRING\",\"name\":\"ID_3\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}],\"measures\":[{\"id\":56,\"type\":\"INTEGER\",\"name\":\"int_var\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"}]},\"messages\":[],\"ascalar\":false,\"acomponent\":false},{\"result\":{\"id\":5,\"name\":\"DS_33\",\"description\":null,\"attributes\":[],\"persistent\":true,\"viral\":[],\"identifiers\":[{\"id\":20,\"type\":\"INTEGER\",\"name\":\"ID_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":21,\"type\":\"STRING\",\"name\":\"ID_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":22,\"type\":\"STRING\",\"name\":\"ID_3\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}],\"measures\":[{\"id\":23,\"type\":\"STRING\",\"name\":\"ME_1\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"MEASURE\"}]},\"messages\":[],\"command\":\"DS_33\",\"ascalar\":false,\"acomponent\":false}]";
		List<ResultExpression> result = vtlTestUtils.getResult(commandStatements);
		List<ResultExpression> resultExpressionsToComapare = vtlTestUtils.convertJsonToResultExpression(jsonResult);
		assertNotNull("Conversione non riuscita", resultExpressionsToComapare);
		assertNotNull("Validazione Semantica non riuscita", result);
		assertTrue("I due risultati non sono uguali",
				vtlTestUtils.compareResult(result.get(0), resultExpressionsToComapare.get(0)));
	}

	@Test
	@Transactional
	public void min() throws Exception {
		VtlDataset vtlDatasetFind = datasetRepository.findByNameIgnoreCase("DS_1");
		if (vtlDatasetFind == null) {
			VtlDataset vtlDataset = new VtlDataset();

			vtlDataset.setPersistent(true);
			vtlDataset.setName("DS_30");
			vtlDataset.setIsOnlyAScalar(false);
			vtlDataset.setTransitory(false);
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_1", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_2", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_3", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_1", VtlType.INTEGER, VtlComponentRole.MEASURE));
			datasetRepository.save(vtlDataset);
		}
		String commandStatements = "DS_r := min ( DS_30 group by  Id_1 );";
		String jsonResult = "[{\"result\":{\"id\":142,\"name\":\"DS_r\",\"description\":null,\"attributes\":[],\"persistent\":false,\"identifiers\":[{\"id\":367,\"type\":\"INTEGER\",\"name\":\"ID_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}],\"viral\":[],\"measures\":[{\"id\":368,\"type\":\"INTEGER\",\"name\":\"ME_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"}]},\"messages\":[],\"command\":\"DS_r:=min(DS_30groupbyId_1);\",\"ascalar\":false,\"acomponent\":false},{\"result\":{\"id\":140,\"name\":\"temporary_139\",\"description\":null,\"attributes\":[],\"persistent\":false,\"identifiers\":[{\"id\":365,\"type\":\"INTEGER\",\"name\":\"ID_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}],\"viral\":[],\"measures\":[{\"id\":366,\"type\":\"INTEGER\",\"name\":\"ME_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"}]},\"messages\":[],\"command\":\"groupbyId_1\",\"ascalar\":false,\"acomponent\":false},{\"resultComponent\":{\"id\":362,\"type\":\"INTEGER\",\"name\":\"ID_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},\"messages\":[],\"command\":\"Id_1\",\"ascalar\":false,\"acomponent\":true},{\"result\":{\"id\":138,\"name\":\"temporary_137\",\"description\":null,\"attributes\":[],\"persistent\":false,\"identifiers\":[{\"id\":362,\"type\":\"INTEGER\",\"name\":\"ID_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":363,\"type\":\"STRING\",\"name\":\"ID_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":364,\"type\":\"STRING\",\"name\":\"ID_3\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}],\"viral\":[],\"measures\":[{\"id\":361,\"type\":\"INTEGER\",\"name\":\"ME_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"}]},\"messages\":[],\"ascalar\":false,\"acomponent\":false},{\"result\":{\"id\":30,\"name\":\"DS_30\",\"description\":null,\"attributes\":[],\"persistent\":true,\"identifiers\":[{\"id\":119,\"type\":\"INTEGER\",\"name\":\"ID_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":120,\"type\":\"STRING\",\"name\":\"ID_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":121,\"type\":\"STRING\",\"name\":\"ID_3\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}],\"viral\":[],\"measures\":[{\"id\":122,\"type\":\"INTEGER\",\"name\":\"ME_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"}]},\"messages\":[],\"command\":\"DS_30\",\"ascalar\":false,\"acomponent\":false}]";
		List<ResultExpression> result = vtlTestUtils.getResult(commandStatements);
		List<ResultExpression> resultExpressionsToComapare = vtlTestUtils.convertJsonToResultExpression(jsonResult);
		assertNotNull("Conversione non riuscita", resultExpressionsToComapare);
		assertNotNull("Validazione Semantica non riuscita", result);
		assertTrue("I due risultati non sono uguali",
				vtlTestUtils.compareResult(result.get(0), resultExpressionsToComapare.get(0)));
	}

	@Test
	@Transactional
	public void max() throws Exception {
		VtlDataset vtlDatasetFind = datasetRepository.findByNameIgnoreCase("DS_1");
		if (vtlDatasetFind == null) {
			VtlDataset vtlDataset = new VtlDataset();

			vtlDataset.setPersistent(true);
			vtlDataset.setName("DS_30");
			vtlDataset.setIsOnlyAScalar(false);
			vtlDataset.setTransitory(false);
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_1", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_2", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_3", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_1", VtlType.INTEGER, VtlComponentRole.MEASURE));
			datasetRepository.save(vtlDataset);
		}
		String commandStatements = "DS_r := max ( DS_30 group by  Id_1 );";
		String jsonResult = "[{\"result\":{\"id\":150,\"name\":\"DS_r\",\"description\":null,\"attributes\":[],\"persistent\":false,\"identifiers\":[{\"id\":379,\"type\":\"INTEGER\",\"name\":\"ID_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}],\"viral\":[],\"measures\":[{\"id\":380,\"type\":\"INTEGER\",\"name\":\"ME_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"}]},\"messages\":[],\"command\":\"DS_r:=max(DS_30groupbyId_1);\",\"ascalar\":false,\"acomponent\":false},{\"result\":{\"id\":148,\"name\":\"temporary_147\",\"description\":null,\"attributes\":[],\"persistent\":false,\"identifiers\":[{\"id\":377,\"type\":\"INTEGER\",\"name\":\"ID_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}],\"viral\":[],\"measures\":[{\"id\":378,\"type\":\"INTEGER\",\"name\":\"ME_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"}]},\"messages\":[],\"command\":\"groupbyId_1\",\"ascalar\":false,\"acomponent\":false},{\"resultComponent\":{\"id\":374,\"type\":\"INTEGER\",\"name\":\"ID_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},\"messages\":[],\"command\":\"Id_1\",\"ascalar\":false,\"acomponent\":true},{\"result\":{\"id\":146,\"name\":\"temporary_145\",\"description\":null,\"attributes\":[],\"persistent\":false,\"identifiers\":[{\"id\":374,\"type\":\"INTEGER\",\"name\":\"ID_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":375,\"type\":\"STRING\",\"name\":\"ID_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":376,\"type\":\"STRING\",\"name\":\"ID_3\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}],\"viral\":[],\"measures\":[{\"id\":373,\"type\":\"INTEGER\",\"name\":\"ME_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"}]},\"messages\":[],\"ascalar\":false,\"acomponent\":false},{\"result\":{\"id\":30,\"name\":\"DS_30\",\"description\":null,\"attributes\":[],\"persistent\":true,\"identifiers\":[{\"id\":119,\"type\":\"INTEGER\",\"name\":\"ID_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":120,\"type\":\"STRING\",\"name\":\"ID_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":121,\"type\":\"STRING\",\"name\":\"ID_3\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}],\"viral\":[],\"measures\":[{\"id\":122,\"type\":\"INTEGER\",\"name\":\"ME_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"}]},\"messages\":[],\"command\":\"DS_30\",\"ascalar\":false,\"acomponent\":false}]";
		List<ResultExpression> result = vtlTestUtils.getResult(commandStatements);
		List<ResultExpression> resultExpressionsToComapare = vtlTestUtils.convertJsonToResultExpression(jsonResult);
		assertNotNull("Conversione non riuscita", resultExpressionsToComapare);
		assertNotNull("Validazione Semantica non riuscita", result);
		assertTrue("I due risultati non sono uguali",
				vtlTestUtils.compareResult(result.get(0), resultExpressionsToComapare.get(0)));
	}

	@Test
	@Transactional
	public void median() throws Exception {
		VtlDataset vtlDatasetFind = datasetRepository.findByNameIgnoreCase("DS_1");
		if (vtlDatasetFind == null) {
			VtlDataset vtlDataset = new VtlDataset();

			vtlDataset.setPersistent(true);
			vtlDataset.setName("DS_30");
			vtlDataset.setIsOnlyAScalar(false);
			vtlDataset.setTransitory(false);
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_1", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_2", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_3", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_1", VtlType.INTEGER, VtlComponentRole.MEASURE));
			datasetRepository.save(vtlDataset);
		}
		String commandStatements = "DS_r := median ( DS_30  group by  Id_1 );";
		String jsonResult = "[{\"result\":{\"id\":158,\"name\":\"DS_r\",\"description\":null,\"attributes\":[],\"persistent\":false,\"identifiers\":[{\"id\":391,\"type\":\"INTEGER\",\"name\":\"ID_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}],\"viral\":[],\"measures\":[{\"id\":392,\"type\":\"NUMBER\",\"name\":\"ME_1\",\"domainValue\":\"num_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"}]},\"messages\":[],\"command\":\"DS_r:=median(DS_30groupbyId_1);\",\"ascalar\":false,\"acomponent\":false},{\"result\":{\"id\":156,\"name\":\"temporary_155\",\"description\":null,\"attributes\":[],\"persistent\":false,\"identifiers\":[{\"id\":389,\"type\":\"INTEGER\",\"name\":\"ID_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}],\"viral\":[],\"measures\":[{\"id\":390,\"type\":\"NUMBER\",\"name\":\"ME_1\",\"domainValue\":\"num_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"}]},\"messages\":[],\"command\":\"groupbyId_1\",\"ascalar\":false,\"acomponent\":false},{\"resultComponent\":{\"id\":386,\"type\":\"INTEGER\",\"name\":\"ID_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},\"messages\":[],\"command\":\"Id_1\",\"ascalar\":false,\"acomponent\":true},{\"result\":{\"id\":154,\"name\":\"temporary_153\",\"description\":null,\"attributes\":[],\"persistent\":false,\"identifiers\":[{\"id\":386,\"type\":\"INTEGER\",\"name\":\"ID_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":387,\"type\":\"STRING\",\"name\":\"ID_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":388,\"type\":\"STRING\",\"name\":\"ID_3\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}],\"viral\":[],\"measures\":[{\"id\":385,\"type\":\"NUMBER\",\"name\":\"ME_1\",\"domainValue\":\"num_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"}]},\"messages\":[],\"ascalar\":false,\"acomponent\":false},{\"result\":{\"id\":30,\"name\":\"DS_30\",\"description\":null,\"attributes\":[],\"persistent\":true,\"identifiers\":[{\"id\":119,\"type\":\"INTEGER\",\"name\":\"ID_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":120,\"type\":\"STRING\",\"name\":\"ID_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":121,\"type\":\"STRING\",\"name\":\"ID_3\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}],\"viral\":[],\"measures\":[{\"id\":122,\"type\":\"INTEGER\",\"name\":\"ME_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"}]},\"messages\":[],\"command\":\"DS_30\",\"ascalar\":false,\"acomponent\":false}]";
		List<ResultExpression> result = vtlTestUtils.getResult(commandStatements);
		List<ResultExpression> resultExpressionsToComapare = vtlTestUtils.convertJsonToResultExpression(jsonResult);
		assertNotNull("Conversione non riuscita", resultExpressionsToComapare);
		assertNotNull("Validazione Semantica non riuscita", result);
		assertTrue("I due risultati non sono uguali",
				vtlTestUtils.compareResult(result.get(0), resultExpressionsToComapare.get(0)));

	}

	@Test
	@Transactional
	public void stddevPop() throws Exception {
		VtlDataset vtlDatasetFind = datasetRepository.findByNameIgnoreCase("DS_1");
		if (vtlDatasetFind == null) {
			VtlDataset vtlDataset = new VtlDataset();

			vtlDataset.setPersistent(true);
			vtlDataset.setName("DS_30");
			vtlDataset.setIsOnlyAScalar(false);
			vtlDataset.setTransitory(false);
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_1", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_2", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_3", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_1", VtlType.INTEGER, VtlComponentRole.MEASURE));
			datasetRepository.save(vtlDataset);
		}
		String commandStatements = "DS_r := stddev_pop ( DS_30 group by  Id_1 );";
		String jsonResult = "[{\"result\":{\"id\":166,\"name\":\"DS_r\",\"description\":null,\"attributes\":[],\"persistent\":false,\"identifiers\":[{\"id\":403,\"type\":\"INTEGER\",\"name\":\"ID_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}],\"viral\":[],\"measures\":[{\"id\":404,\"type\":\"NUMBER\",\"name\":\"ME_1\",\"domainValue\":\"num_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"}]},\"messages\":[],\"command\":\"DS_r:=stddev_pop(DS_30groupbyId_1);\",\"ascalar\":false,\"acomponent\":false},{\"result\":{\"id\":164,\"name\":\"temporary_163\",\"description\":null,\"attributes\":[],\"persistent\":false,\"identifiers\":[{\"id\":401,\"type\":\"INTEGER\",\"name\":\"ID_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}],\"viral\":[],\"measures\":[{\"id\":402,\"type\":\"NUMBER\",\"name\":\"ME_1\",\"domainValue\":\"num_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"}]},\"messages\":[],\"command\":\"groupbyId_1\",\"ascalar\":false,\"acomponent\":false},{\"resultComponent\":{\"id\":398,\"type\":\"INTEGER\",\"name\":\"ID_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},\"messages\":[],\"command\":\"Id_1\",\"ascalar\":false,\"acomponent\":true},{\"result\":{\"id\":162,\"name\":\"temporary_161\",\"description\":null,\"attributes\":[],\"persistent\":false,\"identifiers\":[{\"id\":398,\"type\":\"INTEGER\",\"name\":\"ID_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":399,\"type\":\"STRING\",\"name\":\"ID_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":400,\"type\":\"STRING\",\"name\":\"ID_3\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}],\"viral\":[],\"measures\":[{\"id\":397,\"type\":\"NUMBER\",\"name\":\"ME_1\",\"domainValue\":\"num_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"}]},\"messages\":[],\"ascalar\":false,\"acomponent\":false},{\"result\":{\"id\":30,\"name\":\"DS_30\",\"description\":null,\"attributes\":[],\"persistent\":true,\"identifiers\":[{\"id\":119,\"type\":\"INTEGER\",\"name\":\"ID_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":120,\"type\":\"STRING\",\"name\":\"ID_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":121,\"type\":\"STRING\",\"name\":\"ID_3\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}],\"viral\":[],\"measures\":[{\"id\":122,\"type\":\"INTEGER\",\"name\":\"ME_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"}]},\"messages\":[],\"command\":\"DS_30\",\"ascalar\":false,\"acomponent\":false}]";
		List<ResultExpression> result = vtlTestUtils.getResult(commandStatements);
		List<ResultExpression> resultExpressionsToComapare = vtlTestUtils.convertJsonToResultExpression(jsonResult);
		assertNotNull("Conversione non riuscita", resultExpressionsToComapare);
		assertNotNull("Validazione Semantica non riuscita", result);
		assertTrue("I due risultati non sono uguali",
				vtlTestUtils.compareResult(result.get(0), resultExpressionsToComapare.get(0)));
	}

	@Test
	@Transactional
	public void stddevSamp() throws Exception {
		VtlDataset vtlDatasetFind = datasetRepository.findByNameIgnoreCase("DS_1");
		if (vtlDatasetFind == null) {
			VtlDataset vtlDataset = new VtlDataset();

			vtlDataset.setPersistent(true);
			vtlDataset.setName("DS_30");
			vtlDataset.setIsOnlyAScalar(false);
			vtlDataset.setTransitory(false);
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_1", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_2", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_3", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_1", VtlType.INTEGER, VtlComponentRole.MEASURE));
			datasetRepository.save(vtlDataset);
		}
		String commandStatements = "DS_r := stddev_samp ( DS_30 group by  Id_1 );";
		String jsonResult = "[{\"result\":{\"id\":174,\"name\":\"DS_r\",\"description\":null,\"attributes\":[],\"persistent\":false,\"identifiers\":[{\"id\":415,\"type\":\"INTEGER\",\"name\":\"ID_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}],\"viral\":[],\"measures\":[{\"id\":416,\"type\":\"NUMBER\",\"name\":\"ME_1\",\"domainValue\":\"num_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"}]},\"messages\":[],\"command\":\"DS_r:=stddev_samp(DS_30groupbyId_1);\",\"ascalar\":false,\"acomponent\":false},{\"result\":{\"id\":172,\"name\":\"temporary_171\",\"description\":null,\"attributes\":[],\"persistent\":false,\"identifiers\":[{\"id\":413,\"type\":\"INTEGER\",\"name\":\"ID_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}],\"viral\":[],\"measures\":[{\"id\":414,\"type\":\"NUMBER\",\"name\":\"ME_1\",\"domainValue\":\"num_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"}]},\"messages\":[],\"command\":\"groupbyId_1\",\"ascalar\":false,\"acomponent\":false},{\"resultComponent\":{\"id\":410,\"type\":\"INTEGER\",\"name\":\"ID_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},\"messages\":[],\"command\":\"Id_1\",\"ascalar\":false,\"acomponent\":true},{\"result\":{\"id\":170,\"name\":\"temporary_169\",\"description\":null,\"attributes\":[],\"persistent\":false,\"identifiers\":[{\"id\":410,\"type\":\"INTEGER\",\"name\":\"ID_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":411,\"type\":\"STRING\",\"name\":\"ID_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":412,\"type\":\"STRING\",\"name\":\"ID_3\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}],\"viral\":[],\"measures\":[{\"id\":409,\"type\":\"NUMBER\",\"name\":\"ME_1\",\"domainValue\":\"num_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"}]},\"messages\":[],\"ascalar\":false,\"acomponent\":false},{\"result\":{\"id\":30,\"name\":\"DS_30\",\"description\":null,\"attributes\":[],\"persistent\":true,\"identifiers\":[{\"id\":119,\"type\":\"INTEGER\",\"name\":\"ID_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":120,\"type\":\"STRING\",\"name\":\"ID_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":121,\"type\":\"STRING\",\"name\":\"ID_3\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}],\"viral\":[],\"measures\":[{\"id\":122,\"type\":\"INTEGER\",\"name\":\"ME_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"}]},\"messages\":[],\"command\":\"DS_30\",\"ascalar\":false,\"acomponent\":false}]";
		List<ResultExpression> result = vtlTestUtils.getResult(commandStatements);
		List<ResultExpression> resultExpressionsToComapare = vtlTestUtils.convertJsonToResultExpression(jsonResult);
		assertNotNull("Conversione non riuscita", resultExpressionsToComapare);
		assertNotNull("Validazione Semantica non riuscita", result);
		assertTrue("I due risultati non sono uguali",
				vtlTestUtils.compareResult(result.get(0), resultExpressionsToComapare.get(0)));

	}

	@Test
	@Transactional
	public void varPop() throws Exception {
		VtlDataset vtlDatasetFind = datasetRepository.findByNameIgnoreCase("DS_1");
		if (vtlDatasetFind == null) {
			VtlDataset vtlDataset = new VtlDataset();

			vtlDataset.setPersistent(true);
			vtlDataset.setName("DS_30");
			vtlDataset.setIsOnlyAScalar(false);
			vtlDataset.setTransitory(false);
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_1", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_2", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_3", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_1", VtlType.INTEGER, VtlComponentRole.MEASURE));
			datasetRepository.save(vtlDataset);
		}
		String commandStatements = "DS_r := var_pop ( DS_30 group by Id_1 );";
		String jsonResult = "[{\"result\":{\"id\":10,\"name\":\"DS_r\",\"description\":null,\"attributes\":[],\"persistent\":false,\"measures\":[{\"id\":17,\"type\":\"NUMBER\",\"name\":\"ME_1\",\"domainValue\":\"num_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"viral\":[],\"identifiers\":[{\"id\":1,\"type\":\"INTEGER\",\"name\":\"ID_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}]},\"messages\":[],\"command\":\"DS_r:=var_pop(DS_30groupbyId_1);\",\"acomponent\":false,\"ascalar\":false},{\"result\":{\"id\":10,\"name\":\"DS_r\",\"description\":null,\"attributes\":[],\"persistent\":false,\"measures\":[{\"id\":17,\"type\":\"NUMBER\",\"name\":\"ME_1\",\"domainValue\":\"num_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"viral\":[],\"identifiers\":[{\"id\":1,\"type\":\"INTEGER\",\"name\":\"ID_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}]},\"messages\":[],\"command\":\"groupbyId_1\",\"acomponent\":false,\"ascalar\":false},{\"result\":{\"id\":8,\"name\":\"temporary_7\",\"description\":null,\"attributes\":[],\"persistent\":false,\"measures\":[{\"id\":17,\"type\":\"NUMBER\",\"name\":\"ME_1\",\"domainValue\":\"num_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"viral\":[],\"identifiers\":[{\"id\":1,\"type\":\"INTEGER\",\"name\":\"ID_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":2,\"type\":\"STRING\",\"name\":\"ID_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":3,\"type\":\"STRING\",\"name\":\"ID_3\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}]},\"messages\":[],\"acomponent\":false,\"ascalar\":false},{\"result\":{\"id\":1,\"name\":\"DS_30\",\"description\":null,\"attributes\":[],\"persistent\":true,\"measures\":[{\"id\":4,\"type\":\"INTEGER\",\"name\":\"ME_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"viral\":[],\"identifiers\":[{\"id\":1,\"type\":\"INTEGER\",\"name\":\"ID_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":2,\"type\":\"STRING\",\"name\":\"ID_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":3,\"type\":\"STRING\",\"name\":\"ID_3\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}]},\"messages\":[],\"command\":\"DS_30\",\"acomponent\":false,\"ascalar\":false}]";
		List<ResultExpression> result = vtlTestUtils.getResult(commandStatements);
		List<ResultExpression> resultExpressionsToComapare = vtlTestUtils.convertJsonToResultExpression(jsonResult);
		assertNotNull("Conversione non riuscita", resultExpressionsToComapare);
		assertNotNull("Validazione Semantica non riuscita", result);
		assertTrue("I due risultati non sono uguali",
				vtlTestUtils.compareResult(result.get(0), resultExpressionsToComapare.get(0)));

	}

	@Test
	@Transactional
	public void varSamp() throws Exception {
		VtlDataset vtlDatasetFind = datasetRepository.findByNameIgnoreCase("DS_1");
		if (vtlDatasetFind == null) {
			VtlDataset vtlDataset = new VtlDataset();

			vtlDataset.setPersistent(true);
			vtlDataset.setName("DS_30");
			vtlDataset.setIsOnlyAScalar(false);
			vtlDataset.setTransitory(false);
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_1", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_2", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_3", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_1", VtlType.INTEGER, VtlComponentRole.MEASURE));
			datasetRepository.save(vtlDataset);
		}

		String commandStatements = "DS_r := var_samp ( DS_30 group by Id_1 );";
		String jsonResult = "[{\"result\":{\"id\":76,\"name\":\"DS_r\",\"description\":null,\"attributes\":[],\"persistent\":false,\"viral\":[],\"identifiers\":[{\"id\":6,\"type\":\"INTEGER\",\"name\":\"ID_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}],\"measures\":[{\"id\":74,\"type\":\"INTEGER\",\"name\":\"ME_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"}]},\"messages\":[],\"command\":\"DS_r:=var_samp(DS_30groupbyId_1);\",\"ascalar\":false,\"acomponent\":false},{\"result\":{\"id\":76,\"name\":\"DS_r\",\"description\":null,\"attributes\":[],\"persistent\":false,\"viral\":[],\"identifiers\":[{\"id\":6,\"type\":\"INTEGER\",\"name\":\"ID_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}],\"measures\":[{\"id\":74,\"type\":\"INTEGER\",\"name\":\"ME_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"}]},\"messages\":[],\"command\":\"groupbyId_1\",\"ascalar\":false,\"acomponent\":false},{\"result\":{\"id\":74,\"name\":\"temporary_73\",\"description\":null,\"attributes\":[],\"persistent\":false,\"viral\":[],\"identifiers\":[{\"id\":6,\"type\":\"INTEGER\",\"name\":\"ID_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":7,\"type\":\"STRING\",\"name\":\"ID_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":8,\"type\":\"STRING\",\"name\":\"ID_3\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}],\"measures\":[{\"id\":74,\"type\":\"INTEGER\",\"name\":\"ME_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"}]},\"messages\":[],\"ascalar\":false,\"acomponent\":false},{\"result\":{\"id\":2,\"name\":\"DS_30\",\"description\":null,\"attributes\":[],\"persistent\":true,\"viral\":[],\"identifiers\":[{\"id\":6,\"type\":\"INTEGER\",\"name\":\"ID_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":7,\"type\":\"STRING\",\"name\":\"ID_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":8,\"type\":\"STRING\",\"name\":\"ID_3\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}],\"measures\":[{\"id\":9,\"type\":\"INTEGER\",\"name\":\"ME_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"}]},\"messages\":[],\"command\":\"DS_30\",\"ascalar\":false,\"acomponent\":false}]";
		List<ResultExpression> result = vtlTestUtils.getResult(commandStatements);
		List<ResultExpression> resultExpressionsToComapare = vtlTestUtils.convertJsonToResultExpression(jsonResult);
		assertNotNull("Conversione non riuscita", resultExpressionsToComapare);
		assertNotNull("Validazione Semantica non riuscita", result);
		assertTrue("I due risultati non sono uguali",
				vtlTestUtils.compareResult(result.get(0), resultExpressionsToComapare.get(0)));
	}

	@Test
	@Transactional
	public void firstValue() throws Exception {
		VtlDataset vtlDatasetFind = datasetRepository.findByNameIgnoreCase("DS_1");
		if (vtlDatasetFind == null) {
			VtlDataset vtlDataset = new VtlDataset();

			vtlDataset.setPersistent(true);
			vtlDataset.setName("DS_34");
			vtlDataset.setIsOnlyAScalar(false);
			vtlDataset.setTransitory(false);
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_1", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_2", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_3", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_1", VtlType.INTEGER, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_2", VtlType.INTEGER, VtlComponentRole.MEASURE));
			datasetRepository.save(vtlDataset);
		}
		String commandStatements = "DS_r := first_value ( DS_34 over ( partition by  Id_1, Id_2 order by  Id_3  data points between 1 preceding and 1 following) );";
		String jsonResult = "[{\"result\":{\"id\":186,\"name\":\"DS_r\",\"description\":null,\"attributes\":[],\"persistent\":false,\"identifiers\":[{\"id\":444,\"type\":\"STRING\",\"name\":\"ID_1\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":445,\"type\":\"STRING\",\"name\":\"ID_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":446,\"type\":\"INTEGER\",\"name\":\"ID_3\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}],\"viral\":[],\"measures\":[{\"id\":442,\"type\":\"INTEGER\",\"name\":\"ME_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},{\"id\":443,\"type\":\"INTEGER\",\"name\":\"ME_2\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"}]},\"messages\":[],\"command\":\"DS_r:=first_value(DS_34over(partitionbyId_1,Id_2orderbyId_3datapointsbetween1precedingand1following));\",\"ascalar\":false,\"acomponent\":false},{\"result\":{\"id\":184,\"name\":\"temporary_183\",\"description\":null,\"attributes\":[],\"persistent\":false,\"identifiers\":[{\"id\":439,\"type\":\"STRING\",\"name\":\"ID_1\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":440,\"type\":\"STRING\",\"name\":\"ID_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":441,\"type\":\"INTEGER\",\"name\":\"ID_3\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}],\"viral\":[],\"measures\":[{\"id\":437,\"type\":\"INTEGER\",\"name\":\"ME_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},{\"id\":438,\"type\":\"INTEGER\",\"name\":\"ME_2\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"}]},\"messages\":[],\"ascalar\":false,\"acomponent\":false},{\"resultComponent\":{\"id\":139,\"type\":\"INTEGER\",\"name\":\"ID_3\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},\"messages\":[],\"command\":\"Id_3\",\"ascalar\":false,\"acomponent\":true},{\"resultComponent\":{\"id\":138,\"type\":\"STRING\",\"name\":\"ID_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},\"messages\":[],\"command\":\"Id_2\",\"ascalar\":false,\"acomponent\":true},{\"resultComponent\":{\"id\":137,\"type\":\"STRING\",\"name\":\"ID_1\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},\"messages\":[],\"command\":\"Id_1\",\"ascalar\":false,\"acomponent\":true}]";
		List<ResultExpression> result = vtlTestUtils.getResult(commandStatements);
		List<ResultExpression> resultExpressionsToComapare = vtlTestUtils.convertJsonToResultExpression(jsonResult);
		assertNotNull("Conversione non riuscita", resultExpressionsToComapare);
		assertNotNull("Validazione Semantica non riuscita", result);
		assertTrue("I due risultati non sono uguali",
				vtlTestUtils.compareResult(result.get(0), resultExpressionsToComapare.get(0)));

	}

	@Test
	@Transactional
	public void lastValue() throws Exception {
		VtlDataset vtlDatasetFind = datasetRepository.findByNameIgnoreCase("DS_1");
		if (vtlDatasetFind == null) {
			VtlDataset vtlDataset = new VtlDataset();

			vtlDataset.setPersistent(true);
			vtlDataset.setName("DS_34");
			vtlDataset.setIsOnlyAScalar(false);
			vtlDataset.setTransitory(false);
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_1", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_2", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_3", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_1", VtlType.INTEGER, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_2", VtlType.INTEGER, VtlComponentRole.MEASURE));
			datasetRepository.save(vtlDataset);
		}
		String commandStatements = "DS_r := last_value ( DS_34 over ( partition by Id_1, Id_2 order by Id_3 data points between 1 preceding and 1 following ) );";
		String jsonResult = "[{\"result\":{\"id\":182,\"name\":\"DS_r\",\"description\":null,\"attributes\":[],\"persistent\":false,\"identifiers\":[{\"id\":434,\"type\":\"STRING\",\"name\":\"ID_1\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":435,\"type\":\"STRING\",\"name\":\"ID_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":436,\"type\":\"INTEGER\",\"name\":\"ID_3\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}],\"viral\":[],\"measures\":[{\"id\":432,\"type\":\"INTEGER\",\"name\":\"ME_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},{\"id\":433,\"type\":\"INTEGER\",\"name\":\"ME_2\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"}]},\"messages\":[],\"command\":\"DS_r:=last_value(DS_34over(partitionbyId_1,Id_2orderbyId_3datapointsbetween1precedingand1following));\",\"ascalar\":false,\"acomponent\":false},{\"result\":{\"id\":180,\"name\":\"temporary_179\",\"description\":null,\"attributes\":[],\"persistent\":false,\"identifiers\":[{\"id\":429,\"type\":\"STRING\",\"name\":\"ID_1\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":430,\"type\":\"STRING\",\"name\":\"ID_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":431,\"type\":\"INTEGER\",\"name\":\"ID_3\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}],\"viral\":[],\"measures\":[{\"id\":427,\"type\":\"INTEGER\",\"name\":\"ME_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},{\"id\":428,\"type\":\"INTEGER\",\"name\":\"ME_2\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"}]},\"messages\":[],\"ascalar\":false,\"acomponent\":false},{\"resultComponent\":{\"id\":139,\"type\":\"INTEGER\",\"name\":\"ID_3\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},\"messages\":[],\"command\":\"Id_3\",\"ascalar\":false,\"acomponent\":true},{\"resultComponent\":{\"id\":138,\"type\":\"STRING\",\"name\":\"ID_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},\"messages\":[],\"command\":\"Id_2\",\"ascalar\":false,\"acomponent\":true},{\"resultComponent\":{\"id\":137,\"type\":\"STRING\",\"name\":\"ID_1\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},\"messages\":[],\"command\":\"Id_1\",\"ascalar\":false,\"acomponent\":true}]";
		List<ResultExpression> result = vtlTestUtils.getResult(commandStatements);
		List<ResultExpression> resultExpressionsToComapare = vtlTestUtils.convertJsonToResultExpression(jsonResult);
		assertNotNull("Conversione non riuscita", resultExpressionsToComapare);
		assertNotNull("Validazione Semantica non riuscita", result);
		assertTrue("I due risultati non sono uguali",
				vtlTestUtils.compareResult(result.get(0), resultExpressionsToComapare.get(0)));
	}

	@Test
	@Transactional
	public void lag() throws Exception {
		VtlDataset vtlDatasetFind = datasetRepository.findByNameIgnoreCase("DS_1");
		if (vtlDatasetFind == null) {
			VtlDataset vtlDataset = new VtlDataset();

			vtlDataset.setPersistent(true);
			vtlDataset.setName("DS_34");
			vtlDataset.setIsOnlyAScalar(false);
			vtlDataset.setTransitory(false);
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_1", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_2", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_3", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_1", VtlType.INTEGER, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_2", VtlType.INTEGER, VtlComponentRole.MEASURE));
			datasetRepository.save(vtlDataset);
		}
		String commandStatements = "DS_r := lag ( DS_34 , 1 over ( partition by  Id_1 , Id_2  order by  Id_3  ) );";
		String jsonResult = "[{\"result\":{\"id\":178,\"name\":\"DS_r\",\"description\":null,\"attributes\":[],\"persistent\":false,\"identifiers\":[{\"id\":424,\"type\":\"STRING\",\"name\":\"ID_1\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":425,\"type\":\"STRING\",\"name\":\"ID_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":426,\"type\":\"INTEGER\",\"name\":\"ID_3\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}],\"viral\":[],\"measures\":[{\"id\":422,\"type\":\"INTEGER\",\"name\":\"ME_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},{\"id\":423,\"type\":\"INTEGER\",\"name\":\"ME_2\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"}]},\"messages\":[],\"command\":\"DS_r:=lag(DS_34,1over(partitionbyId_1,Id_2orderbyId_3));\",\"ascalar\":false,\"acomponent\":false},{\"result\":{\"id\":176,\"name\":\"temporary_175\",\"description\":null,\"attributes\":[],\"persistent\":false,\"identifiers\":[{\"id\":419,\"type\":\"STRING\",\"name\":\"ID_1\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":420,\"type\":\"STRING\",\"name\":\"ID_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":421,\"type\":\"INTEGER\",\"name\":\"ID_3\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}],\"viral\":[],\"measures\":[{\"id\":417,\"type\":\"INTEGER\",\"name\":\"ME_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},{\"id\":418,\"type\":\"INTEGER\",\"name\":\"ME_2\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"}]},\"messages\":[],\"ascalar\":false,\"acomponent\":false},{\"resultComponent\":{\"id\":139,\"type\":\"INTEGER\",\"name\":\"ID_3\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},\"messages\":[],\"command\":\"Id_3\",\"ascalar\":false,\"acomponent\":true},{\"resultComponent\":{\"id\":138,\"type\":\"STRING\",\"name\":\"ID_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},\"messages\":[],\"command\":\"Id_2\",\"ascalar\":false,\"acomponent\":true},{\"resultComponent\":{\"id\":137,\"type\":\"STRING\",\"name\":\"ID_1\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},\"messages\":[],\"command\":\"Id_1\",\"ascalar\":false,\"acomponent\":true}]";
		List<ResultExpression> result = vtlTestUtils.getResult(commandStatements);
		List<ResultExpression> resultExpressionsToComapare = vtlTestUtils.convertJsonToResultExpression(jsonResult);
		assertNotNull("Conversione non riuscita", resultExpressionsToComapare);
		assertNotNull("Validazione Semantica non riuscita", result);
		assertTrue("I due risultati non sono uguali",
				vtlTestUtils.compareResult(result.get(0), resultExpressionsToComapare.get(0)));
	}

	@Test
	@Transactional
	public void lead() throws Exception {
		VtlDataset vtlDatasetFind = datasetRepository.findByNameIgnoreCase("DS_1");
		if (vtlDatasetFind == null) {
			VtlDataset vtlDataset = new VtlDataset();

			vtlDataset.setPersistent(true);
			vtlDataset.setName("DS_34");
			vtlDataset.setIsOnlyAScalar(false);
			vtlDataset.setTransitory(false);
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_1", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_2", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_3", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_1", VtlType.INTEGER, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_2", VtlType.INTEGER, VtlComponentRole.MEASURE));
			datasetRepository.save(vtlDataset);
		}
		String commandStatements = "DS_r := lead ( DS_34 , 1 over ( partition by Id_1 , Id_2 order by Id_3 ) );";
		String jsonResult = "[{\"result\":{\"id\":190,\"name\":\"DS_r\",\"description\":null,\"attributes\":[],\"persistent\":false,\"identifiers\":[{\"id\":454,\"type\":\"STRING\",\"name\":\"ID_1\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":455,\"type\":\"STRING\",\"name\":\"ID_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":456,\"type\":\"INTEGER\",\"name\":\"ID_3\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}],\"viral\":[],\"measures\":[{\"id\":452,\"type\":\"INTEGER\",\"name\":\"ME_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},{\"id\":453,\"type\":\"INTEGER\",\"name\":\"ME_2\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"}]},\"messages\":[],\"command\":\"DS_r:=lead(DS_34,1over(partitionbyId_1,Id_2orderbyId_3));\",\"ascalar\":false,\"acomponent\":false},{\"result\":{\"id\":188,\"name\":\"temporary_187\",\"description\":null,\"attributes\":[],\"persistent\":false,\"identifiers\":[{\"id\":449,\"type\":\"STRING\",\"name\":\"ID_1\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":450,\"type\":\"STRING\",\"name\":\"ID_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":451,\"type\":\"INTEGER\",\"name\":\"ID_3\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}],\"viral\":[],\"measures\":[{\"id\":447,\"type\":\"INTEGER\",\"name\":\"ME_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},{\"id\":448,\"type\":\"INTEGER\",\"name\":\"ME_2\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"}]},\"messages\":[],\"ascalar\":false,\"acomponent\":false},{\"resultComponent\":{\"id\":139,\"type\":\"INTEGER\",\"name\":\"ID_3\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},\"messages\":[],\"command\":\"Id_3\",\"ascalar\":false,\"acomponent\":true},{\"resultComponent\":{\"id\":138,\"type\":\"STRING\",\"name\":\"ID_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},\"messages\":[],\"command\":\"Id_2\",\"ascalar\":false,\"acomponent\":true},{\"resultComponent\":{\"id\":137,\"type\":\"STRING\",\"name\":\"ID_1\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},\"messages\":[],\"command\":\"Id_1\",\"ascalar\":false,\"acomponent\":true}]";
		List<ResultExpression> result = vtlTestUtils.getResult(commandStatements);
		List<ResultExpression> resultExpressionsToComapare = vtlTestUtils.convertJsonToResultExpression(jsonResult);
		assertNotNull("Conversione non riuscita", resultExpressionsToComapare);
		assertNotNull("Validazione Semantica non riuscita", result);
		assertTrue("I due risultati non sono uguali",
				vtlTestUtils.compareResult(result.get(0), resultExpressionsToComapare.get(0)));
	}

	@Test
	@Transactional
	public void rank() throws Exception {
		VtlDataset vtlDatasetFind = datasetRepository.findByNameIgnoreCase("DS_1");
		if (vtlDatasetFind == null) {
			VtlDataset vtlDataset = new VtlDataset();

			vtlDataset.setPersistent(true);
			vtlDataset.setName("DS_34");
			vtlDataset.setIsOnlyAScalar(false);
			vtlDataset.setTransitory(false);
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_1", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_2", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_3", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_1", VtlType.INTEGER, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_2", VtlType.INTEGER, VtlComponentRole.MEASURE));
			datasetRepository.save(vtlDataset);
		}
        String commandStatements = "DS_r := DS_34 [ calc Me_2 := rank ( over ( partition by Id_1 , Id_2 order by Me_1 ) ) ];";

		String jsonResult = "[{\"result\":{\"id\":95,\"name\":\"DS_r\",\"description\":null,\"attributes\":[],\"persistent\":false,\"viral\":[],\"identifiers\":[{\"id\":206,\"type\":\"STRING\",\"name\":\"ID_1\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":207,\"type\":\"STRING\",\"name\":\"ID_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":208,\"type\":\"INTEGER\",\"name\":\"ID_3\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}],\"measures\":[{\"id\":209,\"type\":\"INTEGER\",\"name\":\"ME_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},{\"id\":210,\"type\":\"INTEGER\",\"name\":\"Me_2\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"}]},\"messages\":[],\"command\":\"DS_r:=DS_34[calcMe_2:=rank(ME_1over(partitionbyId_1,Id_2orderbyMe_1))];\",\"acomponent\":false,\"ascalar\":false},{\"result\":{\"id\":93,\"name\":\"temporary_92\",\"description\":null,\"attributes\":[],\"persistent\":false,\"viral\":[],\"identifiers\":[{\"id\":200,\"type\":\"STRING\",\"name\":\"ID_1\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":201,\"type\":\"STRING\",\"name\":\"ID_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":202,\"type\":\"INTEGER\",\"name\":\"ID_3\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}],\"measures\":[{\"id\":203,\"type\":\"INTEGER\",\"name\":\"ME_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},{\"id\":205,\"type\":\"INTEGER\",\"name\":\"Me_2\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"}]},\"messages\":[],\"command\":\"calcMe_2:=rank(ME_1over(partitionbyId_1,Id_2orderbyMe_1))\",\"acomponent\":false,\"ascalar\":false},{\"resultComponent\":{\"id\":null,\"type\":\"INTEGER\",\"name\":\"Me_2\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},\"messages\":[],\"command\":\"Me_2:=rank(ME_1over(partitionbyId_1,Id_2orderbyMe_1))\",\"acomponent\":true,\"ascalar\":false},{\"resultComponent\":{\"id\":null,\"type\":\"INTEGER\",\"name\":\"int_var\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},\"messages\":[],\"acomponent\":true,\"ascalar\":false},{\"resultComponent\":{\"id\":198,\"type\":\"INTEGER\",\"name\":\"ME_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},\"messages\":[],\"command\":\"Me_1\",\"acomponent\":true,\"ascalar\":false},{\"resultComponent\":{\"id\":196,\"type\":\"STRING\",\"name\":\"ID_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},\"messages\":[],\"command\":\"Id_2\",\"acomponent\":true,\"ascalar\":false},{\"resultComponent\":{\"id\":195,\"type\":\"STRING\",\"name\":\"ID_1\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},\"messages\":[],\"command\":\"Id_1\",\"acomponent\":true,\"ascalar\":false}]";
		List<ResultExpression> result = vtlTestUtils.getResult(commandStatements);
		System.out.println(vtlTestUtils.convertResultToJson(result));
		List<ResultExpression> resultExpressionsToComapare = vtlTestUtils.convertJsonToResultExpression(jsonResult);
		assertNotNull("Conversione non riuscita", resultExpressionsToComapare);
		assertNotNull("Validazione Semantica non riuscita", result);
		assertTrue("I due risultati non sono uguali",
				vtlTestUtils.compareResult(result.get(0), resultExpressionsToComapare.get(0)));

	}

	@Test
	@Transactional
	public void ratioToReport() throws Exception {
		VtlDataset vtlDatasetFind = datasetRepository.findByNameIgnoreCase("DS_1");
		if (vtlDatasetFind == null) {
			VtlDataset vtlDataset = new VtlDataset();

			vtlDataset.setPersistent(true);
			vtlDataset.setName("DS_34");
			vtlDataset.setIsOnlyAScalar(false);
			vtlDataset.setTransitory(false);
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_1", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_2", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_3", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_1", VtlType.INTEGER, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_2", VtlType.INTEGER, VtlComponentRole.MEASURE));
			datasetRepository.save(vtlDataset);
		}
		String commandStatements = "DS_r := ratio_to_report ( DS_34  over ( partition by  Id_1, Id_2  ) );";
		String jsonResult = "[{\"result\":{\"id\":194,\"name\":\"DS_r\",\"description\":null,\"attributes\":[],\"persistent\":false,\"identifiers\":[{\"id\":464,\"type\":\"STRING\",\"name\":\"ID_1\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":465,\"type\":\"STRING\",\"name\":\"ID_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":466,\"type\":\"INTEGER\",\"name\":\"ID_3\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}],\"viral\":[],\"measures\":[{\"id\":462,\"type\":\"NUMBER\",\"name\":\"ME_1\",\"domainValue\":\"num_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},{\"id\":463,\"type\":\"NUMBER\",\"name\":\"ME_2\",\"domainValue\":\"num_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"}]},\"messages\":[],\"command\":\"DS_r:=ratio_to_report(DS_34over(partitionbyId_1,Id_2));\",\"ascalar\":false,\"acomponent\":false},{\"result\":{\"id\":192,\"name\":\"temporary_191\",\"description\":null,\"attributes\":[],\"persistent\":false,\"identifiers\":[{\"id\":459,\"type\":\"STRING\",\"name\":\"ID_1\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":460,\"type\":\"STRING\",\"name\":\"ID_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":461,\"type\":\"INTEGER\",\"name\":\"ID_3\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}],\"viral\":[],\"measures\":[{\"id\":457,\"type\":\"NUMBER\",\"name\":\"ME_1\",\"domainValue\":\"num_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},{\"id\":458,\"type\":\"NUMBER\",\"name\":\"ME_2\",\"domainValue\":\"num_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"}]},\"messages\":[],\"ascalar\":false,\"acomponent\":false},{\"resultComponent\":{\"id\":138,\"type\":\"STRING\",\"name\":\"ID_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},\"messages\":[],\"command\":\"Id_2\",\"ascalar\":false,\"acomponent\":true},{\"resultComponent\":{\"id\":137,\"type\":\"STRING\",\"name\":\"ID_1\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},\"messages\":[],\"command\":\"Id_1\",\"ascalar\":false,\"acomponent\":true}]";
		List<ResultExpression> result = vtlTestUtils.getResult(commandStatements);
		List<ResultExpression> resultExpressionsToComapare = vtlTestUtils.convertJsonToResultExpression(jsonResult);
		assertNotNull("Conversione non riuscita", resultExpressionsToComapare);
		assertNotNull("Validazione Semantica non riuscita", result);
		assertTrue("I due risultati non sono uguali",
				vtlTestUtils.compareResult(result.get(0), resultExpressionsToComapare.get(0)));

	}

}
