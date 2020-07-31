package com.capgemini.istat.vtlcompiler.vtlsqlbuilder.expression.join;

import com.capgemini.istat.vtlcompiler.vtlcommon.model.KeyVariables;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.operator.Operator;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.translation.SqlResult;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.VtlExpression;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.join.VtlJoinSelectClause;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.join.VtlJoinSelectClauseItem;
import com.capgemini.istat.vtlcompiler.vtlsqlbuilder.TranslationFactory;
import com.capgemini.istat.vtlcompiler.vtlsqlbuilder.model.OperatorTranslation;
import com.capgemini.istat.vtlcompiler.vtlsqlbuilder.service.sqlresult.ISqlResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.Map;

@Component
@Scope("prototype")
public class JoinSelectClauseTranslation implements OperatorTranslation {
    private VtlJoinSelectClause vtlJoinSelectClause;
    private TranslationFactory translationFactory;
    private ISqlResultService sqlResultService;

    @Override
    public void setVtlExpression(VtlExpression vtlExpression) {
        this.vtlJoinSelectClause = (VtlJoinSelectClause) vtlExpression;
    }

    @Autowired
    public void setTranslationFactory(TranslationFactory translationFactory) {
        this.translationFactory = translationFactory;
    }


    public void setSqlResultService(ISqlResultService sqlResultService) {
        this.sqlResultService = sqlResultService;
    }


    @Override
    public SqlResult translate(Map<KeyVariables, Object> variables) throws Exception {
        SqlResult sqlResult;
        LinkedList<SqlResult> sqlValidationItems = new LinkedList<>();
        for(VtlJoinSelectClauseItem vtlJoinSelectClauseItem : vtlJoinSelectClause.getVtlJoinSelectClauseItems()){
            translationFactory.translate(vtlJoinSelectClauseItem, variables);
                sqlValidationItems.addLast(
                        sqlResultService.renameTableAndCreateTemporaryTable(vtlJoinSelectClauseItem.getVtlExpression(), vtlJoinSelectClauseItem.getAsName())
                );

        }

        sqlResult = sqlResultService.preJoinRenames(vtlJoinSelectClause, (Operator) variables.get(KeyVariables.JOIN_OPERATOR));

        return sqlResult;
    }


}
