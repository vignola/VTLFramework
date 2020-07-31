package com.capgemini.istat.vtlcompiler.vtlsemantic;

import com.capgemini.istat.vtlcompiler.vtlcommon.model.KeyVariables;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.operator.OperatorValidation;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.semantic.ResultExpression;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.constant.VtlConstantExpression;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.dataset.VtlComponentId;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.dataset.VtlVarId;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.define.operator.VtlUserFunction;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.define.ruleset.datapoint.VtlDatapointRuleset;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.define.ruleset.hierarchical.VtlHierarchicalRuleset;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.VtlExpression;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.VtlMembership;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.VtlParenthesisExpression;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.booleans.VtlBooleanBinaryExpression;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.booleans.VtlBooleanUnaryExpression;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.clause.VtlClauseOperator;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.clause.aggr.*;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.clause.analytic.VtlAnalyticFunctionExpression;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.clause.calc.VtlCalcClauseExpression;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.clause.calc.VtlCalcClauseItemExpression;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.clause.drop.VtlKeepOrDropClauseExpression;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.clause.filter.VtlFilterClauseExpression;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.clause.pivot.VtlPivotClauseExpression;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.clause.rename.VtlRenameClauseExpression;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.clause.sub.VtlSubSpaceExpression;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.clause.unpivot.VtlPivotOrUnpivotClauseExpression;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.comparison.*;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.conditional.VtlConditionalBinaryExpression;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.conditional.VtlIfExpression;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.function.VtlBinaryExpression;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.function.VtlUnaryExpression;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.function.VtlUnaryWithOneParameter;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.generic.VtlCallExpression;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.generic.VtlCastExpression;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.grouping.VtlGroupClauseExpression;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.grouping.VtlHavingClauseExpression;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.hierarchy.VtlHierarchyExpression;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.join.VtlJoinBody;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.join.VtlJoinExpression;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.join.VtlJoinSelectClause;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.join.VtlJoinSelectClauseItem;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.numeric.VtlNumericBinaryExpression;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.numeric.VtlNumericUnaryExpression;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.numeric.VtlNumericUnaryWithOneParameterExpression;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.set.VtlSetBinaryExpression;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.set.VtlSetUnnaryExpression;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.string.VtlStringBinaryExpression;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.string.VtlStringUnaryExpression;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.string.VtlStringUnaryWithOneParameterExpression;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.time.VtlCurrentDate;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.time.VtlTimeUnaryExpression;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.time.VtlTimeUnaryWithOneParameterExpression;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.validation.VtlCheck;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.validation.VtlCheckDatapoint;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.validation.VtlCheckHierarchy;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.statement.VtlAssignment;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.statement.VtlPersist;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.statement.VtlStatement;
import com.capgemini.istat.vtlcompiler.vtlsemantic.expression.StatementValidation;
import com.capgemini.istat.vtlcompiler.vtlsemantic.expression.assign.AssignValidation;
import com.capgemini.istat.vtlcompiler.vtlsemantic.expression.clause.ClauseValidation;
import com.capgemini.istat.vtlcompiler.vtlsemantic.expression.clause.GroupClauseValidation;
import com.capgemini.istat.vtlcompiler.vtlsemantic.expression.clause.HavingClauseValidation;
import com.capgemini.istat.vtlcompiler.vtlsemantic.expression.clause.aggr.*;
import com.capgemini.istat.vtlcompiler.vtlsemantic.expression.clause.analytic.AnalyticFunctionValidation;
import com.capgemini.istat.vtlcompiler.vtlsemantic.expression.clause.calc.CalcClauseItemValidation;
import com.capgemini.istat.vtlcompiler.vtlsemantic.expression.clause.calc.CalcClauseValidation;
import com.capgemini.istat.vtlcompiler.vtlsemantic.expression.clause.drop.KeepOrDropClauseValidation;
import com.capgemini.istat.vtlcompiler.vtlsemantic.expression.clause.filter.FilterClauseValidation;
import com.capgemini.istat.vtlcompiler.vtlsemantic.expression.clause.pivot.PivotClauseValidation;
import com.capgemini.istat.vtlcompiler.vtlsemantic.expression.clause.rename.RenameClauseValidation;
import com.capgemini.istat.vtlcompiler.vtlsemantic.expression.clause.sub.SubClauseValidation;
import com.capgemini.istat.vtlcompiler.vtlsemantic.expression.clause.unpivot.UnpivotClauseValidation;
import com.capgemini.istat.vtlcompiler.vtlsemantic.expression.comparison.BetweenValidation;
import com.capgemini.istat.vtlcompiler.vtlsemantic.expression.comparison.ExistInValidation;
import com.capgemini.istat.vtlcompiler.vtlsemantic.expression.comparison.InValidation;
import com.capgemini.istat.vtlcompiler.vtlsemantic.expression.conditional.IfValidation;
import com.capgemini.istat.vtlcompiler.vtlsemantic.expression.define.DatapointRulesetValidation;
import com.capgemini.istat.vtlcompiler.vtlsemantic.expression.define.DefineUserFunctionValidation;
import com.capgemini.istat.vtlcompiler.vtlsemantic.expression.define.HierarchicalRulesetValidation;
import com.capgemini.istat.vtlcompiler.vtlsemantic.expression.functions.BinaryValidation;
import com.capgemini.istat.vtlcompiler.vtlsemantic.expression.functions.SetUnnaryValidation;
import com.capgemini.istat.vtlcompiler.vtlsemantic.expression.functions.UnaryExpressionValidation;
import com.capgemini.istat.vtlcompiler.vtlsemantic.expression.functions.UnaryWithOneParameterValidation;
import com.capgemini.istat.vtlcompiler.vtlsemantic.expression.functions.generic.CallValidation;
import com.capgemini.istat.vtlcompiler.vtlsemantic.expression.functions.generic.CastValidation;
import com.capgemini.istat.vtlcompiler.vtlsemantic.expression.functions.hierarchy.HierarchyValidation;
import com.capgemini.istat.vtlcompiler.vtlsemantic.expression.functions.validation.CheckDatapointValidation;
import com.capgemini.istat.vtlcompiler.vtlsemantic.expression.functions.validation.CheckHierarchyValidation;
import com.capgemini.istat.vtlcompiler.vtlsemantic.expression.functions.validation.CheckValidation;
import com.capgemini.istat.vtlcompiler.vtlsemantic.expression.general.MembershipValidation;
import com.capgemini.istat.vtlcompiler.vtlsemantic.expression.general.ParenthesisValidation;
import com.capgemini.istat.vtlcompiler.vtlsemantic.expression.join.JoinBodyValidation;
import com.capgemini.istat.vtlcompiler.vtlsemantic.expression.join.JoinSelectClauseValidation;
import com.capgemini.istat.vtlcompiler.vtlsemantic.expression.join.JoinSelectClauseValidationItem;
import com.capgemini.istat.vtlcompiler.vtlsemantic.expression.join.JoinValidation;
import com.capgemini.istat.vtlcompiler.vtlsemantic.expression.persist.PersistValidation;
import com.capgemini.istat.vtlcompiler.vtlsemantic.expression.terminal.ComponentIdValidation;
import com.capgemini.istat.vtlcompiler.vtlsemantic.expression.terminal.ConstantValidation;
import com.capgemini.istat.vtlcompiler.vtlsemantic.expression.terminal.VarIdValidation;
import com.capgemini.istat.vtlcompiler.vtlsemantic.expression.time.CurrentDateValidation;
import com.capgemini.istat.vtlcompiler.vtlsemantic.service.validation.SemanticMessageUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.UUID;
import java.util.function.Supplier;

