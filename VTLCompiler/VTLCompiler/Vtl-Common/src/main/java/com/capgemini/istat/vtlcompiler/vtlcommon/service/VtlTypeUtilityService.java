package com.capgemini.istat.vtlcompiler.vtlcommon.service;

import com.capgemini.istat.vtlcompiler.vtlcommon.model.dataset.VtlType;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.constant.VtlConstant;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.constant.VtlConstantExpression;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * La classe VtlTypeUtilityService si occupa di fornire tutte le funzionalità necessarie per la manipolazione e la trasformazione
 * dei tipi dei componenti VTL.
 * Perlopiù questa classe viene utilizzata o ai fini di controllo per il motere semantico o per i meccanismi di cast
 */
@Service
public class VtlTypeUtilityService {

    /**
     *  il metodo controlla se due tipi sono identici. E' un semplice controllo di equals senza meccanismo di cast implicito
     * @param type il tipo da controllare
     * @param otherType l'altro tipo da confrontare
     * @return un valore booleano. True se sono uguali false altrimenti
     */
    public Boolean sameType(String type, String otherType) {
        return type.equals(otherType);
    }

    /**
     *  Questo metodo dati tue VtlType restituisce il tipo con maggiore forza nell'ottica del meccanismo di casting implicito
     *  implentato secondo requisiti di linguaggio VTL.
     *  Quindi per esempio se un tipo stringa incontra un tipo numerico il cast implicito(e il metodo) restituirà stringa.
     * @param type uno dei due tipi da confrontare
     * @param otherType il secondo tipo da confrontare
     * @return viene restituito il tipo VTL "vincitore" secondo le dinamiche di casting inmplicito
     */
    public VtlType getStrongestType(VtlType type, VtlType otherType) {
        if (type.equals(otherType) || otherType.equals(VtlType.NONE))
            return type;
        if ((type.equals(VtlType.STRING) || otherType.equals(VtlType.STRING)) && //Se uno dei due è varchar
                (type.equals(VtlType.INTEGER) || otherType.equals(VtlType.INTEGER)    //Se l'altro è intero, numero o booleano
                        || type.equals(VtlType.NUMBER) || otherType.equals(VtlType.NUMBER) ||
                        type.equals(VtlType.BOOLEAN) || otherType.equals(VtlType.BOOLEAN)))
            return VtlType.STRING; //cast implicito varchar

        if ((type.equals(VtlType.INTEGER) || otherType.equals(VtlType.INTEGER)) &&
                (type.equals(VtlType.NUMBER) || otherType.equals(VtlType.NUMBER)))  //cast implicito  integer -> number
            return VtlType.NUMBER;

        if ((type.equals(VtlType.TIME) || otherType.equals(VtlType.TIME)) &&
                (type.equals(VtlType.DATE) || otherType.equals(VtlType.DATE)
                        || type.equals(VtlType.TIME_PERIOD) || otherType.equals(VtlType.TIME_PERIOD)))
            return VtlType.TIME;

        if ((type.equals(VtlType.DURATION) || otherType.equals(VtlType.DURATION)) &&
                (type.equals(VtlType.STRING) || otherType.equals(VtlType.STRING)))
            return VtlType.DURATION;

        return null;
    }

    /**
     * il metodo confronta due VtlType  e verifica se è necessario (o possibile) un meccanismo di cast Implicito
     *
     * @param type         Il tipo in ingresso
     * @param expectedType il tipo che ci aspettiamo o con il quale vogliamo effettuare un confronto
     * @return un valore booleano. True se è necessario o possibile un cast implicito false se non necessario o non possibile
     */
    public Boolean needImplicitCast(VtlType type, VtlType expectedType) {
        if (type.equals(expectedType))
            return false;
        if ((type.equals(VtlType.STRING) || expectedType.equals(VtlType.STRING)) && //Se uno dei due è varchar
                (type.equals(VtlType.INTEGER) || expectedType.equals(VtlType.INTEGER)    //Se l'altro è intero, numero o booleano
                        || type.equals(VtlType.NUMBER) || expectedType.equals(VtlType.NUMBER) ||
                        type.equals(VtlType.BOOLEAN) || expectedType.equals(VtlType.BOOLEAN)))
            return true;

        if ((type.equals(VtlType.INTEGER) || expectedType.equals(VtlType.INTEGER)) &&
                (type.equals(VtlType.NUMBER) || expectedType.equals(VtlType.NUMBER)))
            return true;


        if ((type.equals(VtlType.TIME) || expectedType.equals(VtlType.TIME)) &&
                (type.equals(VtlType.DATE) || expectedType.equals(VtlType.DATE)
                        || type.equals(VtlType.TIME_PERIOD) || expectedType.equals(VtlType.TIME_PERIOD)))
            return true;

        //Non sono nelle dinamiche di cast possibile. dovrebbe essere stato intercettato l'errore prima
        return false;
    }

