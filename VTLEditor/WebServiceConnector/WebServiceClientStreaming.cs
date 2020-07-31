using System;
using System.IO;
using System.Net;
using System.Text.RegularExpressions;
using System.Xml;

namespace WebServiceConnector
{
    public class WebServiceClientStreaming
    {
        #region Constants

        /// <summary>
        /// The buffer size.
        /// </summary>
        private const int BufferSize = 8192;

        /// <summary>
        /// This field holds a template for soap 1.1 request envelope
        /// </summary>
        private const string SoapRequest =
            "<soap:Envelope xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:{0}=\"{1}\"><soap:Header/><soap:Body><{0}:{2}>{3}</{0}:{2}></soap:Body></soap:Envelope>";

        /// <summary>
        /// This field holds a template for soap 1.1 request envelope
        /// </summary>
        private const string SoapRequestParameter =
            "<{0}:{1}>{2}</{0}:{1}>";

        /// <summary>
        /// The prefix for the web service namespace it can be anything
        /// </summary>
        private const string WsPrefix = "web";

        /// <summary>
        /// The SDMX v2.0 Message XML namespace
        /// </summary>
        private const string SdmxV2MessageNamespace = "http://www.SDMX.org/resources/SDMXML/schemas/v2_0/message";

        #endregion

        #region Private Fields

        private WsConfigurationSettings _settings;
        private string _output;
        private string _extFile;

        private string _prefix;

        #endregion

        #region Constructors and Destructors

        /// <summary>
        /// Initializes a new instance of the <see cref="WSClientStream_"/> class. 
        /// Initialize a new instance of the WSClientStream class
        /// It sets the endpoint, WSDL URL, authentication, proxy and method information
        /// </summary>
        /// <param name="settings">
        /// The Web Service settings.
        /// </param>
        public WebServiceClientStreaming(WsConfigurationSettings settings)
        {
            if (settings == null)
            {
                throw new ArgumentNullException("settings");
            }

            this._settings = settings;
        }

        #endregion  

        /// <summary>
        /// Invoke Method worker
        /// Send the request to the WebService in a different thread
        /// </summary>
        /// <param name="sender">
        /// The parameter is not used.
        /// </param>
        /// <param name="e">
        /// The <see cref="DoWorkEventArgs"/>. Should have the <see cref="DoWorkEventArgs.Argument"/> set to a string
        /// </param>
        public void InvokeMethod(XmlDocument RequestXml, string path, string extFile)
        {
            bool success = false;
            WebServiceClient client = new WebServiceClient(this._settings);
            XmlDocument doc = new XmlDocument();

            if (String.Equals(extFile, WebServiceConnector.WebServiceConstants.ResultType._SDMX) || String.Equals(extFile, WebServiceConnector.WebServiceConstants.ResultType._CSV) || String.Equals(extFile, WebServiceConnector.WebServiceConstants.ResultType._XML))
                //fine
                this._extFile = extFile;
            else
                throw new Exception("File extension does not handled");

            if (!String.IsNullOrEmpty(path))
                this._output = path;
            else
                throw new Exception("Path is null");
            

            doc=RequestXml;

            using (WebResponse response = client.InvokeMethod(doc))
            {
                using (Stream responseStream = response.GetResponseStream())
                {                                          
                        string filePath = path;
                        string tempFileName = Path.GetTempFileName();

                        using (var fs = new FileStream(tempFileName, FileMode.Create))
                        {
                            var buffer = new byte[32768];
                            int read;
                            while ((read = responseStream.Read(buffer, 0, buffer.Length)) > 0)
                            {
                                fs.Write(buffer, 0, read);
                            }
                            fs.Flush();
                        }

                        ResponseUtils.FormatXmlFile(tempFileName, filePath);

                        try
                        {
                            File.Delete(tempFileName);
                        }
                        catch
                        {
                            //Do nothing
                        }
                        
                    }
                }            
        
        }

        private string CheckForError(string result)
        {
            const string RegexStr = "<ErrorMessage[^>]*?>(?<ErrorText>.*?)</ErrorMessage>";
            MatchCollection mc = Regex.Matches(result, RegexStr, RegexOptions.Singleline);

            if (mc.Count > 0)
            {
                return mc[0].Groups["ErrorText"].Value;
            }

            return null;
        }               
       
    }
}


