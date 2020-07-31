using System;
using System.Collections.Generic;
using System.Data;
using System.Linq;
using System.Text;
using System.Text.RegularExpressions;
using System.Threading.Tasks;
using System.Web;
using System.Xml;
using System.Xml.Linq;
using ArtefactInfo.model;

namespace VTLInt_WS.VTLDB
{
    public class Util
    {
        #region CONSTANTS
        
        public const int MaxOutputFileLength = 31457280;
        public const string SOAP = "SOAP";

        public const string NS_SDMX_STRUCTURE = "http://www.sdmx.org/resources/sdmxml/schemas/v2_1/structure";

        public const string SDMX_ID_FORMAT = "{0}__{1}__{2}";
        #endregion

        public enum wsOperation
        {
            GetCodelist,
            GetConceptScheme,
            GetDataflow,
            GetDataStructure,
            GetDataStructureWithRef
        }

        #region STATIC METHODs

        public static string GetOracleConnectionString(string Host, string UserName, string Pwd)
        {
            //return "Data Source=LIB1;User Id=sv_vtl;Password=svvtl;Integrated Security=no;";
            //return "Data Source=" + Host + ";User Id=" + UserName + ";Password=" + Pwd + ";Integrated Security=no;";
            return "Data Source=" + Host + ";User Id=" + UserName + ";Password=" + Pwd + ";";
        }


        public static XmlNamespaceManager GetNamespaceManager(XmlDocument xDom)
        {
            XmlNamespaceManager xManag = new XmlNamespaceManager(xDom.NameTable);
            xManag.AddNamespace("query", @"http://www.sdmx.org/resources/sdmxml/schemas/v2_1/query");

            return xManag;
        }

        public static XDocument ToXDocument(XmlDocument xmlDocument)
        {
            try
            {
                using (var nodeReader = new XmlNodeReader(xmlDocument))
                {        
                    return XDocument.Load(nodeReader, LoadOptions.None);
                }
             }
            catch (Exception ex) 
            {
                throw new Exception(ex.Message);
            }
        }

        public static XmlDocument ToXmlDocument(XDocument xDocument)
        {
            try
            {
                var xmlDocument = new XmlDocument();
                using (var xmlReader = xDocument.CreateReader())
                {
                    xmlDocument.Load(xmlReader);
                }
                return xmlDocument;
             }
            catch (Exception ex) 
            {
                throw new Exception(ex.Message);
            }
        }

        public static XmlDocument RemoveXmlns(XmlDocument doc)
        {
            XDocument d;
            try
            {
                using (var nodeReader = new XmlNodeReader(doc))
                    d = XDocument.Load(nodeReader);

                d.Root.Descendants().Attributes().Where(x => x.IsNamespaceDeclaration).Remove();

                foreach (var elem in d.Descendants())
                    elem.Name = elem.Name.LocalName;

                var xmlDocument = new XmlDocument();
                using (var xmlReader = d.CreateReader())
                    xmlDocument.Load(xmlReader);

                return xmlDocument;
             }
            catch (Exception ex) 
            {
                throw new Exception(ex.Message);
            }
        }

        public static XmlDocument RemoveXmlns(String xml)
        {
            try
            {
                XDocument d = XDocument.Parse(xml);
                d.Root.Descendants().Attributes().Where(x => x.IsNamespaceDeclaration).Remove();

                foreach (var elem in d.Descendants())
                    elem.Name = elem.Name.LocalName;

                var xmlDocument = new XmlDocument();
                xmlDocument.Load(d.CreateReader());

                return xmlDocument;
             }
            catch (Exception ex) 
            {
                throw new Exception(ex.Message);
            }
        }


        public static void RemoveFilter(XmlDocument xDom)
        {
            try
            {
                XmlNode xNodeID = xDom.SelectSingleNode("//query:ID", Util.GetNamespaceManager(xDom));

                for (int i = xNodeID.ParentNode.ChildNodes.Count - 1; i >= 0; i--)
                    xNodeID.ParentNode.RemoveChild(xNodeID.ParentNode.ChildNodes[i]);
             }
            catch (Exception ex) 
            {
                throw new Exception(ex.Message);
            }

        }

        public static void SetFilter(ref XmlDocument xDom, string id)
        {
            try
            {
                string[] vtl_id = Regex.Split(id, CommonConstant.VTLID_SEPARATOR);
                XmlNode xNodeID = xDom.SelectSingleNode("//query:ID", Util.GetNamespaceManager(xDom));
                xNodeID.ChildNodes.Item(0).InnerText = vtl_id[0];
                xNodeID = xDom.SelectSingleNode("//query:AgencyID", Util.GetNamespaceManager(xDom));
                xNodeID.ChildNodes.Item(0).InnerText = vtl_id[1];
                xNodeID = xDom.SelectSingleNode("//query:Version", Util.GetNamespaceManager(xDom));
                xNodeID.ChildNodes.Item(0).InnerText = vtl_id[2];
             }
            catch (Exception ex) 
            {
                throw new Exception(ex.Message);
            }
        }  

        public static void SetReturnDetails(ref XmlDocument xDom, string value)
        {
            try
            {
                XmlNode xNodeID = xDom.SelectSingleNode("//query:ReturnDetails", Util.GetNamespaceManager(xDom));
                xNodeID.Attributes["detail"].Value = value;
             }
            catch (Exception ex) 
            {
                throw new Exception(ex.Message);
            }
        }

        public static bool IntToBool(int i) { return i == 0 ? false : true; }

        public static List<BaseArtefactInfo> MergeResult(List<BaseArtefactInfo> sumds, List<BaseArtefactInfo> vtlds)
        {   try
            {
                List<BaseArtefactInfo> result = vtlds;
                BaseArtefactInfo ai;
                foreach (BaseArtefactInfo dsinfo in sumds)
                {
                    ai = vtlds.Find(x => x.vtlId == dsinfo.vtlId);
                    if (ai == null)
                    {
                        result.Add(dsinfo);
                    }
                }

                return result;
             }
            catch (Exception ex) 
            {
                throw new Exception(ex.Message);
            }
        }

        public static string SafeGetString(IDataReader reader, int colIndex)
        {
            try
            {
                if (!reader.IsDBNull(colIndex))
                    return reader.GetString(colIndex).Trim();
                return string.Empty;
             }
            catch (Exception ex) 
            {
                throw new Exception(ex.Message);
            }
        }


        #endregion
    }
}