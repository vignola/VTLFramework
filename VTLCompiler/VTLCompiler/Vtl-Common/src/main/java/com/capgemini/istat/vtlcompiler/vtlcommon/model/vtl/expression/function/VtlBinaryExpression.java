package com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.function;

import com.capgemini.istat.vtlcompiler.vtlcommon.model.operator.Operator;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.VtlExpression;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.booleans.VtlBooleanBinaryExpression;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.comparison.VtlComparisonBinaryExpression;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.conditional.VtlConditionalBinaryExpression;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.numeric.VtlNumericBinaryExpression;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.set.VtlSetBinaryExpression;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.string.VtlStringBinaryExpression;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import java.io.Serializable;

@JsonIdentityInfo(
        generator = ObjectIdGenerators.IntSequenceGenerator.class,
        property = "oid"
)
@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.EXISTING_PROPERTY, //field must be present in the POJO
        property = "clazz")

@JsonSubTypes({
        @JsonSubTypes.Type(value = VtlBooleanBinaryExpression.class),
        @JsonSubTypes.Type(value = VtlComparisonBinaryExpression.class),
        @JsonSubTypes.Type(value = VtlConditionalBinaryExpression.class),
        @JsonSubTypes.Type(value = VtlNumericBinaryExpression.class),
        @JsonSubTypes.Type(value = VtlSetBinaryExpression.class),
        @JsonSubTypes.Type(value = VtlStringBinaryExpression.class)
})
public class VtlBinaryExpression extends VtlExpression implements Serializable {

    private VtlExpression leftExpression;
    private VtlExpression rightExpression;

    public VtlBinaryExpression() {

    }

    public VtlBinaryExpression(VtlExpression leftExpression, VtlExpression rightExpression) {
        this.leftExpression = leftExpression;
        this.rightExpression = rightExpression;
    }

    public VtlBinaryExpression(VtlExpression leftExpression, VtlExpression rightExpression, Operator operator) {
        this.leftExpression = leftExpression;
        this.rightExpression = rightExpression;
        setOperator(operator);
    }

    public VtlExpression getLeftExpression() {
        return leftExpression;
    }

    public void setLeftExpression(VtlExpression leftExpression) {
        this.leftExpression = leftExpression;
    }

    public VtlExpression getRightExpression() {
        return rightExpression;
    }

    public void setRightExpression(VtlExpression rightExpression) {
        this.rightExpression = rightExpression;
    }

    @Override
    public String getType() {
        return "VtlBinaryExpression";
    }


}
