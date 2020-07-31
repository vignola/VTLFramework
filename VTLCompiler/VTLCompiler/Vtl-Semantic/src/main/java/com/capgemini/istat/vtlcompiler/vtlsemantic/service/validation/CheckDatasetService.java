package com.capgemini.istat.vtlcompiler.vtlsemantic.service.validation;

import com.capgemini.istat.vtlcompiler.vtlcommon.model.dataset.VtlComponent;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.dataset.VtlComponentRole;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.dataset.VtlDataset;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.dataset.VtlType;
import com.capgemini.istat.vtlcompiler.vtlcommon.service.ComponentUtilityService;
import com.capgemini.istat.vtlcompiler.vtlcommon.service.DatasetUtilityService;
import com.capgemini.istat.vtlcompiler.vtlcommon.service.IDatasetIntegration;
import com.capgemini.istat.vtlcompiler.vtlcommon.service.VtlTypeUtilityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

/**
 * il servizio offre una serie di metodi necessari per effettuare i controlli di validità semantica.
 * Tutti i metodi restituiscono un valore booleano che viene utilizzato per verificare se è necessario far scattare
 * un messaggio di errore semantico
 *
 * @see DatasetUtilityService
 * @see ComponentUtilityService
 * @see VtlTypeUtilityService
 * @see com.capgemini.istat.vtlcompiler.vtlcommon.service.DatasetIntegrationService
 */
@Service
public class CheckDatasetService {
    private DatasetUtilityService datasetUtilityService;
    private ComponentUtilityService componentUtilityService;
    private VtlTypeUtilityService vtlTypeUtilityService;

    private IDatasetIntegration datasetIntegrationService;

    @Autowired
    public void setDatasetIntegration(IDatasetIntegration datasetIntegrationService) {
        this.datasetIntegrationService = datasetIntegrationService;
    }

    @Autowired
    public void setDatasetUtilityService(DatasetUtilityService datasetUtilityService) {
        this.datasetUtilityService = datasetUtilityService;
    }

    @Autowired
    public void setComponentUtilityService(ComponentUtilityService componentUtilityService) {
        this.componentUtilityService = componentUtilityService;
    }

    @Autowired
    public void setVtlTypeUtilityService(VtlTypeUtilityService vtlTypeUtilityService) {
        this.vtlTypeUtilityService = vtlTypeUtilityService;
    }

    /**
     * il metodo verifica che un dataset sia superset dell'altro
     *
     * @param vtlDataset
     * @param otherVtlDataset
     * @return
     */
    public boolean isSuperset(VtlDataset vtlDataset, VtlDataset otherVtlDataset) {
        return datasetUtilityService.isSuperset(vtlDataset, otherVtlDataset);
    }

    /**
     * il metodo verifica che i dataset abbiano tutte le misure in comune
     *
     * @param vtlDataset
     * @param otherVtlDataset
     * @return
     */
    public Boolean hasCommonMeasure(VtlDataset vtlDataset, VtlDataset otherVtlDataset) {
        List<VtlComponent> supersetMeasures = null;
        List<VtlComponent> measures = null;
        boolean result = false;

        if (vtlDataset.getMeasures().size() == 0)
            return false;

        supersetMeasures = componentUtilityService.getGreaterComponent(vtlDataset.getMeasures(), otherVtlDataset.getMeasures());
        measures = componentUtilityService.getLesserComponent(vtlDataset.getMeasures(), otherVtlDataset.getMeasures());

        for (VtlComponent VtlComponent : measures) {
            result = result || supersetMeasures.contains(VtlComponent);
        }

        return result;
    }

    /**
     * il metodo verifca che i due dataset abbiano almeno una misura dello stesso tipo
     *
     * @param vtlDataset
     * @param otherVtlDataset
     * @return
     */
    public Boolean has_one_measure_of_same_type(VtlDataset vtlDataset, VtlDataset otherVtlDataset) {
        Boolean result = false;
        for (VtlComponent vtlComponentLeft : vtlDataset.getMeasures()) {
            for (VtlComponent vtlComponentRight : otherVtlDataset.getMeasures()) {
                if (vtlTypeUtilityService.isEqualsWithCast(vtlComponentLeft.getType(), vtlComponentRight.getType()))
                    result = true;
            }
        }
        return result;
    }


