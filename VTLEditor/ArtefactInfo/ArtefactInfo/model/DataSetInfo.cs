
namespace ArtefactInfo.model
{
    public class DataSetInfo:BaseArtefactInfo
    {
        public bool isCollected { get; set; }
        public string datastructure_id { get; set; }
        public string sdmx_DataStructure_id{ get; set; }
        public string sdmx_DataStructure_agency { get; set; }
        public string sdmx_DataStructure_version { get; set; }        

        public DataSetInfo():base ()
        { 
            isCollected = false;
        }

        //[SecurityPermission(SecurityAction.Demand, SerializationFormatter = true)]
        //public void GetObjectData(SerializationInfo info, StreamingContext context)
        //{
        //    base.GetObjectData(info, context);
        //    info.AddValue("isCollected",isCollected);
        //    info.AddValue("datastructure_id",datastructure_id);
        //    info.AddValue("sdmx_DataStructure", sdmx_DataStructure_id);
        //    info.AddValue("sdmx_DataStructure_agency",sdmx_DataStructure_agency);
        //    info.AddValue("sdmx_DataStructure_version",sdmx_DataStructure_version);
        //}

    }
}
