package com.capgemini.istat.vtlcompiler.vtlsemantic.service.result;

import com.capgemini.istat.vtlcompiler.vtlcommon.model.KeyVariables;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.dataset.VtlComponent;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.dataset.VtlComponentRole;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.dataset.VtlDataset;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.dataset.VtlType;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.exception.ValidationException;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.operator.Operator;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.semantic.ResultExpression;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.semantic.ValidationCheck;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.constant.VtlConstant;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.constant.VtlConstantExpression;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.dataset.VtlVarId;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.define.operator.VtlUserFunction;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.define.operator.datatype.VtlDataType;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.define.operator.datatype.VtlScalarType;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.define.ruleset.datapoint.VtlDatapointRuleset;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.define.ruleset.hierarchical.VtlHierarchicalRuleset;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.VtlExpression;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.clause.pivot.VtlPivotClauseExpression;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.clause.unpivot.VtlPivotOrUnpivotClauseExpression;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.comparison.VtlInExpression;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.generic.VtlCallExpression;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.generic.VtlCastExpression;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.grouping.VtlHavingClauseExpression;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.hierarchy.VtlHierarchyExpression;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.join.VtlJoinSelectClause;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.join.VtlJoinSelectClauseItem;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.validation.VtlCheck;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.validation.VtlCheckDatapoint;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.validation.VtlCheckHierarchy;
import com.capgemini.istat.vtlcompiler.vtlsemantic.service.validation.SemanticMessageUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Questa classe funge da interfaccia per tutti i nodi del validatore semantico.
 * La logica che contraddistingue la maggior parte dei metodi è quella di riconoscimento dello scenario di dataset o component
 * e attiva il servizio con la logica necessaria a svolgere l'operazione
 * Tutti i metodi della classe restituiscono un oggetto Result expression che contiene o il risultato della trasformazione semantica
 * o la lista degli errori che si sono incontrati durante la validazione del dato.
 *
 * @see DatasetResultService
 * @see ComponentResultService
 * @see UserFunctionResultService
 */
@Service
public class FunctionResultService {

    private DatasetResultService datasetResultService;
    private ComponentResultService componentResultService;
    private UserFunctionResultService userFunctionResultService;

    @Autowired
    public void setDatasetResultService(DatasetResultService datasetResultService) {
        this.datasetResultService = datasetResultService;
    }

    @Autowired
    public void setComponentResultService(ComponentResultService componentResultService) {
        this.componentResultService = componentResultService;
    }

    @Autowired
    public void setUserFunctionResultService(UserFunctionResultService userFunctionResultService) {
        this.userFunctionResultService = userFunctionResultService;
    }

    /**
     * Applica una funzione binaria
     *
     * @param resultLeft
     * @param resultRight
     * @param operator
     * @return
     */
    public ResultExpression getBinaryResult(ResultExpression resultLeft, ResultExpression resultRight, Operator operator) {
        ResultExpression resultExpression = null;
        if (resultLeft.isAComponent() || resultRight.isAComponent()) {
            resultExpression =
                    componentResultService.createTemporaryComponentFromBinaryExpression(
                            resultLeft.getResultComponent(),
                            resultRight.getResultComponent(),
                            operator
                    );
        } else {
            resultExpression =
                    datasetResultService.createTemporaryDatasetFromBinaryExpression(
                            resultLeft.getResult(),
                            resultRight.getResult(),
                            operator
                    );


        }
        return resultExpression;
    }

    /**
     * applica una funzione unaria
     *
     * @param resultExpression
     * @param operator
     * @return
     */
    public ResultExpression getUnaryResult(ResultExpression resultExpression, Operator operator) {
        ResultExpression result = null;
        if (resultExpression.isAComponent()) {
            result =
                    componentResultService.createTemporaryComponentFromUnaryExpression(
                            resultExpression.getResultComponent(),
                            operator
                    );

        } else {
            result =
                    datasetResultService.createTemporaryDatasetFromUnaryExpression(
                            resultExpression.getResult(),
                            operator,
                            true
                    );

        }
        return result;
    }

