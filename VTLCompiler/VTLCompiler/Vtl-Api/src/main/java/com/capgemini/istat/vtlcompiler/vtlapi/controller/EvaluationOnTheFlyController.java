package com.capgemini.istat.vtlcompiler.vtlapi.controller;

import com.capgemini.istat.vtlcompiler.vtlapi.model.VtlRequest;
import com.capgemini.istat.vtlcompiler.vtlapi.service.EvaluationOnTheFlyService;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.semantic.ResultExpression;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.statement.VtlStatement;
import com.capgemini.istat.vtlcompiler.vtlinterpreter.service.IParseTreeService;
import com.capgemini.istat.vtlcompiler.vtllexicon.LexiconService;
import com.capgemini.istat.vtlcompiler.vtlsemantic.service.SemanticService;
import com.capgemini.istat.vtlcompiler.vtlsqlbuilder.service.TranslationService;
import com.healthmarketscience.sqlbuilder.SqlObject;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/engine")
@Api(value = "Set di Endpoint per accedere alla valutazione on the fly del linguaggio VTL")
public class EvaluationOnTheFlyController extends ExceptionHandlerController{
    Logger log = LogManager.getLogger(EvaluationOnTheFlyController.class);
    private EvaluationOnTheFlyService evaluationOnTheFlyService;
    private LexiconService lexiconService;
    private IParseTreeService parseTreeService;
    private SemanticService semanticService;
    private TranslationService translationService;


    @Autowired
    public void setEvaluationOnTheFlyService(EvaluationOnTheFlyService evaluationOnTheFlyService) {
        this.evaluationOnTheFlyService = evaluationOnTheFlyService;
    }

    @Autowired
    public void setLexiconService(LexiconService lexiconService) {
        this.lexiconService = lexiconService;
    }

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

    @PostMapping("/evaluate")
    @ApiOperation(value = "${evaluation.evaluateOnTheFly}", notes = "${evaluation.evaluateOnTheFly.notes}")
    public ResponseEntity<?> evaluateOnTheFly(
            @ApiParam(value = "${evaluation.evaluateOnTheFly.param.vtlRequest.description}")
            @RequestBody VtlRequest vtlRequest) throws Exception {
        log.info("Evaluate the data definition");
        vtlRequest.setRequestUuid(UUID.randomUUID());
        try {
            evaluationOnTheFlyService.createDatasets(vtlRequest);
            log.info("Evaluate the user function definition");
            try {
                evaluationOnTheFlyService.createFunctions(vtlRequest);
            } catch (Exception e) {
                return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
            }
            log.info("Evaluate the statements");
            String orderedStatement = lexiconService.checkSyntax(vtlRequest.getStatements());
            List<VtlStatement> result = parseTreeService.visitTree(orderedStatement);
            List<LinkedList<ResultExpression>> semanticResults = semanticService.validateSemantic(result, vtlRequest.getRequestUuid());
            if (vtlRequest.isOnlyValidation() || vtlRequest.isXmlResult()) {
                ResponseEntity<?> responseEntity = evaluationOnTheFlyService.validateStatement(semanticResults, vtlRequest);
                evaluationOnTheFlyService.eraseAllByRequestId(vtlRequest);
                return responseEntity;
            }
            LinkedList<SqlObject> translatedCommands = translationService.translateStatements(result, true, vtlRequest.getSqlType());
            ResponseEntity<?> responseEntity = evaluationOnTheFlyService.translateSQL(translatedCommands, vtlRequest);
            evaluationOnTheFlyService.eraseAllByRequestId(vtlRequest);
            return responseEntity;
        }catch(Exception e){
            evaluationOnTheFlyService.eraseAllByRequestId(vtlRequest);
            throw e;
        }
    }


}
