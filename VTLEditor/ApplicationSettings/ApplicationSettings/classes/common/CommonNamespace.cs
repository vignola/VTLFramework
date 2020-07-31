using System;
using System.Collections.Generic;
using System.Linq;
using System.Xml;
using System.Xml.XPath;

namespace ApplicationSettings.classes.common
{
   public class CommonNamespace
    {
        public static string message="http://www.SDMX.org/resources/SDMXML/schemas/v2_0/message";
        public static string common="http://www.SDMX.org/resources/SDMXML/schemas/v2_0/common";
        public static string registry="http://www.SDMX.org/resources/SDMXML/schemas/v2_0/registry";
        public static string structure="http://www.SDMX.org/resources/SDMXML/schemas/v2_0/structure";
        public static string xsi="http://www.w3.org/2001/XMLSchema-instance";
        public static string schemaLocation = "http://www.SDMX.org/resources/SDMXML/schemas/v2_0/message SDMXMessage.xsd";
        public static string Soap11Ns = "http://schemas.xmlsoap.org/soap/envelope/";       
        
        public static string xsdDefaultSchema="http://www.w3.org/2001/XMLSchema";
        public static string xmlnswebservice ="http://www.sdmx.org/resources/webservices";
        //GenericData message-generic-schemalocation message         
        public static string generic = "http://www.SDMX.org/resources/SDMXML/schemas/v2_0/generic";        

        public static System.Xml.XmlNamespaceManager RegistryNamespace(XmlNameTable NameTable)
        {
            try
            {
                System.Xml.XmlNamespaceManager tmp= new System.Xml.XmlNamespaceManager(NameTable);
                tmp.AddNamespace("message", message);
                tmp.AddNamespace("common",common);
                tmp.AddNamespace("registry",registry);
                tmp.AddNamespace("structure",structure);
                tmp.AddNamespace("xsi",xsi);
                tmp.AddNamespace("schemaLocation",schemaLocation);
                return tmp;
            }
            catch (Exception ex) 
            {
                throw new Exception("Error, [Common.CommonNameSpace.RegistryNamespace] " + ex.Message);
            }
        }

        public static System.Xml.XmlNamespaceManager GenericNamespace(XmlNameTable NameTable) 
        {
            try
            {
                System.Xml.XmlNamespaceManager tmp = new System.Xml.XmlNamespaceManager(NameTable);
                tmp.AddNamespace("message", message);
                tmp.AddNamespace("common", common);
                tmp.AddNamespace("generic", generic);            
                tmp.AddNamespace("xsi", xsi);
                tmp.AddNamespace("schemaLocation", schemaLocation);
                return tmp;
            }
            catch (Exception ex) 
            {
                throw new Exception("Error, [Common.CommonNameSpace.GenericNamespace] " + ex.Message);
            }
        }

        public static void AddSpecificGenericNamespace(string prefix,System.Xml.XmlNamespaceManager nmsMng) 
        {
            try
            {
                nmsMng.AddNamespace(prefix, "http://www.SDMX.org/resources/SDMXML/schemas/v2_0/generic");
             }
            catch (Exception ex) 
            {
                throw new Exception("Error, [Common.CommonNameSpace.AddSpecificGenericNamespace] " + ex.Message);
            }
        }

        public static string GetGenericPrefix(string xmlDoc) 
        {
             try
            {
                XmlDocument x = new XmlDocument();
                x.LoadXml(xmlDoc);
                XmlNamespaceManager nsmgr = CommonNamespace.GenericNamespace(x.NameTable);
                nsmgr.AddNamespace("soap", "http://schemas.xmlsoap.org/soap/envelope/");
                XmlNode xnode=x.SelectSingleNode("//soap:Body/child::*", nsmgr).FirstChild;                             
                XPathNavigator foo = xnode.CreateNavigator();
                foo.MoveToFollowing(XPathNodeType.Element);
                IDictionary<string, string> whatever = foo.GetNamespacesInScope(XmlNamespaceScope.All);
                var prefix = from nsname in whatever where nsname.Value.IndexOf("generic") > -1 select nsname.Key;

                return prefix.First().ToString();
            }
            catch (Exception ex)
            {
                throw new Exception("Error, [Common.CommonNameSpace.GetGenericPrefix] " + ex.Message);
            }
        }
    }
}
