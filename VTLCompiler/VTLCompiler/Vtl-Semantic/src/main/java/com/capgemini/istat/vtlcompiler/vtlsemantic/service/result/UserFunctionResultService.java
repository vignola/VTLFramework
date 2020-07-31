package com.capgemini.istat.vtlcompiler.vtlsemantic.service.result;

import com.capgemini.istat.vtlcompiler.vtlcommon.model.KeyVariables;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.dataset.VtlComponent;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.dataset.VtlComponentRole;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.dataset.VtlDataset;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.define.UserFunction;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.define.VtlUserFunctionType;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.exception.ValidationException;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.semantic.ResultExpression;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.semantic.SemanticMessage;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.semantic.ValidationCheck;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.semantic.ValidationData;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.valuedomain.ValueDomain;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.constant.VtlOptional;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.dataset.VtlComponentId;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.define.VtlDefineFunction;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.define.operator.VtlFunctionParameter;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.define.operator.VtlUserFunction;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.define.operator.datatype.*;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.define.ruleset.VarParameter;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.define.ruleset.datapoint.VtlDatapointRuleset;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.define.ruleset.hierarchical.VtlCodeItem;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.define.ruleset.hierarchical.VtlHRRule;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.define.ruleset.hierarchical.VtlHierarchicalRuleset;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.VtlExpression;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.generic.VtlCallExpression;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.hierarchy.VtlHierarchyExpression;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.validation.VtlCheckDatapoint;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.validation.VtlCheckHierarchy;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.statement.VtlStatement;
import com.capgemini.istat.vtlcompiler.vtlcommon.repository.ValueDomainRepository;
import com.capgemini.istat.vtlcompiler.vtlcommon.service.ComponentUtilityService;
import com.capgemini.istat.vtlcompiler.vtlcommon.service.VtlUserFunctionService;
import com.capgemini.istat.vtlcompiler.vtlinterpreter.service.ParseTreeService;
import com.capgemini.istat.vtlcompiler.vtlsemantic.service.validation.ComponentSemanticValidationService;
import com.capgemini.istat.vtlcompiler.vtlsemantic.service.validation.DatasetSemanticValidationService;
import com.capgemini.istat.vtlcompiler.vtlsemantic.service.validation.SemanticMessageUtility;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * la classe offre tutti i metodi necessari per la validazione e la trasformazione semantica dell userDefineOperation.
 *
 * @see VtlUserFunctionService
 * @see ParseTreeService
 * @see DatasetSemanticValidationService
 * @see ComponentSemanticValidationService
 * @see ComponentUtilityService
 */
@Service
public class UserFunctionResultService {
    private VtlUserFunctionService vtlUserFunctionService;
    private ParseTreeService parseTreeService;
    private DatasetSemanticValidationService datasetSemanticValidationService;
    private ComponentSemanticValidationService componentSemanticValidationService;
    private ComponentUtilityService componentUtilityService;
    private ValueDomainRepository valueDomainRepository;
    public static final Logger logger = LogManager.getLogger(UserFunctionResultService.class);


    @Autowired
    public void setVtlUserFunctionService(VtlUserFunctionService vtlUserFunctionService) {
        this.vtlUserFunctionService = vtlUserFunctionService;
    }

    @Autowired
    public void setParseTreeService(ParseTreeService parseTreeService) {
        this.parseTreeService = parseTreeService;
    }

    @Autowired
    public void setDatasetSemanticValidationService(DatasetSemanticValidationService datasetSemanticValidationService) {
        this.datasetSemanticValidationService = datasetSemanticValidationService;
    }

    @Autowired
    public void setComponentSemanticValidationService(ComponentSemanticValidationService componentSemanticValidationService) {
        this.componentSemanticValidationService = componentSemanticValidationService;
    }

    @Autowired
    public void setComponentUtilityService(ComponentUtilityService componentUtilityService) {
        this.componentUtilityService = componentUtilityService;
    }

    @Autowired
    public void setValueDomainRepository(ValueDomainRepository valueDomainRepository) {
        this.valueDomainRepository = valueDomainRepository;
    }

    /**
     * dato un nome di funzione viene restituita l'expression che la rappresenta. Viene invocato il motore di interpretazione
     *
     * @param functionName
     * @return
     */
    public VtlDefineFunction getUserFunctionContent(String functionName) {
        UserFunction userFunction = vtlUserFunctionService.getByName(functionName);
        if (userFunction == null)
            return null;
        List<VtlStatement> vtlStatements = parseTreeService.visitTree(userFunction.getFunctionContent());
        return vtlStatements.get(0).getVtlDefineFunction();
    }

    /**
     * Dato un nome funzione e un tipo viene verificato che il tipo della funzione sia come quello inserito
     *
     * @param functionName
     * @param functionType
     * @return
     */
    public Boolean checkFunctionType(String functionName, VtlUserFunctionType functionType) {
        UserFunction userFunction = vtlUserFunctionService.getByName(functionName);
        return userFunction.getFunctionType().equals(functionType);
    }

    /**
     * Controlla l'esistenza di una funzione sul database
     *
     * @param functionName
     * @return
     */
    public Boolean checkExistFunction(String functionName) {
        UserFunction userFunction = vtlUserFunctionService.getByName(functionName);
        if (userFunction == null)
            return false;
        return true;
    }

    /**
     * Carica una user function dal database
     *
     * @param functionName
     * @return
     */
    public VtlUserFunction getUserFunction(String functionName) {
        VtlDefineFunction vtlDefineFunction = getUserFunctionContent(functionName);
        if (vtlDefineFunction != null && vtlDefineFunction instanceof VtlUserFunction)
            return (VtlUserFunction) vtlDefineFunction;
        else
            return null;
    }

    /**
     * carica una ruleset datapoint dal database
     *
     * @param functionName
     * @return
     */
    public VtlDatapointRuleset getDataPointRuleset(String functionName) {
        VtlDefineFunction vtlDefineFunction = getUserFunctionContent(functionName);
        if (vtlDefineFunction != null && vtlDefineFunction instanceof VtlDatapointRuleset)
            return (VtlDatapointRuleset) vtlDefineFunction;
        else
            return null;
    }

