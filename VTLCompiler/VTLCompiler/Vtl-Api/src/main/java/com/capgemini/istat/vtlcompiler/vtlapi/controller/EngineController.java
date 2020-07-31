package com.capgemini.istat.vtlcompiler.vtlapi.controller;

import com.capgemini.istat.vtlcompiler.vtlapi.response.VtlCompilerResponse;
import com.capgemini.istat.vtlcompiler.vtlcommon.constant.ConstantUtility;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.dataset.VtlDataset;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.semantic.ResultExpression;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.statement.VtlStatement;
import com.capgemini.istat.vtlcompiler.vtlinterpreter.service.IParseTreeService;
import com.capgemini.istat.vtlcompiler.vtllexicon.LexiconService;
import com.capgemini.istat.vtlcompiler.vtlsdmxbuilder.service.writer.StructureService;
import com.capgemini.istat.vtlcompiler.vtlsemantic.service.SemanticService;
import com.capgemini.istat.vtlcompiler.vtlsqlbuilder.service.TranslationService;
import com.healthmarketscience.sqlbuilder.SqlObject;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;


@RestController
@RequestMapping("/engine")
@Api(value = "Set di Endpoint per accedere ai motori di elaborazione del linguaggio VTL")
public class EngineController extends ExceptionHandlerController {
    public static final Logger logger = LogManager.getLogger(EngineController.class);
    private IParseTreeService parseTreeService;
    private SemanticService semanticService;
    private TranslationService translationService;
    private LexiconService lexiconService;
    private StructureService structureService;



    
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

    @Autowired
    public void setStructureService(StructureService structureService) {
        this.structureService = structureService;
    }

    @Autowired
    public void setLexiconService(LexiconService lexiconService) {
        this.lexiconService = lexiconService;
    }

    @PostMapping("/buildStatements")
    @ApiOperation(value = "${engineController.buildStatements}", notes = "${engineController.buildStatements.notes}")
    public ResponseEntity<VtlCompilerResponse> buildStatements(@ApiParam(value = "${engineController.buildStatements.param.commandStatements.description}") @RequestBody String commandStatements) {
        VtlCompilerResponse vtlCompilerResponse = new VtlCompilerResponse();
        vtlCompilerResponse.setResultStatements(parseTreeService.visitTree(commandStatements));
        return new ResponseEntity<>(vtlCompilerResponse, HttpStatus.OK);
    }

    @PostMapping("/checkInstruction")
    @ApiOperation(value = "${engineController.checkInstruction}", notes = "${engineController.checkInstruction.notes}")
    public ResponseEntity<VtlCompilerResponse> checkInstruction(@ApiParam(value = "${engineController.checkInstruction.param.commandStatements.description}") @RequestBody String commandStatements) throws Exception {
        lexiconService.parse(commandStatements);
        VtlCompilerResponse vtlCompilerResponse = new VtlCompilerResponse();
        return new ResponseEntity<>(vtlCompilerResponse, HttpStatus.OK);
    }

    @PostMapping("/orderInstruction")
    @ApiOperation(value = "${engineController.orderInstruction}", notes = "${engineController.orderInstruction.notes}")
    public ResponseEntity<VtlCompilerResponse> orderInstruction(@ApiParam(value = "${engineController.orderInstruction.param.commandStatements.description}") @RequestBody String commandStatements) throws Exception {
        List<VtlStatement> result = lexiconService.orderVTLStatementsToList(commandStatements);

        VtlCompilerResponse vtlCompilerResponse = new VtlCompilerResponse();
        vtlCompilerResponse.setErrorResponse(null);
        vtlCompilerResponse.setResultStatements(result);

        return new ResponseEntity<>(vtlCompilerResponse, HttpStatus.OK);
    }

