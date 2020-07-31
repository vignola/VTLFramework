package com.capgemini.istat.vtlcompiler.vtlsemantic.service.result;

import com.capgemini.istat.vtlcompiler.vtlcommon.model.dataset.VtlComponent;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.dataset.VtlComponentRole;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.dataset.VtlDataset;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.dataset.VtlType;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.exception.ComponentNotFoundException;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.exception.UsingNotFoundException;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.exception.ValidationException;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.operator.Interaction;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.operator.Operator;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.semantic.ResultExpression;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.semantic.SemanticMessage;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.semantic.ValidationCheck;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.semantic.ValidationData;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.valuedomain.ValueDomain;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.constant.VtlConstant;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.constant.VtlInteger;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.dataset.VtlComponentId;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.dataset.VtlVarId;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.clause.pivot.VtlPivotClauseExpression;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.clause.unpivot.VtlPivotOrUnpivotClauseExpression;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.comparison.VtlInExpression;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.hierarchy.VtlHierarchyExpression;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.join.VtlJoinSelectClause;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.validation.VtlCheck;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.validation.VtlCheckDatapoint;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.validation.VtlCheckHierarchy;
import com.capgemini.istat.vtlcompiler.vtlcommon.repository.ValueDomainRepository;
import com.capgemini.istat.vtlcompiler.vtlcommon.service.ComponentUtilityService;
import com.capgemini.istat.vtlcompiler.vtlcommon.service.DatasetUtilityService;
import com.capgemini.istat.vtlcompiler.vtlsemantic.service.validation.DatasetSemanticValidationService;
import com.capgemini.istat.vtlcompiler.vtlsemantic.service.validation.SemanticMessageUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * la classe si occupa di effettuare tutti i passaggi della validazione e della trasformazione dei dataset.
 * Questa classe rappresenta il core del motore semantico per i dataset
 * Tutti i metodi della classe restituiscono un oggetto Result expression che contiene o il risultato della trasformazione semantica
 * o la lista degli errori che si sono incontrati durante la validazione del dato.
 *
 * @see DatasetUtilityService
 * @see ComponentUtilityService
 * @see DatasetSemanticValidationService
 * @see ResultExpression
 */
@Service
public class DatasetResultService {
    private DatasetUtilityService datasetUtilityService;
    private ComponentUtilityService componentUtilityService;
    private DatasetSemanticValidationService datasetSemanticValidationService;
    private ValueDomainRepository valueDomainRepository;

    @Autowired
    public void setDatasetUtilityService(DatasetUtilityService datasetUtilityService) {
        this.datasetUtilityService = datasetUtilityService;
    }

