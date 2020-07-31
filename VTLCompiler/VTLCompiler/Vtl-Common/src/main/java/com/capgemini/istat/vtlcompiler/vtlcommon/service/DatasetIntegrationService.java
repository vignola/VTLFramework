package com.capgemini.istat.vtlcompiler.vtlcommon.service;

import com.capgemini.istat.vtlcompiler.vtlcommon.model.dataset.VtlDataset;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.valuedomain.ValueDomain;
import com.capgemini.istat.vtlcompiler.vtlcommon.repository.DatasetRepository;
import com.capgemini.istat.vtlcompiler.vtlcommon.repository.ValueDomainRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

/**
 * la classe si occupa di prelevare i dati dai database.
 * La logica principale è che se il dato viene ricercato prima sulla vista e dopo, se non viene trovato, nel database interno
 * dell'applicazione.
 * Se viene attivato il profilo di utilizzo del solo db interno vengono ignorati i dati provenienti dalla vista.
 * La requestUuid viene utilizzata per trovare le tabelle legate alla sessione o legare una singola tabella temporanea a una sessione
 *
 * @see VtlDatabaseService
 */
@Service
public class DatasetIntegrationService implements IDatasetIntegration {
    private DatasetRepository datasetRepository;
    private VtlDatabaseService vtlDatabaseService;
    private ValueDomainRepository valueDomainRepository;
    private Environment environment;

    @Autowired
    public void setDatasetRepository(DatasetRepository datasetRepository) {
        this.datasetRepository = datasetRepository;
    }

    @Autowired
    public void setVtlDatabaseService(VtlDatabaseService vtlDatabaseService) {
        this.vtlDatabaseService = vtlDatabaseService;
    }

    @Autowired
    public void setValueDomainRepository(ValueDomainRepository valueDomainRepository) {
        this.valueDomainRepository = valueDomainRepository;
    }

