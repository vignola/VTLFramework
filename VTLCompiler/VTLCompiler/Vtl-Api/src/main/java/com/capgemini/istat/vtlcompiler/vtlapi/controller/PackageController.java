package com.capgemini.istat.vtlcompiler.vtlapi.controller;

import com.capgemini.istat.vtlcompiler.vtlapi.response.VtlCompilerResponse;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.exception.TraslationException;
import com.capgemini.istat.vtlcompiler.vtlsqlbuilder.service.vtlpackage.VtlPackageService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.LinkedList;

@Controller
@RequestMapping("/vtlPackage")
@Api(value = "Endpoints per ottenere i package SQL")
public class PackageController extends ExceptionHandlerController {
    private VtlPackageService vtlPackageService;
    public static final Logger logger = LogManager.getLogger(PackageController.class);


    @Autowired
    public void setVtlPackageService(VtlPackageService vtlPackageService) {
        this.vtlPackageService = vtlPackageService;
    }

    @ApiOperation(value = "Deprecato. Non utilizzare", notes = "Deprecato. Non utilizzare")
    @GetMapping("/getContent")
    public ResponseEntity<VtlCompilerResponse> vtlPackage() throws TraslationException {
        VtlCompilerResponse vtlCompilerResponse = new VtlCompilerResponse();
        LinkedList<String> resultSQL = new LinkedList<>();
        resultSQL.add(vtlPackageService.getVtlPackage());
        vtlCompilerResponse.setResultSQL(resultSQL);
        return new ResponseEntity<>(vtlCompilerResponse, HttpStatus.OK);
    }

    @GetMapping("/file/{fileName}")
    @ApiOperation(value = "${vtlPackage.downloadFile}", notes = "${vtlPackage.downloadFile.notes}")
    public ResponseEntity<Resource> downloadFile(
            @ApiParam(value = "${vtlPackage.downloadFile.param.filename.description}")
            @PathVariable String fileName, HttpServletRequest request) {
        // Load file as Resource
        Resource resource = vtlPackageService.getResource(fileName);
        // Try to determine file's content type
        String contentType = null;
        try {
            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        } catch (IOException ex) {
            logger.info("Could not determine file type.");
        }
        // Fallback to the default content type if type could not be determined
        if (contentType == null) {
            contentType = "application/octet-stream";
        }
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }
}