    /**
     * carica una hierarchical ruleset dal database
     *
     * @param functionName
     * @return
     */
    public VtlHierarchicalRuleset getHierarchicalRuleset(String functionName) {
        VtlDefineFunction vtlDefineFunction = getUserFunctionContent(functionName);
        if (vtlDefineFunction != null && vtlDefineFunction instanceof VtlHierarchicalRuleset)
            return (VtlHierarchicalRuleset) vtlDefineFunction;
        else
            return null;
    }

    /**
     * Effettua le validazioni per una call di una user function
     *
     * @param vtlCallExpression
     * @return
     * @throws ValidationException
     */
    public ResultExpression validateCallExpression(VtlCallExpression vtlCallExpression) throws ValidationException {
        ResultExpression resultExpression = new ResultExpression();
        SemanticMessage semanticMessage = checkExistsFunction(vtlCallExpression.getFunctionName(), VtlUserFunctionType.OPERATOR_FUNCTION);
        if (semanticMessage == null) {
            VtlUserFunction vtlUserFunction = getUserFunction(vtlCallExpression.getFunctionName());
            vtlCallExpression.setVtlUserFunction(vtlUserFunction);
        }
        if (semanticMessage != null)
            resultExpression.getMessages().add(semanticMessage);
        SemanticMessageUtility.processSemanticMessage(resultExpression);
        return resultExpression;
    }

    /**
     * Effettua le validazioni per i parametri di una call user function
     *
     * @param vtlCallExpression
     * @param variablesObjectMap
     * @return
     * @throws ValidationException
     */
    public ResultExpression validateCallExpressionParameter(VtlCallExpression vtlCallExpression, Map<KeyVariables, Object> variablesObjectMap) throws ValidationException {
        ResultExpression resultExpression = new ResultExpression();
        List<SemanticMessage> resultMessages = checkParameters(vtlCallExpression, vtlCallExpression.getVtlUserFunction(), variablesObjectMap);
        resultExpression.setMessages(resultMessages);
        SemanticMessageUtility.processSemanticMessage(resultExpression);
        vtlCallExpression.setParameterMap((Map<String, VtlExpression>) variablesObjectMap.get(KeyVariables.PARAMETER_MAP));
        return resultExpression;
    }

    /**
     * Effettua le validazioni per i parametri di una call user function. Vengono controllati l'esistenza di tuti iparametri
     * e che combacino con la funzione salvata
     *
     * @param vtlCallExpression
     * @param vtlUserFunction
     * @param variablesObjectMap
     * @return
     */
    public List<SemanticMessage> checkParameters(VtlCallExpression vtlCallExpression, VtlUserFunction vtlUserFunction, Map<KeyVariables, Object> variablesObjectMap) {
        List<SemanticMessage> semanticMessages = new ArrayList<>();
        Integer biggerSize =
                vtlCallExpression.getParameters().size() >= vtlUserFunction.getVtlFunctionParameters().size() ?
                        vtlCallExpression.getParameters().size() : vtlUserFunction.getVtlFunctionParameters().size();
        Map<String, VtlExpression> parameterName = new HashMap<>();
        for (int i = 0; i < biggerSize; i++) {
            VtlFunctionParameter vtlFunctionParameter = null;
            VtlExpression vtlParameterExpression = null;
            try {
                vtlFunctionParameter = vtlUserFunction.getVtlFunctionParameters().get(i);
            } catch (Exception exc) {
                logger.info("parametro funzione non esistente");
            }
            try {
                vtlParameterExpression = vtlCallExpression.getParameters().get(i);
            } catch (Exception exc) {
                logger.info("parametro  non esistente");

            }
            SemanticMessage semanticMessage = checkCoherenceParameter(vtlParameterExpression, vtlFunctionParameter);

            if (semanticMessage == null && vtlParameterExpression != null)
                semanticMessage = checkCoherenceParameterType(vtlParameterExpression, vtlFunctionParameter);
            if (semanticMessage != null) {
                semanticMessages.add(
                        semanticMessage
                );
                return semanticMessages;
            }
            if (vtlFunctionParameter != null) {
                if (vtlParameterExpression != null && !(vtlParameterExpression instanceof VtlOptional)) {
                    semanticMessages.addAll(
                            checkVtlDataType(vtlParameterExpression, vtlFunctionParameter.getVtlDataType(), vtlFunctionParameter.getParameterName())
                    );
                    parameterName.put(vtlFunctionParameter.getParameterName(), vtlParameterExpression);
                } else {
                    parameterName.put(vtlFunctionParameter.getParameterName(), vtlFunctionParameter.getDefaultValue());
                }
            }
            if (!semanticMessages.isEmpty())
                return semanticMessages;
        }
        if (semanticMessages.size() == 0)
            variablesObjectMap.put(KeyVariables.PARAMETER_MAP, parameterName);
        return semanticMessages;
    }

    /**
     * Viene verificato che la funzione esista e che sia del tipo ricercato. Se le condizioni non sono verificate
     * viene costruito il messaggio di errore
     *
     * @param functionName
     * @param functionType
     * @return
     */
    public SemanticMessage checkExistsFunction(String functionName, VtlUserFunctionType functionType) {
        String[] parameters = new String[1];
        parameters[0] = functionName;
        if (!checkExistFunction(functionName))
            return SemanticMessageUtility.setUpBlockingMessage(ValidationCheck.FUNCTION_NOT_EXIST.getCode(), ValidationCheck.FUNCTION_NOT_EXIST.getMessage(), parameters);
        if (!checkFunctionType(functionName, functionType))
            return SemanticMessageUtility.setUpBlockingMessage(ValidationCheck.FUNCTION_TYPE_WRONG.getCode(), ValidationCheck.FUNCTION_TYPE_WRONG.getMessage(), parameters);
        return null;
    }

