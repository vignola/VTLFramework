package com.capgemini.istat.vtlcompiler.vtlapi.controller;

import com.capgemini.istat.vtlcompiler.vtlapi.model.VtlComponentLite;
import com.capgemini.istat.vtlcompiler.vtlapi.model.VtlDatasetLite;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.dataset.VtlComponent;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.dataset.VtlDataset;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.valuedomain.ValueDomain;
import com.capgemini.istat.vtlcompiler.vtlcommon.service.IDatasetIntegration;
import com.capgemini.istat.vtlcompiler.vtlcommon.service.VtlDatasetService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/vtlDatasets")
@Api(value = "Set di Endpoint per gestire i dataset VTL")
public class VtlDatasetController {

	private IDatasetIntegration datasetIntegrationService;
    private VtlDatasetService vtlDatasetService;

    @Autowired
    public void setDatasetIntegration(IDatasetIntegration datasetIntegrationService) {
		this.datasetIntegrationService = datasetIntegrationService;
	}

    @Autowired
    public void setVtlDatasetService(VtlDatasetService vtlDatasetService) {
        this.vtlDatasetService = vtlDatasetService;
    }

    @GetMapping("/")
    @ApiOperation(value = "${vtlDatasetController.getAllDatasets}", notes = "${vtlDatasetController.getAllDatasets.notes}")
    public @ResponseBody ResponseEntity<List<VtlDataset>> getAllDatasets() {
        return new ResponseEntity<>(datasetIntegrationService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/allAsc")
    @ApiOperation(value = "${vtlDatasetController.getAllDatasetsAsc}", notes = "${vtlDatasetController.getAllDatasetsAsc.notes}")
    public @ResponseBody ResponseEntity<List<VtlDataset>> getAllDatasetsAsc() {
        return new ResponseEntity<>(datasetIntegrationService.findAllByOrderByNameAsc(), HttpStatus.OK);
    }

    @PostMapping("/createVtlDataset")
    @ApiOperation(value = "${vtlDatasetController.createVtlDataset}", notes = "${vtlDatasetController.createVtlDataset.notes}")
    public @ResponseBody
    ResponseEntity createVtlDataset(@RequestBody VtlDatasetLite vtlDataset) {
        if (vtlDataset.getName() != null)
            datasetIntegrationService.deleteByName(vtlDataset.getName());
        VtlDataset vtlDatasetCreated = vtlDatasetService.createVtlDataset(convert(vtlDataset));
        if (vtlDatasetCreated == null)
            return new ResponseEntity<String>("non è possibile creare il dataset manca un campo obbligatorio. Campi obbligatori -> name dataset, name component, type component, role component ", HttpStatus.BAD_REQUEST);
        return new ResponseEntity<VtlDataset>(vtlDatasetCreated, HttpStatus.OK);
    }

    @PostMapping("/createVtlDatasetByString")
    @ApiOperation(value = "${vtlDatasetController.createVtlDatasetByString}", notes = "${vtlDatasetController.createVtlDatasetByString.notes}")
    public @ResponseBody
    ResponseEntity createVtlDatasetByString(@RequestBody VtlDatasetLite vtlDataset) {
        if (vtlDataset.getName() != null)
            datasetIntegrationService.deleteByName(vtlDataset.getName());
        VtlDataset vtlDatasetCreated = vtlDatasetService.createVtlDatasetByString(convert(vtlDataset), vtlDataset.getComponentsDescriptions());
        if (vtlDatasetCreated == null)
            return new ResponseEntity<String>("non è possibile creare il dataset manca un campo obbligatorio. Campi obbligatori -> name dataset, name component, type component, role component ", HttpStatus.BAD_REQUEST);
        return new ResponseEntity<VtlDataset>(vtlDatasetCreated, HttpStatus.OK);
    }

    @GetMapping("getByName/{vtlDatasetName}")
    @ApiOperation(value = "${vtlDatasetController.getByName}", notes = "${vtlDatasetController.getByName.notes}")
    public @ResponseBody
    ResponseEntity<VtlDataset> getByName(
            @ApiParam(value = "${vtlDatasetController.getByName.param.vtlDatasetName.description}")
            @PathVariable String vtlDatasetName
    ) {
        VtlDataset result = datasetIntegrationService.findByNameIgnoreCase(vtlDatasetName, null);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @DeleteMapping("/deleteByName/{vtlDatasetName}")
    @ApiOperation(value = "${vtlDatasetController.deleteVtlDataset}", notes = "${vtlDatasetController.deleteVtlDataset.notes}")
    public @ResponseBody
    ResponseEntity<String> deleteVtlDataset(
            @ApiParam(value = "${vtlDatasetController.deleteVtlDataset.param.vtlDatasetName.description}")
            @PathVariable String vtlDatasetName
    ) {
        Integer cancelled = datasetIntegrationService.deleteByName(vtlDatasetName);
        String message = cancelled > 0 ? "Dataset Cancellato" : "Dataset non Cancellato";
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @DeleteMapping("/deleteAll")
    @ApiOperation(value = "${vtlDatasetController.deleteAll}", notes = "${vtlDatasetController.deleteAll.notes}")
    public  @ResponseBody ResponseEntity<String> deleteAll() {
    	datasetIntegrationService.deleteAll();
        return  new ResponseEntity<>("Tutti i dataset sono stati cancellati.", HttpStatus.OK);
    }

    @PostMapping("/setUpVtlDatasets")
    @ApiOperation(value = "${vtlDatasetController.setUpVtlDatasets}", notes = "${vtlDatasetController.setUpVtlDatasets.notes}")
    public @ResponseBody ResponseEntity setUpVtlDatasets() {
        boolean mockCreated = vtlDatasetService.setUpMockVtlDatasets();
        if(!mockCreated)
            return new ResponseEntity<String>("Operazione non effettuata. Sono stati già creati alcuni dataset", HttpStatus.OK);
        return new ResponseEntity<List<VtlDataset>>(datasetIntegrationService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/getValueDomain/{valueDomainName}")
    @ApiOperation(value = "${vtlDatasetController.getValDomainDatasets}", notes = "${vtlDatasetController.getValDomainDatasets.notes}")
    public @ResponseBody
    ResponseEntity<List<ValueDomain>> getValDomainDatasets(
            @ApiParam(value = "${vtlDatasetController.getValDomainDatasets.param.valueDomainName.description}")
            @PathVariable String valueDomainName
    ) {
        return new ResponseEntity<>(datasetIntegrationService.getAllByValueDomainNameIgnoreCase(valueDomainName), HttpStatus.OK);
    }

    @GetMapping("/getValueDomain/{valueDomainName}/{valueDomainCode}")
    @ApiOperation(value = "${vtlDatasetController.getValDomainDatasetsForValue}", notes = "${vtlDatasetController.getValDomainDatasetsForValue.notes}")
    public @ResponseBody
    ResponseEntity<ValueDomain> getValDomainDatasetsForValue(
            @ApiParam(value = "${vtlDatasetController.getValDomainDatasetsForValue.param.valueDomainName.description}")
            @PathVariable String valueDomainName,
            @ApiParam(value = "${vtlDatasetController.getValDomainDatasetsForValue.param.valueDomainCode.description}")
            @PathVariable String valueDomainCode) {
        return new ResponseEntity<>(datasetIntegrationService.getByValueDomainNameIgnoreCaseAndCodeIgnoreCase(valueDomainName, valueDomainCode), HttpStatus.OK);
    }

    private VtlDataset convert(VtlDatasetLite vtlDatasetLite) {
        VtlDataset vtlDataset = new VtlDataset();
        BeanUtils.copyProperties(vtlDatasetLite, vtlDataset);
        vtlDataset.setVtlComponents(new ArrayList<>());
        if (vtlDatasetLite.getVtlComponents() != null)
            for (VtlComponentLite vtlComponentLite : vtlDatasetLite.getVtlComponents()) {
                VtlComponent vtlComponent = new VtlComponent();
                BeanUtils.copyProperties(vtlComponentLite, vtlComponent);
                vtlDataset.addComponent(vtlComponent);
            }
        return vtlDataset;
    }
}
