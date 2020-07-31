using System;
using System.Collections.Generic;
using System.IO;
using System.Xml;
using ArtefactInfo.model;
using Org.Sdmxsource.Sdmx.Api.Exception;
using Org.Sdmxsource.Sdmx.Api.Model.Objects;
using Org.Sdmxsource.Sdmx.Api.Model.Objects.Codelist;
using Org.Sdmxsource.Sdmx.Api.Model.Objects.DataStructure;
using Org.Sdmxsource.Sdmx.Api.Util;
using Org.Sdmxsource.Util.ResourceBundle;


namespace SDMXMetadataLoader.service
{
/// <summary>
/// Summary description for MetadataLoader
/// </summary>
public class MetadataLoader
{	
        #region Private variables and properties

        private string _queryPath;
        private WebServiceLayer.classes.service.Net.WebServiceLayer _webLayer;
        private ISdmxObjects _sdmxObjects;

        public ISdmxObjects SDMXStructure
        {
            get
            {
                return _sdmxObjects;
            }
            set
            {
                _sdmxObjects = value;
            }
        }

        public string QueryPath
        {
            get
            {
                return _queryPath;
            }
            set
            {
                _queryPath = value;
            }

        }

        public WebServiceLayer.classes.service.Net.WebServiceLayer WSLayer
        {
            get
            {
                return _webLayer;
            }
            set
            {
                _webLayer = value;
            }

        }

        #endregion

        #region Constructors        

        public MetadataLoader(string QueriesPath, WebServiceLayer.classes.service.Net.WebServiceLayer WSLayer) 
        {
            _queryPath = QueriesPath;
            _webLayer = WSLayer;
            _sdmxObjects = new Org.Sdmxsource.Sdmx.Util.Objects.Container.SdmxObjectsImpl();
            SdmxException.SetMessageResolver(new MessageDecoder());
        }

        #endregion

        #region Public methods

        public void ClearStructure() 
        { 
            _sdmxObjects=new Org.Sdmxsource.Sdmx.Util.Objects.Container.SdmxObjectsImpl();
        }                     


        public List<BaseArtefactInfo> LoadDataflows21()
        {            
            XmlDocument queryDoc = new XmlDocument();
            string DataflowResultDoc=null;

            try
            {                
                queryDoc.Load(_queryPath + "\\DataFlow.xml");
                DataflowResultDoc = _webLayer.InvokeMethod(queryDoc, WebServiceConnector.WebServiceConstants.QueryType._DATAFLOW21);
                XmlDocument xmlDoc=new XmlDocument();
                xmlDoc.LoadXml(DataflowResultDoc);

                List<BaseArtefactInfo> dataflowList = MetadataRetriever.ParseDataFlowResponse(xmlDoc, _webLayer.WebServiceInfo.WebService_ReturnDetail.ToString());

                return dataflowList;
            }
            catch (Exception ex)
            {
                throw new Exception("[GetDataDLL.Model.ApplicationModel.MetadataLoader.LoadDataflows21] Query:" + queryDoc.InnerXml + " -  " + ex.Message);
            }
        }

        public List<BaseArtefactInfo> LoadDataStructures21()
        {            
            XmlDocument queryDoc = new XmlDocument();
            string DataStructureResultDoc=null;

            try
            {
                queryDoc.Load(_queryPath + "\\DataStructure.xml");
                DataStructureResultDoc = _webLayer.InvokeMethod(queryDoc, WebServiceConnector.WebServiceConstants.QueryType._DATASTRUCTURE21);
                XmlDocument xmlDoc = new XmlDocument();
                xmlDoc.LoadXml(DataStructureResultDoc);

                List<BaseArtefactInfo> dataStructureList = MetadataRetriever.ParseDataStructureResponse(xmlDoc, "Stub");

                return dataStructureList;
            }
            catch (Exception ex)
            {
                throw new Exception("[GetDataDLL.Model.ApplicationModel.MetadataLoader.LoadDataflows] Query:" + queryDoc.InnerXml + " -  " + ex.Message);
            }
        }

