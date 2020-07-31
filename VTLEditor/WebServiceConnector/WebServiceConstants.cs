
namespace WebServiceConnector
{
    public class WebServiceConstants
    {
        public struct QueryType
        {
          //2.0
          public const string _QUERYSTRUCTURE = "QueryStructure";
          public const string _GETCOMPACTDATA = "GetCompactData";
          public const string _GETGENERICDATA = "GetGenericData";
          public const string _GETCROSSSECTIONALDATA = "GetCrossSectionalData";

          //Metadata 2.1
          public const string _DATAFLOW21 = "GetDataflow";
          public const string _DATASTRUCTURE21 = "GetDataStructure";
          public const string _CODELIST21 = "GetCodelist";
        }

        
        public struct ResultType
        {
            public const string _XML = "XML";
            public const string _SDMX = "SDMX";
            public const string _CSV = "CSV";
        }
    }
}
