package com.capgemini.istat.vtlcompiler.vtlcommon.service;

import com.capgemini.istat.vtlcompiler.vtlcommon.model.dataset.VtlComponent;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.dataset.VtlComponentRole;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.dataset.VtlDataset;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.dataset.VtlType;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.operator.Operator;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.constant.*;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.dataset.VtlComponentId;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * la classe offre tutti i metodi necessari alla manipolazione e al controllo dei componenti VTL.
 * La classe viene usata principalmente nel motore semantico per effettuare le trasformazioni sui dataset e sui
 * ma offre funzionalità per tutti i motori dell'applicazione
 *
 * @see VtlTypeUtilityService
 */
@Service
public class ComponentUtilityService {

    private VtlTypeUtilityService vtlTypeUtilityService;

    @Autowired
    public void setVtlTypeUtilityService(VtlTypeUtilityService vtlTypeUtilityService) {
        this.vtlTypeUtilityService = vtlTypeUtilityService;
    }

    /**
     * Data una lista di componenti estrae il componente con il nome cercato. E' possibile estrarre il componente cos'ì com'è
     * oppure si può estrarre il componente con il nome di default imposto dal tipo del componente trovato.
     * Se il componente trovato non ha il ruolo di measure a meno che non sia attivo il flag disableCastName viene restituito
     * un component con il nome di default imposto dal tipo
     * Se non viene trovato il componente il metodo restituisce null
     *
     * @param vtlComponents   una lista dei componenti
     * @param componentName   il nome ricercato
     * @param disableCastName questo flag attiva o disattiva il meccanismo di generazione di un nuovo componente con
     *                        il nome di default del tipo dek componetne ricercato
     * @return il componente ricercato. null se non viene trovato.
     */
    public VtlComponent getComponentFromName(List<VtlComponent> vtlComponents, String componentName, Boolean disableCastName) {
        // qui deve entrare un dataset e deve avere diversi comportamenti in base alla misura, identicativo,virale
        for (VtlComponent vtlComponent : vtlComponents) {
            if (vtlComponent.getName().equalsIgnoreCase(componentName))
                if (vtlComponent.getVtlComponentRole() == VtlComponentRole.MEASURE || disableCastName) {
                    return vtlComponent;
                } else {
                    return generateComponentWithBasicName(vtlComponent);
                }
        }
        return null;
    }

    /**
     * Date due liste di componenti restituisce la lista con più componenti. Se uno dei due è null restituisce una lista vuota
     * se le due liste sono identiche viene restituita la prima
     *
     * @param vtlComponents      la prima lista di componenti da controllare
     * @param otherVtlComponents la seconda lista di componenti da controllare
     * @return la lista con maggior numero di componenti
     */
    public List<VtlComponent> getGreaterComponent(List<VtlComponent> vtlComponents, List<VtlComponent> otherVtlComponents) {
        if (vtlComponents == null || otherVtlComponents == null)
            return new ArrayList<>();
        if (vtlComponents.size() >= otherVtlComponents.size()) {
            return vtlComponents;
        } else {
            return otherVtlComponents;
        }
    }

    /**
     * Date due liste di componenti restituisce la lista con meno componenti. Se uno dei due è null restituisce una lista vuota
     * se le due liste sono identiche viene restituita la seconda
     *
     * @param vtlComponents      la prima lista di componenti da controllare
     * @param otherVtlComponents la seconda lista di componenti da controllare
     * @return la lista con minor numero di componenti
     */
    public List<VtlComponent> getLesserComponent(List<VtlComponent> vtlComponents, List<VtlComponent> otherVtlComponents) {
        if (vtlComponents == null || otherVtlComponents == null)
            return new ArrayList<>();
        if (vtlComponents.size() >= otherVtlComponents.size()) {
            return otherVtlComponents;
        } else {
            return vtlComponents;
        }
    }

