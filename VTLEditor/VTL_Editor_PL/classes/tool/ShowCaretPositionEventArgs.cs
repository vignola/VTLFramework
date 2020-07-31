using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace VTL_Editor_PL.classes.tool
{
    public class ShowCaretPositionEventArgs:EventArgs
    {
        private int _lineNumber=-1;
        private int _columnNumber=-1;

        public int LineNumber
        {
            get{ return _lineNumber;}
            
        }

        public int ColumnNumber{
            get{return _columnNumber;}        
        }

        public ShowCaretPositionEventArgs(int line, int column)
        {
            _lineNumber=line;
            _columnNumber=column;
        }   
    }
}