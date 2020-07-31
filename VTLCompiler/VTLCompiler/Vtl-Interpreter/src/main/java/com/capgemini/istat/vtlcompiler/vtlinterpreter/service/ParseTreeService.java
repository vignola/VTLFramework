package com.capgemini.istat.vtlcompiler.vtlinterpreter.service;


import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.statement.VtlStatement;
import com.capgemini.istat.vtlcompiler.vtlinterpreter.visitor.StartVisitor;
import com.capgemini.istat.vtlcompiler.vtlparser.VtlLexer;
import com.capgemini.istat.vtlcompiler.vtlparser.VtlParser;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Questa classe si occupa di iniziare il parsing dell'albero di validazione sintattica
 * Da questa classe parte la navigazione e interpretazione degli statement attraverso gli strumenti offerti da ANTLR
 */
@Service
public class ParseTreeService implements IParseTreeService {

    private StartVisitor startVisitor;
    private Map<String, Object> variables;

    @Autowired
    public void setStartVisitor(StartVisitor startVisitor) {
        this.startVisitor = startVisitor;
    }

    /**
     * Il metodo prende in ingresso degli statement e restituisce degli oggetti che rappresentano gli statement in modo
     * che possano essere utilizzati dai diversi motori della piattaforma il motore di interpretazione in ogni suo componente
     * inizializza una serie di variabili che possono essere utilizzate durante l'interpretazione
     *
     * @param vtlCommands la lista dei comandi da interpretare
     * @return lista vtlstatement interpretati dal motore di validazione
     */
    @Override
    public List<VtlStatement> visitTree(String vtlCommands) {
        this.variables = new HashMap<>();
        VtlLexer lexer = new VtlLexer(CharStreams.fromString(vtlCommands));
        CommonTokenStream commonTokenStream = new CommonTokenStream(lexer);
        VtlParser parser = new VtlParser(commonTokenStream);
        List<VtlStatement> result = startVisitor.visit(parser.start());
        return result;
    }

    public Map<String, Object> getVariables() {
        if (variables == null)
            this.variables = new HashMap<>();
        return variables;
    }

    public void setVariables(Map<String, Object> variables) {
        this.variables = variables;
    }


}
