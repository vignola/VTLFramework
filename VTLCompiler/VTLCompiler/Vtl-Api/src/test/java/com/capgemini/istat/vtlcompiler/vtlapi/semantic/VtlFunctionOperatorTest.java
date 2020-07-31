package com.capgemini.istat.vtlcompiler.vtlapi.semantic;

import com.capgemini.istat.vtlcompiler.vtlapi.utils.VtlTestUtils;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.dataset.VtlComponent;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.dataset.VtlComponentRole;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.dataset.VtlDataset;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.dataset.VtlType;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.define.UserFunction;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.define.VtlUserFunctionType;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.semantic.ResultExpression;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.valuedomain.ValueDomain;
import com.capgemini.istat.vtlcompiler.vtlcommon.repository.DatasetRepository;
import com.capgemini.istat.vtlcompiler.vtlcommon.repository.UserFunctionRepository;
import com.capgemini.istat.vtlcompiler.vtlcommon.repository.ValueDomainRepository;
import com.capgemini.istat.vtlcompiler.vtlsemantic.service.SemanticService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertNotNull;


@RunWith(SpringRunner.class)
@SpringBootTest
public class VtlFunctionOperatorTest {

    private UserFunctionRepository userFunctionRepository;
    private VtlTestUtils vtlTestUtils;
    private DatasetRepository datasetRepository;
    private ValueDomainRepository valueDomainRepository;
    public static final Logger logger = LogManager.getLogger(SemanticService.class);

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

    @Autowired
    public void setValueDomainRepository(ValueDomainRepository valueDomainRepository) {
        this.valueDomainRepository = valueDomainRepository;
    }


    @Test
    @Transactional
    public void testParametroDuplicato() throws Exception {
        UserFunction userFunction = new UserFunction();
        userFunction.setFunctionName("sum_int");
        userFunction.setFunctionType(VtlUserFunctionType.OPERATOR_FUNCTION);
        String userDefinition = "define operator sum_int (x integer, x integer) " + "returns integer " + "is x + x " + "end operator;";
        userFunction.setFunctionContent(userDefinition);
        userFunctionRepository.save(userFunction);
        try {
            List<ResultExpression> resultDefinition = vtlTestUtils.getResult(userDefinition);
            assertTrue("contollo semantico fallito", false);
        } catch (Exception exception) {

            logger.info(exception);
        }
    }

    @Test
    @Transactional
    public void testParametroValueDomain() throws Exception {
        UserFunction userFunction = new UserFunction();
        userFunction.setFunctionName("sum_int");
        userFunction.setFunctionType(VtlUserFunctionType.OPERATOR_FUNCTION);
        String userDefinition = "define operator sum_int (x valueDomain, y integer) returns integer is x + y end operator;";
        userFunction.setFunctionContent(userDefinition);
        userFunctionRepository.save(userFunction);
        try {
            List<ResultExpression> resultDefinition = vtlTestUtils.getResult(userDefinition);
            assertTrue("contollo semantico fallito", false);
        } catch (Exception exception) {

            logger.info(exception);
        }
    }

    @Test
    @Transactional
    public void testParametroOpzionale() throws Exception {
        UserFunction userFunction = new UserFunction();
        userFunction.setFunctionName("sum_int");
        userFunction.setFunctionType(VtlUserFunctionType.OPERATOR_FUNCTION);
        String userDefinition = "define operator sum_int (x integer, y integer) returns integer is x + y end operator;";
        userFunction.setFunctionContent(userDefinition);
        userFunctionRepository.save(userFunction);
        try {
            List<ResultExpression> resultDefinition = vtlTestUtils.getResult(userDefinition);
            String commandStatements = "DS_r := sum_int(2);";
            resultDefinition = vtlTestUtils.getResult(commandStatements);

            assertTrue("contollo semantico fallito", false);
        } catch (Exception exception) {

            logger.info(exception);
        }
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
        String jsonResult = "[{\"result\":{\"id\":12,\"name\":\"DS_r\",\"description\":null,\"attributes\":[],\"persistent\":false,\"measures\":[{\"id\":6,\"type\":\"INTEGER\",\"name\":\"int_var\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"identifiers\":[],\"viral\":[]},\"resultComponent\":{\"id\":6,\"type\":\"INTEGER\",\"name\":\"int_var\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},\"messages\":[],\"command\":\"DS_r:=sum_int(2,3)\",\"acomponent\":false,\"ascalar\":true,\"scalarComponent\":{\"id\":6,\"type\":\"INTEGER\",\"name\":\"int_var\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},\"adataset\":false},{\"result\":{\"id\":10,\"name\":\"temporary_9\",\"description\":\"temporary_5\",\"attributes\":[],\"persistent\":false,\"measures\":[{\"id\":5,\"type\":\"INTEGER\",\"name\":\"int_var\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"identifiers\":[],\"viral\":[]},\"resultComponent\":{\"id\":5,\"type\":\"INTEGER\",\"name\":\"int_var\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},\"messages\":[],\"command\":\"sum_int(2,3)\",\"acomponent\":false,\"ascalar\":true,\"scalarComponent\":{\"id\":5,\"type\":\"INTEGER\",\"name\":\"int_var\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},\"adataset\":false},{\"result\":{\"id\":6,\"name\":\"temporary_5\",\"description\":null,\"attributes\":[],\"persistent\":false,\"measures\":[{\"id\":3,\"type\":\"INTEGER\",\"name\":\"int_var\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"identifiers\":[],\"viral\":[]},\"resultComponent\":{\"id\":3,\"type\":\"INTEGER\",\"name\":\"int_var\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},\"messages\":[],\"command\":\"x+y\",\"acomponent\":false,\"ascalar\":true,\"scalarComponent\":{\"id\":3,\"type\":\"INTEGER\",\"name\":\"int_var\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},\"adataset\":false},{\"result\":{\"id\":2,\"name\":\"temporary_1\",\"description\":null,\"attributes\":[],\"persistent\":false,\"measures\":[{\"id\":1,\"type\":\"INTEGER\",\"name\":\"int_var\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"identifiers\":[],\"viral\":[]},\"resultComponent\":{\"id\":1,\"type\":\"INTEGER\",\"name\":\"int_var\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},\"messages\":[],\"command\":\"x\",\"acomponent\":false,\"ascalar\":true,\"scalarComponent\":{\"id\":1,\"type\":\"INTEGER\",\"name\":\"int_var\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},\"adataset\":false},{\"result\":{\"id\":4,\"name\":\"temporary_3\",\"description\":null,\"attributes\":[],\"persistent\":false,\"measures\":[{\"id\":2,\"type\":\"INTEGER\",\"name\":\"int_var\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"identifiers\":[],\"viral\":[]},\"resultComponent\":{\"id\":2,\"type\":\"INTEGER\",\"name\":\"int_var\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},\"messages\":[],\"command\":\"y\",\"acomponent\":false,\"ascalar\":true,\"scalarComponent\":{\"id\":2,\"type\":\"INTEGER\",\"name\":\"int_var\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},\"adataset\":false}]";

        List<ResultExpression> result = vtlTestUtils.getResult(commandStatements);
        logger.info(vtlTestUtils.convertResultToJson(result));
        List<ResultExpression> resultExpressionsToCompare = vtlTestUtils.convertJsonToResultExpression(jsonResult);
        assertNotNull("Conversione non riuscita", resultExpressionsToCompare);
        assertNotNull("Validazione Semantica non riuscita", result);
        Assert.assertTrue("I due risultati non sono uguali",
                vtlTestUtils.compareResult(result.get(0), resultExpressionsToCompare.get(0)));
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
        String jsonResult = "[{\"result\":{\"id\":12,\"name\":\"DS_r\",\"description\":null,\"attributes\":[],\"persistent\":false,\"measures\":[{\"id\":6,\"type\":\"INTEGER\",\"name\":\"int_var\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"identifiers\":[],\"viral\":[]},\"resultComponent\":{\"id\":6,\"type\":\"INTEGER\",\"name\":\"int_var\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},\"messages\":[],\"command\":\"DS_r:=sum_int(2,3)\",\"acomponent\":false,\"ascalar\":true,\"scalarComponent\":{\"id\":6,\"type\":\"INTEGER\",\"name\":\"int_var\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},\"adataset\":false},{\"result\":{\"id\":10,\"name\":\"temporary_9\",\"description\":\"temporary_5\",\"attributes\":[],\"persistent\":false,\"measures\":[{\"id\":5,\"type\":\"INTEGER\",\"name\":\"int_var\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"identifiers\":[],\"viral\":[]},\"resultComponent\":{\"id\":5,\"type\":\"INTEGER\",\"name\":\"int_var\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},\"messages\":[],\"command\":\"sum_int(2,3)\",\"acomponent\":false,\"ascalar\":true,\"scalarComponent\":{\"id\":5,\"type\":\"INTEGER\",\"name\":\"int_var\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},\"adataset\":false},{\"result\":{\"id\":6,\"name\":\"temporary_5\",\"description\":null,\"attributes\":[],\"persistent\":false,\"measures\":[{\"id\":3,\"type\":\"INTEGER\",\"name\":\"int_var\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"identifiers\":[],\"viral\":[]},\"resultComponent\":{\"id\":3,\"type\":\"INTEGER\",\"name\":\"int_var\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},\"messages\":[],\"command\":\"x+y\",\"acomponent\":false,\"ascalar\":true,\"scalarComponent\":{\"id\":3,\"type\":\"INTEGER\",\"name\":\"int_var\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},\"adataset\":false},{\"result\":{\"id\":2,\"name\":\"temporary_1\",\"description\":null,\"attributes\":[],\"persistent\":false,\"measures\":[{\"id\":1,\"type\":\"INTEGER\",\"name\":\"int_var\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"identifiers\":[],\"viral\":[]},\"resultComponent\":{\"id\":1,\"type\":\"INTEGER\",\"name\":\"int_var\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},\"messages\":[],\"command\":\"x\",\"acomponent\":false,\"ascalar\":true,\"scalarComponent\":{\"id\":1,\"type\":\"INTEGER\",\"name\":\"int_var\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},\"adataset\":false},{\"result\":{\"id\":4,\"name\":\"temporary_3\",\"description\":null,\"attributes\":[],\"persistent\":false,\"measures\":[{\"id\":2,\"type\":\"INTEGER\",\"name\":\"int_var\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"identifiers\":[],\"viral\":[]},\"resultComponent\":{\"id\":2,\"type\":\"INTEGER\",\"name\":\"int_var\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},\"messages\":[],\"command\":\"y\",\"acomponent\":false,\"ascalar\":true,\"scalarComponent\":{\"id\":2,\"type\":\"INTEGER\",\"name\":\"int_var\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},\"adataset\":false}]";

        List<ResultExpression> result = vtlTestUtils.getResult(commandStatements);
        logger.info(vtlTestUtils.convertResultToJson(result));
        List<ResultExpression> resultExpressionsToCompare = vtlTestUtils.convertJsonToResultExpression(jsonResult);
        assertNotNull("Conversione non riuscita", resultExpressionsToCompare);
        assertNotNull("Validazione Semantica non riuscita", result);
        Assert.assertTrue("I due risultati non sono uguali",
                vtlTestUtils.compareResult(result.get(0), resultExpressionsToCompare.get(0)));
    }