    /**
     * il metodo verifica che i dataset abbiano la stessa struttura
     *
     * @param vtlDataset
     * @param otherVtlDataset
     * @return
     */
    public Boolean hasSameStructure(VtlDataset vtlDataset, VtlDataset otherVtlDataset) {

        Boolean result =
                checkStructure(vtlDataset.getIdentifiers(), otherVtlDataset.getIdentifiers())
                        && checkStructure(vtlDataset.getMeasures(), otherVtlDataset.getMeasures())
                        && checkStructure(vtlDataset.getViral(), otherVtlDataset.getViral())
                        && checkStructure(vtlDataset.getAttributes(), otherVtlDataset.getAttributes());
        return result;

    }

    /**
     * il metodo dato in ingresso due liste di componenti verifica che le due liste posseggano gli stessi componenti
     *
     * @param comp
     * @param compOther
     * @return
     */
    public Boolean checkStructure(List<VtlComponent> comp, List<VtlComponent> compOther) {
        if (comp.size() != compOther.size()) {
            return false;
        } else {
            for (VtlComponent vtlComponent : comp) {
                VtlComponent component = componentUtilityService.getComponentFromName(compOther, vtlComponent.getName(), true);
                if (component == null) {
                    return false;
                }
            }
            return true;
        }
    }

    /**
     * il metodo verifica che il dataset possieda almeno un componente di tipo time period
     *
     * @param vtlDataset
     * @return
     */
    public Boolean hasOneIdentifierTimePeriod(VtlDataset vtlDataset) {
        List<VtlComponent> identifiers = vtlDataset.getIdentifiers();
        VtlComponent vtlComponent = componentUtilityService.getFirstComponentWithType(identifiers, VtlType.TIME_PERIOD);
        return vtlComponent != null;
    }

    /**
     * verifica che il dataset possieda almeno un identificativo di tipo time period
     *
     * @param vtlDataset
     * @return
     */
    public Boolean hasOnlyOneIdentifierTimePeriod(VtlDataset vtlDataset) {
        List<VtlComponent> identifiers = vtlDataset.getIdentifiers();
        List<VtlComponent> vtlComponents = componentUtilityService.getComponentsWithType(identifiers, VtlType.TIME_PERIOD);
        if (vtlComponents.isEmpty()) {
            vtlComponents = componentUtilityService.getComponentsWithType(identifiers, VtlType.DATE);
        }
        if (vtlComponents.isEmpty()) {
            vtlComponents = componentUtilityService.getComponentsWithType(identifiers, VtlType.TIME_PERIOD);
        }
        return vtlComponents.size() == 1;
    }

    /**
     * verifica che il dataset possieda almeno un identificativo di tipo time
     *
     * @param vtlDataset
     * @return
     */
    public Boolean hasOneIdentifierTime(VtlDataset vtlDataset) {
        List<VtlComponent> identifiers = vtlDataset.getIdentifiers();
        VtlComponent vtlComponent = componentUtilityService.getFirstComponentWithType(identifiers, VtlType.TIME);
        if (vtlComponent == null) {
            vtlComponent = componentUtilityService.getFirstComponentWithType(identifiers, VtlType.DATE);
        }
        if (vtlComponent == null) {
            vtlComponent = componentUtilityService.getFirstComponentWithType(identifiers, VtlType.TIME_PERIOD);
        }
        return vtlComponent != null;
    }

    /**
     * dato un dataset e il nome di un componente viene verificato che il componente sia un identifier
     *
     * @param vtlDataset
     * @param name
     * @return
     */
    public Boolean isIdentifier(VtlDataset vtlDataset, String name) {
        VtlComponent vtlComponent = componentUtilityService.getComponentFromName(
                vtlDataset.getIdentifiers(),
                name,
                true
        );
        return vtlComponent != null;
    }