    /**
     * Controlla che siano rispettati i parametri di coerenza dei parametri opzionali
     *
     * @param callParameter
     * @param vtlFunctionParameter
     * @return
     */
    public SemanticMessage checkCoherenceParameter(VtlExpression callParameter,
                                                   VtlFunctionParameter vtlFunctionParameter) {
        if (vtlFunctionParameter == null)
            return SemanticMessageUtility.setUpBlockingMessage(
                    ValidationCheck.WRONG_PARAMETER_INVOCATION.getCode(),
                    ValidationCheck.WRONG_PARAMETER_INVOCATION.getMessage()
            );
        if (callParameter == null && vtlFunctionParameter.getDefaultValue() == null) {
            String[] parameters = new String[1];
            parameters[0] = vtlFunctionParameter.getParameterName();
            return SemanticMessageUtility.setUpBlockingMessage(
                    ValidationCheck.FUNCTION_TYPE_NOT_OPTIONAL.getCode(),
                    ValidationCheck.FUNCTION_TYPE_NOT_OPTIONAL.getMessage(),
                    parameters
            );

        }
        if (callParameter instanceof VtlOptional && vtlFunctionParameter.getDefaultValue() == null) {
            String[] parameters = new String[1];
            parameters[0] = vtlFunctionParameter.getParameterName();
            return SemanticMessageUtility.setUpBlockingMessage(
                    ValidationCheck.FUNCTION_TYPE_NOT_OPTIONAL.getCode(),
                    ValidationCheck.FUNCTION_TYPE_NOT_OPTIONAL.getMessage(),
                    parameters
            );

        }
        return null;
    }

    /**
     * viene controllato il tipo tipo sui parametri delle user function. il metodo controlla se viene utilizzato uno scalare
     * piuttosto di un componente o un dataset richiesto dalla user function
     *
     * @param callParameter
     * @param vtlFunctionParameter
     * @return
     */
    public SemanticMessage checkCoherenceParameterType(VtlExpression callParameter,
                                                       VtlFunctionParameter vtlFunctionParameter) {
        if (callParameter instanceof VtlOptional) {
            return null;
        }
        String[] parameters = new String[1];
        parameters[0] = vtlFunctionParameter.getParameterName();
        if ((callParameter.getResultExpression().isAScalar() && !(vtlFunctionParameter.getVtlDataType() instanceof VtlScalarType)) ||
                (!callParameter.getResultExpression().isAScalar() && callParameter.getResultExpression().getResult() != null && !(vtlFunctionParameter.getVtlDataType() instanceof VtlDatasetType)) ||
                (!callParameter.getResultExpression().isAScalar() && callParameter.getResultExpression().getResultComponent() != null && !(vtlFunctionParameter.getVtlDataType() instanceof VtlComponentType))) {
            return wrongParameterError(parameters);
        }
        return null;
    }

    /**
     * Organizza i messaggi di errore(se presenti) dell'analisi di coerenza dei parametri delle userFunction
     *
     * @param vtlDatasetType
     * @param vtlDataset
     * @return
     */
    public List<SemanticMessage> checkDataType(VtlDatasetType vtlDatasetType, VtlDataset vtlDataset) {
        List<SemanticMessage> semanticMessages = new ArrayList<>();
        for (VtlComponentConstraint vtlComponentConstraint : vtlDatasetType.getComponentConstraints()) {
            semanticMessages.addAll(checkComponentConstraint(vtlComponentConstraint, vtlDataset));
        }
        return semanticMessages;
    }

    /**
     * Verifica la coerenza dei parametri nella casistica di dichiarazione implicita delle colonne delle user function
     * e restituisce una lista di errori se presenti
     *
     * @param vtlComponentConstraint
     * @param vtlDataset
     * @return
     */
    public List<SemanticMessage> checkComponentConstraint(VtlComponentConstraint vtlComponentConstraint, VtlDataset vtlDataset) {
        List<SemanticMessage> semanticMessagesResult = new ArrayList<>();
        if (vtlDataset == null) {
            String[] parameters = new String[1];
            parameters[0] = "";
            semanticMessagesResult.add(SemanticMessageUtility.setUpBlockingMessage(
                    ValidationCheck.WRONG_PARAMETER_TYPE_INVOCATION.getCode(),
                    ValidationCheck.WRONG_PARAMETER_TYPE_INVOCATION.getMessage(),
                    parameters)
            );

            return semanticMessagesResult;
        }
        ValidationData validationData = new ValidationData();
        validationData.getVtlDatasets().add(vtlDataset);
        ValidationCheck validationCheck = null;
        if (vtlComponentConstraint.getVtlComponentMultiplicity().equals(VtlComponentMultiplicity.ONLY_ONE))
            validationCheck = ValidationCheck.HAS_ONLY_ONE_BY_ROLE;
        else if (vtlComponentConstraint.getVtlComponentMultiplicity().equals(VtlComponentMultiplicity.ONE_TO_MANY))
            validationCheck = ValidationCheck.HAS_AT_LEAST_ONE_BY_ROLE;
        validationData.getValidationChecks().add(validationCheck);
        validationData.setVtlComponentRole(vtlComponentConstraint.getVtlComponentType().getComponentRole());
        VtlScalarType vtlScalarType = vtlComponentConstraint.getVtlComponentType().getScalarType();
        setValidationScalar(vtlScalarType, validationData);
        semanticMessagesResult.addAll(datasetSemanticValidationService.validateSemantic(validationData));
        return semanticMessagesResult;

    }