    /**
     * Date due liste di componenti vengono restituiti tutti i componenti in comune. La prima lista passata come parametro è quella
     * in cui vengono ricercati gli elementi della seconda lista
     *
     * @param superSetVtlComponents  la lista con più elementi
     * @param secondaryVtlComponents la seconda lista
     * @return una lista con tutti gli elementi in comune. Lista vuota se non ne vengono trovati
     */
    public List<VtlComponent> getCommonComponent(List<VtlComponent> superSetVtlComponents, List<VtlComponent> secondaryVtlComponents) {
        List<VtlComponent> result = new ArrayList<>();
        for (VtlComponent vtlComponent : superSetVtlComponents) {
            VtlComponent vtlComponentFound = getComponentFromName(secondaryVtlComponents, vtlComponent.getName(), true);
            if (vtlComponentFound != null)
                result.add(vtlComponentFound);
        }
        return result;// superSetVtlComponents.stream().filter(secondaryVtlComponents::contains).collect(toList());
    }

    /**
     * Date due liste di componenti vengono restituiti tutti i componenti in comune. La prima lista passata come parametro è quella
     * in cui vengono ricercati gli elementi della seconda lista
     * Gli elementi comuni vengono restituiti con un cast implicito forzoso verso il tipo più forte. Il nome del componente può essere modificato
     * secondo le regole del metodo  generateComponentWithCast
     *
     * @param superSetVtlComponents  la lista con più elementi
     * @param secondaryVtlComponents la seconda lista
     * @return una lista con tutti gli elementi in comune. Lista vuota se non ne vengono trovati
     */
    public List<VtlComponent> getCommonComponentWithCast(List<VtlComponent> superSetVtlComponents, List<VtlComponent> secondaryVtlComponents) {
        List<VtlComponent> result = new ArrayList<>();
        for (VtlComponent vtlComponent : secondaryVtlComponents) {
            if (superSetVtlComponents.contains(vtlComponent)) {
                result.add(generateComponentWithCast(vtlComponent, superSetVtlComponents.get(superSetVtlComponents.indexOf(vtlComponent)).getType()));
            }
        }
        return result;
    }

    /**
     * il metodo prende in ingresso una lista e un componente ed effettua un cast su tutti gli elementi della lista.
     * il tipo con cui effettuare il cast è quello del scalarVtlComponent
     *
     * @param superSetVtlComponents lista di componenti su cui effettuare il cast
     * @param scalarVtlComponent    il componente da cui viene prelevato il tipo per effettuare il cast
     * @return una lista di componenti dove tutti gli elementi hanno subito un cast verso il tipo specificato
     */
    public List<VtlComponent> getComponentsWithCast(List<VtlComponent> superSetVtlComponents, VtlComponent scalarVtlComponent) {
        List<VtlComponent> result = new ArrayList<>();
        for (VtlComponent vtlComponent : superSetVtlComponents) {
            result.add(generateComponentWithCast(vtlComponent, scalarVtlComponent.getType()));
        }
        return result;
    }

    /**
     * Dato un componente e un tipo. Viene restituito un nuovo componente con il tipo più forte(secondo le regole del cast implicito).
     * Il nuovo componente, a seconda del cast subito e dall'impostazione della disattivazione del controllo sul nome,
     * può essere rinominato nel nome di default del tipo vincitore.
     *
     * @param vtlComponent       il componente di partenza da controllare
     * @param otherTypeComponent il tipo con cui si vuole generare il nuovo componente
     * @return il nuovo componente generato.
     */
    public VtlComponent generateComponentWithCast(VtlComponent vtlComponent, VtlType otherTypeComponent) {
        VtlComponent vtlComponentTemp = copyComponent(vtlComponent);
        vtlComponentTemp.setType(vtlTypeUtilityService.getStrongestType(vtlComponent.getType(), otherTypeComponent));
        vtlComponentTemp.setDomainValue(vtlComponentTemp.getType().getDomainValue());
        if (vtlComponentTemp.getIgnoreNameCheck() && vtlTypeUtilityService.needImplicitCast(vtlComponent.getType(), otherTypeComponent)) {
            vtlComponentTemp.setName(vtlComponentTemp.getType().getDefaultName());
        }
        return vtlComponentTemp;
    }

