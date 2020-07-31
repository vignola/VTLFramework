package com.capgemini.istat.vtlcompiler.vtllexicon;

import com.capgemini.istat.vtlcompiler.vtlcommon.model.exception.SyntaxException;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.statement.VtlStatement;
import com.capgemini.istat.vtlcompiler.vtlparser.VtlBaseListener;
import com.capgemini.istat.vtlcompiler.vtlparser.VtlLexer;
import com.capgemini.istat.vtlcompiler.vtlparser.VtlListener;
import com.capgemini.istat.vtlcompiler.vtlparser.VtlParser;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;

@Service
public class LexiconService {

	//Public methods-------------------------------------------	                
    
    public void parse(String VTLStatements) throws Exception 
    {
        String str_error = "";
        String[] vtlStatements=null;               
        
        if (!(VTLStatements.toLowerCase().indexOf("define ")>-1)) 
        {        	        	 
        	vtlStatements = splitStatements(VTLStatements);
        }
        else 
        {            	
        	vtlStatements= new String[]{VTLStatements};
        }
        	
        String mainResult=null;                
        
        System.out.println("Syntax validation request [ " +  Calendar.getInstance().getTime() + "]");
        
        for (String statement : vtlStatements) 
        {          
                CharStream stream = new ANTLRInputStream(statement);
                VtlLexer lexer = new VtlLexer(stream);
                CommonTokenStream tokens = new CommonTokenStream(lexer);

                VtlParser parser = new VtlParser(tokens);

                VTLParserErrorListener errorListener = new VTLParserErrorListener();

                parser.removeErrorListeners();
                parser.addErrorListener(errorListener);
                lexer.removeErrorListeners();
                lexer.addErrorListener(errorListener);

                VtlParser.StartContext rContext = parser.start();

                str_error = errorListener.toString();
                 
                System.out.println(statement + " => " + str_error);
                
                int i = 0;
                if (str_error != null) 
                {
                    String[] error = str_error.split("\\--");
                    String line = Integer.toString((Integer.parseInt(error[0]) + i + 1));
                    String column = error[1];
                    String message = error[2];

                    mainResult += "line: " + line + " - column: " + column + " - message: " + message + "<;>";
                }
                i++;
        }           
        
        if (mainResult!=null) throw new SyntaxException(mainResult);
                
    }
        
    public String orderVTLStatements(String vtlStatements) throws Exception 
    {    	
    	List<String> neworder=orderVTLStatementCore(vtlStatements);
    	
    	if (neworder==null) return null;
    	
    	return StringUtils.join(neworder,";");
    }
    
    public List<VtlStatement> orderVTLStatementsToList(String vtlStatements) throws Exception 
    {
    	List<VtlStatement> resultList=new ArrayList<VtlStatement>();
    	        
    	List<String> neworder=orderVTLStatementCore(vtlStatements);
    	
    	if (neworder==null) return null;
    	
        for (String elem:neworder) 
        {        	
        	VtlStatement resItem=new VtlStatement();
        	resItem.setCommand(elem);
        	resultList.add(resItem);
        }

        return resultList;
    }

    public String checkSyntax(String vtlStatements) throws Exception
    {
    	parse(vtlStatements); // If syntax errors are present a Syntax exception will be raised
    	
    /*	if (!(vtlStatements.toLowerCase().indexOf("define ")>-1))
    		return orderVTLStatements(vtlStatements);
    	else*/
    		return vtlStatements;
    	
    }
    
    //Private methods------------------------------------------
    
