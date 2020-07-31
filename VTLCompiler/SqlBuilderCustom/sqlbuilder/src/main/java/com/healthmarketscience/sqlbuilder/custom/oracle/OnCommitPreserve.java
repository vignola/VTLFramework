package com.healthmarketscience.sqlbuilder.custom.oracle;


import com.healthmarketscience.common.util.AppendableExt;
import com.healthmarketscience.sqlbuilder.CreateTableQuery;
import com.healthmarketscience.sqlbuilder.ValidationContext;
import com.healthmarketscience.sqlbuilder.custom.CustomSyntax;
import com.healthmarketscience.sqlbuilder.custom.HookType;

import java.io.IOException;

public class OnCommitPreserve extends CustomSyntax {

    @Override
    public void apply(CreateTableQuery query) {
        query.addCustomization(CreateTableQuery.Hook.TABLE, HookType.AFTER, this);
    }

    @Override
    public void appendTo(AppendableExt app) throws IOException {
        app.append(" ON COMMIT PRESERVE ROWS ");
    }

    @Override
    protected void collectSchemaObjects(ValidationContext vContext) {

    }
}
