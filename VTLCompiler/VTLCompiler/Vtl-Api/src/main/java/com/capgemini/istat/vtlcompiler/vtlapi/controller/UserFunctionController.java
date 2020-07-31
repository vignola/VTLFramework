package com.capgemini.istat.vtlcompiler.vtlapi.controller;

import com.capgemini.istat.vtlcompiler.vtlcommon.model.define.UserFunction;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.exception.FunctionExistException;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.exception.NotAFunctionException;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.statement.VtlStatement;
import com.capgemini.istat.vtlcompiler.vtlcommon.service.VtlUserFunctionService;
import com.capgemini.istat.vtlcompiler.vtlinterpreter.service.IParseTreeService;
import com.capgemini.istat.vtlcompiler.vtlsemantic.service.SemanticService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/userFunctionOperators")
@Api(value = "Set di Endpoint per definire userFunction nel linguaggio VTL")
public class UserFunctionController extends ExceptionHandlerController {
    private IParseTreeService engineService;
    private VtlUserFunctionService vtlUserFunctionService;
    private SemanticService semanticService;
/*    private final MessageSource messageSource;

    public UserFunctionController(MessageSource messageSource) {
        this.messageSource = messageSource;
    }*/


    @Autowired
    public void setEngineService(IParseTreeService engineService) {
        this.engineService = engineService;
    }

    @Autowired
    public void setVtlUserFunctionService(VtlUserFunctionService vtlUserFunctionService) {
        this.vtlUserFunctionService = vtlUserFunctionService;
    }

    @Autowired
    public void setSemanticService(SemanticService semanticService) {
        this.semanticService = semanticService;
    }

    @GetMapping("/")
    @ApiOperation(
            value = "${userFunctionController.getAllFunction}",
            notes = "${userFunctionController.getAllFunction.notes}"
    )
    public ResponseEntity<List<UserFunction>> getAllFunction() {
        return new ResponseEntity<>(vtlUserFunctionService.getAllFunctions(), HttpStatus.OK);
    }


    @PostMapping("/createUserFunction")
    @ApiOperation(
            value = "${userFunctionController.createUserFunction}",
            notes = "${userFunctionController.createUserFunction.notes}"
    )
    public ResponseEntity createUserFunction(
            @ApiParam(value = "${userFunctionController.createUserFunction.param.function.description}")
            @RequestBody String function) throws Exception {
        List<VtlStatement> result = engineService.visitTree(function);
        semanticService.validateSemantic(result, null);
        try {
            vtlUserFunctionService.createVtlUserFunction(result.get(0), function);
        } catch (NotAFunctionException e) {
            return new ResponseEntity("Il comando inserito non è un define operator", HttpStatus.BAD_REQUEST);
        } catch (FunctionExistException e) {
            return new ResponseEntity("il nome della funzione è già stato utilizzato", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity("Operazione effettuata con successo", HttpStatus.OK);
    }

    @PutMapping("/editUserFunction")
    @ApiOperation(
            value = "${userFunctionController.editUserFunction}",
            notes = "${userFunctionController.editUserFunction.notes}"
    )
    public ResponseEntity editUserFunction(
            @ApiParam(value = "${userFunctionController.editUserFunction.param.function.description}")
            @RequestBody String function) throws Exception {
        List<VtlStatement> result = engineService.visitTree(function);
        semanticService.validateSemantic(result, null);
        try {
            vtlUserFunctionService.editVtlUserFunction(result.get(0), function);
        } catch (NotAFunctionException e) {
            return new ResponseEntity("Il comando inserito non è un define operator", HttpStatus.BAD_REQUEST);
        } catch (FunctionExistException e) {
            return new ResponseEntity("La funzione non esiste", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity("Operazione effettuata con successo", HttpStatus.OK);
    }

    @GetMapping("/getByName/{userFunctionName}")
    @ApiOperation(
            value = "${userFunctionController.getByName}",
            notes = "${userFunctionController.getByName.notes}"
    )
    public ResponseEntity<UserFunction> getByName(
            @ApiParam(value = "${userFunctionController.getByName.param.userFunctionName.description}")
            @PathVariable String userFunctionName) {
        return new ResponseEntity<>(vtlUserFunctionService.getByName(userFunctionName), HttpStatus.OK);
    }

    @DeleteMapping("/deleteByName/{userFunctionName}")
    @ApiOperation(
            value = "${userFunctionController.deleteByName}",
            notes = "${userFunctionController.deleteByName.notes}"
    )
    public ResponseEntity deleteByName(
            @ApiParam(value = "${userFunctionController.deleteByName.param.userFunctionName.description}")
            @PathVariable String userFunctionName) {
        Integer deletedElements = vtlUserFunctionService.deleteByName(userFunctionName);
        if (deletedElements == 0) {
            return new ResponseEntity("Nessun elemento cancellato", HttpStatus.OK);
        } else {
            return new ResponseEntity("Elemento cancellato con successo", HttpStatus.OK);
        }
    }

    @GetMapping("/getByType/{functionType}")
    @ApiOperation(
            value = "${userFunctionController.getAllFunctionByType}",
            notes = "${userFunctionController.getAllFunctionByType.notes}"
    )
    public ResponseEntity<List<UserFunction>> getAllFunctionByType(
            @ApiParam(value = "${userFunctionController.getAllFunctionByType.param.functionType.description}")
            @PathVariable String functionType) {
        return new ResponseEntity<>(vtlUserFunctionService.getAllFunctionByType(functionType), HttpStatus.OK);
    }


}