    private List<String> orderVTLStatementCore(String vtlStatements) throws Exception
    {
    	boolean found;
        
        ArrayList<String> clearedToken;
        List<String> neworder= new ArrayList<String>();;
        ArrayList<String> datasetCreated = new ArrayList<String>();
        ArrayList<ArrayList<String>> statementsFound = new ArrayList<ArrayList<String>>();
        String statementToFind;
        
        CharStream stream;
        CommonTokenStream tokens;
        VtlListener listener;
        VtlLexer lexer;
        VtlParser parser;
        
        StatementOrderParserErrorListener errorListener;
        StatementOrderParserTreeWalker walker;
        
        System.out.println("Ordered statement request ["  + Calendar.getInstance().getTime() + "]");
        
        String[] statementList = splitStatements(vtlStatements);
                
        if (statementList.length==1) 
        {
        	neworder.add(vtlStatements);
        }
        else 
        {        
	        for (int i = 0; i < statementList.length; i++) 
	        {
	            clearedToken = new ArrayList<String>();
	            stream = new ANTLRInputStream(statementList[i]);
	            lexer = new VtlLexer(stream);
	            tokens = new CommonTokenStream(lexer);
	
	            parser = new VtlParser(tokens);
	
	            errorListener = new StatementOrderParserErrorListener();
	
	            lexer.removeErrorListeners();
	            lexer.addErrorListener(errorListener);
	            parser.removeErrorListeners();
	            parser.addErrorListener(errorListener);
	
	            VtlParser.StartContext rContext = parser.start();
	
	            walker = new StatementOrderParserTreeWalker();
	
	            listener = new VtlBaseListener();
	
	            walker.walk(listener, rContext);
	            ArrayList<String> tmp = walker.getTokensFound();
	            String[] tokenList = parser.getTokenNames();
	
	
	            for (int k = 0; k < tmp.size(); k++) 
	            {
	                found = false;
	                String tokenToBeFound = tmp.get(k).trim().toLowerCase();
	                for (int l = 0; l < tokenList.length; l++) 
	                {
	                    if (tokenToBeFound.equals(tokenList[l].trim().toLowerCase().replace("'", ""))) 
	                    {
	                        found = true;
	                        break;
	                    }
	                }
	                if (!found && tokenToBeFound != "")
	                    if (!isNumeric(tokenToBeFound))
	                        clearedToken.add(tokenToBeFound);
	
	            }            
	            datasetCreated.add(clearedToken.get(0));
	            statementsFound.add(clearedToken);
	        }        
	        
	        datasetCreated = selectionSortOrder(datasetCreated, statementsFound);        
	        
	        if (datasetCreated==null) throw new SyntaxException("Loop in the VTL statements");               
	        
	        for (int i = 0; i < datasetCreated.size(); i++) 
	        {
	            String tmpDataset = datasetCreated.get(i);
	            for (int l = 0; l < statementList.length; l++) 
	            {            	
	            	
	            	if (statementList[l].indexOf(":=")>-1) 
	            		statementToFind=statementList[l].split("\\:=")[0];
	            	else
	            		statementToFind=statementList[l].split("\\<-")[0];
	            	
	                if (statementToFind.trim().toLowerCase().equals(tmpDataset.trim().toLowerCase())) {                	
	                    neworder.add(statementList[l]);
	                    break;
	                }
	            }
	        }        
        }
        
        for (String itm:neworder)
        	System.out.println(itm);               
        
        return neworder;
    }
    
    private String removeComments(String wholeStatements) 
    {
        return wholeStatements.replaceAll("((?:(?!\\2|\\\\).|\\\\.)*\\2)|\\/\\/[^\\n]*|\\/\\*(?:[^*]|\\*(?!\\/))*\\*\\/", "");
    }
    
    private String[] splitStatements(String vtlStatements) throws Exception 
    {
        String[] vtlStatementsSplitted;

        vtlStatements = removeComments(vtlStatements);
        vtlStatementsSplitted = vtlStatements.replaceAll("(\\r|\\n)", "").split("(?<=;)");
        for (String str : vtlStatementsSplitted)
            str = str.trim();
        return vtlStatementsSplitted;
    }
    
    private ArrayList<String> selectionSortOrder(ArrayList<String> datasetCreated, ArrayList<ArrayList<String>> statementsFound) throws Exception 
    {
        ArrayList<String> checkLoopList = new ArrayList<String>();
        boolean movement_done = true;
        while (movement_done) {
            movement_done = false;
            for (int i = 0; i < datasetCreated.size(); i++) 
            {
                String elem = datasetCreated.get(i);
                for (int l = 0; l < statementsFound.size(); l++) 
                {
                    try 
                    {
                        ArrayList<String> currentStatement = statementsFound.get(l);
                        if (currentStatement.indexOf(elem) > 0)
                            if (i > l) {
                                checkLoopList.add(i + "--" + l);
                                String tmp = datasetCreated.get(i);
                                String tmp2 = datasetCreated.get(l);
                                ArrayList<String> token = statementsFound.get(l);
                                ArrayList<String> token2 = statementsFound.get(i);

                                datasetCreated.set(l, tmp);
                                datasetCreated.set(i, tmp2);

                                statementsFound.set(i, token);
                                statementsFound.set(l, token2);
                                movement_done = true;
                            }
                    } catch (Exception ex) 
                    {
                        System.out.println("Error " + ex.getMessage());
                    }
                    if (checkLoop(checkLoopList)) return null;
                }
            }
        }

        return datasetCreated;
    }

    private boolean checkLoop(ArrayList<String> couple) throws Exception 
    {
        boolean ris = false;
        for (int i = 0; i < couple.size(); i++) 
        {
            int occurrences = Collections.frequency(couple, couple.get(i));
            if (occurrences > 5) 
            {
                ris = true;
                break;
            }
        }
        return ris;
    }

    private boolean isNumeric(String s) 
    {
        return s != null && s.matches("[-+]?\\d*\\.?\\d+");
    }

}
