using System;
using System.Collections.Generic;
using System.Linq;
using System.Xml;
using System.Xml.Linq;
using ArtefactInfo.model;
using ArtefactInfo.tools;
using Org.Sdmxsource.Sdmx.Api.Util;

namespace SDMXMetadataLoader.service
{
    public class MetadataRetriever
    {
        private Org.Sdmxsource.Sdmx.Api.Model.IStructureWorkspace extractStructureArtefact(string InvokeMethodResultDoc)
        {
            Org.Sdmxsource.Sdmx.Api.Model.IStructureWorkspace structureworkspace;
            XmlNode soapContent;
            XmlNamespaceManager nsmgr;

            try
            {
                Estat.Sri.SdmxStructureMutableParser.Manager.StructureMutableParsingManager StructureReader = new Estat.Sri.SdmxStructureMutableParser.Manager.StructureMutableParsingManager();
                XmlDocument wsResponse = new XmlDocument();

                wsResponse.LoadXml(InvokeMethodResultDoc);
                nsmgr = CommonNamespace.StructureNamespace(wsResponse.NameTable);
                
                soapContent = wsResponse.SelectSingleNode("//web:GetDataStructureResponse ", nsmgr);

                if (soapContent != null)
                {
                    using (IReadableDataLocation location = new Org.Sdmxsource.Util.Io.XmlDocReadableDataLocation(soapContent))
                    {
                        structureworkspace = StructureReader.ParseStructures(location);
                        location.Close();
                    }
                }
                else
                {
                    return null;
                }

                return structureworkspace;
            }
            catch (Exception e)
            {
                throw new Exception("[GetDataDLL.Model.ApplicationModel.MetadataLoader.extractStructureArtefact] - " + e.Message);
            }
        }


