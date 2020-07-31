using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace VTL_Editor_PL
{
    class clsOperators
    {
        public static int currentID;
        
        //Properties.
        public string belongingGroup { get; set; }
        public string syntax { get; set; }
        //protected List<String> operand { get; set; }
        public string name { get; set; }
        public string type { get; set; }
        
        // Default constructor. If a derived class does not invoke a base-
        // class constructor explicitly, the default constructor is called
        // implicitly.
        public clsOperators()
        {
        }
        
        // Instance constructor 
        public clsOperators(string belongingGroup, string syntax, List<String> operand, string name, string type)
        {
            this.belongingGroup = belongingGroup;
            this.syntax = syntax;
            //this.operand = operand;
            this.name = name;
            this.type = type;

        }


        public List<string> loadGroup()
        {
            List<string> operatorGroups=new List<string>();
            operatorGroups.Add("String");
            operatorGroups.Add("Boolean");
            operatorGroups.Add("Comparison");
            operatorGroups.Add("Date");



            return operatorGroups;
        }
        public List<string> loadOperators(string group)
        {
            List<string> operators = new List<string>();
            

      switch (group)
      {
          case "String":
              operators.Add("length");
              operators.Add("instr");
              operators.Add("substr");
              operators.Add("upper");
              break;
          case "Boolean":
              operators.Add("and");
              operators.Add("or");
              operators.Add("xor");
              operators.Add("not");
              break;
          case "Comparison":
              operators.Add("greater than");
              operators.Add("greater or equal than");
              operators.Add("lower than");
              operators.Add("lower or equal than");
              break;
          case "Date":
              operators.Add("extract");
              operators.Add("current_date");

              break;          
      }
   
           

            return operators;
        }


        public string loadSyntax(string operatorName,string group)
        {
           string strSyntax;


            switch (operatorName)
            {
                case "String":
                    
                //    operators.Add("length");
                //    operators.Add("instr");
                //    operators.Add("substr");
                //    operators.Add("upper");
                //    break;
                //case "Boolean":
                //    operators.Add("and");
                //    operators.Add("or");
                //    operators.Add("xor");
                //    operators.Add("not");
                //    break;
                //case "Comparison":
                //    operators.Add("greater than");
                //    operators.Add("greater or equal than");
                //    operators.Add("lower than");
                //    operators.Add("lower or equal than");
                //    break;
                //case "Date":
                //    operators.Add("extract");
                //    operators.Add("current_date");

                    break;
            }



            return syntax;
        }

    }
}