    /**
     * Dato un componente e un tipo viene generato un nuovo componente del tipo indicato mantenendo il vecchio nome del componente
     *
     * @param vtlComponent       il componente di partenza
     * @param otherTypeComponent il tipo con cui si vuole effettuare il cast
     * @return il nuovo componente generato.
     */
    public VtlComponent getComponentWithExpectedType(VtlComponent vtlComponent, VtlType otherTypeComponent) {
        VtlComponent vtlComponentTemp = copyComponent(vtlComponent);
        vtlComponentTemp.setType(vtlTypeUtilityService.getStrongestType(vtlComponent.getType(), otherTypeComponent));
        vtlComponentTemp.setDomainValue(vtlComponentTemp.getType().getDomainValue());
        vtlComponentTemp.setDomainValueParent(vtlComponentTemp.getType().getDomainValueParent());
        return vtlComponentTemp;
    }

    /**
     * Questo metodo viene utilizzato su quasi tutti gli altri metodi e permette la copia di un oggetto component su un oggetto gemello
     * per poter permettere delle modifiche senza intaccare l'oggetto di partenza.
     *
     * @param vtlComponent il componente da copiare
     * @return un componente identico a quello inserito.
     */
    public VtlComponent copyComponent(VtlComponent vtlComponent) {
        VtlComponent vtlComponentTemp = new VtlComponent();
        BeanUtils.copyProperties(vtlComponent, vtlComponentTemp);
        vtlComponentTemp.setId(null);
        vtlComponentTemp.setVtlDataset(null);
        return vtlComponentTemp;
    }

    /**
     * il metodo prende in ingresso un componente e genera un nuovo componente con lo stesso tipo del componente inserito
     * ma con il ruolo forzato a misura e con un nome di default rispetto il tipo.
     *
     * @param vtlComponent il componente di partenza
     * @return un nuovo componente di ruolo misura e con un nome di default
     */
    public VtlComponent generateComponentWithBasicName(VtlComponent vtlComponent) {
        VtlComponent vtlComponentTemp = copyComponent(vtlComponent);
        vtlComponentTemp.setName(vtlComponentTemp.getType().getDefaultName());
        vtlComponentTemp.setIgnoreNameCheck(true);
        vtlComponentTemp.setVtlComponentRole(VtlComponentRole.MEASURE);
        return vtlComponentTemp;
    }

    /**
     * Dato un componente in ingresso ne crea uno nuovo identico al precedente ma con un nuovo ruolo e nome
     *
     * @param vtlComponent     il componente da copiare
     * @param vtlComponentRole il ruolo che il nuovo componente dovrà ricoprire
     * @param componentName    il nome del nuovo componente da generare
     * @return il nuovo componente generato
     */
    public VtlComponent generateComponentWithRoleAndName(VtlComponent vtlComponent, VtlComponentRole vtlComponentRole, String componentName) {
        VtlComponent vtlComponentTemp = copyComponent(vtlComponent);
        vtlComponentTemp.setName(componentName);
        vtlComponentTemp.setIgnoreNameCheck(false);
        if (vtlComponentRole != null)
            vtlComponentTemp.setVtlComponentRole(vtlComponentRole);
        return vtlComponentTemp;
    }

    /**
     * il metodo prende in ingresso una lista di componenti e un tipo e restituisce una lista di nuovi componenti
     * del tipo specificato.
     *
     * @param vtlComponents la lista di componenti di partenza
     * @param vtlType       il tipo in cui si vuole cambiare la lista di componenti
     * @return la nuova lista di componenti generata
     */
    List<VtlComponent> substituteComponents(List<VtlComponent> vtlComponents, VtlType vtlType) {
        List<VtlComponent> results = new ArrayList<>();
        for (VtlComponent vtlComponent : vtlComponents) {
            VtlComponent generatedMeasure = getDefaultComponent(vtlType, getRequestUuid(vtlComponents));
            if (getNumberOfComponent(results, generatedMeasure.getName()) != 0) {
                generatedMeasure.setName(generatedMeasure.getName() + "_" + getNumberOfComponent(results, generatedMeasure.getName()));
            }
            results.add(generatedMeasure);
        }

        return results;
    }

