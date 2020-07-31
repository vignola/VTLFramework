package com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.define.ruleset;

import java.io.Serializable;

public class VarParameter implements Serializable {

    private String name;
    private String alias;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }
}