    /**
     * Offre delle funzionalità di controllo dei componenti rispetto al tipo richiesto dalla user function
     *
     * @param vtlComponentType
     * @param vtlComponent
     * @return
     */
    public List<SemanticMessage> checkComponentType(VtlComponentType vtlComponentType, VtlComponent vtlComponent) {
        List<SemanticMessage> semanticMessagesResult = new ArrayList<>();
        if (vtlComponent == null) {
            String[] parameters = new String[1];
            parameters[0] = "";
            semanticMessagesResult.add(SemanticMessageUtility.setUpBlockingMessage(
                    ValidationCheck.WRONG_PARAMETER_TYPE_INVOCATION.getCode(),
                    ValidationCheck.WRONG_PARAMETER_TYPE_INVOCATION.getMessage(),
                    parameters)
            );
        }
        ValidationData validationData = new ValidationData();
        validationData.getVtlComponents().add(vtlComponent);
        validationData.setVtlComponentRole(vtlComponentType.getComponentRole());
        VtlScalarType vtlScalarType = vtlComponentType.getScalarType();
        setValidationScalar(vtlScalarType, validationData);
        semanticMessagesResult.addAll(componentSemanticValidationService.validateSemantic(validationData));
        return semanticMessagesResult;
    }

    /**
     * Offre dei controlli di tipo e value domain rispetto a un tipo scalare in ingresso.
     *
     * @param vtlScalarType
     * @param resultExpression
     * @return
     */
    public List<SemanticMessage> checkScalarType(VtlScalarType vtlScalarType, ResultExpression resultExpression) {
        ValidationData validationData = new ValidationData();
        validationData.getVtlDatasets().add(resultExpression.getResult());
        validationData.setVtlComponentRole(VtlComponentRole.MEASURE);
        setValidationScalar(vtlScalarType, validationData);
        return datasetSemanticValidationService.validateSemantic(validationData);
    }

    private ValidationData setValidationScalar(VtlScalarType vtlScalarType, ValidationData validationData) {
        if (vtlScalarType != null) {
            if (vtlScalarType.getVtlType() != null) {
                validationData.setVtlType(vtlScalarType.getVtlType());
                validationData.getValidationChecks().add(ValidationCheck.HAS_TYPE_AND_ROLE);
            }
            if (vtlScalarType.getValueDomain() != null) {
                validationData.setValueDomain(vtlScalarType.getValueDomain());
                validationData.getValidationChecks().add(ValidationCheck.HAS_VALUE_DOMAIN_AND_ROLE);
            }

        }
        return validationData;
    }

    /**
     * Verifica che il tipo espresso dall'output di una user define operatorion sia coerente rispetto al risultato trovato
     *
     * @param expression
     * @param vtlDataType
     * @param parameterName
     * @return
     */
    public List<SemanticMessage> checkVtlDataType(VtlExpression expression, VtlDataType vtlDataType, String parameterName) {
        List<SemanticMessage> semanticMessages = new ArrayList<>();
        //dataet
        if (vtlDataType instanceof VtlDatasetType) {
            semanticMessages.addAll(
                    setupParameterError(
                            parameterName,
                            checkDataType(
                                    (VtlDatasetType) vtlDataType,
                                    expression.getResultExpression().getResult()
                            )
                    )
            );
        } else if (vtlDataType instanceof VtlComponentType) {
            // Componente
            semanticMessages.addAll(
                    setupParameterError(
                            parameterName,
                            checkComponentType(
                                    (VtlComponentType) vtlDataType,
                                    expression.getResultExpression().getResultComponent()
                            )
                    )
            );
        } else {
            semanticMessages.addAll(
                    setupParameterError(
                            parameterName,
                            checkScalarType(
                                    (VtlScalarType) vtlDataType,
                                    expression.getResultExpression())
                    )
            );
        }

        return semanticMessages;
    }

    /**
     * Offre la validazione della check Datapoint
     *
     * @param vtlCheckDatapoint
     * @param variables
     * @return
     * @throws ValidationException
     */
    public ResultExpression validateCheckDatapoint(VtlCheckDatapoint vtlCheckDatapoint, Map<KeyVariables, Object> variables) throws ValidationException {
        ResultExpression resultExpression = new ResultExpression();
        SemanticMessage semanticMessage = checkExistsFunction(vtlCheckDatapoint.getRulesetName(), VtlUserFunctionType.DATAPOINT_FUNCTION);
        if (semanticMessage == null) {
            vtlCheckDatapoint.setVtlDatapointRuleset(getDataPointRuleset(vtlCheckDatapoint.getRulesetName()));
        } else {
            resultExpression.getMessages().add(semanticMessage);
        }
        SemanticMessageUtility.processSemanticMessage(resultExpression);
        checkDatapointParameters(vtlCheckDatapoint, variables);
        vtlCheckDatapoint.setParameterMapping((Map<String, VtlExpression>) variables.get(KeyVariables.PARAMETER_MAP));
        return resultExpression;
    }

    /**
     * Offre la validazione dei parametri della check datapoint
     *
     * @param vtlCheckDatapoint
     * @param variables
     * @throws ValidationException
     */
    public void checkDatapointParameters(VtlCheckDatapoint vtlCheckDatapoint, Map<KeyVariables, Object> variables) throws ValidationException {
        ResultExpression resultExpression = new ResultExpression();
        List<SemanticMessage> semanticMessagesResult = new ArrayList<>();
        // se c'è un dataset senza parametri e tipologia dei valueDomain
        if (vtlCheckDatapoint.getVtlDatapointRuleset().isValueDomainParameter()) {
            if (vtlCheckDatapoint.getComponentIds() == null || vtlCheckDatapoint.getComponentIds().isEmpty()) {
                Object[] parameters = new Object[1];
                parameters[0] = "";
                semanticMessagesResult.add(
                        SemanticMessageUtility.setUpBlockingMessage(
                                ValidationCheck.INCOMPATIBLE_PARAMETER.getCode(),
                                ValidationCheck.INCOMPATIBLE_PARAMETER.getMessage()
                        )
                );
            } else {
                //Controllo value domain
                SemanticMessage semanticMessage = checkParameterSize(vtlCheckDatapoint.getComponentIds().size(), vtlCheckDatapoint.getVtlDatapointRuleset().getSignatures().size());
                if (semanticMessage == null) {
                    semanticMessage = checkValueDomainsParameter(vtlCheckDatapoint.getComponentIds(), vtlCheckDatapoint.getVtlDatapointRuleset().getSignatures());
                }
                if (semanticMessage != null)
                    semanticMessagesResult.add(semanticMessage);
            }
        } else {
            SemanticMessage semanticMessage = null;
            if (vtlCheckDatapoint.getComponentIds() == null || vtlCheckDatapoint.getComponentIds().isEmpty()) {
                semanticMessage = checkParameterSize(vtlCheckDatapoint.getDatasetToCheck().getResultExpression().getResult().getVtlComponents().size(), vtlCheckDatapoint.getVtlDatapointRuleset().getSignatures().size());
            } else {
                semanticMessage = checkParameterSize(vtlCheckDatapoint.getComponentIds().size(), vtlCheckDatapoint.getVtlDatapointRuleset().getSignatures().size());

            }
            if (semanticMessage != null)
                semanticMessagesResult.add(semanticMessage);
        }
        resultExpression.setMessages(semanticMessagesResult);
        if (vtlCheckDatapoint.getComponentIds() != null) {
            mapExplicitParameter(vtlCheckDatapoint.getComponentIds(), vtlCheckDatapoint.getVtlDatapointRuleset().getSignatures(), variables);
        } else {
            mapImplicitParameter(vtlCheckDatapoint.getDatasetToCheck().getResultExpression().getResult(), vtlCheckDatapoint.getVtlDatapointRuleset().getSignatures(), variables);
        }
        SemanticMessageUtility.processSemanticMessage(resultExpression);

    }