    /**
     * Il metodo prende in ingresso una costante e restituisce un componente dello stesso tipo e con il nome di default
     * in base al tipo della costante ricevuta
     *
     * @param vtlConstant la costante da cui prelevare il tipo
     * @return un componente dello stesso tipo della costante
     */
    public VtlComponent createComponentFromVtlConstant(VtlConstant vtlConstant) {
        if (vtlConstant instanceof VtlString) {
            return getDefaultComponent(VtlType.STRING, vtlConstant.getRequestUuid());
        }
        if (vtlConstant instanceof VtlInteger) {
            return getDefaultComponent(VtlType.INTEGER, vtlConstant.getRequestUuid());
        }
        if (vtlConstant instanceof VtlFloat) {
            return getDefaultComponent(VtlType.NUMBER, vtlConstant.getRequestUuid());
        }
        if (vtlConstant instanceof VtlBoolean) {
            return getDefaultComponent(VtlType.BOOLEAN, vtlConstant.getRequestUuid());
        }

        if (vtlConstant instanceof VtlDuration) {
            return getDefaultComponent(VtlType.DURATION, vtlConstant.getRequestUuid());
        }

        return null;
    }

    /**
     * Dato un tipo e una requestUuid viene generato un nuovo component. Il nome del component è quello di default
     * rispetto al tipo ricevuto
     *
     * @param vtlType     il tipo di cui si vuole creare il componente
     * @param requestUuid un requestUuid per legare il componente a una session
     * @return il nuovo componente generato
     */
    public VtlComponent getDefaultComponent(VtlType vtlType, UUID requestUuid) {
        VtlComponent vtlComponent = new VtlComponent();
        vtlComponent.setName(vtlType.getDefaultName());
        vtlComponent.setType(vtlType);
        vtlComponent.setDomainValue(vtlType.getDomainValue());
        vtlComponent.setDomainValueParent(vtlType.getDomainValueParent());
        vtlComponent.setVtlComponentRole(VtlComponentRole.MEASURE);
        vtlComponent.setIgnoreNameCheck(true);
        vtlComponent.setRequestUuid(requestUuid);
        return vtlComponent;
    }

    /**
     * il metodo prende in ingresso una lista di componenti e un nome di un componente e restituisce il numero di componenti
     * trovati con quel nome
     *
     * @param measures    la lista di misure in cui cercare il nome
     * @param defaultName il nome da ricercare
     * @return il numero di componenti trovati con il nome ricercato. 0 se non viene trovata nessuna corrispendenza
     */
    Integer getNumberOfComponent(List<VtlComponent> measures, String defaultName) {
        Integer result = 0;
        for (VtlComponent measure : measures) {
            if (measure.getName().contains(defaultName))
                result++;
        }
        return result;
    }

    /**
     * il metodo prende in ingresso un componente e un operatore e svolge sul componente l'operazione definita dall'operatore
     * L'oggetto operatore contiene al suo interno il tipo aspettato dopo l'operazione.
     * Il metodo si occupa di effettuare un cast sul componente o una sostituzione con un nuovo componente
     *
     * @param vtlComponent il componente su cui effettuare l'operazione
     * @param operator     l'operatore da applicare al componente
     * @return un nuovo componente calcolato
     */
    public VtlComponent applyOperatorToComponent(VtlComponent vtlComponent, Operator operator) {
        VtlComponent vtlComponentResult = null;
        if (operator.getSubstituteComponent()) {
            vtlComponentResult = getDefaultComponent(operator.getExpectedTypeReturn(), vtlComponent.getRequestUuid());
        } else {
            vtlComponentResult = generateComponentWithCast(vtlComponent, operator.getExpectedTypeReturn());
        }
        return vtlComponentResult;
    }

