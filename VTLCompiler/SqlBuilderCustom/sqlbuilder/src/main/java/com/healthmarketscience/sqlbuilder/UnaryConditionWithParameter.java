package com.healthmarketscience.sqlbuilder;

import com.healthmarketscience.common.util.AppendableExt;

import java.io.IOException;
import java.io.Serializable;

public class UnaryConditionWithParameter extends Condition implements Serializable {

    /**
     * Interface which can be implemented to provide a custom unary operation.
     */
    public interface CustomUnaryWithParameterOp {

        /**
         * Returns {@code true} if the operator comes before the value, {@code
         * false} otherwise.
         *
         * @return
         */
        public boolean isPrefixOp();

        /**
         * Returns the Converter which handles the {@code Object} -&gt; {@code
         * SqlObject} conversion for the operation value.
         *
         * @return
         */
        public Converter<Object, ? extends SqlObject> getConverter();
    }

    /**
     * Enum representing the unary operations supported in a SQL condition, e.g.
     * <code>"(&lt;column&gt; &lt;unaryOp&gt;)"</code> or
     * <code>"(&lt;unaryOp&gt; &lt;column&gt;)"</code>.
     */
    public enum Op implements CustomUnaryWithParameterOp {
        /**
         * {@code Object} -&gt; {@code SqlObject} conversions handled by
         * {@link Converter#COLUMN_VALUE_TO_OBJ}.
         */
        REGEXP_LIKE("REGEXP_LIKE ", true, Converter.COLUMN_VALUE_TO_OBJ);

        private final String _opStr;
        private final boolean _isPrefixOp;
        private final Converter<Object, ? extends SqlObject> _converter;

        private Op(String opStr, boolean isPrefixOp,
                   Converter<Object, ? extends SqlObject> converter) {
            _opStr = opStr;
            _isPrefixOp = isPrefixOp;
            _converter = converter;

        }

        @Override
        public boolean isPrefixOp() {
            return _isPrefixOp;
        }

        @Override
        public Converter<Object, ? extends SqlObject> getConverter() {
            return _converter;
        }

        @Override
        public String toString() {
            return _opStr;
        }
    }

    private CustomUnaryWithParameterOp _unaryOp;
    private SqlObject _value;
    private ValueObject _parameter;

    public UnaryConditionWithParameter(Op unaryOp, SqlObject obj, ValueObject parameter) {
        this(unaryOp, (Object) obj, parameter);
    }

    /**
     * {@code Object} -&gt; {@code SqlObject} conversions handled by
     * {@link Op#getConverter}.
     *
     * @param unaryOp
     * @param value
     * @param parameter
     */
    public UnaryConditionWithParameter(Op unaryOp, Object value, ValueObject parameter) {
        this((CustomUnaryWithParameterOp) unaryOp, value, parameter);
    }

    /**
     * {@code Object} -&gt; {@code SqlObject} conversions handled by
     * {@link CustomUnaryWithParameterOp#getConverter}.
     *
     * @param unaryOp
     * @param value
     * @param parameter
     */
    public UnaryConditionWithParameter(CustomUnaryWithParameterOp unaryOp, Object value, ValueObject parameter) {
        _unaryOp = unaryOp;
        _value = _unaryOp.getConverter().convert(value);
        _parameter = parameter;
    }

    @Override
    protected void collectSchemaObjects(ValidationContext vContext) {
        _value.collectSchemaObjects(vContext);
    }

    @Override
    public void appendTo(AppendableExt app) throws IOException {
        if (_unaryOp.isPrefixOp()) {
            app.append(_unaryOp);
            openParen(app);
            app.append(_value).append(",").append(_parameter);
            closeParen(app);
        } else {
            openParen(app);
            app.append(_value).append(_unaryOp);
            closeParen(app);
        }
    }

    /**
     * Convenience method for generating a Condition for testing if a column is
     * {@code NULL}.
     * <p>
     * {@code Object} -&gt; {@code SqlObject} conversions handled by
     * {@link Converter#COLUMN_VALUE_TO_OBJ}.
     *
     * @param value
     * @param parameter
     * @return UnaryConditionWithParameter
     */
    public static UnaryConditionWithParameter regexpLike(Object value, ValueObject parameter) {
        return new UnaryConditionWithParameter(Op.REGEXP_LIKE, value, parameter);
    }
}
