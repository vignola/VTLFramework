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
public class VtlJoinOperatorsTest {

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
	public void innerJoin() throws Exception {
		VtlDataset vtlDatasetFind = datasetRepository.findByNameIgnoreCase("DS_1");
		if (vtlDatasetFind == null) {
			VtlDataset vtlDataset = new VtlDataset();

			vtlDataset.setPersistent(true);
			vtlDataset.setName("DS_4");
			vtlDataset.setIsOnlyAScalar(false);
			vtlDataset.setTransitory(false);
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_1", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_2", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_1", VtlType.STRING, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_2", VtlType.STRING, VtlComponentRole.MEASURE));
			datasetRepository.save(vtlDataset);

			VtlDataset vtlDatasetSecond = new VtlDataset();
			vtlDatasetSecond.setPersistent(true);
			vtlDatasetSecond.setName("DS_36");
			vtlDatasetSecond.setIsOnlyAScalar(false);
			vtlDatasetSecond.setTransitory(false);
			vtlDatasetSecond
					.addComponent(vtlTestUtils.getVtlComponent("ID_1", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDatasetSecond
					.addComponent(vtlTestUtils.getVtlComponent("ID_2", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDatasetSecond
					.addComponent(vtlTestUtils.getVtlComponent("ME_1A", VtlType.STRING, VtlComponentRole.MEASURE));
			vtlDatasetSecond
					.addComponent(vtlTestUtils.getVtlComponent("ME_2", VtlType.STRING, VtlComponentRole.MEASURE));
			datasetRepository.save(vtlDatasetSecond);
		}
		String commandStatements = "DS_r  := inner_join ( DS_4 as d1, DS_36 as d2 keep Me_1, d2#Me_2, Me_1A);";
		String jsonResult = "[{\"result\":{\"id\":14,\"name\":\"DS_r\",\"description\":null,\"attributes\":[],\"persistent\":false,\"identifiers\":[{\"id\":33,\"type\":\"INTEGER\",\"name\":\"ID_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":34,\"type\":\"STRING\",\"name\":\"ID_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}],\"viral\":[],\"measures\":[{\"id\":35,\"type\":\"STRING\",\"name\":\"ME_1\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"MEASURE\"},{\"id\":36,\"type\":\"STRING\",\"name\":\"ME_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"MEASURE\"},{\"id\":37,\"type\":\"STRING\",\"name\":\"ME_1A\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"MEASURE\"}]},\"messages\":[],\"command\":\"DS_r:=inner_join(DS_4asd1,DS_36asd2keepMe_1,d2#Me_2,Me_1A);\",\"ascalar\":false,\"acomponent\":false},{\"result\":{\"id\":12,\"name\":\"temporary_11\",\"description\":null,\"attributes\":[],\"persistent\":false,\"identifiers\":[{\"id\":28,\"type\":\"INTEGER\",\"name\":\"ID_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":29,\"type\":\"STRING\",\"name\":\"ID_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}],\"viral\":[],\"measures\":[{\"id\":30,\"type\":\"STRING\",\"name\":\"ME_1\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"MEASURE\"},{\"id\":31,\"type\":\"STRING\",\"name\":\"ME_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"MEASURE\"},{\"id\":32,\"type\":\"STRING\",\"name\":\"ME_1A\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"MEASURE\"}]},\"messages\":[],\"command\":\"inner_join(DS_4asd1,DS_36asd2keepMe_1,d2#Me_2,Me_1A)\",\"ascalar\":false,\"acomponent\":false},{\"result\":{\"id\":10,\"name\":\"temporary_9\",\"description\":null,\"attributes\":[],\"persistent\":false,\"identifiers\":[{\"id\":23,\"type\":\"INTEGER\",\"name\":\"ID_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":24,\"type\":\"STRING\",\"name\":\"ID_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}],\"viral\":[],\"measures\":[{\"id\":25,\"type\":\"STRING\",\"name\":\"ME_1\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"MEASURE\"},{\"id\":26,\"type\":\"STRING\",\"name\":\"d2#ME_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"MEASURE\"},{\"id\":27,\"type\":\"STRING\",\"name\":\"ME_1A\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"MEASURE\"}]},\"messages\":[],\"command\":\"keepMe_1,d2#Me_2,Me_1A\",\"ascalar\":false,\"acomponent\":false},{\"result\":{\"id\":8,\"name\":\"temporary_7\",\"description\":null,\"attributes\":[],\"persistent\":false,\"identifiers\":[{\"id\":17,\"type\":\"INTEGER\",\"name\":\"ID_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":18,\"type\":\"STRING\",\"name\":\"ID_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}],\"viral\":[],\"measures\":[{\"id\":19,\"type\":\"STRING\",\"name\":\"ME_1\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"MEASURE\"},{\"id\":20,\"type\":\"STRING\",\"name\":\"d1#ME_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"MEASURE\"},{\"id\":21,\"type\":\"STRING\",\"name\":\"ME_1A\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"MEASURE\"},{\"id\":22,\"type\":\"STRING\",\"name\":\"d2#ME_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"MEASURE\"}]},\"messages\":[],\"ascalar\":false,\"acomponent\":false},{\"result\":{\"id\":6,\"name\":\"d2\",\"description\":null,\"attributes\":[],\"persistent\":false,\"identifiers\":[{\"id\":13,\"type\":\"INTEGER\",\"name\":\"ID_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":14,\"type\":\"STRING\",\"name\":\"ID_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}],\"viral\":[],\"measures\":[{\"id\":15,\"type\":\"STRING\",\"name\":\"ME_1A\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"MEASURE\"},{\"id\":16,\"type\":\"STRING\",\"name\":\"ME_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"MEASURE\"}]},\"messages\":[],\"ascalar\":false,\"acomponent\":false},{\"result\":{\"id\":4,\"name\":\"d1\",\"description\":null,\"attributes\":[],\"persistent\":false,\"identifiers\":[{\"id\":9,\"type\":\"INTEGER\",\"name\":\"ID_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":10,\"type\":\"STRING\",\"name\":\"ID_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}],\"viral\":[],\"measures\":[{\"id\":11,\"type\":\"STRING\",\"name\":\"ME_1\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"MEASURE\"},{\"id\":12,\"type\":\"STRING\",\"name\":\"ME_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"MEASURE\"}]},\"messages\":[],\"ascalar\":false,\"acomponent\":false}]";
		List<ResultExpression> result = vtlTestUtils.getResult(commandStatements);
		List<ResultExpression> resultExpressionsToComapare = vtlTestUtils.convertJsonToResultExpression(jsonResult);
		assertNotNull("Conversione non riuscita", resultExpressionsToComapare);
		assertNotNull("Validazione Semantica non riuscita", result);
		assertTrue("I due risultati non sono uguali",
				vtlTestUtils.compareResult(result.get(0), resultExpressionsToComapare.get(0)));

	}

	@Test
	@Transactional
	public void innerJoinFilter() throws Exception {
		VtlDataset vtlDatasetFind = datasetRepository.findByNameIgnoreCase("DS_1");
		if (vtlDatasetFind == null) {
			VtlDataset vtlDataset = new VtlDataset();

			vtlDataset.setPersistent(true);
			vtlDataset.setName("DS_4");
			vtlDataset.setIsOnlyAScalar(false);
			vtlDataset.setTransitory(false);
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_1", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_2", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_1", VtlType.STRING, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_2", VtlType.STRING, VtlComponentRole.MEASURE));
			datasetRepository.save(vtlDataset);

			VtlDataset vtlDatasetSecond = new VtlDataset();
			vtlDatasetSecond.setPersistent(true);
			vtlDatasetSecond.setName("DS_36");
			vtlDatasetSecond.setIsOnlyAScalar(false);
			vtlDatasetSecond.setTransitory(false);
			vtlDatasetSecond
					.addComponent(vtlTestUtils.getVtlComponent("ID_1", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDatasetSecond
					.addComponent(vtlTestUtils.getVtlComponent("ID_2", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDatasetSecond
					.addComponent(vtlTestUtils.getVtlComponent("ME_1A", VtlType.STRING, VtlComponentRole.MEASURE));
			vtlDatasetSecond
					.addComponent(vtlTestUtils.getVtlComponent("ME_2", VtlType.STRING, VtlComponentRole.MEASURE));
			datasetRepository.save(vtlDatasetSecond);
		}
		String commandStatements = "DS_r := inner_join (DS_4 as d1, DS_36 as d2 filter Me_1 = \"A\" calc Me_4 := Me_1 || Me_1A drop d1#Me_2);";
		String jsonResult = "[{\"result\":{\"id\":66,\"name\":\"DS_r\",\"description\":null,\"attributes\":[],\"persistent\":false,\"viral\":[],\"measures\":[{\"id\":227,\"type\":\"STRING\",\"name\":\"ME_1\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"MEASURE\"},{\"id\":228,\"type\":\"STRING\",\"name\":\"ME_1A\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"MEASURE\"},{\"id\":229,\"type\":\"STRING\",\"name\":\"ME_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"MEASURE\"},{\"id\":230,\"type\":\"STRING\",\"name\":\"Me_4\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"identifiers\":[{\"id\":225,\"type\":\"INTEGER\",\"name\":\"ID_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":226,\"type\":\"STRING\",\"name\":\"ID_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}]},\"messages\":[],\"command\":\"DS_r:=inner_join(DS_4asd1,DS_36asd2filterMe_1=\\\"A\\\"calcMe_4:=Me_1||Me_1Adropd1#Me_2);\",\"acomponent\":false,\"ascalar\":false},{\"result\":{\"id\":64,\"name\":\"temporary_63\",\"description\":null,\"attributes\":[],\"persistent\":false,\"viral\":[],\"measures\":[{\"id\":221,\"type\":\"STRING\",\"name\":\"ME_1\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"MEASURE\"},{\"id\":222,\"type\":\"STRING\",\"name\":\"ME_1A\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"MEASURE\"},{\"id\":223,\"type\":\"STRING\",\"name\":\"ME_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"MEASURE\"},{\"id\":224,\"type\":\"STRING\",\"name\":\"Me_4\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"identifiers\":[{\"id\":219,\"type\":\"INTEGER\",\"name\":\"ID_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":220,\"type\":\"STRING\",\"name\":\"ID_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}]},\"messages\":[],\"command\":\"inner_join(DS_4asd1,DS_36asd2filterMe_1=\\\"A\\\"calcMe_4:=Me_1||Me_1Adropd1#Me_2)\",\"acomponent\":false,\"ascalar\":false},{\"result\":{\"id\":62,\"name\":\"temporary_61\",\"description\":null,\"attributes\":[],\"persistent\":false,\"viral\":[],\"measures\":[{\"id\":215,\"type\":\"STRING\",\"name\":\"ME_1\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"MEASURE\"},{\"id\":216,\"type\":\"STRING\",\"name\":\"ME_1A\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"MEASURE\"},{\"id\":217,\"type\":\"STRING\",\"name\":\"d2#ME_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"MEASURE\"},{\"id\":218,\"type\":\"STRING\",\"name\":\"Me_4\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"identifiers\":[{\"id\":213,\"type\":\"INTEGER\",\"name\":\"ID_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":214,\"type\":\"STRING\",\"name\":\"ID_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}]},\"messages\":[],\"command\":\"filterMe_1=\\\"A\\\"calcMe_4:=Me_1||Me_1Adropd1#Me_2\",\"acomponent\":false,\"ascalar\":false},{\"result\":{\"id\":60,\"name\":\"temporary_59\",\"description\":null,\"attributes\":[],\"persistent\":false,\"viral\":[],\"measures\":[{\"id\":208,\"type\":\"STRING\",\"name\":\"ME_1\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"MEASURE\"},{\"id\":209,\"type\":\"STRING\",\"name\":\"d1#ME_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"MEASURE\"},{\"id\":210,\"type\":\"STRING\",\"name\":\"ME_1A\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"MEASURE\"},{\"id\":211,\"type\":\"STRING\",\"name\":\"d2#ME_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"MEASURE\"},{\"id\":212,\"type\":\"STRING\",\"name\":\"Me_4\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"identifiers\":[{\"id\":206,\"type\":\"INTEGER\",\"name\":\"ID_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":207,\"type\":\"STRING\",\"name\":\"ID_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}]},\"messages\":[],\"command\":\"calcMe_4:=Me_1||Me_1A\",\"acomponent\":false,\"ascalar\":false},{\"resultComponent\":{\"id\":null,\"type\":\"STRING\",\"name\":\"Me_4\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"MEASURE\"},\"messages\":[],\"command\":\"Me_4:=Me_1||Me_1A\",\"acomponent\":true,\"ascalar\":false},{\"resultComponent\":{\"id\":null,\"type\":\"STRING\",\"name\":\"ME_1\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"MEASURE\"},\"messages\":[],\"command\":\"Me_1||Me_1A\",\"acomponent\":true,\"ascalar\":false},{\"resultComponent\":{\"id\":195,\"type\":\"STRING\",\"name\":\"ME_1\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"MEASURE\"},\"messages\":[],\"command\":\"Me_1\",\"acomponent\":true,\"ascalar\":false},{\"resultComponent\":{\"id\":197,\"type\":\"STRING\",\"name\":\"ME_1A\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"MEASURE\"},\"messages\":[],\"command\":\"Me_1A\",\"acomponent\":true,\"ascalar\":false},{\"result\":{\"id\":58,\"name\":\"temporary_57\",\"description\":null,\"attributes\":[],\"persistent\":false,\"viral\":[],\"measures\":[{\"id\":202,\"type\":\"STRING\",\"name\":\"ME_1\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"MEASURE\"},{\"id\":203,\"type\":\"STRING\",\"name\":\"d1#ME_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"MEASURE\"},{\"id\":204,\"type\":\"STRING\",\"name\":\"ME_1A\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"MEASURE\"},{\"id\":205,\"type\":\"STRING\",\"name\":\"d2#ME_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"identifiers\":[{\"id\":200,\"type\":\"INTEGER\",\"name\":\"ID_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":201,\"type\":\"STRING\",\"name\":\"ID_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}]},\"messages\":[],\"acomponent\":false,\"ascalar\":false},{\"resultComponent\":{\"id\":null,\"type\":\"BOOLEAN\",\"name\":\"bool_var\",\"domainValue\":\"bool_vd\",\"domainValueParent\":\"bool_vd\",\"vtlComponentRole\":\"MEASURE\"},\"messages\":[],\"command\":\"Me_1=\\\"A\\\"\",\"acomponent\":true,\"ascalar\":false},{\"resultComponent\":{\"id\":195,\"type\":\"STRING\",\"name\":\"ME_1\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"MEASURE\"},\"messages\":[],\"command\":\"Me_1\",\"acomponent\":true,\"ascalar\":false},{\"result\":{\"id\":56,\"name\":\"temporary_55\",\"description\":null,\"attributes\":[],\"persistent\":false,\"viral\":[],\"measures\":[{\"id\":199,\"type\":\"STRING\",\"name\":\"string_var\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"identifiers\":[]},\"resultComponent\":{\"id\":199,\"type\":\"STRING\",\"name\":\"string_var\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"MEASURE\"},\"messages\":[],\"command\":\"\\\"A\\\"\",\"acomponent\":false,\"ascalar\":true,\"scalarComponent\":{\"id\":199,\"type\":\"STRING\",\"name\":\"string_var\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"MEASURE\"}},{\"result\":{\"id\":54,\"name\":\"temporary_53\",\"description\":null,\"attributes\":[],\"persistent\":false,\"viral\":[],\"measures\":[{\"id\":195,\"type\":\"STRING\",\"name\":\"ME_1\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"MEASURE\"},{\"id\":196,\"type\":\"STRING\",\"name\":\"d1#ME_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"MEASURE\"},{\"id\":197,\"type\":\"STRING\",\"name\":\"ME_1A\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"MEASURE\"},{\"id\":198,\"type\":\"STRING\",\"name\":\"d2#ME_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"identifiers\":[{\"id\":193,\"type\":\"INTEGER\",\"name\":\"ID_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":194,\"type\":\"STRING\",\"name\":\"ID_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}]},\"messages\":[],\"acomponent\":false,\"ascalar\":false},{\"result\":{\"id\":52,\"name\":\"d2\",\"description\":null,\"attributes\":[],\"persistent\":false,\"viral\":[],\"measures\":[{\"id\":191,\"type\":\"STRING\",\"name\":\"ME_1A\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"MEASURE\"},{\"id\":192,\"type\":\"STRING\",\"name\":\"ME_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"identifiers\":[{\"id\":189,\"type\":\"INTEGER\",\"name\":\"ID_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":190,\"type\":\"STRING\",\"name\":\"ID_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}]},\"messages\":[],\"acomponent\":false,\"ascalar\":false},{\"result\":{\"id\":50,\"name\":\"d1\",\"description\":null,\"attributes\":[],\"persistent\":false,\"viral\":[],\"measures\":[{\"id\":187,\"type\":\"STRING\",\"name\":\"ME_1\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"MEASURE\"},{\"id\":188,\"type\":\"STRING\",\"name\":\"ME_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"identifiers\":[{\"id\":185,\"type\":\"INTEGER\",\"name\":\"ID_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":186,\"type\":\"STRING\",\"name\":\"ID_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}]},\"messages\":[],\"acomponent\":false,\"ascalar\":false}]";
		List<ResultExpression> result = vtlTestUtils.getResult(commandStatements);
		List<ResultExpression> resultExpressionsToComapare = vtlTestUtils.convertJsonToResultExpression(jsonResult);
		assertNotNull("Conversione non riuscita", resultExpressionsToComapare);
		assertNotNull("Validazione Semantica non riuscita", result);
		assertTrue("I due risultati non sono uguali",
				vtlTestUtils.compareResult(result.get(0), resultExpressionsToComapare.get(0)));

	}

	@Test
	@Transactional
	public void innerJoinKeep() throws Exception {
		VtlDataset vtlDatasetFind = datasetRepository.findByNameIgnoreCase("DS_1");
		if (vtlDatasetFind == null) {
			VtlDataset vtlDataset = new VtlDataset();

			vtlDataset.setPersistent(true);
			vtlDataset.setName("DS_4");
			vtlDataset.setIsOnlyAScalar(false);
			vtlDataset.setTransitory(false);
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_1", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_2", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_1", VtlType.STRING, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_2", VtlType.STRING, VtlComponentRole.MEASURE));
			datasetRepository.save(vtlDataset);

			VtlDataset vtlDatasetSecond = new VtlDataset();
			vtlDatasetSecond.setPersistent(true);
			vtlDatasetSecond.setName("DS_36");
			vtlDatasetSecond.setIsOnlyAScalar(false);
			vtlDatasetSecond.setTransitory(false);
			vtlDatasetSecond
					.addComponent(vtlTestUtils.getVtlComponent("ID_1", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDatasetSecond
					.addComponent(vtlTestUtils.getVtlComponent("ID_2", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDatasetSecond
					.addComponent(vtlTestUtils.getVtlComponent("ME_1A", VtlType.STRING, VtlComponentRole.MEASURE));
			vtlDatasetSecond
					.addComponent(vtlTestUtils.getVtlComponent("ME_2", VtlType.STRING, VtlComponentRole.MEASURE));
			datasetRepository.save(vtlDatasetSecond);
		}
		String commandStatements = "DS_r := inner_join ( DS_4  filter Id_2 = \"B\" calc Me_2 := Me_2 || \"NEW\" keep Me_1, Me_2);";
		String jsonResult = "[{\"result\":{\"id\":94,\"name\":\"DS_r\",\"description\":null,\"attributes\":[],\"persistent\":false,\"viral\":[],\"measures\":[{\"id\":284,\"type\":\"STRING\",\"name\":\"ME_1\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"MEASURE\"},{\"id\":285,\"type\":\"STRING\",\"name\":\"Me_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"identifiers\":[{\"id\":282,\"type\":\"INTEGER\",\"name\":\"ID_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":283,\"type\":\"STRING\",\"name\":\"ID_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}]},\"messages\":[],\"command\":\"DS_r:=inner_join(DS_4filterId_2=\\\"B\\\"calcMe_2:=Me_2||\\\"NEW\\\"keepMe_1,Me_2);\",\"acomponent\":false,\"ascalar\":false},{\"result\":{\"id\":92,\"name\":\"temporary_91\",\"description\":null,\"attributes\":[],\"persistent\":false,\"viral\":[],\"measures\":[{\"id\":280,\"type\":\"STRING\",\"name\":\"ME_1\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"MEASURE\"},{\"id\":281,\"type\":\"STRING\",\"name\":\"Me_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"identifiers\":[{\"id\":278,\"type\":\"INTEGER\",\"name\":\"ID_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":279,\"type\":\"STRING\",\"name\":\"ID_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}]},\"messages\":[],\"command\":\"inner_join(DS_4filterId_2=\\\"B\\\"calcMe_2:=Me_2||\\\"NEW\\\"keepMe_1,Me_2)\",\"acomponent\":false,\"ascalar\":false},{\"result\":{\"id\":90,\"name\":\"temporary_89\",\"description\":null,\"attributes\":[],\"persistent\":false,\"viral\":[],\"measures\":[{\"id\":276,\"type\":\"STRING\",\"name\":\"ME_1\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"MEASURE\"},{\"id\":277,\"type\":\"STRING\",\"name\":\"Me_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"identifiers\":[{\"id\":274,\"type\":\"INTEGER\",\"name\":\"ID_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":275,\"type\":\"STRING\",\"name\":\"ID_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}]},\"messages\":[],\"command\":\"filterId_2=\\\"B\\\"calcMe_2:=Me_2||\\\"NEW\\\"keepMe_1,Me_2\",\"acomponent\":false,\"ascalar\":false},{\"result\":{\"id\":88,\"name\":\"temporary_87\",\"description\":null,\"attributes\":[],\"persistent\":false,\"viral\":[],\"measures\":[{\"id\":271,\"type\":\"STRING\",\"name\":\"ME_1\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"MEASURE\"},{\"id\":273,\"type\":\"STRING\",\"name\":\"Me_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"identifiers\":[{\"id\":269,\"type\":\"INTEGER\",\"name\":\"ID_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":270,\"type\":\"STRING\",\"name\":\"ID_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}]},\"messages\":[],\"command\":\"calcMe_2:=Me_2||\\\"NEW\\\"\",\"acomponent\":false,\"ascalar\":false},{\"resultComponent\":{\"id\":null,\"type\":\"STRING\",\"name\":\"Me_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"MEASURE\"},\"messages\":[],\"command\":\"Me_2:=Me_2||\\\"NEW\\\"\",\"acomponent\":true,\"ascalar\":false},{\"resultComponent\":{\"id\":null,\"type\":\"STRING\",\"name\":\"ME_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"MEASURE\"},\"messages\":[],\"command\":\"Me_2||\\\"NEW\\\"\",\"acomponent\":true,\"ascalar\":false},{\"resultComponent\":{\"id\":262,\"type\":\"STRING\",\"name\":\"ME_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"MEASURE\"},\"messages\":[],\"command\":\"Me_2\",\"acomponent\":true,\"ascalar\":false},{\"result\":{\"id\":86,\"name\":\"temporary_85\",\"description\":null,\"attributes\":[],\"persistent\":false,\"viral\":[],\"measures\":[{\"id\":268,\"type\":\"STRING\",\"name\":\"string_var\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"identifiers\":[]},\"resultComponent\":{\"id\":268,\"type\":\"STRING\",\"name\":\"string_var\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"MEASURE\"},\"messages\":[],\"command\":\"\\\"NEW\\\"\",\"acomponent\":false,\"ascalar\":true,\"scalarComponent\":{\"id\":268,\"type\":\"STRING\",\"name\":\"string_var\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"MEASURE\"}},{\"result\":{\"id\":84,\"name\":\"temporary_83\",\"description\":null,\"attributes\":[],\"persistent\":false,\"viral\":[],\"measures\":[{\"id\":266,\"type\":\"STRING\",\"name\":\"ME_1\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"MEASURE\"},{\"id\":267,\"type\":\"STRING\",\"name\":\"ME_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"identifiers\":[{\"id\":264,\"type\":\"INTEGER\",\"name\":\"ID_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":265,\"type\":\"STRING\",\"name\":\"ID_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}]},\"messages\":[],\"acomponent\":false,\"ascalar\":false},{\"resultComponent\":{\"id\":null,\"type\":\"BOOLEAN\",\"name\":\"bool_var\",\"domainValue\":\"bool_vd\",\"domainValueParent\":\"bool_vd\",\"vtlComponentRole\":\"MEASURE\"},\"messages\":[],\"command\":\"Id_2=\\\"B\\\"\",\"acomponent\":true,\"ascalar\":false},{\"resultComponent\":{\"id\":260,\"type\":\"STRING\",\"name\":\"ID_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},\"messages\":[],\"command\":\"Id_2\",\"acomponent\":true,\"ascalar\":false},{\"result\":{\"id\":82,\"name\":\"temporary_81\",\"description\":null,\"attributes\":[],\"persistent\":false,\"viral\":[],\"measures\":[{\"id\":263,\"type\":\"STRING\",\"name\":\"string_var\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"identifiers\":[]},\"resultComponent\":{\"id\":263,\"type\":\"STRING\",\"name\":\"string_var\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"MEASURE\"},\"messages\":[],\"command\":\"\\\"B\\\"\",\"acomponent\":false,\"ascalar\":true,\"scalarComponent\":{\"id\":263,\"type\":\"STRING\",\"name\":\"string_var\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"MEASURE\"}},{\"result\":{\"id\":80,\"name\":\"temporary_79\",\"description\":null,\"attributes\":[],\"persistent\":false,\"viral\":[],\"measures\":[{\"id\":261,\"type\":\"STRING\",\"name\":\"ME_1\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"MEASURE\"},{\"id\":262,\"type\":\"STRING\",\"name\":\"ME_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"identifiers\":[{\"id\":259,\"type\":\"INTEGER\",\"name\":\"ID_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":260,\"type\":\"STRING\",\"name\":\"ID_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}]},\"messages\":[],\"acomponent\":false,\"ascalar\":false},{\"result\":{\"id\":4,\"name\":\"DS_4\",\"description\":null,\"attributes\":[],\"persistent\":true,\"viral\":[],\"measures\":[{\"id\":15,\"type\":\"STRING\",\"name\":\"ME_1\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"MEASURE\"},{\"id\":16,\"type\":\"STRING\",\"name\":\"ME_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"identifiers\":[{\"id\":13,\"type\":\"INTEGER\",\"name\":\"ID_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":14,\"type\":\"STRING\",\"name\":\"ID_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}]},\"messages\":[],\"command\":\"DS_4\",\"acomponent\":false,\"ascalar\":false}]";
		List<ResultExpression> result = vtlTestUtils.getResult(commandStatements);
		List<ResultExpression> resultExpressionsToComapare = vtlTestUtils.convertJsonToResultExpression(jsonResult);
		assertNotNull("Conversione non riuscita", resultExpressionsToComapare);
		assertNotNull("Validazione Semantica non riuscita", result);
		assertTrue("I due risultati non sono uguali",
				vtlTestUtils.compareResult(result.get(0), resultExpressionsToComapare.get(0)));

	}

	@Test
	@Transactional
	public void leftJoin() throws Exception {
		VtlDataset vtlDatasetFind = datasetRepository.findByNameIgnoreCase("DS_1");
		if (vtlDatasetFind == null) {
			VtlDataset vtlDataset = new VtlDataset();

			vtlDataset.setPersistent(true);
			vtlDataset.setName("DS_4");
			vtlDataset.setIsOnlyAScalar(false);
			vtlDataset.setTransitory(false);
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_1", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_2", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_1", VtlType.STRING, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_2", VtlType.STRING, VtlComponentRole.MEASURE));
			datasetRepository.save(vtlDataset);

			VtlDataset vtlDatasetSecond = new VtlDataset();
			vtlDatasetSecond.setPersistent(true);
			vtlDatasetSecond.setName("DS_36");
			vtlDatasetSecond.setIsOnlyAScalar(false);
			vtlDatasetSecond.setTransitory(false);
			vtlDatasetSecond
					.addComponent(vtlTestUtils.getVtlComponent("ID_1", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDatasetSecond
					.addComponent(vtlTestUtils.getVtlComponent("ID_2", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDatasetSecond
					.addComponent(vtlTestUtils.getVtlComponent("ME_1A", VtlType.STRING, VtlComponentRole.MEASURE));
			vtlDatasetSecond
					.addComponent(vtlTestUtils.getVtlComponent("ME_2", VtlType.STRING, VtlComponentRole.MEASURE));
			datasetRepository.save(vtlDatasetSecond);
		}
		String commandStatements = "DS_r  := left_join ( DS_4 as d1, DS_36 as d2 keep Me_1, d2#Me_2, Me_1A );";
		String jsonResult = "[{\"result\":{\"id\":14,\"name\":\"DS_r\",\"description\":null,\"attributes\":[],\"persistent\":false,\"identifiers\":[{\"id\":33,\"type\":\"INTEGER\",\"name\":\"ID_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":34,\"type\":\"STRING\",\"name\":\"ID_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}],\"measures\":[{\"id\":35,\"type\":\"STRING\",\"name\":\"ME_1\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"MEASURE\"},{\"id\":36,\"type\":\"STRING\",\"name\":\"ME_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"MEASURE\"},{\"id\":37,\"type\":\"STRING\",\"name\":\"ME_1A\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"viral\":[]},\"messages\":[],\"command\":\"DS_r:=left_join(DS_4asd1,DS_36asd2keepMe_1,d2#Me_2,Me_1A);\",\"acomponent\":false,\"ascalar\":false},{\"result\":{\"id\":12,\"name\":\"temporary_11\",\"description\":null,\"attributes\":[],\"persistent\":false,\"identifiers\":[{\"id\":28,\"type\":\"INTEGER\",\"name\":\"ID_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":29,\"type\":\"STRING\",\"name\":\"ID_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}],\"measures\":[{\"id\":30,\"type\":\"STRING\",\"name\":\"ME_1\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"MEASURE\"},{\"id\":31,\"type\":\"STRING\",\"name\":\"ME_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"MEASURE\"},{\"id\":32,\"type\":\"STRING\",\"name\":\"ME_1A\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"viral\":[]},\"messages\":[],\"command\":\"left_join(DS_4asd1,DS_36asd2keepMe_1,d2#Me_2,Me_1A)\",\"acomponent\":false,\"ascalar\":false},{\"result\":{\"id\":10,\"name\":\"temporary_9\",\"description\":null,\"attributes\":[],\"persistent\":false,\"identifiers\":[{\"id\":23,\"type\":\"INTEGER\",\"name\":\"ID_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":24,\"type\":\"STRING\",\"name\":\"ID_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}],\"measures\":[{\"id\":25,\"type\":\"STRING\",\"name\":\"ME_1\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"MEASURE\"},{\"id\":26,\"type\":\"STRING\",\"name\":\"d2#ME_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"MEASURE\"},{\"id\":27,\"type\":\"STRING\",\"name\":\"ME_1A\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"viral\":[]},\"messages\":[],\"command\":\"keepMe_1,d2#Me_2,Me_1A\",\"acomponent\":false,\"ascalar\":false},{\"result\":{\"id\":8,\"name\":\"temporary_7\",\"description\":null,\"attributes\":[],\"persistent\":false,\"identifiers\":[{\"id\":17,\"type\":\"INTEGER\",\"name\":\"ID_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":18,\"type\":\"STRING\",\"name\":\"ID_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}],\"measures\":[{\"id\":19,\"type\":\"STRING\",\"name\":\"ME_1\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"MEASURE\"},{\"id\":20,\"type\":\"STRING\",\"name\":\"d1#ME_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"MEASURE\"},{\"id\":21,\"type\":\"STRING\",\"name\":\"ME_1A\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"MEASURE\"},{\"id\":22,\"type\":\"STRING\",\"name\":\"d2#ME_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"viral\":[]},\"messages\":[],\"acomponent\":false,\"ascalar\":false},{\"result\":{\"id\":6,\"name\":\"d2\",\"description\":null,\"attributes\":[],\"persistent\":false,\"identifiers\":[{\"id\":13,\"type\":\"INTEGER\",\"name\":\"ID_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":14,\"type\":\"STRING\",\"name\":\"ID_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}],\"measures\":[{\"id\":15,\"type\":\"STRING\",\"name\":\"ME_1A\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"MEASURE\"},{\"id\":16,\"type\":\"STRING\",\"name\":\"ME_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"viral\":[]},\"messages\":[],\"acomponent\":false,\"ascalar\":false},{\"result\":{\"id\":4,\"name\":\"d1\",\"description\":null,\"attributes\":[],\"persistent\":false,\"identifiers\":[{\"id\":9,\"type\":\"INTEGER\",\"name\":\"ID_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":10,\"type\":\"STRING\",\"name\":\"ID_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}],\"measures\":[{\"id\":11,\"type\":\"STRING\",\"name\":\"ME_1\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"MEASURE\"},{\"id\":12,\"type\":\"STRING\",\"name\":\"ME_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"viral\":[]},\"messages\":[],\"acomponent\":false,\"ascalar\":false}]";
		List<ResultExpression> result = vtlTestUtils.getResult(commandStatements);
		List<ResultExpression> resultExpressionsToComapare = vtlTestUtils.convertJsonToResultExpression(jsonResult);
		assertNotNull("Conversione non riuscita", resultExpressionsToComapare);
		assertNotNull("Validazione Semantica non riuscita", result);
		assertTrue("I due risultati non sono uguali",
				vtlTestUtils.compareResult(result.get(0), resultExpressionsToComapare.get(0)));

	}

	@Test
	@Transactional
	public void fullJoin() throws Exception {
		VtlDataset vtlDatasetFind = datasetRepository.findByNameIgnoreCase("DS_1");
		if (vtlDatasetFind == null) {
			VtlDataset vtlDataset = new VtlDataset();

			vtlDataset.setPersistent(true);
			vtlDataset.setName("DS_4");
			vtlDataset.setIsOnlyAScalar(false);
			vtlDataset.setTransitory(false);
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_1", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_2", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_1", VtlType.STRING, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_2", VtlType.STRING, VtlComponentRole.MEASURE));
			datasetRepository.save(vtlDataset);

			VtlDataset vtlDatasetSecond = new VtlDataset();
			vtlDatasetSecond.setPersistent(true);
			vtlDatasetSecond.setName("DS_36");
			vtlDatasetSecond.setIsOnlyAScalar(false);
			vtlDatasetSecond.setTransitory(false);
			vtlDatasetSecond
					.addComponent(vtlTestUtils.getVtlComponent("ID_1", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDatasetSecond
					.addComponent(vtlTestUtils.getVtlComponent("ID_2", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDatasetSecond
					.addComponent(vtlTestUtils.getVtlComponent("ME_1A", VtlType.STRING, VtlComponentRole.MEASURE));
			vtlDatasetSecond
					.addComponent(vtlTestUtils.getVtlComponent("ME_2", VtlType.STRING, VtlComponentRole.MEASURE));
			datasetRepository.save(vtlDatasetSecond);
		}
		String commandStatements = "DS_r := full_join ( DS_4 as d1, DS_36 as d2 keep Me_1, d2#Me_2, Me_1A );";
		String jsonResult = "[{\"result\":{\"id\":14,\"name\":\"DS_r\",\"description\":null,\"attributes\":[],\"persistent\":false,\"identifiers\":[{\"id\":33,\"type\":\"INTEGER\",\"name\":\"ID_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":34,\"type\":\"STRING\",\"name\":\"ID_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}],\"viral\":[],\"measures\":[{\"id\":35,\"type\":\"STRING\",\"name\":\"ME_1\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"MEASURE\"},{\"id\":36,\"type\":\"STRING\",\"name\":\"ME_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"MEASURE\"},{\"id\":37,\"type\":\"STRING\",\"name\":\"ME_1A\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"MEASURE\"}]},\"messages\":[],\"command\":\"DS_r:=full_join(DS_4asd1,DS_36asd2keepMe_1,d2#Me_2,Me_1A);\",\"acomponent\":false,\"ascalar\":false},{\"result\":{\"id\":12,\"name\":\"temporary_11\",\"description\":null,\"attributes\":[],\"persistent\":false,\"identifiers\":[{\"id\":28,\"type\":\"INTEGER\",\"name\":\"ID_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":29,\"type\":\"STRING\",\"name\":\"ID_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}],\"viral\":[],\"measures\":[{\"id\":30,\"type\":\"STRING\",\"name\":\"ME_1\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"MEASURE\"},{\"id\":31,\"type\":\"STRING\",\"name\":\"ME_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"MEASURE\"},{\"id\":32,\"type\":\"STRING\",\"name\":\"ME_1A\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"MEASURE\"}]},\"messages\":[],\"command\":\"full_join(DS_4asd1,DS_36asd2keepMe_1,d2#Me_2,Me_1A)\",\"acomponent\":false,\"ascalar\":false},{\"result\":{\"id\":10,\"name\":\"temporary_9\",\"description\":null,\"attributes\":[],\"persistent\":false,\"identifiers\":[{\"id\":23,\"type\":\"INTEGER\",\"name\":\"ID_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":24,\"type\":\"STRING\",\"name\":\"ID_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}],\"viral\":[],\"measures\":[{\"id\":25,\"type\":\"STRING\",\"name\":\"ME_1\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"MEASURE\"},{\"id\":26,\"type\":\"STRING\",\"name\":\"d2#ME_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"MEASURE\"},{\"id\":27,\"type\":\"STRING\",\"name\":\"ME_1A\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"MEASURE\"}]},\"messages\":[],\"command\":\"keepMe_1,d2#Me_2,Me_1A\",\"acomponent\":false,\"ascalar\":false},{\"result\":{\"id\":8,\"name\":\"temporary_7\",\"description\":null,\"attributes\":[],\"persistent\":false,\"identifiers\":[{\"id\":17,\"type\":\"INTEGER\",\"name\":\"ID_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":18,\"type\":\"STRING\",\"name\":\"ID_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}],\"viral\":[],\"measures\":[{\"id\":19,\"type\":\"STRING\",\"name\":\"ME_1\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"MEASURE\"},{\"id\":20,\"type\":\"STRING\",\"name\":\"d1#ME_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"MEASURE\"},{\"id\":21,\"type\":\"STRING\",\"name\":\"ME_1A\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"MEASURE\"},{\"id\":22,\"type\":\"STRING\",\"name\":\"d2#ME_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"MEASURE\"}]},\"messages\":[],\"acomponent\":false,\"ascalar\":false},{\"result\":{\"id\":6,\"name\":\"d2\",\"description\":null,\"attributes\":[],\"persistent\":false,\"identifiers\":[{\"id\":13,\"type\":\"INTEGER\",\"name\":\"ID_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":14,\"type\":\"STRING\",\"name\":\"ID_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}],\"viral\":[],\"measures\":[{\"id\":15,\"type\":\"STRING\",\"name\":\"ME_1A\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"MEASURE\"},{\"id\":16,\"type\":\"STRING\",\"name\":\"ME_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"MEASURE\"}]},\"messages\":[],\"acomponent\":false,\"ascalar\":false},{\"result\":{\"id\":4,\"name\":\"d1\",\"description\":null,\"attributes\":[],\"persistent\":false,\"identifiers\":[{\"id\":9,\"type\":\"INTEGER\",\"name\":\"ID_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":10,\"type\":\"STRING\",\"name\":\"ID_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}],\"viral\":[],\"measures\":[{\"id\":11,\"type\":\"STRING\",\"name\":\"ME_1\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"MEASURE\"},{\"id\":12,\"type\":\"STRING\",\"name\":\"ME_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"MEASURE\"}]},\"messages\":[],\"acomponent\":false,\"ascalar\":false}]";
		List<ResultExpression> result = vtlTestUtils.getResult(commandStatements);
		List<ResultExpression> resultExpressionsToComapare = vtlTestUtils.convertJsonToResultExpression(jsonResult);
		assertNotNull("Conversione non riuscita", resultExpressionsToComapare);
		assertNotNull("Validazione Semantica non riuscita", result);
		assertTrue("I due risultati non sono uguali",
				vtlTestUtils.compareResult(result.get(0), resultExpressionsToComapare.get(0)));

	}

	@Test
	@Transactional
	public void crossJoin() throws Exception {
		VtlDataset vtlDatasetFind = datasetRepository.findByNameIgnoreCase("DS_1");
		if (vtlDatasetFind == null) {
			VtlDataset vtlDataset = new VtlDataset();

			vtlDataset.setPersistent(true);
			vtlDataset.setName("DS_4");
			vtlDataset.setIsOnlyAScalar(false);
			vtlDataset.setTransitory(false);
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_1", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_2", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_1", VtlType.STRING, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_2", VtlType.STRING, VtlComponentRole.MEASURE));
			datasetRepository.save(vtlDataset);

			VtlDataset vtlDatasetSecond = new VtlDataset();
			vtlDatasetSecond.setPersistent(true);
			vtlDatasetSecond.setName("DS_36");
			vtlDatasetSecond.setIsOnlyAScalar(false);
			vtlDatasetSecond.setTransitory(false);
			vtlDatasetSecond
					.addComponent(vtlTestUtils.getVtlComponent("ID_1", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDatasetSecond
					.addComponent(vtlTestUtils.getVtlComponent("ID_2", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDatasetSecond
					.addComponent(vtlTestUtils.getVtlComponent("ME_1A", VtlType.STRING, VtlComponentRole.MEASURE));
			vtlDatasetSecond
					.addComponent(vtlTestUtils.getVtlComponent("ME_2", VtlType.STRING, VtlComponentRole.MEASURE));
			datasetRepository.save(vtlDatasetSecond);
		}
		String commandStatements = "DS_r := cross_join (DS_4 as d1, DS_36 as d2 \n"
				+ "rename d1#Id_1 to Id11, d1#Id_2 to Id12, d2#Id_1 to Id21, d2#Id_2 to Id22, d1#Me_2  to Me12 );";
		String jsonResult = "[{\"result\":{\"id\":22,\"name\":\"DS_r\",\"description\":null,\"attributes\":[],\"persistent\":false,\"identifiers\":[{\"id\":76,\"type\":\"STRING\",\"name\":\"Id22\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":77,\"type\":\"INTEGER\",\"name\":\"Id21\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":78,\"type\":\"STRING\",\"name\":\"Id12\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":79,\"type\":\"INTEGER\",\"name\":\"Id11\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}],\"viral\":[],\"measures\":[{\"id\":73,\"type\":\"STRING\",\"name\":\"ME_1\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"MEASURE\"},{\"id\":74,\"type\":\"STRING\",\"name\":\"ME_1A\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"MEASURE\"},{\"id\":75,\"type\":\"STRING\",\"name\":\"ME_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"MEASURE\"},{\"id\":80,\"type\":\"STRING\",\"name\":\"Me12\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"MEASURE\"}]},\"messages\":[],\"command\":\"DS_r:=cross_join(DS_4asd1,DS_36asd2renamed1#Id_1toId11,d1#Id_2toId12,d2#Id_1toId21,d2#Id_2toId22,d1#Me_2toMe12);\",\"acomponent\":false,\"ascalar\":false},{\"result\":{\"id\":20,\"name\":\"temporary_19\",\"description\":null,\"attributes\":[],\"persistent\":false,\"identifiers\":[{\"id\":68,\"type\":\"STRING\",\"name\":\"Id22\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":69,\"type\":\"INTEGER\",\"name\":\"Id21\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":70,\"type\":\"STRING\",\"name\":\"Id12\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":71,\"type\":\"INTEGER\",\"name\":\"Id11\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}],\"viral\":[],\"measures\":[{\"id\":65,\"type\":\"STRING\",\"name\":\"ME_1\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"MEASURE\"},{\"id\":66,\"type\":\"STRING\",\"name\":\"ME_1A\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"MEASURE\"},{\"id\":67,\"type\":\"STRING\",\"name\":\"ME_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"MEASURE\"},{\"id\":72,\"type\":\"STRING\",\"name\":\"Me12\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"MEASURE\"}]},\"messages\":[],\"command\":\"cross_join(DS_4asd1,DS_36asd2renamed1#Id_1toId11,d1#Id_2toId12,d2#Id_1toId21,d2#Id_2toId22,d1#Me_2toMe12)\",\"acomponent\":false,\"ascalar\":false},{\"result\":{\"id\":18,\"name\":\"temporary_17\",\"description\":null,\"attributes\":[],\"persistent\":false,\"identifiers\":[{\"id\":60,\"type\":\"STRING\",\"name\":\"Id22\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":61,\"type\":\"INTEGER\",\"name\":\"Id21\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":62,\"type\":\"STRING\",\"name\":\"Id12\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":63,\"type\":\"INTEGER\",\"name\":\"Id11\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}],\"viral\":[],\"measures\":[{\"id\":57,\"type\":\"STRING\",\"name\":\"ME_1\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"MEASURE\"},{\"id\":58,\"type\":\"STRING\",\"name\":\"ME_1A\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"MEASURE\"},{\"id\":59,\"type\":\"STRING\",\"name\":\"d2#ME_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"MEASURE\"},{\"id\":64,\"type\":\"STRING\",\"name\":\"Me12\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"MEASURE\"}]},\"messages\":[],\"command\":\"renamed1#Id_1toId11,d1#Id_2toId12,d2#Id_1toId21,d2#Id_2toId22,d1#Me_2toMe12\",\"acomponent\":false,\"ascalar\":false},{\"result\":{\"id\":8,\"name\":\"temporary_7\",\"description\":null,\"attributes\":[],\"persistent\":false,\"identifiers\":[{\"id\":17,\"type\":\"INTEGER\",\"name\":\"d1#ID_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":18,\"type\":\"STRING\",\"name\":\"d1#ID_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":19,\"type\":\"INTEGER\",\"name\":\"d2#ID_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":20,\"type\":\"STRING\",\"name\":\"d2#ID_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}],\"viral\":[],\"measures\":[{\"id\":21,\"type\":\"STRING\",\"name\":\"ME_1\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"MEASURE\"},{\"id\":22,\"type\":\"STRING\",\"name\":\"d1#ME_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"MEASURE\"},{\"id\":23,\"type\":\"STRING\",\"name\":\"ME_1A\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"MEASURE\"},{\"id\":24,\"type\":\"STRING\",\"name\":\"d2#ME_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"MEASURE\"}]},\"messages\":[],\"acomponent\":false,\"ascalar\":false},{\"result\":{\"id\":6,\"name\":\"d2\",\"description\":null,\"attributes\":[],\"persistent\":false,\"identifiers\":[{\"id\":13,\"type\":\"INTEGER\",\"name\":\"ID_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":14,\"type\":\"STRING\",\"name\":\"ID_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}],\"viral\":[],\"measures\":[{\"id\":15,\"type\":\"STRING\",\"name\":\"ME_1A\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"MEASURE\"},{\"id\":16,\"type\":\"STRING\",\"name\":\"ME_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"MEASURE\"}]},\"messages\":[],\"acomponent\":false,\"ascalar\":false},{\"result\":{\"id\":4,\"name\":\"d1\",\"description\":null,\"attributes\":[],\"persistent\":false,\"identifiers\":[{\"id\":9,\"type\":\"INTEGER\",\"name\":\"ID_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":10,\"type\":\"STRING\",\"name\":\"ID_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}],\"viral\":[],\"measures\":[{\"id\":11,\"type\":\"STRING\",\"name\":\"ME_1\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"MEASURE\"},{\"id\":12,\"type\":\"STRING\",\"name\":\"ME_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"MEASURE\"}]},\"messages\":[],\"acomponent\":false,\"ascalar\":false}]";
		List<ResultExpression> result = vtlTestUtils.getResult(commandStatements);
		List<ResultExpression> resultExpressionsToComapare = vtlTestUtils.convertJsonToResultExpression(jsonResult);
		assertNotNull("Conversione non riuscita", resultExpressionsToComapare);
		assertNotNull("Validazione Semantica non riuscita", result);
		assertTrue("I due risultati non sono uguali",
				vtlTestUtils.compareResult(result.get(0), resultExpressionsToComapare.get(0)));
	}
	
	@Test
	@Transactional
	public void innerJoinNoUsing() throws Exception {
		VtlDataset vtlDatasetFind = datasetRepository.findByNameIgnoreCase("DS_1");
		if (vtlDatasetFind == null) {
			VtlDataset vtlDataset = new VtlDataset();
			vtlDataset.setPersistent(true);
			vtlDataset.setName("DS_63");
			vtlDataset.setIsOnlyAScalar(false);
			vtlDataset.setTransitory(false);
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_1", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_1", VtlType.STRING, VtlComponentRole.MEASURE));
			datasetRepository.save(vtlDataset);

			VtlDataset vtlDatasetSecond = new VtlDataset();
			vtlDatasetSecond.setPersistent(true);
			vtlDatasetSecond.setName("DS_64");
			vtlDatasetSecond.setIsOnlyAScalar(false);
			vtlDatasetSecond.setTransitory(false);
			vtlDatasetSecond
					.addComponent(vtlTestUtils.getVtlComponent("ID_1", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDatasetSecond
					.addComponent(vtlTestUtils.getVtlComponent("ID_2", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDatasetSecond
					.addComponent(vtlTestUtils.getVtlComponent("ME_2", VtlType.STRING, VtlComponentRole.MEASURE));
			datasetRepository.save(vtlDatasetSecond);
			
			VtlDataset vtlDatasetThird = new VtlDataset();
			vtlDatasetThird.setPersistent(true);
			vtlDatasetThird.setName("DS_65");
			vtlDatasetThird.setIsOnlyAScalar(false);
			vtlDatasetThird.setTransitory(false);
			vtlDatasetThird
					.addComponent(vtlTestUtils.getVtlComponent("ID_1", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDatasetThird
					.addComponent(vtlTestUtils.getVtlComponent("ID_2", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDatasetThird
					.addComponent(vtlTestUtils.getVtlComponent("ID_3", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDatasetThird
					.addComponent(vtlTestUtils.getVtlComponent("ME_3", VtlType.STRING, VtlComponentRole.MEASURE));
			datasetRepository.save(vtlDatasetThird);
		}
		String commandStatements = "DS_r := inner_join (DS_63 as ds63, DS_64 as ds64, DS_65 as ds65);";
		String jsonResult = "[{\"result\":{\"id\":15,\"name\":\"DS_r\",\"description\":null,\"attributes\":[],\"persistent\":false,\"identifiers\":[{\"id\":31,\"type\":\"INTEGER\",\"name\":\"ID_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":32,\"type\":\"STRING\",\"name\":\"ID_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":33,\"type\":\"STRING\",\"name\":\"ID_3\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}],\"measures\":[{\"id\":34,\"type\":\"STRING\",\"name\":\"ME_1\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"MEASURE\"},{\"id\":35,\"type\":\"STRING\",\"name\":\"ME_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"MEASURE\"},{\"id\":36,\"type\":\"STRING\",\"name\":\"ME_3\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"viral\":[]},\"messages\":[],\"command\":\"DS_r:=inner_join(DS_63asds63,DS_64asds64,DS_65asds65)\",\"acomponent\":false,\"ascalar\":false,\"adataset\":true},{\"result\":{\"id\":13,\"name\":\"temporary_12\",\"description\":null,\"attributes\":[],\"persistent\":false,\"identifiers\":[{\"id\":25,\"type\":\"INTEGER\",\"name\":\"ID_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":26,\"type\":\"STRING\",\"name\":\"ID_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":27,\"type\":\"STRING\",\"name\":\"ID_3\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}],\"measures\":[{\"id\":28,\"type\":\"STRING\",\"name\":\"ME_1\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"MEASURE\"},{\"id\":29,\"type\":\"STRING\",\"name\":\"ME_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"MEASURE\"},{\"id\":30,\"type\":\"STRING\",\"name\":\"ME_3\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"viral\":[]},\"messages\":[],\"command\":\"inner_join(DS_63asds63,DS_64asds64,DS_65asds65)\",\"acomponent\":false,\"ascalar\":false,\"adataset\":true},{\"result\":{\"id\":11,\"name\":\"temporary_10\",\"description\":null,\"attributes\":[],\"persistent\":false,\"identifiers\":[{\"id\":19,\"type\":\"INTEGER\",\"name\":\"ID_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":20,\"type\":\"STRING\",\"name\":\"ID_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":21,\"type\":\"STRING\",\"name\":\"ID_3\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}],\"measures\":[{\"id\":22,\"type\":\"STRING\",\"name\":\"ME_1\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"MEASURE\"},{\"id\":23,\"type\":\"STRING\",\"name\":\"ME_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"MEASURE\"},{\"id\":24,\"type\":\"STRING\",\"name\":\"ME_3\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"viral\":[]},\"messages\":[],\"acomponent\":false,\"ascalar\":false,\"adataset\":true},{\"result\":{\"id\":9,\"name\":\"ds65\",\"description\":null,\"attributes\":[],\"persistent\":false,\"identifiers\":[{\"id\":15,\"type\":\"INTEGER\",\"name\":\"ID_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":16,\"type\":\"STRING\",\"name\":\"ID_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":17,\"type\":\"STRING\",\"name\":\"ID_3\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}],\"measures\":[{\"id\":18,\"type\":\"STRING\",\"name\":\"ME_3\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"viral\":[]},\"messages\":[],\"acomponent\":false,\"ascalar\":false,\"adataset\":true},{\"result\":{\"id\":7,\"name\":\"ds64\",\"description\":null,\"attributes\":[],\"persistent\":false,\"identifiers\":[{\"id\":12,\"type\":\"INTEGER\",\"name\":\"ID_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":13,\"type\":\"STRING\",\"name\":\"ID_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}],\"measures\":[{\"id\":14,\"type\":\"STRING\",\"name\":\"ME_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"viral\":[]},\"messages\":[],\"acomponent\":false,\"ascalar\":false,\"adataset\":true},{\"result\":{\"id\":5,\"name\":\"ds63\",\"description\":null,\"attributes\":[],\"persistent\":false,\"identifiers\":[{\"id\":10,\"type\":\"INTEGER\",\"name\":\"ID_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}],\"measures\":[{\"id\":11,\"type\":\"STRING\",\"name\":\"ME_1\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"viral\":[]},\"messages\":[],\"acomponent\":false,\"ascalar\":false,\"adataset\":true}]";
		List<ResultExpression> result = vtlTestUtils.getResult(commandStatements);
		System.out.println("########"+vtlTestUtils.convertResultToJson(result));
		List<ResultExpression> resultExpressionsToComapare = vtlTestUtils.convertJsonToResultExpression(jsonResult);
		assertNotNull("Conversione non riuscita", resultExpressionsToComapare);
		assertNotNull("Validazione Semantica non riuscita", result);
		assertTrue("I due risultati non sono uguali",
				vtlTestUtils.compareResult(result.get(0), resultExpressionsToComapare.get(0)));
	}

	@Test
	@Transactional
	public void innerJoinUsing() throws Exception {
		VtlDataset vtlDatasetFind = datasetRepository.findByNameIgnoreCase("DS_1");
		if (vtlDatasetFind == null) {
			VtlDataset vtlDataset = new VtlDataset();
			vtlDataset.setPersistent(true);
			vtlDataset.setName("DS_63");
			vtlDataset.setIsOnlyAScalar(false);
			vtlDataset.setTransitory(false);
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_1", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_2", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_1", VtlType.STRING, VtlComponentRole.MEASURE));
			datasetRepository.save(vtlDataset);

			VtlDataset vtlDatasetSecond = new VtlDataset();
			vtlDatasetSecond.setPersistent(true);
			vtlDatasetSecond.setName("DS_64");
			vtlDatasetSecond.setIsOnlyAScalar(false);
			vtlDatasetSecond.setTransitory(false);
			vtlDatasetSecond
					.addComponent(vtlTestUtils.getVtlComponent("ID_1", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDatasetSecond
					.addComponent(vtlTestUtils.getVtlComponent("ID_2", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDatasetSecond
					.addComponent(vtlTestUtils.getVtlComponent("ME_2", VtlType.STRING, VtlComponentRole.MEASURE));
			datasetRepository.save(vtlDatasetSecond);
			
			VtlDataset vtlDatasetThird = new VtlDataset();
			vtlDatasetThird.setPersistent(true);
			vtlDatasetThird.setName("DS_65");
			vtlDatasetThird.setIsOnlyAScalar(false);
			vtlDatasetThird.setTransitory(false);
			vtlDatasetThird
					.addComponent(vtlTestUtils.getVtlComponent("ID_1", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDatasetThird
					.addComponent(vtlTestUtils.getVtlComponent("ID_2", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDatasetThird
					.addComponent(vtlTestUtils.getVtlComponent("ID_3", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDatasetThird
					.addComponent(vtlTestUtils.getVtlComponent("ME_3", VtlType.STRING, VtlComponentRole.MEASURE));
			datasetRepository.save(vtlDatasetThird);
		}
		String commandStatements = "DS_r := inner_join (DS_63 as ds63, DS_64 as ds64, DS_65 as ds65 using ID_1);";
		String jsonResult = "[{\"result\":{\"id\":15,\"name\":\"DS_r\",\"description\":null,\"attributes\":[],\"persistent\":false,\"identifiers\":[{\"id\":33,\"type\":\"INTEGER\",\"name\":\"ID_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":34,\"type\":\"STRING\",\"name\":\"ID_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":35,\"type\":\"STRING\",\"name\":\"ID_3\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}],\"measures\":[{\"id\":36,\"type\":\"STRING\",\"name\":\"ME_1\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"MEASURE\"},{\"id\":37,\"type\":\"STRING\",\"name\":\"ME_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"MEASURE\"},{\"id\":38,\"type\":\"STRING\",\"name\":\"ME_3\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"viral\":[]},\"messages\":[],\"command\":\"DS_r:=inner_join(DS_63asds63,DS_64asds64,DS_65asds65usingID_1)\",\"adataset\":true,\"acomponent\":false,\"ascalar\":false},{\"result\":{\"id\":13,\"name\":\"temporary_12\",\"description\":null,\"attributes\":[],\"persistent\":false,\"identifiers\":[{\"id\":27,\"type\":\"INTEGER\",\"name\":\"ID_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":28,\"type\":\"STRING\",\"name\":\"ID_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":29,\"type\":\"STRING\",\"name\":\"ID_3\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}],\"measures\":[{\"id\":30,\"type\":\"STRING\",\"name\":\"ME_1\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"MEASURE\"},{\"id\":31,\"type\":\"STRING\",\"name\":\"ME_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"MEASURE\"},{\"id\":32,\"type\":\"STRING\",\"name\":\"ME_3\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"viral\":[]},\"messages\":[],\"command\":\"inner_join(DS_63asds63,DS_64asds64,DS_65asds65usingID_1)\",\"adataset\":true,\"acomponent\":false,\"ascalar\":false},{\"result\":{\"id\":11,\"name\":\"temporary_10\",\"description\":null,\"attributes\":[],\"persistent\":false,\"identifiers\":[{\"id\":21,\"type\":\"INTEGER\",\"name\":\"ID_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":22,\"type\":\"STRING\",\"name\":\"ID_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":23,\"type\":\"STRING\",\"name\":\"ID_3\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}],\"measures\":[{\"id\":24,\"type\":\"STRING\",\"name\":\"ME_1\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"MEASURE\"},{\"id\":25,\"type\":\"STRING\",\"name\":\"ME_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"MEASURE\"},{\"id\":26,\"type\":\"STRING\",\"name\":\"ME_3\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"viral\":[]},\"messages\":[],\"adataset\":true,\"acomponent\":false,\"ascalar\":false},{\"result\":{\"id\":9,\"name\":\"ds65\",\"description\":null,\"attributes\":[],\"persistent\":false,\"identifiers\":[{\"id\":17,\"type\":\"INTEGER\",\"name\":\"ID_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":18,\"type\":\"STRING\",\"name\":\"ID_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":19,\"type\":\"STRING\",\"name\":\"ID_3\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}],\"measures\":[{\"id\":20,\"type\":\"STRING\",\"name\":\"ME_3\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"viral\":[]},\"messages\":[],\"adataset\":true,\"acomponent\":false,\"ascalar\":false},{\"result\":{\"id\":7,\"name\":\"ds64\",\"description\":null,\"attributes\":[],\"persistent\":false,\"identifiers\":[{\"id\":14,\"type\":\"INTEGER\",\"name\":\"ID_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":15,\"type\":\"STRING\",\"name\":\"ID_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}],\"measures\":[{\"id\":16,\"type\":\"STRING\",\"name\":\"ME_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"viral\":[]},\"messages\":[],\"adataset\":true,\"acomponent\":false,\"ascalar\":false},{\"result\":{\"id\":5,\"name\":\"ds63\",\"description\":null,\"attributes\":[],\"persistent\":false,\"identifiers\":[{\"id\":11,\"type\":\"INTEGER\",\"name\":\"ID_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":12,\"type\":\"STRING\",\"name\":\"ID_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}],\"measures\":[{\"id\":13,\"type\":\"STRING\",\"name\":\"ME_1\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"viral\":[]},\"messages\":[],\"adataset\":true,\"acomponent\":false,\"ascalar\":false}]";
		List<ResultExpression> result = vtlTestUtils.getResult(commandStatements);
		System.out.println("########"+vtlTestUtils.convertResultToJson(result));
		List<ResultExpression> resultExpressionsToComapare = vtlTestUtils.convertJsonToResultExpression(jsonResult);
		assertNotNull("Conversione non riuscita", resultExpressionsToComapare);
		assertNotNull("Validazione Semantica non riuscita", result);
		assertTrue("I due risultati non sono uguali",
				vtlTestUtils.compareResult(result.get(0), resultExpressionsToComapare.get(0)));
	}
	
	@Test
	@Transactional
	public void innerJoinUsing2() throws Exception {
		VtlDataset vtlDatasetFind = datasetRepository.findByNameIgnoreCase("DS_1");
		if (vtlDatasetFind == null) {
			VtlDataset vtlDataset = new VtlDataset();
			vtlDataset.setPersistent(true);
			vtlDataset.setName("DS_63");
			vtlDataset.setIsOnlyAScalar(false);
			vtlDataset.setTransitory(false);
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_1", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_2", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_1", VtlType.STRING, VtlComponentRole.MEASURE));
			datasetRepository.save(vtlDataset);

			VtlDataset vtlDatasetSecond = new VtlDataset();
			vtlDatasetSecond.setPersistent(true);
			vtlDatasetSecond.setName("DS_64");
			vtlDatasetSecond.setIsOnlyAScalar(false);
			vtlDatasetSecond.setTransitory(false);
			vtlDatasetSecond
					.addComponent(vtlTestUtils.getVtlComponent("ID_1", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDatasetSecond
					.addComponent(vtlTestUtils.getVtlComponent("ID_2", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDatasetSecond
					.addComponent(vtlTestUtils.getVtlComponent("ME_2", VtlType.STRING, VtlComponentRole.MEASURE));
			datasetRepository.save(vtlDatasetSecond);
			
			VtlDataset vtlDatasetThird = new VtlDataset();
			vtlDatasetThird.setPersistent(true);
			vtlDatasetThird.setName("DS_65");
			vtlDatasetThird.setIsOnlyAScalar(false);
			vtlDatasetThird.setTransitory(false);
			vtlDatasetThird
					.addComponent(vtlTestUtils.getVtlComponent("ID_1", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDatasetThird
					.addComponent(vtlTestUtils.getVtlComponent("ID_2", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDatasetThird
					.addComponent(vtlTestUtils.getVtlComponent("ID_3", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDatasetThird
					.addComponent(vtlTestUtils.getVtlComponent("ME_3", VtlType.STRING, VtlComponentRole.MEASURE));
			datasetRepository.save(vtlDatasetThird);
		}
		String commandStatements = "DS_r := inner_join (DS_63 as ds63, DS_64 as ds64, DS_65 as ds65 using ID_1, ID_2);";
		String jsonResult = "[{\"result\":{\"id\":15,\"name\":\"DS_r\",\"description\":null,\"attributes\":[],\"persistent\":false,\"identifiers\":[{\"id\":33,\"type\":\"INTEGER\",\"name\":\"ID_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":34,\"type\":\"STRING\",\"name\":\"ID_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":35,\"type\":\"STRING\",\"name\":\"ID_3\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}],\"measures\":[{\"id\":36,\"type\":\"STRING\",\"name\":\"ME_1\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"MEASURE\"},{\"id\":37,\"type\":\"STRING\",\"name\":\"ME_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"MEASURE\"},{\"id\":38,\"type\":\"STRING\",\"name\":\"ME_3\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"viral\":[]},\"messages\":[],\"command\":\"DS_r:=inner_join(DS_63asds63,DS_64asds64,DS_65asds65usingID_1,ID_2)\",\"acomponent\":false,\"ascalar\":false,\"adataset\":true},{\"result\":{\"id\":13,\"name\":\"temporary_12\",\"description\":null,\"attributes\":[],\"persistent\":false,\"identifiers\":[{\"id\":27,\"type\":\"INTEGER\",\"name\":\"ID_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":28,\"type\":\"STRING\",\"name\":\"ID_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":29,\"type\":\"STRING\",\"name\":\"ID_3\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}],\"measures\":[{\"id\":30,\"type\":\"STRING\",\"name\":\"ME_1\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"MEASURE\"},{\"id\":31,\"type\":\"STRING\",\"name\":\"ME_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"MEASURE\"},{\"id\":32,\"type\":\"STRING\",\"name\":\"ME_3\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"viral\":[]},\"messages\":[],\"command\":\"inner_join(DS_63asds63,DS_64asds64,DS_65asds65usingID_1,ID_2)\",\"acomponent\":false,\"ascalar\":false,\"adataset\":true},{\"result\":{\"id\":11,\"name\":\"temporary_10\",\"description\":null,\"attributes\":[],\"persistent\":false,\"identifiers\":[{\"id\":21,\"type\":\"INTEGER\",\"name\":\"ID_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":22,\"type\":\"STRING\",\"name\":\"ID_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":23,\"type\":\"STRING\",\"name\":\"ID_3\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}],\"measures\":[{\"id\":24,\"type\":\"STRING\",\"name\":\"ME_1\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"MEASURE\"},{\"id\":25,\"type\":\"STRING\",\"name\":\"ME_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"MEASURE\"},{\"id\":26,\"type\":\"STRING\",\"name\":\"ME_3\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"viral\":[]},\"messages\":[],\"acomponent\":false,\"ascalar\":false,\"adataset\":true},{\"result\":{\"id\":9,\"name\":\"ds65\",\"description\":null,\"attributes\":[],\"persistent\":false,\"identifiers\":[{\"id\":17,\"type\":\"INTEGER\",\"name\":\"ID_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":18,\"type\":\"STRING\",\"name\":\"ID_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":19,\"type\":\"STRING\",\"name\":\"ID_3\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}],\"measures\":[{\"id\":20,\"type\":\"STRING\",\"name\":\"ME_3\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"viral\":[]},\"messages\":[],\"acomponent\":false,\"ascalar\":false,\"adataset\":true},{\"result\":{\"id\":7,\"name\":\"ds64\",\"description\":null,\"attributes\":[],\"persistent\":false,\"identifiers\":[{\"id\":14,\"type\":\"INTEGER\",\"name\":\"ID_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":15,\"type\":\"STRING\",\"name\":\"ID_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}],\"measures\":[{\"id\":16,\"type\":\"STRING\",\"name\":\"ME_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"viral\":[]},\"messages\":[],\"acomponent\":false,\"ascalar\":false,\"adataset\":true},{\"result\":{\"id\":5,\"name\":\"ds63\",\"description\":null,\"attributes\":[],\"persistent\":false,\"identifiers\":[{\"id\":11,\"type\":\"INTEGER\",\"name\":\"ID_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":12,\"type\":\"STRING\",\"name\":\"ID_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}],\"measures\":[{\"id\":13,\"type\":\"STRING\",\"name\":\"ME_1\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"viral\":[]},\"messages\":[],\"acomponent\":false,\"ascalar\":false,\"adataset\":true}]";
		List<ResultExpression> result = vtlTestUtils.getResult(commandStatements);
		System.out.println("########"+vtlTestUtils.convertResultToJson(result));
		List<ResultExpression> resultExpressionsToComapare = vtlTestUtils.convertJsonToResultExpression(jsonResult);
		assertNotNull("Conversione non riuscita", resultExpressionsToComapare);
		assertNotNull("Validazione Semantica non riuscita", result);
		assertTrue("I due risultati non sono uguali",
				vtlTestUtils.compareResult(result.get(0), resultExpressionsToComapare.get(0)));
	}

	@Test
	@Transactional
	public void innerJoinUsing4() throws Exception {
		VtlDataset vtlDatasetFind = datasetRepository.findByNameIgnoreCase("DS_1");
		if (vtlDatasetFind == null) {
			VtlDataset vtlDataset = new VtlDataset();
			vtlDataset.setPersistent(true);
			vtlDataset.setName("DS_63");
			vtlDataset.setIsOnlyAScalar(false);
			vtlDataset.setTransitory(false);
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_1", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("CMP_1", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_1", VtlType.STRING, VtlComponentRole.MEASURE));
			datasetRepository.save(vtlDataset);

			VtlDataset vtlDatasetSecond = new VtlDataset();
			vtlDatasetSecond.setPersistent(true);
			vtlDatasetSecond.setName("DS_64");
			vtlDatasetSecond.setIsOnlyAScalar(false);
			vtlDatasetSecond.setTransitory(false);
			vtlDatasetSecond
					.addComponent(vtlTestUtils.getVtlComponent("ID_1", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDatasetSecond
					.addComponent(vtlTestUtils.getVtlComponent("CMP_1", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDatasetSecond
					.addComponent(vtlTestUtils.getVtlComponent("ME_2", VtlType.STRING, VtlComponentRole.MEASURE));
			datasetRepository.save(vtlDatasetSecond);
			
			VtlDataset vtlDatasetThird = new VtlDataset();
			vtlDatasetThird.setPersistent(true);
			vtlDatasetThird.setName("DS_65");
			vtlDatasetThird.setIsOnlyAScalar(false);
			vtlDatasetThird.setTransitory(false);
			vtlDatasetThird
					.addComponent(vtlTestUtils.getVtlComponent("ID_1", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDatasetThird
					.addComponent(vtlTestUtils.getVtlComponent("ID_2", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDatasetThird
					.addComponent(vtlTestUtils.getVtlComponent("CMP_1", VtlType.STRING, VtlComponentRole.MEASURE));
			vtlDatasetThird
					.addComponent(vtlTestUtils.getVtlComponent("ME_3", VtlType.STRING, VtlComponentRole.MEASURE));
			datasetRepository.save(vtlDatasetThird);
		}
		String commandStatements = "DS_r := inner_join (DS_63 as ds63, DS_64 as ds64, DS_65 as ds65 using ID_1, CMP_1);";
		String jsonResult = "[{\"result\":{\"id\":15,\"name\":\"DS_r\",\"description\":null,\"attributes\":[],\"persistent\":false,\"identifiers\":[{\"id\":33,\"type\":\"INTEGER\",\"name\":\"ID_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":34,\"type\":\"STRING\",\"name\":\"ID_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}],\"measures\":[{\"id\":35,\"type\":\"STRING\",\"name\":\"ME_1\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"MEASURE\"},{\"id\":36,\"type\":\"STRING\",\"name\":\"ME_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"MEASURE\"},{\"id\":37,\"type\":\"STRING\",\"name\":\"CMP_1\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"MEASURE\"},{\"id\":38,\"type\":\"STRING\",\"name\":\"ME_3\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"viral\":[]},\"messages\":[],\"command\":\"DS_r:=inner_join(DS_63asds63,DS_64asds64,DS_65asds65usingID_1,CMP_1)\",\"adataset\":true,\"acomponent\":false,\"ascalar\":false},{\"result\":{\"id\":13,\"name\":\"temporary_12\",\"description\":null,\"attributes\":[],\"persistent\":false,\"identifiers\":[{\"id\":27,\"type\":\"INTEGER\",\"name\":\"ID_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":28,\"type\":\"STRING\",\"name\":\"ID_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}],\"measures\":[{\"id\":29,\"type\":\"STRING\",\"name\":\"ME_1\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"MEASURE\"},{\"id\":30,\"type\":\"STRING\",\"name\":\"ME_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"MEASURE\"},{\"id\":31,\"type\":\"STRING\",\"name\":\"CMP_1\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"MEASURE\"},{\"id\":32,\"type\":\"STRING\",\"name\":\"ME_3\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"viral\":[]},\"messages\":[],\"command\":\"inner_join(DS_63asds63,DS_64asds64,DS_65asds65usingID_1,CMP_1)\",\"adataset\":true,\"acomponent\":false,\"ascalar\":false},{\"result\":{\"id\":11,\"name\":\"temporary_10\",\"description\":null,\"attributes\":[],\"persistent\":false,\"identifiers\":[{\"id\":21,\"type\":\"INTEGER\",\"name\":\"ID_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":22,\"type\":\"STRING\",\"name\":\"ID_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}],\"measures\":[{\"id\":23,\"type\":\"STRING\",\"name\":\"ME_1\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"MEASURE\"},{\"id\":24,\"type\":\"STRING\",\"name\":\"ME_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"MEASURE\"},{\"id\":25,\"type\":\"STRING\",\"name\":\"CMP_1\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"MEASURE\"},{\"id\":26,\"type\":\"STRING\",\"name\":\"ME_3\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"viral\":[]},\"messages\":[],\"adataset\":true,\"acomponent\":false,\"ascalar\":false},{\"result\":{\"id\":9,\"name\":\"ds65\",\"description\":null,\"attributes\":[],\"persistent\":false,\"identifiers\":[{\"id\":17,\"type\":\"INTEGER\",\"name\":\"ID_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":18,\"type\":\"STRING\",\"name\":\"ID_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}],\"measures\":[{\"id\":19,\"type\":\"STRING\",\"name\":\"CMP_1\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"MEASURE\"},{\"id\":20,\"type\":\"STRING\",\"name\":\"ME_3\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"viral\":[]},\"messages\":[],\"adataset\":true,\"acomponent\":false,\"ascalar\":false},{\"result\":{\"id\":7,\"name\":\"ds64\",\"description\":null,\"attributes\":[],\"persistent\":false,\"identifiers\":[{\"id\":14,\"type\":\"INTEGER\",\"name\":\"ID_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":15,\"type\":\"STRING\",\"name\":\"CMP_1\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}],\"measures\":[{\"id\":16,\"type\":\"STRING\",\"name\":\"ME_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"viral\":[]},\"messages\":[],\"adataset\":true,\"acomponent\":false,\"ascalar\":false},{\"result\":{\"id\":5,\"name\":\"ds63\",\"description\":null,\"attributes\":[],\"persistent\":false,\"identifiers\":[{\"id\":11,\"type\":\"INTEGER\",\"name\":\"ID_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":12,\"type\":\"STRING\",\"name\":\"CMP_1\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}],\"measures\":[{\"id\":13,\"type\":\"STRING\",\"name\":\"ME_1\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"viral\":[]},\"messages\":[],\"adataset\":true,\"acomponent\":false,\"ascalar\":false}]";
		List<ResultExpression> result = vtlTestUtils.getResult(commandStatements);
		System.out.println("########"+vtlTestUtils.convertResultToJson(result));
		List<ResultExpression> resultExpressionsToComapare = vtlTestUtils.convertJsonToResultExpression(jsonResult);
		assertNotNull("Conversione non riuscita", resultExpressionsToComapare);
		assertNotNull("Validazione Semantica non riuscita", result);
		assertTrue("I due risultati non sono uguali",
				vtlTestUtils.compareResult(result.get(0), resultExpressionsToComapare.get(0)));
	}

	@Test
	@Transactional
	public void leftJoinNoUsing() throws Exception {
		VtlDataset vtlDatasetFind = datasetRepository.findByNameIgnoreCase("DS_1");
		if (vtlDatasetFind == null) {
			VtlDataset vtlDataset = new VtlDataset();
			vtlDataset.setPersistent(true);
			vtlDataset.setName("DS_63");
			vtlDataset.setIsOnlyAScalar(false);
			vtlDataset.setTransitory(false);
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_1", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_2", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_1", VtlType.STRING, VtlComponentRole.MEASURE));
			datasetRepository.save(vtlDataset);

			VtlDataset vtlDatasetSecond = new VtlDataset();
			vtlDatasetSecond.setPersistent(true);
			vtlDatasetSecond.setName("DS_64");
			vtlDatasetSecond.setIsOnlyAScalar(false);
			vtlDatasetSecond.setTransitory(false);
			vtlDatasetSecond
					.addComponent(vtlTestUtils.getVtlComponent("ID_1", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDatasetSecond
					.addComponent(vtlTestUtils.getVtlComponent("ID_2", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDatasetSecond
					.addComponent(vtlTestUtils.getVtlComponent("ME_2", VtlType.STRING, VtlComponentRole.MEASURE));
			datasetRepository.save(vtlDatasetSecond);
			
			VtlDataset vtlDatasetThird = new VtlDataset();
			vtlDatasetThird.setPersistent(true);
			vtlDatasetThird.setName("DS_65");
			vtlDatasetThird.setIsOnlyAScalar(false);
			vtlDatasetThird.setTransitory(false);
			vtlDatasetThird
					.addComponent(vtlTestUtils.getVtlComponent("ID_1", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDatasetThird
					.addComponent(vtlTestUtils.getVtlComponent("ID_2", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDatasetThird
					.addComponent(vtlTestUtils.getVtlComponent("ME_3", VtlType.STRING, VtlComponentRole.MEASURE));
			datasetRepository.save(vtlDatasetThird);
		}
		String commandStatements = "DS_r := left_join (DS_63 as ds63, DS_64 as ds64, DS_65 as ds65 );";
		String jsonResult = "[{\"result\":{\"id\":15,\"name\":\"DS_r\",\"description\":null,\"attributes\":[],\"persistent\":false,\"identifiers\":[{\"id\":29,\"type\":\"INTEGER\",\"name\":\"ID_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":30,\"type\":\"STRING\",\"name\":\"ID_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}],\"measures\":[{\"id\":31,\"type\":\"STRING\",\"name\":\"ME_1\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"MEASURE\"},{\"id\":32,\"type\":\"STRING\",\"name\":\"ME_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"MEASURE\"},{\"id\":33,\"type\":\"STRING\",\"name\":\"ME_3\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"viral\":[]},\"messages\":[],\"command\":\"DS_r:=left_join(DS_63asds63,DS_64asds64,DS_65asds65)\",\"adataset\":true,\"acomponent\":false,\"ascalar\":false},{\"result\":{\"id\":13,\"name\":\"temporary_12\",\"description\":null,\"attributes\":[],\"persistent\":false,\"identifiers\":[{\"id\":24,\"type\":\"INTEGER\",\"name\":\"ID_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":25,\"type\":\"STRING\",\"name\":\"ID_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}],\"measures\":[{\"id\":26,\"type\":\"STRING\",\"name\":\"ME_1\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"MEASURE\"},{\"id\":27,\"type\":\"STRING\",\"name\":\"ME_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"MEASURE\"},{\"id\":28,\"type\":\"STRING\",\"name\":\"ME_3\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"viral\":[]},\"messages\":[],\"command\":\"left_join(DS_63asds63,DS_64asds64,DS_65asds65)\",\"adataset\":true,\"acomponent\":false,\"ascalar\":false},{\"result\":{\"id\":11,\"name\":\"temporary_10\",\"description\":null,\"attributes\":[],\"persistent\":false,\"identifiers\":[{\"id\":19,\"type\":\"INTEGER\",\"name\":\"ID_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":20,\"type\":\"STRING\",\"name\":\"ID_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}],\"measures\":[{\"id\":21,\"type\":\"STRING\",\"name\":\"ME_1\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"MEASURE\"},{\"id\":22,\"type\":\"STRING\",\"name\":\"ME_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"MEASURE\"},{\"id\":23,\"type\":\"STRING\",\"name\":\"ME_3\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"viral\":[]},\"messages\":[],\"adataset\":true,\"acomponent\":false,\"ascalar\":false},{\"result\":{\"id\":9,\"name\":\"ds65\",\"description\":null,\"attributes\":[],\"persistent\":false,\"identifiers\":[{\"id\":16,\"type\":\"INTEGER\",\"name\":\"ID_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":17,\"type\":\"STRING\",\"name\":\"ID_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}],\"measures\":[{\"id\":18,\"type\":\"STRING\",\"name\":\"ME_3\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"viral\":[]},\"messages\":[],\"adataset\":true,\"acomponent\":false,\"ascalar\":false},{\"result\":{\"id\":7,\"name\":\"ds64\",\"description\":null,\"attributes\":[],\"persistent\":false,\"identifiers\":[{\"id\":13,\"type\":\"INTEGER\",\"name\":\"ID_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":14,\"type\":\"STRING\",\"name\":\"ID_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}],\"measures\":[{\"id\":15,\"type\":\"STRING\",\"name\":\"ME_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"viral\":[]},\"messages\":[],\"adataset\":true,\"acomponent\":false,\"ascalar\":false},{\"result\":{\"id\":5,\"name\":\"ds63\",\"description\":null,\"attributes\":[],\"persistent\":false,\"identifiers\":[{\"id\":10,\"type\":\"INTEGER\",\"name\":\"ID_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":11,\"type\":\"STRING\",\"name\":\"ID_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}],\"measures\":[{\"id\":12,\"type\":\"STRING\",\"name\":\"ME_1\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"viral\":[]},\"messages\":[],\"adataset\":true,\"acomponent\":false,\"ascalar\":false}]";
		List<ResultExpression> result = vtlTestUtils.getResult(commandStatements);
		System.out.println("########"+vtlTestUtils.convertResultToJson(result));
		List<ResultExpression> resultExpressionsToComapare = vtlTestUtils.convertJsonToResultExpression(jsonResult);
		assertNotNull("Conversione non riuscita", resultExpressionsToComapare);
		assertNotNull("Validazione Semantica non riuscita", result);
		assertTrue("I due risultati non sono uguali",
				vtlTestUtils.compareResult(result.get(0), resultExpressionsToComapare.get(0)));
	}
	
	@Test
	@Transactional
	public void leftJoinUsing() throws Exception { 
		VtlDataset vtlDatasetFind = datasetRepository.findByNameIgnoreCase("DS_1");
		if (vtlDatasetFind == null) {
			VtlDataset vtlDataset = new VtlDataset();
			vtlDataset.setPersistent(true);
			vtlDataset.setName("DS_63");
			vtlDataset.setIsOnlyAScalar(false);
			vtlDataset.setTransitory(false);
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_1", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_2", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_1", VtlType.STRING, VtlComponentRole.MEASURE));
			datasetRepository.save(vtlDataset);

			VtlDataset vtlDatasetSecond = new VtlDataset();
			vtlDatasetSecond.setPersistent(true);
			vtlDatasetSecond.setName("DS_64");
			vtlDatasetSecond.setIsOnlyAScalar(false);
			vtlDatasetSecond.setTransitory(false);
			vtlDatasetSecond
					.addComponent(vtlTestUtils.getVtlComponent("ID_1", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDatasetSecond
					.addComponent(vtlTestUtils.getVtlComponent("ID_2", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDatasetSecond
					.addComponent(vtlTestUtils.getVtlComponent("ME_2", VtlType.STRING, VtlComponentRole.MEASURE));
			datasetRepository.save(vtlDatasetSecond);
			
			VtlDataset vtlDatasetThird = new VtlDataset();
			vtlDatasetThird.setPersistent(true);
			vtlDatasetThird.setName("DS_65");
			vtlDatasetThird.setIsOnlyAScalar(false);
			vtlDatasetThird.setTransitory(false);
			vtlDatasetThird
					.addComponent(vtlTestUtils.getVtlComponent("ID_1", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDatasetThird
					.addComponent(vtlTestUtils.getVtlComponent("ID_2", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDatasetThird
					.addComponent(vtlTestUtils.getVtlComponent("ME_3", VtlType.STRING, VtlComponentRole.MEASURE));
			datasetRepository.save(vtlDatasetThird);
		}
		String commandStatements = "DS_r := left_join (DS_63 as ds63, DS_64 as ds64, DS_65 as ds65 using ID_1);";
		String jsonResult = "[{\"result\":{\"id\":15,\"name\":\"DS_r\",\"description\":null,\"attributes\":[],\"persistent\":false,\"identifiers\":[{\"id\":29,\"type\":\"INTEGER\",\"name\":\"ID_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":30,\"type\":\"STRING\",\"name\":\"ID_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}],\"measures\":[{\"id\":31,\"type\":\"STRING\",\"name\":\"ME_1\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"MEASURE\"},{\"id\":32,\"type\":\"STRING\",\"name\":\"ME_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"MEASURE\"},{\"id\":33,\"type\":\"STRING\",\"name\":\"ME_3\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"viral\":[]},\"messages\":[],\"command\":\"DS_r:=left_join(DS_63asds63,DS_64asds64,DS_65asds65usingID_1)\",\"adataset\":true,\"acomponent\":false,\"ascalar\":false},{\"result\":{\"id\":13,\"name\":\"temporary_12\",\"description\":null,\"attributes\":[],\"persistent\":false,\"identifiers\":[{\"id\":24,\"type\":\"INTEGER\",\"name\":\"ID_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":25,\"type\":\"STRING\",\"name\":\"ID_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}],\"measures\":[{\"id\":26,\"type\":\"STRING\",\"name\":\"ME_1\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"MEASURE\"},{\"id\":27,\"type\":\"STRING\",\"name\":\"ME_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"MEASURE\"},{\"id\":28,\"type\":\"STRING\",\"name\":\"ME_3\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"viral\":[]},\"messages\":[],\"command\":\"left_join(DS_63asds63,DS_64asds64,DS_65asds65usingID_1)\",\"adataset\":true,\"acomponent\":false,\"ascalar\":false},{\"result\":{\"id\":11,\"name\":\"temporary_10\",\"description\":null,\"attributes\":[],\"persistent\":false,\"identifiers\":[{\"id\":19,\"type\":\"INTEGER\",\"name\":\"ID_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":20,\"type\":\"STRING\",\"name\":\"ID_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}],\"measures\":[{\"id\":21,\"type\":\"STRING\",\"name\":\"ME_1\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"MEASURE\"},{\"id\":22,\"type\":\"STRING\",\"name\":\"ME_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"MEASURE\"},{\"id\":23,\"type\":\"STRING\",\"name\":\"ME_3\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"viral\":[]},\"messages\":[],\"adataset\":true,\"acomponent\":false,\"ascalar\":false},{\"result\":{\"id\":9,\"name\":\"ds65\",\"description\":null,\"attributes\":[],\"persistent\":false,\"identifiers\":[{\"id\":16,\"type\":\"INTEGER\",\"name\":\"ID_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":17,\"type\":\"STRING\",\"name\":\"ID_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}],\"measures\":[{\"id\":18,\"type\":\"STRING\",\"name\":\"ME_3\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"viral\":[]},\"messages\":[],\"adataset\":true,\"acomponent\":false,\"ascalar\":false},{\"result\":{\"id\":7,\"name\":\"ds64\",\"description\":null,\"attributes\":[],\"persistent\":false,\"identifiers\":[{\"id\":13,\"type\":\"INTEGER\",\"name\":\"ID_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":14,\"type\":\"STRING\",\"name\":\"ID_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}],\"measures\":[{\"id\":15,\"type\":\"STRING\",\"name\":\"ME_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"viral\":[]},\"messages\":[],\"adataset\":true,\"acomponent\":false,\"ascalar\":false},{\"result\":{\"id\":5,\"name\":\"ds63\",\"description\":null,\"attributes\":[],\"persistent\":false,\"identifiers\":[{\"id\":10,\"type\":\"INTEGER\",\"name\":\"ID_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":11,\"type\":\"STRING\",\"name\":\"ID_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}],\"measures\":[{\"id\":12,\"type\":\"STRING\",\"name\":\"ME_1\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"viral\":[]},\"messages\":[],\"adataset\":true,\"acomponent\":false,\"ascalar\":false}]";
		List<ResultExpression> result = vtlTestUtils.getResult(commandStatements);
		System.out.println("########"+vtlTestUtils.convertResultToJson(result));
		List<ResultExpression> resultExpressionsToComapare = vtlTestUtils.convertJsonToResultExpression(jsonResult);
		assertNotNull("Conversione non riuscita", resultExpressionsToComapare);
		assertNotNull("Validazione Semantica non riuscita", result);
		assertTrue("I due risultati non sono uguali",
				vtlTestUtils.compareResult(result.get(0), resultExpressionsToComapare.get(0)));
	}
	
	@Test
	@Transactional
	public void leftJoinUsing2() throws Exception { 
		VtlDataset vtlDatasetFind = datasetRepository.findByNameIgnoreCase("DS_1");
		if (vtlDatasetFind == null) {
			VtlDataset vtlDataset = new VtlDataset();
			vtlDataset.setPersistent(true);
			vtlDataset.setName("DS_63");
			vtlDataset.setIsOnlyAScalar(false);
			vtlDataset.setTransitory(false);
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_1", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_2", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_1", VtlType.STRING, VtlComponentRole.MEASURE));
			datasetRepository.save(vtlDataset);

			VtlDataset vtlDatasetSecond = new VtlDataset();
			vtlDatasetSecond.setPersistent(true);
			vtlDatasetSecond.setName("DS_64");
			vtlDatasetSecond.setIsOnlyAScalar(false);
			vtlDatasetSecond.setTransitory(false);
			vtlDatasetSecond
					.addComponent(vtlTestUtils.getVtlComponent("ID_1", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDatasetSecond
					.addComponent(vtlTestUtils.getVtlComponent("ID_2", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDatasetSecond
					.addComponent(vtlTestUtils.getVtlComponent("ME_2", VtlType.STRING, VtlComponentRole.MEASURE));
			datasetRepository.save(vtlDatasetSecond);
			
			VtlDataset vtlDatasetThird = new VtlDataset();
			vtlDatasetThird.setPersistent(true);
			vtlDatasetThird.setName("DS_65");
			vtlDatasetThird.setIsOnlyAScalar(false);
			vtlDatasetThird.setTransitory(false);
			vtlDatasetThird
					.addComponent(vtlTestUtils.getVtlComponent("ID_1", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDatasetThird
					.addComponent(vtlTestUtils.getVtlComponent("ID_2", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDatasetThird
					.addComponent(vtlTestUtils.getVtlComponent("ME_3", VtlType.STRING, VtlComponentRole.MEASURE));
			datasetRepository.save(vtlDatasetThird);
		}
		String commandStatements = "DS_r := left_join (DS_63 as ds63, DS_64 as ds64, DS_65 as ds65 using ID_1, ID_2);";
		String jsonResult = "[{\"result\":{\"id\":15,\"name\":\"DS_r\",\"description\":null,\"attributes\":[],\"persistent\":false,\"identifiers\":[{\"id\":29,\"type\":\"INTEGER\",\"name\":\"ID_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":30,\"type\":\"STRING\",\"name\":\"ID_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}],\"measures\":[{\"id\":31,\"type\":\"STRING\",\"name\":\"ME_1\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"MEASURE\"},{\"id\":32,\"type\":\"STRING\",\"name\":\"ME_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"MEASURE\"},{\"id\":33,\"type\":\"STRING\",\"name\":\"ME_3\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"viral\":[]},\"messages\":[],\"command\":\"DS_r:=left_join(DS_63asds63,DS_64asds64,DS_65asds65usingID_1,ID_2)\",\"acomponent\":false,\"ascalar\":false,\"adataset\":true},{\"result\":{\"id\":13,\"name\":\"temporary_12\",\"description\":null,\"attributes\":[],\"persistent\":false,\"identifiers\":[{\"id\":24,\"type\":\"INTEGER\",\"name\":\"ID_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":25,\"type\":\"STRING\",\"name\":\"ID_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}],\"measures\":[{\"id\":26,\"type\":\"STRING\",\"name\":\"ME_1\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"MEASURE\"},{\"id\":27,\"type\":\"STRING\",\"name\":\"ME_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"MEASURE\"},{\"id\":28,\"type\":\"STRING\",\"name\":\"ME_3\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"viral\":[]},\"messages\":[],\"command\":\"left_join(DS_63asds63,DS_64asds64,DS_65asds65usingID_1,ID_2)\",\"acomponent\":false,\"ascalar\":false,\"adataset\":true},{\"result\":{\"id\":11,\"name\":\"temporary_10\",\"description\":null,\"attributes\":[],\"persistent\":false,\"identifiers\":[{\"id\":19,\"type\":\"INTEGER\",\"name\":\"ID_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":20,\"type\":\"STRING\",\"name\":\"ID_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}],\"measures\":[{\"id\":21,\"type\":\"STRING\",\"name\":\"ME_1\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"MEASURE\"},{\"id\":22,\"type\":\"STRING\",\"name\":\"ME_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"MEASURE\"},{\"id\":23,\"type\":\"STRING\",\"name\":\"ME_3\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"viral\":[]},\"messages\":[],\"acomponent\":false,\"ascalar\":false,\"adataset\":true},{\"result\":{\"id\":9,\"name\":\"ds65\",\"description\":null,\"attributes\":[],\"persistent\":false,\"identifiers\":[{\"id\":16,\"type\":\"INTEGER\",\"name\":\"ID_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":17,\"type\":\"STRING\",\"name\":\"ID_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}],\"measures\":[{\"id\":18,\"type\":\"STRING\",\"name\":\"ME_3\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"viral\":[]},\"messages\":[],\"acomponent\":false,\"ascalar\":false,\"adataset\":true},{\"result\":{\"id\":7,\"name\":\"ds64\",\"description\":null,\"attributes\":[],\"persistent\":false,\"identifiers\":[{\"id\":13,\"type\":\"INTEGER\",\"name\":\"ID_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":14,\"type\":\"STRING\",\"name\":\"ID_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}],\"measures\":[{\"id\":15,\"type\":\"STRING\",\"name\":\"ME_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"viral\":[]},\"messages\":[],\"acomponent\":false,\"ascalar\":false,\"adataset\":true},{\"result\":{\"id\":5,\"name\":\"ds63\",\"description\":null,\"attributes\":[],\"persistent\":false,\"identifiers\":[{\"id\":10,\"type\":\"INTEGER\",\"name\":\"ID_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":11,\"type\":\"STRING\",\"name\":\"ID_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}],\"measures\":[{\"id\":12,\"type\":\"STRING\",\"name\":\"ME_1\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"viral\":[]},\"messages\":[],\"acomponent\":false,\"ascalar\":false,\"adataset\":true}]";
		List<ResultExpression> result = vtlTestUtils.getResult(commandStatements);
		System.out.println("########"+vtlTestUtils.convertResultToJson(result));
		List<ResultExpression> resultExpressionsToComapare = vtlTestUtils.convertJsonToResultExpression(jsonResult);
		assertNotNull("Conversione non riuscita", resultExpressionsToComapare);
		assertNotNull("Validazione Semantica non riuscita", result);
		assertTrue("I due risultati non sono uguali",
				vtlTestUtils.compareResult(result.get(0), resultExpressionsToComapare.get(0)));
	}
	
	@Test
	@Transactional
	public void leftJoinUsing3() throws Exception {
		VtlDataset vtlDatasetFind = datasetRepository.findByNameIgnoreCase("DS_1");
		if (vtlDatasetFind == null) {
			VtlDataset vtlDataset = new VtlDataset();
			vtlDataset.setPersistent(true);
			vtlDataset.setName("DS_63");
			vtlDataset.setIsOnlyAScalar(false);
			vtlDataset.setTransitory(false);
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_1", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_2", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_1", VtlType.STRING, VtlComponentRole.MEASURE));
			datasetRepository.save(vtlDataset);

			VtlDataset vtlDatasetSecond = new VtlDataset();
			vtlDatasetSecond.setPersistent(true);
			vtlDatasetSecond.setName("DS_64");
			vtlDatasetSecond.setIsOnlyAScalar(false);
			vtlDatasetSecond.setTransitory(false);
			vtlDatasetSecond
					.addComponent(vtlTestUtils.getVtlComponent("ID_1", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDatasetSecond
					.addComponent(vtlTestUtils.getVtlComponent("ID_2", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDatasetSecond
					.addComponent(vtlTestUtils.getVtlComponent("ME_2", VtlType.STRING, VtlComponentRole.MEASURE));
			datasetRepository.save(vtlDatasetSecond);
			
			VtlDataset vtlDatasetThird = new VtlDataset();
			vtlDatasetThird.setPersistent(true);
			vtlDatasetThird.setName("DS_65");
			vtlDatasetThird.setIsOnlyAScalar(false);
			vtlDatasetThird.setTransitory(false);
			vtlDatasetThird
					.addComponent(vtlTestUtils.getVtlComponent("ID_1", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDatasetThird
					.addComponent(vtlTestUtils.getVtlComponent("ID_2", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDatasetThird
					.addComponent(vtlTestUtils.getVtlComponent("ME_3", VtlType.STRING, VtlComponentRole.MEASURE));
			datasetRepository.save(vtlDatasetThird);
		}
		String commandStatements = "DS_r := left_join (DS_64 as ds64, DS_65 as ds65, DS_63 as ds63 using ID_1, ID_2);";
		String jsonResult = "[{\"result\":{\"id\":15,\"name\":\"DS_r\",\"description\":null,\"attributes\":[],\"persistent\":false,\"identifiers\":[{\"id\":29,\"type\":\"INTEGER\",\"name\":\"ID_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":30,\"type\":\"STRING\",\"name\":\"ID_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}],\"measures\":[{\"id\":31,\"type\":\"STRING\",\"name\":\"ME_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"MEASURE\"},{\"id\":32,\"type\":\"STRING\",\"name\":\"ME_3\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"MEASURE\"},{\"id\":33,\"type\":\"STRING\",\"name\":\"ME_1\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"viral\":[]},\"messages\":[],\"command\":\"DS_r:=left_join(DS_64asds64,DS_65asds65,DS_63asds63usingID_1,ID_2)\",\"adataset\":true,\"acomponent\":false,\"ascalar\":false},{\"result\":{\"id\":13,\"name\":\"temporary_12\",\"description\":null,\"attributes\":[],\"persistent\":false,\"identifiers\":[{\"id\":24,\"type\":\"INTEGER\",\"name\":\"ID_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":25,\"type\":\"STRING\",\"name\":\"ID_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}],\"measures\":[{\"id\":26,\"type\":\"STRING\",\"name\":\"ME_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"MEASURE\"},{\"id\":27,\"type\":\"STRING\",\"name\":\"ME_3\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"MEASURE\"},{\"id\":28,\"type\":\"STRING\",\"name\":\"ME_1\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"viral\":[]},\"messages\":[],\"command\":\"left_join(DS_64asds64,DS_65asds65,DS_63asds63usingID_1,ID_2)\",\"adataset\":true,\"acomponent\":false,\"ascalar\":false},{\"result\":{\"id\":11,\"name\":\"temporary_10\",\"description\":null,\"attributes\":[],\"persistent\":false,\"identifiers\":[{\"id\":19,\"type\":\"INTEGER\",\"name\":\"ID_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":20,\"type\":\"STRING\",\"name\":\"ID_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}],\"measures\":[{\"id\":21,\"type\":\"STRING\",\"name\":\"ME_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"MEASURE\"},{\"id\":22,\"type\":\"STRING\",\"name\":\"ME_3\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"MEASURE\"},{\"id\":23,\"type\":\"STRING\",\"name\":\"ME_1\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"viral\":[]},\"messages\":[],\"adataset\":true,\"acomponent\":false,\"ascalar\":false},{\"result\":{\"id\":9,\"name\":\"ds63\",\"description\":null,\"attributes\":[],\"persistent\":false,\"identifiers\":[{\"id\":16,\"type\":\"INTEGER\",\"name\":\"ID_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":17,\"type\":\"STRING\",\"name\":\"ID_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}],\"measures\":[{\"id\":18,\"type\":\"STRING\",\"name\":\"ME_1\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"viral\":[]},\"messages\":[],\"adataset\":true,\"acomponent\":false,\"ascalar\":false},{\"result\":{\"id\":7,\"name\":\"ds65\",\"description\":null,\"attributes\":[],\"persistent\":false,\"identifiers\":[{\"id\":13,\"type\":\"INTEGER\",\"name\":\"ID_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":14,\"type\":\"STRING\",\"name\":\"ID_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}],\"measures\":[{\"id\":15,\"type\":\"STRING\",\"name\":\"ME_3\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"viral\":[]},\"messages\":[],\"adataset\":true,\"acomponent\":false,\"ascalar\":false},{\"result\":{\"id\":5,\"name\":\"ds64\",\"description\":null,\"attributes\":[],\"persistent\":false,\"identifiers\":[{\"id\":10,\"type\":\"INTEGER\",\"name\":\"ID_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":11,\"type\":\"STRING\",\"name\":\"ID_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}],\"measures\":[{\"id\":12,\"type\":\"STRING\",\"name\":\"ME_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"viral\":[]},\"messages\":[],\"adataset\":true,\"acomponent\":false,\"ascalar\":false}]";
		List<ResultExpression> result = vtlTestUtils.getResult(commandStatements);
		System.out.println("########"+vtlTestUtils.convertResultToJson(result));
		List<ResultExpression> resultExpressionsToComapare = vtlTestUtils.convertJsonToResultExpression(jsonResult);
		assertNotNull("Conversione non riuscita", resultExpressionsToComapare);
		assertNotNull("Validazione Semantica non riuscita", result);
		assertTrue("I due risultati non sono uguali",
				vtlTestUtils.compareResult(result.get(0), resultExpressionsToComapare.get(0)));
	}

	@Test
	@Transactional
	public void leftJoinUsing5() throws Exception {
		VtlDataset vtlDatasetFind = datasetRepository.findByNameIgnoreCase("DS_1");
		if (vtlDatasetFind == null) {
			VtlDataset vtlDataset = new VtlDataset();
			vtlDataset.setPersistent(true);
			vtlDataset.setName("DS_63");
			vtlDataset.setIsOnlyAScalar(false);
			vtlDataset.setTransitory(false);
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_1", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("CMP_1", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_1", VtlType.STRING, VtlComponentRole.MEASURE));
			datasetRepository.save(vtlDataset);

			VtlDataset vtlDatasetSecond = new VtlDataset();
			vtlDatasetSecond.setPersistent(true);
			vtlDatasetSecond.setName("DS_64");
			vtlDatasetSecond.setIsOnlyAScalar(false);
			vtlDatasetSecond.setTransitory(false);
			vtlDatasetSecond
					.addComponent(vtlTestUtils.getVtlComponent("ID_1", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDatasetSecond
					.addComponent(vtlTestUtils.getVtlComponent("CMP_1", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDatasetSecond
					.addComponent(vtlTestUtils.getVtlComponent("ME_2", VtlType.STRING, VtlComponentRole.MEASURE));
			datasetRepository.save(vtlDatasetSecond);
			
			VtlDataset vtlDatasetThird = new VtlDataset();
			vtlDatasetThird.setPersistent(true);
			vtlDatasetThird.setName("DS_65");
			vtlDatasetThird.setIsOnlyAScalar(false);
			vtlDatasetThird.setTransitory(false);
			vtlDatasetThird
					.addComponent(vtlTestUtils.getVtlComponent("ID_1", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDatasetThird
					.addComponent(vtlTestUtils.getVtlComponent("ID_2", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDatasetThird
					.addComponent(vtlTestUtils.getVtlComponent("CMP_1", VtlType.STRING, VtlComponentRole.MEASURE));
			vtlDatasetThird
					.addComponent(vtlTestUtils.getVtlComponent("ME_3", VtlType.STRING, VtlComponentRole.MEASURE));
	
			datasetRepository.save(vtlDatasetThird);
		}
		String commandStatements = "DS_r := left_join (DS_65 as ds65, DS_63 as ds63, DS_64 as ds64 using ID_1, CMP_1);";
		String jsonResult = "[{\"result\":{\"id\":15,\"name\":\"DS_r\",\"description\":null,\"attributes\":[],\"persistent\":false,\"identifiers\":[{\"id\":33,\"type\":\"INTEGER\",\"name\":\"ID_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":34,\"type\":\"STRING\",\"name\":\"ID_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}],\"measures\":[{\"id\":35,\"type\":\"STRING\",\"name\":\"CMP_1\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"MEASURE\"},{\"id\":36,\"type\":\"STRING\",\"name\":\"ME_3\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"MEASURE\"},{\"id\":37,\"type\":\"STRING\",\"name\":\"ME_1\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"MEASURE\"},{\"id\":38,\"type\":\"STRING\",\"name\":\"ME_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"viral\":[]},\"messages\":[],\"command\":\"DS_r:=left_join(DS_65asds65,DS_63asds63,DS_64asds64usingID_1,CMP_1)\",\"adataset\":true,\"acomponent\":false,\"ascalar\":false},{\"result\":{\"id\":13,\"name\":\"temporary_12\",\"description\":null,\"attributes\":[],\"persistent\":false,\"identifiers\":[{\"id\":27,\"type\":\"INTEGER\",\"name\":\"ID_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":28,\"type\":\"STRING\",\"name\":\"ID_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}],\"measures\":[{\"id\":29,\"type\":\"STRING\",\"name\":\"CMP_1\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"MEASURE\"},{\"id\":30,\"type\":\"STRING\",\"name\":\"ME_3\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"MEASURE\"},{\"id\":31,\"type\":\"STRING\",\"name\":\"ME_1\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"MEASURE\"},{\"id\":32,\"type\":\"STRING\",\"name\":\"ME_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"viral\":[]},\"messages\":[],\"command\":\"left_join(DS_65asds65,DS_63asds63,DS_64asds64usingID_1,CMP_1)\",\"adataset\":true,\"acomponent\":false,\"ascalar\":false},{\"result\":{\"id\":11,\"name\":\"temporary_10\",\"description\":null,\"attributes\":[],\"persistent\":false,\"identifiers\":[{\"id\":21,\"type\":\"INTEGER\",\"name\":\"ID_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":22,\"type\":\"STRING\",\"name\":\"ID_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}],\"measures\":[{\"id\":23,\"type\":\"STRING\",\"name\":\"CMP_1\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"MEASURE\"},{\"id\":24,\"type\":\"STRING\",\"name\":\"ME_3\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"MEASURE\"},{\"id\":25,\"type\":\"STRING\",\"name\":\"ME_1\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"MEASURE\"},{\"id\":26,\"type\":\"STRING\",\"name\":\"ME_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"viral\":[]},\"messages\":[],\"adataset\":true,\"acomponent\":false,\"ascalar\":false},{\"result\":{\"id\":9,\"name\":\"ds64\",\"description\":null,\"attributes\":[],\"persistent\":false,\"identifiers\":[{\"id\":18,\"type\":\"INTEGER\",\"name\":\"ID_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":19,\"type\":\"STRING\",\"name\":\"CMP_1\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}],\"measures\":[{\"id\":20,\"type\":\"STRING\",\"name\":\"ME_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"viral\":[]},\"messages\":[],\"adataset\":true,\"acomponent\":false,\"ascalar\":false},{\"result\":{\"id\":7,\"name\":\"ds63\",\"description\":null,\"attributes\":[],\"persistent\":false,\"identifiers\":[{\"id\":15,\"type\":\"INTEGER\",\"name\":\"ID_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":16,\"type\":\"STRING\",\"name\":\"CMP_1\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}],\"measures\":[{\"id\":17,\"type\":\"STRING\",\"name\":\"ME_1\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"viral\":[]},\"messages\":[],\"adataset\":true,\"acomponent\":false,\"ascalar\":false},{\"result\":{\"id\":5,\"name\":\"ds65\",\"description\":null,\"attributes\":[],\"persistent\":false,\"identifiers\":[{\"id\":11,\"type\":\"INTEGER\",\"name\":\"ID_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":12,\"type\":\"STRING\",\"name\":\"ID_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}],\"measures\":[{\"id\":13,\"type\":\"STRING\",\"name\":\"CMP_1\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"MEASURE\"},{\"id\":14,\"type\":\"STRING\",\"name\":\"ME_3\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"viral\":[]},\"messages\":[],\"adataset\":true,\"acomponent\":false,\"ascalar\":false}]";
		List<ResultExpression> result = vtlTestUtils.getResult(commandStatements);
		System.out.println("########"+vtlTestUtils.convertResultToJson(result));
		List<ResultExpression> resultExpressionsToComapare = vtlTestUtils.convertJsonToResultExpression(jsonResult);
		assertNotNull("Conversione non riuscita", resultExpressionsToComapare);
		assertNotNull("Validazione Semantica non riuscita", result);
		assertTrue("I due risultati non sono uguali",
				vtlTestUtils.compareResult(result.get(0), resultExpressionsToComapare.get(0)));
	}
	
	@Test
	@Transactional
	public void fullJoinNoUsing() throws Exception {
		VtlDataset vtlDatasetFind = datasetRepository.findByNameIgnoreCase("DS_1");
		if (vtlDatasetFind == null) {
			VtlDataset vtlDataset = new VtlDataset();
			vtlDataset.setPersistent(true);
			vtlDataset.setName("DS_63");
			vtlDataset.setIsOnlyAScalar(false);
			vtlDataset.setTransitory(false);
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_1", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_2", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_1", VtlType.STRING, VtlComponentRole.MEASURE));
			datasetRepository.save(vtlDataset);

			VtlDataset vtlDatasetSecond = new VtlDataset();
			vtlDatasetSecond.setPersistent(true);
			vtlDatasetSecond.setName("DS_64");
			vtlDatasetSecond.setIsOnlyAScalar(false);
			vtlDatasetSecond.setTransitory(false);
			vtlDatasetSecond
					.addComponent(vtlTestUtils.getVtlComponent("ID_1", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDatasetSecond
					.addComponent(vtlTestUtils.getVtlComponent("ID_2", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDatasetSecond
					.addComponent(vtlTestUtils.getVtlComponent("ME_2", VtlType.STRING, VtlComponentRole.MEASURE));
			datasetRepository.save(vtlDatasetSecond);
		
		}
		String commandStatements = "DS_r := full_join (DS_63 as ds63, DS_64 as ds64);";
		String jsonResult = "[{\"result\":{\"id\":13,\"name\":\"DS_r\",\"description\":null,\"attributes\":[],\"persistent\":false,\"identifiers\":[{\"id\":25,\"type\":\"INTEGER\",\"name\":\"ID_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":26,\"type\":\"STRING\",\"name\":\"ID_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}],\"measures\":[{\"id\":27,\"type\":\"STRING\",\"name\":\"ME_1\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"MEASURE\"},{\"id\":28,\"type\":\"STRING\",\"name\":\"ME_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"viral\":[]},\"messages\":[],\"command\":\"DS_r:=full_join(DS_63asds63,DS_64asds64)\",\"adataset\":true,\"acomponent\":false,\"ascalar\":false},{\"result\":{\"id\":11,\"name\":\"temporary_10\",\"description\":null,\"attributes\":[],\"persistent\":false,\"identifiers\":[{\"id\":21,\"type\":\"INTEGER\",\"name\":\"ID_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":22,\"type\":\"STRING\",\"name\":\"ID_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}],\"measures\":[{\"id\":23,\"type\":\"STRING\",\"name\":\"ME_1\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"MEASURE\"},{\"id\":24,\"type\":\"STRING\",\"name\":\"ME_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"viral\":[]},\"messages\":[],\"command\":\"full_join(DS_63asds63,DS_64asds64)\",\"adataset\":true,\"acomponent\":false,\"ascalar\":false},{\"result\":{\"id\":9,\"name\":\"temporary_8\",\"description\":null,\"attributes\":[],\"persistent\":false,\"identifiers\":[{\"id\":17,\"type\":\"INTEGER\",\"name\":\"ID_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":18,\"type\":\"STRING\",\"name\":\"ID_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}],\"measures\":[{\"id\":19,\"type\":\"STRING\",\"name\":\"ME_1\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"MEASURE\"},{\"id\":20,\"type\":\"STRING\",\"name\":\"ME_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"viral\":[]},\"messages\":[],\"adataset\":true,\"acomponent\":false,\"ascalar\":false},{\"result\":{\"id\":7,\"name\":\"ds64\",\"description\":null,\"attributes\":[],\"persistent\":false,\"identifiers\":[{\"id\":14,\"type\":\"INTEGER\",\"name\":\"ID_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":15,\"type\":\"STRING\",\"name\":\"ID_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}],\"measures\":[{\"id\":16,\"type\":\"STRING\",\"name\":\"ME_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"viral\":[]},\"messages\":[],\"adataset\":true,\"acomponent\":false,\"ascalar\":false},{\"result\":{\"id\":5,\"name\":\"ds63\",\"description\":null,\"attributes\":[],\"persistent\":false,\"identifiers\":[{\"id\":11,\"type\":\"INTEGER\",\"name\":\"ID_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":12,\"type\":\"STRING\",\"name\":\"ID_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}],\"measures\":[{\"id\":13,\"type\":\"STRING\",\"name\":\"ME_1\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"viral\":[]},\"messages\":[],\"adataset\":true,\"acomponent\":false,\"ascalar\":false}]";
		List<ResultExpression> result = vtlTestUtils.getResult(commandStatements);
		System.out.println("########"+vtlTestUtils.convertResultToJson(result));
		List<ResultExpression> resultExpressionsToComapare = vtlTestUtils.convertJsonToResultExpression(jsonResult);
		assertNotNull("Conversione non riuscita", resultExpressionsToComapare);
		assertNotNull("Validazione Semantica non riuscita", result);
		assertTrue("I due risultati non sono uguali",
				vtlTestUtils.compareResult(result.get(0), resultExpressionsToComapare.get(0)));
	}

	@Test
	@Transactional
	public void innerJoinNoUsingIdentifier() throws Exception {
		VtlDataset vtlDatasetFind = datasetRepository.findByNameIgnoreCase("DS_1");
		if (vtlDatasetFind == null) {
			VtlDataset vtlDataset = new VtlDataset();
			vtlDataset.setPersistent(true);
			vtlDataset.setName("DS_63");
			vtlDataset.setIsOnlyAScalar(false);
			vtlDataset.setTransitory(false);
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_1", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_1", VtlType.STRING, VtlComponentRole.MEASURE));
			datasetRepository.save(vtlDataset);

			VtlDataset vtlDatasetSecond = new VtlDataset();
			vtlDatasetSecond.setPersistent(true);
			vtlDatasetSecond.setName("DS_64");
			vtlDatasetSecond.setIsOnlyAScalar(false);
			vtlDatasetSecond.setTransitory(false);
			vtlDatasetSecond
					.addComponent(vtlTestUtils.getVtlComponent("ID_1", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDatasetSecond
					.addComponent(vtlTestUtils.getVtlComponent("ID_2", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDatasetSecond
					.addComponent(vtlTestUtils.getVtlComponent("ME_2", VtlType.STRING, VtlComponentRole.MEASURE));
			datasetRepository.save(vtlDatasetSecond);
			
			VtlDataset vtlDatasetThird = new VtlDataset();
			vtlDatasetThird.setPersistent(true);
			vtlDatasetThird.setName("DS_65");
			vtlDatasetThird.setIsOnlyAScalar(false);
			vtlDatasetThird.setTransitory(false);
			vtlDatasetThird
					.addComponent(vtlTestUtils.getVtlComponent("ID_1", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDatasetThird
					.addComponent(vtlTestUtils.getVtlComponent("ID_2", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDatasetThird
					.addComponent(vtlTestUtils.getVtlComponent("ID_3", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDatasetThird
					.addComponent(vtlTestUtils.getVtlComponent("ME_3", VtlType.STRING, VtlComponentRole.MEASURE));
	
			datasetRepository.save(vtlDatasetThird);
			
		}
		String commandStatements = "DS_r := inner_join (DS_63 as ds63, DS_64 as ds64, DS_65 as ds65);";
		String jsonResult = "[{\"result\":{\"id\":15,\"name\":\"DS_r\",\"description\":null,\"attributes\":[],\"persistent\":false,\"identifiers\":[{\"id\":31,\"type\":\"INTEGER\",\"name\":\"ID_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":32,\"type\":\"STRING\",\"name\":\"ID_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":33,\"type\":\"STRING\",\"name\":\"ID_3\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}],\"measures\":[{\"id\":34,\"type\":\"STRING\",\"name\":\"ME_1\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"MEASURE\"},{\"id\":35,\"type\":\"STRING\",\"name\":\"ME_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"MEASURE\"},{\"id\":36,\"type\":\"STRING\",\"name\":\"ME_3\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"viral\":[]},\"messages\":[],\"command\":\"DS_r:=inner_join(DS_63asds63,DS_64asds64,DS_65asds65)\",\"adataset\":true,\"acomponent\":false,\"ascalar\":false},{\"result\":{\"id\":13,\"name\":\"temporary_12\",\"description\":null,\"attributes\":[],\"persistent\":false,\"identifiers\":[{\"id\":25,\"type\":\"INTEGER\",\"name\":\"ID_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":26,\"type\":\"STRING\",\"name\":\"ID_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":27,\"type\":\"STRING\",\"name\":\"ID_3\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}],\"measures\":[{\"id\":28,\"type\":\"STRING\",\"name\":\"ME_1\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"MEASURE\"},{\"id\":29,\"type\":\"STRING\",\"name\":\"ME_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"MEASURE\"},{\"id\":30,\"type\":\"STRING\",\"name\":\"ME_3\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"viral\":[]},\"messages\":[],\"command\":\"inner_join(DS_63asds63,DS_64asds64,DS_65asds65)\",\"adataset\":true,\"acomponent\":false,\"ascalar\":false},{\"result\":{\"id\":11,\"name\":\"temporary_10\",\"description\":null,\"attributes\":[],\"persistent\":false,\"identifiers\":[{\"id\":19,\"type\":\"INTEGER\",\"name\":\"ID_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":20,\"type\":\"STRING\",\"name\":\"ID_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":21,\"type\":\"STRING\",\"name\":\"ID_3\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}],\"measures\":[{\"id\":22,\"type\":\"STRING\",\"name\":\"ME_1\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"MEASURE\"},{\"id\":23,\"type\":\"STRING\",\"name\":\"ME_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"MEASURE\"},{\"id\":24,\"type\":\"STRING\",\"name\":\"ME_3\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"viral\":[]},\"messages\":[],\"adataset\":true,\"acomponent\":false,\"ascalar\":false},{\"result\":{\"id\":9,\"name\":\"ds65\",\"description\":null,\"attributes\":[],\"persistent\":false,\"identifiers\":[{\"id\":15,\"type\":\"INTEGER\",\"name\":\"ID_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":16,\"type\":\"STRING\",\"name\":\"ID_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":17,\"type\":\"STRING\",\"name\":\"ID_3\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}],\"measures\":[{\"id\":18,\"type\":\"STRING\",\"name\":\"ME_3\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"viral\":[]},\"messages\":[],\"adataset\":true,\"acomponent\":false,\"ascalar\":false},{\"result\":{\"id\":7,\"name\":\"ds64\",\"description\":null,\"attributes\":[],\"persistent\":false,\"identifiers\":[{\"id\":12,\"type\":\"INTEGER\",\"name\":\"ID_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":13,\"type\":\"STRING\",\"name\":\"ID_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}],\"measures\":[{\"id\":14,\"type\":\"STRING\",\"name\":\"ME_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"viral\":[]},\"messages\":[],\"adataset\":true,\"acomponent\":false,\"ascalar\":false},{\"result\":{\"id\":5,\"name\":\"ds63\",\"description\":null,\"attributes\":[],\"persistent\":false,\"identifiers\":[{\"id\":10,\"type\":\"INTEGER\",\"name\":\"ID_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}],\"measures\":[{\"id\":11,\"type\":\"STRING\",\"name\":\"ME_1\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"viral\":[]},\"messages\":[],\"adataset\":true,\"acomponent\":false,\"ascalar\":false}]";
		List<ResultExpression> result = vtlTestUtils.getResult(commandStatements);
		System.out.println("########"+vtlTestUtils.convertResultToJson(result));
		List<ResultExpression> resultExpressionsToComapare = vtlTestUtils.convertJsonToResultExpression(jsonResult);
		assertNotNull("Conversione non riuscita", resultExpressionsToComapare);
		assertNotNull("Validazione Semantica non riuscita", result);
		assertTrue("I due risultati non sono uguali",
				vtlTestUtils.compareResult(result.get(0), resultExpressionsToComapare.get(0)));
	}
	
	@Test
	@Transactional
	public void innerJoinUsingIdentifier() throws Exception {
		VtlDataset vtlDatasetFind = datasetRepository.findByNameIgnoreCase("DS_1");
		if (vtlDatasetFind == null) {
			VtlDataset vtlDataset = new VtlDataset();
			vtlDataset.setPersistent(true);
			vtlDataset.setName("DS_63");
			vtlDataset.setIsOnlyAScalar(false);
			vtlDataset.setTransitory(false);
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_1", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_2", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_1", VtlType.STRING, VtlComponentRole.MEASURE));
			datasetRepository.save(vtlDataset);

			VtlDataset vtlDatasetSecond = new VtlDataset();
			vtlDatasetSecond.setPersistent(true);
			vtlDatasetSecond.setName("DS_64");
			vtlDatasetSecond.setIsOnlyAScalar(false);
			vtlDatasetSecond.setTransitory(false);
			vtlDatasetSecond
					.addComponent(vtlTestUtils.getVtlComponent("ID_1", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDatasetSecond
					.addComponent(vtlTestUtils.getVtlComponent("ID_2", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDatasetSecond
					.addComponent(vtlTestUtils.getVtlComponent("ME_2", VtlType.STRING, VtlComponentRole.MEASURE));
			datasetRepository.save(vtlDatasetSecond);
			
			VtlDataset vtlDatasetThird = new VtlDataset();
			vtlDatasetThird.setPersistent(true);
			vtlDatasetThird.setName("DS_65");
			vtlDatasetThird.setIsOnlyAScalar(false);
			vtlDatasetThird.setTransitory(false);
			vtlDatasetThird
					.addComponent(vtlTestUtils.getVtlComponent("ID_1", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDatasetThird
					.addComponent(vtlTestUtils.getVtlComponent("ID_2", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDatasetThird
					.addComponent(vtlTestUtils.getVtlComponent("ID_3", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDatasetThird
					.addComponent(vtlTestUtils.getVtlComponent("ME_3", VtlType.STRING, VtlComponentRole.MEASURE));
	
			datasetRepository.save(vtlDatasetThird);
			
		}
		String commandStatements = "DS_r := inner_join (DS_63 as ds63, DS_64 as ds64, DS_65 as ds65 using ID_1);";
		String jsonResult = "[{\"result\":{\"id\":15,\"name\":\"DS_r\",\"description\":null,\"attributes\":[],\"persistent\":false,\"identifiers\":[{\"id\":33,\"type\":\"INTEGER\",\"name\":\"ID_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":34,\"type\":\"INTEGER\",\"name\":\"ID_2\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":35,\"type\":\"STRING\",\"name\":\"ID_3\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}],\"measures\":[{\"id\":36,\"type\":\"STRING\",\"name\":\"ME_1\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"MEASURE\"},{\"id\":37,\"type\":\"STRING\",\"name\":\"ME_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"MEASURE\"},{\"id\":38,\"type\":\"STRING\",\"name\":\"ME_3\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"viral\":[]},\"messages\":[],\"command\":\"DS_r:=inner_join(DS_63asds63,DS_64asds64,DS_65asds65usingID_1)\",\"ascalar\":false,\"adataset\":true,\"acomponent\":false},{\"result\":{\"id\":13,\"name\":\"temporary_12\",\"description\":null,\"attributes\":[],\"persistent\":false,\"identifiers\":[{\"id\":27,\"type\":\"INTEGER\",\"name\":\"ID_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":28,\"type\":\"INTEGER\",\"name\":\"ID_2\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":29,\"type\":\"STRING\",\"name\":\"ID_3\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}],\"measures\":[{\"id\":30,\"type\":\"STRING\",\"name\":\"ME_1\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"MEASURE\"},{\"id\":31,\"type\":\"STRING\",\"name\":\"ME_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"MEASURE\"},{\"id\":32,\"type\":\"STRING\",\"name\":\"ME_3\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"viral\":[]},\"messages\":[],\"command\":\"inner_join(DS_63asds63,DS_64asds64,DS_65asds65usingID_1)\",\"ascalar\":false,\"adataset\":true,\"acomponent\":false},{\"result\":{\"id\":11,\"name\":\"temporary_10\",\"description\":null,\"attributes\":[],\"persistent\":false,\"identifiers\":[{\"id\":21,\"type\":\"INTEGER\",\"name\":\"ID_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":22,\"type\":\"INTEGER\",\"name\":\"ID_2\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":23,\"type\":\"STRING\",\"name\":\"ID_3\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}],\"measures\":[{\"id\":24,\"type\":\"STRING\",\"name\":\"ME_1\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"MEASURE\"},{\"id\":25,\"type\":\"STRING\",\"name\":\"ME_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"MEASURE\"},{\"id\":26,\"type\":\"STRING\",\"name\":\"ME_3\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"viral\":[]},\"messages\":[],\"ascalar\":false,\"adataset\":true,\"acomponent\":false},{\"result\":{\"id\":9,\"name\":\"ds65\",\"description\":null,\"attributes\":[],\"persistent\":false,\"identifiers\":[{\"id\":17,\"type\":\"INTEGER\",\"name\":\"ID_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":18,\"type\":\"INTEGER\",\"name\":\"ID_2\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":19,\"type\":\"STRING\",\"name\":\"ID_3\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}],\"measures\":[{\"id\":20,\"type\":\"STRING\",\"name\":\"ME_3\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"viral\":[]},\"messages\":[],\"ascalar\":false,\"adataset\":true,\"acomponent\":false},{\"result\":{\"id\":7,\"name\":\"ds64\",\"description\":null,\"attributes\":[],\"persistent\":false,\"identifiers\":[{\"id\":14,\"type\":\"INTEGER\",\"name\":\"ID_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":15,\"type\":\"INTEGER\",\"name\":\"ID_2\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}],\"measures\":[{\"id\":16,\"type\":\"STRING\",\"name\":\"ME_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"viral\":[]},\"messages\":[],\"ascalar\":false,\"adataset\":true,\"acomponent\":false},{\"result\":{\"id\":5,\"name\":\"ds63\",\"description\":null,\"attributes\":[],\"persistent\":false,\"identifiers\":[{\"id\":11,\"type\":\"INTEGER\",\"name\":\"ID_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":12,\"type\":\"INTEGER\",\"name\":\"ID_2\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}],\"measures\":[{\"id\":13,\"type\":\"STRING\",\"name\":\"ME_1\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"viral\":[]},\"messages\":[],\"ascalar\":false,\"adataset\":true,\"acomponent\":false}]";
		List<ResultExpression> result = vtlTestUtils.getResult(commandStatements);
		System.out.println("########innerJoinUsingIdentifier: "+vtlTestUtils.convertResultToJson(result));
		List<ResultExpression> resultExpressionsToComapare = vtlTestUtils.convertJsonToResultExpression(jsonResult);
		assertNotNull("Conversione non riuscita", resultExpressionsToComapare);
		assertNotNull("Validazione Semantica non riuscita", result);
		assertTrue("I due risultati non sono uguali",
				vtlTestUtils.compareResult(result.get(0), resultExpressionsToComapare.get(0)));
	}
	
	@Test
	@Transactional
	public void innerJoinUsingIdentifier2() throws Exception {
		VtlDataset vtlDatasetFind = datasetRepository.findByNameIgnoreCase("DS_1");
		if (vtlDatasetFind == null) {
			VtlDataset vtlDataset = new VtlDataset();
			vtlDataset.setPersistent(true);
			vtlDataset.setName("DS_63");
			vtlDataset.setIsOnlyAScalar(false);
			vtlDataset.setTransitory(false);
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_1", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_2", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_1", VtlType.STRING, VtlComponentRole.MEASURE));
			datasetRepository.save(vtlDataset);

			VtlDataset vtlDatasetSecond = new VtlDataset();
			vtlDatasetSecond.setPersistent(true);
			vtlDatasetSecond.setName("DS_64");
			vtlDatasetSecond.setIsOnlyAScalar(false);
			vtlDatasetSecond.setTransitory(false);
			vtlDatasetSecond
					.addComponent(vtlTestUtils.getVtlComponent("ID_1", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDatasetSecond
					.addComponent(vtlTestUtils.getVtlComponent("ID_2", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDatasetSecond
					.addComponent(vtlTestUtils.getVtlComponent("ME_2", VtlType.STRING, VtlComponentRole.MEASURE));
			datasetRepository.save(vtlDatasetSecond);
			
			VtlDataset vtlDatasetThird = new VtlDataset();
			vtlDatasetThird.setPersistent(true);
			vtlDatasetThird.setName("DS_65");
			vtlDatasetThird.setIsOnlyAScalar(false);
			vtlDatasetThird.setTransitory(false);
			vtlDatasetThird
					.addComponent(vtlTestUtils.getVtlComponent("ID_1", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDatasetThird
					.addComponent(vtlTestUtils.getVtlComponent("ID_2", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDatasetThird
					.addComponent(vtlTestUtils.getVtlComponent("ID_3", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDatasetThird
					.addComponent(vtlTestUtils.getVtlComponent("ME_3", VtlType.STRING, VtlComponentRole.MEASURE));
	
			datasetRepository.save(vtlDatasetThird);
			
		}
		String commandStatements = "DS_r := inner_join (DS_63 as ds63, DS_64 as ds64, DS_65 as ds65 using ID_1, ID_2);";
		String jsonResult = "[{\"result\":{\"id\":75,\"name\":\"DS_r\",\"description\":null,\"attributes\":[],\"persistent\":false,\"identifiers\":[{\"id\":180,\"type\":\"INTEGER\",\"name\":\"ID_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":181,\"type\":\"INTEGER\",\"name\":\"ID_2\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":182,\"type\":\"STRING\",\"name\":\"ID_3\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}],\"measures\":[{\"id\":183,\"type\":\"STRING\",\"name\":\"ME_1\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"MEASURE\"},{\"id\":184,\"type\":\"STRING\",\"name\":\"ME_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"MEASURE\"},{\"id\":185,\"type\":\"STRING\",\"name\":\"ME_3\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"viral\":[]},\"messages\":[],\"command\":\"DS_r:=inner_join(DS_63asds63,DS_64asds64,DS_65asds65usingID_1,ID_2)\",\"acomponent\":false,\"adataset\":true,\"ascalar\":false},{\"result\":{\"id\":73,\"name\":\"temporary_72\",\"description\":null,\"attributes\":[],\"persistent\":false,\"identifiers\":[{\"id\":174,\"type\":\"INTEGER\",\"name\":\"ID_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":175,\"type\":\"INTEGER\",\"name\":\"ID_2\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":176,\"type\":\"STRING\",\"name\":\"ID_3\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}],\"measures\":[{\"id\":177,\"type\":\"STRING\",\"name\":\"ME_1\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"MEASURE\"},{\"id\":178,\"type\":\"STRING\",\"name\":\"ME_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"MEASURE\"},{\"id\":179,\"type\":\"STRING\",\"name\":\"ME_3\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"viral\":[]},\"messages\":[],\"command\":\"inner_join(DS_63asds63,DS_64asds64,DS_65asds65usingID_1,ID_2)\",\"acomponent\":false,\"adataset\":true,\"ascalar\":false},{\"result\":{\"id\":71,\"name\":\"temporary_70\",\"description\":null,\"attributes\":[],\"persistent\":false,\"identifiers\":[{\"id\":168,\"type\":\"INTEGER\",\"name\":\"ID_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":169,\"type\":\"INTEGER\",\"name\":\"ID_2\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":170,\"type\":\"STRING\",\"name\":\"ID_3\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}],\"measures\":[{\"id\":171,\"type\":\"STRING\",\"name\":\"ME_1\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"MEASURE\"},{\"id\":172,\"type\":\"STRING\",\"name\":\"ME_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"MEASURE\"},{\"id\":173,\"type\":\"STRING\",\"name\":\"ME_3\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"viral\":[]},\"messages\":[],\"acomponent\":false,\"adataset\":true,\"ascalar\":false},{\"result\":{\"id\":69,\"name\":\"ds65\",\"description\":null,\"attributes\":[],\"persistent\":false,\"identifiers\":[{\"id\":164,\"type\":\"INTEGER\",\"name\":\"ID_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":165,\"type\":\"INTEGER\",\"name\":\"ID_2\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":166,\"type\":\"STRING\",\"name\":\"ID_3\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}],\"measures\":[{\"id\":167,\"type\":\"STRING\",\"name\":\"ME_3\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"viral\":[]},\"messages\":[],\"acomponent\":false,\"adataset\":true,\"ascalar\":false},{\"result\":{\"id\":67,\"name\":\"ds64\",\"description\":null,\"attributes\":[],\"persistent\":false,\"identifiers\":[{\"id\":161,\"type\":\"INTEGER\",\"name\":\"ID_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":162,\"type\":\"INTEGER\",\"name\":\"ID_2\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}],\"measures\":[{\"id\":163,\"type\":\"STRING\",\"name\":\"ME_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"viral\":[]},\"messages\":[],\"acomponent\":false,\"adataset\":true,\"ascalar\":false},{\"result\":{\"id\":65,\"name\":\"ds63\",\"description\":null,\"attributes\":[],\"persistent\":false,\"identifiers\":[{\"id\":158,\"type\":\"INTEGER\",\"name\":\"ID_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":159,\"type\":\"INTEGER\",\"name\":\"ID_2\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}],\"measures\":[{\"id\":160,\"type\":\"STRING\",\"name\":\"ME_1\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"viral\":[]},\"messages\":[],\"acomponent\":false,\"adataset\":true,\"ascalar\":false}]";
		List<ResultExpression> result = vtlTestUtils.getResult(commandStatements);
		System.out.println("########innerJoinUsingIdentifier2: "+vtlTestUtils.convertResultToJson(result));
		List<ResultExpression> resultExpressionsToComapare = vtlTestUtils.convertJsonToResultExpression(jsonResult);
		assertNotNull("Conversione non riuscita", resultExpressionsToComapare);
		assertNotNull("Validazione Semantica non riuscita", result);
		assertTrue("I due risultati non sono uguali",
				vtlTestUtils.compareResult(result.get(0), resultExpressionsToComapare.get(0)));
	}
	
	@Test
	@Transactional
	public void innerJoinUsingIdentifier3() throws Exception {
		VtlDataset vtlDatasetFind = datasetRepository.findByNameIgnoreCase("DS_1");
		if (vtlDatasetFind == null) {
			VtlDataset vtlDataset = new VtlDataset();
			vtlDataset.setPersistent(true);
			vtlDataset.setName("DS_63");
			vtlDataset.setIsOnlyAScalar(false);
			vtlDataset.setTransitory(false);
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_1", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_2", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_1", VtlType.STRING, VtlComponentRole.MEASURE));
			datasetRepository.save(vtlDataset);

			VtlDataset vtlDatasetSecond = new VtlDataset();
			vtlDatasetSecond.setPersistent(true);
			vtlDatasetSecond.setName("DS_64");
			vtlDatasetSecond.setIsOnlyAScalar(false);
			vtlDatasetSecond.setTransitory(false);
			vtlDatasetSecond
					.addComponent(vtlTestUtils.getVtlComponent("ID_1", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDatasetSecond
					.addComponent(vtlTestUtils.getVtlComponent("ID_2", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDatasetSecond
					.addComponent(vtlTestUtils.getVtlComponent("ME_2", VtlType.STRING, VtlComponentRole.MEASURE));
			datasetRepository.save(vtlDatasetSecond);
						
		}
		String commandStatements = "DS_r := inner_join (DS_63 as ds63, DS_64 as ds64 using ID_1);";
		String jsonResult = "[{\"result\":{\"id\":87,\"name\":\"DS_r\",\"description\":null,\"attributes\":[],\"persistent\":false,\"identifiers\":[{\"id\":206,\"type\":\"INTEGER\",\"name\":\"ID_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":207,\"type\":\"INTEGER\",\"name\":\"ID_2\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}],\"measures\":[{\"id\":208,\"type\":\"STRING\",\"name\":\"ME_1\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"MEASURE\"},{\"id\":209,\"type\":\"STRING\",\"name\":\"ME_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"viral\":[]},\"messages\":[],\"command\":\"DS_r:=inner_join(DS_63asds63,DS_64asds64usingID_1)\",\"acomponent\":false,\"adataset\":true,\"ascalar\":false},{\"result\":{\"id\":85,\"name\":\"temporary_84\",\"description\":null,\"attributes\":[],\"persistent\":false,\"identifiers\":[{\"id\":202,\"type\":\"INTEGER\",\"name\":\"ID_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":203,\"type\":\"INTEGER\",\"name\":\"ID_2\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}],\"measures\":[{\"id\":204,\"type\":\"STRING\",\"name\":\"ME_1\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"MEASURE\"},{\"id\":205,\"type\":\"STRING\",\"name\":\"ME_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"viral\":[]},\"messages\":[],\"command\":\"inner_join(DS_63asds63,DS_64asds64usingID_1)\",\"acomponent\":false,\"adataset\":true,\"ascalar\":false},{\"result\":{\"id\":83,\"name\":\"temporary_82\",\"description\":null,\"attributes\":[],\"persistent\":false,\"identifiers\":[{\"id\":198,\"type\":\"INTEGER\",\"name\":\"ID_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":199,\"type\":\"INTEGER\",\"name\":\"ID_2\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}],\"measures\":[{\"id\":200,\"type\":\"STRING\",\"name\":\"ME_1\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"MEASURE\"},{\"id\":201,\"type\":\"STRING\",\"name\":\"ME_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"viral\":[]},\"messages\":[],\"acomponent\":false,\"adataset\":true,\"ascalar\":false},{\"result\":{\"id\":81,\"name\":\"ds64\",\"description\":null,\"attributes\":[],\"persistent\":false,\"identifiers\":[{\"id\":195,\"type\":\"INTEGER\",\"name\":\"ID_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":196,\"type\":\"INTEGER\",\"name\":\"ID_2\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}],\"measures\":[{\"id\":197,\"type\":\"STRING\",\"name\":\"ME_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"viral\":[]},\"messages\":[],\"acomponent\":false,\"adataset\":true,\"ascalar\":false},{\"result\":{\"id\":79,\"name\":\"ds63\",\"description\":null,\"attributes\":[],\"persistent\":false,\"identifiers\":[{\"id\":192,\"type\":\"INTEGER\",\"name\":\"ID_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":193,\"type\":\"INTEGER\",\"name\":\"ID_2\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}],\"measures\":[{\"id\":194,\"type\":\"STRING\",\"name\":\"ME_1\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"viral\":[]},\"messages\":[],\"acomponent\":false,\"adataset\":true,\"ascalar\":false}]";
		List<ResultExpression> result = vtlTestUtils.getResult(commandStatements);
		System.out.println("########innerJoinUsingIdentifier3: "+vtlTestUtils.convertResultToJson(result));
		List<ResultExpression> resultExpressionsToComapare = vtlTestUtils.convertJsonToResultExpression(jsonResult);
		assertNotNull("Conversione non riuscita", resultExpressionsToComapare);
		assertNotNull("Validazione Semantica non riuscita", result);
		assertTrue("I due risultati non sono uguali",
				vtlTestUtils.compareResult(result.get(0), resultExpressionsToComapare.get(0)));
	}
	
	@Test
	@Transactional
	public void innerJoinUsingIdentifier4() throws Exception {
		VtlDataset vtlDatasetFind = datasetRepository.findByNameIgnoreCase("DS_1");
		if (vtlDatasetFind == null) {
			VtlDataset vtlDataset = new VtlDataset();
			vtlDataset.setPersistent(true);
			vtlDataset.setName("DS_63");
			vtlDataset.setIsOnlyAScalar(false);
			vtlDataset.setTransitory(false);
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_1", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("CMP_1", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_1", VtlType.STRING, VtlComponentRole.MEASURE));
			datasetRepository.save(vtlDataset);

			VtlDataset vtlDatasetSecond = new VtlDataset();
			vtlDatasetSecond.setPersistent(true);
			vtlDatasetSecond.setName("DS_64");
			vtlDatasetSecond.setIsOnlyAScalar(false);
			vtlDatasetSecond.setTransitory(false);
			vtlDatasetSecond
					.addComponent(vtlTestUtils.getVtlComponent("ID_1", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDatasetSecond
					.addComponent(vtlTestUtils.getVtlComponent("CMP_1", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDatasetSecond
					.addComponent(vtlTestUtils.getVtlComponent("ME_2", VtlType.STRING, VtlComponentRole.MEASURE));
			datasetRepository.save(vtlDatasetSecond);
			
			VtlDataset vtlDatasetThird = new VtlDataset();
			vtlDatasetThird.setPersistent(true);
			vtlDatasetThird.setName("DS_65");
			vtlDatasetThird.setIsOnlyAScalar(false);
			vtlDatasetThird.setTransitory(false);
			vtlDatasetThird
					.addComponent(vtlTestUtils.getVtlComponent("ID_1", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDatasetThird
					.addComponent(vtlTestUtils.getVtlComponent("ID_2", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDatasetThird
					.addComponent(vtlTestUtils.getVtlComponent("CMP_1", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDatasetThird
					.addComponent(vtlTestUtils.getVtlComponent("ME_3", VtlType.STRING, VtlComponentRole.MEASURE));
	
			datasetRepository.save(vtlDatasetThird);
			
		}
		String commandStatements = "DS_r := inner_join (DS_63 as ds63, DS_64 as ds64, DS_65 as ds65 using ID_1, CMP_1);";
		String jsonResult = "[{\"result\":{\"id\":102,\"name\":\"DS_r\",\"description\":null,\"attributes\":[],\"persistent\":false,\"identifiers\":[{\"id\":242,\"type\":\"INTEGER\",\"name\":\"ID_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":243,\"type\":\"INTEGER\",\"name\":\"ID_2\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":244,\"type\":\"INTEGER\",\"name\":\"CMP_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}],\"measures\":[{\"id\":245,\"type\":\"STRING\",\"name\":\"ME_1\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"MEASURE\"},{\"id\":246,\"type\":\"STRING\",\"name\":\"ME_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"MEASURE\"},{\"id\":247,\"type\":\"STRING\",\"name\":\"ME_3\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"viral\":[]},\"messages\":[],\"command\":\"DS_r:=inner_join(DS_63asds63,DS_64asds64,DS_65asds65usingID_1,CMP_1)\",\"acomponent\":false,\"adataset\":true,\"ascalar\":false},{\"result\":{\"id\":100,\"name\":\"temporary_99\",\"description\":null,\"attributes\":[],\"persistent\":false,\"identifiers\":[{\"id\":236,\"type\":\"INTEGER\",\"name\":\"ID_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":237,\"type\":\"INTEGER\",\"name\":\"ID_2\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":238,\"type\":\"INTEGER\",\"name\":\"CMP_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}],\"measures\":[{\"id\":239,\"type\":\"STRING\",\"name\":\"ME_1\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"MEASURE\"},{\"id\":240,\"type\":\"STRING\",\"name\":\"ME_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"MEASURE\"},{\"id\":241,\"type\":\"STRING\",\"name\":\"ME_3\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"viral\":[]},\"messages\":[],\"command\":\"inner_join(DS_63asds63,DS_64asds64,DS_65asds65usingID_1,CMP_1)\",\"acomponent\":false,\"adataset\":true,\"ascalar\":false},{\"result\":{\"id\":98,\"name\":\"temporary_97\",\"description\":null,\"attributes\":[],\"persistent\":false,\"identifiers\":[{\"id\":230,\"type\":\"INTEGER\",\"name\":\"ID_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":231,\"type\":\"INTEGER\",\"name\":\"ID_2\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":232,\"type\":\"INTEGER\",\"name\":\"CMP_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}],\"measures\":[{\"id\":233,\"type\":\"STRING\",\"name\":\"ME_1\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"MEASURE\"},{\"id\":234,\"type\":\"STRING\",\"name\":\"ME_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"MEASURE\"},{\"id\":235,\"type\":\"STRING\",\"name\":\"ME_3\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"viral\":[]},\"messages\":[],\"acomponent\":false,\"adataset\":true,\"ascalar\":false},{\"result\":{\"id\":96,\"name\":\"ds65\",\"description\":null,\"attributes\":[],\"persistent\":false,\"identifiers\":[{\"id\":226,\"type\":\"INTEGER\",\"name\":\"ID_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":227,\"type\":\"INTEGER\",\"name\":\"ID_2\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":228,\"type\":\"INTEGER\",\"name\":\"CMP_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}],\"measures\":[{\"id\":229,\"type\":\"STRING\",\"name\":\"ME_3\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"viral\":[]},\"messages\":[],\"acomponent\":false,\"adataset\":true,\"ascalar\":false},{\"result\":{\"id\":94,\"name\":\"ds64\",\"description\":null,\"attributes\":[],\"persistent\":false,\"identifiers\":[{\"id\":223,\"type\":\"INTEGER\",\"name\":\"ID_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":224,\"type\":\"INTEGER\",\"name\":\"CMP_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}],\"measures\":[{\"id\":225,\"type\":\"STRING\",\"name\":\"ME_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"viral\":[]},\"messages\":[],\"acomponent\":false,\"adataset\":true,\"ascalar\":false},{\"result\":{\"id\":92,\"name\":\"ds63\",\"description\":null,\"attributes\":[],\"persistent\":false,\"identifiers\":[{\"id\":220,\"type\":\"INTEGER\",\"name\":\"ID_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":221,\"type\":\"INTEGER\",\"name\":\"CMP_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}],\"measures\":[{\"id\":222,\"type\":\"STRING\",\"name\":\"ME_1\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"viral\":[]},\"messages\":[],\"acomponent\":false,\"adataset\":true,\"ascalar\":false}]";
		List<ResultExpression> result = vtlTestUtils.getResult(commandStatements);
		System.out.println("########innerJoinUsingIdentifier4: "+vtlTestUtils.convertResultToJson(result));
		List<ResultExpression> resultExpressionsToComapare = vtlTestUtils.convertJsonToResultExpression(jsonResult);
		assertNotNull("Conversione non riuscita", resultExpressionsToComapare);
		assertNotNull("Validazione Semantica non riuscita", result);
		assertTrue("I due risultati non sono uguali",
				vtlTestUtils.compareResult(result.get(0), resultExpressionsToComapare.get(0)));
	}
	
	@Test
	@Transactional
	public void leftJoinNoUsingIdentifier() throws Exception {
		VtlDataset vtlDatasetFind = datasetRepository.findByNameIgnoreCase("DS_1");
		if (vtlDatasetFind == null) {
			VtlDataset vtlDataset = new VtlDataset();
			vtlDataset.setPersistent(true);
			vtlDataset.setName("DS_63");
			vtlDataset.setIsOnlyAScalar(false);
			vtlDataset.setTransitory(false);
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_1", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_2", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_1", VtlType.STRING, VtlComponentRole.MEASURE));
			datasetRepository.save(vtlDataset);

			VtlDataset vtlDatasetSecond = new VtlDataset();
			vtlDatasetSecond.setPersistent(true);
			vtlDatasetSecond.setName("DS_64");
			vtlDatasetSecond.setIsOnlyAScalar(false);
			vtlDatasetSecond.setTransitory(false);
			vtlDatasetSecond
					.addComponent(vtlTestUtils.getVtlComponent("ID_1", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDatasetSecond
					.addComponent(vtlTestUtils.getVtlComponent("ID_2", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDatasetSecond
					.addComponent(vtlTestUtils.getVtlComponent("ME_2", VtlType.STRING, VtlComponentRole.MEASURE));
			datasetRepository.save(vtlDatasetSecond);
			
			VtlDataset vtlDatasetThird = new VtlDataset();
			vtlDatasetThird.setPersistent(true);
			vtlDatasetThird.setName("DS_65");
			vtlDatasetThird.setIsOnlyAScalar(false);
			vtlDatasetThird.setTransitory(false);
			vtlDatasetThird
					.addComponent(vtlTestUtils.getVtlComponent("ID_1", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDatasetThird
					.addComponent(vtlTestUtils.getVtlComponent("ID_2", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDatasetThird
					.addComponent(vtlTestUtils.getVtlComponent("ME_3", VtlType.STRING, VtlComponentRole.MEASURE));
	
			datasetRepository.save(vtlDatasetThird);
			
		}
		String commandStatements = "DS_r := left_join (DS_63 as ds63, DS_64 as ds64, DS_65 as ds65);";
		String jsonResult = "[{\"result\":{\"id\":440,\"name\":\"DS_r\",\"description\":null,\"attributes\":[],\"persistent\":false,\"identifiers\":[{\"id\":1044,\"type\":\"INTEGER\",\"name\":\"ID_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":1045,\"type\":\"INTEGER\",\"name\":\"ID_2\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}],\"measures\":[{\"id\":1046,\"type\":\"STRING\",\"name\":\"ME_1\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"MEASURE\"},{\"id\":1047,\"type\":\"STRING\",\"name\":\"ME_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"MEASURE\"},{\"id\":1048,\"type\":\"STRING\",\"name\":\"ME_3\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"viral\":[]},\"messages\":[],\"command\":\"DS_r:=left_join(DS_63asds63,DS_64asds64,DS_65asds65)\",\"acomponent\":false,\"adataset\":true,\"ascalar\":false},{\"result\":{\"id\":438,\"name\":\"temporary_437\",\"description\":null,\"attributes\":[],\"persistent\":false,\"identifiers\":[{\"id\":1039,\"type\":\"INTEGER\",\"name\":\"ID_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":1040,\"type\":\"INTEGER\",\"name\":\"ID_2\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}],\"measures\":[{\"id\":1041,\"type\":\"STRING\",\"name\":\"ME_1\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"MEASURE\"},{\"id\":1042,\"type\":\"STRING\",\"name\":\"ME_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"MEASURE\"},{\"id\":1043,\"type\":\"STRING\",\"name\":\"ME_3\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"viral\":[]},\"messages\":[],\"command\":\"left_join(DS_63asds63,DS_64asds64,DS_65asds65)\",\"acomponent\":false,\"adataset\":true,\"ascalar\":false},{\"result\":{\"id\":436,\"name\":\"temporary_435\",\"description\":null,\"attributes\":[],\"persistent\":false,\"identifiers\":[{\"id\":1034,\"type\":\"INTEGER\",\"name\":\"ID_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":1035,\"type\":\"INTEGER\",\"name\":\"ID_2\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}],\"measures\":[{\"id\":1036,\"type\":\"STRING\",\"name\":\"ME_1\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"MEASURE\"},{\"id\":1037,\"type\":\"STRING\",\"name\":\"ME_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"MEASURE\"},{\"id\":1038,\"type\":\"STRING\",\"name\":\"ME_3\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"viral\":[]},\"messages\":[],\"acomponent\":false,\"adataset\":true,\"ascalar\":false},{\"result\":{\"id\":434,\"name\":\"ds65\",\"description\":null,\"attributes\":[],\"persistent\":false,\"identifiers\":[{\"id\":1031,\"type\":\"INTEGER\",\"name\":\"ID_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":1032,\"type\":\"INTEGER\",\"name\":\"ID_2\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}],\"measures\":[{\"id\":1033,\"type\":\"STRING\",\"name\":\"ME_3\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"viral\":[]},\"messages\":[],\"acomponent\":false,\"adataset\":true,\"ascalar\":false},{\"result\":{\"id\":432,\"name\":\"ds64\",\"description\":null,\"attributes\":[],\"persistent\":false,\"identifiers\":[{\"id\":1028,\"type\":\"INTEGER\",\"name\":\"ID_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":1029,\"type\":\"INTEGER\",\"name\":\"ID_2\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}],\"measures\":[{\"id\":1030,\"type\":\"STRING\",\"name\":\"ME_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"viral\":[]},\"messages\":[],\"acomponent\":false,\"adataset\":true,\"ascalar\":false},{\"result\":{\"id\":430,\"name\":\"ds63\",\"description\":null,\"attributes\":[],\"persistent\":false,\"identifiers\":[{\"id\":1025,\"type\":\"INTEGER\",\"name\":\"ID_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":1026,\"type\":\"INTEGER\",\"name\":\"ID_2\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}],\"measures\":[{\"id\":1027,\"type\":\"STRING\",\"name\":\"ME_1\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"viral\":[]},\"messages\":[],\"acomponent\":false,\"adataset\":true,\"ascalar\":false}]";
		List<ResultExpression> result = vtlTestUtils.getResult(commandStatements);
		System.out.println("########leftJoinNoUsingIdentifier: "+vtlTestUtils.convertResultToJson(result));
		List<ResultExpression> resultExpressionsToComapare = vtlTestUtils.convertJsonToResultExpression(jsonResult);
		assertNotNull("Conversione non riuscita", resultExpressionsToComapare);
		assertNotNull("Validazione Semantica non riuscita", result);
		assertTrue("I due risultati non sono uguali",
				vtlTestUtils.compareResult(result.get(0), resultExpressionsToComapare.get(0)));
	}
	
	@Test
	@Transactional
	public void leftJoinUsingIdentifier() throws Exception {
		VtlDataset vtlDatasetFind = datasetRepository.findByNameIgnoreCase("DS_1");
		if (vtlDatasetFind == null) {
			VtlDataset vtlDataset = new VtlDataset();
			vtlDataset.setPersistent(true);
			vtlDataset.setName("DS_63");
			vtlDataset.setIsOnlyAScalar(false);
			vtlDataset.setTransitory(false);
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_1", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_2", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_1", VtlType.STRING, VtlComponentRole.MEASURE));
			datasetRepository.save(vtlDataset);

			VtlDataset vtlDatasetSecond = new VtlDataset();
			vtlDatasetSecond.setPersistent(true);
			vtlDatasetSecond.setName("DS_64");
			vtlDatasetSecond.setIsOnlyAScalar(false);
			vtlDatasetSecond.setTransitory(false);
			vtlDatasetSecond
					.addComponent(vtlTestUtils.getVtlComponent("ID_1", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDatasetSecond
					.addComponent(vtlTestUtils.getVtlComponent("ID_2", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDatasetSecond
					.addComponent(vtlTestUtils.getVtlComponent("ME_2", VtlType.STRING, VtlComponentRole.MEASURE));
			datasetRepository.save(vtlDatasetSecond);
			
			VtlDataset vtlDatasetThird = new VtlDataset();
			vtlDatasetThird.setPersistent(true);
			vtlDatasetThird.setName("DS_65");
			vtlDatasetThird.setIsOnlyAScalar(false);
			vtlDatasetThird.setTransitory(false);
			vtlDatasetThird
					.addComponent(vtlTestUtils.getVtlComponent("ID_1", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDatasetThird
					.addComponent(vtlTestUtils.getVtlComponent("ID_2", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDatasetThird
					.addComponent(vtlTestUtils.getVtlComponent("ME_3", VtlType.STRING, VtlComponentRole.MEASURE));
	
			datasetRepository.save(vtlDatasetThird);
			
		}
		String commandStatements = "DS_r := left_join (DS_63 as ds63, DS_64 as ds64, DS_65 as ds65 using ID_1);";
		String jsonResult = "[{\"result\":{\"id\":475,\"name\":\"DS_r\",\"description\":null,\"attributes\":[],\"persistent\":false,\"identifiers\":[{\"id\":1131,\"type\":\"INTEGER\",\"name\":\"ID_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":1132,\"type\":\"INTEGER\",\"name\":\"ID_2\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}],\"measures\":[{\"id\":1133,\"type\":\"STRING\",\"name\":\"ME_1\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"MEASURE\"},{\"id\":1134,\"type\":\"STRING\",\"name\":\"ME_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"MEASURE\"},{\"id\":1135,\"type\":\"STRING\",\"name\":\"ME_3\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"viral\":[]},\"messages\":[],\"command\":\"DS_r:=left_join(DS_63asds63,DS_64asds64,DS_65asds65usingID_1)\",\"acomponent\":false,\"adataset\":true,\"ascalar\":false},{\"result\":{\"id\":473,\"name\":\"temporary_472\",\"description\":null,\"attributes\":[],\"persistent\":false,\"identifiers\":[{\"id\":1126,\"type\":\"INTEGER\",\"name\":\"ID_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":1127,\"type\":\"INTEGER\",\"name\":\"ID_2\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}],\"measures\":[{\"id\":1128,\"type\":\"STRING\",\"name\":\"ME_1\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"MEASURE\"},{\"id\":1129,\"type\":\"STRING\",\"name\":\"ME_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"MEASURE\"},{\"id\":1130,\"type\":\"STRING\",\"name\":\"ME_3\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"viral\":[]},\"messages\":[],\"command\":\"left_join(DS_63asds63,DS_64asds64,DS_65asds65usingID_1)\",\"acomponent\":false,\"adataset\":true,\"ascalar\":false},{\"result\":{\"id\":471,\"name\":\"temporary_470\",\"description\":null,\"attributes\":[],\"persistent\":false,\"identifiers\":[{\"id\":1121,\"type\":\"INTEGER\",\"name\":\"ID_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":1122,\"type\":\"INTEGER\",\"name\":\"ID_2\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}],\"measures\":[{\"id\":1123,\"type\":\"STRING\",\"name\":\"ME_1\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"MEASURE\"},{\"id\":1124,\"type\":\"STRING\",\"name\":\"ME_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"MEASURE\"},{\"id\":1125,\"type\":\"STRING\",\"name\":\"ME_3\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"viral\":[]},\"messages\":[],\"acomponent\":false,\"adataset\":true,\"ascalar\":false},{\"result\":{\"id\":469,\"name\":\"ds65\",\"description\":null,\"attributes\":[],\"persistent\":false,\"identifiers\":[{\"id\":1118,\"type\":\"INTEGER\",\"name\":\"ID_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":1119,\"type\":\"INTEGER\",\"name\":\"ID_2\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}],\"measures\":[{\"id\":1120,\"type\":\"STRING\",\"name\":\"ME_3\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"viral\":[]},\"messages\":[],\"acomponent\":false,\"adataset\":true,\"ascalar\":false},{\"result\":{\"id\":467,\"name\":\"ds64\",\"description\":null,\"attributes\":[],\"persistent\":false,\"identifiers\":[{\"id\":1115,\"type\":\"INTEGER\",\"name\":\"ID_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":1116,\"type\":\"INTEGER\",\"name\":\"ID_2\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}],\"measures\":[{\"id\":1117,\"type\":\"STRING\",\"name\":\"ME_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"viral\":[]},\"messages\":[],\"acomponent\":false,\"adataset\":true,\"ascalar\":false},{\"result\":{\"id\":465,\"name\":\"ds63\",\"description\":null,\"attributes\":[],\"persistent\":false,\"identifiers\":[{\"id\":1112,\"type\":\"INTEGER\",\"name\":\"ID_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":1113,\"type\":\"INTEGER\",\"name\":\"ID_2\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}],\"measures\":[{\"id\":1114,\"type\":\"STRING\",\"name\":\"ME_1\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"viral\":[]},\"messages\":[],\"acomponent\":false,\"adataset\":true,\"ascalar\":false}]";
		List<ResultExpression> result = vtlTestUtils.getResult(commandStatements);
		System.out.println("########leftJoinUsingIdentifier: "+vtlTestUtils.convertResultToJson(result));
		List<ResultExpression> resultExpressionsToComapare = vtlTestUtils.convertJsonToResultExpression(jsonResult);
		assertNotNull("Conversione non riuscita", resultExpressionsToComapare);
		assertNotNull("Validazione Semantica non riuscita", result);
		assertTrue("I due risultati non sono uguali",
				vtlTestUtils.compareResult(result.get(0), resultExpressionsToComapare.get(0)));
	}
	
	@Test
	@Transactional
	public void leftJoinUsingIdentifier2() throws Exception {
		VtlDataset vtlDatasetFind = datasetRepository.findByNameIgnoreCase("DS_1");
		if (vtlDatasetFind == null) {
			VtlDataset vtlDataset = new VtlDataset();
			vtlDataset.setPersistent(true);
			vtlDataset.setName("DS_63");
			vtlDataset.setIsOnlyAScalar(false);
			vtlDataset.setTransitory(false);
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_1", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_2", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_1", VtlType.STRING, VtlComponentRole.MEASURE));
			datasetRepository.save(vtlDataset);

			VtlDataset vtlDatasetSecond = new VtlDataset();
			vtlDatasetSecond.setPersistent(true);
			vtlDatasetSecond.setName("DS_64");
			vtlDatasetSecond.setIsOnlyAScalar(false);
			vtlDatasetSecond.setTransitory(false);
			vtlDatasetSecond
					.addComponent(vtlTestUtils.getVtlComponent("ID_1", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDatasetSecond
					.addComponent(vtlTestUtils.getVtlComponent("ID_2", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDatasetSecond
					.addComponent(vtlTestUtils.getVtlComponent("ME_2", VtlType.STRING, VtlComponentRole.MEASURE));
			datasetRepository.save(vtlDatasetSecond);
			
			VtlDataset vtlDatasetThird = new VtlDataset();
			vtlDatasetThird.setPersistent(true);
			vtlDatasetThird.setName("DS_65");
			vtlDatasetThird.setIsOnlyAScalar(false);
			vtlDatasetThird.setTransitory(false);
			vtlDatasetThird
					.addComponent(vtlTestUtils.getVtlComponent("ID_1", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDatasetThird
					.addComponent(vtlTestUtils.getVtlComponent("ID_2", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDatasetThird
					.addComponent(vtlTestUtils.getVtlComponent("ME_3", VtlType.STRING, VtlComponentRole.MEASURE));
	
			datasetRepository.save(vtlDatasetThird);
			
		}
		String commandStatements = "DS_r := left_join (DS_63 as ds63, DS_64 as ds64, DS_65 as ds65 using ID_1, ID_2);";
		String jsonResult = "[{\"result\":{\"id\":15,\"name\":\"DS_r\",\"description\":null,\"attributes\":[],\"persistent\":false,\"identifiers\":[{\"id\":29,\"type\":\"INTEGER\",\"name\":\"ID_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":30,\"type\":\"INTEGER\",\"name\":\"ID_2\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}],\"measures\":[{\"id\":31,\"type\":\"STRING\",\"name\":\"ME_1\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"MEASURE\"},{\"id\":32,\"type\":\"STRING\",\"name\":\"ME_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"MEASURE\"},{\"id\":33,\"type\":\"STRING\",\"name\":\"ME_3\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"viral\":[]},\"messages\":[],\"command\":\"DS_r:=left_join(DS_63asds63,DS_64asds64,DS_65asds65usingID_1,ID_2)\",\"adataset\":true,\"acomponent\":false,\"ascalar\":false},{\"result\":{\"id\":13,\"name\":\"temporary_12\",\"description\":null,\"attributes\":[],\"persistent\":false,\"identifiers\":[{\"id\":24,\"type\":\"INTEGER\",\"name\":\"ID_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":25,\"type\":\"INTEGER\",\"name\":\"ID_2\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}],\"measures\":[{\"id\":26,\"type\":\"STRING\",\"name\":\"ME_1\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"MEASURE\"},{\"id\":27,\"type\":\"STRING\",\"name\":\"ME_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"MEASURE\"},{\"id\":28,\"type\":\"STRING\",\"name\":\"ME_3\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"viral\":[]},\"messages\":[],\"command\":\"left_join(DS_63asds63,DS_64asds64,DS_65asds65usingID_1,ID_2)\",\"adataset\":true,\"acomponent\":false,\"ascalar\":false},{\"result\":{\"id\":11,\"name\":\"temporary_10\",\"description\":null,\"attributes\":[],\"persistent\":false,\"identifiers\":[{\"id\":19,\"type\":\"INTEGER\",\"name\":\"ID_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":20,\"type\":\"INTEGER\",\"name\":\"ID_2\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}],\"measures\":[{\"id\":21,\"type\":\"STRING\",\"name\":\"ME_1\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"MEASURE\"},{\"id\":22,\"type\":\"STRING\",\"name\":\"ME_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"MEASURE\"},{\"id\":23,\"type\":\"STRING\",\"name\":\"ME_3\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"viral\":[]},\"messages\":[],\"adataset\":true,\"acomponent\":false,\"ascalar\":false},{\"result\":{\"id\":9,\"name\":\"ds65\",\"description\":null,\"attributes\":[],\"persistent\":false,\"identifiers\":[{\"id\":16,\"type\":\"INTEGER\",\"name\":\"ID_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":17,\"type\":\"INTEGER\",\"name\":\"ID_2\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}],\"measures\":[{\"id\":18,\"type\":\"STRING\",\"name\":\"ME_3\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"viral\":[]},\"messages\":[],\"adataset\":true,\"acomponent\":false,\"ascalar\":false},{\"result\":{\"id\":7,\"name\":\"ds64\",\"description\":null,\"attributes\":[],\"persistent\":false,\"identifiers\":[{\"id\":13,\"type\":\"INTEGER\",\"name\":\"ID_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":14,\"type\":\"INTEGER\",\"name\":\"ID_2\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}],\"measures\":[{\"id\":15,\"type\":\"STRING\",\"name\":\"ME_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"viral\":[]},\"messages\":[],\"adataset\":true,\"acomponent\":false,\"ascalar\":false},{\"result\":{\"id\":5,\"name\":\"ds63\",\"description\":null,\"attributes\":[],\"persistent\":false,\"identifiers\":[{\"id\":10,\"type\":\"INTEGER\",\"name\":\"ID_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":11,\"type\":\"INTEGER\",\"name\":\"ID_2\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}],\"measures\":[{\"id\":12,\"type\":\"STRING\",\"name\":\"ME_1\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"viral\":[]},\"messages\":[],\"adataset\":true,\"acomponent\":false,\"ascalar\":false}]";
		List<ResultExpression> result = vtlTestUtils.getResult(commandStatements);
		System.out.println("########"+vtlTestUtils.convertResultToJson(result));
		List<ResultExpression> resultExpressionsToComapare = vtlTestUtils.convertJsonToResultExpression(jsonResult);
		assertNotNull("Conversione non riuscita", resultExpressionsToComapare);
		assertNotNull("Validazione Semantica non riuscita", result);
		assertTrue("I due risultati non sono uguali",
				vtlTestUtils.compareResult(result.get(0), resultExpressionsToComapare.get(0)));
	}
	
	@Test
	@Transactional
	public void leftJoinUsingIdentifier3() throws Exception {
		VtlDataset vtlDatasetFind = datasetRepository.findByNameIgnoreCase("DS_1");
		if (vtlDatasetFind == null) {
			VtlDataset vtlDataset = new VtlDataset();
			vtlDataset.setPersistent(true);
			vtlDataset.setName("DS_63");
			vtlDataset.setIsOnlyAScalar(false);
			vtlDataset.setTransitory(false);
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_1", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("CMP_1", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_1", VtlType.STRING, VtlComponentRole.MEASURE));
			datasetRepository.save(vtlDataset);

			VtlDataset vtlDatasetSecond = new VtlDataset();
			vtlDatasetSecond.setPersistent(true);
			vtlDatasetSecond.setName("DS_64");
			vtlDatasetSecond.setIsOnlyAScalar(false);
			vtlDatasetSecond.setTransitory(false);
			vtlDatasetSecond
					.addComponent(vtlTestUtils.getVtlComponent("ID_1", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDatasetSecond
					.addComponent(vtlTestUtils.getVtlComponent("CMP_1", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDatasetSecond
					.addComponent(vtlTestUtils.getVtlComponent("ME_2", VtlType.STRING, VtlComponentRole.MEASURE));
			datasetRepository.save(vtlDatasetSecond);
			
			VtlDataset vtlDatasetThird = new VtlDataset();
			vtlDatasetThird.setPersistent(true);
			vtlDatasetThird.setName("DS_65");
			vtlDatasetThird.setIsOnlyAScalar(false);
			vtlDatasetThird.setTransitory(false);
			vtlDatasetThird
					.addComponent(vtlTestUtils.getVtlComponent("ID_1", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDatasetThird
					.addComponent(vtlTestUtils.getVtlComponent("ID_2", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDatasetThird
					.addComponent(vtlTestUtils.getVtlComponent("CMP_1", VtlType.INTEGER, VtlComponentRole.MEASURE));
			vtlDatasetThird
					.addComponent(vtlTestUtils.getVtlComponent("ME_3", VtlType.STRING, VtlComponentRole.MEASURE));
	
			datasetRepository.save(vtlDatasetThird);
			
		}
		String commandStatements = "DS_r := left_join (DS_65 as ds65, DS_63 as ds63, DS_64 as ds64 using ID_1, CMP_1);";
		String jsonResult = "[{\"result\":{\"id\":15,\"name\":\"DS_r\",\"description\":null,\"attributes\":[],\"persistent\":false,\"identifiers\":[{\"id\":33,\"type\":\"INTEGER\",\"name\":\"ID_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":34,\"type\":\"STRING\",\"name\":\"ID_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}],\"measures\":[{\"id\":35,\"type\":\"INTEGER\",\"name\":\"CMP_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},{\"id\":36,\"type\":\"STRING\",\"name\":\"ME_3\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"MEASURE\"},{\"id\":37,\"type\":\"STRING\",\"name\":\"ME_1\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"MEASURE\"},{\"id\":38,\"type\":\"STRING\",\"name\":\"ME_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"viral\":[]},\"messages\":[],\"command\":\"DS_r:=left_join(DS_65asds65,DS_63asds63,DS_64asds64usingID_1,CMP_1)\",\"adataset\":true,\"acomponent\":false,\"ascalar\":false},{\"result\":{\"id\":13,\"name\":\"temporary_12\",\"description\":null,\"attributes\":[],\"persistent\":false,\"identifiers\":[{\"id\":27,\"type\":\"INTEGER\",\"name\":\"ID_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":28,\"type\":\"STRING\",\"name\":\"ID_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}],\"measures\":[{\"id\":29,\"type\":\"INTEGER\",\"name\":\"CMP_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},{\"id\":30,\"type\":\"STRING\",\"name\":\"ME_3\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"MEASURE\"},{\"id\":31,\"type\":\"STRING\",\"name\":\"ME_1\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"MEASURE\"},{\"id\":32,\"type\":\"STRING\",\"name\":\"ME_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"viral\":[]},\"messages\":[],\"command\":\"left_join(DS_65asds65,DS_63asds63,DS_64asds64usingID_1,CMP_1)\",\"adataset\":true,\"acomponent\":false,\"ascalar\":false},{\"result\":{\"id\":11,\"name\":\"temporary_10\",\"description\":null,\"attributes\":[],\"persistent\":false,\"identifiers\":[{\"id\":21,\"type\":\"INTEGER\",\"name\":\"ID_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":22,\"type\":\"STRING\",\"name\":\"ID_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}],\"measures\":[{\"id\":23,\"type\":\"INTEGER\",\"name\":\"CMP_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},{\"id\":24,\"type\":\"STRING\",\"name\":\"ME_3\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"MEASURE\"},{\"id\":25,\"type\":\"STRING\",\"name\":\"ME_1\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"MEASURE\"},{\"id\":26,\"type\":\"STRING\",\"name\":\"ME_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"viral\":[]},\"messages\":[],\"adataset\":true,\"acomponent\":false,\"ascalar\":false},{\"result\":{\"id\":9,\"name\":\"ds64\",\"description\":null,\"attributes\":[],\"persistent\":false,\"identifiers\":[{\"id\":18,\"type\":\"INTEGER\",\"name\":\"ID_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":19,\"type\":\"INTEGER\",\"name\":\"CMP_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}],\"measures\":[{\"id\":20,\"type\":\"STRING\",\"name\":\"ME_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"viral\":[]},\"messages\":[],\"adataset\":true,\"acomponent\":false,\"ascalar\":false},{\"result\":{\"id\":7,\"name\":\"ds63\",\"description\":null,\"attributes\":[],\"persistent\":false,\"identifiers\":[{\"id\":15,\"type\":\"INTEGER\",\"name\":\"ID_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":16,\"type\":\"INTEGER\",\"name\":\"CMP_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}],\"measures\":[{\"id\":17,\"type\":\"STRING\",\"name\":\"ME_1\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"viral\":[]},\"messages\":[],\"adataset\":true,\"acomponent\":false,\"ascalar\":false},{\"result\":{\"id\":5,\"name\":\"ds65\",\"description\":null,\"attributes\":[],\"persistent\":false,\"identifiers\":[{\"id\":11,\"type\":\"INTEGER\",\"name\":\"ID_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":12,\"type\":\"STRING\",\"name\":\"ID_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}],\"measures\":[{\"id\":13,\"type\":\"INTEGER\",\"name\":\"CMP_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},{\"id\":14,\"type\":\"STRING\",\"name\":\"ME_3\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"viral\":[]},\"messages\":[],\"adataset\":true,\"acomponent\":false,\"ascalar\":false}]";
		List<ResultExpression> result = vtlTestUtils.getResult(commandStatements);
		System.out.println("########"+vtlTestUtils.convertResultToJson(result));
		List<ResultExpression> resultExpressionsToComapare = vtlTestUtils.convertJsonToResultExpression(jsonResult);
		assertNotNull("Conversione non riuscita", resultExpressionsToComapare);
		assertNotNull("Validazione Semantica non riuscita", result);
		assertTrue("I due risultati non sono uguali",
				vtlTestUtils.compareResult(result.get(0), resultExpressionsToComapare.get(0)));
	}
	
	@Test
	@Transactional
	public void fullJoinUsingIdentifier() throws Exception {
		VtlDataset vtlDatasetFind = datasetRepository.findByNameIgnoreCase("DS_1");
		if (vtlDatasetFind == null) {
			VtlDataset vtlDataset = new VtlDataset();
			vtlDataset.setPersistent(true);
			vtlDataset.setName("DS_63");
			vtlDataset.setIsOnlyAScalar(false);
			vtlDataset.setTransitory(false);
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_1", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_2", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_1", VtlType.STRING, VtlComponentRole.MEASURE));
			datasetRepository.save(vtlDataset);

			VtlDataset vtlDatasetSecond = new VtlDataset();
			vtlDatasetSecond.setPersistent(true);
			vtlDatasetSecond.setName("DS_64");
			vtlDatasetSecond.setIsOnlyAScalar(false);
			vtlDatasetSecond.setTransitory(false);
			vtlDatasetSecond
					.addComponent(vtlTestUtils.getVtlComponent("ID_1", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDatasetSecond
					.addComponent(vtlTestUtils.getVtlComponent("ID_2", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDatasetSecond
					.addComponent(vtlTestUtils.getVtlComponent("ME_2", VtlType.STRING, VtlComponentRole.MEASURE));
			datasetRepository.save(vtlDatasetSecond);
						
		}
		String commandStatements = "DS_r := full_join (DS_63 as ds63, DS_64 as ds64);";
		String jsonResult = "[{\"result\":{\"id\":12,\"name\":\"DS_r\",\"description\":null,\"attributes\":[],\"persistent\":false,\"identifiers\":[{\"id\":21,\"type\":\"INTEGER\",\"name\":\"ID_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":22,\"type\":\"INTEGER\",\"name\":\"ID_2\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}],\"measures\":[{\"id\":23,\"type\":\"STRING\",\"name\":\"ME_1\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"MEASURE\"},{\"id\":24,\"type\":\"STRING\",\"name\":\"ME_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"viral\":[]},\"messages\":[],\"command\":\"DS_r:=full_join(DS_63asds63,DS_64asds64)\",\"acomponent\":false,\"ascalar\":false,\"adataset\":true},{\"result\":{\"id\":10,\"name\":\"temporary_9\",\"description\":null,\"attributes\":[],\"persistent\":false,\"identifiers\":[{\"id\":17,\"type\":\"INTEGER\",\"name\":\"ID_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":18,\"type\":\"INTEGER\",\"name\":\"ID_2\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}],\"measures\":[{\"id\":19,\"type\":\"STRING\",\"name\":\"ME_1\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"MEASURE\"},{\"id\":20,\"type\":\"STRING\",\"name\":\"ME_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"viral\":[]},\"messages\":[],\"command\":\"full_join(DS_63asds63,DS_64asds64)\",\"acomponent\":false,\"ascalar\":false,\"adataset\":true},{\"result\":{\"id\":8,\"name\":\"temporary_7\",\"description\":null,\"attributes\":[],\"persistent\":false,\"identifiers\":[{\"id\":13,\"type\":\"INTEGER\",\"name\":\"ID_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":14,\"type\":\"INTEGER\",\"name\":\"ID_2\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}],\"measures\":[{\"id\":15,\"type\":\"STRING\",\"name\":\"ME_1\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"MEASURE\"},{\"id\":16,\"type\":\"STRING\",\"name\":\"ME_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"viral\":[]},\"messages\":[],\"acomponent\":false,\"ascalar\":false,\"adataset\":true},{\"result\":{\"id\":6,\"name\":\"ds64\",\"description\":null,\"attributes\":[],\"persistent\":false,\"identifiers\":[{\"id\":10,\"type\":\"INTEGER\",\"name\":\"ID_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":11,\"type\":\"INTEGER\",\"name\":\"ID_2\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}],\"measures\":[{\"id\":12,\"type\":\"STRING\",\"name\":\"ME_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"viral\":[]},\"messages\":[],\"acomponent\":false,\"ascalar\":false,\"adataset\":true},{\"result\":{\"id\":4,\"name\":\"ds63\",\"description\":null,\"attributes\":[],\"persistent\":false,\"identifiers\":[{\"id\":7,\"type\":\"INTEGER\",\"name\":\"ID_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":8,\"type\":\"INTEGER\",\"name\":\"ID_2\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}],\"measures\":[{\"id\":9,\"type\":\"STRING\",\"name\":\"ME_1\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"viral\":[]},\"messages\":[],\"acomponent\":false,\"ascalar\":false,\"adataset\":true}]";
		List<ResultExpression> result = vtlTestUtils.getResult(commandStatements);
		System.out.println("########fullJoinUsingIdentifier: "+vtlTestUtils.convertResultToJson(result));
		List<ResultExpression> resultExpressionsToComapare = vtlTestUtils.convertJsonToResultExpression(jsonResult);
		assertNotNull("Conversione non riuscita", resultExpressionsToComapare);
		assertNotNull("Validazione Semantica non riuscita", result);
		assertTrue("I due risultati non sono uguali",
				vtlTestUtils.compareResult(result.get(0), resultExpressionsToComapare.get(0)));
	}

    /*	@Test
        @Transactional
        public void crossJoinUsingIdentifier() throws Exception {
            VtlDataset vtlDatasetFind = datasetRepository.findByNameIgnoreCase("DS_1");
            if (vtlDatasetFind == null) {
                VtlDataset vtlDataset = new VtlDataset();
                vtlDataset.setPersistent(true);
                vtlDataset.setName("DS_63");
                vtlDataset.setIsOnlyAScalar(false);
                vtlDataset.setTransitory(false);
                vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_1", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
                vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_2", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
                vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_2", VtlType.STRING, VtlComponentRole.MEASURE));
                datasetRepository.save(vtlDataset);

                VtlDataset vtlDatasetSecond = new VtlDataset();
                vtlDatasetSecond.setPersistent(true);
                vtlDatasetSecond.setName("DS_64");
                vtlDatasetSecond.setIsOnlyAScalar(false);
                vtlDatasetSecond.setTransitory(false);
                vtlDatasetSecond
                        .addComponent(vtlTestUtils.getVtlComponent("ID_1", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
                vtlDatasetSecond
                        .addComponent(vtlTestUtils.getVtlComponent("ID_2", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
                vtlDatasetSecond
                        .addComponent(vtlTestUtils.getVtlComponent("ID_3", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
                vtlDatasetSecond
                        .addComponent(vtlTestUtils.getVtlComponent("ME_3", VtlType.STRING, VtlComponentRole.MEASURE));
                datasetRepository.save(vtlDatasetSecond);

            }
            String commandStatements = "DS_r := cross_join (DS_63 as ds63, DS_64 as ds64);";
            String jsonResult = "[{\"result\":{\"id\":12,\"name\":\"DS_r\",\"description\":null,\"attributes\":[],\"persistent\":false,\"identifiers\":[],\"measures\":[],\"viral\":[]},\"messages\":[],\"command\":\"DS_r:=cross_join(DS_63asds63,DS_64asds64)\",\"adataset\":true,\"acomponent\":false,\"ascalar\":false},{\"result\":{\"id\":10,\"name\":\"temporary_9\",\"description\":null,\"attributes\":[],\"persistent\":false,\"identifiers\":[],\"measures\":[],\"viral\":[]},\"messages\":[],\"command\":\"cross_join(DS_63asds63,DS_64asds64)\",\"adataset\":true,\"acomponent\":false,\"ascalar\":false},{\"result\":{\"id\":8,\"name\":\"temporary_7\",\"description\":null,\"attributes\":[],\"persistent\":false,\"identifiers\":[{\"id\":15,\"type\":\"INTEGER\",\"name\":\"ds63#ID_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":16,\"type\":\"INTEGER\",\"name\":\"ds63#ID_2\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":17,\"type\":\"INTEGER\",\"name\":\"ds64#ID_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":18,\"type\":\"INTEGER\",\"name\":\"ds64#ID_2\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":19,\"type\":\"INTEGER\",\"name\":\"ID_3\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}],\"measures\":[{\"id\":20,\"type\":\"STRING\",\"name\":\"ME_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"MEASURE\"},{\"id\":21,\"type\":\"STRING\",\"name\":\"ME_3\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"viral\":[]},\"messages\":[],\"adataset\":true,\"acomponent\":false,\"ascalar\":false},{\"result\":{\"id\":6,\"name\":\"ds64\",\"description\":null,\"attributes\":[],\"persistent\":false,\"identifiers\":[{\"id\":11,\"type\":\"INTEGER\",\"name\":\"ID_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":12,\"type\":\"INTEGER\",\"name\":\"ID_2\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":13,\"type\":\"INTEGER\",\"name\":\"ID_3\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}],\"measures\":[{\"id\":14,\"type\":\"STRING\",\"name\":\"ME_3\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"viral\":[]},\"messages\":[],\"adataset\":true,\"acomponent\":false,\"ascalar\":false},{\"result\":{\"id\":4,\"name\":\"ds63\",\"description\":null,\"attributes\":[],\"persistent\":false,\"identifiers\":[{\"id\":8,\"type\":\"INTEGER\",\"name\":\"ID_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":9,\"type\":\"INTEGER\",\"name\":\"ID_2\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}],\"measures\":[{\"id\":10,\"type\":\"STRING\",\"name\":\"ME_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"viral\":[]},\"messages\":[],\"adataset\":true,\"acomponent\":false,\"ascalar\":false}]";
            List<ResultExpression> result = vtlTestUtils.getResult(commandStatements);
            System.out.println("########crossJoinUsingIdentifier: "+vtlTestUtils.convertResultToJson(result));
            List<ResultExpression> resultExpressionsToComapare = vtlTestUtils.convertJsonToResultExpression(jsonResult);
            assertNotNull("Conversione non riuscita", resultExpressionsToComapare);
            assertNotNull("Validazione Semantica non riuscita", result);
            assertTrue("I due risultati non sono uguali",
                    vtlTestUtils.compareResult(result.get(0), resultExpressionsToComapare.get(0)));
        }
        */
	@Test
	@Transactional
	public void innerJoinViral() throws Exception {
		VtlDataset vtlDatasetFind = datasetRepository.findByNameIgnoreCase("DS_1");
		if (vtlDatasetFind == null) {
			VtlDataset vtlDataset = new VtlDataset();
			vtlDataset.setPersistent(true);
			vtlDataset.setName("DS_63");
			vtlDataset.setIsOnlyAScalar(false);
			vtlDataset.setTransitory(false);
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_1", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("AT_1", VtlType.INTEGER, VtlComponentRole.ATTRIBUTE));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_1", VtlType.STRING, VtlComponentRole.MEASURE));
			datasetRepository.save(vtlDataset);

			VtlDataset vtlDatasetSecond = new VtlDataset();
			vtlDatasetSecond.setPersistent(true);
			vtlDatasetSecond.setName("DS_64");
			vtlDatasetSecond.setIsOnlyAScalar(false);
			vtlDatasetSecond.setTransitory(false);
			vtlDatasetSecond
					.addComponent(vtlTestUtils.getVtlComponent("ID_1", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDatasetSecond
					.addComponent(vtlTestUtils.getVtlComponent("ID_2", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDatasetSecond
					.addComponent(vtlTestUtils.getVtlComponent("AT_2", VtlType.INTEGER, VtlComponentRole.VIRAL));
			vtlDatasetSecond
					.addComponent(vtlTestUtils.getVtlComponent("ME_2", VtlType.STRING, VtlComponentRole.MEASURE));
			datasetRepository.save(vtlDatasetSecond);
			
			VtlDataset vtlDatasetThird = new VtlDataset();
			vtlDatasetThird.setPersistent(true);
			vtlDatasetThird.setName("DS_65");
			vtlDatasetThird.setIsOnlyAScalar(false);
			vtlDatasetThird.setTransitory(false);
			vtlDatasetThird
					.addComponent(vtlTestUtils.getVtlComponent("ID_1", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDatasetThird
					.addComponent(vtlTestUtils.getVtlComponent("ID_2", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDatasetThird
					.addComponent(vtlTestUtils.getVtlComponent("ID_3", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDatasetThird
					.addComponent(vtlTestUtils.getVtlComponent("ME_3", VtlType.STRING, VtlComponentRole.MEASURE));
	
			datasetRepository.save(vtlDatasetThird);
						
		}
		String commandStatements = "DS_r := inner_join (DS_63 as ds63, DS_64 as ds64, DS_65 as ds65);";
		String jsonResult = "[{\"result\":{\"id\":15,\"name\":\"DS_r\",\"description\":null,\"attributes\":[{\"id\":43,\"type\":\"INTEGER\",\"name\":\"AT_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"ATTRIBUTE\"}],\"persistent\":false,\"identifiers\":[{\"id\":39,\"type\":\"INTEGER\",\"name\":\"ID_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":40,\"type\":\"INTEGER\",\"name\":\"ID_2\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":41,\"type\":\"INTEGER\",\"name\":\"ID_3\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}],\"measures\":[{\"id\":42,\"type\":\"STRING\",\"name\":\"ME_1\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"MEASURE\"},{\"id\":44,\"type\":\"STRING\",\"name\":\"ME_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"MEASURE\"},{\"id\":46,\"type\":\"STRING\",\"name\":\"ME_3\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"viral\":[{\"id\":45,\"type\":\"INTEGER\",\"name\":\"AT_2\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"VIRAL\"}]},\"messages\":[],\"command\":\"DS_r:=inner_join(DS_63asds63,DS_64asds64,DS_65asds65)\",\"adataset\":true,\"acomponent\":false,\"ascalar\":false},{\"result\":{\"id\":13,\"name\":\"temporary_12\",\"description\":null,\"attributes\":[{\"id\":35,\"type\":\"INTEGER\",\"name\":\"AT_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"ATTRIBUTE\"}],\"persistent\":false,\"identifiers\":[{\"id\":31,\"type\":\"INTEGER\",\"name\":\"ID_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":32,\"type\":\"INTEGER\",\"name\":\"ID_2\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":33,\"type\":\"INTEGER\",\"name\":\"ID_3\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}],\"measures\":[{\"id\":34,\"type\":\"STRING\",\"name\":\"ME_1\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"MEASURE\"},{\"id\":36,\"type\":\"STRING\",\"name\":\"ME_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"MEASURE\"},{\"id\":38,\"type\":\"STRING\",\"name\":\"ME_3\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"viral\":[{\"id\":37,\"type\":\"INTEGER\",\"name\":\"AT_2\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"VIRAL\"}]},\"messages\":[],\"command\":\"inner_join(DS_63asds63,DS_64asds64,DS_65asds65)\",\"adataset\":true,\"acomponent\":false,\"ascalar\":false},{\"result\":{\"id\":11,\"name\":\"temporary_10\",\"description\":null,\"attributes\":[{\"id\":27,\"type\":\"INTEGER\",\"name\":\"AT_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"ATTRIBUTE\"}],\"persistent\":false,\"identifiers\":[{\"id\":23,\"type\":\"INTEGER\",\"name\":\"ID_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":24,\"type\":\"INTEGER\",\"name\":\"ID_2\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":25,\"type\":\"INTEGER\",\"name\":\"ID_3\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}],\"measures\":[{\"id\":26,\"type\":\"STRING\",\"name\":\"ME_1\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"MEASURE\"},{\"id\":28,\"type\":\"STRING\",\"name\":\"ME_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"MEASURE\"},{\"id\":30,\"type\":\"STRING\",\"name\":\"ME_3\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"viral\":[{\"id\":29,\"type\":\"INTEGER\",\"name\":\"AT_2\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"VIRAL\"}]},\"messages\":[],\"adataset\":true,\"acomponent\":false,\"ascalar\":false},{\"result\":{\"id\":9,\"name\":\"ds65\",\"description\":null,\"attributes\":[],\"persistent\":false,\"identifiers\":[{\"id\":19,\"type\":\"INTEGER\",\"name\":\"ID_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":20,\"type\":\"INTEGER\",\"name\":\"ID_2\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":21,\"type\":\"INTEGER\",\"name\":\"ID_3\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}],\"measures\":[{\"id\":22,\"type\":\"STRING\",\"name\":\"ME_3\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"viral\":[]},\"messages\":[],\"adataset\":true,\"acomponent\":false,\"ascalar\":false},{\"result\":{\"id\":7,\"name\":\"ds64\",\"description\":null,\"attributes\":[],\"persistent\":false,\"identifiers\":[{\"id\":15,\"type\":\"INTEGER\",\"name\":\"ID_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":16,\"type\":\"INTEGER\",\"name\":\"ID_2\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}],\"measures\":[{\"id\":18,\"type\":\"STRING\",\"name\":\"ME_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"viral\":[{\"id\":17,\"type\":\"INTEGER\",\"name\":\"AT_2\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"VIRAL\"}]},\"messages\":[],\"adataset\":true,\"acomponent\":false,\"ascalar\":false},{\"result\":{\"id\":5,\"name\":\"ds63\",\"description\":null,\"attributes\":[{\"id\":13,\"type\":\"INTEGER\",\"name\":\"AT_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"ATTRIBUTE\"}],\"persistent\":false,\"identifiers\":[{\"id\":12,\"type\":\"INTEGER\",\"name\":\"ID_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}],\"measures\":[{\"id\":14,\"type\":\"STRING\",\"name\":\"ME_1\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"viral\":[]},\"messages\":[],\"adataset\":true,\"acomponent\":false,\"ascalar\":false}]";
		List<ResultExpression> result = vtlTestUtils.getResult(commandStatements);
		System.out.println("########innerJoinViral: "+vtlTestUtils.convertResultToJson(result));
		List<ResultExpression> resultExpressionsToComapare = vtlTestUtils.convertJsonToResultExpression(jsonResult);
		assertNotNull("Conversione non riuscita", resultExpressionsToComapare);
		assertNotNull("Validazione Semantica non riuscita", result);
		assertTrue("I due risultati non sono uguali",
				vtlTestUtils.compareResult(result.get(0), resultExpressionsToComapare.get(0)));
	}
	
	//TODO DOVREBBE FALLIRE ORA TORNA DS_R VUOTO
	/*
	@Test
	@Transactional
	public void innerJoinAttributeFail() throws Exception {
		VtlDataset vtlDatasetFind = datasetRepository.findByNameIgnoreCase("DS_1");
		if (vtlDatasetFind == null) {
			VtlDataset vtlDataset = new VtlDataset();
			vtlDataset.setPersistent(true);
			vtlDataset.setName("DS_63");
			vtlDataset.setIsOnlyAScalar(false);
			vtlDataset.setTransitory(false);
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_1", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("CMP_1", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_1", VtlType.STRING, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("AT_1", VtlType.STRING, VtlComponentRole.ATTRIBUTE));
			datasetRepository.save(vtlDataset);

			VtlDataset vtlDatasetSecond = new VtlDataset();
			vtlDatasetSecond.setPersistent(true);
			vtlDatasetSecond.setName("DS_64");
			vtlDatasetSecond.setIsOnlyAScalar(false);
			vtlDatasetSecond.setTransitory(false);
			vtlDatasetSecond
					.addComponent(vtlTestUtils.getVtlComponent("ID_1", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDatasetSecond
					.addComponent(vtlTestUtils.getVtlComponent("CMP_1", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDatasetSecond
					.addComponent(vtlTestUtils.getVtlComponent("ME_1", VtlType.STRING, VtlComponentRole.MEASURE));
			vtlDatasetSecond
					.addComponent(vtlTestUtils.getVtlComponent("AT_1", VtlType.STRING, VtlComponentRole.ATTRIBUTE));
			datasetRepository.save(vtlDatasetSecond);
			
			VtlDataset vtlDatasetThird = new VtlDataset();
			vtlDatasetThird.setPersistent(true);
			vtlDatasetThird.setName("DS_65");
			vtlDatasetThird.setIsOnlyAScalar(false);
			vtlDatasetThird.setTransitory(false);
			vtlDatasetThird
					.addComponent(vtlTestUtils.getVtlComponent("ID_1", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDatasetThird
					.addComponent(vtlTestUtils.getVtlComponent("ID_2", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDatasetThird
					.addComponent(vtlTestUtils.getVtlComponent("CMP_1", VtlType.INTEGER, VtlComponentRole.MEASURE));
			vtlDatasetThird
					.addComponent(vtlTestUtils.getVtlComponent("ME_1", VtlType.STRING, VtlComponentRole.MEASURE));
			vtlDatasetThird
					.addComponent(vtlTestUtils.getVtlComponent("AT_2", VtlType.STRING, VtlComponentRole.VIRAL));
			
			datasetRepository.save(vtlDatasetThird);
						
		}
		String commandStatements = "DS_r := inner_join (DS_63 as ds63, DS_64 as ds64, DS_65 as ds65 using ID_1, CMP_1);";
		String jsonResult = "";
		List<ResultExpression> result = vtlTestUtils.getResult(commandStatements);
		System.out.println("########innerJoinAttribute: "+vtlTestUtils.convertResultToJson(result));
		List<ResultExpression> resultExpressionsToComapare = vtlTestUtils.convertJsonToResultExpression(jsonResult);
		assertNotNull("Conversione non riuscita", resultExpressionsToComapare);
		assertNotNull("Validazione Semantica non riuscita", result);
		assertTrue("I due risultati non sono uguali",
				vtlTestUtils.compareResult(result.get(0), resultExpressionsToComapare.get(0)));
	}
	*/
	@Test
	@Transactional
	public void innerJoinFilter2() throws Exception {
		VtlDataset vtlDatasetFind = datasetRepository.findByNameIgnoreCase("DS_1");
		if (vtlDatasetFind == null) {
			VtlDataset vtlDataset = new VtlDataset();
			vtlDataset.setPersistent(true);
			vtlDataset.setName("DS_63");
			vtlDataset.setIsOnlyAScalar(false);
			vtlDataset.setTransitory(false);
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_1", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_1", VtlType.STRING, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("AT_1", VtlType.STRING, VtlComponentRole.ATTRIBUTE));
			datasetRepository.save(vtlDataset);

			VtlDataset vtlDatasetSecond = new VtlDataset();
			vtlDatasetSecond.setPersistent(true);
			vtlDatasetSecond.setName("DS_64");
			vtlDatasetSecond.setIsOnlyAScalar(false);
			vtlDatasetSecond.setTransitory(false);
			vtlDatasetSecond
					.addComponent(vtlTestUtils.getVtlComponent("ID_1", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDatasetSecond
					.addComponent(vtlTestUtils.getVtlComponent("ID_2", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDatasetSecond
					.addComponent(vtlTestUtils.getVtlComponent("ME_2", VtlType.STRING, VtlComponentRole.MEASURE));
			vtlDatasetSecond
					.addComponent(vtlTestUtils.getVtlComponent("AT_2", VtlType.STRING, VtlComponentRole.VIRAL));
			datasetRepository.save(vtlDatasetSecond);
			
			VtlDataset vtlDatasetThird = new VtlDataset();
			vtlDatasetThird.setPersistent(true);
			vtlDatasetThird.setName("DS_65");
			vtlDatasetThird.setIsOnlyAScalar(false);
			vtlDatasetThird.setTransitory(false);
			vtlDatasetThird
					.addComponent(vtlTestUtils.getVtlComponent("ID_1", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDatasetThird
					.addComponent(vtlTestUtils.getVtlComponent("ID_2", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDatasetThird
					.addComponent(vtlTestUtils.getVtlComponent("ID_3", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDatasetThird
					.addComponent(vtlTestUtils.getVtlComponent("ME_3", VtlType.STRING, VtlComponentRole.MEASURE));
						
			datasetRepository.save(vtlDatasetThird);
						
		}
		String commandStatements = "DS_r := inner_join (DS_63 as ds63, DS_64 as ds64, DS_65 as ds65 filter ME_1>0 and ME_3=ME_2);";
		String jsonResult = "[{\"result\":{\"id\":19,\"name\":\"DS_r\",\"description\":null,\"attributes\":[{\"id\":52,\"type\":\"STRING\",\"name\":\"AT_1\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"ATTRIBUTE\"}],\"persistent\":false,\"identifiers\":[{\"id\":48,\"type\":\"INTEGER\",\"name\":\"ID_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":49,\"type\":\"INTEGER\",\"name\":\"ID_2\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":50,\"type\":\"INTEGER\",\"name\":\"ID_3\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}],\"measures\":[{\"id\":51,\"type\":\"STRING\",\"name\":\"ME_1\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"MEASURE\"},{\"id\":53,\"type\":\"STRING\",\"name\":\"ME_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"MEASURE\"},{\"id\":55,\"type\":\"STRING\",\"name\":\"ME_3\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"viral\":[{\"id\":54,\"type\":\"STRING\",\"name\":\"AT_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"VIRAL\"}]},\"messages\":[],\"command\":\"DS_r:=inner_join(DS_63asds63,DS_64asds64,DS_65asds65filterME_1>0andME_3=ME_2)\",\"adataset\":true,\"acomponent\":false,\"ascalar\":false},{\"result\":{\"id\":17,\"name\":\"temporary_16\",\"description\":null,\"attributes\":[{\"id\":44,\"type\":\"STRING\",\"name\":\"AT_1\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"ATTRIBUTE\"}],\"persistent\":false,\"identifiers\":[{\"id\":40,\"type\":\"INTEGER\",\"name\":\"ID_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":41,\"type\":\"INTEGER\",\"name\":\"ID_2\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":42,\"type\":\"INTEGER\",\"name\":\"ID_3\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}],\"measures\":[{\"id\":43,\"type\":\"STRING\",\"name\":\"ME_1\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"MEASURE\"},{\"id\":45,\"type\":\"STRING\",\"name\":\"ME_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"MEASURE\"},{\"id\":47,\"type\":\"STRING\",\"name\":\"ME_3\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"viral\":[{\"id\":46,\"type\":\"STRING\",\"name\":\"AT_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"VIRAL\"}]},\"messages\":[],\"command\":\"inner_join(DS_63asds63,DS_64asds64,DS_65asds65filterME_1>0andME_3=ME_2)\",\"adataset\":true,\"acomponent\":false,\"ascalar\":false},{\"result\":{\"id\":15,\"name\":\"temporary_14\",\"description\":null,\"attributes\":[{\"id\":36,\"type\":\"STRING\",\"name\":\"AT_1\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"ATTRIBUTE\"}],\"persistent\":false,\"identifiers\":[{\"id\":32,\"type\":\"INTEGER\",\"name\":\"ID_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":33,\"type\":\"INTEGER\",\"name\":\"ID_2\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":34,\"type\":\"INTEGER\",\"name\":\"ID_3\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}],\"measures\":[{\"id\":35,\"type\":\"STRING\",\"name\":\"ME_1\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"MEASURE\"},{\"id\":37,\"type\":\"STRING\",\"name\":\"ME_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"MEASURE\"},{\"id\":39,\"type\":\"STRING\",\"name\":\"ME_3\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"viral\":[{\"id\":38,\"type\":\"STRING\",\"name\":\"AT_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"VIRAL\"}]},\"messages\":[],\"command\":\"filterME_1>0andME_3=ME_2\",\"adataset\":true,\"acomponent\":false,\"ascalar\":false},{\"resultComponent\":{\"id\":null,\"type\":\"BOOLEAN\",\"name\":\"bool_var\",\"domainValue\":\"bool_vd\",\"domainValueParent\":\"bool_vd\",\"vtlComponentRole\":\"MEASURE\"},\"messages\":[],\"command\":\"ME_1>0andME_3=ME_2\",\"adataset\":false,\"acomponent\":true,\"ascalar\":false},{\"resultComponent\":{\"id\":null,\"type\":\"BOOLEAN\",\"name\":\"bool_var\",\"domainValue\":\"bool_vd\",\"domainValueParent\":\"bool_vd\",\"vtlComponentRole\":\"MEASURE\"},\"messages\":[],\"command\":\"ME_1>0\",\"adataset\":false,\"acomponent\":true,\"ascalar\":false},{\"resultComponent\":{\"id\":26,\"type\":\"STRING\",\"name\":\"ME_1\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"MEASURE\"},\"messages\":[],\"command\":\"ME_1\",\"adataset\":false,\"acomponent\":true,\"ascalar\":false},{\"result\":{\"id\":13,\"name\":\"temporary_12\",\"description\":null,\"attributes\":[],\"persistent\":false,\"identifiers\":[],\"measures\":[{\"id\":31,\"type\":\"INTEGER\",\"name\":\"int_var\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"viral\":[]},\"resultComponent\":{\"id\":31,\"type\":\"INTEGER\",\"name\":\"int_var\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},\"messages\":[],\"command\":\"0\",\"adataset\":false,\"scalarComponent\":{\"id\":31,\"type\":\"INTEGER\",\"name\":\"int_var\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},\"acomponent\":false,\"ascalar\":true},{\"resultComponent\":{\"id\":null,\"type\":\"BOOLEAN\",\"name\":\"bool_var\",\"domainValue\":\"bool_vd\",\"domainValueParent\":\"bool_vd\",\"vtlComponentRole\":\"MEASURE\"},\"messages\":[],\"command\":\"ME_3=ME_2\",\"adataset\":false,\"acomponent\":true,\"ascalar\":false},{\"resultComponent\":{\"id\":30,\"type\":\"STRING\",\"name\":\"ME_3\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"MEASURE\"},\"messages\":[],\"command\":\"ME_3\",\"adataset\":false,\"acomponent\":true,\"ascalar\":false},{\"resultComponent\":{\"id\":28,\"type\":\"STRING\",\"name\":\"ME_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"MEASURE\"},\"messages\":[],\"command\":\"ME_2\",\"adataset\":false,\"acomponent\":true,\"ascalar\":false},{\"result\":{\"id\":11,\"name\":\"temporary_10\",\"description\":null,\"attributes\":[{\"id\":27,\"type\":\"STRING\",\"name\":\"AT_1\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"ATTRIBUTE\"}],\"persistent\":false,\"identifiers\":[{\"id\":23,\"type\":\"INTEGER\",\"name\":\"ID_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":24,\"type\":\"INTEGER\",\"name\":\"ID_2\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":25,\"type\":\"INTEGER\",\"name\":\"ID_3\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}],\"measures\":[{\"id\":26,\"type\":\"STRING\",\"name\":\"ME_1\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"MEASURE\"},{\"id\":28,\"type\":\"STRING\",\"name\":\"ME_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"MEASURE\"},{\"id\":30,\"type\":\"STRING\",\"name\":\"ME_3\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"viral\":[{\"id\":29,\"type\":\"STRING\",\"name\":\"AT_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"VIRAL\"}]},\"messages\":[],\"adataset\":true,\"acomponent\":false,\"ascalar\":false},{\"result\":{\"id\":9,\"name\":\"ds65\",\"description\":null,\"attributes\":[],\"persistent\":false,\"identifiers\":[{\"id\":19,\"type\":\"INTEGER\",\"name\":\"ID_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":20,\"type\":\"INTEGER\",\"name\":\"ID_2\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":21,\"type\":\"INTEGER\",\"name\":\"ID_3\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}],\"measures\":[{\"id\":22,\"type\":\"STRING\",\"name\":\"ME_3\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"viral\":[]},\"messages\":[],\"adataset\":true,\"acomponent\":false,\"ascalar\":false},{\"result\":{\"id\":7,\"name\":\"ds64\",\"description\":null,\"attributes\":[],\"persistent\":false,\"identifiers\":[{\"id\":15,\"type\":\"INTEGER\",\"name\":\"ID_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":16,\"type\":\"INTEGER\",\"name\":\"ID_2\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}],\"measures\":[{\"id\":17,\"type\":\"STRING\",\"name\":\"ME_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"viral\":[{\"id\":18,\"type\":\"STRING\",\"name\":\"AT_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"VIRAL\"}]},\"messages\":[],\"adataset\":true,\"acomponent\":false,\"ascalar\":false},{\"result\":{\"id\":5,\"name\":\"ds63\",\"description\":null,\"attributes\":[{\"id\":14,\"type\":\"STRING\",\"name\":\"AT_1\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"ATTRIBUTE\"}],\"persistent\":false,\"identifiers\":[{\"id\":12,\"type\":\"INTEGER\",\"name\":\"ID_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}],\"measures\":[{\"id\":13,\"type\":\"STRING\",\"name\":\"ME_1\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"viral\":[]},\"messages\":[],\"adataset\":true,\"acomponent\":false,\"ascalar\":false}]";
		List<ResultExpression> result = vtlTestUtils.getResult(commandStatements);
		System.out.println("########innerJoinFilter2: "+vtlTestUtils.convertResultToJson(result));
		List<ResultExpression> resultExpressionsToComapare = vtlTestUtils.convertJsonToResultExpression(jsonResult);
		assertNotNull("Conversione non riuscita", resultExpressionsToComapare);
		assertNotNull("Validazione Semantica non riuscita", result);
		assertTrue("I due risultati non sono uguali",
				vtlTestUtils.compareResult(result.get(0), resultExpressionsToComapare.get(0)));
	}
	
	@Test
	@Transactional
	public void innerJoinFilter3() throws Exception {
		VtlDataset vtlDatasetFind = datasetRepository.findByNameIgnoreCase("DS_1");
		if (vtlDatasetFind == null) {
			VtlDataset vtlDataset = new VtlDataset();
			vtlDataset.setPersistent(true);
			vtlDataset.setName("DS_63");
			vtlDataset.setIsOnlyAScalar(false);
			vtlDataset.setTransitory(false);
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_1", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_1", VtlType.STRING, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("AT_1", VtlType.STRING, VtlComponentRole.ATTRIBUTE));
			datasetRepository.save(vtlDataset);

			VtlDataset vtlDatasetSecond = new VtlDataset();
			vtlDatasetSecond.setPersistent(true);
			vtlDatasetSecond.setName("DS_64");
			vtlDatasetSecond.setIsOnlyAScalar(false);
			vtlDatasetSecond.setTransitory(false);
			vtlDatasetSecond
					.addComponent(vtlTestUtils.getVtlComponent("ID_1", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDatasetSecond
					.addComponent(vtlTestUtils.getVtlComponent("ID_2", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDatasetSecond
					.addComponent(vtlTestUtils.getVtlComponent("ME_2", VtlType.STRING, VtlComponentRole.MEASURE));
			vtlDatasetSecond
					.addComponent(vtlTestUtils.getVtlComponent("AT_2", VtlType.STRING, VtlComponentRole.VIRAL));
			datasetRepository.save(vtlDatasetSecond);
			
			VtlDataset vtlDatasetThird = new VtlDataset();
			vtlDatasetThird.setPersistent(true);
			vtlDatasetThird.setName("DS_65");
			vtlDatasetThird.setIsOnlyAScalar(false);
			vtlDatasetThird.setTransitory(false);
			vtlDatasetThird
					.addComponent(vtlTestUtils.getVtlComponent("ID_1", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDatasetThird
					.addComponent(vtlTestUtils.getVtlComponent("ID_2", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDatasetThird
					.addComponent(vtlTestUtils.getVtlComponent("ID_3", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDatasetThird
					.addComponent(vtlTestUtils.getVtlComponent("ME_3", VtlType.STRING, VtlComponentRole.MEASURE));
						
			datasetRepository.save(vtlDatasetThird);
						
		}
		String commandStatements = "DS_r := inner_join (DS_63 as ds63, DS_64 as ds64, DS_65 as ds65 filter AT_1>0 and ME_3=ME_2);";
		String jsonResult = "[{\"result\":{\"id\":19,\"name\":\"DS_r\",\"description\":null,\"attributes\":[{\"id\":52,\"type\":\"STRING\",\"name\":\"AT_1\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"ATTRIBUTE\"}],\"persistent\":false,\"identifiers\":[{\"id\":48,\"type\":\"INTEGER\",\"name\":\"ID_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":49,\"type\":\"INTEGER\",\"name\":\"ID_2\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":50,\"type\":\"INTEGER\",\"name\":\"ID_3\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}],\"measures\":[{\"id\":51,\"type\":\"STRING\",\"name\":\"ME_1\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"MEASURE\"},{\"id\":53,\"type\":\"STRING\",\"name\":\"ME_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"MEASURE\"},{\"id\":55,\"type\":\"STRING\",\"name\":\"ME_3\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"viral\":[{\"id\":54,\"type\":\"STRING\",\"name\":\"AT_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"VIRAL\"}]},\"messages\":[],\"command\":\"DS_r:=inner_join(DS_63asds63,DS_64asds64,DS_65asds65filterAT_1>0andME_3=ME_2)\",\"acomponent\":false,\"ascalar\":false,\"adataset\":true},{\"result\":{\"id\":17,\"name\":\"temporary_16\",\"description\":null,\"attributes\":[{\"id\":44,\"type\":\"STRING\",\"name\":\"AT_1\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"ATTRIBUTE\"}],\"persistent\":false,\"identifiers\":[{\"id\":40,\"type\":\"INTEGER\",\"name\":\"ID_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":41,\"type\":\"INTEGER\",\"name\":\"ID_2\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":42,\"type\":\"INTEGER\",\"name\":\"ID_3\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}],\"measures\":[{\"id\":43,\"type\":\"STRING\",\"name\":\"ME_1\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"MEASURE\"},{\"id\":45,\"type\":\"STRING\",\"name\":\"ME_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"MEASURE\"},{\"id\":47,\"type\":\"STRING\",\"name\":\"ME_3\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"viral\":[{\"id\":46,\"type\":\"STRING\",\"name\":\"AT_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"VIRAL\"}]},\"messages\":[],\"command\":\"inner_join(DS_63asds63,DS_64asds64,DS_65asds65filterAT_1>0andME_3=ME_2)\",\"acomponent\":false,\"ascalar\":false,\"adataset\":true},{\"result\":{\"id\":15,\"name\":\"temporary_14\",\"description\":null,\"attributes\":[{\"id\":36,\"type\":\"STRING\",\"name\":\"AT_1\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"ATTRIBUTE\"}],\"persistent\":false,\"identifiers\":[{\"id\":32,\"type\":\"INTEGER\",\"name\":\"ID_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":33,\"type\":\"INTEGER\",\"name\":\"ID_2\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":34,\"type\":\"INTEGER\",\"name\":\"ID_3\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}],\"measures\":[{\"id\":35,\"type\":\"STRING\",\"name\":\"ME_1\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"MEASURE\"},{\"id\":37,\"type\":\"STRING\",\"name\":\"ME_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"MEASURE\"},{\"id\":39,\"type\":\"STRING\",\"name\":\"ME_3\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"viral\":[{\"id\":38,\"type\":\"STRING\",\"name\":\"AT_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"VIRAL\"}]},\"messages\":[],\"command\":\"filterAT_1>0andME_3=ME_2\",\"acomponent\":false,\"ascalar\":false,\"adataset\":true},{\"resultComponent\":{\"id\":null,\"type\":\"BOOLEAN\",\"name\":\"bool_var\",\"domainValue\":\"bool_vd\",\"domainValueParent\":\"bool_vd\",\"vtlComponentRole\":\"MEASURE\"},\"messages\":[],\"command\":\"AT_1>0andME_3=ME_2\",\"acomponent\":true,\"ascalar\":false,\"adataset\":false},{\"resultComponent\":{\"id\":null,\"type\":\"BOOLEAN\",\"name\":\"bool_var\",\"domainValue\":\"bool_vd\",\"domainValueParent\":\"bool_vd\",\"vtlComponentRole\":\"MEASURE\"},\"messages\":[],\"command\":\"AT_1>0\",\"acomponent\":true,\"ascalar\":false,\"adataset\":false},{\"resultComponent\":{\"id\":27,\"type\":\"STRING\",\"name\":\"AT_1\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"ATTRIBUTE\"},\"messages\":[],\"command\":\"AT_1\",\"acomponent\":true,\"ascalar\":false,\"adataset\":false},{\"result\":{\"id\":13,\"name\":\"temporary_12\",\"description\":null,\"attributes\":[],\"persistent\":false,\"identifiers\":[],\"measures\":[{\"id\":31,\"type\":\"INTEGER\",\"name\":\"int_var\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"viral\":[]},\"resultComponent\":{\"id\":31,\"type\":\"INTEGER\",\"name\":\"int_var\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},\"messages\":[],\"command\":\"0\",\"acomponent\":false,\"ascalar\":true,\"adataset\":false,\"scalarComponent\":{\"id\":31,\"type\":\"INTEGER\",\"name\":\"int_var\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"}},{\"resultComponent\":{\"id\":null,\"type\":\"BOOLEAN\",\"name\":\"bool_var\",\"domainValue\":\"bool_vd\",\"domainValueParent\":\"bool_vd\",\"vtlComponentRole\":\"MEASURE\"},\"messages\":[],\"command\":\"ME_3=ME_2\",\"acomponent\":true,\"ascalar\":false,\"adataset\":false},{\"resultComponent\":{\"id\":30,\"type\":\"STRING\",\"name\":\"ME_3\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"MEASURE\"},\"messages\":[],\"command\":\"ME_3\",\"acomponent\":true,\"ascalar\":false,\"adataset\":false},{\"resultComponent\":{\"id\":28,\"type\":\"STRING\",\"name\":\"ME_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"MEASURE\"},\"messages\":[],\"command\":\"ME_2\",\"acomponent\":true,\"ascalar\":false,\"adataset\":false},{\"result\":{\"id\":11,\"name\":\"temporary_10\",\"description\":null,\"attributes\":[{\"id\":27,\"type\":\"STRING\",\"name\":\"AT_1\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"ATTRIBUTE\"}],\"persistent\":false,\"identifiers\":[{\"id\":23,\"type\":\"INTEGER\",\"name\":\"ID_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":24,\"type\":\"INTEGER\",\"name\":\"ID_2\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":25,\"type\":\"INTEGER\",\"name\":\"ID_3\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}],\"measures\":[{\"id\":26,\"type\":\"STRING\",\"name\":\"ME_1\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"MEASURE\"},{\"id\":28,\"type\":\"STRING\",\"name\":\"ME_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"MEASURE\"},{\"id\":30,\"type\":\"STRING\",\"name\":\"ME_3\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"viral\":[{\"id\":29,\"type\":\"STRING\",\"name\":\"AT_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"VIRAL\"}]},\"messages\":[],\"acomponent\":false,\"ascalar\":false,\"adataset\":true},{\"result\":{\"id\":9,\"name\":\"ds65\",\"description\":null,\"attributes\":[],\"persistent\":false,\"identifiers\":[{\"id\":19,\"type\":\"INTEGER\",\"name\":\"ID_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":20,\"type\":\"INTEGER\",\"name\":\"ID_2\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":21,\"type\":\"INTEGER\",\"name\":\"ID_3\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}],\"measures\":[{\"id\":22,\"type\":\"STRING\",\"name\":\"ME_3\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"viral\":[]},\"messages\":[],\"acomponent\":false,\"ascalar\":false,\"adataset\":true},{\"result\":{\"id\":7,\"name\":\"ds64\",\"description\":null,\"attributes\":[],\"persistent\":false,\"identifiers\":[{\"id\":15,\"type\":\"INTEGER\",\"name\":\"ID_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":16,\"type\":\"INTEGER\",\"name\":\"ID_2\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}],\"measures\":[{\"id\":17,\"type\":\"STRING\",\"name\":\"ME_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"viral\":[{\"id\":18,\"type\":\"STRING\",\"name\":\"AT_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"VIRAL\"}]},\"messages\":[],\"acomponent\":false,\"ascalar\":false,\"adataset\":true},{\"result\":{\"id\":5,\"name\":\"ds63\",\"description\":null,\"attributes\":[{\"id\":14,\"type\":\"STRING\",\"name\":\"AT_1\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"ATTRIBUTE\"}],\"persistent\":false,\"identifiers\":[{\"id\":12,\"type\":\"INTEGER\",\"name\":\"ID_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}],\"measures\":[{\"id\":13,\"type\":\"STRING\",\"name\":\"ME_1\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"viral\":[]},\"messages\":[],\"acomponent\":false,\"ascalar\":false,\"adataset\":true}]";
		List<ResultExpression> result = vtlTestUtils.getResult(commandStatements);
		System.out.println("########innerJoinFilter3: "+vtlTestUtils.convertResultToJson(result));
		List<ResultExpression> resultExpressionsToComapare = vtlTestUtils.convertJsonToResultExpression(jsonResult);
		assertNotNull("Conversione non riuscita", resultExpressionsToComapare);
		assertNotNull("Validazione Semantica non riuscita", result);
		assertTrue("I due risultati non sono uguali",
				vtlTestUtils.compareResult(result.get(0), resultExpressionsToComapare.get(0)));
	}
	
/*	@Test
	@Transactional
	public void innerJoinFilter4() throws Exception {
		VtlDataset vtlDatasetFind = datasetRepository.findByNameIgnoreCase("DS_1");
		if (vtlDatasetFind == null) {
			VtlDataset vtlDataset = new VtlDataset();
			vtlDataset.setPersistent(true);
			vtlDataset.setName("DS_63");
			vtlDataset.setIsOnlyAScalar(false);
			vtlDataset.setTransitory(false);
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_1", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("CMP_1", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_1", VtlType.STRING, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("AT_1", VtlType.STRING, VtlComponentRole.ATTRIBUTE));
			datasetRepository.save(vtlDataset);

			VtlDataset vtlDatasetSecond = new VtlDataset();
			vtlDatasetSecond.setPersistent(true);
			vtlDatasetSecond.setName("DS_64");
			vtlDatasetSecond.setIsOnlyAScalar(false);
			vtlDatasetSecond.setTransitory(false);
			vtlDatasetSecond
					.addComponent(vtlTestUtils.getVtlComponent("ID_1", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDatasetSecond
					.addComponent(vtlTestUtils.getVtlComponent("CMP_1", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDatasetSecond
					.addComponent(vtlTestUtils.getVtlComponent("ME_1", VtlType.STRING, VtlComponentRole.MEASURE));
			vtlDatasetSecond
					.addComponent(vtlTestUtils.getVtlComponent("AT_1", VtlType.STRING, VtlComponentRole.ATTRIBUTE));
			datasetRepository.save(vtlDatasetSecond);
			
			VtlDataset vtlDatasetThird = new VtlDataset();
			vtlDatasetThird.setPersistent(true);
			vtlDatasetThird.setName("DS_65");
			vtlDatasetThird.setIsOnlyAScalar(false);
			vtlDatasetThird.setTransitory(false);
			vtlDatasetThird
					.addComponent(vtlTestUtils.getVtlComponent("ID_1", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDatasetThird
					.addComponent(vtlTestUtils.getVtlComponent("ID_2", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDatasetThird
					.addComponent(vtlTestUtils.getVtlComponent("CMP_1", VtlType.INTEGER, VtlComponentRole.MEASURE));
			vtlDatasetThird
					.addComponent(vtlTestUtils.getVtlComponent("ME_1", VtlType.STRING, VtlComponentRole.MEASURE));
			vtlDatasetThird
					.addComponent(vtlTestUtils.getVtlComponent("AT_2", VtlType.STRING, VtlComponentRole.VIRAL));			
			datasetRepository.save(vtlDatasetThird);
						
		}
		String commandStatements = "DS_r := inner_join (DS_63 as ds63, DS_64 as ds64, DS_65 as ds65 using ID_1, CMP_1);";
		String jsonResult = "[{\"result\":{\"id\":15,\"name\":\"DS_r\",\"description\":null,\"attributes\":[],\"persistent\":false,\"identifiers\":[],\"measures\":[],\"viral\":[]},\"messages\":[],\"command\":\"DS_r:=inner_join(DS_63asds63,DS_64asds64,DS_65asds65usingID_1,CMP_1)\",\"adataset\":true,\"ascalar\":false,\"acomponent\":false},{\"result\":{\"id\":13,\"name\":\"temporary_12\",\"description\":null,\"attributes\":[],\"persistent\":false,\"identifiers\":[],\"measures\":[],\"viral\":[]},\"messages\":[],\"command\":\"inner_join(DS_63asds63,DS_64asds64,DS_65asds65usingID_1,CMP_1)\",\"adataset\":true,\"ascalar\":false,\"acomponent\":false},{\"result\":{\"id\":11,\"name\":\"temporary_10\",\"description\":null,\"attributes\":[{\"id\":30,\"type\":\"STRING\",\"name\":\"ds63#AT_1\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"ATTRIBUTE\"},{\"id\":32,\"type\":\"STRING\",\"name\":\"ds64#AT_1\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"ATTRIBUTE\"}],\"persistent\":false,\"identifiers\":[{\"id\":27,\"type\":\"INTEGER\",\"name\":\"ID_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":28,\"type\":\"INTEGER\",\"name\":\"ID_2\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}],\"measures\":[{\"id\":29,\"type\":\"STRING\",\"name\":\"ds63#ME_1\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"MEASURE\"},{\"id\":31,\"type\":\"STRING\",\"name\":\"ds64#ME_1\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"MEASURE\"},{\"id\":33,\"type\":\"INTEGER\",\"name\":\"CMP_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},{\"id\":34,\"type\":\"STRING\",\"name\":\"ds65#ME_1\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"viral\":[{\"id\":35,\"type\":\"STRING\",\"name\":\"AT_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"VIRAL\"}]},\"messages\":[],\"adataset\":true,\"ascalar\":false,\"acomponent\":false},{\"result\":{\"id\":9,\"name\":\"ds65\",\"description\":null,\"attributes\":[],\"persistent\":false,\"identifiers\":[{\"id\":22,\"type\":\"INTEGER\",\"name\":\"ID_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":23,\"type\":\"INTEGER\",\"name\":\"ID_2\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}],\"measures\":[{\"id\":24,\"type\":\"INTEGER\",\"name\":\"CMP_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},{\"id\":25,\"type\":\"STRING\",\"name\":\"ME_1\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"viral\":[{\"id\":26,\"type\":\"STRING\",\"name\":\"AT_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"VIRAL\"}]},\"messages\":[],\"adataset\":true,\"ascalar\":false,\"acomponent\":false},{\"result\":{\"id\":7,\"name\":\"ds64\",\"description\":null,\"attributes\":[{\"id\":21,\"type\":\"STRING\",\"name\":\"AT_1\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"ATTRIBUTE\"}],\"persistent\":false,\"identifiers\":[{\"id\":18,\"type\":\"INTEGER\",\"name\":\"ID_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":19,\"type\":\"INTEGER\",\"name\":\"CMP_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}],\"measures\":[{\"id\":20,\"type\":\"STRING\",\"name\":\"ME_1\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"viral\":[]},\"messages\":[],\"adataset\":true,\"ascalar\":false,\"acomponent\":false},{\"result\":{\"id\":5,\"name\":\"ds63\",\"description\":null,\"attributes\":[{\"id\":17,\"type\":\"STRING\",\"name\":\"AT_1\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"ATTRIBUTE\"}],\"persistent\":false,\"identifiers\":[{\"id\":14,\"type\":\"INTEGER\",\"name\":\"ID_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":15,\"type\":\"INTEGER\",\"name\":\"CMP_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}],\"measures\":[{\"id\":16,\"type\":\"STRING\",\"name\":\"ME_1\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"viral\":[]},\"messages\":[],\"adataset\":true,\"ascalar\":false,\"acomponent\":false}]";
		List<ResultExpression> result = vtlTestUtils.getResult(commandStatements);
		System.out.println("########innerJoinFilter4: "+vtlTestUtils.convertResultToJson(result));
		List<ResultExpression> resultExpressionsToComapare = vtlTestUtils.convertJsonToResultExpression(jsonResult);
		assertNotNull("Conversione non riuscita", resultExpressionsToComapare);
		assertNotNull("Validazione Semantica non riuscita", result);
		assertTrue("I due risultati non sono uguali",
				vtlTestUtils.compareResult(result.get(0), resultExpressionsToComapare.get(0)));
	}*/

/*	@Test
	@Transactional
	public void innerJoinFilter6() throws Exception {
		
		VtlDataset vtlDatasetFind = datasetRepository.findByNameIgnoreCase("DS_1");
		if (vtlDatasetFind == null) {
			VtlDataset vtlDataset = new VtlDataset();
			vtlDataset.setPersistent(true);
			vtlDataset.setName("DS_63");
			vtlDataset.setIsOnlyAScalar(false);
			vtlDataset.setTransitory(false);
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_1", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("CMP_1", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_1", VtlType.STRING, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("AT_1", VtlType.STRING, VtlComponentRole.ATTRIBUTE));
			datasetRepository.save(vtlDataset);

			VtlDataset vtlDatasetSecond = new VtlDataset();
			vtlDatasetSecond.setPersistent(true);
			vtlDatasetSecond.setName("DS_64");
			vtlDatasetSecond.setIsOnlyAScalar(false);
			vtlDatasetSecond.setTransitory(false);
			vtlDatasetSecond
					.addComponent(vtlTestUtils.getVtlComponent("ID_1", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDatasetSecond
					.addComponent(vtlTestUtils.getVtlComponent("CMP_1", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDatasetSecond
					.addComponent(vtlTestUtils.getVtlComponent("ME_1", VtlType.STRING, VtlComponentRole.MEASURE));
			vtlDatasetSecond
					.addComponent(vtlTestUtils.getVtlComponent("AT_1", VtlType.STRING, VtlComponentRole.ATTRIBUTE));
			datasetRepository.save(vtlDatasetSecond);
			
			VtlDataset vtlDatasetThird = new VtlDataset();
			vtlDatasetThird.setPersistent(true);
			vtlDatasetThird.setName("DS_65");
			vtlDatasetThird.setIsOnlyAScalar(false);
			vtlDatasetThird.setTransitory(false);
			vtlDatasetThird
					.addComponent(vtlTestUtils.getVtlComponent("ID_1", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDatasetThird
					.addComponent(vtlTestUtils.getVtlComponent("ID_2", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDatasetThird
					.addComponent(vtlTestUtils.getVtlComponent("CMP_1", VtlType.INTEGER, VtlComponentRole.MEASURE));
			vtlDatasetThird
					.addComponent(vtlTestUtils.getVtlComponent("ME_1", VtlType.STRING, VtlComponentRole.MEASURE));
			vtlDatasetThird
					.addComponent(vtlTestUtils.getVtlComponent("AT_2", VtlType.STRING, VtlComponentRole.VIRAL));			
			datasetRepository.save(vtlDatasetThird);
						
		}
		String commandStatements = "DS_r := inner_join (DS_63 as ds63, DS_64 as ds64, DS_65 as ds65 using ID_1, CMP_1 filter ds63#ME_1>0);";
		String jsonResult = "[{\"result\":{\"id\":19,\"name\":\"DS_r\",\"description\":null,\"attributes\":[],\"persistent\":false,\"identifiers\":[],\"measures\":[],\"viral\":[]},\"messages\":[],\"command\":\"DS_r:=inner_join(DS_63asds63,DS_64asds64,DS_65asds65usingID_1,CMP_1filterds63#ME_1>0)\",\"adataset\":true,\"acomponent\":false,\"ascalar\":false},{\"result\":{\"id\":17,\"name\":\"temporary_16\",\"description\":null,\"attributes\":[],\"persistent\":false,\"identifiers\":[],\"measures\":[],\"viral\":[]},\"messages\":[],\"command\":\"inner_join(DS_63asds63,DS_64asds64,DS_65asds65usingID_1,CMP_1filterds63#ME_1>0)\",\"adataset\":true,\"acomponent\":false,\"ascalar\":false},{\"result\":{\"id\":15,\"name\":\"temporary_14\",\"description\":null,\"attributes\":[{\"id\":40,\"type\":\"STRING\",\"name\":\"ds63#AT_1\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"ATTRIBUTE\"},{\"id\":42,\"type\":\"STRING\",\"name\":\"ds64#AT_1\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"ATTRIBUTE\"}],\"persistent\":false,\"identifiers\":[{\"id\":37,\"type\":\"INTEGER\",\"name\":\"ID_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":38,\"type\":\"INTEGER\",\"name\":\"ID_2\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}],\"measures\":[{\"id\":39,\"type\":\"STRING\",\"name\":\"ds63#ME_1\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"MEASURE\"},{\"id\":41,\"type\":\"STRING\",\"name\":\"ds64#ME_1\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"MEASURE\"},{\"id\":43,\"type\":\"INTEGER\",\"name\":\"CMP_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},{\"id\":44,\"type\":\"STRING\",\"name\":\"ds65#ME_1\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"viral\":[{\"id\":45,\"type\":\"STRING\",\"name\":\"AT_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"VIRAL\"}]},\"messages\":[],\"command\":\"filterds63#ME_1>0\",\"adataset\":true,\"acomponent\":false,\"ascalar\":false},{\"resultComponent\":{\"id\":null,\"type\":\"BOOLEAN\",\"name\":\"bool_var\",\"domainValue\":\"bool_vd\",\"domainValueParent\":\"bool_vd\",\"vtlComponentRole\":\"MEASURE\"},\"messages\":[],\"command\":\"ds63#ME_1>0\",\"adataset\":false,\"acomponent\":true,\"ascalar\":false},{\"resultComponent\":{\"id\":29,\"type\":\"STRING\",\"name\":\"ds63#ME_1\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"MEASURE\"},\"messages\":[],\"command\":\"ds63#ME_1\",\"adataset\":false,\"acomponent\":true,\"ascalar\":false},{\"result\":{\"id\":13,\"name\":\"temporary_12\",\"description\":null,\"attributes\":[],\"persistent\":false,\"identifiers\":[],\"measures\":[{\"id\":36,\"type\":\"INTEGER\",\"name\":\"int_var\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"viral\":[]},\"resultComponent\":{\"id\":36,\"type\":\"INTEGER\",\"name\":\"int_var\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},\"messages\":[],\"command\":\"0\",\"adataset\":false,\"acomponent\":false,\"ascalar\":true,\"scalarComponent\":{\"id\":36,\"type\":\"INTEGER\",\"name\":\"int_var\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"}},{\"result\":{\"id\":11,\"name\":\"temporary_10\",\"description\":null,\"attributes\":[{\"id\":30,\"type\":\"STRING\",\"name\":\"ds63#AT_1\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"ATTRIBUTE\"},{\"id\":32,\"type\":\"STRING\",\"name\":\"ds64#AT_1\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"ATTRIBUTE\"}],\"persistent\":false,\"identifiers\":[{\"id\":27,\"type\":\"INTEGER\",\"name\":\"ID_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":28,\"type\":\"INTEGER\",\"name\":\"ID_2\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}],\"measures\":[{\"id\":29,\"type\":\"STRING\",\"name\":\"ds63#ME_1\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"MEASURE\"},{\"id\":31,\"type\":\"STRING\",\"name\":\"ds64#ME_1\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"MEASURE\"},{\"id\":33,\"type\":\"INTEGER\",\"name\":\"CMP_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},{\"id\":34,\"type\":\"STRING\",\"name\":\"ds65#ME_1\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"viral\":[{\"id\":35,\"type\":\"STRING\",\"name\":\"AT_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"VIRAL\"}]},\"messages\":[],\"adataset\":true,\"acomponent\":false,\"ascalar\":false},{\"result\":{\"id\":9,\"name\":\"ds65\",\"description\":null,\"attributes\":[],\"persistent\":false,\"identifiers\":[{\"id\":22,\"type\":\"INTEGER\",\"name\":\"ID_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":23,\"type\":\"INTEGER\",\"name\":\"ID_2\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}],\"measures\":[{\"id\":24,\"type\":\"INTEGER\",\"name\":\"CMP_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},{\"id\":25,\"type\":\"STRING\",\"name\":\"ME_1\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"viral\":[{\"id\":26,\"type\":\"STRING\",\"name\":\"AT_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"VIRAL\"}]},\"messages\":[],\"adataset\":true,\"acomponent\":false,\"ascalar\":false},{\"result\":{\"id\":7,\"name\":\"ds64\",\"description\":null,\"attributes\":[{\"id\":21,\"type\":\"STRING\",\"name\":\"AT_1\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"ATTRIBUTE\"}],\"persistent\":false,\"identifiers\":[{\"id\":18,\"type\":\"INTEGER\",\"name\":\"ID_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":19,\"type\":\"INTEGER\",\"name\":\"CMP_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}],\"measures\":[{\"id\":20,\"type\":\"STRING\",\"name\":\"ME_1\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"viral\":[]},\"messages\":[],\"adataset\":true,\"acomponent\":false,\"ascalar\":false},{\"result\":{\"id\":5,\"name\":\"ds63\",\"description\":null,\"attributes\":[{\"id\":17,\"type\":\"STRING\",\"name\":\"AT_1\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"ATTRIBUTE\"}],\"persistent\":false,\"identifiers\":[{\"id\":14,\"type\":\"INTEGER\",\"name\":\"ID_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":15,\"type\":\"INTEGER\",\"name\":\"CMP_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}],\"measures\":[{\"id\":16,\"type\":\"STRING\",\"name\":\"ME_1\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"viral\":[]},\"messages\":[],\"adataset\":true,\"acomponent\":false,\"ascalar\":false}]";
		List<ResultExpression> result = vtlTestUtils.getResult(commandStatements);
		System.out.println("########innerJoinFilter6: "+vtlTestUtils.convertResultToJson(result));
		List<ResultExpression> resultExpressionsToComapare = vtlTestUtils.convertJsonToResultExpression(jsonResult);
		assertNotNull("Conversione non riuscita", resultExpressionsToComapare);
		assertNotNull("Validazione Semantica non riuscita", result);
		assertTrue("I due risultati non sono uguali",
				vtlTestUtils.compareResult(result.get(0), resultExpressionsToComapare.get(0)));
	}*/
	
	@Test
	@Transactional
	public void innerJoinCalc() throws Exception { 
		VtlDataset vtlDatasetFind = datasetRepository.findByNameIgnoreCase("DS_1");
		if (vtlDatasetFind == null) {
			VtlDataset vtlDataset = new VtlDataset();
			vtlDataset.setPersistent(true);
			vtlDataset.setName("DS_63");
			vtlDataset.setIsOnlyAScalar(false);
			vtlDataset.setTransitory(false);
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_1", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_1", VtlType.INTEGER, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("AT_1", VtlType.STRING, VtlComponentRole.ATTRIBUTE));
			datasetRepository.save(vtlDataset);

			VtlDataset vtlDatasetSecond = new VtlDataset();
			vtlDatasetSecond.setPersistent(true);
			vtlDatasetSecond.setName("DS_64");
			vtlDatasetSecond.setIsOnlyAScalar(false);
			vtlDatasetSecond.setTransitory(false);
			vtlDatasetSecond
					.addComponent(vtlTestUtils.getVtlComponent("ID_1", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDatasetSecond
					.addComponent(vtlTestUtils.getVtlComponent("ID_2", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDatasetSecond
					.addComponent(vtlTestUtils.getVtlComponent("ME_2", VtlType.INTEGER, VtlComponentRole.MEASURE));
			vtlDatasetSecond
					.addComponent(vtlTestUtils.getVtlComponent("AT_2", VtlType.STRING, VtlComponentRole.VIRAL));
			datasetRepository.save(vtlDatasetSecond);
			
			VtlDataset vtlDatasetThird = new VtlDataset();
			vtlDatasetThird.setPersistent(true);
			vtlDatasetThird.setName("DS_65");
			vtlDatasetThird.setIsOnlyAScalar(false);
			vtlDatasetThird.setTransitory(false);
			vtlDatasetThird
					.addComponent(vtlTestUtils.getVtlComponent("ID_1", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDatasetThird
					.addComponent(vtlTestUtils.getVtlComponent("ID_2", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDatasetThird
					.addComponent(vtlTestUtils.getVtlComponent("ID_3", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDatasetThird
					.addComponent(vtlTestUtils.getVtlComponent("ME_3", VtlType.INTEGER, VtlComponentRole.MEASURE));
			datasetRepository.save(vtlDatasetThird);
						
		}
		String commandStatements = "DS_r := inner_join (DS_63 as ds63, DS_64 as ds64, DS_65 as ds65 calc ME_1:=ME_2+ME_3);";
		String jsonResult = "[{\"result\":{\"id\":17,\"name\":\"DS_r\",\"description\":null,\"attributes\":[{\"id\":51,\"type\":\"STRING\",\"name\":\"AT_1\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"ATTRIBUTE\"}],\"persistent\":false,\"identifiers\":[{\"id\":48,\"type\":\"INTEGER\",\"name\":\"ID_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":49,\"type\":\"INTEGER\",\"name\":\"ID_2\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":50,\"type\":\"INTEGER\",\"name\":\"ID_3\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}],\"measures\":[{\"id\":52,\"type\":\"INTEGER\",\"name\":\"ME_2\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},{\"id\":54,\"type\":\"INTEGER\",\"name\":\"ME_3\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},{\"id\":55,\"type\":\"INTEGER\",\"name\":\"ME_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"viral\":[{\"id\":53,\"type\":\"STRING\",\"name\":\"AT_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"VIRAL\"}]},\"messages\":[],\"command\":\"DS_r:=inner_join(DS_63asds63,DS_64asds64,DS_65asds65calcME_1:=ME_2+ME_3)\",\"adataset\":true,\"acomponent\":false,\"ascalar\":false},{\"result\":{\"id\":15,\"name\":\"temporary_14\",\"description\":null,\"attributes\":[{\"id\":43,\"type\":\"STRING\",\"name\":\"AT_1\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"ATTRIBUTE\"}],\"persistent\":false,\"identifiers\":[{\"id\":40,\"type\":\"INTEGER\",\"name\":\"ID_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":41,\"type\":\"INTEGER\",\"name\":\"ID_2\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":42,\"type\":\"INTEGER\",\"name\":\"ID_3\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}],\"measures\":[{\"id\":44,\"type\":\"INTEGER\",\"name\":\"ME_2\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},{\"id\":46,\"type\":\"INTEGER\",\"name\":\"ME_3\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},{\"id\":47,\"type\":\"INTEGER\",\"name\":\"ME_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"viral\":[{\"id\":45,\"type\":\"STRING\",\"name\":\"AT_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"VIRAL\"}]},\"messages\":[],\"command\":\"inner_join(DS_63asds63,DS_64asds64,DS_65asds65calcME_1:=ME_2+ME_3)\",\"adataset\":true,\"acomponent\":false,\"ascalar\":false},{\"result\":{\"id\":13,\"name\":\"temporary_12\",\"description\":null,\"attributes\":[{\"id\":35,\"type\":\"STRING\",\"name\":\"AT_1\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"ATTRIBUTE\"}],\"persistent\":false,\"identifiers\":[{\"id\":31,\"type\":\"INTEGER\",\"name\":\"ID_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":32,\"type\":\"INTEGER\",\"name\":\"ID_2\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":33,\"type\":\"INTEGER\",\"name\":\"ID_3\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}],\"measures\":[{\"id\":36,\"type\":\"INTEGER\",\"name\":\"ME_2\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},{\"id\":38,\"type\":\"INTEGER\",\"name\":\"ME_3\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},{\"id\":39,\"type\":\"INTEGER\",\"name\":\"ME_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"viral\":[{\"id\":37,\"type\":\"STRING\",\"name\":\"AT_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"VIRAL\"}]},\"messages\":[],\"command\":\"calcME_1:=ME_2+ME_3\",\"adataset\":true,\"acomponent\":false,\"ascalar\":false},{\"resultComponent\":{\"id\":null,\"type\":\"INTEGER\",\"name\":\"ME_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},\"messages\":[],\"command\":\"ME_1:=ME_2+ME_3\",\"adataset\":false,\"acomponent\":true,\"ascalar\":false},{\"resultComponent\":{\"id\":null,\"type\":\"INTEGER\",\"name\":\"ME_2\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},\"messages\":[],\"command\":\"ME_2+ME_3\",\"adataset\":false,\"acomponent\":true,\"ascalar\":false},{\"resultComponent\":{\"id\":28,\"type\":\"INTEGER\",\"name\":\"ME_2\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},\"messages\":[],\"command\":\"ME_2\",\"adataset\":false,\"acomponent\":true,\"ascalar\":false},{\"resultComponent\":{\"id\":30,\"type\":\"INTEGER\",\"name\":\"ME_3\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},\"messages\":[],\"command\":\"ME_3\",\"adataset\":false,\"acomponent\":true,\"ascalar\":false},{\"result\":{\"id\":11,\"name\":\"temporary_10\",\"description\":null,\"attributes\":[{\"id\":27,\"type\":\"STRING\",\"name\":\"AT_1\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"ATTRIBUTE\"}],\"persistent\":false,\"identifiers\":[{\"id\":23,\"type\":\"INTEGER\",\"name\":\"ID_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":24,\"type\":\"INTEGER\",\"name\":\"ID_2\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":25,\"type\":\"INTEGER\",\"name\":\"ID_3\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}],\"measures\":[{\"id\":26,\"type\":\"INTEGER\",\"name\":\"ME_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},{\"id\":28,\"type\":\"INTEGER\",\"name\":\"ME_2\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},{\"id\":30,\"type\":\"INTEGER\",\"name\":\"ME_3\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"viral\":[{\"id\":29,\"type\":\"STRING\",\"name\":\"AT_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"VIRAL\"}]},\"messages\":[],\"adataset\":true,\"acomponent\":false,\"ascalar\":false},{\"result\":{\"id\":9,\"name\":\"ds65\",\"description\":null,\"attributes\":[],\"persistent\":false,\"identifiers\":[{\"id\":19,\"type\":\"INTEGER\",\"name\":\"ID_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":20,\"type\":\"INTEGER\",\"name\":\"ID_2\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":21,\"type\":\"INTEGER\",\"name\":\"ID_3\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}],\"measures\":[{\"id\":22,\"type\":\"INTEGER\",\"name\":\"ME_3\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"viral\":[]},\"messages\":[],\"adataset\":true,\"acomponent\":false,\"ascalar\":false},{\"result\":{\"id\":7,\"name\":\"ds64\",\"description\":null,\"attributes\":[],\"persistent\":false,\"identifiers\":[{\"id\":15,\"type\":\"INTEGER\",\"name\":\"ID_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":16,\"type\":\"INTEGER\",\"name\":\"ID_2\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}],\"measures\":[{\"id\":17,\"type\":\"INTEGER\",\"name\":\"ME_2\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"viral\":[{\"id\":18,\"type\":\"STRING\",\"name\":\"AT_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"VIRAL\"}]},\"messages\":[],\"adataset\":true,\"acomponent\":false,\"ascalar\":false},{\"result\":{\"id\":5,\"name\":\"ds63\",\"description\":null,\"attributes\":[{\"id\":14,\"type\":\"STRING\",\"name\":\"AT_1\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"ATTRIBUTE\"}],\"persistent\":false,\"identifiers\":[{\"id\":12,\"type\":\"INTEGER\",\"name\":\"ID_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}],\"measures\":[{\"id\":13,\"type\":\"INTEGER\",\"name\":\"ME_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"viral\":[]},\"messages\":[],\"adataset\":true,\"acomponent\":false,\"ascalar\":false}]";
		List<ResultExpression> result = vtlTestUtils.getResult(commandStatements);
		System.out.println("########innerJoinCalc: "+vtlTestUtils.convertResultToJson(result));
		List<ResultExpression> resultExpressionsToComapare = vtlTestUtils.convertJsonToResultExpression(jsonResult);
		assertNotNull("Conversione non riuscita", resultExpressionsToComapare);
		assertNotNull("Validazione Semantica non riuscita", result);
		assertTrue("I due risultati non sono uguali",
				vtlTestUtils.compareResult(result.get(0), resultExpressionsToComapare.get(0)));
	}
	
	//TODO DOVREBBE FALLIRE INVECE PASSA(fare controllo sulla calc non si può fare su identificativi)
	@Test
	@Transactional
	public void innerJoinCalc2Fail() throws Exception {
		//errato, non si può sovrascrivere un Identifier
		VtlDataset vtlDatasetFind = datasetRepository.findByNameIgnoreCase("DS_1");
		if (vtlDatasetFind == null) {
			VtlDataset vtlDataset = new VtlDataset();
			vtlDataset.setPersistent(true);
			vtlDataset.setName("DS_63");
			vtlDataset.setIsOnlyAScalar(false);
			vtlDataset.setTransitory(false);
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_1", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_1", VtlType.STRING, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("AT_1", VtlType.STRING, VtlComponentRole.ATTRIBUTE));
			datasetRepository.save(vtlDataset);

			VtlDataset vtlDatasetSecond = new VtlDataset();
			vtlDatasetSecond.setPersistent(true);
			vtlDatasetSecond.setName("DS_64");
			vtlDatasetSecond.setIsOnlyAScalar(false);
			vtlDatasetSecond.setTransitory(false);
			vtlDatasetSecond
					.addComponent(vtlTestUtils.getVtlComponent("ID_1", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDatasetSecond
					.addComponent(vtlTestUtils.getVtlComponent("ID_2", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDatasetSecond
					.addComponent(vtlTestUtils.getVtlComponent("ME_2", VtlType.INTEGER, VtlComponentRole.MEASURE));
			vtlDatasetSecond
					.addComponent(vtlTestUtils.getVtlComponent("AT_2", VtlType.STRING, VtlComponentRole.VIRAL));
			datasetRepository.save(vtlDatasetSecond);
			
			VtlDataset vtlDatasetThird = new VtlDataset();
			vtlDatasetThird.setPersistent(true);
			vtlDatasetThird.setName("DS_65");
			vtlDatasetThird.setIsOnlyAScalar(false);
			vtlDatasetThird.setTransitory(false);
			vtlDatasetThird
					.addComponent(vtlTestUtils.getVtlComponent("ID_1", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDatasetThird
					.addComponent(vtlTestUtils.getVtlComponent("ID_2", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDatasetThird
					.addComponent(vtlTestUtils.getVtlComponent("ID_3", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDatasetThird
					.addComponent(vtlTestUtils.getVtlComponent("ME_3", VtlType.INTEGER, VtlComponentRole.MEASURE));
			datasetRepository.save(vtlDatasetThird);
						
		}
		String commandStatements = "DS_r := inner_join (DS_63 as ds63, DS_64 as ds64, DS_65 as ds65 calc ID_1:=ME_2+ME_3);";
		String jsonResult = " [{\"result\":{\"id\":17,\"name\":\"DS_r\",\"description\":null,\"attributes\":[{\"id\":51,\"type\":\"STRING\",\"name\":\"AT_1\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"ATTRIBUTE\"}],\"persistent\":false,\"identifiers\":[{\"id\":48,\"type\":\"INTEGER\",\"name\":\"ID_2\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":49,\"type\":\"INTEGER\",\"name\":\"ID_3\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}],\"measures\":[{\"id\":50,\"type\":\"STRING\",\"name\":\"ME_1\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"MEASURE\"},{\"id\":52,\"type\":\"INTEGER\",\"name\":\"ME_2\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},{\"id\":54,\"type\":\"INTEGER\",\"name\":\"ME_3\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},{\"id\":55,\"type\":\"INTEGER\",\"name\":\"ID_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"viral\":[{\"id\":53,\"type\":\"STRING\",\"name\":\"AT_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"VIRAL\"}]},\"messages\":[],\"command\":\"DS_r:=inner_join(DS_63asds63,DS_64asds64,DS_65asds65calcID_1:=ME_2+ME_3)\",\"acomponent\":false,\"ascalar\":false,\"adataset\":true},{\"result\":{\"id\":15,\"name\":\"temporary_14\",\"description\":null,\"attributes\":[{\"id\":43,\"type\":\"STRING\",\"name\":\"AT_1\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"ATTRIBUTE\"}],\"persistent\":false,\"identifiers\":[{\"id\":40,\"type\":\"INTEGER\",\"name\":\"ID_2\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":41,\"type\":\"INTEGER\",\"name\":\"ID_3\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}],\"measures\":[{\"id\":42,\"type\":\"STRING\",\"name\":\"ME_1\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"MEASURE\"},{\"id\":44,\"type\":\"INTEGER\",\"name\":\"ME_2\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},{\"id\":46,\"type\":\"INTEGER\",\"name\":\"ME_3\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},{\"id\":47,\"type\":\"INTEGER\",\"name\":\"ID_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"viral\":[{\"id\":45,\"type\":\"STRING\",\"name\":\"AT_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"VIRAL\"}]},\"messages\":[],\"command\":\"inner_join(DS_63asds63,DS_64asds64,DS_65asds65calcID_1:=ME_2+ME_3)\",\"acomponent\":false,\"ascalar\":false,\"adataset\":true},{\"result\":{\"id\":13,\"name\":\"temporary_12\",\"description\":null,\"attributes\":[{\"id\":35,\"type\":\"STRING\",\"name\":\"AT_1\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"ATTRIBUTE\"}],\"persistent\":false,\"identifiers\":[{\"id\":32,\"type\":\"INTEGER\",\"name\":\"ID_2\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":33,\"type\":\"INTEGER\",\"name\":\"ID_3\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}],\"measures\":[{\"id\":34,\"type\":\"STRING\",\"name\":\"ME_1\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"MEASURE\"},{\"id\":36,\"type\":\"INTEGER\",\"name\":\"ME_2\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},{\"id\":38,\"type\":\"INTEGER\",\"name\":\"ME_3\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},{\"id\":39,\"type\":\"INTEGER\",\"name\":\"ID_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"viral\":[{\"id\":37,\"type\":\"STRING\",\"name\":\"AT_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"VIRAL\"}]},\"messages\":[],\"command\":\"calcID_1:=ME_2+ME_3\",\"acomponent\":false,\"ascalar\":false,\"adataset\":true},{\"resultComponent\":{\"id\":null,\"type\":\"INTEGER\",\"name\":\"ID_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},\"messages\":[],\"command\":\"ID_1:=ME_2+ME_3\",\"acomponent\":true,\"ascalar\":false,\"adataset\":false},{\"resultComponent\":{\"id\":null,\"type\":\"INTEGER\",\"name\":\"ME_2\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},\"messages\":[],\"command\":\"ME_2+ME_3\",\"acomponent\":true,\"ascalar\":false,\"adataset\":false},{\"resultComponent\":{\"id\":28,\"type\":\"INTEGER\",\"name\":\"ME_2\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},\"messages\":[],\"command\":\"ME_2\",\"acomponent\":true,\"ascalar\":false,\"adataset\":false},{\"resultComponent\":{\"id\":30,\"type\":\"INTEGER\",\"name\":\"ME_3\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},\"messages\":[],\"command\":\"ME_3\",\"acomponent\":true,\"ascalar\":false,\"adataset\":false},{\"result\":{\"id\":11,\"name\":\"temporary_10\",\"description\":null,\"attributes\":[{\"id\":27,\"type\":\"STRING\",\"name\":\"AT_1\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"ATTRIBUTE\"}],\"persistent\":false,\"identifiers\":[{\"id\":23,\"type\":\"INTEGER\",\"name\":\"ID_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":24,\"type\":\"INTEGER\",\"name\":\"ID_2\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":25,\"type\":\"INTEGER\",\"name\":\"ID_3\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}],\"measures\":[{\"id\":26,\"type\":\"STRING\",\"name\":\"ME_1\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"MEASURE\"},{\"id\":28,\"type\":\"INTEGER\",\"name\":\"ME_2\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},{\"id\":30,\"type\":\"INTEGER\",\"name\":\"ME_3\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"viral\":[{\"id\":29,\"type\":\"STRING\",\"name\":\"AT_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"VIRAL\"}]},\"messages\":[],\"acomponent\":false,\"ascalar\":false,\"adataset\":true},{\"result\":{\"id\":9,\"name\":\"ds65\",\"description\":null,\"attributes\":[],\"persistent\":false,\"identifiers\":[{\"id\":19,\"type\":\"INTEGER\",\"name\":\"ID_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":20,\"type\":\"INTEGER\",\"name\":\"ID_2\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":21,\"type\":\"INTEGER\",\"name\":\"ID_3\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}],\"measures\":[{\"id\":22,\"type\":\"INTEGER\",\"name\":\"ME_3\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"viral\":[]},\"messages\":[],\"acomponent\":false,\"ascalar\":false,\"adataset\":true},{\"result\":{\"id\":7,\"name\":\"ds64\",\"description\":null,\"attributes\":[],\"persistent\":false,\"identifiers\":[{\"id\":15,\"type\":\"INTEGER\",\"name\":\"ID_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":16,\"type\":\"INTEGER\",\"name\":\"ID_2\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}],\"measures\":[{\"id\":17,\"type\":\"INTEGER\",\"name\":\"ME_2\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"viral\":[{\"id\":18,\"type\":\"STRING\",\"name\":\"AT_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"VIRAL\"}]},\"messages\":[],\"acomponent\":false,\"ascalar\":false,\"adataset\":true},{\"result\":{\"id\":5,\"name\":\"ds63\",\"description\":null,\"attributes\":[{\"id\":14,\"type\":\"STRING\",\"name\":\"AT_1\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"ATTRIBUTE\"}],\"persistent\":false,\"identifiers\":[{\"id\":12,\"type\":\"INTEGER\",\"name\":\"ID_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}],\"measures\":[{\"id\":13,\"type\":\"STRING\",\"name\":\"ME_1\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"viral\":[]},\"messages\":[],\"acomponent\":false,\"ascalar\":false,\"adataset\":true}]";
		List<ResultExpression> result = vtlTestUtils.getResult(commandStatements);
		System.out.println("########innerJoinCalc2Fail: "+vtlTestUtils.convertResultToJson(result));
		List<ResultExpression> resultExpressionsToComapare = vtlTestUtils.convertJsonToResultExpression(jsonResult);
		assertNotNull("Conversione non riuscita", resultExpressionsToComapare);
		assertNotNull("Validazione Semantica non riuscita", result);
		assertTrue("I due risultati non sono uguali",
				vtlTestUtils.compareResult(result.get(0), resultExpressionsToComapare.get(0)));
	}
	
	@Test
	@Transactional
	public void innerJoinCalc3() throws Exception {
		
		VtlDataset vtlDatasetFind = datasetRepository.findByNameIgnoreCase("DS_1");
		if (vtlDatasetFind == null) {
			VtlDataset vtlDataset = new VtlDataset();
			vtlDataset.setPersistent(true);
			vtlDataset.setName("DS_63");
			vtlDataset.setIsOnlyAScalar(false);
			vtlDataset.setTransitory(false);
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_1", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_1", VtlType.STRING, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("AT_1", VtlType.STRING, VtlComponentRole.ATTRIBUTE));
			datasetRepository.save(vtlDataset);

			VtlDataset vtlDatasetSecond = new VtlDataset();
			vtlDatasetSecond.setPersistent(true);
			vtlDatasetSecond.setName("DS_64");
			vtlDatasetSecond.setIsOnlyAScalar(false);
			vtlDatasetSecond.setTransitory(false);
			vtlDatasetSecond
					.addComponent(vtlTestUtils.getVtlComponent("ID_1", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDatasetSecond
					.addComponent(vtlTestUtils.getVtlComponent("ID_2", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDatasetSecond
					.addComponent(vtlTestUtils.getVtlComponent("ME_2", VtlType.INTEGER, VtlComponentRole.MEASURE));
			vtlDatasetSecond
					.addComponent(vtlTestUtils.getVtlComponent("AT_2", VtlType.STRING, VtlComponentRole.VIRAL));
			datasetRepository.save(vtlDatasetSecond);
			
			VtlDataset vtlDatasetThird = new VtlDataset();
			vtlDatasetThird.setPersistent(true);
			vtlDatasetThird.setName("DS_65");
			vtlDatasetThird.setIsOnlyAScalar(false);
			vtlDatasetThird.setTransitory(false);
			vtlDatasetThird
					.addComponent(vtlTestUtils.getVtlComponent("ID_1", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDatasetThird
					.addComponent(vtlTestUtils.getVtlComponent("ID_2", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDatasetThird
					.addComponent(vtlTestUtils.getVtlComponent("ID_3", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDatasetThird
					.addComponent(vtlTestUtils.getVtlComponent("ME_3", VtlType.INTEGER, VtlComponentRole.MEASURE));
			datasetRepository.save(vtlDatasetThird);
						
		}
		String commandStatements = "DS_r := inner_join (DS_63 as ds63, DS_64 as ds64, DS_65 as ds65 calc identifier ID_4:=ME_2+ME_3);";
		String jsonResult = "[{\"result\":{\"id\":17,\"name\":\"DS_r\",\"description\":null,\"attributes\":[{\"id\":53,\"type\":\"STRING\",\"name\":\"AT_1\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"ATTRIBUTE\"}],\"persistent\":false,\"identifiers\":[{\"id\":49,\"type\":\"INTEGER\",\"name\":\"ID_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":50,\"type\":\"INTEGER\",\"name\":\"ID_2\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":51,\"type\":\"INTEGER\",\"name\":\"ID_3\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":57,\"type\":\"INTEGER\",\"name\":\"ID_4\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}],\"measures\":[{\"id\":52,\"type\":\"STRING\",\"name\":\"ME_1\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"MEASURE\"},{\"id\":54,\"type\":\"INTEGER\",\"name\":\"ME_2\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},{\"id\":56,\"type\":\"INTEGER\",\"name\":\"ME_3\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"viral\":[{\"id\":55,\"type\":\"STRING\",\"name\":\"AT_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"VIRAL\"}]},\"messages\":[],\"command\":\"DS_r:=inner_join(DS_63asds63,DS_64asds64,DS_65asds65calcidentifierID_4:=ME_2+ME_3)\",\"acomponent\":false,\"ascalar\":false,\"adataset\":true},{\"result\":{\"id\":15,\"name\":\"temporary_14\",\"description\":null,\"attributes\":[{\"id\":44,\"type\":\"STRING\",\"name\":\"AT_1\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"ATTRIBUTE\"}],\"persistent\":false,\"identifiers\":[{\"id\":40,\"type\":\"INTEGER\",\"name\":\"ID_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":41,\"type\":\"INTEGER\",\"name\":\"ID_2\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":42,\"type\":\"INTEGER\",\"name\":\"ID_3\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":48,\"type\":\"INTEGER\",\"name\":\"ID_4\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}],\"measures\":[{\"id\":43,\"type\":\"STRING\",\"name\":\"ME_1\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"MEASURE\"},{\"id\":45,\"type\":\"INTEGER\",\"name\":\"ME_2\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},{\"id\":47,\"type\":\"INTEGER\",\"name\":\"ME_3\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"viral\":[{\"id\":46,\"type\":\"STRING\",\"name\":\"AT_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"VIRAL\"}]},\"messages\":[],\"command\":\"inner_join(DS_63asds63,DS_64asds64,DS_65asds65calcidentifierID_4:=ME_2+ME_3)\",\"acomponent\":false,\"ascalar\":false,\"adataset\":true},{\"result\":{\"id\":13,\"name\":\"temporary_12\",\"description\":null,\"attributes\":[{\"id\":35,\"type\":\"STRING\",\"name\":\"AT_1\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"ATTRIBUTE\"}],\"persistent\":false,\"identifiers\":[{\"id\":31,\"type\":\"INTEGER\",\"name\":\"ID_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":32,\"type\":\"INTEGER\",\"name\":\"ID_2\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":33,\"type\":\"INTEGER\",\"name\":\"ID_3\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":39,\"type\":\"INTEGER\",\"name\":\"ID_4\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}],\"measures\":[{\"id\":34,\"type\":\"STRING\",\"name\":\"ME_1\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"MEASURE\"},{\"id\":36,\"type\":\"INTEGER\",\"name\":\"ME_2\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},{\"id\":38,\"type\":\"INTEGER\",\"name\":\"ME_3\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"viral\":[{\"id\":37,\"type\":\"STRING\",\"name\":\"AT_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"VIRAL\"}]},\"messages\":[],\"command\":\"calcidentifierID_4:=ME_2+ME_3\",\"acomponent\":false,\"ascalar\":false,\"adataset\":true},{\"resultComponent\":{\"id\":null,\"type\":\"INTEGER\",\"name\":\"ID_4\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},\"messages\":[],\"command\":\"identifierID_4:=ME_2+ME_3\",\"acomponent\":true,\"ascalar\":false,\"adataset\":false},{\"resultComponent\":{\"id\":null,\"type\":\"INTEGER\",\"name\":\"ME_2\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},\"messages\":[],\"command\":\"ME_2+ME_3\",\"acomponent\":true,\"ascalar\":false,\"adataset\":false},{\"resultComponent\":{\"id\":28,\"type\":\"INTEGER\",\"name\":\"ME_2\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},\"messages\":[],\"command\":\"ME_2\",\"acomponent\":true,\"ascalar\":false,\"adataset\":false},{\"resultComponent\":{\"id\":30,\"type\":\"INTEGER\",\"name\":\"ME_3\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},\"messages\":[],\"command\":\"ME_3\",\"acomponent\":true,\"ascalar\":false,\"adataset\":false},{\"result\":{\"id\":11,\"name\":\"temporary_10\",\"description\":null,\"attributes\":[{\"id\":27,\"type\":\"STRING\",\"name\":\"AT_1\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"ATTRIBUTE\"}],\"persistent\":false,\"identifiers\":[{\"id\":23,\"type\":\"INTEGER\",\"name\":\"ID_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":24,\"type\":\"INTEGER\",\"name\":\"ID_2\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":25,\"type\":\"INTEGER\",\"name\":\"ID_3\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}],\"measures\":[{\"id\":26,\"type\":\"STRING\",\"name\":\"ME_1\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"MEASURE\"},{\"id\":28,\"type\":\"INTEGER\",\"name\":\"ME_2\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},{\"id\":30,\"type\":\"INTEGER\",\"name\":\"ME_3\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"viral\":[{\"id\":29,\"type\":\"STRING\",\"name\":\"AT_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"VIRAL\"}]},\"messages\":[],\"acomponent\":false,\"ascalar\":false,\"adataset\":true},{\"result\":{\"id\":9,\"name\":\"ds65\",\"description\":null,\"attributes\":[],\"persistent\":false,\"identifiers\":[{\"id\":19,\"type\":\"INTEGER\",\"name\":\"ID_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":20,\"type\":\"INTEGER\",\"name\":\"ID_2\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":21,\"type\":\"INTEGER\",\"name\":\"ID_3\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}],\"measures\":[{\"id\":22,\"type\":\"INTEGER\",\"name\":\"ME_3\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"viral\":[]},\"messages\":[],\"acomponent\":false,\"ascalar\":false,\"adataset\":true},{\"result\":{\"id\":7,\"name\":\"ds64\",\"description\":null,\"attributes\":[],\"persistent\":false,\"identifiers\":[{\"id\":15,\"type\":\"INTEGER\",\"name\":\"ID_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":16,\"type\":\"INTEGER\",\"name\":\"ID_2\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}],\"measures\":[{\"id\":17,\"type\":\"INTEGER\",\"name\":\"ME_2\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"viral\":[{\"id\":18,\"type\":\"STRING\",\"name\":\"AT_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"VIRAL\"}]},\"messages\":[],\"acomponent\":false,\"ascalar\":false,\"adataset\":true},{\"result\":{\"id\":5,\"name\":\"ds63\",\"description\":null,\"attributes\":[{\"id\":14,\"type\":\"STRING\",\"name\":\"AT_1\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"ATTRIBUTE\"}],\"persistent\":false,\"identifiers\":[{\"id\":12,\"type\":\"INTEGER\",\"name\":\"ID_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}],\"measures\":[{\"id\":13,\"type\":\"STRING\",\"name\":\"ME_1\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"viral\":[]},\"messages\":[],\"acomponent\":false,\"ascalar\":false,\"adataset\":true}]";
		List<ResultExpression> result = vtlTestUtils.getResult(commandStatements);
		System.out.println("########innerJoinCalc3: "+vtlTestUtils.convertResultToJson(result));
		List<ResultExpression> resultExpressionsToComapare = vtlTestUtils.convertJsonToResultExpression(jsonResult);
		assertNotNull("Conversione non riuscita", resultExpressionsToComapare);
		assertNotNull("Validazione Semantica non riuscita", result);
		assertTrue("I due risultati non sono uguali",
				vtlTestUtils.compareResult(result.get(0), resultExpressionsToComapare.get(0)));
	}
	
	//TODO MANCA AT_3 in DS_r (FARE MODIFICA PER FARE SALIRE GLI ATTIBUTI NELLA GROUP BY)
	@Test
	@Transactional
	public void innerJoinAggr() throws Exception {
		
		VtlDataset vtlDatasetFind = datasetRepository.findByNameIgnoreCase("DS_1");
		if (vtlDatasetFind == null) {
			VtlDataset vtlDataset = new VtlDataset();
			vtlDataset.setPersistent(true);
			vtlDataset.setName("DS_63");
			vtlDataset.setIsOnlyAScalar(false);
			vtlDataset.setTransitory(false);
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_1", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_1", VtlType.STRING, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("AT_1", VtlType.INTEGER, VtlComponentRole.ATTRIBUTE));
			datasetRepository.save(vtlDataset);

			VtlDataset vtlDatasetSecond = new VtlDataset();
			vtlDatasetSecond.setPersistent(true);
			vtlDatasetSecond.setName("DS_64");
			vtlDatasetSecond.setIsOnlyAScalar(false);
			vtlDatasetSecond.setTransitory(false);
			vtlDatasetSecond
					.addComponent(vtlTestUtils.getVtlComponent("ID_1", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDatasetSecond
					.addComponent(vtlTestUtils.getVtlComponent("ID_2", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDatasetSecond
					.addComponent(vtlTestUtils.getVtlComponent("ME_2", VtlType.INTEGER, VtlComponentRole.MEASURE));
			vtlDatasetSecond
					.addComponent(vtlTestUtils.getVtlComponent("AT_2", VtlType.STRING, VtlComponentRole.VIRAL));
			datasetRepository.save(vtlDatasetSecond);
			
			VtlDataset vtlDatasetThird = new VtlDataset();
			vtlDatasetThird.setPersistent(true);
			vtlDatasetThird.setName("DS_65");
			vtlDatasetThird.setIsOnlyAScalar(false);
			vtlDatasetThird.setTransitory(false);
			vtlDatasetThird
					.addComponent(vtlTestUtils.getVtlComponent("ID_1", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDatasetThird
					.addComponent(vtlTestUtils.getVtlComponent("ID_2", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDatasetThird
					.addComponent(vtlTestUtils.getVtlComponent("ID_3", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
			vtlDatasetThird
					.addComponent(vtlTestUtils.getVtlComponent("ME_3", VtlType.INTEGER, VtlComponentRole.MEASURE));
			datasetRepository.save(vtlDatasetThird);
						
		}
		String commandStatements = "DS_r := inner_join (DS_63 as ds63, DS_64 as ds64, DS_65 as ds65 aggr ME_1:=sum(ME_2), attribute AT_3:=sum(AT_1) group by ID_1, ID_2);";
		String jsonResult = "[{\"result\":{\"id\":19,\"name\":\"DS_r\",\"description\":null,\"attributes\":[],\"persistent\":false,\"identifiers\":[{\"id\":42,\"type\":\"INTEGER\",\"name\":\"ID_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":43,\"type\":\"INTEGER\",\"name\":\"ID_2\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}],\"measures\":[{\"id\":44,\"type\":\"INTEGER\",\"name\":\"ME_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"viral\":[]},\"messages\":[],\"command\":\"DS_r:=inner_join(DS_63asds63,DS_64asds64,DS_65asds65aggrME_1:=sum(ME_2),attributeAT_3:=sum(AT_1)groupbyID_1,ID_2)\",\"adataset\":true,\"acomponent\":false,\"ascalar\":false},{\"result\":{\"id\":17,\"name\":\"temporary_16\",\"description\":null,\"attributes\":[],\"persistent\":false,\"identifiers\":[{\"id\":39,\"type\":\"INTEGER\",\"name\":\"ID_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":40,\"type\":\"INTEGER\",\"name\":\"ID_2\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}],\"measures\":[{\"id\":41,\"type\":\"INTEGER\",\"name\":\"ME_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"viral\":[]},\"messages\":[],\"command\":\"inner_join(DS_63asds63,DS_64asds64,DS_65asds65aggrME_1:=sum(ME_2),attributeAT_3:=sum(AT_1)groupbyID_1,ID_2)\",\"adataset\":true,\"acomponent\":false,\"ascalar\":false},{\"result\":{\"id\":15,\"name\":\"temporary_14\",\"description\":null,\"attributes\":[],\"persistent\":false,\"identifiers\":[{\"id\":36,\"type\":\"INTEGER\",\"name\":\"ID_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":37,\"type\":\"INTEGER\",\"name\":\"ID_2\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}],\"measures\":[{\"id\":38,\"type\":\"INTEGER\",\"name\":\"ME_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"viral\":[]},\"messages\":[],\"command\":\"aggrME_1:=sum(ME_2),attributeAT_3:=sum(AT_1)groupbyID_1,ID_2\",\"adataset\":true,\"acomponent\":false,\"ascalar\":false},{\"resultComponent\":{\"id\":32,\"type\":\"INTEGER\",\"name\":\"ID_2\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},\"messages\":[],\"command\":\"ID_2\",\"adataset\":false,\"acomponent\":true,\"ascalar\":false},{\"resultComponent\":{\"id\":31,\"type\":\"INTEGER\",\"name\":\"ID_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},\"messages\":[],\"command\":\"ID_1\",\"adataset\":false,\"acomponent\":true,\"ascalar\":false},{\"result\":{\"id\":13,\"name\":\"temporary_12\",\"description\":\"temporary_10\",\"attributes\":[{\"id\":34,\"type\":\"INTEGER\",\"name\":\"AT_3\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"ATTRIBUTE\"}],\"persistent\":false,\"identifiers\":[{\"id\":31,\"type\":\"INTEGER\",\"name\":\"ID_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":32,\"type\":\"INTEGER\",\"name\":\"ID_2\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":33,\"type\":\"INTEGER\",\"name\":\"ID_3\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}],\"measures\":[{\"id\":35,\"type\":\"INTEGER\",\"name\":\"ME_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"viral\":[]},\"messages\":[],\"command\":\"ME_1:=sum(ME_2),attributeAT_3:=sum(AT_1)\",\"adataset\":true,\"acomponent\":false,\"ascalar\":false},{\"resultComponent\":{\"id\":null,\"type\":\"INTEGER\",\"name\":\"AT_3\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"ATTRIBUTE\"},\"messages\":[],\"command\":\"attributeAT_3:=sum(AT_1)\",\"adataset\":false,\"acomponent\":true,\"ascalar\":false},{\"resultComponent\":{\"id\":null,\"type\":\"INTEGER\",\"name\":\"ME_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},\"messages\":[],\"command\":\"ME_1:=sum(ME_2)\",\"adataset\":false,\"acomponent\":true,\"ascalar\":false},{\"result\":{\"id\":11,\"name\":\"temporary_10\",\"description\":null,\"attributes\":[{\"id\":27,\"type\":\"INTEGER\",\"name\":\"AT_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"ATTRIBUTE\"}],\"persistent\":false,\"identifiers\":[{\"id\":23,\"type\":\"INTEGER\",\"name\":\"ID_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":24,\"type\":\"INTEGER\",\"name\":\"ID_2\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":25,\"type\":\"INTEGER\",\"name\":\"ID_3\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}],\"measures\":[{\"id\":26,\"type\":\"STRING\",\"name\":\"ME_1\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"MEASURE\"},{\"id\":28,\"type\":\"INTEGER\",\"name\":\"ME_2\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},{\"id\":30,\"type\":\"INTEGER\",\"name\":\"ME_3\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"viral\":[{\"id\":29,\"type\":\"STRING\",\"name\":\"AT_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"VIRAL\"}]},\"messages\":[],\"adataset\":true,\"acomponent\":false,\"ascalar\":false},{\"result\":{\"id\":9,\"name\":\"ds65\",\"description\":null,\"attributes\":[],\"persistent\":false,\"identifiers\":[{\"id\":19,\"type\":\"INTEGER\",\"name\":\"ID_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":20,\"type\":\"INTEGER\",\"name\":\"ID_2\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":21,\"type\":\"INTEGER\",\"name\":\"ID_3\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}],\"measures\":[{\"id\":22,\"type\":\"INTEGER\",\"name\":\"ME_3\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"viral\":[]},\"messages\":[],\"adataset\":true,\"acomponent\":false,\"ascalar\":false},{\"result\":{\"id\":7,\"name\":\"ds64\",\"description\":null,\"attributes\":[],\"persistent\":false,\"identifiers\":[{\"id\":15,\"type\":\"INTEGER\",\"name\":\"ID_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":16,\"type\":\"INTEGER\",\"name\":\"ID_2\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}],\"measures\":[{\"id\":17,\"type\":\"INTEGER\",\"name\":\"ME_2\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"viral\":[{\"id\":18,\"type\":\"STRING\",\"name\":\"AT_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"VIRAL\"}]},\"messages\":[],\"adataset\":true,\"acomponent\":false,\"ascalar\":false},{\"result\":{\"id\":5,\"name\":\"ds63\",\"description\":null,\"attributes\":[{\"id\":14,\"type\":\"INTEGER\",\"name\":\"AT_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"ATTRIBUTE\"}],\"persistent\":false,\"identifiers\":[{\"id\":12,\"type\":\"INTEGER\",\"name\":\"ID_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}],\"measures\":[{\"id\":13,\"type\":\"STRING\",\"name\":\"ME_1\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"viral\":[]},\"messages\":[],\"adataset\":true,\"acomponent\":false,\"ascalar\":false}]";
		List<ResultExpression> result = vtlTestUtils.getResult(commandStatements);
		System.out.println("########innerJoinAggr: "+vtlTestUtils.convertResultToJson(result));
		List<ResultExpression> resultExpressionsToComapare = vtlTestUtils.convertJsonToResultExpression(jsonResult);
		assertNotNull("Conversione non riuscita", resultExpressionsToComapare);
		assertNotNull("Validazione Semantica non riuscita", result);
		assertTrue("I due risultati non sono uguali",
				vtlTestUtils.compareResult(result.get(0), resultExpressionsToComapare.get(0)));
	}

}
