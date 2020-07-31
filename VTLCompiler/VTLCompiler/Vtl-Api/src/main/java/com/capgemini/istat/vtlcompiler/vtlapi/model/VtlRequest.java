package com.capgemini.istat.vtlcompiler.vtlapi.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

/**
 * La classe contiene tutti i dati che provengono dalle richieste per l'elaborazione On The Fly. Questa classe non
 * contiene logica. E' solo un contenitore e offre metodi di getting e setting dell'oggetto.
 */
@ApiModel(description = "La classe rappresenta una richiesta completa di contesto per l'esecuzione on the fly")
public class VtlRequest implements Serializable {
    @ApiModelProperty(notes = "${vtlRequest.functions.notes}",
            example = "define operator max_int (x integer, y integer) returns integer is if x > y then x else y end operator;",
            required = false, position = 0)
    private String functions;

    @ApiModelProperty(notes = "${vtlRequest.datasets.notes}",
            example = "[{\"name\":\"ds_1\",\"isPersistent\":true,\"componentsDescriptions\":\"ID_1,STRING,IDENTIFIER;ME_1,NUMBER,MEASURE\"},{\"name\":\"ds_2\",\"isPersistent\":true,\"componentsDescriptions\":\"ID_1,STRING,IDENTIFIER;ID_2,STRING,IDENTIFIER;ME_1,NUMBER,MEASURE\"},{\"name\":\"ds_3\",\"isPersistent\":true,\"vtlComponents\":[{\"name\":\"ID_1\",\"type\":\"STRING\",\"vtlComponentRole\":\"IDENTIFIER\",\"valueDomain\":\"string_vd\",\"valueDomainParent\":\"string_vd\"},{\"name\":\"ID_2\",\"type\":\"STRING\",\"vtlComponentRole\":\"IDENTIFIER\",\"valueDomain\":\"string_vd\",\"valueDomainParent\":\"string_vd\"},{\"name\":\"ME_1\",\"type\":\"NUMBER\",\"vtlComponentRole\":\"MEASURE\",\"valueDomain\":\"string_vd\",\"valueDomainParent\":\"string_vd\"}]}]",
            required = false, position = 1)
    private List<VtlDatasetLite> datasets;

    @ApiModelProperty(notes = "${vtlRequest.statements.notes}",
            example = "ds_r := 2+5; ds_r2 := Ds_2 +4; DS_r3 := max_int(2, 3);ds_4 <- ds_1+ds_2+ds_3;",
            required = true, position = 2)
    private String statements;

    @ApiModelProperty(notes = "${vtlRequest.requestUuid.notes}",
            example = "756ef45a-7340-11ea-bc55-0242ac130003",
            required = false, position = 3)
    private UUID requestUuid;

    @ApiModelProperty(notes = "${vtlRequest.onlyValidation.notes}",
            example = "false",
            required = false, position = 4)
    private boolean onlyValidation;

    @ApiModelProperty(notes = "${vtlRequest.xmlResult.notes}",
            example = "false",
            required = false, position = 5)
    private boolean xmlResult;

    @ApiModelProperty(notes = "${vtlRequest.sqlType.notes}",
            example = "oracleSql",
            required = false, position = 6)
    private String sqlType;

    @ApiModelProperty(notes = "${vtlRequest.aliases.notes}",
            example = "[{\"name\":\"dS_2\", \"alias\":\"test\"}]",
            required = false, position = 7)
    private List<DatasetRename> aliases;

    public VtlRequest() {
    }

    public String getFunctions() {
        return functions;
    }

    public void setFunctions(String functions) {
        this.functions = functions;
    }

    public List<VtlDatasetLite> getDatasets() {
        return datasets;
    }

    public void setDatasets(List<VtlDatasetLite> datasets) {
        this.datasets = datasets;
    }

    public String getStatements() {
        return statements;
    }

    public void setStatements(String statements) {
        this.statements = statements;
    }

    public UUID getRequestUuid() {
        return requestUuid;
    }

    public void setRequestUuid(UUID requestUuid) {
        this.requestUuid = requestUuid;
    }

    public boolean isOnlyValidation() {
        return onlyValidation;
    }

    public void setOnlyValidation(boolean onlyValidation) {
        this.onlyValidation = onlyValidation;
    }

    public boolean isXmlResult() {
        return xmlResult;
    }

    public void setXmlResult(boolean xmlResult) {
        this.xmlResult = xmlResult;
    }

    public String getSqlType() {
        return sqlType;
    }

    public void setSqlType(String sqlType) {
        this.sqlType = sqlType;
    }

    public List<DatasetRename> getAliases() {
        return aliases;
    }

    public void setAliases(List<DatasetRename> aliases) {
        this.aliases = aliases;
    }
}
