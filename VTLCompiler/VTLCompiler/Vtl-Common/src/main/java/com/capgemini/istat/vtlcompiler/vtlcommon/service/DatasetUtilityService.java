package com.capgemini.istat.vtlcompiler.vtlcommon.service;

import com.capgemini.istat.vtlcompiler.vtlcommon.model.dataset.VtlComponent;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.dataset.VtlComponentRole;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.dataset.VtlDataset;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.dataset.VtlType;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.exception.ComponentNotFoundException;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.exception.UsingNotFoundException;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.exception.ValidationException;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.operator.Operator;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.constant.VtlConstant;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.constant.VtlConstantExpression;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.dataset.VtlComponentId;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.validation.OutputMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * la classe offre tutti i servizi necessari per la creazione e manipolazione dei dataset.
 * Questa classe viene utilizzata da tutti i motori dell'applicazione ed è l'unica che contiene i mezzi necessari per elaborare
 * le trasformazioni dei dataset
 *
 * @see DatasetIntegrationService
 * @see ComponentUtilityService
 * @see VtlTypeUtilityService
 */
@Service
public class DatasetUtilityService {
    private IDatasetIntegration datasetIntegrationService;
    private ComponentUtilityService componentUtilityService;
    private VtlTypeUtilityService vtlTypeUtilityService;
    private static final String FIRST = "First";
    private static final String SECOND = "Second";

