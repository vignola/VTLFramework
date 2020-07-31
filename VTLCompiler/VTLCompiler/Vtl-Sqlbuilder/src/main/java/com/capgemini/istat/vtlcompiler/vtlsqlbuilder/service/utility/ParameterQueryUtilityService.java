package com.capgemini.istat.vtlcompiler.vtlsqlbuilder.service.utility;

import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.constant.*;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.VtlExpression;
import com.healthmarketscience.sqlbuilder.FunctionCall;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * la classe rappresenta un servizio di utilità per la costruzione dei sql object
 */
@Service
public class ParameterQueryUtilityService {
    /**
     * il metodo viene utilizzato per costruire l'aggiunta di parametri di una funzione
     *
     * @param functionCall la funzione generica sql
     * @param parameter    il parametro da aggiungere alla functionCall
     */
    public void addParameter(FunctionCall functionCall, VtlExpression parameter) {
        if (parameter != null) {
            switch (parameter.getType()) {
                case VtlOptional.VTL_OBJECT_TYPE:
                    //TODO da impostare il default in base alla funzione
                    functionCall.addNumericValueParam(99);
                    break;
                case VtlConstantExpression.VTL_OBJECT_TYPE:
                    VtlConstantExpression vtlConstantExpression = (VtlConstantExpression) parameter;
                    switch (vtlConstantExpression.getVtlConstant().getType()) {
                        case VtlString.VTL_OBJECT_TYPE:
                            String stringVal = (String) vtlConstantExpression.getVtlConstant().getValue();
                            functionCall.addCustomParams(stringVal);
                            break;
                        case VtlInteger.VTL_OBJECT_TYPE:
                            Integer integerVal = (Integer) vtlConstantExpression.getVtlConstant().getFormattedValue();
                            functionCall.addNumericValueParam(integerVal);
                            break;
                        case VtlDuration.VTL_OBJECT_TYPE:
                            String durationVal = Character.toString((Character) vtlConstantExpression.getVtlConstant().getValue());
                            functionCall.addCustomParams(durationVal);
                            break;
                        default:
                            throw new IllegalArgumentException("Invalid costant parameter type: " + vtlConstantExpression.getVtlConstant().getType());
                    }
                    break;
                default:
                    throw new IllegalArgumentException("Invalid parameter type: " + parameter.getType());
            }
        }
    }

    /**
     * aggiunge una lista di parametri a una functionCall
     *
     * @param functionCall la funzione sql da tradurre
     * @param parameters   la lista di parametri da aggiungere
     */
    public void addOptionalParameters(FunctionCall functionCall, List<VtlExpression> parameters) {
        if (parameters != null) {
            for (VtlExpression parameter : parameters) {
                addParameter(functionCall, parameter);
            }
        }
    }


}
