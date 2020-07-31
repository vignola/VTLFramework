package com.capgemini.istat.vtlcompiler.vtlsemantic.service.validation;

import com.capgemini.istat.vtlcompiler.vtlcommon.model.dataset.VtlComponent;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.dataset.VtlComponentRole;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.dataset.VtlType;
import com.capgemini.istat.vtlcompiler.vtlcommon.service.VtlTypeUtilityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * il servizio offre una serie di metodi necessari per effettuare i controlli di validità semantica.
 * Tutti i metodi restituiscono un valore booleano che viene utilizzato per verificare se è necessario far scattare
 * un messaggio di errore semantico
 *
 * @see VtlTypeUtilityService
 */
@Service
public class CheckComponentService {
    private VtlTypeUtilityService vtlTypeUtilityService;

    @Autowired
    public void setVtlTypeUtilityService(VtlTypeUtilityService vtlTypeUtilityService) {
        this.vtlTypeUtilityService = vtlTypeUtilityService;
    }

    /**
     * dato un componente viene verificato che il compoente sia  di tipo intero o numerico
     *
     * @param vtlComponent
     * @return
     */
    public Boolean isNumberOrIntegerType(VtlComponent vtlComponent) {
        return vtlComponent.getType().equals(VtlType.INTEGER) || vtlComponent.getType().equals(VtlType.NUMBER);
    }

    /**
     * dato un componente viene verificato che sia di tipo intero
     *
     * @param vtlComponent
     * @return
     */
    public Boolean isIntegerType(VtlComponent vtlComponent) {
        return vtlComponent.getType().equals(VtlType.INTEGER);
    }

    /**
     * dato un componente viene verificato che sia di tipo number
     *
     * @param vtlComponent
     * @return
     */
    public Boolean isNumberType(VtlComponent vtlComponent) {
        return vtlComponent.getType().equals(VtlType.NUMBER);
    }

    /**
     * dato un componente viene verificato che sia di tipo booleano
     *
     * @param vtlComponent
     * @return
     */
    public Boolean isBooleanType(VtlComponent vtlComponent) {
        return vtlComponent.getType().equals(VtlType.BOOLEAN);
    }

    /**
     * dato un componente viene verificato che sia di tipo intero
     *
     * @param vtlComponent
     * @return
     */
    public Boolean isImplictablyCastableToString(VtlComponent vtlComponent) {
        return vtlComponent.getType().equals(VtlType.INTEGER)
                || vtlComponent.getType().equals(VtlType.NUMBER)
                || vtlComponent.getType().equals(VtlType.STRING)
                || vtlComponent.getType().equals(VtlType.BOOLEAN);
    }

    /**
     * dati due componenti viene verificato che siano di tipo equivalente secondo le regole di cast
     *
     * @param leftComponent
     * @param rightComponent
     * @return
     */
    public Boolean isSameTypeComponents(VtlComponent leftComponent, VtlComponent rightComponent) {
        return vtlTypeUtilityService.isEqualsWithCast(leftComponent.getType(), rightComponent.getType());
    }

    /**
     * dati due componenti viene verificato che abbiano esattamente lo stesso tipo
     *
     * @param leftComponent
     * @param rightComponent
     * @return
     */
    public Boolean isIdenticalType(VtlComponent leftComponent, VtlComponent rightComponent) {
        return leftComponent.getType() == rightComponent.getType();
    }

    /**
     * dato un componente viene verificato che sia di tipo time, time_period o date
     *
     * @param vtlComponent
     * @return
     */
    public Boolean isTime(VtlComponent vtlComponent) {
        return vtlComponent.getType().equals(VtlType.TIME) || vtlComponent.getType().equals(VtlType.TIME_PERIOD) || vtlComponent.getType().equals(VtlType.DATE);
    }

    /**
     * dato un componente viene verificato che sia di tipo time_period
     *
     * @param vtlComponent
     * @return
     */
    public Boolean isTimePeriod(VtlComponent vtlComponent) {
        return vtlComponent.getType().equals(VtlType.TIME_PERIOD);
    }

    /**
     * dato un componente un ruolo e un tipo viene verificato che il componente corrisponda al tipo e il ruolo immessi
     *
     * @param vtlComponent
     * @param vtlComponentRole
     * @param vtlType
     * @return
     */
    public boolean hasTypeAndRole(VtlComponent vtlComponent, VtlComponentRole vtlComponentRole, VtlType vtlType) {
        return vtlComponent.getVtlComponentRole().equals(vtlComponentRole) && vtlTypeUtilityService.isEqualsWithCast(vtlComponent.getType(), vtlType);
    }

    /**
     * dato un componente un ruolo e un valueDomain viene verificato che il componente corrisponda al ruolo e value domain immessi
     *
     * @param vtlComponent
     * @param vtlComponentRole
     * @param vtlValueDomain
     * @return
     */
    public boolean hasValueDomainAndRole(VtlComponent vtlComponent, VtlComponentRole vtlComponentRole, String vtlValueDomain) {
        return vtlComponent.getVtlComponentRole().equals(vtlComponentRole) && vtlComponent.getDomainValue().equalsIgnoreCase(vtlValueDomain);
    }


}
