package com.capgemini.istat.vtlcompiler.vtlapi.model;

import com.capgemini.istat.vtlcompiler.vtlcommon.model.dataset.VtlComponentRole;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.dataset.VtlType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

@ApiModel(description = "La classe rappresenta la struttura di un componente")
public class VtlComponentLite implements Serializable {
    @ApiModelProperty(notes = "${vtlComponent.type.notes}",
            example = "INTEGER", required = true, position = 1)
    private VtlType type;
    @ApiModelProperty(notes = "${vtlComponent.name.notes}",
            example = "id_1", required = true, position = 0)
    private String name;
    @ApiModelProperty(notes = "${vtlComponent.domainValue.notes}",
            example = "vd_integer", required = true, position = 3)
    private String domainValue;
    @ApiModelProperty(notes = "${vtlComponent.domainValueParent.notes}",
            example = "vd_number", required = true, position = 4)
    private String domainValueParent;
    @ApiModelProperty(notes = "${vtlComponent.vtlComponentRole.notes}",
            example = "IDENTIFIER", required = true, position = 2)
    private VtlComponentRole vtlComponentRole;

    public VtlType getType() {
        return type;
    }

    public void setType(VtlType type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDomainValue() {
        return domainValue;
    }

    public void setDomainValue(String domainValue) {
        this.domainValue = domainValue;
    }

    public String getDomainValueParent() {
        return domainValueParent;
    }

    public void setDomainValueParent(String domainValueParent) {
        this.domainValueParent = domainValueParent;
    }

    public VtlComponentRole getVtlComponentRole() {
        return vtlComponentRole;
    }

    public void setVtlComponentRole(VtlComponentRole vtlComponentRole) {
        this.vtlComponentRole = vtlComponentRole;
    }
}
