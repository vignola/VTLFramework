package com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression;


import com.capgemini.istat.vtlcompiler.vtlcommon.model.operator.Operator;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.semantic.ResultExpression;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.translation.SqlResult;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.VtlObject;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.constant.VtlConstantExpression;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.constant.VtlOptional;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.dataset.VtlComponentId;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.dataset.VtlVarId;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.clause.VtlClauseOperator;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.clause.VtlDatasetClause;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.clause.aggr.VtlAggrFunctionClauseExpression;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.clause.aggr.VtlAggrFunctionExpression;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.clause.aggr.VtlAggrInvocationExpression;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.clause.aggr.VtlAggregateClauseExpression;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.clause.analytic.*;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.clause.calc.VtlCalcClauseItemExpression;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.clause.rename.VtlRenameClauseItemExpression;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.clause.sub.VtlSubSpaceItemExpression;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.comparison.VtlBetweenExpression;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.comparison.VtlExistIn;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.comparison.VtlInExpression;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.conditional.VtlIfExpression;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.function.VtlBinaryExpression;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.function.VtlUnaryExpression;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.function.VtlUnaryWithOneParameter;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.generic.VtlCastExpression;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.grouping.VtlGroupClauseExpression;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.grouping.VtlHavingClauseExpression;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.hierarchy.VtlHierarchyExpression;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.join.*;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.time.VtlCurrentDate;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.validation.VtlCheck;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.validation.VtlCheckDatapoint;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.validation.VtlCheckHierarchy;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.statement.VtlStatement;
import com.fasterxml.jackson.annotation.*;

import java.io.Serializable;
import java.util.UUID;

@JsonIdentityInfo(
        generator = ObjectIdGenerators.IntSequenceGenerator.class,
        property = "oid"
)
@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.EXISTING_PROPERTY, //field must be present in the POJO
        property = "clazz")

@JsonSubTypes({
        @JsonSubTypes.Type(value = VtlConstantExpression.class),
        @JsonSubTypes.Type(value = VtlOptional.class),

        @JsonSubTypes.Type(value = VtlComponentId.class),
        @JsonSubTypes.Type(value = VtlVarId.class),

        @JsonSubTypes.Type(value = VtlMembership.class),
        @JsonSubTypes.Type(value = VtlParenthesisExpression.class),

        @JsonSubTypes.Type(value = VtlClauseOperator.class),
        @JsonSubTypes.Type(value = VtlDatasetClause.class),

        @JsonSubTypes.Type(value = VtlAggregateClauseExpression.class),
        @JsonSubTypes.Type(value = VtlAggrFunctionClauseExpression.class),
        @JsonSubTypes.Type(value = VtlAggrFunctionExpression.class),
        @JsonSubTypes.Type(value = VtlAggrInvocationExpression.class),

        @JsonSubTypes.Type(value = VtlAnalyticFunctionExpression.class),
        @JsonSubTypes.Type(value = VtlOrderBy.class),
        @JsonSubTypes.Type(value = VtlOrderByItem.class),
        @JsonSubTypes.Type(value = VtlPartitionBy.class),
        @JsonSubTypes.Type(value = VtlWindowing.class),
        @JsonSubTypes.Type(value = VtlWindowingItem.class),
        @JsonSubTypes.Type(value = VtlWindowingLimit.class),

        @JsonSubTypes.Type(value = VtlCalcClauseItemExpression.class),
        @JsonSubTypes.Type(value = VtlRenameClauseItemExpression.class),
        @JsonSubTypes.Type(value = VtlSubSpaceItemExpression.class),

        @JsonSubTypes.Type(value = VtlBetweenExpression.class),
        @JsonSubTypes.Type(value = VtlExistIn.class),
        @JsonSubTypes.Type(value = VtlInExpression.class),

        @JsonSubTypes.Type(value = VtlIfExpression.class),

        @JsonSubTypes.Type(value = VtlBinaryExpression.class),
        @JsonSubTypes.Type(value = VtlCastExpression.class),
        @JsonSubTypes.Type(value = VtlUnaryExpression.class),
        @JsonSubTypes.Type(value = VtlUnaryWithOneParameter.class),

        @JsonSubTypes.Type(value = VtlGroupClauseExpression.class),
        @JsonSubTypes.Type(value = VtlHavingClauseExpression.class),

        @JsonSubTypes.Type(value = VtlHierarchyExpression.class),

        @JsonSubTypes.Type(value = VtlJoinAggrClauseItem.class),
        @JsonSubTypes.Type(value = VtlJoinBody.class),
        @JsonSubTypes.Type(value = VtlJoinComponentClause.class),
        @JsonSubTypes.Type(value = VtlJoinExpression.class),
        @JsonSubTypes.Type(value = VtlJoinSelectClause.class),
        @JsonSubTypes.Type(value = VtlJoinSelectClauseItem.class),
        @JsonSubTypes.Type(value = VtlJoinUsing.class),

        @JsonSubTypes.Type(value = VtlCurrentDate.class),

        @JsonSubTypes.Type(value = VtlCheck.class),
        @JsonSubTypes.Type(value = VtlCheckDatapoint.class),
        @JsonSubTypes.Type(value = VtlCheckHierarchy.class),

        @JsonSubTypes.Type(value = VtlStatement.class)

})
public abstract class VtlExpression implements VtlObject, Serializable {
    private String command;
    private Operator operator;
    private ResultExpression resultExpression;
    private String type;
    private SqlResult sqlResult;
    private UUID requestUuid;

    @JsonProperty
    private String uuid;

    @JsonProperty
    private String clazz = this.getClass().getSimpleName();

    public String getClazz() {
        return clazz;
    }

    public void setClazz(String clazz) {
        this.clazz = clazz;
    }

    public VtlExpression() {
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    @Override
    public String getType() {
        return type;
    }

    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    public Operator getOperator() {
        return operator;
    }

    public void setOperator(Operator operator) {
        this.operator = operator;
    }

    public ResultExpression getResultExpression() {
        return resultExpression;
    }

    public void setResultExpression(ResultExpression resultExpression) {
        this.resultExpression = resultExpression;
    }

    public SqlResult getSqlResult() {
        return sqlResult;
    }

    public void setSqlResult(SqlResult sqlResult) {
        this.sqlResult = sqlResult;
    }

    public UUID getRequestUuid() {
        return requestUuid;
    }

    public void setRequestUuid(UUID requestUuid) {
        this.requestUuid = requestUuid;
    }
}
