package com.capgemini.istat.vtlcompiler.vtlcommon.service;

import com.capgemini.istat.vtlcompiler.vtlcommon.model.define.UserFunction;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.define.VtlUserFunctionType;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.exception.FunctionExistException;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.exception.NotAFunctionException;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.statement.VtlStatement;
import com.capgemini.istat.vtlcompiler.vtlcommon.repository.UserFunctionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

/**
 * La classe offre tutte le funzionalità necessarie per la gestione dei define operator.
 *
 * @see VtlDatabaseService
 */
@Service
public class VtlUserFunctionService {
    private UserFunctionRepository userFunctionRepository;
    private VtlDatabaseService vtlDatabaseService;
    private Environment environment;

    @Autowired
    public void setUserFunctionRepository(UserFunctionRepository userFunctionRepository) {
        this.userFunctionRepository = userFunctionRepository;
    }

    @Autowired
    public void setVtlDatabaseService(VtlDatabaseService vtlDatabaseService) {
        this.vtlDatabaseService = vtlDatabaseService;
    }

    @Autowired
    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }

    /**
     * Il metodo prende in ingresso un vtlStatement contenente una user function e il comando vtl che la rappresenta
     * e la salva sul database interno.
     *
     * @param vtlStatement il VtlStatement rappresentante la funzione
     * @param content      il comando vtl inserito. verrà utilizzato per ricostruire la funzione
     * @throws NotAFunctionException  questa eccezione viene lanciata se nello statement non contiene un define statement
     * @throws FunctionExistException questa eccezione viene lanciata se la funzione esiste già
     */
    public void createVtlUserFunction(VtlStatement vtlStatement, String content) throws NotAFunctionException, FunctionExistException {
        UserFunction userFunction = new UserFunction();
        checkFunction(vtlStatement, false);
        userFunction.setFunctionName(vtlStatement.getVtlDefineFunction().getFunctionName());
        userFunction.setFunctionType(vtlStatement.getVtlDefineFunction().getFunctionType());
        userFunction.setFunctionContent(content);
        userFunction.setRequestUuid(vtlStatement.getRequestUuid());
        userFunctionRepository.save(userFunction);
    }

    /**
     * Questo metodo viene utilizzato per aggiornare il contenuto di una user funciton
     *
     * @param vtlStatement il VtlStatement che contiene la userFunction
     * @param content      il comando immesso che contiene la userFunction
     * @throws FunctionExistException questa eccezione viene lanciata se non viene trovata la funzione da modificare
     * @throws NotAFunctionException  questa eccezione viene lanciata se il comando inserito non è un define operator
     */
    public void editVtlUserFunction(VtlStatement vtlStatement, String content) throws FunctionExistException, NotAFunctionException {
        UserFunction userFunction = checkFunction(vtlStatement, true);
        if (userFunction == null)
            throw new FunctionExistException();
        userFunction.setFunctionContent(content);
        userFunctionRepository.save(userFunction);
    }

    /**
     * questa funzione si occupa di controllare se la userFunction può essere salvata nel database
     * Effettua controlli di preesistenza della funzione e si occupa di controllare che lo statement inserito si
     * riferisce ad un define operator
     *
     * @param vtlStatement il VtlStatement da controllare
     * @param inInEdit     questo valore booleano indica se si sta controllando una casistica di edit funzione o crea funzione
     * @return L'userFunction trovata(se esiste) null se non viene trovata
     * @throws NotAFunctionException  questa eccezione viene lanciata quando lo statement inserito non si riferisce a una define operator
     * @throws FunctionExistException questa eccezione viene lanciata quando lo funzione non esiste e ci troviamo in modifica oppure quando non ci troviamo in modifica e la funzione esiste giù
     */
    public UserFunction checkFunction(VtlStatement vtlStatement, boolean inInEdit) throws NotAFunctionException, FunctionExistException {
        if (vtlStatement.getVtlDefineFunction() == null)
            throw new NotAFunctionException();
        UserFunction existingUserFunction = userFunctionRepository.findByFunctionName(vtlStatement.getVtlDefineFunction().getFunctionName());
        if (existingUserFunction != null && !inInEdit) {
            throw new FunctionExistException();
        }
        return existingUserFunction;
    }

    /**
     * Questo metodo restituisce tutte le UserFunction salvate nel database
     *
     * @return la lista delle userFunction inserite
     */
    public List<UserFunction> getAllFunctions() {
        List<UserFunction> repositoryResult = userFunctionRepository.findAll();
        if (repositoryResult.isEmpty() && !isActiveProfileNoExternalDB()) {
            repositoryResult = this.vtlDatabaseService.getAllUserFunctions();
            if (!repositoryResult.isEmpty()) {
                this.saveAll(repositoryResult);
                repositoryResult = userFunctionRepository.findAll();
            }
        }
        return repositoryResult;
    }

    /**
     * Questa funzione dato un nome trova la funzione a cui si fa riferimento
     *
     * @param functionName il nome della funzione ricercata
     * @return la userFunction ricercata o null se non trova niente
     */
    public UserFunction getByName(String functionName) {
        UserFunction repositoryResult = userFunctionRepository.findByFunctionName(functionName);
        if (repositoryResult == null && !isActiveProfileNoExternalDB()) {
            repositoryResult = this.vtlDatabaseService.getUserFunctionByName(functionName);
            if (repositoryResult != null) {
                this.save(repositoryResult);
                repositoryResult = userFunctionRepository.findByFunctionName(functionName);
            }
        }
        return repositoryResult;
    }

    /**
     * Cancella la user function con il nome indicato
     *
     * @param functionName il nome della funzione da cancellare
     * @return il numero di funzioni cancellate
     */
    public Integer deleteByName(String functionName) {
        return userFunctionRepository.deleteByFunctionName(functionName);
    }

    /**
     * Questo metodo prende in ingresso un tipo di funzione   e restituisce la lista delle funzioni della tipologia
     * richiesta. La funzioni che vengono trovate sul database esterno vengono copiate nel database interno.
     *
     * @param functionType il tipo di funzione ricercato OPERATOR_FUNCTION, DATAPOINT_FUNCTION, HIERARCHICAL_FUNCTION
     * @return la lista delle funzioni ricercate o null
     */
    public List<UserFunction> getAllFunctionByType(String functionType) {
        List<UserFunction> repositoryResult = userFunctionRepository.findAllByFunctionTypeOrderByFunctionName(VtlUserFunctionType.valueOf(functionType));
        if (repositoryResult.isEmpty() && !isActiveProfileNoExternalDB()) {
            repositoryResult = this.vtlDatabaseService.getAllUserFunctionsByType(functionType);
            if (!repositoryResult.isEmpty()) {
                this.saveAll(repositoryResult);
                repositoryResult = userFunctionRepository.findAllByFunctionTypeOrderByFunctionName(VtlUserFunctionType.valueOf(functionType));
            }
        }
        return repositoryResult;
    }

    /**
     * Salva sul database interno la userFunction
     *
     * @param result l'UserFunction da salvare
     */
    public void save(UserFunction result) {
        this.userFunctionRepository.save(result);
    }

    /**
     * Salva sul database interno tutte le userFunction inviate
     *
     * @param result la lista delle userFunction da salvare
     */
    public void saveAll(List<UserFunction> result) {
        this.userFunctionRepository.saveAll(result);
    }

    /**
     * Cancella tutte le userFunction per requestUuid
     *
     * @param requestUuid il requestUuid che indica le userFunction da cancellare
     * @return il numero delle userFunction cancellate
     */
    public Integer deleteAllByRequestUuid(UUID requestUuid) {
        return userFunctionRepository.deleteAllByRequestUuid(requestUuid);
    }

    /**
     * il metodo controlla se è attiva la modalità di avvio senza un db esterno
     *
     * @return true se è abilitato il profilo noExternalDB false altrimenti
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
