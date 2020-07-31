using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.ComponentModel;
using System.Text.RegularExpressions;
using System.Reflection;

namespace VTLlib
{
    public abstract class VTLExpression
    {
     //{
       public  VTLExpressionType type=new VTLExpressionType();
        const char BS = '\\';

        private static string extractString(string text)
        {

            StringBuilder sb = new StringBuilder(text);
            int startIndex = 1; // Skip initial quote
            int slashIndex = -1;

            while ((slashIndex = sb.ToString().IndexOf(BS, startIndex)) != -1)
            {
                char escapeType = sb[slashIndex + 1];
                switch (escapeType)
                {
                    case 'u':
                        string hcode = String.Concat(sb[slashIndex + 4], sb[slashIndex + 5]);
                        string lcode = String.Concat(sb[slashIndex + 2], sb[slashIndex + 3]);
                        char unicodeChar = Encoding.Unicode.GetChars(new byte[] { System.Convert.ToByte(hcode, 16), System.Convert.ToByte(lcode, 16) })[0];
                        sb.Remove(slashIndex, 6).Insert(slashIndex, unicodeChar);
                        break;
                    case 'n': sb.Remove(slashIndex, 2).Insert(slashIndex, '\n'); break;
                    case 'r': sb.Remove(slashIndex, 2).Insert(slashIndex, '\r'); break;
                    case 't': sb.Remove(slashIndex, 2).Insert(slashIndex, '\t'); break;
                    case '\'': sb.Remove(slashIndex, 2).Insert(slashIndex, '\''); break;
                    case '\\': sb.Remove(slashIndex, 2).Insert(slashIndex, '\\'); break;
                    default: throw new ApplicationException("Unvalid escape sequence: \\" + escapeType);
                }

                startIndex = slashIndex + 1;

            }

            sb.Remove(0, 1);
            sb.Remove(sb.Length - 1, 1);

            return sb.ToString();
        }

        //public BinaryExpression And(VTLExpression operand)
        //{
            
        //    return new BinaryExpression(BinaryExpressionType.and, this, operand);
        //}

        //public BinaryExpression And(object operand)
        //{
        //    return new BinaryExpression(BinaryExpressionType.and, this, new ValueExpression(operand));
        //}

        //public BinaryExpression DividedBy(VTLExpression operand)
        //{
        //    return new BinaryExpression(BinaryExpressionType.division, this, operand);
        //}

        //public BinaryExpression DividedBy(object operand)
        //{
        //    return new BinaryExpression(BinaryExpressionType.division, this, new ValueExpression(operand));
        //}

        //public BinaryExpression EqualsTo(VTLExpression operand)
        //{
        //    return new BinaryExpression(BinaryExpressionType.equal, this, operand);
        //}

        //public BinaryExpression EqualsTo(object operand)
        //{
        //    return new BinaryExpression(BinaryExpressionType.equal, this, new ValueExpression(operand));
        //}

        //public BinaryExpression GreaterThan(VTLExpression operand)
        //{
        //    return new BinaryExpression(BinaryExpressionType.greater, this, operand);
        //}

        //public BinaryExpression GreaterThan(object operand)
        //{
        //    return new BinaryExpression(BinaryExpressionType.greater, this, new ValueExpression(operand));
        //}

        //public BinaryExpression GreaterOrEqualThan(VTLExpression operand)
        //{
        //    return new BinaryExpression(BinaryExpressionType.greaterorequal, this, operand);
        //}

        //public BinaryExpression GreaterOrEqualThan(object operand)
        //{
        //    return new BinaryExpression(BinaryExpressionType.greaterorequal, this, new ValueExpression(operand));
        //}

        //public BinaryExpression LesserThan(VTLExpression operand)
        //{
        //    return new BinaryExpression(BinaryExpressionType.lesser, this, operand);
        //}

        //public BinaryExpression LesserThan(object operand)
        //{
        //    return new BinaryExpression(BinaryExpressionType.lesser, this, new ValueExpression(operand));
        //}

        //public BinaryExpression LesserOrEqualThan(VTLExpression operand)
        //{
        //    return new BinaryExpression(BinaryExpressionType.lesserorequal, this, operand);
        //}

        //public BinaryExpression LesserOrEqualThan(object operand)
        //{
        //    return new BinaryExpression(BinaryExpressionType.lesserorequal, this, new ValueExpression(operand));
        //}

        //public BinaryExpression Minus(VTLExpression operand)
        //{
        //    return new BinaryExpression(BinaryExpressionType.minus, this, operand);
        //}

        //public BinaryExpression Minus(object operand)
        //{
        //    return new BinaryExpression(BinaryExpressionType.minus, this, new ValueExpression(operand));
        //}

 
        //public BinaryExpression NotEqual(VTLExpression operand)
        //{
        //    return new BinaryExpression(BinaryExpressionType.notequal, this, operand);
        //}

        //public BinaryExpression NotEqual(object operand)
        //{
        //    return new BinaryExpression(BinaryExpressionType.notequal, this, new ValueExpression(operand));
        //}

        //public BinaryExpression Or(VTLExpression operand)
        //{
        //    return new BinaryExpression(BinaryExpressionType.or, this, operand);
        //}

        //public BinaryExpression Or(object operand)
        //{
        //    return new BinaryExpression(BinaryExpressionType.or, this, new ValueExpression(operand));
        //}

        //public BinaryExpression Plus(VTLExpression operand)
        //{
        //    return new BinaryExpression(BinaryExpressionType.plus, this, operand);
        //}

        //public BinaryExpression Plus(object operand)
        //{
        //    return new BinaryExpression(BinaryExpressionType.plus, this, new ValueExpression(operand));
        //}

        //public BinaryExpression Mult(VTLExpression operand)
        //{
        //    return new BinaryExpression(BinaryExpressionType.moltiplication, this, operand);
        //}

        //public BinaryExpression Mult(object operand)
        //{
        //    return new BinaryExpression(BinaryExpressionType.moltiplication, this, new ValueExpression(operand));
        //}



        //public override string ToString()
        //{
        //    SerializationVisitor serializer = new SerializationVisitor();
        //    this.Accept(serializer);

        //    return serializer.Result.ToString().TrimEnd(' ');
        //}

        //public virtual void Accept(LogicalExpressionVisitor visitor)
        //{
        //    visitor.Visit(this);
        //}
    }
}
