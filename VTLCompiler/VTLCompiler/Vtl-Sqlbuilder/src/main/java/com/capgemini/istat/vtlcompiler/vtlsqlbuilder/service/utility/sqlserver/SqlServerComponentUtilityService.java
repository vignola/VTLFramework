package com.capgemini.istat.vtlcompiler.vtlsqlbuilder.service.utility.sqlserver;

import com.capgemini.istat.vtlcompiler.vtlcommon.model.dataset.VtlType;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.operator.Operator;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.translation.SqlComponent;
import com.capgemini.istat.vtlcompiler.vtlsqlbuilder.service.utility.CommonSqlComponentUtilityService;
import com.healthmarketscience.sqlbuilder.FunctionCall;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Offre le funzionalità di manipolazione dei componenti Sql specifici per SQLServer
 */
@Service
public class SqlServerComponentUtilityService extends CommonSqlComponentUtilityService {

    @Autowired
    public void setSqlObjectUtilityService(SqlServerObjectUtilityService sqlServerObjectUtilityService) {
        super.setSqlObjectUtilityService(sqlServerObjectUtilityService);
    }

    /**
     * effettua la concatenazione fra due componenti virali
     *
     * @param sqlComponentLeft
     * @param sqlComponentRight
     * @return
     */
    @Override
    public SqlComponent applyConcatToViralsComponent(SqlComponent sqlComponentLeft, SqlComponent sqlComponentRight) {
        SqlComponent sqlComponentsResult = new SqlComponent();

        FunctionCall concatLeft = new FunctionCall(Operator.CONCATENATION.getValue());
        concatLeft.addCustomParams(sqlComponentLeft.getResult()).addCustomParams(":");

        FunctionCall concatRight = new FunctionCall(Operator.CONCATENATION.getValue());
        concatRight.addCustomParams(concatLeft).addCustomParams(sqlComponentRight.getResult());

        sqlComponentsResult.setResult(concatRight);

        sqlComponentsResult.setAliasName(sqlComponentLeft.getAliasName());
        return sqlComponentsResult;
    }

    /**
     * applica l'operatore di cast a un componente
     *
     * @param sqlComponent
     * @param mask
     * @param operator
     * @param vtlType
     * @return
     */
    @Override
    public SqlComponent applyCastOperator(SqlComponent sqlComponent, String mask, Operator operator, VtlType vtlType) {

        if (sqlComponent.getVtlComponent().getType().toString().equalsIgnoreCase("DATE") && operator.getValue().equalsIgnoreCase("CAST_TO_STRING")) {
            FunctionCall fc = FunctionCall.customFunction("dbo.vtl_cast_to_string_date")
                    .addCustomParams(sqlComponent.getResult(), sqlComponent.getVtlComponent().getType().toString(), mask != null ? mask : "");
            sqlComponent.setResult(
                    fc
            );
        } else if (sqlComponent.getVtlComponent().getType().toString().equalsIgnoreCase("DATE") && operator.getValue().equalsIgnoreCase("CAST_TO_TIME")) {
            FunctionCall fc = FunctionCall.customFunction("dbo.vtl_cast_to_time_date")
                    .addCustomParams(sqlComponent.getResult(), sqlComponent.getVtlComponent().getType().toString(), mask != null ? mask : "");
            sqlComponent.setResult(
                    fc
            );
        } else{
            FunctionCall fc = FunctionCall.customFunction(sqlObjectUtilityService.getSqLOperator(operator.getValue()))
                    .addCustomParams(sqlComponent.getResult(), sqlComponent.getVtlComponent().getType().toString(), mask != null ? mask : "");
            sqlComponent.setResult(
                    fc
            );
        }
        sqlComponent.setAliasName(vtlType.getDefaultName());
        return sqlComponent;
    }
}