        public List<BaseArtefactInfo> LoadDataStructureDetailed21(string dsd_id, string dsd_agency, string dsd_versione)
        {
            XmlDocument queryDoc = new XmlDocument();
            string DataStructureResultDoc=null;
            string QueryText;

            try
            {
                TextReader txtRdr = new StreamReader(_queryPath + "\\DataStructureDetailed.xml");
                QueryText = txtRdr.ReadToEnd();
                txtRdr.Close();
                QueryText = QueryText.Replace("###AGENCYID###", dsd_agency);
                QueryText = QueryText.Replace("###KEYFAMILYID###", dsd_id);
                QueryText = QueryText.Replace("###VERSION###", dsd_versione);

                queryDoc.LoadXml(QueryText);
                DataStructureResultDoc = _webLayer.InvokeMethod(queryDoc, WebServiceConnector.WebServiceConstants.QueryType._DATASTRUCTURE21);
                XmlDocument xmlDoc = new XmlDocument();
                xmlDoc.LoadXml(DataStructureResultDoc);

                List<BaseArtefactInfo> dataStructureList = MetadataRetriever.ParseDataStructureResponse(xmlDoc, _webLayer.WebServiceInfo.WebService_ReturnDetail.ToString());

                return dataStructureList;
            }
            catch (Exception ex)
            {
                throw new Exception("[GetDataDLL.Model.ApplicationModel.MetadataLoader.LoadDataflows] Query:" + queryDoc.InnerXml + " -  " + ex.Message);
            }
        }
        public List<BaseArtefactInfo> LoadDataStructuresComponents21(string dsd_id, string agency_id, string version)
        {
            XmlDocument queryDoc = new XmlDocument();
            string DataStructureResultDoc=null;
            string QueryText;

            try
            {                

                TextReader txtRdr = new StreamReader(_queryPath + "\\DataStructureComponent.xml");                
                QueryText = txtRdr.ReadToEnd();
                txtRdr.Close();
                QueryText = QueryText.Replace("###AGENCYID###", agency_id);
                QueryText = QueryText.Replace("###KEYFAMILYID###", dsd_id);
                QueryText = QueryText.Replace("###VERSION###", version);
                QueryText = QueryText.Replace("###RETURNDETAILS###", this._webLayer.WebServiceInfo.WebService_ReturnDetail.ToString());

                queryDoc.LoadXml(QueryText);

                DataStructureResultDoc = _webLayer.InvokeMethod(queryDoc, WebServiceConnector.WebServiceConstants.QueryType._DATASTRUCTURE21);
                XmlDocument xmlDoc = new XmlDocument();
                xmlDoc.LoadXml(DataStructureResultDoc);

                List<BaseArtefactInfo> dataStructureList = MetadataRetriever.ParseDataStructureResponse(xmlDoc, _webLayer.WebServiceInfo.WebService_ReturnDetail.ToString());                

                return dataStructureList;
            }
            catch (Exception ex)
            {
                throw new Exception("[GetDataDLL.Model.ApplicationModel.MetadataLoader.LoadDataflows] Query:" + queryDoc.InnerXml + " -  " + ex.Message);
            }
        }
        

        public List<BaseArtefactInfo> LoadCodelist21()
        {
            //Org.Sdmxsource.Sdmx.Api.Model.IStructureWorkspace structureworkspace;
            XmlDocument queryDoc = new XmlDocument();
            string ValueDomainResultDoc;

            try
            {
                queryDoc.Load(_queryPath + "\\Codelist.xml");
                ValueDomainResultDoc = _webLayer.InvokeMethod(queryDoc, WebServiceConnector.WebServiceConstants.QueryType._CODELIST21);
                XmlDocument xmlDoc = new XmlDocument();
                xmlDoc.LoadXml(ValueDomainResultDoc);

                List<BaseArtefactInfo> valueDomainList = MetadataRetriever.ParseValueDomainResponse(xmlDoc, "Stub");

                return valueDomainList;
            }
            catch (Exception ex)
            {
                throw new Exception("[GetDataDLL.Model.ApplicationModel.MetadataLoader.LoadDataflows] Query:" + queryDoc.InnerXml + " -  " + ex.Message);
            }
        }


        public List<BaseArtefactInfo> LoadCodelistCodes21(string dsd_id, string dsd_agency, string dsd_version)
        {            
            string QueryText;
            XmlDocument queryDoc = new XmlDocument();
            //String returnDetail=null;

            try
            {
                TextReader txtRdr = new StreamReader(_queryPath + "\\CodelistCodes.xml");
                QueryText = txtRdr.ReadToEnd();
                txtRdr.Close();
                QueryText = QueryText.Replace("###AGENCYID###", dsd_agency);
                QueryText = QueryText.Replace("###KEYFAMILYID###", dsd_id);
                QueryText = QueryText.Replace("###VERSION###", dsd_version);
                //returnDetail = this._webLayer.WebServiceInfo.WebService_ReturnDetail == ApplicationSettings.classes.services.ApplicationSettings.WebServiceInfo.RETURN_DETAIL_IMPLEMENTATION.Full ? this._webLayer.WebServiceInfo.WebService_ReturnDetail.ToString() : ApplicationSettings.classes.services.ApplicationSettings.WebServiceInfo.RETURN_DETAIL_IMPLEMENTATION.Stub.ToString();
                QueryText = QueryText.Replace("###RETURNDETAILS###", this._webLayer.WebServiceInfo.WebService_ReturnDetail.ToString());

                string ValueDomainResultDoc=null;

                queryDoc.LoadXml(QueryText);
                    ValueDomainResultDoc = _webLayer.InvokeMethod(queryDoc, WebServiceConnector.WebServiceConstants.QueryType._CODELIST21);
                    XmlDocument xmlDoc = new XmlDocument();
                    xmlDoc.LoadXml(ValueDomainResultDoc);

                    List<BaseArtefactInfo> valueDomainList = MetadataRetriever.ParseValueDomainResponse(xmlDoc, this._webLayer.WebServiceInfo.WebService_ReturnDetail.ToString());

                    return valueDomainList;
               
            }
            catch (Exception ex)
            {
                if (ex.Message.IndexOf("Semantic Error - 007") > -1) return null;

                throw new Exception("[GetDataDLL.Model.ApplicationModel.MetadataLoader.LoadDataflows] Query:" + queryDoc.InnerXml + " -  " + ex.Message);
            }
        }               
       