    /**
     * effettua un operazione di aggregazione
     *
     * @param resultExpression
     * @param operator
     * @return
     */
    public ResultExpression getAggrResult(ResultExpression resultExpression, Operator operator) {
        ResultExpression result = null;
        if (resultExpression.isAComponent()) {
            result =
                    componentResultService.createTemporaryComponentFromUnaryExpression(
                            resultExpression.getResultComponent(),
                            operator
                    );

        } else {
            result =
                    datasetResultService.createTemporaryDatasetFromUnaryExpression(
                            resultExpression.getResult(),
                            operator,
                            false
                    );

        }
        return result;
    }

    /**
     * Effettua un operazione di cast sui parametri delle funzioni
     *
     * @param resultExpression
     * @param vtlType
     * @return
     */
    public ResultExpression getCast(ResultExpression resultExpression, VtlType vtlType) {
        ResultExpression result = null;
        if (resultExpression.isAComponent()) {
            result = componentResultService.castComponent(resultExpression.getResultComponent(), vtlType);
        } else {
            result = datasetResultService.doCast(resultExpression.getResult(), vtlType);
        }

        return result;
    }

    /**
     * Effettua un oeprazione di cast
     *
     * @param vtlCastExpression
     * @return
     */
    public ResultExpression doCast(VtlCastExpression vtlCastExpression) {
        ResultExpression result = null;
        ResultExpression resultExpression = vtlCastExpression.getVtlExpression().getResultExpression();
        VtlType vtlTypeFrom;
        if (resultExpression.isAComponent()) {
            result = componentResultService.castComponent(resultExpression.getResultComponent(), vtlCastExpression.getVtlType());
            vtlTypeFrom = resultExpression.getResultComponent().getType();
        } else {
            result = datasetResultService.doCast(resultExpression.getResult(), vtlCastExpression.getVtlType());
            vtlTypeFrom = resultExpression.getResult().getMeasures().get(0).getType();
        }
        if (result.getMessages().size() == 0 && isMaskMandatory(vtlTypeFrom, vtlCastExpression.getOperator()) && vtlCastExpression.getMask() == null) {
            result.getMessages().add(SemanticMessageUtility.setUpBlockingMessage(
                    ValidationCheck.NEED_CAST_MASK.getCode(),
                    ValidationCheck.NEED_CAST_MASK.getMessage()
            ));
        }
        return result;
    }

    /**
     * Effettua la trasformazione semantica dell'operatore controllo sui parametri opzionali o no delle diverse funzioni VTL
     *
     * @param resultExpression
     * @param operator
     * @return
     * @throws ValidationException
     */
    public ResultExpression checkParameter(ResultExpression resultExpression, Operator operator) throws ValidationException {
        return datasetResultService.checkParameter(resultExpression, operator);
    }

    /**
     * Applica una funzione a un componente
     *
     * @param vtlComponent
     * @param operator
     * @return
     */
    public ResultExpression createTemporaryComponentFromUnaryExpression(VtlComponent vtlComponent, Operator operator) {
        return componentResultService.createTemporaryComponentFromUnaryExpression(vtlComponent, operator);
    }

    /**
     * Effettua un operazione di between fra due componenti
     *
     * @param vtlComponentLeft
     * @param vtlComponentRight
     * @param operator
     * @return
     */
    public ResultExpression createTemporaryComponentFromBinaryExpression(VtlComponent vtlComponentLeft,
                                                                         VtlComponent vtlComponentRight,
                                                                         Operator operator) {
        return componentResultService.createTemporaryComponentFromBinaryExpression(vtlComponentLeft, vtlComponentRight, operator);
    }

    /**
     * Seleziona un componente e lo restituisce
     *
     * @param vtlDataset
     * @param componentName
     * @param operator
     * @param ignoreCase
     * @return
     */
    public ResultExpression getComponent(VtlDataset vtlDataset, String componentName, Operator operator, boolean ignoreCase) {
        return datasetResultService.getComponent(vtlDataset, componentName, operator, ignoreCase);
    }

    /**
     * preleva un dataset dal database e lo restituisce
     *
     * @param vtlVarId
     * @return
     */
    public ResultExpression getDatasetByName(VtlVarId vtlVarId) {
        return datasetResultService.getDatasetByName(vtlVarId);
    }