        public static List<BaseArtefactInfo> ParseDataStructureResponse(XmlDocument doc, string returnDetails)
        {
            List<BaseArtefactInfo> dataStructures = new List<BaseArtefactInfo>();
            XDocument xDoc2 = RetrievingUtility.ToXDocument(doc);
            XNamespace ns = RetrievingUtility.NS_SDMX_STRUCTURE;            

            switch (returnDetails)
            {
                case "Stub":

                    XmlDocument docWithoutNSa = RetrievingUtility.RemoveXmlns(doc);

                     XmlNodeList nodeList = docWithoutNSa.SelectNodes("//DataStructure");

                    foreach (XmlNode xmlNd in nodeList) 
                    {
                        BaseArtefactInfo tmpBaseArtefact = new BaseArtefactInfo();
                        tmpBaseArtefact.sdmxId = xmlNd.Attributes["id"].Value;
                        tmpBaseArtefact.sdmxAgency= xmlNd.Attributes["agencyID"].Value;
                        tmpBaseArtefact.sdmxVersion = xmlNd.Attributes["version"].Value;
                        tmpBaseArtefact.dataSource = "SUM";

                        tmpBaseArtefact.vtlId = String.Concat(new String[] { tmpBaseArtefact.sdmxId, RetrievingUtility.VTLID_SEPARATOR, tmpBaseArtefact.sdmxAgency, RetrievingUtility.VTLID_SEPARATOR, tmpBaseArtefact.sdmxVersion });
                                             
                        XmlNodeList nodeLocalizedListxml = xmlNd.SelectNodes("./Name");
                        tmpBaseArtefact.name = new List<LocalizedValue>();

                        foreach (XmlNode localString in nodeLocalizedListxml)
                        {
                            LocalizedValue tmpLocalized = new LocalizedValue();                            
                            tmpLocalized.lang = localString.Attributes["xml:lang"].Value;
                            tmpLocalized.value = localString.InnerText;
                            tmpBaseArtefact.name.Add(tmpLocalized);
                        }

                        XmlNodeList nodeLocalizedDescListxml = xmlNd.SelectNodes("./Description");

                        foreach (XmlNode localString in nodeLocalizedDescListxml)
                        {
                            LocalizedValue tmpLocalized = new LocalizedValue();
                            tmpBaseArtefact.name = new List<LocalizedValue>();
                            tmpLocalized.lang = localString.Attributes["xml:lang"].Value;
                            tmpLocalized.value = localString.InnerText;
                            tmpBaseArtefact.description.Add(tmpLocalized);
                        }

                        dataStructures.Add(tmpBaseArtefact);
                    }
                    
                    //var dataStructs2 = (from x in xDoc2.Root.Descendants(ns + "DataStructure")
                    //                    select
                    //                      new BaseArtefactInfo
                    //                      {
                    //                          vtlId = String.Concat(new String[] { (string)x.Attribute("id"), VTLID_SEPARATOR, (string)x.Attribute("agencyID"), VTLID_SEPARATOR, (string)x.Attribute("version") }),
                    //                          name = x.Elements("Name")
                    //                                .Select(r => new LocalizedValue
                    //                                {
                    //                                    lang = (string)r.Attribute("lang").Value,
                    //                                    value = (string)r.Value
                    //                                }).ToList(),
                    //                          description = x.Elements("Description")
                    //                                .Select(r => new LocalizedValue
                    //                                {
                    //                                    lang = (string)r.Attribute("lang").Value,
                    //                                    value = (string)r.Value
                    //                                }).ToList(),
                    //                          sdmxId = (string)x.Attribute("id"),
                    //                          sdmxAgency = (string)x.Attribute("agencyID"),
                    //                          sdmxVersion = (string)x.Attribute("version"),
                    //                          dataSource = "SUM"
                    //                      }
                    //                  ).ToList();

                    //foreach (BaseArtefactInfo x in dataStructs2)
                    //{
                    //    dataStructures.Add(x);
                    //}
                    break;

                case "Full":
                    goto case "CompleteStub";

                case "CompleteStub":
                    XmlDocument docWithoutNS = RetrievingUtility.RemoveXmlns(doc);

                    //List DataStructure's Components                   
                    XmlNodeList itemNodes = docWithoutNS.SelectNodes("//DataStructures");
                    BaseArtefactInfo baInfo = new BaseArtefactInfo();

                    foreach (XmlNode itemNode in itemNodes)
                    {
                        baInfo.sdmxId = itemNode.SelectSingleNode("DataStructure/@id").Value;
                        baInfo.sdmxAgency = itemNode.SelectSingleNode("DataStructure/@agencyID").Value;
                        baInfo.sdmxVersion = itemNode.SelectSingleNode("DataStructure/@version").Value;

                        XmlNodeList listNames = itemNode.SelectNodes(".//Name");
                        List<LocalizedValue> names = new List<LocalizedValue>();

                        foreach (XmlNode i in listNames)
                        {
                            names.Add(new LocalizedValue() { lang = i.Attributes["xml:lang"].Value, value = i.InnerText });
                        }                        

                        baInfo.name = names;

                        //Add Dimensions
                        XmlNodeList listDims = itemNode.SelectNodes("//DimensionList/Dimension");
                        List<BaseComponentInfo> compon = new List<BaseComponentInfo>();
                        int compCounter = 2;

                        foreach (XmlNode dim in listDims)
                        {
                            compon.Add(new DataStructureComponentInfo()
                            {
                                vtlId = dim.Attributes["id"].Value,
                                valuedomain_id = String.Format(RetrievingUtility.SDMX_ID_FORMAT,
                                               (string)dim.SelectSingleNode("LocalRepresentation/Enumeration/Ref/@id").Value,
                                               (string)dim.SelectSingleNode("LocalRepresentation/Enumeration/Ref/@agencyID").Value,
                                               (string)dim.SelectSingleNode("LocalRepresentation/Enumeration/Ref/@version").Value),
                                role = "Dimension"
                            });
                            
                            compon.Last().name = new List<LocalizedValue>();
                            LocalizedValue tmpName = new LocalizedValue("EN", (string)dim.Attributes["id"].Value);
                            compon.Last().name.Add(tmpName);

                            //try{
                            //    tmpcomp.name.Add(baInfo.name[compCounter]);                             
                            //    tmpcomp.name.Add(baInfo.name[compCounter + 1]);
                            //}catch (Exception ex){}                                                        
                            //compCounter+=2;
                        }

                       

                        //Add TimeDimension
                        XmlNode timeDim = itemNode.SelectSingleNode("//DimensionList/TimeDimension");
                        compon.Add(new DataStructureComponentInfo() { vtlId = timeDim.Attributes["id"].Value, role = "TimeDimension" });

                        //Add PrimaryMeasure
                        XmlNode primaryDim = itemNode.SelectSingleNode("//MeasureList/PrimaryMeasure");
                        compon.Add(new DataStructureComponentInfo() { vtlId = primaryDim.Attributes["id"].Value, role = "PrimaryMeasure" });

                        //Add Attributes
                        XmlNodeList listAttr = itemNode.SelectNodes("//DimensionList/Attribute");

                        foreach (XmlNode attr in listAttr)
                        {
                            compon.Add(new DataStructureComponentInfo()
                            {
                                vtlId = attr.Attributes["id"].Value,
                                valuedomain_id = String.Format(RetrievingUtility.SDMX_ID_FORMAT,
                                               (string)attr.SelectSingleNode("LocalRepresentation/Enumeration/Ref/@id").Value,
                                               (string)attr.SelectSingleNode("LocalRepresentation/Enumeration/Ref/@agencyID").Value,
                                               (string)attr.SelectSingleNode("LocalRepresentation/Enumeration/Ref/@version").Value),
                                role = "Attribute"
                            });
                        }

                        baInfo.DataStructureDetails.Components = compon;

                    }

                    dataStructures.Add(baInfo);

                    break;
            }

            return dataStructures;
        }

