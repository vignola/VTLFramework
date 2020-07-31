package com.capgemini.istat.vtlcompiler.vtlsemantic.service.result;

import com.capgemini.istat.vtlcompiler.vtlcommon.model.dataset.VtlComponent;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.dataset.VtlComponentRole;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.dataset.VtlType;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.operator.Interaction;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.operator.Operator;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.semantic.ResultExpression;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.semantic.ValidationCheck;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.semantic.ValidationData;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.constant.VtlConstantExpression;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.grouping.VtlHavingClauseExpression;
import com.capgemini.istat.vtlcompiler.vtlcommon.service.ComponentUtilityService;
import com.capgemini.istat.vtlcompiler.vtlcommon.service.VtlTypeUtilityService;
import com.capgemini.istat.vtlcompiler.vtlsemantic.service.validation.ComponentSemanticValidationService;
import com.capgemini.istat.vtlcompiler.vtlsemantic.service.validation.SemanticMessageUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * * la classe si occupa di effettuare tutti i passaggi della validazione e della trasformazione dei componenti.
 * * Questa classe rappresenta il core del motore semantico per i componenti
 * * Tutti i metodi della classe restituiscono un oggetto Result expression che contiene o il risultato della trasformazione semantica
 * * o la lista degli errori che si sono incontrati durante la validazione del dato.
 *
 * @see ComponentUtilityService
 * @see ComponentSemanticValidationService
 * @see VtlTypeUtilityService
 * @see ResultExpression
 */
@Service
public class ComponentResultService {
    private ComponentUtilityService componentUtilityService;
    private ComponentSemanticValidationService componentSemanticValidationService;
    private VtlTypeUtilityService vtlTypeUtilityService;


    @Autowired
    public void setComponentUtilityService(ComponentUtilityService componentUtilityService) {
        this.componentUtilityService = componentUtilityService;
    }

    @Autowired
    public void setComponentSemanticValidationService(ComponentSemanticValidationService componentSemanticValidationService) {
        this.componentSemanticValidationService = componentSemanticValidationService;
    }


    @Autowired
    public void setVtlTypeUtilityService(VtlTypeUtilityService vtlTypeUtilityService) {
        this.vtlTypeUtilityService = vtlTypeUtilityService;
    }

    /**
     * Assegna a un componente un ruolo e un nome.
     *
     * @param vtlComponent
     * @param vtlComponentRole
     * @param componentName
     * @return
     */
    public ResultExpression assignComponentNameAndRole(VtlComponent vtlComponent, VtlComponentRole vtlComponentRole, String componentName) {
        ResultExpression resultExpression = new ResultExpression();
        resultExpression.setMessages(new ArrayList<>());
        resultExpression.setResultComponent(
                componentUtilityService.generateComponentWithRoleAndName(
                        vtlComponent,
                        vtlComponentRole,
                        componentName
                )
        );
        return resultExpression;
    }

    /**
     * Effettua la trasformazione semantica dell'operatore having
     *
     * @param vtlHavingClauseExpression
     * @return
     */
    public ResultExpression getHavingresult(VtlHavingClauseExpression vtlHavingClauseExpression) {
        ResultExpression resultExpression = new ResultExpression();
        if (vtlHavingClauseExpression.getVtlExpression().getOperator().getExpectedTypeReturn() != VtlType.BOOLEAN) {
            resultExpression.getMessages().add(
                    SemanticMessageUtility.setUpBlockingMessage(ValidationCheck.HAVING_EXCEPTION.getCode(), ValidationCheck.HAVING_EXCEPTION.getMessage())
            );
        } else {
            return null;
        }
        return resultExpression;
    }

    /**
     * Applica una funzione binaria a due componenti
     *
     * @param vtlComponentLeft
     * @param vtlComponentRight
     * @param operator
     * @return
     */
    public ResultExpression createTemporaryComponentFromBinaryExpression(VtlComponent vtlComponentLeft,
                                                                         VtlComponent vtlComponentRight,
                                                                         Operator operator) {
        ResultExpression result = new ResultExpression();
        ValidationData validationData = new ValidationData();
        validationData.getVtlComponents().add(vtlComponentLeft);
        validationData.getVtlComponents().add(vtlComponentRight);

        if (vtlComponentLeft.isScalar() || vtlComponentRight.isScalar()) {
            validationData.setValidationChecks(operator.getValidationMap().get(Interaction.SCALAR_TO_COMPONENT));
            result.setMessages(componentSemanticValidationService.validateSemantic(validationData));
            if (!SemanticMessageUtility.existBlockingMessage(result.getMessages())) {
                result.setResultComponent(componentUtilityService.getComponentWithExpectedType(
                        getNormalComponent(vtlComponentLeft, vtlComponentRight),
                        getScalarlComponent(vtlComponentLeft, vtlComponentRight).getType()
                ));
            }
        } else {
            validationData.setValidationChecks(operator.getValidationMap().get(Interaction.COMPONENT_TO_COMPONENT));
            result.setMessages(componentSemanticValidationService.validateSemantic(validationData));
            if (!SemanticMessageUtility.existBlockingMessage(result.getMessages())) {
                result.setResultComponent(
                        componentUtilityService.generateComponentWithCast(
                                vtlComponentLeft,
                                vtlComponentRight.getType()
                        )
                );
            }

        }
        result = applyCastIfNeedeed(result, operator);
        return result;
    }

