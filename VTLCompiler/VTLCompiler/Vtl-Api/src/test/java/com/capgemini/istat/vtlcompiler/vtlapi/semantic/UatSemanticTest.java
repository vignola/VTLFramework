package com.capgemini.istat.vtlcompiler.vtlapi.semantic;

import com.capgemini.istat.vtlcompiler.vtlapi.utils.VtlTestUtils;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.dataset.VtlComponentRole;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.dataset.VtlDataset;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.dataset.VtlType;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.exception.ValidationException;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.semantic.ResultExpression;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.valuedomain.ValueDomain;
import com.capgemini.istat.vtlcompiler.vtlcommon.repository.DatasetRepository;
import com.capgemini.istat.vtlcompiler.vtlcommon.repository.ValueDomainRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UatSemanticTest {
	private VtlTestUtils vtlTestUtils;
	private DatasetRepository datasetRepository;
	private ValueDomainRepository valueDomainRepository;
    public static final Logger logger = LogManager.getLogger(UatSemanticTest.class);


	@Autowired
	public void setVtlTestUtils(VtlTestUtils vtlTestUtils) {
		this.vtlTestUtils = vtlTestUtils;
	}

	@Autowired
	public void setDatasetRepository(DatasetRepository datasetRepository) {
		this.datasetRepository = datasetRepository;
	}
	
	@Autowired
    public void setValueDomainRepository(ValueDomainRepository valueDomainRepository) {
        this.valueDomainRepository = valueDomainRepository;
    }
	
	@Test
	@Transactional
	public void uatMonthlyMilk() throws Exception {
		VtlDataset vtlDatasetFind = datasetRepository.findByNameIgnoreCase("DS_1");
		if (vtlDatasetFind == null) {
			VtlDataset vtlDataset = new VtlDataset();
			vtlDataset.setPersistent(true);
			vtlDataset.setName("DS_micro");
			vtlDataset.setIsOnlyAScalar(false);
			vtlDataset.setTransitory(false);
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("YEAR", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("MONTH", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("IDENT", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("WEIGHT", VtlType.NUMBER, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("M011", VtlType.NUMBER, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("M012", VtlType.NUMBER, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("M013", VtlType.NUMBER, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("M021", VtlType.NUMBER, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("M022", VtlType.NUMBER, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("M023", VtlType.NUMBER, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("M031", VtlType.NUMBER, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("M032", VtlType.NUMBER, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("M033", VtlType.NUMBER, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("M041", VtlType.NUMBER, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("M042", VtlType.NUMBER, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("M043", VtlType.NUMBER, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("M051", VtlType.NUMBER, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("M052", VtlType.NUMBER, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("M053", VtlType.NUMBER, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("M061", VtlType.NUMBER, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("M062", VtlType.NUMBER, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("M063", VtlType.NUMBER, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("M07", VtlType.NUMBER, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("M08", VtlType.NUMBER, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("M09", VtlType.NUMBER, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("M10", VtlType.NUMBER, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("M11", VtlType.NUMBER, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("M12", VtlType.NUMBER, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("M13", VtlType.NUMBER, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("M14", VtlType.NUMBER, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("M15", VtlType.NUMBER, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("M16", VtlType.NUMBER, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("M17", VtlType.NUMBER, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("M18", VtlType.NUMBER, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("M19", VtlType.NUMBER, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("M20", VtlType.NUMBER, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("M21", VtlType.NUMBER, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("M22", VtlType.NUMBER, VtlComponentRole.MEASURE));
			vtlDataset.addComponent(vtlTestUtils.getVtlComponent("M23", VtlType.NUMBER, VtlComponentRole.MEASURE));
			datasetRepository.save(vtlDataset);

            VtlDataset vtlDataset2 = new VtlDataset();
			vtlDataset2.setPersistent(true);
			vtlDataset2.setName("DS_MAP");
			vtlDataset2.setIsOnlyAScalar(false);
			vtlDataset2.setTransitory(false);
			vtlDataset2.addComponent(vtlTestUtils.getVtlComponent("ISTAT_DAIRYPROD_CODES", VtlType.STRING, VtlComponentRole.IDENTIFIER));
			vtlDataset2.addComponent(vtlTestUtils.getVtlComponent("DESC", VtlType.STRING, VtlComponentRole.MEASURE));
			vtlDataset2.addComponent(vtlTestUtils.getVtlComponent("DIM_CL_H_DAIRYPROD_A", VtlType.STRING, VtlComponentRole.MEASURE));
			datasetRepository.save(vtlDataset2);

            //AGGIUNTA VALUE DOMAIN
			String[] cl_var_codes = {"M011","M012","M013","M021","M022","M023","M031","M032","M033","M041","M042","M043","M051","M052",
					"M053","M061","M062","M063","M07","M08","M09","M10","M11"};
			List<String> codes = new ArrayList<String>();
			Collections.addAll(codes, cl_var_codes);
			this.addValueDom(valueDomainRepository, "CL_VAR", codes);
		}
        String commandStatements = "DS_unpivot:=DS_micro[drop weight][unpivot ISTAT_DAIRYPROD_CODES, OBS_VALUE];\n" +
                "DS_weighted:=DS_unpivot*DS_micro[rename weight to OBS_VALUE]#OBS_VALUE;\n" +
                "DS_calc:= inner_join(DS_unpivot,DS_MAP using ISTAT_DAIRYPROD_CODES calc identifier 'time_period':=year || month, identifier ref_area:=\"IT\", identifier FREQ:= \"M\", identifier DIM_CL_H_DAIRYPROD_A:= ISTAT_DAIRYPROD_CODES keep OBS_VALUE);\n" +
                "DS_macro:=sum(DS_micro group by 'time_period', ref_area, FREQ, DIM_CL_H_DAIRYPROD_A);\n" +
                "DS_exists:=Exists_in(DS_mandatory, DS_macro, false);\n" +
                "DS_r2:=check(not isnull(fill_time_series(inner_join(DS_macro,DS_mandatory),all) ) errorcode \"2\" invalid );\n" +
                "DS_r3:= check((DS_macro#OBS_VALUE >= 3 or DS_macro#OBS_VALUE >=4.8) and DIM_CL_H_DAIRYPROD_A=\"D1110D_T\"  errorcode \"3\" invalid);\n" +
                "DS_r4:= check((DS_macro#OBS_VALUE < 2.7 or DS_macr#OBS_VALUE > 4) and DIM_CL_H_DAIRYPROD_A=\"D1110D_P\"  errorcode \"4\" invalid );\n" +
                "DS_r :=union( DS_r2[calc identifier ruleID:='errorcode']#ruleID,  DS_r3[calc identifier ruleID:='errorcode']#ruleID , DS_r4[calc identifier ruleID:='errorcode']#ruleID);\n" +
                "DS_error:=cross_join(DS_new_structure, DS_r, DS_map filter DS_new_structure#TIME_PERIOD= DS_r#TIME_PERIOD and DS_new_structure#DIM_CL_H_DAIRYPROD_A =DS_r#DIM_CL_H_DAIRYPROD_A);\n" +
                "DS_UWM:=DS_micro[sub MAT_PROD= \"3\"]#FAT_DRY;\n" +
                "DS_join:=left_join(DS_micro as DS1,DS_UWM as DS2 calc PRO:=DS1#QUANTITY, FAT:=DS1#QUANTITY*DS#'1FAT_DRY', UWM:=(DS1#QUANTITY*DS#'1FAT_DRY') * DS2#FAT_DRY, USM:=((DS1#QUANTITY/DS1#FAT_ITIS)*100)-((DS1#QUANTITY*DS#'1FAT_DRY')* DS2#FAT_DRY), PRO2:=DS1#QUANTITY, OUT:= DS1#QUANTITY* DS1#PROTEIN keep PRO, FAT, UWM,USM,PRO2,OUT);\n" +
                "DS_unpivot:=DS_join [unpivot DIM_CL_H_DAIRYPROD_BHREV, OBS_VALUE];\n";




		String jsonResult = "";
        try {
            List<ResultExpression> result = vtlTestUtils.getResult(commandStatements);
            List<ResultExpression> resultExpressionsToComapare = vtlTestUtils.convertJsonToResultExpression(jsonResult);
            assertNotNull("Conversione non riuscita", resultExpressionsToComapare);
            assertNotNull("Validazione Semantica non riuscita", result);
            assertTrue("I due risultati non sono uguali",
                    vtlTestUtils.compareResult(result.get(0), resultExpressionsToComapare.get(0)));
        } catch (ValidationException validationException) {
            logger.info("ERRORE -> " + validationException.getSemanticMessage().getMessage());
        }

	}


	public void addValueDom(ValueDomainRepository valueDomainRepository, String name, List<String> codes) {
		ValueDomain valueDomain = null;
		for (String code : codes) {
			valueDomain = new ValueDomain();
			valueDomain.setCode(code);
        	valueDomain.setValueDomainName(name);
        	valueDomain.setDescription("");
        	valueDomainRepository.save(valueDomain);
		}
	}
}
