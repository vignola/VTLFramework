package com.capgemini.istat.vtlcompiler.vtlsemantic.service.validation;

import com.capgemini.istat.vtlcompiler.vtlcommon.model.dataset.VtlComponent;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.semantic.SemanticMessage;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.semantic.ValidationCheck;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.semantic.ValidationData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * la classe rappresenta il core di validazione per i component. Il motore di validazione semantica invoca questa classe
 * per verificare la presenza di errori.
 *
 * @see CheckComponentService
 */
@Service
public class ComponentSemanticValidationService {

    private CheckComponentService checkComponentService;


    @Autowired
    public void setCheckComponentService(CheckComponentService checkComponentService) {
        this.checkComponentService = checkComponentService;
    }


    /**
     * il metodo prende in ingresso un oggetto che contiene tutti i dati da validare e in base alla lista di validazioni
     * da effettuare attiva le diverse parti del motore.
     * Se qualche validazione non viene soddisfatta viene costruito un messaggio di errore ad hoc.
     * la lista di validazioni disponibili sono visibili nella classe ValidationCheck
     *
     * @param validationData un oggetto contenente tutti i dati da validare. E' popolato dei soli dati necessari alla validazione
     * @return una lista di messaggi di errore o una lista vuota se non ce ne sono
     */
    public List<SemanticMessage> validateSemantic(ValidationData validationData) {
        String[] parameters;
        List<SemanticMessage> semanticMessages = new ArrayList<>();
        for (ValidationCheck validationCheck : validationData.getValidationChecks()) {
            switch (validationCheck) {
                case IS_NUMBER_OR_INTEGER_TYPE: {
                    SemanticMessage semanticMessage;
                    for (VtlComponent vtlComponent : validationData.getVtlComponents()) {
                        if (!checkComponentService.isNumberOrIntegerType(vtlComponent)) {
                            parameters = new String[1];
                            parameters[0] = vtlComponent.getName();
                            semanticMessage = SemanticMessageUtility.setUpBlockingMessage(ValidationCheck.IS_NUMBER_OR_INTEGER_TYPE.getCode(),
                                    ValidationCheck.IS_NUMBER_OR_INTEGER_TYPE.getMessage(), parameters);
                            semanticMessages.add(semanticMessage);
                        }
                    }
                    break;
                }
                case IS_INTEGER: {
                    SemanticMessage semanticMessage;
                    for (VtlComponent vtlComponent : validationData.getVtlComponents()) {
                        if (!checkComponentService.isIntegerType(vtlComponent)) {
                            parameters = new String[1];
                            parameters[0] = vtlComponent.getName();
                            semanticMessage = SemanticMessageUtility.setUpBlockingMessage(ValidationCheck.IS_INTEGER.getCode(),
                                    ValidationCheck.IS_INTEGER.getMessage(), parameters);
                            semanticMessages.add(semanticMessage);
                        }
                    }
                    break;
                }
                case IS_NUMBER: {
                    SemanticMessage semanticMessage;
                    for (VtlComponent vtlComponent : validationData.getVtlComponents()) {
                        if (!checkComponentService.isNumberType(vtlComponent)) {
                            parameters = new String[1];
                            parameters[0] = vtlComponent.getName();
                            semanticMessage = SemanticMessageUtility.setUpBlockingMessage(ValidationCheck.IS_NUMBER.getCode(),
                                    ValidationCheck.IS_NUMBER.getMessage(), parameters);
                            semanticMessages.add(semanticMessage);
                        }
                    }
                    break;
                }
                case IS_BOOLEAN: {
                    SemanticMessage semanticMessage;
                    for (VtlComponent vtlComponent : validationData.getVtlComponents()) {
                        if (!checkComponentService.isBooleanType(vtlComponent)) {
                            parameters = new String[1];
                            parameters[0] = vtlComponent.getName();
                            semanticMessage = SemanticMessageUtility.setUpBlockingMessage(ValidationCheck.IS_BOOLEAN.getCode(),
                                    ValidationCheck.IS_BOOLEAN.getMessage(), parameters);
                            semanticMessages.add(semanticMessage);
                        }
                    }
                    break;
                }
                case IS_IMPLICIT_CASTABLE_TO_STRING: {
                    SemanticMessage semanticMessage;
                    for (VtlComponent vtlComponent : validationData.getVtlComponents()) {
                        if (!checkComponentService.isImplictablyCastableToString(vtlComponent)) {
                            parameters = new String[1];
                            parameters[0] = vtlComponent.getName();
                            semanticMessage = SemanticMessageUtility.setUpBlockingMessage(ValidationCheck.IS_IMPLICIT_CASTABLE_TO_STRING.getCode(),
                                    ValidationCheck.IS_IMPLICIT_CASTABLE_TO_STRING.getMessage(), parameters);
                            semanticMessages.add(semanticMessage);
                        }
                    }
                    break;
                }
                case IS_SAME_TYPE_MEASURES: {
                    SemanticMessage semanticMessage;
                    if (validationData.getVtlComponents() != null && validationData.getVtlComponents().size() > 1) {
                        if (!checkComponentService.isSameTypeComponents(validationData.getVtlComponents().get(0), validationData.getVtlComponents().get(1))) {
                            parameters = new String[1];
                            parameters[0] = validationData.getVtlComponents().get(1).getName();
                            semanticMessage = SemanticMessageUtility.setUpBlockingMessage(ValidationCheck.IS_SAME_TYPE_MEASURES.getCode(),
                                    ValidationCheck.IS_SAME_TYPE_MEASURES.getMessage(), parameters);
                            semanticMessages.add(semanticMessage);
                        }
                    } else {
                        parameters = new String[1];
                        parameters[0] = validationData.getComponentName();
                        semanticMessage = SemanticMessageUtility.setUpBlockingMessage(ValidationCheck.TECHNICAL_ERROR.getCode(),
                                ValidationCheck.TECHNICAL_ERROR.getMessage(), parameters);
                        semanticMessages.add(semanticMessage);
                    }
                    break;
                }
                case IS_IDENTICAL_TYPE: {
                    SemanticMessage semanticMessage;
                    if (validationData.getVtlComponents() != null && validationData.getVtlComponents().size() > 1) {
                        VtlComponent vtlComponentSample = validationData.getVtlComponents().get(0);
                        for (VtlComponent vtlComponent : validationData.getVtlComponents()) {
                            if (!checkComponentService.isIdenticalType(vtlComponentSample, vtlComponent)) {
                                parameters = new String[1];
                                parameters[0] = vtlComponent.getName();
                                semanticMessage = SemanticMessageUtility.setUpBlockingMessage(ValidationCheck.IS_IDENTICAL_TYPE.getCode(),
                                        ValidationCheck.IS_IDENTICAL_TYPE.getMessage(), parameters);
                                semanticMessages.add(semanticMessage);
                            }
                        }
                    } else {
                        parameters = new String[1];
                        parameters[0] = validationData.getComponentName();
                        semanticMessage = SemanticMessageUtility.setUpBlockingMessage(ValidationCheck.TECHNICAL_ERROR.getCode(),
                                ValidationCheck.TECHNICAL_ERROR.getMessage(), parameters);
                        semanticMessages.add(semanticMessage);
                    }
                    break;
                }
                case IS_TIME: {
                    SemanticMessage semanticMessage;
                    for (VtlComponent vtlComponent : validationData.getVtlComponents()) {
                        if (!checkComponentService.isTime(vtlComponent)) {
                            parameters = new String[1];
                            parameters[0] = vtlComponent.getName();
                            semanticMessage = SemanticMessageUtility.setUpBlockingMessage(ValidationCheck.IS_TIME.getCode(),
                                    ValidationCheck.IS_TIME.getMessage(), parameters);
                            semanticMessages.add(semanticMessage);
                        }
                    }
                    break;
                }
                case IS_TIME_PERIOD: {
                    SemanticMessage semanticMessage;
                    for (VtlComponent vtlComponent : validationData.getVtlComponents()) {
                        if (!checkComponentService.isTimePeriod(vtlComponent)) {
                            parameters = new String[1];
                            parameters[0] = vtlComponent.getName();
                            semanticMessage = SemanticMessageUtility.setUpBlockingMessage(ValidationCheck.IS_TIME_PERIOD.getCode(),
                                    ValidationCheck.IS_TIME_PERIOD.getMessage(), parameters);
                            semanticMessages.add(semanticMessage);
                        }
                    }
                    break;
                }
                case NOT_ALLOWED_COMPONENT: {
                    SemanticMessage semanticMessage;
                    semanticMessage = SemanticMessageUtility.setUpBlockingMessage(ValidationCheck.NOT_ALLOWED_COMPONENT.getCode(),
                            ValidationCheck.NOT_ALLOWED_COMPONENT.getMessage());
                    semanticMessages.add(semanticMessage);
                    break;
                }
                case HAS_TYPE_AND_ROLE: {
                    SemanticMessage semanticMessage;
                    boolean check;
                    check = checkComponentService.hasTypeAndRole(validationData.getVtlComponents().get(0), validationData.getVtlComponentRole(), validationData.getVtlType());

                    if (!check) {
                        parameters = new String[2];
                        parameters[0] = validationData.getVtlComponentRole().toString();
                        parameters[1] = validationData.getVtlType().toString();
                        semanticMessage = SemanticMessageUtility.setUpBlockingMessage(ValidationCheck.HAS_TYPE_AND_ROLE.getCode(),
                                ValidationCheck.HAS_TYPE_AND_ROLE.getMessage(), parameters);
                        semanticMessages.add(semanticMessage);
                    }
                    break;
                }
                case HAS_VALUE_DOMAIN_AND_ROLE: {
                    SemanticMessage semanticMessage;
                    boolean check;
                    check = checkComponentService.hasValueDomainAndRole(validationData.getVtlComponents().get(0), validationData.getVtlComponentRole(), validationData.getValueDomain());

                    if (!check) {
                        parameters = new String[2];
                        parameters[0] = validationData.getVtlComponentRole().toString();
                        parameters[1] = validationData.getValueDomain();
                        semanticMessage = SemanticMessageUtility.setUpBlockingMessage(ValidationCheck.HAS_VALUE_DOMAIN_AND_ROLE.getCode(),
                                ValidationCheck.HAS_VALUE_DOMAIN_AND_ROLE.getMessage(), parameters);
                        semanticMessages.add(semanticMessage);
                    }
                    break;
                }
                default: {
                    parameters = new String[1];
                    parameters[0] = validationCheck.toString();
                    SemanticMessage semanticMessage = SemanticMessageUtility.setUpBlockingMessage(ValidationCheck.NO_RULES.getCode(),
                            ValidationCheck.NO_RULES.getMessage(), parameters);
                    semanticMessages.add(semanticMessage);
                }
            }
        }
        return semanticMessages;
    }
}