    /**
     * Applica una funzione unaria su un componente
     *
     * @param vtlComponent
     * @param operator
     * @return
     */
    public ResultExpression createTemporaryComponentFromUnaryExpression(VtlComponent vtlComponent, Operator operator) {
        ResultExpression result = new ResultExpression();
        ValidationData validationData = new ValidationData();
        validationData.getVtlComponents().add(vtlComponent);
        if (vtlComponent.isScalar()) {
            validationData.setValidationChecks(operator.getValidationMap().get(Interaction.SCALAR));
            result.setMessages(componentSemanticValidationService.validateSemantic(validationData));
            if (!SemanticMessageUtility.existBlockingMessage(result.getMessages())) {
                result.setResultComponent(
                        componentUtilityService.getDefaultComponent(operator.getExpectedTypeReturn(), vtlComponent.getRequestUuid())
                );
            }
        } else { // E' un component
            validationData.setValidationChecks(operator.getValidationMap().get(Interaction.COMPONENT));
            result.setMessages(componentSemanticValidationService.validateSemantic(validationData));
            if (!SemanticMessageUtility.existBlockingMessage(result.getMessages())) {
                result.setResultComponent(componentUtilityService.applyOperatorToComponent(vtlComponent, operator));
            }
        }
        result = applyCastIfNeedeed(result, operator);
        return result;
    }

    /**
     * prende in ingresso due componenti e restituisce quello che non è scalare
     *
     * @param vtlComponentLeft
     * @param vtlComponentRight
     * @return
     */
    public VtlComponent getNormalComponent(VtlComponent vtlComponentLeft, VtlComponent vtlComponentRight) {
        return vtlComponentLeft.isScalar() ? vtlComponentRight : vtlComponentLeft;
    }

    /**
     * prende in ingresso due componenti e restituisce quello scalare
     *
     * @param vtlComponentLeft
     * @param vtlComponentRight
     * @return
     */
    public VtlComponent getScalarlComponent(VtlComponent vtlComponentLeft, VtlComponent vtlComponentRight) {
        return vtlComponentLeft.isScalar() ? vtlComponentLeft : vtlComponentRight;
    }

    /**
     * applica il cast su un componente e sostituisci il nome a seconda delle necessità dell'operatore
     *
     * @param resultExpression
     * @param operator
     * @return
     */
    public ResultExpression applyCastIfNeedeed(ResultExpression resultExpression, Operator operator) {
        if (!SemanticMessageUtility.existBlockingMessage(resultExpression.getMessages())) {
            if (operator.getSubstituteComponent()) {
                resultExpression.setResultComponent(componentUtilityService.getDefaultComponent(operator.getExpectedTypeReturn(), resultExpression.getResultComponent().getRequestUuid()));
            } else if (operator.getExpectedTypeReturn() != VtlType.NONE) {
                resultExpression.setResultComponent(
                        componentUtilityService.getComponentWithExpectedType(
                                resultExpression.getResultComponent(),
                                operator.getExpectedTypeReturn()
                        )
                );
            }
        }
        return resultExpression;
    }

    /**
     * Offre le funzionalità di cast su un componente
     *
     * @param vtlComponent
     * @param vtlType
     * @return
     */
    public ResultExpression castComponent(VtlComponent vtlComponent, VtlType vtlType) {
        ResultExpression resultExpression = new ResultExpression();
        if (vtlTypeUtilityService.isCastable(vtlComponent.getType(), vtlType)) {
            resultExpression.setResultComponent(
                    componentUtilityService.castComponent(vtlComponent, vtlType)
            );
        } else {
            String[] parameters = new String[1];
            parameters[0] = vtlType.getValueType();
            resultExpression.getMessages().add(
                    SemanticMessageUtility.setUpBlockingMessage(ValidationCheck.NOT_ALLOWED_CAST.getCode(),
                            ValidationCheck.NOT_ALLOWED_CAST.getMessage(), parameters));
        }
        return resultExpression;
    }

    /**
     * Effettua la trasformazione semantica dell'operatore in per i componenti
     *
     * @param constants
     * @return
     */
    public ResultExpression inConstantValidation(List<VtlConstantExpression> constants) {
        Boolean isSameType = vtlTypeUtilityService.hasSameTypeScalars(constants);
        if (!isSameType) {
            ResultExpression resultExpression = new ResultExpression();
            resultExpression.getMessages().add(
                    SemanticMessageUtility.setUpBlockingMessage(ValidationCheck.IS_SAME_TYPE_SCALARS.getCode(), ValidationCheck.IS_SAME_TYPE_SCALARS.getMessage())
            );
            return resultExpression;
        } else {
            return null;
        }
    }




}
