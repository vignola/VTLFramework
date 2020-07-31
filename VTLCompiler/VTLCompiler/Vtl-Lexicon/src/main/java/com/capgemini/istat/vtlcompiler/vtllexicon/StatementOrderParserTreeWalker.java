package com.capgemini.istat.vtlcompiler.vtllexicon;

import org.antlr.v4.runtime.tree.*;

import java.util.ArrayList;

public class StatementOrderParserTreeWalker {
    static String txt = "";
    private static ArrayList<String> _tokenFound;

    public StatementOrderParserTreeWalker() {
        _tokenFound = new ArrayList<String>();
    }

    public boolean walk(ParseTreeListener listener, ParseTree t) {
        try {
            if ((t instanceof ErrorNode)) {
                listener.visitErrorNode(((ErrorNode) (t)));
            } else if ((t instanceof TerminalNode)) {
                listener.visitTerminal(((TerminalNode) (t)));
                if (t.toStringTree() != "<EOF>") {
                    _tokenFound.add(t.toStringTree());
                    //RuleNode r = ((RuleNode)(t.getParent()));
		            	 /*if (r.getRuleContext().getClass().toString().indexOf("LnAtomContext")>-1)
		            	 System.out.println("RULE: " + r.getRuleContext().getText());*/
                    //System.out.println("RULE: " + r.getRuleContext().getClass().toString() + "  --  " + r.getRuleContext().getText());
                }
            }

            RuleNode r = ((RuleNode) (t));


            int n = r.getChildCount();

            for (int i = 0; (i < n); i++) {
                this.walk(listener, r.getChild(i));
            }
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

    public ArrayList<String> getTokensFound() {
        return _tokenFound;
    }
}
