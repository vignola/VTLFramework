using System.Collections.Generic;

namespace ArtefactInfo.model
{
    public class BaseArtefactInfo //: ISerializable 
    {       
        public static class CREATION_TYPE
        {
            public const int SDMX_WS_UPLOAD = 0;
            public const int TRANSFORMATION_RESULT = 1;
            public const int USER_DEFINED = 2;
        }

        #region PUBLIC FIELDS 

        public string vtlId { get; set; }
        public string sdmxId { get; set; }
        public string sdmxAgency { get; set; }
        public string sdmxVersion { get; set; }
        public List<LocalizedValue> name { get; set; }
        public List<LocalizedValue> description { get; set; }
        public string dataSource { get; set; }
        public int creationType { get; set; }

        public DataStructureInformation DataStructureDetails  { get; set; }
        
        #endregion

        public class DataStructureInformation//:ISerializable
        {
            public List<BaseComponentInfo> Components;
            public DataStructureInformation() { Components = new List<BaseComponentInfo>(); }

            //[SecurityPermission(SecurityAction.Demand, SerializationFormatter = true)]
            //public void GetObjectData(SerializationInfo info, StreamingContext context)
            //{
            //    if (info == null)
            //        throw new ArgumentNullException("info");
            //    info.AddValue("Components", Components);
            //}
        }
       
        public BaseArtefactInfo()
        {
            DataStructureDetails = new DataStructureInformation();
        }

        //[SecurityPermission(SecurityAction.Demand, SerializationFormatter = true)]
        //public void GetObjectData(SerializationInfo info, StreamingContext context)
        //{
        //    if (info == null)
        //        throw new ArgumentNullException("info");

        //    info.AddValue("vtlId",vtlId);
        //    info.AddValue("sdmxId",sdmxId);
        //    info.AddValue("sdmxAgency",sdmxAgency);
        //    info.AddValue("sdmxVersion",sdmxVersion);
        //    info.AddValue("name",name);
        //    info.AddValue("description", description);
        //    info.AddValue("dataSource",dataSource);
        //    info.AddValue("DataStructureDetails", DataStructureDetails);
        //}

        public override string ToString()
        {
            return vtlId;
        }
        
    }

    
}