    @Test
    @Transactional
    public void callSumIntDefaultSecond() throws Exception {


        UserFunction userFunction = new UserFunction();
        userFunction.setFunctionName("sum_int");
        userFunction.setFunctionType(VtlUserFunctionType.OPERATOR_FUNCTION);
        userFunction.setFunctionContent("define operator sum_int (x integer default 0, y integer default 0) " + "returns integer " + "is x + y " + "end operator;");
        userFunctionRepository.save(userFunction);
        String jsonResult = "[{\"result\":{\"id\":12,\"name\":\"DS_r\",\"description\":null,\"attributes\":[],\"persistent\":false,\"measures\":[{\"id\":6,\"type\":\"INTEGER\",\"name\":\"int_var\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"identifiers\":[],\"viral\":[]},\"resultComponent\":{\"id\":6,\"type\":\"INTEGER\",\"name\":\"int_var\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},\"messages\":[],\"command\":\"DS_r:=sum_int(2,3)\",\"acomponent\":false,\"ascalar\":true,\"scalarComponent\":{\"id\":6,\"type\":\"INTEGER\",\"name\":\"int_var\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},\"adataset\":false},{\"result\":{\"id\":10,\"name\":\"temporary_9\",\"description\":\"temporary_5\",\"attributes\":[],\"persistent\":false,\"measures\":[{\"id\":5,\"type\":\"INTEGER\",\"name\":\"int_var\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"identifiers\":[],\"viral\":[]},\"resultComponent\":{\"id\":5,\"type\":\"INTEGER\",\"name\":\"int_var\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},\"messages\":[],\"command\":\"sum_int(2,3)\",\"acomponent\":false,\"ascalar\":true,\"scalarComponent\":{\"id\":5,\"type\":\"INTEGER\",\"name\":\"int_var\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},\"adataset\":false},{\"result\":{\"id\":6,\"name\":\"temporary_5\",\"description\":null,\"attributes\":[],\"persistent\":false,\"measures\":[{\"id\":3,\"type\":\"INTEGER\",\"name\":\"int_var\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"identifiers\":[],\"viral\":[]},\"resultComponent\":{\"id\":3,\"type\":\"INTEGER\",\"name\":\"int_var\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},\"messages\":[],\"command\":\"x+y\",\"acomponent\":false,\"ascalar\":true,\"scalarComponent\":{\"id\":3,\"type\":\"INTEGER\",\"name\":\"int_var\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},\"adataset\":false},{\"result\":{\"id\":2,\"name\":\"temporary_1\",\"description\":null,\"attributes\":[],\"persistent\":false,\"measures\":[{\"id\":1,\"type\":\"INTEGER\",\"name\":\"int_var\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"identifiers\":[],\"viral\":[]},\"resultComponent\":{\"id\":1,\"type\":\"INTEGER\",\"name\":\"int_var\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},\"messages\":[],\"command\":\"x\",\"acomponent\":false,\"ascalar\":true,\"scalarComponent\":{\"id\":1,\"type\":\"INTEGER\",\"name\":\"int_var\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},\"adataset\":false},{\"result\":{\"id\":4,\"name\":\"temporary_3\",\"description\":null,\"attributes\":[],\"persistent\":false,\"measures\":[{\"id\":2,\"type\":\"INTEGER\",\"name\":\"int_var\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"identifiers\":[],\"viral\":[]},\"resultComponent\":{\"id\":2,\"type\":\"INTEGER\",\"name\":\"int_var\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},\"messages\":[],\"command\":\"y\",\"acomponent\":false,\"ascalar\":true,\"scalarComponent\":{\"id\":2,\"type\":\"INTEGER\",\"name\":\"int_var\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},\"adataset\":false}]";

        String commandStatements = "DS_r := sum_int(1);";
        List<ResultExpression> result = vtlTestUtils.getResult(commandStatements);
        logger.info(vtlTestUtils.convertResultToJson(result));
        List<ResultExpression> resultExpressionsToCompare = vtlTestUtils.convertJsonToResultExpression(jsonResult);
        assertNotNull("Conversione non riuscita", resultExpressionsToCompare);
        assertNotNull("Validazione Semantica non riuscita", result);
        Assert.assertTrue("I due risultati non sono uguali",
                vtlTestUtils.compareResult(result.get(0), resultExpressionsToCompare.get(0)));

    }

