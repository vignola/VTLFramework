using System;
using System.Linq;

namespace ArtefactInfo.model
{
    public class CommonConstant
    {
        public const string VTLID_SEPARATOR = "__";

        public static string[] splitGlobalID(string globalId) 
        {   try
            {
                string[] splitted=globalId.Split(new[] { CommonConstant.VTLID_SEPARATOR }, StringSplitOptions.None);
                if (splitted.Count() != 3) return null;
                return splitted;
            }
            catch (Exception ex) 
            {
                throw new Exception(ex.Message);
            }
        }

        public static string ToGlobalID(string id, string agency, string version) 
        {
            return id + VTLID_SEPARATOR + agency + VTLID_SEPARATOR + version;
        }

        public static string ToGlobalID(string[] components)
        {   try
            {
                if (components.Length != 3) return null;
                return components[0] + VTLID_SEPARATOR + components[1] + VTLID_SEPARATOR + components[2];
            }
            catch (Exception ex) 
            {
                throw new Exception(ex.Message);
            }
        }
    }
}
