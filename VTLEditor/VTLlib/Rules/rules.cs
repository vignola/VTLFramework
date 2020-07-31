using System;
using System.Text.RegularExpressions;
using Antlr4.Runtime;
using Antlr4.Runtime.Tree;

namespace VTLlib
{
    public class rules
    {
        private static int counter=0;
        public string RunParser(String VTLrule)
        {
            // Get our lexer
            AntlrInputStream inputStream = new AntlrInputStream(VTLrule);
            ValidationMlLexer ValidationMlLexer = new ValidationMlLexer(inputStream);


            // Get a list of matched tokens
            CommonTokenStream commonTokenStream = new CommonTokenStream(ValidationMlLexer);

            // Pass the tokens to the parser
            ValidationMlParser ValidationMlParser = new ValidationMlParser(commonTokenStream);

            //var pp = Combined1Parser.AndContext.GetChildContext(null, 23);
            // Specify our entry point

            ValidationMlParser.StartContext rContext = ValidationMlParser.start();

            //Combined1Parser.BooleanExprContext rC = (Combined1Parser.BooleanExprContext)rContext.children[0];

            ////// Walk it and attach our listener
            ParseTreeWalker walker = new ParseTreeWalker();

            ValidationMlBaseListener listener = new ValidationMlBaseListener();

            String str_error = "";

            str_error = walker.Walk(listener, rContext);


            if (!String.IsNullOrEmpty(str_error))
            {
                str_error = "Syntax error: " + str_error;
            }
            else
            {
                str_error = "Success";
            }

            return str_error;
        }

               public class ParseTreeWalker
        {
            public static readonly ParseTreeWalker Default = new ParseTreeWalker();
            string txt = "";
            public virtual string Walk(IParseTreeListener listener, IParseTree t)
            {
                counter++;
                if (t is IErrorNode)
                {
                    listener.VisitErrorNode((IErrorNode)t);                   
                    txt = txt + t.ToString();
                    return txt;
                }
                else
                {
                    if (t is ITerminalNode)
                    {
                        listener.VisitTerminal((ITerminalNode)t);
                        return txt;
                    }
                    
                }
                IRuleNode r = (IRuleNode)t;
                string parentname = r.GetText();
                //Console.WriteLine("##### PARENT " + counter + "####" + parentname + "#####");
                EnterRule(listener, r);
                int n = r.ChildCount;                

                for (int i = 0; i < n; i++)
                {
                    Walk(listener, r.GetChild(i));

                    Console.WriteLine("##### CHILD " + i + " of " + parentname + "####" + r.GetChild(i).GetText() + "#####");
                    
                }

                ExitRule(listener, r);
                return txt; 
            }

            /// <summary>
            /// The discovery of a rule node, involves sending two events: the generic
            /// <see cref="IParseTreeListener.EnterEveryRule(Antlr4.Runtime.ParserRuleContext)">IParseTreeListener.EnterEveryRule(Antlr4.Runtime.ParserRuleContext)</see>
            /// and a
            /// <see cref="Antlr4.Runtime.RuleContext">Antlr4.Runtime.RuleContext</see>
            /// -specific event. First we trigger the generic and then
            /// the rule specific. We to them in reverse order upon finishing the node.
            /// </summary>
            protected internal virtual void EnterRule(IParseTreeListener listener, IRuleNode r)
            {
                ParserRuleContext ctx = (ParserRuleContext)r.RuleContext;
                listener.EnterEveryRule(ctx);
                ctx.EnterRule(listener);
            }

            protected internal virtual void ExitRule(IParseTreeListener listener, IRuleNode r)
            {
                ParserRuleContext ctx = (ParserRuleContext)r.RuleContext;
                ctx.ExitRule(listener);
                listener.ExitEveryRule(ctx);
            }
        }

               public Regex getRegex()
               {
                   Regex rx = new Regex(@"length|and|or|trim|rtrim|ltrim|\(|\)|:=|\[|\]|,|");
                   return rx;

               }
               
    }
}
