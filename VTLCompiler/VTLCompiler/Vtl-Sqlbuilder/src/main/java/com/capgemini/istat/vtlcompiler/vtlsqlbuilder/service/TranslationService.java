package com.capgemini.istat.vtlcompiler.vtlsqlbuilder.service;

import com.capgemini.istat.vtlcompiler.vtlcommon.model.KeyVariables;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.exception.TraslationException;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.semantic.ValidationCheck;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.translation.SqlResult;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.statement.VtlStatement;
import com.capgemini.istat.vtlcompiler.vtlcommon.service.DatasetIntegrationService;
import com.capgemini.istat.vtlcompiler.vtlsqlbuilder.TranslationFactory;
import com.healthmarketscience.sqlbuilder.CustomSql;
import com.healthmarketscience.sqlbuilder.SqlObject;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Questa classe rappresenta il servizio di interfaccia per poter accedere ai servizi di traduzione degli statement
 *
 * @see TranslationFactory
 * @see DatasetIntegrationService
 */
@Service
public class TranslationService {

    public static final Logger logger = LogManager.getLogger(TranslationService.class);
    private TranslationFactory translationFactory;
    private DatasetIntegrationService datasetIntegrationService;
    private Environment environment;


    @Autowired
    public void setTranslationFactory(TranslationFactory translationFactory) {
        this.translationFactory = translationFactory;
    }

    @Autowired
    public void setDatasetIntegrationService(DatasetIntegrationService datasetIntegrationService) {
        this.datasetIntegrationService = datasetIntegrationService;
    }

    @Autowired
    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }

    /**
     * Il metodo prendendo in ingresso una lista di statement costruisce una traduzione verso il sql richiesto.
     * Da questo metodo parte l'esplorazione dell'albero di traduzione e vengono poi restituiti gli sqlObject che contengono
     * i comandi SQL tradotti
     * <p>
     * Il flusso di elaborazione del motore di traduzione:
     * - Prende in ingresso una lista di comandi interpretati(VtlStatement) a cui è allegato il risultato della traduzione semantica,
     * - Attiva il profilo di traduzione giunto dalla richiesta
     * - Attiva i singoli nodi di traduzione tramite la classe {@link TranslationFactory} fino a costruire un albero di validazione.
     * - Ogni nodo tramite la classe TranslationFactory accede ai risultati del nodo più in profondità e restituisce il proprio risultato
     * - Ogni nodo attiva i metodi specifici offerti dai servizi di traduzione del dato {@link com.capgemini.istat.vtlcompiler.vtlsqlbuilder.service.sqlresult.ISqlResultService}
     * - I risultati vengono ordinati, raccolti e restituiti dalla classe TranslationService.
     * </p>
     *
     * @param statementList la lista di statement da tradurre
     * @param withDrop      questo flag determina se verrà fatta l'operazione di drop
     * @param profileActive questo parametro informa il motore su quale tipo di sql si è richiesta la traduzione
     * @return una lista ordinata di istruzioni SQL
     * @throws Exception Eventuali eccezioni di traduzione derivanti dalla libreria sqlObject
     */
    public LinkedList<SqlObject> translateStatements(List<VtlStatement> statementList, boolean withDrop, String profileActive) throws Exception {
        String command = null;
        LinkedList<SqlObject> sqlResults = new LinkedList<>();
        LinkedList<SqlObject> sqlRollback = new LinkedList<>();
        KeyVariables keyVariable = null;
        if (profileActive == null) {
            profileActive = environment.getActiveProfiles()[0];
        }
        if (profileActive.equalsIgnoreCase("oracleSql")) {
            keyVariable = KeyVariables.SQL_TYPE_ORACLE;
        } else if (profileActive.equalsIgnoreCase("sqlServer")) {
            keyVariable = KeyVariables.SQL_TYPE_SQL_SERVER;
        } else if (profileActive.equalsIgnoreCase("mySql")) {
            keyVariable = KeyVariables.SQL_TYPE_MYSQL;
        } else if (profileActive.equalsIgnoreCase("postgreSql")) {
            keyVariable = KeyVariables.SQL_TYPE_POSTGRES;
        } else {
            TraslationException traslationException = new TraslationException(ValidationCheck.INVALID_ACTIVE_PROFILE.getMessage());
            traslationException.setCode(ValidationCheck.INVALID_ACTIVE_PROFILE.getCode());
            traslationException.setParameters(profileActive);
            throw traslationException;
        }
        UUID requestUuid = null;
        if (!statementList.isEmpty())
            requestUuid = statementList.get(0).getRequestUuid();
        try {

            for (VtlStatement vtlStatement : statementList) {
                command = vtlStatement.getCommand();
                logger.info("--------------->    Comando in traduzione -> " + command);
                Map<KeyVariables, Object> parameter = new HashMap<>();
                parameter.put(keyVariable, true);
                SqlResult result = translationFactory.translate(vtlStatement, parameter);
                sqlResults.addAll(result.getResultList());
                logger.info("--------------->    Comando tradotto");
            }
            extractSql(sqlRollback, sqlResults);
            if (withDrop)
                sqlResults.addAll(sqlRollback);
        } catch (Exception e){
            deleteTemporaryDataset(requestUuid);
            throw e;
        }
        deleteTemporaryDataset(requestUuid);

        return sqlResults;
    }

    private void deleteTemporaryDataset(UUID requestUuid) {
        logger.info("Dataset temporanei cancellati -> " + datasetIntegrationService.deleteAllByIsPersistentEquals(false, requestUuid));
    }

    private void extractSql(LinkedList<SqlObject> sqlRollback, LinkedList<SqlObject> sqlResult) {
        List<SqlObject> sqlObjectToExtract = new ArrayList<>();
        for (SqlObject sqlObject : sqlResult) {
            if (sqlObject.toString().contains("-- reverse")) {
                sqlObjectToExtract.add(sqlObject);
                sqlRollback.addLast(
                        new CustomSql(sqlObject.toString().replaceAll("-- reverse ", ""))
                );
            }
        }
        sqlResult.removeAll(sqlObjectToExtract);
    }

}