    /**
     * verifica che il dataset possieda un solo identificativo di tipo time
     *
     * @param vtlDataset
     * @return
     */
    public Boolean hasOnlyOneIdentifierTime(VtlDataset vtlDataset) {
        List<VtlComponent> identifiers = vtlDataset.getIdentifiers();
        List<VtlComponent> vtlComponents = componentUtilityService.getComponentsWithType(identifiers, VtlType.TIME);
        if (vtlComponents.isEmpty()) {
            vtlComponents = componentUtilityService.getComponentsWithType(identifiers, VtlType.DATE);
        }
        if (vtlComponents.isEmpty()) {
            vtlComponents = componentUtilityService.getComponentsWithType(identifiers, VtlType.TIME_PERIOD);
        }
        return vtlComponents.size() == 1;
    }

    /**
     * dato un dataset e un nome di un componente, viene verificato che il componente esista all'interno del dataset.
     * il controllo può essere fatto in case sensitive
     *
     * @param vtlDataset
     * @param componentName
     * @param ignoreCase
     * @return
     */
    public Boolean existComponentWithName(VtlDataset vtlDataset, String componentName, boolean ignoreCase) {
        if (ignoreCase)
            return existComponentIgnoreCase(vtlDataset.getVtlComponents(), componentName);
        else
            return existComponent(vtlDataset.getVtlComponents(), componentName);

    }

    /**
     * Dato un dataset e un nome di dataset viene verificato che il nome coincida con quello immesso
     *
     * @param vtlDataset
     * @param datasetName
     * @return
     */
    public Boolean hasDatasetThisName(VtlDataset vtlDataset, String datasetName) {
        return vtlDataset.getName().equalsIgnoreCase(datasetName);
    }

    /**
     * data una lista di componenti e il nome di un componente viene verificata l'esistenza del componente all'interno della lista
     * il controllo viene fatto in case insensitive
     *
     * @param VtlComponents
     * @param componentName
     * @return
     */
    public Boolean existComponentIgnoreCase(List<VtlComponent> VtlComponents, String componentName) {
        for (VtlComponent vtlComponent : VtlComponents) {
            if (vtlComponent.getName().equalsIgnoreCase(componentName))
                return true;
        }
        return false;
    }

    /**
     * data una lista di componenti e il nome di un componente viene verificata l'esistenza del componente all'interno della lista
     * il controllo viene fatto in case sensitive
     *
     * @param VtlComponents
     * @param componentName
     * @return
     */
    public Boolean existComponent(List<VtlComponent> VtlComponents, String componentName) {
        for (VtlComponent vtlComponent : VtlComponents) {
            if (vtlComponent.getName().equals(componentName))
                return true;
        }
        return false;
    }

    /**
     * viene verificata l'esistenza del dataset sul database
     *
     * @param name
     * @param ignoreCase
     * @param requestUuid
     * @return
     */
    public Boolean existDatasetWithName(String name, boolean ignoreCase, UUID requestUuid) {
        if (ignoreCase)
            return datasetIntegrationService.countAllByNameIgnoreCase(name, requestUuid) != 0;
        else
            return datasetIntegrationService.countAllByName(name, requestUuid) != 0;

    }

    /**
     * viene verificato che il dataset contenga una sola misura
     *
     * @param vtlDataset
     * @return
     */
    public Boolean hasOnlyOneMisure(VtlDataset vtlDataset) {
        return vtlDataset.getMeasures() != null && vtlDataset.getMeasures().size() == 1;
    }

    /**
     * viene verificato che il dataset ha un solo identificativo
     *
     * @param vtlDataset
     * @return
     */
    public Boolean hasOnlyOneIdentifier(VtlDataset vtlDataset) {
        return vtlDataset.getIdentifiers() != null && vtlDataset.getIdentifiers().size() == 1;
    }

