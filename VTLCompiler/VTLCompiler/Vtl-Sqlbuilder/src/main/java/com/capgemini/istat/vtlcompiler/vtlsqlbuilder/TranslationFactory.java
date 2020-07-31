package com.capgemini.istat.vtlcompiler.vtlsqlbuilder;

import com.capgemini.istat.vtlcompiler.vtlcommon.model.KeyVariables;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.translation.SqlResult;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.constant.VtlConstantExpression;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.dataset.VtlComponentId;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.dataset.VtlVarId;
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
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.function.VtlUnaryExpression;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.function.VtlUnaryWithOneParameter;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.generic.VtlCallExpression;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.generic.VtlCastExpression;
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
import com.capgemini.istat.vtlcompiler.vtlsqlbuilder.expression.StatementTranslation;
import com.capgemini.istat.vtlcompiler.vtlsqlbuilder.expression.analytic.AnalyticFunctionTranslation;
import com.capgemini.istat.vtlcompiler.vtlsqlbuilder.expression.assign.AssignmentTranslation;
import com.capgemini.istat.vtlcompiler.vtlsqlbuilder.expression.binary.*;
import com.capgemini.istat.vtlcompiler.vtlsqlbuilder.expression.clause.ClauseTranslation;
import com.capgemini.istat.vtlcompiler.vtlsqlbuilder.expression.clause.aggr.*;
import com.capgemini.istat.vtlcompiler.vtlsqlbuilder.expression.clause.calc.CalcClauseItemTranslation;
import com.capgemini.istat.vtlcompiler.vtlsqlbuilder.expression.clause.calc.CalcClauseTranslation;
import com.capgemini.istat.vtlcompiler.vtlsqlbuilder.expression.clause.drop.DropClauseTranslation;
import com.capgemini.istat.vtlcompiler.vtlsqlbuilder.expression.clause.filter.FilterClauseTranslation;
import com.capgemini.istat.vtlcompiler.vtlsqlbuilder.expression.clause.pivot.PivotClauseTranslation;
import com.capgemini.istat.vtlcompiler.vtlsqlbuilder.expression.clause.rename.RenameClauseTranslation;
import com.capgemini.istat.vtlcompiler.vtlsqlbuilder.expression.clause.sub.SubspaceClauseTranslation;
import com.capgemini.istat.vtlcompiler.vtlsqlbuilder.expression.clause.unpivot.UnpivotClauseTranslation;
import com.capgemini.istat.vtlcompiler.vtlsqlbuilder.expression.comparison.BetweenTranslation;
import com.capgemini.istat.vtlcompiler.vtlsqlbuilder.expression.comparison.ExistInTranslation;
import com.capgemini.istat.vtlcompiler.vtlsqlbuilder.expression.comparison.InTranslation;
import com.capgemini.istat.vtlcompiler.vtlsqlbuilder.expression.conditional.IfTranslation;
import com.capgemini.istat.vtlcompiler.vtlsqlbuilder.expression.functions.generic.CallTranslation;
import com.capgemini.istat.vtlcompiler.vtlsqlbuilder.expression.functions.hierarchy.HierarchyTranslation;
import com.capgemini.istat.vtlcompiler.vtlsqlbuilder.expression.functions.validation.CheckDatapointTranslation;
import com.capgemini.istat.vtlcompiler.vtlsqlbuilder.expression.functions.validation.CheckHierarchicalTranslation;
import com.capgemini.istat.vtlcompiler.vtlsqlbuilder.expression.functions.validation.CheckValidationTranslation;
import com.capgemini.istat.vtlcompiler.vtlsqlbuilder.expression.general.MembershipTranslation;
import com.capgemini.istat.vtlcompiler.vtlsqlbuilder.expression.general.ParenthesisTranslation;
import com.capgemini.istat.vtlcompiler.vtlsqlbuilder.expression.generic.CastTranslation;
import com.capgemini.istat.vtlcompiler.vtlsqlbuilder.expression.grouping.HavingClauseTranslation;
import com.capgemini.istat.vtlcompiler.vtlsqlbuilder.expression.join.JoinBodyTranslation;
import com.capgemini.istat.vtlcompiler.vtlsqlbuilder.expression.join.JoinSelectClauseItemTranslation;
import com.capgemini.istat.vtlcompiler.vtlsqlbuilder.expression.join.JoinSelectClauseTranslation;
import com.capgemini.istat.vtlcompiler.vtlsqlbuilder.expression.join.JoinTranslation;
import com.capgemini.istat.vtlcompiler.vtlsqlbuilder.expression.persist.PersistTranslation;
import com.capgemini.istat.vtlcompiler.vtlsqlbuilder.expression.set.SetBinaryTranslation;
import com.capgemini.istat.vtlcompiler.vtlsqlbuilder.expression.set.SetUnnaryTranslation;
import com.capgemini.istat.vtlcompiler.vtlsqlbuilder.expression.terminal.ComponentIdTranslation;
import com.capgemini.istat.vtlcompiler.vtlsqlbuilder.expression.terminal.ConstantTranslation;
import com.capgemini.istat.vtlcompiler.vtlsqlbuilder.expression.terminal.VarIdTranslation;
import com.capgemini.istat.vtlcompiler.vtlsqlbuilder.expression.time.CurrentDateTranslation;
import com.capgemini.istat.vtlcompiler.vtlsqlbuilder.expression.unary.*;
import com.capgemini.istat.vtlcompiler.vtlsqlbuilder.model.OperatorTranslation;
import com.capgemini.istat.vtlcompiler.vtlsqlbuilder.service.sqlresult.ISqlResultService;
import com.capgemini.istat.vtlcompiler.vtlsqlbuilder.service.sqlresult.mysql.MySqlResultService;
import com.capgemini.istat.vtlcompiler.vtlsqlbuilder.service.sqlresult.oracle.OracleSqlResultService;
import com.capgemini.istat.vtlcompiler.vtlsqlbuilder.service.sqlresult.postgresql.PostgreSqlResultService;
import com.capgemini.istat.vtlcompiler.vtlsqlbuilder.service.sqlresult.sqlserver.SqlServerResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

