package com.capgemini.istat.vtlcompiler.vtlcommon.model.dataset;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

/**
 * La classe rappresenta un componente di una tabella.
 * Le principali proprietà della classe sono il tipo, il nome,il ruolo, il value domain e il valueDomainParent e il requestUuid.
 * La classe rappresenta anche una tabella presente sul database temporaneo
 *
 * @see VtlType
 */
@Entity
@Table(name = "component")
@JsonIgnoreProperties(value = {"vtlDataset"})
public class VtlComponent implements Serializable {

    @GeneratedValue(generator = "temporary_component_identifier", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(allocationSize = 1, initialValue = 1, name = "temporary_component_identifier", sequenceName = "temporary_component_identifier")
    @Id
    @Column(name = "component_id")
    private Long id;

    @Enumerated(EnumType.STRING)
    private VtlType type;
    private String name;
    @Column(name = "domain_value")
    private String domainValue;
    @Column(name = "domain_value_parent")
    private String domainValueParent;
    private Boolean scalar;

    @Enumerated(EnumType.STRING)
    private VtlComponentRole vtlComponentRole;
    private Boolean ignoreNameCheck = false;
    private UUID requestUuid;

    @ManyToOne(/*fetch = FetchType.LAZY,*/ cascade = CascadeType.ALL)
    @JoinColumn(name = "dataset_id", referencedColumnName = "dataset_id")
    private VtlDataset vtlDataset;


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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public VtlComponentRole getVtlComponentRole() {
        return vtlComponentRole;
    }

    public void setVtlComponentRole(VtlComponentRole vtlComponentRole) {
        this.vtlComponentRole = vtlComponentRole;
    }


    public VtlDataset getVtlDataset() {
        return vtlDataset;
    }

    public void setVtlDataset(VtlDataset vtlDataset) {
        this.vtlDataset = vtlDataset;
    }

    public String getDomainValueParent() {
        return domainValueParent;
    }

    public void setDomainValueParent(String domainValueParent) {
        this.domainValueParent = domainValueParent;
    }

    @JsonIgnore
    public Boolean getIgnoreNameCheck() {
        return ignoreNameCheck;
    }

    @JsonIgnore
    public void setIgnoreNameCheck(Boolean scalar) {
        ignoreNameCheck = scalar;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VtlComponent VtlComponent = (VtlComponent) o;
        return isCompatibleType(type, VtlComponent.type) &&
                (name.equalsIgnoreCase(VtlComponent.name) || ignoreNameCheck && VtlComponent.ignoreNameCheck) &&
                Objects.equals(domainValueParent, VtlComponent.domainValueParent);
    }

    public boolean isCompatibleType(VtlType type, VtlType otherType) {
        if (type.equals(otherType))
            return true;
        if ((type == VtlType.STRING || otherType == VtlType.STRING) &&
                (type == VtlType.INTEGER || otherType == VtlType.INTEGER
                        || type == VtlType.NUMBER || otherType == VtlType.NUMBER ||
                        type == VtlType.BOOLEAN || otherType == VtlType.BOOLEAN))
            return true;

        if ((type == VtlType.INTEGER || otherType == VtlType.INTEGER) &&
                (type == VtlType.NUMBER || otherType == VtlType.NUMBER))
            return true;

        return false;
    }

    public UUID getRequestUuid() {
        return requestUuid;
    }

    public void setRequestUuid(UUID requestUuid) {
        this.requestUuid = requestUuid;
    }

    @JsonIgnore
    public Boolean getScalar() {
        if (scalar == null)
            this.scalar = false;
        return scalar;
    }

    @JsonIgnore
    public Boolean isScalar() {
        return getScalar();
    }

    @JsonIgnore
    public void setScalar(Boolean scalar) {
        this.scalar = scalar;
    }

    @Override
    public int hashCode() {

        return Objects.hash(type, name);
    }

    @Override
    public String toString() {
        return "VtlComponent{" +
                "type='" + type + '\'' +
                ", name='" + name + '\'' +
                ",domainValue='" + domainValue + '\'' +
                ",domainValueParent='" + domainValueParent + '\'' +
                '}';
    }
}
