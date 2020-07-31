package com.capgemini.istat.vtlcompiler.vtlsqlbuilder.expression.terminal;

import com.capgemini.istat.vtlcompiler.vtlcommon.model.KeyVariables;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.translation.SqlResult;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.constant.VtlConstantExpression;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.VtlExpression;
import com.capgemini.istat.vtlcompiler.vtlsqlbuilder.model.OperatorTranslation;
import com.capgemini.istat.vtlcompiler.vtlsqlbuilder.service.sqlresult.ISqlResultService;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@Scope("prototype")
public class ConstantTranslation implements OperatorTranslation {
    private VtlConstantExpression vtlConstantExpression;
    private ISqlResultService sqlResultService;


    @Override
    public void setVtlExpression(VtlExpression vtlExpression) {
        this.vtlConstantExpression = (VtlConstantExpression) vtlExpression;
    }


    public void setSqlResultService(ISqlResultService sqlResultService) {
        this.sqlResultService = sqlResultService;
    }

    @Override
    public SqlResult translate(Map<KeyVariables, Object> variables) throws Exception {
        return sqlResultService.createConstantSql(vtlConstantExpression);
    }
}
