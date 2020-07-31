using System;
using System.Collections.Generic;
using System.Linq;
using System.Reflection;

namespace VTLlib
{
    public class Operators
    {
        public List<String[]> GetOperatorList(Type t, String grpName = null)
        {
            List<String[]> lst = new List<String[]>();


            foreach (Type type in Assembly.GetAssembly(typeof(Operator)).GetTypes()
         .Where(myType => myType.IsClass && !myType.IsAbstract && myType.IsSubclassOf(typeof(Operator))))  //
            {
                String[] op = new String[2];
                Object objInstance = Activator.CreateInstance(type);
                PropertyInfo groupProperty = type.GetProperty("opGroup");
                PropertyInfo nameProperty = type.GetProperty("Name");


                    op[0] = groupProperty.Name;
                    if ( nameProperty.GetValue(objInstance)!=null && !String.IsNullOrEmpty(nameProperty.ToString())) op[1] = nameProperty.GetValue(objInstance).ToString();
                    else op[1] = type.Name;

                if (grpName != null)
                {
                    if (grpName == groupProperty.GetValue(objInstance).ToString()) lst.Add(op);
                }
                else lst.Add(op);    
            }
            return lst;
        }

        public List<String[]> GetOperatorList(String grpName=null)
        {
             return GetOperatorList(typeof(Operator),grpName); 
        }
        public List<String[]> GetOperator(String opName)
        {
            List<String[]> lst = new List<String[]>();

            Operator newOp = new Operator();
            foreach (Type type in Assembly.GetAssembly(typeof(Operator)).GetTypes()
                    .Where(myType => myType.IsClass && !myType.IsAbstract && myType.IsSubclassOf(typeof(Operator))))  //
            {
              Type  newType=type.GetNestedType(opName);
            }
            return lst;
        }

        public List<String> GetGroups()
        {
            return GetGroups(typeof(Operator));
        }

        public List<String> GetGroups(Type t)
        {
            List<String> lst = new List<String>();


            foreach (Type type in Assembly.GetAssembly(typeof(Operator)).GetTypes()
                .Where(myType => myType.IsClass && !myType.IsAbstract && myType.IsSubclassOf(typeof(Operator))))  //
           
                {
                     Object objInstance = Activator.CreateInstance(type);
                        PropertyInfo thisProperty = type.GetProperty("opGroup");
                        String grpName = thisProperty.GetValue(objInstance).ToString();
                        Boolean grpFound = false;
                        for (int j = 0; j < lst.Count(); j++)
                        {
                            if (lst[j] == grpName) grpFound = true;
                        }
                        if (grpFound == false) lst.Add(grpName);
            
            }


  
            return lst;
        }

  
    }
}