    /**
     * Costruisce messaggi di errore se il numero di parametri non risulta congruente con quanto dichiarato dalle user function
     *
     * @param sizeParameterInvocation
     * @param sizeParameterDefinition
     * @return
     */
    public SemanticMessage checkParameterSize(Integer sizeParameterInvocation, Integer sizeParameterDefinition) {
        if (sizeParameterInvocation != sizeParameterDefinition) {
            Object[] parameters = new Object[1];
            parameters[0] = "";
            return SemanticMessageUtility.setUpBlockingMessage(
                    ValidationCheck.INCOMPATIBLE_PARAMETER.getCode(),
                    ValidationCheck.INCOMPATIBLE_PARAMETER.getMessage()
            );
        }

        return null;
    }

    /**
     * Esegue controlli sui value domain dei parametri delle user function
     *
     * @param vtlComponentIdInvocations
     * @param vtlComponentIdsParameters
     * @return
     */
    public SemanticMessage checkValueDomainsParameter(List<VtlComponentId> vtlComponentIdInvocations, List<VarParameter> vtlComponentIdsParameters) {

        for (int i = 0; i < vtlComponentIdInvocations.size(); i++) {
            VtlComponent vtlComponent = vtlComponentIdInvocations.get(i).getResultExpression().getResultComponent();
            String valueDomain = vtlComponentIdsParameters.get(i).getName();
            SemanticMessage semanticMessage = checkValueDomain(valueDomain, vtlComponent);
            if (semanticMessage != null)
                return semanticMessage;
        }
        return null;
    }

    /**
     * Effettua controlli sui value domain dei componenti delle user function
     *
     * @param valueDomain
     * @param vtlComponent
     * @return
     */
    public SemanticMessage checkValueDomain(String valueDomain, VtlComponent vtlComponent) {
        if (vtlComponent.getDomainValue().equalsIgnoreCase(valueDomain)) {
            return null;
        } else {
            String[] parameters = new String[1];
            parameters[0] = "";
            return SemanticMessageUtility.setUpBlockingMessage(
                    ValidationCheck.INCOMPATIBLE_VALUE_DOMAIN.getCode(),
                    ValidationCheck.INCOMPATIBLE_VALUE_DOMAIN.getMessage(),
                    parameters
            );
        }
    }

    /**
     * Esegue un mapping fra i parametri espliciti della funzione e i valori della funzione stessa
     *
     * @param vtlComponentIdInvocations
     * @param vtlComponentIdsParameters
     * @param variables
     */
    public void mapExplicitParameter(
            List<VtlComponentId> vtlComponentIdInvocations,
            List<VarParameter> vtlComponentIdsParameters,
            Map<KeyVariables, Object> variables) {
        Map<String, VtlExpression> parameterName = new HashMap<>();
        for (int i = 0; i < vtlComponentIdInvocations.size(); i++) {
            String name;
            if (vtlComponentIdsParameters.get(i).getAlias() == null || vtlComponentIdsParameters.get(i).getAlias().isEmpty()) {
                name = vtlComponentIdsParameters.get(i).getName();
            } else {
                name = vtlComponentIdsParameters.get(i).getAlias();
            }
            parameterName.put(name, vtlComponentIdInvocations.get(i));
        }
        variables.put(KeyVariables.PARAMETER_MAP, parameterName);
    }

    /**
     * Esegue un mapping fra i parametri impliciti della funzione e i valori della funzione stessa
     *
     * @param vtlDataset
     * @param vtlComponentIdsParameters
     * @param variables
     * @throws ValidationException
     */
    public void mapImplicitParameter(VtlDataset vtlDataset, List<VarParameter> vtlComponentIdsParameters, Map<KeyVariables, Object> variables) throws ValidationException {
        ResultExpression resultExpression = new ResultExpression();
        Map<String, VtlExpression> parameterName = new HashMap<>();
        for (VarParameter varParameter : vtlComponentIdsParameters) {
            String name;
            if (varParameter.getAlias() == null || varParameter.getAlias().isEmpty()) {
                name = varParameter.getName();
            } else {
                name = varParameter.getAlias();
            }
            VtlComponentId vtlComponentId = new VtlComponentId();
            VtlComponent vtlComponent = componentUtilityService.getComponentFromName(vtlDataset.getVtlComponents(), varParameter.getName(), true);
            if (vtlComponent == null) {
                resultExpression.getMessages().add(
                        SemanticMessageUtility.setUpBlockingMessage(ValidationCheck.WRONG_PARAMETER_NAME.getCode(), ValidationCheck.WRONG_PARAMETER_NAME.getMessage())
                );
            } else {
                ResultExpression resultExpressionComponent = new ResultExpression();
                resultExpression.setResultComponent(vtlComponent);
                vtlComponentId.setResultExpression(resultExpressionComponent);
                parameterName.put(name, vtlComponentId);
            }
        }
        SemanticMessageUtility.processSemanticMessage(resultExpression);
        variables.put(KeyVariables.PARAMETER_MAP, parameterName);
    }