    /**
     * il componente prende in ingresso un componente e ne restituisce uno identico ma con un nuovo nome specificato
     *
     * @param vtlComponent  il componente di partenza da cui copiare
     * @param componentName il nome che si vuole assegnare al nuovo componente
     * @return il nuovo componente generato
     */
    public VtlComponent setComponentName(VtlComponent vtlComponent, String componentName) {
        VtlComponent vtlComponentResult = copyComponent(vtlComponent);
        vtlComponentResult.setName(componentName);
        return vtlComponentResult;
    }

    /**
     * Data una lista di componenti viene estratto il primo componente che corrisponde al tipo ricercato
     *
     * @param vtlComponents la lista di componenti da cui ricercare
     * @param vtlType       il tipo ricercato
     * @return il primo componente incontrato che ha lo stesso tipo di quello ricercato
     */
    public VtlComponent getFirstComponentWithType(List<VtlComponent> vtlComponents, VtlType vtlType) {
        for (VtlComponent vtlComponent : vtlComponents) {
            if (vtlComponent.getType() == vtlType) {
                //Forse copy? Vedremo....
                return vtlComponent;
            }
        }
        return null;
    }

    /**
     * Data una lista di componenti e un tipo vengono estratti dalla lista tutti i componenti che si riferiscono al tipo ricercato
     *
     * @param vtlComponents la lista di componenti in cui ricercare
     * @param vtlType       il tipo ricercato
     * @return una lista di componenti dello stesso tipo di quello ricercato
     */
    public List<VtlComponent> getComponentsWithType(List<VtlComponent> vtlComponents, VtlType vtlType) {
        List<VtlComponent> results = new ArrayList<>();
        for (VtlComponent vtlComponent : vtlComponents) {
            if (vtlComponent.getType() == vtlType) {
                results.add(vtlComponent);
            }
        }
        return results;
    }

    /**
     * Data una lista di componenti e un nome di un componente viene restituita la stessa lista meno i componenti
     * che hanno il nome ricercato
     *
     * @param vtlComponents la lista dei componenti da cui eliminare il nome ricercato
     * @param componentName il nome del componente che si vuole eliminare
     * @return una lista di componenti meno i componenti con il nome ricercato
     */
    public List<VtlComponent> removeComponentWithName(List<VtlComponent> vtlComponents, String componentName) {
        List<VtlComponent> results = new ArrayList<>();
        for (VtlComponent vtlComponent : vtlComponents) {
            if (!vtlComponent.getName().equalsIgnoreCase(componentName))
                results.add(vtlComponent);
        }
        return results;
    }

    /**
     * Dato un componente e un tipo viene effettuato un cast verso il tipo richiesto. se il cast non è possibile
     * viene restituito null
     *
     * @param vtlComponent il componente da castare
     * @param vtlType      il tipo in cui si vuole castare il componente
     * @return il nuovo componente generato o null se il cast non è possibile
     */
    public VtlComponent castComponent(VtlComponent vtlComponent, VtlType vtlType) {
        if (vtlTypeUtilityService.isCastable(vtlComponent.getType(), vtlType)) {
            VtlComponent resultComponent = getDefaultComponent(vtlType, vtlComponent.getRequestUuid());
            resultComponent.setName(vtlComponent.getName());
            resultComponent.setScalar(vtlComponent.isScalar());
            return resultComponent;
        } else {
            return null;
        }
    }

    /**
     * Dati due dataset vengono estratti tutti gli identificativi e viene restituito il superset degli identificativi
     *
     * @param vtlDataset      il primo dataset
     * @param otherVtlDataset il secondo dataset
     * @return una lista di componenti che è superset dei due dataset e una lista vuota se non viene trovato alcun superset
     */
    public List<VtlComponent> getSupersetIdentifiers(VtlDataset vtlDataset, VtlDataset otherVtlDataset) {
        List<VtlComponent> supersetIdentifiers = getGreaterComponent(vtlDataset.getIdentifiers(), otherVtlDataset.getIdentifiers());
        List<VtlComponent> identifiers = getLesserComponent(vtlDataset.getIdentifiers(), otherVtlDataset.getIdentifiers());
        if (supersetIdentifiers.containsAll(identifiers)) {
            return supersetIdentifiers;
        } else {
            return new ArrayList<>();
        }
    }