        #endregion

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
                nsmgr = ApplicationSettings.classes.common.CommonNamespace.RegistryNamespace(wsResponse.NameTable);

                nsmgr.AddNamespace("message", "http://www.SDMX.org/resources/SDMXML/schemas/v2_0/message");

                //2.0
                soapContent = wsResponse.SelectSingleNode("//message:RegistryInterface", nsmgr);

                if (soapContent == null)
                {
                    //2.1 structure (dataflow, codelist)
                    soapContent = wsResponse.SelectSingleNode("/*[local-name()='Envelope' and namespace-uri()='http://schemas.xmlsoap.org/soap/envelope/']/*[local-name()='Body' and namespace-uri()='http://schemas.xmlsoap.org/soap/envelope/'][1]/*[local-name()='GetDataflowResponse' and namespace-uri()='http://tempuri.org/'][1]/*[local-name()='GetDataflowResult' and namespace-uri()='http://tempuri.org/'][1]/*[local-name()='Structure' and namespace-uri()='http://www.sdmx.org/resources/sdmxml/schemas/v2_1/message'][1]");
                    if (soapContent == null)
                        //2.1 DSD
                        soapContent = wsResponse.SelectSingleNode("/*[local-name()='Envelope' and namespace-uri()='http://schemas.xmlsoap.org/soap/envelope/']/*[local-name()='Body' and namespace-uri()='http://schemas.xmlsoap.org/soap/envelope/'][1]/*[local-name()='GetDataStructureResponse' and namespace-uri()='http://tempuri.org/'][1]/*[local-name()='GetDataStructureResult' and namespace-uri()='http://tempuri.org/'][1]/*[local-name()='Structure' and namespace-uri()='http://www.sdmx.org/resources/sdmxml/schemas/v2_1/message'][1]");
                        //soapContent = wsResponse.SelectSingleNode("/*[local-name()='Envelope' and namespace-uri()='http://schemas.xmlsoap.org/soap/envelope/']/*[local-name()='Body' and namespace-uri()='http://schemas.xmlsoap.org/soap/envelope/'][1]/*[local-name()='GetDataStructureResponse' and namespace-uri()='http://tempuri.org/'][1]/*[local-name()='GetDataStructureResult' and namespace-uri()='http://tempuri.org/'][1]/*[local-name()='Structure' and namespace-uri()='http://www.sdmx.org/resources/sdmxml/schemas/v2_1/message'][1]/*[local-name()='Structures' and namespace-uri()='http://www.sdmx.org/resources/sdmxml/schemas/v2_1/message'][1]/*[local-name()='DataStructures' and namespace-uri()='http://www.sdmx.org/resources/sdmxml/schemas/v2_1/structure'][1]");
                }

                if (soapContent!=null)
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

        private ICodelistObject GenerateTimePeriodCodelist(IDataflowObject DataFlowItem, IDimension timeDimension)
        {
            try
            {
                Org.Sdmxsource.Sdmx.Api.Model.Mutable.Codelist.ICodelistMutableObject codelist = new Org.Sdmxsource.Sdmx.SdmxObjects.Model.Mutable.Codelist.CodelistMutableCore();
                codelist.Id = "CL_TIME_PERIOD";
                codelist.AgencyId = DataFlowItem.AgencyId;
                codelist.Version = "1.0";
                codelist.AddName("en", "Time Dimension Start and End periods");
                
                codelist.CreateItem("1950", "Start Time Period");
                codelist.CreateItem(DateTime.Now.Year.ToString(), "End Time Period");
                
                return codelist.ImmutableInstance;
            }            
            catch (Exception e)
            {
                throw new Exception("[GetDataDLL.Model.ApplicationModel.MetadataLoader.extraceStructureArtefact] - " + e.Message);
            }
        }
        
    }
}
