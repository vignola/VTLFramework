using System;
using System.Collections.Generic;
using System.Linq;
using System.Net;
using System.Text;
using System.Threading.Tasks;

namespace VTL_Editor_PL.classes.tool
{
    class RemoteFileExists
    {
        public static bool Check(string url, bool getMethod)
        {
            try
            {
                //Creating the HttpWebRequest
                HttpWebRequest request = WebRequest.Create(url) as HttpWebRequest;
                //Setting the Request method HEAD, you can also use GET too.
                if (getMethod)
                    request.Method = "GET";
                else
                    request.Method = "POST";
                //Getting the Web Response.
                HttpWebResponse response = request.GetResponse() as HttpWebResponse;
                //Returns TRUE if the Status code == 200
                return (response.StatusCode == HttpStatusCode.OK);
            }
            catch (Exception ex)
            {
                string parola = ex.Message;
                return false;
            }
        }       
    }
}
