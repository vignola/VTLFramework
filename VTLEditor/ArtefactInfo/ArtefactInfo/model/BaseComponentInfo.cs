using System.Collections.Generic;

namespace ArtefactInfo.model
{
    public class BaseComponentInfo//:ISerializable
    {
        public string vtlId { get; set; }
        public string seq_id { get; set; }
        public List<LocalizedValue> name { get; set; }
        public string datastructure_id_ref { get; set; }
        public ValueDomainInfoInDataStructure ValueDomainInfo { get; set; }
        public string Role { get; set; }

        //[SecurityPermission(SecurityAction.Demand, SerializationFormatter = true)]
        //public void GetObjectData(SerializationInfo info, StreamingContext context)
        //{
        //    if (info == null)
        //        throw new ArgumentNullException("info");
        //    info.AddValue("vtlId", vtlId);
        //    info.AddValue("name", name);
        //    info.AddValue("datastructure_id_ref", datastructure_id_ref);
        //}
    }
}