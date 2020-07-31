package com.capgemini.istat.vtlcompiler.vtlcommon.model.semantic;

import com.capgemini.istat.vtlcompiler.vtlcommon.model.dataset.VtlComponent;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.dataset.VtlComponentRole;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.dataset.VtlDataset;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.dataset.VtlType;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * La classe contiene tutte le informazioni necessarie ad effettuare le validazioni semantiche
 * Sono presenti tutte le combinazioni possibili di validazione,  sui componenti, sui dataset e sulle userDefine.
 * Le proprietà della classe vengono popolate secondo l'esigenza di validazione.
 *
 * @see VtlDataset
 * @see VtlComponent
 * @see ValidationCheck
 * @see VtlType
 * @see VtlComponentRole
 */
public class ValidationData implements Serializable {

    private List<VtlDataset> vtlDatasets;
    private List<VtlComponent> vtlComponents;
    private String componentName;
    private String datasetName;
    private List<ValidationCheck> validationChecks;
    private VtlType vtlType;
    private VtlComponentRole vtlComponentRole;
    private String valueDomain;
    private boolean ignoreCase = true;
    private UUID requestUuid;

    public List<VtlDataset> getVtlDatasets() {
        if (this.vtlDatasets == null)
            this.vtlDatasets = new ArrayList<>();
        return vtlDatasets;
    }

    public void setVtlDatasets(List<VtlDataset> vtlDatasets) {
        this.vtlDatasets = vtlDatasets;
    }

    public List<VtlComponent> getVtlComponents() {
        if (vtlComponents == null)
            this.vtlComponents = new ArrayList<>();
        return vtlComponents;
    }

    public void setVtlComponents(List<VtlComponent> vtlComponents) {
        this.vtlComponents = vtlComponents;
    }

    public String getComponentName() {
        return componentName;
    }

    public void setComponentName(String componentName) {
        this.componentName = componentName;
    }

    public String getDatasetName() {
        return datasetName;
    }

    public void setDatasetName(String datasetName) {
        this.datasetName = datasetName;
    }

    public List<ValidationCheck> getValidationChecks() {
        if (this.validationChecks == null)
            this.validationChecks = new ArrayList<>();
        return validationChecks;
    }

    public void setValidationChecks(List<ValidationCheck> validationChecks) {
        this.validationChecks = validationChecks;
    }

    public VtlType getVtlType() {
        return vtlType;
    }

    public void setVtlType(VtlType vtlType) {
        this.vtlType = vtlType;
    }

    public VtlComponentRole getVtlComponentRole() {
        return vtlComponentRole;
    }

    public void setVtlComponentRole(VtlComponentRole vtlComponentRole) {
        this.vtlComponentRole = vtlComponentRole;
    }

    public String getValueDomain() {
        return valueDomain;
    }

    public void setValueDomain(String valueDomain) {
        this.valueDomain = valueDomain;
    }

    public boolean isIgnoreCase() {
        return ignoreCase;
    }

    public void setIgnoreCase(boolean ignoreCase) {
        this.ignoreCase = ignoreCase;
    }

    public UUID getRequestUuid() {
        return requestUuid;
    }

    public void setRequestUuid(UUID requestUuid) {
        this.requestUuid = requestUuid;
    }
}
