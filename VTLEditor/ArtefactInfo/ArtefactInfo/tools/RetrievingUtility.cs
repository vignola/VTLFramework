using System;
using System.Collections.Generic;
using System.Data;
using System.Linq;
using System.Xml;
using System.Xml.Linq;
using ArtefactInfo.model;


namespace ArtefactInfo.tools
{

    public class RetrievingUtility
    {
        #region CONSTANTS

        public const string VTLID_SEPARATOR = "__";
        public const int MaxOutputFileLength = 31457280;
        public const string SOAP = "SOAP";

        public const string NS_SDMX_STRUCTURE = "http://www.sdmx.org/resources/sdmxml/schemas/v2_1/structure";
        public const string NS_SDMX_21_COMMON = "http://www.sdmx.org/resources/sdmxml/schemas/v2_1/common";

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

        //public static string GetTemplate(Util.wsOperation operation)
        //{
        //    string fileName;

        //    fileName = operation.ToString().Replace("Get", "") + ".xml";

        //    return HttpContext.Current.Server.MapPath(@"~\SdmxQueryTemplate") + "\\" + fileName;
        //}

        public static XmlNamespaceManager GetNamespaceManager(XmlDocument xDom)
        {
            XmlNamespaceManager xManag = new XmlNamespaceManager(xDom.NameTable);
            xManag.AddNamespace("query", @"http://www.sdmx.org/resources/sdmxml/schemas/v2_1/query");

            return xManag;
        }
        public static XDocument ToXDocument(XmlDocument xmlDocument)
        {
            using (var nodeReader = new XmlNodeReader(xmlDocument))
            {
                //nodeReader.MoveToContent();
                return XDocument.Load(nodeReader, LoadOptions.None);
            }
        }

        public static XmlDocument ToXmlDocument(XDocument xDocument)
        {
            var xmlDocument = new XmlDocument();
            using (var xmlReader = xDocument.CreateReader())
            {
                xmlDocument.Load(xmlReader);
            }
            return xmlDocument;
        }

        public static XmlDocument RemoveXmlns(XmlDocument doc)
        {
            XDocument d;
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

        public static XmlDocument RemoveXmlns(String xml)
        {
            XDocument d = XDocument.Parse(xml);
            d.Root.Descendants().Attributes().Where(x => x.IsNamespaceDeclaration).Remove();

            foreach (var elem in d.Descendants())
                elem.Name = elem.Name.LocalName;

            var xmlDocument = new XmlDocument();
            xmlDocument.Load(d.CreateReader());

            return xmlDocument;
        }


        public static void RemoveFilter(XmlDocument xDom)
        {
            XmlNode xNodeID = xDom.SelectSingleNode("//query:ID", GetNamespaceManager(xDom));

            for (int i = xNodeID.ParentNode.ChildNodes.Count - 1; i >= 0; i--)
                xNodeID.ParentNode.RemoveChild(xNodeID.ParentNode.ChildNodes[i]);

        }

        public static void SetFilter(ref XmlDocument xDom, string id)
        {
            string[] vtl_id = CommonConstant.splitGlobalID(id);
            XmlNode xNodeID = xDom.SelectSingleNode("//query:ID", GetNamespaceManager(xDom));
            xNodeID.ChildNodes.Item(0).InnerText = vtl_id[0];
            xNodeID = xDom.SelectSingleNode("//query:AgencyID", GetNamespaceManager(xDom));
            xNodeID.ChildNodes.Item(0).InnerText = vtl_id[1];
            xNodeID = xDom.SelectSingleNode("//query:Version", GetNamespaceManager(xDom));
            xNodeID.ChildNodes.Item(0).InnerText = vtl_id[2];
        }

        //public static void SetStub(ref XmlDocument xDom)
        //{
        //    XmlNode xNodeID = xDom.SelectSingleNode("//query:ReturnDetails", Util.GetNamespaceManager(xDom));
        //    xNodeID.Attributes["detail"].Value = "Stub";
        //}

        public static void SetReturnDetails(ref XmlDocument xDom, string value)
        {
            XmlNode xNodeID = xDom.SelectSingleNode("//query:ReturnDetails", GetNamespaceManager(xDom));
            xNodeID.Attributes["detail"].Value = value;
        }
        public static bool IntToBool(int i) { return i == 0 ? false : true; }

        public static List<BaseArtefactInfo> MergeResult(List<BaseArtefactInfo> sumds, List<BaseArtefactInfo> vtlds)
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

        public static string SafeGetString(IDataReader reader, int colIndex)
        {
            if (!reader.IsDBNull(colIndex))
                return reader.GetString(colIndex).Trim();
            return string.Empty;
        }


        #endregion
    }
}