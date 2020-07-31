package com.capgemini.istat.vtlcompiler.vtlsemantic.service.validation;

import com.capgemini.istat.vtlcompiler.vtlcommon.model.dataset.VtlComponent;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.dataset.VtlComponentRole;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.dataset.VtlDataset;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.semantic.SemanticMessage;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.semantic.ValidationCheck;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.semantic.ValidationData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * la classe rappresenta il core di validazione per i dataset. Il motore di validazione semantica invoca questa classe
 * per verificare la presenza di errori
 *
 * @see CheckDatasetService
 */
@Service
public class DatasetSemanticValidationService {
    private CheckDatasetService checkDatasetService;


    @Autowired
    public void setCheckDatasetService(CheckDatasetService checkDatasetService) {
        this.checkDatasetService = checkDatasetService;
    }


    /**
     * il metodo prende in ingresso un oggetto che contiene tutti i dati da validare e in base alla lista di validazioni da effettuare
     * attiva le singole validazioni
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
                case IS_SUPERSET: {
                    SemanticMessage semanticMessage;
                    if (!checkDatasetService.isSuperset(validationData.getVtlDatasets().get(0),
                            validationData.getVtlDatasets().get(1))) {
                        parameters = new String[2];
                        parameters[0] = validationData.getVtlDatasets().get(0).getName();
                        parameters[1] = validationData.getVtlDatasets().get(1).getName();
                        semanticMessage = SemanticMessageUtility.setUpBlockingMessage(ValidationCheck.IS_SUPERSET.getCode(),
                                ValidationCheck.IS_SUPERSET.getMessage(), parameters);
                        semanticMessages.add(semanticMessage);
                    }
                    break;
                }
                case HAS_COMMON_MEASURE: {
                    SemanticMessage semanticMessage;
                    if (!checkDatasetService.hasCommonMeasure(validationData.getVtlDatasets().get(0),
                            validationData.getVtlDatasets().get(1))) {
                        parameters = new String[2];
                        parameters[0] = validationData.getVtlDatasets().get(0).getName();
                        parameters[1] = validationData.getVtlDatasets().get(1).getName();
                        semanticMessage = SemanticMessageUtility.setUpBlockingMessage(ValidationCheck.HAS_COMMON_MEASURE.getCode(),
                                ValidationCheck.HAS_COMMON_MEASURE.getMessage(), parameters);
                        semanticMessages.add(semanticMessage);
                    }
                    break;
                }
                case HAS_ONLY_MEASURE_NUMBER: {
                    SemanticMessage semanticMessage;
                    if (!checkDatasetService.hasMeasureOnlyNumber(validationData.getVtlDatasets().get(0))) {
                        parameters = new String[1];
                        parameters[0] = validationData.getVtlDatasets().get(0).getName();
                        semanticMessage = SemanticMessageUtility.setUpBlockingMessage(ValidationCheck.HAS_ONLY_MEASURE_NUMBER.getCode(),
                                ValidationCheck.HAS_ONLY_MEASURE_NUMBER.getMessage(), parameters);
                        semanticMessages.add(semanticMessage);
                    }
                    break;
                }
                case HAS_AT_LEAST_ONE_IDENTIFIER: {
                    SemanticMessage semanticMessage;
                    if (!checkDatasetService.hasAtLeastOneIdentifier(validationData.getVtlDatasets().get(0))) {
                        parameters = new String[1];
                        parameters[0] = validationData.getVtlDatasets().get(0).getName();
                        semanticMessage = SemanticMessageUtility.setUpBlockingMessage(ValidationCheck.HAS_AT_LEAST_ONE_IDENTIFIER.getCode(),
                                ValidationCheck.HAS_AT_LEAST_ONE_IDENTIFIER.getMessage(), parameters);
                        semanticMessages.add(semanticMessage);
                    }
                    break;
                }
                case HAS_ONLY_ONE_MEASURE: {
                    SemanticMessage semanticMessage;
                    if (!checkDatasetService.hasOnlyOneMisure(validationData.getVtlDatasets().get(0))) {
                        parameters = new String[1];
                        parameters[0] = validationData.getVtlDatasets().get(0).getName();
                        semanticMessage = SemanticMessageUtility.setUpBlockingMessage(ValidationCheck.HAS_ONLY_ONE_MEASURE.getCode(),
                                ValidationCheck.HAS_ONLY_ONE_MEASURE.getMessage(), parameters);
                        semanticMessages.add(semanticMessage);
                    }
                    break;
                }
                case HAS_ONLY_MEASURE_INTEGER_OR_NUMBER: {
                    for (VtlDataset vtlDataset : validationData.getVtlDatasets()) {
                        if (!checkDatasetService.hasMeasureNumberOrInteger(vtlDataset)) {
                            parameters = new String[1];
                            parameters[0] = vtlDataset.getName();
                            SemanticMessage semanticMessage = SemanticMessageUtility.setUpBlockingMessage(ValidationCheck.HAS_ONLY_MEASURE_INTEGER_OR_NUMBER.getCode(),
                                    ValidationCheck.HAS_ONLY_MEASURE_INTEGER_OR_NUMBER.getMessage(), parameters);
                            semanticMessages.add(semanticMessage);
                        }
                    }
                    break;
                }
                case HAS_ONLY_MEASURE_INTEGER: {
                    for (VtlDataset vtlDataset : validationData.getVtlDatasets()) {
                        if (!checkDatasetService.hasOnlyMeasureInteger(vtlDataset)) {
                            parameters = new String[1];
                            parameters[0] = vtlDataset.getName();
                            SemanticMessage semanticMessage = SemanticMessageUtility.setUpBlockingMessage(ValidationCheck.HAS_ONLY_MEASURE_INTEGER.getCode(),
                                    ValidationCheck.HAS_ONLY_MEASURE_INTEGER.getMessage(), parameters);
                            semanticMessages.add(semanticMessage);
                        }
                    }
                    break;
                }
                case HAS_ONLY_MEASURE_BOOLEAN: {
                    for (VtlDataset vtlDataset : validationData.getVtlDatasets()) {
                        if (!checkDatasetService.hasOnlyMeasureBoolean(vtlDataset)) {
                            parameters = new String[1];
                            parameters[0] = vtlDataset.getName();
                            SemanticMessage semanticMessage = SemanticMessageUtility.setUpBlockingMessage(ValidationCheck.HAS_ONLY_MEASURE_BOOLEAN.getCode(),
                                    ValidationCheck.HAS_ONLY_MEASURE_BOOLEAN.getMessage(), parameters);
                            semanticMessages.add(semanticMessage);
                        }
                    }
                    break;
                }
                case COMPONENT_EXISTS: {
                    SemanticMessage semanticMessage;
                    if (!checkDatasetService.existComponentWithName(validationData.getVtlDatasets().get(0),
                            validationData.getComponentName(), validationData.isIgnoreCase())) {
                        parameters = new String[2];
                        parameters[0] = validationData.getVtlDatasets().get(0).getName();
                        parameters[1] = validationData.getComponentName();
                        semanticMessage = SemanticMessageUtility.setUpBlockingMessage(ValidationCheck.COMPONENT_EXISTS.getCode(),
                                ValidationCheck.COMPONENT_EXISTS.getMessage(), parameters);
                        semanticMessages.add(semanticMessage);
                    }
                    break;
                }
                case COMPONENT_NOT_EXISTS: {
                    SemanticMessage semanticMessage;
                    if (checkDatasetService.existComponentWithName(validationData.getVtlDatasets().get(0),
                            validationData.getComponentName(), validationData.isIgnoreCase())) {
                        parameters = new String[2];
                        parameters[0] = validationData.getVtlDatasets().get(0).getName();
                        parameters[1] = validationData.getComponentName();
                        semanticMessage = SemanticMessageUtility.setUpBlockingMessage(ValidationCheck.COMPONENT_EXISTS.getCode(),
                                ValidationCheck.COMPONENT_NOT_EXISTS.getMessage(), parameters);
                        semanticMessages.add(semanticMessage);
                    }
                    break;
                }
                case HAS_DATASET_THIS_NAME: {
                    SemanticMessage semanticMessage;
                    if (!checkDatasetService.hasDatasetThisName(validationData.getVtlDatasets().get(0),
                            validationData.getDatasetName())) {
                        parameters = new String[2];
                        parameters[0] = validationData.getDatasetName();
                        parameters[1] = validationData.getComponentName();
                        semanticMessage = SemanticMessageUtility.setUpBlockingMessage(ValidationCheck.HAS_DATASET_THIS_NAME.getCode(),
                                ValidationCheck.HAS_DATASET_THIS_NAME.getMessage(), parameters);
                        semanticMessages.add(semanticMessage);
                    }
                    break;
                }
                case DATASET_EXIST: {
                    SemanticMessage semanticMessage;
                    if (!checkDatasetService.existDatasetWithName(validationData.getDatasetName(), validationData.isIgnoreCase(), validationData.getRequestUuid())) {
                        parameters = new String[1];
                        parameters[0] = validationData.getDatasetName();
                        semanticMessage = SemanticMessageUtility.setUpBlockingMessage(ValidationCheck.DATASET_EXIST.getCode(),
                                ValidationCheck.DATASET_EXIST.getMessage(), parameters);
                        semanticMessages.add(semanticMessage);
                    }
                    break;
                }
                case HAS_MEASURES_IMPLICITLY_CASTABLE_TO_STRING: {
                    for (VtlDataset vtlDataset : validationData.getVtlDatasets()) {
                        if (!checkDatasetService.hasMeasureCastableToString(vtlDataset)) {
                            parameters = new String[1];
                            parameters[0] = vtlDataset.getName();
                            SemanticMessage semanticMessage = SemanticMessageUtility.setUpBlockingMessage(ValidationCheck.HAS_MEASURES_IMPLICITLY_CASTABLE_TO_STRING.getCode(),
                                    ValidationCheck.HAS_MEASURES_IMPLICITLY_CASTABLE_TO_STRING.getMessage(), parameters);
                            semanticMessages.add(semanticMessage);
                        }
                    }
                    break;
                }
                case HAS_ONE_MEASURE_OF_SAME_TYPE: {
                    SemanticMessage semanticMessage;
                    if (!checkDatasetService.has_one_measure_of_same_type(validationData.getVtlDatasets().get(0), validationData.getVtlDatasets().get(1))) {
                        parameters = new String[2];
                        parameters[0] = validationData.getVtlDatasets().get(0).getName();
                        parameters[1] = validationData.getVtlDatasets().get(1).getName();
                        semanticMessage = SemanticMessageUtility.setUpBlockingMessage(ValidationCheck.HAS_ONE_MEASURE_OF_SAME_TYPE.getCode(),
                                ValidationCheck.HAS_ONE_MEASURE_OF_SAME_TYPE.getMessage(), parameters);
                        semanticMessages.add(semanticMessage);
                    }
                    break;
                }
                case NOT_ALLOWED_SCALAR_TO_SCALAR: {
                    SemanticMessage semanticMessage;
                    semanticMessage = SemanticMessageUtility.setUpBlockingMessage(ValidationCheck.NOT_ALLOWED_SCALAR_TO_SCALAR.getCode(), ValidationCheck.NOT_ALLOWED_SCALAR_TO_SCALAR.getMessage());
                    semanticMessages.add(semanticMessage);
                    break;
                }
                case NOT_ALLOWED_SCALAR_TO_DATASET: {
                    SemanticMessage semanticMessage;
                    semanticMessage = SemanticMessageUtility.setUpBlockingMessage(ValidationCheck.NOT_ALLOWED_SCALAR_TO_DATASET.getCode(), ValidationCheck.NOT_ALLOWED_SCALAR_TO_DATASET.getMessage());
                    semanticMessages.add(semanticMessage);
                    break;
                }
                case HAS_SAME_STRUCTURE: {
                    SemanticMessage semanticMessage;
                    if (!checkDatasetService.hasSameStructure(validationData.getVtlDatasets().get(0), validationData.getVtlDatasets().get(1))) {
                        parameters = new String[2];
                        parameters[0] = validationData.getVtlDatasets().get(0).getName();
                        parameters[1] = validationData.getVtlDatasets().get(1).getName();
                        semanticMessage = SemanticMessageUtility.setUpBlockingMessage(ValidationCheck.HAS_SAME_STRUCTURE.getCode(),
                                ValidationCheck.HAS_SAME_STRUCTURE.getMessage(), parameters);
                        semanticMessages.add(semanticMessage);
                    }
                    break;
                }
                case HAS_AT_LEAST_ONE_IDENTIFIER_TIME_PERIOD: {
                    SemanticMessage semanticMessage;
                    if (!checkDatasetService.hasOneIdentifierTimePeriod(validationData.getVtlDatasets().get(0))) {
                        parameters = new String[1];
                        parameters[0] = validationData.getVtlDatasets().get(0).getName();
                        semanticMessage = SemanticMessageUtility.setUpBlockingMessage(ValidationCheck.HAS_AT_LEAST_ONE_IDENTIFIER_TIME_PERIOD.getCode(),
                                ValidationCheck.HAS_AT_LEAST_ONE_IDENTIFIER_TIME_PERIOD.getMessage(), parameters);
                        semanticMessages.add(semanticMessage);
                    }
                    break;
                }
                case HAS_ONLY_ONE_IDENTIFIER_TIME_PERIOD: {
                    SemanticMessage semanticMessage;
                    if (!checkDatasetService.hasOnlyOneIdentifierTimePeriod(validationData.getVtlDatasets().get(0))) {
                        parameters = new String[1];
                        parameters[0] = validationData.getVtlDatasets().get(0).getName();
                        semanticMessage = SemanticMessageUtility.setUpBlockingMessage(ValidationCheck.HAS_ONLY_ONE_IDENTIFIER_TIME_PERIOD.getCode(),
                                ValidationCheck.HAS_ONLY_ONE_IDENTIFIER_TIME_PERIOD.getMessage(), parameters);
                        semanticMessages.add(semanticMessage);
                    }
                    break;
                }
                case HAS_AT_LEAST_ONE_IDENTIFIER_TIME: {
                    SemanticMessage semanticMessage;
                    if (!checkDatasetService.hasOneIdentifierTime(validationData.getVtlDatasets().get(0))) {
                        parameters = new String[1];
                        parameters[0] = validationData.getVtlDatasets().get(0).getName();
                        semanticMessage = SemanticMessageUtility.setUpBlockingMessage(ValidationCheck.HAS_AT_LEAST_ONE_IDENTIFIER_TIME.getCode(),
                                ValidationCheck.HAS_AT_LEAST_ONE_IDENTIFIER_TIME.getMessage(), parameters);
                        semanticMessages.add(semanticMessage);
                    }
                    break;
                }
                case HAS_ONLY_ONE_IDENTIFIER_TIME: {
                    SemanticMessage semanticMessage;
                    if (!checkDatasetService.hasOnlyOneIdentifierTime(validationData.getVtlDatasets().get(0))) {
                        parameters = new String[1];
                        parameters[0] = validationData.getVtlDatasets().get(0).getName();
                        semanticMessage = SemanticMessageUtility.setUpBlockingMessage(ValidationCheck.HAS_ONLY_ONE_IDENTIFIER_TIME.getCode(),
                                ValidationCheck.HAS_ONLY_ONE_IDENTIFIER_TIME.getMessage(), parameters);
                        semanticMessages.add(semanticMessage);
                    }
                    break;
                }
                case HAS_ONLY_IDENTIFIER: {
                    SemanticMessage semanticMessage;
                    if (!checkDatasetService.isIdentifier(validationData.getVtlDatasets().get(0), validationData.getComponentName())) {
                        parameters = new String[2];
                        parameters[0] = validationData.getVtlDatasets().get(0).getName();
                        parameters[1] = validationData.getComponentName();
                        semanticMessage = SemanticMessageUtility.setUpBlockingMessage(ValidationCheck.HAS_ONLY_IDENTIFIER.getCode(),
                                ValidationCheck.HAS_ONLY_IDENTIFIER.getMessage(), parameters);
                        semanticMessages.add(semanticMessage);
                    }
                    break;
                }
                case ARE_NOT_IDENTIFIERS: {
                    SemanticMessage semanticMessage;
                    for (VtlComponent vtlComponent : validationData.getVtlComponents()) {
                        if (checkDatasetService.isIdentifier(validationData.getVtlDatasets().get(0), vtlComponent.getName())) {
                            parameters = new String[1];
                            parameters[0] = vtlComponent.getName();
                            semanticMessage = SemanticMessageUtility.setUpBlockingMessage(ValidationCheck.ARE_NOT_IDENTIFIERS.getCode(),
                                    ValidationCheck.ARE_NOT_IDENTIFIERS.getMessage(), parameters);
                            semanticMessages.add(semanticMessage);
                        }
                    }
                    break;
                }
                case ARE_ALL_IDENTIFIERS: {
                    SemanticMessage semanticMessage;
                    for (VtlComponent vtlComponent : validationData.getVtlComponents()) {
                        if (!checkDatasetService.isIdentifier(validationData.getVtlDatasets().get(0), vtlComponent.getName())) {
                            parameters = new String[1];
                            parameters[0] = vtlComponent.getName();
                            semanticMessage = SemanticMessageUtility.setUpBlockingMessage(ValidationCheck.ARE_ALL_IDENTIFIERS.getCode(),
                                    ValidationCheck.ARE_ALL_IDENTIFIERS.getMessage(), parameters);
                            semanticMessages.add(semanticMessage);
                        }
                    }
                    break;
                }
                case HAS_ONLY_ONE_BY_ROLE: {
                    SemanticMessage semanticMessage;
                    boolean check = true;
                    if (validationData.getVtlComponentRole() == VtlComponentRole.IDENTIFIER) {
                        check = check && checkDatasetService.hasOnlyOneIdentifier(validationData.getVtlDatasets().get(0));
                    }
                    if (validationData.getVtlComponentRole() == VtlComponentRole.MEASURE) {
                        check = check && checkDatasetService.hasOnlyOneMisure(validationData.getVtlDatasets().get(0));
                    }
                    if (validationData.getVtlComponentRole() == VtlComponentRole.ATTRIBUTE) {
                        check = check && checkDatasetService.hasOnlyOneAttribute(validationData.getVtlDatasets().get(0));
                    }
                    if (validationData.getVtlComponentRole() == VtlComponentRole.VIRAL) {
                        check = check && checkDatasetService.hasOnlyOneViral(validationData.getVtlDatasets().get(0));
                    }

                    if (!check) {
                        parameters = new String[1];
                        parameters[0] = validationData.getVtlComponentRole().toString();
                        semanticMessage = SemanticMessageUtility.setUpBlockingMessage(ValidationCheck.HAS_ONLY_ONE_BY_ROLE.getCode(),
                                ValidationCheck.HAS_ONLY_ONE_BY_ROLE.getMessage(), parameters);
                        semanticMessages.add(semanticMessage);
                    }
                    break;

                }
                case HAS_AT_LEAST_ONE_BY_ROLE: {
                    SemanticMessage semanticMessage;
                    boolean check = true;
                    if (validationData.getVtlComponentRole() == VtlComponentRole.IDENTIFIER) {
                        check = check && checkDatasetService.hasAtLeastOneIdentifier(validationData.getVtlDatasets().get(0));
                    }
                    if (validationData.getVtlComponentRole() == VtlComponentRole.MEASURE) {
                        check = check && checkDatasetService.hasAtLeastOneMeasure(validationData.getVtlDatasets().get(0));
                    }
                    if (validationData.getVtlComponentRole() == VtlComponentRole.ATTRIBUTE) {
                        check = check && checkDatasetService.hasAtLeastOneAttribute(validationData.getVtlDatasets().get(0));
                    }
                    if (validationData.getVtlComponentRole() == VtlComponentRole.VIRAL) {
                        check = check && checkDatasetService.hasAtLeastOneViral(validationData.getVtlDatasets().get(0));
                    }

                    if (!check) {
                        parameters = new String[1];
                        parameters[0] = validationData.getVtlComponentRole().toString();
                        semanticMessage = SemanticMessageUtility.setUpBlockingMessage(ValidationCheck.HAS_AT_LEAST_ONE_BY_ROLE.getCode(),
                                ValidationCheck.HAS_AT_LEAST_ONE_BY_ROLE.getMessage(), parameters);
                        semanticMessages.add(semanticMessage);
                    }
                    break;
                }
                case HAS_TYPE_AND_ROLE: {
                    SemanticMessage semanticMessage;
                    boolean check;
                    check = checkDatasetService.hasTypeAndRole(validationData.getVtlDatasets().get(0), validationData.getVtlComponentRole(), validationData.getVtlType());

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
                    check = checkDatasetService.hasValueDomainAndRole(validationData.getVtlDatasets().get(0), validationData.getVtlComponentRole(), validationData.getValueDomain());

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
