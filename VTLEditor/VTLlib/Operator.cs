using System;
using System.Collections.Generic;
using System.Linq;
using System.Reflection;
using System.Text.RegularExpressions;

namespace VTLlib
{
    public   class Operator
    {
        public String Syntax { get; set; }
        public VTLGroup opGroup { get; set; }
        public String Name { get; set; }
        public String Keyword { get; set; }
        public String Description { get; set; }
        public  void Evaluate(){}

      public String GetOperatorProperty(Type t,VTLOperatorProperty prop, String opName)
        
      {
            string name;
            String syntax="";
            foreach (Type type in Assembly.GetAssembly(typeof(Operator)).GetTypes()
                    .Where(myType => myType.IsClass && !myType.IsAbstract && myType.IsSubclassOf(typeof(Operator))))  //
            {
          //Type type=typeof(Operator).GetNestedType(opName).GetType();
               Object objInstance = Activator.CreateInstance(type);
                PropertyInfo nameProperty = type.GetProperty("Name");
                PropertyInfo syntaxProperty = type.GetProperty(prop.ToString());

                if (nameProperty.GetValue(objInstance) != null && !String.IsNullOrEmpty(nameProperty.ToString())) name = nameProperty.GetValue(objInstance).ToString();
                else name = type.Name;

                if (name == opName)
                {
                    if (syntaxProperty.GetValue(objInstance)!=null) syntax = syntaxProperty.GetValue(objInstance).ToString();
                    break;
                }

            }
            return syntax;
        
      }

      public String GetOperatorProperty(VTLOperatorProperty prop, String opName)
      {
          return GetOperatorProperty((typeof(Operator)), prop, opName) + " ";
      }
        public Regex getRegex(String opName)
        {
            List<String> lst = new List<String>();
            String regex = "";
            regex = GetOperatorProperty(VTLOperatorProperty.Keyword, opName);
            regex =  regex + "|\\(|\\)|:=|\\[|\\]|,|\\|||\\{|\\}|";
            Regex rx = new Regex(@"" +regex);
            return rx;

        }





    }
}