/**
 * Questa classe è la classe principale per la ricostruzione dell'albero per la validazione semantica.
 * In questa classe è presente una mappa di tutte le parti dell'albero attivabili e un metodo generico che permette la
 * navigazione fra tutti i nodi
 */
@Service
public class SemanticFactory {

    private Map<String, Supplier<OperatorValidation>> operatori;
    private ApplicationContext ctx;

    @Autowired
    public void setCtx(ApplicationContext ctx) {
        this.ctx = ctx;
    }

    /**
     * in questo metodo di inizializzazione viene creata una mappa che associa un nodo di interpretazione dell'albero(VtlExpression) con
     * un nodo di validazione(OperationValidation).
     */
    @PostConstruct
    public void initMap() {
        this.operatori = new HashMap<>();
        operatori.put(VtlStatement.class.getName(), () -> ctx.getBean(StatementValidation.class));
        operatori.put(VtlAssignment.class.getName(), () -> ctx.getBean(AssignValidation.class));
        operatori.put(VtlPersist.class.getName(), () -> ctx.getBean(PersistValidation.class));
        operatori.put(VtlMembership.class.getName(), () -> ctx.getBean(MembershipValidation.class));
        operatori.put(VtlParenthesisExpression.class.getName(), () -> ctx.getBean(ParenthesisValidation.class));
        operatori.put(VtlCastExpression.class.getName(), () -> ctx.getBean(CastValidation.class));
        operatori.put(VtlComponentId.class.getName(), () -> ctx.getBean(ComponentIdValidation.class));
        operatori.put(VtlVarId.class.getName(), () -> ctx.getBean(VarIdValidation.class));
        operatori.put(VtlConstantExpression.class.getName(), () -> ctx.getBean(ConstantValidation.class));


        operatori.put(VtlUnaryExpression.class.getName(), () -> ctx.getBean(UnaryExpressionValidation.class));
        operatori.put(VtlUnaryWithOneParameter.class.getName(),() -> ctx.getBean(UnaryWithOneParameterValidation.class));
        operatori.put(VtlBinaryExpression.class.getName(), () -> ctx.getBean(BinaryValidation.class));

        //boolean
        operatori.put(VtlBooleanUnaryExpression.class.getName(), () -> ctx.getBean(UnaryExpressionValidation.class));
        operatori.put(VtlBooleanBinaryExpression.class.getName(), () -> ctx.getBean(BinaryValidation.class));

        //String
        operatori.put(VtlStringUnaryExpression.class.getName(), () -> ctx.getBean(UnaryExpressionValidation.class));
        operatori.put(VtlStringUnaryWithOneParameterExpression.class.getName(), () -> ctx.getBean(UnaryWithOneParameterValidation.class));
        operatori.put(VtlStringBinaryExpression.class.getName(), () -> ctx.getBean(BinaryValidation.class));

        //comparison
        operatori.put(VtlComparisonUnaryWithOneParameterExpression.class.getName(), () -> ctx.getBean(UnaryWithOneParameterValidation.class));
        operatori.put(VtlComparisonBinaryExpression.class.getName(), () -> ctx.getBean(BinaryValidation.class));
        operatori.put(VtlBetweenExpression.class.getName(), () -> ctx.getBean(BetweenValidation.class));
        operatori.put(VtlInExpression.class.getName(), () -> ctx.getBean(InValidation.class));
        operatori.put(VtlExistIn.class.getName(), () -> ctx.getBean(ExistInValidation.class));

        //numeric
        operatori.put(VtlNumericUnaryExpression.class.getName(), () -> ctx.getBean(UnaryExpressionValidation.class));
        operatori.put(VtlNumericUnaryWithOneParameterExpression.class.getName(), () -> ctx.getBean(UnaryWithOneParameterValidation.class));
        operatori.put(VtlNumericBinaryExpression.class.getName(), () -> ctx.getBean(BinaryValidation.class));

        //set
        operatori.put(VtlSetBinaryExpression.class.getName(), () -> ctx.getBean(BinaryValidation.class));
        operatori.put(VtlSetUnnaryExpression.class.getName(), () -> ctx.getBean(SetUnnaryValidation.class));

        //time
        operatori.put(VtlTimeUnaryExpression.class.getName(), () -> ctx.getBean(UnaryExpressionValidation.class));
        operatori.put(VtlTimeUnaryWithOneParameterExpression.class.getName(), () -> ctx.getBean(UnaryWithOneParameterValidation.class));
        operatori.put(VtlCurrentDate.class.getName(), () -> ctx.getBean(CurrentDateValidation.class));

        //group
        operatori.put(VtlGroupClauseExpression.class.getName(), () -> ctx.getBean(GroupClauseValidation.class));
        operatori.put(VtlHavingClauseExpression.class.getName(), () -> ctx.getBean(HavingClauseValidation.class));

        //clause
        operatori.put(VtlClauseOperator.class.getName(), () -> ctx.getBean(ClauseValidation.class));
        operatori.put(VtlCalcClauseExpression.class.getName(), () -> ctx.getBean(CalcClauseValidation.class));
        operatori.put(VtlCalcClauseItemExpression.class.getName(), () -> ctx.getBean(CalcClauseItemValidation.class));
        operatori.put(VtlFilterClauseExpression.class.getName(), () -> ctx.getBean(FilterClauseValidation.class));
        operatori.put(VtlAggrClauseExpression.class.getName(), () -> ctx.getBean(AggrClauseValidation.class));
        operatori.put(VtlAggregateClauseExpression.class.getName(), () -> ctx.getBean(AggregateClauseValidation.class));
        operatori.put(VtlAggrFunctionClauseExpression.class.getName(), () -> ctx.getBean(AggrFunctionClauseValidation.class));
        operatori.put(VtlAggrFunctionExpression.class.getName(), () -> ctx.getBean(AggrFunctionValidation.class));
        operatori.put(VtlAggrComp.class.getName(), () -> ctx.getBean(AggrCompValidation.class));
        operatori.put(VtlAnalyticFunctionExpression.class.getName(), () -> ctx.getBean(AnalyticFunctionValidation.class));
        operatori.put(VtlAggrInvocationExpression.class.getName(), () -> ctx.getBean(AggrInvocationValidation.class));
        operatori.put(VtlRenameClauseExpression.class.getName(), () -> ctx.getBean(RenameClauseValidation.class));
        operatori.put(VtlKeepOrDropClauseExpression.class.getName(), () -> ctx.getBean(KeepOrDropClauseValidation.class));
        operatori.put(VtlSubSpaceExpression.class.getName(), () -> ctx.getBean(SubClauseValidation.class));
        operatori.put(VtlPivotOrUnpivotClauseExpression.class.getName(), () -> ctx.getBean(UnpivotClauseValidation.class));
        operatori.put(VtlPivotClauseExpression.class.getName(), () -> ctx.getBean(PivotClauseValidation.class));

        //conditional
        operatori.put(VtlConditionalBinaryExpression.class.getName(), () -> ctx.getBean(BinaryValidation.class));
        operatori.put(VtlIfExpression.class.getName(), () -> ctx.getBean(IfValidation.class));

        //join
        operatori.put(VtlJoinExpression.class.getName(), () -> ctx.getBean(JoinValidation.class));
        operatori.put(VtlJoinBody.class.getName(), () -> ctx.getBean(JoinBodyValidation.class));
        operatori.put(VtlJoinSelectClause.class.getName(), () -> ctx.getBean(JoinSelectClauseValidation.class));
        operatori.put(VtlJoinSelectClauseItem.class.getName(), () -> ctx.getBean(JoinSelectClauseValidationItem.class));

        //call
        operatori.put(VtlCallExpression.class.getName(), () -> ctx.getBean(CallValidation.class));

        //validation
        operatori.put(VtlCheckDatapoint.class.getName(), () -> ctx.getBean(CheckDatapointValidation.class));
        operatori.put(VtlCheckHierarchy.class.getName(), () -> ctx.getBean(CheckHierarchyValidation.class));
        operatori.put(VtlCheck.class.getName(), () -> ctx.getBean(CheckValidation.class));

        //hierarchy
        operatori.put(VtlHierarchyExpression.class.getName(), () -> ctx.getBean(HierarchyValidation.class));

        //RulesetCustom
        operatori.put(VtlDatapointRuleset.class.getName(), () -> ctx.getBean(DatapointRulesetValidation.class));
        operatori.put(VtlHierarchicalRuleset.class.getName(), () -> ctx.getBean(HierarchicalRulesetValidation.class));
        operatori.put(VtlUserFunction.class.getName(), () -> ctx.getBean(DefineUserFunctionValidation.class));

    }

