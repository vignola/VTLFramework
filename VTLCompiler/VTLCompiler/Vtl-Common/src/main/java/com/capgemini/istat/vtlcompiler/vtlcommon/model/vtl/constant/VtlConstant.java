package com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.constant;

import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.VtlObject;
import com.fasterxml.jackson.annotation.*;

import java.io.Serializable;
import java.util.UUID;

@JsonIdentityInfo(
        generator = ObjectIdGenerators.IntSequenceGenerator.class,
        property = "oid"
)
@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.EXISTING_PROPERTY, //field must be present in the POJO
        property = "clazz")

@JsonSubTypes({
        @JsonSubTypes.Type(value = VtlBoolean.class),
        @JsonSubTypes.Type(value = VtlDuration.class),
        @JsonSubTypes.Type(value = VtlFloat.class),
        @JsonSubTypes.Type(value = VtlInteger.class),
        @JsonSubTypes.Type(value = VtlNull.class),
        @JsonSubTypes.Type(value = VtlString.class)
})

public abstract class VtlConstant<T> implements VtlObject, Serializable {
    @JsonProperty
    private String uuid;

    private UUID requestUuid;

    @JsonProperty
    private String clazz = this.getClass().getSimpleName();

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getClazz() {
        return clazz;
    }

    public void setClazz(String clazz) {
        this.clazz = clazz;
    }

    public abstract T getValue();

    public abstract T getFormattedValue();

    public abstract void setValue(T value);

    public UUID getRequestUuid() {
        return requestUuid;
    }

    public void setRequestUuid(UUID requestUuid) {
        this.requestUuid = requestUuid;
    }
}
