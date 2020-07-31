package com.capgemini.istat.vtlcompiler.vtlapi.utils;

import com.capgemini.istat.vtlcompiler.vtlcommon.model.dataset.VtlComponent;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.dataset.VtlComponentRole;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.dataset.VtlType;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.statement.VtlStatement;
import com.capgemini.istat.vtlcompiler.vtlinterpreter.service.IParseTreeService;
import com.capgemini.istat.vtlcompiler.vtlsemantic.service.SemanticService;
import com.capgemini.istat.vtlcompiler.vtlsqlbuilder.service.TranslationService;
import com.healthmarketscience.sqlbuilder.SqlObject;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
public class TranslationUtilsTestService {
    private static final Logger logger = LogManager.getLogger(TranslationUtilsTestService.class);
    private IParseTreeService parseTreeService; 
    private SemanticService semanticService;
    private TranslationService translationService;

    @Autowired
    public void setParseTreeService(IParseTreeService parseTreeService) {
        this.parseTreeService = parseTreeService;
    }

    @Autowired
    public void setSemanticService(SemanticService semanticService) {
        this.semanticService = semanticService;
    }

    @Autowired
    public void setTranslationService(TranslationService translationService) {
        this.translationService = translationService;
    }

    public String translate(String commandStatements) throws Exception {
        List<VtlStatement> result = parseTreeService.visitTree(commandStatements);
        semanticService.validateSemantic(result, null);
        LinkedList<SqlObject> translatedCommands = translationService.translateStatements(result, false, null);
        String resultSQLString = "";
        for (SqlObject sqlObject : translatedCommands) {
            if (sqlObject != null)
            	logger.info("Print: " + sqlObject.toString());
            resultSQLString = resultSQLString+sqlObject.toString()+";";
        }
        return resultSQLString;
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

    public String temporaryValueObfuscation(String comandSqlResult) {
        while(comandSqlResult.contains("temporary_")) {
            int start = comandSqlResult.indexOf("temporary_");
            int end = comandSqlResult.indexOf(" ", start);
            comandSqlResult = comandSqlResult.substring(0, start) + comandSqlResult.substring(end, comandSqlResult.length());
        }
        return comandSqlResult;
    }
}
