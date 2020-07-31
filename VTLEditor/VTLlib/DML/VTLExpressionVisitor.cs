using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace VTLlib
{
    

        public abstract class VTLExpressionVisitor
        {
            public abstract void Visit(VTLExpression expression);
            //public abstract void Visit(TernaryExpression expression);
            public abstract void Visit(BinaryExpression expression);
            //public abstract void Visit(UnaryExpression expression);
            //public abstract void Visit(ValueExpression expression);
            public abstract void Visit(Function function);
            public abstract void Visit(Identifier function);
        }
   
}
