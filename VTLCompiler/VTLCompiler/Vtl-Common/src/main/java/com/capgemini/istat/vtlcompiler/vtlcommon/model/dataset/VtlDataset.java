package com.capgemini.istat.vtlcompiler.vtlcommon.model.dataset;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * La classe rappresenta la struttura di una tabella.
 * Le informazioni principali contenute nella classe sono, la lista di componenti contenuti, il nome della tabella e il requestUuid
 * Questa classe è presente come tabella nel database temporaneo interno
 * Sono presenti anche flag descrittivi del dataset utili per l'elaborazione del motore semantico.
 * <p>isOnlyScalar rappresenta se il dataset rappresenta solo uno scalare</p>
 * <p>isPersistent indica se il dataset è temporaneo o no</p>
 * <p>isTransitory non ha applicazioni pratiche</p>
 *
 * @see VtlComponent
 */
@Entity
@Table(name = "dataset")
public class VtlDataset implements Serializable {

    @GeneratedValue(generator = "temporary_dataset_identifier", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(allocationSize = 1, initialValue = 1, name = "temporary_dataset_identifier", sequenceName = "temporary_dataset_identifier")
    @Id
    @Column(name = "dataset_id")
    private Long id;
    private String name;
    private String description;
    @Column(name = "is_only_a_scalar")
    private Boolean isOnlyAScalar;
    @Column(name = "is_persistent")
    private Boolean isPersistent;
    @Column(name = "is_transitory")
    private Boolean isTransitory;
    @Column(name = "request_uuid")
    private UUID requestUuid;


    @OneToMany(mappedBy = "vtlDataset", cascade = CascadeType.ALL)
    private List<VtlComponent> vtlComponents;

    public VtlDataset() {
        this.vtlComponents = new ArrayList<>();
    }

    @JsonIgnore
    public List<VtlComponent> getVtlComponents() {
        if (vtlComponents == null)
            this.vtlComponents = new ArrayList<>();
        return vtlComponents;
    }

    public void setVtlComponents(List<VtlComponent> vtlComponents) {
        this.vtlComponents = vtlComponents;
    }

    public void addComponent(VtlComponent vtlComponent) {
        Boolean previousValue = vtlComponent.getIgnoreNameCheck();
        vtlComponent.setIgnoreNameCheck(false);
        if (!this.vtlComponents.contains(vtlComponent)) {
            vtlComponent.setIgnoreNameCheck(previousValue);
            vtlComponent.setVtlDataset(this);
            this.vtlComponents.add(vtlComponent);
        }
    }

    public void addComponentsList(List<VtlComponent> VtlComponents) {
        for (VtlComponent VtlComponent : VtlComponents) {
            addComponent(VtlComponent);
        }
    }

    public void removeComponent(VtlComponent vtlComponent) {
        vtlComponents.remove(vtlComponent);
    }


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

    public Boolean isPersistent() {
        return isPersistent;
    }

    public void setPersistent(Boolean persistent) {
        isPersistent = persistent;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @JsonIgnore
    public Boolean isOnlyAScalar() {
        if (isOnlyAScalar == null)
            this.isOnlyAScalar = false;
        return isOnlyAScalar;
    }

    public void setIsOnlyAScalar(Boolean onlyAScalar) {
        if (onlyAScalar == null)
            this.isOnlyAScalar = false;
        else
            this.isOnlyAScalar = onlyAScalar;
    }

    public List<VtlComponent> getIdentifiers() {
        return vtlComponents.stream().filter(component -> component.getVtlComponentRole() == VtlComponentRole.IDENTIFIER).collect(Collectors.toList());
    }

    public List<VtlComponent> getMeasures() {
        return vtlComponents.stream().filter(component -> component.getVtlComponentRole() == VtlComponentRole.MEASURE).collect(Collectors.toList());
    }

    public List<VtlComponent> getAttributes() {
        return vtlComponents.stream().filter(component -> component.getVtlComponentRole() == VtlComponentRole.ATTRIBUTE).collect(Collectors.toList());
    }

    public List<VtlComponent> getViral() {
        return vtlComponents.stream().filter(component -> component.getVtlComponentRole() == VtlComponentRole.VIRAL).collect(Collectors.toList());
    }

    public void setIdentifiers(List<VtlComponent> vtlComponents) {
        addComponentsList(vtlComponents);
    }

    public void setMeasures(List<VtlComponent> vtlComponents) {
        addComponentsList(vtlComponents);
    }

    public void setAttributes(List<VtlComponent> vtlComponents) {
        addComponentsList(vtlComponents);
    }

    public void setViral(List<VtlComponent> vtlComponents) {
        addComponentsList(vtlComponents);
    }

    @JsonIgnore
    public Boolean getTransitory() {
        return isTransitory;
    }

    public void setTransitory(Boolean transitory) {
        isTransitory = transitory;
    }

    public UUID getRequestUuid() {
        if (requestUuid != null)
            return requestUuid;
        for (VtlComponent vtlComponent : vtlComponents) {
            if (vtlComponent.getRequestUuid() != null)
                return vtlComponent.getRequestUuid();
        }
        return null;
    }

    public void setRequestUuid(UUID requestUuid) {
        this.requestUuid = requestUuid;
        for (VtlComponent vtlComponent : vtlComponents) {
            vtlComponent.setRequestUuid(requestUuid);
        }
    }

    public List<VtlComponent> getComponentsByRole(VtlComponentRole componentRole) {
        if (componentRole == VtlComponentRole.IDENTIFIER)
            return getIdentifiers();
        if (componentRole == VtlComponentRole.MEASURE)
            return getMeasures();
        if (componentRole == VtlComponentRole.ATTRIBUTE)
            return getAttributes();
        if (componentRole == VtlComponentRole.VIRAL)
            return getViral();
        return new ArrayList<>();
    }


}