        public static List<BaseArtefactInfo> ParseDataFlowResponse(XmlDocument doc, string returnDetails)
        {
            List<BaseArtefactInfo> dataFlows = new List<BaseArtefactInfo>();
            XDocument xDoc2 = RetrievingUtility.ToXDocument(doc);
            XNamespace ns = RetrievingUtility.NS_SDMX_STRUCTURE;

            switch (returnDetails)
            {
                case "Stub":
                    throw new NotImplementedException();
                    //var dataStructs2 = (from x in xDoc2.Root.Descendants(ns + "DataStructure")
                    //                    select
                    //                      new BaseArtefactInfo
                    //                      {
                    //                          vtlId = String.Concat(new String[] { (string)x.Attribute("id"), VTLID_SEPARATOR, (string)x.Attribute("agencyID"), VTLID_SEPARATOR, (string)x.Attribute("version") }),
                    //                          name = x.Elements("Name")
                    //                                .Select(r => new LocalizedValue
                    //                                {
                    //                                    lang = (string)r.Attribute("lang").Value,
                    //                                    value = (string)r.Value
                    //                                }).ToList(),
                    //                          description = x.Elements("Description")
                    //                                .Select(r => new LocalizedValue
                    //                                {
                    //                                    lang = (string)r.Attribute("lang").Value,
                    //                                    value = (string)r.Value
                    //                                }).ToList(),
                    //                          sdmxId = (string)x.Attribute("id"),
                    //                          sdmxAgency = (string)x.Attribute("agencyID"),
                    //                          sdmxVersion = (string)x.Attribute("version"),
                    //                          dataSource = "SUM"
                    //                      }
                    //                  ).ToList();

                    //foreach (BaseArtefactInfo x in dataStructs2)
                    //{
                    //    dataFlows.Add(x);
                    //}
                    break;

                case "Full":
                    goto case "CompleteStub";

                case "CompleteStub":
                    XmlDocument docWithoutNS = RetrievingUtility.RemoveXmlns(doc);

                    //List DataStructure's Components                   
                    XmlNodeList itemNodes = docWithoutNS.SelectNodes("//Dataflows");
                    BaseArtefactInfo baInfo;

                    foreach (XmlNode itemNode in itemNodes[0].ChildNodes)
                    {
                        baInfo = new DataSetInfo();
                        baInfo.sdmxId = itemNode.Attributes["id"].Value;
                        baInfo.sdmxAgency = itemNode.Attributes["agencyID"].Value;
                        baInfo.sdmxVersion = itemNode.Attributes["version"].Value;

                        XmlNodeList listNames = itemNode.SelectNodes("Name");
                        List<LocalizedValue> names = new List<LocalizedValue>();
                        foreach (XmlNode i in listNames)
                        {
                            names.Add(new LocalizedValue() { lang = i.Attributes["xml:lang"].Value, value = i.InnerText });
                        }

                        baInfo.name = names;

                        XmlNode structRef = itemNode.SelectSingleNode("Structure/Ref");
                        ((DataSetInfo)baInfo).datastructure_id = structRef.Attributes["id"].Value;
                        ((DataSetInfo)baInfo).sdmx_DataStructure_id= structRef.Attributes["id"].Value;
                        ((DataSetInfo)baInfo).sdmx_DataStructure_agency= structRef.Attributes["agencyID"].Value;
                        ((DataSetInfo)baInfo).sdmx_DataStructure_version = structRef.Attributes["version"].Value;   
                        

                        dataFlows.Add(baInfo);
                    }
                    break;

            }
            return dataFlows;
           }

        
        public static List<BaseArtefactInfo> ParseValueDomainResponse(XmlDocument doc, string returnDetails)
        {
            List<BaseArtefactInfo> codeLists = new List<BaseArtefactInfo>();
            XDocument xDoc2 = RetrievingUtility.ToXDocument(doc);
            XNamespace ns = RetrievingUtility.NS_SDMX_STRUCTURE;
            XNamespace nsCommon = RetrievingUtility.NS_SDMX_21_COMMON;
            
            switch (returnDetails)
            {
                case "Stub":
                    XmlDocument docWithoutNSa = RetrievingUtility.RemoveXmlns(doc);

                    XmlNodeList nodeList = docWithoutNSa.SelectNodes("//Codelist");

                    foreach (XmlNode xmlNd in nodeList) 
                    {
                        BaseArtefactInfo tmpBaseArtefact = new BaseArtefactInfo();
                        tmpBaseArtefact.sdmxId = xmlNd.Attributes["id"].Value;
                        tmpBaseArtefact.sdmxAgency= xmlNd.Attributes["agencyID"].Value;
                        tmpBaseArtefact.sdmxVersion = xmlNd.Attributes["version"].Value;
                        tmpBaseArtefact.dataSource = "SUM";

                        tmpBaseArtefact.vtlId = String.Concat(new String[] { tmpBaseArtefact.sdmxId, RetrievingUtility.VTLID_SEPARATOR, tmpBaseArtefact.sdmxAgency, RetrievingUtility.VTLID_SEPARATOR, tmpBaseArtefact.sdmxVersion });
                                             
                        XmlNodeList nodeLocalizedListxml = xmlNd.SelectNodes("./Name");
                        tmpBaseArtefact.name = new List<LocalizedValue>();

                        foreach (XmlNode localString in nodeLocalizedListxml)
                        {
                            LocalizedValue tmpLocalized = new LocalizedValue();                            
                            tmpLocalized.lang = localString.Attributes["xml:lang"].Value;
                            tmpLocalized.value = localString.InnerText;
                            tmpBaseArtefact.name.Add(tmpLocalized);
                        }

                        XmlNodeList nodeLocalizedDescListxml = xmlNd.SelectNodes("./Description");

                        foreach (XmlNode localString in nodeLocalizedDescListxml)
                        {
                            LocalizedValue tmpLocalized = new LocalizedValue();
                            tmpBaseArtefact.name = new List<LocalizedValue>();
                            tmpLocalized.lang = localString.Attributes["xml:lang"].Value;
                            tmpLocalized.value = localString.InnerText;
                            tmpBaseArtefact.description.Add(tmpLocalized);
                        }
                        codeLists.Add(tmpBaseArtefact);
                    }
                                        
                    break;

                case "Full": 
                    goto case "CompleteStub";

                case "CompleteStub":
                    XmlDocument docWithoutNS = RetrievingUtility.RemoveXmlns(doc);

                    //List DataStructure's Components                   
                    XmlNodeList itemNodes = docWithoutNS.SelectNodes("//Codelist");
                    BaseArtefactInfo baInfo;
                    
                    foreach (XmlNode itemNode in itemNodes)
                    {
                        //baInfo.sdmxId = itemNode.SelectSingleNode("Codelist/@id").Value;
                        //baInfo.sdmxAgency = itemNode.SelectSingleNode("Codelist/@agencyID").Value;
                        //baInfo.sdmxVersion = itemNode.SelectSingleNode("Codelist/@version").Value;                        
                        baInfo = new BaseArtefactInfo();

                        baInfo.sdmxId = itemNode.Attributes["id"].Value;
                        baInfo.sdmxAgency = itemNode.Attributes["agencyID"].Value;
                        baInfo.sdmxVersion = itemNode.Attributes["version"].Value;

                        XmlNodeList listNames = itemNode.SelectNodes("Name");
                        List<LocalizedValue> names = new List<LocalizedValue>();
                        //foreach (XmlNode i in listNames)
                        //{
                        for (int l = 0; l < listNames.Count; l++)
                        {
                            XmlNode i = listNames[l];
                            names.Add(new LocalizedValue() { lang = i.Attributes["xml:lang"].Value, value = i.InnerText });
                        }
                        //}

                        baInfo.name = names;

                        //Add Dimensions
                        XmlNodeList listCodes = itemNode.SelectNodes("Code");
                        List<BaseComponentInfo> compon = new List<BaseComponentInfo>();
                        baInfo.DataStructureDetails = new BaseArtefactInfo.DataStructureInformation();

                        foreach (XmlNode code in listCodes)
                        {
                            XmlNodeList lCodeNames = code.SelectNodes("./Name");
                            List<LocalizedValue> codenames = new List<LocalizedValue>();
                            foreach (XmlNode nodeCode in lCodeNames)                            
                            {                                
                                codenames.Add(new LocalizedValue() { lang = nodeCode.Attributes["xml:lang"].Value, value = nodeCode.InnerText });
                            }

                            compon.Add(new BaseComponentInfo()
                            {
                                vtlId = code.Attributes["id"].Value,
                                name = codenames
                            });
                        }

                        baInfo.DataStructureDetails.Components = compon;
                        codeLists.Add(baInfo);
                    }
                    

                    break;
            }

            return codeLists;
        }
    }
}
