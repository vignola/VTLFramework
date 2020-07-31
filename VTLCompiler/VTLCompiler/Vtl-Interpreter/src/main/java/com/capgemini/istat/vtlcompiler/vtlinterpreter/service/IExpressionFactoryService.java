package com.capgemini.istat.vtlcompiler.vtlinterpreter.service;

import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.dataset.VtlComponentId;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.VtlExpression;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.booleans.VtlBooleanBinaryExpression;

public interface IExpressionFactoryService {


    VtlExpression arithmeticFactory(String operator, VtlExpression leftExpression, VtlExpression rightExpression);

    VtlExpression membershipFactory(VtlExpression expression, VtlComponentId vtlComponentId);

    VtlExpression comparisonFactory(String operator, VtlExpression leftExpression, VtlExpression rightExpression);

    VtlExpression unaryFactory(String operator, VtlExpression expression);

    VtlBooleanBinaryExpression booleanFactory(String operator, VtlExpression leftExpression, VtlExpression rightExpression);
}
