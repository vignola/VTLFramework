

namespace VTLlib
{
    public  class BinaryExpression 
	{
       
 
        public class and : Operator
        {
            
            public and()
            {
                this.Syntax = "leftBooleanExpr and rigthBooleanExpr";
                this.opGroup= VTLGroup.Boolean;
                this.Description = "calculates the logical AND";
                this.Keyword = "and";
            }
 
            public int Evaluate(string op)
            {
                return op.Length;
            }
        }



        public class or : Operator
        {
                      
            public or()
            {
                this.Syntax = "leftBooleanExpr or rigthBooleanExpr";
                this.opGroup= VTLGroup.Boolean;
                this.Keyword = "or";
        
            }
            public int Evaluate(string op)
            {
                return op.Length;
            }
        }

        public class xor : Operator
        {
            public xor()
            {
                this.Syntax = "leftBooleanExpr xor rigthBooleanExpr";
                this.opGroup= VTLGroup.Boolean;
                this.Keyword = "xor";
        
            }           
             public int Evaluate(string op)
            {
                return op.Length;
            }
        }

        public class notEqual : Operator
        {
            public notEqual()
            {
                
               this.Syntax = "leftExpr <> rigthExpr";
               this.opGroup= VTLGroup.Comparison;
               this.Keyword="\\<\\>";
               this.Name="not equal";

            }            
            public int Evaluate(string op)
            {
                return op.Length;
            }
        }
        public class lessOrEqual : Operator
        {
            public lessOrEqual()
            {

                this.Syntax = "leftExpr <= rigthExpr";
               this.opGroup= VTLGroup.Comparison;
               this.Keyword = "\\<=";
               this.Name = "less or equal";

            }            
             public int Evaluate(string op)
            {
                return op.Length;
            }
        }
        public class greaterOrEqual : Operator
        {
            public greaterOrEqual()
            {

                this.Syntax = "leftExpr >= rigthExpr";
               this.opGroup= VTLGroup.Comparison;
               this.Keyword = "\\>=";
               this.Name = "greater or equal";

            }            
            public int Evaluate(string op)
            {
                return op.Length;
            }
        }
        public class less : Operator
        {
            public less()
            {

                this.Syntax = "leftExpr < rigthExpr";
               this.opGroup= VTLGroup.Comparison;
               this.Keyword = "\\<";

            }   
            public int Evaluate(string op)
            {
                return op.Length;
            }
        }

        public class greater : Operator
        {
           public greater()
            {

                this.Syntax = "leftExpr > rigthExpr";
               this.opGroup= VTLGroup.Comparison;
               this.Keyword = "\\>";
               //this.Name = "greater or equal";

            }           
        }
        public class equal : Operator
        {
            public equal()
            {

                this.Syntax = "leftExpr = rigthExpr";
               this.opGroup= VTLGroup.Comparison;
               this.Keyword = "\\=";
               //this.Name = "equal";

            }                 
            public int Evaluate(string op)
            {
                return op.Length;
            }
        }
        public class minus : Operator
        {
           public minus()
            {

                this.Syntax = "leftNumericExpr - rigthNumericExpr";
               this.opGroup= VTLGroup.Numeric;
               this.Keyword = "\\-";
               //this.Name = "minus";

            }                  
            public int Evaluate(string op)
            {
                return op.Length;
            }
        }

        public class plus : Operator
        {
           public plus()
            {

                this.Syntax = "leftNumericExpr + rigthNumericExpr";
               this.opGroup= VTLGroup.Numeric;
               this.Keyword = "\\+";
               //this.Name = "minus";

            } 
            public int Evaluate(string op)
            {
                return op.Length;
            }
        }
        public class division : Operator
        {
            public division()
            {

                this.Syntax = "leftNumericExpr / rigthNumericExpr";
               this.opGroup= VTLGroup.Numeric;
               this.Keyword = "\\/";
               //this.Name = "minus";

            } 
            public int Evaluate(string op)
            {
                return op.Length;
            }
        }

        public class multiplication : Operator
        {
           public multiplication()
            {

               this.Syntax = "leftNumericExpr * rigthNumericExpr";
               this.opGroup= VTLGroup.Numeric;
               this.Keyword = "\\*";
               //this.Name = "minus";

            } 
            public int Evaluate(string op)
            {
                return op.Length;
            }
        }
        public class assignment : Operator
        {
            public assignment()
            {

                this.Syntax = "leftExpr := rigthExpr";
               this.opGroup= VTLGroup.General;
               this.Keyword = "\\:\\=";
              

            } 
            public int Evaluate(string op)
            {
                return op.Length;
            }
        }
        public class put : Operator
        {
            public put()
            {

                this.Syntax = "leftExpr <- rigthExpr";
               this.opGroup= VTLGroup.General;
               this.Keyword = "\\<\\-";
               //this.Name = "minus";

            } 
            public int Evaluate(string op)
            {
                return op.Length;
            }
        }

        public class concatenation : Operator
        {
           public concatenation()
            {

                this.Syntax = "leftExpr || rigthExpr";
               this.opGroup= VTLGroup.String;
               this.Keyword = "\\|\\|";
               //this.Name = "minus";

            } 
 
            public int Evaluate(string op)
            {
                return op.Length;
            }
        }      
        
        }

    }

   