    /**
     * Dati due liste di componenti viene estratto il superset degli identificativi. Il metodo prevede che i componenti
     * dati in ingresso siano identifier
     *
     * @param vtlComponents      la prima lista di identificativi da confrontare
     * @param otherVtlComponents la seconda lista di identificati da confrontare
     * @return una lista di componenti che è superset delle due liste inserite o lista vuota se non viene identificato nessun superset
     */
    public List<VtlComponent> getSupersetIdentifiers(List<VtlComponent> vtlComponents, List<VtlComponent> otherVtlComponents) {
        if (vtlComponents == null || otherVtlComponents == null)
            return new ArrayList<>();
        List<VtlComponent> supersetIdentifiers = getGreaterComponent(vtlComponents, otherVtlComponents);
        List<VtlComponent> identifiers = getLesserComponent(vtlComponents, otherVtlComponents);
        if (supersetIdentifiers.containsAll(identifiers)) {
            return supersetIdentifiers;
        } else {
            return new ArrayList<>();
        }
    }

    /**
     * Quyesti metodo viene utilizzato per costruire la tabella di sintesi al termine delle operazioni di join
     * Dato un ingresso una lista di componenti e una mappa delle misure comuni viene generata una nuova lista dove
     * tutti i nomi che risultano essere in comune vengono rinominati secondo il meccanismo di sharping così come definito
     * nel manuale VTl
     * La mappa in ingresso è formata da una chiave che è il nome del componente e un valore che è il numero di volte in cui
     * il componente appare
     *
     * @param datasetName   il nome del dataset(serve a creare la parte iniziale delle sharping)
     * @param components    la lista di componenti del dataset in cui viene effettuato il meccanismo di sharping
     * @param commonMeasure la mappa delle misure comuni
     * @return una lista di componenti in cui tutti i componenti con lo stesso nome sono stati rinominati secondo il meccanismo di sharping
     */
    public List<VtlComponent> joinComponent(String datasetName, List<VtlComponent> components, Map<String, Integer> commonMeasure) {
        List<VtlComponent> result = new ArrayList<>();
        for (VtlComponent vtlComponent : components) {
            Integer counting = commonMeasure.get(vtlComponent.getName().toUpperCase());
            VtlComponent vtlComponentResult = copyComponent(vtlComponent);
            if (counting > 1) {
                vtlComponentResult.setName(datasetName + "#" + vtlComponent.getName());
            }
            result.add(vtlComponentResult);
        }
        return result;
    }

    /**
     * il metodo dato in ingresso una lista di componenti restituisce la lista di tutti gli identificativi
     *
     * @param vtlDataset il dataset da cui vengono estratti i componenti
     * @return la lista degli identificativi trovati
     */
    public List<VtlComponent> extractComponentExcludingIdentifiers(VtlDataset vtlDataset) {
        List<VtlComponent> vtlComponents = new ArrayList<>();
        vtlComponents.addAll(vtlDataset.getMeasures());
        vtlComponents.addAll(vtlDataset.getAttributes());
        vtlComponents.addAll(vtlDataset.getViral());
        return vtlComponents;
    }

