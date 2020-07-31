using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace VTLlib
{

    public class Identifier : VTLExpression
        {
            public Identifier(string name)
            {
                Name = name;
            }

            public string Name { get; set; }


            //public override void Accept(VTLExpressionVisitor visitor)
            //{
            //    visitor.Visit(this);
            //}
        }
    
}
