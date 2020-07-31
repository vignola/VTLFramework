package com.capgemini.istat.vtlcompiler.vtlapi.controller;


import com.capgemini.istat.vtlcompiler.vtlcommon.model.dataset.VtlDataset;
import com.capgemini.istat.vtlcompiler.vtlcommon.service.DatasetIntegrationService;
import com.capgemini.istat.vtlcompiler.vtlsdmxbuilder.service.writer.StructureService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.io.ByteArrayOutputStream;


@Controller
@Api(value = "Set di Endpoint per ottenere le strutture dei dataset")
public class SdmxController extends ExceptionHandlerController {
    private StructureService structureService;
    private DatasetIntegrationService datasetIntegrationService;


    @Autowired
    public void setStructureService(StructureService structureService) {
        this.structureService = structureService;
    }

    @Autowired
    public void setDatasetIntegrationService(DatasetIntegrationService datasetIntegrationService) {
        this.datasetIntegrationService = datasetIntegrationService;
    }


    @GetMapping(value = "/file/dsd/{datasetName}")
    @ApiOperation(
            value = "${sdmxController.downloadFile}",
            notes = "${sdmxController.downloadFile.notes}")

    public ResponseEntity<Resource> downloadFile(
            @ApiParam(value = "${sdmxController.downloadFile.param.datasetName.description}")
            @PathVariable String datasetName) {
        VtlDataset vtlDatasetFound = datasetIntegrationService.findByNameIgnoreCase(datasetName, null);
        ByteArrayOutputStream byteArrayOutputStream = structureService.writeStructureToStream(vtlDatasetFound);
        Resource resource = new ByteArrayResource(byteArrayOutputStream.toByteArray());
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(MediaType.APPLICATION_XML.toString()))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + datasetName + ".xml" + "\"")
                .body(resource);
    }
}
