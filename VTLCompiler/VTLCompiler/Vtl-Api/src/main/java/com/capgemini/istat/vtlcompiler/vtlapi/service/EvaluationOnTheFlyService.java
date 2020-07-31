package com.capgemini.istat.vtlcompiler.vtlapi.service;

import com.capgemini.istat.vtlcompiler.vtlapi.model.DatasetRename;
import com.capgemini.istat.vtlcompiler.vtlapi.model.VtlComponentLite;
import com.capgemini.istat.vtlcompiler.vtlapi.model.VtlDatasetLite;
import com.capgemini.istat.vtlcompiler.vtlapi.model.VtlRequest;
import com.capgemini.istat.vtlcompiler.vtlapi.response.VtlCompilerResponse;
import com.capgemini.istat.vtlcompiler.vtlcommon.constant.ConstantUtility;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.dataset.VtlComponent;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.dataset.VtlDataset;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.exception.FunctionExistException;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.exception.NotAFunctionException;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.semantic.ResultExpression;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.statement.VtlStatement;
import com.capgemini.istat.vtlcompiler.vtlcommon.service.VtlDatasetService;
import com.capgemini.istat.vtlcompiler.vtlcommon.service.VtlUserFunctionService;
import com.capgemini.istat.vtlcompiler.vtlinterpreter.service.IParseTreeService;
import com.capgemini.istat.vtlcompiler.vtllexicon.LexiconService;
import com.capgemini.istat.vtlcompiler.vtlsdmxbuilder.service.writer.StructureService;
import com.capgemini.istat.vtlcompiler.vtlsemantic.service.SemanticService;
import com.healthmarketscience.sqlbuilder.SqlObject;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * La classe offre tutti i servizi necessari per l'elaborazione delle richieste on the fly.
 *
 * @see SemanticService
 * @see VtlDatasetService
 * @see com.capgemini.istat.vtlcompiler.vtlinterpreter.service.ParseTreeService
 * @see VtlUserFunctionService
 * @see LexiconService
 * @see StructureService
 */
@Service
public class EvaluationOnTheFlyService {
    private VtlDatasetService vtlDatasetService;
    private SemanticService semanticService;
    private IParseTreeService engineService;
    private VtlUserFunctionService vtlUserFunctionService;
    private LexiconService lexiconService;
    private IParseTreeService parseTreeService;
    private StructureService structureService;


    @Autowired
    public void setVtlDatasetService(VtlDatasetService vtlDatasetService) {
        this.vtlDatasetService = vtlDatasetService;
    }

    @Autowired
    public void setSemanticService(SemanticService semanticService) {
        this.semanticService = semanticService;
    }

    @Autowired
    public void setEngineService(IParseTreeService engineService) {
        this.engineService = engineService;
    }

    @Autowired
    public void setVtlUserFunctionService(VtlUserFunctionService vtlUserFunctionService) {
        this.vtlUserFunctionService = vtlUserFunctionService;
    }

    @Autowired
    public void setLexiconService(LexiconService lexiconService) {
        this.lexiconService = lexiconService;
    }

    @Autowired
    public void setParseTreeService(IParseTreeService parseTreeService) {
        this.parseTreeService = parseTreeService;
    }

    @Autowired
    public void setStructureService(StructureService structureService) {
        this.structureService = structureService;
    }

    /**
     * Il metodo  crea i dataset che sono indicati
     * nell'oggetto VtlRequest e restituisce true se le creazioni sono avvenute con successo
     * il metodo è in grado di interpretare tutte le modalità di inserimento dei dataset
     * <p>
     *
     * @param vtlRequest l'oggetto che contiene la richiesta proveniente dal frontend
     * @return true se la creazione è avvenuta con successo
     * @throws Exception In caso di errore cancella tutti i dataset creati in questa richiesta e indica come formulare la richiesta
     */
    public boolean createDatasets(VtlRequest vtlRequest) throws Exception {
        if (vtlRequest.getDatasets() != null) {
            for (VtlDatasetLite vtlDatasetLite : vtlRequest.getDatasets()) {
                vtlDatasetLite.setRequestUuid(vtlRequest.getRequestUuid());
                VtlDataset vtlDatasetCreated = null;
                if (vtlDatasetLite.getComponentsDescriptions() != null)
                    vtlDatasetCreated = vtlDatasetService.createVtlDatasetByString(convert(vtlDatasetLite), vtlDatasetLite.getComponentsDescriptions());
                else
                    vtlDatasetCreated = vtlDatasetService.createVtlDataset(convert(vtlDatasetLite));
                if (vtlDatasetCreated == null) {
                    vtlDatasetService.deleteAllByRequestUuid(vtlRequest.getRequestUuid());
                    throw new Exception("Non è possibile creare il dataset manca un campo obbligatorio. Campi obbligatori -> name dataset, name component, type component, role component");
                }
            }
        }
        return true;
    }