    /**
     * Effettua la trasformazione semantica dell'operatore validazione e esecuzione  della check Hierarchy
     *
     * @param vtlCheckHierarchy
     * @param variables
     * @return
     * @throws ValidationException
     */
    public ResultExpression validateCheckHierarchicalRuleset(VtlCheckHierarchy vtlCheckHierarchy, Map<KeyVariables, Object> variables) throws ValidationException {
        ResultExpression resultExpression = new ResultExpression();
        SemanticMessage semanticMessage = checkExistsFunction(vtlCheckHierarchy.getHrRule(), VtlUserFunctionType.HIERARCHICAL_FUNCTION);

        if (semanticMessage == null) {
            vtlCheckHierarchy.setVtlHierarchicalRuleset(getHierarchicalRuleset(vtlCheckHierarchy.getHrRule()));
        } else {
            resultExpression.getMessages().add(semanticMessage);
        }
        SemanticMessageUtility.processSemanticMessage(resultExpression);
        checkHierarchicalRulesetParameter(vtlCheckHierarchy, variables);
        return resultExpression;
    }

    /**
     * Effettua la trasformazione semantica dell'operatore validazione dei parametri della chech hierarchy
     *
     * @param vtlCheckHierarchy
     * @param variables
     * @throws ValidationException
     */
    public void checkHierarchicalRulesetParameter(VtlCheckHierarchy vtlCheckHierarchy, Map<KeyVariables, Object> variables) throws ValidationException {
        ResultExpression resultExpression = new ResultExpression();
        List<SemanticMessage> semanticMessagesResult = new ArrayList<>();
        // se c'è un dataset senza parametri e tipologia dei valueDomain
        if (vtlCheckHierarchy.getVtlHierarchicalRuleset().isValueDomainParameter()) {
            if (vtlCheckHierarchy.getConditionComponents() == null || vtlCheckHierarchy.getConditionComponents().isEmpty()) {
                Object[] parameters = new Object[1];
                parameters[0] = "";
                semanticMessagesResult.add(
                        SemanticMessageUtility.setUpBlockingMessage(
                                ValidationCheck.INCOMPATIBLE_PARAMETER.getCode(),
                                ValidationCheck.INCOMPATIBLE_PARAMETER.getMessage()
                        )
                );
            } else {
                //Controllo value domain
                SemanticMessage semanticMessage = checkParameterSize(vtlCheckHierarchy.getConditionComponents().size(), vtlCheckHierarchy.getVtlHierarchicalRuleset().getSignatures().size());
                if (semanticMessage == null) {
                    semanticMessage = checkValueDomainsParameter(vtlCheckHierarchy.getConditionComponents(), vtlCheckHierarchy.getVtlHierarchicalRuleset().getSignatures());
                }
                if (semanticMessage != null)
                    semanticMessagesResult.add(semanticMessage);
            }
        } else {
            SemanticMessage semanticMessage = null;
            if (vtlCheckHierarchy.getConditionComponents() != null && !vtlCheckHierarchy.getConditionComponents().isEmpty()) {
                semanticMessage = checkParameterSize(vtlCheckHierarchy.getConditionComponents().size(), vtlCheckHierarchy.getVtlHierarchicalRuleset().getSignatures().size());
            }

            if (semanticMessage != null)
                semanticMessagesResult.add(semanticMessage);
        }

        if (vtlCheckHierarchy.getVtlHierarchicalRuleset().getRuleVar() != null && vtlCheckHierarchy.getRuleComponent() == null) {
            Object[] parameters = new Object[1];
            parameters[0] = "";
            semanticMessagesResult.add(
                    SemanticMessageUtility.setUpBlockingMessage(
                            ValidationCheck.RULE_NOT_FOUND.getCode(),
                            ValidationCheck.RULE_NOT_FOUND.getMessage()
                    )
            );
        }
        resultExpression.setMessages(semanticMessagesResult);
        if (vtlCheckHierarchy.getConditionComponents() != null) {
            mapExplicitParameter(vtlCheckHierarchy.getConditionComponents(), vtlCheckHierarchy.getVtlHierarchicalRuleset().getSignatures(), variables);
        } else {
            mapImplicitParameter(vtlCheckHierarchy.getVtlVarId().getResultExpression().getResult(), vtlCheckHierarchy.getVtlHierarchicalRuleset().getSignatures(), variables);
        }
        SemanticMessageUtility.processSemanticMessage(resultExpression);
        vtlCheckHierarchy.setParameterMapping((Map<String, VtlExpression>) variables.get(KeyVariables.PARAMETER_MAP));
        vtlCheckHierarchy.getParameterMapping().put("value", vtlCheckHierarchy.getRuleComponent());
    }

    /**
     * Effettua la trasformazione semantica dell'operatore validazione e esecuzione della hierarchy
     *
     * @param vtlHierarchyExpression
     * @param variables
     * @return
     * @throws ValidationException
     */
    public ResultExpression validateHierarchy(VtlHierarchyExpression vtlHierarchyExpression, Map<KeyVariables, Object> variables) throws ValidationException {
        ResultExpression resultExpression = new ResultExpression();
        SemanticMessage semanticMessage = checkExistsFunction(vtlHierarchyExpression.getHrName(), VtlUserFunctionType.HIERARCHICAL_FUNCTION);

        if (semanticMessage == null) {
            vtlHierarchyExpression.setVtlHierarchicalRuleset(getHierarchicalRuleset(vtlHierarchyExpression.getHrName()));
        } else {
            resultExpression.getMessages().add(semanticMessage);
        }

        SemanticMessageUtility.processSemanticMessage(resultExpression);
        checkHierarchyParameter(vtlHierarchyExpression, variables);
        return resultExpression;
    }

