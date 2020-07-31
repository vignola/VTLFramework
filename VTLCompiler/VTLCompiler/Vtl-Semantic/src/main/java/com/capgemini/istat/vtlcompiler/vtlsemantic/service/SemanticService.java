package com.capgemini.istat.vtlcompiler.vtlsemantic.service;

import com.capgemini.istat.vtlcompiler.vtlcommon.model.KeyVariables;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.exception.ValidationException;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.semantic.ResultExpression;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.statement.VtlStatement;
import com.capgemini.istat.vtlcompiler.vtlcommon.service.IDatasetIntegration;
import com.capgemini.istat.vtlcompiler.vtlsemantic.SemanticFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * la classe si occupa di gestire le richieste di validazione semantica proveniente dalle api di frontend
 */
@Service
public class SemanticService {
    public static final Logger logger = LogManager.getLogger(SemanticService.class);
    private SemanticFactory semanticFactory;
    private IDatasetIntegration datasetIntegrationService;
    
    @Autowired
    public void setDatasetIntegration(IDatasetIntegration datasetIntegrationService) {
		this.datasetIntegrationService = datasetIntegrationService;
	}

    public SemanticFactory getSemanticFactory() {
        return semanticFactory;
    }

    @Autowired
    public void setSemanticFactory(SemanticFactory semanticFactory) {
        this.semanticFactory = semanticFactory;
    }

    /**
     * Il metodo prende in ingresso una lista di statement e il relativo requestUuid e avvia il motore semantico.
     * Se l'uuid è null ne viene generato uno a runtime. AL termine della validazione vengono cancellati tutti i dataset
     * temporanei generati per i singoli passaggi della validazione. Nel caso la validazione generi errori l'eccezione viene propagata
     * ai metodi chiamanti
     * <p>
     * Il flusso di elaborazione del motore semantico:
     * - Prende in ingresso una lista di comandi interpretati(VtlStatement),
     * - Attiva i singoli nodi di validazione tramite la classe {@link SemanticFactory} fino a costruire un albero di validazione.
     * - Ogni nodo tramite la classe SemanticFactory accede ai risultati del nodo più in profondità e restituisce il proprio risultato
     * - Ogni nodo attiva i metodi specifici offerti dai servizi di validazione e trasformazione del dato {@link com.capgemini.istat.vtlcompiler.vtlsemantic.service.result.FunctionResultService},{@link com.capgemini.istat.vtlcompiler.vtlsemantic.service.result.UserFunctionResultService}
     * - Ogni nodo setta il risultato ottenuto nell'oggetto di interpretazione ricevuto
     * - I risultati di tutti i nodi vengono ordinati, raccolti e restituiti dalla classe SemanticService.
     * </p>
     *
     * @param statementList la lista di statement da validare
     * @param requestUuid   il codice della richiesta
     * @return il metodo ritorna una lista di liste dei risultati della validazione. Una lista per ogni statement.
     * @throws Exception il metodo può generare una serie di eccezioni che rappresentano gli errori di validazione trovati
     *                   dal motore semantico.
     */
    public List<LinkedList<ResultExpression>> validateSemantic(List<VtlStatement> statementList, UUID requestUuid) throws Exception {
        String command = null;
        List<LinkedList<ResultExpression>> results = new ArrayList<>();
        UUID uuid = requestUuid;
        if (uuid == null)
            uuid = UUID.randomUUID();

        try {


            for (VtlStatement vtlStatement : statementList) {
                command = vtlStatement.getCommand();
                logger.info("--------------->    Comando in validazione -> " + command);
                Map<KeyVariables, Object> parameter = new HashMap<>();
                parameter.put(KeyVariables.REQUEST_UUID, uuid);
                results.add(semanticFactory.checkSemantic(vtlStatement, parameter));
                logger.info("--------------->    Comando validato");

            }
        } catch (ValidationException validationExcepiton) {
            deleteTemporaryDataset(uuid);
            validationExcepiton.setCommand(command);
            throw validationExcepiton;
        }
        deleteTemporaryDataset(uuid);
        return results;
    }

    /**
     * Cancella tutti i dataset temporanei marcati dalla requestUuid
     *
     * @param requestUuid il requestUuid da ricercare
     */
    private void deleteTemporaryDataset(UUID requestUuid) {
        logger.info("Dataset temporanei cancellati -> " + datasetIntegrationService.deleteAllByIsPersistentEquals(false, requestUuid));
    }

}
