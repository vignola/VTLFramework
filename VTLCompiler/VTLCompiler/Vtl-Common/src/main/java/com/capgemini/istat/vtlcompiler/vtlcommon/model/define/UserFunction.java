package com.capgemini.istat.vtlcompiler.vtlcommon.model.define;

import javax.persistence.*;
import java.io.Serializable;
import java.util.UUID;

/**
 * la classe rappresenta una funzione VTL definita dall'utente. La classe rappresenta anche una tabella presente sul
 * database intero dell'applicazione.
 * La classe contiene il tipo della funzione e il suo contenuto.
 */
@Entity
@Table(name = "user_function")
public class UserFunction implements Serializable {
    @GeneratedValue(generator = "temporary_function_identifier", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(allocationSize = 1, initialValue = 1, name = "temporary_function_identifier", sequenceName = "temporary_function_identifier")
    @Id
    private Integer id;
    private String functionName;
    @Enumerated(EnumType.ORDINAL)
    private VtlUserFunctionType functionType;
    @Column(name = "function_content", columnDefinition = "CLOB NOT NULL")
    @Lob
    private String functionContent;
    @Column(name = "request_uuid")
    private UUID requestUuid;

    private boolean definitive;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFunctionName() {
        return functionName;
    }

    public void setFunctionName(String functionName) {
        this.functionName = functionName;
    }

    public VtlUserFunctionType getFunctionType() {
        return functionType;
    }

    public void setFunctionType(VtlUserFunctionType functionType) {
        this.functionType = functionType;
    }

    public String getFunctionContent() {
        return functionContent;
    }

    public void setFunctionContent(String functionContent) {
        this.functionContent = functionContent;
    }

    public UUID getRequestUuid() {
        return requestUuid;
    }

    public void setRequestUuid(UUID requestUuid) {
        this.requestUuid = requestUuid;
    }

    public boolean isDefinitive() {
        return definitive;
    }

    public void setDefinitive(boolean definitive) {
        this.definitive = definitive;
    }
}
