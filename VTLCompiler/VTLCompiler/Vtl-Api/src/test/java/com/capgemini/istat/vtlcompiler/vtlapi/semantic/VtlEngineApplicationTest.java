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

import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
public class VtlEngineApplicationTest {

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
	public void simpleTest() throws Exception {

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
		String jsonResult = "[{\"result\":{\"id\":44,\"name\":\"DS_r\",\"description\":null,\"attributes\":[],\"persistent\":false,\"identifiers\":[{\"id\":171,\"type\":\"INTEGER\",\"name\":\"ID_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":172,\"type\":\"STRING\",\"name\":\"ID_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}],\"measures\":[{\"id\":173,\"type\":\"INTEGER\",\"name\":\"ME_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},{\"id\":174,\"type\":\"INTEGER\",\"name\":\"ME_2\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"viral\":[]},\"messages\":[],\"command\":\"DS_r:=DS_1;\",\"acomponent\":false,\"ascalar\":false},{\"result\":{\"id\":1,\"name\":\"DS_1\",\"description\":null,\"attributes\":[],\"persistent\":true,\"identifiers\":[{\"id\":1,\"type\":\"INTEGER\",\"name\":\"ID_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":2,\"type\":\"STRING\",\"name\":\"ID_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}],\"measures\":[{\"id\":3,\"type\":\"INTEGER\",\"name\":\"ME_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},{\"id\":4,\"type\":\"INTEGER\",\"name\":\"ME_2\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"viral\":[]},\"messages\":[],\"command\":\"DS_1\",\"acomponent\":false,\"ascalar\":false}]";
		List<ResultExpression> result = vtlTestUtils.getResult(commandStatements);
		List<ResultExpression> resultExpressionsToComapare = vtlTestUtils.convertJsonToResultExpression(jsonResult);
		assertNotNull("Conversione non riuscita", resultExpressionsToComapare);
		assertNotNull("Validazione Semantica non riuscita", result);
		assertTrue("I due risultati non sono uguali",
				vtlTestUtils.compareResult(result.get(0), resultExpressionsToComapare.get(0)));

	}

}