    /**
     * Offre le funzionalità di validazione dei parametri della hierarchy
     *
     * @param vtlHierarchyExpression
     * @param variables
     * @throws ValidationException
     */
    public void checkHierarchyParameter(VtlHierarchyExpression vtlHierarchyExpression, Map<KeyVariables, Object> variables) throws ValidationException {
        ResultExpression resultExpression = new ResultExpression();
        List<SemanticMessage> semanticMessagesResult = new ArrayList<>();
        // se c'è un dataset senza parametri e tipologia dei valueDomain
        if (vtlHierarchyExpression.getVtlHierarchicalRuleset().isValueDomainParameter()) {
            if (vtlHierarchyExpression.getComponentIds() != null && !vtlHierarchyExpression.getComponentIds().isEmpty()) {
                //Controllo value domain
                SemanticMessage semanticMessage = checkParameterSize(vtlHierarchyExpression.getComponentIds().size(), vtlHierarchyExpression.getVtlHierarchicalRuleset().getSignatures().size());
                if (semanticMessage == null) {
                    semanticMessage = checkValueDomainsParameter(vtlHierarchyExpression.getComponentIds(), vtlHierarchyExpression.getVtlHierarchicalRuleset().getSignatures());
                }
                if (semanticMessage != null)
                    semanticMessagesResult.add(semanticMessage);
            }
        } else {
            SemanticMessage semanticMessage = null;
            if (vtlHierarchyExpression.getComponentIds() != null && !vtlHierarchyExpression.getComponentIds().isEmpty()) {
                semanticMessage = checkParameterSize(vtlHierarchyExpression.getComponentIds().size(), vtlHierarchyExpression.getVtlHierarchicalRuleset().getSignatures().size());
            }
            if (semanticMessage != null)
                semanticMessagesResult.add(semanticMessage);
        }

        if (vtlHierarchyExpression.getVtlHierarchicalRuleset().getRuleVar() != null && vtlHierarchyExpression.getRuleComponent() == null) {
            Object[] parameters = new Object[1];
            parameters[0] = "";
            semanticMessagesResult.add(
                    SemanticMessageUtility.setUpBlockingMessage(
                            ValidationCheck.RULE_NOT_FOUND.getCode(),
                            ValidationCheck.RULE_NOT_FOUND.getMessage()
                    )
            );
        }
        resultExpression.setMessages(semanticMessagesResult);
        if (vtlHierarchyExpression.getComponentIds() != null) {
            mapExplicitParameter(vtlHierarchyExpression.getComponentIds(), vtlHierarchyExpression.getVtlHierarchicalRuleset().getSignatures(), variables);
        } else {
            mapImplicitParameter(vtlHierarchyExpression.getVtlOperandExpression().getResultExpression().getResult(), vtlHierarchyExpression.getVtlHierarchicalRuleset().getSignatures(), variables);
        }
        SemanticMessageUtility.processSemanticMessage(resultExpression);
        vtlHierarchyExpression.setParameterMapping((Map<String, VtlExpression>) variables.get(KeyVariables.PARAMETER_MAP));
        vtlHierarchyExpression.getParameterMapping().put("value", vtlHierarchyExpression.getRuleComponent());
    }

    /**
     * Effettua la trasformazione semantica dell'operatore validazione e esecuzione  della Datapoint Ruleset
     *
     * @param vtlDatapointRuleset
     * @return
     */
    public ResultExpression validateDatapointRuleset(VtlDatapointRuleset vtlDatapointRuleset) {
        ResultExpression resultExpression = new ResultExpression();
        if (vtlDatapointRuleset.isValueDomainParameter()) {
            SemanticMessage semanticMessage = checkExistingValueDomains(vtlDatapointRuleset.getSignatures());
            if (semanticMessage != null)
                resultExpression.getMessages().add(semanticMessage);
        }
        checkParameter(vtlDatapointRuleset.getSignatures(), resultExpression);
        return resultExpression;
    }

    /**
     * Effettua la trasformazione semantica dell'operatore validazione e esecuzione  della user function
     *
     * @param vtlUserFunction
     * @return
     */
    public ResultExpression validateDefineUserFunction(VtlUserFunction vtlUserFunction) {
        ResultExpression resultExpression = new ResultExpression();
        List<String> parameterNames = new ArrayList<>();
        for (VtlFunctionParameter vtlFunctionParameter : vtlUserFunction.getVtlFunctionParameters()) {
            if (vtlFunctionParameter.getVtlDataType() instanceof VtlScalarType) {
                VtlScalarType vtlScalarType = (VtlScalarType) vtlFunctionParameter.getVtlDataType();
                if (vtlScalarType.getValueDomain() != null && !vtlScalarType.getValueDomain().isEmpty()) {
                    List<ValueDomain> valueDomains = valueDomainRepository.getAllByValueDomainNameIgnoreCase(vtlScalarType.getValueDomain());
                    if (valueDomains == null || valueDomains.isEmpty()) {
                        resultExpression.getMessages().add(
                                SemanticMessageUtility.setUpBlockingMessage(
                                        ValidationCheck.VALUEDOMAIN_NOT_FOUND.getCode(),
                                        ValidationCheck.VALUEDOMAIN_NOT_FOUND.getMessage()
                                )
                        );
                        return resultExpression;
                    }
                }
            }
            if (parameterNames.contains(vtlFunctionParameter.getParameterName().toUpperCase())) {
                resultExpression.getMessages().add(
                        SemanticMessageUtility.setUpBlockingMessage(
                                ValidationCheck.PARAMETER_NAME_REPEATED.getCode(),
                                ValidationCheck.PARAMETER_NAME_REPEATED.getMessage(),
                                vtlFunctionParameter.getParameterName()
                        )
                );
            }
            parameterNames.add(vtlFunctionParameter.getParameterName().toUpperCase());
        }
        return resultExpression;
    }

