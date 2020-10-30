using System;
using System.ServiceModel;
using System.Xml;

namespace VTL_Editor_PL.classes.net
{
    public class VTLInt_ServiceManager
    {

        private static Uri _baseAddress = new Uri("http://pc79415.pc.istat.it/VTLInt_WS/");
        private static string _address = "http://pc79415.pc.istat.it/VTLInt_WS/VTLInt_WS.svc";

        //private static Uri _baseAddress = new Uri("http://localhost:2997/");
        //private static string _address = "http://localhost:2997/VTLInt_WS.svc";              

        public static VTLInt_Service.ServiceClient GetClient(ApplicationSettings.classes.services.ApplicationSettings.WebServiceInfo WSInfo)
        {
            try
            {
                _address = WSInfo.WebService_public_URL;
                _baseAddress = new Uri(_address.Substring(0, _address.LastIndexOf('/') + 1));

                BasicHttpBinding binding = new BasicHttpBinding();
                binding.MaxReceivedMessageSize = 20000000;
                binding.MaxBufferSize = 20000000;
                binding.MaxBufferPoolSize = 20000000;
                binding.AllowCookies = true;
                XmlDictionaryReaderQuotas readerQuotas = new XmlDictionaryReaderQuotas();
                readerQuotas.MaxArrayLength = 20000000;
                readerQuotas.MaxStringContentLength = 20000000;
                readerQuotas.MaxDepth = 32;
                binding.ReaderQuotas = readerQuotas;
                if (_baseAddress.Scheme.ToLower() == "https")
                    binding.Security.Mode = BasicHttpSecurityMode.Transport;

                VTLInt_Service.ServiceClient client = new VTLInt_Service.ServiceClient(binding, new EndpointAddress(_address));

                return client;
            }
            catch (Exception ex)
            {
                throw new Exception(ex.Message);
            }
        }

    }
}
