package com.capgemini.istat.vtlcompiler.vtlcommon.model.valuedomain;

import javax.persistence.*;
import java.io.Serializable;

/**
 * la classe rappresenta un valueDomain e a tutti i suoi valori.
 * Le proprietà dalle classe contiene il nome, il codice del valore e la sua descrizione.
 * La classe rappresenta anche una tabella esistente sul database interno dell' applicazione
 */
@Entity
@Table(name = "valueDomain")
public class ValueDomain implements Serializable {

    @GeneratedValue(generator = "temporary_valuedomain_identifier", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(allocationSize = 1, initialValue = 1, name = "temporary_valuedomain_identifier", sequenceName = "temporary_valuedomain_identifier")
    @Id
    @Column(name = "valueDomainId")
    private Long id;
    private String valueDomainName;
    private String code;
    private String description;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getValueDomainName() {
        return valueDomainName;
    }

    public void setValueDomainName(String valueDomainName) {
        this.valueDomainName = valueDomainName;
    }


    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