    /**
     * Effettua l'operazione di membership nello scenario dei componenti. Preleva quindi dal dataset un componente
     * ignorando lo sharp
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
        return datasetResultService.getComponentMembershipInClause(vtlDataset, componentName, datasetInClauseName, operator);
    }

    /**
     * Effettua la trasformazione semantica dell'operatore membership su un dataset
     *
     * @param vtlDataset
     * @param componentName
     * @param operator
     * @return
     */
    public ResultExpression createTemporaryDatasetFromComponentName(VtlDataset vtlDataset, String componentName, Operator operator) {
        return datasetResultService.createTemporaryDatasetFromComponentName(vtlDataset, componentName, operator);
    }

    /**
     * Effettua la trasformazione semantica dell'operatore having
     *
     * @param vtlHavingClauseExpression
     * @return
     */
    public ResultExpression getHavingresult(VtlHavingClauseExpression vtlHavingClauseExpression) {
        return componentResultService.getHavingresult(vtlHavingClauseExpression);
    }

    /**
     * effettua l'operazione di assegnazione di un nome e ruolo al termine della calc o aggr
     *
     * @param vtlComponent
     * @param vtlComponentRole
     * @param componentName
     * @return
     */
    public ResultExpression assignComponentNameAndRole(VtlComponent vtlComponent, VtlComponentRole vtlComponentRole, String componentName) {
        return componentResultService.assignComponentNameAndRole(vtlComponent, vtlComponentRole, componentName);
    }

    /**
     * Assegna un nome a un dataset
     *
     * @param vtlDataset
     * @param datasetName
     * @param operator
     * @return
     */
    public ResultExpression assignDatasetName(VtlDataset vtlDataset, String datasetName, Operator operator) {
        return datasetResultService.assignDatasetName(vtlDataset, datasetName, operator);
    }

    /**
     * effettua la funzionalità di group by
     *
     * @param vtlDataset
     * @param identifiers
     * @param operator
     * @return
     */
    public ResultExpression groupIdentifier(VtlDataset vtlDataset, List<VtlComponent> identifiers, Operator operator) {
        return datasetResultService.groupIdentifier(vtlDataset, identifiers, operator);
    }

    /**
     * Effettua la funzionalità di count
     *
     * @param requestUuid
     * @return
     */
    public ResultExpression countExpr(UUID requestUuid) {
        return datasetResultService.countExpr(requestUuid);
    }

    /**
     * Aggiunge un componente a un dataset come al termine di una calc o aggr
     *
     * @param vtlDataset
     * @param vtlComponents
     * @return
     */
    public ResultExpression addComponentsToDataset(VtlDataset vtlDataset, List<VtlComponent> vtlComponents) {
        return datasetResultService.addComponentsToDataset(vtlDataset, vtlComponents);
    }

    /**
     * Aggrega i componenti a un dataset
     *
     * @param vtlDataset
     * @param vtlComponents
     * @return
     */
    public ResultExpression aggregateComponentToDataset(VtlDataset vtlDataset, List<VtlComponent> vtlComponents) {
        return datasetResultService.aggregateComponentToDataset(vtlDataset, vtlComponents);
    }

    /**
     * Effettua la trasformazione semantica dell'operatore Drop
     *
     * @param vtlDataset
     * @param vtlComponents
     * @param operator
     * @return
     */
    public ResultExpression dropComponents(VtlDataset vtlDataset, List<VtlComponent> vtlComponents, Operator operator) {
        return datasetResultService.dropComponents(vtlDataset, vtlComponents, operator);
    }

    /**
     * Effettua la trasformazione semantica dell'operatore keep
     *
     * @param vtlDataset
     * @param vtlComponents
     * @param operator
     * @return
     */
    public ResultExpression keepComponents(VtlDataset vtlDataset, List<VtlComponent> vtlComponents, Operator operator) {
        return datasetResultService.keepComponents(vtlDataset, vtlComponents, operator);
    }