    /**
     * il metodo controlla se due tipi risultano uguali in base al meccanismo di cast(non implicito)
     *
     * @param type         il tipo da controllare
     * @param expectedType il secondo tipo da controllare
     * @return un valore booleano. Il metodo restituisce true se i tipi sono identici(tramite possibile cast)
     */
    public Boolean isEqualsWithCast(VtlType type, VtlType expectedType) {
        if (type.equals(expectedType))
            return true;
        if ((type.equals(VtlType.STRING) || expectedType.equals(VtlType.STRING)) && //Se uno dei due è varchar
                (type.equals(VtlType.INTEGER) || expectedType.equals(VtlType.INTEGER)    //Se l'altro è intero, numero o booleano
                        || type.equals(VtlType.NUMBER) || expectedType.equals(VtlType.NUMBER) ||
                        type.equals(VtlType.BOOLEAN) || expectedType.equals(VtlType.BOOLEAN) ||
                        type.equals(VtlType.DURATION) || expectedType.equals(VtlType.DURATION))
        )

            return true;

        if ((type.equals(VtlType.INTEGER) || expectedType.equals(VtlType.INTEGER)) &&
                (type.equals(VtlType.NUMBER) || expectedType.equals(VtlType.NUMBER)))
            return true;

        if ((type.equals(VtlType.TIME) || expectedType.equals(VtlType.TIME)) &&
                (type.equals(VtlType.DATE) || expectedType.equals(VtlType.DATE)
                        || type.equals(VtlType.TIME_PERIOD) || expectedType.equals(VtlType.TIME_PERIOD)))
            return true;

        //Non sono nelle dinamiche di cast possibile. dovrebbe essere stato intercettato l'errore prima
        return false;
    }

    /**
     * Il metodo controlla che tutte le costanti inserite siano dello stesso tipo.
     *
     * @param vtlConstants una lista di VtlCostantExpression
     * @return true se lec costanti sono tutte dello stesso tipo
     */
    public boolean hasSameTypeScalars(List<VtlConstantExpression> vtlConstants) {
        Boolean result = true;
        VtlConstant firstElement = vtlConstants.get(0).getVtlConstant();
        for (VtlConstantExpression vtlConstantExpression : vtlConstants) {
            result = result && firstElement.getType().equals(vtlConstantExpression.getVtlConstant().getType());
        }
        return result;
    }

    /**
     * il metodo confronta due VtlType e verifica se sono identici nel contesto del cast implicito.
     * A differenza degli altri metodi qui viene controllato il cast in una sola direzione. vtlType deve essere castabile
     * implicitamente in vtlTypeRequired e non viceversa.
     *
     * @param vtlType         il tipo da controllare
     * @param vtlTypeRequired il tipo atteso
     * @return un valore booelano True se i tipi sono identici o lo diventano secondo la dinamica di cast implicito
     */
    public boolean isTypeCompatible(VtlType vtlType, VtlType vtlTypeRequired) {
        if (vtlType == vtlTypeRequired)
            return true;

        if (vtlTypeRequired == VtlType.NUMBER) {
            return vtlType == VtlType.INTEGER;
        }

        if (vtlTypeRequired == VtlType.STRING) {
            return vtlType == VtlType.NUMBER || vtlType == VtlType.INTEGER;
        }

        if (vtlTypeRequired == VtlType.TIME) {
            return vtlType == VtlType.DATE || vtlType == VtlType.TIME_PERIOD;
        }

        if (vtlTypeRequired == VtlType.STRING) {
            return vtlType == VtlType.INTEGER || vtlType == VtlType.NUMBER
                    || vtlType == VtlType.BOOLEAN;
        }
        return false;
    }

    /**
     * il metodo controlla due tipi  e verifica se il cast è possibile. Il metodo verifica il vtlType con il vtlTypeExpected
     *
     * @param vtlType         il tipo da controllare
     * @param vtlTypeExpected il tipo atteso per il cast
     * @return true se il cast è possibile false altrimenti. Lo schema è quello offerto dal manuale VTL
     */
    public boolean isCastable(VtlType vtlType, VtlType vtlTypeExpected) {
        if (vtlType == vtlTypeExpected)
            return true;
        if (vtlType == VtlType.INTEGER) {
            return vtlTypeExpected == VtlType.NUMBER || vtlTypeExpected == VtlType.BOOLEAN
                    || vtlTypeExpected == VtlType.STRING;
        }
        if (vtlType == VtlType.NUMBER) {
            return vtlTypeExpected == VtlType.INTEGER || vtlTypeExpected == VtlType.BOOLEAN
                    || vtlTypeExpected == VtlType.STRING;
        }
        if (vtlType == VtlType.BOOLEAN) {
            return vtlTypeExpected == VtlType.INTEGER || vtlTypeExpected == VtlType.NUMBER || vtlTypeExpected == VtlType.STRING;
        }
        if (vtlType == VtlType.TIME) {
            return vtlTypeExpected == VtlType.STRING;
        }
        if (vtlType == VtlType.DATE) {
            return vtlTypeExpected == VtlType.TIME || vtlTypeExpected == VtlType.TIME_PERIOD || vtlTypeExpected == VtlType.STRING;
        }
        if (vtlType == VtlType.TIME_PERIOD) {
            return vtlTypeExpected == VtlType.TIME || vtlTypeExpected == VtlType.DATE || vtlTypeExpected == VtlType.STRING;
        }
        if (vtlType == VtlType.STRING) {
            return vtlTypeExpected == VtlType.INTEGER || vtlTypeExpected == VtlType.NUMBER
                    || vtlTypeExpected == VtlType.TIME
                    || vtlTypeExpected == VtlType.DATE || vtlTypeExpected == VtlType.TIME_PERIOD
                    || vtlTypeExpected == VtlType.DURATION;
        }
        if (vtlType == VtlType.DURATION) {
            return vtlTypeExpected == VtlType.STRING;
        }
        return false;
    }

    /**
     * il metodo verifica che il tipo in ingresso appartiene al dominio del tipo time
     *
     * @param type il tipo da controllare
     * @return il metodo restituisce true se il tipo inserito è date, duration time o timePeriod
     */
    public Boolean isDateTimeComponent(VtlType type) {
        return type.equals(VtlType.DATE) || type.equals(VtlType.DURATION) || type.equals(VtlType.TIME) || type.equals(VtlType.TIME_PERIOD);
    }
}