    @PostMapping("/validate/{resultType}")
    @ApiOperation(value = "${engineController.validate}", notes = "${engineController.validate.notes}")
    public ResponseEntity validate(@ApiParam(value = "${engineController.validate.param.resultType.description}", example = "json")
                                   @PathVariable String resultType,
                                   @ApiParam(value = "${engineController.validate.param.commandStatements.description}",
                                           type = "string")
                                   @RequestBody String commandStatements) throws Exception {
        String orderedStatement = lexiconService.checkSyntax(commandStatements);
        List<VtlStatement> result = parseTreeService.visitTree(orderedStatement);
        List<LinkedList<ResultExpression>> semanticResults = semanticService.validateSemantic(result, null);
        LinkedList<ResultExpression> resultSemantic = new LinkedList<>();
        for (LinkedList<ResultExpression> resultStatement : semanticResults) {
            resultSemantic.addLast(
                    resultStatement.getFirst()
            );
        }
        VtlCompilerResponse vtlCompilerResponse = new VtlCompilerResponse();
        vtlCompilerResponse.setResultSemantic(resultSemantic);
        if (resultType != null && resultType.equalsIgnoreCase("xml")) {
            VtlDataset lastResult = resultSemantic.getLast().getResult();
            ByteArrayOutputStream byteArrayOutputStream = structureService.writeStructureToStream(lastResult);
            Resource resource = new ByteArrayResource(byteArrayOutputStream.toByteArray());
            return ResponseEntity.ok()
                    .contentType(MediaType.parseMediaType(MediaType.APPLICATION_XML.toString()))
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + lastResult.getName() + ".xml" + "\"")
                    .body(resource);
        } else {
            return new ResponseEntity<>(vtlCompilerResponse, HttpStatus.OK);
        }
    }

    @PostMapping("/validateAndShowPassages")
    @ApiOperation(value = "${engineController.validateAndShowPassages}", notes = "${engineController.validateAndShowPassages.notes}")
    public ResponseEntity<VtlCompilerResponse> validateAndShowPassages(@ApiParam(value = "${engineController.validateAndShowPassages.param.commandStatements.description}") @RequestBody String commandStatements) throws Exception {
        String orderedStatement = lexiconService.checkSyntax(commandStatements);
        List<VtlStatement> result = parseTreeService.visitTree(orderedStatement);
        List<LinkedList<ResultExpression>> semanticResult = semanticService.validateSemantic(result, null);
        List<ResultExpression> resultSemantic = new ArrayList<>();
        for (LinkedList<ResultExpression> resultExpressions : semanticResult) {
            for (ResultExpression resultExpression : resultExpressions) {
                resultSemantic.add(resultExpression);
            }
        }
        VtlCompilerResponse vtlCompilerResponse = new VtlCompilerResponse();
        vtlCompilerResponse.setResultSemantic(resultSemantic);
        return new ResponseEntity<>(vtlCompilerResponse, HttpStatus.OK);
    }

    @PostMapping("/translate/{sqlType}")
    @ApiOperation(value = "${engineController.translate}", notes = "${engineController.translate.notes}")
    public ResponseEntity<VtlCompilerResponse> translate(@ApiParam(value = "${engineController.translate.param.commandStatements.description}") @RequestBody String commandStatements,
                                                         @ApiParam(value = "${engineController.translate.param.sqlType.description}") @PathVariable String sqlType) throws Exception {
        String orderedStatement = lexiconService.checkSyntax(commandStatements);
        List<VtlStatement> result = parseTreeService.visitTree(orderedStatement);
        semanticService.validateSemantic(result, null);
        LinkedList<SqlObject> translatedCommands = translationService.translateStatements(result, true, sqlType);
        LinkedList<String> resultSQL = new LinkedList<>();
        for (SqlObject sqlObject : translatedCommands) {
            if (sqlObject != null)
                resultSQL.add(sqlObject.toString() + ConstantUtility.SEMICOLON);
        }
        VtlCompilerResponse vtlCompilerResponse = new VtlCompilerResponse();
        vtlCompilerResponse.setResultSQL(resultSQL);
        return new ResponseEntity<>(vtlCompilerResponse, HttpStatus.OK);
    }


}