    @Autowired
    public void setDatasetSemanticValidationService(DatasetSemanticValidationService datasetSemanticValidationService) {
        this.datasetSemanticValidationService = datasetSemanticValidationService;
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
     * Data una costante viene generato un dataset che rappresenta la costante
     *
     * @param constant
     * @return
     */
    public ResultExpression createTemporaryScalarDataset(VtlConstant<?> constant) {
        ResultExpression resultExpression = new ResultExpression();
        resultExpression.setResult(datasetUtilityService.createScalarDataset(constant));
        return resultExpression;
    }

    /**
     * dato un nome del  dataset viene verificata la sua esistenza sul database e viene restituito
     *
     * @param vtlVarId
     * @return
     */
    public ResultExpression getDatasetByName(VtlVarId vtlVarId) {
        ResultExpression resultExpression = new ResultExpression();
        ValidationData validationData = new ValidationData();
        validationData.setValidationChecks(vtlVarId.getOperator().getValidationMap().get(Interaction.DATASET));
        validationData.setDatasetName(vtlVarId.getName());
        validationData.setIgnoreCase(vtlVarId.isIgnoreCase());
        validationData.setRequestUuid(vtlVarId.getRequestUuid());
        resultExpression.setMessages(datasetSemanticValidationService.validateSemantic(validationData));
        if (!SemanticMessageUtility.existBlockingMessage(resultExpression.getMessages()))
            resultExpression.setResult(datasetUtilityService.getDatasetByName(vtlVarId.getName(), vtlVarId.isIgnoreCase(), vtlVarId.getRequestUuid()));
        return resultExpression;
    }

    /**
     * Offre l'operazione di membership su un dataset
     *
     * @param vtlDataset
     * @param componentName
     * @param operator
     * @return
     */
    public ResultExpression createTemporaryDatasetFromComponentName(VtlDataset vtlDataset, String componentName, Operator operator) {
        ResultExpression result = new ResultExpression();
        ValidationData validationData = new ValidationData();
        validationData.getVtlDatasets().add(vtlDataset);
        validationData.setComponentName(componentName);
        validationData.setValidationChecks(operator.getValidationMap().get(Interaction.COMPONENT));
        result.setMessages(datasetSemanticValidationService.validateSemantic(validationData));
        if (!SemanticMessageUtility.existBlockingMessage(result.getMessages())) {
            result.setResult(datasetUtilityService.createDatasetFromComponentName(vtlDataset, componentName));
        }
        return result;
    }

    /**
     * Effettua la trasformazione semantica dell'operatore assign o persist sul dataset
     *
     * @param vtlDataset
     * @param datasetName
     * @param operator
     * @return
     */
    public ResultExpression assignDatasetName(VtlDataset vtlDataset, String datasetName, Operator operator) {
        ResultExpression resultExpression = new ResultExpression();
        ValidationData validationData = new ValidationData();
        validationData.getVtlDatasets().add(vtlDataset);
        validationData.setValidationChecks(operator.getValidationMap().get(Interaction.DATASET));
        resultExpression.setMessages(datasetSemanticValidationService.validateSemantic(validationData));
        if (!SemanticMessageUtility.existBlockingMessage(resultExpression.getMessages())) {
            VtlDataset temporaryDataset = datasetUtilityService.createTemporaryDataset(vtlDataset.getVtlComponents(), vtlDataset.isOnlyAScalar());

            VtlDataset vtlDatasetOld = datasetUtilityService.getDatasetByName(datasetName, true, vtlDataset.getRequestUuid());
            if (vtlDatasetOld != null) {
                //TODO fare controllo per operatore di persistenza
                resultExpression.getMessages().add(SemanticMessageUtility.setUpBlockingMessage(ValidationCheck.TEMPORARY_DATASET.getCode(), ValidationCheck.TEMPORARY_DATASET.getMessage()));
            }

            resultExpression.setResult(
                    datasetUtilityService.changeDatasetName(
                            temporaryDataset,
                            datasetName,
                            false));
        }
        return resultExpression;
    }

    /**
     * Effettua la trasformazione semantica dell'operatore group By della join
     *
     * @param vtlDataset
     * @param identifiers
     * @param operator
     * @return
     */
    public ResultExpression groupIdentifier(VtlDataset vtlDataset, List<VtlComponent> identifiers, Operator operator) {
        ResultExpression resultExpression = new ResultExpression();
        ValidationData validationData = new ValidationData();
        validationData.getVtlDatasets().add(vtlDataset);
        validationData.setValidationChecks(operator.getValidationMap().get(Interaction.DATASET));
        resultExpression.setMessages(datasetSemanticValidationService.validateSemantic(validationData));
        if (!SemanticMessageUtility.existBlockingMessage(resultExpression.getMessages())) {
            List<VtlComponent> identifiersToKeep = identifiers;
            if (operator == Operator.GROUP_EXCEPT) {
                identifiersToKeep = vtlDataset.getIdentifiers();
                for (VtlComponent vtlComponent : identifiers) {
                    identifiersToKeep = componentUtilityService.removeComponentWithName(identifiersToKeep, vtlComponent.getName());
                }
            }
            resultExpression.setResult(
                    datasetUtilityService.groupIdentifiers(
                            vtlDataset,
                            identifiersToKeep
                    )
            );
            return resultExpression;
        }

        return resultExpression;
    }

    /**
     * Offre il meccanismo di aggiunta componente tramite calc
     *
     * @param vtlDataset
     * @param vtlComponents
     * @return
     */
    public ResultExpression addComponentsToDataset(VtlDataset vtlDataset, List<VtlComponent> vtlComponents) {
        ResultExpression resultExpression = new ResultExpression();
        resultExpression.setMessages(new ArrayList<>());
        resultExpression.setResult(
                datasetUtilityService.addComponentToDataset(
                        vtlDataset,
                        vtlComponents
                )
        );
        return resultExpression;
    }

    /**
     * Offre il meccanismo di aggregation sul dataset
     *
     * @param vtlDataset
     * @param vtlComponents
     * @return
     */
    public ResultExpression aggregateComponentToDataset(VtlDataset vtlDataset, List<VtlComponent> vtlComponents) {
        ResultExpression resultExpression = new ResultExpression();
        resultExpression.setResult(
                datasetUtilityService.createDatasetFromMeasure(
                        vtlDataset,
                        vtlComponents,
                        false
                )
        );
        return resultExpression;
    }

    /**
     * Effettua la trasformazione semantica dell'operatore keep sul dataset
     *
     * @param vtlDataset
     * @param vtlComponents
     * @param operator
     * @return
     */
    public ResultExpression keepComponents(VtlDataset vtlDataset, List<VtlComponent> vtlComponents, Operator operator) {
        ResultExpression resultExpression = new ResultExpression();
        ValidationData validationData = new ValidationData();
        validationData.getVtlDatasets().add(vtlDataset);
        validationData.getVtlComponents().addAll(vtlComponents);
        validationData.setValidationChecks(operator.getValidationMap().get(Interaction.DATASET));
        resultExpression.setMessages(datasetSemanticValidationService.validateSemantic(validationData));
        if (!SemanticMessageUtility.existBlockingMessage(resultExpression.getMessages())) {
            resultExpression.setResult(
                    datasetUtilityService.keepDatasetComponent(
                            vtlDataset,
                            vtlComponents
                    )
            );
        }

        return resultExpression;
    }

    /**
     * Effettua la trasformazione semantica dell'operatore drop sul dataset
     *
     * @param vtlDataset
     * @param vtlComponents
     * @param operator
     * @return
     */
    public ResultExpression dropComponents(VtlDataset vtlDataset, List<VtlComponent> vtlComponents, Operator operator) {
        ResultExpression resultExpression = new ResultExpression();
        ValidationData validationData = new ValidationData();
        validationData.getVtlDatasets().add(vtlDataset);
        validationData.getVtlComponents().addAll(vtlComponents);
        validationData.setValidationChecks(operator.getValidationMap().get(Interaction.DATASET));
        resultExpression.setMessages(datasetSemanticValidationService.validateSemantic(validationData));
        if (!SemanticMessageUtility.existBlockingMessage(resultExpression.getMessages())) {
            resultExpression.setResult(
                    datasetUtilityService.dropDatasetComponents(
                            vtlDataset,
                            vtlComponents
                    )
            );
        }

        return resultExpression;
    }

    /**
     * Effettua la trasformazione semantica dell'operatore rename sul dataset
     *
     * @param vtlDataset
     * @param renameMap
     * @param operator
     * @return
     */
    public ResultExpression renameComponents(VtlDataset vtlDataset, Map<String, String> renameMap, Operator operator) {
        ResultExpression resultExpression = new ResultExpression();
        ValidationData validationData = new ValidationData();
        validationData.setValidationChecks(operator.getValidationMap().get(Interaction.DATASET));
        validationData.getVtlDatasets().add(vtlDataset);
        VtlDataset vtlDatasetRenamed = vtlDataset;
        for (Map.Entry<String, String> entry : renameMap.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            validationData.setComponentName(value);
            resultExpression.getMessages().addAll(datasetSemanticValidationService.validateSemantic(validationData));
            if (!SemanticMessageUtility.existBlockingMessage(resultExpression.getMessages())) {
                vtlDatasetRenamed =
                        datasetUtilityService.renameDatasetComponent(
                                vtlDatasetRenamed,
                                key,
                                value
                        );
            }
        }


        resultExpression.setResult(
                vtlDatasetRenamed
        );
        return resultExpression;
    }

    /**
     * Offre il meccanismo di sub sul dataset
     *
     * @param vtlDataset
     * @param subComponentNames
     * @param operator
     * @return
     */
    public ResultExpression subSpaceComponent(VtlDataset vtlDataset, List<String> subComponentNames, Operator operator) {
        ResultExpression resultExpression = new ResultExpression();
        ValidationData validationData = new ValidationData();
        validationData.setValidationChecks(operator.getValidationMap().get(Interaction.DATASET));
        validationData.getVtlDatasets().add(
                vtlDataset
        );
        VtlDataset vtlSubDataset = vtlDataset;
        for (String componentName : subComponentNames) {
            validationData.setComponentName(componentName);
            resultExpression.getMessages().addAll(datasetSemanticValidationService.validateSemantic(validationData));
        }
        for (String componentName : subComponentNames) {
            if (!SemanticMessageUtility.existBlockingMessage(resultExpression.getMessages())) {
                vtlSubDataset =
                        datasetUtilityService.removeComponentFromName(
                                vtlSubDataset,
                                componentName
                        );

            }
        }
        resultExpression.setResult(
                vtlSubDataset
        );
        return resultExpression;
    }

    /**
     * Effettua la trasformazione semantica dell'operatore Join Clause
     *
     * @param vtlDatasets
     * @param vtlJoinSelectClause
     * @param joinOperator
     * @return
     */
    public ResultExpression joinExpression(LinkedList<VtlDataset> vtlDatasets, VtlJoinSelectClause vtlJoinSelectClause, Operator joinOperator) {
        ResultExpression resultExpression = new ResultExpression();
        List<VtlComponentId> usingComponentIdentifiers = null;
        if (vtlJoinSelectClause.getVtlJoinUsing() != null) {
            usingComponentIdentifiers = vtlJoinSelectClause.getVtlJoinUsing().getComponentIds();
        }
        List<VtlComponent> identifiers = null;
        boolean usingExist = usingComponentIdentifiers != null;
        boolean usingCommon = false;
        boolean existAllUsingComponent = true;
        if (usingExist) {
            usingCommon = isAllCommonIdentifier(vtlDatasets, usingComponentIdentifiers);
            existAllUsingComponent = existAllUsingComponent(vtlDatasets, usingComponentIdentifiers);
        }
        try {
            if (existAllUsingComponent) {
                identifiers = getJoinIdentifiers(resultExpression, vtlDatasets, usingComponentIdentifiers, joinOperator, usingCommon);
            } else {
                resultExpression.getMessages().add(
                        SemanticMessageUtility.setUpBlockingMessage(
                                ValidationCheck.USING_COMPONENT_NOT_EXIST.getCode(),
                                ValidationCheck.USING_COMPONENT_NOT_EXIST.getMessage())
                );
            }
        } catch (ComponentNotFoundException componentNotFoundException) {
            String[] parameters = new String[1];
            parameters[0] = componentNotFoundException.getMessage();
            resultExpression.getMessages().add(
                    SemanticMessageUtility.setUpBlockingMessage(ValidationCheck.USING_COMPONENT.getCode(),
                            ValidationCheck.USING_COMPONENT.getMessage(), parameters));
        } catch (UsingNotFoundException usingNotFoundException) {
            resultExpression.getMessages().add(
                    SemanticMessageUtility.setUpBlockingMessage(ValidationCheck.USING_MANDATORY.getCode(),
                            ValidationCheck.USING_MANDATORY.getMessage()));
        }
        if (resultExpression.getMessages().isEmpty()) {
            resultExpression.setResult(
                    datasetUtilityService.joinDataset(vtlDatasets, identifiers)
            );
        }
        vtlJoinSelectClause.setSupersetIdentifiers(identifiers);
        return resultExpression;
    }

    /**
     * Effettua la trasformazione semantica dell'operatore join per gli identificativi. Sono presenti tutti i controlli pregressi per poter effettuare
     * l'operazione di join, viene scelto il dataset candidato e trovati gli identificativi da utilizzare nel dataset di risultato
     *
     * @param resultExpression             a quest'oggetto verranno aggiunti tutti i passaggi intermedi ed eventuali errori di validazione
     * @param vtlDatasets                  la lista di dataset a cui fare la join
     * @param usingComponentIdentifiers    i componenti nella using(se presenti=
     * @param joinOperator                 l'operatore di join coninvolto(Left, right ecc)
     * @param usingIsOnlyCommonIdentifiers definisce se la using è usata solo su identificativi comuni(serve per il meccanismo di elevazione del dataset candidato)
     * @return una lista di componenti che saranno gli identifiers del dataset in join
     * @throws ComponentNotFoundException
     * @throws UsingNotFoundException
     */
    public List<VtlComponent> getJoinIdentifiers(ResultExpression resultExpression, LinkedList<VtlDataset> vtlDatasets,
                                                 List<VtlComponentId> usingComponentIdentifiers, Operator joinOperator,
                                                 boolean usingIsOnlyCommonIdentifiers) throws ComponentNotFoundException, UsingNotFoundException {
        boolean usingExist = usingComponentIdentifiers != null;
        List<VtlComponent> identifiers = null;
        switch (joinOperator) {
            case INNER_JOIN: {
                boolean existSupersetIdentifier = datasetUtilityService.existSupersetDatasets(vtlDatasets);
                if (usingExist) {
                    if (existSupersetIdentifier) {
                        if (usingIsOnlyCommonIdentifiers) {
                            identifiers = componentUtilityService.getSupersetIdentifiers(vtlDatasets);
                        } else {
                            resultExpression.getMessages().add(
                                    SemanticMessageUtility.setUpBlockingMessage(ValidationCheck.HAS_COMMON_SUPERSET.getCode(), ValidationCheck.HAS_COMMON_SUPERSET.getMessage()));
                        }
                    } else {
                        //Possibile predizionPromozione del dataset
                        identifiers = elevateDatasetCandidate(
                                resultExpression,
                                datasetUtilityService.getDatasetCandidate(vtlDatasets, usingComponentIdentifiers),
                                vtlDatasets,
                                usingComponentIdentifiers
                        );
                    }
                } else {
                    //Deve esistere un superset altrimenti errore
                    if (existSupersetIdentifier) {
                        identifiers = componentUtilityService.getSupersetIdentifiers(vtlDatasets);
                    } else {
                        resultExpression.getMessages().add(
                                SemanticMessageUtility.setUpBlockingMessage(ValidationCheck.HAS_COMMON_SUPERSET.getCode(), ValidationCheck.HAS_COMMON_SUPERSET.getMessage()));
                    }
                }
                break;
            }
            case LEFT_JOIN: {
                if (usingExist) {
                    if (datasetUtilityService.hasSameIdentifiers(vtlDatasets)) {
                        if (usingIsOnlyCommonIdentifiers) {
                            identifiers = componentUtilityService.getSupersetIdentifiers(vtlDatasets);
                        } else {
                            resultExpression.getMessages().add(
                                    SemanticMessageUtility.setUpBlockingMessage(ValidationCheck.HAS_COMMON_SUPERSET.getCode(), ValidationCheck.HAS_COMMON_SUPERSET.getMessage()));
                        }
                    } else {
                        //dataset da candidare o superset
                        List<VtlDataset> vtlDatasetsToCandidate = new ArrayList<>();
                        vtlDatasetsToCandidate.add(vtlDatasets.getFirst());
                        identifiers = elevateDatasetCandidate(
                                resultExpression,
                                vtlDatasets.getFirst(),
                                vtlDatasets,
                                usingComponentIdentifiers
                        );
                    }
                } else {
                    if (datasetUtilityService.hasSameIdentifiers(vtlDatasets)) {
                        identifiers = componentUtilityService.getCommonIdentifiers(vtlDatasets);
                    } else {
                        resultExpression.getMessages().add(
                                SemanticMessageUtility.setUpBlockingMessage(ValidationCheck.HAS_COMMON_IDENTIFICATION.getCode(), ValidationCheck.HAS_COMMON_IDENTIFICATION.getMessage())
                        );
                    }
                }
                break;
            }
            case FULL_JOIN: {
                if (!datasetUtilityService.hasSameIdentifiers(vtlDatasets)) {
                    resultExpression.getMessages().add(
                            SemanticMessageUtility.setUpBlockingMessage(ValidationCheck.HAS_COMMON_IDENTIFICATION.getCode(), ValidationCheck.HAS_COMMON_IDENTIFICATION.getMessage()));
                } else {
                    identifiers = componentUtilityService.getCommonIdentifiers(vtlDatasets);
                }
                break;
            }
            case CROSS_JOIN: {
                identifiers = componentUtilityService.getCrossIdentifiers(vtlDatasets);
                break;
            }
            default: {
                throw new ComponentNotFoundException("Non è stato trovato nessun identificativo");
            }
        }

        return identifiers;
    }

    /**
     * Effettua la trasformazione semantica dell'operatore pulizio dei componenti doppi sul dataset temporaneo
     *
     * @param vtlDataset
     * @return
     */
    public ResultExpression removeRedundantNames(VtlDataset vtlDataset) {
        ResultExpression resultExpression = new ResultExpression();
        VtlDataset vtlDatasetResult = datasetUtilityService.removeRedundantNames(vtlDataset);
        if (vtlDatasetResult == null) {
            String[] parameters = new String[1];
            parameters[0] = getSharpedComponentNames(vtlDataset);
            resultExpression.getMessages().add(
                    SemanticMessageUtility.setUpBlockingMessage(ValidationCheck.HAS_DUPLICATE_MEASURES.getCode(),
                            ValidationCheck.HAS_DUPLICATE_MEASURES.getMessage(), parameters));
        } else {
            resultExpression.setResult(vtlDatasetResult);
        }
        return resultExpression;
    }

    /**
     * restituisce una stringa contenente i nomi dei componenti che contengono lo sharp
     *
     * @param vtlDataset
     * @return
     */
    private String getSharpedComponentNames(VtlDataset vtlDataset) {
        String result = "";
        for (VtlComponent vtlComponent : vtlDataset.getVtlComponents()) {
            if (vtlComponent.getName().contains("#")) {
                result = result + "  " + vtlComponent.getName();
            }
        }
        return result;
    }

    private List<VtlComponent> elevateDatasetCandidate(ResultExpression resultExpression,
                                                       VtlDataset datasetToElevate,
                                                       List<VtlDataset> vtlDatasets,
                                                       List<VtlComponentId> usingComponentIdentifiers) {
        List<VtlComponent> identifiers = null;
        if (datasetToElevate != null) {
            if (datasetUtilityService.isAllIdentifiersInOtherDataset(
                    vtlDatasets, datasetToElevate, usingComponentIdentifiers)) {
                if (datasetUtilityService.isUsingAllIdentifiersInOtherDataset(vtlDatasets, datasetToElevate, usingComponentIdentifiers)) {
                    identifiers = datasetToElevate.getIdentifiers();
                } else {
                    resultExpression.getMessages().add(
                            SemanticMessageUtility.setUpBlockingMessage(
                                    ValidationCheck.USING_NOT_ALL_OTHER_IDENTIFIERS.getCode(),
                                    ValidationCheck.USING_NOT_ALL_OTHER_IDENTIFIERS.getMessage()));
                }
            } else {
                resultExpression.getMessages().add(
                        SemanticMessageUtility.setUpBlockingMessage(
                                ValidationCheck.USING_NOT_IDENTIFIERS.getCode(),
                                ValidationCheck.USING_NOT_IDENTIFIERS.getMessage()));
            }
        } else {
            resultExpression.getMessages().add(
                    SemanticMessageUtility.setUpBlockingMessage(
                            ValidationCheck.NO_CANDIDATE_TO_DATASET.getCode(),
                            ValidationCheck.NO_CANDIDATE_TO_DATASET.getMessage()));
        }
        return identifiers;
    }

    /**
     * Effettua la trasformazione semantica dell'operatore count
     *
     * @param requestUuid
     * @return
     */
    public ResultExpression countExpr(UUID requestUuid) {
        ResultExpression resultExpression = new ResultExpression();
        VtlInteger vtlInteger = new VtlInteger();
        vtlInteger.setRequestUuid(requestUuid);
        resultExpression.setResult(datasetUtilityService.createScalarDataset(vtlInteger));
        return resultExpression;
    }

    /**
     * Offre la dinamica di applicazione della maggior parte degli operatori binari
     *
     * @param vtlDatasetLeft
     * @param vtlDatasetRight
     * @param operator
     * @return
     */
    public ResultExpression createTemporaryDatasetFromBinaryExpression(VtlDataset vtlDatasetLeft, VtlDataset vtlDatasetRight,
                                                                       Operator operator) {
        ResultExpression result;
        ValidationData validationData = new ValidationData();
        validationData.getVtlDatasets().add(vtlDatasetLeft);
        validationData.getVtlDatasets().add(vtlDatasetRight);

        result = setBinaryValidation(vtlDatasetLeft, vtlDatasetRight, validationData, operator);
        if (!SemanticMessageUtility.existBlockingMessage(result.getMessages())) {
            if (operator.getSubstituteComponent()) {
                result.setResult(datasetUtilityService.applySubstitutionToDataset(result.getResult(), operator));
            } else if (operator.getExpectedTypeReturn() != VtlType.NONE) {
                result.setResult(datasetUtilityService.doCast(result.getResult(), operator.getExpectedTypeReturn()));
            }
        }
        return result;
    }

    /**
     * Effettua la trasformazione semantica dell'operatore applicazione della maggior parte degli operatori unari
     *
     * @param vtlDataset
     * @param operator
     * @param addViral
     * @return
     */
    public ResultExpression createTemporaryDatasetFromUnaryExpression(VtlDataset vtlDataset, Operator operator, boolean addViral) {
        ResultExpression result = new ResultExpression();
        ValidationData validationData = new ValidationData();
        validationData.getVtlDatasets().add(vtlDataset);
        if (vtlDataset.isOnlyAScalar()) {
            validationData.setValidationChecks(operator.getValidationMap().get(Interaction.SCALAR));
            result.setMessages(datasetSemanticValidationService.validateSemantic(validationData));
            if (!SemanticMessageUtility.existBlockingMessage(result.getMessages())) {
                result.setResult(datasetUtilityService.applySubstitutionToScalar(operator, vtlDataset.getMeasures().get(0).getType(), vtlDataset.getRequestUuid()));
            }
        } else { // E' un vtlDataset completo
            validationData.setValidationChecks(operator.getValidationMap().get(Interaction.DATASET));
            result.setMessages(datasetSemanticValidationService.validateSemantic(validationData));
            if (!SemanticMessageUtility.existBlockingMessage(result.getMessages())) {
                result.setResult(datasetUtilityService.applyOperatorToDataset(vtlDataset, operator, addViral));
            }
        }
        if (!SemanticMessageUtility.existBlockingMessage(result.getMessages()) && operator.getExpectedTypeReturn() != VtlType.NONE) {
            result.setResult(datasetUtilityService.doCast(result.getResult(), operator.getExpectedTypeReturn()));
        }
        return result;
    }

    /**
     * Effettua la trasformazione semantica dell'operatore cast
     *
     * @param vtlDataset
     * @param vtlType
     * @return
     */
    public ResultExpression doCast(VtlDataset vtlDataset, VtlType vtlType) {
        String[] parameters;
        ResultExpression resultExpression = new ResultExpression();
        if (vtlDataset.getMeasures().size() != 1) {
            parameters = new String[1];
            parameters[0] = vtlType.getValueType();
            resultExpression.getMessages().add(
                    SemanticMessageUtility.setUpBlockingMessage(ValidationCheck.HAS_ONLY_ONE_MEASURE_TYPE.getCode(),
                            ValidationCheck.HAS_ONLY_ONE_MEASURE_TYPE.getMessage(), parameters));
        } else {
            VtlDataset resultDataset = datasetUtilityService.doCast(vtlDataset, vtlType);
            if (resultDataset == null) {
                parameters = new String[1];
                parameters[0] = vtlType.getValueType();
                resultExpression.getMessages().add(
                        SemanticMessageUtility.setUpBlockingMessage(ValidationCheck.NOT_ALLOWED_CAST.getCode(),
                                ValidationCheck.NOT_ALLOWED_CAST.getMessage(), parameters));
            } else {
                resultExpression.setResult(
                        datasetUtilityService.doCast(vtlDataset, vtlType)
                );
            }
        }
        return resultExpression;
    }

    /**
     * Effettua la trasformazione semantica dell'operatore controllo sui parametri delle funzioni
     *
     * @param vtlDataset
     * @param operator
     * @return
     */
    public List<SemanticMessage> checkParameter(VtlDataset vtlDataset, Operator operator) {
        List<SemanticMessage> result;
        ValidationData validationData = new ValidationData();
        validationData.getVtlDatasets().add(vtlDataset);
        if (vtlDataset.isOnlyAScalar()) {
            validationData.setValidationChecks(operator.getValidationMap().get(Interaction.SCALAR));
            result = datasetSemanticValidationService.validateSemantic(validationData);

        } else { // E' un vtlDataset completo
            validationData.setValidationChecks(operator.getValidationMap().get(Interaction.DATASET));
            result = datasetSemanticValidationService.validateSemantic(validationData);
        }
        return result;
    }

    /**
     * Verifica l'esistenza di un component e lo restituisce
     *
     * @param vtlDataset
     * @param componentName
     * @param operator
     * @param ignoreCase
     * @return
     */
    public ResultExpression getComponent(VtlDataset vtlDataset, String componentName, Operator operator, boolean ignoreCase) {
        ResultExpression resultExpression = new ResultExpression();
        ValidationData validationData = new ValidationData();
        validationData.setValidationChecks(operator.getValidationMap().get(Interaction.COMPONENT));
        validationData.getVtlDatasets().add(vtlDataset);
        validationData.setComponentName(componentName);
        validationData.setIgnoreCase(ignoreCase);
        resultExpression.setMessages(datasetSemanticValidationService.validateSemantic(validationData));
        if (!SemanticMessageUtility.existBlockingMessage(resultExpression.getMessages())) {
            resultExpression.setResultComponent(
                    componentUtilityService.getComponentFromName(
                            vtlDataset.getVtlComponents(),
                            componentName,
                            operator == Operator.COMPONENT
                    )
            );
        }
        return resultExpression;
    }

    /**
     * Preleva un componente da un dataset. Il componente possiede uno # all'interno del nome come per la membership che viene ignorato
     *
     * @param vtlDataset
     * @param componentName
     * @param datasetInClauseName
     * @param operator
     * @return
     */
    public ResultExpression getComponentMembershipInClause(VtlDataset vtlDataset,
                                                           String componentName,
                                                           String datasetInClauseName,
                                                           Operator operator) {
        ResultExpression resultExpression = new ResultExpression();
        ValidationData validationData = new ValidationData();

        validationData.setValidationChecks(operator.getValidationMap().get(Interaction.COMPONENT));
        validationData.getVtlDatasets().add(vtlDataset);
        validationData.setDatasetName(datasetInClauseName);
        validationData.setComponentName(componentName);
        resultExpression.setMessages(datasetSemanticValidationService.validateSemantic(validationData));
        if (!SemanticMessageUtility.existBlockingMessage(resultExpression.getMessages())) {
            resultExpression.setResultComponent(
                    componentUtilityService.getComponentFromName(
                            vtlDataset.getVtlComponents(),
                            componentName,
                            true
                    )
            );
        }
        return resultExpression;
    }

    /**
     * Costruisce un messaggio di errore a seguito del check dei parametri
     *
     * @param resultExpression
     * @param operator
     * @return
     * @throws ValidationException
     */
    public ResultExpression checkParameter(ResultExpression resultExpression, Operator operator) throws ValidationException {
        resultExpression
                .setMessages(
                        checkParameter(
                                resultExpression.getResult(),
                                operator
                        )
                );

        SemanticMessageUtility.processSemanticMessage(resultExpression);
        return resultExpression;
    }

    /**
     * Rimuove gli identificativi da un dataset
     *
     * @param vtlDataset
     * @return
     */
    public ResultExpression removeIdentifiers(VtlDataset vtlDataset) {
        ResultExpression resultExpression = new ResultExpression();
        List<VtlComponent> vtlComponents = new ArrayList<>();
        vtlComponents.addAll(vtlDataset.getMeasures());
        vtlComponents.addAll(vtlDataset.getViral());
        resultExpression.setResult(
                datasetUtilityService.createTemporaryDataset(vtlComponents, vtlDataset.isOnlyAScalar())
        );
        return resultExpression;
    }

    /**
     * Effettua la trasformazione semantica dell'operatore check Datapoint
     *
     * @param vtlCheckDatapoint
     * @return
     */
    public ResultExpression checkDatapoint(VtlCheckDatapoint vtlCheckDatapoint) {
        ResultExpression resultExpression = new ResultExpression();
        resultExpression.setResult(datasetUtilityService.checkValidation(
                vtlCheckDatapoint.getDatasetToCheck().getResultExpression().getResult(),
                null,
                vtlCheckDatapoint.getOutputMode(),
                true,
                false)
        );
        return resultExpression;
    }

    /**
     * Effettua la trasformazione semantica dell'operatore checkHierarchy
     *
     * @param vtlCheckHierarchy
     * @param operator
     * @return
     */
    public ResultExpression checkHierarchicalRuleset(VtlCheckHierarchy vtlCheckHierarchy, Operator operator) {
        ResultExpression resultExpression = new ResultExpression();
        ValidationData validationData = new ValidationData();

        validationData.setValidationChecks(operator.getValidationMap().get(Interaction.DATASET));
        validationData.getVtlDatasets().add(vtlCheckHierarchy.getVtlVarId().getResultExpression().getResult());
        resultExpression.setMessages(datasetSemanticValidationService.validateSemantic(validationData));
        if (!SemanticMessageUtility.existBlockingMessage(resultExpression.getMessages())) {
            resultExpression.setResult(
                    datasetUtilityService.checkValidation(
                            vtlCheckHierarchy.getVtlVarId().getResultExpression().getResult(),
                            null,
                            vtlCheckHierarchy.getOutputMode(),
                            true,
                            true
                    )
            );
        }
        return resultExpression;
    }

    /**
     * Effettua la trasformazione semantica dell'operatore check validation
     *
     * @param vtlCheck
     * @param vtlType
     * @param operator
     * @return
     */
    public ResultExpression checkValidation(VtlCheck vtlCheck, VtlType vtlType, Operator operator) {
        ResultExpression resultExpression = new ResultExpression();
        ValidationData validationData = new ValidationData();
        validationData.setValidationChecks(operator.getValidationMap().get(Interaction.DATASET));
        validationData.getVtlDatasets().add(vtlCheck.getBooleanDataset().getResultExpression().getResult());
        resultExpression.setMessages(datasetSemanticValidationService.validateSemantic(validationData));
        if (!SemanticMessageUtility.existBlockingMessage(resultExpression.getMessages())) {
            resultExpression.setResult(
                    datasetUtilityService.checkValidation(
                            vtlCheck.getBooleanDataset().getResultExpression().getResult(),
                            vtlType,
                            vtlCheck.getOutputMode(),
                            false,
                            true
                    )
            );
        }
        return resultExpression;
    }

    /**
     * Effettua la trasformazione semantica dell'operatore hierarchy
     *
     * @param vtlHierarchyExpression
     * @param operator
     * @return
     */
    public ResultExpression hierarchy(VtlHierarchyExpression vtlHierarchyExpression, Operator operator) {
        ResultExpression resultExpression = new ResultExpression();
        ValidationData validationData = new ValidationData();
        validationData.setValidationChecks(operator.getValidationMap().get(Interaction.DATASET));
        validationData.getVtlDatasets().add(vtlHierarchyExpression.getVtlOperandExpression().getResultExpression().getResult());
        resultExpression.setMessages(datasetSemanticValidationService.validateSemantic(validationData));
        if (!SemanticMessageUtility.existBlockingMessage(resultExpression.getMessages())) {
            List<VtlComponent> components = new ArrayList<>();
            components.addAll(vtlHierarchyExpression.getVtlOperandExpression().getResultExpression().getResult().getIdentifiers());
            components.addAll(vtlHierarchyExpression.getVtlOperandExpression().getResultExpression().getResult().getMeasures());
            components.addAll(vtlHierarchyExpression.getVtlOperandExpression().getResultExpression().getResult().getViral());

            resultExpression.setResult(
                    datasetUtilityService.createTemporaryDataset(
                            components,
                            false
                    )
            );
        }
        return resultExpression;
    }

    /**
     * Effettua la trasformazione semantica dell'operatore unpivot
     *
     * @param vtlUnpivotClauseExpression
     * @return
     */
    public ResultExpression unpivot(VtlPivotOrUnpivotClauseExpression vtlUnpivotClauseExpression) {
        ResultExpression resultExpression = new ResultExpression();
        ValidationData validationData = new ValidationData();
        validationData.setValidationChecks(vtlUnpivotClauseExpression.getOperator().getValidationMap().get(Interaction.DATASET));
        validationData.setValueDomain(vtlUnpivotClauseExpression.getVtlDataset().getResultExpression().getResult().getMeasures().get(0).getDomainValue());
        validationData.setVtlComponentRole(VtlComponentRole.MEASURE);
        validationData.getVtlDatasets().add(vtlUnpivotClauseExpression.getVtlDataset().getResultExpression().getResult());
        resultExpression.setMessages(datasetSemanticValidationService.validateSemantic(validationData));
        if (!SemanticMessageUtility.existBlockingMessage(resultExpression.getMessages())) {
            try {
                resultExpression.setResult(
                        datasetUtilityService.unpivot(
                                vtlUnpivotClauseExpression.getVtlDataset().getResultExpression().getResult(),
                                vtlUnpivotClauseExpression.getIdentifier(),
                                vtlUnpivotClauseExpression.getMeasure()
                        )
                );
            } catch (ValidationException e) {
                resultExpression.getMessages().add(
                        SemanticMessageUtility.setUpBlockingMessage(
                                ValidationCheck.COMPONENT_NAME_YET_USED.getCode(),
                                ValidationCheck.COMPONENT_NAME_YET_USED.getMessage()
                        )
                );
            }
        }
        return resultExpression;
    }

    /**
     * Effettua la trasformazione semantica dell'operatore pivot
     *
     * @param vtlPivotClauseExpression
     * @return
     */
    public ResultExpression pivot(VtlPivotClauseExpression vtlPivotClauseExpression) {
        ResultExpression resultExpression = new ResultExpression();
        ValidationData validationData = new ValidationData();
        validationData.setValidationChecks(vtlPivotClauseExpression.getOperator().getValidationMap().get(Interaction.DATASET));
        validationData.setValueDomain(vtlPivotClauseExpression.getMeasure().getResultExpression().getResultComponent().getDomainValue());
        validationData.setVtlComponentRole(VtlComponentRole.MEASURE);
        validationData.getVtlDatasets().add(vtlPivotClauseExpression.getVtlDataset().getResultExpression().getResult());
        resultExpression.setMessages(datasetSemanticValidationService.validateSemantic(validationData));
        if (!SemanticMessageUtility.existBlockingMessage(resultExpression.getMessages())) {
            try {
                resultExpression.setResult(
                        datasetUtilityService.pivot(
                                vtlPivotClauseExpression.getVtlDataset().getResultExpression().getResult(),
                                vtlPivotClauseExpression.getIdentifier(),
                                vtlPivotClauseExpression.getMeasure(),
                                vtlPivotClauseExpression.getConstantExpressions()
                        )
                );
            } catch (ValidationException e) {
                resultExpression.getMessages().add(
                        SemanticMessageUtility.setUpBlockingMessage(
                                ValidationCheck.COMPONENT_NAME_YET_USED.getCode(),
                                ValidationCheck.COMPONENT_NAME_YET_USED.getMessage()
                        )
                );
            }
        }
        return resultExpression;
    }

    /**
     * Verifica la correttezza semantica delle ValueIn Expression
     *
     * @param vtlExpression
     * @return
     */
    public ResultExpression getScalarValueDomain(VtlInExpression vtlExpression) {
        ResultExpression resultExpression = new ResultExpression();
        List<ValueDomain> valueDomains = valueDomainRepository.getAllByValueDomainNameIgnoreCase(vtlExpression.getValueDomain());
        if (valueDomains.isEmpty()) {
            resultExpression.getMessages().add(SemanticMessageUtility.setUpBlockingMessage(ValidationCheck.VALUEDOMAIN_NOT_FOUND.getCode(), ValidationCheck.VALUEDOMAIN_NOT_FOUND.getMessage()));
        } else {

            VtlComponent vtlComponent = vtlExpression.getVtlElement().getResultExpression().getResultComponent() != null ? vtlExpression.getVtlElement().getResultExpression().getResultComponent() : vtlExpression.getVtlElement().getResultExpression().getResult().getMeasures().get(0);
            if (vtlComponent == null) {
                resultExpression.getMessages().add(SemanticMessageUtility.setUpBlockingMessage(ValidationCheck.COMPONENT_NOT_EXISTS.getCode(), ValidationCheck.COMPONENT_NOT_EXISTS.getMessage()));
            } else {
                List<VtlComponent> components = new ArrayList<>();
                components.add(componentUtilityService.getDefaultComponent(vtlComponent.getType(), vtlExpression.getRequestUuid()));
                resultExpression.setResult(datasetUtilityService.createTemporaryDataset(components, true));
            }
        }
        return resultExpression;
    }

    /**
     * Effettua la trasformazione semantica dell'operatore existIn
     *
     * @param vtlDatasetLeft
     * @param vtlDatasetRight
     * @param operator
     * @return
     */
    public ResultExpression existInResult(VtlDataset vtlDatasetLeft, VtlDataset vtlDatasetRight,
                                          Operator operator) {
        ResultExpression result;
        ValidationData validationData = new ValidationData();
        validationData.getVtlDatasets().add(vtlDatasetLeft);
        validationData.getVtlDatasets().add(vtlDatasetRight);

        result = setBinaryValidation(vtlDatasetLeft, vtlDatasetRight, validationData, operator);
        if (!SemanticMessageUtility.existBlockingMessage(result.getMessages())) {
            result.setResult(datasetUtilityService.existInDataset(vtlDatasetLeft, vtlDatasetRight));
        }
        return result;
    }

    /**
     * Offre il meccanismo di validazione per la maggior parte degli operatori binari(matematiche, concatenazioni ecc)
     *
     * @param vtlDatasetLeft
     * @param vtlDatasetRight
     * @param validationData
     * @param operator
     * @return
     */
    private ResultExpression setBinaryValidation(VtlDataset vtlDatasetLeft, VtlDataset vtlDatasetRight,
                                                 ValidationData validationData, Operator operator) {
        ResultExpression result = new ResultExpression();
        if (vtlDatasetLeft.isOnlyAScalar() && vtlDatasetRight.isOnlyAScalar()) {
            // scenario ad dizione scalare controllo number
            validationData.setValidationChecks(operator.getValidationMap().get(Interaction.SCALAR_TO_SCALAR));
            result.setMessages(datasetSemanticValidationService.validateSemantic(validationData));
            if (!SemanticMessageUtility.existBlockingMessage(result.getMessages())) {
                result.setResult(datasetUtilityService.mergeScalarWithScalar(vtlDatasetLeft, vtlDatasetRight));
            }
        } else if (vtlDatasetLeft.isOnlyAScalar()) {
            // scenario scalare + dataset
            validationData.setValidationChecks(operator.getValidationMap().get(Interaction.SCALAR_TO_DATASET));
            validationData.setVtlComponentRole(VtlComponentRole.MEASURE);
            result.setMessages(datasetSemanticValidationService.validateSemantic(validationData));
            if (!SemanticMessageUtility.existBlockingMessage(result.getMessages())) {
                result.setResult(datasetUtilityService.mergeDatasetWithScalar(vtlDatasetRight, vtlDatasetLeft.getMeasures().get(0)));
            }
        } else if (vtlDatasetRight.isOnlyAScalar()) {
            // scenario dataset + scalare
            validationData.setValidationChecks(operator.getValidationMap().get(Interaction.SCALAR_TO_DATASET));
            validationData.setVtlComponentRole(VtlComponentRole.MEASURE);
            result.setMessages(datasetSemanticValidationService.validateSemantic(validationData));
            if (!SemanticMessageUtility.existBlockingMessage(result.getMessages())) {
                result.setResult(datasetUtilityService.mergeDatasetWithScalar(vtlDatasetLeft, vtlDatasetRight.getMeasures().get(0)));
            }

        } else {
            // dataset + dataset
            validationData.setValidationChecks(operator.getValidationMap().get(Interaction.DATASET_TO_DATASET));
            result.setMessages(datasetSemanticValidationService.validateSemantic(validationData));
            if (!SemanticMessageUtility.existBlockingMessage(result.getMessages())) {
                result.setResult(datasetUtilityService.mergeDatasetWithDataset(vtlDatasetLeft, vtlDatasetRight));
            }
        }
        return result;
    }

    /**
     * Effettua la trasformazione semantica dell'operatore currentDate
     *
     * @param requestUuid
     * @return
     */
    public ResultExpression currentDate(UUID requestUuid) {
        VtlComponent vtlComponent = componentUtilityService.getDefaultComponent(VtlType.DATE, requestUuid);
        List<VtlComponent> vtlComponentList = new ArrayList<>();
        vtlComponentList.add(vtlComponent);
        ResultExpression resultExpression = new ResultExpression();
        resultExpression.setResult(
                datasetUtilityService.createTemporaryDataset(vtlComponentList, true)
        );
        return resultExpression;
    }

    private boolean isAllCommonIdentifier(List<VtlDataset> vtlDatasets, List<VtlComponentId> usingComponentIdentifiers) {
        List<VtlComponent> commonIdentifiers = componentUtilityService.getCommonIdentifiers(vtlDatasets);
        return componentUtilityService.foundAllComponentByComponentId(commonIdentifiers, usingComponentIdentifiers);
    }

    private boolean existAllUsingComponent(List<VtlDataset> vtlDatasets, List<VtlComponentId> usingComponentIdentifiers) {
        boolean foundAll = true;
        for (VtlDataset vtlDataset : vtlDatasets) {
            foundAll = foundAll && componentUtilityService.foundAllComponentByComponentId(vtlDataset.getVtlComponents(), usingComponentIdentifiers);
        }
        return foundAll;
    }

}
