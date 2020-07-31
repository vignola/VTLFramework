package com.capgemini.istat.vtlcompiler.vtlapi.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

@ApiModel(description = "La classe rappresenta la struttura di un dataset")
public class VtlDatasetLite implements Serializable {
    @ApiModelProperty(notes = "${vtlDataset.name.notes}",
            example = "ds_1",
            required = true, position = 0)
    private String name;

    @ApiModelProperty(notes = "${vtlDataset.description.notes}",
            example = "esempio di descrizione",
            required = false, position = 1)
    private String description;

    @ApiModelProperty(notes = "${vtlDataset.isOnlyAScalar.notes}",
            example = "false",
            required = false, position = 2)
    private Boolean isOnlyAScalar;

    @ApiModelProperty(notes = "${vtlDataset.isPersistent.notes}",
            example = "false",
            required = false, position = 3)
    @JsonProperty("isPersistent")
    private Boolean isPersistent;

    @ApiModelProperty(notes = "${vtlDataset.vtlComponentLites.notes}",

            required = true, position = 4)
    private List<VtlComponentLite> vtlComponentLites;

    @ApiModelProperty(notes = "${vtlDataset.componentsDescriptions.notes}",
            example = "ID_1,STRING,IDENTIFIER;ME_1,NUMBER,MEASURE",
            required = false, position = 5)
    private String componentsDescriptions;

    @ApiModelProperty(notes = "${vtlDataset.requestUuid.notes}",
            example = "756ef45a-7340-11ea-bc55-0242ac130003",
            required = false, position = 6)
    private UUID requestUuid;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getOnlyAScalar() {
        return isOnlyAScalar;
    }

    public void setOnlyAScalar(Boolean onlyAScalar) {
        if (onlyAScalar == null)
            this.isOnlyAScalar = false;
        else
            this.isOnlyAScalar = onlyAScalar;
    }

    public Boolean getPersistent() {
        return isPersistent;
    }

    public void setPersistent(Boolean persistent) {
        if (persistent == null)
            this.isPersistent = false;
        else
            this.isPersistent = persistent;
    }

    public List<VtlComponentLite> getVtlComponents() {
        return vtlComponentLites;
    }

    public void setVtlComponents(List<VtlComponentLite> vtlComponentLites) {
        this.vtlComponentLites = vtlComponentLites;
    }

    public String getComponentsDescriptions() {
        return componentsDescriptions;
    }

    public void setComponentsDescriptions(String componentsDescriptions) {
        this.componentsDescriptions = componentsDescriptions;
    }

    public UUID getRequestUuid() {
        return requestUuid;
    }

    public void setRequestUuid(UUID requestUuid) {
        this.requestUuid = requestUuid;
    }
}