    @Autowired
    public void setDatasetIntegration(IDatasetIntegration datasetIntegrationService) {
        this.datasetIntegrationService = datasetIntegrationService;
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
     * la classe genera il nome di una nuova tabella temporanea
     *
     * @return il nome della tabella temporanea
     */
    public String getName() {
        return "temporary_" + datasetIntegrationService.getTemporaryId();
    }

    /**
     * il metodo prende in ingresso un dataset e una lista di componenti e genera un nuovo dataset.
     * Il nuovo dataset generato possiede come componenti i componenti passati al metodo e mantiene tutte le impostazioni
     * del dataset originario.
     * i componenti virali vengono copiati dal dataset di partenza in base  flag addViral
     *
     * @param vtlDataset    il dataset di partenza da cui vengono copiate le impostazioni del dataset
     * @param vtlComponents la lista di componenti che verranno aggiunte al nuovo dataset
     * @param addViral      questo flag disciplina l'aggiunta degli attributi virali(se disponibili)
     * @return il nuovo dataset temporaneo generato
     */
    public VtlDataset createDatasetFromMeasure(VtlDataset vtlDataset, List<VtlComponent> vtlComponents, Boolean addViral) {
        VtlDataset result = new VtlDataset();
        result.setName(getName());
        result.setDescription(vtlDataset.getName());
        result.addComponentsList(componentUtilityService.copyComponents(vtlDataset.getIdentifiers())); //aggiungo tutti gli identifiers precedenti
        result.addComponentsList(componentUtilityService.copyComponents(vtlComponents)); //Aggiungo tutte le misure nuove
        if (addViral)
            result.addComponentsList(componentUtilityService.copyComponents(vtlDataset.getViral())); //Aggiungo tutti gli attributi viral
        result.setPersistent(false);
        result.setTransitory(true);
        result.setIsOnlyAScalar(vtlDataset.isOnlyAScalar());
        result.setRequestUuid(result.getRequestUuid());
        datasetIntegrationService.save(result);
        return result;
    }

    /**
     * il metodo prende in ingresso un dataset e un nome di un componente appartenente al dataset e genera un nuovo dataset
     * temporaneo avente come misure il componente indicato più tutti gli attributi virali.
     * Se il componente indicato è un identifier o un attributo virale valgono le regole di promozione dei component del linguaggio
     * VTL
     *
     * @param vtlDataset    il dataset di partenza da copiare
     * @param componentName il componente appartenente al dataset di partenza che diventerà unica misura del nuovo dataset
     * @return il nuovo dataset generato.
     */
    public VtlDataset createDatasetFromComponentName(VtlDataset vtlDataset, String componentName) {
        VtlDataset result = new VtlDataset();
        result.setName(getName());
        result.setDescription(vtlDataset.getName());
        result.addComponentsList(componentUtilityService.copyComponents(vtlDataset.getIdentifiers())); //aggiungo tutti gli identifiers precedenti
        VtlComponent vtlComponent = componentUtilityService.getComponentFromName(vtlDataset.getVtlComponents(), componentName, false);
        result.addComponent(componentUtilityService.copyComponent(vtlComponent));
        result.addComponentsList(componentUtilityService.copyComponents(vtlDataset.getViral()));
        result.setPersistent(false);
        result.setTransitory(true);
        result.setRequestUuid(result.getRequestUuid());
        datasetIntegrationService.save(result);
        return result;
    }

    /**
     * il metodo prende in ingresso una lista di componenti e genera un nuovo dataset. Il flag isOnlyScalar sta a indicare
     * se il nuovo dataset rappresenta uno scalare oppure è un dataset normale
     *
     * @param vtlComponents la lista di componenti che comporranno il nuovo dataset
     * @param isOnlyScalar  il flag che certifica se si tratta solo di uno scalare
     * @return il nuovo dataset generato
     */
    public VtlDataset createTemporaryDataset(List<VtlComponent> vtlComponents, Boolean isOnlyScalar) {
        VtlDataset result = new VtlDataset();
        result.setName(getName());
        result.addComponentsList(componentUtilityService.copyComponents(vtlComponents));
        result.setIsOnlyAScalar(isOnlyScalar);
        result.setPersistent(false);
        result.setTransitory(true);
        result.setRequestUuid(result.getRequestUuid());
        datasetIntegrationService.save(result);
        return result;
    }

    /**
     * il metodo dato un dataset ne genera uno nuovo temporaneo. Il dataset avrà un nuovo nome come indicato nei parametri
     * e a seconda di come sono valorizzati i flag aggiungerà gli identifier, le misure e gli attributi virali. I flag
     * sono fra loro indipendenti.
     *
     * @param vtlDataset      il dataset di partenza da copiare
     * @param newName         il nuovo nome che si vuole dare al dataset
     * @param copyIdentifiers il flag indica se si vogliono compiare gli identificativi
     * @param copyMeasures    il flag indica se si vogliono copiare le misure
     * @param copyViral       il flag indica se si vogliono copiare gli attributi virali
     * @return il nuovo dataset generato
     */
    public VtlDataset cloneDatasetWithNewName(VtlDataset vtlDataset, String newName, Boolean copyIdentifiers, Boolean copyMeasures, Boolean copyViral) {
        VtlDataset result = new VtlDataset();
        result.setName(newName);
        result.setDescription(vtlDataset.getName());
        if (copyIdentifiers) {
            result.addComponentsList(componentUtilityService.copyComponents(vtlDataset.getIdentifiers()));
        }
        if (copyMeasures) {
            result.addComponentsList(componentUtilityService.copyComponents(vtlDataset.getMeasures()));
        }
        if (copyViral) {
            result.addComponentsList(componentUtilityService.copyComponents(vtlDataset.getViral()));
        }
        result.setPersistent(false);
        result.setTransitory(true);
        return result;
    }

    /**
     * il metodo dato in ingresso una VtlConstant genera un dataset che lo rappresenta
     *
     * @param vtlConstant la costante che verrà promossa a dataset
     * @return il nuovo dataset temporaneo generato
     */
    public VtlDataset createScalarDataset(VtlConstant<?> vtlConstant) {
        List<VtlComponent> vtlComponents = new ArrayList<>();
        VtlComponent vtlComponent = componentUtilityService.createComponentFromVtlConstant(vtlConstant);
        vtlComponent.setRequestUuid(vtlConstant.getRequestUuid());
        vtlComponents.add(vtlComponent);
        return createTemporaryDataset(vtlComponents, true);
    }

    /**
     * Il metodo prende in ingresso un dataset e una lista di componenti e genera un nuovo dataset
     * Tutti i componenti presenti nella lista ma non presenti nel dataset risultante vengono aggiunti
     * Tutti i componenti presenti sia sul dataset che sulla lista dei componenti vengono sostituiti con quelli della lista di componenti
     *
     * @param vtlDataset    il dataset di partenza
     * @param vtlComponents la lista di componenti da aggiungere o sostituire al dataset di partenza
     * @return il nuovo dataset generato
     */
    public VtlDataset addComponentToDataset(VtlDataset vtlDataset, List<VtlComponent> vtlComponents) {
        VtlDataset vtlDatasetResult = createTemporaryDataset(vtlDataset.getVtlComponents(), vtlDataset.isOnlyAScalar());
        List<VtlComponent> datasetVtlComponents = vtlDatasetResult.getVtlComponents();
        for (VtlComponent vtlComponent : vtlComponents) {
            VtlComponent found = componentUtilityService.getComponentFromName(datasetVtlComponents, vtlComponent.getName(), true);
            if (found == null) {
                // non l'ho trovato quindi aggiungi
                vtlDatasetResult.addComponent(componentUtilityService.copyComponent(vtlComponent));
            } else {
                vtlDatasetResult = createTemporaryDataset(
                        componentUtilityService.removeComponentWithName(vtlDatasetResult.getVtlComponents(), found.getName()),
                        vtlDataset.isOnlyAScalar()
                );
                //L'ho trovato quindi rimuovi il vecchio e aggiungi il nuovo
                vtlDatasetResult.addComponent(componentUtilityService.copyComponent(vtlComponent));
            }
        }
        vtlDataset.setRequestUuid(vtlComponents.get(0).getRequestUuid());
        datasetIntegrationService.save(vtlDatasetResult);
        return vtlDatasetResult;
    }

    /**
     * Dato un nome di dataset questo viene cercato sui database. Il dataset può essere ricercato in case sensitive
     * e legato a una specifica sessione
     *
     * @param datasetName il nome del dataset
     * @param ignoreCase  questo flag indica se la ricerca deve essere fatt in case insensitive o case sensitive
     * @param requestUuid la sessione legata alla richiesta
     * @return il dataset trovato sul database
     */
    public VtlDataset getDatasetByName(String datasetName, boolean ignoreCase, UUID requestUuid) {
        if (ignoreCase)
            return datasetIntegrationService.findByNameIgnoreCase(datasetName, requestUuid);
        else
            return datasetIntegrationService.findByName(datasetName, requestUuid);

    }

    /**
     * il metodo preso in ingresso un dataset ne genera uno nuovo con il nome indicato.
     * Il nuovo database generato può essere settato come dataset persistente
     *
     * @param vtlDataset  il dataset di partenza
     * @param datasetName il nuovo nome di dataset da assegnare
     * @param isPersist   questo flag decide se il nuovo dataset generato dovrà essere persistente o no
     * @return il nuovo dataset generato
     */
    public VtlDataset changeDatasetName(VtlDataset vtlDataset, String datasetName, Boolean isPersist) {
        vtlDataset.setName(datasetName);
        vtlDataset.setTransitory(false);
        vtlDataset.setPersistent(isPersist);
        vtlDataset.setRequestUuid(vtlDataset.getRequestUuid());
        datasetIntegrationService.save(vtlDataset);
        return vtlDataset;
    }

    /**
     * il metodo prende in ingresso un dataset e una lista di identificativi e restituisce un nuovo dataset composto dai
     * nuovi identificativi e tutte le misure e gli attributi precedenti
     *
     * @param vtlDataset            il dataset di partenza
     * @param identifiersNameToKeep la nuova lista di identificativi del dataset
     * @return un nuovo dataset temporaneo
     */
    public VtlDataset groupIdentifiers(VtlDataset vtlDataset, List<VtlComponent> identifiersNameToKeep) {
        List<VtlComponent> identifiers = vtlDataset.getIdentifiers();
        List<VtlComponent> result = new ArrayList<>();
        for (VtlComponent vtlComponent : identifiers) {
            for (VtlComponent vtlComponentToKeep : identifiersNameToKeep) {
                if (vtlComponentToKeep.getName().equalsIgnoreCase(vtlComponent.getName())) {
                    result.add(vtlComponent);
                }
            }
        }
        result.addAll(vtlDataset.getMeasures());
        result.addAll(vtlDataset.getViral());
        return createTemporaryDataset(result, false);
    }

    public VtlDataset setNameMeasure(VtlDataset vtlDataset, String name) {
        vtlDataset.getMeasures().get(0).setName(name);
        datasetIntegrationService.save(vtlDataset);
        return vtlDataset;
    }

    /**
     * il metodo si occupa di implementare la funzionalità di interazione fra un dataset e uno scalare.
     * Tutte le misure del dataset interagiscono con il dataset e viene generato un nuovo dataset con le misure eventualmente
     * modificate di tipo(Secondo la dinamica di cast)
     *
     * @param vtlDataset il dataset di partenza
     * @param scalar     lo scalare che interagisce con il dataset
     * @return il nuovo dataset generato dall'interazione di uno scalare con un dataset
     */
    public VtlDataset mergeDatasetWithScalar(VtlDataset vtlDataset, VtlComponent scalar) {
        List<VtlComponent> measures = vtlDataset.getMeasures();
        List<VtlComponent> resultVtlComponent = componentUtilityService.getComponentsWithCast(measures, scalar);
        resultVtlComponent.addAll(vtlDataset.getIdentifiers());
        resultVtlComponent.addAll(vtlDataset.getViral());
        return createTemporaryDataset(resultVtlComponent, false);
    }

    /**
     * il metodo implementa la dinamica di interazione fra due scalari
     *
     * @param vtlDatasetLeft  un dataset che simula uno scalare
     * @param vtlDatasetRight un dataset che simula uno scalare
     * @return il dataset risultante. Potrebbe aver subito un cambiamento di tipo secondo le specifiche di cast
     */
    public VtlDataset mergeScalarWithScalar(VtlDataset vtlDatasetLeft, VtlDataset vtlDatasetRight) {
        List<VtlComponent> commonVtlComponents = componentUtilityService.getCommonComponentWithCast(vtlDatasetLeft.getMeasures(), vtlDatasetRight.getMeasures());
        return createTemporaryDataset(commonVtlComponents, true);
    }

    /**
     * il metodo implementa la dinamica di interazione fra un operatore e un dataset. L'oggetto Operator porta al suo interno
     * la trasformazione che deriva dalla sua applicazione.
     * Principalmente possono avvenire due operazioni una sostituzione delle misure o un cast verso un altro tipo.
     * Il metodo prevede la possibilità di aggiungere gli attributi virali a seconda del contesto di utilizzo
     *
     * @param vtlDataset il dataset di partenza
     * @param operator   un oggetto che rappresenta l'operazione da effettuare
     * @param addViral   questo flag disciplina se gli attributi virali del dataset di partenza devono essere presenti alla fine dell'operazione
     * @return il nuovo dataset con l'operatore applicato
     */
    public VtlDataset applyOperatorToDataset(VtlDataset vtlDataset, Operator operator, boolean addViral) {
        List<VtlComponent> vtlComponents = new ArrayList<>();
        vtlComponents.addAll(vtlDataset.getIdentifiers());
        if (operator.getSubstituteComponent()) {
            vtlComponents.addAll(componentUtilityService.substituteComponents(vtlDataset.getMeasures(), operator.getExpectedTypeReturn()));
        } else {
            for (VtlComponent measure : vtlDataset.getMeasures()) {
                VtlComponent vtlComponent = componentUtilityService.generateComponentWithCast(measure, operator.getExpectedTypeReturn());
                vtlComponents.add(vtlComponent);
            }
        }
        if (addViral)
            vtlComponents.addAll(vtlDataset.getViral());
        return createTemporaryDataset(vtlComponents, false);
    }

    /**
     * Il metodo prende in ingresso un dataset e un operatore e applica una sostituzione delle misure del dataset.
     *
     * @param vtlDataset il dataset su cui applicare un operatore
     * @param operator   un oggetto che rappresenta l'operazione da effettuare
     * @return il dataset risultante con le nuove misure sostituite.
     */
    public VtlDataset applySubstitutionToDataset(VtlDataset vtlDataset, Operator operator) {
        List<VtlComponent> vtlComponents = new ArrayList<>();
        vtlComponents.addAll(vtlDataset.getIdentifiers());
        vtlComponents.addAll(componentUtilityService.substituteComponents(vtlDataset.getMeasures(), operator.getExpectedTypeReturn()));

        vtlComponents.addAll(vtlDataset.getViral());
        return createTemporaryDataset(vtlComponents, vtlDataset.isOnlyAScalar());
    }

    /**
     * Il metodo prende in ingresso un operazione e un tipo e genera uno scalare come simulazione di risposta a un operatore
     * se non sono previste modifiche viene restituito un dataset che simula uno scalare che simula uno scalare dello stesso tipo
     * indicato al metodo
     *
     * @param operator    l'operatore da applicare
     * @param vtlType     il tipo di partenza
     * @param requestUuid la requestUuid della richiesta
     * @return un nuovo dataset che rappresenta uno scalare
     */
    public VtlDataset applySubstitutionToScalar(Operator operator, VtlType vtlType, UUID requestUuid) {
        List<VtlComponent> scalarMeasures = new ArrayList<>(); //Sarà sempre solo uno
        if (operator.getExpectedTypeReturn() != VtlType.NONE)
            scalarMeasures.add(componentUtilityService.getDefaultComponent(operator.getExpectedTypeReturn(), requestUuid));
        else
            scalarMeasures.add(componentUtilityService.getDefaultComponent(vtlType, requestUuid));
        return createTemporaryDataset(scalarMeasures, true);
    }

    /**
     * il metodo effettua un merging fra due dataset con le stesse misure.
     * Il dataset risultante avrà un superset degli identifier dei due dataset e delle nuove misure con il tipo più forte
     * secondo le dinamiche di cast di VTL
     *
     * @param vtlDatasetLeft  il primo dataset da unire
     * @param vtlDatasetRight il secondo dataset da unire
     * @return il nuovo dataset generato
     */
    public VtlDataset mergeDatasetWithDataset(VtlDataset vtlDatasetLeft, VtlDataset vtlDatasetRight) {
        List<VtlComponent> supersetIdentifier = componentUtilityService.getGreaterComponent(vtlDatasetLeft.getIdentifiers(), vtlDatasetRight.getIdentifiers());
        Map<String, List<VtlComponent>> componentMap = extractGreaterAndLesserComponent(vtlDatasetLeft.getMeasures(), vtlDatasetRight.getMeasures());

        List<VtlComponent> commonMeasure = componentUtilityService.getCommonComponentWithCast(componentMap.get(FIRST), componentMap.get(SECOND));
        componentMap = extractGreaterAndLesserComponent(vtlDatasetLeft.getViral(), vtlDatasetRight.getViral());
        List<VtlComponent> commonVirals = componentUtilityService.getCommonComponentWithCast(componentMap.get(FIRST), componentMap.get(SECOND));
        List<VtlComponent> allVtlComponent = new ArrayList<>();
        allVtlComponent.addAll(supersetIdentifier);
        allVtlComponent.addAll(commonMeasure);
        allVtlComponent.addAll(commonVirals);
        return createTemporaryDataset(allVtlComponent, false);
    }

    /**
     * il metodo dati due liste di componenti restituiscono una mappa di due elementi ordinati secondo lunghezza dei componenti.
     * Nel primo elemento è presente la lista di componenti maggiore
     * Nel secondo elmento è presenta la litsa di componenti minore
     *
     * @param componentsLeft    una lista di componenti da confrontare
     * @param vtlComponentRight una lista di componenti da confrontare
     * @return una mappa che ordina i componenti secondo lunghezza
     */
    public Map<String, List<VtlComponent>> extractGreaterAndLesserComponent(List<VtlComponent> componentsLeft, List<VtlComponent> vtlComponentRight) {
        Map<String, List<VtlComponent>> result = new HashMap<>();
        result.put(FIRST, componentUtilityService.getGreaterComponent(componentsLeft, vtlComponentRight));
        result.put(SECOND, componentUtilityService.getLesserComponent(componentsLeft, vtlComponentRight));
        return result;
    }

    /**
     * il metodo prende in ingresso un dataset e una lista di componenti. e crea un nuovo dataset con tutti gli identificativi
     * del dataset di partenza più tutti i componenti che sono inseriti nella lista.
     *
     * @param vtlDataset          il dataset di partenza
     * @param vtlComponentsToKeep tutti i componenti da aggiungere al dataset
     * @return il nuovo dataset generato
     */
    public VtlDataset keepDatasetComponent(VtlDataset vtlDataset, List<VtlComponent> vtlComponentsToKeep) {
        List<VtlComponent> vtlComponents = new ArrayList<>();
        vtlComponents.addAll(vtlDataset.getIdentifiers());
        vtlComponents.addAll(vtlComponentsToKeep);
        return createTemporaryDataset(vtlComponents, false);
    }

    /**
     * il metodo prende in ingresso un dataset e una lista di componenti. e crea un nuovo dataset eliminando tutti i componenti
     * inseriti nella lista dal dataset di partenza
     *
     * @param vtlDataset          il dataset di partenza
     * @param vtlComponentsToDrop tutti i componenti da rimuovere al dataset
     * @return il nuovo dataset generato
     */
    public VtlDataset dropDatasetComponents(VtlDataset vtlDataset, List<VtlComponent> vtlComponentsToDrop) {
        List<VtlComponent> vtlComponentsResult = new ArrayList<>();
        List<VtlComponent> vtlComponents = vtlDataset.getVtlComponents();
        for (VtlComponent vtlComponent : vtlComponents) {
            boolean keep = true;
            for (VtlComponent vtlComponentToDrop : vtlComponentsToDrop) {
                if (vtlComponentToDrop.getName().equalsIgnoreCase(vtlComponent.getName())) {
                    keep = false;
                }
            }
            if (keep) {
                vtlComponentsResult.add(componentUtilityService.getComponentFromName(vtlDataset.getVtlComponents(), vtlComponent.getName(), true));
            }
        }
        return createTemporaryDataset(vtlComponentsResult, false);
    }

    /**
     * Il metodo prende in ingresso un dataset e rinomina un componente.
     *
     * @param vtlDataset il dataset di partenza
     * @param fromName   il nome  da ricercare all'interno del dataset
     * @param toName     il nome con cui si vuole rinominare il componente
     * @return un nuovo dataset con il componente rinominato
     */
    public VtlDataset renameDatasetComponent(VtlDataset vtlDataset, String fromName, String toName) {
        VtlComponent vtlComponentTo = componentUtilityService.setComponentName(
                componentUtilityService.getComponentFromName(
                        vtlDataset.getVtlComponents(),
                        fromName,
                        true
                ),
                toName);

        List<VtlComponent> vtlComponentsResult =
                componentUtilityService.removeComponentWithName(
                        vtlDataset.getVtlComponents(),
                        fromName
                );
        vtlComponentsResult.add(vtlComponentTo);
        return createTemporaryDataset(vtlComponentsResult, false);
    }

    /**
     * il metodo prende in ingresso un dataset e un nome di componente e genera un nuovo dataset dove è stato
     * eliminato il componente del nome inserito.
     *
     * @param vtlDataset    il dataset di partenza
     * @param componentName il nome del componente da eliminare dal dataset
     * @return il nuovo dataset senza il componente indicato
     */
    public VtlDataset removeComponentFromName(VtlDataset vtlDataset, String componentName) {
        List<VtlComponent> vtlComponentsResult = vtlDataset.getVtlComponents();
        vtlComponentsResult = componentUtilityService.removeComponentWithName(vtlComponentsResult, componentName);
        return createTemporaryDataset(vtlComponentsResult, false);
    }

    /**
     * Il metodo prende in ingresso un dataset e un tipo e applica il cast diretto sui componenti.
     *
     * @param vtlDataset il dataset di partenza
     * @param vtlType    il tipo da imporre a tutte le misure del dataset
     * @return il nuovo dataset generato
     */
    public VtlDataset doCast(VtlDataset vtlDataset, VtlType vtlType) {
        List<VtlComponent> measures = vtlDataset.getMeasures();
        List<VtlComponent> componentsCast = new ArrayList<>();
        for (VtlComponent vtlComponent : measures) {
            VtlComponent vtlComponentCast = componentUtilityService.copyComponent(vtlComponent);
            vtlComponentCast.setType(vtlType);
            vtlComponentCast.setDomainValueParent(vtlType.getDomainValueParent());
            componentsCast.add(vtlComponentCast);
        }
        return createDatasetFromMeasure(vtlDataset, componentsCast, true);
    }

    /**
     * deprecato
     *
     * @param vtlDataset
     * @param vtlType
     * @return
     */
    public VtlDataset castDataset(VtlDataset vtlDataset, VtlType vtlType) {
        List<VtlComponent> vtlComponents = new ArrayList<>(vtlDataset.getIdentifiers());
        for (VtlComponent vtlComponent : vtlDataset.getMeasures()) {
            if (vtlTypeUtilityService.isCastable(vtlComponent.getType(), vtlType)) {
                return null;
            }
            vtlComponents.add(
                    componentUtilityService.castComponent(vtlComponent, vtlType)
            );
        }
        vtlComponents.addAll(vtlDataset.getViral());
        return createTemporaryDataset(vtlComponents, vtlDataset.isOnlyAScalar());
    }

    /**
     * il metodo crea la tabella di sintesi necessaria all'ultimo passaggio della costruzione del risultato della join fra dataset
     *
     * @param vtlDatasets        i dataset da unire
     * @param supersetIdentifier gli identificativi superset dei dataset(i nuovi identificativi)
     * @return un dataset di sintesi della join
     */
    public VtlDataset joinDataset(List<VtlDataset> vtlDatasets, List<VtlComponent> supersetIdentifier) {
        Map<String, Integer> commonMeasure = componentUtilityService.countCommonComponent(vtlDatasets, false);
        List<VtlComponent> result = new ArrayList<>();
        for (VtlDataset vtlDataset : vtlDatasets) {
            result.addAll(
                    componentUtilityService.joinComponent(
                            vtlDataset.getName(),
                            componentUtilityService.extractComponentExcludingIdentifiers(vtlDataset),
                            commonMeasure
                    )
            );
        }

        result.addAll(0, supersetIdentifier);
        return createTemporaryDataset(result, false);
    }

    /**
     * il metodo prende in ingresso una lista di dataset e verifica se esiste un superset fra tutti i dataset
     *
     * @param vtlDatasets la lista dei dataset da analizzare
     * @return true se esiste un superset di identificativi false altrimenti.
     */
    public boolean existSupersetDatasets(LinkedList<VtlDataset> vtlDatasets) {
        boolean result = true;
        for (VtlDataset vtlDataset : vtlDatasets) {
            for (VtlDataset otherVtlDataset : vtlDatasets) {
                if (vtlDataset != otherVtlDataset)
                    result = result && isSuperset(vtlDataset, otherVtlDataset);
            }
        }
        return result;
    }

    /**
     * il metodo riconosce se i due dataset inseriti sono uno superset dell'altro.
     *
     * @param vtlDataset      il dataset principale
     * @param otherVtlDataset il secondo dataset da confrontare
     * @return true se sono superset false altrimenti
     */
    public boolean isSuperset(VtlDataset vtlDataset, VtlDataset otherVtlDataset) {
        List<VtlComponent> supersetIdentifiers = null;
        List<VtlComponent> identifiers = null;

        if (vtlDataset.isOnlyAScalar() || otherVtlDataset.isOnlyAScalar())
            return true;

        supersetIdentifiers = componentUtilityService.getGreaterComponent(vtlDataset.getIdentifiers(), otherVtlDataset.getIdentifiers());
        identifiers = componentUtilityService.getLesserComponent(vtlDataset.getIdentifiers(), otherVtlDataset.getIdentifiers());
        return supersetIdentifiers.containsAll(identifiers);
    }

    /**
     * il metodo prende in ingresso una lista di dataset e verifica che tutti i dataset abbiano gli stessi identifier
     *
     * @param vtlDatasets i dataset da controllare
     * @return true se tutti i dataset hanno gli stessi identificativi, false altrimenti
     */
    public Boolean hasSameIdentifiers(List<VtlDataset> vtlDatasets) {
        Boolean result = true;
        List<VtlComponent> commonIdentifiers = vtlDatasets.get(0).getIdentifiers();
        for (VtlDataset vtlDataset : vtlDatasets) {
            result = result && vtlDataset.getIdentifiers().equals(commonIdentifiers);
        }
        return result;
    }

    /**
     * Data una lista di dataset e una lista di componenti viene restituito il dataset che contiene tutti componenti ricercati
     *
     * @param vtlDatasets   i dataset su cui ricercare i componenti
     * @param vtlComponents i componenti ricercati
     * @return un dataset che contiene tutti i componenti ricercati o null
     */
    public VtlDataset getDatasetThatContainsComponents(List<VtlDataset> vtlDatasets, List<VtlComponent> vtlComponents) {
        VtlDataset result = null;
        for (VtlDataset vtlDataset : vtlDatasets) {
            if (vtlDataset.getVtlComponents().containsAll(vtlComponents)) {
                return vtlDataset;
            }
        }
        return result;
    }

    /**
     * il metodo si occupa di trovare il dataset di riferimento fra tutti i dataset messi in join.
     * il dataset di riferimento rappresenta una particolare modalità di join che avviene fra dataset che hanno tutti gli identifier in comune
     * a meno uno che presenta tutti i componenti in comune tranne uno che non è identificativo. Quel dataset diventa il nuovo
     * dataset di riferimento
     *
     * @param vtlDatasets   una lista di dataset da mettere in join
     * @param vtlComponents la lista di componenti che devono essere in comune a tutti dataset. Sono indicati nella using della join
     * @return il dataset di riferimento per la join
     * @throws ComponentNotFoundException uno dei componenti non è stato trovato su tutti i dataset. Non è possibile elevare a dataset di riferimento nessun dataset
     * @throws UsingNotFoundException     non esiste la using nella join.
     */
    public VtlDataset getDatasetCandidate(List<VtlDataset> vtlDatasets, List<VtlComponentId> vtlComponents) throws ComponentNotFoundException, UsingNotFoundException {
        if (vtlComponents == null || vtlComponents.isEmpty())
            throw new UsingNotFoundException(" ");
        for (VtlDataset vtlDataset : vtlDatasets) {
            boolean atLeastOneNotIdentifier = false;
            for (VtlComponentId vtlComponentId : vtlComponents) {
                VtlComponent vtlComponent = componentUtilityService.getComponentFromName(vtlDataset.getVtlComponents(), vtlComponentId.getComponentName(), true);
                if (vtlComponent == null)
                    throw new ComponentNotFoundException(" " + vtlComponentId.getComponentName() + " ");
                atLeastOneNotIdentifier = atLeastOneNotIdentifier || !vtlComponent.getVtlComponentRole().equals(VtlComponentRole.IDENTIFIER);
            }
            if (atLeastOneNotIdentifier) {
                return vtlDataset;
            }
        }
        return null;
    }

    /**
     * il metodo controlla che il dataset candidato abbia tutti gli identificativi indicati nella using in comune con gli altri dataset
     *
     * @param vtlDatasets         la lista di dataset da controllare
     * @param vtlDatasetToElevate il dataset di riferimento presunto
     * @param vtlComponentIds     la lista di componenti richiamati nella using
     * @return true se il dataset è candidabile, false altrimenti.
     */
    public boolean isAllIdentifiersInOtherDataset(List<VtlDataset> vtlDatasets, VtlDataset vtlDatasetToElevate, List<VtlComponentId> vtlComponentIds) {
        boolean result = true;
        for (VtlDataset vtlDataset : vtlDatasets) {
            if (!vtlDataset.getName().equalsIgnoreCase(vtlDatasetToElevate.getName())) {
                for (VtlComponentId vtlComponentId : vtlComponentIds) {
                    VtlComponent vtlComponent = componentUtilityService.getComponentFromName(vtlDataset.getVtlComponents(), vtlComponentId.getComponentName(), true);
                    result = result && vtlComponent != null && vtlComponent.getVtlComponentRole().equals(VtlComponentRole.IDENTIFIER);
                }
            }
        }
        return result;
    }

    /**
     * Data una lista di componenti e una lista di dataset e un dataset cantdidato viene verificato che tutti i dataset
     * a eccezione di quello candidato abbiano tutti i componenti ricercati come identificativi
     *
     * @param vtlDatasets         la lista di dataset da controlare
     * @param vtlDatasetToElevate il dataset candidato a diventare dataset di riferimento
     * @param vtlComponentIds     la lista di componenti che devono essere identificativi su tutti i dataset a eccezione del dataset di riferimento
     * @return true se tutti i dataset contengono la lista di componenti come identificativi false altrimenti.
     */
    public boolean isUsingAllIdentifiersInOtherDataset(List<VtlDataset> vtlDatasets, VtlDataset vtlDatasetToElevate, List<VtlComponentId> vtlComponentIds) {
        boolean result = true;
        for (VtlDataset vtlDataset : vtlDatasets) {
            if (!vtlDataset.getName().equalsIgnoreCase(vtlDatasetToElevate.getName())) {
                if (vtlComponentIds.size() != vtlDataset.getIdentifiers().size()) {
                    result = false;
                }
                for (VtlComponentId vtlComponentId : vtlComponentIds) {
                    VtlComponent vtlComponent = componentUtilityService.getComponentFromName(vtlDataset.getIdentifiers(), vtlComponentId.getComponentName(), true);
                    result = result && vtlComponent != null;
                }
            }
        }
        return result;
    }

    /**
     * dato in ingresso un dataset vengono eliminati tutti i componenti con nomi ridondanti e viene creato un nuovo dataset
     *
     * @param vtlDataset il dataset sul quale eliminare i componenti duplicati
     * @return un nuovo dataset temporaneo senza componenti duplicati
     */
    public VtlDataset removeRedundantNames(VtlDataset vtlDataset) {
        List<VtlComponent> resultComponent = componentUtilityService.renameDuplicateComponents(vtlDataset.getVtlComponents());
        if (resultComponent.isEmpty())
            return null;
        return createTemporaryDataset(resultComponent, false);
    }

    /**
     * il metodo simula il funzionamento degli operatori di validazione. in base alle opzioni passate vengono
     * generate diverse strutture risultanti.
     *
     * @param vtlDataset il dataset su cui applicare la validazione
     * @param vtlType    il tipo dell'imbalance(se presente)
     * @param outputMode le modalità di output delle check validation
     * @param ruleId     se true viene aggiunto il componente della ruleId
     * @param imbalance  se true viene aggiunto il componente dell'imbalance
     * @return il dataset risultato della check
     */
    public VtlDataset checkValidation(VtlDataset vtlDataset, VtlType vtlType, OutputMode outputMode, boolean ruleId, boolean imbalance) {
        VtlDataset result = new VtlDataset();
        result.setName(getName());
        result.setDescription(vtlDataset.getName());
        result.setRequestUuid(vtlDataset.getRequestUuid());
        result.addComponentsList(componentUtilityService.copyComponents(vtlDataset.getIdentifiers()));
        if (ruleId) {
            VtlComponent componentRule = componentUtilityService.getDefaultComponent(VtlType.STRING, vtlDataset.getRequestUuid());
            componentRule.setName("ruleid");
            componentRule.setVtlComponentRole(VtlComponentRole.IDENTIFIER);
            result.addComponent(componentRule);
        }
        if (outputMode == OutputMode.ALL) {
            VtlComponent componentBool = componentUtilityService.getDefaultComponent(VtlType.BOOLEAN, vtlDataset.getRequestUuid());
            result.addComponent(componentBool);
        } else if (outputMode == OutputMode.INVALID || outputMode == OutputMode.ALL_MEASURE) {
            result.addComponentsList(componentUtilityService.copyComponents(vtlDataset.getMeasures()));
            if (outputMode == OutputMode.ALL_MEASURE) {
                result.addComponent(componentUtilityService.getDefaultComponent(VtlType.BOOLEAN, vtlDataset.getRequestUuid()));
            }
        }
        result.addComponentsList(componentUtilityService.copyComponents(vtlDataset.getViral()));
        VtlComponent componentErrorCode = componentUtilityService.getDefaultComponent(VtlType.STRING, vtlDataset.getRequestUuid());
        componentErrorCode.setName("errorcode");
        result.addComponent(componentErrorCode);
        VtlComponent componentErrorLevel = componentUtilityService.getDefaultComponent(VtlType.STRING, vtlDataset.getRequestUuid());
        componentErrorLevel.setName("errorlevel");
        result.addComponent(componentErrorLevel);
        if (imbalance) {
            VtlComponent componentImbalance;
            if (vtlType != null)
                componentImbalance = componentUtilityService.getDefaultComponent(vtlType, vtlDataset.getRequestUuid());
            else
                componentImbalance = componentUtilityService.getDefaultComponent(VtlType.NUMBER, vtlDataset.getRequestUuid());
            componentImbalance.setName("imbalance");
            result.addComponent(componentImbalance);
        }
        datasetIntegrationService.save(result);
        return result;
    }

    /**
     * il metodo pratica l'operazione di unpivot su un dataset
     *
     * @param vtlDataset il dataset su cui praticare l'unpivot
     * @param identifier l'identificativo di riferimento per l'unpivot
     * @param meaasure   la misura di riferimento per l'unpivot
     * @return il nuovo dataset generato
     * @throws ValidationException un errore di validazione viene generato se non esiste l'identifier o la measure indicati
     */
    public VtlDataset unpivot(VtlDataset vtlDataset, VtlComponentId identifier, VtlComponentId meaasure) throws ValidationException {
        if (
                componentUtilityService.getComponentFromName(
                        vtlDataset.getVtlComponents(), identifier.getComponentName(), true) != null
                        || componentUtilityService.getComponentFromName(vtlDataset.getVtlComponents(), meaasure.getComponentName(), true)
                        != null) {
            throw new ValidationException("");
        }
        VtlDataset result = new VtlDataset();
        result.setName(getName());
        result.setDescription(vtlDataset.getName());
        result.addComponentsList(componentUtilityService.copyComponents(vtlDataset.getIdentifiers()));
        VtlComponent vtlComponentIdentifier = componentUtilityService.getDefaultComponent(VtlType.STRING, vtlDataset.getRequestUuid());
        vtlComponentIdentifier.setName(identifier.getComponentName());
        vtlComponentIdentifier.setDomainValue(VtlType.STRING.getDomainValue());
        vtlComponentIdentifier.setDomainValueParent(VtlType.STRING.getDomainValueParent());
        vtlComponentIdentifier.setVtlComponentRole(VtlComponentRole.IDENTIFIER);
        result.addComponent(vtlComponentIdentifier);
        VtlComponent vtlComponentMeasure = componentUtilityService.getDefaultComponent(vtlDataset.getMeasures().get(0).getType(), vtlDataset.getRequestUuid());
        vtlComponentMeasure.setName(meaasure.getComponentName());
        vtlComponentMeasure.setDomainValue(vtlDataset.getMeasures().get(0).getDomainValue());
        vtlComponentMeasure.setDomainValueParent(vtlDataset.getMeasures().get(0).getDomainValueParent());
        result.addComponent(vtlComponentMeasure);
        return result;
    }

    /**
     * il metodo pratica l'operazione di pivot su un dataset
     *
     * @param vtlDataset             il dataset su cu applicare il pivot
     * @param identifier             l'identifier di riferimento per il pivot
     * @param meaasure               la misura di riferimento per il pivot
     * @param vtlConstantExpressions la lista di espressioni che rappresentano i diversi valori raggruppati della pivot che diventeranno componenti del dataset
     * @return il dataset risultante dell'operazione di pivot
     * @throws ValidationException questa eccezione viene lanciata se esiste già un componente che si chiama come il nome di una delle costanti passate.
     */
    public VtlDataset pivot(VtlDataset vtlDataset, VtlComponentId identifier, VtlComponentId meaasure, List<VtlConstantExpression> vtlConstantExpressions) throws ValidationException {
        VtlDataset result = new VtlDataset();
        result.setName(getName());
        result.setDescription(vtlDataset.getName());
        List<VtlComponent> vtlComponents =
                componentUtilityService.removeComponentWithName(
                        componentUtilityService.copyComponents(vtlDataset.getIdentifiers()),
                        identifier.getComponentName()
                );

        for (VtlConstantExpression vtlConstantExpression : vtlConstantExpressions) {
            VtlComponent vtlComponent = componentUtilityService.getDefaultComponent(meaasure.getResultExpression().getResultComponent().getType(), vtlDataset.getRequestUuid());

            if (componentUtilityService.getComponentFromName(vtlDataset.getVtlComponents(), vtlConstantExpression.getVtlConstant().getValue().toString(), true) != null) {
                throw new ValidationException("");
            }

            try {
                Integer.parseInt(vtlConstantExpression.getVtlConstant().getValue().toString());
                vtlComponent.setName(identifier.getComponentName() + "_" + vtlConstantExpression.getVtlConstant().getValue().toString());
            } catch (Exception e) {
                vtlComponent.setName(vtlConstantExpression.getVtlConstant().getValue().toString());
            }

            vtlComponents.add(vtlComponent);
        }

        result.addComponentsList(vtlComponents);
        return result;
    }

    /**
     * il metodo implementa la funzionalità di exit in fra due dataset
     *
     * @param vtlDatasetLeft  il primo dataset da confrontare
     * @param vtlDatasetRight il secondo dataset da confrontare
     * @return il dataset risultante dell'existIn
     */
    public VtlDataset existInDataset(VtlDataset vtlDatasetLeft, VtlDataset vtlDatasetRight) {
        VtlDataset result = new VtlDataset();
        result.setName(getName());
        result.setDescription(vtlDatasetLeft.getName());
        result.addComponentsList(componentUtilityService.copyComponents(vtlDatasetLeft.getIdentifiers()));
        result.addComponent(componentUtilityService.getDefaultComponent(VtlType.BOOLEAN, vtlDatasetLeft.getRequestUuid()));
        result.addComponentsList(
                componentUtilityService.copyComponents(
                        componentUtilityService.getCommonComponent(
                                vtlDatasetLeft.getIdentifiers(),
                                vtlDatasetRight.getIdentifiers()
                        )
                )
        );
        return result;
    }

}