    @Test
    @Transactional
    public void callSumDatasetInt() throws Exception {

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

        UserFunction userFunction = new UserFunction();
        userFunction.setFunctionName("sum_dataset_int");
        userFunction.setFunctionType(VtlUserFunctionType.OPERATOR_FUNCTION);
        userFunction.setFunctionContent("define operator sum_dataset_int (x dataset, y integer default 0) " + "returns dataset " + "is x + y " + "end operator;");
        userFunctionRepository.save(userFunction);
        String commandStatements = "DS_r := sum_dataset_int(DS_2, 3);";

        String jsonResult = "[{\"result\":{\"id\":9,\"name\":\"DS_r\",\"description\":null,\"attributes\":[],\"persistent\":false,\"viral\":[{\"id\":17,\"type\":\"STRING\",\"name\":\"AT_1\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"VIRAL\"}],\"identifiers\":[{\"id\":15,\"type\":\"INTEGER\",\"name\":\"ID_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":16,\"type\":\"STRING\",\"name\":\"ID_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}],\"measures\":[{\"id\":13,\"type\":\"INTEGER\",\"name\":\"ME_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},{\"id\":14,\"type\":\"INTEGER\",\"name\":\"ME_2\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"}]},\"messages\":[],\"command\":\"DS_r:=sum_dataset_int(DS_2,3)\",\"adataset\":true,\"acomponent\":false,\"ascalar\":false},{\"result\":{\"id\":7,\"name\":\"temporary_6\",\"description\":null,\"attributes\":[],\"persistent\":false,\"viral\":[{\"id\":12,\"type\":\"STRING\",\"name\":\"AT_1\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"VIRAL\"}],\"identifiers\":[{\"id\":10,\"type\":\"INTEGER\",\"name\":\"ID_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":11,\"type\":\"STRING\",\"name\":\"ID_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}],\"measures\":[{\"id\":8,\"type\":\"INTEGER\",\"name\":\"ME_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},{\"id\":9,\"type\":\"INTEGER\",\"name\":\"ME_2\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"}]},\"messages\":[],\"command\":\"sum_dataset_int(DS_2,3)\",\"adataset\":true,\"acomponent\":false,\"ascalar\":false},{\"result\":{\"id\":1,\"name\":\"DS_2\",\"description\":null,\"attributes\":[],\"persistent\":true,\"viral\":[{\"id\":5,\"type\":\"STRING\",\"name\":\"AT_1\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"VIRAL\"}],\"identifiers\":[{\"id\":1,\"type\":\"INTEGER\",\"name\":\"ID_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":2,\"type\":\"STRING\",\"name\":\"ID_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}],\"measures\":[{\"id\":3,\"type\":\"INTEGER\",\"name\":\"ME_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},{\"id\":4,\"type\":\"INTEGER\",\"name\":\"ME_2\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"}]},\"messages\":[],\"command\":\"x\",\"adataset\":true,\"acomponent\":false,\"ascalar\":false},{\"result\":{\"id\":3,\"name\":\"temporary_2\",\"description\":null,\"attributes\":[],\"persistent\":false,\"viral\":[],\"identifiers\":[],\"measures\":[{\"id\":6,\"type\":\"INTEGER\",\"name\":\"int_var\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"}]},\"resultComponent\":{\"id\":6,\"type\":\"INTEGER\",\"name\":\"int_var\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},\"messages\":[],\"command\":\"y\",\"adataset\":false,\"acomponent\":false,\"scalarComponent\":{\"id\":6,\"type\":\"INTEGER\",\"name\":\"int_var\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},\"ascalar\":true}]";

        List<ResultExpression> result = vtlTestUtils.getResult(commandStatements);
        logger.info(vtlTestUtils.convertResultToJson(result));
        List<ResultExpression> resultExpressionsToCompare = vtlTestUtils.convertJsonToResultExpression(jsonResult);
        assertNotNull("Conversione non riuscita", resultExpressionsToCompare);
        assertNotNull("Validazione Semantica non riuscita", result);
        Assert.assertTrue("I due risultati non sono uguali",
                vtlTestUtils.compareResult(result.get(0), resultExpressionsToCompare.get(0)));
    }

    @Test
    @Transactional
    public void callSumDataset() throws Exception {
        VtlDataset vtlDatasetFind = datasetRepository.findByNameIgnoreCase("DS_1");
        if (vtlDatasetFind == null) {
            VtlDataset vtlDataset = new VtlDataset();

            vtlDataset.setPersistent(true);
            vtlDataset.setName("DS_58");
            vtlDataset.setIsOnlyAScalar(false);
            vtlDataset.setTransitory(false);
            vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_1", VtlType.INTEGER, VtlComponentRole.IDENTIFIER));
            vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ID_2", VtlType.STRING, VtlComponentRole.IDENTIFIER));
            vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_1", VtlType.INTEGER, VtlComponentRole.MEASURE));
            vtlDataset.addComponent(vtlTestUtils.getVtlComponent("ME_2", VtlType.INTEGER, VtlComponentRole.MEASURE));
            vtlDataset.addComponent(vtlTestUtils.getVtlComponent("AT_1", VtlType.STRING, VtlComponentRole.VIRAL));
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

        String commandStatements = "DS_r := sum_dataset(DS_58, DS_2);";
        String jsonResult = "[{\"result\":{\"id\":6,\"name\":\"DS_r\",\"description\":null,\"attributes\":[],\"persistent\":false,\"identifiers\":[{\"id\":16,\"type\":\"INTEGER\",\"name\":\"ID_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":17,\"type\":\"STRING\",\"name\":\"ID_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}],\"measures\":[{\"id\":18,\"type\":\"INTEGER\",\"name\":\"ME_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},{\"id\":19,\"type\":\"INTEGER\",\"name\":\"ME_2\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"viral\":[{\"id\":20,\"type\":\"STRING\",\"name\":\"AT_1\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"VIRAL\"}]},\"messages\":[],\"command\":\"DS_r:=sum_dataset(DS_58,DS_2)\",\"adataset\":true,\"ascalar\":false,\"acomponent\":false},{\"result\":{\"id\":4,\"name\":\"temporary_3\",\"description\":null,\"attributes\":[],\"persistent\":false,\"identifiers\":[{\"id\":11,\"type\":\"INTEGER\",\"name\":\"ID_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":12,\"type\":\"STRING\",\"name\":\"ID_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}],\"measures\":[{\"id\":13,\"type\":\"INTEGER\",\"name\":\"ME_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},{\"id\":14,\"type\":\"INTEGER\",\"name\":\"ME_2\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"viral\":[{\"id\":15,\"type\":\"STRING\",\"name\":\"AT_1\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"VIRAL\"}]},\"messages\":[],\"command\":\"sum_dataset(DS_58,DS_2)\",\"adataset\":true,\"ascalar\":false,\"acomponent\":false},{\"result\":{\"id\":1,\"name\":\"DS_58\",\"description\":null,\"attributes\":[],\"persistent\":true,\"identifiers\":[{\"id\":1,\"type\":\"INTEGER\",\"name\":\"ID_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":2,\"type\":\"STRING\",\"name\":\"ID_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}],\"measures\":[{\"id\":3,\"type\":\"INTEGER\",\"name\":\"ME_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},{\"id\":4,\"type\":\"INTEGER\",\"name\":\"ME_2\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"viral\":[{\"id\":5,\"type\":\"STRING\",\"name\":\"AT_1\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"VIRAL\"}]},\"messages\":[],\"command\":\"x\",\"adataset\":true,\"ascalar\":false,\"acomponent\":false},{\"result\":{\"id\":2,\"name\":\"DS_2\",\"description\":null,\"attributes\":[],\"persistent\":true,\"identifiers\":[{\"id\":6,\"type\":\"INTEGER\",\"name\":\"ID_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":7,\"type\":\"STRING\",\"name\":\"ID_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}],\"measures\":[{\"id\":8,\"type\":\"INTEGER\",\"name\":\"ME_1\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},{\"id\":9,\"type\":\"INTEGER\",\"name\":\"ME_2\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"viral\":[{\"id\":10,\"type\":\"STRING\",\"name\":\"AT_1\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"VIRAL\"}]},\"messages\":[],\"command\":\"y\",\"adataset\":true,\"ascalar\":false,\"acomponent\":false}]";

        List<ResultExpression> result = vtlTestUtils.getResult(commandStatements);
        logger.info(vtlTestUtils.convertResultToJson(result));
        List<ResultExpression> resultExpressionsToCompare = vtlTestUtils.convertJsonToResultExpression(jsonResult);
        assertNotNull("Conversione non riuscita", resultExpressionsToCompare);
        assertNotNull("Validazione Semantica non riuscita", result);
        Assert.assertTrue("I due risultati non sono uguali",
                vtlTestUtils.compareResult(result.get(0), resultExpressionsToCompare.get(0)));

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
        String jsonResult = "[{\"result\":{\"id\":12,\"name\":\"DS_r\",\"description\":null,\"attributes\":[],\"persistent\":false,\"measures\":[{\"id\":6,\"type\":\"INTEGER\",\"name\":\"int_var\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"identifiers\":[],\"viral\":[]},\"resultComponent\":{\"id\":6,\"type\":\"INTEGER\",\"name\":\"int_var\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},\"messages\":[],\"command\":\"DS_r:=sum_int(2,3)\",\"acomponent\":false,\"ascalar\":true,\"scalarComponent\":{\"id\":6,\"type\":\"INTEGER\",\"name\":\"int_var\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},\"adataset\":false},{\"result\":{\"id\":10,\"name\":\"temporary_9\",\"description\":\"temporary_5\",\"attributes\":[],\"persistent\":false,\"measures\":[{\"id\":5,\"type\":\"INTEGER\",\"name\":\"int_var\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"identifiers\":[],\"viral\":[]},\"resultComponent\":{\"id\":5,\"type\":\"INTEGER\",\"name\":\"int_var\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},\"messages\":[],\"command\":\"sum_int(2,3)\",\"acomponent\":false,\"ascalar\":true,\"scalarComponent\":{\"id\":5,\"type\":\"INTEGER\",\"name\":\"int_var\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},\"adataset\":false},{\"result\":{\"id\":6,\"name\":\"temporary_5\",\"description\":null,\"attributes\":[],\"persistent\":false,\"measures\":[{\"id\":3,\"type\":\"INTEGER\",\"name\":\"int_var\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"identifiers\":[],\"viral\":[]},\"resultComponent\":{\"id\":3,\"type\":\"INTEGER\",\"name\":\"int_var\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},\"messages\":[],\"command\":\"x+y\",\"acomponent\":false,\"ascalar\":true,\"scalarComponent\":{\"id\":3,\"type\":\"INTEGER\",\"name\":\"int_var\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},\"adataset\":false},{\"result\":{\"id\":2,\"name\":\"temporary_1\",\"description\":null,\"attributes\":[],\"persistent\":false,\"measures\":[{\"id\":1,\"type\":\"INTEGER\",\"name\":\"int_var\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"identifiers\":[],\"viral\":[]},\"resultComponent\":{\"id\":1,\"type\":\"INTEGER\",\"name\":\"int_var\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},\"messages\":[],\"command\":\"x\",\"acomponent\":false,\"ascalar\":true,\"scalarComponent\":{\"id\":1,\"type\":\"INTEGER\",\"name\":\"int_var\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},\"adataset\":false},{\"result\":{\"id\":4,\"name\":\"temporary_3\",\"description\":null,\"attributes\":[],\"persistent\":false,\"measures\":[{\"id\":2,\"type\":\"INTEGER\",\"name\":\"int_var\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"identifiers\":[],\"viral\":[]},\"resultComponent\":{\"id\":2,\"type\":\"INTEGER\",\"name\":\"int_var\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},\"messages\":[],\"command\":\"y\",\"acomponent\":false,\"ascalar\":true,\"scalarComponent\":{\"id\":2,\"type\":\"INTEGER\",\"name\":\"int_var\",\"domainValue\":\"int_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},\"adataset\":false}]";

        List<ResultExpression> result = vtlTestUtils.getResult(commandStatements);
        logger.info(vtlTestUtils.convertResultToJson(result));
        List<ResultExpression> resultExpressionsToCompare = vtlTestUtils.convertJsonToResultExpression(jsonResult);
        assertNotNull("Conversione non riuscita", resultExpressionsToCompare);
        assertNotNull("Validazione Semantica non riuscita", result);
        Assert.assertTrue("I due risultati non sono uguali",
                vtlTestUtils.compareResult(result.get(0), resultExpressionsToCompare.get(0)));

    }


    @Test
    @Transactional
    public void testValueDomainNonEsistente() throws Exception {
        UserFunction userFunction = new UserFunction();
        userFunction.setFunctionName("dpr_op1");
        userFunction.setFunctionType(VtlUserFunctionType.DATAPOINT_FUNCTION);
        String userDefinition = "define datapoint ruleset dpr_op1( valuedomain cl_area, cl_sex, num_vd as vd_Me1, num_vd as vd_Me2) is" +
                "rule01: when cl_area=\"IT\" and cl_sex=\"M\" then vd_Me1>0 and vd_Me2<100 errorcode \"wrong 01\" errorlevel 1;" +
                "rule02: when cl_area=\"ITA\" and cl_sex=\"M\" then vd_Me1<0 errorcode \"wrong 02\";" +
                "rule03: when cl_area=\"ITC\" and cl_sex=\"F\" then vd_Me2=-10 errorcode \"wrong 03\"" +
                "end datapoint ruleset;";
        userFunction.setFunctionContent(userDefinition);
        userFunctionRepository.save(userFunction);
        try {
            List<ResultExpression> resultDefinition = vtlTestUtils.getResult(userDefinition);
            assertTrue("contollo semantico fallito", false);
        } catch (Exception exception) {

            logger.info(exception);
        }
    }

    @Test
    @Transactional
    public void testDuplicateParameters() throws Exception {
        ValueDomain valueDomain = new ValueDomain();
        valueDomain.setCode("");
        valueDomain.setValueDomainName(VtlType.NUMBER.getDomainValue());
        valueDomain.setDescription("");
        valueDomainRepository.save(valueDomain);

        valueDomain = new ValueDomain();
        valueDomain.setCode("T");
        valueDomain.setValueDomainName("cl_sex");
        valueDomain.setDescription("");
        valueDomainRepository.save(valueDomain);


        valueDomain = new ValueDomain();
        valueDomain.setCode("M");
        valueDomain.setValueDomainName("cl_sex");
        valueDomain.setDescription("");
        valueDomainRepository.save(valueDomain);

        valueDomain = new ValueDomain();
        valueDomain.setCode("");
        valueDomain.setValueDomainName("cl_area");
        valueDomain.setDescription("");
        valueDomainRepository.save(valueDomain);

        valueDomain = new ValueDomain();
        valueDomain.setCode("");
        valueDomain.setValueDomainName("vd_me1");
        valueDomain.setDescription("");
        valueDomainRepository.save(valueDomain);

        valueDomain = new ValueDomain();
        valueDomain.setCode("");
        valueDomain.setValueDomainName("vd_me2");
        valueDomain.setDescription("");
        valueDomainRepository.save(valueDomain);
        UserFunction userFunction = new UserFunction();
        userFunction.setFunctionName("dpr_op1");
        userFunction.setFunctionType(VtlUserFunctionType.DATAPOINT_FUNCTION);
        String userDefinition = "define datapoint ruleset dpr_op1( valuedomain cl_area, cl_area, num_vd as vd_Me1, num_vd as vd_Me2) is" +
                "rule01: when cl_area=\"IT\" and cl_sex=\"M\" then vd_Me1>0 and vd_Me2<100 errorcode \"wrong 01\" errorlevel 1;" +
                "rule02: when cl_area=\"ITA\" and cl_sex=\"M\" then vd_Me1<0 errorcode \"wrong 02\";" +
                "rule03: when cl_area=\"ITC\" and cl_sex=\"F\" then vd_Me2=-10 errorcode \"wrong 03\"" +
                "end datapoint ruleset;";
        userFunction.setFunctionContent(userDefinition);
        userFunctionRepository.save(userFunction);
        try {
            List<ResultExpression> resultDefinition = vtlTestUtils.getResult(userDefinition);
            assertTrue("contollo semantico fallito", false);
        } catch (Exception exception) {

            logger.info(exception);
        }
    }

    @Test
    @Transactional
    public void checkDatapoint() throws Exception {
        setupDatapoint();

        String commandStatements = "dsr:= check_datapoint(ds_42, dpr_op1 components id_1, id_2,me_1, me_2);";

        String jsonResult = "[{\"result\":{\"id\":26,\"name\":\"dsr\",\"description\":null,\"attributes\":[],\"persistent\":false,\"identifiers\":[{\"id\":25,\"type\":\"STRING\",\"name\":\"ID_1\",\"domainValue\":\"cl_area\",\"domainValueParent\":\"cl_area\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":26,\"type\":\"STRING\",\"name\":\"ID_2\",\"domainValue\":\"cl_sex\",\"domainValueParent\":\"cl_sex\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":27,\"type\":\"STRING\",\"name\":\"ruleid\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}],\"viral\":[{\"id\":30,\"type\":\"STRING\",\"name\":\"AT_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"VIRAL\"}],\"measures\":[{\"id\":28,\"type\":\"NUMBER\",\"name\":\"ME_1\",\"domainValue\":\"num_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},{\"id\":29,\"type\":\"NUMBER\",\"name\":\"ME_2\",\"domainValue\":\"num_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},{\"id\":31,\"type\":\"STRING\",\"name\":\"errorcode\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"MEASURE\"},{\"id\":32,\"type\":\"STRING\",\"name\":\"errorlevel\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"MEASURE\"}]},\"messages\":[],\"command\":\"dsr:=check_datapoint(ds_42,dpr_op1componentsid_1,id_2,me_1,me_2)\",\"ascalar\":false,\"acomponent\":false,\"adataset\":true},{\"result\":{\"id\":24,\"name\":\"temporary_23\",\"description\":\"DS_42\",\"attributes\":[],\"persistent\":null,\"identifiers\":[{\"id\":17,\"type\":\"STRING\",\"name\":\"ID_1\",\"domainValue\":\"cl_area\",\"domainValueParent\":\"cl_area\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":18,\"type\":\"STRING\",\"name\":\"ID_2\",\"domainValue\":\"cl_sex\",\"domainValueParent\":\"cl_sex\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":19,\"type\":\"STRING\",\"name\":\"ruleid\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}],\"viral\":[{\"id\":22,\"type\":\"STRING\",\"name\":\"AT_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"VIRAL\"}],\"measures\":[{\"id\":20,\"type\":\"NUMBER\",\"name\":\"ME_1\",\"domainValue\":\"num_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},{\"id\":21,\"type\":\"NUMBER\",\"name\":\"ME_2\",\"domainValue\":\"num_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},{\"id\":23,\"type\":\"STRING\",\"name\":\"errorcode\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"MEASURE\"},{\"id\":24,\"type\":\"STRING\",\"name\":\"errorlevel\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"MEASURE\"}]},\"messages\":[],\"ascalar\":false,\"acomponent\":false,\"adataset\":true},{\"result\":{\"id\":1,\"name\":\"DS_42\",\"description\":null,\"attributes\":[{\"id\":5,\"type\":\"STRING\",\"name\":\"AT_1\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"ATTRIBUTE\"}],\"persistent\":true,\"identifiers\":[{\"id\":1,\"type\":\"STRING\",\"name\":\"ID_1\",\"domainValue\":\"cl_area\",\"domainValueParent\":\"cl_area\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":2,\"type\":\"STRING\",\"name\":\"ID_2\",\"domainValue\":\"cl_sex\",\"domainValueParent\":\"cl_sex\",\"vtlComponentRole\":\"IDENTIFIER\"}],\"viral\":[{\"id\":6,\"type\":\"STRING\",\"name\":\"AT_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"VIRAL\"}],\"measures\":[{\"id\":3,\"type\":\"NUMBER\",\"name\":\"ME_1\",\"domainValue\":\"num_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},{\"id\":4,\"type\":\"NUMBER\",\"name\":\"ME_2\",\"domainValue\":\"num_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"}]},\"messages\":[],\"command\":\"ds_42\",\"ascalar\":false,\"acomponent\":false,\"adataset\":true},{\"resultComponent\":{\"id\":1,\"type\":\"STRING\",\"name\":\"ID_1\",\"domainValue\":\"cl_area\",\"domainValueParent\":\"cl_area\",\"vtlComponentRole\":\"IDENTIFIER\"},\"messages\":[],\"command\":\"cl_area\",\"ascalar\":false,\"acomponent\":true,\"adataset\":false},{\"resultComponent\":{\"id\":2,\"type\":\"STRING\",\"name\":\"ID_2\",\"domainValue\":\"cl_sex\",\"domainValueParent\":\"cl_sex\",\"vtlComponentRole\":\"IDENTIFIER\"},\"messages\":[],\"command\":\"cl_sex\",\"ascalar\":false,\"acomponent\":true,\"adataset\":false},{\"resultComponent\":{\"id\":3,\"type\":\"NUMBER\",\"name\":\"ME_1\",\"domainValue\":\"num_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},\"messages\":[],\"command\":\"vd_Me1\",\"ascalar\":false,\"acomponent\":true,\"adataset\":false},{\"resultComponent\":{\"id\":4,\"type\":\"NUMBER\",\"name\":\"ME_2\",\"domainValue\":\"num_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},\"messages\":[],\"command\":\"vd_Me2\",\"ascalar\":false,\"acomponent\":true,\"adataset\":false}]";

        List<ResultExpression> result = vtlTestUtils.getResult(commandStatements);
        logger.info(vtlTestUtils.convertResultToJson(result));
        List<ResultExpression> resultExpressionsToCompare = vtlTestUtils.convertJsonToResultExpression(jsonResult);
        assertNotNull("Conversione non riuscita", resultExpressionsToCompare);
        assertNotNull("Validazione Semantica non riuscita", result);
        Assert.assertTrue("I due risultati non sono uguali",
                vtlTestUtils.compareResult(result.get(0), resultExpressionsToCompare.get(0)));

    }

    @Test
    @Transactional
    public void checkDatapointAll() throws Exception {
        setupDatapoint();
        String commandStatements = "dsr:= check_datapoint(ds_42, dpr_op1 components id_1, id_2,me_1, me_2 all);";
        String jsonResult = "[{\"result\":{\"id\":26,\"name\":\"dsr\",\"description\":null,\"attributes\":[],\"persistent\":false,\"identifiers\":[{\"id\":24,\"type\":\"STRING\",\"name\":\"ID_1\",\"domainValue\":\"cl_area\",\"domainValueParent\":\"cl_area\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":25,\"type\":\"STRING\",\"name\":\"ID_2\",\"domainValue\":\"cl_sex\",\"domainValueParent\":\"cl_sex\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":26,\"type\":\"STRING\",\"name\":\"ruleid\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}],\"viral\":[{\"id\":28,\"type\":\"STRING\",\"name\":\"AT_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"VIRAL\"}],\"measures\":[{\"id\":27,\"type\":\"BOOLEAN\",\"name\":\"bool_var\",\"domainValue\":\"bool_vd\",\"domainValueParent\":\"bool_vd\",\"vtlComponentRole\":\"MEASURE\"},{\"id\":29,\"type\":\"STRING\",\"name\":\"errorcode\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"MEASURE\"},{\"id\":30,\"type\":\"STRING\",\"name\":\"errorlevel\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"MEASURE\"}]},\"messages\":[],\"command\":\"dsr:=check_datapoint(ds_42,dpr_op1componentsid_1,id_2,me_1,me_2all)\",\"acomponent\":false,\"adataset\":true,\"ascalar\":false},{\"result\":{\"id\":24,\"name\":\"temporary_23\",\"description\":\"DS_42\",\"attributes\":[],\"persistent\":null,\"identifiers\":[{\"id\":17,\"type\":\"STRING\",\"name\":\"ID_1\",\"domainValue\":\"cl_area\",\"domainValueParent\":\"cl_area\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":18,\"type\":\"STRING\",\"name\":\"ID_2\",\"domainValue\":\"cl_sex\",\"domainValueParent\":\"cl_sex\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":19,\"type\":\"STRING\",\"name\":\"ruleid\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}],\"viral\":[{\"id\":21,\"type\":\"STRING\",\"name\":\"AT_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"VIRAL\"}],\"measures\":[{\"id\":20,\"type\":\"BOOLEAN\",\"name\":\"bool_var\",\"domainValue\":\"bool_vd\",\"domainValueParent\":\"bool_vd\",\"vtlComponentRole\":\"MEASURE\"},{\"id\":22,\"type\":\"STRING\",\"name\":\"errorcode\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"MEASURE\"},{\"id\":23,\"type\":\"STRING\",\"name\":\"errorlevel\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"MEASURE\"}]},\"messages\":[],\"acomponent\":false,\"adataset\":true,\"ascalar\":false},{\"result\":{\"id\":1,\"name\":\"DS_42\",\"description\":null,\"attributes\":[{\"id\":5,\"type\":\"STRING\",\"name\":\"AT_1\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"ATTRIBUTE\"}],\"persistent\":true,\"identifiers\":[{\"id\":1,\"type\":\"STRING\",\"name\":\"ID_1\",\"domainValue\":\"cl_area\",\"domainValueParent\":\"cl_area\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":2,\"type\":\"STRING\",\"name\":\"ID_2\",\"domainValue\":\"cl_sex\",\"domainValueParent\":\"cl_sex\",\"vtlComponentRole\":\"IDENTIFIER\"}],\"viral\":[{\"id\":6,\"type\":\"STRING\",\"name\":\"AT_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"VIRAL\"}],\"measures\":[{\"id\":3,\"type\":\"NUMBER\",\"name\":\"ME_1\",\"domainValue\":\"num_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},{\"id\":4,\"type\":\"NUMBER\",\"name\":\"ME_2\",\"domainValue\":\"num_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"}]},\"messages\":[],\"command\":\"ds_42\",\"acomponent\":false,\"adataset\":true,\"ascalar\":false},{\"resultComponent\":{\"id\":1,\"type\":\"STRING\",\"name\":\"ID_1\",\"domainValue\":\"cl_area\",\"domainValueParent\":\"cl_area\",\"vtlComponentRole\":\"IDENTIFIER\"},\"messages\":[],\"command\":\"cl_area\",\"acomponent\":true,\"adataset\":false,\"ascalar\":false},{\"resultComponent\":{\"id\":2,\"type\":\"STRING\",\"name\":\"ID_2\",\"domainValue\":\"cl_sex\",\"domainValueParent\":\"cl_sex\",\"vtlComponentRole\":\"IDENTIFIER\"},\"messages\":[],\"command\":\"cl_sex\",\"acomponent\":true,\"adataset\":false,\"ascalar\":false},{\"resultComponent\":{\"id\":3,\"type\":\"NUMBER\",\"name\":\"ME_1\",\"domainValue\":\"num_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},\"messages\":[],\"command\":\"vd_Me1\",\"acomponent\":true,\"adataset\":false,\"ascalar\":false},{\"resultComponent\":{\"id\":4,\"type\":\"NUMBER\",\"name\":\"ME_2\",\"domainValue\":\"num_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},\"messages\":[],\"command\":\"vd_Me2\",\"acomponent\":true,\"adataset\":false,\"ascalar\":false}]";
        List<ResultExpression> result = vtlTestUtils.getResult(commandStatements);
        logger.info(vtlTestUtils.convertResultToJson(result));
        List<ResultExpression> resultExpressionsToCompare = vtlTestUtils.convertJsonToResultExpression(jsonResult);
        assertNotNull("Conversione non riuscita", resultExpressionsToCompare);
        assertNotNull("Validazione Semantica non riuscita", result);
        Assert.assertTrue("I due risultati non sono uguali",
                vtlTestUtils.compareResult(result.get(0), resultExpressionsToCompare.get(0)));

    }

    @Test
    @Transactional
    public void checkDatapointAllMeasure() throws Exception {
        setupDatapoint();
        String commandStatements = "dsr:= check_datapoint(ds_42, dpr_op1 components id_1, id_2,me_1, me_2 all_measures);";
        String jsonResult = "[{\"result\":{\"id\":26,\"name\":\"dsr\",\"description\":null,\"attributes\":[],\"persistent\":false,\"identifiers\":[{\"id\":26,\"type\":\"STRING\",\"name\":\"ID_1\",\"domainValue\":\"cl_area\",\"domainValueParent\":\"cl_area\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":27,\"type\":\"STRING\",\"name\":\"ID_2\",\"domainValue\":\"cl_sex\",\"domainValueParent\":\"cl_sex\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":28,\"type\":\"STRING\",\"name\":\"ruleid\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}],\"measures\":[{\"id\":29,\"type\":\"NUMBER\",\"name\":\"ME_1\",\"domainValue\":\"num_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},{\"id\":30,\"type\":\"NUMBER\",\"name\":\"ME_2\",\"domainValue\":\"num_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},{\"id\":31,\"type\":\"BOOLEAN\",\"name\":\"bool_var\",\"domainValue\":\"bool_vd\",\"domainValueParent\":\"bool_vd\",\"vtlComponentRole\":\"MEASURE\"},{\"id\":33,\"type\":\"STRING\",\"name\":\"errorcode\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"MEASURE\"},{\"id\":34,\"type\":\"STRING\",\"name\":\"errorlevel\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"viral\":[{\"id\":32,\"type\":\"STRING\",\"name\":\"AT_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"VIRAL\"}]},\"messages\":[],\"command\":\"dsr:=check_datapoint(ds_42,dpr_op1componentsid_1,id_2,me_1,me_2all_measures)\",\"ascalar\":false,\"adataset\":true,\"acomponent\":false},{\"result\":{\"id\":24,\"name\":\"temporary_23\",\"description\":\"DS_42\",\"attributes\":[],\"persistent\":null,\"identifiers\":[{\"id\":17,\"type\":\"STRING\",\"name\":\"ID_1\",\"domainValue\":\"cl_area\",\"domainValueParent\":\"cl_area\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":18,\"type\":\"STRING\",\"name\":\"ID_2\",\"domainValue\":\"cl_sex\",\"domainValueParent\":\"cl_sex\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":19,\"type\":\"STRING\",\"name\":\"ruleid\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}],\"measures\":[{\"id\":20,\"type\":\"NUMBER\",\"name\":\"ME_1\",\"domainValue\":\"num_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},{\"id\":21,\"type\":\"NUMBER\",\"name\":\"ME_2\",\"domainValue\":\"num_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},{\"id\":22,\"type\":\"BOOLEAN\",\"name\":\"bool_var\",\"domainValue\":\"bool_vd\",\"domainValueParent\":\"bool_vd\",\"vtlComponentRole\":\"MEASURE\"},{\"id\":24,\"type\":\"STRING\",\"name\":\"errorcode\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"MEASURE\"},{\"id\":25,\"type\":\"STRING\",\"name\":\"errorlevel\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"viral\":[{\"id\":23,\"type\":\"STRING\",\"name\":\"AT_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"VIRAL\"}]},\"messages\":[],\"ascalar\":false,\"adataset\":true,\"acomponent\":false},{\"result\":{\"id\":1,\"name\":\"DS_42\",\"description\":null,\"attributes\":[{\"id\":5,\"type\":\"STRING\",\"name\":\"AT_1\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"ATTRIBUTE\"}],\"persistent\":true,\"identifiers\":[{\"id\":1,\"type\":\"STRING\",\"name\":\"ID_1\",\"domainValue\":\"cl_area\",\"domainValueParent\":\"cl_area\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":2,\"type\":\"STRING\",\"name\":\"ID_2\",\"domainValue\":\"cl_sex\",\"domainValueParent\":\"cl_sex\",\"vtlComponentRole\":\"IDENTIFIER\"}],\"measures\":[{\"id\":3,\"type\":\"NUMBER\",\"name\":\"ME_1\",\"domainValue\":\"num_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},{\"id\":4,\"type\":\"NUMBER\",\"name\":\"ME_2\",\"domainValue\":\"num_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"viral\":[{\"id\":6,\"type\":\"STRING\",\"name\":\"AT_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"VIRAL\"}]},\"messages\":[],\"command\":\"ds_42\",\"ascalar\":false,\"adataset\":true,\"acomponent\":false},{\"resultComponent\":{\"id\":1,\"type\":\"STRING\",\"name\":\"ID_1\",\"domainValue\":\"cl_area\",\"domainValueParent\":\"cl_area\",\"vtlComponentRole\":\"IDENTIFIER\"},\"messages\":[],\"command\":\"cl_area\",\"ascalar\":false,\"adataset\":false,\"acomponent\":true},{\"resultComponent\":{\"id\":2,\"type\":\"STRING\",\"name\":\"ID_2\",\"domainValue\":\"cl_sex\",\"domainValueParent\":\"cl_sex\",\"vtlComponentRole\":\"IDENTIFIER\"},\"messages\":[],\"command\":\"cl_sex\",\"ascalar\":false,\"adataset\":false,\"acomponent\":true},{\"resultComponent\":{\"id\":3,\"type\":\"NUMBER\",\"name\":\"ME_1\",\"domainValue\":\"num_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},\"messages\":[],\"command\":\"vd_Me1\",\"ascalar\":false,\"adataset\":false,\"acomponent\":true},{\"resultComponent\":{\"id\":4,\"type\":\"NUMBER\",\"name\":\"ME_2\",\"domainValue\":\"num_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},\"messages\":[],\"command\":\"vd_Me2\",\"ascalar\":false,\"adataset\":false,\"acomponent\":true}]";
        List<ResultExpression> result = vtlTestUtils.getResult(commandStatements);
        logger.info(vtlTestUtils.convertResultToJson(result));
        List<ResultExpression> resultExpressionsToCompare = vtlTestUtils.convertJsonToResultExpression(jsonResult);
        assertNotNull("Conversione non riuscita", resultExpressionsToCompare);
        assertNotNull("Validazione Semantica non riuscita", result);
        Assert.assertTrue("I due risultati non sono uguali",
                vtlTestUtils.compareResult(result.get(0), resultExpressionsToCompare.get(0)));

    }


    @Test
    @Transactional
    public void testRulesetValudeDomainNotExists() throws Exception {
        UserFunction userFunction = new UserFunction();
        userFunction.setFunctionName("dpr_op1");
        userFunction.setFunctionType(VtlUserFunctionType.DATAPOINT_FUNCTION);
        String userDefinition = "define hierarchical ruleset hr_op1 ( valuedomain condition cl_area rule cl_sex) is" +
                "rule01: when cl_area=\"IT\" then T=M+F errorcode \"wrong 01\" errorlevel 1;" +
                "rule02: when cl_area=\"ITA\" then T>M+F  errorcode \"wrong 02\";" +
                "rule03: T>M errorcode \"wrong 03\";" +
                "rule04: T>F errorcode \"wrong 04\" errorlevel 2" +
                "end hierarchical ruleset;";
        userFunction.setFunctionContent(userDefinition);
        userFunctionRepository.save(userFunction);
        try {
            List<ResultExpression> resultDefinition = vtlTestUtils.getResult(userDefinition);
            assertTrue("contollo semantico fallito", false);
        } catch (Exception exception) {

            logger.info(exception);
        }
    }

    @Test
    @Transactional
    public void testRulesetDomainValueNotExists() throws Exception {
        ValueDomain valueDomain = new ValueDomain();
        valueDomain.setCode("T");
        valueDomain.setValueDomainName("cl_sex");
        valueDomain.setDescription("");
        valueDomainRepository.save(valueDomain);

        valueDomain = new ValueDomain();
        valueDomain.setCode("M");
        valueDomain.setValueDomainName("cl_sex");
        valueDomain.setDescription("");
        valueDomainRepository.save(valueDomain);

        valueDomain = new ValueDomain();
        valueDomain.setCode("");
        valueDomain.setValueDomainName("cl_area");
        valueDomain.setDescription("");
        valueDomainRepository.save(valueDomain);

        UserFunction userFunction = new UserFunction();
        userFunction.setFunctionName("hr_op1");
        userFunction.setFunctionType(VtlUserFunctionType.HIERARCHICAL_FUNCTION);
        String userDefinition = "define hierarchical ruleset hr_op1 ( valuedomain condition cl_area rule cl_sex) is" +
                "rule01: when cl_area=\"IT\" then T=M+F errorcode \"wrong 01\" errorlevel 1;" +
                "rule02: when cl_area=\"ITA\" then T>M+F  errorcode \"wrong 02\";" +
                "rule03: T>M errorcode \"wrong 03\";" +
                "rule04: T>F errorcode \"wrong 04\" errorlevel 2" +
                "end hierarchical ruleset;";
        userFunction.setFunctionContent(userDefinition);
        userFunctionRepository.save(userFunction);
        try {
            List<ResultExpression> resultDefinition = vtlTestUtils.getResult(userDefinition);
            assertTrue("contollo semantico fallito", false);
        } catch (Exception exception) {

            logger.info(exception);
        }
    }


    @Test
    @Transactional
    public void check_hierarchyOnlyOneMeasure() throws Exception {
        setupDatapoint();
        String commandStatements = "ds_r:= check_hierarchy (ds_42, hr_op1 condition Id_1 rule id_2 partial_zero dataset_priority all_measures);";
        String jsonResult = "[{\"result\":{\"id\":26,\"name\":\"dsr\",\"description\":null,\"attributes\":[],\"persistent\":false,\"identifiers\":[{\"id\":26,\"type\":\"STRING\",\"name\":\"ID_1\",\"domainValue\":\"cl_area\",\"domainValueParent\":\"cl_area\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":27,\"type\":\"STRING\",\"name\":\"ID_2\",\"domainValue\":\"cl_sex\",\"domainValueParent\":\"cl_sex\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":28,\"type\":\"STRING\",\"name\":\"ruleid\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}],\"measures\":[{\"id\":29,\"type\":\"NUMBER\",\"name\":\"ME_1\",\"domainValue\":\"num_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},{\"id\":30,\"type\":\"NUMBER\",\"name\":\"ME_2\",\"domainValue\":\"num_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},{\"id\":31,\"type\":\"BOOLEAN\",\"name\":\"bool_var\",\"domainValue\":\"bool_vd\",\"domainValueParent\":\"bool_vd\",\"vtlComponentRole\":\"MEASURE\"},{\"id\":33,\"type\":\"STRING\",\"name\":\"errorcode\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"MEASURE\"},{\"id\":34,\"type\":\"STRING\",\"name\":\"errorlevel\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"viral\":[{\"id\":32,\"type\":\"STRING\",\"name\":\"AT_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"VIRAL\"}]},\"messages\":[],\"command\":\"dsr:=check_datapoint(ds_42,dpr_op1componentsid_1,id_2,me_1,me_2all_measures)\",\"ascalar\":false,\"adataset\":true,\"acomponent\":false},{\"result\":{\"id\":24,\"name\":\"temporary_23\",\"description\":\"DS_42\",\"attributes\":[],\"persistent\":null,\"identifiers\":[{\"id\":17,\"type\":\"STRING\",\"name\":\"ID_1\",\"domainValue\":\"cl_area\",\"domainValueParent\":\"cl_area\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":18,\"type\":\"STRING\",\"name\":\"ID_2\",\"domainValue\":\"cl_sex\",\"domainValueParent\":\"cl_sex\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":19,\"type\":\"STRING\",\"name\":\"ruleid\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}],\"measures\":[{\"id\":20,\"type\":\"NUMBER\",\"name\":\"ME_1\",\"domainValue\":\"num_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},{\"id\":21,\"type\":\"NUMBER\",\"name\":\"ME_2\",\"domainValue\":\"num_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},{\"id\":22,\"type\":\"BOOLEAN\",\"name\":\"bool_var\",\"domainValue\":\"bool_vd\",\"domainValueParent\":\"bool_vd\",\"vtlComponentRole\":\"MEASURE\"},{\"id\":24,\"type\":\"STRING\",\"name\":\"errorcode\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"MEASURE\"},{\"id\":25,\"type\":\"STRING\",\"name\":\"errorlevel\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"viral\":[{\"id\":23,\"type\":\"STRING\",\"name\":\"AT_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"VIRAL\"}]},\"messages\":[],\"ascalar\":false,\"adataset\":true,\"acomponent\":false},{\"result\":{\"id\":1,\"name\":\"DS_42\",\"description\":null,\"attributes\":[{\"id\":5,\"type\":\"STRING\",\"name\":\"AT_1\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"ATTRIBUTE\"}],\"persistent\":true,\"identifiers\":[{\"id\":1,\"type\":\"STRING\",\"name\":\"ID_1\",\"domainValue\":\"cl_area\",\"domainValueParent\":\"cl_area\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":2,\"type\":\"STRING\",\"name\":\"ID_2\",\"domainValue\":\"cl_sex\",\"domainValueParent\":\"cl_sex\",\"vtlComponentRole\":\"IDENTIFIER\"}],\"measures\":[{\"id\":3,\"type\":\"NUMBER\",\"name\":\"ME_1\",\"domainValue\":\"num_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},{\"id\":4,\"type\":\"NUMBER\",\"name\":\"ME_2\",\"domainValue\":\"num_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"viral\":[{\"id\":6,\"type\":\"STRING\",\"name\":\"AT_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"VIRAL\"}]},\"messages\":[],\"command\":\"ds_42\",\"ascalar\":false,\"adataset\":true,\"acomponent\":false},{\"resultComponent\":{\"id\":1,\"type\":\"STRING\",\"name\":\"ID_1\",\"domainValue\":\"cl_area\",\"domainValueParent\":\"cl_area\",\"vtlComponentRole\":\"IDENTIFIER\"},\"messages\":[],\"command\":\"cl_area\",\"ascalar\":false,\"adataset\":false,\"acomponent\":true},{\"resultComponent\":{\"id\":2,\"type\":\"STRING\",\"name\":\"ID_2\",\"domainValue\":\"cl_sex\",\"domainValueParent\":\"cl_sex\",\"vtlComponentRole\":\"IDENTIFIER\"},\"messages\":[],\"command\":\"cl_sex\",\"ascalar\":false,\"adataset\":false,\"acomponent\":true},{\"resultComponent\":{\"id\":3,\"type\":\"NUMBER\",\"name\":\"ME_1\",\"domainValue\":\"num_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},\"messages\":[],\"command\":\"vd_Me1\",\"ascalar\":false,\"adataset\":false,\"acomponent\":true},{\"resultComponent\":{\"id\":4,\"type\":\"NUMBER\",\"name\":\"ME_2\",\"domainValue\":\"num_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},\"messages\":[],\"command\":\"vd_Me2\",\"ascalar\":false,\"adataset\":false,\"acomponent\":true}]";
        try {
            List<ResultExpression> resultDefinition = vtlTestUtils.getResult(commandStatements);
            assertTrue("contollo semantico fallito", false);
        } catch (Exception exception) {

            logger.info(exception);
        }
    }

    @Test
    @Transactional
    public void check_hierarchyAllMeasure() throws Exception {
        setupDatapoint();
        String commandStatements = "ds_r:= check_hierarchy (ds_41, hr_op1 condition Id_1 rule id_2 partial_zero dataset_priority all_measures);";
        String jsonResult = "[{\"result\":{\"id\":11,\"name\":\"ds_r\",\"description\":null,\"attributes\":[],\"persistent\":false,\"measures\":[{\"id\":26,\"type\":\"NUMBER\",\"name\":\"ME_1\",\"domainValue\":\"num_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},{\"id\":27,\"type\":\"BOOLEAN\",\"name\":\"bool_var\",\"domainValue\":\"bool_vd\",\"domainValueParent\":\"bool_vd\",\"vtlComponentRole\":\"MEASURE\"},{\"id\":29,\"type\":\"STRING\",\"name\":\"errorcode\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"MEASURE\"},{\"id\":30,\"type\":\"STRING\",\"name\":\"errorlevel\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"MEASURE\"},{\"id\":31,\"type\":\"NUMBER\",\"name\":\"imbalance\",\"domainValue\":\"num_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"identifiers\":[{\"id\":23,\"type\":\"STRING\",\"name\":\"ID_1\",\"domainValue\":\"cl_area\",\"domainValueParent\":\"cl_area\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":24,\"type\":\"STRING\",\"name\":\"ID_2\",\"domainValue\":\"cl_sex\",\"domainValueParent\":\"cl_sex\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":25,\"type\":\"STRING\",\"name\":\"ruleid\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}],\"viral\":[{\"id\":28,\"type\":\"STRING\",\"name\":\"AT_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"VIRAL\"}]},\"messages\":[],\"command\":\"ds_r:=check_hierarchy(ds_41,hr_op1conditionId_1ruleid_2partial_zerodataset_priorityall_measures)\",\"acomponent\":false,\"adataset\":true,\"ascalar\":false},{\"result\":{\"id\":9,\"name\":\"temporary_8\",\"description\":\"DS_41\",\"attributes\":[],\"persistent\":null,\"measures\":[{\"id\":17,\"type\":\"NUMBER\",\"name\":\"ME_1\",\"domainValue\":\"num_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},{\"id\":18,\"type\":\"BOOLEAN\",\"name\":\"bool_var\",\"domainValue\":\"bool_vd\",\"domainValueParent\":\"bool_vd\",\"vtlComponentRole\":\"MEASURE\"},{\"id\":20,\"type\":\"STRING\",\"name\":\"errorcode\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"MEASURE\"},{\"id\":21,\"type\":\"STRING\",\"name\":\"errorlevel\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"MEASURE\"},{\"id\":22,\"type\":\"NUMBER\",\"name\":\"imbalance\",\"domainValue\":\"num_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"identifiers\":[{\"id\":14,\"type\":\"STRING\",\"name\":\"ID_1\",\"domainValue\":\"cl_area\",\"domainValueParent\":\"cl_area\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":15,\"type\":\"STRING\",\"name\":\"ID_2\",\"domainValue\":\"cl_sex\",\"domainValueParent\":\"cl_sex\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":16,\"type\":\"STRING\",\"name\":\"ruleid\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}],\"viral\":[{\"id\":19,\"type\":\"STRING\",\"name\":\"AT_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"VIRAL\"}]},\"messages\":[],\"acomponent\":false,\"adataset\":true,\"ascalar\":false},{\"result\":{\"id\":2,\"name\":\"DS_41\",\"description\":null,\"attributes\":[{\"id\":10,\"type\":\"STRING\",\"name\":\"AT_1\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"ATTRIBUTE\"}],\"persistent\":true,\"measures\":[{\"id\":9,\"type\":\"NUMBER\",\"name\":\"ME_1\",\"domainValue\":\"num_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"identifiers\":[{\"id\":7,\"type\":\"STRING\",\"name\":\"ID_1\",\"domainValue\":\"cl_area\",\"domainValueParent\":\"cl_area\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":8,\"type\":\"STRING\",\"name\":\"ID_2\",\"domainValue\":\"cl_sex\",\"domainValueParent\":\"cl_sex\",\"vtlComponentRole\":\"IDENTIFIER\"}],\"viral\":[{\"id\":11,\"type\":\"STRING\",\"name\":\"AT_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"VIRAL\"}]},\"messages\":[],\"command\":\"ds_41\",\"acomponent\":false,\"adataset\":true,\"ascalar\":false},{\"resultComponent\":{\"id\":7,\"type\":\"STRING\",\"name\":\"ID_1\",\"domainValue\":\"cl_area\",\"domainValueParent\":\"cl_area\",\"vtlComponentRole\":\"IDENTIFIER\"},\"messages\":[],\"command\":\"cl_area\",\"acomponent\":true,\"adataset\":false,\"ascalar\":false}]";
        List<ResultExpression> result = vtlTestUtils.getResult(commandStatements);
        logger.info(vtlTestUtils.convertResultToJson(result));
        List<ResultExpression> resultExpressionsToCompare = vtlTestUtils.convertJsonToResultExpression(jsonResult);
        assertNotNull("Conversione non riuscita", resultExpressionsToCompare);
        assertNotNull("Validazione Semantica non riuscita", result);
        Assert.assertTrue("I due risultati non sono uguali",
                vtlTestUtils.compareResult(result.get(0), resultExpressionsToCompare.get(0)));

    }

    @Test
    @Transactional
    public void check_hierarchyAll() throws Exception {
        setupDatapoint();
        String commandStatements = "ds_r:= check_hierarchy (ds_41, hr_op1 condition Id_1 rule id_2 partial_zero dataset_priority all);";
        String jsonResult = "[{\"result\":{\"id\":11,\"name\":\"ds_r\",\"description\":null,\"attributes\":[],\"persistent\":false,\"measures\":[{\"id\":25,\"type\":\"BOOLEAN\",\"name\":\"bool_var\",\"domainValue\":\"bool_vd\",\"domainValueParent\":\"bool_vd\",\"vtlComponentRole\":\"MEASURE\"},{\"id\":27,\"type\":\"STRING\",\"name\":\"errorcode\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"MEASURE\"},{\"id\":28,\"type\":\"STRING\",\"name\":\"errorlevel\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"MEASURE\"},{\"id\":29,\"type\":\"NUMBER\",\"name\":\"imbalance\",\"domainValue\":\"num_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"viral\":[{\"id\":26,\"type\":\"STRING\",\"name\":\"AT_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"VIRAL\"}],\"identifiers\":[{\"id\":22,\"type\":\"STRING\",\"name\":\"ID_1\",\"domainValue\":\"cl_area\",\"domainValueParent\":\"cl_area\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":23,\"type\":\"STRING\",\"name\":\"ID_2\",\"domainValue\":\"cl_sex\",\"domainValueParent\":\"cl_sex\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":24,\"type\":\"STRING\",\"name\":\"ruleid\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}]},\"messages\":[],\"command\":\"ds_r:=check_hierarchy(ds_41,hr_op1conditionId_1ruleid_2partial_zerodataset_priorityall)\",\"ascalar\":false,\"adataset\":true,\"acomponent\":false},{\"result\":{\"id\":9,\"name\":\"temporary_8\",\"description\":\"DS_41\",\"attributes\":[],\"persistent\":null,\"measures\":[{\"id\":17,\"type\":\"BOOLEAN\",\"name\":\"bool_var\",\"domainValue\":\"bool_vd\",\"domainValueParent\":\"bool_vd\",\"vtlComponentRole\":\"MEASURE\"},{\"id\":19,\"type\":\"STRING\",\"name\":\"errorcode\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"MEASURE\"},{\"id\":20,\"type\":\"STRING\",\"name\":\"errorlevel\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"MEASURE\"},{\"id\":21,\"type\":\"NUMBER\",\"name\":\"imbalance\",\"domainValue\":\"num_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"viral\":[{\"id\":18,\"type\":\"STRING\",\"name\":\"AT_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"VIRAL\"}],\"identifiers\":[{\"id\":14,\"type\":\"STRING\",\"name\":\"ID_1\",\"domainValue\":\"cl_area\",\"domainValueParent\":\"cl_area\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":15,\"type\":\"STRING\",\"name\":\"ID_2\",\"domainValue\":\"cl_sex\",\"domainValueParent\":\"cl_sex\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":16,\"type\":\"STRING\",\"name\":\"ruleid\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}]},\"messages\":[],\"ascalar\":false,\"adataset\":true,\"acomponent\":false},{\"result\":{\"id\":2,\"name\":\"DS_41\",\"description\":null,\"attributes\":[{\"id\":10,\"type\":\"STRING\",\"name\":\"AT_1\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"ATTRIBUTE\"}],\"persistent\":true,\"measures\":[{\"id\":9,\"type\":\"NUMBER\",\"name\":\"ME_1\",\"domainValue\":\"num_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"viral\":[{\"id\":11,\"type\":\"STRING\",\"name\":\"AT_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"VIRAL\"}],\"identifiers\":[{\"id\":7,\"type\":\"STRING\",\"name\":\"ID_1\",\"domainValue\":\"cl_area\",\"domainValueParent\":\"cl_area\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":8,\"type\":\"STRING\",\"name\":\"ID_2\",\"domainValue\":\"cl_sex\",\"domainValueParent\":\"cl_sex\",\"vtlComponentRole\":\"IDENTIFIER\"}]},\"messages\":[],\"command\":\"ds_41\",\"ascalar\":false,\"adataset\":true,\"acomponent\":false},{\"resultComponent\":{\"id\":7,\"type\":\"STRING\",\"name\":\"ID_1\",\"domainValue\":\"cl_area\",\"domainValueParent\":\"cl_area\",\"vtlComponentRole\":\"IDENTIFIER\"},\"messages\":[],\"command\":\"cl_area\",\"ascalar\":false,\"adataset\":false,\"acomponent\":true}]";
        List<ResultExpression> result = vtlTestUtils.getResult(commandStatements);
        logger.info(vtlTestUtils.convertResultToJson(result));
        List<ResultExpression> resultExpressionsToCompare = vtlTestUtils.convertJsonToResultExpression(jsonResult);
        assertNotNull("Conversione non riuscita", resultExpressionsToCompare);
        assertNotNull("Validazione Semantica non riuscita", result);
        Assert.assertTrue("I due risultati non sono uguali",
                vtlTestUtils.compareResult(result.get(0), resultExpressionsToCompare.get(0)));

    }

    @Test
    @Transactional
    public void check_hierarchyInvalid() throws Exception {
        setupDatapoint();
        String commandStatements = "ds_r:= check_hierarchy (ds_41, hr_op1 condition Id_1 rule id_2 partial_zero dataset_priority invalid);";
        String jsonResult = "[{\"result\":{\"id\":11,\"name\":\"ds_r\",\"description\":null,\"attributes\":[],\"persistent\":false,\"viral\":[{\"id\":26,\"type\":\"STRING\",\"name\":\"AT_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"VIRAL\"}],\"measures\":[{\"id\":25,\"type\":\"NUMBER\",\"name\":\"ME_1\",\"domainValue\":\"num_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},{\"id\":27,\"type\":\"STRING\",\"name\":\"errorcode\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"MEASURE\"},{\"id\":28,\"type\":\"STRING\",\"name\":\"errorlevel\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"MEASURE\"},{\"id\":29,\"type\":\"NUMBER\",\"name\":\"imbalance\",\"domainValue\":\"num_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"identifiers\":[{\"id\":22,\"type\":\"STRING\",\"name\":\"ID_1\",\"domainValue\":\"cl_area\",\"domainValueParent\":\"cl_area\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":23,\"type\":\"STRING\",\"name\":\"ID_2\",\"domainValue\":\"cl_sex\",\"domainValueParent\":\"cl_sex\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":24,\"type\":\"STRING\",\"name\":\"ruleid\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}]},\"messages\":[],\"command\":\"ds_r:=check_hierarchy(ds_41,hr_op1conditionId_1ruleid_2partial_zerodataset_priorityinvalid)\",\"acomponent\":false,\"ascalar\":false,\"adataset\":true},{\"result\":{\"id\":9,\"name\":\"temporary_8\",\"description\":\"DS_41\",\"attributes\":[],\"persistent\":null,\"viral\":[{\"id\":18,\"type\":\"STRING\",\"name\":\"AT_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"VIRAL\"}],\"measures\":[{\"id\":17,\"type\":\"NUMBER\",\"name\":\"ME_1\",\"domainValue\":\"num_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"},{\"id\":19,\"type\":\"STRING\",\"name\":\"errorcode\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"MEASURE\"},{\"id\":20,\"type\":\"STRING\",\"name\":\"errorlevel\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"MEASURE\"},{\"id\":21,\"type\":\"NUMBER\",\"name\":\"imbalance\",\"domainValue\":\"num_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"identifiers\":[{\"id\":14,\"type\":\"STRING\",\"name\":\"ID_1\",\"domainValue\":\"cl_area\",\"domainValueParent\":\"cl_area\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":15,\"type\":\"STRING\",\"name\":\"ID_2\",\"domainValue\":\"cl_sex\",\"domainValueParent\":\"cl_sex\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":16,\"type\":\"STRING\",\"name\":\"ruleid\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"IDENTIFIER\"}]},\"messages\":[],\"acomponent\":false,\"ascalar\":false,\"adataset\":true},{\"result\":{\"id\":2,\"name\":\"DS_41\",\"description\":null,\"attributes\":[{\"id\":10,\"type\":\"STRING\",\"name\":\"AT_1\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"ATTRIBUTE\"}],\"persistent\":true,\"viral\":[{\"id\":11,\"type\":\"STRING\",\"name\":\"AT_2\",\"domainValue\":\"string_vd\",\"domainValueParent\":\"string_vd\",\"vtlComponentRole\":\"VIRAL\"}],\"measures\":[{\"id\":9,\"type\":\"NUMBER\",\"name\":\"ME_1\",\"domainValue\":\"num_vd\",\"domainValueParent\":\"num_vd\",\"vtlComponentRole\":\"MEASURE\"}],\"identifiers\":[{\"id\":7,\"type\":\"STRING\",\"name\":\"ID_1\",\"domainValue\":\"cl_area\",\"domainValueParent\":\"cl_area\",\"vtlComponentRole\":\"IDENTIFIER\"},{\"id\":8,\"type\":\"STRING\",\"name\":\"ID_2\",\"domainValue\":\"cl_sex\",\"domainValueParent\":\"cl_sex\",\"vtlComponentRole\":\"IDENTIFIER\"}]},\"messages\":[],\"command\":\"ds_41\",\"acomponent\":false,\"ascalar\":false,\"adataset\":true},{\"resultComponent\":{\"id\":7,\"type\":\"STRING\",\"name\":\"ID_1\",\"domainValue\":\"cl_area\",\"domainValueParent\":\"cl_area\",\"vtlComponentRole\":\"IDENTIFIER\"},\"messages\":[],\"command\":\"cl_area\",\"acomponent\":true,\"ascalar\":false,\"adataset\":false}]";
        List<ResultExpression> result = vtlTestUtils.getResult(commandStatements);
        logger.info(vtlTestUtils.convertResultToJson(result));
        List<ResultExpression> resultExpressionsToCompare = vtlTestUtils.convertJsonToResultExpression(jsonResult);
        assertNotNull("Conversione non riuscita", resultExpressionsToCompare);
        assertNotNull("Validazione Semantica non riuscita", result);
        Assert.assertTrue("I due risultati non sono uguali",
                vtlTestUtils.compareResult(result.get(0), resultExpressionsToCompare.get(0)));

    }


    private void setupDatapoint() throws Exception {
        List<VtlComponent> vtlComponents;
        VtlComponent vtlComponent;

        VtlDataset vtlDataset42 = new VtlDataset();
        vtlDataset42.setPersistent(true);
        vtlDataset42.setName("DS_42");
        vtlDataset42.setIsOnlyAScalar(false);
        vtlDataset42.setTransitory(false);
        vtlComponents = new ArrayList<>();

        vtlComponent = new VtlComponent();
        vtlComponent.setName("ID_1");
        vtlComponent.setType(VtlType.STRING);
        vtlComponent.setDomainValue("cl_area");
        vtlComponent.setDomainValueParent("cl_area");
        vtlComponent.setVtlComponentRole(VtlComponentRole.IDENTIFIER);
        vtlComponent.setVtlDataset(vtlDataset42);
        vtlComponents.add(vtlComponent);

        vtlComponent = new VtlComponent();
        vtlComponent.setName("ID_2");
        vtlComponent.setType(VtlType.STRING);
        vtlComponent.setDomainValue("cl_sex");
        vtlComponent.setDomainValueParent("cl_sex");
        vtlComponent.setVtlComponentRole(VtlComponentRole.IDENTIFIER);
        vtlComponent.setVtlDataset(vtlDataset42);
        vtlComponents.add(vtlComponent);

        vtlComponent = new VtlComponent();
        vtlComponent.setName("ME_1");
        vtlComponent.setType(VtlType.NUMBER);
        vtlComponent.setDomainValue(VtlType.NUMBER.getDomainValue());
        vtlComponent.setDomainValueParent(VtlType.NUMBER.getDomainValueParent());
        vtlComponent.setVtlComponentRole(VtlComponentRole.MEASURE);
        vtlComponent.setVtlDataset(vtlDataset42);
        vtlComponents.add(vtlComponent);

        vtlComponent = new VtlComponent();
        vtlComponent.setName("ME_2");
        vtlComponent.setType(VtlType.NUMBER);
        vtlComponent.setDomainValue(VtlType.NUMBER.getDomainValue());
        vtlComponent.setDomainValueParent(VtlType.NUMBER.getDomainValueParent());
        vtlComponent.setVtlComponentRole(VtlComponentRole.MEASURE);
        vtlComponent.setVtlDataset(vtlDataset42);
        vtlComponents.add(vtlComponent);

        vtlComponent = new VtlComponent();
        vtlComponent.setName("AT_1");
        vtlComponent.setType(VtlType.STRING);
        vtlComponent.setDomainValue(VtlType.STRING.getDomainValue());
        vtlComponent.setDomainValueParent(VtlType.STRING.getDomainValueParent());
        vtlComponent.setVtlComponentRole(VtlComponentRole.ATTRIBUTE);
        vtlComponent.setVtlDataset(vtlDataset42);
        vtlComponents.add(vtlComponent);

        vtlComponent = new VtlComponent();
        vtlComponent.setName("AT_2");
        vtlComponent.setType(VtlType.STRING);
        vtlComponent.setDomainValue(VtlType.STRING.getDomainValue());
        vtlComponent.setDomainValueParent(VtlType.STRING.getDomainValueParent());
        vtlComponent.setVtlComponentRole(VtlComponentRole.VIRAL);
        vtlComponent.setVtlDataset(vtlDataset42);
        vtlComponents.add(vtlComponent);

        vtlDataset42.addComponentsList(vtlComponents);
        datasetRepository.save(vtlDataset42);


        VtlDataset vtlDataset41 = new VtlDataset();
        vtlDataset41.setPersistent(true);
        vtlDataset41.setName("DS_41");
        vtlDataset41.setIsOnlyAScalar(false);
        vtlDataset41.setTransitory(false);
        vtlComponents = new ArrayList<>();

        vtlComponent = new VtlComponent();
        vtlComponent.setName("ID_1");
        vtlComponent.setType(VtlType.STRING);
        vtlComponent.setDomainValue("cl_area");
        vtlComponent.setDomainValueParent("cl_area");
        vtlComponent.setVtlComponentRole(VtlComponentRole.IDENTIFIER);
        vtlComponent.setVtlDataset(vtlDataset41);
        vtlComponents.add(vtlComponent);

        vtlComponent = new VtlComponent();
        vtlComponent.setName("ID_2");
        vtlComponent.setType(VtlType.STRING);
        vtlComponent.setDomainValue("cl_sex");
        vtlComponent.setDomainValueParent("cl_sex");
        vtlComponent.setVtlComponentRole(VtlComponentRole.IDENTIFIER);
        vtlComponent.setVtlDataset(vtlDataset41);
        vtlComponents.add(vtlComponent);

        vtlComponent = new VtlComponent();
        vtlComponent.setName("ME_1");
        vtlComponent.setType(VtlType.NUMBER);
        vtlComponent.setDomainValue(VtlType.NUMBER.getDomainValue());
        vtlComponent.setDomainValueParent(VtlType.NUMBER.getDomainValueParent());
        vtlComponent.setVtlComponentRole(VtlComponentRole.MEASURE);
        vtlComponent.setVtlDataset(vtlDataset41);
        vtlComponents.add(vtlComponent);


        vtlComponent = new VtlComponent();
        vtlComponent.setName("AT_1");
        vtlComponent.setType(VtlType.STRING);
        vtlComponent.setDomainValue(VtlType.STRING.getDomainValue());
        vtlComponent.setDomainValueParent(VtlType.STRING.getDomainValueParent());
        vtlComponent.setVtlComponentRole(VtlComponentRole.ATTRIBUTE);
        vtlComponent.setVtlDataset(vtlDataset41);
        vtlComponents.add(vtlComponent);

        vtlComponent = new VtlComponent();
        vtlComponent.setName("AT_2");
        vtlComponent.setType(VtlType.STRING);
        vtlComponent.setDomainValue(VtlType.STRING.getDomainValue());
        vtlComponent.setDomainValueParent(VtlType.STRING.getDomainValueParent());
        vtlComponent.setVtlComponentRole(VtlComponentRole.VIRAL);
        vtlComponent.setVtlDataset(vtlDataset41);
        vtlComponents.add(vtlComponent);

        vtlDataset41.addComponentsList(vtlComponents);
        datasetRepository.save(vtlDataset41);

        VtlDataset vtlDatasetN = new VtlDataset();
        vtlDatasetN.setPersistent(true);
        vtlDatasetN.setName("DS_59");
        vtlDatasetN.setIsOnlyAScalar(false);
        vtlDatasetN.setTransitory(false);
        vtlComponents = new ArrayList<>();


        vtlDatasetN.addComponentsList(vtlComponents);
        datasetRepository.save(vtlDatasetN);

        ValueDomain valueDomain = new ValueDomain();
        valueDomain.setCode("");
        valueDomain.setValueDomainName(VtlType.NUMBER.getDomainValue());
        valueDomain.setDescription("");
        valueDomainRepository.save(valueDomain);

        valueDomain = new ValueDomain();
        valueDomain.setCode("T");
        valueDomain.setValueDomainName("cl_sex");
        valueDomain.setDescription("");
        valueDomainRepository.save(valueDomain);

        valueDomain = new ValueDomain();
        valueDomain.setCode("F");
        valueDomain.setValueDomainName("cl_sex");
        valueDomain.setDescription("");
        valueDomainRepository.save(valueDomain);

        valueDomain = new ValueDomain();
        valueDomain.setCode("M");
        valueDomain.setValueDomainName("cl_sex");
        valueDomain.setDescription("");
        valueDomainRepository.save(valueDomain);

        valueDomain = new ValueDomain();
        valueDomain.setCode("");
        valueDomain.setValueDomainName("cl_area");
        valueDomain.setDescription("");
        valueDomainRepository.save(valueDomain);

        valueDomain = new ValueDomain();
        valueDomain.setCode("");
        valueDomain.setValueDomainName("vd_me1");
        valueDomain.setDescription("");
        valueDomainRepository.save(valueDomain);

        valueDomain = new ValueDomain();
        valueDomain.setCode("");
        valueDomain.setValueDomainName("vd_me2");
        valueDomain.setDescription("");
        valueDomainRepository.save(valueDomain);

        UserFunction userFunction = new UserFunction();
        userFunction.setFunctionName("dpr_op1");
        userFunction.setFunctionType(VtlUserFunctionType.DATAPOINT_FUNCTION);
        String commandStatements = "define datapoint ruleset dpr_op1( valuedomain cl_area, cl_sex, num_vd as vd_Me1, num_vd as vd_Me2) is " +
                "rule01: when cl_area=\"IT\" and cl_sex=\"M\" then vd_Me1>0 and vd_Me2<100 errorcode \"wrong 01\" errorlevel 1; " +
                "rule02: when cl_area=\"ITA\" and cl_sex=\"M\" then vd_Me1<0 errorcode \"wrong 02\"; " +
                "rule03: when cl_area=\"ITC\" and cl_sex=\"F\" then vd_Me2=- 10 errorcode \"wrong 03\" " +
                "end datapoint ruleset; ";
        userFunction.setFunctionContent(commandStatements);
        userFunctionRepository.save(userFunction);
        userFunction = new UserFunction();
        userFunction.setFunctionName("hr_op1");
        userFunction.setFunctionType(VtlUserFunctionType.HIERARCHICAL_FUNCTION);
        String userDefinition = "define hierarchical ruleset hr_op1 ( valuedomain condition cl_area rule cl_sex) is " +
                "rule01: when cl_area=\"IT\" then T=M+F errorcode \"wrong 01\" errorlevel 1; " +
                "rule02: when cl_area=\"ITA\" then T>M+F  errorcode \"wrong 02\"; " +
                "rule03: T>M errorcode \"wrong 03\"; " +
                "rule04: T>F errorcode \"wrong 04\" errorlevel 2 " +
                "end hierarchical ruleset;";
        userFunction.setFunctionContent(userDefinition);
        userFunctionRepository.save(userFunction);
       /* vtlTestUtils.getResult(userDefinition);
        vtlTestUtils.getResult(commandStatements);*/
    }
}