    @Autowired
    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }

    /**
     * il metodo genera un nome per la tabella temporanea. La sequenza è data da una sequence sul db interno
     *
     * @return il nome del dataset temporaneo
     */
    public String getName() {
        return "temporary_" + datasetRepository.getTemporaryId();
    }

    /**
     * il metodo dato in ingresso un dataset lo salva sul db interno
     *
     * @param result il dataset da salvare
     */
    public void save(VtlDataset result) {
        this.datasetRepository.save(result);
    }

    /**
     * il metodo dato in ingresso un nome tabella legato a un determinato requestUUid lo cercano sui database
     *
     * @param name        il nome del dataset ricercato
     * @param requestUuid il requestUuid legato alla sessione
     * @return il dataset trovato, null se non è presente.
     */
    @Override
    public VtlDataset findByNameIgnoreCase(String name, UUID requestUuid) {
        VtlDataset repositoryResult = fuondVtlDatasetByUuid(this.datasetRepository.findAllByNameIgnoreCase(name), requestUuid);

        if (repositoryResult == null) {
            if (!isActiveProfileNoExternalDB()) {
                repositoryResult = this.vtlDatabaseService.findByName(name, true);
                if (repositoryResult != null) {
                    repositoryResult.setRequestUuid(requestUuid);
                    this.save(repositoryResult);
                }
            }
        } else {
            repositoryResult.setRequestUuid(requestUuid);
        }
        return repositoryResult;
    }

    /**
     * il metodo ricerca un dataset sul database tenendo conto del case del nome tabella
     *
     * @param name        il nome del dataset ricercato
     * @param requestUuid la requestUuid legato alla sessione
     * @return il dataset ricercato. null se non ne trova nessuno
     */
    @Override
    public VtlDataset findByName(String name, UUID requestUuid) {
        VtlDataset result = fuondVtlDatasetByUuid(datasetRepository.findAllByName(name), requestUuid);
        if (result == null) {
            if (!isActiveProfileNoExternalDB()) {
                result = this.vtlDatabaseService.findByName(name, false);
                if (result != null) {
                    result.setRequestUuid(requestUuid);
                    this.save(result);
                }
            }
        } else {
            result.setRequestUuid(requestUuid);
        }
        return result;
    }

    /**
     * Il metodo dato un nome tabella, conta sulla vista il numero di volte in cui è presente una determinata tabella.
     * La ricerca viene fatta in caseInsensitive
     *
     * @param name        il nome della tabella ricercata
     * @param requestUuid la requestUuid legato alla sessione
     * @return il numero in cui occore la tabella ricercata
     */
    @Override
    public Integer countAllByNameIgnoreCase(String name, UUID requestUuid) {
        Integer result = 0;
        VtlDataset repositoryResult = fuondVtlDatasetByUuid(this.datasetRepository.findAllByNameIgnoreCase(name), requestUuid);
        if (repositoryResult != null)
            result++;
        if (result == 0 && !isActiveProfileNoExternalDB()) {
            result = this.vtlDatabaseService.countAllByName(name, true);
        }

        return result;
    }

    /**
     * Il metodo dato un nome tabella, conta sulla vista il numero di volte in cui è presente una determinata tabella.
     * La ricerca viene fatta in caseSensitive
     *
     * @param name        il nome della tabella ricercata
     * @param requestUuid la requestUuid legato alla sessione
     * @return il numero in cui occore la tabella ricercata
     */
    @Override
    public Integer countAllByName(String name, UUID requestUuid) {
        Integer result = 0;
        VtlDataset repositoryResult = fuondVtlDatasetByUuid(this.datasetRepository.findAllByName(name), requestUuid);
        if (repositoryResult != null)
            result++;
        if (result == 0 && !isActiveProfileNoExternalDB()) {
            result = this.vtlDatabaseService.countAllByName(name, false);
        }

        return result;
    }


    /**
     * viene restituito il prossimo numero della sequence del database interno. Non si possono verificare ripetizioni
     *
     * @return un identificativo proveniente da una sequence.
     */
    @Override
    public Long getTemporaryId() {
        return this.datasetRepository.getTemporaryId();
    }

    /**
     * Cancella tutti i dataset legati a una certa richiesta. Il metodo può cancellare dataset temporanei o persistenti
     *
     * @param isPersistent indica se il dataset è persistente o no
     * @param requestUuid  la requestUuid legato alla sessione
     * @return il numero di dataset cancellati
     */
    @Override
    public Integer deleteAllByIsPersistentEquals(Boolean isPersistent, UUID requestUuid) {
        return this.datasetRepository.deleteAllByIsPersistentEqualsAndRequestUuid(isPersistent, requestUuid);
    }

    /**
     * Restituisce tutti i dataset filtrati per persistenza
     *
     * @param isPersistent indica se il dataset è persistente o no
     * @return una lista di dataset. Null se non ne viene trovato nessuno
     */
    @Override
    public List<VtlDataset> findAllByIsPersistentEquals(Boolean isPersistent) {
        List<VtlDataset> repositoryResult = this.datasetRepository.findAllByIsPersistentEquals(isPersistent);
        return repositoryResult;
    }

    /**
     * Cancella un dataset tramite id
     *
     * @param id l'id del dataset da cancellare
     */
    @Override
    public void deleteById(Long id) {
        this.datasetRepository.deleteById(id);
    }

    /**
     * Dato un nome di dataset cancella tutte le occorrenze del dataset(per qualsiasi requestUuid)
     *
     * @param vtlDatasetName il nome di dataset da cancellare
     * @return il numero di dataset cancellati
     */
    @Override
    public Integer deleteByName(String vtlDatasetName) {
        return this.datasetRepository.deleteByName(vtlDatasetName);
    }

    /**
     * Restituisce tutti i dataset del database interno ordinati per nome
     *
     * @return una lista di dataset
     */
    @Override
    public List<VtlDataset> findAllByOrderByNameAsc() {
        List<VtlDataset> repositoryResult = this.datasetRepository.findAllByOrderByNameAsc();
        return repositoryResult;
    }

    /**
     * Restituisce tutti i dataset salvati sul database interno
     *
     * @return una lista contenente tutti i dataset
     */
    @Override
    public List<VtlDataset> findAll() {
        List<VtlDataset> repositoryResult = this.datasetRepository.findAll();
        return repositoryResult;
    }

    /**
     * cancella tutti i dataset esistenti sul db interno
     */
    @Override
    public void deleteAll() {
        this.datasetRepository.deleteAll();
    }

    /**
     * Dato un nome di valueDomain restituisce tutti i valori ad esso collegati. una volta prelevati dalla vista,
     * il value domain viene salvato sul db interno
     *
     * @param valueDomainName il nome del valueDomain
     * @return una lista contenente tutti i valori del value domain
     */
    //VALUEDOMAIN METHODS
    @Override
    public List<ValueDomain> getAllByValueDomainNameIgnoreCase(String valueDomainName) {
        List<ValueDomain> repositoryResult = this.valueDomainRepository.getAllByValueDomainNameIgnoreCase(valueDomainName);
        if (repositoryResult.isEmpty() && !isActiveProfileNoExternalDB()) {
            repositoryResult = this.vtlDatabaseService.getValueDomain(valueDomainName);
            if (!repositoryResult.isEmpty()) {
                this.valueDomainRepository.saveAll(repositoryResult);
            }
        }

        return repositoryResult;
    }

    /**
     * il metodo prende in ingresso un nome di value domain e un valuedomainCode, verifica che esista e lo restituisce
     *
     * @param valueDomainName il nome del valueDomain ricercato
     * @param code            il codice legato al valuedomain ricercato
     * @return il valuedomain trovato. null se non esiste
     */
    @Override
    public ValueDomain getByValueDomainNameIgnoreCaseAndCodeIgnoreCase(String valueDomainName, String code) {
        ValueDomain repositoryResult = this.valueDomainRepository.getByValueDomainNameIgnoreCaseAndCodeIgnoreCase(valueDomainName, code);
        if (repositoryResult == null && !isActiveProfileNoExternalDB()) {
            List<ValueDomain> repositoryResultList = this.vtlDatabaseService.getValueDomain(valueDomainName);
            if (!repositoryResultList.isEmpty()) {
                this.valueDomainRepository.deleteByValueDomainName(valueDomainName);
                this.valueDomainRepository.saveAll(repositoryResultList);
            }
            repositoryResult = this.valueDomainRepository.getByValueDomainNameIgnoreCaseAndCodeIgnoreCase(valueDomainName, code);
        }

        return repositoryResult;
    }

    /**
     * questo metodo prende in ingresso un valueDomain e lo salva sul database interno
     *
     * @param result il value domain da salvare
     */
    @Override
    public void save(ValueDomain result) {
        this.valueDomainRepository.save(result);
    }

    /**
     * il metodo prende in ingresso una lista di dataset e un determinato requestUuid e restituisce il primo dataset
     * che corrisponde con il requestUuid  immesso o il primo dataset persistente trovato.
     *
     * @param vtlDatasets una lista di vtlDataset
     * @param requestUuid il requestUuid legato alla sessione
     * @return un dataset corrispondente al requestUuid, o in mancanza un dataset persistente.
     */
    private VtlDataset fuondVtlDatasetByUuid(List<VtlDataset> vtlDatasets, UUID requestUuid) {
        VtlDataset vtlDatasetPersistent = null;
        if (vtlDatasets == null)
            return null;
        for (VtlDataset vtlDataset : vtlDatasets) {
            if (vtlDataset.isPersistent())
                vtlDatasetPersistent = vtlDataset;
            if (vtlDataset.getRequestUuid() != null && vtlDataset.getRequestUuid().equals(requestUuid))
                return vtlDataset;
        }
        return vtlDatasetPersistent;
    }

    /**
     * il metodo restituisce true se è attivo il profilo "noExternalDB"
     *
     * @return true se + attivo il profilo noExternalDB
     */
    public boolean isActiveProfileNoExternalDB() {
        for (String profile : environment.getActiveProfiles()) {
            if (profile.equalsIgnoreCase("noExternalDB")) {
                return true;
            }
        }
        return false;
    }

}