    /**
     * Effettua la trasformazione semantica dell'operatore rename
     *
     * @param vtlDataset
     * @param renameMap
     * @param operator
     * @return
     */
    public ResultExpression renameComponents(VtlDataset vtlDataset, Map<String, String> renameMap, Operator operator) {
        return datasetResultService.renameComponents(vtlDataset, renameMap, operator);
    }

    /**
     * Effettua la trasformazione semantica dell'operatore sub
     *
     * @param vtlDataset
     * @param subComponentNames
     * @param operator
     * @return
     */
    public ResultExpression subSpaceComponent(VtlDataset vtlDataset, List<String> subComponentNames, Operator operator) {
        return datasetResultService.subSpaceComponent(vtlDataset, subComponentNames, operator);
    }

    /**
     * effettua l'operazione di join
     *
     * @param vtlDatasets
     * @param vtlJoinSelectClause
     * @param joinOperator
     * @return
     */
    public ResultExpression joinExpression(LinkedList<VtlDataset> vtlDatasets, VtlJoinSelectClause vtlJoinSelectClause, Operator joinOperator) {
        return datasetResultService.joinExpression(vtlDatasets, vtlJoinSelectClause, joinOperator);
    }

    /**
     * Elimina i componenti con nomi duplicati al termine dell'operazione di join
     *
     * @param vtlDataset
     * @return
     */
    public ResultExpression removeRedundantNames(VtlDataset vtlDataset) {
        return datasetResultService.removeRedundantNames(vtlDataset);
    }

    /**
     * Data uno scalare restituisce un dataset temporaneo
     *
     * @param constant
     * @return
     */
    public ResultExpression createTemporaryScalarDataset(VtlConstant<?> constant) {
        return datasetResultService.createTemporaryScalarDataset(constant);
    }

    /**
     * Effettua l'operazione di in
     *
     * @param constants
     * @return
     */
    public ResultExpression inConstantValidation(List<VtlConstantExpression> constants) {
        return componentResultService.inConstantValidation(constants);
    }

    /**
     * Effettua l'operazione di call function
     *
     * @param vtlCallExpression
     * @return
     * @throws ValidationException
     */
    public ResultExpression validateCallExpression(VtlCallExpression vtlCallExpression) throws ValidationException {
        return userFunctionResultService.validateCallExpression(vtlCallExpression);
    }

    /**
     * Effettua i controlli di call function parameter
     *
     * @param vtlCallExpression
     * @param variables
     * @return
     * @throws ValidationException
     */
    public ResultExpression validateCallExpressionParameter(VtlCallExpression vtlCallExpression, Map<KeyVariables, Object> variables) throws ValidationException {
        return userFunctionResultService.validateCallExpressionParameter(vtlCallExpression, variables);
    }

    /**
     * Controlla i parametri di uscita di una user function
     *
     * @param resultExpression
     * @param vtlExpression
     * @param vtlDataType
     * @return
     */
    public ResultExpression checkParameterOutput(ResultExpression resultExpression, VtlExpression vtlExpression, VtlDataType vtlDataType) {

        resultExpression.getMessages().addAll(
                userFunctionResultService.checkVtlDataType(vtlExpression, vtlDataType, null)
        );
        return resultExpression;
    }

    /**
     * Effettua un operazione di cast sui parametri delle funzioni
     *
     * @param resultExpression
     * @param vtlCallExpression
     * @return
     */
    public ResultExpression castUserOperatorOutput(ResultExpression resultExpression, VtlCallExpression vtlCallExpression) {
        VtlDataType vtlDataType = vtlCallExpression.getVtlUserFunction().getOutputType();
        if (vtlDataType instanceof VtlScalarType && ((VtlScalarType) vtlDataType).getVtlType() != null) {
            return getCast(resultExpression, ((VtlScalarType) vtlDataType).getVtlType());
        }
        return null;
    }

    /**
     * Rimuove gli identificativi da un dataset
     *
     * @param resultExpression
     * @return
     */
    public ResultExpression removeIdentifiers(ResultExpression resultExpression) {
        return datasetResultService.removeIdentifiers(resultExpression.getResult());
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
        return userFunctionResultService.validateCheckDatapoint(vtlCheckDatapoint, variables);
    }