    /**
     * questo metodo rappresenta l'ossatura del motore semantico. Tutti i nodi tranne quelli terminali dell'albero fanno riferimento
     * a questo metodo per andare in profondità.
     * Il metodo prende in ingresso una VtlExpression, attiva una OperationValidation.
     * I risultati della validazione vengono controllati per verificare l'assenza di errori e, se non sono presenti, viene restituito
     * il risultato al livello superiore dell'albero
     *
     * @param vtlExpression l'espressione da validare
     * @param variables     le variabili di esecuzione. Sono specifiche di ogni singola validazione
     * @return una lista di resultExpression rappresentanti le trasformazioni dei dataset
     * @throws Exception il metodo propaga un eccezione qualsiasi del motore di validazione.
     * @see OperatorValidation
     */
    public LinkedList<ResultExpression> checkSemantic(VtlExpression vtlExpression, Map<KeyVariables, Object> variables) throws Exception {
        OperatorValidation semanticFactory = operatori.get(vtlExpression.getClass().getName()).get();
        semanticFactory.setVtlExpression(vtlExpression);
        vtlExpression.setRequestUuid((UUID) variables.get(KeyVariables.REQUEST_UUID));
        LinkedList<ResultExpression> results = semanticFactory.resolve(variables);
        if (results.size() != 0) {
            SemanticMessageUtility.processSemanticMessage(results.getFirst());
            vtlExpression.setResultExpression(results.getFirst());
        }
        return results;
    }
}
