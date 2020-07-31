package com.capgemini.istat.vtlcompiler.vtlsqlbuilder.expression.join;

import com.capgemini.istat.vtlcompiler.vtlcommon.model.KeyVariables;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.translation.SqlResult;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.VtlExpression;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.join.VtlJoinSelectClauseItem;
import com.capgemini.istat.vtlcompiler.vtlsqlbuilder.TranslationFactory;
import com.capgemini.istat.vtlcompiler.vtlsqlbuilder.model.OperatorTranslation;
import com.capgemini.istat.vtlcompiler.vtlsqlbuilder.service.sqlresult.ISqlResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@Scope("prototype")
public class JoinSelectClauseItemTranslation implements OperatorTranslation {
    private VtlJoinSelectClauseItem vtlJoinSelectClauseItem;
    private TranslationFactory translationFactory;

    @Override
    public void setSqlResultService(ISqlResultService sqlResultService) {
        //non serve
    }

    @Override
    public void setVtlExpression(VtlExpression vtlExpression) {
        this.vtlJoinSelectClauseItem = (VtlJoinSelectClauseItem) vtlExpression;
    }

    @Autowired
    public void setTranslationFactory(TranslationFactory translationFactory) {
        this.translationFactory = translationFactory;
    }

    @Override
    public SqlResult translate(Map<KeyVariables, Object> variables) throws Exception {
        return translationFactory.translate(vtlJoinSelectClauseItem.getVtlExpression(), variables);
    }
}
