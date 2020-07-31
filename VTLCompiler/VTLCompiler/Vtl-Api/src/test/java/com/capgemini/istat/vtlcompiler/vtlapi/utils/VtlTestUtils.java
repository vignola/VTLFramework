package com.capgemini.istat.vtlcompiler.vtlapi.utils;

import com.capgemini.istat.vtlcompiler.vtlapi.controller.EngineController;
import com.capgemini.istat.vtlcompiler.vtlapi.response.VtlCompilerResponse;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.dataset.VtlComponent;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.dataset.VtlComponentRole;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.dataset.VtlDataset;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.dataset.VtlType;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.semantic.ResultExpression;
import com.capgemini.istat.vtlcompiler.vtlcommon.service.ComponentUtilityService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class VtlTestUtils {
    private static final Logger logger = LogManager.getLogger(VtlTestUtils.class);
    private ComponentUtilityService componentUtilityService;
    private EngineController engineController;

    @Autowired
    public void setComponentUtilityService(ComponentUtilityService componentUtilityService) {
        this.componentUtilityService = componentUtilityService;
    }

    @Autowired
    public void setController(EngineController controller) {
        this.engineController = controller;
    }

    public List<ResultExpression> getResult(String commandStatements) throws Exception {
        ResponseEntity<VtlCompilerResponse> validationResult = engineController.validateAndShowPassages(commandStatements);
        return validationResult.getBody().getResultSemantic();
    }

    public List<ResultExpression> convertJsonToResultExpression(String json) {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        try {
            return objectMapper.readValue(json, new TypeReference<List<ResultExpression>>() {
            });
        } catch (IOException e) {
            return null;
        }
    }

    public String convertResultToJson(List<ResultExpression> responseEntity) {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        try {
            return objectMapper.writeValueAsString(responseEntity);
        } catch (IOException e) {
            return null;
        }
    }

    public String convertDatasetToJson(VtlDataset responseEntity) {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        try {
            return objectMapper.writeValueAsString(responseEntity);
        } catch (IOException e) {
            return null;
        }
    }

    public Boolean compareResult(ResultExpression resultExpression, ResultExpression resultExpressionToCompare) {
        VtlDataset vtlDataset = resultExpression.getResult();
        VtlDataset vtlDatasetToCompare = resultExpressionToCompare.getResult();
        if (vtlDataset == null || vtlDatasetToCompare == null) {
            logger.info("Le result expression non hanno restituito risultati coerenti");
            return false;
        }
        logger.info("actual   -> " + convertDatasetToJson(vtlDataset));
        logger.info("expected -> " + convertDatasetToJson(vtlDatasetToCompare));
        return compareDataset(vtlDataset, vtlDatasetToCompare);
    }

    public Boolean compareDataset(VtlDataset vtlDataset, VtlDataset vtlDatasetToCompare) {
        logger.info("Comparing " + vtlDataset.getName() + "  ->  " + vtlDatasetToCompare.getName());
        if (vtlDataset.getVtlComponents().size() != vtlDatasetToCompare.getVtlComponents().size()) {
            logger.info("I dataset non hanno la stessa grandezza");
            return false;
        }
        Boolean comparing = true;
        for (VtlComponent vtlComponent : vtlDataset.getVtlComponents()) {
            VtlComponent vtlComponentToCompare = componentUtilityService.getComponentFromName(vtlDatasetToCompare.getVtlComponents(), vtlComponent.getName(), true);
            if (vtlComponentToCompare == null) {
                logger.info("Il component " + vtlComponent.getName() + " non è presente in entrambe le tabelle");
                return false;
            }
            comparing = comparing && compareComponent(vtlComponent, vtlComponentToCompare);
        }
        return comparing;
    }

    public Boolean compareComponent(VtlComponent vtlComponent, VtlComponent vtlComponentToCompare) {
        return vtlComponent.getName().equalsIgnoreCase(vtlComponentToCompare.getName()) &&
                vtlComponent.getType().equals(vtlComponentToCompare.getType()) &&
                vtlComponent.getDomainValue().equals(vtlComponentToCompare.getDomainValue()) &&
                vtlComponent.getDomainValueParent().equals(vtlComponentToCompare.getDomainValueParent());
    }

    public VtlComponent getVtlComponent(String componentName, VtlType vtlType, VtlComponentRole vtlComponentRole) {
        VtlComponent vtlComponent = new VtlComponent();
        vtlComponent.setName(componentName);
        vtlComponent.setType(vtlType);
        vtlComponent.setDomainValue(vtlType.getDomainValue());
        vtlComponent.setDomainValueParent(vtlType.getDomainValueParent());
        vtlComponent.setVtlComponentRole(vtlComponentRole);
        return vtlComponent;
    }


}
