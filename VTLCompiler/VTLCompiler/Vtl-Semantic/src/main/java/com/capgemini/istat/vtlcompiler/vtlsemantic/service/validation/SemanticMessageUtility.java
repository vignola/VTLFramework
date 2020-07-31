package com.capgemini.istat.vtlcompiler.vtlsemantic.service.validation;

import com.capgemini.istat.vtlcompiler.vtlcommon.model.exception.ValidationException;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.semantic.ResultExpression;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.semantic.SemanticMessage;

import java.util.List;

/**
 * la classe una serie di metodi utili per processare i messaggi di errori di validazione
 */
public class SemanticMessageUtility {

    private SemanticMessageUtility() {
        throw new IllegalStateException("Utility class");
    }

    /**
     * Il metodo verifica in una ResultExpression la presenza di messaggi di errori e lancia un eccezione di validazione
     * popolata con i dati specifici dell'errore riscontrato
     *
     * @param resultExpression un risultato del motore semantico
     * @throws ValidationException l'errore di validazione riscontrato
     */
    public static void processSemanticMessage(ResultExpression resultExpression) throws ValidationException {
        for (SemanticMessage semanticMessage : resultExpression.getMessages()) {
            if (semanticMessage.getBlockingFault()) {
                ValidationException validationException = new ValidationException(semanticMessage.toString());
                validationException.setSemanticMessage(semanticMessage);
                validationException.setLiteCommand(resultExpression.getCommand());
                throw validationException;
            }
        }
    }

    /**
     * il metodo verifica se esistono errori di validazione bloccanti nei messaggi di errore passati
     *
     * @param semanticMessages una lista di messaggi di errore
     * @return true se sono presenti errori bloccanti. Un errore bloccante termina l'esecuzione della validazione
     */
    public static Boolean existBlockingMessage(List<SemanticMessage> semanticMessages) {
        for (SemanticMessage semanticMessage : semanticMessages) {
            if (semanticMessage.getBlockingFault()) {
                return true;
            }
        }
        return false;
    }

    /**
     * il metodo costruisce un messaggio di errore validazione
     *
     * @param code       il codice di errore
     * @param message    il messaggio di errore
     * @param parameters i parametri dinamici per popolare i messaggi di errore
     * @return un messaggio di errore
     */
    public static SemanticMessage setUpBlockingMessage(String code, String message, String... parameters) {
        SemanticMessage semanticMessage = new SemanticMessage();
        semanticMessage.setCode(code);
        semanticMessage.setMessage(message);
        semanticMessage.setParameters(parameters);
        semanticMessage.setErrorLevel(SemanticMessage.ErrorLevel.GRAVE);
        semanticMessage.setBlockingFault(true);
        return semanticMessage;
    }

}