/**
 * Questa classe rappresenta la classe principale per la costruzione dello statement di traduzione. Tutti i nodi di traduzione
 * invocano questa classe per poter proseguire nella traduzione.
 *
 * @see ISqlResultService
 * @see SqlResult
 */
@Service
public class TranslationFactory {
    private ApplicationContext ctx;
    private Map<String, Supplier<OperatorTranslation>> operatori;
    private OracleSqlResultService oracleSqlResultService;
    private SqlServerResultService sqlServerResultService;
    private MySqlResultService mySqlResultService;
    private PostgreSqlResultService postgreSqlResultService;

    @Autowired
    public void setOracleSqlResultService(OracleSqlResultService oracleSqlResultService) {
        this.oracleSqlResultService = oracleSqlResultService;
    }

    @Autowired
    public void setSqlServerResultService(SqlServerResultService sqlServerResultService) {
        this.sqlServerResultService = sqlServerResultService;
    }

    @Autowired
    public void setMySqlResultService(MySqlResultService mySqlResultService) {
        this.mySqlResultService = mySqlResultService;
    }

    @Autowired
    public void setPostgreSqlResultService(PostgreSqlResultService postgreSqlResultService) {
        this.postgreSqlResultService = postgreSqlResultService;
    }

    @Autowired
    public void setCtx(ApplicationContext ctx) {
        this.ctx = ctx;
    }

