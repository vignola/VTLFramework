
namespace VTLlib
{
    abstract class UnaryExpression 
    {
        public class not : Operator
        {
                    
            public not()
            {
                this.Syntax = "not rigthBooleanExpr";
                this.opGroup= VTLGroup.Boolean;
        
            }
 //public String Syntax
 //           {
 //               get { return "not rigthBooleanExpr"; }
 //           }
 //           public opGroup opGroup
 //           {
 //               get { return opGroup.Boolean; }
 //           }
            public int Evaluate(string op)
            {
                return op.Length;
            }
        }
        public class unaryPlus : Operator
        {
             public unaryPlus()
            {
                this.Syntax = "+ rigthNumericExpr";
                this.opGroup= VTLGroup.Numeric;
                 this.Keyword="\\+";
                 this.Name = "unary plus";
            }
           //public String Syntax
           // {
           //     get { return "+ rigthNumericExpr"; }
           // }
           // public String Name
           // {
           //     get { return "unary plus"; }
           // }
           // public opGroup opGroup
           // {
           //     get { return opGroup.Numeric; }
           // }
            //public String Keyword
            //{
            //    get { return "+"; }
            //}
            public int Evaluate(string op)
            {
                return op.Length;
            }
        }

        public class unaryMinus :Operator
        {
            public unaryMinus()
            {
                this.Syntax = "not rigthBooleanExpr";
                this.opGroup= VTLGroup.Numeric;
                this.Keyword="\\-";
                this.Name = "unary minus";
            }
            //public String Syntax
            //{
            //    get { return "- rigthNumericExpr"; }
            //}
            //public String Name
            //{
            //    get { return "unary minus"; }
            //}
            //public opGroup opGroup
            //{
            //    get { return opGroup.Numeric; }
            //}
            //public String Keyword
            //{
            //    get { return "-"; }
            //}
            public int Evaluate(string op)
            {
                return op.Length;
            }
        }


    }
}