    /**
     * Effettua la trasformazione semantica dell'operatore check Datapoint
     *
     * @param vtlCheckDatapoint
     * @return
     */
    public ResultExpression checkDatapoint(VtlCheckDatapoint vtlCheckDatapoint) {
        return datasetResultService.checkDatapoint(vtlCheckDatapoint);
    }

    /**
     * Effettua la trasformazione semantica dell'operatore validazione della check Hierarchy
     *
     * @param vtlCheckHierarchy
     * @param variables
     * @return
     * @throws ValidationException
     */
    public ResultExpression validateCheckHierarchicalRuleset(VtlCheckHierarchy vtlCheckHierarchy, Map<KeyVariables, Object> variables) throws ValidationException {
        return userFunctionResultService.validateCheckHierarchicalRuleset(vtlCheckHierarchy, variables);
    }

    /**
     * Effettua la trasformazione semantica dell'operatore esecuzione della check Hierarchy
     *
     * @param vtlCheckHierarchy
     * @return
     */
    public ResultExpression checkHierarchicalRuleset(VtlCheckHierarchy vtlCheckHierarchy) {
        return datasetResultService.checkHierarchicalRuleset(vtlCheckHierarchy, vtlCheckHierarchy.getOperator());
    }

    /**
     * Effettua la trasformazione semantica dell'operatore esecuzione check validation
     *
     * @param vtlCheck
     * @param vtlType
     * @return
     */
    public ResultExpression checkValidation(VtlCheck vtlCheck, VtlType vtlType) {
        return datasetResultService.checkValidation(vtlCheck, vtlType, vtlCheck.getOperator());
    }

    /**
     * Effettua la trasformazione semantica dell'operatore validazione della hierarchy
     *
     * @param vtlHierarchyExpression
     * @param variables
     * @return
     * @throws ValidationException
     */
    public ResultExpression validateHierarchy(VtlHierarchyExpression vtlHierarchyExpression, Map<KeyVariables, Object> variables) throws ValidationException {
        return userFunctionResultService.validateHierarchy(vtlHierarchyExpression, variables);
    }

    /**
     * Effettua la trasformazione semantica dell'operatore esecuzione della hierarchy
     *
     * @param vtlHierarchyExpression
     * @return
     */
    public ResultExpression hierarchy(VtlHierarchyExpression vtlHierarchyExpression) {
        return datasetResultService.hierarchy(vtlHierarchyExpression, vtlHierarchyExpression.getOperator());
    }

    /**
     * Effettua la trasformazione semantica dell'operatore validazione della datapoint
     *
     * @param vtlDatapointRuleset
     * @return
     */
    public ResultExpression validateDatapointRuleset(VtlDatapointRuleset vtlDatapointRuleset) {
        return userFunctionResultService.validateDatapointRuleset(vtlDatapointRuleset);
    }

    /**
     * Effettua la trasformazione semantica dell'operatore validazione della hierarhical rulset
     *
     * @param vtlHierarchicalRuleset
     * @return
     */
    public ResultExpression validateHierarchicalRuleset(VtlHierarchicalRuleset vtlHierarchicalRuleset) {
        return userFunctionResultService.validateHierarhicalRuleset(vtlHierarchicalRuleset);
    }

    /**
     * Effettua la trasformazione semantica dell'operatore validazione della user define
     *
     * @param vtlUserFunction
     * @return
     */
    public ResultExpression validateDefineUserFunction(VtlUserFunction vtlUserFunction) {
        ResultExpression resultExpression = userFunctionResultService.validateDefineUserFunction(vtlUserFunction);

        return resultExpression;
    }

    /**
     * Effettua la trasformazione semantica dell'operatore unpivot
     *
     * @param vtlUnpivotClauseExpression
     * @return
     */
    public ResultExpression unpivot(VtlPivotOrUnpivotClauseExpression vtlUnpivotClauseExpression) {
        return datasetResultService.unpivot(vtlUnpivotClauseExpression);
    }

    /**
     * Effettua la trasformazione semantica dell'operatore pivot
     *
     * @param vtlPivotClauseExpression
     * @return
     */
    public ResultExpression pivot(VtlPivotClauseExpression vtlPivotClauseExpression) {
        return datasetResultService.pivot(vtlPivotClauseExpression);
    }

