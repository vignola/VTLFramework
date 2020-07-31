using System;
using System.Configuration;
using System.IO;
using System.Xml;
using WebServiceConnector;

namespace WebServiceLayer.classes.service.Net
{
    public class WebServiceLayer
    {        
        private WebServiceClient _client;
        private WsConfigurationSettings _settings;
        private WebServiceClientStreaming _clientStream;
        private ApplicationSettings.classes.services.ApplicationSettings.WebServiceInfo _webServiceInfo;

        public ApplicationSettings.classes.services.ApplicationSettings.WebServiceInfo WebServiceInfo
        {
            get { return _webServiceInfo; }
        }

        //public WebServiceLayer(ApplicationSettings CurrentSettings) 
        //{
        //    try
        //    {                
        //        _settings = WsConfigurationSettings.GetSection(ConfigurationUserLevel.PerUserRoamingAndLocal);
        //        _settings.EndPoint = CurrentSettings.getWebserviceInfoByID(CurrentSettings.SelectedWebServiceID).WebService_clear_URL;
        //        _settings.WSDL = _settings.EndPoint + "?WSDL";
        //        _settings.EnableProxy = CurrentSettings.EnableProxy;
        //        _settings.ProxyServer = CurrentSettings.ProxyServer;
        //        _settings.ProxyServerPort = CurrentSettings.ProxyPort;
        //        _settings.ProxyUsername = CurrentSettings.ProxyUser;
        //        _settings.ProxyPassword = CurrentSettings.ProxyPwd;

        //        _settings.Password = CurrentSettings.getWebserviceInfoByID(CurrentSettings.SelectedWebServiceID).WebService_Password;
        //        _settings.Username = CurrentSettings.getWebserviceInfoByID(CurrentSettings.SelectedWebServiceID).WebService_UserName;

        //        _webServiceInfo = CurrentSettings.getWebserviceInfoByID(CurrentSettings.SelectedWebServiceID);
        //     }
        //    catch (Exception ex) 
        //    {
        //        throw new Exception("Error, [WebServiceLayer.classes.service.Net.WebServiceLayer.WebServiceLayer] " + ex.Message);
        //    }                
        //}

        public WebServiceLayer(ApplicationSettings.classes.services.ApplicationSettings CurrentSettings, int WebServiceID)
        {
            try
            {
                _settings = WsConfigurationSettings.GetSection(ConfigurationUserLevel.PerUserRoamingAndLocal);
                _settings.EndPoint = CurrentSettings.getWebserviceInfoByID(WebServiceID).WebService_clear_URL;
                _settings.WSDL = _settings.EndPoint + "?WSDL";
                _settings.EnableProxy = CurrentSettings.EnableProxy;
                _settings.ProxyServer = CurrentSettings.ProxyServer;
                _settings.ProxyServerPort = CurrentSettings.ProxyPort;
                _settings.ProxyUsername = CurrentSettings.ProxyUser;
                _settings.ProxyPassword = CurrentSettings.ProxyPwd;

                _settings.Password = CurrentSettings.getWebserviceInfoByID(WebServiceID).WebService_Password;
                _settings.Username = CurrentSettings.getWebserviceInfoByID(WebServiceID).WebService_UserName;

                _webServiceInfo = CurrentSettings.getWebserviceInfoByID(WebServiceID);
            }
            catch (Exception ex)
            {
                throw new Exception("Error, [WebServiceLayer.classes.service.Net.WebServiceLayer.WebServiceLayer] " + ex.Message);
            }
        }

        public void InvokeStreamMethod(XmlDocument QuerySDMX, string TypeOfQuery, string path, string extFile)
        {
            try
            {
                _settings.Operation = TypeOfQuery;
                _clientStream = new WebServiceClientStreaming(_settings);
                _clientStream.InvokeMethod(QuerySDMX, path, extFile);
            }
            catch (Exception ex)
            {
                throw new Exception("Error, [WebServiceLayer.classes.service.Net.WebServiceLayer.InovkeMethod] " + ex.Message);
            }
        }

        public string InvokeMethod(XmlDocument QuerySDMX, string Action)
        {
            try
            {
                _settings.Operation = Action;
                _client = new WebServiceClient(_settings);
                String result = null;

                using (Stream stream = _client.InvokeMethod(QuerySDMX).GetResponseStream())
                {
                    StreamReader reader = new StreamReader(stream, System.Text.Encoding.UTF8);
                    result = reader.ReadToEnd();
                }

                if (result.IndexOf("<soap:Fault>") > 0)
                {
                    throw new Exception("Error, SOAP FAULT" + result);
                }

                return result;
             }
            catch (Exception ex) 
            {
                throw new Exception("Error, [WebServiceLayer.classes.service.Net.WebServiceLayer.InovkeMethod] " + ex.Message);
            }
        }

        public WsConfigurationSettings getSettings(){
            return _settings;
        }
    }
}