    /**
     * il metodo dato in ingfresso una lista di dataset, crea una mappa che conta il numero di component che hanno lo stesso nome
     * la mappa è composta da una chiave che è il nome del componente e un intero che rappresenta il numero delle occorrenze
     * in cui appare un componente con quel nome.
     * Il flag only identifier delimita la ricerca dei duplicati ai soli identificativi o a tutti gli altri componenti
     *
     * @param vtlDatasets     una lista di dataset da analizzare
     * @param onlyIdentifiers il flag che decide se creare la mappa solo su gli identifier o su tutti gli altri componenti
     * @return una mappa che contiene il numero di occorrenze dei nomi del component
     */
    public Map<String, Integer> countCommonComponent(List<VtlDataset> vtlDatasets, Boolean onlyIdentifiers) {
        Map<String, Integer> measureCount = new HashMap<>();
        for (VtlDataset vtlDataset : vtlDatasets) {
            List<VtlComponent> components;
            if (onlyIdentifiers)
                components = vtlDataset.getIdentifiers();
            else
                components = extractComponentExcludingIdentifiers(vtlDataset);
            for (VtlComponent component : components) {
                Integer value = measureCount.get(component.getName().toUpperCase());
                if (value == null)
                    value = 0;
                value++;
                measureCount.put(component.getName().toUpperCase(), value);
            }
        }
        return measureCount;
    }

    /**
     * il metodo prende in ingresso una lista di componenti e restituisce false se sono presenti solo identificativi
     * true se è presente almeno un componente che non sia un identificativo
     *
     * @param vtlComponents una lista di componenti
     * @return false se sono tutti identificativi altrimenti true
     */
    public boolean isNotOnlyIdentifiers(List<VtlComponent> vtlComponents) {
        boolean result = true;
        for (VtlComponent vtlComponent : vtlComponents) {
            result = result && vtlComponent.getVtlComponentRole().equals(VtlComponentRole.IDENTIFIER);
        }
        return !result;
    }

    /**
     * Date due liste di componenti viene restituito true se tutti i componenti di vtlcomponetIds sono presenti in vtlcomponents
     *
     * @param vtlComponents   la lita di componenti in cui cercare
     * @param vtlComponentIds la lista di componenti che si vuole siano presenti in vtlComponents
     * @return true se tutti vtlcomponentsId sono presenti in vtlcomponents
     */
    public boolean foundAllComponentByComponentId(List<VtlComponent> vtlComponents, List<VtlComponentId> vtlComponentIds) {
        boolean result = true;
        if (vtlComponentIds == null || vtlComponentIds.isEmpty())
            return false;
        for (VtlComponentId vtlComponentId : vtlComponentIds) {
            result = result && getComponentFromName(vtlComponents, vtlComponentId.getComponentName(), true) != null;
        }
        return result;
    }

    /**
     * questo metodo viene utilizzato per effettuare il cross Join. Data una lista di dataset applica lo sharpening a tutti gli identificativi
     *
     * @param vtlDatasets la lista dei dataset a cui applicare il cross join
     * @return una lista di identificativi su cui è applicato lo sharpening
     */
    public List<VtlComponent> getCrossIdentifiers(List<VtlDataset> vtlDatasets) {
        Map<String, Integer> commonMeasure = countCommonComponent(vtlDatasets, true);
        List<VtlComponent> result = new ArrayList<>();
        for (VtlDataset vtlDataset : vtlDatasets) {
            result.addAll(
                    joinComponent(
                            vtlDataset.getName(),
                            vtlDataset.getIdentifiers(),
                            commonMeasure
                    )
            );
        }
        return result;
    }

    /**
     * Il metodo dato in ingresso una lista di componenti prova a rimuovere tutti i nomi dei componenti che presentano
     * uno sharp e a ripristinare il nome originario del componente. Se individua nuovamente un nome duplicato
     * restituisce lista vuota.
     * Questo metodo viene utilizzato nell'ultima fase di join
     *
     * @param vtlComponents la lista di componenti su cui rimuovere lo sharpening
     * @return una lista di componenti senza sharp o lista vuota se non è possibile per via della presenza di duplicati
     */
    public List<VtlComponent> renameDuplicateComponents(List<VtlComponent> vtlComponents) {
        List<VtlComponent> resultComponents = new ArrayList<>();
        for (VtlComponent vtlComponent : vtlComponents) {
            if (vtlComponent.getName().contains("#")) {
                String[] nameSplitted = vtlComponent.getName().split("#");
                VtlComponent vtlComponentRenamed = setComponentName(vtlComponent, nameSplitted[1]);
                if (getComponentFromName(resultComponents, nameSplitted[1], true) == null) {
                    resultComponents.add(vtlComponentRenamed);
                } else {
                    return new ArrayList<>();
                }
            } else {
                if (getComponentFromName(resultComponents, vtlComponent.getName(), true) == null) {
                    resultComponents.add(copyComponent(vtlComponent));
                } else {
                    return new ArrayList<>();
                }
            }
        }
        return resultComponents;
    }

