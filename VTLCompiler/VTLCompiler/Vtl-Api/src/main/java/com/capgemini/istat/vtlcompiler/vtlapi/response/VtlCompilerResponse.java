package com.capgemini.istat.vtlcompiler.vtlapi.response;

import com.capgemini.istat.vtlcompiler.vtlcommon.model.semantic.ResultExpression;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.statement.VtlStatement;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

public class VtlCompilerResponse {
    @ApiModelProperty(notes = "un possibile errore riscontrato durante l'esecuzione",
            example = "\"code\": \"VTL015\",\n" +
                    "        \"message\": \"Il dataset pippo non esiste.  ->  pippo\",\n" +
                    "        \"command\": \"ds_r := 2+pippo\"",
            required = true, position = 0)
    private ErrorResponse errorResponse;
    @ApiModelProperty(notes = "La lista degli oggetti interpretati")
    private List<VtlStatement> resultStatements;
    @ApiModelProperty(notes = "La lista delle strutture semantiche validate")
    private List<ResultExpression> resultSemantic;
    @ApiModelProperty(notes = "La lista delle istruzioni SQL tradotte")
    private List<String> resultSQL;

    public ErrorResponse getErrorResponse() {
        return errorResponse;
    }

    public void setErrorResponse(ErrorResponse errorResponse) {
        this.errorResponse = errorResponse;
    }

    public List<VtlStatement> getResultStatements() {
        return resultStatements;
    }

    public void setResultStatements(List<VtlStatement> resultStatements) {
        this.resultStatements = resultStatements;
    }

    public List<ResultExpression> getResultSemantic() {
        return resultSemantic;
    }

    public void setResultSemantic(List<ResultExpression> resultSemantic) {
        this.resultSemantic = resultSemantic;
    }

    public List<String> getResultSQL() {
        return resultSQL;
    }

    public void setResultSQL(List<String> resultSQL) {
        this.resultSQL = resultSQL;
    }
}
