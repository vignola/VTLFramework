using System;
using System.IO;
using System.Net;
using System.Net.Http;
using System.Net.Http.Headers;

namespace WebServiceConnector
{
    public class RestWebConnector
    {
        private string _endPointAddress = null;
        private Uri _baseAddress = null;
        private static HttpClient _HttpClient = null;

        public RestWebConnector(string EndPoint) 
        {
            _endPointAddress = EndPoint;
            _baseAddress = new Uri(_endPointAddress.Substring(0, _endPointAddress.LastIndexOf('/') + 1));

        }

        public string SendGetFileRequest()
        {
            var request = (HttpWebRequest)WebRequest.Create(_endPointAddress);
 
            request.Method = "GET";
            request.UserAgent = "VTLEditorGUI";

            request.AutomaticDecompression = DecompressionMethods.Deflate | DecompressionMethods.GZip;
 
            var content = string.Empty;
 
            using (var response = (HttpWebResponse)request.GetResponse())
            {
                using (var stream = response.GetResponseStream())
                {
                    using (var sr = new StreamReader(stream))
                    {
                        content = sr.ReadToEnd();
                    }
                }
            }
 
            return content;
         }

        

        public string SendGetRequest(string RestMessage) 
        {
            if (_HttpClient==null) _HttpClient = new HttpClient();
            _HttpClient.BaseAddress = _baseAddress;

            // Add an Accept header for JSON format.
            _HttpClient.DefaultRequestHeaders.Accept.Add(
            new MediaTypeWithQualityHeaderValue("application/json"));

            // List data response.
            HttpResponseMessage response = _HttpClient.GetAsync(_endPointAddress + "?" + RestMessage).Result;  // Blocking call! Program will wait here until a response is received or a timeout occurs.
            
            if (response.IsSuccessStatusCode)
            {
                // Parse the response body.
                var dataObjects =response.Content.ReadAsStringAsync();   //Make sure to add a reference to System.Net.Http.Formatting.dll

                Console.WriteLine("{0}", dataObjects.GetType());
               
            }
            else
            {
                Console.WriteLine("{0} ({1})", (int)response.StatusCode, response.ReasonPhrase);
            }

            return null;
        }

        public string SendPostRequest(String RestMessage)
        {
            if (_HttpClient == null)
            {
                _HttpClient = new HttpClient();
                _HttpClient.BaseAddress = _baseAddress;
            }

            // Add an Accept header for JSON format.
            _HttpClient.DefaultRequestHeaders.Accept.Add(
            new MediaTypeWithQualityHeaderValue("application/json"));

            // List data response.            
            StringContent queryString = new StringContent(RestMessage);
            HttpResponseMessage response = _HttpClient.PostAsync(_endPointAddress, queryString).Result;
            

            //if (response.IsSuccessStatusCode)
            //{
                // Parse the response body.
                var dataObjects = response.Content.ReadAsStringAsync();   //Make sure to add a reference to System.Net.Http.Formatting.dll

                Console.WriteLine("{0}", dataObjects.GetType());

            //}
            //else
            //{
            //    Console.WriteLine("{0} ({1})", (int)response.StatusCode, response.ReasonPhrase);
            //}

                return dataObjects.Result.ToString();
        }
        
    }
}
