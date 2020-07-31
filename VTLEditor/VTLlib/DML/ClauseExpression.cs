
namespace VTLlib
{
    class ClauseExpression
    {

        public class calc : Operator
        {

            public calc()
            {
                this.Name = "calc";
                this.Syntax = "ScalarExpr [calc {role} comp := calc_comp {, {role} comp := calc_comp}*]";
                this.opGroup = VTLGroup.Clauses;
                this.Keyword = "calc";
                this.Description = "calculation of a component";
            }
            public void Evaluate()
            {

            }
        
        }
        public class drop : Operator
        {

            public drop()
            {
                this.Name = "drop";
                this.Syntax = "ScalarExpr [drop comp_list ]";
                this.opGroup = VTLGroup.Clauses;
                this.Keyword = "drop";
                this.Description = "removal of a component";
            }
            public void Evaluate()
            {

            }

        }
        public class keep : Operator
        {

            public keep()
            {
                this.Name = "keep";
                this.Syntax = "ScalarExpr [keep comp_list ]";
                this.opGroup = VTLGroup.Clauses;
                this.Keyword = "keep";
                this.Description = "maintaining components";
            }
            public void Evaluate()
            {

            }

        }
        public class filter : Operator
        {

            public filter()
            {
                this.Name = "filter";
                this.Syntax = "ScalarExpr [filter BooleanExpr]";
                this.opGroup = VTLGroup.Clauses;
                this.Keyword = "filter";
                this.Description = "filtering components";
            }
            public void Evaluate()
            {

            }

        }
        public class rename : Operator
        {

            public rename()
            {
                this.Name = "rename";
                this.Syntax = "ScalarExpr [rename comp_from to comp_to {, comp_from to comp_to }* ]";
                this.opGroup = VTLGroup.Clauses;
                this.Keyword = "rename|to";
                this.Description = "renaming components";
            }
            public void Evaluate()
            {

            }

        }
        public class pivot : Operator
        {

            public pivot()
            {
                this.Name = "pivot";
                this.Syntax = "ScalarExpr [pivot elem_list to dim , msr]";
                this.opGroup = VTLGroup.Clauses;
                this.Keyword = "pivot|to";
                this.Description = "pivoting";
            }
            public void Evaluate()
            {

            }

        }
        public class unpivot : Operator
        {

            public unpivot()
            {
                this.Name = "unpivot";
                this.Syntax = "ScalarExpr [unpivot dim, msr {to elem_list}]";
                this.opGroup = VTLGroup.Clauses;
                this.Keyword = "unpivot|to";
                this.Description = "unpivoting";
            }
            public void Evaluate()
            {

            }

        }
        public class subspace : Operator
        {

            public subspace()
            {
                this.Name = "sub";
                this.Syntax = "ScalarExpr [sub ident = value { , ident = value }*]";
                this.opGroup = VTLGroup.Clauses;
                this.Keyword = "sub";
            }
            public void Evaluate()
            {

            }

        }
    }
}