    /**
     * il metodo dato in ingresso una lista di componenti li copia in una nuova lista di componenti identica alla precedente
     *
     * @param vtlComponents la lista di componenti da copiare
     * @return la nuova lista di componenti copiati
     */
    public List<VtlComponent> copyComponents(List<VtlComponent> vtlComponents) {
        List<VtlComponent> vtlComponentsCopied = new ArrayList<>();
        for (VtlComponent vtlComponent : vtlComponents) {
            vtlComponentsCopied.add(copyComponent(vtlComponent));
        }
        return vtlComponentsCopied;
    }

    /**
     * il metodo dato un taset e un ruolo estrae tutti i componenti con il ruolo ricercato
     *
     * @param vtlDataset       il dataset in cui ricercare
     * @param vtlComponentRole il ruolo ricercato
     * @return la lista di componenti del dataset con il ruolo ricercato
     */
    public List<VtlComponent> getComponentsByRole(VtlDataset vtlDataset, VtlComponentRole vtlComponentRole) {
        if (vtlComponentRole.equals(VtlComponentRole.IDENTIFIER)) {
            return vtlDataset.getIdentifiers();
        }
        if (vtlComponentRole.equals(VtlComponentRole.MEASURE)) {
            return vtlDataset.getMeasures();
        }
        if (vtlComponentRole.equals(VtlComponentRole.ATTRIBUTE)) {
            return vtlDataset.getAttributes();
        }
        if (vtlComponentRole.equals(VtlComponentRole.VIRAL)) {
            return vtlDataset.getViral();
        }
        return new ArrayList<>();
    }

    /**
     * il metodo prende in ingresso una lista di dataset e restituisce una lista di componenti di identificativi in comune
     *
     * @param vtlDatasets una lista di dataset
     * @return una lista di identifier comuni a tutti i dataset
     */
    public List<VtlComponent> getCommonIdentifiers(List<VtlDataset> vtlDatasets) {
        List<VtlComponent> commonIdentifiers = vtlDatasets.get(0).getIdentifiers();
        for (VtlDataset vtlDataset : vtlDatasets) {
            commonIdentifiers = getCommonComponent(commonIdentifiers, vtlDataset.getIdentifiers());
        }
        return commonIdentifiers;
    }

    /**
     * il metodo dato in ingresso una lista di dataset restituisce il superset identifier di tutti i dataset
     *
     * @param vtlDatasets i dataset da analizzare
     * @return una lista di componenti superset di tutti i dataset
     */
    public List<VtlComponent> getSupersetIdentifiers(List<VtlDataset> vtlDatasets) {
        List<VtlComponent> supersetIdentifiers = vtlDatasets.get(0).getIdentifiers();
        for (VtlDataset vtlDataset : vtlDatasets) {
            supersetIdentifiers = getSupersetIdentifiers(supersetIdentifiers, vtlDataset.getIdentifiers());
        }
        return supersetIdentifiers;
    }

    /**
     * il metodo dato in ingresso una lista di componenti estrae il requestuuid
     *
     * @param vtlComponents la lista di componenti da cui estrarre l'uuid
     * @return l'uuid dei componenti. Viene estratto il primo trovato. Null se non ne viene trovato nessuno
     */
    private UUID getRequestUuid(List<VtlComponent> vtlComponents) {
        for (VtlComponent vtlComponent : vtlComponents) {
            if (vtlComponent.getRequestUuid() != null)
                return vtlComponent.getRequestUuid();
        }
        return null;
    }

}