    /**
     * in questo metodo di inizializzazione viene creata una mappa che associa un nodo di interpretazione dell'albero(VtlExpression) con
     * un nodo di traduzione(OperatorTranslation).
     */
    @PostConstruct
    public void initMap() {
        this.operatori = new HashMap<>();

        operatori.put(VtlStatement.class.getName(), () -> ctx.getBean(StatementTranslation.class));
        operatori.put(VtlAssignment.class.getName(), () -> ctx.getBean(AssignmentTranslation.class));
        operatori.put(VtlPersist.class.getName(), () -> ctx.getBean(PersistTranslation.class));
        operatori.put(VtlMembership.class.getName(), () -> ctx.getBean(MembershipTranslation.class));
        operatori.put(VtlParenthesisExpression.class.getName(), () -> ctx.getBean(ParenthesisTranslation.class));
        operatori.put(VtlCastExpression.class.getName(), () -> ctx.getBean(CastTranslation.class));
        operatori.put(VtlComponentId.class.getName(), () -> ctx.getBean(ComponentIdTranslation.class));
        operatori.put(VtlVarId.class.getName(), () -> ctx.getBean(VarIdTranslation.class));
        operatori.put(VtlConstantExpression.class.getName(), () -> ctx.getBean(ConstantTranslation.class));


        operatori.put(VtlUnaryExpression.class.getName(), () -> ctx.getBean(UnaryFunctionTranslation.class));
        operatori.put(VtlUnaryWithOneParameter.class.getName(),() -> ctx.getBean(UnaryFunctionWithOneParameterTranslation.class));

        //boolean
        operatori.put(VtlBooleanUnaryExpression.class.getName(), () -> ctx.getBean(BooleanUnaryTranslation.class));
        operatori.put(VtlBooleanBinaryExpression.class.getName(), () -> ctx.getBean(BooleanBinaryTranslation.class));

        //String
        operatori.put(VtlStringUnaryExpression.class.getName(), () -> ctx.getBean(UnaryFunctionTranslation.class));
        operatori.put(VtlStringUnaryWithOneParameterExpression.class.getName(), () -> ctx.getBean(UnaryFunctionWithOneParameterTranslation.class));
        operatori.put(VtlStringBinaryExpression.class.getName(), () -> ctx.getBean(StringBinaryTranslation.class));

        //comparison
        operatori.put(VtlComparisonUnaryWithOneParameterExpression.class.getName(), () -> ctx.getBean(UnaryFunctionWithOneParameterTranslation.class));
        operatori.put(VtlComparisonBinaryExpression.class.getName(), () -> ctx.getBean(ComparisonBinaryTranslation.class));
        operatori.put(VtlBetweenExpression.class.getName(), () -> ctx.getBean(BetweenTranslation.class));
        operatori.put(VtlInExpression.class.getName(), () -> ctx.getBean(InTranslation.class));
        operatori.put(VtlExistIn.class.getName(), () -> ctx.getBean(ExistInTranslation.class));

        //numeric
        operatori.put(VtlNumericUnaryExpression.class.getName(), () -> ctx.getBean(NumericUnaryTranslation.class));
        operatori.put(VtlNumericUnaryWithOneParameterExpression.class.getName(), () -> ctx.getBean(UnaryFunctionWithOneParameterTranslation.class));
        operatori.put(VtlNumericBinaryExpression.class.getName(), () -> ctx.getBean(NumericBinaryTranslation.class));

        //set
        operatori.put(VtlSetBinaryExpression.class.getName(), () -> ctx.getBean(SetBinaryTranslation.class));
        operatori.put(VtlSetUnnaryExpression.class.getName(), () -> ctx.getBean(SetUnnaryTranslation.class));

        //time
        operatori.put(VtlTimeUnaryExpression.class.getName(), () -> ctx.getBean(UnaryTimeFunctionTranslation.class));
        operatori.put(VtlTimeUnaryWithOneParameterExpression.class.getName(), () -> ctx.getBean(UnaryTimeFunctionWithOneParameterTranslation.class));
        operatori.put(VtlCurrentDate.class.getName(), () -> ctx.getBean(CurrentDateTranslation.class));

        //group
        operatori.put(VtlHavingClauseExpression.class.getName(), () -> ctx.getBean(HavingClauseTranslation.class));

        //clause
        operatori.put(VtlClauseOperator.class.getName(), () -> ctx.getBean(ClauseTranslation.class));
        operatori.put(VtlCalcClauseExpression.class.getName(), () -> ctx.getBean(CalcClauseTranslation.class));
        operatori.put(VtlCalcClauseItemExpression.class.getName(), () -> ctx.getBean(CalcClauseItemTranslation.class));
        operatori.put(VtlFilterClauseExpression.class.getName(), () -> ctx.getBean(FilterClauseTranslation.class));
        operatori.put(VtlAggrClauseExpression.class.getName(), () -> ctx.getBean(AggrClauseTranslation.class));
        operatori.put(VtlAggregateClauseExpression.class.getName(), () -> ctx.getBean(AggregateClauseTranslation.class));
        operatori.put(VtlAggrFunctionClauseExpression.class.getName(), () -> ctx.getBean(AggrFunctionClauseTranslation.class));
        operatori.put(VtlAggrInvocationExpression.class.getName(), () -> ctx.getBean(AggrInvocationTranslation.class));
        operatori.put(VtlAggrComp.class.getName(), () -> ctx.getBean(AggrCompTranslation.class));
        operatori.put(VtlAnalyticFunctionExpression.class.getName(), () -> ctx.getBean(AnalyticFunctionTranslation.class));
        operatori.put(VtlKeepOrDropClauseExpression.class.getName(), () -> ctx.getBean(DropClauseTranslation.class));
        operatori.put(VtlRenameClauseExpression.class.getName(), () -> ctx.getBean(RenameClauseTranslation.class));
        //    operatori.put(VtlKeepClauseExpression.class.getName(), () -> ctx.getBean(KeepClauseTranslation.class));
        operatori.put(VtlSubSpaceExpression.class.getName(), () -> ctx.getBean(SubspaceClauseTranslation.class));
        operatori.put(VtlPivotOrUnpivotClauseExpression.class.getName(), () -> ctx.getBean(UnpivotClauseTranslation.class));
        operatori.put(VtlPivotClauseExpression.class.getName(), () -> ctx.getBean(PivotClauseTranslation.class));

        //conditional
        operatori.put(VtlConditionalBinaryExpression.class.getName(), () -> ctx.getBean(ConditionalBinaryTranslation.class));
        operatori.put(VtlIfExpression.class.getName(), () -> ctx.getBean(IfTranslation.class));

        //join
        operatori.put(VtlJoinExpression.class.getName(), () -> ctx.getBean(JoinTranslation.class));
        operatori.put(VtlJoinBody.class.getName(), () -> ctx.getBean(JoinBodyTranslation.class));
        operatori.put(VtlJoinSelectClause.class.getName(), () -> ctx.getBean(JoinSelectClauseTranslation.class));
        operatori.put(VtlJoinSelectClauseItem.class.getName(), () -> ctx.getBean(JoinSelectClauseItemTranslation.class));

        //call
        operatori.put(VtlCallExpression.class.getName(), () -> ctx.getBean(CallTranslation.class));

        //validation
        operatori.put(VtlCheckDatapoint.class.getName(), () -> ctx.getBean(CheckDatapointTranslation.class));
        operatori.put(VtlCheckHierarchy.class.getName(), () -> ctx.getBean(CheckHierarchicalTranslation.class));
        operatori.put(VtlCheck.class.getName(), () -> ctx.getBean(CheckValidationTranslation.class));

        //hierarchy
        operatori.put(VtlHierarchyExpression.class.getName(), () -> ctx.getBean(HierarchyTranslation.class));

    }