    /**
     * Il metodo prende in ingresso un oggetto VtlRequest e si occupa di creare tutte le funzioni che sono
     * contenute nella vtlRequest.
     *
     * @param vtlRequest l'oggetto che contiene la richiesta proveniente dal frontend
     * @throws Exception In caso di errore viene restituito il motivo per cui non è stato possibile procedere all'operazione.
     */
    public void createFunctions(VtlRequest vtlRequest) throws Exception {
        String commandInEvaluation = "";
        if (vtlRequest.getFunctions() != null) {
            try {
                List<VtlStatement> functionStatements = engineService.visitTree(vtlRequest.getFunctions());
                semanticService.validateSemantic(functionStatements, vtlRequest.getRequestUuid());
                for (VtlStatement vtlStatement : functionStatements) {
                    commandInEvaluation = vtlStatement.getCommand() + ";";
                    vtlUserFunctionService.createVtlUserFunction(vtlStatement, commandInEvaluation);
                }
            } catch (NotAFunctionException e) {
                throw new NotAFunctionException("Il comando inserito non è un define operator " + commandInEvaluation);
            } catch (FunctionExistException e) {
                throw new FunctionExistException("il nome della funzione è già stato utilizzato " + commandInEvaluation);
            }
        }
    }

    /**
     * Il metodo si occupa di organizzare il risultato della validazione semantica per le funzionalità di valutazione on the fly.
     * In base ai parametri di ingresso è in grado di restituire sia la lista dei dataset validati semanticamente
     * sia l'XML dell'sdmx risultato della validazione.
     *
     * @param semanticResults la lista degli statement validati. Quest'oggetto contiene tutti i risultati delle validazioni semantiche compresi i passaggi intermedi di validazione
     * @param vtlRequest      l'oggetto che contiene la richiesta proveniente dal frontend
     * @return il metodo può ritornare, in base alla richiesta o un oggetto contenente le validazioni semantiche o il file XML (SDMX) di validazione
     * @throws Exception
     */
    public ResponseEntity<?> validateStatement(List<LinkedList<ResultExpression>> semanticResults, VtlRequest vtlRequest) throws Exception {
        LinkedList<ResultExpression> resultSemantic = new LinkedList<>();
        for (LinkedList<ResultExpression> resultStatement : semanticResults) {
            resultSemantic.addLast(
                    resultStatement.getFirst()
            );
        }
        VtlCompilerResponse vtlCompilerResponse = new VtlCompilerResponse();
        vtlCompilerResponse.setResultSemantic(resultSemantic);
        if (vtlRequest.isXmlResult()) {
            VtlDataset lastResult = resultSemantic.getLast().getResult();
            ByteArrayOutputStream byteArrayOutputStream = structureService.writeStructureToStream(lastResult);
            Resource resource = new ByteArrayResource(byteArrayOutputStream.toByteArray());
            return ResponseEntity.ok()
                    .contentType(MediaType.parseMediaType(MediaType.APPLICATION_XML.toString()))
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + lastResult.getName() + ".xml" + "\"")
                    .body(resource);
        } else {
            return new ResponseEntity<>(vtlCompilerResponse, HttpStatus.OK);
        }
    }

    /**
     * Il metodo raccoglie tutti i risultati della traduzione e li prepara per l'output per il servizio di traduzione on
     * the fly.
     *
     * @param translatedCommands la lista dei comandi tradotti nel sql richiesto
     * @param vtlRequest         l'oggetto che contiene la richiesta proveniente dal frontend
     * @return ritorna un oggetto VtlCompilerResponse che contiene tutti i dati delle diverse fasi di valutazione dei comandi
     */
    public ResponseEntity<?> translateSQL(List<SqlObject> translatedCommands, VtlRequest vtlRequest) {
        LinkedList<String> resultSQL = new LinkedList<>();
        for (SqlObject sqlObject : translatedCommands) {
            if (sqlObject != null)
                resultSQL.add(replaceDataset(sqlObject.toString() + ConstantUtility.SEMICOLON, vtlRequest));
        }
        VtlCompilerResponse vtlCompilerResponse = new VtlCompilerResponse();
        vtlCompilerResponse.setResultSQL(resultSQL);
        return new ResponseEntity<>(vtlCompilerResponse, HttpStatus.OK);
    }

    /**
     * Il servizio prende in ingresso delle traduzioni Sql e si occupa di rimappare i nomi in base al contenuto della lista degli alias contenuti nell'oggetto VtlRequest
     *
     * @param command    la Stringa dei comandi tradotti in sql
     * @param vtlRequest l'oggetto che contiene la richiesta proveniente dal frontend
     * @return una stringa contenente i comandi sql rimappati secondo la lista degli alias
     */
    public String replaceDataset(String command, VtlRequest vtlRequest) {
        if (vtlRequest.getAliases() != null) {
            for (DatasetRename datasetRename : vtlRequest.getAliases()) {
                command = command.replaceAll(" " + "(?i)" + datasetRename.getName() + " ", " " + datasetRename.getAlias() + " ");
            }
        }
        return command;
    }

    /**
     * Il metodo prende in ingresso una lista di SqlObject contenenti le traduzioni sql, aggiunge il punto e virgola alla fine e restituisce il risultato in un oggetto VtlCompilerResponse
     *
     * @param translatedCommands la lista di oggetti contenenti i comandi tradotti in sql
     * @return il metodo ritorna una ResponseEntity contenente un oggetto VtlCompilerResponse
     */
    public ResponseEntity translateSQL(List<SqlObject> translatedCommands) {
        LinkedList<String> resultSQL = new LinkedList<>();
        for (SqlObject sqlObject : translatedCommands) {
            if (sqlObject != null)
                resultSQL.add(sqlObject.toString() + ConstantUtility.SEMICOLON);
        }
        VtlCompilerResponse vtlCompilerResponse = new VtlCompilerResponse();
        vtlCompilerResponse.setResultSQL(resultSQL);
        return new ResponseEntity<>(vtlCompilerResponse, HttpStatus.OK);
    }

    /**
     * il metodo cancella tutte le funzioni e i dataset tramite il RequestUuid contenute all'interno della VtlRequest
     *
     * @param vtlRequest l'oggetto che contiene la richiesta proveniente dal frontend
     */
    public void eraseAllByRequestId(VtlRequest vtlRequest) {
        vtlUserFunctionService.deleteAllByRequestUuid(vtlRequest.getRequestUuid());
        vtlDatasetService.deleteAllByRequestUuid(vtlRequest.getRequestUuid());
    }

    /**
     * Converte l'oggetto VtlDatasetLite(un oggetto che rappresenta il dataset per il frontend) in un oggetto VtlDataset. Non viene fatta alcuna operazione sul dataset
     *
     * @param vtlDatasetLite un oggetto che rappresenta il dataset per il frontend
     * @return VtlDataset un dataset pronto per essere interpretato dal motore semantico
     */
    private VtlDataset convert(VtlDatasetLite vtlDatasetLite) {
        VtlDataset vtlDataset = new VtlDataset();
        BeanUtils.copyProperties(vtlDatasetLite, vtlDataset);
        vtlDataset.setVtlComponents(new ArrayList<>());
        if (vtlDatasetLite.getVtlComponents() != null)
            for (VtlComponentLite vtlComponentLite : vtlDatasetLite.getVtlComponents()) {
                VtlComponent vtlComponent = new VtlComponent();
                BeanUtils.copyProperties(vtlComponentLite, vtlComponent);
                vtlDataset.addComponent(vtlComponent);
            }
        return vtlDataset;
    }


}
