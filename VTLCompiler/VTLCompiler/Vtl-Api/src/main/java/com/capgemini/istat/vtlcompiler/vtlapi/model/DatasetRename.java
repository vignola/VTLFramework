package com.capgemini.istat.vtlcompiler.vtlapi.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "La classe contiene i dati di rinominazione delle tabelle per l'elaborazione on the fly")
public class DatasetRename {
    @ApiModelProperty(notes = "${datasetRename.name.notes}", example = "${datasetRename.name.example}", required = true, position = 0)
    private String name;
    @ApiModelProperty(notes = "${datasetRename.alias.notes}", example = "${datasetRename.alias.example}", required = true, position = 1)
    private String alias;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }
}
