package com.capgemini.istat.vtlcompiler.vtlinterpreter.service;


import com.capgemini.istat.vtlcompiler.vtlcommon.model.operator.Operator;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.dataset.VtlComponentId;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.VtlExpression;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.VtlMembership;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.booleans.VtlBooleanBinaryExpression;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.comparison.VtlComparisonBinaryExpression;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.numeric.VtlNumericBinaryExpression;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.numeric.VtlNumericUnaryExpression;
import org.springframework.stereotype.Service;

/**
 * la classe si occupa di generare alcune VTLexpression per il motore di interpretazione.
 * La classe viene utilizzata per generare alcune VtlExpression che hanno struttura identica e differiscono solo per l'operator
 * Non è presente particolare logica. Si tratta perlopiù di switch case e popolamento di bean
 */
@Service
public class ExpressionFactoryService implements IExpressionFactoryService {

    /**
     * Il metodo prende in ingresso tutti dati necessari per generare un espressione aritmetica e la restituisce
     * @param operator l'operatore matematico in ingresso
     * @param leftExpression la left expression
     * @param rightExpression la right expression
     * @return una VtlExpression che rappresenta l'operatore
     */
    @Override
    public VtlExpression arithmeticFactory(String operator, VtlExpression leftExpression, VtlExpression rightExpression) {
        VtlExpression vtlExpression;
        switch (operator) {
            case "+": {
                vtlExpression = new VtlNumericBinaryExpression(leftExpression, rightExpression, Operator.ADDITION);
                break;
            }
            case "-": {
                vtlExpression = new VtlNumericBinaryExpression(leftExpression, rightExpression, Operator.SUBSTRACTION);
                break;
            }
            case "*": {
                vtlExpression = new VtlNumericBinaryExpression(leftExpression, rightExpression, Operator.MULTIPLICATION);
                break;
            }
            case "/": {
                vtlExpression = new VtlNumericBinaryExpression(leftExpression, rightExpression, Operator.DIVISION);
                break;
            }
            case "mod": {
                vtlExpression = new VtlNumericBinaryExpression(leftExpression, rightExpression, Operator.MOD);
                break;
            }
            default:
                vtlExpression = null;
        }
        return vtlExpression;
    }

    /**
     * Il metodo prende in ingresso tutti dati necessari per generare un espressione di comparazione e la restituisce
     *
     * @param operator        l'operatore di comparazione in ingresso
     * @param leftExpression  la left expression
     * @param rightExpression la right expression
     * @return una VtlExpression che rappresenta l'operatore
     */
    public VtlExpression comparisonFactory(String operator, VtlExpression leftExpression, VtlExpression rightExpression) {
        VtlExpression vtlExpression;
        switch (operator) {
            case "=": {
                vtlExpression = new VtlComparisonBinaryExpression(leftExpression, rightExpression, Operator.EQUAL_TO);
                break;
            }
            case "<>": {
                vtlExpression = new VtlComparisonBinaryExpression(leftExpression, rightExpression, Operator.NOT_EQUAL_TO);
                break;
            }
            case ">": {
                vtlExpression = new VtlComparisonBinaryExpression(leftExpression, rightExpression, Operator.GREATER_THAN);
                break;
            }
            case ">=": {
                vtlExpression = new VtlComparisonBinaryExpression(leftExpression, rightExpression, Operator.GREATER_THAN_EQUALS);
                break;
            }
            case "<": {
                vtlExpression = new VtlComparisonBinaryExpression(leftExpression, rightExpression, Operator.LESS_THAN);
                break;
            }
            case "<=": {
                vtlExpression = new VtlComparisonBinaryExpression(leftExpression, rightExpression, Operator.LESS_THAN_EQUALS);
                break;
            }
            default:
                vtlExpression = null;
        }
        return vtlExpression;
    }

    /**
     * il metodo data un espressione e un operatore applica restituisce un espressione unaria corrispondente
     *
     * @param operator   l'operatore dell'operazione
     * @param expression l'espressione dell'operazione
     * @return una VtlExpression che rappresenta l'operatore
     */
    @Override
    public VtlExpression unaryFactory(String operator, VtlExpression expression) {
        VtlExpression vtlExpression;
        switch (operator) {
            case "-": {
                vtlExpression = new VtlNumericUnaryExpression(expression, Operator.UNARY_NEGATIVE);
                break;
            }
            case "+": {
                vtlExpression = new VtlNumericUnaryExpression(expression, Operator.UNARY_POSITIVE);
                break;
            }
            default:
                vtlExpression = null;
        }
        return vtlExpression;

    }

    /**
     * Il metodo prende in ingresso tutti dati necessari per generare un espressione booleana e la restituisce
     *
     * @param operator        l'operatore booleano in ingresso
     * @param leftExpression  la left expression
     * @param rightExpression la right expression
     * @return una VtlExpression che rappresenta l'operatore
     */
    @Override
    public VtlBooleanBinaryExpression booleanFactory(String operator, VtlExpression leftExpression, VtlExpression rightExpression) {
        VtlBooleanBinaryExpression vtlExpression;
        switch (operator) {
            case "and": {
                vtlExpression = new VtlBooleanBinaryExpression(leftExpression, rightExpression, Operator.AND);
                break;
            }
            case "or": {
                vtlExpression = new VtlBooleanBinaryExpression(leftExpression, rightExpression, Operator.OR);
                break;
            }
            case "xor": {
                vtlExpression = new VtlBooleanBinaryExpression(leftExpression, rightExpression, Operator.XOR);
                break;
            }
            default:
                vtlExpression = null;
        }
        return vtlExpression;
    }

    /**
     * il metodo dato in ingresso una VtlExpression e un componente restituisce una VtlExpression di tipo membership
     *
     * @param vtlExpression  la vtlExpression in ingresso
     * @param vtlComponentId il componente sulla quale viene effettuta la membership
     * @return una VtlExpression che rappresenta la membership.
     */
    @Override
    public VtlExpression membershipFactory(VtlExpression vtlExpression, VtlComponentId vtlComponentId) {
        VtlMembership vtlMembership = new VtlMembership();
        vtlMembership.setVtlExpression(vtlExpression);
        vtlMembership.setVtlComponentId(vtlComponentId);
        return vtlMembership;
    }


}
