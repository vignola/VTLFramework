package com.capgemini.istat.vtlcompiler.vtlinterpreter.visitor;

import com.capgemini.istat.vtlcompiler.vtlcommon.model.KeyVariables;
import com.capgemini.istat.vtlcompiler.vtlparser.VtlBaseVisitor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.Map;

public abstract class InterpreterVisitor<T> extends VtlBaseVisitor<T> {
    public static final Logger logger = LogManager.getLogger(InterpreterVisitor.class);
    private Map<KeyVariables, Object> variables;

    public Map<KeyVariables, Object> getVariables() {
        if (variables == null)
            this.variables = new HashMap<>();
        return variables;
    }

    public void setVariables(Map<KeyVariables, Object> variables) {
        this.variables = variables;
    }
}
