package com.healthmarketscience.sqlbuilder;

import com.healthmarketscience.common.util.AppendableExt;

import java.io.IOException;

public class CreateTableQueryInto extends CreateTableQuery {
	
	private String alas;
	
	public CreateTableQueryInto(Object tableStr) {
		super(tableStr);
		// TODO Auto-generated constructor stub
	}
	
	public CreateTableQueryInto(Object tableStr, String alias) {
		super(tableStr);
		alas = alias;
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void appendTo(AppendableExt app, SqlContext newContext) throws IOException {
		
		newContext.setUseTableAliases(false);

        customAppendTo(app, Hook.HEADER);

        app.append("SELECT ");
        if (_tableType != null) {
            app.append(_tableType);
        }
        
        String alias = alas;
        String aliasAll = alias+".* ";
        app.append(aliasAll);
        
        customAppendTo(app, Hook.TABLE, "INTO ").append(_object);

        if (_sqlObject == null) {
            app.append(" (").append(_columns);
            if (!_constraints.isEmpty()) {
                app.append(",").append(_constraints);
            }
            app.append(")");
        } else {
        	app.append(" FROM ");
            app.append(_sqlObject);
            app.append(" "+alias);
        }
        customAppendTo(app, Hook.TRAILER);
	}



}