    /**
     * Verifica la correttezza semantica delle Value in una InExpression
     *
     * @param vtlInExpression
     * @return
     */
    public ResultExpression getScalarValueDomain(VtlInExpression vtlInExpression) {
        return datasetResultService.getScalarValueDomain(vtlInExpression);
    }

    /**
     * Effettua la trasformazione semantica dell'operatore existIn
     *
     * @param resultLeft
     * @param resultRight
     * @param operator
     * @return
     */
    public ResultExpression existInResult(ResultExpression resultLeft, ResultExpression resultRight, Operator operator) {
        ResultExpression resultExpression =
                datasetResultService.existInResult(
                        resultLeft.getResult(),
                        resultRight.getResult(),
                        operator
                );
        return resultExpression;
    }

    /**
     * Effettua la trasformazione semantica dell'operatore currentDate
     *
     * @param requestUuid
     * @return
     */
    public ResultExpression currentDate(UUID requestUuid) {
        return datasetResultService.currentDate(requestUuid);
    }

    private boolean isMaskMandatory(VtlType vtlFromType, Operator operator) {
        if (Operator.CAST_TO_NUMBER == operator &&
                vtlFromType == VtlType.STRING) {
            return true;
        }
        if (Operator.CAST_TO_TIME == operator &&
                vtlFromType == VtlType.STRING) {
            return true;
        }
        if (Operator.CAST_TO_DATE == operator &&
                vtlFromType == VtlType.STRING || vtlFromType == VtlType.TIME_PERIOD) {
            return true;
        }
        if (Operator.CAST_TO_TIME_PERIOD == operator && vtlFromType == VtlType.STRING) {
            return true;
        }
        if (Operator.CAST_TO_STRING == operator &&
                vtlFromType == VtlType.TIME || vtlFromType == VtlType.DATE || vtlFromType == VtlType.DURATION) {
            return true;
        }
        if (Operator.CAST_TO_DURATION == operator &&
                vtlFromType == VtlType.STRING) {
            return true;
        }
        return false;
    }

    /**
     * Verifica se è necessario effettuare una rinominazione sui componenti dei dataset al termine della join
     *
     * @param vtlJoinSelectClause
     * @param resultExpression
     * @return
     */
    public ResultExpression checkRedundantNames(VtlJoinSelectClause vtlJoinSelectClause, ResultExpression resultExpression) {
        List<String> datasetsNames = new ArrayList<>();
        List<String> aliasNames = new ArrayList<>();

        for (VtlJoinSelectClauseItem vtlJoinSelectClauseItem : vtlJoinSelectClause.getVtlJoinSelectClauseItems()) {
            if (vtlJoinSelectClauseItem.getAsName() == null || vtlJoinSelectClauseItem.getAsName().isEmpty()) {
                if (vtlJoinSelectClauseItem.getVtlExpression() instanceof VtlVarId) {
                    VtlVarId vtlVarId = (VtlVarId) vtlJoinSelectClauseItem.getVtlExpression();

                    if (datasetsNames.contains(vtlVarId.getName().toUpperCase())) {
                        resultExpression.getMessages().add(SemanticMessageUtility.setUpBlockingMessage(
                                ValidationCheck.NEED_ALIAS_NAME.getCode(),
                                ValidationCheck.NEED_ALIAS_NAME.getMessage(),
                                vtlVarId.getName()
                        ));
                    }
                    datasetsNames.add(vtlVarId.getName().toUpperCase());
                }
            } else {
                if (aliasNames.contains(vtlJoinSelectClauseItem.getAsName().toUpperCase())) {
                    resultExpression.getMessages().add(SemanticMessageUtility.setUpBlockingMessage(
                            ValidationCheck.ALIAS_REPEATED.getCode(),
                            ValidationCheck.ALIAS_REPEATED.getMessage(),
                            vtlJoinSelectClauseItem.getAsName()
                    ));
                }
                aliasNames.add(vtlJoinSelectClauseItem.getAsName().toUpperCase());
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
        return resultExpression;
    }
}