    /**
     * Effettua la trasformazione semantica dell'operatore validazione e esecuzione della hierarchical ruleset
     *
     * @param vtlHierarchicalRuleset
     * @return
     */
    public ResultExpression validateHierarhicalRuleset(VtlHierarchicalRuleset vtlHierarchicalRuleset) {
        ResultExpression resultExpression = new ResultExpression();
        checkParameter(vtlHierarchicalRuleset.getSignatures(), resultExpression);

        if (vtlHierarchicalRuleset.isValueDomainParameter()) {
            SemanticMessage semanticMessage = checkExistingValueDomains(vtlHierarchicalRuleset.getSignatures());
            if (semanticMessage != null)
                resultExpression.getMessages().add(semanticMessage);

            if (vtlHierarchicalRuleset.getRuleVar() != null) {
                List<ValueDomain> valueDomainRule = valueDomainRepository.getAllByValueDomainNameIgnoreCase(vtlHierarchicalRuleset.getRuleVar());
                if (valueDomainRule == null || valueDomainRule.isEmpty()) {
                    resultExpression.getMessages().add(
                            SemanticMessageUtility.setUpBlockingMessage(
                                    ValidationCheck.VALUEDOMAIN_NOT_FOUND.getCode(),
                                    ValidationCheck.VALUEDOMAIN_NOT_FOUND.getMessage()
                            ));
                }
                for (VtlHRRule vtlHRRule : vtlHierarchicalRuleset.getVtlHRRules()) {
                    SemanticMessage semanticMessageRule =
                            checkExistingValueDomainCode(
                                    vtlHierarchicalRuleset.getRuleVar(),
                                    vtlHRRule.getVtlCodeItemRelation().getLeftCodeItem()
                            );
                    if (semanticMessageRule != null) {
                        resultExpression.getMessages().add(semanticMessageRule);
                    }
                    for (VtlCodeItem vtlCodeItem : vtlHRRule.getVtlCodeItemRelation().getVtlCodeItems()) {
                        SemanticMessage semanticMessageRightRule =
                                checkExistingValueDomainCode(
                                        vtlHierarchicalRuleset.getRuleVar(),
                                        vtlCodeItem.getCodeItem()
                                );
                        if (semanticMessageRightRule != null) {
                            resultExpression.getMessages().add(semanticMessageRightRule);
                        }
                    }


                }
            }

        }
        return resultExpression;

    }

    private void checkParameter(List<VarParameter> parameters, ResultExpression resultExpression) {
        List<String> datasetsNames = new ArrayList<>();
        List<String> aliasNames = new ArrayList<>();
        for (VarParameter varParameter : parameters) {
            if (varParameter.getAlias() == null || varParameter.getAlias().isEmpty()) {
                if (datasetsNames.contains(varParameter.getName().toUpperCase())) {
                    resultExpression.getMessages().add(SemanticMessageUtility.setUpBlockingMessage(
                            ValidationCheck.NEED_ALIAS_NAME.getCode(),
                            ValidationCheck.NEED_ALIAS_NAME.getMessage(),
                            varParameter.getName()
                    ));
                }
                datasetsNames.add(varParameter.getName().toUpperCase());
            } else {
                if (aliasNames.contains(varParameter.getAlias().toUpperCase())) {
                    resultExpression.getMessages().add(SemanticMessageUtility.setUpBlockingMessage(
                            ValidationCheck.ALIAS_REPEATED.getCode(),
                            ValidationCheck.ALIAS_REPEATED.getMessage(),
                            varParameter.getAlias().toUpperCase()
                    ));
                }
                aliasNames.add(varParameter.getAlias().toUpperCase().toUpperCase());
            }
        }

        for (String datasetName : datasetsNames) {
            if (aliasNames.contains(datasetName)) {
                resultExpression.getMessages().add(SemanticMessageUtility.setUpBlockingMessage(
                        ValidationCheck.PARAMETER_NAME_REPEATED.getCode(),
                        ValidationCheck.PARAMETER_NAME_REPEATED.getMessage(),
                        datasetName
                ));
            }
        }

    }

    private SemanticMessage checkExistingValueDomains(List<VarParameter> varParameters) {
        for (VarParameter varParameter : varParameters) {
            List<ValueDomain> valueDomains = valueDomainRepository.getAllByValueDomainNameIgnoreCase(varParameter.getName());
            if (valueDomains == null || valueDomains.isEmpty()) {
                return SemanticMessageUtility.setUpBlockingMessage(
                        ValidationCheck.VALUEDOMAIN_NOT_FOUND.getCode(),
                        ValidationCheck.VALUEDOMAIN_NOT_FOUND.getMessage()
                );
            }
        }
        return null;
    }

    private SemanticMessage checkExistingValueDomainCode(String valueDomain, String valueDomainCode) {
        ValueDomain valueDomainFound = valueDomainRepository.getByValueDomainNameIgnoreCaseAndCodeIgnoreCase(valueDomain, valueDomainCode);
        if (valueDomainFound == null)
            return SemanticMessageUtility.setUpBlockingMessage(
                    ValidationCheck.VALUEDOMAIN_VALUE_NOT_FOUND.getCode(),
                    ValidationCheck.VALUEDOMAIN_VALUE_NOT_FOUND.getMessage()
            );
        return null;
    }


    private List<SemanticMessage> setupParameterError(String parameterName, List<SemanticMessage> semanticMessages) {
        String scope = parameterName != null ? "parametro " + parameterName : "output ";
        for (SemanticMessage semanticMessage : semanticMessages) {
            semanticMessage.setMessage(scope + " -> " + semanticMessage.getMessage());
        }
        return semanticMessages;
    }

    private SemanticMessage wrongParameterError(String[] parameters) {
        return SemanticMessageUtility.setUpBlockingMessage(
                ValidationCheck.WRONG_PARAMETER_TYPE_INVOCATION.getCode(),
                ValidationCheck.WRONG_PARAMETER_TYPE_INVOCATION.getMessage(),
                parameters
        );
    }
}
