using System;
using System.Collections.Generic;
using System.Xml;
using ApplicationSettings.classes.common;
using ApplicationSettings.classes.utility;
using ISTAT_DB_DAL;
using static ApplicationSettings.classes.services.ApplicationSettings.WebServiceInfo;

namespace ApplicationSettings.classes.services
    {
        public class ApplicationSettings
        {
            public class WebServiceInfo : ICloneable
            {            
                public enum RETURN_DETAIL_IMPLEMENTATION
                {
                    Stub,
                    CompleteStub,
                    Full
                }                

            #region private properties

            private int _webService_ID;
                private string _webService_URL;
                private string _webService_UserName;

                #endregion

                #region public properties

                public string WebService_UserName;
                public string WebService_Password;
                public string WebService_Name;
                public string WebService_Description;
                public bool WebService_Crypted;
                public RETURN_DETAIL_IMPLEMENTATION WebService_ReturnDetail;               

                public int WebService_ID
                {
                    get { return _webService_ID; }
                    set { _webService_ID = value; }
                }

                public string WebService_public_URL
                {
                    get { return _webService_URL; }
                    set { _webService_URL = value; }
                }

                public string WebService_clear_URL
                {
                    get
                    {
                        if (WebService_Crypted)
                        {
                            return StringCryptography.Decrypt(_webService_URL);
                        }
                        else
                        {
                            return _webService_URL;
                        }
                    }
                }

                #endregion

                public override string ToString()
                {
                    return WebService_Name;
                }

                public WebServiceInfo(string WebServiceName, string WebServiceURL)
                {
                    _webService_URL = WebServiceURL;
                    WebService_Name = WebServiceName;
                    WebService_ID = generateID();
                }

                public WebServiceInfo() { }

                public int generateID()
                {
                    return (_webService_URL + WebService_Name).GetHashCode();
                }

                public Object Clone()
                {
                    WebServiceInfo tmpObj = new WebServiceInfo("", "");
                    tmpObj.WebService_ID = this.WebService_ID;
                    tmpObj.WebService_Name = this.WebService_Name;
                    tmpObj._webService_URL = this._webService_URL;
                    tmpObj.WebService_UserName = this.WebService_UserName;
                    tmpObj.WebService_Password = this.WebService_Password;
                    tmpObj.WebService_Crypted = this.WebService_Crypted;                    
                    return tmpObj;
                }
            }

            public class DBInfo
            {                

                public int ID;
                public string DBConnectionName;
                public ProviderType DBType;
                public string Host;
                public string HostPort;
                public string DataBase;
                public string UserName;
                public string TSNName;
                public string Pwd;
                public bool WindowsAuthentication;

                public static DBInfo FromXml(XmlNode xmlInfo)
                {
                    DBInfo tmpInfo = new DBInfo();
                    XmlNode elem = xmlInfo.SelectSingleNode("DBConnectionName");
                    if (elem != null) tmpInfo.DBConnectionName = elem.InnerText;

                    elem = xmlInfo.SelectSingleNode("DBType");
                    if (elem!= null) tmpInfo.DBType = (ProviderType)System.Enum.Parse(typeof(ProviderType), elem.InnerText);

                    elem = xmlInfo.SelectSingleNode("Host");
                    if (elem != null) tmpInfo.Host = elem.InnerText;

                    elem = xmlInfo.SelectSingleNode("HostPort");
                    if (elem != null) tmpInfo.HostPort = elem.InnerText;

                    elem = xmlInfo.SelectSingleNode("DataBase");
                    if (elem != null) tmpInfo.DataBase = elem.InnerText;

                    elem = xmlInfo.SelectSingleNode("UserName");
                    if (elem != null) tmpInfo.UserName = elem.InnerText;

                    elem = xmlInfo.SelectSingleNode("Pwd");
                    if (elem != null) tmpInfo.Pwd = elem.InnerText;

                    elem = xmlInfo.SelectSingleNode("TSNName");
                    if (elem != null) tmpInfo.TSNName = elem.InnerText;

                    elem = xmlInfo.SelectSingleNode("WindowsAuthentication");
                    if (elem != null) tmpInfo.WindowsAuthentication = elem.InnerText == "true" ? true : false;

                    return tmpInfo;
                }

                public static XmlDocument ToXml(DBInfo DBInfoItem)
                {
                    XmlDocument xmlDoc = new XmlDocument();

                    //XmlDeclaration xmlDeclaration = xmlDoc.CreateXmlDeclaration("1.0", "UTF-8", null);
                    //XmlElement root = xmlDoc.DocumentElement;
                    //xmlDoc.InsertBefore(xmlDeclaration, root);

                    XmlElement element1 = xmlDoc.CreateElement(string.Empty, "DBConnection", string.Empty);
                    xmlDoc.AppendChild(element1);

                    XmlElement element2 = xmlDoc.CreateElement(string.Empty, "DBConnectionName", string.Empty);
                    XmlText text2 = xmlDoc.CreateTextNode(DBInfoItem.DBConnectionName);
                    element2.AppendChild(text2);
                    element1.AppendChild(element2);

                    XmlElement element21 = xmlDoc.CreateElement(string.Empty, "DBType", string.Empty);
                    XmlText text21 = xmlDoc.CreateTextNode(DBInfoItem.DBType.ToString());
                    element21.AppendChild(text21);
                    element1.AppendChild(element21);

                    XmlElement element3 = xmlDoc.CreateElement(string.Empty, "Host", string.Empty);
                    XmlText text3 = xmlDoc.CreateTextNode(DBInfoItem.Host);
                    element3.AppendChild(text3);
                    element1.AppendChild(element3);

                    XmlElement element4 = xmlDoc.CreateElement(string.Empty, "HostPort", string.Empty);
                    XmlText text4 = xmlDoc.CreateTextNode(DBInfoItem.HostPort);
                    element4.AppendChild(text4);
                    element1.AppendChild(element4);

                    XmlElement element5 = xmlDoc.CreateElement(string.Empty, "DataBase", string.Empty);
                    XmlText text5 = xmlDoc.CreateTextNode(DBInfoItem.DataBase);
                    element5.AppendChild(text5);
                    element1.AppendChild(element5);

                    XmlElement element6 = xmlDoc.CreateElement(string.Empty, "UserName", string.Empty);
                    XmlText text6 = xmlDoc.CreateTextNode(DBInfoItem.UserName);
                    element6.AppendChild(text6);
                    element1.AppendChild(element6);

                    XmlElement element7 = xmlDoc.CreateElement(string.Empty, "Pwd", string.Empty);
                    XmlText text7 = xmlDoc.CreateTextNode(DBInfoItem.Pwd);
                    element7.AppendChild(text7);
                    element1.AppendChild(element7);

                    XmlElement element8 = xmlDoc.CreateElement(string.Empty, "TSNName", string.Empty);
                    XmlText text8 = xmlDoc.CreateTextNode(DBInfoItem.TSNName);
                    element8.AppendChild(text8);
                    element1.AppendChild(element8);

                    XmlElement element9 = xmlDoc.CreateElement(string.Empty, "WindowsAuthentication", string.Empty);
                    XmlText text9 = xmlDoc.CreateTextNode(DBInfoItem.WindowsAuthentication ? "true" : "false");
                    element9.AppendChild(text9);
                    element1.AppendChild(element9);

                    return xmlDoc;
                }

                public string GetOracleConnectionString()
                {
                //return "Data Source=LIB1;User Id=sv_vtl;Password=svvtl;Integrated Security=no;";
                //return "Data Source=LIB1;User Id=sv_vtl;Password=svvtl";
                //return "Data Source=" + Host + ";User Id=" + UserName + ";Password=" + Pwd + ";Integrated Security=no;";
                return "Data Source=" + TSNName + ";User Id=" + UserName + ";Password=" + Pwd + ";";
                }
            }

            private List<WebServiceInfo> _WebServices;
            private List<DBInfo> _dbConnections;
            private WebServiceInfo _interactionWebService;
            private WebServiceInfo _validationWebService;
            private CommonConst.Loading_Status _loadedStatus = CommonConst.Loading_Status.GENERIC_ERROR;

            public string OBS_default_valueDomainID;
            public string TIME_PERIOD_default_valueDomainID;

            public CommonConst.Loading_Status SettingsStatus
            {
                get
                {
                    return _loadedStatus;
                }
                protected set
                {
                    _loadedStatus = value;
                }
            }

            //WS
            public bool EnableProxy;
            public string ProxyServer;
            public int ProxyPort;
            public string ProxyUser;
            public string ProxyPwd;

            //Folders        
            public bool LogActive;
            public string LogPath;
            public string QueriesPath;
            public string Language;
            public string SettingsVersion;

            //DBConnections
            public List<DBInfo> DBConnections
            {
                get { return _dbConnections; }
                set { _dbConnections = value; }
            }


            public List<WebServiceInfo> WebServices
            {
                get { return _WebServices; }
                set { _WebServices = value; }
            }

            public WebServiceInfo InteractionWebService
            {
                get { return _interactionWebService; }
                set { _interactionWebService = value; }
            }

            public WebServiceInfo ValidationWebService
            {
                get { return _validationWebService; }
                set { _validationWebService = value; }
            }

            public ApplicationSettings()
            {
                _WebServices = new List<WebServiceInfo>();
            }

            public CommonConst.Loading_Status getWebServices(XmlDocument WebServiceURLs)
            {
                try
                {
                    WebServiceInfo tmpWs;
                    System.Xml.XmlDocument tmpDoc = WebServiceURLs;

                    XmlNodeList listNode = tmpDoc.SelectNodes("//WebService");

                    if (listNode == null) return CommonConst.Loading_Status.SDMX_WEBSERVICE_NOT_PRESENT;

                    if (listNode.Count == 0) return CommonConst.Loading_Status.SDMX_WEBSERVICE_NOT_PRESENT;

                    _WebServices = new List<WebServiceInfo>();

                    foreach (XmlNode nd in listNode)
                    {
                        tmpWs = new WebServiceInfo(nd.SelectSingleNode("Name").InnerText, nd.SelectSingleNode("Url").InnerText);
                        tmpWs.WebService_ID = int.Parse(nd.SelectSingleNode("ID").InnerText);
                        tmpWs.WebService_Description = nd.SelectSingleNode("Description").InnerText;
                        tmpWs.WebService_UserName = nd.SelectSingleNode("UserName").InnerText;
                        tmpWs.WebService_Password = nd.SelectSingleNode("Password").InnerText;

                        if (nd.SelectSingleNode("Crypted") != null)
                        {
                            tmpWs.WebService_Crypted = nd.SelectSingleNode("Crypted").InnerText == "true" ? true : false;
                        }
                        else
                        {
                            return CommonConst.Loading_Status.OBSOLETE_VERSION;
                        }

                        if (nd.SelectSingleNode("ReturnDetail") != null) tmpWs.WebService_ReturnDetail = nd.SelectSingleNode("ReturnDetail").InnerText == WebServiceInfo.RETURN_DETAIL_IMPLEMENTATION.Full.ToString() ? WebServiceInfo.RETURN_DETAIL_IMPLEMENTATION.Full : WebServiceInfo.RETURN_DETAIL_IMPLEMENTATION.CompleteStub;

                        _WebServices.Add(tmpWs);
                    }

                    return CommonConst.Loading_Status.SDMX_WEBSERVICE_LOADED;
                }
                catch (Exception ex)
                {
                    throw new Exception("Error, [Common.CommonItem.LoadSettings] " + ex.Message);
                }
            }

            public CommonConst.Loading_Status getInteractionWebService(XmlDocument WebServiceURLs) 
            {
                CommonConst.Loading_Status result = getWebService(WebServiceURLs, ref _interactionWebService);
                if (result== CommonConst.Loading_Status.LOADED)
                { 
                    return CommonConst.Loading_Status.INTERACTION_WS_LOADED; 
                }
                else
                {
                return result;
                }                    
            }

            public CommonConst.Loading_Status getValidationWebService(XmlDocument WebServiceURLs)
            {
                CommonConst.Loading_Status result = getWebService(WebServiceURLs, ref _validationWebService);
                if (result == CommonConst.Loading_Status.LOADED) 
                {
                    return CommonConst.Loading_Status.VALIDATION_WS_LOADED; 
                }
                else
                { 
                    return result;
                }
            }

            private CommonConst.Loading_Status getWebService(XmlDocument WebServiceURLs, ref WebServiceInfo wsInfo)
            {
                try
                {                    
                    System.Xml.XmlDocument tmpDoc = WebServiceURLs;

                    if (tmpDoc == null) return CommonConst.Loading_Status.SDMX_WEBSERVICE_NOT_PRESENT;

                    XmlNode nd = tmpDoc.SelectSingleNode("//WebService");

                    if (nd == null) return CommonConst.Loading_Status.SDMX_WEBSERVICE_NOT_PRESENT;

                    wsInfo = new WebServiceInfo();

                    wsInfo = new WebServiceInfo(nd.SelectSingleNode("Name").InnerText, nd.SelectSingleNode("Url").InnerText);
                    wsInfo.WebService_ID = int.Parse(nd.SelectSingleNode("ID").InnerText);
                    wsInfo.WebService_Description = nd.SelectSingleNode("Description").InnerText;
                    wsInfo.WebService_UserName = nd.SelectSingleNode("UserName").InnerText;
                    wsInfo.WebService_Password = nd.SelectSingleNode("Password").InnerText;
                    wsInfo.WebService_Crypted = nd.SelectSingleNode("Crypted").InnerText == "true" ? true : false;
                    if (nd.SelectSingleNode("ReturnDetail") != null) wsInfo.WebService_ReturnDetail = nd.SelectSingleNode("ReturnDetail").InnerText == WebServiceInfo.RETURN_DETAIL_IMPLEMENTATION.Full.ToString() ? WebServiceInfo.RETURN_DETAIL_IMPLEMENTATION.Full : WebServiceInfo.RETURN_DETAIL_IMPLEMENTATION.CompleteStub;

                    return CommonConst.Loading_Status.LOADED;
                }
                catch (Exception ex)
                {
                    throw new Exception("Error, [GetDataDLL.Services.Config.ApplicationSettings.LoadSettings] " + ex.Message);
                }
            }

            public XmlDocument setXmlDocWebServices(List<WebServiceInfo> wsList)
            {
                XmlTextWriter wr = null;
                try
                {
                    XmlDocument xmlDoc = new XmlDocument();
                    System.IO.MemoryStream ms = new System.IO.MemoryStream();
                    wr = new XmlTextWriter(ms, System.Text.Encoding.UTF8);

                    _WebServices = wsList;

                    wr.WriteStartDocument();
                    wr.WriteStartElement("WebServices");
                    foreach (WebServiceInfo wsInfo in _WebServices)
                    {
                        wr.WriteStartElement("WebService");
                        wr.WriteElementString("ID", wsInfo.WebService_ID.ToString());
                        wr.WriteElementString("Name", wsInfo.WebService_Name);
                        wr.WriteElementString("Description", wsInfo.WebService_Description);
                        wr.WriteElementString("Url", wsInfo.WebService_public_URL);
                        wr.WriteElementString("UserName", wsInfo.WebService_UserName);
                        wr.WriteElementString("Password", wsInfo.WebService_Password);
                        wr.WriteElementString("Crypted", wsInfo.WebService_Crypted == true ? "true" : "false");
                        wr.WriteElementString("ReturnDetail", wsInfo.WebService_ReturnDetail.ToString());

                        wr.WriteEndElement();//WebService
                    }
                    wr.WriteEndElement();//WebServices
                    wr.WriteEndDocument();
                    wr.Flush();

                    string xmlText = System.Text.Encoding.UTF8.GetString(ms.ToArray());

                    xmlDoc.LoadXml(xmlText.Substring(1));
                    return xmlDoc;
                }
                catch (Exception ex)
                {
                    throw new Exception("Error, [GetDataDLL.Services.Config.ApplicationSettings.LoadSettings] " + ex.Message);
                }
                finally
                {
                    if (wr != null) wr.Close();
                }
            }

            public XmlDocument setXmlDocInteractionWebServices(WebServiceInfo IntWs) 
            {
                return setXmlDocWebServices(IntWs, "InteractionWebService");
            }

            public XmlDocument setXmlDocValidationWebServices(WebServiceInfo IntWs)
            {
                return setXmlDocWebServices(IntWs, "ValidationWebService");
            }

            private XmlDocument setXmlDocWebServices(WebServiceInfo IntWs, string XmlStartElementWS)
            {
                XmlTextWriter wr = null;
                try
                {
                    XmlDocument xmlDoc = new XmlDocument();
                    System.IO.MemoryStream ms = new System.IO.MemoryStream();
                    wr = new XmlTextWriter(ms, System.Text.Encoding.UTF8);

                    _interactionWebService = IntWs;

                    wr.WriteStartDocument();
                    wr.WriteStartElement(XmlStartElementWS);       
             
                        wr.WriteStartElement("WebService");

                            wr.WriteElementString("ID", IntWs.WebService_ID.ToString());
                            wr.WriteElementString("Name", IntWs.WebService_Name);
                            wr.WriteElementString("Description", IntWs.WebService_Description);
                            wr.WriteElementString("Url", IntWs.WebService_public_URL);
                            wr.WriteElementString("UserName", IntWs.WebService_UserName);
                            wr.WriteElementString("Password", IntWs.WebService_Password);
                            wr.WriteElementString("Crypted", IntWs.WebService_Crypted == true ? "true" : "false");
                            wr.WriteElementString("ReturnDetail", IntWs.WebService_ReturnDetail.ToString());

                        wr.WriteEndElement();//WebService

                        wr.WriteEndElement();//</"XmlStartElementWS">

                    wr.WriteEndDocument();
                    wr.Flush();

                    string xmlText = System.Text.Encoding.UTF8.GetString(ms.ToArray());

                    xmlDoc.LoadXml(xmlText.Substring(1));
                    return xmlDoc;
                }
                catch (Exception ex)
                {
                    throw new Exception("Error, [GetDataDLL.Services.Config.ApplicationSettings.LoadSettings] " + ex.Message);
                }
                finally
                {
                    if (wr != null) wr.Close();
                }
            }
            public WebServiceInfo getWebserviceInfoByID(int id)
            {
                try
                {
                    foreach (WebServiceInfo wsInfo in _WebServices)
                    {
                        if (wsInfo.WebService_ID == id) return wsInfo;
                    }
                    return null;
                }
                catch (Exception ex)
                {
                    throw new Exception("Error, [GetDataDLL.Services.Config.ApplicationSettings.getWebserviceInfoByID] " + ex.Message);
                }
            }

            public int getWebServiceIdBy_Url_Name(string URL, string WSName)
            {
                try
                {
                    WebServiceInfo tmpWS = _WebServices.Find(delegate(WebServiceInfo wsInfo)
                    { return wsInfo.WebService_Name == WSName && wsInfo.WebService_public_URL == URL; });
                    if (tmpWS != null) return tmpWS.WebService_ID;
                    return -1;
                }
                catch (Exception ex)
                {
                    throw new Exception("Error, [GetDataDLL.Services.Config.ApplicationSettings.getWebServiceIdBy_Url_Name] " + ex.Message);
                }
            }

            public CommonConst.Loading_Status getDBConnections(XmlDocument DBConnectionsXML) 
            {
                try 
                {
                _dbConnections = new List<DBInfo>();

                System.Xml.XmlDocument tmpDoc = DBConnectionsXML;

                if (tmpDoc == null) return CommonConst.Loading_Status.DB_CONNECTIONS_NOT_PRESENT;

                XmlNode nd = tmpDoc.SelectSingleNode("//DBConnections");

                if (nd == null) return CommonConst.Loading_Status.DB_CONNECTIONS_NOT_PRESENT;

                XmlNodeList dbConnection = nd.SelectNodes("DBConnection");
                if (dbConnection == null) return CommonConst.Loading_Status.DB_CONNECTIONS_NOT_PRESENT;
                if (dbConnection.Count == 0) return CommonConst.Loading_Status.DB_CONNECTIONS_NOT_PRESENT;

                foreach(XmlNode conn in dbConnection) 
                {
                    DBInfo dbInf= new DBInfo();                    
                    dbInf = DBInfo.FromXml(conn);
                    _dbConnections.Add(dbInf);
                }

                return CommonConst.Loading_Status.LOADED;
            }
            catch (Exception ex)
                {
                    throw new Exception("Error, [GetDataDLL.Services.Config.ApplicationSettings.setDBConnections] " + ex.Message);
                }
        }

            public XmlDocument setDBConnections(List<DBInfo> dbConnections) 
            {            
                XmlTextWriter wr = null;
                try
                {
                    XmlDocument xmlDoc = new XmlDocument();
                    System.IO.MemoryStream ms = new System.IO.MemoryStream();
                    wr = new XmlTextWriter(ms, System.Text.Encoding.UTF8);

                    _dbConnections = dbConnections;

                    wr.WriteStartDocument();
                    wr.WriteStartElement("DBConnections");
                    foreach (DBInfo dbInfo in _dbConnections)
                    {                        
                        wr.WriteRaw(DBInfo.ToXml(dbInfo).OuterXml);                        
                    }
                    wr.WriteEndElement();//DBConnections
                    wr.WriteEndDocument();
                    wr.Flush();

                    string xmlText = System.Text.Encoding.UTF8.GetString(ms.ToArray());

                    xmlDoc.LoadXml(xmlText.Substring(1));
                    return xmlDoc;
                                
                }
                catch (Exception ex)
                {
                    throw new Exception("Error, [GetDataDLL.Services.Config.ApplicationSettings.setDBConnections] " + ex.Message);
                }
            }

            public static WebServiceInfo getWebserviceInfoByID(List<WebServiceInfo> wsList, int id)
            {
                try
                {
                    foreach (WebServiceInfo wsInfo in wsList)
                    {
                        if (wsInfo.WebService_ID == id) return wsInfo;
                    }
                    return null;
                }
                catch (Exception ex)
                {
                    throw new Exception("Error, [GetDataDLL.Services.Config.ApplicationSettings.getWebserviceInfoByID] " + ex.Message);
                }
            }


        }
    }

