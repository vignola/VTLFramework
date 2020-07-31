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

public class VtlConditionalOperatorsTest {

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
	public void nvl() throws Exception {
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
		String commandStatements = "DS_r := nvl ( DS_18, 0 );";
		String jsonResult = "[{\"result\":{\"id\":214,\"name\":\"DS_r\",\"description\":null,\"attributes\":[],\"persistent\":false,\"identifiers\":[{\"id\":505,\"type\":\"INTEGER\",\"name\":\"ID_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":506,\"type\":\"STRING\",\"name\":\"ID_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":507,\"type\":\"STRING\",\"name\":\"ID_3\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":508,\"type\":\"STRING\",\"name\":\"ID_4\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}],\"viral\":[],\"measures\":[{\"id\":504,\"type\":\"INTEGER\",\"name\":\"ME_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"}]},\"messages\":[],\"command\":\"DS_r:=nvl(DS_18,0);\",\"ascalar\":false,\"acomponent\":false},{\"result\":{\"id\":212,\"name\":\"temporary_211\",\"description\":null,\"attributes\":[],\"persistent\":false,\"identifiers\":[{\"id\":500,\"type\":\"INTEGER\",\"name\":\"ID_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":501,\"type\":\"STRING\",\"name\":\"ID_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":502,\"type\":\"STRING\",\"name\":\"ID_3\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":503,\"type\":\"STRING\",\"name\":\"ID_4\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}],\"viral\":[],\"measures\":[{\"id\":499,\"type\":\"INTEGER\",\"name\":\"ME_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"}]},\"messages\":[],\"command\":\"nvl(DS_18,0)\",\"ascalar\":false,\"acomponent\":false},{\"result\":{\"id\":18,\"name\":\"DS_18\",\"description\":null,\"attributes\":[],\"persistent\":true,\"identifiers\":[{\"id\":69,\"type\":\"INTEGER\",\"name\":\"ID_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":70,\"type\":\"STRING\",\"name\":\"ID_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":71,\"type\":\"STRING\",\"name\":\"ID_3\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":72,\"type\":\"STRING\",\"name\":\"ID_4\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}],\"viral\":[],\"measures\":[{\"id\":73,\"type\":\"INTEGER\",\"name\":\"ME_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"}]},\"messages\":[],\"command\":\"DS_18\",\"ascalar\":false,\"acomponent\":false},{\"result\":{\"id\":210,\"name\":\"temporary_209\",\"description\":null,\"attributes\":[],\"persistent\":false,\"identifiers\":[],\"viral\":[],\"measures\":[{\"id\":498,\"type\":\"INTEGER\",\"name\":\"int_var\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"}]},\"resultComponent\":{\"id\":498,\"type\":\"INTEGER\",\"name\":\"int_var\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},\"messages\":[],\"command\":\"0\",\"scalarComponent\":{\"id\":498,\"type\":\"INTEGER\",\"name\":\"int_var\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},\"ascalar\":true,\"acomponent\":false}]";
		List<ResultExpression> result = vtlTestUtils.getResult(commandStatements);
		List<ResultExpression> resultExpressionsToComapare = vtlTestUtils.convertJsonToResultExpression(jsonResult);
		assertNotNull("Conversione non riuscita", resultExpressionsToComapare);
		assertNotNull("Validazione Semantica non riuscita", result);
		assertTrue("I due risultati non sono uguali",
				vtlTestUtils.compareResult(result.get(0), resultExpressionsToComapare.get(0)));

	}

	@Test
	@Transactional
	public void ifThenElse() throws Exception {
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
			vtlDatasetSecond.setName("DS_11");
			vtlDatasetSecond.setIsOnlyAScalar(false);
			vtlDatasetSecond.setTransitory(false);
			vtlDatasetSecond
					.addComponent(vtlTestUtils.getVtlComponent("ID_1", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDatasetSecond
					.addComponent(vtlTestUtils.getVtlComponent("ID_2", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDatasetSecond
					.addComponent(vtlTestUtils.getVtlComponent("ID_3", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDatasetSecond
					.addComponent(vtlTestUtils.getVtlComponent("ID_4", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDatasetSecond
					.addComponent(vtlTestUtils.getVtlComponent("ME_1", VtlType.NUMBER, VtlComponentRole.MEASURE));
			datasetRepository.save(vtlDatasetSecond);

			VtlDataset vtlDatasetThird = new VtlDataset();
			vtlDatasetThird.setPersistent(true);
			vtlDatasetThird.setName("DS_28");
			vtlDatasetThird.setIsOnlyAScalar(false);
			vtlDatasetThird.setTransitory(false);
			vtlDatasetThird
					.addComponent(vtlTestUtils.getVtlComponent("ID_1", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDatasetThird
					.addComponent(vtlTestUtils.getVtlComponent("ID_2", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDatasetThird
					.addComponent(vtlTestUtils.getVtlComponent("ID_3", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDatasetThird
					.addComponent(vtlTestUtils.getVtlComponent("ID_4", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDatasetThird
					.addComponent(vtlTestUtils.getVtlComponent("ME_1", VtlType.NUMBER, VtlComponentRole.MEASURE));
			datasetRepository.save(vtlDatasetThird);

		}
		String commandStatements = "DS_r := if ( DS_18#Id_4 = \"F\" ) then DS_11 else DS_28;";
		String jsonResult = "[{\"result\":{\"id\":208,\"name\":\"DS_r\",\"description\":null,\"attributes\":[],\"persistent\":false,\"identifiers\":[{\"id\":493,\"type\":\"INTEGER\",\"name\":\"ID_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":494,\"type\":\"STRING\",\"name\":\"ID_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":495,\"type\":\"STRING\",\"name\":\"ID_3\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":496,\"type\":\"STRING\",\"name\":\"ID_4\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}],\"viral\":[],\"measures\":[{\"id\":497,\"type\":\"NUMBER\",\"name\":\"ME_1\",\"domainValue\":\"num_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"}]},\"messages\":[],\"command\":\"DS_r:=if(DS_18#Id_4=\\\"F\\\\\\\")thenDS_11elseDS_28;\",\"ascalar\":false,\"acomponent\":false},{\"result\":{\"id\":28,\"name\":\"DS_28\",\"description\":null,\"attributes\":[],\"persistent\":true,\"identifiers\":[{\"id\":109,\"type\":\"INTEGER\",\"name\":\"ID_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":110,\"type\":\"STRING\",\"name\":\"ID_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":111,\"type\":\"STRING\",\"name\":\"ID_3\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":112,\"type\":\"STRING\",\"name\":\"ID_4\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}],\"viral\":[],\"measures\":[{\"id\":113,\"type\":\"NUMBER\",\"name\":\"ME_1\",\"domainValue\":\"num_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"}]},\"messages\":[],\"command\":\"DS_28\",\"ascalar\":false,\"acomponent\":false},{\"result\":{\"id\":11,\"name\":\"DS_11\",\"description\":null,\"attributes\":[],\"persistent\":true,\"identifiers\":[{\"id\":37,\"type\":\"INTEGER\",\"name\":\"ID_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":38,\"type\":\"STRING\",\"name\":\"ID_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":39,\"type\":\"STRING\",\"name\":\"ID_3\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":40,\"type\":\"STRING\",\"name\":\"ID_4\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}],\"viral\":[],\"measures\":[{\"id\":41,\"type\":\"NUMBER\",\"name\":\"ME_1\",\"domainValue\":\"num_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"}]},\"messages\":[],\"command\":\"DS_11\",\"ascalar\":false,\"acomponent\":false},{\"result\":{\"id\":204,\"name\":\"temporary_203\",\"description\":null,\"attributes\":[],\"persistent\":false,\"identifiers\":[{\"id\":484,\"type\":\"INTEGER\",\"name\":\"ID_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":485,\"type\":\"STRING\",\"name\":\"ID_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":486,\"type\":\"STRING\",\"name\":\"ID_3\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":487,\"type\":\"STRING\",\"name\":\"ID_4\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}],\"viral\":[],\"measures\":[{\"id\":483,\"type\":\"BOOLEAN\",\"name\":\"bool_var\",\"domainValue\":\"bool_vd\",\"domainValueParent\":\"bool_vd\",\"vtlComponentRole\":\"MEASURE\"}]},\"messages\":[],\"ascalar\":false,\"acomponent\":false},{\"result\":{\"id\":202,\"name\":\"temporary_201\",\"description\":null,\"attributes\":[],\"persistent\":false,\"identifiers\":[{\"id\":479,\"type\":\"INTEGER\",\"name\":\"ID_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":480,\"type\":\"STRING\",\"name\":\"ID_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":481,\"type\":\"STRING\",\"name\":\"ID_3\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":482,\"type\":\"STRING\",\"name\":\"ID_4\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}],\"viral\":[],\"measures\":[{\"id\":478,\"type\":\"BOOLEAN\",\"name\":\"bool_var\",\"domainValue\":\"bool_vd\",\"domainValueParent\":\"bool_vd\",\"vtlComponentRole\":\"MEASURE\"}]},\"messages\":[],\"command\":\"DS_18#Id_4=\\\"F\\\\\\\"\",\"ascalar\":false,\"acomponent\":false},{\"result\":{\"id\":196,\"name\":\"temporary_195\",\"description\":\"DS_18\",\"attributes\":[],\"persistent\":false,\"identifiers\":[{\"id\":467,\"type\":\"INTEGER\",\"name\":\"ID_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":468,\"type\":\"STRING\",\"name\":\"ID_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":469,\"type\":\"STRING\",\"name\":\"ID_3\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":470,\"type\":\"STRING\",\"name\":\"ID_4\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}],\"viral\":[],\"measures\":[{\"id\":471,\"type\":\"STRING\",\"name\":\"string_var\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"MEASURE\"}]},\"messages\":[],\"command\":\"DS_18#Id_4\",\"ascalar\":false,\"acomponent\":false},{\"result\":{\"id\":18,\"name\":\"DS_18\",\"description\":null,\"attributes\":[],\"persistent\":true,\"identifiers\":[{\"id\":69,\"type\":\"INTEGER\",\"name\":\"ID_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":70,\"type\":\"STRING\",\"name\":\"ID_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":71,\"type\":\"STRING\",\"name\":\"ID_3\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":72,\"type\":\"STRING\",\"name\":\"ID_4\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}],\"viral\":[],\"measures\":[{\"id\":73,\"type\":\"INTEGER\",\"name\":\"ME_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"}]},\"messages\":[],\"command\":\"DS_18\",\"ascalar\":false,\"acomponent\":false},{\"result\":{\"id\":198,\"name\":\"temporary_197\",\"description\":null,\"attributes\":[],\"persistent\":false,\"identifiers\":[],\"viral\":[],\"measures\":[{\"id\":472,\"type\":\"STRING\",\"name\":\"string_var\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"MEASURE\"}]},\"resultComponent\":{\"id\":472,\"type\":\"STRING\",\"name\":\"string_var\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"MEASURE\"},\"messages\":[],\"command\":\"\\\"F\\\\\\\"\",\"scalarComponent\":{\"id\":472,\"type\":\"STRING\",\"name\":\"string_var\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"MEASURE\"},\"ascalar\":true,\"acomponent\":false}]";
		List<ResultExpression> result = vtlTestUtils.getResult(commandStatements);
		List<ResultExpression> resultExpressionsToComapare = vtlTestUtils.convertJsonToResultExpression(jsonResult);
		assertNotNull("Conversione non riuscita", resultExpressionsToComapare);
		assertNotNull("Validazione Semantica non riuscita", result);
		assertTrue("I due risultati non sono uguali",
				vtlTestUtils.compareResult(result.get(0), resultExpressionsToComapare.get(0)));

	}

}