    /**
     * viene verificato che il dataset possieda un solo attributo
     *
     * @param vtlDataset
     * @return
     */
    public Boolean hasOnlyOneAttribute(VtlDataset vtlDataset) {
        return vtlDataset.getAttributes() != null && vtlDataset.getAttributes().size() == 1;
    }

    /**
     * viene verificato che il metodo possieda un solo attributo virale
     *
     * @param vtlDataset
     * @return
     */
    public Boolean hasOnlyOneViral(VtlDataset vtlDataset) {
        return vtlDataset.getViral() != null && vtlDataset.getViral().size() == 1;
    }

    /**
     * viene verificato che il dataset possieda almeno un identificativo
     *
     * @param vtlDataset
     * @return
     */
    public Boolean hasAtLeastOneIdentifier(VtlDataset vtlDataset) {
        List<VtlComponent> identifiers = vtlDataset.getIdentifiers();
        return identifiers != null && identifiers.size() > 0;
    }

    /**
     * viene verificato che il dataset possieda almeno una misura
     *
     * @param vtlDataset
     * @return
     */
    public Boolean hasAtLeastOneMeasure(VtlDataset vtlDataset) {
        return vtlDataset.getMeasures() != null && vtlDataset.getMeasures().size() >= 1;
    }

    /**
     * viene verificato che il dataset possieda almeno un attributo
     *
     * @param vtlDataset
     * @return
     */
    public Boolean hasAtLeastOneAttribute(VtlDataset vtlDataset) {
        return vtlDataset.getAttributes() != null && vtlDataset.getAttributes().size() >= 1;
    }

    /**
     * viene verificato che il dataset possieda almeno un attributo virale
     *
     * @param vtlDataset
     * @return
     */
    public Boolean hasAtLeastOneViral(VtlDataset vtlDataset) {
        return vtlDataset.getViral() != null && vtlDataset.getViral().size() >= 1;
    }

    /**
     * Dato un dataset un ruolo e un tipo viene controllato  che i componenti del dataset ricoprenti
     * il ruolo ricercato siano tutti del tipo ricercato(l'uguaglianza viene calcolata tramite dinamica di cast)
     *
     * @param vtlDataset
     * @param vtlComponentRole
     * @param vtlType
     * @return
     */
    public Boolean hasTypeAndRole(VtlDataset vtlDataset, VtlComponentRole vtlComponentRole, VtlType vtlType) {
        if (vtlComponentRole.equals(VtlComponentRole.IDENTIFIER))
            return checkType(vtlDataset.getIdentifiers(), vtlType);
        if (vtlComponentRole.equals(VtlComponentRole.MEASURE))
            return checkType(vtlDataset.getMeasures(), vtlType);
        if (vtlComponentRole.equals(VtlComponentRole.ATTRIBUTE))
            return checkType(vtlDataset.getAttributes(), vtlType);
        if (vtlComponentRole.equals(VtlComponentRole.VIRAL))
            return checkType(vtlDataset.getViral(), vtlType);
        return false;
    }

    /**
     * Dato un dataset un ruolo e un valueDomain viene controllato che i componenti del dataset ricoprenti il ruolo
     * ricercato siano tutti dello stesso valueDomain
     *
     * @param vtlDataset
     * @param vtlComponentRole
     * @param valueDomain
     * @return
     */
    public Boolean hasValueDomainAndRole(VtlDataset vtlDataset, VtlComponentRole vtlComponentRole, String valueDomain) {
        if (vtlComponentRole.equals(VtlComponentRole.IDENTIFIER))
            return checkValueDomain(vtlDataset.getIdentifiers(), valueDomain);
        if (vtlComponentRole.equals(VtlComponentRole.MEASURE))
            return checkValueDomain(vtlDataset.getMeasures(), valueDomain);
        if (vtlComponentRole.equals(VtlComponentRole.ATTRIBUTE))
            return checkValueDomain(vtlDataset.getAttributes(), valueDomain);
        if (vtlComponentRole.equals(VtlComponentRole.VIRAL))
            return checkValueDomain(vtlDataset.getViral(), valueDomain);
        return false;
    }

