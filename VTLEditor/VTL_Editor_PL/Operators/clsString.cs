using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace VTL_Editor_PL.Operators.DML
{
    class clsString : clsOperators
    {
        public List<clsOperators> getStringOperators(){
        List<clsOperators> StringOperatorsList = new List<clsOperators>();

            //length operator
            clsString length = new clsString();
            length.belongingGroup = "String";
            length.name = "length";
            //this.operand = ["StringExpr"];
            length.syntax = "length ( <<StringExpr>> ) ";
            length.type = "functional";
            StringOperatorsList.Add(length);

            //concatenation operator
            clsString concat = new clsString();
            concat.belongingGroup = "String";
            concat.name = "concatenation";
            //this.operand = ["StringExpr"];
            concat.syntax = "<<StringExpr>> || <<StringExpr>>";
            concat.type = "infix";
            StringOperatorsList.Add(concat);
            
            
            //trim operator
            clsString trim = new clsString();
            trim.belongingGroup = "String";
            trim.name = "trim";
            //this.operand = ["StringExpr"];
            trim.syntax = "trim(<<StringExpr>>)";
            trim.type = "functional";
            StringOperatorsList.Add(trim);

            //rtrim operator
            clsString rtrim = new clsString();
            rtrim.belongingGroup = "String";
            rtrim.name = "rtrim";
            //this.operand = ["StringExpr"];
            rtrim.syntax = "rtrim(<<StringExpr>>)";
            rtrim.type = "functional";
            StringOperatorsList.Add(rtrim);

           //ltrim operator
            clsString ltrim = new clsString();
            ltrim.belongingGroup = "String";
            ltrim.name = "ltrim";
            //this.operand = ["StringExpr"];
            ltrim.syntax = "ltrim(<<StringExpr>>)";
            ltrim.type = "functional";
            StringOperatorsList.Add(ltrim);

            //upper operator
            clsString upper = new clsString();
            upper.belongingGroup = "String";
            upper.name = "upper";
            //this.operand = ["StringExpr"];
            upper.syntax = "upper(<<StringExpr>>)";
            upper.type = "functional";
            StringOperatorsList.Add(upper);

            //lower operator
            clsString lower = new clsString();
            lower.belongingGroup = "String";
            lower.name = "lower";
            //this.operand = ["StringExpr"];
            lower.syntax = "lower(<<StringExpr>>)";
            lower.type = "functional";
            StringOperatorsList.Add(lower);

            

            //substr operator
            clsString substr = new clsString();
            substr.belongingGroup = "String";
            substr.name = "substr";
            //this.operand = ["StringExpr"];
            substr.syntax = "substr(<<StringExpr>> {, startPosition} {, length} )";
            substr.type = "functional";
            StringOperatorsList.Add(substr);

           








        return StringOperatorsList;

        }
        public clsOperators getOperator(string operatorName)
        {
            clsOperators StringOperator = new clsOperators();
            List<clsOperators> StringOperatorsList = new List<clsOperators>();
            StringOperatorsList = getStringOperators();
            for (int i = 0; i < StringOperatorsList.Count(); i++)
            {
                if (StringOperatorsList[i].name == operatorName) StringOperator = StringOperatorsList[i];
            }
            

            return StringOperator;

        }

    }
}
