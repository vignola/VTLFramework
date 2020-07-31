using System;
using System.Text.RegularExpressions;
using System.Windows.Forms;
using VTL_Editor_PL.classes.config;
using VTL_Editor_PL.classes.tool;

namespace VTL_Editor_PL.classes.common
{
	class CommonItem
	{        
		public static MainApplicationSettings CurrentSettings;
		public static ErrorAndLogManager ErrManger;

		public static string AUTOCOMPLETE_VOCABULARY_PATH = Application.StartupPath + @"\Resources\xmlAutoComplete.xml";

		public const string DEFINE_OPERATOR_STATEMENT=
			"define operator <operator_name> ( <parameter> , ... )\n" + 
			"\treturns <outputType>\n"+
			"\tis <operatorBody> \n"+
			"end operator;";

		public const string DEFINE_DATAPOINTRULESET_STATEMENT=
			"define datapoint ruleset <rulesetName> ( valuedomain | variable <valueDomain or variable>,...) is\n" + 
			"\t<ruleName> : when <antecedentCondition> then <consequentCondition> errorcode <errorCode> errorlevel <errorLevel>\n"+
			"\t;...\n"+
			"end datapoint ruleset;";
		public const string DEFINE_HIERARCHICALRULESET_STATEMENT = 
			"define hierarchical ruleset <rulesetName> ( valuedomain | variable condition <conditionSignature> rule <rule>) is\n" + 
			"\t<ruleName> : when <leftCondition> then <leftCodeItem> errorcode <errorCode> errorlevel <errorLevel>\n"+
			"\t;...\n"+
			"end hierarchical ruleset;";


		public const string SYNTACTIC_VALIDATION_WEBMETHOD = "/engine/checkInstruction";
		public const string SEMANTIC_VALIDATION_WEBMETHOD = "/engine/validate";
		public const string ORDER_STATEMENTS_WEBMETHOD = "/engine/orderInstruction";
		public const string SYNTHESIS_VALIDATION_WEBMETHOD = "/engine/translate";
		public const string SQL_PACKAGE = "/vtlPackage/file";

		public enum SQL_DIALECT 
		{ 
			sqlServer,
			oracleSql,
			mySql,
			postgreSql
		}

		public enum SEMANTIC_RESULT
		{
			xml,
			json
		}

		#region Utility

		public static void WaitOn()
		{
			Cursor.Current = Cursors.WaitCursor;
		}

		public static void WaitOff()
		{
			Cursor.Current = Cursors.Default;
		}

		public static string StripComments(string code)
		{
			var re = @"(@(?:""[^""]*"")+|""(?:[^""\n\\]+|\\.)*""|'(?:[^'\n\\]+|\\.)*')|//.*|/\*(?s:.*?)\*/";
			return Regex.Replace(code, re, "$1");
		}

        public static bool IsXml(string xmlString)
        {
            if (!string.IsNullOrEmpty(xmlString) && xmlString.TrimStart().StartsWith("<"))
            {
                try
                {
                    var doc = System.Xml.Linq.XDocument.Parse(xmlString);
                    return true;
                }
                catch (Exception ex) 
                { 
                    return false;
                }
            }
            else
            {
                return false;   
            }
        }

		#endregion  

		
	}    
}