    /**
     * Data una lista di componenti e un tipo viene verificato che tutti i componenti abbiano un tipo compatibile
     * con quello ricercato
     *
     * @param vtlComponents
     * @param vtlType
     * @return
     */
    public boolean checkType(List<VtlComponent> vtlComponents, VtlType vtlType) {
        boolean result = true;
        if (vtlType.equals(VtlType.SCALAR))
            return result;
        for (VtlComponent vtlComponent : vtlComponents) {
            result = result && vtlTypeUtilityService.isTypeCompatible(vtlComponent.getType(), vtlType);
        }
        return result;
    }

    /**
     * Data una lista di componenti e un valueDomain viene controlato che tutti i componenti abbiano il valueDomain
     * ricercato
     *
     * @param vtlComponents
     * @param valueDomain
     * @return
     */
    public boolean checkValueDomain(List<VtlComponent> vtlComponents, String valueDomain) {
        boolean result = true;
        for (VtlComponent vtlComponent : vtlComponents) {
            result = result && vtlComponent.getDomainValue().equalsIgnoreCase(valueDomain);
        }
        return result;
    }

    /**
     * Viene verificato che il dataset abbia tutte le misure di tipo number
     *
     * @param vtlDataset
     * @return
     */
    public Boolean hasMeasureOnlyNumber(VtlDataset vtlDataset) {
        boolean result = true;
        List<VtlComponent> measures = vtlDataset.getMeasures();
        for (VtlComponent vtlComponent : measures) {
            result = result && vtlComponent.getType().equals(VtlType.NUMBER);
        }
        return result;
    }

    /**
     * viene verificato che il dataset abbia tutte le misure di tipo number o integer
     *
     * @param vtlDataset
     * @return
     */
    public Boolean hasMeasureNumberOrInteger(VtlDataset vtlDataset) {
        boolean result = true;
        List<VtlComponent> measures = vtlDataset.getMeasures();
        for (VtlComponent vtlComponent : measures) {
            result = result && (vtlComponent.getType().equals(VtlType.NUMBER) || vtlComponent.getType().equals(VtlType.INTEGER));
        }
        return result;
    }

    /**
     * viene verificato che il dataset abbia tutte le misure di tipo integer
     *
     * @param vtlDataset
     * @return
     */
    public Boolean hasOnlyMeasureInteger(VtlDataset vtlDataset) {
        boolean result = true;
        List<VtlComponent> measures = vtlDataset.getMeasures();
        for (VtlComponent vtlComponent : measures) {
            result = result && vtlComponent.getType().equals(VtlType.INTEGER);
        }
        return result;
    }

    /**
     * viene verificato che il dataset abbia solo misure booleane
     *
     * @param vtlDataset
     * @return
     */
    public Boolean hasOnlyMeasureBoolean(VtlDataset vtlDataset) {
        boolean result = true;
        List<VtlComponent> measures = vtlDataset.getMeasures();
        for (VtlComponent vtlComponent : measures) {
            result = result && vtlComponent.getType().equals(VtlType.BOOLEAN);
        }
        return result;
    }

    /**
     * viene verificato che il dataset abbia tutte le misure che siano implicitamente castabili su stringa
     *
     * @param vtlDataset
     * @return
     */
    public Boolean hasMeasureCastableToString(VtlDataset vtlDataset) {
        boolean result = true;
        List<VtlComponent> measures = vtlDataset.getMeasures();
        for (VtlComponent vtlComponent : measures) {
            if (vtlComponent.getType().equals(VtlType.DATE)
                    || vtlComponent.getType().equals(VtlType.TIME)
                    || vtlComponent.getType().equals(VtlType.DURATION)
                    || vtlComponent.getType().equals(VtlType.TIME_PERIOD))
                result = false;
        }
        return result;
    }

}