    /**
     * Questo metodo rappresenta l'ossatura del motore di traduzione. Ogni nodo invoca questo metodo per poter continuare
     * l'esplorazione dell'albero e poter ottenere un risultato da un livello più profondo di navigazione.
     * Il metodo prende in ingresso una VTLExpression, attiva il componente di traduzione corrispondente e lo attiva settandolo
     * con il profilo di traduzione richiesto
     * Al termine raccoglie i risultati e li restituisce
     *
     * @param vtlExpression il nodo da tradurre
     * @param variables     le variabili necessarie alle traduzioni passate al nodo di traduzione
     * @return un oggetto contenente i risultati
     * @throws Exception
     */
    public SqlResult translate(VtlExpression vtlExpression, Map<KeyVariables, Object> variables) throws Exception {
        OperatorTranslation semanticFactory = operatori.get(vtlExpression.getClass().getName()).get();
        semanticFactory.setVtlExpression(vtlExpression);
        semanticFactory.setSqlResultService(getSqlType(variables));
        SqlResult results = semanticFactory.translate(variables);
        vtlExpression.setSqlResult(results);
        return results;
    }

    /**
     * Il metodo attiva il servizio di traduzione specifico(oracle, Mysql, ecc) in base alla richiesta ricevuta.
     *
     * @param variables le variabili della richiesta di traduzione
     * @return un servizio di traduzione SQL specifico
     */
    ISqlResultService getSqlType(Map<KeyVariables, Object> variables) {
        if (variables.get(KeyVariables.SQL_TYPE_ORACLE) != null)
            return oracleSqlResultService;
        if (variables.get(KeyVariables.SQL_TYPE_MYSQL) != null)
            return mySqlResultService;
        if (variables.get(KeyVariables.SQL_TYPE_POSTGRES) != null)
            return postgreSqlResultService;
        if (variables.get(KeyVariables.SQL_TYPE_SQL_SERVER) != null)
            return sqlServerResultService;
        return null;
    }
}
